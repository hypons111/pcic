<template>
  <div class="w-100" :class="isValidRef ? 'form-check-with-marginB' : 'form-check-no-marginB'">
    <b-form-row no-gutters>
      <b-col cols="5">
        <b-input-group prepend="前4碼">
          <b-form-input
            maxlength="4"
            :readonly="readOnlyProp"
            v-model="$v.preFour.$model"
            type="text"
            placeholder="如:0933"
            :state="validateState($v.preFour)"
          ></b-form-input>
          <b-form-invalid-feedback v-for="(error, index) of $v.preFour.$errors" :key="'mobileTel4-' + index">
            {{ error.$message }}
          </b-form-invalid-feedback>
        </b-input-group>
      </b-col>
      <b-col cols="7">
        <b-input-group prepend="後6碼">
          <b-form-input
            maxlength="6"
            :readonly="readOnlyProp"
            v-model="$v.postSix.$model"
            type="text"
            placeholder="如:112233"
            :state="validateState($v.postSix)"
            ref="phoneNumPostSixComp"
          ></b-form-input>
          <b-form-invalid-feedback v-for="(error, index) of $v.postSix.$errors" :key="'mobileTel6-' + index">
            {{ error.$message }}
          </b-form-invalid-feedback>
        </b-input-group>
      </b-col>
    </b-form-row>
  </div>
</template>

<script lang="ts">
/**
 *   上層傳來字串，在這裡把字串分割處理並驗證，回傳固定形式的phone字串形式上去
 */
//【父元件寫法】
//
// template內：
//   <i-phone-num v-model="phoneNumVal"           用v-model雙向繫節
//                ref="iphoneNum">                call i-phone-num 裡面的方法(ex:reset)
//  </i-phone-num>
//  其他參數
//   :isRequired:判斷該欄位是否必填
//   :readonly :是否唯讀
//
// script裡：
//    1)再加了ref後 呼叫reset
//      const iphoneNum = ref(null);
//      iphoneNum.value.reset();
//
import { Ref, ref, toRef, reactive, watch } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { mobileTel4, mobileTel6, required, requiredIf } from '@/shared/validators';
export default {
  name: 'i-phone-num',
  props: {
    value: {
      required: false,
      type: String,
      default: '',
    },
    isRequired: {
      required: false,
      type: Boolean,
      default: false,
    },
    readonly: {
      required: false,
      type: Boolean,
      default: false,
    },
  },
  model: {
    prop: 'value',
    event: 'getValue',
  },
  setup(props, context) {
    //from parent component, format like 0912-3456789 or 0912345678
    const phoneNumProp: Ref = toRef(props, 'value');
    const isRequiredProp: Ref = toRef(props, 'isRequired');
    const readOnlyProp: Ref = toRef(props, 'readonly');
    const phoneNumPostSixComp = ref(null);

    const isValidRef = ref(true);
    // console.log('props value', props.value);
    let seperatePhoneNumDefault = {
      preFour: '',
      postSix: '',
    };
    let seperatePhoneNum = reactive(Object.assign({}, seperatePhoneNumDefault));

    if (phoneNumProp.value !== null && phoneNumProp.value.length > 0) {
      processPropsData();
    }
    let rules = {};
    if (isRequiredProp.value) {
      rules = {
        preFour: { mobileTel4: mobileTel4(() => seperatePhoneNum.preFour), required: required },
        postSix: { mobileTel6: mobileTel6(() => seperatePhoneNum.postSix), required: required },
      };
    } else {
      rules = {
        preFour: {
          mobileTel4: mobileTel4(() => seperatePhoneNum.preFour),
          requiredIf: requiredIf(() => seperatePhoneNum.postSix.length > 0),
        },
        postSix: {
          mobileTel6: mobileTel6(() => seperatePhoneNum.postSix),
          requiredIf: requiredIf(() => seperatePhoneNum.preFour.length > 0),
        },
      };
    }

    let { $v, checkValidity, reset } = useValidation(rules, seperatePhoneNum, seperatePhoneNumDefault);

    // 也可以不用context emit,就不用隨時判斷，寫一個function讓外面的call
    watch(seperatePhoneNum, () => {
      //在不是required的情況下，如果都是空的的話，驗證會有問題，所以需要reset()
      if (!isRequiredProp.value && seperatePhoneNum.preFour === '' && seperatePhoneNum.postSix === '') {
        seperatePhoneNumDefault.preFour = '';
        seperatePhoneNumDefault.postSix = '';
        reset();
      }
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          context.emit('getValue', seperatePhoneNum.preFour + '-' + seperatePhoneNum.postSix);
        } else {
          context.emit('getValue', null);
        }
      });
    });
    //判斷底下margin
    watch($v.value.preFour, newValue => {
      // console.log('watch')
      if (!newValue.$error && !$v.value.postSix.$error) {
        isValidRef.value = true;
      } else {
        isValidRef.value = false;
      }
    });
    watch($v.value.postSix, newValue => {
      // console.log('watch')
      if (!newValue.$error && !$v.value.preFour.$error) {
        isValidRef.value = true;
      } else {
        isValidRef.value = false;
      }
    });
    //自動跳格
    watch(
      () => seperatePhoneNum.preFour,
      newValue => {
        if (newValue.length === 4) {
          phoneNumPostSixComp.value.focus();
        }
      }
    );

    //把父元件來的值 assign到此元件的變數
    function processPropsData() {
      let splitPhoneArr: string[] = splitPhoneNum(phoneNumProp.value);
      seperatePhoneNumDefault.preFour = splitPhoneArr[0];
      seperatePhoneNumDefault.postSix = splitPhoneArr[1];

      seperatePhoneNum.preFour = splitPhoneArr[0];
      seperatePhoneNum.postSix = splitPhoneArr[1];
    }

    //切割傳下來的手機號碼
    function splitPhoneNum(phoneNum: string): string[] {
      let returnStringArr = [];
      switch (phoneNum.length) {
        //單純數字
        case 10:
          returnStringArr[0] = phoneNum.substring(0, 4);
          returnStringArr[1] = phoneNum.substring(4);
          break;
        //或許有加一個'-'
        case 11:
          //有加一個'-'
          if (phoneNum.indexOf('-') > 0) {
            returnStringArr[0] = phoneNum.split('-')[0];
            returnStringArr[1] = phoneNum.split('-')[1];
            break;
          }
        //沒有就去default特殊處理
        default:
          handleSpecialCase();
          break;
      }

      return returnStringArr;
    }

    function handleSpecialCase() {
      //到時候要寫特殊處理，現在不知道有什麼狀況
    }

    return {
      $v,
      isValidRef,
      readOnlyProp,
      phoneNumPostSixComp,
      validateState,
      reset,
      checkValidity,
    };
  },
};
</script>
<style scoped>
.form-check-no-marginB {
  margin-bottom: 0px !important;
}
.form-check-with-marginB {
  margin-bottom: 24px !important;
}
.w-100 {
  width: 100%;
}
</style>

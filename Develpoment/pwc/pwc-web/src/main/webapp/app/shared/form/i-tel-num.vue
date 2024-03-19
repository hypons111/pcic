<template>
  <div class="w-100" :class="isValidRef ? 'form-check-with-marginB' : 'form-check-no-marginB'">
    <b-form-row>
      <b-col cols="3">
        <b-input-group prepend="區碼">
          <b-form-input
            maxlength="3"
            v-model="$v.areaNumber.$model"
            :readonly="readOnlyProp"
            type="text"
            placeholder="如:02"
            :state="validateState($v.areaNumber)"
          ></b-form-input>
          <b-form-invalid-feedback v-for="(error, index) of $v.areaNumber.$errors" :key="'areaNumber-' + index">
            {{ error.$message }}
          </b-form-invalid-feedback>
        </b-input-group>
      </b-col>
      <b-col cols="5">
        <b-input-group prepend="電話號碼">
          <b-form-input
            maxlength="8"
            v-model="$v.telNumber.$model"
            :readonly="readOnlyProp"
            type="text"
            placeholder="如:22551124"
            :state="validateState($v.telNumber)"
            ref="telNumComponent"
          ></b-form-input>
          <b-form-invalid-feedback v-for="(error, index) of $v.telNumber.$errors" :key="'telNumber-' + index">
            {{ error.$message }}
          </b-form-invalid-feedback>
        </b-input-group>
      </b-col>
      <b-col cols="4">
        <b-input-group prepend="分機號碼">
          <b-form-input
            maxlength="4"
            :readonly="isExtendReadonly || readOnlyProp"
            v-model="$v.extendNumber.$model"
            type="text"
            placeholder="如:1234"
            :state="validateState($v.extendNumber)"
          ></b-form-input>
          <b-form-invalid-feedback v-for="(error, index) of $v.extendNumber.$errors" :key="'extendNumber-' + index">
            {{ error.$message }}
          </b-form-invalid-feedback>
        </b-input-group>
      </b-col>
    </b-form-row>
  </div>
</template>

<script lang="ts">
import { BCol, BFormInput, BFormInvalidFeedback, BFormRow, BInputGroup } from 'bootstrap-vue';
/**
 *   上層傳來字串，在這裡把字串分割處理並驗證，回傳固定形式的phone字串形式上去
 */
//【父元件寫法】
//
// template內：
//   <i-phone-num v-model="telNumVal"           用v-model雙向繫節
//                ref="iTelNum">                call i-tel-num 裡面的方法(ex:reset)
//  </i-phone-num>
//
// script裡：
//   1) 再加了ref後 呼叫reset
//      const iphoneNum = ref(null);
//      iphoneNum.value.reset();
//
import { useValidation, validateState } from '@/shared/form';
import { areaNumber, extendNumber, required, requiredIf, telNumber } from '@/shared/validators';
import { reactive, Ref, ref, toRef, watch } from '@vue/composition-api';
export default {
  name: 'i-phone-num',
  components: { BCol, BFormInput, BFormInvalidFeedback, BFormRow, BInputGroup },
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
    //from parent component, format like 02-12345678 or 02-12345678#1001
    const phoneNumProp: Ref = toRef(props, 'value');
    const isRequiredProp: Ref = toRef(props, 'isRequired');
    const readOnlyProp: Ref = toRef(props, 'readonly');
    const isValidRef = ref(true);

    let telNumComponent = ref(null);
    let isExtendReadonly = ref(true);

    let seperateTelNumDefault = {
      areaNumber: '',
      telNumber: '',
      extendNumber: '',
    };
    let seperateTelNum = reactive(Object.assign({}, seperateTelNumDefault));

    if (phoneNumProp.value !== null && phoneNumProp.value.length > 0) {
      processPropsData();
    }

    let rules = {};
    if (isRequiredProp.value) {
      rules = {
        areaNumber: { areaNumber: areaNumber(() => seperateTelNum.areaNumber), required: required },
        telNumber: { telNumber: telNumber(() => seperateTelNum.telNumber), required: required },
        extendNumber: { extendNumber: extendNumber(() => seperateTelNum.extendNumber) },
      };
    } else {
      rules = {
        areaNumber: {
          areaNumber: areaNumber(() => seperateTelNum.areaNumber),
          requiredIf: requiredIf(() => seperateTelNum.telNumber.length > 0),
        },
        telNumber: {
          telNumber: telNumber(() => seperateTelNum.telNumber),
          requiredIf: requiredIf(() => seperateTelNum.areaNumber.length > 0),
        },
        extendNumber: { extendNumber: extendNumber(() => seperateTelNum.extendNumber) },
      };
    }

    let { $v, checkValidity, reset } = useValidation(rules, seperateTelNum, seperateTelNumDefault);

    watch(seperateTelNum, () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          let returnNumber = seperateTelNum.areaNumber + '-' + seperateTelNum.telNumber;
          returnNumber = seperateTelNum.extendNumber === '' ? returnNumber : returnNumber + '#' + seperateTelNum.extendNumber;
          context.emit('getValue', returnNumber);
        } else {
          context.emit('getValue', null);
        }
      });
    });

    //判斷欄位輸入情況
    watch(
      seperateTelNum,
      () => {
        if (!isRequiredProp.value && seperateTelNum.areaNumber === '' && seperateTelNum.telNumber === '') {
          seperateTelNumDefault.areaNumber = '';
          seperateTelNumDefault.telNumber = '';
          seperateTelNumDefault.extendNumber = '';
          reset();
          isExtendReadonly.value = true;
        } else if (seperateTelNum.areaNumber === '' && seperateTelNum.telNumber === '') {
          isExtendReadonly.value = true;
        } else {
          isExtendReadonly.value = false;
        }
      },
      { immediate: true }
    );

    //判斷底下margin
    watch($v.value.areaNumber, newValue => {
      if (!newValue.$error && !$v.value.telNumber.$error && !$v.value.extendNumber.$error) {
        isValidRef.value = true;
      } else {
        isValidRef.value = false;
      }
    });

    watch($v.value.telNumber, newValue => {
      if (!newValue.$error && !$v.value.areaNumber.$error && !$v.value.extendNumber.$error) {
        isValidRef.value = true;
      } else {
        isValidRef.value = false;
      }
    });
    watch($v.value.extendNumber, newValue => {
      if (!newValue.$error && !$v.value.areaNumber.$error && !$v.value.telNumber.$error) {
        isValidRef.value = true;
      } else {
        isValidRef.value = false;
      }
    });
    //區碼輸入完自動跳格
    // watch(()=>seperateTelNum.areaNumber,(newValue)=>{
    //   if(newValue.length === 2){
    //     telNumComponent.value.focus();
    //   }
    // })

    //把父元件來的值 assign到此元件的變數
    function processPropsData() {
      let splitTelNumArr: string[] = splitTelNum(phoneNumProp.value);
      seperateTelNumDefault.areaNumber = splitTelNumArr[0];
      seperateTelNumDefault.telNumber = splitTelNumArr[1];
      seperateTelNumDefault.extendNumber = splitTelNumArr[2];

      seperateTelNum.areaNumber = splitTelNumArr[0];
      seperateTelNum.telNumber = splitTelNumArr[1];
      seperateTelNum.extendNumber = splitTelNumArr[2];
    }

    //切割傳下來的電話號碼，變成陣列形式['02','12345678','5555']
    function splitTelNum(telNum: string): string[] {
      let returnStringArr = ['', '', ''];
      //先處理分機
      if (telNum.indexOf('#') > 0) {
        let extendArr = telNum.split('#');
        telNum = extendArr[0];
        returnStringArr[2] = extendArr[1];
      }
      //處理完分機看長度
      let telNumLength = telNum.trim().length;
      //如果是02-12345678 只有一個 - 的型態 或是 02 12345678這樣
      if (telNumLength >= 9 && telNumLength <= 11) {
        if (telNum.indexOf('-') > 0) {
          returnStringArr[0] = telNum.split('-')[0];
          returnStringArr[1] = telNum.split('-')[1];
        } else {
          returnStringArr[0] = telNum.substring(0, 2);
          returnStringArr[1] = telNum.substring(2);
        }
      } else {
        handleSpecialCase();
      }
      return returnStringArr;
    }
    function handleSpecialCase() {
      //到時候要寫特殊處理，現在不知道有什麼狀況
    }

    return {
      $v,
      readOnlyProp,
      isValidRef,
      isExtendReadonly,
      telNumComponent,
      validateState,
      reset,
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

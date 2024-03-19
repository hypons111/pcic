<template>
  <b-input-group>
    <template #prepend>
      <b-form-select 
        v-if="showTelType" 
        class="telTypeBgColor" 
        v-model="telType" 
        @change="telTypeChangeEvent"
      >
        <b-form-select-option :value="true">行動電話</b-form-select-option>
        <b-form-select-option :value="false">有線電話</b-form-select-option>
      </b-form-select>
    </template>
    <b-form-input
      ref="frontTelComponent"
      maxlength="4"
      v-bind="$attrs"
      v-model="tel.frontTel"
      :class="cols[0]"
      :placeholder="telType ? '前四碼' : '區碼'"
      @input="frontTelInputEvent"
      lazy
      trim
    ></b-form-input>
    <b-input-group-text>-</b-input-group-text>
    <b-form-input
      ref="backTelComponent"
      v-bind="$attrs"
      v-model="tel.backTel"
      :class="cols[1]"
      :placeholder="telType ? '後六碼' : '局碼'"
      @input="backTelInputEvent"
      lazy
      trim
    ></b-form-input>
    <b-input-group-text v-if="!telType && showExt">#</b-input-group-text>
    <b-form-input
      v-if="!telType && showExt"
      v-bind="$attrs"
      v-model="tel.extTel"
      placeholder="分機"
      :class="cols[2]"
      @input="extTelInputEvent"
      lazy
      trim
    ></b-form-input>
  </b-input-group>
</template>

<script lang="ts">
import { ref, reactive, watch, toRefs, unref } from '@vue/composition-api';
export default {
  name: 'i-tel',
  inheritAttrs: false,
  props: {
    value: {
      required: false,
      type: String,
      default: null,
    },
    isMobile: {
      required: false,
      type: Boolean,
      default: false,
    },
    showTelType: {
      required: false,
      type: Boolean,
      default: false,
    },
    showExt: {
      required: false,
      type: Boolean,
      default: false,
    },
  },
  model: {
    prop: 'value',
    event: 'update',
  },
  setup(props, context) {
    const { value, showTelType, showExt } = toRefs(props);
    const telType = ref(props.isMobile); // 行動電話 = true, 有線電話 = false
    const frontTelComponent = ref(null);
    const backTelComponent = ref(null);
    const cols = ref(getCols());

    const tel = reactive({
      frontTel: '',
      backTel: '',
      extTel: '',
    });

    watch(value, updateInnerTel, { immediate: true })
    watch(tel, updateOuterTel)

    function updateInnerTel(newVal: string) {
      if(newVal !== concatTel()) {
        splitTel(newVal);
        if (unref(showTelType) && tel.frontTel.length === 4 && tel.frontTel.substring(0, 2) === '09') {
          telType.value = true;
        }
      }
    }

    function updateOuterTel() {
      const innerTel = concatTel();
      if(unref(value) !== innerTel) {
        context.emit('update', innerTel);
      }
    }

    function splitTel(value: string) {
      if (value) {
        let valueArr = value.split(/[-#]/);
        if (valueArr.length === 2) {
          tel.frontTel = valueArr[0];
          tel.backTel = valueArr[1];
          tel.extTel = '';
        } else if (valueArr.length === 3) {
          tel.frontTel = valueArr[0];
          tel.backTel = valueArr[1];
          tel.extTel = valueArr[2];
        } else if (valueArr.length === 1 && value.length === 10 && value.substring(0, 2) === '09') {
          tel.frontTel = value.substring(0, 4);
          tel.backTel = value.substring(4);
          tel.extTel = '';
        } else {
          tel.frontTel = '';
          tel.backTel = value;
          tel.extTel = '';
        }
      } else {
        tel.frontTel = '';
        tel.backTel = '';
        tel.extTel = '';
      }
    }

    function concatTel() {
      if (!tel.frontTel && !tel.backTel && !tel.extTel) return null;
      if (tel.extTel != '') {
        return tel.frontTel.concat('-', tel.backTel, '#', tel.extTel);
      } else {
        return tel.frontTel.concat('-', tel.backTel);
      }
    }

    function frontTelInputEvent(value: string) {
      if (unref(telType) && value.length === 4) {
        backTelComponent.value.focus();
      }
    }

    function backTelInputEvent(value: string) {
      if (value.length === 0) {
        frontTelComponent.value.focus();
      }
    }

    function extTelInputEvent(value: string) {
      if (value.length === 0) {
        backTelComponent.value.focus();
      }
    }

    function telTypeChangeEvent() {
      cols.value = getCols();
      // 以下為了切換telType時能觸發重新驗證
      const innerTel = concatTel();
      const updateTel = unref(showExt) && innerTel ? innerTel.split(/[#]/)[0] : innerTel; // 有顯示分機，切換telType時去掉"#"後面
      new Promise<void>((resolve) => {
        context.emit('update', null);
        resolve();
      }).then(() => {
        // context.emit('update', innerTel); 用這變更外面的值，觸發updateInnerTel，會導致第一欄已輸入"09XX"時，[行動電話]無法切至[有線電話]
        splitTel(updateTel); // 用這先變更裡面的值，觸發updateOuterTel，再觸發updateInnerTel，就會被內外值相同不更新擋住
      })
    }

    function getCols(): String[] {
      if (unref(showTelType) && unref(showExt) && !unref(telType)) {
        return ['col-2', 'col-7', 'col-2'];
      } else if (unref(showTelType)) {
         return ['col-3', 'col-8'];
      } else if (unref(showExt) && !unref(telType)) {
        return ['col-3', 'col-6', 'col-3'];
      } else {
        return ['col-4', 'col-8'];
      }
    }

    return {
      tel,
      cols,
      telType,
      frontTelComponent,
      backTelComponent,
      frontTelInputEvent,
      backTelInputEvent,
      extTelInputEvent,
      telTypeChangeEvent,
    }
  },
};
</script>
<style scoped>
.telTypeBgColor {
  background-color: rgb(218, 245, 255);
}
</style>

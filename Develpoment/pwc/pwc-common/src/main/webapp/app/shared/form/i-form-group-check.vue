<template>
  <b-form-group
    v-bind="$attrs"
    :label="labelProp"
    :class="{ 'form-check-no-marginB': marginCheck, 'form-check-with-marginB': !marginCheck, 'col-6': staticClass ? false : true }"
    :label-cols="$attrs['label-cols'] ? $attrs['label-cols'] : 4"
    :content-cols="$attrs['content-cols'] ? $attrs['content-cols'] : 8"
    :label-align="$attrs['label-align'] ? $attrs['label-align'] : 'right'"
    label-class="label"
  >
    <template v-if="isLabelStar" v-slot:label> <span class="text-danger">*</span>{{ labelProp }} </template>
    <div class="h-100 d-flex align-items-center flex-wrap">
      <slot></slot>
      <div v-if="marginCheck" class="break"></div>
      <div v-if="dual1Prop !== null && dual2Prop != null && (dual1Prop.$error || dual2Prop.$error)">
        <dual-form-invalid-feedback :dualInvalidFeedback="[dual1Prop.$errors, dual2Prop.$errors]"></dual-form-invalid-feedback>
      </div>

      <div v-else-if="itemProp != null">
        <b-form-invalid-feedback :force-show="true" v-if="marginCheck">
          <!-- {{ itemProp.$errors[0].$message }} -->
          {{ itemErrorsMessage() }}
        </b-form-invalid-feedback>
      </div>
    </div>
  </b-form-group>
</template>

<script lang="ts">
import DualFormInvalidFeedback from '@/shared/form/dual-form-invalid-feedback.vue';
import { watch, defineComponent, ref, toRef, getCurrentInstance } from '@vue/composition-api';

export default defineComponent({
  name: 'i-form-group-check',
  components: { 'dual-form-invalid-feedback': DualFormInvalidFeedback },
  inheritAttrs: false,
  props: {
    bottomPlain: {
      type: Boolean,
      require: false,
      default: true,
    },
    item: {
      type: [Object, Array],
      required: false,
      default: null,
    },
    labelStar: {
      type: Boolean,
      required: false,
      default: false,
    },
    // dualCheck: {
    //   type: Array,
    //   required: false,
    //   default: () => [],
    // },
    label: {
      type: String,
      required: false,
      default: '',
    },
    // 測試用
    dual1: {
      type: Object,
      required: false,
      default: null,
    },
    dual2: {
      type: Object,
      required: false,
      default: null,
    },
    hasInvalid: {
      type: Boolean,
      require: false,
      default: false,
    },
  },
  setup(props, context) {
    const itemProp = toRef(props, 'item');
    const isLabelStar = toRef(props, 'labelStar');
    const labelProp = toRef(props, 'label');
    const isBottomPlain = toRef(props, 'bottomPlain');

    const dual1Prop = toRef(props, 'dual1');
    const dual2Prop = toRef(props, 'dual2');
    const marginCheck = ref(props.hasInvalid);

    const component = getCurrentInstance();
    const staticClass = component.vnode.data.staticClass;

    watch(
      itemProp,
      () => {
        marginCheckFunction();
      },
      { deep: true }
    );
    watch(
      dual1Prop,
      () => {
        dualCheck();
      },
      { deep: true }
    );
    watch(
      dual2Prop,
      () => {
        dualCheck();
      },
      { deep: true }
    );
    watch(
      () => props.hasInvalid,
      newVal => {
        marginCheck.value = newVal;
      }
    );

    function marginCheckFunction(): void {
      // if (!!itemProp.value && itemProp.value.$error) marginCheck.value = true;
      // else marginCheck.value = false;
      if (!!itemProp.value && itemProp.value instanceof Array) {
        marginCheck.value = itemProp.value.find(item => item.$error) ? true : false;
      } else if (!!itemProp.value && itemProp.value.$error) {
        marginCheck.value = true;
      } else {
        marginCheck.value = false;
      }
    }

    function dualCheck(): void {
      if (dual1Prop.value !== null && dual1Prop.value !== null && (dual1Prop.value.$error || dual2Prop.value.$error)) {
        marginCheck.value = true;
      } else {
        marginCheck.value = false;
      }
    }

    function itemErrorsMessage(): string {
      if (!!itemProp.value && itemProp.value instanceof Array) {
        const itemError = itemProp.value.find(item => item.$error);
        return itemError ? itemError.$errors[0].$message : '';
      } else if (!!itemProp.value && itemProp.value.$error) {
        return itemProp.value.$errors[0].$message;
      }
      return '';
    }

    return {
      labelProp,
      marginCheck,
      isBottomPlain,
      isLabelStar,
      itemProp,
      dual1Prop,
      dual2Prop,
      staticClass,
      itemErrorsMessage,
    };
  },
});
</script>

<style scoped>
.form-check-row-height {
  height: 24px;
}

.form-check-no-marginB {
  margin-bottom: 0px !important;
}
.form-check-with-marginB {
  margin-bottom: 24px !important;
}

.text-danger {
  color: red;
  font-weight: bold;
}

.break {
  flex-basis: 100%;
  height: 0;
}
</style>

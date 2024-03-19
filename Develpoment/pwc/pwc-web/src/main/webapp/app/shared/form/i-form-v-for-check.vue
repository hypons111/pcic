<template>
  <div>
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
        <!-- single的 -->
        <slot></slot>
        <b-form-invalid-feedback v-for="(error, index) of $v.validationInput.$errors" :key="index" :force-show="true">
          {{ error.$message }}
        </b-form-invalid-feedback>
        <!-- single的 -->
      </div>
    </b-form-group>
  </div>
</template>
<script lang="ts">
import { useValidation } from '@/shared/form/validations';
import { defineComponent, getCurrentInstance, onMounted, reactive, ref, toRef, watch } from '@vue/composition-api';
import { BFormGroup, BFormInvalidFeedback } from 'bootstrap-vue';

export default defineComponent({
  name: 'i-form-v-for-check',
  components: { BFormGroup, BFormInvalidFeedback },
  inheritAttrs: false,
  props: {
    value: {
      required: true,
    },
    rules: {
      type: Object,
      required: true,
    },
    // 上面是single的
    bottomPlain: {
      type: Boolean,
      require: false,
      default: true,
    },
    labelStar: {
      type: Boolean,
      required: false,
      default: false,
    },
    label: {
      type: String,
      required: false,
      default: '',
    },
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
    const isLabelStar = toRef(props, 'labelStar');
    const labelProp = toRef(props, 'label');
    const isBottomPlain = toRef(props, 'bottomPlain');

    const dual1Prop = toRef(props, 'dual1');
    const dual2Prop = toRef(props, 'dual2');
    const marginCheck = ref(props.hasInvalid);

    const component = getCurrentInstance();
    const staticClass = component.vnode.data.staticClass;

    onMounted(() => {
      context.emit('returnValidity', checkValidity);
    });
    const form = reactive({
      validationInput: props.value,
    });
    const rules = {
      validationInput: props.rules,
    };
    watch(
      () => props.value,
      newVal => {
        form.validationInput = newVal;
      }
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

    function dualCheck(): void {
      if (dual1Prop.value !== null && dual1Prop.value !== null && (dual1Prop.value.$error || dual2Prop.value.$error)) {
        marginCheck.value = true;
      } else {
        marginCheck.value = false;
      }
    }
    const { $v, checkValidity, reset } = useValidation(rules, form, { validationInput: '' });
    return { $v, checkValidity, reset, labelProp, marginCheck, isBottomPlain, isLabelStar, dual1Prop, dual2Prop, staticClass };
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

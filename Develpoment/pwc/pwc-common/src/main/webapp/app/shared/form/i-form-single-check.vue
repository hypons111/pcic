<template>
  <div>
    <slot></slot>
    <b-form-invalid-feedback v-for="(error, index) of $v.validationInput.$errors" :key="index" :force-show="true">
      {{ error.$message }}
    </b-form-invalid-feedback>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, watch } from '@vue/composition-api';
import { useValidation } from '@/shared/form/validations';

export default defineComponent({
  name: 'i-form-single-check',
  inheritAttrs: false,
  props: {
    value: {
      required: true,
    },
    rules: {
      type: Object,
      required: true,
    },
  },
  setup(props, context) {
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
    const { $v, checkValidity, reset } = useValidation(rules, form, { validationInput: '' });
    return { $v, checkValidity, reset };
  },
});
</script>

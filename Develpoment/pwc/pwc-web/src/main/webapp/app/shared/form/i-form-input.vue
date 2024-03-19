<template>
  <div>
    <b-form-input
      v-bind="$attrs"
      :value="value"
      @input="$emit('input', $event)"
      v-model="$v.validationInput.$model"
      :state="validateState($v.validationInput)"
    ></b-form-input>
    <b-form-invalid-feedback v-for="(error, index) of $v.validationInput.$errors" :key="index">
      {{ error.$message }}
    </b-form-invalid-feedback>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form/validations';
import { defineComponent, reactive } from '@vue/composition-api';
import { BFormInput, BFormInvalidFeedback } from 'bootstrap-vue';

export default defineComponent({
  name: 'i-form-input',
  components: { BFormInput, BFormInvalidFeedback },
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
  setup(props) {
    const form = reactive({
      validationInput: props.value,
    });
    const rules = {
      validationInput: props.rules,
    };
    const { $v } = useValidation(rules, form, { validationInput: '' });
    return { $v, validateState };
  },
});
</script>

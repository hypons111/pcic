<template>
  <b-input-group>
    <i-date-picker v-model="innerDual1" :disabled-date="notAfterDateEnd" :disabled="disableBool"></i-date-picker>
    <b-input-group-text>至</b-input-group-text>
    <i-date-picker v-model="innerDual2" :disabled-date="notBeforeDateStart" :disabled="disableBool"></i-date-picker>
  </b-input-group>
</template>

<script>
import IDatePicker from '@/shared/i-date-picker/i-date-picker.vue';
import { ref, toRefs, watch } from '@vue/composition-api';
import { BInputGroup, BInputGroupText } from 'bootstrap-vue';

export default {
  name: 'i-dual-date-picker',
  components: { IDatePicker, BInputGroup, BInputGroupText },
  props: ['dual1', 'dual2', 'disabled'],
  setup(props, context) {
    const { dual1, dual2, disabled } = toRefs(props);
    const innerDual1 = ref(dual1.value);
    const innerDual2 = ref(dual2.value);
    const disableBool = ref(disabled);

    const updateInnerDual1 = value => (innerDual1.value = value);
    const updateInnerDual2 = value => (innerDual2.value = value);
    watch(dual1, updateInnerDual1);
    watch(dual2, updateInnerDual2);

    const updateOuterDual1 = value => {
      if (dual1.value !== innerDual1.value) {
        context.emit('update:dual1', value);
        context.emit('change', value, innerDual2.value);
      }
    };
    const updateOuterDual2 = value => {
      if (dual2.value !== innerDual2.value) {
        context.emit('update:dual2', value);
        context.emit('change', innerDual1.value, value);
      }
    };
    watch(innerDual1, updateOuterDual1);
    watch(innerDual2, updateOuterDual2);

    const notBeforeDateStart = date => {
      const offset = new Date().getTimezoneOffset();
      date = new Date(date.getTime() - offset * 60 * 1000);
      return date < innerDual1.value && innerDual1.value;
    };
    const notAfterDateEnd = date => {
      const offset = new Date().getTimezoneOffset();
      date = new Date(date.getTime() - offset * 60 * 1000);
      return date > innerDual2.value && innerDual2.value;
    };
    watch(disabled, newValue => {
      if (newValue === true) {
        disableBool.value = true;
      } else {
        disableBool.value = false;
      }
    });

    return {
      innerDual1,
      innerDual2,
      notBeforeDateStart,
      notAfterDateEnd,
      disableBool,
    };
  },
};
</script>
<style></style>

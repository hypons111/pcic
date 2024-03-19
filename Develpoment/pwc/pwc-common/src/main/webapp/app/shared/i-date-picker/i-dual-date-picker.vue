
<template>
  <b-input-group>
    <i-date-picker v-model="dual1" :disabled-date="notAfterDateEnd" @change="updateDual1"></i-date-picker>
    <b-input-group-text>至</b-input-group-text>
    <i-date-picker v-model="dual2" :disabled-date="notBeforeDateStart" @change="updateDual2"></i-date-picker>
  </b-input-group>
</template>

<script>
export default {
  name: 'i-dual-date-picker',
  props: ['dual1', 'dual2'],
  setup(props, context) {
    const notBeforeDateStart = date => {
      if (props.dual1) return date < new Date(props.dual1);
    };
    const notAfterDateEnd = date => {
      if (props.dual2) return date > new Date(props.dual2);
    };

    const updateDual1 = value => context.emit('update:dual1', value);
    const updateDual2 = value => context.emit('update:dual2', value);

    return {
      notBeforeDateStart,
      notAfterDateEnd,
      updateDual1,
      updateDual2,
    };
  },
};
</script>
<style>
</style>

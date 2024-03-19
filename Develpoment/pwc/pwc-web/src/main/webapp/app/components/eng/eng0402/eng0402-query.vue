<template>
  <eng-0401-query :isReload="isReload" :criteria="criteria" />
</template>

<script>
import Eng0401Query from '@/components/eng/eng0401/eng0401-query.vue';
import { onActivated } from '@vue/composition-api';
export default {
  name: 'Eng0402Query',
  components: { Eng0401Query },
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
    criteria: {
      type: Object,
      required: false,
    },
  },
};
</script>

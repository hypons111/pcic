<template>
  <eng-0401-edit-info :eng0401="eng0401" :formStatus="formStatus" :criteria="criteria" :role="role" />
</template>

<script>
import Eng0401EditInfo from '@/components/eng/eng0401/eng0401-edit-info.vue';
export default {
  name: 'Eng0402EditInfo',
  components: { Eng0401EditInfo },
  props: {
    eng0401: {
      required: false,
    },
    formStatus: {
      required: false,
    },
    role: {
      required: false,
    },
    criteria: {
      required: false,
    },
  },
};
</script>
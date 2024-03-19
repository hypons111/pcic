<template>
  <b-row>
    <b-col md="6">
      <b-form-invalid-feedback :force-show="true" v-for="(error, index) of dualInvalidFeedback[0]" :key="index">
        {{ error.$message }}
      </b-form-invalid-feedback>
    </b-col>
    <b-col md="5" class="pl-4">
      <b-form-invalid-feedback :force-show="true" v-for="(error, index) of dualInvalidFeedback[1]" :key="index">
        {{ error.$message }}
      </b-form-invalid-feedback>
    </b-col>
  </b-row>
</template>

<script lang="ts">
import { defineComponent } from '@vue/composition-api';

export default defineComponent({
  name: 'dual-form-invalid-feedback',
  props: {
    dualInvalidFeedback: {
      type: Array,
      required: false,
    },
  },
});
</script>

<style scoped></style>

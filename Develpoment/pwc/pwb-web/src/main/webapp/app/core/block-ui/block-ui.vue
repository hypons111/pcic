<template>
  <div class="mask-container" v-if="isLoading">
    <div class="mask"></div>
    <div class="app-loading">
      <img src="../../../content/images/pwc.svg" class="scale-up-center" alt="pwc" />
    </div>
  </div>
</template>

<script>
export default {
  props: {
    isLoading: {
      type: Boolean,
      required: true,
    },
  },
  name: 'blockUI',
};
</script>
<style>
.mask-container {
  position: fixed;
  z-index: 9999;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.mask-container .mask {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #444;
  opacity: 0.5;
}
</style>

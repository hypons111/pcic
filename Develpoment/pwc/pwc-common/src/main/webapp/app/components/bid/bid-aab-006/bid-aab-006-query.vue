<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAab006Info'" :button-name="'開工要件'"></bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';
export default {
  name: 'bidAab006Query',
  components: { BidProjectBasicQueryView },
  setup(props, context) {
    return {};
  },
};
</script>

<style></style>

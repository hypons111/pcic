<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAab005Info'" :button-name="'未開工原因'"></bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';
export default {
  name: 'bidAab005Query',
  components: { BidProjectBasicQueryView },
  setup(props, context) {
    return {};
  },
};
</script>

<style></style>

<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAad003Query'" :buttonName="'環保裁罰狀況'"></bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';
export default {
  name: 'bidAad003Info',
  components: { BidProjectBasicQueryView },
  setup(props, context) {
    return {};
  },
};
</script>

<style></style>

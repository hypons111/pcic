<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAbe002'" :button-name="'改為非工程'"></bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';
export default {
  name: 'bidAbe002Query',
  components: { BidProjectBasicQueryView },
  setup(props, context) {
    return {};
  },
};
</script>

<style></style>

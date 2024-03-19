<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAbb001InfoMenu'"></bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';
export default {
  name: 'bidAbb001',
  components: { BidProjectBasicQueryView },
  setup(props, context) {
    return {};
  },
};
</script>
<style></style>

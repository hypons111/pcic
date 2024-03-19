<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAab001InfoMenu'"></bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';
export default {
  name: 'bidAab001Query',
  components: { BidProjectBasicQueryView },
  setup() {
    return {};
  },
};
</script>
<style></style>

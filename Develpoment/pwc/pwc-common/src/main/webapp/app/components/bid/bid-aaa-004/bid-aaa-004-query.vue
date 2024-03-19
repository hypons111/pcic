<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAaa004InfoMenu'"> </bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';

export default {
  name: 'bidAaa004Query',
  components: { BidProjectBasicQueryView },
  setup(props, context) {
    return {};
  },
};
</script>
<style></style>

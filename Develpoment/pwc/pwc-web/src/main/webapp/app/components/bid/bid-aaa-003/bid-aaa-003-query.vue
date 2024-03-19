<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAaa003InfoMenu'"> </bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';

export default {
  name: 'bidAaa003',
  components: { BidProjectBasicQueryView },
  setup(props, context) {
    return {};
  },
};
</script>
<style></style>

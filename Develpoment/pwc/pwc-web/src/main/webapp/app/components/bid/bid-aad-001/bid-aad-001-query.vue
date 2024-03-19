<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAad001EditInfo'"></bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';
export default {
  name: 'bidAad001',
  components: { BidProjectBasicQueryView },
  setup() {
    return {};
  },
};
</script>
<style></style>

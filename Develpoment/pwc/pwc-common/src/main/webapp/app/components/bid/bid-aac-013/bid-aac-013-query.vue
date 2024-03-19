<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAac013Info'" :buttonName="'工地型預拌混凝土通報'"></bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';
export default {
  name: 'bidAac013Query',
  components: { BidProjectBasicQueryView },
  setup(props, context) {
    return {};
  },
};
</script>

<style></style>

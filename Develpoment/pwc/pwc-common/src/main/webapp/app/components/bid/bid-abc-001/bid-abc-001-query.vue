<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAbc001Info'">
      <!-- 這邊可以塞自訂義按鈕 -->
      <template v-slot:customButton>
      </template> -->
    </bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';
export default {
  name: 'bidAbc001Query',
  components: { BidProjectBasicQueryView },
  setup(props, context) {
    return {};
  },
};
</script>

<style></style>

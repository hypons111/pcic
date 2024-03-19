<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAaa005Edit'">
      <!-- 這邊可以塞自訂義按鈕
      <template v-slot:customButton>
      </template> -->
    </bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';
import { navigateByNameAndParams } from '@/router/router';
export default {
  name: 'bidAaa005Query',
  components: { BidProjectBasicQueryView },
  setup(props, context) {
    return {};
  },
};
</script>

<style></style>

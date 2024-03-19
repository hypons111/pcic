<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAad007InfoMenu'">
      <!-- 這邊可以塞自訂義按鈕 -->
      <!-- <template v-slot:customButton>
        <i-button type="envelope" @click = "function..."></i-button>
      </template> -->
    </bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';
export default {
  name: 'bidAad007Query',
  components: { BidProjectBasicQueryView },
  setup(props, context) {
    return {};
  },
};
</script>

<style></style>

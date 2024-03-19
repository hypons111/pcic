<template>
  <div>
    <bid-project-basic-query-view :toRouterPath="'bidAaa002EditInfo'">
      <!-- 這邊可以塞自訂義按鈕 -->
      <template v-slot:customButton>
        <i-button type="file-earmark-plus" @click="toAdd"></i-button>
      </template>
    </bid-project-basic-query-view>
  </div>
</template>

<script lang="ts">
import BidProjectBasicQueryView from '@/components/bid/bid-common-component/bid-project-basic-query-view.vue';
import { navigateByNameAndParams } from '@/router/router';
export default {
  name: 'bidAad002Query',
  components: { BidProjectBasicQueryView },
  setup(props, context) {

    function toAdd() {
      navigateByNameAndParams('bidAaa002Edit');
    }

    return {
      toAdd
    };
  },
};
</script>

<style></style>

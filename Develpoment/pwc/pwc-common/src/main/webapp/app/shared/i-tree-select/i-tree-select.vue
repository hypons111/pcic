<template>
  <div>
    <treeselect
      v-bind="$attrs"
      v-on="$listeners"
      v-bind:class="{ bvFocusStyle: isTreeSelectAct }"
      noResultsText="查無結果"
      noOptionsText="目前無選項"
      searchPromptText="輸入關鍵字查詢..."
      loadingText="查詢中..."
      @open="isTreeSelectAct = true"
      @close="isTreeSelectAct = false"
    >
      <template v-for="(_, slot) of $scopedSlots" v-slot:[slot]="scope">
        <slot :name="slot" v-bind="scope" /> </template
    ></treeselect>
  </div>
</template>

<script lang="ts">
import { ref } from '@vue/composition-api';
import Treeselect from '@riophae/vue-treeselect';
import '@riophae/vue-treeselect/dist/vue-treeselect.css';

export default {
  name: 'i-tree-select',
  components: {
    Treeselect,
  },
  setup(props, context) {
    const isTreeSelectAct = ref(false);
    return {
      isTreeSelectAct,
    };
  },
};
</script>

<style>
.vue-treeselect__value-container {
  height: 38px;
}

.vue-treeselect__control {
  border: 1px solid #ced4da;
}

.bvFocusStyle {
  color: #495057;
  background-color: #fff;
  border-radius: 5px;
  border-color: #80bdff;
  outline: 0;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}
.vue-treeselect--disabled .vue-treeselect__control {
  background-color: #e9ecef;
}
</style>

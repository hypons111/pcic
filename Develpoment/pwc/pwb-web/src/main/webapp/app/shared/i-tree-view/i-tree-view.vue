<template>
  <div v-if="model && model.length">
    <vue-tree-list :model="dataModel">
      <template v-slot:leafNameDisplay="node">
        <span>
          {{ node.model.meta.label }}
        </span>
      </template>
      <template v-slot:treeNodeIcon="node">
        <b-form-checkbox
          v-if="!node.model.meta.directory"
          @input="handleCheckboxChange(node)"
          v-model="node.model.checked"
        ></b-form-checkbox>
        <div v-else></div>
      </template>
    </vue-tree-list>
  </div>
</template>

<script>
import { VueTreeList, Tree } from 'vue-tree-list';
import { createTreeNode } from '@/shared/i-tree-view/common';
import { computed, ref, watch } from '@vue/composition-api';

export default {
  name: 'i-tree-view',
  props: {
    model: {
      type: Array,
      required: false,
    },
  },
  components: {
    VueTreeList,
  },
  setup(props) {
    const data = computed(() => props.model);
    const dataModel = ref(undefined);

    watch(data, () => {
      if (data.value && data.value.length) {
        data.value.forEach(node => createTreeNode(node));
        dataModel.value = new Tree(data.value);
      }
    });

    const checkedNodes = [];

    function handleCheckboxChange() {
      checkedNodes.splice(0, checkedNodes.length);
      addCheckedNode(dataModel.value);
    }

    function addCheckedNode(node) {
      if (node.checked && node.meta.functionId) {
        checkedNodes.push(node.meta.functionId);
      }
      if (node.children && node.children.length > 0) {
        node.children.forEach(child => addCheckedNode(child));
      }
    }

    function getCheckedNode() {
      handleCheckboxChange();
      return checkedNodes;
    }

    return { dataModel, handleCheckboxChange, getCheckedNode };
  },
};
</script>

<style>
.vtl-border {
  display: none !important;
}
</style>

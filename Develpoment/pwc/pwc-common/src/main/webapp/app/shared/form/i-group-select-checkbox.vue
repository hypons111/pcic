<template>
  <div style="height: 40px">
    <b-form-group v-if="isDropdown">
      <b-dropdown size="sm" class="m-2" id="dropdown-button1" :text="textProp.value" variant="light">
        <div class="dropdown-box">
          <div v-for="(item, index) in options" :key="item.child[0].value">
            <b-form-checkbox @change="toggleAll(options[index].parents, index)" :id="options[index].parents">
              {{ options[index].parents }}
            </b-form-checkbox>
            <b-form-checkbox-group v-model="form.data" :options="item.child" class="ml-4" stacked></b-form-checkbox-group>
          </div>
        </div>
      </b-dropdown>
    </b-form-group>
    <b-form-group v-else>
      <div v-for="(item, index) in options" :key="item.child[0].value">
        <b-form-checkbox @change="toggleAll(options[index].parents, index)" :id="options[index].parents">
          {{ options[index].parents }}
        </b-form-checkbox>
        <b-form-checkbox-group v-model="form.data" :options="item.child" class="ml-4" stacked></b-form-checkbox-group>
      </div>
    </b-form-group>
  </div>
</template>

<script lang="ts">
import { reactive, ref, watch, toRef } from '@vue/composition-api';

export default {
  name: 'i-group-select-checkbox',
  props: {
    options: {
      type: Array,
      require: false,
    },
    value: {
      type: Array,
      require: false,
    },
    isDropdown: {
      type: Boolean,
      default: true,
      require: false,
    },
    text: {
      type: String,
      require: false,
    },
  },
  model: {
    prop: 'value',
    event: 'getValue',
  },
  setup(props, context) {
    const form = reactive({
      data: [],
    });

    const selectAllArray = [];

    //如果陣列該值存在會被篩出來
    const arrayFilter = (compareValue, compareArray: Array) => {
      return compareArray.filter(el => el === compareValue);
    };
    //如果陣列該值存在會被砍掉
    const arrayRemover = (compareValue, compareArray: Array) => {
      return compareArray.filter(el => el !== compareValue);
    };

    function toggleAll(id, index): void {
      //先取得完整陣列
      for (let i = 0; i < this.options[index].child.length; i++) {
        selectAllArray[i] = this.options[index].child[i].value;
      }
      //選擇節點
      let checkedNode = document.querySelector('#' + id).checked;
      //清除全部元素
      if (checkedNode === false) {
        selectAllArray.forEach(Element => {
          form.data = arrayRemover(Element, form.data);
        });
      }
      //加入所有元素
      else {
        selectAllArray.forEach(element => {
          if (arrayFilter(element, form.data).length === 0) {
            form.data.push(element);
          }
        });
      }
    }

    const textProp = reactive({});

    watch(form, () => {
      context.emit('getValue', form.data);
    });

    watch(
      form,
      () => {
        if (form.data.length === 0) {
          textProp.value = '請選擇';
        } else {
          textProp.value = '已選擇' + form.data.length + '項';
        }
      },
      { immediate: true }
    );

    return {
      toggleAll,
      form,
      textProp,
    };
  },
};
</script>
<style scoped>
#dropdown-button1 {
  width: 150px;
}
.dropdown-box {
  overflow-x: auto;
  height: 150px;
}
</style>

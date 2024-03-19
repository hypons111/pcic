<template>
  <div style="height: 40px">
    <b-form-group v-if="isDropdown">
      <b-dropdown size="sm" class="m-2" id="dropdown-button1" :text="textProp.value" variant="light">
        <div class="dropdown-box">
          <div v-if="selectAllEvent">
            <b-form-checkbox @change="checkboxChange(true)" v-model="selectAll"> 全選 </b-form-checkbox>
          </div>
          <div v-for="item in options" :key="item.child[0].value">
            <b-form-checkbox @change="checkboxChange(false)" :id="item.parents" v-model="item.value">
              {{ item.parents }}
            </b-form-checkbox>
            <b-form-checkbox-group
              @change="childCheckboxChange"
              v-model="form.data"
              :options="item.child"
              class="ml-4"
              stacked
            ></b-form-checkbox-group>
          </div>
        </div>
      </b-dropdown>
    </b-form-group>
    <b-form-group v-else>
      <div v-for="item in options" :key="item.child[0].value">
        <b-form-checkbox @change="checkboxChange(false)" :id="item.parents" v-model="item.value">
          {{ item.parents }}
        </b-form-checkbox>
        <b-form-checkbox-group
          @change="childCheckboxChange"
          v-model="form.data"
          :options="item.child"
          class="ml-4"
          stacked
        ></b-form-checkbox-group>
      </div>
    </b-form-group>
  </div>
</template>

<script lang="ts">
import { reactive, ref, watch } from '@vue/composition-api';
import { BDropdown, BFormCheckbox, BFormCheckboxGroup, BFormGroup } from 'bootstrap-vue';
import { includes as _includes, keys as _keys, size as _size } from 'lodash';

export default {
  name: 'i-group-select-checkbox',
  components: { BDropdown, BFormCheckbox, BFormCheckboxGroup, BFormGroup },
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
    selectAllEvent: {
      type: Boolean,
      default: false,
      require: false,
    },
  },
  model: {
    prop: 'value',
    event: 'getValue',
  },
  setup(props, context) {
    const form = reactive({ data: [] });
    const optionsProp = reactive(props.options);
    const selectAll = ref(false);
    const textProp = reactive({ value: '請選擇' });
    let count = 0;

    optionsProp.forEach(o => {
      count += _size(o.child);
    });

    function checkboxChange(all) {
      form.data = [];
      props.options.forEach(o => {
        const keys = _keys(o);
        if (_includes(keys, 'value')) o.value = all ? selectAll.value : o.value;
        if (_includes(keys, 'child') && ((all && selectAll.value) || o.value))
          o.child.forEach(c => {
            form.data.push(c.value);
          });
      });
      if (!all) selectAll.value = checkValue('parent');
    }

    function childCheckboxChange() {
      selectAll.value = checkValue('child');
    }

    function checkValue(target) {
      let checkAll = true,
        isChild = target === 'child';
      props.options.forEach(o => {
        const keys = _keys(o);
        if (_includes(keys, 'value')) checkAll = checkAll && o.value;
        if (o.value && !isChild) return;
        if (_includes(keys, 'child'))
          o.child.forEach(c => {
            checkAll = checkAll && _includes(form.data, c.value);
          });
      });
      return checkAll;
    }

    watch(form, () => {
      context.emit('getValue', form.data);
    });

    watch(form, () => (textProp.value = form.data.length === 0 ? '請選擇' : '已選擇' + form.data.length + '項'), {
      immediate: false,
      deep: true,
    });

    return {
      selectAll,
      form,
      textProp,

      checkboxChange,
      childCheckboxChange,
    };
  },
};
</script>
<style scoped>
#dropdown-button1 {
  width: 300px;
}
.dropdown-box {
  overflow-x: auto;
  height: 200px;
}
</style>

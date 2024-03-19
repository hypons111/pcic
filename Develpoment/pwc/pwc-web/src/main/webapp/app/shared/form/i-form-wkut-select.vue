<template>
  <b-form-select class="col-4" v-model="vDataProp[modelKey].$model" :options="formOptionsProp[modelKey]"></b-form-select>
</template>

<script lang="ts">
import BidUnit10Servie from '@/components/bid/bidService/bid-unit10.service';
import { useGetters } from '@u3u/vue-hooks';
import { defineComponent, reactive, ref } from '@vue/composition-api';
import { BFormSelect } from 'bootstrap-vue';
import {
  concat as _concat,
  filter as _filter,
  includes as _includes,
  isArray as _isArray,
  isObject as _isObject,
  keys as _keys,
  size as _size,
} from 'lodash';

export default defineComponent({
  name: 'IFormWkutSelect',
  components: { BFormSelect },
  props: {
    wkutData: {
      required: true,
      type: Object,
      default: () => {},
    },
    vData: {
      required: true,
      type: Object,
      default: () => {},
    },
    formOptions: {
      required: true,
      type: Object,
      default: () => {},
    },
  },
  setup(props) {
    const bidUnit10Service = new BidUnit10Servie();
    const wkutDataProp = reactive(props.wkutData);
    const vDataProp = reactive(props.vData);
    const formOptionsProp = reactive(props.formOptions);
    const orgId = ref(useGetters(['account']).account.value).value.bidWkut[0];
    const modelKey = ref(wkutDataProp.data.modelKey).value;

    bidUnit10Service.findMangUnit(orgId).then(result => {
      formOptionsProp[modelKey].splice(0, formOptionsProp[modelKey].length);
      if (result && result.length > 0) {
        result.forEach(element => {
          const item = {
            value: element.code,
            text: element.name,
          };
          formOptionsProp[modelKey].push(item);
        });
        addSelectAllOption();
      }
    });

    function addSelectAllOption() {
      const keys = _keys(wkutDataProp.data),
        arr = [];
      if (!_includes(keys, 'defaultOption')) return;
      if (!_isObject(wkutDataProp.data.defaultOption)) return;
      if (!_isArray(formOptionsProp[modelKey])) return;
      if (_size(formOptionsProp[modelKey]) === 0) return;
      const filterArr = _filter(formOptionsProp[modelKey], option => {
        return option.value === wkutDataProp.data.defaultOption.value && option.text === wkutDataProp.data.defaultOption.text;
      });
      if (_size(filterArr) === 1) return;
      arr.push(wkutDataProp.data.defaultOption);
      formOptionsProp[modelKey] = _concat(arr, formOptionsProp[modelKey]);
    }

    return {
      wkutDataProp,
      vDataProp,
      orgId,
      modelKey,
      formOptionsProp,
    };
  },
});
</script>

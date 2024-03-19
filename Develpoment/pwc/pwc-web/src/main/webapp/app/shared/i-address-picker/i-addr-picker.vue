<template>
  <div class="w-100">
    <b-form-row v-if="!isText">
      <b-col :cols="cols[0]">
        <b-form-input :value="zip" disabled></b-form-input>
      </b-col>
      <b-col :cols="cols[1]">
        <b-form-select v-bind="$attrs" :value="city" :options="cityOptions" @change="updateCity" :state="cityState"> </b-form-select>
      </b-col>
      <b-col :cols="cols[2]">
        <b-form-select v-bind="$attrs" :value="town" :options="townOptions" @change="updateTown" :state="townState"> </b-form-select>
      </b-col>
      <b-col :cols="cols[3]">
        <!-- <b-form-input v-bind="$attrs" v-model="innerOther" :state="otherState" lazy trim> </b-form-input> -->
        <i-hard-word v-bind="$attrs" v-model="innerOther" :state="otherState"></i-hard-word>
      </b-col>
      <b-col :cols="cols[4]" align-self="center" v-if="cols[4]">
        <slot name="end" />
      </b-col>
    </b-form-row>
    <div v-else>
      <slot
        name="text"
        :zip="zip ? zip : ''"
        :city="formOptionsFormatter(cityOptions, city)"
        :town="formOptionsFormatter(townOptions, town)"
        :other="other ? other : ''"
      >
        <!-- {{
          (zip ? zip + ' ' : '') + formOptionsFormatter(cityOptions, city) + formOptionsFormatter(townOptions, town) + (other ? other : '')
        }} -->
        {{ formOptionsFormatter(cityOptions, city) + formOptionsFormatter(townOptions, town) + (other ? other : '') }}
      </slot>
    </div>
  </div>
</template>

<script lang="ts">
import IHardWord from '@/shared/i-hard-word/i-hard-word.vue';
import { useStore } from '@u3u/vue-hooks';
import { AdmPostAddr } from '@/shared/model/adm/adm-post-addr.model';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { BCol, BFormInput, BFormRow, BFormSelect } from 'bootstrap-vue';
import { computed, defineComponent, onMounted, Ref, ref, toRefs, unref, watch } from '@vue/composition-api';

export default defineComponent({
  name: 'IAddrPicker',
  components: { BCol, BFormInput, BFormRow, BFormSelect, IHardWord },
  inheritAttrs: false,
  props: {
    zip: {
      type: String,
      required: false,
      default: null,
    },
    city: {
      type: String,
      required: false,
      default: null,
    },
    town: {
      type: String,
      required: false,
      default: null,
    },
    other: {
      type: String,
      required: false,
      default: null,
    },
    cityState: {
      required: false,
    },
    townState: {
      required: false,
    },
    otherState: {
      required: false,
    },
    cols: {
      type: Array,
      required: false,
      default: () => [1, 2, 2, 7],
    },
    isText: {
      type: Boolean,
      required: false,
      default: false,
    },
  },
  setup(props, context) {
    const { zip, city, town, other } = toRefs(props);
    const innerOther = ref(props.other);
    const allAddress: Ref<AdmPostAddr[]> = ref([]);
    const cityOptions = ref([]);

    onMounted(() => {
      // axios.get(`/service/adm-post-addrs`).then(res => {
      //   unref(allAddress).splice(0, unref(allAddress).length, ...res.data);
      //   getCityOptions();
      //   if (!unref(zip) && !!unref(city)) context.emit('update:zip', getZip(unref(city).concat(unref(town))));
      // });
      unref(allAddress).splice(0, unref(allAddress).length, ...useStore().value.getters.admPostAddr);
      getCityOptions();
      if (!unref(zip) && !!unref(city)) context.emit('update:zip', getZip(unref(city).concat(unref(town))));
    });

    // reset時避免zip沒值
    watch(zip, newVal => {
      if (!newVal && !!unref(city)) {
        context.emit('update:zip', getZip(unref(city).concat(unref(town))));
      }
    });

    watch(other, updateInnerOther);
    watch(innerOther, updateOuterOther);

    function updateOuterOther(value: string) {
      if (unref(other) !== unref(innerOther)) {
        context.emit('update:other', value);
      }
    }

    function updateInnerOther(value: string) {
      if (unref(other) !== unref(innerOther)) {
        innerOther.value = value;
      }
    }

    const townOptions = computed(() => {
      const townOptions = unref(allAddress)
        .filter(item => item.cityCode === unref(city))
        .map(item => {
          return { value: item.areaCode.substring(5), text: item.area };
        });
      townOptions.unshift({ value: null, text: '請選擇' });
      return townOptions;
    });

    function getCityOptions() {
      const set = new Set();
      cityOptions.value = unref(allAddress)
        .filter(item => (!set.has(item.city) ? set.add(item.city) : false))
        .map(item => {
          return { value: item.cityCode, text: item.city };
        });
      unref(cityOptions).unshift({ value: null, text: '請選擇' });
    }

    function getZip(value: string) {
      const addr = unref(allAddress).find(item => item.areaCode === value);
      return addr ? addr.zip : null;
    }

    function updateCity(value: string) {
      context.emit('update:city', value);
      context.emit('update:town', null);
      context.emit('update:zip', null);
    }

    function updateTown(value: string) {
      context.emit('update:town', value);
      context.emit('update:zip', getZip(unref(city).concat(value)));
    }

    return {
      innerOther,
      cityOptions,
      townOptions,
      updateCity,
      updateTown,
      formOptionsFormatter,
    };
  },
});
</script>
<style scoped></style>

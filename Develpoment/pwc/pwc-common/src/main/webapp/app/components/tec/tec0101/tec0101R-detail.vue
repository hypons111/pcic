<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-body">
          <b-form-row class="align-items-center">
            <b-col cols="1"></b-col>
            <b-col cols="4" class="text-left label"
              ><label>{{ $t('label.newMessage') }}</label></b-col
            >
          </b-form-row>
          <br />
          <b-form-row class="align-items-center">
            <b-col cols="1"></b-col>
            <b-col cols="1.5" class="text-right label"
              ><label>{{ $t('label.newMessage') + ': ' }}</label></b-col
            >
            <b-col cols="6" class="text-left label"
              ><label>{{ $v.pageTitle.$model }}</label></b-col
            >
          </b-form-row>
          <br />
          <b-form-row class="align-items-center">
            <b-col cols="1"></b-col>
            <b-col cols="1.5" class="text-right label"
              ><label>{{ $t('label.page_start') + ': ' }}</label></b-col
            >
            <b-col cols="4" class="text-left label"
              ><label>{{ formatDate($v.pageStart.$model, '/') }}</label></b-col
            >
            <b-col cols="1"></b-col>
            <b-col cols="5.5" class="text-right label"
              ><label>{{ $t('label.content_type') + ': ' }}</label> <label>{{ $v.contentType.$model }}</label></b-col
            >
          </b-form-row>
          <br />
          <b-form-row class="align-items-center">
            <b-col cols="1"></b-col>
            <b-col cols="11" class="text-left label"
              ><label>{{ $v.content.$model }}</label></b-col
            >
          </b-form-row>
          <b-form-row class="align-items-center">
            <b-col cols="5"></b-col>
            <i-button type="arrow-counterclockwise" @click="toQueryView"></i-button>
          </b-form-row>
        </div>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, ref, toRefs, watch } from '@vue/composition-api';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import { navigateByNameAndParams } from '@/router/router';
import { useStore } from '@u3u/vue-hooks';
import { Tec_page } from '@/shared/model/tec_page.model';

export default {
  name: 'tec0101RDetail',
  props: {
    tec_page: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const tec_page = toRefs(props).tec_page;

    //預設值
    let formDefault = {
      pageStart: '',
      pageTitle: '',
      contentType: '',
      content: '',
    };
    //響應式物件form，把預設值丟進去
    let form = reactive(Object.assign({}, formDefault));
    //表單物件驗證規則
    const rules = {
      pageStart: {},
      pageTitle: {},
      contentType: {},
      content: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const createDefaultValue = (data: Tec_page) => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
    };

    // 是否需要重新查詢
    const isReload = ref(false);

    // 返回查詢畫面
    const toQueryView = () => {
      const fromName = useStore().value.getters.routeData.fromName;
      navigateByNameAndParams(fromName, { isReload: isReload.value });
    };

    watch(
      tec_page,
      () => {
        if (tec_page.value) {
          createDefaultValue(tec_page.value);
        }
      },
      { immediate: true }
    );

    return {
      $v,
      toQueryView,
      reset,
      validateState,
      formatDate,
    };
  },
};
</script>

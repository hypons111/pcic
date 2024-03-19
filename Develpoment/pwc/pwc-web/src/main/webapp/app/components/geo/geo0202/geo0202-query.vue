<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-11">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <!-- 希望工作地點 -->
              <i-form-group-check
                :label="$t('label.workplaces') + '：'"
                class="col-12"
                label-cols="2"
                content-cols="7"
                :item="$v.workPlaceListShow"
              >
                <div v-for="option in cityOptions" :key="option.value" class="col-4 d-inline-block">
                  <b-form-checkbox v-model="$v.workPlaceListShow.$model" :value="option.value" :state="validateState($v.workPlaceListShow)"
                    >{{ option.text }}
                  </b-form-checkbox>
                </div>
              </i-form-group-check>
              <div>
                <!-- {{ cityOptions }} -->
                <!-- {{ $v.workPlaceList.$model }} -->
              </div>
            </b-form-row>
            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="clickQueryBtn"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>
    <section class="container mt-3" v-if="queryStatus">
      <i-table
        class="table-sm"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged($event)"
        ref="iTableComponent"
      >
        <template #cell(subjectText)="row"> {{ row.item.companyName }}徵求{{ row.item.jobTitle }}{{ row.item.personCount }}名 </template>
        <template #cell(action)="row">
          <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY)"></i-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, Ref, ref, toRefs, toRef, watch, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useBvModal } from '@/shared/modal';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { required, maxLength } from '@/shared/validators';
import { GeoJob } from '@/shared/model/geoModel/geo-job.model';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { FormStatusEnum } from '@/shared/enum';
import DualFormInvalidFeedback from '@/shared/form/dual-form-invalid-feedback.vue';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { useStore } from '@u3u/vue-hooks';

export default {
  name: 'geo0202Query',
  components: {},
  props: {
    isReload: {
      required: false,
    },
    // admAccount: {
    //   type: Object,
    //   required: false,
    // },
  },
  setup(props, context) {
    // const admAccountProp = toRefs(props).admAccount;

    onActivated(() => {
      if (isReloadProp.value || isReload.value) {
        clickQueryBtn();
      }
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    //從使用者撈資料
    const store = context.root.$store;
    const account = {
      userId: store.getters.account.id, //帳號USER_ID
      userType: store.getters.account.userType,
      compIdno: store.getters.account.compIdno,
      idn: store.getters.account.idn,
      role: null,
    };
    account.role = account.idn && !account.compIdno ? 1 : account.compIdno && !account.idn ? 2 : null;

    // 是否需要重新查詢
    const isReload = ref(false);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      workPlaceList: null,
      workPlaceListShow: [],
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      workPlaceList: {},
      workPlaceListShow: { maxLength: maxLength(3, '希望工作地點最多不得超過三個') },
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();
    const admSysCodeService = new AdmSysCodeService();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      result: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'GEO');
      formOptions.publishStatus = [
        { value: '0', text: '處理中' },
        { value: '1', text: '刊登中' },
        { value: '2', text: '超過刊登時間' },
        { value: '3', text: '未核准' },
      ];
    }
    function getCityOptions() {
      const set = new Set();
      cityOptions.value = useStore()
        .value.getters.admPostAddr.slice()
        .filter(item => (!set.has(item.city) ? set.add(item.city) : false))
        .map(item => {
          return { value: item.cityCode, text: item.city };
        });
    }
    const cityOptions = ref(undefined);
    const formOptions = reactive({
      publishStatus: [],
      jobType: [],
    });

    const table = reactive({
      fields: [
        // {
        //   key: 'id',
        //   label: '序號',
        //   sortable: true,
        //   thClass: 'text-center',
        //   tdClass: 'text-center align-middle',
        // },
        {
          key: 'subjectText',
          label: i18n.t('label.subjectText'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'publishDateStart',
          label: i18n.t('label.publishDateStart'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
          // thStyle: { width: '10%' },
        },
        {
          key: 'jobTitle',
          label: i18n.t('label.jobTitle'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '20%' },
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          proceedFrontEndDtatToBackEndDTO(form);
          // form.workPlaceList = form.workPlaceList.join(',');
          form.workPlaceList = form.workPlaceListShow.join(',');
          dataPromise.value = axios
            .post('/geo-jobs/workPlace', form) //前端分頁(前後端選一)
            // .post('/geo-jobs/criteria-jpa', form) //後端分頁
            .then(({ data }) => {
              // table.data = data.slice(0, data.length); //前端分頁
              // table.totalItems = data.length; //前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const toEditView = (geoJob: GeoJob, formStatus: FormStatusEnum) => {
      navigateByNameAndParams('geo0202Read', { geoJob: geoJob, formStatus: formStatus });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const iTableComponent = ref(null); // 綁定 i-table 元件用

    // 點擊查詢按鈕時先將 i-table 元件內的 currentPage(當前頁面) 回到 1，再執行查詢函式。
    const clickQueryBtn = () => {
      if (iTableComponent.value) iTableComponent.value.state.pagination.currentPage = 1;
      handleQuery();
    };

    //修正idatepicker前後端時間差+8小時
    const proceedFrontEndDtatToBackEndDTO = form => {
      //處理日期的offset
      const value = new Date();
      const offset = value.getTimezoneOffset();
      form.createTimeStart = form.createTimeStart ? new Date(form.createTimeStart.getTime() - offset * 60 * 1000) : null;
      form.createTimeEnd = form.createTimeEnd ? new Date(form.createTimeEnd.getTime() - offset * 60 * 1000) : null;
    };

    onMounted(() => {
      getAllSysCodes();
      getCityOptions();
      formDefault.workPlaceList = formDefault.workPlaceList ? formDefault.workPlaceList.split(',') : [];
      reset();
    });

    return {
      $v,
      stepVisible,
      formOptions,
      queryStatus,
      dataPromise,
      table,
      reset,
      validateState,
      handleQuery,
      ref,
      FormStatusEnum,
      toEditView,
      handlePaginationChanged,
      clickQueryBtn,
      account,
      form,
      GeoJob,
      cityOptions,
    };
  },
};
</script>
<style scoped></style>

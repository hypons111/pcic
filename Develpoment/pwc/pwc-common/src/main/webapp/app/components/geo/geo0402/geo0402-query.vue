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
            <!--問題類別 -->
            <b-form-row>
              <i-form-group-check
                labelStar
                class="col-6"
                label-cols="4"
                content-cols="8"
                :label="$t('label.questionType') + '：'"
                :item="$v.questionType"
              >
                <b-form-select
                  class="col-12"
                  v-model="$v.questionType.$model"
                  :options="formOptions.questionType"
                  :state="validateState($v.questionType)"
                >
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <!-- 提問時間 -->
              <i-form-group-check class="col-6" label-cols="4" content-cols="8" :label="$t('label.questionTime') + '：'" :item="$v.jobType">
                <i-dual-date-picker :dual1.sync="$v.createTimeStart.$model" :dual2.sync="$v.createTimeEnd.$model"></i-dual-date-picker>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 回答狀態 -->
              <i-form-group-check class="col-6" label-cols="4" content-cols="8" :label="$t('label.replyStatus') + '：'" :item="$v.status">
                <b-form-select class="col-12" v-model="$v.status.$model" :options="formOptions.status">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <!-- 提問人姓名 -->
              <i-form-group-check
                class="col-6"
                label-cols="4"
                content-cols="8"
                :label="$t('label.questionPerson') + '：'"
                :item="$v.questionPerson"
              >
                <b-form-input class="col-12" v-model="$v.questionPerson.$model"> </b-form-input>
              </i-form-group-check>
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
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged($event)"
        ref="iTableComponent"
      >
        <template #cell(action)="row">
          <b-button size="sm" type="" @click="toEditView(row.item)">回答</b-button>
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
import { required } from '@/shared/validators';
import { GeoQuestion } from '@/shared/model/geoModel/geo-question.model';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { FormStatusEnum } from '@/shared/enum';
import DualFormInvalidFeedback from '@/shared/form/dual-form-invalid-feedback.vue';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { useStore } from '@u3u/vue-hooks';

export default {
  name: 'geo0402Query',
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
      questionType: '',
      createTimeStart: null,
      createTimeEnd: null,
      status: '',
      questionPerson: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      questionType: { required },
      createTimeStart: {},
      createTimeEnd: {},
      status: {},
      questionPerson: {},
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
      formOptions.questionType = [
        { value: '1', text: '會員問題' },
        { value: '2', text: '機構問題' },
      ];
      formOptions.status = [
        { value: '1', text: '已回答' },
        { value: '0', text: '已送達管理者' },
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
      jobType: [],
      salaryRange: [],
      questionType: [],
      status: [],
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
          key: 'question',
          label: i18n.t('label.question'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'reply',
          label: i18n.t('label.reply'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'status',
          label: i18n.t('label.status'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.status, value),
          thStyle: { width: '10%' },
        },
        {
          key: 'createTime',
          label: i18n.t('label.questionTime'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
          thStyle: { width: '10%' },
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '8%' },
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
          dataPromise.value = axios
            .post('/geo-questions/criteria', form) //前端分頁(前後端選一)
            // .post('/geo-questions/criteria-jpa', form) //後端分頁
            .then(({ data }) => {
              // table.data = data.slice(0, data.length); //前端分頁
              // table.totalItems = data.length; //前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
              console.log(data);
              // createDefaultValue(data);
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const createDefaultValue = data => {
      Object.assign(formDefault, data);
      // Object.assign(form, formDefault);
      // reset();
    };

    const toEditView = (geoQuestion: GeoQuestion) => {
      context.emit('changeComponent', 'geo0402Edit', geoQuestion);
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

    onMounted(() => {
      getAllSysCodes();
      getCityOptions();
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
      cityOptions,
    };
  },
};
</script>
<style scoped></style>

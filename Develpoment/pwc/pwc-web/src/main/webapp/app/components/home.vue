<template>
  <div>
    <nav class="nav pt-4 navbar-expand">
      <div class="nav nav-tabs container-fluid px-2" id="nav-tab" role="tablist">
        <b-button
          class="nav-link active"
          id="nav-infrom-tab"
          data-toggle="tab"
          data-target="#nav-infrom"
          type="button"
          role="tab"
          aria-controls="nav-infrom"
          aria-selected="false"
          @click="reset"
        >
          {{ $t('button.index-information') }}</b-button
        >
        <b-button
          class="nav-link"
          id="nav-download-tab"
          data-toggle="tab"
          data-target="#nav-download"
          type="button"
          role="tab"
          aria-controls="nav-download"
          aria-selected="false"
          @click="reset"
          >{{ $t('button.index-doenload') }}</b-button
        >
        <b-button
          class="nav-link"
          id="nav-job-tab"
          data-toggle="tab"
          data-target="#nav-job"
          type="button"
          role="tab"
          aria-controls="nav-job"
          aria-selected="false"
          @click="reset"
          >{{ $t('button.index-job') }}</b-button
        >
      </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
      <div class="tab-pane fade show active" id="nav-infrom" role="tabpanel" aria-labelledby="nav-infrom-tab">
        <section class="container pt-2">
          <div class="card-header py-1 px-1">
            <b-form-row>
              <!--公告類別-->
              <b-form-group class="col-sm-3 mb-0" label-cols-md="4" content-cols-md="6" :label="$t('label.kind')" label-align-md="right">
                <b-input-group>
                  <b-form-select v-model="$v.kind.$model" :options="queryOptions.kind"></b-form-select>
                </b-input-group>
              </b-form-group>

              <!--公告日期-->
              <b-form-group
                class="col-sm-4 mb-0"
                label-cols-md="4"
                content-cols-md="8"
                :label="$t('label.releaseDate')"
                label-align-md="right"
              >
                <b-input-group>
                  <i-date-picker v-model="$v.startDate.$model" :disabled-date="startDateRule" :state="$v.startDate"></i-date-picker>
                  <i-date-picker v-model="$v.endDate.$model" :state="$v.endDate" :disabled-date="endDateRule"></i-date-picker>
                </b-input-group>
              </b-form-group>

              <!--關鍵字-->
              <b-form-group
                class="col-sm-3 mb-2 mb-sm-0"
                label-cols-md="3"
                content-cols-md="8"
                :label="$t('label.textKeyWord')"
                label-align-md="right"
              >
                <b-input-group>
                  <b-form-input v-model.trim="$v.subject.$model" placeholder="請輸入關鍵字"></b-form-input>
                </b-input-group>
              </b-form-group>
              <b-button-toolbar class="mx-auto">
                <i-button class="mr-2" type="search" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </b-form-row>
          </div>
        </section>
        <section class="container pt-2">
          <div class="card">
            <div class="card-body">
              <b-collapse id="collapse-5" class="px-1 py-1" v-model="stepVisible.result">
                <i-table
                  stacked="sm"
                  striped
                  class="table-sm table-hover"
                  :itemsUndefinedBehavior="'loading'"
                  :items="table.data"
                  :fields="table.fields"
                  :totalItems="table.totalItems"
                  :is-server-side-paging="true"
                  @changePagination="handlePaginationChanged($event)"
                >
                  <template #cell(subject)="row">
                    <a class="href-style" href="javascript: void(0)" @click="toInfoView(row.item)">{{ row.item.subject }}</a>
                  </template>
                </i-table>
              </b-collapse>
            </div>
          </div>
        </section>
      </div>
      <div class="tab-pane fade" id="nav-download" role="tabpanel" aria-labelledby="nav-download-tab">
        <section class="container pt-2">
          <div class="card">
            <div class="card-header py-1 px-1">
              <b-form-row>
                <!--公告類別-->
                <b-form-group class="col-sm-3 mb-0" label-cols-md="4" content-cols-md="6" :label="$t('label.kind')" label-align-md="right">
                  <b-input-group>
                    <b-form-select v-model="$v.kind.$model" :options="queryOptions.kind"></b-form-select>
                  </b-input-group>
                </b-form-group>
                <!--公告日期-->
                <b-form-group
                  class="col-sm-4 mb-0"
                  label-cols-md="4"
                  content-cols-md="8"
                  :label="$t('label.releaseDate')"
                  label-align-md="right"
                >
                  <b-input-group>
                    <i-date-picker v-model="$v.startDate.$model" :state="$v.startDate"></i-date-picker>
                    <i-date-picker v-model="$v.endDate.$model" :state="$v.endDate"></i-date-picker>
                  </b-input-group>
                  <dual-form-invalid-feedback
                    :dual-invalid-feedback="[$v.startDate.$errors, $v.endDate.$errors]"
                  ></dual-form-invalid-feedback>
                </b-form-group>
                <!--關鍵字-->
                <b-form-group
                  class="col-sm-3 mb-0"
                  label-cols-md="3"
                  content-cols-md="8"
                  :label="$t('label.textKeyWord')"
                  label-align-md="right"
                >
                  <b-form-input v-model.trim="$v.subject.$model" placeholder="請輸入關鍵字"></b-form-input>
                </b-form-group>
                <b-button-toolbar class="mx-auto">
                  <i-button class="mr-2" type="search" @click="handleDownQuery"></i-button>
                  <i-button type="x-circle" @click="reset"></i-button>
                </b-button-toolbar>
              </b-form-row>
            </div>
          </div>
        </section>
        <section class="container pt-2">
          <div class="card">
            <div class="card-body">
              <b-collapse id="collapse-5" class="px-1 py-1" v-model="stepVisible.result">
                <i-table
                  stacked="sm"
                  striped
                  class="table-sm table-hover"
                  :itemsUndefinedBehavior="'loading'"
                  :items="tableD.data"
                  :fields="tableD.fields"
                  :totalItems="tableD.totalItems"
                  :is-server-side-paging="true"
                  @changePagination="handlePaginationChanged1($event)"
                >
                  <template #cell(subject)="row">
                    <a class="href-style" href="javascript: void(0)" @click="toDownView(row.item)">{{ row.item.subject }}</a>
                  </template>
                </i-table>
              </b-collapse>
            </div>
          </div>
        </section>
      </div>
      <div class="tab-pane fade" id="nav-job" role="tabpanel" aria-labelledby="nav-job-tab">
        <section class="container pt-2">
          <div class="card">
            <div class="card-header py-1 px-1">
              <b-form-row>
                <!-- 希望工作地點 -->
                <b-form-group
                  class="col-sm-12 mb-0"
                  label-cols-md="2"
                  content-cols-md="10"
                  :label="$t('label.workplaces')"
                  label-align-md="center"
                >
                  <div v-for="option in cityOptions" :key="option.value" class="col-4 d-inline-block">
                    <b-form-checkbox v-model="$v.workPlaceListShow.$model" :value="option.value">{{ option.text }} </b-form-checkbox>
                  </div>
                </b-form-group>
                <b-form-group class="col-sm-7 mb-0"> </b-form-group>
                <b-button-toolbar class="mx-auto">
                  <i-button class="mr-2" type="search" @click="handleDownQuery"></i-button>
                  <i-button type="x-circle" @click="reset"></i-button>
                </b-button-toolbar>
              </b-form-row>
            </div>
          </div>
        </section>
        <section class="container mt-3">
          <div class="card">
            <div class="card-body">
              <i-table
                stacked="sm"
                striped
                class="table-sm table-hover"
                :itemsUndefinedBehavior="'loading'"
                :items="tableJ.data"
                :fields="tableJ.fields"
                :is-server-side-paging="true"
                :totalItems="tableJ.totalItems"
                @changePagination="handlePaginationChanged2($event)"
              >
                <template #cell(subjectText)="row">
                  {{ row.item.companyName }}徵求{{ row.item.jobTitle }}{{ row.item.personCount }}名
                </template>
              </i-table>
            </div>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { navigateByNameAndParams } from '@/router/router';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { formatDate, formatDateString, formatToString } from '@/shared/date/minguo-calendar-utils';
import { useValidation, validateState } from '@/shared/form';
import DualFormInvalidFeedback from '@/shared/form/dual-form-invalid-feedback.vue';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import IDatePicker from '@/shared/i-date-picker/i-date-picker.vue';
import i18n from '@/shared/i18n';
import { Pagination } from '@/shared/model/pagination.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { maxLength } from '@/shared/validators';
import { useStore } from '@u3u/vue-hooks';
import { onMounted, reactive, ref, toRefs } from '@vue/composition-api';
import axios from 'axios';
import {
  BCollapse,
  BFormCheckbox,
  BButtonToolbar,
  BFormRow,
  BButton, BFormGroup, BFormInput, BFormSelect, BInputGroup
} from 'bootstrap-vue';
import iButton from '@/shared/buttons/i-button.vue';
import iTable from '@/shared/i-table/i-table.vue';

export default {
  name: 'Home',
  components: { 'dual-form-invalid-feedback': DualFormInvalidFeedback, 
  IDatePicker,
  iTable,
  iButton,
  BCollapse,
  BFormCheckbox,
  BButtonToolbar,
    BButton, 
    BFormInput, 
    BInputGroup,
    BFormGroup, 
    BFormSelect,
    BFormRow
   },
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
    homeDate: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const { homeDate } = toRefs(props);

    //onMounted組件第一次載入時調用
    onMounted(() => {
      getDataFromAdmSysCode();
      getAdmUnit();
      if (homeDate.value !== undefined) {
        Object.assign(form, homeDate.value);
        handleQuery();
      } else {
        handleQuery();
      }
      handleDownQuery();
      getAllSysCodes();
      getCityOptions();
      handleJobQuery();
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      subject: '', //內文搜尋
      kind: '', // 狀態
      startDate: null,
      endDate: null,
      workPlaceList: null,
      workPlaceListShow: [],
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      subject: {},
      kind: {},
      startDate: {},
      endDate: {},
      workPlaceList: {},
      workPlaceListShow: { maxLength: maxLength(3, '希望工作地點最多不得超過三個') },
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      result: true,
    });

    // 下拉選單選項
    const queryOptions = reactive({
      kind: [],
      releaseUnit: [],
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    //查詢後顯示的內容
    const table = reactive({
      fields: [
        {
          key: 'releaseDate',
          label: i18n.t('label.releaseDate'), //公告日期
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => formatDate(new Date(value), '/'),
        },
        {
          key: 'kind',
          label: i18n.t('label.kindType'), //類別
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(queryOptions.kind, value),
        },
        {
          key: 'subject',
          label: i18n.t('label.subjectText'), //內文
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'releaseUnit',
          label: i18n.t('label.releaseUnit'), //公告單位
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(queryOptions.releaseUnit, value),
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          if (form.startDate === null) {
            form.releaseDate = null;
            form.downDate = null;
          } else {
            form.releaseDate = formatDateString(form.startDate); //startDate轉字串 to releaseDate
            form.downDate = formatDateString(form.endDate);
          }
          dataPromise.value = axios
            .post('/service/pwc-information/criteria', form) //前端分頁(呼叫後端)
            .then(({ data }) => {
              // table.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
              // table.totalItems = data.length; //前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    //Down查詢後顯示的內容
    const tableD = reactive({
      fields: [
        {
          key: 'releaseDate',
          label: i18n.t('label.releaseDate'), //公告日期
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => formatDate(new Date(value), '/'),
        },
        {
          key: 'kind',
          label: i18n.t('label.kindType'), //類別
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(queryOptions.kind, value),
        },
        {
          key: 'subject',
          label: i18n.t('label.subjectText'), //內文
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'releaseUnit',
          label: i18n.t('label.releaseUnit'), //公告單位
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(queryOptions.releaseUnit, value),
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const handleDownQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          tableD.data = undefined;
          queryStatus.value = true;
          if (form.startDate === null) {
            form.releaseDate = null;
            form.downDate = null;
          } else {
            form.releaseDate = formatDateString(form.startDate); //startDate轉字串 to releaseDate
            form.downDate = formatDateString(form.endDate);
          }
          dataPromise.value = axios
            .post('/service/pwc-download/criteria', form) //前端分頁(呼叫後端)
            .then(({ data }) => {
              // tableD.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
              // tableD.totalItems = data.length; //前端分頁
              tableD.data = data.content.slice(0, data.content.length); //後端分頁
              tableD.totalItems = data.totalElements; //後端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const handlePaginationChanged1 = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleDownQuery();
    };
    const handlePaginationChanged2 = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleJobQuery();
    };

    //公告類別下拉選單
    const getDataFromAdmSysCode = () => {
      const admUrl = '/service/sys-codes/criteria-jpa';
      let admSysSalaryRangeCriteria = {
        moduleType: 'PWC',
        dataType: 'KIND',
      };
      axios.post(admUrl, admSysSalaryRangeCriteria).then(({ data }) => {
        if (data != null && Array.isArray(data.content)) {
          queryOptions.kind.splice(0, queryOptions.kind.length);
          data.content.forEach(item => {
            let selectOption = {
              value: '',
              text: '',
            };
            selectOption.value = item.value;
            selectOption.text = item.codeDescript;

            queryOptions.kind.push(selectOption);
          });
        }
      });
    };

    //取得發布單位 AdmUnit
    const getAdmUnit = () => {
      const admUrl = '/service/adm-units/get-unit-opts';
      axios.get(admUrl).then(({ data }) => {
        if (data != null && Array.isArray(data)) {
          queryOptions.releaseUnit.splice(0, queryOptions.releaseUnit.length);
          data.forEach(item => {
            let selectOption = {
              value: '',
              text: '',
            };
            selectOption.value = item.unitId;
            selectOption.text = item.unitName;
            queryOptions.releaseUnit.push(selectOption);
          });
        }
      });
    };

    const toInfoView = (pwcInformation: PwcInformation) => {
      navigateByNameAndParams('home-information', { pwcInformation: pwcInformation, homeDate: form, isNotKeepAlive: false });
    };

    const toDownView = (pwcDownload: PwcDownload) => {
      navigateByNameAndParams('home-download', { pwcDownload: pwcDownload, isNotKeepAlive: false });
    };

    function startDateRule(date: Date) {
      return date > new Date(form.endDate);
    }

    function endDateRule(date: Date) {
      return date < new Date(form.startDate);
    }
    // -----------------------------------求才資訊----------------------------------------------
    // const iTableComponent = ref(null); // 綁定 i-table 元件用
    const tableJ = reactive({
      fields: [
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
        },
        {
          key: 'jobTitle',
          label: i18n.t('label.jobTitle'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });
    const cityOptions = ref(undefined);
    const admSysCodeService = new AdmSysCodeService();

    const formOptions = reactive({
      publishStatus: [],
      jobType: [],
    });
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

    const handleJobQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          tableJ.data = undefined;
          queryStatus.value = true;
          form.workPlaceList = form.workPlaceListShow.join(',');
          dataPromise.value = axios
            .post('service/geo-jobs/publicJobs', form) //後端分頁(前後端選一)
            .then(({ data }) => {
              tableJ.data = data.content.slice(0, data.content.length); //後端分頁
              tableJ.totalItems = data.totalElements; //後端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    return {
      $v,
      stepVisible,
      queryOptions,
      queryStatus,
      dataPromise,
      table,
      tableD,
      tableJ,
      reset,
      validateState,
      handleQuery,
      handleDownQuery,
      handlePaginationChanged,
      handlePaginationChanged1,
      handlePaginationChanged2,
      toInfoView,
      toDownView,
      startDateRule,
      endDateRule,
      cityOptions,
      handleJobQuery,
    };
  },
};
</script>

<style scoped>
.nav-tabs .nav-link {
  width: 50%;
  font-size: 19px;
  line-height: 28px;
  opacity: 0.45;
  color: #000;
  background: #fff;
  border: 0;
  padding: 0.6rem;
  letter-spacing: 0.125rem;
}
.btn-secondary:not(:disabled):not(.disabled).active {
  background: #1aa4b7;
}
</style>

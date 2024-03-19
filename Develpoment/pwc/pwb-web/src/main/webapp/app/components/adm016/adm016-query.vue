<template>
  <div>
    <section class="container mt-4">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                後臺功能操作紀錄查詢
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-10">
          <b-collapse id="collapse-4" style="color: black" v-model="stepVisible">
            <div class="ml-5">
              <b-form-row>
                <i-form-group-check :label="$t('label.adm016.functionId')" :item="$v.functionId">
                  <b-form-input v-model.trim="$v.functionId.$model"></b-form-input>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm016.functionName')" :item="$v.functionName">
                  <b-form-input v-model.trim="$v.functionName.$model"></b-form-input>
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <i-form-group-check :label="$t('label.adm016.functionExtendId')">
                  <b-form-select :options="queryOptions.functionExtendOpts" v-model.trim="$v.functionExtendId.$model"></b-form-select>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm016.sysCategory')">
                  <b-form-select :options="queryOptions.sysCategoryOpts" v-model.trim="$v.sysCategory.$model"></b-form-select>
                </i-form-group-check>
              </b-form-row>

              <b-form-group
                :label="$t('label.adm016.dateRange')"
                :class="{ 'form-check-with-marginB': true, 'col-6': false }"
                :label-cols="2"
                :content-cols="10"
                :label-align="'right'"
                label-class="label"
              >
                <b-input-group prepend="介於">
                  <i-date-picker type="date" v-model="$v.dateStart.$model" placeholder="開始日期" />
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker type="date" v-model="$v.dateEnd.$model" placeholder="結束日期" />
                </b-input-group>
              </b-form-group>

              <b-button-toolbar class="float-right">
                <i-button class="mr-1" type="bar-chart-line" @click="statistics()"></i-button>
                <i-button class="mr-1" type="search" @click="query()"></i-button>
                <i-button class="mr-1" type="x-circle" @click="reset()"></i-button>
              </b-button-toolbar>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-4" v-if="queryStatus">
      <div class="card">
        <div class="card-body">
          <i-table
            class="table-sm"
            itemsUndefinedBehavior="loading"
            :items="table.data"
            :fields="queryMode === 'query' ? table.queryFields : table.statisticsFields"
            :totalItems="table.totalItems"
            :isServerSidePaging="true"
            @changePagination="queryMode === 'query' ? handlePaginationChanged($event) : handlePaginationChangedStatistics($event)"
          >
            <template #cell(action)="data">
              <i-button v-if="queryMode === 'query'" class="mb-1" size="sm" type="binoculars" @click="toEditView(data.item.id)"></i-button>
              <!--跳窗查看功能統計下的延伸功能使用次數-->
              <i-button
                v-if="queryMode === 'statistics'"
                class="mb-1"
                size="sm"
                type="binoculars"
                @click="toStatisticsView(data.item.functionId)"
              ></i-button>
            </template>
          </i-table>
        </div>
      </div>
    </section>

    <b-modal
      id="update-modal"
      class="border-info"
      size="xl"
      v-model="showModal"
      title="延伸功能操作次數"
      headerBgVariant="info"
      headerTextVariant="light"
      titleTag="h4"
    >
      <div class="ml-2">
        <i-table
          class="table-sm"
          itemsUndefinedBehavior="loading"
          :items="table.statisticsInfoData"
          :fields="table.statisticsInfoFields"
          :totalItems="table.totalItems"
          :isServerSidePaging="false"
        >
          <template #cell(rowNum)="data">
            {{ data.index + 1 }}
          </template>
        </i-table>
      </div>
      <template #modal-footer>
        <div>
          <i-button class="mr-1" type="arrow-left" @click="showModal = false"></i-button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onMounted, reactive, ref, watch } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { dateTimeFormatter } from '@/shared/date/minguo-calendar-utils';
import { sysCategoryFormatter } from '@/shared/formatter/common';

export default {
  name: 'Adm016Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props, context) {
    const notificationService: NotificationService = useNotification();
    const showModal = ref(false);
    // 查詢結果及狀態
    const stepVisible = ref(true);
    const queryStatus = ref(false);
    const queryMode = ref();

    const allFunctionExtendOpts = ref([]);

    onMounted(() => {
      getAllFunctionExtendOpts();
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const adm016FormDefaults = {
      functionId: '',
      functionName: '',
      functionExtendId: '',
      sysCategory: '',
      dateStart: null,
      dateEnd: null,
    };

    // 建立表單物件 ref
    const adm016Form = reactive(Object.assign({}, adm016FormDefaults));

    // 表單物件驗證規則
    const rules = {
      functionId: {},
      functionName: {},
      functionExtendId: {},
      sysCategory: {},
      dateStart: {},
      dateEnd: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, adm016Form, adm016FormDefaults);

    const queryOptions = reactive({
      sysCategoryOpts: [
        { value: '', text: '請選擇' },
        { value: 'SYS001', text: '工程雲服務網' },
        { value: 'SYS002', text: '工程雲後臺管理' },
        { value: 'SYS003', text: '全球資訊網' },
        { value: 'SYS004', text: '全球資訊網後臺管理' },
      ],
      // 延伸功能選項
      functionExtendOpts: [],
    });

    const table = reactive({
      queryFields: [
        {
          key: 'rowNum',
          label: '序號',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: { width: '5%' },
        },
        {
          key: 'sysCategory',
          label: '系統類別',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
          formatter: (value: any) => sysCategoryFormatter(value),
        },
        {
          key: 'jwtToken',
          label: 'Token連線序號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '12%' },
        },
        {
          key: 'functionId',
          label: '功能代號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'functionName',
          label: '功能名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'functionExtendName',
          label: '延伸功能',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'createUserSys',
          label: '操作人員或系統帳號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'createTime',
          label: '建檔時間',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '12%' },
          formatter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
        },
        {
          key: 'action',
          sortable: false,
          label: '動作',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '6%' },
        },
      ],

      statisticsFields: [
        {
          key: 'rowNum',
          label: '序號',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: { width: '5%' },
        },
        {
          key: 'sysCategory',
          label: '系統類別',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'functionId',
          label: '功能代號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'functionName',
          label: '功能名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'useCount',
          label: '使用次數',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'action',
          sortable: false,
          label: '動作',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '6%' },
        },
      ],

      statisticsInfoFields: [
        {
          key: 'rowNum',
          label: '序號',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: { width: '5%' },
        },
        {
          key: 'functionExtendId',
          label: '延伸功能代號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'functionExtendName',
          label: '延伸功能名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'useCount',
          label: '使用次數',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
      ],
      statisticsInfoData: undefined,
      data: undefined,
      totalItems: 0,
    });

    function getAllFunctionExtendOpts() {
      axios
        .get('adm-function-extend/get-function-extend-opts')
        .then(({ data }) => {
          allFunctionExtendOpts.value.splice(0, allFunctionExtendOpts.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 依表單條件查詢帳號
    function query() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (adm016Form.dateStart !== null) {
            adm016Form.dateStart = new Date(adm016Form.dateStart);
          }
          if (adm016Form.dateEnd !== null) {
            adm016Form.dateEnd = new Date(adm016Form.dateEnd);
          }
          queryMode.value = 'query';
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/adm-function-used-records/criteria-jpa', adm016Form) //後端分頁
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
            })
            .finally(() => {})
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    function statistics() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (adm016Form.dateStart !== null) {
            adm016Form.dateStart = new Date(adm016Form.dateStart);
          }
          if (adm016Form.dateEnd !== null) {
            adm016Form.dateEnd = new Date(adm016Form.dateEnd);
          }
          queryMode.value = 'statistics';
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/adm-function-used-records/statistics', adm016Form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .finally(() => {})
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(adm016Form, pagination);
      query();
    };

    const handlePaginationChangedStatistics = (pagination: Pagination) => {
      Object.assign(adm016Form, pagination);
      statistics();
    };

    // 依點選項目之帳號 轉至檢視使用者畫面
    function toEditView(id: number) {
      navigateByNameAndParams('adm016-edit-info', { id: id });
    }

    function toStatisticsView(functionId: string) {
      if (adm016Form.dateStart !== null) {
        adm016Form.dateStart = new Date(adm016Form.dateStart);
      }
      if (adm016Form.dateEnd !== null) {
        adm016Form.dateEnd = new Date(adm016Form.dateEnd);
      }
      axios
        .post(`/adm-function-used-records/statistics/info/${functionId}`, adm016Form)
        .then(({ data }) => {
          console.log(data);
          table.statisticsInfoData = []; //前端分頁
          table.statisticsInfoData.splice(0, table.statisticsInfoData.length, ...data); //前端分頁
          table.totalItems = data.length; //前端分頁
          showModal.value = true;
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    const isReloadProp = computed(() => {
      if (props.isReload) {
        query();
      }
      return props.isReload;
    });

    watch(isReloadProp, () => {});

    watch(allFunctionExtendOpts, () => {
      queryOptions.functionExtendOpts = allFunctionExtendOpts.value.map(data => {
        return { text: data['functionExtendName'] + '-' + data['functionExtendId'], value: data['functionExtendId'] };
      });
      queryOptions.functionExtendOpts.unshift({ text: '請選擇', value: '' });
    });

    return {
      $v,
      checkValidity,
      stepVisible,
      queryStatus,
      table,
      queryOptions,
      queryMode,
      reset,
      validateState,
      toEditView,
      toStatisticsView,
      query,
      statistics,
      handlePaginationChanged,
      handlePaginationChangedStatistics,
      showModal,
    };
  },
};
</script>

<template>
  <div>
    <section class="container mt-4">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                批次紀錄查詢
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
                <i-form-group-check :label="$t('label.adm010.batchServiceNo')" :item="$v.batchServiceNo">
                  <b-form-input v-model.trim="$v.batchServiceNo.$model"></b-form-input>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm010.batchServiceName')" :item="$v.batchServiceName">
                  <b-form-input v-model.trim="$v.batchServiceName.$model"></b-form-input>
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <i-form-group-check :label="$t('label.adm010.batchServiceCategory')" :item="$v.batchServiceCategory">
                  <b-form-select :options="queryOptions.batchServiceCategoryOpts" v-model="$v.batchServiceCategory.$model"></b-form-select>
                </i-form-group-check>
              </b-form-row>

              <b-button-toolbar class="float-right">
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
            :fields="table.fields"
            :totalItems="table.totalItems"
            :isServerSidePaging="true"
            @changePagination="handlePaginationChanged($event)"
          >
            <template #cell(action)="data">
              <i-button class="mb-1" size="sm" type="binoculars" @click="toEditView(data.item)"></i-button>
            </template>
          </i-table>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, reactive, ref, watch } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { executeFormatter } from '@/shared/formatter/common';
import { dateTimeFormatter } from '@/shared/date/minguo-calendar-utils';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { AdmBatchRecord } from '@/shared/model/adm-batch-record.model';

export default {
  name: 'Adm010Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props, context) {
    const notificationService: NotificationService = useNotification();
    // 查詢結果及狀態
    const stepVisible = ref(true);
    const queryStatus = ref(false);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const adm010FormDefaults = {
      batchServiceNo: '', //批次服務編號
      batchServiceName: '', //批次服務名稱
      batchServiceCategory: '', //批次服務類型
    };

    // 建立表單物件 ref
    const adm010Form = reactive(Object.assign({}, adm010FormDefaults));

    // 表單物件驗證規則
    const rules = {
      batchServiceNo: {},
      batchServiceName: {},
      batchServiceCategory: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, adm010Form, adm010FormDefaults);

    const queryOptions = reactive({
      batchServiceCategoryOpts: [
        { value: '', text: '請選擇' },
        { value: 'bsc01', text: '批次服務類型1' },
        { value: 'bsc02', text: '批次服務類型2' },
        { value: 'bsc03', text: '批次服務類型3' },
      ],
    });

    const table = reactive({
      fields: [
        {
          key: 'rowNum',
          label: '序號',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: { width: '5%' },
        },
        {
          key: 'id',
          label: '批次紀錄流水號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'batchServiceNo',
          label: '批次服務編號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'batchServiceName',
          label: '批次服務名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '12%' },
        },
        {
          key: 'batchServiceCategory',
          label: '批次服務類型',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'triggerTime',
          label: '啟動時間',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
          formatter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
        },
        {
          key: 'finishTime',
          label: '完成時間',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
          formatter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
        },
        {
          key: 'result',
          label: '執行結果',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '5%' },
          formatter: (value: string) => executeFormatter(value),
        },
        {
          key: 'action',
          sortable: false,
          label: '動作',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '5%' },
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    // 依表單條件查詢帳號
    function query() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/adm-batch-records/criteria-jpa', adm010Form)
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
      Object.assign(adm010Form, pagination);
      query();
    };

    // 依點選項目之帳號 轉至審核畫面
    function toEditView(admBatchRecord: AdmBatchRecord) {
      navigateByNameAndParams('adm010-edit-info', { admBatchRecord: admBatchRecord });
    }

    const isReloadProp = computed(() => {
      if (props.isReload) {
        query();
      }
      return props.isReload;
    });

    watch(isReloadProp, () => {});

    return {
      $v,
      checkValidity,
      stepVisible,
      queryStatus,
      queryOptions,
      table,
      reset,
      validateState,
      toEditView,
      query,
      handlePaginationChanged,
    };
  },
};
</script>

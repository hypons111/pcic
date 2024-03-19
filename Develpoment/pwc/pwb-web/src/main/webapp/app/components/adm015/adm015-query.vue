<template>
  <div>
    <section class="container mt-4">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                後臺登入歷程查詢
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
                <i-form-group-check :label="$t('label.adm015.userId')" :item="$v.createUserSys">
                  <i-date-picker type="date" v-model="$v.createUserSys.$model" />
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm015.chName')" :item="$v.chName">
                  <b-form-input v-model.trim="$v.chName.$model"></b-form-input>
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <i-form-group-check :label="$t('label.adm015.sysCategory')" :item="$v.sysCategory">
                  <b-form-select :options="queryOptions.sysCategoryOpts" v-model="$v.sysCategory.$model"> </b-form-select>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm015.result')" :item="$v.result">
                  <b-form-select :options="queryOptions.resultOpts" v-model="$v.result.$model"> </b-form-select>
                </i-form-group-check>
              </b-form-row>

              <b-form-group
                :label="$t('label.adm015.dateRange')"
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
              <i-button class="mb-1" size="sm" type="binoculars" @click="toEditView(data.item.id)"></i-button>
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
import { executeFormatter, sysCategoryFormatter } from '@/shared/formatter/common';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { dateTimeFormatter } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Adm015Query',
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
    const adm015FormDefaults = {
      createUserSys: '',
      chName: '',
      sysCategory: '',
      result: '',
      dateStart: null,
      dateEnd: null,
    };

    // 建立表單物件 ref
    const adm015Form = reactive(Object.assign({}, adm015FormDefaults));

    // 表單物件驗證規則
    const rules = {
      createUserSys: {},
      chName: {},
      sysCategory: {},
      result: {},
      dateStart: {},
      dateEnd: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, adm015Form, adm015FormDefaults);

    const queryOptions = reactive({
      sysCategoryOpts: [
        { value: '', text: '請選擇' },
        { value: '1', text: '工程會後台' },
        { value: '2', text: '技師資料庫' },
        { value: '3', text: '價格資料庫' },
      ],
      resultOpts: [
        { value: '', text: '請選擇' },
        { value: 'Y', text: '成功' },
        { value: 'N', text: '失敗' },
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
          key: 'sysCategory',
          label: '系統類別',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
          formatter: (value: string) => sysCategoryFormatter(value),
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
          key: 'result',
          label: '登入結果',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '5%' },
          formatter: (value: string) => executeFormatter(value),
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
          if (adm015Form.dateStart !== null) {
            adm015Form.dateStart = new Date(adm015Form.dateStart);
          }
          if (adm015Form.dateEnd !== null) {
            adm015Form.dateEnd = new Date(adm015Form.dateEnd);
          }
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/adm-login-his/criteria-jpa', adm015Form) //後端分頁
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
            })
            .finally(() => {})
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(adm015Form, pagination);
      query();
    };

    // 依點選項目之帳號 轉至檢視使用者畫面
    function toEditView(id: string) {
      navigateByNameAndParams('adm015-edit-info', { id: id });
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
      table,
      queryOptions,
      reset,
      validateState,
      toEditView,
      query,
      handlePaginationChanged,
    };
  },
};
</script>

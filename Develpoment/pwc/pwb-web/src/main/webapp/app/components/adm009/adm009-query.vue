<template>
  <div>
    <section class="container mt-4">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                批次服務查詢
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
                <i-form-group-check :label="$t('label.adm009.batchServiceNo')" :item="$v.batchServiceNo">
                  <b-form-input v-model.trim="$v.batchServiceNo.$model"></b-form-input>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm009.batchServiceName')" :item="$v.batchServiceName">
                  <b-form-input v-model.trim="$v.batchServiceName.$model"></b-form-input>
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <i-form-group-check :label="$t('label.adm009.batchServiceCategory')" :item="$v.batchServiceCategory">
                  <b-form-select :options="queryOptions.batchServiceCategoryOpts" v-model="$v.batchServiceCategory.$model"></b-form-select>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm009.isRegular')" :item="$v.isRegular">
                  <b-form-select :options="queryOptions.isRegularOpts" v-model="$v.isRegular.$model"></b-form-select>
                </i-form-group-check>
              </b-form-row>

              <b-button-toolbar class="float-right">
                <i-button class="mr-1" type="search" @click="query()"></i-button>
                <i-button class="mr-1" type="x-circle" @click="reset()"></i-button>
                <i-button class="mr-1" type="file-earmark-plus" @click="create()"></i-button>
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
              <i-button class="mb-1" size="sm" type="binoculars" @click="toEditView(data.item.batchServiceNo)"></i-button>
              <i-button class="mb-1" size="sm" type="pencil-square" @click="toModifyView(data.item.batchServiceNo)"></i-button>
              <i-button class="mb-1" size="sm" type="trash" @click="submitDelete(data.item.batchServiceNo)"></i-button>
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
import { executeFormatter, statusFormatter, yesNoFormatter } from '@/shared/formatter/common';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';

export default {
  name: 'Adm009Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props, context) {
    // modal物件
    const $bvModal = useBvModal();
    const notificationService: NotificationService = useNotification();
    // 查詢結果及狀態
    const stepVisible = ref(true);
    const queryStatus = ref(false);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const adm009FormDefaults = {
      batchServiceNo: '',
      batchServiceName: '',
      batchServiceCategory: '',
      isRegular: '',
    };

    // 建立表單物件 ref
    const adm009Form = reactive(Object.assign({}, adm009FormDefaults));

    // 表單物件驗證規則
    const rules = {
      batchServiceNo: {},
      batchServiceName: {},
      batchServiceCategory: {},
      isRegular: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, adm009Form, adm009FormDefaults);

    const queryOptions = reactive({
      batchServiceCategoryOpts: [
        { value: '', text: '請選擇' },
        { value: 'bsc01', text: '批次服務類型1' },
        { value: 'bsc02', text: '批次服務類型2' },
        { value: 'bsc03', text: '批次服務類型3' },
      ],
      isRegularOpts: [
        { value: '', text: '請選擇' },
        { value: 'Y', text: '是' },
        { value: 'N', text: '否' },
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
          key: 'isRegular',
          label: '是否為定期作業',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '5%' },
          formatter: (value: string) => yesNoFormatter(value),
        },
        {
          key: 'batchServiceStatus',
          label: '批次服務狀態',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '5%' },
          formatter: (value: string) => statusFormatter(value),
        },
        {
          key: 'batchExeStatus',
          label: '執行狀況',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '5%' },
          formatter: (value: string) => executeFormatter(value),
        },
        // {
        //   key: 'batchFrequency',
        //   label: '作業頻率',
        //   sortable: true,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        //   thStyle: { width: '8%' },
        // },
        {
          key: 'action',
          sortable: false,
          label: '動作',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '18%' },
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
            .post('/adm-batch-services/criteria-jpa', adm009Form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .finally(() => {})
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    function create() {
      navigateByNameAndParams('adm009-edit-info', { batchServiceNo: '_new' });
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(adm009Form, pagination);
      query();
    };

    // 依點選項目之帳號 轉至審核畫面
    function toEditView(batchServiceNo: string) {
      navigateByNameAndParams('adm009-edit-info', { batchServiceNo: batchServiceNo, formStatus: 'info' });
    }

    function toModifyView(batchServiceNo: string) {
      navigateByNameAndParams('adm009-edit-info', { batchServiceNo: batchServiceNo, formStatus: 'modify' });
    }

    function submitDelete(batchServiceNo: string) {
      $bvModal
        .msgBoxConfirm('是否確認刪除？', {
          headerBgVariant: 'danger',
          headerTextVariant: 'light',
          titleTag: 'h4',
          title: '刪除確認',
          headerClass: 'p-2 justify-content-center font-weight-bolder',
          bodyClass: 'h5',
          footerClass: 'justify-content-center',
          okTitle: '確定',
          okVariant: 'info',
          cancelTitle: '取消',
        })
        .then((isOK: boolean) => {
          if (isOK) {
            deleteBatchServiceInfo(batchServiceNo);
          }
        });
    }

    function deleteBatchServiceInfo(batchServiceNo: string) {
      axios
        .delete(`/adm-batch-services/${batchServiceNo}`)
        .then(() => {
          query();
        })
        .catch(notificationErrorHandler(notificationService));
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
      toModifyView,
      submitDelete,
      query,
      create,
      handlePaginationChanged,
    };
  },
};
</script>

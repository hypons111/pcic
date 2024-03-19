<template>
  <div>
    <section class="container mt-4">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                功能查詢
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
                <i-form-group-check :label="$t('label.adm006.functionId')" :item="$v.functionId">
                  <b-form-input v-model.trim="$v.functionId.$model"></b-form-input>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm006.functionName')" :item="$v.functionName">
                  <b-form-input v-model.trim="$v.functionName.$model"></b-form-input>
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <i-form-group-check :label="$t('label.adm006.sysCategory')" :item="$v.sysCategory">
                  <b-form-select :options="queryOptions.sysCategoryOpts" v-model="$v.sysCategory.$model"></b-form-select>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm006.functionCategory')" :item="$v.functionCategory">
                  <b-form-select :options="queryOptions.functionCategoryOpts" v-model="$v.functionCategory.$model"></b-form-select>
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <i-form-group-check :label="$t('label.adm006.status')" :item="$v.status">
                  <b-form-select :options="queryOptions.statusOpts" v-model="$v.status.$model"></b-form-select>
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
            :isServerSidePaging="false"
            @changePagination="handlePaginationChanged($event)"
          >
            <template #cell(rowNum)="data">
              {{ data.index + 1 }}
            </template>
            <template #cell(action)="data">
              <i-button class="mb-1" size="sm" type="binoculars" @click="toEditView(data.item.functionId)"></i-button>
              <i-button class="mb-1" size="sm" type="pencil-square" @click="toModifyView(data.item.functionId)"></i-button>
              <i-button class="mb-1" size="sm" type="trash" @click="submitDelete(data.item.functionId)"></i-button>
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
import { functionCategoryFormatter, statusFormatter } from '@/shared/formatter/common';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';

export default {
  name: 'Adm006Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props, context) {
    const notificationService: NotificationService = useNotification();
    // modal物件
    const $bvModal = useBvModal();
    const stepVisible = ref(true);
    // 查詢結果及狀態
    const queryStatus = ref(false);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const adm006FormDefaults = {
      functionId: '', //功能代號
      functionName: '', //功能名稱
      functionCategory: '', //功能類別
      sysCategory: '', //系統類別
      status: '', //使用狀態
    };

    // 建立表單物件 ref
    const adm006Form = reactive(Object.assign({}, adm006FormDefaults));

    // 表單物件驗證規則
    const rules = {
      functionId: {},
      functionName: {},
      functionCategory: {},
      sysCategory: {},
      status: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, adm006Form, adm006FormDefaults);

    const queryOptions = reactive({
      statusOpts: [
        { value: '', text: '請選擇' },
        { value: 'Y', text: '啟用' },
        { value: 'N', text: '停用' },
      ],
      functionCategoryOpts: [
        { value: '', text: '請選擇' },
        { value: '0', text: '選單' },
        { value: '1', text: '可執行' },
      ],
      sysCategoryOpts: [
        { value: '', text: '請選擇' },
        { value: '1', text: '工程雲服務網' },
        { value: '2', text: '工程雲後臺管理' },
        { value: '3', text: '全球資訊網' },
        { value: '4', text: '全球資訊網後臺管理' },
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
          thStyle: { width: '12%' },
        },
        {
          key: 'functionCategory',
          label: '功能類別',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
          formatter: (value: string) => functionCategoryFormatter(value),
        },
        {
          key: 'status',
          label: '使用狀態',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '6%' },
          formatter: (value: string) => statusFormatter(value),
        },
        {
          key: 'action',
          sortable: false,
          label: '動作',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '12%' },
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    // 依表單條件查詢功能
    function query() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/adm-function/criteria', adm006Form) //前端分頁
            .then(({ data }) => {
              table.data = []; //前端分頁
              table.data.splice(0, table.data.length, ...data); //前端分頁
              table.totalItems = data.length; //前端分頁
            })
            .finally(() => {})
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(adm006Form, pagination);
      query();
    };

    function create() {
      navigateByNameAndParams('adm006-edit-info', { functionId: '_new' });
    }

    // 依點選項目之admFunction 轉至檢視功能畫面
    function toEditView(functionId: string) {
      navigateByNameAndParams('adm006-edit-info', { functionId: functionId, formStatus: 'info' });
    }

    function toModifyView(functionId: string) {
      navigateByNameAndParams('adm006-edit-info', { functionId: functionId, formStatus: 'modify' });
    }

    function submitDelete(functionId: string) {
      $bvModal
        .msgBoxConfirm('是否確認刪除?', {
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
            deleteFunction(functionId);
          }
        });
    }

    function deleteFunction(functionId: string) {
      axios
        .delete(`/adm-function/${functionId}`)
        .then(() => {
          query();
        })
        .catch(notificationErrorHandler(notificationService));
    }

    const isReloadProp = computed(() => props.isReload);

    watch(
      isReloadProp,
      () => {
        if (isReloadProp.value) {
          query();
        }
        return props.isReload;
      },
      { immediate: true }
    );

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

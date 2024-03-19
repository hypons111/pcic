<template>
  <div>
    <section class="container mt-4">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                角色查詢
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
                <i-form-group-check :label="$t('label.adm005.roleId')" :item="$v.roleId">
                  <b-form-input v-model.trim="$v.roleId.$model"></b-form-input>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm005.roleName')" :item="$v.roleName">
                  <b-form-input v-model.trim="$v.roleName.$model"></b-form-input>
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <i-form-group-check :label="$t('label.adm005.businessCategory')" :item="$v.businessCategory">
                  <b-form-select :options="queryOptions.businessCategoryOpts" v-model="$v.businessCategory.$model"></b-form-select>
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
              <i-button class="mb-1" size="sm" type="binoculars" @click="toEditView(data.item.roleId)"></i-button>
              <i-button class="mb-1" size="sm" type="pencil-square" @click="toModifyView(data.item.roleId)"></i-button>
              <i-button class="mb-1" size="sm" type="trash" @click="submitDelete(data.item.roleId)"></i-button>
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
import { Pagination } from '@/shared/model/pagination.model';
import { businessCategoryFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';

export default {
  name: 'Adm005Query',
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
    const adm005FormDefaults = {
      roleId: '', //帳號
      roleName: '', //姓名
      businessCategory: '', //業務類別
    };

    // 建立表單物件 ref
    const adm005Form = reactive(Object.assign({}, adm005FormDefaults));

    // 表單物件驗證規則
    const rules = {
      roleId: {},
      roleName: {},
      businessCategory: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, adm005Form, adm005FormDefaults);

    const queryOptions = reactive({
      businessCategoryOpts: [
        { value: '', text: '請選擇' },
        { value: '1', text: '技師顧問' },
        { value: '2', text: '價格資料庫' },
        { value: '3', text: '技術資料庫' },
        { value: '4', text: '標案管理' },
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
          key: 'roleId',
          label: '角色代號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '10%' },
        },
        {
          key: 'roleName',
          label: '角色名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'businessCategory',
          label: '業務類別',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
          formatter: (value: string) => businessCategoryFormatter(value),
        },
        {
          key: 'roleFunctionList',
          label: '角色功能',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '27%' },
        },
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
            .post('/adm-role/criteria', adm005Form) //前端分頁
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
      Object.assign(adm005Form, pagination);
      query();
    };

    function create() {
      navigateByNameAndParams('adm005-edit-info', { roleId: '_new' });
    }

    // 依點選項目之帳號 轉至檢視使用者畫面
    function toEditView(roleId: string) {
      navigateByNameAndParams('adm005-edit-info', { roleId: roleId, formStatus: 'info' });
    }

    function toModifyView(roleId: string) {
      navigateByNameAndParams('adm005-edit-info', { roleId: roleId, formStatus: 'modify' });
    }

    function submitDelete(roleId: string) {
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
            deleteRole(roleId);
          }
        });
    }

    function deleteRole(roleId: string) {
      axios
        .delete(`/adm-role/${roleId}`)
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

<template>
  <div>
    <section class="container mt-4">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                延伸功能查詢
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
                <i-form-group-check :label="$t('label.adm007.functionExtendId')" :item="$v.functionExtendId">
                  <b-form-input v-model.trim="$v.functionExtendId.$model"></b-form-input>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm007.functionExtendName')" :item="$v.functionExtendName">
                  <b-form-input v-model.trim="$v.functionExtendName.$model"></b-form-input>
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
              <i-button class="mb-1" size="sm" type="binoculars" @click="toEditView(data.item.functionExtendId)"></i-button>
              <i-button class="mb-1" size="sm" type="pencil-square" @click="toModifyView(data.item.functionExtendId)"></i-button>
              <i-button class="mb-1" size="sm" type="trash" @click="submitDelete(data.item.functionExtendId)"></i-button>
            </template>
          </i-table>
        </div>
      </div>
    </section>
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
import { useBvModal } from '@/shared/modal';

export default {
  name: 'Adm007Query',
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
    const stepVisible = ref(true);
    // 查詢結果及狀態
    const queryStatus = ref(false);
    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const adm007FormDefaults = {
      functionExtendId: '', //延伸功能代號
      functionExtendName: '', //延伸功能名稱
    };
    // 建立表單物件 ref
    const adm007Form = reactive(Object.assign({}, adm007FormDefaults));
    // 表單物件驗證規則
    const rules = {
      functionExtendId: {},
      functionExtendName: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, adm007Form, adm007FormDefaults);

    onMounted(() => {
      // 初始化查詢
      testAPI();
    });

    function testAPI() {
      axios
        .get('/cors/getOpenData/parking') //前端分頁
        .then(({ data }) => {
          console.log(data);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

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
            .post('/adm-function-extend/criteria', adm007Form) //前端分頁
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
      Object.assign(adm007Form, pagination);
      query();
    };

    function create() {
      navigateByNameAndParams('adm007-edit-info', { functionExtendId: '_new' });
    }

    // 依點選項目之admFunctionExtend 轉至檢視功能畫面
    function toEditView(functionExtendId: string) {
      navigateByNameAndParams('adm007-edit-info', { functionExtendId: functionExtendId, formStatus: 'info' });
    }

    function toModifyView(functionExtendId: string) {
      navigateByNameAndParams('adm007-edit-info', { functionExtendId: functionExtendId, formStatus: 'modify' });
    }

    function submitDelete(functionExtendId: string) {
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
            deleteFunctionExtend(functionExtendId);
          }
        });
    }

    function deleteFunctionExtend(functionExtendId: string) {
      axios
        .delete(`/adm-function-extend/${functionExtendId}`)
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

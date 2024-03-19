<template>
  <div>
    <section class="container mt-4">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                i18n代碼設定
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
                <i-form-group-check :label="$t('label.adm013.lang')" :item="$v.lang">
                  <b-form-select :options="queryOptions.langOpts" v-model="$v.lang.$model"></b-form-select>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm013.category')" :item="$v.category">
                  <b-form-select :options="queryOptions.categoryOpts" v-model="$v.category.$model"></b-form-select>
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <i-form-group-check :label="$t('label.adm013.code')" :item="$v.code">
                  <b-form-input v-model.trim="$v.code.$model"></b-form-input>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm013.text')" :item="$v.text">
                  <b-form-input v-model.trim="$v.text.$model"></b-form-input>
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
              <i-button class="mb-1" size="sm" type="binoculars" @click="toEditView(data.item.id)"></i-button>
              <i-button class="mb-1" size="sm" type="pencil-square" @click="toModifyView(data.item.id)"></i-button>
              <i-button class="mb-1" size="sm" type="trash" @click="submitDelete(data.item.id)"></i-button>
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
import { statusFormatter } from '@/shared/formatter/common';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { AdmSysCode } from '@/shared/model/adm-sys-code.model';
import { AdmSysParameter } from '@/shared/model/adm-sys-parameter.model';
import { useBvModal } from '@/shared/modal';

export default {
  name: 'Adm013Query',
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
    const adm013FormDefaults = {
      lang: '', //語系
      category: '', //i18n代碼類別
      code: '', //代碼
      text: '', //顯示內容
    };

    // 建立表單物件 ref
    const adm013Form = reactive(Object.assign({}, adm013FormDefaults));

    // 表單物件驗證規則
    const rules = {
      lang: {},
      category: {},
      code: {},
      text: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, adm013Form, adm013FormDefaults);

    const queryOptions = reactive({
      langOpts: [
        { value: '', text: '請選擇' },
        { value: 'zh_TW', text: 'zh_TW' },
        { value: 'en_US', text: 'en_US' },
      ],
      categoryOpts: [
        { value: '', text: '請選擇' },
        { value: 'button', text: 'button' },
        { value: 'label', text: 'label' },
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
          key: 'lang',
          label: '語系',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '10%' },
        },
        {
          key: 'category',
          label: 'i18n代碼類別',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '10%' },
        },
        {
          key: 'code',
          label: '代碼',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '10%' },
        },
        {
          key: 'text',
          label: '顯示內容',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '20%' },
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
            .post('/adm-code-table/criteria', adm013Form)
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
      navigateByNameAndParams('adm013-edit-info', { id: '_new' });
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(adm013Form, pagination);
      query();
    };

    // 依點選項目之帳號 轉至審核畫面
    function toEditView(id: number) {
      navigateByNameAndParams('adm013-edit-info', { id: id, formStatus: 'info' });
    }

    function toModifyView(id: number) {
      navigateByNameAndParams('adm013-edit-info', { id: id, formStatus: 'modify' });
    }

    function submitDelete(id: number) {
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
            deleteCodeTableInfo(id);
          }
        });
    }

    function deleteCodeTableInfo(id: number) {
      axios
        .delete(`/adm-code-table/${id}`)
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

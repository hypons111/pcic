<template>
  <div>
    <section class="container mt-4">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                系統代碼設定
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
                <i-form-group-check :label="$t('label.adm012.moduleType')" :item="$v.moduleType">
                  <b-form-select :options="queryOptions.moduleTypeOpts" v-model="$v.moduleType.$model"></b-form-select>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm012.dataType')" :item="$v.dataType">
                  <b-form-select :options="queryOptions.dataTypeOpts" v-model="$v.dataType.$model"></b-form-select>
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <i-form-group-check :label="$t('label.adm012.dataKey')" :item="$v.dataKey">
                  <b-form-input v-model.trim="$v.dataKey.$model"></b-form-input>
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
              <i-button class="mb-1" size="sm" type="binoculars" @click="toEditView(data.item)"></i-button>
              <i-button class="mb-1" size="sm" type="pencil-square" @click="toModifyView(data.item)"></i-button>
              <i-button class="mb-1" size="sm" type="trash" @click="submitDelete(data.item)"></i-button>
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
  name: 'Adm012Query',
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
    const adm012FormDefaults = {
      moduleType: '', //模組別
      dataType: '', //系統代碼類別
      dataKey: '', //系統代碼
    };

    // 建立表單物件 ref
    const adm012Form = reactive(Object.assign({}, adm012FormDefaults));

    // 表單物件驗證規則
    const rules = {
      moduleType: {},
      dataType: {},
      dataKey: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, adm012Form, adm012FormDefaults);

    const allDataTypeOpts = ref([]);

    onMounted(() => {
      getAllDataTypeOpts();
    });

    function getAllDataTypeOpts() {
      axios
        .get('adm-sys-codes/get-data-type-opts')
        .then(({ data }) => {
          allDataTypeOpts.value.splice(0, allDataTypeOpts.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    const queryOptions = reactive({
      moduleTypeOpts: [
        { value: '', text: '請選擇' },
        { value: 'M01', text: '模組別1' },
        { value: 'M02', text: '模組別2' },
        { value: 'M03', text: '模組別3' },
      ],
      dataTypeOpts: [
        { value: '', text: '請選擇' },
        { value: 'T01', text: '系統代碼類別1' },
        { value: 'T02', text: '系統代碼類別2' },
        { value: 'T03', text: '系統代碼類別3' },
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
          key: 'moduleType',
          label: '模組別',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'dataType',
          label: '系統代碼類別',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'dataKey',
          label: '系統代碼',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'value',
          label: '屬性',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '15%' },
        },
        {
          key: 'codeDescript',
          label: '代碼描述',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '15%' },
        },
        {
          key: 'isValid',
          label: '是否啟用',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '5%' },
          formatter: (value: string) => statusFormatter(value),
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
            .post('/adm-sys-codes/criteria-jpa', adm012Form)
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
      const creation = new AdmSysCode();
      creation.moduleType = '_new';
      creation.dataType = '_new';
      creation.dataKey = '_new';
      navigateByNameAndParams('adm012-edit-info', { admSysCode: creation });
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(adm012Form, pagination);
      query();
    };

    // 依點選項目之帳號 轉至審核畫面
    function toEditView(data: AdmSysCode) {
      navigateByNameAndParams('adm012-edit-info', { admSysCode: data, formStatus: 'info' });
    }

    function toModifyView(data: AdmSysCode) {
      navigateByNameAndParams('adm012-edit-info', { admSysCode: data, formStatus: 'modify' });
    }

    function submitDelete(data: AdmSysCode) {
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
            deleteSysCodeInfo(data.moduleType, data.dataType, data.dataKey);
          }
        });
    }

    function deleteSysCodeInfo(moduleType: string, dataType: string, dataKey: string) {
      axios
        .delete(`/adm-sys-codes/${moduleType}/${dataType}/${dataKey}`)
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
    watch(allDataTypeOpts, () => {
      queryOptions.dataTypeOpts = allDataTypeOpts.value.map(data => {
        return { text: data['dataType'], value: data['dataType'] };
      });
      queryOptions.dataTypeOpts.unshift({ text: '請選擇', value: '' });
    });

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

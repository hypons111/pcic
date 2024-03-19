<template>
  <div>
    <section class="container mt-4">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                系統參數設定
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
                <i-form-group-check :label="$t('label.adm011.sysName')" :item="$v.sysName">
                  <b-form-input v-model.trim="$v.sysName.$model"></b-form-input>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm011.sysType')" :item="$v.sysType">
                  <b-form-select :options="queryOptions.sysTypeOpts" v-model="$v.sysType.$model"></b-form-select>
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
import { AdmSysParameter } from '@/shared/model/adm-sys-parameter.model';
import { useBvModal } from '@/shared/modal';

export default {
  name: 'Adm011Query',
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
    const adm011FormDefaults = {
      sysName: '', //系統參數名稱
      sysType: '', //系統參數類型
      status: '', //狀態
    };

    // 建立表單物件 ref
    const adm011Form = reactive(Object.assign({}, adm011FormDefaults));

    // 表單物件驗證規則
    const rules = {
      sysName: {},
      sysType: {},
      status: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, adm011Form, adm011FormDefaults);

    const allSysTypeOpts = ref([]);

    onMounted(() => {
      getAllSysTypeOpts();
    });

    function getAllSysTypeOpts() {
      axios
        .get('adm-sys-parameters/get-sys-type-opts')
        .then(({ data }) => {
          allSysTypeOpts.value.splice(0, allSysTypeOpts.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    const queryOptions = reactive({
      sysTypeOpts: [],
      statusOpts: [
        { value: '', text: '請選擇' },
        { value: 'Y', text: '啟用' },
        { value: 'N', text: '停用' },
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
          key: 'sysName',
          label: '系統參數名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '12%' },
        },
        {
          key: 'sysType',
          label: '系統參數類型',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'sysValue',
          label: '系統參數值',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'descript',
          label: '系統參數描述',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '15%' },
        },
        {
          key: 'status',
          label: '狀態',
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
          thStyle: { width: '15%' },
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
            .post('/adm-sys-parameters/criteria-jpa', adm011Form)
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
      const creation = new AdmSysParameter();
      creation.sysName = '_new';
      creation.sysType = '_new';
      navigateByNameAndParams('adm011-edit-info', { admSysParameter: creation });
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(adm011Form, pagination);
      query();
    };

    // 依點選項目之帳號 轉至審核畫面
    function toEditView(data: AdmSysParameter) {
      navigateByNameAndParams('adm011-edit-info', { admSysParameter: data, formStatus: 'info' });
    }

    function toModifyView(data: AdmSysParameter) {
      navigateByNameAndParams('adm011-edit-info', { admSysParameter: data, formStatus: 'modify' });
    }

    function submitDelete(data: AdmSysParameter) {
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
            deleteSysParameterInfo(data.sysName, data.sysType);
          }
        });
    }

    function deleteSysParameterInfo(sysName: string, sysType: string) {
      axios
        .delete(`/adm-sys-parameters/${sysName}/${sysType}`)
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
    watch(allSysTypeOpts, () => {
      queryOptions.sysTypeOpts = allSysTypeOpts.value.map(data => {
        return { text: data['sysType'], value: data['sysType'] };
      });
      queryOptions.sysTypeOpts.unshift({ text: '請選擇', value: '' });
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

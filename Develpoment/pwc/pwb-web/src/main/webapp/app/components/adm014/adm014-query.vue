<template>
  <div>
    <section class="container mt-4">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                日曆設定查詢
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
                <i-form-group-check :label="$t('label.adm014.holidayDate')" :item="$v.holidayDate">
                  <i-date-picker type="date" v-model="$v.holidayDate.$model" />
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm014.holidayName')" :item="$v.holidayName">
                  <b-form-input v-model.trim="$v.holidayName.$model"></b-form-input>
                </i-form-group-check>
              </b-form-row>

              <b-form-group
                :label="$t('label.adm014.dateRange')"
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
import { computed, reactive, ref, watch } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { dateTimeFormatter, formatDate } from '@/shared/date/minguo-calendar-utils';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';

export default {
  name: 'Adm014Query',
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
    const adm014FormDefaults = {
      holidayDate: null,
      holidayName: '',
      dateStart: null,
      dateEnd: null,
    };

    // 建立表單物件 ref
    const adm014Form = reactive(Object.assign({}, adm014FormDefaults));

    // 表單物件驗證規則
    const rules = {
      holidayDate: {},
      holidayName: {},
      dateStart: {},
      dateEnd: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, adm014Form, adm014FormDefaults);

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
          key: 'holidayDate',
          label: '假日日期',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '12%' },
          formatter: (value: any) => formatDate(value, '/'),
        },
        {
          key: 'holidayName',
          label: '假日名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'updateUser',
          label: '更新人員',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'updateTime',
          label: '更新時間',
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
          thStyle: { width: '12%' },
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    // 依表單條件查詢
    function query() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (adm014Form.holidayDate !== null) {
            adm014Form.holidayDate = new Date(adm014Form.holidayDate);
          }
          if (adm014Form.dateStart !== null) {
            adm014Form.dateStart = new Date(adm014Form.dateStart);
          }
          if (adm014Form.dateEnd !== null) {
            adm014Form.dateEnd = new Date(adm014Form.dateEnd);
          }
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/adm-holidays/criteria-jpa', adm014Form)
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
      navigateByNameAndParams('adm014-edit-info', { id: '_new' });
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(adm014Form, pagination);
      query();
    };

    // 依點選項目之日期 轉至檢視畫面
    function toEditView(id: any) {
      navigateByNameAndParams('adm014-edit-info', { id: id, formStatus: 'info' });
    }

    function toModifyView(id: any) {
      navigateByNameAndParams('adm014-edit-info', { id: id, formStatus: 'modify' });
    }

    function submitDelete(id: any) {
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
            deleteHolidayInfo(id);
          }
        });
    }

    function deleteHolidayInfo(id: number) {
      axios
        .delete(`/adm-holidays/${id}`)
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

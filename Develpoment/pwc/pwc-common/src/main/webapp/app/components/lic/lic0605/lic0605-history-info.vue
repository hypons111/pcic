<template>
  <div>
    <!--查詢條件區-->
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            歷次辦理情形
          </h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.planName') + '：'" :item="$v.projectName">
              <b-form-input v-model="$v.projectName.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.dept') + '：'" :item="$v.adminOrg">
              <b-form-input v-model="$v.adminOrg.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.lic_class') + '：'" :item="$v.licenseCategory">
              <b-form-input v-model="$v.licenseCategory.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.licenseName') + '：'" :item="$v.licenseName">
              <b-form-input v-model="$v.licenseName.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <div class="button-float-right">
            <b-button-toolbar>
              <b-button class="mt-1 ml-1" variant="info" @click="">匯出ods</b-button>
            </b-button-toolbar>
          </div>
        </div>
      </div>
    </section>

    <!--系統資料區-->
    <section class="container mt-3">
      <i-table
        class="table-sm"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields"
        :totalItems="table.totalItems"
        :is-server-side-paging="true"
        @changePagination="handlePaginationChanged($event)"
      >
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, ref, toRefs, watch, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { LicLicense } from '@/shared/model/licModel/lic-license.model';
import axios from 'axios';
import { required } from '@/shared/validators';
import { formatDate } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'lic0605HistoryInfo',
  props: {
    licLicense: {
      type: Object,
      required: false,
    },
  },

  //props代表傳進來的參數，context代表整頁的容器物件
  setup(props, context) {
    onMounted(() => {
      handleQuery();
    });

    onActivated(() => {
      handleQuery();
    });

    const licenseProp = toRefs(props).licLicense;

    //表單預設值
    const formDefault = {
      projectName: '', //計畫名稱
      adminOrg: '', //主辦機關
      licenseCategory: '', //證照許可類別
      licenseName: '', //送審書件名稱
    };

    //表單物件驗證規則
    const rules = {
      projectName: {},
      adminOrg: {},
      licenseCategory: {},
      licenseName: {},
    };

    //建立表單物件ref
    //reactive()內傳入參數可監聽該物件達到響應式
    //Object.assign(,)為指派第2個參數對象到第1個參數
    const form = reactive(Object.assign({}, formDefault));

    //$v為表單物件轉換而來，checkValidity是用來做欄位檢核
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //modal物件，用在bootstrap-vue彈出視窗用
    const $bvModal = useBvModal();

    //查詢表單樣式
    const table = reactive({
      fields: [
        {
          key: 'procDate',
          label: $i18n.t('label.date'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
          // formatter: value => (value == undefined ? '' : formatDate(new Date(value), '/')),
        },
        {
          key: 'intervalDays',
          label: $i18n.t('label.intervalDays'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'handlingResult',
          label: $i18n.t('label.handlingResult'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'relatedRecord',
          label: $i18n.t('label.relatedRecord'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 1, //TODO:假資料用
    });

    const handleQuery = () => {
      table.data = undefined;
      table.data = [];
      table.data.splice(0, table.data.length, ...mockdata);
    };

    const createDefaultValue = (data: LicLicense) => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
    };

    watch(
      licenseProp,
      () => {
        createDefaultValue(licenseProp.value);
      },
      { immediate: true }
    );

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const mockdata = [
      {
        procDate: '2014.03.16',
        intervalDays: '',
        handlingResult: '第1次 送審',
        relatedRecord: '',
      },
    ];

    //變數要做return，<template>才可以使用
    return {
      $v,
      validateState,
      checkValidity,
      table,
      reset,
      handleQuery,
      handlePaginationChanged,
      licenseProp,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

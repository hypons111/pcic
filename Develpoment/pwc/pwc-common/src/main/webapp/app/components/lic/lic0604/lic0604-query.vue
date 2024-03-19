<template>
  <div>
    <!--查詢條件區-->
    <section class="container">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
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
          <b-collapse v-model="stepVisible">
            <b-form-row>
              <i-form-group-check
                class="col-12"
                :label-cols="2"
                :content-cols="10"
                :label="$t('label.planName') + '：'"
                :item="$v.projectName"
                :labelStar="true"
              >
                <b-form-select :options="options.projectName" v-model="$v.projectName.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.licenseCategory') + '：'" :item="$v.licenseCategory" :labelStar="true">
                <b-form-select :options="options.licenseCategory" v-model="$v.licenseCategory.$model"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.licenseName') + '：'" :item="$v.licenseName" :labelStar="true">
                <b-form-select :options="options.licenseName" v-model="$v.licenseName.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>

            <div class="button-float-right">
              <b-button-toolbar>
                <i-button class="mt-1 ml-1" type="search" @click="handleQuery"></i-button>
                <i-button class="mt-1 ml-1" type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>

    <!-- 匯出ods 按鈕 -->
    <section>
      <div class="container mt-1 mb-1">
        <b-button class="mt-1 ml-1" variant="info" @click="">匯出ods</b-button>
      </div>
    </section>

    <!--系統資料區-->
    <section class="container mt-3" v-if="queryStatus">
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
import { computed, onActivated, reactive, ref } from '@vue/composition-api';
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
  name: 'lic0604Query',
  props: {
    isReload: {
      required: false,
    },
  },
  //props代表傳進來的參數，context代表整頁的容器物件
  setup(props, context) {
    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    const isReloadProp = computed(() => props.isReload);

    //表單預設值
    const formDefault = {
      projectName: '', //計畫名稱
      licenseCategory: '', //證照類別
      licenseName: '', //送審書件名稱
    };

    //表單物件驗證規則
    const rules = {
      projectName: {},
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

    //下拉式選單
    const options = reactive({
      projectName: [
        { value: '', text: '全部' },
        { value: '1', text: '國立故宮博物院南部院區籌設計畫' },
        { value: '2', text: '曾文南化聯通管工程計畫' },
        { value: '3', text: '烏溪鳥嘴潭人工湖工程計畫' },
        { value: '4', text: '臺南市先進運輸系統紅線規劃作業' },
      ],
      licenseCategory: [
        { value: '', text: '全部' },
        { value: '1', text: '環境影響' },
        { value: '2', text: '水土保持' },
        { value: '3', text: '非都編定' },
        { value: '4', text: '都市計畫' },
        { value: '5', text: '用地取得' },
        { value: '6', text: '都設審議' },
        { value: '7', text: '綠建築' },
        { value: '8', text: '文化保存' },
        { value: '9', text: '建築許可' },
        { value: '10', text: '河川許可' },
        { value: '11', text: '跨鐵公路' },
        { value: '12', text: '管線處理' },
        { value: '13', text: '其它法令' },
      ],
      licenseName: [
        { value: '', text: '全部' },
        { value: '1', text: '使用執照' },
      ],
    });

    //查詢結果及狀態
    const queryStatus = ref(false);
    //顯示查詢條件區
    let stepVisible = ref(true);

    //modal物件，用在bootstrap-vue彈出視窗用
    const $bvModal = useBvModal();

    //查詢表單樣式
    const table = reactive({
      fields: [
        {
          key: 'projectName',
          label: $i18n.t('label.planName'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'licenseCategory',
          label: $i18n.t('label.lic_class'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'licenseName',
          label: $i18n.t('label.licenseName'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'reviewOrg',
          label: $i18n.t('label.organ_review'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'expectDate',
          label: $i18n.t('label.date_due'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
          // formatter: value => (value == undefined ? '' : formatDate(new Date(value), '/')),
        },
        {
          key: 'realDate',
          label: $i18n.t('label.date_actual'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
          // formatter: value => (value == undefined ? '' : formatDate(new Date(value), '/')),
        },
        {
          key: 'handlingSituation',
          label: $i18n.t('label.handlingSituation'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'procDate',
          label: $i18n.t('label.date'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'memo',
          label: $i18n.t('label.memo'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 3, //TODO:假資料用
    });

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          table.data = [];
          table.data.splice(0, table.data.length, ...mockdata);
        }
      });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const mockdata = [
      {
        projectName: '曾文南化聯通管工程計畫',
        licenseCategory: '環境影響',
        licenseName: '使用執照',
        reviewOrg: '經濟部水利署',
        expectDate: '2015.09.30',
        realDate: '2015.12.18',
        handlingSituation: '送審 第1次辦理',
        procDate: '2014.06.18',
        memo: '',
      },
      {
        projectName: '烏溪鳥嘴潭人工湖工程計畫',
        licenseCategory: '水土保持',
        licenseName: '使用執照',
        reviewOrg: '經濟部水利署',
        expectDate: '2021.09.28',
        realDate: '2021.10.31',
        handlingSituation: '送審 第1次辦理',
        procDate: '2020.09.28',
        memo: '',
      },
      {
        projectName: '臺南市先進運輸系統紅線規劃作業',
        licenseCategory: '都市計畫',
        licenseName: '使用執照',
        reviewOrg: '臺南市捷運工程處',
        expectDate: '2021.04.25',
        realDate: '2021.09.30',
        handlingSituation: '送審 第1次辦理',
        procDate: '2019.03.25',
        memo: '',
      },
    ];

    //變數要做return，<template>才可以使用
    return {
      $v,
      validateState,
      checkValidity,
      queryStatus,
      stepVisible,
      options,
      table,
      reset,
      handleQuery,
      handlePaginationChanged,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

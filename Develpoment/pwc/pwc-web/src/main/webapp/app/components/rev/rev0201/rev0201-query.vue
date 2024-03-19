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
              <i-form-group-check :label="$t('label.dateType') + '：'" :item="$v.projectName">
                <b-form-select :options="options.dateType" v-model="$v.dateType.$model"></b-form-select>
              </i-form-group-check>
              <i-form-group-check> </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check
                class="col-12"
                label-cols="2"
                content-cols="10"
                :label="$t('label.datePeriod') + '：'"
                :item="$v.expiredDatePeriod"
              >
                <i-date-picker v-model="$v.dateStart.$model" class="col-5"></i-date-picker>
                <div class="col-2 text-center">-</div>
                <i-date-picker v-model="$v.dateEnd.$model" class="col-5"></i-date-picker>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.isOverdue') + '：'" :item="$v.isOverdue">
                <b-form-select :options="options.isOverdue" v-model="$v.isOverdue.$model"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.reviewCategory') + '：'" :item="$v.reviewCategory">
                <b-form-select :options="options.reviewCategory" v-model="$v.reviewCategory.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.queryField') + '：'" :item="$v.queryField">
                <b-form-select :options="options.queryField" v-model="$v.queryField.$model"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.queryContent') + '：'" :item="$v.queryContent">
                <b-form-input v-model="$v.queryContent.$model"> </b-form-input>
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
    <!-- 新增按鈕 -->
    <section>
      <div class="container mt-1 mb-1">
        <!-- 新增按鈕 -->
        <i-button type="file-earmark-plus" @click="toCreateView"></i-button>
        <!-- 匯出ods 按鈕 -->
        <b-button class="mt-1 ml-1" variant="info">匯出ods</b-button>
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
        <!--#cell為id,row代表這一列的資料-->
        <template #cell(action)="row">
          <i-button class="mt-1" type="binoculars" @click="toInfoView(row.item)"></i-button>
          <i-button class="mt-1" type="pencil-square" @click="toEditView(row.item)"></i-button>
          <b-button class="mt-1" variant="info" @click="toReviewView(row.item)">審查</b-button>
          <i-button class="mt-1" type="trash" @click="deleteData(row.item.id)"></i-button>
        </template>
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
import axios from 'axios';
import { required } from '@/shared/validators';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import i18n from '@/shared/i18n';

export default {
  name: 'rev0201Query',
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
      dateType: '', //日期條件種類
      dateStart: '', // 日期區間(起)
      dateEnd: '', // 日期區間(訖)
      isOverdue: [], // 是否為逾期
      reviewCategory: [], // 審議類別
      queryField: [], //查詢欄位
      queryContent: '', //查詢內容
    };

    //表單物件驗證規則
    const rules = {
      dateType: {},
      dateStart: {},
      dateEnd: {},
      isOverdue: {},
      reviewCategory: {},
      queryField: {},
      queryContent: {},
      realDateEnd: {},
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
      dateType: [
        { value: '', text: '無' },
        { value: '1', text: '建檔日' },
        { value: '2', text: '本會收文日' },
        { value: '3', text: '異動日期' },
      ],
      isOverdue: [
        { value: '', text: '全選' },
        { value: '1', text: '是' },
        { value: '2', text: '否' },
      ],
      reviewCategory: [
        { value: '', text: '全選' },
        { value: '1', text: '可行性評估' },
        { value: '2', text: '新興建設計畫' },
        { value: '3', text: '修正計畫' },
      ],
      queryField: [
        { value: '', text: '無' },
        { value: '1', text: '狀態' },
        { value: '2', text: '主管機關' },
        { value: '3', text: '主辦機關' },
        { value: '4', text: '案件編號' },
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
          key: 'reviewType',
          label: i18n.t('label.reviewCategory'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'createDate',
          label: i18n.t('label.fileDate'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'caseName',
          label: i18n.t('label.caseNa'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'status',
          label: i18n.t('label.status'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'adminOrg',
          label: i18n.t('label.bos'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'org',
          label: i18n.t('label.dept'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'contactPerson',
          label: i18n.t('label.contactPersonId'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'classify',
          label: i18n.t('label.classify'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'pccUnit',
          label: i18n.t('label.subject'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'receiptDate',
          label: i18n.t('label.receiptDate'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 4, //TODO:假資料用
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

    // 匯出 ods
    const exportOds = () => {
      // form.fileName = 'ods' + Date.now();
      // axios.post('/service/rec-projects/export-ods', form, { responseType: 'blob' }).then(response => {
      //   const url = window.URL.createObjectURL(new Blob([response.data]));
      //   const link = document.createElement('a');
      //   link.href = url;
      //   link.setAttribute('download', form.fileName + '.ods');
      //   document.body.appendChild(link);
      //   link.click();
      // });
    };

    //轉到新增頁面
    const toCreateView = () => {
      navigateByNameAndParams('rev0202EditInfo', { formStatusForward: 'create' });
    };

    //轉到編輯頁面
    const toEditView = (revMain: any) => {
      navigateByNameAndParams('rev0202EditInfo', { formStatusForward: 'modify', revMain: revMain, isNotKeepAlive: true });
    };

    //轉到檢視頁面
    const toInfoView = (revMain: any) => {
      navigateByNameAndParams('rev0202EditInfo', { formStatusForward: 'info', revMain: revMain, isNotKeepAlive: true });
    };

    //轉到審查頁面
    const toReviewView = (revMain: any) => {
      navigateByNameAndParams('rev0203ReviewInfo', { revMain: revMain, isNotKeepAlive: true });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    //刪除
    const deleteData = (deleteId: string) => {
      // $bvModal.msgBoxConfirm('刪除此工程將連同相關基本資料、災損圖片、概念設計示意圖說文件、復建工項等資料一併刪除，確定刪除？').then((isOK: boolean) => {
      //   if (isOK) {
      //     let url: string;
      //     url = `/rec-projects/` + deleteId;
      //     axios
      //       .delete(url)
      //       .then($bvModal.msgBoxOk('資料刪除成功'))
      //       .finally(handleQuery())
      //       .catch(notificationErrorHandler(notificationService));
      //   }
      // });
      $bvModal.msgBoxConfirm('確定刪除計畫評估申請資料？').then((isOK: boolean) => {
        $bvModal.msgBoxOk('資料刪除成功');
        handleQuery();
      });
    };

    const mockdata = [
      {
        reviewType: '可行性評估',
        createDate: '110/03/26',
        caseName: '國道1號後勁溪過水橋新建工程',
        status: '審查完成',
        adminOrg: '交通部',
        org: '交通部高速公路局',
        contactPerson: '劉建位',
        classify: '公路',
        pccUnit: '二科',
        receiptDate: '110/03/15',
        id: '1',
      },
      {
        reviewType: '修正計畫',
        createDate: '110/1/19',
        caseName: '桃園航空城機場園區用地取得計畫',
        status: '審查完成',
        adminOrg: '交通部',
        org: '交通部民用航空局',
        contactPerson: '楊博淵',
        classify: '都市開發',
        pccUnit: '一科',
        receiptDate: '109/12/16',
        id: '2',
      },
      {
        reviewType: '可行性評估',
        createDate: '109/1/13',
        caseName: '宜蘭線龜山-外澳間路線改善工程可行性研究',
        status: '審查完成',
        adminOrg: '交通部',
        org: '交通部臺灣鐵路管理局',
        contactPerson: '張巍霈',
        classify: '軌道運輸',
        pccUnit: '一科',
        receiptDate: '108/5/28',
        id: '3',
      },
      {
        reviewType: '修正計畫',
        createDate: '108/11/26',
        caseName:
          '交通部陳報「前瞻基礎建設-城鄉建設-改善停車問題計畫」修正計畫（草案）乙案新北市政府函陳「淡水河北側沿和平面道路工程」公共建設計畫一案',
        status: '審查完成',
        adminOrg: '交通部',
        org: '交通部',
        contactPerson: '林清煌',
        classify: '工商設施',
        pccUnit: '三科',
        receiptDate: '108/5/1',
        id: '4',
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
      exportOds,
      toCreateView,
      toEditView,
      toInfoView,
      toReviewView,
      deleteData,
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

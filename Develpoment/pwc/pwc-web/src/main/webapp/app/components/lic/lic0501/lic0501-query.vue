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
              <i-form-group-check :label="$t('label.planName') + '：'" :item="$v.projectName" :labelStar="true">
                <b-form-input v-model="$v.projectName.$model" :state="validateState($v.projectName)">> </b-form-input>
              </i-form-group-check>
              <i-form-group-check> </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.planType') + '：'" :item="$v.projectCategory">
                <b-form-select :options="options.projectCategory" v-model="$v.projectCategory.$model"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.bos') + '：'" :item="$v.execOrg">
                <b-form-select :options="options.execOrg" v-model="$v.execOrg.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.dept') + '：'" :item="$v.adminOrg">
                <b-form-select :options="options.adminOrg" v-model="$v.adminOrg.$model"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.status') + '：'" :item="$v.projectStatus">
                <b-form-select :options="options.projectStatus" v-model="$v.projectStatus.$model"></b-form-select>
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
        <i-button type="file-earmark-plus" @click="toCreateView"></i-button>
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
          <i-button class="mt-1" type="pencil-square" @click="toEditView(row.item)"></i-button>
          <i-button class="mt-1" type="trash" @click="deleteProject(row.item.projectNo)"></i-button>
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
import { Project_basic } from '@/shared/model/project_basic';
import axios from 'axios';
import { required } from '@/shared/validators';

export default {
  name: 'lic0501Query',
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
      projectCategory: '', // 計畫類別
      execOrg: '', // 主辦機關
      adminOrg: '', // 主管機關
      projectStatus: '', // 計畫狀態
    };
    //表單物件驗證規則
    const rules = {
      projectName: { notNull: required },
      projectCategory: {},
      execOrg: {},
      adminOrg: {},
      projectStatus: {},
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
      projectCategory: [
        { value: '', text: '全部' },
        { value: 'A01', text: '公路' },
        { value: 'A02', text: '軌道運輸' },
        { value: 'A03', text: '航空' },
        { value: 'A04', text: '港埠' },
        { value: 'A05', text: '觀光' },
        { value: 'B01', text: '環境保護' },
        { value: 'B02', text: '水利建設' },
        { value: 'B03', text: '下水道' },
        { value: 'B04', text: '國家公園' },
        { value: 'C01', text: '工商設施' },
        { value: 'C02', text: '油電' },
        { value: 'D01', text: '都市開發' },
        { value: 'E01', text: '文化' },
        { value: 'F01', text: '教育' },
        { value: 'F02', text: '體育' },
        { value: 'G01', text: '農業建設' },
        { value: 'H01', text: '衛生福利' },
      ],
      execOrg: [
        { value: '', text: '全部' },
        { value: '300000000A', text: '行政院' },
        { value: '301000000A', text: '內政部' },
        { value: '301010000C', text: '內政部警政署' },
        { value: '305720000S', text: '海軍軍官學校' },
        { value: '307510000D', text: '財政部關稅總局' },
        { value: '315000000H', text: '交通部' },
      ],
      adminOrg: [
        { value: '', text: '全部' },
        { value: '300000000A', text: '行政院' },
        { value: '301000000A', text: '內政部' },
        { value: '301010000C', text: '內政部警政署' },
        { value: '305720000S', text: '海軍軍官學校' },
        { value: '307510000D', text: '財政部關稅總局' },
        { value: '315000000H', text: '交通部' },
      ],
      projectStatus: [
        { value: '', text: '全部' },
        { value: '0', text: '執行中' },
        { value: '1', text: '完成' },
        { value: '2', text: '中止' },
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
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectCategory',
          label: $i18n.t('label.planType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'execOrg',
          label: $i18n.t('label.bos'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'adminOrg',
          label: $i18n.t('label.dept'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectStatus',
          label: $i18n.t('label.status'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: $i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
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

    //轉到新增頁面
    const toCreateView = () => {
      navigateByNameAndParams('lic0501EditInfo', { formStatusForward: 'create' });
    };

    //轉到編輯頁面
    const toEditView = (project_basic: Project_basic) => {
      navigateByNameAndParams('lic0501EditInfo', { formStatusForward: 'modify', project_basic: project_basic, isNotKeepAlive: true });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    //刪除
    const deleteProject = (deleteId: string) => {
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
      $bvModal.msgBoxConfirm('確定刪除計畫基本資料檔？').then((isOK: boolean) => {
        $bvModal.msgBoxOk('資料刪除成功');
        handleQuery();
      });
    };
    const mockdata = [
      {
        projectName: '曾文南化聯通管工程計畫',
        projectCategory: 'B02',
        execOrg: '經濟部',
        adminOrg: '經濟部水利署',
        projectStatus: '執行中',
      },
      {
        projectName: '烏溪鳥嘴潭人工湖工程計畫',
        projectCategory: 'B02',
        execOrg: '經濟部',
        adminOrg: '經濟部水利署',
        projectStatus: '執行中',
      },
      {
        projectName: '臺南市先進運輸系統紅線規劃作業',
        projectCategory: 'A02',
        execOrg: '交通部',
        adminOrg: '臺南市捷運工程處',
        projectStatus: '執行中',
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
      toCreateView,
      toEditView,
      deleteProject,
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

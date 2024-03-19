<template>
  <div>
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
        <!--#cell為id,row代表這一列的資料-->
        <template #cell(action)="row">
          <b-button class="mt-1" variant="info" @click="openOpinionModal(row.item.id)">加註意見</b-button>
        </template>
      </i-table>
    </section>
    <b-modal id="remark-modal" size="lg" title="加註意見" header-bg-variant="secondary" header-text-variant="light" hide-footer>
      <template>
        <div class="container">
          <div class="card">
            <div class="card-body col-12">
              <b-form-row class="mt-3">
                <b-form-textarea class="col-12" v-model="reviewRemark" rows="4" maxlength="4000" placeholder="加註意見"></b-form-textarea>
              </b-form-row>
              <div class="button-float-right">
                <b-button-toolbar>
                  <i-button class="mt-3 ml-1" type="check-circle" @click="toaddOpinion()"></i-button>
                  <i-button class="mt-3 ml-1" type="arrow-counterclockwise" @click="$bvModal.hide('remark-modal'); reviewRemark = '';"></i-button>
                </b-button-toolbar>
              </div>
            </div>
          </div>
        </div>
      </template>
    </b-modal>
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
import i18n from '@/shared/i18n';
import axios from 'axios';
import { required } from '@/shared/validators';
import { formatDate } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'lic0401ReviewInfo',
  props: {
    licLicense: {
      type: Object,
      required: false,
    }
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
      reviewRemark: '', //意見
    };

    //表單物件驗證規則
    const rules = {
      projectName: {},
      adminOrg: {},
      licenseCategory: {},
      licenseName: {},
      reviewRemark: {},
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
          key: 'adminOrg',
          label: i18n.t('label.dept'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectName',
          label: i18n.t('label.planName'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'licName',
          label: i18n.t('label.licName'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'expectDate',
          label: i18n.t('label.predictDate'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },

        {
          key: 'reviewRemark',
          label: i18n.t('label.opinions'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 3, //TODO:假資料用
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


    let tempId = undefined;
    const reviewRemark = ref(''); 

    // 將 table id 傳入 modal
    const openOpinionModal = (id: string) => {
      tempId = id;
      $bvModal.show('remark-modal');
    };

    //modal 確認按鈕
    const toaddOpinion = (id: String) => {
      // 找 table id = 點選的 tempId ，再將輸入的值(reviewRemark.value)指定給reviewRemark欄位
      mockdata.find(e => e.id == tempId).reviewRemark = reviewRemark.value;
      $bvModal.hide('remark-modal');
      //指定給reviewRemark欄位後將 reviewRemark.value 清空
      reviewRemark.value = '';
      tempId = '';
    };

    

    const mockdata = [
      {
        adminOrg: '台北市政府捷運工程局',
        projectName: '台北都會區大眾捷運系統工程計畫',
        licName: '1.河防安全影響評估報告',
        expectDate: '2021-09-30',
        reviewRemark: '',
        id: '0',
      },
      {
        adminOrg: '台北市政府捷運工程局',
        projectName: '台北都會區大眾捷運系統工程計畫',
        licName: '排水計劃書',
        expectDate: '2021-09-30',
        reviewRemark: '',
        id: '1',
      },
      {
        adminOrg: '台北市政府捷運工程局',
        projectName: '台北都會區大眾捷運系統工程計畫-環狀線周邊土地開發',
        licName: '景美溪安全影響評估報告',
        expectDate: '2023-07-01',
        reviewRemark: '',
        id: '2',
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
      toaddOpinion,
      reviewRemark,
      openOpinionModal,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

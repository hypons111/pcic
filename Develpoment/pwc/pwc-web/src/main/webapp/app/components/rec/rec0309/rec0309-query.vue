<template>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
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
              <i-form-group-check :labelStar="true" :label="$t('label.typhoonName') + ':'" :item="$v.typhoonName">
                <b-form-select v-model="$v.typhoonNo.$model" :options="queryOptions.typhoon"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.projectCodes') + ':'" :item="$v.projectCodes">
                <b-form-select v-model="$v.projectCode.$model" :options="queryOptions.projectCode"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.recAmount') + ':'" :item="$v.recAmount">
                <b-form-select v-model="$v.applyAmount.$model" :options="queryOptions.amount"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.orgName') + ':'" :item="$v.orgName">
                <b-form-select v-model.trim="$v.orgName.$model" :options="queryOptions.orgName"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.city') + ':'" :item="$v.city">
                <b-form-select v-model.trim="$v.applyOrgId.$model" :options="queryOptions.orgId"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.applyStatus') + ':'" :item="$v.applyStatus">
                <b-form-select v-model="$v.projectStatus.$model" :options="queryOptions.projectStatus"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.engName') + ':'" :item="$v.engName">
                <b-form-input v-model.trim="$v.projectName.$model"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-12" label-cols="1" content-cols="11">
                <b-form-checkbox v-model="$v.isReviewChangeAmount.$model" :true-value="true" :false-value="false"
                  >{{ $t('label.reviewChangeProject') }}　</b-form-checkbox
                >
                <b-form-checkbox v-model="$v.isSurveyPlan.$model" :true-value="true" :false-value="false">{{
                  $t('label.surveyPlanProject')
                }}</b-form-checkbox>
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

    <section>
      <div class="card-body col-12">
        <b-form-row class="float-left col-4">
          <i-form-group-check :label="$t('label.reportFormat')" :item="$v.reportFormat" class="col-12">
            <b-form-select
              v-model="$v.reportFormat.$model"
              :options="queryOptions.reportFormat1"
              v-if="optionVisible.option1"
            ></b-form-select>
            <b-form-select
              v-model="$v.reportFormat.$model"
              :options="queryOptions.reportFormat2"
              v-if="optionVisible.option2"
            ></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <b-button-toolbar>
          <!-- 匯出按鈕 -->
          <i-button class="mt-1 ml-1" type="box-arrow-up-right" @click="exportOds"></i-button>
          <b-button class="mt-1 ml-1" variant="info" @click="toRec0312U1View()">審查排序送出</b-button>
          <b-button class="mt-1 ml-1" variant="info" @click="toRec0313U1View()">審查彙整提報</b-button>
          <b-button class="mt-1 ml-1" variant="info" @click="toRec0312U2View()">審議排序送出</b-button>
          <b-button class="mt-1 ml-1" variant="info" @click="toRec0313U2View()">審議彙整提報</b-button>
        </b-button-toolbar>
      </div>
    </section>
    <!-- i-table -->
    <section>
      <div class="container mt-3" v-if="queryStatus">
        <i-table
          class="table-sm"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :fields="table.fields"
          :totalItems="table.totalItems"
          :is-server-side-paging="true"
          @changePagination="handlePaginationChanged($event)"
        >
          <template #cell(action)="row">
            <i-button class="mt-1" type="binoculars" @click="toRec0304R1View(row.item)"></i-button>
            <b-button class="mt-1 ml-1" variant="info" @click="toRec0311U1View(row.item)">審查</b-button>
            <b-button class="mt-1 ml-1" variant="info" @click="toRec0311U2View(row.item)">審議</b-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, ref, watch, onMounted } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import { RecProject } from '@/shared/model/recModel/rec-project.model';
import axios from 'axios';
import { required } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import iButton from '@/shared/buttons/i-button.vue';
import { useGetters } from '@u3u/vue-hooks';
import { commaFormatter } from '@/shared/formatter/common';

export default {
  components: { iButton },
  name: 'rec0309Query',
  props: {
    isReload: {
      required: false,
    },
  },
  setup() {
    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    onMounted(() => {
      getOrgRole();
      getRoleId();
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      typhoonNo: '',
      applyOrgId: '',
      orgName: '',
      projectCode: '',
      applyAmount: 0,
      projectName: '',
      projectStatus: '',
      isReviewChangeAmount: '',
      isSurveyPlan: '',
      reportFormat: '',
      id: '',
    };

    const rules = {
      typhoonNo: {},
      applyOrgId: {},
      orgName: {},
      projectCode: {},
      applyAmount: {},
      projectName: {},
      projectStatus: {},
      isReviewChangeAmount: {},
      isSurveyPlan: {},
      reportFormat: {},
    };

    // 下拉選單選項
    const queryOptions = reactive({
      typhoon: [
        { value: '', text: '全部' },
        { value: 'Typ22050410200590107', text: '111年5月颱風' },
      ],
      orgId: [
        { value: '', text: '全部' },
        { value: '301060000C', text: '新北市政府' },
      ],
      projectCode: [
        { value: '', text: '全部' },
        { value: 'A1', text: 'A1-水利工程' },
        { value: 'B1', text: 'B1-觀光工程' },
        { value: 'C1', text: 'C1-道路橋梁工程-公路系統工程' },
        { value: 'C2', text: 'C2-道路橋梁工程-村里聯絡道路工程' },
      ],
      amount: [
        { value: '0', text: '全部' },
        { value: '1000', text: '一千萬以下' },
        { value: '5000', text: '一千萬至五千萬' },
        { value: '5001', text: '五千萬以上' },
      ],
      projectStatus: [
        { value: '', text: '全部' },
        { value: '6', text: '未送出' },
        { value: '5', text: '地方業管單位審核中' },
        { value: '4', text: '地方管理員審核中' },
        { value: '3', text: '中央業管單位審核中' },
        { value: '2', text: '中央管理員審核中' },
        { value: '1', text: '經費審議小組業管單位審核中' },
        { value: '0', text: '經費審議小組管理員審核中' },
        { value: '-1', text: '執行中' },
        { value: '-2', text: '已刪除' },
      ],
      orgName: [
        { value: '', text: '請選擇' },
        { value: '1', text: 'orgName' },
      ],
      reportFormat1: [
        { value: '', text: '請選擇' },
        { value: '1', text: '補助明細表' },
      ],
      reportFormat2: [
        { value: '', text: '請選擇' },
        { value: '1', text: '補助明細表' },
        { value: '2', text: '審議總表' },
        { value: '3', text: '工程類別統計表' },
        { value: '4', text: '工程經費統計表' },
      ],
    });

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault, ['typhoonNo']);

    const notificationService: NotificationService = useNotification();

    const allOrgRole = ref([]);

    const getOrgRole = () => {
      axios
        .get('/rec-user-project-codes/get-rec-org-role')
        .then(({ data }) => {
          allOrgRole.value.splice(0, allOrgRole.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const table = reactive({
      fields: [
        {
          key: 'applySubmitNo',
          label: $i18n.t('label.applySubmitNo'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'approveNo',
          label: $i18n.t('label.approveNo'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectCode',
          label: $i18n.t('label.projectCodes'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectName',
          label: $i18n.t('label.engName'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'orgName',
          label: $i18n.t('label.orgName'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'city',
          label: $i18n.t('label.city'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'village',
          label: $i18n.t('label.village'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'applyAmount',
          label: $i18n.t('label.recAmounts'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-right align-middle',
          formatter: value => commaFormatter(value),
        },
        {
          key: 'reviewAmount',
          label: $i18n.t('label.reviewSuggestAmount'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-right align-middle',
          formatter: value => commaFormatter(value),
        },
        {
          key: 'projectStatus',
          label: $i18n.t('label.status'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: $i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-right align-middle',
        },
      ],
      data: undefined,
      totalItems: 3, //TODO:mockdata
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    //顯示查詢條件區
    const stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //登入角色
    const optionVisible = ref({
      option1: true,
      option2: false,
    });

    const roleId1 = ['REC_30', 'REC_31', 'REC_20'];
    const roleId2 = ['REC_10', 'REC_00'];

    // 取得登入帳號(PWB_ROLE_APPROVE.USER_ID)
    const loginUserId = ref(useGetters(['account']).account.value.id);

    // 查詢登入帳號所擁有的角色(ADM_ACCOUNT_ROLE.ROLE_ID)
    const getRoleId = () => {
      form.id = loginUserId.value;

      // axios.post('/adm-account-role/getRoleId', form).then(({ data }) => {
      //   for (let roleId of data) {
      //     console.log("roleId:"+roleId)
      //      if (roleId1.indexOf(roleId) == -1) {
      //         optionVisible.option1 == false;
      //         optionVisible.option2 == true;
      //      } if (roleId2.indexOf(roleId) == -1) {
      //         optionVisible.option1 == true;
      //         optionVisible.option2 == false;
      //      } else {
      //         optionVisible.option1 == true;
      //         optionVisible.option2 == false;
      //      }
      //   }
      // });
    };

    //轉到REC0312U1 災後復建審議-審查排序送出畫面
    const toRec0312U1View = (recProject: any) => {
      navigateByNameAndParams('rec0312U1', {
        formStatusForward: 'modify',
        recProject: recProject,
        isNotKeepAlive: true,
      });
    };

    //轉到REC0313U1 災後復建審議-審查彙整提報畫面
    const toRec0313U1View = (recProject: any) => {
      navigateByNameAndParams('rec0313U1', {
        formStatusForward: 'modify',
        recProject: recProject,
        isNotKeepAlive: true,
      });
    };

    //轉到REC0312U2 災後復建審議-審議排序送出畫面
    const toRec0312U2View = (recProject: any) => {
      navigateByNameAndParams('rec0312U2', {
        formStatusForward: 'modify',
        recProject: recProject,
        isNotKeepAlive: true,
      });
    };

    //轉到REC0313U2 災後復建審議-審議彙整提報畫面
    const toRec0313U2View = (recProject: any) => {
      navigateByNameAndParams('rec0313U2', {
        formStatusForward: 'modify',
        recProject: recProject,
        isNotKeepAlive: true,
      });
    };

    //轉到檢視頁面
    const toRec0304R1View = (recProject: any) => {
      navigateByNameAndParams('rec0304-view-project', {
        formStatusForward: 'modify',
        recProject: recProject,
        isNotKeepAlive: true,
      });
    };

    //轉到審查頁面
    const toRec0311U1View = (recProject: any) => {
      navigateByNameAndParams('rec0311U1', {
        formStatusForward: 'modify',
        recProject: recProject,
        isNotKeepAlive: true,
      });
    };

    //轉到審議頁面
    const toRec0311U2View = (recProject: any) => {
      navigateByNameAndParams('rec0311U2', {
        formStatusForward: 'modify',
        recProject: recProject,
        isNotKeepAlive: true,
      });
    };

    //依條件查詢復建工程
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          table.data = [];
          table.data.splice(0, table.data.length, ...mockdata);

          // axios //TODO:fix post api
          //   .post('/rec-projects/criteria-jpa', form)
          //   .then(({ data }) => {
          //     table.data = data.content.slice(0, data.content.length);
          //     table.totalItems = data.totalElements;
          //   })
          //   .finally()
          //   .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    watch(allOrgRole, () => {
      queryOptions.orgName = allOrgRole.value.map(data => {
        //map回傳新的格式
        return { value: data['orgId'], text: data['orgName'] };
      });
      queryOptions.orgName.unshift({ text: '請選擇', value: '' }); //添加一個或多個元素至陣列的開頭
    });

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

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

    const mockdata = [
      {
        applySubmitNo: '001',
        approveNo: '',
        projectCode: 'G1',
        projectName: '鹿谷鄉竹林村田頭巷旁野溪災修復建工程',
        orgName: '南投縣政府',
        city: '鹿谷鄉',
        village: '竹林村',
        applyAmount: '1670',
        reviewAmount: '1670',
        projectStatus: '中央業管單位審核中 2021.08.04 18:16',
      },
      {
        applySubmitNo: '002',
        approveNo: '',
        projectCode: 'G1',
        projectName: '鹿谷鄉秀峰村崛底橋下游護岸災修復建工程',
        orgName: '南投縣政府',
        city: '鹿谷鄉',
        village: '秀峰村',
        applyAmount: '9980',
        reviewAmount: '9980',
        projectStatus: '中央業管單位審核中 2021.08.04 18:16',
      },
      {
        applySubmitNo: '003',
        approveNo: '',
        projectCode: 'G1',
        projectName: '魚池鄉中明村7鄰排水溝災修復建工程',
        orgName: '南投縣政府',
        city: '魚池鄉',
        village: '中明村',
        applyAmount: '360',
        reviewAmount: '360',
        projectStatus: '中央業管單位審核中 2021.08.04 18:16',
      },
    ];

    return {
      $v,
      stepVisible,
      validateState,
      optionVisible,
      getRoleId,
      checkValidity,
      queryStatus,
      handleQuery,
      reset,
      handlePaginationChanged,
      queryOptions,
      table,
      exportOds,
      toRec0312U1View,
      toRec0313U1View,
      toRec0312U2View,
      toRec0313U2View,
      toRec0304R1View,
      toRec0311U1View,
      toRec0311U2View,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

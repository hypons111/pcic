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
        <div class="card-body col-11">
          <b-collapse v-model="stepVisible">
            <b-form-row>
              <i-form-group-check :labelStar="true" label="請選擇欲申請的颱風:" :item="$v.typhoonNo">
                <b-form-select v-model="$v.typhoonNo.$model" :options="queryOptions.typhoonName" :state="validateState($v.typhoonNo)"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="工程類別:">
                <b-form-select v-model="$v.projectCode.$model" :options="queryOptions.projectCode"></b-form-select>
              </i-form-group-check>
              <i-form-group-check label="復建經費:">
                <b-form-select v-model="$v.applyAmount.$model" :options="queryOptions.amount"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="機關名稱:">
                <b-form-select v-model.trim="$v.applyOrgId.$model" :options="queryOptions.applyOrgIdList"></b-form-select>
              </i-form-group-check>
              <i-form-group-check label="鄉(鎮市):">
                <b-form-select v-model.trim="$v.city.$model" :options="queryOptions.city"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="工程名稱:">
                <b-form-input v-model.trim="$v.projectName.$model"></b-form-input>
              </i-form-group-check>
              <i-form-group-check label="變更案件類別:">
                <b-form-select v-model="$v.actionType.$model" :options="queryOptions.actionType"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="變更案件狀態:">
                <b-form-select v-model="$v.actionStatus.$model" :options="queryOptions.actionStatus"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-12" label-cols="1" content-cols="11">
                <b-form-checkbox v-model="$v.isReviewChangeAmount.$model" :true-value="true" :false-value="false">基本設計審查應送審而未送審工程　</b-form-checkbox>
                <b-form-checkbox v-model="$v.isSurveyPlan.$model" :true-value="true" :false-value="false">屬調查規劃費用之案件　</b-form-checkbox>
                <b-form-checkbox v-model="$v.isCheckAmount.$model" :true-value="true" :false-value="false">屬結案但保留部分費用</b-form-checkbox>
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
        <b-button variant="info" @click="toExpList">匯出(查詢清單)</b-button>
        <b-button variant="info" @click="toCostBatch">調整經費整批送出</b-button>
        <b-button variant="info" @click="toExtendBatch">完工展延整批送出</b-button>
      </div>
    </section>
    <!-- i-table -->
    <section>
      <div class="container" v-if="queryStatus">
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
            <i-button class="mt-1" type="binoculars" @click="toEditView(row.item)"></i-button>
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
  name: 'rec0401Query',
  props: {
    isReload: {
      required: false,
    },
  },
  setup() {
    onActivated(() => {
      // if (isReloadProp.value) {
      //   handleQuery();
      // }
    });

    onMounted(() => {
      getTyphoon();
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      orgId:'',
      typhoonNo: '',
      applyEndTime: '',
      applyOrgId: '',
      projectCode: '',
      city: '',
      applyAmount: 0,
      projectName: '',
      actionType: '',
      actionStatus: '',
      isReviewChangeAmount: '',
      isSurveyPlan: '',
      isCheckAmount: '',
    };

    const rules = {
      orgId:{},
      typhoonNo: {notNull: required},
      applyEndTime: {},
      applyOrgId: {},
      projectCode: {},
      city: {},
      applyAmount: {},
      projectName: {},
      actionType: {},
      actionStatus: {},
      isReviewChangeAmount: {},
      isSurveyPlan: {},
      isCheckAmount: {},
    };

    // 下拉選單選項
    const queryOptions = reactive({
      applyOrgIdList: [
        { value: '', text: '全部' },
        { value: '1', text: 'orgName' },
      ],
      projectCode: [
        { value: '', text: '全部' },
        { value: 'A1', text: 'A1：水利工程' },
        { value: 'B1', text: 'B1：觀光工程' },
        { value: 'C1', text: 'C1：道路橋梁工程-公路系統工程' },
        { value: 'C2', text: 'C2：道路橋梁工程-村里聯絡道路工程' },
      ],
      amount: [
        { value: '0', text: '全部' },
        { value: '999', text: '一千萬以下' },
        { value: '4999', text: '一千萬至五千萬' },
        { value: '5000', text: '五千萬以上' },
      ],
      actionType: [
        { value: '', text: '全部' },
        { value: '1', text: '1：經費調整' },
        { value: '2', text: '2：概念設計審查' },
        { value: '3', text: '3：工程變更或註銷' },
        { value: '4', text: '4：完工展延' },
      ],
      actionStatus: [
        { value: '', text: '全部' },
        { value: '-10', text: '.  -10：暫存' },
        { value: '-5', text: '　-5：申請中' },
        { value: '-1', text: '　-1：退回' },
        { value: '0', text: '　 0：不同意' },
        { value: '1', text: '　 1：同意' },
      ],
      city: [
        { value: '', text: '請選擇' },
        { value: '1', text: 'city' },
      ],
      typhoonName: [
        { value: '', text: '請選擇', date: '' },
        { value: '1', text: 'typhoonName', date: '' },
      ],
    });

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;
    const orgIdEnd = userIdentity.indexOf('-');
    const orgId = userIdentity.substr(0,orgIdEnd);
    form.orgId = orgId;

    const allTyphoon = ref([]);
    const getTyphoon = () => {
      axios
        .get(`/rec-projects/get-project-typhoon/${orgId}`)
        .then(({ data }) => {
          allTyphoon.value.splice(0, allTyphoon.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const allProjectCode = ref([]);
    const getProjectCode = () => {
      axios
        .get(`/rec-projects/get-project-code/${form.typhoonNo}/${orgId}`)
        .then(({ data }) => {
          allProjectCode.value.splice(0, allProjectCode.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const allOrgRole = ref([]);
    const getOrgRole = () => {
      axios
        .get(`/rec-projects/get-project-org-role/${form.typhoonNo}/${orgId}`)
        .then(({ data }) => {
          allOrgRole.value.splice(0, allOrgRole.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const allCity = ref([]);
    const getCity = () => {
      axios
        .get(`/rec-projects/get-project-city/${form.typhoonNo}/${orgId}`)
        .then(({ data }) => {
          allCity.value.splice(0, allCity.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const table = reactive({
      fields: [
        {
          key: 'orgName',
          label: '機關名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectCode',
          label: '工程類別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'approveNo',
          label: '核定序號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectName',
          label: '復建工程名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'city',
          label: '鄉(鎮市)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'village',
          label: '村(里)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'applyAmount',
          label: '經費',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: value => commaFormatter(value),
        },
        {
          key: 'actionType',
          label: '變更案件類別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'actionDescript',
          label: '工程狀況',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: $i18n.t('label.action'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
      ],
      data: undefined,
      totalItems: 2,
    });
    // 查詢結果及狀態
    const queryStatus = ref(false);
    //顯示查詢條件區
    let stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //轉到編輯頁面
    const toEditView = (recProject: any) => {
      navigateByNameAndParams('rec0403-edit-info', { formStatusForward: 'modify', recProject: recProject, isNotKeepAlive: true });
    };

    //轉到調整經費整批送出
    const toCostBatch = () => {
      navigateByNameAndParams('rec0407-query', { fisNotKeepAlive: true });
    };

    //轉到完工展延整批送出
    const toExtendBatch = () => {
      navigateByNameAndParams('rec0410-query', { fisNotKeepAlive: true });
    };

    //依條件查詢復建工程
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
      //   if (isValid) {
      //     //重置條件
      //     table.data = undefined;
      //     axios
      //       .post('/rec-projects/criteria-jpa', form)
      //       .then(({ data }) => {
      //         table.data = data.content.slice(0, data.content.length);
      //         table.totalItems = data.totalElements;
      //       })
      //       .finally()
      //       .catch(notificationErrorHandler(notificationService));
      //   }
      // });
      queryStatus.value=true;
      table.data = [
        {
          orgName : '南投縣政府',
          projectCode : 'A1',
          approveNo : '014',
          projectName : '109年5月豪雨-南投市漳興里復興路、和興坑排水幹線護岸災修復建工程',
          city : '南投市',
          village : '彰興里',
          applyAmount : '9,852',
          actionType :'完工展廷',
          actionDescript :'逾期未完工',
        },
        {
          orgName : '台中市政府',
          projectCode : 'A1',
          approveNo : '014',
          projectName : '110年5月豪雨-台中市北屯區復興路、和興坑排水幹線護岸災修復建工程',
          city : '台中市',
          village : '北屯區',
          applyAmount : '6,152',
          actionType :'完工展廷',
          actionDescript :'逾期未完工',
        },
      ]
    })};

    watch(allTyphoon, () => {
      queryOptions.typhoonName = allTyphoon.value.map(data => {
        //map回傳新的格式
        return { value: data['typhoonNo'], text: data['typhoonName'], date: data['applyEndTime'] };
      });
      queryOptions.typhoonName.unshift({ text: '請先選擇颱風，再做其他操作', value: '', date:'' }); //添加一個或多個元素至陣列的開頭
    });

    watch(allProjectCode, () => {
      queryOptions.projectCode = allProjectCode.value.map(data => {
        return { value: data['projectCode'], text: data['projectCode']+'：'+data['projectCodeName'] };
      });
      queryOptions.projectCode.unshift({ text: '請選擇', value: '' }); //添加一個或多個元素至陣列的開頭
    });

    watch(allOrgRole, () => {
      queryOptions.applyOrgIdList = allOrgRole.value.map(data => {
        return { value: data['applyOrgId'], text: data['orgName'] };
      });
      queryOptions.applyOrgIdList.unshift({ text: '請選擇', value: '' }); //添加一個或多個元素至陣列的開頭
    });

    watch(allCity, () => {
      queryOptions.city = allCity.value.map(data => {
        return { value: data['city'], text: data['city'] };
      });
      queryOptions.city.unshift({ text: '請選擇', value: '' }); //添加一個或多個元素至陣列的開頭
    });

    //颱風改變，即時更改所有選項
    watch(form, () => {
      getProjectCode();
      getOrgRole();
      getCity();
    });

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      $v,
      stepVisible,
      queryStatus,
      validateState,
      checkValidity,
      handleQuery,
      reset,
      handlePaginationChanged,
      queryOptions,
      table,
      toEditView,
      toCostBatch,
      toExtendBatch,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

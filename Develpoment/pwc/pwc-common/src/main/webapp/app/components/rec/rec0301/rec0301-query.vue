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
              <i-form-group-check label="提報期限:">
                <i-date-picker disabled v-model.trim="$v.applyEndTime.$model" :state="$v.applyEndTime"></i-date-picker>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="工程類別:" :item="$v.projectCode">
                <b-form-select v-model="$v.projectCode.$model" :options="queryOptions.projectCode" ></b-form-select>
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
              <i-form-group-check label="申請狀況:">
                <b-form-select v-model="$v.projectStatus.$model" :options="queryOptions.projectStatus"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-12" label-cols="1" content-cols="11">
                <b-form-checkbox v-model="$v.isReviewChangeAmount.$model" :true-value="true" :false-value="false">經審議後建議變更類別之案件　</b-form-checkbox>
                <b-form-checkbox v-model="$v.isSurveyPlan.$model" :true-value="true" :false-value="false">屬調查規劃費用之案件</b-form-checkbox>
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
        <b-button variant="info" @click="toSortView">排序送出</b-button>
        <b-button variant="info" @click="toCenterView">提報中央</b-button>
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
            <i-button class="mt-1" type="pencil-square" @click="toEditView(row.item)"></i-button>
            <i-button class="mt-1" type="trash" @click="deleteProject(row.item.projectNo)"></i-button>
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
  name: 'rec0301Query',
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
      projectStatus: '',
      isReviewChangeAmount: '',
      isSurveyPlan: '',
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
      projectStatus: {},
      isReviewChangeAmount: {},
      isSurveyPlan: {},
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
      projectStatus: [
        { value: '', text: '全部' },
        { value: '6', text: '6：未送出' },
        { value: '5', text: '5：地方業管單位審核中' },
        { value: '4', text: '4：地方管理員審核中' },
        { value: '3', text: '3：中央業管單位審核中' },
        { value: '2', text: '2：中央管理員審核中' },
        { value: '1', text: '1：經費審議小組業管單位審核中' },
        { value: '0', text: '0：經費審議小組管理員審核中' },
        { value: '-1', text: '-1：執行中' },
        { value: '-2', text: '-2：已刪除' },
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

    const allStatus = ref([]);
    const getStatus = () => {
      axios
        .get(`/rec-projects/get-project-status/${form.typhoonNo}/${orgId}`)
        .then(({ data }) => {
          allStatus.value.splice(0, allStatus.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const table = reactive({
      fields: [
        {
          key: 'applySubmitNo',
          label: '提報序號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'approveNo',
          label: '核列序號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectCode',
          label: '工程代碼',
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
          label: '受災鄉(鎮市)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'village',
          label: '受災村(里)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'applyAmount',
          label: '復建經費(千元)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: value => commaFormatter(value),
        },
        {
          key: 'reviewAmount',
          label: '審議建議經費(千元)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: value => commaFormatter(value),
        },
        {
          key: 'projectStatusName',
          label: '狀態',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'statusChangeDate',
          label: '此次狀態變更時間',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: value => formatDate(new Date(value), '/'),
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
      totalItems: 0,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    //顯示查詢條件區
    let stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //轉到新增頁面
    const toCreateView = () => {
      navigateByNameAndParams('rec0301-create-project', { formStatusForward: 'create', recTyphoonNo: form.typhoonNo ,isNotKeepAlive: true  });
    };

    //轉到排序送出頁面
    const toSortView = () => {
      navigateByNameAndParams('rec0307-sort-project', { recTyphoonNo: form.typhoonNo ,isNotKeepAlive: true  });
    };

    //轉到提報中央頁面
    const toCenterView = () => {
      navigateByNameAndParams('rec0308-center-project', { recTyphoonNo: form.typhoonNo ,isNotKeepAlive: true  });
    };

    //轉到編輯頁面
    const toEditView = (recProject: any) => {
      navigateByNameAndParams('rec0301-edit-info', { formStatusForward: 'modify', recProject: recProject, isNotKeepAlive: true });
    };

    //依條件查詢復建工程
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/rec-projects/criteria-jpa', form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

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

    watch(allStatus, () => {
      queryOptions.projectStatus = allStatus.value.map(data => {
        return { value: data['projectStatus'], text: data['projectStatus']+'：'+data['projectStatusName'] };
      });
      queryOptions.projectStatus.unshift({ text: '請選擇', value: '' }); //添加一個或多個元素至陣列的開頭
    });

    //颱風改變，即時更改所有選項
    watch(form, () => {
      //更新提報期限
      for (let i=0;i< queryOptions.typhoonName.length;i++ ){
        if (queryOptions.typhoonName[i].value===form.typhoonNo){
          form.applyEndTime=queryOptions.typhoonName[i].date;
        }
      }

      getProjectCode();
      getOrgRole();
      getCity();
      getStatus();
    });

    //刪除復建工程
    const deleteProject = (deleteId: string) => {
      $bvModal.msgBoxConfirm('刪除此工程將連同相關基本資料、災損圖片、概念設計示意圖說文件、復建工項等資料一併刪除，確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          let url: string;
          url = `/rec-projects/` + deleteId;
          axios
            .delete(url)
            .then($bvModal.msgBoxOk('資料刪除成功'))
            .finally(handleQuery())
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      $v,
      stepVisible,
      validateState,
      checkValidity,
      queryStatus,
      handleQuery,
      reset,
      handlePaginationChanged,
      queryOptions,
      table,
      toEditView,
      toCreateView,
      toSortView,
      toCenterView,
      deleteProject,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

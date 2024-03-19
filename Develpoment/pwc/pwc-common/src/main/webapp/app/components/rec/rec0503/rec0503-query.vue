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
              <!-- 帳號 -->
              <i-form-group-check :label="$t('label.accountId') + ':'" :item="$v.userId">
                <b-form-input v-model="$v.userId.$model" :state="validateState($v.userId)" maxlength="20"> </b-form-input>
              </i-form-group-check>
              <!-- 帳號名稱 -->
              <i-form-group-check :label="$t('label.accountName') + ':'" :item="$v.chName">
                <b-form-input v-model="$v.chName.$model" :state="validateState($v.chName)" maxlength="20"> </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 帳號狀態 -->
              <i-form-group-check :label="$t('label.accountStatus')" :item="$v.status">
                <b-form-select :options="options.status" v-model="$v.status.$model"></b-form-select>
              </i-form-group-check>
              <!-- 帳號權限 -->
              <i-form-group-check :label="$t('label.accountPermit')" :item="$v.roleName">
                <b-form-select :options="options.roleName" v-model="$v.roleName.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 所屬機關 -->
              <i-form-group-check :label="$t('label.belongOrgan')" :item="$v.orgName">
                <b-form-select :options="options.orgName" v-model="$v.orgName.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="'登入者ID(測試用)'" :item="$v.loginId">
                <b-form-select :options="options.loginId" v-model="$v.loginId.$model"> </b-form-select>
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
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, ref, onMounted, watch } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import { RecUserProjectCode } from '@/shared/model/recModel/rec-user-project-code.model';
import axios from 'axios';
import { required, required as vuelidateRequired, and, requiredUnless } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { admAccountStatusFormatter } from '@/shared/formatter/common';
import { helpers, requiredIf as vuelidateRequiredIf } from '@vuelidate/validators';
import { oneNotNull } from '@/shared/validators/with-message/atleast-one-not-null';

export default {
  name: 'rec0503Query',
  props: {
    isReload: {
      required: false,
    },
  },
  setup() {
    onMounted(() => {
      getOrgRole();
      getAdmRole();
    });

    const requiredIf = (prop: boolean | string | (() => boolean | Promise<boolean>)) =>
      helpers.withMessage('請輸入帳號或帳號名稱', vuelidateRequiredIf(prop));

    const allOrgRole = ref([]);

    const allAdmRole = ref([]);

    const getOrgRole = () => {
      axios
        .get('/rec-user-project-codes/get-rec-org-role')
        .then(({ data }) => {
          allOrgRole.value.splice(0, allOrgRole.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const getAdmRole = () => {
      axios
        .get('/rec-user-project-codes/get-rec-adm-role')
        .then(({ data }) => {
          allAdmRole.value.splice(0, allAdmRole.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      userId: '',
      chName: '',
      orgName: '',
      roleName: '',
      status: '',
      loginId: '3.60.1-39',
    };

    const form = reactive(Object.assign({}, formDefault));

    const isFormEmpty: boolean = () => {
      if (form.chName.length === 0 && form.userId.length === 0) {
        return true;
      } else {
        return false;
      }
    };

    const rules = {
      userId: {
        //requiredIf當條件為true的時候必填
        requiredIf: requiredIf(() => !form.chName),
      },
      chName: { requiredIf: requiredIf(() => !form.userId) },
      orgName: {},
      roleName: {},
      status: {},
      loginId: {},
    };

    const options = reactive({
      status: [
        { value: '', text: '請選擇' },
        { value: 'Y', text: '啟用' },
        { value: 'N', text: '停用' },
      ],
      roleName: [
        { value: '', text: '請選擇' },
        { value: '1', text: 'roleName1' },
        { value: '2', text: 'roleName2' },
      ],
      orgName: [
        { value: '', text: '請選擇' },
        { value: '1', text: 'orgName' },
        { value: '2', text: 'orgName' },
      ],
      loginId: [
        { value: '3.60.1-39', text: '模組管理員(00)帳號' },
        { value: '3.60.3-38', text: '中央管理員(20)帳號' },
        { value: '3.60.1-41', text: '地方管理員(40) 帳號' },
      ],
    });

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault, ['userId', 'chName']);

    const resetWithoutClean = () => {
      setTimeout($v.value.$reset);
    };

    const notificationService: NotificationService = useNotification();

    const table = reactive({
      fields: [
        {
          key: 'userId',
          label: $i18n.t('label.accountId'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'chName',
          label: $i18n.t('label.accountName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'orgName',
          label: $i18n.t('label.belongOrgan'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'contactInformationTel',
          label: $i18n.t('label.contactInformationTel'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'roleName',
          label: $i18n.t('label.accountPermit'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'emailAddress',
          label: $i18n.t('label.emailAddress'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'status',
          label: $i18n.t('label.accountStatus'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: value => admAccountStatusFormatter(value),
        },
        {
          key: 'action',
          label: $i18n.t('label.action'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
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

    //轉到編輯頁面
    const toEditView = (recUserProjectCode: RecUserProjectCode) => {
      navigateByNameAndParams('rec0503-edit-info', {
        loginIdObj: { loginId: form.loginId }, //最後要改回直接抓登入帳號ID
        recUserProjectCode: recUserProjectCode,
        isNotKeepAlive: true,
      });
    };

    //依條件查詢帳號
    const handleQuery = () => {
      if (isFormEmpty() === false) {
        resetWithoutClean();
      }
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/rec-user-project-codes/criteria-jpa', form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    watch(allOrgRole, () => {
      options.orgName = allOrgRole.value.map(data => {
        //map回傳新的格式
        return { value: data['orgId'], text: data['orgName'] };
      });
      options.orgName.unshift({ text: '請選擇', value: '' }); //添加一個或多個元素至陣列的開頭
    });

    watch(allAdmRole, () => {
      options.roleName = allAdmRole.value.map(data => {
        return { value: data['roleId'], text: data['roleName'] };
      });
      options.roleName.unshift({ text: '請選擇', value: '' });
    });

    return {
      $v,
      stepVisible,
      validateState,
      checkValidity,
      queryStatus,
      handleQuery,
      reset,
      handlePaginationChanged,
      options,
      table,
      toEditView,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

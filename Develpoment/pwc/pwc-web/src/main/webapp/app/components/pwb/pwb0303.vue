<template>
  <div>
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
              <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>

        <div class="card-body col-11">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <!-- 模組 -->
              <i-form-group-check :label="$t('label.moduleType') + '：'">
                <span>災後復建工程經費審議及執行資訊系統</span>
              </i-form-group-check>
              <!-- 狀態 -->
              <i-form-group-check :label="$t('label.status') + '：'">
                <b-form-select v-model="$v.status.$model" :options="formOptions.status">
                  <template #first>
                    <option value="">全部</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 機關名稱 -->
              <i-form-group-check :label="$t('label.orgName') + '：'">
                <b-form-input v-model="$v.orgName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="clickQueryBtn"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2" v-if="queryStatus">
      <i-table
        class="table-sm"
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >
        <template #cell(action)="row">
          <b-button size="sm" variant="outline-secondary" v-show="row.item.status === '3'" @click="cancelApply(row.item)">
            {{ $t('button.cancel') }}
          </b-button>
          <b-button size="sm" variant="outline-secondary" v-show="row.item.status === '1'" @click="approve(row.item)">
            {{ $t('button.clipboard-plus') }}
          </b-button>
          <b-button size="sm" variant="outline-secondary" v-show="row.item.status === '1'" @click="notApprove(row.item)">
            {{ $t('button.notApprove') }}
          </b-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, reactive, ref } from '@vue/composition-api';
import { useGetters } from '@u3u/vue-hooks';
import { Pagination } from '@/shared/model/pagination.model';
import { useValidation } from '@/shared/form';,
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { useBvModal } from '@/shared/modal';
import NotificationService from '@/shared/notification/notification-service';
import i18n from '@/shared/i18n';
import axios from 'axios';


export default {
  name: 'pwb0303',
  setup() {

    onMounted(() => {
      getAdmAccount();
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      moduleType: '', //模組名稱
      status: '', //狀態
      orgName: '', //機關名稱
      pccOrgId: '', //機關代碼
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      moduleType: {},
      status: {},
      orgName: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    const table = reactive({
      fields: [
        {
          // 單位
          key: 'orgName',
          label: i18n.t('label.unit'),
        },
        {
          // 帳號類別
          key: 'userType',
          label: i18n.t('label.type'),
          formatter: (value: string) => formOptionsFormatter(formOptions.businessType, value),
        },
        {
          // 模組名稱
          key: 'moduleName',
          label: i18n.t('label.moduleName'),
        },
        {
          // 業務別
          key: 'businessType',
          label: i18n.t('label.businessType'),
        },
        {
          // 狀態
          key: 'status',
          label: i18n.t('label.status'),
          formatter: (value: string) => formOptionsFormatter(formOptions.status, value),
        },
        {
          // 申請日期
          key: 'applyTime',
          label: i18n.t('label.applyDate'),
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 審核日期
          key: 'approveTime',
          label: i18n.t('label.approveDate'),
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          key: 'action',
          label: '',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const formOptions = reactive({
      status: [
        { value: '1', text: '申請中' },
        { value: '3', text: '已啟用' },
        { value: '4', text: '核退' },
      ],
      businessType: [
        { value: 'A', text: '機關單位' },
      ],
    });

    // 取得登入帳號(PWB_ROLE_APPROVE.USER_ID)
    const loginUserId = ref(useGetters(['account']).account.value.id);

    // 查詢登入者的PCC_ORG_ID
    function getAdmAccount(){
      axios.get('/service/adm-account/' + loginUserId.value).then(({ data }) => {
        form.pccOrgId = data.pccOrgId;
      });  
    }

    const iTableComponent = ref(null);

    const clickQueryBtn = () => {
      if (iTableComponent.value) iTableComponent.value.state.pagination.currentPage = 1;
      handleQuery();
    };

    const notificationService: NotificationService = useNotification();

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          queryStatus.value = true;
          axios
            .post('/pwb-unit-apply/getApplyList', form) 
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length); 
              table.totalItems = data.totalElements; 
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    // modal物件
    const $bvModal = useBvModal();

    const dataPromise = ref(null);

    /* ***** 核退 ***** */
    const notApprove = (item) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否核退權限').then((isOK: boolean) => {
            if (isOK) {
              item.status = '4'
              item.approveTime = new Date();
              item.updateTime = new Date();
              item.updateUser = loginUserId.value;
              putForm(item);
            }
          });
        }
      });
    };

    const putForm = (item) => {
      let id = item.id;
      let url = '/pwb-unit-apply/disapprove/' + id;
      dataPromise.value = axios
        .put(url, item)
        .then(() => {
          handleQuery();
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };


    /* ***** 核准 ***** */
    const approve = (item) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否核准權限').then((isOK: boolean) => {
            if (isOK) {
              item.status = '3'
              item.approveTime = new Date();
              item.updateTime = new Date();
              item.updateUser = loginUserId.value;
              postForm(item);
            }
          });
        }
      });
    };

     const postForm = (item) => {
      let url = '/pwb-unit-apply/approve/' + loginUserId.value ;
      dataPromise.value = axios
        .post(url, item )
        .then(() => {
          handleQuery();
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };


    /* ***** 取消 ***** */
    const cancelApply = (item) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否取消權限').then((isOK: boolean) => {
            if (isOK) {
              item.status = '4'
              item.approveTime = new Date();
              item.updateTime = new Date();
              item.updateUser = loginUserId.value;
              putForm(item);
              deleteForm(item.unitId);
            }
          });
        }
      });
    };

    const deleteForm = (unitId) => {
      let url = '/pwb-unit-apply/cancel/' + unitId;
      dataPromise.value = axios
        .delete(url, unitId)
        .then(() => {
          handleQuery();
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };



    return {
      formDefault,
      form,
      rules,
      $v,
      reset,
      stepVisible,
      queryStatus,
      table,
      formOptions,
      clickQueryBtn,
      handleQuery,
      handlePaginationChanged,
      iTableComponent,
      formOptionsFormatter,
      notApprove,
      putForm,
      approve,
      postForm,
      cancelApply
    };
  },
};
</script>
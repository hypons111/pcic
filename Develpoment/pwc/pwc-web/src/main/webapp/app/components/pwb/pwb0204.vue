<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1">
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
              <i-form-group-check :label="$t('label.moduleType')">
                <b-form-select v-model="$v.moduleType.$model" :options="formOptions.moduleType">
                  <template #first>
                    <option value="">全部</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <!-- 狀態 -->
              <i-form-group-check :label="$t('label.status')">
                <b-form-select v-model="$v.status.$model" :options="formOptions.status">
                  <template #first>
                    <option value="">全部</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 帳號 -->
              <i-form-group-check :label="$t('label.account')">
                <b-form-input v-model="$v.userId.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 使用者 -->
              <i-form-group-check :label="$t('label.user')">
                <b-form-input v-model="$v.chName.$model" trim></b-form-input>
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
            {{ $t('button.arrow-counterclockwise') }}
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
import { onActivated, onMounted, reactive, ref, watch, toRefs } from '@vue/composition-api';
import { useGetters } from '@u3u/vue-hooks';
import { Pagination } from '@/shared/model/pagination.model';
import { useValidation } from '@/shared/form';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { useBvModal } from '@/shared/modal';
import NotificationService from '@/shared/notification/notification-service';
import i18n from '@/shared/i18n';
import axios from 'axios';

export default {
  name: 'pwb0204',
  props: {
    pwb0102Detail: {
      type: Object,
      required: false,
    },
  },
  setup(props) {
    onActivated(() => {
      reset();
    });

    onMounted(() => {
      getModuleType();
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      userType: '',
      moduleType: '',
      status: '',
      orgName: '',
      name: '',
      userId: '',
      chName: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      userType: {},
      moduleType: {},
      status: {},
      orgName: {},
      name: {},
      userId: {},
      chName: {},
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
          // 帳號類別
          key: 'userType',
          label: i18n.t('label.type'),
          formatter: (value: string) => formOptionsFormatter(formOptions.userType, value),
        },
        {
          // 使用者
          key: 'chName',
          label: i18n.t('label.user'),
        },
        {
          // 帳號
          key: 'userId',
          label: i18n.t('label.account'),
        },
        {
          // 模組名稱
          key: 'moduleType',
          label: i18n.t('label.moduleName'),
          formatter: (value: string) => formOptionsFormatter(formOptions.moduleType, value),
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
      userType: [
        { value: 'A', text: '機關帳號' },
        { value: 'C', text: '公司法人帳號' },
        { value: 'I', text: '個人帳號' },
      ],
      moduleType: [],
    });

    // 將查到的PWB_ROLE_APPROVE.MODULE_TYPE存在這
    const dataKey = [];

    // 取得登入帳號(PWB_ROLE_APPROVE.USER_ID)
    const approveId = ref(useGetters(['account']).account.value.id);

    // 查詢登入帳號有哪幾個模組代號(PWB_ROLE_APPROVE.MODULE_TYPE)
    function getModuleType() {
      axios.post('/pwb-role-approve/getModuleType/' + approveId.value).then(({ data }) => {
        for (let moduleType of data) {
          dataKey.push(moduleType);
        }
        getAllSysCodes();
      });
    }

    const notificationService: NotificationService = useNotification();

    // 查詢模組代號(ADM_SYS_CODE.DATA_KEY)所對應的中文名稱(ADM_SYS_CODE.VALUE)
    function getAllSysCodes(): void {
      axios
        .get('/service/adm-sys-codes')
        .then(({ data }) => {
          for (let i of dataKey) {
            formOptions.moduleType.push(
              ...data
                .filter(item => item['moduleType'] === 'ADM' && item['dataType'] === 'MODULE_TYPE' && item['dataKey'] === i)
                .map(item => {
                  return { value: item['dataKey'], text: item['value'] };
                })
            );
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    //給元件判斷 :disable 為 T,F用的
    function judgeInputDisable(currentRadioValue: string, radioValue: string): boolean {
      return !(radioValue === currentRadioValue);
    }

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/pwb-role-approve/getSearchList', form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const iTableComponent = ref(null);

    const clickQueryBtn = () => {
      if (iTableComponent.value) iTableComponent.value.state.pagination.currentPage = 1;
      handleQuery();
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    // modal物件
    const $bvModal = useBvModal();

    /* ***** 核退 ***** */
    const notApprove = (item: any) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否核退權限').then((isOK: boolean) => {
            if (isOK) {
              item.status = '4';
              item.approveTime = new Date();
              putForm(item);
            }
          });
        }
      });
    };

    const dataPromise = ref(null);

    const putForm = item => {
      let id = item.id;
      let url = '/pwb-role-apply/disapprove-apply/' + id;
      dataPromise.value = axios
        .put(url, item)
        .then(() => {
          handleQuery();
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    /* ***** 核准 ***** */
    const approve = (item: any) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否核准權限').then((isOK: boolean) => {
            if (isOK) {
              item.status = '3';
              item.approveTime = new Date();
              postForm(item);
            }
          });
        }
      });
    };

    const postForm = item => {
      let url = '/pwb-role-apply/approve-apply/' + approveId.value;
      dataPromise.value = axios
        .post(url, item)
        .then(() => {
          handleQuery();
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    /* ***** 取消 ***** */
    const cancelApply = item => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否取消權限').then((isOK: boolean) => {
            if (isOK) {
              item.status = '4';
              item.approveTime = new Date();
              putForm(item);
              deleteForm(item.userId);
            }
          });
        }
      });
    };

    const deleteForm = userId => {
      let url = '/pwb-role-apply/cancel-apply/' + userId;
      dataPromise.value = axios
        .delete(url, userId)
        .then(() => {
          handleQuery();
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const pwb0102DetailProp = toRefs(props).pwb0102Detail;

    watch(
      pwb0102DetailProp,
      () => {
        if (pwb0102DetailProp.value) {
          createDefaultValue(pwb0102DetailProp.value);
        }
      },
      { immediate: true }
    );

    function createDefaultValue(data) {
      Object.assign(form, data.userId);
      handleQuery();
    }

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
      judgeInputDisable,
      iTableComponent,
      formOptionsFormatter,
      notApprove,
      putForm,
      approve,
      postForm,
      cancelApply,
    };
  },
};
</script>

<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                單位管理者權限維護
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

        <div class="card-body col-12">
          <b-collapse v-model="stepVisible.step1">
            <b-row>
              <b-col cols="6">
                <!-- 模組 -->
                <i-form-group-check :label="$t('label.moduleType')" class="col-12" label-cols="2">
                  <b-form-select style="width: auto" v-model="$v.moduleType.$model" :options="formOptions.moduleType"></b-form-select>
                </i-form-group-check>
              </b-col>
              <b-col cols="6">
                <!-- 狀態 -->
                <i-form-group-check :label="$t('label.status')" class="col-12" label-cols="2">
                  <b-form-select style="width: auto" v-model="$v.status.$model" :options="formOptions.status"> </b-form-select>
                </i-form-group-check>
              </b-col>
            </b-row>

            <b-row>
              <!-- 帳號類別 -->
              <i-form-group-check class="col-12" label-cols="1" :label="$t('label.type')">
                <b-col cols="2" class="text-left">
                  <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="A">全部</b-form-radio>
                </b-col>
                <b-col cols="2" class="text-left">
                  <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="D">機關</b-form-radio>
                </b-col>
                <b-col cols="2" class="text-left">
                  <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="E">其他單位</b-form-radio>
                </b-col>
                <b-col cols="6" class="text-left">
                  <i-tree-select
                    v-model="$v.orgName.$model"
                    placeholder="機關或其他單位名稱查詢(至少輸入4個字)"
                    :multiple="false"
                    :async="true"
                    :load-options="loadOptions"
                    :disabled="judgeInputDisable($v.radioType.$model, 'A')"
                  ></i-tree-select>
                </b-col>
              </i-form-group-check>
            </b-row>

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
import { onActivated, onMounted, reactive, ref, watch, toRefs, toRef } from '@vue/composition-api';
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
import ItreeSelect from '@/shared/i-tree-select/i-tree-select.vue';
import { ASYNC_SEARCH } from '@riophae/vue-treeselect';

export default {
  name: 'pwb0302',
  props: {
    pwb0102Detail: {
      type: Object,
      required: false,
    },
  },
  components: {
    'i-tree-select': ItreeSelect,
  },

  setup(props, context) {
    const pwb0102DetailProp = toRef(props, 'pwb0102Detail');

    onMounted(() => {
      getModuleType();
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      userType: '',
      moduleType: '',
      status: '',
      orgName: null,
      name: '',
      unitId: '',
      chName: '',
      radioType: 'A',
      userId: '',
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
      unitId: {},
      chName: {},
      radioType: {},
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
          // 單位 (名稱)
          key: 'chName',
          label: i18n.t('label.unit'),
        },
        {
          // 帳號類別
          key: 'userType',
          label: i18n.t('label.type'),
          formatter: (value: string) => formOptionsFormatter(formOptions.userType, value),
        },
        {
          // 模組名稱
          key: 'moduleType',
          label: i18n.t('label.moduleName'),
          formatter: (value: string) => formOptionsFormatter(formOptions.moduleType, value),
        },
        {
          // 業務別
          key: 'businessType',
          label: i18n.t('label.businessType'),
          // formatter: (value: string) => formOptionsFormatter(formOptions.moduleType, value),
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
        { value: 'A', text: '機關管理者' },
        { value: 'C', text: '單位管理者' },
      ],
      moduleType: [],
    });

    // 取得登入帳號(PWB_ROLE_APPROVE.USER_ID)
    const approveId = ref(useGetters(['account']).account.value.id);

    // 將查到的PWB_ROLE_APPROVE.MODULE_TYPE存在這
    const dataKey = [];

    // 查詢登入帳號有哪幾個模組代號(PWB_ROLE_APPROVE.MODULE_TYPE)
    function getModuleType() {
      axios.post('/module-type-agency/' + approveId.value).then(({ data }) => {
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
      return radioValue === currentRadioValue;
    }

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/search-unit-list', form) //後端分頁
            .then(({ data }) => {
              // table.data = data.slice(0, data.length);   //前端分頁
              // table.totalItems = data.length;            //前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
              console.log(data);
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

    /* ********************** 核退 ********************** */
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

    const putForm = (item: any) => {
      let id = item.id;
      let url = '/disapprove-unit-apply/' + id;
      dataPromise.value = axios
        .put(url, item)
        .then(() => {
          handleQuery();
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };
    /* ************************************************* */

    /* ********************** 核准 ********************** */
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

    const postForm = (item: any) => {
      let url = '/approve-unit-apply/' + approveId.value;
      dataPromise.value = axios
        .post(url, item)
        .then(() => {
          handleQuery();
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    /* ************************************************* */

    /* ********************** 取消 ********************** */
    const cancelApply = (item: any) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否取消權限').then((isOK: boolean) => {
            if (isOK) {
              item.status = '4';
              item.approveTime = new Date();
              putForm(item);
              deleteForm(item.unitId);
            }
          });
        }
      });
    };

    const deleteForm = unitId => {
      let url = '/cancel-unit-apply/' + unitId;
      dataPromise.value = axios
        .delete(url, unitId)
        .then(() => {
          handleQuery();
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };
    /* ************************************************* */

    //利用tree select 自帶的API來動態產生選項
    function loadOptions({ action, searchQuery, callback }) {
      //判斷注音
      let pattern = /[\u3105-\u3129\u02CA\u02C7\u02CB\u02D9]/;
      if (action === ASYNC_SEARCH && !pattern.test(searchQuery) && searchQuery.toString().length > 3) {
        const config = { headers: { Accept: 'application/json', 'Content-Type': 'application/json' } };
        let url = '';
        if (form.radioType === 'D') {
          url = '/service/adm-agent-findAll';
        } else if (form.radioType === 'E') {
          url = '/adm-companies-unit';
        }
        const agentName = new Promise((resolve, reject) => {
          axios
            .post(url, { keyWord: searchQuery.toString(), type: form.radioType }, config)
            .then((res: any) => {
              resolve(res.data);
            })
            .catch(err => {
              reject(err);
            });
        }).then(res => {
          const options = [];
          res.forEach(ele => {
            const agentOption = { id: '', label: '' };
            if (form.radioType === 'D') {
              agentOption.id = ele.orgId;
              agentOption.label = ele.orgName;
              form.userId = ele.pccOrgId;
            } else {
              agentOption.id = ele.compIdno;
              agentOption.label = ele.name;
              form.userId = ele.compIdno;
            }
            options.push(agentOption);
          });
          callback(null, options);
        });
      }
    }

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
      Object.assign(form, data);
      getModuleType();
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
      loadOptions,
    };
  },
};
</script>

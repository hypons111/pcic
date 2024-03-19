<template>
  <div>
    <section class="container pt-2">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="p-0 m-0">
            <font-awesome-icon icon="user"></font-awesome-icon>
            使用者權限申請
          </h5>
        </div>
        <div class="card-body">
          <i-table
            stacked="sm"
            striped
            class="table-sm table-hover"
            :itemsUndefinedBehavior="'loading'"
            :items="table.data"
            :fields="table.fields"
            :totalItems="table.totalItems"
            :is-server-side-paging="true"
            @changePagination="handlePaginationChanged($event)"
          >
            <template #cell(action)="row">
              <b-button
                size="sm"
                variant="outline-secondary"
                v-show="row.item.status === '4' || row.item.status === null"
                @click="apply(row.item)"
              >
                {{ $t('button.node-plus') }}
              </b-button>
              <b-button
                size="sm"
                variant="outline-secondary"
                v-show="row.item.status === '1' || row.item.status === '3'"
                @click="cancel(row.item)"
              >
                {{ $t('button.cancel') }}
              </b-button>
            </template>
          </i-table>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation } from '@/shared/form';
import { reactive, ref, onMounted } from '@vue/composition-api';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { Pagination } from '@/shared/model/pagination.model';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import { required } from '@/shared/validators';
// import { navigateByNameAndParams } from '@/router/router';
// import { handleBack } from '@/router/router';
// import { accountStore } from '@/shared/config/store/account-store';
import { useGetters } from '@u3u/vue-hooks';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { formatToString } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'pwb0203',
  props: {
    isReload: {
      required: false,
    },
  },
  setup(props, context) {
    onMounted(() => {
      //onMounted : 組件第一次載入時調用
      handleQuery();
      getAllSysCodes();
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      id: '',
      userId: '',
      moduleType: '',
      status: '',
      // applyStatus:'',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      id: {},
      userId: {},
      moduleType: {},
      status: {},
      // applyStatus: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //取得使用者資訊
    const userIdentity = ref(useGetters(['account']).account.value);

    // 查詢結果及狀態
    // const queryStatus = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const formOptions = reactive({
      status: [
        { value: '1', text: '申請中' },
        { value: '3', text: '已啟用' },
        { value: '4', text: '核退' },
      ],
      moduleType: [],
    });

    //查詢後顯示的內容
    const table = reactive({
      fields: [
        {
          key: 'moduleType',
          label: i18n.t('label.moduleName'), //模組名稱
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.moduleType, value),
        },
        {
          key: 'status',
          label: i18n.t('label.status'), //狀態
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) =>
            formOptionsFormatter(formOptions.status, value) === '' ? '未申請' : formOptionsFormatter(formOptions.status, value),
        },
        {
          key: 'applyTime',
          label: i18n.t('label.applyDate'), //申請日期
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          key: 'approveTime',
          label: i18n.t('label.approveTime'), //審核日期
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          key: 'action',
          label: '',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const handleQuery = () => {
      table.data = undefined;
      form.userId = userIdentity.value.id;
      const url = '/pwb-role-applies/query-module';
      dataPromise.value = axios
        .post(url, form) //前端分頁(呼叫後端)
        .then(({ data }) => {
          table.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
          table.totalItems = data.length; //前端分頁
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    function getAllSysCodes(): void {
      axios
        .get('/service/adm-sys-codes')
        .then(({ data }) => {
          formOptions.moduleType.push(
            ...data
              .filter(item => item['moduleType'] === 'ADM' && item['dataType'] === 'MODULE_TYPE')
              .map(item => {
                return { value: item['dataKey'], text: item['value'] };
              })
          );
        })
        .catch(notificationErrorHandler(notificationService));
    }

    //申請
    const apply = (item: any) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否申請').then((isOK: boolean) => {
            if (isOK) {
              item.status = '1';
              if (item.id !== null) {
                item.applyTime = new Date();
                item.approveTime = '';
                putForm(item);
              } else {
                item.userId = userIdentity.value.id;
                item.applyTime = new Date();
                item.updateUser = userIdentity.value.id;
                item.updateTime = new Date();
                item.createUser = userIdentity.value.id;
                item.createTime = new Date();
                postForm(item);
              }
            }
          });
        }
      });
    };

    //取消
    const cancel = (item: any) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否取消').then((isOK: boolean) => {
            if (isOK) {
              item.status = '6';
              item.applyTime = '';
              item.approveTime = '';
              putForm(item);
            }
          });
        }
      });
    };

    const postForm = (item: any) => {
      let url = '/pwb-role-applies';
      dataPromise.value = axios
        .post(url, item)
        .then((response: AxiosResponse) => {
          handleQuery();
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const putForm = (item: any) => {
      let id = item.id;
      let url = '/pwb-role-applies/' + id;
      dataPromise.value = axios
        .put(url, item)
        .then((response: AxiosResponse) => {
          handleQuery();
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    return {
      $v,
      table,
      reset,
      handlePaginationChanged,
      apply,
      cancel,
    };
  },
};
</script>

<style scoped></style>

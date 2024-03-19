<template>
  <div>
    <section class="container pt-2">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="p-0 m-0">
            <font-awesome-icon icon="user"></font-awesome-icon>
            待辦事項
          </h5>
        </div>
        <div class="card-body">
          <b-table
            stacked="sm"
            class="table-sm table-hover "
            :items="table.data"
            :fields="table.fields"
            bordered
          >
            <template #cell(action)="row">
              <i-button type="binoculars" @click="toEditView(row.item)"></i-button>              
            </template>
          </b-table>
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
import { useGetters } from '@u3u/vue-hooks';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'pwb0102',
  props: {
    isReload: {
      required: false,
    },
  },
  setup(props, context) {
    onMounted(() => {
      handleQuery();
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
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();


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
          key: 'unitId',
          label: i18n.t('label.unitId'), //單位代碼
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'unitName',
          label: i18n.t('label.unit'), //單位
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'userId',
          label: i18n.t('label.account'), //帳號
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'chName',
          label: i18n.t('label.userName'), //姓名
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'businessType',
          label: i18n.t('label.jobName'), //作業名稱
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'caseNum',
          label: i18n.t('label.caseNumber'), //案件數
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
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
      // table.data = undefined;
      form.userId = userIdentity.value.id;
      const url = '/penging-view-get-userid/'+ userIdentity.value.id ;
      dataPromise.value = axios
        .get(url, form) //前端分頁(呼叫後端)
        .then(({ data }) => {
          table.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
          table.totalItems = data.length; //前端分頁
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    function toEditView(rowData) {
      if(rowData.businessType === '個人帳號權限審核'){
        navigateByNameAndParams('pwb0204', { pwb0102Detail: rowData });
      }else{
        navigateByNameAndParams('pwb0302', { pwb0102Detail: rowData });
      }

    }

    return {
      table,
      reset,
      handlePaginationChanged,
      toEditView,
    };
  },
};
</script>

<style scoped></style>

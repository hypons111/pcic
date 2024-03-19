<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <div class="d-flex justify-content-center">
                <div  style="color: red">最近5年發生重大職災之公共工程清單</div>
                <div  style="color: blue;font-size: small; ">(依發生日期排序)</div>
                <div  style="color: black;font-size: small;">(不含非工程類)</div>
              </div>
             
             
            </div>
            <div class="col-sm-1 p-0">
              
            </div>
          </div>
        </div>
       
      </div>
    </section>
    <section class="mt-2">
      <!-- v-if="stepVisible.step2" -->
      <div class="container">
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
            <i-button type="binoculars" @click="toSee(row.item)"></i-button>
            <i-button type="pencil-square" @click="toEdit(row.item)"></i-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import VwBidProjectService from '@/components/bid/bidService/vw-bid-project.service';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation } from '@/shared/form';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import i18n from '@/shared/i18n';
import { Pagination } from '@/shared/model/pagination.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { reactive, ref } from '@vue/composition-api';

export default {
  name: 'bidAkc001Menu',
  props: {},
  setup(props, context) {
    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      code: '', // 機關代碼
      name: '', // 機關名稱
    };

    // 表單物件驗證規則
    const rules = {
      code: {}, // 機關代碼
      name: {}, // 機關名稱
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      step2: false,
    });

    let selected = ref('A');

    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
    }

    // 查詢結果及狀態
    const dataPromise = ref(null);

    const table = reactive({
      fields: [
        {
          //勞動部核定日期
          key: 'MeDate',
          label: '勞動部核定日期',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //發生日期
          key: 'SDate',
          label: '發生日期',
          sortable: false,
          thStyle:'width:6%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //工程名稱
          key: 'name',
          label: '工程名稱',
          sortable: false,
          thStyle:'width:6%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //工程主辦機關
          key: 'wkut',
          label:'工程主辦機關',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //設計單位
          key: 'dcutString',
          label:'設計單位',
          sortable: false,
          thStyle:'width:6%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //監造單位
          key: 'scutString',
          label:'監造單位',
          sortable: false,
          thStyle:'width:6%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //施工廠商名稱
          key: 'ccutString',
          label:'施工廠商名稱',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //可歸責廠商
          key: 'respons',
          label:'可歸責廠商',
          sortable: false,
          thStyle:'width:6%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //罹災時作業災情
          key: 'disasterScript',
          label:'罹災時作業災情',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //災害類型
          key: ' sType',
          label:'災害類型',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //死
          key: 'diedNum',
          label:'死',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //傷
          key: 'hurtNum',
          label:'傷',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        }
        {
          //縣市別
          key: 'pCode',
          label:'縣市別',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        }
      ],
      data: undefined,
      totalItems: 0,
    });
    
    //送 query去後端撈資料，渲染到table
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          stepVisible.step2 = true;
          dataPromise.value = axios
            .post('/bid-unit-10/criteria-jpa', form) //前端分頁(呼叫後端)
            .then(({ data }) => {
              // table.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
              // table.totalItems = data.length; //前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };
    handleQuery();
    const toSee = value => {
      navigateByNameAndParams('bidAbe007Edit', { value, formStatus: FormStatusEnum.READONLY, KeepAlive: true });
    };

    const toEdit = value => {
      console.log(value);
      navigateByNameAndParams('bidAbe007Edit', { value, formStatus: FormStatusEnum.MODIFY, KeepAlive: true });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      $v,
      reset,
      stepVisible,
      dataPromise,
      table,
      handleQuery,
      handlePaginationChanged,
      toEdit,
      toSee,
      selected,
      FormStatusEnum,
    };
  },
};
</script>
<style></style>

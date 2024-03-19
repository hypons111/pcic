<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <div class="d-flex justify-content-center">
                <div  style="color: black">110年度各工程施工查核小組查核及複查結果</div>
                <div  style="color: black;font-size: small; ">(依查核日期排序)</div>
                
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
          //查核日期
          key: 'chkDate',
          label: '查核日期',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //查核小組
          key: 'checkUnit',
          label: '查核小組',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //工程主辦單位
          key: 'wkut',
          label: '工程主辦單位',
          sortable: false,
          thStyle:'width:12%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //工程名稱
          key: 'name',
          label:'工程名稱',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //契約金額
          key: 'ctSum',
          label:'契約金額(千元)',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //規劃設計
          key: 'dcutString',
          label:'規劃設計',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //監造單位
          key: 'scutString',
          label:'監造單位',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        
        {
          //承攬廠商
          key: 'scutString',
          label:'承攬廠商',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //專案管理
          key: 'pcmut',
          label:'專案管理',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //評分
          key: ' score',
          label:'評分',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //材料抽檢結果
          key: 'testFlag',
          label:'材料抽檢結果',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
       
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

<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
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
        <div class="card-body">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <i-form-group-check
                class="col-12"
                label-cols-md="1"
                content-cols-md="8"
                :label="$t('label.code') + ':'"
                label-align-md="right"
              >
                <b-form-input class="col-4" v-model="$v.code.$model" trim></b-form-input>
                <div class="col-8" style="color: blue">請輸入機關代碼關鍵字</div>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check
                class="col-12"
                label-cols-md="1"
                content-cols-md="8"
                :label="$t('label.agencyName') + ':'"
                label-align-md="right"
              >
                <b-form-input class="col-4" v-model="$v.name.$model" trim></b-form-input>
                <div class="col-8" style="color: blue">請輸入機關名稱關鍵字(如:營建署)</div>
              </i-form-group-check>
            </b-form-row>
            <div class="text-center">
              <i-button type="search" @click="handleQuery"></i-button>
              <i-button type="x-circle" @click="reset"></i-button>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>
    <section class="mt-2">
      <div v-if="stepVisible.step2" class="container">
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
  name: 'bidAbe007',
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
          //機關代碼
          key: 'code',
          label: i18n.t('label.code'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          //機關名稱
          key: 'name',
          label: i18n.t('label.agencyName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        // {
        //   //聯絡地址
        //   key: 'addr',
        //   label: i18n.t('label.addr'),
        //   sortable: false,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        // },
        // {
        //   //連絡電話
        //   key: 'tel',
        //   label: i18n.t('label.contactInformationTel'),
        //   sortable: false,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        // },
        // {
        //   //傳真機號碼
        //   key: 'fax',
        //   label: i18n.t('label.fax'),
        //   sortable: false,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        // },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: 'width:20%',
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

    const toSee = value => {
      navigateByNameAndParams('bidAbe007Edit', { value, formStatus: FormStatusEnum.READONLY });
    };

    const toEdit = value => {
      console.log(value);
      navigateByNameAndParams('bidAbe007Edit', { value, formStatus: FormStatusEnum.MODIFY });
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
      FormStatusEnum,
    };
  },
};
</script>
<style></style>

<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-body">
          <div>變更設計併案說明：</div>
          <div>
            一、本標案僅可併至同一機關執行尚未結案之標案，如非同機關之標案，請上級機關先行以執行機關變更方式移轉標案資料至母案執行機關即可。
          </div>
          <div>二、請先選取下方欲併案之執行單位，即可開始併案程序。</div>
        </div>
      </div>
    </section>

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
              <i-form-group-check :label="'標案名稱關鍵字'">
                <b-form-input v-model="$v.prjName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-row>
              <b-col cols="5">
                <i-button type="search" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-row>
          </b-collapse>
        </div>
      </div>
    </section>
    <section class="container mt-2">
      <div class="card">
        <div class="card-body">
          <div>註：本表統計本年度內完工或尚未完工之案件（不含電腦、辦公設備及其他）</div>
        </div>
      </div>
    </section>
    <section class="mt-2">
      <div v-if="stepVisible.step2" class="container">
        <i-table
          ref="iTable"
          class="table-sm"
          title="標案變更設計併案查詢結果"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :fields="table.fields"
          :totalItems="table.totalItems"
          :is-server-side-paging="true"
          @changePagination="handlePaginationChanged($event)"
        >
          <template #cell(action)="row">
            <b-button variant="info" @click="toEditView(row.item)">併案</b-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import AccountService from '@/account/account.service';
import VwBidProjectService from '@/components/bid/bidService/vw-bid-project.service';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation } from '@/shared/form';
import { commaFormatter } from '@/shared/formatter/common';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import i18n from '@/shared/i18n';
import { Pagination } from '@/shared/model/pagination.model';
import { VwBidProjectQueryCriteria } from '@/shared/model/bidModel/vw-bid-project-query.criteria';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { onActivated, reactive, ref, inject } from '@vue/composition-api';

export default {
  name: 'bidAdc001Query',
  props: {},
  setup(props, context) {
    const vwBidProjectService = new VwBidProjectService();
    const bidProjectService = new BidProjectService();
    const accountService = inject<() => AccountService>('accountService')();

    const iTable = ref(null);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      prjno: '', // 標案編號
      prjName: '', // 標案名稱關鍵字
    };

    // 表單物件驗證規則
    const rules = {
      prjno: {}, // 標案編號
      prjName: {}, // 標案名稱關鍵字
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

    // 查詢結果及狀態
    const dataPromise = ref(null);

    const table = reactive({
      fields: [
        {
          key: 'wkut',
          label: i18n.t('label.wkut'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'prjno',
          label: i18n.t('label.prjno'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'name0',
          label: i18n.t('label.projectName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'actDate',
          label: i18n.t('label.actDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'ctSum',
          label: i18n.t('label.ctSum'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    //fixed me 之後要改
    //從使用者撈wkut
    function getAccountWkut(): string {
      return accountService.user.pccOrg ? accountService.user.pccOrg : '360000000G';
    }

    //送 query去後端撈資料，渲染到table
    function handleQuery() {
      checkValidity().then(result => {
        if (result) {
          if (iTable.value) iTable.value.state.pagination.currentPage = 1;
          stepVisible.step2 = true;
          let queryCriteria = queryStatusToCriteria();
          getDatafromAxios(queryCriteria);
        } else {
          //do something
        }
      });
    }

    function getDatafromAxios(queryCriteria) {
      bidProjectService
        .findProjectByCriteria(queryCriteria)
        .then(res => {
          table.data = res.content.slice(0, res.content.length); //後端分頁
          table.totalItems = res.totalElements; //後端分頁
        })
        .catch(notificationErrorHandler(notificationService));
      //checkVailidaty 沒過
    }

    function toEditView(value) {
      let bidProjectKey = {
        wkut: value.wkut,
        prjno: value.prjno,
      };
      navigateByNameAndParams('bidAdc001Edit', { bidProjectKey });
    }

    function handlePaginationChanged(pagination: Pagination) {
      let queryCriteria = queryStatusToCriteria();
      Object.assign(queryCriteria, pagination);
      getDatafromAxios(queryCriteria);
    }

    //讓前端畫面的criteria轉成後端認得的 DTO
    function queryStatusToCriteria() {
      let criteria = new VwBidProjectQueryCriteria();
      criteria.queryType = 'bidAdc001';
      criteria.prjName0 = form.prjName;
      criteria.wkut = getAccountWkut();
      return criteria;
    }

    return {
      $v,
      iTable,
      reset,
      stepVisible,
      dataPromise,
      table,
      handleQuery,
      handlePaginationChanged,
      toEditView,
    };
  },
};
</script>
<style></style>

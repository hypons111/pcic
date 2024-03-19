<template>
  <div>
    <section class="container">
<!--      <div class="card">-->
<!--        <div class="card-header py-1 text-left">-->
<!--          <div class="row align-items-center">-->
<!--            <div class="col-sm-11 p-0">-->
<!--              <h5 class="m-0">-->
<!--                <font-awesome-icon icon="search"></font-awesome-icon>-->
<!--                查詢條件-->
<!--              </h5>-->
<!--            </div>-->
<!--            <div class="col-sm-1 p-0">-->
<!--              <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">-->
<!--                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>-->
<!--                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>-->
<!--              </b-button>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->
<!--        <div class="card-body">-->
<!--          <b-collapse v-model="stepVisible.step1">-->
<!--            <b-form-row>-->
<!--              <i-form-group-check :label="'標案編號'">-->
<!--                <b-form-input v-model="$v.prjno.$model" trim></b-form-input>-->
<!--              </i-form-group-check>-->
<!--              <i-form-group-check :label="'標案名稱關鍵字'">-->
<!--                <b-form-input v-model="$v.prjName.$model" trim></b-form-input>-->
<!--              </i-form-group-check>-->
<!--            </b-form-row>-->
<!--            <b-row>-->
<!--              <b-col cols="5">-->
<!--                <i-button type="search" @click="handleQuery"></i-button>-->
<!--                <i-button type="x-circle" @click="reset"></i-button>-->
<!--              </b-col>-->
<!--            </b-row>-->
<!--          </b-collapse>-->
<!--        </div>-->
<!--      </div>-->


      <div>
        <bid-project-basic-query-view :toRouterPath="'bidAaa007InfoMenu'" :buttonName="'檢視'"></bid-project-basic-query-view>
      </div>


    </section>
    <section class="mt-2">
<!--      <div v-if="stepVisible.step2" class="container">-->
<!--        <i-table-->
<!--          ref="iTable"-->
<!--          class="table-sm"-->
<!--          :itemsUndefinedBehavior="'loading'"-->
<!--          :items="table.data"-->
<!--          :fields="table.fields"-->
<!--          :totalItems="table.totalItems"-->
<!--          :is-server-side-paging="true"-->
<!--          @changePagination="handlePaginationChanged($event)"-->
<!--        >-->
<!--          <template #cell(action)="row">-->
<!--            <i-button type="binoculars" @click="toEditView(row.item)"></i-button>-->
<!--          </template>-->
<!--        </i-table>-->
<!--      </div>-->
    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import VwBidProjectService from '@/components/bid/bidService/vw-bid-project.service';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation } from '@/shared/form';
import { commaFormatter } from '@/shared/formatter/common';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import i18n from '@/shared/i18n';
import { Pagination } from '@/shared/model/pagination.model';
import { VwBidProjectQueryCriteria } from '@/shared/model/bidModel/vw-bid-project-query.criteria';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { onActivated, reactive, ref } from '@vue/composition-api';
import BidProjectBasicQueryView from "@/components/bid/bid-common-component/bid-project-basic-query-view.vue";

export default {
  name: 'bidAaa007',
  components: { BidProjectBasicQueryView },
  props: {},
  setup(props, context) {
    const vwBidProjectService = new VwBidProjectService();
    const bidCommonService = new BidCommonService();
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

    // // 區塊是否顯示
    // const stepVisible = reactive({
    //   step1: true,
    //   step2: false,
    // });

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
          key: 'projectName',
          label: i18n.t('label.projectName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'aopDate',
          label: i18n.t('label.aopDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'typeCode',
          label: i18n.t('label.typeCode'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'aeDate',
          label: i18n.t('label.aeDate'),
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

    // //送 query去後端撈資料，渲染到table
    // function handleQuery() {
    //   checkValidity().then(result => {
    //     if (result) {
    //       if (iTable.value) iTable.value.state.pagination.currentPage = 1;
    //       stepVisible.step2 = true;
    //       let queryCriteria = queryStatusToCriteria();
    //       getDatafromAxios(queryCriteria);
    //     } else {
    //       //do something
    //     }
    //   });
    // }

    function getDatafromAxios(queryCriteria) {
      vwBidProjectService
        .findByCriteria(queryCriteria)
        .then(res => {
          //為了formatter後端帶來的typeCode 用
          //因為寫的formatter 是 async function,所以沒辦法用 b-table內建的 formatter function
          //只好在撈data的時候做加工，把typeCode轉成對應的字串 ex 10=> 建築--建築建造工程
          res.content.forEach(ele => {
            if (ele.typeCode) {
              bidCommonService.bidProjectTypeCodeConverter(ele.typeCode).then(data => {
                ele.typeCode = data;
              });
            }
          });

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
      navigateByNameAndParams('bidAaa007InfoMenu', { bidProjectKey });
    }

    function handlePaginationChanged(pagination: Pagination) {
      let queryCriteria = queryStatusToCriteria();
      Object.assign(queryCriteria, pagination);
      getDatafromAxios(queryCriteria);
    }

    //讓前端畫面的criteria轉成後端認得的 DTO
    function queryStatusToCriteria() {
      let criteria = new VwBidProjectQueryCriteria();
      criteria.queryType = 'keyWord';
      criteria.prjno = form.prjno;
      criteria.prjName = form.prjName;
      return criteria;
    }

    return {
      $v,
      iTable,
      dataPromise,
      table,

      handlePaginationChanged,
      toEditView,
    };
  },
};
</script>
<style></style>

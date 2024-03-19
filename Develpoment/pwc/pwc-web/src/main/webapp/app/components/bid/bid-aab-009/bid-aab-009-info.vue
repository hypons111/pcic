<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">使用預算來源資料</h5>
        </div>
        <div>
          <b-table :items="table.topData" :fields="table.topFields">
            <template #cell(tableNo)="row"> {{ row.index + 1 }}</template>
            <template #cell(disNo)="row"> {{ row.item.disNo }}</template>
            <template #cell(disName)="row"> {{ row.item.disName }}</template>
            <template #cell(amnta)="row"> {{ row.item.amnta }}</template>
            <template #cell(amntb)="row"> {{ row.item.amntb }}</template>
            <template #cell(amnt)="row"> {{ row.item.amnt }}</template>
          </b-table>
        </div>
        <div class="mt-2"></div>
        <div class="card-header py-1 text-left">
          <h5 class="m-0">復健請款情形資料</h5>
        </div>
        <div class="mt-2">
          <i-button type="file-earmark-plus" @click="toCreate"></i-button>
          <b-button style="background-color: #1aa4b7" @click="toTable">復建工程發包執行表</b-button>
        </div>
        <div class="mt-2">
          <i-table
            class="table-sm"
            :itemsUndefinedBehavior="'loading'"
            :items="table.data"
            :fields="table.fields"
            :totalItems="table.totalItems"
            :is-server-side-paging="true"
            :hide-no="true"
            @changePagination="handlePaginationChanged($event)"
          >
            <!--#cell為id,row代表這一列的資料-->
            <template #thead-top>
              <b-th colspan="11" style="text-align: center"> 標案歷次撥款資料 金額單位:千元</b-th>
            </template>
            <template #cell(year)="row">{{ row.item.year }}/{{ row.item.month }}</template>
            <template #cell(tenDate)="row">{{ formatDate(row.item.tenDate ? new Date(row.item.tenDate) : null, '/') }}</template>
            <template #cell(payDate)="row">{{ formatDate(row.item.payDate ? new Date(row.item.payDate) : null, '/') }}</template>
            <template #cell(action)="row">
              <i-button class="btn-sm" type="pencil-square" @click="toModify(row.item)"></i-button>
            </template>
          </i-table>
        </div>
        <i-pdf-viewer ref="pdfViewer" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import NotificationService from '@/shared/notification/notification-service';
import { reactive, Ref, ref, toRef, watch } from '@vue/composition-api';
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { Pagination } from '@/shared/model/pagination.model';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import i18n from '@/shared/i18n';
import BidPrjPay2Service from '@/components/bid/bidService/bid-prj-pay2.service';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import BidPrjSur2Service from '@/components/bid/bidService/bid-prj-sur2.service';
import { commaFormatter } from '@/shared/formatter/common';

export default {
  name: 'bidAab009Info',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidProjectService = new BidProjectService();
    const bidPrjPay2Service = new BidPrjPay2Service();
    const bidPrjSur2Service = new BidPrjSur2Service();
    const notificationService: NotificationService = useNotification();
    const iTable = ref(null);
    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });
    const pdfViewer = ref(null);

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        // console.log('fetchBidProjectInfo res ', result);
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      wkut: '',
      prjno: '',
      disNo: '',
      disName: '',
      amnta: '',
      amntb: '',
      amnt: '',
      year: '',
      month: '',
      date: '',
      trueMon: '',
      payMonT: '',
      payMonU: '',
      tenDate: undefined,
      tenMon: '',
      payDate: undefined,
      payMon: '',
      payMonS: '',
      chkNo: '',
      payRmk: '',
    };

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      disNo: {},
      disName: {},
      amnta: {},
      amntb: {},
      amnt: {},
      year: {},
      month: {},
      date: {},
      trueMon: {},
      payMonT: {},
      payMonU: {},
      tenDate: {},
      tenMon: {},
      payDate: {},
      payMon: {},
      payMonS: {},
      chkNo: {},
      payRmk: {},
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      topFields: [
        {
          key: 'tableNo',
          label: '序號',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'disNo',
          label: '核定編號',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'disName',
          label: '預算科目或摘要',
          sortable: false,
          thStyle: 'width:30%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'amnta',
          label: '使用地方配合款(千元)',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'amntb',
          label: '中央補助款(千元)',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'amnt',
          label: '合計預算款(千元)',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
      ],
      fields: [
        {
          key: 'year',
          label: '請撥年月',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'trueMon',
          label: '請撥金額',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value),
        },
        {
          key: 'tenDate',
          label: '預定撥款日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'tenMon',
          label: '預定撥款金額',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value),
        },
        {
          key: 'payDate',
          label: '實際撥款日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'payMon',
          label: '實際撥款金額',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value),
        },
        {
          key: 'payMonS',
          label: '累計已撥金額',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value),
        },
        {
          key: 'rmk',
          label: '備註',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: '動作',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      topData: undefined,
      data: undefined,
      totalItems: 0,
      topTotalItem: 0,
    });

    const handleQuery = () => {
      checkValidity().then(result => {
        if (result) {
          if (iTable.value) iTable.value.state.pagination.currentPage = 1;
          getPrjSur2DatafromAxios(bidProjectKeyProp.value);
          getPrjPay2DatafromAxios(bidProjectKeyProp.value);
        }
      });
    };

    // 使用預算來源資料
    function getPrjSur2DatafromAxios(queryCriteria) {
      bidPrjSur2Service
        .getBidPrjSur2ByCriteria(queryCriteria)
        .then(res => {
          table.topData = res.content.slice(0, res.content.length); // 前端分頁
          table.topTotalItem = res.content.length; // 前端分頁
        })
        .catch(notificationErrorHandler(notificationService));
      //checkVailidaty 沒過
    }
    // 復健請款情形資料
    function getPrjPay2DatafromAxios(queryCriteria) {
      bidPrjPay2Service
        .getBidPrjPay2ByCriteria(queryCriteria)
        .then(res => {
          table.data = res.content.slice(0, res.content.length); //後端分頁
          table.totalItems = res.totalElements; //後端分頁
        })
        .catch(notificationErrorHandler(notificationService));
      //checkVailidaty 沒過
    }

    // 後端分頁用
    function handlePaginationChanged(pagination: Pagination) {
      Object.assign(form, pagination);
      handleQuery();
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          handleQuery();
        }
      },
      { immediate: true }
    );

    function toCreate() {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAab009Edit', { bidProjectKey, formStatus: 'create' });
    }

    function toModify(row) {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
        year: row.year,
        month: row.month,
      };
      navigateByNameAndParams('bidAab009Edit', { bidProjectKey, formStatus: 'modify' });
    }

    function toTable() {
      let bidProject = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };

      axios
        .post('/bid-prj-pay-2-s/BidAae023', bidProject, { responseType: 'blob' })
        .then(res => {
          let blob = new Blob([res.data], { type: 'application/pdf' });
          let url = window.URL.createObjectURL(blob);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    }

    return {
      projectInfo,
      iTable,
      $v,
      reset,
      bidProjectKeyProp,
      table,
      form,
      handleQuery,
      toCreate,
      toModify,
      handlePaginationChanged,
      formatDate,
      toTable,
      pdfViewer,
    };
  },
  filters: {
    // dateToString: (value: Date) => {
    //   const date = value.toString();
    //   const year = date.substring(0, 4);
    //   const yNum = Number(year) - 1911;
    //   const month = date.substring(5, 7);
    //   const day = date.substring(8);
    //   const rocDate = yNum + '/' + month + '/' + day;
    //   return rocDate;
    // },
  },
};
</script>

<style scoped></style>

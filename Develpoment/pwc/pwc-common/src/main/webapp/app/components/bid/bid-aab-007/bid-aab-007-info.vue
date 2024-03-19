<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">開口合約派工資料</h5>
        </div>
        <div>
          <i-button class="mt-2" type="file-earmark-plus" @click="toCreate"></i-button>
          <b-button class="mt-2" style="background-color: #1aa4b7" @click="toTable">開口合約派工表</b-button>
        </div>
        <div class="mt-2">
          <i-table
            ref="iTable"
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
              <b-th colspan="10">
                <div>
                  <div class="float-left ml-3 mr-5">&nbsp;&nbsp;&nbsp;合計概估金額: &nbsp;{{ countMonNum.sumDspPmon }} &nbsp;千元</div>
                  <div class="float-left ml-3 mr-5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                  <div class="float-left ml-5 mr-5">已估驗計價金額:&nbsp; {{ countMonNum.sumDspAmon }}&nbsp;千元</div>
                  <div class="float-left ml-3 mr-5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                  <div class="float-left ml-5 mr-5">已付款金額:&nbsp;{{ countMonNum.sumDspA2mon }}&nbsp;千元</div>
                </div>
              </b-th>
            </template>
            <template #cell(action)="row">
              <i-button class="btn-sm" type="pencil-square" @click="toModify(row.item)"></i-button>
              <b-button class="btn-sm" style="background-color: #1aa4b7" @click="toWorkTable(row.item)">施工通知單</b-button>
            </template>
          </i-table>
        </div>
      </div>
    </div>
    <i-pdf-viewer ref="pdfViewer" />
  </div>
</template>

<script lang="ts">
import NotificationService from '@/shared/notification/notification-service';
import { Reactive, reactive, Ref, ref, toRef, watch } from '@vue/composition-api';
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { Pagination } from '@/shared/model/pagination.model';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import i18n from '@/shared/i18n';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { IBidDsptch, BidDsptch } from '@/shared/model/bidModel/bid-dsptch.model';
import BidDsptchService from '@/components/bid/bidService/bid-dsptch.service';
import { number } from '@storybook/addon-knobs';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { commaFormatter } from '@/shared/formatter/common';

export default {
  name: 'bidAab007Info',
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
    const bidCommonService = new BidCommonService();
    const bidDsptchService = new BidDsptchService();

    const notificationService: NotificationService = useNotification();
    const pdfViewer = ref(null);

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });

    const iTable = ref(null);

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
          result.typeCode = data;
          Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        });
      });
    }

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault: IBidDsptch = new BidDsptch();

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      dspNo: {},
      dspSdat: {},
      ccut1Tel: {},
      ccut1Fax: {},
      ccut1er: {},
      ccut1erTel: {},
      scutTel: {},
      scutFax: {},
      scuter: {},
      scuterTel: {},
      dspSpeed: {},
      dspItem: {},
      dspPmon: {},
      dspPedat: {},
      dspAedat: {},
      dspF1dat: {},
      dspAmon: {},
      dspF2dat: {},
      sender: {},
      sendtel: {},
      sendfax: {},
      createUser: {},
      createDate: {},
      updateDate: {},
      updateUser: {},
    };

    // 建立表單物件 ref
    const form: Reactive<IBidDsptch> = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields: [
        {
          key: 'dspNo',
          label: '序號',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'dspSdat',
          label: '派工日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          key: 'dspItem',
          label: '通知事項',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'dspPmon',
          label: '概估金額(千元)',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value),
        },
        {
          key: 'dspPedat',
          label: '預定完成期限',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          key: 'dspAedat',
          label: '實際完成日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          key: 'dspF1dat',
          label: '實際估驗日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          key: 'dspAmon',
          label: '計價金額(千元)',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value),
        },
        {
          key: 'dspF2dat',
          label: '實際付款日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
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
      data: undefined,
      totalItems: 0,
    });

    const handleQuery = () => {
      if (iTable.value) iTable.value.state.pagination.currentPage = 1;
      getDatafromAxios(bidProjectKeyProp.value);
    };

    function getDatafromAxios(queryCriteria) {
      bidDsptchService
        .getPrjFalsByCriteria(queryCriteria)
        .then(res => {
          table.data = res.content.slice(0, res.content.length); //後端分頁
          table.totalItems = res.totalElements; //後端分頁
          countMonNum.value.sumDspA2mon = 0;
          countMonNum.value.sumDspAmon = 0;
          countMonNum.value.sumDspPmon = 0;

          res.content.forEach(ele => {
            if (ele.dspF2dat != null) {
              countMon(ele.dspPmon, ele.dspAmon, true);
            } else {
              countMon(ele.dspPmon, ele.dspAmon, false);
            }
          });
        })
        .catch(notificationErrorHandler(notificationService));
      //checkVailidaty 沒過
    }
    const countMonNum = ref({ sumDspPmon: 0, sumDspAmon: 0, sumDspA2mon: 0 });
    function countMon(pmon: number, amon: number, f2datIsNotNull: unknown) {
      if (pmon != null) {
        countMonNum.value.sumDspPmon = countMonNum.value.sumDspPmon + pmon;
      }
      if (amon != null) {
        countMonNum.value.sumDspAmon = countMonNum.value.sumDspAmon + amon;
      }
      if (f2datIsNotNull) {
        countMonNum.value.sumDspA2mon = countMonNum.value.sumDspA2mon + amon;
      }
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
      navigateByNameAndParams('bidAab007Edit', { bidProjectKey, formStatus: 'create' });
    }

    function toModify(row) {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
        dspNo: row.dspNo,
      };

      navigateByNameAndParams('bidAab007Edit', { bidProjectKey, formStatus: 'modify' });
    }

    function toTable() {
      let bidProject = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };

      axios
        .post('/bid-prj-dsptches/BidAae020s01', bidProject, { responseType: 'blob' })
        .then(res => {
          let blob = new Blob([res.data], { type: 'application/pdf' });
          let url = window.URL.createObjectURL(blob);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    }

    function toWorkTable(value) {
      let criteria = {
        wkut: value.wkut,
        prjno: value.prjno,
        dspNo: value.dspNo,
      };
      axios
        .post('/bid-prj-dsptches/BidAae020s02', criteria, { responseType: 'blob' })
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
      countMon,
      countMonNum,
      toTable,
      toWorkTable,
      pdfViewer,
    };
  },
};
</script>

<style scoped></style>

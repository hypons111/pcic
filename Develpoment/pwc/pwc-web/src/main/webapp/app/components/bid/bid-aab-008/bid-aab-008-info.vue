<template>
  <div>
    <div class="card col-12">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>

      <div class="card-header py-1 text-left">
        <h5 class="m-0">復健院核定項目資料</h5>
      </div>

      <div class="mt-2">
        <i-button type="file-earmark-plus" size="sm" @click="toCreateView"></i-button>
        <b-button class="btn-sm" style="background-color: #1aa4b7" @click="toTable">復建工程發包執行表</b-button>
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
          @changePagination="handlePaginationChanged($event)"
        >
          <template #thead-top>
            <td colspan="3"></td>
            <th colspan="3" style="text-align: center">使用預算來源(千元)</th>
            <td></td>
          </template>
          <template #cell(action)="row">
            <!--              修改工安事件(單一)-->
            <i-button class="btn-sm" type="pencil-square" @click="toModifyView(row.item)"></i-button>
          </template>
        </i-table>
      </div>
    </div>
    <i-pdf-viewer ref="pdfViewer" />
  </div>
</template>

<script lang="ts">
import { reactive, Ref, ref, toRef, toRefs, watch } from '@vue/composition-api';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { commaFormatter } from '@/shared/formatter/common';
export default {
  name: 'bidAab008Info',
  components: {
    bidProjectInfo,
  },
  props: {
    formStatusForward: {
      type: String,
      required: false,
    },
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    //傳入狀態
    const formStatusForwardProp = toRefs(props).formStatusForward;
    // 基本資料
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();
    // 傳入PK
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });
    const notificationService: NotificationService = useNotification();
    // 查詢結果及狀態
    const dataPromise = ref(null); //查詢結果
    const pdfViewer = ref(null);

    /**
     * 撈出project的部分資料(基本資料)
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
          result.typeCode = data;
          Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        });
      });
    }

    let formDefault = {
      wkut: '', // 執行機關代碼
      prjno: '', // 標案編號
      disNo: '', // 核定預算項目
      amnta: '', // 使用地方災害準備金數
      amntb: '', // 使用中央補助款數
      amnt: '', // 建立日期
      createDate: '', // 建立人員
      createUser: '', // 建立日期
      updateDate: '', // 更新日期
      updateUser: '', // 更新人員
      dsts: '', // 資料狀況
      confOut: '', // 預定執行單位資料確認
      uIp: '', // 更新IP
      disName: '', // 災害工程項目及內容名稱
      veri3Budg: '', // 審查小組審議金額
    };

    let rules = {
      wkut: {}, // 執行機關代碼
      prjno: {}, // 標案編號
      disNo: {}, // 核定預算項目
      amnta: {}, // 使用地方災害準備金數
      amntb: {}, // 使用中央補助款數
      amnt: {}, // 建立日期
      createDate: {}, // 建立人員
      createUser: {}, // 建立日期
      updateDate: {}, // 更新日期
      updateUser: {}, // 更新人員
      dsts: {}, // 資料狀況
      confOut: {}, // 預定執行單位資料確認
      uIp: {}, // 更新IP
      disName: {}, // 災害工程項目及內容名稱
      veri3Budg: {}, // 審查小組審議金額
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 處理後端分頁問題
    const iTable = ref(null);

    const table = reactive({
      fields: [
        {
          key: 'disNo',
          label: '核定編號',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'disName',
          label: '工程項目及名稱內容',
          sortable: false,
          thStyle: 'width:25%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'amnta',
          label: '地方災害準備金',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value),
        },
        {
          key: 'amntb',
          label: '中央補助款',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value),
        },
        {
          key: 'amnt',
          label: '合計',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value),
        },
        {
          key: 'action',
          label: '動作',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const handleQuery = () => {
      table.data = undefined;
      form.wkut = bidProjectKeyProp.value.wkut;
      form.prjno = bidProjectKeyProp.value.prjno;

      dataPromise.value = axios
        .post('/bid-prj-sur-2-s/criteria', form)
        .then(({ data }) => {
          if (data != null) {
            if (iTable.value) iTable.value.state.pagination.currentPage = 1;

            table.data = data.content.slice(0, data.content.length); //後端分頁
            table.totalItems = data.totalElements; //後端分頁
          }
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const toCreateView = () => {
      let bidPrjSur2Key = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAab008EditInfo', { bidPrjSur2Key, formStatusForward: 'create' });
    };

    const toModifyView = row => {
      let bidPrjSur2Key = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
        disNo: row.disNo,
      };
      console.log('bidPrjSur2Key = ', bidPrjSur2Key);
      navigateByNameAndParams('bidAab008EditInfo', { bidPrjSur2Key, formStatusForward: 'modify' });
    };

    // 後端分頁用
    function handlePaginationChanged(pagination: Pagination) {
      Object.assign(form, pagination);
      handleQuery();
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

    return {
      formStatusForwardProp,
      $v,
      bidProjectKeyProp,
      projectInfo,
      form,
      table,
      handlePaginationChanged,
      handleQuery,
      toCreateView,
      toModifyView,
      toTable,
      fetchBidProjectInfo,
      pdfViewer,
      iTable,
    };
  },
};
</script>

<style scoped></style>

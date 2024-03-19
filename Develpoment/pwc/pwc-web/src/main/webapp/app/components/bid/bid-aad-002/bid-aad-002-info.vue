<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <div>
      <span style="color: red">職業安全衛生法</span>
      <span @click="toLaw"><u>第37條</u></span>
      <span style="color: blue">事業單位工作發生職業災害，雇主應...</span>
    </div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">工安事件狀況列表</h5>
      </div>
      <div class="card-body col-12">
        <div>
          <i-button type="file-earmark-plus" size="sm" @click="toCreateView"></i-button>
        </div>

        <!--        查詢工安事件狀況列表(多)-->
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
            <template #cell(action)="row">
              <!--              查詢工安事件(單一)-->
              <!--              {{ row.item }} 得到的資料-->
              <i-button class="btn-sm" type="binoculars" @click="toDetailView(row.item.wkut, row.item.prjno, row.item.sdate)"></i-button>
              <!--              修改工安事件(單一)-->
              <i-button class="btn-sm" type="pencil-square" @click="toModifyView(row.item.wkut, row.item.prjno, row.item.sdate)"></i-button>
              <br />
              <!--              刪除工安事件(單一)-->
              <!--              <i-button-->
              <!--                class="btn-sm"-->
              <!--                type="trash"-->
              <!--                @click="toDeleteView(row.item.wkut, row.item.prjno, row.item.sdate, row.item.prmNo)"-->
              <!--              ></i-button>-->
              <b-button class="btn-sm mt-1" style="background-color: #1aa4b7" @click="toTable(row.item)">工安事件表</b-button>
            </template>
          </i-table>

          註:已由勞動部填報<span style="color: blue">核定日期</span>資料不開放修改
          <i-pdf-viewer ref="pdfViewer" />
        </div>
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
import i18n from '@/shared/i18n';
import { Pagination } from '@/shared/model/pagination.model';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import { formatToString } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'bidAad002Info',
  // 基本資料用公用套件
  components: {
    bidProjectInfo,
  },
  props: {
    // 由前面傳來的值決定是哪一個基本資料顯示及其工安事件(基本資料用)
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    // 基本資料
    const bidProjectService = new BidProjectService();
    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    // 傳入PK
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });
    /**
     * 從使用者撈 wkut的作法。
     * 如果是登錄就有的資訊應從使用者那邊撈出來
     *
     * */
    const store = context.root.$store;
    let unitId = store.getters.account.unit.id;

    //給跳轉分辨用
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
      DELETE = 'delete',
    }

    const iTable = ref(null);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      wkut: '', // 執行機關代碼
      prjno: '', // 標案編號
      sdate: '', // 發生日期
      disasterScript: '', //罹災時災情
      peDate: '', // 提報勞動部日期
      meDate: '', //勞動部核定日期
      action: '', // 動作
      prmNo: '', // 提報文號(PK)
    };

    // 表單物件驗證規則
    let rules = {
      wkut: {}, // 執行機關代碼
      prjno: {}, // 標案編號
      sdate: {}, // 發生日期
      disasterScript: {}, //罹災時災情
      peDate: {}, // 提報勞動部日期
      meDate: {}, //勞動部核定日期
      action: {}, //動作
      prmNo: '', // 提報文號(PK)
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields: [
        {
          key: 'sdate',
          label: '發生日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          key: 'disasterScript',
          label: '罹災時災情',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-left',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'peDate',
          label: '提報勞動部日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          key: 'meDate',
          label: '勞動部核定日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
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
    // 查詢結果及狀態
    // 查詢結果及狀態
    const queryStatus = ref(false);
    const dataPromise = ref(null); //查詢結果
    //
    const notificationService: NotificationService = useNotification();

    const handleQuery = () => {
      table.data = undefined;
      queryStatus.value = true;

      form.wkut = bidProjectKeyProp.value.wkut;
      form.prjno = bidProjectKeyProp.value.prjno;
      dataPromise.value = axios
        .post('/bid-prj-safes/criteria', form) //後端分頁
        .then(({ data }) => {
          if (iTable.value) iTable.value.state.pagination.currentPage = 1;
          table.data = data.content.slice(0, data.content.length); //後端分頁
          table.totalItems = data.totalElements; //後端分頁
        })
        .catch(notificationErrorHandler(notificationService))
        .finally(() => (dataPromise.value = null));
    };
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

    // 新增工安事件
    function toCreateView(wkut, prjno) {
      let bidPrjSafeKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAad002EditInfo', { bidPrjSafeKey, formStatusForward: 'create' });
    }

    // 檢視單一工安事件
    function toDetailView(wkut, prjno, sdate) {
      let bidPrjSafeKey = {
        wkut: wkut,
        prjno: prjno,
        sdate: sdate,
      };
      navigateByNameAndParams('bidAad002Detail', { bidPrjSafeKey, formStatusForward: 'info' });
    }

    // 修改單一工安事件
    const toModifyView = (wkut, prjno, sdate) => {
      let bidPrjSafeKey = {
        wkut: wkut,
        prjno: prjno,
        sdate: sdate,
      };
      navigateByNameAndParams('bidAad002EditInfo', { bidPrjSafeKey, formStatusForward: 'modify' });
    };
    // // 刪除單一工安事件 (暫時拿掉)
    // const toDeleteView = (wkut, prjno, sdate, prmNo) => {
    //   let bidPrjSafeKey = {
    //     wkut: wkut,
    //     prjno: prjno,
    //     sdate: sdate,
    //     prmNo: prmNo,
    //   };
    //   navigateByNameAndParams('bidAad002EditInfo', { bidPrjSafeKey, formStatusForward: 'delete' });
    // };

    function toTable(bidProject) {
      axios
        .post('/bid-prj-safes/bidAae006', bidProject, { responseType: 'blob' })
        .then(res => {
          let blob = new Blob([res.data], { type: 'application/pdf' });
          let url = window.URL.createObjectURL(blob);
          this.$refs.pdfViewer.pdfSrc = url;
          this.$refs.pdfViewer.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    }

    function toLaw() {
      const link = 'http://law.moj.gov.tw/LawClass/LawSingle.aspx?Pcode=N0060001&FLNO=37'; //目標地址
      window.open(link, '_blank'); //新視窗開啟
    }

    return {
      fetchBidProjectInfo,
      $v,
      reset,
      projectInfo,
      formStatusEnum,
      table,
      form,
      handleQuery,
      handlePaginationChanged,
      toCreateView,
      toDetailView,
      toModifyView,
      toTable,
      toLaw,
      // toDeleteView,
    };
  },
};
</script>

<style scoped></style>

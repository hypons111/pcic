<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">開工要件資料</h5>
        </div>
        <div style="text-align: center">訂定「公共工程開工要件注意事項」，並自中華民國109年9月29日生效，請查照 並轉知所屬(轄)機關。</div>
        <div class="mt-2">
          <i-button type="file-earmark-plus" @click="toCreate"></i-button>
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
            <!--#cell為id,row代表這一列的資料-->
            <template #cell(admFileId)="list">
              <!--              {{ table.data[list['index']].admFileId }}-->
              <b-link @click="downloadAdmFile(list.item.admFileId)">{{ list.item.checkDate + '檔案下載連結' }}</b-link>
            </template>
            <template #cell(checkDate)="row">{{ formatDate(row.item.checkDate ? new Date(row.item.checkDate) : null, '-') }}</template>
            <template #cell(createDate)="row"> {{ formatDate(row.item.createDate, '-') }} </template>
            <template #cell(action)="item">
              <i-button
                class="btn-sm"
                type="binoculars"
                @click="toCheck(item.item)"
                v-if="table.data.length !== item['index'] + 1"
              ></i-button>
              <i-button
                class="btn-sm"
                type="pencil-square"
                @click="toModify(item.item)"
                v-if="table.data.length === item['index'] + 1"
              ></i-button>
              <b-button class="btn-sm" style="background-color: #1aa4b7" @click="toTable(item.item)">開工要件表</b-button>
            </template>
          </i-table>
        </div>
      </div>
    </div>
    <i-pdf-viewer ref="pdfViewer" />
  </div>
</template>

<script lang="ts">
import { Reactive, reactive, Ref, ref, toRef, watch } from '@vue/composition-api';
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { Pagination } from '@/shared/model/pagination.model';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidPrjStcService from '@/components/bid/bidService/bid-prj-stc.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import { BidPrjSur2, IBidPrjSur2 } from '@/shared/model/bidModel/bid-prj-sur2';
import { BidPrjStc, IBidPrjStc } from '@/shared/model/bidModel/bid-prj-stc.model';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import { downloadFile } from '@/shared/formatter/common';

export default {
  name: 'bidAab006Info',
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
    const bidPrjStcService = new BidPrjStcService();
    const notificationService: NotificationService = useNotification();
    const pdfViewer = ref(null);
    // 處理後端分頁問題
    const iTable = ref(null);
    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });

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

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      checkDate: {},
      admFileId: {},
      yndA01: {},
      resonA01: {},
      dateA01: {},
      yndA02: {},
      resonA02: {},
      dateA02: {},
      yndA03: {},
      resonA03: {},
      dateA03: {},
      yndA04: {},
      resonA04: {},
      dateA04: {},
      yndA05: {},
      resonA05: {},
      dateA05: {},
      yndA06: {},
      resonA06: {},
      dateA06: {},
      yndA07: {},
      resonA07: {},
      dateA07: {},
      yndA08: {},
      resonA08: {},
      dateA08: {},
      yndA09: {},
      resonA09: {},
      dateA09: {},
      yndA10: {},
      resonA10: {},
      dateA10: {},
      yndA11: {},
      resonA11: {},
      dateA11: {},
      yndA12: {},
      resonA12: {},
      dateA12: {},
      yndA13: {},
      resonA13: {},
      dateA13: {},
      yndA14: {},
      resonA14: {},
      dateA14: {},
      yndA15: {},
      resonA15: {},
      dateA15: {},
      createDate: {},
      createUser: {},
      updateDate: {},
      updateUser: {},
    };

    // 建立表單物件 ref
    let formDefault: IBidPrjStc = new BidPrjStc();
    let form: Reactive<IBidPrjStc> = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields: [
        {
          key: 'checkDate',
          label: '檢核日期',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'createDate',
          label: '資料登錄日期',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'admFileId',
          label: '上傳檔案',
          sortable: false,
          thStyle: 'width:35%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          key: 'action',
          label: '動作',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const handleQuery = () => {
      // table.data = [];
      // table.totalItems = 1;
      // table.data.splice(0, table.data.length, ...mockdata);
      getPrjStcDataFromAxios(bidProjectKeyProp.value);
    };
    function getPrjStcDataFromAxios(queryCriteria) {
      bidPrjStcService
        .getBidPrjStcByCriteria(queryCriteria)
        .then(res => {
          if (res != null) {
            if (iTable.value) iTable.value.state.pagination.currentPage = 1;

            table.data = res.content.slice(0, res.content.length); //後端分頁
            table.totalItems = res.totalElements; //後端分頁
            console.log(res);
          }
        })
        .catch(notificationErrorHandler(notificationService));
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
      navigateByNameAndParams('bidAab006Edit', { bidProjectKey, formStatus: 'create' });
    }

    function toModify(item) {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
        checkDate: item.checkDate,
      };
      navigateByNameAndParams('bidAab006Edit', { bidProjectKey, formStatus: 'modify' });
    }

    function toCheck(item) {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
        checkDate: item.checkDate,
      };
      navigateByNameAndParams('bidAab006Edit', { bidProjectKey, formStatus: 'info' });
    }

    //對應到下載按鈕
    function downloadAdmFile(id: number) {
      axios
        .get(`/adm-files/download/${id}`, { responseType: 'blob' })
        .then((response: AxiosResponse) => downloadFile(response))
        .catch(notificationErrorHandler(notificationService));
    }

    // 開工要件表
    function toTable(item) {
      let bidProject = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
        checkDate: item.checkDate,
      };
      console.log('@@bidProject : ', bidProject);
      axios
        .post('/bid-prj-stcs/BidAae013', bidProject, { responseType: 'blob' })
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
      $v,
      reset,
      bidProjectKeyProp,
      table,
      form,
      handleQuery,
      toCreate,
      toModify,
      toCheck,
      handlePaginationChanged,
      getPrjStcDataFromAxios,
      formatDate,
      downloadAdmFile,
      toTable,
      pdfViewer,
    };
  },
};
</script>

<style scoped></style>

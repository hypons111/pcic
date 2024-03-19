<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <i-button type="file-earmark-plus" @click="toNewStopwk"></i-button>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">停工狀況資料</h5>
        </div>
        <div></div>
        <div class="card-body col-12">
          <div class="container">
            <i-table
              border0
              title="停工狀況資料"
              ref="iTable"
              class="table-sm"
              :itemsUndefinedBehavior="'loading'"
              :items="prjStopwkQueryTable.data"
              :fields="prjStopwkQueryTable.fields"
              :totalItems="prjStopwkQueryTable.totalItems"
              :is-server-side-paging="true"
              @changePagination="handlePaginationChanged($event)"
            >
              <template #cell(remark)="{ item }">
                <i-part-of-message :id="'stopwkRemark' + item.id" :value="item.remark"></i-part-of-message>
              </template>
              <template #cell(startDate)="{ item }">
                {{ item.startDate ? formatDate(new Date(item.startDate), '/') : '(未填)' }}
              </template>
              <template #cell(peDate)="{ item }">
                {{ item.peDate ? formatDate(new Date(item.peDate), '/') : '(未填)' }}
              </template>
              <template #cell(endDate)="{ item }">
                {{ item.endDate ? formatDate(new Date(item.endDate), '/') : '(未填)' }}
              </template>
              <template #cell(stopDateCount)="row">
                <div v-if="row.item.endDate">
                  {{ dateDiffinDays(new Date(row.item.startDate), new Date(row.item.endDate)) }}
                </div>
                <div v-else>尚未復工</div>
              </template>
              <template #cell(action)="row">
                <i-button type="pencil-square" @click="toUpadteStopwkView(row.item)"></i-button>
                <b-button variant="info" @click="toStopwkTable(row.item)">停工報告表</b-button>
                <b-button variant="info" @click="toRecoverywkTable(row.item)">復工報告表</b-button>
              </template>
            </i-table>
          </div>
        </div>
      </div>
    </section>
    <i-pdf-viewer ref="pdfViewer" />
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, onActivated } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidPrjStopwkService from '@/components/bid/bidService/bid-prj-stopwk.service';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'bidAaa004InfoMenu',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    //在keep alive的時候保留原本的project資料
    const tempProjectKey = {
      wkut: '',
      prjno: '',
    };
    setTempProjectKey();

    const bidProjectService = new BidProjectService();
    const bidPrjStopwkService = new BidPrjStopwkService();
    let bidSysCodeTable = new BidSysCodeTable();
    const iTable = ref(null);
    const projectInfo = reactive({ wkut: '', prjno: '' });
    const pdfViewer = ref(null);
    const notificationService: NotificationService = useNotification();

    enum formStatusEnum {
      CREATE = 'create',
      MODIFY = 'modify',
    }
    const prjStopwkQueryTable = reactive({
      fields: [
        {
          key: 'startDate',
          label: '停工日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'stopResonValue',
          label: '停工原因',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'remark',
          label: '停工原因說明',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'peDate',
          label: '預定復工日期',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'endDate',
          label: '實際復工日期',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'stopDateCount',
          label: '停工日數',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    // navigateByNameAndParams('bidAaa001ProjectLoginMenu', { bidProjectKey });

    function dateDiffinDays(startDate: Date, endDate: Date): number {
      let endDateTime = endDate.getTime();
      let startDateTime = startDate.getTime();
      return Math.floor((endDateTime - startDateTime) / (24 * 3600 * 1000));
    }
    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    function setTempProjectKey() {
      if (bidProjectKeyProp.value) {
        tempProjectKey.wkut = bidProjectKeyProp.value.wkut;
        tempProjectKey.prjno = bidProjectKeyProp.value.prjno;
      }
    }

    function getProjectStopwkByCriteria(criteria) {
      bidPrjStopwkService.findProjectAllStopwk(criteria).then(stopWorkData => {
        bidSysCodeTable.getBidSysCodeWithParam('BID_011').then(sysCodeData => {
          stopWorkData.content.forEach(stopWorkElement => {
            let obj = sysCodeData.find(element => {
              return element.dataKey == stopWorkElement.stopReson;
            });
            stopWorkElement.stopResonValue = obj.value;
          });
          prjStopwkQueryTable.data = stopWorkData.content;
          prjStopwkQueryTable.totalItems = stopWorkData.totalElements;
        });
      });
    }

    function fetchProjectAllstopwk(wkut, prjno) {
      getProjectStopwkByCriteria({
        wkut: wkut,
        prjno: prjno,
      });
    }
    function toNewStopwk() {
      let bidProjectStopwkKeyProp = bidProjectKeyProp.value ? bidProjectKeyProp.value : tempProjectKey;
      navigateByNameAndParams('bidAaa004Edit', {
        bidProjectStopwkKey: bidProjectStopwkKeyProp,
        formStatus: formStatusEnum.CREATE,
      });
    }

    function handlePaginationChanged(pagination: Pagination) {
      let queryCriteria = {
        wkut: tempProjectKey.wkut,
        prjno: tempProjectKey.prjno,
      };
      Object.assign(queryCriteria, pagination);
      getProjectStopwkByCriteria(queryCriteria);
    }

    function toUpadteStopwkView(bidPrjStopwkData) {
      const bidPrjStopwkKey = {
        wkut: bidPrjStopwkData.wkut,
        prjno: bidPrjStopwkData.prjno,
        startDate: bidPrjStopwkData.startDate,
      };
      navigateByNameAndParams('bidAaa004Edit', {
        bidProjectStopwkKey: bidPrjStopwkKey,
        formStatus: formStatusEnum.MODIFY,
      });
    }

    //往停工表 //改成下載pdf
    function toStopwkTable(bidPrjStopwkData) {
      const bidProjectStopwkKey = {
        wkut: bidPrjStopwkData.wkut,
        prjno: bidPrjStopwkData.prjno,
        startDate: bidPrjStopwkData.startDate,
      };
      bidPrjStopwkService
        .printPrjStopwkStopReportPDF(bidProjectStopwkKey)
        .then(result => {
          let blob = new Blob([result.data], { type: 'application/pdf' });
          let url = window.URL.createObjectURL(blob);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => {
          notificationService.danger('製表失敗');
        });
    }
    //往復工表  //改成下載pdf
    function toRecoverywkTable(bidPrjStopwkData) {
      const bidProjectStopwkKey = {
        wkut: bidPrjStopwkData.wkut,
        prjno: bidPrjStopwkData.prjno,
        startDate: bidPrjStopwkData.startDate,
      };
      bidPrjStopwkService
        .printPrjStopwkRecoveryReportPDF(bidProjectStopwkKey)
        .then(result => {
          let blob = new Blob([result.data], { type: 'application/pdf' });
          let url = window.URL.createObjectURL(blob);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => {
          notificationService.danger('製表失敗');
        });
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        setTempProjectKey();
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          fetchProjectAllstopwk(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    onActivated(() => {
      if (bidProjectKeyProp.value) {
        setTempProjectKey();
        fetchProjectAllstopwk(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
      } else if (tempProjectKey.wkut.length > 0) {
        fetchProjectAllstopwk(tempProjectKey.wkut, tempProjectKey.prjno);
      }
    });

    return {
      iTable,
      pdfViewer,
      projectInfo,
      prjStopwkQueryTable,
      bidProjectKeyProp,
      toNewStopwk,
      toUpadteStopwkView,
      toStopwkTable,
      toRecoverywkTable,
      dateDiffinDays,
      handlePaginationChanged,
      formatDate,
    };
  },
};
</script>

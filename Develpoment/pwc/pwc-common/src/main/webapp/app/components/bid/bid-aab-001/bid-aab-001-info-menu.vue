<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <i-button type="file-earmark-plus" @click="toNewPrjMonth"></i-button>
    <b-button variant="info" @click="printMonthReport">各月分配表</b-button>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">各月分配資料</h5>
        </div>
        <div></div>
        <div class="card-body col-12">
          <div class="container">
            <i-table
              border0
              title="各月分配資料"
              ref="iTable"
              class="table-sm"
              :itemsUndefinedBehavior="'loading'"
              :items="prjMonthQueryTable.data"
              :fields="prjMonthQueryTable.fields"
              :totalItems="prjMonthQueryTable.totalItems"
              :is-server-side-paging="true"
              hide-card-header
              @changePagination="handlePaginationChanged($event)"
            >
              <template v-slot:cell(yearMonth)="slot"> {{ slot.item.yr }} /{{ slot.item.month }} </template>
              <template v-slot:cell(ptrate)="slot"> {{ slot.item.ptrate }}% </template>
              <template v-slot:cell(pexp)="slot"> {{ commaFormatter(slot.item.pexp, 3) }} </template>
              <template #cell(action)="row">
                <i-button type="pencil-square" @click="toUpadteProjectMonthView(row.item)"></i-button>
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
import BidPrjMonthService from '@/components/bid/bidService/bid-prj-month.service';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { commaFormatter } from '@/shared/formatter/common';

export default {
  name: 'bidAab001InfoMenu',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const tempProjectKey = {
      wkut: '',
      prjno: '',
    };
    setTempProjectKey();

    const bidProjectService = new BidProjectService();
    const bidPrjMonthService = new BidPrjMonthService();

    const iTable = ref(null);
    const pdfViewer = ref(null);
    const projectInfo = reactive({ wkut: '', prjno: '' });
    const notificationService: NotificationService = useNotification();

    enum formStatusEnum {
      CREATE = 'create',
      MODIFY = 'modify',
    }
    const prjMonthQueryTable = reactive({
      fields: [
        {
          key: 'yearMonth',
          label: '年月',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'ptrate',
          label: '總累計預定進度',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'pexp',
          label: '總累計預定支用金額(千元)',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'item',
          label: '預定工作摘要',
          sortable: false,
          thStyle: 'width:30%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    //用上一頁跳轉會出問題的bug
    function setTempProjectKey() {
      if (bidProjectKeyProp.value) {
        tempProjectKey.wkut = bidProjectKeyProp.value.wkut;
        tempProjectKey.prjno = bidProjectKeyProp.value.prjno;
      }
    }

    function handlePaginationChanged(pagination: Pagination) {
      let queryCriteria = {
        wkut: tempProjectKey.wkut,
        prjno: tempProjectKey.prjno,
      };
      Object.assign(queryCriteria, pagination);
      getProjectAllPrjMonth(queryCriteria);
    }

    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    /**
     * 用wkut和prjno包裝成critria(分頁用)撈出該prj所有month schedule
     */
    function getProjectAllPrjMonth(criteria) {
      bidPrjMonthService.getAllPrjMonthByProjectKey(criteria).then(result => {
        prjMonthQueryTable.data = result.content;
        prjMonthQueryTable.totalItems = result.totalElements;
      });
    }

    function getProjectAllMonthByKey(wkut: string, prjno: string) {
      const queryCriteria = {
        wkut: wkut,
        prjno: prjno,
      };
      getProjectAllPrjMonth(queryCriteria);
    }

    //新增
    function toNewPrjMonth() {
      let bidProjectMonthKeyProp = bidProjectKeyProp.value ? bidProjectKeyProp.value : tempProjectKey;
      navigateByNameAndParams('bidAab001Edit', {
        bidProjectMonthKey: bidProjectMonthKeyProp,
        formStatus: formStatusEnum.CREATE,
      });
    }

    //下載pdf
    function printMonthReport() {
      const bidProjectKey = bidProjectKeyProp.value ? bidProjectKeyProp.value : tempProjectKey;
      bidPrjMonthService
        .printMonthPredictReport(bidProjectKey)
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

    //更新
    function toUpadteProjectMonthView(bidProjectMonthData) {
      const bidProjectMonthKey = {
        wkut: bidProjectMonthData.wkut,
        prjno: bidProjectMonthData.prjno,
        syr: bidProjectMonthData.syr,
        month: bidProjectMonthData.month,
      };
      navigateByNameAndParams('bidAab001Edit', {
        bidProjectMonthKey: bidProjectMonthKey,
        formStatus: formStatusEnum.MODIFY,
      });
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          setTempProjectKey();
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          getProjectAllMonthByKey(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    onActivated(() => {
      if (bidProjectKeyProp.value) {
        setTempProjectKey();
        getProjectAllMonthByKey(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
      } else if (tempProjectKey.wkut.length > 0) {
        getProjectAllMonthByKey(tempProjectKey.wkut, tempProjectKey.prjno);
      }
    });

    return {
      iTable,
      pdfViewer,
      projectInfo,
      prjMonthQueryTable,
      bidProjectKeyProp,
      toNewPrjMonth,
      toUpadteProjectMonthView,
      handlePaginationChanged,
      printMonthReport,
      commaFormatter,
    };
  },
};
</script>

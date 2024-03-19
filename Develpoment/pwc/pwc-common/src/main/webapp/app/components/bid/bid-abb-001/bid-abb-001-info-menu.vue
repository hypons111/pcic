<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <div>103.11.19 工程管字第10300404100號函 有關公共工程履約階段聯合查核機制</div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">查核預排行程記錄</h5>
        </div>
        <div class="mt-2 ml-2">
          <i-button type="file-earmark-plus" @click="toAddNew"></i-button>
          <b-button variant="info" @click="getBidGovCheckData">查核督導紀錄</b-button>
          <b-button variant="info" @click="toContactList">聯絡窗口</b-button>
        </div>
        <div class="card-body col-12">
          <div class="container">
            <i-table
              border0
              title="查核預排行程記錄資料"
              ref="iTable"
              class="table-sm"
              :itemsUndefinedBehavior="'loading'"
              :items="prjGovPrecheckTable.data"
              :fields="prjGovPrecheckTable.fields"
              :totalItems="prjGovPrecheckTable.totalItems"
              :is-server-side-paging="true"
              @changePagination="handlePaginationChanged($event)"
            >
              <template #cell(action)="{ item }">
                <i-button type="pencil-square" @click="toEdit(item.wkut, item.prjno, item.checkDate)"></i-button>
                <i-button type="trash" @click="checkDelete(item.wkut, item.prjno, item.checkDate)"></i-button>
              </template>
            </i-table>
          </div>
        </div>
      </div>
    </section>
    <!-- govCheck的彈跳modal -->
    <section>
      <b-modal
        id="bidGovCheckData"
        size="xl"
        title="查核督導紀錄(清單)"
        :header-text-variant="'light'"
        :header-bg-variant="'info'"
        hide-footer
      >
        <div>
          <i-table
            border0
            title="查核督導紀錄"
            class="table-sm"
            :itemsUndefinedBehavior="'loading'"
            :items="prjGovCheckTable.data"
            :fields="prjGovCheckTable.fields"
            :totalItems="prjGovCheckTable.totalItems"
            :is-server-side-paging="true"
            centered
          >
            <!-- ref="iTableGovCheck" -->
            <!-- @changePagination="handlePaginationChanged($event)" -->
          </i-table>
        </div>
        <b-button class="mt-3" block @click="$bvModals.hide('bidGovCheckData')">關閉</b-button>
      </b-modal>
    </section>
    <!-- delte govPrecheck的彈跳modal -->
    <section>
      <b-modal
        id="delteBidGovPrecheck"
        size="xl"
        title="刪除預排查核"
        :header-text-variant="'light'"
        :header-bg-variant="'info'"
        hide-footer
      >
        <div v-if="bidGovPreCheckKey.wkut !== ''">
          <bidAbb001Info :bidGovPreCheckKey="bidGovPreCheckKey" :key="bidGovPreCheckKey.wkut + bidGovPreCheckKey.prjno"></bidAbb001Info>
          <b-row class="justify-content-center">
            <i-button type="x-lg" @click="deleteGovPreCheck(bidGovPreCheckKey)"> </i-button>
            <b-button class="mt-3" block @click="$bvModals.hide('delteBidGovPrecheck')">關閉視窗</b-button>
          </b-row>
        </div>
      </b-modal>
    </section>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, onActivated } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidGovPrecheckService from '@/components/bid/bidService/bid-gov-precheck.service';
import BidGovCheckService from '@/components/bid/bidService/bid-gov-check.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import bidAbb001Info from '@/components/bid/bid-abb-001/bid-abb-001-info.vue';
import { useBvModal } from '@/shared/modal';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';

export default {
  name: 'bidAbb001InfoMenu',
  components: { bidProjectInfo, bidAbb001Info },
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

    const notificationService: NotificationService = useNotification();
    const bidProjectService = new BidProjectService();
    const bidGovPrecheckService = new BidGovPrecheckService();
    const bidGovCheckService = new BidGovCheckService();
    const bidSysCodeTable = new BidSysCodeTable();
    const $bvModals = useBvModal();
    const iTable = ref(null);
    const projectInfo = reactive({ wkut: '', prjno: '' });
    const bidGovPreCheckKey = reactive({ wkut: '', prjno: '', checkDate: '' });

    enum formStatusEnum {
      CREATE = 'create',
      MODIFY = 'modify',
    }
    const prjGovPrecheckTable = reactive({
      fields: [
        {
          key: 'checkDate',
          label: '預定查核日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'method',
          label: '查核方式',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'checkUnit',
          label: '查核主辦機關',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'checker1',
          label: '主辦人',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'checkerTel',
          label: '聯絡電話',
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

    const prjGovCheckTable = reactive({
      fields: [
        {
          key: 'checkDate',
          label: '辨理日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'checkUnit',
          label: '小組名稱',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'roveChk5',
          label: '型式',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'checker',
          label: '參與人員',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'oChker',
          label: '外聘委員',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'score',
          label: '成績',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'detel3',
          label: '其他意見',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.assign(projectInfo, result);
      });
    }

    function setTempProjectKey() {
      if (bidProjectKeyProp.value) {
        tempProjectKey.wkut = bidProjectKeyProp.value.wkut;
        tempProjectKey.prjno = bidProjectKeyProp.value.prjno;
      }
    }

    function getGovPrecheckByCriteria(criteria) {
      bidGovPrecheckService.getGovPrecheckPageByCriteria(criteria).then(govPrecheckData => {
        if (govPrecheckData.content && govPrecheckData.content.length > 0) {
          govPrecheckData.content.forEach(element => {
            if (element.method === '1') {
              element.method = '事先函之';
            } else if (element.method === '2') {
              element.method = '不預告';
            } else {
              element.method = '(未填)';
            }

            element.checkUnit = '(請保密)';
          });
        }

        prjGovPrecheckTable.data = govPrecheckData.content;
        prjGovPrecheckTable.totalItems = govPrecheckData.totalElements;
      });
    }

    function fetchGovPrecheckPage(wkut, prjno) {
      getGovPrecheckByCriteria({
        wkut: wkut,
        prjno: prjno,
      });
    }

    function toAddNew() {
      let bidGovPreCheckKeyProp = bidProjectKeyProp.value ? bidProjectKeyProp.value : tempProjectKey;
      bidGovPrecheckService.findProjectLatestBidGovPrecheck(bidGovPreCheckKeyProp).then(latestDTO => {
        const diffDays = getNumberOfDays(new Date(latestDTO.checkDate), new Date());
        if (!latestDTO || diffDays >= 45) {
          navigateByNameAndParams('bidAbb001Edit', {
            bidGovPreCheckKey: bidGovPreCheckKeyProp,
            formStatus: formStatusEnum.CREATE,
          });
        } else {
          notificationService.danger('本案已有預排查核行程，不開放新增');
        }
      });
    }

    function getNumberOfDays(start: Date, end: Date): number {
      // One day in milliseconds
      const oneDay = 1000 * 60 * 60 * 24;
      // Calculating the time difference between two dates
      const diffInTime = end.getTime() - start.getTime();
      // Calculating the no. of days between two dates
      const diffInDays = Math.round(diffInTime / oneDay);
      return diffInDays;
    }

    function toEdit(wkut, prjno, checkDate) {
      let bidGovPreCheckKeyProp = {
        wkut: wkut,
        prjno: prjno,
        checkDate: checkDate,
      };
      navigateByNameAndParams('bidAbb001Edit', {
        bidGovPreCheckKey: bidGovPreCheckKeyProp,
        formStatus: formStatusEnum.MODIFY,
      });
    }

    function toContactList() {
      navigateByNameAndParams('bidAbb001ContactList', {});
    }

    //給 key 值，讓info 的component運作
    function checkDelete(wkut, prjno, checkDate) {
      bidGovPreCheckKey.wkut = wkut;
      bidGovPreCheckKey.prjno = prjno;
      bidGovPreCheckKey.checkDate = checkDate;
      $bvModals.show('delteBidGovPrecheck');
    }

    //取的bidGovCheck資料
    function getBidGovCheckData() {
      const criteria = {
        wkut: tempProjectKey.wkut,
        prjno: tempProjectKey.prjno,
      };
      bidGovCheckService.getGovCheckPageByCriteria(criteria).then(govCheckData => {
        bidSysCodeTable.getBidSysCodeWithParam('BID_047').then(sysCodeData => {
          govCheckData.content.forEach(govCheckElement => {
            let obj = sysCodeData.find(element => {
              return element.dataKey == govCheckElement.roveChk5;
            });
            govCheckElement.roveChk5 = obj.value;
            prjGovCheckTable.data = govCheckData.content;
            prjGovCheckTable.totalItems = govCheckData.totalElements;
            $bvModals.show('bidGovCheckData');
          });
        });
      });
    }

    function handlePaginationChanged(pagination: Pagination) {
      let queryCriteria = {
        wkut: tempProjectKey.wkut,
        prjno: tempProjectKey.prjno,
      };
      Object.assign(queryCriteria, pagination);
      getGovPrecheckByCriteria(queryCriteria);
    }

    function deleteGovPreCheck(govPrecheckPK) {
      bidGovPrecheckService.deleteBidGovPrecheck(govPrecheckPK).then(() => {
        $bvModals.hide('delteBidGovPrecheck');
        fetchGovPrecheckPage(tempProjectKey.wkut, tempProjectKey.prjno);
      });
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        setTempProjectKey();
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          fetchGovPrecheckPage(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    onActivated(() => {
      if (bidProjectKeyProp.value) {
        setTempProjectKey();
        fetchGovPrecheckPage(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
      } else if (tempProjectKey.wkut.length > 0) {
        fetchGovPrecheckPage(tempProjectKey.wkut, tempProjectKey.prjno);
      }
    });

    return {
      iTable,
      projectInfo,
      prjGovPrecheckTable,
      bidProjectKeyProp,
      $bvModals,
      prjGovCheckTable,
      bidGovPreCheckKey,
      getBidGovCheckData,
      checkDelete,
      handlePaginationChanged,
      toAddNew,
      toEdit,
      deleteGovPreCheck,
      toContactList,
    };
  },
};
</script>

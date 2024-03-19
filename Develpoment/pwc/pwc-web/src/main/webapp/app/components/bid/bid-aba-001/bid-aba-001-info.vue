<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <section class="container mt-2">
      <b-tabs v-if="isMonthHaveData == true" content-class="mt-3">
        <b-tab title="最新進度資料">
          <bidLatestPrjMnth :prjMonthProp="latestPrjMonth"></bidLatestPrjMnth>
        </b-tab>
        <b-tab title="各月份進度資料">
          <div class="card">
            <div class="card-body col-12" v-if="isShowMonthDetail == false">
              <i-table
                class="table-sm"
                :itemsUndefinedBehavior="'loading'"
                :items="prjMonthQueryTable.data"
                :fields="prjMonthQueryTable.fields"
                :totalItems="prjMonthQueryTable.totalItems"
                :is-server-side-paging="true"
                @changePagination="handlePaginationChanged($event)"
              >
                <template v-slot:cell(yearAndMonth)="slot"> {{ slot.item.yr }} /{{ slot.item.month }} </template>
                <template #cell(action)="row">
                  <i-button
                    type="binoculars"
                    @click="changeToShowSingle(row.item.wkut, row.item.prjno, row.item.yr, row.item.month)"
                  ></i-button>
                  <!-- toEditView(row.item) -->
                </template>
              </i-table>
            </div>
            <div class="card-body col-12" v-else>
              <bidPrjMnthInfo :prjMonthkeyProp="monthDetailKey" @changeToList="changeToList"></bidPrjMnthInfo>
            </div>
          </div>
        </b-tab>
      </b-tabs>
      <b-tabs v-else content-class="mt-3">
        <b-tab title="最新進度資料">
          <div class="card">
            <h3>查無標案進度資料</h3>
          </div>
        </b-tab>
      </b-tabs>
    </section>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidPrjMonthService from '@/components/bid/bidService/bid-prj-month.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import bidLatestPrjMnth from '@/components/bid/bid-aba-001/bid-latest-prjmnth.vue';
import bidPrjMnthInfo from '@/components/bid/bid-aba-001/bid-prjmonth-info.vue';
import { BidPrjMonth } from '@/shared/model/bidModel/bid-prj-month.model';
import { Pagination } from '@/shared/model/pagination.model';
export default {
  name: 'bidAba001Info',
  components: { bidProjectInfo, bidLatestPrjMnth, bidPrjMnthInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidProjectService = new BidProjectService();
    const bidPrjMonthService = new BidPrjMonthService();
    const bidCommonService = new BidCommonService();
    const isMonthHaveData = ref(false);
    const isShowMonthDetail = ref(false);
    let monthDetailKey = reactive({
      wkut: '',
      prjno: '',
      syr: '',
      month: '',
    });

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });
    const prjMonthCriteria = { wkut: '', prjno: '' };
    const prjMonthQueryTable = reactive({
      fields: [
        {
          key: 'yearAndMonth',
          label: '年月',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'ptrate',
          label: i18n.t('label.ptrate'),
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'pexp',
          label: i18n.t('label.pexp'),
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'atrate',
          label: i18n.t('label.atrate'),
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'aexp',
          label: i18n.t('label.aexp'),
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'sumt',
          label: i18n.t('label.sumt'),
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

    let latestPrjMonth: BidPrjMonth = reactive(new BidPrjMonth());

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        // bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
        //   result.typeCode = data;
        //   bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(actmthData => {
        //     result.actmth = actmthData;
        //     Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        //   });
        // });
      });
    }

    /**
     * 用wkut和prjno撈出該prj最新一筆month schedule
     */
    function getlatestPrjMonth(wkut, prjno) {
      bidPrjMonthService
        .getLatestPrjMonthByProjectKey(wkut, prjno)
        .then(result => {
          if (result != null) {
            isMonthHaveData.value = true;
            Object.keys(result).forEach(key => (latestPrjMonth[key] = result[key]));
          } else {
            isMonthHaveData.value = false;
          }
        })
        .catch(err => {});
    }
    /**
     * 用wkut和prjno包裝成critria(分頁用)撈出該prj所有month schedule
     */
    function getProjectAllPrjMonth(criteria) {
      bidPrjMonthService.getAllPrjMonthByProjectKey(criteria).then(result => {
        prjMonthQueryTable.data = result.content.slice(0, result.content.length); //後端分頁
        prjMonthQueryTable.totalItems = result.totalElements; //後端分頁
      });
    }
    /**
     * 後端分頁用
     */
    function handlePaginationChanged(pagination: Pagination) {
      Object.assign(prjMonthCriteria, pagination);
      getProjectAllPrjMonth(prjMonthCriteria);
    }

    //切換顯示month的list還是單筆
    function changeToList() {
      isShowMonthDetail.value = false;
    }

    function changeToShowSingle(wkut, prjno, year, month) {
      monthDetailKey.wkut = wkut;
      monthDetailKey.prjno = prjno;
      monthDetailKey.syr = year;
      monthDetailKey.month = month;
      isShowMonthDetail.value = true;
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          getlatestPrjMonth(newValue.wkut, newValue.prjno);
          prjMonthCriteria.wkut = newValue.wkut;
          prjMonthCriteria.prjno = newValue.prjno;
          getProjectAllPrjMonth(prjMonthCriteria);
        }
      },
      { immediate: true }
    );

    return {
      isMonthHaveData,
      isShowMonthDetail,
      prjMonthQueryTable,
      projectInfo,
      latestPrjMonth,
      monthDetailKey,
      changeToShowSingle,
      changeToList,
    };
  },
};
</script>

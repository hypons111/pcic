<template>
  <div>
    <div class="card">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </div>

    <div class="card">
      <b-card-body>
        <b-row>
          <b-col cols="4">
            <h5 class="m-0">查核(督導)紀錄:序號1</h5>
          </b-col>
          <b-col cols="4"> 查核日期:110/09/01</b-col>
          <b-col cols="4"> 上次更新日期:111年03月04日</b-col>
        </b-row>

        <b-tabs content-class="mt-4">
          <b-tab title="查核基本資料" :active="activeTab(0)" @click="changeTabIndex(0)" :disabled="tabDisabled[0]">
            <bid-abc-001-edit-info-1 />
          </b-tab>
          <b-tab title="查核委員" :active="activeTab(1)" @click="changeTabIndex(1)" :disabled="tabDisabled[1]">
            <bid-abc-001-edit-info-2 :bidProjectKeyProp="bidProjectKeyProp" />
          </b-tab>
          <b-tab title="參與人員" :active="activeTab(2)" @click="changeTabIndex(2)" :disabled="tabDisabled[2]">
            <bid-abc-001-edit-info-3 />
          </b-tab>
          <b-tab title="優點" :active="activeTab(3)" @click="changeTabIndex(3)" :disabled="tabDisabled[3]">
            <bid-abc-001-edit-info-4 />
          </b-tab>
          <b-tab title="檢驗報告及評分" :active="activeTab(4)" @click="changeTabIndex(4)" :disabled="tabDisabled[4]">
            <bid-abc-001-edit-info-5 />
          </b-tab>
          <b-tab title="查核缺失" :active="activeTab(5)" @click="changeTabIndex(5)" :disabled="tabDisabled[5]">
            <bid-abc-001-edit-info-6 />
          </b-tab>
          <b-tab title="查核相片" :active="activeTab(6)" @click="changeTabIndex(6)" :disabled="tabDisabled[6]">
            <bid-abc-001-edit-info-7 />
          </b-tab>
          <b-tab title="查核作業檢查表" :active="activeTab(7)" @click="changeTabIndex(7)" :disabled="tabDisabled[7]">
            <bid-abc-001-edit-info-8 />
          </b-tab>
          <b-tab title="查核懲處資料" :active="activeTab(8)" @click="changeTabIndex(8)" :disabled="tabDisabled[8]">
            <bid-abc-001-edit-info-9 />
          </b-tab>
        </b-tabs>

        <!-- <div class="text-center mt-2">
          <i-button type="tag"></i-button>
          <i-button type="save"></i-button>
          <i-button type="arrow-counterclockwise" @click="backToPreviousPage"></i-button>
          <i-button type="x-circle"></i-button>
          <i-button type="projectinfo"></i-button>
          <i-button type="reportinfo"></i-button>
        </div> -->
      </b-card-body>
    </div>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import { watch, reactive, ref, Ref, toRef } from '@vue/composition-api';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { parseRocDate } from '@/shared/date/minguo-calendar-utils';
import { navigateByNameAndParams } from '@/router/router';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import BidAbc001EditInfo1 from '@/components/bid/bid-abc-001/bid-abc-001-editInfo1.vue';
import BidAbc001EditInfo2 from '@/components/bid/bid-abc-001/bid-abc-001-editInfo2.vue';
import BidAbc001EditInfo3 from '@/components/bid/bid-abc-001/bid-abc-001-editInfo3.vue';
import BidAbc001EditInfo4 from '@/components/bid/bid-abc-001/bid-abc-001-editInfo4.vue';
import BidAbc001EditInfo5 from '@/components/bid/bid-abc-001/bid-abc-001-editInfo5.vue';
import BidAbc001EditInfo6 from '@/components/bid/bid-abc-001/bid-abc-001-editInfo6.vue';
import BidAbc001EditInfo7 from '@/components/bid/bid-abc-001/bid-abc-001-editInfo7.vue';
import BidAbc001EditInfo8 from '@/components/bid/bid-abc-001/bid-abc-001-editInfo8.vue';
import BidAbc001EditInfo9 from '@/components/bid/bid-abc-001/bid-abc-001-editInfo9.vue';

export default {
  name: 'bidAbc001EditInfo',
  props: {
    engYearreport: {
      type: Object,
      required: false,
    },
    bidProjectKeyProp: {
      type: Object,
      required: false,
    },
  },
  components: {
    bidProjectInfo,
    BidAbc001EditInfo1,
    BidAbc001EditInfo2,
    BidAbc001EditInfo3,
    BidAbc001EditInfo4,
    BidAbc001EditInfo5,
    BidAbc001EditInfo6,
    BidAbc001EditInfo7,
    BidAbc001EditInfo8,
    BidAbc001EditInfo9,
  },

  setup(props) {
    const bidProjectKeyProps: Ref<any> = toRef(props, 'bidProjectKeyProp');
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();

    const isReload = ref(false);
    const dataPromise = ref(null);
    const $bvModal = useBvModal();

    const tabIndex = ref(0);
    const tabDisabled = reactive([false, false, false, false, false, false, false, false, false]);

    const activeTab = (index: number) => {
      if (tabIndex.value === index) {
        return true;
      } else {
        return false;
      }
    };

    const changeTabIndex = (index: number) => {
      tabIndex.value = index;
    };

    const toQueryView = () => {
      handleBack({ isReload: isReload.value, form: form });
      isReload.value = false;
    };

    const reset = () => {
      switch (tabIndex.value) {
        case 0:
          break;
        case 1:
          eng0202BasicInfo.value.reset();
          break;
        case 2:
          eng0202EngrRoster.value.reset();
          break;
        case 3:
          break;
        case 4:
          eng0202ServiceCase.value.reset();
          break;
        case 5:
          eng0202Questionnaire.value.reset();
          break;
        case 6:
          engReviewLog.value.reset();
      }
    };

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAbc001Query');
    }

    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
          result.typeCode = data;
          Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        });
      });
    }

    const createDefaultValue = data => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      reset();
    };

    watch(
      bidProjectKeyProps,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    return {
      dataPromise,
      tabIndex,
      activeTab,
      changeTabIndex,
      tabDisabled,
      toQueryView,
      reset,
      backToPreviousPage,
      projectInfo,
      bidProjectKeyProps,
    };
  },
};
</script>

<style></style>

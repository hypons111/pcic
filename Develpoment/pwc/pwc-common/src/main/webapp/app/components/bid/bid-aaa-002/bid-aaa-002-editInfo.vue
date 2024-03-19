<template>
  <div class="card">
    <b-card-body>
      <h5 class="m-0">
        <font-awesome-icon icon="project-diagram"></font-awesome-icon>
        標案資料
      </h5>
      <div class="card-body col-11">
        <!-- 執行單位 -->
        <b-row>
          <i-form-group-check :label="'執行單位'" :label-cols="2" :content-cols="10" class="col-12" label-align-md="left">
            {{ wkutName }}
          </i-form-group-check>
        </b-row>

        <!-- 標案編號-->
        <b-row>
          <i-form-group-check :label="'標案編號'" label-align-md="left">
            {{ projectInfo.prjno }}
          </i-form-group-check>
        </b-row>
      </div>
    </b-card-body>
    <b-card-body>
      <b-tabs content-class="mt-4">
        <b-tab title="基本資料1" :active="activeTab(0)" @click="changeTabIndex(0)" >
          <bid-aaa-002-edit-info-1 :bidProjectInfo="projectInfo" ref="bidAaa002EditInfo1" />
        </b-tab>
        <b-tab title="基本資料1(續)" :active="activeTab(1)" @click="changeTabIndex(1)" >
          <bid-aaa-002-edit-info-12 :bidProjectInfo="projectInfo" ref="bidAaa002EditInfo12" />
        </b-tab>
        <b-tab title="基本資料2" :active="activeTab(2)" @click="changeTabIndex(2)" >
          <bid-aaa-002-edit-info-2 :bidProjectInfo="projectInfo" ref="bidAaa002EditInfo2" />
        </b-tab>
        <b-tab title="基本資料3" :active="activeTab(3)" @click="changeTabIndex(3)" >
          <bid-aaa-002-edit-info-3 :bidProjectInfo="projectInfo" ref="bidAaa002EditInfo3" />
        </b-tab>
        <b-tab title="基本資料4" :active="activeTab(4)" @click="changeTabIndex(4)" >
          <bid-aaa-002-edit-info-4 :bidProjectInfo="projectInfo" ref="bidAaa002EditInfo4" />
        </b-tab>
        <b-tab title="基本資料5" :active="activeTab(5)" @click="changeTabIndex(5)" >
          <bid-aaa-002-edit-info-5 :bidProjectInfo="projectInfo" ref="bidAaa002EditInfo5" />
        </b-tab>
      </b-tabs>

      <div class="text-center mt-2">
        <i-button type="tag" @click="submitForm(false)" v-promise-btn="{ promise: dataPromise }"></i-button>
        <i-button type="save" @click="submitForm(true)" v-promise-btn="{ promise: dataPromise }"></i-button>
        <i-button type="arrow-counterclockwise" @click="backToPreviousPage"></i-button>
        <i-button type="x-circle" @click="reset" ></i-button>
        <b-button variant="info" @click="toReport1">基本資料表</b-button>
        <b-button variant="info" @click="toReport2">開工報告表</b-button>
        <!-- <div class="text-muted mt-1">目前分頁：{{ tabIndex + 1 }} / 7</div> -->
      </div>
    </b-card-body>
    <i-pdf-viewer ref="pdfViewer" />
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidUnit10Servie from '@/components/bid/bidService/bid-unit10.service';
import { IBidProject, BidProject } from '@/shared/model/bidModel/bid-project.model';
import { watch, reactive, ref, toRef } from '@vue/composition-api';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler, notificationErrorHandlerWithErrorParam } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { parseRocDate } from '@/shared/date/minguo-calendar-utils';
import { navigateByNameAndParams } from '@/router/router';
import BidAaa002EditInfo1 from '@/components/bid/bid-aaa-002/bid-aaa-002-editInfo1.vue';
import BidAaa002EditInfo12 from '@/components/bid/bid-aaa-002/bid-aaa-002-editInfo12.vue';
import BidAaa002EditInfo2 from '@/components/bid/bid-aaa-002/bid-aaa-002-editInfo2.vue';
import BidAaa002EditInfo3 from '@/components/bid/bid-aaa-002/bid-aaa-002-editInfo3.vue';
import BidAaa002EditInfo4 from '@/components/bid/bid-aaa-002/bid-aaa-002-editInfo4.vue';
import BidAaa002EditInfo5 from '@/components/bid/bid-aaa-002/bid-aaa-002-editInfo5.vue';

export default {
  name: 'bidAaa002EditInfo',
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  components: {
    BidAaa002EditInfo1,
    BidAaa002EditInfo12,
    BidAaa002EditInfo2,
    BidAaa002EditInfo3,
    BidAaa002EditInfo4,
    BidAaa002EditInfo5,
  },

  setup(props) {
    const pdfViewer = ref(null);
    const bidAaa002EditInfo1 = ref(null);
    const bidAaa002EditInfo12 = ref(null);
    const bidAaa002EditInfo2 = ref(null);
    const bidAaa002EditInfo3 = ref(null);
    const bidAaa002EditInfo4 = ref(null);
    const bidAaa002EditInfo5 = ref(null);

    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidProjectService = new BidProjectService();
    const bidUnit10Service = new BidUnit10Servie();

    const projectInfo = reactive(new BidProject());

    let wkutName = ref('');

    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        bidUnit10Service.findAdmAgentyCode(wkut).then(admAgent => {
          wkutName.value = admAgent.orgName;
        });
      });
    }

    const isReload = ref(false);
    const dataPromise = ref(null);
    const $bvModal = useBvModal();
    const notificationService = useNotification();

    const tabIndex = ref(0);
    const tabDisabled = reactive([false, false, false, false, false, false]);

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
          bidAaa002EditInfo1.value.reset();
          break;
        case 1:
          bidAaa002EditInfo12.value.reset();
          break;
        case 2:
          bidAaa002EditInfo2.value.reset();
          break;
        case 3:
          bidAaa002EditInfo3.value.reset();
          break;
        case 4:
          bidAaa002EditInfo4.value.reset();
          break;
        case 5:
          bidAaa002EditInfo5.value.reset();
          break;
      }
    };

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAaa002Query');
    }

    function submitForm(isCheck) {
      if (isCheck) {
        Promise.all([
          bidAaa002EditInfo1.value.checkValidity(),
          bidAaa002EditInfo12.value.checkValidity(),
          bidAaa002EditInfo2.value.checkValidity(),
          bidAaa002EditInfo3.value.checkValidity(),
          bidAaa002EditInfo4.value.checkValidity(),
          bidAaa002EditInfo5.value.checkValidity(),
        ]).then((res: Array<boolean>) => {
          if (res.every(item => item)) {
            $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
              if (isOK) {
                putForm();
              }
            });
          } else {
            $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
          }
        });
      } else {
        $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
          if (isOK) {
            putForm();
          }
        });
      }
    };

    function putForm() {
      Object.assign(projectInfo, bidAaa002EditInfo1.value.putForm());
      Object.assign(projectInfo, bidAaa002EditInfo12.value.putForm());
      Object.assign(projectInfo, bidAaa002EditInfo2.value.putForm());
      Object.assign(projectInfo, bidAaa002EditInfo3.value.putForm());
      Object.assign(projectInfo, bidAaa002EditInfo4.value.putForm());
      Object.assign(projectInfo, bidAaa002EditInfo5.value.putForm());

      const formData = new FormData();
      formData.append('form', new Blob([JSON.stringify(projectInfo)], { type: 'application/json' }));
      formData.append('ckpFile', bidAaa002EditInfo5.value.putForm().ckpFile);
      formData.append('ckaFile', bidAaa002EditInfo5.value.putForm().ckaFile);
      formData.append('ckdFile', bidAaa002EditInfo5.value.putForm().ckdFile);
      bidProjectService.updateBidProject(formData)
        .then(() => {
          fetchBidProjectInfo(projectInfo.wkut, projectInfo.prjno);
        })
        .catch(err => {
          if (err.response && err.response.data.errorKey === 'bidValidError') {
            errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
            $bvModals.show('valid-error-modal');
          } else {
            notificationErrorHandlerWithErrorParam(err, notificationService);
          }
        });
    }

    function toReport1() {
      axios
        .post('/bid-projects/bidAae001', projectInfo, {responseType: 'blob'})
        .then(res => {
          let blob = new Blob([res.data], { type: "application/pdf" });
          let url = window.URL.createObjectURL(blob);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    }

    function toReport2() {
      axios
        .post('/bid-projects/bidAae002', projectInfo, {responseType: 'blob'})
        .then(res => {
          let blob = new Blob([res.data], { type: "application/pdf" });
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
          changeTabIndex(0);
        }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      dataPromise,
      tabIndex,
      activeTab,
      changeTabIndex,
      pdfViewer,
      bidAaa002EditInfo1,
      bidAaa002EditInfo12,
      bidAaa002EditInfo2,
      bidAaa002EditInfo3,
      bidAaa002EditInfo4,
      bidAaa002EditInfo5,
      toQueryView,
      reset,
      submitForm,
      backToPreviousPage,
      toReport1,
      toReport2,
      wkutName,
    };
  },
};
</script>

<style>
.nav-tabs .nav-link {
  color: #007bff;
}
.nav-tabs .nav-link.active {
  font-weight: bold;
}
</style>

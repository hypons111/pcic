<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">外籍營造工核定狀況</h5>
        </div>

        <div class="card-body col-12">
          <div>
            <i-button size="md" type="folder-plus" variant="outline-secondary" @click="toAdd"></i-button>
          </div>
          <i-table
            border0
            ref="iTable"
            class="table-sm"
            :itemsUndefinedBehavior="'loading'"
            :items="table.data"
            :fields="table.fields"
            :totalItems="table.totalItems"
            :is-server-side-paging="true"
            @changePagination="handlePaginationChanged($event)"
          >
            <template #cell(action)="row">
              <!-- <i-button type="binoculars" @click="toTable(row.item)"></i-button> -->
              <i-button type="pencil-square" @click="toEdit(row.item)"></i-button>
              <b-button style="background-color: #1aa4b7" @click="toTable(row.item)">外勞核定表</b-button>
            </template>
          </i-table>

          <b-row>
            <b-col cols="1">參考:</b-col>
            <b-col cols="10"
              ><span style="color: red">就業服務法</span>&nbsp;&nbsp;<a
                href="https://law.moj.gov.tw/LawClass/LawSingle.aspx?pcode=N0090001&flno=43"
                target="_blank"
                >第四十三條</a
              >&nbsp;&nbsp;<span>除本法令有規定，外國人位精雇主申請許可，不得在中華民國境內工作...</span></b-col
            >
          </b-row>
          <b-row>
            <b-col cols="1">參考:</b-col>
            <b-col cols="10"
              ><span style="color: red">就業服務法</span>&nbsp;&nbsp;<a
                href="https://law.moj.gov.tw/LawClass/LawSingle.aspx?pcode=N0090001&flno=64"
                target="_blank"
                >第六十四條</a
              >&nbsp;&nbsp;<span>第十款雇主聘僱外國人在中華民國境內從事之工作，除本法令有規定外，以下列各款違限...</span><br /><span
                >十、為因應國家重要建設工程或經濟社會發展需要，經中央主管機關指訂之工作。</span
              ></b-col
            >
          </b-row>
          <b-row>
            <b-col cols="1"></b-col>
            <b-col cols="10"
              ><span style="color: red">勞動部</span
              ><span>專案核定民間機構投資重大經建工程及政府機關或公營事業機構發包興建之重要建設工程聘僱外及營造工</span>&nbsp;&nbsp;<a
                href="https://laws.mol.gov.tw/FLAW/FLAWDAT0202.aspx?id=FL042378"
                target="_blank"
                >作業規範</a
              ></b-col
            >
          </b-row>
          <b-row>
            <b-col cols="1"></b-col>
            <b-col cols="10"
              ><span style="color: red">勞動部</span
              ><span>外國人從事就業服務法第四十六條第一項第八款至第十一款工作資格及審查標準</span>&nbsp;&nbsp;<a
                href="https://laws.mol.gov.tw/FLAW/FLAWDAT01.aspx?id=FL028067"
                target="_blank"
                >審查標準</a
              >&nbsp;&nbsp;<a href="https://laws.mol.gov.tw/FLAW/FLAWDOC01.aspx?id=FL028067&flno=17" target="_blank">第17條</a
              >&nbsp;&nbsp;<a href="https://laws.mol.gov.tw/FLAW/FLAWDOC01.aspx?id=FL028067&flno=18" target="_blank">第18條</a
              >&nbsp;&nbsp;<a href="https://laws.mol.gov.tw/FLAW/FLAWDOC01.aspx?id=FL028067&flno=19" target="_blank">第19條</a></b-col
            >
          </b-row>
          <b-row>
            <b-col cols="1">參考:</b-col>
            <b-col cols="10"
              ><span style="color: red">就業服務法</span>&nbsp;&nbsp;<a
                href="https://law.moj.gov.tw/LawClass/LawSingle.aspx?pcode=N0090001&flno=47"
                target="_blank"
                >第五十七條</a
              >&nbsp;&nbsp;<span>雇主聘雇外國人不得有下列情事:...</span><br />
              <span>四、未經許可，指派所聘僱從事第四十六條第一項第八款至第十款規定工作之外國人變更工作場所。 </span>
            </b-col>
          </b-row>
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
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidPrjStopwkService from '@/components/bid/bidService/bid-prj-stopwk.service';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import axios from 'axios';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required } from '@/shared/validators';
import { handleBack } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
export default {
  name: 'bidAad005Info',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const pdfViewer = ref(null);

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

    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
    }

    const dataPromise = ref(null);
    const notificationService: NotificationService = useNotification();

    const table = reactive({
      fields: [
        {
          //核定日期
          key: 'sdate',
          label: i18n.t('label.frDate'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          //核定人數
          key: 'frWkr',
          label: i18n.t('label.frWkr'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //核定文號
          key: 'frPno',
          label: i18n.t('label.frPno'),
          sortable: false,
          // thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        // {
        //   //核定說明
        //   key: 'frRmk',
        //   label: i18n.t('label.frRmk'),
        //   sortable: false,
        //   thStyle: 'width:20%',
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        // },
        {
          key: 'action',
          label: '功能',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: 'width:10%',
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

    // function getProjectStopwkByCriteria(criteria) {
    //   bidPrjStopwkService.findProjectAllStopwk(criteria).then(stopWorkData => {
    //     bidSysCodeTable.getBidSysCodeWithParam('BID_011').then(sysCodeData => {
    //       stopWorkData.content.forEach(stopWorkElement => {
    //         let obj = sysCodeData.find(element => {
    //           return element.dataKey == stopWorkElement.stopReson;
    //         });
    //         stopWorkElement.stopResonValue = obj.value;
    //       });
    //       table.data = stopWorkData.content;
    //       table.totalItems = stopWorkData.totalElements;
    //     });
    //   });
    // }

    // function fetchProjectAllstopwk(wkut, prjno) {
    //   getProjectStopwkByCriteria({
    //     wkut: wkut,
    //     prjno: prjno,
    //   });
    // }

    function handlePaginationChanged(pagination: Pagination) {
      let queryCriteria = {
        wkut: tempProjectKey.wkut,
        prjno: tempProjectKey.prjno,
      };
      Object.assign(queryCriteria, pagination);
      // getProjectStopwkByCriteria(queryCriteria);
    }

    // function toSee(bidPrjStopwkData) {
    //   const bidPrjStopwkKey = {
    //     wkut: bidPrjStopwkData.wkut,
    //     prjno: bidPrjStopwkData.prjno,
    //     startDate: bidPrjStopwkData.startDate,
    //   };
    //   navigateByNameAndParams('bidAaa004Edit', {
    //     bidProjectStopwkKey: bidPrjStopwkKey,
    //     formStatus: formStatusEnum.MODIFY,
    //   });
    // }

    const handleQuery = () => {
      table.data = undefined;
      const projectPK = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      dataPromise.value = axios
        .post('/bid-prj-frwkr/criteria-jpa', projectPK) //後端端分頁(呼叫後端)\
        .then(({ data }) => {
          // table.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
          // table.totalItems = data.length; //前端分頁
          table.data = data.content.slice(0, data.content.length); //後端分頁
          table.totalItems = data.totalElements; //後端分頁
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    //新增
    const toAdd = () => {
      navigateByNameAndParams('bidAad005Edit', {
        bidProjectKeyProp: props.bidProjectKey,
        formStatus: FormStatusEnum.CREATE,
        isNotKeepAlive: false,
      });
    };

    //檢視
    const toEdit = bidProjectKeyProp => {
      navigateByNameAndParams('bidAad005Edit', {
        bidProjectKeyProp: bidProjectKeyProp,
        formStatus: FormStatusEnum.MODIFY,
        isNotKeepAlive: false,
      });
    };

    //製報表
    const toTable = bidProject => {
      let criteria = {
        wkut: bidProject.wkut,
        prjno: bidProject.prjno,
        sdate: bidProject.sdate,
      };
      axios
        .post('/bid-prj-frwkr/bidAae021', criteria, { responseType: 'blob' })
        .then(res => {
          let blob = new Blob([res.data], { type: 'application/pdf' });
          let url = window.URL.createObjectURL(blob);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    };

    watch(
      bidProjectKeyProp,
      newValue => {
        setTempProjectKey();
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          handleQuery();

          // fetchProjectAllstopwk(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    onActivated(() => {
      if (bidProjectKeyProp.value) {
        setTempProjectKey();
        // fetchProjectAllstopwk(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
      } else if (tempProjectKey.wkut.length > 0) {
        // fetchProjectAllstopwk(tempProjectKey.wkut, tempProjectKey.prjno);
      }
    });

    return {
      iTable,
      projectInfo,
      table,
      bidProjectKeyProp,
      // toSee,
      dataPromise,
      // dateDiffinDays,
      handlePaginationChanged,
      handleQuery,
      toTable,
      pdfViewer,
      toAdd,
      toEdit,
      FormStatusEnum,
    };
  },
};
</script>

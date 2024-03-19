<template>
  <b-card no-body>
    <b-card-header>
      <h5 class="m-0">
        <font-awesome-icon icon="project-diagram"></font-awesome-icon>
        年度業務報告
      </h5>
    </b-card-header>
    <b-card-body>
      <b-tabs content-class="mt-4">
        <b-tab title="授權服務書" :active="activeTab(0)" @click="changeTabIndex(0)" :disabled="tabDisabled[0]">
          <eng-0202-authorization-service :engYearreport="engYearreportRef" ref="eng0202AuthorizationService" />
        </b-tab>
        <b-tab title="公司基本資料" :active="activeTab(1)" @click="changeTabIndex(1)" :disabled="tabDisabled[1]">
          <eng-0202-basic-info :engYearreport="engYearreportRef" ref="eng0202BasicInfo"/>
        </b-tab>
        <b-tab title="執業技師人員名冊" :active="activeTab(2)" @click="changeTabIndex(2)" :disabled="tabDisabled[2]">
          <eng-0202-engr-roster :engYearreport="engYearreportRef" ref="eng0202EngrRoster"/>
        </b-tab>
        <b-tab title="年度異動核備情形" :active="activeTab(3)" @click="changeTabIndex(3)" :disabled="tabDisabled[3]">
          <eng-0202-change-situation :engYearreport="engYearreportRef" ref="eng0202ChangeSituation" />
        </b-tab>
        <b-tab title="年度服務案件統計" :active="activeTab(4)" @click="changeTabIndex(4)" :disabled="tabDisabled[4]">
          <eng-0202-service-case :engYearreport="engYearreportRef" ref="eng0202ServiceCase" />
        </b-tab>
        <b-tab title="公司調查表" :active="activeTab(5)" @click="changeTabIndex(5)" :disabled="tabDisabled[5]">
          <eng-0202-questionnaire :engYearreport="engYearreportRef" ref="eng0202Questionnaire" />
        </b-tab>
        <b-tab title="案件審核" :active="activeTab(6)" @click="changeTabIndex(6)">
          <b-container>
            <eng-review-log :engReviewLog="engYearreport? engYearreport.engReviewLog: null" ref="engReviewLog">
              <template #page-end>
                <div class="text-right mt-4">
                  <i-button type="clipboard-plus"></i-button>
                  <i-button type="clipboard-minus"></i-button>
                </div>
              </template>
            </eng-review-log>
          </b-container>
        </b-tab>
      </b-tabs>

      <div class="text-center mt-4">
        <i-button type="chevron-bar-left" @click="tabIndex > 0 ? tabIndex-- : ''"></i-button>
        <i-button type="chevron-bar-right" @click="nextTab"></i-button>
      </div>

      <div class="text-center mt-2">
        <i-button v-show="true" type="send-check" @click="submitForm(true)" v-promise-btn="{ promise: dataPromise }"></i-button>
        <i-button v-show="true" type="save" @click="submitForm(false)" v-promise-btn="{ promise: dataPromise }"></i-button>
        <i-button type="x-circle" @click="reset()"></i-button>
        <i-button type="arrow-left" @click="toQueryView"></i-button>
        <div class="text-muted mt-1">目前分頁：{{ tabIndex + 1 }} / 7</div>
      </div>
    </b-card-body>
  </b-card>
</template>

<script lang="ts">
import axios from 'axios';
import { watch, reactive, ref, } from '@vue/composition-api';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { parseRocDate } from '@/shared/date/minguo-calendar-utils';
import EngReviewLog from '@/components/eng/eng-review-log/eng-review-log.vue';
import Eng0202BasicInfo from '@/components/eng/eng0202/eng0202-basic-info.vue';
import Eng0202EngrRoster from '@/components/eng/eng0202/eng0202-engr-roster.vue';
import Eng0202ChangeSituation from '@/components/eng/eng0202/eng0202-change-situation.vue';
import Eng0202ServiceCase from '@/components/eng/eng0202/eng0202-service-case.vue';
import Eng0202Questionnaire from '@/components/eng/eng0202/eng0202-questionnaire.vue';
import Eng0202AuthorizationService from '@/components/eng/eng0202/eng0202-authorization-service.vue';

export default {
  name: 'Eng0202EditInfo',
  props: {
    engYearreport: {
      type: Object,
      required: false,
    },
  },
  components: {
    EngReviewLog,
    Eng0202BasicInfo,
    Eng0202EngrRoster,
    Eng0202ChangeSituation,
    Eng0202ServiceCase,
    Eng0202Questionnaire,
    Eng0202AuthorizationService,
  },

  setup(props) {

    const isReload = ref(false);
    const dataPromise = ref(null);
    const $bvModal = useBvModal();
    const notificationService = useNotification();

    const tabIndex = ref(0);
    const tabDisabled = reactive([false, false, false, false, false, false]);

    const engYearreportRef = ref(null);
    const engReviewLog = ref(null);
    const eng0202BasicInfo = ref(null);
    const eng0202EngrRoster = ref(null);
    const eng0202ChangeSituation = ref(null);
    const eng0202ServiceCase = ref(null);
    const eng0202Questionnaire = ref(null);
    const eng0202AuthorizationService = ref(null);

    const testData = [
      {
        no: '1',
        engYearreportId: '123456',
        engYearreportYyyy: '108',
        compName: '台灣世曦工程顧問股份有限公司',
        compIdno: '28412550',
        licenseNo: '工程技顧登字第000048號',
        createTime: parseRocDate('108/06/12', '/'),
        fillStatus: '2',
        status: '3',
        remark:
          '至於何時才會回暖？中央氣象局表示，週五過後冷空氣減弱，水氣明顯減少，天氣將逐漸回暖，恢復為「東偶雨西晴」的天氣型態。而氣象局也特別提醒，這波寒流影響下，北部與東北部2000至2500公尺以上，以及其他地區3000公尺以上高山有望降雪，民眾行駛高山公路務必留意路況安全​。',
        engReviewLog: [
          {
            itemNo: '1',
            reviewStatus: '已送出',
            reviewStaff: '蔡再發',
            reviewTime: '2022-02-02 18:43:36',
            reviewDesc: '本服務係為簡政便民提升系統填報效率，委託方如送出同意授權仍得於六月三十日（含）前撤回授權，並自行負責至系統填報相關欄位',
          },
          {
            itemNo: '2',
            reviewStatus: '已收文',
            reviewStaff: '王小名',
            reviewTime: '2022-02-05 09:23:49',
            reviewDesc: 'pass!',
          },
        ],
        engYearreportEngr: [
          {
            no: '1',
            engrName: '蘇玫心',
            engrIdno: 'P220723812',
            engrLicenseNo: '技執字第002151號',
            subjectList: '3,5',
            remark: '又擊斃俄指揮官！烏克蘭打退坦克軍團 畫面超震撼',
          },
          {
            no: '2',
            engrName: '蘇季鴻',
            engrIdno: 'A129371472',
            engrLicenseNo: '技執字第008174號',
            subjectList: '1',
            remark: '現代人對車子非常講究，相關的設備知識更要清楚了解，才能安心上路。',
          },
          {
            no: '3',
            engrName: '羅博智',
            engrIdno: 'L122269414',
            engrLicenseNo: '技執字第006649號',
            subjectList: '5,6,7',
            remark: '印度神童新預言曝光 這天小心5件事 他苦勸：寧可信其有',
          },
        ],
        yearreportType: '',
        compId: '',
        establishDate: null,
        addrZip: '',
        addrCity: '',
        addrTown: '',
        addrOther: '',
        masterName: '',
        capital: '',
        saleAreaList: '',
        execType: '',
        engrIdno: '',
        engrName: '',
        engrSubjectList: '',
        staffNumForeign: '',
        incomeTotal: 0,
        incomeOperating: '',
        incomeNonOperating: '',
        incomeTechService: '',
        incomeTechServiceDomestic: 0,
        incomeTechServiceForeign: 0,
        isIncomeTechServiceMatch: '',
        incomeNonTechService: 0,
        feeOperating: '',
        feeNonOperating: '',
        feeDepreciation: '',
        feeIndirectTax: '',
        feeStaff: '',
        feeRd: '',
        feeTrain: '',
        countFinancing: '',
        feeFinancing: '',
        originalIncomeTotal: 0,
        originalIncomeOperating: 0,
        originalIncomeNonOperating: 0,
        originalIncomeNonTechService: 0,
        revenueGrowthRatio: 0,
        revenueLastYear: 0,
        staffNumTotal: 0,
        staffNumAdministration: '',
        staffNumEngineering: '',
        staffNumEngr: '',
        staffOutputValue: 0,
        netProfitRatio: 0,
        netProfitAfterTax: '',
        netIncome: '',
        netValueTurnoverRatio: 0,
        income: '',
        netValue: '',
        flowRatio: 0,
        flowAssets: '',
        flowLiabilities: '',
        preparedByName: '',
        preparedByTel: '',
        notFillDesc: '',
        reviewStaff: '',
        reviewTime: '',
        reviewDesc: '',
        createUser: '',
        updateUser: '',
        updateTime: '',
        transferFrom: '',
        transferTime: '',
      },
      {
        no: '2',
        engYearreportId: '123456',
        engYearreportYyyy: '105',
        // compName: '台灣世曦工程顧問股份有限公司',
        compIdno: '28412550',
        licenseNo: '工程技顧登字第000048號',
        createTime: parseRocDate('108/06/12', '/'),
        fillStatus: '2',
        status: '3',
        remark: '123',
        engReviewLog: [
          {
            itemNo: '1',
            reviewStatus: '已送出',
            reviewStaff: '蔡再發',
            reviewTime: '2022-02-02 18:43:36',
            reviewDesc: '本服務係為簡政便民提升系統填報效率，委託方如送出同意授權仍得於六月三十日（含）前撤回授權，並自行負責至系統填報相關欄位',
          },
          {
            itemNo: '2',
            reviewStatus: '已收文',
            reviewStaff: '王小名',
            reviewTime: '2022-02-05 09:23:49',
            reviewDesc: 'pass!',
          },
        ],
      },
    ];


    watch(() => props.engYearreport, (newVal: object) => {
      engYearreportRef.value = Object.assign({}, newVal);
      // switch(newVal['no']) {
        //   case '1':
      //     Object.assign(engYearreportRef, testData[0]);
      //     break;
      //   case '2':
      //     Object.assign(engYearreportRef, testData[1]);
      // }
    },{immediate: true, deep: true});

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

    const nextTab = () => {
      switch (tabIndex.value) {
        case 0:
          tabDisabled[1] = false;
          tabIndex.value++;
          break;
        case 1:
          eng0202BasicInfo.value.checkValidity().then((isValid: boolean) => {
            if (isValid) {
              tabDisabled[2] = false;
              tabIndex.value++;
            } else {
              $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行下一頁。');
            }
          });
          break;
        case 2:
          tabDisabled[3] = false;
          tabIndex.value++;
          break;
        case 3:
          tabDisabled[4] = false;
          tabIndex.value++;
          break;
        case 4:
          tabDisabled[5] = false;
          tabIndex.value++;
          break;
        case 5:
          tabIndex.value++;
      }
    };

    // let formDefault = {
      //   engYearreportId: '',
    //   engYearreportYyyy: '',
    //   yearreportType: '',
    //   compId: '',
    //   compIdno: '',
    //   licenseNo: '',
    //   establishDate: '',
    //   compName: '',
    //   addrZip: '',
    //   addrCity: '',
    //   addrTown: '',
    //   addrOther: '',
    //   masterName: '',
    //   capital: '',
    //   saleAreaList: '',
    //   execType: '',
    //   engrIdno: '',
    //   engrName: '',
    //   engrSubjectList: '',
    //   staffNumForeign: '',
    //   incomeTotal: 0,
    //   incomeOperating: '',
    //   incomeNonOperating: '',
    //   incomeTechService: '',
    //   incomeTechServiceDomestic: 0,
    //   incomeTechServiceForeign: 0,
    //   isIncomeTechServiceMatch: '',
    //   incomeNonTechService: 0,
    //   feeOperating: '',
    //   feeNonOperating: '',
    //   feeDepreciation: '',
    //   feeIndirectTax: '',
    //   feeStaff: '',
    //   feeRd: '',
    //   feeTrain: '',
    //   countFinancing: '',
    //   feeFinancing: '',
    //   remark: '',
    //   originalIncomeTotal: 0,
    //   originalIncomeOperating: 0,
    //   originalIncomeNonOperating: 0,
    //   originalIncomeNonTechService: 0,
    //   revenueGrowthRatio: 0,
    //   revenueLastYear: 0,
    //   staffNumTotal: 0,
    //   staffNumAdministration: '',
    //   staffNumEngineering: '',
    //   staffNumEngr: '',
    //   staffOutputValue: 0,
    //   netProfitRatio: 0,
    //   netProfitAfterTax: '',
    //   netIncome: '',
    //   netValueTurnoverRatio: 0,
    //   income: '',
    //   netValue: '',
    //   flowRatio: 0,
    //   flowAssets: '',
    //   flowLiabilities: '',
    //   preparedByName: '',
    //   preparedByTel: '',
    //   notFillDesc: '',
    //   status: '',
    //   reviewStaff: '',
    //   reviewTime: '',
    //   reviewDesc: '',
    //   createUser: '',
    //   createTime: '',
    //   updateUser: '',
    //   updateTime: '',
    //   transferFrom: '',
    //   transferTime: '',

    //   no:'',
    //   fillStatus: '',
    //   engReviewLog: [],
    //   engYearreportEngr: [],
    // };
    // const formRules = {
      //   engYearreportId: {},
    //   engYearreportYyyy: {},
    //   yearreportType: {},
    //   compId: {},
    //   compIdno: {},
    //   licenseNo: {},
    //   establishDate: { notNull: required },
    //   compName: {},
    //   addrZip: {},
    //   addrCity: {},
    //   addrTown: {},
    //   addrOther: {},
    //   masterName: {},
    //   capital: { notNull: required },
    //   saleAreaList: {},
    //   execType: {},
    //   engrIdno: {},
    //   engrName: {},
    //   engrSubjectList: {},
    //   staffNumForeign: {},
    //   incomeTotal: {},
    //   incomeOperating: {},
    //   incomeNonOperating: {},
    //   incomeTechService: {},
    //   incomeTechServiceDomestic: {},
    //   incomeTechServiceForeign: {},
    //   isIncomeTechServiceMatch: {},
    //   incomeNonTechService: {},
    //   feeOperating: {},
    //   feeNonOperating: {},
    //   feeDepreciation: {},
    //   feeIndirectTax: {},
    //   feeStaff: {},
    //   feeRd: {},
    //   feeTrain: {},
    //   countFinancing: {},
    //   feeFinancing: {},
    //   remark: {},
    //   originalIncomeTotal: {},
    //   originalIncomeOperating: {},
    //   originalIncomeNonOperating: {},
    //   originalIncomeNonTechService: {},
    //   revenueGrowthRatio: {},
    //   revenueLastYear: {},
    //   staffNumTotal: {},
    //   staffNumAdministration: {},
    //   staffNumEngineering: {},
    //   staffNumEngr: {},
    //   staffOutputValue: {},
    //   netProfitRatio: {},
    //   netProfitAfterTax: {},
    //   netIncome: {},
    //   netValueTurnoverRatio: {},
    //   income: {},
    //   netValue: {},
    //   flowRatio: {},
    //   flowAssets: {},
    //   flowLiabilities: {},
    //   preparedByName: {},
    //   preparedByTel: {},
    //   notFillDesc: {},
    //   status: {},
    //   reviewStaff: {},
    //   reviewTime: {},
    //   reviewDesc: {},
    //   createUser: {},
    //   createTime: {},
    //   updateUser: {},
    //   updateTime: {},
    //   transferFrom: {},
    //   transferTime: {},

    //   no:{},
    //   fillStatus: {},
    //   engReviewLog: {},
    //   engYearreportEngr: {},
    // };

    const form = reactive({engYearreportId:''});

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

    const submitForm = (isCheck: boolean = false) => {
      if(isCheck) {
        Promise.all([
          eng0202BasicInfo.value.checkValidity(),
          2,
          3,
          4,
          eng0202Questionnaire.value.checkValidity()
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
      }else {
        $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
          if (isOK) {
            putForm();
          }
        });
      }
    };

    const putForm = () => {
      // let url: string;
      // if (formStatusRef.value === FormStatusEnum.MODIFY) {
      //   url = `/cms-comps/${form.engEngrCertiicateId}`;
      // }
      // dataPromise.value = axios
      //   .put(url, form)
      //   .then(({ data }) => {
      //     changeFormStatus(FormStatusEnum.INFO);
      //     createDefaultValue(data);
      //     isReload.value = true;
      //     notificationService.success(`${form.engEngrCertiicateId}-${form.chName}:修改成功`);
      //   })
      //   .finally(() => (dataPromise.value = null))
      //   .catch(notificationErrorHandler(notificationService));
      // Object.assign(form, eng0202BasicInfo.value.putForm());
      // form['engYearreportEngr'] = Object.assign([], eng0202EngrRoster.value.putForm());

      Object.assign(engYearreportRef.value['engYearreportAuth'], eng0202AuthorizationService.value.putForm());
      Object.assign(engYearreportRef.value, eng0202BasicInfo.value.putForm());
      // engYearreportEngr陣列長度不會改變, 只能變更單筆資料的備註, 所以可以用Object.assign覆蓋原陣列。
      Object.assign(engYearreportRef.value['engYearreportEngr'], eng0202EngrRoster.value.putForm());
      Object.assign(engYearreportRef.value, eng0202Questionnaire.value.putForm());
      // engYearreportCase陣列長度可能會改變, 所以不能直接用Object.assign, 例如長度3 -> 2, 只會覆蓋原陣列前兩筆。
      // 先指定為空陣列
      engYearreportRef.value['engYearreportCase'] = [];
      Object.assign(engYearreportRef.value['engYearreportCase'], eng0202ServiceCase.value.putForm());
      // 用下面的方法會秀逗，好像會觸發子元件多次watch，像是BasicInfo就會變回更改前
      // engYearreportRef.value['engYearreportCase'].splice(
      //   0,
      //   engYearreportRef.value['engYearreportCase'].length,
      //   ...eng0202ServiceCase.value.putForm()
      // );
    };

    return {
      engYearreportRef,
      dataPromise,
      tabIndex,
      activeTab,
      changeTabIndex,
      nextTab,
      tabDisabled,
      engReviewLog,
      eng0202BasicInfo,
      eng0202EngrRoster,
      eng0202ChangeSituation,
      eng0202ServiceCase,
      eng0202Questionnaire,
      eng0202AuthorizationService,
      toQueryView,
      reset,
      submitForm,
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

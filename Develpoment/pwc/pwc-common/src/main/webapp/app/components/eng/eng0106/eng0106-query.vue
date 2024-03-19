<template>
  <b-container>
    <b-card no-body v-if="roleRef === RoleEnum.ADMIN">
      <b-card-header class="py-1">
        <b-row class="align-items-center">
          <b-col cols="11">
            <h5 class="m-0">
              <font-awesome-icon icon="search"></font-awesome-icon>
              查詢條件
            </h5>
          </b-col>
          <b-col cols="1" class="p-0">
            <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
              <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
              <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
            </b-button>
          </b-col>
        </b-row>
      </b-card-header>
      <b-card-body class="col-11">
        <b-collapse v-model="stepVisible.step1">
          <b-form-row>
            <!-- 年度 -->
            <i-form-group-check :label="$t('label.engYearreportYyyy') + '：'" :item="$v.engYearreportYyyy">
              <i-date-picker v-model="$v.engYearreportYyyy.$model" type="year" placeholder="yyy"></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 技師姓名 -->
            <i-form-group-check :label="$t('label.engrName') + '：'" :item="$v.engrName">
              <b-form-input v-model="$v.engrName.$model" trim></b-form-input>
            </i-form-group-check>
            <!-- 身分證字號 -->
            <i-form-group-check :label="$t('label.idno') + '：'" :item="$v.engrIdno">
              <b-form-input v-model="$v.engrIdno.$model" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 機構名稱 -->
            <i-form-group-check :label="$t('label.compName') + '：'" :item="$v.compName">
              <b-form-input v-model.trim="$v.compName.$model"></b-form-input>
            </i-form-group-check>
            <!-- 統一編號 -->
            <i-form-group-check :label="$t('label.compIdno') + '：'" :item="$v.compIdno">
              <b-form-input v-model="$v.compIdno.$model" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 填寫狀況 -->
            <i-form-group-check :label="$t('label.fillStatus') + '：'" :item="$v.notFillDesc">
              <b-form-select v-model="$v.notFillDesc.$model" :options="formOptions.fillStatus"> </b-form-select>
            </i-form-group-check>
            <!-- 狀態 -->
            <i-form-group-check :label="$t('label.status') + '：'" :item="$v.status">
              <b-form-select v-model="$v.status.$model" :options="formOptions.status"> </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <b-col offset="8">
              <i-button type="search" @click="handleQuery"></i-button>
              <i-button type="x-circle" @click="reset"></i-button>
            </b-col>
          </b-form-row>
        </b-collapse>
      </b-card-body>
    </b-card>

    <b-card no-body>
      <b-card-header class="py-1">
        <b-row class="align-items-center">
          <b-col cols="11">
            <h5 class="m-0">
              <b-icon icon="gear-fill"></b-icon>
              匯入匯出及設定
            </h5>
          </b-col>
          <b-col cols="1" class="p-0">
            <b-button class="float-right" @click="stepVisible.step2 = !stepVisible.step2">
              <font-awesome-icon v-if="stepVisible.step2" icon="chevron-up"></font-awesome-icon>
              <font-awesome-icon v-if="!stepVisible.step2" icon="chevron-down"></font-awesome-icon>
            </b-button>
          </b-col>
        </b-row>
      </b-card-header>
      <b-card-body class="col-11">
        <b-collapse v-model="stepVisible.step2">
          <b-form-row>
            <!-- 匯入技師綜合所得 -->
            <i-form-group-check label="匯入技師綜合所得：" :item="$v.compName">
              <b-form-file
                accept="text/plain"
                browse-text="選擇檔案"
                placeholder="未選擇任何檔案"
                drop-placeholder="拖曳檔案至此"
                v-model="$v.compName.$model"
              ></b-form-file>
            </i-form-group-check>
            <!-- 匯入按鈕-->
            <b-col offset="2">
              <i-button type="file-earmark-arrow-up"></i-button>
              <!-- <b-button variant="info">匯出授權同意技師名單(含授權書)</b-button> -->
            </b-col>
          </b-form-row>

          <b-form-row>
            <!-- 匯入技師營業所得 -->
            <i-form-group-check label="匯入技師營業所得：" :item="$v.compName">
              <b-form-file
                accept="text/plain"
                browse-text="選擇檔案"
                placeholder="未選擇任何檔案"
                drop-placeholder="拖曳檔案至此"
                v-model="$v.compName.$model"
              ></b-form-file>
            </i-form-group-check>
            <!-- 匯入按鈕-->
            <b-col offset="2">
              <i-button type="file-earmark-arrow-up"></i-button>
            </b-col>
          </b-form-row>

          <b-form-row class="mb-4">
            <b-col cols="8">
              授權服務書期限設定為每年的
              <b-form-select
                class="col-1"
                v-model="$v.monthOfDeadline.$model"
                :options="formOptions.month"
                @change="changeDayOption"
              ></b-form-select>
              月
              <b-form-select class="col-1" v-model="$v.dayOfDeadline.$model" :options="formOptions.day"></b-form-select>
              號
            </b-col>
            <b-col cols="4">
              <i-button type="save"></i-button>
            </b-col>
          </b-form-row>

          <b-form-row>
            <b-col offset="8">
              <b-button variant="info">匯出授權同意技師名單(含授權書)</b-button>
            </b-col>
          </b-form-row>
        </b-collapse>
      </b-card-body>
    </b-card>

    <div class="mt-2">
      <i-button type="file-earmark-plus" @click="toEditView(null)"></i-button>
      <!--切換角色按鈕-->
      <b-button size="md" variant="warning" @click="switchRoles">
        <font-awesome-icon :icon="['fas', 'people-arrows']" />
        {{ roleRef }}
      </b-button>
      <!--切換角色按鈕-->
    </div>

    <i-table
      v-show="stepVisible.step3"
      class="mt-2"
      ref="iTableComponent"
      items-undefined-behavior="loading"
      is-server-side-paging
      :items="table.data"
      :fields="table.fields_backend"
      :totalItems="table.totalItems"
      @changePagination="handlePaginationChanged"
    >
      <template #cell(action)="row">
        <i-button class="mb-1" size="sm" type="binoculars" @click="toEditView(row.item)"></i-button>
      </template>
    </i-table>
  </b-container>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import NotificationService from '@/shared/notification/notification-service';
import { useValidation, validateState } from '@/shared/form';
import { onActivated, onMounted, reactive, Ref, ref, toRef } from '@vue/composition-api';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { parseRocDate, formatDate } from '@/shared/date/minguo-calendar-utils';
import { navigateByNameAndParams } from '@/router/router';
// import axios from 'axios';
// import { notificationErrorHandler } from '@/shared/http/http-response-helper';
// import { egrCertificate } from '@/shared/model/cms-comp.model';
// import { Pagination } from '@/shared/model/pagination.model';
import { RoleEnum } from '@/shared/enum';
import { Pagination } from '@/shared/model/pagination.model';

export default {
  name: 'Eng0202Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
    form: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    // 切換角色
    const switchRoles = () => {
      if (roleRef.value === RoleEnum.ADMIN) roleRef.value = RoleEnum.USER;
      else roleRef.value = RoleEnum.ADMIN;
    };

    const isReloadProp = toRef(props, 'isReload');

    onMounted(() => {
      changeDayOption();
    });

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    const formOptions = reactive({
      status: [
        { value: undefined, text: '請選擇' },
        { value: '1', text: '申請' },
        { value: '3', text: '初審中' },
        { value: '4', text: '複審中' },
        { value: '5', text: '已結案' },
        { value: '6', text: '作廢' },
      ],
      fillStatus: [
        { value: undefined, text: '請選擇' },
        { value: '1', text: '未填' },
        { value: '2', text: '有填未完整' },
        { value: '3', text: '完整' },
        { value: '4', text: '未填或有填未完整' },
      ],
      month: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
      day: [],
    });

    function changeDayOption() {
      formOptions.day.splice(0, formOptions.day.length);
      if (form.monthOfDeadline === 2) {
        for (let i = 1; i <= 28; i++) {
          formOptions.day.push(i);
        }
      } else if ([1, 3, 5, 7, 8, 10, 12].includes(form.monthOfDeadline)) {
        for (let i = 1; i <= 31; i++) {
          formOptions.day.push(i);
        }
      } else {
        for (let i = 1; i <= 30; i++) {
          formOptions.day.push(i);
        }
      }
    }

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      engYearreportYyyy: undefined,
      engrName: undefined,
      engrIdno: undefined,
      compName: undefined,
      compIdno: undefined,
      notFillDesc: undefined,
      status: undefined,
      monthOfDeadline: 7,
      dayOfDeadline: 1,
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      engYearreportYyyy: {},
      engrName: {},
      engrIdno: {},
      compName: {},
      compIdno: {},
      notFillDesc: {},
      status: {},
      monthOfDeadline: {},
      dayOfDeadline: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      step2: true,
      step3: false,
    });

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
      fields_backend: [
        {
          // 年度
          key: 'engYearreportYyyy',
          label: i18n.t('label.engYearreportYyyy'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          // 機構名稱
          key: 'compName',
          label: i18n.t('label.compName'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 技師姓名
          key: 'engrName',
          label: i18n.t('label.engrName'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 身分證字號
          key: 'engrIdno',
          label: i18n.t('label.idno'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 報告日期
          key: 'createTime',
          label: i18n.t('label.reportDate'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
          formatter: (value: Date) => formatDate(value, '/'),
        },
        {
          // 填寫狀況
          key: 'fillStatus',
          label: i18n.t('label.fillStatus'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => optionsFormatter('fillStatus', value),
        },
        // {
        //   // 狀態
        //   key: 'status',
        //   label: i18n.t('label.status'),
        //   sortable: true,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        //   formatter: (value: string) => optionsFormatter('status', value),
        // },
        {
          // 動作
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
          // thStyle: { width: '10%' },
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          stepVisible.step3 = true;
          // axios
          //   // .post('/cms-comps/criteria', form)           //前端分頁
          //   .post('/cms-comps/criteria-jpa', form) //後端分頁
          //   .then(({ data }) => {
          //     // table.data = data.slice(0, data.length);   //前端分頁
          //     // table.totalItems = data.length;            //前端分頁
          //     table.data = data.content.slice(0, data.content.length); //後端分頁
          //     table.totalItems = data.totalElements; //後端分頁
          //   })
          //   .catch(notificationErrorHandler(notificationService));
          table.data = [
            {
              no: '1',
              engYearreportId: '123456',
              engYearreportYyyy: '108',
              engrName: '蔡再發',
              engrIdno: 'R100637002',
              compName: '一達電機技師事務所',
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
                  reviewDesc:
                    '本服務係為簡政便民提升系統填報效率，委託方如送出同意授權仍得於六月三十日（含）前撤回授權，並自行負責至系統填報相關欄位',
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
              changeSituation: [
                {
                  no: '1',
                  name: '巫哲緯',
                  memberIdno: 'B120518158',
                  memberTitle: '02',
                  status: '1',
                  inDate: parseRocDate('108/11/15', '/'),
                },
                {
                  no: '2',
                  name: '林忠機',
                  memberIdno: 'Y120051218	',
                  memberTitle: '03',
                  status: '0',
                  inDate: parseRocDate('107/04/23', '/'),
                },
                {
                  no: '3',
                  name: '金玉瑩',
                  memberIdno: 'F220879607',
                  memberTitle: '04',
                  status: '1',
                  inDate: parseRocDate('109/10/03', '/'),
                },
              ],
              engYearreportCase: [
                {
                  engYearreportCaseId: '1',
                  caseName: '台九線蘇花公路南澳和平段新建工程委託監造服務工作',
                  clientName: '交通部公路總局蘇花公路改善工程處',
                  price: 845500000,
                  yearCompletePrice: 80068850,
                  engYearreportCaseEngr: [
                    {
                      engYearreportCaseEngrId: '1',
                      subjectList: '1,3',
                      subject: '3',
                      name: '高健發',
                      idno: 'E100238072',
                      dateStart: parseRocDate('105/06/12', '/'),
                      dateEnd: parseRocDate('108/06/12', '/'),
                      mainTask:
                        '個案分頁工程之設計簽證內容包括預算書、設計圖、施工規範及機關認為必要之項目等；施工簽證內容包括施工廠商品質計畫與施工計畫審查、施工圖說審查、材料與設備抽驗、施工查驗與查核、監造抽查(驗)停留點(含安全衛生事項)、施工廠商估驗計價之查估、設備功能運轉測試之由驗及機關認為必要之項目等。',
                    },
                    {
                      engYearreportCaseEngrId: '2',
                      subjectList: '2,3,5',
                      subject: '5',
                      name: '許力方',
                      idno: 'S121174163',
                      dateStart: parseRocDate('105/06/12', '/'),
                      dateEnd: parseRocDate('108/06/12', '/'),
                      mainTask: '個案分頁工程之設計簽證內容包括預算書。',
                    },
                  ],
                },
                {
                  engYearreportCaseId: '2',
                  caseName: '高雄鐵路地下化延伸鳳山計畫工程設計暨配合工作技術服務',
                  // clientName: '交通部鐵道局',
                  price: 250000000,
                  yearCompletePrice: 164500000,
                },
                {
                  engYearreportCaseId: '3',
                  caseName: '台九線蘇花公路南澳和平段新建工程委託監造服務工作',
                  clientName: '交通部公路總局蘇花公路改善工程處',
                  price: 845500000,
                  yearCompletePrice: 80068850,
                  isForeignCase: 1,
                  engYearreportCaseEngr: [
                    {
                      engYearreportCaseEngrId: '1',
                      subjectList: '1,3',
                      subject: '3',
                      name: '高健發',
                      idno: 'E100238072',
                      dateStart: parseRocDate('105/06/12', '/'),
                      dateEnd: parseRocDate('108/06/12', '/'),
                      mainTask:
                        '個案分頁工程之設計簽證內容包括預算書、設計圖、施工規範及機關認為必要之項目等；施工簽證內容包括施工廠商品質計畫與施工計畫審查、施工圖說審查、材料與設備抽驗、施工查驗與查核、監造抽查(驗)停留點(含安全衛生事項)、施工廠商估驗計價之查估、設備功能運轉測試之由驗及機關認為必要之項目等。',
                    },
                    {
                      engYearreportCaseEngrId: '2',
                      subjectList: '2,3,5',
                      subject: '5',
                      name: '許力方',
                      idno: 'S121174163',
                      dateStart: parseRocDate('105/06/12', '/'),
                      dateEnd: parseRocDate('108/06/12', '/'),
                      mainTask: '個案分頁工程之設計簽證內容包括預算書。',
                    },
                  ],
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
              engYearreportAuth: [
                {
                  engYearreportAuthId: 1,
                  isAuthorize: 1,
                  createTime: parseRocDate('108/06/12', '/'),
                },
                {
                  engYearreportAuthId: 2,
                  isAuthorize: 0,
                  createTime: parseRocDate('109/06/12', '/'),
                },
              ],
            },
            {
              no: '2',
              engYearreportId: '123456',
              engYearreportYyyy: '105',
              compName: '',
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
                  reviewDesc:
                    '本服務係為簡政便民提升系統填報效率，委託方如送出同意授權仍得於六月三十日（含）前撤回授權，並自行負責至系統填報相關欄位',
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
                  engrName: 'aaaa',
                  engrIdno: 'P220723812',
                  engrLicenseNo: '技執字第002151號',
                  subjectList: '3,5',
                  remark: 'aaaaaaaaa',
                },
                {
                  no: '2',
                  engrName: 'bbbb',
                  engrIdno: 'A129371472',
                  engrLicenseNo: '技執字第008174號',
                  subjectList: '1',
                  remark: 'bbbbbbbbbb',
                },
              ],
              engYearreportCase: [
                {
                  engYearreportCaseId: '1',
                  caseName: '台九線蘇花公路南澳和平段新建工程委託監造服務工作',
                  clientName: '交通部公路總局蘇花公路改善工程處',
                  price: 845500000,
                  yearCompletePrice: 80068850,
                  engYearreportCaseEngr: [
                    {
                      engYearreportCaseEngrId: '1',
                      subjectList: '1,3',
                      subject: '3',
                      name: '高健發',
                      idno: 'E100238072',
                      dateStart: parseRocDate('105/06/12', '/'),
                      dateEnd: parseRocDate('108/06/12', '/'),
                      mainTask:
                        '個案分頁工程之設計簽證內容包括預算書、設計圖、施工規範及機關認為必要之項目等；施工簽證內容包括施工廠商品質計畫與施工計畫審查、施工圖說審查、材料與設備抽驗、施工查驗與查核、監造抽查(驗)停留點(含安全衛生事項)、施工廠商估驗計價之查估、設備功能運轉測試之由驗及機關認為必要之項目等。',
                    },
                    {
                      engYearreportCaseEngrId: '2',
                      subjectList: '2,3,5',
                      subject: '5',
                      name: '許力方',
                      idno: 'S121174163',
                      dateStart: parseRocDate('105/06/12', '/'),
                      dateEnd: parseRocDate('108/06/12', '/'),
                      mainTask: '個案分頁工程之設計簽證內容包括預算書。',
                    },
                  ],
                },
              ],
            },
            {
              no: '2',
              engYearreportId: '123456',
              engYearreportYyyy: '105',
              compName: '',
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
                  reviewDesc:
                    '本服務係為簡政便民提升系統填報效率，委託方如送出同意授權仍得於六月三十日（含）前撤回授權，並自行負責至系統填報相關欄位',
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
                  engrName: 'aaaa',
                  engrIdno: 'P220723812',
                  engrLicenseNo: '技執字第002151號',
                  subjectList: '3,5',
                  remark: '@@',
                },
              ],
              engYearreportCase: [
                {
                  engYearreportCaseId: '1',
                  caseName: '台九線蘇花公路南澳和平段新建工程委託監造服務工作',
                  clientName: '交通部公路總局蘇花公路改善工程處',
                  price: 845500000,
                  yearCompletePrice: 80068850,
                  engYearreportCaseEngr: [
                    {
                      engYearreportCaseEngrId: '1',
                      subjectList: '1,3',
                      subject: '3',
                      name: '高健發',
                      idno: 'E100238072',
                      dateStart: parseRocDate('105/06/12', '/'),
                      dateEnd: parseRocDate('108/06/12', '/'),
                      mainTask:
                        '個案分頁工程之設計簽證內容包括預算書、設計圖、施工規範及機關認為必要之項目等；施工簽證內容包括施工廠商品質計畫與施工計畫審查、施工圖說審查、材料與設備抽驗、施工查驗與查核、監造抽查(驗)停留點(含安全衛生事項)、施工廠商估驗計價之查估、設備功能運轉測試之由驗及機關認為必要之項目等。',
                    },
                    {
                      engYearreportCaseEngrId: '2',
                      subjectList: '2,3,5',
                      subject: '5',
                      name: '許力方',
                      idno: 'S121174163',
                      dateStart: parseRocDate('105/06/12', '/'),
                      dateEnd: parseRocDate('108/06/12', '/'),
                      mainTask: '個案分頁工程之設計簽證內容包括預算書。',
                    },
                  ],
                },
              ],
            },
          ];
          table.totalItems = 3;
        }
      });
    };

    const toEditView = (engYearreport: any) => {
      navigateByNameAndParams('Eng0106EditInfo', { engYearreport: engYearreport, role: roleRef.value });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const optionsFormatter = (option: string, value: string) => {
      let itemFound = formOptions[option].find((item: { value: string }) => item.value === value);
      if (itemFound) {
        return itemFound.text;
      } else {
        return '';
      }
    };

    return {
      validateState,
      formOptions,
      $v,
      stepVisible,
      table,
      reset,
      handleQuery,
      toEditView,
      roleRef,
      RoleEnum,
      ref,
      switchRoles,
      formatDate,
      optionsFormatter,
      changeDayOption,
      handlePaginationChanged,
    };
  },
};
</script>
<style></style>

<template>
  <div>
    <section class="container">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      <div class="card-body col-12">
        <b-row>
          <b-col cols="1">
            <b-button @click="toLook" variant="link" style="color: blue">填報說明</b-button>
          </b-col>
          <b-col class="col-10">
            &nbsp;<span style="color: red">工程會98.7.28</span>
            <u style="color: blue">09800335210</u>
            <span>函，為掌握各項公共工程勞工需求情形，並即時傳遞各項缺工訊息供政府機關辦理就業媒合作業...，請查照。</span>
          </b-col>
        </b-row>
        <b-row>
          <b-col cols="1"> </b-col>
          <b-col class="col-10">
            <span style="color: red">工程會98.9.28</span>
            <u style="color: blue">09800335210</u>
            <span>函，本會「公共工程標案管理系統」業於98年8月1日起新增人力需求與缺工通報功能...，請查照。</span>
          </b-col>
        </b-row>

        <b-row>
          <b-col cols="1"> </b-col>
          <b-col class="col-10">
            <span style="color: red">工程會107.4.24</span>
            <u style="color: blue">09800335210</u>
            <span>函，各機關辦理公共工程如有承覽廠商人力不足情形，請確實...，請查照。</span>
          </b-col>
        </b-row>

        <div class="card">
          <div class="card-header">
            <h5 class="m-0">人力需求新增</h5>
          </div>
          <div class="card-body">
            <b-form-row class="col-sm-12" label-cols-md="2" content-cols-md="10">
              <div class="d-inline">
                <i-date-picker
                  v-model="$v.yearAndMonthDate.$model"
                  type="month"
                  placeholder="yy/mm"
                  :disabled="formStatusRef === FormStatusEnum.MODIFY"
                ></i-date-picker>
              </div>
              <div class="col-8">
                <p class="d-inline">年/月</p>
              </div>
            </b-form-row>
            <div>
              <span>請承攬廠商依照<span style="color: red">施工計劃書</span><span>中人力需求分配量，提供下表每月人力需求資料</span></span>
            </div>
            <div>
              <span style="color: red">需勞動部媒合人數</span
              ><b>填報不是0(有媒合需求)，系統將立刻自動發送Email轉知當地就業服務站協助媒合</b>
            </div>

            <b-table-simple hover small bordered>
              <b-tbody>
                <b-tr v-for="(job, index) of jobOptions" :key="job">
                  <b-th width="10%"
                    ><span style="color: red">{{ job }}</span> 需求</b-th
                  >
                  <b-td>
                    <span style="color: red">當月整體施工人力</span>
                    需求量
                    <b-form-input
                      class="col-1 d-inline-block"
                      v-model="$v[index === 0 ? 'person02' : 'worker' + (index < 10 ? '0' + index : index)].$model"
                      :state="validateState($v[index === 0 ? 'person02' : 'worker' + (index < 10 ? '0' + index : index)])"
                    ></b-form-input>
                    人月，短少數
                    <b-form-input
                      class="col-1 d-inline-block"
                      v-model="$v[index === 0 ? 'personA02' : 'workerA' + (index < 10 ? '0' + index : index)].$model"
                      :state="validateState($v[index === 0 ? 'personA02' : 'workerA' + (index < 10 ? '0' + index : index)])"
                    ></b-form-input>
                    人月，需勞動部媒合人數
                    <b-form-input
                      class="col-1 d-inline-block"
                      v-model="$v[index === 0 ? 'personB02' : 'workerB' + (index < 10 ? '0' + index : index)].$model"
                      :state="validateState($v[index === 0 ? 'personB02' : 'workerB' + (index < 10 ? '0' + index : index)])"
                    ></b-form-input>
                    人月，薪資待遇(元)
                    <b-form-input
                      class="col-1 d-inline-block"
                      v-model="$v[index === 0 ? 'person02Salary' : 'worker' + (index < 10 ? '0' + index : index) + 'Salary'].$model"
                      :state="validateState($v[index === 0 ? 'person02Salary' : 'worker' + (index < 10 ? '0' + index : index) + 'Salary'])"
                    ></b-form-input>
                  </b-td>
                </b-tr>
              </b-tbody>
            </b-table-simple>
            <div>
              註:1.<span style="color: red">需勞動部媒合</span>人員填報數後，系統將<span style="color: blue">自動發送Email</span
              >轉知當地就業服務站協助媒合
            </div>
            <div>
              &nbsp;&nbsp;&nbsp;&nbsp;2.工程施工中<span style="color: red">人力需求</span>明顯異常時，系統將<span style="color: blue"
                >自動發送Email</span
              >通知主管部門或縣市政府<span style="color: blue">施工查核小組</span>協助了解。
            </div>
            <div class="text-center">
              <i-button type="check-circle" @click="toSubmit"></i-button>
              <i-button type="x-circle" @click="reset"></i-button>
              <i-button size="md" type="arrow-counterclockwise" @click="toQueryView"></i-button>
            </div>
          </div>
        </div>
      </div>
    </section>

    <b-modal
      title="人力需求資料系統填報常見問題Q&A"
      id="graph-add-modal"
      size="lg"
      header-bg-variant="secondary"
      header-text-variant="light"
      centered
    >
      <b-form-row class="py-3">
        <div>一、<span style="color: red">為什麼要收集工程力需求資料?</span></div>
        <div>
          答:因98年全球經濟風暴，失業率提升，政府為振興經濟，投入四年共五千億的特別預算來擴大公共建設投資，帶動經濟景氣回升，因此，在相關公共工程陸續發包施工後，勢必須要勞工投入，以推動工程。而勞工市場的供需希望能快速媒合，請工程主辦機關依照施工計畫書中人力需求分配量，提供每月人力需求資料，以瞭解全國各地區之勞工人力需求量，並快速將需求資訊傳遞至勞委會所屬各地就業服務中心，俾利讓待業勞工上工，並順利推動工程。
        </div>
      </b-form-row>

      <b-form-row class="py-3">
        <div>二、<span style="color: red">為什麼要在工程標案管理系統中填報人力需求及短少數資料？</span></div>
        <duv
          >答：當政府機關要蒐集人力需求量，傳統做法為發文給中央各部會、直轄市及各縣市政府調查，公文將函轉各層級執行機關，不管有沒有辦理工程案件均將收到通知，提報相關資料給上一層級之機關彙整，除了浪費紙張外，各機關彙整亦耗費大量人力，且資料正確性也難以掌握。為免造成各機關困擾，於大家共用之平臺直接加入蒐集功能，每一執行中之標案預估下一個月份人力需求、短少量及需勞委會媒合人數，各層級所需彙整資料即刻完成，並立即將訊息自動傳送自勞委會所屬各地就業服務中心進行媒合做業，可免除相關人員困擾，並提升時效。
        </duv>
        <!-- <i-form-group-check label="檔案類別：" :item="$v.month" class="col-12" label-cols="2" content-cols="10">
          <b-form-radio-group v-model="$v.month.$model" :state="validateState($v.month)" />
        </i-form-group-check> -->
      </b-form-row>

      <b-form-row class="py-3">
        <div>三、<span style="color: red">人力「需求量」、「短少數」及「需勞委會媒合人數」的定義是什麼？</span></div>
        <div>答：人力「需求量」係指依施工計畫工程執行進度每月所需各類勞工之數量。</div>
      </b-form-row>

      <b-form-row class="py-3">
        <div>四、<span style="color: red">什麼時候要填報人力需求資料？</span></div>
        <div>
          答：工程標案管理系統中已開工施工中之工程類標案應填報人力需求量，為免一次全部填報造成各機關困擾，已自動控制自98年8月起發包預算5000萬以上工程於按月填報工程執行進度前將要求完成人力需求量填報，並逐月降低門檻，
          98年9月起發包預算1000萬以上， 98年月起發包預算100萬以上均需提報。
        </div>
      </b-form-row>

      <b-form-row class="py-3">
        <div>五、<span style="color: red">要如何填報人力需求資料？</span></div>
        <div>
          答：工程主辦機關依照承攬廠商所提施工計畫書中人力需求分配量，在填報最新月份工程執行進度時，需同時填報下一個月的人力需求量及短缺數，（例如；在6月10日填報截至5月底的工程執行進度時，則必需同時填報7月勞工人力需求及短缺資料），當有需勞委會媒合人數填報，工程主辦機關按下「確定」鈕後，系統將自動傳送短缺勞工之工種（模板工、鋼筋工…）及短缺人數、工地聯絡窗口電話等資訊至工程所在地的勞委會所屬就業服務中心，進行勞工媒合作業。
        </div>
      </b-form-row>

      <b-form-row class="py-3">
        <div>六、<span style="color: red">以前已進用之人力資料要填報嗎？</span></div>
        <div>答：勞工人力短缺的需求，主要係針對未來的短缺需求進行預為因應媒合，對於過去的資料不需填報。</div>
      </b-form-row>

      <!-- <template #modal-footer="{ cancel }">
        <div class="col-12 text-center">
          <b-button size="md" @click="submitForm">儲存</b-button>
          <b-button size="md" @click="cancel">取消</b-button>
          <b-button size="md" @click="reset">清除</b-button>
        </div>
      </template> -->
    </b-modal>
  </div>
</template>

<script lang="ts">
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { reactive, ref, toRef, watch, Ref, onMounted, unref, computed } from '@vue/composition-api';
import { required } from '@/shared/validators';
import { handleBack } from '@/router/router';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import BidPrjMonthService from '@/components/bid/bidService/bid-prj-month.service';
import { required, lineTel, mobileTel, email, maxLength, address, sameAs, minValue, nationalId, taxIdNumber } from '@/shared/validators';

export default {
  name: 'bidAad006Edit',
  props: {
    bidProjectKeyProp: {
      type: Object,
      required: false,
    },
    // 原為 ref(FormStatusEnum) 傳過來會變物件 { "value": "xxx" }，目的為這樣更改value值就不會被Vue警告(避免變異父元件資料)
    formStatus: {
      required: false,
      type: String,
    },
  },
  components: {
    bidProjectInfo,
  },

  setup(props, context) {
    const bringValueProp = toRef(props, 'bidProjectKeyProp');
    const formStatusRef = toRef(props, 'formStatus'); // toRef會保持對來源property的響應式連接，但改變父元件資料會被Vue警告，所以這邊改用ref複製一份新的資料
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();
    const admSysCodeService = new AdmSysCodeService();
    const bidPrjMonthService = new BidPrjMonthService();

    onMounted(() => {});
    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
    }

    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

    function handleProjectMonthDateToString(bidPrjMonth: IBidPrjMonth) {
      bidPrjMonth.syr = (bidPrjMonth.yearAndMonthDate.getFullYear() - 1911).toString();
      bidPrjMonth.yr = bidPrjMonth.syr;
      bidPrjMonth.month = (bidPrjMonth.yearAndMonthDate.getMonth() + 1).toString().padStart(2, '0');
    }

    function handleStringToProjectMonthDate(bidPrjMonth: IBidPrjMonth) {
      bidPrjMonth.yearAndMonthDate = new Date(parseInt(bidPrjMonth.syr) + 1911, parseInt(bidPrjMonth.month) - 1);
    }

    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      yearAndMonthDate: '',
      syr: '',
      month: '',
      person01: '',
      person02: '',
      worker01: '', //
      worker02: '', //
      worker03: '', //
      worker04: '', //
      worker05: '', //
      worker06: '', //
      worker07: '', //
      worker08: '', //
      worker09: '', //
      worker10: '', //
      worker11: '', //
      worker12: '', //
      personA01: '',
      personA02: '',
      workerA01: '', //
      workerA02: '', //
      workerA03: '', //
      workerA04: '', //
      workerA05: '', //
      workerA06: '', //
      workerA07: '', //
      workerA08: '', //
      workerA09: '', //
      workerA10: '', //
      workerA11: '', //
      workerA12: '', //
      personB01: '',
      personB02: '',
      workerB01: '', //
      workerB02: '', //
      workerB03: '', //
      workerB04: '', //
      workerB05: '', //
      workerB06: '', //
      workerB07: '', //
      workerB08: '', //
      workerB09: '', //
      workerB10: '', //
      workerB11: '', //
      workerB12: '', //
      person01Salary: '',
      person02Salary: '',
      worker01Salary: '', //
      worker02Salary: '', //
      worker03Salary: '', //
      worker04Salary: '', //
      worker05Salary: '', //
      worker06Salary: '', //
      worker07Salary: '', //
      worker08Salary: '', //
      worker09Salary: '', //
      worker10Salary: '', //
      worker11Salary: '', //
      worker12Salary: '', //
    };

    // 表單物件驗證規則
    const rules = {
      yearAndMonthDate: { required: required },
      syr: {},
      month: {},
      person01: {},
      person02: {
        minValue: minValue(
          computed(() => form.personA02),
          true
        ),
      },
      // person01: {},
      // person02: {},
      worker01: {
        minValue: minValue(
          computed(() => form.workerA01),
          true
        ),
      },
      worker02: {
        minValue: minValue(
          computed(() => form.workerA02),
          true
        ),
      },
      worker03: {
        minValue: minValue(
          computed(() => form.workerA03),
          true
        ),
      },
      worker04: {
        minValue: minValue(
          computed(() => form.workerA04),
          true
        ),
      },
      worker05: {
        minValue: minValue(
          computed(() => form.workerA05),
          true
        ),
      },
      worker06: {
        minValue: minValue(
          computed(() => form.workerA06),
          true
        ),
      },
      worker07: {
        minValue: minValue(
          computed(() => form.workerA07),
          true
        ),
      },
      worker08: {
        minValue: minValue(
          computed(() => form.workerA08),
          true
        ),
      },
      worker09: {
        minValue: minValue(
          computed(() => form.workerA09),
          true
        ),
      },
      worker10: {
        minValue: minValue(
          computed(() => form.workerA10),
          true
        ),
      },
      worker11: {
        minValue: minValue(
          computed(() => form.workerA11),
          true
        ),
      },
      worker12: {
        minValue: minValue(
          computed(() => form.workerA12),
          true
        ),
      },
      personA01: {},
      personA02: {
        minValue: minValue(
          computed(() => form.personB02),
          true
        ),
      },
      workerA01: {
        minValue: minValue(
          computed(() => form.workerB01),
          true
        ),
      },
      workerA02: {
        minValue: minValue(
          computed(() => form.workerB02),
          true
        ),
      },
      workerA03: {
        minValue: minValue(
          computed(() => form.workerB03),
          true
        ),
      },
      workerA04: {
        minValue: minValue(
          computed(() => form.workerB04),
          true
        ),
      },
      workerA05: {
        minValue: minValue(
          computed(() => form.workerB05),
          true
        ),
      },
      workerA06: {
        minValue: minValue(
          computed(() => form.workerB06),
          true
        ),
      },
      workerA07: {
        minValue: minValue(
          computed(() => form.workerB07),
          true
        ),
      },
      workerA08: {
        minValue: minValue(
          computed(() => form.workerB08),
          true
        ),
      },
      workerA09: {
        minValue: minValue(
          computed(() => form.workerB09),
          true
        ),
      },
      workerA10: {
        minValue: minValue(
          computed(() => form.workerB10),
          true
        ),
      },
      workerA11: {
        minValue: minValue(
          computed(() => form.workerB11),
          true
        ),
      },
      workerA12: {
        minValue: minValue(
          computed(() => form.workerB12),
          true
        ),
      },
      personB01: {},
      personB02: {},
      workerB01: {},
      workerB02: {},
      workerB03: {},
      workerB04: {},
      workerB05: {},
      workerB06: {},
      workerB07: {},
      workerB08: {},
      workerB09: {},
      workerB10: {},
      workerB11: {},
      workerB12: {},
      person01Salary: {},
      person02Salary: {},
      worker01Salary: {},
      worker02Salary: {},
      worker03Salary: {},
      worker04Salary: {},
      worker05Salary: {},
      worker06Salary: {},
      worker07Salary: {},
      worker08Salary: {},
      worker09Salary: {},
      worker10Salary: {},
      worker11Salary: {},
      worker12Salary: {},
    };

    const groups = [
      ['person02', 'personA02'],
      ['worker01', 'workerA01'],
      ['worker02', 'workerA02'],
      ['worker03', 'workerA03'],
      ['worker04', 'workerA04'],
      ['worker05', 'workerA05'],
      ['worker06', 'workerA06'],
      ['worker07', 'workerA07'],
      ['worker08', 'workerA08'],
      ['worker09', 'workerA09'],
      ['worker10', 'workerA10'],
      ['worker11', 'workerA11'],
      ['worker12', 'workerA12'],
      ['personA02', 'personB02'],
      ['workerA01', 'workerB01'],
      ['workerA02', 'workerB02'],
      ['workerA03', 'workerB03'],
      ['workerA04', 'workerB04'],
      ['workerA05', 'workerB05'],
      ['workerA06', 'workerB06'],
      ['workerA07', 'workerB07'],
      ['workerA08', 'workerB08'],
      ['workerA09', 'workerB09'],
      ['workerA10', 'workerB10'],
      ['workerA11', 'workerB11'],
      ['workerA12', 'workerB12'],
    ];

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault, groups);

    const notificationService: NotificationService = useNotification();

    // 下拉選單選項
    const queryOptions = reactive({
      months: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
    });

    //下拉試選單響應試
    const ccutnitoptions = ref([]);
    const ccutnitoptionss = ref([]);

    // 執行結果
    // const queryStatus = ref(false);
    const dataPromise = ref(null);

    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const stepVisible = reactive({
      queryRow: true,
    });

    const toSubmit = () => {
      const value = new Date();
      const offset = value.getTimezoneOffset();
      form.createDate = new Date();
      form.createDate = new Date(form.createDate.getTime() - offset * 60 * 1000);
      form.createUser = userIdentity;
      // form.syr = 101;
      // form.month = 12;

      let criteria = {
        wkut: bringValueProp.value.wkut,
        prjno: bringValueProp.value.prjno,
      };

      Object.assign(form, criteria);
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          handleProjectMonthDateToString(form);
          bidPrjMonthService.saveProjectMonth(form);
          console.log('formform:');
          dataPromise.value = axios
            .post('/bid-prj-wksorcs', form)
            .then(({ data }) => {
              console.log('datadatadata:', data);
              createDefaultValue(data);
              isReload.value = true;
              // if (formStatusRef.value === formStatusEnum.CREATE) {
              $bvModal.msgBoxOk('新增成功!');
              // } else {
              // $bvModal.msgBoxOk('修改成功!');
              // }
              // navigateByNameAndParams('bidAad003Detail', { form, formStatus: FormStatusEnum.MODIFY, isNotKeepAlive: false });
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //下拉式選單
    // const getAllSysCodes = () => {
    //   admSysCodeService.setFormOptions(queryOptions, 'BID');
    // };

    const toQueryView = () => {
      let bidProjectKey = {
        wkut: bringValueProp.value.wkut,
        prjno: bringValueProp.value.prjno,
      };
      navigateByNameAndParams('bidAad006Info', { bidProjectKey, isNotKeepAlive: false });
    };

    const createDefaultValue = data => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      formDefault.yearAndMonthDate = new Date(parseInt(data.syr) + 1911, parseInt(data.month) - 1);
      reset();
    };

    //限制能選的日期
    function notAfterSdateEnd(date: Date) {
      return date > new Date();
    }

    const toLook = () => {
      $bvModal.show('graph-add-modal');
    };

    watch(
      bringValueProp,
      newValue => {
        reset();
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          createDefaultValue(newValue);
        }
      },
      { immediate: true }
    );

    const jobOptions = [
      '現場工程師',
      '鋼筋工',
      '模板工',
      '泥水工',
      '普通工',
      '機械操作工',
      '體力工',
      '建築細木工',
      '水電工',
      '混凝澆置工',
      '路面鋪設工',
      '砌石工',
      '其他工',
    ];

    return {
      projectInfo,
      formDefault,
      $v,
      reset,
      validateState,
      dataPromise,
      // table,
      queryOptions,
      toQueryView,
      createDefaultValue,
      FormStatusEnum,
      formStatusRef,
      toSubmit,
      ccutnitoptionss,
      formOptionsFormatter,
      stepVisible,
      notAfterSdateEnd,
      jobOptions,
      form,
      toLook,
      $bvModal,
    };
  },
  filters: {
    dateToString: (value: string) => {
      var date = new Date(value);
      const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      return year + '年' + month + '月' + day + '日';
    },
    responsIdToName: (value: string) => {},
  },
};
</script>
<style></style>

<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>

    <div>
      <span style="color: red">職業安全衛生法</span>
      <a
        href="http://law.moj.gov.tw/LawClass/LawSingle.aspx?Pcode=N0060001&FLNO=37"
        style="color: black; text-decoration: underline !important"
        >第37條</a
      >
      <span style="color: blue">事業單位工作發生職業災害，雇主應...</span>
    </div>
    <div class="card">
      <!--   標頭(依功能分)   -->
      <div class="card-header py-1 text-left" v-if="formStatusForwardProp === 'create'">
        <h5 class="m-0">新增工安事件狀況資料</h5>
      </div>

      <div
        class="card-header py-1 text-left"
        v-if="
          formStatusForwardProp === 'createView' ||
          formStatusForwardProp === 'detail' ||
          formStatusForwardProp === 'modifyView' ||
          formStatusForwardProp === 'delete'
        "
      >
        <h5 class="m-0">工安事件狀況資料</h5>
      </div>

      <div class="card-header py-1 text-left" v-if="formStatusForwardProp === 'modify'">
        <h5 class="m-0">修改工安事件狀況資料</h5>
      </div>
      <!--   標頭(依功能分)    -->

      <div class="card-body col-12">
        <b-form-row v-if="formStatusForwardProp === 'modify'">
          <i-form-group-check :label-cols="2" :content-cols="10" class="col-12">
            初次填報日期{{ form.createDate | dateToString }} 最後修正日期{{ form.updateDate | dateToString }}
          </i-form-group-check>
        </b-form-row>
        <b-form-row v-if="formStatusForwardProp === 'create'">
          <i-form-group-check :label="'發生日期 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true" :item="$v.sdate">
            <i-date-picker
              v-model="$v.sdate.$model"
              placeholder="yyy/MM/dd"
              class="col-4"
              :state="validateState($v.sdate)"
              :disabled-date="notAfterSdateEnd"
            ></i-date-picker>
          </i-form-group-check>
        </b-form-row>
        <b-form-row v-if="formStatusForwardProp === 'modify'">
          <i-form-group-check :label="'發生日期 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
            <i-date-picker
              v-model="$v.sdateNew.$model"
              placeholder="yyy/MM/dd"
              class="col-4"
              :state="validateState($v.sdateNew)"
              :disabled-date="notAfterSdateEnd"
            ></i-date-picker>
          </i-form-group-check>
        </b-form-row>
        <b-form-row v-if="formStatusForwardProp === 'create' || formStatusForwardProp === 'modify'">
          <i-form-group-check :label="'罹災人員隸屬單位 '" :label-cols="2" :content-cols="10" class="col-12">
            <b-form-select class="col-4" v-model="$v.ccnt.$model" :options="ccntOptions"></b-form-select>
            <span style="color: blue">(人員如屬合法分包廠商聘僱員工，請先確認A9是否完整填報)</span>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'create' || formStatusForwardProp === 'modify'">
          <i-form-group-check :label="'可歸責之廠商1 '" :label-cols="2" :content-cols="10" class="col-12">
            <b-form-select class="col-4" v-model="$v.respons.$model" :options="responsOptions"></b-form-select>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'create' || formStatusForwardProp === 'modify'">
          <i-form-group-check :label="'可歸責之廠商2 '" :label-cols="2" :content-cols="10" class="col-12">
            <b-form-select class="col-4" v-model="$v.respons2.$model" :options="responsOptions"></b-form-select>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'create' || formStatusForwardProp === 'modify'">
          <i-form-group-check :label="'管轄單位 '" :label-cols="2" :content-cols="10" class="col-12">
            <b-form-select class="col-4" v-model="$v.ctUnit.$model" :options="ctUnitOptions"></b-form-select>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'create' || formStatusForwardProp === 'modify'">
          <i-form-group-check :label="'災害類型 '" :label-cols="2" :content-cols="10" class="col-12">
            <b-form-select class="col-4" v-model="$v.stype.$model" :options="sTypeOptions"></b-form-select>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'create' || formStatusForwardProp === 'modify'">
          <i-form-group-check :label="'媒介物 '" :label-cols="2" :content-cols="10" class="col-12">
            <b-form-input class="col-4" v-model="$v.media.$model"></b-form-input>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'create' || formStatusForwardProp === 'modify'">
          <i-form-group-check
            :label="'傷亡人數 '"
            :label-cols="2"
            :content-cols="10"
            class="col-12"
            :item="[$v.diedNum, $v.hurtNum]"
            :label-star="true"
          >
            <!--            dual1=""-->
            <!--            :dual2=""-->
            死亡
            <b-form-input class="col-4" v-model="$v.diedNum.$model" :state="validateState($v.diedNum)"></b-form-input>
            人 受傷
            <b-form-input class="col-4" v-model="$v.hurtNum.$model" :state="validateState($v.hurtNum)"></b-form-input>
            人
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'create' || formStatusForwardProp === 'modify'">
          <i-form-group-check
            :label="'罹災時災情 '"
            :label-cols="2"
            :content-cols="10"
            class="col-12"
            :label-star="true"
            :item="$v.disasterScript"
          >
            <b-form-textarea v-model="$v.disasterScript.$model" :state="validateState($v.disasterScript)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'create' || formStatusForwardProp === 'modify'">
          <i-form-group-check
            :label="'發生前累計工期 '"
            :label-cols="2"
            :content-cols="10"
            class="col-12"
            :label-star="true"
            :item="$v.wdayed"
          >
            <b-form-input class="col-4" v-model="$v.wdayed.$model" :state="validateState($v.wdayed)"></b-form-input>
            天<span style="color: blue">(填寫發生災害前一日之公共工程施工日誌所填報之累計工期)</span>
            <span style="color: red">(截至 {{ getLastYear }}年{{ getLastMonth }} 月底止累計工期共{{ wdayeds }} 天。)</span>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'create' || formStatusForwardProp === 'modify'">
          <i-form-group-check :label="'備註 '" :label-cols="2" :content-cols="10" class="col-12">
            <span style="color: blue">(填寫發生災害責任單位...等等，)</span>
            <b-form-textarea v-model="$v.remark.$model"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'create'">
          <i-form-group-check :label="'提報勞動部日期 '" :label-cols="2" :content-cols="10" class="col-12" :item="$v.peDate">
            <i-date-picker v-model="$v.peDate.$model" placeholder="yyy/MM/dd" class="col-4"></i-date-picker>
          </i-form-group-check>
        </b-form-row>
        <b-form-row v-if="formStatusForwardProp === 'modify'">
          <i-form-group-check :label="'提報勞動部日期 '" :label-cols="2" :content-cols="10" class="col-12" :item="$v.peDate">
            <i-date-picker v-model="$v.peDate.$model" placeholder="yyy/MM/dd" class="col-4" lazy></i-date-picker>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'create'">
          <i-form-group-check :label="'提報文號 '" :label-cols="2" :content-cols="10" class="col-12" :label-star="true" :item="$v.prmNo">
            <b-form-input class="col-4" v-model="$v.prmNo.$model" :state="validateState($v.prmNo)"></b-form-input>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'create'">
          <i-form-group-check :label="'核定文號 '" :label-cols="2" :content-cols="10" class="col-12">
            <b-form-input class="col-4" v-model="$v.mrmNo.$model"></b-form-input>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'modify'">
          <i-form-group-check :label="'提報文號 '" :label-cols="2" :content-cols="10" class="col-12" :label-star="true" :item="$v.prmNo">
            <b-form-input class="col-4" v-model="$v.prmNo.$model" :state="validateState($v.prmNo)"></b-form-input>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'modify'">
          <i-form-group-check :label="'勞動部核定日期 '" :label-cols="2" :content-cols="10" class="col-12">
            <b-col v-if="form.meDate == null || JSON.stringify($v.meDate) == '{}'"
              >年月日<span style="color: blue">(本欄由勞動部指定人員提報)</span></b-col
            >
            <b-col v-if="form.meDate != null">{{ form.meDate }} <span style="color: blue">(本欄由勞動部指定人員提報)</span></b-col>
          </i-form-group-check>
        </b-form-row>

        <b-form-row v-if="formStatusForwardProp === 'modify'">
          <i-form-group-check :label="'核定文號 '" :label-cols="2" :content-cols="10" class="col-12">
            <b-col v-if="form.meDate == null"><span style="color: blue">(本欄由勞動部指定人員提報)</span></b-col>
            <b-col v-if="form.meDate != null">{{ form.mrmNo }}<span style="color: blue"> (本欄由勞動部指定人員提報)</span></b-col>
          </i-form-group-check>
        </b-form-row>

        <div>
          <b-col style="text-align: center" v-if="formStatusForwardProp === 'create'">
            <i-button type="check-circle" @click="submitForm('create')"></i-button>
            <i-button type="x-circle" @click="reset"></i-button>
            <i-button type="arrow-counterclockwise" @click="toCancel"></i-button>
          </b-col>
          <b-col style="text-align: center" v-if="formStatusForwardProp === 'modify'">
            <i-button type="check-circle" @click="submitForm('modify')"></i-button>
            <i-button type="x-circle" @click="reset"></i-button>
            <i-button type="arrow-counterclockwise" @click="toCancel"></i-button>
          </b-col>
        </div>
      </div>
    </div>
    <!-- 錯誤訊息 -->
    <b-modal id="valid-error-modal" size="lg" title="資料驗證有誤" :header-text-variant="'light'" :header-bg-variant="'danger'" hide-footer>
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModals.hide('valid-error-modal')">了解</b-button>
    </b-modal>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, toRefs, Reactive } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';

import { useValidation, validateState } from '@/shared/form';
import { useGetters } from '@u3u/vue-hooks';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { boolean } from '@storybook/addon-knobs';

import { IBidPrjSafe, BidPrjSafe } from '@/shared/model/bidModel/bid-prj-safe';
import BidProjectUnitService from '@/components/bid/bidService/bid-project-unit.service';
import BidProjectService from '@/components/bid/bidService/bid-project.service';

import BidCommonService from '@/components/bid/bidService/bid-common.service';
import { numeric, decimal, required, integer } from '@/shared/validators';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { IBidDsptch } from '@/shared/model/bidModel/bid-dsptch.model';

export default {
  name: 'bidAad002EditInfo',
  components: {
    bidProjectInfo,
  },
  props: {
    formStatusForward: {
      type: String,
      required: true,
    },
    // 由前面傳來的值決定找單一工安事件狀況或者列表
    bidPrjSafeKey: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectUnitService = new BidProjectUnitService();
    // 傳入PK
    const bidPrjSafeKeyProp: Ref<any> = toRef(props, 'bidPrjSafeKey');
    //傳入狀態
    const formStatusForwardProp = toRefs(props).formStatusForward;
    // 基本資料
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();

    // 跳轉分頁用
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      CREATEVIEW = 'createView',
      MODIFY = 'modify',
      MODIFYVIEW = 'modifyView',
      DELETE = 'delete',
    }

    const notificationService: NotificationService = useNotification();
    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    // modal物件
    const $bvModal = useBvModal();

    //是否要重新查詢
    const isReload: boolean = ref(null);

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const iTable = ref(null);

    // 空值表單
    let cleanTable = {
      wkut: '', // 執行單位
      prjno: '', // 標案編號
      sdate: undefined, // 發生日期
      sdateNew: undefined,
      ccnt: '', // 罹災人員隸屬單位
      respons: '', // 可歸責廠商1
      respons2: '', // 可歸責廠商2
      ctUnit: '', // 管轄單位
      stype: '', // 災害類型
      media: '', // 媒介物
      diedNum: '', //死亡人數
      hurtNum: '', // 受傷人數
      disasterScript: '', //罹災時災情
      wdayed: '', // 發生前累計工期
      remark: '', // 備註
      peDate: '', // 提報勞動部日期
      prmNo: '', // 提報文號
      mrmNo: '', // 核定文號
      meDate: '', // 勞動部核定日期
      createDate: undefined, //新增日期
      createUser: '', //新增人員
      updateDate: undefined, //更新日期
      updateUser: '', //更新人員
      uIp: '', //更新IP
      confOut: '', // 預定執行單位資料
      ctrNo: '', // 管控編號
      ccut: '', // 發生廠商
      medatm: '', // 未使用
      pedatm: '', // 未使用
    };
    //檢核規則
    let rules = {
      wkut: {}, // 執行單位
      prjno: {}, // 標案編號
      sdate: { notNull: required }, // 發生日期
      sdateNew: { notNull: required }, // 發生日期新
      ccnt: {}, // 罹災人員隸屬單位
      respons: {}, // 可歸責廠商1
      respons2: {}, // 可歸責廠商2
      ctUnit: {}, // 管轄單位
      stype: {}, // 災害類型
      media: {}, // 媒介物
      diedNum: { required: required, numeric: numeric }, //死亡人數
      hurtNum: { required: required, numeric: numeric }, // 受傷人數
      disasterScript: { required: required }, //罹災時災情
      wdayed: { required: required, numeric: numeric }, // 發生前累計工期
      remark: {}, // 備註
      peDate: {}, // 提報勞動部日期
      prmNo: { required: required }, // 提報文號
      mrmNo: {}, // 核定文號
      meDate: {}, // 勞動部核定日期
      createDate: {}, //新增日期
      createUser: {}, //新增人員
      updateDate: {}, //更新日期
      updateUser: {}, //更新人員
      uIp: {}, //更新IP
      confOut: {}, // 預定執行單位資料
      ctrNo: {}, // 管控編號
      ccut: {}, // 發生廠商
      medatm: {}, // 未使用
      pedatm: {}, // 未使用
    };

    const responsOptions = ref([]);
    const ccntOptions = ref([]);
    const sTypeOptions = ref([]);
    const ctUnitOptions = ref([]);

    // 罹災人員隸屬單位
    function handleCcntChange(wkut: string, prjno: string) {
      ccntOptions.value.splice(0, ccntOptions.value.length);
      bidProjectUnitService
        .findBidCcntByKeyWorld(wkut, prjno)
        .then(res => {
          if (res != null && res.length > 0) {
            res.forEach(ele => {
              if (!(ele.pcmut != null && ele.subccut != null)) {
                if (ele.type === '2') {
                  const ccntOption = { value: '', text: '' };
                  ccntOption.value = ele.scut;
                  ccntOption.text = '監造單位--' + ele.name;
                  ccntOptions.value.push(ccntOption);
                } else if (ele.type === '3') {
                  const ccntOption = { value: '', text: '' };
                  ccntOption.value = ele.ccut;
                  ccntOption.text = '承攬廠商--' + ele.name;
                  ccntOptions.value.push(ccntOption);
                }
              } else if (ele.pcmut != null) {
                const ccntOption = { value: '', text: '' };
                ccntOption.value = ele.pcmut;
                ccntOption.text = 'PCM單位--' + ele.name;
                ccntOptions.value.push(ccntOption);
              } else if (ele.subccut != null) {
                const ccntOption = { value: '', text: '' };
                ccntOption.value = ele.subccut;
                ccntOption.text = '分包協力--' + ele.name;
                ccntOptions.value.push(ccntOption);
              }
            });
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 可歸責廠商的選項
    function handleResponsChange(wkut: string, prjno: string) {
      responsOptions.value.splice(0, responsOptions.value.length);
      bidProjectUnitService
        .findBidCcutByKeyWorld(wkut, prjno)
        .then(res => {
          if (res != null && res.length > 0) {
            res.forEach(ele => {
              if (ele.type === '3') {
                const responsOption = { value: '', text: '' };
                responsOption.value = ele.ccut;
                responsOption.text = '承攬廠商--' + ele.name;
                responsOptions.value.push(responsOption);
              }
            });
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 管轄單位的選項
    function handleCtUnitChange() {
      // 給SYSCODE 所需的搜尋條件
      let ctUnitSysCode = { moduleType: 'BID', dataType: 'BID_017' };
      ctUnitOptions.value.splice(0, ctUnitOptions.value.length);
      axios
        .post('/adm-sys-codes/criteria-jpa', ctUnitSysCode)
        .then(({ data }) => {
          if (data != null && Array.isArray(data.content)) {
            data.content.forEach(ele => {
              const ctUnitOption = { value: '', text: '' };
              ctUnitOption.value = ele.dataKey;
              ctUnitOption.text = ele.value;
              ctUnitOptions.value.push(ctUnitOption);
            });
          }
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    }

    // 災害類型單位的選項
    function handleStypeChange() {
      // 給SYSCODE 所需的搜尋條件
      let sTypeSysCode = { moduleType: 'BID', dataType: 'BID_018' };
      sTypeOptions.value.splice(0, sTypeOptions.value.length);
      axios
        .post('/adm-sys-codes/criteria-jpa', sTypeSysCode)
        .then(({ data }) => {
          if (data != null && Array.isArray(data.content)) {
            data.content.forEach(ele => {
              let sTypeOption = { value: '', text: '' };
              sTypeOption.value = ele.dataKey;
              sTypeOption.text = ele.value;
              sTypeOptions.value.push(sTypeOption);
            });
          }
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    }

    //預設值
    // const formDefault = Object.assign({}, cleanTable);
    const formDefault: IBidPrjSafe = new BidPrjSafe();
    // 建立表單物件 ref
    const form: Reactive<IBidPrjSafe> = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //創建初始值
    const createDefaultValue = (data: IBidPrjSafe) => {
      // 當在修改的時候，資料從後端打進來資料型態i-datepicker，會無法判斷
      data.peDate = new Date(data.peDate);
      data.sdateNew = new Date(data.sdate);
      Object.assign(formDefault, data);
      reset();
    };

    /**
     * 撈出project的部分資料(基本資料)
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
          result.typeCode = data;
          Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        });
      });
    }

    //變更表單操作狀態，當form狀態不為查看時重置
    const changeFormStatus = (status: formStatusEnum) => {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    // //取得使用者資訊id
    // const userIdentity = ref(useGetters(['account']).account.value).value.id;
    // // const userIp = ref(useGetters(['account']).account.value).value.ip;

    /**
     * 從使用者撈 user,ip的作法。
     * 如果是登錄就有的資訊應從使用者那邊撈出來
     *
     * */
    const store = context.root.$store;
    let userIdentity = store.getters.account.id;
    let userIp = store.getters.account.ip;

    const handleQuery = bidPrjSafeKeyProp => {
      axios
        .post('/bid-prj-safes/getOne', bidPrjSafeKeyProp.value) //後端分頁
        .then(({ data }) => {
          if (iTable.value) iTable.value.state.pagination.currentPage = 1;
          createDefaultValue(data);
        })
        .catch(notificationErrorHandler(notificationService));
    };
    const submitForm = (submitMethod: string) => {
      if (submitMethod === 'create') {
        form.sdateNew = form.sdate;
      }
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (submitMethod === 'modify') {
            //提交修改
            form.updateUser = userIdentity;
            form.uIp = userIp;
            // // 前端做時區的時差處理
            // const value = new Date();
            // // 時區差(分鐘)
            // const offset = value.getTimezoneOffset();
            form.updateDate = new Date();
            // form.updateDate = new Date(form.updateDate.getTime() - offset * 60 * 1000);
            // form.sdateNew = new Date(form.sdateNew.getTime() - offset * 60 * 1000);

            axios
              .put('/bid-prj-safes/updateOne', form)
              .then(({ data }) => {
                //填入值
                createDefaultValue(data);

                notificationService.success('資料異動成功');
                let bidPrjSafeKey = {
                  wkut: form.wkut,
                  prjno: form.prjno,
                  sdate: form.sdate,
                };
                navigateByNameAndParams('bidAad002Detail', { bidPrjSafeKey, formStatusForward: 'modifyView' });
              })
              .catch(err => {
                if (err.response && err.response.data.errorKey === 'bidValidError') {
                  // console.log(err.response.data.detailMessages);
                  errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
                  $bvModals.show('valid-error-modal');
                }
              });
          } else if (submitMethod === 'create') {
            //提交新增
            form.wkut = bidPrjSafeKeyProp.value.wkut;
            form.prjno = bidPrjSafeKeyProp.value.prjno;
            // // 前端做時區的時差處理
            // const value = new Date();
            // // 時區差(分鐘)
            // const offset = value.getTimezoneOffset();
            // form.sdate = new Date(form.sdate.getTime() - offset * 60 * 1000);
            // form.sdate = new Date();
            form.createUser = userIdentity;
            form.updateUser = userIdentity;
            form.createDate = new Date();
            // form.createDate = new Date(form.createDate.getTime() - offset * 60 * 1000);
            form.updateDate = new Date();
            // form.updateDate = new Date(form.updateDate.getTime() - offset * 60 * 1000);

            axios
              .post(`/bid-prj-safes`, form)
              .then(({ data }) => {
                createDefaultValue(data);

                notificationService.success('資料新增成功');

                let bidPrjSafeKey = {
                  wkut: bidPrjSafeKeyProp.value.wkut,
                  prjno: bidPrjSafeKeyProp.value.prjno,
                  sdate: form.sdate,
                };
                navigateByNameAndParams('bidAad002Detail', { bidPrjSafeKey, formStatusForward: 'createView' });
              })
              .catch(err => {
                if (err.response && err.response.data.errorKey === 'bidValidError') {
                  // console.log('@@@@@@', err.response.data.detailMessages);
                  errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
                  $bvModals.show('valid-error-modal');
                }
              });
          }
        } else {
          notificationService.danger('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    // 取消
    const toCancel = () => {
      reset();
      let bidProjectKey = {
        wkut: bidPrjSafeKeyProp.value.wkut,
        prjno: bidPrjSafeKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAad002Info', { bidProjectKey, formStatusForward: 'info' });
    };

    // //確認
    // const toConfirm = () => {
    //   reset();
    //   let bidPrjSafeKey = {
    //     wkut: form.wkut,
    //     prjno: form.prjno,
    //   };
    //   navigateByNameAndParams('bidAad002Info', { bidPrjSafeKey, formStatusForward: 'info' });
    // };

    // 上個月
    const getLastMonth = ref();
    // 上個月的年份
    const getLastYear = ref();

    const wdayeds = ref();

    const getWdayeds = (wkut, prjno) => {
      let date = new Date(new Date().getTime());
      // 上個月.
      let lastMonth = date.getMonth();
      let lastMonthStr = '';
      if (lastMonth === 0) {
        lastMonth = 12;
      }
      if (lastMonth < 10) {
        lastMonthStr = '0' + lastMonth;
      } else {
        lastMonthStr = '' + lastMonth;
      }
      // 上個月的年份
      let lastYear = date.getFullYear() - 1911;
      if (lastMonth === 12) {
        lastYear = lastYear - 1;
      }

      // 查詢WDAYEDS
      axios.get(`bid-prj-months/${wkut}/${prjno}/${lastYear}/${lastMonthStr}`).then(({ data }) => {
        if (data != null) {
          if (data.wdayeds != null) {
            wdayeds.value = data.wdayeds;
            getLastYear.value = data.syr;
            getLastMonth.value = data.month;
          } else {
            wdayeds.value = '';
            getLastYear.value = data.syr;
            getLastMonth.value = data.month;
          }
        }
      });
    };

    function notAfterSdateEnd(date: Date) {
      return date > new Date();
    }

    watch(
      bidPrjSafeKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          //基本資料
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);

          // 依照功能來判斷是否要先將資料匯入版面
          if (
            formStatusForwardProp.value === 'modify' ||
            formStatusForwardProp.value === 'modifyView' ||
            formStatusForwardProp.value === 'createView' ||
            formStatusForwardProp.value === 'info' ||
            formStatusForwardProp.value === 'delete'
          ) {
            handleQuery(bidPrjSafeKeyProp);
          } else {
            Object.assign(formDefault, cleanTable);
            Object.assign(form, formDefault);
            changeFormStatus(formStatusEnum.CREATE);
            createDefaultValue(form);
          }
          // 下拉式選單選項
          handleCcntChange(newValue.wkut, newValue.prjno);
          handleResponsChange(newValue.wkut, newValue.prjno);
          handleCtUnitChange();
          handleStypeChange();
          getWdayeds(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    return {
      bidPrjSafeKeyProp,
      formStatusForwardProp,
      projectInfo,
      form,
      $v,
      validateState,
      handleQuery,
      responsOptions,
      ccntOptions,
      sTypeOptions,
      ctUnitOptions,
      submitForm,
      formStatusEnum,
      formStatusRef,
      reset,
      toCancel,
      // toConfirm,
      getLastMonth,
      getLastYear,
      wdayeds,
      notAfterSdateEnd,
      $bvModals,
      errorMessages,
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
  },
};
</script>

<style scoped></style>

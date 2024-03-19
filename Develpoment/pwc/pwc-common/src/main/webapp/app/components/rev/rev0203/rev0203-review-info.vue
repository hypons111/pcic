<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">可行性評估/新興建設計畫審查</h5>
        </div>
        <!-- 申請資料1 -->
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.caseNa') + '：'" :item="$v.caseName">
              <b-form-input v-model="$v.caseName.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.caseNum') + '：'" :item="$v.caseNum">
              <b-form-input v-model="$v.caseNum.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.reviewCategory') + '：'" :item="$v.reviewType">
              <b-form-input v-model="$v.reviewType.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.classify') + '：'" :item="$v.classify">
              <b-form-input v-model="$v.classify.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.planType') + '：'" :item="$v.planType">
              <b-form-input v-model="$v.planType.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.totalBudget') + '：'" :item="$v.totalBudget">
              <b-form-input v-model="$v.totalBudget.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>

        <!-- 申請資料2 -->
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.bos') + '：'" :item="$v.adminOrg">
              <b-form-input v-model="$v.adminOrg.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.dept') + '：'" :item="$v.org">
              <b-form-input v-model="$v.org.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.planContact') + '：'" :item="$v.contactPerson">
              <b-form-input v-model="$v.contactPerson.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.tel') + '：'" :item="$v.contactTel">
              <b-form-input v-model="$v.contactTel.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.contactEmail') + '：'" :item="$v.contactEmail">
              <b-form-input v-model="$v.contactEmail.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.planDesc') + '：'" :item="$v.planDesc">
              <b-form-input v-model="$v.planDesc.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.planStartDate') + '：'" :item="$v.planStartDate">
              <b-form-input v-model="$v.planStartDate.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.planEndDate') + '：'" :item="$v.planEndDate">
              <b-form-input v-model="$v.planEndDate.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :label="$t('label.planManual') + '：'"
              :item="$v.planManual"
            >
              <b-form-input v-model="$v.planManual.$model" disabled> </b-form-input>
              <i-button class="ml-1" type="binoculars"></i-button>
            </i-form-group-check>
            <i-form-group-check> </i-form-group-check>
          </b-form-row>
        </div>

        <!-- 特殊備註 -->
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.specialNote') + '：'"
              :item="$v.specialNote"
            >
              <b-form-textarea rows="3" v-model="$v.specialNote.$model" disabled></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
        </div>

        <!-- 審查畫面-審查資訊 -->
        <div class="card-header mt-5">
          <h5 class="m-0">審查資訊</h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.projectUndertaker') + '：'" :item="$v.contactPerson">
              <b-form-input v-model="$v.contactPerson.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.contactInformationTel') + '：'" :item="$v.contactTel">
              <b-form-input v-model="$v.contactTel.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.contactEmail') + '：'" :item="$v.contactEmail">
              <b-form-input v-model="$v.contactEmail.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check> </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.reviewFile') + '：'" :item="$v.reviewFile">
              <b-form-file
                v-model.trim="$v.reviewFile.$model"
                browse-text="選擇檔案"
                :placeholder="$v.reviewFile.$model ? $v.reviewFile.name : ''"
              >
              </b-form-file>
            </i-form-group-check>
            <i-form-group-check> <b-button variant="info">上傳</b-button></i-form-group-check>
          </b-form-row>
        </div>

        <div class="card-header mt-5">
          <h5 class="m-0">審議附件清單</h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.reviewFile') + '：'"
              :item="$v.reviewFile"
            >
              <b-form-input v-model="$v.reviewFile.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <!--檢視按鈕-開啟審議附件檔案 -->
          <div class="button-float-right">
            <b-button-toolbar class="mt-1">
              <i-button class="ml-1" type="binoculars"></i-button>
              <i-button class="ml-1" type="trash"></i-button>
            </b-button-toolbar>
          </div>
        </div>

        <!-- 審查畫面-工程會審議 -->
        <div class="card-header mt-5">
          <h5 class="m-0">工程會審議</h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.receiptDate') + '：'" :item="$v.receiptDate" :labelStar="true">
              <b-form-input v-model="$v.receiptDate.$model"> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.receiptNo') + '：'" :item="$v.receiptNo" :labelStar="true">
              <b-form-input v-model="$v.receiptNo.$model"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.reviewTotalBudget') + '：'" :item="$v.reviewTotalBudget" :labelStar="true">
              <b-form-input v-model="$v.reviewTotalBudget.$model"> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.approvalBudget') + '：'" :item="$v.approvalBudget" :labelStar="true">
              <b-form-input v-model="$v.approvalBudget.$model"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="$t('label.comment') + '：'" :item="$v.specialNote">
              <b-form-textarea rows="3" v-model="$v.comment.$model"></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.specialNote') + '：'"
              :item="$v.specialNote"
            >
              <b-form-input v-model="$v.specialNote.$model"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.reviewHistory') + '：'"
              :item="$v.reviewHistory"
            >
              <b-form-input v-model="$v.reviewHistory.$model"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>

        <!-- 審查畫面-缺失清單 -->
        <div class="card-header">
          <h5 class="m-0">缺失清單</h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" :label="$t('label.defectItems') + '：'">
              A. 程序面缺失
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(A) 未依原預定期程送審</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(B) 送審前該個案工程已發包</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(C) 非屬應送本會審議之案件</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(D) 計畫程序未完備</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(E) 工程內容與計畫核定內容不符</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(F) 提送資料項目未齊全</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label=""> B. 技術面缺失 </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label=""
              ><b-form-checkbox :true-value="true" :false-value="false">(A) 基本資料調查未詳實</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(B) 工程量體或數量不合理</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(C) 空間或設施配置不當</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(D) 設計規範引用不當或錯誤、工期安排未妥適</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label=""> C. 經費面缺失 </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(A) 經費架構比例不合理</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(B) 單價與數量編列內容未詳實</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(C) 大宗營建材料單價不合理</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label=""> D. 其他缺失 </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(A) 其他</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label=""> E. 無缺失 </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(A) 本次審議無缺失</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label=""> </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" :label="$t('label.defectRemark') + '：'">
              <b-form-textarea rows="3"></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
        </div>

        <!-- 審查畫面-審議進度 -->
        <div class="card-header">
          <h5 class="m-0">審議進度</h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.reviewProgress') + '：'" :labelStar="true">
              <b-form-select :options="options.reviewProgress"></b-form-select>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.changeTime') + '：'" :labelStar="true">
              <b-form-input v-model="$v.updateTime.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.changeUser') + '：'" :labelStar="true">
              <b-form-input v-model="$v.updateUser.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check> </i-form-group-check>
          </b-form-row>

          <div class="button-float-right">
            <b-button-toolbar>
              <i-button class="mt-1 ml-1" type="check-circle" @click="toQueryView"></i-button>
            </b-button-toolbar>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { FormStatusEnum } from '@/shared/enum';
import { RevMain } from '@/shared/model/revModel/rev-main.model';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'rev0203ReviewInfo',
  //props可接受父元件傳來的物件，裡面可以定義也可以放空物件如xxx:{}
  props: {
    revMain: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const revMainProp = toRefs(props).revMain;

    const notificationService: NotificationService = useNotification();

    // 宣告表單物件及初始值
    const formDefault = {
      //基本資料1
      caseName: '', //案件名稱
      caseNum: '1071C111090000', //案件編號
      reviewType: '', //審議類別
      classify: '', //工程建設類別
      planType: '', //計畫類別
      totalBudget: '4,174,200', //函報總經費(萬元)

      //基本資料2
      adminOrg: '', // 主管機關
      org: '', // 主辦機關
      contactPerson: '', // 計畫聯絡人
      contactTel: '02-23657210#2232', // 電話
      contactEmail: 'u284582@test.com', // 電子郵件
      planDesc: '', // 計畫概述
      planStartDate: '109/01/01', // 計畫日期(起)
      planEndDate: '110/12/31', // 計畫日期(訖)
      planManual: null, //計畫書

      specialNote: '', //特殊備註

      isPurcContentCheck: undefined, //發包內容是否與基設一致
      isPurcFundCheck: undefined, //發包經費是否符合基設預算額度內
      reviewFile: null, //審議附件
      receiptDate: '', //本會收文日期
      receiptNo: '', //本會收文號
      reviewTotalBudget: '', //屬本會審議範圍總經費(萬元)
      approvalBudget: '', //本會核定經費(萬元)
      comment: '', //審議意見
      reviewHistory: '', //審議歷程

      reviewProgress: '', //審議進度
      updateTime: '', //異動時間
      updateUser: '', //異動人員
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      caseName: { notnull: required },
      caseNum: {},
      reviewType: { notnull: required },
      classify: { notnull: required },
      planType: { notnull: required },
      totalBudget: { notnull: required },

      adminOrg: {},
      org: {},
      contactPerson: {},
      contactTel: {},
      contactEmail: {},
      planDesc: {},
      planStartDate: {},
      planEndDate: {},
      planManual: {},

      specialNote: {},

      isPurcContentCheck: {},
      isPurcFundCheck: {},
      reviewFile: {},

      receiptDate: { notnull: required },
      receiptNo: { notnull: required },
      reviewTotalBudget: { notnull: required },
      approvalBudget: { notnull: required },
      comment: {},
      reviewHistory: {},

      reviewProgress: { notnull: required },
      updateTime: {},
      updateUser: {},
    };
    //checkValidity用來檢核，reset用來重置form
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 下拉選單選項
    const options = reactive({
      reviewProgress: [
        { value: '', text: '審查中' },
        { value: '1', text: '審查完成' },
      ],
    });

    // 是否需要重新查詢
    const isReload = ref(false);

    const $bvModal = useBvModal();

    const toQueryView = () => {
      handleBack({ isReload: isReload.value, isNotKeepAlive: true });
      isReload.value = false;
    };

    const createDefaultValue = (data: RevMain) => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
    };

    watch(
      revMainProp,
      () => {
        createDefaultValue(revMainProp.value);
      },
      { immediate: true }
    );

    return {
      $v,
      options,
      toQueryView,
      validateState,
      revMainProp,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

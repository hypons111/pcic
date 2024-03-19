<template>
  <div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">詳細資料</h5>
      </div>
      <div class="card-body col-11">
        <!-- 查核方式 -->
        <b-row>
          <i-form-group-check :labelStar="true" :label="'查核方式：'" :item="$v.method">
            <!-- :item="$v.respons" -->
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.method.$model">
                <!-- v-model="$v.respons.$model" -->
                <b-form-radio value="Y">事先函知</b-form-radio>
                <b-form-radio value="N">不事先通知</b-form-radio>
              </b-form-radio-group>
            </div>
            <div>
              (依工程施工查核小組作業辦法第五條規定，得不預先通知赴工地進行查核。 94.9.26
              工程管字第09400350900號函工程施工查核小組不預先通知查核作業補充規定詳細。)
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 預定查核日期 -->
        <b-row>
          <i-form-group-check :labelStar="true" :label="'預定查核日期：'" :item="$v.checkDate">
            <!-- :dual1.sync="$v.reviewDateStart.$model" :dual2.sync="$v.reviewDateEnd.$model" -->
            <b-input-group>
              <i-date-picker v-model="$v.checkDate.$model" :state="validateState($v.checkDate)"></i-date-picker>
              <b-input-group-text>{{ '至' }}</b-input-group-text>
              <i-date-picker v-model="$v.checkDateEnd.$model" :disabled-date="notBeforeStartDate"></i-date-picker>
            </b-input-group>
          </i-form-group-check>
        </b-row>
        <!-- 查核小組 -->
        <b-row>
          <i-form-group-check :label="'查核小組：'">
            <!-- {{ form.wkut }} -->
            <!-- <b-form-input lazy trim>></b-form-input> -->
            <b-form-select v-model="$v.checkUnit.$model" :options="examineUnitArray"></b-form-select>
          </i-form-group-check>
        </b-row>
        <!-- 查核主題 -->
        <b-row>
          <i-form-group-check :label="'查核主題：'">
            <b-form-input v-model="$v.checkPurp.$model" lazy trim>></b-form-input>
          </i-form-group-check>
        </b-row>
        <b-row>
          <!-- 主辦人員 連絡電話 -->
          <i-form-group-check :label="'主辦人員：'">
            <b-form-input v-model="$v.checker1.$model" lazy trim>></b-form-input>
          </i-form-group-check>

          <i-form-group-check :label="'聯絡電話：'" :item="$v.checkerTel">
            <b-form-input v-model="$v.checkerTel.$model" lazy trim :state="validateState($v.checkerTel)"></b-form-input>
          </i-form-group-check>
        </b-row>
        <!-- 查核領隊 -->
        <b-row>
          <i-form-group-check :label="'查核領隊：'">
            <b-form-input v-model="$v.checkLeader.$model" lazy trim>></b-form-input>
          </i-form-group-check>
        </b-row>
        <!-- 查核委員 -->
        <b-row>
          <i-form-group-check :label="'查核委員：'" class="col-12" :label-cols="2" :content-cols="4">
            <b-form-textarea v-model="$v.checkMember.$model" rows="3" max-rows="6" lazy trim></b-form-textarea>
            <!-- :state="validateState($v.remark)" -->
          </i-form-group-check>
        </b-row>

        <!-- 功能按鈕 -->
        <b-row>
          <i-button type="save" @click="submitFormData"> </i-button>
          <i-button type="x-circle" @click="reset"> </i-button>
          <i-button type="arrow-left" @click="backToPreviousPage"> </i-button>
        </b-row>
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
import { Ref, ref, toRef, reactive, watch, unref } from '@vue/composition-api';
import { IBidGovPrecheck, BidGovPrecheck } from '@/shared/model/bidModel/bid-gov-precheck.model';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, tel } from '@/shared/validators';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useBvModal } from '@/shared/modal';
import BidGovPrecheckService from '@/components/bid/bidService/bid-gov-precheck.service';
import BidUtilService from '@/components/bid/bidService/bid-util-service';
import { navigateByNameAndParams } from '@/router/router';
import { notificationErrorHandlerWithErrorParam } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';

export default {
  name: 'bidAaa004Edit',
  props: {
    bidGovPreCheckKey: {
      required: false,
    },
    formStatus: {
      required: true,
    },
  },
  setup(props, context) {
    const bbidGovPreCheckKeyProp: Ref<any> = toRef(props, 'bidGovPreCheckKey');
    const formStatusProp: Ref<any> = toRef(props, 'formStatus');
    let bidSysCodeTable = new BidSysCodeTable();
    let formDefault: IBidGovPrecheck = new BidGovPrecheck();
    const notificationService: NotificationService = useNotification();
    const bidGovPrecheckService = new BidGovPrecheckService();
    const bidUtilService = new BidUtilService();
    //電話元件
    const telComponent = ref(null);
    //選查核單位的下拉式選單
    const examineUnitArray = reactive([]);

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    enum formStatusEnum {
      CREATE = 'create',
      MODIFY = 'modify',
    }

    let form = reactive(Object.assign({}, formDefault));

    const rules = {
      method: { required: required },
      checkDate: { required: required },
      checkDateEnd: {},
      checkUnit: {},
      checkPurp: {},
      checker1: {},
      checkerTel: { tel: tel(() => unref(telComponent).telType) },
      checkLeader: {},
      checkMember: {},
    };
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //處理傳進來的日期 從string轉成date
    function handleOriginModifyData(govPreCheck: IBidGovPrecheck) {
      govPreCheck.checkDate = new Date(govPreCheck.checkDate);
      govPreCheck.checkDateEnd = govPreCheck.checkDateEnd ? new Date(govPreCheck.checkDateEnd) : null;
    }

    //產生下拉式選單的方法
    function getExamineUnitArray(wkut: string) {
      bidUtilService.findBidExamineUnit(wkut).then(returnArray => {
        if (returnArray && returnArray.length > 0) {
          console.log(returnArray);
          returnArray.forEach(ele => {
            const item = { value: '', text: '' };
            item.value = ele.orgId;
            item.text = ele.orgName;
            examineUnitArray.push(item);
          });
        }
      });
    }

    //接收傳下來的key並判斷是新增還是修改
    watch(
      bbidGovPreCheckKeyProp,
      newValue => {
        reset();
        getExamineUnitArray(newValue.wkut);
        if (formStatusProp.value && formStatusProp.value === formStatusEnum.CREATE) {
          Object.assign(formDefault, new BidGovPrecheck());
          Object.assign(form, formDefault);
          form.wkut = newValue.wkut;
          form.prjno = newValue.prjno;
          formDefault.wkut = newValue.wkut;
          formDefault.prjno = newValue.prjno;
          //如果有殘存的id屬性則把它拿掉
          delete form['id'];
        } else if (formStatusProp.value && formStatusProp.value === formStatusEnum.MODIFY) {
          const govPrecheckPK = {
            wkut: newValue.wkut,
            prjno: newValue.prjno,
            checkDate: newValue.checkDate,
          };
          bidGovPrecheckService.getOneBidGovPrecheck(govPrecheckPK).then(govPreCheckData => {
            handleOriginModifyData(govPreCheckData);
            Object.assign(form, govPreCheckData);
            Object.assign(formDefault, govPreCheckData);
            reset();
          });
        }
      },
      { immediate: true }
    );

    function proceedFrontEndDataToBackEndDTO(bidGovPrecheck: IBidGovPrecheck) {
      // bidGovPrecheck.checkUnit = bidGovPrecheck.wkut;
    }

    //回上一頁
    function backToPreviousPage() {
      const bidProjectKey = {
        wkut: form.wkut,
        prjno: form.prjno,
      };
      reset();
      // console.log(bidProjectKey);
      navigateByNameAndParams('bidAbb001InfoMenu', {
        bidProjectKey: bidProjectKey,
      });
    }

    function submitFormData() {
      checkValidity().then(bool => {
        if (bool) {
          if (formStatusProp.value === formStatusEnum.CREATE) {
            proceedFrontEndDataToBackEndDTO(form);
            bidGovPrecheckService
              .saveBidGovPrecheck(form)
              .then(() => backToPreviousPage())
              .catch(err => {
                if (err.response && err.response.data.errorKey === 'bidValidError') {
                  errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
                  $bvModals.show('valid-error-modal');
                } else {
                  notificationErrorHandlerWithErrorParam(err, notificationService);
                }
              });
          } else if (formStatusProp.value === formStatusEnum.MODIFY) {
            proceedFrontEndDataToBackEndDTO(form);
            bidGovPrecheckService
              .updateExistBidGovPrecheck(form)
              .then(() => backToPreviousPage())
              .catch(err => {
                if (err.response && err.response.data.errorKey === 'bidValidError') {
                  errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
                  $bvModals.show('valid-error-modal');
                } else {
                  notificationErrorHandlerWithErrorParam(err, notificationService);
                }
              });
          }
        }
      });
    }

    //日期disable
    function notBeforeStartDate(date: Date) {
      if (form.checkDate) return date < new Date(form.checkDate);
    }

    return {
      formStatusProp,
      formStatusEnum,
      form,
      $v,
      examineUnitArray,
      $bvModals,
      telComponent,
      errorMessages,
      reset,
      backToPreviousPage,
      validateState,
      submitFormData,
      notBeforeStartDate,
    };
  },
};
</script>

<style>
.h-38p {
  height: 38px;
}
</style>

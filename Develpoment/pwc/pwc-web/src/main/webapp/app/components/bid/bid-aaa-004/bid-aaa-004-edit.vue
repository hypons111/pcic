<template>
  <div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">停工狀況詳細資料</h5>
      </div>
      <div class="card-body col-12">
        <!-- 停工日期 -->
        <b-row>
          <i-form-group-check
            :label="'停工日期：'"
            :disabled="formStatusProp === formStatusEnum.MODIFY"
            :labelStar="true"
            :item="$v.startDate"
          >
            <i-date-picker v-model="$v.startDate.$model" :disabled="formStatusProp === formStatusEnum.MODIFY" lazy></i-date-picker>
          </i-form-group-check>
        </b-row>
        <!-- 核定文號 -->
        <b-row>
          <i-form-group-check :label="'核定文號：'" :labelStar="true" :item="$v.prmNo">
            <b-form-input v-model="$v.prmNo.$model" :state="validateState($v.prmNo)" lazy trim></b-form-input>
          </i-form-group-check>
        </b-row>
        <!-- 停工原因 -->
        <b-row>
          <i-form-group-check :label="'停工原因：'" :labelStar="true" :item="$v.stopReson">
            <b-form-select
              v-model="$v.stopReson.$model"
              :options="stopwkReasonOptions"
              :state="validateState($v.stopReson)"
            ></b-form-select>
          </i-form-group-check>
        </b-row>
        <!-- 責任歸屬 -->
        <b-row>
          <i-form-group-check :label="'責任歸屬：'" :labelStar="true" :item="$v.respons">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.respons.$model">
                <b-form-radio value="A">甲方</b-form-radio>
                <b-form-radio value="B">乙方</b-form-radio>
                <b-form-radio value="C">甲乙雙方</b-form-radio>
                <b-form-radio value="D">分屬雙方</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 停工原因說明 -->
        <b-row>
          <i-form-group-check
            :label="'停工原因說明：'"
            :labelStar="true"
            :item="$v.remark"
            class="col-12"
            :label-cols="2"
            :content-cols="6"
          >
            <b-form-textarea
              v-model="$v.remark.$model"
              placeholder="原因說明..."
              rows="3"
              max-rows="6"
              :state="validateState($v.remark)"
              lazy
              trim
            ></b-form-textarea>
          </i-form-group-check>
        </b-row>
        <!-- 可展延工期天數 -->
        <b-row>
          <i-form-group-check :label="'可展延工期天數：'" :item="$v.noCntDay">
            <b-form-input v-model="$v.noCntDay.$model" :state="validateState($v.noCntDay)" lazy trim></b-form-input>
          </i-form-group-check>
          <div class="pt-2">天(依工程採購契約範本第7條(三).1.(3)、第21條(十)1.內容填報)</div>
        </b-row>
        <!-- 建議協調層級 -->
        <b-row>
          <i-form-group-check :label="'建議協調層級：'" :item="$v.negoLevel">
            <b-form-select v-model="$v.negoLevel.$model" :options="stopwkNegoOptions" :state="validateState($v.negoLevel)"></b-form-select>
          </i-form-group-check>
        </b-row>
        <!-- 待協調事項涉及機關名稱 -->
        <b-row>
          <i-form-group-check :label="'待協調事項涉及機關名稱：'" :item="$v.negoUnit">
            <b-form-input v-model="$v.negoUnit.$model" :state="validateState($v.negoUnit)" lazy trim></b-form-input>
          </i-form-group-check>
        </b-row>
        <!-- 預定復工日期 -->
        <b-row>
          <i-form-group-check :label="'預定復工日期：'" :item="$v.peDate">
            <i-date-picker
              :disabled-date="notBeforeStartDate"
              v-model="$v.peDate.$model"
              lazy
              :disabled="formStatusProp === formStatusEnum.MODIFY"
            ></i-date-picker>
          </i-form-group-check>
          <div class="pt-2">(本欄填報後將做為後續版控依據，不允許修改)</div>
        </b-row>
        <!-- 實際復工日期 -->
        <b-row>
          <i-form-group-check :label="'實際復工日期：'" :item="$v.endDate">
            <i-date-picker :disabled-date="notBeforeStartDate" v-model="$v.endDate.$model" lazy></i-date-picker>
          </i-form-group-check>
        </b-row>
        <!-- 復工原因說明 -->
        <b-row>
          <i-form-group-check :label="'復工原因說明：'" :item="$v.eRemark" class="col-12" :label-cols="2" :content-cols="6">
            <b-form-textarea
              v-model="$v.eRemark.$model"
              placeholder="原因說明..."
              rows="3"
              max-rows="6"
              :state="validateState($v.eRemark)"
              lazy
              trim
            ></b-form-textarea>
          </i-form-group-check>
        </b-row>
        <!-- 功能按鈕 -->
        <b-row class="mt-2 justify-content-center" align-content="center">
          <i-button class="mr-2" type="save" @click="submitFormData"> </i-button>
          <i-button class="mr-2" type="x-circle" @click="reset"> </i-button>
          <i-button class="mr-2" type="arrow-left" @click="backToPreviousPage"> </i-button>
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
import { Ref, ref, toRef, reactive, watch } from '@vue/composition-api';
import { IBidPrjStopwk, BidPrjStopwk } from '@/shared/model/bidModel/bid-prj-stopwk.model';
import BidPrjStopwkService from '@/components/bid/bidService/bid-prj-stopwk.service';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric } from '@/shared/validators';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { notificationErrorHandlerWithErrorParam } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';

export default {
  name: 'bidAaa004Edit',
  props: {
    bidProjectStopwkKey: {
      required: false,
    },
    formStatus: {
      required: true,
    },
  },
  setup(props, context) {
    const bidPrjStopwkService = new BidPrjStopwkService();
    const bidProjectStopwkKeyProp: Ref<any> = toRef(props, 'bidProjectStopwkKey');
    const formStatusProp: Ref<any> = toRef(props, 'formStatus');
    let bidSysCodeTable = new BidSysCodeTable();
    let formDefault: IBidPrjStopwk = new BidPrjStopwk();
    const notificationService: NotificationService = useNotification();

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    enum formStatusEnum {
      CREATE = 'create',
      MODIFY = 'modify',
    }

    let form = reactive(Object.assign({}, formDefault));

    const rules = {
      startDate: { required: required },
      prmNo: { required: required },
      stopReson: { required: required },
      respons: { required: required },
      remark: { required: required },
      noCntDay: { numeric: numeric },
      negoLevel: {},
      negoUnit: {},
      peDate: { required: required },
      endDate: {},
      eRemark: {},
    };
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //處理傳進來的日期 從string轉成date
    function handleOriginModifyData(stopwkData: BidPrjStopwk) {
      stopwkData.startDate = new Date(stopwkData.startDate);
      stopwkData.peDate = stopwkData.peDate ? new Date(stopwkData.peDate) : null;
      stopwkData.endDate = stopwkData.endDate ? new Date(stopwkData.endDate) : null;
    }

    function proceedFrontEndDataToBackEndDTO(stopwkData: BidPrjStopwk) {
      //處理日期的offset
      const value = new Date();
      const offset = value.getTimezoneOffset();
      stopwkData.startDate = new Date(stopwkData.startDate.getTime() - offset * 60 * 1000);
      stopwkData.peDate = stopwkData.peDate ? new Date(stopwkData.peDate.getTime() - offset * 60 * 1000) : null;
      stopwkData.endDate = stopwkData.endDate ? new Date(stopwkData.endDate.getTime() - offset * 60 * 1000) : null;
    }

    //接收傳下來的key並判斷是新增還是修改
    watch(
      bidProjectStopwkKeyProp,
      newValue => {
        reset();
        // console.log('formStatusProp.value', formStatusProp.value);
        if (formStatusProp.value && formStatusProp.value === formStatusEnum.CREATE) {
          Object.assign(formDefault, new BidPrjStopwk());
          Object.assign(form, formDefault);
          form.wkut = newValue.wkut;
          form.prjno = newValue.prjno;
          //如果有殘存的id屬性則把它拿掉
          delete form['id'];
        } else if (formStatusProp.value && formStatusProp.value === formStatusEnum.MODIFY) {
          bidPrjStopwkService.getBidProjectStopwkByKey(newValue.wkut, newValue.prjno, newValue.startDate).then(stopwkData => {
            handleOriginModifyData(stopwkData);
            Object.assign(form, stopwkData);
            Object.assign(formDefault, stopwkData);
            reset();
          });
        }
      },
      { immediate: true }
    );
    let stopwkReasonOptions = reactive([]);
    let stopwkNegoOptions = reactive([]);

    //產生兩個下拉式選單
    bidSysCodeTable.getBidSysCodeWithParam('BID_011').then(data => {
      stopwkReasonOptions.splice(0, stopwkReasonOptions.length);
      data.forEach(ele => {
        const selectOption = {
          value: '',
          text: '',
        };
        selectOption.value = ele.dataKey;
        selectOption.text = ele.value;
        stopwkReasonOptions.push(selectOption);
      });
    });
    bidSysCodeTable.getBidSysCodeWithParam('BID_012').then(data => {
      stopwkNegoOptions.splice(0, stopwkNegoOptions.length);
      data.forEach(ele => {
        const selectOption = {
          value: '',
          text: '',
        };
        selectOption.value = ele.dataKey;
        selectOption.text = ele.value;
        stopwkNegoOptions.push(selectOption);
      });
    });

    //回上一頁
    function backToPreviousPage() {
      const bidProjectKey = {
        wkut: form.wkut,
        prjno: form.prjno,
      };
      reset();
      // console.log(bidProjectKey);
      navigateByNameAndParams('bidAaa004InfoMenu', {
        bidProjectKey: bidProjectKey,
      });
    }

    //日期disable
    function notBeforeStartDate(date: Date) {
      if (form.startDate) return date < new Date(form.startDate);
    }

    function submitFormData() {
      checkValidity().then(bool => {
        if (bool) {
          if (formStatusProp.value === formStatusEnum.CREATE) {
            proceedFrontEndDataToBackEndDTO(form);
            bidPrjStopwkService
              .saveBidProjectStopwk(form)
              .then(() => {
                navigateByNameAndParams('bidAaa004InfoMenu', {
                  bidProjectKey: { wkut: form.wkut, prjno: form.prjno },
                });
              })
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
            bidPrjStopwkService
              .updateExistingBidPrjStopwk(form)
              .then(responseEntity => {
                navigateByNameAndParams('bidAaa004InfoMenu', {
                  bidProjectKey: { wkut: form.wkut, prjno: form.prjno },
                });
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
        }
      });
    }

    return {
      formStatusProp,
      formStatusEnum,
      stopwkReasonOptions,
      stopwkNegoOptions,
      $v,
      reset,
      $bvModals,
      errorMessages,
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

<template>
  <div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">各月分配詳細資料</h5>
      </div>
      <div class="card-body col-12">
        <!-- 資料月份 -->
        <b-row>
          <i-form-group-check :label="'資料月份：'" :labelStar="true" :item="$v.yearAndMonthDate">
            <!-- <b-form-input v-model="$v.syr.$model" :state="validateState($v.syr)" lazy trim></b-form-input> -->
            <i-date-picker
              type="month"
              v-model="$v.yearAndMonthDate.$model"
              :disabled="formStatusProp === formStatusEnum.MODIFY"
              lazy
            ></i-date-picker>
          </i-form-group-check>
        </b-row>
        <!-- 至上月止總累計預定支用金額 -->
        <b-row>
          <i-form-group-check :label="'總累計預定支用金額：'" :labelStar="true" :item="$v.pexp">
            <b-form-input v-model="$v.pexp.$model" :state="validateState($v.pexp)" lazy trim></b-form-input>
          </i-form-group-check>
          <div>千元</div>
        </b-row>
        <!-- 至上月止總累計預定支用金額： -->
        <b-row>
          <i-form-group-check :label="'總累計預定進度：'" :labelStar="true" :item="$v.ptrate">
            <b-form-input v-model="$v.ptrate.$model" :state="validateState($v.ptrate)" lazy trim></b-form-input>
          </i-form-group-check>
          <div>%</div>
        </b-row>
        <!-- 本月預定工作摘要 -->
        <b-row>
          <i-form-group-check :label="'本月預定工作摘要：'" :labelStar="true" :item="$v.item">
            <b-form-textarea
              v-model="$v.item.$model"
              placeholder="原因說明..."
              rows="3"
              max-rows="6"
              :state="validateState($v.item)"
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
import { IBidPrjMonth, BidPrjMonth } from '@/shared/model/bidModel/bid-prj-month.model';
import BidPrjMonthService from '@/components/bid/bidService/bid-prj-month.service';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { notificationErrorHandlerWithErrorParam } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';

export default {
  name: 'bidAab001Edit',
  props: {
    bidProjectMonthKey: {
      required: false,
    },
    formStatus: {
      required: true,
    },
  },
  setup(props, context) {
    const bidPrjMonthService = new BidPrjMonthService();
    const bidProjectMonthKeyProp: Ref<any> = toRef(props, 'bidProjectMonthKey');
    const formStatusProp: Ref<any> = toRef(props, 'formStatus');

    let formDefault: IBidPrjMonth = new BidPrjMonth();
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
      yearAndMonthDate: { required: required },
      pexp: { required: required, numeric: numeric },
      ptrate: { required: required, numeric: numeric, maxValue: maxValue(100) },
      item: { required: required },
    };
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //接收傳下來的key並判斷是新增還是修改
    watch(
      bidProjectMonthKeyProp,
      newValue => {
        reset();
        if (formStatusProp.value && formStatusProp.value === formStatusEnum.CREATE) {
          Object.assign(formDefault, new BidPrjMonth());
          Object.assign(form, formDefault);
          form.wkut = newValue.wkut;
          form.prjno = newValue.prjno;
          //如果有殘存的id屬性則把它拿掉
          delete form['id'];
        } else if (formStatusProp.value && formStatusProp.value === formStatusEnum.MODIFY) {
          bidPrjMonthService
            .getCertainPrjMonthByProjectKey(newValue.wkut, newValue.prjno, newValue.syr, newValue.month)
            .then(prjMonthData => {
              Object.assign(form, prjMonthData);
              handleStringToProjectMonthDate(form);
              Object.assign(formDefault, prjMonthData);
              handleStringToProjectMonthDate(formDefault);
              reset();
            });
        }
      },
      { immediate: true }
    );

    //回上一頁
    function backToPreviousPage() {
      const bidProjectKey = {
        wkut: form.wkut,
        prjno: form.prjno,
      };
      reset();
      navigateByNameAndParams('bidAab001InfoMenu', {
        bidProjectKey: bidProjectKey,
      });
    }

    function handleProjectMonthDateToString(bidPrjMonth: IBidPrjMonth) {
      bidPrjMonth.syr = (bidPrjMonth.yearAndMonthDate.getFullYear() - 1911).toString();
      bidPrjMonth.yr = bidPrjMonth.syr;
      bidPrjMonth.month = (bidPrjMonth.yearAndMonthDate.getMonth() + 1).toString().padStart(2, '0');
    }

    function handleStringToProjectMonthDate(bidPrjMonth: IBidPrjMonth) {
      bidPrjMonth.yearAndMonthDate = new Date(parseInt(bidPrjMonth.syr) + 1911, parseInt(bidPrjMonth.month) - 1);
    }

    function submitFormData() {
      checkValidity().then(bool => {
        if (bool) {
          if (formStatusProp.value === formStatusEnum.CREATE) {
            handleProjectMonthDateToString(form);
            bidPrjMonthService
              .saveProjectMonth(form)
              .then(() => {
                backToPreviousPage();
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
            bidPrjMonthService
              .partiallyUpdateProjectMonth(form)
              .then(responseEntity => {
                backToPreviousPage();
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
      $v,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      submitFormData,
    };
  },
};
</script>

<style>
.h-38p {
  height: 38px;
}
</style>

<template>
  <div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">變更經費期程詳細資料</h5>
      </div>
      <div class="card-body col-12">
        <!-- 變更日期 -->
        <b-row>
          <i-form-group-check :label="'變更日期：'" :labelStar="true" :item="$v.chgDate">
            <i-date-picker v-model="$v.chgDate.$model" :disabled="formStatusProp === formStatusEnum.MODIFY" lazy></i-date-picker>
          </i-form-group-check>
        </b-row>
        <!-- 程序 -->
        <b-row>
          <i-form-group-check :label="'程序：'" :labelStar="true" :item="$v.mdyPress">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.mdyPress.$model">
                <b-form-radio value="Y">有簽報辦理變更設計</b-form-radio>
                <b-form-radio value="N">單純工期展延</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 核准變更文號 -->
        <b-row>
          <i-form-group-check :label="'核准變更文號：'" :labelStar="true" :item="$v.okNo">
            <b-form-input v-model="$v.okNo.$model" :state="validateState($v.okNo)" lazy trim></b-form-input>
          </i-form-group-check>
        </b-row>
        <!-- 變更原因 -->
        <b-row>
          <i-form-group-check :label="'變更原因：'" :labelStar="true" :item="$v.mdyReson">
            <b-form-select v-model="$v.mdyReson.$model" :options="prjModifyOptions" :state="validateState($v.mdyReson)"></b-form-select>
          </i-form-group-check>
        </b-row>
        <!-- 原因說明 -->
        <b-row>
          <i-form-group-check :label="'原因說明：'" :labelStar="true" :item="$v.mdyRemark" class="col-12" :label-cols="2" :content-cols="6">
            <b-form-textarea
              v-model="$v.mdyRemark.$model"
              placeholder="原因說明..."
              rows="3"
              max-rows="6"
              :state="validateState($v.mdyRemark)"
              lazy
              trim
            ></b-form-textarea>
          </i-form-group-check>
        </b-row>
        <!-- 變更前母案進度 -->
        <b-row>
          <i-form-group-check
            :label="'變更前母案進度：'"
            :labelStar="true"
            :item="$v.chgRate"
            class="col-12"
            :label-cols="2"
            :content-cols="10"
          >
            <div>
              <b-form-input v-model="$v.chgRate.$model" :state="validateState($v.chgRate)" lazy trim></b-form-input>
            </div>
            <div class="d-inline">%(填列本次變更設計申請母案之實際進度)</div>
          </i-form-group-check>
        </b-row>
        <!-- 前次修正後累計增帳金額  前次修正後累計減帳金額-->
        <b-row>
          <i-form-group-check :label="'前次修正後累計增帳金額：'" :item="$v.oldAddAmt">
            <div class="d-inline">
              <b-form-input v-model="$v.oldAddAmt.$model" :item="$v.okNo" :state="validateState($v.oldAddAmt)"></b-form-input>
            </div>
            <div class="d-inline">千元</div>
          </i-form-group-check>
          <i-form-group-check :label="'前次修正後累計減帳金額：'" :item="$v.oldSubtractAmt">
            <div class="d-inline">
              <b-form-input v-model="$v.oldSubtractAmt.$model" :state="validateState($v.oldSubtractAmt)"></b-form-input>
            </div>
            <div class="d-inline">千元</div>
          </i-form-group-check>
        </b-row>
        <!-- 前次修正後總價 -->
        <b-row>
          <i-form-group-check :label="'前次修正後總價：'" :labelStar="true" class="col-12" :label-cols="2" :content-cols="10">
            <div class="d-inline"><b-form-input v-model="$v.oldAmt.$model" :state="validateState($v.oldAmt)"></b-form-input></div>
            <div class="d-inline">如為第一次變更，前次修正總價應該等於 原契約總價</div>
          </i-form-group-check>
        </b-row>
        <!-- 本次增帳金額  本次減帳金額-->
        <b-row>
          <i-form-group-check :label="'本次增帳金額：'" :item="$v.addAmt">
            <div class="d-inline"><b-form-input v-model="$v.addAmt.$model" :state="validateState($v.addAmt)"></b-form-input></div>
            <div class="d-inline">千元</div>
          </i-form-group-check>
          <i-form-group-check :label="'本次減帳金額：'" :item="$v.subtractAmt">
            <div class="d-inline"><b-form-input v-model="$v.subtractAmt.$model" :state="validateState($v.subtractAmt)"></b-form-input></div>
            <div class="d-inline">千元</div>
          </i-form-group-check>
        </b-row>
        <!-- 本次修正後總價 -->
        <!-- newAmt -->
        <b-row>
          <i-form-group-check :label="'本次修正後總價：'" class="col-12" :label-cols="2" :content-cols="10">
            <div class="d-inline"><b-form-input v-model="computedNewAmt" readonly></b-form-input></div>
            <div class="d-inline">本次修正後總價 = 前次修正後總價 + 本次增帳金額 - 本次減帳金額</div>
          </i-form-group-check>
        </b-row>
        <!-- 前次修正後預定完工日 -->
        <b-row>
          <i-form-group-check :label="'前次修正後預定完工日：'" :labelStar="true" :item="$v.oldDate">
            <i-date-picker v-model="$v.oldDate.$model"></i-date-picker>
          </i-form-group-check>
        </b-row>
        <!-- 本次修正後預定完工日 -->
        <b-row>
          <i-form-group-check :label="'本次修正後預定完工日：'" :labelStar="true" :item="$v.newDate">
            <i-date-picker v-model="$v.newDate.$model"></i-date-picker>
          </i-form-group-check>
        </b-row>
        <!-- 展延期程 -->
        <b-row>
          <i-form-group-check :label="'展延期程：'" class="col-12" :label-cols="2" :content-cols="10">
            <div class="d-inline position-relative">
              <div class="pt-2">
                依採購契約要項四十六條
                廠商履約有下列情形之一者，得檢具事證，以書面通知機關，機關得審酌期情形後，嚴懲履約期限，不計預期違約金
              </div>
              <div class="pt-2">
                屬不可抗力至共
                <b-form-input class="col-2 d-inline-block" v-model="$v.dlyd1.$model" :state="validateState($v.dlyd1)"></b-form-input>天(A)
              </div>
              <div class="pt-2">
                屬不可抗力不可歸責於廠商之契約變更或機關通知廠商停工共
                <b-form-input class="col-2 d-inline-block" v-model="$v.dlyd2.$model" :state="validateState($v.dlyd2)"></b-form-input>天(B)
              </div>
              <div class="pt-2">
                機關應提供予廠商之資料、器材、場所或應採行之審查獲同意等配合措施，未依契約規定提供或採行共
                <b-form-input class="col-2 d-inline-block" v-model="$v.dlyd3.$model" :state="validateState($v.dlyd3)"></b-form-input>天(C)
              </div>
              <div class="pt-2">
                可歸責於與機關有契約關係之其他廠商之延遲共
                <b-form-input class="col-2 d-inline-block" v-model="$v.dlyd4.$model" :state="validateState($v.dlyd4)"></b-form-input>天(D)
              </div>
              <div class="pt-2">
                其他可歸責於機關或不可歸責於廠商之事由共
                <b-form-input class="col-2 d-inline-block" v-model="$v.dlyd5.$model" :state="validateState($v.dlyd5)"></b-form-input>天(E)
              </div>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 准延天數 -->
        <b-row>
          <i-form-group-check :label="'准延天數：'">
            <b-form-input class="col-2 d-inline-block" v-model="computedNdays" readonly></b-form-input>天(A+B+C+D+E)(本欄自動計算)
          </i-form-group-check>
        </b-row>
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
import { Ref, ref, toRef, reactive, watch, computed } from '@vue/composition-api';
import { IBidPrjModify, BidPrjModify } from '@/shared/model/bidModel/bid-prj-modify.model';
import BidPrjModifyService from '@/components/bid/bidService/bid-prj-modify.service';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { notificationErrorHandlerWithErrorParam } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';

export default {
  name: 'bidAaa003Edit',
  props: {
    bidProjectModifyKey: {
      required: false,
    },
    formStatus: {
      required: true,
    },
  },
  setup(props, context) {
    const bidPrjModifyService = new BidPrjModifyService();
    const bidProjectModifyKeyProp: Ref<any> = toRef(props, 'bidProjectModifyKey');
    const formStatusProp: Ref<any> = toRef(props, 'formStatus');
    let bidSysCodeTable = new BidSysCodeTable();
    let formDefault: IBidPrjModify = new BidPrjModify();
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
      chgDate: { required: required },
      mdyPress: { required: required },
      okNo: { required: required },
      mdyReson: { required: required },
      mdyRemark: { required: required },
      chgRate: { required: required, numeric: numeric, maxValue: maxValue(100) },
      oldAddAmt: { numeric: numeric },
      oldSubtractAmt: { numeric: numeric },
      oldAmt: { required: required, numeric: numeric },
      addAmt: { numeric: numeric },
      subtractAmt: { numeric: numeric },
      newAmt: { numeric: numeric },
      oldDate: { required: required },
      newDate: { required: required },
      dlyd1: { numeric: numeric },
      dlyd2: { numeric: numeric },
      dlyd3: { numeric: numeric },
      dlyd4: { numeric: numeric },
      dlyd5: { numeric: numeric },
      nDays: {},
    };
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //處理傳進來的日期 從string轉成date
    function handleOriginModifyData(modifyData: BidPrjModify) {
      modifyData.chgDate = new Date(modifyData.chgDate);
      modifyData.oldDate = modifyData.oldDate ? new Date(modifyData.oldDate) : null;
      modifyData.newDate = modifyData.oldDate ? new Date(modifyData.newDate) : null;
    }

    function proceedFrontEndDataToBackEndDTO(modifyData: BidPrjModify) {
      modifyData.nDays = computedNdays.value;
      modifyData.newAmt = computedNewAmt.value;
      //處理日期的offset
      const value = new Date();
      const offset = value.getTimezoneOffset();
      modifyData.chgDate = new Date(modifyData.chgDate.getTime() - offset * 60 * 1000);
      modifyData.oldDate = modifyData.oldDate ? new Date(modifyData.oldDate.getTime() - offset * 60 * 1000) : null;
      modifyData.newDate = modifyData.newDate ? new Date(modifyData.newDate.getTime() - offset * 60 * 1000) : null;
    }

    //處理自動計算的值
    let computedNdays = computed(() => {
      return (
        Number(form.dlyd1 ? form.dlyd1 : 0) +
        Number(form.dlyd2 ? form.dlyd2 : 0) +
        Number(form.dlyd3 ? form.dlyd3 : 0) +
        Number(form.dlyd4 ? form.dlyd4 : 0) +
        Number(form.dlyd5 ? form.dlyd5 : 0)
      );
    });

    let computedNewAmt = computed(() => {
      return (
        Number(form.oldAmt ? form.oldAmt : 0) + Number(form.addAmt ? form.addAmt : 0) - Number(form.subtractAmt ? form.subtractAmt : 0)
      );
    });

    //接收傳下來的key並判斷是新增還是修改
    watch(
      bidProjectModifyKeyProp,
      newValue => {
        reset();
        // console.log('formStatusProp.value', formStatusProp.value);
        if (formStatusProp.value && formStatusProp.value === formStatusEnum.CREATE) {
          Object.assign(formDefault, new BidPrjModify());
          Object.assign(form, formDefault);
          form.wkut = newValue.wkut;
          form.prjno = newValue.prjno;
          //如果有殘存的id屬性則把它拿掉
          delete form['id'];
          //已有一筆以上之經費變更期程資料，「前次修正後預定完工日」：預設「前次標案預定完工日」。
          bidPrjModifyService.findProjectAllModify({ wkut: newValue.wkut, prjno: newValue.prjno }).then(data => {
            if (data.content.length > 0) {
              let lastData = data.content.splice(-1);
              form.oldDate = lastData[0].newDate ? new Date(lastData[0].newDate) : null;
            }
          });
        } else if (formStatusProp.value && formStatusProp.value === formStatusEnum.MODIFY) {
          bidPrjModifyService.getBidProjectModifyByKey(newValue.wkut, newValue.prjno, newValue.chgDate).then(modifyData => {
            handleOriginModifyData(modifyData);
            Object.assign(form, modifyData);
            Object.assign(formDefault, modifyData);
            reset();
          });
        }
      },
      { immediate: true }
    );
    let selected = ref(null);
    let prjModifyOptions = reactive([]);

    //產生下拉式選單
    bidSysCodeTable.getBidSysCodeWithParam('BID_010').then(data => {
      prjModifyOptions.splice(0, prjModifyOptions.length);
      data.forEach(ele => {
        const selectOption = {
          value: '',
          text: '',
        };
        selectOption.value = ele.dataKey;
        selectOption.text = ele.value;
        prjModifyOptions.push(selectOption);
      });
    });
    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAaa003InfoMenu', {
        bidProjectModifyKey: {
          wkut: form.wkut,
          prjno: form.prjno,
        },
      });
    }

    function submitFormData() {
      checkValidity().then(bool => {
        if (bool) {
          if (formStatusProp.value === formStatusEnum.CREATE) {
            proceedFrontEndDataToBackEndDTO(form);
            bidPrjModifyService
              .saveBidProjectModify(form)
              .then(() => {
                navigateByNameAndParams('bidAaa003InfoMenu', {
                  bidProjectModifyKey: {
                    wkut: form.wkut,
                    prjno: form.prjno,
                  },
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
            // console.log(form);
            bidPrjModifyService
              .updateExistingBidPrjModify(form)
              .then(() => {
                navigateByNameAndParams('bidAaa003InfoMenu', {
                  bidProjectModifyKey: {
                    wkut: form.wkut,
                    prjno: form.prjno,
                  },
                });
              })
              .catch(err => {
                if (err.response && err.response.data.errorKey === 'bidValidError') {
                  errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
                  $bvModals.show('valid-error-modal');
                }
              });
          }
        }
      });
    }

    return {
      selected,
      formStatusProp,
      formStatusEnum,
      prjModifyOptions,
      $v,
      computedNdays,
      computedNewAmt,
      submitFormData,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
    };
  },
};
</script>

<style>
.h-38p {
  height: 38px;
}
</style>

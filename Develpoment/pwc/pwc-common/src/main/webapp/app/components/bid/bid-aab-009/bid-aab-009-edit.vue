<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="card-header">
          <h5 class="m-0">使用預算來源資料</h5>
        </div>
        <div>
          <div>
            <b-table :items="table.topData" :fields="table.topFields">
              <template #cell(tableNo)="row"> {{ row.index + 1 }}</template>
              <template #cell(disNo)="row"> {{ row.item.disNo }}</template>
              <template #cell(disName)="row"> {{ row.item.disName }}</template>
              <template #cell(amnta)="row"> {{ row.item.amnta }}</template>
              <template #cell(amntb)="row"> {{ row.item.amntb }}</template>
              <template #cell(amnt)="row"> {{ row.item.amnt }}</template>
            </b-table>
          </div>
        </div>
        <div class="mt-2"></div>
        <div class="card-header">
          <h5 class="m-0">復健請款情形詳細資料</h5>
        </div>
        <div class="mt-2">
          <b-form-row>
            <i-form-group-check
              :label="'請撥年月: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              :item="$v.date"
              v-if="formStatusProp === 'create'"
            >
              <i-date-picker type="month" class="col-4" v-model="$v.date.$model" trim></i-date-picker>
            </i-form-group-check>
            <i-form-group-check
              :label="'請撥年月: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              :item="$v.date"
              v-if="formStatusProp === 'modify'"
            >
              {{ form.year }}/{{ form.month }}
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check
              :label="'本次請撥金額: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              :item="$v.trueMon"
            >
              <b-form-input class="col-4" v-model="$v.trueMon.$model" :state="validateState($v.trueMon)" trim></b-form-input
              ><span class="ml-1">千元</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'應撥總金額: '" :label-cols="2" :content-cols="10" class="col-12" :item="$v.payMonT">
              <!--              <b-form-input class="col-4" v-model="$v.payMonT.$model" :state="validateState($v.payMonT)" trim></b-form-input>-->
              {{ form.payMonT }}
              <span class="ml-1">千元 (院核定補助項目中屬中央補助款用於本案之總額度)</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check
              :label="'截至上次已撥總金額: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :item="$v.payMonU"
              :labelStar="true"
            >
              <b-form-input class="col-4" v-model="$v.payMonU.$model" :state="validateState($v.payMonU)" trim></b-form-input
              ><span class="ml-1">千元</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'本次預訂撥款-日期:'" :label-cols="2" :content-cols="10" class="col-12">
              <!--              <i-form-group-check :label="'日期:'" :label-cols="2" :content-cols="10" class="col-12">-->
              <i-date-picker class="col-4" v-model="$v.tenDate.$model" trim></i-date-picker
              ><span class="ml-1">(指貴單位希望行政院主計總處撥款日期)</span>
              <!--              </i-form-group-check>-->
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'本次預訂撥款-金額:'" :label-cols="2" :content-cols="10" class="col-12" :item="$v.tenMon">
              <b-form-input class="col-4" v-model="$v.tenMon.$model" :state="validateState($v.tenMon)" trim></b-form-input
              ><span class="ml-1">千元</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'本次實際撥款-日期:'" :label-cols="2" :content-cols="10" class="col-12">
              <i-date-picker class="col-4" v-model="$v.payDate.$model" trim></i-date-picker
              ><span class="ml-1">(指行政主計總處實際撥款日期)</span>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'本次實際撥款-金額:'" :label-cols="2" :content-cols="10" class="col-12" :item="$v.payMon">
              <b-form-input class="col-4" v-model="$v.payMon.$model" :state="validateState($v.payMon)" trim></b-form-input
              ><span class="ml-1">千元</span>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'累計已撥總金額: '" :label-cols="2" :content-cols="10" class="col-12" :item="$v.payMonS">
              <b-form-input class="col-4" v-model="$v.payMonS.$model" :state="validateState($v.payMonS)" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'核撥文號: '" :label-cols="2" :content-cols="10" class="col-12">
              <b-form-input class="col-4" v-model="$v.chkNo.$model" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'備註: '" :label-cols="2" :content-cols="10" class="col-12">
              <b-form-textarea class="col-4" v-model="$v.payRmk.$model" trim></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-col style="text-align: center">
            <i-button type="check-circle" @click="submitForm"></i-button>
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
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { inject, Reactive, reactive, ref, Ref, toRef, watch } from '@vue/composition-api';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import { useValidation, validateState } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import BidPrjPay2Service from '@/components/bid/bidService/bid-prj-pay2.service';
import BidPrjSur2Service from '@/components/bid/bidService/bid-prj-sur2.service';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { BidPrjPay2, IBidPrjPay2 } from '@/shared/model/bidModel/bid-prj-pay2.model';
import AccountService from '@/account/account.service';
import { useBvModal } from '@/shared/modal';
import { numeric, required } from '@/shared/validators';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { boolean } from '@storybook/addon-knobs';
import { BidPrjSur2, IBidPrjSur2 } from '@/shared/model/bidModel/bid-prj-sur2';

export default {
  name: 'bidAab009Edit',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
    formStatus: {
      type: String,
      required: true,
    },
  },

  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const formStatusProp: Ref<any> = toRef(props, 'formStatus');
    const bidProjectService = new BidProjectService();
    const bidPrjPay2Service = new BidPrjPay2Service();
    const bidPrjSur2Service = new BidPrjSur2Service();
    const notificationService: NotificationService = useNotification();
    // 取得登錄資料
    const accountService = inject<() => AccountService>('accountService')();

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        // console.log('fetchBidProjectInfo res ', result);
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      disNo: {},
      disName: {},
      amnta: {},
      amntb: {},
      amnt: {},
      year: {},
      month: {},
      date: { required: required },
      trueMon: { numeric: numeric, required: required },
      payMonT: { numeric: numeric },
      payMonU: { numeric: numeric, required: required },
      tenDate: {},
      tenMon: { numeric: numeric },
      payDate: {},
      payMon: { numeric: numeric },
      payMonS: { numeric: numeric },
      chkNo: {},
      payRmk: {},
    };
    const table = reactive({
      topFields: [
        {
          key: 'tableNo',
          label: '序號',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'disNo',
          label: '核定編號',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'disName',
          label: '預算科目或摘要',
          sortable: false,
          thStyle: 'width:30%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'amnta',
          label: '使用地方配合款(千元)',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'amntb',
          label: '中央補助款(千元)',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'amnt',
          label: '合計預算款(千元)',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      topData: undefined,
      topTotalItems: 0,
    });

    // 使用預算來源資料
    function getPrjSur2DatafromAxios(queryCriteria) {
      bidPrjSur2Service
        .getBidPrjSur2ByCriteria(queryCriteria)
        .then(res => {
          // 應撥總金額 payMonT = amntb 使用中央補助款數(千元)
          let monT = 0;
          res.content.forEach(item => (monT += item.amntb));
          form.payMonT = monT;

          table.topData = res.content.slice(0, res.content.length); // 前端分頁
          table.topTotalItems = res.content.length; // 前端分頁
        })
        .catch(notificationErrorHandler(notificationService));
      //checkVailidaty 沒過
    }
    // // 復健請款情形資料
    // function getPrjPay2DatafromAxios(queryCriteria) {
    //   bidPrjPay2Service
    //     .getBidPrjPay2ByCriteria(queryCriteria)
    //     .then(res => {})
    //     .catch(notificationErrorHandler(notificationService));
    //   //checkVailidaty 沒過
    // }

    const handleQuery = () => {
      // getPrjPay2DatafromAxios(bidProjectKeyProp.value);
      getPrjSur2DatafromAxios(bidProjectKeyProp.value);
    };

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (formStatusProp.value === 'create') {
            form.createUser = accountService.user.id;
            form.updateUser = accountService.user.id;
            form.wkut = bidProjectKeyProp.value.wkut;
            form.prjno = bidProjectKeyProp.value.prjno;

            form.createDate = new Date();
            form.updateDate = new Date();

            // 時間轉換，YEAR和MONTH要當字串存
            const date = formatToString(form.date);
            form.year = Number(date.substring(0, 3));
            form.month = date.substring(3, 5);

            bidPrjPay2Service
              .saveBidPrjPay2(form)
              .then(() => {
                navigateByNameAndParams('bidAab009Info', {
                  bidProjectKey: {
                    wkut: bidProjectKeyProp.value.wkut,
                    prjno: bidProjectKeyProp.value.prjno,
                  },
                });
              })
              .catch(err => {
                if (err.response && err.response.data.errorKey === 'bidValidError') {
                  errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
                  $bvModals.show('valid-error-modal');
                }
              });
          } else if (formStatusProp.value === 'modify') {
            form.updateUser = accountService.user.id;
            form.updateDate = new Date();

            bidPrjPay2Service
              .updateExistingPay2(form)
              .then(() => {
                navigateByNameAndParams('bidAab009Info', {
                  bidProjectKey: {
                    wkut: bidProjectKeyProp.value.wkut,
                    prjno: bidProjectKeyProp.value.prjno,
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
        } else {
          notificationService.danger('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const toCancel = () => {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAab009Info', { bidProjectKey });
    };
    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault: IBidPrjPay2 = new BidPrjPay2();
    let Sur2formDefault: IBidPrjSur2 = new BidPrjSur2();

    // 建立表單物件 ref
    let form: Reactive<IBidPrjPay2> = reactive(Object.assign({}, formDefault));
    let sur2form: Reactive<IBidPrjPay2> = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          handleQuery();

          if (formStatusProp.value === 'create') {
            Object.assign(formDefault, new BidPrjPay2());
            Object.assign(form, formDefault);
            reset();
          } else if (formStatusProp.value === 'modify') {
            bidPrjPay2Service.getOneBidPrjPay2(newValue).then(bidPrjPay2Date => {
              const dateStr = bidPrjPay2Date.data.year + '-' + bidPrjPay2Date.data.month + '-' + '01';
              const date = parseToDate(dateStr, '-');
              bidPrjPay2Date.data.date = date;
              bidPrjPay2Date.data.tenDate = new Date(bidPrjPay2Date.data.tenDate);
              bidPrjPay2Date.data.payDate = new Date(bidPrjPay2Date.data.payDate);

              Object.assign(form, bidPrjPay2Date.data);
              Object.assign(formDefault, bidPrjPay2Date.data);
              reset();
            });
          }
        }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      $v,
      reset,
      table,
      bidProjectKeyProp,
      formStatusProp,
      form,
      handleQuery,
      submitForm,
      toCancel,
      $bvModals,
      validateState,
      Sur2formDefault,
      sur2form,
      errorMessages,
    };
  },
};
</script>

<style scoped></style>

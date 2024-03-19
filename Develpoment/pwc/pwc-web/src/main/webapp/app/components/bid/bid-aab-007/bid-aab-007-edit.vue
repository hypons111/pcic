<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="mt-2"></div>
        <div class="card-header">
          <h5 class="m-0">開口合約派工詳細資料</h5>
        </div>
        <div class="mt-2">
          <b-form-row>
            <i-form-group-check :label="'通知編號: '" :label-cols="2" :content-cols="10" class="col-12" v-if="formStatusProp === 'modify'">
              {{ form.dspNo }}
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check
              :label="'通知日期: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              :item="$v.dspSdat"
            >
              <i-date-picker class="col-4" v-model="$v.dspSdat.$model" :state="validateState($v.dspSdat)"></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'承覽廠商:'" :label-cols="2" :content-cols="10" class="col-12">
              <b-form-row class="col-10 mt-2">{{ codeToName.ccut }}</b-form-row>
              <b-form-row class="mt-2">
                電話: &nbsp;<b-form-input class="col-4 mr-2" v-model="$v.ccut1Tel.$model"></b-form-input> 傳真: &nbsp;<b-form-input
                  class="col-4"
                  v-model="$v.ccut1Fax.$model"
                ></b-form-input
              ></b-form-row>
              <b-form-row class="mt-2">
                工地負責人: &nbsp;<b-form-input class="col-4 mr-2" v-model="$v.ccut1er.$model"></b-form-input> 手機: &nbsp;<b-form-input
                  class="col-4"
                  v-model="$v.ccut1erTel.$model"
                ></b-form-input
              ></b-form-row>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'監造單位: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
              <b-form-row class="col-10 mt-2">{{ codeToName.scut }}</b-form-row>
              <b-form-row class="mt-2">
                電話: &nbsp;<b-form-input class="col-4 mr-2" v-model="$v.scutTel.$model" :state="validateState($v.scutTel)"></b-form-input>
                傳真: &nbsp;<b-form-input class="col-4" v-model="$v.scutFax.$model" :state="validateState($v.scutFax)"></b-form-input
              ></b-form-row>
              <b-form-row class="mt-2">
                工地負責人: &nbsp;<b-form-input
                  class="col-4 mr-2"
                  v-model="$v.scuter.$model"
                  :state="validateState($v.scuter)"
                ></b-form-input>
                手機: &nbsp;<b-form-input class="col-4" v-model="$v.scuterTel.$model" :state="validateState($v.scuterTel)"></b-form-input
              ></b-form-row>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'速別: '" :label-cols="2" :content-cols="10" class="col-12" :label-star="true" :item="$v.dspSpeed">
              <b-form-radio-group v-model="$v.dspSpeed.$model" :state="validateState($v.dspSpeed)">
                <b-form-radio value="A">最速件</b-form-radio>
                <b-form-radio value="B">請速辦理</b-form-radio>
                <b-form-radio value="C">請於期限內辦理</b-form-radio>
                <b-form-radio value="D">請檢閱 </b-form-radio>
                <b-form-radio value="E">請配合辦理</b-form-radio>
              </b-form-radio-group>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check
              :label="'施工通知事項: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :label-star="true"
              :item="$v.dspItem"
            >
              <div class="col-10">請註明本次通知施作地點、範圍、項目及內容</div>
              <b-form-textarea class="col-6" v-model="$v.dspItem.$model" :state="validateState($v.dspItem)"></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check
              :label="'概估金額: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :label-star="true"
              :item="$v.dspPmon"
            >
              <b-form-input class="col-4" v-model="$v.dspPmon.$model" :state="validateState($v.dspPmon)"></b-form-input>千元
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check
              :label="'預定完成日期: '"
              :labelStar="true"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :item="$v.dspPedat"
            >
              <i-date-picker class="col-4" v-model="$v.dspPedat.$model" :state="validateState($v.dspPedat)"></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'實際完成日期: '" :label-cols="2" :content-cols="10" class="col-12">
              <i-date-picker class="col-4" v-model="$v.dspAedat.$model"></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'實際估驗日期: '" :label-cols="2" :content-cols="10" class="col-12">
              <i-date-picker class="col-4" v-model="$v.dspF1dat.$model"></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'實際計價金額: '" :label-cols="2" :content-cols="10" class="col-12">
              <b-form-input class="col-4" v-model="$v.dspAmon.$model"></b-form-input>千元
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'實際付款日期: '" :label-cols="2" :content-cols="10" class="col-12">
              <i-date-picker class="col-4" v-model="$v.dspF2dat.$model"></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'承辦人: '" :label-cols="2" :content-cols="10" class="col-12">
              <b-form-row class="col-10 mt-2">eee</b-form-row>
              <b-form-row class="col-6 mt-2"
                >電話:&nbsp;<b-form-input class="col-4 mr-2" v-model="$v.sendtel.$model"></b-form-input> 傳真: &nbsp;<b-form-input
                  class="col-4"
                  v-model="$v.sendfax.$model"
                ></b-form-input
              ></b-form-row>
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
import { useValidation, validateState } from '@/shared/form/validations';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import AccountService from '@/account/account.service';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import BidDsptchService from '@/components/bid/bidService/bid-dsptch.service';
import BidProjectUnitService from '@/components/bid/bidService/bid-project-unit.service';
import BidUnit10Service from '@/components/bid/bidService/bid-unit10.service';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useGetters } from '@u3u/vue-hooks';
import { IBidDsptch, BidDsptch } from '@/shared/model/bidModel/bid-dsptch.model';
import { numeric, required } from '@/shared/validators';

export default {
  name: 'bidAab007Edit',
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

  setup(props) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const formStatusProp: Ref<any> = toRef(props, 'formStatus');
    const bidProjectService = new BidProjectService();
    const bidDsptchService = new BidDsptchService();
    const bidProjectUnitService = new BidProjectUnitService();
    const bidUnit10Service = new BidUnit10Service();
    const bidSysCodeTable = new BidSysCodeTable();
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
    // sum 用於validation
    const sum = ref({ ctSum: undefined, ctSumF: undefined });
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        // console.log('fetchBidProjectInfo res ', result);
        sum.value.ctSum = result.ctSum;
        sum.value.ctSumF = result.ctSumF;
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault: IBidDsptch = new BidDsptch();

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      dspNo: {},
      dspSdat: { notNull: required },
      ccut1Tel: {},
      ccut1Fax: {},
      ccut1er: {},
      ccut1erTel: {},
      scutTel: { notNull: required },
      scutFax: { notNull: required },
      scuter: { notNull: required },
      scuterTel: { notNull: required },
      dspSpeed: { notNull: required },
      dspItem: { notNull: required },
      dspPmon: { numeric: numeric, notNull: required },
      dspPedat: { notNull: required },
      dspAedat: {},
      dspF1dat: {},
      dspAmon: { numeric: numeric },
      dspF2dat: {},
      sender: {},
      sendtel: {},
      sendfax: {},
      createUser: {},
      createDate: {},
      updateDate: {},
      updateUser: {},
    };
    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (formStatusProp.value === 'create') {
            form.wkut = bidProjectKeyProp.value.wkut;
            form.prjno = bidProjectKeyProp.value.prjno;
            form.ctSum = sum.value.ctSum;
            form.ctSumF = sum.value.ctSumF;
            form.sender = userIdentity;
            form.createUser = userIdentity;
            form.createDate = new Date();

            bidDsptchService
              .saveBidPrjFals(form)
              .then(() => {
                navigateByNameAndParams('bidAab007Info', {
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
            form.wkut = bidProjectKeyProp.value.wkut;
            form.prjno = bidProjectKeyProp.value.prjno;
            form.updateUser = userIdentity;
            form.updateDate = new Date();

            bidDsptchService
              .updateExistingPrjFals(form)
              .then(() => {
                navigateByNameAndParams('bidAab007Info', {
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

    // 取消
    const toCancel = () => {
      reset();
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAab007Info', { bidProjectKey });
    };

    const codeToName = ref({ ccut: '', scut: '' });
    // 找承攬廠商及監造單位
    function handleCcutName(wkut: string, prjno: string, type: string) {
      bidProjectUnitService
        .findUnitsByWkutAndPrjnoAndType(wkut, prjno, type)
        .then(res => {
          if (res != null && res.length > 0) {
            console.log('@@ccut res :', res);
            bidUnit10Service
              .findUnitNameByCode(res.ccut)
              .then(res => {
                codeToName.value.ccut = res.name;
              })
              .catch(notificationErrorHandler(notificationService));
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function handleScutName(wkut: string, prjno: string, type: string) {
      bidProjectUnitService
        .findUnitsByWkutAndPrjnoAndType(wkut, prjno, type)
        .then(res => {
          if (res != null && res.length > 0) {
            bidUnit10Service
              .findUnitNameByCode(res.scut)
              .then(res => {
                codeToName.value.scut = res.name;
              })
              .catch(notificationErrorHandler(notificationService));
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 建立表單物件 ref
    let form: Reactive<IBidDsptch> = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);

          if (formStatusProp.value === 'create') {
            handleCcutName(newValue.wkut, newValue.prjnoc, '3');
            handleScutName(newValue.wkut, newValue.prjnoc, '2');
            Object.assign(formDefault, new BidDsptch());
            Object.assign(form, formDefault);
            reset();
          } else if (formStatusProp.value === 'modify') {
            bidDsptchService.getOneBidPrjFals(newValue).then(bidDsptchDate => {
              handleCcutName(newValue.wkut, newValue.prjnoc, '3');
              handleScutName(newValue.wkut, newValue.prjnoc, '2');
              Object.assign(form, bidDsptchDate.data);
              Object.assign(formDefault, bidDsptchDate.data);
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
      bidProjectKeyProp,
      formStatusProp,
      form,
      submitForm,
      toCancel,
      errorMessages,
      $bvModals,
      formatDate,
      userIdentity,
      handleCcutName,
      handleScutName,
      codeToName,
      sum,
      validateState,
    };
  },
  filters: {},
};
</script>

<style scoped></style>

<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-header">
        <h5 class="m-0">復健院核定項目詳細資料</h5>
      </div>
      <div class="card-body">
        <i-form-group-check
          :label="'核定編號:'"
          :label-cols="2"
          :content-cols="10"
          class="col-12"
          v-if="formStatusForwardProp === 'modify'"
        >
          {{ form.prjno }}
        </i-form-group-check>
        <i-form-group-check
          :label="'核定預算項目:'"
          :label-cols="2"
          :content-cols="10"
          class="col-12"
          v-if="formStatusForwardProp === 'modify'"
          >{{ form.disName }}</i-form-group-check
        >
        <i-form-group-check :label="'核定經費:'" :label-cols="2" :content-cols="10" class="col-12" v-if="formStatusForwardProp === 'modify'"
          >{{ form.veri3Budg }} 千元</i-form-group-check
        >
        <i-form-group-check
          :label="'使用地方災害準備金數:'"
          :label-cols="2"
          :content-cols="10"
          class="col-12"
          :label-star="true"
          v-if="formStatusForwardProp === 'modify'"
          :item="$v.amnta"
        >
          <b-form-input class="col-4" v-model="$v.amnta.$model" :state="validateState($v.amnta)"></b-form-input><span> 千元</span>
          <span>(填列本標案分配之額度屬地方災害準備金部分，金額大小可洽貴機關會計單位或財主單位)</span>
        </i-form-group-check>
        <i-form-group-check
          :label="'使用中央輔助款數:'"
          :label-cols="2"
          :content-cols="10"
          class="col-12"
          :label-star="true"
          v-if="formStatusForwardProp === 'modify'"
          :item="$v.amntb"
        >
          <b-form-input class="col-4" v-model="$v.amntb.$model" :state="validateState($v.amntb)"></b-form-input><span> 千元</span>
          <span>(填列本標案分配之額度屬中央補助款部分，金額大小可洽貴機關會計單位或財主單位)</span>
        </i-form-group-check>
        <i-form-group-check
          :label="'本標案使用預算數:'"
          :label-cols="2"
          :content-cols="10"
          class="col-12"
          v-if="formStatusForwardProp === 'modify'"
        >
          {{ form.amnt }} 千元
        </i-form-group-check>
        <i-form-group-check
          :label="'核定預算項目:'"
          :label-cols="2"
          :content-cols="10"
          class="col-12"
          v-if="formStatusForwardProp === 'create'"
        >
          <b-form-select class="col-4" v-model="$v.disNo.$model" :options="disNoOptions">
            <template #first>
              <option value="undefined">-- 請選擇 --</option>
            </template>
          </b-form-select>
        </i-form-group-check>
        <i-form-group-check
          :label="'使用地方災害準備金數:'"
          :label-cols="2"
          :content-cols="10"
          class="col-12"
          :label-star="true"
          v-if="formStatusForwardProp === 'create'"
          :item="$v.amnta"
        >
          <b-form-input class="col-4" v-model="$v.amnta.$model" :state="validateState($v.amnta)"></b-form-input><span> 千元</span>
          <span>(填列本標案分配之額度屬地方災害準備金部分，金額大小可洽貴機關會計單位或財主單位)</span>
        </i-form-group-check>
        <i-form-group-check
          :label="'使用中央輔助款數:'"
          :label-cols="2"
          :content-cols="10"
          class="col-12"
          :label-star="true"
          v-if="formStatusForwardProp === 'create'"
          :item="$v.amntb"
        >
          <b-form-input class="col-4" v-model="$v.amntb.$model" :state="validateState($v.amntb)"></b-form-input><span> 千元</span>
          <span>(填列本標案分配之額度屬中央補助款部分，金額大小可洽貴機關會計單位或財主單位)</span>
        </i-form-group-check>
        <i-form-group-check
          :label="'本標案使用預算數:'"
          :label-cols="2"
          :content-cols="10"
          class="col-12"
          v-if="formStatusForwardProp === 'create'"
        >
          <span>(本欄免填，由上兩欄加總)</span>
        </i-form-group-check>

        <div>
          <b-col style="text-align: center">
            <i-button type="check-circle" @click="submitForm('create')" v-if="formStatusForwardProp === 'create'"></i-button>
            <i-button type="check-circle" @click="submitForm('modify')" v-if="formStatusForwardProp === 'modify'"></i-button>
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
import { inject, Reactive, reactive, Ref, ref, toRef, toRefs, watch } from '@vue/composition-api';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useValidation, validateState } from '@/shared/form';
import axios from 'axios';
import { BidPrjSur2, IBidPrjSur2 } from '@/shared/model/bidModel/bid-prj-sur2';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';
import { decimal, required } from '@/shared/validators';
import BidPrjSur2Service from '@/components/bid/bidService/bid-prj-sur2.service';
import BidMdlbasService from '@/components/bid/bidService/bid-mdlbas.service';
import AccountService from '@/account/account.service';
export default {
  name: 'bidAab008EditInfo',
  components: {
    bidProjectInfo,
  },
  props: {
    formStatusForward: {
      type: String,
      required: false,
    },
    bidPrjSur2Key: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    // 基本資料
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();
    const bidPrjSur2Service = new BidPrjSur2Service();
    const bidMdlbasService = new BidMdlbasService();
    // 傳入PK
    const bidPrjSur2KeyProp: Ref<any> = toRef(props, 'bidPrjSur2Key');
    //傳入狀態
    const formStatusForwardProp = toRefs(props).formStatusForward;

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

    const notificationService: NotificationService = useNotification();

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

    let formDefault: IBidPrjSur2 = new BidPrjSur2();

    let rules = {
      wkut: {}, // 執行機關代碼
      prjno: {}, // 標案編號
      disNo: {}, // 核定預算項目
      amnta: { notNull: required, decimal: decimal }, // 使用地方災害準備金數
      amntb: { notNull: required, decimal: decimal }, // 使用中央補助款數
      amnt: {}, // 建立日期
      createDate: {}, // 建立人員
      createUser: {}, // 建立日期
      updateDate: {}, // 更新日期
      updateUser: {}, // 更新人員
      dsts: {}, // 資料狀況
      confOut: {}, // 預定執行單位資料確認
      uIp: {}, // 更新IP
      disName: {}, // 災害工程項目及內容名稱
      veri3Budg: {}, // 審查小組審議金額
    };

    // 建立表單物件 ref
    let form: Reactive<IBidPrjSur2> = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const disNoOptions = reactive([]);
    function handelDisNoChange(criteria) {
      bidMdlbasService.getDisNoByCriteria(criteria).then(res => {
        if (res != null && res.length > 0) {
          // 清空處理
          disNoOptions.splice(0, disNoOptions.length);
          res.forEach(ele => {
            const disNoOption = { value: '', text: '' };
            disNoOption.value = ele.disNo;
            disNoOption.text = ele.disNo + ele.disName + ele.veri3Budg;
            disNoOptions.push(disNoOption);
          });
        }
      });
    }
    // 查詢結果及狀態
    const dataPromise = ref(null); //查詢結果

    const handleQuery = () => {
      form.wkut = bidPrjSur2KeyProp.value.wkut;
      form.prjno = bidPrjSur2KeyProp.value.prjno;
      dataPromise.value = axios
        .post('/bid-prj-sur-2-s/criteria', form)
        .then(({ data }) => {})
        .catch(notificationErrorHandler(notificationService))
        .finally(() => (dataPromise.value = null));
    };

    // 取得登錄資料
    const accountService = inject<() => AccountService>('accountService')();

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const submitForm = (submitMethod: string) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (submitMethod === 'modify') {
            //提交修改
            form.updateUser = accountService.user.id;
            form.updateDate = new Date();

            // form.uIp = accountService.user.;

            axios
              .put('/bid-prj-sur-2-s/updateOne', form)
              .then(() => {
                let bidProjectKey = {
                  wkut: bidPrjSur2KeyProp.value.wkut,
                  prjno: bidPrjSur2KeyProp.value.prjno,
                };
                navigateByNameAndParams('bidAab008Info', { bidProjectKey });
              })
              .finally()
              .catch(err => {
                if (err.response && err.response.data.errorKey === 'bidValidError') {
                  // console.log(err.response.data.detailMessages);
                  errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
                  $bvModals.show('valid-error-modal');
                }
              });
          }
          if (submitMethod === 'create') {
            //提交新增
            form.createUser = accountService.user.id;
            form.updateUser = accountService.user.id;
            form.wkut = bidPrjSur2KeyProp.value.wkut;
            form.prjno = bidPrjSur2KeyProp.value.prjno;
            form.createDate = new Date();
            form.updateDate = new Date();

            axios
              .post(`/bid-prj-sur-2-s`, form)
              .then(() => {
                let bidProjectKey = {
                  wkut: bidPrjSur2KeyProp.value.wkut,
                  prjno: bidPrjSur2KeyProp.value.prjno,
                };
                navigateByNameAndParams('bidAab008Info', { bidProjectKey });
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

    watch(
      bidPrjSur2KeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);

          if (formStatusForwardProp.value === 'modify') {
            bidPrjSur2Service.getOneBidPrjSur2(newValue).then(bidPrjSur2Date => {
              Object.assign(form, bidPrjSur2Date.data);
              Object.assign(formDefault, bidPrjSur2Date.data);
              reset();
            });
          } else if (formStatusForwardProp.value === 'create') {
            handleQuery();

            let criteria = {
              wkut: newValue.wkut,
              prjno: newValue.prjno,
            };
            handelDisNoChange(criteria);

            Object.assign(formDefault, new BidPrjSur2());
            Object.assign(form, formDefault);
            reset();
          }
        }
      },
      { immediate: true }
    );

    // 取消
    const toCancel = () => {
      let bidProjectKey = {
        wkut: bidPrjSur2KeyProp.value.wkut,
        prjno: bidPrjSur2KeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAab008Info', { bidProjectKey });
    };

    return {
      validateState,
      formStatusForwardProp,
      bidPrjSur2KeyProp,
      projectInfo,
      form,
      $v,
      reset,
      disNoOptions,
      handleQuery,
      errorMessages,
      $bvModals,
      submitForm,
      toCancel,
      fetchBidProjectInfo,
      handelDisNoChange,
    };
  },
};
</script>

<style scoped></style>

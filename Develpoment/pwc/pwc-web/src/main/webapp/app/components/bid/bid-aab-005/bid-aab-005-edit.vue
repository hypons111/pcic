<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="mt-2"></div>
        <div class="card-header">
          <h5 class="m-0">未開工原因資料</h5>
        </div>
        <div class="mt-2">
          <b-form-row>
            <i-form-group-check :label="'資料月份: '" :label-cols="2" :content-cols="10" class="col-12" v-if="formStatusProp === 'create'">
              <i-date-picker type="month" class="col-4" v-model="$v.falsDate.$model" placeholder="yyy/MM"></i-date-picker>
            </i-form-group-check>

            <i-form-group-check :label="'資料月份: '" :label-cols="2" :content-cols="10" class="col-12" v-if="formStatusProp === 'modify'">
              {{ form.year }}年{{ form.month }}月
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'未開工原因: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
              <b-form-select class="col-4" v-model="$v.mft.$model" :options="bidFalsOpts">
                <template #first>
                  <option value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'未開工原因說明: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
              <b-form-textarea class="col-6" v-model="$v.mfas.$model"></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'解決辦法: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
              <b-form-textarea class="col-6" v-model="$v.mfrt.$model"></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'待協調事項及涉及機關名稱: '" :label-cols="2" :content-cols="10" class="col-12">
              <b-form-textarea class="col-6" v-model="$v.mfru.$model"></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'實際決標日期: '" :label-cols="2" :content-cols="10" class="col-12">
              {{ formatDate(projectDate.actDate ? new Date(projectDate.actDate) : null, '/') }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'目前預定開工日: '" :label-cols="2" :content-cols="10" class="col-12">
              {{ formatDate(projectDate.csDate ? new Date(projectDate.csDate) : null, '/') }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check
              :label="'修正後開工日: '"
              :labelStar="true"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              v-if="formStatusProp === 'create'"
            >
              <i-date-picker v-model="$v.newDate.$model" class="col-4" v-if="projectDate.asDate == undefined"></i-date-picker>
              <span v-if="projectDate.asDate != undefined"
                >本案已於{{
                  formatDate(projectDate.asDate ? new Date(projectDate.asDate) : null, '/')
                }}實際開工，不開放修正預定開工日期</span
              >
            </i-form-group-check>
            <i-form-group-check
              :label="'修正後開工日: '"
              :labelStar="true"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              v-if="formStatusProp === 'modify'"
            >
              <i-date-picker v-model="$v.newDate.$model" class="col-4" v-if="projectDate.asDate == undefined"></i-date-picker>
              <span v-if="projectDate.asDate != undefined"
                >本案已於{{
                  formatDate(projectDate.asDate ? new Date(projectDate.asDate) : null, '/')
                }}實際開工，不開放修正預定開工日期</span
              >
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
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import { required } from '@/shared/validators';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import AccountService from '@/account/account.service';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import BidPrjFalsService from '@/components/bid/bidService/bid-prj-fals.service';
import { IBidPrjFals, BidPrjFals } from '@/shared/model/bidModel/bid-prj-fals.model';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useGetters } from '@u3u/vue-hooks';

export default {
  name: 'bidAab005Edit',
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
    const bidPrjFalsSerice = new BidPrjFalsService();
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
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        // console.log('fetchBidProjectInfo res ', result);
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault: IBidPrjFals = new BidPrjFals();

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      year: {},
      month: {},
      falsDate: {},
      syr: {},
      mft: {},
      mfas: {},
      mfrt: {},
      mfru: {},
      oldDate: {},
      newDate: {},
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
            form.oldDate = projectDate.value.csDate;
            form.createUser = userIdentity;
            form.createDate = new Date();

            // 時間轉換，YEAR和MONTH要當字串存
            const date = formatToString(form.falsDate);
            form.syr = date.substring(0, 3);
            form.year = date.substring(0, 3);
            form.month = date.substring(3, 5);

            bidPrjFalsSerice
              .saveBidPrjFals(form)
              .then(() => {
                navigateByNameAndParams('bidAab005Info', {
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
            form.oldDate = projectDate.value.csDate;

            bidPrjFalsSerice
              .updateExistingPrjFals(form)
              .then(() => {
                navigateByNameAndParams('bidAab005Info', {
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
      navigateByNameAndParams('bidAab005Info', { bidProjectKey });
    };

    const projectDate = ref({ actDate: undefined, csDate: undefined, asDate: undefined });
    // 找實際決標日期/目前預定開工日
    function handleProjectDate(wkut: string, prjno: string) {
      projectDate.value.actDate = undefined;
      projectDate.value.csDate = undefined;
      projectDate.value.asDate = undefined;

      bidProjectService
        .findBidProjectByWkutAndPrjno(wkut, prjno)
        .then(res => {
          if (res != null) {
            projectDate.value.actDate = res.actDate;
            projectDate.value.csDate = res.csDate;
            projectDate.value.asDate = res.asDate;
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 下拉選單:未開工原因
    //去後端撈選項
    const bidFalsOpts = reactive([]);
    bidSysCodeTable.getBidSysCodeWithParam('BID_016').then(data => {
      bidFalsOpts.splice(0, bidFalsOpts.length);
      data.forEach(ele => {
        const selectOption = {
          value: '',
          text: '',
        };
        selectOption.value = ele.dataKey;
        selectOption.text = ele.value;
        bidFalsOpts.push(selectOption);
      });
    });
    // 建立表單物件 ref
    let form: Reactive<IBidPrjFals> = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);

          if (formStatusProp.value === 'create') {
            handleProjectDate(newValue.wkut, newValue.prjno);
            Object.assign(formDefault, new BidPrjFals());
            Object.assign(form, formDefault);
            reset();
          } else if (formStatusProp.value === 'modify') {
            bidPrjFalsSerice.getOneBidPrjFals(newValue).then(bidPrjFalsDate => {
              handleProjectDate(newValue.wkut, newValue.prjno);
              Object.assign(form, bidPrjFalsDate.data);
              Object.assign(formDefault, bidPrjFalsDate.data);
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
      projectDate,
      handleProjectDate,
      formatDate,
      bidFalsOpts,
      userIdentity,
    };
  },
  filters: {},
};
</script>

<style scoped></style>

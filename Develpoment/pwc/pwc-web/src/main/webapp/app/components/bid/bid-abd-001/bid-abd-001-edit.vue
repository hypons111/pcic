<template>
  <div>
    <section class="container">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">主管機關進度查證資料</h5>
          {{ formStatusRef }}
        </div>
        <div class="card-body">
          <div class="card-body">
            <!-- 進度月份 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'進度月份'">
                <b-form-row>
                  <span>{{ $v.yearAndMonthDate.$model | dateToString }}</span>
                  <P class="col-8" style="color: blue" v-if="formStatusRef === FormStatusEnum.CREATE"
                    >(本月份進度查證填報時間限於次一月12日起置次二月11日止)</P
                  >
                  <!-- <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.agreeDate.$model | dateToString }}</span> -->
                </b-form-row>
              </i-form-group-check>
            </b-form-row>
            <!-- 查證單位 -->
            <b-form-row>
              <i-form-group-check
                v-if="formStatusRef === FormStatusEnum.MODIFY"
                class="col-sm-6"
                label-cols-md="4"
                content-cols-md="3"
                :label="'查證單位'"
              >
                <!-- <b-form-input v-if="formStatusRef === FormStatusEnum.MODIFY" v-model="$v.prmNo.$model" trim></b-form-input> -->
                <span>{{ getName }}</span>
              </i-form-group-check>
            </b-form-row>
            <!-- 佐證資料 -->
            <b-form-row>
              <i-form-group-check class="col-sm-6" label-cols-md="4" content-cols-md="3" :label="'佐證資料'">
                <b-form-select
                  v-if="formStatusRef === FormStatusEnum.CREATE"
                  v-model="$v.mft.$model"
                  :options="queryOptions.bid055"
                ></b-form-select>
                <span v-if="formStatusRef === FormStatusEnum.MODIFY || formStatusRef === FormStatusEnum.DELETE">{{
                  formOptionsFormatter(queryOptions.bid055, $v.mft.$model)
                }}</span>
              </i-form-group-check>
              <i-form-group-check class="col-sm-6" label-cols-md="4" content-cols-md="3" label="佐證資料自行管理編號" :item="$v.mftno">
                <b-form-input
                  v-if="formStatusRef === FormStatusEnum.MODIFY || formStatusRef === FormStatusEnum.CREATE"
                  v-model="$v.mftno.$model"
                  :state="validateState($v.mftno)"
                ></b-form-input>
                <span v-if="formStatusRef === FormStatusEnum.DELETE">{{ $v.mftno.$model }}</span></i-form-group-check
              >
            </b-form-row>
            <!-- 查證時系統資料預定進度 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="10"
                :label="'查證時系統資料預定進度'"
                :item="$v.ptrate"
              >
                <div class="d-inline">
                  <!-- <b-form-input class="col-12" v-model="$v.ptrate.$model" :state="validateState($v.ptrate)"></b-form-input> -->
                  <span>{{ $v.ptrate.$model }}</span>
                </div>
                <div class="col-8">
                  <div class="d-inline">%</div>
                </div>
              </i-form-group-check>
            </b-form-row>
            <!-- 查證時佐證資料預定進度 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="10"
                :label="'查證時佐證資料預定進度'"
                :item="$v.ptrate1"
              >
                <div class="d-inline">
                  <b-form-input
                    v-if="formStatusRef === FormStatusEnum.MODIFY || formStatusRef === FormStatusEnum.CREATE"
                    class="col-12"
                    v-model="$v.ptrate1.$model"
                    :state="validateState($v.ptrate1)"
                  ></b-form-input>
                  <span v-if="formStatusRef === FormStatusEnum.DELETE">{{ $v.ptrate1.$model }}</span>
                </div>
                <div class="col-8">
                  <div class="d-inline">%</div>
                </div>
              </i-form-group-check>
            </b-form-row>
            <!-- 查證時系統資料實際進度-->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="10"
                :label="'查證時系統資料實際進度'"
                :item="$v.atrate"
              >
                <div class="d-inline">
                  <!-- <b-form-input class="col-12" v-model="$v.atrate.$model" :state="validateState($v.atrate)"></b-form-input> -->
                  <span>{{ $v.atrate.$model }}</span>
                </div>
                <div class="col-8">
                  <div class="d-inline">%</div>
                </div>
              </i-form-group-check>
            </b-form-row>
            <!-- 查證時佐證資料實際進度-->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="10"
                :label="'查證時佐證資料實際進度'"
                :item="$v.atrate1"
              >
                <div class="d-inline">
                  <b-form-input
                    v-if="formStatusRef === FormStatusEnum.MODIFY || formStatusRef === FormStatusEnum.CREATE"
                    class="col-12"
                    v-model="$v.atrate1.$model"
                    :state="validateState($v.atrate1)"
                  ></b-form-input>
                  <span v-if="formStatusRef === FormStatusEnum.DELETE">{{ $v.atrate1.$model }}</span>
                </div>
                <div class="col-8">
                  <div class="d-inline">%</div>
                </div>
              </i-form-group-check>
            </b-form-row>
            <!-- 差異原因及處理方式說明 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'差異原因及處理方式說明'">
                <span v-if="formStatusRef === FormStatusEnum.MODIFY" style="color: blue">(填寫...等。)</span>
                <b-form-textarea
                  v-if="formStatusRef === FormStatusEnum.MODIFY || formStatusRef === FormStatusEnum.CREATE"
                  v-model="$v.mfas.$model"
                  trim
                  id="textarea-rows"
                  placeholder=""
                  rows="4"
                  :state="validateState($v.mfas)"
                ></b-form-textarea>
                <span v-if="formStatusRef === FormStatusEnum.DELETE">{{ $v.mfas.$model }}</span>
              </i-form-group-check>
            </b-form-row>
            <div class="text-center" v-if="formStatusRef === FormStatusEnum.MODIFY || formStatusRef === FormStatusEnum.CREATE">
              <i-button type="check-circle" @click="toSubmit"></i-button>
              <i-button size="md" type="arrow-counterclockwise" @click="toQueryView"></i-button>
              <i-button type="x-circle" @click="reset"></i-button>
            </div>
            <div class="text-center" v-if="formStatusRef === FormStatusEnum.DELETE">
              <b-button style="background-color: #1aa4b7" @click="toDelete">確定刪除</b-button>
              <i-button size="md" type="arrow-counterclockwise" @click="toQueryView"></i-button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import i18n from '@/shared/i18n';
import BidPrjMonthService from '@/components/bid/bidService/bid-prj-month.service';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { reactive, ref, toRef, watch, Ref, onMounted } from '@vue/composition-api';
import { maxValue, minValue, required } from '@/shared/validators';
import { handleBack } from '@/router/router';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { parseRocDate, formatDate, formatDateTime } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'bidAbd001Edit',
  props: {
    bidProjectKeyProp: {
      type: Object,
      required: false,
    },
    // 原為 ref(FormStatusEnum) 傳過來會變物件 { "value": "xxx" }，目的為這樣更改value值就不會被Vue警告(避免變異父元件資料)
    formStatus: {
      required: false,
      type: String,
    },
  },
  components: {
    bidProjectInfo,
  },

  setup(props, context) {
    const bringValueProp = toRef(props, 'bidProjectKeyProp');
    const formStatusRef = toRef(props, 'formStatus'); // toRef會保持對來源property的響應式連接，但改變父元件資料會被Vue警告，所以這邊改用ref複製一份新的資料
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();
    const bidPrjMonthService = new BidPrjMonthService();
    const admSysCodeService = new AdmSysCodeService();

    onMounted(() => {
      getAllSysCodes();
      findPtrate();
      findName();
      // findOrgId();

      // initDefaultValue();
    });
    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
      DELETE = '刪除',
    }

    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;
    const pccOrgId = ref(useGetters(['account']).account.value).value.pccOrgId;
    const store = context.root.$store;
    const userIp = store.getters.account.ip;

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      yearAndMonthDate: new Date(),
      mft: '', //佐證資料
      mftno: '', //佐證編號
      ptrate: '', //查證時系統資料預定進度
      ptrate1: '', //查證時佐證資料預定進度
      atrate: '', //查證時系統資料實際進度
      atrate1: '', //查證佐證統資料實際進度
      mfas: '', //差異原因及處理方式說明
      syr: '',
      chkunit: '',
      month: '',
      uIp: '',
    };

    // 表單物件驗證規則
    const rules = {
      yearAndMonthDate: {},
      mft: {},
      mftno: {},
      ptrate: {},
      ptrate1: { maxValue: maxValue(100), minValue: minValue(0) },
      atrate: {},
      atrate1: { maxValue: maxValue(100), minValue: minValue(0) },
      mfas: {},
      chkunit: {},
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 下拉選單選項
    const queryOptions = reactive({
      bid055: [],
    });

    const getName = ref();

    // 執行結果
    const dataPromise = ref(null);

    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const stepVisible = reactive({
      queryRow: true,
    });

    const toSubmit = () => {
      const value = new Date();
      const offset = value.getTimezoneOffset();
      form.createDate = new Date();
      form.createDate = new Date(form.createDate.getTime() - offset * 60 * 1000);
      form.createUser = userIdentity;
      form.chkunit = pccOrgId;
      form.uIp = userIp;

      let criteria = {
        wkut: bringValueProp.value.wkut,
        prjno: bringValueProp.value.prjno,
      };

      Object.assign(form, criteria);
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          handleProjectMonthDateToString(form);
          bidPrjMonthService.saveProjectMonth(form);
          dataPromise.value = axios
            .post('/bid-prj-ratecfs', form)
            .then(({ data }) => {
              createDefaultValue(data);
              isReload.value = true;
              $bvModal.msgBoxOk('新增成功!');
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //刪除
    const toDelete = () => {
      let deleteKey = {
        wkut: bringValueProp.value.wkut,
        prjno: bringValueProp.value.prjno,
        syr: bringValueProp.value.syr,
        month: bringValueProp.value.month,
        chkunit: bringValueProp.value.chkunit,
        mft: bringValueProp.value.mft,
      };
      let bidProjectKey = {
        wkut: bringValueProp.value.wkut,
        prjno: bringValueProp.value.prjno,
      };
      axios.post('/bid-prj-ratecfs/delete', deleteKey).then(() => {
        $bvModal.msgBoxOk('刪除成功!');
        // toQuery(deleteKey.wkut, deleteKey.prjno);
      });

      navigateByNameAndParams('bidAbd001Info', { bidProjectKey, isNotKeepAlive: false });
    };

    const findName = () => {
      const criteria = bringValueProp.value.chkunit;
      axios.get('/bid-unit-10-name/' + criteria).then(({ data }) => {
        if (data.name != null) {
          getName.value = data.name;
        } else {
          getName.value = '(未填寫)';
        }
      });
    };

    const findOrgId = () => {
      axios.post('/bid-prj-ratecf-find-org-id/' + pccOrgId).then(({ data }) => {
        console.log('8888');
        console.log(data, '8888');
      });
    };

    function handleProjectMonthDateToString(bidPrjMonth: IBidPrjMonth) {
      bidPrjMonth.syr = (bidPrjMonth.yearAndMonthDate.getFullYear() - 1911).toString();
      bidPrjMonth.yr = bidPrjMonth.syr;
      bidPrjMonth.month = (bidPrjMonth.yearAndMonthDate.getMonth() + 1).toString().padStart(2, '0');
    }

    function handleStringToProjectMonthDate(bidPrjMonth: IBidPrjMonth) {
      bidPrjMonth.yearAndMonthDate = new Date(parseInt(bidPrjMonth.syr) + 1911, parseInt(bidPrjMonth.month) - 1);
    }

    const findPtrate = () => {
      const criteria = {
        wkut: bringValueProp.value.wkut,
        prjno: bringValueProp.value.prjno,
      };
      axios.post('/bid-prj-ratecf-find-by-pk', criteria).then(({ data }) => {
        const criteria = {
          atrate: data.atrate,
          ptrate: data.ptrate,
        };
        Object.assign(form, criteria);
      });
    };

    //下拉式選單
    const getAllSysCodes = () => {
      admSysCodeService.setFormOptions(queryOptions, 'BID');
    };

    const toQueryView = () => {
      let bidProjectKey = {
        wkut: bringValueProp.value.wkut,
        prjno: bringValueProp.value.prjno,
      };
      navigateByNameAndParams('bidAbd001Info', { bidProjectKey, isNotKeepAlive: false });
    };

    const createDefaultValue = data => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      // if (data.syr !== null || data.syr !== undefined) {
      // formDefault.yearAndMonthDate = new Date(parseInt(data.syr) + 1911, parseInt(data.month) - 1);
      // }
      reset();
    };

    //限制能選的日期
    function notAfterSdateEnd(date: Date) {
      return date > new Date();
    }

    watch(
      bringValueProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          // bringValueProp.value.startDate = new Date(bringValueProp.value.startDate);
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          createDefaultValue(newValue);
        }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      formDefault,
      $v,
      form,
      reset,
      validateState,
      dataPromise,
      queryOptions,
      toQueryView,
      createDefaultValue,
      FormStatusEnum,
      formStatusRef,
      toSubmit,
      getAllSysCodes,
      formOptionsFormatter,
      stepVisible,
      notAfterSdateEnd,
      findPtrate,
      toDelete,
      findName,
      getName,
      // userId,
    };
  },
  filters: {
    dateToString: (value: string) => {
      var date = new Date(value);
      const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      return year + '年' + month + '月';
    },
    responsIdToName: (value: string) => {},
  },
};
</script>
<style></style>

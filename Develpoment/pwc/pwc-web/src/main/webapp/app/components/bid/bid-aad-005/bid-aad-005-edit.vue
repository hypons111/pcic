<template>
  <div>
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">新增外籍營造工核定狀況</h5>
      </div>
      <div class="card-body col-12">
        <!-- 核定日期 -->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'核定日期'" :item="$v.sdate">
            <b-form-row>
              <i-date-picker v-if=" formStatusRef === FormStatusEnum.CREATE" v-model="$v.sdate.$model" placeholder="yyy/MM/dd"></i-date-picker>
              <span v-if=" formStatusRef === FormStatusEnum.MODIFY">{{ $v.sdate.$model | dateToString }}</span>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 核定人數-->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="6" :label="'核定人數'" :item="$v.frWkr">
            <b-form-input class="col-sm-5" v-model="$v.frWkr.$model" :state="validateState($v.frWkr)"></b-form-input>
            <div class="col-sm-6"><span>人</span></div>
          </i-form-group-check>
        </b-form-row>
        <!-- 核定方式-->
        <b-form-row>
          <i-form-group-check :label="'核定方式'" :item="$v.frType">
            <b-form-radio-group content-cols-md="12" v-model="$v.frType.$model">
              <b-form-radio value="A">本工程核定</b-form-radio>
              <b-form-radio value="B">他工程調派</b-form-radio>
            </b-form-radio-group>
          </i-form-group-check>
        </b-form-row>
        <!-- 核定文號-->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="5" :label="'核定文號'" :item="$v.frPno">
            <b-form-input v-model="$v.frPno.$model" :state="validateState($v.frPno)"></b-form-input>
            <div><span style="color: blue">請填報勞動部核定文號，如&nbsp;&nbsp;勞動發事字第1091234567號</span></div>
          </i-form-group-check>
        </b-form-row>
        <!-- 核定說明 -->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="5" :label="'核定說明'" :item="$v.frRmk">
            <b-form-textarea
              id="textarea-rows"
              v-model="$v.frRmk.$model"
              trim
              :state="validateState($v.frRmk)"
              placeholder=""
              rows="4"
            ></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 計畫別 -->
        <b-form-row>
          <i-form-group-check> </i-form-group-check>
          <i-form-group-check class="col-sm-6" label-cols-md="1" content-cols-md="8" :label="'計畫別'" :item="$v.frKnd1">
            <b-form-select
              class="col-8"
              v-model="$v.frKnd1.$model"
              :state="validateState($v.frKnd1)"
              :options="queryOptions.bid039"
            ></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 核配比例-->
        <b-form-row>
          <i-form-group-check class="col-sm-6" label-cols-md="4" content-cols-md="6" :label="'核配比例'" :item="$v.frRate">
            <b-form-input class="col-sm-5" v-model="$v.frRate.$model" :state="validateState($v.frRate)"></b-form-input>
            <div class="col-sm-1"><span>%</span></div>
          </i-form-group-check>
          <i-form-group-check class="col-sm-6" label-cols-md="1" content-cols-md="8" :label="'特殊性'" :item="$v.frKnd2">
            <b-form-select
              class="col-8"
              v-model="$v.frKnd2.$model"
              :state="validateState($v.frKnd2)"
              :options="queryOptions.bid040"
            ></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 規模 -->
        <b-form-row>
          <i-form-group-check> </i-form-group-check>
          <i-form-group-check class="col-sm-6" label-cols-md="1" content-cols-md="8" :label="'規模'" :item="$v.frKnd3">
            <b-form-select
              class="col-8"
              v-model="$v.frKnd3.$model"
              :state="validateState($v.frKnd3)"
              :options="queryOptions.bid041"
            ></b-form-select>
          </i-form-group-check>
        </b-form-row>

        <b-row>
          <b-col cols="2"></b-col>
          <b-col cols="6"
            ><span style="color: blue">核配比例依勞動報專案核定...重要建設工程聘僱外及營造工作頁規範三、(二)附表一&nbsp;&nbsp;計算</span
            ><br />
            <span style="color: red">總分=計畫別級別*30%+特殊性級別*40%+規劃級別*30%</span><br />
            <span style="color: red">總分80以上，核配比例(%)=總分*0.004</span><br />
            <span style="color: red">總分未達80，核配比例(%)=&nbsp;&nbsp;20%</span><br />
          </b-col>
        </b-row>

        <div class="text-center">
          <i-button type="save" @click="toSubmit"></i-button>
          <i-button type="arrow-counterclockwise" @click="backToPreviousPage"></i-button>
          <i-button type="x-circle"></i-button>
        </div>
      </div>
    </div>

    <!-- 錯誤訊息 -->
    <!-- <b-modal
      id="valid-error-modal"
      size="lg"
      title="資料驗證有誤:"
      :header-text-variant="'light'"
      :header-bg-variant="'danger'"
      hide-footer
    >
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModals.hide('valid-error-modal')">了解</b-button>
    </b-modal> -->
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, computed, Reactive, onMounted } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';
import { handleBack } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import axios from 'axios';
import FormStatusEnum from '@/shared/enum';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';

export default {
  name: 'bidAad005Edit',
  props: {
    bidProjectKeyProp: {
      type: Object
      required: false,
    },
    formStatus: {
      required: false,
      type: String,
    },
  },
  components: {},

  setup(props, context) {
    const bidProjectKeyProps: Ref<any> = toRef(props, 'bidProjectKeyProp');
    const admSysCodeService = new AdmSysCodeService();

    const formStatusRef = toRef(props, 'formStatus'); // toRef會保持對來源property的響應式連接，但改變父元件資料會被Vue警告，所以這邊改用ref複製一份新的資料
    //透過modal show錯誤訊息
    const $bvModal = useBvModal();
    let errorMessages = ref([]);

    const notificationService: NotificationService = useNotification();

    // 下拉選單選項
    const queryOptions = reactive({
      bid039: [],
      bid040: [],
      bid041: [],
    });

    onMounted(() => {
      getAllSysCodes();
    });

    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
      DELETE = '刪除',
    }

    const formDefault = {
      sdate: '', //核定日期
      frWkr: '', //核定人數
      frType: '', //核定方式
      frPno: '', //核定文號
      frRmk: '', //核定說明
      frRate: '', //核配比例
      frKnd1: '', //計畫別
      frKnd2: '', //特殊性
      frKnd3: '', //規模
      createDate: '',
      createUser: '',
      uIp: '',
    };

    const rules = {
      sdate: {},
      frWkr: {},
      frType: {},
      frPno: {},
      frRmk: {},
      frRate: {},
      frKnd1: {},
      frKnd2: {},
      frKnd3: {},
    };

    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 是否需要重新查詢
    const isReload = ref(false);

    //去SYSCODE找下拉是選單
    const getAllSysCodes = () => {
      admSysCodeService.setFormOptions(queryOptions, 'BID');
    };

      //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;
    //  const fromName = useStore().value.getters.routeData.fromName;

     //取得使用者資訊ip
     const store = context.root.$store;
     const userIp = store.getters.account.ip

     const dataPromise = ref(null);
    

    //回上一頁
    function backToPreviousPage() {
      reset();
      handleBack({ isReload: isReload.value });
      isReload.value = false;
    }

    //新增頁面的提交
    const toSubmit = () => {
      // checkValidity().then((isValid: boolean) => {
      const value = new Date();
      const offset = value.getTimezoneOffset();
      form.createDate = new Date();
      form.createDate = new Date(form.createDate.getTime() - offset * 60 * 1000);
      form.createUser = userIdentity;
      form.uIp = userIp;

      let criteria = {
        wkut: bidProjectKeyProps.value.wkut,
        prjno: bidProjectKeyProps.value.prjno,
      };
      Object.assign(form, criteria);
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          dataPromise.value = axios
            .post('/bid-prj-frwkrs', form)
            .then(({ data }) => {
              createDefaultValue(data);
              isReload.value = true;
              $bvModal.msgBoxOk('外籍勞工核定新增成功!');
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const createDefaultValue = data => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      reset();
    };


     watch(
      bidProjectKeyProps,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          bidProjectKeyProps.value.sdate = new Date(bidProjectKeyProps.value.sdate);
          createDefaultValue(newValue);
        }
      },
      { immediate: true }
    );


    return {
      $v,
      reset,
      $bvModal,
      errorMessages,
      backToPreviousPage,
      validateState,
      formDefault,
      FormStatusEnum,
      formStatusRef,
      getAllSysCodes,
      queryOptions,
      toSubmit,
      userIdentity,
      dataPromise,
    };
  },
   filters: {
    dateToString: (value: string) => {
      var date = new Date(value);
      const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      return year + '年' + month + '月' + day + '日';
    },
    responsIdToName: (value: string) => {},
  },
};
</script>

<style></style>

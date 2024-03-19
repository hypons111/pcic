<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0" v-if="formStatusForwardProp === formStatusEnum.MODIFY">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            編輯計劃證照資料
          </h5>
          <h5 class="m-0" v-else-if="formStatusForwardProp === formStatusEnum.CREATE">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            新增計劃證照資料
          </h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="$t('label.planName') + '：'" :item="$v.projectName">
              <b-form-input class="col-12" v-model="$v.projectName.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.lic_class') + '：'" :item="$v.licenseCategory" :labelStar="true">
              <b-form-select v-model="$v.licenseCategory.$model" :state="validateState($v.licenseCategory)">
                <option v-for="(selectOption, indexOpt) in options.licenseCategory" :key="indexOpt" :value="selectOption.text">
                  {{ selectOption.text }}
                </option>
              </b-form-select>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.licenseName') + '：'" :item="$v.licenseName" :labelStar="true">
              <b-form-input v-model="$v.licenseName.$model" :state="validateState($v.licenseName)"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.organ_review') + '：'" :item="$v.reviewOrg" :labelStar="true">
              <b-form-select v-model="$v.reviewOrg.$model" :state="validateState($v.reviewOrg)">
                <option v-for="(selectOption, indexOpt) in options.reviewOrg" :key="indexOpt" :value="selectOption.text">
                  {{ selectOption.text }}
                </option>
              </b-form-select>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.date_reserve') + '：'" :item="$v.expiredDate" :labelStar="true">
              <i-date-picker v-model="$v.expiredDate.$model" :state="validateState($v.expiredDate)"></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.date_due') + '：'" :item="$v.expectDate" :labelStar="true">
              <i-date-picker v-model="$v.expectDate.$model" :state="validateState($v.expectDate)"></i-date-picker>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.date_actual') + '：'" :item="$v.realDate">
              <i-date-picker v-model="$v.realDate.$model"></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <div class="button-float-right">
            <b-button-toolbar class="mt-5" v-if="formStatusForwardProp === formStatusEnum.MODIFY">
              <i-button class="ml-1" type="save" @click="submitForm('modify')"></i-button>
              <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
              <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
              <i-button class="ml-1" type="trash" @click="toQueryView"></i-button>
            </b-button-toolbar>
            <b-button-toolbar class="mt-5" v-else-if="formStatusForwardProp === formStatusEnum.CREATE">
              <i-button class="ml-1" type="plus-circle" @click="submitFormNext"></i-button>
              <i-button class="ml-1" type="save" @click="submitForm('create')"></i-button>
              <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
              <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
            </b-button-toolbar>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler, notificationResponseHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { FormStatusEnum } from '@/shared/enum';
import { LicLicense } from '@/shared/model/licModel/lic-license.model';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'lic0601EditInfo',
  //props可接受父元件傳來的物件，裡面可以定義也可以放空物件如xxx:{}
  props: {
    formStatusForward: {
      type: String,
      required: true,
    },
    licLicense: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const licenseProp = toRefs(props).licLicense;

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    //傳入狀態
    const formStatusForwardProp = toRefs(props).formStatusForward;

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    // 宣告表單物件及初始值
    const formDefault = {
      projectName: '國立故宮博物院南部院區籌設計畫', //計畫名稱
      licenseCategory: '', //證照許可類別
      licenseName: '', //送審書件名稱
      reviewOrg: '', //審證機關
      expiredDate: undefined, //預計送審日期
      expectDate: undefined, //預計完成日期
      realDate: undefined, //實際完成日期
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      projectName: { notnull: required },
      licenseCategory: { notnull: required },
      licenseName: { notnull: required },
      reviewOrg: { notnull: required },
      expiredDate: { notnull: required },
      expectDate: { notnull: required },
      realDate: {},
    };
    //checkValidity用來檢核，reset用來重置form
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 下拉選單選項
    const options = reactive({
      licenseCategory: [
        { value: '', text: '請選擇' },
        { value: '1', text: '環境影響' },
        { value: '2', text: '水土保持' },
        { value: '3', text: '非都編定' },
        { value: '4', text: '都市計畫' },
        { value: '5', text: '用地取得' },
        { value: '6', text: '都設審議' },
        { value: '7', text: '綠建築' },
        { value: '8', text: '文化保存' },
        { value: '9', text: '建築許可' },
        { value: '10', text: '河川許可' },
        { value: '11', text: '跨鐵公路' },
        { value: '12', text: '管線處理' },
        { value: '13', text: '其它法令' },
      ],
      reviewOrg: [
        { value: '', text: '請選擇' },
        { value: '300000000A', text: '行政院' },
        { value: '301000000A', text: '內政部' },
        { value: '301010000C', text: '經濟部水利署' },
        { value: '305720000S', text: '海軍軍官學校' },
        { value: '307510000D', text: '臺南市捷運工程處' },
        { value: '315000000H', text: '交通部' },
      ],
    });

    // 是否需要重新查詢
    const isReload = ref(false);

    const $bvModal = useBvModal();

    const toQueryView = () => {
      handleBack({ isReload: isReload.value, isNotKeepAlive: true });
      isReload.value = false;
    };

    const changeFormStatus = (status: formStatusEnum) => {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    //新增&修改送出前檢查訊息
    const submitForm = (submitMethod: string) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (submitMethod === 'modify') {
            //提交修改
            //putForm();
            $bvModal.msgBoxOk('資料異動成功');
            toQueryView();
          }
          if (submitMethod === 'create') {
            //提交新增
            //insertTyphoon(true);
            $bvModal.msgBoxOk('資料新增成功');
            reset();
          }
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //繼續新增送出前檢查
    const submitFormNext = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //提交新增
          // insertTyphoon(false);
          $bvModal.msgBoxOk('資料新增成功');
          changeFormStatus(formStatusEnum.CREATE);
          reset();
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const createDefaultValue = (data: LicLicense) => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
    };

    watch(
      licenseProp,
      () => {
        if (formStatusForwardProp.value === 'modify') {
          createDefaultValue(licenseProp.value);
          changeFormStatus(formStatusEnum.MODIFY);
        } else {
          Object.assign(form, formDefault);
          createDefaultValue(form);
          changeFormStatus(formStatusEnum.CREATE);
        }
      },
      { immediate: true }
    );

    return {
      formStatusEnum,
      formStatusRef,
      formStatusForwardProp,
      $v,
      options,
      changeFormStatus,
      toQueryView,
      submitForm,
      submitFormNext,
      reset,
      validateState,
      licenseProp,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

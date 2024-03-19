<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0" v-if="formStatusForwardProp === formStatusEnum.MODIFY">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            編輯證照辦理情形填報資料
          </h5>
          <h5 class="m-0" v-else-if="formStatusForwardProp === formStatusEnum.CREATE">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            新增證照辦理情形填報資料
          </h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.planName') + '：'" :item="$v.projectName">
              <b-form-input v-model="$v.projectName.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.lic_class') + '：'" :item="$v.licenseCategory">
              <b-form-input v-model="$v.licenseCategory.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.licenseName') + '：'" :item="$v.licenseName">
              <b-form-input v-model="$v.licenseName.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.organ_review') + '：'" :item="$v.reviewOrg" :labelStar="true">
              <b-form-select v-model="$v.reviewOrg.$model" :state="validateState($v.reviewOrg)">
                <option v-for="(selectOption, indexOpt) in options.reviewOrg" :key="indexOpt" :value="selectOption.text">
                  {{ selectOption.text }}
                </option>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.date_actual') + '：'" :item="$v.realDate">
              <i-date-picker v-model="$v.realDate.$model"></i-date-picker>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.procDate') + '：'" :item="$v.procDate" :labelStar="true">
              <i-date-picker v-model="$v.procDate.$model" :state="validateState($v.procDate)"></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.handlingSituation') + '-' + $t('label.status') + '：'" :item="$v.procStatus">
              <b-form-input v-model="$v.procStatus.$model"> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.handlingSituation') + '-' + $t('label.counts') + '：'" :item="$v.procTimes">
              <b-form-input v-model="$v.procTimes.$model"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.relatedFile') + '：'" :item="$v.relatedFile">
              <b-form-file
                v-model.trim="$v.relatedFile.$model"
                @change=""
                browse-text="選擇檔案"
                :placeholder="$v.relatedFile.$model ? $v.relatedFile.name : ''"
              ></b-form-file>
            </i-form-group-check>
            <i-form-group-check></i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-12" :label-cols="2" :content-cols="10" :label="$t('label.memo') + '：'" :item="$v.memo">
              <b-form-textarea v-model.trim="$v.memo.$model" placeholder="" rows="8"></b-form-textarea>
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
  name: 'lic0603EditInfo',
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
      licenseCategory: '建築許可(建築執照或特種建築物許可等)', //證照許可類別
      licenseName: '使用執照', //送審書件名稱
      reviewOrg: '', //審證機關
      realDate: undefined, //實際完成日期
      procDate: undefined, //: undefined, //實際完成日期
      procStatus: '', //辦理情形-狀態
      procTimes: undefined, //辦理情形-次數
      relatedFile: null, //相關紀錄文件(檔案)
      memo: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      projectName: {},
      licenseCategory: {},
      licenseName: {},
      reviewOrg: { notnull: required },
      realDate: {},
      procDate: { notnull: required },
      procStatus: {},
      procTimes: {},
      procStatus: {},
      relatedFile: {},
      memo: {},
    };
    //checkValidity用來檢核，reset用來重置form
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 下拉選單選項
    const options = reactive({
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

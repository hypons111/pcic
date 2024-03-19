<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0" v-if="formStatusForwardProp === formStatusEnum.MODIFY">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            編輯計劃管理資料
          </h5>
          <h5 class="m-0" v-else-if="formStatusForwardProp === formStatusEnum.CREATE">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            新增計劃管理資料
          </h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <!-- 計畫名稱 -->
            <i-form-group-check
              class="col-10"
              :labelStar="true"
              :label="$t('label.planName') + '：'"
              :item="$v.projectName"
              :label-cols="4"
              :content-cols="8"
            >
              <b-form-input v-model.trim="$v.projectName.$model" :state="validateState($v.projectName)" maxlength="30"> </b-form-input>
            </i-form-group-check>
            <i-form-group-check
              class="col-10"
              :label="$t('label.planType') + '：'"
              :item="$v.projectCategory"
              :labelStar="true"
              :state="validateState($v.projectCategory)"
            >
              <b-form-select :options="options.projectCategory" v-model="$v.projectCategory.$model"></b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-10" :label="$t('label.planFile') + '：'" :item="$v.planFile">
              <b-form-file
                v-model.trim="$v.planFile.$model"
                @change=""
                browse-text="瀏覽..."
                :placeholder="$v.planFile.$model ? $v.planFile.name : ''"
              ></b-form-file>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check
              class="col-10"
              :label="$t('label.planPeriod') + '：'"
              :item="$v.planPeriod"
              :state="validateState($v.planPeriod)"
              :labelStar="true"
            >
              <b-form-row class="form-inline">
                <i-date-picker></i-date-picker>
                至
                <i-date-picker></i-date-picker>
              </b-form-row>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-10" :label="$t('label.organ_exec') + '：'" :item="$v.execOrg" :labelStar="true">
              <b-form-select :options="options.execOrg" v-model="$v.execOrg.$model" :state="validateState($v.execOrg)"></b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-10" :label="$t('label.organ_manage') + '：'" :item="$v.adminOrg" :labelStar="true">
              <b-form-select :options="options.adminOrg" v-model="$v.adminOrg.$model" :state="validateState($v.adminOrg)"></b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-10" :label="$t('label.date_feas') + '：'" :item="$v.date_feas">
              <i-date-picker v-model.trim="$v.date_feas.$model" :state="$v.date_feas"></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-10" :label="$t('label.date_cons') + '：'" :item="$v.date_cons">
              <i-date-picker v-model.trim="$v.date_cons.$model" :state="$v.date_cons"></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-10" :label="$t('label.date_work') + '：'" :item="$v.date_work">
              <i-date-picker v-model.trim="$v.date_work.$model" :state="$v.date_work"></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-10" :label="$t('label.total_budget') + '：'" :item="$v.total_budget">
              <b-form-input v-model.trim="$v.total_budget.$model"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-10" :label="$t('label.execName') + '：'" :item="$v.execName" :labelStar="true">
              <b-form-input v-model.trim="$v.execName.$model" :state="validateState($v.execName)"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-10" :label="$t('label.execTel') + '：'" :item="$v.execTel" :labelStar="true">
              <b-form-input v-model.trim="$v.execTel.$model" :state="validateState($v.execTel)"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-10" :label-cols="4" :content-cols="8" :label="$t('label.memo') + '：'" :item="$v.memo">
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
import { Project_basic } from '@/shared/model/project_basic';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'lic0501EditInfo',
  //props可接受父元件傳來的物件，裡面可以定義也可以放空物件如xxx:{}
  props: {
    formStatusForward: {
      type: String,
      required: true,
    },
    project_basic: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const projectProp = toRefs(props).project_basic;

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
      projectName: '',
      projectCategory: '',
      planFile: null,
      planPeriod: '',
      execOrg: '',
      adminOrg: '',
      date_feas: '',
      date_cons: '',
      date_work: '',
      total_budget: '',
      execName: '',
      execTel: '',
      memo: '',
    };
    // 建立表單物件 ref
    let form = reactive(Object.assign({}, formDefault));
    // 表單物件驗證規則
    const rules = {
      projectName: { notnull: required },
      projectCategory: { notnull: required },
      planFile: {},
      planPeriod: {},
      execOrg: { notnull: required },
      adminOrg: { notnull: required },
      date_feas: {},
      date_cons: {},
      date_work: {},
      total_budget: {},
      execName: {},
      execTel: {},
      memo: {},
    };
    //checkValidity用來檢核，reset用來重置form
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 下拉選單選項
    const options = reactive({
      projectCategory: [
        { value: '', text: '請選擇' },
        { value: 'A01', text: '公路' },
        { value: 'A02', text: '航道運輸' },
        { value: 'A03', text: '航空' },
        { value: 'A04', text: '港埠' },
        { value: 'A05', text: '觀光' },
        { value: 'B01', text: '環境保護' },
        { value: 'B02', text: '水利建設' },
        { value: 'B03', text: '下水道' },
        { value: 'B04', text: '國家公園' },
        { value: 'C01', text: '工商設施' },
        { value: 'C02', text: '油電' },
        { value: 'D01', text: '都市開發' },
        { value: 'E01', text: '文化' },
        { value: 'F01', text: '教育' },
        { value: 'F02', text: '體育' },
        { value: 'G01', text: '農業建設' },
        { value: 'H01', text: '衛生福利' },
      ],
      execOrg: [
        { value: '', text: '請選擇' },
        { value: '300000000A', text: '行政院' },
        { value: '301000000A', text: '內政部' },
        { value: '301010000C', text: '內政部警政署' },
        { value: '305720000S', text: '海軍軍官學校' },
        { value: '307510000D', text: '財政部關稅總局' },
        { value: '315000000H', text: '交通部' },
      ],
      adminOrg: [
        { value: '', text: '請選擇' },
        { value: '300000000A', text: '行政院' },
        { value: '301000000A', text: '內政部' },
        { value: '301010000C', text: '內政部警政署' },
        { value: '305720000S', text: '海軍軍官學校' },
        { value: '307510000D', text: '財政部關稅總局' },
        { value: '315000000H', text: '交通部' },
      ],
      projectStatus: [
        { value: '', text: '全部' },
        { value: '0', text: '執行中' },
        { value: '1', text: '完成' },
        { value: '2', text: '中止' },
      ],
    });

    // 執行結果
    const dataPromise = ref(null);
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

    const createDefaultValue = (data: Project_basic) => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
    };

    watch(
      projectProp,
      () => {
        if (projectProp.value) {
          createDefaultValue(projectProp.value);
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
      dataPromise,
      changeFormStatus,
      toQueryView,
      submitForm,
      submitFormNext,
      reset,
      validateState,
      projectProp,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

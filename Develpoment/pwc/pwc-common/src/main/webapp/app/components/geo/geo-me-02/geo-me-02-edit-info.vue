<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            任職機構與領導人
          </h5>
        </div>
        <div class="card-body">
          <b-row class="align-items-center mb-3">
            <b-col cols="6">
              <div class="text-center label-top">專業指導人</div>
            </b-col>
            <b-col cols="6">
              <div class="text-center label-top">任職機構</div>
            </b-col>
          </b-row>

          <b-row class="align-items-center">
            <b-col cols="2">
              <div class="text-right pr-1 b-form-group-label">姓名</div>
            </b-col>
            <b-col cols="4">
              <div v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">{{ $v.mentorName.$model }}</div>
              <b-form-input v-else v-model="$v.mentorName.$model" :state="validateState($v.mentorName)" trim></b-form-input>
            </b-col>

            <b-col cols="2">
              <div class="text-right pr-1 b-form-group-label">名稱</div>
            </b-col>
            <b-col cols="4">
              <div v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">{{ $v.compName.$model }}</div>
              <b-form-input v-else v-model="$v.compName.$model" :state="validateState($v.compName)" trim></b-form-input>
            </b-col>
          </b-row>
          <b-row class="feedback-row">
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.mentorName.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.compName.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
          </b-row>

          <b-row class="align-items-center">
            <b-col cols="2">
              <div class="text-right pr-1 b-form-group-label">職稱</div>
            </b-col>
            <b-col cols="4">
              <div v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">{{ $v.mentorPost.$model }}</div>
              <b-form-input v-else v-model="$v.mentorPost.$model" :state="validateState($v.mentorPost)" trim></b-form-input>
            </b-col>

            <b-col cols="2">
              <div class="text-right pr-1 b-form-group-label">統編</div>
            </b-col>
            <b-col cols="4">
              <div v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">
                {{ $v.compSerialNum.$model }}
              </div>
              <b-form-input v-else v-model="$v.compSerialNum.$model" :state="validateState($v.compSerialNum)" trim></b-form-input>
            </b-col>
          </b-row>
          <b-row class="feedback-row">
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.mentorPost.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.compSerialNum.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
          </b-row>

          <b-row class="align-items-center">
            <b-col cols="2">
              <div class="text-right pr-1 b-form-group-label">技師科別</div>
            </b-col>
            <b-col cols="4">
              <div v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">
                {{ $v.mentorSubject.$model ? queryOptionsFormatter('subject', $v.mentorSubject.$model) : '' }}
              </div>
              <b-form-select
                v-else
                v-model="$v.mentorSubject.$model"
                :options="formOptions.subject"
                :state="validateState($v.mentorSubject)"
              ></b-form-select>
            </b-col>

            <b-col cols="2">
              <div class="text-right pr-1 b-form-group-label">負責人姓名</div>
            </b-col>
            <b-col cols="4">
              <div v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">
                {{ $v.compPrincipalName.$model }}
              </div>
              <b-form-input v-else v-model="$v.compPrincipalName.$model" :state="validateState($v.compPrincipalName)" trim></b-form-input>
            </b-col>
          </b-row>
          <b-row class="feedback-row">
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.mentorSubject.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.compPrincipalName.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
          </b-row>

          <b-row class="align-items-center">
            <b-col cols="2">
              <div class="text-right pr-1 b-form-group-label">技師證書字號</div>
            </b-col>
            <b-col cols="4">
              <div v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">
                {{ $v.mentorCertificateID.$model }}
              </div>
              <b-form-input v-else v-model="$v.mentorCertificateID.$model" :state="validateState($v.mentorCertificateID)"></b-form-input>
            </b-col>
          </b-row>
          <b-row class="feedback-row">
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.mentorCertificateID.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
          </b-row>

          <b-row class="align-items-center mb-5">
            <b-col cols="2">
              <div class="text-right pr-1 b-form-group-label">技師執業執照字號</div>
            </b-col>
            <b-col cols="4">
              <div v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">
                {{ $v.mentorLicenseID.$model }}
              </div>
              <b-form-input v-else v-model="$v.mentorLicenseID.$model" :state="validateState($v.mentorLicenseID)"></b-form-input>
            </b-col>
          </b-row>
          <b-row class="feedback-row">
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.mentorLicenseID.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
          </b-row>

          <b-row class="align-items-center mb-3" v-if="formStatusRef !== formStatusEnum.CREATE">
            <b-col cols="2">
              <div class="text-right label"><span class="red-text">*</span>是否核准</div>
            </b-col>
            <b-col cols="10">
              <div v-if="formStatusRef !== formStatusEnum.APPROVE">
                {{ $v.isApprove.$model ? queryOptionsFormatter('isApprove', $v.isApprove.$model) : '' }}
              </div>
              <b-form-radio-group
                v-if="formStatusRef === formStatusEnum.APPROVE"
                v-model="$v.isApprove.$model"
                :options="formOptions.isApprove.slice(2)"
              >
              </b-form-radio-group>
            </b-col>
          </b-row>

          <b-row v-if="$v.isApprove.$model === '0'">
            <b-col cols="2">
              <div class="text-right label"><span class="red-text">*</span>退回原因</div>
            </b-col>
            <b-col cols="10">
              <span v-if="formStatusRef !== formStatusEnum.APPROVE">{{ $v.refuseReason.$model }}</span>
              <b-form-textarea
                v-if="formStatusRef === formStatusEnum.APPROVE"
                v-model.trim="$v.refuseReason.$model"
                :state="validateState($v.refuseReason)"
                rows="4"
              ></b-form-textarea>
            </b-col>
          </b-row>
          <b-row class="feedback-row">
            <b-col offset="2">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.refuseReason.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
          </b-row>

          <b-button-toolbar class="float-right mt-4">
            <b-button v-show="$v.isApprove.$model === '1' || $v.isApprove.$model === '0'" size="sm" variant="outline-secondary" @click=""
              >送出審核</b-button
            >
            <b-button v-show="formStatusRef !== formStatusEnum.INFO" size="sm" variant="outline-secondary" @click=""
              ><font-awesome-icon :icon="['far', 'save']" />儲存</b-button
            >
            <b-button
              v-show="
                (formStatusRef === formStatusEnum.INFO && $v.isApprove.$model === '1') ||
                (formStatusRef === formStatusEnum.INFO && $v.isApprove.$model === '0')
              "
              size="sm"
              variant="outline-secondary"
              @click="changeFormStatus(formStatusEnum.MODIFY)"
              ><font-awesome-icon :icon="['far', 'edit']" />{{ $t('button.modify') }}</b-button
            >
            <b-button v-show="formStatusRef !== formStatusEnum.INFO" size="sm" variant="outline-secondary" @click="reset">重設</b-button>
            <b-button
              v-show="formStatusRef === formStatusEnum.MODIFY"
              size="sm"
              variant="outline-secondary"
              @click="changeFormStatus(formStatusEnum.INFO)"
              ><font-awesome-icon :icon="['fas', 'undo-alt']" />取消</b-button
            >
            <b-button v-show="formStatusRef !== formStatusEnum.MODIFY" size="sm" variant="outline-secondary" @click="toQueryView"
              ><font-awesome-icon :icon="['fas', 'arrow-left']" />返回</b-button
            >
          </b-button-toolbar>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, Ref, ref } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { statusFormatter } from '@/shared/formatter/common';
// import { guildTypeFormatter, statusFormatter } from '@/shared/formatter/common';
// import { CmsComp } from '@/shared/model/cms-comp.model';

export default {
  name: 'geoMe02EditInfo',
  props: {
    cmsComp: {
      required: false,
    },
    formStatus: {
      // type: Ref<formStatusEnum>,
      required: false,
    },
  },
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
      APPROVE = 'approve',
    }

    const formOptions = reactive({
      isApprove: [
        // { value: '', text: '不拘' },
        { value: '1', text: '未送出審核' },
        { value: '2', text: '待審核' },
        { value: '3', text: '通過' },
        { value: '0', text: '不通過' },
      ],
      subject: [
        { value: '', text: '請選擇' },
        { value: '1', text: '土木工程' },
        { value: '2', text: '水利工程' },
        { value: '3', text: '結構工程' },
        { value: '4', text: '大地工程' },
        { value: '5', text: '水土保持' },
        { value: '6', text: '採礦工程' },
        { value: '7', text: '應用地質' },
        { value: '8', text: '其他' },
      ],
    });

    // const roleRef: Ref<RoleEnum> = ref(RoleEnum.USER);

    // 傳入的物件
    const cmsCompProp = computed(() => props.cmsComp);
    const formStatus = computed(() => props.formStatus);
    const formStatusRef: Ref<formStatusEnum> = ref(formStatus.value.value);
    // const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    let formEmpty = {
      mentorName: '', //指導人姓名
      mentorPost: '', //指導人職稱
      mentorSubject: '', //指導人技師科別
      mentorCertificateID: '', //指導人技師證書字號
      mentorLicenseID: '', //指導人技師執業執照字號
      compName: '', //機構名稱
      compSerialNum: '', //機構統編
      compPrincipalName: '', //機構負責人姓名
      isApprove: '', //審核狀態
      refuseReason: '', //退回原因
    };

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      mentorName: '', //指導人姓名
      mentorPost: '', //指導人職稱
      mentorSubject: '', //指導人技師科別
      mentorCertificateID: '', //指導人技師證書字號
      mentorLicenseID: '', //指導人技師執業執照字號
      compName: '', //機構名稱
      compSerialNum: '', //機構統編
      compPrincipalName: '', //機構負責人姓名
      isApprove: '', //審核狀態
      refuseReason: '', //退回原因
    };

    // 建立表單物件 ref
    let form = reactive(Object.assign({}, formDefault));

    onActivated(() => {
      reset();
      // Object.keys(formDefault).forEach(key => formDefault[key]= '');
      Object.assign(formDefault, formEmpty);
      if (cmsCompProp.value) {
        Object.assign(formDefault, cmsCompProp.value);
        Object.assign(form, formDefault);
      } else {
        Object.assign(form, formDefault);
      }
      formStatusRef.value = formStatus.value.value;
    });

    // 表單物件驗證規則
    const rules = {
      mentorName: { notNull: required }, //指導人姓名
      mentorPost: { notNull: required }, //指導人職稱
      mentorSubject: { notNull: required }, //指導人技師科別
      mentorCertificateID: { notNull: required }, //指導人技師證書字號
      mentorLicenseID: { notNull: required }, //指導人技師執業執照字號
      compName: { notNull: required }, //機構名稱
      compSerialNum: { notNull: required }, //機構統編
      compPrincipalName: { notNull: required }, //機構負責人姓名
      isApprove: {}, //審核狀態
      refuseReason: { notNull: required }, //退回原因
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);
    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const toQueryView = () => {
      context.emit('changeComponent', 'GeoMe02Query', isReload.value);
    };

    const changeFormStatus = (status: formStatusEnum) => {
      reset();
      formStatusRef.value = status;
    };

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
            if (isOK) {
              putForm();
            }
          });
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const putForm = () => {
      let url: string;
      if (formStatusRef.value === formStatusEnum.MODIFY) {
        url = `/cms-comps/${form.id}`;
      }
      dataPromise.value = axios
        .put(url, form)
        .then(({ data }) => {
          changeFormStatus(formStatusEnum.INFO);
          createDefaultValue(data);
          isReload.value = true;
          notificationService.success(`${form.id}-${form.name}:修改成功`);
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const createDefaultValue = (data: any) => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
    };

    function queryOptionsFormatter(option: string, value: string) {
      let out = formOptions[option].find(item => item.value === value);
      if (out) {
        return out.text;
      } else {
        return '';
      }
    }

    // const queryOptionsFormatter = (option: string, value: string) => formOptions[option].find((item) => item.value === value).text;

    return {
      formStatusEnum,
      formStatusRef,
      $v,
      dataPromise,
      changeFormStatus,
      reset,
      validateState,
      toQueryView,
      submitForm,
      formOptions,
      queryOptionsFormatter,
      // roleRef
      // cmsCompProp
    };
  },
};
</script>
<style>
.red-text {
  color: red;
  font-weight: bold;
}
.label {
  background-color: #737373;
  font-weight: bolder;
  color: white;
  padding-top: 7px;
  padding-bottom: 7px;
  padding-right: 7px;
  /*border-right: 3px solid #d57c36;*/
  /*border-bottom: 3px solid #d57c36;*/
}
.b-form-group-label {
  background-color: #e5e5e5;
}
.feedback-row {
  height: 24px;
}
.label-top {
  background-color: #737373;
  font-weight: bolder;
  color: white;
  padding-top: 7px;
  padding-bottom: 7px;
  padding-right: 7px;
  /*border-right: 3px solid #d57c36;*/
  /*border-bottom: 3px solid #d57c36;*/
}
</style>

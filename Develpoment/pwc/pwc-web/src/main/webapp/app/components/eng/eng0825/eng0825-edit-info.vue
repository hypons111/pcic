<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            基本資料
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 發文日期 -->
            <i-form-group-check content-cols="4" :label="$t('label.publicDate') + '：'" :item="$v.publicDate">
              <!--<div>{{ $v.publicDate.$model | dateToString }}</div>-->
              <div>{{ data.publicDate }}</div>
            </i-form-group-check>
            <!-- 發文字號 -->
            <i-form-group-check content-cols="4" :label="$t('label.publicDocNo') + '：'" :item="$v.publicDocNo">
              <!-- <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.publicDocNo.$model }}</div> -->
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.publicDocNo }}</div>
              <b-form-input v-else v-model="$v.publicDocNo.$model" :state="validateState($v.publicDocNo)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 根據工程技術顧問公司管理條例 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="4" :label="$t('label.baseOn') + '：'" :item="$v.baseOn">
              <!-- <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.baseOn.$model }}</div> -->
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.baseOn }}</div>
              <b-form-input v-else v-model="$v.baseOn.$model" :state="validateState($v.baseOn)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 主旨 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="4" :label="$t('label.mainSubject') + '：'" :item="$v.subjects">
              <!-- <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.subjects.$model }}</div> -->
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.subjects }}</div>
              <b-form-input v-else v-model="$v.subjects.$model" :state="validateState($v.subjects)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 說明 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="4" :label="$t('label.description') + '：'" :item="$v.content">
              <!-- <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.content.$model }}</div> -->
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.content }}</div>
              <b-form-input v-else v-model="$v.content.$model" :state="validateState($v.content)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>

        <div class="text-center mb-2">
          <!-- 返回 -->
          <i-button type="arrow-left" @click="toQueryView" />
        </div>
        <!-- <i-switch-case
          class="mb-3"
          :criteria="criteria"
          api-url="/eng-engr-subjectpoints/criteria"
          @handleCaseChanged="createDefaultValue"
        ></i-switch-case> -->
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
// import EngEngrSubjectpointService from './eng-engr-subjectpoint.service';
import { useBvModal } from '@/shared/modal';
import { useNotification } from '@/shared/notification';
import { useValidation, validateState } from '@/shared/form';
import { handleBack } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { reactive, ref, toRefs, onMounted, defineComponent, unref } from '@vue/composition-api';
import { required, maxLength, nationalId, between, queryEngrByIdno } from '@/shared/validators';
import { ApplyItemEnum, FormStatusEnum, MemberTypeEnum, StatusEnum } from '@/shared/enum';
import { IEngEngrSubjectpoint, EngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import { useStore } from '@u3u/vue-hooks';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';

interface propsType {
  engEngrSubjectpoint: IEngEngrSubjectpoint;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0825EditInfo',
  props: {
    engEngrSubjectpoint: {
      required: false,
      type: Object,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    criteria: {
      required: false,
      type: Object,
    },
  },
  components: {
    ISwitchCase,
  },
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    //const engEngrSubjectpointService = new EngEngrSubjectpointService();

    const store = useStore();
    const $bvModal = useBvModal();
    const notificationService = useNotification();

    const { engEngrSubjectpoint, formStatus, criteria } = toRefs(props);

    const isReload = ref(false);

    const formOptions = reactive({
      law: [],
      subject: [],
      subjectList: [],
    });

    let formDefault = new EngEngrSubjectpoint();
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      publicDate: {},
      publicDocNo: {},
      baseOn: {},
      subjects: {},
      content: {},
      /*applyDate: { required: required },
      contractorName: {},
      idno: { required: required, nationalId: nationalId, isEngrIdno: queryEngrByIdno },
      chName: {},
      theme: { required: required, maxLength: maxLength(200) },
      content: { required: required, maxLength: maxLength(2000) },
      law: { required: required },
      subject: { required: required },
      point: { required: required, between: between(0, 999) },
      checkDesc: { maxLength: maxLength(200) },*/
      remark: { maxLength: maxLength(2000) },
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      setFormOptions();
      createDefaultValue(unref(engEngrSubjectpoint));
    });

    function setFormOptions() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    function createDefaultValue(data: IEngEngrSubjectpoint) {
      // console.log('engEngrSubjectpoint：', data);
      Object.assign(formDefault, data);
      setDate();
      reset();
      setSubjectList();
      // getContractorName();
    }

    function setDate() {
      formDefault.applyDate = parseToDate(formDefault.applyDate, '-');
    }

    function setSubjectList() {
      if (form.subjectList) {
        formOptions.subjectList = formOptions.subject.filter(item => form.subjectList.split(',').includes(item.value));
      } else {
        formOptions.subjectList = [];
        form.subject = undefined;
      }
    }

    function getContractorName() {
      axios
        .get(`/service/adm-account/${form.contractorId}`)
        .then(res => {
          formDefault.contractorName = res.data?.chName;
          form.contractorName = res.data?.chName;
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function submitForm() {
      $bvModal.msgBoxConfirm('請再次確認資料是否填寫正確？').then((isOK: boolean) => {
        if (isOK) {
          checkValidity().then((isValid: boolean) => {
            if (isValid) {
              //putForm();
            } else {
              $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
            }
          });
        }
      });
    }

    /*function putForm() {
      setFormBeforeSubmit();

      if (unref(formStatus) === FormStatusEnum.EDIT) {
        engEngrSubjectpointService
          .update(form)
          .then(engEngrSubjectpoint => {
            createDefaultValue(engEngrSubjectpoint);
            isReload.value = true;
          })
          .catch(notificationErrorHandler(notificationService));
      } else {
        engEngrSubjectpointService
          .create(form)
          .then(engEngrSubjectpoint => {
            createDefaultValue(engEngrSubjectpoint);
            isReload.value = true;
          })
          .catch(notificationErrorHandler(notificationService));
      }
    }*/

    function setFormBeforeSubmit() {
      const now = new Date();
      const offset = now.getTimezoneOffset();
      form.applyDate = form.applyDate ? new Date(form.applyDate.getTime() - offset * 60 * 1000) : null;

      form.subjectList = formOptions.subjectList.map(item => item.value).join(',');
      form.updateTime = new Date();
      form.updateUser = store.value.getters.account.id;
      if (unref(formStatus) === FormStatusEnum.CREATE) {
        form.contractorId = store.value.getters.account.id;
        form.createTime = new Date();
        form.createUser = store.value.getters.account.id;
        form.transferTime = null;
        form.transferFrom = null;
      }
    }

    function toQueryView() {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    }

    function pageReset() {
      reset();
      setSubjectList();
    }

    function queryEngMember() {
      if (!!form.idno && form.idno.length === 10) {
        axios
          .get(`/eng-members/${MemberTypeEnum.ENGR}/${form.idno}`)
          .then(copyMemberInfo)
          .catch(notificationErrorHandler(notificationService));
      }
    }

    function copyMemberInfo(res: AxiosResponse) {
      const data = res.data;
      form.engrId = data.engMemberId;
      form.chName = data.chName;
      form.enName = data.enName;

      axios
        .get(`/eng-engr-certificates/find-by-idno-and-status/${form.idno}/${StatusEnum.CLOSE}`)
        .then(getEngrCertificateSubjects)
        .catch(notificationErrorHandler(notificationService));
    }

    function getEngrCertificateSubjects(res: AxiosResponse) {
      const certificateList: IEngEngrCertificate[] = res.data;
      const set1 = new Set();
      const set2 = new Set();
      form.subjectList = certificateList
        .filter(item => !!item.engrCertificateDocNo || !!item.engrCertificateDocNoOld) // 過濾證書字號跟舊證書字號都沒有的資料
        .filter(item => !item.applyItemsList.split(',').includes(ApplyItemEnum.ABOLISH)) // 過濾申請項目包含廢止的資料
        .sort((a, b) => b.engEngrCertificateVersion - a.engEngrCertificateVersion) // 依據version降冪排列
        .filter(item => (!set1.has(item.engEngrCertificateId) ? set1.add(item.engEngrCertificateId) : false)) // 只取相同id第一筆資料(即最大version)
        .map(item => item.subject) // IEngEngrCertificate[] 取subject轉成 string[]
        .filter(item => (!set2.has(item) ? set2.add(item) : false)) // 過濾相同subject
        .sort((a, b) => Number(a) - Number(b)) // 依據subject代碼大小升冪排列
        .join(',');
      form.subject = undefined;
      setSubjectList();
    }
    const data = {
        publicDate: '2007/12/12',
        publicDocNo: '經(80)工0四四三號',
        baseOn: '第二條',
        subjects: '冷藏科',
        content: '經濟部公告',
      }

    return {
      FormStatusEnum,
      $v,
      pageReset,
      validateState,
      toQueryView,
      submitForm,
      //putForm,
      formOptions,
      formOptionsFormatter,
      queryEngMember,
      createDefaultValue,
      data,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>

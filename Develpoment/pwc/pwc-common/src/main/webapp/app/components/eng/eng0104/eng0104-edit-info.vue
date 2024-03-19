<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            書面積分審查
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 申請日期 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.applyDate') + '：'" :item="$v.applyDate">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.applyDate.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.applyDate.$model"
                :state="validateState($v.applyDate)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
            <!-- 承辦人員 -->
            <i-form-group-check :label="$t('label.contractorId') + '：'">
              <div>{{ $v.contractorName.$model }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 身分證字號 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.idno') + '：'" :item="$v.idno">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.idno.$model }}</div>
              <b-form-input
                v-else
                v-model="$v.idno.$model"
                :state="validateState($v.idno)"
                id="idnoInputPopover"
                maxlength="10"
                @change="queryEngMember"
                trim
                lazy
              ></b-form-input>
              <b-popover
                v-if="formStatus !== FormStatusEnum.READONLY"
                target="idnoInputPopover"
                placement="right"
                triggers="hover focus"
                title="身分證字號查詢"
                content="依據身分證字號帶入技師資料。"
              ></b-popover>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 技師姓名 -->
            <i-form-group-check :label="$t('label.engrName') + '：'" :item="$v.chName">
              <div>{{ $v.chName.$model }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 審查主題 -->
            <i-form-group-check labelStar class="col-12" label-cols="2" content-cols="5" :label="$t('label.theme') + '：'" :item="$v.theme">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.theme.$model }}</div>
              <b-form-input v-else v-model="$v.theme.$model" :state="validateState($v.theme)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 審查內容 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="5"
              :label="$t('label.content') + '：'"
              :item="$v.content"
            >
              <b-form-textarea
                :plaintext="formStatus === FormStatusEnum.READONLY"
                v-model="$v.content.$model"
                rows="4"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 適用款次 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.law') + '：'" :item="$v.law">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ formOptionsFormatter(formOptions.law, $v.law.$model) }}</div>
              <b-form-select v-else v-model="$v.law.$model" :options="formOptions.law" :state="validateState($v.law)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 科別 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.subject') + '：'" :item="$v.subject">
              <div v-if="formStatus === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.subjectList, $v.subject.$model) }}
              </div>
              <b-form-select v-else v-model="$v.subject.$model" :options="formOptions.subjectList" :state="validateState($v.subject)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 積分 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.point') + '：'" :item="$v.point">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.point.$model }}</div>
              <b-form-input v-else v-model="$v.point.$model" :state="validateState($v.point)" type="number" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 核准說明 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="5" :label="$t('label.checkDesc') + '：'" :item="$v.checkDesc">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.checkDesc.$model }}</div>
              <b-form-input v-else v-model="$v.checkDesc.$model" trim lazy></b-form-input>
              <!-- <b-form-input v-else v-model="$v.checkDesc.$model" trim lazy class="web-ime"></b-form-input> -->
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="5" :label="$t('label.remark') + '：'" :item="$v.remark">
              <b-form-textarea
                :plaintext="formStatus === FormStatusEnum.READONLY"
                v-model="$v.remark.$model"
                rows="4"
                maxlength="2000"
                trim
                lazy
              ></b-form-textarea>
              <!-- class="web-ime" -->
            </i-form-group-check>
          </b-form-row>
        </div>

        <div class="text-center mb-2">
          <!-- 儲存 -->
          <i-button v-if="formStatus !== FormStatusEnum.READONLY" type="save" @click="submitForm" />
          <!-- 清除 -->
          <i-button v-if="formStatus !== FormStatusEnum.READONLY" type="x-circle" @click="pageReset" />
          <!-- 返回 -->
          <i-button type="arrow-left" @click="toQueryView" />
        </div>
        <i-switch-case
          class="mb-3"
          :criteria="criteria"
          api-url="/eng-engr-subjectpoints/criteria"
          @changeCase="createDefaultValue"
        ></i-switch-case>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import EngEngrSubjectpointService from './eng-engr-subjectpoint.service';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { useStore } from '@u3u/vue-hooks';
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
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';

interface propsType {
  engEngrSubjectpoint: IEngEngrSubjectpoint;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0104EditInfo',
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
    const engEngrSubjectpointService = new EngEngrSubjectpointService();

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
      applyDate: { required: required },
      contractorName: {},
      idno: { required: required, nationalId: nationalId, isEngrIdno: queryEngrByIdno },
      chName: {},
      theme: { required: required, maxLength: maxLength(200) },
      content: { required: required, maxLength: maxLength(2000) },
      law: { required: required },
      subject: { required: required },
      point: { required: required, between: between(0, 999) },
      checkDesc: { maxLength: maxLength(200) },
      remark: { maxLength: maxLength(2000) },
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      setFormOptions();
      createDefaultValue(unref(engEngrSubjectpoint));
      // loadWebImeScript(function () {
      //   createWebIme('.web-ime', { showWordType: false, onlyExt: true });
      // });
    });

    function setFormOptions() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    function createDefaultValue(data: IEngEngrSubjectpoint) {
      Object.assign(formDefault, data);
      setDate();
      reset();
      setSubjectList();
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

    function submitForm() {
      $bvModal.msgBoxConfirm('請再次確認資料是否填寫正確？').then((isOK: boolean) => {
        if (isOK) {
          checkValidity().then((isValid: boolean) => {
            if (isValid) {
              putForm();
            } else {
              $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
            }
          });
        }
      });
    }

    function putForm() {
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
    }

    function setFormBeforeSubmit() {
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

    return {
      FormStatusEnum,
      $v,
      pageReset,
      validateState,
      toQueryView,
      submitForm,
      putForm,
      formOptions,
      formOptionsFormatter,
      queryEngMember,
      createDefaultValue,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>

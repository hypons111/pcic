<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            
          </h5>
        </div>
        <div class="card-body">
          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 事務所/顧問公司 -->
            <i-form-group-check content-cols="4" :label="$t('label.consultantcompany') + '：'" :item="$v.consultantcompany">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.consultantcompany.$model }}</div>
              <b-form-input v-else v-model="$v.consultantcompany.$model" :state="validateState($v.consultantcompany)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT || formStatusRef === FormStatusEnum.CREATE">
            <!-- 統一編號 -->
            <i-form-group-check content-cols="4" :label="$t('label.compIdno') + '：'" :item="$v.compIdno">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.compIdno.$model }}</div>
              <b-form-input v-else v-model="$v.compIdno.$model" :state="validateState($v.compIdno)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 適用款項 -->
            <i-form-group-check content-cols="4" :label="$t('label.law') + '：'" :item="$v.law">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.law.$model }}</div>
              <b-form-input v-else v-model="$v.law.$model" :state="validateState($v.law)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 案名 -->
            <i-form-group-check content-cols="4" :label="$t('label.caseName') + '：'" :item="$v.caseName">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.caseName.$model }}</div>
              <b-form-input v-else v-model="$v.caseName.$model" :state="validateState($v.caseName)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 查核日期 -->
            <i-form-group-check content-cols="4" :label="$t('label.checkDate') + '：'" :item="$v.checkDate">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.checkDate.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.checkDate.$model"
                :state="validateState($v.checkDate)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 文號 -->
            <i-form-group-check content-cols="4" :label="$t('label.docNo') + '：'" :item="$v.docNo">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.docNo.$model }}</div>
              <b-form-input v-else v-model="$v.docNo.$model" :state="validateState($v.docNo)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <!-- <b-form-row v-if="role === RoleEnum.ADMIN"> -->
          <b-form-row>
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.remark') + '：'" :item="$v.remark">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.remark.$model"
                rows="4"
                maxlength="1000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 附件 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="9"
              :label="$t('label.appendix') + '：'"
              :item="$v.appendixFiles"
            >
              <b-form-file
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                multiple
                accept="image/jpeg, image/png, application/pdf"
                browse-text="選擇檔案"
                placeholder="未選擇任何檔案"
                drop-placeholder="拖曳檔案至此"
                v-model="$v.appendixFiles.$model"
                :state="validateState($v.appendixFiles)"
                @input="appendixSelected"
                class="col-9"
              >
                <template slot="file-name" slot-scope="{ names }">
                  <b-badge variant="info">{{ names[0] }}</b-badge>
                  <b-badge v-if="names.length > 1" variant="info" class="ml-1">{{ names[1] }}</b-badge>
                  <b-badge v-if="names.length > 2" variant="info" class="ml-1"> + {{ names.length - 2 }} 個檔案 </b-badge>
                </template>
              </b-form-file>
              <!-- <span v-if="formStatusRef !== FormStatusEnum.READONLY" class="remark-text col-3"
                >新申請者附：考試及格證書<br />補發者附&emsp;：考試及格證書及遺失切結書</span
              >
              <span v-if="formStatusRef !== FormStatusEnum.READONLY" class="remark-text col-12 p-0"
                >註：附件上傳以 jpg、pdf 或 PNG 格式為限，單筆檔案大小勿超過 2M</span
              > -->
              <b-table-simple
                v-if="formStatusRef === FormStatusEnum.READONLY && $v.appendixFilesInfo.$model.length > 0"
                class="mt-2"
                bordered
                small
              >
                <b-tr>
                  <b-th colspan="3" class="text-center">已上傳附件</b-th>
                </b-tr>
                <b-tr v-for="(admFile, index) of $v.appendixFilesInfo.$model" :key="admFile.id">
                  <b-th class="text-center">附件{{ index + 1 }}</b-th>
                  <b-td>&nbsp;{{ admFile.fileNameOri }}</b-td>
                  <b-td class="text-center col-3">
                    <i-button size="sm" type="file-earmark-arrow-down" @click="downloadAdmFile(admFile.id)"></i-button>
                  </b-td>
                </b-tr>
              </b-table-simple>
            </i-form-group-check>
            <b-table-simple
              v-if="formStatusRef !== FormStatusEnum.READONLY && $v.appendixFilesInfo.$model.length > 0"
              class="offset-2 col-9"
              bordered
              small
            >
              <b-tr>
                <b-th colspan="3" class="text-center">已上傳附件</b-th>
              </b-tr>
              <b-tr v-for="(admFile, index) of $v.appendixFilesInfo.$model" :key="admFile.id">
                <b-th class="text-center">附件{{ index + 1 }}</b-th>
                <b-td>&nbsp;{{ admFile.fileNameOri }}</b-td>
                <b-td class="text-center col-3">
                  <i-button size="sm" type="file-earmark-arrow-down" @click="downloadAdmFile(admFile.id)"></i-button>
                  <i-button size="sm" type="trash" @click="deleteAppendix(admFile.id)"></i-button>
                </b-td>
              </b-tr>
            </b-table-simple>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 備查狀態 -->
            <i-form-group-check content-cols="4" :label="$t('label.refeStatus') + '：'" :item="$v.status">
              <div>{{ $v.status.$model }}</div>
            </i-form-group-check>
            <!-- <i-form-group-check :label="$t('label.status') + '：'">
              <div>{{ formOptionsFormatter(formOptions.status, engEbillingCheckRef.status) }}</div>
            </i-form-group-check> -->
          </b-form-row>
          <b-form-row v-if="formStatusRef !== FormStatusEnum.READONLY">
            <b-col offset="8">
              <i-button type="file-earmark-plus" @click=""> </i-button>
            </b-col>
          </b-form-row>
        </div>
        
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            查核項目
          </h5>
        </div>
        <div class="card-body">
          <!-- <b-form-row v-if="role === RoleEnum.ADMIN">  -->
          <b-form-row>
            <!-- 查核案件 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.caseDesc') + '：'" :item="$v.caseDesc">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.caseDesc.$model"
                rows="4"
                maxlength="1000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <!-- <b-form-row v-if="role === RoleEnum.ADMIN"> -->
          <b-form-row>
            <!-- 缺失情形 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.defectDsec') + '：'" :item="$v.defectDsec">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.defectDsec.$model"
                rows="4"
                maxlength="1000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <!-- <b-form-row v-if="role === RoleEnum.ADMIN"> -->
          <b-form-row>
            <!-- 改善情形 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.improveDesc') + '：'" :item="$v.improveDesc">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.improveDesc.$model"
                rows="4"
                maxlength="1000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <!-- <b-form-row>
            <b-col offset="8">
              <i-button type="search" v-promise-btn="{ promise: dataPromise }" @click="handleQuery"></i-button>
              <i-button type="x-circle" @click="reset"></i-button>
            </b-col>
          </b-form-row> -->
        </div>

        <b-col offset="8">
          <!-- 儲存 -->
          <!-- <i-button v-if="formStatus !== FormStatusEnum.READONLY" type="save" @click="submitForm" /> -->
          <!-- 新增 -->
          <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" type="file-earmark-plus" @click="" />
          <!-- 清除 -->
          <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" type="x-circle" @click="reset"></i-button>
        </b-col>

        <div class="text-center mb-2">
          <!-- 儲存 -->
          <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" type="save" @click="submitForm(false)" />
          <!-- 核准 -->
          <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" type="clipboard-plus" @click="" />
          <!-- 退回 -->
          <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" type="clipboard-minus" @click="" />
          <!-- 返回 -->
          <i-button type="arrow-left" @click="toQueryView" />
        </div>
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
import { EngEbillingCheck } from '@/shared/model/eng/eng-ebilling-check.model';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';
import { EngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';
import {
  requiredIf,
  file,
} from '@/shared/validators';
import {
  RoleEnum,
} from '@/shared/enum';

interface propsType {
  engEngrSubjectpoint: IEngEngrSubjectpoint;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0203EditInfo',
  props: {
    engEngrSubjectpoint: {
      required: false,
      type: Object,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    role: {
      required: false,
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
    const engEbillingCheckRef = ref(new EngEbillingCheck());
    const notificationService = useNotification();
    const formStatusRef = ref(props.formStatus);
    const { engEngrSubjectpoint, formStatus, criteria } = toRefs(props);
    const isCheckRequired = ref(false);
    const isReload = ref(false);

    const formOptions = reactive({
      law: [],
      subject: [],
      subjectList: [],
      projectType: [],
      projectTypeList: [],
      status: [],
    });
    let formDefault = new EngEngrCertificate();
    // let formDefault = new EngEngrSubjectpoint();
    const form = reactive(Object.assign({}, formDefault));

    const formData = new FormData();
      form.appendixFiles.forEach(file => {
        formData.append('appendixFiles', file);
      });

    const rules = {
      consultantcompany: {},
      compIdno: {},
      law: {},
      caseName:{},
      checkDate: {},
      docNo: {},
      appendixFiles: {
        requiredIf: requiredIf(() => isCheckRequired.value && form.appendixFilesInfo.length == 0),
        file: file(2 * 1024 * 1024, ['image/jpeg', 'image/png', 'application/pdf']),
      },
      appendixFilesInfo: {},
      status: {},
      caseDesc: {},
      defectDsec: {},
      improveDesc: {},
      remark: { maxLength: maxLength(2000) },
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      setFormOptions();
      createDefaultValue(unref(engEngrSubjectpoint));
    });

    function appendixSelected(files: File[]) {}

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

    function deleteAppendix(id: number) {
      axios
        .delete(`/adm-files/${id}`)
        .then(() => {
          formDefault.appendixFilesInfo = formDefault.appendixFilesInfo.filter(item => item.id !== id);
          form.appendixFilesInfo = form.appendixFilesInfo.filter(item => item.id !== id);
        })
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

    const data = 
      {
        consultantcompany: '',
        compIdno: '',
        law: '',
        caseName:'',
        checkDate: '',
        docNo: '',
        status: '',
        caseDesc: '',
        defectDsec: '',
        improveDesc: '',
        remark: '',
      }

    return {
      FormStatusEnum,
      appendixSelected,
      requiredIf,
      $v,
      RoleEnum,
      pageReset,
      validateState,
      toQueryView,
      submitForm,
      formStatusRef ,
      //putForm,
      formOptions,
      formOptionsFormatter,
      queryEngMember,
      createDefaultValue,
      reset,
      data,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>

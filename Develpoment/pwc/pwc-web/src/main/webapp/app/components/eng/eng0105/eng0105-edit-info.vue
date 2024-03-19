<template>
  <b-container>
    <b-card no-body>
      <b-card-header>
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          技師懲戒作業
        </h5>
      </b-card-header>
      <b-card-body>
        <b-form-row>
          <!-- 案件類型 -->
          <i-form-group-check content-cols="4" :label="$t('label.caseType') + '：'" :item="$v.caseType">
            <b-form-select
              v-if="formStatusRef !== FormStatusEnum.READONLY"
              v-model="$v.caseType.$model"
              :options="formOptions.caseType"
              :state="validateState($v.caseType)"
            >
              <template #first>
                <option :value="undefined">-- 請選擇 --</option>
              </template>
            </b-form-select>
            <div v-else>{{ formOptionsFormatter(formOptions.caseType, $v.caseType.$model) }}</div>
          </i-form-group-check>
          <!-- 承辦人員 -->
          <i-form-group-check content-cols="4" :label="$t('label.contractorId') + '：'" :item="$v.contractorId">
            <b-form-select
              v-if="formStatusRef !== FormStatusEnum.READONLY"
              v-model="$v.contractorId.$model"
              :options="formOptions.contractorId"
              :state="validateState($v.contractorId)"
            >
              <template #first>
                <option :value="undefined">-- 請選擇 --</option>
              </template>
            </b-form-select>
            <div v-else>
              {{ formOptionsFormatter(formOptions.contractorId, $v.contractorId.$model) }}
            </div>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 申請日期 -->
          <i-form-group-check content-cols="4" :label="$t('label.applyDate') + '：'">
            <i-date-picker v-if="formStatusRef !== FormStatusEnum.READONLY" v-model="$v.applyDate.$model"></i-date-picker>
            <div v-else>{{ $v.applyDate.$model | dateToString }}</div>
          </i-form-group-check>
          <!-- 執行發文日期 -->
          <i-form-group-check content-cols="4" :label="$t('label.execDocDate') + '：'">
            <i-date-picker v-if="formStatusRef !== FormStatusEnum.READONLY" v-model="$v.execDocDate.$model"></i-date-picker>
            <div v-else>{{ $v.execDocDate.$model | dateToString }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 交付懲戒者 -->
          <i-form-group-check :label="$t('label.deliverObject') + '：'" :item="$v.deliverObject">
            <b-input-group v-if="formStatusRef !== FormStatusEnum.READONLY">
              <b-form-select
                class="col-4"
                v-model="$v.deliverObjectType.$model"
                :options="formOptions.deliverObjectType"
                :state="validateState($v.deliverObjectType)"
              >
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
              <b-form-input v-model="$v.deliverObject.$model" :state="validateState($v.deliverObject)" trim lazy></b-form-input>
            </b-input-group>
            <div v-else>
              {{ formOptionsFormatter(formOptions.deliverObjectType, $v.deliverObjectType.$model) }}&ensp;{{ $v.deliverObject.$model }}
            </div>
          </i-form-group-check>
          <!-- 執行發文字號 -->
          <i-form-group-check :label="$t('label.execDocNo') + '：'" :item="$v.execDocNo">
            <span>工程技字第&ensp;</span>
            <b-form-input
              v-if="formStatusRef !== FormStatusEnum.READONLY"
              v-model="$v.execDocNo.$model"
              class="col-6"
              :state="validateState($v.execDocNo)"
              trim
              lazy
            ></b-form-input>
            <div v-else>{{ $v.execDocNo.$model }}</div>
            <span>&ensp;號</span>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 案號 -->
          <i-form-group-check class="col-12" label-cols="2" content-cols="5" :label="$t('label.caseNo') + '：'" :item="$v.caseNo">
            <b-form-input
              v-if="formStatusRef !== FormStatusEnum.READONLY"
              v-model="$v.caseNo.$model"
              :state="validateState($v.caseNo)"
              trim
              lazy
            ></b-form-input>
            <div v-else>{{ $v.caseNo.$model }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 案由 -->
          <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.caseDesc') + '：'" :item="$v.caseDesc">
            <b-form-textarea
              :plaintext="formStatusRef === FormStatusEnum.READONLY"
              v-model="$v.caseDesc.$model"
              rows="4"
              maxlength="2000"
              trim
              lazy
            ></b-form-textarea>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 發生日期 -->
          <i-form-group-check content-cols="4" :label="$t('label.caseDate') + '：'">
            <i-date-picker v-if="formStatusRef !== FormStatusEnum.READONLY" v-model="$v.caseDate.$model"></i-date-picker>
            <div v-else>{{ $v.caseDate.$model | dateToString }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-card sub-title="技師資訊" sub-title-text-variant="secondary" class="mb-4">
          <hr />
          <b-form-row>
            <!-- 身分證字號 -->
            <i-form-group-check label-star content-cols="4" :label="$t('label.idno') + '：'" :item="$v.engrIdno">
              <b-form-input
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                v-model="$v.engrIdno.$model"
                :state="validateState($v.engrIdno)"
                trim
                lazy
              ></b-form-input>
              <div v-else>{{ $v.engrIdno.$model }}</div>
            </i-form-group-check>
            <!-- 技師姓名 -->
            <i-form-group-check label-star content-cols="4" :label="$t('label.engrName') + '：'" :item="$v.engrName">
              <b-form-input
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                v-model="$v.engrName.$model"
                :state="validateState($v.engrName)"
                trim
                lazy
              ></b-form-input>
              <div v-else>{{ $v.engrName.$model }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 科別 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="$t('label.subject') + '：'" :item="$v.subjectList">
              <b-form-checkbox-group
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                v-model="$v.subjectList.$model"
                :options="formOptions.subjectList"
                :state="validateState($v.subjectList)"
              ></b-form-checkbox-group>
              <div v-else>{{ $v.subjectList.$model }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 執業執照字號 -->
            <i-form-group-check label-star content-cols="6" :label="$t('label.engrLicenseNo') + '：'" :item="$v.licenseNo">
              <b-form-input
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                v-model="$v.licenseNo.$model"
                :state="validateState($v.licenseNo)"
                trim
                lazy
              ></b-form-input>
              <div v-else>{{ $v.licenseNo.$model }}</div>
            </i-form-group-check>
            <!-- 執照機構名稱 -->
            <i-form-group-check label-star content-cols="7" :label="$t('label.engrLicenseCompName') + '：'" :item="$v.licenseCompName">
              <b-form-input
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                v-model="$v.licenseCompName.$model"
                :state="validateState($v.licenseCompName)"
                trim
                lazy
              ></b-form-input>
              <div v-else>{{ $v.licenseCompName.$model }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 技師公會 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="$t('label.ognList') + '：'" :item="$v.ognList">
              <b-form-checkbox-group
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                v-model="$v.ognList.$model"
                :state="validateState($v.ognList)"
              >
                <div v-for="option in formOptions.ognList" :key="option.value" class="col-4 d-inline-block">
                  <b-form-checkbox :value="option.value">
                    {{ option.text }}
                  </b-form-checkbox>
                </div>
              </b-form-checkbox-group>
              <div v-else>{{ $v.ognList.$model }}</div>
            </i-form-group-check>
          </b-form-row>
        </b-card>

        <b-card sub-title="決議書資訊" sub-title-text-variant="secondary" class="mb-4">
          <hr />
          <b-form-row>
            <!-- 決議日期 -->
            <i-form-group-check label-star content-cols="4" label="決議日期：">
              <i-date-picker v-if="formStatusRef !== FormStatusEnum.READONLY" v-model="$v.resoDocDate.$model"></i-date-picker>
              <div v-else>{{ $v.resoDocDate.$model | dateToString }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 決議書發文日期 -->
            <i-form-group-check label-star content-cols="4" :label="$t('label.resoDocDate') + '：'">
              <i-date-picker v-if="formStatusRef !== FormStatusEnum.READONLY" v-model="$v.resoDocDate.$model"></i-date-picker>
              <div v-else>{{ $v.resoDocDate.$model | dateToString }}</div>
            </i-form-group-check>
            <!-- 決議書發文字號 -->
            <i-form-group-check label-star content-cols="6" :label="$t('label.resoDocNo') + '：'" :item="$v.resoDocNo">
              <b-form-input
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                v-model="$v.resoDocNo.$model"
                :state="validateState($v.resoDocNo)"
                trim
                lazy
              ></b-form-input>
              <div v-else>{{ $v.resoDocNo.$model }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 決議書主文 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.resoDesc') + '：'" :item="$v.resoDesc">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.resoDesc.$model"
                rows="4"
                maxlength="2000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>

            <!-- 決議書事實 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.resoFact') + '：'" :item="$v.resoFact">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.resoFact.$model"
                rows="4"
                maxlength="2000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>

            <!-- 決議書理由 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.resoReason') + '：'" :item="$v.resoReason">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.resoReason.$model"
                rows="4"
                maxlength="2000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 附件 -->
            <i-form-group-check
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
                placeholder="未選擇任何檔案（可選擇多個檔案）"
                drop-placeholder="拖曳檔案至此"
                v-model="$v.appendixFiles.$model"
                :state="validateState($v.appendixFiles)"
                class="col-6"
              >
                <template slot="file-name" slot-scope="{ names }">
                  <b-badge variant="info">{{ names[0] }}</b-badge>
                  <b-badge v-if="names.length > 1" variant="info" class="ml-1">{{ names[1] }}</b-badge>
                  <b-badge v-if="names.length > 2" variant="info" class="ml-1"> + {{ names.length - 2 }} 個檔案 </b-badge>
                </template>
              </b-form-file>
              <!-- 上傳 -->
              <b-button v-if="formStatusRef !== FormStatusEnum.READONLY" variant="info" class="ml-5">上傳</b-button>
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
                    <i-button size="sm" type="file-earmark-arrow-down"></i-button>
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
                  <i-button size="sm" type="file-earmark-arrow-down"></i-button>
                  <i-button size="sm" type="trash"></i-button>
                </b-td>
              </b-tr>
            </b-table-simple>
          </b-form-row>
        </b-card>

        <b-card sub-title="懲戒事由" sub-title-text-variant="secondary" class="mb-4">
          <hr />
          <eng0105-discipline-detail :disciplineDtls="eng0105.engEngrDisciplineDtlDTOList" :formStatus="formStatusRef">
          </eng0105-discipline-detail>
        </b-card>
      </b-card-body>

      <div class="text-center mb-2">
        <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" type="save"></i-button>
        <i-button type="clipboard-x"></i-button>
        <i-button type="arrow-left" @click="toQueryView"></i-button>
      </div>

      <i-switch-case
        class="mb-3"
        :criteria="criteria"
        api-url="/eng-engr-subjectpoints/criteria"
        @changeCase="createDefaultValue"
      ></i-switch-case>
    </b-card>
  </b-container>
</template>

<script lang="ts">
import axios from 'axios';
import {
  Ref,
  ref,
  reactive,
  computed,
  onActivated,
  watch,
  toRefs,
  toRef,
  defineComponent,
  unref,
  onMounted,
  provide,
} from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { email, required } from '@/shared/validators';
import { formatDate, formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import Eng0105DisciplineDetail from '@/components/eng/eng0105/eng0105-discipline-detail.vue';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { handleBack } from '@/router/router';
import { CompTypeEnum, FormStatusEnum, RoleEnum } from '@/shared/enum';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { useNotification } from '@/shared/notification';
import { EngCourse } from '@/shared/model/eng/eng-course.model';
import { EngTeacher, IEngTeacher } from '@/shared/model/eng/eng-teacher.model';
import { IEngCourseDtl } from '@/shared/model/eng/eng-course-dtl.model';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formOptionsFormatter } from '@/shared/formatter/common';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { count } from 'console';

interface propsType {
  eng0105;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0105EditInfo',
  components: { Eng0105DisciplineDetail, ISwitchCase },
  props: {
    eng0105: {
      required: false,
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
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();

    const { eng0105 } = toRefs(props);
    const formStatusRef = ref(props.formStatus);
    const criteriaRef = ref(props.criteria);
    const $bvModal = useBvModal();
    const notificationService = useNotification();

    let isReload = false;
    const isCheckRequired = ref(false); // 儲存 = false; 送出 = true

    const engCourseDetails: Ref<IEngCourseDtl[]> = ref([]);

    const formOptions = reactive({
      disciplineType: [],
      caseType: [],
      breakLawList: [],
      deliverObjectType: [],
      subjectList: [
        { value: '01', text: '土木工程' },
        { value: '02', text: '大地工程' },
      ],
      ognList: [
        { value: '01', text: '台北市土木技師公會' },
        { value: '02', text: '台南市土木技師公會' },
        { value: '03', text: '社團法人臺中市土木技師公會' },
        { value: '04', text: '社團法人臺灣省土木技師公會' },
        { value: '05', text: '桃園市土木技師公會' },
      ],
    });

    const formDefault = {
      caseType: undefined,
      deliverObjectType: undefined,
      deliverObject: undefined,
      applyDate: undefined,
      contractorId: undefined,
      execDocDate: undefined,
      execDocNo: undefined,
      caseNo: undefined,
      caseDesc: undefined,
      caseDate: undefined,
      engrIdno: undefined,
      engrName: undefined,
      subjectList: undefined,
      licenseNo: undefined,
      licenseCompName: undefined,
      ognList: undefined,
      resoDocDate: undefined,
      resoDocNo: undefined,
      resoDesc: undefined,
      resoFact: undefined,
      resoReason: undefined,
      breakLawList: undefined,
      disciplineType: undefined,
      count: undefined,
      execDateStart: undefined,
      execDateEnd: undefined,

      appendixFiles: [],
      appendixFilesInfo: [],
    };
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      caseType: {},
      deliverObjectType: {},
      deliverObject: {},
      applyDate: {},
      contractorId: {},
      execDocDate: {},
      execDocNo: {},
      caseNo: {},
      caseDesc: {},
      caseDate: {},
      engrIdno: {},
      engrName: {},
      subjectList: {},
      licenseNo: {},
      licenseCompName: {},
      ognList: {},
      resoDocDate: {},
      resoDocNo: {},
      resoDesc: {},
      resoFact: {},
      resoReason: {},
      breakLawList: {},
      disciplineType: {},
      count: {},
      execDateStart: {},
      execDateEnd: {},

      appendixFiles: {},
      appendixFilesInfo: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      setFormOptions();
      createDefaultValue(unref(eng0105));
    });

    function setFormOptions() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    function createDefaultValue(data) {
      Object.assign(formDefault, data);
      setDate();
      reset();
    }

    function setDate() {
      formDefault.execDocDate = parseToDate(formDefault.execDocDate, '-');
    }

    function submitForm(isCheckReq: boolean) {
      isCheckRequired.value = isCheckReq;
      $v.value.$reset(); // 先清除欄位$dirty狀態，才能依據isCheckRequired(是否驗證必填)重新驗證

      // 不加this.$nextTick，checkValidity() 會比上方 $v.value.$reset() 先執行!!!
      this.$nextTick(() => {
        checkValidity().then((isValid: boolean) => {
          if (isValid) {
            if (isCheckReq) {
              $bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
                if (isOK) {
                  putForm();
                }
              });
            } else {
              putForm();
            }
          } else {
            $bvModal.msgBoxOk(isCheckReq ? '欄位尚未填寫完畢，請於輸入完畢後再行送出。' : '欄位尚未填寫完整，請輸入完整或不輸入再行儲存。');
          }
        });
      });
    }

    async function putForm() {
      setFormBeforeSubmit();

      // if (formStatusRef.value === FormStatusEnum.EDIT) {
      //   engCourseService
      //     .update(formData)
      //     .then(eng0105 => {
      //       createDefaultValue(eng0105);
      //       isReload = true;
      //     })
      //     .catch(notificationErrorHandler(notificationService));
      // } else {
      //   engCourseService
      //     .create(formData)
      //     .then(eng0105 => {
      //       // 1.新增完繼續按儲存或送出才會變修改 2.要在createDefaultValue上面才不會改到applyItems
      //       formStatusRef.value = FormStatusEnum.EDIT;
      //       createDefaultValue(eng0105);
      //       isReload = true;
      //     })
      //     .catch(notificationErrorHandler(notificationService));
      // }
    }

    function setFormBeforeSubmit() {}

    function changeFormStatus(formStatus: FormStatusEnum) {
      formStatusRef.value = formStatus;
      reset();
    }

    function toQueryView() {
      handleBack({ isReload: isReload, isNotKeepAlive: true });
      isReload = false;
    }

    function pageReset() {
      reset();
    }

    return {
      FormStatusEnum,
      formStatusRef,
      form,
      engCourseDetails,
      formOptions,
      $v,
      validateState,
      toQueryView,
      formatDate,
      formOptionsFormatter,
      createDefaultValue,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>

<style>
</style>

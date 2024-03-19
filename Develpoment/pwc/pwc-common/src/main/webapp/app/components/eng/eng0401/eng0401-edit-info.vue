<template>
  <b-container>
    <b-card no-body>
      <b-card-header>
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          課程管理
        </h5>
      </b-card-header>
      <b-card-body>
        <b-form-row>
          <!-- 所屬單位 -->
          <i-form-group-check labelStar :label="$t('label.belongUnit') + '：'" :item="$v.compName">
            <b-input-group>
              <b-form-select
                class="col-4"
                v-model="$v.compType.$model"
                :options="formOptions.compType"
                :disabled="formStatusRef !== FormStatusEnum.EDIT"
              >
              </b-form-select>
              <b-form-input
                :disabled="formStatusRef !== FormStatusEnum.EDIT"
                v-model="$v.compName.$model"
                :state="validateState($v.compName)"
                trim
                lazy
              ></b-form-input>
            </b-input-group>
          </i-form-group-check>
          <!-- 主辦單位 -->
          <i-form-group-check labelStar :label="$t('label.adminCompName') + '：'" :item="$v.adminCompName">
            <b-form-input
              v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="$v.adminCompName.$model"
              :state="validateState($v.adminCompName)"
              trim
              lazy
            ></b-form-input>
            <div v-else>{{ $v.adminCompName.$model }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 課程名稱 -->
          <i-form-group-check :label="$t('label.courseName') + '：'" :labelStar="true" :item="$v.courseName">
            <b-form-input
              v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="$v.courseName.$model"
              :state="validateState($v.courseName)"
              trim
              lazy
            ></b-form-input>
            <div v-else>{{ $v.courseName.$model }}</div>
            <span v-if="formStatusRef === FormStatusEnum.EDIT" class="remark-text"
              >如有採用視訊或網路教學，請於其後加註「本課程有採用視訊或網路教學」</span
            >
          </i-form-group-check>
          <!-- 申請日期 -->
          <i-form-group-check content-cols="4" :label="$t('label.applyDate') + '：'" :item="$v.applyDate">
            <i-date-picker v-if="formStatusRef === FormStatusEnum.EDIT" v-model="$v.applyDate.$model"> </i-date-picker>
            <div v-else>{{ $v.applyDate.$model | dateToString }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 課程日期 -->
          <i-form-group-check labelStar :label="$t('label.courseDate') + '：'" :dual1="$v.dateStart" :dual2="$v.dateEnd">
            <i-dual-date-picker
              v-if="formStatusRef === FormStatusEnum.EDIT"
              :dual1.sync="$v.dateStart.$model"
              :dual2.sync="$v.dateEnd.$model"
            ></i-dual-date-picker>
            <div v-else>{{ $v.dateStart.$model | dateToString }} ~ {{ $v.dateEnd.$model | dateToString }}</div>
          </i-form-group-check>
          <!-- 上傳截止時間 -->
          <i-form-group-check content-cols="4" :label="$t('label.uploadDeadline') + '：'">
            <i-date-picker v-if="formStatusRef === FormStatusEnum.EDIT" v-model="$v.uploadDeadline.$model"></i-date-picker>
            <div v-else>{{ $v.uploadDeadline.$model | dateToString }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 上課地點 -->
          <i-form-group-check labelStar class="col-12" label-cols="2" content-cols="6" :label="$t('label.place') + '：'" :item="$v.place">
            <b-form-input
              v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="$v.place.$model"
              :state="validateState($v.place)"
              trim
              lazy
            ></b-form-input>
            <div v-else>{{ $v.place.$model }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 聯絡人姓名 -->
          <i-form-group-check content-cols="6" :label="$t('label.contactName') + '：'" :item="$v.contactPerson">
            <b-form-input
              v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="$v.contactPerson.$model"
              :state="validateState($v.contactPerson)"
              trim
              lazy
            ></b-form-input>
            <div v-else>{{ $v.contactPerson.$model }}</div>
          </i-form-group-check>
          <!-- 聯絡人電話 -->
          <i-form-group-check content-cols="6" :label="$t('label.contactTel') + '：'" :item="$v.contactTel">
            <b-form-input
              v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="$v.contactTel.$model"
              :state="validateState($v.contactTel)"
              trim
              lazy
            ></b-form-input>
            <div v-else>{{ $v.contactTel.$model }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 電子信箱 -->
          <i-form-group-check content-cols="6" :label="$t('label.email') + '：'" :item="$v.contactEmail">
            <b-form-input
              v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="$v.contactEmail.$model"
              :state="validateState($v.contactEmail)"
              trim
              lazy
            >
            </b-form-input>
            <div v-else>{{ $v.contactEmail.$model }}</div>
          </i-form-group-check>
          <!-- 活動網址 -->
          <i-form-group-check :label="$t('label.webUrl') + '：'" :item="$v.webUrl">
            <b-form-input
              v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="$v.webUrl.$model"
              :state="validateState($v.webUrl)"
              trim
              lazy
            ></b-form-input>
            <div v-else>{{ $v.webUrl.$model }}</div>
          </i-form-group-check>
        </b-form-row>

        <!-- 備註 -->
        <b-form-row>
          <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.remark') + '：'" :item="$v.remark">
            <b-form-textarea
              :plaintext="formStatusRef !== FormStatusEnum.EDIT"
              v-model="$v.remark.$model"
              rows="4"
              maxlength="1200"
              trim
              lazy
            ></b-form-textarea>
            <span v-if="formStatusRef === FormStatusEnum.EDIT" class="remark-text"
              >如有採用視訊或網路教學，請於備註欄說明本課程之學員身分確認機制及簽到/退管理方式，相關資料並應建檔留存。</span
            >
          </i-form-group-check>
        </b-form-row>

        <!-- 審核說明 -->
        <b-form-row>
          <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.reviewDesc') + '：'" :item="$v.reviewDesc">
            <b-form-textarea
              :plaintext="formStatusRef === FormStatusEnum.READONLY"
              v-model="$v.reviewDesc.$model"
              rows="4"
              maxlength="2000"
              trim
              lazy
            ></b-form-textarea>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 核備文號 -->
          <i-form-group-check :label="$t('label.docNo') + '：'">
            <div>{{ $v.docNo.$model }}</div>
          </i-form-group-check>
          <!-- 審核狀態 -->
          <i-form-group-check :label="$t('label.status') + '：'">
            <div>{{ formOptionsFormatter(formOptions.status, $v.status.$model) }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 初審 -->
          <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.checkStaff1') + '：'"> </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 複審 -->
          <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.checkStaff2') + '：'"> </i-form-group-check>
        </b-form-row>

        <b-card title="講師資訊" title-tag="h5" class="mb-4">
          <hr />
          <eng0401-teacher :formStatus="formStatusRef" />
        </b-card>

        <b-card title="課程資訊" title-tag="h5">
          <hr />
          <eng0401-course-detail :engCourseDtls="engCourseDtls" :engCourseDtlTeachers="engCourseDtlTeachers" :formStatus="formStatusRef">
          </eng0401-course-detail>
        </b-card>
      </b-card-body>

      <div class="text-center mb-2">
        <i-button type="clipboard-x"></i-button>
        <i-button type="send-check"></i-button>
        <i-button type="arrow-left" @click="toQueryView"></i-button>
      </div>

      <i-switch-case class="mb-3" :criteria="criteria" api-url="/eng-courses/criteria-jpa" @changeCase="handleCaseChanged"></i-switch-case>
    </b-card>

    <!-- 送出data的彈跳視窗 -->
    <b-modal id="pretend-send-data-modal" hide-footer>
      <div class="d-block text-center">
        send data!!
        <h3>form:</h3>
        {{ form }}
        <br />
        <h3>engCourseDtls:</h3>
        {{ engCourseDtls }}
      </div>
      <b-button class="mt-3" block @click="$bvModal.hide('pretend-send-data-modal')">Close Me</b-button>
    </b-modal>
  </b-container>
</template>

<script lang="ts">
import axios from 'axios';
import { Ref, ref, reactive, toRefs, defineComponent, unref, onMounted, provide, onActivated, watch } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { email, required } from '@/shared/validators';
import { formatDate, formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import Eng0401Teacher from '@/components/eng/eng0401/eng0401-teacher.vue';
import Eng0401CourseDetail from '@/components/eng/eng0401/eng0401-courseDetail.vue';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { handleBack } from '@/router/router';
import { IEng0401 } from '@/shared/model/eng/eng0401.model';
import { CompTypeEnum, FormStatusEnum, RoleEnum } from '@/shared/enum';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import EngCourseService from './eng-course.service';
import { useNotification } from '@/shared/notification';
import { EngCourse, IEngCourse } from '@/shared/model/eng/eng-course.model';
import { EngTeacher, IEngTeacher } from '@/shared/model/eng/eng-teacher.model';
import { IEngCourseDtl } from '@/shared/model/eng/eng-course-dtl.model';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formOptionsFormatter } from '@/shared/formatter/common';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { IEngCourseDtlTeacher } from '@/shared/model/eng/eng-course-dtl-teacher.model';

interface propsType {
  eng0401: IEng0401;
  formStatus: FormStatusEnum;
  role: RoleEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0401EditInfo',
  components: { Eng0401Teacher, Eng0401CourseDetail, ISwitchCase },
  props: {
    eng0401: {
      required: false,
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
  setup(props) {
    const { eng0401 } = toRefs(props);
    const formStatusRef = ref(props.formStatus);
    const $bvModal = useBvModal();
    const notificationService = useNotification();
    const admSysCodeService = new AdmSysCodeService();
    const engCourseService = new EngCourseService();

    let isReload = false;
    const isCheckRequired = ref(false); // 儲存 = false; 送出 = true

    const engCourseDtls: Ref<IEngCourseDtl[]> = ref([]);
    const engCourseDtlTeachers: Ref<IEngCourseDtlTeacher[]> = ref([]);
    const engTeachers: Ref<IEngTeacher[]> = ref([]);

    const formOptions = reactive({
      status: [],
      compType: [],
    });

    let formDefault = new EngCourse();
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      engCourseId: {},
      applyDate: {},
      compId: {},
      adminCompName: {},
      courseName: {},
      dateStart: {},
      dateEnd: {},
      uploadDeadline: {},
      place: {},
      contactPerson: {},
      contactTel: {},
      contactEmail: {},
      status: {},
      reviewStaff: {},
      reviewTime: {},
      reviewDesc: {},
      webUrl: {},
      docNo: {},
      isNeedCheck: {},
      isMedia: {},
      remark: {},
      updateTime: {},
      updateUser: {},
      createTime: {},
      createUser: {},
      transferFrom: {},
      transferTime: {},
      compType: {},
      compName: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    provide('engTeachers', engTeachers);
    provide('updateEngTeachers', updateEngTeachers);

    function updateEngTeachers(data: IEngTeacher[]) {
      unref(engTeachers).splice(0, unref(engTeachers).length, ...data);
    }

    onMounted(() => {
      getAllSysCode();
      createDefaultValue(unref(eng0401));
    });

    onActivated(() => {
      createDefaultValue(unref(eng0401));
    });

    function getAllSysCode() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.compType = formOptions.compType.filter(
        item =>
          item.value !== CompTypeEnum.OFFICE && item.value !== CompTypeEnum.DISCIPLINE_UNIT && item.value !== CompTypeEnum.ARCHITECT_OFFICE
      );
    }

    function createDefaultValue(data: IEng0401) {
      console.log('ENG0401：', data);
      Object.assign(formDefault, data.engCourseDTO);
      setDate();
      reset();
      engCourseDtls.value = data.engCourseDtlDTOList.slice();
      engCourseDtlTeachers.value = data.engCourseDtlTeacherDTOList.slice();
      engTeachers.value = getTeachers(data);
    }

    function setDate() {
      formDefault.applyDate = parseToDate(formDefault.applyDate, '-');
      formDefault.dateStart = parseToDate(formDefault.dateStart, '-');
      formDefault.dateEnd = parseToDate(formDefault.dateEnd, '-');
      formDefault.uploadDeadline = parseToDate(formDefault.uploadDeadline, '-');
    }

    function getTeachers(data: IEng0401): IEngTeacher[] {
      const teachers: IEngTeacher[] = [];
      const set = new Set();
      data.engCourseDtlTeacherDTOList
        .filter(item =>
          // !set.has(item.engTeacherId.concat(',', item.teacherName)) ? set.add(item.engTeacherId.concat(',', item.teacherName)) : false
          !set.has(item.engTeacherId) ? set.add(item.engTeacherId) : false
        )
        .forEach(({ engTeacherId, teacherName, teacherHighestEdu, teacherJob, teacherExperience }) => {
          const engTeacher = new EngTeacher();
          engTeacher.engTeacherId = engTeacherId;
          engTeacher.teacherName = teacherName;
          engTeacher.teacherHighestEdu = teacherHighestEdu;
          engTeacher.teacherJob = teacherJob;
          engTeacher.teacherExperience = teacherExperience;
          teachers.push(engTeacher);
        });
      return teachers;
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
      //     .then(eng0401 => {
      //       createDefaultValue(eng0401);
      //       isReload = true;
      //     })
      //     .catch(notificationErrorHandler(notificationService));
      // } else {
      //   engCourseService
      //     .create(formData)
      //     .then(eng0401 => {
      //       // 1.新增完繼續按儲存或送出才會變修改 2.要在createDefaultValue上面才不會改到applyItems
      //       formStatusRef.value = FormStatusEnum.EDIT;
      //       createDefaultValue(eng0401);
      //       isReload = true;
      //     })
      //     .catch(notificationErrorHandler(notificationService));
      // }
    }

    function setFormBeforeSubmit() {
      // READONLY 可以作廢會修改資料，所以也要擋
      if (formStatusRef.value !== FormStatusEnum.EDIT && formStatusRef.value !== FormStatusEnum.READONLY) {
        form.reviewStaff = null;
        form.reviewTime = null;
        form.reviewDesc = null;
        form.transferTime = null;
        form.transferFrom = null;
      }
    }

    function changeFormStatus(formStatus: FormStatusEnum) {
      formStatusRef.value = formStatus;
      reset();
    }

    function toQueryView() {
      handleBack({ isReload: isReload, criteria: props.criteria, isNotKeepAlive: true });
      isReload = false;
    }

    function pageReset() {
      reset();
    }

    //在改變子組件的時候透過自定義方法更改parent的資料
    // function changeTeacherData(data: any, index: number, flag: string) {
    //   switch (flag) {
    //     case 'edit':
    //       engTeachers.value.splice(index, 1, data);
    //       break;
    //     case 'add':
    //       engTeachers.value.push(data);
    //       break;
    //     case 'delete':
    //       engTeachers.value.splice(index, 1);
    //       break;
    //     default:
    //       break;
    //   }
    // }
    // function changeCourseDetailData(data: any, index: number, flag: string) {
    //   switch (flag) {
    //     case 'edit':
    //       engCourseDtls.value.splice(index, 1, data);
    //       break;
    //     case 'add':
    //       engCourseDtls.value.push(data);
    //       break;
    //     case 'delete':
    //       engCourseDtls.value.splice(index, 1);
    //       engCourseDtls.value.forEach((element, index) => {
    //         // element.itemNo = index + 1;
    //       });
    //       break;
    //   }
    // }

    function changeStatusToEdit() {
      window.scroll({ top: 0, left: 0, behavior: 'smooth' });
      formStatusRef.value = FormStatusEnum.EDIT;
    }
    function changeStatusToInfo() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          window.scroll({ top: 0, left: 0, behavior: 'smooth' });
          formStatusRef.value = FormStatusEnum.READONLY;
        }
      });
    }

    //20220111 改成新的寫法
    // watch(
    //   course_H,
    //   () => {
    //     if (course_H.value) {
    //       Object.assign(courseH_Default, course_H.value);
    //       // console.log('IM', courseH_Default);
    //       initOriginTeacherAndCourseData('456');
    //       reset();
    //     }
    //   },
    //   { immediate: true }
    // );
    // watch(
    //   formStatusRefOrigin,
    //   () => {
    //     // console.log(formStatusRefOrigin.value);
    //     switch (formStatusRefOrigin.value) {
    //       case FormStatusEnum.INFO:
    //         formStatusRef.value = FormStatusEnum.INFO;
    //         initOriginTeacherAndCourseData('123');
    //         break;
    //       case FormStatusEnum.MODIFY:
    //         formStatusRef.value = FormStatusEnum.MODIFY;
    //         initOriginTeacherAndCourseData('123');
    //         break;
    //       case FormStatusEnum.CREATE:
    //         formStatusRef.value = FormStatusEnum.CREATE;
    //         clearDataFromKeepAlive();
    //         break;
    //     }
    //   },
    //   { immediate: true }
    // );

    function handleCaseChanged(data: IEngCourse) {
      engCourseService.find(data.engCourseId).then(createDefaultValue).catch(notificationErrorHandler(notificationService));
    }

    return {
      FormStatusEnum,
      formStatusRef,
      form,
      engCourseDtls,
      engCourseDtlTeachers,
      formOptions,
      $v,
      // changeCourseDetailData,
      validateState,
      // changeTeacherData,
      toQueryView,
      changeStatusToEdit,
      changeStatusToInfo,
      formatDate,
      formOptionsFormatter,
      handleCaseChanged,
      engTeachers,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>

<style>
</style>

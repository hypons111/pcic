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
          <!-- <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT"> -->
          <b-form-row>
            <!-- 課程 -->
            <i-form-group-check content-cols="4" :label="$t('label.courseName') + '：'" :item="$v.courseName">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.courseName.$model }}</div> -->
              {{ data2.courseName }}
              <!-- <b-form-input v-else v-model="$v.courseName.$model" :state="validateState($v.courseName)" trim lazy> </b-form-input> -->
            </i-form-group-check>
            <!-- 日期 -->
            <i-form-group-check content-cols="4" :label="$t('label.dateStart') + '：'" :item="$v.dateStart">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.dateStart.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.dateStart.$model"
                :state="validateState($v.dateStart)"
                placeholder="yyy/MM/dd"
              ></i-date-picker> -->
              {{ data2.dateStart }}
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <b-col offset="6">
              <!--上課名單數:{{ $v.consultantcompany.$model }}已審核:{{ $v.consultantcompany.$model }}未審核:{{ $v.consultantcompany.$model }}-->
              上課名單數:111已審核:111未審核:0
            </b-col>
          </b-form-row>

          <b-form-row>
            <i-form-group-check>
            Excel檔:<i-button type="upload2" @click="handleQueryBtn" /><a href="/ab">下載範例</a><i-button type="file-earmark-arrow-upclass" @click="handleQueryBtn" />
            </i-form-group-check>

            <!-- 延長後上傳期限 -->
            <i-form-group-check content-cols="4" :label="$t('label.uploadDeadlinelong') + '：'" :item="$v.uploadDeadlinelong">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.uploadDeadlinelong.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.uploadDeadlinelong.$model"
                :state="validateState($v.uploadDeadlinelong)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
            <i-button type="uploadlong" @click="handleQueryBtn" />
          </b-form-row>
        </div>
        
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            查詢條件
          </h5>
        </div>
        <div class="card-body">
          <!-- <b-form-row v-if="role === RoleEnum.ADMIN">  -->
          <b-form-row>
            <!-- 主題 -->
            <i-form-group-check content-cols="4" :label="$t('label.title') + '：'" :item="$v.courseName1">
              <div v-if="formStatus === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.courseNameList, $v.courseName1.$model) }}
              </div>
              <b-form-select v-else v-model="$v.courseName1.$model" :options="formOptions.courseNameList" :state="validateState($v.courseName1)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 姓名/身分證字號 -->
            <i-form-group-check content-cols="4" :label="$t('label.Nameidno') + '：'" :item="$v.chName">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.chName.$model }}</div>
              <b-form-input v-else v-model="$v.chName.$model" :state="validateState($v.chName)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <!-- <b-form-row v-if="role === RoleEnum.ADMIN"> -->
          <b-form-row>
            <!-- 異常 -->
            <i-form-group-check content-cols="4" :label="$t('label.errType') + '：'" :item="$v.errType">
              <div v-if="formStatus === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.errTypeList, $v.errType.$model) }}
              </div>
              <b-form-select v-else v-model="$v.errType.$model" :options="formOptions.errTypeList" :state="validateState($v.errType)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
        </div>

        <div class="text-center mb-2">
          <i-button type="search" @click="handleQueryBtn"></i-button>
          <i-button type="x-circle" @click="reset"></i-button>
        </div>
      </div>
    </section>
    <section class="container mt-2" v-show="queryStatus">
      <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged" 
      >
      <!--</i-table>從i-table 傳來的changePagination 塞進 handlePaginationChanged-->
        <template #cell(applyMode)="row">
          {{ formOptionsFormatter(formOptions.applyType, row.item.applyType) }}<br />
          {{ formOptionsFormatter(formOptions.applyItemsList, row.item.applyItemsList) }}<br />
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import i18n from '@/shared/i18n';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
// import EngEngrSubjectpointService from './eng-engr-subjectpoint.service';
import { useBvModal } from '@/shared/modal';
import { useNotification } from '@/shared/notification';
import { useValidation, validateState } from '@/shared/form';
import { handleBack } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { reactive, ref, toRefs, onMounted, defineComponent, unref, onActivated, toRef } from '@vue/composition-api';
import { required, maxLength, nationalId, between, queryEngrByIdno } from '@/shared/validators';
import { ApplyItemEnum, FormStatusEnum, MemberTypeEnum, StatusEnum } from '@/shared/enum';
import { IEngEngrSubjectpoint, EngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import { Pagination } from '@/shared/model/pagination.model';
import { useStore } from '@u3u/vue-hooks';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';

interface propsType {
  engEngrSubjectpoint: IEngEngrSubjectpoint;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0403EditInfo',
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
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  components: {
    ISwitchCase,
  },
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    //const engEngrSubjectpointService = new EngEngrSubjectpointService();
    const iTableComponent = ref(null);
    const store = useStore();
    const $bvModal = useBvModal();
    const notificationService = useNotification();
    const formStatusRef = ref(props.formStatus);
    const { engEngrSubjectpoint, formStatus, criteria } = toRefs(props);

    const isReload = ref(false);

    const queryStatus = ref(false);

    const formOptions = reactive({
      law: [],
      subject: [],
      subjectList: [],
      projectType: [],
      projectTypeList: [],
      status: [],
    });

    let formDefault = new EngEngrSubjectpoint();
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      courseName: {},
      dateStart: {},
      consultantcompany: {},
      uploadDeadlinelong: {},
      courseName1: {},
      chName: {},
      errType: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const table = reactive({
      fields_backend: [
        {
          // 技師姓名
          key: 'engrName',
          label: i18n.t('label.engrName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 異常
          key: 'errType',
          label: i18n.t('label.errType'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 異常狀況
          key: 'errTypeStatus',
          label: i18n.t('label.errTypeStatus'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.subject, value),
        },
        {
          // 簽到時間
          key: 'checkin',
          label: i18n.t('label.checkin'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 簽退時間
          key: 'checkout',
          label: i18n.t('label.checkout'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 技師科別
          key: 'engrSubject',
          label: i18n.t('label.engrSubject'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 審核狀態
          key: 'reviewStatus',
          label: i18n.t('label.reviewStatus'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
      ],
      data1: undefined,
      totalItems: undefined,
    });

    onMounted(() => {
      setFormOptions();
      createDefaultValue(unref(engEngrSubjectpoint));
    });

    const isReloadProp = toRef(props, 'isReload');

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
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

    const handleQueryBtn = () => {
      // if (iTableComponent.value) {
      iTableComponent.value.state.pagination.currentPage = 1;
      iTableComponent.value.emitPagination();
      // Object.assign(form, iTableComponent.value.pagination);
      // }
      // handleQuery();
    };

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
    
    const handleQuery = () => {
      const now = new Date();
      const offset = now.getTimezoneOffset();

      //驗證
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data1 = undefined;
          queryStatus.value = true;
          // axios
          //   // .post('/cms-comps/criteria', form)           //前端分頁
          //   .post('/eng-engr-certificates/criteria-jpa', form) //後端分頁
          //   .then(({ data }) => {
          //     // table.data = data.slice(0, data.length);   //前端分頁
          //     // table.totalItems = data.length;            //前端分頁
          //     table.data = data.content.slice(0, data.content.length); //後端分頁
          //     table.totalItems = data.totalElements; //後端分頁
          //   })
          //   .catch(notificationErrorHandler(notificationService));
        }
      });
    };

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
    const data2 = 
      {
        courseName: '土壤與地下水',
        dateStart: '110/2/1',
        // consultantcompany: '',
        uploadDeadlinelong: '',
        courseName1: '土壤與地下水1',
        chName: '',
        errType: '',
        // engrName: '方定成',
        // errType: '',
        // errTypeStatus: '',
        // checkin: '',
        // checkout: '',
        // engrSubject: '',
        // reviewStatus: '',
      }

    return {
      FormStatusEnum,
      handleQueryBtn,
      reset,
      formStatusRef,
      queryStatus,
      table,
      $v,
      pageReset,
      validateState,
      toQueryView,
      submitForm,
      //putForm,
      formOptions,
      formOptionsFormatter,
      queryEngMember,
      handlePaginationChanged,
      createDefaultValue,
      data2,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>

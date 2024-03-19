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
            <!-- 技師姓名 -->
            <i-form-group-check content-cols="4" :label="$t('label.engrName') + '：'" :item="$v.chName">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.chName.$model }}</div> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data2.chName }}</div>
              <!-- <b-form-input v-else v-model="$v.chName.$model" :state="validateState($v.chName)" trim lazy> </b-form-input> -->
              <b-form-input v-else v-model="data2.chName" :state="validateState($v.chName)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 身分證 -->
            <i-form-group-check content-cols="4" :label="$t('label.idno') + '：'" :item="$v.idno">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.idno.$model }}</div> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data2.idno }}</div>
              <!-- <b-form-input v-else v-model="$v.idno.$model" :state="validateState($v.idno)" trim lazy> </b-form-input> -->
              <b-form-input v-else v-model="data2.idno" :state="validateState($v.idno)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 上課日期 -->
            <i-form-group-check content-cols="4" :label="$t('label.courseDate') + '：'" :item="$v.courseDate">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.courseDate.$model | dateToString }}</div> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data2.courseDate }}</div>
              <!-- <i-date-picker
                v-else
                v-model="$v.courseDate.$model"
                :state="validateState($v.courseDate)"
                placeholder="yyy/MM/dd"
              ></i-date-picker> -->
              <i-date-picker
                v-else
                v-model="data2.courseDate"
                :state="validateState($v.courseDate)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
            <!-- 實得積分 -->
            <i-form-group-check content-cols="4" :label="$t('label.realpoint') + '：'" :item="$v.realpoint">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.realpoint.$model }}</div> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data2.realpoint }}</div>
              <!-- <b-form-input v-else v-model="$v.realpoint.$model" :state="validateState($v.realpoint)" trim lazy> </b-form-input> -->
              <b-form-input v-else v-model="data2.realpoint" :state="validateState($v.realpoint)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 應得積分 -->
            <i-form-group-check content-cols="4" :label="$t('label.shouldpoint') + '：'" :item="$v.shouldpoint">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.shouldpoint.$model }}</div> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data2.shouldpoint }}</div>
              <!-- <b-form-input v-else v-model="$v.shouldpoint.$model" :state="validateState($v.shouldpoint)" trim lazy> </b-form-input> -->
              <b-form-input v-else v-model="data2.shouldpoint" :state="validateState($v.shouldpoint)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 審查狀態 -->
            <i-form-group-check
              class="col-8"
              label-cols="3"
              content-cols="2"
              :label="$t('label.reviewStatus') + '：'"
              :item="$v.status"
            >
            <!-- v-model="$v.status.$model" -->
              <b-form-select
                v-model="data2.status"
                :options="formOptions.status"
                :state="validateState($v.status)"
              ></b-form-select>
            </i-form-group-check>
          </b-form-row>
        </div>
        <div class="text-center mb-2">
          <!-- 返回 -->
          <i-button type="arrow-left" @click="toQueryView" />
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

        <template #cell(subject)="{ item }">
          <div v-if="formStatusRef === FormStatusEnum.EDIT">
            <b-form-select v-model="item.subject" :options="formOptions.subject" @change="calculatePoint(item)">
              <template #first>
                <option :value="undefined">-- 請選擇 --</option>
              </template>
            </b-form-select>
          </div>
          <div v-else>{{ formOptionsFormatter(formOptions.subject, item.subject) }}</div>
        </template>

        <template #cell(errType)="{ item }">
          <div v-if="formStatusRef === FormStatusEnum.EDIT">
            <b-form-select v-model="item.errType" :options="formOptions.errType" @change="calculatePoint(item)">
              <template #first>
                <option :value="undefined">-- 請選擇 --</option>
              </template>
            </b-form-select>
          </div>
          <div v-else>{{ formOptionsFormatter(formOptions.errType, item.errType) }}</div>
        </template>

        <!-- <template #head(isReview)>  
          <b-form-checkbox v-model="isAllCheck" 
          @change="changeCheck"></b-form-checkbox>
        </template> -->

        <template #cell(isReview)="row">
          <b-form-checkbox v-model="row.item.isReview"></b-form-checkbox>
        </template>

  <!-- timeEnd timeStart timeEndArr timeStartArr -->
        <!-- 簽到時間 -->
        <!-- <template #cell(timeStart)="{ item }">
          <div v-if="item.isEdit">
            <select v-model="item.timeStartArr[0]" @change="calculatePoint(item)">
              <option v-for="hh in formOptions.clockHH" :value="hh.value" :key="hh.value">
                {{ hh.text }}
              </option>
            </select>
            <select v-model="item.timeStartArr[1]" @change="calculatePoint(item)">
              <option v-for="mm in formOptions.clockMM" :value="mm.value" :key="mm.value">
                {{ mm.text }}
              </option>
            </select>
          </div>
          <div v-else>{{ item.timeStartArr[0] }}:{{ item.timeStartArr[1] }}</div>
        </template> -->

        <!-- 簽退時間 -->
        <!-- <template #cell(timeEnd)="{ item }">
          <div v-if="item.isEdit">
            <select v-model="item.timeEndArr[0]" @change="calculatePoint(item)">
              <option v-for="hh in formOptions.clockHH" :value="hh.value" :key="hh.value">
                {{ hh.text }}
              </option>
            </select>
            <select v-model="item.timeEndArr[1]" @change="calculatePoint(item)">
              <option v-for="mm in formOptions.clockMM" :value="mm.value" :key="mm.value">
                {{ mm.text }}
              </option>
            </select>
          </div>
          <div v-else>{{ item.timeEndArr[0] }}:{{ item.timeEndArr[1] }}</div>
        </template> -->

        <template #cell(remark)="{ item }">
          <b-form-textarea rows="3" v-if="formStatusRef === FormStatusEnum.EDIT" v-model="item.remark"></b-form-textarea>
          <!-- <b-form-textarea rows="3" v-model="item.teacherExperience" :plaintext="!item.isEdit" :disabled="!item.isEdit"></b-form-textarea> -->
          <div v-else>{{ item.remark }}</div>
          <!-- <i-part-of-message v-else type="hint" :id="item.teacherName" :value="item.teacherExperience"></i-part-of-message> -->
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
import { IEngCourseDtl, EngCourseDtl } from '@/shared/model/eng/eng-course-dtl.model';
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';
import { navigateByNameAndParams } from '@/router/router';

interface propsType {
  // engCourseDtls: IEngCourseDtl[];
  engEngrSubjectpoint: IEngEngrSubjectpoint;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0404EditInfo',
  props: {
    engEngrSubjectpoint: {
      required: false,
      type: Object,
    },
    // engCourseDtls: {
    //   required: false,
    // },
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
    let isAllCheck = ref(false);
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


    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.status = [
        { value: '0', text: '未審' },
        { value: '1', text: '已審' },
      ]
      formOptions.errType = [
        { value: '0', text: '無' },
        { value: '1', text: '未到' },
        { value: '2', text: '遲到' },
        { value: '3', text: '早退' },
        { value: '4', text: '科目不符' },
        { value: '5', text: '衝堂' },
      ]
    }

    const clockSelectHH = ref([]);
    for (let i = 8; i <= 22; i++) {
      let timeHH = {
        value: '',
        text: '',
      };
      if (i < 10) {
        timeHH.value = '0' + (i + '');
        timeHH.text = '0' + (i + '');
      } else {
        timeHH.value = i + '';
        timeHH.text = i + '';
      }
      clockSelectHH.value.push(timeHH);
    }

    const formOptions = reactive({
      status: [],
      subject: [],
      errType: [],
      clockHH: clockSelectHH.value,
      clockMM: [
        { value: '00', text: '00' },
        { value: '10', text: '10' },
        { value: '20', text: '20' },
        { value: '30', text: '30' },
        { value: '40', text: '40' },
        { value: '50', text: '50' },
      ],
    });

    let formDefault = {
      chName: '',
      idno: '',
      courseDate: null,
      realpoint: '',
      shouldpoint: '',
      status: '',
      isReview: '',
      courseName: '',
      subject: '',
      errType: '',
      shouldpoint1: '',
      realpoint1: '',
      courseDate1: '',
      timeStart: '',
      timeEnd: '',
      remark: '',
      subject2: '',
    };

    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      chName: {},
      idno: {},
      courseDate: {},
      realpoint: {},
      shouldpoint: {},
      status: {},
      isReview: {},
      courseName: {},
      subject: {},
      errType: {},
      shouldpoint1: {},
      realpoint1: {},
      courseDate1: {},
      timeStart: {},
      timeEnd: {},
      remark: {},
      subject2: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const table = reactive({
      fields_backend: [
        {
          // 打勾框框
            // key: 'check',
            // label: '',
            // sortable: false,
            // thClass: 'text-center',
            // tdClass: 'text-center align-middle',

          // 核准
          key: 'isReview',
          label: "核准",
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 課程
          key: 'courseName',
          label: "課程",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 技師科別
          key: 'subject',
          label: "技師科別",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.subject, value),
        },
        {
          // 異常
          key: 'errType',
          label: i18n.t('label.errType'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 應得積分
          key: 'shouldpoint1',
          label: i18n.t('label.shouldpoint'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 實得積分
          key: 'realpoint1',
          label: i18n.t('label.realpoint'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 上課日期
          key: 'courseDate1',
          label: i18n.t('label.courseDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 簽到時間
          key: 'timeStart',
          label: i18n.t('label.checkin'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 簽退時間
          key: 'timeEnd',
          label: i18n.t('label.checkout'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 核准說明
          key: 'remark',
          label: i18n.t('label.remark'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 主題科別
          key: 'subject2',
          label: i18n.t('label.subject'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.subject, value),
        },
      ],
      data: undefined,
      // totalItems: undefined,
    });

    onMounted(() => {
      // table.engCourseDtls = props.engCourseDtls.slice().map(item => ({
      //   ...item,
      //   courseDate: parseToDate(item.courseDate, '-'),
      //   timeStartArr: item.timeStart.split(':'),
      //   timeEndArr: item.timeEnd.split(':'),
      //   subjectArr: item.subjectList.split(','),
      //   // isSelected: false,
      //   isEdit: false,
      //   // subjectIsAll: false,
      //   _showDetails: true,
      //   teacherList: [],
      // }));
      
      getAllSysCodes();
      handleQuery();
      setFormOptions();
    });

    onActivated(() => {
    });

    const changeCheck = () => {
      // isAllCheck.value = !isAllCheck.value;  // 加了會爆炸
      for(let i = 0; i < table.data.length; i++) {
        table.data[i].isReview = !table.data[i].isReview;
      }
      
    }

    const clearCheck = () => {
      isAllCheck.value = true;
      isAllCheck.value = false;
      for(let i = 0; i < table.data.length; i++) {
        table.data[i].isReview = false;
      }
    }

    function setFormOptions() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    function calculatePoint(item: IEngCourseDtl) {
      const totalMin =
        (Number(item.timeEndArr[0]) - Number(item.timeStartArr[0])) * 60 + (Number(item.timeEndArr[1]) - Number(item.timeStartArr[1]));
      const section = totalMin >= 200 ? 4 : totalMin >= 180 ? 3 : totalMin >= 90 ? 2 : totalMin >= 50 ? 1 : 0;
      switch (item.law) {
        case '1':
          item.point = section * 10;
          break;
        case '2':
          item.point = totalMin < 50 ? 10 : 20;
          break;
        case '3':
          item.point = section * 10;
          break;
        case '4':
          item.point = totalMin >= 120 ? section * 10 : 0;
          break;
        case '5':
          item.point = section * 10;
          break;
        case '6':
          item.point = section * 10;
          break;
        case '7':
          item.point = 0;
      }
    }

    const handleQueryBtn = () => {
      iTableComponent.value.state.pagination.currentPage = 1;
      iTableComponent.value.emitPagination();
    };

    function toQueryView() {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    }

    const handleQuery = () => {
      const now = new Date();
      const offset = now.getTimezoneOffset();

      //驗證
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          table.data = [
            {
              no: '1',
              isReview: false,
              courseName: '智慧融合',
              subject: '02',
              errType: '1',
              shouldpoint1: '20',
              realpoint1: '20',
              courseDate1: '110/4/29',
              timeStart: '',
              timeEnd: '',
              remark: '123',
              subject2: '01'
            },
          ];
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

    const data2 = 
      {
        chName: '張林土',
        idno: 'A123456789',
        courseDate: '110/4/29',
        realpoint: '20',
        shouldpoint: '20',
        status: '0',
      }

    return {
      FormStatusEnum,
      handleQueryBtn,
      reset,
      formStatusRef,
      queryStatus,
      table,
      $v,
      validateState,
      toQueryView,
      formOptions,
      formOptionsFormatter,
      handlePaginationChanged,
      data2,
      clearCheck,
      changeCheck,
      isAllCheck,
      calculatePoint,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>

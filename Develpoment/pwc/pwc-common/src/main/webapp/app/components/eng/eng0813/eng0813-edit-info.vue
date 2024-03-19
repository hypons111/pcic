<template>
  <b-container>
    <b-card no-body>
      <b-card-header>
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          各類證照申辦案件統計表-查詢畫面
        </h5>
      </b-card-header>
      <b-card-body>
        <b-form-row>
          <b-col offset="5" class="text-right mb-2">
            <i-button type="arrow-bar-left" size="sm"></i-button>
            <i-button type="arrow-bar-right" size="sm"></i-button>
          </b-col>
        </b-form-row>
        <b-form-row>
          <!-- 所屬單位 -->
          <i-form-group-check :label="$t('label.belUnit') + '：'" :item="$v.belUnit">
            <div>{{ data.belUnit }}</div>
          </i-form-group-check>
          <!-- 主辦單位名稱 -->
          <i-form-group-check :label="$t('label.organizer') + '：'" :item="$v.organizer">
            <div>{{ data.organizer }}</div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <!-- 課程名稱 -->
          <i-form-group-check :label="$t('label.courseName') + '：'" :item="$v.courseName">
            <div>{{ data.courseName }}</div>
          </i-form-group-check>
          <!-- 課程核備文號 -->
          <i-form-group-check :label="$t('label.docNo') + '：'" :item="$v.docNo">
            <div>{{ data.docNo }}</div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <!-- 申請日期 -->
          <i-form-group-check :label="$t('label.applyDate') + '：'" :item="$v.applyDate">
            <div>{{ data.applyDate }}</div>
          </i-form-group-check>
          <!-- 課程日期 -->
          <i-form-group-check :label="$t('label.courseDate') + '：'" :item="$v.courseDate">
            <div>{{ data.courseDate }}</div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <!-- 上課地點 -->
          <i-form-group-check :label="$t('label.place') + '：'" :item="$v.place">
            <div>{{ data.place }}</div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <!-- 聯絡人姓名 -->
          <i-form-group-check :label="$t('label.contactName') + '：'" :item="$v.contactName">
            <div>{{ data.contactName }}</div>
          </i-form-group-check>
          <!-- 聯絡人電話 -->
          <i-form-group-check :label="$t('label.contactTel') + '：'" :item="$v.contactTel">
            <div>{{ data.contactTel }}</div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <!-- 電子郵件 -->
          <i-form-group-check :label="$t('label.emailAddress') + '：'" :item="$v.emailAddress">
            <div>{{ data.emailAddress }}</div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <!-- 活動網址 -->
          <i-form-group-check :label="$t('label.webUrl') + '：'" :item="$v.webUrl">
            <div>{{ data.webUrl }}</div>
          </i-form-group-check>
        </b-form-row>
      </b-card-body>

      <!--表格一 教師資訊 -->
      <section class="container mt-2">
        <hr />
        <i-table
          small
          ref="iTableComponent"
          title="教師資訊"
          :itemsUndefinedBehavior="'loading'"
          :items="Teachertable.data"
          :is-server-side-paging="true"
          :fields="Teachertable.fields_backend"
          :totalItems="Teachertable.totalItems"
        >
        </i-table>
      </section>
      <section class="container mt-2">
        <hr />
        <!--表格二 課程資訊 -->
        <i-table
          small
          ref="iTableComponent"
          title="課程資訊"
          :itemsUndefinedBehavior="'loading'"
          :items="Coursetable.data"
          :is-server-side-paging="true"
          :fields="Coursetable.fields_backend"
          :totalItems="Coursetable.totalItems"
        >
        </i-table>
      </section>
      <b-form-row> <p></p> </b-form-row>
      <b-form-row>
        <b-col offset="5">
          <i-button type="arrow-left" @click="toQueryView"></i-button>
        </b-col>
      </b-form-row>
      <b-form-row> <p></p> </b-form-row>
    </b-card>
  </b-container>
</template>

<script lang="ts">
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { defineComponent, reactive, onMounted, ref } from '@vue/composition-api';
import { handleBack } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import i18n from '@/shared/i18n';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import NotificationService from '@/shared/notification/notification-service';
import { log } from 'console';
import { navigateByNameAndParams } from '@/router/router';
import { FormStatusEnum } from '@/shared/enum';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
// import { onMounted, onActivated, watch, reactive, Ref, ref, toRef, inject } from '@vue/composition-api';

export default defineComponent({
  name: 'Eng0813R2',
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    const $bvModal = useBvModal();
    const notificationService = useNotification();
    const eng0813R2 = ref(null);

    onMounted(() => {
      getAllSysCodes();
      handleQuery();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const handleQuery = () => {
      queryStatus.value = true;
      Teachertable.data = Teacherdata;
      Teachertable.totalItems = Teacherdata.length;
      Coursetable.data = Coursedata;
      Coursetable.totalItems = Coursedata.length;
    };

    const formOptions = reactive({
      status: [],
      applyItemsList: [],
      applyType: [],
      subject: [],
      examType: [],
      identityType: [],
      sex: [],
      receiveType: [],
      payType: [],
    });

    const formDefault = {
      chName: undefined,
    };

    const form = reactive(Object.assign({}, formDefault));

    const rules = {
      chName: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // const notificationService: NotificationService = useNotification();

    // // 區塊是否顯示
    // const stepVisible = reactive({
    //   step1: true,
    // });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    // // modal物件
    // const $bvModal = useBvModal();

    // 教師資訊表格
    const Teachertable = reactive({
      fields_backend: [
        {
          // 姓名
          key: 'chName',
          label: i18n.t('label.chName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '10%' },
        },
        {
          // 最高學歷
          key: 'highestDegree',
          label: i18n.t('label.highestDegree'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 現職
          key: 'teacherJob',
          label: i18n.t('label.teacherJob'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 主要簡歷(限300字)
          key: 'teacherExperience',
          label: i18n.t('label.teacherExperience'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });
    // 教師資訊(暫時用)
    const Teacherdata = [
      {
        chName: '王大明',
        highestDegree: '台大土木研究所',
        teacherJob: '工程師',
        teacherExperience: '工程師',
      },
    ];
    const Coursetable = reactive({
      fields_backend: [
        {
          // 課程主題
          key: 'courseTheme',
          label: i18n.t('label.courseTheme'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '10%' },
        },
        {
          // 日期
          key: 'date',
          label: i18n.t('label.date'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 時間(起)
          key: 'timeStart',
          label: i18n.t('label.timeStart'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 時間(訖)
          key: 'timeEnd',
          label: i18n.t('label.timeEnd'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 適用款次
          key: 'law',
          label: i18n.t('label.law'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 核准積分
          key: 'approvePoint',
          label: i18n.t('label.approvePoint'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 講師
          key: 'teacher',
          label: i18n.t('label.teacher'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 適用科別
          key: 'applicableSubject',
          label: i18n.t('label.applicableSubject'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });
    // 教師資訊(暫時用)
    const Coursedata = [
      {
        courseTheme: '新申請',
        date: '111',
        timeStart: '111.5',
        timeEnd: '1111.7',
        law: '第一款',
        approvePoint: '9',
        teacher: '王大明',
        applicableSubject: '土木',
      },
    ];

    const data = {
      belUnit: '台灣經營研究院',
      organizer: '台灣經營研究院',
      courseName: '缺失研討會',
      docNo: '0909',
      applyDate: '111',
      courseDate: '112',
      place: '台北市',
      contactName: '王先生',
      contactTel: '09123456',
      emailAddress: 'dsdads@sdsad.com',
      webUrl: 'sfsfffwefas.com',
    };

    function toQueryView() {
      handleBack({ isNotKeepAlive: true });
    }

    return {
      $v,
      formOptions,
      toQueryView,
      queryStatus,
      Teachertable,
      Coursetable,
      data,
      ref,
      formOptionsFormatter,
      handleQuery,
    };
  },
});
</script>
<style scoped></style>

function ref(arg0: boolean) { throw new Error('Function not implemented.'); } function formOptionsFormatter(subject: any[], value: string):
any { throw new Error('Function not implemented.'); } function formatToString(value: string, arg1: string, arg2: string): any { throw new
Error('Function not implemented.'); }

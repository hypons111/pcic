<template>
  <b-container>
    <b-card no-body>
      <b-card-header>
        <h5 class="m-0">各類證照申辦案件統計表-查詢畫面</h5>
      </b-card-header>
      <b-card-body>
        <b-form-row>
          <i-form-group-check content-cols="6" :label="$t('label.applyToType') + '：'" :item="$v.applyToType">
            <b-form-select v-model="$v.applyToType.$model" :options="formOptions.applyItemsList">
              <template #first>
                <option :value="undefined">全部</option>
              </template>
            </b-form-select>
          </i-form-group-check>
          <i-form-group-check class="col-6" content-cols="6" :label="$t('label.contractorId') + '：'" :item="$v.contractorId">
            <b-form-select v-model="$v.contractorId.$model" >
              <!-- :options="formOptions.contractorId" -->
              <template #first>
                <option :value="undefined">不拘</option>
              </template>
            </b-form-select>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <i-form-group-check :label="$t('label.publicDate') + '：'" :dual1="$v.publicDateStart" :dual2="$v.publicDateEnd">
            <b-input-group>
              <i-date-picker
                v-model="$v.publicDateStart.$model"
                :disabled-date="notAfterPublicDateEnd"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
              <b-input-group-text>至</b-input-group-text>
              <i-date-picker
                v-model="$v.publicDateEnd.$model"
                :disabled-date="notBeforePublicDateStart"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </b-input-group>
          </i-form-group-check>
          <b-col offset="8">
            <i-button type="search" @click="handleQuery"></i-button>
            <i-button type="x-circle" @click="reset"></i-button>
          </b-col>
        </b-form-row>
      </b-card-body>
      <!--表格一 技師證書申辦統計表 -->
      <section class="container mt-2" v-show="queryStatus">
        <hr />
        <i-table
          small
          ref="iTableComponent"
          title="技師證書申辦統計表"
          :itemsUndefinedBehavior="'loading'"
          :items="Certitable.data"
          :is-server-side-paging="true"
          :fields="Certitable.fields_backend"
          :totalItems="Certitable.totalItems"
        >
          <template #cell(action)="row">
            <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
          </template>
        </i-table>
      </section>
      <!--表格二 技師執業執照申辦統計表-->
      <section class="container mt-2" v-show="queryStatus">
        <hr />
        <i-table
          small
          ref="iTableComponent"
          title="技師執業執照申辦統計表"
          :itemsUndefinedBehavior="'loading'"
          :items="Licentable.data"
          :is-server-side-paging="true"
          :fields="Licentable.fields_backend"
          :totalItems="Licentable.totalItems"
        >
        </i-table>
      </section>
      <!--表格三 技術顧問公司登記證申辦統計表-->
      <section class="container mt-2" v-show="queryStatus">
        <hr />
        <i-table
          small
          ref="iTableComponent"
          title="技術顧問公司登記證申辦統計表"
          :itemsUndefinedBehavior="'loading'"
          :items="Comtable.data"
          :is-server-side-paging="true"
          :fields="Comtable.fields_backend"
          :totalItems="Comtable.totalItems"
        >
        </i-table>
      </section>
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
// import { onMounted, onActivated, watch, reactive, Ref, ref, toRef, inject } from '@vue/composition-api';

export default defineComponent({
  name: 'Eng0811Query',
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    const $bvModal = useBvModal();
    const notificationService = useNotification();

    onMounted(() => {
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const handleQuery = () => {
      queryStatus.value = true;
      Certitable.data = Certidata;
      Certitable.totalItems = Certidata.length;
      Licentable.data = Licendata;
      Licentable.totalItems = Licendata.length;
      Comtable.data = Comdata;
      Comtable.totalItems = Comdata.length;
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
      engEngrCertificateId: undefined,
      engEngrCertificateVersion: undefined,
      engEngrCertificateNo: undefined,
      status: undefined,
      reviewStaff: undefined,
      reviewTime: undefined,
      reviewDesc: undefined,
      applyItemsList: undefined,
      applyItems: undefined,
      applyType: undefined,
      applyDateStart: undefined,
      applyDateEnd: undefined,
      idno: undefined,
      chName: undefined,
      enName: undefined,
      birthDate: undefined,
      sex: undefined,
      contactTelOffice: undefined,
      contactTelHome: undefined,
      mobileTel: undefined,
      fax: undefined,
      email: undefined,
      permanentAddrZip: undefined,
      permanentAddrCity: undefined,
      permanentAddrTown: undefined,
      permanentAddrOther: undefined,
      contactAddrZip: undefined,
      contactAddrCity: undefined,
      contactAddrTown: undefined,
      contactAddrOther: undefined,
      identityType: undefined,
      addrForeign: undefined,
      subject: undefined,
      engrCertificateDocNo: undefined,
      engrCertificateDocNoOld: undefined,
      examType: undefined,
      examNo: undefined,
      examNoOld: undefined,
      receiveDateStart: undefined,
      receiveDateEnd: undefined,
      receiveNo: undefined,
      publicDateStart: undefined,
      publicDateEnd: undefined,
      receiveType: undefined,
      receiveAddrZip: undefined,
      receiveAddrCity: undefined,
      receiveAddrTown: undefined,
      receiveAddrOther: undefined,
      contractorId: undefined,
      contractorName: undefined,
      remark: undefined,
      createTime: undefined,

      payType: undefined,
      virtualAccount: undefined,

      effectDateEnd: undefined,
      engYearreportYyyy: '',
      validDate: '',
      totalexeNo: '',
      newexe: '',
      stopexe: '',
      applyToType: 'undefined',
      // compName: '',
      // compIdno: '',
      // notFillDesc: '',
      // engrName: '',
    };
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      engEngrCertificateId: {},
      engEngrCertificateVersion: {},
      engEngrCertificateNo: {},
      status: {},
      reviewStaff: {},
      reviewTime: {},
      reviewDesc: {},
      applyItemsList: {},
      applyItems: {},
      applyType: {},
      applyDateStart: {},
      applyDateEnd: {},
      idno: {},
      chName: {},
      enName: {},
      birthDate: {},
      sex: {},
      contactTelOffice: {},
      contactTelHome: {},
      mobileTel: {},
      fax: {},
      email: {},
      permanentAddrZip: {},
      permanentAddrCity: {},
      permanentAddrTown: {},
      permanentAddrOther: {},
      contactAddrZip: {},
      contactAddrCity: {},
      contactAddrTown: {},
      contactAddrOther: {},
      identityType: {},
      addrForeign: {},
      subject: {},
      engrCertificateDocNo: {},
      engrCertificateDocNoOld: {},
      examType: {},
      examNo: {},
      examNoOld: {},
      receiveDateStart: {},
      receiveDateEnd: {},
      receiveNo: {},
      publicDateStart: {},
      publicDateEnd: {},
      receiveType: {},
      receiveAddrZip: {},
      receiveAddrCity: {},
      receiveAddrTown: {},
      receiveAddrOther: {},
      contractorId: {},
      contractorName: {},
      remark: {},
      createTime: {},

      payType: {},
      virtualAccount: {},

      effectDateEnd: {},
      engYearreportYyyy: {},
      validDate: {},
      totalexeNo: {},
      newexe: {},
      stopexe: {},
      applyToType: {},
      // compName: {},
      // compIdno: {},
      // notFillDesc: {},
      // engrName: {},
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

    // 技師證書表格
    const Certitable = reactive({
      fields_backend: [
        {
          // 申辦種類
          key: 'applyToType',
          label: i18n.t('label.applyToType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '10%' },
        },
        {
          // 總申請件數
          key: 'totalApplyNum',
          label: i18n.t('label.totalApplyNum'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 網路申辦
          key: 'intApply',
          label: i18n.t('label.intApply'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 郵寄申辦
          key: 'postApply',
          label: i18n.t('label.postApply'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
          {
          // 網路申辦/總案件(百分比%)
          key: 'intApplyRate',
          label: i18n.t('label.intApplyRate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });
    // 技師證書資料(暫時用)
    const Certidata = [
      {
        applyToType:'新申請' ,
        totalApplyNum: '3',
        intApply: '2',
        postApply: '1',
        intApplyRate: '33',
      },
    ];
    // 技師執照表格
    const Licentable = reactive({
      fields_backend: [
        {
          // 申辦種類
          key: 'applyToType',
          label: i18n.t('label.applyToType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '10%' },
        },
        {
          // 總申請件數
          key: 'totalApplyNum',
          label: i18n.t('label.totalApplyNum'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 網路申辦
          key: 'intApply',
          label: i18n.t('label.intApply'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 郵寄申辦
          key: 'postApply',
          label: i18n.t('label.postApply'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
          {
          // 網路申辦/總案件(百分比%)
          key: 'intApplyRate',
          label: i18n.t('label.intApplyRate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });
    // 技師執照資料(暫時用)
    const Licendata = [
      {
        applyToType:'換發',
        totalApplyNum: '2',
        intApply: '1',
        postApply: '1',
        intApplyRate: '50',
      },
    ];
    // 公司表格 
    const Comtable = reactive({
      fields_backend: [
        {
          // 申辦種類
          key: 'applyToType',
          label: i18n.t('label.applyToType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '10%' },
        },
        {
          // 總申請件數
          key: 'totalApplyNum',
          label: i18n.t('label.totalApplyNum'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 網路申辦
          key: 'intApply',
          label: i18n.t('label.intApply'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 郵寄申辦
          key: 'postApply',
          label: i18n.t('label.postApply'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
          {
          // 網路申辦/總案件(百分比%)
          key: 'intApplyRate',
          label: i18n.t('label.intApplyRate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });
    // 公司資料(暫時用)
    const Comdata = [
      {
        applyToType:'廢止',
        totalApplyNum: '5',
        intApply: '2',
        postApply: '3',
        intApplyRate: '40',
      },
    ];

    function toQueryView() {
      handleBack({ isNotKeepAlive: true });
    }

    function notBeforeApplyDateStart(date: Date): boolean {
      if (form.applyDateStart) return date < new Date(form.applyDateStart);
    }

    function notAfterApplyDateEnd(date: Date) {
      if (form.applyDateEnd) return date > new Date(form.applyDateEnd);
    }

    function notBeforePublicDateStart(date: Date) {
      if (form.publicDateStart) return date < new Date(form.publicDateStart);
    }

    function notAfterPublicDateEnd(date: Date) {
      if (form.publicDateEnd) return date > new Date(form.publicDateEnd);
    }

    return {
      $v,
      reset,
      validateState,
      formOptions,
      toQueryView,
      queryStatus,
      Licentable,
      Certitable,
      Comtable,
      ref,
      formOptionsFormatter,
      handleQuery,
      notBeforeApplyDateStart,
      notAfterApplyDateEnd,
      notBeforePublicDateStart,
      notAfterPublicDateEnd,
    };
  },
});
</script>
<style scoped></style>

function ref(arg0: boolean) { throw new Error('Function not implemented.'); } function formOptionsFormatter(subject: any[], value: string):
any { throw new Error('Function not implemented.'); } function formatToString(value: string, arg1: string, arg2: string): any { throw new
Error('Function not implemented.'); }

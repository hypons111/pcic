<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <!-- <font-awesome-icon icon="project-diagram"></font-awesome-icon> -->
            技師個人資料
          </h5>
        </div>
        <div class="card-body">
          <!-- 姓名 -->
          <b-form-row class="align-items-center mt-2">
            <i-form-group-check class="col-12" label-cols="6" content-cols="6" :label="$t('label.name') + '：'" :item="$v.chName">
              <div>{{ $v.chName.$model }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- 科別 -->
          <!-- <b-form-row>
            <i-form-group-check class="col-6" label-cols="4" content-cols="8" :label="$t('label.subject') + '：'" :item="$v.subject">
              <div>{{ $v.subject.$model }}</div>
            </i-form-group-check> -->
          <!-- 證書字號 -->
          <!-- <i-form-group-check
              class="col-6"
              label-cols="4"
              content-cols="8"
              :label="$t('label.engrCertificateDocNo') + '：'"
              :item="$v.engrCertificateDocNo"
            >
              <div>{{ $v.engrCertificateDocNo.$model }}</div>
            </i-form-group-check>
          </b-form-row> -->

          <!-- 執照字號 -->
          <!-- <b-form-row>
            <i-form-group-check
              class="col-3"
              label-cols="4"
              content-cols="8"
              :label="$t('label.engrLicenseDocNo') + '：'"
              :item="$v.engrLicenseDocNo"
            >
              <div>{{ $v.engrLicenseDocNo.$model }}</div>
            </i-form-group-check> -->
          <!-- 職業機構 -->
          <!-- <i-form-group-check
              class="col-3"
              label-cols="4"
              content-cols="8"
              :label="$t('label.engCompChName') + '：'"
              :item="$v.compChName"
            >
              <div>{{ $v.compChName.$model }}</div> -->
          <!-- </i-form-group-check> -->
          <!-- 機構統編 -->
          <!-- <i-form-group-check class="col-3" label-cols="4" content-cols="8" :label="$t('label.engCompIdno') + '：'" :item="$v.compIdno">
              <div>{{ $v.compIdno.$model }}</div>
            </i-form-group-check> -->
          <!-- 申請項目 -->
          <!-- <i-form-group-check class="col-3" label-cols="4" content-cols="8" :label="$t('label.applyItems') + '：'" :item="$v.applyItem">
              <div>{{ $v.applyItem.$model }}</div>
            </i-form-group-check>
          </b-form-row> -->

          <b-table :fields="table1.fields_backend" :items="table1.data">
            <!-- <template #cell(subject)="row">
              {{ row.item.subject }}
            </template> -->
            <!-- <template #cell(engrCertificateDocNo)="row">
              {{ row.item.engrCertificateDocNo }}
            </template> -->
          </b-table>
          <b-table :fields="table2.fields_backend" :items="table2.data"> </b-table>

          <b-button-toolbar class="float-right mt-5">
            <b-form-group>
              <i-button type="arrow-left" @click="toQueryView">返回</i-button>
              <!-- <b-button @click="handleCertificateQuery">查上</b-button>
              <b-button @click="handleLicenseQuery">查下</b-button> -->
            </b-form-group>
          </b-button-toolbar>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, toRef, watch, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email, maxLength } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { navigateByNameAndParams, handleBack } from '@/router/router';
// import { EngEngrCertificate } from '@/shared/model/eng/eng-engr-certifiacte.model';
import { EngMember } from '@/shared/model/eng/eng-member.model';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { formOptionsFormatter } from '@/shared/formatter/common';

export default {
  name: 'Eng0706EngInfo',
  props: {
    engEngrCertificate: {
      type: Object,
      required: false,
    },
    engMember: {
      required: false,
      type: Object,
    },
  },
  setup(props, context) {
    const engEngrCertificateProp = toRef(props, 'engEngrCertificate');
    const engMemberProp = toRef(props, 'engMember');
    const admSysCodeService = new AdmSysCodeService();

    // console.log(engMember);
    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      chName: '',
      // chName: engMemberProp.value.chName,
      engrId: '',
      idno: '',
      status: null,
      applyItemsList: null,
      subject: '',
      engrCertificateDocNo: '',
      engrLicenseDocNo: '',
      compChName: '',
      compIdno: '',
      applyItem: '',
    };
    formDefault.chName = engMemberProp.value.chName;
    formDefault.engrId = engMemberProp.value.engMemberId;
    formDefault.idno = engMemberProp.value.idno;
    formDefault.status = 5;
    formDefault.applyItemsList = 0;
    // 建立表單物件 ref
    let form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      chName: {},
      subject: {},
      engrCertificateDocNo: {},
      engrLicenseDocNo: {},
      compChName: {},
      compIdno: {},
      applyItem: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);
    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const formOptions = reactive({
      subject: [],
      applyItem: [],
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.applyItem = [
        { value: '0', text: '新申請' },
        { value: '1', text: '變更' },
        { value: '2', text: '換發' },
        { value: '3', text: '換照' },
        { value: '4', text: '補發' },
        { value: '5', text: '自行停業' },
        { value: '6', text: '變更及換發' },
        { value: '7', text: '廢止' },
        { value: '8', text: '核發英文證明書' },
      ];
    }

    const table1 = reactive({
      fields_backend: [
        {
          // 科別
          key: 'subject',
          label: i18n.t('label.subject'),
          sortable: false,
          thClass: 'text-left',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.subject, value),
        },
        {
          // 證書字號
          key: 'engrCertificateDocNo',
          label: i18n.t('label.engrCertificateDocNo'),
          sortable: false,
          thClass: 'text-left',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });
    const table2 = reactive({
      fields_backend: [
        {
          // 執照字號
          key: 'engrLicenseDocNo',
          label: i18n.t('label.engrLicenseDocNo'),
          sortable: false,
          thClass: 'text-left',
          tdClass: 'text-left align-middle',
        },
        {
          // 職業機構
          key: 'compChName',
          label: i18n.t('label.engCompChName'),
          sortable: false,
          thClass: 'text-left',
          tdClass: 'text-left align-middle',
        },
        {
          // 機構統編
          key: 'compIdno',
          label: i18n.t('label.engCompIdno'),
          sortable: false,
          thClass: 'text-left',
          tdClass: 'text-left align-middle',
        },
        {
          // 申請項目
          key: 'applyItem',
          label: i18n.t('label.applyItems'),
          sortable: false,
          thClass: 'text-left',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.applyItem, value),
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const toQueryView = () => {
      handleBack({ isReload: isReload.value, isNotKeepAlive: true });
      isReload.value = false;
    };

    // const createDefaultValue = (data: EngEngrCertificate) => {
    //   // Object.assign(formDefault, data);
    //   // Object.assign(form, formDefault);
    //   Object.assign(form, data);
    //   // reset();
    // };

    // watch(
    //   engEngrCertificateProp,
    //   () => {
    //     if (engEngrCertificateProp.value) {
    //       createDefaultValue(engEngrCertificateProp.value);
    //     }
    //   },
    //   { immediate: true }
    // );

    const handleCertificateQuery = () => {
      let url: string;
      url = `/eng-engr-certificates/${form.idno}/${form.status}/${form.applyItemsList}`;
      table1.data = undefined;
      dataPromise.value = axios
        .get(url, form)
        .then(({ data }) => {
          // console.log(data);
          table1.data = data.slice(0, data.length); //前端分頁
          table1.totalItems = data.length; //前端分頁
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const handleLicenseQuery = () => {
      // let url: string;
      // url = `/eng-engr-licenses/criteria-jpa`;
      table2.data = undefined;
      dataPromise.value = axios
        .post('/eng-engr-licenses/criteria-jpa', form)
        .then(({ data }) => {
          // console.log(data);
          table2.data = data.slice(0, data.length); //前端分頁
          table2.totalItems = data.length; //前端分頁
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    onMounted(() => {
      getAllSysCodes();
      handleCertificateQuery();
      handleLicenseQuery();
    });

    return {
      $v,
      dataPromise,
      reset,
      validateState,
      toQueryView,
      table1,
      table2,
      handleCertificateQuery,
      handleLicenseQuery,
    };
  },
};
</script>
<style>
.red-text {
  color: red;
  font-weight: bold;
}
</style>

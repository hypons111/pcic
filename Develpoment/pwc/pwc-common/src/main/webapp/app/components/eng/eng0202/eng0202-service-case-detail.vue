<template>
  <b-container>
    <b-form-row>
      <!-- 項次 -->
      <i-form-group-check :label="$t('label.engYearreportCaseId')+'：'" :item="$v.engYearreportCaseId">
        <b-form-input plaintext disabled :value="$v.engYearreportCaseId.$model"></b-form-input>
      </i-form-group-check>
      <!-- 案號 -->
      <i-form-group-check :label="$t('label.caseNo')+'：'" :item="$v.caseNo" labelStar content-cols="4">
        <b-form-input v-model.trim="$v.caseNo.$model" :state="validateState($v.caseNo)"></b-form-input>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 標案名稱 -->
      <i-form-group-check :label="$t('label.caseName')+'：'" :item="$v.caseName" labelStar>
        <b-form-textarea v-model.trim="$v.caseName.$model" :state="validateState($v.caseName)" rows="2"></b-form-textarea>
      </i-form-group-check>
      <!-- 是否延續至明年 -->
      <i-form-group-check :label="$t('label.isToNextYear')+'：'">
        <b-form-checkbox v-model="$v.isToNextYear.$model" class="pt-2" value="1" unchecked-value="0" switch>是</b-form-checkbox>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 案主姓名 -->
      <i-form-group-check :label="$t('label.clientName')+'：'" :item="$v.clientName">
        <b-form-input v-model.trim="$v.clientName.$model" :state="validateState($v.clientName)"></b-form-input>
      </i-form-group-check>
      <!-- 案主電話 -->
      <i-form-group-check :label="$t('label.clientTel')+'：'" :item="$v.clientTel" content-cols="6">
        <b-form-input v-model.trim="$v.clientTel.$model" :state="validateState($v.clientTel)"></b-form-input>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 案主地址 -->
      <i-form-group-check
        :label="$t('label.clientAddr') + '：'"
        :hasInvalid="true"
        class="col-12"
        label-cols="2"
        content-cols="10"
      >
        <i-address-picker
          ref="clientAddr"
          :isReset="true"
          :addrCity="$v.clientAddrCity.$model"
          :addrArea="$v.clientAddrTown.$mode"
          :addrRoad="$v.clientAddrOther.$model"
          @getZip="getClientAddrZip"
          @getCity="getClientAddrCity"
          @getArea="getClientAddrTown"
          @getRoad="getClientAddrOther"
        ></i-address-picker>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 決標日期/簽約日期 -->
      <i-form-group-check :label="$t('label.tenderDate')+'：'" :item="$v.tenderDate" content-cols="4">
        <i-date-picker v-model.trim="$v.tenderDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
      </i-form-group-check>
      <!-- 核備文號 -->
      <i-form-group-check :label="$t('label.docNo')+'：'" :item="$v.docNo" content-cols="4">
        <b-form-input v-model.trim="$v.docNo.$model" :state="validateState($v.docNo)"></b-form-input>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 日期起迄 -->
      <i-form-group-check :label="$t('label.dateStartToEnd')+'：'" :dual1="$v.dateStart" :dual2="$v.dateEnd">
        <b-input-group>
          <i-date-picker v-model="$v.dateStart.$model" :disabled-date="notAfterDateEnd" placeholder="yyy/MM/dd"></i-date-picker>
          <b-input-group-text>至</b-input-group-text>
          <i-date-picker v-model="$v.dateEnd.$model" :disabled-date="notBeforeDateStart" placeholder="yyy/MM/dd"></i-date-picker>
        </b-input-group>
      </i-form-group-check>
      <!-- 是否為海外案件 -->
      <i-form-group-check :label="$t('label.isForeignCase')+'：'">
        <b-form-checkbox v-model="$v.isForeignCase.$model" class="pt-2" value="1" unchecked-value="0" switch>是</b-form-checkbox>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 委託方式 -->
      <i-form-group-check :label="$t('label.commissionedType')+'：'" :item="$v.commissionedType" content-cols="6">
        <b-form-select
          v-model="$v.commissionedType.$model"
          :options="formOptions.commissionedType"
          :state="validateState($v.commissionedType)"
        >
          <template #first>
            <option value="">-- 請選擇 --</option>
          </template>
        </b-form-select>
      </i-form-group-check>
      <!-- 服務類型 -->
      <i-form-group-check :label="$t('label.serviceType')+'：'" :item="$v.serviceType" content-cols="6">
        <b-form-select v-model="$v.serviceType.$model" :options="formOptions.serviceType" :state="validateState($v.serviceType)">
          <template #first>
            <option value="">-- 請選擇 --</option>
          </template>
        </b-form-select>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 服務合約金額 -->
      <i-form-group-check :label="$t('label.serviceCasePrice')+'：'" :item="$v.price" content-cols="4">
        <b-form-input 
          v-model.trim="$v.price.$model" 
          :state="validateState($v.price)" 
          placeholder="請輸入正整數"
          v-i-currency
        ></b-form-input>
      </i-form-group-check>
      <!-- 本年度完成金額 -->
      <i-form-group-check :label="$t('label.yearCompletePrice')+'：'" :item="$v.yearCompletePrice" content-cols="4">
        <b-form-input 
          v-model.trim="$v.yearCompletePrice.$model" 
          :state="validateState($v.yearCompletePrice)" 
          placeholder="請輸入正整數"
          v-i-currency
        ></b-form-input>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 計畫主持人 -->
      <i-form-group-check :label="$t('label.host')+'：'" :item="$v.host">
        <b-form-textarea v-model.trim="$v.host.$model" :state="validateState($v.host)" rows="2"></b-form-textarea>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 服務項目及內容摘要 -->
      <i-form-group-check :label="$t('label.serviceContent')+'：'" :item="$v.serviceContent">
        <b-form-textarea v-model.trim="$v.serviceContent.$model" :state="validateState($v.serviceContent)" rows="3"></b-form-textarea>
      </i-form-group-check>
      <!-- 服務項目之工程地點 -->
      <i-form-group-check :label="$t('label.servicePlace')+'：'" :item="$v.servicePlace">
        <b-form-textarea v-model.trim="$v.servicePlace.$model" :state="validateState($v.servicePlace)" rows="3"></b-form-textarea>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 合作建築師事務所名稱 -->
      <i-form-group-check :label="$t('label.archCompName')+'：'" :item="$v.archCompName">
        <b-form-textarea v-model.trim="$v.archCompName.$model" :state="validateState($v.archCompName)" rows="3"></b-form-textarea>
      </i-form-group-check>
      <!-- 合作建築師事務所之工作內容 -->
      <i-form-group-check :label="$t('label.archCompWorkContent')+'：'" :item="$v.archCompWorkContent">
        <b-form-textarea
          v-model.trim="$v.archCompWorkContent.$model"
          :state="validateState($v.archCompWorkContent)"
          rows="3"
        ></b-form-textarea>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 其他協力廠商名稱 -->
      <i-form-group-check :label="$t('label.otherCompName')+'：'" :item="$v.otherCompName">
        <b-form-textarea v-model.trim="$v.otherCompName.$model" :state="validateState($v.otherCompName)" rows="3"></b-form-textarea>
      </i-form-group-check>
      <!-- 其他協力廠商或分包情形之內容 -->
      <i-form-group-check :label="$t('label.otherCompWorkContent')+'：'" :item="$v.otherCompWorkContent">
        <b-form-textarea
          v-model.trim="$v.otherCompWorkContent.$model"
          :state="validateState($v.otherCompWorkContent)"
          rows="3"
        ></b-form-textarea>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 備註 -->
      <i-form-group-check
        :label="$t('label.remark')+'：'"
        :item="$v.remark"
        class="col-12"
        label-cols="2"
        content-cols="10"
      >
        <b-form-textarea v-model.trim="$v.remark.$model" rows="3"></b-form-textarea>
      </i-form-group-check>
    </b-form-row>
    
    <i-table 
      small 
      border0
      show-filter
      size="sm"
      per-page="10"
      :bordered="false"
      :isServerSidePaging="false"
      :items="engYearreportCaseEngrRef" 
      :fields="table.fields"
      :filter-ignored-fields="['engYearreportCaseEngrId','subject','joinPeriod','action']"
      class="mt-4"
    >
      <template #table-top>
        <b-row class="mb-2" >
          <b-col align="end">
            請選擇參與本案的技師名單：
            <b-form-select 
              v-model="selectedEngr"
              :options="engYearreportEngr"
              value-field="engrIdno"
              text-field="engrName"
              class="col-2"
            >
              <template #first>
                <option value="">-- 請選擇 --</option>
              </template>
            </b-form-select>
            <i-button class="ml-1" type="file-earmark-plus" @click="addEngr(selectedEngr)" :disabled="selectedEngr === ''"></i-button>
          </b-col>
        </b-row>
      </template>
      <template #cell(subject)="row">
        <b-form-select
          v-model="row.item.subject"
          :options="filterSubject(row.item.subjectList)"
        >
          <template #first>
            <option value="">-- 請選擇 --</option>
          </template>
        </b-form-select>
      </template>
      <template #cell(joinPeriod)="row">
        <i-date-picker v-model="row.item.dateStart" input-class="join-period-date-picker" placeholder="yyy/MM/dd"></i-date-picker>
        到
        <i-date-picker v-model="row.item.dateEnd" input-class="join-period-date-picker" placeholder="yyy/MM/dd"></i-date-picker>
      </template>
      <template #cell(mainTask)="row">
        <b-form-textarea
          v-model="row.item.mainTask"
          rows="3"
        ></b-form-textarea>
      </template>
      <template #cell(action)="row">
        <i-button size="sm" type="trash" @click="deleteEngr(row.item.engYearreportCaseEngrId)"></i-button>
      </template>
    </i-table>
  </b-container>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import { reactive, ref } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, integer, numeric } from '@/shared/validators';

export default {
  name: 'Eng0202ServiceCaseDetail',
  props: {
    engYearreportCaseDetail: {
      type: Object,
      required: false,
      default: null,
    },
    engYearreportEngr: {
      type: Array,
      required: false,
      default: null,
    },
    isForeignCase: {
      type: Boolean,
      required: false,
      default: false,
    },
    role: {
      type: String,
      required: false,
    },
  },
  setup(props) {
    const formOptions = {
      commissionedType: [
        { value: '0', text: '服務成本加公費法' },
        { value: '1', text: '建造費用百分比法' },
        { value: '2', text: '按月、按日或按時計酬法' },
        { value: '3', text: '總包價法或單價計算法' },
      ],
      serviceType: [
        { value: '0', text: '規劃與可行性研究、設計及協辦發包' },
        { value: '1', text: '專案管理或施工監造' },
        { value: '2', text: '設計及施工監造' },
        { value: '3', text: '其他' },
      ],
      subject: [
        { value: '1', text: '土木工程' },
        { value: '2', text: '水利工程' },
        { value: '3', text: '結構工程' },
        { value: '4', text: '大地工程' },
        { value: '5', text: '水土保持' },
        { value: '6', text: '採礦工程' },
        { value: '7', text: '應用地質' },
      ],
    };

    const filterSubject = (subjectList: string) => {
      let subjectArray = subjectList.split(',');
      return formOptions.subject.filter((item) => subjectArray.includes(item.value));
    };

    const table = reactive({
      fields: [
        // {
        //   // 序號
        //   key: 'no',
        //   label: i18n.t('label.no'),
        //   sortable: false,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        // },
        {
          // 科別
          key: 'subject',
          label: i18n.t('label.subject'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '15%' },
        },
        {
          // 姓名
          key: 'name',
          label: i18n.t('label.name'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // thStyle: { width: '15%' },
        },
        {
          // 身分證字號
          key: 'idno',
          label: i18n.t('label.idno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 參與期間
          key: 'joinPeriod',
          label: i18n.t('label.joinPeriod'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '15%' },
        },
        {
          // 主要業務
          key: 'mainTask',
          label: i18n.t('label.mainTask'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '40%' },
        },
        {
          // 動作
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
    });

    let formDefault = {
      engYearreportId: '',
      engYearreportYyyy: '',
      engYearreportCaseId: '',
      engYearreportCaseGuid: '',
      sourceId: '',
      caseNo: '',
      caseName: '',
      clientName: '',
      clientTel: '',
      clientAddrZip: '',
      clientAddrCity: '',
      clientAddrTown: '',
      clientAddrOther: '',
      docNo: '',
      price: '',
      yearCompletePrice: '',
      tenderDate: '',
      dateStart: '',
      dateEnd: '',
      commissionedType: '',
      serviceType: '',
      host: '',
      serviceContent: '',
      servicePlace: '',
      archCompName: '',
      archCompWorkContent: '',
      otherCompName: '',
      otherCompWorkContent: '',
      remark: '',
      isForeignCase: props.isForeignCase ? '1' : '0',
      isToNextYear: '',
      engYearreportCaseEngr: []
    };
    const formRules = {
      engYearreportId: {},
      engYearreportYyyy: {},
      engYearreportCaseId: {},
      engYearreportCaseGuid: {},
      sourceId: {},
      caseNo: { required },
      caseName: { required },
      clientName: {},
      clientTel: {},
      clientAddrZip: {},
      clientAddrCity: {},
      clientAddrTown: {},
      clientAddrOther: {},
      docNo: {},
      price: { numeric, integer },
      yearCompletePrice: { numeric, integer },
      tenderDate: {},
      dateStart: {},
      dateEnd: {},
      commissionedType: {},
      serviceType: {},
      host: {},
      serviceContent: {},
      servicePlace: {},
      archCompName: {},
      archCompWorkContent: {},
      otherCompName: {},
      otherCompWorkContent: {},
      remark: {},
      isForeignCase: {},
      isToNextYear: {},
      engYearreportCaseEngr: {}
    };

    Object.assign(formDefault, props.engYearreportCaseDetail);
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(formRules, form, formDefault);

    const engYearreportCaseEngrRef = ref([]);
    if(props.engYearreportCaseDetail && props.engYearreportCaseDetail['engYearreportCaseEngr'] instanceof Array) {
      for(let i = 0; i < props.engYearreportCaseDetail['engYearreportCaseEngr'].length; i++){
        engYearreportCaseEngrRef.value[i] = reactive(Object.assign({}, props.engYearreportCaseDetail['engYearreportCaseEngr'][i]));
      }
    }

    const clientAddr = ref(null);
    function getClientAddrZip(param: string) { form.clientAddrZip = param; };
    function getClientAddrCity(param: string) { form.clientAddrCity = param; };
    function getClientAddrTown(param: string) { form.clientAddrTown = param; };
    function getClientAddrOther(param: string) { form.clientAddrOther = param; };

    const modalReset = () => {
      reset();
      clientAddr.value.reset();
      engYearreportCaseEngrRef.value = [];
      for(let i = 0; i < formDefault.engYearreportCaseEngr.length; i++){
        engYearreportCaseEngrRef.value[i] = reactive(Object.assign({}, formDefault.engYearreportCaseEngr[i]));
      }
    };

    const selectedEngr = ref('');

    const addEngr = (selectedEngr: string) => {
      if(props.engYearreportEngr instanceof Array) {
        const { engrName , engrIdno, subjectList } = props.engYearreportEngr.find(item => item.engrIdno === selectedEngr);
        let caseEngr = {
          engYearreportCaseEngrId: engYearreportCaseEngrRef.value.length + 1,
          subjectList: subjectList,
          subject: '',
          name: engrName,
          idno: engrIdno,
        };
        engYearreportCaseEngrRef.value.push(caseEngr);
      }
    };

    const deleteEngr = (engYearreportCaseEngrId: string) => {
      engYearreportCaseEngrRef.value = engYearreportCaseEngrRef.value.filter(item => item.engYearreportCaseEngrId !== engYearreportCaseEngrId);
      engYearreportCaseEngrRef.value.forEach((item, index) => item.engYearreportCaseEngrId = index + 1);
    };

    const sendData = () => {
      form.engYearreportCaseEngr = engYearreportCaseEngrRef.value;
      return form;
    };

    // watch(
    //   engYearreportCaseRef,
    //   () => {
    //     Object.assign(formDefault, engYearreportCaseRef.value);
    //     reset();
    //   },
    //   { immediate: true }
    // );

    const notBeforeDateStart = (date: Date) => date < new Date(form.dateStart);
    const notAfterDateEnd = (date: Date) => date > new Date(form.dateEnd);

    return {
      formOptions,
      filterSubject,
      table,
      $v,
      validateState,
      checkValidity,
      clientAddr,
      getClientAddrZip,
      getClientAddrCity,
      getClientAddrTown,
      getClientAddrOther,
      engYearreportCaseEngrRef,
      selectedEngr,
      addEngr,
      deleteEngr,
      modalReset,
      sendData,
      notBeforeDateStart,
      notAfterDateEnd,
    };
  },
};
</script>

<style scope>
.join-period-date-picker {
  height: calc(1.3em + 0.5rem + 2px) !important;
  width: calc(6em + 2.5rem + 15px);
  padding: 0.25rem 0.75rem !important;
  border: 0;
}
</style>

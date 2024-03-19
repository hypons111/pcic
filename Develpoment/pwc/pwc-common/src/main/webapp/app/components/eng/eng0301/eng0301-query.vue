<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-11">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <!-- 案件類型 -->
              <i-form-group-check :label="$t('label.engCaseCaseType') + '：'" :item="$v.caseType">
                <b-form-select v-model="$v.caseType.$model" :options="formOptions.engCaseCaseType">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <!-- 簽證項目 -->
              <i-form-group-check :label="$t('label.recordItems') + '：'" :item="$v.recordItemsList">
                <b-form-checkbox-group v-model="$v.recordItems.$model" :options="formOptions.recordItemsList"></b-form-checkbox-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 工程種類 -->
              <i-form-group-check :label="$t('label.projectType') + '：'" :item="$v.projectType">
                <b-form-select v-model="$v.projectType.$model" :options="formOptions.projectType">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 案件編號 -->
              <i-form-group-check :label="$t('label.caseNum') + '：'" :item="$v.caseNo">
                <b-form-input v-model="$v.caseNo.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 案件名稱 -->
              <i-form-group-check :label="$t('label.caseNa') + '：'" :item="$v.caseName">
                <b-form-input v-model="$v.caseName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 執行機關名稱 -->
              <i-form-group-check
                v-if="$v.roleRef.$model === 'ENG_08' || $v.roleRef.$model === 'ENG_03'"
                :label="$t('label.wkutName') + '：'"
                :item="$v.execOrgName"
              >
                <b-form-input v-model="$v.execOrgName.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 執行機關代碼 -->
              <i-form-group-check v-if="$v.roleRef.$model === 'ENG_08'" :label="$t('label.execOrgId') + '：'" :item="$v.execOrgId">
                <b-form-input v-model="$v.execOrgId.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 承辦日期 -->
              <i-form-group-check :label="$t('label.execDate') + '：'" :dual1="$v.execDateStart" :dual2="$v.execDateEnd">
                <b-input-group>
                  <i-date-picker
                    v-model="$v.execDateStart.$model"
                    :disabled-date="notAfterExecDateEnd"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker
                    v-model="$v.execDateEnd.$model"
                    :disabled-date="notBeforeExecDateStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
              <!-- 決標日期 -->
              <i-form-group-check :label="$t('label.tenderDate') + '：'" :dual1="$v.tenderDateStart" :dual2="$v.tenderDateEnd">
                <b-input-group>
                  <i-date-picker
                    v-model="$v.tenderDateStart.$model"
                    :disabled-date="notAfterTenderDateEnd"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker
                    v-model="$v.tenderDateEnd.$model"
                    :disabled-date="notBeforeTenderDateStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 得標機構名稱 -->
              <i-form-group-check
                v-if="$v.roleRef.$model === 'ENG_08' || $v.roleRef.$model === 'ENG_03'"
                :label="$t('label.compNa') + '：'"
                :item="$v.compName"
              >
                <b-form-input v-model="$v.compName.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 得標機構統編 -->
              <i-form-group-check
                v-if="$v.roleRef.$model === 'ENG_08' || $v.roleRef.$model === 'ENG_03'"
                :label="$t('label.compIdn') + '：'"
              >
                <b-form-input v-model="$v.compIdno.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 技師姓名 -->
              <i-form-group-check v-if="$v.roleRef.$model !== 'ENG_01'" :label="$t('label.engrName') + '：'" :item="$v.engrName">
                <b-form-input v-model="$v.engrName.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 技師身分證字號 -->
              <i-form-group-check v-if="$v.roleRef.$model !== 'ENG_01'" :label="$t('label.engrIdno') + '：'" :item="$v.engrId">
                <b-form-input v-model="$v.engrId.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <b-col offset="8">
                <!-- 查詢 -->
                <i-button type="search" @click="handleQueryBtn" />
                <!-- 清除 -->
                <i-button type="x-circle" @click="clear" />
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2">
      <!-- 新增 -->
      <i-button type="file-earmark-plus" @click="toEditView(new EngCaseTotalDTO(), FormStatusEnum.CREATE)"> </i-button>
      <!-- 切換身分 -->
      <b-form-select style="width: 15%" v-model="$v.roleRef.$model" :options="formOptions.identityList">
        <template #first>
          <option value="">請選擇</option>
        </template>
      </b-form-select>
    </section>

    <section class="container mt-2" v-show="queryStatus">
      <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        :items="table.data"
        @changePagination="handlePaginationChanged"
      >
        <template #head(caseNo)="row">
          {{ $t('label.caseNum') }} <br />
          {{ $t('label.caseNa') }}
        </template>
        <template #cell(caseNo)="row">
          {{ row.item.caseNo }} <br />
          {{ row.item.caseName }}
        </template>
        <template #cell(compName)="row">
          <div v-for="comp in row.item.compList" :key="comp.compId">
            {{ comp.compName }}
          </div>
        </template>
        <template #cell(execDateStart)="row">
          {{ formatToString(row.item.execDateStart, '/', '-') }} ~ <br />
          {{ row.item.execDateEnd | dateStringToMingo }}
        </template>
        <template #cell(action)="row">
          <!-- 檢視 -->
          <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
          <!-- 編輯 -->
          <i-button size="sm" type="pencil-square" @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)"></i-button>
        </template>
        <template #row-details="{ item }">
          <b-table
            small
            :itemsUndefinedBehavior="'loading'"
            :is-server-side-paging="true"
            :fields="table2.fields_backend"
            :totalItems="table2.totalItems"
            :items="item.engrList"
          >
            <template v-slot:cell(no)="row">
              {{ row.index + 1 }}
            </template>
            <template v-slot:cell(execDateStart)="slot">
              {{ slot.item.execDateStart | dateStringToMingo }} ~
              {{ slot.item.execDateEnd | dateStringToMingo }}
            </template>
            <template #cell(action)="row">
              <!-- 檢視技師 -->
              <i-button size="sm" type="binoculars" @click="toQuery2(FormStatusEnum.READONLY, row.index)"></i-button>
            </template>
          </b-table>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { Ref, onMounted, ref, reactive } from '@vue/composition-api';
import { useValidation } from '@/shared/form';
import i18n from '@/shared/i18n';
import axios from 'axios';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import { EngCaseTotalDTO } from '@/shared/model/eng/eng-case.model';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Eng0301Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const iTableComponent = ref(null);
    const queryStatus = ref(false);
    const admSysCodeService = new AdmSysCodeService();
    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formOptions = reactive({
      recordItemsList: [], // 簽證項目
      recordRangeList: [], // 簽證範圍
      engCaseCaseType: [],
      projectType: [],
      subject: [],
      identityList: [],
    });

    const formDefault = {
      caseType: '',
      recordItemsList: '',
      recordItems: [],
      recordRangeList: '',
      recordRanges: [],
      subject: '',
      subjects: [],
      projectType: '',
      caseNo: '',
      caseName: '',
      execOrgName: '',
      execOrgId: '',
      execDateStart: null,
      execDateEnd: null,
      tenderDateStart: null,
      tenderDateEnd: null,
      compName: '',
      compIdno: '',
      engrName: '',
      engrId: '',
      roleRef: 'ENG_01',
    };

    const form = reactive(Object.assign({}, formDefault));

    const rules = {
      caseType: {},
      recordItemsList: {},
      recordItems: {},
      recordRangeList: {},
      recordRanges: {},
      subject: {},
      subjects: {},
      projectType: {},
      caseNo: {},
      caseName: {},
      execOrgName: {},
      execOrgId: {},
      execDateStart: {},
      execDateEnd: {},
      tenderDateStart: {},
      tenderDateEnd: {},
      compName: {},
      compIdno: {},
      engrName: {},
      engrId: {},
      roleRef: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();
    const table = reactive({
      fields_backend: [
        {
          // 案件編號，名稱
          key: 'caseNo',
          label: i18n.t('label.caseNum'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        // {
        //   // 案件名稱
        //   key: 'caseName',
        //   label: i18n.t('label.caseNa'),
        //   sortable: false,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        // },
        {
          // 案件類型
          key: 'caseType',
          label: i18n.t('label.engCaseCaseType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.engCaseCaseType, value),
        },
        {
          // 執行機關
          key: 'execOrgName',
          label: i18n.t('label.wkutName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 得標機構
          key: 'compName',
          label: i18n.t('label.compNa'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 聯絡人姓名
          key: 'contactPerson',
          label: i18n.t('label.contactName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '6%' },
        },
        {
          // 電話
          key: 'contactTel',
          label: i18n.t('label.phoneNo'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '10%' },
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 預訂承辦日期
          key: 'execDateStart',
          label: i18n.t('label.execDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 工程種類
          key: 'projectType',
          label: i18n.t('label.projectType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.projectType, value),
        },
        {
          // 簽證範圍
          key: 'recordRangeList',
          label: i18n.t('label.recordRangeList'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
          formatter: (value: string) => formOptionsFormatter(formOptions.recordRangeList, value),
        },
        {
          // 簽證項目
          key: 'recordItemsList',
          label: i18n.t('label.recordItems'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.recordItemsList, value),
        },
        {
          // 動作
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const table2 = reactive({
      fields_backend: [
        {
          // 序號
          key: 'no',
          label: i18n.t('label.no'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '5%' },
        },
        {
          // 技師名字
          key: 'engrName',
          label: i18n.t('label.engrName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 技師科別
          key: 'subject',
          label: i18n.t('label.subject'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.subject, value),
        },
        {
          // 簽證範圍
          key: 'recordRangeList',
          label: i18n.t('label.recordRangeList'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 簽證項目
          key: 'recordItemsList',
          label: i18n.t('label.recordItems'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.recordItemsList, value),
        },
        {
          // 預訂承辦日期
          key: 'execDateStart',
          label: i18n.t('label.execDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 動作
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    function getAllSysCodes() {
      formOptions.recordItemsList = [
        { value: '0', text: '設計' },
        { value: '1', text: '監造' },
        { value: '2', text: '施工' },
        { value: '99', text: '其他' },
      ];
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      axios
        .get('/adm-roles')
        .then(data => {
          formOptions.identityList = data.data
            .filter(item => item.roleId === 'ENG_01' || item.roleId === 'ENG_02' || item.roleId === 'ENG_03' || item.roleId === 'ENG_08')
            .map(item => ({ value: item.roleId, text: item.roleName }));
        })
        .catch(notificationErrorHandler(notificationService));
    }

    onMounted(() => {
      getAllSysCodes();
    });

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const handleQueryBtn = () => {
      iTableComponent.value.state.pagination.currentPage = 1;
      iTableComponent.value.emitPagination();
      // handleQuery();
    };

    const handleQuery = () => {
      const now = new Date();
      form.recordItemsList = '';
      form.recordRangeList = '';
      form.subject = '';
      for (let i = 0; i < form.recordItems.length; i++) {
        form.recordItemsList += $v.value.recordItems.$model[i] + ',';
      }
      for (let i = 0; i < form.recordRanges.length; i++) {
        form.recordRangeList += $v.value.recordRanges.$model[i] + ',';
      }
      for (let i = 0; i < form.subjects.length; i++) {
        form.subject += $v.value.subjects.$model[i] + ',';
      }
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true; // 才會顯示i-table
          switch (
            form.roleRef // 暫時用寫死的身分
          ) {
            case 'ENG_01':
              form.engrId = '00008333';
              break;
            case 'ENG_02':
              form.compIdno = '28412550';
              break;
            case 'ENG_03':
              form.execOrgId = '3.82.11.3';
              break;
          }

          axios
            .post('/eng-eng0301r1', form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.data.forEach(item => (item._showDetails = true));
              table.totalItems = data.totalElements;
              // console.log('回傳', data);
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const clear = () => {
      reset();
      queryStatus.value = false;
      table.data = undefined;
      table2.data = undefined;
    };

    const toEditView = (engCaseTotal: EngCaseTotalDTO, formStatus: FormStatusEnum, index?: number) => {
      navigateByNameAndParams('Eng0301EditInfo', { formStatus: formStatus });
    };

    const toQuery2 = async (formStatus: FormStatusEnum, index?: number) => {
      navigateByNameAndParams('Eng0301Query2', {});
    };

    function notBeforeExecDateStart(date: Date) {
      if (form.execDateStart) return date < new Date(form.execDateStart);
    }

    function notAfterExecDateEnd(date: Date) {
      if (form.execDateEnd) return date > new Date(form.execDateEnd);
    }

    function notBeforeTenderDateStart(date: Date) {
      if (form.tenderDateStart) return date < new Date(form.tenderDateStart);
    }

    function notAfterTenderDateEnd(date: Date) {
      if (form.tenderDateEnd) return date > new Date(form.tenderDateEnd);
    }

    return {
      stepVisible,
      formOptions,
      $v,
      iTableComponent,
      handlePaginationChanged,
      handleQueryBtn,
      handleQuery,
      queryStatus,
      table,
      reset,
      notBeforeExecDateStart,
      notAfterExecDateEnd,
      notBeforeTenderDateStart,
      notAfterTenderDateEnd,
      toEditView,
      toQuery2,
      clear,
      table2,
      FormStatusEnum,
      EngCaseTotalDTO,
      formatToString,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
    dateStringToMingo: (value: string) => formatToString(value, '/', '-'),
  },
};
</script>
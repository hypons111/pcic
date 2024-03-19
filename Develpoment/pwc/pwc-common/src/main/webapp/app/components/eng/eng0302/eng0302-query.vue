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
              <i-form-group-check :label="$t('label.caseType') + '：'" 
              :item="$v.caseType">
                <b-form-select v-model="$v.caseType.$model" 
                :options="formOptions.caseType">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <!-- 簽證項目 -->
              <i-form-group-check
                :label="$t('label.recordItems') + '：'"
                :item="$v.recordItemsList"
              >
                <b-form-checkbox-group
                  v-model="$v.recordItems.$model"
                  :options="formOptions.recordItemsList"
                ></b-form-checkbox-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 工程種類 -->
              <i-form-group-check :label="$t('label.projectType') + '：'" 
              :item="$v.projectType">
                <b-form-select v-model="$v.projectType.$model" 
                :options="formOptions.projectType">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 案件編號 -->
              <i-form-group-check :label="$t('label.caseNum') + '：'"
              :item="$v.caseNo">
                <b-form-input v-model="$v.caseNo.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 案件名稱 -->
              <i-form-group-check :label="$t('label.caseNa') + '：'" 
              :item="$v.caseName">
                <b-form-input v-model="$v.caseName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 執行機關名稱 -->
              <i-form-group-check :label="$t('label.wkutName') + '：'"
              :item="$v.execOrgName">
                <b-form-input v-model="$v.execOrgName.$model"
                 trim></b-form-input>
              </i-form-group-check>
              <!-- 執行機關代碼 -->
              <i-form-group-check :label="$t('label.execOrgId') + '：'" 
              :item="$v.execOrgId">
                <b-form-input v-model="$v.execOrgId.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 承辦日期 -->
              <i-form-group-check :label="$t('label.execDate') + '：'"
              :dual1="$v.execDateStart" :dual2="$v.execDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.execDateStart.$model"
                    :disabled-date="notAfterExecDateEnd"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.execDateEnd.$model"
                    :disabled-date="notBeforeExecDateStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
              <!-- 預計簽證日期(應登錄日期) -->
              <i-form-group-check :label="$t('label.expectDate') + '：'"
              :dual1="$v.expectDateStart" :dual2="$v.expectDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.expectDateStart.$model"
                    :disabled-date="notAfterExpectDateEnd"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.expectDateEnd.$model"
                    :disabled-date="notBeforeExpectDateStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 得標機構名稱 -->
              <i-form-group-check :label="$t('label.compNa') + '：'"
              :item="$v.compName">
                <b-form-input v-model="$v.compName.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 得標機構統編 -->
              <i-form-group-check :label="$t('label.compIdn') + '：'" >
                <b-form-input v-model="$v.compIdno.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 技師姓名 -->
              <i-form-group-check :label="$t('label.engrName') + '：'"
              :item="$v.engrName">
                <b-form-input v-model="$v.engrName.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 技師身分證字號 -->
              <i-form-group-check :label="$t('label.engrIdno') + '：'" 
              :item="$v.engrId">
                <b-form-input v-model="$v.engrId.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
            <!-- 逾期系列 -->
              <i-form-group-check class="col-12"
									label-cols="2"
									content-cols="6"
                :item="$v.timeoutList"
              >
                <b-form-checkbox-group
                  v-model="$v.timeouts.$model"
                  :options="formOptions.timeoutList"
                ></b-form-checkbox-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <b-col offset="8">
              <!-- 查詢 -->
              <i-button type="search" @click="handleQueryBtn"/>
              <!-- 清除 -->
              <i-button type="x-circle" @click="clear"/>
            </b-col>
            </b-form-row>
            
          </b-collapse>
        </div>
      </div>
    </section>
    <br/>
    <section class="container mt-2" v-show="queryStatus">
      <b-form-row>
        <b-col >
          <b-button variant="info" @click="submitForm(true)">產生excel報表</b-button>
          <b-button variant="info" @click="submitForm(true)">發送預期未填通知郵件</b-button>
          <b-button variant="info" @click="submitForm(true)">發送未填通知郵件</b-button>
          <b-button variant="info" @click="clearCheck">清除選取</b-button>
        </b-col>
      </b-form-row>
      <br/>
      <i-table
        :hideNo="true"
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        :items="table.data"
        @changePagination="handlePaginationChanged"
      >
        <!-- 設定欄位標題 -->
        <template #head(check)>  
          <b-form-checkbox v-model="isAllCheck" 
          @change="changeCheck"></b-form-checkbox>
        </template>

        <template #cell(check)="row">
          <b-form-checkbox v-model="row.item.check"></b-form-checkbox>
        </template>

        <template #cell(action)="row">
          <!-- 檢視 -->
          <i-button size="sm" type="binoculars" 
          @click="toEditView('Eng0302Info', row.item, FormStatusEnum.READONLY, row.index)"
          ></i-button>
          <!-- 編輯 -->
          <i-button
            size="sm"
            type="pencil-square"
            @click="toEditView('Eng0302Edit', row.item, FormStatusEnum.EDIT, row.index)"
          >
          </i-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, ref, reactive } from '@vue/composition-api';
import {useValidation} from '@/shared/form';
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
import { useBvModal } from '@/shared/modal';

export default {
  name: 'Eng0302Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(prop) {
    let isAllCheck = ref(false);
    const stepVisible = reactive({
      step1: true,
    })
    const iTableComponent = ref(null);
    const queryStatus = ref(false);
    const admSysCodeService = new AdmSysCodeService();
    const $bvModal = useBvModal();

    const formOptions = reactive({
      recordItemsList: [],
      caseType: [],
      projectType: [],
      timeoutList: []  
    });

    const formDefault = {
      caseType: '',
      recordItemsList: '',
      recordItems: [],
      projectType: '',
      caseNo: '',
      caseName: '',
      execOrgName: '',
      execOrgId: '',
      execDateStart: null,
      execDateEnd: null,
      expectDateStart: null,
      expectDateEnd: null,
      tenderDateStart: null,
      tenderDateEnd: null,
      compName: '',
      compIdno: '',
      engrName: '',
      engrId: '',
      timeoutList: '',
      timeouts: [],
    }

    const form = reactive(Object.assign({}, formDefault));

    const rules = {
      caseType: {},
      recordItemsList: {},
      recordItems: {},
      projectType: {},
      caseNo: {},
      caseName: {},
      execOrgName: {},
      execOrgId: {},
      execDateStart: {},
      execDateEnd: {},
      expectDateStart: {},
      expectDateEnd: {},
      tenderDateStart: {},
      tenderDateEnd: {},
      compName: {},
      compIdno: {},
      engrName: {},
      engrId: {},
      timeoutList: {},
      timeouts: {},
    }

    const { $v, checkValidity, reset } = 
    useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    function submitForm(isCheckReq: boolean) {
      $v.value.$reset();
      this.$nextTick(() => {
        $bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
        if (isOK) {
          $bvModal.msgBoxOk('發送成功!');
        }
      })})
    }

    function getAllSysCodes() {
      formOptions.caseType = [
        { value: '0', text: 'BOT案件' },
        { value: '1', text: '機關自辦' },
        { value: '2', text: '機關委辦' }
      ];
      formOptions.recordItemsList = [
        { value: '0', text: '設計' },
        { value: '1', text: '監造' },
        { value: '2', text: '施工' },
        { value: '99', text: '其他' }
      ];
      formOptions.timeoutList = [
        { value: '0', text: '顯示逾期登錄' },
        { value: '1', text: '顯示逾期未填' },
        { value: '2', text: '顯示未填' }
      ];
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    onMounted(() => {
      getAllSysCodes();
    })

    const changeCheck = () => {
      if(isAllCheck.value) {
        for(let i = 0; i < data.length; i++) {
          data[i].check = true;
        }
      } else {
        for(let i = 0; i < data.length; i++) {
          data[i].check = false;
        }
      }
    }

    const handleQueryBtn = () => {
        // iTableComponent.value.state.pagination.currentPage = 1;
        // iTableComponent.value.emitPagination();
        handleQuery();
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const handleQuery = () => {
      const now = new Date();
      $v.value.recordItemsList.$model = "";
      for(let i = 0; i < $v.value.recordItems.$model.length; i++) {
        $v.value.recordItemsList.$model += $v.value.recordItems.$model[i] + ',';
      }
      table.data = data;
      table.totalItems = data.length;
      queryStatus.value = true;
    }

    const clear = () => {
      reset();
      queryStatus.value = false;
      table.data = [];
    }

    const toEditView =(name: string, engCaseTotal: EngCaseTotalDTO, 
    formStatus: FormStatusEnum, index?: number) => {
      navigateByNameAndParams(name, { formStatus: formStatus });
    }

    const clearCheck = () => {
      isAllCheck.value = false;
      for(let i = 0; i < data.length; i++) {
        data[i].check = false;
      }
    }

    function notBeforeExecDateStart(date: Date) {
      if (form.execDateStart) return date < new Date(form.execDateStart);
    }

    function notAfterExecDateEnd(date: Date) {
      if (form.execDateEnd) return date > new Date(form.execDateEnd);
    }

    // function notBeforeTenderDateStart(date: Date) {
    //   if (form.tenderDateStart) return date < new Date(form.tenderDateStart);
    // }

    // function notAfterTenderDateEnd(date: Date) {
    //   if (form.tenderDateEnd) return date > new Date(form.tenderDateEnd);
    // }

    function notAfterExpectDateEnd(date: Date) {
      if (form.expectDateEnd) return date > new Date(form.expectDateEnd);
    }

    function notBeforeExpectDateStart(date: Date) {
      if (form.expectDateStart) return date < new Date(form.expectDateStart);
    }

    const table = reactive({
        fields_backend: [
          {
            // 打勾框框
            key: 'check',
            label: '',
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // thStyle: { width: '10%' },
          },
          {
            // 案件編號
            key: 'caseNo',
            label: i18n.t('label.caseNum'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 案件名稱
            key: 'caseName',
            label: i18n.t('label.caseNa'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-left align-middle',
          },
          {
            // 工程種類
            key: 'projectType',
            label: i18n.t('label.projectType'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-left align-middle',
            formatter: (value: string) => 
            formOptionsFormatter(formOptions.projectType, value),
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
            // 技師名字
            key: 'engrName',
            label: i18n.t('label.engrName'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 序號
            key: 'no',
            label: i18n.t('label.no'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 簽證項目
            key: 'recordItemsList',
            label: i18n.t('label.recordItems'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-left align-middle',
            formatter: (value: string) => 
            formOptionsFormatter(formOptions.recordItemsList, value),
          },
          {
            // 預訂承辦日期
            key: 'execDateStart',
            label: i18n.t('label.execDate'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // formatter: (value: string) => formatToString(value, '/', '-'),
          },
          {
            // 應登錄日期
            key: 'expectDate',
            label: i18n.t('label.expectDate'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // formatter: (value: string) => formatToString(value, '/', '-'),
          },
          {
            // 實際登錄日期
            key: 'realTime',
            label: i18n.t('label.realTime'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // formatter: (value: string) => formatToString(value, '/', '-'),
          },
          {
            // 修改日期
            key: 'updateTime',
            label: i18n.t('label.updateTime'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // formatter: (value: string) => formatToString(value, '/', '-'),
          },
          {
            // 簽證內容
            key: 'recordContent',
            label: i18n.t('label.recordContent'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // formatter: (value: string) => formatToString(value, '/', '-'),
          },
          {
            // 簽證意見
            key: 'recordComment',
            label: i18n.t('label.recordComment'),
            sortable: true,
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
            // thStyle: { width: '10%' },
          },
        ],
        data: undefined,
        totalItems: undefined,
      });

    const data = [
        {
          caseNo: 'work1050912-1',
          caseName: '成功橋拓寬工程',
          projectType: '01',
          recordRangeList: '道路工程、橋樑工程及隧道工程',
          recordRangeOther: '',
          engrName: '王茲為',
          no: 1,
          recordItemsList: '1',
          recordItems: [],
          execDateStart: '106/10/26',
          execDateEnd: '108/11/14',
          expectDate: '106/10/26',
          realTime: '106/10/26',
          updateTime: '106/10/10',
          recordContent: '',
          recordComment: '',
          check: false

      }]

    return {
      stepVisible,
      iTableComponent,
      queryStatus,
      formOptions,
      $v,
      data,
      table,
      handlePaginationChanged,
      handleQueryBtn,
      notBeforeExecDateStart,
      notAfterExecDateEnd,
      // notBeforeTenderDateStart,
      // notAfterTenderDateEnd,
      notAfterExpectDateEnd,
      notBeforeExpectDateStart,
      clear,
      changeCheck,
      clearCheck,
      isAllCheck,
      toEditView,
      FormStatusEnum,
      submitForm
    }
  }
}
</script>
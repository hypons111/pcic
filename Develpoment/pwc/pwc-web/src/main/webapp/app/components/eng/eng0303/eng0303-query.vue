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
            </b-form-row>
            <b-form-row>
              <!-- 承辦日期(區間) -->
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
            </b-form-row>
            <b-form-row>
              <!-- 數量區間 -->
              <i-form-group-check :label="$t('label.quantity') + '：'"
              class="col-12" label-cols="2">
              <!-- :item="$v.compName" -->
              <b-form-input v-model="$v.quantity1.$model"
               class="col-3" trim></b-form-input> ~ 
              <b-form-input v-model="$v.quantity2.$model"
               class="col-3" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 金額 -->
              <i-form-group-check :label="$t('label.amount2') + '：'"
              class="col-12" label-cols="2">
              <!-- :item="$v.compName" -->
              <b-form-input v-model="$v.amount1.$model"
               class="col-3" trim></b-form-input> ~ 
              <b-form-input v-model="$v.amount2.$model"
               class="col-3" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 技師身分證字號 -->
              <i-form-group-check :label="$t('label.engrIdno') + '：'" 
              :item="$v.engrId">
                <b-form-input v-model="$v.engrId.$model" trim></b-form-input>
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
        <template #cell(action)="row">
          <!-- 檢視 -->
          <i-button size="sm" type="binoculars" 
          @click="toInfo(row.item, row.index)"></i-button>
          
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

export default {
  name: 'Eng0303Query',
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
    const stepVisible = reactive({ step1: true, })

    const formOptions = reactive({
      recordItemsList: [],
      caseType: [],
      projectType: [] 
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
      tenderDateStart: null,
      tenderDateEnd: null,
      compName: '',
      compIdno: '',
      engrName: '',
      engrId: '',
      quantity1: 0,
      quantity2: 0,
      amount1: 0,
      amount2: 0,
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
      tenderDateStart: {},
      tenderDateEnd: {},
      compName: {},
      compIdno: {},
      engrName: {},
      engrId: {},
      quantity1: {},
      quantity2: {},
      amount1: {},
      amount2: {},
    }

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    onMounted(() => {
      getAllSysCodes();
    })

    const handleQueryBtn = () => {
      handleQuery();
    }

    const handleQuery = () => {
      table.data = data;
      table.totalItems = data.length;
      queryStatus.value = true;
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const clear = () => {
      reset();
      queryStatus.value = false;
      table.data = [];
    }

    function toInfo(EngCaseTotalDTO: EngCaseTotalDTO, index?: number) {
      navigateByNameAndParams('Eng0303Info', { EngCaseTotalDTO })
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
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    function notBeforeExecDateStart(date: Date) {
    if (form.execDateStart) return date < new Date(form.execDateStart);
    }

    function notAfterExecDateEnd(date: Date) {
      if (form.execDateEnd) return date > new Date(form.execDateEnd);
    }

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
          // 執照號碼
          key: 'engrLicenseDocNo',
          label: i18n.t('label.engrLicenseDocNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 簽證數量
          key: 'signQuantity',
          label: i18n.t('label.signQuantity'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
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
      data: [],
      totalItems: undefined,
    });

    const data = [
      {
        engrName: '黃志誠',
        engrLicenseDocNo: '技執字第007391號',
        signQuantity: '27',
      }
    ]

    return {
      $v,
      formOptions,
      stepVisible,
      notAfterExecDateEnd,
      notBeforeExecDateStart,
      handleQueryBtn,
      handlePaginationChanged,
      clear,
      table,
      queryStatus,
      toInfo
    }
  }
}

</script>
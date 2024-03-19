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
        <div class="card-body col-12">
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
              <!-- 執行機關代碼 -->
              <i-form-group-check :label="$t('label.execOrgId') + '：'" 
              :item="$v.execOrgId">
                <b-form-input v-model="$v.execOrgId.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 決標日期 -->
              <i-form-group-check :label="$t('label.tenderDate') + '：'"
              :dual1="$v.tenderDateStart" :dual2="$v.tenderDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.tenderDateStart.$model"
                    :disabled-date="notAfterTenderDateEnd"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.tenderDateEnd.$model"
                    :disabled-date="notBeforeTenderDateStart"
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
              <!-- 實際簽證日期(實際登錄日期) -->
              <i-form-group-check :label="$t('label.realTime') + '：'"
              :dual1="$v.realTimeStart" :dual2="$v.realTimeDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.realTimeStart.$model"
                    :disabled-date="notAfterRealTimeEnd"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.expectDateEnd.$model"
                    :disabled-date="notBeforeRealTimeStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 逾期系列 -->
              <i-form-group-check class="col-8"
									label-cols="3"
									content-cols="6"
                :item="$v.timeoutList"
              >
                <b-form-checkbox-group
                  v-model="$v.timeouts.$model"
                  :options="formOptions.timeoutList"
                ></b-form-checkbox-group>
                
              </i-form-group-check>
                <!-- 清除 -->
                <i-button type="x-circle" @click="clear"/>
              
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                匯出欄位
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step2 = !stepVisible.step2">
                <font-awesome-icon v-if="stepVisible.step2" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step2" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-12">
          <b-collapse v-model="stepVisible.step2">
            <i-form-group-check class="col-12" label-cols="0" 
            content-cols="12" >
              <!-- 全選checkbox -->
              <div class="col-4 d-inline-block">
                <b-form-checkbox v-model="isAllCheck" 
              @change="changeCheck">全選</b-form-checkbox>
              </div>
            </i-form-group-check>
          <!-- 欄位的checkbox們 -->
            <i-form-group-check class="col-12" label-cols="0" 
            content-cols="12" :item="$v.columnList">
            <div v-for="option in formOptions.columnList" class="col-3 d-inline-block">
              <b-form-checkbox v-model="option.value">
                {{ option.text }}
              </b-form-checkbox>
            </div>
          </i-form-group-check>
            <b-form-row>
              <b-col offset="8">
                <!-- 匯出 -->
              <i-button @click="submitForm" type="box-arrow-up-right"/>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
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
  name: 'Eng0306Export',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    let isAllCheck = ref(false);
    const admSysCodeService = new AdmSysCodeService();
    const $bvModal = useBvModal();
    const stepVisible = reactive({ step1: true, step2: true })
    const formOptions = reactive({
      recordItemsList: [],
      caseType: [],
      projectType: [],
      timeoutList: [],
      columnList: [],  
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
      realTimeStart: null,
      realTimeEnd: null,
      compName: '',
      compIdno: '',
      engrName: '',
      engrId: '',
      timeoutList: '',
      timeouts: [],
      columnList: [],
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
      realTimeStart: {},
      realTimeEnd:{},
      compName: {},
      compIdno: {},
      engrName: {},
      engrId: {},
      timeoutList: {},
      timeouts: {},
      columnList: {},
    }

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    const notificationService: NotificationService = useNotification();

    onMounted(() => {
      getAllSysCodes();
    })

    const changeCheck = () => {
      if(isAllCheck.value) {
        for(let i = 0; i < formOptions.columnList.length; i++) {
          formOptions.columnList[i].value = true;
        }
      } else if(!isAllCheck.value) {
        for(let i = 0; i < formOptions.columnList.length; i++) {
          formOptions.columnList[i].value = false;
        }
      }
    }

    const clear = () => {
      reset();
    }

    function submitForm(isCheckReq: boolean) {
      $v.value.$reset();
      this.$nextTick(() => {
        $bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
          if (isOK) {
            $bvModal.msgBoxOk('匯出成功!');
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
      formOptions.columnList = [
        { value: false, column: 'engCaseId', text: '案號' },
        { value: false, column: 'execOrgName', text: '執行機關' },
        { value: false,  column: 'caseName', text: '案件名稱' },
        { value: false,  column: 'compIdno', text: '得標機構統一編號' },
        { value: false,  column: 'compName', text: '得標機構名稱' },
        { value: false,  column: 'engrName', text: '技師姓名' },
        { value: false,  column: 'idno', text: '技師身分證字號' },
        { value: false,  column: 'subject', text: '承辦技師科別' },
        { value: false,  column: 'engrLicenseDocNo', text: '執業執照字號' },
        { value: false,  column: 'tenderPrice', text: '決標金額' },
        { value: false,  column: 'execDateStart', text: '參與開始日期' },
        { value: false,  column: 'execDateEnd', text: '參與結束日期' },
        { value: false,  column: 'recordContent', text: '簽證內容' },
        { value: false,  column: 'recordComment', text: '簽證意見' },
        { value: false,  column: 'tenderDate', text: '決標日期' },
        { value: false,  column: 'realTime', text: '實際登錄日期' },
        { value: false,  column: 'expectDate', text: '應簽證日期' },
        { value: false,  column: 'updateTime', text: '修改日期' },
        { value: false,  column: 'itemmsList', text: '簽證項目' },
        { value: false,  column: 'closeDate', text: '完成簽證日期' },
        { value: false,  column: 'projectType', text: '工程種類' },
        { value: false,  column: 'recordRangeList', text: '簽證範圍' },
      ]
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

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
    function notAfterExpectDateEnd(date: Date) {
      if (form.expectDateEnd) return date > new Date(form.expectDateEnd);
    }
    function notBeforeExpectDateStart(date: Date) {
      if (form.expectDateStart) return date < new Date(form.expectDateStart);
    }
    function notAfterRealTimeEnd(date: Date) {
      if (form.realTimeEnd) return date > new Date(form.realTimeEnd);
    }
    function notBeforeRealTimeStart(date: Date) {
      if (form.realTimeStart) return date < new Date(form.realTimeStart);
    }

    return {
      stepVisible,
      formOptions,
      $v,
      notBeforeExecDateStart,
      notAfterExecDateEnd,
      notAfterExpectDateEnd,
      notBeforeExpectDateStart,
      notBeforeTenderDateStart,
      notAfterTenderDateEnd,
      notAfterRealTimeEnd,
      notBeforeRealTimeStart,
      clear,
      isAllCheck,
      changeCheck,
      submitForm,
    }
  }
}
</script>
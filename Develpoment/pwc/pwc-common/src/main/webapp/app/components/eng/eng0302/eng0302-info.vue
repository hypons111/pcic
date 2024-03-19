<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            案件資訊
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 編號 -->
            <i-form-group-check labelStar :label="$t('label.engEngrCertificateNo') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.caseNo }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.caseNo"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
            <!-- 案件類型 -->
            <i-form-group-check :label="$t('label.caseType') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.caseType, data.caseType) }}
              </div>
              <b-form-select v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.caseType" :options="formOptions.caseType">
                <template #first>
                  <option value=""></option>
                </template>
              </b-form-select>
              <b-form-select v-if="formStatusRef === FormStatusEnum.CREATE">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 案件名稱 -->
            <i-form-group-check labelStar :label="$t('label.caseNa') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.caseName }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.caseName"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 招標(自辦)機關代碼和名稱 -->
            <i-form-group-check labelStar :label="$t('label.orgIdName') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.orgId }}
              </div>
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.orgName }}
              </div>
            </i-form-group-check>
            <i-form-group-check labelStar :label="$t('label.wkut') + '：'">
              <!-- 執行機關 -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.execOrgId }}
              </div>
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.execOrgName }}
              </div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 聯絡人 -->
            <i-form-group-check :label="$t('label.contactPerson') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.contactPerson }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.contactPerson"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 聯絡人電話 -->
          <b-form-row>
            <i-form-group-check :label="$t('label.contactTel') + '：'" class="col-12" label-cols="2" content-cols="6">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.contactTel }}
              </div>
              <i-tel
                v-if="formStatusRef === FormStatusEnum.EDIT"
                ref="tel1Component"
                show-ext
                show-tel-type
                v-model="data.contactTel"
                maxlength="50"
              ></i-tel>
              <i-tel v-if="formStatusRef === FormStatusEnum.CREATE" maxlength="50"> </i-tel>
            </i-form-group-check>
          </b-form-row>
          <!-- 服務項目及內容摘要 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.entrustitem') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.entrustItem }}
              </div>
              <b-form-textarea rows="4" maxlength="80" trim lazy v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.entrustItem">
              </b-form-textarea>
              <b-form-textarea v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-textarea>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 決標或自辦起始日期 -->
            <i-form-group-check :label="$t('label.tenderDate') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.tenderDateStart }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.tenderDateStart"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
            <!-- 決標金額 -->
            <i-form-group-check :label="$t('label.ctSum') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.ctSum }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.ctSum"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 預定辦理日期 -->
            <i-form-group-check labelStar :label="$t('label.execDate') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.execDateStart }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.execDateStart"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 工程種類 -->
            <i-form-group-check labelStar :label="$t('label.projectType') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.projectType, data.projectType) }}
              </div>
              <b-form-select v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.projectType" :options="formOptions.projectType">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
              <b-form-select v-if="formStatusRef === FormStatusEnum.CREATE" :options="formOptions.projectType">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 簽證範圍 -->
            <i-form-group-check labelStar class="col-12" label-cols="2" :label="$t('label.recordRangeList') + '：'" :item="$v.recordRanges">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.recordRangeList, data.recordRangeList) }}
              </div>
              <b-form-checkbox-group
                v-if="formStatusRef === FormStatusEnum.EDIT"
                v-model="data.recordRanges"
                :options="formOptions.recordRangeList"
              ></b-form-checkbox-group>
              <b-form-checkbox-group
                v-if="formStatusRef === FormStatusEnum.CREATE"
                :options="formOptions.recordRangeList"
              ></b-form-checkbox-group>
              <b-form-input class="col-4" v-if="formStatusRef !== FormStatusEnum.READONLY" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 簽證項目 -->
            <i-form-group-check labelStar class="col-12" label-cols="2" :label="$t('label.recordItems') + '：'" :item="$v.recordItemsList">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.recordItemsList, data.recordItemsList) }}
              </div>
              <b-form-checkbox-group
                v-if="formStatusRef === FormStatusEnum.EDIT"
                v-model="data.recordItems"
                :options="formOptions.recordItemsList"
              ></b-form-checkbox-group>
              <b-form-checkbox-group
                v-if="formStatusRef === FormStatusEnum.CREATE"
                :options="formOptions.recordItemsList"
              ></b-form-checkbox-group>
              <b-form-input class="col-4" v-if="formStatusRef !== FormStatusEnum.READONLY" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.remark') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{}}
              </div>
              <b-form-textarea rows="4" maxlength="80" trim lazy v-if="formStatusRef === FormStatusEnum.EDIT"> </b-form-textarea>
              <b-form-textarea v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-textarea>
            </i-form-group-check>
          </b-form-row>
        </div>
      </div>
    </section>
    <br />
    <section>
      <!-- 得標機構 -->
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            得標機構
          </h5>
        </div>
        <div class="card-body">
          <b-table sticky-header :items="compTable.compData" :fields="compTable.fields" bordered responsive="sm">
            <!-- 承辦日期 -->
            <template #cell(execDate)="row">
              <!-- <div >
                <i-form-input type="text"  
              v-model="row.item.execDateStart" 
              :rules="formInnerTableRules.nameRule"></i-form-input>
              ~<br/>
              <i-form-input type="text"
              v-model="row.item.execDateEnd" 
              :rules="formInnerTableRules.nameRule"></i-form-input>
              </div> -->
              <span
                >{{ row.item.execDateStart }}<br />~ <br />
                {{ row.item.execDateEnd }}</span
              >
            </template>
          </b-table>
        </div>
      </div>
    </section>

    <br />
    <section>
      <!-- 簽證技師 -->
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            簽證技師
          </h5>
        </div>
        <div class="card-body">
          <b-table sticky-header :items="engrTable.engrData" :fields="engrTable.fields" bordered responsive="sm">
            <!-- 承辦日期 -->
            <template #cell(execDate)="row">
              <!-- <div >
                <i-form-input type="text"  
              v-model="row.item.execDateStart" 
              :rules="formInnerTableRules.nameRule"></i-form-input>
              ~<br/>
              <i-form-input type="text"
              v-model="row.item.execDateEnd" 
              :rules="formInnerTableRules.nameRule"></i-form-input>
              </div> -->
              <span
                >{{ row.item.execDateStart }}<br />~ <br />
                {{ row.item.execDateEnd }}</span
              >
            </template>
          </b-table>
        </div>
      </div>
    </section>
    <br />
    <b-form-row>
      <b-col offset="5">
        <!-- 返回 -->
        <i-button type="arrow-left" @click="toLast" />
      </b-col>
    </b-form-row>
    <br />
    <b-form-row>
      <b-col offset="5">
        <!-- 上一筆 -->
        <i-button type="arrow-bar-left" />
        <!-- 下一筆 -->
        <i-button type="arrow-bar-right" />
      </b-col>
    </b-form-row>
  </div>
</template>

<script lang="ts">
import { watch, toRef, toRefs, ref, unref, defineComponent, inject, onMounted, reactive } from '@vue/composition-api';
import { Eng0703, IEng0703 } from '@/shared/model/eng/eng0703.model';
import { FormStatusEnum, RoleEnum } from '@/shared/enum';
import { string } from 'prop-types';
import { handleBack } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { formOptionsFormatter } from '@/shared/formatter/common';
import axios, { AxiosResponse } from 'axios';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';
import { EngCaseTotalDTO } from '@/shared/model/eng/eng-case.model';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import i18n from '@/shared/i18n';

export default {
  name: 'Eng0302Info',
  props: {
    formStatus: {
      type: String,
      required: false,
    },
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const formStatusRef = ref(props.formStatus);
    const admSysCodeService = new AdmSysCodeService();
    const isReload = ref(false); // 是否需要重新查詢

    let formDefault = new EngCaseTotalDTO();
    const form = reactive(Object.assign({}, formDefault));
    // 驗證
    const rules = {
      caseType: {},
      recordItemsList: {},
      recordItems: {},
      recordRanges: {},
      recordRangeList: {},
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
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const formOptions = reactive({
      recordItemsList: [], // 簽證項目
      recordRangeList: [], // 簽證範圍
      caseType: [], // 案件類型
      projectType: [], // 工程種類
      timeoutList: [],
    });

    onMounted(() => {
      getAllSysCodes();
      compTable.compData = compData;
      engrTable.engrData = engrData;
    });

    function toLast() {
      navigateByNameAndParams('Eng0302Query', {
        isReload: isReload.value,
        isNotKeepAlive: true,
        formStatus: formStatusRef,
      });
      isReload.value = false;
    }

    function getAllSysCodes() {
      formOptions.caseType = [
        { value: '0', text: 'BOT案件' },
        { value: '1', text: '機關自辦' },
        { value: '2', text: '機關委辦' },
      ];
      formOptions.recordItemsList = [
        { value: '0', text: '設計' },
        { value: '1', text: '監造' },
        { value: '2', text: '施工' },
        { value: '99', text: '其他' },
      ];
      formOptions.recordRangeList = [
        { value: '0', text: '航空測量' },
        { value: '1', text: '工址調查' },
        { value: '2', text: '定線工程' },
        { value: '3', text: '機電工程' },
        { value: '4', text: '照明工程' },
        { value: '5', text: '道路工程、橋樑工程及隧道工程' },
        { value: '6', text: '水理分析、排水工程' },
        { value: '7', text: '交通工程' },
        { value: '99', text: '其它' },
      ];
      formOptions.timeoutList = [
        { value: '0', text: '顯示逾期登錄' },
        { value: '1', text: '顯示逾期未填' },
        { value: '2', text: '顯示未填' },
      ];
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const data = {
      caseNo: 'work1050912-1',
      caseName: '成功橋拓寬工程',
      caseType: '0',
      recordItemsList: '1',
      recordItems: ['1'],
      projectType: '01',
      execOrgName: '臺北市政府工務局新建工程處',
      orgId: '3.79.11.1',
      orgName: '臺北市政府工務局新建工程處',
      execOrgId: '3.79.11.1',
      execDateStart: '106/10/26',
      execDateEnd: '108/11/14',
      tenderDateStart: '106/01/26',
      tenderDateEnd: null,
      compName: '建業工程顧問有限公司',
      compIdno: '',
      engrId: '',
      recordRanges: ['5'],
      recordRangeList: '5',
      recordRangeOther: '',
      contactPerson: '陳濬為',
      contactTel: '02-23456789',
      ctSum: '173,965,946',
      entrustItem: '',
    };

    const compData = [
      {
        engYearreportEngrId: 1,
        agentName: '建業工程顧問有限公司',
        compIdno: '86151049',
        caslicenseNoeNo: '工程技顧登字第000201號',
        execDateStart: '106/03/17',
        execDateEnd: '108/11/14',
      },
    ];

    const engrData = [
      {
        engYearreportEngrId: 1,
        no: 1,
        compName: '建業工程顧問有限公司',
        compNames: ['0'],
        idno: 'F125069458',
        engrName: '王茲為',
        execDateStart: '106/10/26',
        execDateEnd: '108/11/14',
        engrCertificate: [''],
        engrLicenseDocNo: '技執字第007123號',
        recordRangeList: '5',
        recordRanges: ['5'],
        recordItems: ['1'],
        recordItemsList: '1',
        docNo: '',
        closeDate: '108/11/14',
        signStartDate: '106/01/26',
        remark: '',
        engEngrCertificateIdList: '土木工程',
        engEngrCertificates: ['0'],
      },
    ];

    const compTable = reactive({
      fields: [
        {
          // 項次
          key: 'engYearreportEngrId',
          label: i18n.t('label.engYearreportEngrId'),
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {
          // 機關名稱
          key: 'agentName',
          label: i18n.t('label.agentName'),
          sortable: false,
          // thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
        },
        {
          // 統編
          key: 'compIdno',
          label: i18n.t('label.compIdno'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {
          // 登記證字號
          key: 'caslicenseNoeNo',
          label: i18n.t('label.licenseNo'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {
          // 預定承辦日期
          key: 'execDate',
          label: i18n.t('label.execDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      compData: undefined,
    });

    const engrTable = reactive({
      fields: [
        {
          // 項次
          key: 'engYearreportEngrId',
          label: i18n.t('label.engYearreportEngrId'),
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {
          // 序號
          key: 'no',
          label: i18n.t('label.no'),
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {
          // 技師
          key: 'engrName',
          label: i18n.t('label.engrName'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {
          // 科別
          key: 'engEngrCertificateIdList',
          label: i18n.t('label.subject'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
        },
        {
          // 簽證範圍
          key: 'recordRangeList',
          label: i18n.t('label.recordRangeList'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
          formatter: (value: string) => formOptionsFormatter(formOptions.recordRangeList, value),
        },
        {
          // 簽證項目
          key: 'recordItemsList',
          label: i18n.t('label.recordItems'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
          formatter: (value: string) => formOptionsFormatter(formOptions.recordItemsList, value),
        },
        {
          // 辦理時間
          key: 'execDate',
          label: i18n.t('label.execDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      engrData: undefined,
    });

    return {
      formStatusRef,
      FormStatusEnum,
      formOptionsFormatter,
      formOptions,
      data,
      $v,
      compTable,
      compData,
      engrTable,
      engrData,
      toLast,
    };
  },
};
</script>
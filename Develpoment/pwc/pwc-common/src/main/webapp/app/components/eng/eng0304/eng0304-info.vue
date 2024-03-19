<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">案件資訊</h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 案件編號 -->
            <i-form-group-check :label="$t('label.caseNum') + '：'">
              <div>{{ data.caseNum }}</div>
            </i-form-group-check>
            <!-- 案件類型 -->
            <i-form-group-check :label="$t('label.caseType') + '：'">
              <div>{{ data.caseType }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 案件名稱 -->
            <i-form-group-check :label="$t('label.caseNa') + '：'">
              <div>{{ data.caseNa }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 招標機關代號 名稱-->
            <i-form-group-check :label="$t('label.orgIdName') + '：'">
              <div>{{ data.orgId }}</div>
              <div>{{ data.orgName }}</div>
            </i-form-group-check>
            <!-- 執行機關 -->
            <i-form-group-check labelStar :label="$t('label.wkut') + '：'">
              <div>{{ data.execOrgId }}</div>
              <div>{{ data.wkutName }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 聯絡人 -->
            <i-form-group-check :label="$t('label.contactPerson') + '：'">
              <div>{{ data.contactPerson }}</div>
            </i-form-group-check>
            <!-- 聯絡人電話 -->
            <i-form-group-check :label="$t('label.contactTel') + '：'">
              <div>{{ data.contactTel }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 服務項目及內容摘要 -->
            <i-form-group-check :label="$t('label.entrustitem') + '：'">
              <div>{{ data.serviceContent }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 決標或自辦起始日期 -->
            <i-form-group-check :label="$t('label.tenderDate') + '：'">
              <div>{{ data.tenderDate }}</div>
            </i-form-group-check>
            <!-- 決標金額 -->
            <i-form-group-check :label="$t('label.ctSum') + '：'">
              <div>{{ data.ctSum }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 預定辦理日期 -->
            <i-form-group-check :label="$t('label.execDate') + '：'">
              <div>{{ data.execDateStart + '~' + data.execDateEnd }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 工程種類 -->
            <i-form-group-check :label="$t('label.projectType') + '：'">
              <div>{{ data.projectType }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 簽證範圍 -->
            <i-form-group-check :label="$t('label.recordRangeList') + '：'">
              <div>{{ data.recordRangeList }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 簽證項目 -->
            <i-form-group-check :label="$t('label.recordItems') + '：'">
              <div>{{ data.recordItems }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 備註 -->
            <i-form-group-check :label="$t('label.remark') + '：'">
              <div>{{ data.remark }}</div>
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
            <template #cell(execDate)="row">
              {{ row.item.execDateStart + '~' + row.item.execDateEnd }}
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
            <template #cell(execDate)="row">
              {{ row.item.execDateStart + '~' + row.item.execDateEnd }}
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
  name: 'Eng0304Info',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const isReload = ref(false);
    let formDefault = new EngCaseTotalDTO();
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      caseNum: {},
      caseType: {},
      caseNa: {},
      orgId: {},
      orgName: {},
      execOrgId: {},
      wkutName: {},
      contactPerson: {},
      contactTel: {},
      execDateStart: {},
      execDateEnd: {},
      projectType: {},
      recordRangeList: {},
      recordItems: {},
      remark: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const formOptions = reactive({});

    onMounted(() => {
      compTable.compData = compData;
      engrTable.engrData = engrData;
    });

    function toLast() {
      navigateByNameAndParams('Eng0304Query', {
        isReload: isReload.value,
        isNotKeepAlive: true,
      });
      isReload.value = false;
    }

    const compTable = reactive({
      fields: [
        {
          // 項次
          key: 'no',
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
          // formatter: (value: string) =>
          // formOptionsFormatter(formOptions.recordRangeList, value)
        },
        {
          // 簽證項目
          key: 'recordItemsList',
          label: i18n.t('label.recordItems'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
          // formatter: (value: string) =>
          // formOptionsFormatter(formOptions.recordItemsList, value)
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

    const data = {
      caseNum: '106-B-11',
      caseType: 'BOT案件',
      caseNa: '古坑鄉大崎嶺農路支線擋土牆崩塌災後復工程',
      orgId: '3.76.49',
      orgName: '雲林縣政府',
      execOrgId: '3.76.49',
      wkutName: '雲林縣政府',
      contactPerson: '雲林縣政府',
      contactTel: '(05)5522000',
      serviceContent: '擋土牆崩塌災後復工程',
      tenderDate: '106/01/05',
      ctSum: '91,981',
      execDateStart: '106/01/12',
      execDateEnd: '106/07/13',
      projectType: '海岸、河川整治及水利工程',
      recordRangeList: '堤防',
      recordItems: '設計,監造',
      remark: '',
    };

    const compData = [
      {
        no: 1,
        agentName: '宏吉測量工程顧問有限公司',
        compIdno: '12850580',
        caslicenseNoeNo: '工程技顧登字第000418號',
        execDateStart: '106/01/12',
        execDateEnd: '106/07/13',
      },
    ];

    const engrData = [
      {
        no: 1,
        engrName: '李正民',
        engEngrCertificateIdList: '土木工程',
        recordRangeList: '道路工程、橋樑工程及隧道工程',
        recordItemsList: '設計,監造',
        execDateStart: '106/01/12',
        execDateEnd: '106/07/13',
      },
    ];

    return {
      data,
      compTable,
      engrTable,
      toLast,
    };
  },
};
</script>
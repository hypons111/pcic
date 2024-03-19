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
          </div>
        </div>
        <div class="card-body col-11">
          <b-form-row>
            <!-- 關鍵字 -->
            <i-form-group-check :label="$t('label.keyWord') + '：'" 
            >
            <b-form-input v-model="$v.searchKey.$model"></b-form-input>
            </i-form-group-check>
            <!-- 字別 -->
            <i-form-group-check :label="$t('label.judicialDataDocType') + '：'" 
            >
            <b-form-input v-model="$v.docType.$model"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 年度 -->
            <i-form-group-check :label="$t('label.iYear') + '：'" 
            >
            <b-form-input v-model="$v.judgeYear.$model"></b-form-input>
            </i-form-group-check>
            <!-- 號次 -->
            <i-form-group-check :label="$t('label.docNumber') + '：'" 
            >
            <b-form-input v-model="$v.docNumber.$model"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 裁判日期 -->
            <i-form-group-check :label="$t('label.judgeDate') + '：'" 
            >
              <i-dual-date-picker :dual1.sync="$v.judgeDate1.$model"
              :dual2.sync="$v.judgeDate2.$model">
              </i-dual-date-picker>
            </i-form-group-check>
            <!-- 裁判案由 -->
            <i-form-group-check :label="$t('label.judgeReson') + '：'" 
            >
            <b-form-input v-model="$v.judgeReson.$model"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 狀態 -->
            <i-form-group-check :label="$t('label.status') + '：'" 
            >
              <b-form-select v-model="$v.status.$model"
              :options="formOptions.status">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <b-col offset="2">
              <!-- 查詢 -->
              <i-button type="search" @click="handleQueryBtn"/>
              <!-- 清除 -->
              <i-button type="x-circle" @click="clear"/>
            </b-col>
          </b-form-row>
        </div>
      </div>
    </section>
    <section class="container mt-2" v-show="queryStatus">
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
      <template #cell(action)="row">
        <!-- 檢視 -->
          <i-button size="sm" type="binoculars" 
          @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)">
          </i-button>
      </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { Ref, onMounted, onActivated, watch, ref, toRef, reactive } from '@vue/composition-api';
import { RoleEnum, FormStatusEnum} from '@/shared/enum';
import { useValidation, validateState } from '@/shared/form';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { Pagination } from '@/shared/model/pagination.model';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { IEngManage, EngManage } from '@/shared/model/eng/eng-manage.model';
import EngManageService from '../eng0703/eng0703-manage.service';
import { Eng0703, IEng0703 } from '@/shared/model/eng/eng0703.model';
import { Cipher } from 'crypto';
import { navigateByNameAndParams } from '@/router/router';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { useBvModal } from '@/shared/modal';

export default {
  name: 'Eng0607Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    const queryStatus = ref(false);
    const formOptions = reactive({
      status: [],
    })

    const formDefault = {
      searchKey: '',
      docType: '',
      judgeYear: '',
      docNumber: '',
      judgeDate1: null,
      judgeDate2: null,
      judgeReson: '',
      status: '',
    }
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      searchKey: {},
      docType: {},
      judgeYear: {},
      docNumber: {},
      judgeDate1: {},
      judgeDate2: {},
      judgeReson: {},
      status: {},
    }
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

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

    const clear = () => {
      reset();
      queryStatus.value = false;
      table.data = [];
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const toEditView = async ({}, formStatus: FormStatusEnum) => {
      navigateByNameAndParams('Eng0607Info', {
        formStatus: formStatus
      }) 
    }

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.status = [
        { value: '1', text: "待確認" },
        { value: '2', text: "列管" },
        { value: '3', text: "結案" }
      ]
    }

    const table = reactive({
      fields_backend: [
        {
          // 項次
          key: 'index',
          label: i18n.t('label.index'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => 
          // formOptionsFormatter(formOptions.compType, value),
        },
        {
          // 裁判書序號
          key: 'engLawJudgmentId',
          label: i18n.t('label.engLawJudgmentId'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 裁判案由
          key: 'judgeReson',
          label: i18n.t('label.judgeReson'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 年度
          key: 'judgeYear',
          label: i18n.t('label.iYear'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 字別
          key: 'docType',
          label: i18n.t('label.judicialDataDocType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 號次
          key: 'docNumber',
          label: i18n.t('label.docNumber'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 裁判日期
          key: 'judgeDate',
          label: i18n.t('label.judgeDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 技師名/公司名
          key: 'searchKey',
          label: i18n.t('label.keyWord'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 搜尋種類，技師或公司
          key: 'searchType',
          label: i18n.t('label.searchType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 狀態
          key: 'status',
          label: i18n.t('label.status'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
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
        index: 1,
        engLawJudgmentId: 'CPEM,111,竹東交簡,18,20220412,1',
        judgeReson: '公共危險',
        judgeYear: '111	',
        docType: '竹東交簡',
        docNumber: '18',
        judgeDate: '111/04/12',
        searchKey: '無',
        searchType: '公司',
        status: '1',
      }
    ]

    return {
      $v,
      formOptions,
      handleQueryBtn,
      clear,
      handlePaginationChanged,
      table,
      queryStatus,
      toEditView,
      FormStatusEnum,
    }
  }
}
</script>
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
            <!-- 公司類型 -->
            <i-form-group-check :label="$t('label.compType') + '：'">
              <b-form-select v-model="$v.compType.$model" :options="formOptions.compType">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 執業狀態 -->
            <i-form-group-check :label="$t('label.practice') + '：'">
              <b-form-select v-model="$v.applyItem.$model" :options="formOptions.applyItem">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 關鍵字 -->
            <i-form-group-check :label="$t('label.keyWord') + '：'">
              <b-form-input v-model="$v.keyWord.$model"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 日期區間 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.duringDate') + '：'">
              <i-dual-date-picker :dual1.sync="$v.duringDate1.$model" :dual2.sync="$v.duringDate1.$model"> </i-dual-date-picker>
            </i-form-group-check>
          </b-form-row>
          <!-- 7年以上實務經驗技師人數 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" :label="$t('label.isMore7YearAmount') + '：'">
              <b-form-input v-model="$v.isMore7YearAmount1.$model" class="col-3"></b-form-input> ~
              <b-form-input v-model="$v.isMore7YearAmount2.$model" class="col-3"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 技師人數 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" :label="$t('label.engrAmount') + '：'">
              <b-form-input v-model="$v.engrAmount1.$model" class="col-3"></b-form-input> ~
              <b-form-input v-model="$v.engrAmount2.$model" class="col-3"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 篩選 -->
          <b-form-row>
            <i-form-group-check :label="$t('label.filter') + '：'" class="col-12" label-cols="2">
              <div v-for="(item, index) in formOptions.filterList" class="col-5 d-inline-block">
                <b-form-checkbox v-model="item.isTrue" @change="checkValue">
                  {{ item.text }}
                </b-form-checkbox>
              </div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <b-col offset="8">
              <!-- 查詢 -->
              <i-button type="search" @click="handleQueryBtn" />
              <!-- 清除 -->
              <i-button type="x-circle" @click="clear" />
              <!-- 統計表 -->
              <b-button @click="submitForm" variant="info">統計表</b-button>
            </b-col>
          </b-form-row>
        </div>
      </div>
    </section>

    <section class="container mt-2" v-show="queryStatus">
      <!-- 匯出Excel名冊 -->
      <b-button variant="info" @click="submitForm">匯出Excel名冊</b-button>
      <!-- 匯出Xml名冊 -->
      <b-button variant="info" @click="submitForm">匯出Xml名冊</b-button>
      <i-table
        title="資料列表"
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        :items="table.data"
        @changePagination="handlePaginationChanged"
      >
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { Ref, onMounted, onActivated, watch, ref, toRef, reactive } from '@vue/composition-api';
import { RoleEnum, FormStatusEnum } from '@/shared/enum';
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
  name: 'Eng0804Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    const queryStatus = ref(false);
    const $bvModal = useBvModal();

    const formOptions = reactive({
      compType: [],
      applyItem: [],
      filterList: [],
    });

    const formDefault = {
      compType: '',
      applyItem: '',
      keyWord: '',
      duringDate1: '',
      duringDate2: '',
      isMore7YearAmount1: 0,
      isMore7YearAmount2: 0,
      engrAmount1: 0,
      engrAmount2: 0,
    };
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      compType: {},
      applyItem: {},
      keyWord: {},
      duringDate1: {},
      duringDate2: {},
      isMore7YearAmount1: {},
      isMore7YearAmount2: {},
      engrAmount1: {},
      engrAmount2: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const handleQueryBtn = () => {
      handleQuery();
    };

    const handleQuery = () => {
      table.data = data;
      table.totalItems = data.length;
      queryStatus.value = true;
    };

    const clear = () => {
      reset();
      queryStatus.value = false;
      table.data = [];
      formOptions.filterList.filter(item => {
        item.isTrue = false;
      }); // 清除checkbox的勾勾
    };

    onMounted(() => {
      getAllSysCodes();
    });

    function checkValue() {
      // checkbox變動的話
    }

    function submitForm(isCheckReq: boolean) {
      $v.value.$reset();
      this.$nextTick(() => {
        $bvModal.msgBoxConfirm('是否確認匯出？').then((isOK: boolean) => {
          if (isOK) {
            $bvModal.msgBoxOk('匯出成功!');
          }
        });
      });
    }

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.compType = formOptions.compType.filter(item => item.value === '0' || item.value === '1' || item.value === '9');
      formOptions.filterList = [
        { isTrue: false, value: '0', text: '尚未領有工程技術顧問公司登記證' },
        { isTrue: false, value: '1', text: '具技師之董事人數未達1/3' },
      ];
    }

    const table = reactive({
      fields_backend: [
        {
          // 公司類型
          key: 'type',
          label: i18n.t('label.compType'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.compType, value),
        },
        {
          // 公司名稱
          key: 'chName',
          label: i18n.t('label.comName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 統一編號
          key: 'compIdno',
          label: i18n.t('label.compIdno'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 身分證字號
          key: 'idno',
          label: i18n.t('label.idno'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 登記證字號
          key: 'licenseNo',
          label: i18n.t('label.licenseNo'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 7年以上實務經驗技師人數
          key: 'isMore7YearAmount',
          label: i18n.t('label.isMore7YearAmount'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 技師人數
          key: 'engrAmount',
          label: i18n.t('label.engrAmount'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 董事人數
          key: 'trusteeAmount',
          label: i18n.t('label.trusteeAmount'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 董事具有技師資格人數
          key: 'trusteeIsEngrAmount',
          label: i18n.t('label.trusteeIsEngrAmount'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 申請項目
          key: 'applyItems',
          label: i18n.t('label.applyItems'),
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
        type: '1',
        chName: '一方工程技術顧問有限公司',
        compIdno: '80222807',
        idno: 'M120299577',
        licenseNo: '工程技顧登字第000813號',
        isMore7YearAmount: 1,
        engrAmount: 1,
        trusteeAmount: 1,
        trusteeIsEngrAmount: 1,
        applyItems: '換發',
      },
      {
        type: '1',
        chName: '九固工程顧問有限公司',
        compIdno: '84785716',
        idno: 'T101772280',
        licenseNo: '工程技顧登字第000531號',
        isMore7YearAmount: 1,
        engrAmount: 1,
        trusteeAmount: 1,
        trusteeIsEngrAmount: 1,
        applyItems: '換發',
      },
      {
        type: '1',
        chName: '九宜工程顧問有限公司',
        compIdno: '23659266',
        idno: 'Q121004404',
        licenseNo: '工程技顧登字第000046號',
        isMore7YearAmount: 1,
        engrAmount: 1,
        trusteeAmount: 1,
        trusteeIsEngrAmount: 1,
        applyItems: '換發',
      },
    ];

    return {
      formOptions,
      table,
      queryStatus,
      handlePaginationChanged,
      handleQueryBtn,
      clear,
      $v,
      checkValue,
      submitForm,
    };
  },
};
</script>
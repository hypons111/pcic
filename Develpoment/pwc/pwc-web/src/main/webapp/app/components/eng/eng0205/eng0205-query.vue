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
            <!-- 年度 -->
            <i-form-group-check :label="$t('label.iYear') + '：'" 
            >
              <b-form-select v-model="$v.yyyy.$model"
              :options="formOptions.yyyy">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 公司名稱 -->
            <i-form-group-check :label="$t('label.comName') + '：'" 
            >
              <b-form-input v-model="$v.compName.$model"></b-form-input>
            </i-form-group-check>
            <!-- 統一編號 -->
            <i-form-group-check :label="$t('label.compIdno') + '：'" 
            >
              <b-form-input v-model="$v.compIdno.$model"></b-form-input>
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
        </div>
      </div>
    </section>
    <br/>
    <section class="container mt-2" v-show="queryStatus">
      <b-form-row >
          
        <b-col >
          <b-form-select style="width:30%" 
          v-model="$v.excel.$model"
          :options="formOptions.excel">
            <template #first>
              <option value="">請選擇</option>
            </template>
          </b-form-select>
          <b-button variant="info" @click="submitForm(true)">
          匯出Excel</b-button>
          <b-button variant="info" @click="toViewOther('Eng0205Query2')">
          [顧問公司]海外案件統計表</b-button>
          <b-button variant="info" @click="toViewOther('Eng0205Query3')">
          [事務所]海外案件統計表</b-button>
        </b-col>
      </b-form-row>
      <i-table
        title="資料列表"
        small
        show-empty
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
  name: 'Eng0205Query',
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
      yyyy: [],
      excel: [],
    })
    const formDefault = {
      yyyy: '',
      compName: '',
      compIdno: '',
      excel: '',
    }
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      yyyy: {},
      compName: {},
      compIdno: {},
      excel: {},
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

    function submitForm(isCheckReq: boolean) {
      $v.value.$reset();
      this.$nextTick(() => {
        $bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
        if (isOK) {
          $bvModal.msgBoxOk('發送成功!');
        }
      })})
    }

    const toViewOther =(name: string ) => {
      navigateByNameAndParams(name, {});
    }

    function getAllSysCodes() {
      formOptions.excel = [
        { value: '0', text: '顧問公司營收及費用等統計報表'},
        { value: '1', text: '事務所營收及費用等統計報表'},
        { value: '2', text: '海外案件明細表'},
      ]
      formOptions.yyyy = [
        { value: '1', text: "112" },
        { value: '2', text: "111" },
        { value: '3', text: "110" },
        { value: '4', text: "109" },
        { value: '5', text: "108" },
        { value: '6', text: "107" },
        { value: '7', text: "106" },
        { value: '8', text: "105" },
        { value: '9', text: "104" },
        { value: '10', text: "103" },
        { value: '11', text: "102" },
        { value: '12', text: "101" },
        { value: '13', text: "100" },
        { value: '14', text: "99" },
        { value: '15', text: "98" },
        { value: '16', text: "97" },
        { value: '17', text: "96" },
        { value: '18', text: "95" },
        { value: '19', text: "94" },
        { value: '20', text: "93" },
        { value: '21', text: "92" },
        { value: '22', text: "91" },
        { value: '23', text: "90" },
        { value: '24', text: "89" },
      ]
    }

    const table = reactive({
      fields_backend: [
        {
          // 公司名稱
          key: 'compName',
          label: i18n.t('label.comName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => 
          // formOptionsFormatter(formOptions.compType, value),
        },
        {
          // 統一編號
          key: 'compIdno',
          label: i18n.t('label.compIdno'),
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
          tdClass: 'text-left align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 資本額
          key: 'capital',
          label: i18n.t('label.capital'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 技術服務收入
          key: 'incomeTechService',
          label: i18n.t('label.incomeTechService'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          // thStyle: { width: '10%' },
        },
      ],
      data: [],
      totalItems: undefined,
    });

    const data = [
      {
        compName: '一方工程技術顧問有限公司',
        compIdno: '80222807',
        licenseNo: '工程技顧登字第000813號',
        capital: '1,000,000',
        incomeTechService: '500,000',
      },
      {
        compName: '恆揚工程顧問有限公司',
        compIdno: '97449504',
        licenseNo: '工程技顧登字第000813號',
        capital: '20,000,000',
        incomeTechService: '16,288,454',
      },
      {
        compName: '盈佳土木結構技師事務所',
        compIdno: '99337759',
        licenseNo: '技執字第003180號',
        capital: '50,000',
        incomeTechService: '50,000',
      }
    ]

    return {
      queryStatus,
      formOptions,
      $v,
      handleQueryBtn,
      clear,
      handlePaginationChanged,
      table,
      submitForm,
      toViewOther,
    }
  }
}
</script>
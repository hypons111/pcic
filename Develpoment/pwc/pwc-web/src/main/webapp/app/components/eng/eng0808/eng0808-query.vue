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
            <!-- 負責人姓名 -->
            <i-form-group-check :label="$t('label.masterName') + '：'" 
            >
            <b-form-input v-model="$v.masterName.$model"></b-form-input>
            </i-form-group-check>
            <!-- 身分證字號 -->
            <i-form-group-check :label="$t('label.idno') + '：'" 
            >
            <b-form-input v-model="$v.masterIdno.$model"></b-form-input>
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
            <!-- 登記證字號 -->
            <i-form-group-check :label="$t('label.licenseNo') + '：'" 
            >
            <b-form-input v-model="$v.cstLicenseNo.$model"></b-form-input>
            </i-form-group-check>
            <!-- 發證日期 -->
            <i-form-group-check :label="$t('label.publicDate') + '：'" 
            >
            <i-dual-date-picker :dual1.sync="$v.publicDate1.$model" 
            :dual2.sync="$v.publicDate2.$model">
            </i-dual-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 科別 -->
            <i-form-group-check :label="$t('label.subject') + '：'" 
            >
              <b-form-select v-model="$v.saleAreaList.$model"
              :options="formOptions.subject">
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
  name: 'Eng0808Query',
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
    const formOptions = reactive({
      subject: [],
    });
    const formDefault = {
      masterName: '',
      masterIdno: '',
      compName: '', 
      compIdno: '', 
      cstLicenseNo: '', 
      publicDate1: null,
      publicDate2: null,
      saleAreaList: ''
    }
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      masterName: {},
      masterIdno: {},
      compName: {}, 
      compIdno: {}, 
      cstLicenseNo: {}, 
      publicDate1: {},
      publicDate2: {},
      saleAreaList: {},
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

    function toInfo(data, index?: number) {
      navigateByNameAndParams('Eng0808Info', { data })
    }

    const clear = () => {
      reset();
      queryStatus.value = false;
      table.data = [];
    }

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const table = reactive({
      fields_backend: [
        {
          // 登記證字號
          key: 'cstLicenseNo',
          label: i18n.t('label.licenseNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 統一編號
          key: 'compIdno',
          label: i18n.t('label.compIdno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 公司名稱
          key: 'compName',
          label: i18n.t('label.comName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 機構(或顧問公司)電話
          key: 'compTel1',
          label: i18n.t('label.cstLicenseCompTel'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 職業技師人數
          key: 'engrAmounts',
          label: i18n.t('label.engrAmounts'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 科別有無符合
          key: 'isSubject',
          label: i18n.t('label.isSubject'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 不符合科別數量
          key: 'noSubAmount',
          label: i18n.t('label.noSubAmount'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 無申請科別人數
          key: 'noApplySubAmount',
          label: i18n.t('label.noApplySubAmount'),
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
        cstLicenseNo: '工程技顧登字第000240號',
        compIdno: '23052438',
        compName: '艾奕康工程顧問股份有限公司',
        compTel1: '02-27200999',
        engrAmounts: 3,
        isSubject: '有',
        noSubAmount: 0,
        noApplySubAmount: 0,
      }
    ]

    return {
      $v,
      formOptions,
      handleQueryBtn,
      handlePaginationChanged,
      clear,
      table,
      queryStatus,
      toInfo,
    }
  }
}
</script>
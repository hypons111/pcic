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
              <!-- 技師姓名 -->
              <i-form-group-check :label="$t('label.engrName') + '：'"
              :item="$v.engrName">
                <b-form-input v-model="$v.engrName.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 身分證字號 -->
              <i-form-group-check :label="$t('label.idno') + '：'"
              :item="$v.engrId">
                <b-form-input v-model="$v.engrId.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 統一編號 -->
              <i-form-group-check :label="$t('label.compIdno') + '：'"
              :item="$v.compIdno">
                <b-form-input v-model="$v.compIdno.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 公司名稱 -->
              <i-form-group-check :label="$t('label.comName') + '：'"
              :item="$v.chName">
                <b-form-input v-model="$v.chName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 科別 -->
              <i-form-group-check :label="$t('label.subjectList') + '：'" 
              :item="$v.saleAreaList">
                <b-form-select v-model="$v.saleAreaList.$model" 
                :options="formOptions.subject">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <!-- 執業狀態 -->
              <i-form-group-check
                :label="$t('label.practice') + '：'"
                :item="$v.applyItem"
              >
                <b-form-select v-model="$v.applyItem.$model" 
                :options="formOptions.applyItem">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
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
          @click="toInfoView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
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
import { Eng0703 } from '@/shared/model/eng/eng0703.model';

export default {
  name: 'Eng0818Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(prop) {
    const iTableComponent = ref(null);
    const queryStatus = ref(false);
    const admSysCodeService = new AdmSysCodeService();
    const stepVisible = reactive({ step1: true, })

    const formOptions = reactive({
      subject: [],
      applyItem: [],
    })

    const formDefault = {
      engrName: '',
      engrId: '',
      compIdno: '',
      chName: '',
      saleAreaList: '',
      applyItem: '',
    }

    const form = reactive(Object.assign({}, formDefault));

    const rules = {
      engrName: {},
      engrId: {},
      compIdno: {},
      chName: {},
      saleAreaList: {},
      applyItem: {},
    }

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      getAllSysCodes();
    })

    const handleQueryBtn = () => {
      queryStatus.value = true;
      handleQuery();
    }

    const handleQuery = () => {
      table.data = data;
      table.totalItems = data.length;
    }

    const toInfoView =(eng0703: Eng0703, 
    formStatus: FormStatusEnum, index?: number) => {
      navigateByNameAndParams('Eng0818Info', { formStatus: formStatus });
    }

    const clear = () => {
        reset();
        
        queryStatus.value = false;
      }

    function getAllSysCodes() {
      formOptions.applyItem = [
        { value: '01', text: '許可變更'},
        { value: '02', text: '換發'},
        { value: '03', text: '許可變更科別'},
        { value: '04', text: '註銷'},
        { value: '05', text: '自行停業'},
        { value: '06', text: '停業處分'},
        { value: '07', text: '撤銷或廢止登記'},
        { value: '08', text: '廢止變更許可'},
      ]
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const handlePaginationChanged = (pagination: Pagination) => {
        Object.assign(form, pagination);
        handleQuery();
      };

    const data = [
      {
        compIdno: '86627729',
        masterName: '蔣封文',
        status: '換發',
        chName: '康地科技顧問股份有限公司',
        licenseNo: '工程技顧登字第000014號',
        applyItem: '02',
        applyDate: '110/05/03',
      }
    ]

    const table = reactive({
      fields_backend: [
        {
          // 統一編號
          key: 'compIdno',
          label: i18n.t('label.compIdno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 代表人(負責人姓名)
          key: 'masterName',
          label: i18n.t('label.name'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 狀態
          key: 'status',
          label: i18n.t('label.status'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => 
          // formOptionsFormatter(formOptions.caseType, value),
        },
        {
          // 公司名稱
          key: 'chName',
          label: i18n.t('label.comName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 登記證字號
          key: 'licenseNo',
          label: i18n.t('label.licenseNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 申請日期->收文日期
          key: 'applyDate',
          label: i18n.t('label.receiveDate'),
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
    })

    return {
      iTableComponent,
      queryStatus,
      stepVisible,
      $v,
      formOptions,
      handleQueryBtn,
      clear,
      table,
      handlePaginationChanged,
      toInfoView,
      FormStatusEnum
    }
  }
}
</script>
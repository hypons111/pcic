<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                查詢條件
              </h5>
            </div>
          </div>
        </div>
        <div class="card-body col-11">
          <b-form-row>
            <!-- 類型 -->
            <i-form-group-check :label="$t('label.isEffect') + '：'" 
            :item="$v.isEffect">
              <b-form-select v-model="$v.isEffect.$model" 
              :options="formOptions.isEffect">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 執業方式 -->
            <i-form-group-check :label="$t('label.execType') + '：'" 
            :item="$v.execType">
              <b-form-select v-model="$v.execType.$model" 
                :options="formOptions.execType">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 有效日期 -->
            <i-form-group-check :label="$t('label.effectDateEnd') + '：'" 
            :item="$v.isEffect">
              <i-dual-date-picker :dual1.sync="$v.effectDateEnd1.$model" 
              :dual2.sync="$v.effectDateEnd2.$model"></i-dual-date-picker>
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
      <b-form-row>
        <i-form-group-check :label="'將選取的項目：'">
          <b-col >
            <b-button variant="info" @click="submitForm(true)">
            通知換發、變更或註銷</b-button>
            <b-button variant="info" @click="clearCheck">清除選取</b-button>
          </b-col>
        </i-form-group-check>
      </b-form-row>
      <i-table
        :hideNo="true"
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
        <!-- 設定欄位標題 -->
        <template #head(check)>  
          <b-form-checkbox v-model="isAllCheck" 
          @change="changeCheck"></b-form-checkbox>
        </template>
        <!-- 選取 -->
        <template #cell(check)="row">
          <b-form-checkbox v-model="row.item.check"></b-form-checkbox>
        </template>
        <!-- 狀態 -->
        <template #cell(isEffect)="row">
          {{ row.item.isEffect? '逾期': '即將逾期'}}
        </template>
        <!-- 有效日期 -->
        <template #cell(engEngrEffectDataEnd)="row">
          {{ row.item.engEngrEffectDataEnd | dateToString }}
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, ref, reactive, Ref } from '@vue/composition-api';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import i18n from '@/shared/i18n';
import axios from 'axios';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useValidation, validateState } from '@/shared/form';
import { Pagination } from '@/shared/model/pagination.model';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { IEngManage, EngManage } from '@/shared/model/eng/eng-manage.model';
import { useBvModal } from '@/shared/modal';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Eng0809Query',
    props: {
      isReload: {
        type: Boolean,
        required: false,
      },
    },
    setup(props) {
      let isAllCheck = ref(false);
      const iTableComponent = ref(null);
      const queryStatus = ref(false);
      const admSysCodeService = new AdmSysCodeService();
      const $bvModal = useBvModal();

      const formOptions = reactive({
        isEffect: [],
        execType: [],
      });
      const formDefault = {
        isEffect: '',
        execType: '',
        effectDateEnd1: null,
        effectDateEnd2: null,
      };
      const form = reactive(Object.assign({}, formDefault));

      const rules = {
        isEffect: {},
        execType: {},
        effectDateEnd1: {},
        effectDateEnd2: {}
      };
      const { $v, checkValidity, reset } = 
      useValidation(rules, form, formDefault);

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
      table.data = data;
      table.totalItems = data.length;
      queryStatus.value = true;
    }

    const clear = () => {
      reset();
      queryStatus.value = false;
      table.data = [];
    }

    const clearCheck = () => {
      isAllCheck.value = false;
      for(let i = 0; i < data.length; i++) {
        data[i].check = false;
      }
    }

    function submitForm(isCheckReq: boolean) {
      $v.value.$reset();
      this.$nextTick(() => {
        $bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
        if (isOK) {
          $bvModal.msgBoxOk('作業成功!');
        }
      })})
    }

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.isEffect = [
        { value: '0', text: '即將逾期' },
        { value: '1', text: '逾期' }
      ]
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
          // 狀態
          key: 'isEffect',
          label: i18n.t('label.status'),
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
          // 身分證字號
          key: 'idno',
          label: i18n.t('label.idno'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => 
          // formOptionsFormatter(formOptions.projectType, value),
        },
        {
          // 技師姓名
          key: 'chName',
          label: i18n.t('label.engrName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 執照號碼
          key: 'engrLicenseDocNo',
          label: i18n.t('label.engrLicenseDocNo'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 有效日期(迄)
          key: 'engEngrEffectDataEnd',
          label: i18n.t('label.engEngrEffectDataEnd'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => 
          // formOptionsFormatter(formOptions.recordItemsList, value),
        },
        {
          // 執業機構名稱
          key: 'engrLicenseCompName',
          label: i18n.t('label.engrLicenseCompName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 執業機構地址
          key: 'compAddr',
          label: i18n.t('label.engrCompAddr'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 戶籍地址
          key: 'permanentAddr',
          label: i18n.t('label.permanentAddr'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 執業機構電話(一)
          key: 'engCompTel1',
          label: i18n.t('label.engCompTel1'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 執業機構電話(二)
          key: 'engCompTel2',
          label: i18n.t('label.engCompTel2'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 行動電話
          key: 'mobileTel',
          label: i18n.t('label.mobileTel'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const data = [
      {
        check: false,
        isEffect: true,
        no: 1,
        idno: 'N12227****',
        chName: '陳炳昌',
        engrLicenseDocNo: '技執字第003869號',
        engEngrEffectDataEnd: new Date(2016, (1 - 1), 11),
        engrLicenseCompName: '成大電機工業技師事務所',
        compAddr: '臺東縣台東市勝利街329號2樓',
        permanentAddr: '高雄縣鳥松鄉本館路216巷1弄5號',
        engCompTel1: '07-7933927',
        engCompTel2: '',
        mobileTel: '',
      },
      {
        check: false,
        isEffect: true,
        no: 2,
        idno: 'B10074****',
        chName: '詹添全',
        engrLicenseDocNo: '技執字第002309號',
        engEngrEffectDataEnd: new Date(2016, (4 - 1), 2),
        engrLicenseCompName: '詹添全土木技師事務所',
        compAddr: '臺北市信義區忠孝東路4段553巷22弄8號1樓',
        permanentAddr: '臺北市信義區忠孝東路4段553巷16弄8號5樓',
        engCompTel1: '87877130',
        engCompTel2: '02-87877130',
        mobileTel: '',
      }
    ]
    
    return {
      $v,
      formOptions,
      queryStatus,
      iTableComponent,
      submitForm,
      clearCheck,
      table,
      handleQueryBtn,
      handlePaginationChanged,
      changeCheck,
      isAllCheck,
      clear,
    }
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
}
</script>
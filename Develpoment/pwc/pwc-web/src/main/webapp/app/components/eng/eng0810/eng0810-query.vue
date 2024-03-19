<template>
  <b-container>
    <b-card no-body>
      <b-card-header>
        <h5 class="m-0">技師事務所及技師人數統計-查詢畫面</h5>
      </b-card-header>
      <b-card-body>
        <b-form-row>
          <!-- v-model="$v.subject.$model" -->
          <!-- 年度 -->
          <i-form-group-check labelStar :label="$t('label.engYearreportYyyy') + '：'" :item="$v.engYearreportYyyy">
            <i-date-picker class="col-10" v-model="$v.engYearreportYyyy.$model" type="year" placeholder="yyy"></i-date-picker>
          </i-form-group-check>
          <!-- 有效日期 -->
          <i-form-group-check labelStar :label="$t('label.validDate') + '：'" :item="$v.validDate">
            <i-date-picker class="col-10" v-model="$v.effectDateEnd.$model"></i-date-picker>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <b-col offset="8">
            <i-button type="search" @click="handleQuery"> </i-button>
            <i-button type="x-circle" @click="reset"></i-button>
          </b-col>
        </b-form-row>
      </b-card-body>
      <!--表格一 技師執業情形統計表 -->
      <section class="container mt-2" v-show="queryStatus">
        <i-table
          small
          ref="iTableComponent"
          title="技師執業情形統計表"
          :itemsUndefinedBehavior="'loading'"
          :items="Engrtable.data"
          :is-server-side-paging="true"
          :fields="Engrtable.fields_backend"
          :totalItems="Engrtable.totalItems"
        >
          <template #cell(engYearreportYyyy)="row">
            <b-link @click="toEditView1">{{ row.item.engYearreportYyyy }}</b-link>
          </template>
          <template #cell(totalexeNo)="row">
            <b-link @click="toEditView1">{{ row.item.totalexeNo }}</b-link>
          </template>
          <template #cell(newexe)="row">
            <b-link @click="toEditView1">{{ row.item.newexe }}</b-link>
          </template>
          <template #cell(stopexe)="row">
            <b-link @click="toEditView1">{{ row.item.stopexe }}</b-link>
          </template>
        </i-table>
      </section>

      <!--表格二 技師事務所經營情形統計表-->
      <section class="container mt-2" v-show="queryStatus">
        <hr />
        <i-table
          small
          ref="iTableComponent"
          title="技師事務所經營情形統計表"
          :itemsUndefinedBehavior="'loading'"
          :items="Comtable.data"
          :is-server-side-paging="true"
          :fields="Comtable.fields_backend"
          :totalItems="Comtable.totalItems"
        >
          <template #cell(action)="row">
            <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
          </template>
          <template #cell(engYearreportYyyy)="row">
            <b-link @click="toEditView2">{{ row.item.engYearreportYyyy }}</b-link>
          </template>
          <template #cell(totalexeNo)="row">
            <b-link @click="toEditView2">{{ row.item.totalexeNo }}</b-link>
          </template>
          <template #cell(newexe)="row">
            <b-link @click="toEditView2">{{ row.item.newexe }}</b-link>
          </template>
          <template #cell(stopexe)="row">
            <b-link @click="toEditView2">{{ row.item.stopexe }}</b-link>
          </template>
        </i-table>
      </section>
    </b-card>
  </b-container>
</template>

<script lang="ts">
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { defineComponent, reactive, onMounted, ref } from '@vue/composition-api';
import { handleBack } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import i18n from '@/shared/i18n';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import NotificationService from '@/shared/notification/notification-service';
import { log } from 'console';
import { navigateByNameAndParams } from '@/router/router';
// import { onMounted, onActivated, watch, reactive, Ref, ref, toRef, inject } from '@vue/composition-api';

export default defineComponent({
  name: 'Eng0810Query',
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    const $bvModal = useBvModal();
    const notificationService = useNotification();

    onMounted(() => {
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const handleQuery = () => {
      queryStatus.value = true;
      Engrtable.data = Engrdata;
      Engrtable.totalItems = Engrdata.length;
      Comtable.data = Comdata;
      Comtable.totalItems = Comdata.length;
    };

    const formOptions = reactive({
      status: [],
      applyItemsList: [],
      applyType: [],
      subject: [],
      examType: [],
      identityType: [],
      sex: [],
      receiveType: [],
      payType: [],
    });

    const formDefault = {
      engYearreportYyyy: '',
      validDate: '',
      totalexeNo: '',
      newexe: '',
      stopexe: '',
    };
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      effectDateEnd: {},
      engYearreportYyyy: {},
      validDate: {},
      totalexeNo: {},
      newexe: {},
      stopexe: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // const notificationService: NotificationService = useNotification();

    // // 區塊是否顯示
    // const stepVisible = reactive({
    //   step1: true,
    // });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    // // modal物件
    // const $bvModal = useBvModal();

    // 技師表格
    const Engrtable = reactive({
      fields_backend: [
        {
          // 年度
          key: 'engYearreportYyyy',
          label: i18n.t('label.engYearreportYyyy'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '10%' },
        },
        {
          // 總執業數
          key: 'totalexeNo',
          label: i18n.t('label.totalexeNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 新增執業
          key: 'newexe',
          label: i18n.t('label.newexe'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 停止執業
          key: 'stopexe',
          label: i18n.t('label.stopexe'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });
    // 技師資料(暫時用)
    const Engrdata = [
      {
        engYearreportYyyy: '1',
        totalexeNo: '技師一',
        newexe: '1',
        stopexe: '1',
      },
      {
        engYearreportYyyy: '2',
        totalexeNo: '技師二',
        newexe: '2',
        stopexe: '2',
      },
    ];
    // 技師事務所表格
    const Comtable = reactive({
      fields_backend: [
        {
          // 年度
          key: 'engYearreportYyyy',
          label: i18n.t('label.engYearreportYyyy'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '10%' },
        },
        {
          // 總執業數
          key: 'totalexeNo',
          label: i18n.t('label.totalexeNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 新增執業
          key: 'newexe',
          label: i18n.t('label.newexe'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 停止執業
          key: 'stopexe',
          label: i18n.t('label.stopexe'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });
    // 技師事務所資料(暫時用)
    const Comdata = [
      {
        engYearreportYyyy: '3',
        totalexeNo: '技師事務所一',
        newexe: '3',
        stopexe: '3',
      },
      {
        engYearreportYyyy: '4',
        totalexeNo: '技師事務所二',
        newexe: '4',
        stopexe: '4',
      },
    ];

    function toQueryView() {
      handleBack({ isNotKeepAlive: true });
    }

    const toEditView1 = (queryStatus1: boolean) => {
      navigateByNameAndParams('Eng0810EditInfo', { queryStatus1: true });
    };
    const toEditView2 = (queryStatus2: boolean) => {
      navigateByNameAndParams('Eng0810EditInfo', { queryStatus2: true });
    };

    return {
      $v,
      reset,
      validateState,
      formOptions,
      toQueryView,
      queryStatus,
      Comtable,
      Engrtable,
      ref,
      formOptionsFormatter,
      handleQuery,
      toEditView1,
      toEditView2,
    };
  },
});
</script>
<style scoped></style>

function ref(arg0: boolean) { throw new Error('Function not implemented.'); } function formOptionsFormatter(subject: any[], value: string):
any { throw new Error('Function not implemented.'); } function formatToString(value: string, arg1: string, arg2: string): any { throw new
Error('Function not implemented.'); }

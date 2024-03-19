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
            <!-- 統一編號 -->
            <i-form-group-check :label="$t('label.compIdno') + '：'" 
            >
              <b-form-input v-model="$v.compIdno.$model"></b-form-input>
            </i-form-group-check>
            <!-- 公司名稱 -->
            <i-form-group-check :label="$t('label.comName') + '：'" 
            >
              <b-form-input v-model="$v.chName.$model"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 入會日期 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="6"
            :label="$t('label.inDate') + '：'">
              <i-dual-date-picker 
              :dual1.sync="$v.inDate.$model" :dual2.sync="$v.leaveDate.$model">
              </i-dual-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 身分證字號 -->
            <i-form-group-check :label="$t('label.idno') + '：'" 
            >
              <b-form-input v-model="$v.idno.$model"></b-form-input>
            </i-form-group-check>
            <!-- 姓名 -->
            <i-form-group-check :label="$t('label.name') + '：'" 
            >
              <b-form-input v-model="$v.name.$model"></b-form-input>
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
      <!-- 任職期間 -->
      <template #cell(duringTenure)="row">
        {{ row.item.inDate1 }} <br/> ~ <br/>
        {{ row.item.leaveDate1 }}
      </template>
      <!-- 擔任董監事之公司名稱及統編，標題 -->
      <template #head(masterSCompName)>
        {{ $t('label.masterSCompName') }} <br/>
        {{ $t('label.compIdno') }}
      </template>
      <!-- 內容 -->
      <template #cell(masterSCompName)="row">
        {{ row.item.masterSCompName }} <br/>
        {{ row.item.compIdno }}
      </template>
      <!-- 執業期間 -->
      <template #cell(practiceSDate)="row">
        {{ row.item.inDate2 }} <br/> ~ <br/>
        {{ row.item.leaveDate2 }}
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

export default {
  name: 'Eng0807Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const queryStatus = ref(false);

    const formDefault = {
      compIdno: '',
      chName: '',
      inDate: '',
      leaveDate: '',
      idno: '',
      name: '',
    }
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      compIdno: {},
      chName: {},
      inDate: {},
      leaveDate: {},
      idno: {},
      name: {},
    }
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

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

    const table = reactive({
      fields_backend: [
        {
          // idno
          key: 'idno',
          label: i18n.t('label.idno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 技師姓名
          key: 'engrName',
          label: i18n.t('label.engrName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 職務名稱(職稱)
          key: 'engCompMemberType',
          label: i18n.t('label.engCompMemberType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 任職期間
          key: 'duringTenure',
          label: i18n.t('label.duringTenure'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 擔任董監事之公司名稱
          // 公司統一編號
          key: 'masterSCompName',
          label: i18n.t('label.masterSCompName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 執業之公司名稱
          key: 'practiceSCompName',
          label: i18n.t('label.practiceSCompName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 執業之公司統編
          key: 'practiceSCompIdno',
          label: i18n.t('label.practiceSCompIdno'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 執業期間
          key: 'practiceSDate',
          label: i18n.t('label.practiceSDate'),
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
        idno: 'A123456789',
        engrName: '蔡貫洪',
        engCompMemberType: '董事或執行業務',
        inDate1: '109/06/25',  // 任職期間
        leaveDate1: '',
        masterSCompName: '一宅通工程顧問公司',
        compIdno: 'c1234567',
        practiceSCompName: '大錀土木工程顧問公司',
        practiceSCompIdno: 'b65789123',
        inDate2: '99/01/09',
        leaveDate2: '109/06/08',
      },
      {
        idno: 'F135792468',
        engrName: '邵育仁',
        engCompMemberType: '董事或執行業務',
        inDate1: '108/10/05',  // 任職期間
        leaveDate1: '',
        masterSCompName: '大掘工程顧問股份有限公司',
        compIdno: '66708812',
        practiceSCompName: '台灣辛隆工程顧問公司',
        practiceSCompIdno: '94781134',
        inDate2: '96/09/06',
        leaveDate2: '108/08/08',
      },
    ];

    return {
      table,
      handlePaginationChanged,
      handleQueryBtn,
      clear,
      queryStatus,
      $v,
    }
  }
}
</script>
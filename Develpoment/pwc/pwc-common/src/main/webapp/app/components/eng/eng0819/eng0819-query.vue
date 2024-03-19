<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                報表日期: {{ '111年07月12日' }}
              </h5>
            </div>
          </div>
        </div>
        <div class="card-body col-12">
          <b-table :items="table.data"
          :fields="table.fields" 
          bordered 
          responsive="sm">

            <template #cell(action)="row">
              <!-- 檢視 -->
              <i-button size="sm" type="binoculars" 
              v-if="row.item.action"
              @click="toInfoView(row.item.id)"
              v-b-modal.view-detail></i-button>
            </template>
          </b-table>
        </div>
      </div>
    </section>

    <b-modal id="view-detail" size="xl" title="執照機構名冊" header-bg-variant="secondary" header-text-variant="light">
      <b-form-row>
        <!-- 機構名稱 -->
        <i-form-group-check :label="$t('label.compName') + '：'"
        >
          <b-form-input  trim></b-form-input>
        </i-form-group-check>
        <!-- 統一編號 -->
        <i-form-group-check :label="$t('label.compIdno') + '：'"
        >
          <b-form-input trim></b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <b-col offset="8">
          <!-- 篩選 -->
          <b-button variant="info">篩選</b-button>
        </b-col>
      </b-form-row>
      <br/>
      <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :is-server-side-paging="true"
        :fields="table2.fields_backend"
        :totalItems="table2.totalItems"
        :items="table2.data"
        
      >
      <!-- @changePagination="handlePaginationChanged" -->
      
      </i-table>
      <template #modal-footer="{ cancel }">
        <!-- <b-button @click="ok()" variant="info">確定</b-button> -->
        <b-button @click="cancel()" variant="info">離開</b-button>
      </template>
    </b-modal>
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
  name: 'Eng0819Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(prop) {

    onMounted(() => {
      table.data = data;
    })

    const toInfoView = (id: string) => {
      table2.data = [];  // 先歸零
      for(let i = 0; i < data2.length; i++) {
        if(data2[i].id === id) {
          table2.data.push(data2[i]);
        }
      }
      table2.totalItems = table2.data.length;
    }

    const table = reactive({
      fields: [
        {
          // 縣市
          key: 'county',
          label: i18n.t('label.County'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 公司數
          key: 'compAmount',
          label: i18n.t('label.compAmount'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 比例
          key: 'ratio',
          label: i18n.t('label.ratio'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 動作
          key: 'action',
          label: i18n.t('label.action'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
    })

    const table2 = reactive({
      fields_backend: [
        {
          // 機構名稱
          key: 'compName',
          label: i18n.t('label.compName'),
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
          // 地址
          key: 'addr',
          label: i18n.t('label.addr'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => 
          // formOptionsFormatter(formOptions.caseType, value),
        },
        {
          // 執照字號
          key: 'engrLicenseDocNo',
          label: i18n.t('label.engrLicenseDocNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: [],
      totalItems: undefined,
    })

    const data = [
      {
        id: '1',
        county: "臺北市",
        compAmount: 2,
        ratio: '50.00%',
        action: true
      },
      {
        id: '3',
        county: "台中市",
        compAmount: 1,
        ratio: '25.00%',
        action: true
      },
      {
        id: '2',
        county: "高雄市",
        compAmount: 1,
        ratio: '25.00%',
        action: true
      },
      {
        county: "合計",
        compAmount: 4,
        ratio: '',
        action: false
      },
    ]

    const data2 = [
      {
        id: '1',
        compName: '昭宏工程顧問股份有限公司',
        compIdno: '23326839',
        addr: '臺北市興隆路2段154巷7號1樓',
        engrLicenseDocNo: '工程技顧登字第000006號',
      },
      {
        id: '1',
        compName: '礪德工程顧問有限公司',
        compIdno: '84273742',
        addr: '臺北市羅斯福路3段135號10樓之1',
        engrLicenseDocNo: '工程技顧登字第000009號',
      },
      {
        id: '2',
        compName: '鼎升工程顧問有限公司',
        compIdno: '16800627',
        addr: '高雄市三民區中都街75巷1號',
        engrLicenseDocNo: '工程技顧登字第000002號',
      },
      {
        id: '3',
        compName: '松暉工程顧問有限公司',
        compIdno: '89696278',
        addr: '臺中市北屯區同榮路288巷23號1樓',
        engrLicenseDocNo: '工程技顧登字第000012號',
      },
    ];

    return {
      table,
      table2,
      toInfoView
    }
  }
}
</script>
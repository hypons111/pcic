<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                工程技術事務所年度業務報告書-海外案件統計
              </h5>
            </div>
          </div>
        </div>
        <div class="card-body col-11">
           <p class="text-center font-weight-bold">
            盈佳土木結構技師事務所 (統編:)
           </p>
        </div>
        <b-table sticky-header
          :items="table1.data" 
          :fields="table1.fields" 
          bordered 
          responsive="sm">
        </b-table>
        <p>本年度服務費完成金額總計:50,000元</p>
      </div>
    </section>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-center">
           <p class="text-center font-weight-bold">
            案件細項內容 ()
           </p>
        </div>
        <div class="card-body col-11">
          <b-form-row>
            <!-- 編號 -->
            <i-form-group-check
            :label="$t('label.engEngrNo') + '：'">
            {{ data1.engEngrNo }}
            </i-form-group-check>
            <!-- 案號 -->
            <i-form-group-check
            :label="$t('label.caseNo') + '：'">
            {{ data1.caseNo }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 案件名稱 -->
            <i-form-group-check
            :label="$t('label.caseName') + '：'">
            {{ data1.caseName }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 案主姓名 -->
            <i-form-group-check
            :label="$t('label.clientName') + '：'">
            {{ data1.clientName }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 案主地址 -->
            <i-form-group-check
            :label="$t('label.clientAddr') + '：'">
            {{ data1.clientAddr }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 案主電話 -->
            <i-form-group-check
            :label="$t('label.clientTel') + '：'">
            {{ data1.clientTel }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 契約文號 -->
            <i-form-group-check
            :label="$t('label.docNo1') + '：'">
            {{ data1.docNo }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 委託方式 -->
            <i-form-group-check
            :label="$t('label.commissionedType') + '：'">
            {{ data1.commissionedType }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 服務類型 -->
            <i-form-group-check
            :label="$t('label.serviceType') + '：'">
            {{ data1.serviceType }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 服務期間 -->
            <i-form-group-check
            :label="$t('label.date') + '：'">
            {{ data1.dateStart }} ~ {{ data1.dateEnd}} 
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 服務合約金額 -->
            <i-form-group-check
            :label="$t('label.serviceCasePrice') + '：'">
            {{ data1.serviceCasePrice }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 本年度完成金額 -->
            <i-form-group-check
            :label="$t('label.yearCompletePrice') + '：'">
            {{ data1.yearCompletePrice }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 計畫主持人 -->
            <i-form-group-check
            :label="$t('label.host') + '：'">
            {{ data1.host }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 主要參與技師科別、姓名及身分證字號 -->
            <i-form-group-check class="col-12"
              label-cols="2" content-cols="10"
            :label="$t('label.engrSubjectNameIdno') + '：'">
              <b-table sticky-header
              :items="table2.data" 
              :fields="table2.fields" 
              bordered 
              responsive="sm">
              <template #head(engr)>
                {{ $t('label.engr') }}<br/>{{ $t('label.idno') }}
              </template>
              <template #cell(engr)="row">
                {{ row.item.engrName }} <br/>
                {{ row.item.engrIdno }}
              </template>
              <template #cell(date)="row">
                {{ row.item.dateStart | dateToString}}<br/>~<br/>
                {{ row.item.dateEnd | dateToString }}
              </template> 
            </b-table>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 服務項目及內容摘要 -->
            <i-form-group-check
            :label="$t('label.serviceContent') + '：'">
            {{ data1.serviceContent }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 服務項目之工程地點 -->
            <i-form-group-check
            :label="$t('label.servicePlace') + '：'">
            {{ data1.servicePlace }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 合作建築師事務所名稱 -->
            <i-form-group-check
            :label="$t('label.archCompName') + '：'">
            {{ data1.archCompName }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 合作建築師事務所之工作內容 -->
            <i-form-group-check
            :label="$t('label.archCompWorkContent') + '：'">
            {{ data1.archCompWorkContent }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 其他協力廠商名稱 -->
            <i-form-group-check
            :label="$t('label.otherCompName') + '：'">
            {{ data1.otherCompName }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 其他協力廠商或分包情形之內容 -->
            <i-form-group-check
            :label="$t('label.otherCompWorkContent') + '：'">
            {{ data1.otherCompWorkContent }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 備註 -->
            <i-form-group-check
            :label="$t('label.remark') + '：'">
            {{ data1.remark }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <b-col offset="5">
              <!-- 返回 -->
              <i-button type="arrow-left" @click="toLast"/>
            </b-col>
          </b-form-row>
        </div>
      </div>
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
import { useBvModal } from '@/shared/modal';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Eng0205Query3',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(prop) {
    const isReload = ref(false);

    onMounted(() => {
      table1.data = data;
      table2.data = data2;
    })

    function toLast() {
      navigateByNameAndParams('Eng0205Query', {
        isReload: isReload.value, isNotKeepAlive: true,
      } );
      isReload.value = false;
    }

    const table1 = reactive({
      fields: [
        {  // 序號
          key: 'no',
          label: i18n.t('label.no'),
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {  // 標案名稱
          key: 'caseName',
          label: i18n.t('label.caseName'),
          sortable: false,
          // thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
        },
        {  // 案主姓名
          key: 'clientName',
          label: i18n.t('label.clientName'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {  // 服務合約金額
          key: 'serviceCasePrice',
          label: i18n.t('label.serviceCasePrice'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle ',
        },
        {
          // 本年度完成金額
          key: 'yearCompletePrice',
          label: i18n.t('label.yearCompletePrice'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
      ],
      data: undefined,
    });

    const table2 = reactive({
      fields: [
        {  // 序號
          key: 'no',
          label: i18n.t('label.no'),
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {  // 科別
          key: 'subject',
          label: i18n.t('label.subject'),
          sortable: false,
          // thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
        },
        {  // 技師，身分證字號
          key: 'engr',
          label: i18n.t('label.engr'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {  // 參與期間
          key: 'date',
          label: i18n.t('label.date'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {
          // 主要業務
          key: 'mainTask',
          label: i18n.t('label.mainTask'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
    });

    const data = [
      {
        no: 1,
        caseName: '淡水輕軌捷運漁人碼頭站鏤雕光棚',
        clientName: '島嶼工程顧問公司',
        serviceCasePrice: '50,000',
        yearCompletePrice: '50,000',

        engEngrNo: '1',
        caseNo: '109-02',
        clientAddr: '臺北市大同區',
        clientTel: '02-25853598',
        docNo: '',
        commissionedType: '總包價法或單價計算法',
        serviceType: '設計及施工監造',
        dateStart: '109/04/16',
        dateEnd: '109/06/23',
        host: '陳健信',

        engrSubjectNameIdno: '',

        serviceContent: '結構設計',
        servicePlace: '新北市淡水區',
        archCompName: '',
        archCompWorkContent: '',
        otherCompName: '',
        otherCompWorkContent: '',
        remark: '',
      }
    ];

    const data1 = data[0];

    const data2 = [
      {
        no: 1,
        subject: '結構工程',
        engrName: '陳健信',
        engrIdno: 'F12226****',
        dateStart: new Date(2010, 4, 16),
        dateEnd: new Date(2010, 6, 23),
        mainTask: '結構設計',
      }
    ]
    
    return {
      table1,
      table2,
      data1,
      toLast,
    }
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
}
</script>
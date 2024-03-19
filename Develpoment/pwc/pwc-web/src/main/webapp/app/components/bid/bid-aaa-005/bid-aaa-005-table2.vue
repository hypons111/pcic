<template>
  <div>
    <section class="container">
      <i-button type="printer" v-print="'#printTable'"></i-button>
      <div class="card">
        <div class="card-body" id="printTable">
          <b-table-simple borderless>
            <b-tr>
              <b-td colspan="3" align="center"><h5>{{ projectInfo.wkutName }}</h5></b-td>
            </b-tr>
            <b-tr>
              <b-td colspan="3" align="center"><h5>分批(期)付款表</h5></b-td>
            </b-tr>
            <b-tr>
              <b-td width="15%"></b-td>
              <b-td align="center" width="70%">{{ prjPayInfo.trueDate | dateToString }}</b-td>
              <b-td align="right" width="15%">單位：新台幣</b-td>
            </b-tr>
            <b-tr>
              <b-td colspan="3">工程名稱：{{ projectInfo.name }}</b-td>
            </b-tr>
          </b-table-simple>
          <b-table-simple bordered>
            <b-tr>
              <b-td align="center" width="30%">所屬年度月份</b-td>
              <b-td align="center" width="30%">{{ prjPayInfo.payDate | dateToYYYMM }}</b-td>
              <b-td align="center" width="40%">說      明</b-td>
            </b-tr>
            <b-tr>
              <b-td align="center">應付總額</b-td>
              <b-td align="right">{{ prjPayInfo.payMonT | moneyToTWD }}</b-td>
              <b-td rowspan="5">
                <p style="line-height:34px">
                  (1){{ projectInfo.cntid | cntid }}</br>
                  (2)第  {{ prjPayInfo.monNo | padStart3}}  次付款。</br>
                  (3)契約副本或抄本黏附於____年____月份。</br>
                  　支出憑證簿第____冊第____號。</br>
                  (4)於支付尾款時或分批驗收後，應付驗收</br>
                  　證明。
                </p>
              </b-td>
            </b-tr>
            <b-tr>
               <b-td align="center">截至上次已付金額</b-td>
               <b-td align="right">{{ prjPayInfo.payMonU | moneyToTWD }}</b-td>
            </b-tr>
            <b-tr>
               <b-td align="center">本次已付金額</b-td>
               <b-td align="right">{{ prjPayInfo.payMon | moneyToTWD }}</b-td>
            </b-tr>
            <b-tr>
               <b-td align="center">已付金額</b-td>
               <b-td align="right">{{ prjPayInfo.payMonS | moneyToTWD }}</b-td>
            </b-tr>
            <b-tr>
              <b-td align="center">未付金額</b-td>
              <b-td align="right">{{ (prjPayInfo.payMonT - prjPayInfo.payMonS) | moneyToTWD }}</b-td>
            </b-tr>
          </b-table-simple>
          <b-table-simple borderless>
            <b-tr>
              <b-td width="17%">承辦單</br>位人員</b-td>
              <b-td width="19%">承辦單位</br>主管人員</b-td>
              <b-td width="17%">會計單</br>位人員</b-td>
              <b-td width="24%">主辦會計人員</br>或其授權代簽人</b-td>
              <b-td width="23%">機關長官或</br>其授權代簽人</b-td>
            </b-tr>
          </b-table-simple>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, onActivated } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import BidPrModifyService from '@/components/bid/bidService/bid-prj-modify.service';
import { Pagination } from '@/shared/model/pagination.model';
import { commaFormatter } from '@/shared/formatter/common';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { formatDate } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'bidAaa005Table2',
  props: {
    projectInfoProp: {
      type: Object,
      required: false,
    },
    prjPayInfoProp: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const projectInfo: Ref<any> = toRef(props, 'projectInfoProp');
    const prjPayInfo: Ref<any> = toRef(props, 'prjPayInfoProp');

    return {
      projectInfo,
      prjPayInfo
    };
  },
  filters: {
    commaFilter: (value: any) => commaFormatter(value),
    dateToString: (value: string) => {
      var date = new Date(value);
      const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      return year + '年' + month + '月' + day + '日';
    },
    dateToYYYMM: (value: string) => {
      if (value) {
        var date = new Date(value);
        const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        return year + '年度' + month + '月份';
      } else {
        return '___年度__月份';
      }
    },
    moneyToTWD: (value: any) => {
      if (value != undefined) {
        return commaFormatter(value*1000) + ' 元';
      } else {
        return '(未填)';
      }
    },
    padStart3: (value: string) => {
      return value.padStart(3, '0');
    },
    cntid: (value: string) => {
      if (value) {
        return '合約編號：' + value + ' 。';
      } else {
        return '未定合約。';
      }
    },
  },
};
</script>
<style>
  @page { size: portrait; }
</style>

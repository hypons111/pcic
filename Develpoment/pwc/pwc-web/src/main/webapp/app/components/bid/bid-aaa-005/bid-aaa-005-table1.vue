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
              <b-td colspan="2" align="center"><h5>歷次請撥付金額一覽表</h5></b-td>
            </b-tr>
            <b-tr>
              <b-td width="75%"></b-td>
              <b-td align="right" width="25%">列印日期：{{ new Date() | dateToString }}</b-td>
            </b-tr>
          </b-table-simple>
          <b-table-simple bordered>
            <b-tr>
              <b-td colspan="2" width="50%">標案名稱：{{ projectInfo.name }}</b-td>
            </b-tr>
            <b-tr>
              <b-td>執行機關：{{ projectInfo.wkutName }}</b-td>
              <b-td>標案編號：{{ projectInfo.prjno }}</b-td>
            </b-tr>
            <b-tr>
              <b-td>承辦人：{{ projectInfo.man }}</b-td>
              <b-td>連絡電話：{{ projectInfo.tel }}</b-td>
            </b-tr>
            <b-tr>
              <b-td>歸屬計畫主辦機關：{{ projectInfo.plnName | unfilled }}</b-td>
              <b-td>歸屬計畫編號(名稱)：{{ projectInfo.plnNo | unfilled }}</b-td>
            </b-tr>
            <b-tr>
              <b-td>主管機關：{{ projectInfo.bosName }}</b-td>
              <b-td>主辦機關：{{ projectInfo.deptName }}</b-td>
            </b-tr>
            <b-tr>
              <b-td>發包預算：{{ projectInfo.bdgt1 | commaFilter }}千元</b-td>
              <b-td>決標金額：{{ projectInfo.ctSum | commaFilter }}千元</b-td>
            </b-tr>
          </b-table-simple>
          <div v-for="(item, index) in projectPayDataArray" :key="index">
            <b-table-simple bordered>
              <b-tr>
                <b-td rowspan="2" align="center">次別</b-td>
                <b-td rowspan="2" align="center">應付總額</br>千元</b-td>
                <b-td rowspan="2" align="center">截至上次</br>已付總額</br>千元</b-td>
                <b-td colspan="2" align="center">本次申請付款</b-td>
                <b-td colspan="2" align="center">單據送達</b-td>
                <b-td colspan="2" align="center">本次實際付款</b-td>
                <b-td rowspan="2" align="center">累計已付</br>千元</b-td>
                <b-td rowspan="2" align="center">未付金額</br>千元</b-td>
                <b-td rowspan="2" align="center">備註</b-td>
              </b-tr>
              <b-tr>
                <b-td align="center">日期</b-td>
                <b-td align="center">金額</br>千元</b-td>
                <b-td align="center">日期</b-td>
                <b-td align="center">金額</br>千元</b-td>
                <b-td align="center">日期</b-td>
                <b-td align="center">金額</br>千元</b-td>
              </b-tr>
              <b-tr>
                <b-td align="center">{{ item.monNo | padStart3 }}</b-td>
                <b-td align="right">{{ item.payMonT | commaFilter }}</b-td>
                <b-td align="right">{{ item.payMonU | commaFilter }}</b-td>
                <b-td align="center">{{ item.trueDate | dateToYYYMMDD }}</b-td>
                <b-td align="right">{{ item.trueMon | commaFilter }}</b-td>
                <b-td align="center">{{ item.tenDate | dateToYYYMMDD }}</b-td>
                <b-td align="right">{{ item.tenMon | commaFilter }}</b-td>
                <b-td align="center">{{ item.payDate | dateToYYYMMDD }}</b-td>
                <b-td align="right">{{ item.payMon | commaFilter }}</b-td>
                <b-td align="right">{{ item.payMonS | commaFilter }}</b-td>
                <b-td align="right">{{ item.payMonT - item.payMonS | commaFilter }}</b-td>
                <b-td align="center">{{ item.payRemark }}</b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="12">註：可點選次別列印支出憑證之分批付款表</b-td>
              </b-tr>
            </b-table-simple>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import { commaFormatter } from '@/shared/formatter/common';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { Ref, ref, toRef, reactive, watch, onActivated } from '@vue/composition-api';

export default {
  name: 'bidAaa005Table1',
  props: {
    projectInfoProp: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const projectInfo: Ref<any> = toRef(props, 'projectInfoProp');
    //下面pay表格的資料
    const projectPayDataArray = reactive([]);

    const notificationService: NotificationService = useNotification();

    function fetchProjectAllPay(criteria) {
      axios
        .post('/bid-prj-pays/getAll', criteria) //後端分頁
        .then(({ data }) => {
          projectPayDataArray.splice(0, projectPayDataArray.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    watch(
      projectInfo,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchProjectAllPay(newValue);
        }
      },
      { immediate: true }
    );
    onActivated(() => {
      if (projectInfo.value) {
        fetchProjectAllPay(projectInfo.value);
      } else if (tempProjectKey.wkut.length > 0) {
        fetchProjectAllPay(tempProjectKey);
      }
    });
    return {
      projectInfo,
      projectPayDataArray,
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
    padStart3: (value: string) => {
      return value.padStart(3, '0');
    },
    dateToYYYMMDD: (value: string) => {
      var date = new Date(value);
      const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      return year + month + day;
    },
    unfilled: (value: string) => {
      if (value) {
        return value;
      else {
        return '(未填)';
      }
    },
  },
};
</script>
<style>
  @page { size: landscape; }
</style>

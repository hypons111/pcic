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
              <b-td colspan="2" align="center"><h5>物價指數調整試算表(僅供參考)</h5></b-td>
            </b-tr>
            <b-tr>
              <b-td width="75%"></b-td>
              <b-td align="right" width="25%">列印日期：{{ new Date() | dateToString }}</b-td>
            </b-tr>
          </b-table-simple>
          <b-table-simple bordered>
            <b-tr>
              <b-td colspan="2" width="50%">工程名稱：{{ projectInfo.name }}</b-td>
            </b-tr>
            <b-tr>
              <b-td align="center">當期估驗之直接工程費</b-td>
              <b-td>A = 當期估驗款 {{ prjPayInfo.payMon * 1000 | commaFilter }}元 * 80%</b-td>
            </b-tr>
            <b-tr>
              <b-td align="center">估驗計價年度月份</b-td>
              <b-td>{{ prjPayInfo.payDate | dateToYYYMM }} 物價指數調整值 B ={{ form.B }}</br>
                    (如本月物價指數尚未公告時暫以100計)</b-td>
            </b-tr>
            <b-tr>
              <b-td align="center">開標當月份</b-td>
              <b-td>{{ prjPayInfo.actDate | dateToYYYMM }} 物價指數調整值 C ={{ form.C }}</b-td>
            </b-tr>
            <b-tr>
              <b-td align="center">指數增減率</b-td>
              <b-td>D =[(B/C)-1]*100% 　　＝ {{ form.D }}%</b-td>
            </b-tr>
            <b-tr>
              <b-td align="center">已付預付款佔契約總價之百分比</b-td>
              <b-td>E = {{ form.E }}%</b-td>
            </b-tr>
            <b-tr>
              <b-td align="center">營業稅率應何時計算之</b-td>
              <b-td>F = 1.05 　　(1+營業稅率)</b-td>
            </b-tr>
            <b-tr>
              <b-td align="center">物價指數調整金額</b-td>
              <b-td>A * (1-E) * (指數增減率 D 之絕對值 - 2.5%) * F</br>
                    {{ form.G }}</b-td>
            </b-tr>
          </b-table-simple>
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
  name: 'bidAaa005Table3',
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

    const notificationService: NotificationService = useNotification();

    const form = reactive({
      A: '',
      B: 100,
      C: 100,
      D: '',
      E: '',
      F: '',
      G: '',
    };

    function getBInd1(date) {
      if (date) {
        var date = new Date(date);
        const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        let cindKey = {
          year: year,
          month: month,
        }
        axios
          .post('/bid-ccinds/getOne', cindKey)
          .then(({ data }) => {
            if (data && data['ind1']) {
              form.B = data.ind1;
              processArrayData();
            }
          })
          .finally(() => {})
          .catch(() => {
            form.B = 100;
            processArrayData();
          });
      } else {
        form.B = 100;
      }
    }

    function getCInd1(date) {
      if (date) {
        var date = new Date(date);
        const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        let cindKey = {
          year: year,
          month: month,
        }
        axios
          .post('/bid-ccinds/getOne', cindKey)
          .then(({ data }) => {
            if (data && data['ind1']) {
              form.C = data.ind1;
              processArrayData();
            }
          })
          .finally(() => {})
          .catch(() => {
            form.C = 100;
            processArrayData();
          });
      } else {
        form.C = 100;
      }
    }

    function processArrayData() {
      const A = '(未填)';
      if (prjPayInfo.value.payMon) {
        A = prjPayInfo.value.payMon * 1000 * 0.8;
      }
      if (prjPayInfo.value.payDate) {
        prjPayInfo.value.actDate = projectInfo.value.actDate;
      } else {
        prjPayInfo.value.actDate = undefined;
      }
      const B = form.B;
      const C = form.C;
      if (C === 0) {
        C = 100;
      }
      const D = (((B/C)-1)*100).toFixed(3);
      const E = ((projectInfo.value.pays / projectInfo.value.ctSum) * 100).toFixed(2);;
      const F = 1.05;
      const G = '';
      if (Math.abs(D) >= 2.5) {
        G = commaFormatter(A*(1-E*0.01)*(Math.abs(D)-2.5)*0.01*F) + '元';
      } else if (Math.abs(D) < 2.5) {
        G = '指數增減率 D 之絕對值 ' + Math.abs(D).toFixed(3) + ' % 未達 2.5% 不需物調';
      }
      form.A = A;
      form.D = D;
      form.E = E;
      form.F = F;
      form.G = G;
    }

    watch(
      projectInfo,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          getCInd1(newValue.actDate);
        }
      },
      { immediate: true }
    );
    watch(
      prjPayInfo,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          getBInd1(newValue.payDate);
        }
      },
      { immediate: true }
    );
    onActivated(() => {
      if (projectInfo.value) {
        getCInd1(projectInfo.value.actDate);
      }
      if (prjPayInfo.value) {
        getBInd1(prjPayInfo.value.payDate);
      }
    });
    return {
      projectInfo,
      prjPayInfo,
      form,
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
  },
};
</script>
<style>
  @page { size: portrait; }
</style>

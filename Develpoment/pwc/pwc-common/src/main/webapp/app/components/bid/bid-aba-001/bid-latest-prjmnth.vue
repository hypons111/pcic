<template>
  <div>
    <div class="card">
      <div class="card-body col-11">
        <b-table-simple bordered>
          <b-tbody>
            <b-tr>
              <b-td style="width: 15%">總累計預定完成金額</b-td>
              <b-td style="width: 15%">{{ prjMonth.pexp }}</b-td>
              <b-td style="width: 15%">總累計實際完成金額</b-td>
              <b-td style="width: 15%">{{ prjMonth.aexp }}</b-td>
              <b-td style="width: 15%">總累計估驗計價</b-td>
              <b-td style="width: 15%">{{ prjMonth.sumt }}</b-td>
            </b-tr>
            <b-tr>
              <b-td>總累計預定進度</b-td>
              <b-td>{{ prjMonth.ptrate }}</b-td>

              <b-td>總累計實際進度</b-td>
              <b-td colspan="3">{{ prjMonth.atrate }}</b-td>
            </b-tr>
            <b-tr>
              <b-td>預定工作摘要</b-td>
              <b-td colspan="5">{{ prjMonth.item }}</b-td>
            </b-tr>
            <b-tr>
              <b-td>執行情形</b-td>
              <b-td colspan="5">{{ prjMonth.remark }}</b-td>
            </b-tr>
            <b-tr>
              <b-td>本月進行中之專業工程項目</b-td>
              <b-td colspan="5">{{ professionalEngineering }}</b-td>
            </b-tr>
            <b-tr>
              <b-td>現場管理</b-td>
              <b-td colspan="5">
                <div v-for="(message, index) in onSiteManagementArr" :key="index">
                  {{ message }}
                </div>
              </b-td>
            </b-tr>
          </b-tbody>
        </b-table-simple>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, watch, toRaw } from '@vue/composition-api';
import { IBidPrjMonth } from '@/shared/model/bidModel/bid-prj-month.model';
import BidPrjMonthFormatter from '@/components/bid/bidService/bid-prj-month.formatter';
export default {
  name: 'bidLatestPrjMnth',
  props: {
    prjMonthProp: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const prjMonth: Ref<IBidPrjMonth> = toRef(props, 'prjMonthProp');
    const bidPrjMonthFormatter = new BidPrjMonthFormatter();

    let professionalEngineering = ref('');
    let onSiteManagementArr = ref([]);

    function changeFormatValue() {
      professionalEngineering.value = bidPrjMonthFormatter.professionalEngineeringProjectFormat(prjMonth.value);
      onSiteManagementArr.value = bidPrjMonthFormatter.onSiteManagementFormat(prjMonth.value);
    }

    watch(
      prjMonth.value,
      () => {
        changeFormatValue();
      },
      { immediate: true }
    );

    return { prjMonth, professionalEngineering, onSiteManagementArr };
  },
};
</script>

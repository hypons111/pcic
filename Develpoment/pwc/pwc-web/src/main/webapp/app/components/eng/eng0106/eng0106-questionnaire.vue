<template>
  <b-container>
    <b-table-simple borderless small>
      <b-tr>
        <b-td>
          <div class="py-1">員工人數必填（包含行政人員、工程專業人員及技師人員欄位總和與前項員工人數欄位需相等）</div>
          <!-- 行政人員 -->
          1. {{ i18n.t('label.staffNumAdministration') }} ( 係指 貴公司之正式行政人員數，例：人事、會計、總務 )
          <b-form-input
            v-model.trim="$v.staffNumAdministration.$model"
            :state="validateState($v.staffNumAdministration)"
            @change="calculateNum()"
            class="col-2 d-inline-block mb-1"
            size="sm"
            placeholder="請輸入正整數"
            v-i-currency
          ></b-form-input>
          人
          <br />
          <!-- 工程專業人員 -->
          2. {{ i18n.t('label.staffNumEngineering') }} ( 係指 貴公司之正式工程專業人員數［不含執業技師］)
          <b-form-input
            v-model.trim="$v.staffNumEngineering.$model"
            :state="validateState($v.staffNumEngineering)"
            @change="calculateNum()"
            class="col-2 d-inline-block mb-1"
            size="sm"
            placeholder="請輸入正整數"
            v-i-currency
          ></b-form-input>
          人
          <br />
          <!-- 技師人員 -->
          3. {{ i18n.t('label.staffNumEngr') }} ( 執業技師人員數 )
          <b-form-input
            v-model.trim="$v.staffNumEngr.$model"
            :state="validateState($v.staffNumEngr)"
            @change="calculateNum()"
            class="col-2 d-inline-block"
            size="sm"
            placeholder="請輸入正整數"
            v-i-currency
          ></b-form-input>
          人
          <!-- 員工人數合計 -->
          <div class="py-1">{{ i18n.t('label.staffNumTotal') }} {{ $v.staffNumTotal.$model }} 人</div>
        </b-td>
      </b-tr>
    </b-table-simple>

    <b-form-row class="mt-5">
      <!-- 填表人姓名 -->
      <i-form-group-check :label="$t('label.preparedByName')" :item="$v.preparedByName" content-cols="4">
        <b-form-input v-model.trim="$v.preparedByName.$model" :state="validateState($v.preparedByName)"></b-form-input>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 填表人連絡電話 -->
      <i-form-group-check :label="$t('label.preparedByTel')" class="col-12" label-cols="2" content-cols="4" :item="$v.preparedByTel">
        <b-form-input v-model="$v.preparedByTel.$model" :state="validateState($v.preparedByTel)" trim lazy></b-form-input>
      </i-form-group-check>
      <!-- <b-form-radio-group v-model="isMobileTel" class="pt-2 ml-4">
        <b-form-radio :value="true">行動電話</b-form-radio>
        <b-form-radio :value="false">有線電話</b-form-radio>
      </b-form-radio-group> -->
    </b-form-row>
  </b-container>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import { watch, reactive, ref, computed, unref } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, integer, numeric, tel } from '@/shared/validators';

export default {
  name: 'Eng0202Questionnaire',
  props: {
    engYearreport: {
      type: Object,
      required: false,
    },
  },
  setup(props) {
    const preparedByTelComponent = ref(null);
    const isMobileTel = ref(true);

    let formDefault = {
      engYearreportId: '',
      engYearreportYyyy: '',
      yearreportType: '',
      incomeTechService: 0,
      incomeTechServiceDomestic: 0,
      incomeTechServiceForeign: 0,
      isIncomeTechServiceMatch: '',
      revenueGrowthRatio: 0,
      revenueLastYear: 0,
      staffNumTotal: 0,
      staffNumAdministration: '',
      staffNumEngineering: '',
      staffNumEngr: '',
      staffOutputValue: 0,
      netProfitRatio: 0,
      netProfitAfterTax: '',
      netIncome: '',
      netValueTurnoverRatio: 0,
      income: '',
      netValue: '',
      flowRatio: 0,
      flowAssets: '',
      flowLiabilities: '',
      preparedByName: '',
      preparedByTel: '',
      status: '',
    };

    let formEmpty = Object.assign({}, formDefault);
    const form = reactive(Object.assign({}, formDefault));

    const formRules = {
      engYearreportId: {},
      engYearreportYyyy: {},
      yearreportType: {},
      incomeTechService: {},
      incomeTechServiceDomestic: {},
      incomeTechServiceForeign: {},
      isIncomeTechServiceMatch: {},
      revenueGrowthRatio: {},
      revenueLastYear: {},
      staffNumTotal: {},
      staffNumAdministration: { required, numeric, integer },
      staffNumEngineering: { required, numeric, integer },
      staffNumEngr: { required, numeric, integer },
      staffOutputValue: {},
      netProfitRatio: {},
      netProfitAfterTax: { numeric, integer },
      netIncome: { numeric, integer },
      netValueTurnoverRatio: {},
      income: { numeric, integer },
      netValue: { numeric, integer },
      flowRatio: {},
      flowAssets: { numeric, integer },
      flowLiabilities: { numeric, integer },
      preparedByName: {},
      preparedByTel: { tel: tel(() => unref(preparedByTelComponent).telType) },
      status: {},
    };
    const { $v, checkValidity, reset } = useValidation(formRules, form, formDefault);

    watch(
      () => props.engYearreport,
      newVal => {
        // console.log("questionnaire watch!!!!!!!!!!");
        Object.assign(formDefault, formEmpty);
        for (let key in formDefault) {
          if (newVal[key]) formDefault[key] = newVal[key];
        }
        reset();
      },
      { immediate: true, deep: true }
    );

    const calculateNum = () => {
      if (!isNaN(Number(form.incomeTechService)) && !isNaN(Number(form.revenueLastYear)) && Number(form.revenueLastYear) != 0) {
        form.revenueGrowthRatio =
          ((Math.round(Number(form.incomeTechService)) - Math.round(Number(form.revenueLastYear))) /
            Math.round(Number(form.revenueLastYear))) *
          100;
        form.revenueGrowthRatio = Number(form.revenueGrowthRatio.toFixed(3));
      } else {
        form.revenueGrowthRatio = 0;
      }

      if (!isNaN(Number(form.staffNumAdministration)) && !isNaN(Number(form.staffNumEngineering)) && !isNaN(Number(form.staffNumEngr))) {
        form.staffNumTotal =
          Math.round(Number(form.staffNumAdministration)) +
          Math.round(Number(form.staffNumEngineering)) +
          Math.round(Number(form.staffNumEngr));
      } else {
        form.staffNumTotal = 0;
      }

      if (!isNaN(Number(form.incomeTechService)) && !isNaN(Number(form.staffNumTotal)) && Number(form.staffNumTotal) != 0) {
        form.staffOutputValue = Math.round(Number(form.incomeTechService)) / Math.round(Number(form.staffNumTotal));
        form.staffOutputValue = Number(form.staffOutputValue.toFixed(3));
      } else {
        form.staffOutputValue = 0;
      }

      if (!isNaN(Number(form.netProfitAfterTax)) && !isNaN(Number(form.netIncome)) && Number(form.netIncome) != 0) {
        form.netProfitRatio = (Math.round(Number(form.netProfitAfterTax)) / Math.round(Number(form.netIncome))) * 100;
        form.netProfitRatio = Number(form.netProfitRatio.toFixed(3));
      } else {
        form.netProfitRatio = 0;
      }

      if (!isNaN(Number(form.income)) && !isNaN(Number(form.netValue)) && Number(form.netValue) != 0) {
        form.netValueTurnoverRatio = (Math.round(Number(form.income)) / Math.round(Number(form.netValue))) * 100;
        form.netValueTurnoverRatio = Number(form.netValueTurnoverRatio.toFixed(3));
      } else {
        form.netValueTurnoverRatio = 0;
      }

      if (!isNaN(Number(form.flowAssets)) && !isNaN(Number(form.flowLiabilities)) && Number(form.flowLiabilities) != 0) {
        form.flowRatio = (Math.round(Number(form.flowAssets)) / Math.round(Number(form.flowLiabilities))) * 100;
        form.flowRatio = Number(form.flowRatio.toFixed(3));
      } else {
        form.flowRatio = 0;
      }
    };

    const getpreparedByTel = (data: string) => {
      form.preparedByTel = data;
    };

    const putForm = () => {
      return form;
    };

    return {
      i18n,
      preparedByTelComponent,
      isMobileTel,
      $v,
      checkValidity,
      reset,
      validateState,
      calculateNum,
      getpreparedByTel,
      putForm,
    };
  },
};
</script>

<style></style>

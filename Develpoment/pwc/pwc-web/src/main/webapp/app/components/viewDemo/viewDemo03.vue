<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          測試專用
        </h5>
      </div>
      <div class="card-body">
        <div class="text-right">地址：{{ $v.zip.$model }} {{ $v.city.$model }} {{ $v.town.$model }} {{ $v.other.$model }}</div>

        <b-form-row>
          <i-form-group-check labelStar class="col-12" label-cols="2" content-cols="10" label="地址：" :item="[$v.town, $v.other, $v.city]">
            <i-address-picker
              :zip.sync="$v.zip.$model"
              :city.sync="$v.city.$model"
              :town.sync="$v.town.$model"
              :other.sync="$v.other.$model"
              :city-state="validateState($v.city)"
              :town-state="validateState($v.town)"
              :other-state="validateState($v.other)"
            >
            </i-address-picker>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <i-form-group-check
            labelStar
            class="col-12"
            label-cols="2"
            content-cols="10"
            label="地址(使用slot)："
            :item="[$v.town, $v.other, $v.city]"
          >
            <i-address-picker
              :zip.sync="$v.zip.$model"
              :city.sync="$v.city.$model"
              :town.sync="$v.town.$model"
              :other.sync="$v.other.$model"
              :city-state="validateState($v.city)"
              :town-state="validateState($v.town)"
              :other-state="validateState($v.other)"
              :cols="[1, 2, 2, 5, 2]"
            >
              <template #end>
                <b-form-checkbox>
                  <span class="small">同戶籍地址</span>
                </b-form-checkbox>
              </template>
            </i-address-picker>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <i-form-group-check labelStar class="col-12" label-cols="2" content-cols="10" label="地址(純文字)：">
            <i-address-picker :isText="true" :zip="$v.zip.$model" :city="$v.city.$model" :town="$v.town.$model" :other="$v.other.$model">
              <template #text="{ zip, city, town, other }">
                {{ `[${zip}] ${city},${town}${other}` }}
              </template>
            </i-address-picker>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <i-form-group-check
            labelStar
            class="col-12"
            label-cols="2"
            content-cols="10"
            :label="'多欄位驗證測試'"
            :item="[$v.test1, $v.test2]"
          >
            <b-form-input class="col-6" v-model="$v.test1.$model" :state="validateState($v.test1)" />
            <b-form-input class="col-6" v-model="$v.test2.$model" :state="validateState($v.test2)" />
          </i-form-group-check>
        </b-form-row>

        <!-- <b-form-input v-model="$v.a.$model" :state="validateState($v.a)" />
        <b-form-input v-model="$v.c.c1.$model" :state="validateState($v.c.c1)" />
        <b-form-input v-model="$v.c.c3.$model[1]" />
        <b-form-input v-model="$v.d.$model[0]" />
        <b-form-input v-model="$v.d.$model[3].d1" /> -->
        <!-- <b-form-input v-model="$v.a.$model[0]" />
        <b-form-input v-model="$v.a.$model[1]" /> -->

        <!-- <div>{{ testD }}</div>
        <div>{{ test }}</div> -->

        <b-form-row>
          <i-form-group-check label="有線電話：" :item="$v.tel1">
            <i-tel v-model="$v.tel1.$model" :state="validateState($v.tel1)"> </i-tel>
          </i-form-group-check>
          {{ $v.tel1.$model }}
        </b-form-row>

        <b-form-row>
          <i-form-group-check label="有線電話(含分機)：" :item="$v.tel3">
            <i-tel show-ext v-model="$v.tel3.$model" :state="validateState($v.tel3)"></i-tel>
          </i-form-group-check>
          {{ $v.tel3.$model }}
        </b-form-row>

        <b-form-row>
          <i-form-group-check label="行動電話：" :item="$v.tel2">
            <i-tel is-mobile show-ext v-model="$v.tel2.$model" :state="validateState($v.tel2)"></i-tel>
          </i-form-group-check>
          {{ $v.tel2.$model }}
        </b-form-row>

        <b-form-row>
          <i-form-group-check label="行動電話(可切換)：" :item="$v.tel4">
            <i-tel ref="tel4Component" isMobile show-tel-type v-model="$v.tel4.$model" :state="validateState($v.tel4)"></i-tel>
          </i-form-group-check>
          {{ $v.tel4.$model }}
        </b-form-row>

        <b-form-row>
          <i-form-group-check label="有線電話(含分機,可切換)：" :item="$v.tel5">
            <i-tel ref="tel5Component" show-tel-type show-ext v-model="$v.tel5.$model" :state="validateState($v.tel5)"></i-tel>
          </i-form-group-check>
          {{ $v.tel5.$model }}
        </b-form-row>

        <b-form-row class="mb-4" style="margin-top: 300px">
          <i-form-group-check label="日期起訖：" :dual1="$v.date1" :dual2="$v.date2">
            <i-dual-date-picker :dual1.sync="$v.date1.$model" :dual2.sync="$v.date2.$model"></i-dual-date-picker>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <i-part-of-message id="123" :value="$v.message.$model" class="mt-5 mb-3"></i-part-of-message>
        </b-form-row>
        <b-form-row>
          <i-part-of-message id="321" type="hint" :value="$v.message.$model" class="mb-3"></i-part-of-message>
        </b-form-row>
        <b-form-row>
          <i-part-of-message id="456" :value="$v.message2.$model" class="mb-3"></i-part-of-message>
        </b-form-row>
        <b-form-row>
          <i-part-of-message id="654" :limit-length="30" :value="$v.message2.$model" class="mb-3"></i-part-of-message>
        </b-form-row>

        <div>
          <b-button @click="checkValidity">驗證</b-button>
          <b-button @click="reset">清除</b-button>
        </div>

        <!-- highcharts -->
        <highcharts :options="chartOptions" style="width: 100%; height: 400px"></highcharts>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import { reactive, ref, unref } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, address, lineTel, mobileTel, tel } from '@/shared/validators';
import _ from 'lodash';

// highcharts
import Highcharts from 'highcharts';
import Exporting from 'highcharts/modules/exporting';
Exporting(Highcharts);
import Vue from 'vue';
import HighchartsVue from 'highcharts-vue';
Vue.use(HighchartsVue);
import { Chart } from 'highcharts-vue';

export default {
  name: 'viewDemo03',
  components: {
    highcharts: Chart,
  },
  setup(props, context) {
    const tel4Component = ref(null);
    const tel5Component = ref(null);

    let formD = {
      // zip: '',
      // city: '10002',
      // town: '010',
      // other: '@@',
      zip: null,
      city: null,
      town: null,
      other: null,
      test1: null,
      test2: null,
      tel1: null,
      tel2: '0910121212',
      tel3: '0910121212',
      tel4: null,
      tel5: null,
      message:
        '燃煤發電PM2.5減量及視覺汙染改善之改造技術及最佳化操作檢討。中興工程 第147期 2020年4月' +
        '燃煤發電PM2.5減量及視覺汙染改善之改造技術及最佳化操作檢討。中興工程 第147期 2020年4月' +
        '燃煤發電PM2.5減量及視覺汙染改善之改造技術及最佳化操作檢討。中興工程 第147期 2020年4月',
      message2: '汙染改善之改造技術及汙染改善之改造技術及汙染改善之改造技術及汙染改善之改造技術及汙染改善之改造技術及',
      date1: null,
      date2: null,
    };
    const form = reactive(Object.assign({}, formD));
    const rules = {
      zip: {},
      city: { required },
      town: { equalsCityBoolean: address(() => form.city) },
      other: { equalsCityBoolean: address(() => form.city) },
      test1: { required },
      test2: { required },
      tel1: { lineTel },
      tel2: { mobileTel },
      tel3: { lineTel, required },
      tel4: { tel: tel(() => unref(tel4Component).telType) },
      tel5: { tel: tel(() => unref(tel5Component).telType) },
      message: {},
      message2: {},
      date1: { required },
      date2: { required },
    };
    // const { $v, checkValidity, reset } = useValidation(rules, form, formD);
    // const { $v, checkValidity, reset } = useValidation(rules, form, formD, ['city','town','other']);
    const { $v, checkValidity, reset } = useValidation(rules, form, formD, [
      ['city', 'town', 'other'],
      ['test1', 'test2'],
    ]);

    let testD = {
      a: '1',
      b: 2,
      c: {
        c1: 3,
        c2: '4',
        c3: [3, 6, 9],
      },
      d: [5, 6, 7, { d1: '8', d2: 9 }],
    };
    // let testD = {a: [3,6,9]}

    // const test = reactive(Object.assign({}, testD))
    const test = reactive(_.cloneDeep(testD));

    const ruleT = {
      a: {},
      b: {},
      c: {
        c1: { required },
        c2: {},
        c3: {},
      },
      d: {},
      // d: {required},
      // d: [{required}, {}, {}],
    };

    // const { $v, checkValidity, reset } = useValidation(ruleT, test, testD);

    // highcharts
    Highcharts.setOptions({
      lang: {
        downloadJPEG: '下載為 JPEG',
      },
    });

    var formater = new Intl.NumberFormat('en-US');

    let data = {
      plansCount: 5,
      totalBudget: 1000,
      reviewBudget: 600,
      approvedBudget: 500,
      details: [
        {
          adminOrgName: 'Roger',
          totalBudget: 200,
        },
        {
          adminOrgName: 'Peter',
          totalBudget: 100,
        },
      ],
    };

    let chartOptions = {
      chart: {
        plotBackgroundColor: null,
        plotBorderWidth: 0,
        plotShadow: false,
      },
      title: {
        text: '各計畫主管部會公共建設計畫提報經費分布圖(億元)',
      },
      subtitle: {
        text:
          '收案數' +
          data.plansCount +
          '件，計畫提報經費(A)' +
          formater.format(data.totalBudget) +
          '億元，屬本會審議範圍經費(B)' +
          formater.format(data.reviewBudget) +
          '億元，本會建議核列經費(C)' +
          formater.format(data.approvedBudget) +
          '億元，經費核列' +
          (data.reviewBudget === 0 ? 'N/A' : Math.floor((data.approvedBudget / data.reviewBudget) * 100) + '%') +
          '(C/B)',
      },
      tooltip: {
        pointFormat: '<b>{point.percentage:.1f}%</b>',
      },
      series: [
        {
          name: '主管機關',
          type: 'pie',
          innerSize: '80%',
          data: data.details.map(function (item) {
            return [item.adminOrgName, item.totalBudget];
          }),
          dataLabels: {
            enabled: true,
            format: '<b>{point.name}</b>: {y}$',
          },
        },
      ],
    };

    return {
      $v,
      checkValidity,
      reset,
      validateState,
      tel4Component,
      tel5Component,
      testD,
      test,

      chartOptions,
    };
  },
};
</script>

<style></style>

<template>
  <div>
    <!-- 查詢條件 -->
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
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-10">
          <b-collapse v-model="stepVisible">
            <b-form-row>
              <!-- 縣市dropdown(父子) -->
              <i-form-group-check :label="$t('label.County')" :item="$v.applyOrgId">
                <i-group-select-checkbox
                  v-model="$v.applyOrgId.$model"
                  :options="options.applyOrgIdList"
                  :isDropdown="true"
                ></i-group-select-checkbox>
              </i-form-group-check>
            </b-form-row>
            <div class="button-float-right">
              <b-button-toolbar>
                <b-button variant="info" @click="getChartData()">統計</b-button>
              </b-button-toolbar>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>

    <section>
      <div v-if="chartShow"></div>
    </section>

    <!-- 圖表區 -->
    <section class="container mt-2" v-if="chartShow">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">統計結果</h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="chartVisible = !chartVisible">
                <font-awesome-icon v-if="chartVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!chartVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>

        <!-- 機關名稱圖表 -->
        <div class="card-body col-10">
          <b-collapse v-model="chartVisible">
            <div>
              <label>核列件數統計表</label>
              <b-table
                class="table-sm"
                :itemsUndefinedBehavior="'loading'"
                :items="countTable.data"
                :fields="countTable.fields"
                :totalItems="countTable.totalItems"
                ref="table"
              ></b-table>
              <i-apexcharts :type="'multi-line'" :items="chartOptions.chartItem" :data="chartOptions.chartCount"></i-apexcharts>
              <label>核列金額統計表</label>
              <b-table
                class="table-sm"
                :itemsUndefinedBehavior="'loading'"
                :items="amountTable.data"
                :fields="amountTable.fields"
                :totalItems="amountTable.totalItems"
              ></b-table>
              <i-apexcharts :type="'multi-line'" :items="chartOptions.chartItem" :data="chartOptions.chartAmount"></i-apexcharts>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, reactive, ref, toRefs, onMounted, watch } from '@vue/composition-api';
import { Pagination } from '@/shared/model/pagination.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import i18n from '@/shared/i18n';
import axios from 'axios';
import { object } from '@storybook/addon-knobs';
import { commaFormatter } from '@/shared/formatter/common';

export default {
  name: 'rec0201Query',
  props: {
    isReload: {
      required: false,
    },
  },
  setup() {
    onMounted(() => {
      getAllOrgRoleArea();
    });

    const allOrgRoleArea = ref([]);

    const getAllOrgRoleArea = () => {
      axios
        .get('/service/rec-projects/get-all-org')
        .then(({ data }) => {
          allOrgRoleArea.value.splice(0, allOrgRoleArea.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      applyOrgId: [],
    };

    const form = reactive(Object.assign({}, formDefault));

    const rules = {
      applyOrgId: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const options = reactive({
      applyOrgIdList: [
        {
          parents: '-----北部地區',
          child: [
            { value: '1', text: '台北' },
            { value: '2', text: '新北' },
          ],
        },
        {
          parents: '-----南部地區',
          child: [
            { value: '3', text: '高雄' },
            { value: '4', text: '屏東' },
          ],
        },
      ],
    });

    //顯示查詢條件區
    let stepVisible = ref(true);

    //圖表區展開收合
    let chartVisible = ref(true);
    //圖表區顯示狀態
    let chartShow = ref(false);

    watch(allOrgRoleArea, () => {
      options.applyOrgIdList = allOrgRoleArea.value.map(data => {
        return {
          parents: data['parents'],
          child: data['child'],
        };
      });
    });

    //件數統計表
    const countTable = reactive({
      fields: [],
      data: undefined,
      totalItems: 0,
    });

    //金額統計表
    const amountTable = reactive({
      fields: [],
      data: undefined,
      totalItems: 0,
    });

    //年分欄位
    const yearList;

    //動態欄位生成
    const tableRefactor = () => {
      for (let i = 0; i < yearList.length; i++) {
        let template = {
          key: yearList[i],
          label: yearList[i],
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => commaFormatter(value),
        };
        countTable.fields[i] = template;
        amountTable.fields[i] = template;
      }
    };

    const chartOptions = reactive({
      chartItem: [],
      chartCount: [],
      chartAmount: [],
    });
    const getChartData = () => {
      //為重新渲染所以先改為false
      chartShow.value = false;
      axios
        .post('/service/rec-projects/org-chart-data-year', form)
        .then(({ data }) => {
          //包裝table欄位
          yearList = data['yearList'].map(e => e.year);
          //生成陣列，放入物件同時加入屬性及賦值
          let countList = [];
          let amountList = [];
          for (let i = 0; i < data['countList'].length; i++) {
            let countObj = {};
            let amountObj = {};
            for (let j = 0; j < yearList.length; j++) {
              if (j > 0) {
                countObj[yearList[j]] = data['countList'][i]['data'][j - 1];
                amountObj[yearList[j]] = data['amountList'][i]['data'][j - 1];
              }
            }
            countObj['機關名稱'] = data['countList'][i]['name'];
            amountObj['機關名稱'] = data['countList'][i]['name'];
            countList[i] = countObj;
            amountList[i] = amountObj;

            //傳入圖表值
            let chartYearList = yearList.slice(1);
            chartOptions.chartItem = chartYearList;
            chartOptions.chartCount = data['countList'].map(e => {
              return {
                name: e['name'],
                data: e['data'],
              };
            });
            chartOptions.chartAmount = data['amountList'].map(e => {
              return {
                name: e['name'],
                data: e['data'],
              };
            });
          }
          tableRefactor();
          countTable.data = countList;
          amountTable.data = amountList;
          chartShow.value = true;
        })

        .catch(notificationErrorHandler(notificationService));
    };

    return {
      $v,
      stepVisible,
      chartVisible,
      chartShow,
      validateState,
      checkValidity,
      options,
      countTable,
      amountTable,
      getChartData,
      chartOptions,
    };
  },
};
</script>
<style scoped>
.form-row {
  height: 62px;
}
.button-float-right {
  float: right;
}
</style>

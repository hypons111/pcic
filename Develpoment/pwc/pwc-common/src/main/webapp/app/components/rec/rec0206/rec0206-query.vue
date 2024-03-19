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
              <i-form-group-check :label="$t('label.typhoonName')" :item="$v.typhoonName">
                <i-group-select-checkbox
                  v-model="$v.typhoonName.$model"
                  :options="options.typhoonList"
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
        <!-- 颱風圖表 -->
        <div class="card-body col-10">
          <b-collapse v-model="chartVisible">
            <label>復建案件數統計表</label>
            <b-table
              class="table-sm"
              :itemsUndefinedBehavior="'loading'"
              :items="countTable.data"
              :fields="countTable.fields"
              :totalItems="countTable.totalItems"
            ></b-table>
            <i-apexcharts :type="'bar'" :items="chartOptions.chartItem" :data="chartOptions.chartCount"></i-apexcharts>
            <label>復建案件金額統計表</label>
            <b-table
              class="table-sm"
              :itemsUndefinedBehavior="'loading'"
              :items="amountTable.data"
              :fields="amountTable.fields"
              :totalItems="amountTable.totalItems"
            ></b-table>
            <i-apexcharts :type="'bar'" :items="chartOptions.chartItem" :data="chartOptions.chartAmount"></i-apexcharts>
          </b-collapse>
        </div>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { required } from '@/shared/validators';
import { reactive, ref, watch, onMounted } from '@vue/composition-api';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import i18n from '@/shared/i18n';
import axios from 'axios';

export default {
  name: 'rec0201Query',
  setup() {
    onMounted(() => {
      getAllTyphoon();
    });

    const allTyphoon = ref([]);

    const getAllTyphoon = () => {
      axios
        .get('/service/rec-projects/get-all-typhoon')
        .then(({ data }) => {
          allTyphoon.value.splice(0, allTyphoon.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const notificationService: NotificationService = useNotification();

    //顯示查詢條件區
    let stepVisible = ref(true);
    //圖表區展開收合
    let chartVisible = ref(true);
    //圖表區顯示狀態
    let chartShow = ref(false);

    const formDefault = {
      typhoonName: [],
    };

    const form = reactive(Object.assign({}, formDefault));

    const rules = {
      typhoonName: { notNull: required },
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const options = reactive({
      typhoonList: [],
    });

    //件數統計表
    const countTable = reactive({
      fields: [
        {
          key: 'item',
          label: $i18n.t('label.indigenousArea'),
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'cnt',
          label: $i18n.t('label.caseCount'),
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    //金額統計表
    const amountTable = reactive({
      fields: [
        {
          key: 'item',
          label: $i18n.t('label.indigenousArea'),
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'amt',
          label: $i18n.t('label.reviewAmount'),
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => commaFormatter(value),
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    //生成件數及金額圖表
    const chartOptions = reactive({
      chartItem: [],
      chartCount: [],
      chartAmount: [],
    });
    const getChartData = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //為重新渲染所以先改為false
          chartShow.value = false;
          axios
            .post('/rec-projects/chart-data-by-indigenous', form)
            .then(({ data }) => {
              chartOptions.chartItem = data['countAndAmount'].map(e => e.item);
              chartOptions.chartCount = data['countAndAmount'].map(e => e.cnt);
              chartOptions.chartAmount = data['countAndAmount'].map(e => e.amt);
              countTable.data = data['countAndAmount'];
              amountTable.data = data['countAndAmount'];
              //數值改動後重新渲染
              chartShow.value = true;
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    watch(allTyphoon, () => {
      options.typhoonList = allTyphoon.value.map(data => {
        return {
          parents: data['parents'],
          child: data['child'],
        };
      });
    });

    return {
      $v,
      options,
      stepVisible,
      chartVisible,
      chartShow,
      countTable,
      amountTable,
      getChartData,
      chartOptions,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

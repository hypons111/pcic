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
              <!-- 颱風名稱dropdown(父子) -->
              <i-form-group-check :label="$t('label.typhoonName')" :item="$v.typhoonName">
                <i-group-select-checkbox
                  v-model="$v.typhoonName.$model"
                  :options="options.typhoonList"
                  :isDropdown="true"
                ></i-group-select-checkbox>
              </i-form-group-check>
              <!-- 縣市dropdown(父子) -->
              <i-form-group-check :label="$t('label.County')" :item="$v.applyOrgId">
                <i-group-select-checkbox
                  v-model="$v.applyOrgId.$model"
                  :options="options.applyOrgIdList"
                  :isDropdown="true"
                ></i-group-select-checkbox>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 工程類別dropdown -->
              <i-form-group-check :label="$t('label.projectCodes')" :item="$v.projectCodes">
                <i-group-select-checkbox
                  v-model="$v.projectCodes.$model"
                  :options="options.projectCodesList"
                  :isDropdown="true"
                ></i-group-select-checkbox>
              </i-form-group-check>
              <!-- 鄉鎮市 -->
              <i-form-group-check :label="$t('label.city')" :item="$v.city">
                <b-form-input v-model="$v.city.$model" :state="validateState($v.city)"> </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 進度起訖 -->
              <i-form-group-check :label="$t('label.bidProgressFromTo')">
                <b-input-group>
                  <b-form-input v-model="$v.bidProgressMin.$model" :state="validateState($v.bidProgressMin)"> </b-form-input>
                  <b-input-group-text>至</b-input-group-text>
                  <b-form-input v-model="$v.bidProgressMax.$model" :state="validateState($v.bidProgressMax)"> </b-form-input>
                </b-input-group>
              </i-form-group-check>
              <!-- 工程名稱 -->
              <i-form-group-check :label="$t('label.engName')" :item="$v.projectName">
                <b-form-input v-model="$v.projectName.$model" :state="validateState($v.projectName)"> </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 金額起迄 -->
              <i-form-group-check :label="$t('label.amount')">
                <b-input-group>
                  <b-form-input v-model="$v.reviewAmountMin.$model" :state="validateState($v.reviewAmountMin)"> </b-form-input>
                  <b-input-group-text>至</b-input-group-text>
                  <b-form-input v-model="$v.reviewAmountMax.$model" :state="validateState($v.reviewAmountMax)"> </b-form-input>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <div class="button-float-right">
              <b-button-toolbar>
                <i-button class="mt-1 ml-1" type="search" @click="handleQuery"></i-button>
                <i-button class="mt-1 ml-1" type="x-circle" @click="reset"></i-button>
                <b-dropdown text="統計" variant="info" class="mt-1 ml-1" id="dropdown">
                  <b-dropdown-item @click="getChartData('typhoon')">依颱風</b-dropdown-item>
                  <b-dropdown-item @click="getChartData('projectCode')">依工程代碼</b-dropdown-item>
                  <b-dropdown-item @click="getChartData('org')">依機關名稱</b-dropdown-item>
                </b-dropdown>
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
            <div v-if="chartType === 'typhoon'">
              <label>復建案件數統計表</label>
              <b-table
                class="table-sm"
                :itemsUndefinedBehavior="'loading'"
                :items="countTable.data"
                :fields="countTable.fields"
                :totalItems="countTable.totalItems"
              ></b-table>
              <i-apexcharts :type="'pie'" :items="chartOptions.chartItem" :data="chartOptions.chartCount"></i-apexcharts>
              <label>復建案件金額統計表</label>
              <b-table
                class="table-sm"
                :itemsUndefinedBehavior="'loading'"
                :items="amountTable.data"
                :fields="amountTable.fields"
                :totalItems="amountTable.totalItems"
              ></b-table>
              <i-apexcharts :type="'pie'" :items="chartOptions.chartItem" :data="chartOptions.chartAmount"></i-apexcharts>
            </div>
          </b-collapse>
        </div>

        <!-- 工程代碼圖表 -->
        <div class="card-body col-10">
          <b-collapse v-model="chartVisible">
            <div v-if="chartType === 'projectCode'">
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
            </div>
          </b-collapse>
        </div>

        <!-- 機關名稱圖表 -->
        <div class="card-body col-10">
          <b-collapse v-model="chartVisible">
            <div v-if="chartType === 'org'">
              <label>復建案件數統計表</label>
              <b-table
                class="table-sm"
                :itemsUndefinedBehavior="'loading'"
                :items="countTable.data"
                :fields="countTable.fields"
                :totalItems="countTable.totalItems"
              ></b-table>
              <i-apexcharts :type="'line'" :items="chartOptions.chartItem" :data="chartOptions.chartCount"></i-apexcharts>
              <label>復建案件金額統計表</label>
              <b-table
                class="table-sm"
                :itemsUndefinedBehavior="'loading'"
                :items="amountTable.data"
                :fields="amountTable.fields"
                :totalItems="amountTable.totalItems"
              ></b-table>
              <i-apexcharts :type="'line'" :items="chartOptions.chartItem" :data="chartOptions.chartAmount"></i-apexcharts>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>

    <!-- 匯出按鈕 -->
    <section>
      <div class="card-body col-10">
        <b-button-toolbar>
          <i-button class="mt-1 ml-1" type="box-arrow-up-right" @click="exportOds"></i-button>
          <b-button class="mt-1 ml-1" @click="exportKml" variant="info">匯出KML</b-button>
        </b-button-toolbar>
      </div>
    </section>

    <!-- i-table -->
    <section>
      <div class="container" v-if="queryStatus">
        <i-table
          class="table-sm"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :fields="table.fields"
          :totalItems="table.totalItems"
          :is-server-side-paging="true"
          @changePagination="handlePaginationChanged($event)"
        >
        </i-table>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { numeric } from '@vuelidate/validators';
import { computed, reactive, ref, onMounted, watch } from '@vue/composition-api';
import { Pagination } from '@/shared/model/pagination.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import i18n from '@/shared/i18n';
import axios from 'axios';
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
      getAllTyphoon();
      getAllPrjCode();
    });

    const allOrgRoleArea = ref([]);

    const allTyphoon = ref([]);

    const allPrjCode = ref([]);

    const getAllOrgRoleArea = () => {
      axios
        .get('/service/rec-projects/get-all-org')
        .then(({ data }) => {
          allOrgRoleArea.value.splice(0, allOrgRoleArea.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const getAllTyphoon = () => {
      axios
        .get('/service/rec-projects/get-all-typhoon')
        .then(({ data }) => {
          allTyphoon.value.splice(0, allTyphoon.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const getAllPrjCode = () => {
      axios
        .get('/service/rec-projects/get-all-prj')
        .then(({ data }) => {
          allPrjCode.value.splice(0, allPrjCode.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      typhoonName: [],
      projectCodes: [],
      applyOrgId: [],
      city: '',
      bidProgressMin: '', //進度起
      bidProgressMax: '', //進度迄
      projectName: '',
      reviewAmountMin: '', //審議經費最小值
      reviewAmountMax: '', //審議經費最大值
      fileName: '', //預設檔名
    };

    const form = reactive(Object.assign({}, formDefault));

    const rules = {
      typhoonName: {},
      projectCodes: {},
      applyOrgId: {},
      city: {},
      bidProgressMin: { numeric },
      bidProgressMax: { numeric },
      projectName: {},
      reviewAmountMin: { numeric },
      reviewAmountMax: { numeric },
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const options = reactive({
      typhoonList: [],
      applyOrgIdList: [],
      projectCodesList: [],
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    //顯示查詢條件區
    let stepVisible = ref(true);

    //圖表區展開收合
    let chartVisible = ref(true);
    //圖表區顯示狀態
    let chartShow = ref(false);
    //圖表區展示種類
    let chartType = ref('');

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/service/rec-projects/criteria-jpa', form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const table = reactive({
      fields: [
        {
          key: 'typhoonName',
          label: $i18n.t('label.typhoon'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'approveNo',
          label: $i18n.t('label.approveNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'projectCode',
          label: $i18n.t('label.projectCodes'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'orgName',
          label: $i18n.t('label.orgName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectName',
          label: $i18n.t('label.recEngName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'city',
          label: $i18n.t('label.city'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'village',
          label: $i18n.t('label.village'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'reviewAmount',
          label: $i18n.t('label.reviewAmount'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: value => commaFormatter(value),
        },
        {
          key: 'applyAmount',
          label: $i18n.t('label.applyAmount'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: value => commaFormatter(value),
        },
        {
          key: 'bidProgress',
          label: $i18n.t('label.bidProgress'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'scheduledFinishDate',
          label: $i18n.t('label.scheduledFinishDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => (value == undefined ? '' : formatDate(new Date(value), '/')),
        },
        {
          key: 'bidDate',
          label: $i18n.t('label.bidDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => (value == undefined ? '' : formatDate(new Date(value), '/')),
        },
        {
          key: 'actualFinishDate',
          label: $i18n.t('label.actualFinishDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => (value == undefined ? '' : formatDate(new Date(value), '/')),
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    watch(allOrgRoleArea, () => {
      options.applyOrgIdList = allOrgRoleArea.value.map(data => {
        return {
          parents: data['parents'],
          child: data['child'],
        };
      });
    });

    watch(allTyphoon, () => {
      options.typhoonList = allTyphoon.value.map(data => {
        return {
          parents: data['parents'],
          child: data['child'],
        };
      });
    });

    watch(allPrjCode, () => {
      options.projectCodesList = allPrjCode.value.map(data => {
        return {
          parents: data['parents'],
          child: data['child'],
        };
      });
    });

    const exportOds = () => {
      form.fileName = 'ods' + Date.now();
      axios.post('/service/rec-projects/export-ods', form, { responseType: 'blob' }).then(response => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', form.fileName + '.ods');
        document.body.appendChild(link);
        link.click();
      });
    };

    const exportKml = () => {
      form.fileName = 'kml' + Date.now();
      axios.post('/service/rec-projects/export-kml', form, { responseType: 'blob' }).then(response => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', form.fileName + '.kml');
        document.body.appendChild(link);
        link.click();
      });
    };

    //件數統計表
    const countTable = reactive({
      fields: [
        {
          key: 'item',
          label: 'item',
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
          label: $i18n.t('label.typhoon'),
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

    const changeLabelValue = (typeValue: string) => {
      if (typeValue === 'typhoon') {
        countTable.fields[0].label = i18n.t('label.typhoon');
        amountTable.fields[0].label = i18n.t('label.typhoon');
      } else if (typeValue === 'projectCode') {
        countTable.fields[0].label = i18n.t('label.projectCodes');
        amountTable.fields[0].label = i18n.t('label.projectCodes');
      } else if (typeValue === 'org') {
        countTable.fields[0].label = i18n.t('label.orgName');
        amountTable.fields[0].label = i18n.t('label.orgName');
      }
    };

    //生成件數及金額圖表
    const chartOptions = reactive({
      chartItem: [],
      chartCount: [],
      chartAmount: [],
    });
    const getChartData = (chartSelected: string) => {
      //為重新渲染所以先改為false
      chartShow.value = false;
      let postUrl;
      if (chartSelected === 'typhoon') {
        postUrl = '/service/rec-projects/typhoon-chart-data';
      } else if (chartSelected === 'projectCode') {
        postUrl = '/service/rec-projects/projectCode-chart-data';
      } else if (chartSelected === 'org') {
        postUrl = '/service/rec-projects/org-chart-data';
      }
      axios
        .post(postUrl, form)
        .then(({ data }) => {
          chartType.value = chartSelected;
          changeLabelValue(chartSelected);
          chartOptions.chartItem = data['count'].map(e => e.item);
          chartOptions.chartCount = data['count'].map(e => e.cnt);
          chartOptions.chartAmount = data['amount'].map(e => e.amt);
          countTable.data = data['count'];
          amountTable.data = data['amount'];
          //數值改動後重新渲染
          chartShow.value = true;
        })
        .catch(notificationErrorHandler(notificationService));
    };

    return {
      $v,
      stepVisible,
      chartVisible,
      chartShow,
      chartType,
      validateState,
      checkValidity,
      queryStatus,
      handleQuery,
      reset,
      handlePaginationChanged,
      table,
      options,
      exportOds,
      exportKml,
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

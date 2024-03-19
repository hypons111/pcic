<template>
  <div>
    <apexchart :type="typeProp" width="500" :options="chartOptions" :series="series"></apexchart>
    <!-- <apexchart type="line" width="500" :options="chartOptions" :series="series"></apexchart> -->
  </div>
</template>

<script lang="ts">
export default {
  name: 'i-apexcharts',
  props: {
    type: {
      type: String,
      require: false,
    },
    items: {
      type: Array,
      require: false,
    },
    data: {
      type: Array,
      require: false,
    },
  },
  setup(props, context) {
    //類型
    const typeProp = props.type;
    //項目
    const itemsProp = props.items;
    //數據
    const dataProp = props.data;

    let series;

    let chartOptions;

    if (typeProp === 'bar' || typeProp === 'line') {
      /* 長條圖 or 折線圖 */
      series = [
        {
          name: '',
          data: dataProp, //傳入參數2陣列
        },
      ];
      chartOptions = {
        xaxis: {
          categories: itemsProp, //傳入參數1陣列
        },
      };
    } else if (typeProp === 'pie') {
      /* 圓餅圖 */
      series = dataProp;
      chartOptions = {
        chart: {
          width: 380,
          type: 'pie',
        },
        labels: itemsProp,
      };
    } else if (typeProp === 'multi-line') {
      /* 多重折線圖，傳入參數方式不同 */
      typeProp = 'line';
      series = dataProp;
      chartOptions = {
        xaxis: {
          categories: itemsProp, //傳入參數1陣列
        },
      };
    }

    return {
      series,
      chartOptions,
      typeProp,
    };
  },
};
</script>

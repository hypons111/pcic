  <template>
    <div>
      <!-- 查詢條件 -->
      <section class="container mt-2 mb-2">
        <div class="h4 m-0 py-2 button-border">工項價量分析</div>
        <div class="card mt-2">
          <div class="card-header text-left">
            <div class="align-items-center">
              <div class="col-sm-11 p-0">
                <h5 class="m-0">
                  <font-awesome-icon icon="search"></font-awesome-icon>
                  查詢條件
                </h5>
              </div>
            </div>
          </div>
  
          <div class="card-body">
  
            <div id="" class="flex card-row">
              <div class="flex item">
                <label>
                  <p class="font_red">*</p>工項編碼
                </label>
                <input id="code_input" class="form-control"></input>
                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#codeQueryModal">編碼查詢</button>
                <div class="block"></div>
              </div>
  
              <div class="flex item rightGroupRight">
                <button type="button" class="btn btn-info" data-toggle="modal"
                  data-target="#introductionModal">頁面說明</button>
              </div>
  
            </div>
  
            <div id="" class="flex card-row">
              <div class="flex item">
                <label>工項描述</label>
                <input id="description_input" class="form-control" disabled> </input>
                <div class="block"></div>
              </div>
            </div>
  
            <div id="" class="flex card-row">
              <div class="flex item">
                <label>決標日(起迄)</label>
                <i-date-picker id="startDate" class="form-control"></i-date-picker>
                <b-input-group-text>至</b-input-group-text>
                <i-date-picker id="endDate" class="form-control"></i-date-picker>
                <div class="block"></div>
              </div>
  
              <div class="flex item">
                <label>地區</label>
                <select name="city" id="city"></select>
                <div class="block"></div>
              </div>
            </div>
  
            <div id="" class="flex card-row">
              <div class="flex item">
                <label>查詢類別</label>
                <select name="scale" id="scale">
                  <option class="dropdown-item" value="0">決標價</option>
                  <option class="dropdown-item" value="1">預算價</option>
                </select>
                <div class="block"></div>
              </div>
            </div>
  
            <div id="" class="flex card-row">
              <div class="flex item">
                <label>
                  <p class="font_red">*</p>工項數量
                </label>
                <div id="mrp0106UnitGroup" class="flex">
                  <div class="flex quad">
                    <label class="tight">最少</label>
                    <input id="minProjectNumber" class="form-control"></input>
                  </div>
  
                  <div class="flex quad">
                    <label class="tight">最多</label>
                    <input id="maxProjectNumber" class="form-control"></input>
                    <input id="unit_input" class="form-control" disabled></input>
                  </div>
                </div>
  
                <button class="btn btn-info" @click="useSuggestedValue()">取用建議值</button>
              </div>
            </div>
  
            <div class="buttonGroup flex card-row">
              <button class="btn btn-info" @click="query0106()">價格查詢</button>
              <button class="btn btn-info" @click="resetQuery()">清除</button>
            </div>
          </div>
        </div>
      </section>
  
      <!-- code Modal -->
      <div class="modal fade" id="codeQueryModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button id="closeModal" type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <section class="container mt-2 mb-2">
                <div class="card">
                  <div class="card-header text-left">
                    <div class="align-items-center">
                      <div class="col-sm-11 p-0">
                        <h5 class="m-0">編碼查詢</h5>
                      </div>
                    </div>
                  </div>
  
                  <div class="card-body">
                    <div id="" class="flex card-row">
                      <div class="flex item">
                        <label class="">工項編碼或<br>工項名稱</label>
                        <input id="modal_code_input" class="form-control"></input>
                        <div class="block"></div>
                      </div>
                    </div>
  
                    <div class="buttonGroup flex card-row">
                      <button class="btn btn-info" @click="queryProjectCode()">查詢</button>
                      <button class="btn btn-info" @click="clearQueryCode()">清除</button>
                    </div>
                  </div>
                </div>
              </section>
  
              <section id="resultTable" class="container mt-4 mb-2 hidden">
                <mrp-table ref="iTable" class="table-sm" :title="'查詢結果'" :page="'mrp0106r'" :hideNo="true"
                  :itemsUndefinedBehavior="'loading'" :items="table.data" :fields="table.fields"
                  :is-server-side-paging="true" :totalItems="table.totalItems"
                  @changePagination="handlePaginationChanged($event, requestData)">
                  <template #cell(a)="row">
                    <button class="btn btn-info" @click="useProjectCode(row.item)">選用</button>
                  </template>
                </mrp-table>
              </section>
  
            </div>
          </div>
        </div>
      </div>
  
      <!-- Modal -->
      <div class="modal fade" id="introductionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLongTitle">頁面說明</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-content">
              <div class="modal-body">
                <p>本頁面可計算特定工項於特定時間段與所需數量範圍內的參考價格。</p>
                <p>計算流程如下:</p>
                <p>1.自頁面獲取給定之查詢時間、類別、工項數量。</p>
                <p>2.從公共工程價格資料庫撈取價格資料。</p>
                <p>3.判別以卡方適合度檢定判定樣本是否符合常態分佈。</p>
                <p>4.以信賴區間方法推算參考價格。</p>
              </div>
            </div>
  
          </div>
        </div>
      </div>
  
      <!-- highCharts -->
      <section id="chart" class="container mt-2 mb-2 hidden">
        <div class="card mt-2">
          <div class="card-header text-left">
            <div class="align-items-center">
              <div class="col-sm-11 p-0">
                <h5 class="m-0">
                  <font-awesome-icon icon="list"></font-awesome-icon>
                  查詢結果
                </h5>
              </div>
            </div>
          </div>

          <div id="highchart_body" class="card-body">
            <figure class="highcharts-figure">
              <div id="highChartContainer"></div>
            </figure>
          </div>
        </div>
      </section>
  
    </div>
  </template>
  <script lang="ts">
  import iDatePicker from '@/shared/i-date-picker/i-date-picker.vue';
  import mrpTable from '@/shared/i-table/mrp-table.vue';
import { Pagination } from '@/shared/model/pagination.model';
  import { useNotification } from '@/shared/notification';
  import NotificationService from '@/shared/notification/notification-service';
  import { onMounted, reactive } from '@vue/composition-api';
  import axios from 'axios';
  import { BInputGroupText } from 'bootstrap-vue';
  import Highcharts from 'highcharts';
  import HighchartsMore from 'highcharts/highcharts-more';
  import Exporting from 'highcharts/modules/exporting';
  
  export default {
    name: "mrp0106r",
    components: { mrpTable, iDatePicker, BInputGroupText },
    setup() {
      const notificationService: NotificationService = useNotification();
      let currentPage = 0; // 後端分頁用
      let totalPate = 0; // 後端分頁用
      let hightChartsTitles = {
        title: "",
        subtitle: "",
        xTitle: ""
      }
      let redBorderTop = 0
      let redBorderLeft = 0
      let redBorderRight = 0
      const requestData = {}
      const table = reactive({
        fields: [
          {
            key: 'a',
            label: '選用',
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle'
          },
          {
            key: 'code',
            label: '工項編碼',
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle'
          },
          {
            key: 'name',
            label: '工項名稱',
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle'
          },
          {
            key: 'unit',
            label: '單位',
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle'
          }
        ],
        data: [],
        totalItems: undefined,
      });

      function getCityMenu() {
        let innerHTMLstring = ""
        axios
          .get("/service/MRP0106R/queryMrpCity", { params: requestData })
          .then(({data}) => {
            data.response.forEach(city => {
              switch(city.text) {
                case "北部地區": city.id = "1,2,3,13,19,20,22"
                  break
                case "中部地區": city.id = "4,5,6,7,8"
                  break
                case "南部地區": city.id = "9,10,11,12,23"
                  break
                case "東部地區": city.id = "14,15"
                  break
              }
              innerHTMLstring += `<option class="dropdown-item" value="${city.id}">${city.text}</option>`;
              if (city.id === "21") {
                innerHTMLstring += `<option class="dropdown-item" value="19">馬祖</option>`;
              }
            });
          })
          .catch(error => { console.log("error : ", error) })
          .finally(() => { document.querySelector("#city").innerHTML = innerHTMLstring }) 
      }
  
      function queryProjectCode() {
        axios
          .get("/service/MRP0106R/queryCode", {
            params: { keyword: document.querySelector("#modal_code_input").value.trim() }
          })
          .then(({data}) => { 
            table.data = data.response 
            table.totalItems = data.response.length
          })
          .catch(error => {console.log("error message : ", error)})
          .finally(() => { document.querySelector("#resultTable").classList.remove("hidden") })        
      }
  
      function useProjectCode(item) {
        document.querySelector("#code_input").value = item.code
        document.querySelector("#description_input").value = item.name
        document.querySelector("#unit_input").value = item.unit
        hightChartsTitles.title = (item.name + " 價量散佈圖")
        hightChartsTitles.subtitle = "XXX"
        hightChartsTitles.xTitle = ("總採購數量 " + item.unit)
        document.querySelector("#closeModal").click()
        clearQueryCode()
      }

      function getRequestData() {
        requestData.resourceCode = document.querySelector("#code_input").value.trim()
        requestData.dateStart = ""
        requestData.dateEnd = ""
        requestData.isBudget = ""
        requestData.region = ""
        requestData.regionName = ""
        const min = document.querySelector("#minProjectNumber").value.trim()
        const max = document.querySelector("#maxProjectNumber").value.trim()
        requestData.lowerBound = min
        requestData.upperBound =  max
        redBorderLeft = Number(min)
        redBorderRight = Number(max)
      }
        
      function checkInput(requestData) {
        if (requestData.resourceCode === "") {
          notificationService.danger("工項編碼不可留空。")
          return false
        }

        if (requestData.lowerBound === "" ) {
          notificationService.danger("最少工項數量不可留空。")
          return false
        }
        if (requestData.upperBound === "") {
          notificationService.danger("最多工項數量不可留空。")
          return false
        }
        return true
      }

      function useSuggestedValue() {
        getRequestData()
        axios
          .get("/service/MRP0106R/querySuggestBound", { params: requestData })
          .then(({data}) => {
            if(data.response.length !== 0) {
              document.querySelector("#minProjectNumber").value = data.lowerBound
              document.querySelector("#maxProjectNumber").value = data.upperBound
            }
          })
          .catch(error => { console.log("error : ", error) })
          .finally(() => {}) 
      }

      function query0106() {
        getRequestData()
        console.log("requestData : ", requestData)
        if(!checkInput(requestData)) { return }
        
        axios
          .get("/service/MRP0106R/queryPrice", { params: requestData })
          .then(({ data }) => {
            console.log("queryPrice respose data : ", data)
            HighchartsMore(Highcharts)
            Exporting(Highcharts);
            Highcharts.chart('highChartContainer', {
              chart: {
                type: 'scatter',
                zoomType: 'xy',
                events: {
                  load: function() {
                    console.log("yAxis : ", this.yAxis[0].tickPositions.slice(-1)[0])
                    redBorderTop = this.yAxis[0].tickPositions.slice(-1)[0]
                  }
                }
              },

              colors: [
                "rgba(255, 0, 0)", 
                "rgba(100, 100, 100)"
              ],
        
              title: { text: hightChartsTitles.title },
      
              subtitle: {
                text: hightChartsTitles.subtitle,
                align: 'center'
              },
      
              caption: {
                text: '<p style="color: red">計算結果不建議參考，因為選取樣本不符合常態分佈</p>',
                align: 'center'
              },

              xAxis: {
                title: { text: hightChartsTitles.xTitle },
                labels: { format: '{value}' },
                startOnTick: true,
                endOnTick: true,
                showLastLabel: false
              },
      
              yAxis: {
                title: { text: '平均價格 元' },
                labels: { 
                  format: '{value}', 

                }
              },
      
              legend: { enabled: true },
      
              plotOptions: {
                scatter: {
                  marker: {
                    radius: 2.5,
                    symbol: 'circle',
                    states: {
                      hover: {
                        enabled: true,
                        lineColor: 'rgb(100,100,100)'
                      }
                    }
                  },
                  states: {
                    hover: {
                      marker: {
                        enabled: true
                      }
                    }
                  }
                }
              },
      
              tooltip: { enabled: true },
      
              series: [
                {
                  name: '區間內資料',
                  data: [[200, 500]], //data.inBoundData, // $$$
                  id: 'in, side',
                  marker: { symbol: 'circle' }
                },{
                  name: '區間外資料',
                  data: [[400, 800]], //data.outBoundData, // $$$
                  id: 'outside',
                  marker: { symbol: 'circle' }
                },{
                  name: '',
                  type: 'polygon',
                  color: 'none',
                  lineWidth: 1,
                  lineColor:'red',
                  enableMouseTracking: false,
                  data: getRedBorder()
                }
              ]
            });
          })
          .catch(error => { console.log("error : ", error) })
          .finally(() => {
            Highcharts.chart('highChartContainer', {
              colors: [
                "rgba(255, 0, 0)", 
                "rgba(100, 100, 100)"
              ],
              series: [
                {
                  name: '區間內資料',
                  data: [[200, 500]], //data.inBoundData, // $$$
                  id: 'in, side',
                  marker: { symbol: 'circle' }
                },{
                  name: '區間外資料',
                  data: [[400, 800]], //data.outBoundData, // $$$
                  id: 'outside',
                  marker: { symbol: 'circle' }
                },{
                  name: '',
                  type: 'polygon',
                  color: 'none',
                  lineWidth: 1,
                  lineColor:'red',
                  enableMouseTracking: false,
                  data: getRedBorder()
                }
              ]
            });
          }) 
        document.querySelector("#chart").classList.remove("hidden")
      }

      function getRedBorder() {
        return [
          [redBorderLeft, 0], // 左下 [x軸, y軸]
          [redBorderRight, 0], // 右下 [x軸, y軸]
          [redBorderRight, redBorderTop], // 右上 [x軸, y軸]
          [redBorderLeft, redBorderTop] // 左上 [x軸, y軸]
        ]
      }

      function clearQueryCode() {
        table.data = []
        document.querySelector("#resultTable").classList.add("hidden")
        document.querySelector("#modal_code_input").value = ""
      }

      function clearChart() {
        document.querySelector("#chart").classList.add("hidden")
        // chartOptions.series[0].data = []
        // chartOptions.series[1].data = []
      }
  
      function resetQuery() {
        clearChart()
        document.querySelector("#code_input").value = ""
        document.querySelector("#description_input").value = ""
        document.querySelector("#unit_input").value = ""
        document.querySelector('#startDate input').value = ""
        document.querySelector('#endDate input').value = ""
        document.querySelector('#city').value = "all"
        document.querySelector("#minProjectNumber").value = ""
        document.querySelector("#maxProjectNumber").value = ""
        document.querySelector('#scale').value = "0"
      }

      // 分頁
      const handlePaginationChanged = (pagination: Pagination) => {
        const form = {};
        Object.assign(form, pagination);
        handleQuery(form);
      };

      const handleQuery = form => {
          axios
            .get("/service/MRP0106R/queryCode", {
              params: { keyword: document.querySelector("#modal_code_input").value.trim() }
            })
            .then(({ data }) => {
              table.data = data.response.slice((form.currentPage * form.perPage), data.response.length);
            })
          .finally()
      };

      onMounted(() => {
        getCityMenu()
      });
  
      return {
        table,
        getCityMenu,
        useSuggestedValue,
        queryProjectCode,
        clearQueryCode,
        clearChart,
        useProjectCode,
        getRequestData,
        query0106,
        resetQuery,
        handlePaginationChanged
      };
    },
  };
  </script>
  
  <style scoped>
  @import 'mrp.css';
  
  #unit_input {
    width: 80px !important;
  }
  
  .modal-body table * {
    border: 1px solid #dee2e6;
  }
  
  #codeQueryModal>div {
    min-width: 100% !important;
    /* border: 1px solid red; */
  }
  
  #codeQueryModal>div>div {
    min-width: 80% !important;
    margin: auto;
    /* border: 1px solid blue; */
  }
  </style>
  
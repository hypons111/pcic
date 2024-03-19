<template>
  <div>
    <!-- 查詢條件 -->
    <section id="searchSection" class="container mt-2 mb-2">
      <div class="h4 m-0 py-2 button-border">使用者紀錄管理</div>
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
          <div class="flex card-row">
            <div class="flex item">
              <label class="wide">
                <p class="font_red">*</p>使用者類型
              </label>
              <select id="roleMenu" class="form-control" name="">
                <option class="dropdown-item" disabled selected value>請選擇</option>
                <option class="dropdown-item" value="member">會員</option>
                <option class="dropdown-item" value="passer">非會員</option>
                <option class="dropdown-item" value="3">公部門</option>
                <option class="dropdown-item" value="4">技師公會</option>
                <option class="dropdown-item" value="5">同業公會</option>
                <option class="dropdown-item" value="6">建築師事務所</option>
                <option class="dropdown-item" value="7">綜合營造所</option>
                <option class="dropdown-item" value="8">顧問公司</option>
                <option class="dropdown-item" value="9">其他</option>
              </select>
              <div class="block"></div>
            </div>
          </div>

          <div class="flex card-row">
            <div id="dateGroup" class="flex item">
              <label class="wide"><p class="font_red">*</p>查詢時間區間</label>
              <i-date-picker id="startDate" class="form-control"></i-date-picker>
              <b-input-group-text>至</b-input-group-text>
              <i-date-picker id="endDate" class="form-control"></i-date-picker>
              <div class="block"></div>
            </div>
          </div>

          <div class="flex card-row">
            <div class="flex item">
              <label class="wide">關鍵字</label>
              <input id="keyword" class="form-control"></input>
              <div class="block"></div>
            </div>
          </div>

          <div class="buttonGroup flex card-row">
            <button class="btn btn-info" @click="query0103()">查詢</button>
            <button class="btn btn-info" @click="resetQuery()">清除</button>
          </div>
        </div>
      </div>
    </section>

    <!-- 查詢結果 -->
    <section id="resultTable" class="container mt-4 mb-2 hidden">
      <mrp-table
        ref="mrpTable" 
        class="table-sm" 
        :page="'mrp0103r'" 
        :hideNo="true" 
        :downloadButton="true"
        :statisticsButton="true" 
        :itemsUndefinedBehavior="'loading'" 
        :items="table.data" 
        :fields="table.fields"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged($event)">
      </mrp-table>
    </section>

    <!-- 統計 -->
    <section id="statisticsTable" class="container mt-4 mb-2 hidden">
      <div class="card table-sm">
        <div class="card-header d-none d-md-block">
          <h5 class="my-0">
            <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="list" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="svg-inline--fa fa-list fa-w-16">
              <path data-v-a04363e0="" fill="currentColor" d="M80 368H16a16 16 0 0 0-16 16v64a16 16 0 0 0 16 16h64a16 16 0 0 0 16-16v-64a16 16 0 0 0-16-16zm0-320H16A16 16 0 0 0 0 64v64a16 16 0 0 0 16 16h64a16 16 0 0 0 16-16V64a16 16 0 0 0-16-16zm0 160H16a16 16 0 0 0-16 16v64a16 16 0 0 0 16 16h64a16 16 0 0 0 16-16v-64a16 16 0 0 0-16-16zm416 176H176a16 16 0 0 0-16 16v32a16 16 0 0 0 16 16h320a16 16 0 0 0 16-16v-32a16 16 0 0 0-16-16zm0-320H176a16 16 0 0 0-16 16v32a16 16 0 0 0 16 16h320a16 16 0 0 0 16-16V80a16 16 0 0 0-16-16zm0 160H176a16 16 0 0 0-16 16v32a16 16 0 0 0 16 16h320a16 16 0 0 0 16-16v-32a16 16 0 0 0-16-16z" class=""></path>
            </svg>
            統計
          </h5>
        </div>
        <div class="card-body m-0 p-0">
          <div id="" style="padding: 10px 20px;">
            <button id="statisticsTableBackButton" class="mt-1 mb-1 btn btn-info" @click="hideStatisticsTable()">回上頁</button>
          </div>
          <div class="m-0 table-responsive">
            <table role="table" aria-busy="false" aria-colcount="7" class="table b-table table-hover table-bordered">
              <thead role="rowgroup" class="">
                <tr role="row" class="">
                  <th role="columnheader" scope="col" aria-colindex="1" class="text-center">
                    <div></div>
                  </th>
                  <th role="columnheader" scope="col" aria-colindex="2" class="text-center">
                    <div>使用者數量</div>
                  </th>
                  <th role="columnheader" scope="col" aria-colindex="3" class="text-center">
                    <div>查詢關鍵字</div>
                  </th>
                  <th role="columnheader" scope="col" aria-colindex="4" class="text-center">
                    <div>所得資料筆數</div>
                  </th>
                  <th role="columnheader" scope="col" aria-colindex="5" class="text-center">
                    <div>所得總樣本數</div>
                  </th>
                  <th role="columnheader" scope="col" aria-colindex="6" class="text-center">
                    <div>下載資料筆數</div>
                  </th>
                </tr>
              </thead>

              <tbody role="rowgroup">
                <tr role="row" aria-rowindex="1" class="">
                  <td class="text-center align-middle">總量</td>
                  <td id="modal_user_cnt" class="text-center align-middle"></td>
                  <td id="modal_keyword_cnt" class="text-center align-middle"></td>
                  <td id="modal_num_cnt" class="text-center align-middle"></td>
                  <td id="modal_sam_num_cnt" class="text-center align-middle"></td>
                  <td id="modal_dow_num_cnt" class="text-center align-middle"></td>
                </tr>
                <tr role="row" aria-rowindex="2" class="">
                  <td class="text-center align-middle">平均每人每次查詢量</td>
                  <td class="text-center align-middle"></td>
                  <td id="modal_avg_keyword_cnt" class="text-center align-middle"></td>
                  <td id="modal_avg_num_cnt" class="text-center align-middle"></td>
                  <td id="modal_avg_sam_num_cnt" class="text-center align-middle"></td>
                  <td id="modal_avg_dow_num_cnt" class="text-center align-middle"></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </section>

  </div>
</template>
<script lang="ts">
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import iDatePicker from '@/shared/i-date-picker/i-date-picker.vue';
import { Pagination } from '@/shared/model/pagination.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { onMounted, reactive } from '@vue/composition-api';
import axios from 'axios';
import util from '../../../../../mrp_util';
import mrpTable from '@/shared/i-table/mrp-table.vue';
import {
  BInputGroupText
} from 'bootstrap-vue';

export default {
  name: "mrp0103r",
  components: {  mrpTable, iDatePicker, BInputGroupText },
  setup() {
    const notificationService: NotificationService = useNotification();
    const defaultDate = {};
    const requestData = {};
    let currentPage = 0; // 後端分頁用
    let totalPage = 0; // 後端分頁用
    const table = reactive({
      fields: [
        {
          key: 'idx',
          label: '編號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: number) => {
            return value + currentPage
          }
        },
        {
          key: 'create_date',
          label: '時間',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'name',
          label: '使用者',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'type',
          label: '單位類型',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => {
            switch(value) {
              case "member":
                return "會員";
              case "passer":
                return "非會員";
            }
          }
        },
        {
          key: 'action',
          label: '動作/頁籤',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'ip',
          label: '位址',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'search_conditions',
          label: '查詢條件',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        }
      ],
      data: [],
      totalItems: undefined,
    });

    function addListener() {
      document.querySelector('#downloadButton').addEventListener("click", () => { download() })
      document.querySelector('#statisticsButton').addEventListener("click", () => { showStatisticsTable() })
    }

    function getDefaultDate() {
      const d = new Date()
      const CURRENT_DATE_STRING = `${d.getFullYear()}/${d.getMonth()+1}/${d.getDate()}`
      const startDateString = util().toY3(util().fullDateWithLastMonth(CURRENT_DATE_STRING, "/"))
      const endDateString = util().toY3(CURRENT_DATE_STRING)
      defaultDate.startDate = startDateString
      defaultDate.endDate = endDateString
      setDatepicker(startDateString, endDateString)
    }

    function setDatepicker(s, e) {
      document.querySelector('#startDate input').value = s
      document.querySelector('#endDate input').value = e
    }

    function getRequestData() {
      requestData.type = document.querySelector("#roleMenu").value
      requestData.create_sdate = util().toY4(document.querySelector("#startDate input").value)
      requestData.create_edate = util().toY4(document.querySelector("#endDate input").value)
      requestData.search_conditions = document.querySelector("#keyword").value.trim()
      requestData.queryArea = 0
      requestData.perPage = 20
    }

    function checkInput(requestData) {
      if (requestData.create_edate === "1911") {
        notificationService.danger("時間區間不可留空。")
        return false
      }
      
      if (requestData.create_sdate === "1911") {
        notificationService.danger("時間區間不可留空。")
        return false
      }
      return true
    }

    function getDataLength() {
      return axios.get('/service/MRP0103R/queryMrpRecordingSize', { params: requestData })
    }

    function getData() {
      return axios.get('/service/MRP0103R/queryMrpRecording', { params: requestData })
    }

    function query0103() {
      getRequestData()
      if(!checkInput(requestData)) { return }
      Promise.all([getDataLength(), getData()])
        .then((response) => {
            totalPage = response[0].data.size;
            table.totalItems = response[0].data.size;
            table.data = response[1].data.response;
        })
        .finally(() => {
          document.querySelector("#resultTable").classList.remove("hidden")
          setDatepicker(util().toY3(requestData.create_sdate), util().toY3(requestData.create_edate))
          addListener()
        })
    }

    function resetQuery() {
      setDatepicker(defaultDate.startDate, defaultDate.endDate)
    }

    function download() {
      axios
        .get("/service/MRP0103R/downloadMrp0103R", { 
          params: requestData, 
          responseType: 'blob'
        })
        .then((response) => {
          const href = URL.createObjectURL(response.data)
          const link = document.createElement('a')
          link.href = href;
          link.setAttribute('download', `使用者紀錄管理_${util().idGenerator()}`)
          document.body.appendChild(link)
          link.click()
          document.body.removeChild(link)
          URL.revokeObjectURL(href)
        });
    }

    function showStatisticsTable() {
      axios
        .get("/service/MRP0103R/queryMrpRecordingStatistics", { params: requestData })
        .then(({ data }) => {
          let user_cnt = Number(data.response[0].user_cnt)
          let keyword_cnt = Number(data.response[0].search_conditions)
          let num_cnt = Number(data.response[0].num)
          let sam_num_cnt = Number(data.response[0].sample_num)
          let dow_num_cnt = Number(data.response[0].download_num)

          document.querySelector("#modal_user_cnt").innerText = user_cnt
          document.querySelector("#modal_keyword_cnt").innerText = keyword_cnt
          document.querySelector("#modal_num_cnt").innerText = num_cnt
          document.querySelector("#modal_sam_num_cnt").innerText = sam_num_cnt
          document.querySelector("#modal_dow_num_cnt").innerText = dow_num_cnt

          document.querySelector("#modal_avg_keyword_cnt").innerText = keyword_cnt / user_cnt
          document.querySelector("#modal_avg_num_cnt").innerText = num_cnt / user_cnt
          document.querySelector("#modal_avg_sam_num_cnt").innerText = sam_num_cnt / user_cnt
          document.querySelector("#modal_avg_dow_num_cnt").innerText = dow_num_cnt / user_cnt

          document.querySelector("#statisticsTable").classList.remove("hidden")
          document.querySelector("#searchSection").classList.add("hidden")
          document.querySelector("#resultTable").classList.add("hidden")
        })
    }

    function hideStatisticsTable() {
      document.querySelector("#statisticsTable").classList.add("hidden")
      document.querySelector("#searchSection").classList.remove("hidden")
      document.querySelector("#resultTable").classList.remove("hidden")
    }

    /* 分頁 */
    const handlePaginationChanged = (pagination: Pagination, requestData) => {
      const form = {}
      Object.assign(form, pagination);
      Object.assign(form, requestData);
      handleQuery(form);
    };

    const handleQuery = form => {
      currentPage = (form.perPage * form.currentPage)
      requestData.queryArea = ((form.currentPage * form.perPage))
      requestData.perPage = form.perPage

      axios
        .get("/service/MRP0103R/queryMrpRecording", { params: requestData })
        .then(({ data }) => {
          table.data = data.response
          table.totalItems = totalPage
        })
        .catch(notificationErrorHandler(notificationService))
        .finally(() => {
          setDatepicker(util().toY3(requestData.create_sdate), util().toY3(requestData.create_edate))
        }) 
    };
    
    onMounted(() => {
      getDefaultDate()
    });

    return {
      table,
      setDatepicker,
      query0103,
      hideStatisticsTable,
      resetQuery,
      download,
      showStatisticsTable,
      handleQuery,
      handlePaginationChanged,
    };
  },
};
</script>

<style scoped>
@import 'mrp.css';
</style>
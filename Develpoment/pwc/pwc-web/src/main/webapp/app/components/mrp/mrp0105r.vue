<template>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2 mb-2">
      <div class="h4 m-0 py-2 button-border">變動篩選管理</div>
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
          <div id="queryTime" class="flex card-row">
            <div class="flex item">
              <label>查詢時間區間</label>
              <i-date-picker id="startDate" class="form-control"></i-date-picker>
              <b-input-group-text>至</b-input-group-text>
              <i-date-picker id="endDate" class="form-control"></i-date-picker>
              <div class="block"></div>
            </div>
          </div>

          <div class="flex card-row">
            <div class="flex item">
              <label>關鍵字</label>
              <input id="keyword"  class="form-control"></input>
              <div class="block"></div>
            </div>
          </div>

          <div class="buttonGroup flex card-row">
              <button class="btn btn-info" @click="query0105()">查詢</button>
              <button class="btn btn-info" @click="resetQuery()">清除</button>
            </div>
        </div>
      </div>
    </section>
    <!--    查詢結果-->
    <section id="resultTable" class="container mt-4 mb-2 hidden">
      <mrp-table
        ref="iTable"
        class="table-sm"
        :page="'mrp0105r'"
        :hideNo="true"
        :downloadButton="true"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged($event)"
      >
        <template #cell(a)="row">
          <div>
            <button
              class="btn btn-info"
              data-toggle="modal"
              data-target="#exampleModal"
              @click="showModal(row.item)">檢視</button>
          </div>

          <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">檢視</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div id="modal-body" class="">
                  <div class="container-fluid">
                    <div id="modal-table_1" class="row">
                      <table>
                        <tbody>
                          <tr>
                            <td class="header headerOnLeft">工項名稱</td>
                            <td id="name" class="bodyOnLeft"></td>
                            <td class="header headerOnRight">價格類型</td>
                            <td id="priceType" class="bodyOnRight"></td>
                          </tr>
                          <tr>
                            <td class="header">編碼</td>
                            <td id="code"  class="bodyOnLeft"></td>
                            <td class="header">列管時間</td>
                            <td id="createtime"  class="bodyOnRight"></td>
                          </tr>
                          <tr>
                            <td class="header">平均值</td>
                            <td id="avg" class="bodyOnLeft"></td>
                            <td class="header">與上月相比變動幅度</td>
                            <td id="delta" class="bodyOnRight"></td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                    <div id="modal-table_2" class="row mt-4">
                      <table>
                        <thead>
                          <tr>
                            <td class="header">編號</td>
                            <td class="header">資料時間</td>
                            <td class="header">原描述</td>
                            <td class="header">單位</td>
                            <td class="header">價錢</td>
                            <td class="header">列管狀態</td>
                          </tr>
                        </thead>
                        <tbody id="modal-table_2_tbody"></tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </template>
      </mrp-table>
    </section>
  </div>
</template>
<script lang="ts">
import { validateState } from '@/shared/form';
import { Pagination } from '@/shared/model/pagination.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { onMounted, reactive } from '@vue/composition-api';
import axios from 'axios';
import util from '../../../../../mrp_util';
import iDatePicker from '@/shared/i-date-picker/i-date-picker.vue'
import mrpTable from '@/shared/i-table/mrp-table.vue';
import {
  BInputGroupText
} from 'bootstrap-vue';


export default {
  components: { mrpTable, iDatePicker, BInputGroupText },
  setup() {
    const notificationService: NotificationService = useNotification();
    const defaultDate = {};
    const requestData = {};
    let xlsFileName: ""
    let currentPage = 0; // 後端分頁用
    let totalPate = 0; // 後端分頁用
    const table = reactive({
      fields: [
        {
          key: 'id',
          label: '編號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'date',
          label: '列管時間',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'name',
          label: '工項名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'pricetype',
          label: '價格類型',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => {
            if (value === "2") {
              return "預算價格"
            } else if (value === "5") {
              return "決標價格"
            }
          }
        },
        {
          key: 'delta',
          label: '變動幅度',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'status',
          label: '列管狀態',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => {
            if (value === "0") {
              return "列管"
            } else if (value === "1") {
              return "解除列管"
            }
          }
        },
        {
          key: 'a',
          label: '詳情',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        }
      ],
      data: [],
      totalItems: undefined,
    });

    function addListener() {
      document.querySelector('#downloadButton').addEventListener("click", e => { download() })
    }

    function getDefaultDate() {
      axios
        .get("/service/MRP0105R/queryLatestDate")
        .then(({ data }) => {
          defaultDate.startDate = data.response[0].start_date
          defaultDate.endDate = data.response[0].end_date
          setDatepicker(data.response[0].start_date, data.response[0].end_date)
        })
        .catch(error => { console.log("ERROR: ", error) })
    }

    function setDatepicker(s, e) {
      document.querySelector('#startDate input').value = s
      document.querySelector('#endDate input').value = e
    }

    function getRequestData() {
      requestData.start_date = util().toY4(document.querySelector('#startDate input').value)
      requestData.end_date = util().toY4(document.querySelector('#endDate input').value)
      requestData.keyword = document.querySelector("#keyword").value.trim()
    }

    function checkInput(requestData) {
      if (requestData.start_date === "1911" || requestData.end_date === "1911") {
        notificationService.danger('時間區間不可留空。');
        return false
      }
      return true
    }

    function query0105() {
      getRequestData()
      if(!checkInput(requestData)) { return }
      axios
        .get('/service/MRP0105R/queryMrpDelta', { params: requestData })
        .then(({ data }) => {
          table.data = data.response
          table.totalItems = data.response.length
          xlsFileName = data.fileName
        })
        .catch(error => { notificationService.danger('error'); })
        .finally(() => {
          document.querySelector("#resultTable").classList.remove("hidden")
          setDatepicker(util().toY3(requestData.start_date), util().toY3(requestData.end_date))
        }) 
    }

    function resetQuery() {
      setDatepicker(defaultDate.startDate, defaultDate.endDate)
      document.querySelector("#keyword").value = ""
    }

    function download() {
      if(table.totalItems === 0 ) {
        notificationService.danger('查無資料');
        return
      }
      axios
        .get("/service/MRP0105R/downloadExcel", {
          params: { fileName: xlsFileName }, 
          responseType: 'blob'
        })
        .then((response) => {
          const href = URL.createObjectURL(response.data)
          const link = document.createElement('a')
          link.href = href;
          link.setAttribute('download', `${xlsFileName}`)
          document.body.appendChild(link)
          link.click()
          document.body.removeChild(link)
          URL.revokeObjectURL(href)
        });
    }

    function showModal(data) {
      const deltaRequestData = {
        create_date: util().toY4(data.date),
        code: data.code,
        award_budget_date: util().toY4(data.award_budget_date),
        delta: data.delta
      }
      console.log("request : ", deltaRequestData)
      
      axios
      .get('/service/MRP0105R/queryMrpView', { params: deltaRequestData })
      .then(({ data }) => {
        console.log("respone : ", data)

        let dataString = ""
          document.querySelector("#name").innerText = data.response[0].name
          document.querySelector("#priceType").innerText = data.response[0].pricetype === "2" ? "預算" : "決算"
          document.querySelector("#code").innerText = data.response[0].code
          document.querySelector("#createtime").innerText = data.response[0].create_date
          document.querySelector("#avg").innerText = data.avg
          document.querySelector("#delta").innerText = deltaRequestData.delta
          data.response.forEach(item => {
            dataString += `
              <tr>
                <td>${item.id}</td>
                <td>${item.award_budget_date}</td>
                <td style="text-align: justify; padding: 0px 10px;">${item.description_tw}</td>
                <td>${item.unit}</td>
                <td>${item.price}</td>
                <td>${item.used === "0" ? "列管" : "解除列管"}</td
              </tr>`
          })
        })
        .catch(error => { console.log("ERROR: ", error) })
        .finally(() => { document.querySelector("#modal-table_2_tbody").innerHTML = dataString }) 
    }

    /* 分頁 */
    const handlePaginationChanged = (pagination : Pagination, requestData) => {
      const form = {}
      Object.assign(form, pagination);
      Object.assign(form, requestData);
      handleQuery(form);
    };

    const handleQuery = form => {
      axios
        .get('/service/MRP0105R/queryMrpDelta', { params: requestData })
        .then(({ data }) => {
          table.data = data.response.slice((form.currentPage * form.perPage), data.response.length);
        })
        .finally()
    };

    onMounted(() => {
      getDefaultDate()
      addListener()
    });

    return {
      table,
      setDatepicker,
      query0105,
      resetQuery,
      showModal,
      validateState,
      handleQuery,
      handlePaginationChanged,

    };
  },
};
</script>

<style scoped>
  @import 'mrp.css';
  @import 'viewModal.css';
  
  #modal-table_2 td.header:nth-child(1),
  #modal-table_2 td.header:nth-child(5) {
    width: 70px;
  }
  #modal-table_2 td.header:nth-child(2),
  #modal-table_2 td.header:nth-child(4),
  #modal-table_2 td.header:nth-child(6) {
    width: 140px;
  }

</style>
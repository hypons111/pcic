<template>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2 mb-2">
      <div class="h4 m-0 py-2 button-border">上傳結果查詢</div>
      <p id="timer"></p><!-- 目前線上人數共{{ onPeopleNumber }}人{{ datetime }} -->
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
              <label class="">機關名稱或<br>機關代碼</label>
              <input id="ORG" class="form-control"> </input>
              &nbsp;
              <a id='' href="https://web.pcc.gov.tw/prkms/tender/common/orgName/indexTenderOrgName" target="_blank">
                <img id='openExclamationMarkInstruction' src="./image/exclaim_icon.png" alt="" width="20" height="20"></img>
                機關查詢
              </a>
              &nbsp;
              <div class="block"></div>
            </div>

            <div class="flex item">
              <label class="">標案名稱或<br>標案編號</label>
              <input id='engineering' class="form-control"> </input>
              <div class="block"></div>
            </div>
          </div>

          <div id="keyword" class="flex card-row">
            <div class="flex item">
              <label class="">決標日期或<br>決標公告日期</label>
              <i-date-picker id="startDate" class="form-control"></i-date-picker>
              <b-input-group-text>至</b-input-group-text>
              <i-date-picker id="endDate" class="form-control"></i-date-picker>
              <div class="block"></div>
            </div>
          </div>

          <div id="keyword" class="flex card-row">
            <div class="flex item double">
              <p style="color:red">※僅限新版工程價格電子檔上傳專屬平台(106年12月1日上線)之傳輸結果查詢。</p>
              <div class="block"></div>
            </div>
          </div>

          <div class="buttonGroup flex card-row">
            <button class="btn btn-info" @click="query0301()">查詢</button>
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
        :title="'查詢結果 (※查詢結果可能包含本次登入標案)'"
        :page="'mrp0301r'"
        :itemsUndefinedBehavior="'loading'" 
        :items="table.data" 
        :fields="table.fields" 
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged($event, requestData)"
      >

        <template #cell(option)="row">
          <div>
            <button
            class="btn btn-info"
            data-toggle="modal"
            data-target="#exampleModal"
            @click="showModalContents(row.item)">查看</button>
          </div>

          <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">上傳紀錄</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <div class="container-fluid">
                    <div id="modal-table" class="row"></div>
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
import iDatePicker from '@/shared/i-date-picker/i-date-picker.vue';
import mrpTable from '@/shared/i-table/mrp-table.vue';
import { Pagination } from '@/shared/model/pagination.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { onMounted, reactive } from '@vue/composition-api';
import axios from 'axios';
import { BInputGroupText } from 'bootstrap-vue';
import util from '../../../../../mrp_util';

export default {
  name: "mrp0301r", 
  components: { mrpTable, iDatePicker, BInputGroupText },
  data() {
    return {
      onPeopleNumber: 1,
      datetime: '',
      datetimeSwitch: undefined,
      requestData: {}
    }
  },

  methods: {
    getDatetime() {
      const d = new Date()
      const timer = document.querySelector("#timer")
      timer.innerText = `
        目前線上人數共 ${this.changeYearFormat(d.getFullYear())}/${d.getMonth() + 1}/${d.getDate()} ${this.ampm(d.getHours())} ${this.addZero(d.getHours())}:${this.addZero(d.getMinutes())}:${this.addZero(d.getSeconds())}
      `
    },
    changeYearFormat(year) { return year - 1911 },
    ampm(hour) { return hour > 12 ? '上午' : '下午' },
    addZero(value) { return value < 10 ? value = '0' + value : value },
    doSetInterval() {
      this.interval = window.setInterval(() => {
        this.getDatetime()
      }, 1000)
    },
    setDefaultDate() {
      const d = new Date()
      const lastDay = (new Date(d.getFullYear(), d.getMonth() + 1, 0)).toString().split(" ")[2];
      const startDateString = `${d.getFullYear() - 1911}/${(d.getMonth() + 1).toString().padStart(2, "0")}/01`
      const endDateString = `${d.getFullYear() - 1911}/${(d.getMonth() + 1).toString().padStart(2, "0")}/${lastDay}`
      window.setTimeout(() => {
        document.querySelector('#startDate input').value = startDateString
        document.querySelector('#endDate input').value = endDateString
      }, 0)
    }
  },
  
  mounted() {
    this.doSetInterval()
    this.setDefaultDate()
  },
  
  beforeDestroy() {
    clearInterval(this.interval)
    window.clearInterval(this.interval)
  },
  
  setup() {
    const notificationService: NotificationService = useNotification();
    const defaultDate = {};
    const requestData = {};
    let currentPage = 0; // 後端分頁用
    let totalPate = 0; // 後端分頁用
    const table = reactive({
      fields: [
        {
          key: 'create_date',
          label: '上傳日期',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => {
            var date = new Date(value);
            const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            const hour = date.getHours().toString().padStart(2, '0');
            const minute = date.getMinutes().toString().padStart(2, '0');
            return year + '-' + month + '-' + day + ' ' + hour + ':' + minute
          }
        },{
          key: 'status',
          label: '上傳狀態',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },{
          key: 'org_name',
          label: '主辦機關',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },{
          key: 'org_code',
          label: '機關代碼',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },{
          key: 'engineering_name',
          label: '標案名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },{
          key: 'engineering_code',
          label: '標案案號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },{
          key: 'budget_date',
          label: '決標日期',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },{
          key: 'notice_date',
          label: '決標公告日期',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },{
          key: 'option',
          label: '動作',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        }
      ],
      data: undefined,
      totalItems: undefined,
    });
    
    function getDefaultDate() {
      const d = new Date()
      const lastDay = (new Date(d.getFullYear(), d.getMonth() + 1, 0)).toString().split(" ")[2];
      const startDateString = util().toY3(`${d.getFullYear()}/${d.getMonth()+1}/01`)
      const endDateString = util().toY3(`${d.getFullYear()}/${d.getMonth()+1}/${lastDay}`)
      defaultDate.startDate = startDateString
      defaultDate.endDate = endDateString
      window.setTimeout(() => {
        setDatepicker(startDateString, endDateString)
      }, 0)
    }

    function setDatepicker(s, e) {
      document.querySelector('#startDate input').value = s
      document.querySelector('#endDate input').value = e
    }

    function getRequestData() {
      requestData.org = document.querySelector('#ORG').value.trim()
      requestData.engineering = document.querySelector('#engineering').value.trim()
      requestData.start_date = util().toY4(document.querySelector('#startDate input').value)
      requestData.end_date = util().toY4(document.querySelector('#endDate input').value)
    }

    function checkInput(requestData) {
      if (requestData.start_date === "1911" ) {
        notificationService.danger("時間區間不可留空。")
        return false
      }
      
      if (requestData.end_date === "1911") {
        notificationService.danger("時間區間不可留空。")
        return false
      }
      return true
    }

    function query0301() {
      getRequestData()
      if(!checkInput(requestData)) { return }
      axios
        .get("/service/MRP0301R/queryMrpResult", { params: requestData })
        .then(({ data }) => { 
          table.data = data.response
          table.data.length = data.response.length
        })
        .catch(error => { console.log("error : ", error) })
        .finally(() => {
          document.querySelector("#resultTable").classList.remove("hidden")
          setDatepicker(util().toY3(requestData.start_date), util().toY3(requestData.end_date))
        }) 
    }

    function resetQuery() {
      setDatepicker(defaultDate.startDate, defaultDate.endDate)
      document.querySelector('#ORG').value = ''
      document.querySelector('#engineering').value = ''
    }

    /* 查看 */
    function showModalContents(data) {
      console.log("showModalContents() : ", data)

      // axios
      //   .get("/service/MRP0301R/queryMrpResultDt", { params: requestData })
      //   .then(({ data }) => { console.log("response data : ", data) })
      //   .catch(error => { console.log(error) })
      //   .finally(() => {
          document.querySelector("#modal-table").innerHTML = `
            <table class="col-12">
              <thead>
                <tr>
                  <th class="col-4">標案案號</th>
                  <th class="col-4">狀態</th>
                  <th class="col-4">時間</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>${data.engineering_code}</td>
                  <td>${data.status}</td>
                  <td>${data.create_date}</td>
                </tr>
              </tbody>
            </table>
          `
      //   })
      // } 
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
        .get("/service/MRP0301R/queryMrpResult", { params: requestData })
        .then(({ data }) => {
          table.data = data.response.slice((form.currentPage * form.perPage), data.response.length);
        })
        .finally()
    };

    onMounted(() => {
      getDefaultDate()
    });

    return {
      table,
      query0301,
      resetQuery,
      setDatepicker,
      showModalContents,
      validateState,
      handleQuery,
      handlePaginationChanged,
    };

  }
}
</script>

<style scoped>
@import 'mrp.css';
</style>
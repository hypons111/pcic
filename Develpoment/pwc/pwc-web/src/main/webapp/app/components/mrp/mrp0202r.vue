<template>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2 mb-2">
      <div class="h4 m-0 py-2 button-border">早期價格查詢</div>
      <p>第1~54期公共工程價格資料庫 (提供93年~105年4月間決標之工程標案單價)</p>
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

        <div class="card-body ">

            <div class="flex card-row">
              <div class="flex item">
                <label>工項類別</label>
                <select id="type" name="type">
                  <option class="dropdown-item" value="LEM_">工項</option>
                  <option class="dropdown-item" value="M">材料(M)</option>
                  <option class="dropdown-item" value="L">勞務(L)</option>
                  <option class="dropdown-item" value="E">機具(E)</option>
                </select>
              </div>
              
              <div class="flex item">
                <label>工項編碼</label>
                <input id="code" class="form-control"></input>
                <div class="block"></div>
              </div>
            </div>

            <div class="flex card-row">
              <div class="flex item double">
                <label id="" class="">工項名稱的<br>關鍵字</label>
                <input id="keyword_1" class="form-control"></input>
                <select id="operator_1" class="input-group-text">
                  <option value="AND">AND</option>
                  <option value="OR">OR</option>
                  <option value="AND NOT">NOT</option>
                </select>
                <input id="keyword_2" class="form-control"></input>
                <select id="operator_2" class="input-group-text">
                  <option value="AND">AND</option>
                  <option value="OR">OR</option>
                  <option value="AND NOT">NOT</option>
                </select>
                <input id="keyword_3" class="form-control"></input>
                <div class="block"></div>
              </div>
            </div>

            <div class="flex card-row">
              <div class="flex item">
                <label>公佈期數</label>
                <select id="period" name="value"></select>
              </div>
            
              <div class="flex item">
                <label>區域別</label>
                <select id="area" name="area">
                  <option class="dropdown-item" value="北部">北部</option>
                  <option class="dropdown-item" value="中部">中部</option>
                  <option class="dropdown-item" value="東部">東部</option>
                  <option class="dropdown-item" value="南部">南部</option>
                  <option class="dropdown-item" value="全部">全部</option>
                </select>
              </div>
            </div>
            
            <div class="flex card-row">
              <div class="flex item double">
                <p class="font_red">※編碼或關鍵字第一欄至少要有1個輸入的查詢條件大於2個字元</p>
                <div class="block"></div>
              </div>
            </div>

            <div class="buttonGroup flex card-row">
              <button class="btn btn-info" data-toggle="modal" data-target="#exampleModal" @click="query0202()">查詢</button>
              <button class="btn btn-info" @click="resetQuery()">清除</button>
            </div>

            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    該價格資訊僅做為編列預算之參考，於實際編列預算時，應視使用目的，並考量工程規模、性質、施工規範內容、施工品質要求、施工地點差異及工期長短等因素，配合工程專業判斷，予以調整，以符實際個案需求。
                  </div>
                </div>
              </div>
            </div>
        </div>
      </div>
    </section>

    <!--    查詢結果-->
    <section id="resultTable" class="container mt-4 mb-2 hidden">
      <mrp-table
        ref="iTable"
        class="table-sm"
        :page="'mrp0202r'"
        :hideNo="true"
        :footerDownloadButton="true"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged($event)"
      >
        <template #cell(button_1)="row">
          <button id="detailButton" class="mt-1 ml-1 btn btn-info"  @click="showDetail(row.item)">細節</button>
        </template>
      </mrp-table>
    </section>

    <!-- 細節 --> 
    <section id="detailTable" class="container mt-4 hidden">
      <div class="card mt-4">
        <div class="card-body">
          <div class="flex">
            <div class="flex item">
              <label style=" width: 70px">期數</label>
              <p id="detailPeriod"></p>
            </div>

            <div class="flex item">
              <label style="width: 70px">工項代碼</label>
              <p id="detailCode"></p>
            </div>
          </div>
          
          <div class="flex">
            <div class="flex item ">
              <label style="width: 70px">工項名稱</label>
              <p id="detailCodeName"></p>
            </div>

            <div class="flex item">
              <label style="width: 70px">單位</label>
              <p id="detailUnit"></p>
            </div>
          </div>

          <div id="" class="flex card-row">
            <table id="A_Table" class="table b-table table-hover table-bordered">
              <thead>
                <tr>
                  <th rowspan="2" class="text-center align-middle">
                    <div class="text-center align-middle">區域別</div>
                  </th>
                  <th colspan="3" class="text-center align-middle">
                    <div class="text-center align-middle">北部</div>
                  </th>
                  <th colspan="3" class="text-center align-middle">
                    <div class="text-center align-middle">中部</div>
                  </th>
                  <th colspan="3" class="text-center align-middle">
                    <div class="text-center align-middle">南部</div>
                  </th>
                  <th colspan="3" class="text-center align-middle">
                    <div class="text-center align-middle">東部</div>
                  </th>
                </tr>
                <tr>
                  <th class="text-center align-middle" style="width: 106px">
                    <div class="text-center align-middle">上限</div>
                  </th>
                  <th class="text-center align-middle" style="width: 106px">
                    <div class="text-center align-middle">平均</div>
                  </th>
                  <th class="text-center align-middle" style="width: 106px">
                    <div class="text-center align-middle">下限</div>
                  </th>
                  <th class="text-center align-middle" style="width: 106px">
                    <div class="text-center align-middle">上限</div>
                  </th>
                  <th class="text-center align-middle" style="width: 106px">
                    <div class="text-center align-middle">平均</div>
                  </th>
                  <th class="text-center align-middle" style="width: 106px">
                    <div class="text-center align-middle">下限</div>
                  </th>
                  <th class="text-center align-middle" style="width: 106px">
                    <div class="text-center align-middle">上限</div>
                  </th>
                  <th class="text-center align-middle" style="width: 106px">
                    <div class="text-center align-middle">平均</div>
                  </th>
                  <th class="text-center align-middle" style="width: 106px">
                    <div class="text-center align-middle">下限</div>
                  </th>
                  <th class="text-center align-middle" style="width: 106px">
                    <div class="text-center align-middle">上限</div>
                  </th>
                  <th class="text-center align-middle" style="width: 106px">
                    <div class="text-center align-middle">平均</div>
                  </th>
                  <th class="text-center align-middle" style="width: 106px">
                    <div class="text-center align-middle">下限</div>
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="text-center align-middle">價格</td>
                  <td id="northUpper" class="text-center align-middle detailValue"></td>
                  <td id="northAvg" class="text-center align-middle detailValue"></td>
                  <td id="northLower" class="text-center align-middle detailValue"></td>

                  <td id="middleUpper" class="text-center align-middle detailValue"></td>
                  <td id="middleAvg" class="text-center align-middle detailValue"></td>
                  <td id="middleLower" class="text-center align-middle detailValue"></td>

                  <td id="southUpper" class="text-center align-middle detailValue"></td>
                  <td id="southAvg" class="text-center align-middle detailValue"></td>
                  <td id="southLower" class="text-center align-middle detailValue"></td>
                  
                  <td id="eastUpper" class="text-center align-middle detailValue"></td>
                  <td id="eastAvg" class="text-center align-middle detailValue"></td>
                  <td id="eastLower" class="text-center align-middle detailValue"></td>
                </tr>
                <tr>
                  <td class="text-center align-middle">備註</td>
                  <td colspan="12" class="text-center align-middle">價格為標案資料統計分析，本項目及價格僅供參考，不作為查估標準及大量使用之依據，請詳閱「公共工程價格資料庫」使用說明。</td>
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
import { validateState } from '@/shared/form';
import { Pagination } from '@/shared/model/pagination.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { onMounted, reactive } from '@vue/composition-api';
import axios from 'axios';
import getIssue from '../../../../../mrp_issue';
import mrpTable from '@/shared/i-table/mrp-table.vue';

export default {
  name: "mrp0202r",
  components: { mrpTable },
  setup() {
    const notificationService: NotificationService = useNotification();
    const requestData = {}
    const filteredData = []
    const table = reactive({
      fields: [
        {
          key: 'button_1',
          label: '挑選',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'period',
          label: '期數',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'code',
          label: '工項編碼',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'code_name',
          label: '工項名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'unit',
          label: '單位',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'area',
          label: '區域別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'avg_price',
          label: '平均價格',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'sample',
          label: '樣本數',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        }
      ],
      data: [],
      totalItems: undefined,
    });
    let currentPage = 0; // 後端分頁用
    let totalPate = 0; // 後端分頁用

    // 公佈期數
    function getPeriodMenu() {
      getIssue().forEach(value => {
        document.querySelector("#period").innerHTML += `<option class="dropdown-item" value="${value.issue}">${value.name}</option>`;
      });
    }

    function getRequestData() {
      requestData.code_type = document.querySelector("#type").value
      requestData.code = document.querySelector("#code").value.trim()
      requestData.name1 = document.querySelector("#keyword_1").value.trim()
      requestData.cond1 = document.querySelector("#operator_1").value
      requestData.name2 = document.querySelector("#keyword_2").value.trim()
      requestData.cond2 = document.querySelector("#operator_2").value
      requestData.name3 = document.querySelector("#keyword_3").value.trim()
      requestData.period = document.querySelector("#period").value
      requestData.area = document.querySelector("#area").value
    }

    function checkInput(requestData) {
      if ((requestData.code === "" || requestData.code.length < 2) &&
        (requestData.name1 === "" || requestData.name1.length < 2) ) {
        notificationService.danger("編碼或關鍵字第一欄至少要有1個輸入的查詢條件大於2個字元。")
        return false
      }
      return true
    }

    function query0202() {
      document.querySelector("#detailTable").classList.add("hidden") 
      clearDetail()
      
      getRequestData()
      if(!checkInput(requestData)) { return }
      console.log("requestData : ", requestData)

      axios
        .get("/service/MRP0202R/queryConPrice", { params: requestData })
        .then(({ data }) => {
          console.log('response data : ', data);
          this.table.data = data.response
          this.table.totalItems = data.response.length
        })
        .catch(error => { console.log("error : ", error) })
        .finally(() => { document.querySelector("#resultTable").classList.remove("hidden") })
    }

    function resetQuery() {
      document.querySelector("#type").value = "A"
      document.querySelector("#code").value = ""
      document.querySelector("#keyword_1").value = ""
      document.querySelector("#keyword_2").value = ""
      document.querySelector("#keyword_3").value = ""
      document.querySelector("#operator_1").value = "and"
      document.querySelector("#operator_2").value = "and"
      document.querySelector("#period").value = "第四十五期"
      document.querySelector("#area").value = "北部"
    }

    function clearDetail() {
      document.querySelectorAll(".detailValue").forEach(item => {
        item.value = ""
      })
    }

    function showDetail(data) {
      console.log("data : ", data)

      document.querySelector("#detailPeriod").innerText = data.period
      document.querySelector("#detailCode").innerText = data.code
      document.querySelector("#detailCodeName").innerText = data.code_name
      document.querySelector("#detailUnit").innerText = data.unit

      axios.
        get("/service/MRP0202R/queryConPriceDetail", { params: data })
        .then(({ data }) => {
          if(data.response.北部) {
            document.querySelector("#northUpper").innerText = data.response.北部.upper_price
            document.querySelector("#northAvg").innerText = data.response.北部.avg_price
            document.querySelector("#northLower").innerText = data.response.北部.lower_price
          }
          if(data.response.中部) {
            document.querySelector("#middleUpper").innerText = data.response.中部.upper_price
            document.querySelector("#middleAvg").innerText = data.response.中部.avg_price
            document.querySelector("#middleLower").innerText = data.response.中部.lower_price
          }
          if(data.response.南部) {
            document.querySelector("#southUpper").innerText = data.response.南部.upper_price
            document.querySelector("#southAvg").innerText = data.response.南部.avg_price
            document.querySelector("#southLower").innerText = data.response.南部.lower_price
          }
          if(data.response.東部) {
            document.querySelector("#eastUpper").innerText = data.response.東部.upper_price
            document.querySelector("#eastAvg").innerText = data.response.東部.avg_price
            document.querySelector("#eastLower").innerText = data.response.東部.lower_price
          }
        })
        .catch(error => { console.log("error : ", error) })
        .finally(() => {
          document.querySelector("#detailTable").classList.remove("hidden") 
          window.setTimeout(() => { document.querySelector('html').scrollTop = (document.querySelector('html').clientHeight*2) }, 0)
        }) 
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
        .get("/service/MRP0202R/queryConPrice", { params: requestData })
        .then(({ data }) => {
          table.data = data.response.slice((form.currentPage * form.perPage), data.response.length);
        })
        .finally()
    };

    onMounted(() => {
      getPeriodMenu()
    });

    return {
      table,
      getPeriodMenu,
      query0202,
      showDetail,
      resetQuery,
      validateState,
      handleQuery,
      handlePaginationChanged,
    };
  },
};
</script>
<style scoped>
  @import 'mrp.css';

  #detailTable>div>div>div>div:nth-child(1) {
    width: 70%;
    /* border: 1px solid red; */
  }
  #detailTable>div>div>div>div:nth-child(2) {
    width: 30%;
    /* border: 1px solid green; */
  }
</style>

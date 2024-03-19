<template>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2 mb-2">
      <div class="h4 m-0 py-2 button-border">解析前原始工項查詢</div>
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
            <label id="" class="wider">工程地區</label>
              <select name="city" id="city"></select>
              <div class="block"></div>
            </div>

            <div class="flex item ">
              <label id="" class="">工程規模</label>
              <select name="price" id="priceType"></select>
            </div>
          </div>

          <div class="flex card-row">
            <div class="flex item">
              <label class="wider"><p class="font_red">*</p>決標日(起迄)</label>
              <i-date-picker id="startDate" class="form-control"></i-date-picker>
              <b-input-group-text>至</b-input-group-text>
              <i-date-picker id="endDate" class="form-control"></i-date-picker>
              <div class="block"></div>
            </div>

            <div class="flex item ">
              <label id="">工程類型</label>
              <select name="type" id="type"></select>
            </div>
          </div>

          <div class="flex card-row">
            <div class="flex item">
              <label class="wider"><p class="font_red">*</p>關鍵字或編碼
                <img id='openExclamationMarkInstruction' src="./image/exclaim_icon.png" alt="" width="20" height="20"></img>
                <div id='exclamationMarkInstruction' class='card hidden popup'>
                  <p>【請參考工程會頒布的 01-16 篇細目編碼的工項名稱關鍵字或編碼關鍵字查詢，同一工項可輸入多組空白隔開的關鍵字，如欲查詢M0305046203產品，預拌混凝土材料費，280kgf/cm2，第2型水泥，關鍵字可輸入：混凝土280 2型。】</p>
                </div> 
              </label>
              <input id="keyword"  class="form-control"> </input>
              <div class="block"></div>
            </div>
          </div>

          <div class="buttonGroup flex card-row">
              <button class="btn btn-info" @click="query0109()">查詢</button>
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
        :page="'mrp0109r'"
        :downloadButton="true"
        :hideNo="true"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged($event)"
      >
        <template #cell(a)="row">
          <div>
            <button class="btn btn-info" data-toggle="modal" data-target="#exampleModal"
              @click="showModal(row.item)">檢視</button>
          </div>

          <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
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
                            <td class="header headerOnLeft">原工項名稱</td>
                            <td id="modalProjectName" class="bodyOnLeft"></td>
                            <td class="header headerOnRight">價錢類型</td>
                            <td id="modalPriceType" class="bodyOnRight"></td>
                          </tr>
                          <tr>
                            <td class="header">單位</td>
                            <td id="unit" class="bodyOnLeft"></td>
                            <td class="header">所屬區域</td>
                            <td id="cityName" class="bodyOnRight"></td>
                          </tr>
                          <tr>
                            <td class="header">原編碼</td>
                            <td id="code" class="bodyOnLeft" colspan="3"></td>
                          </tr>
                        </tbody>
                      </table>
                    </div>

                    <div id="modal-table_2" class="row mt-4">
                      <table>
                        <thead>
                          <tr>
                            <td class="header">決標月份</td>
                            <td class="header">原描述</td>
                            <td class="header">機關別</td>
                            <td class="header">工程名稱</td>
                            <td class="header">價格</td>
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
import iDatePicker from '@/shared/i-date-picker/i-date-picker.vue';
import mrpTable from '@/shared/i-table/mrp-table.vue';
import { Pagination } from '@/shared/model/pagination.model';
import { IUser } from '@/shared/model/user.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { useGetters } from '@u3u/vue-hooks';
import { onMounted, reactive, Ref } from '@vue/composition-api';
import axios from 'axios';
import { BInputGroupText } from 'bootstrap-vue';
import util from '../../../../../mrp_util';

export default {
  name: "mrp0109r",
  components: { mrpTable, iDatePicker, BInputGroupText },
  setup() {
    const notificationService: NotificationService = useNotification();

    const account : Ref<IUser> = useGetters(['account']).account;
    const account_id = "00000000-0000-0000-0000-000000000000"
    const create_id = "TEST"
    // const account_id = account.value.userId
    // const create_id = account.value.chName
    const defaultDate = {};
    const requestData = {};
    let xlsFileName = "";
    let currentPage = 0; // 後端分頁用
    let totalPate = 0; // 後端分頁用
    const table = reactive({
      fields: [
        {
          key: 'date',
          label: '決標月份',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'description',
          label: '原描述',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'code',
          label: '原編碼',
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
        },
        {
          key: 'cityName',
          label: '所屬區域',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'avgPrice',
          label: '平均價格',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'priceType',
          label: '價格類型',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => {
            if (value === "2") {
              return "預算價"
            } else if (value === "5") {
              return "決標價"
            }
          }
        },
        {
          key: 'sample',
          label: '樣本數量',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'a',
          label: '詳細資料',
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
    }

    function getCityMenu() {
      axios
        .get("/service/MRP0201R/queryMrpCity")
        .then(({ data }) => {
          let innerHTMLstring = ""
          data.response.forEach(city => {
            if(!city.id ) {
              switch(city.text) {
                case "北部地區": city.id = "北部地區"
                  break
                case "中部地區": city.id = "中部地區"
                  break
                case "南部地區": city.id = "南部地區"
                  break
                case "東部地區": city.id = "東部地區"
                  break
              }
            }
            innerHTMLstring += `<option class="dropdown-item" value="${city.id}">${city.text}</option>`;
          })
          document.querySelector("#city").innerHTML = innerHTMLstring
        })
    }

    function getProjectScaleMenu() {
      axios
        .get("/service/MRP0201R/queryAdmSysCode")
        .then(({data}) => {
          const budgetList = data.response.filter(item => item.VALUE.includes("budget"))
          const winList = data.response.filter(item => item.VALUE.includes("win"))
          let dropdownString = ""
          budgetList.forEach(item => {
            if (item.VALUE === "budget0") {
              dropdownString += `<optgroup label=${item.DATA_KEY}>`;
            } else {
              dropdownString += `<option class="dropdown-item" value="${item.VALUE}">${item.DATA_KEY}</option>`;
            }
          });
          winList.forEach(item => {
            if (item.VALUE === "win0") {
              dropdownString += `</optgroup><optgroup label=${item.DATA_KEY}>`;
            } else {
              dropdownString += `<option class="dropdown-item" value="${item.VALUE}">${item.DATA_KEY}</option>`;
            }
          });
          dropdownString += "</optgroup>"
          document.querySelector("#priceType").innerHTML = dropdownString
        })
    }

    function getProjectTypeMenu() {
      axios
        .get("/service/MRP0201R/queryItemCode")
        .then(({ data }) => {
          data.response.forEach(type => {
            document.querySelector("#type").innerHTML += `<option class="dropdown-item" value="${type.code}">${type.name}</option>`;
          });
        })
    }

    function getDefaultDate() {
      const d = new Date()
      const startDateString = util().toY3(`${d.getFullYear()-3}-${d.getMonth()+1}-${d.getDate()}`)
      const endDateString = util().toY3(`${d.getFullYear()}-${d.getMonth()+1}-${d.getDate()}`)
      setDatepicker(startDateString, endDateString)
    }

    function setDatepicker(s, e) {
      document.querySelector('#startDate input').value = s
      document.querySelector('#endDate input').value = e
    }

    function getRequestData() {
      requestData.ddl_city = document.querySelector("#city").value
      requestData.ddl_size = document.querySelector("#priceType").value
      requestData.ddl_type = document.querySelector("#type").value
      requestData.key_word = document.querySelector("#keyword").value.trim()
      requestData.startime = util().toY4(document.querySelector("#startDate input").value)
      requestData.endtime = util().toY4(document.querySelector("#endDate input").value)
    }

    function checkInput(requestData) {
      if (requestData.startime === "1911" || requestData.endtime === "1911") {
        notificationService.danger("時間區間不可留空。")
        return false
      }
      if (util().compareDates("2015/05/01", requestData.startime, "/")) {
        notificationService.danger("起日不得小於104-05-01。")
        return false
      }
      if (util().compareDates(requestData.startime, requestData.endtime, "/")) {
        notificationService.danger("迄日不得小於起日。")
        return false
      }
      if(requestData.key_word === '') {
        notificationService.danger("關鍵字不可留空。")
        return false
      }
      return true
    }

    function query0109() {
      getRequestData()
      if(!checkInput(requestData)) { return; }
      console.log("request data : ", requestData);
      axios
        .get('/service/MRP0109R/queryBasic', { params: requestData })
        .then(({ data }) => {
          xlsFileName = data.filename;
          table.data = data.response;
          table.totalItems = data.response.length;
        })
        .catch(error => { console.log("error : ", error) })
        .finally(() => {
          document.querySelector("#resultTable").classList.remove("hidden");
          setDatepicker(util().toY3(requestData.startime), util().toY3(requestData.endtime));
        }) 
    }

    function showModal(data) {
      console.log("data : ", data)
      document.querySelector("#modalProjectName").innerText = data.description
      document.querySelector("#modalPriceType").innerText = data.priceType === "2" ? "預算價格" : "決標價格"
      document.querySelector("#unit").innerText = data.unit
      document.querySelector("#cityName").innerText = data.cityName
      document.querySelector("#code").innerText = data.code

      let dataString = ""
      data.detail.forEach(item => {
        dataString += `
          <tr>
            <td class="align-middle">${item.detail_date}</td>
            <td class="align-middle">${item.detail_description}</td>
            <td class="align-middle">${item.detail_department}</td>
            <td class="align-middle">${item.detail_engineer}</td>
            <td class="align-middle">${item.detail_price}</td>
          </tr>`
          // style="text-align: justify; padding: 0px 10px;"
      })
      document.querySelector("#modal-table_2_tbody").innerHTML = dataString
    }

    function resetQuery() {
      document.querySelector("#city").value = ""
      document.querySelector("#priceType").value = "budget1"
      setDatepicker(defaultDate.startDate, defaultDate.endDate)
      document.querySelector("#type").value = ""
      document.querySelector("#keyword").value = ""
    }

    function download() {
      const requestData = {
        fileName : xlsFileName,
        acc : account_id,
        ip : create_id
      };
      axios
        .get('/service/MRP0109R/downloadExcel', {
          params : requestData,
          responseType: 'blob'
        })
        .then(response => {
          const href = URL.createObjectURL(response.data);
          const link = document.createElement('a');
          link.href = href;
          link.setAttribute('download', xlsFileName);
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
          URL.revokeObjectURL(href);
        });
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
        .get('/service/MRP0109R/queryBasic', { params: requestData })
        .then(({ data }) => {
          table.data = data.response.slice((form.currentPage * form.perPage), data.response.length);
        })
        .finally()
    };

    onMounted(() => {
      getCityMenu()
      getDefaultDate()
      getProjectScaleMenu()
      getProjectTypeMenu()
      addListener()
    });

    return {
      table,
      getProjectScaleMenu,
      getProjectTypeMenu,
      setDatepicker,
      query0109,
      showModal,
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
  @import 'viewModal.css';
</style>
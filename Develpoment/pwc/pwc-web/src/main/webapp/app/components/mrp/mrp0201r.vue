<template>
  <div>
    <!-- 查詢條件 -->
    <section id="querySection" class="container mt-2 mb-2">
      <div class="h4 m-0 py-2 button-border">價格查詢</div>
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
          <div id="projectLocationGroup" class="flex card-row">
              <div class="flex item">
                <label id="" class="wide">工程縣市</label>
                <select name="city" id="city"></select>
                <div class="block"></div>
              </div>

              <div class="flex item">
                <label id="" class="">工程鄉鎮</label>
                <select name="town" id="town">
                  <option disabled selected value>請選擇</option>
                </select>
              </div>
          </div>

          <div id="secondRowGroup" class="flex card-row">
              <div class="flex item">
                <label id="" class="wide">工程規模</label>
                <select name="priceType" id="priceType"></select>
                <div class="block"></div>
              </div>

              <div class="flex item">
                <label id="" class="">工程類型</label>
                <select name="type" id="type"></select>
              </div>
          </div>

          <div id="thirdRowGroup" class="flex card-row">
              <div class="flex item">
                <label class="wide">
                  關鍵字或編碼
                  <img id='openExclamationMarkInstruction' src="./image/exclaim_icon.png" alt="" width="20" height="20"></img>
                  <div id='exclamationMarkInstruction' class='card hidden popup'>
                    <p>【請參考工程會頒布的 01-16 篇細目編碼的工項名稱關鍵字或編碼關鍵字查詢，同一工項可輸入多組空白隔開的關鍵字，如欲查詢M0305046203產品，預拌混凝土材料費，280kgf/cm2，第2型水泥，關鍵字可輸入：混凝土280 2型。】</p>
                  </div>
                </label>
                <input id="keyword"  class="form-control"> </input>
                <div class="block"></div>
              </div>

              <div class="flex item">
                <label>決標日(起迄)</label>
                <mrpDatePicker id="startDate" class="form-control"></mrpDatePicker>
                <b-input-group-text>至</b-input-group-text>
                <mrpDatePicker id="endDate" class="form-control"></mrpDatePicker>
                <div class="block"></div>
              </div>
          </div>

          <div id="queryUpdataDateGroup" class="flex card-row">
              <div class="flex item ">
                <label id="" class="wide">回查更新日期</label>
                <select name="date" id="date"></select>
                <div class="block"></div>
              </div>
          </div>

          <div id="infomationGroup" class="flex card-row">
              <div class=" item">
                <p id="update_date"></p>
              </div>
              <div class=" item ">
                <p id="data_range"></p>
              </div>
          </div>
              
          <div class="flex card-row mrp0201ButtonGroup">
            <button class="btn btn-info" @click="query0201()">查詢</button>
            <button class="btn btn-info" @click="resetQuery()">清除</button>
          </div>

          <div id="collectionListGroup" class="flex card-row mrp0201ButtonGroup"></div>
        
        </div>
      </div>
    </section>

    <!-- 查詢結果-->
    <section id="resultSection" class="container mt-4 mb-2 hidden">
      <MRP0201
        ref="iTable"
        class="table-sm"
       :page="'mrp0201r'"
       :showFilterIcon="false"
       :hideNo="true"
       :itemsUndefinedBehavior="'loading'"
       :items="table.data"
       :fields="table.fields"
       :is-server-side-paging="true"
       :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged($event, requestData)"
      >
        <template v-slot:head(err_price)="data">
          <div>標準差 <img id="popupIcon" src="./image/exclaim_icon.png" width="20" height="20" style="cursor:pointer;"></img></div>
        </template>

        <template #cell(errorReport)="row">
          <div><button
              class="btn btn-info"
              data-toggle="modal"
              data-target="#exampleModal"
              @click="showErrorModal(row.item)">異常回報</button>
          </div>
        </template>
        
        <template #cell(addToCollection)="row">
          <div><button class="btn btn-info" @click="addCollectionItem(row.item)">加入</button></div>
        </template>
      </MRP0201>
    </section>
    
    <!-- 異常回報 modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">異常回報</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div id="modal-body" class="">
            <div id="modal-body-detail"></div>
            <div class="inputWrapper">
              <div class="inputP">
                <p class="font_red">*</p>
                <p>價格</p>
              </div>
              <input type="text" id="price" name="price" placeholder="請輸入價格">
            </div>

            <div class="inputWrapper">
              <div class="inputP">
                <p>採購量</p>
              </div>
              <input type="text" id="purchase_quantity" name="quantity" placeholder="請輸入採購量">
            </div>

            <div class="inputWrapper">
              <div class="inputP">
                <p class="font_red">*</p>
                <p>姓名</p>
              </div>
              <input type="text" id="person_name" name="name" placeholder="聮絡人姓名">
            </div>

            <div class="inputWrapper">
              <div class="inputP">
                <p class="font_red">*</p>
                <p>電話</p>
              </div>
              <input type="text" id="persontel" name="phone" placeholder="聮絡人電話">
            </div>

            <div class="inputWrapper">
              <div class="inputP">
                <p class="font_red">*</p>
                <p>信箱</p>
              </div>
              <input type="text" id="person_mail" name="email" placeholder="聮絡人信箱">
            </div>
                    
            <div class="textareaWrapper">
              <div id="textareaLabel" class="text-left">
                <p class="font_red">*</p>
                <p>說明</p>
              </div>
              <textarea id="wrong_return_description" name="description" rows="5" cols="70" placeholder="請填說明"></textarea>
            </div>

            <div class="">
              <button class="btn btn-info" v-on:click="errorSubmit()">送出</button>
              <button id="closeModal" class="btn btn-info" v-on:click="errorCancel()">取消</button>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script lang="ts">
import { validateState } from '@/shared/form';
import mrpDatePicker from '@/shared/i-date-picker/mrp0201-date-picker.vue';
import MRP0201 from '@/shared/i-table/mrp0101_mrp0201-table.vue';
import { Pagination } from '@/shared/model/pagination.model';
import { IUser } from '@/shared/model/user.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { useGetters } from '@u3u/vue-hooks';
import { onMounted, reactive, Ref } from '@vue/composition-api';
import axios from 'axios';
import { BInputGroupText } from 'bootstrap-vue';
import Highcharts from 'highcharts';
import Exporting from 'highcharts/modules/exporting';
import util from '../../../../../mrp_util';

export default {
  name : "mrp0101r", 
  components : {MRP0201, mrpDatePicker, BInputGroupText },
  props: {
    addORedit: {
      type: String,
      default: "",
    }
  },
  setup(props) {
    const notificationService: NotificationService = useNotification();
    const account : Ref<IUser> = useGetters(['account']).account;
    const account_id = "00000000-0000-0000-0000-000000000000"
    const create_id = "TEST"
    const sourceIp = "0000000000"
    // const account_id = account.value.userId
    // const create_id = account.value.chName
    const defaultDate = {};
    const requestData = {}; 
    const errorRequestData = {};
    const basicQueryResult = [];
    const oldBtnName = "";
    let downloadFileName = "";
    const table = reactive({
      fields : [
        {
          key : 'name',
          label : '工項名稱',
          sortable : false,
          thClass : 'text-center',
          tdClass : 'text-center align-middle'
        },
        {
          key : 'code',
          label : '工項編碼',
          sortable : false,
          thClass : 'text-center',
          tdClass : 'text-center align-middle'
        },
        {
          key : 'unit',
          label : '單位',
          sortable : false,
          thClass : 'text-center',
          tdClass : 'text-center align-middle'
        },
        {
          key : 'city',
          label : '所屬區域',
          sortable : false,
          thClass : 'text-center',
          tdClass : 'text-center align-middle'
        },
        {
          key : 'pricetype',
          label : '價格類型',
          sortable : false,
          thClass : 'text-center',
          tdClass : 'text-center align-middle',
          formatter : (value : string) => {
            if (value === "2") { return "預算價格" } 
            else if (value === "5") { return "決標價格" }
          }
        },
        {
          key : 'price',
          label : '平均價格(元)',
          sortable : false,
          thClass : 'text-center',
          tdClass : 'text-center align-middle'
        },
        {
          key : 'err_price',
          label : '標準差',
          sortable : false,
          thClass : 'text-center err_price',
          tdClass : 'text-center align-middle'
        },
        {
          key : 'num',
          label : '樣本數量',
          sortable : false,
          thClass : 'text-center',
          tdClass : 'text-center align-middle'
        },
        {
          key : 'errorReport',
          label : '異常回報',
          sortable : false,
          thClass : 'text-center',
          tdClass : 'text-center align-middle'
        }
      ],
      data : [],
      totalItems : undefined,
    });
    let currentBadget = "";
    let currentPage = 0; // 後端分頁用
    let totalPate = 0; // 後端分頁用
    let isSDListener = false;


    function addAreaListener() {
      document.querySelector("#resultSection").addEventListener("click", e => {
        if(e.target.classList.contains("bluePin")) {
          setRedPin(e.target)
          queryByArea(e.target)
        }
      })
    }

    function addBadgeListener() {
      const badgeList = document.querySelectorAll('#badgeGroup button');
      badgeList.forEach(badge => {
        badge.addEventListener('click', e => {
          sectionController(e.target);
        });
      });
    }



    function addCityListener() {
      document.querySelector("#city").addEventListener("change", (e) => {
        document.querySelector("#town").innerHTML = "<option disabled selected value>請選擇</option>";
        const idArray = e.target.value
        if(idArray[0] !== "" && !idArray.includes(",")) {
          getTownList(idArray.split("^")[0])
        }
      })
    }

    function addSDListener() {
      isSDListener = true
      document.querySelectorAll("#popupIcon").forEach(icon => {
        icon.addEventListener('mouseenter', () => {
          document.querySelector("#sdPopupModal").classList.add("popup")
          document.querySelector("#sdPopupModal").classList.remove("hidden")
        })
      })
      document.querySelectorAll("#sdPopupModal").forEach(icon => {
        icon.addEventListener('mouseleave', () => {
          document.querySelector("#sdPopupModal").classList.remove("popup")
          document.querySelector("#sdPopupModal").classList.add("hidden")
        })
      })
    }

    /* get default data */
    function getCityList() {
      let innerHTMLstring = ""
      axios
        .get("/service/MRP0201R/queryMrpCity")
        .then(({ data }) => {
          data.response.forEach(city => {
            if(city.id !== "27" && city.id !== "26") {
              innerHTMLstring += `<option class="dropdown-item" value="${city.id}^${city.text}">${city.text}</option>`;
            }
          })
        })
        .catch(error => { console.log("error : ", error) })
        .finally(() => { document.querySelector("#city").innerHTML = innerHTMLstring }) 
    }

    function getTownList(cityId) {
      axios
        .get("/service/MRP0201R/queryMrpTown")
        .then(({data}) => {
          data.response.forEach(town => {
            if(town.CITY_ID === cityId && town.CITY_ID !== "24") {
              document.querySelector("#town").innerHTML += `<option class="dropdown-item" value="${town.NAME}">${town.NAME}</option>`
            }
          });
        })
    }

    function getProjectScaleList() {
      axios
        .get("/service/MRP0201R/queryAdmSysCode")
        .then(({data}) => {
          const budgetList = data.response.filter(item => item.VALUE.includes("budget"))
          const winList = data.response.filter(item => item.VALUE.includes("win"))
          let dropdownString = ""
          budgetList.forEach(item => {
            let key = item.DATA_KEY.trim()
            if (item.VALUE === "budget0") {
              dropdownString += `<optgroup label=${item.DATA_KEY}>`;
            } else {
              dropdownString += `<option class="dropdown-item" value="${item.VALUE}^${key}">${key}</option>`;
            }
          });
          winList.forEach(item => {
            if (item.VALUE === "win0") {
              dropdownString += `</optgroup><optgroup label=${item.DATA_KEY}>`;
            } else {
              dropdownString += `<option class="dropdown-item" value="${item.VALUE}^${item.DATA_KEY}">${item.DATA_KEY}</option>`;
            }
          });
          dropdownString += "</optgroup>"
          document.querySelector("#priceType").innerHTML = dropdownString
        })
    }

    function getProjectTypeList() {
      axios
        .get("/service/MRP0201R/queryItemCode")
        .then(({ data }) => {
          data.response.forEach(type => {
            document.querySelector("#type").innerHTML += `<option class="dropdown-item" value="${type.code}^${type.name}">${type.name}</option>`;
          });
        })
    }

    function getDateList() {
      axios
        .get("/service/MRP0201R/queryMainInfo")
        .then(({ data }) => {
          data.response[0].create_date_list.forEach(date => {
            document.querySelector("#date").innerHTML += `<option class="dropdown-item" value="${date}">${date}</option>`;
          })
          document.querySelector("#update_date").innerText = "資料庫更新日期：" + data.response[0].db_updated_date
          document.querySelector("#data_range").innerText = "目前收錄包含：" + data.response[0].data_range + "決標期間的資料"
        })
    }

    function getDefaultDate() {
      axios
        .get("/service/MRP0201R/queryMainInfo")
        .then(({ data }) => {
          const startDateString = data.response[0].budget_smonth
          const endDateString = data.response[0].budget_emonth
          setDatepicker(startDateString, endDateString)
          defaultDate.startDate = startDateString
          defaultDate.endDate = endDateString
        })
    }

    function setDatepicker(s, e) {
      document.querySelector('#startDate input').value = s.slice(0, 6)
      document.querySelector('#endDate input').value = e.slice(0, 6)
    }

    function resetQuery() {
      document.querySelector("#city").value = "^全選"
      document.querySelector("#town").innerHTML = "<option disabled selected value>請選擇</option>"
      document.querySelector("#priceType").value = "budget1^預算金額全選"
      document.querySelector("#type").value = "0^全選"
      document.querySelector("#keyword").value = ""
      setDatepicker(defaultDate.startDate, defaultDate.endDate)
      getDateList()
    }


    /* section controller */
    function sectionController(section) {
      if(currentBadget === section.id) { return } 
      else { currentBadget = section.id }
      document.querySelectorAll('.resultDiv').forEach(div => { div.classList.add('hidden'); });
      switch (section.id) {
        case "basicSearchButton" : 
          document.querySelector('#basicResult').classList.toggle("hidden")
          table.data = basicQueryResult
          table.totalItems = basicQueryResult.length
          break;
        case "areaSearchButton" : 
          document.querySelector('#areaResult').classList.toggle("hidden")
          setBluePin()
          break;
        case "treeSearchButton" : 
          document.querySelector('#treeResult').classList.toggle("hidden")
          showTreePanel()
          break;
        case "trendSearchButton" : 
          document.querySelector('#trendResult').classList.toggle("hidden")
          queryByTrend()
          break;
      }
    }

    function switchSection(status) {
      switch(status) {
        case 1:
          document.querySelector("#resultSection").classList.add("hidden")
          document.querySelector("#collectuionSection").classList.remove("hidden")
          return
        case 2:
          document.querySelector("#resultSection").classList.remove("hidden")
          document.querySelector("#collectuionSection").classList.add("hidden")
          return
        case 3:
          document.querySelector("#resultSection").classList.add("hidden")
          document.querySelector("#collectuionSection").classList.add("hidden")
          return
        case 4:
          document.querySelector("#resultSection").classList.remove("hidden")
          document.querySelector("#collectuionSection").classList.remove("hidden")
          return
      }
    }

    /* basic */
    function getRequestData() {
      requestData.city_id = document.querySelector("#city").value.split("^")[1].replace("全選", "")
      requestData.town_id = document.querySelector("#town").value
      requestData.pro_scale = document.querySelector("#priceType").value.split("^")[0]
      requestData.pro_type = document.querySelector("#type").value.split("^")[0].replace("0", "")
      requestData.key_word = document.querySelector("#keyword").value.trim()
      requestData.startmonth = util().toY4((document.querySelector("#startDate input").value) + "-01")
      requestData.endmonth = util().toY4(document.querySelector("#endDate input").value + "-01")
      requestData.account_id = account_id
      requestData.create_id = create_id
      requestData.mrpNo = "0201"

      /* 搜尋範圍使用 */
      requestData.pro_scale_string = document.querySelector("#priceType").value.split("^")[1]
      requestData.pro_type_string = document.querySelector("#type").value.split("^")[1]
    }

    function query0201() {
      getRequestData()
      setQueryInformation(requestData)
      axios
        .get("/service/MRP0201R/queryBasic", { params : requestData })
        .then(({ data }) => {
          table.data = data.response
          table.totalItems = data.response.length
          basicQueryResult = data.response
          downloadFileName = data.filename
        })
        .catch(error => { console.log(error) })
        .finally(() => {
          if(isSDListener === false) { addSDListener() }
          document.querySelector('#resultSection').classList.remove("hidden")
          sectionController({ id : "basicSearchButton" })
          window.setTimeout(() => { setDatepicker(util().toY3(requestData.startmonth), util().toY3(requestData.endmonth)) }, 0)
          resetCollectionData()
          switchSection(2)
        })
    }

    function setQueryInformation(data) {
      let keyword = data.key_word
      let city = data.city_id
      let town = data.town_id
      let area = (city + town) === "" ? "全選" : (`${city}" "${town}`)
      let scale = data.pro_scale_string
      let type = data.pro_type_string
      document.querySelector("#searchCondition").innerText = `搜尋範圍："${keyword}"， 縣巿："${area}"， 規模："${scale}"， 類型："${type}"`
    }


    /* map */
    function queryByArea(targetArea) {
      const mapRequestData = JSON.parse(JSON.stringify(requestData))
      mapRequestData.city_id = targetArea.dataset.name
      console.log("mapRequestData : ", mapRequestData)
      
      axios
        .get("/service/MRP0201R/queryBasic", { params : mapRequestData })
        .then(({ data }) => {
          console.log("map response : ", data)
          table.data = data.response
          table.totalItems = data.response.length
        })
        .catch(error => { console.log(error) })
        .finally(() => {
          setDatepicker(util().toY3(requestData.startmonth), util().toY3(requestData.endmonth))
        })
    }

    function setBluePin() {
      const selectedArea_array = document.querySelector("#city").value.split("^")[0].split(",")
      const area_array = document.querySelectorAll(".pin")

      area_array.forEach(area => {
        area.classList.remove("bluePin")
        area.classList.remove("redPin")
      })
      
      if(selectedArea_array.length === 1 && selectedArea_array[0] !== "") {
        area_array.forEach(area => {
          if(`area_${selectedArea_array}` === area.id) {
            area.classList.add("redPin")
          }
        })
      } else if(selectedArea_array.length > 1) {
        area_array.forEach(area => {
          if(selectedArea_array.includes(area.id.slice(5))) {
            area.classList.add("bluePin")
          }
        })
      } else {
        area_array.forEach(area => { area.classList.add("bluePin") })
      }
    }

    function setRedPin(target) {
      document.querySelectorAll(".bluePin").forEach(area => {
        if(area.id === target.id) { area.classList.add("redPin") }
        else { area.classList.remove("redPin") }
      })
    }

    /* tree */
    function showTreePanel() {
      const treeContainer = document.querySelector("#treeContainer")
      treeContainer.innerHTML = ""
      basicQueryResult.forEach(item => {
        const item_array = item.name.split("。")
        let htmlString = "" 

        for(let i=0; i<item_array.length; i++) {
          if(!treeContainer.innerHTML.includes(`id="${replaceCharater(item_array[i])}"`)) {
            if(item_array.length === 1 && i === 0) {
              htmlString += `<ul id="${replaceCharater(item_array[i])}" class="top hidable last_ul"><li id="${item.code}" class="last_li">${item_array[i]}</li>`
            } else if(i === 0) {
              // console.log("a : ", item_array[i])
              htmlString += `<ul id="${replaceCharater(item_array[i])}" class="top"><li class="closeFolder">${item_array[i]}</li>`
            } else if((i+1) === item_array.length) {
              // console.log("b : ", item_array[i])
              htmlString += `<ul id="${replaceCharater(item_array[i])}" class="hidable last_ul hidden"><li id="${item.code}" class="last_li">${item_array[i]}</li>`
            } else {
              // console.log("c : ", item_array[i])
              htmlString += `<ul id="${replaceCharater(item_array[i])}" class="hidable hidden"><li class="closeFolder">${item_array[i]}</li>`
            }
          } else {
            if(!treeContainer.innerHTML.includes(`id="${replaceCharater(item_array[i+1])}"`)) {
              let string = ""
              if((i+2) === item_array.length) {
                // console.log("d : ", item_array[i + 1])
                string += `<ul id="${replaceCharater(item_array[i+1])}" class="hidable last_ul hidden"><li id="${item.code}" class="last_li">${item_array[i + 1]}</li>`
              } else if(item_array[i + 1]){
                // console.log("e : ", item_array[i + 1])
                string += `<ul id="${replaceCharater(item_array[i+1])}" class="hidable hidden"><li class="closeFolder">${item_array[i + 1]}</li>`
              }
              document.getElementById(replaceCharater(item_array[i])).innerHTML += string
            }
          }
        }
        treeContainer.innerHTML += htmlString
      })
    }

    function addTreeListener() {
      const treeContainer = document.querySelector("#treeContainer")
      treeContainer.addEventListener("click", event => {
        
        let topNode = event.target
        while(!topNode.classList.contains("top")) {
          topNode = topNode.parentElement
        }

        document.querySelectorAll(".top").forEach(item => {
          if(item.id !== topNode.id) {
            item.children[0].classList.remove("openFolder")
            for(let i=0; i<item.children.length; i++) {
              if(item.children[i].classList.contains("hidable")) {
                item.children[i].classList.add("hidden")
              }
            }
          }
        })
        
        if(event.target.classList.contains("closeFolder")) {
          event.target.classList.toggle("openFolder")
          document.querySelectorAll(`#${replaceCharater(event.target.parentElement.id)}>ul`).forEach(item => {
            item.classList.toggle("hidden")
          })
        }

        // query by tree
        if(event.target.id !== "" && event.target.tagName === "LI") {
          queryByTree(event.target)
        }
      })
    }

    function replaceCharater(string) {
      if(string !== undefined) {
        let newString = "_" + string.replace(/[.*+?^${}()|[\]\\\/_]/g, '')
        return newString
      }
    }

    function queryByTree(target) {
      // basicQueryResult.forEach(item => {console.log(item)})

      const treeQueryResult = basicQueryResult.filter(data => data.code === target.id)
      table.data = treeQueryResult
      table.totalItems = treeQueryResult.length

      const treeResultTable_2 = document.querySelector("#treeResultTable_2")
      treeResultTable_2.classList.remove("hidden")
    }


    /* trend */
    function queryByTrend() {
      const categoryData = getMonthInterval(basicQueryResult[0].datestring, basicQueryResult[basicQueryResult.length-1].datestring)
      const nameCounter = []
      const seriesData =  []
      basicQueryResult.forEach(item => {
        if(!nameCounter.includes(item.name)) {
          nameCounter.push(item.name)
          const obj = {
            name : item.name,
            data : []
          }
          seriesData.push(obj)
        }
      })
      basicQueryResult.forEach(item => {
        seriesData[nameCounter.indexOf(item.name)].data.push(Number(item.price))
      })
      setHighcharts(seriesData, categoryData)
    }

    function setHighcharts(series, category) {
      Exporting(Highcharts);
      Highcharts.chart('highChartContainer', {
        title : { 
          text : ".",
          style : { color : 'none' }
        },

        legend : {
          align : 'left',
          verticalAlign : 'bottom'
        },

        xAxis : { 
          categories : category,
          tickWidth : 1,
          tickLength : 10,
          tickPosition : "inside",
          tickmarkPlacement : "on"
        },
        series : series,
        
        plotOptions : {
          series : {
            marker : {
              enabled : true,
              radius : 5
            }
          }
        },

      });
    }

    function getMonthInterval(startDate, endDate) {
      let startPart = startDate.split("/")
      let endPart = endDate.split("/")
      let year = startPart[0];
      let month;
      month = (Number(endPart[0]) - Number(startPart[0])) * 12;
      month -= Number(startPart[1]);
      month += Number(endPart[1]);
      let length = month <= 0 ? 0 : month
      let startWith = Number(startPart[1])
      let arr = []
        
      for(let i = 0; i <= length; i++) {
        if(startWith == 13) {
          startWith = 1
          year ++
        }
        arr.push(year + "-" + startWith)
        startWith ++
      }
      console.log("arr : ", arr)
      return arr
    }


    /* 異常回報 */
    function showErrorModal(data) {
      document.querySelector("#modal-body-detail").innerHTML = `
        <p id="errorName" class="text-left">工項名稱：${data.name}</p>
        <p id="errorCode" class="text-left">工項編碼：${data.code}</p>
        <p id="errorUnit" class="text-left">單位：${data.unit}</p>
      `
    }

    function getErrorRequestData() {
      errorRequestData.name = document.querySelector("#errorName").innerText.split("：")[1]
      errorRequestData.code = document.querySelector("#errorCode").innerText.split("：")[1]
      errorRequestData.unit = document.querySelector("#errorUnit").innerText.split("：")[1]
      errorRequestData.price = document.querySelector("#price").value.trim()
      errorRequestData.purchase_quantity = document.querySelector("#purchase_quantity").value.trim()
      errorRequestData.person_name = document.querySelector("#person_name").value.trim()
      errorRequestData.persontel = document.querySelector("#persontel").value.trim()
      errorRequestData.person_mail = document.querySelector("#person_mail").value.trim()
      errorRequestData.wrong_return_description = document.querySelector("#wrong_return_description").value.trim()
    }

    function checkErrorInput(requestData) {
      if (requestData.price === "" ||
        requestData.name === "" ||
        requestData.persontel === "" ||
        requestData.person_mail === "" ||
        requestData.wrong_return_description === ""
      ) {
        notificationService.danger("請輸入價格、姓名、電話、信箱、說明")
        return false
      }
      return true
    }

    function errorSubmit() {
      getErrorRequestData()
      if(!checkErrorInput(errorRequestData)) { return }
      axios
      .post('/service/MRP0201R/updateWrongReturn', errorRequestData)
        .then(response => {
          if(response.status === 200) {
            notificationService.danger("異常回報已送出")
            errorCancel()
          }
        })
        .catch(error => {  console.log("ERROR : ", error)  })
        .finally(() => {});
    }

    function errorCancel() {
      document.querySelector("#modal-body-detail").innerHTML = ""
      document.querySelector("#price").value = ""
      document.querySelector("#purchase_quantity").value = ""
      document.querySelector("#person_name").value = ""
      document.querySelector("#persontel").value = ""
      document.querySelector("#person_mail").value = ""
      document.querySelector("#wrong_return_description").value = ""
      document.querySelector(".modal-header button").click()
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
        .get("/service/MRP0201R/queryBasic", { params : requestData })
        .then(({ data }) => {
          table.data = data.response.slice((form.currentPage * form.perPage), data.response.length);
        })
        .finally()
    };

    onMounted(() => {
      getCityList()
      getProjectScaleList()
      getProjectTypeList()
      getDefaultDate()
      getDateList()
      addAreaListener()
      addTreeListener()
      addBadgeListener()
      addCityListener()
    });

    return {
      table,
      setDatepicker,
      query0201,
      resetQuery,
      showErrorModal,
      errorSubmit,
      errorCancel,
      switchSection,
      validateState,
      handleQuery,
      handlePaginationChanged,

    };
  },
};
</script>

<style scoped>
@import 'mrp.css';

</style>

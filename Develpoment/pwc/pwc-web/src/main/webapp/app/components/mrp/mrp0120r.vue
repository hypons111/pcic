<template>
  <div>

    <section class="container mt-2 mb-2">
      <div class="h4 m-0 py-2 button-border">決標結果回報</div>
      <div class="card mt-2">
        
        <div id="card_header" class="card-header text-left">
          <div class="align-items-center">
            <div class="col-sm-12 p-0 flex">
              <h5 id="mainHeader" class="m-0">決標結果回報 / 工程價格電子檔上傳</h5>
              <a id="donwloadGuideLink" href="" download=""><h5 class="m-0">下載操作手冊</h5></a>
            </div>
          </div>
        </div>

        <stepStatesGroup></stepStatesGroup>

        <!-- state_0 -->
        <div id="state_0" class="card-body hidden">
          <div class="contentGroup flex">
            <p>雲端系統[工程價格上傳]平台支援Edge、Chrome、Firefox瀏覽器，工程單價上傳不影響決標公告傳輸至正式區。</p>
            <p class="mt-4 mb-4">
              因<a href="https://support.microsoft.com/zh-tw/windows/internet-explorer-%E4%B8%8B%E8%BC%89-d49e1f0d-571c-9a7b-d97e-be248806ca70" target="_blank">
              [Internet Explorer的支援已於2022年6月15日終止]</a>，基於資安考量本功能不支援IE，詳情請參閱
              <a href="https://learn.microsoft.com/zh-tw/lifecycle/announcements/internet-explorer-11-end-of-support" target="_blank">Windows公告</a>。
            </p>
            <span>
              <input type="checkbox" id="isShowstate_0" name="isShowstate_0" checked></input>
              <label id="isShowstate_0_label" class="ml-1" for="isShowstate_0">不再出現此訊息</label>
            </span>
            <div class="buttonGroup card-row">
              <button class="btn btn-info" @click="stateController('0', '1')">我已了解，開始上傳</button>
            </div>
          </div>
        </div>
        
        <!-- state_1 -->
        <div id="state_1" class="card-body hidden">

          <div class="contentGroup">
            <h5 class="bg_gray font_bold">標案資訊</h5>
            <table id="state_1_table" class="table">
                <tbody>
                  <tr>
                    <td class="col-1 font_bold">標案案號：</td>
                    <td id="ENGINEERING_CODE" class="col-5">{{ projectData.ENGINEERING_CODE }}</td>
                    <td class="font_bold">招標機關：</td>
                    <td id="MAIN_ORG_NAME">{{ projectData.MAIN_ORG_NAME }}</td>
                  </tr>
                  <tr>
                    <td class="font_bold">聯絡人：</td>
                    <td id="TENDER_HOST">{{ projectData.TENDER_HOST }}</td>
                    <td class="col-1"></td>
                    <td id="" class="col-5"></td>
                  </tr>
                  <tr>
                    <td class="font_bold">標案名稱：</td>
                    <td id="ENGINEERING_NAME">{{ projectData.ENGINEERING_NAME }}</td>
                    <td class="font_bold">機關標號：</td>
                    <td id="MAIN_ORG_CODE">{{ projectData.MAIN_ORG_CODE }}</td> 
                  </tr>
                  <tr>
                    <td class="font_bold">聯絡電話：</td>
                    <td id="TENDER_PHONE">{{ projectData.TENDER_PHONE }}</td>
                    <td class="font_bold">決標日期：</td>
                    <td id="AWARD_BUDGET_DATE">{{ projectData.AWARD_BUDGET_DATE }}</td>
                  </tr>
                  <tr>
                    <td class="font_bold">決標金額：</td>
                    <td id="AWARD_BUDGET">{{ projectData.AWARD_BUDGET }}</td>
                    <td class="font_bold">預算金額：</td>
                    <td id="MAIN_BUDGET">{{ projectData.MAIN_BUDGET }}</td>
                  </tr>
                </tbody>
            </table>
          </div>
            
          <div class="contentGroup">
            <h5 class="bg_gray font_bold">機關填表</h5>
            <div class="flex card-row ">
                <div class="flex item double">
                  <p class="font_bold">【機關承辦聯絡資訊】</p>
                  <div class="block"></div>
                </div>
            </div>

            <div class="flex card-row">
              <div class="flex item">
                <label><p class="font_red">*</p>聯絡人</label>
                <input data-label="聯絡人" id="name" class="form-control isCheckInput" v-model="projectData.name"></input>
                <div class="block"></div>
              </div>

              <div class="flex item">
                <label><p class="font_red">*</p>電子郵件</label>
                <input data-label="電子郵件" id="email" class="form-control isCheckInput" v-model="projectData.email"></input>
                <div class="block"></div>
              </div>
            </div>

            <div class="flex card-row">
                <div class="flex item double">
                  <label><p class="font_red">*</p>聯絡電話</label>
                  (
                  <input data-label="聯絡電話" id="phone_1" class="form-control isCheckInput phonePart" v-model="projectData.phone0"></input>
                  )
                  <input data-label="聯絡電話" id="phone_2" class="form-control isCheckInput phonePart" v-model="projectData.phone1"></input>
                  分機
                  <input data-label="聯絡電話" id="phone_3" class="form-control phonePart" v-model="projectData.phone2"></input>
                  <div class="block"></div>
                </div>
            </div>

            <div id="" class="flex card-row">
              <div class="flex item double">
                <p class="font_red">公共工程價格資料庫所收集的聯絡資訊，僅供通知上傳相關問題與檢覈結果之用，並不做其他用途。</p>
                <div class="block"></div>
              </div>
            </div>

            <div id="" class="flex card-row">
              <div class="flex item double">
                <p class="font_bold">【工程標案資訊】</p>
                <div class="block"></div>
              </div>
            </div>

            <div id="" class="flex card-row">
              <div class=" item">
                <label class="wider"><p class="font_red">*</p>預算金額核可日</label>
                <i-date-picker id="check_budget_date" class="isCheckInput" placeholder="YYY/MM/DD" v-model="projectData.check_budget_date"></i-date-picker>
                <div class="block"></div>
              </div>
            </div>


            <div id="" class="flex card-row">
              <label class="wider"><p class="font_red">*</p>工期起迄日</label>
              <div class=" item">
                <label>預定開工日期</label>
                <i-date-picker id="start_time" class="isCheckInput" placeholder="YYY/MM/DD" v-model="projectData.start_time"></i-date-picker>
                <div class="block"></div>
              </div>

              <div class=" item">
                <label>預定完工日期</label>
                <i-date-picker id="end_time" class="isCheckInput" placeholder="YYY/MM/DD" v-model="projectData.end_time"></i-date-picker>
                <div class="block"></div>
              </div>
            </div>

            <div id="areaGroup" class="flex card-row">
              <label class="wider algin-top"><p class="font_red">*</p>工程所在地區</label>
              <div id="selectArea" class="flex item algin-top">
                <div class="part">
                  <label class="short">縣巿</label>
                  <select name="city" id="city" class="select" @change="selectCity($event)"></select>
                </div>

                <div class="part">
                  <label class="short">鄉鎮</label>
                  <select name="town" id="town" class="select">
                    <option disabled selected value>請選擇&emsp;</option>
                  </select>
                </div>         

                <div id="addTownButton">
                  <button class="btn btn-info" @click="selectArea($event)">加入</button>
                </div>
              </div>

              <div id="selectedAreaDiv" class="flex item">
                <table id="selectedAreaTable">
                  <tbody>
                    <tr style="height:41px;">
                      <td colspan="41" style="text-align:center; border:1px solid gray;">已選擇地區</td>
                    </tr>
                  </tbody>
                </table>
                <div class="block"></div>
              </div>

            </div>
          </div>

          <div class="contentGroup">
            <h5 class="bg_gray font_bold">工程價格電子檔上傳</h5>

            <div id="xmlUploadGroup" class="flex card-row">
              <div id="xmlUploadInput" class="flex item double">
                <label for="files">XML檔案上傳</label>
                <input type="file" id="xmlFiles" name="files" accept=".xml" multiple>
                <button class="btn btn-light" @click="selectFiles()">瀏覽</button>
              </div>
            </div>

            <div id="" class="">
              <div class="">
                <div id="xmlList"></div>
                <div class="block"></div>
              </div>
            </div>

            <div id="" class="flex card-row">
              <div class="flex item double">
                <p class="font_red">※請注意，如重新上傳同標案之XML，請務必重傳該標案所有的預算、契約電子檔，以免造成後續稽催。</p>
                <div class="block"></div>
              </div>
            </div>
          </div>

          <div id="" class="buttonGroup flex card-row">
            <button class="btn btn-info" @click="stateController('1', '2', state_2FileArray)">下一步</button>
          </div>
        </div>

        <!-- state_2 -->
        <div id="state_2" class="card-body hidden">
          <div class="contentGroup">
            <table id="" class="table">
              <tbody>
                <tr>
                  <td class="col-1 font_bold">招標機關：</td>
                  <td class="col-5" id="">{{ projectData.MAIN_ORG_NAME }}</td>
                </tr>
                <tr>
                  <td class="font_bold">標案案號：</td>
                  <td id="">{{ projectData.ENGINEERING_CODE }}</td>
                </tr>
                <tr>
                  <td class="font_bold">標案名稱：</td>
                  <td id="">{{ projectData.ENGINEERING_NAME }}</td>
                </tr>
                <tr>
                  <td class="font_bold">預算金額：</td>
                  <td id="">{{ projectData.MAIN_BUDGET }}</td>
                </tr>
                <tr><td></td></tr>
                <tr>
                  <td colspan="4" style="color: red">提醒你，選擇之單價檔有部分不符合決標公告內容，請再次確認有選擇正確之單價檔，如確認無誤請按下「皆已確認無誤」繼續上傳作業。</td>
                </tr>
                <tr><td></td></tr>
              </tbody>
              
              <tbody v-for="(item, index) in state_2FileArray[0]">
                <tr class="table">
                  <td class="col-1 font_bold">單價電子檔：</td>
                  <td class="col-5">{{ state_2FileArray[0][index].fileName }}</td>
                  <td class="col-1 font_bold">單價電子檔：</td>
                  <td class="col-5">{{ state_2FileArray[1][index].fileName }}</td>
                </tr>
                <tr>
                  <td class="font_bold">招標機關：</td>
                  <td>{{ state_2FileArray[0][index].ProcuringEntity }}</td>
                  <td class="font_bold">招標機關：</td>
                  <td>{{ state_2FileArray[1][index].ProcuringEntity }}</td>
                </tr>
                <tr>
                  <td class="font_bold">標案案號：</td>
                  <td>{{ state_2FileArray[0][index].ProcuringEntityId }}</td>
                  <td class="font_bold">標案案號：</td>
                  <td>{{ state_2FileArray[1][index].ProcuringEntityId }}</td>
                </tr>
                <tr>
                  <td class="font_bold">標案名稱：</td>
                  <td>{{ state_2FileArray[0][index].ContractTitle }}</td>
                  <td class="font_bold">標案名稱：</td>
                  <td>{{ state_2FileArray[1][index].ContractTitle }}</td>
                </tr>
                <tr>
                  <td class="font_bold">預算金額：</td>
                  <td>{{ state_2FileArray[0][index].TotalAmount }}</td>
                </tr>
              </tbody>
              
            </table>
          </div>

          <div id="" class="buttonGroup flex card-row">
            <button class="btn btn-info" @click="stateController('2', '3')">皆已確認無誤</button>
            <button class="btn btn-info" @click="toggleStates('2', '1')">重新選擇檔案</button>
          </div>
        </div>

        <!-- state_3 -->
        <div id="state_3" class="card-body hidden">
          <div class="contentGroup">
            <table id="state_3_table" class="table">
              <tbody>
                <tr>
                  <td class="col-6">
                    <label style="width: 100%;" for="is40Percent_INPUT1">
                      <input id="state_3_INPUT1" type="checkbox" name="is40Percent" value="y" @click="moreThan40Percent()"> 標案案號、標案名稱、招標機關、預算金額、決標金額正確
                    </label>
                  </td>
                  <td><p id="state3_p1" class="hidden">使用者已確認無誤</p></td>
                </tr>
                <tr>
                  <td class="col-6">
                    <label style="width: 100%;" for="is40Percent_INPUT2">
                      <input id="is40Percent_INPUT2" type="checkbox" name="is40Percent" value="n" @click="lessThan40Percent()"> 未達細目編碼要求之正確率門檻
                    </label>
                  </td>
                  <td><p id="state3_p2" class="font_red hidden">未達細目編碼要求之正確率門檻</p></td>
                </tr>
                <tr><td colspan="2">配合公共工程會員會提升編碼正確率目標，細目編碼正確率需大40%以上，請更正後重新上傳，或於下方填報說明無法達成之原因，按下[確認]完成上傳作業。</td></tr>
                <tr><td colspan="2">XXX{{ }}</td></tr>
                <tr><td colspan="2">細目編碼正確率{{ }}</td></tr>
                <tr id="showTextarea" class="hidden"><td id="textareaTD" colspan="2"><textarea type="textarea" rows="10" mixlength="50" placeholder=" (請在此填寫無法達成40%之說明，限30字以上，未填寫無法上傳)"></textarea></td></tr>
              </tbody>
            </table>
          </div>

          <div id="" class="buttonGroup flex card-row">
            <button class="btn btn-info" @click="stateController('3', '4')">下一步</button>
          </div>
        </div>

        <!-- state_4 -->
        <div id="state_4" class="card-body hidden">

          <div class="contentGroup">
            <table id="state_4-1_table" class="table">
              <body>
                <tr>
                  <td class="col-4"></td>
                  <td id="tick_TD" class="col-1" rowspan="3"><img id="tick" src="./image/tick.png" alt=""></img></td>
                  <td id="systemDatetime" class="col-3">{{ systemDatetime }}</td>
                  <td class="col-4"></td>
                </tr>
                <tr>
                  <td></td>
                  <td style="color: #82c815">感謝使用公共工程價格資料庫</td>
                  <td></td>
                </tr>
                <tr>
                  <td></td>
                  <td style="color: #82c815">工程價格電子檔上傳作業</td>
                  <td></td>
                </tr>
              </body>
            </table>
          </div>

          <div class="contentGroup">
            <h5 class="bg_gray">本次上傳作業明細</h5>
            <table id="state_4-2_table" class="table b-table table-hover table-bordered">
              <thead>
                <tr>
                  <th class="text-center align-middle">標案名稱</th>
                  <th class="text-center align-middle">工程價格電子檔</th>
                  <th class="text-center align-middle">檢覈結果</th>
                  <th class="text-center align-middle">上傳狀態</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="text-left align-middle"></td>
                  <td class="text-left align-middle"></td>
                  <td class="text-left align-middle"></td>
                  <td class="text-center align-middle"></td>
                </tr>
              </tbody>
            </table>
          </div>

          <div class="contentGroup">
            <p id="state_4_email"></p>
          </div>

        </div>

      </div>
    </section>

  </div>
</template>

<script lang="ts">
import iDatePicker from '@/shared/i-date-picker/i-date-picker.vue';
import stepStatesGroup from '@/shared/i-table/stepStatesGroup.vue';
import { IUser } from '@/shared/model/user.model';
import { useGetters } from '@u3u/vue-hooks';
import { getCurrentInstance, onMounted, Ref } from '@vue/composition-api';
import axios from 'axios';
import util from '../../../../../mrp_util';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';

export default {
  name: "mrp0120r",
  components: { iDatePicker, stepStatesGroup },
  data() {
    return {
      onlinePeopleAmount : 1,
      systemDatetime : "",
      email : "",
      state_2FileArray : [[],[]],
      projectData : {
        ENGINEERING_CODE: "",
        TENDER_HOST: "",
        MAIN_ORG_NAME: "",
        ENGINEERING_NAME: "",
        MAIN_ORG_CODE: "",
        TENDER_PHONE: "",
        AWARD_BUDGET_DATE: "",
        AWARD_BUDGET: "",
        MAIN_BUDGET: ""
      },
    }
  },

  methods: {
    getDatetime() {
      const d = new Date()
      this.systemDatetime = `
        ${this.changeYearFormat(d.getFullYear())}/${d.getMonth() + 1}/${d.getDate()} 
        ${this.ampm(d.getHours())}
        ${this.addZero(d.getHours())}:${this.addZero(d.getMinutes())}:${this.addZero(d.getSeconds())}
      `
    },
    changeYearFormat(year) { return year - 1911 },
    ampm(hour) { return hour > 12 ? '上午' : '下午' },
    addZero(value) { return value < 10 ? value = '0' + value : value },
    doSetInterval() {
      this.getDatetime()
    },
  },
  
  mounted() {
    console.log("window : ", window.document)
    this.doSetInterval()
  },
  
  beforeDestroy() {
    clearInterval(this.interval)
    window.clearInterval(this.interval)
  },

  setup() {
    const notificationService: NotificationService = useNotification();
    const account : Ref<IUser> = useGetters(['account']).account;
    const chName: "一一一"
    // const chName = account.value.chName
    const projectId = "000000000000000000"
    let xmllistener = false
    const state_2DataObject = {}
    const xmlArray = []
    let xmlLength = 0
    const xmlData = {}
    const selectedAreaArray = []
    let isSelectedAreaListener = false

    /* onMounted */
    function getMainInfo(projectData) {
      const requestData = {
        contractCode : "3.45.44_1_1CN111V06",   // temp
        filesourceId : "638149900527844993"     // temp
      }
      axios
        .get("/service/MRP0120R/queryMainInfo", { params : requestData })
        .then(({data}) => {
          for(const item in data.response[0].data) {
            if(item === "phone") {
              for(let i=0; i<3; i++) {
                projectData[`${item}${i}`] = data.response[0].data[item].split("-")[i]
              }
            }
            projectData[`${item}`] = data.response[0].data[item]
          }
          data.response[1].area.forEach(area => {
            selectedAreaArray.push(area)
          })
        })
        .finally(() => { showSelectedAreaTable() })
    }
    
    function isShowstate_0() {
      if(localStorage.getItem("isShowstate_0") === null) {
        document.querySelector("#state_0").classList.remove("hidden")
        document.querySelector("#state_1").classList.add("hidden")
      } else if (localStorage.getItem("isShowstate_0") === "hidden") {
        document.querySelector("#state_0").classList.add("hidden")
        document.querySelector("#state_1").classList.remove("hidden")
      }
    }

    /* 有沒有勾選不再出現此訊息 */
    function showstate_0NextTime() {
      document.querySelector("#isShowstate_0").checked ?
      localStorage.setItem("isShowstate_0", "hidden") : localStorage.removeItem("isShowstate_0")
    }

    function getCityMenu() {
      axios
        .get("/service/MRP0201R/queryMrpCity")
        .then(({ data }) => {
          let innerHTMLstring = ""
          data.response.forEach(city => {
            if(city.id !== "24" && city.id !== "25" && city.id !== "26" && city.id !== "27") {
              innerHTMLstring += `<option class="dropdown-item" value="${city.id}^${city.text}">${city.text}</option>`;
            }
          })
          document.querySelector("#city").innerHTML = innerHTMLstring
        })
    }

    function deleteArea(event) {
      selectedAreaArray.splice(event.target.dataset.serialno, 1)
      showSelectedAreaTable()
    }

    /* 下一步 controller */
    function stateController(current, next, state_2FileArray) {
      if(current === "" && next === "0") {
        toggleStates(current, next, "決標結果回報 / 工程價格電子檔上傳")                  
      } else if(current === "0" && next === "1") {
        showstate_0NextTime()
        toggleStates(current, next, "決標結果回報 / 工程價格電子檔上傳 / 填表及XML上傳")                  
      } else if(current === "1" && next === "2") {
        getState_1Data(state_2FileArray)
        checkState_1_and_toggleStates(state_2FileArray, current, next, "決標結果回報 / 工程價格電子檔上傳 / 上傳預覧")
      } else if(current === "2" && next === "3") {
        checkState_3()
        toggleStates(current, next, "決標結果回報 / 工程價格電子檔上傳 / 線上檢覆")
      } else if(current === "3" && next === "4") {
        setState_4_email()
        toggleStates(current, next, "決標結果回報 / 工程價格電子檔上傳 / 完成上傳")
      }
    }

    /* 上一步/下一步 controller */
    function toggleStates(from, to, string) {
      console.log(`from : ${from} > to : ${to}`)
      document.querySelector(`#state_${from}`).classList.add("hidden")
      document.querySelector(`#state_${to}`).classList.remove("hidden")
      document.querySelectorAll(`.step`).forEach(step => { step.classList.remove("on") })
      document.querySelector(`#step_${to}`).classList.add("on")
      document.querySelector("#mainHeader").innerText = string
    }



    /* state 2 */
    function selectCity(e) {
      const selectedCityId = e.target.value.split("^")[0]
      const selectedCityName = e.target.value.split("^")[1]
      let htmlString = "<option disabled selected value>請選擇&emsp;</option>"
      axios
        .get("/service/MRP0201R/queryMrpTown")
        .then(({data}) => {
          data.response.forEach(town => {
            if(town.CITY_ID === selectedCityId && town.CITY_ID !== "24") {
              htmlString += `<option class="dropdown-item" value="${selectedCityName}^${town.NAME}">${town.NAME}</option>`
            }
          });
        })
        .finally(() => { document.querySelector("#town").innerHTML = htmlString })
    }

    function selectArea() {
      const selectedTown = document.querySelector("#town").value
      if(selectedTown === "") { return } 
      if(isSelectedAreaListener === false) {
        isSelectedAreaListener = true
        document.querySelector("#selectedAreaTable tbody").addEventListener("click", deleteArea)
      }
      if(selectedAreaArray.includes(selectedTown)) {
        notificationService.danger("地區已加入")
      } else {
        selectedAreaArray.push(selectedTown)
        showSelectedAreaTable()
      }
    }

    function showSelectedAreaTable() {
      const selectedAreaTableBody = document.querySelector("#selectedAreaTable tbody")
      let htmlString = `
        <tr style="height:41px;">
          <td colspan="41" style="text-align:center; border:1px solid gray;">已選擇地區</td>
        </tr>`
      selectedAreaArray.forEach((area, index) => {
        const selectedCityName = area.split("^")[0]
        const selectedTownName = area.split("^")[1]
        htmlString += `
          <tr class="selectedAreaRow">
          <td data-v-52495062 class="selectedAreaCell">${index+1}</td>
          <td data-v-52495062 class="selectedAreaCell city">${selectedCityName}</td>
          <td data-v-52495062 class="selectedAreaCell town">${selectedTownName}</td>
          <td data-v-52495062><button class="btn btn-info" data-serialNo="${index}">刪除</button></td>
        </tr>`
      })
      selectedAreaTableBody.innerHTML = htmlString
    }

    function selectFiles() {
      document.querySelector("#xmlFiles").click()
      if(!xmllistener) {
        xmllistener = true
        document.querySelector("#xmlFiles").addEventListener("change", event => {
          let string = ""
          event.target.files.forEach(file => {
            string += `<p>&emsp;&emsp;${file.name}</p>`
          })
          document.querySelector("#xmlList").innerHTML = string
        })
      }
    }

    function getState_1Data(state_2FileArray) {
      state_2DataObject.name = document.querySelector("#name").value
      state_2DataObject.project_id = projectId
      state_2DataObject.email = document.querySelector("#email").value
      state_2DataObject.phone = `${document.querySelector("#phone_1").value}-${document.querySelector("#phone_2").value}-${document.querySelector("#phone_3").value}`
      
      state_2DataObject.check_budget_date = util().toY4((document.querySelector("#check_budget_date input").value))
      state_2DataObject.start_time = util().toY4((document.querySelector("#start_time input").value))
      state_2DataObject.end_time = util().toY4((document.querySelector("#end_time input").value))
      
      state_2DataObject.TAIWAN_TOWN_ID = []
      const cityArray = document.querySelectorAll(".city")
      const townArray = document.querySelectorAll(".town")
      for(let i=0; i<cityArray.length; i++) {
        state_2DataObject.TAIWAN_TOWN_ID.push(`${cityArray[i].innerText}^${townArray[i].innerText}`)
      }

      if(state_2FileArray[0].length !== 0 || state_2FileArray[1].length !== 0) { 
        state_2FileArray[0].length = 0
        state_2FileArray[1].length = 0
      }
      xmlLength = document.querySelector("#xmlFiles").files.length
      for(let i=0; i<xmlLength; i++) {
        const xml = document.querySelector("#xmlFiles").files[i]
        xmlArray.push(xml)
        const tempObject = {}
        tempObject.fileName = xml.name
        let file = xml
        let reader = new FileReader()
        let parser = new DOMParser()
        reader.readAsText(file)
        reader.onload = function() {
          let xmlDoc = parser.parseFromString(reader.result, "text/xml")
          tempObject.ProcuringEntity = xmlDoc.getElementsByTagName('ProcuringEntity')[0].innerHTML
          tempObject.ProcuringEntityId = xmlDoc.getElementsByTagName('ProcuringEntityId')[0].innerHTML
          tempObject.ContractTitle = xmlDoc.getElementsByTagName('ContractTitle')[0].innerHTML
          tempObject.ContractScale = xmlDoc.getElementsByTagName('ContractScale')[0].innerHTML
          tempObject.CorrectRatio = xmlDoc.getElementsByTagName('CorrectRatio')[0].innerHTML
          tempObject.WeightCorrectRatio = xmlDoc.getElementsByTagName('WeightCorrectRatio')[0].innerHTML
          tempObject.FitRatio = xmlDoc.getElementsByTagName('FitRatio')[0].innerHTML
          tempObject.WeightFitRatio = xmlDoc.getElementsByTagName('WeightFitRatio')[0].innerHTML
          tempObject.TotalAmount = xmlDoc.getElementsByTagName('TotalAmount')[0].innerHTML
          tempObject.fileType = xmlDoc.getElementsByTagName('TotalAmount')[0].innerHTML
          tempObject.documentType = xmlDoc.getElementsByTagName('ETenderSheet')[0].getAttribute("documentType")
        }
        reader.onloadend = function() {
          xml.name.includes("預算書") ? state_2FileArray[0].push(tempObject) : state_2FileArray[1].push(tempObject)
        }
      }
    }

    function checkState_1_and_toggleStates(state_2FileArray, current, next, titleString) {
      /* check data here */
      console.log("state_2DataObject : ", state_2DataObject)
        axios
          .post("/service/MRP0120R/updateContractInfo", state_2DataObject)
            .then(({data}) => {
              console.log("data : ", data)
            })
            .catch(error => {  console.log("ERROR : ", error)  })
            .finally(() => {});

      /* check file here */
      let timeout = window.setInterval(() => {
        if(state_2FileArray.flat().length === xmlLength) {
          clearTimeout(timeout)
          state_2FileArray.flat().forEach(file => {
            /* 1 */
            if(!file.fileName.includes(".xml")) {
              notificationService.danger("檔案格式不正確，勿上傳EXCEL或AP等其他檔案格式，請使用XML檔案格式，PCCES轉檔問題，請洽(02)2708-8090")
              return
            }
            /* 6 */
            if(file.documentType === null || file.documentType === "" || file.documentType == undefined) {
              notificationService.danger("傳輸檔案非公共工程經費電腦估價系統格式電子檔，PCCES 單價電子檔製作問題，請洽(02)2708-8090")
              return
            }
            /* 7 */
            if(file.CorrectRatio === null || file.CorrectRatio === "" || file.CorrectRatio == undefined) {
              notificationService.danger("傳輸檔案無正確率欄位，不符合公共工程經費電腦估價系統格式，可繼續上傳作業，但正確率會為0%，PCCES 單價電子檔製作問題，請洽(02)2708-8090")
              return
            }
          })

          /* 2 */
          // "本案曾經上傳過XML檔，重新上傳請務必重送全部檔案，以免後續稽催。請確定是否覆蓋?"
          /* 3 */
          // "本次上傳作業已選過同名檔案，是否取代舊檔?"
          
          /* 4 */
          if(state_2FileArray[0].length === 0 && state_2FileArray[1].length === 0) {
            notificationService.danger("尚未選擇檔案")
            return
          }
          /* 5 */
          if(state_2FileArray[0].length !== state_2FileArray[1].length) {
            notificationService.danger("【決標書】與【預算書】的檔案要一樣多")
            return
          }

          /* 8 */
          // "${request_mes+submin_mes+other_mes}，請選擇契約單價電子檔及預算單價電子檔， PCCES 單價電子檔製作問題，請洽(02)2708-8090」，下方有「利用預算書製作契約單價xml電子檔，供決標上傳之應用」之連結"
          /* 9 */
          // "傳輸檔案非契約單價電子檔"
          /* 10 */
          // "屬複數決標案件者，契約單價電子檔及預算單價電子檔之檔案數量須分別大於或等於決標品項數"

          /* 11 */
          if(state_2FileArray[1].length === 0) {
            notificationService.danger("傳輸檔案皆為預算書格式，缺漏契約單價或未存取為契約類型之XML，PCCES 單價電子檔製作問題，請洽(02)2708-8090，下方有「利用預算書製作契約單價xml電子檔，供決標上傳之應用」之連結")
            return
          }
          /* 12 */
          if(state_2FileArray[0].length === 0) {
            notificationService.danger("傳輸檔案皆為契約書格式，缺漏預算單價或未存取為預算類型之XML，PCCES 單價電子檔製作問題，請洽(02)2708-8090，下方有「利用預算書製作契約單價xml電子檔，供決標上傳之應用」之連結")
            return
          }
          /* 13 */
          if(state_2FileArray[0].length !== state_2FileArray[1].length) {
            notificationService.danger("缺漏部分契約單價電子檔或預算單價電子檔")
            return
          }
        }
        



/*
        const url = "/service/MRP0120R/uploadFiles"
        let formData = new FormData()
        xmlArray.forEach(xml => { formData.append("xml_files", xml) })
        const config = {headers: { "Content-Type": "multipart/form-data"}
        axios
          .post(url, formData, config)
            .then(({data}) => { console.log("data : ", data) })
            .catch(error => { console.log("ERROR : ", error) })
            .finally(() => {});
*/ 


        // function uploadState_1Data() {
      //     Promise.all([
      //       axios.post('/service/MRP0120R/updateContractInfo', { params : state_2DataObject }), 
      //       axios.post('/service/MRP0120R/uploadFiles', { params : state_2FileArray })
      //     ])
      //       .then(response => {
      //         console.log(response)
      //         // toggleStates(current, next, titleString)
      //       })
      //       .finally(() => { console.log("finally") })
      //   // }

      }, 100)

    }

    /*
        function state_1_data() {
          return axios.post('/service/MRP0120R/updateContractInfo', { params : state_2DataObject })
        }
        function state_1_file() {
          return axios.post('/service/MRP0120R/uploadFiles', { params : state_2FileArray })
        }
    */


    function showMismatchLabel() {}
    /* state 2 */


    /*  3 */
    function moreThan40Percent() {
        document.querySelector("#state3_p1").classList.toggle("font_red")
    }

    function lessThan40Percent() {
      document.querySelector("#state3_p2").classList.toggle("font_red")
      document.querySelector("#showTextarea").classList.toggle("hidden")
    }
    
    function checkState_3() {
      // if(Number(uploadFileData.uploadFileCorrectRatio.replace("%", "")) < 30) {}
    }
    /* state 3 */


    /* state 4 */
    function setState_4_email() {
      document.querySelector("#state_4_email").innerText = `工程標案 工項編碼推薦，將於1天內寄到 ${state_2DataObject.email}，請注意後續信件通知`
    }
    /* state 4 */




    onMounted(() => {
      getMainInfo(getCurrentInstance().data.projectData)
      isShowstate_0()
      getCityMenu()
    })
    
    return {
      stateController,
      toggleStates,
      selectCity,
      selectArea,
      deleteArea,
      showstate_0NextTime,
      selectFiles,
      moreThan40Percent,
      lessThan40Percent,
    }


  },
}

</script>

<style scoped>
  @import 'mrp.css';

  p {
    margin: 0px;
  }

  #isShowstate_0_label {
    width: auto;
  }

  .bg_gray {
    margin: 0px;
    padding: 10px;
    background-color: lightgray;
  }
  .font_bold {
    padding: 12px 0px 12px 12px !important;
    font-weight: bold;
  }

  .contentGroup {
    flex-direction: column;
    margin: 30px 0px 0px 0px;
  }

  #state_0>.contentGroup>span,
  #state_0>div>div {
    align-self: center;
    /* border: 1px solid green; */
  }
  #state_0>.contentGroup>span {
    margin: 15px 0px 5px 10px;
  }

  #state_0>div>div>button {
    margin-left: 0px;
  }

  #uploadFiles__BV_file_outer_ {
    width: 30% !important;
    flex-grow: 1;
  }

  .a {
    border: 2px solid blue;
  }


  table {
    margin: 0px !important;
  }

  table td {
    /* padding: 10px 0px; */
    /* border: 0px solid white; */
    border: 0px solid red;
  }
  
  #textareaTD textarea {
    width: 100%;
  }






  #state_4-1_table td {
    padding: 0px 20px;
    height: 40px;
    line-height: 40px;
    /* border: 2px solid red; */
  }

  #state_4-1_table tr:nth-child(1) td:nth-child(2) {
    width: 146px;
    height: 120px;
    padding: 0px;
  }

  #state_4-2_table td {
    border: 1px solid #dee2e6;
  }




  #tick {
    width: 100%;
    height: 100%;
  }


  /* card header */
  #card_header > div > div {
    padding-right: 10px !important;
    justify-content: space-between !important;
  }





  /* 聯絡電話 */
  .phonePart {
    padding: 16px !important;
  }
  #phone_1,
  #phone_3 {
    max-width: 70px !important;
    margin: 0px 5px;
  }
  #phone_2 {
    margin-left: 10px !important;
    margin-right: 20px !important;
    max-width: 103px !important;
  }

.short {
  width: 50px;
  line-height: 30px;
  margin-right: 0px;
}

.part {
  height: 30px;
  display: flex;
}


/* 地區 pull down menu */
.algin-top {
  align-self: start;
  /* border: 1px solid green; */
}

#selectedAreaDiv {
  height: fit-content;
}

#selectArea>div {
  margin-right: 50px;
  /* border: 1px solid navy; */
}

#selectedAreaTable {
  flex-grow: 1;
  /* border: 2px solid greenyellow !important; */
}
#selectedAreaTable tbody{
  display: table;
  width: 100%;
}
.selectedAreaCell {
  padding: 0px 20px;
}
.selectedAreaRow>td {
  border: 1px solid gray !important;
}
.selectedAreaRow>td:first-child {
  width: 15%;
}
.selectedAreaRow>td:last-child {
  width: 1%;
  white-space: nowrap;
}

</style>
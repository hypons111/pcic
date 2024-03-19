<template>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2 mb-2">
      <div class="h4 m-0 py-2 button-border">工項編碼異動匯入</div>
      <div id="import" class="card mt-2">
        <div class="card-body">
          <div class="flex card-row">
            <div class="flex double">
              <button id="file_button" class="btn btn-info" @click="clickInput()">選擇檔案</button>
              <input id="file_input" class="hidden" type="file" accept="application/vnd.ms-excel"/>
              <p id="file_p" class="font_red margin_0_5_0_0">請選擇工項異動檔(Excel)</p>
              <div class="buttonGroup flex card-row">
                <button class="btn btn-info" @click="Upload()">上傳</button>
                <button class="btn btn-info" @click="">儲存</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 查詢結果 -->
    <section id="resultTable" class="container mt-4 hidden">
      <mrp-table
        ref="iTable"
        class="table-sm"
        :page="'mrp0118r'"
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

    <!-- 操作狀態 -->
    <section id="statusSection" class="container hidden">
      <div id="statusDiv" class="card mt-2">
        <p id="status" >請點擊[上傳]按鈕</p>
      </div>
    </section>

  </div>
</template>
<script lang="ts">
import mrpTable from '@/shared/i-table/mrp-table.vue';
import { onMounted, reactive } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';

export default {
  name: "mrp0118r", 
  components: { mrpTable },
  setup() {
    const notificationService: NotificationService = useNotification();
    let file_input = undefined
    let file_p = undefined
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
          key: 'a',
          label: '工項編碼',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'b',
          label: '工項名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'c',
          label: '工項單位',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'd',
          label: '狀態',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle'
        }
      ],
      data: [],
      totalItems: undefined,
    });

    function addListener() {
      file_input = document.querySelector("#file_input")
      file_p = document.querySelector("#file_p")
      file_input.addEventListener("change", () => {
        file_p.innerText = file_input.files[0].name
        file_p.classList.remove("font_red")
        document.querySelector("#statusSection").classList.toggle("hidden")
      })
    }

    function clickInput() {
      document.querySelector("#file_input").click()
    }

    function Upload() {
      document.querySelector("#statusSection").classList.toggle("hidden")
      let temp = " x "
      document.querySelector("#status").innerText = `已匯入${temp}筆，有${temp}筆已存在於工項代碼檔`

      console.log("file : ", document.querySelector("#file_input").files[0])

      const url = "/service/MRP0118R"
      let formData = new FormData()
      formData.append("XLS_file", document.querySelector("#file_input").files[0])
      const config = {headers: { "Content-Type": "multipart/form-data"}}

      axios
        .post(url, formData, config)
        .then(({ data }) => { console.log("data : ", data) })
        .catch(error => { console.log("ERROR : ", error) })
        .finally(() => {
          document.querySelector("#resultTable").classList.toggle("hidden")
          document.querySelector("#statusSection").classList.toggle("hidden")
        }) 
    }

    function back() {
      changeView("", "")
    }

    function changeView( fileName, resultString) {
      reset()
      document.querySelector("#import").classList.toggle("hidden")
      document.querySelector("#import_result").classList.toggle("hidden")
      document.querySelector("#fileName").innerText += fileName
      document.querySelector("#result").innerText += resultString
    }

    function reset() {
      file_p.innerText = "請選擇工項異動檔(Excel)"
      file_p.classList.add("font_red")
    }

    onMounted(() => {
      addListener()
    });

    return {
      table,
      clickInput,
      Upload,
      back,
      reset
    };
  },
};
</script>

<style scoped>
@import 'mrp.css';

#file_button {
  width: 80px;
  height: 30px;
}

#import_result>.card-body>.flex>.flex {
  flex-direction: column;
  align-items: flex-start;
}

#import_result>.card-body>.flex>.flex {
  flex-direction: column;
  align-items: flex-start;
}
#import_result>.card-body>.flex>.fle>p:nth-child(1) {
  margin-bottom: 50px !important;
}

.card-body > .card-row > div > * {
  /* border: 1px solid red !important; */
}

#file_button {
  padding: 6px 12px !important;
  width: auto !important;
  height: 38px ;
}

#file_p {
  flex-grow: 1;
  height: 38px ;
  padding: 6px 12px;
  border: 1px solid gray !important;
}

.buttonGroup {
  margin-bottom: 0px;
}

.margin_0_5_0_0 {
  margin: 0px 5px 0px 0px !important;
}

/* 操作狀態 */
#statusSection { text-align: center; }
#statusDiv { margin: 0px !important; }
#status { margin: 10px 0px !important; }
</style>

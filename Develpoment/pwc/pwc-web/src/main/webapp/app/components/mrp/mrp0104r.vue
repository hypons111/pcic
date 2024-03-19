<template>
  <section class="container mt-2 mb-2">
    <div class="h4 m-0 py-2 button-border">解析 KPI 管理</div>
    <div class="card mt-2">
      <table id="A_Table" class="table b-table table-hover table-bordered">
        <thead>
          <tr>
            <th class="text-center align-middle width-60percent">
              <div class="text-center align-middle">工項編碼解析KPI</div>
            </th>
            <th class="text-center align-middle">
              <div class="text-center align-middle">KPI</div>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="text-center align-middle">本月解析工項數量</td>
            <td id="A1" class="text-center align-middle data"></td>
          </tr>
          <tr>
            <td class="text-center align-middle">本月解析成功工項數量</td>
            <td id="A2" class="text-center align-middle data"></td>
          </tr>
          <tr>
            <td class="text-center align-middle">本月工項解析成功比率</td>
            <td id="A3" class="text-center align-middle data"></td>
          </tr>
          <tr>
            <td class="text-center align-middle">本月前五碼正確比率</td>
            <td id="A4" class="text-center align-middle data"></td>
          </tr>
          <tr>
            <td class="text-center align-middle">本月統計偏差篩選後工項量</td>
            <td id="A5" class="text-center align-middle data"></td>
          </tr>
          <tr>
            <td class="text-center align-middle">本月變動列管工項量</td>
            <td id="A6" class="text-center align-middle data"></td>
          </tr>
        </tbody>
      </table>

      <table id="B_Table" class="table b-table table-hover table-bordered">
        <thead>
          <tr>
            <th class="text-center align-middle width-60percent">
              <div class="text-center align-middle">使用者應用KPI</div>
            </th>
            <th class="text-center align-middle">
              <div class="text-center align-middle">內容</div>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="text-center align-middle">最常查詢工項-1</td>
            <td id="B1" class="text-center align-middle data"></td>
          </tr>
          <tr>
            <td class="text-center align-middle">最常查詢工項-2</td>
            <td id="B2" class="text-center align-middle data"></td>
          </tr>
          <tr>
            <td class="text-center align-middle">最常查詢工項-3</td>
            <td id="B3" class="text-center align-middle data"></td>
          </tr>
          <tr>
            <td class="text-center align-middle">最常查詢不到工項-1</td>
            <td id="B4" class="text-center align-middle data"></td>
          </tr>
          <tr>
            <td class="text-center align-middle">最常查詢不到工項-2</td>
            <td id="B5" class="text-center align-middle data"></td>
          </tr>
          <tr>
            <td class="text-center align-middle">最常查詢不到工項-3</td>
            <td id="B6" class="text-center align-middle data"></td>
          </tr>
        </tbody>
      </table>

      <table id="C_Table" class="table b-table table-hover table-bordered">
        <thead>
          <tr>
            <th class="text-center align-middle width-60percent">
              <div class="text-center align-middle">內容</div>
            </th>
            <th class="text-center align-middle">
              <div class="text-center align-middle">下載</div>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="text-center align-middle">最常查詢工項前100項</td>
            <td class="text-center align-middle">
              <button id="C1" class="btn btn-info" @click="download(mostSearch)">下載</button>
            </td>
          </tr>
          <tr>
            <td class="text-center align-middle">最常查詢不到工項前100項</td>
            <td class="text-center align-middle">
              <button id="C2" class="btn btn-info" @click="download(failSearch)">下載</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>

<script lang="ts">
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { getCurrentInstance, onMounted } from '@vue/composition-api';
import axios from 'axios';

export default {
  data() {
    return {
      mostSearch: "",
      failSearch: ""
    }
  },

  setup() {
    const notificationService: NotificationService = useNotification();
    function getData(CurrentInstanceData) {
      axios.get('/service/MRP0104R/queryKPI').then(({ data }) => {
        for(const property in data.response[0]) {
          if(property === "mostSearch") {
            CurrentInstanceData.mostSearch = data.response[0].mostSearch
          } else if(property === "failSearch") {
            CurrentInstanceData.failSearch = data.response[0].failSearch
          } else {
            document.querySelector(`#${property}`).innerText = data.response[0][property]
          }
        }
      });
      const dataList = document.querySelectorAll('.data');
      dataList.forEach(cell => {
        cell.innerText = '';
      });
    }

    function download(fileName) {
      axios
        .get("/service/getTheFile", { 
          params: { fileName: fileName }, 
          responseType: 'blob'
        })
        .then((response) => {
          console.log('downloadPDF.reponse : ', response)
          const href = URL.createObjectURL(response.data)
          const link = document.createElement('a')
          link.href = href;
          link.setAttribute('download', `${fileName}`)
          document.body.appendChild(link)
          link.click()
          document.body.removeChild(link)
          URL.revokeObjectURL(href)
        });
    }

    onMounted(() => {
      getData(getCurrentInstance().data);
    });

    return {
      download,
    };
  },
};
</script>

<style scoped>
.width-60percent {
  width: 60%;
}
</style>

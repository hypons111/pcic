<template>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2 mb-2">
      <div class="h4 m-0 py-2 button-border">參考單價設定</div>
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
          <div id="queryUpdataDateGroup" class="flex card-row">
            <div class="flex item">
              <label id="" class="wide">決標日期區間</label>
              <i-date-picker id="startDate" class="form-control"></i-date-picker>
              <b-input-group-text>至</b-input-group-text>
              <i-date-picker id="endDate" class="form-control"></i-date-picker>
              <div class="block"></div>
            </div>
          </div>

          <div class="buttonGroup flex card-row">
            <button class="btn btn-info" @click="download()">匯出 EXCEL</button>
          </div>
        </div>

      </div>
    </section>
  </div>
</template>
<script lang="ts">
import iDatePicker from '@/shared/i-date-picker/i-date-picker.vue';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import { BInputGroupText } from 'bootstrap-vue';
import util from '../../../../../mrp_util';

export default {
  name: "mrp0121r",
  components: { iDatePicker, BInputGroupText },
  setup() {
    const notificationService: NotificationService = useNotification();
    function download() {

      const requestData = {
        start_date: util().toY4(document.querySelector("#startDate input").value),
        end_date: util().toY4(document.querySelector("#startDate input").value)
      }
      
      axios
        .get("/service/MRP0121R/downloadExcel", {
          params: requestData,
          responseType: 'blob'
        })
        .then(( data ) => {
          const href = URL.createObjectURL(data.data)
          const link = document.createElement('a')
          link.href = href;
          link.setAttribute('download', `參考單價設定${util().idGenerator()}.xls`)
          document.body.appendChild(link)
          link.click()
          document.body.removeChild(link)
          URL.revokeObjectURL(href)
        })
        .catch(error => { console.log(error) })
    }

    return {
      download
    };
  },
};
</script>

<style scoped>
  @import 'mrp.css';
</style>

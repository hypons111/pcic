<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                公路-跨海橋梁-結構工程-下部結構-橋墩
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>

        <div class="card-body col-12">
          <b-collapse v-model="stepVisible">
            <table border="2">
              <tr>
                <th  class="text-center">建檔日</th>
                <th  class="text-center">收文日</th>
                <th  class="text-center">案件編號</th>
                <th  class="text-center">案件名稱</th>
                <th class="text-center">區域名稱</th>
                <th class="text-center">計劃狀態</th>
                <th class="text-center">工項</th>
                <th class="text-center">數量</th>
                <th  class="text-center">單位</th>
                <th  class="text-center">經費(萬元)</th>
                <th  class="text-center">備註</th>
              </tr>
              <tr v-for="(item,key) in table1.data">
                <td>{{item.createDate}}</td>
                <td>{{item.receiptDate}}</td>
                <td>{{item.projectNo}}</td>
                <td class="text-center">{{item.projectName}}</td>
                <td class="text-center">{{item.areaName}}</td>
                <td class="text-center">{{item.planStatus}}</td>
                <td class="text-center">{{item.workItem}}</td>
                <td class="text-center">{{item.qty}}</td>
                <td class="text-center">{{item.unit}}</td>
                <td class="text-center">{{item.amount}}</td>
                <td class="text-center">{{item.remark}}</td>
              </tr>
            </table>
          </b-collapse>
        </div>

      </div>

      <div class="text-center mt-4">
          <i-button type="arrow-left" @click="toQueryView"></i-button>
      </div>

    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, ref, watch, onMounted } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import { required } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import iButton from '@/shared/buttons/i-button.vue';

export default {
  components: { iButton },
  name: 'rev0606-edit-info',
  props: {
    isReload: {
      required: false,
    },
  },
  setup() {
    onActivated(() => {
      // if (isReloadProp.value) {
      //   handleQuery();
      // }
    });

    onMounted(() => {
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      memberName: '',
    };

    const rules = {
      memberName: {},
    };

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const table1 = reactive({data: undefined,});
            table1.data = [
            {
              createDate: '民國110/01/19',
              receiptDate: '--',
              projectNo: 'prj202101191234567',
              projectName: '臺南市安平漁港跨海大橋新建工程',
              areaName: '',
              planStatus: '審查中',
              workItem: '公路-跨海橋梁-結構工程-下部結構-橋墩',
              qty: 7,
              unit: '座',
              amount: '77,000',
              remark: '',
            },
          ];

    // 查詢結果及狀態
    const queryStatus = ref(false);

    //顯示查詢條件區
    let stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //返回查詢畫面
    const toQueryView = () => {
      navigateByNameAndParams('rev0606Query', { isNotKeepAlive: true });
    };

    return {
      $v,
      stepVisible,
      queryStatus,
      validateState,
      checkValidity,
      reset,
      toQueryView,
      table1
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

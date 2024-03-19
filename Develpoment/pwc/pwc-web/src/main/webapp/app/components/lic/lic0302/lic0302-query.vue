<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                重大公共建設計畫證照許可管理資訊系統_整體計畫總覽
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
                <th class="text-center">序號</th>
                <th  class="text-center">計畫名稱</th>
                <th  class="text-center">證照名稱</th>
                <th  class="text-center">審證機關</th>
                <th  class="text-center">預定送審日期</th>
                <th class="text-center">預定完成日期</th>
                <th  class="text-center">實際完成日期</th>
                <th width="4%" class="text-center">動作</th>
              </tr>
              <tr v-for="(item,key) in table1.data">
                <td class="text-center">{{key+1}}</td>
                <td>{{item.projectName}}</td>
                <td>{{item.licenseName}}</td>
                <td>{{item.reviewOrgName}}</td>
                <td class="text-center">{{item.expiredDate}}</td>
                <td class="text-center">{{item.expectDate}}</td>
                <td class="text-center">{{item.realDate}}</td>
                <td width="8%">
                  <b-button class="m-1 p-1" variant="info" @click="toSinglePlan(item)">查看計畫</b-button>
                  <b-button class="m-1 p-1" variant="info" @click="toLicense(item)">查看證照</b-button>
                </td>
              </tr>
            </table>
          </b-collapse>
        </div>

      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, ref, watch, onMounted } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import { required } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import iButton from '@/shared/buttons/i-button.vue';

export default {
  components: { iButton },
  name: 'lic0302Query',
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
              projectName: '台9線花東縱谷公路安全景觀大道計畫(臺東段)',
              licenseName: '土地徵收計畫',
              reviewOrgName: '內政部',
              expiredDate: '2026-02-15',
              expectDate: '2026-02-15',
              realDate: '',
            },
            {
              projectName: '國道4號台中環線豐原潭子段計畫',
              licenseName: '徵收土地計畫書',
              reviewOrgName: '內政部',
              expiredDate: '2017-03-31',
              expectDate: '2017-06-30',
              realDate: '2017-04-12',
            },
            {
              projectName: '國道7號高雄路段計畫',
              licenseName: '徵收土地計畫書',
              reviewOrgName: '內政部',
              expiredDate: '2023-06-30',
              expectDate: '2023-12-31',
              realDate: '',
            },
            {
              projectName: '國道1號甲線計畫',
              licenseName: '徵收土地計畫書',
              reviewOrgName: '內政部',
              expiredDate: '2023-12-31',
              expectDate: '2024-02-29',
              realDate: '',
            },
          ];

    // 查詢結果及狀態
    const queryStatus = ref(false);

    //顯示查詢條件區
    let stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //轉到查看計畫
    const toSinglePlan = (item: any) => {
      navigateByNameAndParams('lic0201Query', { item: item, isNotKeepAlive: true });
    };

    //轉到查看證照
    const toLicense = (item: any) => {
      navigateByNameAndParams('lic0303Query', { item: item, isNotKeepAlive: true  });
    };

    return {
      $v,
      stepVisible,
      queryStatus,
      validateState,
      checkValidity,
      reset,
      toSinglePlan,
      toLicense,
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

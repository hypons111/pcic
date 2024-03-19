<template>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
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
        <div class="card-body col-10">
          <b-collapse v-model="stepVisible">
            <b-form-row>
              <!-- 年度 -->
              <i-form-group-check :labelStar="true" label="統計年度" :item="$v.statisticYear">
                <i-date-picker
                  v-model="$v.statisticYear.$model"
                  type="year"
                  placeholder="yyy"
                ></i-date-picker>
              </i-form-group-check>
            </b-form-row>

            <div class="button-float-right">
              <b-button-toolbar>
                <i-button class="mt-1 ml-1" type="search" @click="handleQuery"></i-button>
                <i-button class="mt-1 ml-1" type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </div>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                審議完成率
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

        <div class="card-body col-12" v-if="queryStatus">
          <table align="center">
              <tr>
                <td align="center"><b>各計畫主管部會公共建設計畫提報經費分布圖(億元)</b></td>
              </tr>
              <tr>
                <td>收案數63件，計畫提報經費(A)1,348.615億元，屬本會審議範圍經費(B)1,277.405億元、本會建議核列經費(C)1,625.277億元、經費核列99%(C/B)</td>
              </tr>
              <tr>
                <td align="center">
                  <i-apexcharts :type="'pie'" :items="items" :data="data"></i-apexcharts>
                </td>
              </tr>
          </table>
          <table align="center">
              <tr>
                <td align="center"><b>各計畫主管部會公共建設計畫經費核列率</b></td>
              </tr>
              <tr>
                <td>
                  <i-apexcharts :type="'bar'" :items="items" :data="data"></i-apexcharts>
                </td>
              </tr>
          </table>
        </div>

        <!-- <div class="container" v-if="queryStatus">
          <b-collapse v-model="stepVisible">
            <i-apexcharts :type="'pie'" :items="items" :data="data"></i-apexcharts>
          </b-collapse>
        </div> -->

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
  name: 'rev0601Query',
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
      statisticYear: '',
    };

    const rules = {
      statisticYear: {notnull: required},
    };

    const items = ['交通部', '經濟部', '內政部', '教育部', '國防部', '國軍退除役官兵輔導委員會', '法務部', '科技部', '衛生福利部'];
    const data = [817.7948, 108.0824, 50.9396, 64.0521, 48.0729, 66.9322, 55.1671, 32.3453, 29.9894];
    // const items = ['交通部', '經濟部', '內政部', '教育部', '國防部', '國軍退除役官兵輔導委員會', '法務部', '科技部', '衛生福利部', '經濟部工業局','中央研究院','文化部','客家委員會','海洋委員會','文化部文化資產局'];
    // const data = [817.7948, 108.0824, 50.9396, 64.0521, 48.0729, 66.9322, 55.1671, 32.3453, 29.9894, 21.3445, 20.3137, 10.0582, 8.9513, 7, 0];


    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 查詢結果及狀態
    const queryStatus = ref(false);

    //顯示查詢條件區
    let stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //轉到新增頁面
    const toCreateView = () => {
      navigateByNameAndParams('lic0701-edit-info', { formStatusForward: 'create' ,isNotKeepAlive: true  });
    };

    //轉到編輯頁面
    const toEditView = (licWorkshopMember: any) => {
      navigateByNameAndParams('lic0701-edit-info', { formStatusForward: 'modify', licWorkshopMember: licWorkshopMember, isNotKeepAlive: true });
    };

    const cntReceipt11All = ref(23);
    const cntDone11All = ref(15);
    const cntReceipt12All = ref(21);
    const cntDone12All = ref(3);
    const cntReceipt22All = ref(55);
    const cntDone22All = ref(40);
    const cntReceipt31All = ref(64);
    const cntDone31All = ref(60);

    const table = reactive({
      data: undefined,
    });

    //依條件查詢工作小組成員
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
      //   if (isValid) {
      //     //重置條件
      //     table.data = undefined;
      //     axios
      //       .post('/rec-projects/criteria-jpa', form)
      //       .then(({ data }) => {
      //         table.data = data.content.slice(0, data.content.length);
      //         table.totalItems = data.totalElements;
      //       })
      //       .finally()
      //       .catch(notificationErrorHandler(notificationService));
      //   }
      // });
      queryStatus.value=true;
      table.data = [
        {
          classifyId : '農業建設',
          cntReceipt11 : 2,
          cntDone11 : 2,
          cntReceipt12 : 0,
          cntDone12 : 0,
          cntReceipt22 : 0,
          cntDone22 : 0,
          cntReceipt31 : 25,
          cntDone31 : 25,
          cntReceipt99 : 27,
          cntDone99 : 27,
        },
        {
          classifyId : '下水道',
          cntReceipt11 : 9,
          cntDone11 : 2,
          cntReceipt12 : 15,
          cntDone12 : 0,
          cntReceipt22 : 23,
          cntDone22 : 10,
          cntReceipt31 : 24,
          cntDone31 : 22,
          cntReceipt99 : 10,
          cntDone99 : 2,
        },
        {
          classifyId : '都市開發',
          cntReceipt11 : 12,
          cntDone11 : 11,
          cntReceipt12 : 6,
          cntDone12 : 3,
          cntReceipt22 : 32,
          cntDone22 : 30,
          cntReceipt31 : 14,
          cntDone31 : 13,
          cntReceipt99 : 37,
          cntDone99 : 36,
        },
      ]
    })};

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      $v,
      stepVisible,
      queryStatus,
      validateState,
      checkValidity,
      handleQuery,
      reset,
      handlePaginationChanged,
      table,
      toCreateView,
      toEditView,
      cntReceipt11All,
      cntDone11All,
      cntReceipt12All,
      cntDone12All,
      cntReceipt22All,
      cntDone22All,
      cntReceipt31All,
      cntDone31All,
      items,
      data,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

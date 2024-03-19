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
              <i-form-group-check :labelStar="true" label="統計截止日期:" :item="$v.receiptDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.receiptDateEnd.$model" :state="$v.receiptDateEnd"></i-date-picker>
                </b-input-group>
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
                替選方案評估統計　　　　　　　　　　　　　　　　　　　　統計截止日期：{{formatDateInChinese($v.receiptDateEnd.$model)}}
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

        <div class="container" v-if="queryStatus">
          <b-collapse v-model="stepVisible">
            <table border="2">
              <tr>
                <th class="text-center">年度</th>
                <th class="text-center">總件數</th>
                <th class="text-center">核列經費(億元)</th>
                <th class="text-center">辦理替選方案評估之件數</th>
                <th class="text-center">節省成本或增加效益之金額(億元)</th>
              </tr>

              <tr v-for="(item,key) in table.data">
                <td class="text-center">{{item.year}}</td>
                <td class="text-right">{{item.cntCase}}</td>
                <td class="text-right">{{item.approveAmount}}</td>
                <td class="text-right">{{item.cntAlte}}</td>
                <td class="text-right">{{item.cutCost}}</td>
              </tr>

            </table>
          </b-collapse>
        </div>

      </div>
    </section>
<!-- 匯出ods 按鈕 -->
    <section>
      <div class="container mt-1 mb-1" v-if="queryStatus">
        <b-button variant="info" @click="toSortView">匯出ods</b-button>
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
import { formatDateInChinese } from '@/shared/date/minguo-calendar-utils';

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
      receiptDateStart:'',
      receiptDateEnd:'',
    };

    const rules = {
      receiptDateStart:{notnull: required},
      receiptDateEnd:{notnull: required},
    };

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
          year : '110年',
          cntCase : 32,
          approveAmount : '607.09',
          cntAlte: 6,
          cutCost : 16.62,
        },
        {
          year : '109年',
          cntCase : 67,
          approveAmount : '2,326.56',
          cntAlte: 14,
          cutCost : 23.00,
        },
        {
          year : '108年',
          cntCase : 72,
          approveAmount : '1,569.00',
          cntAlte: 16,
          cutCost : 54.19,
        },
        {
          year : '107年',
          cntCase : 75,
          approveAmount : '781.00',
          cntAlte: 13,
          cutCost : 23.37,
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
      formatDateInChinese,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

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
              <i-form-group-check :labelStar="true" label="本會收文日期:" :dual1="$v.receiptDateStart" :dual2="$v.receiptDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.receiptDateStart.$model" :state="$v.receiptDateStart"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
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

        <div class="container" v-if="queryStatus">
          <b-collapse v-model="stepVisible">
            <table border="2">
              <tr>
                <th rowspan="2" class="text-center">自{{$v.receiptDateStart.$model}}至{{$v.receiptDateEnd.$model}}期間審議案件辦理情形</th>
                <th colspan="3" class="text-center">可行性評估</th>
                <th colspan="3" class="text-center">新興建設計畫</th>
                <th colspan="3" class="text-center">基本設計</th>
                <th colspan="3" class="text-center">年度先期</th>
                <th colspan="3" class="text-center">總計</th>
              </tr>
              <tr>

                <th  class="text-center">收案數A</th>
                <th  class="text-center">結案數B</th>
                <th  class="text-center">審議完成率B/A(%)</th>
                <th  class="text-center">收案數A</th>
                <th  class="text-center">結案數B</th>
                <th  class="text-center">審議完成率B/A(%)</th>
                <th  class="text-center">收案數A</th>
                <th  class="text-center">結案數B</th>
                <th  class="text-center">審議完成率B/A(%)</th>
                <th  class="text-center">收案數A</th>
                <th  class="text-center">結案數B</th>
                <th  class="text-center">審議完成率B/A(%)</th>
                <th  class="text-center">收案數A</th>
                <th  class="text-center">結案數B</th>
                <th  class="text-center">審議完成率B/A(%)</th>
              </tr>

              <tr v-for="(item,key) in table.data">
                <td>{{item.classifyId}}</td>
                <td class="text-right">{{item.cntReceipt11}}</td>
                <td class="text-right">{{item.cntDone11}}</td>
                <td class="text-right">{{((item.cntDone11 / item.cntReceipt11) * 100 ).toFixed(2)}}</td>
                <td class="text-right">{{item.cntReceipt12}}</td>
                <td class="text-right">{{item.cntDone12}}</td>
                <td class="text-right">{{((item.cntDone12 / item.cntReceipt12) * 100 ).toFixed(2)}}</td>
                <td class="text-right">{{item.cntReceipt22}}</td>
                <td class="text-right">{{item.cntDone22}}</td>
                <td class="text-right">{{((item.cntDone22 / item.cntReceipt22) * 100 ).toFixed(2)}}</td>
                <td class="text-right">{{item.cntReceipt31}}</td>
                <td class="text-right">{{item.cntDone31}}</td>
                <td class="text-right">{{((item.cntDone31 / item.cntReceipt31) * 100 ).toFixed(2)}}</td>
                <td class="text-right">{{item.cntReceipt11+item.cntReceipt12+item.cntReceipt22+item.cntReceipt31}}</td>
                <td class="text-right">{{item.cntDone11+item.cntDone12+item.cntDone22+item.cntDone31}}</td>
                <td class="text-right">{{(((item.cntDone11+item.cntDone12+item.cntDone22+item.cntDone31) / (item.cntReceipt11+item.cntReceipt12+item.cntReceipt22+item.cntReceipt31)) * 100 ).toFixed(2)}}</td>
                <!-- <td>{{cntReceipt11All += item.cntReceipt11}}{{cntReceipt12All += item.cntReceipt12}}
                           {{cntReceipt22All += item.cntReceipt22}}{{cntReceipt31All += item.cntReceipt31}}
                           {{cntDone11All += item.cntDone11}}{{cntDone12All += item.cntDone12}}
                           {{cntDone22All += item.cntDone22}}{{cntDone31All += item.cntDone31}}
                </td> -->
              </tr>
              <tr>
                <td>合計</td>
                <td class="text-right">{{cntReceipt11All}}</td>
                <td class="text-right">{{cntDone11All}}</td>
                <td class="text-right">{{((cntDone11All / cntReceipt11All) * 100 ).toFixed(2)}}</td>
                <td class="text-right">{{cntReceipt12All}}</td>
                <td class="text-right">{{cntDone12All}}</td>
                <td class="text-right">{{((cntDone12All / cntReceipt12All) * 100 ).toFixed(2)}}</td>
                <td class="text-right">{{cntReceipt22All}}</td>
                <td class="text-right">{{cntDone22All}}</td>
                <td class="text-right">{{((cntDone22All / cntReceipt22All) * 100 ).toFixed(2)}}</td>
                <td class="text-right">{{cntReceipt31All}}</td>
                <td class="text-right">{{cntDone31All}}</td>
                <td class="text-right">{{((cntDone31All / cntReceipt31All) * 100 ).toFixed(2)}}</td>
                <td class="text-right">{{cntReceipt11All+cntReceipt12All+cntReceipt22All+cntReceipt31All}}</td>
                <td class="text-right">{{cntDone11All+cntDone12All+cntDone22All+cntDone31All}}</td>
                <td class="text-right">{{(((cntDone11All+cntDone12All+cntDone22All+cntDone31All) / (cntReceipt11All+cntReceipt12All+cntReceipt22All+cntReceipt31All)) * 100 ).toFixed(2)}}</td>
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
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

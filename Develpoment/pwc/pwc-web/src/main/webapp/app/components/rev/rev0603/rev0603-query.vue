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
              <i-form-group-check class="col-6" :labelStar="true" label-cols="4" content-cols="8" label="本會收文日期:" :dual1="$v.receiptDateStart" :dual2="$v.receiptDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.receiptDateStart.$model" :state="$v.receiptDateStart"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.receiptDateEnd.$model" :state="$v.receiptDateEnd"></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-12" label-cols="2" content-cols="10" label="計畫類別:">
                <b-input-group>
                  <b-form-checkbox v-model="$v.check6.$model" :true-value="true" :false-value="false">可行性評估　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check7.$model" :true-value="true" :false-value="false">新興建設計畫　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check8.$model" :true-value="true" :false-value="false">基本設計　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check9.$model" :true-value="true" :false-value="false">修正計畫　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check10.$model" :true-value="true" :false-value="false">年度先期　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check11.$model" :true-value="true" :false-value="false">不含尚未結案　</b-form-checkbox>
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
                主管機關
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
            <table border="2" width="100%">
              <tr>
                <th width="20%" class="text-center">主管機關</th>
                <th width="20%" class="text-center">案件數</th>
                <th width="20%" class="text-center">計畫金額(億元)</th>
                <th width="20%" class="text-center">審議金額(億元)</th>
                <th width="20%" class="text-center">核定金額(億元)</th>
              </tr>

              <tr v-for="(item,key) in table.data">
                <td>{{item.adminOrgName}}</td>
                <td class="text-right">{{item.cntCase}}</td>
                <td class="text-right">{{item.planAmount}}</td>
                <td class="text-right">{{item.reviewAmount}}</td>
                <td class="text-right">{{item.approveAmount}}</td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                主辦機關
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
            <table border="2" width="100%">
              <tr>
                <th width="20%" class="text-center">主辦機關</th>
                <th width="20%" class="text-center">案件數</th>
                <th width="20%" class="text-center">計畫金額(億元)</th>
                <th width="20%" class="text-center">審議金額(億元)</th>
                <th width="20%" class="text-center">核定金額(億元)</th>
              </tr>

              <tr v-for="(item,key) in table1.data">
                <td>{{item.orgName}}</td>
                <td class="text-right">{{item.cntCase}}</td>
                <td class="text-right">{{item.planAmount}}</td>
                <td class="text-right">{{item.reviewAmount}}</td>
                <td class="text-right">{{item.approveAmount}}</td>
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
  name: 'rev0603Query',
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
      receiptDateStart:'109-01-01',
      receiptDateEnd:'110-12-31',
      check1: true,
      check2: '',
      check3: '',
      check4: '',
      check5: '',
      check6: '',
      check7: '',
      check8: '',
      check9: '',
      check10: '',
      check11: '',
      radio1: '',
      radio2: '',
    };

    const rules = {
      receiptDateStart:{notnull: required},
      receiptDateEnd:{notnull: required},
      check1: {},
      check2: {},
      check3: {},
      check4: {},
      check5: {},
      check6: {},
      check7: {},
      check8: {},
      check9: {},
      check10: {},
      check11: {},
      radio1: {},
      radio2: {},
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

    const table1 = reactive({
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
          adminOrgName : '海洋委員會',
          cntCase : 1,
          planAmount : 0.00,
          reviewAmount : 1.53,
          approveAmount: 1.53
        },
        {
          adminOrgName : '交通部',
          cntCase : 2,
          planAmount : 135.44,
          reviewAmount : 125.78,
          approveAmount: 125.78
        },
        {
          adminOrgName : '行政院環境保護署',
          cntCase : 1,
          planAmount : 0.00,
          reviewAmount : 36.10,
          approveAmount: 36.10
        },
        {
          adminOrgName : '經濟部',
          cntCase : 1,
          planAmount : 289.95,
          reviewAmount : 289.95,
          approveAmount: 289.95
        },
      ],
      table1.data = [
        {
          orgName : '行政院環境保護署',
          cntCase : 1,
          planAmount : 0.00,
          reviewAmount : 36.10,
          approveAmount: 36.10,
        },
        {
          orgName : '海洋委員會海巡署東南沙分署',
          cntCase : 1,
          planAmount : 0.00,
          reviewAmount : 1.53,
          approveAmount: 1.53,
        },
        {
          orgName : '交通部公路總局',
          cntCase : 1,
          planAmount : 128.64,
          reviewAmount : 118.97,
          approveAmount: 118.97,
        },
        {
          orgName : '台灣中油股份有限公司',
          cntCase : 1,
          planAmount : 289.95,
          reviewAmount : 289.95,
          approveAmount: 289.95
        },
        {
          orgName : '交通部高速公路局',
          cntCase : 1,
          planAmount : 6.80,
          reviewAmount : 6.80,
          approveAmount: 6.80,
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
      table1,
      toCreateView,
      toEditView,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

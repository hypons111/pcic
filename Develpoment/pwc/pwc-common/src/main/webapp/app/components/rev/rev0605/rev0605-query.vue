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
              <i-form-group-check class="col-12" :labelStar="true" label-cols="2" content-cols="10" label="本會收文日期:" :dual1="$v.receiptDateStart" :dual2="$v.receiptDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.receiptDateStart.$model" :state="$v.receiptDateStart"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.receiptDateEnd.$model" :state="$v.receiptDateEnd"></i-date-picker>
                  <b-input-group-text></b-input-group-text>
                  <b-form-checkbox v-model="$v.check1.$model" :true-value="true" :false-value="false">審查中　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check2.$model" :true-value="true" :false-value="false">需補正　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check3.$model" :true-value="true" :false-value="false">已補正　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check4.$model" :true-value="true" :false-value="false">函退　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check5.$model" :true-value="true" :false-value="false">審查完成　</b-form-checkbox>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-12" :labelStar="true" label-cols="2" content-cols="10" label="計算基準:">
                <b-form-radio v-model="$v.radio1.$model" name="some-radios" value="1">經費核列率(%)=本會建議經費(元)/屬本會審議範圍金額(元)</b-form-radio>
                <b-form-radio v-model="$v.radio2.$model" name="some-radios" value="2">經費核列率(%)=本會建議經費(元)/計畫提報經費(元)</b-form-radio>
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
                統計總表
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
                <th width="30%" class="text-center">類別</th>
                <th width="15%" class="text-center">件數</th>
                <th width="20%" class="text-center">函報經費(億元)</th>
                <th width="20%" class="text-center">核列經費(億元)</th>
                <th width="15%" class="text-center">核列百分比%</th>
              </tr>

              <tr v-for="(item,key) in table.data">
                <td class="text-center">{{item.type}}</td>
                <td class="text-right">{{item.cntCase}}</td>
                <td class="text-right">{{item.totalBudget}}</td>
                <td class="text-right">{{item.reviewTotalBudget}}</td>
                <td class="text-right">{{item.reviewPercentage}}</td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                案件清單
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
                <th width="8%" class="text-center">類別</th>
                <th width="50%" class="text-center">案件名稱</th>
                <th width="8%" class="text-center">案件狀態</th>
                <th width="8%" class="text-center">提報日期</th>
                <th width="8%" class="text-center">核定日期</th>
                <th width="6%" class="text-center">函報經費(億元)</th>
                <th width="6%" class="text-center">核列經費(億元)</th>
                <th width="6%" class="text-center">核列比</th>
              </tr>

              <tr v-for="(item,key) in table1.data">
                <td class="text-center">{{item.type}}</td>
                <td class="text-left">{{item.caseName}}</td>
                <td class="text-left">{{item.caseStatus}}</td>
                <td class="text-center">{{item.submitDate}}</td>
                <td class="text-center">{{item.approveDate}}</td>
                <td class="text-right">{{item.totalBudget}}</td>
                <td class="text-right">{{item.approveTotalBudget}}</td>
                <td class="text-right">{{item.reviewPercentage}}</td>
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
  name: 'rev0605Query',
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
          type : '水環境',
          cntCase : 3,
          totalBudget : 9.29,
          reviewTotalBudget : 5.98,
          reviewPercentage: '64.37%'
        },
        {
          type : '軌道',
          cntCase : 2,
          totalBudget : 527.21,
          reviewTotalBudget : 504.17,
          reviewPercentage: '95.62%'
        },
      ],
      table1.data = [
        {
          type :'水環境',
          caseName : '「旭川河及南榮河水質與環境改善規劃及細部設計計畫」基本設計報告書',
          caseStatus : '審查中',
          submitDate : '107/08/27',
          approveDate : '107/09/07',
          totalBudget: 2.86,
          approveTotalBudget : 0.00,
          reviewPercentage: '0.00%'
        },
        {
          type :'水環境',
          caseName : '「龍潭大池水質改善及水體環境營造規劃設計計畫」基本設計',
          caseStatus : '審查中',
          submitDate : '107/07/12',
          approveDate : '107/07/25',
          totalBudget: 0.00,
          approveTotalBudget : 0.00,
          reviewPercentage: '--'
        },
        {
          type :'水環境',
          caseName : '新北市中和地區污水下水道系統第二期工程第十標',
          caseStatus : '審查中',
          submitDate : '106/11/10',
          approveDate : '106/12/07',
          totalBudget: 6.43,
          approveTotalBudget : 5.98,
          reviewPercentage: '93.04%'
        },
        {
          type :'軌道',
          caseName : '「嘉義市區鐵路高架化計畫基本設計階段」基本設計',
          caseStatus : '審查中',
          submitDate : '108/03/11',
          approveDate : '108/04/16',
          totalBudget: 233.61,
          approveTotalBudget : 210.57,
          reviewPercentage: '90.14%'
        },
        {
          type :'軌道',
          caseName : '台南市區鐵路地下化計畫基本設計審議',
          caseStatus : '審查中',
          submitDate : '101/07/04',
          approveDate : '101/10/05',
          totalBudget: 293.60,
          approveTotalBudget : 293.60,
          reviewPercentage: '100.00%'
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

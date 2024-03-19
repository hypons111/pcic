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
            <b-form-row>
              <i-form-group-check class="col-12" :labelStar="true" label-cols="2" content-cols="10" label="計畫類別:">
                <b-input-group>
                  <b-form-checkbox v-model="$v.check6.$model" :true-value="true" :false-value="false">可行性評估　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check7.$model" :true-value="true" :false-value="false">新興建設計畫　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check8.$model" :true-value="true" :false-value="false">基本設計　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check9.$model" :true-value="true" :false-value="false">修正計畫　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check10.$model" :true-value="true" :false-value="false">年度先期　</b-form-checkbox>
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
                公共建設計畫總計
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
                <th width="5%" class="text-center">年度</th>
                <th width="5%" class="text-center">案件數</th>
                <th width="20%" class="text-center">函報總金額(億元)</th>
                <th width="20%" class="text-center">屬本會審議範圍之總金額(億元)</th>
                <th width="20%" class="text-center">本會建議總金額(億元)</th>
                <th width="20%" class="text-center">本會減列總金額(億元)</th>
                <th width="10%" class="text-center">核列百分比%</th>
              </tr>

              <tr v-for="(item,key) in table.data">
                <td class="text-center">{{item.year}}</td>
                <td class="text-right">{{item.cntCase}}</td>
                <td class="text-right">{{item.totalBudget}}</td>
                <td class="text-right">{{item.reviewTotalBudget}}</td>
                <td class="text-right">{{item.suggestTotalBudget}}</td>
                <td class="text-right">{{item.reduceTotalBudget}}</td>
                <td class="text-right">{{item.reviewPercentage}}</td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus && $v.check6.$model">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                可行性評估
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

        <div class="container" v-if="queryStatus && $v.check6.$model">
          <b-collapse v-model="stepVisible">
            <table border="2" width="100%">
              <tr>
                <th width="5%" class="text-center">年度</th>
                <th width="5%" class="text-center">案件數</th>
                <th width="20%" class="text-center">函報總金額(億元)</th>
                <th width="20%" class="text-center">屬本會審議範圍之總金額(億元)</th>
                <th width="20%" class="text-center">本會建議總金額(億元)</th>
                <th width="20%" class="text-center">本會減列總金額(億元)</th>
                <th width="10%" class="text-center">核列百分比%</th>
              </tr>

              <tr v-for="(item,key) in table.data">
                <td class="text-center">{{item.year}}</td>
                <td class="text-right">{{item.cntCase}}</td>
                <td class="text-right">{{item.totalBudget}}</td>
                <td class="text-right">{{item.reviewTotalBudget}}</td>
                <td class="text-right">{{item.suggestTotalBudget}}</td>
                <td class="text-right">{{item.reduceTotalBudget}}</td>
                <td class="text-right">{{item.reviewPercentage}}</td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus && $v.check7.$model">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                新興建設計畫
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

        <div class="container" v-if="queryStatus && $v.check7.$model">
          <b-collapse v-model="stepVisible">
            <table border="2" width="100%">
              <tr>
                <th width="5%" class="text-center">年度</th>
                <th width="5%" class="text-center">案件數</th>
                <th width="20%" class="text-center">函報總金額(億元)</th>
                <th width="20%" class="text-center">屬本會審議範圍之總金額(億元)</th>
                <th width="20%" class="text-center">本會建議總金額(億元)</th>
                <th width="20%" class="text-center">本會減列總金額(億元)</th>
                <th width="10%" class="text-center">核列百分比%</th>
              </tr>

              <tr v-for="(item,key) in table.data">
                <td class="text-center">{{item.year}}</td>
                <td class="text-right">{{item.cntCase}}</td>
                <td class="text-right">{{item.totalBudget}}</td>
                <td class="text-right">{{item.reviewTotalBudget}}</td>
                <td class="text-right">{{item.suggestTotalBudget}}</td>
                <td class="text-right">{{item.reduceTotalBudget}}</td>
                <td class="text-right">{{item.reviewPercentage}}</td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus && $v.check8.$model">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                基本設計
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

        <div class="container" v-if="queryStatus && $v.check8.$model">
          <b-collapse v-model="stepVisible">
            <table border="2" width="100%">
              <tr>
                <th width="5%" class="text-center">年度</th>
                <th width="5%" class="text-center">案件數</th>
                <th width="20%" class="text-center">函報總金額(億元)</th>
                <th width="20%" class="text-center">屬本會審議範圍之總金額(億元)</th>
                <th width="20%" class="text-center">本會建議總金額(億元)</th>
                <th width="20%" class="text-center">本會減列總金額(億元)</th>
                <th width="10%" class="text-center">核列百分比%</th>
              </tr>

              <tr v-for="(item,key) in table.data">
                <td class="text-center">{{item.year}}</td>
                <td class="text-right">{{item.cntCase}}</td>
                <td class="text-right">{{item.totalBudget}}</td>
                <td class="text-right">{{item.reviewTotalBudget}}</td>
                <td class="text-right">{{item.suggestTotalBudget}}</td>
                <td class="text-right">{{item.reduceTotalBudget}}</td>
                <td class="text-right">{{item.reviewPercentage}}</td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus && $v.check9.$model">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                修正計畫
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

        <div class="container" v-if="queryStatus && $v.check9.$model">
          <b-collapse v-model="stepVisible">
            <table border="2" width="100%">
              <tr>
                <th width="5%" class="text-center">年度</th>
                <th width="5%" class="text-center">案件數</th>
                <th width="20%" class="text-center">函報總金額(億元)</th>
                <th width="20%" class="text-center">屬本會審議範圍之總金額(億元)</th>
                <th width="20%" class="text-center">本會建議總金額(億元)</th>
                <th width="20%" class="text-center">本會減列總金額(億元)</th>
                <th width="10%" class="text-center">核列百分比%</th>
              </tr>

              <tr v-for="(item,key) in table.data">
                <td class="text-center">{{item.year}}</td>
                <td class="text-right">{{item.cntCase}}</td>
                <td class="text-right">{{item.totalBudget}}</td>
                <td class="text-right">{{item.reviewTotalBudget}}</td>
                <td class="text-right">{{item.suggestTotalBudget}}</td>
                <td class="text-right">{{item.reduceTotalBudget}}</td>
                <td class="text-right">{{item.reviewPercentage}}</td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left" v-if="queryStatus && $v.check10.$model">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                年度先期
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

        <div class="container" v-if="queryStatus && $v.check10.$model">
          <b-collapse v-model="stepVisible">
            <table border="2" width="100%">
              <tr>
                <th width="5%" class="text-center">年度</th>
                <th width="5%" class="text-center">案件數</th>
                <th width="20%" class="text-center">函報總金額(億元)</th>
                <th width="20%" class="text-center">屬本會審議範圍之總金額(億元)</th>
                <th width="20%" class="text-center">本會建議總金額(億元)</th>
                <th width="20%" class="text-center">本會減列總金額(億元)</th>
                <th width="10%" class="text-center">核列百分比%</th>
              </tr>

              <tr v-for="(item,key) in table.data">
                <td class="text-center">{{item.year}}</td>
                <td class="text-right">{{item.cntCase}}</td>
                <td class="text-right">{{item.totalBudget}}</td>
                <td class="text-right">{{item.reviewTotalBudget}}</td>
                <td class="text-right">{{item.suggestTotalBudget}}</td>
                <td class="text-right">{{item.reduceTotalBudget}}</td>
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
  name: 'rev0604Query',
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
          year : 109,
          cntCase : 364,
          totalBudget : '136,560.24',
          reviewTotalBudget : '137,119.19',
          suggestTotalBudget : '137,099.93',
          reduceTotalBudget : '19.27',
          reviewPercentage: '100.40%'
        },
        {
          year : 110,
          cntCase : 5,
          totalBudget : 425.40,
          reviewTotalBudget : 453.36,
          suggestTotalBudget : 453.36,
          reduceTotalBudget : 0.00,
          reviewPercentage: '106.57%'
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
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

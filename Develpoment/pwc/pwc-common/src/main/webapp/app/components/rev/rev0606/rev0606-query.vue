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
              <i-form-group-check :labelStar="true"  label="建檔日:" :dual1="$v.createDateStart" :dual2="$v.createDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.createDateStart.$model" :state="$v.createDateStart"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.createDateEnd.$model" :state="$v.createDateEnd"></i-date-picker>
                </b-input-group>
              </i-form-group-check>
              <i-form-group-check  label="收文日:" :dual1="$v.receiptDateStart" :dual2="$v.receiptDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.receiptDateStart.$model" :state="$v.receiptDateStart"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.receiptDateEnd.$model" :state="$v.receiptDateEnd"></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="案件類別:" :item="$v.projectCode">
                <b-form-select v-model="$v.projectCode.$model" :options="options.projectCodeList"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :labelStar="true" label="工程類別:" :item="$v.projectType">
                <i-group-select-checkbox
                  v-model="$v.projectType.$model"
                  :state="validateState($v.projectType)"
                  :options="options.projectTypeList"
                  :isDropDown="true">
                </i-group-select-checkbox>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-12" :labelStar="true" label-cols="2" content-cols="10" label="案件狀態:">
                <b-input-group>
                  <b-form-checkbox v-model="$v.check1.$model" :true-value="true" :false-value="false">審查中　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check2.$model" :true-value="true" :false-value="false">需補正　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check3.$model" :true-value="true" :false-value="false">已補正　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check4.$model" :true-value="true" :false-value="false">函退　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check5.$model" :true-value="true" :false-value="false">審查完成　</b-form-checkbox>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-12" :labelStar="true" label-cols="2" content-cols="10" label="詳細區域:">
                <b-input-group>
                  <b-form-checkbox v-model="$v.check6.$model" :true-value="true" :false-value="false"></b-form-checkbox>
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

      </div>
    </section>
    <!-- 匯出ods 按鈕 -->
    <section>
      <div class="container mt-1 mb-1" v-if="queryStatus">
        <b-button variant="info" @click="toSortView">匯出ods</b-button>
      </div>
    </section>
    <!-- i-table -->
    <section>
      <div class="container" v-if="queryStatus">
        <i-table
          class="table-sm"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :fields="table.fields"
          :totalItems="table.totalItems"
          :is-server-side-paging="true"
          @changePagination="handlePaginationChanged($event)"
        >
          <template #cell(action)="row">
            <i-button class="mt-1" type="binoculars" @click="toEditView(row.item)"></i-button>
          </template>
        </i-table>
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
  name: 'rev0606Query',
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
      createDateStart:'',
      createDateEnd:'',
      receiptDateStart:'',
      receiptDateEnd:'',
      projectCode:'',
      projectType:'',
      check1: true,
      check2: '',
      check3: '',
      check4: '',
      check5: '',
      check6: '',
    };

    const rules = {
      createDateStart:{notnull: required},
      createDateEnd:{notnull: required},
      receiptDateStart:{notnull: required},
      receiptDateEnd:{notnull: required},
      projectCode:{},
      projectType:{notnull: required},
      check1: {},
      check2: {},
      check3: {},
      check4: {},
      check5: {},
      check6: {},
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

    const options = reactive({
      projectCodeList: [
        { value: '1', text: '公路' },
        { value: '2', text: '水環境' },
        { value: '3', text: '城鄉' },
        { value: '4', text: '軌道' },
      ],
      projectTypeList: [
        {
          parents: '跨海橋梁',
          child: [
            { value: '1', text: '結構工程' },
            { value: '2', text: '引道工程' },
            { value: '3', text: '結構工程-下部結構' },
            { value: '4', text: '結構工程-上部結構' },
            { value: '5', text: '結構工程-下部結構-基礎' },
            { value: '6', text: '結構工程-下部結構-橋墩' },
            { value: '7', text: '結構工程-下部結構-基礎-樁基礎' },
            { value: '8', text: '結構工程-下部結構-橋墩-混擬土橋墩' },
          ],
        },
      ],
    });

    //轉到新增頁面
    const toCreateView = () => {
      navigateByNameAndParams('lic0701-edit-info', { formStatusForward: 'create' ,isNotKeepAlive: true  });
    };

    //轉到檢視頁面
    const toEditView = (unitPrice: any) => {
      navigateByNameAndParams('rev0606-edit-info', { formStatusForward: 'modify', unitPrice: unitPrice, isNotKeepAlive: true });
    };

    const table = reactive({
      fields: [
        {
          key: 'projectCode',
          label: '案件類別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 5%',
        },
        {
          key: 'projectType',
          label: '工程類別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 5%',
        },
        {
          key: 'unitPriceName',
          label: '單位造價項目',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 15%',
        },
        {
          key: 'qty',
          label: '數量',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width: 5%',
        },
        {
          key: 'unit',
          label: '單位',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 5%' ,
        },
        {
          key: 'amount',
          label: '合計經費(萬元)A',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'totalAreaLength',
          label: '總面積或長度B',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'avgUnitPrice',
          label: '本案平均單價(萬元)A/B',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width: 14%' ,
        },
        {
          key: 'avgPrice',
          label: '工項平均價格(萬元)A/C',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width: 14%' ,
        },
        {
          key: 'cntCase',
          label: '案件數C',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width: 5%' ,
        },
        {
          key: 'action',
          label: '動作',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width:8%',
        },
      ],
      data: undefined,
      totalItems: 3,
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
          projectCode : '公路',
          projectType : '--',
          unitPriceName : '--',
          qty : '--',
          unit: '--',
          amount: '147,000',
          totalAreaLength: 1010,
          avgUnitPrice: 145.54,
          avgPrice: '--',
          cntCase: 1,
        },
        {
          projectCode : '公路',
          projectType : '跨海橋梁',
          unitPriceName : '--',
          qty : '--',
          unit: '--',
          amount: '147,000',
          totalAreaLength: 1010,
          avgUnitPrice: 145.54,
          avgPrice: '--',
          cntCase: 1,
        },
        {
          projectCode : '公路',
          projectType : '跨海橋梁',
          unitPriceName : '結構工程',
          qty : '--',
          unit: '--',
          amount: '147,000',
          totalAreaLength: 1010,
          avgUnitPrice: '--',
          avgPrice: '--',
          cntCase: 1,
        },
        {
          projectCode : '公路',
          projectType : '跨海橋梁',
          unitPriceName : '結構工程-下部結構',
          qty : '--',
          unit: '--',
          amount: '147,000',
          totalAreaLength: 1010,
          avgUnitPrice: '--',
          avgPrice: '--',
          cntCase: 1,
        },
        {
          projectCode : '公路',
          projectType : '跨海橋梁',
          unitPriceName : '結構工程-下部結構-基礎',
          qty : '7',
          unit: '座',
          amount: '70,000',
          totalAreaLength: 1010,
          avgUnitPrice: '--',
          avgPrice: '70,000',
          cntCase: 1,
        },
        {
          projectCode : '公路',
          projectType : '跨海橋梁',
          unitPriceName : '結構工程-下部結構-橋墩',
          qty : '7',
          unit: '座',
          amount: '77,000',
          totalAreaLength: 1010,
          avgUnitPrice: '--',
          avgPrice: '77,000',
          cntCase: 1,
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
      options,
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

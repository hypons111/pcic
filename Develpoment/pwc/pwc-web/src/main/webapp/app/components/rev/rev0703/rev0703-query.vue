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
              <i-form-group-check label="案名:">
                <b-form-input v-model.trim="$v.caseName.$model" placeholder="請輸入案件名稱關鍵字"></b-form-input>
              </i-form-group-check>
              <i-form-group-check label="案號:">
                <b-form-input v-model="$v.caseNo.$model" placeholder="請輸入案件編號關鍵字"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="審議類別:">
                <b-form-select v-model.trim="$v.reviewType.$model" :options="queryOptions.reviewType"></b-form-select>
              </i-form-group-check>
              <i-form-group-check label="工程類別:">
                <b-form-select v-model="$v.projectType.$model" :options="queryOptions.projectType" ></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="主管機關:">
                <b-form-input v-model.trim="$v.adminOrgName.$model" placeholder="請輸入關鍵字"></b-form-input>
              </i-form-group-check>
              <i-form-group-check label="主辦機關:">
                <b-form-input v-model="$v.orgName.$model" placeholder="請輸入關鍵字"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="計畫類別:">
                <b-form-select v-model.trim="$v.planType.$model" :options="queryOptions.planType"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-6" :labelStar="true" label-cols="4" content-cols="8" label="是否前膽:">
                <b-form-radio v-model="$v.radio1.$model" name="some-radios1" value=true>全部　</b-form-radio>
                <b-form-radio v-model="$v.radio2.$model" name="some-radios1" value=true>是　</b-form-radio>
                <b-form-radio v-model="$v.radio3.$model" name="some-radios1" value=true>否　</b-form-radio>
              </i-form-group-check>
              <i-form-group-check class="col-6" :labelStar="true" label-cols="4" content-cols="8" label="是否逾期:">
                <b-form-radio v-model="$v.radio4.$model" name="some-radios2" value=true>全部　</b-form-radio>
                <b-form-radio v-model="$v.radio5.$model" name="some-radios2" value=true>是　</b-form-radio>
                <b-form-radio v-model="$v.radio6.$model" name="some-radios2" value=true>否　</b-form-radio>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-12" :labelStar="true" label-cols="2" content-cols="10" label="案件日期:">
                <b-input-group>
                  <b-form-checkbox v-model="$v.check6.$model" :true-value="true" :false-value="false">不限　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check7.$model" :true-value="true" :false-value="false">本會收文日　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check8.$model" :true-value="true" :false-value="false">建檔日　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check9.$model" :true-value="true" :false-value="false">預定函報日　</b-form-checkbox>
                  <b-form-checkbox v-model="$v.check10.$model" :true-value="true" :false-value="false">審查完成日　</b-form-checkbox>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="本會收文日期:" :dual1="$v.receiptDateStart" :dual2="$v.receiptDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.receiptDateStart.$model" :state="$v.receiptDateStart"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.receiptDateEnd.$model" :state="$v.receiptDateEnd"></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-12" label-cols="3" content-cols="7" label="本會審議範圍總經費(萬元):">
                <table>
                  <tr>
                    <td><b-form-input v-model.trim="$v.money1.$model"></b-form-input></td>
                    <td>－</td>
                    <td><b-form-input v-model.trim="$v.money2.$model"></b-form-input></td>
                  </tr>
                </table>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="案件科別:">
                <b-form-select v-model.trim="$v.case.$model" :options="queryOptions.case"></b-form-select>
              </i-form-group-check>
              <i-form-group-check label="承辦人:" :item="$v.people">
                <b-form-input v-model.trim="$v.people.$model" placeholder="請輸入承辦人"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="計畫狀態:">
                <b-form-select v-model.trim="$v.status.$model" :options="queryOptions.status"></b-form-select>
              </i-form-group-check>
              <i-form-group-check label="發包前檢核狀態:">
                <b-form-select v-model.trim="$v.check.$model" :options="queryOptions.check"></b-form-select>
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
  name: 'lic0701Query',
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
      caseName:'',
      caseNo:'',
      adminOrgName:'',
      orgName:'',
      receiptDateStart:'',
      receiptDateEnd:'',
      radio1:true,
      radio2:'',
      radio3:'',
      radio4:true,
      radio5:'',
      radio6:'',
      check6:true,
      check7:'',
      check8:'',
      check9:'',
      check10:'',
      reviewType:'',
      planType:'',
      projectType:'',
      money1:'',
      money2:'',
      people:'',
      case:'',
      status:'',
      check:'',
    };

    const rules = {
      caseName:{},
      caseNo:{},
      adminOrgName:{},
      orgName:{},
      receiptDateStart:{},
      receiptDateEnd:{},
      radio1:{},
      radio2:{},
      radio3:{},
      radio4:{},
      radio5:{},
      radio6:{},
      check6:{},
      check7:{},
      check8:{},
      check9:{},
      check10:{},
      reviewType:{},
      planType:{},
      projectType:{},
      money1:{},
      money2:{},
      people:{},
      case:{},
      status:{},
      check:{},
    };

    // 下拉選單選項
    const queryOptions = reactive({
      reviewType: [
        { value: '', text: '全部' },
        { value: 'A', text: '計畫評估' },
        { value: 'B', text: '預定函送' },
        { value: 'C', text: '基本設計' },
        { value: 'D', text: '年度先期' },
      ],
      projectType: [
        { value: '', text: '全部' },
        { value: 'A', text: 'A：公路' },
        { value: 'B', text: 'B：教育' },
      ],
      planType: [
        { value: '', text: '工共建設計畫' },
        { value: '1', text: '可行性評估' },
        { value: '2', text: '新興建設計畫' },
        { value: '3', text: '基本設計' },
        { value: '4', text: '修正計畫' },
        { value: '5', text: '年度先期' },
      ],
      status: [
        { value: '', text: '全部' },
        { value: '1', text: '計畫狀態1' },
        { value: '2', text: '計畫狀態2' },
        { value: '3', text: '計畫狀態3' },
      ],
      check: [
        { value: '', text: '請選擇' },
        { value: '1', text: '是' },
        { value: '2', text: '否' },
      ],
      case: [
        { value: '', text: '全部' },
        { value: '1', text: '案件科別1' },
        { value: '2', text: '案件科別1' },
      ],
    });

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const table = reactive({
      fields: [
        {
          key: 'caseNo',
          label: '案號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 35%',
        },
        {
          key: 'caseName',
          label: '案件名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 6%',
        },
        {
          key: 'reviewType',
          label: '審議類別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%',
        },
        {
          key: 'projectType',
          label: '工程建設類別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 25%',
        },
        {
          key: 'planType',
          label: '計畫類別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'adminOrgName',
          label: '主管機關',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'orgName',
          label: '主辦機關',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'createDate',
          label: '建檔日',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'receiptDate',
          label: '本會收文日',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'receiptNo',
          label: '本會收文號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'govApprovalDate',
          label: '院核定日',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'govApprovalNo',
          label: '院核定文號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'isForwardPlan',
          label: '是否前瞻',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'planStartDate',
          label: '計畫開始期程',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'planEndDate',
          label: '計畫結束期程',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'reviewDoneDate',
          label: '審查完成日',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'contactPersonName',
          label: '承辦人',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'planCheckStatus',
          label: '發包前檢核狀態',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },

        {
          key: 'action',
          label: '動作',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width:10%',
        },
      ],
      data: undefined,
      totalItems: 3,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    //顯示查詢條件區
    let stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //轉到新增頁面
    const toCreateView = () => {
    };

    //轉到編輯頁面
    const toEditView = (licWorkshopMember: any) => {
    };

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
          caseNo : '1103G001080000',
          caseName : '國立臺灣劇曲學院內湖校區「劇藝教學大樓」30%基本設計',
          reviewType : '基本設計',
          projectType : '教育',
          planType : '公共建設計畫',
          adminOrgName: '教育部',
          orgName: '教育部',
          createDate: '110/01/08',
          receiptDate: '109/12/15',
          receiptNo: '1090031266',
          govApprovalDate: '107/02/01',
          govApprovalNo:'1070200153',
          isForwardPlan:'否',
          planStartDate:'107/01/01',
          planEndDate:'112/12/01',
          reviewDoneDate:'109/12/23',
          contactPersonName:'林昭葳',
          planCheckStatus:'未完成',
        },
        {
          caseNo : '1093G012020000',
          caseName : '國立臺中教育大學「體育館新建工程」基本設計',
          reviewType : '基本設計',
          projectType : '教育',
          planType : '公共建設計畫',
          adminOrgName: '教育部',
          orgName: '教育部',
          createDate: '109/12/01',
          receiptDate: '109/10/15',
          receiptNo: '1090025947',
          govApprovalDate: '107/04/16',
          govApprovalNo:'1070200460',
          isForwardPlan:'否',
          planStartDate:'109/01/01',
          planEndDate:'112/12/01',
          reviewDoneDate:'109/10/27',
          contactPersonName:'林昭葳',
          planCheckStatus:'未完成',
        },
        {
          caseNo : '1093G012010002',
          caseName : '國立中興大學「獸醫教學大樓」基本設計',
          reviewType : '基本設計',
          projectType : '教育',
          planType : '公共建設計畫',
          adminOrgName: '教育部',
          orgName: '教育部',
          createDate: '109/12/01',
          receiptDate: '109/10/13',
          receiptNo: '1090025602',
          govApprovalDate: '108/12/25',
          govApprovalNo:'1080054633',
          isForwardPlan:'否',
          planStartDate:'109/01/01',
          planEndDate:'112/12/01',
          reviewDoneDate:'109/12/21',
          contactPersonName:'林昭葳',
          planCheckStatus:'未完成',
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
      queryOptions,
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

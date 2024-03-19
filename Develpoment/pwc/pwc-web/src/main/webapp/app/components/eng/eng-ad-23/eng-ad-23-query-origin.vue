備份一下改版前的engAD23 query

<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件Origin
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
              <i-form-group-check :label="$t('label.eng.courseName')" :item="$v.courseName">
                <b-form-input
                  v-model="$v.courseName.$model"
                  :state="validateState($v.courseName)"
                  placeholder="請輸入課程名稱"
                ></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.eng.date_Start')" :bottomPlain="!($v.date_Start.$invalid || $v.date_End.$invalid)">
                <b-input-group>
                  <i-date-picker v-model="$v.date_Start.$model" :state="$v.date_Start"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.date_End.$model" :state="$v.date_End"> </i-date-picker>
                </b-input-group>
                <dual-form-invalid-feedback
                  :dual-invalid-feedback="[$v.date_Start.$errors, $v.date_End.$errors]"
                ></dual-form-invalid-feedback>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.eng.belongUnit')" :item="$v.belongUnit">
                <b-form-input
                  v-model="$v.belongUnit.$model"
                  :state="validateState($v.belongUnit)"
                  placeholder="請輸入主辦單位"
                ></b-form-input>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.eng.checkDate_1')"
                :bottomPlain="!($v.checkDate_1.$invalid || $v.checkDate_1to.$invalid)"
              >
                <b-input-group>
                  <i-date-picker v-model="$v.checkDate_1.$model" :state="$v.checkDate_1"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.checkDate_1to.$model" :state="$v.checkDate_1to"> </i-date-picker>
                </b-input-group>
                <dual-form-invalid-feedback
                  :dual-invalid-feedback="[$v.checkDate_1.$errors, $v.checkDate_1to.$errors]"
                ></dual-form-invalid-feedback>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.eng.docNo')">
                <b-form-input v-model="$v.docNo.$model" :state="validateState($v.docNo)" placeholder="請輸入核備文號"></b-form-input>
              </i-form-group-check>
              <i-form-group-check label-cols-md="4" content-cols-md="6" :label="$t('label.eng.reviewStatus')">
                <b-form-select v-model="$v.reviewStatus.$model" :state="validateState($v.reviewStatus)" :options="status"> </b-form-select>
              </i-form-group-check>
              <b-form-group class="col-sm-2" label-sr-only label-align-md="right" content-cols-md="12"> </b-form-group>
              <b-form-group class="col-sm-4" label-sr-only label-align-md="right" content-cols-md="12">
                <i-button type="search" v-promise-btn="{ promise: dataPromise }" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-form-group>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>
    <section>
      <div class="container" v-show="showDataBool">
        <!-- 放itable -->
        <i-table
          class="table-sm"
          :itemsUndefinedBehavior="'loading'"
          :items="resTable.data"
          :fields="resTable.fields"
          :totalItems="resTable.totalItems"
          :is-server-side-paging="true"
          @changePagination="handlePaginationChanged($event)"
        >
          <template v-slot:cell(date_Start)="slot">
            {{ formatDate(slot.item.date_Start, '/') }}<br />
            至<br />
            {{ formatDate(slot.item.date_End, '/') }}
          </template>

          <template v-slot:cell(contact_Name)="slot">
            聯絡人:{{ slot.item.contact_Name }}<br />
            電話:{{ slot.item.contact_Tel }}<br />
            信箱:{{ slot.item.contact_Mail }}<br />
          </template>
          <template v-slot:cell(status)="row">
            {{ statusValueToText(row.item.status) }}
          </template>

          <template #cell(action)="row">
            <i-button type="binoculars" @click="toEditView(row.item)"></i-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { reactive, ref, onActivated } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'engAd23Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props, context) {
    enum FormStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }
    const stepVisible = ref(true);
    const dataPromise = ref(null);
    const showDataBool = ref(false);

    onActivated(() => {
      reset();
    });

    const queryConditionDefault = {
      courseName: '',
      belongUnit: '',
      date_Start: '',
      date_End: '',
      checkDate_1: '',
      checkDate_1to: '',
      docNo: '',
      reviewStatus: 0,
    };
    let form = reactive({
      courseName: '',
      belongUnit: '',
      date_Start: '',
      date_End: '',
      checkDate_1: '',
      checkDate_1to: '',
      docNo: '',
      reviewStatus: 0,
    });

    const rules = reactive({
      courseName: {},
      belongUnit: {},
      date_Start: {},
      date_End: {},
      checkDate_1: {},
      checkDate_1to: {},
      docNo: {},
      reviewStatus: {},
    });
    const { $v, checkValidity, reset } = useValidation(rules, form, queryConditionDefault);

    const status = ref([
      { value: 0, text: '請選擇' },
      { value: 1, text: '未審' },
      { value: 2, text: '待審' },
      { value: 3, text: '已審核(含批退)' },
      { value: 4, text: '批退' },
    ]);
    const resTable = reactive({
      fields: [
        {
          key: 'index',
          label: '項次',
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-left',
          tdClass: 'text-left align-middle ',
        },
        {
          key: 'belongOf',
          label: '主辦單位',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'name',
          label: '課程名稱',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'date_Start',
          label: '課程日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'contact_Name',
          label: '聯絡資訊',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'status',
          label: '簽核狀態',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'docNo',
          label: '核備文號',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: '動作',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    function toEditView(rowData) {
      // context.emit('changeComponent', 'engAd23EditReview', rowData);
      navigateByNameAndParams('engAd23EditReview', { course_H: rowData });
    }

    function handleQuery() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          showDataBool.value = true;
          resTable.data = courseHeadArr;
          resTable.totalItems = courseHeadArr.length;
        }
      });
    }
    function handlePaginationChanged(event) {
      // console.log('handlePaginationChanged: ', event);
    }
    function statusValueToText(value) {
      return status.value.find(element => element.value === value).text;
    }

    //寫死的假data
    let courseHeadArr = [];
    let courseHead = {
      ID: '2021040150',
      belongOf: '工業技術研究院',
      name: '2021【電網學校x實務菁英培訓】系列一 電力技術實務班+ 系列二電網規劃設計工程師班',
      date_Start: new Date('2021/05/07'),
      date_End: new Date('2021/07/08'),
      place: '國立中央大學客家學院大樓',
      webUrl: 'https://www.google.com/',
      contact_Name: '葉雅芬',
      contact_Tel: '0963009266',
      contact_Mail: 'yyfen@itri.org.tw',
      status: 1, //簽核狀態
    };

    let courseHead2 = {
      ID: '2021050009',
      belongOf: '社團法人中華民國環境分析學會',
      name: '有機物的分析技術（一）',
      date_Start: new Date('2021/07/08'),
      date_End: new Date('2021/07/08'),
      place: '國立中央大學客家學院大樓',
      webUrl: 'https://www.google.com/',
      contact_Name: '陳萱如',
      contact_Tel: '(03)422-8621',
      contact_Mail: 'ceas@ms22.hinet.net',
      status: 1, //簽核狀態
    };
    courseHeadArr.push(courseHead);
    courseHeadArr.push(courseHead2);

    return {
      $v,
      status,
      stepVisible,
      dataPromise,
      resTable,
      showDataBool,
      reset,
      validateState,
      handleQuery,
      handlePaginationChanged,
      statusValueToText,
      formatDate,
      toEditView,
    };
  },
};
</script>

<style></style>

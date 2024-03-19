<template>
  <div>
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
            <!-- {{$v}}<br>
            {{$v.courseName}}<br>
            {{$v.courseStartDate}}<br>
            {{$v.courseEndDate}}<br>
            {{$v.organizer}}<br>
            {{$v.approvalNum}}<br>
            {{$v.approvalStatus}}<br> -->
            <!-- 課程名稱 課程日期 -->
            <b-form-row>
              <i-form-group-check :label="$t('label.course')+$t('label.name')+'：'" :item="$v.courseName">
                <b-form-input
                  v-model="$v.courseName.$model"
                  :state="validateState($v.courseName)"
                  placeholder="請輸入課程名稱"
                ></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.course')+$t('label.date')+'：'" :dual1="$v.courseStartDate" :dual2="$v.courseEndDate">
                <b-input-group>
                  <i-date-picker v-model="$v.courseStartDate.$model" :state="$v.courseStartDate"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.courseEndDate.$model" :state="$v.courseEndDate"> </i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <!-- 主辦單位 核備文號 -->
            <b-form-row>
              <i-form-group-check :label="$t('label.organizer')+'：'" :item="$v.organizer">
                <b-form-input
                  v-model="$v.organizer.$model"
                  :state="validateState($v.organizer)"
                  placeholder="請輸入主辦單位"
                ></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.docNo')+'：'" :item="$v.approvalNum">
                <b-form-input
                  v-model="$v.approvalNum.$model"
                  :state="validateState($v.approvalNum)"
                  placeholder="請輸入核備文號"
                ></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!-- 審核狀態-->
            <b-form-row>
              <i-form-group-check :label="$t('label.approved')+$t('label.status')+'：'" :item="$v.approvalStatus">
                <b-form-select v-model="$v.approvalStatus.$model" :state="validateState($v.approvalStatus)" :options="status">
                </b-form-select>
              </i-form-group-check>

              <b-col cols="2"></b-col>
              <b-col offset-sm="0" cols="4">
                <i-button type="search" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>
    <section>
      <div class="container pt-1">
        <i-button type="file-earmark-plus" @click="toCreateView"></i-button>
      </div>
    </section>
    <!-- itable -->
    <section>
      <div class="container" v-show="queryButtonGo">
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
            <i-button type="clipboard-x" class="mt-1"></i-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
// import { required, minLength,minValue } from 'vuelidate/lib/validators'
import { onMounted, reactive, Ref, ref, onActivated, computed, watch, toRef } from '@vue/composition-api';
import beginDateLessThanEndDate from '@/shared/validators/with-message/begin-date-less-than-end-date';
import { formatDate, parseDate } from '@/shared/date/minguo-calendar-utils';
import { navigateByNameAndParams } from '@/router/router';
import i18n from '@/shared/i18n';

export default {
  name: 'engAd22Query',
  //從上層傳來的是否讀取的資訊
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
    const isReloadProp = toRef(props, 'isReload');

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      } else {
        reset();
      }
    });
    let queryButtonGo = ref(false); //控制查詢結果欄位，按下查詢再顯示
    let stepVisible = ref(true); //隱藏顯示查詢欄位
    //let status = ref(['全部', '批退', '未審', '待審', '已審核']);//核准狀態
    let status = ref([
      { value: 0, text: '批退' },
      { value: 1, text: '未審' },
      { value: 2, text: '待審' },
      { value: 3, text: '已審核' },
    ]);
    /* 定義驗證的規則與返回的東西 */
    //查詢條件
    let queryCourseCondition = reactive({
      courseName: '',
      courseStartDate: '',
      courseEndDate: '',
      organizer: '',
      approvalNum: '',
      approvalStatus: '',
    });
    //表單物件
    const queryCourseConditionDefault = {
      courseName: '',
      courseStartDate: null,
      courseEndDate: null,
      organizer: '',
      approvalNum: '',
      approvalStatus: '',
    };
    // 表單物件驗證規則
    const rules = reactive({
      courseName: {},
      courseStartDate: {},
      courseEndDate: { beginDateLessThanEndDate: beginDateLessThanEndDate(undefined) },
      organizer: {},
      approvalNum: {},
      approvalStatus: {},
    });

    //watch start date 以改變規則
    // watch(queryCourseCondition.courseStartDate, newValue => {
    // console.log('new courseStartDate',newValue)
    // rules.courseEndDate.beginDateLessThanEndDate = beginDateLessThanEndDate(newValue.courseStartDate);
    // });

    //開始驗證
    const { $v, checkValidity, reset } = useValidation(rules, queryCourseCondition, queryCourseConditionDefault);

    //儲存查詢到的結果
    const resTable = reactive({
      fields: [
        {
          key: 'index',
          label: i18n.t('label.index'),
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
        },
        {
          key: 'belongOf',
          label: i18n.t('label.organizer'),
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'name',
          label: i18n.t('label.course').toString()+i18n.t('label.name').toString(),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'date_Start',
          label: i18n.t('label.course').toString()+i18n.t('label.date').toString(),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'contact_Name',
          label: i18n.t('label.contactInfo'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'status',
          label: i18n.t('label.approved').toString()+i18n.t('label.status').toString(),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'docNo',
          label: i18n.t('label.docNo'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    //查詢的結果
    let dataPromise = ref(null);

    function handleQuery() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          queryButtonGo.value = true;
          resTable.data = courseHeadArr;
          resTable.totalItems = courseHeadArr.length;
        }
      });
    }

    const toEditView = params => {
      navigateByNameAndParams('engAd22EditInfo', { course_H: params, formStatus: FormStatusEnum.MODIFY });
    };
    const toCreateView = () => {
      navigateByNameAndParams('engAd22EditInfo', { course_H: {}, formStatus: FormStatusEnum.CREATE });
    };

    //換頁用的,等與後端接再說
    function handlePaginationChanged(event) {
      // console.log('handlePaginationChanged: ', event);
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

    function statusValueToText(value) {
      return status.value.find(element => element.value === value).text;
    }
    return {
      stepVisible,
      status,
      dataPromise,
      $v,
      resTable,
      queryButtonGo,
      validateState,
      handleQuery,
      reset,
      toEditView,
      statusValueToText,
      formatDate,
      toCreateView,
    };
  },
};
</script>
<style scoped></style>

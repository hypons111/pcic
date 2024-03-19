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
        <div class="card-body col-12">
          <b-collapse v-model="stepVisible">
            <b-form-row>
              <i-form-group-check :label="$t('label.innovateName')" :item="$v.innovateName">
                <b-form-input v-model="$v.innovateName.$model" trim></b-form-input>
              </i-form-group-check>

              <i-form-group-check :label="$t('label.companyName')" :item="$v.companyName">
                <b-form-input v-model="$v.companyName.$model"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.communicateOrganName')" :item="$v.communicateOrganName">
                <b-form-input v-model="$v.communicateOrganName.$model"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.communicateTime')" :dual1="$v.communicateDateStart" :dual2="$v.communicateDateEnd">
                <b-input-group>
                  <i-date-picker
                    id="example-datepicker1"
                    v-model="$v.communicateDateStart.$model"
                    :disabled-date="notAfterCommunicateDateEnd"
                    :state="$v.communicateDateStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker
                    id="example-datepicker2"
                    v-model="$v.communicateDateEnd.$model"
                    :state="$v.communicateDateEnd"
                    :disabled-date="notBeforeCommunicateDateStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.createTime')" :dual1="$v.createTimeStart" :dual2="$v.createTimeEnd">
                <b-input-group>
                  <i-date-picker
                    id="example-datepicker3"
                    v-model="$v.createTimeStart.$model"
                    :state="$v.createTimeStart"
                    :disabled-date="notAfterCreateTimeEnd"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker
                    id="example-datepicker4"
                    v-model="$v.createTimeEnd.$model"
                    :state="$v.createTimeEnd"
                    :disabled-date="notBeforeCreateTimeStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>

              <!--  交流狀態-->
              <i-form-group-check :label="$t('label.communicateStatus')" :item="$v.communicateStatus">
                <b-form-radio-group
                  class="pt-2"
                  v-model="$v.communicateStatus.$model"
                  :options="radioOption.communicateStatus"
                ></b-form-radio-group>
              </i-form-group-check>
            </b-form-row>

            <!-- 查詢、清除-->
            <b-form-row>
              <b-col></b-col>
              <b-col offset-sm="0" cols="4">
                <i-button type="search" @click="queryHandler">查詢</i-button>
                <i-button type="x-circle" @click="reset">清除</i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>
    <!--    匯出按鈕-->
    <section>
      <div class="container mt-2">
        <i-button type="box-arrow-up-right" @click="queryPDF">匯出</i-button>
      </div>
    </section>
    <!--        表格開始-->
    <section class="container mt-2" v-if="queryStatus">
      <div>
        <i-table
          class="table-sm"
          :items="table.data"
          :fields="table.fields"
          :itemsUndefinedBehavior="'loading'"
          :totalItems="table.totalItems"
          :is-server-side-paging="true"
          @changePagination="handlePaginationChanged($event)"
        >
          <template #cell(action)="row">
            <i-button size="sm" type="binoculars" @click="toCheckDetail(row.item)">明細</i-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { onActivated, reactive, ref, toRef } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import i18n from '@/shared/i18n';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import { navigateByNameAndParams } from '@/router/router';
import { Pagination } from '@/shared/model/pagination.model';

export default {
  name: 'pro104r1History',
  //從上層傳來的是否讀取的資訊
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props, context) {
    const isReloadProp = toRef(props, 'isReload');

    onActivated(() => {
      if (isReloadProp.value) {
        // handleQuery();
      } else {
        reset();
      }
    });

    let stepVisible = ref(true); //隱藏顯示查詢欄位
    const queryStatus = ref(false); //查詢狀態
    const dataPromise = ref(null); //查詢結果
    const notificationService: NotificationService = useNotification();

    const picked = ref('0');
    const radioOption = ref({
      communicateStatus: [
        { text: '所有產品', value: '0' },
        { text: '交流中', value: '1' },
        { text: '已交流', value: '2' },
        { text: '不交流', value: '3' },
      ],
    });

    /* 定義驗證的規則與返回的東西 */
    //查詢條件

    // 這邊有給KEY的話，items也要換成KEY，否則取值會是undefined，這邊是要顯示的欄位，不顯示的放在ITEMS裡面就好

    const table = reactive({
      fields: [
        {
          key: 'id',
          label: i18n.t('label.serialNumber'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'time',
          label: i18n.t('label.communicateTime'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: Date) => formatDate(value, '/'),
        },
        {
          key: 'createTime',
          label: i18n.t('label.createTime'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: Date) => formatDate(value, '/'),
        },
        {
          key: 'innovateName',
          label: i18n.t('label.innovateName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'name',
          label: i18n.t('label.companyName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'orgName',
          label: i18n.t('label.communicateOrganName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: i18n.t('button.binoculars'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    let items = undefined;

    // print PDF
    function queryPDF() {}

    function toCheckDetail(item: item) {
      console.log('proInnovativeProductId =' + item);
      navigateByNameAndParams('pro0103r3', { review: item, isNotKeepAlive: true });
    }

    const queryHandler = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          dataPromise.value = axios
            // .post('/pro-org-suggestions/getProOrgSuggestionHistory', form)// 前端分頁
            .post('/pro-org-suggestions/getProOrgSuggestionHistoryByCriteria', form) //後端分頁
            .then(({ data }) => {
              // table.data = data.slice(0, data.length);// 前端分頁
              // table.totalItems = data.length;// 前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
            })
            .catch(notificationErrorHandler(notificationService))
            .finally(() => (dataPromise.value = null));
        }
      });
      //
      // this.items = [
      //   {
      //     id: 1,
      //     innovateName: 'PCSR防水工法',
      //     communicateTime: '110/12/12',
      //     companyName: '元X工程有限公司',
      //     communicateOrganName: '工程會',
      //     createTime: '110/12/12',
      //     pk: 5566,
      //   },
      //   {
      //     id: 2,
      //     innovateName: '無框架工法',
      //     companyName: '易X工程有限公司',
      //     communicateOrganName: '行政院',
      //     createTime: '110/03/03',
      //     communicateTime: '110/11/02',
      //     pk: 7788,
      //   },
      //   {
      //     id: 3,
      //     innovateName: '創新工法',
      //     companyName: '大X工程有限公司',
      //     communicateOrganName: '台北市政府',
      //     createTime: '110/10/10',
      //     communicateTime: '110/12/22',
      //     pk: 1133,
      //   },
      // ];
      // fields.totalItems = 1;
    };

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      id: '',
      proInnovativeProductId: '',
      innovateName: '',
      companyName: '',
      communicateOrganName: '',
      communicateDateStart: '',
      communicateDateEnd: '',
      createTimeStart: '',
      createTimeEnd: '',
      communicateStatus: '0',
      name: '',
      orgName: '',
      createTime: '',
      time: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      id: {},
      proInnovativeProductId: {},
      innovateName: {},
      companyName: {},
      communicateOrganName: {},
      communicateDateStart: {},
      communicateDateEnd: {},
      createTimeStart: {},
      createTimeEnd: {},
      communicateStatus: {},
      name: {},
      orgName: {},
      createTime: {},
      time: {},
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      queryHandler();
    };

    // vuelidate
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // date-picker
    function notBeforeCommunicateDateStart(date: Date) {
      return date < new Date(form.communicateDateStart);
    }

    function notAfterCommunicateDateEnd(date: Date) {
      return date > new Date(form.communicateDateEnd);
    }

    function notBeforeCreateTimeStart(date: Date) {
      return date < new Date(form.createTimeStart);
    }

    function notAfterCreateTimeEnd(date: Date) {
      return date > new Date(form.createTimeEnd);
    }

    return {
      stepVisible,
      validateState,
      $v,
      queryStatus,
      dataPromise,
      picked,
      radioOption,
      table,
      items,
      toCheckDetail,
      queryHandler,
      reset,
      queryPDF,
      notBeforeCommunicateDateStart,
      notAfterCommunicateDateEnd,
      notBeforeCreateTimeStart,
      notAfterCreateTimeEnd,
      handlePaginationChanged,
    };
  },
};
</script>

<style scoped></style>

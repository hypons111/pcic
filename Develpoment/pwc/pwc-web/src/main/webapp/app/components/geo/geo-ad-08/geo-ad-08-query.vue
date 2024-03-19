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
              <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <!-- <b-form-group class="col-12" label-cols="2" content-cols="10" label="日期區間" label-align="right">
                <i-date-picker v-model="$v.date.$model" type="month" range></i-date-picker>{{ $v.date.$model }}
              </b-form-group> -->
              <i-form-group-check label="日期">
                <b-input-group>
                  <i-date-picker placeholder="yyy/MM/dd"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker placeholder="yyy/MM/dd"></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>

            <b-form-group class="" label-cols="2" content-cols="10" label="單位名稱" label-align="right">
              <b-form-input v-model.trim="$v.compName.$model"></b-form-input>
            </b-form-group>

            <b-form-group label-cols="2" content-cols="10" label="類別" label-align="right">
              <b-form-select v-model="$v.type.$model" :options="queryOptions.type"></b-form-select>
            </b-form-group>

            <b-form-row class="mt-2" align="right">
              <b-col>
                <b-button class="mr-2" size="md" variant="outline-secondary" v-promise-btn="{ promise: dataPromise }" @click="handleQuery">
                  <font-awesome-icon :icon="['fas', 'search']" />
                  {{ $t('button.query') }}
                </b-button>
                <b-button size="md" variant="outline-secondary" @click="reset">
                  <font-awesome-icon :icon="['fas', 'broom']" />
                  {{ $t('button.clear') }}
                </b-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-3">
      <b-button class="ml-3 mr-2" size="md" variant="primary" @click="">
        <font-awesome-icon :icon="['fas', 'download']" />
        {{ $t('button.download') }}
      </b-button>
    </section>

    <section class="container mt-3" v-if="queryStatus">
      <i-table
        class="table-sm"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      >
        <!--              @changePagination="handlePaginationChanged($event)"-->
        <!--            >-->
        <!--        <template #cell(action)="row">-->
        <!--          <b-button size="sm" variant="outline-secondary" @click="toEditView(row.item, ref(formStatusEnum.INFO))">查看</b-button>-->
        <!--          <b-button size="sm" variant="outline-secondary" v-if="roleRef === RoleEnum.ADMIN" @click="toEditView(row.item, ref(formStatusEnum.MODIFY))">回覆</b-button>-->
        <!--          <b-button size="sm" variant="danger" @click="">刪除</b-button>-->
        <!--        </template>-->
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation } from '@/shared/form';
import { computed, onActivated, reactive, Ref, ref } from '@vue/composition-api';
// import NotificationService from '@/shared/notification/notification-service';
// import { useNotification } from '@/shared/notification';
// import { useBvModal } from '@/shared/modal';
// import axios from 'axios';
// import { notificationErrorHandler } from '@/shared/http/http-response-helper';
// import { CmsComp } from '@/shared/model/cms-comp.model';
// import { Pagination } from '@/shared/model/pagination.model';

export default {
  name: 'geoAd08Query',

  setup(props, context) {
    const queryOptions = reactive({
      type: [
        { value: '', text: '不拘' },
        { value: '1', text: '大地技師' },
      ],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      date: [],
      compName: '',
      type: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      date: {},
      compName: {},
      type: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    // modal物件
    // const $bvModal = useBvModal();

    const table = reactive({
      fields: [
        {
          key: 'id',
          label: '序號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'compName',
          label: '單位名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'type',
          label: '類別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'date',
          label: '日期',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'pass',
          label: '通過',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'fail',
          label: '不通過',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          // dataPromise.value = axios
          //   // .post('/cms-comps/criteria', form)           //前端分頁
          //   .post('/cms-comps/criteria-jpa', form) //後端分頁
          //   .then(({ data }) => {
          //     // table.data = data.slice(0, data.length);   //前端分頁
          //     // table.totalItems = data.length;            //前端分頁
          //     table.data = data.content.slice(0, data.content.length); //後端分頁
          //     table.totalItems = data.totalElements; //後端分頁
          //   })
          //   .finally(() => (dataPromise.value = null))
          //   .catch(notificationErrorHandler(notificationService));
          table.data = [
            {
              id: '1',
              date: '2021/11',
              compName: '光宇工程顧問股份有限公司',
              type: '大地技師',
              pass: '2',
              fail: '3',
            },
            {
              id: '2',
              date: '2021/12',
              compName: '光宇工程顧問股份有限公司',
              type: '大地技師',
              pass: '3',
              fail: '1',
            },
          ];
          table.totalItems = 2;
        }
      });
    };

    const disabledRange = date => {
      return date >= new Date(110, 12);
    };

    return {
      queryOptions,
      $v,
      stepVisible,
      queryStatus,
      dataPromise,
      table,
      reset,
      handleQuery,
      ref,
      disabledRange,
    };
  },
};
</script>
<style>
.col-form-label {
  background-color: #737373;
  color: white;
  font-weight: bolder;
}
.mx-datepicker {
  width: 100%;
}
</style>

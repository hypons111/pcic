<template>
  <div>
    <section class="container mt-4">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                待審核帳號查詢
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
          <b-collapse id="collapse-4" style="color: black" v-model="stepVisible">
            <div class="ml-5">
              <b-form-row>
                <i-form-group-check :label="$t('label.adm003.userId')" :item="$v.userId">
                  <b-form-input v-model.trim="$v.userId.$model"></b-form-input>
                </i-form-group-check>
                <i-form-group-check :label="$t('label.adm003.chName')" :item="$v.chName">
                  <b-form-input v-model.trim="$v.chName.$model"></b-form-input>
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <i-form-group-check :label="$t('label.adm003.userType')" :item="$v.userType">
                  <b-form-select disabled :options="queryOptions.userTypeOpts" v-model="$v.userType.$model"></b-form-select>
                </i-form-group-check>

                <i-form-group-check :label="$t('label.adm003.unitId')" :item="$v.unitId">
                  <b-form-select v-model="$v.unitId.$model" :options="queryOptions.unitOpts"></b-form-select>
                  <b-form-invalid-feedback v-for="(error, index) of $v.unitId.$errors" :key="'unitId-' + index">
                    {{ error.$message }}
                  </b-form-invalid-feedback>
                </i-form-group-check>
              </b-form-row>

              <b-button-toolbar class="float-right">
                <i-button class="mr-1" type="search" @click="queryAccount()"></i-button>
                <i-button class="mr-1" type="x-circle" @click="reset()"></i-button>
              </b-button-toolbar>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-4" v-if="queryStatus">
      <div class="card">
        <div class="card-body">
          <i-table
            class="table-sm"
            itemsUndefinedBehavior="loading"
            :items="table.data"
            :fields="table.fields"
            :totalItems="table.totalItems"
            :isServerSidePaging="true"
            @changePagination="handlePaginationChanged($event)"
          >
            <!-- 前端分頁 -->
            <!--              <template #cell(rowNum)="data">-->
            <!--                {{ data.index + 1 }}-->
            <!--              </template>-->
            <template #cell(action)="data">
              <i-button class="mb-1" size="sm" type="binoculars" @click="toEditView(data.item.userId)"></i-button>
            </template>
          </i-table>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onMounted, reactive, ref, watch } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { pccUnitFormatter, userTypeFormatter } from '@/shared/formatter/common';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'Adm003Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props, context) {
    const notificationService: NotificationService = useNotification();
    // 查詢結果及狀態
    const stepVisible = ref(true);
    const queryStatus = ref(false);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const adm003FormDefaults = {
      userId: '', //帳號
      chName: '', //使用者姓名
      userType: 'A', //帳號類別
      auditStatus: 'S', //審核狀態
      unitId: '', //工程會機關單位
    };

    // 建立表單物件 ref
    const adm003Form = reactive(Object.assign({}, adm003FormDefaults));

    // 表單物件驗證規則
    const rules = {
      userId: {},
      chName: {},
      userType: {},
      status: {},
      unitId: {},
    };

    onMounted(() => {
      getAllUnitOpts();
    });

    const allUnitOpts = ref([]);

    function getAllUnitOpts() {
      axios
        .get('/adm-units/get-unit-opts')
        .then(({ data }) => {
          allUnitOpts.value.splice(0, allUnitOpts.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    const { $v, checkValidity, reset } = useValidation(rules, adm003Form, adm003FormDefaults);

    const queryOptions = reactive({
      unitOpts: [],
      userTypeOpts: [
        { value: '', text: '請選擇' },
        { value: 'A', text: '機關帳號' },
        { value: 'C', text: '公司法人帳號' },
        { value: 'I', text: '個人帳號' },
      ],
    });

    const table = reactive({
      fields: [
        {
          key: 'rowNum',
          label: '序號',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: { width: '5%' },
        },
        {
          key: 'userId',
          label: '帳號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'chName',
          label: '使用者姓名',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'userType',
          label: '帳號類別',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
          formatter: (value: string) => userTypeFormatter(value),
        },
        {
          key: 'unitId',
          label: '工程會機關單位',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
          formatter: (value: string) => pccUnitFormatter(value),
        },
        {
          key: 'roleIdList',
          label: '授權角色',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '20%' },
        },
        {
          key: 'action',
          sortable: false,
          label: '動作',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '8%' },
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    // 依表單條件查詢帳號
    function queryAccount() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          axios
            // .post('/adm-account/criteria', adm003Form) // 前端分頁
            .post('/adm-account/criteria-jpa', adm003Form) // 後端分頁
            .then(({ data }) => {
              // table.data = []; //前端分頁
              // table.data.slice(0, table.data.length); //前端分頁
              // table.totalItems = data.length; //前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
            })
            .finally(() => {})
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(adm003Form, pagination);
      queryAccount();
    };

    // 依點選項目之帳號 轉至審核畫面
    function toEditView(userId: string) {
      // context.emit('changeComponent', 'Adm003EditInfo', admAccount);
      navigateByNameAndParams('adm003-edit-info', { userId: userId });
    }

    const isReloadProp = computed(() => {
      if (props.isReload) {
        queryAccount();
      }
      return props.isReload;
    });

    watch(isReloadProp, () => {});

    watch(allUnitOpts, () => {
      queryOptions.unitOpts = allUnitOpts.value.map(data => {
        return { text: data['unitName'], value: data['unitId'] };
      });
      queryOptions.unitOpts.unshift({ text: '請選擇', value: '' });
    });

    return {
      $v,
      checkValidity,
      stepVisible,
      queryStatus,
      queryOptions,
      table,
      reset,
      validateState,
      toEditView,
      queryAccount,
      handlePaginationChanged,
    };
  },
};
</script>

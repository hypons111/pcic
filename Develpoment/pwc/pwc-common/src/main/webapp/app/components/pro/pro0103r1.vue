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
        <div class="card-body col-11">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <i-form-group-check :label="$t('label.innovateName')" :item="$v.innovateName">
                <b-form-input v-model="$v.innovateName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.companyName')" :item="$v.name">
                <b-form-input v-model="$v.name.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.communicateOrganName')" :item="$v.orgName">
                <b-form-input v-model="$v.orgName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check
                class="col-md-12"
                label-cols="2"
                content-cols="10"
                :label="$t('label.communicateStatus')"
                :item="$v.orgStatus"
              >
                <b-form-radio-group v-model="$v.status.$model" :options="queryOptions.status"></b-form-radio-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check
                class="col-md-12"
                label-cols="2"
                content-cols="10"
                :label="$t('label.writeCondition')"
                :item="$v.writeCondition"
              >
                <b-form-radio-group v-model="$v.writeCondition.$model" :options="queryOptions.writeCondition"></b-form-radio-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="clickQueryBtn"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2">
      <i-button type="file-earmark-plus" @click="goNew"></i-button>
    </section>

    <div>
      <section class="container mt-2" v-if="queryStatus">
        <i-table
          class="table-sm"
          ref="iTableComponent"
          :items="table.data"
          :fields="table.fields"
          :itemsUndefinedBehavior="'loading'"
          :is-server-side-paging="true"
          :totalItems="table.totalItems"
          @changePagination="handlePaginationChanged($event)"
        >
          <template v-slot:cell(status)="row">
            {{ orgStatusValueToText(row.item.status) }}
          </template>
          <template v-slot:cell(writeCondition)="row">
            {{ wcStatusValueToText(row.item.writeCondition) }}
          </template>
          <template #cell(action)="row">
            <i-button class="mb-1" size="sm" type="binoculars" @click="toSee(row.item)"></i-button>
          </template>
        </i-table>
      </section>
    </div>
  </div>
</template>

<script lang="ts">
import { onActivated, reactive, ref } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation } from '@/shared/form';
import i18n from '../../shared/i18n';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { Pagination } from '@/shared/model/pagination.model';

export default {
  setup() {
    onActivated(() => {
      reset();
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      innovateName: '',
      name: '',
      orgName: '',
      status: '',
      writeCondition: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      innovateName: {},
      name: {},
      orgName: {},
      status: {},
      writeCondition: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    const queryOptions = reactive({
      status: [
        { text: '全部', value: '' },
        { text: '受理交流', value: '0' },
        { text: '不受理交流', value: '1' },
      ],
      writeCondition: [
        { text: '全部', value: '' },
        { text: '草稿', value: '0' },
        { text: '公開', value: '1' },
      ],
    });

    const rows = ref(100);
    const perPage = ref(1);
    const currentPage = ref(1);

    // 這邊有給KEY的話，items也要換成KEY，否則取值會是undefined，這邊是要顯示的欄位，不顯示的放在ITEMS裡面就好

    const table = reactive({
      fields: [
        {
          key: 'innovateName',
          label: i18n.t('label.itemName'),
        },
        {
          key: 'name',
          label: i18n.t('label.companyName'),
        },
        {
          key: 'orgName',
          label: i18n.t('label.communicateOrganName'),
        },
        {
          key: 'status',
          label: i18n.t('label.communicateSuggestionStatus'),
        },
        {
          key: 'writeCondition',
          label: i18n.t('label.writeCondition'),
        },
        {
          key: 'action',
          label: '',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    function toSee(item: item) {
      navigateByNameAndParams('pro0103r3', { review: item, isnotkeepAlive: true });
    }

    function goNew() {
      navigateByNameAndParams('pro0103r2', { isnotkeepAlive: true });
    }

    const notificationService: NotificationService = useNotification();

    const iTableComponent = ref(null);

    const clickQueryBtn = () => {
      if (iTableComponent.value) iTableComponent.value.state.pagination.currentPage = 1;
      // console.log('******************');
      // console.log(iTableComponent.value.state.pagination.currentPage);
      handleQuery();
    };

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          axios
            // .post('/pro-org-suggestions/getComStatus', form)           //前端分頁
            .post('/pro-org-suggestions/getComStatusPage', form) //後端分頁
            .then(({ data }) => {
              // table.data = data.slice(0, data.length);   //前端分頁
              // table.totalItems = data.length;            //前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
              // notificationService.info('查詢成功');
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    function orgStatusValueToText(value) {
      return queryOptions.status.find(element => element.value === value).text;
    }

    function wcStatusValueToText(value) {
      return queryOptions.writeCondition.find(element => element.value === value).text;
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      $v,
      formDefault,
      stepVisible,
      queryStatus,
      queryOptions,
      rows,
      perPage,
      currentPage,
      table,
      toSee,
      goNew,
      handleQuery,
      reset,
      orgStatusValueToText,
      wcStatusValueToText,
      handlePaginationChanged,
      clickQueryBtn,
      iTableComponent,
    };
  },
};
</script>

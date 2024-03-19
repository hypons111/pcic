<template>
  <b-container>
    <b-card no-body>
      <b-card-header class="py-1">
        <b-row class="align-items-center">
          <b-col cols="11">
            <h5 class="m-0">
              <font-awesome-icon icon="search"></font-awesome-icon>
              查詢條件
            </h5>
          </b-col>
          <b-col cols="1" class="p-0">
            <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
              <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
              <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
            </b-button>
          </b-col>
        </b-row>
      </b-card-header>
      <b-card-body class="col-11">
        <b-collapse v-model="stepVisible.step1">
          <b-form-row>
            <!-- 申請項目 -->
            <i-form-group-check :label="$t('label.docType') + '：'" :item="$v.docType">
              <b-form-select v-model="$v.docType.$model" :options="formOptions.docType">
                <template #first>
                  <option :value="undefined">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 虛擬帳號 -->
            <i-form-group-check :label="$t('label.virtualAccount') + ' (98685)：'" :item="$v.virtualAccount">
              <b-form-input v-model="$v.virtualAccount.$model" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 入帳日期 -->
            <i-form-group-check :label="$t('label.payDate') + '：'" :dual1="$v.payDateStart" :dual2="$v.payDateEnd">
              <i-dual-date-picker :dual1.sync="$v.payDateStart.$model" :dual2.sync="$v.payDateEnd.$model"></i-dual-date-picker>
            </i-form-group-check>
            <!-- 入帳狀態 -->
            <i-form-group-check :label="$t('label.isClose') + '：'" :item="$v.isClose">
              <b-form-select v-model="$v.isClose.$model" :options="formOptions.isClose">
                <template #first>
                  <option :value="undefined">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 姓名 -->
            <i-form-group-check :label="$t('label.name') + '：'" :item="$v.chName">
              <b-form-input v-model="$v.chName.$model" trim lazy></b-form-input>
            </i-form-group-check>
            <!-- 身分證字號 -->
            <i-form-group-check :label="$t('label.idno') + '：'" :item="$v.idno">
              <b-form-input v-model="$v.idno.$model" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 機構名稱 -->
            <i-form-group-check :label="$t('label.compName') + '：'" :item="$v.compName">
              <b-form-input v-model="$v.compName.$model" trim lazy></b-form-input>
            </i-form-group-check>
            <!-- 統一編號 -->
            <i-form-group-check :label="$t('label.compIdno') + '：'" :item="$v.compIdno">
              <b-form-input v-model="$v.compIdno.$model" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <b-col offset="8">
              <i-button type="search" @click="handleQueryBtn"></i-button>
              <i-button type="x-circle" @click="reset"></i-button>
            </b-col>
          </b-form-row>
        </b-collapse>
      </b-card-body>
    </b-card>

    <b-card no-body>
      <b-card-header class="py-1">
        <b-row class="align-items-center">
          <b-col cols="11">
            <h5 class="m-0">
              <font-awesome-icon icon="search"></font-awesome-icon>
              匯入付款資料
            </h5>
          </b-col>
          <b-col cols="1" class="p-0">
            <b-button class="float-right" @click="stepVisible.step2 = !stepVisible.step2">
              <font-awesome-icon v-if="stepVisible.step2" icon="chevron-up"></font-awesome-icon>
              <font-awesome-icon v-if="!stepVisible.step2" icon="chevron-down"></font-awesome-icon>
            </b-button>
          </b-col>
        </b-row>
      </b-card-header>
      <b-card-body class="col-11 pb-0">
        <b-collapse v-model="stepVisible.step2">
          <b-form-row>
            <!-- txt檔 -->
            <i-form-group-check label="txt檔：" :item="$v.compName">
              <b-form-file
                accept="text/plain"
                browse-text="選擇檔案"
                placeholder="未選擇任何檔案"
                drop-placeholder="拖曳檔案至此"
                v-model="$v.compName.$model"
              ></b-form-file>
            </i-form-group-check>
            <!-- 匯入按鈕-->
            <b-col offset="2">
              <i-button type="file-earmark-arrow-up"></i-button>
            </b-col>
          </b-form-row>
        </b-collapse>
      </b-card-body>
    </b-card>

    <i-table
      v-show="stepVisible.step3"
      small
      class="mt-2"
      ref="iTableComponent"
      items-undefined-behavior="loading"
      is-server-side-paging
      :items="table.data"
      :fields="table.fields"
      :total-items="table.totalItems"
      @changePagination="handlePaginationChanged"
    >
      <template #cell(personalInfo)="{ item }">
        {{ item.chName }}<br />
        {{ item.idno }}
      </template>
      <template #cell(applyType)="{ item }">
        {{ formOptionsFormatter(formOptions.docType, item.docType) }}<br />
        {{ formOptionsFormatter(formOptions.applyItemsList, item.applyItemsList) }}
      </template>
    </i-table>
  </b-container>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import axios from 'axios';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { defineComponent, reactive, ref, onActivated, toRefs, onMounted, Ref } from '@vue/composition-api';
import { useValidation } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';

interface propsType {
  isReload: boolean;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0605Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
    criteria: {
      required: false,
      type: Object,
    },
  },
  setup(props) {
    const { isReload, criteria } = toRefs(props);
    const admSysCodeService = new AdmSysCodeService();
    const notificationService = useNotification();
    const $bvModal = useBvModal();
    const iTableComponent = ref(null);

    onMounted(() => {
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    onActivated(() => {
      if (isReload.value) {
        handleQuery();
      }
      // iTableComponent內的currentPage改變就會觸發handlePaginationChanged事件
      // iTableComponent.value.state.pagination.currentPage = criteria.value.currentPage + 1;
    });

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      step2: true,
      step3: false,
    });

    const formOptions = reactive({
      docType: [],
      isClose: [],
      payType: [],
      applyItemsList: [],
    });

    const formDefault = {
      docType: undefined,
      virtualAccount: undefined,
      payDateStart: undefined,
      payDateEnd: undefined,
      isClose: undefined,
      chName: undefined,
      idno: undefined,
      compName: undefined,
      compIdno: undefined,
    };
    const form = reactive(Object.assign({}, formDefault, new Pagination()));
    const rules = {
      docType: {},
      virtualAccount: {},
      payDateStart: {},
      payDateEnd: {},
      isClose: {},
      chName: {},
      idno: {},
      compName: {},
      compIdno: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields: [
        {
          // 流水號
          key: 'docId',
          label: i18n.t('label.serialNumber'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '15%' },
        },
        {
          // 虛擬帳號
          key: 'virtualAccount',
          label: i18n.t('label.virtualAccount'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          // 金額
          key: 'amount',
          label: i18n.t('label.amount2'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-right align-middle',
        },
        {
          // 入帳日期
          key: 'payDate',
          label: i18n.t('label.payDate'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 繳款方式
          key: 'payType',
          label: i18n.t('label.payType'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.payType, value),
        },
        {
          // 個人資訊
          key: 'personalInfo',
          label: i18n.t('label.personalInfo'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 申請方式
          key: 'applyType',
          label: i18n.t('label.applyType'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 申請日期
          key: 'applyDate',
          label: i18n.t('label.applyDate'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 匯入日期
          key: 'createTime',
          label: i18n.t('label.importDate'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    function handleQueryBtn() {
      iTableComponent.value.state.pagination.currentPage = 1;
      if (!form.currentPage) iTableComponent.value.emitPagination();
    }

    function handlePaginationChanged(pagination: Pagination) {
      Object.assign(form, pagination);
      handleQuery();
    }

    function handleQuery() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          stepVisible.step3 = true;
          axios
            .post('/eng-courses/criteria-jpa', form)
            .then(({ data }) => {
              const fakeData = [
                {
                  docId: '414780',
                  virtualAccount: '9868519000010731',
                  amount: 700,
                  payDate: '2018-08-20',
                  payType: '2',
                  chName: '呂世傑',
                  idno: 'W100384137',
                  docType: '1',
                  applyItemsList: '0,1',
                  applyDate: '2018-08-18',
                  createTime: new Date('2018-08-18'),
                },
              ];
              // table.data = data.content.slice(0, data.content.length);
              // table.totalItems = data.totalElements;
              table.data = fakeData.slice(0, fakeData.length);
              table.totalItems = 1;
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    return {
      $v,
      reset,
      table,
      stepVisible,
      formOptions,
      handleQueryBtn,
      iTableComponent,
      formOptionsFormatter,
      handlePaginationChanged,
    };
  },
});
</script>
<style scoped></style>

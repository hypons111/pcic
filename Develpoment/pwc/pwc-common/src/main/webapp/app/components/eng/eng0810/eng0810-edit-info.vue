<template>
  <b-container>
    <b-card no-body>
      <section v-show="queryStatus1">
        <b-card-header>
          <h5 class="m-0">執業技師名冊</h5>
        </b-card-header>
        <b-card-body>
          <b-form-row>
            <!-- v-model="$v.subject.$model" -->
            <!-- 技師姓名 -->
            <i-form-group-check class="col-6" content-cols="6" :label="$t('label.engrName') + '：'" :item="$v.engrName">
              <b-form-input v-model="$v.engrName.$model" trim></b-form-input>
            </i-form-group-check>
            <!-- 身分證字號 -->
            <i-form-group-check class="col-6" content-cols="6" :label="$t('label.idno') + '：'" :item="$v.idno">
              <b-form-input v-model="$v.idno.$model" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <b-col offset="8">
              <i-button type="search" @click="handleQueryEngr"> </i-button>
              <i-button type="arrow-left" @click="toQueryView"></i-button>
            </b-col>
          </b-form-row>
          <!-- 表格一  技師檢視-->
          <section class="container mt-2" v-show="EngrqueryStatus">
            <hr />
            <i-table
              small
              ref="iTableComponent"
              title="技師檢視"
              :itemsUndefinedBehavior="'loading'"
              :items="Engrtable.data"
              :is-server-side-paging="true"
              :fields="Engrtable.fields_backend"
              :totalItems="Engrtable.totalItems"
            >
            </i-table>
          </section>
        </b-card-body>
      </section>
      <section v-show="queryStatus2">
        <b-card-header>
          <h5 class="m-0">執照機構名冊</h5>
        </b-card-header>
        <b-card-body>
          <b-form-row>
            <!-- v-model="$v.subject.$model" -->
            <!-- 機構名稱 -->
            <i-form-group-check class="col-6" content-cols="6" :label="$t('label.compName') + '：'" :item="$v.compName">
              <b-form-input v-model.trim="$v.compName.$model"></b-form-input>
            </i-form-group-check>
            <!-- 統一編號 -->
            <i-form-group-check class="col-6" content-cols="6" :label="$t('label.compIdno') + '：'" :item="$v.compIdno">
              <b-form-input v-model="$v.compIdno.$model" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <b-col offset="8">
              <i-button type="search" @click="handleQueryCom"> </i-button>
              <i-button type="arrow-left" @click="toQueryView"></i-button>
            </b-col>
          </b-form-row>
          <!-- 表格二  事務所檢視 -->
          <section class="container mt-2" v-show="ComqueryStatus">
            <hr />
            <i-table
              small
              ref="iTableComponent"
              title="事務所檢視"
              :itemsUndefinedBehavior="'loading'"
              :items="Comtable.data"
              :is-server-side-paging="true"
              :fields="Comtable.fields_backend"
              :totalItems="Comtable.totalItems"
            >
              <template #cell(action)="row">
                <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
              </template>
            </i-table>
          </section>
        </b-card-body>
      </section>
    </b-card>
  </b-container>
</template>

<script lang="ts">
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { defineComponent, reactive, onMounted, ref } from '@vue/composition-api';
import { handleBack } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import i18n from '@/shared/i18n';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import NotificationService from '@/shared/notification/notification-service';
import { log } from 'console';
import { boolean, object } from '@storybook/addon-knobs';

export default defineComponent({
  name: 'Eng0810EditInfo',
  props: {
    queryStatus1: {
      type: Boolean,
      required: false,
    },
    queryStatus2: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    const $bvModal = useBvModal();
    const notificationService = useNotification();

    onMounted(() => {
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }
    // 將事務所資料倒到事務所表格
    const handleQueryCom = () => {
      ComqueryStatus.value = true;
      Comtable.data = Comdata;
      Comtable.totalItems = Comdata.length;
    };
    // 將技師資料倒到技師表格
    const handleQueryEngr = () => {
      EngrqueryStatus.value = true;
      Engrtable.data = Engrdata;
      Engrtable.totalItems = Engrdata.length;
    };

    const formOptions = reactive({
      
    });

    const formDefault = {
      
      engrName: '',
      idno: '',
      compName: '',
      compIdno: '',
      
    };
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      idno: {},
      compName: {},
      compIdno: {},
      engrName: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // const notificationService: NotificationService = useNotification();

    // // 區塊是否顯示
    // const stepVisible = reactive({
    //   step1: true,
    // });

    // 查詢結果及狀態
    const ComqueryStatus = ref(false);
    const EngrqueryStatus = ref(false);

    // // modal物件
    // const $bvModal = useBvModal();

    //技師表單選項
    const Engrtable = reactive({
      fields_backend: [
        {
          // 姓名
          key: 'chName',
          label: i18n.t('label.chName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '10%' },
        },
        {
          // 身分證字號
          key: 'idno',
          label: i18n.t('label.idno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 執業執照字號
          key: 'engrLicenseNo',
          label: i18n.t('label.engrLicenseNo'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 科別
          key: 'subject',
          label: i18n.t('label.subject'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    // 技師資料(暫時用)
    const Engrdata = [
      {
        chName: '王大明',
        idno: 'A123456789',
        engrLicenseNo: '100',
        subject: '數學',
      },
      {
        chName: '陳小明',
        idno: 'A223344555',
        engrLicenseNo: '200',
        subject: '英文',
      },
    ];
    // 事務所表單選項
    const Comtable = reactive({
      fields_backend: [
        {
          // 機構名稱
          key: 'compName',
          label: i18n.t('label.compName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 統一編號
          key: 'compIdno',
          label: i18n.t('label.compIdno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 地址
          key: 'addr',
          label: i18n.t('label.addr'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 執照字號
          key: 'engrLicenseDocNo',
          label: i18n.t('label.engrLicenseDocNo'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });
    // 事務所資料(暫時用)
    const Comdata = [
      {
        compName: '公司一',
        compIdno: '110',
        addr: '台北市',
        engrLicenseDocNo: '10',
      },
      {
        compName: '公司二',
        compIdno: '220',
        addr: '新北市',
        engrLicenseDocNo: '20',
      },
    ];

    function toQueryView() {
      handleBack({ isNotKeepAlive: true });
    }

    return {
      $v,
      reset,
      validateState,
      formOptions,
      toQueryView,
      // stepVisible,
      ComqueryStatus,
      EngrqueryStatus,
      Engrtable,
      Comtable,
      ref,
      formOptionsFormatter,
      handleQueryEngr,
      handleQueryCom,
    };
  },
});
</script>
<style scoped></style>

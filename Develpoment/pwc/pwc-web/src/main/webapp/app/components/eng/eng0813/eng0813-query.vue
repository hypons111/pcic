<template>
  <b-container>
    <b-card no-body>
      <b-card-header>
        <h5 class="m-0">各類證照申辦案件統計表-查詢畫面</h5>
      </b-card-header>
      <b-card-body>
        <b-form-row>
          <!-- 課程名稱 -->
          <i-form-group-check :label="$t('label.courseName') + '：'" :item="$v.courseName">
            <b-form-input v-model="$v.courseName.$model" trim lazy></b-form-input>
          </i-form-group-check>
          <!-- 課程日期 -->
          <i-form-group-check :label="$t('label.courseDate') + '：'" :dual1="$v.dateStart" :dual2="$v.dateEnd">
            <i-dual-date-picker :dual1.sync="$v.dateStart.$model" :dual2.sync="$v.dateEnd.$model"></i-dual-date-picker>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <i-form-group-check :label="$t('label.subject') + '：'" :item="$v.subject">
            <b-form-select v-model="$v.subject.$model" :options="formOptions.subject">
              <template #first>
                <option value="">不拘</option>
              </template>
            </b-form-select>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <b-col offset="8">
            <i-button type="search" @click="handleQuery"></i-button>
            <i-button type="x-circle" @click="reset"></i-button>
          </b-col>
        </b-form-row>
      </b-card-body>
      <!--表格一 課程查詢-查詢結果畫面 -->
      <section class="container mt-2" v-show="queryStatus">
        <hr />
        <i-table
          small
          ref="iTableComponent"
          title="課查詢結果"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :is-server-side-paging="true"
          :fields="table.fields_backend"
          :totalItems="table.totalItems"
        
        >
          <template #cell(action)="row">
            <!-- 檢視 -->
            <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
          </template>
        </i-table>
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
import { navigateByNameAndParams } from '@/router/router';
import { FormStatusEnum } from '@/shared/enum';
// import { onMounted, onActivated, watch, reactive, Ref, ref, toRef, inject } from '@vue/composition-api';

export default defineComponent({
  name: 'Eng0813Query',
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

    const handleQuery = () => {
      queryStatus.value = true;
      table.data = data;
      table.totalItems = data.length;
    };

    const formOptions = reactive({

      subject: [],
    });

    const formDefault = {
      
      subject: undefined,
      courseName: '',
      courseDate: '',
      dateStart: undefined,
      dateEnd: undefined,
    };
    const form = reactive(Object.assign({}, formDefault));
    const rules = {

      subject: {},
      courseName: {},
      courseDate: {},
      dateStart: {},
      dateEnd: {},
      
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // const notificationService: NotificationService = useNotification();

    // // 區塊是否顯示
    // const stepVisible = reactive({
    //   step1: true,
    // });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    // // modal物件
    // const $bvModal = useBvModal();

    // 技師證書表格
    const table = reactive({
      fields_backend: [
        {
          // 主辦單位
          key: 'adminCompName',
          label: i18n.t('label.adminCompName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '10%' },
        },
        {
          // 課程名稱
          key: 'courseName',
          label: i18n.t('label.courseName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 課程日期
          key: 'courseDate',
          label: i18n.t('label.courseDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 聯絡資訊
          key: 'contactInfo',
          label: i18n.t('label.contactInfo'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 核備文號
          key: 'docNo',
          label: i18n.t('label.docNo'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 動作
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
      ],
      data: undefined,
      totalItems: undefined,
    });
    // 技師證書資料(暫時用)
    const data = [
      {
        adminCompName: '新申請',
        courseName: '3',
        courseDate: '2',
        contactInfo: '1',
        intApplyRate: '33',
        docNo: '1',
      },
    ];

    function toQueryView() {
      handleBack({ isNotKeepAlive: true });
    }

    const toEditView = () => {
      navigateByNameAndParams('Eng0813EditInfo', { });
    };

    return {
      $v,
      reset,
      validateState,
      formOptions,
      toQueryView,
      queryStatus,
      table,
      ref,
      formOptionsFormatter,
      handleQuery,
      toEditView,
      FormStatusEnum,
    };
  },
});
</script>
<style scoped></style>

function ref(arg0: boolean) { throw new Error('Function not implemented.'); } function formOptionsFormatter(subject: any[], value: string):
any { throw new Error('Function not implemented.'); } function formatToString(value: string, arg1: string, arg2: string): any { throw new
Error('Function not implemented.'); }

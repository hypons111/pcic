<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                
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
              <!-- 申請日期 -->
              <i-form-group-check :label="$t('label.date') + '：'" :item="[$v.createTimeStart, $v.createTimeEnd]">
                <i-dual-date-picker :dual1.sync="$v.createTimeStart.$model" :dual2.sync="$v.createTimeEnd.$model"></i-dual-date-picker>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <i-button type="box-arrow-up-right" @click=""></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2" v-show="stepVisible.step2">
      <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :is-server-side-paging="true"
        :fields="table.fields"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >
        <template #cell(content)="{ item }">
          <i-part-of-message :id="item.engEngrSubjectpointId" :value="item.content"></i-part-of-message>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import i18n from '@/shared/i18n';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { IEngEngrSubjectpoint, EngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import { onMounted, onActivated, reactive, ref, defineComponent, toRefs } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { FormStatusEnum } from '@/shared/enum';
import { formOptionsFormatter } from '@/shared/formatter/common';

interface propsType {
  isReload: boolean;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0104Query',
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
      iTableComponent.value.state.pagination.currentPage = criteria.value.currentPage + 1;
    });

    const formOptions = reactive({
      subject: [],
    });

    const formDefault = {
      createTimeStart: null,
      createTimeEnd: null,
      content: '',
      engrName: '',
      idno: '',
      subject: '',
    };
    const form = reactive(Object.assign({}, formDefault, new Pagination()));
    const rules = {
      createTimeStart: {},
      createTimeEnd: {},
      content: {},
      engrName: {},
      idno: {},
      subject: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      step2: false,
    });

    const table = reactive({
      fields: [
        {
          // 技師
          key: 'chName',
          label: i18n.t('label.engr'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 申請日期
          key: 'applyDate',
          label: i18n.t('label.applyDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 審查主題
          key: 'theme',
          label: i18n.t('label.theme'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '20%' },
        },
        {
          // 審查內容
          key: 'content',
          label: i18n.t('label.content'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '30%' },
        },
        {
          // 科別
          key: 'subject',
          label: i18n.t('label.subject'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.subject, value),
        },
        {
          // 積分
          key: 'point',
          label: i18n.t('label.point'),
          sortable: true,
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

    const handleQueryBtn = () => {
      iTableComponent.value.state.pagination.currentPage = 1;
      if (!form.currentPage) iTableComponent.value.emitPagination();
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const handleQuery = () => {
      const now = new Date();
      const offset = now.getTimezoneOffset();
      form.createTimeStart = form.createTimeStart ? new Date(form.createTimeStart.getTime() - offset * 60 * 1000) : null;
      form.createTimeEnd = form.createTimeEnd ? new Date(form.createTimeEnd.getTime() - offset * 60 * 1000) : null;

      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          stepVisible.step2 = true;
          axios
            .post('/eng-engr-subjectpoints/criteria', form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    return {
      iTableComponent,
      formOptions,
      $v,
      stepVisible,
      table,
      reset,
      handleQueryBtn,
      FormStatusEnum,
      EngEngrSubjectpoint,
      formOptionsFormatter,
      handlePaginationChanged,
    };
  },
});
</script>
<style>
</style>

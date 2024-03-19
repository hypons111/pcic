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
              <!-- 日期區間 -->
              <i-form-group-check :label="$t('label.dateStartToEnd') + '：'" :item="[$v.applyDateStart, $v.applyDateEnd]">
                <i-dual-date-picker :dual1.sync="$v.applyDateStart.$model" :dual2.sync="$v.applyDateEnd.$model"></i-dual-date-picker>
              </i-form-group-check>
              <!-- 執業機構(統一編號) -->
              <i-form-group-check label="執業機構(統一編號)" :item="$v.idno">
                <b-form-input v-model="$v.idno.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 丙等件數區間 -->
              <i-form-group-check :label="$t('label.classRange') + '：'" :item="$v.quantity">
                <b-col :xl="5" :lg="7" :md="8" :sm="24">
                <b-form-input :min="0" v-model="$v.quantity1.$model" type="number" trim></b-form-input>
                </b-col>
                ~
                <b-col :xl="5" :lg="7" :md="8" :sm="24">
                <b-form-input :min="0" v-model="$v.quantity2.$model" type="number" trim></b-form-input>
                </b-col>
              </i-form-group-check>
              
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="handleQueryBtn"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
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
      applyDateStart: null,
      applyDateEnd: null,
      content: '',
      engrName: '',
      idno: '',
      subject: '',
      quantity1: '',
      quantity2: '',
    };
    const form = reactive(Object.assign({}, formDefault, new Pagination()));
    const rules = {
      applyDateStart: {},
      applyDateEnd: {},
      content: {},
      engrName: {},
      idno: {},
      subject: {},
      quantity1: {},
      quantity2: {},
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
          // 執業機構
          key: 'engCompChName',
          label: i18n.t('label.engCompChName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 統一編號
          key: 'compIdno',
          label: i18n.t('label.compIdno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 負責人
          key: 'rpName',
          label: i18n.t('label.rpName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 身分證字號
          key: 'idno',
          label: i18n.t('label.idno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 丙等件數
          key: 'class',
          label: i18n.t('label.class'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
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
      form.applyDateStart = form.applyDateStart ? new Date(form.applyDateStart.getTime() - offset * 60 * 1000) : null;
      form.applyDateEnd = form.applyDateEnd ? new Date(form.applyDateEnd.getTime() - offset * 60 * 1000) : null;

      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          stepVisible.step2 = true;
          table.data = [
            {
              no: '1',
              engCompChName: '榮泰顧問有限公司',
              compIdno: '80064151',
              rpName: '劉希羿',
              idno: '',
              class: '2',
            },
          ];
          table.totalItems = 1;
          // axios
          //   .post('/eng-engr-subjectpoints/criteria', form)
          //   .then(({ data }) => {
          //     table.data = data.content.slice(0, data.content.length);
          //     table.totalItems = data.totalElements;
          //   })
          //   .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const toEditView = (entity: IEngEngrSubjectpoint, formStatus: FormStatusEnum, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['index'] = index;
      criteria['totalItems'] = table.totalItems;

      navigateByNameAndParams('Eng0104EditInfo', {
        engEngrSubjectpoint: entity,
        formStatus: formStatus,
        criteria: criteria,
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
      toEditView,
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

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
      <b-card-body>
        <b-collapse v-model="stepVisible.step1">
          <b-form-row>
            <!-- 執照效期止 -->
            <i-form-group-check label-cols="1" content-cols="11" :item="$v.effectDateEnd">
              <div>查詢&ensp;</div>
              <i-date-picker v-model="$v.effectDateEnd.$model" class="col-4"></i-date-picker>
              <div>&ensp;前執業執照換照積分</div>
            </i-form-group-check>
            <!-- 身分證字號 -->
            <i-form-group-check :label="$t('label.idno') + '：'" content-cols="6" :item="$v.idno">
              <b-form-input v-model="$v.idno.$model" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <b-col offset="8">
              <i-button type="search" @click="handleQuery"></i-button>
              <i-button type="x-circle" @click="reset"></i-button>
            </b-col>
          </b-form-row>
        </b-collapse>
      </b-card-body>
    </b-card>

    <b-card no-body class="mt-2" v-if="eng0103">
      <b-card-header>
        <b-row class="align-items-center">
          <b-col cols="11">
            <h5 class="m-0">
              <font-awesome-icon icon="list"></font-awesome-icon>
              查詢結果
            </h5>
          </b-col>
        </b-row>
      </b-card-header>
      <b-card-body>
        <b-form-row>
          <!-- 技師姓名 -->
          <i-form-group-check :label="$t('label.engrName') + '：'">
            <div>{{ eng0103.chName }}</div>
          </i-form-group-check>
          <!-- 科別 -->
          <i-form-group-check :label="$t('label.subject') + '：'">
            <div>{{ formOptionsFormatter(formOptions.subject, eng0103.subjectList) }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 執照字號 -->
          <i-form-group-check :label="$t('label.engrLicenseDocNo') + '：'">
            <div>{{ eng0103.engrLicenseDocNo }}</div>
          </i-form-group-check>
          <!-- 執照效期 -->
          <i-form-group-check :label="$t('label.effectDate') + '：'">
            <!-- <div>{{ `${formatToString(eng0103.effectDateStart, '/', '-')} ~ ${formatToString(eng0103.effectDateEnd, '/', '-')}` }}</div> -->
            <div>{{ eng0103.effectDateStart | dateToString }} ~ {{ eng0103.effectDateEnd | dateToString }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-table caption-top :fields="table.fields1" :items="eng0103.detail">
          <template #cell(no)="{ index }">
            {{ index + 1 }}
          </template>
          <template #table-caption>
            <div class="text-center"><h5 class="m-0">各科訓練積分如下</h5></div>
          </template>
        </b-table>

        <b-form-row>
          <!-- 日期區間 -->
          <i-form-group-check :label="$t('label.date') + $t('label.period') + '：'">
            <div>{{ eng0103.effectDateStart | dateToString }} ~ {{ $v.effectDateEnd.$model | dateToString }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 總積分 -->
          <i-form-group-check :label="$t('label.totalPoint') + '：'">
            <div>{{ eng0103.totalPoint }}</div>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 說明 -->
          <i-form-group-check :label="$t('label.description') + '：'" class="col-12" label-cols="2" content-cols="6">
            <div>
              不符合換照標準：無第五款(工程倫理積分)，無第五款(執業相關法令)積分，無第五款(政府採購全生命週期概論)積分，有關前揭第五款積分，請於110年09月30日前完成，積分不足450分
            </div>
          </i-form-group-check>
        </b-form-row>

        <div class="text-center">
          <b-button variant="info" v-b-modal.view-detail>檢視明細</b-button>
          <i-button type="box-arrow-up-right"></i-button>
        </div>
      </b-card-body>

      <b-modal id="view-detail" size="xl" title="積分審核紀錄" header-bg-variant="secondary" header-text-variant="light">
        <i-table
          border0
          size="sm"
          class="p-3"
          hide-card-header
          :bordered="false"
          :is-server-side-paging="false"
          :fields="table.fields2"
          :items="eng0103.reviewLog"
        >
          <template #cell(subject)="{ item }">
            <b-form-select v-model="item.subject" :options="formOptions.subject.filter(i => item.subjectList.split(',').includes(i.value))">
            </b-form-select>
          </template>
        </i-table>

        <template #modal-footer="{ ok, cancel }">
          <b-button @click="ok()" variant="info">確定</b-button>
          <b-button @click="cancel()" variant="info">離開</b-button>
        </template>
      </b-modal>
    </b-card>
  </b-container>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import axios from 'axios';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import EngCourseService from '@/components/eng/eng0401/eng-course.service';
import { defineComponent, reactive, ref, onActivated, toRefs, onMounted, Ref } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { FormStatusEnum, RoleEnum, StatusEnum } from '@/shared/enum';
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

    const eng0103 = ref(null);

    onMounted(() => {
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formOptions = reactive({
      subject: [],
      law: [],
    });

    const formDefault = {
      effectDateEnd: undefined,
      idno: undefined,
    };
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      effectDateEnd: {},
      idno: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields1: [
        {
          // 序號
          key: 'no',
          label: i18n.t('label.no'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '15%' },
        },
        {
          // 科別
          key: 'subject',
          label: i18n.t('label.subject'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.subject, value),
        },
        {
          // 積分
          key: 'point',
          label: i18n.t('label.point'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
      ],
      fields2: [
        {
          // 日期
          key: 'applyDate',
          label: i18n.t('label.date'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 課程/書面主題
          key: 'theme',
          label: '課程/書面主題',
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          // 科別
          key: 'subject',
          label: i18n.t('label.subject'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          // 款次
          key: 'law',
          label: i18n.t('label.law'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.law, value),
        },
        {
          // 積分
          key: 'point',
          label: i18n.t('label.point'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
      ],
    });

    function handleQuery() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          axios
            .post('/eng-courses/criteria-jpa', form)
            .then(({ data }) => {
              const fakeData = {
                chName: '呂世傑',
                subjectList: '08',
                engrLicenseDocNo: '技執字第009396號',
                effectDateStart: '2021-08-31',
                effectDateEnd: '2027-08-30',
                detail: [
                  {
                    subject: '08',
                    point: '20',
                  },
                  {
                    subject: '09',
                    point: '30',
                  },
                ],
                totalPoint: 50,
                reviewLog: [
                  {
                    applyDate: '2021-08-31',
                    theme: '審核主題',
                    subject: '08',
                    subjectList: '08,09',
                    law: '7',
                    point: '20',
                  },
                  {
                    applyDate: '2021-08-31',
                    theme: '審核主題',
                    subject: '09',
                    subjectList: '08,09',
                    law: '9',
                    point: '20',
                  },
                ],
              };
              // table.data = data.content.slice(0, data.content.length);
              // table.totalItems = data.totalElements;
              eng0103.value = Object.assign({}, fakeData);
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
      handleQuery,
      formOptions,
      FormStatusEnum,
      formOptionsFormatter,
      eng0103,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/', '-'),
  },
});
</script>
<style scoped></style>

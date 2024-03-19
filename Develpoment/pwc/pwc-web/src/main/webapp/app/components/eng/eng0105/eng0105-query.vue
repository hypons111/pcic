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
            <!-- 發文日期 -->
            <i-form-group-check :label="$t('label.execDocDate') + '：'" :dual1="$v.execDocDateStart" :dual2="$v.execDocDateEnd">
              <i-dual-date-picker :dual1.sync="$v.execDocDateStart.$model" :dual2.sync="$v.execDocDateEnd.$model"></i-dual-date-picker>
            </i-form-group-check>
            <!-- 執行日期 -->
            <i-form-group-check :label="$t('label.execDate2') + '：'" :dual1="$v.execDateStart" :dual2="$v.execDateEnd">
              <i-dual-date-picker :dual1.sync="$v.execDateStart.$model" :dual2.sync="$v.execDateEnd.$model"></i-dual-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 懲戒類型 -->
            <i-form-group-check :label="$t('label.disciplineType') + '：'" :item="$v.disciplineType">
              <b-form-select v-model="$v.disciplineType.$model" :options="formOptions.disciplineType">
                <template #first>
                  <option :value="undefined">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 案件類型 -->
            <i-form-group-check :label="$t('label.caseType') + '：'" :item="$v.caseType">
              <b-form-select v-model="$v.caseType.$model" :options="formOptions.caseType">
                <template #first>
                  <option :value="undefined">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 違反法條 -->
            <i-form-group-check :label="$t('label.breakLaw') + '：'" :item="$v.breakLawList">
              <b-form-select v-model="$v.breakLawList.$model" :options="formOptions.breakLawList">
                <template #first>
                  <option :value="undefined">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 發文文號 -->
            <i-form-group-check :label="$t('label.execDocNo') + '：'" :item="$v.execDocNo">
              <b-form-input v-model="$v.execDocNo.$model" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 技師姓名 -->
            <i-form-group-check :label="$t('label.engrName') + '：'" :item="$v.engrName">
              <b-form-input v-model="$v.engrName.$model" trim lazy></b-form-input>
            </i-form-group-check>
            <!-- 身分證字號 -->
            <i-form-group-check :label="$t('label.idno') + '：'" :item="$v.engrIdno">
              <b-form-input v-model="$v.engrIdno.$model" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 交付懲戒者 -->
            <i-form-group-check :label="$t('label.deliverObject') + '：'" :item="$v.deliverObject">
              <b-input-group>
                <b-form-select class="col-4" v-model="$v.deliverObjectType.$model" :options="formOptions.deliverObjectType">
                  <template #first>
                    <option :value="undefined">請選擇</option>
                  </template>
                </b-form-select>
                <b-form-input v-model="$v.deliverObject.$model" trim lazy></b-form-input>
              </b-input-group>
            </i-form-group-check>
            <b-col offset="2">
              <i-button type="search" @click="handleQueryBtn"></i-button>
              <i-button type="x-circle" @click="reset"></i-button>
            </b-col>
          </b-form-row>
        </b-collapse>
      </b-card-body>
    </b-card>

    <div class="mt-2">
      <i-button type="file-earmark-plus"></i-button>
    </div>

    <i-table
      v-show="stepVisible.step2"
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
      <template #cell(deliverObject)="{ item }">
        {{ formOptionsFormatter(formOptions.deliverObjectType, item.deliverObjectType) }}
        <br />
        {{ item.deliverObject }}
      </template>
      <template #head(engrInfo)>
        {{ $t('label.engrName') }}
        <br />
        {{ $t('label.idno') }}
      </template>
      <template #cell(engrInfo)="{ item }">
        {{ item.engrName }}
        <br />
        {{ item.engrIdno }}
      </template>
      <template #cell(action)="{ item, index }">
        <!-- 檢視 -->
        <i-button size="sm" type="binoculars" @click="toEditView(item, FormStatusEnum.READONLY, index)"></i-button>
        <!-- 編輯 -->
        <i-button size="sm" type="pencil-square" @click="toEditView(item, FormStatusEnum.EDIT, index)"></i-button>
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
import { navigateByNameAndParams } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { FormStatusEnum } from '@/shared/enum';

interface propsType {
  isReload: boolean;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0105Query',
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
      step2: false,
    });

    const formOptions = reactive({
      disciplineType: [],
      caseType: [],
      breakLawList: [],
      deliverObjectType: [],
    });

    const formDefault = {
      execDocDateStart: undefined,
      execDocDateEnd: undefined,
      execDateStart: undefined,
      execDateEnd: undefined,
      disciplineType: undefined,
      caseType: undefined,
      breakLawList: undefined,
      execDocNo: undefined,
      engrName: undefined,
      engrIdno: undefined,
      deliverObjectType: undefined,
      deliverObject: undefined,
    };
    const form = reactive(Object.assign({}, formDefault, new Pagination()));
    const rules = {
      execDocDateStart: {},
      execDocDateEnd: {},
      execDateStart: {},
      execDateEnd: {},
      disciplineType: {},
      caseType: {},
      breakLawList: {},
      execDocNo: {},
      engrName: {},
      engrIdno: {},
      deliverObjectType: {},
      deliverObject: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields: [
        {
          // 案件類型
          key: 'caseType',
          label: i18n.t('label.caseType'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
          // thStyle: { width: '15%' },
          formatter: (value: string) => formOptionsFormatter(formOptions.caseType, value),
        },
        {
          // 交付懲戒者
          key: 'deliverObject',
          label: i18n.t('label.deliverObject'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 技師姓名 身分證字號
          key: 'engrInfo',
          label: i18n.t('label.engr'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 案號
          key: 'caseNo',
          label: i18n.t('label.caseNo'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 決議書主文
          key: 'resoDesc',
          label: i18n.t('label.resoDesc'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 發文文號
          key: 'execDocNo',
          label: i18n.t('label.execDocNo'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 發文日期
          key: 'execDocDate',
          label: i18n.t('label.execDocDate'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 動作
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
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
          stepVisible.step2 = true;
          axios
            .post('/eng-courses/criteria-jpa', form)
            .then(({ data }) => {
              const fakeData = [
                {
                  caseType: '1',
                  deliverObjectType: '2',
                  deliverObject: '台灣省政府建設廳',
                  engrName: '陳輝木',
                  engrIdno: 'A100315374',
                  caseNo: '七九府建二79018326',
                  resoDesc: '陳輝木停止業務2年。',
                  execDocNo: '79018326',
                  execDocDate: '1990-03-31',
                  engEngrDisciplineDtlDTOList: [
                    {
                      engEngrDisciplineId: '12345',
                      engEngrDisciplineDetailId: 1,
                      breakLawList: '1,3',
                      disciplineType: '3',
                      countType: '1',
                      count: 4,
                      execDateStart: '2021-03-31',
                      execDateEnd: '2022-03-31',
                    },
                    {
                      engEngrDisciplineId: '12345',
                      engEngrDisciplineDetailId: 2,
                      breakLawList: '8',
                      disciplineType: '2',
                      countType: '0',
                      count: null,
                      execDateStart: '2021-03-31',
                      execDateEnd: '2022-03-31',
                    },
                  ],
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

    const toEditView = (data, formStatus: FormStatusEnum, index?: number) => {
      if (formStatus !== FormStatusEnum.CREATE) {
        // engCourseService
        //   .find(data)
        //   .then(eng0401 => routerTo(eng0401, formStatus, index))
        //   .catch(notificationErrorHandler(notificationService));
        routerTo(data, formStatus, index);
      } else {
        // const eng0401 = new Eng0401();
        // routerTo(eng0401, formStatus, index);
        routerTo(data, formStatus, index);
      }
    };

    const routerTo = (eng0105, formStatus: FormStatusEnum, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['index'] = index;
      criteria['totalItems'] = table.totalItems;

      navigateByNameAndParams('Eng0105EditInfo', {
        eng0105: eng0105,
        formStatus: formStatus,
        criteria: criteria,
      });
    };

    return {
      $v,
      reset,
      table,
      toEditView,
      stepVisible,
      formOptions,
      FormStatusEnum,
      handleQueryBtn,
      iTableComponent,
      formOptionsFormatter,
      handlePaginationChanged,
    };
  },
});
</script>
<style scoped></style>

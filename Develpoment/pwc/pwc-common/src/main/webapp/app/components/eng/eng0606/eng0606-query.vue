<template>
  <div>
    <section class="container mt-2" v-if="roleRef === RoleEnum.ADMIN">
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
              <!-- 被保險人姓名 -->
              <i-form-group-check :label="$t('label.insuredName') + '：'" :item="$v.name">
                <b-form-input v-model="$v.name.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 被保險人身分證 -->
              <i-form-group-check :label="$t('label.insuredIdno') + '：'" :item="$v.idno">
                <b-form-input v-model="$v.idno.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 單位名稱 -->
              <i-form-group-check :label="$t('label.unitName') + '：'" :item="$v.deptName">
                <b-form-input v-model="$v.deptName.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 保險別 -->
              <i-form-group-check :label="$t('label.insuranceType') + '：'" :item="$v.insuranceType">
                <b-form-select v-model="$v.insuranceType.$model" :options="formOptions.insuranceType">
                  <template #first>
                    <option value="">請選擇</option>
                    <option value="">勞保與就保</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 審核日期 -->
              <i-form-group-check :label="$t('label.reviewDate') + '：'" :dual1="$v.workStartDate" :dual2="$v.workEndDate">
                <b-input-group>
                  <i-date-picker
                    v-model="$v.workStartDate.$model"
                    :disabled-date="notAfterworkEndDate"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker
                    v-model="$v.workEndDate.$model"
                    :disabled-date="notBeforeworkStartDate"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
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

    <section class="container mt-2" v-show="queryStatus">
      <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >
        <template #cell(action)="row">
          <!-- 檢視 -->
          <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
          <!-- 編輯 -->
          <i-button size="sm" type="pencil-square" @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)"></i-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import i18n from '@/shared/i18n';
import { onMounted, onActivated, watch, reactive, Ref, ref, toRef, inject } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import { useBvModal } from '@/shared/modal';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { IEng0601 } from '@/shared/model/eng/eng-engr-subjectpoint.model';

export default {
  name: 'Eng0606Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {

    const iTableComponent = ref(null);

    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    // const isReloadProp = computed(() => props.isReload);
    const isReloadProp = toRef(props, 'isReload');

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    // modal物件
    // const $bvModal = useBvModal();

    const formOptions = reactive({
      insuranceType: [],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      name: '',
      idno: '',
      deptName: '',
      insuranceType: '',
      workStartDate: null,
      workEndDate: null,
    };

    // 表單物件驗證規則
    const rules = {
      name: {},
      idno: {},
      deptName: {},
      insuranceType: {},
      workStartDate: {},
      workEndDate: {},
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    //$v : 抓取上面v-model的值、checkValidity:驗證、reset:清空頁面上的參數,rules:規則、form:新值、formDefault:預設值
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      getAllSysCodes();
    });

    watch(roleRef, () => {
      if (roleRef.value === RoleEnum.USER) {
        handleQuery();
      } else {
        queryStatus.value = false;
        // table.data = undefined;
      }
    });

    function getAllSysCodes() {
      formOptions.insuranceType = [
        { value: 'V', text: '自願職災保險' },
        { value: 'F', text: '農保' },
      ];
    }

    const table = reactive({
      fields_backend: [
        {
          // 保險別
          key: 'insuranceType',
          label: i18n.t('label.insuranceType'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 被保險人姓名
          key: 'Name',
          label: i18n.t('label.insuredName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 被保險人身分證號
          key: 'idno',
          label: i18n.t('label.insuredIdno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 被保險人生日
          key: 'birthDate',
          label: i18n.t('label.insuredBirthDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 保險證號
          key: 'engEngrInsuranceNo',
          label: i18n.t('label.engEngrInsuranceNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 單位名稱
          key: 'deptName',
          label: i18n.t('label.unitName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 工作部門代碼
          key: 'deptCode',
          label: i18n.t('label.deptCode'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 異動別
          key: 'changeCode',
          label: i18n.t('label.changeCode'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 異動日期
          key: 'changeDate',
          label: i18n.t('label.changeDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 投保薪資
          key: 'insureSalary',
          label: i18n.t('label.insureSalary'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 就保註記代碼
          key: 'insureMarkCode',
          label: i18n.t('label.insureMarkCode'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const handleQueryBtn = () => {
      iTableComponent.value.state.pagination.currentPage = 1;
      iTableComponent.value.emitPagination();
    };

    const toEditView = (entity: IEng0601, formStatus: FormStatusEnum, index?: number) => {
        const criteria = Object.assign({}, form);
        criteria['index'] = index;
        criteria['totalItems'] = table.totalItems;

        navigateByNameAndParams('Eng0606EditInfo', {
          engEngrSubjectpoint: entity,
          formStatus: formStatus,
          criteria: criteria,
        });
      };

    const handleQuery = () => {
      const now = new Date();
      const offset = now.getTimezoneOffset();
      form.workStartDate = form.workStartDate ? new Date(form.workStartDate.getTime() - offset * 60 * 1000) : null;
      form.workEndDate = form.workEndDate ? new Date(form.workEndDate.getTime() - offset * 60 * 1000) : null;

      //驗證
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          table.data = [
            {
              no: '1',
              insuranceType: '勞保與就保',
              Name: '陳一億',
              idno: 'A122323243',
              birthDate: '077/05/12',
              engEngrInsuranceNo: '05787216T',
              deptName: '一陽水電',
              deptCode: '部分工時',
              changeCode: '加保',
              changeDate: '108/4/30',
              insureSalary: '24000',
              insureMarkCode: '適用就業保險',
            },
          ];
          table.totalItems = 1;
          // axios
          //   // .post('/cms-comps/criteria', form)           //前端分頁
          //   .post('/eng-engr-certificates/criteria-jpa', form) //後端分頁
          //   .then(({ data }) => {
          //     // table.data = data.slice(0, data.length);   //前端分頁
          //     // table.totalItems = data.length;            //前端分頁
          //     table.data = data.content.slice(0, data.content.length); //後端分頁
          //     table.totalItems = data.totalElements; //後端分頁
          //   })
          //   .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    function notBeforeworkStartDate(date: Date) {
      if (form.workStartDate) return date < new Date(form.workStartDate);
    }

    function notAfterworkEndDate(date: Date) {
      if (form.workEndDate) return date > new Date(form.workEndDate);
    }


    return {
      iTableComponent,
      validateState,
      formOptions,
      $v,
      stepVisible,
      queryStatus,
      table,
      reset,
      handleQueryBtn,
      toEditView,
      roleRef,
      FormStatusEnum,
      RoleEnum,
      ref,
      formOptionsFormatter,
      handlePaginationChanged,
      notBeforeworkStartDate,
      notAfterworkEndDate,
    };
  },
};
</script>
<style>
</style>

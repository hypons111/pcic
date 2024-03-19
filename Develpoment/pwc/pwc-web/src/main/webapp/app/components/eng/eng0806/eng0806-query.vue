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
              <!-- 年度 -->
              <i-form-group-check :label="$t('label.engYearreportYyyy')+'：'" :item="$v.engYearreportYyyy">
                <i-date-picker
                  v-model="$v.engYearreportYyyy.$model"
                  type="year"
                  placeholder="yyy"
                ></i-date-picker>
              </i-form-group-check>
              <!-- 是否累計 -->
              <i-form-group-check
                :item="$v.recordItemsList"
              >
                <b-form-checkbox-group
                  v-model="$v.recordItems.$model"
                  :options="formOptions.recordItemsList"
                ></b-form-checkbox-group>
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
        <template v-slot:thead-top="data">
          <b-tr>
            <b-th class="text-center" colspan="2"></b-th>
            <b-th class="text-center" colspan="4">執業機構</b-th>
          </b-tr>
        </template>
      <!--</i-table>從i-table 傳來的changePagination 塞進 handlePaginationChanged-->
        <template #cell(applyMode)="row">
          {{ formOptionsFormatter(formOptions.applyType, row.item.applyType) }}<br />
          {{ formOptionsFormatter(formOptions.applyItemsList, row.item.applyItemsList) }}<br />
        </template>

        <template #cell(action)="row">
          <!-- 檢視 -->
          <i-button size="sm" type="binoculars" 
          @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
        </template>
        <!-- <template #head(engEngrCertificateNo1)>
          {{ $t('label.engEngrCertificateNo') }}
          <br/>
          {{ $t('label.name') }} 
        </template> -->

        <!-- <template #cell(courseDate1) = "{ item }">
          {{ item.timeStart }}至
          {{ item.timeEnd }} 
        </template> -->
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import i18n from '@/shared/i18n';
import NotificationService from '@/shared/notification/notification-service';
import EngEngrCertificateService from '@/components/eng/eng0101/eng-engr-certificate.service';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
// import EngReviewLogService from '@/components/eng/eng-review-log/eng-review-log.service';
import { addReviewLogAndUpdateFunction } from '@/components/eng/eng-review-log/eng-review-log';
import { onMounted, onActivated, watch, reactive, Ref, ref, toRef, inject } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { IEngEngrCertificate, EngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { IEngEngrSubjectpoint, EngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import { Eng0101, IEng0101 } from '@/shared/model/eng/eng0101.model';

export default {
  name: 'Eng0806Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const engEngrCertificateService = new EngEngrCertificateService();
    // const engReviewLogService = new EngReviewLogService();
    const admSysCodeService = new AdmSysCodeService();
    const iTableComponent = ref(null);

    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    // 前台直接顯示自己的證書
    onMounted(() => {
      if (roleRef.value === RoleEnum.USER) {
        handleQuery();
      }
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.County = [
        { value: '0', text: '台北市' },
      ]
      formOptions.recordItemsList = [
        { value: '0', text: '是否累計' },
      ];
    }

    // 切換角色
    const switchRoles = () => {
      if (roleRef.value === RoleEnum.ADMIN) roleRef.value = RoleEnum.USER;
      else roleRef.value = RoleEnum.ADMIN;
    };

    watch(roleRef, () => {
      if (roleRef.value === RoleEnum.USER) {
        handleQuery();
      } else {
        queryStatus.value = false;
        // table.data = undefined;
      }
    });

    // const isReloadProp = computed(() => props.isReload);
    const isReloadProp = toRef(props, 'isReload');

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    const formOptions = reactive({
      County: [],
      recordItemsList: [],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      engYearreportYyyy: null,
      recordItemsList: '',
      recordItems: [],
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      engYearreportYyyy: {},
      recordItemsList: {},
      recordItems: {},
    };

    //$v : 抓取上面v-model的值、checkValidity:驗證、reset:清空頁面上的參數,rules:規則、form:新值、formDefault:預設值
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
      fields_backend: [
        {
          // 縣市
          key: 'County',
          label: i18n.t('label.County'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.County, value),
        },
        // {
        //   // 執業機構
        //   key: 'engCompChName',
        //   label: i18n.t('label.engCompChName'),
        //   sortable: true,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        // },
        {
          // 第一款
          key: 'paragraph1',
          label: i18n.t('label.paragraph1'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 第二款
          key: 'paragraph2',
          label: i18n.t('label.paragraph2'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 第三款
          key: 'paragraph3',
          label: i18n.t('label.paragraph3'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 合計
          key: 'total',
          label: i18n.t('label.total'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 公司數
          key: 'compAmount',
          label: i18n.t('label.compAmount'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
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

    const handleQuery = () => {
      const now = new Date();
      const offset = now.getTimezoneOffset();
      form.engYearreportYyyy = form.engYearreportYyyy ? new Date(form.engYearreportYyyy.getTime() - offset * 60 * 1000) : null;

      //驗證
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          table.data = [
            {
              no: '1',
              County: '0',
              paragraph1: '209(22.89%)',
              paragraph2: '285(28.02%)',
              paragraph3: '11(16.18%)',
              total: '504(25.25%)',
              compAmount: '325(28.51%)',
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

    const handleQueryBtn = () => {
      // if (iTableComponent.value) {
      // iTableComponent.value.state.pagination.currentPage = 1;
      // iTableComponent.value.emitPagination();
      handleQuery();
      // Object.assign(form, iTableComponent.value.pagination);
      // }
      // handleQuery();
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const toEditView = (entity: IEngEngrSubjectpoint, formStatus: FormStatusEnum, index?: number) => {
        const criteria = Object.assign({}, form);
        criteria['index'] = index;
        criteria['totalItems'] = table.totalItems;

        navigateByNameAndParams('Eng0806EditInfo', {
          engEngrSubjectpoint: entity,
          formStatus: formStatus,
          criteria: criteria,
        });
      };

    const routerTo = (eng0101: IEng0101, formStatus: FormStatusEnum, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['currentPage'] = criteria['currentPage'] * criteria['perPage'] + index;
      criteria['perPage'] = 1;
      criteria['totalItems'] = table.totalItems;

      navigateByNameAndParams('Eng0806EditInfo', {
        eng0101: eng0101,
        formStatus: formStatus,
        role: roleRef.value,
        criteria: criteria,
      });
    };

    // const data = {
    //   printerDate: '111年03月24日',
    //   chName: '曾皇銘',
    //   subjectList: '水利工程',
    //   engrLicenseDocNo: '技執字008864號',
    //   effectDateStart: '108年7月18日',
    //   effectDateEnd: '114年7月18日',
    // };

    const discard = (engEngrCertificate: IEngEngrCertificate) => {
      $bvModal
        .msgBoxConfirm(
          `是否確定作廢？編號：${engEngrCertificate.engEngrCertificateNo}，技師姓名：${
            engEngrCertificate.chName ? engEngrCertificate.chName : ''
          }`
        )
        .then((isOK: boolean) => {
          if (isOK) {
            const reviewLog = addReviewLogAndUpdateFunction(
              ReviewStatusEnum.DISCARD,
              ReviewCaseTypeEnum.ENGR_CERTIFICATE,
              engEngrCertificate
            );

            const formData = new FormData();
            formData.append('form', new Blob([JSON.stringify(engEngrCertificate)], { type: 'application/json' }));
            formData.append('reviewLog', new Blob([JSON.stringify(reviewLog)], { type: 'application/json' }));

            engEngrCertificateService
              .update(formData)
              .then(() => handleQuery())
              .catch(notificationErrorHandler(notificationService));
          }
        });
    };

    function notBeforecourseDateStart(date: Date){
      if (form.engYearreportYyyy) return date > new Date(form.engYearreportYyyy);
    }

    return {
      EngEngrCertificate,
      iTableComponent,
      validateState,
      formOptions,
      $v,
      stepVisible,
      queryStatus,
      table,
      reset,
      handleQueryBtn,
      handleQuery,
      toEditView,
      discard,
      roleRef,
      FormStatusEnum,
      RoleEnum,
      ref,
      switchRoles,
      formOptionsFormatter,
      handlePaginationChanged,
      notBeforecourseDateStart,
      // data,
    };
  },
};
</script>
<style>
</style>

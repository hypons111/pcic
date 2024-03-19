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
              <!-- 課程名稱 -->
              <i-form-group-check :label="$t('label.courseName')+'：'" :item="$v.courseName">
                <b-form-input v-model="$v.courseName.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 課程日期 -->
              <i-form-group-check :label="$t('label.courseDate') + '：'" :dual1="$v.courseDateStart" :dual2="$v.courseDateEnd">
                <b-input-group>
                  <i-date-picker
                    v-model="$v.courseDateStart.$model"
                    :disabled-date="notAftercourseDateEnd"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>~</b-input-group-text>
                  <i-date-picker
                    v-model="$v.courseDateEnd.$model"
                    :disabled-date="notBeforecourseDateStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 科別 -->
              <i-form-group-check :label="$t('label.subject') + '：'" :item="$v.subject">
                <b-form-select v-model="$v.subject.$model" :options="formOptions.subject">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
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
      <!--</i-table>從i-table 傳來的changePagination 塞進 handlePaginationChanged-->
        <template #cell(applyMode)="row">
          {{ formOptionsFormatter(formOptions.applyType, row.item.applyType) }}<br />
          {{ formOptionsFormatter(formOptions.applyItemsList, row.item.applyItemsList) }}<br />
        </template>

        <!-- <template #head(engEngrCertificateNo1)>
          {{ $t('label.engEngrCertificateNo') }}
          <br/>
          {{ $t('label.name') }} 
        </template> -->

        <template #cell(courseDate1) = "{ item }">
          {{ item.courseDateStart }}至
          {{ item.courseDateEnd }} 
        </template>
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
  name: 'Eng0405Query',
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
      formOptions.Status = [
        { value: '0', text: '暫存' },
        { value: '1', text: '批退' },
        { value: '3', text: '初審中' },
        { value: '4', text: '複審中' },
        { value: '5', text: '已結案' },
        { value: '6', text: '作廢' },
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

    // const formOptions = reactive({
    //   status: [
    //     { value: '', text: '不拘' },
    //     { value: '1', text: '申請' },
    //     { value: '3', text: '已送出' },
    //     { value: '4', text: '已收文' },
    //     { value: '5', text: '已結案' },
    //     { value: '6', text: '作廢' },
    //   ],
    //   applyItemsList: [
    //     { value: '0', text: '新申請' },
    //     { value: '1', text: '申請英文證書' },
    //     { value: '2', text: '補發' },
    //     // { value: '3', text: '變更' },
    //     { value: '4', text: '廢止' },
    //   ],
    //   applyType: [],
    //   subject: [],
    // });
    const formOptions = reactive({
      subject: [],
      Status: [
        { value: '0', text: '未審核' },
        { value: '1', text: '已審核' }
      ],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      courseName: '',
      courseDate: null,
      courseDateStart: null,
      courseDateEnd: null,
      subject: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      courseName: {},
      courseDate: {},
      courseDateStart: {},
      courseDateEnd: {},
      subject: {},
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
          // 主辦單位
          key: 'compName',
          label: i18n.t('label.adminCompName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 課程名稱
          key: 'courseName',
          label: i18n.t('label.courseName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 簽到時間
          key: 'courseDate1',
          label: i18n.t('label.checkin'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
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
          key: 'realpoint',
          label: i18n.t('label.point'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 目前狀態
          key: 'status',
          label: "目前狀態",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const handleQuery = () => {
      const now = new Date();
      const offset = now.getTimezoneOffset();
      console.log(now+"\tnow");
      console.log(now.getFullYear());
      console.log(now.getMonth());
      console.log(now.getDate());

      form.courseDateStart = form.courseDateStart ? new Date(form.courseDateStart.getTime() - offset * 60 * 1000) : null;
      form.courseDateEnd = form.courseDateEnd ? new Date(form.courseDateEnd.getTime() - offset * 60 * 1000) : null;

      //驗證
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          table.data = [
            {
              no: '1',
              compName: '行政院公共',
              courseName: '工程倫理',
              courseDateStart: '109/11/22 13:30',
              courseDateEnd: '109/11/22 15:30',
              subject: '1',
              realpoint: '20',
              status: '1',
              // docNo: '2021040001',
              // courseName: '智慧融合轉型',
              // courseDate: '110/4/29',
              // chName: '屏東縣政府',
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
      iTableComponent.value.state.pagination.currentPage = 1;
      iTableComponent.value.emitPagination();
      // Object.assign(form, iTableComponent.value.pagination);
      // }
      // handleQuery();
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    // const toEditView = async (engEngrCertificate: IEngEngrCertificate, formStatus: FormStatusEnum, index?: number) => {
    //   if (formStatus === FormStatusEnum.CHANGE_APPLY || formStatus === FormStatusEnum.REMAKE || formStatus === FormStatusEnum.PATCH) {
    //     const nextVersion = (await engEngrCertificateService.findNextVersionOne(engEngrCertificate)).engEngrCertificateVersion;
    //     const versionDiff = parseFloat((nextVersion - engEngrCertificate.engEngrCertificateVersion).toPrecision(12));
    //     if (versionDiff === 0.001) {
    //       notificationService.danger('無法新增：版號（version）小數位已達上限。');
    //       return;
    //     }
    //   }

    //   if (formStatus !== FormStatusEnum.CREATE) {
    //     engEngrCertificateService
    //       .find(engEngrCertificate)
    //       .then(eng0101 => routerTo(eng0101, formStatus, index))
    //       .catch(notificationErrorHandler(notificationService));
    //   } else {
    //     const eng0101 = new Eng0101();
    //     eng0101.engEngrCertificateDTO = engEngrCertificate;
    //     routerTo(eng0101, formStatus, index);
    //   }
    // };

    const toReadView = (engMember: EngMember, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['index'] = index;
      criteria['totalItems'] = table.totalItems;
      navigateByNameAndParams('Eng0404Read', { engMember: engMember, criteria: criteria });
    };
    
    const toEditView = (entity: IEngEngrSubjectpoint, formStatus: FormStatusEnum, index?: number) => {
        const criteria = Object.assign({}, form);
        criteria['index'] = index;
        criteria['totalItems'] = table.totalItems;

        navigateByNameAndParams('Eng0405EditInfo', {
          engEngrSubjectpoint: entity,
          formStatus: formStatus,
          criteria: criteria,
        });
      };

    const toEditView2 = (entity: IEngEngrSubjectpoint, formStatus: FormStatusEnum, index?: number) => {
        const criteria = Object.assign({}, form);
        criteria['index'] = index;
        criteria['totalItems'] = table.totalItems;

        navigateByNameAndParams('Eng0404Read', {
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

      navigateByNameAndParams('Eng0405EditInfo', {
        eng0101: eng0101,
        formStatus: formStatus,
        role: roleRef.value,
        criteria: criteria,
      });
    };

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
      if (form.courseDateStart) return date > new Date(form.courseDateStart);
    }
    function notAftercourseDateEnd(date: Date) {
      if (form.courseDateEnd) return date > new Date(form.courseDateEnd);
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
      toReadView,
      toEditView,
      toEditView2,
      discard,
      roleRef,
      FormStatusEnum,
      RoleEnum,
      ref,
      switchRoles,
      formOptionsFormatter,
      handlePaginationChanged,
      notBeforecourseDateStart,
      notAftercourseDateEnd,
    };
  },
};
</script>
<style>
</style>

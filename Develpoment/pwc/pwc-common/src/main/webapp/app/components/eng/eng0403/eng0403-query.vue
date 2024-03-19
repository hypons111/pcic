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
              <!-- 核備文號 -->
              <i-form-group-check :label="$t('label.docNo')+'：'" :item="$v.docNo">
                <b-form-input v-model.trim="$v.docNo.$model" type="number"></b-form-input>
              </i-form-group-check>
              <!-- 課程名稱 -->
              <i-form-group-check :label="$t('label.courseName')+'：'" :item="$v.courseName">
                <b-form-input v-model="$v.courseName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 課程日期 -->
              <i-form-group-check :label="$t('label.date') + '：'" :dual1="$v.dateStart" :dual2="$v.dateEnd">
                <b-input-group>
                  <i-date-picker
                    v-model="$v.dateStart.$model"
                    :disabled-date="notAfterdateEnd"
                    placeholder="yyy"
                    type="year"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker
                    v-model="$v.dateEnd.$model"
                    :disabled-date="notBeforedateStart"
                    placeholder="yyy"
                    type="year"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>

            查詢後有顯示課程，但無顯示日期者，請通知系統承辦人員。

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

    <section class="container mt-2">
      <!-- 線上申請須知 -->
      <b-button v-if="roleRef === RoleEnum.USER" size="md" variant="info" @click="$bvModal.show('bv-modal-example')">
        線上申請須知
      </b-button>
      <!-- 新增 -->
      <i-button type="file-earmark-plus" @click="toEditView(new EngEngrCertificate(), FormStatusEnum.CREATE)"> </i-button>
      <!--切換角色按鈕-->
      <b-button size="md" variant="warning" @click="switchRoles">
        <font-awesome-icon :icon="['fas', 'people-arrows']" />
        {{ roleRef }}
      </b-button>
    </section>

    <!-- 線上申請須知modal -->
    <b-modal id="bv-modal-example" size="lg" scrollable header-bg-variant="danger" header-text-variant="light">
      <template #modal-title> 「技師證書線上申辦」聲明 </template>
      <div class="d-block">
        <table>
          <tr>
            <th valign="top">ㄧ、</th>
            <td>
              <p>
                歡迎您使用「技師與工程技術顧問公司管理資訊系統」，本系統提供技師證書全線上申辦，如需申請新科別之技師證書者，請按「新申請」；倘需辦理補發者，
                請於資料列表對應之科別證書案件「申請變更」中，勾選補發進行申請。
              </p>
            </td>
          </tr>
          <tr>
            <th valign="top">二、</th>
            <td>
              <p>
                請依「技師證書申請應附書件」上傳照片及附件電子檔，並請注意申請單內之上傳規格，案件於「送出」後，可「檢視」申請案件對應繳款之「虛擬帳號」；
                並於ATM進行繳款，本會將待銀行帳入通知後續辦(無需郵寄相關資料)。如有上傳缺漏情形，本會承辦人員將請申請者補件。
              </p>
            </td>
          </tr>
          <tr>
            <th valign="top">三、</th>
            <td>
              <p>
                本會仍保留技師證書紙本申辦方式，倘習慣以紙本申請者，亦請依「技師證書申請應附書件」郵寄或親送相關申辦資料到會，
                有關郵寄地址為：臺北市信義區松仁路3號9樓；收件者為：行政院公共工程委員會 秘書處 收。
              </p>
            </td>
          </tr>
        </table>
      </div>
      <template #modal-footer="{ cancel }">
        <b-button size="md" @click="cancel()">關閉</b-button>
      </template>
    </b-modal>

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

        <!--<template #head(engEngrCertificateNo1)>
          {{ $t('label.engEngrCertificateNo') }}
          <br/>
          {{ $t('label.name') }} 
        </template>
 
        <template #cell(engEngrCertificateNo1) = "{ item }">
          {{ item.chName }}
          <br/>
          {{ item.engEngrCertificateNo }} 
        </template>-->

        <template #cell(action)="row">
          <!-- 檢視 -->
          <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
          <!-- 編輯 -->
          <i-button
            v-if="
              (row.item.status !== '3' || roleRef === RoleEnum.ADMIN) &&
              (row.item.status !== '4' || roleRef === RoleEnum.ADMIN) &&
              (row.item.status !== '5' || roleRef === RoleEnum.ADMIN) &&
              row.item.status !== '6'
            "
            size="sm"
            type="pencil-square"
            @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)"
          ></i-button>
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
  name: 'Eng0403Query',
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
      formOptions.status = [
        { value: '0', text: '暫存' },
        { value: '1', text: '核退' },
        { value: '3', text: '已送出' },
        { value: '4', text: '已收文' },
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
      status: [],
      applyItemsList: [],
      applyType: [],
      subject: [],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      docNo: '',
      courseName: '',
      date: null,
      dateStart: null,
      dateEnd: null,
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      docNo: {},
      courseName: {},
      date: {},
      dateStart: {},
      dateEnd: {},
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
          key: 'adminCompName',
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
          // 課程日期
          key: 'dateStart',
          label: i18n.t('label.dateStart'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 完成上傳
          key: 'status',
          label: "完成上傳",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.status, value),
        },
        {
          // 上課名單數
          key: 'isReview',
          label: '上課名單數',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',

        },
        {
          // 已審核
          key: 'isReviewPerson',
          label: '已審核',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 未審核
          key: 'notReviewPerson',
          label: '未審核',
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
          tdClass: 'text-left align-middle',
          // thStyle: { width: '10%' },
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const handleQuery = () => {
      const now = new Date();
      const offset = now.getTimezoneOffset();
      form.dateStart = form.dateStart ? new Date(form.dateStart.getTime() - offset * 60 * 1000) : null;
      form.dateEnd = form.dateEnd ? new Date(form.dateEnd.getTime() - offset * 60 * 1000) : null;

      //驗證
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          table.data = [
            {
              adminCompName: '台灣世',
              courseName: '電能設備',
              dateStart: '110/2/1',
              status: '1',
              isReview: '110',
              isReviewPerson: '110',
              notReviewPerson: '0',
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

    const toEditView = (entity: IEngEngrSubjectpoint, formStatus: FormStatusEnum, index?: number) => {
        const criteria = Object.assign({}, form);
        criteria['index'] = index;
        criteria['totalItems'] = table.totalItems;

        navigateByNameAndParams('Eng0403EditInfo', {
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

      navigateByNameAndParams('Eng0101EditInfo', {
      //navigateByNameAndParams('Eng0510EditInfo', {
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

    function notBeforedateStart(date: Date){
      if (form.dateStart) return date > new Date(form.dateStart);
    }

    function notAfterdateEnd(date: Date) {
      if (form.dateEnd) return date > new Date(form.dateEnd);
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
      toEditView,
      discard,
      roleRef,
      FormStatusEnum,
      RoleEnum,
      ref,
      switchRoles,
      formOptionsFormatter,
      handlePaginationChanged,
      notBeforedateStart,
      notAfterdateEnd,
    };
  },
};
</script>
<style>
</style>

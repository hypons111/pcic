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
              <!-- 報表類型 -->
              <i-form-group-check :label="$t('label.reporttype') + '：'" :item="$v.reporttype">
                <b-form-select v-model="$v.reporttype.$model" :options="formOptions.reporttype">
                  <template #first>
                    <option value="">簽證案件異常檢核表</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <!-- 技師姓名 -->
              <i-form-group-check :label="$t('label.engrName') + '：'" :item="$v.engrName">
                <b-form-input v-model="$v.engrName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 身分證字號 -->
              <i-form-group-check :label="$t('label.idno') + '：'" :item="$v.engrId">
                <b-form-input v-model="$v.engrId.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 處理狀態 -->
              <i-form-group-check :label="$t('label.status') + '：'" :item="$v.status">
                <b-form-select v-model="$v.status.$model" :options="formOptions.status">
                  <template #first>
                    <option value="">待處理</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 執行日期 -->
              <i-form-group-check :label="$t('label.closeDate') + '：'" :dual1="$v.closeDateStart" :dual2="$v.closeDateEnd">
                <b-input-group>
                  <i-date-picker
                    v-model="$v.closeDateStart.$model"
                    :disabled-date="notAftercloseDateEnd"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker
                    v-model="$v.closeDateEnd.$model"
                    :disabled-date="notBeforecloseDateStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
              <b-col offset="6">
                <i-button type="search" @click="handleQueryBtn"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
                <i-button type="box-arrow-up-right" @click=""></i-button>
                <i-button type="layers-fill" @click=""></i-button>
              </b-col>
            </b-form-row>

            <b-form-row>
              <!-- 即時處理 -->
              <i-form-group-check :label="$t('label.immediate')"></i-form-group-check>
            </b-form-row>
            <b-form-row>
              <b-col offset="3">
                <i-button type="Technicianvisaexpired" @click=""></i-button>
                <i-button type="ARegulations" @click=""></i-button>
                <i-button type="BRegulations" @click=""></i-button>
                <i-button type="ERegulations" @click=""></i-button>
                <i-button type="FRegulations" @click=""></i-button>
              </b-col>
            </b-form-row>

            <b-form-row>
            </b-form-row>

            <b-form-row>
              <b-col offset="3">
                <i-button type="GRegulations" @click="handleQueryBtn"></i-button>
                <i-button type="HRegulations" @click="handleQueryBtn"></i-button>
                <i-button type="52Regulations" @click="handleQueryBtn"></i-button>
                <i-button type="53Regulations" @click="handleQueryBtn"></i-button>
                <i-button type="7Regulations" @click="handleQueryBtn"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-3" v-show="queryStatus">
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
        <template #cell(applyMode)="row">
          {{ formOptionsFormatter(formOptions.applyType, row.item.applyType) }}<br />
          {{ formOptionsFormatter(formOptions.applyItemsList, row.item.applyItemsList) }}<br />
        </template>

        <template #head(engrNameengrId)>
          {{ $t('label.engrName') }}
          <br/>
          {{ $t('label.idno') }} 
        </template>
  
        <template #cell(engrNameengrId) = "{ item }">
          {{ item.engrName }}
          <br/>
          {{ item.engrId }} 
        </template>

        <template #head(effectDateStarteffectDateEnd)>
          {{ "執業執照" }}
          <br/>
          {{ "有效時間" }} 
        </template>

        <template #cell(effectDateStarteffectDateEnd) = "{ item }">
          {{ item.effectDateStart }}
          <br/>
          {{ item.effectDateEnd }} 
        </template>

        <template #cell(execDate) = "{ item }">
          {{ item.execDateStart }}
          <br/>
          {{ item.execDateEnd }} 
        </template>

        <template #head(remark)>
          {{ "處理狀態" }}
          <br/>
          {{ "註記" }} 
        </template>

        <template #cell(remark) = "row">
          <i-button size="sm" type="layers-fill" @click=""></i-button>
          <br/>
          待處理註記
        </template>

        <template #cell(action)="row">
          <!-- 檢核 -->
          <i-button size="sm" type="clipboard-plus" @click=""></i-button>
          <!-- 刪除 -->
          <i-button size="sm" type="trash" @click="deleteAppendix(admFile.id)"></i-button>
        </template>
        <template #row-details="{ item }">
          <b-table 
            small
            ref="iTableComponent"
            :itemsUndefinedBehavior="'loading'"
            :is-server-side-paging="true"
            :fields="table2.fields_backend"
            :totalItems="table2.totalItems"
            :items="item.engr"
          >
          <template #head(engrNameidno)>
            {{ $t('label.engrName') }}
            <br/>
            {{ $t('label.idno') }} 
          </template>

          <template #cell(engrNameidno) = "{ item }">
            {{ item.engrName }}
            <br/>
            {{ item.engrId }} 
          </template>

           <template #head(effectDate)>
            {{ "執業執照" }}
            <br/>
            {{ "有效時間" }} 
          </template>

          <template #cell(effectDate) = "{ item }">
            {{ item.effectDateStart }}
            <br/>
            {{ item.effectDateEnd }} 
          </template>

          <template #cell(agreeLaw) = "{ item }">
            {{ item.agreeLaw1 }}
            <br/>
            {{ item.agreeLaw2 }} 
          </template>
          
          <template #head(statusremark)>
            {{ "處理狀態" }}
            <br/>
            {{ "註記" }} 
          </template>
          
          <template #cell(statusremark) = "row">
            <i-button size="sm" type="layers-fill" @click=""></i-button>
            <br/>
            待處理註記
          </template>
          <!-- <template v-slot:cell(execDateStart)="slot">
            {{slot.item.execDateStart}} ~ <br/>
            {{slot.item.execDateEnd}}
          </template> -->
          <template #cell(action)="row">
          <!-- 檢核 -->
          <i-button size="sm" type="clipboard-plus" @click=""></i-button>
          <!-- 刪除 -->
          <i-button size="sm" type="trash" @click="deleteAppendix(admFile.id)"></i-button>
          </template>
          </b-table>
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
  name: 'Eng0812Query',
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
        { value: '0', text: '註銷' },
        { value: '1', text: '正常' },
      ];
    }

    function deleteAppendix(id: number) {
      axios
        .delete(`/adm-files/${id}`)
        .then(() => {
          formDefault.appendixFilesInfo = formDefault.appendixFilesInfo.filter(item => item.id !== id);
          form.appendixFilesInfo = form.appendixFilesInfo.filter(item => item.id !== id);
        })
        .catch(notificationErrorHandler(notificationService));
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
      reporttype: [],
      engrName: [],
      engrId: [],
      status: [],
      closeDateStart: [],
      closeDateEnd: [],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      reporttype: '',
      engrName: '',
      engrId: '',
      status: '',
      closeDateStart: null,
      closeDateEnd: null,
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      reporttype: {},
      engrName: {},
      engrId: {},
      status: {},
      closeDateStart: {},
      closeDateEnd: {},
    };

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

    //查詢後顯示的table
    const table = reactive({
      fields_backend: [
        /*{
          //顧問名稱
          key: 'compName',
          label: i18n.t('label.consultantName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },*/
        {
          //技師姓名+身分證字號
          key: 'engrNameengrId',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          //執業執照有效時間
          key: 'effectDateStarteffectDateEnd',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 執業狀態
          key: 'status',
          label: i18n.t('label.status'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //核准(處分執行)日期
          key: 'closeDate',
          label: i18n.t('label.closeDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          //formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          //得標機構名稱
          key: 'compName',
          label: i18n.t('label.compName2'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 簽證案件名稱
          key: 'caseName',
          label: i18n.t('label.caseName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          //簽證辦理日期
          key: 'execDate',
          label: "簽證辦理日期",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //執行機關
          key: 'execOrgName',
          label: i18n.t('label.wkut'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //處理狀態註記
          key: 'remark',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //動作
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

    const table2 = reactive({
         fields_backend: [
          {
            // 顧問名稱
            key: 'compName1',
            label: i18n.t('label.consultantName'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 技師姓名+身分證字號
            key: 'engrNameidno',
            label: "違反類型",
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {  
            // 執業證照有效時間
            key: 'effectDate',
            // label: "執業證照有效時間",
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // thStyle: { width: '10%' },
          },
          {
            // 執業狀態
            key: 'status1',
            label: "執業狀態",
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 核准(處分執行)日期
            key: 'closeDate1',
            label: "核准(處分執行)日期",
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // formatter: (value: string) => formatToString(value, '/', '-'),
          },
          {
            // 違反類型
            key: 'agreeLaw',
            label: "違反類型",
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 處理狀態+註記
            key: 'statusremark',
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
          },
         ],
        data: undefined,
        totalItems: undefined,
      })

    const handleQuery = () => {
      const now = new Date();
      const offset = now.getTimezoneOffset();
      form.closeDateStart = form.closeDateStart ? new Date(form.closeDateStart.getTime() - offset * 60 * 1000) : null;
      form.closeDateEnd = form.closeDateEnd ? new Date(form.closeDateEnd.getTime() - offset * 60 * 1000) : null;

      table.data = data;
      table.totalItems = data.length;
      queryStatus.value = true;
      // checkValidity().then((isValid: boolean) => {
      //   if (isValid) {
      //     table.data = undefined;
      //     table2.data = undefined;
      //     queryStatus.value = true;
      //     table.data = [
      //       {
      //         no: '1',
      //         //consultantName: '工程企業有限公司',
      //         engrName: '陳尚義',
      //         engrId: 'N123456789',
      //         effectDateStart: '2018/12/24',
      //         effectDateEnd: '2024/12/25',
      //         status: '換照',
      //         closeDate: '2022/03/24',
      //         compName: '建築事務所',
      //         caseName: '大樓服務處',
      //         execDateStart: '2022/02/17',
      //         execDateEnd: '2022/12/08',
      //         execOrgName: '司令部',
      //         remark: '待處理',
      //         engr: [
      //           {

      //           }
      //         ]
      //       },
      //     ];
      //     table.totalItems = 1;

      //     /*axios
      //       // .post('/cms-comps/criteria', form)           //前端分頁
      //       .post('/eng-engr-certificates/criteria-jpa', form) //後端分頁
      //       .then(({ data }) => {
      //         // table.data = data.slice(0, data.length);   //前端分頁
      //         // table.totalItems = data.length;            //前端分頁
      //         table.data = data.content.slice(0, data.content.length); //後端分頁
      //         table.totalItems = data.totalElements; //後端分頁
      //       })
      //       .catch(notificationErrorHandler(notificationService));*/
      //   }
      // });
    };
    
    const clear = () => {
        reset();
        
        queryStatus.value = false;
        table.data = [];
        table2.data = [];
      }

    const data = [
        {
          engrName: '陳尚義',
          engrId: 'N123456789',
          effectDateStart: '2018/12/24',
          effectDateEnd: '2024/12/25',
          status: '換照',
          closeDate: '2022/03/24',
          compName: '建築事務所',
          caseName: '大樓服務處',
          execDateStart: '2022/02/17',
          execDateEnd: '2022/12/08',
          execOrgName: '司令部',
          _showDetails: true,
          engr: [
            {
              compName1: '境豐工程有限公司',
              engrName: '侯裕文',
              engrId: 'Q120335446',
              effectDateStart: '2020/01/21',
              effectDateEnd: '2026/01/20',
              status1: '自行停業',
              closeDate1: '2022/03/24',
              agreeLaw1: '停業技師為公司負責人',
              agreeLaw2: '',
            }
          ]
          
      }]
      
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

        navigateByNameAndParams('Eng0812EditInfo', {
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

    function notBeforecloseDateStart(date: Date) {
      if (form.closeDateStart) return date < new Date(form.closeDateStart);
    }

    function notAftercloseDateEnd(date: Date) {
      if (form.closeDateEnd) return date > new Date(form.closeDateEnd);
    }

    function appendixSelected(files: File[]) {}

    return {
      EngEngrCertificate,
      iTableComponent,
      validateState,
      formOptions,
      $v,
      stepVisible,
      queryStatus,
      table,
      table2,
      reset,
      handleQueryBtn,
      toEditView,
      discard,
      roleRef,
      FormStatusEnum,
      RoleEnum,
      ref,
      appendixSelected,
      switchRoles,
      deleteAppendix,
      formOptionsFormatter,
      handlePaginationChanged,
      notBeforecloseDateStart,
      notAftercloseDateEnd,
      clear,
    };
  },
};
</script>
<style>
</style>

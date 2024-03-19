<template>
  <div>
    <section class="container mt-2">
       <div class="card">
        <div class="card-header p-1 text-left">
          <div class="d-flex justify-content-between align-items-center">
            <div>
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>

            <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
              <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
              <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
            </b-button>
          </div>
        </div>
        <div class="card-body col-10">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <!-- 技師姓名 -->
              <i-form-group-check :label="$t('label.engrName') + '：'" :item="$v.engrName">
                <b-form-input v-model="$v.engrName.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 身分證字號 -->
              <i-form-group-check :label="$t('label.idno') + '：'" :item="$v.idno">
                <b-form-input v-model="$v.idno.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row> 
            <b-form-row>
              <!-- 申請日期 -->
              <i-form-group-check :label="$t('label.applyDate') + '：'" :dual1="$v.applyDateStart" :dual2="$v.applyDateEnd">
                <b-input-group>
                  <i-date-picker
                    v-model="$v.applyDateStart.$model"
                    :disabled-date="notAfterApplyDateEnd"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker
                    v-model="$v.applyDateEnd.$model"
                    :disabled-date="notBeforeApplyDateStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
             <i-form-group-check :label="$t('label.publicDate') + '：'" :dual1="$v.publicDateStart" :dual2="$v.publicDateEnd">
                <b-input-group>
                  <i-date-picker
                    v-model="$v.publicDateStart.$model"
                    :disabled-date="notAfterPublicDateEnd"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker
                    v-model="$v.publicDateEnd.$model"
                    :disabled-date="notBeforePublicDateStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 審核狀態 -->
              <i-form-group-check :label="$t('label.status') + '：'" :item="$v.reviewStatus">
                <b-form-select v-model="$v.reviewStatus.$model" :options="formOptions.status">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
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
              <!-- 執照字號 -->
              <i-form-group-check :label="$t('label.engrLicenseDocNo') + '：'" :item="$v.engrLicenseDocNo">
                <b-form-input v-model="$v.engrLicenseDocNo.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 機構名稱 -->
              <i-form-group-check :label="$t('label.compName') + '：'" :item="$v.compName">
                <b-form-input v-model="$v.compName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 收文文號 -->
              <i-form-group-check :label="$t('label.receiveNo') + '：'" :item="$v.receiveNo">
                <b-form-input v-model="$v.receiveNo.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 編號 -->
              <i-form-group-check :label="$t('label.engEngrCertificateNo') + '：'" :item="$v.engEngrCertificateNo">
                <b-form-input v-model="$v.engEngrCertificateNo.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <!-- row3 -->
            <b-form-row>
              <b-col cols="8"></b-col>
              <b-col offset-sm="0" cols="4">
                <!-- @click="handleQuery" -->
                <i-button type="search" @click="handleQueryBtn"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2">
      <!-- 新增 -->
      <i-button type="file-earmark-plus" @click="toEditView(new EngEngrLicense(), FormStatusEnum.CREATE)"> </i-button>
      <!--切換角色按鈕 正式移除-->
      <b-button size="md" variant="warning" @click="switchRoles">
        <font-awesome-icon :icon="['fas', 'people-arrows']" />
        {{ roleRef }}
      </b-button>
    </section>

    <section class="container mt-2" v-show="queryStatus">
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
      <template #cell(applyMode)="row">
        {{ formOptionsFormatter(formOptions.applyType, row.item.applyType) }}<br />
        {{ formOptionsFormatter(formOptions.applyItemsList, row.item.applyItems) }}
      </template>
      <template #cell(action)="row">
         <!-- 檢視 -->
          <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
        <!-- 申請(變更申請) -->
          <i-button
            v-if="showbutton('CHANGE_APPLY', row.item)"
            size="sm"
            type="node-plus"
            @click="toEditView(row.item, FormStatusEnum.CHANGE_APPLY, row.index)"
          ></i-button>
        <!-- 編輯 -->
        <i-button
            v-if="showbutton('EDIT', row.item)"
            size="sm"
            type="pencil-square"
            @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)"
          ></i-button>
          <!-- 作廢 -->
          <i-button
            v-if="showbutton('DISCARD', row.item)"
            size="sm"
            type="clipboard-x"
            @click="discard(row.item)"
          ></i-button>
      </template>
      </i-table>

    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import { ref, reactive, Ref, onActivated, toRef, watch, onMounted } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import { FormStatusEnum, RoleEnum } from '@/shared/enum';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { useValidation } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { Pagination } from '@/shared/model/pagination.model';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { useBvModal } from '@/shared/modal';
import { IEngEngrLicense, EngEngrLicense } from '@/shared/model/eng/eng-engr-license.model';
import { navigateByNameAndParams } from '@/router/router';
import { Eng0102, IEng0102 } from '@/shared/model/eng/eng0102.model';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';

export default {
  name: 'eng0102Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    const iTableComponent = ref(null);

    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    const isReloadProp = toRef(props, 'isReload');

    const formOptions = reactive({
      status: [],
      subject: [],
      applyType: [],
      applyItemsList: []
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      engrName: '',
      idno: '',
      applyDateStart: null,
      applyDateEnd: null,
      publicDateStart: null,
      publicDateEnd: null,
      reviewStatus: '',
      subject: '',
      engrLicenseDocNo: '',
      compName: '',
      receiveNo: '',
      engEngrCertificateNo: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      engrName: {},
      idno: {},
      applyDateStart: {},
      applyDateEnd: {},
      publicDateStart: {},
      publicDateEnd: {},
      reviewStatus: {},
      subject: {},
      engrLicenseDocNo: {},
      compName: {},
      receiveNo: {},
      engEngrCertificateNo: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 切換角色
    const switchRoles = () => {
      if (roleRef.value === RoleEnum.ADMIN) roleRef.value = RoleEnum.USER;
      else roleRef.value = RoleEnum.ADMIN;
    };

     // modal物件
    const $bvModal = useBvModal();

    // 查詢結果及狀態
    const queryStatus = ref(false);

    const table = reactive({
      fields: [
        {
          // 編號
          key: 'engEngrLicenseNo',
          label: i18n.t('label.engEngrCertificateNo'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 姓名
          key: 'chName',
          label: i18n.t('label.name'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 科別
          key: 'subjectList',
          label: i18n.t('label.subject'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.subject, value),
        },
        {
          // 身分證字號
          key: 'idno',
          label: i18n.t('label.idno'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 收文文號
          key: 'receiveNo',
          label: i18n.t('label.receiveNo'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 申請方式
          key: 'applyType',
          label: i18n.t('label.applyType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 申請日期
          key: 'reciveDate',
          label: i18n.t('label.applyDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 核發日期(發證日期)
          key: 'publicDate',
          label: i18n.t('label.publicDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: Date) => formatToString(value, '/', '-'),
        },
        {
          // 核准狀態
          key: 'status',
          label: i18n.t('label.status'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.status, value),
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
    });

    const handleQuery = () => {
      const now = new Date();
      const offset = now.getTimezoneOffset();
      form.applyDateStart = form.applyDateStart ? new Date(form.applyDateStart.getTime() - offset * 60 * 1000) : null;
      form.applyDateEnd = form.applyDateEnd ? new Date(form.applyDateEnd.getTime() - offset * 60 * 1000) : null;
      form.publicDateStart = form.publicDateStart ? new Date(form.publicDateStart.getTime() - offset * 60 * 1000) : null;
      form.publicDateEnd = form.publicDateEnd ? new Date(form.publicDateEnd.getTime() - offset * 60 * 1000) : null;

      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;

          axios
            // .post('/cms-comps/criteria', form)           //前端分頁
            .post('/eng-engr-licenses/criteria-jpareal', form) //後端分頁
            .then(({ data }) => {
              // table.data = data.slice(0, data.length);   //前端分頁
              // table.totalItems = data.length;            //前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
              
            })
            .catch(notificationErrorHandler(notificationService));
          // table.data = [
          //   {
          //     engEngrCertificateNo: '038090',
          //     chName: '葉永龍',
          //     subject: '土木工程',
          //     idno: 'N120625961',
          //     receiveNo: '',
          //     applyType: '1',
          //     applyItems: '5',
          //     applyDate: '2021/05/06',
          //     publicDate: '',
          //     status: '1'
          //   },
          //   {
          //     engEngrCertificateNo: '038084',
          //     chName: '高誌宏',
          //     subject: '交通工程',
          //     idno: 'L123846040',
          //     receiveNo: '',
          //     applyType: '1',
          //     applyItems: '0',
          //     applyDate: '2021/05/05',
          //     publicDate: '',
          //     status: '1'
          //   },
          //   {
          //     engEngrCertificateNo: '038076',
          //     chName: '林家群',
          //     subject: '土木工程',
          //     idno: 'E122440585',
          //     receiveNo: '',
          //     applyType: '1',
          //     applyItems: '2',
          //     applyDate: '2021/05/04',
          //     publicDate: '',
          //     status: '1'
          //   },
          //   ];
          // table.totalItems = 3;
        }
      });
    };

    const handleQueryBtn = () => {
      iTableComponent.value.state.pagination.currentPage = 1;
      iTableComponent.value.emitPagination();
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const discard = () => {
       // TODO: 作廢 確認跳窗
    };

    // 進入 新增 or 編輯 頁面
    const toEditView = async (engEngrLicense: IEngEngrLicense, formStatus: FormStatusEnum, index?: number) => {
      if (formStatus === FormStatusEnum.CHANGE_APPLY || formStatus === FormStatusEnum.REMAKE || formStatus === FormStatusEnum.PATCH) {
      }

      if (formStatus !== FormStatusEnum.CREATE) {
        const eng0102 = new Eng0102();
        routerTo(eng0102, formStatus, index);

      } else {
        const eng0102 = new Eng0102();
        eng0102.engEngrLicenseDTO = engEngrLicense;
        routerTo(eng0102, formStatus, index);
      }
    };

    const routerTo = (eng0102: IEng0102, formStatus: FormStatusEnum, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['currentPage'] = criteria['currentPage'] * criteria['perPage'] + index;
      criteria['perPage'] = 1;
      criteria['totalItems'] = table.totalItems;

      navigateByNameAndParams('Eng0102EditInfo', {
        eng0102: eng0102,
        formStatus: formStatus,
        role: roleRef.value,
        criteria: criteria,
      });
    };

    function notBeforeApplyDateStart(date: Date) {
      if (form.applyDateStart) return date < new Date(form.applyDateStart);
    }

    function notAfterApplyDateEnd(date: Date) {
      if (form.applyDateEnd) return date > new Date(form.applyDateEnd);
    }

    function notBeforePublicDateStart(date: Date) {
      if (form.publicDateStart) return date < new Date(form.publicDateStart);
    }

    function notAfterPublicDateEnd(date: Date) {
      if (form.publicDateEnd) return date > new Date(form.publicDateEnd);
    }

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    onMounted(() => {
      if (roleRef.value === RoleEnum.USER) {
        handleQuery();
      }
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

    function showbutton(btnType: string, value: []) {
      const editStatus = ['1', '4', '5'];
      const discardStatusUSER = ['1', '5'];
      const discardStatusADMIN = ['3', '4'];
      let result = false;

      switch (btnType) {
        case 'CHANGE_APPLY':
           // TODO: 判別待加入 ADM_ROLE:  ENG_01 技師
          if (roleRef.value === RoleEnum.USER) {
            result = true;
          }
          break;
        case 'EDIT':
          // TODO: 判別待加入 ADM_ROLE:  ENG_01 技師 及 ENG_08 業管人員
          if (value['applyType'] === '1' || roleRef.value === RoleEnum.USER) {
            if (editStatus.indexOf(value['status']) > -1) {
              result = true;
            }
          }
          break;
        case 'DISCARD':
          if (roleRef.value === RoleEnum.USER) {
            if (discardStatusUSER.indexOf(value['status']) > -1){
              result = true;
            }
          } else if (roleRef.value === RoleEnum.ADMIN) {
            if (discardStatusADMIN.indexOf(value['status']) > -1) {
              result = true;
            }
          }
          break;
        default:
          result = false;
          break;
      }

      return result;
    }

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.status = [
        { value: '1', text: '待審核' },
        { value: '2', text: '審核中' },
        { value: '3', text: '已結案' },
        { value: '5', text: '暫存' },
        { value: '0', text: '作廢' },
      ];
      // formOptions.applyType = [
      //   { value: '0', text: '紙本申請'},
      //   { value: '1', text: '線上申請'},
      // ];
      // formOptions.applyItemsList = [
      //   { value: '0', text: '新申請'},
      //   { value: '1', text: '變更'},
      //   { value: '2', text: '換發'},
      //   { value: '3', text: '換照'},
      //   { value: '4', text: '補發'},
      //   { value: '5', text: '自行停業'},
      //   { value: '6', text: '變更及換發'},
      //   { value: '7', text: '廢止'},
      //   { value: '8', text: '核發英文證明書'},
      // ];
    }

    return {
      EngEngrLicense,
      FormStatusEnum,
      $v,
      checkValidity,
      form,
      formOptions,
      formOptionsFormatter,
      handleQueryBtn,
      handlePaginationChanged,
      iTableComponent,
      table,
      toEditView,
      notificationService,
      notBeforeApplyDateStart,
      notAfterApplyDateEnd,
      notBeforePublicDateStart,
      notAfterPublicDateEnd,
      queryStatus,
      ref,
      reset,
      roleRef,
      rules,
      stepVisible,
      showbutton,
      switchRoles,
    };
  },
};
</script>

<style></style>

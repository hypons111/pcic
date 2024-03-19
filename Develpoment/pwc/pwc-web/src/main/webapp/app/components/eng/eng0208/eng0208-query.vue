<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>
          </div>
        </div>
        <div class="card-body col-11">
          <b-form-row>
            <!-- 公司名稱 -->
            <i-form-group-check :label="$t('label.comName') + '：'"
            :item="$v.chName">
              <b-form-input v-model="$v.chName.$model" trim>
              </b-form-input>
            </i-form-group-check>
            <!-- 統一編號 -->
            <i-form-group-check :label="$t('label.compIdno') + '：'"
            :item="$v.compIdno">
              <b-form-input v-model="$v.compIdno.$model" trim>
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 狀態 -->
            <i-form-group-check :label="$t('label.status') + '：'" 
            :item="$v.status">
              <b-form-select v-model="$v.status.$model"
              :options="formOptions.status">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 任職日期 -->
            <i-form-group-check :label="$t('label.inDate') + '：'" 
            :item="$v.inDateStart">
              <i-dual-date-picker :dual1.sync="$v.inDateStart.$model" :dual2.sync="$v.inDateEnd.$model"></i-dual-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 姓名 -->
            <i-form-group-check :label="$t('label.name') + '：'"
            :item="$v.engrName">
              <b-form-input v-model="$v.engrName.$model" trim>
              </b-form-input>
            </i-form-group-check>
            <!-- 身分證字號 -->
            <i-form-group-check :label="$t('label.idno') + '：'"
            :item="$v.memberIdno">
              <b-form-input v-model="$v.memberIdno.$model" trim>
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <b-col offset="8" >
              <!-- 查詢 -->
              <i-button type="search"  @click="handleQueryBtn"/>
              <!-- 清除 -->
              <i-button type="x-circle" class="ml-2" @click="clear"/>
            </b-col>
          </b-form-row>
        </div>
      </div>
    </section>
    <section class="container mt-2" v-show="queryStatus">
      <i-table
        small
        :hideNo="true"
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        :items="table.data"
        @changePagination="handlePaginationChanged"
      >
        <template #cell(isEffectDateEnd)="row">
          {{ row.item.isEffectDateEnd? '逾期' : '否'}}
        </template>
        <template #cell(isSelfStop)="row">
          {{ row.item.isSelfStop? '是' : '否'}}
        </template>
        <template #cell(isMore7Year)="row">
          {{ row.item.isMore7Year? '是' : '否'}}
        </template>

        <template #cell(action)="row">
          <!-- 檢視 -->
          <i-button size="sm" type="binoculars" 
          @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
          <!-- 編輯 -->
          <i-button
            size="sm"
            type="pencil-square"
            @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)"
          ></i-button>
          <!-- 刪除 -->
          <i-button size="sm" type="trash" @click="submitForm"></i-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, ref, reactive, Ref } from '@vue/composition-api';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import i18n from '@/shared/i18n';
import axios from 'axios';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useValidation, validateState } from '@/shared/form';
import { Pagination } from '@/shared/model/pagination.model';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { IEngManage, EngManage } from '@/shared/model/eng/eng-manage.model';
import { useBvModal } from '@/shared/modal';

export default {
  name: 'Eng0208Query',
    props: {
      isReload: {
        type: Boolean,
        required: false,
      },
    },
    setup(props) {
      const admSysCodeService = new AdmSysCodeService();
      const iTableComponent = ref(null);
      const notificationService: NotificationService = useNotification();
      const $bvModal = useBvModal();
      const stepVisible = reactive({step1: true,});
      // 手動定義角色
      const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);
      // 切換角色
      const switchRoles = () => {
        if (roleRef.value === RoleEnum.ADMIN) roleRef.value = RoleEnum.COMP;
        else roleRef.value = RoleEnum.ADMIN;
      };
      // 查詢結果及狀態
      const queryStatus = ref(false);

      const formOptions = reactive({
        status: [],  // 任職狀態
      });

      const formDefault = {
        chName: '',  // 公司名稱
        compIdno: '',  // 公司統編
        status: '',  // 任職狀態
        inDateStart: null,  // 任職日期
        inDateEnd: null,
        engrName: '',
        memberIdno: '',
      };

      const form = reactive(Object.assign({}, formDefault));

      const rules = {
        chName: {},  // 公司名稱
        compIdno: {},  // 公司統編
        status: {},  // 任職狀態
        inDateStart: {},  // 任職日期
        inDateEnd: {},
        engrName: {},
        memberIdno: {},
      }

      const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

      onMounted(() => {
        getAllSysCodes();
      });

      const handleQueryBtn = () => {
        // iTableComponent.value.state.pagination.currentPage = 1;
        // iTableComponent.value.emitPagination();
        handleQuery();
      };

      const clear = () => {
        reset();
        queryStatus.value = false;
        table.data = [];
      }

      const handlePaginationChanged = (pagination: Pagination) => {
        Object.assign(form, pagination);
        handleQuery();
      };

      const toEditView = (engManage: EngManage,
      formStatus: FormStatusEnum, index?: number) => {
        navigateByNameAndParams('Eng0208EditInfo', { formStatus: formStatus});
      }

      function submitForm(isCheckReq: boolean) {
        $v.value.$reset();
        this.$nextTick(() => {
          $bvModal.msgBoxConfirm('請再次確認資料是否刪除？').then((isOK: boolean) => {
            if (isOK) {
              $bvModal.msgBoxOk('儲存成功。');
            }
        })})
      }

      const handleQuery = () => {
        table.data = data;
        table.totalItems = data.length;
        queryStatus.value = true;
      }

      function getAllSysCodes(): void {
        admSysCodeService.setFormOptions(formOptions, 'ENG');
        formOptions.status = [
          { value: '0', text: "離職" },
          { value: '1', text: "任職" }
        ]
      }

      const table = reactive({
        fields_backend: [
          {
            // 公司名稱
            key: 'chName',
            label: i18n.t('label.comName'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-left align-middle',
          },
          {
            // 編號
            key: 'engEngrCertificateNo',
            label: i18n.t('label.engEngrCertificateNo'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 姓名
            key: 'engrName',
            label: i18n.t('label.name'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 性別
            key: 'sex',
            label: i18n.t('label.sex'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // formatter: (value: string) => 
            // formOptionsFormatter(formOptions.caseType, value),
          },
          {
            // 身分證字號
            key: 'memberIdno',
            label: i18n.t('label.idno'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 狀態
            key: 'status',
            label: i18n.t('label.status'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            formatter: (value: string) => 
            formOptionsFormatter(formOptions.status, value),
          },
          {
            // 任職日期
            key: 'inDate',
            label: i18n.t('label.inDate'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 報備離會(或報備離職)日期
            key: 'leaveExpdate',
            label: i18n.t('label.leaveExpdate'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // formatter: (value: string) => formatToString(value, '/', '-'),
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

      const data = [
        {
          chName: '台灣世曦工程顧問股份有限公司',
          engEngrCertificateNo: '9970',
          engrName: '李奕霆',
          sex: '男',
          memberIdno: 'F12856****',
          status: '1',  // 任職
          inDate: '111/04/20',
          isEffectDateEnd: true,  // 逾期
          leaveExpdate: '',
          isSelfStop: false,  // 否
          leaveDate: '',
          isMore7Year: false,  // 否
          subject: '01',  // 土木工程
        }
      ];

      return {
        stepVisible,
        FormStatusEnum,
        switchRoles,
        RoleEnum,
        roleRef,
        queryStatus,
        $v,
        formOptions,
        table,
        handleQueryBtn,
        clear,
        handlePaginationChanged,
        toEditView,
        submitForm,
      }
    }
}
</script>
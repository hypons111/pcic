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
              <!-- 名稱(主管機關) -->
              <i-form-group-check :label="$t('label.bosName') + '：'" :item="$v.bosName">
                <b-form-input v-model="$v.bosName.$model" trim></b-form-input>
              </i-form-group-check>
              <i-form-group-check offset="8">
                <!-- 查詢 -->
                <i-button type="search" @click="handleQueryBtn"></i-button>
                <!-- 清除表單資料 -->
                <i-button class="ml-1" type="x-circle" @click="clear"></i-button>
              </i-form-group-check>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2">
      <!-- 新增 -->
      <i-button type="file-earmark-plus" v-if="roleRef === RoleEnum.ADMIN" @click="toEditView(new EngManage(), FormStatusEnum.CREATE)">
      </i-button>
      <!--切換角色按鈕-->
      <!-- <b-button size="md" variant="warning" @click="switchRoles">
        <font-awesome-icon :icon="['fas', 'people-arrows']" />
        {{ roleRef }}
      </b-button> -->
    </section>

    <section class="container mt-2" v-show="queryStatus">
      <i-table
        v-if="roleRef === RoleEnum.ADMIN"
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >
        <!-- <template #cell(applyMode)="row">
          {{ formOptionsFormatter(formOptions.applyType, row.item.applyType) }}<br />
          {{ formOptionsFormatter(formOptions.applyItemsList, row.item.applyItemsList) }}<br />
        </template> -->

        <template v-slot:cell(tel)="slot">
          電話(一)：{{ slot.item.tel1 }}<br />
          電話(二)：{{ slot.item.tel2 }}<br />
          傳真：{{ slot.item.fax }}<br />
        </template>
        <!-- 加上地址元件 -->
        <template #cell(address1Other)="row">
          <b-form-row>
            <i-form-group-check class="col-12" content-cols="10">
              <i-address-picker
                :is-text="true"
                :zip="row.item.address1Zip"
                :city="row.item.address1City"
                :town="row.item.address1Town"
                :other="row.item.address1Other"
              >
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>
        </template>
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
import EngManageService from '@/components/eng/eng0703/eng0703-manage.service';
// import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
// import EngReviewLogService from '@/components/eng/eng-review-log/eng-review-log.service';
// import { addReviewLogAndUpdateFunction } from '@/components/eng/eng-review-log/eng-review-log';
import { onMounted, onActivated, watch, reactive, Ref, ref, toRef, inject } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { IEngManage, EngManage } from '@/shared/model/eng/eng-manage.model';
// import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { Eng0703, IEng0703 } from '@/shared/model/eng/eng0703.model';

export default {
  name: 'Eng0703Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const engManageService = new EngManageService();
    const iTableComponent = ref(null);

    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    // 切換角色
    const switchRoles = () => {
      if (roleRef.value === RoleEnum.ADMIN) roleRef.value = RoleEnum.USER;
      else roleRef.value = RoleEnum.ADMIN;
    };

    watch(roleRef, () => {
      // if (roleRef.value === RoleEnum.USER) {
      //   handleQuery();
      // } else {
      //   queryStatus.value = false;
      //   // table.data = undefined;
      // }
    });

    // const isReloadProp = computed(() => props.isReload);
    const isReloadProp = toRef(props, 'isReload');

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    // const formOptions = reactive({
    //   status: [],
    //   applyItemsList: [],
    //   applyType: [],
    //   subject: [],
    // });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      bosName: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      bosName: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    // modal物件，彈跳視窗?
    const $bvModal = useBvModal();

    const table = reactive({
      fields_backend: [
        {
          // 名稱
          key: 'chName',
          label: i18n.t('label.bosName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 電話
          key: 'tel',
          label: i18n.t('label.tel'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => formOptionsFormatter(formOptions.subject, value),
        },
        {
          // 地址
          key: 'address1Other',
          label: i18n.t('label.address1'),
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

    // 清除表單資料
    const clear = () => {
      form.bosName = '';

      queryStatus.value = false;
      table.data = undefined;
    };

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/eng-eng0703r1', form) //後端分頁
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    // 按下查詢
    const handleQueryBtn = () => {
      // if (iTableComponent.value) {
      iTableComponent.value.state.pagination.currentPage = 1;

      iTableComponent.value.emitPagination();

      // Object.assign(form, iTableComponent.value.pagination);
      // }
      // handleQuery();
    };

    // 分頁數改變
    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    // 前往編輯畫面
    const toEditView = async (engManage: IEngManage, formStatus: FormStatusEnum, index?: number) => {
      if (formStatus !== FormStatusEnum.CREATE) {
        // 檢視or編輯
        engManageService
          .findEng0703(engManage.engCompId) // 發API到後端
          .then(eng0703 => routerTo(eng0703, formStatus, index))
          .catch(notificationErrorHandler(notificationService));
      } else {
        // 新增的話
        const eng0703 = new Eng0703();
        eng0703.engManageDTO = engManage;
        routerTo(eng0703, formStatus, index);
      }
    };

    // 跳到檢視or編輯or新增畫面
    const routerTo = (eng0703: IEng0703, formStatus: FormStatusEnum, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['currentPage'] = criteria['currentPage'] * criteria['perPage'] + index;
      criteria['perPage'] = 1;
      criteria['totalItems'] = table.totalItems;

      navigateByNameAndParams('Eng0703EditInfo', {
        eng0703: eng0703,
        formStatus: formStatus,
        role: roleRef.value,
        criteria: criteria,
      });
    };

    return {
      EngManage,
      iTableComponent,
      validateState,
      // formOptions,
      $v,
      stepVisible,
      queryStatus,
      table,
      clear,
      reset,
      handleQueryBtn,
      toEditView,
      // discard,
      roleRef,
      FormStatusEnum,
      RoleEnum,
      ref,
      switchRoles,
      formOptionsFormatter,
      handlePaginationChanged,
      // notBeforeApplyDateStart,
      // notAfterApplyDateEnd,
      // notBeforePublicDateStart,
      // notAfterPublicDateEnd,
    };
  },
};
</script>
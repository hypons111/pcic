<template>
  <div>
    <section class="container mt-2" v-if="roleRef === RoleEnum.ADMIN">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="plus-square-fill"></font-awesome-icon>
                審議情形提醒
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
        <div class="card-body col-14">
          <b-collapse v-model="stepVisible.step1">
          <div class="col-12">
            <b-table-simple responsive class="table-sm" borderless>
              <b-thead style="text-align:center;font-size:125%">
                <b-tr>
                  <b-th></b-th>
                  <b-th>可行性評估/新興建設計畫</b-th>
                  <b-th></b-th>
                </b-tr>
              </b-thead>
              <b-tbody style="text-align:center">
                <b-tr>
                  <b-td></b-td>
                  <b-td>案件數</b-td>
                  <b-td></b-td>
                </b-tr>
                <b-tr>
                  <b-td>審查逾期</b-td>
                  <b-td>0</b-td>
                  <b-td><b-button variant="info" @click="toPlanEvaluation">前往處理</b-button></b-td>
                </b-tr>
                <b-tr>
                  <b-td>新提案</b-td>
                  <b-td>13</b-td>
                  <b-td><b-button variant="info" @click="toPlanEvaluation">前往處理</b-button></b-td>
                </b-tr>
                <b-tr>
                  <b-td>審查中</b-td>
                  <b-td>0</b-td>
                  <b-td><b-button variant="info" @click="toPlanEvaluation">前往處理</b-button></b-td>
                </b-tr>
              </b-tbody>
            </b-table-simple>
            </div>
            <div class="col-12.5">
            <b-table-simple responsive class="table-sm" borderless>
              <b-thead style="text-align:center;font-size:125%">
                <b-tr>
                  <b-th></b-th>
                  <b-th>預定函送/基本設計</b-th>
                  <b-th></b-th>
                </b-tr>
              </b-thead>
              <b-tbody style="text-align:center">
                <b-tr>
                  <b-td></b-td>
                  <b-td>案件數</b-td>
                  <b-td></b-td>
                </b-tr>
                <b-tr>
                  <b-td>審查逾期</b-td>
                  <b-td>0</b-td>
                  <b-td><b-button variant="info" @click="toBasicDesignView">前往處理</b-button></b-td>
                </b-tr>
                <b-tr>
                  <b-td>預先提案</b-td>
                  <b-td>56</b-td>
                  <b-td><b-button variant="info" @click="toPreProposeView">前往處理</b-button></b-td>
                </b-tr>
                <b-tr>
                  <b-td>新提案</b-td>
                  <b-td>18</b-td>
                  <b-td><b-button variant="info" @click="toBasicDesignView">前往處理</b-button></b-td>
                </b-tr>
                <b-tr>
                  <b-td>審查中</b-td>
                  <b-td>13</b-td>
                  <b-td><b-button variant="info" @click="toBasicDesignView">前往處理</b-button></b-td>
                </b-tr>
                <b-tr>
                  <b-td>已補正</b-td>
                  <b-td>0</b-td>
                  <b-td><b-button variant="info" @click="toBasicDesignView">前往處理</b-button></b-td>
                </b-tr>
              </b-tbody>
            </b-table-simple>
            </div>
            </b-collapse>
        </div>
    </div>
    </section>
    </div>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import NotificationService from '@/shared/notification/notification-service';
import { useValidation, validateState } from '@/shared/form';
import { onActivated, reactive, Ref, ref, toRef } from '@vue/composition-api';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required } from '@/shared/validators';
import { parseRocDate, formatDate } from '@/shared/date/minguo-calendar-utils';
import { navigateByNameAndParams } from '@/router/router';
// import axios from 'axios';
// import { notificationErrorHandler } from '@/shared/http/http-response-helper';
// import { egrCertificate } from '@/shared/model/cms-comp.model';
// import { Pagination } from '@/shared/model/pagination.model';
import { RoleEnum } from '@/shared/enum';

export default {
  name: 'rev0101Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
    form: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    // 切換角色
    const switchRoles = () => {
      if (roleRef.value === RoleEnum.ADMIN) roleRef.value = RoleEnum.USER;
      else roleRef.value = RoleEnum.ADMIN;
    };

    const isReloadProp = toRef(props, 'isReload');

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    const queryOptions = reactive({
  
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      typhoonName: '',
      recEngName: '',
      typeCode: '',
      County:'',
      //city:'',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      typhoonName: {},
      typeCode: {},
      County:{}

    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });
    var light = 'red';
    // 查詢結果及狀態
    const queryStatus: Ref<Boolean> = ref(true);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
     
    });

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.planListData = undefined;
          // dataPromise.value = axios
          //   // .post('/cms-comps/criteria', form)           //前端分頁
          //   .post('/cms-comps/criteria-jpa', form) //後端分頁
          //   .then(({ data }) => {
          //     // table.data = data.slice(0, data.length);   //前端分頁
          //     // table.totalItems = data.length;            //前端分頁
          //     table.data = data.content.slice(0, data.content.length); //後端分頁
          //     table.totalItems = data.totalElements; //後端分頁
          //   })
          //   .finally(() => (dataPromise.value = null))
          //   .catch(notificationErrorHandler(notificationService));
          
          table.totalItems = 4;
        }
      });
    };

    const toPlanEvaluation = () => {
      navigateByNameAndParams('rev0201Query',null);
    };
    const toPreProposeView = () => {
      navigateByNameAndParams('rev0301Query', null);
    };

    const toBasicDesignView = () => {
      navigateByNameAndParams('rev0401Query', null);
    };

    // const handlePaginationChanged = (pagination: Pagination) => {
    //   Object.assign(form, pagination);
    //   handleQuery();
    // };

    const optionsFormatter = (option: string, value: string) => {
      let itemFound = queryOptions[option].find((item: { value: string }) => item.value === value);
      if (itemFound) {
        return itemFound.text;
      } else {
        return '';
      }
    };

    return {
      validateState,
      queryOptions,
      $v,
      stepVisible,
      queryStatus,
      dataPromise,
      table,
      reset,
      light,
      handleQuery,
      toPreProposeView,
      toPlanEvaluation,
      toBasicDesignView,
      roleRef,
      RoleEnum,
      ref,
      switchRoles,
      formatDate,
      optionsFormatter,
      // handlePaginationChanged,
    };
  },
};
</script>

<style>
</style>

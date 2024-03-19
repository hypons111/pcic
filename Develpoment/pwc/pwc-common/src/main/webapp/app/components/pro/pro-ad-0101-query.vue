<template>
  <div>
    <section class="container mt-2">
      <!--    <section class="container mt-2" v-if="roleRef === RoleEnum.ADMIN">-->
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件(後台)
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
        <!--        創新產品名稱-->
        <div class="card-body col-lg-12">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <i-form-group-check :label="$t('label.innovateName')" :item="$v.innovateName">
                <b-form-input v-model="$v.innovateName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!--            廠商名稱-->
            <b-form-row>
              <i-form-group-check :label="$t('label.companyName')" :item="$v.companyName">
                <b-form-input v-model="$v.companyName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!--            交流機關名稱-->
            <b-form-row>
              <i-form-group-check :label="$t('label.communicateOrganName')" :item="$v.acceptedOrganization">
                <b-form-input v-model="$v.acceptedOrganization.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!--            交流狀態-->
            <b-form-row>
              <i-form-group-check class="col-12" label-cols="2" content-cols="10" label="交流狀態" :item="$v.communicateType">
                <!--selected  <b-form-input v-if="formStatusRef === formStatusEnum.INFO" plaintext disabled :value="queryOptionsFormatter('communicateType', $v.communicateType.$model)"></b-form-input> -->
                <b-form-radio-group
                  class="pt-2"
                  v-model="$v.communicateType.$model"
                  :options="queryOptions.communicateTypeOptions"
                  :state="validateState($v.communicateType)"
                ></b-form-radio-group>
                <!--                {{$v.communicateType.$model}}-->
              </i-form-group-check>
              <!--              {{form}}-->
            </b-form-row>
            <!--            編輯狀態-->
            <b-form-row>
              <i-form-group-check class="col-12" label-cols="2" content-cols="10" label="編輯狀態" aria-label="" :item="$v.status">
                <b-form-radio-group
                  class="pt-2"
                  v-model="$v.statusInnovativeProduct.$model"
                  :options="queryOptions.statusOptions"
                  :state="validateState($v.statusInnovativeProduct)"
                ></b-form-radio-group>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>
    <!--新增按鈕-->
    <!--        秀出檢視的button-->
    <!--    <template #cell(action)="row">-->
    <!--      <i-button class="text-nowrap" size="sm" type="binoculars"-->
    <!--                @click="toEditView(row.item, formStatusEnum.INFO)"></i-button>-->
    <!--      <br/>-->
    <!--    </template>-->
    <!--    新增-->
    <section class="container mt-2">
      <i-button type="file-earmark-plus" @click="toCreateView(new ProInnovativeProductSumSearchDetail(), formStatusEnum.CREATE)"></i-button>
    </section>
    <!--    , editTypeEnum.DRAFT-->
    <section class="container mt-2" v-if="queryStatus">
      <i-table
        class="table-sm"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields_backend"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged($event)"
      >
        <template #cell(action)="row">
          <i-button class="text-nowrap" size="sm" type="binoculars" @click="toEditView(row.item, formStatusEnum.INFO)"></i-button>
          <br />
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, onMounted, watch, toRef, onActivated } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { parseRocDate, formatDate, parseDate } from '@/shared/date/minguo-calendar-utils';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { Pagination } from '@/shared/model/pagination.model';
import { required } from '@/shared/validators';
import {
  IProInnovativeProductSumSearchDetail,
  ProInnovativeProductSumSearchDetail,
} from '@/shared/model/proModel/pro-innovative-product-sum-search-detail-dto';
import { editTypeEnum } from '@/shared/enum';

export default {
  name: 'proAd0101Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
      REMAKE = 'remake',
    }

    enum roleEnum {
      USER = '使用者',
      ADMIN = '管理員',
    }
    // 手動定義角色
    const roleRef: Ref<roleEnum> = ref(roleEnum.ADMIN);

    //isReload??
    const isReloadProp = toRef(props, 'isReload');

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    const queryOptions = reactive({
      communicateTypeOptions: [
        { value: 'ALLPRODUCT', text: '所有產品' },
        { value: 'COMMUNICATING', text: '交流中' },
        { value: 'COMMUNICATED', text: '已交流' },
        { value: 'NOCOMMUNICATE', text: '不交流' },
      ],
      statusOptions: [
        { value: '', text: '全部' },
        { value: '1', text: '公開' },
        { value: '2', text: '草稿' },
        { value: '3', text: '送審中' },
        { value: '4', text: '已下架' },
      ],
      enginType: [
        { value: '0', text: '建築' },
        { value: '1', text: '道路運輸' },
        { value: '2', text: '軌道運輸' },
        { value: '3', text: '機場' },
        { value: '4', text: '港灣' },
        { value: '5', text: '水庫及蓄水' },
        { value: '6', text: '電業設備' },
        { value: '7', text: '水利' },
        { value: '8', text: '自來水' },
        { value: '9', text: '共同管道' },
        { value: '10', text: '下水道' },
        { value: '11', text: '焚化廠' },
        { value: '12', text: '垃圾掩埋場' },
        { value: '13', text: '土地開發' },
        { value: '14', text: '水土保持' },
        { value: '15', text: '其他' },
      ],
    });

    //定義交流狀態
    // const communicateStatusRef = ref(queryOptions.communicateType);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件 ex.''=字串 []=陣列
    const formDefault = {
      communicateType: 'ALLPRODUCT',
      statusInnovativeProduct: '',
      innovateName: '',
      companyName: '',
      acceptedOrganization: '',
    };

    // 建立整個的表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      communicateType: { notNull: required },
      statusInnovativeProduct: {},
      innovateName: {},
      companyName: {},
      acceptedOrganization: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
      // table的th
      fields_backend: [
        {
          key: 'innovateName',
          label: '創新產品名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'statusInnovativeProduct',
          label: '項目狀態',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => queryOptionsFormatter('statusOptions', value),
        },
        {
          key: 'companyName',
          label: '廠商名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'acceptedOrganization',
          label: '交流機關名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'communicateType',
          label: '交流狀態',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => queryOptionsFormatter('communicateTypeOptions', value),
        },
        {
          key: 'action',
          label: '',
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
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          // console.log('form', form);
          dataPromise.value = axios
            .post('/pro-innovative-products/proCriteria-pageBackend', form)
            .then(({ data }) => {
              // table.data = data.slice(0, data.length); //前端分頁
              // table.totalItems = data.length; //前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
              // notificationService.info('查詢成功');
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };
    //進入編輯頁面並帶入資料
    const toEditView = (proBasicForm: any, formStatus: formStatusEnum) => {
      navigateByNameAndParams('proAd0101EditInfoBack', {
        proBasicForm: proBasicForm,
        formStatus: formStatus,
        role: roleRef.value,
      });
      console.log('proBasicForm', proBasicForm);
    };

    //新增
    const toCreateView = (proInnovativeProductSumSearchDetail: IProInnovativeProductSumSearchDetail, formStatus: formStatusEnum) => {
      navigateByNameAndParams('proAd0101EditInfoBack', {
        proInnovativeProductSumSearchDetail: proInnovativeProductSumSearchDetail,
        formStatus: formStatus,
        role: roleRef.value,
      });
      console.log('proInnovativeProductSumSearchDetail', proInnovativeProductSumSearchDetail);
    };

    //控制Options
    const queryOptionsFormatter = (option: string, value: string) => {
      let itemFound = queryOptions[option].find(item => item.value === value);

      if (itemFound) {
        return itemFound.text;
      } else {
        return '';
      }
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      toEditView,
      validateState,
      form,
      queryOptions,
      $v,
      stepVisible,
      reset,
      roleRef,
      formStatusEnum,
      roleEnum,
      ref,
      queryStatus,
      dataPromise,
      handleQuery,
      table,
      queryOptionsFormatter,
      // switchRoles,
      handlePaginationChanged,
      toCreateView,
      ProInnovativeProductSumSearchDetail,
    };
  },
};
</script>

<style scoped></style>

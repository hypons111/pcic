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
              <!-- 姓名 -->
              <i-form-group-check class="col-12" label-cols="4" content-cols="4" :label="$t('label.userName') + '：'" :item="$v.chName">
                <b-form-input v-model.trim="$v.chName.$model"></b-form-input>
              </i-form-group-check>
              <!-- 身分證 -->
              <i-form-group-check class="col-12" label-cols="4" content-cols="4" :label="$t('label.idno') + '：'" :item="$v.compIdno">
                <b-form-input v-model="$v.idno.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="clickQueryBtn"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2">
      <!--切換角色按鈕-->
      <b-button size="md" variant="warning" @click="switchRoles">
        <font-awesome-icon :icon="['fas', 'people-arrows']" />
        {{ roleRef }}
      </b-button>
    </section>

    <section class="container mt-2" v-show="queryStatus">
      <i-table
        small
        ref="iTableComponent"
        itemsUndefinedBehavior="loading"
        :items="table.data"
        :fields="table.fields_backend"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >
        <template #cell(tel)="row">
          (公) {{ row.item.tel1 }}<br />
          (住) {{ row.item.tel2 }}<br />
          (手機) {{ row.item.tel3 }}
        </template>
        <template #cell(addr)="row">
          <!-- (戶籍){{ row.item.permanentAddrZip }} {{ row.item.permanentAddrCity }} {{ row.item.permanentAddrTown }}
          {{ row.item.permanentAddrOther }}<br /> -->
          (戶籍)<i-address-picker
            :isText="true"
            :zip="row.item.permanentAddrZip"
            :city="row.item.permanentAddrCity"
            :town="row.item.permanentAddrTown"
            :other="row.item.permanentAddrOther"
          ></i-address-picker>
          <!-- (通訊){{ row.item.contactAddrZip }} {{ row.item.contactAddrCity }} {{ row.item.contactAddrTown }}
          {{ row.item.contactAddrOther }} -->
          (通訊)<i-address-picker
            :isText="true"
            :zip="row.item.contactAddrZip"
            :city="row.item.contactAddrCity"
            :town="row.item.contactAddrTown"
            :other="row.item.contactAddrOther"
          ></i-address-picker>
        </template>
        <template #cell(action)="row">
          <!-- <i-button class="mb-1" size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY)"></i-button> -->
          <i-button class="mb-1" size="sm" type="binoculars" @click="toReadView(row.item, row.index)"></i-button>
          <i-button class="mb-1" size="sm" type="pencil-square" @click="toEditView(row.item, row.index)"></i-button>
          <b-button class="mb-1" size="sm" type="" @click="toEngInfoView(row.item)">技師資訊</b-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import NotificationService from '@/shared/notification/notification-service';
import { useValidation, validateState } from '@/shared/form';
import { onMounted, onActivated, reactive, Ref, ref, toRef, toRefs } from '@vue/composition-api';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { parseRocDate, formatDate } from '@/shared/date/minguo-calendar-utils';
import { navigateByNameAndParams } from '@/router/router';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
// import { egrCertificate } from '@/shared/model/cms-comp.model';
import { Pagination } from '@/shared/model/pagination.model';
import { RoleEnum, FormStatusEnum } from '@/shared/enum';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';

interface propsType {
  isReload: boolean;
  criteria: any;
}

export default {
  name: 'Eng0706Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
    form: {
      type: Object,
      required: false,
    },
    criteria: {
      required: false,
      type: Object,
    },
  },
  setup(props, context) {
    const admSysCodeService = new AdmSysCodeService();

    const { isReload, criteria } = toRefs(props);
    const iTableComponent = ref(null); // 綁定 i-table 元件用

    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    // 切換角色
    const switchRoles = () => {
      if (roleRef.value === RoleEnum.ADMIN) {
        roleRef.value = RoleEnum.USER;
        navigateByNameAndParams('Eng0706EditInfo', { role: roleRef.value });
      } else roleRef.value = RoleEnum.ADMIN;

      // console.log(roleRef.value);
    };

    const isReloadProp = toRef(props, 'isReload');

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
      // iTableComponent內的currentPage改變就會觸發handlePaginationChanged事件
      iTableComponent.value.state.pagination.currentPage = criteria.value.currentPage + 1;
    });

    onMounted(() => {
      if (roleRef.value === RoleEnum.USER) {
        navigateByNameAndParams('Eng0706Read');
      }
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const queryOptions = reactive({});

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      chName: '',
      idno: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault, new Pagination()));

    // 表單物件驗證規則
    const rules = {
      chName: {},
      idno: {},
    };

    const formOptions = reactive({
      sex: [
        // { value: true, text: '男' },
        // { value: false, text: '女' },
        // { value: '1', text: '男' },
        // { value: '0', text: '女' },
      ],
    });

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus: Ref<boolean> = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    // const tel = [data[0].tel1, data[0].tel2];
    // console.log(tel);

    const table = reactive({
      fields_backend: [
        // {
        //   // 序號
        //   key: 'no',
        //   label: i18n.t('label.no'),
        //   sortable: false,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        // },
        {
          // 姓名
          key: 'chName',
          label: i18n.t('label.userName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 性別
          key: 'sex',
          label: i18n.t('label.sex'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: boolean) => formOptionsFormatter(formOptions.sex, value),
        },
        {
          // 出生日期
          key: 'birthDate',
          label: i18n.t('label.birthDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
          // formatter: (value: Date) => formatDate(value, '/'),
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
          // 電話
          key: 'tel',
          label: i18n.t('label.tel'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 地址
          key: 'addr',
          label: i18n.t('label.addr'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 動作
          key: 'action',
          label: '',
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
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          dataPromise.value = axios
            // .post('/cms-comps/criteria', form)           //前端分頁
            .post('/eng-members/criteria-jpa', form) //後端分頁
            .then(({ data }) => {
              // console.log(data);
              // table.data = data.slice(0, data.length);   //前端分頁
              // table.totalItems = data.length;            //前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const toEditView = (engMember: EngMember, index?: number) => {
      // navigateByNameAndParams('Eng0706EditInfo', { engMember: engMember });
      // navigateByNameAndParams('Eng0706EditInfo', { engMember: engMember, formStatus: formStatus });
      const criteria = Object.assign({}, form);
      criteria['index'] = index;
      criteria['totalItems'] = table.totalItems;
      navigateByNameAndParams('Eng0706EditInfo', { engMember: engMember, role: roleRef.value, criteria: criteria });
    };
    const toReadView = (engMember: EngMember, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['index'] = index;
      criteria['totalItems'] = table.totalItems;
      navigateByNameAndParams('Eng0706Read', { engMember: engMember, criteria: criteria });
    };
    const toEngInfoView = (engMember: EngMember) => {
      navigateByNameAndParams('Eng0706EngInfo', { engMember: engMember });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const optionsFormatter = (option: string, value: string) => {
      let itemFound = queryOptions[option].find((item: { value: string }) => item.value === value);
      if (itemFound) {
        return itemFound.text;
      } else {
        return '';
      }
    };

    // 點擊查詢按鈕時先將 i-table 元件內的 currentPage(當前頁面) 回到 1，再執行查詢函式。
    const clickQueryBtn = () => {
      iTableComponent.value.state.pagination.currentPage = 1;
      if (!form.currentPage) iTableComponent.value.emitPagination();
    };

    return {
      iTableComponent,
      validateState,
      queryOptions,
      $v,
      stepVisible,
      queryStatus,
      dataPromise,
      table,
      reset,
      handleQuery,
      clickQueryBtn,
      toReadView,
      toEditView,
      toEngInfoView,
      roleRef,
      RoleEnum,
      // FormStatusEnum,
      ref,
      switchRoles,
      formatDate,
      optionsFormatter,
      handlePaginationChanged,
    };
  },
};
</script>
<style></style>

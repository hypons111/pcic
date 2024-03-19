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
        <div class="card-body col-12">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <i-form-group-check :label="$t('label.comName') + '：'" :item="$v.comName">
                <b-form-input v-model="$v.comName.$model" placeholder="請輸入公司名稱"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.compIdno') + '：'" :item="$v.compIdno">
                <b-form-input v-model="$v.compIdno.$model" placeholder="請輸入統一編號"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!-- 負責人 -->
            <b-form-row>
              <i-form-group-check :label="$t('label.rpName') + '：'" :item="$v.rpName">
                <b-form-input v-model="$v.rpName.$model" placeholder="請輸入負責人姓名"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <b-col offset="8">
                <i-button type="search" v-promise-btn="{ promise: dataPromise }" @click="clickQueryBtn"></i-button>
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

    <section class="container mt-2" v-if="queryStatus">
      <i-table
        class="table-sm"
        itemsUndefinedBehavior="loading"
        :items="table.data"
        :fields="table.fields_backend"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
        ref="iTableComponent"
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
          <i-button class="mb-1" size="sm" type="binoculars" @click="toReadView(row.item)"></i-button>
          <i-button class="mb-1" size="sm" type="pencil-square" @click="toEditView(row.item, roleRef.value)"></i-button>
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
import { onMounted, onActivated, reactive, Ref, ref, toRef } from '@vue/composition-api';
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
  },
  setup(props, context) {
    const admSysCodeService = new AdmSysCodeService();
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
      comName: '',
      compIdno: '',
      rpName: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      comName: '',
      compIdno: '',
      rpName: '',
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
          // 公司名稱
          key: 'comName',
          label: i18n.t('label.comName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 統一編號
          key: 'compIdno',
          label: i18n.t('label.compIdno'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          //formatter: (value: boolean) => formOptionsFormatter(formOptions.sex, value),
        },
        {
          // 負責人
          key: 'rpName',
          label: i18n.t('label.rpName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
          // formatter: (value: Date) => formatDate(value, '/'),
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

    const toEditView = (engMember: EngMember) => {
      // navigateByNameAndParams('Eng0706EditInfo', { engMember: engMember });
      // navigateByNameAndParams('Eng0706EditInfo', { engMember: engMember, formStatus: formStatus });
      navigateByNameAndParams('Eng0706EditInfo', { engMember: engMember, role: roleRef.value });
    };
    const toReadView = (engMember: EngMemberm) => {
      navigateByNameAndParams('Eng0706Read', { engMember: engMember });
    };
    const toEngInfoView = (engMember: EngMember) => {
      navigateByNameAndParams('Eng0706EngInfo', { engMember: engMember });
    };

    // const deleteEgrCertificate = engEngrCertificateId => {
    //   $bvModal.msgBoxConfirm(`是否確認刪除技師${engEngrCertificateId}證書資料？`).then((isOK: boolean) => {
    //     if (isOK) {
    //     }
    //   });
    // };

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

    const iTableComponent = ref(null); // 綁定 i-table 元件用

    // 點擊查詢按鈕時先將 i-table 元件內的 currentPage(當前頁面) 回到 1，再執行查詢函式。
    const clickQueryBtn = () => {
      if (iTableComponent.value) iTableComponent.value.state.pagination.currentPage = 1;
      handleQuery();
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
      // deleteEgrCertificate,
      handlePaginationChanged,
    };
  },
};
</script>
<style></style>

<template>
  <div>
    <section class="container mt-2" v-if="roleRef === RoleEnum.ADMIN">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                自選縣市提報完工期限展延案件統計表篩選條件
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
              <!-- 颱風(多選) -->
               <i-form-group-check :label="$t('label.typhoonName')+':'" :item="$v.typhoonName" :labelStar="true" >
                <i-group-select-checkbox
                    :options="queryOptions.typhoonList"
                    :isDropDown="true"
                    :state="validateState($v.typhoonName)"
                    v-model="$v.typhoonName.$model">
                </i-group-select-checkbox>
              </i-form-group-check>
              <!-- 工程類別(多選) -->
              <i-form-group-check :label="$t('label.typeCode')+':'" :item="$v.typeCode" :labelStar="true" >
                <i-group-select-checkbox
                    :options="queryOptions.typeCode"
                    :isDropDown="true"
                    :state="validateState($v.typeCode)"
                    v-model="$v.typeCode.$model"
                    >
                </i-group-select-checkbox>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 縣市(多選) -->
              <i-form-group-check :label="$t('label.County')+':'" :item="$v.County" :labelStar="true" >
                <i-group-select-checkbox
                    :options="queryOptions.County"
                    :isDropDown="true"
                    :state="validateState($v.County)"
                    v-model="$v.County.$model"
                    >
                </i-group-select-checkbox>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <i-button type="search" v-promise-btn="{ promise: dataPromise }" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2" v-if="queryStatus">
       <p style="text-align:center">108年8月利奇馬及白鹿颱風災後復建工程統計一覽表</p>
       <b-table
        striped
        bordered
        style="background-color:white"
        class="table-sm"
        itemsUndefinedBehavior="loading"
        :items="table.AugustRecData"
        :fields="table.fields_backend"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      ></b-table>

      <br>
       <p style="text-align:center">108年7月豪雨災後復建工程統計一覽表</p>
       <b-table
        striped
        bordered
        style="background-color:white"
        class="table-sm"
        itemsUndefinedBehavior="loading"
        :items="table.JulyRecData"
        :fields="table.fields_backend"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      ></b-table>

      <br>
       <p style="text-align:center">108年6月豪雨災後復建工程統計一覽表</p>
       <b-table
        striped
        bordered
        style="background-color:white"
        class="table-sm"
        itemsUndefinedBehavior="loading"
        :items="table.JuneRecData"
        :fields="table.fields_backend"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      ></b-table>

       <br>
       <p style="text-align:center">108年非行政院專案(縣市自行管控)災後復建工程統計一覽表</p>
       <b-table
        striped
        bordered
        style="background-color:white"
        class="table-sm"
        itemsUndefinedBehavior="loading"
        :items="table.nEYData"
        :fields="table.fields_backend"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      ></b-table>
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
  name: 'rec0221Query',
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
      typeCode: [
        {
          parents:'全選',
          child:[
            //{ value: '', text:'無(請選擇)'},
            { value: '0', text: 'A1	水利工程' },
            { value: '1', text: 'B1	觀光工程' },
            { value: '3', text: 'C1	道路橋梁工程-公路系統工程' },
            { value: '4', text: 'C2	道路橋梁工程-村里聯絡道路工程' },
            { value: '5', text: 'D1	建築工程' },
            { value: '6', text: 'E1	下水道工程' },
            { value: '7', text: 'F1	共同管道工程' },
            { value: '8', text: 'G1	水土保持工程' },
            { value: '9', text: 'H1	農水路工程-農地重劃區農水路工程' },
            { value: '10', text: 'H2 農水路工程-養殖漁業專區農水路工程' },
            { value: '11', text: 'H3 農水路工程-其他農路工程' },
            { value: '12', text: 'I1 林道工程' },
            { value: '13', text: 'J1 森林遊樂區設施工程' },
            { value: '14', text: 'K1 漁港工程' },
            { value: '15', text: 'L1 學校工程' },
            { value: '16', text: 'M1 環境保護工程' },
            { value: '17', text: 'N1 原住民族部落聯絡道及環境工程' },
            { value: '18', text: 'P1 文化資產工程' },
            ],
        },
      ],
      typhoonList: [
        {
          parents: '-----1月颱風',
          child: [
            { value: '1', text: 'xx颱風' },
            { value: '2', text: 'yy颱風' },
            { value: '3', text: 'zz颱風' },
            { value: '4', text: 'pp颱風' },
          ],
        },
        {
          parents: '-----2月颱風',
          child: [
            { value: '5', text: '5號颱風' },
            { value: '6', text: '6號颱風' },
            { value: '7', text: '7號颱風' },
            { value: '8', text: '8號颱風' },
          ],
        },
      ],
      County: [
        {
          parents:'-----北部地區',
          child:[
            { value: '0', text: '基隆市' },
            { value: '1', text: '臺北市' },
            { value: '3', text: '新北市' },
            { value: '4', text: '桃園市' },
            { value: '5', text: '新竹市' },
            { value: '6', text: '新竹縣' },
            { value: '7', text: '宜蘭縣' }
            ],
        },
        {
          parents:'-----中部地區',
          child:[  
            { value: '8', text: '苗栗縣' },
            { value: '9', text: '臺中市' },
            { value: '10', text: '彰化縣' },
            { value: '11', text: '南投縣' },
            { value: '12', text: '雲林縣' },
            ],
        },
        {
          parents:'-----南部地區',
          child:[
            { value: '13', text: '嘉義市' },
            { value: '14', text: '嘉義縣' },
            { value: '15', text: '臺南市' },
            { value: '16', text: '高雄市' },
            { value: '17', text: '屏東縣' },
            { value: '18', text: '澎湖縣' },
            { value: '19', text: '金門縣' },
            { value: '20', text: '連江縣' },
            ],
        },
        {
          parents:'-----東部地區',
          child:[
            { value: '21', text: '臺東縣' },
            { value: '22', text: '花蓮縣' },   
            ]
        },
      ],
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
      typhoonName: {required:required},
      typeCode: {required:required},
      County:{required:required}

    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus: Ref<Boolean> = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
      fields_backend: [
        {
           //工程類別
          key: 'typeCode',
          label: i18n.t('label.typeCode'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },

        {
          // 提報件數
          key: 'submittedQuantity',
          label: '提報件數',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },

         {
           //提報預算
          key: 'submittedBudget',
          label: '提報預算',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },

        {
          // 核定件數
          key: 'frQtty',
          label: '核定件數',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },

         {
          // 核定預算
          key: 'frBdgt',
          label: '核定預算',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
      ],
      nEYData: undefined,
      JulyRecData: undefined,
      JuneRecData: undefined,
      AugustRecData: undefined,
      totalItems: undefined,
    });

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.nEYData = undefined;
          table.JuneRecData = undefined;
          table.JulyRecData = undefined;
          table.AugustRecData = undefined;
          queryStatus.value = true;
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
          
          table.AugustRecData = [
            {
              typeCode: 'A1',
              submittedQuantity: '21',
              submittedBudget: '38,755',
              frQtty: '20',
              frBdgt: '40,389'
            },
            {
              typeCode: 'C1',
              submittedQuantity: '16',
              submittedBudget: '21,934',
              frQtty: '16',
              frBdgt: '81,639'
            },
            {
              typeCode: 'C2',
              submittedQuantity: '9',
              submittedBudget: '21,934',
              frQtty: '9',
              frBdgt: '17,232'
            },
            {
              typeCode: 'G1',
              submittedQuantity: '47',
              submittedBudget: '67,055',
              frQtty: '46',
              frBdgt: '64,362'
            },
            {
              typeCode: 'H3',
              submittedQuantity: '101',
              submittedBudget: '133,265',
              frQtty: '94',
              frBdgt: '121,857'
            },
            {
              typeCode: 'N1',
              submittedQuantity: '13',
              submittedBudget: '33,842',
              frQtty: '12',
              frBdgt: '31,157'
            },
            {
              typeCode: '合計',
              submittedQuantity: '207',
              submittedBudget: '370,065',
              frQtty: '197',
              frBdgt: '356,636'
            },
          ];
          table.JulyRecData = [
            {
              typeCode: 'A1',
              submittedQuantity: '2',
              submittedBudget: '1,486',
              frQtty: '2',
              frBdgt: '2,071'
            },
            {
              typeCode: 'C1',
              submittedQuantity: '3',
              submittedBudget: '7,907',
              frQtty: '3',
              frBdgt: '11,811'
            },
            {
              typeCode: 'C2',
              submittedQuantity: '1',
              submittedBudget: '951',
              frQtty: '1',
              frBdgt: '1,035'
            },
            {
              typeCode: 'G1',
              submittedQuantity: '5',
              submittedBudget: '8,110',
              frQtty: '5',
              frBdgt: '7,222'
            },
            {
              typeCode: 'H3',
              submittedQuantity: '11',
              submittedBudget: '11,705',
              frQtty: '9',
              frBdgt: '10,496'
            },
            {
              typeCode: '合計',
              submittedQuantity: '22',
              submittedBudget: '30,159',
              frQtty: '20',
              frBdgt: '32,635'
            },
          ];
          table.JuneRecData = [
            {
              typeCode: 'A1',
              submittedQuantity: '26',
              submittedBudget: '40,573',
              frQtty: '26',
              frBdgt: '42,250'
            },
            {
              typeCode: 'C1',
              submittedQuantity: '34',
              submittedBudget: '134,275',
              frQtty: '34',
              frBdgt: '107,853'
            },
            {
              typeCode: 'C2',
              submittedQuantity: '7',
              submittedBudget: '76,057',
              frQtty: '6',
              frBdgt: '16,013'
            },
            {
              typeCode: 'G1',
              submittedQuantity: '59',
              submittedBudget: '65,197',
              frQtty: '58',
              frBdgt: '60,243'
            },
            {
              typeCode: 'H3',
              submittedQuantity: '142',
              submittedBudget: '197,942',
              frQtty: '139',
              frBdgt: '185,612'
            },
            {
              typeCode: 'N1',
              submittedQuantity: '14',
              submittedBudget: '48,457',
              frQtty: '13',
              frBdgt: '43,588'
            },
            {
              typeCode: '合計',
              submittedQuantity: '284',
              submittedBudget: '566,932',
              frQtty: '278',
              frBdgt: '459,619'
            },
          ];
          table.nEYData = [
            {
              typeCode: '合計',
              submittedQuantity: '0',
              submittedBudget: '0',
              frQtty: '0',
              frBdgt: '0'
            },
          ];
          table.totalItems = 4;
        }
      });
    };

    //const toEditView = (engYearreport: any) => {
    //  navigateByNameAndParams('Eng0202EditInfo', { engYearreport: engYearreport, role: roleRef.value });
    //};

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
      handleQuery,
      //toEditView,
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
<style></style>

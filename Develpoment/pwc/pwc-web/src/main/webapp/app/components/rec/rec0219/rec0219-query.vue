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
      <div class="text-right">
           <i-button type="box-arrow-up-right"></i-button>
      </div>
       <i-table
        class="table-sm"
        itemsUndefinedBehavior="loading"
        :items="table.data"
        :fields="table.fields_backend"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      >
        <!--              @changePagination="handlePaginationChanged($event)"-->
        <!--            >-->
        <!--template #cell(action)="row">
          <b-button variant="info" @click="openViewProject">檢視工程</b-button>
          <b-button variant="info" class="mt-2" @click="openViewNearbyProjects">檢視鄰近案件</b-button>
        </template-->
      </i-table>
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
  name: 'rec0219Query',
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
           //颱風名稱
          key: 'typhoonName',
          label: i18n.t('label.typhoonName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },

        {
          // 核列序號
          key: 'approveNo',
          label: i18n.t('label.approveNo'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },

         {
           //工程類別
          key: 'typeCode',
          label: i18n.t('label.typeCode'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },

        {
          // 機關名稱
          key: 'agencyName',
          label: i18n.t('label.agencyName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

         {
          // 復健工程名稱
          key: 'recEngName',
          label: i18n.t('label.recEngName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          // 鄉鎮市區
          key: 'city',
          label: i18n.t('label.city'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 村里
          key: 'village',
          label: i18n.t('label.village'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 審議經費(千元)
          key: 'reviewAmount',
          label: i18n.t('label.reviewAmount'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
         {
          // 實際進度(%)
          key: 'bidProgress',
          label: i18n.t('label.bidProgress'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
         {
          // 完工期限
          key: 'scheduledFinishDate',
          label: i18n.t('label.scheduledFinishDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
         {
          // 發包日期
          key: 'bidDate',
          label: i18n.t('label.bidDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
         {
          // 完工日期
          key: 'aeDate',
          label: i18n.t('label.aeDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
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
          table.data = [
            {
              typhoonName: '108年6月豪雨',
              approveNo: '022',
              typeCode: 'G1',
              agencyName: '南投縣政府',
              recEngName: '埔里鎮合成里史港野溪下游護岸災修復建工程',
              city: '埔里鎮',
              village: '合成里', 
              reviewAmount: '1,704',
              bidProgress: '100.00%',
              scheduledFinishDate: '2020.06.30',
              bidDate: '2019.11.28',
              aeDate: '2020.06.11'        
            },
            {
              typhoonName: '108年6月豪雨',
              approveNo: '004',
              typeCode: 'G1',
              agencyName: '南投縣政府',
              recEngName: '中寮鄉永福村崩埤野溪災修復建工程',
              city: '中寮鄉',
              village: '永福村', 
              reviewAmount: '1,075',
              bidProgress: '100.00%',
              scheduledFinishDate: '2020.04.30',
              bidDate: '2020.02.25',
              aeDate: '2020.04.24'        
            },
            {
              typhoonName: '108年8月利奇馬與白鹿颱風',
              approveNo: '034',
              typeCode: 'H3',
              agencyName: '南投縣政府',
              recEngName: '名間鄉萬丹村農路坍方災修復建工程',
              city: '名間鄉',
              village: '萬丹村', 
              reviewAmount: '4,531',
              bidProgress: '1.00%',
              scheduledFinishDate: '2021.06.30',
              bidDate: '2021.01.26',
              aeDate: ''        
            },
            {
              typhoonName: '108年8月利奇馬與白鹿颱風',
              approveNo: '011',
              typeCode: 'G1',
              agencyName: '南投縣政府',
              recEngName: '仁愛鄉萬豐村投83線20.2k上邊坡災修復建工程',
              city: '仁愛鄉',
              village: '萬豐村', 
              reviewAmount: '37,990',
              bidProgress: '83.00%',
              scheduledFinishDate: '2021.03.31',
              bidDate: '2020.09.22',
              aeDate: ''        
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

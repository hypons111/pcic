<template>
  <div>
    <section class="container mt-2" v-if="roleRef === RoleEnum.ADMIN">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="plus-square-fill"></font-awesome-icon>
                整體概況
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
            <b-table-simple responsive>
              <b-thead>
                <b-tr>
                  <!--b-th sticky-column>Sticky Column Header</b-th>
                  <b-th>Heading 1</b-th>
                  <b-th>Heading 2</b-th>
                  <b-th>Heading 3</b-th>
                  <b-th>Heading 4</b-th-->
                </b-tr>
              </b-thead>
              <b-tbody style="text-align:right">
                <b-tr>
                  <!--b-th sticky-column>Sticky Column Row Header</b-th>
                  <b-td>Cell</b-td-->
                  <b-td></b-td>
                  <b-td>現在日期:</b-td>
                  <b-td>使用者:一般訪客</b-td>
                </b-tr>
              </b-tbody>
            </b-table-simple>

            <b-table-simple>
            <b-tbody style="text-align:left">
                <b-tr>
                  <b-td><div style="font-size:125%">整體狀況燈號:<img src="../../../../content/images/custom/lic-light-yellow-46x46.png" width="30" height="30"></div>
                        <br>
                        <div style="font-size:125%">證照取得(%):73</div>
                  </b-td>
                  <b-td style="text-align:left">
                        <div style="font-size:125%">計畫列表</div>
                        <br>
                        <div style="font-size:125%">執行中計畫數:18</div>
                  </b-td>
                  <b-td>
                        <div class="d-inline">紅燈<img src="../../../../content/images/custom/lic-light-red-46x46.png" width="25" height="25">:7</div><div class="d-inline" style="font-size:75%">(證照未如期取得)</div>
                        <br>
                        <div class="d-inline">黃燈<img src="../../../../content/images/custom/lic-light-yellow-46x46.png" width="25" height="25">:5</div><div class="d-inline" style="font-size:75%">(證照未如期送審)</div>
                        <br>
                        <div class="d-inline">綠燈<img src="../../../../content/images/custom/lic-light-green-46x46.png" width="25" height="25">:6</div><div class="d-inline" style="font-size:75%">(無上述兩種情況)</div>
                        <br>
                        <div class="d-inline">灰燈<img src="../../../../content/images/custom/lic-light-gray-46x46.png" width="25" height="25">:0</div><div class="d-inline" style="font-size:75%">(目前尚無應辦證照)</div>
                  </b-td>
                </b-tr>
              </b-tbody>
            </b-table-simple>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2" v-if="queryStatus">
       <p>各類別計畫證照完成(%):</p>
       <b-table-simple bordered striped style="background-color:white">
              <b-thead>
                <b-tr>
                  <b-th>計畫類型</b-th>
                  <b-th>交通建設</b-th>
                  <b-th>環境資源</b-th>
                  <b-th>經濟能源</b-th>
                  <b-th>都市開發</b-th>
                  <b-th>文化設施</b-th>
                  <b-th>教育設施</b-th>
                  <b-th>農業設施</b-th>
                  <b-th>衛生福利</b-th>
                </b-tr>
              </b-thead>
              <b-tbody style="text-align:right" >
                <b-tr>
                  <!--b-th sticky-column>Sticky Column Row Header</b-th>
                  <b-td>Cell</b-td-->
                  <b-td style="text-align:center">證照完成(%)</b-td>
                  <b-td>68</b-td>
                  <b-td>80</b-td>
                  <b-td>100</b-td>
                  <b-td>-</b-td>
                  <b-td>-</b-td>
                  <b-td>-</b-td>
                  <b-td>-</b-td>
                  <b-td>-</b-td>
                </b-tr>
              </b-tbody>
            </b-table-simple>

            <p>各證照類別取得(%):</p>
            <b-table-simple bordered striped style="background-color:white">
              <b-thead>
                <b-tr>
                  <b-th>證照類別</b-th>
                  <b-th>環境影響</b-th>
                  <b-th>水土保持</b-th>
                  <b-th>非都編定</b-th>
                  <b-th>都市計畫</b-th>
                  <b-th>用地取得</b-th>
                  <b-th>都設審議</b-th>
                  <b-th>綠建築</b-th>
                  <b-th>文化保存</b-th>
                  <b-th>建築許可</b-th>
                  <b-th>河川許可</b-th>
                  <b-th>跨鐵公路</b-th>
                  <b-th>管線處理</b-th>
                  <b-th>其它法令</b-th>
                </b-tr>
              </b-thead>
              <b-tbody style="text-align:right">
                <b-tr>
                  <!--b-th sticky-column>Sticky Column Row Header</b-th>
                  <b-td>Cell</b-td-->
                  <b-td style="text-align:center">證照完成(%)</b-td>
                  <b-td>95</b-td>
                  <b-td>80</b-td>
                  <b-td>100</b-td>
                  <b-td>100</b-td>
                  <b-td>100</b-td>
                  <b-td>31</b-td>
                  <b-td>50</b-td>
                  <b-td>80</b-td>
                  <b-td>78</b-td>
                  <b-td>60</b-td>
                  <b-td>76</b-td>
                  <b-td>7</b-td>
                  <b-td>100</b-td>
                </b-tr>
              </b-tbody>
            </b-table-simple>
       <p>轄管計畫列表：</p>
       <b-table
        class="table-sm"
        itemsUndefinedBehavior="loading"
        striped
        bordered
        style="background-color:white"
        :items="table.planListData"
        :fields="table.fields_backend_planList"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      >
        <!--              @changePagination="handlePaginationChanged($event)"-->
        <!--            >-->
        <template #cell(lightSignal)="row">
          <img src="../../../../content/images/custom/lic-light-red-46x46.png" width="25" height="25" v-if="row.item.light==='red'">
          <img src="../../../../content/images/custom/lic-light-yellow-46x46.png" width="25" height="25" v-if="row.item.light==='yellow'">
          <img src="../../../../content/images/custom/lic-light-green-46x46.png" width="25" height="25" v-if="row.item.light==='green'">
          <img src="../../../../content/images/custom/lic-light-gray-46x46.png" width="25" height="25" v-if="row.item.light==='gray'">
        </template>
      </b-table>
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
  name: 'lic0101Query',
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
    var light = 'red';
    // 查詢結果及狀態
    const queryStatus: Ref<Boolean> = ref(true);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
      fields_backend_planList: [
        {
           //項次
          key: 'index',
          label: i18n.t('label.index'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },

        {
          // 燈號
          key: 'lightSignal',
          label: '燈號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },

         {
           //證照取得(%)
          key: 'licPossessionRate',
          label: '證照取得(%)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle text-red',
        },

        {
          // 計畫名稱
          key: 'planName',
          label: i18n.t('label.planName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

         {
          // 計畫應辦總證照數(A)
          key: 'licToBePossessedSumNum',
          label: '計畫應辦總證照數(A)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right text-color-blue align-middle',
        },

        {
          // 目前應取得數(B)
          key: 'licToBePossessedNum',
          label: '目前應取得數(B)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },

        {
          // 目前已取得數(B)
          key: 'licPossessedNum',
          label: '目前已取得數(C)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },

        {
          // 差異數(C-B)
          key: 'diffNum',
          label: '差異數(C-B)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },

        {
          // 預定證照進度(B/A)
          key: 'scheduledLicProgress',
          label: '預定證照進度(B/A)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },

        {
          // 實際證照進度(C/A)
          key: 'currentLicProgress',
          label: '實際證照進度(C/A)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },

        {
          // 進度差異(%)
          key: 'progressDiff',
          label: '進度差異(%)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },

        {
          // 超前/逾期天數
          key: 'aheadOverdueDays',
          label: '超前/逾期天數',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
      ],
      planListData: [
            {
              index: '1',
              light: 'red',
              licPossessionRate: '23',
              planName: '台北都會區大眾捷運系統工程計畫—環狀線北環段及南環段暨周邊土地開發',
              licToBePossessedSumNum: '41',
              licToBePossessedNum: '26',
              licPossessedNum: '6',
              diffNum: '-20'
              scheduledLicProgress: '63%',
              currentLicProgress: '14%',
              progressDiff: '-49%',
              aheadOverdueDays: '348 D'
            },
            {
              index: '2',
              light: 'yellow',
              licPossessionRate: '80',
              planName: '臺北都會區大眾捷運系統三鶯線建設計畫',
              licToBePossessedSumNum: '21',
              licToBePossessedNum: '20',
              licPossessedNum: '20',
              diffNum: '0'
              scheduledLicProgress: '95%',
              currentLicProgress: '95%',
              progressDiff: '0%',
              aheadOverdueDays: '2634 D'
            },
            {
              index: '3',
              light: 'yellow',
              licPossessionRate: '70',
              planName: '桃園都會區大眾捷運系統航空城捷運線(綠線)暨土地整合發展計畫',
              licToBePossessedSumNum: '17',
              licToBePossessedNum: '8',
              licPossessedNum: '8',
              diffNum: '0'
              scheduledLicProgress: '47%',
              currentLicProgress: '47%',
              progressDiff: '0%',
              aheadOverdueDays: '537 D'
            },
            {
              index: '4',
              light: 'green',
              licPossessionRate: '100',
              planName: '第三座液化天然氣接收站計畫',
              licToBePossessedSumNum: '16',
              licToBePossessedNum: '13',
              licPossessedNum: '13',
              diffNum: '0'
              scheduledLicProgress: '81%',
              currentLicProgress: '81%',
              progressDiff: '0%',
              aheadOverdueDays: '830 D'
            },
            {
              index: '5',
              light: 'green',
              licPossessionRate: '100',
              planName: '台鐵南港至花蓮提速改善計畫',
              licToBePossessedSumNum: '16',
              licToBePossessedNum: '11',
              licPossessedNum: '11',
              diffNum: '0'
              scheduledLicProgress: '69%',
              currentLicProgress: '69%',
              progressDiff: '0%',
              aheadOverdueDays: '324 D'
            },
            
            {
              index: '6',
              light: 'green',
              licPossessionRate: '100',
              planName: '烏溪鳥嘴潭人工湖工程計畫',
              licToBePossessedSumNum: '35',
              licToBePossessedNum: '26',
              licPossessedNum: '26',
              diffNum: '0'
              scheduledLicProgress: '74%',
              currentLicProgress: '74%',
              progressDiff: '0%',
              aheadOverdueDays: '258 D'
            },
            {
              index: '7',
              light: 'red',
              licPossessionRate: '42',
              planName: '曾文南化聯通管工程計畫',
              licToBePossessedSumNum: '78',
              licToBePossessedNum: '60',
              licPossessedNum: '25',
              diffNum: '-35'
              scheduledLicProgress: '77%',
              currentLicProgress: '32%',
              progressDiff: '-45%',
              aheadOverdueDays: '1544 D'
            },
            {
              index: '8',
              light: 'red',
              licPossessionRate: '38',
              planName: '台9線花東縱谷公路安全景觀大道計畫(臺東段)',
              licToBePossessedSumNum: '85',
              licToBePossessedNum: '56',
              licPossessedNum: '21',
              diffNum: '-35'
              scheduledLicProgress: '66%',
              currentLicProgress: '25%',
              progressDiff: '-41%',
              aheadOverdueDays: '754 D'
            },
            {
              index: '9',
              light: 'yellow',
              licPossessionRate: '67',
              planName: '國道4號台中環線豐原潭子段計畫',
              licToBePossessedSumNum: '24',
              licToBePossessedNum: '21',
              licPossessedNum: '14',
              diffNum: '-7'
              scheduledLicProgress: '88%',
              currentLicProgress: '58%',
              progressDiff: '-30%',
              aheadOverdueDays: '336 D'
            },
            {
              index: '10',
              light: 'red',
              licPossessionRate: '36',
              planName: '郵政物流園區建置計畫',
              licToBePossessedSumNum: '54',
              licToBePossessedNum: '42',
              licPossessedNum: '15',
              diffNum: '-27'
              scheduledLicProgress: '78%',
              currentLicProgress: '28%',
              progressDiff: '-50%',
              aheadOverdueDays: '976 D'
            },
            {
              index: '11',
              light: 'red',
              licPossessionRate: '42',
              planName: '臺南市先進運輸系統第一期藍線規劃作業',
              licToBePossessedSumNum: '64',
              licToBePossessedNum: '52',
              licPossessedNum: '22',
              diffNum: '-30'
              scheduledLicProgress: '81%',
              currentLicProgress: '34%',
              progressDiff: '-47%',
              aheadOverdueDays: '254 D'
            },
            {
              index: '12',
              light: 'yellow',
              licPossessionRate: '75',
              planName: '臺北都會區大眾捷運系統工程計畫－萬大-中和-樹林線規劃報告書暨周邊土地發展計畫-第二期計畫',
              licToBePossessedSumNum: '30',
              licToBePossessedNum: '28',
              licPossessedNum: '21',
              diffNum: '-7'
              scheduledLicProgress: '93%',
              currentLicProgress: '70%',
              progressDiff: '-23%',
              aheadOverdueDays: '421 D'
            },
            {
              index: '13',
              light: 'red',
              licPossessionRate: '14',
              planName: '大故宮計畫',
              licToBePossessedSumNum: '53',
              licToBePossessedNum: '37',
              licPossessedNum: '5',
              diffNum: '-32'
              scheduledLicProgress: '70%',
              currentLicProgress: '9%',
              progressDiff: '-61%',
              aheadOverdueDays: '853 D'
            },
            {
              index: '14',
              light: 'green',
              licPossessionRate: '95',
              planName: '國道7號高雄路段計畫',
              licToBePossessedSumNum: '145',
              licToBePossessedNum: '113',
              licPossessedNum: '107',
              diffNum: '-6'
              scheduledLicProgress: '78%',
              currentLicProgress: '73%',
              progressDiff: '-5%',
              aheadOverdueDays: '1746 D'
            },
            {
              index: '15',
              light: 'red',
              licPossessionRate: '0',
              planName: '國道1號甲線計畫',
              licToBePossessedSumNum: '7',
              licToBePossessedNum: '1',
              licPossessedNum: '0',
              diffNum: '-1'
              scheduledLicProgress: '14%',
              currentLicProgress: '0%',
              progressDiff: '-14%',
              aheadOverdueDays: '0 D'
            },
            {
              index: '16',
              light: 'yellow',
              licPossessionRate: '58',
              planName: '臺灣桃園國際機場第三航站區建設計畫',
              licToBePossessedSumNum: '41',
              licToBePossessedNum: '33',
              licPossessedNum: '19',
              diffNum: '-14'
              scheduledLicProgress: '80%',
              currentLicProgress: '19%',
              progressDiff: '-61%',
              aheadOverdueDays: '96 D'
            },
            {
              index: '17',
              light: 'green',
              licPossessionRate: '0',
              planName: '國道1號楊梅至頭份段拓寬計畫',
              licToBePossessedSumNum: '7',
              licToBePossessedNum: '6',
              licPossessedNum: '6',
              diffNum: '0'
              scheduledLicProgress: '86%',
              currentLicProgress: '86%',
              progressDiff: '0%',
              aheadOverdueDays: '0 D'
            },
            {
              index: '18',
              light: 'green',
              licPossessionRate: '87',
              planName: '(A10601)高雄港洲際貨櫃二期大林石化油品儲運中心投資計畫',
              licToBePossessedSumNum: '154',
              licToBePossessedNum: '122',
              licPossessedNum: '106',
              diffNum: '-16'
              scheduledLicProgress: '79%',
              currentLicProgress: '69%',
              progressDiff: '-10%',
              aheadOverdueDays: '564 D'
            },
            {
              index: '合計',
              light: 'yellow',
              licPossessionRate: '57',
              planName: '',
              licToBePossessedSumNum:'888',
              licToBePossessedNum: '675',
              licPossessedNum: '445',
              diffNum: '-230'
              scheduledLicProgress: '73%',
              currentLicProgress: '49%',
              progressDiff: '-24%',
              aheadOverdueDays: '12475 D'
            },

       ],
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
      light,
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

<style>
</style>

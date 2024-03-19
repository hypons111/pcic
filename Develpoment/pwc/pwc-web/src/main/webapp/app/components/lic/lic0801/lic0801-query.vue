<template>
  <div>
    <section class="container mt-2" v-if="roleRef === RoleEnum.ADMIN">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="bell"></font-awesome-icon>
                紅燈計畫及逾期證照
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
              <b-col offset='0'>
                <div style="color:black">紅燈計畫:</div>
              </b-col>
            </b-form-row>

            <b-form-row>
              <b-col offset="0.5">
                <button @click="toPlanView" style="color:red;background-color:transparent;border:none"><u>1.臺北都會區大眾捷運系統工程計畫-環狀線北環段及南環段暨周邊土地開發</u></button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2" v-if="true">
       <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon=""></font-awesome-icon>
                逾期未完成證照:
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step2 = !stepVisible.step2">
                <font-awesome-icon v-if="stepVisible.step2" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step2" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step2">
            <b-table
                  class="table-sm"
                  itemsUndefinedBehavior="loading"
                  striped bordered
                  :items="table.overdueUnfinished"
                  :fields="table.fields_unFinLicBackend"
                  :is-server-side-paging="false"
                  :totalItems="table.totalItems"
                  >
                </b-table>
          </b-collapse>
        </div>
      </div>
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon=""></font-awesome-icon>
                已逾預定送審時程仍未送審證照:
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step3 = !stepVisible.step3">
                <font-awesome-icon v-if="stepVisible.step3" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step3" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step3">
            <b-table
                  class="table-sm"
                  itemsUndefinedBehavior="loading"
                  striped bordered
                  :items="table.data"
                  :fields="table.fields_unFinLicBackend"
                  :is-server-side-paging="false"
                  :totalItems="table.totalItems"
                  >
                </b-table>
          </b-collapse>
        </div>
      </div>
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon=""></font-awesome-icon>
                逾期送審證照:
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step4 = !stepVisible.step4">
                <font-awesome-icon v-if="stepVisible.step4" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step4" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step4">
            <b-table
                  class="table-sm"
                  itemsUndefinedBehavior="loading"
                  striped bordered
                  :items="table.overdueAppLic"
                  :fields="table.fields_unFinLicBackend"
                  :is-server-side-paging="false"
                  :totalItems="table.totalItems"
                  >
                </b-table>
          </b-collapse>
        </div>
      </div>
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon=""></font-awesome-icon>
                前期未填報案件
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step5 = !stepVisible.step5">
                <font-awesome-icon v-if="stepVisible.step5" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step5" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step5">
            <b-table
                  class="table-sm"
                  itemsUndefinedBehavior="loading"
                  striped bordered
                  :items="table.earlyStageUnfilled"
                  :fields="table.fields_earlyStageUnfilledBackend"
                  :is-server-side-paging="false"
                  :totalItems="table.totalItems"
                  >
                </b-table>
          </b-collapse>
        </div>
      </div>
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon=""></font-awesome-icon>
                每月證照績效統計
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step6 = !stepVisible.step6">
                <font-awesome-icon v-if="stepVisible.step6" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step6" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step6">
            <b-table-simple striped bordered>
            <b-thead style="text-align:center">
                <b-tr>
                <b-td>A:應辦總證照數</b-td>
                <b-td>B:C+D已取得證照數</b-td>
                <b-td>C:如期取得證照數</b-td>
                <b-td>D:逾期但以取得證照數</b-td>
                <b-td>E:當時應如期取得證照數</b-td>
                <b-td>F:C/E證照如期取得程度</b-td>
                <b-td>G:B/A證照已取得程度</b-td>
                </b-tr>
            </b-thead>
            <b-tbody style="text-align:right">
                <b-tr>
                  <b-td>8</b-td>
                  <b-td>3</b-td>
                  <b-td>3</b-td>
                  <b-td>0</b-td>
                  <b-td>2</b-td>
                  <b-td>150.00%</b-td>
                  <b-td>37.5%</b-td>
                </b-tr>
              </b-tbody>
            </b-table-simple>
          </b-collapse>
        </div>
      </div>
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon=""></font-awesome-icon>
                未來三個月到期之應辦證照
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step7 = !stepVisible.step7">
                <font-awesome-icon v-if="stepVisible.step7" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step7" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step7">
            <b-table
                  class="table-sm"
                  itemsUndefinedBehavior="loading"
                  striped bordered
                  :items="table.nextThreeMonth"
                  :fields="table.fields_nextThreeMonthBackend"
                  :is-server-side-paging="false"
                  :totalItems="table.totalItems"
                  >
                </b-table>
          </b-collapse>
        </div>
      </div>
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon=""></font-awesome-icon>
               未來一個月應送審之證照
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step8 = !stepVisible.step8">
                <font-awesome-icon v-if="stepVisible.step8" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step8" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step8">
            <b-table
                  class="table-sm"
                  itemsUndefinedBehavior="loading"
                  striped bordered
                  :items="table.nextMonth"
                  :fields="table.fields_unFinLicBackend"
                  :is-server-side-paging="false"
                  :totalItems="table.totalItems"
                  >
                </b-table>
          </b-collapse>
        </div>
      </div>
      <div>
        <br>
        <b-button class="float-right" variant="info">確認所有提醒</b-button>
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
  name: 'lic0801Query',
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

    const toPlanView = () => {
      navigateByNameAndParams('lic0201Query',null);
    }
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

    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      step2: true,
      step3: true,
      step4: true,
      step5: true,
      step6: true,
      step7: true,
      step8: true
    });

    
    // 查詢結果及狀態
    const queryStatus = ref(true);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
      fields_unFinLicBackend: [
        {
           //證照名稱
          key: 'licName',
          label: '證照名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
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
           //主辦單位
          key: 'adminCompName',
          label: i18n.t('label.adminCompName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          // 審證機關
          key: 'organ_review',
          label: i18n.t('label.organ_review'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 預計送審日期
          key: 'date_reserve',
          label: i18n.t('label.date_reserve'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        }
      ],
      fields_earlyStageUnfilledBackend: [
        {
           //證照名稱
          key: 'licName',
          label: '證照名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
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
           //主辦單位
          key: 'adminCompName',
          label: i18n.t('label.adminCompName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          // 審證機關
          key: 'organ_review',
          label: i18n.t('label.organ_review'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      fields_nextThreeMonthBackend: [
        {
           //證照名稱
          key: 'licName',
          label: '證照名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
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
           //主辦單位
          key: 'adminCompName',
          label: i18n.t('label.adminCompName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          // 審證機關
          key: 'organ_review',
          label: i18n.t('label.organ_review'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 到期日
          key: 'due',
          label: '到期日',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        }
      ],
      overdueUnfinished: [
            {
              licName: "南臺南車站特種建築物許可",
              planName: "臺南市區鐵路地下化計畫",
              adminCompName: "交通部鐵道局中部工程處",
              organ_review: "內政部營建署",
              date_reserve: "2018-01-31"
            },
            {
              licName: "臺南車站都市設計審議報告",
              planName: "臺南市區鐵路地下化計畫",
              adminCompName: "交通部鐵道局中部工程處",
              organ_review: "臺南市政府都市發展局",
              date_reserve: "2018-04-30"
            },
            {
              licName: "萬大-中和-樹林地區捷運系統第3次環境影響差異分析報告",
              planName: "臺北都會區大眾捷運系統工程計畫-萬大-中和-樹林線規劃報告書暨周邊土地發展計畫-第二期計畫",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "行政員環境保護署",
              date_reserve: "2021-07-14"
            },
            {
              licName: "臺北都會區大眾捷運系統環狀線北環段-北機廠(含主變電站)都市審議報告書",
              planName: "臺北都會區大眾捷運系統工程計畫-萬大-中和-樹林線規劃報告書暨周邊土地發展計畫-第二期計畫",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府城鄉發展局",
              date_reserve: "2021-08-01"
            },
          ],
          data: [
            {
              licName: "南臺南車站特種建築物許可",
              planName: "臺南市區鐵路地下化計畫",
              adminCompName: "交通部鐵道局中部工程處",
              organ_review: "內政部營建署",
              date_reserve: "2017-04-30"
            },
            {
              licName: "「變更土城都市計畫」案、「變更土城(頂埔地區)」案、「變更樹林都市計畫」案、「變更樹林(三多里地區)都市計畫」案、「變更龍壽、迴龍都市計畫」案",
              planName: "臺北都會區大眾捷運系統工程計畫-萬大-中和-樹林線規劃報告書暨周邊土地發展計畫-第二期計畫",
              adminCompName: "台北市政府捷運工程局",
              organ_review: "內政部",
              date_reserve: "2017-06-13"
            },
            {
              licName: "3K+7100-11K+585徵收土地計畫",
              planName: "東西向快速公路台76線(原漢寶草屯線)台19線以西路段改線工程",
              adminCompName: "交通部公路總局西部濱海公路北區臨時工程處",
              organ_review: "內政部",
              date_reserve: "2021-07-29"
            },
            {
              licName: "跨(穿)越鐵路施工申請",
              planName: "桃園都會區大眾捷運系統航空城捷運線(綠線)暨土地整合發展計畫",
              adminCompName: "桃園市政府捷運工程局",
              organ_review: "交通部臺灣鐵路管理局",
              date_reserve: "2021-07-30"
            },
          ],
          overdueAppLic: [
            {
              licName: "3.淡水河安全影響評估報告(DF117)",
              planName: "臺北都會區大眾捷運系統工程計畫-環狀線北環段及南環段暨周邊土地開發",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              date_reserve: "2020-05-25"
            },
            {
              licName: "3.景美溪安全影響評估報告(DF115)",
              planName: "臺北都會區大眾捷運系統工程計畫-環狀線北環段及南環段暨周邊土地開發",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              date_reserve: "2020-06-12"
            },
            {
              licName: "排水計畫書",
              planName: "臺北都會區大眾捷運系統工程計畫-萬大-中和-樹林線暨周邊土地發展計畫-第二期計畫",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              date_reserve: "2020-06-19"
            },
            {
              licName: "2.二重疏洪道安全影響評估報告(DF116)",
              planName: "臺北都會區大眾捷運系統工程計畫-環狀線北環段及南環段暨周邊土地開發",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              date_reserve: "2020-08-27"
            },
            {
              licName: "1.河防安全影響評估報告(DQ125)",
              planName: "臺北都會區大眾捷運系統工程計畫-環狀線北環段及南環段暨周邊土地開發",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              date_reserve: "2020-11-13"
            },
          ],
          earlyStageUnfilled: [
            {
              licName: "候選綠建築證書",
              planName: "烏溪鳥嘴潭人工湖工程計畫",
              adminCompName: "經濟部水利署",
              organ_review: "內政部營建署",
            },
            {
              licName: "跨(穿)越公路及道路挖掘申請",
              planName: "烏溪鳥嘴潭人工湖工程計畫",
              adminCompName: "經濟部水利署",
              organ_review: "交通部公路總局",
            },
            {
              licName: "公用管線布設申請",
              planName: "烏溪鳥嘴潭人工湖工程計畫",
              adminCompName: "經濟部水利署",
              organ_review: "南投縣政府",
            },
            {
              licName: "土方處理(取消)",
              planName: "烏溪鳥嘴潭人工湖工程計畫",
              adminCompName: "經濟部水利署",
              organ_review: "交通部臺灣區國道高速公路局",
            },
            {
              licName: "3K+7100-11K+585徵收土地計畫",
              planName: "東西向快速公路台76線(原漢寶草屯線)台19線以西路段改線工程",
              adminCompName: "交通部公路總局西部濱海公路北區臨時工程處",
              organ_review: "內政部",
            },
            {
              licName: "11K+585-16K+607徵收土地計畫書",
              planName: "東西向快速公路台76線(原漢寶草屯線)台19線以西路段改線工程",
              adminCompName: "交通部公路總局西部濱海公路北區臨時工程處",
              organ_review: "內政部",
            },
          ],
          nextThreeMonth: [
            {
              licName: "1.河防安全影響評估報告",
              planName: "臺北都會區大眾捷運系統工程計畫-萬大-中和-樹林線規劃報告書濟州土地發展計畫-第二期計畫",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              due: "2021-09-30"
            },
            {
              licName: "排水計畫書",
              planName: "臺北都會區大眾捷運系統工程計畫-萬大-中和-樹林線規劃報告書濟州土地發展計畫-第二期計畫",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              due: "2021-09-30"
            },
          ],
          nextMonth: [
            {
              licName: "水上基地內廠房、辦公室建物",
              planName: "嘉義市區鐵路高架化計畫",
              adminCompName: "交通部鐵道局",
              organ_review: "嘉義縣政府",
              date_reserve: "2021-09-30"
            },
            {
              licName: "嘉義車站、嘉北車站、北回歸線站",
              planName: "嘉義市區鐵路高架化計畫",
              adminCompName: "交通部鐵道局",
              organ_review: "嘉義市政府",
              date_reserve: "2021-09-30"
            },
              {
              licName: "丙類危險性工作場所許可證",
              planName: "第三座液化天然氣接收站投資計畫",
              adminCompName: "台灣中油股份有限公司",
              organ_review: "勞動部職業安全衛生署",
              date_reserve: "2021-10-15"
            },
          ]
    });
    queryStatus.value = true;
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          table.overdueAppLic = undefined;
          table.overdueUnfinished = undefined;
          table.earlyStageUnfilled = undefined;
          table.nextThreeMonth = undefined;
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
          table.overdueUnfinished = [
            {
              licName: "南臺南車站特種建築物許可",
              planName: "臺南市區鐵路地下化計畫",
              adminCompName: "交通部鐵道局中部工程處",
              organ_review: "內政部營建署",
              date_reserve: "2018-01-31"
            },
            {
              licName: "臺南車站都市設計審議報告",
              planName: "臺南市區鐵路地下化計畫",
              adminCompName: "交通部鐵道局中部工程處",
              organ_review: "臺南市政府都市發展局",
              date_reserve: "2018-04-30"
            },
            {
              licName: "萬大-中和-樹林地區捷運系統第3次環境影響差異分析報告",
              planName: "臺北都會區大眾捷運系統工程計畫-萬大-中和-樹林線規劃報告書暨周邊土地發展計畫-第二期計畫",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "行政員環境保護署",
              date_reserve: "2021-07-14"
            },
            {
              licName: "臺北都會區大眾捷運系統環狀線北環段-北機廠(含主變電站)都市審議報告書",
              planName: "臺北都會區大眾捷運系統工程計畫-萬大-中和-樹林線規劃報告書暨周邊土地發展計畫-第二期計畫",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府城鄉發展局",
              date_reserve: "2021-08-01"
            },
          ];
          table.data = [
            {
              licName: "南臺南車站特種建築物許可",
              planName: "臺南市區鐵路地下化計畫",
              adminCompName: "交通部鐵道局中部工程處",
              organ_review: "內政部營建署",
              date_reserve: "2017-04-30"
            },
            {
              licName: "「變更土城都市計畫」案、「變更土城(頂埔地區)」案、「變更樹林都市計畫」案、「變更樹林(三多里地區)都市計畫」案、「變更龍壽、迴龍都市計畫」案",
              planName: "臺北都會區大眾捷運系統工程計畫-萬大-中和-樹林線規劃報告書暨周邊土地發展計畫-第二期計畫",
              adminCompName: "台北市政府捷運工程局",
              organ_review: "內政部",
              date_reserve: "2017-06-13"
            },
            {
              licName: "3K+7100-11K+585徵收土地計畫",
              planName: "東西向快速公路台76線(原漢寶草屯線)台19線以西路段改線工程",
              adminCompName: "交通部公路總局西部濱海公路北區臨時工程處",
              organ_review: "內政部",
              date_reserve: "2021-07-29"
            },
            {
              licName: "跨(穿)越鐵路施工申請",
              planName: "桃園都會區大眾捷運系統航空城捷運線(綠線)暨土地整合發展計畫",
              adminCompName: "桃園市政府捷運工程局",
              organ_review: "交通部臺灣鐵路管理局",
              date_reserve: "2021-07-30"
            },
          ];
          table.overdueAppLic = [
            {
              licName: "3.淡水河安全影響評估報告(DF117)",
              planName: "臺北都會區大眾捷運系統工程計畫-環狀線北環段及南環段暨周邊土地開發",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              date_reserve: "2020-05-25"
            },
            {
              licName: "3.景美溪安全影響評估報告(DF115)",
              planName: "臺北都會區大眾捷運系統工程計畫-環狀線北環段及南環段暨周邊土地開發",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              date_reserve: "2020-06-12"
            },
            {
              licName: "排水計畫書",
              planName: "臺北都會區大眾捷運系統工程計畫-萬大-中和-樹林線暨周邊土地發展計畫-第二期計畫",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              date_reserve: "2020-06-19"
            },
            {
              licName: "2.二重疏洪道安全影響評估報告(DF116)",
              planName: "臺北都會區大眾捷運系統工程計畫-環狀線北環段及南環段暨周邊土地開發",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              date_reserve: "2020-08-27"
            },
            {
              licName: "1.河防安全影響評估報告(DQ125)",
              planName: "臺北都會區大眾捷運系統工程計畫-環狀線北環段及南環段暨周邊土地開發",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              date_reserve: "2020-11-13"
            },
          ];
          table.earlyStageUnfilled = [
            {
              licName: "候選綠建築證書",
              planName: "烏溪鳥嘴潭人工湖工程計畫",
              adminCompName: "經濟部水利署",
              organ_review: "內政部營建署",
            },
            {
              licName: "跨(穿)越公路及道路挖掘申請",
              planName: "烏溪鳥嘴潭人工湖工程計畫",
              adminCompName: "經濟部水利署",
              organ_review: "交通部公路總局",
            },
            {
              licName: "公用管線布設申請",
              planName: "烏溪鳥嘴潭人工湖工程計畫",
              adminCompName: "經濟部水利署",
              organ_review: "南投縣政府",
            },
            {
              licName: "土方處理(取消)",
              planName: "烏溪鳥嘴潭人工湖工程計畫",
              adminCompName: "經濟部水利署",
              organ_review: "交通部臺灣區國道高速公路局",
            },
            {
              licName: "3K+7100-11K+585徵收土地計畫",
              planName: "東西向快速公路台76線(原漢寶草屯線)台19線以西路段改線工程",
              adminCompName: "交通部公路總局西部濱海公路北區臨時工程處",
              organ_review: "內政部",
            },
            {
              licName: "11K+585-16K+607徵收土地計畫書",
              planName: "東西向快速公路台76線(原漢寶草屯線)台19線以西路段改線工程",
              adminCompName: "交通部公路總局西部濱海公路北區臨時工程處",
              organ_review: "內政部",
            },
          ];
          table.nextThreeMonth = [
            {
              licName: "1.河防安全影響評估報告",
              planName: "臺北都會區大眾捷運系統工程計畫-萬大-中和-樹林線規劃報告書濟州土地發展計畫-第二期計畫",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              due: "2021-09-30"
            },
            {
              licName: "排水計畫書",
              planName: "臺北都會區大眾捷運系統工程計畫-萬大-中和-樹林線規劃報告書濟州土地發展計畫-第二期計畫",
              adminCompName: "臺北市政府捷運工程局",
              organ_review: "新北市政府水利局",
              due: "2021-09-30"
            },
          ];
          table.nextMonth = [
            {
              licName: "水上基地內廠房、辦公室建物",
              planName: "嘉義市區鐵路高架化計畫",
              adminCompName: "交通部鐵道局",
              organ_review: "嘉義縣政府",
              date_reserve: "2021-09-30"
            },
            {
              licName: "嘉義車站、嘉北車站、北回歸線站",
              planName: "嘉義市區鐵路高架化計畫",
              adminCompName: "交通部鐵道局",
              organ_review: "嘉義市政府",
              date_reserve: "2021-09-30"
            },
              {
              licName: "丙類危險性工作場所許可證",
              planName: "第三座液化天然氣接收站投資計畫",
              adminCompName: "台灣中油股份有限公司",
              organ_review: "勞動部職業安全衛生署",
              date_reserve: "2021-10-15"
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
      toPlanView,
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

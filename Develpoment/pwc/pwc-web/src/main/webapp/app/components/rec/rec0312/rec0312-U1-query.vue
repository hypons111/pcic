<template>
  <div>
    <!-- 查詢條件 -->
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
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-10">
          <b-collapse v-model="stepVisible">
            <b-form-row>
              <i-form-group-check :label="$t('label.projectCodes')" :item="$v.projectCodes" :labelStar="true">
                <b-form-select
                  v-model="$v.projectCode.$model"
                  :options="queryOptions.projectCode"
                  :state="validateState($v.projectCode)"
                ></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.orgName')" :item="$v.orgName" :labelStar="true">
                <b-form-select
                  v-model.trim="$v.orgName.$model"
                  :options="queryOptions.orgName"
                  :state="validateState($v.orgName)"
                ></b-form-select>
              </i-form-group-check>
            </b-form-row>

            <div class="button-float-right">
              <b-button-toolbar>
                <b-button class="mt-1 ml-1" variant="info" @click="handleQuery">分批審議</b-button>
                <b-button class="mt-1 ml-1" variant="info" @click="handleQuery">確認審議</b-button>
              </b-button-toolbar>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>

    <!-- i-table -->
    <section v-if="queryStatus">
      <div class="card-body col-12" v-if="dataStatus">
        <b-form-row class="font-color-red"> 紅色審查建議經費為依照核列比率 88.76%計算之金額 </b-form-row>
        <b-form-row class="font-color-red"> 一千萬以下未滿足最低抽查比率：差 2 件 </b-form-row>
      </div>
      <div class="container mt-3">
        <b-table class="table-sm" :itemsUndefinedBehavior="'loading'" :items="table.data" :fields="table.fields">
          <template #head(isReview)="row">
            {{ $t('label.review') }}
            <b-form-checkbox :true-value="true" :false-value="false" v-model="checked" @change="changeAllChecked"></b-form-checkbox>
          </template>
          <template #cell(isReview)="row">
            <b-form-checkbox v-model="row.item.isReview" :true-value="true" :false-value="false"></b-form-checkbox>
          </template>
          <template #cell(approveNo)="row">
            <b-form-input maxlength="3" type="text" v-if="inputStatus && row.item.isReview"></b-form-input>
            <div v-if="showSerialStatus">{{ row.item.approveNo }}</div>
          </template>
          <template #cell(reviewAmount)="row">
            <div :style="{ color: row.item.reviewAmount > 1800 && row.item.reviewAmount < 2550 ? 'red' : '' }">
              {{ row.item.reviewAmount | commaFormatter }}
            </div>
          </template>
        </b-table>
      </div>
      <div class="button-float-right mt-3">
        <b-button-toolbar>
          <b-button class="mt-1 ml-1" variant="info" @click="genSerialbyView">依照目前排序產生序號</b-button>
          <b-button class="mt-1 ml-1" variant="info" @click="genSerialbySelf">自訂排序</b-button>
          <i-button class="mt-1 ml-1" type="check-circle" @click="genSerialbyView"></i-button>
          <b-button class="mt-1 ml-1" variant="info" @click="genSerialbyView">序號及金額寫入資料庫不送出</b-button>
          <b-button class="mt-1 ml-1" variant="info" @click="send">送出</b-button>
        </b-button-toolbar>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, ref, watch, onMounted } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import { RecProject } from '@/shared/model/recModel/rec-project.model';
import axios from 'axios';
import { required } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import iButton from '@/shared/buttons/i-button.vue';
import { useGetters } from '@u3u/vue-hooks';
import { commaFormatter } from '@/shared/formatter/common';

export default {
  components: { iButton },
  name: 'rec0312U1',
  props: {
    isReload: {
      required: false,
    },
  },
  setup() {
    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    onMounted(() => {
      getOrgRole();
      getRoleId();
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      orgName: '',
      projectCode: ''
    };

    const rules = {
      orgName: {},
      projectCode: {}
    };

    // 下拉選單選項
    const queryOptions = reactive({
      projectCode: [
        { value: '', text: '請選擇' },
        { value: 'A1', text: 'A1-水利工程' },
        { value: 'B1', text: 'B1-觀光工程' },
        { value: 'C1', text: 'C1-道路橋梁工程-公路系統工程' },
        { value: 'C2', text: 'C2-道路橋梁工程-村里聯絡道路工程' },
      ]
      orgName: [
        { value: '', text: '請選擇' },
        { value: '1', text: 'orgName' },
      ]
    });

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const allOrgRole = ref([]);

    const getOrgRole = () => {
      axios
        .get('/rec-user-project-codes/get-rec-org-role')
        .then(({ data }) => {
          allOrgRole.value.splice(0, allOrgRole.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const table = reactive({
      fields: [
        {
          key: 'isReview',
          label: $i18n.t('label.review'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'approveNo',
          label: $i18n.t('label.approveNo'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'applySubmitNo',
          label: $i18n.t('label.applySubmitNo'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectCode',
          label: $i18n.t('label.projectCodes'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectName',
          label: $i18n.t('label.recEngName'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'city',
          label: $i18n.t('label.city'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'village',
          label: $i18n.t('label.village'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'applyAmount',
          label: $i18n.t('label.recAmount'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-right align-middle',
          formatter: value => commaFormatter(value),
        },
        {
          key: 'reviewAmount',
          label: $i18n.t('label.reSuggestAmount'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-right align-middle font-color-red',
          formatter: value => commaFormatter(value)
        }
      ],
      data: undefined
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    // 資料顯示狀態
    const dataStatus = ref(false);
    //顯示查詢條件區
    const stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //登入角色
    const optionVisible = ref({
      option1: true,
      option2: false,
    });

    const roleId1 = ['REC_30', 'REC_31', 'REC_20'];
    const roleId2 = ['REC_10', 'REC_00'];

    // 取得登入帳號(PWB_ROLE_APPROVE.USER_ID)
    const loginUserId = ref(useGetters(['account']).account.value.id);

    // 查詢登入帳號所擁有的角色(ADM_ACCOUNT_ROLE.ROLE_ID)
    const getRoleId = () => {
      form.id = loginUserId.value;

      // axios.post('/adm-account-role/getRoleId', form).then(({ data }) => {
      //   for (let roleId of data) {
      //     console.log("roleId:"+roleId)
      //      if (roleId1.indexOf(roleId) == -1) {
      //         optionVisible.option1 == false;
      //         optionVisible.option2 == true;
      //      } if (roleId2.indexOf(roleId) == -1) {
      //         optionVisible.option1 == true;
      //         optionVisible.option2 == false;
      //      } else {
      //         optionVisible.option1 == true;
      //         optionVisible.option2 == false;
      //      }
      //   }
      // });
    };



    //依條件查詢復建工程
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          dataStatus.value = true;
          table.data = [];
          table.data = mockdata;


          // axios //TODO:fix post api
          //   .post('/rec-projects/criteria-jpa', form)
          //   .then(({ data }) => {
          //     table.data = data.content.slice(0, data.content.length);
          //     table.totalItems = data.totalElements;
          //   })
          //   .finally()
          //   .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    watch(allOrgRole, () => {
      queryOptions.orgName = allOrgRole.value.map(data => {
        //map回傳新的格式
        return { value: data['orgId'], text: data['orgName'] };
      });
      queryOptions.orgName.unshift({ text: '請選擇', value: '' }); //添加一個或多個元素至陣列的開頭
    });

    const checked = ref(null);

    const changeAllChecked = () => {
      if (checked.value) {
        for (let i =0; i < table.data.length; i++) {
          table.data[i].isReview=true;
        }
      } else {
        for (let i =0; i < table.data.length; i++) {
          table.data[i].isReview=false;
        }
      }
    }

    // 輸入欄位顯示狀態
    const inputStatus = ref(false);
    // 核列序號顯示狀態
    const showSerialStatus = ref(false);

    //依照目前排序產生序號
    const genSerialbyView = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          dataStatus.value = true;
          table.data = [];
          inputStatus.value = false;
          showSerialStatus.value = true;
          table.data = mockdata;
          const serial = 1;
          for (let i =0; i < table.data.length; i++) {
            if (table.data[i].isReview == true) {
              table.data[i].approveNo = '00'+serial;
              serial++;
            }
          }
      });
    };

    //自訂排序
    const genSerialbySelf = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          dataStatus.value = true;
          table.data = [];
          table.data = mockdata;
          for (let i =0; i < table.data.length; i++) {
            if (table.data[i].isReview == true) {
              inputStatus.value = true;
            }else{
              inputStatus.value = false;
            }
          }
          showSerialStatus.value = false;
      });
    };

    //確認按鈕
    const checkSerial = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          dataStatus.value = true;
          table.data = [];
          table.data = mockdata;
          const serial = 1;
          for (let i =0; i < table.data.length; i++) {
            if (table.data[i].isReview == true) {
              table.data[i].approveNo = '00'+serial;
              serial++;
            }
          }
          inputStatus.value = false;
          showSerialStatus.value = true;
      });
    };

    // 序號及金額寫入資料庫不送出


    // 送出
    const send = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          dataStatus.value = false;
          table.data = [];
      });
    };

    const mockdata = [
      {
        isReview: true,
        approveNo: '',
        applySubmitNo: '001',
        projectCode: 'G1',
        projectName: '鹿谷鄉竹林村田頭巷旁野溪災修復建工程',
        city: '鹿谷鄉',
        village: '竹林村',
        applyAmount: '1464',
        reviewAmount: '1557'
      },
      {
        isReview: false,
        approveNo: '',
        applySubmitNo: '002',
        projectCode: 'G1',
        projectName: '鹿谷鄉秀峰村崛底橋下游護岸災修復建工程',
        city: '鹿谷鄉',
        village: '秀峰村',
        applyAmount: '9980',
        reviewAmount: '9980'
      },
      {
        isReview: false,
        approveNo: '',
        applySubmitNo: '003',
        projectCode: 'G1',
        projectName: '魚池鄉中明村7鄰排水溝災修復建工程',
        city: '魚池鄉',
        village: '中明村',
        applyAmount: '360',
        reviewAmount: '360'
      },
      {
        isReview: true,
        approveNo: '',
        applySubmitNo: '004',
        projectCode: 'G1',
        projectName: '達邦村米西給娜農路災害復建工程',
        city: '阿里山鄉',
        village: '達邦村',
        applyAmount: '2871',
        reviewAmount: '2548'
      },
      {
        isReview: true,
        approveNo: '',
        applySubmitNo: '005',
        projectCode: 'G1',
        projectName: '達邦村米亞庫巴農路災害復建工程',
        city: '阿里山鄉',
        village: '達邦村',
        applyAmount: '2102',
        reviewAmount: '1866'
      }
    ];

    return {
      $v,
      stepVisible,
      validateState,
      optionVisible,
      getRoleId,
      checkValidity,
      queryStatus,
      dataStatus,
      handleQuery,
      reset,
      queryOptions,
      table,
      checked,
      changeAllChecked,
      genSerialbyView,
      checkSerial,
      genSerialbySelf,
      inputStatus,
      showSerialStatus,
      send
    };
  },
  filters: {
    commaFormatter: (value: string) => commaFormatter(value),
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
.font-color-red {
  color: red !important;
}
</style>

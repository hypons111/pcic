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
              <i-form-group-check :label="$t('label.orgName')" :item="$v.orgName" :labelStar="true">
                <b-form-select
                  v-model.trim="$v.orgName.$model"
                  :options="queryOptions.orgName"
                  :state="validateState($v.orgName)"
                ></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.projectCodes')" :item="$v.projectCodes" :labelStar="true">
                <b-form-select
                  v-model="$v.projectCode.$model"
                  :options="queryOptions.projectCode"
                  :state="validateState($v.projectCode)"
                ></b-form-select>
              </i-form-group-check>
            </b-form-row>

            <div class="button-float-right">
              <b-button-toolbar>
                <i-button class="mt-1 ml-1" type="search" @click="handleQuery"></i-button>
              </b-button-toolbar>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>

    <!-- i-table -->
    <section v-if="queryStatus">
      <div class="card-body col-12" v-if="dataStatus">
        <b-form-row>共 5 件復建工程 </b-form-row>
      </div>
      <div class="container mt-3">
        <b-table class="table-sm" :itemsUndefinedBehavior="'loading'" :items="table.data" :fields="table.fields"> </b-table>
      </div>
      <div class="button-float-right mt-3">
        <b-button-toolbar>
          <b-button class="mt-1 ml-1" variant="info" @click="send">管理員送出</b-button>
          <b-button class="mt-1 ml-1" variant="info" @click="toSendBackView()">退回案件</b-button>
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
  name: 'rec0313U2',
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
      // getOrgRole();
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
        { value: '', text: '全部' },
        { value: 'A1', text: 'A1-水利工程' },
        { value: 'B1', text: 'B1-觀光工程' },
        { value: 'C1', text: 'C1-道路橋梁工程-公路系統工程' },
        { value: 'C2', text: 'C2-道路橋梁工程-村里聯絡道路工程' },
      ]
      orgName: [
        { value: '', text: '全部' },
        { value: '1', text: '南投縣政府' },
      ]
    });

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // const allOrgRole = ref([]);

    // const getOrgRole = () => {
    //   axios
    //     .get('/rec-user-project-codes/get-rec-org-role')
    //     .then(({ data }) => {
    //       allOrgRole.value.splice(0, allOrgRole.value.length, ...data);
    //     })
    //     .catch(notificationErrorHandler(notificationService));
    // };

    const table = reactive({
      fields: [
        {
          key: 'approveNo',
          label: $i18n.t('label.priority'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'orgName',
          label: $i18n.t('label.orgName'),
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

    // watch(allOrgRole, () => {
    //   queryOptions.orgName = allOrgRole.value.map(data => {
    //     //map回傳新的格式
    //     return { value: data['orgId'], text: data['orgName'] };
    //   });
    //   queryOptions.orgName.unshift({ text: '請選擇', value: '' }); //添加一個或多個元素至陣列的開頭
    // });


    // 管理員送出
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


    //轉到REC0313U2-back 退回案件畫面
    const toSendBackView = () => {
      navigateByNameAndParams('rec0313U2-back');
    };

    const mockdata = [
      {
        approveNo: '001',
        orgName: '南投縣政府',
        projectCode: 'G1',
        projectName: '鹿谷鄉竹林村田頭巷旁野溪災修復建工程',
        city: '鹿谷鄉',
        village: '竹林村',
        reviewAmount: '1557'
      },
      {
        approveNo: '002',
        orgName: '南投縣政府',
        projectCode: 'G1',
        projectName: '鹿谷鄉秀峰村崛底橋下游護岸災修復建工程',
        city: '鹿谷鄉',
        village: '秀峰村',
        reviewAmount: '9980'
      },
      {
        approveNo: '003',
        orgName: '南投縣政府',
        projectCode: 'G1',
        projectName: '魚池鄉中明村7鄰排水溝災修復建工程',
        city: '魚池鄉',
        village: '中明村',
        reviewAmount: '360'
      },
      {
        approveNo: '004',
        orgName: '南投縣政府',
        projectCode: 'G1',
        projectName: '達邦村米西給娜農路災害復建工程',
        city: '阿里山鄉',
        village: '達邦村',
        reviewAmount: '2871'
      },
      {
        approveNo: '005',
        orgName: '南投縣政府',
        projectCode: 'G1',
        projectName: '達邦村米亞庫巴農路災害復建工程',
        city: '阿里山鄉',
        village: '達邦村',
        reviewAmount: '1866'
      }
    ];

    return {
      $v,
      stepVisible,
      validateState,
      optionVisible,
      getRoleId,
      queryStatus,
      dataStatus,
      handleQuery,
      reset,
      queryOptions,
      table,
      send,
      toSendBackView
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

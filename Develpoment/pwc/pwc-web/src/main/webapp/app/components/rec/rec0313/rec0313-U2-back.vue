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
            <!-- 查詢1(註: 狀態=0之案件) -->
            <b-form-row class="mb-3"> 查詢 工程會 管理員審核中 案件 </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.orgName') + '1'" :item="$v.orgName" :labelStar="true">
                <b-form-select v-model.trim="$v.orgName.$model" :options="queryOptions.orgName1"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.projectCodes') + '1'" :item="$v.projectCodes" :labelStar="true">
                <b-form-select v-model="$v.projectCode.$model" :options="queryOptions.projectCode1"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <div class="button-float-right">
              <b-button-toolbar>
                <b-button class="mt-1 ml-1" variant="info" @click="handleQuery1">查詢1</b-button>
              </b-button-toolbar>
            </div>
            <br /><br /><br />
            <!-- 查詢2(註: 狀態介於1至5之案件) -->
            <b-form-row class="mb-3"> 查詢 非屬 工程會 管理員審核中 案件 </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.orgName') + '2'" :item="$v.orgName" :labelStar="true">
                <b-form-select v-model.trim="$v.orgName.$model" :options="queryOptions.orgName2"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.projectCodes') + '2'" :item="$v.projectCodes" :labelStar="true">
                <b-form-select v-model="$v.projectCode.$model" :options="queryOptions.projectCode2"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <div class="button-float-right">
              <b-button-toolbar>
                <b-button class="mt-1 ml-1" variant="info" @click="handleQuery2">查詢2</b-button>
              </b-button-toolbar>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>

    <!-- i-table -->
    <section v-if="queryStatus1">
      <div class="container mt-3">
        <b-table class="table-sm" :itemsUndefinedBehavior="'loading'" :items="table.data" :fields="table.fields"> </b-table>
      </div>
      <div class="button-float-right mt-3">
        <b-button-toolbar>
          <b-button class="mt-1 ml-1" variant="info" @click="sendBack1" :disabled="table.data.length < 1"
            >工程會管理員退回至工程會業管</b-button
          >
          <b-button class="mt-1 ml-1" variant="info" @click="sendBack1" :disabled="table.data.length < 1"
            >工程會管理員退回至中央業管</b-button
          >
        </b-button-toolbar>
      </div>
    </section>
    <section v-if="queryStatus2">
      <div class="container mt-3">
        <b-table class="table-sm" :itemsUndefinedBehavior="'loading'" :items="table.data" :fields="table.fields"> </b-table>
      </div>
      <div class="button-float-right mt-3">
        <b-button-toolbar>
          <b-button class="mt-1 ml-1" variant="info" @click="sendBack2" :disabled="table.data.length < 1"
            >工程會業管退回中央管理員</b-button
          >
          <b-button class="mt-1 ml-1" variant="info" @click="sendBack2" :disabled="table.data.length < 1">中央管理員退回中央業管</b-button>
          <b-button class="mt-1 ml-1" variant="info" @click="sendBack2" :disabled="table.data.length < 1">中央業管退回地方管理員</b-button>
          <b-button class="mt-1 ml-1" variant="info" @click="sendBack2" :disabled="table.data.length < 1">地方管理員退回地方業管</b-button>
          <b-button class="mt-1 ml-1" variant="info" @click="sendBack2" :disabled="table.data.length < 1">地方業管退回地方提報者</b-button>
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
  name: 'rec0313U2-back',
  props: {
    isReload: {
      required: false,
    },
  },
  setup() {
    onActivated(() => {
      if (isReloadProp.value) {
        // handleQuery1();
        // handleQuery2();
      }
    });

    onMounted(() => {
      // getOrgRole();
      // getRoleId();
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
      projectCode1: [
        { value: '', text: '全部' },
        { value: 'A1', text: 'A1-水利工程' }
      ]
      orgName1: [
        { value: '', text: '全部' },
        { value: '1', text: '南投縣政府' }
      ]
      projectCode2: [
        { value: '', text: '全部' },
        { value: 'A1', text: 'A1-水利工程' },
        { value: 'C2', text: 'C2-道路橋梁工程-村里聯絡道路工程' },
      ]
      orgName2: [
        { value: '', text: '全部' },
        { value: '1', text: '南投縣政府' },
      ]
    });

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();



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

    // 查詢結果及狀態1
    const queryStatus1 = ref(false);
    // 查詢結果及狀態2
    const queryStatus2 = ref(false);
    //顯示查詢條件區
    const stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();



    //依條件查詢提報審議狀態=0之案件
    const handleQuery1 = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus1.value = true;
          queryStatus2.value = false;
          table.data = [];
          table.data = mockdata1;


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

    //依條件查詢提報審議狀態介於1至5之案件
    const handleQuery2 = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus2.value = true;
          queryStatus1.value = false;
          table.data = [];
          table.data = mockdata2;


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



    // 案件退回
    const sendBack1 = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus2.value = false;
          queryStatus1.value = -true;
          table.data = [];
      });
    };

    // 案件退回
    const sendBack2 = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus2.value = true;
          queryStatus1.value = false;
          table.data = [];
      });
    };

    const mockdata1 = [
      {
        approveNo: '001',
        orgName: '南投縣政府',
        projectCode: 'A1',
        projectName: '鹿谷鄉竹林村田頭巷旁野溪災修復建工程',
        city: '鹿谷鄉',
        village: '竹林村',
        reviewAmount: '1557'
      },
      {
        approveNo: '002',
        orgName: '南投縣政府',
        projectCode: 'A1',
        projectName: '鹿谷鄉秀峰村崛底橋下游護岸災修復建工程',
        city: '鹿谷鄉',
        village: '秀峰村',
        reviewAmount: '9980'
      }
    ];

    const mockdata2 = [
      {
        approveNo: '003',
        orgName: '南投縣政府',
        projectCode: 'A1',
        projectName: '魚池鄉中明村7鄰排水溝災修復建工程',
        city: '魚池鄉',
        village: '中明村',
        reviewAmount: '360'
      },
      {
        approveNo: '004',
        orgName: '南投縣政府',
        projectCode: 'A1',
        projectName: '達邦村米西給娜農路災害復建工程',
        city: '阿里山鄉',
        village: '達邦村',
        reviewAmount: '2871'
      },
      {
        approveNo: '005',
        orgName: '南投縣政府',
        projectCode: 'C2',
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
      queryStatus1,
      queryStatus2,
      handleQuery1,
      handleQuery2,
      reset,
      queryOptions,
      table,
      sendBack1,
      sendBack2
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

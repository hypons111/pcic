<template>
  <div>
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
              <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row v-if="roleRef === RoleEnum.ADMIN">
              <b-form-group class="col-12" label-cols="2" content-cols="10" label="第一試及格人員姓名" label-align-md="right">
                <b-form-input v-model.trim="$v.compName.$model"></b-form-input>
              </b-form-group>
            </b-form-row>

            <!--            <b-form-row>-->
            <b-form-group class="" label-cols-md="2" content-cols-md="10" label="審核狀態" label-align-md="right">
              <b-form-select v-model="$v.isApprove.$model" :options="queryOptions.isApprove"></b-form-select>
            </b-form-group>
            <!--            </b-form-row>-->

            <b-form-row class="mt-2" align="right">
              <b-col>
                <!--切換角色按鈕-->
                <b-button class="mr-2" size="md" variant="warning" @click="switchRoles">
                  <font-awesome-icon :icon="['fas', 'people-arrows']" />
                  {{ roleRef }}
                </b-button>
                <!--切換角色按鈕-->

                <b-button class="mr-2" size="md" variant="outline-secondary" v-promise-btn="{ promise: dataPromise }" @click="handleQuery">
                  <font-awesome-icon :icon="['fas', 'search']" />
                  {{ $t('button.query') }}
                </b-button>
                <b-button size="md" variant="outline-secondary" @click="reset">
                  <font-awesome-icon :icon="['fas', 'broom']" />
                  {{ $t('button.clear') }}
                </b-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-3" v-if="roleRef === RoleEnum.USER">
      <b-button class="ml-3" size="md" variant="primary" @click="toEditView(null, ref(formStatusEnum.CREATE))">
        <font-awesome-icon :icon="['fas', 'plus']" />
        {{ $t('button.insert') }}
      </b-button>
    </section>

    <section class="container mt-3" v-if="queryStatus">
      <i-table
        class="table-sm"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="roleRef === RoleEnum.USER ? table.fields_frontend : table.fields_backend"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      >
        <!--              @changePagination="handlePaginationChanged($event)"-->
        <!--            >-->
        <template #cell(action2)="row">
          <b-button
            v-if="roleRef === RoleEnum.USER"
            size="sm"
            variant="outline-secondary"
            @click="toEditView(row.item, ref(formStatusEnum.INFO))"
          >
            <font-awesome-icon :icon="['fas', 'binoculars']" />
            查看
          </b-button>
          <b-button
            v-if="roleRef === RoleEnum.ADMIN"
            size="sm"
            variant="outline-secondary"
            @click="toEditView(row.item, ref(formStatusEnum.APPROVE))"
          >
            審核
          </b-button>
        </template>
        <template #cell(action1)="row">
          <b-button v-if="row.item.isApprove === '1' || row.item.isApprove === '0'" size="sm" variant="outline-secondary" @click=""
            >送出審核</b-button
          >
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation } from '@/shared/form';
import { computed, onActivated, reactive, Ref, ref } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { useRouter } from '@u3u/vue-hooks';
// import axios from 'axios';
// import { notificationErrorHandler } from '@/shared/http/http-response-helper';
// import { CmsComp } from '@/shared/model/cms-comp.model';
// import { Pagination } from '@/shared/model/pagination.model';

export default {
  name: 'geoMe02Query',
  props: {
    isReload: {
      type: Boolean,
      required: true,
    },
  },
  setup(props, context) {
    enum RoleEnum {
      USER = '使用者',
      ADMIN = '管理員',
    }
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
      APPROVE = 'approve',
    }
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    const switchRoles = () => {
      if (roleRef.value === RoleEnum.ADMIN) roleRef.value = RoleEnum.USER;
      else roleRef.value = RoleEnum.ADMIN;
    };

    const queryOptions = reactive({
      isApprove: [
        { value: '', text: '不拘' },
        { value: '1', text: '未送出審核' },
        { value: '2', text: '待審核' },
        { value: '3', text: '通過' },
        { value: '0', text: '不通過' },
      ],
      subject: [
        { value: '1', text: '土木工程' },
        { value: '2', text: '水利工程' },
        { value: '3', text: '結構工程' },
        { value: '4', text: '大地工程' },
        { value: '5', text: '水土保持' },
        { value: '6', text: '採礦工程' },
        { value: '7', text: '應用地質' },
        { value: '8', text: '其他' },
      ],
    });

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    const isReloadProp = computed(() => props.isReload);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      id: '',
      Name: '', //第一試及格者姓名
      mentorName: '', //指導人姓名
      mentorPost: '', //指導人職稱
      mentorSubject: '', //指導人技師科別
      mentorCertificateID: '', //指導人技師證書字號
      mentorLicenseID: '', //指導人技師執業執照字號
      compName: '', //機構名稱
      compSerialNum: '', //機構統編
      compPrincipalName: '', //機構負責人姓名
      isApprove: '', //審核狀態
      refuseReason: '', //退回原因
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      id: {},
      Name: {}, //第一試及格者姓名
      mentorName: {}, //指導人姓名
      mentorPost: {}, //指導人職稱
      mentorSubject: {}, //指導人技師科別
      mentorCertificateID: {}, //指導人技師證書字號
      mentorLicenseID: {}, //指導人技師執業執照字號
      compName: {}, //機構名稱
      compSerialNum: {}, //機構統編
      compPrincipalName: {}, //機構負責人姓名
      isApprove: {}, //審核狀態
      refuseReason: {}, //退回原因
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
      fields_frontend: [
        {
          key: 'id',
          label: '序號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'mentorName',
          label: '指導人姓名',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'mentorPost',
          label: '指導人職稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'mentorSubject',
          label: '指導人技師科別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => queryOptionsFormatter('subject', value).text,
        },
        {
          key: 'compName',
          label: '機構名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '20%' },
        },
        {
          key: 'compSerialNum',
          label: '機構統編',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'isApprove',
          label: '是否核准',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => queryOptionsFormatter('isApprove', value).text,
        },
        {
          key: 'action1',
          label: '',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'action2',
          label: '',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      fields_backend: [
        {
          key: 'id',
          label: '序號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'name',
          label: '第一試及格者姓名',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'mentorName',
          label: '指導人姓名',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'mentorPost',
          label: '指導人職稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'mentorSubject',
          label: '指導人技師科別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => queryOptionsFormatter('subject', value).text,
        },
        {
          key: 'compName',
          label: '機構名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '20%' },
        },
        {
          key: 'compSerialNum',
          label: '機構統編',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'isApprove',
          label: '是否核准',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => queryOptionsFormatter('isApprove', value).text,
        },
        {
          key: 'action2',
          label: '',
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
              id: '1',
              name: '蕭百成',
              mentorName: '洪志聖',
              mentorPost: '課長',
              mentorSubject: '3',
              compName: '經濟部水利署第五河川局',
              compSerialNum: '64001407',
              isApprove: '1',
            },
            {
              id: '2',
              name: '楊詩蔚',
              mentorName: '潘俊佑',
              mentorPost: '負責人',
              mentorSubject: '5',
              compName: '旺揚工程顧問有限公司',
              compSerialNum: '42665957',
              isApprove: '2',
            },
            {
              id: '3',
              name: '卜一一',
              mentorName: '陳地公',
              mentorPost: '專案經理',
              mentorSubject: '8',
              compName: '黎明工程顧問股份有限公司',
              compSerialNum: '97586963',
              isApprove: '3',
            },
            {
              id: '4',
              name: '楊書豪',
              mentorName: '粘志偉',
              mentorPost: '股長',
              mentorSubject: '1',
              compName: '臺北市政府工務局大地工程處',
              compSerialNum: '25652041',
              isApprove: '0',
              refuseReason:
                '依專門職業及技術人員高等考試大地工程技師考試分階段考試規則(下稱考試規則)第8條第1項規定，實務經歷，' +
                '應為專任於同條第1項各款所列機關（構），並由該機關（構）之大地工程或土木工程、水利工程、結構工程、水土保持、應用地質、' +
                '採礦工程科技師指導（以下稱專業指導人）並簽章證明。\n' +
                '台端所列專業指導人”黃茗宏”君，經查本會登錄資料並無請領技師證書紀錄，如黃君確領有技師證書，請其提供證書字號並登錄表內相關欄位後再提出申請。\n' +
                '另依考試規則第8條第2項規定，非任職於第1項各款所列機關（構）或專業指導人資格不符前項規定之實務經歷，如符合附表所定實務經歷內容者，' +
                '亦得申請審查採計，惟專業指導人應為其直屬主管，經審查通過之工作年資折半採計。',
            },
          ];
          table.totalItems = 4;
        }
      });
    };

    const toEditView = (cmsComp: any, formStatusRef: Ref<formStatusEnum>) => {
      context.emit('changeComponent', 'GeoMe02EditInfo', cmsComp, formStatusRef);
      // useRouter().router.push({
      //   name: 'GeoIn02EditInfo',
      //   params: {
      //     cmsComp
      //   },
      // });
      // useRouter().router.push('/GeoIn02EditInfo')
    };

    const deleteCmsComp = id => {
      $bvModal.msgBoxConfirm(`是否確認刪除公會${id}通訊資料？`).then((isOK: boolean) => {
        if (isOK) {
        }
      });
    };

    // const handlePaginationChanged = (pagination: Pagination) => {
    //   Object.assign(form, pagination);
    //   handleQuery();
    // };

    const queryOptionsFormatter = (option: string, value: string) => queryOptions[option].find(item => item.value === value);

    return {
      queryOptions,
      $v,
      stepVisible,
      queryStatus,
      dataPromise,
      table,
      reset,
      handleQuery,
      toEditView,
      deleteCmsComp,
      roleRef,
      formStatusEnum,
      RoleEnum,
      ref,
      switchRoles,
      // handlePaginationChanged,
    };
  },
};
</script>
<style>
.col-form-label {
  background-color: #737373;
  color: white;
  font-weight: bolder;
}
</style>

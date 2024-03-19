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
            <b-form-row>
              <b-form-group class="col-md-6" label-cols="4" content-cols="8" label="姓名" label-align="right">
                <b-form-input v-model.trim="$v.name.$model"></b-form-input>
              </b-form-group>
              <b-form-group class="col-md-6" label-cols="4" content-cols="8" label="求職條件" label-align="right">
                <b-form-input v-model.trim="$v.jobConditions.$model"></b-form-input>
              </b-form-group>
            </b-form-row>

            <b-form-row>
              <b-form-group class="col-12 justify-content-center" label-cols="2" content-cols="10" label="機構類型" label-align="right">
                <b-form-checkbox-group v-model="$v.agencyTypeList.$model" :options="queryOptions.agencyType"></b-form-checkbox-group>
              </b-form-group>
            </b-form-row>

            <b-form-row>
              <b-form-group class="col-12" label-cols="2" content-cols="10" label="科別" label-align="right">
                <b-form-checkbox-group v-model="$v.subjectList.$model" :options="queryOptions.subject"></b-form-checkbox-group>
              </b-form-group>
            </b-form-row>

            <b-form-row class="mt-2" align="right">
              <b-col>
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
    <section class="container mt-3" v-if="queryStatus">
      <!--      <div class="card">-->
      <!--        <div class="card-header py-1">-->
      <!--          <div class="row align-items-center">-->
      <!--            <div class="col-sm-11 p-0">-->
      <!--              <h5 class="m-0">-->
      <!--                <font-awesome-icon icon="project-diagram"></font-awesome-icon>-->
      <!--                系統資料區-->
      <!--              </h5>-->
      <!--            </div>-->
      <!--            <div class="col-sm-1 p-0">-->
      <!--              <b-button class="float-right" @click="stepVisible.result = !stepVisible.result">-->
      <!--                <font-awesome-icon v-if="stepVisible.result" icon="chevron-up"></font-awesome-icon>-->
      <!--                <font-awesome-icon v-if="!stepVisible.result" icon="chevron-down"></font-awesome-icon>-->
      <!--              </b-button>-->
      <!--            </div>-->
      <!--          </div>-->
      <!--        </div>-->
      <!--        <div class="card-body">-->
      <!--          <b-collapse id="collapse-5" class="px-1 py-1" v-model="stepVisible.result">-->
      <i-table
        class="table-sm"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      >
        <!--              @changePagination="handlePaginationChanged($event)"-->
        <!--            >-->
        <!--        <template #cell(action)="row">-->
        <!--          <b-button size="sm" variant="outline-secondary" @click="toEditView(row.item, ref(formStatusEnum.INFO))">查看</b-button>-->
        <!--          <b-button size="sm" variant="outline-secondary" v-if="roleRef === RoleEnum.ADMIN" @click="toEditView(row.item, ref(formStatusEnum.MODIFY))">回覆</b-button>-->
        <!--          <b-button size="sm" variant="danger" @click="">刪除</b-button>-->
        <!--        </template>-->
      </i-table>
      <!--          </b-collapse>-->
      <!--        </div>-->
      <!--      </div>-->
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation } from '@/shared/form';
import { computed, onActivated, reactive, Ref, ref } from '@vue/composition-api';
// import NotificationService from '@/shared/notification/notification-service';
// import { useNotification } from '@/shared/notification';
// import { useBvModal } from '@/shared/modal';
// import axios from 'axios';
// import { notificationErrorHandler } from '@/shared/http/http-response-helper';
// import { CmsComp } from '@/shared/model/cms-comp.model';
// import { Pagination } from '@/shared/model/pagination.model';

export default {
  name: 'geoIn03Query',
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
    }
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    const queryOptions = reactive({
      agencyType: [
        { value: '1', text: '顧問公司' },
        { value: '2', text: '技師事務所' },
        { value: '3', text: '營造業' },
        { value: '4', text: '政府機關' },
      ],
      subject: [
        { value: '1', text: '土木' },
        { value: '2', text: '大地' },
        { value: '3', text: '結構' },
        { value: '4', text: '水利' },
        { value: '5', text: '測量' },
        { value: '6', text: '環工' },
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
      name: '',
      jobConditions: '',
      agencyTypeList: [],
      subjectList: [],
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      name: {},
      jobConditions: {},
      agencyTypeList: {},
      subjectList: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      result: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    // modal物件
    // const $bvModal = useBvModal();

    const table = reactive({
      fields: [
        {
          key: 'id',
          label: '序號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'name',
          label: '姓名',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '20%' },
        },
        {
          key: 'companyPhone',
          label: '公司電話',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'jobConditions',
          label: '求職條件',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'email',
          label: '電子郵件',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'mobilePhone',
          label: '手機',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        // {
        //   key: 'action',
        //   label: '',
        //   sortable: false,
        //   thClass: 'text-center',
        //   tdClass: 'text-center align-middle',
        // },
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
              name: '洪立彥',
              companyPhone: '02-80723333#4303',
              jobConditions: '未填寫資料',
              email: 'ligonkuamai@gmail.com',
              mobilePhone: '0912509469',
            },
            {
              id: '2',
              name: '王子熙',
              companyPhone: '-',
              jobConditions: '分析或設計較佳',
              email: 'handsomesammi@gmail.com',
              mobilePhone: '0939289090',
            },
          ];
          table.totalItems = 2;
        }
      });
    };

    // const toEditView = (cmsComp: any, formStatusRef: Ref<formStatusEnum>) => {
    //   context.emit('changeComponent', 'GeoIn03EditInfo', cmsComp, formStatusRef);
    // };

    // const deleteCmsComp = id => {
    //   $bvModal.msgBoxConfirm(`是否確認刪除公會${id}通訊資料？`).then((isOK: boolean) => {
    //     if (isOK) {
    //     }
    //   });
    // };

    // const handlePaginationChanged = (pagination: Pagination) => {
    //   Object.assign(form, pagination);
    //   handleQuery();
    // };

    // const queryOptionsFormatter = (option: string, value: string) => queryOptions[option].find(item => item.value === value);

    return {
      queryOptions,
      $v,
      stepVisible,
      queryStatus,
      dataPromise,
      table,
      reset,
      handleQuery,
      roleRef,
      formStatusEnum,
      RoleEnum,
      ref,
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
.form-row {
  align-items: center;
}
</style>

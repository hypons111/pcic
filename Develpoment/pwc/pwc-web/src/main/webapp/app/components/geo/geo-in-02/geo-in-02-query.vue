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
              <b-form-group
                v-if="roleRef === RoleEnum.ADMIN"
                class="col-sm-6"
                label-cols-md="4"
                content-cols-md="8"
                label="機構名稱"
                label-align-md="right"
              >
                <b-form-input v-model.trim="$v.compName.$model"></b-form-input>
              </b-form-group>
              <b-form-group class="col-sm-6" label-cols-md="4" content-cols-md="8" label="職稱" label-align-md="right">
                <b-form-input v-model.trim="$v.jobTitle.$model"></b-form-input>
              </b-form-group>
            </b-form-row>

            <b-form-row v-if="roleRef === RoleEnum.ADMIN">
              <b-form-group class="col-sm-6" label-cols-md="4" content-cols-md="8" label="工作屬性" label-align-md="right">
                <b-form-select v-model="$v.jobType.$model" :options="queryOptions.jobType"></b-form-select>
              </b-form-group>
              <b-form-group class="col-sm-6" label-cols-md="4" content-cols-md="8" label="待遇" label-align-md="right">
                <b-form-select v-model="$v.salaryRange.$model" :options="queryOptions.salaryRange"></b-form-select>
              </b-form-group>
            </b-form-row>

            <b-form-row>
              <b-form-group class="col-sm-6" label-cols-md="4" content-cols-md="8" label="刊登狀態" label-align-md="right">
                <b-form-select v-model="$v.publishStatus.$model" :options="queryOptions.publishStatus"></b-form-select>
              </b-form-group>
              <b-form-group class="col-sm-6" label-cols-md="4" content-cols-md="8" label="是否核准" label-align-md="right">
                <b-form-select v-model="$v.isApprove.$model" :options="queryOptions.isApprove"></b-form-select>
              </b-form-group>
            </b-form-row>

            <b-form-row>
              <b-col cols="2" class="text-right label">
                <span>工作地點</span>
              </b-col>
              <b-col cols="10">
                <div v-for="option in queryOptions.city" :key="option.value" class="col-sm-2 d-inline-block">
                  <b-form-checkbox v-model="$v.workPlace.$model" :value="option.value">
                    {{ option.text }}
                  </b-form-checkbox>
                </div>
              </b-col>

              <!--              <b-form-group label="Using options array:" v-slot="{ ariaDescribedby }">-->
              <!--                <b-form-checkbox-group-->
              <!--                  id="checkbox-group-1"-->
              <!--                  v-model="selected"-->
              <!--                  :options="options"-->
              <!--                  :aria-describedby="ariaDescribedby"-->
              <!--                  name="flavour-1"-->
              <!--                ></b-form-checkbox-group>-->
              <!--              </b-form-group>-->
            </b-form-row>

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
        :fields="roleRef === RoleEnum.ADMIN ? table.fields_admin : table.fields_user"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      >
        <!--              @changePagination="handlePaginationChanged($event)"-->
        <!--            >-->
        <template #cell(action)="row">
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
  name: 'geoIn02Query',
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
      jobType: [
        { value: '', text: '不拘' },
        { value: '1', text: '工程技術顧問公司且有合格指導人' },
        { value: '2', text: '技師事務所且有合格指導人' },
        { value: '3', text: '營造業且有合格指導人' },
        { value: '4', text: '政府工程機關且有合格指導人' },
        { value: '5', text: '其他類型機構或無合格指導人' },
      ],
      salaryRange: [
        { value: '', text: '不拘' },
        { value: '1', text: '年薪 40萬(不含)以下' },
        { value: '2', text: '年薪 40萬(含)~55萬(不含)' },
        { value: '3', text: '年薪 55萬(含)~70萬(不含)' },
        { value: '4', text: '年薪 70萬(含)~85萬(不含)' },
        { value: '5', text: '年薪 85萬(含)~100萬(不含)' },
        { value: '6', text: '年薪 100萬(含)以上' },
        { value: '0', text: '面議' },
      ],
      publishStatus: [
        { value: '', text: '不拘' },
        { value: '1', text: '未刊登' },
        { value: '2', text: '已刊登' },
        { value: '3', text: '已徵才' },
        { value: '0', text: '結束刊登' },
      ],
      isApprove: [
        { value: '', text: '不拘' },
        { value: '1', text: '待核准' },
        { value: '2', text: '是' },
        { value: '0', text: '否' },
      ],
      city: [
        { value: '基隆市', text: '基隆市' },
        { value: '台北市', text: '台北市' },
        { value: '新北市', text: '新北市' },
        { value: '桃園市', text: '桃園市' },
        { value: '新竹縣', text: '新竹縣' },
        { value: '新竹市', text: '新竹市' },
        { value: '苗栗縣', text: '苗栗縣' },
        { value: '台中市', text: '台中市' },
        { value: '南投縣', text: '南投縣' },
        { value: '彰化縣', text: '彰化縣' },
        { value: '雲林縣', text: '雲林縣' },
        { value: '嘉義縣', text: '嘉義縣' },
        { value: '嘉義市', text: '嘉義市' },
        { value: '台南市', text: '台南市' },
        { value: '高雄市', text: '高雄市' },
        { value: '屏東縣', text: '屏東縣' },
        { value: '台東縣', text: '台東縣' },
        { value: '花蓮縣', text: '花蓮縣' },
        { value: '宜蘭縣', text: '宜蘭縣' },
        { value: '澎湖縣', text: '澎湖縣' },
        { value: '金門縣', text: '金門縣' },
        { value: '連江縣', text: '連江縣' },
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
      compName: '', //機構名稱
      jobTitle: '', //職稱
      jobType: '', //工作屬性
      salaryRange: '', //薪水
      publishStatus: '', //刊登狀態
      isApprove: '', //是否核准
      workPlace: [], //工作地點
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      compName: {}, //機構名稱
      jobTitle: {}, //職稱
      jobType: {}, //工作屬性
      salaryRange: {}, //薪水
      publishStatus: {}, //刊登狀態
      isApprove: {}, //是否核准
      workPlace: {}, //工作地點
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      result: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
      fields_admin: [
        {
          key: 'geoJobId',
          label: '序號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'compName',
          label: '機構名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '20%' },
        },
        {
          key: 'jobTitle',
          label: '職稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'jobType',
          label: '工作屬性',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => queryOptionsFormatter('jobType', value).text,
        },
        {
          key: 'publishTimeStart',
          label: '刊登時間(起)',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'publishTimeEnd',
          label: '刊登時間(迄)',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'publishStatus',
          label: '刊登狀態',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => queryOptionsFormatter('publishStatus', value).text,
        },
        {
          key: 'isApprove',
          label: '是否核准',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => queryOptionsFormatter('isApprove', value).text,
        },
        {
          key: 'action',
          label: '',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      fields_user: [
        {
          key: 'geoJobId',
          label: '序號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'jobTitle',
          label: '職稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'jobType',
          label: '工作屬性',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => queryOptionsFormatter('jobType', value).text,
        },
        {
          key: 'publishTimeStart',
          label: '刊登時間(起)',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'publishTimeEnd',
          label: '刊登時間(迄)',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'publishStatus',
          label: '刊登狀態',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => queryOptionsFormatter('publishStatus', value).text,
        },
        {
          key: 'isApprove',
          label: '是否核准',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => queryOptionsFormatter('isApprove', value).text,
        },
        {
          key: 'action',
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
              geoJobId: '1',
              compName: '光宇工程顧問股份有限公司',
              jobTitle: '軟體工程師',
              jobType: '2',
              salaryRange: '6',
              workPlace: ['台北市', '新北市'],
              publishTimeStart: '2021/11/25',
              publishTimeEnd: '2021/11/26',
              publishStatus: '1',
              isApprove: '1',
            },
            {
              geoJobId: '2',
              compName: '光宇工程顧問股份有限公司',
              jobTitle: '軟體工程師',
              jobType: '2',
              salaryRange: '6',
              workPlace: ['台北市', '新北市'],
              publishTimeStart: '2021/11/25',
              publishTimeEnd: '2021/11/26',
              publishStatus: '2',
              isApprove: '2',
            },
            {
              geoJobId: '3',
              compName: '光宇工程顧問股份有限公司',
              jobTitle: '軟體工程師',
              jobType: '2',
              salaryRange: '6',
              workPlace: ['台北市', '新北市'],
              publishTimeStart: '2021/11/25',
              publishTimeEnd: '2021/11/26',
              publishStatus: '1',
              isApprove: '0',
              rejectReason: '已過刊登日期',
            },
          ];
          table.totalItems = 3;
        }
      });
    };

    const toEditView = (cmsComp: any, formStatusRef: Ref<formStatusEnum>) => {
      context.emit('changeComponent', 'GeoIn02EditInfo', cmsComp, formStatusRef);
      // useRouter().router.push({
      //   name: 'GeoIn02EditInfo',
      //   params: {
      //     cmsComp
      //   },
      // });
      // useRouter().router.push('/GeoIn02EditInfo')
    };

    const deleteCmsComp = geoJobId => {
      $bvModal.msgBoxConfirm(`是否確認刪除公會${geoJobId}通訊資料？`).then((isOK: boolean) => {
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

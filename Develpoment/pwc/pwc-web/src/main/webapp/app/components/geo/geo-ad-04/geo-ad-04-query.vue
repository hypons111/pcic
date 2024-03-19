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
              <b-form-group class="col-12" label-cols="2" content-cols="10" label="類科名稱" label-align="right">
                <b-form-select v-model="$v.subject.$model" :options="queryOptions.subject"></b-form-select>
              </b-form-group>
            </b-form-row>

            <b-form-row>
              <b-form-group class="col-md-6" label-cols="4" content-cols="8" label="姓名" label-align="right">
                <b-form-input v-model.trim="$v.name.$model"></b-form-input>
              </b-form-group>
              <b-form-group class="col-md-6" label-cols="4" content-cols="8" label="身分證字號" label-align="right">
                <b-form-input v-model.trim="$v.IDNumber.$model"></b-form-input>
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

    <section class="container mt-3">
      <b-button class="ml-3 mr-2" size="md" variant="primary" @click="">
        <font-awesome-icon :icon="['fas', 'upload']" />
        {{ $t('button.upload') }}
      </b-button>
      <b-button class="" size="md" variant="primary" @click="">通知</b-button>
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
  name: 'geoAd04Query',
  setup(props, context) {
    const queryOptions = reactive({
      subject: [
        { value: '', text: '不拘' },
        { value: '1', text: '大地工程技師(一)' },
      ],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      id: '',
      subject: '',
      ranking: '',
      name: '',
      sex: '',
      birthday: '',
      IDNumber: '',
      education: '',
      address: '',
      certificateID: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      id: {},
      subject: {},
      ranking: {},
      name: {},
      sex: {},
      birthday: {},
      IDNumber: {},
      education: {},
      address: {},
      certificateID: {},
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
          label: '冊報序號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'subject',
          label: '類科名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'ranking',
          label: '名次',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'name',
          label: '姓名',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // thStyle: { width: '10%' },
        },
        {
          key: 'sex',
          label: '性別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'birthday',
          label: '出生年月日',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'IDNumber',
          label: '身分證字號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'education',
          label: '學歷',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'address',
          label: '地址',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'certificateID',
          label: '入場證號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
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
              id: '109051',
              subject: '大地工程技師(一)',
              ranking: '12',
              name: '蔡哲倫',
              sex: '男',
              birthday: '86年05月21日',
              IDNumber: 'F130026401',
              education: '國立交通大學土木工程學系(科)畢業',
              address: '臺北市北投區豐年路一段56號12樓',
              certificateID: '10310077',
            },
            {
              id: '109050',
              subject: '大地工程技師(一)',
              ranking: '17',
              name: '廖柄淳',
              sex: '男',
              birthday: '75年06月04日',
              IDNumber: 'B122319831',
              education: '中國科技大學土木與防災設計系畢業',
              address: '新北市土城區學府路一段126巷78號14樓',
              certificateID: '10310135',
            },
          ];
          table.totalItems = 2;
        }
      });
    };

    return {
      queryOptions,
      $v,
      stepVisible,
      queryStatus,
      dataPromise,
      table,
      reset,
      handleQuery,
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

<template>
  <div>
    <!--    <section class="container">-->
    <!--      <b-form-row v-if="roleRef === RoleEnum.USER">-->
    <!--        <b-button class="ml-1" size="md" variant="primary" @click="toEditView(null, ref(formStatusEnum.CREATE))">新增</b-button>-->
    <!--      </b-form-row>-->
    <!--    </section>-->
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
            <!--            <b-form-row>-->
            <!--              <b-input-group prepend="時間區間" class="mb-2">-->
            <!--                <b-form-input type="date" v-model="$v.questionTimeStart.$model"></b-form-input>-->
            <!--                <b-form-input type="date" v-model="$v.questionTimeEnd.$model"></b-form-input>-->
            <!--              </b-input-group>-->
            <!--            </b-form-row>-->

            <b-form-row v-if="roleRef === RoleEnum.ADMIN">
              <b-form-group class="col-12" label-cols-md="2" content-cols-md="10" label="姓名" label-align-md="right">
                <b-form-input v-model.trim="$v.name.$model"></b-form-input>
              </b-form-group>
            </b-form-row>

            <b-form-row>
              <!--              <b-input-group prepend="問題" class="mb-2">-->
              <!--                <b-form-input v-model.trim="$v.question.$model"></b-form-input>-->
              <!--              </b-input-group>-->
              <b-form-group class="col-12" label-cols-md="2" content-cols-md="10" label="問題" label-align-md="right">
                <b-form-input v-model.trim="$v.question.$model"></b-form-input>
              </b-form-group>
            </b-form-row>

            <b-form-row>
              <b-form-group class="col-12" label-cols-md="2" content-cols-md="10" label="狀態" label-align-md="right">
                <b-form-select v-model.trim="$v.status.$model" :options="queryOptions.status"></b-form-select>
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

    <section class="container mt-3" v-if="roleRef === RoleEnum.USER">
      <b-button class="ml-3" size="md" variant="primary" @click="toEditView(null, ref(formStatusEnum.CREATE))">
        <font-awesome-icon :icon="['fas', 'plus']" />
        {{ $t('button.insert') }}
      </b-button>
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
        :fields="roleRef === RoleEnum.ADMIN ? table.fields_admin : table.fields_user"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      >
        <!--              @changePagination="handlePaginationChanged($event)"-->
        <!--            >-->
        <template #cell(action)="row">
          <b-button
            size="sm"
            variant="outline-secondary"
            v-if="roleRef === RoleEnum.USER"
            @click="toEditView(row.item, ref(formStatusEnum.INFO))"
          >
            <font-awesome-icon :icon="['fas', 'binoculars']" />
            查看
          </b-button>
          <b-button
            size="sm"
            variant="outline-secondary"
            v-if="roleRef === RoleEnum.ADMIN"
            @click="toEditView(row.item, ref(formStatusEnum.MODIFY))"
          >
            <font-awesome-icon :icon="['far', 'edit']" />
            編輯
          </b-button>
          <b-button size="sm" variant="danger" @click="">
            <font-awesome-icon :icon="['fas', 'trash']" />
            {{ $t('button.delete') }}
          </b-button>
        </template>
        <!--              <template #cell(action2)="row">-->
        <!--                <b-button size="sm" variant="danger" @click="">刪除</b-button>-->
        <!--              </template>-->
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
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
// import { replyStatusFormatter } from '@/shared/formatter/common';
// import axios from 'axios';
// import { notificationErrorHandler } from '@/shared/http/http-response-helper';
// import { CmsComp } from '@/shared/model/cms-comp.model';
// import { Pagination } from '@/shared/model/pagination.model';
import { useRouter } from '@u3u/vue-hooks';

export default {
  name: 'geoCo01Query',
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
      status: [
        { value: '', text: '不拘' },
        { value: '0', text: '未回覆' },
        { value: '1', text: '已回覆' },
      ],
    });

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    const isReloadProp = computed(() => props.isReload);

    const now = new Date();
    const day = ('0' + now.getDate()).slice(-2);
    const month = ('0' + (now.getMonth() + 1)).slice(-2);
    const today = now.getFullYear() + '-' + month + '-' + day;
    const lastYear = now.getFullYear() - 1 + '-' + month + '-' + day;

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      // questionTime: '', //提問時間
      name: '', //姓名
      question: '', //問題
      // answer: '', //回答
      status: '',
      questionTimeStart: lastYear,
      questionTimeEnd: today,
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      // questionTime: {},
      name: {},
      question: {},
      // answer: {},
      status: {},
      questionTimeStart: {},
      questionTimeEnd: {},
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
          key: 'id',
          label: '序號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'name',
          label: '姓名',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'question',
          label: '問題',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        // formatter: (value: string) => guildTypeFormatter(value),
        // {
        //   key: 'answer',
        //   label: '回答',
        //   sortable: false,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        // },
        {
          key: 'questionTime',
          label: '提問時間',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'status',
          label: '狀態',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => replyStatusFormatter(value),
        },
        {
          key: 'action',
          label: '',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        // {
        //   key: 'action2',
        //   label: '',
        //   sortable: false,
        //   thClass: 'text-center',
        //   tdClass: 'text-center align-middle',
        // },
      ],
      fields_user: [
        {
          key: 'id',
          label: '序號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'question',
          label: '問題',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'questionTime',
          label: '提問時間',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'status',
          label: '狀態',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => replyStatusFormatter(value),
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
              id: '1',
              questionTime: '2018-08-09 15:13:26',
              name: '卜一一',
              question: '106年大地技師分階段考試第一試何時報名?',
              answer: '',
              status: '0',
            },
            {
              id: '2',
              questionTime: '2018-08-09 15:13:26',
              name: '卜一一',
              question: '106年大地技師分階段考試第一試何時報名?',
              answer:
                '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。' +
                '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。' +
                '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。' +
                '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。' +
                '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。',
              status: '1',
            },
            {
              id: '3',
              questionTime: '2018-08-09 15:13:26',
              name: '卜一一',
              question: '106年大地技師分階段考試第一試何時報名?',
              answer: '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。',
              status: '1',
            },
            {
              id: '4',
              questionTime: '2018-08-09 15:13:26',
              name: '卜一一',
              question: '106年大地技師分階段考試第一試何時報名?',
              answer: '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。',
              status: '1',
            },
            {
              id: '5',
              questionTime: '2018-08-09 15:13:26',
              name: '卜一一',
              question: '106年大地技師分階段考試第一試何時報名?',
              answer: '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。',
              status: '1',
            },
            {
              id: '6',
              questionTime: '2018-08-09 15:13:26',
              name: '卜一一',
              question: '106年大地技師分階段考試第一試何時報名?',
              answer: '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。',
              status: '1',
            },
            {
              id: '7',
              questionTime: '2018-08-09 15:13:26',
              name: '卜一一',
              question: '106年大地技師分階段考試第一試何時報名?',
              answer: '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。',
              status: '1',
            },
            {
              id: '8',
              questionTime: '2018-08-09 15:13:26',
              name: '卜一一',
              question: '106年大地技師分階段考試第一試何時報名?',
              answer: '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。',
              status: '1',
            },
            {
              id: '9',
              questionTime: '2018-08-09 15:13:26',
              name: '卜一一',
              question: '106年大地技師分階段考試第一試何時報名?',
              answer: '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。',
              status: '1',
            },
            {
              id: '10',
              questionTime: '2018-08-09 15:13:26',
              name: '卜一一',
              question: '106年大地技師分階段考試第一試何時報名?',
              answer: '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。',
              status: '1',
            },
            {
              id: '11',
              questionTime: '2018-08-09 15:13:26',
              name: '卜一一',
              question: '106年大地技師分階段考試第一試何時報名?',
              answer: '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。',
              status: '1',
            },
            {
              id: '12',
              questionTime: '2018-08-09 15:13:26',
              name: '卜一一',
              question: '106年大地技師分階段考試第一試何時報名?',
              answer: '目前已過報名時間，有關技師分階段考試報名訊息請至考選部網站查詢。',
              status: '1',
            },
          ];
          table.totalItems = 12;
        }
      });
    };

    const toEditView = (cmsComp: any, formStatusRef: Ref<formStatusEnum>) => {
      // alert("toEditView : " + formStatusRef.value)
      context.emit('changeComponent', 'GeoCo01EditInfo', cmsComp, formStatusRef);
      // useRouter().router.push({
      //   name: 'techexamQaEditInfo',
      //   params: {
      //     cmsComp,
      //   },
      // });
      // useRouter().router.push('/techexamQaEditInfo')
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

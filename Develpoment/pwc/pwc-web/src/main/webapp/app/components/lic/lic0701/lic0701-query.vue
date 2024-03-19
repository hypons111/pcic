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
              <i-form-group-check :labelStar="true" label="計畫名稱:" :item="$v.licProjectBasicId">
                <b-form-select v-model.trim="$v.licProjectBasicId.$model" :options="queryOptions.licProjectBasicName" :state="validateState($v.licProjectBasicId)"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="姓名:">
                <b-form-input v-model.trim="$v.memberName.$model"></b-form-input>
              </i-form-group-check>
              <i-form-group-check label="職稱:">
                <b-form-input v-model="$v.memberTitle.$model"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="機關名稱:">
                <b-form-select v-model.trim="$v.orgId.$model" :options="queryOptions.orgIdList"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :labelStar="true" label="機關屬性:" :item="$v.orgAttr">
                <b-form-select v-model.trim="$v.orgAttr.$model" :options="queryOptions.orgAttrList" :state="validateState($v.orgAttr)"></b-form-select>
              </i-form-group-check>
            </b-form-row>

            <div class="button-float-right">
              <b-button-toolbar>
                <i-button class="mt-1 ml-1" type="search" @click="handleQuery"></i-button>
                <i-button class="mt-1 ml-1" type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>
    <!-- 新增按鈕 -->
    <section>
      <div class="container mt-1 mb-1">
        <i-button type="file-earmark-plus" @click="toCreateView"></i-button>
      </div>
    </section>
    <!-- i-table -->
    <section>
      <div class="container" v-if="queryStatus">
        <i-table
          class="table-sm"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :fields="table.fields"
          :totalItems="table.totalItems"
          :is-server-side-paging="true"
          @changePagination="handlePaginationChanged($event)"
        >
          <template #cell(action)="row">
            <i-button class="mt-1" type="pencil-square" @click="toEditView(row.item)"></i-button>
            <i-button class="mt-1" type="trash" @click="deleteProject(row.item.licWorkshopMemberId)"></i-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, ref, watch, onMounted } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import { required } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import iButton from '@/shared/buttons/i-button.vue';

export default {
  components: { iButton },
  name: 'lic0701Query',
  props: {
    isReload: {
      required: false,
    },
  },
  setup() {
    onActivated(() => {
      // if (isReloadProp.value) {
      //   handleQuery();
      // }
    });

    onMounted(() => {
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      memberName: '',
      memberTitle: '',
      orgId: '',
      orgAttr: '',
      licProjectBasicId: '',
    };

    const rules = {
      memberName: {},
      memberTitle: {},
      orgId: {},
      orgAttr: {notNull: required},
      licProjectBasicId: {notNull: required},
    };

    // 下拉選單選項
    const queryOptions = reactive({
      orgIdList: [
        { value: '', text: '全部' },
        { value: '11111111', text: '11111111：台灣中油股份有限公司興建工程處' },
        { value: '22222222', text: '22222222：交通部公路總局第三區養護工程處' },
        { value: '33333333', text: '33333333：新北市政府工程局' },
        { value: '44444444', text: '44444444：臺灣港務股份有限公司基隆港務分公司' },
      ],
      orgAttrList: [
        { value: '', text: '全部' },
        { value: '1', text: '1：主辦機關' },
        { value: '2', text: '2：審證機關' },
      ],
      licProjectBasicName: [
        { value: '', text: '全部' },
        { value: 'L10801', text: 'L10801：天然氣事業部永安廠增建儲糟投資計畫' },
        { value: 'L10802', text: 'L10802：台9線花東縱谷公路安全景觀大道計畫(臺東段)' },
        { value: 'L10803', text: 'L10803：臺北都會區大眾捷運系統三鶯線建設計畫' },
      ],
    });

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const table = reactive({
      fields: [
        {
          key: 'licProjectBasicId',
          label: '計畫名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 35%',
        },
        {
          key: 'memberName',
          label: '姓名',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 6%',
        },
        {
          key: 'memberTitle',
          label: '職稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%',
        },
        {
          key: 'orgId',
          label: '機關',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 25%',
        },
        {
          key: 'orgAttr',
          label: '機關屬性',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'action',
          label: '操作(編輯涉及證照項目)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width:10%',
        },
      ],
      data: undefined,
      totalItems: 3,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    //顯示查詢條件區
    let stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //轉到新增頁面
    const toCreateView = () => {
      navigateByNameAndParams('lic0701-edit-info', { formStatusForward: 'create' ,isNotKeepAlive: true  });
    };

    //轉到編輯頁面
    const toEditView = (licWorkshopMember: any) => {
      navigateByNameAndParams('lic0701-edit-info', { formStatusForward: 'modify', licWorkshopMember: licWorkshopMember, isNotKeepAlive: true });
    };

    //依條件查詢工作小組成員
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
      //   if (isValid) {
      //     //重置條件
      //     table.data = undefined;
      //     axios
      //       .post('/rec-projects/criteria-jpa', form)
      //       .then(({ data }) => {
      //         table.data = data.content.slice(0, data.content.length);
      //         table.totalItems = data.totalElements;
      //       })
      //       .finally()
      //       .catch(notificationErrorHandler(notificationService));
      //   }
      // });
      queryStatus.value=true;
      table.data = [
        {
          licProjectBasicId : '(L10801)天然氣事業部永安廠增建儲糟投資計畫',
          memberName : '陳宇軒',
          memberTitle : '工作小組成員',
          orgId : '台灣中油股份有限公司興建工程處',
          orgAttr : '主辦機關',
        },
        {
          licProjectBasicId : '(L10802)台9線花東縱谷公路安全景觀大道計畫(臺東段)',
          memberName : '陳克劼',
          memberTitle : '段長',
          orgId : '交通部公路總局第三區養護工程處',
          orgAttr : '審證機關',
        },
        {
          licProjectBasicId : '(L10803)臺北都會區大眾捷運系統三鶯線建設計畫',
          memberName : '包晃豪',
          memberTitle : '主任秘書',
          orgId : '新北市政府工務局',
          orgAttr : '審證機關',
        },
      ]
    })};

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      $v,
      stepVisible,
      queryStatus,
      validateState,
      checkValidity,
      handleQuery,
      reset,
      handlePaginationChanged,
      queryOptions,
      table,
      toCreateView,
      toEditView,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

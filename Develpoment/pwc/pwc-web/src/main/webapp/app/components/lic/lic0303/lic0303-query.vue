<template>
  <div>
     <!-- 匯出ods 按鈕 -->
    <section>
      <div class="container mt-1 mb-1">
        <b-button variant="info" @click="toSortView">匯出ods</b-button>
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
  name: 'lic0303Query',
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
      handleQuery();
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      memberName: '',
    };

    const rules = {
      memberName: {},
    };

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const table = reactive({
      fields: [
        {
          key: 'projectName',
          label: '計畫名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 8%',
        },
        {
          key: 'licenseCategoryName',
          label: '證照許可類別',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%',
        },
        {
          key: 'licenseName',
          label: '送審書件名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 25%',
        },
        {
          key: 'reviewOrgName',
          label: '審證機關',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%',
        },
        {
          key: 'expectDate',
          label: '預定完成日期',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: value => formatDate(new Date(value), '/'),
          thStyle: 'width: 10%',
        },
        {
          key: 'realDate',
          label: '實際完成日期',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: value => formatDate(new Date(value), '/'),
          thStyle: 'width: 10%' ,
        },
        {
          key: 'procStautsNameTimes',
          label: '辦理情形',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%',
        },
        {
          key: 'procDate',
          label: '日期',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: value => formatDate(new Date(value), '/'),
          thStyle: 'width: 8%' ,
        },
        {
          key: 'procRemark',
          label: '備註',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 5%',
        },
      ],
      data: undefined,
      totalItems: 2,
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
          projectName : '淡江大橋',
          licenseCategoryName : '河川區域使用許可',
          licenseName : '河川區域使用許可都市計畫擬定或土地使用變更證書',
          reviewOrgName : '經濟部商品檢驗局',
          expectDate : '2015-05-29',
          realDate : '',
          procStautsNameTimes : '第1次送審',
          procDate : '2015-07-15',
          procRemark : '無',
        },
        {
          projectName : '淡江大橋',
          licenseCategoryName : '河川區域使用許可',
          licenseName : '河川區域使用許可都市計畫擬定或土地使用變更證書',
          reviewOrgName : '經濟部商品檢驗局',
          expectDate : '2015-05-29',
          realDate : '',
          procStautsNameTimes : '第2次送審',
          procDate : '2015-08-13',
          procRemark : '無',
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

<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <div>
          <i-button size="md" type="folder-plus" @click="toAdd"></i-button>
        </div>
        <div>
          <i-table
            class="table-sm"
            :itemsUndefinedBehavior="'loading'"
            :items="table.data"
            :fields="table.fields"
            :totalItems="table.totalItems"
            :is-server-side-paging="true"
            :hide-no="true"
            @changePagination="handlePaginationChanged($event)"
          >
            <template #cell(action)>
              <i-button size="md" type="pencil-square" @click="toEdit"></i-button>
              <i-button size="md" type="trash" @click="toDelete"></i-button>
            </template>
          </i-table>
        </div>
      </div>
    </div>

    <!-- 錯誤訊息 -->
    <b-modal
      id="valid-error-modal"
      size="lg"
      title="資料驗證有誤:"
      :header-text-variant="'light'"
      :header-bg-variant="'danger'"
      hide-footer
    >
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModals.hide('valid-error-modal')">了解</b-button>
    </b-modal>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, computed, Reactive, onMounted } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';

export default {
  name: 'bidAbc001EditInfo2',
  props: {
    bidProjectKeyProp: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectModifyKeyProp: Ref<any> = toRef(props, 'bidProjectKeyProp');

    onMounted(() => {
      handleQuery();
    });

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const notificationService: NotificationService = useNotification();

    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
      DELETE = '刪除',
    }

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(criteria, pagination);
      handleQuery();
    };

    const table = ref([]);
    const handleQuery = () => {
      table.data = [];
      table.totalItems = 3;
      table.data.splice(0, table.data.length, ...mockdata);
    };

    const table = reactive({
      fields: [
        {
          key: 'a',
          label: '品質管理制(Q=20分)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          key: 'b',
          label: '施工品質(W=60分)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'c',
          label: '施工進度(P=20分)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'd',
          label: '評分',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'e',
          label: '環境',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'f',
          label: '安全',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'g',
          label: '強度',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'h',
          label: '美觀',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'i',
          label: '功能',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'j',
          label: '委員表現',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: '動作',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width:15%',
        },
      ],
      data: undefined,
      totalItems: 3,
    });

    const mockdata = [
      {
        a: '(未填)',
        b: '(未填)',
        c: '17.0',
        d: '17.0',
        e: '82.2',
        f: '80.0',
        g: '80.0',
        h: '82.2',
        i: '82.2',
        j: '備受肯定',
      },
      {
        a: '16.0',
        b: '48.0',
        c: '17.0',
        d: '17.0',
        e: '82.2',
        f: '82.0',
        g: '82.0',
        h: '82.2',
        i: '82.2',
        j: '備受肯定',
      },
      {
        a: '15.0',
        b: '49.0',
        c: '18.0',
        d: '18.0',
        e: '82.2',
        f: '81.0',
        g: '81.0',
        h: '82.2',
        i: '82.2',
        j: '備受肯定',
      },
    ];

    const formDefault = {};

    const rules = {};

    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAbc001Query');
    }

    const toAdd = () => {
      reset();
      navigateByNameAndParams('bidAbc001Edit2', {
        formStatus: FormStatusEnum.CREATE,
        bidProjectModifyKeyProp: props.bidProjectKeyProp,
        isNotKeepAlive: true,
      });
    };

    const toEdit = () => {
      reset();
      navigateByNameAndParams('bidAbc001Edit2', {
        formStatus: FormStatusEnum.MODIFY,
        bidProjectModifyKeyProp: props.bidProjectKeyProp,
        isNotKeepAlive: true,
      });
    };

    const toDelete = () => {
      reset();
      navigateByNameAndParams('bidAbc001Edit2', {
        formStatus: FormStatusEnum.DELETE,
        bidProjectModifyKeyProp: props.bidProjectKeyProp,
        isNotKeepAlive: true,
      });
    };

    return {
      $v,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      formDefault,
      stepVisible,
      handlePaginationChanged,
      table,
      handleQuery,
      toEdit,
      toAdd,
      toDelete,
      FormStatusEnum,
    };
  },
};
</script>

<style></style>

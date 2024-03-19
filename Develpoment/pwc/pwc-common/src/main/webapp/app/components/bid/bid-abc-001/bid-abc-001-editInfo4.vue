<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <div>
          <i-button type="folder-plus"></i-button>
          <i-button type="save"></i-button>
          <i-button type="trash"></i-button>
        </div>

        <div>
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
  name: 'bidAbc001EditInfo4',
  props: {
    bidProjectKey: {
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectModifyKeyProp: Ref<any> = toRef(props, 'bidProjectKey');

    onMounted(() => {
      handleQuery();
    });

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const notificationService: NotificationService = useNotification();

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
      table.totalItems = 5;
      table.data.splice(0, table.data.length, ...mockdata);
    };

    const table = reactive({
      fields: [
        {
          key: 'a',
          label: '選擇',
          sortable: false,
          thClass: 'text-center',
          // tdClass: 'text-left align-middle',
        },
        {
          key: 'b',
          label: '優點',
          sortable: false,
          thClass: 'text-center',
          // tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 1,
    });

    const mockdata = [
      {
        a: '',
        b: '',
      },
      {
        a: '',
        b: '',
      },
      {
        a: '',
        b: '',
      },
      {
        a: '',
        b: '',
      },
      {
        a: '',
        b: '',
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
    };
  },
};
</script>

<style></style>

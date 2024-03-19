<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <div>
          <b-row>
            <b-col cols="4">
              <i-button size="md" type="folder-plus" @click="toAdd"></i-button>
            </b-col>
            <b-col cols="8">
              缺失扣點填報請參考:施工查核小組和品質缺失扣點記錄表弟22頁填表說明<u style="color: blue">填表說明下載</u></b-col
            >
          </b-row>
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
  name: 'bidAbc001EditInfo6',
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
      table.totalItems = 6;
      table.data.splice(0, table.data.length, ...mockdata);
    };

    const table = reactive({
      fields: [
        {
          key: 'a',
          label: '缺失編號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          key: 'b',
          label: '缺失內容',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'c',
          label: '等級',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'd',
          label: '扣點數',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'e',
          label: '記點對象',
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
        },
      ],
      data: undefined,
      totalItems: 1,
    });

    const mockdata = [
      {
        a: '1.02.03.04',
        b: '監造單位監造計畫材料設備送審管制總表預定送審日期及是否驗廠未填寫',
        c: '輕微',
        d: '',
        e: '(未填)',
      },
      {
        a: '4.01.04',
        b: '主辦機關雖辦理督導62次為未落實辦理現地部分施工工項(如陸面臨鋪及交維設施等)之抽查驗。',
        c: '輕微',
        d: '',
        e: '',
      },
      {
        a: '5.06.99',
        b: '承攬廠商剩餘土石方載運車輛請確認是否為合法砂石專用車。',
        c: '輕微',
        d: '',
        e: '',
      },
      {
        a: '5.09.08',
        b: '路邊小型工程告示牌內容為符合規定(如路證張貼覆蓋相關資訊等)。',
        c: '輕微',
        d: '',
        e: '',
      },
      {
        a: '5.14.12.03',
        b: '簡報中TV檢視照片部分人員未穿著反光背心。',
        c: '輕微',
        d: '',
        e: '',
      },
      {
        a: '6.01.05',
        b: '進度管制應以施工網圖為主，對於已影響要徑工項，未考慮展延或箱因應政策，如屋頂鋼構、涉及部分基地台未遷移影響施工部份等。',
        c: '輕微',
        d: '',
        e: '',
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
      navigateByNameAndParams('bidAbc001Edit6', {
        formStatus: FormStatusEnum.CREATE,
        bidProjectModifyKeyProp: props.bidProjectKeyProp,
        isNotKeepAlive: true,
      });
    };

    const toEdit = () => {
      reset();
      navigateByNameAndParams('bidAbc001Edit6', {
        formStatus: FormStatusEnum.MODIFY,
        bidProjectModifyKeyProp: props.bidProjectKeyProp,
        isNotKeepAlive: true,
      });
    };

    const toDelete = () => {
      reset();
      navigateByNameAndParams('bidAbc001Edit6', {
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
      toAdd,
      toEdit,
      toDelete,
      FormStatusEnum,
    };
  },
};
</script>

<style></style>

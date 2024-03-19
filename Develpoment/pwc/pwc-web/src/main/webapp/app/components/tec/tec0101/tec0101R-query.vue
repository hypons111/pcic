<template>
  <div>
    <section class="container mt-3">
      <b-table
        class="table-sm"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields"
        :totalItems="table.totalItems"
        :is-server-side-paging="true"
        @changePagination="handlePaginationChanged($event)"
        :title="'最新消息'"
      >
        <template #cell(action)="row">
          <i-button type="binoculars" @click="toEditView(row.item)"></i-button>
        </template>
      </b-table>
    </section>
    <section class="container mt-3">
      <div class="card">
        <div class="card-body">
          <b-col cols="9" class="text-right label"><a href="#">(規範...)</a><a href="#">(物價...)</a><a href="#">(PCCES...)</a></b-col>
          <tr>
            <label id="tec_claim">公共工程價格資料庫 特別聲明</label>
          </tr>
          <tr>
            <label>1. 由於公共工程種類繁多，各機關辦理其工程可能有其特殊需求，故本公共工程施工綱要規範尚非屬施工標準規範。</label>
          </tr>
          <tr>
            <label
              >2.
              於不違反政府採購法及符合個別工程特殊需求與確保工程品質之原則下，本施工綱要規範以開放方式，允許各機關按照本綱要規範編撰架構與編碼系統，就規範之內容予以增修或新編，作為發包契約文件之一。</label
            >
          </tr>
          <tr>
            <label
              >3.
              本文件所彙編之各項招標文件、契約要項及各章施工綱要規範，係供工程契約文件編訂之參考，各使用單位應依工程特性另行增修必要內容，不宜全份套用。</label
            >
          </tr>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { onBeforeMount, reactive, ref } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';
import axios from 'axios';
import { Tec_page } from '@/shared/model/tec_page.model';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';

export default {
  name: 'tec0101RQuery',
  props: {
    isReload: {
      required: true,
    },
  },
  setup(props, context) {
    onBeforeMount(() => {
      handleQuery();
    });

    const form = reactive(
      Object.assign(
        {},
        {
          pageId: '',
          pageStart: '',
          pageTitle: '',
          page_content_type: '',
          page_content: '',
        }
      )
    );

    const table = reactive({
      fields: [
        {
          key: 'pageId',
          label: $i18n.t('label.id'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'pageStart',
          label: $i18n.t('label.page_start'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: value => formatDate(new Date(value), '/'),
        },
        {
          key: 'pageTitle',
          label: $i18n.t('label.subjectText'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: $i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    const dataPromise = ref(null);

    const notificationService: NotificationService = useNotification();

    const handleQuery = () => {
      table.data = undefined;
      queryStatus.value = true;
      dataPromise.value = axios
        .post('/service/tec-pages/bulletin', form) //post("call後端拿到Obj",預先設好的Obj)
        .then(({ data }) => {
          table.data = [];
          table.data.splice(0, table.data.length, ...data);
          table.totalItems = data.totalItems;
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const $bvModal = useBvModal();

    const toEditView = (tec_page: Tec_page) => {
      console.log(tec_page);
      navigateByNameAndParams('tec0101R-detail', { tec_page: tec_page, isNotKeepAlive: true });
    };

    return {
      dataPromise,
      table,
      handleQuery,
      toEditView,
    };
  },
};
</script>
<style scoped>
#tec_claim {
  color: red;
}
</style>

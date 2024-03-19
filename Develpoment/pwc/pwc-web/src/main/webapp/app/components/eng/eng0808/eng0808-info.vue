<template>
  <div>
    <section class="container">
      <i-table
        :hideNo="true"
        small
        title="資料列表"
        ref="iTable"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields_backend"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >
        <template v-slot:cell(engrAmounts)="row">
          <b-link v-b-modal.view-detail> {{ row.item.engrAmounts }}</b-link>
        </template>
      </i-table>
      <br />
      <b-form-row>
        <b-col offset="5">
          <!-- 返回 -->
          <i-button type="arrow-left" @click="toLast" />
        </b-col>
      </b-form-row>
    </section>

    <b-modal id="view-detail" title="執照技師名單" header-bg-variant="secondary" header-text-variant="light">
      <div>翁忠川</div>
      <br />
      <div>陳俊安</div>
      <br />
      <div>林業旺</div>
      <br />
      <template #modal-footer="{ cancel }">
        <b-button @click="cancel()" variant="info">離開</b-button>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import i18n from '@/shared/i18n';
import NotificationService from '@/shared/notification/notification-service';
import EngReviewLogService from '@/components/eng/eng-review-log/eng-review-log.service';
import { onMounted, onActivated, watch, reactive, Ref, ref, toRef, inject } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'Eng0808Info',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup() {
    const iTable = ref(null);
    const isReload = ref(false); // 是否需要重新查詢

    const formDefault = {};

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {};

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault); // 雖無驗證但也不可刪

    const notificationService: NotificationService = useNotification();

    onMounted(() => {
      handleQuery();
    });

    function toLast() {
      navigateByNameAndParams('Eng0808Query', {
        isReload: isReload.value,
        isNotKeepAlive: true,
      });
      isReload.value = false;
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const handleQuery = () => {
      table.data = undefined;
      table.data = data;
      table.totalItems = data.length;
    };

    const table = reactive({
      fields_backend: [
        {
          // 科別
          key: 'subject',
          label: i18n.t('label.subject'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // (執業技師)人數
          key: 'engrAmounts',
          label: i18n.t('label.engrAmounts'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 七年以上實務經驗技師人數
          key: 'isMore7YearAmount',
          label: i18n.t('label.isMore7YearAmount'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => formOptionsFormatter(formOptions.connections, value),
        },
        {
          // 非營業範圍科別
          key: 'noSaleArea',
          label: i18n.t('label.noSaleArea'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const data = [
      {
        subject: '土木工程', // 土木工程
        engrAmounts: 3,
        isMore7YearAmount: 1,
        noSaleArea: '',
      },
    ];

    return {
      handlePaginationChanged,
      $v,
      table,
      iTable,
      toLast,
    };
  },
};
</script>
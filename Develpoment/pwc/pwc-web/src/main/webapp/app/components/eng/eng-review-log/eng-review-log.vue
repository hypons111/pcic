<template>
  <!-- <b-card header="案件審核" header-tag="h4"> -->
  <b-card title="案件審核" title-tag="h5">
    <hr />
    <div class="pl-4 pr-4">
      <b-table
        small
        :items="table.data"
        :fields="table.fields_backend"
        class="mb-4"
      />
      <b-form-textarea
        v-model.trim="reviewDesc"
        rows="4"
        placeholder="請輸入審核說明"
      ></b-form-textarea>
      <slot name="page-end" />
    </div>
    <template v-for="(_, slot) of $scopedSlots" v-slot:[slot]="scope">
      <slot :name="slot" v-bind="scope" />
    </template>
  </b-card>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import axios from 'axios';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { onActivated, onMounted, reactive, ref, watch } from '@vue/composition-api';
import { RoleEnum, FormStatusEnum } from '@/shared/enum';
import { EngReviewLog } from '@/shared/model/eng/eng-review-log.model'
import { formOptionsFormatter } from '@/shared/formatter/common';

export default {
  name: 'EngReviewLog',
  props: {
    id: {
      type: String,
      required: false,
    },
    version: {
      type: Number,
      required: false,
    },
    type: {
      type: String,
      required: false,
    },
    formStatus: {
      required: false,
    },
    role: {
      required: false,
    },
  },
  setup(props) {
    const notificationService = useNotification();
    const reviewDesc = ref(null);

    const formOptions = {
      reviewStatus: [
        { value: '1', text: '申請' },
        { value: '2', text: '重新申請' },
        { value: '3', text: '核准' },
        { value: '4', text: '核退' },
        { value: '5', text: '取消核准' },
        { value: '6', text: '作廢' },
      ]
    }

    const table = reactive({
      fields_backend: [
        {
          // 序號
          key: 'itemNo',
          label: i18n.t('label.no'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '10%' },
        },
        {
          // 審核狀態
          key: 'reviewStatus',
          label: i18n.t('label.reviewStatus'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '15%' },
          formatter: (value: string) => formOptionsFormatter(formOptions.reviewStatus, value),
        },
        {
          // 審核人員
          key: 'reviewStaff',
          label: i18n.t('label.reviewStaff'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '15%' },
        },
        {
          // 審核時間
          key: 'reviewTime',
          label: i18n.t('label.reviewTime'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '20%' },
          formatter: (value: Date) => value.toLocaleString(),
        },
        {
          // 審核說明
          key: 'reviewDesc',
          label: i18n.t('label.reviewDesc'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '40%' },
          // formatter: (value: string) => queryOptionsFormatter('subject', value),
        },
      ],
      data: undefined,
      totalItems: undefined
    });

    let form = reactive(new EngReviewLog());

    watch(() => props.id, () => {
      // console.log("id：", props.id);
      // console.log("version：", props.version);
      query();
    })

    // onActivated(() => {
    //   query();
    // })

    function query() {
      form.externalId = props.id as string;
      form.externalVersion = props.version as number;
      form.reviewCaseType = props.type as string;

      axios
        .post('/eng-review-logs/criteria-jpa', form)
        .then(({ data }) => {
          table.data = data.slice(0, data.length);
          table.totalItems = data.length;
        })
        .catch(notificationErrorHandler(notificationService));
    }

    return {
      FormStatusEnum,
      RoleEnum,
      table,
      reviewDesc,
      query
    };
  },
};
</script>

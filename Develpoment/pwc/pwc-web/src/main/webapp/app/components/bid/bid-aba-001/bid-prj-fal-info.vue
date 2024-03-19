<template>
  <div>
    <b-table-simple bordered>
      <b-tbody>
        <b-tr>
          <b-td style="width: 25%">資料新增日期</b-td>
          <b-td style="width: 25%">{{ formatDateTime(bidPrjFal.createDate) }}</b-td>
          <b-td style="width: 25%">上次更新日期</b-td>
          <b-td style="width: 25%">{{ formatDateTime(bidPrjFal.updateDate) }}</b-td>
        </b-tr>
        <b-tr>
          <b-td style="width: 25%">更新者帳號</b-td>
          <b-td colspan="3">{{ bidPrjFal.updateUser }}</b-td>
        </b-tr>
        <b-tr>
          <b-td style="width: 25%">項目</b-td>
          <b-td style="width: 25%">{{ bidPrjFal.mft }}</b-td>
          <b-td style="width: 25%">改進完成日期</b-td>
          <b-td style="width: 25%">{{ bidPrjFal.okDate }}</b-td>
        </b-tr>
        <b-tr>
          <b-td style="width: 25%">原因分析</b-td>
          <b-td colspan="3">{{ bidPrjFal.mfas }}</b-td>
        </b-tr>
        <b-tr>
          <b-td style="width: 25%">責任歸屬</b-td>
          <b-td colspan="3">{{ bidPrjFalFormatter.falReasonConverter(bidPrjFal.respons) }}</b-td>
        </b-tr>
        <b-tr>
          <b-td style="width: 25%">處理方式</b-td>
          <b-td colspan="3">{{ bidPrjFalFormatter.falMetherConverter(bidPrjFal.mether) }}</b-td>
        </b-tr>
        <b-tr>
          <b-td style="width: 25%">解決辦法</b-td>
          <b-td colspan="3">{{ bidPrjFal.mfrt }}</b-td>
        </b-tr>
      </b-tbody>
    </b-table-simple>
  </div>
</template>

<script lang="ts">
import { Ref, toRef } from '@vue/composition-api';
import { formatDateTime } from '@/shared/date/minguo-calendar-utils';
import BidPrjFalFormatter from '@/components/bid/bidService/bid-prj-fal.formatter';
export default {
  name: 'bidPrjFalInfo',
  props: {
    bidPrjFalInfo: {
      type: Object,
      required: true,
    },
  },
  setup(props, context) {
    const bidPrjFal: Ref<any> = toRef(props, 'bidPrjFalInfo');
    let bidPrjFalFormatter = new BidPrjFalFormatter();

    return {
      bidPrjFal,
      bidPrjFalFormatter,
      formatDateTime,
    };
  },
};
</script>

<style></style>

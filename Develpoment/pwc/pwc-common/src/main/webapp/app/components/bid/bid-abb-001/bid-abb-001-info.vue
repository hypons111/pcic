<template>
  <div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">詳細資料</h5>
      </div>
      <div class="card-body col-11">
        <!-- 查核方式 -->
        <b-row>
          <i-form-group-check :labelStar="true" :label="'查核方式：'">
            {{ bidGovPrecheck.method }}
          </i-form-group-check>
        </b-row>
        <!-- 預定查核日期 -->
        <b-row>
          <i-form-group-check :labelStar="true" :label="'預定查核日期：'">
            {{ bidGovPrecheck.checkDate }}至{{ bidGovPrecheck.checkDateEnd }}
          </i-form-group-check>
        </b-row>
        <!-- 查核小組 -->
        <b-row>
          <i-form-group-check :label="'查核小組：'">
            {{ bidGovPrecheck.wkut }}
          </i-form-group-check>
        </b-row>
        <!-- 查核主題 -->
        <b-row>
          <i-form-group-check :label="'查核主題：'">
            {{ bidGovPrecheck.checkPurp }}
          </i-form-group-check>
        </b-row>
        <b-row>
          <!-- 主辦人員 連絡電話 -->
          <i-form-group-check :label="'連絡電話：'">
            {{ bidGovPrecheck.checkerTel }}
          </i-form-group-check>

          <i-form-group-check :label="'主辦人員：'">
            {{ bidGovPrecheck.checker1 }}
          </i-form-group-check>
        </b-row>
        <!-- 查核領隊 -->
        <b-row>
          <i-form-group-check :label="'查核領隊：'">
            {{ bidGovPrecheck.checkLeader }}
          </i-form-group-check>
        </b-row>
        <!-- 查核委員 -->
        <b-row>
          <i-form-group-check :label="'查核委員：'" class="col-12" :label-cols="2" :content-cols="4">
            {{ bidGovPrecheck.checkMember }}
          </i-form-group-check>
        </b-row>

        <!-- 功能按鈕 -->
        <!-- <b-row class="justify-content-center">
          <i-button type="x-lg" @click="deleteGovPreCheck"> </i-button>
        </b-row> -->
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, computed } from '@vue/composition-api';
import { IBidGovPrecheck, BidGovPrecheck } from '@/shared/model/bidModel/bid-gov-precheck.model';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import BidGovPrecheckService from '@/components/bid/bidService/bid-gov-precheck.service';
import { navigateByNameAndParams } from '@/router/router';
import { notificationErrorHandlerWithErrorParam } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';

export default {
  name: 'bidAbb001Info',
  props: {
    bidGovPreCheckKey: {
      required: false,
    },
  },
  setup(props, context) {
    const bbidGovPreCheckKeyProp: Ref<any> = toRef(props, 'bidGovPreCheckKey');
    let bidSysCodeTable = new BidSysCodeTable();
    let formDefault: IBidGovPrecheck = new BidGovPrecheck();
    const notificationService: NotificationService = useNotification();
    const bidGovPrecheckService = new BidGovPrecheckService();
    const bidGovPrecheck = reactive(new BidGovPrecheck());

    //處理傳進來的日期 從string轉成date
    function handleOriginModifyData(govPreCheck: IBidGovPrecheck) {
      govPreCheck.checkDate = new Date(govPreCheck.checkDate);
      govPreCheck.checkDateEnd = govPreCheck.checkDateEnd ? new Date(govPreCheck.checkDateEnd) : null;
    }

    watch(
      bbidGovPreCheckKeyProp,
      newValue => {
        let bidGovPrecheckKey = newValue;
        bidGovPrecheckService.getOneBidGovPrecheck(bidGovPrecheckKey).then(govPreCheckData => {
          //handleOriginModifyData(govPreCheckData);
          Object.assign(bidGovPrecheck, govPreCheckData);
        });
      },
      { immediate: true }
    );

    return {
      bidGovPrecheck,
    };
  },
};
</script>

<style>
.h-38p {
  height: 38px;
}
</style>

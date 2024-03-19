<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <!-- 工期類別 -->
        <b-form-row>
          <i-form-group-check :label="'工期類別：'" :item="$v.wKind" :labelStar="true">
            <b-form-select v-model="$v.wKind.$model" :options="wKindOpts"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 總天數 -->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'總天數：'" :item="$v.wDays">
            <div class="d-inline">
              <b-form-input class="col-12" v-model="$v.wDays.$model" :state="validateState($v.wDays)"></b-form-input>
            </div>
            <div class="col-8">
              <div class="d-inline">天</div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 工期說明-->
        <b-form-row>
          <i-form-group-check :label="'工期說明：'" :item="$v.wRemk">
            <b-form-textarea rows="3" max-rows="6" v-model="$v.wRemk.$model" :state="validateState($v.wRemk)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 工程總預算 -->
        <b-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'工程總預算：'" :item="$v.totAmt" :labelStar="true">
            <div class="d-inline">
              <b-form-input class="col-12" v-model="$v.totAmt.$model" :state="validateState($v.totAmt)"></b-form-input>
            </div>
            <div class="col-8">
              <div class="d-inline">千元</div>
              <div class="d-inline">(1)=(2)+(3)+(4)+(5)+(6)+(7)</div>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 本案發包預算 -->
        <b-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'本案發包預算：'" :item="$v.bdgt1" :labelStar="true">
            <div class="d-inline">
              <b-form-input class="col-12" v-model="$v.bdgt1.$model" :state="validateState($v.bdgt1)"></b-form-input>
            </div>
            <div class="col-8">
              <div class="d-inline">千元</div>
              <div class="d-inline">(2)只依採購法施行細則第6條所列之採購金額。</div>
            </div>
            <div>(決標公告填列採購金額:{{ $v.bdgt1_0.$model }}千元)</div>
          </i-form-group-check>
        </b-row>
        <!-- 供給材料費 -->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'供給材料費：'" :item="$v.igtct">
            <div class="d-inline">
              <b-form-input class="col-12" v-model="$v.igtct.$model" :state="validateState($v.igtct)"></b-form-input>
            </div>
            <div class="col-8">
              <div class="d-inline">千元</div>
              <div class="d-inline">(3)</div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 用地取得及拆遷補償費 -->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'用地取得及拆遷補償費：'" :item="$v.othct">
            <div class="d-inline">
              <b-form-input class="col-12" v-model="$v.othct.$model" :state="validateState($v.othct)"></b-form-input>
            </div>
            <div class="col-8">
              <div class="d-inline">千元</div>
              <div class="d-inline">(4)</div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 工程管理費 -->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'工程管理費：'" :item="$v.rsvct">
            <div class="d-inline">
              <b-form-input class="col-12" v-model="$v.rsvct.$model" :state="validateState($v.rsvct)"></b-form-input>
            </div>
            <div class="col-8">
              <div class="d-inline">千元</div>
              <div class="d-inline">(5)</div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 空汙費 -->
        <b-form-row>
          <i-form-group-check class="col-sm-6" label-cols-md="4" content-cols-md="8" :label="'空污費：'" :item="$v.poll">
            <div class="d-inline">
              <b-form-input class="col-12" v-model="$v.poll.$model" :state="validateState($v.poll)"></b-form-input>
            </div>
            <div class="col-4">
              <div class="d-inline">千元</div>
              <div class="d-inline">(6)</div>
            </div>
          </i-form-group-check>
          <i-form-group-check class="col-sm-6" label-cols-md="4" content-cols-md="8" :label="'空污徵收管制編號：'" :item="$v.pollNo">
            <b-form-input class="col-8" v-model="$v.pollNo.$model" :state="validateState($v.pollNo)"></b-form-input>
          </i-form-group-check>
        </b-form-row>
        <!-- 其他費用 -->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'其他費用：'" :item="$v.othMon">
            <div class="d-inline">
              <b-form-input class="col-12" v-model="$v.othMon.$model" :state="validateState($v.othMon)"></b-form-input>
            </div>
            <div class="col-8">
              <div class="d-inline">千元</div>
              <div class="d-inline">(7)</div>
            </div>
          </i-form-group-check>
        </b-form-row>
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
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { Ref, ref, toRef, reactive, watch, computed, Reactive } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';

export default {
  name: 'bidAaa002EditInfo2',
  props: {
    bidProjectInfo: {
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectInfoProp: Ref<any> = toRef(props, 'bidProjectInfo');

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const notificationService: NotificationService = useNotification();
    const bidSysCodeTable = new BidSysCodeTable();

    const wKindOpts = reactive([]);

    //去後端撈選項
    bidSysCodeTable.getBidSysCodeWithParam('BID_005').then(returnArray => {
      wKindOpts.splice(0, wKindOpts.length);
      if (returnArray && returnArray.length > 0) {
        returnArray.forEach(ele => {
          const item = { value: '', text: '' };
          item.value = ele.dataKey;
          item.text = ele.value;
          wKindOpts.push(item);
        });
      }
    });

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formDefault = {
      wKind: '', //工期類別
      wDays: '', //總天數
      wRemk: '', //工期說明
      totAmt: '', //工程總預算
      bdgt1: '', //發包預算
      bdgt1_0: '', //預算金額
      igtct: '', //供給材料費
      othct: '', //用地取得及拆遷補償費
      rsvct: '', //工程管理費
      poll: '', //空汙費
      pollNo: '', //空污徵收管制編號
      othMon: '', //其他費用
    };
    let formEmpty = Object.assign({}, formDefault);
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      wKind: { required: required },
      wDays: {},
      wRemk: {},
      totAmt: { required: required },
      bdgt1: { required: required },
      bdgt1_0: {},
      igtct: {},
      othct: {},
      rsvct: {},
      poll: {},
      pollNo: {},
      othMon: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAaa002Query');
    }

    function putForm() {
      return form;
    }

    watch(() => props.bidProjectInfo, (newVal: object) => {
      Object.assign(formDefault, formEmpty)
      for(let key in formDefault){
        if(newVal[key]) formDefault[key] = newVal[key];
      }
      reset();
    },{immediate: true, deep: true});

    return {
      $v,
      checkValidity,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      formDefault,
      stepVisible,
      wKindOpts,
      putForm,
    };
  },
};
</script>

<style></style>

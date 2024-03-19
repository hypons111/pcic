<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <!-- 領隊-->
        <b-form-row>
          <i-form-group-check :label="'領隊'" :item="$v.chkLead">
            <b-form-textarea rows="3" max-rows="6" v-model="$v.chkLead.$model" :state="validateState($v.chkLead)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 工作人員-->
        <b-form-row>
          <i-form-group-check :label="'工作人員'" :item="$v.checker">
            <b-form-textarea rows="3" max-rows="6" v-model="$v.checker.$model" :state="validateState($v.checker)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 主管、主辦機關人員 -->
        <b-form-row>
          <i-form-group-check :label="'主管、主辦機關人員'" :item="$v.unChker">
            <b-form-textarea rows="3" max-rows="6" v-model="$v.unChker.$model" :state="validateState($v.unChker)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 監造設計承造單位人員-->
        <b-form-row>
          <i-form-group-check :label="'監造設計承造單位人員'" :item="$v.coChker">
            <b-form-textarea rows="3" max-rows="6" v-model="$v.coChker.$model" :state="validateState($v.coChker)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>

        <div class="text-center mt-2">
          <i-button type="save"></i-button>
          <i-button type="arrow-counterclockwise" @click="backToPreviousPage"></i-button>
          <i-button type="x-circle"></i-button>
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
import { Ref, ref, toRef, reactive, watch, computed, Reactive } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';

export default {
  name: 'bidAbc001EditInfo3',
  props: {
    bidProjectKey: {
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectModifyKeyProp: Ref<any> = toRef(props, 'bidProjectKey');

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formDefault = {
      chkLead: '', //領隊
      checker: '', //工作人員(參予人員)
      unChker: '', //主管、主辦機關人員
      coChker: '', //監造設計承造單位人員
    };

    const rules = {
      chkLead: { required: required },
      checker: { required: required },
      unChker: { required: required }, //專案項目
      coChker: { required: required }, //列管狀態
    };

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
    };
  },
};
</script>

<style></style>

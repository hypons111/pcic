<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <!-- 查核日期 -->
        <b-form-row>
          <i-form-group-check :label="'查核日期:'" :item="$v.checkDate">
            <b-form-row>
              <i-date-picker v-model="$v.checkDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
            <div style="color: blue">(查核內容依工程施工查核小組作業辦法辦理)</div>
          </i-form-group-check>
        </b-form-row>
        <!-- 型態 -->
        <b-form-row>
          <i-form-group-check :label="'型態:'" :item="$v.roveChk5">
            <div>
              <b-form-radio-group v-model="$v.roveChk5.$model">
                <b-form-radio value="A">查核</b-form-radio>
                <b-form-radio value="B">複查</b-form-radio>
                <b-form-radio value="C">督導</b-form-radio>
              </b-form-radio-group>
              <div class="col-12" style="color: blue">
                <b-col sm>查核系指依政府採購法(第七十條)規定辦理之事項。</b-col>
                <b-col sm>複查系指查核小組當年度對同一標案進行第二次查核者，跨年度則重新計算。</b-col>
                <b-col sm>督導系指依公共工程施工品質管理作業要點第十五點規定辦理之事項</b-col>
              </div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 方式 -->
        <b-form-row>
          <i-form-group-check :label="'方式:'" :item="$v.roveChk5a">
            <div>
              <b-form-radio-group v-model="$v.roveChk5a.$model">
                <b-form-radio value="A">查核</b-form-radio>
                <b-form-radio value="B">複查</b-form-radio>
                <b-form-radio value="C">督導</b-form-radio>
              </b-form-radio-group>
              <div class="col-12" style="color: red">
                <b-col sm cols="8">依工程施工查核小組作業辦法第五條規定，得不預先通知赴工地進行查核。</b-col>
                <b-col sm>94.9.26工程館自第09400350900號涵工程施工查核小組不預先通知查核作業補充規定詳細。 </b-col>
              </div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 查核小組 -->
        <b-form-row>
          <i-form-group-check :label="'查核小組'" :item="$v.checkUnit">
            <b-form-select v-model="$v.checkUnit.$model"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 工程進度、經費支用及目前施工概況-->
        <b-form-row>
          <i-form-group-check :label="'工程進度、經費支用及目前施工概況'" :item="$v.detel0">
            <b-form-textarea rows="3" max-rows="6" v-model="$v.detel0.$model" :state="validateState($v.detel0)"></b-form-textarea>
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
  name: 'bidAbc001EditInfo1',
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
      checkDate: '', // 查核日期
      roveChk5: '', //型態(類別)
      roveChk5a: '', //方式
      checkUnit: '', //查核小組(主辦單位)
      detel0: '', //工程進度、經費支用及目前施工概況
    };

    const rules = {
      checkDate: { required: required },
      roveChk5: { required: required },
      roveChk5a: { required: required },
      checkUnit: {},
      detel0: {},
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

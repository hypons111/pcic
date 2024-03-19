<template>
  <div class="container">
    <div class="card">
      <div class="card-body col-12">
        <b-form-row class="mt-3">
          <i-form-group-check class="col-12" :label="$t('label.reviewApprovedAmount')" :item="$v.reviewApprovedAmount">
            <b-form-input> </b-form-input>
          </i-form-group-check>
        </b-form-row>
        <b-form-row class="mt-3">
          <i-form-group-check class="col-12" :label="$t('label.reviewApprovedDeadline')" :item="$v.reviewApprovedDeadline">
            <b-form-input> </b-form-input>
          </i-form-group-check>
        </b-form-row>
        <b-form-row class="mt-3">
          <i-form-group-check class="col-12" :label="$t('label.replyDate')" :item="$v.replyDate">
            <b-form-input value="2021.08.23" readonly> </b-form-input>
          </i-form-group-check>
        </b-form-row>
        <div class="button-float-right">
          <b-button-toolbar>
            <i-button class="mt-1 ml-1" type="save" @click="toEditInfo"></i-button>
            <i-button class="mt-1 ml-1" type="arrow-counterclockwise" @click="toEditInfo"></i-button>
          </b-button-toolbar>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, reactive, Ref, ref, watch, toRefs, onActivated, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useGetters } from '@u3u/vue-hooks';

export default {
  name: 'rec0411-review',
  props: {

  },
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    // 空值表單
    const formDefault = {
      reviewApprovedAmount: '',
      reviewApprovedDeadline: '',
      replyDate: '',
    };

    // 預存好的form
    const form = reactive(Object.assign({}, formDefault));

    // 檢核規則
    const rules = {
      handlingStatus: { notNull: required },
      extensionCondition: { notNull: required },
    };



    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //是否要重新查詢
    const isReload: boolean = ref(null);
    //modal物件
    const $bvModal = useBvModal();

    //轉到復建執行管控檢視案件畫面
    const toEditInfo = () => {
      navigateByNameAndParams('rec0403-edit-info', { isNotKeepAlive: true });
    };

    return {
      $v,
      formStatusEnum,
      formStatusRef,
      reset,
      validateState,
      toEditInfo,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          經費調整概要
        </h5>
      </div>
      <div class="card-body col-12">
        <b-row align-h="center">
          <b-col cols="11">
            <a href="https://law.moj.gov.tw/LawClass/LawSingle.aspx?pcode=T0020059&flno=15" target="連結目標" title="中央對各級地方政府重大天然災害救災經費處理辦法(第15條)">中央對各級地方政府重大天然災害救災經費處理辦法(第15條)</a>
            <table width="40%">
              <tr>
                <td width="40%">目前核定經費</td>
                <td width="40%"><b-form-input v-model="$v.originalAmount.$model" disabled=ture></b-form-input></td>
                <td width="20%">（千元）</td>
              </tr>
              <tr>
                <td>調整經費</td>
                <td><b-form-input v-model="$v.requestAmount.$model" :state="validateState($v.requestAmount)" disabled=true></b-form-input></td>
                <td>（千元）</td>
              </tr>
            </table>
            <table width="80%">
              <tr>
                <td width="20%">調整經費說明</td>
                <td><b-form-textarea v-model="$v.requestReason.$model" rows="4" maxlength="4000" placeholder="調整經費說明" disabled=true></b-form-textarea></td>
              </tr>
            </table>
          </b-col>
        </b-row>
      </div>

      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          審核意見
        </h5>
      </div>
      <div class="card-body col-12">
        <b-row align-h="center">
          <b-col cols="11">
            <table width="40%">
              <tr>
                <td width="40%">核可調整經費</td>
                <td width="40%"><b-form-input v-model="$v.approveAmount.$model" disabled=true></b-form-input></td>
                <td width="20%">（千元）</td>
              </tr>
            </table>
            <table width="80%">
              <tr>
                <td width="20%">意見</td>
                <td><b-form-textarea v-model="$v.approveOpinion.$model" rows="4" maxlength="4000" placeholder="審核意見" disabled=true></b-form-textarea></td>
              </tr>
            </table>
          </b-col>
        </b-row>
        <div class="text-center mt-4">
          <b-button variant="info" @click="toEditInfo">確認</b-button>
        </div>
      </div>



    </div>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs, onActivated, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import { navigateByNameAndParams } from '@/router/router';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { formatDateInChinese } from '@/shared/date/minguo-calendar-utils';
import { commaFormatter } from '@/shared/formatter/common';

export default {
  name: 'rec0406-view-cost',
  props: {
    formStatusForward: {
      type: String,
      required: true,
    },
    recProject: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    //傳入狀態
    const formStatusForwardProp = toRefs(props).formStatusForward;

    const recProjectProp = toRefs(props).recProject;

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    //空值表單
    const formDefault = {
      originalAmount:1452,
      requestAmount:'1500',
      requestReason:'材料物價上漲，經費不足',
      approveAmount:'1500',
      approveOpinion:'無意見',
      createUser: '',
      createTime: undefined,
      updateUser: '',
      updateTime: undefined,
    };

    //預存好的form
    const form = reactive(Object.assign({}, formDefault));

    //檢核規則
    const rules = {
      originalAmount:{},
      requestAmount:{notNull: required},
      requestReason:{notNull: required},
      approveAmount:{},
      approveOpinion:{},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //是否要重新查詢
    const isReload: boolean = ref(null);
    //modal物件
    const $bvModal = useBvModal();

    //返回查詢畫面
    const toQueryView = () => {
      const param = { isReload: isReload.value };
      handleBack(param);
    };

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
      toQueryView,
      formStatusForwardProp,
      recProjectProp,
      formatDateInChinese,
      toEditInfo,
    };
  },
  filters: {
    commaFilter: (value: any) => commaFormatter(value),
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

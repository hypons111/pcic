<template>
  <div>
    <section class="container">
      <!-- 學歷 -->
      <b-card no-body>
        <b-card-header class="py-1">
          <b>統計數據</b>
        </b-card-header>
        <b-card-body class="py-2">
          <b-form-row>
            <!-- 會員總數 -->
            <i-form-group-check :label="$t('label.memberCount') + '：'" :item="$v.memberCount">
              <!-- <b-form-input v-model="$v.memberCount.$model" :state="validateState($v.memberCount)"></b-form-input> -->
              {{ $v.memberCount.$model }}
            </i-form-group-check>
            <!-- 機構申請數 -->
            <i-form-group-check :label="$t('label.companyCount') + '：'" :item="$v.companyCount">
              <!-- <b-form-input v-model="$v.companyCount.$model" :state="validateState($v.companyCount)"></b-form-input> -->
              {{ $v.companyCount.$model }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 職缺總數 -->
            <i-form-group-check :label="$t('label.jobCount') + '：'" :item="$v.jobCount">
              <!-- <b-form-input v-model="$v.jobCount.$model" :state="validateState($v.jobCount)"></b-form-input> -->
              {{ $v.jobCount.$model }}
            </i-form-group-check>
          </b-form-row>
        </b-card-body>
      </b-card>
      <b-card no-body>
        <b-card-header class="py-1">
          <b>代辦事項</b>
        </b-card-header>
        <b-card-body class="py-2">
          <b-form-row>
            <!--  Q&A待回復 -->
            <i-form-group-check :label="$t('label.qaReview') + '：'" :item="$v.qaReview">
              <!-- <i-date-picker v-model="$v.qaReview.$model" :state="validateState($v.qaReview)"></i-date-picker> -->
              {{ $v.qaReview.$model }}件 &emsp;
              <b-link to="geo0402">Q&A管理</b-link>
            </i-form-group-check>
            <!-- 機構審核待審核 -->
            <i-form-group-check :label="$t('label.companyReview') + '：'" :item="$v.companyReview">
              <!-- <b-form-input v-model="$v.companyReview.$model" :state="validateState($v.companyReview)"></b-form-input> -->
              {{ $v.companyReview.$model }}件 &emsp;
              <b-link to="">後臺帳號管理</b-link>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 工作職缺待審核 -->
            <i-form-group-check :label="$t('label.jobReview') + '：'" :item="$v.jobReview">
              <!-- <i-date-picker v-model="$v.jobReview.$model" :state="validateState($v.jobReview)"></i-date-picker> -->
              {{ $v.jobReview.$model }}件 &emsp;
              <b-link to="geo0603">工作職缺管理</b-link>
            </i-form-group-check>
          </b-form-row>
        </b-card-body>
      </b-card>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { onMounted, reactive, Ref, ref, toRef, toRefs, watch, defineComponent, unref } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useBvModal } from '@/shared/modal';
import axios from 'axios';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack, navigateByNameAndParams } from '@/router/router';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { FormStatusEnum, RoleEnum } from '@/shared/enum';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { useStore } from '@u3u/vue-hooks';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { GeoMember } from '@/shared/model/geoModel/geo-member.model';
import { formOptionsFormatter } from '@/shared/formatter/common';

interface propsType {
  geoMember: GeoMember;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'geo0601Read',
  props: {
    role: {
      required: false,
    },
    criteria: {
      required: false,
      type: Object,
    },
  },
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      MODIFY = 'modify',
    }
    const admSysCodeService = new AdmSysCodeService();
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);
    const isCheckRequired = ref(false); // 儲存 = false; 送出 = true
    const isSamePermanentAddr = ref(false); // 同戶籍地址checkbox

    // const { geoMember, role, criteria } = toRefs(props);

    // const role = ref(props.role).value;

    //從使用者撈資料
    const store = useStore();
    const engAccount = {
      userId: store.value.getters.account.id,
      idno: store.value.getters.account.idn,
    };
    // console.log(engAccount.idno);
    const cityOptions = ref(undefined);

    onMounted(() => {
      handleQuery();
    });

    //針對form的表示，之後要轉成後端接收的data Type
    let formDefault = {
      memberCount: '',
      companyCount: '',
      jobCount: '',
      qaReview: '',
      companyReview: '',
      jobReview: '',
    };

    let form = reactive(Object.assign({}, formDefault));
    const rules = {
      memberCount: {},
      companyCount: {},
      jobCount: {},
      qaReview: {},
      companyReview: {},
      jobReview: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);

    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        dataPromise.value = axios
          .post('/geo-0601')
          .then(({ data }) => {
            console.log(data);
            Object.assign(formDefault, data);
            reset();
          })
          .finally(() => (dataPromise.value = null))
          .catch(notificationErrorHandler(notificationService));
      });
    };

    return {
      formStatusEnum,
      RoleEnum,
      formStatusRef,
      formOptionsFormatter,
      dataPromise,
      $v,
      form,
      validateState,
      reset,
    };
  },
});
</script>
<style></style>

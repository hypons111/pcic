<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <!-- <font-awesome-icon icon="project-diagram"></font-awesome-icon> -->
            Q&A
          </h5>
        </div>
        <div class="card-body">
          <b-form-row class="align-items-center mt-2">
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="9"
              :label="$t('label.question') + '：'"
              :item="$v.question"
            >
              <b-form-textarea cols="50" rows="5" v-model.trim="$v.question.$model" :state="validateState($v.question)"></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <div class="d-flex justify-content-center">
            <b-button-toolbar class="mt-5">
              <i-button class="ml-1" type="save" @click="submitForm">儲存</i-button>
              <i-button class="ml-1" type="x-circle" @click="reset">清除</i-button>
              <i-button class="ml-1" type="arrow-left" @click="toQueryView">返回</i-button>
            </b-button-toolbar>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, Ref, ref, watch, toRefs, toRef, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, maxLength } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { GeoQuestion, IGeoQuestion } from '@/shared/model/geoModel/geo-question.model';
import { handleBack, navigateByNameAndParams } from '@/router/router';

export default {
  name: 'geo0401c1',
  props: {
    GeoQuestion: {
      required: false,
    },
    formStatus: {
      required: false,
    },
  },
  setup(props, context) {
    //從使用者撈資料
    const store = context.root.$store;
    const account = {
      userId: store.getters.account.id, //帳號
      userType: store.getters.account.userType,
      compIdno: store.getters.account.compIdno, //統編
      idn: store.getters.account.idn, //身分證字號
      role: null,
    };
    account.role = account.idn && !account.compIdno ? 1 : account.compIdno && !account.idn ? 2 : null;

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      // userId: '',
      geoQuestionId: '',
      roleId: '',
      question: '',
      createUser: '',
      updateUser: '',
    };

    // 建立表單物件 ref
    let form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      question: { notNull: required, maxLength: maxLength(500, '文字長度不得超過500個字!') },
      // userId: {},
      geoQuestionId: {},
      roleId: {},
      createUser: {},
      updateUser: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);
    // 是否需要重新查詢
    const isReload = ref(false);
    // modal物件
    const $bvModal = useBvModal();

    const toQueryView = () => {
      handleBack({ isReload: isReload.value });
      isReload.value = false;
    };

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          putForm();
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const putForm = () => {
      let url: string;
      url = `/geo-questions`;
      form.roleId = account.role;
      form.geoQuestionId = account.userId;
      form.createUser = account.userId;
      form.updateUser = account.userId;
      dataPromise.value = axios
        .post(url, form)
        .then(({ data }) => {
          createDefaultValue(data);
          isReload.value = true;
          notificationService.success(`儲存成功`);
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const createDefaultValue = (data: any) => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
    };

    const handleGeoMember = () => {
      dataPromise.value = axios
        .post('/geo-questions/criteria-geomember', account)
        .then(({ data }) => {
          // form.geoQuestionId = data[0].geoMemberId;
          // form.createUser = data[0].geoMemberId;
          isReload.value = true;
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    onMounted(() => {
      handleGeoMember();
    });

    return {
      $v,
      dataPromise,
      reset,
      validateState,
      checkValidity,
      toQueryView,
      submitForm,
    };
  },
};
</script>
<style>
.red-text {
  color: red;
  font-weight: bold;
}
</style>

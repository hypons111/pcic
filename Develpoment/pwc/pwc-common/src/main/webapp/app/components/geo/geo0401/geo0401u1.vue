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
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, toRefs, watch } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email, maxLength } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { navigateByNameAndParams, handleBack } from '@/router/router';
import { GeoQuestion } from '@/shared/model/geoModel/geo-question.model';
import { useStore } from '@u3u/vue-hooks';

export default {
  name: 'geo0401u1',
  props: {
    geoQuestion: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const geoQuestionProp = toRefs(props).geoQuestion;

    //從使用者撈資料
    const store = useStore();
    const engAccount = {
      idno: store.value.getters.account.idn,
      id: store.value.getters.account.id,
    };

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      id: '',
      question: '',
      updateUser: '',
      updateTime: null,
    };

    // 建立表單物件 ref
    let form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      id: {},
      question: { notNull: required, maxLength: maxLength(500, '文字長度不得超過500個字!') },
      updateUser: {},
      updateTime: {},
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
      const param = { isReload: isReload.value };
      handleBack(param);
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
      url = `/geo-questions/${form.id}`;
      form.updateUser = engAccount.id;
      form.updateTime = new Date();
      dataPromise.value = axios
        .put(url, form)
        .then(({ data }) => {
          createDefaultValue(data);
          isReload.value = true;
          notificationService.success(`儲存成功`);
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const createDefaultValue = (data: GeoQuestion) => {
      Object.assign(formDefault, data);
      reset();
    };

    watch(
      geoQuestionProp,
      () => {
        if (geoQuestionProp.value) {
          createDefaultValue(geoQuestionProp.value);
        }
      },
      { immediate: true }
    );

    return {
      $v,
      dataPromise,
      reset,
      validateState,
      toQueryView,
      submitForm,
    };
  },
};
</script>
<style></style>

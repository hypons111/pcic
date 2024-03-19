<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <!-- <font-awesome-icon icon="project-diagram"></font-awesome-icon> -->
            備註設定
          </h5>
        </div>
        <div class="card-body">
          <b-form-row class="align-items-center mt-2">
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="9"
              :label="$t('label.memberRemark') + '：'"
              :item="$v.memberRemark"
            >
              <b-form-textarea
                cols="50"
                rows="5"
                v-model.trim="$v.memberRemark.$model"
                :state="validateState($v.memberRemark)"
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
          <b-form-row class="align-items-center mt-2">
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="9"
              :label="$t('label.companyRemark') + '：'"
              :item="$v.companyRemark"
            >
              <b-form-textarea
                cols="50"
                rows="5"
                v-model.trim="$v.companyRemark.$model"
                :state="validateState($v.companyRemark)"
              ></b-form-textarea>
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
import { onMounted, reactive, Ref, ref, toRefs, watch } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email, maxLength } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { navigateByNameAndParams, handleBack } from '@/router/router';
import { GeoQuestion } from '@/shared/model/geoModel/geo-question.model';
import { AdmSysCode } from '@/shared/model/adm/adm-sys-code.model';

export default {
  name: 'geo0402Create',
  props: {
    geoQuestion: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const geoQuestionProp = toRefs(props).geoQuestion;

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      memberRemark: '',
      companyRemark: '',
    };

    // 建立表單物件 ref
    let form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      memberRemark: { maxLength: maxLength(30) },
      companyRemark: { maxLength: maxLength(30) },
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // modal物件
    const $bvModal = useBvModal();

    const toQueryView = () => {
      context.emit('changeComponent', 'geo0402Query');
      reset();
    };

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('Q&A管理備註設定資料內容正確？').then((isOK: boolean) => {
            if (isOK) {
              putForm();
            }
          });
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const putForm = () => {
      let url: string;
      url = `/geo-questions/criteria-remark-update`;
      axios
        .put(url, form)
        .then(() => {
          createDefaultValue(form);
          notificationService.success(`儲存成功`);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const createDefaultValue = data => {
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

    //方法一
    const admSysCode = {
      moduleType: 'GEO',
      dataType: 'JOB_REMARK',
      sortBy: ['sortNo'],
    };
    const handleQuery = () => {
      axios
        .post('/adm-sys-codes/criteria-jpa', admSysCode)
        .then(({ data }) => {
          // console.log(data);
          formDefault.memberRemark = data.content[0].value;
          formDefault.companyRemark = data.content[1].value;
          reset();
        })
        .catch(notificationErrorHandler(notificationService));
    };

    //方法二
    // const handleQuery = () => {
    //   axios
    //     .get('/adm-sys-codes/GEO/JOB_REMARK/01')
    //     .then(({ data }) => {
    //       console.log(data);
    //       formDefault.memberRemark = data.value;
    //     })
    //     .catch(notificationErrorHandler(notificationService));
    //   axios
    //     .get('/adm-sys-codes/GEO/JOB_REMARK/02')
    //     .then(({ data }) => {
    //       console.log(data);
    //       formDefault.companyRemark = data.value;
    //     })
    //     .catch(notificationErrorHandler(notificationService));
    //   reset();
    // };

    onMounted(() => {
      handleQuery();
    });

    return {
      $v,
      reset,
      validateState,
      toQueryView,
      submitForm,
      handleQuery,
    };
  },
};
</script>
<style></style>

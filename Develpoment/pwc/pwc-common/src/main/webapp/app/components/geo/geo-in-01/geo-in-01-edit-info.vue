<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            機構資訊
          </h5>
        </div>
        <div class="card-body">
          <b-row class="mb-3 align-items-center">
            <b-col cols="2">
              <div class="text-right label">登入帳號</div>
            </b-col>
            <b-col cols="5">
              <div class="pl-1">{{ $v.account.$model }}</div>
            </b-col>
          </b-row>
          <!--          <b-row class="mb-1 feedback-row"> </b-row>-->

          <b-row class="mb-3 align-items-center">
            <b-col cols="2">
              <div class="text-right label">機構名稱</div>
            </b-col>
            <b-col cols="5">
              <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.name.$model }}</div>
              <b-form-input
                v-if="formStatusRef === formStatusEnum.MODIFY"
                v-model="$v.name.$model"
                :state="validateState($v.name)"
                trim
              ></b-form-input>
            </b-col>
            <b-col cols="5" class="pl-0">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">{{ error.$message }}</b-form-invalid-feedback>
            </b-col>
          </b-row>
          <!--          <b-row class="mb-1 feedback-row">-->
          <!--            <b-col offset="2">-->
          <!--              <div class="is-invalid"></div>-->
          <!--              <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">{{ error.$message }}</b-form-invalid-feedback>-->
          <!--            </b-col>-->
          <!--          </b-row>-->

          <!--          <b-form-group-->
          <!--            class=""-->
          <!--            label="機構名稱"-->
          <!--            label-class="label"-->
          <!--            label-align="right"-->
          <!--            label-for="name"-->
          <!--            label-cols="2"-->
          <!--            content-cols="10"-->
          <!--          >-->
          <!--            <div class="pl-1"-->
          <!--                 v-if="formStatusRef === formStatusEnum.INFO">{{ $v.name.$model }}</div>-->
          <!--            <b-form-input-->
          <!--              id="name"-->
          <!--              v-if="formStatusRef === formStatusEnum.MODIFY"-->
          <!--              v-model="$v.name.$model"-->
          <!--              :state="validateState($v.name)"-->
          <!--              trim-->
          <!--            ></b-form-input>-->
          <!--            <b-form-invalid-feedback-->
          <!--              v-for="(error, index) of $v.name.$errors"-->
          <!--              :key="index"-->
          <!--            >{{ error.$message }}</b-form-invalid-feedback>-->
          <!--          </b-form-group>-->

          <b-row class="mb-3 align-items-center">
            <b-col cols="2">
              <div class="text-right label">連絡人</div>
            </b-col>
            <b-col cols="5">
              <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.contactPerson.$model }}</div>
              <b-form-input
                v-if="formStatusRef === formStatusEnum.MODIFY"
                v-model="$v.contactPerson.$model"
                :state="validateState($v.contactPerson)"
                trim
              ></b-form-input>
            </b-col>
            <b-col cols="5" class="pl-0">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.contactPerson.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
          </b-row>
          <!--          <b-row class="mb-1 feedback-row">-->
          <!--            <b-col offset="2">-->
          <!--              <div class="is-invalid"></div>-->
          <!--              <b-form-invalid-feedback v-for="(error, index) of $v.contactPerson.$errors" :key="index">{{-->
          <!--                error.$message-->
          <!--              }}</b-form-invalid-feedback>-->
          <!--            </b-col>-->
          <!--          </b-row>-->

          <b-row class="mb-3 align-items-center">
            <b-col cols="2">
              <div class="text-right label">電話</div>
            </b-col>
            <b-col cols="5">
              <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.Tel.$model }}</div>
              <b-form-input
                v-if="formStatusRef === formStatusEnum.MODIFY"
                v-model="$v.Tel.$model"
                :state="validateState($v.Tel)"
                trim
              ></b-form-input>
            </b-col>
            <b-col cols="5" class="pl-0">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.Tel.$errors" :key="index">{{ error.$message }}</b-form-invalid-feedback>
            </b-col>
          </b-row>
          <!--          <b-row class="mb-1 feedback-row">-->
          <!--            <b-col offset="2">-->
          <!--              <div class="is-invalid"></div>-->
          <!--              <b-form-invalid-feedback v-for="(error, index) of $v.Tel.$errors" :key="index">{{ error.$message }}</b-form-invalid-feedback>-->
          <!--            </b-col>-->
          <!--          </b-row>-->

          <b-row class="mb-3 align-items-center">
            <b-col cols="2">
              <div class="text-right label">手機</div>
            </b-col>
            <b-col cols="5">
              <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.mobilePhone.$model }}</div>
              <b-form-input
                v-if="formStatusRef === formStatusEnum.MODIFY"
                v-model="$v.mobilePhone.$model"
                :state="validateState($v.mobilePhone)"
                trim
              ></b-form-input>
            </b-col>
            <b-col cols="5" class="pl-0">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.mobilePhone.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
          </b-row>
          <!--          <b-row class="mb-1 feedback-row">-->
          <!--            <b-col offset="2">-->
          <!--              <div class="is-invalid"></div>-->
          <!--              <b-form-invalid-feedback v-for="(error, index) of $v.mobilePhone.$errors" :key="index">{{-->
          <!--                error.$message-->
          <!--              }}</b-form-invalid-feedback>-->
          <!--            </b-col>-->
          <!--          </b-row>-->

          <b-row class="mb-3 align-items-center">
            <b-col cols="2">
              <div class="text-right label">電子信箱</div>
            </b-col>
            <b-col cols="5">
              <div class="pl-1" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.email.$model }}</div>
              <b-form-input
                v-if="formStatusRef === formStatusEnum.MODIFY"
                v-model="$v.email.$model"
                :state="validateState($v.email)"
                trim
              ></b-form-input>
            </b-col>
            <b-col cols="5" class="pl-0">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.email.$errors" :key="index">{{
                error.$message
              }}</b-form-invalid-feedback>
            </b-col>
          </b-row>
          <!--          <b-row class="mb-1 feedback-row">-->
          <!--            <b-col offset="2">-->
          <!--              <div class="is-invalid"></div>-->
          <!--              <b-form-invalid-feedback v-for="(error, index) of $v.email.$errors" :key="index">{{-->
          <!--                error.$message-->
          <!--              }}</b-form-invalid-feedback>-->
          <!--            </b-col>-->
          <!--          </b-row>-->

          <b-button-toolbar v-show="formStatusRef === formStatusEnum.INFO" class="float-right mt-5">
            <b-button size="sm" variant="outline-secondary" @click="changeFormStatus(formStatusEnum.MODIFY)">
              <font-awesome-icon :icon="['far', 'edit']" />
              {{ $t('button.modify') }}
            </b-button>
            <b-button size="sm" variant="outline-secondary" @click="">
              <font-awesome-icon :icon="['fas', 'arrow-left']" />
              返回
            </b-button>
          </b-button-toolbar>
          <b-button-toolbar v-show="formStatusRef === formStatusEnum.MODIFY" class="float-right mt-5">
            <b-button size="sm" variant="outline-secondary" v-promise-btn="{ promise: dataPromise }" @click="submitForm">
              <font-awesome-icon :icon="['fas', 'save']" />
              儲存
            </b-button>
            <b-button size="sm" variant="outline-secondary" @click="reset">重設</b-button>
            <b-button size="sm" variant="outline-secondary" @click="changeFormStatus(formStatusEnum.INFO)">
              <font-awesome-icon :icon="['fas', 'undo-alt']" />
              取消
            </b-button>
          </b-button-toolbar>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onMounted, onActivated, reactive, Ref, ref } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { statusFormatter } from '@/shared/formatter/common';
import { guildTypeFormatter, statusFormatter } from '@/shared/formatter/common';
import { CmsComp } from '@/shared/model/cms-comp.model';

export default {
  name: 'geoIn01EditInfo',
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      MODIFY = 'modify',
    }

    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);

    onMounted(() => {
      const data = {
        account: '23465070',
        name: '光宇工程顧問股份有限公司',
        contactPerson: '周邦彥', //聯絡人
        Tel: '04-23710156',
        mobilePhone: '0987654321',
        email: 'chou@mail.kunitech.com.tw', //信箱
      };
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
    });

    // onActivated(() => {
    //   if (cmsCompProp.value) {
    //     Object.assign(formDefault, cmsCompProp.value);
    //     Object.assign(form, formDefault);
    //   } else {
    //     Object.assign(formDefault, formEmpty);
    //     Object.assign(form, formDefault);
    //   }
    //   formStatusRef.value = formStatus.value.value;
    // });

    const changeFormStatus = (status: formStatusEnum) => {
      if (status === formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    let formDefault = {
      account: '', //登入帳號
      name: '', //機構名稱
      contactPerson: '', //聯絡人
      Tel: '', //電話
      mobilePhone: '', //手機
      email: '', //信箱
    };

    let form = reactive(Object.assign({}, formDefault));

    const rules = {
      account: {},
      name: { notNull: required },
      contactPerson: { notNull: required },
      Tel: { notNull: required },
      mobilePhone: { notNull: required },
      email: { notNull: required, email: email },
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
            if (isOK) {
              setFormAddress();
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
      if (formStatusRef.value === formStatusEnum.MODIFY) {
        url = `/cms-comps/xxx`;
      }
      // dataPromise.value = axios
      //   .put(url, form)
      //   .then(({ data }) => {
      changeFormStatus(formStatusEnum.INFO);
      // createDefaultValue(data);
      notificationService.success(`${form.name}:修改成功`);
      // })
      // .finally(() => (dataPromise.value = null))
      // .catch(notificationErrorHandler(notificationService));

      Object.assign(formDefault, form);
    };

    // const createDefaultValue = (data: any) => {
    //   Object.assign(formDefault, data);
    //   Object.assign(form, formDefault);
    // };

    return {
      formStatusEnum,
      formStatusRef,
      dataPromise,
      changeFormStatus,
      $v,
      validateState,
      reset,
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
.label {
  background-color: #737373;
  font-weight: bolder;
  color: white;
  padding-top: 7px;
  padding-bottom: 7px;
  padding-right: 7px;
  /*border-right: 3px solid #d57c36;*/
  /*border-bottom: 3px solid #d57c36;*/
}
.mx-datepicker {
  width: 100%;
}
.b-form-group-label {
  background-color: #e5e5e5;
}
.feedback-row {
  height: 24px;
}
</style>

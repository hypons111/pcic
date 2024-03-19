<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            問與答
          </h5>
        </div>
        <div class="card-body">
          <b-form-row class="align-items-center" v-if="formStatusRef !== formStatusEnum.CREATE">
            <b-col cols="2" class="text-right label"><span>提問時間</span> </b-col>
            <b-col cols="4">{{ $v.questionTime.$model }}</b-col>
            <b-col cols="2" class="text-right label"><span>姓名</span> </b-col>
            <b-col cols="4">{{ $v.name.$model }}</b-col>
          </b-form-row>
          <b-form-row class="align-items-center mt-2">
            <b-col cols="2" class="text-right label"><span>問題</span> </b-col>
            <b-col cols="10">
              <b-form-textarea v-if="formStatusRef === formStatusEnum.CREATE" v-model.trim="$v.question.$model"> </b-form-textarea>
              <span v-else>{{ $v.question.$model }}</span>
            </b-col>
          </b-form-row>
          <b-form-row class="align-items-center mt-2" v-if="formStatusRef !== formStatusEnum.CREATE">
            <b-col cols="2" class="text-right label"> <span class="red-text">*</span><span>回答</span></b-col>
            <b-col cols="10">
              <!--              <div v-if="formStatusRef !== formStatusEnum.INFO">-->
              <b-form-textarea v-if="formStatusRef !== formStatusEnum.INFO" v-model.trim="$v.answer.$model"> </b-form-textarea>
              <!--              </div>-->
              <span v-else>{{ $v.answer.$model }}</span>
            </b-col>
          </b-form-row>

          <b-button-toolbar v-show="formStatusRef === formStatusEnum.CREATE" class="float-right mt-5">
            <b-button size="sm" variant="outline-secondary" @click="">
              <font-awesome-icon :icon="['far', 'save']" />
              確認送出
            </b-button>
            <b-button size="sm" variant="outline-secondary" @click="toQueryView">
              <font-awesome-icon :icon="['fas', 'arrow-left']" />
              返回
            </b-button>
          </b-button-toolbar>
          <b-button-toolbar v-show="formStatusRef === formStatusEnum.INFO" class="float-right mt-5">
            <!--            <b-button size="sm" variant="outline-secondary" @click="changeFormStatus(formStatusEnum.MODIFY)">-->
            <!--              <font-awesome-icon :icon="['far', 'edit']" />-->
            <!--              修改-->
            <!--            </b-button>-->
            <b-button size="sm" variant="outline-secondary" @click="toQueryView">
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
            <b-button size="sm" variant="outline-secondary" @click="toQueryView">
              <font-awesome-icon :icon="['fas', 'arrow-left']" />
              返回
            </b-button>
          </b-button-toolbar>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, Ref, ref } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
// import { guildTypeFormatter, statusFormatter } from '@/shared/formatter/common';
// import { CmsComp } from '@/shared/model/cms-comp.model';

export default {
  name: 'geoCO01EditInfo',
  props: {
    cmsComp: {
      required: false,
    },
    formStatus: {
      // type: Ref<formStatusEnum>,
      required: false,
    },
  },
  setup(props, context) {
    // enum RoleEnum {
    //   USER = '使用者',
    //   ADMIN = '管理員',
    // }

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // const roleRef: Ref<RoleEnum> = ref(RoleEnum.USER);

    // 傳入的物件
    const cmsCompProp = computed(() => props.cmsComp);
    const formStatus = computed(() => props.formStatus);
    const formStatusRef: Ref<formStatusEnum> = ref(formStatus.value.value);

    let formEmpty = {
      id: '',
      questionTime: '', //提問時間
      name: '', //姓名
      question: '', //問題
      answer: '', //回答
    };

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      id: '',
      questionTime: '', //提問時間
      name: '', //姓名
      question: '', //問題
      answer: '', //回答
    };

    // 建立表單物件 ref
    let form = reactive(Object.assign({}, formDefault));

    onActivated(() => {
      // alert(formStatus.value.value);
      if (cmsCompProp.value) {
        Object.assign(formDefault, cmsCompProp.value);
        Object.assign(form, formDefault);
      } else {
        Object.assign(formDefault, formEmpty);
        Object.assign(form, formDefault);
      }
      formStatusRef.value = formStatus.value.value;
    });

    // 表單物件驗證規則
    const rules = {
      id: {},
      questionTime: {},
      name: {},
      question: { notNull: required },
      answer: { notNull: required },
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
      context.emit('changeComponent', 'GeoCo01Query', isReload.value);
    };

    const changeFormStatus = (status: formStatusEnum) => {
      if (status === formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
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
      if (formStatusRef.value === formStatusEnum.MODIFY) {
        url = `/cms-comps/${form.id}`;
      }
      dataPromise.value = axios
        .put(url, form)
        .then(({ data }) => {
          changeFormStatus(formStatusEnum.INFO);
          createDefaultValue(data);
          isReload.value = true;
          notificationService.success(`${form.id}-${form.name}:修改成功`);
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const createDefaultValue = (data: any) => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
    };

    return {
      formStatusEnum,
      formStatusRef,
      $v,
      dataPromise,
      changeFormStatus,
      reset,
      validateState,
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

.label {
  background-color: #737373;
  font-weight: bolder;
  color: white;
  padding-top: 5px;
  padding-bottom: 5px;
}
</style>

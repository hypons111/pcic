<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            密碼變更
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 帳號 -->
            <i-form-group-check labelStar :label="$t('label.account') + '：'" :item="$v.userId">
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.userId.$model }}</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 新密碼 -->
            <i-form-group-check labelStar :label="$t('label.newPassword1') + '：'" :item="$v.userPadHash">
              <b-input-group>
                <template #append>
                  <b-input-group-text class="bg-info text-white btn" @click="showPwdNew = !showPwdNew">
                    <b-icon :icon="showPwdNew ? 'eye-fill' : 'eye-slash-fill'"></b-icon>
                  </b-input-group-text>
                </template>
                <b-form-input :type="showPwdNew ? 'password' : 'text'" v-model="$v.userPadHash.$model" trim></b-form-input>
              </b-input-group>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 再次輸入新密碼 -->
            <i-form-group-check labelStar :label="$t('label.newPassword2') + '：'" :item="$v.newPassword2">
              <b-input-group>
                <template #append>
                  <b-input-group-text class="bg-info text-white btn" @click="showPwdNew2 = !showPwdNew2">
                    <b-icon :icon="showPwdNew2 ? 'eye-fill' : 'eye-slash-fill'"></b-icon>
                  </b-input-group-text>
                </template>
                <b-form-input :type="showPwdNew2 ? 'password' : 'text'" v-model="$v.newPassword2.$model" trim></b-form-input>
              </b-input-group>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 驗證碼 -->
            <i-form-group-check labelStar :label="$t('label.verificationCode') + '：'" :item="$v.verificationCode">
              <b-form-input
                v-model="$v.verificationCode.$model"
                type="text"
                :state="validateState($v.verificationCode)"
                placeholder="點擊圖片可變換驗證碼"
              ></b-form-input>
            </i-form-group-check>
            <i-form-group-check label-cols-md="1" content-cols-md="8">
              <div @click="refreshCode">
                <s-identify :identifyCodeDefault="identifyCodeRef"></s-identify>
              </div>
            </i-form-group-check>
          </b-form-row>

          <b-button-toolbar class="d-flex justify-content-center">
            <i-button class="mr-1" type="check-circle" @click="searchAdmAccount"></i-button>
          </b-button-toolbar>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, computed, reactive, ref, watch, toRefs } from '@vue/composition-api';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { required, minLength, sameAs, passwordGpo } from '@/shared/validators';
import { useValidation, validateState } from '@/shared/form';
import SIdentify from '@/shared/form/identify.vue';
import axios from 'axios';

export default {
  name: 'pwb0201pwd',
  props: {
    userId: {
      type: Object,
      required: false,
    },
  },
  components: {
    SIdentify,
  },
  setup(props) {
    onMounted(() => {
      makeCode(identifyCodes, 4);
    });

    // 宣告表單物件及初始值，是前後端 接收或傳遞 的資料物件
    const formDefault = {
      userId: '', //帳號userId
      userPadHash: '', //新密碼
      newPassword2: '', //再次輸入新密碼
      verificationCode: '', //驗證碼
      identifyCodeA: '', //驗證碼(圖)
    };

    // 建立一份新的表單物件
    let form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      userId: { notNull: required },
      userPadHash: { notNull: required, minLength: minLength(8), passwordGpo: passwordGpo(computed(() => form.userPadHash)) },
      newPassword2: {
        notNull: required,
        sameAs: sameAs(
          computed(() => form.userPadHash),
          '新密碼'
        ),
      },
      verificationCode: {
        notNull: required,
        sameAs: sameAs(
          computed(() => form.identifyCodeA),
          '驗證碼'
        ),
      },
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    let identifyCodeRef = ref('');

    //驗證碼
    function randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    }

    const identifyCode = '';
    const identifyCodes = '1234567890';

    //重新產生驗證碼
    function refreshCode() {
      identifyCode = '';
      makeCode(identifyCodes, 4);
    }

    //驗證碼
    function makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        identifyCode += identifyCodes[randomNum(0, identifyCodes.length)];
      }
      identifyCodeRef.value = identifyCode;
      form.identifyCodeA = identifyCodeRef.value;
    }

    let showPwdNew = true;
    let showPwdNew2 = true;

    const userIdProp = toRefs(props).userId;

    watch(
      userIdProp,
      () => {
        if (userIdProp.value) {
          createDefaultValue(userIdProp.value);
        }
      },
      { immediate: true }
    );

    function createDefaultValue(userIdProp) {
      form.userId = userIdProp;
    }

    const notificationService = useNotification();

    //用USER_ID查詢ADM_ACCOUNT的資料
    function searchAdmAccount() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          axios
            .get('/service/adm-account/' + userIdProp.value)
            .then(({ data }) => {
              data.userPadHash = form.userPadHash;
              Object.assign(form, data);
              saveAdmAccount();
              saveAdmPadHis();
            })
            .finally(() => {})
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    //將ADM_ACCOUNT的密碼update
    function saveAdmAccount() {
      form.updateUser = '申請人';
      form.updateTime = new Date();
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          axios
            .put('/service/adm-change-password', form)
            .finally(() => {})
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    //新增一筆ADM_PAD_HIS
    function saveAdmPadHis() {
      form.createTime = new Date();
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          axios
            .post('/adm-pad-his', form)
            .finally(() => {})
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    return {
      $v,
      form,
      validateState,
      refreshCode,
      identifyCodeRef,
      showPwdNew,
      showPwdNew2,
      searchAdmAccount,
    };
  },
};
</script>

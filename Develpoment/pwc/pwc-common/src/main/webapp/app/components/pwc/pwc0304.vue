<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            帳號申請
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 帳號 -->
            <i-form-group-check labelStar :label="$t('label.account') + '：'" :item="$v.userId">
              <b-form-input v-model="$v.userId.$model" type="text" :state="validateState($v.userId)"></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 預設密碼 -->
            <i-form-group-check labelStar :label="$t('label.defaultPassword') + '：'" :item="$v.defaultPassword">
              <b-input-group>
                <template #append>
                  <b-input-group-text class="bg-info text-white btn"  @click="showPwdDefault=!showPwdDefault">
                    <b-icon :icon="showPwdDefault ? 'eye-fill' : 'eye-slash-fill'"></b-icon>
                  </b-input-group-text>
                </template>
                <b-form-input :type="showPwdDefault ? 'password' : 'text'" v-model="$v.defaultPassword.$model" trim></b-form-input>
              </b-input-group>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 新密碼 -->
            <i-form-group-check labelStar :label="$t('label.newPassword1') + '：'" :item="$v.userPadHash">
              <b-input-group>
                <template #append>
                  <b-input-group-text class="bg-info text-white btn"  @click="showPwdNew=!showPwdNew">
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
                  <b-input-group-text class="bg-info text-white btn"  @click="showPwdNew2=!showPwdNew2">
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
              <b-form-input v-model="$v.verificationCode.$model" trim placeholder="點擊圖片可變換驗證碼"></b-form-input>
            </i-form-group-check>
            <i-form-group-check label-cols-md="1" content-cols-md="8">
              <div @click="refreshCode" >
                <SIdentify :identifyCodeDefault="identifyCodeRef"></SIdentify>   
              </div>
            </i-form-group-check>
          </b-form-row>

          <b-button-toolbar class="d-flex justify-content-center">
            <i-button class="mr-1" type="send-check" @click="checkPwAndSave"></i-button>
          </b-button-toolbar>
        </div>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { computed, defineComponent, onMounted, reactive, ref, watch } from '@vue/composition-api';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { required, minLength, sameAs, passwordGpo} from '@/shared/validators';
import { parseRocDate } from '@/shared/date/minguo-calendar-utils';
import axios from 'axios';
import { useBvModal } from '@/shared/modal';
import { useValidation, validateState } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import SIdentify from '@/shared/form/identify.vue';

export default defineComponent({
  name: 'pwc0304U',
  components: {
    SIdentify
  },
  setup(props, context) {

    const identifyCode = "";
    const identifyCodes = "1234567890";

    //onMounted組件第一次載入時調用
    onMounted(() => {      
      makeCode(identifyCodes, 4);
    });

    const notificationService = useNotification();
    // modal物件
    const $bvModal = useBvModal();

    // 宣告表單物件及初始值，是前後端 接收或傳遞 的資料物件
    const formDefault = {
      userId: '', //帳號userId
      defaultPassword: '', //預設密碼
      userPadHash: '', //新密碼
      newPassword2:'' , //再次輸入新密碼
      verificationCode:'',//驗證碼
      identifyCodeA:'',//驗證碼(圖)
    };

    // 建立一份新的表單物件
    let form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      userId: {notNull: required},
      defaultPassword: {notNull: required},
      userPadHash: {notNull: required, minLength: minLength(8), passwordGpo: passwordGpo(computed(() => form.userPadHash))},
      newPassword2: {notNull: required,sameAs: sameAs(computed(() => form.userPadHash),'新密碼')},
      verificationCode: {notNull: required,sameAs: sameAs(computed(() => form.identifyCodeA),'驗證碼')},
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //取得使用者資訊 需要登入後才能使用
    const userIdentity = ref(useGetters(['account']).account.value);

    function saveTask() {
      form.updateUser = '申請人';
      form.updateTime = new Date();
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          axios.put('/service/adm-change-password', form)
          .then(({ data }) => {
            reset();
          })
          .finally(() => {})
          .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    function checkPwAndSave() {
      // 查詢user帳號取密碼
      axios
        .post('/service/adm-user-account', form)
        .then(({ data }) => {
          if (data.length === 0) {
            $bvModal.msgBoxConfirm('此帳號不存在 請重新輸入', {
            headerBgVariant: 'danger',
            headerTextVariant: 'light',
            titleTag: 'h4',
            title: '帳號確認',
            headerClass: 'p-2 justify-content-center font-weight-bolder',
            bodyClass: 'h5',
            footerClass: 'justify-content-center',
            okTitle: '確定',
            });
          }else{
            if (data.userPadHash !== form.defaultPassword) {
              $bvModal.msgBoxConfirm('預設密碼錯誤 請重新輸入', {
              headerBgVariant: 'danger',
              headerTextVariant: 'light',
              titleTag: 'h4',
              title: '密碼確認',
              headerClass: 'p-2 justify-content-center font-weight-bolder',
              bodyClass: 'h5',
              footerClass: 'justify-content-center',
              okTitle: '確定',
              });
            } else {
              data.userPadHash = form.userPadHash;
              Object.assign(form, data);
              saveTask();
            }
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    let identifyCodeRef = ref('');
    //驗證碼
    function randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    }
    //重新產生驗證碼 
    function refreshCode() {
      identifyCode = "";
      makeCode(identifyCodes, 4);
    }
    //驗證碼
    function makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        identifyCode += identifyCodes[
          randomNum(0, identifyCodes.length)
        ];
      }
      identifyCodeRef.value = identifyCode;
      form.identifyCodeA = identifyCodeRef.value;
    }

    let showPwdDefault = true;
    let showPwdNew = true;
    let showPwdNew2 = true;

    return {
      $v,
      form,
      saveTask,
      validateState,
      checkPwAndSave,
      refreshCode,
      identifyCodeRef,
      showPwdDefault,
      showPwdNew,
      showPwdNew2,
    };
  },
});
</script>
<style scoped>

</style>


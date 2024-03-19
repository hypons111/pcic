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
            <i-form-group-check labelStar :label="$t('label.chName') + '：'" :item="$v.chName">
              <b-form-input v-model="$v.chName.$model" type="text" :state="validateState($v.chName)"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 身分證字號/居留證號 -->
            <i-form-group-check labelStar :label="$t('label.certNo') + '：'" :item="$v.idno">
              <b-form-input v-model="$v.idno.$model" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 性別 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.sex') + '：'" :item="$v.sex">
              <b-form-select v-model="$v.sex.$model" :options="options.sex" :state="validateState($v.sex)">
                <template #first>
                  <option :value="null">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 出生日期 -->
            <i-form-group-check labelStar content-cols="5" :label="$t('label.birthday') + '：'" :item="$v.birthday">
              <i-date-picker v-model="$v.birthday.$model" :state="validateState($v.birthday)" placeholder="yyy/MM/dd"></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 電話 -->
            <i-form-group-check labelStar class="col-12" label-cols="2" content-cols="5" :label="$t('label.tel') + '：'" :item="$v.workTel">
              <i-tel v-model.trim="$v.workTel.$model" :state="validateState($v.workTel)" showExt></i-tel>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 行動電話 -->
            <i-form-group-check labelStar content-cols="6" :label="$t('label.mobileTel') + '：'" :item="$v.mobileTel">
              <i-tel v-model.trim="$v.mobileTel.$model" :state="validateState($v.mobileTel)" isMobile></i-tel>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 通訊地址 -->
            <i-form-group-check
              :label="$t('label.address') + '：'"
              class="col-12"
              label-cols="2"
              content-cols="10"
              :labelStar="true"
              :hasInvalid="true"
            >
              <i-address-picker
                ref="addressComp"
                :isReset="true"
                :addrCity="form.homeAddrCity"
                :addrArea="form.homeAddrTown"
                :addrRoad="form.homeAddrOther"
                @getCity="getHomeAddrCity"
                @getArea="getHomeAddrTown"
                @getRoad="getHomeAddrOther"
              ></i-address-picker>
            </i-form-group-check>
            <!-- {{ $v.address }}-->
          </b-form-row>

          <b-form-row>
            <!-- email -->
            <i-form-group-check labelStar :label="$t('label.email') + '：'" :item="$v.email">
              <b-form-input v-model="$v.email.$model" type="text" :state="validateState($v.email)" placeholder="xx@xx.xx"></b-form-input>
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
                <SIdentify :identifyCodeDefault="identifyCodeRef"></SIdentify>
              </div>
            </i-form-group-check>
          </b-form-row>

          <b-button-toolbar class="d-flex justify-content-center">
            <i-button class="mr-1" type="send-check" @click="checkUserId"></i-button>
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
import { required, email, maxLength, nationalId, mobileTel, lineTel, sameAs } from '@/shared/validators';
import { parseRocDate } from '@/shared/date/minguo-calendar-utils';
import axios from 'axios';
import { useBvModal } from '@/shared/modal';
import { useValidation, validateState } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import SIdentify from '@/shared/form/identify.vue';

export default defineComponent({
  name: 'pwc0303Login',
  components: {
    SIdentify,
  },

  setup(props, context) {
    const notificationService = useNotification();
    // modal物件
    const $bvModal = useBvModal();

    // 宣告表單物件及初始值，是前後端 接收或傳遞 的資料物件
    const formDefault = {
      userId: '', //帳號userId
      chName: '', //姓名
      idno: '', //證號idno
      sex: '', //性別
      birthday: null, //生日birthday
      workTel: '', //電話 workTel
      mobileTel: '', //行動電話
      homeAddrCity: '', //地址 縣市 homeAddrCity
      homeAddrTown: '', //地址 地區 homeAddrTown
      homeAddrOther: '', //地址 路名 homeAddrOther
      email: '', // 信箱
      verificationCode: '', //驗證碼
      identifyCodeA: '', //驗證碼(圖)
    };
    // 建立一份新的表單物件
    let form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      userId: { notNull: required },
      chName: { notNull: required, maxLength: maxLength(50) },
      idno: { nationalId: nationalId },
      sex: { notNull: required },
      birthday: { notNull: required },
      workTel: { lineTel: lineTel },
      mobileTel: { mobileTel: mobileTel },
      email: { email: email, maxLength: maxLength(100) },
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

    const checked = ref([]);

    const options = reactive({
      sex: [
        { value: 'M', text: '男' },
        { value: 'F', text: '女' },
      ],
    });

    //使用地址元件
    const addressComp = ref(null);
    function getHomeAddrCity(param) {
      form.homeAddrCity = param;
    }
    function getHomeAddrTown(param) {
      form.homeAddrTown = param;
    }
    function getHomeAddrOther(param) {
      form.homeAddrOther = param;
    }

    const isCheckUserId = ref(false);

    //取得使用者資訊
    const userIdentity = ref(useGetters(['account']).account.value);

    function saveTask() {
      form.updateUser = '申請人';
      form.updateTime = new Date();

      if (isCheckUserId) {
        checkValidity().then((isValid: boolean) => {
          if (isValid) {
            // combineTel();
            // combineMobileTel();
            $bvModal
              .msgBoxConfirm('是否確認新增', {
                headerBgVariant: 'info',
                headerTextVariant: 'light',
                titleTag: 'h4',
                title: '新增確認',
                headerClass: 'p-2 justify-content-center font-weight-bolder',
                bodyClass: 'h5',
                footerClass: 'justify-content-center',
                okTitle: '確定',
                okVariant: 'info',
                cancelTitle: '取消',
              })
              .then((isOK: boolean) => {
                if (isOK) {
                  save();
                }
              });
          } else {
            $bvModal.msgBoxOk('請檢查資料是否輸入完畢。', {
              headerBgVariant: 'danger',
              headerTextVariant: 'light',
              titleTag: 'h4',
              title: '資料未輸入完畢',
              headerClass: 'p-2 justify-content-center font-weight-bolder',
              bodyClass: 'h5',
              footerClass: 'justify-content-center',
              okTitle: '確定',
              okVariant: 'info',
            });
          }
        });
      }
    }

    function save() {
      axios
        .put('/service/adm-account-save', form)
        .then(({ data }) => {
          reset();
          // getAllRoles();
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    function checkUserId() {
      // 查詢user帳號
      axios
        .post('/service/adm-user-account', form)
        .then(({ data }) => {
          if (data.length > 0) {
            $bvModal.msgBoxConfirm('此帳號已註冊 請重新輸入', {
              headerBgVariant: 'danger',
              headerTextVariant: 'light',
              titleTag: 'h4',
              title: '帳號確認',
              headerClass: 'p-2 justify-content-center font-weight-bolder',
              bodyClass: 'h5',
              footerClass: 'justify-content-center',
              okTitle: '確定',
            });
          } else {
            $bvModal
              .msgBoxConfirm('此帳號未註冊過', {
                headerBgVariant: 'danger',
                headerTextVariant: 'light',
                titleTag: 'h4',
                title: '帳號確認',
                headerClass: 'p-2 justify-content-center font-weight-bolder',
                bodyClass: 'h5',
                footerClass: 'justify-content-center',
                okTitle: '確定',
              })
              .then((isOK: boolean) => {
                if (isOK) {
                  saveTask();
                }
              });
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function isInLeft(option) {
      if (checked.value.length) {
        return checked.value.filter(c => c === option.code).length <= 0;
      }
      return true;
    }

    const identifyCode = '';
    const identifyCodes = '1234567890';
    let identifyCodeRef = ref('');

    onMounted(() => {
      makeCode(identifyCodes, 4);
    });

    //驗證碼
    function randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    }
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

    return {
      checkUserId,
      $v,
      form,
      options,
      validateState,
      addressComp,
      getHomeAddrCity,
      getHomeAddrTown,
      getHomeAddrOther,
      refreshCode,
      identifyCodeRef,
    };
  },
});
</script>

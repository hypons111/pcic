<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0" v-if="formStatusRef === formStatusEnum.INFO">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視i18n代碼
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.CREATE">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            新增i18n代碼
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.MODIFY">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            修改i18n代碼
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check :label="$t('label.adm013.lang') + '：'" :item="$v.lang" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-select :options="options.langOpts" v-model="$v.lang.$model" :state="validateState($v.lang)"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm013.lang') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.lang.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm013.category') + '：'"
                :item="$v.category"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-select
                  :options="options.categoryOpts"
                  v-model="$v.category.$model"
                  :state="validateState($v.category)"
                ></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm013.category') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.category.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm013.code') + '：'" :item="$v.code" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model="$v.code.$model" type="text" :state="validateState($v.code)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm013.code') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.code.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm013.text') + '：'" :item="$v.text" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model="$v.text.$model" type="text" :state="validateState($v.text)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm013.text') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.text.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-button-toolbar v-show="formStatusRef === formStatusEnum.INFO" class="float-right">
              <i-button class="mr-1" type="pencil-square" @click="changeFormStatus(formStatusEnum.MODIFY)"></i-button>
              <i-button class="mr-1" type="trash" @click="submitDelete()"></i-button>
              <i-button class="mr-1" type="arrow-left" @click="toQueryView()"></i-button>
            </b-button-toolbar>

            <b-button-toolbar v-show="formStatusRef !== formStatusEnum.INFO" class="float-right">
              <i-button class="mr-1" type="save" @click="submitForm()"></i-button>
              <i-button class="mr-1" type="x-circle" @click="reset()"></i-button>
              <i-button
                v-if="formStatusRef === formStatusEnum.MODIFY"
                class="mr-1"
                type="arrow-left"
                @click="changeFormStatus(formStatusEnum.INFO)"
              ></i-button>
              <i-button v-if="formStatusRef === formStatusEnum.CREATE" class="mr-1" type="arrow-left" @click="toQueryView()"></i-button>
            </b-button-toolbar>
          </b-form>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, reactive, ref, toRefs, watch } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useBvModal } from '@/shared/modal';
import { englishNumberSymbol, maxLength, maxValue, minValue, required } from '@/shared/validators';
import { accountStore } from '@/shared/config/store/account-store';
import { navigateByNameAndParams } from '@/router/router';
import { handleBack } from '@/router/router';
import { AdmCodeTable } from '@/shared/model/adm-code-table.model';

export default {
  name: 'Adm013EditInfo',
  props: {
    id: {
      required: true,
    },
    formStatus: {
      type: String,
      required: true,
    },
  },
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef = ref(formStatusEnum.INFO);

    // modal物件
    const $bvModal = useBvModal();
    // 建立表單物件預設值
    let formDefault: AdmCodeTable = new AdmCodeTable().setDefault();
    const isReload = ref(false);
    // 建立一份新的表單物件
    const form = reactive(Object.assign({}, formDefault));
    // 使用者資訊
    const userUnit = computed(() => {
      let account = context.root.$store.getters.account;
      if (account) {
        return account.unit;
      } else {
        return '';
      }
    });

    // @ts-ignore
    const userIdentity = accountStore.state.userIdentity;

    // 表單物件驗證規則
    const rules = {
      lang: { notNull: required },
      category: { notNull: required },
      code: { notNull: required, maxLength: maxLength(100) },
      text: { notNull: required, maxLength: maxLength(2000) },
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    const notificationService: NotificationService = useNotification();

    const options = reactive({
      langOpts: [
        { value: '', text: '請選擇' },
        { value: 'zh_TW', text: 'zh_TW' },
        { value: 'en_US', text: 'en_US' },
      ],
      categoryOpts: [
        { value: '', text: '請選擇' },
        { value: 'button', text: 'button' },
        { value: 'label', text: 'label' },
      ],
    });

    function initCodeTable() {
      // 查整頁資訊
      axios
        .get(`/adm-code-table/${propsId.value}`)
        .then(({ data }) => {
          if (data && data['id']) {
            Object.assign(formDefault, data);
            Object.assign(form, formDefault);
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 返回列表
    function toQueryView() {
      const param = { isReload: isReload.value };
      handleBack(param);
    }

    // 變更表單操作狀態
    function changeFormStatus(status: formStatusEnum) {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    }

    function submitForm() {
      if (formStatusRef.value === formStatusEnum.CREATE) {
        submitCreation();
      } else if (formStatusRef.value === formStatusEnum.MODIFY) {
        submitModify();
      }
    }

    function submitCreation() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal
            .msgBoxConfirm('是否確認新增?', {
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
                postCreation();
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

    function submitModify() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal
            .msgBoxConfirm('是否確認修改？', {
              headerBgVariant: 'info',
              headerTextVariant: 'light',
              titleTag: 'h4',
              title: '修改確認',
              headerClass: 'p-2 justify-content-center font-weight-bolder',
              bodyClass: 'h5',
              footerClass: 'justify-content-center',
              okTitle: '確定',
              okVariant: 'info',
              cancelTitle: '取消',
            })
            .then((isOK: boolean) => {
              if (isOK) {
                putModify();
              }
            });
        } else {
          console.log(form);
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

    function submitDelete() {
      $bvModal
        .msgBoxConfirm('是否確認刪除？', {
          headerBgVariant: 'danger',
          headerTextVariant: 'light',
          titleTag: 'h4',
          title: '刪除確認',
          headerClass: 'p-2 justify-content-center font-weight-bolder',
          bodyClass: 'h5',
          footerClass: 'justify-content-center',
          okTitle: '確定',
          okVariant: 'info',
          cancelTitle: '取消',
        })
        .then((isOK: boolean) => {
          if (isOK) {
            deleteCodeTableInfo(propsId.value);
          }
        });
    }

    function deleteCodeTableInfo(id: number) {
      axios
        .delete(`/adm-code-table/${id}`)
        .then(() => {
          isReload.value = true;
          navigateByNameAndParams('adm013', { isReload: isReload.value });
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 送出新增表單
    function postCreation() {
      axios
        .post(`/adm-code-table`, form)
        .then(({ data }) => {
          isReload.value = true;
          navigateByNameAndParams('adm013', { isReload: isReload.value });
          // notificationService.success(`系統代碼:${data.moduleType}，已新增。`);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    // 送出修改表單
    function putModify() {
      axios
        .put(`/adm-code-table/${propsId.value}`, form)
        .then(({ data }) => {
          isReload.value = true;
          Object.assign(formDefault, data);
          navigateByNameAndParams('adm013', { isReload: isReload });
          changeFormStatus(formStatusEnum.INFO);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    const propsId = toRefs(props).id;
    const propsFormStatus = toRefs(props).formStatus;

    watch(
      propsId,
      () => {
        if (propsId.value !== '_new') {
          initCodeTable();
          if (propsFormStatus.value === formStatusEnum.INFO) {
            changeFormStatus(formStatusEnum.INFO);
          } else {
            changeFormStatus(formStatusEnum.MODIFY);
          }
        } else {
          Object.assign(formDefault, new AdmCodeTable().setDefault());
          Object.assign(form, formDefault);
          changeFormStatus(formStatusEnum.CREATE);
        }
      },
      { immediate: true }
    );

    return {
      formStatusEnum,
      formStatusRef,
      formDefault,
      options,
      $v,
      userUnit,
      toQueryView,
      submitForm,
      submitDelete,
      reset,
      validateState,
      changeFormStatus,
      form,
    };
  },
};
</script>

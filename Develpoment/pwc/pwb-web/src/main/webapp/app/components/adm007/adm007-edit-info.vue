<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0" v-if="formStatusRef === formStatusEnum.INFO">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視延伸功能
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.CREATE">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            新增延伸功能
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.MODIFY">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            修改延伸功能
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm007.functionExtendId') + '：'"
                :item="$v.functionExtendId"
                v-if="formStatusRef === formStatusEnum.CREATE"
              >
                <b-form-input v-model="$v.functionExtendId.$model" :state="validateState($v.functionExtendId)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm007.functionExtendId') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.functionExtendId.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm007.functionExtendName') + '：'"
                :item="$v.functionExtendName"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-input
                  v-model="$v.functionExtendName.$model"
                  type="text"
                  :state="validateState($v.functionExtendName)"
                ></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm007.functionExtendName') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.functionExtendName.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm007.functionExtendDescript') + '：'"
                :item="$v.functionExtendDescript"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-textarea
                  v-model="$v.functionExtendDescript.$model"
                  type="text"
                  :state="validateState($v.functionExtendDescript)"
                ></b-form-textarea>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm007.functionExtendDescript') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.functionExtendDescript.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row v-if="formStatusRef !== formStatusEnum.CREATE">
              <i-form-group-check :label="$t('label.adm007.updateUser') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ formDefault.updateUser }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm007.updateTime') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ formDefault.updateTime | dateTimeFilter }}
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
import { maxLength, required } from '@/shared/validators';
import { accountStore } from '@/shared/config/store/account-store';
import { AdmFunctionExtend } from '@/shared/model/adm-function-extend.model';
import { navigateByNameAndParams } from '@/router/router';
import { functionCategoryFormatter, statusFormatter, sysCategoryFormatter } from '@/shared/formatter/common';
import { dateTimeFormatter } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Adm007EditInfo',
  props: {
    functionExtendId: {
      type: String,
      required: false,
    },
    formStatus: {
      type: String,
      required: false,
    },
  },
  setup(props, context) {
    // 表單操作狀態
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }
    const formStatusRef = ref(formStatusEnum.INFO);
    // modal物件
    const $bvModal = useBvModal();
    // 修改狀態
    const isReload = ref(false);
    // 建立表單物件預設值
    let formDefault: AdmFunctionExtend = new AdmFunctionExtend().setDefault();
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
      functionExtendId: { notNull: required, maxLength: maxLength(20) },
      functionExtendName: { notNull: required, maxLength: maxLength(100) },
      functionExtendDescript: { notNull: required, maxLength: maxLength(500) },
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    function initFunctionExtendInfo() {
      // 查整頁資訊
      axios
        .get(`/adm-function-extend/${props.functionExtendId}`)
        .then(({ data }) => {
          if (data && data['functionExtendId']) {
            Object.assign(formDefault, data);
            Object.assign(form, formDefault);
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 返回列表
    function toQueryView() {
      navigateByNameAndParams('adm007', { isReload: isReload.value });
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
            .msgBoxConfirm('是否確認修改?', {
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

    // 送出新增表單
    function postCreation() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      axios
        .post(`/adm-function-extend`, form)
        .then(({ data }) => {
          isReload.value = true;
          navigateByNameAndParams('adm007', { isReload: isReload.value });
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    // 送出修改表單
    function putModify() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      axios
        .put(`/adm-function-extend/${form.functionExtendId}`, form)
        .then(({ data }) => {
          isReload.value = true;
          Object.assign(formDefault, data);
          changeFormStatus(formStatusEnum.INFO);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    function submitDelete() {
      $bvModal
        .msgBoxConfirm('是否確認刪除?', {
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
            deleteFunctionExtend();
          }
        });
    }

    function deleteFunctionExtend() {
      axios
        .delete(`/adm-function-extend/${form.functionExtendId}`)
        .then(() => {
          navigateByNameAndParams('adm007', { isReload: isReload.value });
        })
        .catch(notificationErrorHandler(notificationService));
    }

    const propsFunctionExtendId = toRefs(props).functionExtendId;
    const propsFormStatus = toRefs(props).formStatus;

    watch(
      propsFunctionExtendId,
      () => {
        if (propsFunctionExtendId.value !== '_new') {
          initFunctionExtendInfo();
          if (propsFormStatus.value === formStatusEnum.INFO) {
            changeFormStatus(formStatusEnum.INFO);
          } else {
            changeFormStatus(formStatusEnum.MODIFY);
          }
        } else {
          Object.assign(formDefault, new AdmFunctionExtend().setDefault());
          changeFormStatus(formStatusEnum.CREATE);
        }
      },
      { immediate: true }
    );

    return {
      formStatusEnum,
      formStatusRef,
      $v,
      userUnit,
      toQueryView,
      submitDelete,
      submitForm,
      reset,
      validateState,
      changeFormStatus,
      form,
      formDefault,
    };
  },
  filters: {
    dateTimeFilter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
  },
};
</script>

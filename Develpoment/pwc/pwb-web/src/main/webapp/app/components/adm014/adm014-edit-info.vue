<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0" v-if="formStatusRef === formStatusEnum.INFO">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視假日
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.CREATE">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            新增假日
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.MODIFY">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            修改假日
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm014.holidayDate') + '：'"
                :item="$v.holidayDate"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <i-date-picker type="date" v-model="$v.holidayDate.$model" />
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm014.holidayDate') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.holidayDate.$model | dateFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm014.holidayName') + '：'"
                :item="$v.holidayName"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-input v-model="$v.holidayName.$model" type="text"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm014.holidayName') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.holidayName.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row v-if="formStatusRef !== formStatusEnum.CREATE">
              <i-form-group-check :label="$t('label.adm014.updateUser') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ formDefault.updateUser }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm014.updateTime') + '：'">
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
import { statusFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { handleBack } from '@/router/router';
import { dateTimeFormatter, formatDate } from '@/shared/date/minguo-calendar-utils';
import { AdmHoliday } from '@/shared/model/adm-holiday.model';

export default {
  name: 'Adm014EditInfo',
  props: {
    id: {
      required: true,
    },
    formStatus: {
      type: String,
      required: false,
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
    let formDefault: AdmHoliday = new AdmHoliday().setDefault();
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
      holidayName: { notNull: required, maxLength: maxLength(10) },
      holidayDate: { notNull: required },
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    const notificationService: NotificationService = useNotification();

    function initHolidayInfo() {
      // 查整頁資訊
      axios
        .get(`/adm-holidays/${propsId.value}`)
        .then(({ data }) => {
          if (data && data['id']) {
            data['holidayDate'] = new Date(data['holidayDate']);
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
            deleteHolidayInfo(propsId.value);
          }
        });
    }

    function deleteHolidayInfo(id: number) {
      axios
        .delete(`/adm-holidays/${id}`)
        .then(() => {
          isReload.value = true;
          navigateByNameAndParams('adm014', { isReload: isReload.value });
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 送出新增表單
    function postCreation() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      form.holidayDate = new Date(form.holidayDate);
      axios
        .post(`/adm-holidays`, form)
        .then(({ data }) => {
          isReload.value = true;
          navigateByNameAndParams('adm014', { isReload: isReload.value });
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    }

    // 送出修改表單
    function putModify() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      form.holidayDate = new Date(form.holidayDate);
      axios
        .put(`/adm-holidays/${form.id}`, form)
        .then(({ data }) => {
          isReload.value = true;
          Object.assign(formDefault, data);
          navigateByNameAndParams('adm014', { isReload: isReload });
          changeFormStatus(formStatusEnum.INFO);
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    }

    const propsId = toRefs(props).id;
    const propsFormStatus = toRefs(props).formStatus;

    watch(
      propsId,
      () => {
        if (propsId.value !== '_new') {
          initHolidayInfo();
          if (propsFormStatus.value === formStatusEnum.INFO) {
            changeFormStatus(formStatusEnum.INFO);
          } else {
            changeFormStatus(formStatusEnum.MODIFY);
          }
        } else {
          Object.assign(formDefault, new AdmHoliday().setDefault());
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
  filters: {
    statusFilter: (value: string) => statusFormatter(value),
    dateFilter: (value: any) => formatDate(value, '/'),
    dateTimeFilter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
  },
};
</script>

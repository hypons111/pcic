<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0" v-if="formStatusRef === formStatusEnum.INFO">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視系統參數
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.CREATE">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            新增系統參數
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.MODIFY">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            修改系統參數
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm011.sysName') + '：'"
                :item="$v.sysName"
                v-if="formStatusRef === formStatusEnum.CREATE"
              >
                <b-form-input v-model="$v.sysName.$model" type="text" :state="validateState($v.sysName)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm011.sysName') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.sysName.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm011.sysType') + '：'"
                :item="$v.sysType"
                v-if="formStatusRef === formStatusEnum.CREATE"
              >
                <b-form-input v-model="$v.sysType.$model" type="text" :state="validateState($v.sysType)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm011.sysType') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.sysType.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm011.sysValue') + '：'"
                :item="$v.sysValue"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-input v-model="$v.sysValue.$model" type="text" :state="validateState($v.sysValue)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm011.sysValue') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.sysValue.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm011.descript') + '：'"
                :item="$v.descript"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-input v-model="$v.descript.$model" type="text" :state="validateState($v.descript)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm011.descript') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.descript.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm011.status') + '：'" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-select :options="options.statusOpts" v-model="$v.status.$model" :state="validateState($v.status)"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm011.status') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.status.$model | statusFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row v-if="formStatusRef !== formStatusEnum.CREATE">
              <i-form-group-check :label="$t('label.adm011.updateUser') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ formDefault.updateUser }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm011.updateTime') + '：'">
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
import { AdmSysParameter } from '@/shared/model/adm-sys-parameter.model';
import { handleBack } from '@/router/router';
import { dateTimeFormatter } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Adm011EditInfo',
  props: {
    admSysParameter: {
      type: Object as () => AdmSysParameter,
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
    let formDefault: AdmSysParameter = new AdmSysParameter().setDefault();
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
      sysName: { notNull: required, maxLength: maxLength(20) },
      sysType: { notNull: required, maxLength: maxLength(20) },
      sysValue: { notNull: required, maxLength: maxLength(50) },
      descript: { notNull: required, maxLength: maxLength(500) },
      status: { notNull: required },
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    const notificationService: NotificationService = useNotification();

    const options = reactive({
      statusOpts: [
        { value: '', text: '請選擇' },
        { value: 'N', text: '停用' },
        { value: 'Y', text: '啟用' },
      ],
    });

    function initSysParameterInfo() {
      // 查整頁資訊
      axios
        .get(`/adm-sys-parameters/${propsAdmSysParameter.value.sysName}/${propsAdmSysParameter.value.sysType}`)
        .then(({ data }) => {
          if (data && data['sysName'] && data['sysType']) {
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
            deleteSysParameterInfo(propsAdmSysParameter.value.sysName, propsAdmSysParameter.value.sysType);
          }
        });
    }

    function deleteSysParameterInfo(sysName: string, sysType: string) {
      axios
        .delete(`/adm-sys-parameters/${sysName}/${sysType}`)
        .then(() => {
          isReload.value = true;
          navigateByNameAndParams('adm011', { isReload: isReload.value });
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 送出新增表單
    function postCreation() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      axios
        .post(`/adm-sys-parameters`, form)
        .then(({ data }) => {
          isReload.value = true;
          navigateByNameAndParams('adm011', { isReload: isReload.value });
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    // 送出修改表單
    function putModify() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      axios
        .put(`/adm-sys-parameters/${form.sysName}/${form.sysType}`, form)
        .then(({ data }) => {
          isReload.value = true;
          Object.assign(formDefault, data);
          navigateByNameAndParams('adm011', { isReload: isReload });
          changeFormStatus(formStatusEnum.INFO);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    const propsAdmSysParameter = toRefs(props).admSysParameter;
    const propsFormStatus = toRefs(props).formStatus;

    watch(
      propsAdmSysParameter,
      () => {
        if (propsAdmSysParameter.value.sysName !== '_new' && propsAdmSysParameter.value.sysType !== '_new') {
          initSysParameterInfo();
          if (propsFormStatus.value === formStatusEnum.INFO) {
            changeFormStatus(formStatusEnum.INFO);
          } else {
            changeFormStatus(formStatusEnum.MODIFY);
          }
        } else {
          Object.assign(formDefault, new AdmSysParameter().setDefault());
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
  filters: {
    statusFilter: (value: string) => statusFormatter(value),
    dateTimeFilter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
  },
};
</script>

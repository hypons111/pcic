<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0" v-if="formStatusRef === formStatusEnum.INFO">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視功能
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.CREATE">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            新增功能
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.MODIFY">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            修改功能
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm006.functionId') + '：'"
                :item="$v.functionId"
                v-if="formStatusRef === formStatusEnum.CREATE"
              >
                <b-form-input v-model="$v.functionId.$model" type="text" :state="validateState($v.functionId)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm006.functionId') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.functionId.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm006.functionName') + '：'"
                :item="$v.functionName"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-input v-model="$v.functionName.$model" type="text" :state="validateState($v.functionName)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm006.functionName') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.functionName.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm006.functionDescript') + '：'"
                :item="$v.functionDescript"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-textarea
                  v-model="$v.functionDescript.$model"
                  :state="validateState($v.functionDescript)"
                  rows="3"
                ></b-form-textarea>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm006.functionDescript') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.functionDescript.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm006.functionPath') + '：'"
                :item="$v.functionPath"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-textarea v-model="$v.functionPath.$model" :state="validateState($v.functionPath)" rows="3"></b-form-textarea>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm006.functionPath') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.functionPath.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm006.functionCategory') + '：'"
                :item="$v.functionCategory"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-select
                  :options="options.functionCategoryOpts"
                  v-model="$v.functionCategory.$model"
                  :state="validateState($v.functionCategory)"
                ></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm006.functionCategory') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.functionCategory.$model | functionCategoryFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm006.sysCategory') + '：'"
                :item="$v.sysCategory"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-select
                  :options="options.sysCategoryOpts"
                  v-model="$v.sysCategory.$model"
                  :state="validateState($v.sysCategory)"
                ></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm006.sysCategory') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.sysCategory.$model | sysCategoryFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm006.sortNo') + '：'" :item="$v.sortNo" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model="$v.sortNo.$model" type="text" :state="validateState($v.sortNo)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm006.sortNo') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.sortNo.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm006.masterFunctionId') + '：'"
                :item="$v.masterFunctionId"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-input v-model="$v.masterFunctionId.$model" type="text" :state="validateState($v.masterFunctionId)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm006.masterFunctionId') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.masterFunctionId.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm006.status') + '：'" :item="$v.status" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-select :options="options.statusOpts" v-model="$v.status.$model" :state="validateState($v.status)"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm006.status') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.status.$model | statusFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row v-if="formStatusRef !== formStatusEnum.CREATE">
              <i-form-group-check :label="$t('label.adm006.updateUser') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ formDefault.updateUser }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm006.updateTime') + '：'">
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
import { maxLength, minValue, required } from '@/shared/validators';
import { accountStore } from '@/shared/config/store/account-store';
import { functionCategoryFormatter, sysCategoryFormatter, statusFormatter } from '@/shared/formatter/common';
import { AdmFunction } from '@/shared/model/adm-function.model';
import { navigateByNameAndParams } from '@/router/router';
import { dateTimeFormatter } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Adm006EditInfo',
  props: {
    functionId: {
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
    let formDefault: AdmFunction = new AdmFunction().setDefault();
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
      functionId: { notNull: required, maxLength: maxLength(20) },
      functionName: { notNull: required, maxLength: maxLength(100) },
      functionDescript: { notNull: required, maxLength: maxLength(500) },
      functionPath: { notNull: required, maxLength: maxLength(1000) },
      functionCategory: { notNull: required },
      sysCategory: { notNull: required },
      sortNo: { notNull: required, minValue: minValue(0), maxLength: maxLength(255) },
      masterFunctionId: { notNull: required, maxLength: maxLength(20) },
      status: { notNull: required },
      updateUser: {},
      updateTime: {},
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
      functionCategoryOpts: [
        { value: '0', text: '選單' },
        { value: '1', text: '可執行功能' },
      ],
      sysCategoryOpts: [
        { value: '', text: '請選擇' },
        { value: '1', text: '工程雲服務網' },
        { value: '2', text: '工程雲後臺管理' },
        { value: '3', text: '全球資訊網' },
        { value: '4', text: '全球資訊網後臺管理' },
      ],
    });

    function initFunctionInfo() {
      // 查整頁資訊
      axios
        .get(`/adm-function/${props.functionId}`)
        .then(({ data }) => {
          if (data && data['functionId']) {
            Object.assign(formDefault, data);
            Object.assign(form, formDefault);
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 返回列表
    function toQueryView() {
      navigateByNameAndParams('adm006', { isReload: false });
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

    // 送出新增表單
    function postCreation() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      axios
        .post(`/adm-function`, form)
        .then(({ data }) => {
          isReload.value = true;
          navigateByNameAndParams('adm006', { isReload: isReload.value });
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
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

    // 送出修改表單
    function putModify() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      axios
        .put(`/adm-function/${form.functionId}`, form)
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
            deleteFunction();
          }
        });
    }

    function deleteFunction() {
      axios
        .delete(`/adm-function/${form.functionId}`)
        .then(() => {
          navigateByNameAndParams('adm006', { isReload: false });
        })
        .catch(notificationErrorHandler(notificationService));
    }

    const propsFunctionId = toRefs(props).functionId;
    const propsFormStatus = toRefs(props).formStatus;

    watch(
      propsFunctionId,
      () => {
        if (propsFunctionId.value !== '_new') {
          initFunctionInfo();
          if (propsFormStatus.value === formStatusEnum.INFO) {
            changeFormStatus(formStatusEnum.INFO);
          } else {
            changeFormStatus(formStatusEnum.MODIFY);
          }
        } else {
          Object.assign(formDefault, new AdmFunction().setDefault());
          changeFormStatus(formStatusEnum.CREATE);
        }
      },
      { immediate: true }
    );

    return {
      formStatusEnum,
      formStatusRef,
      options,
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
    statusFilter: (value: string) => statusFormatter(value),
    functionCategoryFilter: (value: string) => functionCategoryFormatter(value),
    sysCategoryFilter: (value: string) => sysCategoryFormatter(value),
    dateTimeFilter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
  },
};
</script>

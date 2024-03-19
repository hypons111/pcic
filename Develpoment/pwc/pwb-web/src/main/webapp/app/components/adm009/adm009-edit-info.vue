<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0" v-if="formStatusRef === formStatusEnum.INFO">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視批次服務
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.CREATE">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            新增批次服務
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.MODIFY">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            修改批次服務
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm009.batchServiceNo') + '：'"
                :item="$v.batchServiceNo"
                v-if="formStatusRef === formStatusEnum.CREATE"
              >
                <b-form-input v-model="$v.batchServiceNo.$model" type="text" :state="validateState($v.batchServiceNo)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm009.batchServiceNo') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.batchServiceNo.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm009.batchServiceName') + '：'"
                :item="$v.batchServiceName"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-input v-model="$v.batchServiceName.$model" type="text" :state="validateState($v.batchServiceName)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm009.batchServiceName') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.batchServiceName.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm009.batchServiceCategory') + '：'"
                :item="$v.batchServiceCategory"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-input v-model="$v.batchServiceCategory.$model" type="text" :state="validateState($v.batchServiceCategory)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm009.batchServiceCategory') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.batchServiceCategory.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm009.batchServiceProg') + '：'"
                :item="$v.batchServiceProg"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-input v-model="$v.batchServiceProg.$model" type="text" :state="validateState($v.batchServiceProg)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm009.batchServiceProg') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.batchServiceProg.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm009.isRegular') + '：'"
                :item="$v.isRegular"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-select
                  :options="options.isRegularOpts"
                  v-model="$v.isRegular.$model"
                  :state="validateState($v.isRegular)"
                ></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm009.isRegular') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.isRegular.$model | yesNoFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm009.batchServiceProgMethod') + '：'"
                :item="$v.batchServiceProgMethod"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-input v-model="$v.batchServiceProgMethod.$model" type="text" :state="validateState($v.batchServiceProgMethod)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm009.batchServiceProgMethod') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.batchServiceProgMethod.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm009.batchFrequency') + '：'"
                :item="$v.batchFrequency"
                v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-select
                  :options="options.batchFrequencyOpts"
                  v-model="$v.batchFrequency.$model"
                  :state="validateState($v.batchFrequency)"
                ></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm009.batchFrequency') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.batchFrequency.$model | batchFrequencyFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm009.batchServiceStatus') + '：'"
                :item="$v.batchServiceStatus"
                v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-select
                  :options="options.statusOpts"
                  v-model="$v.batchServiceStatus.$model"
                  :state="validateState($v.batchServiceStatus)"
                ></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm009.batchServiceStatus') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.batchServiceStatus.$model | statusFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm009.batchExeStatus') + '：'"
                :item="$v.batchExeStatus"
                v-if="formStatusRef === formStatusEnum.INFO"
              >
                <div class="h-100 d-flex align-items-center">
                  {{ $v.batchExeStatus.$model | executeFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row v-if="formStatusRef !== formStatusEnum.CREATE">
              <i-form-group-check :label="$t('label.adm009.updateUser') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.updateUser }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm009.updateTime') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.updateTime | dateTimeFilter }}
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
import {cronFornatter, executeFormatter, statusFormatter, yesNoFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { handleBack } from '@/router/router';
import { dateTimeFormatter } from '@/shared/date/minguo-calendar-utils';
import { AdmBatchService } from '@/shared/model/adm-batch-service.model';

export default {
  name: 'Adm009EditInfo',
  props: {
    batchServiceNo: {
      type: String,
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
    let formDefault: AdmBatchService = new AdmBatchService().setDefault();
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
      batchServiceNo: { notNull: required, maxLength: maxLength(20) },
      batchServiceName: { notNull: required, maxLength: maxLength(20) },
      batchServiceCategory: { notNull: required },
      batchServiceProg: { notNull: required, maxLength: maxLength(150) },
      batchServiceProgMethod: { notNull: required, maxLength: maxLength(100) },
      isRegular: { notNull: required },
      batchServiceStatus: { notNull: required },
      batchExeStatus: {},
      batchFrequency: { notNull: required },
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    const notificationService: NotificationService = useNotification();

    const options = reactive({
      isRegularOpts: [
        { value: '', text: '請選擇' },
        { value: 'Y', text: '是' },
        { value: 'N', text: '否' },
      ],
      statusOpts: [
        { value: '', text: '請選擇' },
        { value: 'N', text: '停用' },
        { value: 'Y', text: '啟用' },
      ],
      batchExeStatusOpts: [
        { value: '', text: '無' },
        { value: 'Y', text: '成功' },
        { value: 'N', text: '失敗' },
        { value: 'S', text: '執行中' },
        { value: 'T', text: '未執行' },
      ],
      batchFrequencyOpts: [
        { value: '', text: '請選擇' },
        { value: '0 0 0/1 * * ?', text: '每1小時執行' },
        { value: '0 0 0/2 * * ?', text: '每2小時執行' },
        { value: '0 0 0/6 * * ?', text: '每6小時執行' },
        { value: '0 0 0 * * ?', text: '每日00:00執行' },
        { value: '0 0 1 * * ?', text: '每日01:00執行' },
        { value: '0 0 2 * * ?', text: '每日02:00執行' },
        { value: '0 0 3 * * ?', text: '每日03:00執行' },
        { value: '0 0 4 * * ?', text: '每日04:00執行' },
        { value: '0 0 5 * * ?', text: '每日05:00執行' },
        { value: '0 0 6 * * ?', text: '每日06:00執行' },
        { value: '0 0 7 * * ?', text: '每日07:00執行' },
        { value: '0 0 8 * * ?', text: '每日08:00執行' },
        { value: '0 0 9 * * ?', text: '每日09:00執行' },
        { value: '0 0 10 * * ?', text: '每日10:00執行' },
        { value: '0 0 11 * * ?', text: '每日11:00執行' },
        { value: '0 0 12 * * ?', text: '每日12:00執行' },
        { value: '0 0 13 * * ?', text: '每日13:00執行' },
        { value: '0 0 14 * * ?', text: '每日14:00執行' },
        { value: '0 0 15 * * ?', text: '每日15:00執行' },
        { value: '0 0 16 * * ?', text: '每日16:00執行' },
        { value: '0 0 17 * * ?', text: '每日17:00執行' },
        { value: '0 0 18 * * ?', text: '每日18:00執行' },
        { value: '0 0 19 * * ?', text: '每日19:00執行' },
        { value: '0 0 20 * * ?', text: '每日20:00執行' },
        { value: '0 0 21 * * ?', text: '每日21:00執行' },
        { value: '0 0 22 * * ?', text: '每日22:00執行' },
        { value: '0 0 23 * * ?', text: '每日23:00執行' },
      ],
    });

    function initBatchServiceInfo() {
      // 查整頁資訊
      axios
        .get(`/adm-batch-services/${propsBatchServiceNo.value}`)
        .then(({ data }) => {
          if (data && data['batchServiceNo']) {
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
            deleteBatchServiceInfo(propsBatchServiceNo.value);
          }
        });
    }

    function deleteBatchServiceInfo(batchServiceNo: string) {
      axios
        .delete(`/adm-batch-services/${batchServiceNo}`)
        .then(() => {
          isReload.value = true;
          navigateByNameAndParams('adm009', { isReload: isReload.value });
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 送出新增表單
    function postCreation() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      axios
        .post(`/adm-batch-services`, form)
        .then(({ data }) => {
          isReload.value = true;
          navigateByNameAndParams('adm009', { isReload: isReload.value });
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    // 送出修改表單
    function putModify() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      axios
        .put(`/adm-batch-services/${form.batchServiceNo}`, form)
        .then(({ data }) => {
          isReload.value = true;
          Object.assign(formDefault, form);
          changeFormStatus(formStatusEnum.INFO);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    const propsBatchServiceNo = toRefs(props).batchServiceNo;
    const propsFormStatus = toRefs(props).formStatus;

    watch(
      propsBatchServiceNo,
      () => {
        if (propsBatchServiceNo.value !== '_new') {
          initBatchServiceInfo();
          if (propsFormStatus.value === formStatusEnum.INFO) {
            changeFormStatus(formStatusEnum.INFO);
          } else {
            changeFormStatus(formStatusEnum.MODIFY);
          }
        } else {
          Object.assign(formDefault, new AdmBatchService().setDefault());
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
    yesNoFilter: (value: string) => yesNoFormatter(value),
    executeFilter: (value: string) => executeFormatter(value),
    batchFrequencyFilter: (value:string) => cronFornatter(value)
  },
};
</script>

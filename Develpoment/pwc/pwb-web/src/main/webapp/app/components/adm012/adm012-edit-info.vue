<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0" v-if="formStatusRef === formStatusEnum.INFO">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視系統代碼
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.CREATE">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            新增系統代碼
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.MODIFY">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            修改系統代碼
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm012.moduleType') + '：'"
                :item="$v.moduleType"
                v-if="formStatusRef === formStatusEnum.CREATE"
              >
                <b-form-input v-model="$v.moduleType.$model" type="text" :state="validateState($v.moduleType)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.moduleType') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.moduleType.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm012.dataType') + '：'"
                :item="$v.dataType"
                v-if="formStatusRef === formStatusEnum.CREATE"
              >
                <b-form-select :options="options.dataTypeOpts" v-model="$v.dataType.$model"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.dataType') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.dataType.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm012.dataKey') + '：'"
                :item="$v.dataKey"
                v-if="formStatusRef === formStatusEnum.CREATE"
              >
                <b-form-input v-model="$v.dataKey.$model" type="text" :state="validateState($v.dataKey)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.dataKey') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.dataKey.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.sortNo') + '：'" :item="$v.sortNo" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model="$v.sortNo.$model" type="text"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.sortNo') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.sortNo.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm012.value') + '：'" :item="$v.value" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model="$v.value.$model" type="text" :state="validateState($v.value)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.value') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.value.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.remark') + '：'" :item="$v.remark" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model="$v.remark.$model" type="text"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.remark') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.remark.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm012.codeDescript') + '：'"
                :item="$v.codeDescript"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-textarea v-model="$v.codeDescript.$model" type="text" :state="validateState($v.codeDescript)"></b-form-textarea>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.codeDescript') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.codeDescript.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm012.enCodeDescript') + '：'"
                :item="$v.enCodeDescript"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-textarea v-model="$v.enCodeDescript.$model" type="text"></b-form-textarea>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.enCodeDescript') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.enCodeDescript.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm012.isValid') + '：'"
                :item="$v.isValid"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-select :options="options.statusOpts" v-model="$v.isValid.$model" :state="validateState($v.isValid)"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.isValid') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.isValid.$model | statusFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm012.isModify') + '：'"
                :item="$v.isModify"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-select
                  :options="options.isModifyOpts"
                  v-model="$v.isModify.$model"
                  :state="validateState($v.isModify)"
                ></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.isModify') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.isModify.$model | yesNoFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm012.attr1') + '：'" :item="$v.attr1" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-textarea v-model="$v.attr1.$model" type="text" :state="validateState($v.attr1)"></b-form-textarea>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.attr1') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.attr1.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.attr2') + '：'" :item="$v.attr2" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-textarea v-model="$v.attr2.$model" type="text"></b-form-textarea>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.attr2') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.attr2.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-row v-if="formStatusRef !== formStatusEnum.CREATE">
              <b-col md="2" class="{ 'form-check-with-marginB': true, 'col-6': false, form-single-row-style }">{{
                $t('label.adm012.validFromDate')
              }}</b-col>
              <b-col md="8">{{ formDefault.validFromDate | dateFilter }}</b-col>
            </b-row>

            <b-form-row v-if="formStatusRef !== formStatusEnum.CREATE">
              <i-form-group-check :label="$t('label.adm012.createUser') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ formDefault.createUser }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.createTime') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ formDefault.createTime | dateTimeFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row v-if="formStatusRef !== formStatusEnum.CREATE">
              <i-form-group-check :label="$t('label.adm012.updateUser') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ formDefault.updateUser }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm012.updateTime') + '：'">
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
import { computed, onMounted, reactive, ref, toRefs, watch } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useBvModal } from '@/shared/modal';
import { englishNumberSymbol, maxLength, maxValue, minValue, required } from '@/shared/validators';
import { accountStore } from '@/shared/config/store/account-store';
import { statusFormatter, yesNoFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { handleBack } from '@/router/router';
import { AdmSysCode } from '@/shared/model/adm-sys-code.model';
import { dateFormatter, dateTimeFormatter } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Adm012EditInfo',
  props: {
    admSysCode: {
      type: Object as () => AdmSysCode,
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
    let formDefault: AdmSysCode = new AdmSysCode().setDefault();
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
      moduleType: { notNull: required, maxLength: maxLength(3) },
      dataType: { notNull: required, maxLength: maxLength(20) },
      dataKey: { notNull: required, maxLength: maxLength(20) },
      sortNo: { notNull: required, maxValue: maxValue(255), minValue: minValue(0) },
      value: { notNull: required, maxLength: maxLength(100) },
      codeDescript: { maxLength: maxLength(500) },
      enCodeDescript: { maxLength: maxLength(1000), limit: englishNumberSymbol(computed(() => form.enCodeDescript)) },
      remark: { maxLength: maxLength(500) },
      isValid: { notNull: required },
      isModify: { notNull: required },
      attr1: { maxLength: maxLength(50) },
      attr2: { maxLength: maxLength(50) },
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    const allDataTypeOpts = ref([]);

    onMounted(() => {
      getAllDataTypeOpts();
    });

    function getAllDataTypeOpts() {
      axios
        .get('adm-sys-codes/get-data-type-opts')
        .then(({ data }) => {
          allDataTypeOpts.value.splice(0, allDataTypeOpts.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    const notificationService: NotificationService = useNotification();

    const options = reactive({
      moduleTypeOpts: [
        { value: '', text: '請選擇' },
        { value: 'M01', text: '模組別1' },
        { value: 'M02', text: '模組別2' },
        { value: 'M03', text: '模組別3' },
      ],
      dataTypeOpts: [],
      statusOpts: [
        { value: '', text: '請選擇' },
        { value: false, text: '停用' },
        { value: true, text: '啟用' },
      ],
      isModifyOpts: [
        { value: '', text: '請選擇' },
        { value: false, text: '否' },
        { value: true, text: '是' },
      ],
    });

    function initSysCode() {
      // 查整頁資訊
      axios
        .get(`/adm-sys-codes/${propsAdmSysCode.value.moduleType}/${propsAdmSysCode.value.dataType}/${propsAdmSysCode.value.dataKey}`)
        .then(({ data }) => {
          if (data && data['moduleType'] && data['dataType'] && data['dataKey']) {
            Object.assign(formDefault, data);
            Object.assign(form, formDefault);
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 返回列表
    function toQueryView() {
      // navigateByNameAndParams('adm012', { isReload: isReload.value });
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
            deleteSysCodeInfo(propsAdmSysCode.value.moduleType, propsAdmSysCode.value.dataType, propsAdmSysCode.value.dataKey);
          }
        });
    }

    function deleteSysCodeInfo(moduleType: string, dataType: string, dataKey: string) {
      axios
        .delete(`/adm-sys-codes/${moduleType}/${dataType}/${dataKey}`)
        .then(() => {
          isReload.value = true;
          navigateByNameAndParams('adm012', { isReload: isReload.value });
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 送出新增表單
    function postCreation() {
      if (form.isValid) {
        form.validFromDate = new Date();
      }
      form.createUser = 'SYS';
      form.createTime = new Date();
      axios
        .post(`/adm-sys-codes`, form)
        .then(({ data }) => {
          isReload.value = true;
          navigateByNameAndParams('adm012', { isReload: isReload.value });
          // notificationService.success(`系統代碼:${data.moduleType}，已新增。`);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    // 送出修改表單
    function putModify() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      if (!formDefault.isValid && form.isValid) {
        form.validFromDate = new Date();
      } else {
        form.validFromDate = null;
      }
      axios
        .put(`/adm-sys-codes/${form.moduleType}/${form.dataType}/${form.dataKey}`, form)
        .then(({ data }) => {
          isReload.value = true;
          Object.assign(formDefault, data);
          navigateByNameAndParams('adm012', { isReload: isReload });
          changeFormStatus(formStatusEnum.INFO);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    const propsAdmSysCode = toRefs(props).admSysCode;
    const propsFormStatus = toRefs(props).formStatus;

    watch(
      propsAdmSysCode,
      () => {
        if (
          propsAdmSysCode.value.moduleType !== '_new' &&
          propsAdmSysCode.value.dataType !== '_new' &&
          propsAdmSysCode.value.dataKey !== '_new'
        ) {
          initSysCode();
          if (propsFormStatus.value === formStatusEnum.INFO) {
            changeFormStatus(formStatusEnum.INFO);
          } else {
            changeFormStatus(formStatusEnum.MODIFY);
          }
        } else {
          Object.assign(formDefault, new AdmSysCode().setDefault());
          Object.assign(form, formDefault);
          changeFormStatus(formStatusEnum.CREATE);
        }
      },
      { immediate: true }
    );

    watch(allDataTypeOpts, () => {
      options.dataTypeOpts = allDataTypeOpts.value.map(data => {
        return { text: data['dataType'], value: data['dataType'] };
      });
      options.dataTypeOpts.unshift({ text: '請選擇', value: '' });
    });

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
    yesNoFilter: (value: string) => yesNoFormatter(value),
    dateTimeFilter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
    dateFilter: (value: any) => dateFormatter(value, '民國年月日', 'YYY/MM/DD'),
  },
};
</script>

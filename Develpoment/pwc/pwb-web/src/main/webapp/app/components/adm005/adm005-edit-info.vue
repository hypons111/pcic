<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0" v-if="formStatusRef === formStatusEnum.INFO">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視角色
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.CREATE">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            新增角色
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.MODIFY">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            修改角色
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm005.roleId') + '：'"
                :item="$v.roleId"
                v-if="formStatusRef === formStatusEnum.CREATE"
              >
                <b-form-input v-model="$v.roleId.$model" type="text" :state="validateState($v.roleId)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm005.roleId') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.roleId.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm005.roleName') + '：'"
                :item="$v.roleName"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-input v-model="$v.roleName.$model" type="text" :state="validateState($v.roleName)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm005.roleName') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.roleName.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check
                :label="$t('label.adm005.roleDescript') + '：'"
                :item="$v.roleDescript"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-textarea v-model="$v.roleDescript.$model" :state="validateState($v.roleDescript)" rows="3"></b-form-textarea>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm005.roleDescript') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.roleDescript.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.adm005.businessCategory') + '：'"
                :item="$v.businessCategory"
                v-if="formStatusRef !== formStatusEnum.INFO"
              >
                <b-form-select
                  :options="options.businessCategoryOpts"
                  v-model="$v.businessCategory.$model"
                  :state="validateState($v.businessCategory)"
                ></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm005.businessCategory') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.businessCategory.$model | businessCategoryFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-group
              :label="$t('label.adm005.admFunctionDTOs') + '：'"
              :class="{ 'form-check-with-marginB': true, 'col-6': false }"
              :label-cols="2"
              :content-cols="10"
              :label-align="'right'"
              label-class="label"
            >
              <b-table
                :empty-text="'尚未選取任何功能'"
                :items="$v.admFunctionDTOs.$model"
                :fields="formInnerTableFields.function"
                show-empty
                class="table table-sm"
                head-variant="light"
              >
                <template #cell(action)="data">
                  <i-button size="sm" type="trash" @click="deleteFunction(data.item)"></i-button>
                </template>
              </b-table>
            </b-form-group>

            <b-button-toolbar v-show="formStatusRef !== formStatusEnum.INFO" class="offset-md-2 mb-2">
              <i-button size="sm" type="file-earmark-plus" v-b-modal.function-modal></i-button>
            </b-button-toolbar>

            <b-form-row v-if="formStatusRef !== formStatusEnum.CREATE">
              <i-form-group-check :label="$t('label.adm005.updateUser') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ formDefault.updateUser }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm005.updateTime') + '：'">
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
              <i-button class="mr-1" type="x-circle" @click="resetAll()"></i-button>
              <i-button v-if="formStatusRef === formStatusEnum.MODIFY" class="mr-1" type="arrow-left" @click="back()"></i-button>
              <i-button v-if="formStatusRef === formStatusEnum.CREATE" class="mr-1" type="arrow-left" @click="toQueryView()"></i-button>
            </b-button-toolbar>
          </b-form>
        </div>
      </div>
    </section>
    <function-query @addFunction="addFunction($event)"></function-query>
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
import { statusFormatter, businessCategoryFormatter } from '@/shared/formatter/common';
import { AdmRole } from '@/shared/model/adm-role.model';
import { AdmFunction } from '@/shared/model/adm-function.model';
import { navigateByNameAndParams } from '@/router/router';
import { dateTimeFormatter } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Adm005EditInfo',
  props: {
    roleId: {
      type: String,
      required: false,
    },
    formStatus: {
      type: String,
      required: false,
    },
  },
  components: {
    FunctionQuery: () => import('@/components/adm005/function-query.vue'),
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
    let formDefault: AdmRole = new AdmRole().setDefault();
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
      roleId: { notNull: required },
      roleName: { notNull: required, maxLength: maxLength(20) },
      roleDescript: { notNull: required, maxLength: maxLength(500) },
      businessCategory: { notNull: required },
      roleFunctionList: {},
      admFunctionDTOs: {},
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 表單內部表格欄位
    const formInnerTableFields = reactive({
      function: [
        { key: 'functionId', label: '功能代碼', sortable: false, tdClass: 'text-left', thStyle: { width: '8%' } },
        { key: 'functionName', label: '功能名稱', sortable: false, tdClass: 'text-left', thStyle: { width: '12%' } },
        { key: 'functionDescript', label: '功能描述', sortable: false, tdClass: 'text-left', thStyle: { width: '25%' } },
        { key: 'none', label: '程式控制狀態使用' },
      ],
    });

    // 表單內部表格內容
    const formInnerTable = reactive({
      functionDefault: [],
    });

    const options = reactive({
      businessCategoryOpts: [
        { value: '', text: '請選擇' },
        { value: '1', text: '技師顧問' },
        { value: '2', text: '價格資料庫' },
        { value: '3', text: '技術資料庫' },
        { value: '4', text: '標案管理' },
      ],
    });

    function initRoleFunctionInfo() {
      // 查整頁資訊
      axios
        .get(`/adm-role/${props.roleId}`)
        .then(({ data }) => {
          if (data && data['roleId']) {
            if (typeof data.admFunctionDTOs !== 'undefined') {
              formInnerTable.functionDefault = JSON.parse(JSON.stringify(data.admFunctionDTOs));
            }
            Object.assign(formDefault, data);
            formDefault.roleFunctionListDisplay = data.roleFunctionList.split(',');
            formDefault.roleFunctionListDisplay.pop();
            Object.assign(form, formDefault);
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 暫時刪除功能
    function deleteFunction(target: AdmFunction) {
      form.admFunctionDTOs = form.admFunctionDTOs.filter((source: AdmFunction) => source.functionId !== target.functionId);
    }

    // 暫時新增功能
    function addFunction(admFunction: AdmFunction) {
      if (!form.admFunctionDTOs.find(existFunction => existFunction.functionId === admFunction.functionId)) {
        let target: AdmFunction = new AdmFunction(admFunction.functionId, admFunction.functionName, admFunction.functionDescript);
        form.admFunctionDTOs.push(target);
      }
    }

    // 返回列表
    function toQueryView() {
      navigateByNameAndParams('adm005', { isReload: false });
    }

    function back() {
      initRoleFunctionInfo();
      changeFormStatus(formStatusEnum.INFO);
    }

    function resetAll() {
      if (formStatusRef.value === formStatusEnum.MODIFY) {
        initRoleFunctionInfo();
        changeFormStatus(formStatusEnum.MODIFY);
      } else if (formStatusRef.value === formStatusEnum.CREATE) {
        reset();
        changeFormStatus(formStatusEnum.CREATE);
      }
    }

    // 變更表單操作狀態
    function changeFormStatus(status: formStatusEnum) {
      formStatusRef.value = status;
      const actionField = {
        key: 'action',
        label: '動作',
        sortable: false,
        thClass: 'text-center',
        tdClass: 'text-center',
        thStyle: { width: '8%' },
      };
      const noneActionField = {
        key: 'action',
        label: '動作',
        sortable: false,
        thClass: 'd-none',
        tdClass: 'd-none',
        thStyle: { width: '8%' },
      };
      // 依表單操作狀態顯示或隱藏移除欄位
      if (status === formStatusEnum.INFO) {
        formInnerTableFields.function.pop();
        formInnerTableFields.function.push(noneActionField);
      } else {
        formInnerTableFields.function.pop();
        formInnerTableFields.function.push(actionField);
      }
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
            deleteRole();
          }
        });
    }

    function deleteRole() {
      axios
        .delete(`/adm-role/${form.roleId}`)
        .then(() => {
          navigateByNameAndParams('adm005', { isReload: isReload.value });
        })
        .catch(notificationErrorHandler(notificationService));
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
                transferFunctionToString();
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
          // console.log(form);
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
                transferFunctionToString();
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

    function transferFunctionToString() {
      // 將暫存的admFunctionDTOs內的所選功能functionId轉為字串放進roleFunctionList
      form.roleFunctionList = form.admFunctionDTOs.map(functionItem => functionItem.functionId).join(',');
    }

    // 送出新增表單
    function postCreation() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      axios
        .post(`/adm-role`, form)
        .then(({ data }) => {
          isReload.value = true;
          navigateByNameAndParams('adm005', { isReload: isReload.value });
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    // 送出修改表單
    function putModify() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      axios
        .put(`/adm-role/${form.roleId}`, form)
        .then(({ data }) => {
          isReload.value = true;
          Object.assign(formDefault, form);
          changeFormStatus(formStatusEnum.INFO);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    const propsRoleId = computed(() => props.roleId);
    const propsFormStatus = toRefs(props).formStatus;

    watch(
      propsRoleId,
      () => {
        if (propsRoleId.value !== '_new') {
          initRoleFunctionInfo();
          if (propsFormStatus.value === formStatusEnum.INFO) {
            changeFormStatus(formStatusEnum.INFO);
          } else {
            changeFormStatus(formStatusEnum.MODIFY);
          }
        } else {
          Object.assign(formDefault, new AdmRole().setDefault());
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
      formDefault,
      toQueryView,
      back,
      resetAll,
      submitDelete,
      submitForm,
      reset,
      validateState,
      changeFormStatus,
      form,
      deleteFunction,
      addFunction,
      formInnerTableFields,
      formInnerTable,
    };
  },
  filters: {
    statusFilter: (value: string) => statusFormatter(value),
    businessCategoryFilter: (value: string) => businessCategoryFormatter(value),
    dateTimeFilter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
  },
};
</script>

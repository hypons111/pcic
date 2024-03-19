<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0" v-if="formStatusRef === formStatusEnum.INFO">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視角色
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check :label="$t('label.adm005.roleId') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.roleId.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm005.roleName') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.roleName.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm005.roleDescript') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.roleDescript.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm005.businessCategory') + '：'">
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
              </b-table>
            </b-form-group>

            <b-form-row>
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

            <b-button-toolbar class="float-right">
              <i-button class="mr-1" type="arrow-left" @click="toQueryView()"></i-button>
            </b-button-toolbar>
          </b-form>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, reactive, ref, watch } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useBvModal } from '@/shared/modal';
import { maxLength, required } from '@/shared/validators';
import { accountStore } from '@/shared/config/store/account-store';
import { statusFormatter, businessCategoryFormatter } from '@/shared/formatter/common';
import { AdmRole } from '@/shared/model/adm-role.model';
import { handleBack } from '@/router/router';
import { dateTimeFormatter } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Adm003RoleInfo',
  props: {
    userId: {
      type: String,
      required: true,
    },
    roleId: {
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
        // { key: 'functionExtend', label: '延伸功能', sortable: false, tdClass: 'text-left', thStyle: { width: '40%' } },
        { key: 'none', label: '程式控制狀態使用' },
      ],
    });

    // 表單內部表格內容
    const formInnerTable = reactive({
      functionDefault: [],
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

    // 返回列表
    function toQueryView() {
      const param = { userId: props.userId, formStatus: 'info' };
      handleBack(param);
    }

    // 變更表單操作狀態
    function changeFormStatus(status: formStatusEnum) {
      formStatusRef.value = status;
      const actionField = { key: 'action', label: '動作', sortable: false, tdClass: 'text-left', thStyle: { width: '5%' } };
      const noneActionField = {
        key: 'action',
        label: '動作',
        sortable: false,
        thClass: 'd-none',
        tdClass: 'd-none',
        thStyle: { width: '5%' },
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

    const propsRoleId = computed(() => props.roleId);

    watch(
      propsRoleId,
      () => {
        if (propsRoleId.value !== 'new') {
          initRoleFunctionInfo();
          changeFormStatus(formStatusEnum.INFO);
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
      $v,
      formDefault,
      userUnit,
      toQueryView,
      reset,
      validateState,
      changeFormStatus,
      form,
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

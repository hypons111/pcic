<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          編輯使用者工程類別
        </h5>
      </div>
      <div class="card-body col-10">
        <div>
          <div>
            <b-form-row class="align-items-top">
              <!-- 帳號 -->
              <i-form-group-check :label="$t('label.accountId') + ':'" :item="$v.userId">
                <b-form-input v-model="$v.userId.$model" :state="validateState($v.userId)" disabled> </b-form-input>
              </i-form-group-check>
              <!-- 帳號名稱 -->
              <i-form-group-check :label="$t('label.accountName') + ':'" :item="$v.chName">
                <b-form-input v-model="$v.chName.$model" :state="validateState($v.chName)" disabled> </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 所屬機關 -->
              <i-form-group-check :label="$t('label.belongOrgan')" :item="$v.orgName">
                <b-form-input v-model="$v.orgName.$model" :state="validateState($v.orgName)" disabled> </b-form-input>
              </i-form-group-check>
              <!-- 連絡電話 -->
              <i-form-group-check :label="$t('label.contactInformationTel')" :item="$v.workTel">
                <b-form-input v-model="$v.workTel.$model" :state="validateState($v.workTel)" disabled> </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 電子郵件信箱 -->
              <i-form-group-check :label="$t('label.emailAddress')" :item="$v.email">
                <b-form-input v-model="$v.email.$model" :state="validateState($v.email)" disabled> </b-form-input>
              </i-form-group-check>
              <!-- 帳號權限 -->
              <i-form-group-check :label="$t('label.accountPermit')" :item="$v.roleName">
                <b-form-input v-model="$v.roleName.$model" :state="validateState($v.roleName)" disabled> </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 帳號狀態 -->
              <i-form-group-check :label="$t('label.accountStatus')" :item="$v.status">
                <b-form-input v-model="$v.status.$model" :state="validateState($v.status)" disabled> </b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row class="align-items-center mt-2">
              <!-- V-for寫法 -->
              <b-col cols="2" class="text-right label">
                <span>{{ $t('label.projectCodes') }}</span>
              </b-col>
              <b-col cols="10">
                <div v-for="option in formOptions.projectCodesOption" :key="option.value" class="col-sm-6 d-inline-block">
                  <b-form-checkbox v-model="$v.projectCodes.$model" :value="option.value" :disabled="option.disabled">
                    {{ option.text }}
                  </b-form-checkbox>
                </div>
              </b-col>
            </b-form-row>

            <div class="button-float-right">
              <b-button-toolbar class="mt-5">
                <i-button class="ml-1" type="save" @click="insertProjectCodes"></i-button>
                <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
                <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler, notificationResponseHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { RecUserProjectCode } from '@/shared/model/recModel/rec-user-project-code.model';
import { admAccountStatusFormatter } from '@/shared/formatter/common';

export default {
  name: 'rec0503-edit-info',
  props: {
    recUserProjectCode: {
      type: Object,
      required: false,
    },
    loginIdObj: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const allProjectCodes = ref([]);

    const loginIdValue = toRefs(props).loginIdObj.value['loginId'];

    // 先抓全部的工程類別checkbox，
    // 後在後端把可以選的回傳其他block掉(回傳登入者ID可選取的類別，如果該ROLE_ID是REC_20則要檢查REC_ORG_PROJECT_CODE)
    const getAllProjectCodesOption = () => {
      let roleIdValue = recUserProjectCodeProp.value['roleId'];
      let orgIdValue = recUserProjectCodeProp.value['orgId'];
      axios
        .get('/rec-user-project-codes/get-access-project-codes/' + loginIdValue + '/' + roleIdValue + '/' + orgIdValue)
        .then(({ data }) => {
          allProjectCodes.value.splice(0, allProjectCodes.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    //抓原本已有選的
    const getCheckedProjectCodes = () => {
      let userIdValue = recUserProjectCodeProp.value['userId'];
      axios.get('/rec-user-project-codes/get-checked-project-codes/' + userIdValue).then(({ data }) => {
        form.projectCodes = data;
        // createDefaultValue(form);
      });
    };

    const recUserProjectCodeProp = toRefs(props).recUserProjectCode;

    //預設值
    const formDefault = {
      userId: '',
      chName: '',
      orgName: '',
      roleName: '',
      status: '',
      workTel: '',
      email: '',
      projectCodes: [],
      createUser: '',
      createTime: undefined,
      updateUser: '',
      updateTime: undefined,
    };

    //預存好的form
    const form = reactive(Object.assign({}, formDefault));

    //檢核規則
    const rules = {
      userId: {},
      chName: {},
      orgName: {},
      roleName: {},
      status: {},
      workTel: {},
      email: {},
      projectCodes: {},
    };

    const formOptions = reactive({
      projectCodesOption: [
        { value: 'A1', text: '水利工程' },
        { value: 'B1', text: '觀光工程' },
        { value: 'D1', text: '建築工程' },
      ],
    });

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //是否要重新查詢
    const isReload: boolean = ref(null);
    //modal物件
    const $bvModal = useBvModal();

    //返回查詢畫面
    const toQueryView = () => {
      const param = { isReload: isReload.value };
      handleBack(param);
    };

    //創建初始值
    const createDefaultValue = (data: RecUserProjectCode) => {
      Object.assign(formDefault, data);
      reset();
    };

    //送出勾選資訊
    const insertProjectCodes = () => {
      //4個系統用欄位
      form.createUser = loginIdValue;
      form.updateUser = loginIdValue;
      form.createTime = new Date();
      form.updateTime = new Date();
      axios
        .post('/rec-user-project-codes/insert-project-codes', form)
        .then($bvModal.msgBoxOk('資料修改成功'))
        .catch(notificationErrorHandler(notificationService));
    };

    watch(
      recUserProjectCodeProp,
      () => {
        if (recUserProjectCodeProp.value) {
          recUserProjectCodeProp.value['status'] = admAccountStatusFormatter(recUserProjectCodeProp.value['status']);
          createDefaultValue(recUserProjectCodeProp.value);
          getAllProjectCodesOption();
          getCheckedProjectCodes();
        }
      },
      { immediate: true }
    );

    watch(
      allProjectCodes,
      () => {
        formOptions.projectCodesOption = allProjectCodes.value.map(data => {
          //map回傳新的格式
          return { value: data['dataKey'], text: data['value'], disabled: data['disabled'] };
        });
      },
      { immediate: true }
    );

    return {
      $v,
      reset,
      validateState,
      toQueryView,
      recUserProjectCodeProp,
      form,
      formOptions,
      insertProjectCodes,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

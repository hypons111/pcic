<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0" v-if="formStatusForwardProp === formStatusEnum.MODIFY">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          編輯颱風資料
        </h5>
        <h5 class="m-0" v-else-if="formStatusForwardProp === formStatusEnum.CREATE">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          新增颱風資料
        </h5>
      </div>
      <div class="card-body col-10">
        <div>
          <!--新增或修改-->
          <div>
            <b-form-row class="align-items-top">
              <!-- 颱風名稱 -->
              <i-form-group-check
                :labelStar="true"
                :label="$t('label.typhoonName') + '：'"
                :item="$v.typhoonName"
                :label-cols="4"
                :content-cols="8"
              >
                <b-form-input v-model.trim="$v.typhoonName.$model" :state="validateState($v.typhoonName)" maxlength="30"> </b-form-input>
              </i-form-group-check>
              <!-- 開放申請日期 -->
              <i-form-group-check
                :labelStar="true"
                :label="$t('label.applyStartTime') + '：'"
                :item="$v.applyStartTime"
                :label-cols="6"
                :content-cols="6"
              >
                <i-date-picker v-model.trim="$v.applyStartTime.$model" :state="$v.applyStartTime"></i-date-picker>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="align-items-top">
              <!-- 申請截止日期 -->
              <i-form-group-check
                :labelStar="true"
                :label="$t('label.applyEndTime') + '：'"
                :item="$v.applyEndTime"
                :label-cols="6"
                :content-cols="6"
              >
                <i-date-picker v-model.trim="$v.applyEndTime.$model" :state="$v.applyEndTime"></i-date-picker>
              </i-form-group-check>
              <!-- 一千萬以下完工日期 -->
              <i-form-group-check
                :labelStar="true"
                :label="$t('label.finishDate1000') + '：'"
                :item="$v.finishDate1000"
                :label-cols="6"
                :content-cols="6"
              >
                <i-date-picker v-model.trim="$v.finishDate1000.$model" :state="$v.finishDate1000"></i-date-picker>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="align-items-top">
              <!-- 一千萬～五千萬完工日期 -->
              <i-form-group-check
                :labelStar="true"
                :label="$t('label.finishDate5000') + '：'"
                :item="$v.finishDate5000"
                :label-cols="6"
                :content-cols="6"
              >
                <i-date-picker v-model="$v.finishDate5000.$model" :state="$v.finishDate5000"></i-date-picker>
              </i-form-group-check>
              <!-- 提報只到地方層級 -->
              <i-form-group-check :label="$t('label.isLocalOnly') + '：'" :item="$v.isLocalOnly" :label-cols="6" :content-cols="6">
                <b-form-checkbox v-model="$v.isLocalOnly.$model" :true-value="true" :false-value="false"> </b-form-checkbox>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="align-items-top">
              <!-- 可修改提報資料 -->
              <i-form-group-check
                :label="$t('label.isEditSubmitData') + '：'"
                :item="$v.isEditSubmitData"
                :label-cols="6"
                :content-cols="6"
              >
                <b-form-checkbox v-model="$v.isEditSubmitData.$model" :true-value="true" :false-value="false"> </b-form-checkbox>
              </i-form-group-check>
              <!-- 不可提報經費調整申請 -->
              <i-form-group-check
                :label="$t('label.isDenyBudgetChange') + '：'"
                :item="$v.isDenyBudgetChange"
                :label-cols="6"
                :content-cols="6"
              >
                <b-form-checkbox v-model="$v.isDenyBudgetChange.$model" :true-value="true" :false-value="false"> </b-form-checkbox>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="align-items-top">
              <!-- 行政院核定情形 -->
              <i-form-group-check
                class="form-group form-check-with-marginB col-12"
                :label="$t('label.centerApprovedProgress') + '：'"
                :item="$v.centerApprovedProgress"
                :label-cols="3"
                :content-cols="9"
              >
                <b-form-textarea
                  v-model.trim="$v.centerApprovedProgress.$model"
                  :state="validateState($v.centerApprovedProgress)"
                  maxlength="2000"
                >
                </b-form-textarea>
              </i-form-group-check>
            </b-form-row>
            <div class="button-float-right">
              <b-button-toolbar class="mt-5" v-if="formStatusRef === 'modify'">
                <i-button class="ml-1" type="save" @click="submitForm('modify')"></i-button>
                <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
                <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
                <i-button class="ml-1" type="trash" @click="deleteTyphoon"></i-button>
              </b-button-toolbar>
              <b-button-toolbar class="mt-5" v-else-if="formStatusRef === 'create'">
                <i-button class="ml-1" type="plus-circle" @click="submitFormNext"></i-button>
                <i-button class="ml-1" type="save" @click="submitForm('create')"></i-button>
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
import { reactive, Ref, ref, watch, toRefs } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler, notificationResponseHandler } from '@/shared/http/http-response-helper';
import { RecTyphoon } from '@/shared/model/recModel/rec-typhoon.model';
import { handleBack } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { FormStatusEnum } from '@/shared/enum';

export default {
  name: 'rec0502-edit-info',
  props: {
    formStatusForward: {
      type: String,
      required: true,
    },
    recTyphoon: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const recTyphoonProp = toRefs(props).recTyphoon;

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    //空值表單
    const cleanTable = {
      typhoonNo: '',
      typhoonName: '',
      applyStartTime: undefined,
      applyEndTime: undefined,
      finishDate1000: undefined,
      finishDate5000: undefined,
      isLocalOnly: false,
      isEditSubmitData: false,
      isDenyBudgetChange: false,
      centerApprovedProgress: '',
      createUser: '',
      createTime: undefined,
      updateUser: '',
      updateTime: undefined,
    };

    //預設值
    const formDefault = Object.assign({}, cleanTable);

    //預存好的form
    const form = reactive(Object.assign({}, formDefault));

    //檢核規則
    const rules = {
      typhoonName: { notNull: required },
      applyStartTime: { notNull: required },
      applyEndTime: { notNull: required },
      finishDate1000: { notNull: required },
      finishDate5000: { notNull: required },
      isLocalOnly: {},
      isEditSubmitData: {},
      isDenyBudgetChange: {},
      isLocalOnly: {},
      isEditSubmitData: {},
      isDenyBudgetChange: {},
      centerApprovedProgress: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //是否要重新查詢
    const isReload: boolean = ref(null);
    //modal物件
    const $bvModal = useBvModal();

    //返回查詢畫面
    const toQueryView = () => {
      changeFormStatus(formStatusEnum.CREATE);
      createDefaultValue(cleanTable);
      reset();
      const param = { isReload: isReload.value };
      handleBack(param);
    };

    //創建初始值
    const createDefaultValue = (data: RecTyphoon) => {
      Object.assign(formDefault, data);
      reset();
    };

    //變更表單操作狀態，當form狀態不為查看時重置
    const changeFormStatus = (status: formStatusEnum) => {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    //修改表單狀態
    const changeformStatusForwardPropEdit = () => {
      formStatusRef.value = formStatusEnum.MODIFY;
    };

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;

    //新增&修改送出前檢查訊息
    const submitForm = (submitMethod: string) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (submitMethod === 'modify') {
            //提交修改
            putForm();
            $bvModal.msgBoxOk('資料異動成功');
          }
          if (submitMethod === 'create') {
            //提交新增
            insertTyphoon(true);
            $bvModal.msgBoxOk('資料新增成功');
            changeFormStatus(formStatusEnum.MODIFY); //改成修改按鈕
          }
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //繼續新增送出前檢查
    const submitFormNext = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //提交新增
          insertTyphoon(false);
          $bvModal.msgBoxOk('資料新增成功');
          changeFormStatus(formStatusEnum.CREATE);
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //修改
    const putForm = () => {
      form.updateUser = userIdentity;
      form.updateTime = new Date();
      let url: string;
      if (formStatusRef.value === formStatusEnum.MODIFY) {
        url = `/rec-typhoons/${form.typhoonNo}`;
      }
      axios
        .put(url, form)
        .then(({ data }) => {
          //填入值
          createDefaultValue(data);
          isReload.value = true;
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    };

    //刪除
    const deleteTyphoon = () => {
      $bvModal.msgBoxConfirm('刪除風災後將一併刪除相關案件及變更資料，確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          let url: string;
          url = `/rec-typhoons/${form.typhoonNo}`;
          axios
            .delete(url)
            .then(toQueryView())
            .then($bvModal.msgBoxOk('資料刪除成功'))
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    //新增
    const insertTyphoon = (insertDone: boolean) => {
      //4個系統用欄位
      form.createUser = userIdentity;
      form.updateUser = userIdentity;
      form.createTime = new Date();
      form.updateTime = new Date();
      axios
        .post(`/rec-typhoons`, form)
        .then(({ data }) => {
          isReload.value = true;
          if (insertDone) {
            createDefaultValue(data); //不再新增時將值放入
          }
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    };

    //傳入狀態
    const formStatusForwardProp = toRefs(props).formStatusForward;

    watch(
      recTyphoonProp,
      () => {
        if (formStatusForwardProp.value === 'modify') {
          createDefaultValue(recTyphoonProp.value);
          changeFormStatus(formStatusEnum.MODIFY);
        } else {
          Object.assign(formDefault, cleanTable);
          Object.assign(form, formDefault);
          createDefaultValue(form);
          changeFormStatus(formStatusEnum.CREATE);
        }
      },
      { immediate: true }
    );

    return {
      $v,
      formStatusEnum,
      formStatusRef,
      changeFormStatus,
      reset,
      validateState,
      toQueryView,
      submitForm,
      submitFormNext,
      deleteTyphoon,
      formStatusForwardProp,
      userIdentity,
      changeformStatusForwardPropEdit,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

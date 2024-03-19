<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">編輯政府機關名稱</h5>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step1">
            <!-- 機關編號 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="2"
                :label="'機關編號'"
                :item="$v.code"
                label-align-md="left"
              >
                <b-form-row>
                  <span>{{ $v.code.$model }}</span>
                </b-form-row>
              </i-form-group-check>
            </b-form-row>
            <!-- 機關名稱 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="3"
                :label="$t('label.orgName')"
                label-align-md="left"
                :labelStar="true"
              >
                <b-form-input v-if="formStatusRef === FormStatusEnum.MODIFY" v-model="$v.name.$model" trim></b-form-input>
                <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.name.$model }}</span>
              </i-form-group-check>
            </b-form-row>
            <!-- 聯絡地址 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="3" :label="'聯絡地址'" label-align-md="left">
                <b-form-input v-if="formStatusRef === FormStatusEnum.MODIFY" v-model="$v.addr.$model" trim></b-form-input>
                <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.addr.$model }}</span>
              </i-form-group-check>
            </b-form-row>
            <!-- 連絡電話 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="3"
                :label="$t('label.contactInformationTel')"
                label-align-md="left"
              >
                <b-form-input v-if="formStatusRef === FormStatusEnum.MODIFY" v-model="$v.tel.$model" trim></b-form-input>
                <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.tel.$model }}</span>
              </i-form-group-check>
            </b-form-row>
            <!-- 傳真機號碼 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="3" :label="'傳真機號碼'" label-align-md="left">
                <b-form-input v-if="formStatusRef === FormStatusEnum.MODIFY" v-model="$v.fax.$model" trim></b-form-input>
                <span v-if="formStatusRef === FormStatusEnum.READONLY"> {{ $v.fax.$model }}</span>
              </i-form-group-check>
            </b-form-row>
            <!-- 新增日期 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="10"
                :label="$t('label.create-date')"
                :item="$v.createDate"
                label-align-md="left"
              >
                <b-form-row>
                  <!-- <i-date-picker v-model="$v.createDate.$model" trim placeholder="yyy/MM/dd"></i-date-picker> -->
                  <span>{{ $v.createDate.$model | dateToString }}</span>
                </b-form-row>
              </i-form-group-check>
            </b-form-row>
            <!-- 修改日期 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="10"
                :label="$t('label.updateTime')"
                label-align-md="left"
              >
                <!-- <b-form-input class="col-1" v-model="$v.updateDate.$model" trim></b-form-input> -->
                <span>{{ $v.updateDate.$model | dateToString }}</span>
              </i-form-group-check>
            </b-form-row>
            <!-- 修改人代碼 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                toQueryView
                content-cols-md="10"
                :label="'修改人代碼'"
                label-align-md="left"
              >
                <!-- <b-form-textarea v-model="$v.updateUser.$model" trim id="textarea-rows" placeholder="" rows="4"></b-form-textarea> -->
                <span>{{ $v.updateUser.$model }}</span>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <b-col offset="4" v-if="formStatusRef === FormStatusEnum.MODIFY">
                <i-button type="check-circle" @click="toSubmit"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
                <i-button size="md" type="arrow-counterclockwise" @click="toQueryView"></i-button>
              </b-col>
              <b-col offset="4" v-if="formStatusRef === FormStatusEnum.READONLY">
                <i-button size="md" type="arrow-bar-left" @click="toQueryView"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { reactive, ref, toRef, watch, Ref, onMounted } from '@vue/composition-api';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { handleBack } from '@/router/router';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { useGetters } from '@u3u/vue-hooks';

export default {
  name: 'bidAbe007Edit',
  props: {
    value: {
      type: Object,
      required: false,
    },
    formStatus: {
      required: false,
      type: String,
    },
  },

  setup(props, context) {
    const bringValueProp = toRef(props, 'value');
    const formStatusRef = toRef(props, 'formStatus'); // toRef會保持對來源property的響應式連接，但改變父元件資料會被Vue警告，所以這邊改用ref複製一份新的資料

    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
    }

    const stepVisible = reactive({
      step1: true,
    });

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;

    //取得使用者資訊ip
    const store = context.root.$store;
    const userIp = store.getters.account.ip;

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      code: '', //機關代碼
      name: '', //機關名稱
      addr: '', //聯絡地址
      tel: '', //連絡電話
      fax: '', //傳真機號碼
      createDate: null, //新增日期
      updateDate: null, //修改日期
      updateUser: '', //修改人代碼
    };

    // 表單物件驗證規則
    const rules = {
      code: {}, //機端代碼
      name: {}, //機關名稱
      addr: {}, //聯絡地址
      tel: {}, //連絡電話
      fax: {}, //傳真機號碼
      createDate: {}, //新增日期
      updateDate: {}, //修改日期
      updateUser: {}, //修改人
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const getName = ref();

    // 執行結果
    // const queryStatus = ref(false);
    const dataPromise = ref(null);

    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const toSubmit = () => {
      const value = new Date();
      const offset = value.getTimezoneOffset();
      form.createDate = new Date();
      form.createDate = new Date(form.createDate.getTime() - offset * 60 * 1000);
      form.updateUser = userIdentity;
      // Object.assign(form, criteria);
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          dataPromise.value = axios
            .post('/bid-unit-10', form)
            .then(({ data }) => {
              createDefaultValue(data);
              isReload.value = true;
              $bvModal.msgBoxOk('政府機關修改成功!');
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const toQueryView = () => {
      handleBack({ isReload: isReload.value });
      isReload.value = false;
    };

    const createDefaultValue = data => {
      Object.assign(formDefault, data);
      // formDefault.createDate = formatToString(formDefault.createDate, '/', '-');
      // formDefault.updateDate = formatToString(formDefault.updateDate, '/', '-');
      Object.assign(form, formDefault);
      reset();
    };

    watch(
      bringValueProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          // bringValueProp.value.createDate = new Date(bringValueProp.value.createDate);
          createDefaultValue(newValue);
        }
      },
      { immediate: true }
    );

    return {
      // projectInfo,
      formDefault,
      $v,
      reset,
      validateState,
      dataPromise,
      toQueryView,
      createDefaultValue,
      FormStatusEnum,
      formStatusRef,
      toSubmit,
      stepVisible,
      formOptionsFormatter,
    };
  },

  filters: {
    dateToString: (value: string) => {
      var date = new Date(value);
      const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      return year + '年' + month + '月' + day + '日';
    },
    responsIdToName: (value: string) => {},
  },
};
</script>
<style></style>

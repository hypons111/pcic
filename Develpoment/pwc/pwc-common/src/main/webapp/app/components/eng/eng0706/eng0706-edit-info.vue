<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            詳細資料
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 身分證字號 -->
            <i-form-group-check class="col-12" label-cols="3" content-cols="4" :label="$t('label.idno') + '：'" :item="$v.idno">
              <div>{{ $v.idno.$model }}</div>
              <!-- <b-form-input v-model="$v.idno.$model" :state="validateState($v.idno)"></b-form-input> -->
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 身分別 -->
            <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="2"
              :label="$t('label.identityType') + '：'"
              :item="$v.identityType"
            >
              <b-form-select
                v-model="$v.identityType.$model"
                :options="formOptions.identityType"
                :state="validateState($v.identityType)"
              ></b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 姓名(中) -->
            <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="3"
              :label="$t('label.chName') + '：'"
              :label-star="true"
              :item="$v.chName"
            >
              <b-form-input v-model="$v.chName.$model" :state="validateState($v.chName)"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 姓名(英) -->
            <i-form-group-check class="col-12" label-cols="3" content-cols="3" :label="$t('label.enName') + '：'" :item="$v.enName">
              <b-form-input v-model="$v.enName.$model" :state="validateState($v.enName)"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 性別 -->
            <i-form-group-check class="col-12" label-cols="3" content-cols="2" :label="$t('label.sex') + '：'" :item="$v.sex">
              <b-form-select v-model="$v.sex.$model" :options="formOptions.sex" :state="validateState($v.sex)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 出生日期 -->
            <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="3"
              :label="$t('label.birthDate') + '：'"
              :label-star="true"
              :item="$v.birthDate"
            >
              <!-- <div>{{ $v.birthDate.$model | dateToString }}</div> -->
              <i-date-picker v-model="$v.birthDate.$model" :state="validateState($v.birthDate)" placeholder="yyy/MM/dd"></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 戶籍地址 -->
            <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="9"
              :label="$t('label.permanentAddr') + '：'"
              :item="[$v.permanentAddrTown, $v.permanentAddrOther, $v.permanentAddrCity]"
            >
              <!-- <div>
                {{ $v.permanentAddrCity.$model + $v.permanentAddrTown.$model + $v.permanentAddrOther.$model }}
              </div> -->
              <i-address-picker
                :zip.sync="$v.permanentAddrZip.$model"
                :city.sync="$v.permanentAddrCity.$model"
                :town.sync="$v.permanentAddrTown.$model"
                :other.sync="$v.permanentAddrOther.$model"
                :cityState="validateState($v.permanentAddrCity)"
                :townState="validateState($v.permanentAddrTown)"
                :otherState="validateState($v.permanentAddrOther)"
                :cols="[1, 2, 2, 5]"
              >
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 通訊地址 -->
            <i-form-group-check
              :label="$t('label.contactAddr') + '：'"
              class="col-12"
              label-cols="3"
              content-cols="9"
              :item="[$v.contactAddrTown, $v.contactAddrOther, $v.contactAddrCity]"
            >
              <!-- <div>
                {{ $v.contactAddrCity.$model + $v.contactAddrTown.$model + $v.contactAddrOther.$model }}
              </div> -->
              <i-address-picker
                :zip.sync="$v.contactAddrZip.$model"
                :city.sync="$v.contactAddrCity.$model"
                :town.sync="$v.contactAddrTown.$model"
                :other.sync="$v.contactAddrOther.$model"
                :cityState="validateState($v.contactAddrCity)"
                :townState="validateState($v.contactAddrTown)"
                :otherState="validateState($v.contactAddrOther)"
                :cols="[1, 2, 2, 5, 2]"
              >
                <template #end>
                  <b-form-checkbox class="ml-2" v-model="isSamePermanentAddr" @change="samePermanentAddr">
                    <span class="small">同戶籍地址</span>
                  </b-form-checkbox>
                </template>
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 電話(公) -->
            <i-form-group-check
              :label="$t('label.contactTelOffice') + '：'"
              :label-star="true"
              class="col-12"
              label-cols="3"
              content-cols="6"
              :item="$v.tel1"
            >
              <b-form-input v-model="$v.tel1.$model" :state="validateState($v.tel1)"></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 電話 住 -->
            <i-form-group-check :label="$t('label.contactTelHome') + '：'" class="col-12" label-cols="3" content-cols="6" :item="$v.tel2">
              <b-form-input v-model="$v.tel2.$model"></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 手機 -->
            <i-form-group-check :label="$t('label.mobileTel') + '：'" class="col-12" label-cols="3" content-cols="6" :item="$v.tel3">
              <b-form-input v-model="$v.tel3.$model"></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 傳真 -->
            <i-form-group-check :label="$t('label.fax') + '：'" class="col-12" label-cols="3" content-cols="6" :item="$v.fax">
              <b-form-input v-model="$v.fax.$model" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- email -->
            <i-form-group-check class="col-12" label-cols="3" content-cols="6" :label="$t('label.email') + '：'" :item="$v.email1">
              <b-form-input v-model="$v.email1.$model" :state="validateState($v.email1)"></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="role === RoleEnum.ADMIN">
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="3" content-cols="6" :label="$t('label.remark') + '：'" :item="$v.remark">
              <b-form-textarea v-model="$v.remark.$model" :state="validateState($v.remark)"></b-form-textarea>
              <!-- <div>{{ $v.remark.$model }}</div> -->
            </i-form-group-check>
          </b-form-row>

          <div class="text-center mb-3">
            <i-button class="ml-1" type="save" @click="submitForm"></i-button>
            <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
            <i-button v-if="role === RoleEnum.ADMIN" class="ml-1" type="arrow-left" @click="toQueryView"></i-button>
          </div>
          <i-switch-case
            v-if="role === RoleEnum.ADMIN"
            class="mb-3"
            :criteria="criteria"
            api-url="/eng-members/criteria-jpa"
            @changeCase="createDefaultValue"
          ></i-switch-case>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { onMounted, reactive, Ref, ref, toRef, toRefs, watch, defineComponent, unref } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';

import { useBvModal } from '@/shared/modal';
import { required, requiredIf, maxLength, enName, mobileTel, lineTel, address, email, nationalId } from '@/shared/validators';
import { parseRocDate } from '@/shared/date/minguo-calendar-utils';
import axios from 'axios';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack, navigateByNameAndParams } from '@/router/router';
import { EngMember } from '@/shared/model/eng/eng-member.model';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { FormStatusEnum, RoleEnum } from '@/shared/enum';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { useRouter, useStore } from '@u3u/vue-hooks';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { AdmFunctionUsedRecord } from '@/shared/model/adm/adm-function-used-record.model';

interface propsType {
  engMember: EngMember;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0706Read',
  props: {
    engMember: {
      required: false,
      type: Object,
    },
    role: {
      required: false,
    },
    criteria: {
      required: false,
      type: Object,
    },
  },
  components: {
    ISwitchCase,
  },
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      MODIFY = 'modify',
    }
    const engMemberProp = toRef(props, 'engMember');
    const admSysCodeService = new AdmSysCodeService();
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);
    const isCheckRequired = ref(false); // 儲存 = false; 送出 = true
    const isSamePermanentAddr = ref(false); // 同戶籍地址checkbox

    const { engMember, role, criteria } = toRefs(props);

    const role = ref(props.role).value;

    //從使用者撈資料
    const store = useStore();
    const engAccount = {
      userId: store.value.getters.account.id,
      idno: store.value.getters.account.idn,
    };
    // console.log(engAccount.idno);

    onMounted(() => {
      // console.log(role);
      if (role === RoleEnum.USER) {
        handleQuery();
      }
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const formOptions = reactive({
      sex: [
        // { value: true, text: '男' },
        // { value: false, text: '女' },
        // { value: '1', text: '男' },
        // { value: '0', text: '女' },
      ],
      identityType: [
        // { value: '', text: '請選擇' },
        // { value: '0', text: '一般' },
        // { value: '1', text: '原住民' },
        // { value: '2', text: '外國籍' },
      ],
    });

    //針對form的表示，之後要轉成後端接收的data Type
    let formDefault = {
      idno: '',
      identityType: '',
      chName: '',
      enName: '',
      sex: null,
      birthDate: null,
      permanentAddrZip: '',
      permanentAddrCity: '',
      permanentAddrTown: '',
      permanentAddrOther: '',
      contactAddrZip: '',
      contactAddrCity: '',
      contactAddrTown: '',
      contactAddrOther: '',
      tel1: '',
      tel2: '',
      tel3: '',
      fax: '',
      email1: '',
      remark: '',
      updateTime: null,
      updateUser: '',
    };
    Object.assign(formDefault, engMemberProp.value);
    formDefault.birthDate = parseToDate(formDefault.birthDate, '-');
    // formDefault.birthDate = formatToMinguoStr(formDefault.birthDate, '/', '-');
    // console.log(formDefault);

    let form = reactive(Object.assign({}, formDefault));

    const rules = {
      idno: { nationalId: nationalId }, //身分證字號
      identityType: { required: required },
      chName: { required },
      enName: { requiredIf: requiredIf(() => isCheckRequired.value), enName: enName, maxLength: maxLength(100) },
      sex: { requiredIf: requiredIf(() => isCheckRequired.value) },
      birthDate: { required },
      permanentAddrZip: {},
      permanentAddrCity: { requiredIf: requiredIf(() => isCheckRequired.value) },
      permanentAddrTown: { equalsCityBoolean: address(() => form.permanentAddrCity) },
      permanentAddrOther: { equalsCityBoolean: address(() => form.permanentAddrCity) },
      contactAddrZip: {},
      contactAddrCity: { requiredIf: requiredIf(() => isCheckRequired.value) },
      contactAddrTown: { equalsCityBoolean: address(() => form.contactAddrCity) },
      contactAddrOther: { equalsCityBoolean: address(() => form.contactAddrCity) },
      tel1: { required },
      tel2: {},
      tel3: {},
      fax: {},
      email1: { email: email, maxLength: maxLength(50) },
      remark: {},
    };

    const groups = [
      ['permanentAddrCity', 'permanentAddrTown', 'permanentAddrOther'],
      ['contactAddrCity', 'contactAddrTown', 'contactAddrOther'],
    ];

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault, groups);

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);

    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          putForm();
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };
    const putForm = () => {
      let url: string;
      url = `/eng-members/${form.engMemberId}`;
      form.updateTime = new Date();
      form.updateUser = engAccount.userId;
      // form.updateUser
      dataPromise.value = axios
        .put(url, form, { headers: { functionId: useRouter().route.value.meta.functionId } })
        .then(({ data }) => {
          createDefaultValue(data);
          isReload.value = true;
          notificationService.success(`儲存成功`);
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const createDefaultValue = (data: EngMember) => {
      Object.assign(formDefault, data);
      reset();
    };

    function samePermanentAddr() {
      switch (isSamePermanentAddr.value) {
        case true:
          form.contactAddrZip = form.permanentAddrZip;
          form.contactAddrCity = form.permanentAddrCity;
          form.contactAddrTown = form.permanentAddrTown;
          form.contactAddrOther = form.permanentAddrOther;
          break;
        case false:
          form.contactAddrZip = formDefault.contactAddrZip;
          form.contactAddrCity = formDefault.contactAddrCity;
          form.contactAddrTown = formDefault.contactAddrTown;
          form.contactAddrOther = formDefault.contactAddrOther;
      }
    }

    const handleQuery = () => {
      dataPromise.value = axios
        .post('/eng-members/criteria-jpa', engAccount) //後端分頁
        .then(({ data }) => {
          // console.log(data);
          // console.log(data.content[0]);
          Object.assign(formDefault, data.content[0]);
          formDefault.birthDate = parseToDate(formDefault.birthDate, '-');
          Object.assign(form, formDefault);
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const usedRecord = new AdmFunctionUsedRecord();
    usedRecord.sysCategory = 'ENG';
    usedRecord.functionId = 'ENG0706';
    usedRecord.tableName = 'ENG_MEMBER';
    usedRecord.tableKey = form.engMemberId;
    usedRecord.recordContent = 'ENG0706U1技師個人基本資料-修改';
    usedRecord.updateValue = JSON.stringify(form);
    usedRecord.createUserSys = engAccount.userId;
    // console.log(usedRecord.updateValue);
    // const addAdmFunctionUsedRecord = () => {
    //   dataPromise.value = axios
    //     .post(`/adm-function-used-records`, usedRecord)
    //     .then(({ data }) => {
    //       isReload.value = true;
    //       notificationService.success(`新增異動紀錄`);
    //     })
    //     .finally(() => (dataPromise.value = null))
    //     .catch(notificationErrorHandler(notificationService));
    // };

    const toQueryView = () => {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    };

    return {
      formStatusEnum,
      RoleEnum,
      formStatusRef,
      formOptions,
      dataPromise,
      $v,
      form,
      toQueryView,
      validateState,
      reset,
      submitForm,
      samePermanentAddr,
      isSamePermanentAddr,
      handleQuery,
      createDefaultValue,
    };
  },
});
</script>
<style></style>

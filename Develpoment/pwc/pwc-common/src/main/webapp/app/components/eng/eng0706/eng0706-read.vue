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
            <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="4"
              :label="$t('label.idno') + '：'"
              :label-star="true"
              :item="$v.idno"
            >
              <!-- <b-form-input v-model="$v.idno.$model" :state="validateState($v.idno)"></b-form-input> -->
              <div>{{ $v.idno.$model }}</div>
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
              <!-- <b-form-select
                v-model="$v.identityType.$model"
                :options="formOptions.identityType"
                :state="validateState($v.identityType)"
              ></b-form-select> -->
              <div>{{ formOptionsFormatter(formOptions.identityType, $v.identityType.$model) }}</div>
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
              <!-- <b-form-input v-model="$v.chName.$model" :state="validateState($v.chName)"></b-form-input> -->
              <div>{{ $v.chName.$model }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 姓名(英) -->
            <i-form-group-check class="col-12" label-cols="3" content-cols="3" :label="$t('label.enName') + '：'" :item="$v.enName">
              <!-- <b-form-input v-model="$v.enName.$model" :state="validateState($v.enName)"></b-form-input> -->
              <div>{{ $v.enName.$model }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 性別 -->
            <i-form-group-check class="col-12" label-cols="3" content-cols="2" :label="$t('label.sex') + '：'" :item="$v.sex">
              <!-- <b-form-select v-model="$v.sex.$model" :options="formOptions.sex" :state="validateState($v.sex)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select> -->
              <div>{{ formOptionsFormatter(formOptions.sex, $v.sex.$model) }}</div>
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
              <!-- <i-date-picker v-model="$v.birthDate.$model" :state="validateState($v.birthDate)" placeholder="yyy/MM/dd"></i-date-picker> -->
              <div>{{ $v.birthDate.$model | dateToString }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 戶籍地址 -->
            <i-form-group-check
              labelStar
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
                :isText="true"
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
                :isText="true"
                :zip.sync="$v.contactAddrZip.$model"
                :city.sync="$v.contactAddrCity.$model"
                :town.sync="$v.contactAddrTown.$model"
                :other.sync="$v.contactAddrOther.$model"
                :cityState="validateState($v.contactAddrCity)"
                :townState="validateState($v.contactAddrTown)"
                :otherState="validateState($v.contactAddrOther)"
                :cols="[1, 2, 2, 5, 2]"
              >
                <!-- <template #end>
                    <b-form-checkbox class="ml-2" v-model="isSamePermanentAddr" @change="samePermanentAddr">
                      <span class="small">同戶籍地址</span>
                    </b-form-checkbox>
                  </template> -->
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
              <!-- <i-tel v-model="$v.tel1.$model" :state="validateState($v.tel1)"></i-tel> -->
              <div>{{ $v.tel1.$model }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 電話 住 -->
            <i-form-group-check :label="$t('label.contactTelHome') + '：'" class="col-12" label-cols="3" content-cols="6" :item="$v.tel2">
              <!-- <i-tel v-model="$v.tel2.$model" :state="validateState($v.tel2)"></i-tel> -->
              <div>{{ $v.tel2.$model }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 手機 -->
            <i-form-group-check :label="$t('label.mobileTel') + '：'" class="col-12" label-cols="3" content-cols="6" :item="$v.tel3">
              <!-- <i-tel isMobile v-model="$v.tel3.$model" :state="validateState($v.tel3)"></i-tel> -->
              <div>{{ $v.tel3.$model }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 傳真 -->
            <i-form-group-check :label="$t('label.fax') + '：'" class="col-12" label-cols="3" content-cols="6" :item="$v.fax">
              <!-- <b-form-input v-model="$v.fax.$model" trim></b-form-input> -->
              <div>{{ $v.fax.$model }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- email -->
            <i-form-group-check class="col-12" label-cols="3" content-cols="6" :label="$t('label.email') + '：'" :item="$v.email1">
              <!-- <b-form-input v-model="$v.email1.$model" :state="validateState($v.email1)"></b-form-input> -->
              <div>{{ $v.email1.$model }}</div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="role === RoleEnum.ADMIN">
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="3" content-cols="6" :label="$t('label.remark') + '：'" :item="$v.remark">
              <!-- <b-form-textarea v-model="$v.remark.$model" :state="validateState($v.remark)"></b-form-textarea> -->
              <div>{{ $v.remark.$model }}</div>
            </i-form-group-check>
          </b-form-row>

          <div class="text-center mb-3">
            <i-button class="ml-1" type="arrow-left" @click="toQueryView"></i-button>
          </div>
          <i-switch-case
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
import { onMounted, reactive, Ref, ref, toRef, toRefs, defineComponent, unref } from '@vue/composition-api';
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
import { formOptionsFormatter } from '@/shared/formatter/common';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { FormStatusEnum, RoleEnum } from '@/shared/enum';
import { useStore } from '@u3u/vue-hooks';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';

interface propsType {
  engEngrSubjectpoint: EngMember;
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

    const { engMember, role, criteria } = toRefs(props);

    //從使用者撈資料
    // const store = context.root.$store;
    const store = useStore();
    const engAccount = {
      // idno: store.getters.account.idn,
      idno: store.value.getters.account.idn,
    };

    const role = ref(props.role).value;

    onMounted(() => {
      // console.log(role);
      // if (role === RoleEnum.USER) {
      //   handleQuery();
      // }
      getAllSysCodes();
      createDefaultValue();
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
    };
    Object.assign(formDefault, engMemberProp.value);
    formDefault.birthDate = parseToDate(formDefault.birthDate, '-');
    // formDefault.birthDate = formatToString(formDefault.birthDate, '/', '-');
    // console.log(formDefault);

    let form = reactive(Object.assign({}, formDefault));

    const rules = {
      idno: { nationalId: nationalId }, //身分證字號
      identityType: { required: required },
      chName: { requiredIf: requiredIf(() => isCheckRequired.value), maxLength: maxLength(20) },
      enName: { requiredIf: requiredIf(() => isCheckRequired.value), enName: enName, maxLength: maxLength(100) },
      sex: { requiredIf: requiredIf(() => isCheckRequired.value) },
      birthDate: { requiredIf: requiredIf(() => isCheckRequired.value) },
      permanentAddrZip: {},
      permanentAddrCity: { requiredIf: requiredIf(() => isCheckRequired.value) },
      permanentAddrTown: { equalsCityBoolean: address(() => form.permanentAddrCity) },
      permanentAddrOther: { equalsCityBoolean: address(() => form.permanentAddrCity) },
      contactAddrZip: {},
      contactAddrCity: { requiredIf: requiredIf(() => isCheckRequired.value) },
      contactAddrTown: { equalsCityBoolean: address(() => form.contactAddrCity) },
      contactAddrOther: { equalsCityBoolean: address(() => form.contactAddrCity) },
      tel1: { requiredIf: requiredIf(() => isCheckRequired.value), lineTel: lineTel },
      tel2: { lineTel: lineTel },
      tel3: { requiredIf: requiredIf(() => isCheckRequired.value && form.receiveType === '3'), mobileTel: mobileTel },
      fax: {},
      email1: { email: email, maxLength: maxLength(50) },
      remark: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);

    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const toQueryView = () => {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    };

    const createDefaultValue = (data: EngMember) => {
      Object.assign(formDefault, data);
      formDefault.birthDate = parseToDate(formDefault.birthDate, '-');
      reset();
    };

    // const handleQuery = () => {
    //   dataPromise.value = axios
    //     .post('/eng-members/criteria-jpa', engAccount) //後端分頁
    //     .then(({ data }) => {
    //       // console.log(data);
    //       console.log(data.content[0]);
    //       Object.assign(form, data.content[0]);
    //       form.birthDate = parseToDate(form.birthDate, '-');
    //     })
    //     .finally(() => (dataPromise.value = null))
    //     .catch(notificationErrorHandler(notificationService));
    // };

    return {
      FormStatusEnum,
      RoleEnum,
      // role,
      formStatusRef,
      formOptions,
      formOptionsFormatter,
      dataPromise,
      $v,
      form,
      toQueryView,
      validateState,
      createDefaultValue,
      // handleQuery,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>

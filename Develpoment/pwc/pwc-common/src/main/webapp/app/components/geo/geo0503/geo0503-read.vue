<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            基本資料
          </h5>
        </div>
        <div class="card-body">
          <!-- 個人資料 -->
          <b-card no-body>
            <b-card-header class="py-1">
              <b>個人資料</b>
            </b-card-header>
            <b-card-body class="py-2">
              <b-form-row>
                <!-- 姓名 -->
                <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.userName') + '：'" :item="$v.userName">
                  <!-- <b-form-input readonly v-model="$v.userName.$model" :state="validateState($v.userName)"></b-form-input> -->
                  {{ $v.userName.$model }}
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <!-- 性別 -->
                <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.sex') + '：'" :item="$v.sex">
                  <div class="h-100 d-flex align-items-center">
                    <b-form-radio-group
                      disabled
                      v-model="$v.sex.$model"
                      :options="formOptions.sex"
                      :state="validateState($v.sex)"
                    ></b-form-radio-group>
                  </div>
                  <!-- {{ formOptionsFormatter(formOptions.sex, $v.sex.$model) }} -->
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <!-- 出生日期 -->
                <i-form-group-check class="col-12" label-cols="2" content-cols="2" :label="$t('label.birthday') + '：'" :item="$v.birthday">
                  <i-date-picker disabled v-model="$v.birthday.$model" :state="validateState($v.birthday)"></i-date-picker>
                  <!-- {{ $v.birthday.$model }} -->
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <!-- 通訊地址 -->
                <i-form-group-check
                  class="col-12"
                  label-cols="2"
                  content-cols="9"
                  :label="$t('label.contactAddr') + '：'"
                  :item="[$v.addrZip, $v.addrTown, $v.addrOther, $v.addrCity]"
                >
                  <i-address-picker
                    :is-text="true"
                    :zip.sync="$v.addrZip.$model"
                    :city.sync="$v.addrCity.$model"
                    :town.sync="$v.addrTown.$model"
                    :other.sync="$v.addrOther.$model"
                    :city-state="validateState($v.addrCity)"
                    :town-state="validateState($v.addrTown)"
                    :other-state="validateState($v.addrOther)"
                    :cols="[1, 2, 2, 5, 2]"
                  ></i-address-picker>
                  <!-- {{ $v.addrZip.$model }} {{ $v.addrCity.$model }} {{ $v.addrTown.$model }} {{ $v.addrOther.$model }} -->
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <!-- email -->
                <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.email') + '：'" :item="$v.email">
                  <!-- <b-form-input readonly v-model="$v.email.$model" :state="validateState($v.email)"></b-form-input> -->
                  {{ $v.email.$model }}
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <!-- 電話(公) -->
                <i-form-group-check
                  class="col-12"
                  label-cols="2"
                  content-cols="6"
                  :label="$t('label.contactTelOffice') + '：'"
                  :item="$v.workTel"
                >
                  <!-- <i-tel disabled showExt v-model="$v.workTel.$model" :state="validateState($v.workTel)" :item="$v.workTel"></i-tel> -->
                  {{ $v.workTel.$model }}
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <!-- 電話 家 -->
                <i-form-group-check
                  class="col-12"
                  label-cols="2"
                  content-cols="6"
                  :label="$t('label.contactTelHome') + '：'"
                  :item="$v.homeTel"
                >
                  <!-- <i-tel disabled v-model="$v.homeTel.$model" :state="validateState($v.homeTel)" :item="$v.homeTel"></i-tel> -->
                  {{ $v.homeTel.$model }}
                </i-form-group-check>
              </b-form-row>

              <b-form-row>
                <!-- 傳真 -->
                <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.fax') + '：'" :item="$v.fax">
                  <!-- <i-tel disabled v-model="$v.fax.$model" :state="validateState($v.fax)"></i-tel> -->
                  {{ $v.fax.$model }}
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <!-- 手機 -->
                <i-form-group-check
                  class="col-12"
                  label-cols="2"
                  content-cols="6"
                  :label="$t('label.mobileTel') + '：'"
                  :item="$v.mobileTel"
                >
                  <!-- <i-tel disabled isMobile v-model="$v.mobileTel.$model" :state="validateState($v.mobileTel)"></i-tel> -->
                  {{ $v.mobileTel.$model }}
                </i-form-group-check>
              </b-form-row>
            </b-card-body>
          </b-card>
          <!-- 學歷 -->
          <b-card no-body>
            <b-card-header class="py-1">
              <b>學歷(畢業年級)</b>
            </b-card-header>
            <b-card-body class="py-2">
              <!--sub-title="學歷(畢業年級)" <hr /> -->
              <b-form-row>
                <!-- 應考學歷 -->
                <i-form-group-check :label="$t('label.examineeDegree') + '：'" :item="$v.examineeDegree">
                  <!-- <b-form-input v-model="$v.examineeDegree.$model" :state="validateState($v.examineeDegree)"></b-form-input> -->
                  {{ $v.examineeDegree.$model }}
                </i-form-group-check>
                <!-- 最高學歷 -->
                <i-form-group-check :label="$t('label.highestDegree') + '：'" :item="$v.highestDegree">
                  <!-- <b-form-input v-model="$v.highestDegree.$model" :state="validateState($v.highestDegree)"></b-form-input> -->
                  {{ $v.highestDegree.$model }}
                </i-form-group-check>
              </b-form-row>
            </b-card-body>
          </b-card>
          <b-card no-body>
            <b-card-header class="py-1">
              <b>第一階段考試及格或免試證明</b>
            </b-card-header>
            <b-card-body class="py-2">
              <!-- 考試證明 -->
              <b-form-row>
                <!-- 日期 -->
                <i-form-group-check :label="$t('label.date') + '：'" :item="$v.firstexamDate">
                  <!-- <i-date-picker v-model="$v.firstexamDate.$model" :state="validateState($v.firstexamDate)"></i-date-picker> -->
                  {{ $v.firstexamDate.$model }}
                </i-form-group-check>
                <!-- 證號 -->
                <i-form-group-check :label="$t('label.certNo') + '：'" :item="$v.firstexamCertNo">
                  <!-- <b-form-input v-model="$v.firstexamCertNo.$model" :state="validateState($v.firstexamCertNo)"></b-form-input> -->
                  {{ $v.firstexamCertNo.$model }}
                </i-form-group-check>
              </b-form-row>
            </b-card-body>
          </b-card>
          <b-card no-body>
            <b-card-header class="py-1">
              <b>主動求才資訊</b>
            </b-card-header>
            <b-card-body class="py-2">
              <!-- 求才資訊-->
              <b-form-row>
                <!-- 身份證 -->
                <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.idno') + '：'" :item="$v.idno">
                  <!-- <b-form-input v-model="$v.idno.$model" :state="validateState($v.idno)"></b-form-input> -->
                  {{ $v.idno.$model }}
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <!-- 開放求才單位查詢 -->
                <i-form-group-check
                  class="col-12"
                  label-cols="2"
                  content-cols="6"
                  :label="$t('label.isEnablePublic') + '：'"
                  :item="$v.isEnable"
                >
                  <b-form-radio-group
                    disabled
                    class="pt-2"
                    v-model="$v.isEnable.$model"
                    :options="formOptions.isEnable"
                    :state="validateState($v.isEnable)"
                  ></b-form-radio-group>
                  <!-- {{ formOptionsFormatter(formOptions.isEnable, $v.isEnable.$model) }} -->
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <!-- 工作屬性 -->
                <i-form-group-check class="col-12" label-cols="2" content-cols="4" :label="$t('label.jobType') + '：'" :item="$v.jobType">
                  <b-form-select
                    disabled
                    v-model="$v.jobType.$model"
                    :options="formOptions.jobType"
                    :state="validateState($v.jobType)"
                  ></b-form-select>
                  <!-- {{ formOptionsFormatter(formOptions.jobType, $v.jobType.$model) }} -->
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <!-- 類別 -->
                <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="$t('label.jobKind') + '：'" :item="$v.jobKind">
                  <!-- <b-form-select
                  disabled
                  v-model="$v.jobKind.$model"
                  :options="formOptions.jobKind"
                  :state="validateState($v.jobKind)"
                ></b-form-select> -->
                  <div v-for="option in formOptions.jobKind" :key="option.value" class="col-4 d-inline-block">
                    <b-form-checkbox disabled v-model="$v.jobKind.$model" :value="option.value" :state="validateState($v.jobKind)"
                      >{{ option.text }}
                    </b-form-checkbox>
                  </div>
                  <!-- {{ formOptionsFormatter(formOptions.jobKind, $v.jobKind.$model) }} -->
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <!-- 希望待遇 -->
                <i-form-group-check
                  class="col-12"
                  label-cols="2"
                  content-cols="4"
                  :label="$t('label.salaryRange') + '：'"
                  :item="$v.salaryRange"
                >
                  <b-form-select disabled v-model="$v.salaryRange.$model" :options="formOptions.salaryRange"></b-form-select>
                  <!-- {{ formOptionsFormatter(formOptions.salaryRange, $v.salaryRange.$model) }} -->
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <!-- 希望工作地點 -->
                <i-form-group-check
                  class="col-12"
                  label-cols="2"
                  content-cols="6"
                  :label="$t('label.workplaces') + '：'"
                  :item="$v.workPlace"
                >
                  <!-- <b-form-checkbox-group class="pt-2" v-model="$v.workPlace.$model" :options="cityOptions"></b-form-checkbox-group> -->
                  <div v-for="option in cityOptions" :key="option.value" class="col-4 d-inline-block">
                    <b-form-checkbox disabled v-model="$v.workPlace.$model" :value="option.value" :state="validateState($v.workPlace)"
                      >{{ option.text }}
                    </b-form-checkbox>
                  </div>
                  <!-- {{ $v.workPlace.$model }} -->
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <!-- 工作需求 -->
                <i-form-group-check
                  class="col-12"
                  label-cols="2"
                  content-cols="6"
                  :label="$t('label.jobDemand') + '：'"
                  :item="$v.jobDemand"
                >
                  <!-- <b-form-textarea v-model="$v.jobDemand.$model"></b-form-textarea> -->
                  {{ $v.jobDemand.$model }}
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <!-- 備註 -->
                <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.remark') + '：'" :item="$v.remark">
                  <!-- <b-form-input v-model="$v.remark.$model"></b-form-input> -->
                  {{ $v.remark.$model }}
                </i-form-group-check>
              </b-form-row>
            </b-card-body>
          </b-card>

          <!-- 最後的工作列 -->
          <div class="d-flex justify-content-center">
            <b-button-toolbar class="mt-5">
              <i-button class="ml-1" type="arrow-left" @click="toQueryView"></i-button>
            </b-button-toolbar>
          </div>
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
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { FormStatusEnum, RoleEnum } from '@/shared/enum';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { useStore } from '@u3u/vue-hooks';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { GeoMember } from '@/shared/model/geoModel/geo-member.model';
import { formOptionsFormatter } from '@/shared/formatter/common';

interface propsType {
  geoMember: GeoMember;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'geo0503Read',
  props: {
    geoMember: {
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
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      MODIFY = 'modify',
    }
    const admSysCodeService = new AdmSysCodeService();
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);
    const isCheckRequired = ref(false); // 儲存 = false; 送出 = true
    const isSamePermanentAddr = ref(false); // 同戶籍地址checkbox

    // const { geoMember, role, criteria } = toRefs(props);

    // const role = ref(props.role).value;

    //從使用者撈資料
    const store = useStore();
    const engAccount = {
      userId: store.value.getters.account.id,
      idno: store.value.getters.account.idn,
    };
    // console.log(engAccount.idno);
    const cityOptions = ref(undefined);

    onMounted(() => {
      // console.log(role);
      // if (role === RoleEnum.USER) {
      // handleQuery();
      // }
      getAllSysCodes();
      createDefaultValue(props.geoMember);
      getCityOptions();
    });

    function getCityOptions() {
      const set = new Set();
      cityOptions.value = useStore()
        .value.getters.admPostAddr.slice()
        .filter(item => (!set.has(item.city) ? set.add(item.city) : false))
        .map(item => {
          return { value: item.cityCode, text: item.city };
        });
    }

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'GEO');
      formOptions.isEnable = [
        { value: '0', text: '是' },
        { value: '1', text: '否' },
      ];
      formOptions.sex = [
        { value: '0', text: '女' },
        { value: '1', text: '男' },
      ];
    }

    const formOptions = reactive({
      sex: [],
      jobType: [],
      jobKind: [],
      salaryRange: [],
      isEnable: [],
    });

    //針對form的表示，之後要轉成後端接收的data Type
    let formDefault = new GeoMember();

    let form = reactive(Object.assign({}, formDefault));
    const rules = {
      userName: {}, //姓名
      birthday: {}, // notNull: required生日
      sex: {},
      email: {}, //信箱
      workTel: {},
      homeTel: {},
      fax: {},
      mobileTel: {},
      examineeDegree: {}, //{notNull: required}, //應考學歷
      highestDegree: {}, //{notNull: required}, //最高學歷
      firstexamDate: {}, //{notNull: required}, //第一階段考試及格或免試證明-日期
      firstexamCertNo: {}, //證號
      idno: {}, //身分證字號
      isEnable: {},
      jobType: {},
      jobKind: {},
      salaryRange: {},
      workPlace: {},
      jobDemand: {},
      remark: {},
      addrZip: {},
      addrCity: {},
      addrTown: {},
      addrOther: {},
    };

    const groups = [
      ['permanentAddrCity', 'permanentAddrTown', 'permanentAddrOther'],
      ['contactAddrCity', 'contactAddrTown', 'contactAddrOther'],
    ];

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault, groups);

    const createDefaultValue = (data: GeoMember) => {
      Object.assign(formDefault, data);
      // formDefault.birthday = formatToString(formDefault.birthday, '/', '-');
      formDefault.firstexamDate = formatToString(formDefault.firstexamDate, '/', '-');
      formDefault.birthday = parseToDate(formDefault.birthday, '-');
      formDefault.workPlace = formDefault.workPlace.split(',');
      // formDefault.type1List = setTypeList(formDefault.type1List);
      // formDefault.type2List = setTypeList(formDefault.type2List);
      // formDefault.type3List = setTypeList(formDefault.type3List);
      reset();
    };

    console.log(formDefault);

    function setTypeList(typeList: string) {
      return typeList
        .split(',')
        .map((item, index) => {
          if (item === '1') {
            // return formOptions.type1List[index].text;
            return index < 10 ? '0' + (index + 1) : (index + 1).toString();
          } else {
            return item;
          }
        })
        .filter(item => item !== '0');
    }

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);

    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const toQueryView = () => {
      handleBack({ isReload: unref(isReload), isNotKeepAlive: true });
      isReload.value = false;
    };

    return {
      formStatusEnum,
      RoleEnum,
      formStatusRef,
      formOptions,
      formOptionsFormatter,
      dataPromise,
      $v,
      form,
      toQueryView,
      validateState,
      reset,
      isSamePermanentAddr,
      createDefaultValue,
      cityOptions,
    };
  },
});
</script>
<style></style>

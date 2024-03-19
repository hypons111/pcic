<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 匯入日期 -->
            <i-form-group-check content-cols="4" :label="$t('label.importDate') + '：'" :item="$v.importDate">
              <!-- <div>{{ data.importDate }}</div> -->
              <div>{{ $v.importDate.$model | dateToString }}</div>
              <!-- <div>{{ $v.importDate.$model }}</div> -->
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 科別 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.subject') + '：'" :item="$v.subject">
              <div v-if="formStatus === FormStatusEnum.READONLY">
                <!-- {{ formOptionsFormatter(formOptions.subject, data.subject) }} -->
                {{ formOptionsFormatter(formOptions.subject, $v.subject.$model) }}
              </div>
              <b-form-select v-else v-model="$v.subject.$model" :options="formOptions.subject" :state="validateState($v.subject)">
              <!-- <b-form-select v-else v-model="data.subject" :options="formOptions.subject" :state="validateState($v.subject)"> -->
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          
          <b-form-row>
            <!-- 冊報序號 -->
            <i-form-group-check content-cols="4" :label="$t('label.examno1') + '：'" :item="$v.examno1">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.examno1.$model }}</div>
              <!-- <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.examno1 }}</div> -->
              <b-form-input v-else v-model="$v.examno1.$model" :state="validateState($v.examno1)" type="number" trim lazy></b-form-input>
              <!-- <b-form-input v-else v-model="data.examno1" :state="validateState($v.examno1)" type="number" trim lazy></b-form-input> -->
            </i-form-group-check>
            <!-- 入場證號(不可重複) -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.examno2No') + '：'" :item="$v.examno2">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.examno2.$model }}</div>
              <!-- <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.examno2 }}</div> -->
              <b-form-input v-else v-model="$v.examno2.$model" :state="validateState($v.examno2)" type="number" trim lazy></b-form-input>
              <!-- <b-form-input v-else v-model="data.examno2" :state="validateState($v.examno2)" type="number" trim lazy></b-form-input> -->
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 名次 -->
            <i-form-group-check content-cols="4" :label="$t('label.ranking') + '：'" :item="$v.ranking">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.ranking.$model }}</div>
              <!-- <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.ranking }}</div> -->
              <b-form-input v-else v-model="$v.ranking.$model" :state="validateState($v.ranking)" type="number" trim lazy></b-form-input>
              <!-- <b-form-input v-else v-model="data.ranking" :state="validateState($v.ranking)" type="number" trim lazy></b-form-input> -->
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 姓名 -->
            <i-form-group-check labelStar class="col-12" label-cols="2" content-cols="4" :label="$t('label.name') + '：'" :item="$v.name">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.name.$model }}</div>
              <!-- <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.name }}</div> -->
              <b-form-input v-else v-model="$v.name.$model" :state="validateState($v.name)" trim lazy></b-form-input>
              <!-- <b-form-input v-else v-model="data.name" :state="validateState($v.name)" trim lazy></b-form-input> -->
            </i-form-group-check>
            <!-- 身分證字號 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.idno') + '：'" :item="$v.idno">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.idno.$model }}</div>
              <!-- v-model="$v.idno.$model" -->
              <!-- <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.idno }}</div> -->
              <b-form-input
                v-else
                v-model="$v.idno.$model"
                :state="validateState($v.idno)"
                id="idnoInputPopover"
                maxlength="10"
                trim
                lazy
              ></b-form-input>
              <!-- @change="queryEngMember" -->
              <b-popover
                v-if="formStatus !== FormStatusEnum.READONLY"
                target="idnoInputPopover"
                placement="right"
                triggers="hover focus"
                title="身分證字號查詢"
                content="依據身分證字號帶入技師資料。"
              ></b-popover>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 性別 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.sex') + '：'" :item="$v.sex">
              <b-form-select v-model="$v.sex.$model" :options="formOptions.sex" :state="validateState($v.sex)">
              <!-- <b-form-select v-model="data.sex" :options="formOptions.sex" :state="validateState($v.sex)"> -->
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>

            <!-- 出生日期 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.birthday') + '：'" :item="$v.birthday">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.birthday.$model | dateToString }}</div>
              <!-- <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.birthday }}</div> -->
              <!-- v-model="$v.birthday.$model" -->
              <i-date-picker
                v-else
                v-model="$v.birthday.$model"
                :state="validateState($v.birthday)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 地址 -->
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="9"
              :label="$t('label.addr') + '：'"
              :item="[$v.contactAddrTown, $v.contactAddrOther, $v.contactAddrCity]"
            >
              <i-address-picker
                :zip.sync="$v.contactAddrZip.$model"
                :city.sync="$v.contactAddrCity.$model"
                :town.sync="$v.contactAddrTown.$model"
                :other.sync="$v.contactAddrOther.$model"
                :cityState="validateState($v.contactAddrCity)"
                :townState="validateState($v.contactAddrTown)"
                :otherState="validateState($v.contactAddrOther)"
                :cols="[1, 2, 2, 5]"
              >
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 學歷 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="5" :label="$t('label.education') + '：'" :item="$v.education">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.education.$model }}</div>
              <!-- <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.education }}</div> -->
              <b-form-input v-else v-model="$v.education.$model" :state="validateState($v.education)" trim lazy></b-form-input>
              <!-- <b-form-input v-else v-model="data.education" :state="validateState($v.education)" trim lazy></b-form-input> -->
            </i-form-group-check>
          </b-form-row>
        </div>

        <div class="text-center mb-2">
          <!-- 儲存 -->
          <i-button v-if="formStatus !== FormStatusEnum.READONLY" type="save" @click="" />
          <!-- 返回 -->
          <i-button type="arrow-left" @click="toLast" />
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import EngEngrSubjectpointService from '@/components/eng/eng0104/eng-engr-subjectpoint.service'; 
import { useBvModal } from '@/shared/modal';
import { useNotification } from '@/shared/notification';
import { useValidation, validateState } from '@/shared/form';
import { handleBack } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { reactive, ref, toRefs, onMounted, defineComponent, unref, toRef } from '@vue/composition-api';
import { required, maxLength, nationalId, between, queryEngrByIdno } from '@/shared/validators';
import { ApplyItemEnum, FormStatusEnum, MemberTypeEnum, StatusEnum } from '@/shared/enum';
import { IEngEngrSubjectpoint, EngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import { IEngEngrExam, EngEngrExam } from '@/shared/model/eng/eng-engr-exam.model';
import { useStore } from '@u3u/vue-hooks';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';
import { navigateByNameAndParams } from '@/router/router';

interface propsType {
  // engEngrSubjectpoint: IEngEngrSubjectpoint;
  EngEngrExam: IEngEngrExam;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0107EditInfo',
  props: {
    EngEngrExam: {
      required: false,
      type: Object,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    criteria: {
      required: false,
      type: Object,
    },
  },
  components: {
    ISwitchCase,
  },
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    const engEngrExamProp = toRef(props, 'EngEngrExam');

    const $bvModal = useBvModal();
    const notificationService = useNotification();
    const formStatusRef = ref(props.formStatus);

    // 執行結果
    const dataPromise = ref(null);

    const isReload = ref(false);
    const store = useStore();
    const engAccount = {
      userId: store.value.getters.account.id,
      idno: store.value.getters.account.idn,
    };

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.sex = [
        { value: '0', text: '男' },
        { value: '1', text: '女' },
      ];
    };

    const formOptions = reactive({
      subject: [],
      sex: [],
    });

    let formDefault = {
      importDate: null,
      subject: '',
      examno1: '',
      examno2No: '',
      ranking: '',
      name: '',
      idno: '',
      sex: '',
      birthday: null,
      contactAddrZip: '',
      contactAddrTown: '',
      contactAddrOther: '',
      contactAddrCity: '',
      education: '',
    };

    Object.assign(formDefault, engEngrExamProp.value);
    formDefault.importDate = parseToDate(formDefault.importDate, '-'); 
    formDefault.birthday = parseToDate(formDefault.birthday, '-');

    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      importDate: {},
      subject: {},
      examno1: {},
      examno2: {},
      ranking: {},
      name: {},
      idno: {},
      sex: {},
      birthday: {},
      contactAddrZip: {},
      contactAddrTown: {},
      contactAddrOther: {},
      contactAddrCity: {},
      addrZip: {},
      addrCity: {},
      addrTown: {},
      addrOther: {},
      education: {},
      /*applyDate: { required: required },
      contractorName: {},
      idno: { required: required, nationalId: nationalId, isEngrIdno: queryEngrByIdno },
      chName: {},
      theme: { required: required, maxLength: maxLength(200) },
      content: { required: required, maxLength: maxLength(2000) },
      law: { required: required },
      subject: { required: required },
      point: { required: required, between: between(0, 999) },
      checkDesc: { maxLength: maxLength(200) },
      remark: { maxLength: maxLength(2000) },*/
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      handleQuery();
      getAllSysCodes();
    });

    function toLast() {
      navigateByNameAndParams('Eng0107Query', {
        isReload: isReload.value, isNotKeepAlive: true,
        formStatus: formStatusRef
      } );
      isReload.value = false;
    }
    
    const handleQuery = () => {
      dataPromise.value = axios
        .post('/eng-engr-exams/criteria-jpa', engAccount) //後端分頁
        .then(({ data }) => {
          Object.assign(formDefault, data.content[0]);
          formDefault.importDate = parseToDate(formDefault.importDate, '-'); 
          formDefault.birthday = parseToDate(formDefault.birthday, '-');
          Object.assign(form, formDefault);
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };
    // const data = {
    //     importDate: '99/07/03',
    //     subject: '0',
    //     examno1: '',
    //     examno2No: '109071302',
    //     ranking: '',
    //     name: '郭慶煌',
    //     idno: 'E102563309',
    //     sex: '1',
    //     birthday: '44/01/14',
    //     education: '',
    // }

    return {
      FormStatusEnum,
      $v,
      toLast,
      validateState,
      formOptions,
      formOptionsFormatter,
      dataPromise,
      handleQuery,
      // data,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>

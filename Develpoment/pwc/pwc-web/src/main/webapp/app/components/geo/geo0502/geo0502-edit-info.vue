<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0" style="text-align: center">工作職缺資訊</h5>
        </div>
        <div class="card-header">
          <h5 class="m-0">
            <!-- <font-awesome-icon icon="project-diagram"></font-awesome-icon> -->
            工作職缺詳細資料
          </h5>
        </div>
        <div class="card-body">
          <!-- 刊登日期起 -->
          <b-form-row>
            <i-form-group-check labelStar class="col-6" label-cols="3" content-cols="6" :label="$t('label.publishDateStart') + '：'">
              <i-date-picker v-model="$v.publishDateStart.$model" :state="validateState($v.publishDateStart)"></i-date-picker>
            </i-form-group-check>

            <!-- 刊登日期起屹 -->
            <i-form-group-check labelStar class="col-6" label-cols="3" content-cols="6" :label="$t('label.publishDateEnd') + '：'">
              <i-date-picker v-model="$v.publishDateEnd.$model" :state="validateState($v.publishDateEnd)"></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <!-- 工作職稱 -->
          <b-form-row>
            <i-form-group-check
              labelStar
              class="col-6"
              label-cols="3"
              content-cols="6"
              :label="$t('label.jobTitle') + '：'"
              :item="$v.jobTitle"
            >
              <b-form-input v-model="$v.jobTitle.$model" :state="validateState($v.jobTitle)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 工作屬性 -->
            <i-form-group-check
              labelStar
              class="col-6"
              label-cols="3"
              content-cols="6"
              :label="$t('label.jobType') + '：'"
              :item="$v.jobType"
            >
              <b-form-select v-model="$v.jobType.$model" :options="formOptions.jobType" :state="validateState($v.jobType)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <!-- 類別 -->
          <b-form-row>
            <i-form-group-check
              labelStar
              class="col-6"
              label-cols="3"
              content-cols="6"
              :label="$t('label.jobKind') + '：'"
              :item="$v.jobKind"
            >
              <b-form-select v-model="$v.jobKind.$model" :options="formOptions.jobKind" :state="validateState($v.jobKind)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 工作待遇 -->
            <i-form-group-check
              labelStar
              class="col-6"
              label-cols="3"
              content-cols="6s"
              :label="$t('label.salaryRange1') + '：'"
              :item="$v.salaryRange"
            >
              <b-form-select v-model="$v.salaryRange.$model" :options="formOptions.salaryRange" :state="validateState($v.salaryRange)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <!-- 需求人數 -->
          <b-form-row>
            <i-form-group-check
              labelStar
              class="col-6"
              label-cols="3"
              content-cols="6"
              :label="$t('label.personCount') + '：'"
              :item="$v.personCount"
            >
              <b-form-input v-model="$v.personCount.$model" :state="validateState($v.personCount)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>
      </div>

      <div class="card">
        <div class="card-header">
          <h5 class="m-0">內容-第一類調查、試驗或監測</h5>
        </div>
        <div class="card-body">
          <i-form-group-check class="col-12" label-cols="0" content-cols="12" :item="$v.type1ListShow">
            <div v-for="option in formOptions.type1List" :key="option.value" class="col-4 d-inline-block">
              <b-form-checkbox v-model="$v.type1ListShow.$model" :value="option.value" :state="validateState($v.type1ListShow)">
                {{ option.text }}
              </b-form-checkbox>
            </div>
          </i-form-group-check>
          <!-- {{ $v.type1ListShow.$model }} -->
          <br />
        </div>
      </div>
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">內容-第二類規劃、分析或設計</h5>
        </div>
        <div class="card-body">
          <i-form-group-check class="col-12" label-cols="0" content-cols="12" :item="$v.type2ListShow">
            <div v-for="option in formOptions.type2List" :key="option.value" class="col-4 d-inline-block">
              <b-form-checkbox v-model="$v.type2ListShow.$model" :value="option.value" :state="validateState($v.type2ListShow)">
                {{ option.text }}
              </b-form-checkbox>
            </div>
          </i-form-group-check>
        </div>
      </div>
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">內容-第三類施工、建造或管理</h5>
        </div>
        <div class="card-body">
          <i-form-group-check class="col-12" label-cols="0" content-cols="12" :item="$v.type3ListShow">
            <div v-for="option in formOptions.type3List" :key="option.value" class="col-4 d-inline-block">
              <b-form-checkbox v-model="$v.type3ListShow.$model" :value="option.value" :state="validateState($v.type3ListShow)">
                {{ option.text }}
              </b-form-checkbox>
            </div>
          </i-form-group-check>
        </div>
      </div>
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">工作地點(必填欄位)</h5>
        </div>
        <div class="card-body">
          <i-form-group-check class="col-12" label-cols="0" content-cols="12" :item="$v.workPlaceListShow">
            <div v-for="option in cityOptions" :key="option.value" class="col-4 d-inline-block">
              <b-form-checkbox v-model="$v.workPlaceListShow.$model" :value="option.value" :state="validateState($v.workPlaceListShow)"
                >{{ option.text }}
              </b-form-checkbox>
            </div>
          </i-form-group-check>
          <div>
            <!-- {{ cityOptions }} -->
            <!-- {{ $v.workPlaceList.$model }} -->
          </div>
        </div>
      </div>
      <!-- 其他條件 -->
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">其他條件(必填欄位)</h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="0" content-cols="12" :item="$v.otherComment">
              <b-form-textarea rows="5" v-model="$v.otherComment.$model" :state="validateState($v.otherComment)"> </b-form-textarea>
            </i-form-group-check>
          </b-form-row>
        </div>
      </div>
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">聯絡方式(必填欄位)</h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 行動電話 -->
            <i-form-group-check
              labelStar
              class="col-6"
              label-cols="3"
              content-cols="9"
              :label="$t('label.mobileTel') + '：'"
              :item="$v.mobileTel"
            >
              <b-form-input v-model="$v.mobileTel.$model" :state="validateState($v.mobileTel)" isMobile trim></b-form-input>
            </i-form-group-check>
            <!-- 連絡電話 -->
            <i-form-group-check
              labelStar
              class="col-6"
              label-cols="3"
              content-cols="9"
              :label="$t('label.contactTel') + '：'"
              :item="$v.contactTel"
            >
              <b-form-input v-model="$v.contactTel.$model" :state="validateState($v.contactTel)" showExt trim></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 連絡人 -->
          <b-form-row>
            <i-form-group-check
              labelStar
              class="col-6"
              label-cols="3"
              content-cols="6"
              :label="$t('label.contactPerson') + '：'"
              :item="$v.contactPerson"
            >
              <b-form-input v-model="$v.contactPerson.$model" :state="validateState($v.contactPerson)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>
      </div>
      <!-- 是否公開-->
      <b-form-row>
        <i-form-group-check
          labelStar
          class="col-10"
          label-cols="2"
          content-cols="8"
          :label="$t('label.isPublic') + '：'"
          :item="$v.isPublic"
        >
          <b-form-radio-group v-model="$v.isPublic.$model" :options="formOptions.isPublic" :state="validateState($v.isPublic)">
          </b-form-radio-group>
        </i-form-group-check>
      </b-form-row>
      <!-- 狀態-->
      <b-form-row>
        <i-form-group-check
          labelStar
          class="col-10"
          label-cols="2"
          content-cols="8"
          :label="$t('label.publishStatus') + '：'"
          :item="$v.publishStatus"
        >
          <b-form-radio-group
            v-model="$v.publishStatus.$model"
            :options="formOptions.publishStatus"
            :state="validateState($v.publishStatus)"
          >
          </b-form-radio-group>
        </i-form-group-check>
      </b-form-row>
      <div class="text-center mb-3">
        <i-button class="ml-1" type="save" @click="submitForm"></i-button>
        <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
        <i-button class="ml-1" type="arrow-left" @click="toQueryView"></i-button>
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
import { parseToDate } from '@/shared/date/minguo-calendar-utils';
import { FormStatusEnum, RoleEnum } from '@/shared/enum';
import { formOptionsFormatter } from '@/shared/formatter/common';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { useStore } from '@u3u/vue-hooks';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { GeoJob } from '@/shared/model/geoModel/geo-job.model';

interface propsType {
  geoJob: GeoJob;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'geo0502Edit',
  props: {
    geoJob: {
      required: false,
      type: Object,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
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
    // const geoJobProp = toRef(props, 'geoJob');
    const admSysCodeService = new AdmSysCodeService();
    const formStatusRef: Ref<FormStatusEnum> = ref(props.formStatus);
    const isCheckRequired = ref(false); // 儲存 = false; 送出 = true

    const { geoJob, criteria } = toRefs(props);
    // const role = ref(props.role).value;

    //從使用者撈資料
    const store = useStore();
    const engAccount = {
      userId: store.value.getters.account.id,
      idno: store.value.getters.account.idn,
    };

    const cityOptions = ref(undefined);

    let test = ref(undefined);
    onMounted(() => {
      createDefaultValue(props.geoJob);
      getAllSysCodes();
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
      formOptions.isPublic = [
        { value: '0', text: '公開' },
        { value: '1', text: '不公開' },
      ];
      formOptions.publishStatus = [
        { value: '0', text: '處理中' },
        { value: '1', text: '刊登中' },
        { value: '2', text: '超過刊登時間' },
        { value: '3', text: '未核准' },
      ];
    }
    const formOptions = reactive({
      jobType: [],
      jobKind: [],
      salaryRange: [],
      isPublic: [],
      publishStatus: [],
      type1List: [],
      type2List: [],
      type3List: [],
    });

    //針對form的表示，之後要轉成後端接收的data Type
    let formDefault = new GeoJob();
    // let formDefault = {
    //   jobTitle: '',
    //   jobType: '',
    //   workPlaceList: '',
    //   workPlaceListDesc: '',
    //   salaryRange: '',
    //   otherComment: '',
    //   publishDateStart: '',
    //   publishDateEnd: '',
    //   contactPerson: '',
    //   mobileTel: '',
    //   contactTel: '',
    //   isPublic: '',
    // };
    // Object.assign(formDefault, geoJobProp.value);
    // formDefault.publishDateStart = parseToDate(formDefault.publishDateStart, '-');
    // formDefault.publishDateEnd = parseToDate(formDefault.publishDateEnd, '-');
    let form = reactive(Object.assign({}, formDefault));
    const rules = {
      jobTitle: { required: required },
      jobType: { required: required },
      jobKind: { required: required },
      workPlaceList: { required: required },
      // workPlaceListDesc: { required: required },
      salaryRange: { required: required },
      otherComment: { required: required },
      publishDateStart: { required: required },
      publishDateEnd: { required: required },
      contactPerson: { required: required },
      mobileTel: { required: required },
      contactTel: { required: required },
      isPublic: { required: required },
      personCount: { required: required },
      type1List: { required: required },
      type2List: { required: required },
      type3List: { required: required },
      publishStatus: { required: required },
      type1ListShow: { required: required },
      type2ListShow: { required: required },
      type3ListShow: { required: required },
      workPlaceListShow: { required: required },
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (formStatusRef.value === FormStatusEnum.EDIT) {
            $bvModal.msgBoxConfirm('確定修改一筆工作職缺記錄？').then((isOK: boolean) => {
              if (isOK) {
                putForm();
              }
            });
          } else {
            $bvModal.msgBoxConfirm('請再次確認資料是否填寫正確!').then((isOK: boolean) => {
              if (isOK) {
                putForm();
              }
            });
          }
        } else {
          $bvModal.msgBoxOk('欄位資料不可為空！');
        }
      });
    };
    const putForm = () => {
      let url: string;
      form.type1List = resetTypeList(form.type1ListShow, formOptions.type1List);
      form.type2List = resetTypeList(form.type2ListShow, formOptions.type2List);
      form.type3List = resetTypeList(form.type3ListShow, formOptions.type3List);
      form.workPlaceList = form.workPlaceListShow.join(',');
      form.updateTime = new Date();
      form.updateUser = engAccount.userId;
      if (formStatusRef.value === FormStatusEnum.EDIT) {
        url = `/geo-jobs/${formDefault.id}/GEO0502`;
        axios
          .put(url, form)
          .then(({ data }) => {
            createDefaultValue(data);
            isReload.value = true;
            notificationService.success(`儲存成功`);
          })
          .catch(notificationErrorHandler(notificationService));
      } else {
        url = `/geo-jobs`;
        axios
          .post(url, form)
          .then(({ data }) => {
            createDefaultValue(data);
            isReload.value = true;
            notificationService.success(`儲存成功`);
          })
          .catch(notificationErrorHandler(notificationService));
      }
    };

    const createDefaultValue = (data: GeoJob) => {
      Object.assign(formDefault, data);
      formDefault.type1ListShow = setTypeList(formDefault.type1List);
      formDefault.type2ListShow = setTypeList(formDefault.type2List);
      formDefault.type3ListShow = setTypeList(formDefault.type3List);
      formDefault.workPlaceListShow = formDefault.workPlaceList ? formDefault.workPlaceList.split(',') : [];
      reset();
    };

    function setTypeList(typeList: string) {
      if (!typeList) return [];
      return typeList
        .split(',')
        .map((item, index) => {
          if (item === '1') {
            return index < 9 ? '0' + (index + 1) : (index + 1).toString();
          } else {
            return item;
          }
        })
        .filter(item => item !== '0');
    }
    function resetTypeList(typeList: string[], formOptionsTypeList: { value: string; text: string }[]) {
      const newTypeList = [];
      for (let i = 1; i <= formOptionsTypeList.length; i++) {
        if (typeList.map(i => Number(i)).includes(i)) {
          newTypeList.push('1');
        } else {
          newTypeList.push('0');
        }
      }
      // return newTypeList;
      return newTypeList.toString();
    }

    const toQueryView = () => {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    };

    return {
      formStatusEnum,
      RoleEnum,
      formStatusRef,
      formOptions,
      formOptionsFormatter,
      $v,
      form,
      toQueryView,
      validateState,
      reset,
      submitForm,
      createDefaultValue,
      cityOptions,
      test,
    };
  },
});
</script>
<style></style>

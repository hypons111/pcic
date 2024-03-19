<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0" style="text-align: center">
            {{ $v.companyName.$model }}徵求{{ $v.jobTitle.$model }}{{ $v.personCount.$model }}名
          </h5>
        </div>
        <div class="card-header">
          <h5 class="m-0">
            <!-- <font-awesome-icon icon="project-diagram"></font-awesome-icon> -->
            工作職缺詳細資料
          </h5>
        </div>
        <div class="card-body">
          <!-- 職缺狀態 -->
          <b-form-row :class="{ 'text-red': $v.publishStatus.$model === '2' }">
            <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="9"
              :label="$t('label.publishStatus') + '：'"
              :item="$v.publishStatus"
            >
              <div>
                {{ formOptionsFormatter(formOptions.publishStatus, $v.publishStatus.$model) }}
              </div>
            </i-form-group-check>
          </b-form-row>
          <!-- 職稱 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="3" content-cols="9" :label="$t('label.jobTitle') + '：'" :item="$v.jobTitle">
              <div>{{ $v.jobTitle.$model }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- 類別 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="3" content-cols="9" :label="$t('label.jobKind') + '：'" :item="$v.jobKind">
              <div>{{ formOptionsFormatter(formOptions.jobKind, $v.jobKind.$model) }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- 工作屬性 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="3" content-cols="9" :label="$t('label.jobType') + '：'" :item="$v.jobType">
              <div>{{ formOptionsFormatter(formOptions.jobType, $v.jobType.$model) }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- 工作地點 -->
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="9"
              :label="$t('label.workPlaceList') + '：'"
              :item="$v.workPlaceList"
            >
              <!-- <div>{{ $v.workPlaceList.$model }}</div> -->
              <div>{{ formOptionsFormatter(cityOptions, $v.workPlaceList.$model) }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- 工作地點描述 -->
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="9"
              :label="$t('label.workPlaceListDesc') + '：'"
              :item="$v.workPlaceListDesc"
            >
              <div>{{ $v.workPlaceListDesc.$model }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- 工作待遇 -->
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="9"
              :label="$t('label.salaryRange1') + '：'"
              :item="$v.salaryRange"
            >
              <div>{{ formOptionsFormatter(formOptions.salaryRange, $v.salaryRange.$model) }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- 其他條件 -->
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="9"
              :label="$t('label.otherComment') + '：'"
              :item="$v.otherComment"
            >
              <div>{{ $v.otherComment.$model }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- 刊登日期 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="3" content-cols="9" :label="$t('label.publishDate') + '：'">
              <div>{{ formatToString($v.publishDateStart.$model, '/', '-') }}~{{ formatToString($v.publishDateEnd.$model, '/', '-') }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- 聯絡人 -->
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="2"
              :label="$t('label.contactPerson') + '：'"
              :item="$v.contactPerson"
            >
              <div>{{ $v.contactPerson.$model }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- 聯絡方式 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="3" content-cols="9" :label="$t('label.contact') + '：'">
              <div>手機 : {{ $v.mobileTel.$model }}，電話 : {{ $v.contactTel.$model }}</div>
            </i-form-group-check>
          </b-form-row>
        </div>
      </div>
      <b-form-row>
        <b-col cols="4">
          <b-card no-body>
            <b-card-header><h6 class="m-0">內容-第一類調查、試驗或監測</h6></b-card-header
            ><b-card-body style="height: 300px; overflow-y: overlay">
              <!-- {{ $v.type1ListShow.$model }} -->
              <div v-for="s in $v.type1ListShow.$model" :key="s">{{ formOptionsFormatter(formOptions.type1List, s) }}</div>
            </b-card-body>
          </b-card>
        </b-col>
        <b-col cols="4">
          <b-card no-body>
            <b-card-header><h6 class="m-0">內容-第二類規劃、分析或設計</h6></b-card-header
            ><b-card-body style="height: 300px; overflow-y: overlay">
              <!-- {{ $v.type2List.$model }} -->
              <div v-for="s in $v.type2ListShow.$model" :key="s">{{ formOptionsFormatter(formOptions.type2List, s) }}</div>
            </b-card-body>
          </b-card>
        </b-col>
        <b-col cols="4">
          <b-card no-body>
            <b-card-header><h6 class="m-0">內容-第三類施工、建造或管理</h6></b-card-header>
            <b-card-body style="height: 300px; overflow-y: overlay">
              <!-- {{ $v.type3List.$model }} -->
              <div v-for="s in $v.type3ListShow.$model" :key="s">{{ formOptionsFormatter(formOptions.type3List, s) }}</div>
            </b-card-body>
          </b-card>
        </b-col>
      </b-form-row>
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">工作職缺審核狀態</h5>
        </div>
        <div class="card-body">
          <!-- 是否核准-->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="3" content-cols="5" :label="$t('label.isApprove') + '：'" :item="$v.isApprove">
              <b-form-select v-model="$v.isApprove.$model" :options="formOptions.isApprove" :state="validateState($v.isApprove)">
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <!--退回原因 -->
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="8"
              :label="$t('label.rejectReason') + '：'"
              :item="$v.rejectReason"
            >
              <b-form-textarea v-model="$v.rejectReason.$model" :state="validateState($v.rejectReason)" trim lazy> </b-form-textarea>
            </i-form-group-check>
          </b-form-row>
        </div>
      </div>
      <div class="text-center mt-3">
        <i-button class="ml-1" type="save" @click="submitForm"></i-button>
        <i-button class="ml-1" type="arrow-left" @click="toQueryView"></i-button>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { onMounted, reactive, Ref, ref, toRef, toRefs, watch, defineComponent, unref } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { handleBack, navigateByNameAndParams } from '@/router/router';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { FormStatusEnum, RoleEnum } from '@/shared/enum';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { useStore } from '@u3u/vue-hooks';
import { GeoJob } from '@/shared/model/geoModel/geo-job.model';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import axios from 'axios';

interface propsType {
  geoJob: GeoJob;
  formStatus: FormStatusEnum;
  criteria: any;
  role: RoleEnum;
}

export default defineComponent<propsType>({
  name: 'geo0603Edit',
  props: {
    geoJob: {
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

    const { geoJob, role, criteria } = toRefs(props);

    // const role = ref(props.role).value;

    //從使用者撈資料
    const store = useStore();
    const engAccount = {
      userId: store.value.getters.account.id,
      idno: store.value.getters.account.idn,
    };
    const cityOptions = ref(undefined);

    onMounted(() => {
      getAllSysCodes();
      createDefaultValue(props.geoJob);
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
    }

    const formOptions = reactive({
      publishStatus: [
        { value: '0', text: '處理中' },
        { value: '1', text: '刊登中' },
        { value: '2', text: '超過刊登時間' },
        { value: '3', text: '未核准' },
      ],
      jobType: [],
      jobKind: [],
      salaryRange: [],
      isPublic: [],
      type1List: [],
      type2List: [],
      type3List: [],
      isApprove: [
        { value: '0', text: '待審核' },
        { value: '1', text: '審核通過' },
        { value: '2', text: '審核不通過' },
      ],
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

    let form = reactive(Object.assign({}, formDefault));
    const rules = {
      companyName: {},
      personCount: {},
      jobTitle: {},
      jobType: {},
      jobKind: {},
      workPlaceList: {},
      workPlaceListDesc: {},
      salaryRange: {},
      otherComment: {},
      publishDateStart: {},
      publishDateEnd: {},
      contactPerson: {},
      mobileTel: {},
      contactTel: {},
      isPublic: {},
      type1List: {},
      type2List: {},
      type3List: {},
      publishStatus: {},
      isApprove: {},
      rejectReason: {},
      type1ListShow: {},
      type2ListShow: {},
      type3ListShow: {},
    };

    const groups = [
      ['permanentAddrCity', 'permanentAddrTown', 'permanentAddrOther'],
      ['contactAddrCity', 'contactAddrTown', 'contactAddrOther'],
    ];

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault, groups);

    const createDefaultValue = (data: GeoJob) => {
      Object.assign(formDefault, data);
      formDefault.type1ListShow = setTypeList(formDefault.type1List);
      formDefault.type2ListShow = setTypeList(formDefault.type2List);
      formDefault.type3ListShow = setTypeList(formDefault.type3List);
      // expiredDate();
      reset();
    };

    function setTypeList(typeList: string) {
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

    // modal物件
    const $bvModal = useBvModal();

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('確定審核並送出一筆工作職缺記錄？').then((isOK: boolean) => {
            if (isOK) {
              putForm();
            }
          });
        }
      });
    };
    const putForm = () => {
      let url: string;
      form.type1List = resetTypeList(form.type1ListShow, formOptions.type1List);
      form.type2List = resetTypeList(form.type2ListShow, formOptions.type2List);
      form.type3List = resetTypeList(form.type3ListShow, formOptions.type3List);
      // form.workPlaceList = form.workPlaceList.join(',');
      form.updateTime = new Date();
      form.updateUser = engAccount.userId;
      url = `/geo-jobs/${formDefault.id}/GEO0603`;
      axios
        .put(url, form)
        .then(({ data }) => {
          createDefaultValue(data);
          isReload.value = true;
          notificationService.success(`儲存成功`);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);

    // 是否需要重新查詢
    const isReload = ref(false);

    const toQueryView = () => {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    };

    const expiredDate = () => {
      if (formDefault.publishDateEnd < new Date()) {
        formDefault.publishStatus = '2';
      }
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
      submitForm,
      formatToString,
    };
  },
});
</script>
<style scoped>
.text-red {
  color: red;
}
</style>

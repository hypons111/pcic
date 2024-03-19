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
          <!-- 是否公開首頁 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="3" content-cols="9" :label="$t('label.isPublic') + '：'" :item="$v.isPublic">
              <div>{{ formOptionsFormatter(formOptions.isPublic, $v.isPublic.$model) }}</div>
            </i-form-group-check>
          </b-form-row>
          <div class="text-center mb-3">
            <i-button class="ml-1" type="arrow-left" @click="toQueryView"></i-button>
          </div>
        </div>
      </div>
      <b-form-row>
        <b-col cols="4">
          <b-card no-body>
            <b-card-header><h6 class="m-0">內容-第一類調查、試驗或監測</h6></b-card-header
            ><b-card-body style="height: 300px; overflow-y: overlay">
              <!-- {{ $v.type1List.$model }} -->
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

interface propsType {
  geoJob: GeoJob;
  formStatus: FormStatusEnum;
  criteria: any;
  role: RoleEnum;
}

export default defineComponent<propsType>({
  name: 'geo0502Read',
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
        // { value: '0', text: '處理中' },
        // { value: '1', text: '刊登中' },
        // { value: '2', text: '超過刊登時間' },
        // { value: '3', text: '未核准' },
      ],
      jobType: [],
      jobKind: [],
      salaryRange: [],
      isPublic: [],
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
      // formDefault.publishDateStart = formatToString(formDefault.publishDateStart, '/', '-');
      // formDefault.publishDateEnd = formatToString(formDefault.publishDateEnd, '/', '-');
      formDefault.type1ListShow = setTypeList(formDefault.type1List);
      formDefault.type2ListShow = setTypeList(formDefault.type2List);
      formDefault.type3ListShow = setTypeList(formDefault.type3List);
      reset();
    };

    function setTypeList(typeList: string) {
      return typeList
        .split(',')
        .map((item, index) => {
          if (item === '1') {
            // return formOptions.type1List[index].text;
            return index < 9 ? '0' + (index + 1) : (index + 1).toString();
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
      dataPromise,
      $v,
      form,
      toQueryView,
      validateState,
      reset,
      isSamePermanentAddr,
      createDefaultValue,
      cityOptions,
      formatToString,
    };
  },
});
</script>
<style></style>

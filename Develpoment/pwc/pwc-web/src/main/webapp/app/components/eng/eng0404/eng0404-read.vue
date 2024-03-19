<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            課程詳細資料
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 所屬單位 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.chNameUnit') + '：'" :item="$v.chNameUnit">
              <div>{{ data.chNameUnit }}</div>
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.chNameUnit.$model }}</div>
              <b-form-input v-else v-model="$v.chNameUnit.$model" :state="validateState($v.chNameUnit)" trim lazy> </b-form-input> -->
            </i-form-group-check>
            <!-- 主辦單位 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.organizer') + '：'" :item="$v.organizer">
              {{ data.organizer }}
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.organizer.$model }}</div>
              <b-form-input v-else v-model="$v.organizer.$model" :state="validateState($v.organizer)" trim lazy> </b-form-input> -->
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 課程名稱 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.courseName') + '：'" :item="$v.courseName">
              {{ data.courseName }}
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.courseName.$model }}</div>
              <b-form-input v-else v-model="$v.courseName.$model" :state="validateState($v.courseName)" trim lazy> </b-form-input> -->
            </i-form-group-check>
            <!-- 申請日期 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.applyDate') + '：'" :item="$v.applyDate">
              {{ data.applyDate }}
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY || role === RoleEnum.USER">{{ $v.applyDate.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.applyDate.$model"
                :state="validateState($v.applyDate)"
                placeholder="yyy/MM/dd"
              ></i-date-picker> -->
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 課程日期 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.courseDate') + '：'" :item="$v.courseDate">
              {{ data.courseDateStart }}
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY || role === RoleEnum.USER">{{ $v.courseDate.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.courseDateStart.$model"
                :state="validateState($v.courseDateStart)"
                placeholder="yyy/MM/dd"
              ></i-date-picker> -->
              ~<br/>
              {{ data.courseDateEnd }}
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY || role === RoleEnum.USER">{{ $v.courseDate.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.courseDateEnd.$model"
                :state="validateState($v.courseDateEnd)"
                placeholder="yyy/MM/dd"
              ></i-date-picker> -->
            </i-form-group-check>
            <!-- 上傳截止日期 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.uploadDeadline') + '：'" :item="$v.uploadDeadline">
              {{ data.uploadDeadline }}
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY || role === RoleEnum.USER">{{ $v.uploadDeadline.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.uploadDeadline.$model"
                :state="validateState($v.uploadDeadline)"
                placeholder="yyy/MM/dd"
              ></i-date-picker> -->
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 上課地點 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.place') + '：'" :item="$v.place">
              {{ data.place }}
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.place.$model }}</div>
              <b-form-input v-else v-model="$v.place.$model" :state="validateState($v.place)" trim lazy> </b-form-input> -->
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 聯絡人姓名 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.contactName') + '：'" :item="$v.contactName">
              {{ data.contactName }}
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.contactName.$model }}</div>
              <b-form-input v-else v-model="$v.contactName.$model" :state="validateState($v.contactName)" trim lazy> </b-form-input> -->
            </i-form-group-check>
            <!-- 聯絡人電話 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.contactTel') + '：'" :item="$v.contactTel">
              {{ data.contactTel }}
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.contactTel.$model }}</div>
              <b-form-input v-else v-model="$v.contactTel.$model" :state="validateState($v.contactTel)" trim lazy> </b-form-input> -->
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 電子郵件 -->
            <i-form-group-check :label="$t('label.email') + '：'" :item="$v.email">
              {{ data.email }}
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.email.$model }}</div>
              <b-form-input v-model="$v.email.$model" trim></b-form-input> -->
            </i-form-group-check>
            <!-- 活動網址 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.webUrl') + '：'" :item="$v.webUrl">
              {{ data.webUrl }}
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.webUrl.$model }}</div>
              <b-form-input v-else v-model="$v.webUrl.$model" :state="validateState($v.webUrl)" trim lazy> </b-form-input> -->
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="5" :label="$t('label.remark') + '：'" :item="$v.remark">
              {{ data.remark }}
              <!-- <b-form-textarea
                :plaintext="formStatus === FormStatusEnum.READONLY"
                v-model="$v.remark.$model"
                rows="4"
                maxlength="2000"
                trim
                lazy
              ></b-form-textarea> -->
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 核准說明 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="5" :label="$t('label.checkDesc') + '：'" :item="$v.checkDesc">
              {{ data.checkDesc }}
              <!-- <b-form-textarea
                :plaintext="formStatus === FormStatusEnum.READONLY"
                v-model="$v.checkDesc.$model"
                rows="4"
                maxlength="2000"
                trim
                lazy
              ></b-form-textarea> -->
            </i-form-group-check>
          </b-form-row>

          課程核備文號:{{ data.place.$model }}<br/>審核狀態:{{ data.place.$model }}

          <div class="text-center mb-3">
            <i-button class="ml-1" type="arrow-left" @click="toQueryView"></i-button>
          </div>
          <i-switch-case
            class="mb-3"
            :criteria="criteria"
            api-url="/eng-members/criteria-jpa"
            @handleCaseChanged="createDefaultValue"
          ></i-switch-case>
        </div>
      </div>
    </section>
      <!-- 教師資訊 -->
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            教師資訊
          </h5>
        </div>
        <div class="card-body" v-if="formStatusRef !== FormStatusEnum.READONLY">
        <!-- <div class="card-body"> -->
          <Eng0404EditComp :isEditComp="true" :comps="comps" 
          :key="form.engCaseId" @changeCompData="changeCompData"> </Eng0404EditComp>
        </div>
        <div class="card-body" v-else>
        <!-- <div class="card-body"> -->
          <Eng0404EditComp :isEditComp="false" :comps="comps" 
          :key="form.engCaseId" @changeCompData="changeCompData"> </Eng0404EditComp>
        </div>
      </div>
      <br/>
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            課程資訊
          </h5>
        </div>
        <div class="card-body" v-if="formStatusRef !== FormStatusEnum.READONLY">
          <Eng0404EditComp2 :isEditComp="true" :engrs="engrs" 
          :key="form.engCaseId" @changeEngrData="changeEngrData"> </Eng0404EditComp2>
        </div>
        <div class="card-body" v-else>
          <Eng0404EditComp2 :isEditComp="false" :engrs="engrs" 
          :key="form.engCaseId" @changeEngrData="changeEngrData"> </Eng0404EditComp2>
        </div>
    </div>
    <br/>
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          *通用科別
        </h5>
      </div>
      <div class="card-body" v-if="formStatusRef !== FormStatusEnum.READONLY">
        <b-form-row>
          <!-- 簽證範圍 -->
          <i-form-group-check labelStar
          class="col-12" label-cols="2"
          :label="$t('label.GenDiList') + '：'"
          :item="$v.recordRanges">
            <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY"> -->
            <div>
              {{ formOptionsFormatter(formOptions.GenDiList, 
              data.GenDiList) }}
            </div>
            <!-- v-if="formStatusRef === FormStatusEnum.EDIT" -->
            <b-form-checkbox-group
              v-model="data.recordRanges"
              :options="formOptions.GenDiList"
            ></b-form-checkbox-group>
            <!-- <b-form-checkbox-group
            v-if="formStatusRef === FormStatusEnum.CREATE"
              
              :options="formOptions.GenDiList"
            ></b-form-checkbox-group>
            <b-form-input class="col-4"
            v-if="formStatusRef !== FormStatusEnum.READONLY"
            trim></b-form-input> -->
          </i-form-group-check>
          <!-- <i-form-group-check
                :label="$t('label.GenDiList') + '：'"
                :item="$v.GenDiList"
              >
                <b-form-checkbox-group
                  v-model="$v.recordRanges.$model"
                  :options="formOptions.GenDiList"
                ></b-form-checkbox-group>
              </i-form-group-check> -->
        </b-form-row>
      </div>
    </div>
    <br/>
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          審核紀錄
        </h5>
      </div>
      <div class="card-body" v-if="formStatusRef !== FormStatusEnum.READONLY">
        <!-- <div class="card-body"> -->
          <Eng0404EditEngr :isEditComp="true" :comps3="comps3" 
          :key="form.engCaseId"> </Eng0404EditEngr>
        </div>
        <div class="card-body" v-else>
        <!-- <div class="card-body"> -->
          <Eng0404EditEngr :isEditComp="false" :comps3="comps3" 
          :key="form.engCaseId"> </Eng0404EditEngr>
      </div>
    </div>
    <b-form-row>
      <b-col offset="5">
        <!-- 返回 -->
        <i-button type="arrow-left" @click="toQueryView"></i-button>
        <!-- <i-button type="arrow-left" @click="toLast"/> -->
        <!-- 確認送出 -->
        <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" 
        type="send-check" @click="submitForm(true)" />
      </b-col>
    </b-form-row>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { onMounted, reactive, Ref, ref, toRef, toRefs, defineComponent, unref, watch } from '@vue/composition-api';
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
import Eng0404EditComp from '@/components/eng/eng0404/eng0404-edit-comp.vue';
import Eng0404EditComp2 from '@/components/eng/eng0404/eng0404-edit-comp2.vue';
import Eng0404EditEngr from '@/components/eng/eng0404/eng0404-edit-engr.vue';
import { EngCaseTotalDTO } from '@/shared/model/eng/eng-case.model';

interface propsType {
  engEngrSubjectpoint: EngMember;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0404Read',
   components: { ISwitchCase, Eng0404EditComp, Eng0404EditComp2, Eng0404EditEngr },
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
    formStatus: {
      type: String,
      required: false,
    },
  },
  // components: {
  //   ISwitchCase,
  // },
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      MODIFY = 'modify',
    }
    let comps = ref([]);
    let engrs = ref([]);
    let comps3 = ref([]);
    const engMemberProp = toRef(props, 'engMember');
    const admSysCodeService = new AdmSysCodeService();

    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);
    const isCheckRequired = ref(false); // 儲存 = false; 送出 = true
    const newFormDefault = new EngCaseTotalDTO();
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
      
      formOptions.GenDiList = [
          { value: '0', text: '請選擇' },
          { value: '1', text: '土木工程' },
          { value: '2', text: '水利工程' },
          { value: '3', text: '結構工程' },
          { value: '4', text: '大地工程' },
          { value: '5', text: '測量' },
          { value: '6', text: '環境工程' },
          { value: '7', text: '都市計畫' },
          { value: '8', text: '機械工程' },
          { value: '9', text: '冷凍空調工程' }
        ];
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    watch(
      formStatusRef,
      () => {
        initCompAndEngr();
        switch(formStatusRef.value) {
          case FormStatusEnum.READONLY:
            initCompAndEngr();
            break;
          case FormStatusEnum.EDIT:
            initCompAndEngr();
            break;
          case FormStatusEnum.CREATE:
            clearDataFromKeepAlive();
            break;
        }
      },
      { immediate: true }
    );

    function clearDataFromKeepAlive() {
      formDefault = Object.assign(formDefault, newFormDefault);
      formDefault.engCaseId = generateRandomID();
      Object.keys(form).forEach(key => {
        form[key] = formDefault[key];
      });
      comps.value = [];
      engrs.value = [];
      comps3.value = [];
    }

    function generateRandomID() {
      var S4 = function () {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
      };
      return S4() + S4() + '-' + S4() + '-' + S4() + '-' + S4() + '-' + S4() + S4() + S4();
    }

    function initCompAndEngr() {
      comps.value = [];
      engrs.value = [];
      comps3.value = [];

      let c1 = {
        chName: '邱宜璋',
        highestDegree: 'high School',
        teacherJob: '公務員',
        mainResume: '公司',
      };
      comps.value.push(c1);

      let e1 = {
        isReview: '1',
        courseTheme: '備標簡報製作',
        date: '110/11/1',
        timeStart: '08:00',
        timeEnd: '10:00',
        law: '第一款',
        approvedPoint: '20',
        teacher: '林木木',
        modifyRemark: '',
      };
      engrs.value.push(e1);

      let i1 = {
        reviewTime: '111-04-30 10:47:55',
        reviewStatus: '1-申請',
        reviewStaff: '王大明',
        reviewDesc: '申請核准為0',
      };
      comps3.value.push(i1);
    }
    
    function changeCompData(data: any, index: number, flag: string) {
      switch (flag) {
        case 'edit':
          comps.value.splice(index, 1, data);
          break;
        case 'add':
          comps.value.push(data);
          break;
        case 'delete':
          comps.value.splice(index, 1);
          break;
        default:
          break;
      }
    }

    function changeEngrData(data: any, index: number, flag: string) {
      switch (flag) {
        case 'edit':
          engrs.value.splice(index, 1, data);
          break;
        case 'add':
          engrs.value.push(data);
          break;
        case 'delete':
          engrs.value.splice(index, 1);
          break;
        default:
          break;
      }
    }

    // 目前專注跳確認視窗
    function submitForm(isCheckReq: boolean) {
      $v.value.$reset();
      this.$nextTick(() => {
        $bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
                if (isOK) {
                  $bvModal.msgBoxOk('儲存成功!');
                }
      })})
    }

    const formOptions = reactive({
      GenDiList: [],
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
      chNameUnit: '',
      organizer: '',
      courseName: '',
      applyDate: null,
      courseDate: null,
      courseDateStart: null,
      courseDateEnd: null,
      uploadDeadline: null,
      place: '',
      contactName: '',
      contactTel: '',
      email: '',
      webUrl: '',
      remark: '',
      checkDesc: '',
      engCaseId: '',
      GenDiList: '',
      recordRanges: [],
    };
    Object.assign(formDefault, engMemberProp.value);
    formDefault.applyDate = parseToDate(formDefault.applyDate, '-');
    formDefault.courseDate = parseToDate(formDefault.courseDate, '-');
    // formDefault.birthDate = formatToString(formDefault.birthDate, '/', '-');
    // console.log(formDefault);

    let form = reactive(Object.assign({}, formDefault));

    const rules = {
      chNameUnit: {},
      organizer: {},
      courseName: {},
      applyDate: {},
      courseDate: {},
      courseDateStart: {},
      courseDateEnd: {},
      uploadDeadline: {},
      place: {},
      contactName: {},
      contactTel: {},
      email: {},
      webUrl: {},
      remark: {},
      checkDesc: {},
      recordRanges: {},
      GenDiList: {},
    };
    const data={
      chNameUnit: '鼎漢國際有限公司',
      organizer: '鼎漢國際有限公司',
      courseName: 'Mas2.0',
      applyDate: '110/04/27',
      courseDate: '',
      courseDateStart: '110/04/27',
      courseDateEnd: '111/04/27',
      uploadDeadline: '110/04/27',
      place: '台北市',
      contactName: '施宥慈',
      contactTel: '0227488822',
      email: 'omber618@thi.com.tw',
      webUrl: '',
      remark: '本課程',
      checkDesc: '本課程好',
      recordRanges: '1',
      GenDiList: [''],
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
      formDefault.applyDate = parseToDate(formDefault.applyDate, '-');
      formDefault.courseDate = parseToDate(formDefault.courseDate, '-');
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
      changeCompData,
      changeEngrData,
      comps,
      engrs,
      comps3,
      submitForm,
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
      data
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>

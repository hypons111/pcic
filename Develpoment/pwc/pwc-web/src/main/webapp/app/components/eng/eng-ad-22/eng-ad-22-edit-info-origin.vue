<template>
  <div class="container">
    <!-- 基本資料 -->
    <div class="card">
      <div class="card-header pl-3">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          詳細資料
        </h5>
      </div>
      <div class="card-body col-11">
        <!-- 因為覺得右邊有點太滿所以稍微縮減一些寬度col-11-->
        <!-- 編輯模式 到時候包v-if-->
        <div>
          <!-- 基本資料 b-form group-->

          <div v-if="formStatusRef === FormStatusEnum.MODIFY || formStatusRef === FormStatusEnum.CREATE">
            <!-- 單位 -->
            <b-form-row class="align-items-top input-check-row">
              <b-form-group
                class="col-sm-3"
                label-class="label"
                label-cols-md="8"
                content-cols-md="4"
                :label="$t('label.eng.companyName')"
                label-align-md="right"
              >
                <b-form-select :options="belongUnit" :disabled="formStatusRef === FormStatusEnum.INFO">
                  <template #first>
                    <option value="undefined">-- 請選擇 --</option>
                  </template>
                </b-form-select>
              </b-form-group>
              <b-form-group class="col-sm-3" label-sr-only label-align-md="right" content-cols-md="12">
                <b-form-input
                  v-model.trim="$v.belongOf.$model"
                  :state="validateState($v.belongOf)"
                  placeholder="請輸入所屬單位"
                ></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.belongOf.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
              <b-form-group
                class="col-sm-6"
                label-cols-md="4"
                label-class="label"
                content-cols-md="8"
                :label="$t('label.eng.belongUnit')"
                label-align-md="right"
              >
                <b-form-input
                  v-model.trim="$v.belongOf.$model"
                  :state="validateState($v.belongOf)"
                  placeholder="請輸入主辦單位"
                ></b-form-input>
              </b-form-group>
            </b-form-row>
            <!-- 課程名稱 -->
            <b-form-row class="align-items-top input-check-row">
              <b-form-group
                class="col-sm-6"
                label-cols-md="4"
                label-class="label"
                content-cols-md="8"
                :label="$t('label.eng.courseName')"
                label-align-md="right"
              >
                <b-form-input v-model.trim="$v.name.$model" :state="validateState($v.name)" placeholder="請輸入課程名稱"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
              <b-form-group
                class="col-sm-6"
                label-cols-md="4"
                label-class="label"
                content-cols-md="4"
                :label="$t('label.eng.createDate')"
                label-align-md="right"
              >
                <i-date-picker v-model="$v.createDate.$model" :state="$v.createDate"> </i-date-picker>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-top little-font pt-0">
              <b-col cols="2"></b-col>
              <b-col cols="4">
                <span class="red-text little-font">如有採用視訊或網路教學，請於其後加註「本課程有採用視訊或網路教學」</span>
              </b-col>
            </b-form-row>
            <!-- 開始與結束 -->
            <b-form-row class="align-items-center">
              <b-form-group
                class="col-6"
                label-class="label"
                label-cols="4"
                content-cols="8"
                :label="$t('label.eng.date_Start')"
                label-align="right"
              >
                <b-input-group>
                  <i-date-picker v-model="$v.date_Start.$model" :state="$v.date_Start"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.date_End.$model" :state="$v.date_End"> </i-date-picker>
                </b-input-group>
                <dual-form-invalid-feedback
                  :dual-invalid-feedback="[$v.date_Start.$errors, $v.date_End.$errors]"
                ></dual-form-invalid-feedback>
              </b-form-group>
            </b-form-row>
            <!-- <b-form-row class="align-items-center">
              <b-form-group class="col-6" label-cols="4" content-cols="8" :label="$t('label.eng.date_Start')" label-align="right">
                <b-input-group>
                  <b-form-datepicker></b-form-datepicker>
                  <b-input-group-text>至</b-input-group-text>
                  <b-form-datepicker></b-form-datepicker>
                </b-input-group>
              </b-form-group>
            </b-form-row> -->
            <!-- 截止時間 -->
            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-4"
                label-cols-md="6"
                label-class="label"
                content-cols-md="6"
                :state="validateState($v.date_End)"
                :label="$t('label.eng.uploadDeadline')"
                label-align-md="right"
              >
                <i-date-picker v-model="$v.UploadDeadline.$model" :state="$v.UploadDeadline"> </i-date-picker>
              </b-form-group>
            </b-form-row>
            <!-- 地點 -->
            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-12"
                label-cols-md="2"
                label-class="label"
                content-cols-md="4"
                :label="$t('label.eng.place')"
                label-align-md="right"
              >
                <b-form-input v-model="$v.place.$model" :state="validateState($v.place)" placeholder="請輸入上課地點"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.place.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
            </b-form-row>
            <!-- 姓名電話 -->
            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-6"
                label-cols-md="4"
                label-class="label"
                content-cols-md="8"
                :label="$t('label.eng.contact_Name')"
                label-align-md="right"
              >
                <b-form-input
                  v-model.trim="$v.contact_Name.$model"
                  :state="validateState($v.contact_Name)"
                  placeholder="請輸入聯絡人姓名"
                ></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.contact_Name.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
              <b-form-group
                class="col-sm-6"
                label-cols-md="4"
                label-class="label"
                content-cols-md="8"
                :label="$t('label.eng.contact_Tel')"
                label-align-md="right"
              >
                <!-- <i-phone-number v-model="$v.contact_Tel.$model" ></i-phone-number> -->
                <b-form-input
                  v-model.trim="$v.contact_Tel.$model"
                  :state="validateState($v.contact_Tel)"
                  placeholder="請輸入聯絡人電話"
                ></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.contact_Tel.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
            </b-form-row>
            <!-- EMAIL -->
            <b-form-row class="align-items-center input-check-row">
              <b-form-group
                class="col-sm-6"
                label-class="label"
                label-cols-md="4"
                content-cols-md="8"
                :label="$t('label.eng.contact_Mail')"
                label-align-md="right"
              >
                <b-form-input v-model="$v.contact_Mail.$model" :state="validateState($v.contact_Mail)" placeholder="請輸入電子郵件">
                </b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.contact_Mail.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
              <span class="is-invalid"></span>
              <span class="red-text little-font" v-for="(error, index) of $v.contact_Mail.$errors" :key="index">
                {{ error.$message }}
              </span>
            </b-form-row>
            <!-- 網址 -->
            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-12"
                label-cols-md="2"
                label-class="label"
                content-cols-md="4"
                :label="$t('label.eng.webUrl')"
                label-align-md="right"
              >
                <b-form-input v-model="$v.webUrl.$model" :state="validateState($v.webUrl)" placeholder="請輸入活動網址"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.webUrl.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
            </b-form-row>
            <!-- 備註 -->
            <b-form-row class="align-items-center form-group-textarea">
              <b-form-group
                class="col-sm-12"
                label-cols-md="2"
                label-class="label"
                content-cols-md="4"
                :label="$t('label.eng.remark')"
                label-align-md="right"
              >
                <b-form-textarea
                  v-model="$v.remark.$model"
                  :state="validateState($v.remark)"
                  placeholder="備註..."
                  rows="3"
                  max-rows="3"
                ></b-form-textarea>
                <b-form-invalid-feedback v-for="(error, index) of $v.remark.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-top error-message little-font">
              <!-- <b-form-group class="col-sm-2" label-sr-only label-align-md="right" content-cols-md="12"> </b-form-group> -->
              <!-- <b-form-group class="col-sm-4" label-sr-only label-align-md="right" content-cols-md="12"> -->
              <b-col cols="2"></b-col>
              <b-col cols="4">
                <span class="red-text"
                  >如有採用視訊或網路教學，請於備註欄說明本課程之學員身分確認機制及簽到/退管理方式，相關資料並應建檔留存。</span
                >
              </b-col>
              <!-- </b-form-group> -->
            </b-form-row>
          </div>
          <!-- END OF 基本資料 b-form group-->
          <!-- INFO component-->
          <eng22courseHeadInfo v-if="formStatusRef === FormStatusEnum.INFO" :courseHead="form" :key="form.ID"> </eng22courseHeadInfo>

          <!-- 核准的欄位 -->
          <div>
            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-6"
                label-cols-md="4"
                content-cols-md="8"
                :label="$t('label.eng.docNo')"
                label-class="label"
                label-align-md="right"
              >
                <b-form-input placeholder="課程核備文號"></b-form-input>
              </b-form-group>
              <b-form-group
                class="col-sm-6 align-items-center"
                label-cols-md="4"
                content-cols-md="8"
                label-class="label"
                :label="$t('label.eng.status')"
                label-align-md="right"
              >
                <b-form-input plaintext disabled value="未審"></b-form-input>
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="6"
                :label="$t('label.eng.checkStaff1')"
                label-class="label"
                label-align-md="right"
              >
              </b-form-group>
            </b-form-row>

            <b-form-row class="align-items-center">
              <b-form-group
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="6"
                :label="$t('label.eng.checkStaff2')"
                label-class="label"
                label-align-md="right"
              >
              </b-form-group>
            </b-form-row>
          </div>
          <!-- 功能按鈕 -->
          <div class="d-flex justify-content-center">
            <b-button-toolbar class="float-right mt-5">
              <i-button type="pencil-square" v-show="formStatusRef === FormStatusEnum.INFO" @click="changeStatusToEdit"></i-button>
              <i-button
                type="tag"
                v-show="formStatusRef === FormStatusEnum.MODIFY || formStatusRef === FormStatusEnum.CREATE"
                @click="changeStatusToInfo"
              ></i-button>
              <i-button class="ml-1" type="arrow-left" @click="toQueryView"></i-button>
            </b-button-toolbar>
          </div>
        </div>
      </div>
    </div>
    <!-- 教師資訊 -->
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          教師資訊
        </h5>
      </div>
      <div class="card-body">
        <eng22Teacher :isEditTeacher="true" :teachers="teachers" :key="form.ID" @changeTeacherData="changeTeacherData"> </eng22Teacher>
      </div>
    </div>
    <!-- 詳細課程資訊 -->
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          詳細課程
        </h5>
      </div>
      <div class="card-body">
        <eng22courseD
          :teachers="teachers"
          :key="form.ID"
          :courseDetailArr="courseDetailArr"
          @changeCourseDetailData="changeCourseDetailData"
        >
        </eng22courseD>
      </div>
    </div>
    <!-- 最後的工作列 -->
    <div class="d-flex justify-content-center">
      <b-button-toolbar class="mt-5">
        <b-button class="ml-1" variant="info">
          <b-icon icon="hammer"></b-icon>
          {{ '作廢' }}
        </b-button>
        <i-button class="ml-1" type="send-check" @click="sendAllData"></i-button>
        <i-button class="ml-1" type="arrow-left" @click="toQueryView"></i-button>
      </b-button-toolbar>
    </div>
    <b-modal id="pretend-send-data-modal" hide-footer>
      <div class="d-block text-center">
        send data!!
        <h3>form:</h3>
        {{ form }}
        <br />
        <h3>courseDetailArr:</h3>
        {{ courseDetailArr }}
      </div>
      <b-button class="mt-3" block @click="$bvModal.hide('pretend-send-data-modal')">Close Me</b-button>
    </b-modal>
  </div>
</template>

<script lang="ts">
import { Ref, ref, reactive, computed, onActivated, watch } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { email, required } from '@/shared/validators';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import eng22Teacher from '@/components/eng/eng-ad-22/eng22-teacher.vue';
import eng22courseD from '@/components/eng/eng-ad-22/eng22-courseD.vue';
import eng22courseHeadInfo from '@/components/eng/eng-ad-22/eng22-courseHeaderInfo.vue';
import { parseRocDate } from '@/shared/date/minguo-calendar-utils';
import iPhoneNumber from '@/shared/form/i-phone-num.vue';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'engAd22EditInfo',
  components: { eng22Teacher, eng22courseD, eng22courseHeadInfo, 'i-phone-number': iPhoneNumber },
  props: {
    course_H: {
      required: false,
    },
    formStatus: {
      required: false,
    },
  },
  setup(props, context) {
    let isReload = ref(false);
    const $bvModal = useBvModal(); //modal物件
    enum FormStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }
    // const formStatusRef: Ref<FormStatusEnum> = ref(FormStatusEnum.MODIFY);

    const toQueryView = () => {
      // context.emit('changeComponent', 'engAd22Query', isReload.value);
      navigateByNameAndParams('engAd22-query', { isReload: false });
    };

    const formStatusRefOrigin = computed(() => props.formStatus);
    //傳入的course
    const course_H = computed(() => props.course_H);

    //如果有傳參數進來就把default值設定成傳進來的
    onActivated(() => {
      reset();
      if (course_H.value) {
        Object.assign(courseH_Default, course_H.value);
        Object.assign(form, courseH_Default);
        // console.log('date',new Date().toISOString().slice(0,10).replace(/-/g,""));
        // form.date_Start = parseRocDate(new Date().toISOString().slice(0,10).replace(/-/g,""));
      }
      switch (formStatusRefOrigin.value) {
        case FormStatusEnum.INFO:
          formStatusRef.value = FormStatusEnum.INFO;
          initOriginTeacherAndCourseData('123');
          break;
        case FormStatusEnum.MODIFY:
          formStatusRef.value = FormStatusEnum.MODIFY;
          initOriginTeacherAndCourseData('123');
          break;
        case FormStatusEnum.CREATE:
          formStatusRef.value = FormStatusEnum.CREATE;
          clearDataFromKeepAlive();
          break;
      }
    });
    //放教師的data用的
    let teachers = ref([]);
    //放couseDetail的data用的
    let courseDetailArr = ref([]);

    let formStatusRef = ref();

    function generateRandomID() {
      var S4 = function () {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
      };
      return S4() + S4() + '-' + S4() + '-' + S4() + '-' + S4() + '-' + S4() + S4() + S4();
    }

    const newcourseH_Default = {
      ID: '',
      companyName: '',
      belongOf: '',
      name: '',
      createDate: new Date(),
      date_Start: undefined,
      date_End: undefined,
      UploadDeadline: '',
      contact_Name: '',
      contact_Tel: '',
      contact_Mail: '',
      webUrl: '',
      remark: '',
      docNo: '',
      place: '',
      status: undefined,
      CheckStaff_1: '',
      CheckStatus_1: '',
      CheckDate_1: undefined,
      CheckStaff_2: '',
      CheckStatus_2: '',
      CheckDate_2: undefined,
    };

    let courseH_Default = {
      ID: '',
      companyName: '',
      belongOf: '',
      name: '',
      createDate: new Date(),
      date_Start: undefined,
      date_End: undefined,
      UploadDeadline: '',
      contact_Name: '',
      contact_Tel: '',
      contact_Mail: '',
      webUrl: '',
      remark: '',
      docNo: '',
      place: '',
      status: undefined,
      CheckStaff_1: '',
      CheckStatus_1: '',
      CheckDate_1: undefined,
      CheckStaff_2: '',
      CheckStatus_2: '',
      CheckDate_2: undefined,
    };
    const courseRules = {
      ID: {},
      companyName: {},
      belongOf: { required: required },
      name: { required: required },
      createDate: {},
      date_Start: { required: required },
      date_End: {},
      UploadDeadline: { required: required },
      contact_Name: {},
      contact_Tel: {},
      contact_Mail: { email: email },
      webUrl: {},
      remark: { required: required },
      docNo: {},
      place: {},
      status: {},
      CheckStaff_1: {},
      CheckStatus_1: {},
      CheckDate_1: {},
      CheckStaff_2: {},
      CheckStatus_2: {},
      CheckDate_2: {},
    };
    let form = reactive(Object.assign({}, courseH_Default));
    const { $v, checkValidity, reset } = useValidation(courseRules, form, courseH_Default);
    const belongUnit = ref(['顧問公司', '公會', '政府組織', '全聯會', '顧問公會']);

    function initOriginTeacherAndCourseData(courseHeaderID) {
      // console.log('到時候要用ID:', courseHeaderID, '去撈data');
      //初始化放data的地方
      teachers.value = [];
      courseDetailArr.value = [];
      //先寫死要撈的teacher data
      let t1 = {
        name: '顏玉明',
        highEdu: '英國曼徹斯特大學營建工程及法律博士',
        job: '政治大學法律科際整合研究所教授',
        experience: '寰瀛法律事務所律師  國立中興大學科技法律研究所助理教授',
      };
      let t2 = {
        name: '黃立',
        highEdu: '奧國維也納大學法學博士',
        job: '國立政治大學法律系教授兼法學碩士在職專班執行長',
        experience: '政大法律系系主任、政大法學院院長',
      };
      teachers.value.push(t1);
      teachers.value.push(t2);
      //再寫死要撈的course data
      //放courseDetail
      let cd1 = {
        ID: '123',
        name: '工程法律',
        itemNo: 1,
        date: new Date('2021/11/01'),
        time_Start: '08:00',
        time_End: '11:00',
        subjectID: '01,02,04',
        point: '30',
        teacher: t1,
        law: 1,
        remark: '',
        modifyId: '',
        modifyDate: '',
      };
      let cd2 = {
        ID: '123',
        name: '台灣工程承攬契約中情勢變更之適用問題',
        itemNo: 2,
        date: new Date('2021/11/01'),
        time_Start: '13:00',
        time_End: '15:00',
        subjectID: '01,04',
        point: '30',
        law: 1,
        teacher: t2,
        remark: '',
        modifyId: '',
        modifyDate: '',
      };
      courseDetailArr.value.push(cd1);
      courseDetailArr.value.push(cd2);
      // console.log('teachers:', teachers.value);
      // console.log('courseDetailArr:', courseDetailArr.value);
    }
    function clearDataFromKeepAlive() {
      courseH_Default = Object.assign(courseH_Default, newcourseH_Default);
      courseH_Default.ID = generateRandomID();
      Object.keys(form).forEach(key => {
        form[key] = courseH_Default[key];
      });
      teachers.value = [];
      courseDetailArr.value = [];
    }

    function sendAllData() {
      $bvModal.show('pretend-send-data-modal');
    }
    //在改變子組件的時候透過自定義方法更改parent的資料
    function changeTeacherData(data: any, index: number, flag: string) {
      switch (flag) {
        case 'edit':
          teachers.value.splice(index, 1, data);
          break;
        case 'add':
          teachers.value.push(data);
          break;
        case 'delete':
          teachers.value.splice(index, 1);
          break;
        default:
          break;
      }
    }
    function changeCourseDetailData(data: any, index: number, flag: string) {
      switch (flag) {
        case 'edit':
          courseDetailArr.value.splice(index, 1, data);
          break;
        case 'add':
          courseDetailArr.value.push(data);
          break;
        case 'delete':
          courseDetailArr.value.splice(index, 1);
          courseDetailArr.value.forEach((element, index) => {
            element.itemNo = index + 1;
          });
          break;
      }
    }

    function changeStatusToEdit() {
      window.scroll({ top: 0, left: 0, behavior: 'smooth' });
      formStatusRef.value = FormStatusEnum.MODIFY;
    }
    function changeStatusToInfo() {
      window.scroll({ top: 0, left: 0, behavior: 'smooth' });
      formStatusRef.value = FormStatusEnum.INFO;
    }
    return {
      FormStatusEnum,
      formStatusRef,
      form,
      teachers,
      courseDetailArr,
      belongUnit,
      $v,
      changeCourseDetailData,
      validateState,
      changeTeacherData,
      toQueryView,
      changeStatusToEdit,
      changeStatusToInfo,
      formatDate,
      sendAllData,
    };
  },
};
</script>

<style scoped>
.red-text {
  color: red;
}
.little-font {
  font-size: 12px;
}
.error-message {
  height: auto;
}
.input-check-row {
  height: 62px;
}
.form-group-textarea > .form-group {
  height: auto;
}
</style>

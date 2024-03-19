<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          詳細資料
        </h5>
      </div>
      <div class="card-body col-md-10">
        <!-- col-md-10讓右邊不要太滿 -->
        <eng22courseHeadInfo :courseHead="courseHeaderCheck" :key="courseHeaderCheck.ID"> </eng22courseHeadInfo>
        <!-- 批退原因 -->
        <b-form-row class="align-items-center">
          <i-form-group-check :item="$v.checkDesc" :label="$t('label.checkDesc')+'：'" :labelStar="true">
            <b-form-textarea
              v-model="$v.checkDesc.$model"
              :state="validateState($v.checkDesc)"
              placeholder="批退原因..."
              rows="3"
              max-rows="3"
            ></b-form-textarea>
          </i-form-group-check>
        </b-form-row>

        <div>
          <!-- 核准的欄位 -->
          <div>
            <b-form-row class="align-items-center">
              <i-form-group-check :label="$t('label.docNo')+'：'">
                <!-- <b-form-input placeholder="課程核備文號"></b-form-input> -->
              </i-form-group-check>
              <i-form-group-check class="col-sm-6 align-items-center" :label="$t('label.status')+'：'">
                <b-form-input plaintext disabled value="未審"></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row class="align-items-center">
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="6" :label="$t('label.checkStaff1')+'：'">
              </i-form-group-check>
            </b-form-row>

            <b-form-row class="align-items-center">
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="6" :label="$t('label.checkStaff2')+'：'">
              </i-form-group-check>
            </b-form-row>
          </div>
          <!-- 功能按鈕 -->
          <div class="d-flex justify-content-center">
            <b-button-toolbar class="float-right mt-5">
              <i-button type="arrow-left" @click="toQueryView"></i-button>
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
        <eng22Teacher :teachers="teachers" :isEditTeacher="false" :key="courseHeaderCheck.ID"></eng22Teacher>
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
          :key="courseHeaderCheck.ID"
          :courseDetailArr="courseDetailArr"
          :isCheckingProp="true"
          @changeCourseDetailData="changeCourseDetailData"
        >
        </eng22courseD>
      </div>
    </div>
    <!-- 按鈕列表 -->
    <div class="d-flex justify-content-center">
      <b-button-toolbar class="mt-5">
        <i-button class="ml-1" type="clipboard-plus"></i-button>
        <i-button class="ml-1" type="clipboard-minus"></i-button>
        <i-button class="ml-1" type="clipboard-x"></i-button>
        <i-button class="ml-1" type="send-check"></i-button>
        <i-button class="ml-1" type="arrow-left"></i-button>
      </b-button-toolbar>
    </div>
    <div class="d-flex justify-content-end">
      <b-button-toolbar>
        <i-button type="arrow-bar-left"></i-button>
        <i-button type="arrow-bar-right"></i-button>
      </b-button-toolbar>
    </div>
    <!-- 送出data的彈跳視窗 -->
    <!-- <b-modal id="pretend-send-data-modal" hide-footer>
      <div class="d-block text-center">
        send data!!
        <h3>form:</h3>
        {{ form }}
        <br />
        <h3>courseDetailArr:</h3>
        {{ courseDetailArr }}
      </div>
      <b-button class="mt-3" block @click="$bvModal.hide('pretend-send-data-modal')">Close Me</b-button>
    </b-modal> -->
  </div>
</template>

<script lang="ts">
import eng22courseHeadInfo from '@/components/eng/eng-ad-22/eng22-courseHeaderInfo.vue';
import eng22Teacher from '@/components/eng/eng-ad-22/eng22-teacher.vue';
import eng22courseD from '@/components/eng/eng-ad-22/eng22-courseD.vue';
import { computed, reactive, ref, watch } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import { required } from '@/shared/validators';
import { handleBack } from '@/router/router';
export default {
  name: 'engAd23EditReview',
  components: {
    eng22courseHeadInfo,
    eng22Teacher,
    eng22courseD,
  },
  props: {
    course_H: {
      required: true,
    },
    formStatus: {
      required: false,
    },
  },
  setup(props, context) {
    let isReload = ref(false);
    //放教師的data用的
    let teachers = ref([]);
    //放couseDetail的data用的
    let courseDetailArr = ref([]);

    // onActivated(() => {
    //   initOriginTeacherAndCourseData('123'); //假裝去抓資料
    //   courseHeaderCheck.value = Object.assign(courseHeaderCheck.value, courseHeader.value);
    //   courseHeaderCheck.value['checkDesc'] = ''; //批退原因
    // });

    const courseHeader = computed(() => props.course_H);

    let formDefault = {
      checkDesc: '',
    };

    const courseHeaderCheck = reactive(Object.assign({}, formDefault));
    const courseHeaderCheckRules = {
      checkDesc: { required: required },
    };

    const { $v, checkValidity, reset } = useValidation(courseHeaderCheckRules, courseHeaderCheck, formDefault);

    const toQueryView = () => {
      // context.emit('changeComponent', 'engAd23Query', isReload.value);
      const param = { isReload: false };
      handleBack(param);
    };

    /**
     * 用courseHeaderID 去撈courseDetail資料,
     */
    function initOriginTeacherAndCourseData(courseHeaderID) {
      // console.log('到時候要用ID:', courseHeaderID, '去撈data');
      //初始化放data的地方
      // teachers.value.splice(0,teachers.value.length);
      teachers.value = [];
      courseDetailArr.value = [];
      //先寫死要撈的teacher data
      let t1 = {
        name: '顏玉明',
        highEdu: '英國曼徹斯特大學營建工程及法律博士',
        job: '政治大學法律科際整合研究所教授',
        experience: '寰瀛法律事務所律師  國立中興大學科技法律研究所助理教授',
      };
      teachers.value.push(t1);
      //再寫死要撈的course data
      //放courseDetail
      let cd1 = {
        ID: '123',
        name: '工程法律',
        itemNo: 1,
        date: new Date('2021/11/01'),
        time_Start: '08:00',
        time_End: '11:00',
        subjectID: '01,02,03,04',
        point: '30',
        teacher: t1,
        law: 1,
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
        subjectID: '01',
        point: '30',
        law: 1,
        teacher: t1,
        modifyId: '',
        modifyDate: '',
      };
      courseDetailArr.value.push(cd1);
      courseDetailArr.value.push(cd2);
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
    //修改activated新寫法
    watch(
      courseHeader,
      newValue => {
        console.log(courseHeader.value);
        if (courseHeader.value) {
          initOriginTeacherAndCourseData('123'); //假裝去抓資料
          Object.assign(formDefault, courseHeader.value);
          reset();
          // courseHeaderCheck.value['checkDesc'] = ''; //批退原因
        }
      },
      { immediate: true }
    );

    return {
      toQueryView,
      $v,
      courseHeaderCheck,
      teachers,
      courseDetailArr,
      validateState,
      changeCourseDetailData,
    };
  },
};
</script>

<style scoped></style>

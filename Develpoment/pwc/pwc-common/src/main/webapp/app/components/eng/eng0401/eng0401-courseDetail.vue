<template>
  <div>
    <!-- <div v-if="formStatus === FormStatusEnum.REVIEW">
      <b-form-checkbox v-model="allCourseDtlReview" @change="toggleAllcourseDtlReview($event)">全部核准 </b-form-checkbox>
    </div> -->
    <b-table :items="table.engCourseDtls" :fields="table.fields" bordered small class="mb-0">
      <!-- 核准 -->
      <template #head(review)>
        {{ $t('label.approved') }}
        <!-- <b-form-checkbox class=""></b-form-checkbox> -->
        <input type="checkbox" class="input-checkbox" v-model="allCourseDtlReview" @change="toggleAllcourseDtlReview" />
      </template>
      <template #cell(review)="{ item }">
        <!-- <b-form-checkbox v-model="item.isReview" @change="toggleCourseDtlReview(item, 'edit')"> </b-form-checkbox> -->
        <input type="checkbox" class="input-checkbox" v-model="item.isReview" @change="toggleCourseDtlReview" />
      </template>

      <!-- 項次 -->
      <template #cell(index)="{ index }">
        <div>{{ index + 1 }}</div>
      </template>

      <!-- 課程主題 -->
      <template #cell(courseName)="{ item }">
        <i-form-single-check
          v-show="item.isEdit"
          :value="item.courseName"
          :rules="rules.courseName"
          @returnValidity="item.checkValidityArr.push($event)"
        >
          <b-form-input v-model="item.courseName" trim lazy></b-form-input>
        </i-form-single-check>
        <div v-show="!item.isEdit">{{ item.courseName }}</div>
      </template>

      <!-- 日期 -->
      <template #cell(courseDate)="{ item }">
        <i-form-single-check
          v-show="item.isEdit"
          :value="item.courseDate"
          :rules="rules.courseDate"
          @returnValidity="item.checkValidityArr.push($event)"
        >
          <i-date-picker v-model="item.courseDate"></i-date-picker>
        </i-form-single-check>
        <div v-show="!item.isEdit">{{ item.courseDate | dateToString }}</div>
      </template>

      <!-- 時間(起) -->
      <template #cell(timeStart)="{ item }">
        <div v-if="item.isEdit">
          <select v-model="item.timeStartArr[0]" @change="calculatePoint(item)">
            <option v-for="hh in formOptions.clockHH" :value="hh.value" :key="hh.value">
              {{ hh.text }}
            </option>
          </select>
          <select v-model="item.timeStartArr[1]" @change="calculatePoint(item)">
            <option v-for="mm in formOptions.clockMM" :value="mm.value" :key="mm.value">
              {{ mm.text }}
            </option>
          </select>
        </div>
        <div v-else>{{ item.timeStartArr[0] }}:{{ item.timeStartArr[1] }}</div>
      </template>

      <!-- 時間(迄) -->
      <template #cell(timeEnd)="{ item }">
        <div v-if="item.isEdit">
          <select v-model="item.timeEndArr[0]" @change="calculatePoint(item)">
            <option v-for="hh in formOptions.clockHH" :value="hh.value" :key="hh.value">
              {{ hh.text }}
            </option>
          </select>
          <select v-model="item.timeEndArr[1]" @change="calculatePoint(item)">
            <option v-for="mm in formOptions.clockMM" :value="mm.value" :key="mm.value">
              {{ mm.text }}
            </option>
          </select>
        </div>
        <div v-else>{{ item.timeEndArr[0] }}:{{ item.timeEndArr[1] }}</div>
      </template>

      <!-- 適用款次 -->
      <template #cell(law)="{ item }">
        <i-form-single-check v-show="item.isEdit" :value="item.law" :rules="rules.law" @returnValidity="item.checkValidityArr.push($event)">
          <b-form-select v-model="item.law" :options="formOptions.courseLaw" @change="calculatePoint(item)">
            <template #first>
              <option :value="undefined">-- 請選擇 --</option>
            </template>
          </b-form-select>
        </i-form-single-check>
        <div v-show="!item.isEdit">{{ formOptionsFormatter(formOptions.courseLaw, item.law) }}</div>
      </template>

      <!-- 講師 -->
      <template #cell(teachers)="{ item }">
        <i-form-single-check :value="item.teacherArr" :rules="rules.teachers" @returnValidity="item.checkValidityArr.push($event)">
          <i-tree-select
            :disabled="!item.isEdit"
            v-model="item.teacherArr"
            :options="table.engTeachers"
            :searchable="true"
            :clearable="true"
            multiple
          >
          </i-tree-select>
        </i-form-single-check>
      </template>

      <!-- 修改註記 -->
      <template #cell(remark)="{ item }">
        <i-form-single-check
          v-show="formStatus === FormStatusEnum.REVIEW && item.isEdit"
          :value="item.remark"
          :rules="rules.remark"
          @returnValidity="item.checkValidityArr.push($event)"
        >
          <b-form-input v-model="item.remark" trim lazy></b-form-input>
        </i-form-single-check>
        <div v-show="formStatus !== FormStatusEnum.REVIEW || !item.isEdit" :value="item.remark">{{ item.remark }}</div>
      </template>

      <!-- 動作 -->
      <template #cell(action)="{ item, index }">
        <!-- 編輯 -->
        <div v-if="formStatus !== FormStatusEnum.READONLY && !item.isEdit" :class="{ invisible: isEditingCourseDetail }">
          <i-button size="sm" class="mb-1" type="pencil-square" @click="editCourseDetail(item)"></i-button>
        </div>
        <!-- 刪除 -->
        <div v-if="formStatus !== FormStatusEnum.READONLY && !item.isEdit" :class="{ invisible: isEditingCourseDetail }">
          <i-button size="sm" class="mb-1" type="trash" @click="deleteCourseDetail(item)"></i-button>
        </div>
        <!-- 暫存 -->
        <div v-if="item.isEdit">
          <i-button size="sm" class="mb-1" type="tag" @click="saveCourseDetail(item, index)"></i-button>
        </div>
        <!-- 取消 -->
        <div v-if="item.isEdit">
          <i-button size="sm" type="arrow-counterclockwise" @click="cancelAction(item)"></i-button>
        </div>
      </template>

      <!-- 適用科別 -->
      <template #row-details="{ item }">
        <b-card no-body>
          <b-card-header class="py-1">
            <b>適用科別</b>
          </b-card-header>
          <b-card-body class="py-2" v-if="formStatus !== FormStatusEnum.REVIEW">
            <i-form-single-check
              type="other"
              :value="item.subjectArr"
              :rules="rules.teachers"
              @returnValidity="item.checkValidityArr.push($event)"
            >
              <b-form-checkbox :disabled="!item.isEdit" v-model="item.subjectArr" value="0" @change="toggleAllSubject(item)">
                全部
              </b-form-checkbox>
              <b-form-row>
                <div v-for="option in formOptions.subject" :key="option.value" class="col-2 d-inline-block">
                  <b-form-checkbox :disabled="!item.isEdit" v-model="item.subjectArr" :value="option.value" @change="toggleSubject(item)">
                    {{ option.text }}
                  </b-form-checkbox>
                </div>
              </b-form-row>
            </i-form-single-check>
          </b-card-body>
          <b-card-body class="py-3" v-else>
            {{ formOptionsFormatter(formOptions.subject, item.subjectList) }}
          </b-card-body>
        </b-card>
      </template>

      <!-- table底下(新增按鈕) -->
      <template #table-caption>
        <div v-if="formStatus === FormStatusEnum.EDIT && !isEditingCourseDetail">
          <!-- 新增 -->
          <i-button
            v-if="!table.engCourseDtls || table.engCourseDtls.length === 0"
            type="file-earmark-plus"
            @click="addCourseDetail"
          ></i-button>
          <!-- 繼續新增 -->
          <i-button v-else type="plus-circle" @click="addCourseDetail"></i-button>
        </div>
      </template>
    </b-table>
  </div>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { useBvModal } from '@/shared/modal';
import { ref, reactive, watch, onMounted, computed, defineComponent, toRefs, inject, Ref, unref } from '@vue/composition-api';
import { IEngCourseDtl, EngCourseDtl } from '@/shared/model/eng/eng-course-dtl.model';
import { IEngTeacher } from '@/shared/model/eng/eng-teacher.model';
import { FormStatusEnum } from '@/shared/enum';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import ITreeSelect from '@/shared/i-tree-select/i-tree-select.vue';
import { IEngCourseDtlTeacher } from '@/shared/model/eng/eng-course-dtl-teacher.model';
import { maxLength, required, requiredIf } from '@/shared/validators';
import _ from 'lodash';

interface propsType {
  engCourseDtls: IEngCourseDtl[];
  engCourseDtlTeachers: IEngCourseDtlTeacher[];
  formStatus: FormStatusEnum;
  isCheckingProp: Boolean;
}

export default defineComponent<propsType>({
  name: 'Eng0401CourseDetail',
  components: { ITreeSelect },
  props: {
    engCourseDtls: {
      required: false,
    },
    engCourseDtlTeachers: {
      required: false,
    },
    formStatus: {
      required: false,
    },
    isCheckingProp: {
      type: Boolean,
      required: false,
      default: false,
    },
  },
  setup(props) {
    const $bvModal = useBvModal(); //modal物件
    const isEditingCourseDetail = ref(false); //是否添加新detail的判斷
    const courseDtlReview = ref([]);
    const allCourseDtlReview = ref(false); //審核全選用

    let { engCourseDtls, engCourseDtlTeachers, formStatus } = toRefs(props);
    const admSysCodeService = new AdmSysCodeService();
    let engCourseDetailsTemp = undefined;

    const courseNameComponent = ref(null);
    const courseDateComponent = ref(null);
    const lawComponent = ref(null);
    const teachersComponent = ref(null);

    const engTeachers = inject<Ref<IEngTeacher[]>>('engTeachers');

    watch(
      engTeachers,
      newVal => {
        if (table.engTeachers && newVal.length < table.engTeachers.length) {
          const newEngTeacherId = newVal.map(e => e.engTeacherId);
          const oldEngTeacherId = table.engTeachers.map(e => e.id);
          oldEngTeacherId
            .filter(engTeacherId => newEngTeacherId.indexOf(engTeacherId) === -1)
            .forEach(engTeacherId => {
              table.engCourseDtls.forEach(
                engCourseDtl => (engCourseDtl.teacherArr = engCourseDtl.teacherArr.filter(teacherId => teacherId !== engTeacherId))
              );
            });
        }

        table.engTeachers = newVal.map((item, index) => ({
          id: item.engTeacherId,
          label: `${index + 1}. ${item.teacherName}`,
        }));
      },
      { deep: true }
    );

    watch(engCourseDtls, newVal => {
      table.engCourseDtls = newVal.slice().map(item => ({
        ...item,
        courseDate: parseToDate(item.courseDate, '-'),
        timeStartArr: item.timeStart.split(':'),
        timeEndArr: item.timeEnd.split(':'),
        subjectArr: item.subjectList.split(','),
        // isSelected: false,
        // subjectIsAll: false,
        teacherArr: engCourseDtlTeachers.value.filter(i => i.engCourseDetailId === item.engCourseDetailId).map(i => i.engTeacherId),
        isEdit: false,
        _showDetails: true,
        checkValidityArr: [],
      }));
      table.maxEngCourseDetailId = Math.max(...table.engCourseDtls.map(i => i.engCourseDetailId).filter(i => i));
    });

    //為物件添加屬性,方便前端做數據操作
    onMounted(() => {
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const clockSelectHH = ref([]);
    for (let i = 8; i <= 22; i++) {
      let timeHH = {
        value: '',
        text: '',
      };
      if (i < 10) {
        timeHH.value = '0' + (i + '');
        timeHH.text = '0' + (i + '');
      } else {
        timeHH.value = i + '';
        timeHH.text = i + '';
      }
      clockSelectHH.value.push(timeHH);
    }

    const formOptions = reactive({
      courseLaw: [],
      subject: [],
      clockHH: clockSelectHH.value,
      clockMM: [
        { value: '00', text: '00' },
        { value: '10', text: '10' },
        { value: '20', text: '20' },
        { value: '30', text: '30' },
        { value: '40', text: '40' },
        { value: '50', text: '50' },
      ],
    });

    // //讓修改過的物件還原成本來的型態目前沒用到
    // function parseRenderDataToRaw(courseDetail) {
    //   courseDetail.time_Start = courseDetail.time_Start.join(':');
    //   courseDetail.time_End = courseDetail.time_End.join(':');
    //   courseDetail.subjectID = courseDetail.subjectID.join(',');
    // }

    const table: {
      fields: any;
      engCourseDtls: IEngCourseDtl[];
      maxEngCourseDetailId: number;
      engTeachers: { id: string; label: string }[];
    } = reactive({
      fields: [
        {
          // 項次
          key: 'index',
          label: i18n.t('label.index'),
          sortable: false,
          thStyle: 'width:3%',
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          // 課程主題
          key: 'courseName',
          label: i18n.t('label.courseTheme'),
          sortable: false,
          thStyle: 'width:16%',
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 日期
          key: 'courseDate',
          label: i18n.t('label.date'),
          sortable: false,
          thStyle: 'width:12%',
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          // 時間(起)
          key: 'timeStart',
          label: i18n.t('label.timeStart'),
          sortable: false,
          thStyle: 'width:6%',
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          // 時間(迄)
          key: 'timeEnd',
          label: i18n.t('label.timeEnd'),
          sortable: false,
          thStyle: 'width:6%',
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          // 適用款次
          key: 'law',
          label: i18n.t('label.law'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 核准積分
          key: 'point',
          label: i18n.t('label.approved').toString() + i18n.t('label.point').toString(),
          sortable: false,
          thStyle: 'width:3%',
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle ',
        },
        {
          // 講師
          key: 'teachers',
          label: i18n.t('label.teacher'),
          sortable: false,
          thStyle: 'width:14%',
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle ',
        },
        {
          // 修改註記
          key: 'remark',
          label: i18n.t('label.modifyRemark'),
          sortable: false,
          thStyle: 'width:14%',
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle ',
        },
      ],
      engCourseDtls: undefined,
      maxEngCourseDetailId: undefined,
      engTeachers: undefined,
    });

    // 審核時顯示審核勾選checkbox
    if (formStatus.value === FormStatusEnum.REVIEW) {
      table.fields.splice(1, 0, {
        // 核准
        key: 'review',
        sortable: false,
        thStyle: 'width:3%',
        thClass: 'text-center align-middle',
        tdClass: 'text-center align-middle',
      });
    } else {
      table.fields.splice(1, 0, {
        // 是否審核
        key: 'isReview',
        label: i18n.t('label.isReview'),
        sortable: false,
        thStyle: 'width:3%',
        thClass: 'text-center align-middle',
        tdClass: 'text-center align-middle',
      });
    }

    // 檢視時不顯示動作欄位
    if (formStatus.value !== FormStatusEnum.READONLY) {
      table.fields.push({
        // 動作
        key: 'action',
        label: i18n.t('label.action'),
        sortable: false,
        thStyle: 'width:10%',
        thClass: 'text-center align-middle',
        tdClass: 'text-center align-middle',
      });
    }

    const rules = {
      courseName: {
        required: required,
        maxLength: maxLength(600),
      },
      courseDate: {
        required: required,
      },
      law: {
        required: required,
      },
      teachers: {
        required: required,
      },
      remark: {},
    };

    // 每個科別checkbox，每個科別都選時"全部"也會打勾，反之只要一個沒選"全部"不會打勾
    function toggleSubject(data: IEngCourseDtl) {
      if (!data.subjectArr.includes('0') && data.subjectArr.length === formOptions.subject.length) {
        data.subjectArr.push('0');
      } else if (data.subjectArr.includes('0')) {
        data.subjectArr = data.subjectArr.filter(item => item !== '0');
      }
    }

    // 科別裡全選的按鈕
    function toggleAllSubject(data: IEngCourseDtl) {
      if (data.subjectArr.includes('0')) {
        formOptions.subject.forEach(item => {
          if (!data.subjectArr.includes(item.value)) data.subjectArr.push(item.value);
        });
        // data.subjectArr.sort((a, b) => Number(a) - Number(b));
      } else {
        data.subjectArr = [];
      }
    }

    function addCourseDetail() {
      const engCourseDtl = new EngCourseDtl();
      engCourseDtl.isEdit = true;
      table.engCourseDtls.push(engCourseDtl);
      isEditingCourseDetail.value = true;
    }

    function editCourseDetail(item: IEngCourseDtl) {
      item.isEdit = !item.isEdit;
      isEditingCourseDetail.value = !isEditingCourseDetail.value;
      engCourseDetailsTemp = Object.assign({}, item);
    }

    function saveCourseDetail(item: IEngCourseDtl, index: number) {
      Promise.all(item.checkValidityArr.map(checkValidity => checkValidity())).then((res: Array<boolean>) => {
        if (res.every(item => item)) {
          if (
            item.timeStartArr[0] > item.timeEndArr[0] ||
            (item.timeStartArr[0] === item.timeEndArr[0] && item.timeStartArr[1] >= item.timeEndArr[1])
          ) {
            $bvModal.msgBoxOk(`課程資訊第${index + 1}項次，上課結束時間不可小於等於開始時間。`);
          } else {
            if (!item.engCourseDetailId) {
              table.maxEngCourseDetailId += 1;
              item.engCourseDetailId = table.maxEngCourseDetailId;
            }
            item.isEdit = !item.isEdit;
            isEditingCourseDetail.value = !isEditingCourseDetail.value;
          }
        } else {
          $bvModal.msgBoxOk(`課程資訊第${index + 1}項次填寫有誤，請於修正完畢後再行暫存。`);
        }
      });
    }

    function deleteCourseDetail(item: IEngCourseDtl) {
      $bvModal.msgBoxConfirm('是否確認刪除此筆課程資訊？').then((isOK: boolean) => {
        if (isOK) {
          table.engCourseDtls = table.engCourseDtls.filter(i => i.engCourseDetailId !== item.engCourseDetailId);
        }
      });
    }

    function cancelAction(item: IEngCourseDtl) {
      if (item.engCourseDetailId) {
        Object.assign(item, engCourseDetailsTemp);
        item.isEdit = false;
      } else {
        table.engCourseDtls = table.engCourseDtls.filter(i => i.engCourseDetailId);
      }
      isEditingCourseDetail.value = false;
    }

    function toggleCourseDtlReview(courseDetailItem: any, flag: string) {
      // let addItem = Object.assign({}, courseDetailItem);
      // switch (flag) {
      //   case 'edit':
      //     context.emit('changeCourseDetailData', addItem, courseDetailItem.itemNo - 1, 'edit');
      //     break;
      //   case 'add':
      //     context.emit('changeCourseDetailData', addItem, courseDetailItem.itemNo - 1, 'add');
      //     break;
      //   case 'delete':
      //     context.emit('changeCourseDetailData', addItem, courseDetailItem.itemNo - 1, 'delete');
      //     break;
      //   default:
      //     break;
      // }
      allCourseDtlReview.value = table.engCourseDtls.every(item => item.isReview) ? true : false;
    }

    //判斷courseDetail是否全選
    function toggleAllcourseDtlReview() {
      // if (bool) {
      //   table.engCourseDtls.forEach(element => {
      //     // element.isSelected = true;
      //     toggleCourseDtlReview(element, 'edit');
      //   });
      // } else {
      //   table.engCourseDtls.forEach(element => {
      //     // element.isSelected = false;
      //     toggleCourseDtlReview(element, 'edit');
      //   });
      //   allCourseDtlReview.value = false;
      // }
      table.engCourseDtls.forEach(item => {
        item.isReview = allCourseDtlReview.value;
      });
    }

    function calculatePoint(item: IEngCourseDtl) {
      const totalMin =
        (Number(item.timeEndArr[0]) - Number(item.timeStartArr[0])) * 60 + (Number(item.timeEndArr[1]) - Number(item.timeStartArr[1]));
      const section = totalMin >= 200 ? 4 : totalMin >= 180 ? 3 : totalMin >= 90 ? 2 : totalMin >= 50 ? 1 : 0;
      switch (item.law) {
        case '1':
          item.point = section * 10;
          break;
        case '2':
          item.point = totalMin < 50 ? 10 : 20;
          break;
        case '3':
          item.point = section * 10;
          break;
        case '4':
          item.point = totalMin >= 120 ? section * 10 : 0;
          break;
        case '5':
          item.point = section * 10;
          break;
        case '6':
          item.point = section * 10;
          break;
        case '7':
          item.point = 0;
      }
    }

    return {
      FormStatusEnum,
      table,
      rules,
      formOptions,
      formOptionsFormatter,

      addCourseDetail,
      editCourseDetail,
      saveCourseDetail,
      deleteCourseDetail,
      cancelAction,
      calculatePoint,
      toggleSubject,
      toggleAllSubject,
      isEditingCourseDetail,

      courseDtlReview,
      allCourseDtlReview,
      toggleAllcourseDtlReview,
      toggleCourseDtlReview,

      courseNameComponent,
      courseDateComponent,
      lawComponent,
      teachersComponent,
      requiredIf,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>

<style scoped>
.input-checkbox {
  height: 15px;
  width: 15px;
}
</style>

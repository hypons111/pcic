<template>
  <div>
    <div v-if="isChecking">
      <b-form-checkbox v-model="courseDetailSelectAll" @change="toggleAllcourseDetail($event)">全部核准 </b-form-checkbox>
    </div>
    <b-table :items="courseDetailTable.courseDetailData" :fields="courseDetailTable.fields" responsive="sm" bordered>
      <template v-if="isChecking" #cell(check)="row">
        <b-form-checkbox v-model="row.item.isSelected" @change="triggerSaveCorseDetailData(row.item, 'edit')"> </b-form-checkbox>
      </template>

      <template #cell(name)="row">
        <b-form-input v-if="row.item.isEdit" type="text" v-model.trim="row.item.name"></b-form-input>
        <span v-else>{{ row.item.name }}</span>
      </template>
      <template #cell(date)="row">
        <i-date-picker v-if="row.item.isEdit" v-model="row.item.date"></i-date-picker>

        <span v-else>{{ formatDate(row.item.date, '/') }}</span>
      </template>

      <template #cell(time_Start)="row">
        <div v-if="row.item.isEdit">
          <select v-model="row.item.time_Start[0]">
            <option v-for="hh in clockSelectHH" :value="hh.value" :key="hh.value">
              {{ hh.text }}
            </option>
          </select>
          <select v-model="row.item.time_Start[1]">
            <option v-for="mm in clockSelectMM" :value="mm.value" :key="mm.value">
              {{ mm.text }}
            </option>
          </select>
        </div>
        <span v-else>{{ row.item.time_Start[0] }}:{{ row.item.time_Start[1] }}</span>
      </template>

      <template #cell(time_End)="row">
        <div v-if="row.item.isEdit">
          <select v-model="row.item.time_End[0]">
            <option v-for="hh in clockSelectHH" :value="hh.value" :key="hh.value">
              {{ hh.text }}
            </option>
          </select>
          <select v-model="row.item.time_End[1]">
            <option v-for="mm in clockSelectMM" :value="mm.value" :key="mm.value">
              {{ mm.text }}
            </option>
          </select>
        </div>
        <span v-else>{{ row.item.time_End[0] }}:{{ row.item.time_End[1] }}</span>
      </template>

      <template #cell(law)="row">
        <div v-if="row.item.isEdit">
          <b-form-select :options="applicableLaw" v-model="row.item.law"></b-form-select>
        </div>
        <span v-else>{{ lawValueToText(row.item.law) }}</span>
      </template>
      <template #cell(point)="row">
        <span>{{ row.item.point.value }}</span>
      </template>

      <template #cell(teacher)="row">
        <div v-if="row.item.isEdit">
          <b-form-select v-model="row.item.teacher">
            <option v-for="option in teacherArr" :value="option" :key="option.name">
              {{ option.name }}
            </option>
          </b-form-select>
        </div>
        <span v-else>{{ row.item.teacher.name }}</span>
      </template>

      <template #cell(remark)="row">
        <b-form-input v-if="row.item.isEdit && isChecking" type="text" v-model.trim="row.item.remark"></b-form-input>
        <span v-else>{{ row.item.remark }}</span>
      </template>
      <template #cell(action)="row">
        <i-button type="pencil-square" v-if="!row.item.isEdit" @click="editCourseDetailData(row.item)"></i-button>
        <i-button type="tag" v-if="row.item.isEdit" @click="saveCourseDetailData(row.item)"></i-button>
        <i-button class="mt-1" type="trash" v-if="!row.item.isEdit" @click="deleteCurrentCourseDetail(row.item)"></i-button>
        <i-button type="arrow-counterclockwise" v-show="row.item.isEdit && isAddingCourseDetail" @click="cancelAction(row.item)"></i-button>
      </template>

      <template #row-details="data">
        <div>
          <div class="card">
            <div class="card-header py-1 text-left">
              <div class="row align-items-center pl-3">
                <div class="col-sm-11 p-0">
                  <p class="m-0 text-bold">適用科別</p>
                </div>
              </div>
            </div>
            <div v-if="!isChecking" class="card-body">
              <b-form-checkbox @change="toggleAllSubject(data.item)" v-model="data.item.subjectIsAll" :disabled="!data.item.isEdit">
                全部
              </b-form-checkbox>
              <b-form-row>
                <div v-for="option in subjects" :key="option.value" class="col-sm-2 d-inline-block">
                  <b-form-checkbox v-model="data.item.subjectID" :value="option.value" :disabled="!data.item.isEdit">
                    {{ option.text }}
                  </b-form-checkbox>
                </div>
              </b-form-row>
            </div>
            <div v-else class="card-body">
              {{ subjectArrToStringValue(data.item.subjectID) }}
            </div>
          </div>
        </div>
      </template>
      <template #table-caption>
        <div v-if="!isChecking">
          <i-button type="plus-circle" v-show="!isAddingCourseDetail" @click="addNewCourseDetailData"></i-button>
        </div>
      </template>
    </b-table>
  </div>
</template>

<script lang="ts">
import { Ref, ref, reactive, watch, onMounted, computed, toRef } from '@vue/composition-api';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import { useBvModal } from '@/shared/modal';

export default {
  name: 'eng22courseD',
  props: {
    teachers: {
      required: false,
    },
    courseDetailArr: {
      required: false,
    },
    isCheckingProp: {
      type: Boolean,
      required: false,
      default: false,
    },
  },
  setup(props, context) {
    const $bvModal = useBvModal(); //modal物件
    let isAddingCourseDetail = ref(false); //是否添加新detail的判斷
    const teacherArr = toRef(props, 'teachers');
    const courseDetailArrOrigin: Ref = toRef(props, 'courseDetailArr');
    let courseDetailArray = ref([]);
    Object.assign(courseDetailArray.value, courseDetailArrOrigin.value); //從父元件的data 複製一份
    let isChecking = toRef(props, 'isCheckingProp'); //判斷是否為審核還是管理
    const courseDetailSelectAll = ref(false); //審核全選用
    //watch判斷是不是有改到父元件用
    // watch(props,(newValue)=>{
    //     console.log('courseDetailteachers',newValue.teachers)
    //     console.log('courseDetail',newValue.courseDetailArr)
    // })

    //為物件添加屬性,方便前端做數據操作
    onMounted(() => {
      courseDetailTable.courseDetailData = courseDetailTable.courseDetailData.map(item => ({
        ...item,
        isSelected: false,
        isEdit: false,
        subjectIsAll: false,
        _showDetails: true,
      }));
    });
    /*以下為建立下拉是選單的選項用的*/
    //#region
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
    const clockSelectMM = ref([
      { value: '00', text: '00' },
      { value: '10', text: '10' },
      { value: '20', text: '20' },
      { value: '30', text: '30' },
      { value: '40', text: '40' },
      { value: '50', text: '50' },
    ]);

    const applicableLaw = ref([
      { value: 1, text: '第一款' },
      { value: 2, text: '第二款' },
      { value: 3, text: '第三款' },
      { value: 5, text: '第五款(工程倫理)' },
      { value: 6, text: '第五款(執業相關法令)' },
      { value: 7, text: '第五款(政府採購全生命週期概論)' },
    ]);

    let selected = ref([]);
    const texts = [
      '土木工程',
      '水利工程',
      '結構工程',
      '大地工程',
      '測量',
      '環境工程',
      '都市計畫',
      '機械工程',
      '冷凍空調工程',
      '造船工程',
      '電機工程',
      '電子工程',
      '資訊',
      '航空工程',
      '化學工程',
      '工業工程',
      '工業安全',
      '工礦衛生',
      '紡織工程',
      '食品',
      '冶金工程',
      '農藝',
      '園藝',
      '林業',
      '畜牧',
      '漁撈',
      '水產養殖',
      '水土保持',
      '採礦工程',
      '應用地質',
      '礦業安全',
      '交通工程',
      '職業衛生',
    ];

    const values = [
      1,
      17,
      5,
      18,
      19,
      6,
      20,
      14,
      21,
      7,
      2,
      8,
      22,
      4,
      23,
      9,
      24,
      10,
      25,
      11,
      26,
      12,
      27,
      13,
      28,
      15,
      29,
      16,
      32,
      31,
      33,
      30,
      872,
    ];
    /*以上為選項用的*/

    let selectedAllArr = []; //來儲存subject全選的,以利點選全部的時候可以直接把值改成這個全選陣列
    let subjects = ref([]); //選項用的空陣列,透過以下的for loop來新增選項

    for (let i = 0; i < texts.length; i++) {
      let subject = {
        text: '',
        value: undefined,
      };
      subject.text = texts[i];
      if (values[i] < 10) {
        subject.value = '0' + values[i];
      } else {
        subject.value = '' + values[i];
      }
      selectedAllArr.push(subject.value);

      subjects.value.push(subject);
    }

    //#endregion

    const courseDetailDefault = {
      ID: '',
      name: '',
      itemNo: 0,
      date: undefined,
      time_Start: '',
      time_End: '',
      subjectID: '',
      point: '',
      law: '',
      teacher: undefined,
      modifyId: '',
      modifyDate: '',
    };
    //把data加工成可以被前端框架渲染的內容
    function parseRawToRenderData(courseDetail) {
      courseDetail.time_Start = courseDetail.time_Start.split(':');
      courseDetail.time_End = courseDetail.time_End.split(':');
      courseDetail.subjectID = courseDetail.subjectID.split(',');
    }
    //讓修改過的物件還原成本來的型態目前沒用到
    function parseRenderDataToRaw(courseDetail) {
      courseDetail.time_Start = courseDetail.time_Start.join(':');
      courseDetail.time_End = courseDetail.time_End.join(':');
      courseDetail.subjectID = courseDetail.subjectID.join(',');
    }
    function addOriginArrProp(courseDetail) {
      courseDetail.isSelected = false;
      courseDetail.isEdit = false;
      courseDetail.subjectIsAll = false;
      courseDetail._showDetails = true;
    }

    //遍歷arr 讓物件屬性作修改
    courseDetailArray.value.forEach(item => {
      parseRawToRenderData(item);
      addComputedProp(item);
      addOriginArrProp(item);
    });

    const courseDetailTable = reactive({
      fields: [
        {
          key: 'itemNo',
          label: '項次',
          sortable: false,
          thStyle: 'width:2.5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'name',
          label: '課程主題',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'date',
          label: '日期',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'time_Start',
          label: '時間(起)',
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'time_End',
          label: '時間(迄)',
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'law',
          label: '適用款次',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'point',
          label: '核准積分',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle ',
        },
        {
          key: 'teacher',
          label: '講師',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
        },
        {
          key: 'remark',
          label: '修改註記',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
        },
        {
          key: 'subjectID',
          label: '適用科別',
          sortable: false,
          thClass: 'd-none',
          tdClass: 'd-none',
        },
        {
          key: 'action',
          label: '功能',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      courseDetailData: undefined,
    });
    courseDetailTable.courseDetailData = courseDetailArray.value;

    //如果是審核的話多一個欄位
    if (isChecking.value === true) {
      courseDetailTable.fields.unshift({
        key: 'check',
        label: '核准',
        sortable: false,
        thStyle: 'width:2.5%',
        thClass: 'text-center',
        tdClass: 'text-right align-middle',
      });
    }
    //科目裡全選的按鈕
    function toggleAllSubject(data) {
      if (data.subjectID.length < texts.length) {
        data.subjectID = selectedAllArr;
      } else {
        data.subjectID = [];
      }
    }

    //判斷courseDetail是否全選
    function toggleAllcourseDetail(bool) {
      if (bool) {
        courseDetailTable.courseDetailData.forEach(element => {
          element.isSelected = true;
          triggerSaveCorseDetailData(element, 'edit');
        });
      } else {
        courseDetailTable.courseDetailData.forEach(element => {
          element.isSelected = false;
          triggerSaveCorseDetailData(element, 'edit');
        });
        courseDetailSelectAll.value = false;
      }
    }

    //watch判斷適用科別是否全選來自動改變course全選按鈕的值
    watch(
      () => courseDetailTable.courseDetailData,
      (newV, oldV) => {
        newV.forEach(item => {
          if (item.subjectID.length === texts.length) {
            item.subjectIsAll = true;
          } else {
            item.subjectIsAll = false;
          }
        });
        if (isChecking.value === true) {
          if (newV.length == newV.filter(element => element.isSelected === true).length) {
            courseDetailSelectAll.value = true;
          } else {
            courseDetailSelectAll.value = false;
          }
        }
      },
      { deep: true }
    );

    //可以編輯的data
    let editableCourseDateDefault = {
      ID: '',
      name: '',
      itemNo: 0,
      date: undefined,
      time_Start: [],
      time_End: [],
      subjectID: [],
      point: '',
      law: 1,
      teacher: undefined,
      modifyId: '',
      modifyDate: '',
      isEdit: true,
      subjectIsAll: false,
      _showDetails: true,
      isSelected: false,
    };
    //為物件的積分屬性加入computed屬性
    function addComputedProp(item) {
      item.point = computed(() => {
        let result = 10 * (item.time_End[0] - item.time_Start[0]);
        if (result >= 40) {
          return 40;
        } else {
          return result;
        }
      });
    }

    //CRUD以及透過emit修改父組件的陣列值
    function editCourseDetailData(data) {
      data.isEdit = !data.isEdit;
    }
    function saveCourseDetailData(item) {
      if (item.name == '' || item.teacher == undefined || item.teacher == {}) {
        // alert('請輸入資料')
        $bvModal.msgBoxOk('資料輸入不完全', { centered: true });
      } else {
        if (isAddingCourseDetail.value === true) {
          triggerSaveCorseDetailData(item, 'add');
          isAddingCourseDetail.value = false;
        } else {
          triggerSaveCorseDetailData(item, 'edit');
        }
        item.isEdit = !item.isEdit;
      }
    }
    function deleteCurrentCourseDetail(courseDetailItem) {
      $bvModal.msgBoxConfirm('是否確認刪除此筆內容？', { centered: true }).then((isOK: boolean) => {
        if (isOK) {
          courseDetailTable.courseDetailData = courseDetailTable.courseDetailData.filter(item => item.itemNo !== courseDetailItem.itemNo);
          courseDetailTable.courseDetailData.forEach((element, index) => {
            element.itemNo = index + 1;
          });
          triggerSaveCorseDetailData(courseDetailItem, 'delete');
        }
      });
    }

    function addNewCourseDetailData() {
      let newCourseD = Object.assign({}, editableCourseDateDefault);
      newCourseD.time_Start = ['08', '00'];
      newCourseD.time_End = ['09', '00'];
      newCourseD.itemNo = courseDetailTable.courseDetailData.length + 1;
      addComputedProp(newCourseD);

      courseDetailTable.courseDetailData.push(newCourseD);

      isAddingCourseDetail.value = true;
    }

    function cancelAction(courseDetailItem) {
      isAddingCourseDetail.value = false;
      courseDetailTable.courseDetailData = courseDetailTable.courseDetailData.filter(item => item.itemNo !== courseDetailItem.itemNo);
    }

    function triggerSaveCorseDetailData(courseDetailItem: any, flag: string) {
      // console.log('data:', courseDetailItem);
      let addItem = Object.assign({}, courseDetailItem);
      switch (flag) {
        case 'edit':
          context.emit('changeCourseDetailData', addItem, courseDetailItem.itemNo - 1, 'edit');
          break;
        case 'add':
          context.emit('changeCourseDetailData', addItem, courseDetailItem.itemNo - 1, 'add');
          break;
        case 'delete':
          context.emit('changeCourseDetailData', addItem, courseDetailItem.itemNo - 1, 'delete');
          break;
        default:
          break;
      }
    }

    //LAW 的value轉成text
    function lawValueToText(value) {
      return applicableLaw.value.find(element => element.value === value).text;
    }
    //subjectList 轉成 data
    function subjectArrToStringValue(subjectArr) {
      let tempString = [];
      subjectArr.forEach(Element => {
        let subjectText = subjects.value.find(ele => ele.value == Element);
        if (subjectText != undefined) {
          tempString.push(subjectText.text);
        }
      });
      return tempString.join();
    }

    return {
      isAddingCourseDetail,
      clockSelectHH,
      clockSelectMM,
      applicableLaw,
      teacherArr,
      subjects,
      selected,
      courseDetailTable,
      isChecking,
      lawValueToText,
      toggleAllSubject,
      formatDate,
      editCourseDetailData,
      addNewCourseDetailData,
      deleteCurrentCourseDetail,
      saveCourseDetailData,
      cancelAction,
      subjectArrToStringValue,
      courseDetailSelectAll,
      toggleAllcourseDetail,
      triggerSaveCorseDetailData,
    };
  },
};
</script>

<style scoped>
.checkbox-in-td {
  text-align: left;
}
.text-bold {
  font-weight: bold;
}
</style>

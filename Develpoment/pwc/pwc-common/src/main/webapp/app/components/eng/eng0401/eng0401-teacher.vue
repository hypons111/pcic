<template>
  <div>
    <b-table :items="table.engTeachers" :fields="table.fields" bordered small class="mb-0">
      <!-- 項次 -->
      <template #cell(index)="{ index }">
        {{ index + 1 }}
      </template>

      <!-- 姓名 -->
      <template #cell(teacherName)="{ item }">
        <i-form-single-check
          v-show="item.isEdit"
          :value="item.teacherName"
          :rules="formInnerTableRules.teacherName"
          @returnValidity="item.checkValidityArr.push($event)"
        >
          <b-form-input v-model="item.teacherName" trim lazy></b-form-input>
        </i-form-single-check>
        <!-- <i-tree-select
          v-if="item.isEdit"
          v-model="item.teacherName"
          placeholder="請輸入講師姓名關鍵字查詢(至少輸入兩個字)"
          :async="true"
          :load-options="loadOptions"
          :closeOnSelect="false"
          @select="selectTeacherForITreeSelect($event, item)"
        >
          <template #option-label="{ node }">
            {{ node.label }}
            <b-link class="float-right" @click="deleteTeacherForITreeSelect($event, node)">刪除</b-link>
          </template>
        </i-tree-select> -->
        <div v-show="!item.isEdit">{{ item.teacherName }}</div>
      </template>

      <!-- 最高學歷 -->
      <template #cell(teacherHighestEdu)="{ item }">
        <i-form-single-check
          v-show="item.isEdit"
          :value="item.teacherHighestEdu"
          :rules="formInnerTableRules.teacherHighestEdu"
          @returnValidity="item.checkValidityArr.push($event)"
        >
          <b-form-input v-model="item.teacherHighestEdu" trim lazy></b-form-input>
        </i-form-single-check>
        <div v-show="!item.isEdit">{{ item.teacherHighestEdu }}</div>
      </template>

      <!-- 現職 -->
      <template #cell(teacherJob)="{ item }">
        <i-form-single-check
          v-show="item.isEdit"
          :value="item.teacherJob"
          :rules="formInnerTableRules.teacherJob"
          @returnValidity="item.checkValidityArr.push($event)"
        >
          <b-form-input v-model="item.teacherJob" trim lazy></b-form-input>
        </i-form-single-check>
        <div v-show="!item.isEdit">{{ item.teacherJob }}</div>
      </template>

      <!-- 主要簡歷 -->
      <template #cell(teacherExperience)="{ item }">
        <i-form-single-check
          v-show="item.isEdit"
          :value="item.teacherExperience"
          :rules="formInnerTableRules.teacherExperience"
          @returnValidity="item.checkValidityArr.push($event)"
        >
          <b-form-textarea rows="3" v-model="item.teacherExperience"></b-form-textarea>
          <!-- <b-form-textarea rows="3" v-model="item.teacherExperience" :plaintext="!item.isEdit" :disabled="!item.isEdit"></b-form-textarea> -->
        </i-form-single-check>
        <div v-show="!item.isEdit">{{ item.teacherExperience }}</div>
        <!-- <i-part-of-message v-else type="hint" :id="item.teacherName" :value="item.teacherExperience"></i-part-of-message> -->
      </template>

      <!-- 動作 -->
      <template #cell(action)="{ item, index }">
        <!-- 編輯 -->
        <div v-if="!item.isEdit" :class="{ invisible: isEditingTeacher }">
          <i-button size="sm" class="mb-1" type="pencil-square" @click="editTeacher(item)"></i-button>
        </div>
        <!-- 刪除 -->
        <div v-if="!item.isEdit" :class="{ invisible: isEditingTeacher }">
          <i-button size="sm" class="mb-1" type="trash" @click="deleteTeacher(index)"></i-button>
        </div>
        <!-- 暫存 -->
        <div v-if="item.isEdit">
          <i-button size="sm" class="mb-1" type="tag" @click="saveTeacher(item, index)"></i-button>
        </div>
        <!-- 取消 -->
        <div v-if="item.isEdit">
          <i-button size="sm" type="arrow-counterclockwise" @click="cancelAction(item, index)"></i-button>
        </div>
      </template>

      <template #table-caption>
        <div v-if="formStatus === FormStatusEnum.EDIT && !isEditingTeacher">
          <!-- 講師查詢 -->
          <b-button v-b-modal.query-teacher-modal variant="info">講師查詢</b-button>
          <!-- 新增 -->
          <i-button v-if="!table.engTeachers || table.engTeachers.length === 0" type="file-earmark-plus" @click="addTeacher"></i-button>
          <!-- 繼續新增 -->
          <i-button v-else type="plus-circle" @click="addTeacher"></i-button>
        </div>
      </template>
    </b-table>
    <b-modal
      id="query-teacher-modal"
      size="lg"
      title="講師查詢"
      header-bg-variant="secondary"
      header-text-variant="light"
      @hide="handleHideQueryTeacherModel"
    >
      <i-form-group-check labelStar :label="$t('label.name') + '：'" class="col-12" label-cols="2" content-cols="10">
        <b-form-input
          class="col-9"
          placeholder="請輸入講師姓名關鍵字查詢(至少輸入兩個字)"
          v-model="table.queryString"
          @keyup.enter="handleQueryTeacher"
          trim
        ></b-form-input>
        <i-button class="ml-4" type="search" @click="handleQueryTeacher" :disabled="disabledQueryTeacherBtn"></i-button>
      </i-form-group-check>

      <b-table v-show="table.queryData" :items="table.queryData" :fields="table.fields" bordered>
        <!-- 項次 -->
        <template #cell(index)="{ index }">
          {{ index + 1 }}
        </template>
        <!-- 動作 -->
        <template #cell(action)="{ item }">
          <b-button v-if="!teacherIsSelected(item)" size="sm" class="mb-1" @click="selectTeacher(item)" variant="info">選擇</b-button>
          <b-button v-else size="sm" class="mb-1" disabled variant="info">已選擇</b-button>
          <br />
          <i-button size="sm" type="trash" @click="deleteEngTeacher(item)"></i-button>
        </template>
      </b-table>

      <template #modal-footer="{ cancel }">
        <b-button @click="cancel()" variant="info">關閉</b-button>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import i18n from '@/shared/i18n';
import { useBvModal } from '@/shared/modal';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { ASYNC_SEARCH } from '@riophae/vue-treeselect';
import { EngTeacher, IEngTeacher } from '@/shared/model/eng/eng-teacher.model';
import { IEngCourseDtlTeacher } from '@/shared/model/eng/eng-course-dtl-teacher.model';
import { Ref, ref, reactive, onMounted, computed, watch, defineComponent, toRefs, inject, unref } from '@vue/composition-api';
import { FormStatusEnum } from '@/shared/enum';
import { maxLength, required } from '@/shared/validators';
import _ from 'lodash';

import ITreeSelect from '@/shared/i-tree-select/i-tree-select.vue';

interface propsType {
  formStatus: FormStatusEnum;
}

export default defineComponent<propsType>({
  name: 'Eng0401Teacher',
  components: { ITreeSelect },
  props: {
    formStatus: {
      required: false,
    },
  },
  setup(props, context) {
    const $bvModal = useBvModal();
    const notificationService = useNotification();
    const { formStatus } = toRefs(props);
    const isAddingTeacher = ref(false); // 是否為新增導師，用在按取消時判斷移除新增那筆或還原該筆至修改前
    const isEditingTeacher = ref(false); // 是否為編輯導師，用在編輯時隱藏其他會異動資料的按鈕
    let teacherDataTemp = undefined; // 紀錄修改前的teacher資料

    const teacherNameComponent = ref(null);
    const teacherHighestEduComponent = ref(null);
    const teacherJobComponent = ref(null);
    const teacherExperienceComponent = ref(null);

    const engTeachers = inject<Ref<IEngTeacher[]>>('engTeachers');
    const updateEngTeachers = inject<Function>('updateEngTeachers');

    watch(engTeachers, newVal => {
      // 為了斷掉與父層原物件reference，使用cloneDeep深拷貝
      table.engTeachers = _.cloneDeep(newVal);
    });

    const table: { fields: any; engTeachers: IEngTeacher[]; queryString: string; queryData: IEngTeacher[] } = reactive({
      fields: [
        {
          // 項次
          key: 'index',
          label: i18n.t('label.index'),
          sortable: false,
          thStyle: 'width:2.5%',
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          // 姓名
          key: 'teacherName',
          label: i18n.t('label.name'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 最高學歷
          key: 'teacherHighestEdu',
          label: i18n.t('label.teacherHighestEdu'),
          sortable: false,
          thStyle: 'width:25%',
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 現職
          key: 'teacherJob',
          label: i18n.t('label.teacherJob'),
          sortable: false,
          thStyle: 'width:25%',
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 主要簡歷(限300字)
          key: 'teacherExperience',
          label: i18n.t('label.teacherExperience'),
          sortable: false,
          thStyle: 'width:25%',
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
      ],
      engTeachers: undefined,
      queryData: undefined,
      queryString: '',
    });

    if (formStatus.value === FormStatusEnum.EDIT) {
      table.fields.push({
        key: 'action',
        label: i18n.t('label.action'),
        sortable: false,
        thStyle: 'width:12.5%',
        thClass: 'text-center align-middle',
        tdClass: 'text-center align-middle',
      });
    }

    //rule
    const formInnerTableRules = {
      teacherName: {
        required: required,
        maxLength: maxLength(200),
      },
      teacherHighestEdu: {
        required: required,
        maxLength: maxLength(200),
      },
      teacherJob: {
        required: required,
        maxLength: maxLength(200),
      },
      teacherExperience: {
        required: required,
        maxLength: maxLength(300),
      },
    };

    function addTeacher() {
      const engTeacher = new EngTeacher();
      engTeacher.isEdit = true;
      table.engTeachers.push(engTeacher);
      isAddingTeacher.value = true;
      isEditingTeacher.value = true;
    }

    function editTeacher(data: IEngTeacher) {
      data.isEdit = true;
      isEditingTeacher.value = true;
      teacherDataTemp = Object.assign({}, data);
    }

    function saveTeacher(data: IEngTeacher, dataIndex: number) {
      Promise.all(data.checkValidityArr.map(checkValidity => checkValidity())).then((res: Array<boolean>) => {
        if (res.every(item => item)) {
          if (!data.engTeacherId) {
            axios
              .get(`/eng-teachers/get-new-pk`)
              .then(res => {
                data.engTeacherId = res.data.engTeacherId;
              })
              .catch(notificationErrorHandler(notificationService));
          }
          data.isEdit = false;
          isAddingTeacher.value = false;
          isEditingTeacher.value = false;
          updateEngTeachers(table.engTeachers);
        } else {
          $bvModal.msgBoxOk(`教師資訊第${dataIndex + 1}項次填寫有誤，請於修正完畢後再行暫存。`);
        }
      });
    }

    function deleteTeacher(dataIndex: number) {
      $bvModal.msgBoxConfirm('是否確認刪除此筆講師資訊？').then((isOK: boolean) => {
        if (isOK) {
          table.engTeachers = table.engTeachers.filter((item, index) => index !== dataIndex);
          updateEngTeachers(table.engTeachers);
        }
      });
    }

    function cancelAction(data: IEngTeacher, dataIndex: number) {
      if (isAddingTeacher.value) {
        table.engTeachers = table.engTeachers.filter((item, index) => index !== dataIndex);
      } else {
        Object.assign(data, teacherDataTemp);
        data.isEdit = false;
      }
      isAddingTeacher.value = false;
      isEditingTeacher.value = false;
    }

    function selectTeacher(data: IEngTeacher) {
      table.engTeachers.push(data);
      $bvModal.hide('query-teacher-modal');
    }

    // 直接刪除 ENG_TEACHER 資料
    function deleteEngTeacher(data: IEngTeacher) {
      $bvModal.msgBoxConfirm('是否確認刪除此筆講師資訊？').then((isOK: boolean) => {
        if (isOK) {
          axios
            .delete(`/eng-teachers/${data.engTeacherId}`)
            .then(() => {
              handleQueryTeacher();
              $bvModal.msgBoxOk('刪除成功');
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    const disabledQueryTeacherBtn = computed(() => {
      let pattern = /[\u3105-\u3129\u02CA\u02C7\u02CB\u02D9]/;
      return pattern.test(table.queryString) || table.queryString.length < 2;
    });

    function handleQueryTeacher() {
      if (disabledQueryTeacherBtn.value) return;
      console.log(table.queryString);
      axios
        .post(`/eng-teachers/find-by-teacher-name-like`, { teacherName: table.queryString })
        .then(res => {
          table.queryData = res.data.slice(0, res.data.length);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function handleHideQueryTeacherModel() {
      table.queryString = '';
      table.queryData = undefined;
    }

    function teacherIsSelected(data: IEngTeacher) {
      return table.engTeachers.map(item => item.engTeacherId).includes(data.engTeacherId);
    }

    function triggerTeacherDataInParent(rowTeacher: any, flag: string) {
      let reviseTeacher = Object.assign({}, rowTeacher.item);
      delete reviseTeacher['isEdit'];

      switch (flag) {
        case 'edit':
          context.emit('changeTeacherData', reviseTeacher, rowTeacher.index, 'edit');
          break;
        case 'add':
          context.emit('changeTeacherData', reviseTeacher, rowTeacher.index, 'add');
          break;
        case 'delete':
          context.emit('changeTeacherData', reviseTeacher, rowTeacher.index, 'delete');
          break;
        default:
          break;
      }
    }

    function loadOptions({ action, searchQuery, callback }) {
      let pattern = /[\u3105-\u3129\u02CA\u02C7\u02CB\u02D9]/; // 判斷輸入注音時不查詢
      if (action === ASYNC_SEARCH && !pattern.test(searchQuery) && searchQuery.length >= 2) {
        axios
          .post(`/eng-teachers/find-by-teacher-name-like`, { teacherName: searchQuery })
          .then(res => {
            callback(
              null,
              res.data.map((item: IEngTeacher) => ({
                id: item.engTeacherId,
                label: item.teacherName,
                value: item,
              }))
            );
          })
          .catch(notificationErrorHandler(notificationService));
      }
    }

    function selectTeacherForITreeSelect(node, item) {
      item.teacherHighestEdu = node.value.teacherHighestEdu;
      item.teacherJob = node.value.teacherJob;
      item.teacherExperience = node.value.teacherExperience;
    }

    function deleteTeacherForITreeSelect(event, node) {
      axios
        .delete(`/eng-teachers/${node.id}`)
        .then(() => {
          $bvModal.msgBoxOk('刪除成功');
        })
        .catch(notificationErrorHandler(notificationService));
      // event.stopPropagation();
    }

    return {
      FormStatusEnum,
      table,
      teacherNameComponent,
      teacherHighestEduComponent,
      teacherJobComponent,
      teacherExperienceComponent,
      formInnerTableRules,
      isEditingTeacher,
      addTeacher,
      editTeacher,
      saveTeacher,
      deleteTeacher,
      cancelAction,
      selectTeacher,
      deleteEngTeacher,
      disabledQueryTeacherBtn,
      handleQueryTeacher,
      handleHideQueryTeacherModel,
      teacherIsSelected,
      loadOptions,
      selectTeacherForITreeSelect,
      deleteTeacherForITreeSelect,
    };
  },
});
</script>

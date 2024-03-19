<template>
  <div>
    <b-table :items="teacherTable.teacherData" :fields="teacherTable.fields" bordered responsive="sm">
      <template #cell(index)="data">
        {{ data.index + 1 }}
      </template>

      <template #cell(name)="row">
        <i-form-input type="text" v-if="row.item.isEdit" v-model="row.item.name" :rules="formInnerTableRules.nameRule"></i-form-input>
        <span v-else>{{ row.item.name }}</span>
      </template>
      <template #cell(highEdu)="row">
        <b-form-input v-if="row.item.isEdit" type="text" v-model="row.item.highEdu"></b-form-input>
        <span v-else>{{ row.item.highEdu }}</span>
      </template>
      <template #cell(job)="row">
        <b-form-input v-if="row.item.isEdit" type="text" v-model="row.item.job"></b-form-input>
        <span v-else>{{ row.item.job }}</span>
      </template>
      <template #cell(experience)="row">
        <b-form-textarea
          placeholder="備註..."
          rows="3"
          max-rows="3"
          v-if="row.item.isEdit"
          type="text"
          v-model="row.item.experience"
        ></b-form-textarea>
        <span v-else>{{ row.item.experience }}</span>
      </template>

      <template v-if="isEditTeachers" #cell(action)="row">
        <div>
          <!-- <b-button v-show="!row.item.isEdit" @click="editTeacherData(row)">
            <font-awesome-icon icon="edit"> </font-awesome-icon>
            {{ $t('button.modify') }}
          </b-button> -->
          <i-button type="pencil-square" v-show="!row.item.isEdit" @click="editTeacherData(row)"></i-button>
          <!-- <b-button v-show="!row.item.isEdit" @click="deleteCurrentData(row)">
            <font-awesome-icon icon="trash"> </font-awesome-icon>
            {{ $t('button.delete') }}
          </b-button> -->
          <i-button type="trash" v-show="!row.item.isEdit" @click="deleteCurrentData(row)"></i-button>
          <!-- <b-button v-show="row.item.isEdit" @click="saveTeacherData(row)">
            <font-awesome-icon icon="tag"> </font-awesome-icon>
            {{ $t('button.temporary') }}
          </b-button> -->
          <i-button type="tag" v-show="row.item.isEdit" @click="saveTeacherData(row)"></i-button>
          <!-- <b-button v-show="row.item.isEdit" @click="cancelAction(row)">
            <font-awesome-icon icon="undo-alt"> </font-awesome-icon>
            {{ $t('button.cancel') }}
          </b-button> -->
          <i-button type="arrow-counterclockwise" v-show="row.item.isEdit" @click="cancelAction(row)"></i-button>
        </div>
      </template>
      <template #table-caption>
        <!-- <b-button v-if="isEditTeachers" v-show="!isAdding" @click="addNewTeacher()"
          ><font-awesome-icon icon="plus"> </font-awesome-icon> {{ $t('button.insert') }}教師
        </b-button> -->
        <i-button type="plus-circle" v-if="isEditTeachers" v-show="!isAdding" @click="addNewTeacher()"></i-button>
      </template>
    </b-table>

    <b-modal id="delete-model" centered title="BootstrapVue">
      <p class="my-4">是否確定刪除</p>
    </b-modal>
  </div>
</template>

<script lang="ts">
import { Ref, ref, reactive, onMounted, computed, watch } from '@vue/composition-api';
import { required } from '@/shared/validators';
import { useBvModal } from '@/shared/modal';
export default {
  name: 'eng22Teacher',
  props: {
    teachers: {
      required: false,
    },
    isEditTeacher: {
      type: Boolean,
      required: false,
      default: true,
    },
  },
  setup(props, context) {
    const teacherArrOrigin: Ref = computed(() => props.teachers);
    let isEditTeachers = computed(() => props.isEditTeacher);
    let teachersArr = ref([]);
    Object.assign(teachersArr.value, teacherArrOrigin.value);
    const $bvModal = useBvModal(); //modal物件

    watch(props, newValue => {
      // console.log('watch', newValue.teachers);
    });

    //把每個物件都加一個isEdit屬性
    onMounted(() => {
      if (isEditTeachers.value) {
        teacherTable.teacherData = teacherTable.teacherData.map(item => ({
          ...item,
          isEdit: false,
        }));
      }
    });
    let isAdding = ref(false);

    const teacherDefault = {
      name: '',
      highEdu: '',
      job: '',
      experience: '',
    };

    let newTeacher = ref([
      {
        name: '',
        highEdu: '',
        job: '',
        experience: '',
      },
    ]);

    const teacherTable = reactive({
      fields: [
        {
          key: 'index',
          label: '項次',
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-top ',
        },
        {
          key: 'name',
          label: '姓名',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-top ',
        },
        {
          key: 'highEdu',
          label: '最高學歷',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-top ',
        },
        {
          key: 'job',
          label: '現職',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-top ',
        },
        {
          key: 'experience',
          label: '主要簡歷(限300字)',
          sortable: false,
          thStyle: 'width:25%',
          thClass: 'text-center',
          tdClass: 'text-left align-top ',
        },
      ],
      teacherData: undefined,
    });
    if (isEditTeachers.value) {
      teacherTable.fields.push({
        key: 'action',
        label: '動作',
        sortable: false,
        thStyle: 'width:20%',
        thClass: 'text-center',
        tdClass: 'text-left align-middle',
      });
    }
    teacherTable.teacherData = teachersArr.value;

    //rule
    const formInnerTableRules = {
      nameRule: {
        required: required,
      },
    };

    function addNewTeacher() {
      let newT = {
        name: '',
        highEdu: '',
        job: '',
        experience: '',
        isEdit: true,
      };
      teacherTable.teacherData.push(newT);
      isAdding.value = true;
    }
    function deleteCurrentData(rowData) {
      if (!isAdding.value) {
        $bvModal.msgBoxConfirm('是否確認刪除此筆內容？', { centered: true }).then((isOK: boolean) => {
          if (isOK) {
            teacherTable.teacherData = teacherTable.teacherData.filter((item, i) => i !== rowData.index);
            triggerTeacherDataInParent(rowData, 'delete');
          }
        });
      } else {
        teacherTable.teacherData = teacherTable.teacherData.filter((item, i) => i !== rowData.index);
      }
    }
    function saveTeacherData(rowData) {
      if (rowData.item.name === '' || rowData.item.highEdu === '' || rowData.item.job === '' || rowData.item.experience === '') {
        $bvModal.msgBoxOk('資料輸入不完全', { centered: true });
      } else {
        currentTeacherData = {};
        teacherTable.teacherData[rowData.index].isEdit = false;
        if (isAdding.value) {
          isAdding.value = false;
          triggerTeacherDataInParent(rowData, 'add');
        } else {
          triggerTeacherDataInParent(rowData, 'edit');
        }
      }
    }
    //紀錄修改前的teacher資料
    let currentTeacherData = {};
    function cancelAction(rowData) {
      if (isAdding.value) {
        deleteCurrentData(rowData);
        isAdding.value = false;
        return;
      }
      teacherTable.teacherData[rowData.index] = Object.assign(teacherTable.teacherData[rowData.index], currentTeacherData);
      teacherTable.teacherData[rowData.index].isEdit = false;
      currentTeacherData = {};
    }
    function editTeacherData(rowData) {
      currentTeacherData = Object.assign({}, rowData.item);
      teacherTable.teacherData[rowData.index].isEdit = !teacherTable.teacherData[rowData.index].isEdit;
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

    return {
      teacherTable,
      teachersArr,
      newTeacher,
      formInnerTableRules,
      isAdding,
      isEditTeachers,
      editTeacherData,
      addNewTeacher,
      saveTeacherData,
      cancelAction,
      deleteCurrentData,
    };
  },
};
</script>

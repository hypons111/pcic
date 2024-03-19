<template>
  <div>
    <b-table :items="compTable.compData" :fields="compTable.fields" 
    bordered responsive="sm">
      <!-- 項次 -->
      <template #cell(index)="data">
        {{ data.index + 1 }}
      </template>
      <!-- 姓名 -->
      <template #cell(chName)="row">
        <i-form-input type="text" v-if="row.item.isEdit" 
        v-model="row.item.chName" :rules="formInnerTableRules.nameRule"></i-form-input>
        <span v-else>{{ row.item.chName }}</span>
      </template>
      <!-- 最高學歷 -->
      <template #cell(highestDegree)="row">
        <b-form-input v-if="row.item.isEdit" type="text" 
        v-model="row.item.highestDegree"></b-form-input>
        <span v-else>{{ row.item.highestDegree }}</span>
      </template>
      <!-- 現職 -->
      <template #cell(teacherJob)="row">
        <b-form-input v-if="row.item.isEdit" type="text" 
        v-model="row.item.teacherJob"></b-form-input>
        <span v-else>{{ row.item.teacherJob }}</span>
      </template>
      <!-- 主要履歷 -->
      <template #cell(mainResume)="row">
        <b-form-input v-if="row.item.isEdit" type="text" 
        v-model="row.item.mainResume"></b-form-input>
        <span v-else>{{ row.item.mainResume }}</span>
      </template>

      <template v-if="isEditCompC" #cell(action)="row">
        <div>
          <!-- <b-button v-show="!row.item.isEdit" @click="editTeacherData(row)">
            <font-awesome-icon icon="edit"> </font-awesome-icon>
            {{ $t('button.modify') }}
          </b-button> -->
          <i-button type="pencil-square" v-show="!row.item.isEdit" 
          @click="editCompData(row)"></i-button>
          <!-- <b-button v-show="!row.item.isEdit" @click="deleteCurrentData(row)">
            <font-awesome-icon icon="trash"> </font-awesome-icon>
            {{ $t('button.delete') }}
          </b-button> -->
          <i-button type="trash" v-show="!row.item.isEdit" 
          @click="deleteCurrentData(row)"></i-button>
          <!-- <b-button v-show="row.item.isEdit" @click="saveTeacherData(row)">
            <font-awesome-icon icon="tag"> </font-awesome-icon>
            {{ $t('button.temporary') }}
          </b-button> -->
          <i-button type="tag" v-show="row.item.isEdit" 
          @click="saveCompData(row)"></i-button>
          <!-- <b-button v-show="row.item.isEdit" @click="cancelAction(row)">
            <font-awesome-icon icon="undo-alt"> </font-awesome-icon>
            {{ $t('button.cancel') }}
          </b-button> -->
          <i-button type="arrow-counterclockwise" v-show="row.item.isEdit" 
          @click="cancelAction(row)"></i-button>
        </div>
      </template>
      <template #table-caption>
        <!-- <b-button v-if="isEditTeachers" v-show="!isAdding" @click="addNewTeacher()"
          ><font-awesome-icon icon="plus"> </font-awesome-icon> {{ $t('button.insert') }}教師
        </b-button> -->
        <i-button type="plus-circle" v-if="isEditCompC" v-show="!isAdding"
         @click="addNewComp()"></i-button>
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
import i18n from '@/shared/i18n';

export default {
  name: 'Eng0404EditComp',
  props: {
    comps: {
      required: false,
    },
    isEditComp: {
      type: Boolean,
      required: false,
      default: true,
    },
  },
  setup(props, context) {
    const compArrOrigin: Ref = computed(() => props.comps);
    let isEditCompC = computed(() => props.isEditComp);
    let compArr = ref([]);
    Object.assign(compArr.value, compArrOrigin.value);
    const $bvModal = useBvModal();

    watch(props, newValue =>  {

    });

    //把每個物件都加一個isEdit屬性
    onMounted(() => {
      if(isEditCompC.value) {
        compTable.compData = compTable.compData.map(item => ({
          ...item,
          isEdit: false,
        }))
      }
    });
    let isAdding = ref(false);

    const compDefault = {
      chName: '',
      highestDegree: '',
      teacherJob: '',
      mainResume: '',

      // agentName: '',
      // compIdno: '',
      // caslicenseNoeNo: '',
      // execDate: '',
    };

    let newComp = ref([
      {
        chName: '',
        highestDegree: '',
        teacherJob: '',
        mainResume: '',

        // agentName: '',
        // compIdno: '',
        // caslicenseNoeNo: '',
        // execDate: '',
      },
    ]);

    const compTable = reactive({
      fields: [
        {  // 項次
          key: 'index',
          label: i18n.t('label.no'),
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-center align-top ',
        },
        {  // 姓名(中)
          key: 'chName',
          label: "姓名",
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-top ',
        },
        {  // 最高學歷
          key: 'highestDegree',
          label: i18n.t('label.highestDegree'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-top ',
        },
        {  // 現職
          key: 'teacherJob',
          label: i18n.t('label.teacherJob'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-top ',
        },
        {
          // 主要履歷
          key: 'mainResume',
          label: "主要履歷(限300字)",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      compData: undefined,
    });
    if(isEditCompC.value) {
      compTable.fields.push({
        key: 'action',
        label: '動作',
        sortable: false,
        thStyle: 'width:20%',
        thClass: 'text-center',
        tdClass: 'text-left align-middle',
      })
    }
    compTable.compData = compArr.value;

    //rule
    const formInnerTableRules = {
      nameRule: {
        required: required,
      },
    };

    function addNewComp() {
      let newC = {
        chName: '',
        highestDegree: '',
        teacherJob: '',
        mainResume: '',
        isEdit: true,
        // agentName: '',
        // compIdno: '',
        // caslicenseNoeNo: '',
        // execDate: '',
        // isEdit: true,
      };
      compTable.compData.push(newC);
      isAdding.value = true;
    }
    function deleteCurrentData(rowData) {
      if (!isAdding.value) {
        $bvModal.msgBoxConfirm('是否確認刪除此筆內容？', { centered: true }).then((isOK: boolean) => {
          if (isOK) {
            compTable.compData = compTable.compData.filter((item, i) => i !== rowData.index);
            triggerCompDataInParent(rowData, 'delete');
          }
        });
      } else {
        compTable.compData = compTable.compData.filter((item, i) => i !== rowData.index);
      }
    }
    function saveCompData(rowData) {
      if (rowData.item.name === '' || rowData.item.highEdu === '' || rowData.item.job === '' || rowData.item.experience === '') {
        $bvModal.msgBoxOk('資料輸入不完全', { centered: true });
      } else {
        currentCompData = {};
        compTable.compData[rowData.index].isEdit = false;
        if (isAdding.value) {
          isAdding.value = false;
          triggerCompDataInParent(rowData, 'add');
        } else {
          triggerCompDataInParent(rowData, 'edit');
        }
      }
    }
    //紀錄修改前的comp資料
    let currentCompData = {};
    function cancelAction(rowData) {
      if (isAdding.value) {
        deleteCurrentData(rowData);
        isAdding.value = false;
        return;
      }
      compTable.compData[rowData.index] = Object.assign(compTable.compData[rowData.index], currentCompData);
      compTable.compData[rowData.index].isEdit = false;
      currentCompData = {};
    }
    function editCompData(rowData) {
      currentCompData = Object.assign({}, rowData.item);
      compTable.compData[rowData.index].isEdit = !compTable.compData[rowData.index].isEdit;
    }

    function triggerCompDataInParent(rowComp: any, flag: string) {
      let reviseComp = Object.assign({}, rowComp.item);
      delete reviseComp['isEdit'];

      switch (flag) {
        case 'edit':
          context.emit('changeCompData', reviseComp, rowComp.index, 'edit');
          break;
        case 'add':
          context.emit('changeCompData', reviseComp, rowComp.index, 'add');
          break;
        case 'delete':
          context.emit('changeCompData', reviseComp, rowComp.index, 'delete');
          break;
        default:
          break;
      }
    }

    return {
      compArr,
      compTable,
      newComp,
      formInnerTableRules,
      isAdding,
      isEditCompC,
      editCompData,
      addNewComp,
      saveCompData,
      cancelAction,
      deleteCurrentData,
    }
  }
}
</script>
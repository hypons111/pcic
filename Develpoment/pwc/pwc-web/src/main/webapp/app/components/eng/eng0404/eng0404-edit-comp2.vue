<template>
  <div>
    <b-table :items="compTable.compData" :fields="compTable.fields" 
    bordered responsive="sm">
      <!-- 項次 -->
      <template #cell(index)="data">
        {{ data.index + 1 }}
      </template>
      <!-- 是否審核 -->
      <template #cell(isReview)="row">
        <i-form-input type="text" v-if="row.item.isEdit" 
        v-model="row.item.isReview" :rules="formInnerTableRules.nameRule"></i-form-input>
        <span v-else>{{ row.item.isReview }}</span>
      </template>
      <!-- 課程主題 -->
      <template #cell(courseTheme)="row">
        <b-form-input v-if="row.item.isEdit" type="text" 
        v-model="row.item.courseTheme"></b-form-input>
        <span v-else>{{ row.item.courseTheme }}</span>
      </template>
      <!-- 日期 -->
      <template #cell(date)="row">
        <div v-if="row.item.isEdit">
          <i-form-input type="text"  
        v-model="row.item.dateStart" 
        :rules="formInnerTableRules.nameRule"></i-form-input>
        ~<br/>
        <i-form-input type="text"
        v-model="row.item.dateEnd" 
        :rules="formInnerTableRules.nameRule"></i-form-input>
        </div>
        <span v-else>{{ row.item.dateStart }}<br/>~ <br/> 
        {{ row.item.dateEnd }}</span>
      </template>
      <!-- 時間(起) -->
      <template #cell(timeStart)="row">
        <b-form-input v-if="row.item.isEdit" type="text" 
        v-model="row.item.timeStart"></b-form-input>
        <span v-else>{{ row.item.timeStart }}</span>
      </template>
      <!-- 時間(迄) -->
      <template #cell(timeEnd)="row">
        <b-form-input v-if="row.item.isEdit" type="text" 
        v-model="row.item.timeEnd"></b-form-input>
        <span v-else>{{ row.item.timeEnd }}</span>
      </template>
      <!-- 適用款次 -->
      <template #cell(law)="row">
        <b-form-input v-if="row.item.isEdit" type="text" 
        v-model="row.item.law"></b-form-input>
        <span v-else>{{ row.item.law }}</span>
      </template>
      <!-- 核准積分 -->
      <template #cell(approvedPoint)="row">
        <b-form-input v-if="row.item.isEdit" type="text" 
        v-model="row.item.approvedPoint"></b-form-input>
        <span v-else>{{ row.item.approvedPoint }}</span>
      </template>
      <!-- 講師 -->
      <template #cell(teacher)="row">
        <b-form-input v-if="row.item.isEdit" type="text" 
        v-model="row.item.teacher"></b-form-input>
        <span v-else>{{ row.item.teacher }}</span>
      </template>
      <!-- 修改註記 -->
      <template #cell(modifyRemark)="row">
        <b-form-input v-if="row.item.isEdit" type="text" 
        v-model="row.item.modifyRemark"></b-form-input>
        <span v-else>{{ row.item.modifyRemark }}</span>
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
  name: 'Eng0404EditComp2',
  props: {
    engrs: {
      required: false,
    },
    isEditComp: {
      type: Boolean,
      required: false,
      default: true,
    },
  },
  setup(props, context) {
    const compArrOrigin: Ref = computed(() => props.engrs);
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
      isReview: '',
      courseTheme: '',
      date: '',
      timeStart: '',
      timeEnd: '',
      law: '',
      approvedPoint: '',
      teacher: '',
      modifyRemark: '',

      // agentName: '',
      // compIdno: '',
      // caslicenseNoeNo: '',
      // execDate: '',
    };

    let newComp = ref([
      {
        isReview: '',
        courseTheme: '',
        date: '',
        timeStart: '',
        timeEnd: '',
        law: '',
        approvedPoint: '',
        teacher: '',
        modifyRemark: '',

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
        {  // 是否審核
          key: 'isReview',
          label: i18n.t('label.isReview'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-top ',
        },
        {  // 課程主題
          key: 'courseTheme',
          label: i18n.t('label.courseTheme'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-top ',
        },
        {  // 日期
          key: 'date',
          label: i18n.t('label.date'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-top ',
        },
        {
          // 時間(起)
          key: 'timeStart',
          label: i18n.t('label.timeStart'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {  // 時間(迄)
          key: 'timeEnd',
          label: i18n.t('label.timeEnd'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-top ',
        },
        {  // 適用款次
          key: 'law',
          label: i18n.t('label.law'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-top ',
        },
        {
          // 核准積分
          key: 'approvedPoint',
          label: i18n.t('label.approvedPoint'),
          sortable: true,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {  // 講師
          key: 'teacher',
          label: i18n.t('label.teacher'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-top ',
        },
        {  // 修改註記
          key: 'modifyRemark',
          label: i18n.t('label.modifyRemark'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-top ',
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
        isReview: '',
        courseTheme: '',
        date: '',
        timeStart: '',
        timeEnd: '',
        law: '',
        approvedPoint: '',
        teacher: '',
        modifyRemark: '',
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
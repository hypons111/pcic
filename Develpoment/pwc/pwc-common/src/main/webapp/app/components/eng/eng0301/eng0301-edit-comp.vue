<template>
  <div>
    <b-table :items="compTable.compData" :fields="compTable.fields" 
    bordered responsive="sm">
      <!-- 項次 -->
      <template #cell(index)="data">
        {{ data.index + 1 }}
      </template>
      <!-- 機關名稱 -->
      <template #cell(agentName)="row">
        <i-form-input type="text" v-if="row.item.isEdit" 
        v-model="row.item.agentName" :rules="formInnerTableRules.nameRule"></i-form-input>
        <span v-else>{{ row.item.agentName }}</span>
      </template>
      <!-- 統一編號 -->
      <template #cell(compIdno)="row">
        <b-form-input v-if="row.item.isEdit" type="text" 
        v-model="row.item.compIdno"></b-form-input>
        <span v-else>{{ row.item.compIdno }}</span>
      </template>
      <!-- 登記證字號 -->
      <template #cell(caslicenseNoeNo)="row">
        <b-form-input v-if="row.item.isEdit" type="text" 
        v-model="row.item.caslicenseNoeNo"></b-form-input>
        <span v-else>{{ row.item.caslicenseNoeNo }}</span>
      </template>
      <!-- 承辦日期 -->
      <template #cell(execDate)="row">
        <div v-if="row.item.isEdit">
          <i-form-input type="text"  
        v-model="row.item.execDateStart" 
        :rules="formInnerTableRules.nameRule"></i-form-input>
        ~<br/>
        <i-form-input type="text"
        v-model="row.item.execDateEnd" 
        :rules="formInnerTableRules.nameRule"></i-form-input>
        </div>
        <span v-else>{{ row.item.execDateStart }}<br/>~ <br/> 
        {{ row.item.execDateEnd }}</span>
      </template>

      <template v-if="isEditCompC" #cell(action)="row">
        <div>
          <!-- <b-button v-show="!row.item.isEdit" @click="editTeacherData(row)">
            <font-awesome-icon icon="edit"> </font-awesome-icon>
            {{ $t('button.modify') }}
          </b-button> -->
          <i-button size="sm" type="pencil-square" v-show="!row.item.isEdit" 
          @click="editCompData(row)"></i-button>
          <!-- <b-button v-show="!row.item.isEdit" @click="deleteCurrentData(row)">
            <font-awesome-icon icon="trash"> </font-awesome-icon>
            {{ $t('button.delete') }}
          </b-button> -->
          <i-button size="sm" type="trash" v-show="!row.item.isEdit" 
          @click="deleteCurrentData(row)"></i-button>
          <!-- <b-button v-show="row.item.isEdit" @click="saveTeacherData(row)">
            <font-awesome-icon icon="tag"> </font-awesome-icon>
            {{ $t('button.temporary') }}
          </b-button> -->
          <i-button size="sm" type="tag" v-show="row.item.isEdit" 
          @click="saveCompData(row)"></i-button>
          <!-- <b-button v-show="row.item.isEdit" @click="cancelAction(row)">
            <font-awesome-icon icon="undo-alt"> </font-awesome-icon>
            {{ $t('button.cancel') }}
          </b-button> -->
          <i-button size="sm" type="arrow-counterclockwise" v-show="row.item.isEdit" 
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
  name: 'Eng0301EditComp',
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
      agentName: '',
      compIdno: '',
      caslicenseNoeNo: '',
      execDate: '',
    };

    let newComp = ref([
      {
        agentName: '',
        compIdno: '',
        caslicenseNoeNo: '',
        execDate: '',
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
          tdClass: 'text-center align-middle ',
        },
        {  // 機關名稱
          key: 'agentName',
          label: i18n.t('label.agentName'),
          sortable: false,
          // thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
        },
        {  // 統編
          key: 'compIdno',
          label: i18n.t('label.compIdno'),
          sortable: false,
          // thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {  // 登記證字號
          key: 'caslicenseNoeNo',
          label: i18n.t('label.licenseNo'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
        {
          // 預定承辦日期
          key: 'execDate',
          label: i18n.t('label.execDate'),
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
        thStyle: 'width:14%',
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
        agentName: '',
        compIdno: '',
        caslicenseNoeNo: '',
        execDate: '',
        isEdit: true,
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
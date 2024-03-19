<template>
  <div>
    <b-table :items="compTable.compData" :fields="compTable.fields" 
    bordered responsive="sm">
      <!-- 項次 -->
      <template #cell(index)="data">
        {{ data.index + 1 }}
      </template>
      <!-- 審核時間 -->
      <template #cell(reviewTime)="row">
        <span>{{ row.item.reviewTime }}</span>
      </template>
      <!-- 審核狀態 -->
      <template #cell(reviewStatus)="row">
        <span>{{ row.item.reviewStatus }}</span>
      </template>
      <!-- 審核人員 -->
      <template #cell(reviewStaff)="row">
        <span>{{ row.item.reviewStaff }}</span>
      </template>
      <!-- 審核說明 -->
      <template #cell(reviewDesc)="row">
        <span>{{ row.item.reviewDesc }}</span>
      </template>
    </b-table>
  </div>
</template>

<script lang="ts">
import { Ref, ref, reactive, onMounted, computed, watch } from '@vue/composition-api';
import { required } from '@/shared/validators';
import { useBvModal } from '@/shared/modal';
import i18n from '@/shared/i18n';

export default {
  name: 'Eng0404EditEngr',
  props: {
    comps3: {
      required: false,
    },
    isEditComp: {
      type: Boolean,
      required: false,
      default: true,
    },
  },
  setup(props, context) {
    const compArrOrigin: Ref = computed(() => props.comps3);
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
      reviewTime: '',
      reviewStatus: '',
      reviewStaff: '',
      reviewDesc: '',
      // agentName: '',
      // compIdno: '',
      // caslicenseNoeNo: '',
      // execDate: '',
    };

    let newComp = ref([
      {
        reviewTime: '',
        reviewStatus: '',
        reviewStaff: '',
        reviewDesc: '',
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
        {  // 審核時間
          key: 'reviewTime',
          label: i18n.t('label.reviewTime'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-top ',
        },
        {  // 審核狀態
          key: 'reviewStatus',
          label: i18n.t('label.reviewStatus'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-top ',
        },
        {  // 審核人員
          key: 'reviewStaff',
          label: i18n.t('label.reviewStaff'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-top ',
        },
        {
          // 審核說明
          key: 'reviewDesc',
          label: i18n.t('label.reviewDesc'),
          sortable: true,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      compData: undefined,
    });
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
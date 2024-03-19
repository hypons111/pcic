<template>
  <div>
    <!-- 新增 -->
      <b-form-row>
        <i-button type="plus-circle" v-if="formStatusEF!=='檢視'
        && engrArr.length === 0"
        
         @click="addNewEngr()"></i-button>
      </b-form-row>
    <div v-for="(engr, index) in engrArr">
    <!-- 移除 -->
      <b-form-row>
        <b-col offset="10">
          <i-button type="trash"  
          v-if="formStatusEF!=='檢視'"
          
          @click="deleteCurrentData(engr, index)"></i-button>
        </b-col>
      </b-form-row>
      <b-form-row>
      <!-- 所屬廠商 -->
        <i-form-group-check labelStar
        :label="$t('label.manufacturer') + '：'"
        >
        <div v-if="formStatusEF==='檢視'">{{ engr.compName }}</div>
        <b-form-input v-if="formStatusEF==='編輯'" 
        v-model="engr.compName">
        </b-form-input>
        <b-form-input v-if="formStatusEF==='1'">
        </b-form-input>
        </i-form-group-check>
      </b-form-row>

      <b-form-row>
        <!-- 身分證字號 -->
        <i-form-group-check labelStar :label="$t('label.idno') + '：'" 
        >
          <div v-if="formStatusEF==='檢視'">
            {{ engr.idno }}
          </div>
          <b-form-input v-if="formStatusEF==='編輯'"
          v-model="engr.idno">
          </b-form-input>
          <b-form-input v-if="formStatusEF==='1'">
          </b-form-input>
        </i-form-group-check>
        <!-- 姓名 -->
        <i-form-group-check :label="$t('label.name') + '：'" 
        >
          <div v-if="formStatusEF==='檢視'">
            {{ engr.name }}
          </div>
          <b-form-input v-if="formStatusEF==='編輯'"
          v-model="engr.name">
          </b-form-input>
          <b-form-input v-if="formStatusEF==='1'">
          </b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <!-- 預定辦理日期 -->
        <i-form-group-check labelStar :label="$t('label.execDate') + '：'"
        >
        <div v-if="formStatusEF==='檢視'">
            {{ engr.execDate }}
          </div>
          <b-form-input v-if="formStatusEF==='編輯'"
          v-model="engr.execDate">
          </b-form-input>
          <b-form-input v-if="formStatusEF==='1'">
          </b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <!-- 證書 -->
        <i-form-group-check labelStar
        :label="$t('label.engrCertificate') + '：'">
          <div v-if="formStatusEF==='檢視'">
            {{ formOptionsFormatter(formOptions.engrCertificateList, 
            engr.engrCertificateList) }}
          </div>
          <b-form-checkbox-group
            v-if="formStatusEF==='編輯'"
            v-model="engr.engrCertificates"
            :options="formOptions.engrCertificateList"
          ></b-form-checkbox-group>
          <b-form-checkbox-group
            v-if="formStatusEF==='1'"
            :options="formOptions.engrCertificateList"
          ></b-form-checkbox-group>
        </i-form-group-check>
        <!-- 執照字號 -->
        <i-form-group-check :label="$t('label.engrLicenseDocNo') + '：'"
        >
          <div v-if="formStatusEF==='檢視'">
            {{ engr.engrLicenseDocNo }}
          </div>
          <b-form-input v-if="formStatusEF==='編輯'"
          v-model="engr.engrLicenseDocNo">
          </b-form-input>
          <b-form-input v-if="formStatusEF==='1'">
          </b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <!-- 簽證範圍 -->
        <i-form-group-check labelStar
        class="col-12" label-cols="2"
        :label="$t('label.recordRangeList') + '：'"
        >
          <div v-if="formStatusEF==='檢視'">
            {{ formOptionsFormatter(formOptions.recordRangeList, 
            engr.recordRangeList) }}
          </div>
          <b-form-checkbox-group
            v-if="formStatusEF==='編輯'"
            v-model="engr.recordRanges"
            :options="formOptions.recordRangeList"
          ></b-form-checkbox-group>
          <b-form-checkbox-group
            v-if="formStatusEF==='1'"
            :options="formOptions.recordRangeList"
          ></b-form-checkbox-group>
          <b-form-input class="col-4"
          v-if="formStatusEF!=='檢視'"
          trim></b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
      <!-- 簽證項目 -->
        <i-form-group-check labelStar
        class="col-12" label-cols="2"
        :label="$t('label.recordItems') + '：'"
        >
          <div v-if="formStatusEF==='檢視'">
            {{ formOptionsFormatter(formOptions.recordItemsList, 
            engr.recordItemsList) }}
          </div>
          <b-form-checkbox-group
            v-if="formStatusEF==='編輯'"
            v-model="engr.recordItems"
            :options="formOptions.recordItemsList"
          ></b-form-checkbox-group>
          <b-form-checkbox-group
            v-if="formStatusEF==='1'"
            :options="formOptions.recordItemsList"
          ></b-form-checkbox-group>
          <b-form-input class="col-4"
          v-if="formStatusEF!=='檢視'"
          trim></b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <!-- 核備文號 -->
        <i-form-group-check :label="$t('label.docNo') + '：'"
        >
          <div v-if="formStatusEF==='檢視'">
            {{ engr.docNo }}
          </div>
          <b-form-input v-if="formStatusEF==='編輯'"
          v-model="engr.docNo">
          </b-form-input>
          <b-form-input v-if="formStatusEF==='1'">
          </b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <!-- 結案日期 -->
        <i-form-group-check :label="$t('label.closeDate') + '：'"
        >
          <div v-if="formStatusEF==='檢視'">
            {{ engr.closeDate }}
          </div>
          <b-form-input v-if="formStatusEF==='編輯'"
          v-model="engr.closeDate">
          </b-form-input>
          <b-form-input v-if="formStatusEF==='1'">
          </b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <!-- 簽證日期 -->
        <i-form-group-check :label="$t('label.signStartDate') + '：'"
        >
          <div v-if="formStatusEF==='檢視'">
            {{ engr.signStartDate }}
          </div>
          <b-form-input v-if="formStatusEF==='編輯'"
          v-model="engr.signStartDate">
          </b-form-input>
          <b-form-input v-if="formStatusEF==='1'">
          </b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <!-- 備註 -->
        <i-form-group-check class="col-12" label-cols="2" content-cols="6" 
        :label="$t('label.remark') + '：'">
          <div v-if="formStatusEF==='檢視'">
          {{ engr.remark }}
          </div>
          <b-form-textarea rows="4" maxlength="80" trim lazy 
          v-model="engr.remark" v-if="formStatusEF==='編輯'"
          >
          </b-form-textarea>
          <b-form-textarea v-if="formStatusEF==='1'">
          </b-form-textarea>
        </i-form-group-check>
      </b-form-row>
      <!-- 新增 -->
      <b-form-row>
        <i-button type="plus-circle" v-if="formStatusEF!=='檢視'"
        
         @click="addNewEngr()"></i-button>
      </b-form-row>
    </div>

  </div>
</template>

<script lang="ts">
import { Ref, ref, reactive, onMounted, computed, watch } from '@vue/composition-api';
import { required } from '@/shared/validators';
import { useBvModal } from '@/shared/modal';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { FormStatusEnum, RoleEnum } from '@/shared/enum';

export default {
  name: 'Eng0301EditEngr',
  props: {
    engrs: {
      required: false,
    },
    isEditEngr: {
      type: Boolean,
      required: false,
      default: true,
    },
    formStatus: {

    }
  },
   setup(props, context) {
    const engrArrOrigin: Ref = computed(() => props.engrs);
    const admSysCodeService = new AdmSysCodeService();
    let formStatusEF = computed(() => props.formStatus);
    let isAdd = ref(false);
    const $bvModal = useBvModal();
    let engrArr = ref([]);
    Object.assign(engrArr.value, engrArrOrigin.value);

    const formOptions = reactive({
      recordItemsList: [], // 簽證項目
      recordRangeList: [], // 簽證範圍
      caseType: [],  // 案件類型
      projectType: [],  // 工程種類

      compName: [],  // 所屬廠商
      engrCertificateList: [], // 證書
    });

    onMounted(() => {
      getAllSysCodes();
    })

    function getAllSysCodes() {
      formOptions.caseType = [
          { value: '0', text: 'BOT案件' },
          { value: '1', text: '機關自辦' },
          { value: '2', text: '機關委辦' }
        ];
        formOptions.recordItemsList = [
          { value: '0', text: '設計' },
          { value: '1', text: '監造' },
          { value: '2', text: '施工' },
          { value: '99', text: '其他' }
        ];
        formOptions.recordRangeList = [
          { value: '0', text: '航空測量' },
          { value: '1', text: '工址調查' },
          { value: '2', text: '定線工程' },
          { value: '3', text: '機電工程' },
          { value: '4', text: '照明工程' },
          { value: '5', text: '道路工程、橋樑工程及隧道工程' },
          { value: '6', text: '水理分析、排水工程' },
          { value: '7', text: '交通工程' },
          { value: '99', text: '其它' },
        ];
        // formOptions.compName = [
        //   { value: '0', text: '建業工程顧問有限公司'},
        // ];
        formOptions.engrCertificateList = [
        { value: '0', text: '土木工程'},
        { value: '1', text: '結構工程'}
      ];
        admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    function addNewEngr() {
      let newEngr = {
        index: dataList.length,
        compNameList: '',
        compName: '', 
        idno: '', 
        name: '',
        execDate: '', 
        engrCertificate: [''], 
        engrLicenseDocNo: '', 
        recordRangeList: '', 
        recordRanges: [],
        recordItems: [], 
        recordItemsList: '',
        docNo: '', 
        closeDate: '', 
        signStartDate: '',
        remark: '',
        engrCertificateList: '',
        engrCertificates: [],
      };
      engrArr.value.push(newEngr);
      isAdd.value = !isAdd.value;
    }

    function deleteCurrentData(engr, index) {
        $bvModal.msgBoxConfirm('是否確認刪除此筆內容？', { centered: true }).then((isOK: boolean) => {
          if(isOK) {
            // engrArr.value = engrArr.value.splice(index, 1);
            engrArr.value = engrArr.value.filter((item, i) => i !== index);
            triggerEngrDataInParent(engr, 'delete');
            isAdd.value = !isAdd.value;
          }
        })
    }

    function triggerEngrDataInParent(engr: any, flag: string) {
      let reviseEngr = Object.assign({}, engr.item);
      delete reviseEngr['isEdit'];

      switch (flag) {
        case 'edit':
          context.emit('changeEngrData', reviseEngr, engr.index, 'edit');
          break;
        case 'add':
          context.emit('changeEngrData', reviseEngr, engr.index, 'add');
          break;
        case 'delete':
          context.emit('changeEngrData', reviseEngr, engr.index, 'delete');
          break;
        default:
          break;
      }
    }

    let dataList = reactive([
      {
        index: 1,  // 暫時的索引
        compNameList: '0',
        compName: '建業工程顧問有限公司', 
        idno: 'F125069458', 
        name: '王茲為',
        execDate: '106/10/26', 
        engrCertificate: [''], 
        engrLicenseDocNo: '技執字第007123號', 
        recordRangeList: '5', 
        recordRanges: ['5'],
        recordItems: ['1'], 
        recordItemsList: '1',
        docNo: '', 
        closeDate: '108/11/14', 
        signStartDate: '106/01/26',
        remark: '',
        engrCertificateList: '0',
        engrCertificates: ['0'],
      }
    ])

    return {
      dataList,
      formOptions,
      formOptionsFormatter,
      formStatusEF,
      FormStatusEnum,
      addNewEngr,
      deleteCurrentData,
      isAdd,
      engrArr
    }
   }
}

</script>
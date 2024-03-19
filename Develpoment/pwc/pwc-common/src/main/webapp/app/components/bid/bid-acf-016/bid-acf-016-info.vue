<template>
  <div>
    
    <div class="card">
      <div class="">
        
           <!-- @click="navigateByNameAndParams('bidAbd001Add',{})" -->
      <div class="mt-2">
       
        
      </div>
        <div class="text-center">設定執行異常之搜尋條件</div>
       <b-table-simple bordered>
        
      <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">可歸責廠商之停工件數較高者:</div>
        </b-td>
        
        <b-td>
          <b-form-row>
        <i-form-group-check :label="'較高之前 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> %
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>


       <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">可歸責廠商之解約件數較高者:</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="'較高之前 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> %
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>


       <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">職災件數比例較高者</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="'較高之前 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> %
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>


         <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">標比未達八成件數比例較高者:</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="'較高之前 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> %
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>



    <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">可歸責廠商之逾期完工達10天以上件數比例較高者:</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="'較高之前 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> %
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>

         <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black;">平均查核成績分數較低者:</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="'較低之前 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> %
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>


       <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black ">平均查核扣點數較多者:</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="'較低之前 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> %
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>

     <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">目前受政府採購法第101至103條停權處分中者:</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="'達 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> 件以上
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>


     <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">歷年受政府採購法第101至103條停權處分者:</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="'達 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> 件以上
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>


      

    </b-table-simple>


      <div class="text-center">設定承攬廠商之搜尋條件</div>
     <b-table-simple bordered>
        
      <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">施工中或最近2年內完工標案之承攬總件數:</div>
        </b-td>
        
        <b-td>
          <b-form-row>
        <i-form-group-check :label="'達'" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> 件以上
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>


       <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">施工中或最近2年內完工標案之承攬總金額(千元):</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="'達'" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> 千元以上
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>


       <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">歷年承攬標案被查核次數:</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="'未達 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> 次
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>


         <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">歷年承攬標案被查核件數比率:</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="'未達'" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input> %
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>



    


       


      

    </b-table-simple>


     <div class="text-center">設定工程標案之搜尋條件</div>
     <b-table-simple bordered>
        
      <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">標案所屬機關:</div>
        </b-td>
        
        <b-td>
          <b-form-row>
        <b-form-select :options="formOptions.company" class="col-4"></b-form-select>
      </b-form-row>
        </b-td>
      </b-tr>


       <b-tr>
        <b-td style="width:35%"> <div class="d-inline" style="color: black">採購金額:</div>
        </b-td>
        
        <b-td><b-form-row>
         <b-form-select :options="formOptions.status" class="col-4"></b-form-select>
      </b-form-row>
        </b-td>
      </b-tr>
 </b-table-simple>


     <div class="col-12 text-center">
          <b-button size="md" @click="navigateByNameAndParams('bidAcf016Edit',{})">查詢</b-button>
          <b-button size="md" @click="cancel">清除</b-button>
        </div>
      </div>
      
    </div>

     <b-modal
      title="執行成功"
      id="graph-add-modal"
      size="lg"
      header-bg-variant="secondary"
      header-text-variant="light"
      centered
    >
   <div class="text-center">標案已加註 設為子案</div>
        
      
     
      <template #modal-footer="{ cancel }">
        <div class="col-12 text-center">
          <b-button size="md" @click="submitForm">關閉</b-button>
          
        </div>
      </template>
    </b-modal>


    
    


    


  </div>
</template>

<script lang="ts">
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { reactive, Ref, toRef, watch } from '@vue/composition-api';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';


export default {
  name: 'bidAac007Edit',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
    formStatus: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const formStatusProp: Ref<any> = toRef(props, 'formStatus');
    const bidProjectService = new BidProjectService();

    const projectInfo = reactive({ wkut: '', prjno: '' });
    
    const $bvModal = useBvModal();
    /**
     * 撈出project的部分資料
     */
    const table = reactive({
      fields: [
        {
          key: 'checkMonth',
          label: '查證月份',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        
        {
          key: 'checkUnit',
          label: '查證單位',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          key: 'proofInfo',
          label: '佐證資料',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

         {
          key: 'sysValue1',
          label: '系統值',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },


        {
          key: 'checkValue1',
          label: '查證值',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },


         {
          key: 'sysValue2',
          label: '系統值',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          key: 'checkValue2',
          label: '查證值',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },


        {
          key: 'diffType',
          label: '差異原因及處理方式',
          sortable: false,
          thStyle: 'width:19%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
       
        {
          key: 'action',
          label: '動作',
          sortable: false,
          thStyle: 'width:12%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const handleQuery = () => {
      table.data = [];
      table.totalItems = 1;
      table.data.splice(0, table.data.length, ...mockdata);
    };

    const mockdata = [
      {
       checkMonth: '111/01',
       checkUnit: '內政部',
        proofInfo: '監造報表',
        sysValue1:'77.20%',
        checkValue1:'77.20%',
        sysValue2:'77.20%',
        checkValue2:'77.20%',
        diffType: '進度比對相符',
        
      },
       {
       checkMonth: '110/07',
       checkUnit: '內政部',
        proofInfo: '監造報表',
         sysValue1:'57.20%',
        checkValue1:'57.20%',
        sysValue2:'57.20%',
        checkValue2:'57.20%',
        diffType: '進度比對相符',
        
      },
       {
       checkMonth: '110/01',
       checkUnit: '內政部',
        proofInfo: '監造報表',
         sysValue1:'37.20%',
        checkValue1:'37.20%',
        sysValue2:'37.20%',
        checkValue2:'37.20%',
        diffType: '進度比對相符',
        
      },
      
      
      
      
    ];
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        // console.log('fetchBidProjectInfo res ', result);
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      wkut: '',
      prjno: '',
      name: '',
      major: '',
      id: '',
      class: '',
      class2: '',
      startDate: '',
      endDate: '',
      duty: '',
      createDate: '',
    };

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      name: {},
      major: {},
      id: {},
      class: {},
      class2: {},
      startDate: {},
      endDate: {},
      duty: {},
      createDate: {},
    };

    const formOptions = reactive({
      duty: [
        { value: '1', text: '專職人員' },
        { value: '2', text: '非專職人員' },
      ],
      company: [
        { value: '1', text: '請選擇' },
        { value: '2', text: '行政院本部' },
        { value: '3', text: '行政院' },
        { value: '4', text: '行政院(中央特別統籌分配稅款或其他)' },
      ],
      status: [
        { value: '1', text: '全部' },
        { value: '2', text: '巨額採購' },
        { value: '3', text: '查核金額以上未達巨額採購' },
        { value: '4', text: '一千萬元以上未達查核金額' },
        { value: '5', text: '公告金額以上未達一千萬元' },
       
    });
    function showAddModel(){
      $bvModal.show('graph-add-modal')
    }
    // const handleQuery = () => {};
    const submitForm = () => {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAac001Info', { bidProjectKey });
    };
    const toCheck = () => {};
    const toCancel = () => {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAac001Info', { bidProjectKey });
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const typeOptions = reactive({
      type: [
        { value: '1', text: '施工日誌' },
        { value: '2', text: '監造報表' },
        { value: '3', text: '進度管制表' },
        { value: '4', text: '估驗計價表' },
        { value: '5', text: '其他佐證表' },
      ],
    });


     const showGraphModifyModel = () => {
      
      $bvModal.show('graph-modify-modal');
    }

      const showGraphDeleteModel = () => {

        $bvModal.show('graph-delete-modal');
      }

    watch(
      bidProjectKeyProp,
      newValue => {
        // if (newValue !== null && newValue !== undefined) {
          // fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          // if (formStatusProp.value === 'modify') {
            handleQuery();
          // }
        // }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      $v,
      reset,
      bidProjectKeyProp,
      formStatusProp,
      formOptions,
      form,
      handleQuery,
      toCheck,
      submitForm,
      toCancel,
      table,
      showAddModel,
      showGraphModifyModel,
      showGraphDeleteModel,

      typeOptions,
      navigateByNameAndParams,
      
    };
  },
};
</script>

<style scoped></style>


<template>
  <div>
    <section>
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <div class="card">
      <div class="">
        
           <!-- @click="navigateByNameAndParams('bidAbd001Add',{})" -->
      <div class="mt-2">
       
        
      </div>

       <b-table-simple bordered>
        
      <b-tr>
        <b-td style="width:30%"> <div class="d-inline" style="color: black"><div class="text-center">日期</div></div>
        
        </b-td>
         <b-td style="width:40%"> <div class="d-inline" style="color: black"><div class="text-center">原因</div></div>
        
        </b-td>
        
       <b-td style="width:30%"> <div class="d-inline" style="color: black"><div class="text-center">後續處理</div></div>
        
       
        </b-td>
      </b-tr>
       <b-tr>
        <b-td style="width:30%"> <div class="d-inline" style="color: black">110/12/10</div>
        
        </b-td>
         <b-td style="width:30%"> <div class="d-inline" style="color: black">本工程自110/10/25申報竣工至今已逾月....</div>
        
        </b-td>
        
        <b-td>
            <b-button size="md" style="background-color: #1aa4b7" @click="navigateByNameAndParams('bidAaa006Host1',{})" >意見反應處理(主辦機關)</b-button>
            <b-button size="md" style="background-color: #1aa4b7" @click="navigateByNameAndParams('bidAaa006Mana1',{})">意見反應處理(主管機關)</b-button>
            <b-button size="md" style="background-color: #1aa4b7" @click="navigateByNameAndParams('bidAaa006Eng',{})">意見反應處理(工程會)</b-button>
        </b-td>
      </b-tr>

 <b-tr>
        <b-td style="width:30%"> <div class="d-inline" style="color: black">111/02/15</div>
        
        </b-td>
         <b-td style="width:30%"> <div class="d-inline" style="color: black">工程於111年1月27日完成驗收......機關仍未給付工程款</div>
        
        </b-td>
        
        <b-td>
         <b-button size="md" style="background-color: #1aa4b7" @click="navigateByNameAndParams('bidAaa006Host1',{})" >意見反應處理(主辦機關)</b-button>
         <b-button size="md" style="background-color: #1aa4b7" @click="navigateByNameAndParams('bidAaa006Mana1',{})">意見反應處理(主管機關)</b-button>
         <b-button size="md" style="background-color: #1aa4b7" @click="navigateByNameAndParams('bidAaa006Eng',{})">意見反應處理(工程會)</b-button>
        </b-td>
      </b-tr>


      

    </b-table-simple>

     
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
        { value: '2', text: '1:港洲營造公司' },
        { value: '3', text: '2:有力機電工程股份有限公司' },
        { value: '4', text: '3:致邦空調工程有限公司' },
      ],
      status: [
        { value: '1', text: '1:在職' },
        { value: '2', text: '2:中途離職' },
        { value: '3', text: '3:竣工解雇' },
        { value: '4', text: '4:查核丙等被撤換' },
        { value: '5', text: '5:查核缺失改善成效不佳被更換' },
        { value: '6', text: '6:未實際於工地執行工作被撤換' },
        { value: '7', text: '7:未能確實執行工作被撤換' },
        { value: '9', text: '9:專案註記' },
      ],
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


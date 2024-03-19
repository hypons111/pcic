<template>
  <div>
    <div class="text-center"><div class="d-inline" style="color: black;font-size:large">最近五年決標之承攬廠商排行榜(依件數)</div style="color:black">金額單位:仟元<div></div></div>
    <h5 class="text-center" >
               
               < 查詢工程類別 >(< 查詢發包預算起 > 起 至 < 查詢發包預算迄 >)
              </h5>
    <div class="card">
      
      <i-table

          class="table-sm"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :fields="table.fields"
          :totalItems="table.totalItems"
          :is-server-side-paging="true"
          @changePagination="handlePaginationChanged($event)"
        >

       

          <!--#cell為id,row代表這一列的資料-->
          <template #cell(action)>
           <i-button type="binoculars" @click="showGraphModifyModel"></i-button>
          </template>
          <template #cell(binoculars)>
           <i-button type="binoculars" @click="showGraphModifyModel"></i-button>
          </template>
      
        </i-table>
       
      
    </div>

     <b-modal
      title="主管機關進度查證資料"
      id="graph-add-modal"
      size="lg"
      header-bg-variant="secondary"
      header-text-variant="light"
      centered
    >
    <b-table-simple bordered>
        
      <b-tr>
        <b-td style="width:35%">進度月份</b-td>
        <b-td>111年02月<div class="d-inline" style="color: blue;font-size: small;">(本月份進度查證填報時間限於次一月12日起至次二月11日止)</div></b-td>
      </b-tr>
      <b-tr>
        <b-td>佐證資料</b-td>
        <b-td >
                <!-- <i-form-group-check>
                  <b-input-group>                  
                   <b-form-select :options="typeOptions" class=" form-group"> </b-form-select>
                  <b-form-input aria-label="Text input with checkbox"></b-form-input>
                </b-input-group>
                
              </i-form-group-check> -->
                <i-form-group-check>            
                   <b-form-select :options="typeOptions.type" class=" form-group"> </b-form-select>
             
                
              </i-form-group-check>
              <i-form-group-check class="col-12 form-group" label-cols="4" content-cols="8"  :label="'佐證資料自行管理編號:'">
              <b-form-input trim></b-form-input>
              </i-form-group-check>
              </b-td>
              
      </b-tr>
      <b-tr>
        <b-td>查證時<div class="d-inline" style="color: red">系統資料</div>預定進度</b-td>
        <b-td>76.04%</b-td>
      </b-tr>
    
      <b-tr>
            <b-td>查證時<div class="d-inline" style="color: red">佐證資料</div>預定進度</b-td>
             <b-td><b-form-input class="col-4"></b-form-input>%</b-td>
        </b-tr>

         <b-tr>
            <b-td>查證時<div class="d-inline" style="color: red">系統資料</div>實際進度</b-td>
            <b-td>76.08%</b-td>
        </b-tr>

         <b-tr>
            <b-td>查證時<div class="d-inline" style="color: red">系統資料</div>實際進度</b-td>
            <b-td><b-form-input class="col-4"></b-form-input>%</b-td>
        </b-tr>

         <b-tr>
            <b-td>差異原因及處理方式說明</b-td>
            <b-td><b-form-input size="lg"></b-form-input></b-td>
        </b-tr>


    </b-table-simple>
        
      
     
     
    </b-modal>


    <!-- 修改 -->
    <b-modal
      title="主管機關進度查證資料"
      id="graph-modify-modal"
      size="lg"
      header-bg-variant="secondary"
      header-text-variant="light"
      centered
    >

    <b-table-simple bordered>
        
      <b-tr>
        <b-td style="width:35%">進度月份</b-td>
        <b-td><div class="d-inline" style="color: blue">111年01月</div></b-td>
      </b-tr>

      <b-tr>
        <b-td style="width:35%">查證單位</b-td>
        <b-td><div class="d-inline" style="color: blue">內政部</div></b-td>     
      </b-tr>

      <b-tr>
        <b-td>佐證資料</b-td>
        <b-td >
                <div class="d-inline" style="color: blue">監造報表</div>
                <!-- <i-form-group-check>
                  <b-input-group>                  
                   <b-form-select :options="typeOptions" class=" form-group"> </b-form-select>
                  <b-form-input aria-label="Text input with checkbox"></b-form-input>
                </b-input-group>
                
              </i-form-group-check> -->
              <i-form-group-check class="col-15 form-group" label-cols="4" content-cols="8"  :label="'佐證資料自行管理編號:'">
              <b-form-input class="col-4"></b-form-input>
              </i-form-group-check>
            
              </b-td>
              
      </b-tr>
      <b-tr>
        <b-td>查證時<div class="d-inline" style="color: red">系統資料</div>預定進度</b-td>
        <b-td>76.04%</b-td>
      </b-tr>
    
      <b-tr>
            <b-td>查證時<div class="d-inline" style="color: red">佐證資料</div>預定進度</b-td>
            <b-td><b-form-input class="col-4"></b-form-input>%</b-td>
        </b-tr>

         <b-tr>
            <b-td>查證時<div class="d-inline" style="color: red">系統資料</div>實際進度</b-td>
            <b-td>76.08%</b-td>
        </b-tr>

         <b-tr>
            <b-td>查證時<div class="d-inline" style="color: red">系統資料</div>實際進度</b-td>
            <b-td><b-form-input class="col-4"></b-form-input>%</b-td>
        </b-tr>

         <b-tr>
            <b-td>差異原因及處理方式說明</b-td>
            <b-td><div class="d-inline" style="color: blue">(填寫...等。)</div><b-form-input size="lg"></b-form-input></b-td>
        </b-tr>


    </b-table-simple>
      
      <template #modal-footer="{ cancel }">
        <div class="col-12 text-center">
          <b-button size="md" @click="submitForm">儲存</b-button>
          <b-button size="md" @click="cancel">取消</b-button>
          <b-button size="md" @click="reset">清除</b-button>
        </div>
      </template>
    </b-modal>


    <b-modal
      id="graph-delete-modal"
      size="lg"
      title="主管機關查詢資料"
      header-bg-variant="secondary"
      header-text-variant="light"
      centered
    >
      <b-table-simple bordered>
        
      <b-tr>
        <b-td style="width:35%">進度月份</b-td>
        <b-td><div class="d-inline" style="color: blue">111年01月</div></b-td>
      </b-tr>

      <b-tr>
        <b-td style="width:35%">查證單位</b-td>
        <b-td><div class="d-inline" style="color: blue">內政部</div></b-td>     
      </b-tr>

      <b-tr>
        <b-td>佐證資料</b-td>
        <b-td >
                <div class="d-inline" style="color: blue">監造報表</div>
                <!-- <i-form-group-check>
                  <b-input-group>                  
                   <b-form-select :options="typeOptions" class=" form-group"> </b-form-select>
                  <b-form-input aria-label="Text input with checkbox"></b-form-input>
                </b-input-group>
                
              </i-form-group-check> -->
              <i-form-group-check class="col-15 form-group" label-cols="4" content-cols="8"  :label="'佐證資料自行管理編號:'">
              <b-form-input class="col-4"></b-form-input>
              </i-form-group-check>
            
              </b-td>
              
      </b-tr>
      <b-tr>
        <b-td>查證時<div class="d-inline" style="color: red">系統資料</div>預定進度</b-td>
        <b-td>76.04%</b-td>
      </b-tr>
    
      <b-tr>
            <b-td>查證時<div class="d-inline" style="color: red">佐證資料</div>預定進度</b-td>
            <b-td>76.04%</b-td>
        </b-tr>

         <b-tr>
            <b-td>查證時<div class="d-inline" style="color: red">系統資料</div>實際進度</b-td>
            <b-td>76.08%</b-td>
        </b-tr>

         <b-tr>
            <b-td>查證時<div class="d-inline" style="color: red">系統資料</div>實際進度</b-td>
             <b-td>76.08%</b-td>
        </b-tr>

         <b-tr>
            <b-td>差異原因及處理方式說明</b-td>
            <b-td><div class="d-inline" style="color: black">進度比對相符</div></b-td>
        </b-tr>


    </b-table-simple>

      <template #modal-footer="{ cancel }">
        <div class="col-12 text-center">
          <b-button size="md" @click="deletePrjGph">確認刪除</b-button>
          <b-button size="md" @click="cancel">取消</b-button>
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
          key: 'proofInfo',
          label: '工程名稱',
          sortable: false,
          thStyle: 'width:14%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'checkMonth',
          label: '主辦單位',
          sortable: false,
          thStyle: 'width:14%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        
        {
          key: 'checkUnit',
          label: '決標金額(千元)',
          sortable: false,
          thStyle: 'width:14%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'email',
          label: '決標日期',
          sortable: false,
          thStyle: 'width:14%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'add',
          label: '完工日期',
          sortable: false,
          thStyle: 'width:14%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'fax',
          label: '查核日期',
          sortable: false,
          thStyle: 'width:14%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'checkDate',
          label: '分數',
          sortable: false,
          thStyle: 'width:14%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
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
       proofInfo: 'XXX',
       checkMonth:'OO鄉公所',
       checkUnit: '357',
       email:'107年11月30日',
       add:'109年07月25日',
       fax:'109年05月19日',
       checkDate:'78',
       

       
      
        
      },
       {
       proofInfo: '',
       checkM: '8',
       checkUnit: '',
       
      
        
      },
       {
       proofInfo: '',
       checkM: '7',
       checkUnit: '',
       
      
        
      },
       {
       proofInfo: '',
       checkM: '6',
       checkUnit: '',
       
      
        
      },
    
    
     
       
      
 
      
      
      
      
      
    ];
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        // console.log('fetchBidProjectInfo res ', result);
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }
    var checkM = '0';
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


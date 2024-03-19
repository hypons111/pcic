<template>
  <div>
    <div class="text-center"><div class="d-inline" style="color: blue;font-size:larger">公共工程標案地理資訊系統: 公共工程執行異常廠商承攬施工中標案一覽表</div></div>
    <div class="text-center"><div class="d-inline" style="color: black">全國標案執行異常廠商承攬[行政院]所屬公共工程標案一覽表(最多顯示2000項)</div></div>
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

         <template #thead-top>
            <td colspan="9"></td>
            <th colspan="4" style="text-align: center">施工進度</th>
            <th colspan="2" style="text-align: center">工程地點</th>
            <td></td>
          </template>

          <!--#cell為id,row代表這一列的資料-->
          <template #cell(action)>
           <i-button type="binoculars" @click="showGraphModifyModel"></i-button>
          </template>
          <template #cell(binoculars)>
           <i-button type="binoculars" @click="showGraphModifyModel"></i-button>
          </template>
        </i-table>
        <div>
        <b-col style="text-align: left">
          <b-button >返回上頁</b-button>
          
        </b-col>
      </div>
      <div>
        <b-col style="text-align: left">
          <b-button >返回公共工程標案地理資訊選項網頁</b-button>
          
        </b-col>
      </div>
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
        
      
     
      <template #modal-footer="{ cancel }">
        <div class="col-12 text-center">
          <b-button size="md" @click="submitForm">儲存</b-button>
          <b-button size="md" @click="cancel">取消</b-button>
          <b-button size="md" @click="reset">清除</b-button>
        </div>
      </template>
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
          key: 'prjno',
          label: '工程標案編號',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        
        {
          key: 'name',
          label: '工程標案名稱',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          key: 'ccut1Name',
          label: '承攬廠商',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },



        {
          key: 'wkut',
          label: '主辦機關',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },



        {
          key: 'bdgt1',
          label: '預算金額',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },


        {
          key: 'ctSum',
          label: '決標金額',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },


        {
          key: 'actDate',
          label: '決標日期',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },


        {
          key: 'ceDate',
          label: '預定完工日期',
          sortable: false,
          thStyle: 'width:8%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },


        {
          key: 'ptrate',
          label: '預定(%)',
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },


        {
          key: 'atrate',
          label: '實際(%)',
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },



        {
          key: 'atrateMinusPtrate:',
          label: '差異(%)',
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },


         {
          key: 'c_syrmnth',
          label: '年月',
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },



         {
          key: 'sysValue1',
          label: '',
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },


        {
          key: 'checkValue1',
          label: '',
          sortable: false,
          thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },


        

        


    
       
        {
          key: 'action',
          label: '查詢標案資訊',
          sortable: false,
          thStyle: 'width:12%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

         {
          key: 'binoculars',
          label: '檢視位置詳圖',
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
       prjno: '',
       name: '',
       ccut1_name: '',
        wkut:'',
        bdgt1:'',
        ctSum:'',
        actDate:'',
        ceDate: '',
        ptrate:'',
        atrate:'',
        atrateMinusPtrate:'',//c_atrate- c_ptrate
        c_syrmnth:'',
        action:'',
        binoculars:'',

        
      },
      
      
      
      
      
    ];
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        // console.log('fetchBidProjectInfo res ', result);
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

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
      bidProjectKeyProp,
      formStatusProp,
      formOptions,
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


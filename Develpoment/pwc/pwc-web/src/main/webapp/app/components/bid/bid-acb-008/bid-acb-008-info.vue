<template>
  <div>
    
    <div class="card">
      <div class="">
        
          
      <div class="mt-2">
       
        
      </div>
         <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                工程類別分類排行查詢
              </h5>
       <b-table-simple bordered>
        
      <b-tr>
        <b-td style="width:35%"> <div class="text-right" style="color: black" >工程類別</div>
        </b-td>
        
        <b-td>
                 
                   <b-form-select :options="typeOptions.type" class=" form-group"> </b-form-select>
             
                
              
        </b-td>
      </b-tr>


       <b-tr>
         <b-td style="width:35%"> <div class="text-right" style="color: black" >發包預算</div>
        </b-td>
        
        <b-td><b-form-row>
       自 <b-form-input class="col-2" ></b-form-input>萬元至<b-form-input class="col-2" ></b-form-input>萬元止
      </b-form-row>
        </b-td>
      </b-tr>


       <b-tr>
       <b-td style="width:35%"> <div class="text-right" style="color: black" >施工地區<br>(可複選)</div>
        </b-td>
        
        <b-td><b-form-row>
              <!-- 縣市(多選) -->
              <i-form-group-check :label="$t('label.County')+':'">
                <i-group-select-checkbox
                    :options="formOptions.County"
                    :isDropDown="true"
                    >
                </i-group-select-checkbox>
              </i-form-group-check>
            </b-form-row>
        </b-td>
      </b-tr>


         <b-tr>
        <b-td style="width:35%"> <div class="text-right" style="color: black" >查核情形</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="' 曾接受查核評比為 '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
            <b-form-checkbox-group>
                <b-form-checkbox >優等</b-form-checkbox>
                <b-form-checkbox >甲等以上</b-form-checkbox>
                <b-form-checkbox >乙等以上</b-form-checkbox>
                <b-form-checkbox >丙等</b-form-checkbox>
                <b-form-checkbox >所有標案</b-form-checkbox>
            </b-form-checkbox-group>
         
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>



    <b-tr>
        <b-td style="width:35%"> <div class="text-right" style="color: black" >金質獎情形</div>
        </b-td>
        
        <b-td><b-form-row>
        <i-form-group-check :label="'曾參與受評比為'" :label-cols="2" :content-cols="8" class="col-12" :labelStar="false">
            <b-form-checkbox-group>
                <b-form-checkbox >特優</b-form-checkbox>
                <b-form-checkbox >優等以上</b-form-checkbox>
                <b-form-checkbox >入圍以上</b-form-checkbox>
                <b-form-checkbox >所有標案</b-form-checkbox>
            </b-form-checkbox-group>
         
        </i-form-group-check>
      </b-form-row>
        </b-td>
      </b-tr>

         <b-tr>
        <b-td style="width:35%"> <div class="text-right" style="color: black" >重大職災發生</div>
        </b-td>
        
        <b-td><b-form-row>
    
          <b-form-radio-group :options="formOptions.duty"> </b-form-radio-group>
       
      </b-form-row>
        </b-td>
      </b-tr>


      
     


    


      

    </b-table-simple>


     


    


     <div class="col-12 text-center">
          <b-button size="md" @click="navigateByNameAndParams('bidAcb008Query',{})">查詢</b-button>
          <b-button size="md" @click="cancel">取消</b-button>
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
        { value: '1', text: '有' },
        { value: '2', text: '所有標案' },
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
        ],
        County: [
        {
          parents:'-----北部地區',
          child:[
            { value: '0', text: '基隆市' },
            { value: '1', text: '臺北市' },
            { value: '3', text: '新北市' },
            { value: '4', text: '桃園市' },
            { value: '5', text: '新竹市' },
            { value: '6', text: '新竹縣' },
            { value: '7', text: '宜蘭縣' }
            ],
        },
        {
          parents:'-----中部地區',
          child:[  
            { value: '8', text: '苗栗縣' },
            { value: '9', text: '臺中市' },
            { value: '10', text: '彰化縣' },
            { value: '11', text: '南投縣' },
            { value: '12', text: '雲林縣' },
            ],
        },
        {
          parents:'-----南部地區',
          child:[
            { value: '13', text: '嘉義市' },
            { value: '14', text: '嘉義縣' },
            { value: '15', text: '臺南市' },
            { value: '16', text: '高雄市' },
            { value: '17', text: '屏東縣' },
            { value: '18', text: '澎湖縣' },
            { value: '19', text: '金門縣' },
            { value: '20', text: '連江縣' },
            ],
        },
        {
          parents:'-----東部地區',
          child:[
            { value: '21', text: '臺東縣' },
            { value: '22', text: '花蓮縣' },   
            ]
        },
        {
          parents:'-----外島地區',
          child:[
            { value: '23', text: '澎湖縣' },
            { value: '24', text: '金門縣' },
            { value: '25', text: '連江縣' },     
            ]
        },
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
        { value: '1', text: '建築-建築建造工程' },
        
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


<template>
  <div>
    

               <section class="container">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                廠商承攬公共工程履歷查詢
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <b-form-group class="col-sm-5" label-cols-md="3" content-cols-md="9" label="廠商名稱關鍵字" label-align-md="right">
                <b-form-input ></b-form-input>
              </b-form-group>
             
              
              <b-col cols="2">
                <i-button type="search" @click="navigateByNameAndParams('bidAcb008Info',{})"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>
    <section>
      
    </section>
    <div class="card">
      
       
      

      

     
      </div>
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
            <b-button class="btn-sm" style="background-color: #1aa4b7">公共工程履歷資料</b-button>
          </template>
        </i-table>
    </div>
  </div>
</template>

<script lang="ts">
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { reactive, Ref, toRef, watch } from '@vue/composition-api';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';

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

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });

    /**
     * 撈出project的部分資料
     */
    const table = reactive({
      fields: [
        {
          key: 'caseName',
          label: '廠商名稱',
          sortable: false,
          thStyle: 'width:50%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
       
        
        {
          key: 'action',
          label: '動作',
          sortable: false,
          thStyle: 'width:50%',
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
       caseName: 'XXX',
        prjno: '110-3469174',
        closingDate: '',
        
       
      },
      {
       caseName: 'XXX',
        prjno: '110-1565853',
        closingDate: '',
        
       
      },
      {
       caseName: 'XXX',
        prjno: '109-3526575',
        closingDate: '110/05/28',
        
       
      },
      {
       caseName: 'XXX',
        prjno: '108-2586746',
        closingDate: '110/11/15',
        
       
      },
      {
       caseName: 'XXX',
        prjno: '108-897-5468',
        closingDate: '',
        
       
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

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      step2: false,
    });


    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      prjno: '', // 標案編號
      prjName: '', // 標案名稱關鍵字
    };

    // 表單物件驗證規則
    const rules = {
      prjno: {}, // 標案編號
      prjName: {}, // 標案名稱關鍵字
    };


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
      navigateByNameAndParams,
      stepVisible,
    };
  },
};
</script>

<style scoped></style>


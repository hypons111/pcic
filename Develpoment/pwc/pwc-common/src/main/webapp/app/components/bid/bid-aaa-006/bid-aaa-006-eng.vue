<template>
  <div>
    <section>
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <div class="card">
        <div class="card-header">
            <h5 class="m-0">延遲付款通報資料</h5>
        </div>
        <b-form-row>
        <i-form-group-check :label="'通報日期  '" :label-cols="2" :content-cols="10" class="col-6">111年xx月xx日</i-form-group-check>
        <i-form-group-check :label="'通報人    '" :label-cols="2" :content-cols="10" class="col-6">XXX</i-form-group-check>
      </b-form-row>

       <b-form-row>
        <i-form-group-check :label="'承攬廠商  '" :label-cols="2" :content-cols="10" class="col-6">XXX工程有限公司</i-form-group-check>
        <i-form-group-check :label="'聯絡電話   '" :label-cols="2" :content-cols="10" class="col-6">02-1234-5678</i-form-group-check>
      </b-form-row>

       <b-form-row>
        <i-form-group-check :label="'延遲天數  '" :label-cols="2" :content-cols="10" class="col-6">60</i-form-group-check>
        <i-form-group-check :label="'電子信箱   '" :label-cols="2" :content-cols="10" class="col-6">123@123.com</i-form-group-check>
      </b-form-row>

       <b-form-row>
        <i-form-group-check :label="'延遲金額  '" :label-cols="2" :content-cols="10" class="col-6">XXX千元</i-form-group-check>
      </b-form-row>

      <b-form-row>
        <i-form-group-check :label="'延遲案情說明  '" :label-cols="2" :content-cols="8" class="col-8">XXX、XXXXX</i-form-group-check>
      </b-form-row>
    </div>
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">主辦機關處理說明</h5>
      </div>
      <b-form-row>
        <i-form-group-check :label="'處理說明 '" :label-cols="2" :content-cols="10" class="col-12">
        XXX</i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'是否屬已進入調解仲裁或訴訟案件 '" :label-cols="4" :content-cols="6" class="col-6" :labelStar="false">
         否
        </i-form-group-check>
        <i-form-group-check :label="'遲延原因'" :label-cols="2" :content-cols="10" class="col-6" :labelStar="false">
          尚在辦理請款作業
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
         <i-form-group-check :label="'機關實際延遲天數  '" :label-cols="4" :content-cols="8" class="col-6" :labelStar="false">
          60天
        
        </i-form-group-check>
        <i-form-group-check :label="'機關實際延遲金額   '" :label-cols="4" :content-cols="8" class="col-6" :labelStar="false">
          XXX千元
          <div style="color:red">(如屬已進入調解仲裁或訴訟案件時，本欄應填報與爭議無關可再支付之金額)</div>
        </i-form-group-check>
      </b-form-row>
      
      <b-form-row>
         <i-form-group-check :label="'預估可驗收日期 '" :labelStar="false">
          XXX年XX月XX日
        </i-form-group-check>
         <i-form-group-check :label="'預估可付款日期 '" :labelStar="false">
          XXX年XX月XX日
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'部分付款累計金額   '" :label-cols="4" :content-cols="8" class="col-6" :labelStar="false">
          XXX千元
          <div style="color:red">(自廠商通報日起，就通報金額部分已給付廠商之累計金額，如無部份付款本欄可免填)</div>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'部分付款歷次給付日期及金額說明 '" :label-cols="2" :content-cols="10" class="col-12">
        XXX<div style="color:red">(應與標案系統「付款狀況(A8)」欄位勾稽)</div></i-form-group-check>
        
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'實際付款(處理)日期 '" :labelStar="false">
          XXX年XX月XX日
          <div style="color:red">(已全部支付廠商通報延遲金額之實際日期，本欄填報前請先至A8將該日付款資料完成登錄)</div>
        </i-form-group-check>
      </b-form-row>
        <b-form-row>
           <i-form-group-check :label="'處理紀錄 '" :label-cols="2" :content-cols="10" class="col-7" :labelStar="false">
           <i-form-group-check >
            <a href="http://localhost:9000/pwc-web/bidAaa006/host2"><u>XXX年XX月XX日</u></a>
           </i-form-group-check>
           <i-form-group-check>
            <a href="http://localhost:9000/pwc-web/bidAaa006/host2"><u>XXX年XX月XX日</u></a>
           </i-form-group-check>
           <i-form-group-check>
            <a href="http://localhost:9000/pwc-web/bidAaa006/host2"><u>XXX年XX月XX日</u></a>
           </i-form-group-check>
           <i-form-group-check>
            <a href="http://localhost:9000/pwc-web/bidAaa006/host2"><u>XXX年XX月XX日</u></a>
           </i-form-group-check>
        </i-form-group-check>
       
            
        </b-form-row>

         <div class="card">
        <div class="card-header">
            <h5 class="m-0">主管部會署或直轄市、縣市政府填報說明</h5>
        </div>
        <b-form-row>
        <i-form-group-check :label="'工程主管機關處理說明  '" :label-cols="4" :content-cols="8" class="col-6">XXX</i-form-group-check>
        
        <i-form-group-check :label="'確認預估可付款日期   '" :label-cols="3" :content-cols="9" class="col-6">
            請工程會調整預估可付款日期為XXX年XX月XX日
        </i-form-group-check>
      </b-form-row>

       <b-form-row>
        <i-form-group-check :label="'結案日期  '" :label-cols="2" :content-cols="10" class="col-6">
            XXX年XX月XX日
        </i-form-group-check>
        <i-form-group-check :label="'結案原因   '" :label-cols="2" :content-cols="10" class="col-6">
            主辦機關已完成付款
        </i-form-group-check>
      </b-form-row>

       <b-form-row>
           <i-form-group-check :label="'處理紀錄 '" :label-cols="2" :content-cols="10" class="col-7" :labelStar="false">
           <i-form-group-check >
            <a href="http://localhost:9000/pwc-web/bidAaa006/mana2"><u>XXX年XX月XX日</u></a>
           </i-form-group-check>
           <i-form-group-check>
            <a href="http://localhost:9000/pwc-web/bidAaa006/mana2"><u>XXX年XX月XX日</u></a>
           </i-form-group-check>
           <i-form-group-check>
            <a href="http://localhost:9000/pwc-web/bidAaa006/mana2"><u>XXX年XX月XX日</u></a>
           </i-form-group-check>
           <i-form-group-check>
            <a href="http://localhost:9000/pwc-web/bidAaa006/mana2"><u>XXX年XX月XX日</u></a>
           </i-form-group-check>
        </i-form-group-check>
       
            
        </b-form-row>

    

      
    </div>

     <div class="card">
        <div class="card-header">
            <h5 class="m-0">工程會註記</h5>
        </div>
        <b-form-row>
        <i-form-group-check :label="'工程會註記  '" :label-cols="4" :content-cols="8" class="col-6"><b-form-textarea class="col-6"></b-form-textarea></i-form-group-check>
        
        <i-form-group-check :label="'工程會管制狀況   '" :label-cols="3" :content-cols="9" class="col-6">
            <b-form-radio-group :options="formOptions.duty"> </b-form-radio-group>
        </i-form-group-check>
      </b-form-row>
    

    <h5 class="text-center">承攬廠商其他通報案件</h5>
     <b-table-simple bordered>
        
      <b-tr>
        <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">通報日期</div></div>
        
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">延遲天數</div></div>
        
        </b-td>
        
       <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">請款單據送達機關</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">延遲金額(千元)</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">延遲案情說明</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">主辦機關說明</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">預估付款日期</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">實際付款日期</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">主管機關說明</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">結案日期</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">結案原因</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">併案</div></div>
        
       
        </b-td>
      </b-tr>

      <b-tr>
        <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">110年10月02日</div></div>
        
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">15</div></div>
        
        </b-td>
        
       <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">110年11月12日</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">900</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">XXX</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"><b-form-checkbox></b-form-checkbox></div></div>
        
       
        </b-td>
      </b-tr>

      <b-tr>
        <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">110年10月03日</div></div>
        
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">15</div></div>
        
        </b-td>
        
       <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">110年11月12日</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">900</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">XXX</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"><b-form-checkbox></b-form-checkbox></div></div>
        
       
        </b-td>
      </b-tr>

       <b-tr>
        <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">111年02月10日</div></div>
        
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">10</div></div>
        
        </b-td>
        
       <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">111年01月20日</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">1500</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">XXX</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"><b-form-checkbox></b-form-checkbox></div></div>
        
       
        </b-td>
      </b-tr>

      <b-tr>
        <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">111年02月25日</div></div>
        
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">20</div></div>
        
        </b-td>
        
       <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">111年01月20日</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">1497</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">XXX</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"><b-form-checkbox></b-form-checkbox></div></div>
        
       
        </b-td>
      </b-tr>


      <b-tr>
        <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">111年04月05日</div></div>
        
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">20</div></div>
        
        </b-td>
        
       <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">111年03月13日</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">500</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center">XXX</div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"></div></div>
        
       
        </b-td>
         <b-td style="width:8%"> <div class="d-inline" style="color: black"><div class="text-center"><b-form-checkbox></b-form-checkbox></div></div>
        
       
        </b-td>
      </b-tr>


      

    </b-table-simple>
      

      
    

      
    </div>


      <div>
        <b-col style="text-align: center">
          <b-button>儲存</b-button>
          <b-button>取消</b-button>
          <b-button>清除</b-button>
        </b-col>
      </div>
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
  name: 'bidAac001Edit',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
    formStatus: {
      type: Object,
      required: true,
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
        { value: '1', text: '確認鎖定' },
        { value: '2', text: '解除鎖定' },
        { value: '3', text: '依申請調整預定付款日期' },
      ],
      company: [
        { value: '1', text: '主辦機關已完成付款' },
        { value: '2', text: '屬爭議處理事件，已進行調解、仲裁或訴訟，目前已' },
        { value: '3', text: '廠商申請撤案' },
        { value: '4', text: '通報內容屬履約爭議事項' },
        { value: '5', text: '契約約定應辦事項尚未完成' },
        { value: '6', text: '通報事項非屬列管範疇' },
      ],
      status: [
        { value: '1', text: '尚在辦理請款作業' },
        { value: '2', text: '上級機關辦理補助款核撥中' },
        { value: '3', text: '上級機關未核撥補助款' },
        { value: '4', text: '洽辦機關未核撥補助款' },
        { value: '5', text: '預算暫被挪為他用' },
        { value: '6', text: '無預算或預算不足' },
        { value: '7', text: '廠商文件未備齊或錯誤需修正' },
        { value: '8', text: '尚有履約爭議事項待解決' },
        { value: '9', text: '有契約規定暫停給付估驗計價款' },
        { value: '10', text: '估驗或驗收不合格' },
        { value: '11', text: '契約規定應辦事項尚未完成' },
        { value: '12', text: '其他' },
      ],
    });

    const handleQuery = () => {};
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

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          if (formStatusProp.value === 'modify') {
            handleQuery();
          }
        }
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
    };
  },
};
</script>

<style scoped></style>

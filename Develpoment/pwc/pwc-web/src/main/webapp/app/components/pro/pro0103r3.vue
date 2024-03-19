<template>
  <div>
      <section class ="container mt-2">
      <div class="card">
        <b-row >
        <b-col cols="2" class="text-right label pr-1" >
               <div class="pt-2">受理狀態</div> 
            </b-col>
        <b-col cols="10">
          
          <b-form-select v-model="acceptStatus" :options="type" @change="change()">
          </b-form-select>
         </b-col>
        </b-row>
        <b-form-row >
         <i-form-group-check :label="$t('label.innovationProductCommunicateId')">
          <div class="pl-3 pt-2 " > 20222010101</div>
          </i-form-group-check>      
        </b-form-row>

        <b-row class="ml-0 mb-2 align-items-start">
        <b-col cols="2" class="text-right label">
              <div class="pt-2">申請廠商</div>
            </b-col>
             <b-col cols="10">
             <b-row>
            
             <b-form-group
              class="col-6"
              :label="$t('label.companyName')"
              content-cols="8"
              label-cols="4"
              label-class="pl-1 b-form-group-label"
              >
              <div class="pl-1 pt-2">大XX股份有限公司</div>
             </b-form-group>

             <b-form-group
                  class="col-6"
                  :label="$t('label.uniformNumber')" 
                  content-cols="8"
                  label-cols="4"
                  label-class="pl-1 b-form-group-label"
                 
                 >
                  <div class="pl-1 pt-2" >00000000</div>
                 </b-form-group>
             </b-row>
             <b-row>
             <b-form-group
                  class="col-6"
                  :label="$t('label.departmentAddr')" 
                  label-class="pl-1 b-form-group-label"
                  content-cols="8"
                  label-cols="4"
                 >
                  <div class="pl-1 pt-2" >台北市信義區松仁路3號</div>
                 </b-form-group>
             </b-row>
             <b-row>
                 <b-form-group
                  class="col-6"
                  :label="$t('label.contactName')" 
                  label-class="pl-1 b-form-group-label"
                  content-cols="8"
                  label-cols="4"
                 >
                  <div class="pl-1 pt-2">林xx</div>
                 </b-form-group>
                 <b-form-group
                  class="col-6"
                  :label="$t('label.jobTitle')" 
                  label-class="pl-1 b-form-group-label"
                  content-cols="8"
                  label-cols="4"
                 >
                  <div class="pl-1 pt-2" >經理</div>
                 </b-form-group>

               </b-row>
               <b-row>
                 <b-form-group
                  class="col-6"
                  :label="$t('label.departmentTel')" 
                  label-class="pl-1  b-form-group-label"
                  content-cols="8"
                  label-cols="4"
                 >
                  <div class="pl-1 pt-2">(03)1234-5678</div>
                 </b-form-group>

                 <b-form-group
                  class="col-6 "
                  :label="$t('label.email')" 
                  label-class="pl-1 b-form-group-label"
                  content-cols="8"
                  label-cols="4"
                 >
                  <div class="pl-1 pt-2">pcctest@pcc.gov.tw</div>
                 </b-form-group>

               </b-row>
            </b-col>
        </b-row>
         
        <b-row class="ml-0 mb-2 align-items-start">
        <b-col cols="2" class="text-right label">
          <div>機關意見</div>
        </b-col>
          <b-col cols="10"> 
                 <b-form-group
                  :label="$t('label.communicateMode')" 
                  label-class="pl-1 b-form-group-label"
                 >
                <b-form-select :options="communicateType"></b-form-select>
                </b-form-group>
                
                 <i-form-group-check 
                 :label="$t('label.eng.date_Communicate')" 
                 :dual1="$v.date_Start" 
                 :dual2="$v.date_End"
                  class="pl-1 b-form-group-label col-12 "
                 label-align-md="left"
                  label-cols="12"
                  content-cols="8" 
                 >
                <b-input-group label-cols="12">
                  <i-date-picker v-model="$v.date_Start.$model" :state="$v.date_Start" placeholder="yyy/MM/dd"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.date_End.$model" :state="$v.date_End" placeholder="yyy/MM/dd"> </i-date-picker>
                </b-input-group>
                </i-form-group-check>
              
              <b-form-group
                  :label="$t('label.communicatePlace')" 
                  label-class="pl-1  b-form-group-label "
                  class="mt-4"
                 >
               <b-form-input value="台北市信義區松仁路3號"></b-form-input>
                </b-form-group>
       
               <b-form-group
                  :label="$t('label.participant')" 
                  label-class="pl-1  b-form-group-label "
                  
                 >
                 
                 <template v-for="tempData in arryaData">
                    <b-row :key="tempData.id" class>
                      <b-col class="col-sm-3">
                        <b-form-checkbox v-model="tempData.checkDisable" :value="tempData.checkValue" class="mt-3">
                        <h6 >{{ tempData.checkName }}</h6></b-form-checkbox>
                      </b-col>
                      <b-col class="col-sm-3"><b-form-input type="text" class="mt-2":disabled="!tempData.checkDisable" placeholder="Enter your Name"/></b-col>
                      <b-col class="col-sm-2 mt-3" ><h6 >職稱:</h6></b-col>
                      <b-col class="col-sm-3 "><b-form-input type="text" class="mt-2":disabled="!tempData.checkDisable" placeholder="Enter your jobtitle"/></b-col>
                    </b-row>
                  </template> 

                 </b-form-group>

                 <b-form-group
                  :label="$t('label.communicateSummary')" 
                  label-class="pl-1  b-form-group-label ">
                  <b-form-textarea></b-form-textarea>
                   </b-form-group>

                   <b-form-group
                  :label="$t('label.communicateOpinion')" 
                  label-class="pl-1  b-form-group-label ">
                  <b-form-textarea></b-form-textarea>
                   </b-form-group>

                   <b-form-group
                  :label="$t('label.others')" 
                  label-class="pl-1  b-form-group-label ">
                  <b-form-textarea></b-form-textarea>
                   </b-form-group>

                   <b-form-group v-show="cond1"
                  :label="$t('label.rejectCommunicateReason')" 
                  label-class="pl-1  b-form-group-label "
                 >
                 </b-form-group>
                 <template v-for="reason in reasonData" >
              <b-row :key="reason.id"v-show="cond1">
                <b-col class="col-sm row justify-content-start">
                  &emsp;&emsp;<b-form-checkbox v-model="checkReason" :value="reason.checkValue" :disabled="reason.checkDisable">
                  <h6>{{ reason.checkName }}</h6></b-form-checkbox>
                </b-col>
              </b-row>
            </template>

            <b-form-group v-show="cond1"
                  :label="$t('label.rejectCommunicateContent')" 
                  label-class="pl-1  b-form-group-label "
                 >
                  <b-form-textarea></b-form-textarea>
                 </b-form-group>
           
          </b-col>
            
        </b-row> 
        <b-row class="ml-0 mb-2 align-items-start">
        <b-col cols="2" class="text-right label">
          <div>編輯狀態</div>
        </b-col>
        <b-col cols="10">
         
        </b-col> 
        </b-row>

     
         

       
       
     

        

        
        </div>
      </b-container>
      <br>
      
        <b-row class="col-sm row justify-content-end">
          <i-button size="sm" type="open" text-align:center></i-button>&ensp;
          <i-button size="sm" type="remove"></i-button>&ensp;
          <i-button size="sm" type="save"></i-button>&ensp;
          <i-button size="sm" type="printer"></i-button>&ensp;
          <i-button size="sm" type="x-circle"></i-button>&ensp;
          <i-button size="sm" type="arrow-counterclockwise"></i-button>
        </b-row>
     
     
      <br>
      
    </section>
  </div>
</template>


<script>

import { reactive } from '@vue/composition-api';
import DatePicker from 'vue2-datepicker';
import formatDate from '@/shared/date/minguo-calendar-utils';
import DualFormInvalidFeedback from '@/shared/form/dual-form-invalid-feedback';
import { useValidation, validateState } from '@/shared/form';

export default {
  data(){
    return{
      comminicateTime: '',
      type: [
        { value: 'N', text: '不受理交流'},
        { value: 'Y', text: '受理交流' },
      ],
    patent: 'Y',
    arryaData: [
      { id: '1',  checkName:  '機關人員', checkValue:  '1', name: '', tel:  '02-1234-5678', file: 'A.pdf',  checkDisable: false},
      { id: '2',  checkName:  '主計人員', checkValue:  '2', name: '', tel:  '0988-988-988', file: 'B.pdf',  checkDisable: false},
      { id: '3',  checkName:  '政風人員', checkValue:  '3', name: '', tel:  '02-0000-1234', file: 'C.pdf',  checkDisable: false},
      { id: '4',  checkName:  '上級機關人員', checkValue:  '4', name: '', tel:  '02-1234-5678', file: 'D.pdf',  checkDisable: false},
      { id: '5',  checkName:  '專家、學者', checkValue:  '5', name: '', tel:  '02-1234-5678', file: 'E.pdf',  checkDisable: false},
    ],
    status: [
        { value: 'Y', text: '公開'},
        { value: 'N', text: '草稿' },
      ],
    reasonData: [
      { id: '1',  checkName:  '1.廠商提出之創新產品不具創新性。:', checkValue:  '1', name: '', checkDisable: false},
      { id: '2',  checkName:  '2.廠商填報欄位內容不實，或有缺漏經通知限期補正屆期未補正。', checkValue:  '2', name: '', checkDisable: false},
      { id: '3',  checkName:  '3.機關無採購該產品之需求。', checkValue:  '3', name: '', checkDisable: false},
      { id: '4',  checkName:  '4.機關一年內曾就同一創新產品辦理交流', checkValue:  '4', name: '', checkDisable: false},
    ],
    cond1:  false,
    acceptStatus: this.$route.params.acceptType,
    communicateType:  [
      {value: '1',  text: '會議'},
      {value: '2',  text: '現勘'},
      {value: '3',  text: '公開示範說明會'},
      {value: '4',  text: '依公共工程創新產品交流平台試辦作業要點第12點規定轉入'},
    ],

    checkSchool:  ['1', '3'],
    tt: 'Y',
    checkReason:  '',
  }
 },
  methods:  {
    change(){
      //alert(this.prop);
      if(this.acceptStatus === 'N'){
        this.comminicateTime = '書面通知廠商不交流日期';
        this.cond1= true;
      }else{
        this.comminicateTime = '交流時間';
        this.cond1= false;
      }

    },
    queryHandler(){

    },
    reset(){

    }
  },
  mounted(){  //ready已經不備使用了
      this.change();
  },
setup(){
  const queryConditionDefault = {
      date_Start: '',
      date_End: '',
    };
  let form = reactive({
      date_Start: '',
      date_End: '',
    });

  const rules = reactive({
      date_Start: {},
      date_End: {}
    });

  const { $v, checkValidity, reset } =  useValidation(rules, form, queryConditionDefault);
  return {
  $v,
  validateState
 

 }
 }

}

</script>

<style>
.b-form-group-include-feedback {
  margin-bottom: 0.25rem;
  height: 5.8rem;
}

.mx-datepicker {
  width: 100%;
}
.feedback-row {
  height: 24px;
}

</style>

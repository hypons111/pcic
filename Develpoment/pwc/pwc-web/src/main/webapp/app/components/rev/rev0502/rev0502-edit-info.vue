<template>
  <div>
    <!--查詢條件區-->
    <section class="container">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                案件瀏覽
              </h5>
            </div>
          </div>
        </div>
        <div class="card-body col-10">
          <b-row>
              <i-form-group-check :label="$t('label.caseNa')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.caseName }}
              </i-form-group-check>
              <i-form-group-check :label="$t('label.caseNum')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.caseNumber }}
              </i-form-group-check>
            </b-row>
            <b-row>
              <i-form-group-check :label="'審議類別:'" :label-cols="4" :content-cols="8">
            {{ mockdata.reviewType }}
              </i-form-group-check>
              <i-form-group-check :label="'工程建設類別:'" :label-cols="4" :content-cols="8">
            {{ mockdata.classify }}
              </i-form-group-check>
            </b-row>
            <b-row>
              <i-form-group-check :label="$t('label.planType')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.planType }}
              </i-form-group-check>
              <i-form-group-check :label="'函報總經費:'" :label-cols="4" :content-cols="8">
            {{ mockdata.budget }}
              </i-form-group-check>
            </b-row>
        </div>
      </div>
    </section>
    <section class="container">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                資料分類
              </h5>
            </div>
          </div>
        </div>
        <div class="card-body col-10">
            <b-row>
              <i-form-group-check :label="$t('label.bos')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.boss }}
              </i-form-group-check>
              <i-form-group-check :label="$t('label.dept')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.dept }}
              </i-form-group-check>
            </b-row>
            <b-row>
              <i-form-group-check :label="$t('label.contactPersonId')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.contactperson }}
              </i-form-group-check>
              <i-form-group-check :label="$t('label.tel')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.telephone }}
              </i-form-group-check>
            </b-row>
              <i-form-group-check :label="$t('label.email')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.email }}
              </i-form-group-check>
              <i-form-group-check :label="'計畫概述:'" :label-cols="4" :content-cols="8">
            {{ mockdata.planexplain }}
              </i-form-group-check>
            </b-row>
            <b-row>
              <i-form-group-check :label="'計畫日期:'" :label-cols="4" :content-cols="8">
            {{ mockdata.dateStart }} ~ {{mockdata.dateEnd}}
              </i-form-group-check>
            </b-row>
        </div>
      </div>
    </section>
    <section class="container">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                工程會會議
              </h5>
            </div>
          </div>
        </div>
        <div class="card-body col-10">
            <b-row>
              <i-form-group-check :label="$t('label.receiveDate')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.receivedate }}
              </i-form-group-check>
              <i-form-group-check :label="$t('label.receiveNo')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.receiveNumber }}
              </i-form-group-check>
            </b-row>
            <b-row>
              <i-form-group-check :label="'屬本會審議範圍總經費(萬元):'" :label-cols="4" :content-cols="8">
            {{ mockdata.reviewtotalamount }}
              </i-form-group-check>
              <i-form-group-check :label="'本會核定經份(萬元):'" :label-cols="4" :content-cols="8">
            {{ mockdata.reviewdecideamount }}
              </i-form-group-check>
            </b-row>
            <b-row>
              <i-form-group-check :label="'工程會承辦人:'" :label-cols="4" :content-cols="8">
            {{ mockdata.engdept }}
              </i-form-group-check>
              <i-form-group-check :label="$t('label.tel')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.telephone2 }}
              </i-form-group-check>
            </b-row>
            <b-row>
              <i-form-group-check :label="$t('label.email')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.email2 }}
              </i-form-group-check>
            </b-row>
            <b-row>
              <i-form-group-check :label="'審議意見:'" class="col-12" :label-cols="2" :content-cols="10" >
                <b-form-textarea :value="mockdata.reviewopinion" rows="3"max-rows="6">
                  {{ mockdata.reviewopinion }}
                </b-form-textarea>
              </i-form-group-check>
            </b-row>
            <b-row>
              <i-form-group-check :label="'審議進度:'" :label-cols="4" :content-cols="8">
            {{ mockdata.status }}
              </i-form-group-check>
              <i-form-group-check :label="$t('label.changeDate')+':'" :label-cols="4" :content-cols="8">
            {{ mockdata.changeDate }}
              </i-form-group-check>
            </b-row>
            <b-row>
              <i-form-group-check :label="'異動人員:'" :label-cols="4" :content-cols="8">
            {{ mockdata.changepeople }}
              </i-form-group-check>
            </b-row>
        </div>
      </div>
    </section>
    
    <section>
      <div class="container mt-1 mb-1" align="left">
        <!-- 檢視按鈕 -->
        <i-button type="check-circle" @click="toInfoView"></i-button>
        </div>
    </section> 
  </div>
</template>

<script lang="ts">
import { computed, onActivated, reactive,Ref,toRef, ref } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';
import { RevMain } from '@/shared/model/revModel/rev-main.model';
import { required } from '@/shared/validators';
import i18n from '@/shared/i18n';

export default {
  name: 'rev0502EditInfo',
  props: {
    isReload: {
      required: false,
    },
  },
  //props代表傳進來的參數，context代表整頁的容器物件
  setup(props, context) {
    onActivated(() => {
      if (isReloadProp.value) {
      }
    });

    const isReloadProp = computed(() => props.isReload);

    //建立表單物件ref
    //reactive()內傳入參數可監聽該物件達到響應式
    //Object.assign(,)為指派第2個參數對象到第1個參數
    //$v為表單物件轉換而來，checkValidity是用來做欄位檢核

    //轉到檢視頁面
    const toInfoView = ((revMain: RevMain) => {
      navigateByNameAndParams('rev0501Query', { formStatusForward: 'info', revMain: revMain, isNotKeepAlive: false });
    };

    const mockdata ={
      
        caseName: '國道1號後勁溪過水橋新建工程',
        caseNumber:'1092C1110234325',
        reviewType: '可行性評估',
        classify: '公路',
        planType:'公共建設計畫',
        budget:'0',

        boss:'海洋委員會',
        dept:'A47000000C 海洋委員會',
        contactperson:'劉建位',
        telephone:' 02-87234894 分機6783',
        email:'windowhu@gmail.com',
        planexplain:'陸軍「岳崗營區工務大樓新建工程」基本設計書圖',
        dateStart:'109/11/20',
        dateEnd:'109/12/23',

        receivedate:'109/11/06',
        receiveNumber:'5555',
        createDate: '110/03/26',
        reviewtotalamount:'11,690',
        reviewdecideamount:'11,690',
        engdept:'黃廣軒',

        telephone2:'02-87897500 分機7805',
        email2:'pjchang@mail.pcc.gov.tw',
        reviewopinion:'一、復貴部106年12月27日國備工營字第1060015417號函。  二、本案函報工程總經費2億8,636萬6,753元，規劃興建地下1層、地上5層之工務大樓1棟及相關附屬設施，興建總樓地板面積9,548平方公尺。  三、本案工程總經費部分，經參酌相關資訊，同意核列工程總經費2億8,636萬6,753元。  四、本案地下一層設閱覽室部分，建議檢討採自然通風採光之可行性(如開高窗等)，以增加空間使用之舒適性。  五、地下一層設有男女廁所各1間，惟男廁設於防災應變中心內，似不利使用，建議依實際使用需求再予檢討。  六、地下室出入車道位於營區道路交叉口處，建議檢討相關交通標線及號誌等設施，以維行車安全。  七、建築物東西向遮陽部分似有不足，建議依當地日照情形及使用需求再予檢討。  八、各類空間面積於細部設計時，請依貴部訂頒「國軍營區設施建造原則」等相關規定，再作檢視。  九、本案營區之相關設備及建材，請擇較為實用、經濟、易維護之方式辦理。  十、請於辦理後續細部設計作業時，邀請具有建築、結構、機電等設計實務經驗之專家學者組成審查小組，詳予審查各空間及設備系統設計之妥適性及經費之合理性，俾使預算發揮最大綜效。  十一、請貴部督導主辦機關責成設計單位確實核算工項數量並依發包當時之營建市場行情單價再作核算，俾利未來順利發包。  十二、其餘請主辦機關配合辦理之事項，請查察本會訂定之「基本設計審議通案事項」 (公布於本會網站http://www.pcc.gov.tw/工程技術/公共工程經費審議/工程計畫審議/基本設計審議通案事項)。  '
        status: '審查完成',
        changeDate:'2020/11/06',
        changepeople:'張碧蓉'
        
      
    
    };

    //變數要做return，<template>才可以使用
    return {
      mockdata,
      toInfoView,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

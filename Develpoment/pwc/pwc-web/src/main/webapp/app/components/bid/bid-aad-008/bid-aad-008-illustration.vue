<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <section class="container mt-2">
          <h3>說明：</h3>
          <div>一、 本表用詞定義如下：</div>
          <div>(一)、 原契約金額：工程案之決標金額，不包含變更設計後追加減金額。</div>
          <div>(二)、 最終契約金額：原契約金額經主辦機關核定追加減金額後之最終金額。</div>
          <div>(三)、 最終核定工期：原契約所定工期併計歷次經主辦機關核定之追加(減)工期。</div>
          <div>(四)、 試驗報告不合格：指試驗結果不符合契約者，包含在容許誤差範圍外需減價收受者。</div>
          <div>
            二、
            計分基準計算結果一律以四捨五入計算至小數點以下第二位為止。例如(1)4.994%計算為4.99%，屬未達5%區間；4.995%計算為5%，屬5%以上區間。(2)查核成績平均79.995分計算為80分，屬80分以上區間；查核成績平均79.994分計算為79.99分，屬未達80分區間。
          </div>
          <div>
            三、 「提前竣工」、「逾期竣工」及「初驗、複驗及驗收過程之缺失與改善天數」等計分項目涉及天數計算者，均依契約所定計算方式辦理。
          </div>
          <div>四、 「專任工程人員參與情形」計分項目所稱「每月至少1次」，不包含開工、復工、停工及竣工等當月不足1個月者。</div>
          <div>
            五、
            「職業安全衛生與職業災害」、「環保」、「民眾通報缺失」及「施工作業」四項計分項目所稱「實際履約日」係指符合契約約定之計算工期日，惟除「施工作業」項目外，逾期部分不納入計算；所稱連續係指兩個連續實際履約日間，包含未施工日(即停工或不計工期日，因工地管理不善發生重大職災或環保事件，則中斷連續)，例如9/10、9/11、9/14、9/15為實際履約日，惟9/13發生依法應通知目的事業主管機關檢查機構之職業災害，則中斷連續，由9/14重新起算。
          </div>
          <div>
            六、
            有關計分項目「依政府採購法第101條至第103條規定被刊登政府採購公報之廠商」之計分基準第四項，其總分計算方式說明如下：如有符合前述計分基準之情形，機關辦理計分時，無須再依各計分項目進行加減分，以25分(即無符合加分條件且扣分均達最大值時之最低分)乘以施工進度百分比計。例如施工進度達60%，則總分為25分*60%=15分。
          </div>
          <div>
            七、
            本履約情形計分表之各項履約事實僅係作為本單一工程計分之依據，不跨標案計算。例如甲廠商承攬之A標案獲得公共工程金質獎優等、B標案則未獲獎，則於計分項目－品質優良，A標案加4分、B標案不加分。乙廠商承攬之C標案依政府採購法第103條第1項第2款停權1年、D標案無前述情形，則於計分項目－依政府採購法第101條至第103條規定被刊登政府採購公報之廠商，C標案減5分、D標案不減分。
          </div>
        </section>
        <section class="container mt-2">
          <b-row class="mt-2 justify-content-center" align-content="center">
            <b-col offset-sm="0" cols="4">
              <i-button type="arrow-bar-left" @click="backToPreviousPage"></i-button>
            </b-col>
          </b-row>
        </section>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Ref, toRef } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'bidAad008Illustration',
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
    bidKpifinKey: {
      type: Object,
      required: false,
    },
    formStatus: {
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidKpifinKeyProp: Ref<any> = toRef(props, 'bidKpifinKey');
    const formStatusProp: Ref<any> = toRef(props, 'formStatus');
    function backToPreviousPage() {
      navigateByNameAndParams('bidAad008Edit', {
        bidProjectKey: bidProjectKeyProp.value,
        bidKpifinKey: bidKpifinKeyProp.value,
        formStatus: formStatusProp.value,
      });
    }
    return { backToPreviousPage };
  },
};
</script>

<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <section class="container mt-2">
          <div>
            <b-table-simple bordered table-class="text-center">
              <b-thead head-variant="info">
                <b-tr>
                  <b-th>計分指標</b-th>
                  <b-th>計分項目</b-th>
                  <b-th>計分基準</b-th>
                </b-tr>
              </b-thead>
              <b-tbody>
                <b-tr>
                  <b-td class="align-middle" rowspan="2"> 如期履約情形 </b-td>
                  <b-td class="align-middle"> 提前竣工或如期完成 </b-td>
                  <b-td class="text-left">
                    依下列級距基準並乘以調整權數計分：(詳說明三) <br />
                    一、實際提前竣工天數/最終核定工期天數×100%： <br />
                    (一)10%以上：加3分。<br />
                    (二)5%以上未達10%：加2分。<br />
                    (三)1%以上未達5%：加1分。 <br />
                    (四)如期或提前竣工但未達上開加分條件者：加0.5分。<br />
                    二、依原契約金額調整權數： <br />
                    (一) 未達新臺幣1千萬元，調整權數0.6 <br />
                    (二) 1千萬元以上未達5千萬元，調整權數0.7 <br />
                    (三) 5千萬元以上未達2億元，調整權數0.8 <br />
                    (四) 2億元以上未達5億元，調整權數1.0 <br />
                    (五) 5億元以上未達10億元，調整權數1.2 <br />
                    (六) 10億元以上未達30億元，調整權數1.5 <br />
                    (七) 30億元以上未達50億元，調整權數1.8 <br />
                    (八) 50億元以上，調整權數2.0<br />
                  </b-td>
                </b-tr>
                <b-tr>
                  <b-td class="align-middle"> 逾期竣工 </b-td>
                  <b-td class="text-left">
                    依下列級距基準並乘以調整權數計分：(詳說明三) 一、 逾期違約金天數/最終核定工期天數×100%：<br />
                    (一) 未達1%：減0.5分。<br />
                    (二) 1%以上未達5%：減1分。<br />
                    (三) 5%以上未達10%：減2分。<br />
                    (四) 10%以上未達20%：減3分。<br />
                    (五) 20%以上：減5分。<br />
                    二、 依原契約金額調整權數：<br />
                    (一) 未達新臺幣1千萬元，調整權數0.6<br />
                    (二) 1千萬元以上未達5千萬元，調整權數0.7<br />
                    (三) 5千萬元以上未達2億元，調整權數0.8<br />
                    (四) 2億元以上未達5億元，調整權數1.0<br />
                    (五) 5億元以上未達10億元，調整權數1.2<br />
                    (六) 10億元以上未達30億元，調整權數1.5<br />
                    (七) 30億元以上未達50億元，調整權數1.8<br />
                    (八) 50億元以上，調整權數2.0<br />
                  </b-td>
                </b-tr>
                <b-tr>
                  <b-td class="align-middle" rowspan="2">履約成本及違約金</b-td>
                  <b-td class="align-middle">提出替代方案</b-td>
                  <b-td class="text-left">
                    施工廠商依招標文件、契約規定於履約期間主動提出技術、工法、材料或設備之替代方案，或詢採購契約要向第21點第1巷第4款機制提出「較契約原標示者更優或對機關更有利」之契約變更，經主辦機關依政府採購法第35條、替代方案實施辦法或契約規定評估後採行，且最後結算驗收結果達成下列條件之一者，加1分：<br />
                    (一) 實際縮減工期達原契約所定工期3%以上且未逾期竣工。<br />
                    (二) 減省經費達原契約金額3%以上。<br />
                  </b-td>
                </b-tr>
                <b-tr>
                  <b-td class="align-middle">違約金金額</b-td>
                  <b-td class="text-left"
                    >依政府採購法及契約所定可歸責於施工廠商之違約金(如損害賠償、品質查核缺失、減價收受等，不含逾期違約金)比率(違約金合計金額/最終契約金額×100%)計分：<br />
                    (一) 未達1%：減0.5分。<br />
                    (二) 1%以上未達2%：減1分<br />
                    (三) 2%以上未達3%：減1.5分。<br />
                    (四) 3%以上未達4%：減2分。<br />
                    (五) 4%以上：減2.5分。</b-td
                  >
                </b-tr>
                <!-- 1 -->
                <b-tr>
                  <b-td class="align-middle" rowspan="9">施工品質</b-td>
                  <b-td class="align-middle" rowspan="2">初驗、複驗及驗收過程之缺失與改善天數</b-td>
                  <b-td class="text-left">
                    (一) 初驗、複驗及驗收過程無缺失或缺失累計之實際改善天數未達3天，且無逾期違約金者(詳說明三)：加2分。<br />
                    (二) 初驗、複驗及驗收過程有缺失，缺失累計之實際改善天數3天以上未達15天且無逾期違約金者(詳說明三)：加1分。<br />
                  </b-td>
                </b-tr>
                <b-tr>
                  <b-td class="text-left">
                    初驗、複驗及驗收過程有缺失，依下列級距基準並乘以調整權數計分(詳說明三)：<br />
                    一、 缺失累計之實際改善天數：<br />
                    (一) 30天以上未達45天：減0.5分。<br />
                    (二) 45天以上未達60天：減1分。<br />
                    (三) 60天以上未達75天：減2分。<br />
                    (四) 75天以上未達90天：減3分。<br />
                    (五) 90天以上未達180天：減4.5分。<br />
                    (六) 180天以上：減6分。<br />
                    二、 依原契約金額調整權數：<br />
                    (一) 未達1千萬元，調整權數2.0<br />
                    (二) 1千萬元以上未達5千萬元，調整權數1.8<br />
                    (三) 5千萬元以上未達2億元，調整權數1.5<br />
                    (四) 2億以上未達5億元，調整權數1.2<br />
                    (五) 5億以上未達10億元，調整權數1.0<br />
                    (六) 10億以上未達30億元，調整權數0.8<br />
                    (七) 30億元以上未達50億元，調整權數0.7<br />
                    (八) 50億元以上，調整權數0.6<br />
                  </b-td>
                </b-tr>
                <!-- 2 -->
                <b-tr>
                  <b-td class="align-middle">施工作業</b-td>
                  <b-td class="text-left">
                    一、 依下列情形減分，並乘以調整權數計分(詳說明五)：<br />
                    (一) 主辦機關、監造單位現場取樣試驗報告，有不合格紀錄：每次減1分。<br />
                    (二) 施工廠商於檢驗停留點應檢附之自主檢查表與監造單位現場查驗內容比對，發現有錯誤情形：每次減0.2分。<br />
                    (三) 施工廠商於檢驗停留點未經檢驗或同意，即進行下一道工序：每次減1分。<br />
                    (四)
                    工程查核或各級機關工程督導小組督導，有丙等情形或重大缺失，工地負責人、工地主任、品管人員或勞安人員經主辦機關通知廠商更換調離工地者：每人次減1分。<br />
                    二、 依實際履約日數調整權數：<br />
                    (一) 未達365天，調整權數1.0<br />
                    (二) 365天以上未達730天，調整權數0.9<br />
                    (三) 730天以上未達1,095天，調整權數0.8<br />
                    (四) 1,095天以上未達1,460天，調整權數0.7<br />
                    (五) 1,460天以上未達1,825天，調整權數0.6<br />
                    (六) 1,825天以上，調整權數0.5<br />
                    三、 本項至多減10分。<br />
                  </b-td>
                </b-tr>
                <!-- 3 -->
                <b-tr>
                  <b-td class="align-middle" rowspan="2">施工查核 </b-td>
                  <b-td class="text-left">
                    以歷次查核平均成績計分：<br />
                    (一) 90分以上加4分。<br />
                    (二) 85分以上未達90分：加3分。<br />
                    (三) 80分以上未達85分：加2分。<br />
                  </b-td>
                </b-tr>
                <b-tr>
                  <b-td class="text-left">
                    以歷次查核平均成績計分：<br />
                    (一) 未達75分：減0.5分。<br />
                    (二) 查核成績單次未達70分，每次再減1分。<br />
                    ※平均成績75分以上未達80分間無加減分。<br />
                  </b-td>
                </b-tr>
                <!-- 4 -->
                <b-tr>
                  <b-td class="align-middle" rowspan="2">專任工程人員參與情形 </b-td>
                  <b-td class="text-left">
                    適用營造業法之工程，施工廠商專任工程人員於契約施工期間親自赴工地現場督察且留存督察紀錄，並確實登載於施工日誌(詳說明四)：<br />
                    (一) 總次數平均每30天達3次以上，且施工期間如當月有施工者，每月至少1次：加2分。<br />
                    (二) 總次數平均每30天達2次以上，且施工期間如當月有施工者，每月至少1次：加1分。<br />
                  </b-td>
                </b-tr>
                <b-tr>
                  <b-td class="text-left">
                    最終核定工期在90天以上且適用營造業法之工程，施工廠商專任工程人員於契約施工期間親自赴工地現場督察且留存督察紀錄，並確實登載於施工日誌，平均每30天未達1次者(詳說明四)：減0.5分。
                  </b-td>
                </b-tr>
                <!-- 5 -->
                <b-tr>
                  <b-td class="align-middle">技術士參與情形 </b-td>
                  <b-td class="text-left">
                    一
                    施工廠商於契約施工期間，依需要在工地設置與施工項目或相關作業有關之技術士，並陳報主辦機關核定且確實登在於施工日誌者，每人加0.5分。<br />
                    二 本項至多加2分。<br />
                  </b-td>
                </b-tr>
                <!-- 6 -->
                <b-tr>
                  <b-td class="align-middle">品質優良 </b-td>
                  <b-td class="text-left">
                    擇優採計下列其中一款，並僅限加分一次：<br />
                    一、 獲得行政院公共工程委員會辦理之公共工程金質獎相關獎項：<br />
                    (一) 特優：加5分。<br />
                    (二) 優等：加4分。<br />
                    (三) 入圍或佳作：加2分。<br />
                    二、 獲得中央部會或直轄市、縣(市)政府辦理之公共工程品質之相關獎項：<br />
                    (一) 最高階獎項(如特優等)：加2.5分。<br />
                    (二) 次高階獎項(如優等等)：加2分。<br />
                    (三) 其他獎項：加1分。<br />
                  </b-td>
                </b-tr>
                <b-tr>
                  <b-td class="align-middle" rowspan="4">安衛環保</b-td>
                  <b-td class="align-middle" rowspan="3">職業安全衛生與職業災害</b-td>
                  <b-td class="text-left">
                    一、 獲得勞動部辦理之金安獎或推動職業安全衛生優良公共工程選拔金安獎相關獎項：<br />
                    (一) 特優：加2.5分。<br />
                    (二) 優等或獲選(101年以前)：加2分。<br />
                    (三) 佳作或入圍(102年以前)：加1.5分。<br />
                    二、 本項至多加2.5分。<br />
                  </b-td>
                </b-tr>
                <b-tr>
                  <b-td class="text-left">
                    一 依下列情形加分 (一)
                    實際履約日未達30天且履約過程中未發生職業安全衛生法第37條第2項所列應通知勞動檢查機構之職業災害者，加0.3分。。<br />
                    (二) (二)
                    實際履約日達30天以上者，依履約過程中，以未發生職業安全衛生法第37條第2項所列應通知勞動檢查機構之職業災害之實際履約日連續天數最多者計算，每連續滿30天加0.3分，尾數不計(詳說明五)。<br />
                    二 本項至多加3分。<br />
                  </b-td>
                </b-tr>
                <b-tr>
                  <b-td class="text-left">
                    一
                    契約施工期間發生職業安全衛生法第37條第2項所列應通知勞動檢查機構之職業災害：每次事件發生扣1分，如有死亡情形每死亡1人加扣1分。<br />
                    二 本項至多減10分。<br />
                  </b-td>
                </b-tr>
                <b-tr>
                  <b-td class="align-middle">環保</b-td>
                  <b-td class="text-left"
                    >一 依下列情形加分<br />
                    (一) 實際履約日未達30天且履約過程中未發生經目的事業主管機關裁罰之環保事件者，加0.3分。<br />
                    (二)
                    實際履約日達30天以上者，依履約過程中，以未發生經目的事業主管機關裁罰之環保事件之實際履約日連續天數最多者計算，每連續滿30天加0.3分，尾數不計(詳說明五)。<br />
                    二 本項至多加3分。<br
                  /></b-td>
                </b-tr>
                <b-tr>
                  <b-td class="align-middle" rowspan="2">民眾反映及是否停權</b-td>
                  <b-td class="align-middle">民眾通報缺失</b-td>
                  <b-td class="text-left">
                    一 依下列情形加分<br />
                    (一) (一)
                    實際履約日未達30天且履約過程中未發生可歸責於施工廠商之全民督工或直轄市、縣(市)政府1999專線等民眾通報缺失案件者，加0.3分。<br />
                    (二) (二)
                    實際履約日達30天以上者，依履約過程中，以未發生可歸責於施工廠商之全民督工或直轄市、縣(市)政府1999專線等民眾通報缺失案件之實際履約日連續天數最多者計算，每連續滿30天加0.3分，尾數不計(詳說明五)。<br />
                    二 本項至多加3分。<br />
                  </b-td>
                </b-tr>
                <b-tr>
                  <b-td class="align-middle">依政府採購法第101條至第103條規定被刊登政府採購公報之廠商</b-td>
                  <b-td class="text-left">
                    一 屬政府採購法第103條第1項第1款者：減10分。<br />
                    二 屬政府採購法第103條第1項第2款或第3款者：減5分。<br />
                    三 屬政府採購法第103條第1項各款之一，且解除契約者：各計分項目不予計分，總分以0分計。<br />
                    四 屬政府採購法第103條第1項各款之一，且終止契約者：各計分項目不予計分，總分以25分乘以施工進度百分比計(詳說明六)。<br
                  /></b-td>
                </b-tr>
              </b-tbody>
            </b-table-simple>
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
  name: 'bidAad008BenchMark',
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

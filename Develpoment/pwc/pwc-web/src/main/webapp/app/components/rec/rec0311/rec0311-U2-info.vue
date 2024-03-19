<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <b>南投縣政府109年5月豪雨公共設施災後復建工程災害查估紀錄及復建經費概估</b>
        </h5>
      </div>
      <div class="card-body col-12">
        <b-form-row class="align-items-top"> 一、{{ $t('label.projectCodes') }}：{{ $v.projectCode.$model }} </b-form-row>
        <b-form-row class="pb-2"> 　　{{ $t('label.applySubmitNo') }}：{{ $v.applySubmitNo.$model }} </b-form-row>
        <b-form-row class="pb-2"> 二、{{ $t('label.recEngName') }}：{{ $v.projectName.$model }} </b-form-row>
        <b-form-row class="align-items-top"> 三、災害發生 </b-form-row>
        <b-form-row class="align-items-top"> 　　日期：2020年 5月 13日 </b-form-row>
        <b-form-row class="align-items-top"> 　　災害發生地點：{{ $v.city.$model }}{{ $v.village.$model }} </b-form-row>
        <b-form-row class="align-items-top">
          　　是否屬土石流潛勢溪流影響區：
          <b-form-radio-group
            v-model="queryOptions.arrRadio[0].text"
            :options="queryOptions.type"
            class="pt-1 pl-5 ml-5"
          ></b-form-radio-group>
        </b-form-row>
        <b-form-row class="align-items-top">
          　　是否屬林班地內：　　　　　　
          <b-form-radio-group
            v-model="queryOptions.arrRadio[0].text"
            :options="queryOptions.type"
            class="pt-1 pl-5 ml-5"
          ></b-form-radio-group>
        </b-form-row>
        <b-form-row class="pb-2">
          　　是否屬重複致災地點：　　　　
          <b-form-radio-group
            v-model="queryOptions.arrRadio[0].text"
            :options="queryOptions.type"
            class="pt-1 pl-5 ml-5"
          ></b-form-radio-group>
        </b-form-row>
        <b-form-row class="align-items-top"> 四、初步查估結果： </b-form-row>
        <b-form-row class="align-items-top"> 　　（一）是否符合中央補助原則（請逐項檢核） </b-form-row>
        <b-form-row class="align-items-top">
          <b-form-radio-group v-model="queryOptions.arrRadio[0].text" :options="queryOptions.type" class="pt-1 pl-5 ml-5">
            非屬災害消防、防汛、搶險、搶修等緊急搶救措施。
          </b-form-radio-group>
        </b-form-row>
        <b-form-row class="align-items-top">
          <b-form-radio-group v-model="queryOptions.arrRadio[1].text" :options="queryOptions.type" class="pl-5 ml-5">
            非屬土方清除、疏濬、機具設備、用地、拆遷補償等非工程項目，與僅具宣傳、景觀功能之設施及植栽。
          </b-form-radio-group>
        </b-form-row>
        <b-form-row class="align-items-top">
          <b-form-radio-group v-model="queryOptions.arrRadio[2].text" :options="queryOptions.type" class="pl-5 ml-5">
            非屬因年久失修等非天然災害造成之損失案件。
          </b-form-radio-group>
        </b-form-row>
        <b-form-row class="align-items-top">
          <b-form-radio-group v-model="queryOptions.arrRadio[3].text" :options="queryOptions.type" class="pl-5 ml-5">
            非屬道路工程中路樹、路燈、反射鏡及交通號誌等涉及交通安全，需於災後立即施作之措施。(若需與道路復建工程一併施作者，不在此限)
          </b-form-radio-group>
        </b-form-row>
        <b-form-row class="align-items-top">
          <b-form-radio-group v-model="queryOptions.arrRadio[4].text" :options="queryOptions.type" class="pl-5 ml-5">
            非屬公共造產或其他由各級政府所經營具有經濟價值之事業。
          </b-form-radio-group>
        </b-form-row>
        <b-form-row class="align-items-top">
          <b-form-radio-group v-model="queryOptions.arrRadio[5].text" :options="queryOptions.type" class="pl-5 ml-5">
            非屬私人設施。
          </b-form-radio-group>
        </b-form-row>
        <b-form-row class="align-items-top">
          <b-form-radio-group v-model="queryOptions.arrRadio[6].text" :options="queryOptions.type" class="pl-5 ml-5">
            擇一報請中央政府主管機關或行政院協助，無重覆提報。
          </b-form-radio-group>
        </b-form-row>
        <b-form-row class="align-items-top">
          <b-form-radio-group v-model="queryOptions.arrRadio[7].text" :options="queryOptions.type" class="pb-1 pl-5 ml-5">
            有具體保護對象，且屬合法開發利用者。
          </b-form-radio-group>
        </b-form-row>
        <b-form-row class="pb-2">
          　　　　　　註：若任一項目為「否」，不符中央補助原則，無需進行下列評估，<b>請本於權責自籌經費辦理。</b>
        </b-form-row>

        <b-form-row class="align-items-top">
          　　（二）現況及災損情形概估：<br />
          　　　　 1. 現況（原保護形式）、災損情形、成因概述<br />
        </b-form-row>
        <b-form-row class="align-items-top"> 　　　　 2. 位置簡圖 </b-form-row>
        <b-form-row class="ml-5 pl-5">
          <img
            src="https://thbwcs.thb.gov.tw/FileResource.axd?path=wces%252Fhtml%252Fimage%252F%25E5%259C%258B%25E9%2581%259310%25E8%2599%259F%25E6%2596%25B0%25E9%2597%25A2%25E9%2581%2593%25E8%25B7%25AF%25E5%25B7%25A5%25E7%25A8%258B%25E4%25BD%258D%25E7%25BD%25AE-%25E5%259C%25B0%25E5%259C%2596%25E6%25A8%2599%25E7%25A4%25BA.png"
            width="500"
            height="240"
            class="d-inline-block align-top"
            alt=""
          />
        </b-form-row>
        <b-form-row class="align-items-top"> 　　　　 3. 照片 </b-form-row>
        <b-form-row class="ml-5 pl-5">
          <img
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCiFS0J9EtohsKx3vXPB4JXZLQMVUumQzvuA&usqp=CAU"
            width="500"
            height="240"
            class="d-inline-block align-top"
            alt="災損照片1"
          />
        </b-form-row>
        <b-form-row class="ml-5 pl-5"> 工區1衛星定位點:X座標 225435 Y座標 2623212 (TWD-97) </b-form-row>
        <b-form-row class="ml-5 pl-5">
          <img
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtR4F3u02sRjl-04aX-o3ndF-pkV8yWDnGoQ&usqp=CAU"
            width="500"
            height="240"
            class="d-inline-block align-top"
            alt="災損照片1"
          />
        </b-form-row>
        <b-form-row class="ml-5 pl-5"> 工區2衛星定位點:X座標 225435 Y座標 2623212 (TWD-97) </b-form-row>
        <b-form-row class="align-items-top">
          　　　　 4. 災損說明（如影響戶數、災害區域大小、預估經濟損失）。<br />
          　　　　　{{ $v.damageDescript.$model }} 既有護岸及固床工基礎嚴重掏空、影響戶數約120戶、災害區域大小約1500M2、預估經濟損失約2000萬
        </b-form-row>
        <b-form-row class="align-items-top">
          　　　　 5. 立即危險性之評估（以公共危險為主，可補充文字敘述，如：可能造成人民生命威脅...）。
        </b-form-row>
        <b-form-row class="pt-2 pb-3">
          　　　　　　　　立即危險高
          <b-form-radio v-model="$v.dangerousDegree.$model" name="some-radios" value="5">5　</b-form-radio>
          <b-form-radio v-model="$v.dangerousDegree.$model" name="some-radios" value="4">4　</b-form-radio>
          <b-form-radio v-model="$v.dangerousDegree.$model" name="some-radios" value="3">3　</b-form-radio>
          <b-form-radio v-model="$v.dangerousDegree.$model" name="some-radios" value="2">2　</b-form-radio>
          <b-form-radio v-model="$v.dangerousDegree.$model" name="some-radios" value="1">1　</b-form-radio>
          立即危險低
        </b-form-row>
        <b-form-row class="pb-2"> 　　　　　{{ $v.dangerousDescript.$model }} 護岸掏空可能造成人民生命威脅、財產損失 </b-form-row>
        <b-form-row class="align-items-top"> 　　（三）破壞模式與可能致災原因分析與檢討 </b-form-row>
        <b-form-row class="pb-2 ml-5 pl-4">
          <b-table-simple bordered>
            <b-thead class="text-center">
              <tr>
                <b-th class="align-middle">項次</b-th>
                <b-th class="align-middle">類型</b-th>
                <b-th class="align-middle">災害情形</b-th>
                <b-th class="align-middle">可能之致災原因</b-th>
                <b-th class="align-middle">範圍及嚴重度</b-th>
              </tr>
            </b-thead>
            <b-tbody>
              <b-tr>
                <b-td>1</b-td>
                <b-td>水利</b-td>
                <b-td>2.護岸損壞</b-td>
                <b-td>豪雨沖刷</b-td>
                <b-td
                  >1.既有構造物(田頭巷)上或下游左、右岸約150公尺<br />
                  2.毀損長度約85公尺
                </b-td>
              </b-tr>
              <b-tr>
                <b-td>2</b-td>
                <b-td>水利</b-td>
                <b-td>2.護岸損壞</b-td>
                <b-td>基礎掏空破壞</b-td>
                <b-td
                  >1.既有構造物(田頭巷)上或下游左、右岸約150公尺<br />
                  2.毀損長度約85公尺
                </b-td>
              </b-tr>
              <b-tr>
                <b-td>3</b-td>
                <b-td>水利</b-td>
                <b-td>2.護岸損壞</b-td>
                <b-td>基礎深度不足</b-td>
                <b-td
                  >1.既有構造物(田頭巷)上或下游左、右岸約150公尺<br />
                  2.毀損長度約85公尺
                </b-td>
              </b-tr>
              <b-tr>
                <b-td>4</b-td>
                <b-td>水利</b-td>
                <b-td>3.河道內結構物(固床工、跌水工、丁壩)等損壞</b-td>
                <b-td>豪雨沖刷</b-td>
                <b-td
                  >1.既有構造物(固床工)<br />
                  2.毀損面積約100平方公尺，深平均2公尺
                </b-td>
              </b-tr>
              <b-tr>
                <b-td>5</b-td>
                <b-td>水利</b-td>
                <b-td>3.河道內結構物(固床工、跌水工、丁壩)等損壞</b-td>
                <b-td>基礎深度不足</b-td>
                <b-td
                  >1.既有構造物(固床工)<br />
                  2.毀損面積約100平方公尺，深平均2公尺
                </b-td>
              </b-tr>
            </b-tbody>
          </b-table-simple>
        </b-form-row>
        <b-form-row class="align-items-top">
          　　（四）復健計畫：（以實地勘查結果研擬復健方案，應包括復建工程內容、數量、單價、基本設計相關圖說及經濟估算等）<br />
          　　　　 1. 復建工程內、數量及單價：
        </b-form-row>
        <b-form-row class="pb-2 ml-5 pl-4">
          <b-table-simple bordered>
            <b-thead class="text-center">
              <tr>
                <b-th class="align-middle">構造物</b-th>
                <b-th class="align-middle">構造物形式</b-th>
                <b-th class="align-middle">單位</b-th>
                <b-th class="align-middle">單價(元)</b-th>
                <b-th class="align-middle">數量</b-th>
                <b-th class="align-middle">複價(元)</b-th>
                <b-th class="align-middle">備註</b-th>
              </tr>
            </b-thead>
            <b-tbody>
              <b-tr>
                <b-td>固床工</b-td>
                <b-td>固床工(高=2.0m、預寬1.0m)</b-td>
                <b-td>m</b-td>
                <b-td class="text-right">13,000</b-td>
                <b-td>15</b-td>
                <b-td class="text-right">195,000</b-td>
                <b-td>長度3.0m、5座</b-td>
              </b-tr>
              <b-tr>
                <b-td>護岸基礎加深補強</b-td>
                <b-td>高2.5m</b-td>
                <b-td></b-td>
                <b-td class="text-right">5,000</b-td>
                <b-td>295</b-td>
                <b-td class="text-right">1,475,000</b-td>
                <b-td>無名橋上下游共118m</b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="7"><b>工區1衛星定位點：X 座標 225435 Y 座標 2623212(TWD-97)</b></b-td>
              </b-tr>
            </b-tbody>
          </b-table-simple>
        </b-form-row>
        <b-form-row class="align-items-top">
          <!-- 初估總經費 -->
          <i-form-group-check :label="'　　　　 2. ' + $t('label.estimateAmount') + ':'" :item="$v.recAmount" class="float-left col-7">
            <b-form-input v-model.trim="$v.recAmount.$model" :state="validateState($v.recAmount)" maxlength="10"> </b-form-input>
          </i-form-group-check>
          (千元)
        </b-form-row>
        <b-form-row class="align-items-top"> 　　　　 3. 基本設計示意圖說： </b-form-row>
        <b-form-row class="ml-5 pl-5">
          <img
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgI7J5b1RZdBEZWnaqPYFnoGlFu_oTdSaF2g&usqp=CAU"
            width="500"
            height="240"
            class="d-inline-block align-top"
            alt="災損照片1"
          />
        </b-form-row>
        <b-form-row class="pb-2 ml-5 pl-5"> 工區1衛星定位點:X座標 225435 Y座標 2623212 (TWD-97) </b-form-row>
        <b-form-row class="align-items-top"> 　　　　 4. 其他補充事項： </b-form-row>
      </div>
      <div class="card-header">
        <h5 class="m-0">
          <b>審查機關意見</b>
        </h5>
      </div>

      <!-- 審查機關意見編輯區 -->
      <div class="card-body col-12">
        <b-form-row>
          <b-table-simple bordered v-if="editVisible">
            <b-thead class="text-center">
              <tr>
                <b-th class="align-middle">構造物</b-th>
                <b-th class="align-middle">構造物形式</b-th>
                <b-th class="align-middle">單價(元)</b-th>
                <b-th class="align-middle">單位</b-th>
                <b-th class="align-middle">數量</b-th>
                <b-th class="align-middle">複價(元)</b-th>
                <b-th class="align-middle">備註</b-th>
              </tr>
            </b-thead>
            <b-tbody>
              <b-tr>
                <b-td><b-form-select v-model="$v.constItemName.$model" :options="queryOptions.constItemName"></b-form-select></b-td>
                <b-td>
                  <b-form-select
                    v-model="$v.constModelName.$model"
                    :options="queryOptions.constModelName"
                    @change="getRefPrice"
                  ></b-form-select
                ></b-td>
                <b-td><b-form-input :value="unitPrice"></b-form-input></b-td>
                <b-td><b-form-input :value="unit"></b-form-input></b-td>
                <b-td><b-form-input value=""></b-form-input></b-td>
                <b-td><b-form-input value=""></b-form-input></b-td>
                <b-td><b-form-input value=""></b-form-input></b-td>
              </b-tr>
            </b-tbody>
          </b-table-simple>
          <b-table-simple bordered v-if="addVisible">
            <b-thead class="text-center">
              <tr>
                <b-th class="align-middle">構造物</b-th>
                <b-th class="align-middle">構造物形式</b-th>
                <b-th class="align-middle">單價(元)</b-th>
                <b-th class="align-middle">單位</b-th>
                <b-th class="align-middle">數量</b-th>
                <b-th class="align-middle">複價(元)</b-th>
                <b-th class="align-middle">備註</b-th>
              </tr>
            </b-thead>
            <b-tbody>
              <b-tr>
                <b-td><b-form-select :options="queryOptions.constItemName"></b-form-select></b-td>
                <b-td><b-form-select :options="queryOptions.constModelName"></b-form-select></b-td>
                <b-td><b-form-input value=""></b-form-input></b-td>
                <b-td><b-form-input value=""></b-form-input></b-td>
                <b-td><b-form-input value=""></b-form-input></b-td>
                <b-td><b-form-input value=""></b-form-input></b-td>
                <b-td><b-form-input value=""></b-form-input></b-td>
              </b-tr>
            </b-tbody>
          </b-table-simple>
        </b-form-row>
        <div class="button-float-right pb-5" v-if="editVisible">
          <b-button-toolbar class="mt-2">
            <i-button class="ml-1" type="check-circle" @click=""></i-button>
            <i-button class="ml-1" type="arrow-counterclockwise" @click="editVisible = !editVisible"></i-button>
          </b-button-toolbar>
        </div>
        <br />
        <b-form-row class="pb-5 col-12" v-if="refPriceVisible">
          參考單價<br />
          (單價僅供參考，並確實依當時工料行情及工區條件等嚴實編列)
          <b-table-simple bordered>
            <b-thead>
              <b-tr>
                <b-th colspan="4" class="align-middle text-center">經濟部水利署提供(110.05)</b-th>
              </b-tr>
              <b-tr>
                <b-th class="align-middle">工程項目</b-th>
                <b-th class="align-middle">單價(元)</b-th>
                <b-th class="align-middle">單位</b-th>
                <b-th class="align-middle">相關訊息</b-th>
                <b-th class="align-middle">動作</b-th>
              </b-tr>
            </b-thead>
            <b-tbody>
              <b-tr>
                <b-td>路路面8cm厚AC(m2,含碎石級配，含材料)</b-td>
                <b-td class="text-right">1,500</b-td>
                <b-td>m2</b-td>
                <b-td></b-td>
                <b-td><b-button class="ml-1" variant="info" @click="getUnitPrice('1,500', 'm2')">選取</b-button></b-td>
              </b-tr>
              <b-tr>
                <b-td>路路面8cm厚AC(m2,含碎石級配，含材料)</b-td>
                <b-td class="text-right">1,300</b-td>
                <b-td>m2</b-td>
                <b-td></b-td>
                <b-td><b-button class="ml-1" variant="info" @click="getUnitPrice('1,300', 'm2')">選取</b-button></b-td>
              </b-tr>
            </b-tbody>
          </b-table-simple>
        </b-form-row>
        <b-form-row class="pb-5 col-12">
          <b-table-simple bordered>
            <b-thead class="text-center">
              <tr>
                <b-th class="align-middle">構造物</b-th>
                <b-th class="align-middle">構造物形式</b-th>
                <b-th class="align-middle">單位</b-th>
                <b-th class="align-middle">單價(元)</b-th>
                <b-th class="align-middle">數量</b-th>
                <b-th class="align-middle">複價(元)</b-th>
                <b-th class="align-middle">備註</b-th>
                <b-th class="align-middle">動作</b-th>
              </tr>
            </b-thead>
            <b-tbody>
              <b-tr>
                <b-td>固床工</b-td>
                <b-td>固床工(高=2.0m、預寬1.0m)</b-td>
                <b-td>m</b-td>
                <b-td class="text-right">13,000</b-td>
                <b-td>15</b-td>
                <b-td class="text-right">195,000</b-td>
                <b-td>長度3.0m、5座</b-td>
                <b-td>
                  <i-button class="mt-1" type="pencil-square" @click="editVisible = !editVisible"></i-button>
                  <i-button class="mt-1" type="trash" @click=""></i-button>
                </b-td>
              </b-tr>
              <b-tr>
                <b-td>護岸基礎加深補強</b-td>
                <b-td>高2.5m</b-td>
                <b-td></b-td>
                <b-td class="text-right">5,000</b-td>
                <b-td>295</b-td>
                <b-td class="text-right">1,475,000</b-td>
                <b-td>無名橋上下游共118m</b-td>
                <b-td>
                  <i-button class="mt-1" type="pencil-square" @click="editVisible = !editVisible"></i-button>
                  <i-button class="mt-1" type="trash" @click=""></i-button>
                </b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="8">
                  <b-button class="mt-1" variant="info" @click="editVisible = !editVisible">新增工項</b-button>
                </b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="8"><b>工區1衛星定位點：X 座標 225435 Y 座標 2623212(TWD-97)</b></b-td>
              </b-tr>
            </b-tbody>
          </b-table-simple>
        </b-form-row>
        <b-form-row>
          <i-form-group-check :label="$t('label.suggestEngCode') + ':'" :item="$v.suggestEngCode" class="col-7">
            <b-form-select v-model="$v.projectCode.$model" :options="queryOptions.projectCode"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <i-form-group-check :labelStar="true" :label="$t('label.isOnsiteCheck') + ':'" :item="$v.isOnsiteCheck" class="col-7">
            <b-form-radio-group
              :labelStar="true"
              v-model="queryOptions.arrRadio[0].text"
              :options="queryOptions.type"
              class="pt-1 pl-5 ml-5"
            ></b-form-radio-group>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <i-form-group-check :labelStar="true" :label="$t('label.isIncludeItemRatio') + ':'" :item="$v.isIncludeItemRatio" class="col-7">
            <b-form-radio-group
              :labelStar="true"
              v-model="queryOptions.arrRadio[0].text"
              :options="queryOptions.type"
              class="pt-1 pl-5 ml-5"
            ></b-form-radio-group>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <i-form-group-check :labelStar="true" :label="$t('label.isPlanningAmount') + ':'" :item="$v.isPlanningAmount" class="col-7">
            <b-form-radio-group
              :labelStar="true"
              v-model="queryOptions.arrRadio[0].text"
              :options="queryOptions.type"
              class="pt-1 pl-5 ml-5"
            ></b-form-radio-group>
          </i-form-group-check>
        </b-form-row>
        <b-form-row class="align-items-top">
          <i-form-group-check :label="$t('label.opinion') + ':'" :item="$v.opinion" class="col-12" label-cols="1" content-cols="11">
            <b-form-textarea v-model="$v.damageDescript.$model" rows="4" maxlength="4000" placeholder=""></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <div class="button-float-right">
          <b-button-toolbar class="mt-5" v-if="formStatusRef === 'modify'">
            <i-button class="ml-1" type="save" @click="submitForm('modify')"></i-button>
            <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
          </b-button-toolbar>
          <b-button-toolbar class="mt-5" v-else-if="formStatusRef === 'create'">
            <i-button class="ml-1" type="k" @click="submitFormNext"></i-button>
            <i-button class="ml-1" type="save" @click="submitForm('create')"></i-button>
          </b-button-toolbar>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { required, email } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { RecProject } from '@/shared/model/recModel/rec-project.model';
import { handleBack } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { FormStatusEnum } from '@/shared/enum';

export default {
  name: 'rec0311U2',
  props: {
    formStatusForward: {
      type: String,
      required: true,
    },
    recProject: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const recProjectProp = toRefs(props).recProject;

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);

    //顯示編輯復建工程區
    const editVisible = ref(false);
    //顯示新增工項區
    const addVisible = ref(false);
    //顯示參考單價區
    const refPriceVisible = ref(false);


    //空值表單
    const cleanTable = {
      projectCode: '',
      applySubmitNo: '',
      projectName: '',
      projectNo: '',
      analysisNo: '',
      damageDate: undefined,
      damageDescript: '',
      dangerousDegree: 3,
      dangerousDescript: '',
      isReviewChangeAmount: false,
      isSurveyPlan: false,
      createUser: '',
      createTime: undefined,
      updateUser: '',
      updateTime: undefined,
      city: '',
      village: '',
      recAmount: '1,670',
      suggestEngCode: '',
      isOnsiteCheck: '',
      isPlanningAmount: '1670'
      isIncludeItemRatio: '',
      opinion: '',
      constItemName: '',
      constModelName: '',
    };

    //預設值
    const formDefault = Object.assign({}, cleanTable);

    //預存好的form
    const form = reactive(Object.assign({}, formDefault));

    //檢核規則
    const rules = {
      projectCode: {notNull: required},
      applySubmitNo: {},
      projectName: {notNull: required},
      projectNo: {},
      analysisNo: {},
      damageDate: {},
      dangerousDegree: {},
      damageDescript: {},
      dangerousDescript: {},
      isReviewChangeAmount: {},
      isSurveyPlan: {},
      city: {},
      village: {},
      recAmount: {},
      suggestEngCode: {},
      isOnsiteCheck: {},
      isPlanningAmount: {},
      isIncludeItemRatio: {},
      opinion: {},
      constItemName: {},
      constModelName: {},
    };

    const allOrgRole = ref([]);

    const getOrgRole = () => {
      axios
        .get('/rec-user-project-codes/get-rec-org-role')
        .then(({ data }) => {
          allOrgRole.value.splice(0, allOrgRole.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    // 下拉選單選項
    const queryOptions = reactive({
      projectCode: [
        { value: '', text: '全部' },
        { value: 'A1', text: 'A1-水利工程' },
        { value: 'B1', text: 'B1-觀光工程' },
        { value: 'C1', text: 'C1-道路橋梁工程-公路系統工程' },
        { value: 'C2', text: 'C2-道路橋梁工程-村里聯絡道路工程' },
      ],
      type: [
        { value: 'Y', text: '是' },
        { value: 'N', text: '否' },
      ],
      arrRadio: [
        { text: 'Y' },
        { text: 'Y' },
        { text: 'Y' },
        { text: 'Y' },
        { text: 'Y' },
        { text: 'Y' },
        { text: 'Y' },
        { text: 'Y' },
      ],
      constItemName: [
        { value: '', text: '請選擇' },
        { value: '1', text: '道路' },
        { value: '2', text: '基樁' },
        { value: '3', text: '排水溝' },
      ],
      constModelName: [
        { value: '', text: '請選擇' },
        { value: '1', text: '15cm厚混泥土路面' },
        { value: '2', text: 'L1型預鑄溝蓋板(100*90cm)' },
        { value: '3', text: '45公分厚混泥土' },
        { value: '4', text: '15公分厚混泥土' },
      ],
    });

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //是否要重新查詢
    const isReload: boolean = ref(null);
    //modal物件
    const $bvModal = useBvModal();

    const recProjectNoProp = recProjectProp.value['projectNo'];

    //轉到編輯頁面
    const toEditView = (recUserProjectCode: RecUserProjectCode) => {
      navigateByNameAndParams('rec0311U1-edit', {
        formStatusForward: 'modify', isNotKeepAlive: true
      });
    };

    //返回查詢畫面
    const toQueryView = () => {
      handleBack({ isReload: isReload.value, isNotKeepAlive: true });
      isReload.value = false;
    };

    //創建初始值
    const createDefaultValue = (data: RecProject) => {
      Object.assign(formDefault, data);
      reset();
    };

    //變更表單操作狀態，當form狀態不為查看時重置
    const changeFormStatus = (status: formStatusEnum) => {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    const changeFormStatus = (status: formStatusEnum) => {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    //修改表單狀態
    const changeformStatusForwardPropEdit = () => {
      formStatusRef.value = formStatusEnum.MODIFY;
    };

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;

    //新增&修改送出前檢查訊息
    const submitForm = (submitMethod: string) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (submitMethod === 'modify') {
            //提交修改
            // putForm();
            toQueryView()
            $bvModal.msgBoxOk('資料異動成功');
          }
          if (submitMethod === 'create') {
            //提交新增
            insertProject(true);
            $bvModal.msgBoxOk('資料新增成功');
            changeFormStatus(formStatusEnum.MODIFY); //改成修改按鈕
          }
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //繼續新增送出前檢查
    const submitFormNext = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //提交新增
          insertProject(false);
          $bvModal.msgBoxOk('資料新增成功');
          changeFormStatus(formStatusEnum.CREATE);
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //修改
    const putForm = () => {
      form.updateUser = userIdentity;
      form.updateTime = new Date();
      let url: string;
      if (formStatusRef.value === formStatusEnum.MODIFY) {
        url = `/rec-projects/${form.projectNo}`;
      }
      axios
        .put(url, form)
        .then(({ data }) => {
          //填入值
          createDefaultValue(data);
          isReload.value = true;
        })
        .then(toQueryView())
        .finally()
        .catch(notificationErrorHandler(notificationService));
    };

    //刪除
    const deleteProject = () => {
      $bvModal.msgBoxConfirm('刪除此工程將連同相關基本資料、災損圖片、概念設計示意圖說文件、復建工項等資料一併刪除，確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          let url: string;
          url = `/rec-projects/${form.projectNo}`;
          axios
            .delete(url)
            .then(toQueryView())
            .then($bvModal.msgBoxOk('資料刪除成功'))
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    //新增
    const insertProject = (insertDone: boolean) => {
      //4個系統用欄位
      form.createUser = userIdentity;
      form.updateUser = userIdentity;
      form.createTime = new Date();
      form.updateTime = new Date();
      axios
        .post(`/rec-projects`, form)
        .then(({ data }) => {
          isReload.value = true;
          if (insertDone) {
            createDefaultValue(data); //不再新增時將值放入
          }
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    };


    //傳入狀態
    const formStatusForwardProp = toRefs(props).formStatusForward;

    watch(
      recProjectProp,
      () => {
        if (formStatusForwardProp.value === 'modify') {
          createDefaultValue(recProjectProp.value);
          changeFormStatus(formStatusEnum.MODIFY);
        } else {
          Object.assign(formDefault, cleanTable);
          Object.assign(form, formDefault);
          createDefaultValue(form);
          changeFormStatus(formStatusEnum.CREATE);
        }
      },
      { immediate: true },
    );

    watch(queryOptions.arrRadio,()=>{
      // for (let i=0;i<queryOptions.arrRadio.length;i++){
      //   if (queryOptions.arrRadio[i].text==='N') {
      //     $bvModal.msgBoxConfirm('檢核結果不符中央補助原則，請本於權責自籌經費辦理。').then((isOK: boolean) => {
      //       if (isOK) {
      //         toQueryView();
      //         for (let i=0;i<queryOptions.arrRadio.length;i++){
      //           queryOptions.arrRadio[i].text='Y';
      //         }
      //       } else {
      //         for (let i=0;i<queryOptions.arrRadio.length;i++){
      //           queryOptions.arrRadio[i].text='Y';
      //         }
      //       }
      //     })
      //   }
      // }
    }
    );

    const getRefPrice = (itemSelected: string) => {
      //為重新渲染所以先改為false
      refPriceVisible.value = false;
      let postUrl;
      if (itemSelected != '0') {
        refPriceVisible.value = true;
      }
    };

    const unitPrice = ref(null);
    const unit = ref(null);

    const getUnitPrice = (unitPriceValue: string, unitValue: String) => {
      unitPrice.value = unitPriceValue;
      unit.value = unitValue;
    };


    return {
      $v,
      editVisible,
      addVisible,
      refPriceVisible,
      formStatusEnum,
      formStatusRef,
      changeFormStatus,
      reset,
      validateState,
      toQueryView,
      submitForm,
      submitFormNext,
      queryOptions,
      deleteProject,
      formStatusForwardProp,
      userIdentity,
      recProjectProp,
      recProjectNoProp,
      getRefPrice,
      getUnitPrice,
      unitPrice,
      unit
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

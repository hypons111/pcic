<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">基本設計審查</h5>
        </div>
        <!-- 申請資料1 -->
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="$t('label.isForwardPlan') + '：'">
              {{ $v.isForwardPlan.$model ? '是' : '否' }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.caseNa') + '：'" :item="$v.caseName">
              <b-form-input v-model="$v.caseName.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.caseNum') + '：'" :item="$v.caseNum">
              <b-form-input v-model="$v.caseNum.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.reviewCategory') + '：'" :item="$v.reviewType">
              <b-form-input v-model="$v.reviewType.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.classify') + '：'" :item="$v.classify">
              <b-form-input v-model="$v.classify.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.planType') + '：'" :item="$v.planType">
              <b-form-input v-model="$v.planType.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.totalBudget') + '：'" :item="$v.totalBudget">
              <b-form-input v-model="$v.totalBudget.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.govApprovalNo') + '：'" :item="$v.govApprovalNo">
              <b-form-input v-model="$v.govApprovalNo.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.govApprovalDate') + '：'" :item="$v.govApprovalDate">
              <b-form-input v-model="$v.govApprovalDate.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.masterPlan') + '：'" :item="$v.masterPlanName">
              <b-form-input v-model="$v.masterPlanName.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.reservePlanNo') + '：'" :item="$v.reservePlanNo">
              <b-form-input v-model="$v.reservePlanNo.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>

        <!-- 申請資料2 -->
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.bos') + '：'" :item="$v.adminOrg">
              <b-form-input v-model="$v.adminOrg.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.dept') + '：'" :item="$v.org">
              <b-form-input v-model="$v.org.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.planContact') + '：'" :item="$v.contactPerson">
              <b-form-input v-model="$v.contactPerson.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.tel') + '：'" :item="$v.contactTel">
              <b-form-input v-model="$v.contactTel.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.contactEmail') + '：'" :item="$v.contactEmail">
              <b-form-input v-model="$v.contactEmail.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.planDesc') + '：'" :item="$v.planDesc">
              <b-form-input v-model="$v.planDesc.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.planStartDate') + '：'" :item="$v.planStartDate">
              <b-form-input v-model="$v.planStartDate.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.planEndDate') + '：'" :item="$v.planEndDate">
              <b-form-input v-model="$v.planEndDate.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :label="$t('label.planManual') + '：'"
              :item="$v.planManual"
            >
              <b-form-input class="col-8" v-model="$v.planManual.$model" disabled> </b-form-input>
              <b-button-toolbar class="col-4"><i-button class="ml-1" type="binoculars"></i-button></b-button-toolbar>
            </i-form-group-check>
          </b-form-row>
        </div>

        <div class="card-header mt-5">
          <h5 class="m-0">填報屬工程建設經費</h5>
        </div>
        <div class="card-body col-12">
          <b-table-simple table-class="text-middle" bordered>
            <b-tbody>
              <b-tr>
                <b-th>用地取得及及拆遷補償費(萬元)</b-th>
                <b-td colspan="5">11,061</b-td>
              </b-tr>
              <b-tr>
                <b-th rowspan="17" class="text-meddle">工程建造費(萬元)</b-th>
                <b-td colspan="2">直接工程成本</b-td>
                <b-td colspan="3">34,481</b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="14">間接工程成本</b-td>
                <b-td rowspan="2">工程管理費</b-td>
                <b-td colspan="3">280</b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3">0.81%</b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="2">工程設計監造費</b-td>
                <b-td colspan="3">2,302</b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3">6.68%</b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="2">階段性專案管理及顧問費</b-td>
                <b-td colspan="3">0</b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3">0.00%</b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="2">環境監測費</b-td>
                <b-td colspan="3">0</b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3">0.00%</b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="2">空氣污染防制費</b-td>
                <b-td colspan="3">129</b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3">0.37%</b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="2">工程保險費</b-td>
                <b-td colspan="3">0</b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3">0.00%</b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="2">其他</b-td>
                <b-td>1,040</b-td>
                <b-td rowspan="2">用途填寫</b-td>
                <b-td rowspan="2">鄰房鑑定費及其他費用</b-td>
              </b-tr>
              <b-tr>
                <b-td><b-form-input> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="2">工程預備費</b-td>
                <b-td colspan="3">345</b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="2">物價調整費</b-td>
                <b-td colspan="3">227</b-td>
              </b-tr>
              <b-tr>
                <b-th colspan="3">工程建造總經費(萬元)</b-th>
                <b-td colspan="3">49,865</b-td>
              </b-tr>
              <b-tr>
                <b-th>其他費用(萬元)</b-th>
                <b-td>0</b-td>
                <b-td>用途填寫</b-td>
                <b-td colspan="3"></b-td>
              </b-tr>
              <b-tr>
                <b-th>屬本會審議範圍總經費(萬元)</b-th>
                <b-td>38,804 (只含工程建造費)</b-td>
                <b-td>上傳預算表(或經費明細表)</b-td>
                <b-td colspan="3"><a href>(預算書)整建工程 B1091005_ap_bdgt.xml</a></b-td>
              </b-tr>
            </b-tbody>
          </b-table-simple>
        </div>

        <div class="card-header mt-5">
          <h5 class="m-0">工程單位造價</h5>
        </div>
        <div class="card-body col-12">
          <b-table-simple table-class="text-middle" bordered>
            <b-tbody>
              <b-tr>
                <b-td style="width: 30%">區域名稱：高速公路新店段</b-td>
                <b-td>
                  <b-table-simple table-class=" text-middle" bordered>
                    <b-thead>
                      <b-tr>
                        <b-th class="text-center" style="width: 30%">工程項目</b-th>
                        <b-th class="text-center">經費(萬元)</b-th>
                        <b-th class="text-center">數量</b-th>
                        <b-th class="text-center">單位</b-th>
                        <b-th class="text-center">單價(萬元)</b-th>
                        <b-th class="text-center">備註</b-th>
                      </b-tr>
                    </b-thead>
                    <b-tbody>
                      <b-tr>
                        <b-td>公路-公(道)路高架橋樑-引道工程</b-td>
                        <b-td>1,000</b-td>
                        <b-td>5</b-td>
                        <b-td>m2</b-td>
                        <b-td>200</b-td>
                        <b-td></b-td>
                      </b-tr>
                      <b-tr>
                        <b-td>加總</b-td>
                        <b-td>0</b-td>
                        <b-td></b-td>
                        <b-td></b-td>
                        <b-td></b-td>
                        <b-td></b-td>
                      </b-tr>
                    </b-tbody>
                  </b-table-simple>
                </b-td>
              </b-tr>
              <b-tr><b-td>總長度</b-td><b-td colspan="'6">0</b-td></b-tr>
            </b-tbody>
          </b-table-simple>
        </div>

        <div class="card-header">
          <h5 class="m-0">替選方案評估(函報總經費達10億元者專用)</h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="$t('label.totalAlte') + '：'">
              <b-form-input disabled> 1,770,300</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="$t('label.totalSaving') + '：'">
              <b-form-input disabled> 261,600</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" label="替選方案清單：">
              <a href>替選方案評估自我檢視表.pdf</a>
            </i-form-group-check>
          </b-form-row>
        </div>

        <!-- 特殊備註 -->
        <div class="card-header">
          <h5 class="m-0">特殊備註</h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.specialNote') + '：'"
              :item="$v.specialNote"
            >
              <b-form-textarea rows="3" v-model="$v.specialNote.$model" disabled></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
        </div>

        <!-- 審查畫面-審查資訊 -->
        <div class="card-header">
          <h5 class="m-0">審查資訊</h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.projectUndertaker') + '：'" :item="$v.contactPerson">
              <b-form-input v-model="$v.contactPerson.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.contactInformationTel') + '：'" :item="$v.contactTel">
              <b-form-input v-model="$v.contactTel.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.contactEmail') + '：'" :item="$v.contactEmail">
              <b-form-input v-model="$v.contactEmail.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check> </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.reviewFile') + '：'" :item="$v.reviewFile">
              <b-form-file
                v-model.trim="$v.reviewFile.$model"
                browse-text="選擇檔案"
                :placeholder="$v.reviewFile.$model ? $v.reviewFile.name : ''"
              >
              </b-form-file>
            </i-form-group-check>
            <i-form-group-check> <b-button variant="info">上傳</b-button></i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.reviewFile') + '：'"
              :item="$v.reviewFile"
            >
              <b-form-input class="col-8" v-model="$v.reviewFile.$model" disabled> </b-form-input>
              <b-button-toolbar class="col-4">
                <i-button class="ml-1" type="binoculars"></i-button>
                <i-button class="ml-1" type="trash"></i-button>
              </b-button-toolbar>
            </i-form-group-check>
          </b-form-row>
        </div>

        <!-- 審查畫面-工程會審議 -->
        <div class="card-header mt-5">
          <h5 class="m-0">工程會審議</h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.receiptDate') + '：'" :item="$v.receiptDate" :labelStar="true">
              <b-form-input v-model="$v.receiptDate.$model"> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.receiptNo') + '：'" :item="$v.receiptNo" :labelStar="true">
              <b-form-input v-model="$v.receiptNo.$model"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.reviewTotalBudget') + '：'" :item="$v.reviewTotalBudget" :labelStar="true">
              <b-form-input v-model="$v.reviewTotalBudget.$model"> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.approvalBudget') + '：'" :item="$v.approvalBudget" :labelStar="true">
              <b-form-input v-model="$v.approvalBudget.$model"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="$t('label.comment') + '：'" :item="$v.specialNote">
              <b-form-textarea rows="3" v-model="$v.comment.$model"></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.specialNote') + '：'"
              :item="$v.specialNote"
            >
              <b-form-input v-model="$v.specialNote.$model"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.reviewHistory') + '：'"
              :item="$v.reviewHistory"
            >
              <b-form-input v-model="$v.reviewHistory.$model"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>

        <!-- 審查畫面-缺失清單 -->
        <div class="card-header">
          <h5 class="m-0">缺失清單</h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" :label="$t('label.defectItems') + '：'">
              A. 程序面缺失
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(A) 未依原預定期程送審</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(B) 送審前該個案工程已發包</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(C) 非屬應送本會審議之案件</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(D) 計畫程序未完備</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(E) 工程內容與計畫核定內容不符</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(F) 提送資料項目未齊全</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label=""> B. 技術面缺失 </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label=""
              ><b-form-checkbox :true-value="true" :false-value="false">(A) 基本資料調查未詳實</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(B) 工程量體或數量不合理</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(C) 空間或設施配置不當</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(D) 設計規範引用不當或錯誤、工期安排未妥適</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label=""> C. 經費面缺失 </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(A) 經費架構比例不合理</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(B) 單價與數量編列內容未詳實</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(C) 大宗營建材料單價不合理</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label=""> D. 其他缺失 </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(A) 其他</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label=""> E. 無缺失 </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label="">
              <b-form-checkbox :true-value="true" :false-value="false">(A) 本次審議無缺失</b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" label=""> </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label-cols="2" :content-cols="10" class="col-12" :label="$t('label.defectRemark') + '：'">
              <b-form-textarea rows="3"></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
        </div>

        <!-- 審查畫面-審議進度 -->
        <div class="card-header">
          <h5 class="m-0">審議進度</h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.reviewProgress') + '：'" :labelStar="true">
              <b-form-select :options="options.reviewProgress"></b-form-select>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.changeTime') + '：'" :labelStar="true">
              <b-form-input v-model="$v.updateTime.$model" disabled> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.changeUser') + '：'" :labelStar="true">
              <b-form-input v-model="$v.updateUser.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check> </i-form-group-check>
          </b-form-row>

          <div class="button-float-right">
            <b-button-toolbar class="mt-5">
              <i-button class="ml-1" type="check-circle" @click="toQueryView"></i-button>
            </b-button-toolbar>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { FormStatusEnum } from '@/shared/enum';
import { RevMain } from '@/shared/model/revModel/rev-main.model';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'rev0403ReviewInfo',
  //props可接受父元件傳來的物件，裡面可以定義也可以放空物件如xxx:{}
  props: {
    revMain: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const revMainProp = toRefs(props).revMain;

    const notificationService: NotificationService = useNotification();

    // 宣告表單物件及初始值
    const formDefault = {
      //基本資料1
      isForwardPlan: false, //為前瞻計畫
      caseName: '', //案件名稱
      caseNum: '1071C111090000', //案件編號
      reviewType: '', //審議類別
      classify: '', //工程建設類別
      planType: '', //計畫類別
      totalBudget: '4,174,200', //函報總經費(萬元)
      govApprovalNo: '', //院核定文號
      govApprovalDate: '', //院核定日期
      masterPlanName: '', //上位計畫
      reservePlanNo: '', //預定函送案件編號

      //基本資料2
      adminOrg: '', // 主管機關
      org: '', // 主辦機關
      contactPerson: '', // 計畫聯絡人
      contactTel: '02-23657210#2232', // 電話
      contactEmail: 'u284582@test.com', // 電子郵件
      planDesc: '', // 計畫概述
      planStartDate: '109/01/01', // 計畫日期(起)
      planEndDate: '110/12/31', // 計畫日期(訖)
      planManual: null, //計畫書

      specialNote: '', //特殊備註

      isPurcContentCheck: undefined, //發包內容是否與基設一致
      isPurcFundCheck: undefined, //發包經費是否符合基設預算額度內
      reviewFile: null, //審議附件
      receiptDate: '', //本會收文日期
      receiptNo: '', //本會收文號
      reviewTotalBudget: '', //屬本會審議範圍總經費(萬元)
      approvalBudget: '', //本會核定經費(萬元)
      comment: '', //審議意見
      reviewHistory: '', //審議歷程

      reviewProgress: [0], //審議進度
      updateTime: '', //異動時間
      updateUser: '', //異動人員
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      isForwardPlan: {},
      caseName: { notnull: required },
      caseNum: {},
      reviewType: { notnull: required },
      classify: { notnull: required },
      planType: { notnull: required },
      totalBudget: { notnull: required },
      govApprovalNo: {},
      govApprovalDate: {},
      masterPlanName: {},
      reservePlanNo: {},

      adminOrg: {},
      org: {},
      contactPerson: {},
      contactTel: {},
      contactEmail: {},
      planDesc: {},
      planStartDate: {},
      planEndDate: {},
      planManual: {},

      specialNote: {},

      isPurcContentCheck: {},
      isPurcFundCheck: {},
      reviewFile: {},

      receiptDate: { notnull: required },
      receiptNo: { notnull: required },
      reviewTotalBudget: { notnull: required },
      approvalBudget: { notnull: required },
      comment: {},
      reviewHistory: {},

      reviewProgress: { notnull: required },
      updateTime: {},
      updateUser: {},
    };
    //checkValidity用來檢核，reset用來重置form
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 下拉選單選項
    const options = reactive({
      reviewProgress: [
        { value: '', text: '審查中' },
        { value: '5', text: '審查完成' },
        { value: '2', text: '需補件' },
        { value: '3', text: '函退' },
      ],
    });

    // 是否需要重新查詢
    const isReload = ref(false);

    const $bvModal = useBvModal();

    const toQueryView = () => {
      handleBack({ isReload: isReload.value, isNotKeepAlive: true });
      isReload.value = false;
    };

    const createDefaultValue = (data: RevMain) => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
    };

    watch(
      revMainProp,
      () => {
        createDefaultValue(revMainProp.value);
      },
      { immediate: true }
    );

    return {
      $v,
      options,
      toQueryView,
      validateState,
      revMainProp,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

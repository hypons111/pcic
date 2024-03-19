<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0" v-if="formStatusForwardProp === formStatusEnum.MODIFY">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            編輯預定函送登打資料
          </h5>
          <h5 class="m-0" v-else-if="formStatusForwardProp === formStatusEnum.CREATE">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            新增預定函送登打資料
          </h5>
          <h5 class="m-0" v-else-if="formStatusForwardProp === formStatusEnum.INFO">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視預定函送登打資料
          </h5>
        </div>
        <!-- 基本資料1 -->
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.isForwardPlan') + '：'" :labelStar="true">
              <b-form-checkbox :true-value="true" :false-value="false" :state="validateState($v.isForwardPlan)"></b-form-checkbox>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.caseNa') + '：'" :item="$v.caseName" :labelStar="true">
              <b-form-input
                v-model="$v.caseName.$model"
                :state="validateState($v.caseName)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              >
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.caseNum') + '：'" :item="$v.caseNum">
              <b-form-input v-model="$v.caseNum.$model" disabled> </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.reviewCategory') + '：'" :item="$v.reviewType">
              <b-form-select :options="options.reviewType" v-model="$v.reviewType.$model" disabled></b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.classify') + '：'" :item="$v.classify" :labelStar="true">
              <b-form-select
                :options="options.classify"
                v-model="$v.classify.$model"
                :state="validateState($v.classify)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              ></b-form-select>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.planType') + '：'" :item="$v.planType" :labelStar="true">
              <b-form-select
                :options="options.planType"
                v-model="$v.planType.$model"
                :state="validateState($v.planType)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              ></b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.totalBudget') + '：'" :item="$v.totalBudget" :labelStar="true">
              <b-form-input
                v-model="$v.totalBudget.$model"
                :state="validateState($v.totalBudget)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              >
              </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.govApprovalNo') + '：'" :item="$v.govApprovalNo" :labelStar="true">
              <b-form-input
                v-model="$v.govApprovalNo.$model"
                :state="validateState($v.govApprovalNo)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              >
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.govApprovalDate') + '：'" :item="$v.govApprovalDate" :labelStar="true">
              <i-date-picker
                v-model="$v.govApprovalDate.$model"
                :state="validateState($v.govApprovalDate)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              ></i-date-picker>
            </i-form-group-check>

            <i-form-group-check></i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.masterPlan') + '：'" :item="$v.masterPlanName" :labelStar="true">
              <b-form-input
                v-model="$v.masterPlanName.$model"
                :state="validateState($v.masterPlanName)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              >
              </b-form-input>
            </i-form-group-check>
            <i-form-group-check>
              <i-button class="mt-1 ml-1" type="search" :disabled="formStatusForwardProp === formStatusEnum.INFO"></i-button>
              <i-button
                class="mt-1 ml-1"
                type="x-circle"
                @click="reset"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              ></i-button>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.reservePlanNo') + '：'" :item="$v.reservePlanNo" :labelStar="true">
              <b-form-input
                v-model="$v.reservePlanNo.$model"
                :state="validateState($v.reservePlanNo)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              >
              </b-form-input>
            </i-form-group-check>
            <i-form-group-check></i-form-group-check>
          </b-form-row>
        </div>

        <!-- 基本資料2 -->
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check :label="$t('label.bos') + '：'" :item="$v.adminOrg" :labelStar="true">
              <b-form-select
                :options="options.adminOrg"
                v-model="$v.adminOrg.$model"
                :state="validateState($v.adminOrg)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              ></b-form-select>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.dept') + '：'" :item="$v.org" :labelStar="true">
              <b-form-select
                :options="options.org"
                v-model="$v.org.$model"
                :state="validateState($v.org)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              ></b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.planContact') + '：'" :item="$v.contactPerson" :labelStar="true">
              <b-form-select
                :options="options.contactPerson"
                v-model="$v.contactPerson.$model"
                :state="validateState($v.contactPerson)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              ></b-form-select>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.tel') + '：'" :item="$v.contactTel" :labelStar="true">
              <b-form-input
                v-model="$v.contactTel.$model"
                :state="validateState($v.contactTel)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              >
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.contactEmail') + '：'" :item="$v.contactEmail" :labelStar="true">
              <b-form-input
                v-model="$v.contactEmail.$model"
                :state="validateState($v.contactEmail)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              >
              </b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.planDesc') + '：'" :item="$v.planDesc" :labelStar="true">
              <b-form-input
                v-model="$v.planDesc.$model"
                :state="validateState($v.planDesc)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              >
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="$t('label.planStartDate') + '：'" :item="$v.planStartDate" :labelStar="true">
              <i-date-picker
                v-model="$v.planStartDate.$model"
                :state="validateState($v.planStartDate)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              ></i-date-picker>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.planEndDate') + '：'" :item="$v.planEndDate" :labelStar="true">
              <i-date-picker
                v-model="$v.planEndDate.$model"
                :state="validateState($v.planEndDate)"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.planManual') + '：'"
              :item="$v.planManual"
            >
              <b-form-file
                class="col-8"
                v-model.trim="$v.planManual.$model"
                browse-text="選擇檔案"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              ></b-form-file>
              <b-button-toolbar class="col-4"><b-button variant="info">上傳</b-button></b-button-toolbar>
            </i-form-group-check>
          </b-form-row>
          <b-form-row class="mt-3">
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="'計畫書清單：'">
              <b-form-input class="col-8" disabled> </b-form-input>
              <b-button-toolbar class="col-4">
                <i-button class="ml-1" type="binoculars"></i-button>
                <i-button class="ml-1" type="trash"></i-button>
              </b-button-toolbar>
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
                <b-td colspan="5"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-th rowspan="17" class="text-meddle">工程建造費(萬元)</b-th>
                <b-td colspan="2">直接工程成本</b-td>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="14">間接工程成本</b-td>
                <b-td rowspan="2">工程管理費</b-td>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="2">工程設計監造費</b-td>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="2">階段性專案管理及顧問費</b-td>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="2">環境監測費</b-td>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="2">空氣污染防制費</b-td>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="2">工程保險費</b-td>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td rowspan="2">其他</b-td>
                <b-td><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
                <b-td rowspan="2">用途填寫</b-td>
                <b-td rowspan="2"
                  ><b-form-textarea rows="3" :disabled="formStatusForwardProp === formStatusEnum.INFO"></b-form-textarea
                ></b-td>
              </b-tr>
              <b-tr>
                <b-td><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="2">工程預備費</b-td>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="2">物價調整費</b-td>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-th colspan="3">工程建造總經費(萬元)</b-th>
                <b-td colspan="3"><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
              </b-tr>
              <b-tr>
                <b-th>其他費用(萬元)</b-th>
                <b-td><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
                <b-td>用途填寫</b-td>
                <b-td colspan="3"
                  ><b-form-textarea rows="3" :disabled="formStatusForwardProp === formStatusEnum.INFO"></b-form-textarea
                ></b-td>
              </b-tr>
              <b-tr>
                <b-th>屬本會審議範圍總經費(萬元)</b-th>
                <b-td><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"> </b-form-input></b-td>
                <b-td>上傳預算表(或經費明細表)</b-td>
                <b-td colspan="3"
                  ><b-form-file browse-text="選擇檔案" :disabled="formStatusForwardProp === formStatusEnum.INFO"></b-form-file>
                  <b-button variant="info">上傳</b-button></b-td
                >
              </b-tr>
            </b-tbody>
          </b-table-simple>

          <b-form-row class="mt-5">
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" label="預算表清單：">
              <b-form-input class="col-8" disabled> </b-form-input>
              <b-button-toolbar class="col-4">
                <i-button class="ml-1" type="binoculars"></i-button>
                <i-button class="ml-1" type="trash"></i-button>
              </b-button-toolbar>
            </i-form-group-check>
          </b-form-row>
        </div>

        <div class="card-header mt-5">
          <h5 class="m-0">工程單位造價</h5>
        </div>
        <div class="card-body col-12">
          <b-button variant="info" :disabled="formStatusForwardProp === formStatusEnum.INFO">增加單位造價</b-button>

          <template>
            <b-table-simple table-class="text-middle" bordered ref="unitPriceGroup">
              <b-tbody>
                <b-tr>
                  <b-td style="width: 30%"
                    >區域名稱：<b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"></b-form-input
                  ></b-td>
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
                          <b-td><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"></b-form-input></b-td>
                          <b-td><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"></b-form-input></b-td>
                          <b-td><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"></b-form-input></b-td>
                          <b-td><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"></b-form-input></b-td>
                          <b-td><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"></b-form-input></b-td>
                          <b-td><b-form-input :disabled="formStatusForwardProp === formStatusEnum.INFO"></b-form-input></b-td>
                        </b-tr>
                      </b-tbody>
                    </b-table-simple>
                    <!--編輯按鈕 -->
                    <div class="button-float-right">
                      <b-button-toolbar class="mt-1">
                        <i-button class="ml-1" type="pencil-square" v-b-modal.unit-price-group-modal></i-button>
                      </b-button-toolbar>
                    </div>
                  </b-td>
                </b-tr>
              </b-tbody>
            </b-table-simple>
          </template>

          <!-- 工程單位造價編輯 modal -->
          <b-modal
            id="unit-price-group-modal"
            size="xl"
            title="工程單位造價設定"
            header-bg-variant="secondary"
            header-text-variant="light"
            hide-footer
          >
            <template>
              <div class="card-body col-12">
                <b-form-row>
                  <!-- 案件類別dropdown -->
                  <i-form-group-check :label="$t('label.caseCategory') + '：'" :item="$v.caseCategory" :labelStar="true">
                    <i-group-select-checkbox
                      v-model="$v.caseCategory.$model"
                      :options="options.caseCategoryList"
                      :isDropdown="true"
                    ></i-group-select-checkbox>
                  </i-form-group-check>

                  <!-- 工程項目dropdown -->
                  <i-form-group-check :label="$t('label.projectItems') + '：'" :item="$v.projectItems" :labelStar="true">
                    <i-group-select-checkbox
                      v-model="$v.projectItems.$model"
                      :options="options.projectItemsList"
                      :isDropdown="true"
                    ></i-group-select-checkbox>
                  </i-form-group-check>
                </b-form-row>
                <b-button-toolbar class="mt-3 mb-3">
                  <i-button type="file-earmark-plus" @click="addContentColumn"></i-button>
                </b-button-toolbar>

                <b-table-simple table-class=" text-middle" bordered v-if="unitPriceVisible">
                  <b-thead>
                    <b-th class="text-center" style="width: 30%">工程項目</b-th>
                    <b-th class="text-center">經費(萬元)</b-th>
                    <b-th class="text-center">數量</b-th>
                    <b-th class="text-center">單位</b-th>
                    <b-th class="text-center">單價(萬元)</b-th>
                    <b-th class="text-center">備註</b-th>
                    <b-th class="text-center">動作</b-th>
                  </b-thead>
                  <b-tbody>
                    <b-td><b-form-input></b-form-input></b-td>
                    <b-td><b-form-input></b-form-input></b-td>
                    <b-td><b-form-input></b-form-input></b-td>
                    <b-td><b-form-input></b-form-input></b-td>
                    <b-td><b-form-input></b-form-input></b-td>
                    <b-td><b-form-input></b-form-input></b-td>
                    <b-td><!--<i-button class="ml-1" type="trash"></i-button>--></b-td>
                  </b-tbody>
                </b-table-simple>

                <div class="button-float-right">
                  <b-button-toolbar class="mt-5">
                    <i-button class="ml-1" type="save" @click="$bvModals.hide('valid-error-modal')"></i-button>
                    <i-button class="ml-1" type="arrow-counterclockwise" @click="$bvModal.hide('unit-price-group-modal')"></i-button>
                  </b-button-toolbar>
                </div>
              </div>
            </template>
          </b-modal>
        </div>

        <div class="card-header mt-5">
          <h5 class="m-0">替選方案評估(函報總經費達10億元者專用)</h5>
        </div>
        <div class="card-body col-10">
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.totalAlte') + '：'"
              :disabled="formStatusForwardProp === formStatusEnum.INFO"
            >
              <b-form-input> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.totalSaving') + '：'"
              :disabled="formStatusForwardProp === formStatusEnum.INFO"
            >
              <b-form-input> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="'上傳替選方案：'">
              <b-form-file class="col-8" browse-text="選擇檔案" :disabled="formStatusForwardProp === formStatusEnum.INFO"></b-form-file>
              <b-button-toolbar class="col-4"
                ><b-button variant="info" :disabled="formStatusForwardProp === formStatusEnum.INFO">上傳</b-button></b-button-toolbar
              >
            </i-form-group-check>
          </b-form-row>
          <b-form-row class="mt-5">
            <i-form-group-check class="col-12" label-cols="2" content-cols="10" label="替選方案清單：">
              <b-form-input class="col-8" disabled> </b-form-input>
              <b-button-toolbar class="col-4">
                <i-button class="ml-1" type="binoculars"></i-button>
                <i-button class="ml-1" type="trash" :disabled="formStatusForwardProp === formStatusEnum.INFO"></i-button>
              </b-button-toolbar>
            </i-form-group-check>
          </b-form-row>
        </div>

        <!-- 特殊備註 -->
        <div class="card-header mt-5">
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
              <b-form-textarea
                rows="3"
                v-model="$v.specialNote.$model"
                :disabled="formStatusForwardProp === formStatusEnum.INFO"
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <div class="button-float-right">
            <b-button-toolbar class="mt-5" v-if="formStatusForwardProp === formStatusEnum.MODIFY">
              <i-button class="ml-1" type="save" @click="submitForm('modify')"></i-button>
              <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
              <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
              <i-button class="ml-1" type="trash" @click="deleteRevMain"></i-button>
              <b-button class="ml-1" variant="info" @click="toQueryView">送出</b-button>
            </b-button-toolbar>
            <b-button-toolbar class="mt-5" v-else-if="formStatusForwardProp === formStatusEnum.CREATE">
              <i-button class="ml-1" type="plus-circle" @click="submitFormNext"></i-button>
              <i-button class="ml-1" type="save" @click="submitForm('create')"></i-button>
              <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
              <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
            </b-button-toolbar>
          </div>
        </div>

        <!-- 檢視畫面 -->
        <div v-if="formStatusForwardProp === formStatusEnum.INFO">
          <!-- 檢視畫面-發包前檢核 -->
          <div class="card-header">
            <h5 class="m-0">發包前檢核</h5>
          </div>
          <div class="card-body col-10">
            <b-form-row>
              <i-form-group-check label="" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
                <b-form-checkbox :true-value="true" :false-value="false" v-model="$v.isPurcContentCheck.$model"
                  >發包內容是否與基設一致</b-form-checkbox
                >
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
                <b-form-checkbox :true-value="true" :false-value="false" v-model="$v.isPurcFundCheck.$model"
                  >發包經費是否符合基設預算額度內</b-form-checkbox
                >
              </i-form-group-check>
            </b-form-row>

            <div class="button-float-right">
              <b-button-toolbar class="mt-1">
                <i-button class="ml-1" type="save"></i-button>
                <b-button class="ml-1" variant="info">送出</b-button>
              </b-button-toolbar>
            </div>
          </div>

          <!-- 檢視畫面-審查資訊 -->
          <div class="card-header mt-5">
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
                </b-button-toolbar>
              </i-form-group-check>
            </b-form-row>
          </div>

          <!-- 檢視畫面-工程會審議 -->
          <div class="card-header">
            <h5 class="m-0">工程會審議</h5>
          </div>
          <div class="card-body col-10">
            <b-form-row>
              <i-form-group-check :label="$t('label.receiptDate') + '：'" :item="$v.receiptDate">
                <b-form-input disabled> </b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.receiptNo') + '：'" :item="$v.receiptNo">
                <b-form-input disabled> </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.reviewTotalBudget') + '：'" :item="$v.reviewTotalBudget">
                <b-form-input disabled> </b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.approvalBudget') + '：'" :item="$v.approvalBudget">
                <b-form-input disabled> </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.comment') + '：'" :item="$v.comment">
                <b-form-input disabled> </b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.defectRecord') + '：'" :item="$v.defectRecord">
                <b-form-input disabled> </b-form-input>
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
                <b-form-input disabled> </b-form-input>
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
                <b-form-input class="col-8" disabled> </b-form-input>
                <b-button-toolbar class="col-4">
                  <i-button class="ml-1" type="binoculars"></i-button>
                </b-button-toolbar>
              </i-form-group-check>
            </b-form-row>
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
  name: 'rev0402EditInfo',
  //props可接受父元件傳來的物件，裡面可以定義也可以放空物件如xxx:{}
  props: {
    formStatusForward: {
      type: String,
      required: true,
    },
    revMain: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const revMainProp = toRefs(props).revMain;

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    //傳入狀態
    const formStatusForwardProp = toRefs(props).formStatusForward;

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    const notificationService: NotificationService = useNotification();

    // 宣告表單物件及初始值
    const formDefault = {
      //基本資料1
      isForwardPlan: undefined, //是否為前瞻計畫

      caseName: '', //案件名稱
      caseNum: '1071C111090000', //案件編號
      reviewType: [], //審議類別
      classify: [], //工程建設類別
      planType: [], //計畫類別
      totalBudget: '', //函報總經費(萬元)
      govApprovalNo: '', //院核定文號
      govApprovalDate: '', //院核定日期
      masterPlanName: '', //上位計畫
      reservePlanNo: '', //預定函送案件編號

      //基本資料2
      adminOrg: '', // 主管機關
      org: '', // 主辦機關
      contactPerson: '', // 計畫聯絡人
      contactTel: '', // 電話
      contactEmail: '', // 電子郵件
      planDesc: '', // 計畫概述
      planStartDate: undefined, // 計畫日期(起)
      planEndDate: undefined, // 計畫日期(訖)
      planManual: null, //計劃書

      specialNote: '', //特殊備註

      isPurcContentCheck: undefined, //發包內容是否與基設一致
      isPurcFundCheck: undefined, //發包經費是否符合基設預算額度內
      reviewFile: '', //審議附件
      receiptDate: undefined, //本會收文日期
      receiptNo: '', //本會收文號
      reviewTotalBudget: '', //屬本會審議範圍總經費(萬元)
      approvalBudget: '', //本會核定經費(萬元)
      comment: '', //審議意見
      defectRecord: '', //整體審議過程中發現缺失
      reviewHistory: null, //審議歷程

      caseCategory: null, //案件類別
      projectItems: null, //工程項目
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      isForwardPlan: { notnull: required },
      caseName: { notnull: required },
      caseNum: {},
      reviewType: { notnull: required },
      classify: { notnull: required },
      planType: { notnull: required },
      totalBudget: { notnull: required },
      govApprovalNo: { notnull: required },
      govApprovalDate: { notnull: required },
      masterPlanName: { notnull: required },
      reservePlanNo: { notnull: required },

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

      caseCategory: { notnull: required },
      projectItems: { notnull: required },
    };
    //checkValidity用來檢核，reset用來重置form
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 下拉選單選項
    const options = reactive({
      caseCategoryList: [],
      projectItemsList: [],
      reviewType: [
        { value: '', text: '請選擇' },
        { value: '1', text: '基本設計' },
      ],
      classify: [
        { value: '', text: '請選擇' },
        { value: 'A01', text: '公路' },
        { value: 'A02', text: '航道運輸' },
        { value: 'A03', text: '航空' },
        { value: 'A04', text: '港埠' },
        { value: 'A05', text: '觀光' },
        { value: 'B01', text: '環境保護' },
        { value: 'B02', text: '水利建設' },
        { value: 'B03', text: '下水道' },
        { value: 'B04', text: '國家公園' },
        { value: 'C01', text: '工商設施' },
        { value: 'C02', text: '油電' },
        { value: 'D01', text: '都市開發' },
        { value: 'E01', text: '文化' },
        { value: 'F01', text: '教育' },
        { value: 'F02', text: '體育' },
        { value: 'G01', text: '農業建設' },
        { value: 'H01', text: '衛生福利' },
      ],
      planType: [
        { value: '', text: '請選擇' },
        { value: '1', text: '公共建設計畫' },
      ],

      adminOrg: [
        { value: '', text: '請選擇' },
        { value: '1', text: '內政部' },
        { value: '1', text: '外交部' },
        { value: '1', text: '國防部' },
        { value: '1', text: '財政部' },
        { value: '1', text: '教育部' },
        { value: '1', text: '法務部' },
        { value: '1', text: '經濟及能源部' },
        { value: '1', text: '交通及建設部' },
        { value: '1', text: '勞動部' },
        { value: '1', text: '農業部' },
        { value: '1', text: '衛生福利部' },
        { value: '1', text: '環境資源部' },
        { value: '1', text: '文化部' },
        { value: '1', text: '科技部' },
      ],
      org: [
        { value: '', text: '請選擇' },
        { value: '1', text: 'orgName' },
      ],
      contactPerson: [
        { value: '', text: '請選擇' },
        { value: '1', text: '林宜楷' },
        { value: '2', text: '楊博淵' },
        { value: '3', text: '劉欽明' },
        { value: '4', text: '劉建位' },
        { value: '5', text: '鮑道訓' },
        { value: '6', text: '李文欽' },
        { value: '7', text: '林延俊' },
        { value: '8', text: '王國書' },
        { value: '9', text: '張巍霈' },
        { value: '10', text: '陳紀勛' },
        { value: '11', text: '林清煌' },
        { value: '12', text: '王國書' },
        { value: '13', text: '陳光凱' },
        { value: '14', text: '王明輝' },
        { value: '15', text: '鮑道訓' },
        { value: '16', text: '劉欽明' },
        { value: '17', text: '陳祖安' },
        { value: '18', text: '陳光凱' },
        { value: '19', text: '王國書' },
        { value: '20', text: '陳登輝' },
        { value: '21', text: '李怡萱' },
      ],
      caseCategory: [
        { value: '', text: '請選擇' },
        { value: '1', text: '下水道' },
      ],
    });

    // 是否需要重新查詢
    const isReload = ref(false);

    const $bvModal = useBvModal();

    const toQueryView = () => {
      handleBack({ isReload: isReload.value, isNotKeepAlive: true });
      isReload.value = false;
    };

    const changeFormStatus = (status: formStatusEnum) => {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    //新增&修改送出前檢查訊息
    const submitForm = (submitMethod: string) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (submitMethod === 'modify') {
            //提交修改
            //putForm();
            $bvModal.msgBoxOk('資料異動成功');
            toQueryView();
          }
          if (submitMethod === 'create') {
            //提交新增
            //insertTyphoon(true);
            $bvModal.msgBoxOk('資料新增成功');
            reset();
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
          // insertTyphoon(false);
          $bvModal.msgBoxOk('資料新增成功');
          changeFormStatus(formStatusEnum.CREATE);
          reset();
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //刪除
    const deleteRevMain = () => {
      $bvModal.msgBoxConfirm('刪除計畫申請資料，確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          toQueryView();
          $bvModal.msgBoxOk('資料刪除成功');
        }
      });
    };

    const createDefaultValue = (data: RevMain) => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
    };

    watch(
      revMainProp,
      () => {
        if (formStatusForwardProp.value === 'modify') {
          createDefaultValue(revMainProp.value);
          changeFormStatus(formStatusEnum.MODIFY);
        } else {
          // Object.assign(form, formDefault);
          // createDefaultValue(form);
          // changeFormStatus(formStatusEnum.CREATE);
        }
      },
      { immediate: true }
    );

    //顯示查詢條件區
    const unitPriceVisible = ref(false);

    const addContentColumn = () => {
      unitPriceVisible.value = true;

      //
    };

    //增加單位造價
    const addUnitPriceGroup = () => {};

    return {
      formStatusEnum,
      formStatusRef,
      formStatusForwardProp,
      $v,
      options,
      changeFormStatus,
      toQueryView,
      submitForm,
      submitFormNext,
      deleteRevMain,
      reset,
      validateState,
      revMainProp,
      addContentColumn,
      unitPriceVisible,
      addUnitPriceGroup,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
.table th,
.table td {
  vertical-align: middle !important;
}
</style>

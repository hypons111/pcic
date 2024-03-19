<template>
  <div>
    <section class="container">
      <div class="card" id="printTable">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            廠商申請創新產品交流基本資料表 -{{ formStatusRef }}- -{{ roleRef }}-
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!--創新產品交流編號-->
            <i-form-group-check
              label-align="left"
              :labelStar="false"
              :label="$t('label.innovationProductCommunicateId')"
              :item="$v.proInnovativeProductId"
              disabled
            >
              <div v-if="formStatusRef === formStatusEnum.INFO">
                {{ $v.proInnovativeProductMainDTO.proInnovativeProductId.$model }}
              </div>
              <b-form-input
                v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                :value="$v.proInnovativeProductMainDTO.proInnovativeProductId.$model"
              ></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!--          申請交流日期-->
          <b-form-row>
            <i-form-group-check :labelStar="false" label-align="left" :label="$t('label.communicateDateApply')" :item="$v.createTime">
              <div v-if="formStatusRef === formStatusEnum.INFO">
                {{ $v.proInnovativeProductMainDTO.createTime.$model | dateToString }}
              </div>
              <i-date-picker
                v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                v-model="$v.proInnovativeProductMainDTO.createTime.$model"
                :state="validateState($v.proInnovativeProductMainDTO.createTime)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <!--          創新產品名稱-->
          <b-form-row>
            <i-form-group-check label-align="left" :labelStar="true" :label="$t('label.innovateName')" :item="$v.innovateName">
              <div v-if="formStatusRef === formStatusEnum.INFO">
                {{ $v.proInnovativeProductMainDTO.innovateName.$model }}
              </div>
              <b-form-input
                v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                :value="$v.proInnovativeProductMainDTO.innovateName.$model"
                :state="validateState($v.proInnovativeProductMainDTO.innovateName)"
                v-model.trim="$v.proInnovativeProductMainDTO.innovateName.$model"
                placeholder="創新產品名稱"
              ></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!--          申請受理交流之機關-->
          <b-form-row>
            <i-form-group-check label-align="left" :labelStar="true" :label="$t('label.communicateInstitutionApply')" :item="$v.orgName">
              <div v-if="formStatusRef === formStatusEnum.INFO">
                {{ $v.proInnovativeProductMainDTO.orgName.$model }}
              </div>
              <!--              <b-form-input-->
              <!--                v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"-->
              <!--                :value="$v.proInnovativeProductMainDTO.acceptedOrganization.$model"-->
              <!--                :state="validateState($v.proInnovativeProductMainDTO.acceptedOrganization)"-->
              <!--                v-model.trim="$v.proInnovativeProductMainDTO.acceptedOrganization.$model"-->
              <!--                placeholder="工程會"-->
              <!--              ></b-form-input>-->
              <i-tree-select
                v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                v-model="$v.proInnovativeProductMainDTO.acceptedOrganization.$model"
                placeholder="請輸入機關關鍵字查詢(至少輸入兩個字)"
                :multiple="false"
                :load-options="loadOptions"
                :async="true"
              ></i-tree-select>
            </i-form-group-check>
          </b-form-row>
          <!--          申請與機關交流之發文日期-->
          <b-form-row>
            <i-form-group-check
              label-align="left"
              :labelStar="true"
              :label="$t('label.communicateInstitutionApplyDate')"
              :item="$v.communicateDate"
            >
              <div v-if="formStatusRef === formStatusEnum.INFO">
                {{ $v.proInnovativeProductMainDTO.communicateDate.$model | dateToString }}
              </div>
              <i-date-picker
                cols="5"
                v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                v-model="$v.proInnovativeProductMainDTO.communicateDate.$model"
                :state="validateState($v.proInnovativeProductMainDTO.communicateDate)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <!--          申請與機關交流之字號-->
          <b-form-row>
            <i-form-group-check
              label-align="left"
              :labelStar="true"
              :label="$t('label.communicateInstitutionApplyNumber')"
              :item="$v.communicateDocNumber"
              :state="validateState($v.proInnovativeProductMainDTO.communicateDocNumber)"
            >
              <div v-if="formStatusRef === formStatusEnum.INFO">
                {{ $v.proInnovativeProductMainDTO.communicateDocNumber.$model }}
              </div>
              <b-form-input
                v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                :value="$v.proInnovativeProductMainDTO.communicateDocNumber.$model"
                :state="validateState($v.proInnovativeProductMainDTO.communicateDocNumber)"
                v-model.trim="$v.proInnovativeProductMainDTO.communicateDocNumber.$model"
                placeholder="123456789"
              ></b-form-input>
              <!--              <b-form-input v-else v-model.trim="$v.communicateDocNumber" :state="validateState($v.communicateDocNumber)"></b-form-input>-->
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <b-col class="text-left">申請廠商:</b-col>
            <b-col cols="10">
              <b-row>
                <b-form-group class="col-6" label="廠商名稱" label-for="name" label-class="pl-1">
                  <div v-if="formStatusRef === formStatusEnum.INFO">
                    {{ $v.proInnovativeProductMainDTO.name.$model }}
                  </div>
                  <div v-if="formStatusRef === formStatusEnum.CREATE">
                    {{ $v.proApplyCompanyDTO.name.$model }}
                  </div>
                </b-form-group>
                <b-form-group class="col-6" label="統一編號" label-class="pl-1">
                  <div v-if="formStatusRef === formStatusEnum.INFO">{{ $v.proInnovativeProductMainDTO.compIdno.$model }}</div>
                  <div v-if="formStatusRef === formStatusEnum.CREATE">
                    {{ $v.proApplyCompanyDTO.compIdno.$model }}
                  </div>
                </b-form-group>
              </b-row>
              <b-row>
                <b-form-group class="col-6" label="地址" label-for="addr" label-class="pl-1">
                  <div class="pt-2" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.proInnovativeProductMainDTO.addr.$model }}</div>
                  <div v-if="formStatusRef === formStatusEnum.CREATE">
                    {{
                      $v.proApplyCompanyDTO.addressCity.$model +
                      $v.proApplyCompanyDTO.addressTown.$model +
                      $v.proApplyCompanyDTO.addressVillage.$model +
                      $v.proApplyCompanyDTO.addressLin.$model +
                      $v.proApplyCompanyDTO.addressRoad.$model
                    }}
                  </div>
                </b-form-group>
              </b-row>
              <b-form-row>
                <b-form-group class="col-3" label="聯絡人" label-class="pl-1">
                  <div v-if="formStatusRef === formStatusEnum.INFO">
                    {{ $v.proInnovativeProductMainDTO.chName.$model }}
                  </div>
                  <div v-if="formStatusRef === formStatusEnum.CREATE">
                    {{ $v.proApplyCompanyDTO.rpName.$model }}
                  </div>
                  <!--                  <b-form-input v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY" trim></b-form-input>-->
                </b-form-group>
                <b-form-group class="col-3" label="職稱" label-class="pl-1">
                  <div v-if="formStatusRef === formStatusEnum.INFO">
                    {{ $v.proInnovativeProductMainDTO.title.$model }}
                  </div>
                  <div v-if="formStatusRef === formStatusEnum.CREATE">
                    {{ $v.proApplyCompanyDTO.title.$model }}
                  </div>
                  <!--                  <b-form-input v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY" trim></b-form-input>-->
                </b-form-group>
                <b-form-group class="col-6" label="電話" label-class="pl-1">
                  <div v-if="formStatusRef === formStatusEnum.INFO">
                    {{ $v.proInnovativeProductMainDTO.workTel.$model }}
                  </div>
                  <div v-if="formStatusRef === formStatusEnum.CREATE">
                    {{ $v.proApplyCompanyDTO.workTel.$model }}
                  </div>
                </b-form-group>
              </b-form-row>
            </b-col>
          </b-form-row>
          <b-form-row>
            <!--            屬性-->
            <i-form-group-check
              label-align="left"
              class="col-12"
              label-cols="2"
              content-cols="10"
              :labelStar="true"
              :label="$t('label.attribute')"
              :item="$v.innovateType"
            >
              <b-form-radio-group
                class="pt-2"
                :disabled="formStatusRef === formStatusEnum.INFO"
                v-model="$v.proInnovativeProductMainDTO.innovateType.$model"
                :options="formOptions.innovateType"
                :state="validateState($v.proInnovativeProductMainDTO.innovateType)"
              ></b-form-radio-group>
            </i-form-group-check>
          </b-form-row>
          <!--          工程類別-->
          <b-form-row>
            <i-form-group-check
              label-align="left"
              class="col-12"
              label-cols="2"
              content-cols="10"
              :labelStar="true"
              :label="$t('label.typeCode')"
            >
              <b-form-checkbox-group
                v-model="$v.enginTypes.$model"
                :options="formOptions.enginType"
                class="mb-2"
                :disabled="formStatusRef === formStatusEnum.INFO"
                name="flavour-1a"
                inline
                :state="validateState($v.enginTypes)"
              ></b-form-checkbox-group>
            </i-form-group-check>
          </b-form-row>
          <!--          創新資訊-->
          <b-form-row>
            <b-col cols="2">創新資訊</b-col>
            <br />
            <b-col cols="10">
              <div>1.功能效益說明</div>
              <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.proInnovativeProductMainDTO.innovateFunction.$model }}</div>
              <b-form-textarea
                v-if="formStatusRef !== formStatusEnum.INFO"
                v-model="$v.proInnovativeProductMainDTO.innovateFunction.$model"
                rows="4"
                trim
              ></b-form-textarea>
              <br />
              <div>2.品質說明</div>
              <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.proInnovativeProductMainDTO.innovateQuality.$model }}</div>
              <b-form-textarea
                v-if="formStatusRef !== formStatusEnum.INFO"
                v-model="$v.proInnovativeProductMainDTO.innovateQuality.$model"
                rows="4"
                trim
              ></b-form-textarea>
              <br />
              <div>3.證明方式</div>
              <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.proInnovativeProductMainDTO.innovateProve.$model }}</div>
              <b-form-textarea
                v-if="formStatusRef !== formStatusEnum.INFO"
                v-model="$v.proInnovativeProductMainDTO.innovateProve.$model"
                rows="4"
                trim
              ></b-form-textarea>
              <!--              <div>3.證明方式</div>-->
              <!--              <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.proInnovativeProductMainDTO.innovateProve.$model }}</div>-->
              <!--              <b-form-textarea-->
              <!--                v-if="formStatusRef !== formStatusEnum.INFO"-->
              <!--                v-model="$v.IProInnovativeProductAddDetailDTO.innovateProve.$model"-->
              <!--                rows="4"-->
              <!--                trim-->
              <!--              ></b-form-textarea>-->
              <br />
              <div>4.專利</div>
              <!--              <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.formOptions.patent.$model }}</div>-->
              <b-form-radio-group
                class="pt-2"
                v-model="$v.proInnovativeProductMainDTO.patent.$model"
                :options="formOptions.patent"
                :disabled="formStatusRef === formStatusEnum.INFO"
                :state="validateState($v.proInnovativeProductMainDTO.patent)"
              >
              </b-form-radio-group>
              <div>
                <div class="" v-if="formStatusRef === formStatusEnum.INFO && $v.proInnovativeProductMainDTO.patent.$model === '0'">
                  <div class="" v-if="formStatusRef === formStatusEnum.INFO">
                    專利期間 {{ $v.proInnovativeProductMainDTO.patentStartDate.$model | dateToString }} 至{{
                      $v.proInnovativeProductMainDTO.patentEndDate.$model | dateToString
                    }}
                  </div>
                </div>
                <div class="" v-if="formStatusRef !== formStatusEnum.INFO && $v.proInnovativeProductMainDTO.patent.$model === '0'">
                  <i-form-group-check labelStar content-cols="6" :label="$t('label.patentStartDate') + '：'" :item="$v.patentStartDate">
                    <i-date-picker
                      v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                      v-model="$v.proInnovativeProductMainDTO.patentStartDate.$model"
                      :state="validateState($v.proInnovativeProductMainDTO.patentStartDate)"
                      placeholder="yyy/MM/dd"
                    ></i-date-picker>
                  </i-form-group-check>
                  至
                  <i-form-group-check labelStar content-cols="6" :label="$t('label.patentEndDate') + '：'" :item="$v.patentEndDate">
                    <i-date-picker
                      v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                      v-model="$v.proInnovativeProductMainDTO.patentEndDate.$model"
                      :state="validateState($v.proInnovativeProductMainDTO.patentEndDate)"
                      placeholder="yyy/MM/dd"
                    ></i-date-picker>
                  </i-form-group-check>
                </div>
              </div>
              <!--              CREATE的日期-->

              <br />
              <div>5.施工成本</div>
              <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.proInnovativeProductMainDTO.constructCost.$model }}</div>
              <b-form-textarea
                v-if="formStatusRef !== formStatusEnum.INFO"
                v-model="$v.proInnovativeProductMainDTO.constructCost.$model"
                rows="4"
                trim
              ></b-form-textarea>
              <br />
              <div>6.維護成本</div>
              <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ $v.proInnovativeProductMainDTO.maintainCost.$model }}</div>
              <b-form-textarea
                v-if="formStatusRef !== formStatusEnum.INFO"
                v-model="$v.proInnovativeProductMainDTO.maintainCost.$model"
                rows="4"
                trim
              ></b-form-textarea>
              <br />
              <div>7.國內外工程實例</div>
              <b-row>
                <b-form-group class="col-4" label="工程名稱" label-for="innovateCaseName" label-class="pl-1">
                  <div v-if="formStatusRef === formStatusEnum.INFO">
                    {{ $v.proInnovativeProductMainDTO.innovateCaseName.$model }}
                  </div>
                  <b-form-input
                    v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                    id="innovateCaseName"
                    v-model="$v.proInnovativeProductMainDTO.innovateCaseName.$model"
                    trim
                  ></b-form-input>
                </b-form-group>
                <b-form-group class="col-4" label="業主" label-for="innovateCaseVendor" label-class="pl-1">
                  <div v-if="formStatusRef === formStatusEnum.INFO">
                    {{ $v.proInnovativeProductMainDTO.innovateCaseVendor.$model }}
                  </div>
                  <b-form-input
                    v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                    id="innovateCaseVendor"
                    v-model="$v.proInnovativeProductMainDTO.innovateCaseVendor.$model"
                    trim
                  ></b-form-input>
                </b-form-group>
                <b-form-group class="col-4" label="工程規模" label-for="innovateCaseScale" label-class="pl-1">
                  <div v-if="formStatusRef === formStatusEnum.INFO">
                    {{ $v.proInnovativeProductMainDTO.innovateCaseScale.$model }}
                  </div>
                  <b-form-input
                    v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                    id="innovateCaseScale"
                    v-model="$v.proInnovativeProductMainDTO.innovateCaseScale.$model"
                    trim
                  ></b-form-input>
                </b-form-group>
              </b-row>
              <br />
              <div>8.產品DM</div>
              <b-button-toolbar v-show="formStatusRef === formStatusEnum.INFO" class="float-left mt-5">
                {{ $v.proInnovativeProductMainDTO.proAdmFile.$model ? $v.proInnovativeProductMainDTO.proAdmFile.$model.fileNameOri : '' }}
                <i-button
                  size="sm"
                  type="file-earmark-arrow-down"
                  @click="downloadAdmFile($v.proInnovativeProductMainDTO.proAdmFile.$model.id)"
                ></i-button>
              </b-button-toolbar>
              <b-form-file
                v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                accept="image/jpeg, image/png, application/pdf"
                browse-text="選擇檔案"
                placeholder="未選擇任何檔案"
                drop-placeholder="拖曳檔案至此"
                v-model="$v.proInnovativeProductMainDTO.picFile.$model"
                @input="appendixSelected"
                class="col-9"
              >
              </b-form-file>
            </b-col>
          </b-form-row>
          <br /><br />
          <b-form-row>
            <b-col cols="2" class="text-left"
              >推薦單位
              <br />
              <span class="text-danger">(如為學(公、協)會需與公共工程相關))</span></b-col
            >
            <b-col cols="10">
              <b-table-simple>
                <b-thead>
                  <b-tr>
                    <b-th>推薦單位</b-th>
                    <b-th>名稱</b-th>
                    <b-th>電話</b-th>
                    <b-th>檔案</b-th>
                  </b-tr>
                </b-thead>
                <b-tbody>
                  <b-tr v-for="(item, index) of $v.proRecommendListDTOs.$model" :key="item.recommendType">
                    <b-td>
                      <b-form-checkbox :disabled="formStatusRef === formStatusEnum.INFO" v-model="item.checked">{{
                        recommendUnit[index]
                      }}</b-form-checkbox>
                    </b-td>
                    <b-td>
                      <div v-if="formStatusRef === formStatusEnum.INFO">{{ item.recommendName }}</div>
                      <b-form-input
                        v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                        v-model="item.recommendName"
                        :disabled="item.checked === false"
                      />
                    </b-td>
                    <b-td>
                      <div v-if="formStatusRef === formStatusEnum.INFO">{{ item.recommendTel }}</div>
                      <b-form-input
                        v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY"
                        v-model="item.recommendTel"
                        :disabled="item.checked === false"
                      />
                    </b-td>
                    <b-td v-if="formStatusRef === formStatusEnum.INFO">
                      {{ item.proAdmFile ? item.proAdmFile.fileNameOri : '' }}
                      <i-button
                        size="sm"
                        type="file-earmark-arrow-down"
                        @click="downloadAdmFile(item.proAdmFile.id)"
                        :checked="item.checked"
                      ></i-button>
                      <!--                      {{ item.proAdmFile.id }}--></b-td
                    >
                    <b-td v-if="formStatusRef === formStatusEnum.CREATE || roleRef === roleEnum.MODIFY">
                      <b-form-file
                        :disabled="item.checked === false"
                        plain
                        accept="image/jpeg, image/png, application/pdf"
                        :checked="item.checked"
                        v-model="item.fileDatas"
                      >
                      </b-form-file>
                    </b-td>
                  </b-tr>
                </b-tbody>
              </b-table-simple>
            </b-col>
          </b-form-row>
          <!--編輯狀態-->
          <!--          {{ $v.proInnovativeProductMainDTO.statusIP.$model }}-->
          <b-form-row>
            <i-form-group-check label-align="left" :label="$t('label.writeCondition')" :item="$v.status">
              <b-form-input
                :value="formOptionsFormatter('status', $v.proInnovativeProductMainDTO.statusIP.$model)"
                plaintext
                disabled
              ></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <!--編輯狀態  ref="proad0101communicateStatus"代表對應的proad0101communicateStatus  data代表值也可以宣告其他東西去帶-->
          <proad0101communicate-status
            ref="proad0101communicateStatus"
            :cmdata="form"
            :formStatus="formStatusRef"
            :role="roleRef"
          ></proad0101communicate-status>

          <proad0101NoCommunicate
            ref="proad0101NoCommunicate"
            :nocmdata="form"
            :formStatus="formStatusRef"
            :role="roleRef"
          ></proad0101NoCommunicate>
          <br />
          <b-row class="col-sm row justify-content-end">
            <!--            v-if="$v.proInnovativeProductMainDTO.statusIP.$model === 3 && roleRef === roleEnum.ADMIN"-->
            <i-button size="sm" type="open" @click="editStatus('1')"></i-button>&ensp;
            <!--            v-if="roleRef === roleEnum.ADMIN"&ensp;-->
            <i-button size="sm" type="remove" @click="editStatus('4')"></i-button>&ensp;
            <!--            v-if="$v.proInnovativeProductMainDTO.statusIP.$model === 2"-->
            <i-button size="sm" type="submission" @click="editStatus('3')"></i-button>&ensp;
            <i-button size="sm" type="save" @click="submitForm(true)"></i-button>&ensp;
            <!--            v-if="roleRef === roleEnum.ADMIN"-->
            <i-button size="sm" @click="deletePro" type="delete"></i-button>&ensp;
            <i-button size="sm" type="printer" v-print="'#printTable'"></i-button>&ensp;
            <i-button size="sm" type="arrow-counterclockwise" @click="toQueryView"></i-button>
          </b-row>
        </div>
        <br />
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { onActivated, watch, reactive, Ref, ref, toRef, toRefs, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { parseRocDate, formatDate } from '@/shared/date/minguo-calendar-utils';
import { ASYNC_SEARCH } from '@riophae/vue-treeselect';
import { ProInnovativeProductMainDTO } from '@/shared/model/proModel/pro-innovative-product-main-dto';
import { downloadFile, statusFormatter } from '@/shared/formatter/common';
import IFormGroupCheck from '@/shared/form/i-form-group-check.vue';
import proad0101communicateStatus from '@/components/pro/pro-ad-0101-communicateStatus.vue';
import proad0101NoCommunicate from '@/components/pro/pro-ad-0101-noCommunicate.vue';
import { navigateByNameAndParams } from '@/router/router';
import AdmAgentService from '@/components/pro/proService/adm-agent.service';
import { ProInnovativeProductProUserCriteria } from '@/shared/model/proModel/pro-innovative-product-pro-user-criteria';
import { ProRecommendListDTO } from '@/shared/model/proModel/pro-recommend-list.model';
import {
  IProInnovativeProductSumSearchDetail,
  ProInnovativeProductSumSearchDetail,
} from '@/shared/model/proModel/pro-innovative-product-sum-search-detail-dto';
import { maxLength, required, requiredIf } from '@/shared/validators';
import { FormStatusEnum, RoleEnum } from '@/shared/enum';
import { ProInnovativeProductAddDetail } from '@/shared/model/proModel/pro-innovative-product-add-detail-dto';
import { useStore } from '@u3u/vue-hooks';
import { IProApplyCompanyDTO } from '@/shared/model/proModel/pro-apply-company-dto';
import { AdmFile } from '@/shared/model/adm/adm-file.model';
import ItreeSelect from '@/shared/i-tree-select/i-tree-select.vue';

export default {
  name: 'proAd0101EditInfo',
  props: {
    proBasicForm: {
      required: false,
    },
    formStatus: {
      // type: formStatusEnum,
      required: true,
    },
    role: {
      required: true,
    },
    proInnovativeProductMainDTO: {
      type: Object,
      required: false,
    },
    proApplyCompanyDTO: {
      type: Object,
      required: false,
    },
    // proEnginListDTOs: {
    //   type: Object,
    //   required: false,
    // },
    // proRecommendListDTOs: {
    //   type: Object,
    //   required: false,
    // },
    // ProInnovativeProductProUserCriteria: {
    //   type: Object,
    //   required: false,
    // },
    ProInnovativeProductSumSearchDetail: {
      type: Object,
      required: false,
    },
    // ProInnovativeProductAddDetail: {
    //   type: Object,
    //   required: false,
    // },
  },
  components: {
    proad0101communicateStatus,
    proad0101NoCommunicate,
    'i-tree-select': ItreeSelect,
  },
  setup(props, context) {
    const proBasicFormRef = toRef(props, 'proBasicForm');
    const ProInnovativeProductProUserCriteriaRef = toRef(props, 'proInnovativeProductProUserCriteria');
    //formStatusRef 表單的狀態 可以辨認表單的使用者和是否要做顯示或是修改
    // const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);
    const formStatusRef = ref(null);
    const proRecommendListDTOs = new ProRecommendListDTO();
    // const appendixFilesMap: Ref<File[]> = ref(null);
    const isCheckRequired = ref(false); // 儲存 = false; 送出 = true
    const admAgentService = new AdmAgentService();
    const proInnovativeProductSumSearchDetailProp = toRefs(props).ProInnovativeProductSumSearchDetail;
    //查看是create還是info或是modify的狀態
    watch(
      () => props.formStatus,
      newVal => {
        formStatusRef.value = newVal;
      },
      { immediate: true }
    );

    const roleRef = toRef(props, 'role');
    const proad0101communicateStatus = ref(null);
    const proad0101NoCommunicate = ref(null);

    // 查詢結果及狀態
    const queryStatus = ref(false);
    // 是否需要重新查詢
    const isReload = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    const notificationService: NotificationService = useNotification();

    onMounted(() => {
      if (formStatusRef.value === formStatusEnum.INFO) {
        form.proInnovativeProductId = props.proBasicForm.proInnovativeProductId;
        axios
          .post('/service/pro-innovative-products/proCriteria-detail', form)
          .then(({ data }) => {
            // setDownloadFileName(data);
            console.log('proCriteria-detail:', data);
            createDefaultValue(data);
          })
          .catch(notificationErrorHandler(notificationService));
      } else if (formStatusRef.value === formStatusEnum.CREATE) {
        findAdd();
        createDefaultValue(props.ProInnovativeProductSumSearchDetail);
        // console.log('props.ProInnovativeProductSumSearchDetail', props.ProInnovativeProductSumSearchDetail);
        console.log(useStore().value.getters);
      }
    });

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
      REMAKE = 'remake',
    }

    enum roleEnum {
      USER = '使用者',
      ADMIN = '管理員',
    }

    const formOptions = reactive({
      enginType: [
        { value: '0', text: '建築' },
        { value: '1', text: '道路運輸' },
        { value: '2', text: '軌道運輸' },
        { value: '3', text: '機場' },
        { value: '4', text: '港灣' },
        { value: '5', text: '水庫及蓄水' },
        { value: '6', text: '電業設備' },
        { value: '7', text: '水利' },
        { value: '8', text: '自來水' },
        { value: '9', text: '共同管道' },
        { value: '10', text: '下水道' },
        { value: '11', text: '焚化廠' },
        { value: '12', text: '垃圾掩埋場' },
        { value: '13', text: '土地開發' },
        { value: '14', text: '水土保持' },
        { value: '15', text: '其他' },
      ],

      innovateType: [
        { value: '0', text: '材料' },
        { value: '1', text: '設備' },
        { value: '2', text: '技術' },
        { value: '3', text: '工法' },
        { value: '4', text: '其他' },
      ],
      patent: [
        { value: '0', text: '有' },
        { value: '1', text: '無' },
      ],
      recommendType: [
        { value: '0', text: '大專院校' },
        { value: '1', text: '研究機構' },
        { value: '2', text: '學會' },
        { value: '3', text: '公會' },
        { value: '4', text: '協會' },
      ],
      status: [
        { value: '0', text: '全部' },
        { value: '1', text: '公開' },
        { value: '2', text: '草稿' },
        { value: '3', text: '送審中' },
        { value: '4', text: '已下架' },
      ],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = new ProInnovativeProductSumSearchDetail();

    const rules = {
      proInnovativeProductMainDTO: {
        proInnovativeProductId: {},
        createTime: {},
        innovateName: { requiredIf: requiredIf(() => isCheckRequired.value), maxLength: maxLength(20) },
        acceptedOrganization: {},
        orgName: {},
        communicateDate: { requiredIf: requiredIf(() => isCheckRequired.value) },
        communicateDocNumber: { requiredIf: requiredIf(() => isCheckRequired.value), maxLength: maxLength(20) },
        innovateType: { required: required },
        innovateFunction: {},
        innovateQuality: {},
        innovateProve: {},
        patent: { required: required },
        patentStartDate: {},
        patentEndDate: {},
        constructCost: {},
        maintainCost: {},
        innovateCaseName: {},
        innovateCaseVendor: {},
        innovateCaseScale: {},
        statusIP: {},
        time: {},
        type: {},
        addr: {},
        outline: {},
        memo: {},
        other: {},
        commReason: {},
        name: {},
        compIdno: {},
        addressCity: {},
        addressTown: {},
        addressVillage: {},
        addressLin: {},
        addressRoad: {},
        chName: {},
        title: {},
        workTel: {},
        communicateType: {},
        statusInnovativeProduct: {},
        statusOrgSuggestion: {},
        writeCondition: {},
        picFile: {},
        // appendixFilesMap: {},
        proAdmFile: {},
      },
      proEnginListDTOs: {
        id: {},
        proInnovativeProductId: {},
        enginType: {},
        updateUser: {},
        updateTime: {},
        createUser: {},
        createTime: {},
      },
      proRecommendListDTOs: {
        proRecommendListId: {},
        proInnovativeProductId: {},
        recommendType: {},
        recommendName: {},
        recommendTel: {},
        updateUser: {},
        updateTime: {},
        createUser: {},
        createTime: {},
        checked: {},
        fileDatas: {},
        proAdmFile: {},
      },
      proCommunicatedDTOs: {
        updateTimePos: {},
        personName: {},
      },
      proNoCommunicateDTOs: {
        noCommunicateType: {},
      },
      enginTypes: { required: required },
      // noCommunicateTypes: {},
      proApplyCompanyDTO: {
        name: {},
        compIdno: {},
        rpName: {},
        addressCity: {},
        addressTown: {},
        addressVillage: {},
        addressLin: {},
        addressRoad: {},
        title: {},
        workTel: {},
      },
    };

    // //利用tree select 自帶的API來動態產生選項
    function loadOptions({ action, searchQuery, callback }) {
      //判斷注音
      let pattern = /[\u3105-\u3129\u02CA\u02C7\u02CB\u02D9]/;
      if (action === ASYNC_SEARCH && !pattern.test(searchQuery) && searchQuery.toString().length >= 2) {
        admAgentService.findAllwithOrgIdAndOrgNameByKeyWord(searchQuery.toString()).then(res => {
          console.log('findAllwithOrgIdAndOrgNameByKeyWord', res);
          const options = [];
          res.forEach(ele => {
            const proOption = { id: '', label: '' };
            proOption.id = ele.orgName;
            proOption.label = ele.orgName;
            options.push(proOption);
          });
          callback(null, options);
        });
      }
    }

    // 因有keepAlive功能，所以須先用formEmpty洗掉formDefault
    // let formEmpty = Object.assign({}, formDefault);

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //在按下新增時表格內可以增加先顯示想要顯示的資料
    function setFormBeforeSubmit(data: IProInnovativeProductSumSearchDetail) {
      //   form.applyItemsList = form.applyItems.join(',');
      //   form.updateTime = new Date();
      //   form.updateUser = context.root.$store.getters.account.id;
      if (formStatusRef.value === FormStatusEnum.CREATE) {
        //     form.engEngrCertificateVersion = formStatusRef.value === FormStatusEnum.CREATE ? 1.0 : form.engEngrCertificateVersion + 0.1;
        //     form.status = isCheckRequired.value ? '3' : null;
        //     form.contractorId = props.role === RoleEnum.ADMIN ? context.root.$store.getters.account.id : null;
        //     form.applyType = props.role === RoleEnum.ADMIN ? '0' : '1';
        form.proInnovativeProductMainDTO.createTime = new Date();
        //     form.createUser = context.root.$store.getters.account.id;
        //     form.reviewStaff = null;
        //     form.reviewTime = null;
        //     form.reviewDesc = null;
        //     form.transferTime = null;
        //     form.transferFrom = null;
      }
      form.proRecommendListDTOs = form.proRecommendListDTOs.filter(item => item['checked'] === true);
      data.proInnovativeProductMainDTO.createUser = useStore().value.getters.account.id;
      // form.proInnovativeProductMainDTO.createUser = useStore().value.getters.account.id;

      // console.log('data:', data);
      // console.log('form:', form);

      //新增DM和推薦檔案
      const formData = new FormData();
      formData.append('form', new Blob([JSON.stringify(data.proInnovativeProductMainDTO)], { type: 'application/json' }));

      formData.append('picFile', form.proInnovativeProductMainDTO.picFile);
      // form.proInnovativeProductMainDTO.appendixFilesMap;
      // i <= 會報未定義錯誤 應該是因為沒有4的type條件判斷
      for (let i = 0; i < form.proRecommendListDTOs.length; i++) {
        // console.log('form.proRecommendListDTOs[i]:', form.proRecommendListDTOs[i]);
        if (form.proRecommendListDTOs[i].recommendType == '0') {
          formData.append('recomPic_college', form.proRecommendListDTOs[i].fileDatas);
        } else if (form.proRecommendListDTOs[i].recommendType == '1') {
          formData.append('recomPic_research', form.proRecommendListDTOs[i].fileDatas);
        } else if (form.proRecommendListDTOs[i].recommendType == '2') {
          formData.append('recomPic_academy', form.proRecommendListDTOs[i].fileDatas);
        } else if (form.proRecommendListDTOs[i].recommendType == '3') {
          formData.append('recomPic_guild', form.proRecommendListDTOs[i].fileDatas);
        } else {
          formData.append('recomPic_association', form.proRecommendListDTOs[i].fileDatas);
        }
      }
      console.log('proUpload formData:', formData);

      axios
        .put('/adm-files/proUpload', formData, { headers: { 'Content-Type': 'multipart/form-data' } })
        .then((res: AxiosResponse) => {
          // resolve(res.data);
          console.log('proUpload res.data:', res.data);
        })
        .catch(err => {
          // reject(err);
          console.log('proUpload err:', err);
        });

      return formData;
    }

    //對應到下載按鈕
    function downloadAdmFile(id: number) {
      axios
        .get(`/adm-files/download/${id}`, { responseType: 'blob' })
        .then((response: AxiosResponse) => downloadFile(response))
        .catch(notificationErrorHandler(notificationService));
    }

    //放入編輯狀態  @click="editStatus('1')"
    function editStatus(statusStr: string) {
      let formObject = formDefault;
      formObject.proInnovativeProductMainDTO.statusIP = statusStr;
      Object.assign(formDefault, formObject);
    }

    function submitForm(isCheckReq: boolean) {
      isCheckRequired.value = isCheckReq;
      // 先清除欄位$dirty狀態，才能依據isCheckRequired(是否驗證必填)重新驗證
      $v.value.$reset();

      // 不加this.$nextTick，checkValidity() 會比上方 $v.value.$reset() 先執行!!!
      this.$nextTick(() => {
        checkValidity().then((isValid: boolean) => {
          if (isValid) {
            if (isCheckReq) {
              $bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
                if (isOK) {
                  putForm();
                  toQueryView();
                }
              });
            } else {
              putForm();
            }
          } else {
            $bvModal.msgBoxOk(isCheckReq ? '欄位尚未填寫完畢，請於輸入完畢後再行送出。' : '欄位尚未填寫完整，請輸入完整或空白後再行儲存。');
          }
        });
      });
    }

    //對應到按下新增完顯示申請廠商資料
    function findAdd() {
      let user = useStore().value.getters.account.id;
      axios.get('/pro-innovative-products/findCompany/' + user).then(({ data }) => {
        createDefaultUserValue(data);
      });
    }

    //對應到刪除
    function findDelete() {
      axios
        .delete('/pro-innovative-products/delete/' + props.proBasicForm.proInnovativeProductId)
        .then(() => {
          notificationService.success(`刪除成功`);
        })
        .catch(notificationErrorHandler(notificationService));
      toQueryView();
    }

    //對應到新增
    function putForm() {
      axios.post('/pro-innovative-products/addProIP', form).then(({ data }) => {
        // console.log('addProIP', data);
        setFormBeforeSubmit(data);

        //新增過後並把資料再放上表格
        createDefaultValueAfterAdd(data);
        isReload.value = true;
      });
    }

    const changeFormStatus = (status: formStatusEnum) => {
      reset();
      // }
      formStatusRef.value = status;
    };
    //查詢渲染明細的資料
    const createDefaultValue = (data: IProInnovativeProductSumSearchDetail) => {
      Object.assign(formDefault, data);
      //map(取兩個以上的資料，所以DTO也會有相應的屬性作對應)
      formDefault.enginTypes = formDefault.proEnginListDTOs.map(item => item.enginType);
      setProRecommendListDTOs(formDefault.proRecommendListDTOs);
      formDefault.noCommunicateTypes = formDefault.proNoCommunicateDTOs.map(item => item.noCommunicateType);
      formDefault.personNames = formDefault.proCommunicatedDTOs.map(item => item.personName);
      reset();
      console.log('$v.value', $v.value);
    };

    //新增並看廠商資料，因為要接到IProApplyCompanyDTO但是和後端的型態不同，所以再改成dataCompany
    const createDefaultUserValue = (data: IProApplyCompanyDTO) => {
      let dataCompany = formDefault;
      dataCompany.proApplyCompanyDTO = data;
      Object.assign(formDefault, dataCompany);
      reset();
      //map(取LIST內的資料)
    };

    //新增過後的資料
    const createDefaultValueAfterAdd = (data: IProInnovativeProductSumSearchDetail) => {
      Object.assign(formDefault, data);
      //map(取LIST內的資料)
      setProRecommendListDTOs(formDefault.proRecommendListDTOs);
      reset();
    };

    //刪除過後的提示
    const deletePro = (data: IProInnovativeProductSumSearchDetail) => {
      Object.assign(formDefault, data);
      $bvModal
        .msgBoxConfirm(
          `是否確定作廢？編號：${formDefault.proInnovativeProductMainDTO.proInnovativeProductId}，創新產品名稱：${formDefault.proInnovativeProductMainDTO.innovateName}`
        )
        .then((isOK: boolean) => {
          if (isOK) {
            findDelete();
          }
        });
    };

    const doubleReset = () => {
      reset();
      proad0101communicateStatus.value.reset();
      proad0101NoCommunicate.value.reset();
    };

    //返回鍵
    function toQueryView() {
      handleBack({ isReload: isReload.value });
      isReload.value = true;
    }

    //控制recommendType
    let recommendUnit = ['大專院校', '研究機構', '學會', '公會', '協會'];

    //把proRecommendListDTOs的資料裝進裡面
    function setProRecommendListDTOs(proRecommendListDTOs) {
      for (let i = 0; i <= 4; i++) {
        if (proRecommendListDTOs.find(item => Number(item.recommendType) === i)) {
          proRecommendListDTOs.find(item => Number(item.recommendType) === i).checked = true;
        } else {
          //製作陣列塞進五種資料
          proRecommendListDTOs.push({
            recommendType: String(i),
            recommendName: null,
            recommendTel: null,
            fileDatas: null,
            checked: false,
          });
        }
      }
      proRecommendListDTOs.sort((a, b) => a.recommendType - b.recommendType);
      // console.log('setProRecommendListDTOs!!!!', proRecommendListDTOs);
    }

    //帶出Options內的資料
    const formOptionsFormatter = (option: string, value: string) => {
      let itemFound = formOptions[option].find((item: any) => item.value === value);
      if (itemFound) {
        return itemFound.text;
      } else {
        return '';
      }
    };

    //檔案選擇
    function appendixSelected(files: File[]) {}

    return {
      loadOptions,
      downloadAdmFile,
      setFormBeforeSubmit,
      appendixSelected,
      formStatusEnum,
      formStatusRef,
      roleEnum,
      roleRef,
      $v,
      validateState,
      doubleReset,
      changeFormStatus,
      toQueryView,
      formOptions,
      formOptionsFormatter,
      proBasicFormRef,
      proad0101communicateStatus,
      proad0101NoCommunicate,
      // table,
      // dataRef,
      recommendUnit,
      // formDetail,
      // proUserCriteria,
      ProInnovativeProductProUserCriteriaRef,
      proRecommendListDTOs,
      form,
      submitForm,
      deletePro,
      editStatus,
    };
  },
  filters: {
    dateToString: (value: Date) => formatDate(value, '/'),
  },
};
</script>
<style>
.red-text {
  color: blue;
  font-size: 12px;
  font-weight: bold;
}
</style>

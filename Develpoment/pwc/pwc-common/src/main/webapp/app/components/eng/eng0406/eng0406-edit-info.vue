<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 案件編號 -->
            <i-form-group-check labelStar class="col-12" label-cols="2" content-cols="4" :label="$t('label.caseNo') + '：'" :item="$v.caseNo">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.caseNo.$model }}</div>
              <b-form-input v-else v-model="$v.caseNo.$model" :state="validateState($v.caseNo)" type="number" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 案件類型 -->
            <i-form-group-check content-cols="4" :label="$t('label.caseType') + '：'" :item="$v.caseType">
              <div v-if="formStatus === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.caseType, $v.caseType.$model) }}
              </div>
              <b-form-select v-else v-model="$v.caseType.$model" :options="formOptions.caseType" :state="validateState($v.caseType)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 案件名稱 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.caseNa') + '：'" :item="$v.caseName">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.caseName.$model }}</div>
              <b-form-input v-else v-model="$v.caseName.$model" :state="validateState($v.caseName)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 招標(自辦)機關(代碼及名稱) -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.orgIdName') + '：'" :item="$v.orgId">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.orgId.$model }}</div>
              <b-form-input v-else v-model="$v.orgId.$model" :state="validateState($v.orgId)" trim lazy></b-form-input>

              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.orgName.$model }}</div>
              <b-form-input v-else v-model="$v.orgName.$model" :state="validateState($v.orgName)" trim lazy></b-form-input>
            </i-form-group-check>
            <!-- 執行機關 -->
            <i-form-group-check content-cols="4" :label="$t('label.wkut') + '：'" :item="$v.execOrgId">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.execOrgId.$model }}</div>
              <b-form-input v-else v-model="$v.execOrgId.$model" :state="validateState($v.execOrgId)" trim lazy></b-form-input>

              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.execOrgName.$model }}</div>
              <b-form-input v-else v-model="$v.execOrgName.$model" :state="validateState($v.execOrgName)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 聯絡人 -->
            <i-form-group-check content-cols="4" :label="$t('label.contactPerson') + '：'" :item="$v.contactPerson">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.contactPerson.$model }}</div>
              <b-form-input v-else v-model="$v.contactPerson.$model" :state="validateState($v.contactPerson)" trim lazy></b-form-input>
            </i-form-group-check>
            <!-- 聯絡人電話 -->
            <i-form-group-check content-cols="4" :label="$t('label.contactTel') + '：'" :item="$v.contactTel">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.contactTel.$model }}</div>
              <b-form-input v-else v-model="$v.contactTel.$model" :state="validateState($v.contactTel)" type="number" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 聯絡人地址 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="4" :label="$t('label.contactConAddr') + '：'" :item="$v.contactConAddr">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.contactConAddr.$model }}</div>
              <b-form-input v-else v-model="$v.contactConAddr.$model" :state="validateState($v.contactConAddr)" type="number" trim lazy></b-form-input>
            </i-form-group-check>
            <!-- <i-form-group-check
              class="col-12"
              label-cols="3"
              content-cols="9"
              :label="$t('label.contactConAddr') + '：'"
              :item="[$v.addrTown, $v.addrOther, $v.addrCity]"
            >
              <i-address-picker
                :zip.sync="$v.addrZip.$model"
                :city.sync="$v.addrCity.$model"
                :town.sync="$v.addrTown.$model"
                :other.sync="$v.addrOther.$model"
                :cityState="validateState($v.addrCity)"
                :townState="validateState($v.addrTown)"
                :otherState="validateState($v.addrOther)"
                :cols="[1, 2, 2, 5]"
              >
              </i-address-picker>
            </i-form-group-check> -->
          </b-form-row>

            
          <!--<b-form-row v-if="role === RoleEnum.ADMIN">-->
            <b-form-row>
            <!-- 委託事項(服務項目及內容摘要) -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.entrustitem') + '：'" :item="$v.entrustitem">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.entrustitem.$model"
                rows="4"
                maxlength="1000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check>
            </i-form-group-check>
          </b-form-row>
          
          <b-form-row>
            <!-- 決標或自辦起始日期 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.DeciAndConDate') + '：'" :item="$v.tenderDate">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.tenderDate.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.tenderDate.$model"
                :state="validateState($v.tenderDate)"
                placeholder="yyy"
                type="year"
              ></i-date-picker>
            </i-form-group-check>
            <!-- 決標金額 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.tenderprice') + '：'" :item="$v.tenderprice">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.tenderprice.$model }}</div>
              <b-form-input v-else v-model="$v.tenderprice.$model" :state="validateState($v.tenderprice)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
          <!-- 預定辦理日期 -->
            <i-form-group-check labelStar :label="$t('label.willexecDate') + '：'" :dual1="$v.execDateStart" :dual2="$v.execDateEnd">
              <i-dual-date-picker :dual1.sync="$v.execDateStart.$model" :dual2.sync="$v.execDateEnd.$model"></i-dual-date-picker>
            </i-form-group-check>
          </b-form-row>          

          <b-form-row>
            <!-- 工程種類 -->
            <i-form-group-check content-cols="4" :label="$t('label.projectType') + '：'" :item="$v.projectType">
              <div v-if="formStatus === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.projectType, $v.projectType.$model) }}
              </div>
              <b-form-select v-else v-model="$v.projectType.$model" :options="formOptions.projectType" :state="validateState($v.projectType)">
                <template #first>
                  <option :value="undefined">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 簽證範圍 --><!--recordRangeList recordRangeOther-->
            <i-form-group-check class="col-12" label-cols="2" content-cols="4" :label="$t('label.recordRange') + '：'" :item="$v.recordRangeList">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.recordRangeList.$model }}</div>
              <b-form-input v-else v-model="$v.recordRangeList.$model" :state="validateState($v.recordRangeList)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 簽證項目 --><!-- recordItemsList recordItemsOther -->
            <i-form-group-check
            class="col-12" label-cols="2"
            :label="$t('label.recordItems') + '：'"
            :item="$v.recordRanges">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.recordItemsList, 
                data.recordItemsList) }}
              </div>
              <b-form-checkbox-group
              v-if="formStatusRef === FormStatusEnum.EDIT"
                v-model="data.recordRanges"
                :options="formOptions.recordItemsList"
              ></b-form-checkbox-group>
              <b-form-checkbox-group
              v-if="formStatusRef === FormStatusEnum.CREATE"
                
                :options="formOptions.recordItemsList"
              ></b-form-checkbox-group>
              <b-form-input class="col-4"
              v-if="formStatusRef !== FormStatusEnum.READONLY"
              trim></b-form-input>
            </i-form-group-check>
          </b-form-row>
          
          <!-- <b-form-row v-if="role === RoleEnum.ADMIN"> -->
          <b-form-row>
            <!-- 技術服務所在地 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.TeSerCation') + '：'" :item="$v.TeSerCation">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.TeSerCation.$model"
                rows="4"
                maxlength="1000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <!-- <b-form-row v-if="role === RoleEnum.ADMIN"> -->
          <b-form-row>
            <!-- 相關主管機關登記或許可證號 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.compLicenseNo') + '：'" :item="$v.compLicenseNo">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.compLicenseNo.$model"
                rows="4"
                maxlength="1000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <!-- <b-form-row v-if="role === RoleEnum.ADMIN"> -->
          <b-form-row>
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.remark') + '：'" :item="$v.remark">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.remark.$model"
                rows="4"
                maxlength="1000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
        </div>

        <!-- <i-switch-case
          class="mb-3"
          :criteria="criteria"
          api-url="/eng-engr-subjectpoints/criteria"
          @handleCaseChanged="createDefaultValue"
        ></i-switch-case> -->
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
          <!-- 身分證字號 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.idno') + '：'" :item="$v.engrIdno">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.engrIdno.$model }}</div>
              <b-form-input
                v-else
                v-model="$v.engrIdno.$model"
                :state="validateState($v.engrIdno)"
                id="idnoInputPopover"
                maxlength="10"
                @change="queryEngMember"
                trim
                lazy
              ></b-form-input>
              <b-popover
                v-if="formStatus !== FormStatusEnum.READONLY"
                target="idnoInputPopover"
                placement="right"
                triggers="hover focus"
                title="身分證字號查詢"
                content="依據身分證字號帶入技師資料。"
              ></b-popover>
            </i-form-group-check>
            <!-- 姓名 -->
            <i-form-group-check content-cols="4" :label="$t('label.userName') + '：'" :item="$v.engrName">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.engrName.$model }}</div>
              <b-form-input v-else v-model="$v.engrName.$model" :state="validateState($v.engrName)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 預定承辦日期 -->
            <i-form-group-check labelStar :label="$t('label.execDate1') + '：'" :dual1="$v.execDateStart1" :dual2="$v.execDateEnd1">
              <i-dual-date-picker :dual1.sync="$v.execDateStart1.$model" :dual2.sync="$v.execDateEnd1.$model"></i-dual-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 證書 -->
            <i-form-group-check content-cols="4" :label="$t('label.engengrcertificateIdList') + '：'" :item="$v.engengrcertificateIdList">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.engengrcertificateIdList.$model }}</div>
              <b-form-input v-else v-model="$v.engengrcertificateIdList.$model" :state="validateState($v.engengrcertificateIdList)" trim lazy></b-form-input>
            </i-form-group-check>
            <!-- 執照 -->
            <i-form-group-check content-cols="4" :label="$t('label.engrLicense') + '：'" :item="$v.engrLicenseDocNo">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.engrLicenseDocNo.$model }}</div>
              <b-form-input v-else v-model="$v.engrLicenseDocNo.$model" :state="validateState($v.engrLicenseDocNo)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 簽證範圍 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="4" :label="$t('label.recordRange') + '：'" :item="$v.recordRange">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.recordRange.$model }}</div>
              <b-form-input v-else v-model="$v.recordRange.$model" :state="validateState($v.recordRange)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 簽證項目 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="4" :label="$t('label.recordItems') + '：'" :item="$v.recordItems">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.recordItems.$model }}</div>
              <b-form-input v-else v-model="$v.recordItems.$model" :state="validateState($v.recordItems)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 核備文號 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="4" :label="$t('label.docNo') + '：'" :item="$v.docNo">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.docNo.$model }}</div>
              <b-form-input v-else v-model="$v.docNo.$model" :state="validateState($v.docNo)" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 結案日期 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="4" :label="$t('label.closingDate') + '：'" :item="$v.closeDate">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ $v.closeDate.$model | dateToString }}</div>
              <i-date-picker
                v-else
                v-model="$v.closeDate.$model"
                :state="validateState($v.closeDate)"
                placeholder="yyy/MM/dd"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <!-- <b-form-row v-if="role === RoleEnum.ADMIN"> -->
          <b-form-row>
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.remark') + '：'" :item="$v.remark1">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.remark1.$model"
                rows="4"
                maxlength="1000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
        </div>

        <div class="text-center mb-2">
          <!-- 儲存 -->
          <!-- <i-button v-if="formStatus !== FormStatusEnum.READONLY" type="save" @click="submitForm" /> -->
          <i-button type="save" @click="submitForm" />
          <!-- 返回 -->
          <i-button type="arrow-left" @click="toQueryView" />
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
// import EngEngrSubjectpointService from './eng-engr-subjectpoint.service';
import { useBvModal } from '@/shared/modal';
import { useNotification } from '@/shared/notification';
import { useValidation, validateState } from '@/shared/form';
import { handleBack } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { reactive, ref, toRefs, onMounted, defineComponent, unref } from '@vue/composition-api';
import { required, maxLength, nationalId, between, queryEngrByIdno } from '@/shared/validators';
import { ApplyItemEnum, FormStatusEnum, MemberTypeEnum, StatusEnum } from '@/shared/enum';
import { IEngEngrSubjectpoint, EngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import { useStore } from '@u3u/vue-hooks';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';

interface propsType {
  engEngrSubjectpoint: IEngEngrSubjectpoint;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0406EditInfo',
  props: {
    engEngrSubjectpoint: {
      required: false,
      type: Object,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    criteria: {
      required: false,
      type: Object,
    },
  },
  components: {
    ISwitchCase,
  },
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    //const engEngrSubjectpointService = new EngEngrSubjectpointService();

    const store = useStore();
    const $bvModal = useBvModal();
    const notificationService = useNotification();
    const formStatusRef = ref(props.formStatus);
    const { engEngrSubjectpoint, formStatus, criteria } = toRefs(props);

    const isReload = ref(false);

    const formOptions = reactive({
      caseType: [],
      projectType: [],
      recordItemsList: [],
    });

    onMounted(() => {
      getAllSysCodes();
    });
    
    function getAllSysCodes(){
      formOptions.caseType = [
          { value: '0', text: 'BOT案件' },
          { value: '1', text: '機關自辦' },
          { value: '2', text: '機關委辦' },
      
      ];
      formOptions.recordItemsList = [
          { value: '0', text: '設計' },
          { value: '1', text: '監造' },
          { value: '2', text: '其他' },
      
      ];
      // formOptions.projectType = [
      //     { value: '0', text: '台北市' },
      // ];
    }

    let formDefault = new EngEngrSubjectpoint();
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      caseNo: {},
      caseType: {},
      caseName: {},
      orgId: {},
      orgName: {},
      execOrgId: {},
      execOrgName: {},
      contactPerson: {},
      contactTel: {},
      contactConAddr: {},
      recordRangeList: {},
      recordItemsList: {},
      // addrTown: {},
      // addrOther: {},
      // addrCity: {},
      // addrZip: {},
      entrustitem: {},
      tenderDate: {},
      tenderprice: {},
      execDateStart: {},
      execDateEnd: {},
      projectType: {},
      TeSerCation: {},
      compLicenseNo: {},
      engrIdno: {},
      engrName: {},
      execDateStart1: {},
      execDateEnd1: {},
      engengrcertificateIdList: {},
      engrLicenseDocNo: {},
      subjects: {},
      recordRange: {},
      recordItems: {},
      docNo: {},
      closeDate: {},
      remark1: {},
      /*applyDate: { required: required },
      contractorName: {},
      idno: { required: required, nationalId: nationalId, isEngrIdno: queryEngrByIdno },
      chName: {},
      theme: { required: required, maxLength: maxLength(200) },
      content: { required: required, maxLength: maxLength(2000) },
      law: { required: required },
      subject: { required: required },
      point: { required: required, between: between(0, 999) },
      checkDesc: { maxLength: maxLength(200) },*/
      remark: { maxLength: maxLength(2000) },
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      setFormOptions();
      createDefaultValue(unref(engEngrSubjectpoint));
    });

    function setFormOptions() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    function createDefaultValue(data: IEngEngrSubjectpoint) {
      // console.log('engEngrSubjectpoint：', data);
      Object.assign(formDefault, data);
      setDate();
      reset();
      setSubjectList();
      // getContractorName();
    }

    function setDate() {
      formDefault.applyDate = parseToDate(formDefault.applyDate, '-');
    }

    function setSubjectList() {
      if (form.subjectList) {
        formOptions.subjectList = formOptions.subject.filter(item => form.subjectList.split(',').includes(item.value));
      } else {
        formOptions.subjectList = [];
        form.subject = undefined;
      }
    }

    function getContractorName() {
      axios
        .get(`/service/adm-account/${form.contractorId}`)
        .then(res => {
          formDefault.contractorName = res.data?.chName;
          form.contractorName = res.data?.chName;
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function submitForm() {
      $bvModal.msgBoxConfirm('請再次確認資料是否填寫正確？').then((isOK: boolean) => {
        if (isOK) {
          checkValidity().then((isValid: boolean) => {
            if (isValid) {
              //putForm();
            } else {
              $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
            }
          });
        }
      });
    }

    /*function putForm() {
      setFormBeforeSubmit();

      if (unref(formStatus) === FormStatusEnum.EDIT) {
        engEngrSubjectpointService
          .update(form)
          .then(engEngrSubjectpoint => {
            createDefaultValue(engEngrSubjectpoint);
            isReload.value = true;
          })
          .catch(notificationErrorHandler(notificationService));
      } else {
        engEngrSubjectpointService
          .create(form)
          .then(engEngrSubjectpoint => {
            createDefaultValue(engEngrSubjectpoint);
            isReload.value = true;
          })
          .catch(notificationErrorHandler(notificationService));
      }
    }*/

    function setFormBeforeSubmit() {
      const now = new Date();
      const offset = now.getTimezoneOffset();
      form.applyDate = form.applyDate ? new Date(form.applyDate.getTime() - offset * 60 * 1000) : null;

      form.subjectList = formOptions.subjectList.map(item => item.value).join(',');
      form.updateTime = new Date();
      form.updateUser = store.value.getters.account.id;
      if (unref(formStatus) === FormStatusEnum.CREATE) {
        form.contractorId = store.value.getters.account.id;
        form.createTime = new Date();
        form.createUser = store.value.getters.account.id;
        form.transferTime = null;
        form.transferFrom = null;
      }
    }

    function toQueryView() {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    }

    function pageReset() {
      reset();
      setSubjectList();
    }

    function queryEngMember() {
      if (!!form.idno && form.idno.length === 10) {
        axios
          .get(`/eng-members/${MemberTypeEnum.ENGR}/${form.idno}`)
          .then(copyMemberInfo)
          .catch(notificationErrorHandler(notificationService));
      }
    }

    function copyMemberInfo(res: AxiosResponse) {
      const data = res.data;
      form.engrId = data.engMemberId;
      form.chName = data.chName;
      form.enName = data.enName;

      axios
        .get(`/eng-engr-certificates/find-by-idno-and-status/${form.idno}/${StatusEnum.CLOSE}`)
        .then(getEngrCertificateSubjects)
        .catch(notificationErrorHandler(notificationService));
    }

    function getEngrCertificateSubjects(res: AxiosResponse) {
      const certificateList: IEngEngrCertificate[] = res.data;
      const set1 = new Set();
      const set2 = new Set();
      form.subjectList = certificateList
        .filter(item => !!item.engrCertificateDocNo || !!item.engrCertificateDocNoOld) // 過濾證書字號跟舊證書字號都沒有的資料
        .filter(item => !item.applyItemsList.split(',').includes(ApplyItemEnum.ABOLISH)) // 過濾申請項目包含廢止的資料
        .sort((a, b) => b.engEngrCertificateVersion - a.engEngrCertificateVersion) // 依據version降冪排列
        .filter(item => (!set1.has(item.engEngrCertificateId) ? set1.add(item.engEngrCertificateId) : false)) // 只取相同id第一筆資料(即最大version)
        .map(item => item.subject) // IEngEngrCertificate[] 取subject轉成 string[]
        .filter(item => (!set2.has(item) ? set2.add(item) : false)) // 過濾相同subject
        .sort((a, b) => Number(a) - Number(b)) // 依據subject代碼大小升冪排列
        .join(',');
      form.subject = undefined;
      setSubjectList();
    }
    const data = [
      {
        caseNo: {},
        caseType: {},
        caseName: {},
        orgId: {},
        orgName: {},
        contactPerson: {},
        contactTel: {},
        addrTown: {},
        addrOther: {},
        addrCity: {},
        addrZip: {},
        entrustitem: {},
        tenderDate: {},
        tenderprice: {},
        execDateStart: {},
        execDateEnd: {},
        projectType: {},
        compLicenseNo: {},
        remark: {},
        engrIdno: {},
        engrName: {},
        execDateStart1: {},
        execDateEnd1: {},
        engengrcertificateIdList: {},
        engrLicenseDocNo: {},
        subjects: {},
        docNo: {},
        closeDate: {},
        remark1: {},
      },
    ];

    return {
      FormStatusEnum,
      $v,
      pageReset,
      validateState,
      toQueryView,
      submitForm,
      //putForm,
      formOptions,
      formOptionsFormatter,
      queryEngMember,
      createDefaultValue,
      data,
      formStatusRef,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>

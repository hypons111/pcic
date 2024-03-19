<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            主管機關
          </h5>
        </div>
        <div class="card-body">
          <!-- 名稱 -->
          <b-form-row>
            <i-form-group-check labelStar
            :label="$t('label.bosName') + '：'" :item="$v.chName" class="col-12" label-cols="2" content-cols="10">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.chName.$model }}</div>
              <b-form-input v-else
                v-model="$v.chName.$model"
                maxlength="100"
                class="col-6"
                trim
                lazy
              ></b-form-input>
            <b-form-checkbox class="col-5 ml-2" v-if="formStatusRef !== FormStatusEnum.READONLY" 
            v-model="$v.isUse.$model"
            @change="changeUse">啟用
            </b-form-checkbox>
            </i-form-group-check>
          </b-form-row>

          <!-- User只在檢視畫面才會看到 -->
            <!-- 電話(一) -->
          <b-form-row>
            <i-form-group-check 
              :label="$t('label.guildPhone1') + '：' " :item="$v.tel1">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
              {{$v.tel1.$model}}</div>
              <b-form-input v-else
              maxlength="50" v-model="$v.tel1.$model">
              </b-form-input>
            </i-form-group-check>
            <!-- 電話2 -->
            <i-form-group-check
              :label="$t('label.guildPhone2') + '：' " :item="$v.tel2">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
              {{$v.tel2.$model}}</div>
              <b-form-input v-else
                maxlength="20" v-model="$v.tel2.$model">
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 傳真 -->
            <i-form-group-check 
             :label="$t('label.fax') + '：'" :item="$v.fax">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
              {{ $v.fax.$model }}</div>
              <b-form-input v-else maxlength="20" v-model="$v.fax.$model">
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- email -->
            <i-form-group-check 
            class="col-12"
              label-cols="2"
              content-cols="6"
              :label="$t('label.email') + '：'" :item="$v.mail1">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.mail1.$model }}</div>
              <b-form-input v-else v-model="$v.mail1.$model" trim lazy
              maxlength="50"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 地址 -->
            <i-form-group-check :label="$t('label.address1') + '：'"  
            :item="[$v.address1Town, $v.address1Other, $v.address1City]"
            class="col-12"
              label-cols="2"
              content-cols="8">
              <i-address-picker 
                :is-text="formStatusRef === FormStatusEnum.READONLY"
                :zip.sync="$v.address1Zip.$model"
                :city.sync="$v.address1City.$model"
                :town.sync="$v.address1Town.$model"
                :other.sync="$v.address1Other.$model"
                maxlength="100"
              >
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" 
              :label="$t('label.remark') + '：'" :item="$v.remark">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="$v.remark.$model"
                rows="4"
                maxlength="80"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <b-col offset="5">
              <!-- 返回 -->
          <i-button type="arrow-left" @click="toQueryView" />
          <!-- 確認送出 -->
          <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" 
          type="send-check" @click="submitForm(true)" />
            </b-col>
          </b-form-row>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import AccountService from '@/account/account.service';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import EngPrintFileService from '../eng9901/eng-print-file.service';
import EngManageService from '../eng0703/eng0703-manage.service';
import EngReviewLog from '@/components/eng/eng-review-log/eng-review-log.vue';
// import EngReviewLogService from '@/components/eng/eng-review-log/eng-review-log.service';
import { addReviewLogAndUpdateFunction } from '@/components/eng/eng-review-log/eng-review-log';
import { IEng0703 } from '@/shared/model/eng/eng0703.model';
import { EngManage } from '@/shared/model/eng/eng-manage.model';
import { EngEbillingCheck } from '@/shared/model/eng/eng-ebilling-check.model';
import { IEngReviewLog } from '@/shared/model/eng/eng-review-log.model';
import { AdmFile } from '@/shared/model/adm/adm-file.model';
import { useBvModal } from '@/shared/modal';
import { useNotification } from '@/shared/notification';
import { useValidation, validateState } from '@/shared/form';
import { handleBack } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { downloadFile, formOptionsFormatter } from '@/shared/formatter/common';
import { reactive, ref, toRefs, onMounted, defineComponent, inject, watch, unref, h, computed } from '@vue/composition-api';
import {
  required,
  requiredIf,
  maxLength,
  nationalId,
  email,
  enName,
  mobileTel,
  lineTel,
  applyItems,
  address,
  file,
  greaterThan,
  verticalPicture,
tel,
} from '@/shared/validators';
import {
  RoleEnum,
  FormStatusEnum,
  ReviewCaseTypeEnum,
  ReviewStatusEnum,
  MemberTypeEnum,
  ApplyItemEnum,
  ReceiveTypeEnum,
  ApplyTypeEnum,
  StatusEnum,
} from '@/shared/enum';

interface propsType {
  eng0703: IEng0703;
  formStatus: FormStatusEnum; // 檢視or編輯or薪增
  role: RoleEnum;  // 管理員or使用者
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0703EditInfo',
  props: {
    eng0703: {
      required: false,
      type: Object,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    role: {
      required: false,
    },
    criteria: {
      required: false,
      type: Object,
    },
  },
  components: {
    EngReviewLog,
  },
  setup(props) {
    const tel1Component = ref(null);
    const tel2Component = ref(null);

    const accountService = inject<() => AccountService>('accountService')();
    const engManageService = new EngManageService();
    const engPrintFileService = new EngPrintFileService();
    const admSysCodeService = new AdmSysCodeService();

    const { eng0703 } = toRefs(props);
    const formStatusRef = ref(props.formStatus);
    const criteriaRef = ref(props.criteria);
    const $bvModal = useBvModal();
    const notificationService = useNotification();

    const engEbillingCheckRef = ref(new EngEbillingCheck());
    const isReload = ref(false); // 是否需要重新查詢
    const isSamePermanentAddr = ref(false); // 同戶籍地址checkbox
    const isCheckRequired = ref(false); // 儲存 = false; 送出 = true

    // const isShow = false;

    // const engReviewLogService = new EngReviewLogService();
    // const engReviewLog = ref(null); // 綁定EngReviewLog元件

    const formOptions = reactive({
      status: [],
      applyItemsList: [],
      subject: [],
      examType: [],
      identityType: [],
      sex: [],
      receiveType: [],
      isClose: [],
    });

    let formDefault = new EngManage();
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      chName: { required: required, maxLength: maxLength(100) },
      tel1: { maxLength: maxLength(50) },
      tel2:{ maxLength: maxLength(20) },
      fax: { maxLength: maxLength(20)},
      mail1:{ email: email, maxLength: maxLength(50)},
      address1Zip:{},
      address1City:{ },
      address1Town:{equalsCityBoolean: address(() => form.address1Town)},
      address1Other: { equalsCityBoolean: address(() => form.address1Other) },
      remark: { maxLength: maxLength(160) },
      isUse:{}
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);   
    

    onMounted(() => {
      // setFormOptions();
      createDefaultValue(unref(eng0703));
    });

    function createDefaultValue(data: IEng0703) {
      Object.assign(formDefault, data);
      // setApplyItems();
      reset();
      // getContractorName();
      // getPic();
      // engEbillingCheckRef.value = data.engEbillingCheckDTO ? data.engEbillingCheckDTO : new EngEbillingCheck();
    }

    function changeUse() {
      formDefault.isUse = !formDefault.isUse;
      
    }

    function submitForm(isCheckReq: boolean) {
      isCheckRequired.value = isCheckReq;
      $v.value.$reset(); // 先清除欄位$dirty狀態，才能依據isCheckRequired(是否驗證必填)重新驗證

      // 不加this.$nextTick，checkValidity() 會比上方 $v.value.$reset() 先執行!!!
      this.$nextTick(() => {
        checkValidity().then((isValid: boolean) => {
          if (isValid) {
            if (isCheckReq) {
              $bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
                if (isOK) {
                  putForm();
                }
              });
            } else {
              putForm();
            }
          } else {
            $bvModal.msgBoxOk(isCheckReq ? '欄位尚未填寫完畢，請於輸入完畢後再行送出。' : '欄位尚未填寫完整，請輸入完整或不輸入再行儲存。');
          }
        });
      });
    }

    async function putForm() {
      const formData = await setFormBeforeSubmit();

      if (formStatusRef.value === FormStatusEnum.EDIT) {
        engManageService
          .updateEng0703(formData)
          .then(eng0703 => {
            createDefaultValue(eng0703);
            isReload.value = true;
            $bvModal.msgBoxConfirm('儲存成功!').then((isOK: boolean) => {

            })
          })
          .catch(notificationErrorHandler(notificationService));
      } else {
        engManageService
          .createEng0703(formData)
          .then(eng0703 => {
            // 1.新增完繼續按儲存或送出才會變修改 2.要在createDefaultValue上面才不會改到applyItems
            formStatusRef.value = FormStatusEnum.EDIT;
            createDefaultValue(eng0703);
            isReload.value = true;
            $bvModal.msgBoxConfirm('儲存成功!').then((isOK: boolean) => {

            })
          })
          .catch(notificationErrorHandler(notificationService));
      }
      // this.isShow = !this.isShow;

    }

    async function setFormBeforeSubmit(isDiscard: boolean = false): Promise<FormData> {
      const now = new Date();
      const offset = now.getTimezoneOffset();
      // form.applyDate = form.applyDate ? new Date(form.applyDate.getTime() - offset * 60 * 1000) : null;
      // form.receiveDate = form.receiveDate ? new Date(form.receiveDate.getTime() - offset * 60 * 1000) : null;
      // form.publicDate = form.publicDate ? new Date(form.publicDate.getTime() - offset * 60 * 1000) : null;
      // form.birthDate = form.birthDate ? new Date(form.birthDate.getTime() - offset * 60 * 1000) : null;

      form.updateTime = new Date();
      form.updateUser = accountService.user.id;
      // READONLY 可以作廢會修改資料，所以也要擋
      if (formStatusRef.value !== FormStatusEnum.EDIT && formStatusRef.value !== FormStatusEnum.READONLY) {
        // form.engEngrCertificateVersion = formStatusRef.value === FormStatusEnum.CREATE ? 1 : await getVersion();
        form.newFrom = formStatusRef.value;
        form.status = isCheckRequired.value ? StatusEnum.REVIEW2_1 : StatusEnum.TEMP;
        form.contractorId = props.role === RoleEnum.ADMIN ? accountService.user.id : null;
        // form.applyType = props.role === RoleEnum.ADMIN ? ApplyTypeEnum.PAPER_APPLY : ApplyTypeEnum.ONLINE_APPLY;
        form.createTime = new Date();
        form.createUser = accountService.user.id;
        form.reviewStaff = null;
        form.reviewTime = null;
        form.reviewDesc = null;
        form.transferTime = null;
        form.transferFrom = null;
      }


      const formData = new FormData();
      formData.append('form', new Blob([JSON.stringify(form)], { type: 'application/json' }));

      return formData;
    }

    function changeFormStatus(formStatus: FormStatusEnum) {
      formStatusRef.value = formStatus;
      // setApplyItems();
      reset();
    }

    function toQueryView() {
      handleBack({ isReload: isReload.value, isNotKeepAlive: true });
      isReload.value = false;
    }

    function pageReset() {
      reset();
    }

    function samePermanentAddr() {
      
    }

    function appendixSelected(files: File[]) {}

    return {
      tel1Component,
      tel2Component,
      FormStatusEnum,
      RoleEnum,
      ReviewCaseTypeEnum,
      ReviewStatusEnum,
      engEbillingCheckRef,
      formStatusRef,
      changeFormStatus,
      $v,
      validateState,
      pageReset,
      toQueryView,
      submitForm,
      putForm,
      formOptions,
      formOptionsFormatter,
      samePermanentAddr,
      isSamePermanentAddr,
      appendixSelected,
      criteriaRef,
      changeUse
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>
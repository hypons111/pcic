<template>
  <b-container>
    <b-card no-body>
      <b-card-header>
        <h5 class="m-0">證書綜合報表</h5>
      </b-card-header>
      <b-card-body>
        <b-card title="查詢條件" title-tag="h5" class="mb-4">
          <hr />
          <!-- <b-card no-body class="mb-4">
          <b-card-header class="py-2">
            <h5 class="m-0">查詢條件</h5>
          </b-card-header> -->
          <b-card-body class="pr-5">
            <b-form-row>
              <!-- 流水號 -->
              <i-form-group-check :label="$t('label.engEngrCertificateNo') + '：'" :item="$v.engEngrCertificateNo">
                <b-form-input v-model="$v.engEngrCertificateNo.$model" trim lazy></b-form-input>
              </i-form-group-check>
              <!-- 科別 -->
              <i-form-group-check content-cols="4" :label="$t('label.subject') + '：'" :item="$v.subject">
                <b-form-select v-model="$v.subject.$model" :options="formOptions.subject">
                  <template #first>
                    <option :value="undefined">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 技師姓名 -->
              <i-form-group-check content-cols="6" :label="$t('label.chName') + '：'" :item="$v.chName">
                <b-form-input v-model="$v.chName.$model" trim lazy> </b-form-input>
              </i-form-group-check>
              <!-- 身分證字號 -->
              <i-form-group-check content-cols="6" :label="$t('label.idno') + '：'" :item="$v.idno">
                <b-form-input v-model="$v.idno.$model" trim lazy></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 性別 -->
              <i-form-group-check content-cols="4" :label="$t('label.sex') + '：'" :item="$v.sex">
                <b-form-select v-model="$v.sex.$model" :options="formOptions.sex">
                  <template #first>
                    <option :value="undefined">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <!-- 考試種類 -->
              <i-form-group-check content-cols="4" :label="$t('label.examType') + '：'" :item="$v.examType">
                <b-form-select v-model="$v.examType.$model" :options="formOptions.examType">
                  <template #first>
                    <option :value="undefined">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 申請方式 -->
              <i-form-group-check content-cols="4" :label="$t('label.applyType') + '：'" :item="$v.applyType">
                <b-form-select v-model="$v.applyType.$model" :options="formOptions.applyType">
                  <template #first>
                    <option :value="undefined">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <!-- 技師證書字號 -->
              <i-form-group-check :label="$t('label.engrCertificateDocNo') + '：'" :item="$v.engrCertificateDocNo">
                <div>
                  技證字第 <b-form-input class="col-6 d-inline-block" v-model="$v.engrCertificateDocNo.$model" trim lazy></b-form-input> 號
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 戶籍地址 -->
              <i-form-group-check
                class="col-12"
                label-cols="2"
                content-cols="10"
                :label="$t('label.permanentAddr') + '：'"
                :item="[$v.permanentAddrTown, $v.permanentAddrOther, $v.permanentAddrCity]"
              >
                <i-address-picker
                  :zip.sync="$v.permanentAddrZip.$model"
                  :city.sync="$v.permanentAddrCity.$model"
                  :town.sync="$v.permanentAddrTown.$model"
                  :other.sync="$v.permanentAddrOther.$model"
                  :cityState="validateState($v.permanentAddrCity)"
                  :townState="validateState($v.permanentAddrTown)"
                  :otherState="validateState($v.permanentAddrOther)"
                >
                </i-address-picker>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 通訊地址 -->
              <i-form-group-check
                class="col-12"
                label-cols="2"
                content-cols="10"
                :label="$t('label.contactAddr') + '：'"
                :item="[$v.contactAddrTown, $v.contactAddrOther, $v.contactAddrCity]"
              >
                <i-address-picker
                  :zip.sync="$v.contactAddrZip.$model"
                  :city.sync="$v.contactAddrCity.$model"
                  :town.sync="$v.contactAddrTown.$model"
                  :other.sync="$v.contactAddrOther.$model"
                  :cityState="validateState($v.contactAddrCity)"
                  :townState="validateState($v.contactAddrTown)"
                  :otherState="validateState($v.contactAddrOther)"
                >
                </i-address-picker>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 收文日期 -->
              <i-form-group-check :label="$t('label.receiveDate') + '：'" :dual1="$v.receiveDateStart" :dual2="$v.receiveDateEnd">
                <i-dual-date-picker :dual1.sync="$v.receiveDateStart.$model" :dual2.sync="$v.receiveDateEnd.$model"></i-dual-date-picker>
              </i-form-group-check>
              <!-- 收文文號 -->
              <i-form-group-check content-cols="4" :label="$t('label.receiveNo') + '：'" :item="$v.receiveNo">
                <b-form-input v-model="$v.receiveNo.$model" :state="validateState($v.receiveNo)" trim lazy> </b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 發證日期 -->
              <i-form-group-check :label="$t('label.publicDate') + '：'" :dual1="$v.publicDateStart" :dual2="$v.publicDateEnd">
                <i-dual-date-picker :dual1.sync="$v.publicDateStart.$model" :dual2.sync="$v.publicDateEnd.$model"></i-dual-date-picker>
              </i-form-group-check>
              <!-- 承辦人 -->
              <i-form-group-check content-cols="4" :label="$t('label.contractorId') + '：'" :item="$v.contractorId">
                <b-form-select v-model="$v.contractorId.$model" :options="formOptions.contractorId">
                  <template #first>
                    <option :value="undefined">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 姓名(英) -->
              <i-form-group-check content-cols="6" :label="$t('label.enName') + '：'" :item="$v.enName">
                <b-form-input
                  v-model="$v.enName.$model"
                  :state="validateState($v.enName)"
                  placeholder="格式：LI, TA-TUNG"
                  trim
                  lazy
                ></b-form-input>
                <!-- 處理狀態 -->
              </i-form-group-check>
              <i-form-group-check content-cols="4" :label="$t('label.status') + '：'" :item="$v.status">
                <b-form-select v-model="$v.status.$model" :options="formOptions.status">
                  <template #first>
                    <option :value="undefined">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 取件方式 -->
              <i-form-group-check content-cols="4" :label="$t('label.receiveType') + '：'" :item="$v.receiveType">
                <b-form-select v-model="$v.receiveType.$model" :options="formOptions.receiveType">
                  <template #first>
                    <option :value="undefined">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 付款方式 -->
              <i-form-group-check content-cols="4" :label="$t('label.payType') + '：'" :item="$v.payType">
                <b-form-select v-model="$v.payType.$model" :options="formOptions.payType">
                  <template #first>
                    <option :value="undefined">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <!-- 虛擬帳號 -->
              <i-form-group-check :label="$t('label.virtualAccount') + ' (98685)：'">
                <b-form-input v-model="$v.virtualAccount.$model" :state="validateState($v.virtualAccount)" trim lazy></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 申請日期 -->
              <i-form-group-check :label="$t('label.applyDate') + '：'" :dual1="$v.applyDateStart" :dual2="$v.applyDateEnd">
                <i-dual-date-picker :dual1.sync="$v.applyDateStart.$model" :dual2.sync="$v.applyDateEnd.$model"></i-dual-date-picker>
              </i-form-group-check>
              <!-- 身分別 -->
              <i-form-group-check content-cols="4" :label="$t('label.identityType') + '：'" :item="$v.identityType">
                <b-form-select v-model="$v.identityType.$model" :options="formOptions.identityType">
                  <template #first>
                    <option :value="undefined">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 只匯出最新證書(含已死亡) -->
              <i-form-group-check label-cols="6" content-cols="6" label="只匯出最新證書(含已死亡)：" :item="$v.onlyNewestIncludingDeath">
                <b-form-checkbox v-model="$v.onlyNewestIncludingDeath.$model" value="1" unchecked-value="0" switch>是</b-form-checkbox>
              </i-form-group-check>
              <b-col offset="2">
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-card-body>
        </b-card>

        <b-card title="匯出欄位" title-tag="h5" class="mb-4">
          <hr />
          <!-- <b-card no-body class="mb-4">
          <b-card-header class="py-2">
            <h5 class="m-0">匯出欄位</h5>
          </b-card-header> -->
          <b-card-body>
            <b-form-checkbox value="all">請選擇</b-form-checkbox>
            <b-form-checkbox-group class="row">
              <b-col cols="2">
                <b-form-checkbox value="subject">{{ $t('label.subject') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="chName">{{ $t('label.chName') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="idno">{{ $t('label.idno') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="sex">{{ $t('label.sex') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="birthDate">{{ $t('label.birthDate') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="examType">{{ $t('label.examType') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="applyItems">{{ $t('label.applyItems') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="engrCertificateDocNo">{{ $t('label.engrCertificateDocNo') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="applyType">{{ $t('label.applyType') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="permanentAddr">{{ $t('label.permanentAddr') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="contactAddr">{{ $t('label.contactAddr') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="contactTelHome">{{ $t('label.contactTelHome') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="contactTelOffice">{{ $t('label.contactTelOffice') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="mobileTel">{{ $t('label.mobileTel') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="examNo">{{ $t('label.examNo') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="email">{{ $t('label.email') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="fax">{{ $t('label.fax') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="receiveNo">{{ $t('label.receiveNo') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="engrCertificateDocNoOld">{{ $t('label.engrCertificateDocNoOld') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="enName">{{ $t('label.enName') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="status">{{ $t('label.status') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="receiveDate">{{ $t('label.receiveDate') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="receiveType">{{ $t('label.receiveType') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="payType">{{ $t('label.payType') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="virtualAccount">{{ $t('label.virtualAccount') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="contractorId">{{ $t('label.contractorId') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="applyDate">{{ $t('label.applyDate') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="publicDate">{{ $t('label.publicDate') }}</b-form-checkbox>
              </b-col>
              <b-col cols="2">
                <b-form-checkbox value="identityType">{{ $t('label.identityType') }}</b-form-checkbox>
              </b-col>
            </b-form-checkbox-group>
          </b-card-body>
        </b-card>
        <div class="text-center">
          <i-button type="box-arrow-up-right" @click="reset"></i-button>
          <i-button type="arrow-left" @click="toQueryView"></i-button>
        </div>
      </b-card-body>
    </b-card>
  </b-container>
</template>

<script lang="ts">
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { defineComponent, reactive, onMounted } from '@vue/composition-api';
import { handleBack } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { ReceiveTypeEnum, StatusEnum } from '@/shared/enum';

export default defineComponent({
  name: 'Eng0108',
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    const $bvModal = useBvModal();
    const notificationService = useNotification();

    onMounted(() => {
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.status = formOptions.status.filter(i => i.value !== StatusEnum.RETURN && i.value !== StatusEnum.REVIEW1);
      formOptions.receiveType = formOptions.receiveType.filter(i => i.value !== ReceiveTypeEnum.COMPANY);
    }

    const formOptions = reactive({
      status: [],
      applyItemsList: [],
      applyType: [],
      subject: [],
      examType: [],
      identityType: [],
      sex: [],
      receiveType: [],
      payType: [],
    });

    const formDefault = {
      engEngrCertificateId: undefined,
      engEngrCertificateVersion: undefined,
      engEngrCertificateNo: undefined,
      status: undefined,
      reviewStaff: undefined,
      reviewTime: undefined,
      reviewDesc: undefined,
      applyItemsList: undefined,
      applyItems: undefined,
      applyType: undefined,
      applyDateStart: undefined,
      applyDateEnd: undefined,
      idno: undefined,
      chName: undefined,
      enName: undefined,
      birthDate: undefined,
      sex: undefined,
      contactTelOffice: undefined,
      contactTelHome: undefined,
      mobileTel: undefined,
      fax: undefined,
      email: undefined,
      permanentAddrZip: undefined,
      permanentAddrCity: undefined,
      permanentAddrTown: undefined,
      permanentAddrOther: undefined,
      contactAddrZip: undefined,
      contactAddrCity: undefined,
      contactAddrTown: undefined,
      contactAddrOther: undefined,
      identityType: undefined,
      addrForeign: undefined,
      subject: undefined,
      engrCertificateDocNo: undefined,
      engrCertificateDocNoOld: undefined,
      examType: undefined,
      examNo: undefined,
      examNoOld: undefined,
      receiveDateStart: undefined,
      receiveDateEnd: undefined,
      receiveNo: undefined,
      publicDateStart: undefined,
      publicDateEnd: undefined,
      receiveType: undefined,
      receiveAddrZip: undefined,
      receiveAddrCity: undefined,
      receiveAddrTown: undefined,
      receiveAddrOther: undefined,
      contractorId: undefined,
      contractorName: undefined,
      remark: undefined,
      createTime: undefined,

      payType: undefined,
      virtualAccount: undefined,
      onlyNewestIncludingDeath: undefined,
    };
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      engEngrCertificateId: {},
      engEngrCertificateVersion: {},
      engEngrCertificateNo: {},
      status: {},
      reviewStaff: {},
      reviewTime: {},
      reviewDesc: {},
      applyItemsList: {},
      applyItems: {},
      applyType: {},
      applyDateStart: {},
      applyDateEnd: {},
      idno: {},
      chName: {},
      enName: {},
      birthDate: {},
      sex: {},
      contactTelOffice: {},
      contactTelHome: {},
      mobileTel: {},
      fax: {},
      email: {},
      permanentAddrZip: {},
      permanentAddrCity: {},
      permanentAddrTown: {},
      permanentAddrOther: {},
      contactAddrZip: {},
      contactAddrCity: {},
      contactAddrTown: {},
      contactAddrOther: {},
      identityType: {},
      addrForeign: {},
      subject: {},
      engrCertificateDocNo: {},
      engrCertificateDocNoOld: {},
      examType: {},
      examNo: {},
      examNoOld: {},
      receiveDateStart: {},
      receiveDateEnd: {},
      receiveNo: {},
      publicDateStart: {},
      publicDateEnd: {},
      receiveType: {},
      receiveAddrZip: {},
      receiveAddrCity: {},
      receiveAddrTown: {},
      receiveAddrOther: {},
      contractorId: {},
      contractorName: {},
      remark: {},
      createTime: {},

      payType: {},
      virtualAccount: {},
      onlyNewestIncludingDeath: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    function toQueryView() {
      handleBack({ isNotKeepAlive: true });
    }

    return {
      $v,
      reset,
      validateState,
      formOptions,
      toQueryView,
    };
  },
});
</script>
<style scoped></style>
<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>
          </div>
        </div>
        <div class="card-body col-12">
          <b-form-row>
            <!-- 流水號 -->
            <i-form-group-check :label="$t('label.serialNumber') + '：'"
            >
              <b-form-input v-model="$v.serialNumber.$model" trim>
              </b-form-input>
            </i-form-group-check>
            <!-- 營業範圍(科別) -->
            <i-form-group-check :label="$t('label.saleAreaList') + '：'" 
              >
              <b-form-select v-model="$v.subjectList.$model" 
              :options="formOptions.subject">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 姓名 -->
            <i-form-group-check :label="$t('label.name') + '：'"
            >
              <b-form-input v-model="$v.name.$model" trim>
              </b-form-input>
            </i-form-group-check>
            <!-- 身分證字號 -->
            <i-form-group-check :label="$t('label.idno') + '：'"
            >
              <b-form-input v-model="$v.idno.$model" trim>
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 性別 -->
            <i-form-group-check :label="$t('label.sex') + '：'" 
            >
              <b-form-select v-model="$v.sex.$model" 
              :options="formOptions.sex">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 執業方式 -->
            <i-form-group-check :label="$t('label.execType') + '：'" 
            >
              <b-form-select v-model="$v.execType.$model" 
              :options="formOptions.execType">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 申請項目 -->
            <i-form-group-check :label="$t('label.applyItems') + '：'" 
            >
              <b-form-select v-model="$v.applyItem.$model" 
              :options="formOptions.applyItemsList">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 登記證字號 / 執照字號 -->
            <i-form-group-check :label="$t('label.cstLicenseDocNo') + '：'"
            >
              <b-form-input v-model="$v.cstLicenseDocNo.$model" trim>
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 戶籍地址 -->
            <i-form-group-check 
            class="col-12" label-cols="2" content-cols="8" 
            :label="$t('label.permanentAddr') + '：'" 
            :item="[$v.town, $v.other, $v.city]">
              <i-address-picker
                :zip.sync="$v.zip.$model"
                :city.sync="$v.city.$model"
                :town.sync="$v.town.$model"
                :other.sync="$v.other.$model"
              >
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 執業機構地址 -->
            <i-form-group-check 
            class="col-12" label-cols="2" content-cols="8" 
            :label="$t('label.engrCompAddr') + '：'" 
            :item="[$v.compTown, $v.compOther, $v.compCity]">
              <i-address-picker
                :zip.sync="$v.compZip.$model"
                :city.sync="$v.compCity.$model"
                :town.sync="$v.compTown.$model"
                :other.sync="$v.compOther.$model"
              >
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 申請日期 -->
            <i-form-group-check  
            :label="$t('label.applyDate') + '：'" 
            >
              <i-dual-date-picker 
              :dual1.sync="$v.applyDateStart.$model" 
              :dual2.sync="$v.applyDateEnd.$model">
              </i-dual-date-picker>
            </i-form-group-check>
            <!-- 申請方式 -->
            <i-form-group-check  
            :label="$t('label.applyType') + '：'" 
            >
              <b-form-select v-model="$v.applyType.$model" 
              :options="formOptions.applyType">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 發證日期 -->
            <i-form-group-check  
            :label="$t('label.publicDate') + '：'" 
            >
              <i-dual-date-picker 
              :dual1.sync="$v.publicDateStart.$model" 
              :dual2.sync="$v.publicDateEnd.$model">
              </i-dual-date-picker>
            </i-form-group-check>
            <!-- 收文文號 -->
            <i-form-group-check :label="$t('label.receiveNo') + '：'"
            >
              <b-form-input v-model="$v.receiveNo.$model" trim>
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 承辦人 -->
            <i-form-group-check :label="$t('label.contactPersonId') + '：'" 
            >
              <b-form-select v-model="$v.contactPersonId.$model" 
              :options="formOptions.contactPersonId">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 狀態 -->
            <i-form-group-check :label="$t('label.status') + '：'" 
            >
              <b-form-select v-model="$v.status.$model" 
              :options="formOptions.status">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 有效日期(起) -->
            <i-form-group-check  
            :label="$t('label.engEngrEffectDataStart') + '：'" 
            >
              <i-dual-date-picker 
              :dual1.sync="$v.engEngrEffectDataStart1.$model" 
              :dual2.sync="$v.engEngrEffectDataStart2.$model">
              </i-dual-date-picker>
            </i-form-group-check>
            <!-- 有效日期(迄) -->
            <i-form-group-check  
            :label="$t('label.engEngrEffectDataEnd') + '：'" 
            >
              <i-dual-date-picker 
              :dual1.sync="$v.engEngrEffectDataEnd1.$model" 
              :dual2.sync="$v.engEngrEffectDataEnd2.$model">
              </i-dual-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 身分別 -->
            <i-form-group-check :label="$t('label.identityType') + '：'" 
            >
              <b-form-select v-model="$v.identityType.$model" 
              :options="formOptions.identityType">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 領有XCA憑證 -->
            <i-form-group-check :label="$t('label.engEngrIsXcaCert') + '：'" 
            >
              <b-form-select v-model="$v.isXcaCert.$model" 
              :options="formOptions.isXcaCert">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 具有七年以上實務經驗 -->
            <i-form-group-check :label="$t('label.engEngrIsMore7Year') + '：'" 
            >
              <b-form-select v-model="$v.engEngrIsMore7Year.$model" 
              :options="formOptions.engEngrIsMore7Year">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 取件方式 -->
            <i-form-group-check :label="$t('label.receiveType') + '：'" 
            >
              <b-form-select v-model="$v.receiveType.$model" 
              :options="formOptions.receiveType">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 付款方式 -->
            <i-form-group-check :label="$t('label.payType') + '：'" 
            >
              <b-form-select v-model="$v.payType.$model" 
              :options="formOptions.payType">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 虛擬帳號 -->
            <i-form-group-check :label="$t('label.virtualAccount') + '(98685)：'"
            >
              <b-form-input v-model="$v.virtualAccount.$model" trim>
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 只匯出最新執照(含已死亡) -->
              <i-form-group-check class="col-8"
									label-cols="3"
									content-cols="5"
                :item="$v.onlyNew"
              >
                <b-form-checkbox-group
                  v-model="$v.onlyNew.$model"
                  :options="formOptions.onlyNew"
                ></b-form-checkbox-group>
              </i-form-group-check>
              <!-- 清除 -->
              <i-button type="x-circle" @click="clear"/>
          </b-form-row>
        </div>
      </div>
    </section>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                匯出欄位
              </h5>
            </div>
          </div>
        </div>
        <div class="card-body col-12">
          <i-form-group-check class="col-12" label-cols="0" 
          content-cols="12" >
            <!-- 全選checkbox -->
            <div class="col-4 d-inline-block">
              <b-form-checkbox v-model="isAllCheck" 
            @change="changeCheck">全選</b-form-checkbox>
            </div>
          </i-form-group-check>
        <!-- 欄位的checkbox們 -->
          <i-form-group-check class="col-12" label-cols="0" 
          content-cols="12" :item="$v.columnList">
          <div v-for="option in formOptions.columnList" class="col-3 d-inline-block">
            <b-form-checkbox v-model="option.value">
              {{ option.text }}
            </b-form-checkbox>
          </div>
        </i-form-group-check>
          <b-form-row>
            <b-col offset="8">
              <!-- 匯出 -->
            <i-button @click="submitForm" type="box-arrow-up-right"/>
            </b-col>
          </b-form-row>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, ref, reactive } from '@vue/composition-api';
import {useValidation, validateState} from '@/shared/form';
import i18n from '@/shared/i18n';
import axios from 'axios';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import { EngCaseTotalDTO } from '@/shared/model/eng/eng-case.model';
import { useBvModal } from '@/shared/modal';

export default {
  name: 'Eng0109Export',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    let isAllCheck = ref(false);
    const admSysCodeService = new AdmSysCodeService();
    const $bvModal = useBvModal();
    const stepVisible = reactive({ step1: true, step2: true })
    const formOptions = reactive({
      subject: [],  //
      applyType: [],
      identityType: [],
      isXcaCert: [],
      engEngrIsMore7Year: [],
      receiveType: [],
      payType: [],
      execType: [],
      applyItemsList: [],
      onlyNew: [],
      status: [],
      sex: [],
      contactPersonId: [],
      columnList: [],
    })

    const formDefault = {
      serialNumber: '',
      subjectList: '',
      applyType: '',
      identityType: '',
      isXcaCert: '',
      engEngrIsMore7Year: '',
      receiveType: '',
      payType: '',
      virtualAccount: '',
      name: '',
      idno: '',
      sex: '',
      execType: '',
      applyItem: '',
      cstLicenseDocNo: '',
      zip: '',
      city: undefined,
      town: undefined,
      other: '',
      compZip: '',
      compCity: undefined,
      compTown: undefined,
      compOther: '',
      applyDateStart: null,
      applyDateEnd: null,
      publicDateStart: null,
      publicDateEnd: null,
      engEngrEffectDataStart1: null,
      engEngrEffectDataStart2: null,
      engEngrEffectDataEnd1: null,
      engEngrEffectDataEnd2: null,
      onlyNew: [],
      status: '',
      contactPersonId: '',
      receiveNo: '',
    }
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      serialNumber: {},
      subjectList: {},
      applyType: {},
      identityType: {},
      isXcaCert: {},
      engEngrIsMore7Year: {},
      receiveType: {},
      payType: {},
      virtualAccount: {},
      name: {},
      idno: {},
      sex: {},
      execType: {},
      applyItem: {},
      cstLicenseDocNo: {},
      zip: {},
      city: {},
      town: {},
      other: {},
      compZip: {},
      compCity: {},
      compTown: {},
      compOther: {},
      applyDateStart: {},
      applyDateEnd: {},
      publicDateStart: {},
      publicDateEnd: {},
      engEngrEffectDataStart1: {},
      engEngrEffectDataStart2: {},
      engEngrEffectDataEnd1: {},
      engEngrEffectDataEnd2: {},
      onlyNew: {},
      status: {},
      contactPersonId: {},
      receiveNo: {},
    }
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      getAllSysCodes();
    })

    const clear = () => {
      reset();
    }

    function submitForm(isCheckReq: boolean) {
      $v.value.$reset();
      this.$nextTick(() => {
        $bvModal.msgBoxConfirm('是否確認匯出？').then((isOK: boolean) => {
          if (isOK) {
            $bvModal.msgBoxOk('匯出成功!');
          }
      })})
    }

    const changeCheck = () => {
      if(isAllCheck.value) {
        for(let i = 0; i < formOptions.columnList.length; i++) {
          formOptions.columnList[i].value = true;
        }
      } else if(!isAllCheck.value) {
        for(let i = 0; i < formOptions.columnList.length; i++) {
          formOptions.columnList[i].value = false;
        }
      }
    }

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.contactPersonId = [
        { value: '0', text: '劉芳男' },
        { value: '0', text: '蔣瑋玲' },
        { value: '0', text: '陳家乾' },
      ];
      formOptions.isXcaCert = [
        { value: '1', text: '是' },
        { value: '0', text: '否' }
      ];
      formOptions.engEngrIsMore7Year = [
        { value: '1', text: '是' },
        { value: '0', text: '否' }
      ];
      formOptions.onlyNew = [
        { value: '0', text: '只匯出最新執照(含已死亡)'},
      ];
      formOptions.columnList = [
        { value: false, column: 'subjectList', text: '科別' },
        { value: false, column: 'chName', text: '姓名' },
        { value: false,  column: 'idno', text: '身分證字號' },
        { value: false,  column: 'sex', text: '性別' },
        { value: false,  column: 'execType', text: '執業方式' },
        { value: false,  column: 'applyItem', text: '申請項目' },
        { value: false,  column: 'engrLicenseDocNo', text: '技師執業執照字號' },
        { value: false,  column: 'permanentAddr', text: '戶籍地址' },
        { value: false,  column: 'compAddr', text: '執業公司地址' },
        { value: false,  column: 'applyDate', text: '申請日期' },
        { value: false,  column: 'receiveDocNo', text: '收文文號' },
        { value: false,  column: 'publicDate', text: '發證日期' },
        { value: false,  column: 'contactPersonId', text: '承辦人' },
        { value: false,  column: 'compChName', text: '執業公司名稱' },
        { value: false,  column: 'effectDateStart', text: '有效日期(起)' },
        { value: false,  column: 'effectDateEnd', text: '有效日期(迄)' },
        { value: false,  column: 'status', text: '處理狀態' },
        { value: false,  column: 'receiveAddr', text: '回函地址' },
        { value: false,  column: 'payType', text: '付款方式' },
        { value: false,  column: 'code', text: '虛擬帳號' },
        { value: false,  column: 'identityType', text: '身分別' },
        { value: false,  column: 'isXcaCert', text: '是否領有XCA憑證號' },
        { value: false,  column: 'engEngrIsMore7Year', text: '是否具有七年以上實務經驗' },
        { value: false,  column: 'mobileTel', text: '手機' },
        { value: false,  column: 'email', text: 'EMAIL' },
        { value: false,  column: 'birthdate', text: '生日' },
        { value: false,  column: 'applyType', text: '申請方式' },
        { value: false,  column: 'isDead', text: '死亡註記' },
      ];
    }

    return {
      $v,
      formOptions,
      clear,
      isAllCheck,
      submitForm,
      changeCheck,
    }
  }
}
</script>
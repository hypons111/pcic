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
              <b-form-select v-model="$v.saleAreaList.$model" 
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
            <!-- 負責人是否為技師 -->
            <i-form-group-check :label="$t('label.isMasterEngr') + '：'" 
            >
              <b-form-select v-model="$v.isMasterEngr.$model" 
              :options="formOptions.isMasterEngr">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 區域主管機關 -->
            <i-form-group-check :label="$t('label.areaMasterOrg') + '：'" 
            >
              <b-form-select v-model="$v.areaMasterOrg.$model" 
              :options="formOptions.areaMasterOrg">
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
              :options="formOptions.applyItem">
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
            <!-- 負責人戶籍地址 -->
            <i-form-group-check 
            class="col-12" label-cols="2" content-cols="8" 
            :label="$t('label.masterPermanentAddr') + '：'" 
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
            <!-- 機構(或顧問公司)地址 -->
            <i-form-group-check 
            class="col-12" label-cols="2" content-cols="8" 
            :label="$t('label.compAddr') + '：'" 
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
            <!-- 發證日期 -->
            <i-form-group-check  
            :label="$t('label.publicDate') + '：'" 
            >
              <i-dual-date-picker 
              :dual1.sync="$v.publicDateStart.$model" 
              :dual2.sync="$v.publicDateEnd.$model">
              </i-dual-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
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
            <!-- 機構名稱 -->
            <i-form-group-check :label="$t('label.compName') + '：'"
            >
              <b-form-input v-model="$v.compName.$model" trim>
              </b-form-input>
            </i-form-group-check>
            <!-- 公司統一編號 -->
            <i-form-group-check :label="$t('label.eclCompIdno') + '：'"
            >
              <b-form-input v-model="$v.eclCompIdno.$model" trim>
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
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
            <!-- 繳款方式 -->
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
            <!-- 符合條例第五條第一項之(A~F) -->
            <i-form-group-check :label="$t('label.agreeLow1') + '：'" 
            >
              <b-form-select v-model="$v.agreeLaw1.$model" 
              :options="formOptions.agreeLaw1">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 符合條例第六條第一項之(G~I) -->
            <i-form-group-check :label="$t('label.agreeLow2') + '：'" 
            >
              <b-form-select v-model="$v.agreeLaw2.$model"  
              :options="formOptions.agreeLaw2">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
              <!-- 只匯出最新執照 -->
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
  name: 'Eng0204Export',
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
      isMasterEngr: [],  // 
      areaMasterOrg: [],  //
      applyItem: [],  //
      contactPersonId: [],  
      status: [],  // 
      receiveType: [],  // 
      payType: [],  // 
      agreeLaw1: [],  //
      agreeLaw2: [],  // 
      columnList: [],
      onlyNew: [],
    });

    const formDefault = {
      saleAreaList: '',
      isMasterEngr: '',
      areaMasterOrg: '',
      applyItem: '',
      contactPersonId: '',
      status: '',
      receiveType: '',
      payType: '',
      agreeLaw1: '',
      agreeLaw2: '', 
      zip: '',
      city: undefined,
      town: undefined,
      other: '',
      compZip: '',
      compCity: undefined,
      compTown: undefined,
      compOther: '',
      serialNumber: '',
      name: '',
      idno: '',
      cstLicenseDocNo: '',
      receiveNo: '',
      compName: '',
      eclCompIdno: '',
      virtualAccount: '',
      onlyNew: [],
      applyDateStart: null,
      applyDateEnd: null,
      publicDateStart: null,
      publicDateEnd: null,
    }

    const form = reactive(Object.assign({}, formDefault));

    const rules = {
      saleAreaList: {},
      isMasterEngr: {},
      areaMasterOrg: {},
      applyItem: {},
      contactPersonId: {},
      status: {},
      receiveType: {},
      payType: {},
      agreeLaw1: {},
      agreeLaw2: {}, 
      zip: {},
      city: {},
      town: {},
      other: {},
      compZip: {},
      compCity: {},
      compTown: {},
      compOther: {},
      serialNumber: {},
      name: {},
      idno: {},
      cstLicenseDocNo: {},
      receiveNo: {},
      compName: {},
      eclCompIdno: {},
      virtualAccount: {},
      onlyNew: {},
      applyDateStart: {},
      applyDateEnd: {},
      publicDateStart: {},
      publicDateEnd: {},
    }

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    const notificationService: NotificationService = useNotification();

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
      formOptions.isMasterEngr = [
        { value: '0', text: '是' },
        { value: '1', text: '否' }
      ];
      formOptions.areaMasterOrg = [
        { value: '0', text: '經濟部中部辦公室' },
        { value: '1', text: '台北市商業處' },
        { value: '2', text: '高雄市政府商業局(商業登記)' },
        { value: '3', text: '經濟部商業司' },
        { value: '4', text: '台北縣政府經濟發展局' },
        { value: '5', text: '高雄市政府經濟發展局' },
        { value: '6', text: '新北市政府經濟發展局' },
        { value: '7', text: '台中市政府經濟發展局' },
        { value: '8', text: '台南市政府經濟發展局' },
        { value: '9', text: '桃園市政府經濟發展局' },
        { value: '10', text: '高雄市建設局(商業設計)、工務局' },
        { value: '11', text: '經濟部加工出口區管理處' },
      ];
      formOptions.contactPersonId = [
        { value: '0', text: '劉芳男' },
        { value: '0', text: '蔣瑋玲' },
        { value: '0', text: '陳家乾' },
      ];
      formOptions.onlyNew = [
        { value: '0', text: "只匯出最新執照"}
      ]
      formOptions.columnList = [
        { value: false, column: 'cstLicenseDocNo', text: '登記證字號' },
        { value: false, column: 'masterName', text: '負責人姓名' },
        { value: false,  column: 'masterIdno', text: '負責人身分證字號' },
        { value: false,  column: 'compIdno', text: '統一編號' },
        { value: false,  column: 'compName', text: '機構名稱' },
        { value: false,  column: 'areaMasterOrg', text: '區域主管機關' },
        { value: false,  column: 'applyItem', text: '申請項目' },
        { value: false,  column: 'applyType', text: '申請方式' },
        { value: false,  column: 'masterPermanentAddr', text: '負責人戶籍地址' },
        { value: false,  column: 'compAddr', text: '機構所在地址' },
        { value: false,  column: 'compTel1', text: '機構電話' },
        { value: false,  column: 'receiveType', text: '自取聯絡方式' },
        { value: false,  column: 'capital', text: '公司資本額' },
        { value: false,  column: 'masterEmail', text: '電子郵件' },
        { value: false,  column: 'compFax', text: '傳真號碼' },
        { value: false,  column: 'receiveNo', text: '收文號' },
        { value: false,  column: 'isMasterEngr', text: '負責人是否為技師' },
        { value: false,  column: 'status', text: '處理狀態' },
        { value: false,  column: 'contactPersonId', text: '承辦人' },
        { value: false,  column: 'receiveAddr', text: '回函地址' },
        { value: false,  column: 'payType', text: '付款方式' },
        { value: false,  column: 'code', text: '虛擬帳號' },
        { value: false,  column: 'applyDate', text: '申請日期' },
        { value: false,  column: 'publicDate', text: '登記證發證日期' },
        { value: false,  column: 'changeItems', text: '變更事項' },
        { value: false,  column: 'agreeLaw1', text: '符合條例第五條' },
        { value: false,  column: 'agreeLaw2', text: '符合條例第六條' },
        { value: false,  column: 'saleAreaList', text: '營業科別' },
      ]
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      console.log(formOptions);
      
    }

    return {
      $v,
      formOptions,
      validateState,
      clear,
      changeCheck,
      isAllCheck,
      submitForm,
    }
  }
}
</script>
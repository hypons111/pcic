<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
								許可/登記證作業-基本資料
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 編號 -->
            <i-form-group-check
							:label="$t('label.engEngrCertificateNo') + '：'"
							>
								<div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.engCstLicenseNo }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" 
                v-model="data.engCstLicenseNo">
                </b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
							</i-form-group-check>
              <!-- 狀態 -->
              <i-form-group-check
							:label="$t('label.status') + '：'"
							>
								<div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.status }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
                 v-model="data.status">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
							</i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 申請日期 -->
            <i-form-group-check labelStar
            :label="$t('label.createDate') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.applyDate }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" 
								v-model="data.applyDate">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
            <!-- 承辦人員 -->
            <i-form-group-check
            :label="$t('label.contractorId') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.contractorId }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
								v-model="data.contractorId"	>
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 申請項目 -->
            <i-form-group-check labelStar
            :label="$t('label.applyItems') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.applyItems }}
              </div>
              <b-form-select v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="data.applyItems"
                :options="formOptions.applyItemsList">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
                <b-form-select v-if="formStatusRef === FormStatusEnum.CREATE"
                :options="formOptions.applyItemsList">
                  <template #first>
                    <option :value=undefined>請選擇</option>
                  </template>
                </b-form-select>
								</b-form-input>
            </i-form-group-check>
            <!-- 發證日期 -->
            <i-form-group-check
            :label="$t('label.publicDate') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.publicDate }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.publicDate">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 負責人姓名 -->
            <i-form-group-check labelStar
            :label="$t('label.masterName') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.masterName }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.masterName">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
            <!-- 身分證字號 -->
            <i-form-group-check labelStar
            :label="$t('label.memberIdno') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.masterIdno }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.masterIdno">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 身分別 -->
            <i-form-group-check labelStar
            :label="$t('label.identityType') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.identityType }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.identityType">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
            <!-- 是否為技師 -->
            <i-form-group-check
            :label="$t('label.isEngr') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.isEngr }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.isEngr">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 性別 -->
            <i-form-group-check
            :label="$t('label.sex') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.sex }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.sex">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
            <!-- 出生日期 -->
            <i-form-group-check
            :label="$t('label.birthday') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.birthday }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.birthday">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 通訊地址 -->
            <i-form-group-check labelStar
            :label="$t('label.contactAddr') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.contactAddr }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.contactAddr">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 手機 -->
            <i-form-group-check
            :label="$t('label.cellphone') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.cellphone }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.cellphone">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
            <!-- Email -->
            <i-form-group-check labelStar
            :label="$t('label.email') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.email }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.email">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 統一編號 -->
            <i-form-group-check labelStar
            :label="$t('label.compIdno') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.compIdno }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.compIdno">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
            <!-- 機構名稱 -->
            <i-form-group-check labelStar
            :label="$t('label.compName') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.chName }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.chName">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 機構電話(一) -->
            <i-form-group-check labelStar
            :label="$t('label.tel1') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.tel1 }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.tel1">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
            <!-- 機構電話(二) -->
            <i-form-group-check
            :label="$t('label.tel2') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.tel2 }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.tel2">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 機構傳真 -->
            <i-form-group-check
            :label="$t('label.agencyFax') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.fax }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.fax">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 機構地址 -->
            <i-form-group-check labelStar
            :label="$t('label.compAddr') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.compAddr }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.compAddr">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 營業範圍 -->
            <i-form-group-check labelStar
            :label="$t('label.saleAreaList') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                <!-- {{ formOptionsFormatter(formOptions.subjectList, data.subjectList) }} -->
                {{ '土木工程、結構工程'}}
              </div>
              <b-form-checkbox-group
								v-if="formStatusRef === FormStatusEnum.EDIT"
                v-model="data.subjectList"
                :options="formOptions.subjectList"
              ></b-form-checkbox-group>
              <b-form-checkbox-group
								v-if="formStatusRef === FormStatusEnum.CREATE"
                
                :options="formOptions.subjectList"
              ></b-form-checkbox-group>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 資本額 -->
            <i-form-group-check labelStar
            :label="$t('label.capital') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.capital }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.capital">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 區域主管機關 -->
            <i-form-group-check labelStar
            :label="$t('label.areaMasterOrg') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.areaMasterOrg }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.areaMasterOrg">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 符合條例第五條第一項之 -->
            <i-form-group-check labelStar
            :label="$t('label.agreeLow1') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.agreeLow1 }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.agreeLow1">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 符合條例第六條第一項之 -->
            <i-form-group-check labelStar
            :label="$t('label.agreeLow2') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.agreeLow2 }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.agreeLow2">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 收文文號 -->
            <i-form-group-check labelStar
            :label="$t('label.receiveNo') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.receiveNo }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.receiveNo">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
            <!-- 執照字號 -->
            <i-form-group-check
            :label="$t('label.engrLicenseDocNo') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.engrLicenseDocNo }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.engrLicenseDocNo">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 取件方式 -->
            <i-form-group-check
            :label="$t('label.receiveType') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.receiveType }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.receiveType">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 寄件地址 -->
            <i-form-group-check labelStar
            :label="$t('label.receiveAddr') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.receiveAddr }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.receiveAddr">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 虛擬帳號 -->
            <i-form-group-check
            :label="$t('label.virtualAccount') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.virtualAccount }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.virtualAccount">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 付款狀態 -->
            <i-form-group-check
            :label="$t('label.isClose') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
									{{ data.isClose }}
								</div>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
									v-model="data.isClose">
								</b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row v-if="formStatusRef !== FormStatusEnum.CREATE">
            <!-- 檢視董事長或執行業務股東名冊 -->
            <i-form-group-check
            :label="'董事長或執行業務股東名冊' + '：'"
            >
              <div>
                <i-button type="binoculars" @click="toEditView('Eng0201EditInfo2')" />
              </div>
              
            </i-form-group-check>
          </b-form-row>
          <b-form-row v-if="formStatusRef !== FormStatusEnum.CREATE">
            <!-- 檢視負責經理人名冊 -->
            <i-form-group-check 
            :label="'負責經理人名冊' + '：'"
            >
              <div>
                <i-button type="binoculars" @click="toEditView('Eng0201EditInfo3')" />
              </div>
              
            </i-form-group-check>
          </b-form-row>
          <b-form-row v-if="formStatusRef !== FormStatusEnum.CREATE">
            <!-- 檢視技師名冊 -->
            <i-form-group-check
            :label="'技師名冊' + '：'"
            >
              <div>
                <i-button type="binoculars" @click="toEditView('Eng0201EditInfo4')" />
              </div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row v-if="formStatusRef !== FormStatusEnum.CREATE">
            <!-- 公司登記查詢 -->
            <i-form-group-check
            :label="'公司登記查詢' + '：'"
            >
              <div>
                <i-button type="binoculars" @click="toEditView('Eng0201EditInfo5')" />
              </div>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <b-col offset="5">
              <!-- 返回 -->
              <i-button type="arrow-left" @click="toQueryView" />
              <!-- 確認送出 -->
              <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" 
              type="send-check" @click="submitForm"/>
            </b-col>
          </b-form-row>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { toRefs, ref, unref, defineComponent, inject, onMounted, reactive} from '@vue/composition-api';
import { Eng0703, IEng0703 } from "@/shared/model/eng/eng0703.model"
import { FormStatusEnum } from '@/shared/enum';
import { RoleEnum } from "@/shared/enum";
import { string } from 'prop-types';
import { handleBack } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { formOptionsFormatter } from '@/shared/formatter/common';
import axios, { AxiosResponse } from 'axios';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';

interface propsType {
	eng0703: IEng0703;
	formStatus: FormStatusEnum;
	role: RoleEnum;
	criteria: any;
  id: string
}
export default defineComponent<propsType>({
  name: 'Eng0201EditInfo',
	props: {
    id: {
      required: false,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    isReload: {
      
    }
	},          
	setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    const formStatusRef = ref(props.formStatus);
    const isReload = ref(false); 
    const $bvModal = useBvModal();
    const rules = {
      applyItems: {},
    }
    const formDefault = {
      applyItems: '',
    }
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const formOptions = reactive({
      subjectList: [],
      applyItemsList: [],
    });

    function toQueryView() {
      toEditView('Eng0201Query');
    }

    function toEditView(name: string) {
      // 跳頁會重洗元件
      navigateByNameAndParams(name, {
        isReload: isReload.value, isNotKeepAlive: true,
        formStatus: formStatusRef.value
      });
      isReload.value = false;
    }

    function submitForm() {
      $bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
        if(isOK) {
          $bvModal.msgBoxConfirm('儲存成功!').then((isOK: boolean) => {})
        }
      });
      $v.value.$reset();
    }

    onMounted(() => {
      // axios
      //   .get('/service/adm-sys-codes')
      //   .then(({ data }) => {
      //     formOptions.subjectList = [];
      //     formOptions.subjectList.push(
      //       ...data.filter(
      //         item => item['moduleType'] === 'ENG' && item['dataType'] === 'SUBJECT')
      //         .map(item => {
      //           return { value: item['dataKey'], text: item['value']};
      //         })
      //     );
      //   })
      getAllSysCodes();
    })

    function getAllSysCodes(): void {
        admSysCodeService.setFormOptions(formOptions, 'ENG');
      }

    const data = {
      id: '01',
      engCstLicenseNo: 'b123456789',
      status: '結案',
      applyItems: '2',
      applyDate: '2020-10-20',
      contractorId: 'F200012345',  // 承辦人員
      applyType: '1',
      publicDate: '2020-10-21',
      masterName: '蔡貫洪',
      masterIdno: 'A123456789',
      identityType: '',  // 身分別
      isEngr: '是',
      sex: '男',
      birthday: '1976-10-25',
      contactAddr: '台北市大安區辛亥路三段100號10樓',
      cellphone: '0922123456', // 手機 
      email: '123@123.123',
      compIdno: 'c1234567',  // 統一編號
      chName: '一宅通工程顧問公司',
      tel1: '02-23456789',
      tel2: '',
      fax: '',
      compAddr: '台北市大安區新生南路三段100號10樓',
      subjectList: ['01', '05'],
      capital: '1000000',  // 資本額
      areaMasterOrg: '臺北市商業處',
      agreeLow1: 'A.本文',
      agreeLow2: 'H.後段',
      receiveNo: 'e1234567',  // 收文文號
      engrLicenseDocNo: 'd1234567',  // 執照字號
      receiveType: '自取',
      receiveAddr: '台北市中山區復興南路三段100號10樓',
      virtualAccount: '2022070520220705',  // 虛擬帳號
      isClose: '已付款' 
    }

    return {
      FormStatusEnum,
      formStatusRef,
      toQueryView,
      submitForm,
      $v,
      reset,
      data,
      formOptions,
      formOptionsFormatter,
      toEditView
    }
  }
})
</script>
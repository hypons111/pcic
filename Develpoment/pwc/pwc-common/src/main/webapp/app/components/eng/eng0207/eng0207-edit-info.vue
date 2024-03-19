<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
								詳細資料
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 會員編號(可編輯修改) -->
            <i-form-group-check
            :label="$t('label.engCompMemberNo') + '：'"
            class="col-12"
              label-cols="3"
              content-cols="4">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.engCompMemberNo }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="data.engCompMemberNo">
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 身分證字號 -->
            <i-form-group-check
            :label="$t('label.idno') + '：'"
            class="col-12"
              label-cols="3"
              content-cols="4">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.idno }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="data.idno">
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 姓名 -->
            <i-form-group-check
            :label="$t('label.name') + '：'"
            class="col-12"
              label-cols="3"
              content-cols="4">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.engrName }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="data.engrName">
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 入會(或任職)日期 -->
            <i-form-group-check
            :label="$t('label.inDate') + '：'"
            class="col-12"
              label-cols="3"
              content-cols="4">
              <div>
                {{ data.inDate | dateToString }}
              </div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 離會(或離職)日期 -->
            <i-form-group-check
            :label="$t('label.leaveDate') + '：'"
            class="col-12"
              label-cols="3"
              content-cols="4">
              <div>
                {{ data.leaveDate | dateToString }}
              </div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 報備離會(或報備離職)日期 -->
            <i-form-group-check
            :label="$t('label.leaveExpdate') + '：'"
            class="col-12"
              label-cols="3"
              content-cols="4">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.leaveExpdate | dateToString }}
              </div>
              <i-date-picker
                v-else
                v-model="data.leaveExpdate"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="3" content-cols="6" :label="$t('label.remark') + '：'">
              <b-form-textarea
                :plaintext="formStatusRef === FormStatusEnum.READONLY"
                v-model="data.remark"
                rows="4"
                maxlength="2000"
                trim
                lazy
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
          <br/>
          <b-table sticky-header
          :items="table1.data" 
          :fields="table1.fields" 
          bordered 
          responsive="sm">
          </b-table>
          <br/>
          <b-table sticky-header
          :items="table2.data" 
          :fields="table2.fields" 
          bordered 
          responsive="sm">
          </b-table>
        </div>
        <b-form-row>
          <b-col offset="5">
            <!-- 返回 -->
            <i-button type="arrow-left" @click="toLast"/>
            <!-- 確認送出 -->
            <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" 
            type="send-check" @click="submitForm(true)" />
          </b-col>
        </b-form-row>
        <br/>
        <b-form-row v-if="formStatusRef !== FormStatusEnum.CREATE">
          <b-col offset="5">  
            <!-- 上一筆 -->
            <i-button type="arrow-bar-left" />
            <!-- 下一筆 -->
            <i-button type="arrow-bar-right"  />
          </b-col>
        </b-form-row>
        <br/>
      </div>
    </section>
    
  </div>
</template>

<script lang="ts">
import { watch, toRef, toRefs, ref, unref, defineComponent, inject, onMounted, reactive} from '@vue/composition-api';
import { Eng0703, IEng0703 } from "@/shared/model/eng/eng0703.model"
import { FormStatusEnum, RoleEnum } from '@/shared/enum';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { string } from 'prop-types';
import { handleBack } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { formOptionsFormatter } from '@/shared/formatter/common';
import axios, { AxiosResponse } from 'axios';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';
import { EngManage } from '@/shared/model/eng/eng-manage.model';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import i18n from '@/shared/i18n';

export default {
  name:'Eng0207EditInfo',
  props: {
    engManage: {
      require: false,
      type: Object
    },
    formStatus: {
      required: false,
      defalte: () => FormStatusEnum.READONLY
    }
  },
  setup(props) {
    const formStatusRef = ref(props.formStatus);
    const isReload = ref(false); // 是否需要重新查詢
    const $bvModal = useBvModal();
    let formDefault = new EngManage();
    const form = reactive(Object.assign({}, formDefault));
    const rules = {}
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      table1.data = tableData;
      table2.data = tableData;
    })

    function toLast() {
      navigateByNameAndParams('Eng0207Query', {
        isReload: isReload.value, isNotKeepAlive: true,
        formStatus: formStatusRef
      } );
      isReload.value = false;
    }

    function submitForm(isCheckReq: boolean) {
      $v.value.$reset();
      this.$nextTick(() => {
        $bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
                if (isOK) {
                  $bvModal.msgBoxOk('儲存成功!');
                }
      })})
    }

    const table1 = reactive({
      fields: [
        {  // 科別
          key: 'subject',
          label: i18n.t('label.subject'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
        },
        {  // 證書字號
          key: 'engrCertificateDocNo',
          label: i18n.t('label.engrCertificateDocNo'),
          sortable: false,
          // thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        }
      ],
      data: undefined,
    });

    const table2 = reactive({
      fields: [
        {  // 執照字號
          key: 'engrLicenseDocNo',
          label: i18n.t('label.engrLicenseDocNo'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
        },
        {  // 公司名稱
          key: 'chName',
          label: i18n.t('label.comName'),
          sortable: false,
          // thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
        },
        {  // 機構統編
          key: 'compIdno',
          label: i18n.t('label.engCompIdno'),
          sortable: false,
          // thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        }
      ],
      data: undefined,
    });

    const data = {
      engCompMemberNo: '9970',
      idno: 'F128564898',
      engrName: '李奕霆',
      inDate: new Date(2022, 4, 20),
      leaveDate: null,
      leaveExpdate: null,
      remark: '',
      
    }

    const tableData = [
      {
        subject: '土木工程',
        engrCertificateDocNo: '技證字第016089號',
        engrLicenseDocNo: '技執字第009658號',
        chName: '台灣世曦工程顧問股份有限公司',
        compIdno: '28412550',
      }
    ]

    return {
      formStatusRef,
      FormStatusEnum,
      data,
      table1,
      table2,
      toLast,
      submitForm,
      $v,
    }
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
}
</script>
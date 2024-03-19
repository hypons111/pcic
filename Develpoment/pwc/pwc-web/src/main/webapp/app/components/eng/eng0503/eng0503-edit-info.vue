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
          <!-- 公會(名稱) -->
          <b-form-row>
            <i-form-group-check 
            class="col-10"
            label-cols="4"
            content-cols="6"
            :label="$t('label.guildName') + '：'">
              <b-form-input v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.guildName">
              </b-form-input>
              <b-form-input v-else></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 會員編號 -->
          <b-form-row>
            <i-form-group-check labelStar
            class="col-10"
            label-cols="4"
            content-cols="6"
            :label="$t('label.engCompMemberNo') + '：'">
              <b-form-input v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.engCompMemberNo">
              </b-form-input>
              <b-form-input v-else></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 身分證字號 -->
          <b-form-row>
            <i-form-group-check labelStar
            class="col-10"
            label-cols="4"
            content-cols="6"
            :label="$t('label.idno') + '：'">
              <b-form-input v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.idno">
              </b-form-input>
              <b-form-input v-else></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 姓名 -->
          <b-form-row>
            <i-form-group-check labelStar
            class="col-10"
            label-cols="4"
            content-cols="6"
            :label="$t('label.name') + '：'">
              <b-form-input v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.name">
                
              </b-form-input>
              <b-form-input v-else></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 入會日期 -->
          <b-form-row>
            <i-form-group-check labelStar
            class="col-10"
            label-cols="4"
            content-cols="6"
            :label="$t('label.inDate') + '：'">
              <i-date-picker v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.inDate"
              ></i-date-picker>
              <i-date-picker v-else 
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <!-- 離會日期 -->
          <b-form-row>
            <i-form-group-check
            class="col-10"
            label-cols="4"
            content-cols="6"
            :label="$t('label.leaveDate') + '：'">
              <i-date-picker v-if="formStatusRef===FormStatusEnum.EDIT" 
              v-model="data.leaveDate"
              ></i-date-picker>
               <i-date-picker v-else
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <!-- 鑑定技師 -->
          <b-form-row>
            <i-form-group-check
            class="col-10"
            label-cols="4"
            content-cols="6"
            :label="$t('label.isIdentify') + '：'">
              <b-form-checkbox v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.isIdentify">
              </b-form-checkbox>
              <b-form-checkbox v-else>
              </b-form-checkbox>
            </i-form-group-check>
          </b-form-row>
          <!-- 是否依營造業法規定加入 -->
          <b-form-row>
            <i-form-group-check
            class="col-10"
            label-cols="4"
            content-cols="6"
            :label="$t('label.isLaw') + '：'">
              <b-form-checkbox v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.isLaw">
              </b-form-checkbox>
              <b-form-checkbox v-else>
              </b-form-checkbox>
            </i-form-group-check>
          </b-form-row>
          <!-- 是否為災害後動員技師 -->
          <b-form-row>
            <i-form-group-check labelStar
            class="col-10"
            label-cols="4"
            content-cols="6"
            :label="$t('label.isEngr1') + '：'">
              <b-form-checkbox v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.isEngr1">
              </b-form-checkbox>
              <b-form-checkbox v-else>
              </b-form-checkbox>
            </i-form-group-check>
          </b-form-row>
          <!-- 備註 -->
          <b-form-row>
            <i-form-group-check
            class="col-10"
            label-cols="4"
            content-cols="6"
            :label="$t('label.remark') + '：'">
              <b-form-textarea
                rows="4"
                maxlength="160"
                trim
                lazy
                v-if="formStatusRef===FormStatusEnum.EDIT"
              ></b-form-textarea>
              <b-form-textarea
                rows="4"
                maxlength="160"
                trim
                lazy
                v-else
              ></b-form-textarea>
            </i-form-group-check>
          </b-form-row>
          <div v-if="formStatusRef===FormStatusEnum.EDIT">
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
        </div>
        <b-form-row>
        <b-col offset="5">
          <!-- 返回 -->
          <i-button type="arrow-left" @click="toQueryView" />
          <!-- 確認送出 -->
          <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" 
          type="send-check" @click="submitForm"/>
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
import axios, { AxiosResponse } from 'axios';
import { RoleEnum } from "@/shared/enum";
import { EngManage } from "@/shared/model/eng/eng-manage.model";
import EngManageService from '@/components/eng/eng0703/eng0703-manage.service';
import { Eng0703, IEng0703 } from "@/shared/model/eng/eng0703.model"
import { toRefs, ref, unref, defineComponent, inject, onMounted, reactive} from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { useBvModal } from '@/shared/modal';
import { email, address, maxLength, required, lineTel, tel, mobileTel} from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useStore } from "@u3u/vue-hooks";
import AccountService from "@/account/account.service";
import { FormStatusEnum } from '@/shared/enum';
import { handleBack } from '@/router/router';
import { formOptionsFormatter } from '@/shared/formatter/common';
import i18n from '@/shared/i18n';
import { on } from 'events';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name:'Eng0503EditInfo',
  props: {
    id: {
      require: false,
      type: Number,
    },
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
    const isReload = ref(false);
    const $bvModal = useBvModal();
    const idRef = ref(props.id);
    let data = ref({arr1:[], arr2: []});

    onMounted(() => {
      switch (idRef.value) {
        case 1: 
          data.value = data1;
          break;
        case 2:
          data.value = data2;
          break;
        case 3:
          data.value = data3;
          break;
      }
      table1.data = data.value.arr1;
      table2.data = data.value.arr2;
    })

    function toQueryView() {
      navigateByNameAndParams('Eng0503Query', {
        isReload: isReload.value, isNotKeepAlive: true,
        formStatus: formStatusRef
      });
      isReload.value = false;
    }

    function submitForm(isCheckReq: boolean) {
      // $v.value.$reset();
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
          tdClass: 'text-center align-middle ',
        },
        {  // 證書字號
          key: 'agentName',
          label: i18n.t('label.agentName'),
          sortable: false,
          // thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
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
          tdClass: 'text-center align-middle ',
        },
        {  // 執業機構
          key: 'engCompChName',
          label: i18n.t('label.engCompChName'),
          sortable: false,
          // thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle ',
        },
        {  // 機構統編
          key: 'engCompIdno',
          label: i18n.t('label.engCompIdno'),
          sortable: false,
          // thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle ',
        },
      ],
      data: undefined,
    });

    const data1 = {
      guildName: '高雄市環境工程技師公會',
      engCompMemberNo: '410',
      idno: 'R120193458',
      name: '鄭煜騰',
      inDate: new Date(2021, 10, 14),
      leaveDate: null,
      isIdentify: false,
      isLaw: true,
      isEngr1: false,
      remark: '',

      arr1: [
        {
          subject: '環境工程',
          agentName: '技證字第017698號',
        }
      ],
      arr2: [
        {
          engrLicenseDocNo: '技執字第009421號',
          engCompChName: '環佑實業有限公司',
          engCompIdno: '86619811',
        }
      ]
    }

    const data2 = {
      guildName: '高雄市環境工程技師公會',
      engCompMemberNo: '407',
      idno: 'N125573971',
      name: '蔡宗延',
      inDate: new Date(2021, 6, 7),
      leaveDate: null,
      isIdentify: true,
      isLaw: false,
      isEngr1: false,
      remark: '',

      arr1: [
        {
          subject: '環境工程',
          agentName: '技證字第017698號',
        }
      ],
      arr2: [
        {
          engrLicenseDocNo: '技執字第009421號',
          engCompChName: '環佑實業有限公司',
          engCompIdno: '86619811',
        }
      ]
    }

    const data3 = {
      guildName: '臺灣省土木技師公會',
      engCompMemberNo: '2433',
      idno: 'N120748834',
      name: '盧垂德',
      inDate: new Date(1993, 10, 22),
      leaveDate: null,
      isIdentify: false,
      isLaw: false,
      isEngr1: true,
      remark: '',

      arr1: [
        {
          subject: '土木工程',
          agentName: '技證字第017698號',
        }
      ],
      arr2: [
        {
          engrLicenseDocNo: '技執字第005591號',
          engCompChName: '中興工程顧問股份有限公司',
          engCompIdno: '84124259',
        }
      ]
    }
    

    return {
      FormStatusEnum,
      formStatusRef,
      data,
      table1,
      table2,
      toQueryView,
      submitForm,
    }
  }
}
</script>
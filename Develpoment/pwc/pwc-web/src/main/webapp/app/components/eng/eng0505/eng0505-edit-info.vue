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
          <!-- 顧問公會 -->
          <b-form-row>
            <i-form-group-check :label="$t('label.guild') + '：'" 
            :item="$v.guild">
              <b-form-select v-model="data.guild" 
                :options="formOptions.guild">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <!-- 顧問公司 -->
          <b-form-row >
            <i-form-group-check :label="$t('label.consultantcompany') + '：'" 
            :item="$v.chName">
            <b-form-input v-if="formStatusRef===FormStatusEnum.EDIT"
            v-model="data.chName">
            </b-form-input>
            <b-form-input v-else
            >
            </b-form-input>
            </i-form-group-check>
            <div class="align-middle">[輸入統一編號亦可帶出公司資料]</div>  
            
          </b-form-row>
          <!-- 統一編號 -->
          <b-form-row>
            <i-form-group-check :label="$t('label.compIdno') + '：'" 
            :item="$v.compIdno">
            <div v-if="formStatusRef===FormStatusEnum.EDIT">
            {{ data.compIdno }}</div>
            <div v-else>
            {{  }}</div>
            </i-form-group-check>
            <!-- 負責人 -->
            <i-form-group-check :label="$t('label.rpName') + '：'" 
            :item="$v.masterName">
            <div v-if="formStatusRef===FormStatusEnum.EDIT">
            {{ data.masterName }}</div>
            <div v-else>
            {{  }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 電話(一) -->
            <i-form-group-check :label="$t('label.tel1') + '：'" 
            :item="$v.tel1">
            <div v-if="formStatusRef===FormStatusEnum.EDIT">
            {{ data.tel1 }}</div>
            <div v-else>
            {{  }}</div>
            </i-form-group-check>
            <!-- 電話(二) -->
            <i-form-group-check :label="$t('label.tel2') + '：'" 
            :item="$v.tel2">
            <div v-if="formStatusRef===FormStatusEnum.EDIT">
            {{ data.tel2 }}</div>
            <div v-else>
            {{  }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- 傳真 -->
          <b-form-row>
            <i-form-group-check :label="$t('label.fax') + '：'" 
            :item="$v.fax">
            <div v-if="formStatusRef===FormStatusEnum.EDIT">
            {{ data.fax }}</div>
            <div v-else>
            {{  }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- 地址 -->
          <b-form-row>
            <i-form-group-check :label="$t('label.addr') + '：'" 
            :item="$v.addr">
            <div v-if="formStatusRef===FormStatusEnum.EDIT">
            {{ data.addr }}</div>
            <div v-else>
            {{  }}</div>
            </i-form-group-check>
          </b-form-row>
          <!-- email -->
          <b-form-row>
            <i-form-group-check :label="$t('label.email') + '：'" 
            :item="$v.email">
            <div v-if="formStatusRef===FormStatusEnum.EDIT">
            {{ data.email }}</div>
            <div v-else>
            {{  }}</div>
            </i-form-group-check>
          </b-form-row>
        </div>
        <br/>
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
  name:'Eng0505EditInfo',
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
    const $bvModal = useBvModal();
    const isReload = ref(false);
    const formOptions = reactive({
      guild: [],
    })
    const formDefault = {
      guild: '',
      chName: '',
      masterName: '',
      compIdno: '',
      tel1: '',
      tel2: '',
      fax: '',
      addr: '',
      email: '',
    }
    const rules = {
      guild: {},
      chName: '',
      masterName: '',
      compIdno: '',
      tel1: '',
      tel2: '',
      fax: '',
      addr: '',
      email: '',
    }
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      getAllSyscodes();
      
    })

    function getAllSyscodes() {
      formOptions.guild = [
        { value: '0', text: '中華民國工程技術顧問商業同業公會'},
        { value: '1', text: '台北市工程技術顧問商業同業公會'},
      ]
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

    function toQueryView() {
      navigateByNameAndParams('Eng0505Query', {
        isReload: isReload.value, isNotKeepAlive: true,
        formStatus: formStatusRef
      });
      isReload.value = false;
    }

    const data = {
      id: 1,
      guild: '0',
      chName: '亞新工程顧問股份有限公司',
      compIdno: '04723653',
      masterName: '莫若楫',
      addr: '新北市汐止區新台五路1段112號22樓',
      tel1: '02-2696173',
      fax: '',
      email: 'ad@maaconsultants.com',
    };

    return {
      FormStatusEnum,
      formStatusRef,
      submitForm,
      toQueryView,
      $v,
      formOptions,
      data,
    }
  }
}
</script>
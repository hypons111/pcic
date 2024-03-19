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
          <!-- 全聯會 -->
          <b-form-row>
            <i-form-group-check labelStar 
            :label="$t('label.bloc') + '：'"
            :item="$v.bloc">
              <b-form-select v-model="data.bloc" 
              :options="formOptions.bloc">
                <template #first>
                  <option value=undefined>請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <!-- 公會 -->
          <b-form-row>
            <i-form-group-check labelStar
            :label="$t('label.guildName') + '：'">
            <b-form-select
            v-model="data.chName" 
              :options="formOptions.guildName">
                <template #first>
                  <option value=undefined>請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <!-- 理事長 -->
          <b-form-row>
            <i-form-group-check 
            :label="$t('label.chairman') + '：'">
            <b-form-input v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.masterName">
                
              </b-form-input>
              <b-form-input v-else></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 秘書長 -->
          <b-form-row>
            <i-form-group-check 
            :label="$t('label.secretaryGeneral') + '：'">
            <b-form-input v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.personName">
                
              </b-form-input>
              <b-form-input v-else></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 電話(一) -->
          <b-form-row>
            <i-form-group-check 
            :label="$t('label.tel1') + '：'">
            <b-form-input v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.tel1">
                
              </b-form-input>
              <b-form-input v-else></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 電話(二) -->
          <b-form-row>
            <i-form-group-check 
            :label="$t('label.tel2') + '：'">
            <b-form-input v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.tel2">
                
              </b-form-input>
              <b-form-input v-else></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 傳真 -->
          <b-form-row>
            <i-form-group-check 
            :label="$t('label.fax') + '：'">
            <b-form-input v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.fax">
                
              </b-form-input>
              <b-form-input v-else></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 地址 -->
          <b-form-row>
            <i-form-group-check 
            :label="$t('label.addr') + '：'">
            <b-form-input v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.addr">
                
              </b-form-input>
              <b-form-input v-else></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- Email -->
          <b-form-row>
            <i-form-group-check 
            :label="$t('label.email') + '：'">
            <b-form-input v-if="formStatusRef===FormStatusEnum.EDIT"
              v-model="data.email">
                
              </b-form-input>
              <b-form-input v-else></b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>
        <br/>
        <b-form-row>
        <b-col offset="5">
          <!-- 返回 -->
          <i-button type="arrow-left" @click="toQueryView" />
          <!-- 儲存 -->
          <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" 
          type="save" @click="submitForm"/>
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
  name:'Eng0504EditInfo',
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
    const idRef = ref(props.id);
    const queryStatus = ref(false);
    const $bvModal = useBvModal();
    let data = ref({});
    const isReload = ref(false);
    const formOptions = reactive({
      bloc: [],
      guildName: [],
    })
    const formDefault = {
      bloc: '',
      chName: '',
      masterName: '',
      personName: '',
      tel1: '',
      tel2: '',
      fax: '',
      addr: '',
      email: '',
    }
    const rules = {
      bloc: {},
      chName: '',
      masterName: '',
      personName: '',
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
      if(idRef.value === 1) {
        data.value = data1;
      } else if(idRef.value === 2) {
        data.value = data2;
      }
    })

    function getAllSyscodes() {
      formOptions.bloc = [
        { value: '0', text: '中華民國土木技師公會全國聯合會'},
        { value: '1', text: '中華民國冷凍空調技師公會全國聯合會'},
      ]
      formOptions.guildName = [
        { value: '0', text: '台北市冷凍空調技師公會'},
        { value: '1', text: '高雄市冷凍空調技師公會'},
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
      navigateByNameAndParams('Eng0504Query', {
        isReload: isReload.value, isNotKeepAlive: true,
        formStatus: formStatusRef
      });
      isReload.value = false;
    }

    const data1 = {
      id: 1,
      bloc: '0',
      chName: '0',
      masterName: '許沛峰',
      personName: '詹佳慧',
      addr: '台北市中山區南京東路2段150號5樓',
      tel1: '02-2377073',
      fax: '02-25170789'
    }
      
    const data2 = {
      id: 2,
      bloc: '1',
      chName: '1',
      masterName: '吳承澐',
      personName: '',
      addr: '高雄市鳳山區福安二街105巷12弄1號',
      tel1: '07-8121559',
      fax: '07-8150218'
    }

    return {
      FormStatusEnum,
      formStatusRef,
      formOptions,
      $v,
      data,
      submitForm,
      toQueryView,
    }
  }
}
</script>
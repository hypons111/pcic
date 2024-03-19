<template>
<div>
  <section class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          董事長或執行業務股東資料
        </h5>
      </div>
      <div class="card-body">
        <!--身分證字號 -->
        <b-form-row>
          <i-form-group-check labelStar
							:label="$t('label.idno') + '：'"
							>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" 
                v-model="data.idno">
                </b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
							</i-form-group-check>
        </b-form-row>
        <!-- 姓名 -->
        <b-form-row>
          <i-form-group-check labelStar
							:label="$t('label.name') + '：'"
							>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" 
                v-model="data.name">
                </b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
							</i-form-group-check>
        </b-form-row>
        <!-- 職稱 -->
        <b-form-row>
          <i-form-group-check
							:label="$t('label.engCompMemberType') + '：'"
							>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" 
                v-model="data.engCompMemberType">
                </b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
							</i-form-group-check>
        </b-form-row>
        <!-- 電話 -->
        <b-form-row>
          <i-form-group-check
							:label="$t('label.tel') + '：'"
							>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" 
                v-model="data.tel">
                </b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
							</i-form-group-check>
        </b-form-row>
        <!-- 是否為技師 -->
        <b-form-row>
          <i-form-group-check
							:label="$t('label.isEngr') + '：'">
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" 
							v-model="data.isEngr">
                </b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
							</i-form-group-check>
        </b-form-row>
        <!-- 任職日期 -->
        <b-form-row>
          <i-form-group-check labelStar
							:label="$t('label.inDate') + '：'"
							>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" 
                v-model="data.inDateStart">
                </b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                 v-model="data.inDateEnd">
								</b-form-input>
							</i-form-group-check>
        </b-form-row>
        <!-- 離職日期 -->
        <b-form-row>
          <i-form-group-check
							:label="$t('label.leaveDate') + '：'"
							>
								<b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" 
                >
                </b-form-input>
                <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" 
                >
								</b-form-input>
							</i-form-group-check>
        </b-form-row>
        <b-form-row>
            <b-col offset="5">
              <!-- 返回 -->
              <i-button type="arrow-left" @click="toLast" />
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

interface propsType {
	eng0703: IEng0703;
	formStatus: FormStatusEnum;
  lastFormStatus: FormStatusEnum;
	role: RoleEnum;
	criteria: any;
  id: string
}
export default defineComponent<propsType>({
  name: 'Eng0201EditInfo2_1',
	props: {
    id: {
      required: false,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    lastFormStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    isReload: {
      
    }
	},          
	setup(props) {
    const formStatusRef = ref(props.formStatus);
    const lastFormStatusRef = ref(props.lastFormStatus);
    const isReload = ref(false);
    const $bvModal = useBvModal();
    const rules = {

    }
    const formDefault = {

    }
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const formOptions = reactive({
      subjectList: [],
    });

    function toLast() {
      // 跳頁會重洗元件
      navigateByNameAndParams('Eng0201EditInfo2', {
        isReload: isReload.value, isNotKeepAlive: true,
        formStatus: lastFormStatusRef.value
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

    const data = 
      {
        idno: 'A123456789',
        name: '蔡貫洪',
        engCompMemberType: '董事長',
        tel: '0912123123',
        isEngr: '是',
        inDateStart: '109/06/25',
        inDateEnd: ''
      }
    

    return {
      formStatusRef,
      FormStatusEnum,
      data,
      toLast,
      submitForm,
      lastFormStatusRef
    }
  }
})
</script>
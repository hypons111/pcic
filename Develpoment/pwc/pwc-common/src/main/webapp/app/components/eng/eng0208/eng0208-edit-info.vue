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
            <i-form-group-check labelStar
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
            <i-form-group-check labelStar
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
            <i-form-group-check labelStar
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
            <!-- 職稱 -->
            <i-form-group-check
            :label="$t('label.memberTitle') + '：'"
            class="col-12"
              label-cols="3"
              content-cols="4">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.memberTitle }}
              </div>
              <b-form-input v-else
              v-model="data.memberTitle">
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 聯絡電話(一) -->
            <i-form-group-check
            :label="$t('label.tel1') + '：'"
            class="col-12"
              label-cols="3"
              content-cols="4">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.tel1 }}
              </div>
              <b-form-input v-else
              v-model="data.tel1">
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 連絡電話(二) -->
            <i-form-group-check
            :label="$t('label.tel2') + '：'"
            class="col-12"
              label-cols="3"
              content-cols="4">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.tel2 }}
              </div>
              <b-form-input v-else
              v-model="data.tel2">
              </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 入會(或任職)日期 -->
            <i-form-group-check labelStar
            :label="$t('label.inDate') + '：'"
            class="col-12"
              label-cols="3"
              content-cols="4">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.inDate | dateToString }}
              </div>
              <i-date-picker
                v-else
                v-model="data.inDate"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 離會(或離職)日期 -->
            <i-form-group-check
            :label="$t('label.leaveDate') + '：'"
            class="col-12"
              label-cols="3"
              content-cols="4">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.leaveDate | dateToString }}
              </div>
              <i-date-picker
                v-else
                v-model="data.leaveDate"
              ></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 狀態 -->
              <i-form-group-check labelStar :label="$t('label.status') + '：'" 
              :item="$v.status"
              class="col-12"
              label-cols="3"
              content-cols="4">
                <div v-if="formStatusRef === FormStatusEnum.READONLY">
                  {{ formOptionsFormatter(formOptions.status, data.status) }}
                </div>
                <b-form-select v-else v-model="data.status"
                :options="formOptions.status">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
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
        </div>
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
  name:'Eng0208EditInfo',
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
    const admSysCodeService = new AdmSysCodeService();
    const formStatusRef = ref(props.formStatus);
    const isReload = ref(false); // 是否需要重新查詢
    const $bvModal = useBvModal();
    let formDefault = new EngManage();
    const form = reactive(Object.assign({}, formDefault));
    const rules = { status: {}}
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const formOptions = reactive({
      status: [],  // 任職狀態
    });

    onMounted(() => {
      getAllSysCodes();
    });

    function toLast() {
      navigateByNameAndParams('Eng0208Query', {
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

    function getAllSysCodes(): void {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.status = [
        { value: '0', text: "離職" },
        { value: '1', text: "任職" }
      ]
    }

    const data = {
      engCompMemberNo: '9970',
      idno: 'F128564898',
      engrName: '李奕霆',
      memberTitle: '經理人兼技師',
      tel1: '(02)27061511',
      tel2: '',
      inDate: new Date(2022, 4, 20),
      leaveDate: null,
      leaveExpdate: null,
      remark: '',
      status: '1',
    }

    return {
      data,
      formStatusRef,
      FormStatusEnum,
      toLast,
      submitForm,
      formOptions,
      $v,
      formOptionsFormatter,
    }
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
}
</script>
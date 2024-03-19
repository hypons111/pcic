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
          <i-form-group-check :label="$t('label.caseNa') + '：'" 
          >
          {{ data.caseName }}
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <!-- 工程種類 -->
        <i-form-group-check :label="$t('label.projectType') + '：'" 
        >
          <div >
          {{ formOptionsFormatter(formOptions.projectType, data.projectType) }}
          </div>
        </i-form-group-check>
        </b-form-row>
        <!-- 簽證範圍 -->
        <b-form-row>
          <i-form-group-check :label="$t('label.recordRangeList') + '：'">
            <div>
              {{ formOptionsFormatter(formOptions.recordRangeList, 
            data.recordRangeList) }}
            </div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <!-- 簽證項目 -->
          <i-form-group-check :label="$t('label.recordItems') + '：'"
          >
          {{ formOptionsFormatter(formOptions.recordItemsList, 
            data.recordItemsList) }}
          </i-form-group-check>
        </b-form-row>
        <!-- 應登錄日期 -->
        <b-form-row>
          <i-form-group-check :label="$t('label.expectDate') + '：'"
          >
          {{ data.expectDate }}
          </i-form-group-check>
        </b-form-row>
        <!-- 簽證內容 -->
        <b-form-row>
          <i-form-group-check :label="$t('label.recordContent') + '：'"
          >
          <b-form-textarea rows="4" maxlength="80" trim lazy 
          v-model="data.recordContent"
          >
          </b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 簽證意見 -->
        <b-form-row>
          <i-form-group-check :label="$t('label.recordComment') + '：'"
          >
          <b-form-textarea rows="4" maxlength="80" trim lazy 
          v-model="data.recordComment"
          >
          </b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 實際登錄日期 -->
        <b-form-row>
          <i-form-group-check :label="$t('label.realTime') + '：'"
          >
          {{ data.realTime }}
          </i-form-group-check>
        </b-form-row>
        <!-- 簽證日期 -->
        <b-form-row>
          <i-form-group-check :label="$t('label.signStartDate') + '：'"
          >
          <b-form-checkbox v-model="data.noSignItem">本期未辦理簽證事項
          </b-form-checkbox>
          <i-date-picker v-model="data.signStartDate"></i-date-picker>
          <div class="text-danger">本欄位請填寫「應登錄日期」前半年內最後一次簽署書圖之時間；
            例如「應登錄日期」為2022/01/01，「簽證日期」的範圍為「2021/07/01~2021/12/31」</div>
          </i-form-group-check>
        </b-form-row>
        <!-- 結案日期 -->
        <b-form-row>
          <i-form-group-check :label="$t('label.closeDate') + '：'"
          >
          <i-date-picker v-model="data.closeDate"></i-date-picker>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <b-col offset="5">
            <!-- 返回 -->
            <i-button type="arrow-left" @click="toLast"/>
            <!-- 確認送出 -->
            <i-button type="send-check"  @click="submitForm(true)"/>

          </b-col>
        </b-form-row>
        <br/>
        <b-form-row>
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
import { string } from 'prop-types';
import { handleBack } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { formOptionsFormatter } from '@/shared/formatter/common';
import axios, { AxiosResponse } from 'axios';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';
import { EngCaseTotalDTO } from '@/shared/model/eng/eng-case.model';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import i18n from '@/shared/i18n';

export default {
  name: 'Eng0302Edit',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    const isReload = ref(false);
    let formDefault = new EngCaseTotalDTO();
    const form = reactive(Object.assign({}, formDefault));
    const $bvModal = useBvModal();

    const rules = {
      
    }
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      getAllSysCodes();
    })

    function toLast() {
      navigateByNameAndParams('Eng0302Query', {
        isReload: isReload.value, isNotKeepAlive: true,
        // formStatus: formStatusRef
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

    function getAllSysCodes() {
      formOptions.recordItemsList = [
        { value: '0', text: '設計' },
        { value: '1', text: '監造' },
        { value: '2', text: '施工' },
        { value: '99', text: '其他' }
      ];
      formOptions.recordRangeList = [
        { value: '0', text: '航空測量' },
        { value: '1', text: '工址調查' },
        { value: '2', text: '定線工程' },
        { value: '3', text: '機電工程' },
        { value: '4', text: '照明工程' },
        { value: '5', text: '道路工程、橋樑工程及隧道工程' },
        { value: '6', text: '水理分析、排水工程' },
        { value: '7', text: '交通工程' },
        { value: '99', text: '其它' },
      ];
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const formOptions = reactive({
      recordItemsList: [], // 簽證項目
      recordRangeList: [], // 簽證範圍
      caseType: [],  // 案件類型
      projectType: [],  // 工程種類
    });

    const data = {
      caseNo: 'work1050912-1',
      caseName: '成功橋拓寬工程',
      projectType: '01',
      recordRangeList: '5',
      recordRangeOther: '',
      engrName: '王茲為',
      no: 1,
      recordItemsList: '1',
      recordItems: [],
      execDateStart: '106/10/26',
      execDateEnd: '108/11/14',
      expectDate: '106/10/26',
      realTime: '106/10/26',
      updateTime: '106/10/10',
      signStartDate: new Date(2017, 9, 10),
      closeDate: new Date(2019, 11, 14),
      recordContent: '',
      recordComment: '',
      noSignItem: false
    }

    return {
      data,
      formOptionsFormatter,
      formOptions,
      toLast,
      submitForm
    }
  }
}
</script>
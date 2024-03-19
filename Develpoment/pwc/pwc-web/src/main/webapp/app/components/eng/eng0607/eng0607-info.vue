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
            <!-- 裁判書序號 -->
            <i-form-group-check :label="$t('label.engLawJudgmentId') + '：'" 
            >
            <div>{{ data.engLawJudgmentId }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 年度 -->
            <i-form-group-check :label="$t('label.iYear') + '：'" 
            >
            <div>{{ data.judgeYear }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 字別 -->
            <i-form-group-check :label="$t('label.judicialDataDocType') + '：'" 
            >
            <div>{{ data.docType }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 號次 -->
            <i-form-group-check :label="$t('label.docNumber') + '：'" 
            >
            <div>{{ data.docNumber }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 裁判日期	 -->
            <i-form-group-check :label="$t('label.judgeDate') + '：'" 
            >
            <div>{{ data.judgeDate }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 裁判案由 -->
            <i-form-group-check :label="$t('label.judgeReson') + '：'" 
            >
            <div>{{ data.judgeReson }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 是否刪除 -->
            <i-form-group-check :label="$t('label.isDelete') + '：'" 
            >
            <div>{{ data.isDelete? '是': '否' }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 技師或公司 -->
            <i-form-group-check :label="$t('label.searchType') + '：'" 
            >
            <div>{{ data.searchType }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 技師名/公司名 -->
            <i-form-group-check :label="$t('label.searchKey') + '：'" 
            >
            <div>{{ data.searchKey }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 狀態 -->
            <i-form-group-check :label="$t('label.status') + '：'" 
            >
            <b-form-select v-model="data.status"
                :options="formOptions.status">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 全文內容 -->
            <i-form-group-check :label="$t('label.judicialDataContent') + '：'" 
            class="col-12" label-cols="2" content-cols="8">
            <div>{{ data.content }}</div>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
          <b-col offset="4">
            <!-- 返回 -->
            <i-button type="arrow-left" @click="toLast"/>
            <!-- 確認送出 -->
            <i-button 
            type="send-check" @click="submitForm(true)" />
            <!-- 司法院檢索 -->
              <b-button variant="info" @click="view">司法院檢索</b-button>
              <!-- 刪除 -->
              <i-button @click="remove"
              type="trash" />
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
  name:'Eng0607Info',
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
    const isReload = ref(false); // 是否需要重新查詢
    const formStatusRef = ref(props.formStatus);
    const $bvModal = useBvModal();
    const formDefault = {

    }
    const form = reactive(Object.assign({}, formDefault));
    const rules = { status: {}}
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const formOptions = reactive({
      status: [],  // 任職狀態
    });

    onMounted(() => {
      getAllSysCodes();
    });

    function view() {
      window.open('https://law.judicial.gov.tw/FJUD/data.aspx?ty=JD&id=' + data.engLawJudgmentId);
    }

    function toLast() {
      navigateByNameAndParams('Eng0607Query', {
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
    function remove(isCheckReq: boolean) {
      $v.value.$reset();
      this.$nextTick(() => {
        $bvModal.msgBoxConfirm('是否確認刪除？').then((isOK: boolean) => {
          if (isOK) {
            $bvModal.msgBoxOk('刪除成功!');
          }
      })})
    }

    function getAllSysCodes(): void {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.status = [
        { value: '1', text: "待確認" },
        { value: '2', text: "列管" },
        { value: '3', text: "結案" }
      ]
    }

    const data = {
      engLawJudgmentId: 'CPEM,111,竹東交簡,18,20220412,1',
      judgeYear: '111	',
      docType: '竹東交簡',
      docNumber: '18',
      judgeDate: '111/04/12',
      judgeReson: '公共危險',
      isDelete: false,
      searchType: '公司',
      searchKey: '無',
      status: '1',
      content: '臺灣新竹地方法院刑事簡易判決 111年度竹東交簡字第18號 聲 請 人 臺灣新竹地方檢察署檢察官 被 告 曾泳瑞 上列被告因公共危險案件，經檢察官聲請以簡易判決處刑（111 年度速偵字第23號），本院判決如下： 主 文 曾泳瑞吐氣所含酒精濃度達每公升零點二五毫克以上而駕駛動力 交通工具，處有期徒刑參月，如易科罰金，以新臺幣壹仟元折算 壹日。 事實及理由 一、犯罪事實：曾泳瑞於民國111年1月7日晚上8時許，在新竹縣 ○○鄉○○路○段000號二呆工程股份有限公司內飲酒後，有吐氣 所含酒精濃度達每公升0.25毫克以上之情形，仍基於酒後駕 駛動力交通工具之犯意，駕駛車牌號碼00-0000號自用小客 車行駛於道路上。嗣於111年1月7日晚上10時30分許，在新 竹縣芎林鄉富林路一段與五和街口為警攔查，復經警於同日 晚上10時38分許，施以酒精濃度測試檢定，測得其吐氣所含 酒精濃度達每公升0.50毫克，始查悉上情。 二、證據： （一）被告曾泳瑞於警詢及偵訊時之自白。 （二）酒精測定紀錄表、新竹縣政府警察局舉發違反道路交通管 理事件通知單影本各1份附卷可查。 三、按行為後法律有變更者，適用行為時之法律。但行為後之法 律有利於行為人者，適用最有利於行為人之法律，刑法第2 條第1項定有明文。查被告行為後，刑法第185條之3之規定 ，業於111年1月28日修正公布，並於111年1月30日起施行， 經比較新舊法之結果，以修正前刑法第185條之3之規定較有 利於被告。是核被告所為，係犯修正前刑法第185條之3第1 項第1款之吐氣所含酒精濃度達每公升0.25毫克以上而駕駛 動力交通工具之公共危險罪。爰審酌被告無視酒後不得駕車 之禁令，酒精濃度達每公升0.50毫克，仍貿然酒後駕車上路 ，嚴重危及道路交通安全，缺乏尊重其他用路人生命、財產 安全之觀念，兼衡被告之智識程度為國中畢業、家庭經濟狀 況為勉持，犯後坦承犯行，態度尚稱良好，及未造成他人生 命、身體及財產法益之實害等一切情狀，量處如主文所示之 刑，並諭知易科罰金之折算標準，以資懲儆。 四、依刑事訴訟法第449條第1項前段、第454條第1項，逕以簡易 判決處刑如主文。 五、如不服本判決，得自收受送達之日起20日內向本院提出上訴 狀。 本案經檢察官吳志中聲請以簡易判決處刑。 中　　華　　民　　國　　111 　年　　4 　　月　　12　　日 竹東簡易庭 法 官 王子謙 以上正本證明與原本無異。 中　　華　　民　　國　　111 　年　　4 　　月　　13　　日 書記官 田宜芳 附錄本案論罪科刑法條全文： 修正前刑法第185條之3 駕駛動力交通工具而有下列情形之一者，處二年以下有期徒刑， 得併科二十萬元以下罰金： 一、吐氣所含酒精濃度達每公升零點二五毫克或血液中酒精濃度 達百分之零點零五以上。 二、有前款以外之其他情事足認服用酒類或其他相類之物，致不 能安全駕駛。 三、服用毒品、麻醉藥品或其他相類之物，致不能安全駕駛。 因而致人於死者，處三年以上十年以下有期徒刑；致重傷者，處 一年以上七年以下有期徒刑。 曾犯本條或陸海空軍刑法第五十四條之罪，經有罪判決確定或經 緩起訴處分確定，於五年內再犯第一項之罪因而致人於死者，處 無期徒刑或五年以上有期徒刑；致重傷者，處三年以上十年以下 有期徒刑。'
    }

    return {
      data,
      formOptions,
      $v,
      toLast,
      submitForm,
      remove,
      view,
    }
  }
}
</script>
<template>
  <b-container>
    <b-form-row>
      <!-- 機構名稱 -->
      <i-form-group-check :label="$t('label.compName')+'：'" :item="$v.compName">
        <b-form-input plaintext disabled :value="$v.compName.$model"></b-form-input>
      </i-form-group-check>
      <!-- 統一編號 -->
      <i-form-group-check :label="$t('label.compIdno')+'：'" :item="$v.compIdno">
        <b-form-input plaintext disabled :value="$v.compIdno.$model"></b-form-input>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 機構地址 -->
      <i-form-group-check :label="$t('label.compAddr')+'：'" :item="$v.addrOther">
        <b-form-input plaintext disabled :value="$v.addrOther.$model"></b-form-input>
      </i-form-group-check>
      <!-- 負責人姓名 -->
      <i-form-group-check :label="$t('label.masterName')+'：'" :item="$v.masterName">
        <b-form-input plaintext disabled :value="$v.masterName.$model"></b-form-input>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 成立日期 -->
      <i-form-group-check labelStar :label="$t('label.establishDate')+'：'" :item="$v.establishDate" content-cols="4">
        <i-date-picker
          v-model.trim="$v.establishDate.$model"
          :state="validateState($v.establishDate)"
          type="month"
          placeholder="yyy/MM"
        ></i-date-picker>
      </i-form-group-check>
      <!-- 資本額 -->
      <i-form-group-check :label="$t('label.capital')+'：'" :item="$v.capital" content-cols="4">
        <b-form-input v-model.trim="$v.capital.$model" :state="validateState($v.capital)" placeholder="請輸入正整數" v-i-currency></b-form-input>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 登記證字號 -->
      <i-form-group-check :label="$t('label.licenseNo')+'：'" :item="$v.licenseNo">
        <b-form-input plaintext disabled :value="$v.licenseNo.$model"></b-form-input>
      </i-form-group-check>
      <!-- 營業範圍 -->
      <i-form-group-check :label="$t('label.saleAreaList')+'：'" :item="$v.saleAreaList">
        <b-form-input plaintext disabled :value="$v.saleAreaList.$model"></b-form-input>
      </i-form-group-check>
    </b-form-row>

    <b-card sub-title="收入（本欄位為配合主計處服務業統計資料用，不作其他用途）" sub-title-text-variant="secondary" class="mb-4">
      <hr />
      <b-card-text class="mb-3">
      <!-- 總收入 -->
        {{ i18n.t('label.incomeTotal') }} {{ $v.incomeTotal.$model | commaFormatter }} 元
      </b-card-text>
      <b-card-text>
        <!-- 營業收入 -->
        其中 {{ i18n.t('label.incomeOperating') }}
        <div class="d-inline position-relative">
          <b-form-input
            v-model.trim="$v.incomeOperating.$model"
            :state="validateState($v.incomeOperating)"
            @change="calculateIncome()"
            class="col-2 d-inline-block mb-2 input-tip-box"
            size="sm"
            placeholder="請輸入正整數"
            v-i-currency
          ></b-form-input>
          元
          <span v-if="$v.engYearreportAuth.isAuthorize.$model !== '1'">本系統已新增向財政部申請查調貴公司申報所得功能，您可以透過『授權服務書』分頁，勾選同意並授予本會權限向財政部查調貴公司所提報之營利事業所得『營業收入』及『非營業收入』。</span>
        </div>
        <span class="remark-text">（可參考公司營利事業所得稅結算申報書表之「年度損益及稅額計算表」項目「01營業收入總額」）</span>
        <br />
        <!-- 技術服務費 -->
        為本年度{{ i18n.t('label.incomeTechService') }}完成金額總計
        <b-form-input
          v-model.trim="$v.incomeTechService.$model"
          :state="validateState($v.incomeTechService)"
          @change="calculateIncome()"
          class="col-2 d-inline-block mb-2"
          size="sm"
          placeholder="請輸入正整數"
          v-i-currency
        ></b-form-input>
        元（包含國內 {{ $v.incomeTechServiceDomestic.$model | commaFormatter }} 元
        + 國外 {{ $v.incomeTechServiceForeign.$model | commaFormatter }} 元）
        <!-- 非技術服務費 -->
        + {{ i18n.t('label.incomeNonTechService') }} {{ $v.incomeNonTechService.$model | commaFormatter }} 元
        <br />

        <!-- 非營業收入 -->
        {{ i18n.t('label.incomeNonOperating') }}
        <div class="d-inline position-relative">
          <b-form-input
            v-model.trim="$v.incomeNonOperating.$model"
            :state="validateState($v.incomeNonOperating)"
            @change="calculateIncome()"
            class="col-2 d-inline-block mb-2 input-tip-box"
            size="sm"
            placeholder="請輸入正整數"
            v-i-currency
          ></b-form-input>
          元
          <span v-if="$v.engYearreportAuth.isAuthorize.$model !== '1'">本系統已新增向財政部申請查調貴公司申報所得功能，您可以透過『授權服務書』分頁，勾選同意並授予本會權限向財政部查調貴公司所提報之營利事業所得『營業收入』及『非營業收入』。</span>
        </div>
        <span class="remark-text">（可參考公司營利事業所得稅結算申報書表之「年度損益及稅額計算表」項目「34非營業收入總額」）</span>
        <br />
        <span class="remark-text">註：本年度技術服務費完成金額總計修改過後將以填報修改為主。</span>
      </b-card-text>
      <b-card-text>
        <table>
          <tr>
            <th colspan="2">[ 計算方式說明 ]</th>
          </tr>
          <tr>
            <th valign="top">1.</th>
            <td>總收入 = 營業收入 + 非營業收入</td>
          </tr>
          <tr>
            <th valign="top">2.</th>
            <td>
              營業收入 = 本年度技術服務費完成金額總計 （包括 [1]
              規劃與可行性研究、基本設計、細部設計、協辦招標與決標、施工監造、專案管理及其相關技術性服務； [2]
              統包工程中如分別提列設計與監造費，請直接填列；如無分別提列，請依據「機關委託技術服務廠商評選及計費辦法」
              附表二及附表三之比例估算）
              <br />
              + 非技術服務費收入（係指本年度營業收入扣除技術服務費完成金額總計）
            </td>
          </tr>
          <tr>
            <th valign="top">3.</th>
            <td>非營業收入 = 租金收入 + 利息收入 + 其他非營業收入</td>
          </tr>
        </table>
      </b-card-text>
    </b-card>

    <b-card sub-title="營業支出費用（本欄位為配合主計處服務業統計資料用，不作其他用途）" sub-title-text-variant="secondary" class="mb-4">
      <hr />
      <b-card-text>
        <!-- 營業支出 -->
        {{ i18n.t('label.feeOperating') }}費用
        <b-form-input
          v-model.trim="$v.feeOperating.$model"
          :state="validateState($v.feeOperating)"
          class="col-2 d-inline-block mb-2"
          size="sm"
          placeholder="請輸入正整數"
          v-i-currency
        ></b-form-input>
        <!-- 固定資本消耗準備(折舊) -->
        元 ，其中{{ i18n.t('label.feeDepreciation') }}
        <b-form-input
          v-model.trim="$v.feeDepreciation.$model"
          :state="validateState($v.feeDepreciation)"
          class="col-2 d-inline-block mb-2"
          size="sm"
          placeholder="請輸入正整數"
          v-i-currency
        ></b-form-input> 元
        <!-- 間接稅淨額 -->
        + {{ i18n.t('label.feeIndirectTax') }}
        <b-form-input
          v-model.trim="$v.feeIndirectTax.$model"
          :state="validateState($v.feeIndirectTax)"
          class="col-2 d-inline-block mb-2"
          size="sm"
          placeholder="請輸入正整數"
          v-i-currency
        ></b-form-input> 元
        <br />
        <!-- 人事費用 -->
        + {{ i18n.t('label.feeStaff') }}
        <b-form-input
          v-model.trim="$v.feeStaff.$model"
          :state="validateState($v.feeStaff)"
          class="col-2 d-inline-block mb-2"
          size="sm"
          placeholder="請輸入正整數"
          v-i-currency
        ></b-form-input> 元
        <!-- 研究發展經費 -->
        + {{ i18n.t('label.feeRd') }}
        <b-form-input
          v-model.trim="$v.feeRd.$model"
          :state="validateState($v.feeRd)"
          class="col-2 d-inline-block mb-2"
          size="sm"
          placeholder="請輸入正整數"
          v-i-currency
        ></b-form-input> 元
        <!-- 人才培育經費 -->
        + {{ i18n.t('label.feeTrain') }}
        <b-form-input
          v-model.trim="$v.feeTrain.$model"
          :state="validateState($v.feeTrain)"
          class="col-2 d-inline-block mb-2"
          size="sm"
          placeholder="請輸入正整數"
          v-i-currency
        ></b-form-input>
        元
      </b-card-text>
      <b-card-text>
        <table>
          <tr>
            <th colspan="2">[ 計算方式說明 ]</th>
          </tr>
          <tr>
            <th valign="top">1.</th>
            <td>
              營業支出費用 = 營業成本 + 營業費用（包含管、銷及研發等費用） = 文具用品 + 旅費 + 運費 + 郵電費 + 修繕費 + 廣告費 + 水電瓦斯費
              + 產物保險費 + 交際費 + 研究發展經費 + 人才培育經費 + 會計相關費用 + 佣金 + 人事費用 + 固定資本消耗準備（即為折舊） +
              間接稅淨額（間接稅 - 政府對企業之補助） + 其他營業費用
            </td>
          </tr>
          <tr>
            <th valign="top">2.</th>
            <td>人事費用 = 薪資支出 + 福利津貼 + 加班費 + 工地津貼 + 保險金 + 退休金 + 伙食費 + 外聘專家顧問報酬</td>
          </tr>
          <tr>
            <th valign="top">3.</th>
            <td>
              「研究發展經費」及「人才培育經費」依工程技術顧問公司管理條例第23條規定，不得少於年度工程技術服務業務營業收入總額千分之5。
            </td>
          </tr>
          <tr>
            <th valign="top">4.</th>
            <td>
              間接稅是廠商對政府非自願性的給付，廠商將其視為生產成本，包括因生產、銷售、購入或使用之商品及服務而課徵之稅捐。
              因該稅捐為廠商之成本，故廠商必將期轉嫁在所生產之產品(商品或服務)價格上，此等同間接對購買者課稅，
              故稱間接稅（例如：營業稅、印花稅等非屬直接稅等皆屬之）。補助金為公民營企業從政府部門所收到之贈與，或稱補貼，
              代表生產者收入遠超過產出所獲致之銷售收入，此收入不計入生產總額，但可用來抵銷生產成本，一般視為間接稅減項； 即間接稅 - 補助金 =
              間接稅淨額。
            </td>
          </tr>
        </table>
      </b-card-text>
    </b-card>

    <b-form-row>
      <!-- 非營業支出 -->
      <i-form-group-check
        :labelStar="true"
        :label="$t('label.feeNonOperating')+'：'"
        :item="$v.feeNonOperating"
        class="col-4"
        label-cols="6"
        content-cols="6"
      >
        <b-form-input
          v-model.trim="$v.feeNonOperating.$model"
          :state="validateState($v.feeNonOperating)"
          placeholder="請輸入正整數"
          v-i-currency
        ></b-form-input>
      </i-form-group-check>
      <span class="col-8 remark-text pt-2">
        說明：非營業支出 = 利息支出 + 租金支出 + 災害損失支出 + 其他 （本欄位為配合主計處服務業統計資料用，不作其他用途）
      </span>
    </b-form-row>

    <b-form-row>
      <!-- 國外員工人數 -->
      <i-form-group-check
        :labelStar="true"
        :label="$t('label.staffNumForeign')+'：'"
        :item="$v.staffNumForeign"
        content-cols="4"
      >
        <b-form-input
          v-model.trim="$v.staffNumForeign.$model"
          :state="validateState($v.staffNumForeign)"
          placeholder="請輸入正整數"
          v-i-currency
        ></b-form-input>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 貸款或融資案件數 -->
      <i-form-group-check
        :labelStar="true"
        :label="$t('label.countFinancing')+'：'"
        :item="$v.countFinancing"
        content-cols="4"
      >
        <b-form-input
          v-model.trim="$v.countFinancing.$model"
          :state="validateState($v.countFinancing)"
          placeholder="請輸入正整數"
          v-i-currency
        ></b-form-input>
      </i-form-group-check>
      <!-- 貸款或融資費用 -->
      <i-form-group-check :labelStar="true" :label="$t('label.feeFinancing')+'：'" :item="$v.feeFinancing" content-cols="4">
        <b-form-input v-model.trim="$v.feeFinancing.$model" :state="validateState($v.feeFinancing)" placeholder="請輸入正整數" v-i-currency></b-form-input>
      </i-form-group-check>
    </b-form-row>

    <b-form-row>
      <!-- 備註 -->
      <i-form-group-check :label="$t('label.remark')+'：'" :item="$v.remark" class="col-8" label-cols="3" content-cols="9">
        <b-form-textarea v-model.trim="$v.remark.$model" rows="4"></b-form-textarea>
      </i-form-group-check>
      <span class="col-4 remark-text pt-2">（總收入為0元或營業收入為0元時，請於此說明）</span>
    </b-form-row>
  </b-container>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import { watch, reactive } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, integer, numeric } from '@/shared/validators';
import { commaFormatter } from '@/shared/formatter/common';

export default {
  name: 'Eng0202BasicInfo',
  props: {
    engYearreport: {
      type: Object,
      required: false,
    },
  },
  setup(props) {

    let formDefault = {
      engYearreportId: '',
      engYearreportYyyy: '',
      yearreportType: '',
      compId: '',
      compIdno: '',
      licenseNo: '',
      establishDate: null,
      compName: '',
      addrZip: '',
      addrCity: '',
      addrTown: '',
      addrOther: '',
      masterName: '',
      capital: '',
      saleAreaList: '',
      execType: '',
      engrIdno: '',
      engrName: '',
      engrSubjectList: '',
      staffNumForeign: '',
      incomeTotal: 0,
      incomeOperating: '',
      incomeNonOperating: '',
      incomeTechService: '',
      incomeTechServiceDomestic: 0,
      incomeTechServiceForeign: 0,
      isIncomeTechServiceMatch: '',
      incomeNonTechService: 0,
      feeOperating: '',
      feeNonOperating: '',
      feeDepreciation: '',
      feeIndirectTax: '',
      feeStaff: '',
      feeRd: '',
      feeTrain: '',
      countFinancing: '',
      feeFinancing: '',
      remark: '',
      status: '',
      engYearreportAuth: {
        isAuthorize: ''
      }
    };
    const formRules = {
      engYearreportId: {},
      engYearreportYyyy: {},
      yearreportType: {},
      compId: {},
      compIdno: {},
      licenseNo: {},
      establishDate: { required },
      compName: {},
      addrZip: {},
      addrCity: {},
      addrTown: {},
      addrOther: {},
      masterName: {},
      capital: { numeric, integer },
      saleAreaList: {},
      execType: {},
      engrIdno: {},
      engrName: {},
      engrSubjectList: {},
      staffNumForeign: {numeric, integer},
      incomeTotal: {},
      incomeOperating: {numeric, integer},
      incomeNonOperating: {numeric, integer},
      incomeTechService: {numeric, integer},
      incomeTechServiceDomestic: {},
      incomeTechServiceForeign: {},
      isIncomeTechServiceMatch: {},
      incomeNonTechService: {},
      feeOperating: {numeric, integer},
      feeNonOperating: {numeric, integer},
      feeDepreciation: {numeric, integer},
      feeIndirectTax: {numeric, integer},
      feeStaff: {numeric, integer},
      feeRd: {numeric, integer},
      feeTrain: {numeric, integer},
      countFinancing: {numeric, integer},
      feeFinancing: {numeric, integer},
      remark: {},
      status: {},
      engYearreportAuth: {
        isAuthorize: {}
      }
    };
    let formEmpty = Object.assign({}, formDefault);
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(formRules, form, formDefault);

    watch(() => props.engYearreport, (newVal: object) => {
      // console.log("basic-info watch!!!!!!!!!!!");
      Object.assign(formDefault, formEmpty)
      for(let key in formDefault){
        if(newVal[key]) formDefault[key] = newVal[key];
      }
      reset();
    },{immediate: true, deep: true});

    const calculateIncome = () => {
      if(!isNaN(Number(form.incomeOperating)) && !isNaN(Number(form.incomeNonOperating))) {
        form.incomeTotal = Math.round(Number(form.incomeOperating)) + Math.round(Number(form.incomeNonOperating));
      }else {
        form.incomeTotal = 0;
      }
      if(!isNaN(Number(form.incomeOperating)) && !isNaN(Number(form.incomeTechService))) {
        form.incomeNonTechService = Math.round(Number(form.incomeOperating)) - Math.round(Number(form.incomeTechService));
      }else {
        form.incomeNonTechService = 0;
      }
    };

    const putForm = () => {
      return form;
    };

    return {
      i18n,
      $v,
      checkValidity,
      reset,
      validateState,
      calculateIncome,
      putForm,
    };
  },
  filters: {
    commaFormatter: (value: string) => commaFormatter(value),
  },
};
</script>

<style scoped>
.input-tip-box + span {
  display: none;
  position: absolute;
  z-index: 1;
  width: 420px;
  border-radius: 4px;
  padding: 8px;
  color: #fff;
  font-weight: bold;
  text-align: left;
  background-color:#8BC34A;
  left: 0;
  bottom: 30px;
}

.input-tip-box:focus + span {
  display: block;
}
</style>

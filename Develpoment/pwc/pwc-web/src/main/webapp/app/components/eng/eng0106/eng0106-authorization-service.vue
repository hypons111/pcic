<template>
  <b-container>
    <b-row align-h="center">
      <b-col cols="11">
        <h3 class="text-center font-weight-bold">
          申報人同意授權服務書
          <span style="color: red"> {{ $v.engYearreportAuthLast.$model.isAuthorize === 1 ? '(已授權)' : '' }}</span>
        </h3>
        <div class="mb-3">
          <span class="border border-dark p-1">授權年度：{{ new Date().getFullYear() - 1912 }}</span>
        </div>
        <h5 class="font-weight-bold text-decoration-underline">技師事務所相關資訊</h5>
        <table>
          <tr>
            <td>授權人(委託人)：</td>
            <td class="font-weight-bold">{{ $v.engrName.$model }}</td>
          </tr>
          <tr>
            <td>執業機構名稱：</td>
            <td class="font-weight-bold">{{ $v.compName.$model }}</td>
          </tr>
          <tr>
            <td>國民身分證統一編號：</td>
            <td class="font-weight-bold">{{ $v.engrIdno.$model }}</td>
          </tr>
          <tr>
            <td>被授權人(受任方)：</td>
            <td class="font-weight-bold">行政院公共工程委員會</td>
          </tr>
        </table>
        <hr />
        <h5 class="font-weight-bold text-decoration-underline">注意事項及授權內容</h5>
        <p>
          &emsp;&emsp;本同意授權服務書說明行政院公共工程委員會（以下簡稱工程會）將如何處理所蒐集到的個人財稅資料，當您勾選「同意授權」時，表示您已閱讀瞭解並同意接受本同意書之所有內容及其後修改變更規定，若您非技師本人或技師事務所負責人，應請技師本人或技師事務所負責人閱讀瞭解，並同意本同意書之所有內容及其後修改變更規定後，方得使用本服務，但若您已接受本服務，視為您已取得技師本人或技師事務所負責人之同意，並遵守以下所有規範：
        </p>
        <h5 class="font-weight-bold">一、授權查調資料之蒐集</h5>
        <table class="mb-3" style="margin-left: -10px">
          <tr>
            <td valign="top">（一）</td>
            <td>
              委託方因故未填報年度業務報告書之「<span style="font-weight: bold; color: red">總收入</span>」及「<span
                style="font-weight: bold; color: red"
                >營業收入</span
              >」欄位，特同意委託工程會蒐集及利用委託人之<span style="font-weight: bold; text-decoration: underline"
                >個人身分證統一編號</span
              >代向財政部財政資訊中心申請查調授權範圍內項目，俾將資料交由工程會領回。
            </td>
          </tr>
          <tr>
            <td valign="top">（二）</td>
            <td>
              授權範圍包含：「<span style="font-weight: bold; text-decoration: underline">個人年度所得</span
              >」與個人綜合所得申報書表之「年度執行業務(其他)所得損益計算表」項目「<span
                style="font-weight: bold; text-decoration: underline"
                >1.本年度收入總額</span
              >」。
            </td>
          </tr>
          <tr>
            <td valign="top">（三）</td>
            <td>
              委託人原應依技師法第二十三條第三款於年度結束之次日起六個月內，檢具年度業務報告書，送中央主管機關備查。<span
                style="font-weight: bold; text-decoration: underline"
                >託人如同意將「總收入」及「營業收入」內容授權工程會向財政部查調欄位財稅資料，<span style="color: red"
                  >上開二項授權欄位將視為已完成填報</span
                ></span
              >。
            </td>
          </tr>
          <tr>
            <td valign="top">（四）</td>
            <td>
              工程會將於七月匯出授權同意名單，續向財政部財政資訊中心進行查調作業，<span
                style="font-weight: bold; text-decoration: underline"
                >預計九月底取得資料匯入本資料庫</span
              >。
            </td>
          </tr>
          <tr>
            <td valign="top">（五）</td>
            <td>
              因個人綜合所得申報書表之年度執行業務所得，現階段未能就執行業務身分拆分所得，<span
                style="font-weight: bold; text-decoration: underline; color: red"
                >本服務建議僅具「技師」身分執行業務者使用，如有多項業別身分，建議自行填報</span
              >。
            </td>
          </tr>
        </table>
        <h5 class="font-weight-bold">二、授權查調資料之保密</h5>
        <p style="padding-left: 40px">
          本服務於資料傳送過程如涉有敏感性個資，工程會將以密件方式處理，與財政部財政資訊中心資料交換並採取加密光碟方式，以維護資訊安全。
        </p>
        <h5 class="font-weight-bold">三、授權查調資料之應用</h5>
        <p style="padding-left: 40px">
          透過授權查調可使政府機關掌握工程技術服務業產業結構現況及市場需求，提供更精準產業產量趨勢，減少因低報收入使預測結果降低之狀況，將有助於準確預測未來產值分析與情勢研判，以制定最適決策，協助業者掌握市場脈動，拓展國際貿易機會。
        </p>
        <h5 class="font-weight-bold">四、撤回授權查調之權益</h5>
        <p style="padding-left: 40px">
          本服務係為簡政便民提升系統填報效率，委託人如送出同意授權仍得於六月三十日（含）前撤回授權，並自行負責至系統填報相關欄位。
        </p>
        <hr />
        <b-form-row class="mb-1">
          <!-- 請輸入扣繳統一編號 -->
          <i-form-group-check label="請輸入扣繳統一編號：" :item="$v.compIdno" class="offset-4 col-4" label-cols="6" content-cols="6">
            <b-form-input v-model="$v.compIdno.$model"></b-form-input>
          </i-form-group-check>
        </b-form-row>
        <div class="text-center mb-1">
          <b-form-checkbox v-model="$v.isChecked.$model">
            {{ $v.engYearreportAuthLast.$model.isAuthorize === 1 ? '不同意授權書內容' : '已閱讀並接受上述授權書內容' }}
          </b-form-checkbox>
        </div>
        <div class="text-center mb-3">
          {{ $v.engYearreportAuthLast.$model.isAuthorize === 1 ? '同意授權日期：' : '不同意授權日期：' }}
          {{ $v.engYearreportAuthLast.$model.createTime | dateToString }}
        </div>
        <div class="text-center">
          <b-button size="sm" type=""
            >送出 ( {{ $v.engYearreportAuthLast.$model.isAuthorize === 1 ? '不同意授權' : '同意授權' }} )</b-button
          >
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script lang="ts">
import { reactive, watch } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';

export default {
  name: 'Eng0202AuthorizationService',
  props: {
    engYearreport: {
      type: Object,
      required: false,
    },
  },
  setup(props) {
    let formDefault = {
      engrName: '',
      engrIdno: '',
      compName: '',
      masterName: '',
      compIdno: '',
      engYearreportAuth: [],
      engYearreportAuthLast: {
        engYearreportAuthId: null,
        isAuthorize: null,
        createTime: new Date(),
      },
      isChecked: null,
    };
    const formRules = {
      engrName: {},
      engrIdno: {},
      compName: {},
      masterName: {},
      compIdno: {},
      engYearreportAuthLast: {
        engYearreportAuthId: {},
        isAuthorize: {},
        createTime: {},
      },
      isChecked: {},
    };
    const formEmpty = Object.assign({}, formDefault);
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(formRules, form, formDefault);

    watch(
      () => props.engYearreport,
      (newVal: object) => {
        // console.log("authorization-service watch!!!!!!!!!!!");
        Object.assign(formDefault, formEmpty);
        for (let key in formDefault) {
          if (newVal[key]) formDefault[key] = newVal[key];
        }
        if (newVal['engYearreportAuth']) {
          const authIdLast = Math.max(...newVal['engYearreportAuth'].map(item => item.engYearreportAuthId));
          Object.assign(
            formDefault.engYearreportAuthLast,
            newVal['engYearreportAuth'].find(item => item.engYearreportAuthId === authIdLast)
          );
        }
        reset();
      },
      { immediate: true, deep: true }
    );

    const putForm = () => {
      if (form.isChecked) {
        let newAuth = {
          engYearreportAuthId: form.engYearreportAuthLast.engYearreportAuthId + 1,
          isAuthorize: form.engYearreportAuthLast.isAuthorize === 0 ? 1 : 0,
          createTime: new Date(),
        };
        form.engYearreportAuth.push(newAuth);
      }
      return form.engYearreportAuth;
    };

    return {
      $v,
      validateState,
      checkValidity,
      reset,
      putForm,
    };
  },
  filters: {
    dateToString: (date: Date) => {
      const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      return year + '年' + month + '月' + day + '日';
    },
  },
};
</script>

<style scoped>
.text-decoration-underline {
  text-decoration: underline;
}
</style>>
</style>

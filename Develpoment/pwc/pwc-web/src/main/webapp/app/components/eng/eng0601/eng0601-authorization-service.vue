<template>
  <b-container>
    <b-row align-h="center">
      <b-col cols="11">
        <h3 class="text-center font-weight-bold ">
          「公共工程技術服務廠商履約紀錄查詢功能」聲明
        </h3>
        <hr/>
        <h5 class="font-weight-bold">一、授權查調資料之蒐集</h5>
        <table class="mb-3" style="margin-left: -10px;">
          <tr>
            <td valign="top">（一）</td>
            <td>委託方因故未填報年度業務報告書之「營業收入」及「非營業收入」欄位，特同意委託工程會蒐集及利用委託方之<span style="font-weight: bold; text-decoration: underline;">營利事業統一編號</span>代向財政部財政資訊中心申請查調授權範圍內項目，俾將資料交由工程會領回。</td>
          </tr>
          <tr>
            <td valign="top">（二）</td>
            <td>授權範圍包含：委託方之營利事業所得稅結算申報書表「年度損益及稅額計算表」項目<span style="font-weight: bold;">「<span style="text-decoration: underline;">01營業收入總額</span>」</span>及<span style="font-weight: bold;">「<span style="text-decoration: underline;">34非營業收入總額</span>」</span>。</td>
          </tr>
          <tr>
            <td valign="top">（三）</td>
            <td>委託方原應依工程技術顧問公司管理條例第二十一條第一項於年度結束之次日起六個月內，檢具主管機關規定之年度業務報告書，送主管機關備查。<span style="font-weight: bold; text-decoration: underline; color: red">委託方如同意將「營業收入」及「非營業收入」內容授權工程會向財政部查調欄位財稅資料，上開二項授權欄位將視為已完成填報</span>。</td>
          </tr>
          <tr>
            <td valign="top">（四）</td>
            <td>工程會將於七月匯出授權同意名單，續向財政部財政資訊中心進行查調作業，<span style="font-weight: bold; text-decoration: underline;">預計九月底取得資料匯入本資料庫</span>。</td>
          </tr>
        </table>
        <h5 class="font-weight-bold">二、授權查調資料之保密</h5>
        <p style="padding-left: 40px;">
          本服務於資料傳送過程如涉有敏感性個資，工程會將以密件方式處理，與財政部財政資訊中心資料交換並採取加密光碟方式，以維護資訊安全。
        </p>
        <h5 class="font-weight-bold">三、授權查調資料之應用</h5>
        <p style="padding-left: 40px;">
          透過授權查調可使政府機關掌握工程技術服務業產業結構現況及市場需求，提供更精準產業產量趨勢，減少因低報收入使預測結果降低之狀況，將有助於準確預測未來產值分析與情勢研判，以制定最適決策，協助業者掌握市場脈動，拓展國際貿易機會。
        </p>
        <h5 class="font-weight-bold">四、撤回授權查調之權益</h5>
        <p style="padding-left: 40px;">
          本服務係為簡政便民提升系統填報效率，委託方如送出同意授權仍得於六月三十日（含）前撤回授權，並自行負責至系統填報相關欄位。
        </p>
      </b-col>
    </b-row>
    <div class="text-center mb-2">
      <!-- 返回 -->
      <i-button type="arrow-left" @click="toQueryView" />
    </div>
  </b-container>
</template>
<script lang="ts">
import { reactive, ref, toRefs, onMounted, defineComponent, unref, onActivated, toRef } from '@vue/composition-api';
import { ApplyItemEnum, FormStatusEnum, MemberTypeEnum, StatusEnum } from '@/shared/enum';
import { IEngEngrSubjectpoint, EngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import { navigateByNameAndParams } from '@/router/router';
import { handleBack } from '@/router/router';
interface propsType {
  engEngrSubjectpoint: IEngEngrSubjectpoint;
  formStatus: FormStatusEnum;
  criteria: any;
}
export default defineComponent<propsType>({
  name: 'Eng0601Authorization',
  props: {
    engEngrSubjectpoint: {
      required: false,
      type: Object,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    criteria: {
      required: false,
      type: Object,
    },
    isReload: {
      type: Boolean,
      required: false,
    },
  },
setup(props) {

const isReload = ref(false);
    const { engEngrSubjectpoint, formStatus, criteria } = toRefs(props);

  function toQueryView() {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    }

    return {
      FormStatusEnum,
      toQueryView,
    }
  };
});
</script>
<style></style>
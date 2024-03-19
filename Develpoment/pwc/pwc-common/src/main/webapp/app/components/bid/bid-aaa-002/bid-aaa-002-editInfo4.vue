<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <!-- 開始規劃設計日期 -->
        <b-form-row>
          <i-form-group-check :label="'開始規劃設計日期：'" :item="$v.pdsDate">
            <b-form-row>
              <i-date-picker v-model="$v.pdsDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 預定規劃設計完成日期 -->
        <b-form-row>
          <i-form-group-check :label="'預定規劃設計完成日期：'" :item="$v.dsDate">
            <b-form-row>
              <i-date-picker v-model="$v.dsDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 實際規劃設計完成日期 -->
        <b-form-row>
          <i-form-group-check :label="'實際規劃設計完成日期：'" :item="$v.adsDate">
            <b-form-row>
              <i-date-picker v-model="$v.adsDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 無法於期限前完成細部設計原因-->
        <b-form-row>
          <i-form-group-check :label="'無法於期限前完成細部設計原因：'" :item="$v.dsdm">
            <b-form-textarea rows="3" max-rows="6" v-model="$v.dsdm.$model" :state="validateState($v.dsdm)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 是否須辦理生態檢核作業 -->
        <b-row>
          <i-form-group-check :label="'是否須辦理生態檢核作業：'" :item="$v.isSpay">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.isSpay.$model">
                <b-form-radio value="Y">是，已依工程會公共工程生態檢核注意事項第二條規定辦理生態檢核作業</b-form-radio>
                <b-form-radio value="Z">是，惟漏依規定於計畫核定、規劃、設計階段辦理生態檢核作業</b-form-radio>
                <b-form-radio value="N">否，無須辦理生態檢核作業</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
      </div>
    </div>

    <div class="card">
      <div class="card-body col-12">
        <!-- 預定公告日期 -->
        <b-form-row>
          <i-form-group-check :label="'預定公告日期：'" :item="$v.opDate">
            <b-form-row>
              <i-date-picker v-model="$v.opDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 實際公告日期 -->
        <b-form-row>
          <i-form-group-check :label="'實際公告日期：'" :item="$v.aopDate">
            <b-form-row>
              <i-date-picker v-model="$v.aopDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
          <i-form-group-check :label="'(本公告為第'" :item="$v.aopCnt">
            <b-form-select class="col-3" v-model="$v.aopCnt.$model" :options="aopCntOpt"></b-form-select>
            <div class="col-8">
              <div class="d-inline">次公告)</div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 預定決標日期 -->
        <b-form-row>
          <i-form-group-check :label="'預定決標日期：'" :item="$v.pctDate">
            <b-form-row>
              <i-date-picker v-model="$v.pctDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 實際決標日期 -->
        <b-form-row>
          <i-form-group-check :label="'實際決標日期：'" :item="$v.actDate">
            <b-form-row>
              <i-date-picker v-model="$v.actDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 預定招標方式 -->
        <b-form-row>
          <i-form-group-check :label="'預定招標方式：'" :item="$v.ctmeth">
            <b-form-select v-model="$v.ctmeth.$model" :options="ctmethOpt"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 實際招標方式 -->
        <b-form-row>
          <i-form-group-check :label="'實際招標方式：'" :item="$v.actmth">
            <b-form-select v-model="$v.actmth.$model" :options="actmthOpt"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 是否特殊採購 -->
        <b-row>
          <i-form-group-check :label="'是否特殊採購：'" :item="$v.specPurch">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.specPurch.$model">
                <b-form-radio value="N">非屬特殊採購工程</b-form-radio>
                <b-form-radio value="Y">符合「投標廠商資格與特殊或巨額採購認定標準」第6條所定特殊採購並於招標公告說明者(限制性招標由主辦機關依同條規定認定)</b-form-radio>
                <b-form-radio value="A">招標文件投標廠商資格訂有需具有相當經驗、實績、人力、財力或設備等特定資格者</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 是否國際競圖 -->
        <b-row>
          <i-form-group-check :label="'是否國際競圖：'" :item="$v.isIntl">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.isIntl.$model">
                <b-form-radio value="Y">本案依機關辦理公共工程國際競圖注意事項採國際競圖辦理</b-form-radio>
                <b-form-radio value="N">非國際競圖</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 契約費用給付方式 -->
        <b-form-row>
          <i-form-group-check :label="'契約費用給付方式：'" :item="$v.tctmth">
            <b-form-select v-model="$v.tctmth.$model" :options="tctmthOpt"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 契約費用給付方式 -->
        <b-form-row>
          <i-form-group-check :label="'決標方式：'" :item="$v.mctmth">
            <b-form-select v-model="$v.mctmth.$model" :options="mctmthOpt"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 是否複數決標 -->
        <b-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'是否複數決標：'" :item="$v.ccnt3">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.ccnt3.$model">
                <b-form-radio value="Y">本案依採購法第五十二條施行細則第65條採複數決標辦理</b-form-radio>
                <b-form-radio value="N">非複數決標</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 是否參考廠商履約情形計分結果選商 -->
        <b-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'是否參考廠商履約情形計分結果選商：'" :item="$v.isPer">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.isPer.$model">
                <b-form-radio value="Y">是</b-form-radio>
                <b-form-radio value="N">否</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 決標金額-->
        <b-form-row>
          <i-form-group-check :label="'決標金額：'" :item="$v.ctSum">
            <b-form-input class="col-3" v-model="$v.ctSum.$model"></b-form-input>
            <div class="col-8">
              <div class="d-inline">千元</div>
            </div>
          </i-form-group-check>
        </b-form-row>
      </div>
    </div>
    <div class="card">
      <div class="card-body col-12">
        <!-- 差異保證金 -->
        <b-form-row>
          <b-col cols="1" class="text-right label pr-1">
            <div class="pt-2">決標差異分析</div>
          </b-col>

          <b-col cols="11">
            <i-form-group-check :label="'差異保證金：'" :item="$v.ctSumU">
              <b-form-input class="col-3" v-model="$v.ctSumU.$model"></b-form-input>
              <div class="col-8">
                <div class="d-inline">千元</div>
              </div>
            </i-form-group-check>
          </b-col>
        </b-form-row>
        <!-- 決標差異分析-->
        <b-form-row>
          <i-form-group-check :label="'(填列決標與預定方式相異或金額差距過大或與決標公告金額不同之原因)：'" :item="$v.ctmo">
            <b-form-textarea rows="3" max-rows="6" v-model="$v.ctmo.$model" :state="validateState($v.ctmo)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 預付款 -->
        <b-form-row>
          <i-form-group-check :label="'預付款：'" :item="$v.pays">
            <b-form-input class="col-3" v-model="$v.pays.$model"></b-form-input>
            <div class="col-8">
              <div class="d-inline">千元</div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 付款方式 -->
        <b-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'付款方式：'" :item="$v.paymth">
            <div>
              <b-form-radio-group v-model="$v.paymth.$model">
                <b-form-radio value="1">分期估驗計價付款</b-form-radio>
                <b-form-radio value="2">驗收後一次計價付款</b-form-radio>
                <b-form-radio value="3">其他：依里程碑、節能績效..等計價付款</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 是否進用外籍營造工 -->
        <b-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'是否進用外籍營造工：'" :item="$v.foreignYn">
            <div>
              <b-form-radio-group v-model="$v.foreignYn.$model">
                <b-form-radio value="Y">本案依勞動部專案核定...聘僱外籍營造工作業規範進用外籍營造工</b-form-radio>
                <b-form-radio value="N">未進用外籍營造工</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 是否已開工 -->
        <b-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'是否已開工：'" :item="$v.kichOff">
            <div>
              <b-form-radio-group v-model="$v.kickOff.$model">
                <b-form-radio value="Y">未開工</b-form-radio>
                <b-form-radio value="N">已開工</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 預定開工日期 -->
        <b-form-row>
          <i-form-group-check :label="'預定開工日期：'" :item="$v.csDate">
            <b-form-row>
              <i-date-picker v-model="$v.csDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 實際開工日期 -->
        <b-form-row>
          <i-form-group-check :label="'實際開工日期：'" :item="$v.asDate">
            <b-form-row>
              <i-date-picker v-model="$v.asDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
          <i-form-group-check :label="'實際動工日期：'" :item="$v.asuDate">
            <b-form-row>
              <i-date-picker v-model="$v.asuDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 預定完工日期 -->
        <b-form-row>
          <i-form-group-check :label="'預定完工日期：'" :item="$v.ceDate">
            <b-form-row>
              <i-date-picker v-model="$v.ceDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 實際完工日期 -->
        <b-form-row>
          <i-form-group-check :label="'實際完工日期：'" :item="$v.aeDate">
            <b-form-row>
              <i-date-picker v-model="$v.aeDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 結算金額 -->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'結算金額：'" :item="$v.fcost2">
            <b-form-input class="col-1" v-model="$v.fcost2.$model"></b-form-input>
            <div class="col-11">
              <div class="d-inline">
                千元
                <span style="color: red">(結算金額為 契約金額 + 增減金額 - 減少金額 - 驗收扣款)</span>
              </div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 預定決算日期 -->
        <b-form-row>
          <i-form-group-check :label="'預定決算日期：'" :item="$v.fcsDate">
            <b-form-row>
              <i-date-picker v-model="$v.fcsDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 實際決算日期 -->
        <b-form-row>
          <i-form-group-check :label="'實際決算日期：'" :item="$v.afcsDate">
            <b-form-row>
              <i-date-picker v-model="$v.afcsDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            </b-form-row>
          </i-form-group-check>
        </b-form-row>
        <!-- 決算金額 -->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'決算金額：'" :item="$v.fcost">
            <b-form-input class="col-1" v-model="$v.fcost.$model"></b-form-input>

            <div class="col-11">
              <div class="d-inline">
                千元
                <span style="color: red">(結算金額為 契約金額 + 增減金額 - 減少金額 - 驗收扣款)</span>
              </div>
            </div>
          </i-form-group-check>
        </b-form-row>
      </div>
    </div>
    <!-- 錯誤訊息 -->
    <b-modal id="valid-error-modal" size="lg" title="資料驗證有誤" :header-text-variant="'light'" :header-bg-variant="'danger'" hide-footer>
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModals.hide('valid-error-modal')">了解</b-button>
    </b-modal>
  </div>
</template>

<script lang="ts">
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { Ref, ref, toRef, reactive, watch, computed, Reactive } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';

export default {
  name: 'bidAaa002EditInfo4',
  props: {
    bidProjectInfo: {
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectInfoKeyProp: Ref<any> = toRef(props, 'bidProjectInfo');

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const notificationService: NotificationService = useNotification();
    const bidSysCodeTable = new BidSysCodeTable();

    const aopCntOpt = reactive([{ value: '', text: '請選擇' },]);
    const ctmethOpt = reactive([]);
    const actmthOpt = reactive([]);
    const tctmthOpt = reactive([]);
    const mctmthOpt = reactive([]);

    for (let i = 1; i <= 20; i++) {
       var num = "0".concat(i).slice(-2);
       const item = { value: '', text: '' };
       item.value = num;
       item.text = num;
       aopCntOpt.push(item);
    }
    //去後端撈選項
    bidSysCodeTable.getBidSysCodeWithParam('BID_006').then(returnArray => {
      ctmethOpt.splice(0, ctmethOpt.length);
      if (returnArray && returnArray.length > 0) {
        returnArray.forEach(ele => {
          const item = { value: '', text: '' };
          item.value = ele.dataKey;
          item.text = ele.value;
          ctmethOpt.push(item);
        });
      }
    });
    bidSysCodeTable.getBidSysCodeWithParam('BID_007').then(returnArray => {
      actmthOpt.splice(0, actmthOpt.length);
      if (returnArray && returnArray.length > 0) {
        returnArray.forEach(ele => {
          const item = { value: '', text: '' };
          item.value = ele.dataKey;
          item.text = ele.value;
          actmthOpt.push(item);
        });
      }
    });
    bidSysCodeTable.getBidSysCodeWithParam('BID_008').then(returnArray => {
      tctmthOpt.splice(0, tctmthOpt.length);
      if (returnArray && returnArray.length > 0) {
        returnArray.forEach(ele => {
          const item = { value: '', text: '' };
          item.value = ele.dataKey;
          item.text = ele.value;
          tctmthOpt.push(item);
        });
      }
    });
    bidSysCodeTable.getBidSysCodeWithParam('BID_009').then(returnArray => {
      mctmthOpt.splice(0, mctmthOpt.length);
      if (returnArray && returnArray.length > 0) {
        returnArray.forEach(ele => {
          const item = { value: '', text: '' };
          item.value = ele.dataKey;
          item.text = ele.value;
          mctmthOpt.push(item);
        });
      }
    });

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formDefault = {
      pdsDate: '', //開始規劃設計日期
      dsDate: '', //預定規劃設計完成日期
      adsDate: '', //實際規劃設計完成日期
      dsdm: '', //無法於期限前完成細部設計原因
      isSpay: '', //是否須辦理生態檢核作業
      opDate: '', //預定公告日期
      aopDate: '', //實際公告日期
      aopCnt: '', //第幾次實際公告
      pctDate: '', //預定決標日期
      actDate: '', //實際決標日期
      ctmeth: '', //預定招標方式
      actmth: '', //實際招標方式
      specPurch: '', //是否特殊採購
      isIntl: '', //是否國際競圖
      tctmth: '', //契約費用給付方式
      mctmth: '', //決標方式
      ccnt3: '', //是否複數決標
      isPer: '', //是否參考廠商履約情形計分結果選商
      ctSum: '', //決標金額
      ctSumU: '', //差異保證金
      ctmo: '', //決標差異分析
      pays: '', //預付款
      foreignYn: '', //是否進用外籍營造工
      paymth: '', //付款方式
      kickOff: '', //是否已開工
      csDate: '', //預定開工日期
      asDate: '', //實際開工日期
      asuDate: '', //實際動工日期
      ceDate: '', //預定完工日期
      aeDate: '', //實際完工日期
      fcost2: '', //結算金額
      fcsDate: '', //預定決算日期
      afcsDate: '', //實際決算日期
      fcost: '', //決算金額
    };
    let formEmpty = Object.assign({}, formDefault);
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      pdsDate: {},
      dsDate: {},
      adsDate: {},
      dsdm: {},
      isSpay: {},
      opDate: {},
      aopDate: {},
      aopCnt: {},
      pctDate: {},
      actDate: {},
      ctmeth: {},
      actmth: {},
      specPurch: {},
      isIntl: {},
      tctmth: {},
      mctmth: {},
      ccnt3: {},
      isPer: {},
      ctSum: {},
      ctSumU: {},
      ctmo: {},
      pays: {},
      foreignYn: {},
      paymth: {},
      kickOff: {},
      csDate: {},
      asDate: {},
      asuDate: {},
      ceDate: {},
      aeDate: {},
      fcost2: {},
      fcsDate: {},
      afcsDate: {},
      fcost: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAaa002Query');
    }

    function putForm() {
      return form;
    }

    watch(() => props.bidProjectInfo, (newVal: object) => {
      Object.assign(formDefault, formEmpty)
      for(let key in formDefault){
        if(newVal[key]) formDefault[key] = newVal[key];
        if(key === 'pdsDate'
          || key === 'dsDate'
          || key === 'adsDate'
          || key === 'opDate'
          || key === 'aopDate'
          || key === 'pctDate'
          || key === 'actDate'
          || key === 'csDate'
          || key === 'asDate'
          || key === 'asuDate'
          || key === 'ceDate'
          || key === 'aeDate'
          || key === 'fcsDate'
          || key === 'afcsDate'
        ) {
          if (newVal[key] != null) {
            formDefault[key] = new Date(newVal[key]);
          }
        }
      }
      reset();
    },{immediate: true, deep: true});

    return {
      $v,
      checkValidity,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      formDefault,
      stepVisible,
      aopCntOpt,
      ctmethOpt,
      actmthOpt,
      tctmthOpt,
      mctmthOpt,
      putForm,
    };
  },
};
</script>

<style></style>

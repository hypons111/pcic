<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <b-row>
          <b-col cols="6">
            <!-- 查核紀錄核定狀況 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="'查核紀錄核定狀況'" :item="$v.chkflag">
                <b-form-select v-model="$v.chkflag.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
          </b-col>
          <b-col cols="6">
            <!-- 查核紀錄發文日期文號-->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="4"
                content-cols-md="6"
                :label="'查核紀錄發文日期文號'"
                :item="$v.roveGNum"
              >
                <b-form-input v-model="$v.roveGNum.$model" :state="validateState($v.roveGNum)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
          </b-col>
        </b-row>
        <b-row>
          <b-col cols="6">
            <!-- 發文超過七天之原因 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="6" :label="'發文超過七天之原因'" :item="$v.ov7day">
                <b-form-textarea rows="3" max-rows="6" v-model="$v.ov7day.$model"></b-form-textarea>
              </i-form-group-check>
            </b-form-row>
          </b-col>
          <b-col cols="6">
            <!-- 改善期限 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="6" :label="'改善期限'" :item="$v.deadDate">
                <b-form-row>
                  <i-date-picker v-model="$v.deadDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
                </b-form-row>
              </i-form-group-check>
            </b-form-row>
          </b-col>
        </b-row>
        <b-row>
          <b-col cols="6">
            <!-- 改善結果 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="'改善結果'" :item="$v.roveFlag">
                <b-form-select v-model="$v.roveFlag.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
          </b-col>
          <b-col cols="6">
            <!-- 第一次改善結回文日期文號-->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="4"
                content-cols-md="6"
                :label="'第一次改善結回文日期文號'"
                :item="$v.roveBNum"
              >
                <b-form-input v-model="$v.roveBNum.$model" :state="validateState($v.roveBNum)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
          </b-col>
        </b-row>
        <b-row>
          <b-col cols="6">
            <!-- 備查結案日期 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="'備查結案日期'" :item="$v.endDate">
                <i-date-picker v-model="$v.endDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
              </i-form-group-check>
            </b-form-row>
          </b-col>
          <b-col cols="6"> </b-col>
        </b-row>
      </div>
    </div>

    <!-- <div class="card">
      <div class="card-body col-12"> -->
    <b-row>
      <b-col cols="8">
        <b-table-simple hover small bordered caption-top responsive>
          <caption></caption>
          <b-thead>
            <b-tr>
              <b-th></b-th>
              <b-th>查核小組建議</b-th>
              <b-th></b-th>
              <b-th width="10%">實際</b-th>
            </b-tr>
          </b-thead>
          <b-tbody>
            <b-tr>
              <b-th></b-th>
              <b-th width="55%">函請主辦機關依法檢討相關人員責任:</b-th>
              <b-td>
                <b-form-radio-group v-model="$v.roveChk0.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group>
              </b-td>
              <td></td>
            </b-tr>
            <b-tr>
              <b-th rowspan="4" width="15%">對主辦機關</b-th>
              <b-th>人員懲處:</b-th>
              <b-td width="20%">
                <b-form-radio-group v-model="$v.roveChk1.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group>
              </b-td>
              <td>否</td>
            </b-tr>
            <b-tr>
              <b-th>人員獎勵:</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk14.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th>通知機關另未適當處置並副之審計機關:</b-th>
              <b-td>
                <b-form-radio-group v-model="$v.roveChk12.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th>函送監察院</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk13.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th width="10%">對專案管理</b-th>
              <b-th>辦理扣款:</b-th>
              <b-td>
                <b-form-radio-group v-model="$v.roveChk7.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group>
              </b-td>
              <b-td>否</b-td>
            </b-tr>
            <b-tr>
              <b-th rowspan="8" width="15%">對主辦機關</b-th>
              <b-th>依政府採購法第101條至第103條規定辦理:</b-th>
              <b-td>
                <b-form-radio-group v-model="$v.roveChk2.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group>
              </b-td>
              <b-td>是</b-td>
            </b-tr>
            <b-tr>
              <b-th>撤換工地負責人</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk3.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th>撤換品管負責人</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk32.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th>撤換安衛人員</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk33.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th>檢討工地主任責任</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk37.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th>檢討專任工程人員責任</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk34.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th>缺失嚴重部份拆除重做</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk35.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th>辦理扣款</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk36.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th rowspan="8" width="15%">對監造廠商</b-th>
              <b-th>檢討建築師責任:</b-th>
              <b-td>
                <b-form-radio-group v-model="$v.roveChk4.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group>
              </b-td>
              <b-td>是</b-td>
            </b-tr>
            <b-tr>
              <b-th>檢討技師責任</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk42.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th>委辦監造撤換監造現場人員</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk43.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th>自辦監造懲處監工人員</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk45.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
            <b-tr>
              <b-th>辦理扣款</b-th>
              <b-td
                ><b-form-radio-group v-model="$v.roveChk44.$model">
                  <b-form-radio value="A">是</b-form-radio>
                  <b-form-radio value="B">否</b-form-radio>
                </b-form-radio-group></b-td
              >
              <td></td>
            </b-tr>
          </b-tbody>
        </b-table-simple>

        <b-row>
          <b-col cols="8">實際獎懲狀況摘要:<span style="color: red">(本機於查核完後是需要通知工程機關於D2填報)</span></b-col>
          <b-col cols="6"> </b-col>
          <b-col cols="1"></b-col>
        </b-row>

        <div class="text-center mt-2">
          <i-button type="save"></i-button>
          <i-button type="arrow-counterclockwise" @click="backToPreviousPage"></i-button>
          <i-button type="x-circle"></i-button>
        </div>
      </b-col>
      <b-col>
        <div style="background-color: red">
          <b-card-group deck>
            <b-card header="責任檢討與結案情形" header-tag="header" title="請餘下攔詳列歷次來函，回函及稽催處理紀錄">
              <b-card-text style="color: blue">範例1:業以yymm.dd...函，請承商撤換品管人員，且並以於yy.mm.dd更換完成。</b-card-text>
              <b-card-text style="color: blue">範例2:因主辦機關函覆之改善情形不良，業以yymm.dd...函要求補正。</b-card-text>
              <b-card-text style="color: blue">範例3:主辦機關逾期未函覆，業以yymm.dd...函稽催。</b-card-text>
              <b-card-text style="color: blue">(結案請註明備查日期)</b-card-text>

              <b-card>
                <b-card-text>1.110.04.13台內總字第00000000號函預先通知查核</b-card-text>
                <b-card-text>2.110.05.05台內總字第00000000號函查核紀錄6/15</b-card-text>
                <b-card-text>3.110.06.10營署水字第00000000號函改善結果</b-card-text>
                <b-card-text>4.110.06.29台內總字第00000000號函同意備查</b-card-text>
              </b-card>
              <b-row style="color: blue" class="py-3">
                <b-col><u>品管人員資料</u></b-col>
                <b-col><u>工地相關人員資料</u></b-col>
              </b-row>
              <b-row style="color: blue" class="py-3">
                <b-col><u>監造現場人員資料</u></b-col>
              </b-row>
              <b-row style="color: blue" class="py-3">
                <b-col><u>廠商專任工程人員資料</u></b-col>
              </b-row>
            </b-card>
          </b-card-group>
        </div>
      </b-col>
    </b-row>

    <!-- 錯誤訊息 -->
    <b-modal
      id="valid-error-modal"
      size="lg"
      title="資料驗證有誤:"
      :header-text-variant="'light'"
      :header-bg-variant="'danger'"
      hide-footer
    >
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModals.hide('valid-error-modal')">了解</b-button>
    </b-modal>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, computed, Reactive } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';

export default {
  name: 'bidAbc001EditInfo9',
  props: {
    bidProjectKey: {
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectModifyKeyProp: Ref<any> = toRef(props, 'bidProjectKey');

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formDefault = {
      chkflag: '', //查核紀錄核定狀況
      roveGNum: '', //查核紀錄發文日期文號
      ov7day: '', //發文超過七天之原因
      deadDate: '', //改善期限
      roveFlag: '', //改善結果
      roveBNum: '', //第一次改善結回文日期文號
      endDate: '', //備查結案日期
      roveChk0: '', //函請主辦機關依法檢討相關人員責任
      roveChk1: '', //人員懲處
      roveChk14: '', //人員獎勵
      roveChk12: '', //通知機關另未適當處置並副之審計機關
      roveChk13: '', //函送監察院
      roveChk7: '', //辦理扣款
      roveChk2: '', //依政府採購法第101條至第103條規定辦理
      roveChk3: '', //撤換工地負責人
      roveChk32: '', //撤換品管負責人
      roveChk33: '', //撤換安衛人員
      roveChk37: '', //檢討工地主任責任
      roveChk34: '', //檢討專任工程人員責任
      roveChk35: '', //缺失嚴重部份拆除重做
      roveChk36: '', //辦理扣款
      roveChk4: '', //檢討建築師責任
      roveChk42: '', //檢討技師責任
      roveChk43: '', //委辦監造撤換監造現場人員
      roveChk45: '', //自辦監造懲處監工人員
      roveChk44: '', //辦理扣款
    };

    const rules = {
      chkflag: { required: required },
      roveGNum: { required: required },
      ov7day: { required: required },
      deadDate: { required: required },
      roveFlag: { required: required },
      roveBNum: {},
      endDate: { required: required },
      roveChk0: { required: required },
      roveChk1: { required: required },
      roveChk14: { required: required },
      roveChk12: { required: required },
      roveChk13: { required: required },
      roveChk7: { required: required },
      roveChk2: { required: required },
      roveChk3: { required: required },
      roveChk32: { required: required },
      roveChk33: { required: required },
      roveChk37: { required: required },
      roveChk34: { required: required },
      roveChk35: { required: required },
      roveChk36: { required: required },
      roveChk4: { required: required },
      roveChk42: { required: required },
      roveChk43: { required: required },
      roveChk45: { required: required },
      roveChk44: { required: required },
    };

    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAbc001Query');
    }

    return {
      $v,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      formDefault,
      stepVisible,
    };
  },
};
</script>

<style></style>

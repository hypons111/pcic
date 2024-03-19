<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <b-row>
          <b-col cols="6">
            <!-- 1.混凝土構造鑽心試體試驗結果 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="5"
                content-cols-md="3"
                :label="'1.混凝土構造鑽心試體試驗結果'"
                :item="$v.testFlag"
              >
                <b-form-select v-model="$v.testFlag.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 2.路面工程瀝青混凝土鑽心試體試驗結果 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="5"
                content-cols-md="3"
                :label="'2.路面工程瀝青混凝土鑽心試體試驗結果'"
                :item="$v.testFlag2"
              >
                <b-form-select v-model="$v.testFlag2.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 2.1AC平整度 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="' 2.1AC平整度'" :item="$v.testFlag7">
                <b-form-select v-model="$v.testFlag7.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 2.2AC厚度 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="' 2.2AC厚度'" :item="$v.testFlag8">
                <b-form-select v-model="$v.testFlag8.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 2.3AC壓實度 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="' 2.3AC壓實度'" :item="$v.testFlag9">
                <b-form-select v-model="$v.testFlag9.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 2.4切割及補修線形是否完整 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="5"
                content-cols-md="3"
                :label="' 2.4切割及補修線形是否完整'"
                :item="$v.testFlagb"
              >
                <b-form-select v-model="$v.testFlagb.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 3.路基工程壓實度測試結果 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="5"
                content-cols-md="3"
                :label="' 3.路基工程壓實度測試結果'"
                :item="$v.testFlag3"
              >
                <b-form-select v-model="$v.testFlag3.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 3.1回填料及夯實 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="' 3.1回填料及夯實'" :item="$v.testFlaga">
                <b-form-select v-model="$v.testFlaga.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 4.主要結構與設計不符情節重大 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="5"
                content-cols-md="3"
                :label="' 4.主要結構與設計不符情節重大'"
                :item="$v.testFlag4"
              >
                <b-form-select v-model="$v.testFlag4.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 5.主要材料設備與設計不符情節重大 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="5"
                content-cols-md="3"
                :label="' 5.主要材料設備與設計不符情節重大'"
                :item="$v.testFlag5"
              >
                <b-form-select v-model="$v.testFlag5.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 5.1鋼筋材料測試 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="' 5.1鋼筋材料測試'" :item="$v.testFlagh">
                <b-form-select v-model="$v.testFlagh.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 5.2管線TV檢測 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="' 5.2管線TV檢測'" :item="$v.testFlagc">
                <b-form-select v-model="$v.testFlagc.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 5.3管道設計高程 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="' 5.3管道設計高程'" :item="$v.testFlagd">
                <b-form-select v-model="$v.testFlagd.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 5.4管材試驗 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="'5.4管材試驗'" :item="$v.testFlage">
                <b-form-select v-model="$v.testFlage.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 5.5另件試驗 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="'5.5另件試驗'" :item="$v.testFlagf">
                <b-form-select v-model="$v.testFlagf.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 5.6踏步試驗 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="'5.6踏步試驗'" :item="$v.testFlagg">
                <b-form-select v-model="$v.testFlagg.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 5.7其他材料試驗 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="5" content-cols-md="3" :label="'5.7其他材料試驗'" :item="$v.testFlagz">
                <b-form-select v-model="$v.testFlagz.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 6.其他缺失情節重大影響安全 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="5"
                content-cols-md="3"
                :label="'6.其他缺失情節重大影響安全'"
                :item="$v.testFlag6"
              >
                <b-form-select v-model="$v.testFlag6.$model"></b-form-select>
              </i-form-group-check>
            </b-form-row>
          </b-col>
          <!-- 檢驗拆驗或鑑定之過程及結果(如鑽心地點、組數與設計強度)-->
          <b-col cols="6">
            <i-form-group-check
              class="col-sm-12"
              label-cols-md="12"
              content-cols-md="12"
              label-align="left"
              :label="'檢驗拆驗或鑑定之過程及結果(如鑽心地點、組數與設計強度)'"
              :item="$v.testMemo"
            >
              <b-form-textarea rows="12" max-rows="3" v-model="$v.testMemo.$model"></b-form-textarea>
            </i-form-group-check>
            <!-- 檢驗單位-->
            <b-form-row>
              <i-form-group-check
                label-align="left"
                class="col-sm-12"
                label-cols-md="3"
                content-cols-md="6"
                :label="'檢驗單位:'"
                :item="$v.testCo"
              >
                <b-form-input v-model="$v.testCo.$model" :state="validateState($v.testCo)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!-- 檢驗報告日期 -->
            <b-form-row>
              <i-form-group-check
                label-align="left"
                class="col-sm-12"
                label-cols-md="3"
                content-cols-md="6"
                :label="'檢驗報告日期:'"
                :item="$v.testDate"
              >
                <b-form-row>
                  <i-date-picker v-model="$v.testDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
                </b-form-row>
              </i-form-group-check>
            </b-form-row>
            <!-- 報告編號-->
            <b-form-row>
              <i-form-group-check
                label-align="left"
                class="col-sm-12"
                label-cols-md="12"
                content-cols-md="6"
                :label="'報告編號:'"
                :item="$v.testNum"
              >
                <b-form-textarea rows="3" max-rows="6" v-model="$v.testNum.$model" :state="validateState($v.testNum)"></b-form-textarea>
              </i-form-group-check>
            </b-form-row>
          </b-col>
        </b-row>
      </div>
    </div>

    <div class="card">
      <div class="card-body col-12">
        <!-- 規劃設計建議-->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="6" :label="'規劃設計建議'" :item="$v.detel5">
            <b-form-textarea rows="3" max-rows="6" v-model="$v.detel5.$model" :state="validateState($v.detel5)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 其他建議-->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="6" :label="'其他建議'" :item="$v.detel3">
            <b-form-textarea rows="3" max-rows="6" v-model="$v.detel3.$model" :state="validateState($v.detel3)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 總評分-->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'總評分'" :item="$v.score">
            <b-form-input class="col-3" v-model="$v.score.$model" :state="validateState($v.score)"></b-form-input>
            <span style="color: blue" class="col-7">儲存時將自動四捨五入</span>
            <div style="color: red" label-cols-md="2">
              依工程施工查核小組作業辦法第八條規定，相關試驗結果不合格者，應該改為丙等
            </div></i-form-group-check
          >
        </b-form-row>
        <b-form-row>
          <!-- 品質指標 -->
          <b-col cols="2">品質指標</b-col>
          <b-col cols="2">
            <!-- 環境-->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="6" content-cols-md="6" :label="'環境(W4)'" :item="$v.fgop1">
                <b-form-input v-model="$v.fgop1.$model" :state="validateState($v.fgop1)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
          </b-col>
          <b-col cols="2">
            <!-- 安全(W8)-->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="6" content-cols-md="6" :label="'安全(W8)'" :item="$v.fgop2">
                <b-form-input v-model="$v.fgop2.$model" :state="validateState($v.fgop2)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
          </b-col>
          <b-col cols="2">
            <!-- 強度-->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="6" content-cols-md="6" :label="'強度(W7)'" :item="$v.fgop3">
                <b-form-input v-model="$v.fgop3.$model" :state="validateState($v.fgop3)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
          </b-col>
          <b-col cols="2">
            <!-- 美觀-->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="6" content-cols-md="6" :label="'美觀(W5)'" :item="$v.fgop4">
                <b-form-input v-model="$v.fgop4.$model" :state="validateState($v.fgop4)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
          </b-col>
          <b-col cols="2">
            <!-- 功能-->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="6" content-cols-md="6" :label="'功能(W6)'" :item="$v.fgop5">
                <b-form-input v-model="$v.fgop5.$model" :state="validateState($v.fgop5)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
          </b-col>
        </b-form-row>
        <!-- 品質缺失懲罰性毀約金機制 -->
        <b-row>
          <b-col cols="2">品質缺失懲罰性毀約金機制 </b-col>
          <b-col>
            <i-form-group-check class="col-sm-12" label-cols-md="1" content-cols-md="6" :item="$v.isckp">
              <div class="d-flex h-38p align-items-center">
                <b-form-radio-group v-model="$v.isckp.$model">
                  <b-form-radio value="A">招標文件已明定施工單位相關規定</b-form-radio>
                  <b-form-radio value="B">施工單位尚未納入</b-form-radio>
                </b-form-radio-group>
              </div>
            </i-form-group-check>
          </b-col>
        </b-row>
        <b-row>
          <b-col cols="2"> </b-col>
          <b-col>
            <i-form-group-check class="col-sm-12" label-cols-md="1" content-cols-md="6" :item="$v.isckp2">
              <div class="d-flex h-38p align-items-center">
                <b-form-radio-group v-model="$v.isckp2.$model">
                  <b-form-radio value="A">招標文件已明定施工單位相關規定</b-form-radio>
                  <b-form-radio value="B">監造單位尚未納入</b-form-radio>
                </b-form-radio-group>
              </div>
            </i-form-group-check>
          </b-col>
        </b-row>
        <b-row>
          <b-col cols="2"> </b-col>
          <b-col>
            <i-form-group-check class="col-sm-12" label-cols-md="1" content-cols-md="6" :item="$v.isckp3">
              <div class="d-flex h-38p align-items-center">
                <b-form-radio-group v-model="$v.isckp3.$model">
                  <b-form-radio value="A">招標文件已明定施工單位相關規定</b-form-radio>
                  <b-form-radio value="B">專案管理單位尚未納入</b-form-radio>
                </b-form-radio-group>
              </div>
              <span style="color: blue" sm>94.1.31工程管字第09400037860號涵為確保公共工程品質，請於招標文件內，明訂...詳細</span>
              <span style="color: blue" sm
                >101.5.17工程館字第10100180300號涵有關修訂「施工查核小組品質缺失懲處性違約金機制罰款額度...」</span
              >
            </i-form-group-check>
          </b-col>
        </b-row>
        <div class="text-center mt-2">
          <i-button type="save"></i-button>
          <i-button type="arrow-counterclockwise" @click="backToPreviousPage"></i-button>
          <i-button type="x-circle"></i-button>
        </div>
      </div>
    </div>

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
  name: 'bidAbc001EditInfo5',
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
      testFlag: '', //混凝土構造鑽心試體試驗結果
      testFlag2: '', //路面工程瀝青混凝土鑽心試體試驗結果
      testFlag7: '', // 2.1AC平整度
      testFlag8: '', //2.2AC厚度
      testFlag9: '', //2.3AC壓實度
      testFlagb: '', //2.4切割及補修線形是否完整
      testFlag3: '', //3.路基工程壓實度測試結果
      testFlaga: '', //3.1回填料及夯實
      testFlag4: '', //4.主要結構與設計不符情節重大
      testCo: '', //檢驗單位
      testDate: '', //檢驗報告日期
      testNum: '', //報告編號
      testFlag5: '', //主要材料設備與設計不符情節重大
      testFlagh: '', //鋼筋材料測試
      testFlagc: '', //管線TV檢測
      testFlagd: '', //管道設計高程
      testFlage: '', // 管材試驗
      testFlagf: '', // 另件試驗
      testFlagg: '', // 踏步試驗
      testFlagz: '', // 其他材料試驗
      testFlag6: '', //其他缺失情節重大影響安全
      testMemo: '', //檢驗拆驗或鑑定之過程及結果(如鑽心地點、組數與設計強度)
      detel5: '', //規劃設計建議
      detel3: '', //其他建議(檢討與建議)
      score: '', //總評分(成績)
      fgop1: '', //環境(W4)
      fgop2: '', //安全(W8)
      fgop3: '', //強度(W8)
      fgop4: '', //美觀(W5)
      fgop5: '', //功能(W6)
      isckp: '', //
      isckp2: '', //
      isckp3: '', //
    };

    const rules = {
      testFlag: { required: required },
      testFlag2: { required: required },
      testFlag7: { required: required },
      testFlag8: { required: required },
      testFlag9: { required: required },
      testFlagb: { required: required },
      testFlag3: { required: required },
      testFlaga: { required: required },
      testFlag4: { required: required },
      testCo: { required: required },
      testDate: {},
      testNum: { required: required },
      testFlag5: { required: required },
      testFlagh: { required: required },
      testFlagc: { required: required },
      testFlagd: {},
      testFlage: { required: required },
      testFlagf: { required: required },
      testFlagg: { required: required },
      testFlagz: { required: required },
      testFlag6: { required: required },
      testMemo: { required: required },
      detel5: { required: required },
      detel3: { required: required },
      score: { required: required },
      fgop1: { required: required },
      fgop2: { required: required },
      fgop3: { required: required },
      fgop4: { required: required },
      fgop5: { required: required },
      isckp: { required: required },
      isckp2: { required: required },
      isckp3: { required: required },
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

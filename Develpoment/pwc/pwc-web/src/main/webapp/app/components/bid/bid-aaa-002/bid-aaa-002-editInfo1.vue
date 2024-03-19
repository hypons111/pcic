<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <b-row>
          <b-col class="col-1">
          </b-col>
          <b-col class="col-11">
            <!-- 標案名稱 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'標案名稱：'" :labelStar="true" :item="$v.name">
                <b-form-input class="col-3" v-model="$v.name.$model" :state="validateState($v.name)"></b-form-input>
                <div class="col-8" v-if="$v.name0.$model">(決標公告標的名稱：{{ $v.name0.$model }})</div>
              </i-form-group-check>
            </b-form-row>
            <!-- 專案項目 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'專案項目：'" :item="$v.isAdd92">
                <b-form-select v-model="$v.isAdd92.$model" :options="isAdd92Opts"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 列管狀態 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'列管狀態：'" :item="$v.pntCtrl">
                <b-form-select v-model="$v.pntCtrl.$model" :options="pntCtrlOpts"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 工程類別 -->
            <b-form-row>
              <i-form-group-check :label="'工程類別：'" :item="$v.typeCode" :labelStar="true">
                <b-form-select v-model="$v.typeCode.$model" :options="typeCodeOpts"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 是否統包 -->
            <b-row>
              <i-form-group-check :label="'是否統包：'" :item="$v.isUnion">
                <div class="d-flex h-38p align-items-center">
                  <b-form-radio-group v-model="$v.isUnion.$model">
                    <b-form-radio value="Y">本案採購法第二十四條採統包方式辦理</b-form-radio>
                    <b-form-radio value="N">非屬統包案件</b-form-radio>
                  </b-form-radio-group>
                </div>
              </i-form-group-check>
            </b-row>
            <!-- 縣市鄉鎮 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'縣市鄉鎮：'" :item="$v.placeCode" :labelStar="true">
                <b-form-select v-model="$v.placeCode.$model" :options="placeCodeOpts"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 施工地點-->
            <b-form-row>
              <i-form-group-check :label="'施工地點：'" :item="$v.place">
                <b-form-input v-model="$v.place.$model" :state="validateState($v.place)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!-- 主辦機關,主管機關-->
            <b-form-row>
              <i-form-group-check :label="'主辦機關：'" :item="$v.depCode">
                <b-form-select v-model="$v.depCode.$model" :options="depCodeOpts"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="'主管機關：'" :item="$v.bosCode">
                <b-form-select v-model="$v.bosCode.$model" :options="bosCodeOpts"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 計畫型補助或委託代辦 -->
            <b-row>
              <i-form-group-check :label="'計畫型補助或委託代辦：'" :item="$v.isBg">
                <div class="d-flex h-38p align-items-center">
                  <b-form-radio-group v-model="$v.isBg.$model">
                    <b-form-radio value="A">中央政府計畫型案件(國發會GPMnet個案列管計畫)</b-form-radio>
                    <b-form-radio value="D">中央政府計畫型有地配合款案件</b-form-radio>
                    <b-form-radio value="B">補助或委託代辦案件</b-form-radio>
                    <b-form-radio value="Z">本機關經費自辦案件</b-form-radio>
                  </b-form-radio-group>
                </div>
              </i-form-group-check>
            </b-row>
          </b-col>
          <b-col class="col-1">
            <!-- 歸屬計畫-->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="12" :label="'歸屬計畫'" />
            </b-form-row>
          </b-col>
          <b-col class="col-11">
            <!-- 計劃主辦機關-->
            <b-form-row>
              <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="'計劃主辦機關：'" :item="$v.plnCode">
                <b-form-input class="col-2" v-model="$v.plnCode.$model" :state="validateState($v.plnCode)"></b-form-input>
                <b-col class="col-3">
                  <bid-unit10-name :orgId="$v.plnCode.$model" />
                  <b-button variant="outline-dark" @click="showOrgaModal">機關代碼查詢</b-button>
                </b-col>
              </i-form-group-check>
            </b-form-row>
            <!-- 計畫編號-->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'計畫編號：'" :item="$v.plnNo">
                <b-form-input class="col-2" v-model="$v.plnNo.$model" :state="validateState($v.plnNo)"></b-form-input>
                <b-col class="col-3">
                  <b-button variant="outline-dark" @click="showPlanModal">計畫編號查詢</b-button>
                  <b-button variant="outline-dark" @click="showExtendModal" v-if="showExtendModalBtn">延伸查詢</b-button>
                </b-col>
              </i-form-group-check>
            </b-form-row>
            <!-- 國發會列管個案計畫名稱 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'國發會列管個案計畫名稱：'" :item="$v.plnUnicode">
                <b-form-select v-model="$v.plnUnicode.$model" :options="plnUnicodeOpts"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <!-- 預算來源機關 -->
            <b-form-row v-if="$v.isBg.$model==='A'">
              <i-form-group-check :label="'預算來源機關(中央補助)：'" :item="$v.bos2Code">
                <b-form-select v-model="$v.bos2Code.$model" :options="bos2CodeOpts"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="'金額比：'" :item="$v.bos2Rate">
                <b-form-input class="col-3" v-model="$v.bos2Rate.$model"></b-form-input>
                <div class="col-8">
                  <div class="d-inline">%</div>
                </div>
              </i-form-group-check>
            </b-form-row>
            <b-form-row v-if="$v.isBg.$model==='B'">
              <i-form-group-check :label="'預算來源機關(委託代辦)：'" :item="$v.bos2bCode">
                <b-form-select v-model="$v.bos2bCode.$model" :options="bos2CodeOpts"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="'金額比：'" :item="$v.bos2bRate">
                <b-form-input class="col-3" v-model="$v.bos2bRate.$model"></b-form-input>
                <div class="col-8">
                  <div class="d-inline">%</div>
                </div>
              </i-form-group-check>
            </b-form-row>
            <b-form-row v-if="$v.isBg.$model==='B'">
              <i-form-group-check :label="'預算來源機關(地方補助)：'" :item="$v.bos2cCode">
                <b-form-select v-model="$v.bos2cCode.$model" :options="bos2CodeOpts"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="'金額比：'" :item="$v.bos2cRate">
                <b-form-input class="col-3" v-model="$v.bos2cRate.$model"></b-form-input>
                <div class="col-8">
                  <div class="d-inline">%</div>
                </div>
              </i-form-group-check>
            </b-form-row>
            <b-form-row v-if="$v.isBg.$model==='D'">
              <i-form-group-check :label="'預算來源機關(地方配合款)：'" :item="$v.bos2dCode">
                <b-form-select v-model="$v.bos2dCode.$model" :options="bos2CodeOpts"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="'金額比：'" :item="$v.bos2dRate">
                <b-form-input class="col-3" v-model="$v.bos2dRate.$model"></b-form-input>
                <div class="col-8">
                  <div class="d-inline">%</div>
                </div>
              </i-form-group-check>
            </b-form-row>
            <!-- 招標公告上縣單位代碼 -->
            <b-form-row>
              <i-form-group-check :label="'招標公告上縣單位代碼：'" :item="$v.lnkCode">
                <div class="d-inline">
                  <b-form-input v-model="$v.lnkCode.$model" :state="validateState($v.lnkCode)"></b-form-input>
                </div>
              </i-form-group-check>
            </b-form-row>
            <!-- 機關首長 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'機關首長：'" :item="$v.ccnt2" :labelStar="true">
                <div class="d-inline">
                  <b-form-input class="col-12" v-model="$v.ccnt2.$model" :state="validateState($v.ccnt2)"></b-form-input>
                </div>
                <div class="col-8">
                  <div class="d-inline">(請填報決標時簽訂合約之機關首長姓名)</div>
                </div>
              </i-form-group-check>
            </b-form-row>
          </b-col>
          <b-col class="col-1">
            <!-- 標案主辦資料-->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="12" :label="'標案主辦資料'" :labelStar="true" />
            </b-form-row>
          </b-col>
          <b-col class="col-11">
            <!-- 姓名-->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'姓名：'" :item="$v.man">
                <b-form-input v-model="$v.man.$model" :state="validateState($v.man)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!-- 連絡電話-->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'聯絡電話：'" :item="$v.tel">
                <b-form-input v-model="$v.tel.$model" :state="validateState($v.tel)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!-- Email信箱-->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'信箱：'" :item="$v.eMail">
                <b-form-input v-model="$v.eMail.$model" :state="validateState($v.eMail)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!-- 工程概要-->
            <b-form-row>
              <i-form-group-check :label="'工程概要：'" :labelStar="true" :item="$v.contain">
                <b-form-textarea rows="3" max-rows="6" v-model="$v.contain.$model" :state="validateState($v.contain)"></b-form-textarea>
              </i-form-group-check>
            </b-form-row>
            <!-- 功能效益-->
            <b-form-row>
              <i-form-group-check :label="'功能效益：'" :item="$v.benf">
                <b-form-textarea rows="3" max-rows="4" v-model="$v.benf.$model" :state="validateState($v.benf)"></b-form-textarea>
              </i-form-group-check>
            </b-form-row>
            <!-- 是否對外公開 -->
            <b-row>
              <i-form-group-check :label="'是否對外公開：'" :item="$v.isOpen">
                <div class="d-flex h-38p align-items-center">
                  <b-form-radio-group v-model="$v.isOpen.$model">
                    <b-form-radio value="Y">是</b-form-radio>
                    <b-form-radio value="N">否</b-form-radio>
                  </b-form-radio-group>
                </div>
              </i-form-group-check>
            </b-row>
            <!-- 理由 -->
            <b-form-row>
              <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'理由：'" :item="$v.dOpen">
                <b-form-select v-model="$v.dOpen.$model" :options="dOpenOpts" :disabled="$v.isOpen.$model==='Y'"></b-form-select>
              </i-form-group-check>
            </b-form-row>
          </b-col>
        </b-row>
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
    <bid-unit10-list ref="bidUnit10List" />
    <bid-plnbas-list ref="bidPlnbasList" />
  </div>
</template>

<script lang="ts">
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import BidPlaceService from '@/components/bid/bidService/bid-place.service';
import BidPlnUnitbService from '@/components/bid/bidService/bid-plnunitb.service';
import BidUtilService from '@/components/bid/bidService/bid-util-service';
import BidUnit10Name from '@/components/bid/bid-common-component/bid-unit10-name.vue';
import BidUnit10List from '@/components/bid/bid-common-component/bid-unit10-list.vue';
import BidPlnbasList from '@/components/bid/bid-common-component/bid-plnbas-list.vue';
import { Ref, ref, toRef, reactive, watch, computed, Reactive, onActivated, onMounted } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';
import { useGetters } from '@u3u/vue-hooks';

export default {
  name: 'bidAaa002EditInfo1',
  props: {
    bidProjectInfo: {
      required: false,
    },
  },
  components: {
    BidUnit10Name,
    BidUnit10List,
    BidPlnbasList,
  },
  setup(props, context) {
    const bidProjectInfoProp: Ref<any> = toRef(props, 'bidProjectInfo');

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const bidUnit10List = ref(null);
    const bidPlnbasList = ref(null);

    const notificationService: NotificationService = useNotification();
    const bidSysCodeTable = new BidSysCodeTable();
    const bidPlaceService = new BidPlaceService();
    const bidUtilService = new BidUtilService();
    const bidPlnUnitbService = new BidPlnUnitbService();

    const isAdd92Opts = reactive([]);
    const pntCtrlOpts = reactive([]);
    const typeCodeOpts = reactive([]);
    const placeCodeOpts = reactive([]);
    const depCodeOpts = reactive([]);
    const bosCodeOpts = reactive([]);
    const bos2CodeOpts = reactive([]);
    const dOpenOpts = reactive([]);
    const plnUnicodeOpts = reactive([]);

    //去後端撈選項
    bidSysCodeTable.getBidSysCodeWithParam('BID_001').then(returnArray => {
      isAdd92Opts.splice(0, isAdd92Opts.length);
      if (returnArray && returnArray.length > 0) {
        returnArray.forEach(ele => {
          const item = { value: '', text: '' };
          item.value = ele.dataKey;
          item.text = ele.value;
          isAdd92Opts.push(item);
        });
      }
    });
    bidSysCodeTable.getBidSysCodeWithParam('BID_002').then(returnArray => {
      pntCtrlOpts.splice(0, pntCtrlOpts.length);
      if (returnArray && returnArray.length > 0) {
        returnArray.forEach(ele => {
          const item = { value: '', text: '' };
          item.value = ele.dataKey;
          item.text = ele.value;
          pntCtrlOpts.push(item);
        });
      }
    });
    bidSysCodeTable.getBidSysCodeWithParam('BID_003').then(returnArray => {
      typeCodeOpts.splice(0, typeCodeOpts.length);
      if (returnArray && returnArray.length > 0) {
        returnArray.forEach(ele => {
          const item = { value: '', text: '' };
          item.value = ele.dataKey;
          item.text = ele.value;
          typeCodeOpts.push(item);
        });
      }
    });
    bidPlaceService.findAllBidPlaces().then(returnArray => {
      placeCodeOpts.splice(0, placeCodeOpts.length);
      if (returnArray && returnArray.length > 0) {
        returnArray.forEach(ele => {
          const item = { value: '', text: '' };
          item.value = ele.placeCode;
          item.text = ele.placeName;
          placeCodeOpts.push(item);
        });
      }
    });
    bidSysCodeTable.getBidSysCodeWithParam('BID_004').then(returnArray => {
      dOpenOpts.splice(0, dOpenOpts.length);
      if (returnArray && returnArray.length > 0) {
        returnArray.forEach(ele => {
          if (ele.dataKey !== '0') {
            const item = { value: '', text: '' };
            item.value = ele.dataKey;
            item.text = ele.value;
            dOpenOpts.push(item);
          }
        });
      }
    });

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });
    const showExtendModalBtn = ref(true);

    const pccOrgId = ref(useGetters(['account']).account.value).value.pccOrgId;

    const formDefault = {
      wkut: '',
      name: '', //標案名稱
      name0: '', //決標公告標的名稱
      isAdd92: '', //專案項目
      pntCtrl: '', //列管狀態
      typeCode: '', //工程類別
      isUnion: '', //是否統包
      placeCode: '', //縣市鄉鎮
      place: '', //施工地點
      depCode: '', //主辦機關   syscode
      bosCode: '', //主管機關  syscode
      isBg: '', //計畫型補助或委託代辦
      plnCode: '', //計劃主辦機關  syscode
      plnNo: '', //計畫編號
      plnUnicode: '', //國發會列管個案計畫名稱
      bos2Code: '', //預算(經費)來源機關
      bos2Rate: '', //金額比
      bos2bCode: '', //預算(經費)來源機關
      bos2bRate: '', //金額比
      bos2cCode: '', //預算(經費)來源機關
      bos2cRate: '', //金額比
      bos2dCode: '', //預算(經費)來源機關
      bos2dRate: '', //金額比
      lnkCode: '', //招標公告上線單位
      ccnt2: '', //機關首長
      man: '', //姓名(聯絡人)
      tel: '', //連絡電話
      eMail: '', //Email信箱
      contain: '', //工程概要
      benf: '', //功能(工程)效益
      isOpen: '', //是否對外公開
      dOpen: '', // 理由
    };
    let formEmpty = Object.assign({}, formDefault);
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      wkut: {},
      name: { required: required }, //標案名稱
      name0: {}, //決標公告標的名稱
      isAdd92: {}, //專案項目
      pntCtrl: {}, //列管狀態
      typeCode: { required: required }, //工程類別
      isUnion: {}, //是否統包
      placeCode: { required: required }, //縣市鄉鎮
      place: {}, //施工地點
      depCode: {}, //主辦機關   syscode
      bosCode: {}, //主管機關  syscode
      isBg: {}, //計畫型補助或委託代辦
      plnCode: {}, //計劃主辦機關  syscode
      plnNo: {}, //計畫編號
      plnUnicode: {}, //國發會列管個案計畫名稱
      bos2Code: {}, //預算(經費)來源機關
      bos2Rate: {}, //金額比
      bos2bCode: {}, //預算(經費)來源機關
      bos2bRate: {}, //金額比
      bos2cCode: {}, //預算(經費)來源機關
      bos2cRate: {}, //金額比
      bos2dCode: {}, //預算(經費)來源機關
      bos2dRate: {}, //金額比
      lnkCode: {}, //招標公告上線單位
      ccnt2: { required: required }, //機關首長
      man: {}, //姓名(聯絡人)
      tel: {}, //連絡電話
      eMail: {}, //Email信箱
      contain: { required: required }, //工程概要
      benf: {}, //功能(工程)效益
      isOpen: {}, //是否對外公開
      dOpen: {}, //理由
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      initDefaultValue();
    });

    onActivated(() => {
      initDefaultValue();
    });

    function initDefaultValue() {
      pccOrgId = ref(useGetters(['account']).account.value).value.pccOrgId
      bidUtilService.findBidManageUnit(pccOrgId).then(returnArray => {
        depCodeOpts.splice(0, depCodeOpts.length);
        if (returnArray && returnArray.length > 0) {
          returnArray.forEach(ele => {
            const item = { value: '', text: '' };
            item.value = ele.code;
            item.text = ele.name;
            depCodeOpts.push(item);
          });
        }
      });
      bidUtilService.findBidManageUnit(pccOrgId).then(returnArray => {
        bosCodeOpts.splice(0, bosCodeOpts.length);
        if (returnArray && returnArray.length > 0) {
          returnArray.forEach(ele => {
            const item = { value: '', text: '' };
            item.value = ele.code;
            item.text = ele.name;
            bosCodeOpts.push(item);
          });
        }
      });
      bidUtilService.findBidManageUnit(pccOrgId).then(returnArray => {
        bos2CodeOpts.splice(0, bos2CodeOpts.length);
        if (returnArray && returnArray.length > 0) {
          returnArray.forEach(ele => {
            const item = { value: '', text: '' };
            item.value = ele.code;
            item.text = ele.name;
            bos2CodeOpts.push(item);
          });
        }
      });
    }

    function findPlnUnionCode() {
      const queryKey = reactive({
        wkut: form.wkut;
        plnCode: form.plnCode;
        bos2Code: form.bos2Code;
        bos2bCode: form.bos2bCode;
        bos2cCode: form.bos2cCode;
        bos2dCode: form.bos2dCode;
      });
      bidPlnUnitbService.findAllByCriteria(queryKey).then(returnArray => {
        plnUnicodeOpts.splice(0, plnUnicodeOpts.length);
        if (returnArray && returnArray.length > 0) {
          returnArray.forEach(ele => {
            const item = { value: '', text: '' };
            item.value = ele.plnoid;
            item.text = ele.plnname;
            plnUnicodeOpts.push(item);
          });
        }
      });
    }

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAaa002Query');
    }

    function putForm() {
      return form;
    }

    function showOrgaModal() {
      const queryKey = reactive({
        code: form.plnCode;
        type: '0';
      });
      bidUnit10List.value.title = '機關代碼查詢';
      bidUnit10List.value.isShowDia(queryKey, true);
    }

    function showPlanModal() {
      const queryKey = reactive({
        wkut: form.wkut;
        plnCode: form.plnCode;
        bos2Code: form.bos2Code;
        bos2bCode: form.bos2bCode;
        bos2cCode: form.bos2cCode;
        bos2dCode: form.bos2dCode;
        isExtend: 'N';
      });
      bidPlnbasList.value.title = '計畫編號查詢';
      bidPlnbasList.value.isShowDia(queryKey, true);
    }

    function showExtendModal() {
      const queryKey = reactive({
        wkut: form.wkut;
        plnCode: form.plnCode;
        bos2Code: form.bos2Code;
        bos2bCode: form.bos2bCode;
        bos2cCode: form.bos2cCode;
        bos2dCode: form.bos2dCode;
        isExtend: 'Y';
      });
      bidPlnbasList.value.title = '延伸查詢';
      bidPlnbasList.value.isShowDia(queryKey, true);
    }

    watch(() => props.bidProjectInfo, (newVal: object) => {
      Object.assign(formDefault, formEmpty)
      for(let key in formDefault){
        if(newVal[key]) formDefault[key] = newVal[key];
      }
      if (formDefault.wkut.length === 10) {
        if (formDefault.wkut.substring(0, 3) === '371' || formDefault.wkut.substring(0, 3) === '376') {
          showExtendModalBtn.value = false;
        }
      }
      reset();
    },{immediate: true, deep: true});

    watch($v.value.isOpen, newValue => {
      if (newValue !== null && newValue !== undefined) {
        if (newValue.$model === 'Y') {
          $v.value.dOpen.$model = '';
        }
      }
    });

    watch($v.value.plnCode, newValue => {
      if (newValue !== null && newValue !== undefined) {
        if (newValue.$model && newValue.$model.length === 10) {
          findPlnUnionCode();
          if (newValue.$model.substring(0, 3) === '371' || newValue.$model.substring(0, 3) === '376') {
            showExtendModalBtn.value = false;
          } else {
            if ($v.value.wkut.$model.substring(0, 3) === '371' || $v.value.wkut.$model.substring(0, 3) === '376') {
              showExtendModalBtn.value = false;
            } else {
              showExtendModalBtn.value = true;
            }
          }
        }
      }
    });

    watch($v.value.bos2Code, newValue => {
      if (newValue !== null && newValue !== undefined) {
        if (newValue.$model && newValue.$model.length === 10) {
          findPlnUnionCode();
        }
      }
    });

    watch($v.value.bos2bCode, newValue => {
      if (newValue !== null && newValue !== undefined) {
        if (newValue.$model && newValue.$model.length === 10) {
          findPlnUnionCode();
        }
      }
    });

    watch($v.value.bos2cCode, newValue => {
      if (newValue !== null && newValue !== undefined) {
        if (newValue.$model && newValue.$model.length === 10) {
          findPlnUnionCode();
        }
      }
    });

    watch($v.value.bos2dCode, newValue => {
      if (newValue !== null && newValue !== undefined) {
        if (newValue.$model && newValue.$model.length === 10) {
          findPlnUnionCode();
        }
      }
    });

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
      isAdd92Opts,
      pntCtrlOpts,
      typeCodeOpts,
      placeCodeOpts,
      depCodeOpts,
      bosCodeOpts,
      bos2CodeOpts,
      dOpenOpts,
      plnUnicodeOpts,
      putForm,
      showOrgaModal,
      bidUnit10List,
      showPlanModal,
      showExtendModal,
      bidPlnbasList,
      showExtendModalBtn,
    };
  },
};
</script>

<style></style>

<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <!-- 標案名稱 -->
        <b-form-row>
          <i-form-group-check
            class="col-sm-12"
            label-cols-md="2"
            content-cols-md="2"
            :label="'標案名稱:'"
            :labelStar="true"
            :item="$v.name"
          >
            <b-form-input v-model="$v.name.$model" :state="validateState($v.name)"></b-form-input>
          </i-form-group-check>
        </b-form-row>
        <!-- 專案項目 -->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'專案項目:'" :item="$v.isAdd92">
            <b-form-select v-model="$v.isAdd92.$model"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 列管狀態 -->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'列管狀態:'" :item="$v.pntCtrl">
            <b-form-select v-model="$v.pntCtrl.$model"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 工程類別 -->
        <b-form-row>
          <i-form-group-check :label="'工程類別:'" :item="$v.typeCode" :labelStar="true">
            <b-form-select v-model="$v.typeCode.$model"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 是否統包 -->
        <b-row>
          <i-form-group-check :label="'是否統包:'" :item="$v.isUnion">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.isUnion.$model">
                <b-form-radio value="A">本案採購法第二十四條採統包方式辦理</b-form-radio>
                <b-form-radio value="B">非屬統包案件</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 縣市鄉鎮 -->
        <b-form-row>
          <i-form-group-check
            class="col-sm-12"
            label-cols-md="2"
            content-cols-md="2"
            :label="'縣市鄉鎮:'"
            :item="$v.placeCode"
            :labelStar="true"
          >
            <b-form-select v-model="$v.placeCode.$model"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 施工地點-->
        <b-form-row>
          <i-form-group-check :label="'施工地點:'" :item="$v.place">
            <b-form-input v-model="$v.place.$model" :state="validateState($v.place)"></b-form-input>
          </i-form-group-check>
        </b-form-row>
        <!-- 主辦機關,主管機關-->
        <b-form-row>
          <i-form-group-check :label="'主辦機關:'" :item="$v.depCode">
            <b-form-select v-model="$v.depCode.$model"></b-form-select>
          </i-form-group-check>
          <i-form-group-check :label="'主管機關:'" :item="$v.bosCode">
            <b-form-select v-model="$v.bosCode.$model"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 計畫型補助或委託代辦 -->
        <b-row>
          <i-form-group-check :label="'計畫型補助或委託代辦:'" :item="$v.isBg">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.isBg.$model">
                <b-form-radio value="A">中央政府計畫型案件(國發會GPMnet個案列管計畫)</b-form-radio>
                <b-form-radio value="B">中央政府計畫型有地配合款案件</b-form-radio>
                <b-form-radio value="C">補助或委託代辦案件</b-form-radio>
                <b-form-radio value="D">本機關經費自辦案件</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 計劃主辦機關-->
        <b-form-row>
          <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="'計劃主辦機關:'" :item="$v.plnCode">
            <b-form-input class="col-1" v-model="$v.plnCode.$model" :state="validateState($v.plnCode)"></b-form-input>
            <b-col class="col-10">
              <b-button variant="outline-dark">機關代碼查詢</b-button>
            </b-col>
          </i-form-group-check>
        </b-form-row>
        <!-- 計畫編號-->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'計畫編號:'" :item="$v.plnNo">
            <b-form-input class="col-1" v-model="$v.plnNo.$model" :state="validateState($v.plnNo)"></b-form-input>
            <b-col class="col-10">
              <b-button variant="outline-dark">計畫編號查詢</b-button>
              <b-button variant="outline-dark">延伸查詢</b-button>
            </b-col>
          </i-form-group-check>
        </b-form-row>
        <!-- 國發會列管個案計畫名稱 -->
        <b-form-row>
          <i-form-group-check
            class="col-sm-12"
            label-cols-md="2"
            content-cols-md="2"
            :label="'國發會列管個案計畫名稱:'"
            :item="$v.plnInicode"
          >
            <b-form-select v-model="$v.plnInicode.$model"></b-form-select>
          </i-form-group-check>
        </b-form-row>
        <!-- 預算來源機關 -->
        <b-form-row>
          <i-form-group-check :label="'預算來源機關:'" :item="$v.bos2Code">
            <b-form-select v-model="$v.bos2Code.$model"></b-form-select>
          </i-form-group-check>
          <i-form-group-check :label="'金額比:'" :item="$v.bosCode">
            <b-form-input class="col-3" v-model="$v.bosCode.$model"></b-form-input>
            <div class="col-8">
              <div class="d-inline">%</div>
              <i-button type="plus-square-fill"></i-button>
            </div>
          </i-form-group-check>
          <b-col class="col-10">
            <b-button variant="plus-lg"></b-button>
          </b-col>
        </b-form-row>
        <!-- 招標公告上縣單位代碼 -->
        <b-form-row>
          <i-form-group-check :label="'招標公告上縣單位代碼:'" :item="$v.lnkCode">
            <div class="d-inline">
              <b-form-input v-model="$v.lnkCode.$model" :state="validateState($v.lnkCode)"></b-form-input>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 機關首長 -->
        <b-form-row>
          <i-form-group-check
            class="col-sm-12"
            label-cols-md="2"
            content-cols-md="10"
            :label="'機關首長:'"
            :item="$v.ccnt2"
            :labelStar="true"
          >
            <div class="d-inline">
              <b-form-input class="col-12" v-model="$v.ccnt2.$model" :state="validateState($v.ccnt2)"></b-form-input>
            </div>
            <div class="col-8">
              <div class="d-inline">(請填報決標時簽訂合約之機關首長姓名)</div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 姓名-->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'姓名:'" :item="$v.man">
            <b-form-input v-model="$v.man.$model" :state="validateState($v.man)"></b-form-input>
          </i-form-group-check>
        </b-form-row>
        <!-- 連絡電話-->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'聯絡電話:'" :item="$v.tel">
            <b-form-input v-model="$v.tel.$model" :state="validateState($v.tel)"></b-form-input>
          </i-form-group-check>
        </b-form-row>
        <!-- Email信箱-->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'信箱:'" :item="$v.eMail">
            <b-form-input v-model="$v.eMail.$model" :state="validateState($v.eMail)"></b-form-input>
          </i-form-group-check>
        </b-form-row>
        <!-- 工程概要-->
        <b-form-row>
          <i-form-group-check :label="'工程概要:'" :labelStar="true" :item="$v.contain">
            <b-form-textarea rows="3" max-rows="6" v-model="$v.contain.$model" :state="validateState($v.contain)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 功能效益-->
        <b-form-row>
          <i-form-group-check :label="'功能效益:'" :item="$v.benf">
            <b-form-textarea rows="3" max-rows="4" v-model="$v.benf.$model" :state="validateState($v.benf)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
        <!-- 是否對外公開 -->
        <b-row>
          <i-form-group-check :label="'是否對外公開:'" :item="$v.isOpen">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.isOpen.$model">
                <b-form-radio value="A">是</b-form-radio>
                <b-form-radio value="B">否</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 理由 -->
        <b-form-row>
          <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'理由:'" :item="$v.dOpen">
            <b-form-select v-model="$v.dOpen.$model"></b-form-select>
          </i-form-group-check>
        </b-form-row>
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
  name: 'bidAbc001EditInfo8',
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
      // wkut: '', //執行機關
      name: '', //標案名稱
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
      plnInicode: '', //國發會列管個案計畫名稱
      bos2Code: '', //預算(經費)來源機關
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

    const rules = {
      // wkut: { required: required }, //執行機關
      name: { required: required }, //標案名稱
      isAdd92: { required: required }, //專案項目
      pntCtrl: { required: required }, //列管狀態
      typeCode: { required: required }, //工程類別
      isUnion: {}, //是否統包
      placeCode: { required: required }, //縣市鄉鎮
      place: { required: required }, //施工地點
      depCode: { required: required }, //主辦機關   syscode
      bosCode: { required: required }, //主管機關  syscode
      isBg: {}, //計畫型補助或委託代辦
      plnCode: { required: required }, //計劃主辦機關  syscode
      plnNo: { required: required }, //計畫編號
      plnInicode: { required: required }, //國發會列管個案計畫名稱
      bos2Code: { required: required }, //預算(經費)來源機關
      lnkCode: { required: required }, //招標公告上線單位
      ccnt2: { required: required }, //機關首長
      man: { required: required }, //姓名(聯絡人)
      tel: { required: required }, //連絡電話
      eMail: { required: required }, //Email信箱
      contain: { required: required }, //工程概要
      benf: { required: required }, //功能(工程)效益
      isOpen: {}, //是否對外公開
      dOpen: {}, //理由
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

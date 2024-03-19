<template>
  <div class="container">
    <!-- 基本資料 -->
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          格式一 兩層label
        </h5>
      </div>
      <div class="card-body col-10">
        <b-form-row class="align-items-top">
          <i-tel-num v-model="telNumVal" ref="itelNum"></i-tel-num>
        </b-form-row>
        {{ telNumVal }}
        <button @click="checkTelNum">check tel</button>
        <!-- {{ $v.postAddr }} -->
        <b-form-row class="align-items-top">
          <i-form-group-check class="col-12" label-cols="2" content-cols="10" :labelStar="true" :item="$v.postAddr" :label="'地址測試'">
            <!-- :hasInvalid="true"  -->
            <i-address-picker :postAddrObject="$v.postAddr.$model" @getPostAddrValue="getPostAddrValue"></i-address-picker>
          </i-form-group-check>
        </b-form-row>
        <button @click="checkArea">check Area</button>
        <button @click="resetArea">reset Area</button>
        <b-form-row class="align-items-top">
          <i-form-group-check class="col-12" label-cols="2" content-cols="8" :hasInvalid="true" :label="'電話'">
            <i-phone-num :value="phoneNumVal" @getValue="getPhoneValue" ref="iphoneNum"></i-phone-num>
          </i-form-group-check>
        </b-form-row>
      </div>
    </div>
  </div>
</template>

<script>
import IPhoneNumber from '@/shared/form/i-phone-num.vue';
import ITelNumber from '@/shared/form/i-tel-num.vue';
import { reactive, ref, toRef, computed } from '@vue/composition-api';
import { AdmPostAddr } from '@/shared/model/adm/adm-post-addr.model';
import { useValidation, validateState } from '@/shared/form';
import { requiredIf } from '@/shared/validators';
import axios from 'axios';
export default {
  name: 'viewDemo01',
  components: { 'i-phone-num': IPhoneNumber, 'i-tel-num': ITelNumber },
  setup(props, contexts) {
    const selected = ref('first');
    const options = ref([
      { text: 'Toggle this custom radio', value: 'first' },
      { text: 'Or toggle this other custom radio', value: 'second' },
      { text: 'This is the 4th radio', value: { fourth: 4 } },
    ]);
    let fileData = ref(null);
    function checkFileData() {
      console.log(fileData.value);
    }

    let phoneNumVal = ref('0912-345678');
    const iphoneNum = ref(null);

    function checkphoneNum() {
      console.log(phoneNumVal.value);
      console.log('reset');
      iphoneNum.value.resetValue();
    }
    function getPhoneValue(newValue) {
      phoneNumVal.value = newValue;
      return newValue;
    }

    let telNumVal = ref('0212345678');
    const itelNum = ref(null);

    function checkTelNum() {
      console.log(telNumVal.value);
      console.log('reset');
      itelNum.value.resetValue();
    }
    function getTelValue(newValue) {
      telNumVal.value = newValue;
      return newValue;
    }

    //測試地址用

    const addressPicker = ref(null);

    let originPostAddr = new AdmPostAddr('', '', '', '', '', '');

    let addrObjDefault = {
      postAddr: Object.assign(new AdmPostAddr(), originPostAddr),
    };
    let addrObj = reactive(Object.assign({}, addrObjDefault));

    const rules = {
      postAddr: {
        cityCode: { notrequiredIf: requiredIf(() => addrObj.postAddr.address) },
        areaCode: { requiredIf: requiredIf(() => addrObj.postAddr.cityCode) },
        address: { requiredIf: requiredIf(() => addrObj.postAddr.cityCode) },
      },
    };
    let { $v, checkValidity, reset } = useValidation(rules, addrObj, addrObjDefault, [
      'postAddr.checkPostAddress',
      'postAddr.address',
      'postAddr.areaCode',
      'postAddr.cityCode',
    ]);

    function checkArea() {
      checkValidity().then(res => {
        console.log('checkValidity:', res);
        console.log(addrObj);
      });
    }
    function fetchDataFromBackEndByUserId() {
      let url = '/adm-account' + '/' + '9.99.3-24'; //store.getters.account.id
      axios.get(url).then(({ data }) => {
        console.log('data', data);
        addrObj.postAddr.city = data.homeAddrCity;
        addrObj.postAddr.area = data.homeAddrTown;

        addrObj.postAddr.address = data.homeAddrOther;

        originPostAddr.city = data.homeAddrCity;
        originPostAddr.area = data.homeAddrTown;
        originPostAddr.address = data.homeAddrOther;
      });
    }
    fetchDataFromBackEndByUserId();
    function getPostAddrValue(value) {
      Object.assign(addrObj.postAddr, value);
    }

    function resetArea() {
      reset();
      Object.assign(addrObj.postAddr, originPostAddr);
    }

    return {
      $v,
      addressPicker,
      addrObj,
      iphoneNum,
      itelNum,
      phoneNumVal,
      selected,
      options,
      fileData,
      checkFileData,
      checkphoneNum,
      getPhoneValue,
      telNumVal,
      checkTelNum,
      getTelValue,
      checkArea,
      getPostAddrValue,
      resetArea,
    };
  },
};
</script>

<style></style>

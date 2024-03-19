<template>
  <div :class="isValidRef ? 'form-check-with-marginBottom' : 'form-check-no-marginBottom'" class="w-100">
    <b-form-row>
      <b-col cols="1">
        <b-form-input v-model="addressForm.zip" :disabled="true"> </b-form-input>
      </b-col>
      <b-col cols="2">
        <b-form-select :options="cityOpt" v-model="$v.cityCode.$model" :state="validateState($v.city)" @change="resetZipAndAreaAndRoad()">
        </b-form-select>
        <b-form-invalid-feedback v-for="(error, index) of $v.city.$errors" :key="'city-' + index">
          {{ error.$message }}
        </b-form-invalid-feedback>
      </b-col>
      <b-col cols="2">
        <b-form-select :options="areaOpt" v-model="$v.areaCode.$model" :state="validateState($v.area)" @input="fetchCertainCityAndArea()">
        </b-form-select>
        <b-form-invalid-feedback v-for="(error, index) of $v.area.$errors" :key="'area-' + index">
          {{ error.$message }}
        </b-form-invalid-feedback>
      </b-col>
      <b-col cols="7">
        <b-form-input v-model="$v.address.$model" :state="validateState($v.address)"> </b-form-input>
        <b-form-invalid-feedback v-for="(error, index) of $v.address.$errors" :key="'address-' + index">
          {{ error.$message }}
        </b-form-invalid-feedback>
      </b-col>
    </b-form-row>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { AdmPostAddr } from '@/shared/model/adm/adm-post-addr.model';
import { whenConditionNotNull } from '@/shared/validators';
import { computed, defineComponent, onMounted, reactive, ref, toRef, watch } from '@vue/composition-api';
import axios from 'axios';
import { BCol, BFormInput, BFormInvalidFeedback, BFormRow, BFormSelect } from 'bootstrap-vue';

export default defineComponent({
  name: 'IAddressPicker',
  components: { BCol, BFormInput, BFormInvalidFeedback, BFormRow, BFormSelect },
  props: {
    postAddrObject: {
      type: Object,
      required: false,
      default() {
        return new AdmPostAddr();
      },
    },
  },
  setup(props, context) {
    const postAddrObjectProp = toRef(props, 'postAddrObject');

    //要回傳的 object
    let returnPostAddrObject = new AdmPostAddr();
    //選定的選項
    let selectAddrOption = [];
    //判斷可不可以回傳
    let returnFlag = false;

    // 建立表單物件預設值
    let formDefault = {
      zip: props.postAddrObject.zip,
      city: props.postAddrObject.city,
      cityCode: props.postAddrObject.cityCode,
      area: props.postAddrObject.area,
      areaCode: props.postAddrObject.areaCode,
      address: props.postAddrObject.address,
    };
    // 建立一份新的表單物件
    let addressForm = reactive(Object.assign({}, formDefault));

    const rules = {
      zip: {},
      city: {},
      cityCode: {
        notNull: whenConditionNotNull(
          computed(() => addressForm.cityCode),
          undefined
        ),
      },
      area: {},
      areaCode: {
        notNull: whenConditionNotNull(
          computed(() => addressForm.cityCode),
          undefined
        ),
      },
      address: {
        notNull: whenConditionNotNull(
          computed(() => addressForm.cityCode),
          undefined
        ),
      },
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, addressForm, formDefault);
    const allAddress = [];

    onMounted(() => {
      entry();
    });

    function entry() {
      if (allAddress.length === 0) {
        getAllAddressPromise().then(data => {
          allAddress.splice(0, allAddress.length, ...data);
        });
      }
    }
    //取得所有address資料的promise
    function getAllAddressPromise(): Promise<any> {
      return new Promise<any>((resolve, reject) => {
        axios
          .get(`/service/adm-post-addrs`)
          .then(res => {
            resolve(res.data);
          })
          .catch(err => {
            reject(err);
          });
      });
    }
    function resetZipAndAreaAndRoad() {
      addressForm.zip = '';
      addressForm.areaCode = '';
      addressForm.area = '';
      addressForm.address = '';
      returnFlag = true;
    }

    const cityOpt = computed(() => {
      const addr = ref(allAddress);
      const emptyOpt = {};
      emptyOpt['value'] = '';
      emptyOpt['text'] = '請選擇';
      const city = [];
      const cityOptions = [];
      const tempCityOptions = addr.value.map(source => {
        const option = {};
        option['value'] = source['cityCode'];
        option['text'] = source['city'];
        if (!city.includes(option['text'])) {
          city.push(source['city']);
          return option;
        }
      });
      tempCityOptions.unshift(emptyOpt);
      tempCityOptions.forEach(item => {
        if (item) {
          cityOptions.push(item);
        }
      });
      return cityOptions;
    });

    const areaOpt = computed(() => {
      const addr = ref(allAddress);
      const emptyOpt = {};
      emptyOpt['value'] = '';
      emptyOpt['text'] = '請選擇';
      const areaOptions = addr.value
        .filter(option => option['cityCode'] === addressForm.cityCode)
        .map(source => {
          const option = {};
          option['value'] = source['areaCode'];
          option['text'] = source['area'];
          return option;
        });
      areaOptions.unshift(emptyOpt);
      return areaOptions;
    });
    const isValidRef = ref(true);

    function fetchCertainCityAndAreaCore() {
      //如果傳進來的縣市區 兩個都有而且是代號
      if (addressForm.areaCode && addressForm.cityCode && addressForm.areaCode.length > 0 && addressForm.cityCode.length > 0) {
        selectAddrOption = allAddress
          .filter(option => option['cityCode'] === addressForm.cityCode)
          .filter(option => option['areaCode'] === addressForm.areaCode)
          .map(source => {
            return source;
          });
        console.log('with code', selectAddrOption);
      }
      //如果傳進來的縣市區 兩個都有而且是中文
      else if (addressForm.area && addressForm.city && addressForm.area.length > 0 && addressForm.city.length > 0) {
        selectAddrOption = allAddress
          .filter(option => option['area'] === addressForm.area)
          .filter(option => option['city'] === addressForm.city)
          .map(source => {
            return source;
          });
        console.log('no code', selectAddrOption);
      }
      if (selectAddrOption.length === 1) {
        //把找出來的選項的值assign 給要回傳的物件
        Object.keys(selectAddrOption[0]).forEach(postAddrKey => {
          returnPostAddrObject[postAddrKey] = selectAddrOption[0][postAddrKey];
          addressForm[postAddrKey] = selectAddrOption[0][postAddrKey];
        });
      }
    }

    //判斷 同步不同步
    function fetchCertainCityAndArea() {
      if (allAddress.length === 0) {
        getAllAddressPromise().then(data => {
          allAddress.splice(0, allAddress.length, ...data);
          fetchCertainCityAndAreaCore();
        });
      } else {
        fetchCertainCityAndAreaCore();
      }
    }

    //判斷回傳
    watch(addressForm, () => {
      console.log('回傳 returnPostAddrObject', returnPostAddrObject);
      checkValidity();

      returnPostAddrObject.address = addressForm.address;
      if (returnFlag) {
        let onlyCityAddr = new AdmPostAddr('', addressForm.city, addressForm.cityCode);
        console.log(onlyCityAddr);
        context.emit('getPostAddrValue', onlyCityAddr);
        returnFlag = false;
      } else {
        context.emit('getPostAddrValue', returnPostAddrObject);
      }
    });

    //判斷留白
    watch($v, () => {
      // console.log('$v error', $v);
      if ($v.value.$errors.length > 0) {
        // console.log('false');
        isValidRef.value = false;
      } else {
        // console.log('true');
        isValidRef.value = true;
      }
    });

    //判斷傳下來的值
    watch(
      postAddrObjectProp.value,
      newValue => {
        // console.log('newValue', newValue);
        console.log('判斷傳下來的值 newValue', newValue);
        Object.assign(addressForm, newValue);
        fetchCertainCityAndArea();
        console.log('判斷傳下來的值 addressForm', addressForm);
      },
      { immediate: true }
    );

    return {
      $v,
      addressForm,
      cityOpt,
      areaOpt,
      isValidRef,
      validateState,
      resetZipAndAreaAndRoad,
      fetchCertainCityAndArea,
      reset,
      checkValidity,
    };
  },
});
</script>
<style scoped>
.form-check-no-marginBottom {
  margin-bottom: 0px !important;
}
.form-check-with-marginBottom {
  margin-bottom: 24px !important;
}
</style>

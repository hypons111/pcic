<template>
  <div :class="isValidRef ? 'form-check-with-marginBottom' : 'form-check-no-marginBottom'">
    <b-form-row>
      <!-- <b-col cols="1">
        <b-form-input v-model="$v.zip.$model" :disabled="true"> </b-form-input>
      </b-col> -->
      <b-col cols="6">
        <b-form-select :options="cityOpt" v-model="$v.city.$model" :state="validateState($v.city)" @change="resetZipAndAreaAndRoad()">
        </b-form-select>
      </b-col>
      <b-col cols="6">
        <b-form-select id="townSelect" :options="areaOpt" v-model="$v.area.$model" :state="validateState($v.area)"> </b-form-select>
      </b-col>
      <!-- <b-col cols="7">
        <b-form-input v-model="$v.road.$model" :state="validateState($v.road)"> </b-form-input>
        <b-form-invalid-feedback v-for="(error, index) of $v.road.$errors" :key="'road-' + index">
          {{ error.$message }}
        </b-form-invalid-feedback>
      </b-col> -->
    </b-form-row>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, onMounted, reactive, ref, toRefs, watch } from '@vue/composition-api';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { required, whenConditionNotNull } from '@/shared/validators';
import { useValidation, validateState } from '@/shared/form';

export default defineComponent({
  name: 'IAddressPicker',
  props: {
    isReset: {
      type: Boolean,
      required: true,
    },
    addrZip: {
      type: String,
      required: false,
      default: '',
    },
    addrCity: {
      type: String,
      required: false,
      default: '',
    },
    addrArea: {
      type: String,
      required: false,
      default: '',
    },
    addrRoad: {
      type: String,
      required: false,
      default: '',
    },
  },
  setup(props, context) {
    const notificationService = useNotification();

    // 建立表單物件預設值
    let formDefault = {
      zip: props.addrZip,
      city: props.addrCity,
      area: props.addrArea,
      road: props.addrRoad,
    };
    // 建立一份新的表單物件
    let ad = reactive(Object.assign({}, formDefault));

    const rules = {
      zip: {},
      city: {},
      area: {
        notNull: whenConditionNotNull(
          computed(() => ad.city),
          undefined
        ),
      },
      road: {
        notNull: whenConditionNotNull(
          computed(() => ad.city),
          undefined
        ),
      },
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, ad, formDefault);
    const allAddress = [];

    onMounted(() => {
      entry();
    });

    function entry() {
      getAllAddress();
    }

    function getAllAddress() {
      axios
        .get(`/service/adm-post-addrs`)
        .then(({ data }) => {
          allAddress.splice(0, allAddress.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function resetZipAndAreaAndRoad() {
      ad.zip = '';
      ad.area = '';
      ad.road = '';
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
        option['value'] = source['city'];
        option['text'] = source['city'];
        if (!city.includes(option['value'])) {
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
        .filter(option => option['city'] === ad.city)
        .map(source => {
          const option = {};
          option['value'] = source['area'];
          option['text'] = source['area'];
          return option;
        });
      areaOptions.unshift(emptyOpt);
      return areaOptions;
    });
    const isValidRef = ref(true);

    watch(ad, () => {
      ad.zip = allAddress
        .filter(option => option['area'] === ad.area)
        .filter(option => option['city'] === ad.city)
        .map(source => {
          return source['zip'];
        })
        .toString();

      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          isValidRef.value = true;
          //【父元件寫法】
          //
          // template內：
          // <i-address-picker
          //     :isReset="isReset"
          //     @changeIsReset="changeIsReset"
          //     :addrZip="formDefault.homeAddrZip"
          //     :addrCity="formDefault.homeAddrCity"
          //     :addrArea="formDefault.homeAddrTown"
          //     :addrRoad="formDefault.homeAddrOther"
          //     @getZip="getHomeAddrZip"
          //     @getCity="getHomeAddrCity"
          //     @getArea="getHomeAddrTown"
          //     @getRoad="getHomeAddrOther"
          // ></i-address-picker>
          //
          // script裡：                         ==> 接收來自地址元件的事件，把欄位值改為地址元件傳來的值
          //     function getHomeAddrZip(param) {
          //        form.homeAddrZip = param;
          //     }
          //     function getHomeAddrCity(param) {
          //        form.homeAddrCity = param;
          //     }
          //     function getHomeAddrTown(param) {
          //        form.homeAddrTown = param;
          //     }
          //     function getHomeAddrOther(param) {
          //        form.homeAddrOther = param;
          //     }
          //
          //    const isReset = ref(false); ==> 預設為false，若父元件要reset欄位，需要改成true並傳遞給地址元件
          //
          //    function changeIsReset(param) {  ==> 清除之後，地址元件將傳遞給父元件的事件，讓父元件將isReset改為false的方法
          //      isReset.value = param;
          //    }
          //

          //分別取得個欄位值
          context.emit('getZip', ad.zip); //e.g 234
          context.emit('getCity', ad.city); //e.g 新北市
          context.emit('getArea', ad.area); //e.g 永和區
          context.emit('getRoad', ad.road); //e.g 仁愛路100號2樓
        } else {
          isValidRef.value = false;
          context.emit('getZip', null);
          context.emit('getCity', null);
          context.emit('getArea', null);
          context.emit('getRoad', null);
        }
      });
      // console.log('ZIP' + ad.zip);
      // console.log('CITY' + ad.city);
      // console.log('AREA' + ad.area);
      // console.log('ROAD' + ad.road);
    });

    const propsIsReset = toRefs(props).isReset;

    watch(propsIsReset, () => {
      if (propsIsReset.value) {
        ad.zip = props.addrZip;
        ad.city = props.addrCity;
        ad.area = props.addrArea;
        ad.road = props.addrRoad;
        context.emit('changeIsReset', false);
      }
    });

    return {
      $v,
      ad,
      cityOpt,
      areaOpt,
      isValidRef,
      validateState,
      resetZipAndAreaAndRoad,
      reset,
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

<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header py-2">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            常用功能維護
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <b-col offset="2" cols="8">
              <dual-list-box
                :source="usualFunctions.source"
                :destination="usualFunctions.destination"
                label="name"
                @onChangeList="onChangeUsualFunction"
              ></dual-list-box>
            </b-col>
          </b-form-row>
        </div>
        <div class="text-center mb-3">
          <i-button type="save" @click="save"></i-button>
          <i-button type="arrow-bar-left" @click=""></i-button>
          <i-button type="x-circle" @click=""></i-button>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { reactive, ref, watch, toRefs, onMounted } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form/';
import { required, email, maxLength, nationalId, mobileTel, lineTel, address } from '@/shared/validators';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import axios from 'axios';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';

export default {
  name: 'pwb0205',

  setup(props) {
    onMounted(() => {
      getUsualFunctionAllRoles();
      getUsualFunctionDetailRoles();
    });

    // 宣告表單物件及初始值，是前後端 接收或傳遞 的資料物件
    const formDefault = {
      userId: null, //帳號
      usualFunctions: [], //功能list
      createUser: '',
      createTime: null,
      loginUserId: null, //登入者的USER_ID
    };
    // 建立一份新的表單物件
    let form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      userId: {},
      usualFunctions: {},
      updateUser: {},
      updateTime: {},
      loginUserId: {},
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService = useNotification();

    // 取得登入帳號(ADM_ACCOUNT.USER_ID)
    const loginUserId = ref(useGetters(['account']).account.value.id);

    //權限
    let usualFunctions = reactive({
      source: [],
      destination: [],
    });

    const allUsualFunctionsProp = ref([]);
    const usualFunctionsProp = ref([]);

    // 建立UsualFunctionSource(左邊)
    function getUsualFunctionAllRoles() {
      //查詢全部功能選項
      let admUrl = '/usualfunction-functionName-default/' + loginUserId.value;
      axios.get(admUrl).then(({ data }) => {
        if (data != null) {
          allUsualFunctionsProp.value.splice(0, allUsualFunctionsProp.value.length);
          data.forEach(item => {
            let queryOption = { code: '', name: '' };
            queryOption.code = item.functionId;
            queryOption.name = item.functionName;
            allUsualFunctionsProp.value.push(queryOption);
          });
        }
      });
    }

    // 建立UsualFunctionSource(右邊)
    function getUsualFunctionDetailRoles() {
      //查詢勾選功能選項
      let admUrl = '/usualfunction-functionName/' + loginUserId.value;
      axios.get(admUrl).then(({ data }) => {
        if (data != null) {
          usualFunctionsProp.value.splice(0, usualFunctionsProp.value.length);
          data.forEach(item => {
            let queryOption = { code: '', name: '' };
            queryOption.code = item.functionId;
            queryOption.name = item.functionName;
            usualFunctionsProp.value.push(queryOption);
          });
        }
      });
    }

    //常用功能(未選)
    watch(allUsualFunctionsProp, () => {
      //重組source陣列移除usualFunctionsProp
      allUsualFunctionsProp.value.forEach(i => {
        usualFunctionsProp.value.forEach(j => {
          if (i.code == j.code) {
            let index = allUsualFunctionsProp.value.indexOf(i);
            if (index > -1) {
              allUsualFunctionsProp.value.splice(index, 1);
            }
          }
        });
      });
      const source = allUsualFunctionsProp.value.filter(option => isInLeft(option));
      usualFunctions.source.splice(0, usualFunctions.source.length, ...source);
    });

    //常用功能(已選)
    watch(usualFunctionsProp, () => {
      const destination = usualFunctionsProp.value.filter(option => isInLeft(option));
      usualFunctions.destination.splice(0, usualFunctions.destination.length, ...destination);
    });

    //onChange
    function onChangeUsualFunction({ source, destination }) {
      //重組source陣列
      usualFunctions.source.splice(0, usualFunctions.source.length, ...source);
      usualFunctions.destination.splice(0, usualFunctions.destination.length, ...destination);
    }

    //權限及模組
    function isInLeft(option) {
      if (usualFunctionsProp.value.length) {
        return usualFunctionsProp.value.filter(c => c === option.code).length <= 0;
      }
      return true;
    }

    //把右邊選擇的權限放到form.usualFunctions
    function dataChang() {
      form.usualFunctions = [];
      usualFunctions.destination.forEach(item => {
        let selectOption = {
          code: '',
          text: '',
        };
        selectOption.code = item.code;
        selectOption.text = item.name;
        form.usualFunctions.push(selectOption);
      });
    }

    //儲存
    function save() {
      //判斷usualFunctions長度
      dataChang();
      form.userId = loginUserId.value;
      form.createUser = loginUserId.value;
      form.createTime = new Date();
      axios
        .put('/add-usualfunctions', form)
        .then(() => {})
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    return {
      formDefault,
      form,
      rules,
      $v,
      onChangeUsualFunction,
      usualFunctions,
      save,
    };
  },
};
</script>

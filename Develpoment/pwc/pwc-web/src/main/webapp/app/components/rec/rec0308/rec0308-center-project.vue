<template>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2">
      <div class="card">
        <div class="card-body col-10">
          <table border="2" cellpadding="2" cellspaceing="2" >
            <tr>
              <th width="5%" class="text-center">優先順序</th>
              <th width="5%" class="text-center">工程代碼</th>
              <th width="50%" class="text-center">復建工程名稱</th>
              <th width="15%" class="text-center">鄉(鎮市)</th>
              <th width="15%" class="text-center">村(里)</th>
              <th width="10%" class="text-center">復建經費</th>
            </tr>
            <tr v-for="(item,key) in table.data">
              <td style="vertical-align : middle;" >{{item.applySubmitNo}}</td>
              <td style="vertical-align : middle;">{{item.projectCode}}</td>
              <td style="vertical-align : middle;">{{item.projectName}}</td>
              <td style="vertical-align : middle;">{{item.city}}</td>
              <td style="vertical-align : middle;">{{item.village}}</td>
              <td style="vertical-align : middle;" class="text-right">{{item.applyAmount}}</td>
            </tr>
          </table>
        </div>
      </div>
    </section>
    <!-- 新增按鈕 -->
    <section>
      <div class="container mt-1 mb-1">
        <b-button variant="info" @click="toCenter">提報</b-button>
        <b-button variant="info" @click="backToCase">案件退回</b-button>
        <!-- <i-button class="ml-1" type="clipboard-minus" @click="backToCase"></i-button> -->
        <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
        <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
      </div>
    </section>
    <!-- i-table -->
    <section>
      
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, ref, watch, onMounted, toRefs } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import { required } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { handleBack } from '@/router/router';
import iButton from '@/shared/buttons/i-button.vue';

export default {
  components: { iButton },
  name: 'rec0308-center-project',
  props: {
    recTyphoonNo: {
      type: String,
      required: false,
    },
  },
  setup(props) {
    const recTyphoonNoProp = toRefs(props).recTyphoonNo;

    onMounted(() => {
      form.typhoonNo = recTyphoonNoProp.value;
      handleQuery();
    });

    const formDefault = {
      typhoonNo: '',
      projectCodeSelect: '',
      projectCode: '',
      city: '',
      village: '',
      applyAmount: 0,
      projectName: '',
      projectStatus: '',
    };

    const rules = {
      typhoonNo: {},
      projectCodeSelect: {},
      projectCode: {},
      city: {},
      applyAmount: {},
      projectName: {},
      projectStatus: {},
    };


    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const allTyphoon = ref([]);
    const getTyphoon = () => {
      axios
        .get('/rec-projects/get-project-typhoon')
        .then(({ data }) => {
          allTyphoon.value.splice(0, allTyphoon.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    //是否要重新查詢
    const isReload: boolean = ref(null);
    
    //返回查詢畫面
    const toQueryView = () => {
      const param = { isReload: isReload.value};
      handleBack(param);
    };

    //toCenter
    const toCenter= () => {
     
    };


    //backToCase
    const backToCase= () => {
     
    };

    const table = reactive({
      data: undefined,
    });

    //依條件查詢復建工程
    const handleQuery = () => {
          //重置條件
          table.data = undefined;
          axios
            .post('/rec-projects/criteria-jpa', form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
            })
            .finally()
            .catch(notificationErrorHandler(notificationService));
    };

    watch(recTyphoonNoProp,() => {
      form.typhoonNo = recTyphoonNoProp.value;
      handleQuery();
      },
      { immediate: true },
    );
   
    return {
      $v,
      validateState,
      checkValidity,
      handleQuery,
      reset,
      table,
      toQueryView,
      toCenter,
      backToCase,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

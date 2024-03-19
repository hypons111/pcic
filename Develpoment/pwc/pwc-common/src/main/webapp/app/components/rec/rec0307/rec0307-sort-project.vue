<template>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2">
      <div class="card">
        <div class="card-body col-10">
          <div>
            <b-form-row>
              <i-form-group-check label="工程類別:">
                <b-form-select v-model="$v.projectCodeSelect.$model" :options="queryOptions.projectCode"></b-form-select>
              </i-form-group-check>
            </b-form-row>
          </div>
          <table border="2" cellpadding="2" cellspaceing="2" >
            <tr>
              <th width="5%" class="text-center">優先順序</th>
              <th width="5%" class="text-center">工程代碼</th>
              <th width="50%" class="text-center">復建工程名稱</th>
              <th width="15%" class="text-center">鄉(鎮市)</th>
              <th width="15%" class="text-center">村(里)</th>
              <th width="10%" class="text-center">復建經費</th>
            </tr>
            <tr v-for="(item,key) in showTable">
              <td style="vertical-align : middle;" v-if="isMyself">
              <input type="text" size="1" v-model="item.applySubmitNo" @blur="tempSave(item)"/></td>
              <td style="vertical-align : middle;" v-else>{{key+1}}</td>
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
        <b-button variant="info" @click="toSortByKey">依照目前排序產生序號</b-button>
        <b-button variant="info" @click="toSortByMyself">自訂排序</b-button>
        <b-button variant="info" :disabled="!isMyself" @click="toSortView">確認</b-button>
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
  name: 'rec0307-sort-project',
  props: {
    recTyphoonNo: {
      type: String,
      required: false,
    },
  },
  setup(props) {
    const recTyphoonNoProp = toRefs(props).recTyphoonNo;

    onActivated(() => {
      form.typhoonNo = recTyphoonNoProp.value;
      handleQuery();
      getProjectCode();
    });

    onMounted(() => {
      form.typhoonNo = recTyphoonNoProp.value;
      handleQuery();
      getProjectCode();
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

    // 下拉選單選項
    const queryOptions = reactive({
      projectCode: [
        { value: '', text: '全部' },
        { value: 'A1', text: 'A1-水利工程' },
        { value: 'B1', text: 'B1-觀光工程' },
        { value: 'C1', text: 'C1-道路橋梁工程-公路系統工程' },
        { value: 'C2', text: 'C2-道路橋梁工程-村里聯絡道路工程' },
      ],
    });

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const showTable = ref([]);
    const showTable2 = ref([]);

    const allTyphoon = ref([]);
    const getTyphoon = () => {
      axios
        .get('/rec-projects/get-project-typhoon')
        .then(({ data }) => {
          allTyphoon.value.splice(0, allTyphoon.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const allProjectCode = ref([]);
    const getProjectCode = () => {
      let url: string;
          url = `/rec-projects/get-project-code/${form.typhoonNo}`;
      axios
        .get(url)
        .then(({ data }) => {
          allProjectCode.value.splice(0, allProjectCode.value.length, ...data);
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

    //SortByKey
    const toSortByKey= () => {
      isMyself.value = false;
      for (let i=0;i< showTable.value.length;i++ ){
        showTable.value[i].applySubmitNo = i+1;
      }
    };

    const isMyself = ref(false);

    //SortByMyself
    const toSortByMyself= () => {
      isMyself.value = true;
      //showTable.value.sort((a,b)=>a.applySubmitNo - b.applySubmitNo);
    };

    //暫存順序
    const tempSave= ((rowData) => {
      var newIndex = showTable.value.findIndex((item) => {
        return rowData.projectNo === item.projectNo;
      });
      showTable.value[newIndex].applySubmitNo = rowData.applySubmitNo;
      
      //顯示升冪排序結果
      //showTable.value.sort((a,b) => a.applySubmitNo - b.applySubmitNo);
    });

    const toSortView = (() => {
      //顯示升冪排序結果
      showTable.value.sort((a,b) => a.applySubmitNo - b.applySubmitNo);
    });

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

    watch(allProjectCode, () => {
      queryOptions.projectCode = allProjectCode.value.map(data => {
        return { value: data['projectCode'], text: data['projectCode']+'：'+data['projectCodeName'] };
      });
      queryOptions.projectCode.unshift({ text: '請選擇', value: '' }); //添加一個或多個元素至陣列的開頭
    });

     watch(recTyphoonNoProp,() => {
        //清除 showTable 所有內容
      form.typhoonNo = recTyphoonNoProp.value;
      handleQuery();
      getProjectCode();
      },
      { immediate: true },
    );

    //工程類別改變，變更 showTable 內容
    watch(form, () => {
      //清除 showTable 所有內容
      showTable.value.splice(0,showTable.value.length)
      for (let i=0;i< table.data.length;i++ ){
        if (table.data[i].projectCode===form.projectCodeSelect){
          showTable.value.push(table.data[i]);
        }
      }
    });

   
    return {
      $v,
      validateState,
      checkValidity,
      handleQuery,
      reset,
      queryOptions,
      table,
      toQueryView,
      showTable,
      isMyself,
      tempSave,
      toSortByKey,
      toSortByMyself,
      toSortView,
      form
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

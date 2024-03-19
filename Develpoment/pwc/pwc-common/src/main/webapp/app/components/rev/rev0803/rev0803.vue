<template>
  <div>
    <!--查詢條件區-->
    <section class="container">
      
        <div class="card-body col-10">
          <b-table
              title="參數設定"
              :itemsUndefinedBehavior="'loading'"
              :items="table.data"
              :fields="table.fields"
          >
          <template #cell(checkUnit)=" row ">
            <b-form-input v-if="row.item.checkUnit==='0'" v-model="$v.data1.$model" value="7"></b-form-input>
            <b-form-input v-if="row.item.checkUnit==='1'" v-model="$v.data2.$model" value="True"></b-form-input>
            <b-form-input v-if="row.item.checkUnit==='2'" v-model="$v.data3.$model" value="30"></b-form-input>
            <b-form-input v-if="row.item.checkUnit==='3'" v-model="$v.data4.$model" value="True"></b-form-input>
            <b-form-input v-if="row.item.checkUnit==='4'" v-model="$v.data5.$model" value="5"></b-form-input>
            <b-form-input v-if="row.item.checkUnit==='5'" v-model="$v.data6.$model" value="1"></b-form-input>
              </template>
          </b-table>
          

        </div>
      
    </section>
    
    <section>
      <div class="container mt-1 mb-1" align="center">
        <!-- 檢視按鈕 -->
        <i-button type="check-circle" ></i-button>
        <i-button type="arrow-counterclockwise" @click="reset"></i-button>
        </div>
    </section> 
  </div>
</template>

<script lang="ts">
import { computed, onActivated, reactive,Ref,toRef, ref } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';
import { RevMain } from '@/shared/model/revModel/rev-main.model';
import { required } from '@/shared/validators';
import i18n from '@/shared/i18n';
import { useValidation } from '@/shared/form';

export default {
  name: 'rev0803',

  
  //props代表傳進來的參數，context代表整頁的容器物件
  setup(props, context) {
    onActivated(() => {
      reset();
    });

    const formDefault = {
      data1: '',
      data2: '',
      data3: '',
      data4: '',
      data5: '',
      data6: '',
    };

    const form = reactive(Object.assign({}, formDefault));

    const rules ={
      data1: {},
      data2: {},
      data3: {},
      data4: {},
      data5: {},
      data6: {},
    };

    // function judgeInputDisable(currentRadioValue: string, radioValue: string): boolean {
    //   return (radioValue === currentRadioValue);
    // };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    const table = reactive({
      fields: [
        {
          key: 'checkDate',
          label: '參數',
          sortable: false,
          thStyle: 'width:60%',
          thClass: 'text-leftr',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'method',
          label: '值',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-left',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'checkUnit',
          label: '修改',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: '型別',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-left',
          tdClass: 'text-left align-middle',
        },
      ],
      data: [
        {
          checkDate: '案件逾期-逾期通知天數',
          method: '7',
          checkUnit: '0',
          action: '數值'
        },
        {
          checkDate: '案件逾期-寄發逾期通知',
          method: 'True',
          checkUnit: '1',
          action: '布林'
        },
        {
          checkDate: '案件逾期-審查逾期天數',
          method: '30',
          checkUnit: '2',
          action: '數值'
        },
        {
          checkDate: '案件逾期-寄發未結案通知',
          method: 'True',
          checkUnit: '3',
          action: '布林'
        },
        {
          checkDate: '案件逾期-未結案通知寄發星期',
          method: '5',
          checkUnit: '4',
          action: '數值'
        },
        {
          checkDate: '案件逾期-設定頁預設分析年長',
          method: '1',
          checkUnit: '5',
          action: '數值'
        },
      ],
    });

    const formDefault = {
      data1: '7',
      data2: 'True',
      data3: '30',
      data4: 'True',
      data5: '5',
      data6: '1',
    };

    const form = reactive(Object.assign({}, formDefault));

    const rules ={
      data1: {},
      data2: {},
      data3: {},
      data4: {},
      data5: {},
      data6: {},
    };

    // function judgeInputDisable(currentRadioValue: string, radioValue: string): boolean {
    //   return (radioValue === currentRadioValue);
    // };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //建立表單物件ref
    //reactive()內傳入參數可監聽該物件達到響應式
    //Object.assign(,)為指派第2個參數對象到第1個參數
    //$v為表單物件轉換而來，checkValidity是用來做欄位檢核



    

    

    //變數要做return，<template>才可以使用
    return {
        table,
        formDefault,
        form,
        rules,
        $v,
        checkValidity,
        reset,

 
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

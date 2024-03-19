<template>
   <div>
    <div class="card">
        <div class="card-header">
            <h5 class="m-0">列印條件</h5>
        </div>
    <b-form-row>
        <i-form-group-check :label="'機關： '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
           <b-form-select :options="formOptions.company" class="col-4"></b-form-select>  
        </i-form-group-check>
      </b-form-row>

    <b-form-row>
        <i-form-group-check :label="'列印層級： '" :labelStar="false">
          <b-form-radio-group :options="formOptions.duty"> </b-form-radio-group>
        </i-form-group-check>
    </b-form-row>

    <b-form-row>
        <i-form-group-check :label="'標案金額自： '" :labelStar="false">
          <b-form-radio-group :options="formOptions.status"> </b-form-radio-group>
        </i-form-group-check>
    </b-form-row>

    <b-form-row>
        <i-form-group-check :label="'標案金額至： '" :labelStar="false">
          <b-form-radio-group :options="formOptions.add"> </b-form-radio-group>
        </i-form-group-check>
    </b-form-row>

    <b-form-row>
        <i-form-group-check :label="'執行狀況： '" :labelStar="false">
          <b-form-radio-group :options="formOptions.new"> </b-form-radio-group>
        </i-form-group-check>
    </b-form-row>
    <b-col style="text-align: center">
      <b-button style="background-color: #1aa4b7">送出</b-button>
      <b-button style="background-color: #1aa4b7">清除</b-button>
    </b-col>   





    </div>



   </div>


</template>



















<script lang="ts">
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { reactive, Ref, toRef, watch } from '@vue/composition-api';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'bidAac001Edit',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
    formStatus: {
      type: Object,
      required: true,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const formStatusProp: Ref<any> = toRef(props, 'formStatus');
    const bidProjectService = new BidProjectService();

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        // console.log('fetchBidProjectInfo res ', result);
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      wkut: '',
      prjno: '',
      name: '',
      major: '',
      id: '',
      class: '',
      class2: '',
      startDate: '',
      endDate: '',
      duty: '',
      createDate: '',
    };

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      name: {},
      major: {},
      id: {},
      class: {},
      class2: {},
      startDate: {},
      endDate: {},
      duty: {},
      createDate: {},
    };

    const formOptions = reactive({
      duty: [
        { value: '1', text: '本機關' },
        { value: '2', text: '含所屬機關' },
      ],
      company: [
        { value: '1', text: '行政院' },
      ],
      status: [
        { value: '1', text: '0萬起' },
        { value: '2', text: '100萬起' },
        { value: '3', text: '500萬起' },
        { value: '4', text: '1000萬起' },
        { value: '5', text: '2000萬起' },
        { value: '6', text: '5000萬起' },
        { value: '7', text: '2億起' },
      ],
      add: [
        { value: '1', text: '100萬止' },
        { value: '2', text: '500萬止' },
        { value: '3', text: '1000萬止' },
        { value: '4', text: '2000萬止' },
        { value: '5', text: '5000萬止' },
        { value: '6', text: '2億止' },
        { value: '7', text: '9000億止' },
      ],
      new: [
        { value: '1', text: '尚未驗收及本年度內驗收標案' },
        { value: '2', text: '尚未驗收標案' },
        { value: '3', text: '包含以前年度已驗收標案' },
      ],
    });

    const handleQuery = () => {};
    const submitForm = () => {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAac001Info', { bidProjectKey });
    };
    const toCheck = () => {};
    const toCancel = () => {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAac001Info', { bidProjectKey });
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          if (formStatusProp.value === 'modify') {
            handleQuery();
          }
        }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      $v,
      reset,
      bidProjectKeyProp,
      formStatusProp,
      formOptions,
      form,
      handleQuery,
      toCheck,
      submitForm,
      toCancel,
    };
  },
};
</script>

<style scoped></style>
<template>
<div>
  <div class="card">
        <div class="card-header">
            <h5 class="m-0">主管機關處理紀錄</h5>
        </div>
        <b-form-row>
        <i-form-group-check :label="'主管機關處理日期  '" :label-cols="2" :content-cols="10" class="col-12">111年03月15日</i-form-group-check>
      </b-form-row>

       <b-form-row>
        <i-form-group-check :label="'主管機關處理說明 '" :label-cols="4" :content-cols="8" class="col-6">XXX</i-form-group-check>
        <i-form-group-check :label="'確認預估可付款日期   '" :label-cols="4" :content-cols="8" class="col-6">(未填報)</i-form-group-check>
      </b-form-row>

       <b-form-row>
        <i-form-group-check :label="'結案日期  '" :label-cols="3" :content-cols="8" class="col-6">(未填報)</i-form-group-check>
        <i-form-group-check :label="'結案原因 '" :label-cols="4" :content-cols="8" class="col-6">(未填報)</i-form-group-check>
      </b-form-row>

      
    </div>
        <div>
        <b-col style="text-align: center">
          <b-button>返回</b-button>
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
        { value: '1', text: '是' },
        { value: '2', text: '否' },
      ],
      company: [
        { value: '1', text: '請選擇' },
        { value: '2', text: '1:港洲營造公司' },
        { value: '3', text: '2:有力機電工程股份有限公司' },
        { value: '4', text: '3:致邦空調工程有限公司' },
      ],
      status: [
        { value: '1', text: '尚在辦理請款作業' },
        { value: '2', text: '上級機關辦理補助款核撥中' },
        { value: '3', text: '上級機關未核撥補助款' },
        { value: '4', text: '洽辦機關未核撥補助款' },
        { value: '5', text: '預算暫被挪為他用' },
        { value: '6', text: '無預算或預算不足' },
        { value: '7', text: '廠商文件未備齊或錯誤需修正' },
        { value: '8', text: '尚有履約爭議事項待解決' },
        { value: '9', text: '有契約規定暫停給付估驗計價款' },
        { value: '10', text: '估驗或驗收不合格' },
        { value: '11', text: '契約規定應辦事項尚未完成' },
        { value: '12', text: '其他' },
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
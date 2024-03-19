<template>
  <div>
    <section>
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">承造廠商品管人員詳細資料</h5>
      </div>
      <b-form-row>
        <i-form-group-check :label="'專職或非專職: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          <b-form-radio-group :options="formOptions.duty"> </b-form-radio-group>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'品管人員身分證: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          <b-form-input class="col-4"></b-form-input>
          <b-button class="btn-sm ml-1" @click="toCheck" style="background-color: #1aa4b7">確實審查</b-button>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'品管人員姓名: '" :label-cols="2" :content-cols="10" class="col-12">許X文 </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'受訓日期: '">民國108年12月31日 </i-form-group-check>
        <i-form-group-check :label="'補訓日期: '"> 民國000年00月00日</i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'有效期限:'">民國112年12月30日 </i-form-group-check>
        <i-form-group-check :label="'初次提報民國日: '">民國109年12月07日 </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'品管人員行動電話: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          <b-form-input class="col-4"></b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'品管人員專長: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          <b-form-input class="col-4"></b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'受聘時服務單位: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          <b-form-select :options="formOptions.company" class="col-4"></b-form-select>
        </i-form-group-check>
      </b-form-row>

      <b-form-row>
        <i-form-group-check :label="'進駐工地日期: '" :labelStar="true">
          <i-date-picker placeholder="yyy/MM/dd" class="col-6"></i-date-picker>
        </i-form-group-check>
        <i-form-group-check :label="'解聘或完工日期: '">
          <i-date-picker placeholder="yyy/MM/dd" class="col-6"></i-date-picker>
        </i-form-group-check>
      </b-form-row>

      <b-form-row>
        <i-form-group-check :label="'目前狀況: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          <b-form-select :options="formOptions.status" class="col-4"></b-form-select>
        </i-form-group-check>
      </b-form-row>
      <div>
        <b-col style="text-align: center">
          <i-button type="check-circle" @click="submitForm"></i-button>
          <i-button type="x-circle" @click="reset"></i-button>
          <i-button type="arrow-counterclockwise" @click="toCancel"></i-button>
        </b-col>
      </div>
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
        { value: '1', text: '專職人員' },
        { value: '2', text: '非專職人員' },
      ],
      company: [
        { value: '1', text: '請選擇' },
        { value: '2', text: '1:港洲營造公司' },
        { value: '3', text: '2:有力機電工程股份有限公司' },
        { value: '4', text: '3:致邦空調工程有限公司' },
      ],
      status: [
        { value: '1', text: '1:在職' },
        { value: '2', text: '2:中途離職' },
        { value: '3', text: '3:竣工解雇' },
        { value: '4', text: '4:查核丙等被撤換' },
        { value: '5', text: '5:查核缺失改善成效不佳被更換' },
        { value: '6', text: '6:未實際於工地執行工作被撤換' },
        { value: '7', text: '7:未能確實執行工作被撤換' },
        { value: '9', text: '9:專案註記' },
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

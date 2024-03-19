<template>
  <div>
    <section>
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">終止或解除合約詳細資料</h5>
      </div>
      <b-form-row>
        <i-form-group-check :label="'終解約日期: '" :labelStar="true">
          <i-date-picker placeholder="yyy/MM/dd" class="col-6"></i-date-picker>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'終止或解除: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          <b-form-radio-group :options="formOptions.duty"> </b-form-radio-group>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'核定文號: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          <b-form-input class="col-4"></b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'終解約原因: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
           <b-form-select :options="formOptions.company" class="col-4"></b-form-select>  責任歸屬:
            <b-form-radio-group :options="formOptions.status"> </b-form-radio-group>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'終解約原因說明: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          <b-form-input class="col-4" size="lg"></b-form-input>
        </i-form-group-check>
      </b-form-row>

      <b-form-row>
         <i-form-group-check :label="'依合約處理事項: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          <b-form-input class="col-4" size="lg"></b-form-input>
        </i-form-group-check>
      </b-form-row>

      <b-form-row>
        <i-form-group-check :label="'是否重新發包: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          <b-form-radio-group :options="formOptions.add"> </b-form-radio-group>
        </i-form-group-check>
      </b-form-row>
       <b-form-row>
        <i-form-group-check :label="'重新發包日期: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <i-date-picker placeholder="yyy/MM/dd" class="col-4"></i-date-picker>
        </i-form-group-check>
      </b-form-row>
       <b-form-row>
        <i-form-group-check :label="'重新發包檔案編號: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4"></b-form-input>（　　　）
          <b-button class="btn-sm ml-1" @click="toCheck" style="background-color: #1aa4b7">標案名稱編號</b-button>
        </i-form-group-check>
      </b-form-row>

      <b-form-row>
         <i-form-group-check :label="'不重新發包說明: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4" size="lg"></b-form-input>
        </i-form-group-check>
      </b-form-row>
      <div>
        <b-col style="text-align: center">
          <b-button>儲存</b-button>
          <b-button>取消</b-button>
          <b-button>清除</b-button>
          <b-button>解約狀況表</b-button>
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
        { value: '1', text: '終止契約' },
        { value: '2', text: '解除契約' },
      ],
      company: [
        { value: '1', text: '1:逾期未開工' },
      ],
      status: [
        { value: '1', text: '甲方:(機關)' },
        { value: '2', text: '乙方:(廠商)' },
        { value: '3', text: '甲乙雙方' },
        { value: '4', text: '尚未確定' },
      ],
      add: [
        { value: '1', text: '是' },
        { value: '2', text: '否' },
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

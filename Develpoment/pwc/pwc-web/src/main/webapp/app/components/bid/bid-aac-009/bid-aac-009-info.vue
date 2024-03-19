<template>
  <div>
    <section>
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">竣工或結案詳細資料</h5>
      </div>
      <b-form-row>
        <i-form-group-check :label="'竣工(或解約)日期: '" :label-cols="2" :content-cols="10" class="col-12">預定:110年12月31日 </i-form-group-check>
        <i-form-group-check :label="''" :label-cols="2" :content-cols="10" class="col-12">實際:<i-date-picker placeholder="yyy/MM/dd" class="col-2"></i-date-picker>
         </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'工期天數: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          最終核定天數<b-form-input class="col-2" ></b-form-input>天
        </i-form-group-check>
        <i-form-group-check :label="''" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          實際履約日數<b-form-input class="col-2" ></b-form-input>天
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
         <i-form-group-check :label="'不(免)計工期天數: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-2" ></b-form-input>天
        </i-form-group-check>
      </b-form-row>
      
      <b-form-row>
        <i-form-group-check :label="'備註: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-4" size="lg"></b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'驗收扣款: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-2" ></b-form-input>千元
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'結算總額: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-2" ></b-form-input>千元
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check :label="'履約逾期: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
         提前竣工天數 <b-form-input class="col-2" ></b-form-input>天，履約逾期總天數<b-form-input class="col-2" ></b-form-input>天，應計違約金天數<b-form-input class="col-2" ></b-form-input>天
        </i-form-group-check>
      </b-form-row>
        <b-form-row>
           <i-form-group-check :label="'違約金: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          逾期違約金<b-form-input class="col-2" ></b-form-input>千元
        </i-form-group-check>
        <i-form-group-check :label="''" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          其他違約金<b-form-input class="col-2" ></b-form-input>千元
        </i-form-group-check>
            
        </b-form-row>
        <b-form-row>
            <i-form-group-check :label="'履約保證金: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
         繳交金額 <b-form-input class="col-2" ></b-form-input>千元(本欄於A4填報)，發還金額<b-form-input class="col-2" ></b-form-input>千元
        </i-form-group-check>
            
        </b-form-row>

        <b-form-row>
           <i-form-group-check :label="'履約保證金發還日期: '" :labelStar="false">
          <i-date-picker placeholder="yyy/MM/dd" class="col-8"></i-date-picker>
          <div style="color:red">(無履約保證金免填本欄)</div>
        </i-form-group-check>
        </b-form-row>

        <b-form-row>
             <i-form-group-check :label="'履約保證金不予發還情形: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
          <b-form-select :options="formOptions.status" class="col-4"></b-form-select>
        </i-form-group-check>
            
        </b-form-row>

      
      
     
      <b-form-row>
        <i-form-group-check :label="'保固保證金: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="false">
          <b-form-input class="col-2" ></b-form-input>千元
        </i-form-group-check>
      </b-form-row>

      <b-form-row>
         <i-form-group-check :label="'保固保證金有效期: '" :labelStar="false">
          <i-date-picker placeholder="yyy/MM/dd" class="col-8"></i-date-picker>
        </i-form-group-check>
      </b-form-row>
      

      <b-form-row>
        <i-form-group-check :label="'決算日期: '" :label-cols="2" :content-cols="10" class="col-12">預定:<i-date-picker placeholder="yyy/MM/dd" class="col-2"></i-date-picker> </i-form-group-check>
        <i-form-group-check :label="''" :label-cols="2" :content-cols="10" class="col-12">實際:(實際執行進度未達100%前本欄不開放填報)<br>尚未完工前如因故與廠商解約請先至C7解約填報解約狀況
         </i-form-group-check>
      </b-form-row>


      <b-form-row>
        <i-form-group-check :label="'決算金額: '" :label-cols="2" :content-cols="10" class="col-12">(尚未完工或解約前本欄不開放填報)<br>
        (決算金額包括發包工程費、工程管理費、自辦工程費、供給材料費等)<br>實際驗收完成超過一年後，本標案所有資料將不再開放修改
        </i-form-group-check>
        
      </b-form-row>


      <div>
        <b-col style="text-align: center">
          <b-button>儲存</b-button>
          <b-button>取消</b-button>
          <b-button>清除</b-button>
          <b-button>結算證明書(單張)</b-button>
          <b-button>結算證明書(多張)</b-button>
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
        { value: '1', text: '1:工程施工中' },
        { value: '2', text: '2:中途離職' },
        { value: '3', text: '3:竣工離職' },
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

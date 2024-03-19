<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="mt-2"></div>
        <div class="card-header">
          <h5 class="m-0">安衛人員詳細資料</h5>
        </div>
        <div class="mt-2">
          <b-form-row>
            <i-form-group-check :label="'初次提報日期: '" :label-cols="2" :content-cols="10" class="col-12">
              民國109年12月07月
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'專職或非專職: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
              <b-form-radio-group>
                <b-form-radio>專職人員</b-form-radio>
                <b-form-radio>非專職人員</b-form-radio>
              </b-form-radio-group>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'身分證: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
              <b-form-input class="col-4"></b-form-input>
              <b-button class="btn-sm ml-1" style="background-color: #1aa4b7">確實審查</b-button>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'姓名: '" :label-cols="2" :content-cols="10" class="col-12"> 黃*泱 </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'行動電話: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
              <b-form-input class="col-4"></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'職稱: '" :labelStar="true" :label-cols="2" :content-cols="10" class="col-12">
              安衛人員
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'證書編號:'" :label-cols="2" :content-cols="10" class="col-12">
              <b-form-input class="col-4"></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'負責本工程啟始日期:'" :labelStar="true">
              <i-date-picker class="col-6"></i-date-picker>
            </i-form-group-check>
            <i-form-group-check :label="'負責本工程結束日期:'">
              <i-date-picker class="col-6"></i-date-picker>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check :label="'目前狀況:'" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
              <b-form-select class="col-4" :options="formOptions.duty"></b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-col style="text-align: center">
            <i-button type="check-circle" @click="submitForm"></i-button>
            <i-button type="x-circle" @click="reset"></i-button>
            <i-button type="arrow-counterclockwise" @click="toCancel"></i-button>
          </b-col>
        </div>
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
  name: 'bidAac005Edit',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
    formStatus: {
      type: String,
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

    const formOptions = reactive({
      duty: [
        { value: '1', text: '1:在職' },
        { value: '2', text: '2:中途離職' },
        { value: '3', text: '3:竣工解職' },
        { value: '4', text: '4:表現不良遭換' },
        { value: '9', text: '9:誤登' },
      ],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      wkut: '',
      prjno: '',
      pname: '',
      id: '',
      job: '',
      certNo: '',
      startDate: '',
      endDate: '',
      duty: '',
      createDate: '',
      updateDate: '',
    };

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      pname: {},
      id: {},
      job: {},
      certNo: {},
      startDate: {},
      endDate: {},
      duty: {},
      createDate: {},
      updateDate: {},
    };

    const handleQuery = () => {};

    const submitForm = () => {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAac005Info', { bidProjectKey });
    };

    const toCancel = () => {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAac005Info', { bidProjectKey });
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          handleQuery();
        }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      $v,
      reset,
      formOptions,
      bidProjectKeyProp,
      formStatusProp,
      form,
      handleQuery,
      submitForm,
      toCancel,
    };
  },
};
</script>

<style scoped></style>

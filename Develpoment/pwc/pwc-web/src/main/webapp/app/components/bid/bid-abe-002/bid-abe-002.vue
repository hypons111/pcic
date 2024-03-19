<template>
  <div>
    <div class="card">
      <bidProjectInfo :projectInfoProp="projectInfo" />

      <div class="card-body">
        <b-table-simple hover small caption-top responsive bordered>
          <b-td width="50%">
            <div v-if="bidProjectOPDT">
              <span style="color: blue">#本標案為決標轉入<br /></span>
              <span style="color: blue" v-if="bidProjectName">#決標轉入時本標案名稱為{{ projectName }}<br /></span>
              <span style="color: blue" v-if="bidProjectBDGT">#決標轉入時發包預算為{{ projectBDGT1 }}千元<br /></span>
              <span style="color: blue" v-if="bidProjectCtSum">#決標轉入時決標金額為{{ projectCtSum }}千元<br /></span>
            </div>
            <span style="color: red" v-if="bidGovCheck">#本標案已有查核紀錄</span><br />
            <span style="color: red" v-if="bidPrjQc">#本標案已有品管監造現場人員</span><br />
          </b-td>
          <b-td width="50%">
            <span style="color: blue">選擇是否改為非工程(打勾表示本標案改為非工程)</span><br />
            <b-form-checkbox v-model="buttonChecked">本標案改為非工程</b-form-checkbox>
          </b-td>
        </b-table-simple>
        <div style="text-align: center">
          <i-button type="save" @click="submitForm"></i-button>
          <i-button type="x-circle" @click="reset"></i-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { inject, ref, watch } from '@vue/composition-api';
import axios from 'axios';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import { reactive, Ref, toRef } from '@vue/composition-api';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import AccountService from '@/account/account.service';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'bidAbe002',
  components: {
    bidProjectInfo,
  },
  props: {
    // 由前面傳來的值決定是哪一個基本資料顯示及其工安事件(基本資料用)
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },

  setup(props, context) {
    const notificationService: NotificationService = useNotification();
    // 基本資料
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();

    // 傳入PK
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

    /**
     * 撈出project的部分資料(基本資料)
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
          result.typeCode = data;
          Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        });
      });
    }
    // 左邊欄位狀態
    //本標案轉入
    const bidProjectOPDT = ref(false);
    // 本標案名稱
    const bidProjectName = ref(false);
    // 本標案發包預算
    const bidProjectBDGT = ref(false);
    // 本標案決標金額
    const bidProjectCtSum = ref(false);
    // 查核督導資料
    const bidGovCheck = ref(false);
    // 品管監造人員
    const bidPrjQc = ref(false);

    let projectName = ref();
    let projectBDGT1 = ref();
    let projectCtSum = ref();

    //選項
    const buttonChecked = ref(false);

    const form = ref({});

    /**
     * 從使用者撈 user,ip的作法。
     * 如果是登錄就有的資訊應從使用者那邊撈出來
     * */
    // 舊寫法後面要用inject
    const store = context.root.$store;
    let userIdentity = store.getters.account.id;
    let userIp = store.getters.account.ip;

    const handleQuery = bidProjectKeyProp => {
      axios.get(`/bid-projects/${bidProjectKeyProp.wkut}/${bidProjectKeyProp.prjno}`).then(({ data }) => {
        if (data.opdt == null) {
          bidProjectOPDT.value = true;
        }
        if (data.name != data.name0) {
          if (data.name0 != null && data.name0.length > 0) {
            console.log('@@@@ ', data.name0);
            bidProjectName.value = true;
            projectName.value = data.name0;
          }
        }
        if (data.bdgt1 != data.bdgt1_0) {
          if (data.bdgt1_0 != null && data.bdgt1_0.length > 0) {
            bidProjectBDGT.value = true;
            projectBDGT1.value = data.bdgt1_0;
          }
        }
        if (data.ctSum != data.ctSum0) {
          if (data.ctSum0 != null && data.ctSum0.length > 0) {
            bidProjectCtSum.value = true;
            projectCtSum.value = data.ctSum0;
          }
        }
        // 要用assign 才能把值正常存入form
        Object.assign(form.value, data);
      });
    };

    const handleGovCheckQuery = bidProjectKeyProp => {
      axios
        .post('/bid-gov-check/getByCriteria', bidProjectKeyProp)
        .then(({ data }) => {
          if (data != null && data.length > 0) {
            bidGovCheck.value = true;
          }
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const handleProjectQc = bidProjectKeyProp => {
      axios.post('/bid-prj-qcs/getListByCretiria', bidProjectKeyProp).then(({ data }) => {
        if (data != null && data.length > 0) {
          bidPrjQc.value = true;
        }
      });
    };

    const reset = () => {
      buttonChecked.value = false;
    };

    const submitForm = () => {
      if (buttonChecked.value) {
        form.value['typeCode'] = '99';
        form.value['typeCode2'] = '';
        form.value['uIp'] = userIp;
        form.value['updateUser'] = userIdentity;

        // 前端做時區的時差處理
        const value = new Date();
        // 時區差(分鐘)
        const offset = value.getTimezoneOffset();
        form.value['updateDate'] = new Date();
        form.value['updateDate'] = new Date(form.value['updateDate'].getTime() - offset * 60 * 1000);

        axios
          .put(`/bid-projects/updateOne`, form.value)
          .then(({ data }) => {
            notificationService.success('標案已加註變更字眼');

            navigateByNameAndParams('bidAbe002Query', {});
          })
          .catch(notificationErrorHandler(notificationService));
      }
    };

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue != null) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          handleQuery(newValue);
          handleGovCheckQuery(newValue);
          handleProjectQc(newValue);
        }
      },
      { immediate: true }
    );

    return {
      bidProjectKeyProp,
      projectInfo,
      bidProjectName,
      bidProjectOPDT,
      bidProjectBDGT,
      bidProjectCtSum,
      bidGovCheck,
      bidPrjQc,
      projectName,
      projectBDGT1,
      projectCtSum,
      buttonChecked,
      reset,
      submitForm,
    };
  },
};
</script>

<style scoped></style>

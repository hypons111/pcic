<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="mt-2"></div>
        <div class="card-header">
          <h5 class="m-0">變更執行機關</h5>
        </div>
        <div class="mt-2">
          <tr>
            <td>變更後執行機關</td>
            <td><b-form-select></b-form-select></td>
          </tr>
          <b-col style="text-align: center">
            <i-button type="check-circle" @click="submitForm"></i-button>
            <i-button type="arrow-counterclockwise" @click="toCancel"></i-button>
            <i-button type="x-circle" @click="reset"></i-button>
          </b-col>
        </div>
        <div>
          <b-form-row>說明:</b-form-row>
          <b-form-row
            >一、執行機關變更主要為將<span style="color: blue">本機關</span>之工程標案執行單位調整至<span style="color: blue"
              >所屬一級機關</span
            >，或將<span style="color: blue">所屬機關</span>執行之標案調整</b-form-row
          >
          <b-form-row>二、確定變更後，標案基本資料及其相關進度品管監工等資料均同時移轉。</b-form-row>
          <b-form-row>三、如要將本機關執行之標案轉移至平行機關執行時，請洽上級機關辦理。</b-form-row>
          <b-form-row
            >四、如要將本機關執行之標案轉移至所屬一級機關之下屬機關時，請先將標案移轉至所屬一級機關，再由該機關移轉至其下屬機關</b-form-row
          >
        </div>
      </div>
    </div>
    <!-- 錯誤訊息 -->
    <b-modal id="valid-error-modal" size="lg" title="資料驗證有誤" :header-text-variant="'light'" :header-bg-variant="'danger'" hide-footer>
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModals.hide('valid-error-modal')">了解</b-button>
    </b-modal>
  </div>
</template>

<script lang="ts">
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { inject, Reactive, reactive, ref, Ref, toRef, watch } from '@vue/composition-api';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import AccountService from '@/account/account.service';
import { useBvModal } from '@/shared/modal';
import { BidDsptch, IBidDsptch } from '@/shared/model/bidModel/bid-dsptch.model';
import { numeric, required } from '@/shared/validators';
import { useGetters } from '@u3u/vue-hooks';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';

export default {
  name: 'bidAda001Edit',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  setup(props) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidProjectService = new BidProjectService();
    const notificationService: NotificationService = useNotification();
    // 取得登錄資料
    const accountService = inject<() => AccountService>('accountService')();
    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });
    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);
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
      wkutName: '',
      wkutOld: '',
      prjnoOld: '',
      createUser: '',
      createDate: undefined,
      updateDate: undefined,
      updateUser: '',
    };

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      wkutName: {},
      wkutOld: {},
      prjnoOld: {},
      createUser: {},
      createDate: {},
      updateDate: {},
      updateUser: {},
    };

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          form.wkutOld = bidProjectKeyProp.value.wkut;
          form.prjnoOld = bidProjectKeyProp.value.prjno;

          form.prjno = bidProjectKeyProp.value.prjno;

          form.createUser = userIdentity;
          form.createDate = new Date();

          // bidDsptchService
          //   .saveBidPrjFals(form)
          //   .then(() => {
          //     navigateByNameAndParams('bidAab007Info', {
          //       bidProjectKey: {
          //         wkut: bidProjectKeyProp.value.wkut,
          //         prjno: bidProjectKeyProp.value.prjno,
          //       },
          //     });
          //   })
          //   .catch(err => {
          //     if (err.response && err.response.data.errorKey === 'bidValidError') {
          //       errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
          //       $bvModals.show('valid-error-modal');
          //     }
          //   });
        } else {
          notificationService.danger('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    // 取消
    const toCancel = () => {
      reset();

      navigateByNameAndParams('bidAda001Query', {});
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          Object.assign(form, formDefault);
          reset();
        }
      },
      { immediate: true }
    );
    return {
      projectInfo,
      $v,
      reset,
      bidProjectKeyProp,
      form,
      submitForm,
      toCancel,
      errorMessages,
      $bvModals,
      userIdentity,
      validateState,
    };
  },
};
</script>

<style scoped></style>

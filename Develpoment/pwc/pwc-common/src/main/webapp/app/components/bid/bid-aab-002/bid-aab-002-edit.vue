<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="mt-2"></div>
        <div class="card-header">
          <h5 class="m-0">預定完成里程碑詳細資料</h5>
        </div>
        <div class="mt-2">
          <b-form-row>
            <i-form-group-check
              :label="'填報日期: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              v-if="formStatusProp === 'create'"
            >
              <i-date-picker v-model="$v.applyDate.$model" class="col-4"></i-date-picker>
            </i-form-group-check>

            <i-form-group-check
              :label="'填報日期: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              v-if="formStatusProp === 'modify'"
            >
              {{ form.applyDate | dateToString }}
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check
              :label="'里程碑名稱: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              v-if="formStatusProp === 'create'"
            >
              <b-form-input v-model="$v.milestoneName.$model" class="col-4"></b-form-input>
            </i-form-group-check>

            <i-form-group-check
              :label="'里程碑名稱: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              v-if="formStatusProp === 'modify'"
            >
              {{ form.milestoneName }}
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check
              :label="'類別: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              v-if="formStatusProp === 'create'"
            >
              <b-form-select v-model="$v.milestoneType.$model" class="col-4" :options="formOptions.milestoneType"></b-form-select>
            </i-form-group-check>
            <i-form-group-check
              :label="'類別: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              v-if="formStatusProp === 'modify'"
            >
              {{ $v.milestoneType.$model ? queryOptionsFormatter('milestoneType', $v.milestoneType.$model).text : '' }}
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="'完成狀態: '" :label-cols="2" :content-cols="10" class="col-12" :labelStar="true">
              <b-form-radio-group
                v-model="$v.milestoneStatus.$model"
                class="col-4"
                :options="formOptions.milestoneStatus"
              ></b-form-radio-group>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check
              :label="'預定完成日期: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              v-if="formStatusProp === 'create'"
            >
              <i-date-picker v-model="$v.estimateDate.$model" class="col-4"></i-date-picker>
            </i-form-group-check>
            <i-form-group-check
              :label="'預定完成日期: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              v-if="formStatusProp === 'modify'"
            >
              {{ form.estimateDate | dateToString }}
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check
              :label="'實際完成日期: '"
              :labelStar="true"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              v-if="formStatusProp === 'create'"
            >
              <i-date-picker v-model="$v.actualDate.$model" class="col-4"></i-date-picker>
            </i-form-group-check>

            <i-form-group-check
              :label="'實際完成日期: '"
              :labelStar="true"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              v-if="formStatusProp === 'modify'"
            >
              <i-date-picker v-model="$v.actualDate.$model" class="col-4"></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check
              :label="'屆期未完成原因目前辦理情形: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              v-if="formStatusProp === 'create'"
            >
              <b-form-textarea v-model="$v.remark.$model"></b-form-textarea>
            </i-form-group-check>
            <i-form-group-check
              :label="'屆期未完成原因目前辦理情形: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              v-if="formStatusProp === 'modify'"
            >
              {{ form.remark }}
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
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import { required } from '@/shared/validators';
import BidPrjMilestoneService from '@/components/bid/bidService/bid-prj-milestone.service';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { BidPrjModify, IBidPrjModify } from '@/shared/model/bidModel/bid-prj-modify.model';
import { BidPrjMilestone, IBidPrjMilestone } from '@/shared/model/bidModel/bid-prj-milestone.model';
import AccountService from '@/account/account.service';
import { formatToString } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'bidAab002Edit',
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
    const bidPrjMilestoneService = new BidPrjMilestoneService();
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

    const formOptions = reactive({
      milestoneType: [
        { value: '1', text: '1:要徑項目' },
        { value: '2', text: '2:未完成開工要件項目' },
        { value: '3', text: '3:停工中預定復工' },
        { value: '4', text: '4:關鍵控管事件' },
        { value: '5', text: '5:重要事件' },
        { value: '6', text: '6:其他' },
      ],
      milestoneStatus: [
        { value: 'Y', text: '已完成' },
        { value: 'N', text: '未完成' },
      ],
    });

    // 選項轉換
    const queryOptionsFormatter = (option: string, value: string) =>
      formOptions[option].find(function (item) {
        if (item.value === value) {
          return item.text; //為什還是傳出物件???
        }
      });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault: IBidPrjMilestone = new BidPrjMilestone();

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      applyDate: { notNull: required },
      milestoneNo: { notNull: required },
      milestoneName: { notNull: required },
      milestoneType: { notNull: required },
      milestoneStatus: { notNull: required },
      estimateDate: { notNull: required },
      actualDate: { notNull: required },
      remark: {},
      createDate: {},
      createUser: {},
      updateDate: {},
      updateUser: {},
    };

    const submitForm = () => {
      if (formStatusProp.value === 'create') {
        form.createUser = accountService.user.id;
        form.updateUser = accountService.user.id;
        form.wkut = bidProjectKeyProp.value.wkut;
        form.prjno = bidProjectKeyProp.value.prjno;

        form.createDate = new Date();
        form.updateDate = new Date();

        bidPrjMilestoneService
          .saveBidPrjMilestone(form)
          .then(() => {
            navigateByNameAndParams('bidAab002Info', {
              bidProjectKey: {
                wkut: bidProjectKeyProp.value.wkut,
                prjno: bidProjectKeyProp.value.prjno,
              },
            });
          })
          .catch(err => {
            if (err.response && err.response.data.errorKey === 'bidValidError') {
              errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
              $bvModals.show('valid-error-modal');
            }
          });
      } else if (formStatusProp.value === 'modify') {
        form.updateUser = accountService.user.id;
        form.updateDate = new Date();

        bidPrjMilestoneService
          .updateExistingBidPrjModify(form)
          .then(() => {
            navigateByNameAndParams('bidAab002Info', {
              bidProjectKey: {
                wkut: bidProjectKeyProp.value.wkut,
                prjno: bidProjectKeyProp.value.prjno,
              },
            });
          })
          .catch(err => {
            if (err.response && err.response.data.errorKey === 'bidValidError') {
              errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
              $bvModals.show('valid-error-modal');
            }
          });
      }
    };

    // 取消
    const toCancel = () => {
      reset();
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAab002Info', { bidProjectKey });
    };

    // 建立表單物件 ref
    let form: Reactive<IBidPrjMilestone> = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    watch(
      bidProjectKeyProp,
      newValue => {
        reset();
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          if (formStatusProp.value === 'create') {
            Object.assign(formDefault, new BidPrjMilestone());
            Object.assign(form, formDefault);
            reset();
          } else if (formStatusProp.value === 'modify') {
            bidPrjMilestoneService.getOneBidPrjModify(newValue).then(bidPrjMilestoneDate => {
              bidPrjMilestoneDate.data.applyDate = new Date(bidPrjMilestoneDate.data.applyDate);
              bidPrjMilestoneDate.data.estimateDate = new Date(bidPrjMilestoneDate.data.estimateDate);
              bidPrjMilestoneDate.data.actualDate = new Date(bidPrjMilestoneDate.data.actualDate);

              Object.assign(form, bidPrjMilestoneDate.data);
              Object.assign(formDefault, bidPrjMilestoneDate.data);
              reset();
            });
          }
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
      submitForm,
      toCancel,
      errorMessages,
      queryOptionsFormatter,
      $bvModals,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
};
</script>

<style scoped></style>

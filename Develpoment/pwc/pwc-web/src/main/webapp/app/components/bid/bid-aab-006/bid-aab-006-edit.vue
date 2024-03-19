<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="mt-2"></div>
        <div class="card-header">
          <h5 class="m-0">開工要件詳細資料</h5>
        </div>
        <div class="mt-2">
          <b-form-row>
            <i-form-group-check
              :label="'檢核日期: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              :item="$v.checkDate"
              v-if="formStatusProp === 'create'"
            >
              <i-date-picker v-model="$v.checkDate.$model" class="col-4"></i-date-picker>
            </i-form-group-check>
            <i-form-group-check
              :label="'檢核日期: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              :item="$v.checkDate"
              v-if="formStatusProp === 'modify' || formStatusProp === 'info'"
            >
              {{ form.checkDate | dateToString }}
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check
              :label="'檔案上傳: '"
              :label-cols="2"
              :content-cols="10"
              class="col-12"
              :labelStar="true"
              v-if="formStatusProp === 'create' || formStatusProp === 'modify'"
            >
              <b-form-file
                v-model="$v.file.$model"
                :accept="'.jpg,.pdf'"
                :state="Boolean($v.file.$model)"
                browse-text="瀏覽..."
                :placeholder="$v.file.$model ? $v.file.$model.name : ''"
              >
              </b-form-file>
            </i-form-group-check>
            <i-form-group-check :label="'檔案上傳:'" :label-cols="2" :content-cols="10" class="col-12" v-if="formStatusProp === 'info'">
              <i-button type="file-earmark-arrow-down" @click="downloadAdmFile(form.admFileId)"></i-button>
            </i-form-group-check>
          </b-form-row>
          <hr />
          <b-table-simple class="col-12" borderless v-for="(item, index) in formOptions.name" :key="index">
            <tr>
              <td class="col-3">檢核項目</td>
              <td class="col-2">檢核結果</td>
              <td>檢討結果及原因備案</td>
              <td>（上級機關核定公文日期及文號）</td>
            </tr>
            <tr>
              <td class="col-3">{{ item.text }}</td>
              <td class="col-2">
                <b-form-radio-group
                  v-model="$v['yndA' + (index + 1 < 10 ? '0' + (index + 1) : index + 1)].$model"
                  :options="radioOptions.ynd"
                  :disabled="disabled"
                >
                </b-form-radio-group>
              </td>
              <td colspan="2">
                <div>
                  <b-form-textarea
                    v-model="$v['resonA' + (index + 1 < 10 ? '0' + (index + 1) : index + 1)].$model"
                    :disabled="disabled"
                  ></b-form-textarea>
                </div>
                <div>
                  預定完成日期:
                  <i-date-picker
                    v-model="$v['dateA' + (index + 1 < 10 ? '0' + (index + 1) : index + 1)].$model"
                    class="col-4"
                    :disabled="disabled"
                  ></i-date-picker>
                </div>
              </td>
            </tr>
          </b-table-simple>

          <hr />
          <b-col style="text-align: center">
            <i-button type="check-circle" @click="submitForm"></i-button>
            <i-button type="arrow-counterclockwise" @click="toCancel"></i-button>
            <i-button type="x-circle" @click="reset"></i-button>
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
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import { useBvModal } from '@/shared/modal';
import { BidPrjStc, IBidPrjStc } from '@/shared/model/bidModel/bid-prj-stc.model';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import AccountService from '@/account/account.service';
import BidPrjStcService from '@/components/bid/bidService/bid-prj-stc.service';
import { required } from '@/shared/validators';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import axios, { AxiosResponse } from 'axios';
import { downloadFile } from '@/shared/formatter/common';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';

export default {
  name: 'bidAab006Edit',
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
    const bidCommonService = new BidCommonService();
    const bidPrjStcService = new BidPrjStcService();
    const disabled = ref(false);
    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);
    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });
    const notificationService: NotificationService = useNotification();
    // 取得登錄資料
    const accountService = inject<() => AccountService>('accountService')();

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
          result.typeCode = data;
          Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        });
      });
    }

    const radioOptions = reactive({
      ynd: [
        { value: 'D', text: '無須辦理' },
        { value: 'Y', text: '已完成' },
        { value: 'N', text: '未完成' },
      ],
    });

    const formOptions = reactive({
      name: [
        { value: '1', text: '1. 環境影響評估(含環境影響差異分析)' },
        { value: '2', text: '2. 水土保持規劃書及水土保持計畫' },
        { value: '3', text: '3. 非都市土地使用變更編定' },
        { value: '4', text: '4. 都市計畫擬定或變更' },
        { value: '5', text: '5. 用地取得' },
        { value: '6', text: '6. 都市計畫審議' },
        { value: '7', text: '7. 古蹟、歷史建築、聚落、文化景觀及遺址保存與維護' },
        { value: '8', text: '8. 建築許可(建築執照或特種建築物許可等)' },
        { value: '9', text: '9. 河川區域使用許可' },
        { value: '10', text: '10. 鄰近、跨(穿)越鐵路、公路施工許可；捷運禁限建範圍施工許可；道路挖掘許可' },
        { value: '11', text: '11. 管線箱涵處理及公有管線佈設許可' },
        { value: '12', text: '12. 樹木保護計畫' },
        { value: '13', text: '13. 評估其他標案影響' },
        { value: '14', text: '14. 地上物處理' },
        { value: '15', text: '15. 工程預算可支用情形' },
      ],
    });

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      checkDate: { notNull: required },
      file: {},
      admFileId: {},
      yndA01: {},
      resonA01: {},
      dateA01: {},
      yndA02: {},
      resonA02: {},
      dateA02: {},
      yndA03: {},
      resonA03: {},
      dateA03: {},
      yndA04: {},
      resonA04: {},
      dateA04: {},
      yndA05: {},
      resonA05: {},
      dateA05: {},
      yndA06: {},
      resonA06: {},
      dateA06: {},
      yndA07: {},
      resonA07: {},
      dateA07: {},
      yndA08: {},
      resonA08: {},
      dateA08: {},
      yndA09: {},
      resonA09: {},
      dateA09: {},
      yndA10: {},
      resonA10: {},
      dateA10: {},
      yndA11: {},
      resonA11: {},
      dateA11: {},
      yndA12: {},
      resonA12: {},
      dateA12: {},
      yndA13: {},
      resonA13: {},
      dateA13: {},
      yndA14: {},
      resonA14: {},
      dateA14: {},
      yndA15: {},
      resonA15: {},
      dateA15: {},
      createDate: {},
      createUser: {},
      updateDate: {},
      updateUser: {},
    };

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (formStatusProp.value === 'create') {
            // 檔案上傳
            form.wkut = bidProjectKeyProp.value.wkut;
            form.prjno = bidProjectKeyProp.value.prjno;

            const formData = new FormData();
            formData.append('form', new Blob([JSON.stringify(form)], { type: 'application/json' }));
            formData.append('file', form.file);

            bidPrjStcService
              .saveBidPrjStc2(formData)
              .then(() => {
                navigateByNameAndParams('bidAab006Info', {
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
            const formData = new FormData();
            formData.append('form', new Blob([JSON.stringify(form)], { type: 'application/json' }));
            formData.append('file', form.file);
            bidPrjStcService
              .updateExistingStc2(formData)
              .then(() => {
                navigateByNameAndParams('bidAab006Info', {
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
        } else {
          notificationService.danger('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const toCancel = () => {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAab006Info', { bidProjectKey });
    };

    //對應到下載按鈕
    function downloadAdmFile(id: number) {
      axios
        .get(`/adm-files/download/${id}`, { responseType: 'blob' })
        .then((response: AxiosResponse) => downloadFile(response))
        .catch(notificationErrorHandler(notificationService));
    }

    // 建立表單物件 ref
    let formDefault: IBidPrjStc = new BidPrjStc();
    let form: Reactive<IBidPrjStc> = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          if (formStatusProp.value === 'info') {
            disabled.value = true;
          } else {
            disabled.value = false;
          }

          if (formStatusProp.value === 'create') {
            Object.assign(formDefault, new BidPrjStc());
            Object.assign(form, formDefault);
            reset();
          } else if (formStatusProp.value === 'modify' || formStatusProp.value === 'info') {
            bidPrjStcService.getOneBidPrjStc(newValue).then(bidPrjStcData => {
              bidPrjStcData.data.checkDate = new Date(bidPrjStcData.data.checkDate);

              Object.assign(form, bidPrjStcData.data);
              Object.assign(formDefault, bidPrjStcData.data);
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
      disabled,
      radioOptions,
      formOptions,
      bidProjectKeyProp,
      formStatusProp,
      form,
      submitForm,
      toCancel,
      $bvModals,
      errorMessages,
      downloadAdmFile,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
};
</script>

<style scoped></style>

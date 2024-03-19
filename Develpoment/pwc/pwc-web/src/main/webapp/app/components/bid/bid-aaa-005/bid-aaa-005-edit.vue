<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <section class="container mt-2">
      <div class="card" v-if="formStatusRef == formStatusEnum.INFO">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">付款狀況資料</h5>
        </div>
        <div class="card-body">
          <div class="mb-1">
            <i-button type="file-earmark-plus" @click="toAddView"></i-button>
            <b-button variant="info" @click="toTableA">歷次請撥付款金額一覽表</b-button>
          </div>
          <i-table
            class="table-sm"
            :itemsUndefinedBehavior="'loading'"
            :items="table.data"
            :fields="table.fields"
            :totalItems="table.totalItems"
            :is-server-side-paging="true"
            :hideNo="true"
            @changePagination="handlePaginationChanged($event)"
          >
            <template #cell(action)="row">
              <i-button type="pencil-square" @click="toEditView(row.item)"></i-button>
              <b-button variant="info" @click="toTableB(row.item)">分期付款表</b-button>
              <b-button variant="info" @click="toTableC(row.item)">物價指數調整表</b-button>
            </template>
          </i-table>
        </div>
      </div>
      <div class="card" v-if="formStatusRef == formStatusEnum.MODIFY || formStatusRef == formStatusEnum.CREATE">
        <div class="card-header">
          <h5 class="m-0">付款狀況詳細資料</h5>
        </div>
        <div class="card-body col-12">
          <b-form-row>
            <i-form-group-check label="請付次別：" label-cols="6" content-cols="6">
              {{ $v.monNo.$model }}
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check label="應付總金額：" :item="$v.payMonT" label-cols="6" content-cols="6" labelStar>
              <b-form-input class="text-right" v-model="$v.payMonT.$model" :state="validateState($v.payMonT)"> </b-form-input>
            </i-form-group-check>
            <div class="pt-2">
              <label>千元 (為契約金額，如有變更設計增減，則為增減後之金額)</label>
            </div>
          </b-form-row>
          <b-form-row>
            <i-form-group-check label="截至上次已付總金額：" :item="$v.payMonU" label-cols="6" content-cols="6" labelStar>
              <b-form-input class="text-right" v-model="$v.payMonU.$model" :state="validateState($v.payMonU)"> </b-form-input>
            </i-form-group-check>
            <div class="pt-2">
              <label>千元(A) (各欄金額如要以元為單位保存資料，請填至小數後三位)</label>
            </div>
          </b-form-row>
          <b-form-row>
            <i-form-group-check label="本次請付：" label-cols="6" content-cols="6" :hasInvalid="true" labelStar>
              <i-form-group-check label="日期：" :item="$v.trueDate" label-cols="4" content-cols="8" class="col-12">
                <i-date-picker v-model="$v.trueDate.$model" :state="validateState($v.trueDate)"> </i-date-picker>
              </i-form-group-check>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check label-cols="6" content-cols="6" :hasInvalid="true">
              <i-form-group-check label="金額：" :item="$v.trueMon" label-cols="4" content-cols="8" class="col-12">
                <b-form-input class="text-right" v-model="$v.trueMon.$model" :state="validateState($v.trueMon)"> </b-form-input>
              </i-form-group-check>
            </i-form-group-check>
            <div class="pt-2">
              <label>千元</label>
            </div>
          </b-form-row>
          <b-form-row>
            <i-form-group-check label="本次廠商請款單據送達機關：" label-cols="6" content-cols="6" :hasInvalid="true" labelStar>
              <i-form-group-check label="日期：" :item="$v.tenDate" label-cols="4" content-cols="8" class="col-12">
                <i-date-picker v-model="$v.tenDate.$model" :state="validateState($v.tenDate)"> </i-date-picker>
              </i-form-group-check>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check label-cols="6" content-cols="6" :hasInvalid="true">
              <i-form-group-check label="金額：" :item="$v.tenMon" label-cols="4" content-cols="8" class="col-12">
                <b-form-input class="text-right" v-model="$v.tenMon.$model" :state="validateState($v.tenMon)"> </b-form-input>
              </i-form-group-check>
            </i-form-group-check>
            <div class="pt-2">
              <label>千元</label>
            </div>
          </b-form-row>
          <b-form-row>
            <i-form-group-check label="本次實際付款：" label-cols="6" content-cols="6" :hasInvalid="true" labelStar>
              <i-form-group-check label="日期：" :item="$v.payDate" label-cols="4" content-cols="8" class="col-12">
                <i-date-picker v-model="$v.payDate.$model" :state="validateState($v.payDate)"> </i-date-picker>
              </i-form-group-check>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <i-form-group-check label-cols="6" content-cols="6" :hasInvalid="true">
              <i-form-group-check label="金額：" :item="$v.payMon" label-cols="4" content-cols="8" class="col-12">
                <b-form-input class="text-right" v-model="$v.payMon.$model" :state="validateState($v.payMon)"> </b-form-input>
              </i-form-group-check>
            </i-form-group-check>
            <div class="pt-2">
              <label>千元(B) (不含契約保留款未給廠商領取部份)</label>
            </div>
          </b-form-row>
          <b-form-row>
            <b-form-group class="col-sm-12" label-cols-md="3" label-align-sm="right" content-cols-md="9" label="累計已付總金額：">
              <b-form-row>
                <b-col cols="4">
                  <b-form-input class="text-right" v-model="$v.payMonS.$model" lazy-formatter></b-form-input>
                </b-col>
                <b-col cols="2">
                  <label>千元 (C=A+B)</label>
                </b-col>
              </b-form-row>
            </b-form-group>
          </b-form-row>
          <b-form-row>
            <i-form-group-check label="付款項目：" :item="$v.payRemark" label-cols="6" content-cols="6" labelStar>
              <b-form-textarea rows="3" v-model="$v.payRemark.$model" :state="validateState($v.payRemark)"> </b-form-textarea>
            </i-form-group-check>
          </b-form-row>
          <b-button-toolbar class="float-right mt-2">
            <i-button type="save" @click="submitForm"></i-button>
            <i-button type="arrow-counterclockwise" @click="toQueryView"> </i-button>
            <i-button type="x-circle" @click="reset"> </i-button>
          </b-button-toolbar>
        </div>
      </div>
    </section>
    <!-- 錯誤訊息 -->
    <b-modal id="valid-error-modal" size="lg" title="資料驗證有誤" :header-text-variant="'light'" :header-bg-variant="'danger'" hide-footer>
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModal.hide('valid-error-modal')">了解</b-button>
    </b-modal>
    <i-pdf-viewer ref="pdfViewer" />
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { commaFormatter } from '@/shared/formatter/common';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useBvModal } from '@/shared/modal';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { required } from '@/shared/validators';
import { onBeforeMount, computed, reactive, ref, toRef, watch, onActivated } from '@vue/composition-api';

export default {
  name: 'bidAaa005Edit',
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  components: {
    bidProjectInfo,
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const tempProjectKey = {
      wkut: '',
      prjno: '',
    };
    if (bidProjectKeyProp.value) {
      tempProjectKey.wkut = bidProjectKeyProp.value.wkut;
      tempProjectKey.prjno = bidProjectKeyProp.value.prjno;
    }
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();

    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      wkut: '', // 執行機關代碼
      prjno: '', // 標案編號
      monNo: '', // 請款次別
      payMonT: '', // 應付總金額(即決標金額)
      payMonU: '', // 截至上次已付總金額
      trueDate: '', // 本次請付日期
      trueMon: '', // 本次請付金額
      tenDate: '', // 本次廠商請款單據送達機關日期
      tenMon: '', // 本次廠商請款單據送達機關金額
      payDate: '', // 本次實際付款日期
      payMon: '', // 本次實際付款金額
      payMonS: '', // 累計已付總金額
      payRemark: '', // 付款項目
    };

    // 表單物件驗證規則
    const rules = {
      wkut: { required: required }, // 執行機關代碼
      prjno: { required: required }, // 標案編號
      monNo: { required: required }, // 請款次別
      payMonT: { required: required },, // 應付總金額(即決標金額)
      payMonU: { required: required }, // 截至上次已付總金額
      trueDate: { required: required }, // 本次請付日期
      trueMon: { required: required }, // 本次請付金額
      tenDate: { required: required }, // 本次廠商請款單據送達機關日期
      tenMon: { required: required }, // 本次廠商請款單據送達機關金額
      payDate: { required: required }, // 本次實際付款日期
      payMon: { required: required }, // 本次實際付款金額
      payMonS: {}, // 累計已付總金額
      payRemark: { required: required }, // 付款項目
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const formEmpty = Object.assign({}, formDefault);
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 下拉選單選項
    const formOptions = reactive({
      type: [
        { value: 'Y', text: '是' },
        { value: 'N', text: '否' },
      ],
    });

    // 執行結果
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();
    let errorMessages = ref([]);

    const table = reactive({
      fields: [
        {
          key: 'monNo',
          label: '次別',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'trueDate',
          label: '請付日期',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => {
            var date = new Date(value);
            const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return year + '/' + month + '/' + day
          }
        },
        {
          key: 'trueMon',
          label: '請付金額',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value)
        },
        {
          key: 'tenDate',
          label: '單據送達機關日期',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => {
            var date = new Date(value);
            const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return year + '/' + month + '/' + day
          }
        },
        {
          key: 'payDate',
          label: '實際付款日期',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => {
            var date = new Date(value);
            const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return year + '/' + month + '/' + day
          }
        },
        {
          key: 'payMon',
          label: '實際付款金額',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value)
        },
        {
          key: 'payMonS',
          label: '累計已付金額',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value)
        },
        {
          key: 'action',
          label: '',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    function handleQuery(criteria) {
      table.data = undefined;
      queryStatus.value = true;
      dataPromise.value = axios
        .post('/bid-prj-pays/getPages', criteria) //後端分頁
        .then(({ data }) => {
          table.data = data.content.slice(0, data.content.length); //後端分頁y
          table.totalItems = data.totalElements; //後端分頁
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    function toEditView(bidPrjPay) {
      formStatusRef.value = formStatusEnum.MODIFY;
      createDefaultValue(bidPrjPay);
    };

    function toAddView() {
      let bidPrjPay = {
        wkut: projectInfo.wkut,
        prjno: projectInfo.prjno,
        monNo: undefined,
      };
      axios
        .post('/bid-prj-pays/getMaxMonNo', bidPrjPay) //後端分頁
        .then(({ data }) => {
          bidPrjPay.monNo = data + 1;
          Object.assign(formDefault, formEmpty);
          createDefaultValue(bidPrjPay);
          delete form['id'];
          formStatusRef.value = formStatusEnum.CREATE;
          // console.log(form);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const toQueryView = () => {
      formStatusRef.value = formStatusEnum.INFO;
      handleQuery(projectInfo);
    };

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
            if (isOK) {
              putForm();
            }
          });
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const putForm = () => {
      if (formStatusRef.value === formStatusEnum.CREATE) {
        form.createUser = 'SYS';
        form.createDate = new Date();
        proceedFrontEndDataToBackEndDTO();
        axios
          .post('/bid-prj-pays', form)
          .then(({ data }) => {
            createDefaultValue(data);
            formStatusRef.value = formStatusEnum.MODIFY;
            // console.log("insert");
          })
          .catch(err => {
            if (err.response && err.response.data.errorKey === 'bidValidError') {
              errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
              $bvModal.show('valid-error-modal');
            }
          });
      } else if (formStatusRef.value === formStatusEnum.MODIFY) {
        form.updateUser = 'SYS';
        form.updateDate = new Date();
        proceedFrontEndDataToBackEndDTO();
        axios
          .put('/bid-prj-pays', form)
          .then(({ data }) => {
            createDefaultValue(data);
            // console.log("update");
          })
          .catch(err => {
            if (err.response && err.response.data.errorKey === 'bidValidError') {
              errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
              $bvModal.show('valid-error-modal');
            }
          });
      }
    };

    function proceedFrontEndDataToBackEndDTO() {
      const value = new Date();
      const offset = value.getTimezoneOffset();
      form.trueDate = form.trueDate ? new Date(form.trueDate.getTime() - offset * 60 * 1000) : null;
      form.tenDate = form.tenDate ? new Date(form.tenDate.getTime() - offset * 60 * 1000) : null;
      form.payDate = form.payDate ? new Date(form.payDate.getTime() - offset * 60 * 1000) : null;
      form.createDate = form.createDate ? new Date(form.createDate.getTime() - offset * 60 * 1000) : null;
      form.updateDate = form.updateDate ? new Date(form.updateDate.getTime() - offset * 60 * 1000) : null;
    }

    const createDefaultValue = (data: BidPrjPay) => {
      if (data.trueDate != null) {
        data.trueDate = new Date(data.trueDate);
      }
      if (data.tenDate != null) {
        data.tenDate = new Date(data.tenDate);
      }
      if (data.payDate != null) {
        data.payDate = new Date(data.payDate);
      }
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      reset();
    };

    function toTableA() {
      axios
        .post('/bid-prj-pay/bidAae009A', projectInfo, {responseType: 'blob'})
        .then(res => {
          let blob = new Blob([res.data], { type: "application/pdf" });
          let url = window.URL.createObjectURL(blob);
          this.$refs.pdfViewer.pdfSrc = url;
          this.$refs.pdfViewer.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    }

    function toTableB(bidPrjPay) {
      axios
        .post('/bid-prj-pay/bidAae009B', bidPrjPay, {responseType: 'blob'})
        .then(res => {
          let blob = new Blob([res.data], { type: "application/pdf" });
          let url = window.URL.createObjectURL(blob);
          this.$refs.pdfViewer.pdfSrc = url;
          this.$refs.pdfViewer.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    }

    function toTableC(bidPrjPay) {
      axios
        .post('/bid-prj-pay/bidAae009C', bidPrjPay, {responseType: 'blob'})
        .then(res => {
          let blob = new Blob([res.data], { type: "application/pdf" });
          let url = window.URL.createObjectURL(blob);
          this.$refs.pdfViewer.pdfSrc = url;
          this.$refs.pdfViewer.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          handleQuery(newValue);
        }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      formStatusEnum,
      formStatusRef,
      formDefault,
      $v,
      reset,
      formOptions,
      validateState,
      queryStatus,
      dataPromise,
      table,
      handleQuery,
      toEditView,
      toQueryView,
      submitForm,
      toAddView,
      errorMessages,
      toTableA,
      toTableB,
      toTableC,
    };
  },
  filters: {
    yesNoFilter: (value: string) => {
      if (value === 'Y') {
        return '是';
      } else {
        return '否';
      }
    },
    dateToString: (value: string) => {
      var date = new Date(value);
      const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      return year + '年' + month + '月' + day + '日';
    },
  },
};
</script>
<style></style>

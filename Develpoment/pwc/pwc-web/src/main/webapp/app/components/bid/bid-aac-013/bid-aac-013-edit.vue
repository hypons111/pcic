<template>
  <div>
    <section class="container">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">工地型預拌混凝土通報詳細資料</h5>
        </div>
        <div class="card-body">
          <div class="card-body">
            <b-collapse v-model="stepVisible.queryRow">
              <!-- 同意日期 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="2"
                  :label="$t('label.agreeDate') + ':'"
                  :item="$v.agreeDate"
                  :labelStar="true"
                >
                  <b-form-row>
                    <i-date-picker
                      v-model="$v.agreeDate.$model"
                      trim
                      placeholder="yyy/MM/dd"
                      :disabled-date="notAfterSdateEnd"
                    ></i-date-picker>
                    <!-- <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.agreeDate.$model | dateToString }}</span> -->
                  </b-form-row>
                </i-form-group-check>
              </b-form-row>
              <!-- 同意文號 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="3"
                  :label="$t('label.agreeNum') + ':'"
                  :item="$v.agreeNum"
                  :labelStar="true"
                >
                  <b-form-input class="col-12" v-model="$v.agreeNum.$model" :state="validateState($v.agreeNum)"></b-form-input>
                </i-form-group-check>
              </b-form-row>
              <!-- 同意理由 -->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="$t('label.agreeReson') + ':'">
                  <b-form-select v-model="$v.agreeReson.$model" :options="queryOptions.bid050"></b-form-select>
                  <!-- <span v-if="formStatusRef === FormStatusEnum.READONLY">{{
                    formOptionsFormatter(queryOptions.BID_050, $v.agreeReson.$model)
                  }}</span> -->
                </i-form-group-check>
              </b-form-row>
              <!-- 預定生產數量 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="10"
                  :label="$t('label.agreeValue') + ':'"
                  :item="$v.agreeValue"
                >
                  <div class="d-inline">
                    <b-form-input class="col-12" v-model="$v.agreeValue.$model" :state="validateState($v.agreeValue)"></b-form-input>
                  </div>
                  <div class="col-8">
                    <div class="d-inline">立方公尺</div>
                  </div>
                </i-form-group-check>
              </b-form-row>
              <!-- 拆除完畢日期 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="2"
                  :label="$t('label.tearDownDate') + ':'"
                  :item="$v.tearDownDate"
                >
                  <b-form-row>
                    <i-date-picker
                      v-model="$v.tearDownDate.$model"
                      trim
                      placeholder="yyy/MM/dd"
                      :disabled-date="notAfterSdateEnd"
                    ></i-date-picker>
                    <!-- <span v-if="formStatusRef === FormStatusEnum.READONLY || FormStatusEnum.MODIFY">{{
                      $v.tearDownDate.$model | dateToString
                    }}</span> -->
                  </b-form-row>
                </i-form-group-check>
              </b-form-row>
              <!-- 累計生產數量 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="10"
                  :label="$t('label.totalValue') + ':'"
                  :item="$v.totalValue"
                >
                  <div class="d-inline">
                    <b-form-input class="col-12" v-model="$v.totalValue.$model" :state="validateState($v.totalValue)"></b-form-input>
                  </div>
                  <div class="col-8">
                    <div class="d-inline">立方公尺</div>
                  </div>
                </i-form-group-check>
              </b-form-row>
              <!-- 生產數量差異說明 -->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="$t('label.valueRemark') + ':'">
                  <b-form-textarea v-model="$v.valueRemark.$model" trim id="textarea-rows" placeholder="" rows="4"></b-form-textarea>
                  <!-- <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.valueRemark.$model }}</span> -->
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <b-col offset="4">
                  <i-button type="check-circle" @click="toSubmit"></i-button>
                  <i-button type="x-circle" @click="reset"></i-button>
                  <i-button size="md" type="arrow-counterclockwise" @click="toQueryView"></i-button>
                </b-col>
              </b-form-row>
            </b-collapse>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { reactive, ref, toRef, watch, Ref, onMounted } from '@vue/composition-api';
import { required } from '@/shared/validators';
import { handleBack } from '@/router/router';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';

export default {
  name: 'bidAac013Edit',
  props: {
    bidProjectKeyProp: {
      type: Object,
      required: false,
    },
    // 原為 ref(FormStatusEnum) 傳過來會變物件 { "value": "xxx" }，目的為這樣更改value值就不會被Vue警告(避免變異父元件資料)
    formStatus: {
      required: false,
      type: String,
    },
  },
  components: {
    bidProjectInfo,
  },

  setup(props, context) {
    const bringValueProp = toRef(props, 'bidProjectKeyProp');
    const formStatusRef = toRef(props, 'formStatus'); // toRef會保持對來源property的響應式連接，但改變父元件資料會被Vue警告，所以這邊改用ref複製一份新的資料
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();
    const admSysCodeService = new AdmSysCodeService();

    onMounted(() => {
      getAllSysCodes();
    });
    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
    }

    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
          result.typeCode = data;
          Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        });
      });
    }

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      agreeDate: '', //同意日期
      agreeNum: '', //同意文號
      agreeReson: '', //同意理由
      agreeValue: '', //預定生產數量
      tearDownDate: '', //拆除完畢日期
      totalValue: '', //累計生產數量
      valueRemark: '', //生產數量差異說明
    };

    // 表單物件驗證規則
    const rules = {
      agreeDate: { notnull: required },
      agreeNum: { notnull: required },
      agreeReson: {},
      agreeValue: {},
      tearDownDate: {},
      totalValue: {},
      valueRemark: {},
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 下拉選單選項
    const queryOptions = reactive({
      bid050: [],
    });

    //下拉試選單響應試
    const ccutnitoptions = ref([]);
    const ccutnitoptionss = ref([]);

    // 執行結果
    // const queryStatus = ref(false);
    const dataPromise = ref(null);

    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const stepVisible = reactive({
      queryRow: true,
    });

    // const table = reactive({
    //   fields: [
    //     {
    //       //裁處日期
    //       key: 'sdate',
    //       label: i18n.t('label.sdate'),
    //       thClass: 'text-center',
    //       tdClass: 'text-center align-middle',
    //       thStyle: 'width:8%',
    //     },
    //     {
    //       //裁處主旨
    //       key: 'prmTitle',
    //       label: i18n.t('label.prmTitle'),
    //       thClass: 'text-center',
    //       tdClass: 'text-left align-middle',
    //       thStyle: 'width:50%',
    //     },
    //     {
    //       //違反日期
    //       key: 'violateDate',
    //       label: i18n.t('label.violateDate'),
    //       thClass: 'text-center',
    //       tdClass: 'text-left align-middle',
    //       thStyle: 'width:8%',
    //     },
    //     {
    //       //違反事實
    //       key: 'violateFact',
    //       label: i18n.t('label.violateFact'),
    //       thClass: 'text-center',
    //       tdClass: 'text-left align-middle',
    //       thStyle: 'width:10%',
    //     },
    //     {
    //       key: 'action',
    //       label: '功能',
    //       thClass: 'text-center',
    //       tdClass: 'text-center align-middle',
    //       thStyle: 'width:20%',
    //     },
    //   ],
    //   data: undefined,
    //   totalItems: 0,
    // });

    const toSubmit = () => {
      const value = new Date();
      const offset = value.getTimezoneOffset();
      form.createDate = new Date();
      form.createDate = new Date(form.createDate.getTime() - offset * 60 * 1000);
      form.createUser = userIdentity;

      let criteria = {
        wkut: bringValueProp.value.wkut,
        prjno: bringValueProp.value.prjno,
      };

      Object.assign(form, criteria);
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          console.log('formform:');
          dataPromise.value = axios
            .post('/bid-prj-segmsets', form)
            .then(({ data }) => {
              console.log('datadatadata:', data);
              createDefaultValue(data);
              isReload.value = true;
              $bvModal.msgBoxOk('新增成功!');
              // navigateByNameAndParams('bidAad003Detail', { form, formStatus: FormStatusEnum.MODIFY, isNotKeepAlive: false });
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //受處分單位下拉式選單
    // const ctUnitOptions = (wkut, prjno) => {
    //   const projectPK = {
    //     wkut: wkut,
    //     prjno: prjno,
    //   };
    //   dataPromise.value = axios
    //     .post('/bid-prj-find-ctunit', projectPK)
    //     .then(({ data }) => {
    //       ccutnitoptionss.value.slice(0, data.values.length);
    //       data.forEach(ele => {
    //         const responOptions = { value: '', text: '' };
    //         if (ele.wkut != null) {
    //           responOptions.value = ele.wkut;
    //           responOptions.text = ele.name;
    //           ccutnitoptionss.value.push(responOptions);
    //         } else if (ele.type === '3') {
    //           responOptions.value = ele.ccut;
    //           responOptions.text = '承攬廠商--' + ele.name;
    //           ccutnitoptionss.value.push(responOptions);
    //         } else if (ele.type === '2') {
    //           responOptions.value = ele.scut;
    //           responOptions.text = '監造單位--' + ele.name;
    //           ccutnitoptionss.value.push(responOptions);
    //         } else if (ele.pcmut != null) {
    //           responOptions.value = ele.pcmut;
    //           responOptions.text = ele.name;
    //           ccutnitoptionss.value.push(responOptions);
    //         } else if (ele.subccut != null) {
    //           responOptions.value = ele.subccut;
    //           responOptions.text = ele.name;
    //           ccutnitoptionss.value.push(responOptions);
    //         }
    //       });
    //     })
    //     .finally(() => (dataPromise.value = null))
    //     .catch(notificationErrorHandler(notificationService));
    // };

    //下拉式選單
    const getAllSysCodes = () => {
      admSysCodeService.setFormOptions(queryOptions, 'BID');
    };

    const toQueryView = () => {
      let bidProjectKey = {
        wkut: bringValueProp.value.wkut,
        prjno: bringValueProp.value.prjno,
      };
      navigateByNameAndParams('bidAac013Info', { bidProjectKey, isNotKeepAlive: false });
    };

    const createDefaultValue = data => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      reset();
    };

    //限制能選的日期
    function notAfterSdateEnd(date: Date) {
      return date > new Date();
    }

    watch(
      bringValueProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          bringValueProp.value.agreeDate = new Date(bringValueProp.value.agreeDate);
          bringValueProp.value.tearDownDate = new Date(bringValueProp.value.tearDownDate);
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);

          // ctUnitOptions(newValue.wkut, newValue.prjno);
          createDefaultValue(newValue);
        }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      formDefault,
      $v,
      reset,
      validateState,
      dataPromise,
      // table,
      queryOptions,
      toQueryView,
      createDefaultValue,
      FormStatusEnum,
      formStatusRef,
      toSubmit,
      getAllSysCodes,
      ccutnitoptionss,
      formOptionsFormatter,
      stepVisible,
      notAfterSdateEnd,
    };
  },
  filters: {
    dateToString: (value: string) => {
      var date = new Date(value);
      const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      return year + '年' + month + '月' + day + '日';
    },
    responsIdToName: (value: string) => {},
  },
};
</script>
<style></style>

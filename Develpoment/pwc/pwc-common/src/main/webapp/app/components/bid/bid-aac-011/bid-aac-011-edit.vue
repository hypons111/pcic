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
            <!-- 採用方式 -->
            <b-row>
              <i-form-group-check :label="$t('label.useType') + ':'" :item="$v.useType" :labelStar="true">
                <div class="d-flex h-38p align-items-center">
                  <b-form-radio-group v-model="$v.useType.$model">
                    <b-form-radio value="A">監督付款</b-form-radio>
                    <b-form-radio value="B">連帶保證</b-form-radio>
                  </b-form-radio-group>
                </div>
              </i-form-group-check>
            </b-row>
            <!-- 機關同意日期 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="2"
                :label="$t('label.startDate') + ':'"
                :item="$v.startDate"
                :labelStar="true"
              >
                <b-form-row>
                  <i-date-picker
                    v-model="$v.startDate.$model"
                    trim
                    placeholder="yyy/MM/dd"
                    :disabled-date="notAfterSdateEnd"
                  ></i-date-picker>
                  <!-- <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.agreeDate.$model | dateToString }}</span> -->
                </b-form-row>
              </i-form-group-check>
            </b-form-row>
            <!-- 核定文號 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="3"
                :label="$t('label.frPno') + ':'"
                :item="$v.prmNo"
                :labelStar="true"
              >
                <b-form-input class="col-12" v-model="$v.prmNo.$model" :state="validateState($v.prmNo)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!-- 後續執行之分包或連帶保證廠商-->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="10"
                :label="$t('label.nccUnit') + ':'"
                :item="$v.nccUnit"
              >
                <b-form-input class="col-2" v-model="$v.nccUnit.$model" :state="validateState($v.nccUnit)"></b-form-input>
                <b-col class="col-6">
                  <bid-unit10-name :orgId="$v.nccUnit.$model" />
                  <b-button variant="outline-dark" @click="showFirmModal">廠商統編查詢</b-button>
                </b-col>
              </i-form-group-check>
            </b-form-row>
            <!-- 繼續施工之工料款 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="10"
                :label="$t('label.lctSum') + ':'"
                :item="$v.lctSum"
              >
                <div class="d-inline">
                  <b-form-input class="col-12" v-model="$v.lctSum.$model" :state="validateState($v.lctSum)"></b-form-input>
                </div>
                <div class="col-8">
                  <div class="d-inline">千元</div>
                </div>
              </i-form-group-check>
            </b-form-row>
            <!-- 採監督付款或連帶保證原因說明 -->
            <b-form-row>
              <i-form-group-check
                class="col-sm-12"
                label-cols-md="2"
                content-cols-md="10"
                :label="$t('label.remark1') + ':'"
                :labelStar="true"
              >
                <b-form-textarea
                  v-model="$v.remark.$model"
                  trim
                  id="textarea-rows"
                  placeholder=""
                  rows="4"
                  :state="validateState($v.remark)"
                ></b-form-textarea>
                <!-- <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.remark.$model }}</span> -->
              </i-form-group-check>
            </b-form-row>
            <div class="text-center">
              <i-button type="check-circle" @click="toSubmit"></i-button>
              <i-button type="x-circle" @click="reset"></i-button>
              <i-button size="md" type="arrow-counterclockwise" @click="toQueryView"></i-button>
            </div>
          </div>
        </div>
      </div>
    </section>
    <bid-unit10-list ref="bidUnit10List" />
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
import BidUnit10List from '@/components/bid/bid-common-component/bid-unit10-list.vue';
import BidUnit10Name from '@/components/bid/bid-common-component/bid-unit10-name.vue';
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
  name: 'bidAac011Edit',
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
    BidUnit10List,
    BidUnit10Name,
    
  },

  setup(props, context) {
    const bringValueProp = toRef(props, 'bidProjectKeyProp');
    const formStatusRef = toRef(props, 'formStatus'); // toRef會保持對來源property的響應式連接，但改變父元件資料會被Vue警告，所以這邊改用ref複製一份新的資料
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();
    const admSysCodeService = new AdmSysCodeService();
    

    onMounted(() => {
      getAllSysCodes();
      initDefaultValue();
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
      useType: '', //採用方式
      startDate: '', //機關同意日期
      prmNo: '', //核定文號
      nccUnit: '', //後續執行之分包或連帶保證廠商
      lctSum: '', //繼續施工之工料款
      remark: '', //採監督付款或連帶保證原因說明
      createDate: '',
      createUser: '',
    };

    // 表單物件驗證規則
    const rules = {
      useType: { notnull: required },
      startDate: { notnull: required },
      prmNo: { notnull: required },
      nccUnit: {},
      lctSum: {},
      remark: { notnull: required },
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const bidUnit10List = ref(null);
    const pccOrgId = ref(useGetters(['account']).account.value).value.pccOrgId;


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
            .post('/bid-prj-gdfks', form)
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



     function showFirmModal() {
      const queryKey = reactive({
         type: '1';
      });
      bidUnit10List.value.title = '廠商代碼查詢';
      bidUnit10List.value.isShowDia(queryKey, true);
    }


    function initDefaultValue() {
      pccOrgId = ref(useGetters(['account']).account.value).value.pccOrgId
      bidUtilService.findBidManageUnit(pccOrgId).then(returnArray => {
        depCodeOpts.splice(0, depCodeOpts.length);
        if (returnArray && returnArray.length > 0) {
          returnArray.forEach(ele => {
            const item = { value: '', text: '' };
            item.value = ele.code;
            item.text = ele.name;
            depCodeOpts.push(item);
          });
        }
      });
      bidUtilService.findBidManageUnit(pccOrgId).then(returnArray => {
        bosCodeOpts.splice(0, bosCodeOpts.length);
        if (returnArray && returnArray.length > 0) {
          returnArray.forEach(ele => {
            const item = { value: '', text: '' };
            item.value = ele.code;
            item.text = ele.name;
            bosCodeOpts.push(item);
          });
        }
      });
      bidUtilService.findBidManageUnit(pccOrgId).then(returnArray => {
        bos2CodeOpts.splice(0, bos2CodeOpts.length);
        if (returnArray && returnArray.length > 0) {
          returnArray.forEach(ele => {
            const item = { value: '', text: '' };
            item.value = ele.code;
            item.text = ele.name;
            bos2CodeOpts.push(item);
          });
        }
      });
    }

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
      navigateByNameAndParams('bidAac011Info', { bidProjectKey, isNotKeepAlive: false });
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
          bringValueProp.value.startDate = new Date(bringValueProp.value.startDate);
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
      showFirmModal,
      bidUnit10List,
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

<template>
  <div>
    <section class="container mt-2" v-if="roleRef === RoleEnum.ADMIN">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-11">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <!-- 姓名 -->
              <i-form-group-check :label="$t('label.chName') + '：'" :item="$v.chName">
                <b-form-input v-model="$v.chName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 身分證字號/居留證號 -->
              <i-form-group-check :label="$t('label.idno') + '：'" :item="$v.idno">
                <b-form-input v-model="$v.idno.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 出生年月日 -->
              <i-form-group-check :label="$t('label.birthDate') + '：'" :item="$v.birthDate">
                <b-form-input v-model="$v.birthDate.$model" type='number' trim></b-form-input>*民國年(例089/01/21)
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 技師證書字號 -->
              <i-form-group-check :label="$t('label.engrCertificateDocNo') + '：技證字第'" :item="$v.engrCertificateDocNo">
                <b-form-input v-model="$v.engrCertificateDocNo.$model" type='number' trim></b-form-input>號
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 驗證碼 -->
              <i-form-group-check :label="$t('label.verificationCode') + '：'" :item="$v.verificationCode">
                <b-form-input v-model="$v.verificationCode.$model" trim placeholder="點擊圖片可變換驗證碼"></b-form-input>
              </i-form-group-check>
              <i-form-group-check label-cols-md="1" content-cols-md="8">
              <div @click="refreshCode" >
                <SIdentify :identifyCodeDefault="identifyCodeRef"></SIdentify>   
              </div>
            </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="handleQueryBtn"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2" v-show="queryStatus">

      <div class="card-body">
        <b-form-row>
          <!-- 證書字號 -->
          <i-form-group-check content-cols="4" :label="$t('label.engrCertificateDocNo') + '：'" :item="$v.engrCertificateDocNo1">
            {{ data.engrCertificateDocNo1 }}
            <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.engrCertificateDocNo1.$model }}</div>
            <b-form-input v-else v-model="$v.engrCertificateDocNo1.$model" :state="validateState($v.engrCertificateDocNo1)" trim lazy> </b-form-input> -->
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 姓名 -->
          <i-form-group-check content-cols="4" :label="$t('label.chName') + '：'" :item="$v.chName1">
            {{ data.chName1 }}
            <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.chName1.$model }}</div> -->
            <!-- <b-form-input v-else v-model="$v.chName1.$model" :state="validateState($v.chName1)" trim lazy> </b-form-input> -->
          </i-form-group-check>
          <!-- 性別 -->
          <i-form-group-check content-cols="4" :label="$t('label.sex') + '：'" :item="$v.sex">
            {{ data.sex }}
            <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.sex.$model }}</div> -->
            <!-- <b-form-input v-else v-model="$v.sex.$model" :state="validateState($v.sex)" trim lazy> </b-form-input> -->
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 身分證/中華民國居留證 -->
          <i-form-group-check content-cols="4" :label="$t('label.idno') + '：'" :item="$v.idno1">
            {{ data.idno1 }}
            <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.idno1.$model }}</div>
            <b-form-input v-else v-model="$v.idno1.$model" :state="validateState($v.idno1)" trim lazy> </b-form-input> -->
          </i-form-group-check>
          <!-- 出生日期 -->
          <i-form-group-check content-cols="4" :label="$t('label.birthDate') + '：'" :item="$v.birthDate1">
            {{ data.birthDate1 }}
            <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.birthDate1.$model }}</div>
            <b-form-input v-else v-model="$v.birthDate1.$model" :state="validateState($v.birthDate1)" trim lazy> </b-form-input> -->
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <!-- 科別 -->
          <i-form-group-check class="col-12" label-cols="2" content-cols="10" :label="$t('label.subject') + '：'" :item="$v.subject">
            {{ data.subject }}
              <!-- <b-form-checkbox-group
                v-if="formStatusRef !== FormStatusEnum.READONLY"
                v-model="$v.subject.$model"
                :options="formOptions.subject"
                :state="validateState($v.subject)"
              ></b-form-checkbox-group>
              <div v-else>{{ $v.subject.$model }}</div> -->
            </i-form-group-check>
        </b-form-row>
      </div>
      <!-- <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged" 
      >
      </i-table> -->
    </section>
      <!-- <div class="text-center mb-2"> -->
        <!-- 返回 -->
        <!-- <i-button type="arrow-left" @click="toQueryView" />
      </div> -->
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import i18n from '@/shared/i18n';
import { onMounted, onActivated, watch, reactive, Ref, ref, toRef, inject, toRefs, unref } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import { useBvModal } from '@/shared/modal';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { IEng0601 } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import SIdentify from '@/shared/form/identify.vue';
import { handleBack } from '@/router/router';

export default {
  name: 'Eng0823Query',
  components: {
    SIdentify
  },
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {

    const iTableComponent = ref(null);

    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    const isReload = ref(false);

    const identifyCode = "";
    const identifyCodes = "1234567890";
    const { engEngrSubjectpoint, formStatus, criteria } = toRefs(props);
    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    // const isReloadProp = computed(() => props.isReload);
    const isReloadProp = toRef(props, 'isReload');

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    // modal物件
    // const $bvModal = useBvModal();
    
    const formOptions = reactive({
      subject: [],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      chName: '',
      idno: '',
      birthDate: null,
      engrCertificateDocNo: '',
      verificationCode: '',
      identifyCodeA:'',//驗證碼(圖)
      engrCertificateDocNo1: '',
      chName1: '',
      sex: '',
      idno1: '',
      birthDate1: null,
      subject: '',
    };

    // 表單物件驗證規則
    const rules = {
      chName: {},
      idno: {},
      birthDate: {},
      engrCertificateDocNo: {},
      verificationCode: {},
      engrCertificateDocNo1: {},
      chName1: {},
      sex: {},
      idno1: {},
      birthDate1: {},
      subject: {},
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    //$v : 抓取上面v-model的值、checkValidity:驗證、reset:清空頁面上的參數,rules:規則、form:新值、formDefault:預設值
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      // handleQuery();
      makeCode(identifyCodes, 4);
    //   getAllSysCodes();
    });

    watch(roleRef, () => {
      if (roleRef.value === RoleEnum.USER) {
        handleQuery();
      } else {
        queryStatus.value = false;
        // table.data = undefined;
      }
    });

    // function getAllSysCodes() {
    //   formOptions.Status = [
    //     { value: '0', text: '暫存' },
    //     { value: '1', text: '批退' },
    //     { value: '3', text: '初審中' },
    //     { value: '4', text: '複審中' },
    //     { value: '5', text: '已結案' },
    //     { value: '6', text: '作廢' },
    //   ];
    // }

    let identifyCodeRef = ref('');
    //驗證碼
    function randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    }

    //重新產生驗證碼 
    function refreshCode() {
      identifyCode = "";
      makeCode(identifyCodes, 4);
    }

    //驗證碼
    function makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        identifyCode += identifyCodes[
          randomNum(0, identifyCodes.length)
        ];
      }
      identifyCodeRef.value = identifyCode;
      form.identifyCodeA = identifyCodeRef.value;
    }
    function toQueryView() {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const handleQueryBtn = () => {
      // iTableComponent.value.state.pagination.currentPage = 1;
      // iTableComponent.value.emitPagination();
      handleQuery();
    };

    const toReadView = () => {
        navigateByNameAndParams('Eng0601Authorization', {
        });
      };
    // const toEditView = (entity: IEng0601, formStatus: FormStatusEnum, index?: number) => {
    //     const criteria = Object.assign({}, form);
    //     criteria['index'] = index;
    //     criteria['totalItems'] = table.totalItems;

    //     navigateByNameAndParams('Eng0816EditInfo', {
    //       engEngrSubjectpoint: entity,
    //       formStatus: formStatus,
    //       criteria: criteria,
    //     });
    //   };

    const table = reactive({
      fields_backend: [
        {}
      ],
      data: undefined,
      totalItems: undefined,
    });

    const data = {
      engrCertificateDocNo1: '技證字第002386號',
      chName1: '余瑞春',
      sex: '男',
      idno1: 'G123456789',
      birthDate1: '民國54年12月15日',
      subject: '冷凍空調工程',
    }
    const handleQuery = () => {

      //驗證
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          // table.data = undefined;
          queryStatus.value = true;
          // table.data = [
          //   {
          //     no: '1',
          //     engrLicenseDocNo: '技執字第002990號',
          //     effectDateStart: '2027/05/26',
          //     effectDateEnd: '',
          //     chName1: '童炳申',
          //     CompChName: '元大有限公司',
          //     compAddrZip: '',
          //     compAddrCity: '桃園市',
          //     compAddrTown: '桃園區',
          //     compAddrOther: '縣府路286之4號5樓',
          //     publicDate: '2021/04/30',
          //     applyitemslist: '換照',
          //     subject: '水土保持科',
          //     engrCertificateDocNo: '台工登字第10396號',
          //   },
          // ];
          // table.totalItems = 1;
          // axios
          //   // .post('/cms-comps/criteria', form)           //前端分頁
          //   .post('/eng-engr-certificates/criteria-jpa', form) //後端分頁
          //   .then(({ data }) => {
          //     // table.data = data.slice(0, data.length);   //前端分頁
          //     // table.totalItems = data.length;            //前端分頁
          //     table.data = data.content.slice(0, data.content.length); //後端分頁
          //     table.totalItems = data.totalElements; //後端分頁
          //   })
          //   .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    return {
      iTableComponent,
      validateState,
      formOptions,
      $v,
      stepVisible,
      queryStatus,
      // table,
      reset,
      handleQueryBtn,
      // toEditView,
      toQueryView,
      toReadView,
      roleRef,
      FormStatusEnum,
      RoleEnum,
      ref,
      formOptionsFormatter,
      handlePaginationChanged,
      refreshCode,
      identifyCodeRef,
      data,
    };
  },
};
</script>
<style>
</style>
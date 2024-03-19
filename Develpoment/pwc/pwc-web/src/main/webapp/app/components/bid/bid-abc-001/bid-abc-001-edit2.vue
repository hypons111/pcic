<template>
  <div>
    <div class="card">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </div>

    <div class="card">
      <b-row class="card-header">
        <b-col cols="4">
          <h5 class="m-0">查核(督導)紀錄:序號1</h5>
        </b-col>
        <b-col cols="4"> 查核日期:110/09/01</b-col>
      </b-row>
      <div class="card-header">
        <h5 class="m-0">查核委員編輯</h5>
      </div>
      <div class="card-body col-12">
        <b-table-simple hover small border caption-top responsive>
          <caption></caption>
          <b-thead> </b-thead>
          <b-tbody>
            <b-tr>
              <b-td>
                <!-- 委員姓名-->
                <b-form-row>
                  <i-form-group-check class="col-sm-12" label-cols-md="3" content-cols-md="4" :label="'委員姓名:'" :item="$v.peoName">
                    <b-form-input v-model="$v.peoName.$model" :state="validateState($v.peoName)"></b-form-input>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
              <b-td>
                <!-- 委員Email-->
                <b-form-row>
                  <i-form-group-check label-cols-md="6" content-cols-md="6" :label="'委員Email:'" :item="$v.peoMail">
                    <b-form-input v-model="$v.peoMail.$model" :state="validateState($v.peoMail)"></b-form-input>
                  </i-form-group-check>
                  <i-form-group-check :item="$v.ynsend">
                    <b-form-radio-group content-cols-md="12" v-model="$v.ynsend.$model">
                      <b-form-radio value="A">寄送</b-form-radio>
                      <b-form-radio value="B">不寄</b-form-radio>
                    </b-form-radio-group>
                  </i-form-group-check></b-form-row
                >
              </b-td>
            </b-tr>
            <b-tr>
              <!-- 內外聘-->
              <i-form-group-check :label="'內外聘'" :item="$v.inOut">
                <b-form-radio-group content-cols-md="12" v-model="$v.inOut.$model">
                  <b-form-radio value="A">內聘</b-form-radio>
                  <b-form-radio value="B">外聘</b-form-radio>
                </b-form-radio-group>
              </i-form-group-check>
            </b-tr>
            <b-tr>
              <b-td>
                <!-- 品質管理制度-->
                <b-form-row>
                  <i-form-group-check
                    class="col-sm-12"
                    label-cols-md="4"
                    content-cols-md="6"
                    :label="'品質管理制度:(Q=20分)'"
                    :item="$v.scorea"
                  >
                    <b-form-input class="col-sm-5" v-model="$v.scorea.$model" :state="validateState($v.scorea)"></b-form-input>
                    <div class="col-sm-6"><span style="color: red">(Q=QA+QB)</span></div>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
              <b-td>
                <!-- 'A、主辦機關、專案管理廠商、監造單位(10分)-->
                <b-form-row>
                  <i-form-group-check
                    class="col-sm-12"
                    label-cols-md="6"
                    content-cols-md="6"
                    :label="'A、主辦機關、專案管理廠商、監造單位(10分):'"
                    :item="$v.scorea1"
                  >
                    <b-form-input class="col-sm-3" v-model="$v.scorea1.$model" :state="validateState($v.scorea1)"></b-form-input
                    ><span class="col-sm-9" style="color: red">(QA=QA1+QA2)</span>
                  </i-form-group-check>
                </b-form-row>
                <!-- '主辦機關、專案管理廠商(5分)-->
                <b-form-row>
                  <i-form-group-check
                    class="col-sm-12"
                    label-cols-md="6"
                    content-cols-md="6"
                    :label="'主辦機關、專案管理廠商(5分):'"
                    :item="$v.scorea1a"
                  >
                    <b-form-input class="col-sm-3" v-model="$v.scorea1a.$model" :state="validateState($v.scorea1a)"></b-form-input
                    ><span class="col-sm-9" style="color: blue">(QA1)</span>
                  </i-form-group-check>
                </b-form-row>
                <!-- '監造單位(5分)-->
                <b-form-row>
                  <i-form-group-check class="col-sm-12" label-cols-md="6" content-cols-md="6" :label="'監造單位(5分):'" :item="$v.scorea1e">
                    <b-form-input class="col-sm-3" v-model="$v.scorea1e.$model" :state="validateState($v.scorea1e)"></b-form-input
                    ><span class="col-sm-6" style="color: blue">(QA2)</span>
                  </i-form-group-check>
                </b-form-row>
                <!-- B、承攬廠商(10分) -->
                <b-form-row>
                  <i-form-group-check
                    class="col-sm-12"
                    label-cols-md="6"
                    content-cols-md="6"
                    :label="'B、承攬廠商(10分):'"
                    :item="$v.scorea2"
                  >
                    <b-form-input class="col-sm-3" v-model="$v.scorea2.$model" :state="validateState($v.scorea2)"></b-form-input
                    ><span class="col-sm-9" style="color: blue">(QB)</span>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
            </b-tr>

            <b-tr>
              <b-td>
                <!-- 施工品質-->

                <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="6" :label="'施工品質:(W=60分)'" :item="$v.scoreb">
                  <b-form-input class="col-sm-5" v-model="$v.scoreb.$model" :state="validateState($v.scoreb)"></b-form-input
                  ><span class="col-sm-6" style="color: red">(W=W1+W2+W3)</span>
                </i-form-group-check>
              </b-td>
              <b-td>
                <!-- 'A、混凝土、鋼筋、模板、土方、結構體、裝修、雜項等(40分)-->
                <b-form-row>
                  <i-form-group-check
                    class="col-sm-12"
                    label-cols-md="6"
                    content-cols-md="6"
                    :label="'A、混凝土、鋼筋、模板、土方、結構體、裝修、雜項等(40分):'"
                    :item="$v.scoreb1"
                  >
                    <b-form-input class="col-sm-3" v-model="$v.scoreb1.$model" :state="validateState($v.scoreb1)"></b-form-input
                    ><span class="col-sm-9" style="color: blue">(W1)</span>
                  </i-form-group-check>
                </b-form-row>
                <!-- 'B、材料設備檢驗與管制(10分)-->
                <b-form-row>
                  <i-form-group-check
                    class="col-sm-12"
                    label-cols-md="6"
                    content-cols-md="6"
                    :label="'B、材料設備檢驗與管制(10分):'"
                    :item="$v.scoreb2"
                  >
                    <b-form-input class="col-sm-3" v-model="$v.scoreb2.$model" :state="validateState($v.scoreb2)"></b-form-input
                    ><span class="col-sm-9" style="color: blue">(QA2)</span>
                  </i-form-group-check>
                </b-form-row>
                <!-- 'C、施工安全衛生(10分)-->
                <b-form-row>
                  <i-form-group-check
                    class="col-sm-12"
                    label-cols-md="6"
                    content-cols-md="6"
                    :label="'C、施工安全衛生(10分):'"
                    :item="$v.scoreb3"
                  >
                    <b-form-input class="col-sm-3" v-model="$v.scoreb3.$model" :state="validateState($v.scoreb3)"></b-form-input>
                    <span class="col-sm-6" style="color: blue">(W3)</span>
                  </i-form-group-check>
                </b-form-row>
              </b-td>
            </b-tr>
            <b-tr>
              <!-- '施工進度-->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="6" :label="'施工進度:(p=20分)'" :item="$v.scoreb3">
                  <b-form-input class="col-sm-5" v-model="$v.scoreb3.$model" :state="validateState($v.scoreb3)"></b-form-input
                  ><span class="col-sm-6" style="color: blue">(P)</span>
                </i-form-group-check>
              </b-form-row>
            </b-tr>
            <b-tr>
              <!-- '評分-->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="4" content-cols-md="6" :label="'評分:'" :item="$v.scoreb3">
                  <b-form-input class="col-sm-5" v-model="$v.scoreb3.$model" :state="validateState($v.scoreb3)"></b-form-input
                  ><span class="col-sm-6" style="color: red">(Q+W+P)</span>
                </i-form-group-check>
              </b-form-row>
            </b-tr>
          </b-tbody>
        </b-table-simple>
        <b-row>
          <b-col cols="6">
            <b-table-simple hover small border>
              <b-thead>
                <b-tr>
                  <b-th style="context: center">五項指標</b-th>
                </b-tr>
              </b-thead>
              <b-tbody>
                <b-tr>
                  <!-- 1.環境指標(W4=100分) -->
                  <b-form-row>
                    <i-form-group-check
                      class="col-sm-12"
                      label-cols-md="3"
                      content-cols-md="9"
                      :label="'1.環境指標(W4=100分)'"
                      :item="$v.fgop1"
                    >
                      <b-form-input class="col-sm-5" v-model="$v.fgop1.$model" :state="validateState($v.fgop1)"></b-form-input
                      ><span class="col-sm-6" style="color: blue">(W4)</span>
                    </i-form-group-check>
                  </b-form-row>
                  <!-- 2.安全指標(W8=100分) -->
                  <b-form-row>
                    <i-form-group-check
                      class="col-sm-12"
                      label-cols-md="3"
                      content-cols-md="9"
                      :label="'2.安全指標(W8=100分'"
                      :item="$v.fgop2"
                    >
                      <b-form-input class="col-sm-5" v-model="$v.fgop2.$model" :state="validateState($v.fgop2)"></b-form-input
                      ><span class="col-sm-6" style="color: red">(W8=W3*10)</span>
                    </i-form-group-check>
                  </b-form-row>
                  <!-- 3.強度指標(W7=100分) -->
                  <b-form-row>
                    <i-form-group-check
                      class="col-sm-12"
                      label-cols-md="3"
                      content-cols-md="9"
                      :label="'3.強度指標(W7=100分)'"
                      :item="$v.fgop3"
                    >
                      <b-form-input class="col-sm-5" v-model="$v.fgop3.$model" :state="validateState($v.fgop3)"></b-form-input
                      ><span class="col-sm-6" style="color: red">(W7=2*W1+2*W2)</span>
                    </i-form-group-check>
                  </b-form-row>
                  <!-- 4.美觀指標(W5=100分) -->
                  <b-form-row>
                    <i-form-group-check
                      class="col-sm-12"
                      label-cols-md="3"
                      content-cols-md="9"
                      :label="'4.美觀指標(W5=100分)'"
                      :item="$v.fgop4"
                    >
                      <b-form-input class="col-sm-5" v-model="$v.fgop4.$model" :state="validateState($v.fgop4)"></b-form-input
                      ><span class="col-sm-6" style="color: blue">(W5)</span>
                    </i-form-group-check>
                  </b-form-row>
                  <!-- 5.功能指標(W4=100分) -->
                  <b-form-row>
                    <i-form-group-check
                      class="col-sm-12"
                      label-cols-md="3"
                      content-cols-md="9"
                      :label="'5.功能指標(W4=100分)'"
                      :item="$v.fgop5"
                    >
                      <b-form-input class="col-sm-5" v-model="$v.fgop5.$model" :state="validateState($v.fgop5)"></b-form-input
                      ><span class="col-sm-6" style="color: blue">(W6)</span>
                    </i-form-group-check>
                  </b-form-row>
                </b-tr>
              </b-tbody>
            </b-table-simple>
          </b-col>
          <b-col cols="6">
            <b-table-simple hover small border>
              <b-thead>
                <b-tr>
                  <b-th style="context: center">委員表現</b-th>
                </b-tr>
              </b-thead>
              <b-tbody>
                <b-tr>
                  <!-- 實務專業 -->
                  <b-form-row>
                    <i-form-group-check class="col-sm-12" label-cols-md="3" content-cols-md="4" :label="'實務專業'" :item="$v.grade1">
                      <b-form-select v-model="$v.grade1.$model"></b-form-select>
                    </i-form-group-check>
                  </b-form-row>
                  <!-- 查核作業嚴謹度 -->
                  <b-form-row>
                    <i-form-group-check class="col-sm-12" label-cols-md="3" content-cols-md="4" :label="'查核作業嚴謹度'" :item="$v.grade2">
                      <b-form-select v-model="$v.grade2.$model"></b-form-select>
                    </i-form-group-check>
                  </b-form-row>
                  <!-- 查核紀錄表填寫詳細度 -->
                  <b-form-row>
                    <i-form-group-check
                      class="col-sm-12"
                      label-cols-md="3"
                      content-cols-md="4"
                      :label="'查核紀錄表填寫詳細度'"
                      :item="$v.grade3"
                    >
                      <b-form-select v-model="$v.grade3.$model"></b-form-select>
                    </i-form-group-check>
                  </b-form-row>
                  <!-- 品質作業熟悉度 -->
                  <b-form-row>
                    <i-form-group-check class="col-sm-12" label-cols-md="3" content-cols-md="4" :label="'品質作業熟悉度'" :item="$v.grade4">
                      <b-form-select v-model="$v.grade4.$model"></b-form-select>
                    </i-form-group-check>
                  </b-form-row>
                </b-tr>
              </b-tbody>
            </b-table-simple>
          </b-col>
        </b-row>
        <div class="text-center mt-2" v-if="formStatusRef === FormStatusEnum.CREATE || formStatusRef === FormStatusEnum.MODIFY">
          <i-button type="save"></i-button>
          <i-button type="arrow-counterclockwise" @click="backToPreviousPage"></i-button>
          <i-button type="x-circle"></i-button>
        </div>
        <div class="text-center mt-2" v-if="formStatusRef === FormStatusEnum.DELETE">
          <i-button type="x-lg"></i-button>
          <i-button type="arrow-counterclockwise" @click="backToPreviousPage"></i-button>
        </div>
      </div>
    </div>

    <!-- 錯誤訊息 -->
    <!-- <b-modal
      id="valid-error-modal"
      size="lg"
      title="資料驗證有誤:"
      :header-text-variant="'light'"
      :header-bg-variant="'danger'"
      hide-footer
    >
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModals.hide('valid-error-modal')">了解</b-button>
    </b-modal> -->
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, computed, Reactive } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';
import { handleBack } from '@/router/router';
import FormStatusEnum from '@/shared/enum';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';

export default {
  name: 'bidAbc001Edit2',
  props: {
    bidProjectModifyKeyProp: {
      // type: Object
      required: false,
    },
    formStatus: {
      required: false,
      type: String,
    },
  },
  components: {
    bidProjectInfo,
  },

  setup(props, context) {
    const bidProjectModifyKeyProps: Ref<any> = toRef(props, 'bidProjectModifyKeyProp');
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();
    const formStatusRef = toRef(props, 'formStatus'); // toRef會保持對來源property的響應式連接，但改變父元件資料會被Vue警告，所以這邊改用ref複製一份新的資料
    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const notificationService: NotificationService = useNotification();

    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
      DELETE = '刪除',
    }

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formDefault = {
      peoName: '李曉東', //委員姓名
      inOut: 'A', //內外聘
      peoMail: 'alksjfkld@gmail.com', //委員Email
      ynsend: 'A', // Email 寄送/不寄
      scorea: '', //品質管理制度
      scorea1: '', //A、主辦機關、專案管理廠商、監造單位(10分)
      scorea1a: '', //主辦機關、專案管理廠商(5分)
      scorea1e: '', //監造單位(5分)
      scorea2: '', //B、承攬廠商(10分)
      scoreb: '', //施工品質
      scoreb1: '', //A、混凝土、鋼筋、模板、土方、結構體、裝修、雜項等(40分)
      scoreb2: '', //B、材料設備檢驗與管制(10分)
      scoreb3: '', //施工安全衛生(10分)
      fgop1: '', //1.環境指標(W4=100分)
      fgop2: '', //2.安全指標(W8=100分)
      fgop3: '', //3.強度指標(W7=100分)
      fgop4: '', //4.美觀指標(W5=100分)
      fgop5: '', //5.功能指標(W4=100分)
      grade1: '', //實務專業
      grade2: '', //查核作業嚴謹度
      grade3: '', //查核紀錄表填寫詳細度
      grade4: '', //品質作業熟悉度
    };

    const rules = {
      peoName: { required: required },
      inOut: { required: required },
      peoMail: { required: required },
      ynsend: { required: required },
      scorea: { required: required },
      scorea1: { required: required },
      scorea1a: { required: required },
      scorea1e: { required: required },
      scorea2: { required: required },
      scoreb: { required: required },
      scoreb1: { required: required },
      scoreb2: { required: required },
      scoreb3: { required: required },
      fgop1: { required: required },
      fgop2: { required: required },
      fgop3: { required: required },
      fgop4: { required: required },
      fgop5: { required: required },
      grade1: { required: required },
      grade2: { required: required },
      grade3: { required: required },
      grade4: { required: required },
    };

    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 是否需要重新查詢
    const isReload = ref(false);

    //回上一頁
    function backToPreviousPage() {
      reset();
      handleBack({ isReload: isReload.value });
      isReload.value = false;
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

    const createDefaultValue = data => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      reset();
    };

    watch(
      bidProjectModifyKeyProps,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    return {
      $v,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      formDefault,
      stepVisible,
      projectInfo,
      FormStatusEnum,
      formStatusRef,
    };
  },
};
</script>

<style></style>

<template>
  <div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">技術服務(規劃設計)</h5>
      </div>
      <div class="card-body col-12">
        <!-- 自辦或委外 -->
        <b-row>
          <i-form-group-check :label="'自辦或委外：'" :item="$v.outSource">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.outSource.$model">
                <b-form-radio value="1">自辦</b-form-radio>
                <b-form-radio value="2">委外</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 規劃單位 ,規劃費用-->
        <b-form-row>
          <i-form-group-check class="col-sm-6" :labelStar="true" :label="'規劃單位：'" :item="$v.drut">
            <b-form-input class="col-4" v-model="$v.drut.$model"></b-form-input>
            <div class="col-8">
              <div class="d-inline"><bid-unit10-name :orgId="$v.drut.$model" /></div>
            </div>
          </i-form-group-check>
          <i-form-group-check class="col-sm-3" :label="'規劃費用：'" :item="$v.drct">
            <b-form-input class="col-8" v-model="$v.drct.$model"></b-form-input>
            <div class="col-4">
              <div class="d-inline">千元</div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 設計單位 ,設計費用-->
        <template v-for="(item, index) in $v.dsuts.$model">
          <b-form-row>
            <i-form-group-check class="col-sm-6" :labelStar="true" :label="'設計單位' + (index+1) + '：'" :item="$v.dsut">
              <b-form-input class="col-4" v-model="item.dsut"></b-form-input>
              <div class="col-8">
                <div class="d-inline"><bid-unit10-name :orgId="item.dsut" /></div>
              </div>
            </i-form-group-check>
            <i-form-group-check class="col-sm-3" :label="'設計費用' + (index+1) + '：'" :item="$v.dsct">
              <b-form-input class="col-8" v-model="item.dsct"></b-form-input>
              <div class="col-4">
                <div class="d-inline">千元</div>
              </div>
            </i-form-group-check>
            <div class="col-3">
              <i-button type="trash" @click="removeDsutUnits(index)" v-if="$v.dsuts.$model.length > 1"></i-button>
              <i-button type="plus-circle" @click="addDsutUnits" v-if="index == $v.dsuts.$model.length - 1"></i-button>
            </div>
          </b-form-row>
        </template>
        <!-- 監造單位 ,監造費用-->
        <template v-for="(item, index) in $v.scuts.$model">
          <b-form-row>
            <i-form-group-check class="col-sm-6" :label="'監造單位' + (index+1) + '：'" :item="$v.scut">
              <b-form-input class="col-4" v-model="item.scut"></b-form-input>
              <div class="col-8">
                <div class="d-inline"><bid-unit10-name :orgId="item.scut" /></div>
              </div>
            </i-form-group-check>
            <i-form-group-check class="col-sm-3" :label="'監造費用' + (index+1) + '：'" :item="$v.scct">
              <b-form-input class="col-8" v-model="item.scct"></b-form-input>
              <div class="col-4">
                <div class="d-inline">千元</div>
              </div>
            </i-form-group-check>
            <div class="col-3">
              <i-button type="trash" @click="removeScutUnits(index)" v-if="$v.scuts.$model.length > 1"></i-button>
              <i-button type="plus-circle" @click="addScutUnits" v-if="index == $v.scuts.$model.length - 1"></i-button>
            </div>
          </b-form-row>
        </template>
        <!-- -->
        <b-form-row>
          <i-form-group-check class="col-12" label-cols-md="5" content-cols-md="4" :label="'(委託規劃設計監造請填入廠商品依編號，自辦設計請填機關代碼305100100C)'">
            <b-col class="col-10">
              <b-button variant="outline-dark" @click="showFirmModal">廠商統編查詢</b-button>
              <b-button variant="outline-dark" @click="showOrgaModal">機關代碼查詢</b-button>
            </b-col>
          </i-form-group-check>
        </b-form-row>
      </div>
    </div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">工程(監造)</h5>
      </div>
      <div class="card-body col-12">
        <!-- 是否開工 -->
        <b-row>
          <i-form-group-check :label="'是否開工：'" :item="$v.kickOff">
            <div class="d-flex h-38p align-items-center">
              <b-form-radio-group v-model="$v.kickOff.$model">
                <b-form-radio value="Y">是</b-form-radio>
                <b-form-radio value="N">否</b-form-radio>
              </b-form-radio-group>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 契約約定受品管訓練合格之派駐現場人員(包含專職與非專職)-->
        <b-form-row>
          <i-form-group-check :label="'「契約約定受品管訓練合格之派駐現場人員人數」(包含專職與非專職)：'" :item="$v.scnum">
            <b-form-input class="col-3" v-model="$v.scnum.$model"></b-form-input>
          </i-form-group-check>
        </b-form-row>
        <!-- 專案管理單位-->
        <b-form-row>
          <i-form-group-check :label="'專案管理單位：'" :item="$v.pcmut">
            <b-form-input class="col-3" v-model="$v.pcmut.$model"></b-form-input>
            <div class="col-8">
              <div class="d-inline"><bid-unit10-name :orgId="$v.pcmut.$model" /></div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 營造廠商-->
        <template v-for="(item, index) in $v.ccuts.$model">
          <b-form-row>
            <i-form-group-check class="col-sm-6" :labelStar="true" :label="'營造廠商' + (index+1) + '：'" :item="$v.ccut">
              <b-form-input class="col-4" v-model="item.ccut"></b-form-input>
              <div class="col-8">
                <div class="d-inline"><bid-unit10-name :orgId="item.ccut" /></div>
              </div>
            </i-form-group-check>
            <i-form-group-check class="col-sm-3" :label="'成造比重' + (index+1) + '：'" :item="$v.crat">
              <b-form-input class="col-8" v-model="item.crat"></b-form-input>
              <div class="col-4">
                <div class="d-inline">%</div>
              </div>
            </i-form-group-check>
            <div class="col-3">
              <i-button type="trash" @click="removeCcutUnits(index)" v-if="$v.ccuts.$model.length > 1"></i-button>
              <i-button type="plus-circle" @click="addCcutUnits" v-if="index == $v.ccuts.$model.length - 1"></i-button>
            </div>
          </b-form-row>
        </template>
        <!-- 契約約定受品管訓練合格品質管理人員人數(包含專職與非專職)-->
        <b-form-row>
          <i-form-group-check :label="'「契約約定受品管訓練合格品質管理人員人數」(包含專職與非專職)：'" :item="$v.ccnum">
            <b-form-input class="col-3" v-model="$v.ccnum.$model"></b-form-input>
          </i-form-group-check>
        </b-form-row>
      </div>
    </div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">保險</h5>
      </div>
      <div class="card-body col-12">
        <!-- 保險日期起迄 -->
        <i-form-group-check :label="'保險日期起迄：'" :dual1="$v.issStartDate" :dual2="$v.issEndDate">
          <b-input-group>
            <i-date-picker v-model="$v.issStartDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
            <b-input-group-text>至</b-input-group-text>
            <i-date-picker v-model="$v.issEndDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
          </b-input-group>
        </i-form-group-check>
        <!-- 保險費用-->
        <b-form-row>
          <i-form-group-check :label="'保險費用：'" :item="$v.issCost">
            <b-form-input class="col-3" v-model="$v.issCost.$model"></b-form-input>
            <div class="col-8">
              <div class="d-inline">千元</div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 保險單位及保險號碼-->
        <b-form-row>
          <i-form-group-check :label="'保險單位及保險號碼：'" :item="$v.issNo">
            <b-form-input class="col-3" v-model="$v.issNo.$model"></b-form-input>
            <div class="col-8">
              <div class="d-inline">千元</div>
            </div>
          </i-form-group-check>
        </b-form-row>
        <!-- 後續加保狀況-->
        <b-form-row>
          <i-form-group-check :label="'後續加保狀況：'" :item="$v.issRmk">
            <b-form-textarea rows="3" max-rows="6" v-model="$v.issRmk.$model" :state="validateState($v.issRmk)"></b-form-textarea>
          </i-form-group-check>
        </b-form-row>
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
    <bid-unit10-list ref="bidUnit10List" />
  </div>
</template>

<script lang="ts">
import BidProjectUnitService from '@/components/bid/bidService/bid-project-unit.service';
import BidUnit10Name from '@/components/bid/bid-common-component/bid-unit10-name.vue';
import BidUnit10List from '@/components/bid/bid-common-component/bid-unit10-list.vue';
import { Ref, ref, toRef, toRefs, reactive, watch, computed, Reactive } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';

export default {
  name: 'bidAaa002EditInfo3',
  props: {
    bidProjectInfo: {
      required: false,
    },
  },
  components: {
    BidUnit10Name,
    BidUnit10List,
  },
  setup(props, context) {
    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const bidUnit10List = ref(null);

    const notificationService: NotificationService = useNotification();
    const bidProjectUnitService = new BidProjectUnitService();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formDefault = {
      wkut: '',
      prjno: '',
      outSource: '', //自辦或委外
      drut: '', //規劃單位
      drct: '', //規劃費用
      dsuts: [], //設計單位 bid_project_unit
      scuts: [], //監造單位 bid_project_unit
      kickOff: '', //是否開工
      scnum: '', //「契約約定受品管訓練合格之派駐現場人員」(包含專職與非專職)
      pcmut: '', //專案管理
      ccuts: [], //營造廠商 bid_project_unit
      ccnum: '', //「契約約定受品管訓練合格品質管理人員人數」(包含專職與非專職)
      issStartDate: '', //保險日期起日
      issEndDate: '', //保險日期迄日
      issCost: '', //保險費用
      issNo: '', //保險單位及保險號碼
      issRmk: '', //後續加保狀況
    };
    let formEmpty = Object.assign({}, formDefault);
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      wkut: {},
      prjno: {},
      outSource: {},
      drut: {},
      drct: {},
      dsuts: {},
      scuts: {},
      kickOff: {},
      scnum: {},
      pcmut: {},
      ccuts: {},
      ccnum: {},
      issStartDate: {},
      issEndDate: {},
      issCost: {},
      issNo: {},
      issRmk: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAaa002Query');
    }

    function addDsutUnits() {
      const item = { wkut: form.wkut, prjno: form.prjno, type: '1', dsut: '', dsct: 0, };
      form.dsuts.push(item);
    }

    function addScutUnits() {
      const item = { wkut: form.wkut, prjno: form.prjno, type: '2', scut: '', scct: 0, };
      form.scuts.push(item);
    }

    function addCcutUnits() {
      const item = { wkut: form.wkut, prjno: form.prjno, type: '3', ccut: '', crat: 0, };
      form.ccuts.push(item);
    }

    function removeDsutUnits(index) {
      form.dsuts.splice(index, 1);
    }

    function removeScutUnits(index) {
      form.scuts.splice(index, 1);
    }

    function removeCcutUnits(index) {
      form.ccuts.splice(index, 1);
    }

    function putForm() {
      return form;
    }

    function showFirmModal() {
      const queryKey = reactive({
         type: '1';
      });
      bidUnit10List.value.title = '廠商代碼查詢';
      bidUnit10List.value.isShowDia(queryKey, true);
    }

    function showOrgaModal() {
      const queryKey = reactive({
        type: '0';
      });
      bidUnit10List.value.title = '機關代碼查詢';
      bidUnit10List.value.isShowDia(queryKey, true);
    }

    watch(() => props.bidProjectInfo, (newVal: object) => {
      Object.assign(formDefault, formEmpty)
      for(let key in formDefault){
        if(newVal[key]) formDefault[key] = newVal[key];
        if(key === 'issStartDate' || key === 'issEndDate') {
          if (newVal[key] != null) {
            formDefault[key] = new Date(newVal[key]);
          }
        }
      }
      bidProjectUnitService.findUnitsByWkutAndPrjnoAndType(newVal.wkut, newVal.prjno, '1')
        .then(returnArray => {
          formDefault.dsuts.splice(0, formDefault.dsuts.length, ...returnArray);
          if (formDefault.dsuts.length == 0) {
            const item = { wkut: form.wkut, prjno: form.prjno, type: '1', dsut: '', dsct: 0, };
            formDefault.dsuts.push(item);
          }
        });
      bidProjectUnitService.findUnitsByWkutAndPrjnoAndType(newVal.wkut, newVal.prjno, '2')
        .then(returnArray => {
          formDefault.scuts.splice(0, formDefault.scuts.length, ...returnArray);
          if (formDefault.scuts.length == 0) {
            const item = { wkut: form.wkut, prjno: form.prjno, type: '2', scut: '', scct: 0, };
            formDefault.scuts.push(item);
          }
        });
      bidProjectUnitService.findUnitsByWkutAndPrjnoAndType(newVal.wkut, newVal.prjno, '3')
        .then(returnArray => {
          formDefault.ccuts.splice(0, formDefault.ccuts.length, ...returnArray);
          if (formDefault.ccuts.length == 0) {
            const item = { wkut: form.wkut, prjno: form.prjno, type: '3', ccut: '', crat: 0, };
            formDefault.ccuts.push(item);
          }
        });
      reset();
    },{immediate: true, deep: true});

    return {
      $v,
      checkValidity,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      formDefault,
      stepVisible,
      putForm,
      addDsutUnits,
      addScutUnits,
      addCcutUnits,
      removeDsutUnits,
      removeScutUnits,
      removeCcutUnits,
      showFirmModal,
      showOrgaModal,
      bidUnit10List,
    };
  },
};
</script>

<style></style>

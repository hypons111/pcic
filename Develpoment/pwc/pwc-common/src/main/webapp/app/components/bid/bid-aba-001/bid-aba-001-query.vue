<template>
  <div>
    <!-- 查詢欄位 -->
    <section class="container mt-2">
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
        <div class="card-body col-12">
          <b-collapse v-model="stepVisible.step1">
            <b-card>
              <b-row class="h-100">
                <b-col cols="2" class="text-right pt-2">
                  <b-form-group>
                    <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="A">A類</b-form-radio>
                  </b-form-group>
                </b-col>
                <b-col cols="10">
                  <!-- 標案編號： -->
                  <i-form-group-check
                    label-align="left"
                    class="col-12"
                    label-cols="3"
                    content-cols="4"
                    :label="$t('label.prjno') + '：'"
                    :item="$v.prjno"
                  >
                    <b-form-input
                      v-model="$v.prjno.$model"
                      ref="prjnoElement"
                      :disabled="judgeInputDisable($v.radioType.$model, 'A')"
                    ></b-form-input>
                  </i-form-group-check>
                </b-col>
              </b-row>
              <b-row class="h-100">
                <b-col cols="2" class="text-right pt-2">
                  <b-form-group>
                    <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="B">B類</b-form-radio>
                  </b-form-group>
                </b-col>
                <b-col cols="10">
                  <!-- 標案名稱關鍵字： -->
                  <i-form-group-check
                    label-align="left"
                    class="col-12"
                    label-cols="3"
                    content-cols="5"
                    :label="$t('label.projectName') + $t('label.keyWord') + '：'"
                    :item="$v.projectName"
                  >
                    <b-form-input
                      v-model="$v.projectName.$model"
                      ref="projectNameElement"
                      :disabled="judgeInputDisable($v.radioType.$model, 'B')"
                    ></b-form-input>
                  </i-form-group-check>
                </b-col>
              </b-row>
              <b-row class="h-100">
                <b-col cols="2" class="text-right pt-2">
                  <b-form-group>
                    <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="C">C類</b-form-radio>
                  </b-form-group>
                </b-col>
                <b-col cols="10">
                  <!-- 規劃設計或監造名稱關鍵字： -->
                  <i-form-group-check
                    label-align="left"
                    class="col-12"
                    label-cols="3"
                    content-cols="6"
                    :label="$t('label.utName') + $t('label.keyWord') + '：'"
                    :item="$v.specificUnit"
                  >
                    <b-form-input
                      v-model="$v.specificUnit.$model"
                      ref="specificUnitElement"
                      :disabled="judgeInputDisable($v.radioType.$model, 'C')"
                    ></b-form-input>
                  </i-form-group-check>
                </b-col>
              </b-row>
            </b-card>

            <b-card class="mt-2">
              <b-row class="h-100 d-flex">
                <b-col cols="2" class="text-right">
                  <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="D">D類：依機關</b-form-radio>
                </b-col>
                <b-col cols="2" class="text-left">
                  <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="E">E類：GPMnet列管計畫</b-form-radio>
                </b-col>
                <b-col cols="2" class="text-left">
                  <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="F">F類：採[統包]標案</b-form-radio>
                </b-col>
                <b-col cols="3" class="text-left">
                  <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="G">G類：採[專案管理]標案</b-form-radio>
                </b-col>
                <b-col cols="3" class="text-left">
                  <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="H">H類：採[最有利標]標案</b-form-radio>
                </b-col>
              </b-row>
              <b-row class="mt-2">
                <b-col cols="2"></b-col>
                <b-col cols="10">
                  <i-form-group-check
                    label-align="left"
                    class="col-12"
                    label-cols="3"
                    content-cols="6"
                    :label="$t('label.wkutName') + $t('label.keyWord') + '：'"
                    :item="$v.wkutNormal"
                  >
                    <i-tree-select
                      v-model="$v.wkutNormal.$model"
                      placeholder="請輸入機關關鍵字查詢(至少輸入兩個字)"
                      :multiple="false"
                      :async="true"
                      :load-options="loadOptions"
                      :disabled="judgeInputDisable($v.radioType.$model, 'D')"
                    ></i-tree-select>
                    <!-- :disabled="$v.radioType.$model !== 'D'" -->
                  </i-form-group-check>
                </b-col>
              </b-row>
            </b-card>
            <b-card class="mt-2">
              <b-row class="h-100 d-flex">
                <b-col cols="2" class="text-right pt-2">
                  <b-form-group>
                    <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="I">I類</b-form-radio>
                  </b-form-group>
                </b-col>
                <b-col cols="10">
                  <i-form-group-check
                    label-align="left"
                    class="col-12"
                    label-cols="3"
                    content-cols="6"
                    :label="$t('label.add92Option') + '：'"
                  >
                    <b-form-select
                      v-model="$v.wkutAdd92Option.$model"
                      @change="handleAdd92Change"
                      :options="options"
                      :disabled="judgeInputDisable($v.radioType.$model, 'I')"
                      ref="wkutAdd92Element"
                    ></b-form-select>
                  </i-form-group-check>
                </b-col>
              </b-row>
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="10">
                  <i-form-group-check
                    label-align="left"
                    class="col-12"
                    label-cols="3"
                    content-cols="6"
                    :label="$t('label.wkutName') + $t('label.option') + '：'"
                    :item="$v.wkutAdd92"
                  >
                    <!-- <b-form-input v-model="$v.wkutAdd92.$model" ref="wkutAdd92Element"></b-form-input> -->
                    <!-- :disabled="$v.radioType.$model !== 'I'" -->
                    <i-tree-select
                      v-model="$v.wkutAdd92.$model"
                      placeholder="請輸入機關關鍵字查詢(至少輸入兩個字)"
                      :options="add92WkutOption"
                      :multiple="false"
                      :disabled="judgeInputDisable($v.radioType.$model, 'I')"
                    ></i-tree-select>
                  </i-form-group-check>
                </b-col>
              </b-row>
            </b-card>
            <b-row class="mt-2 justify-content-center" align-content="center">
              <b-col offset-sm="0" cols="4">
                <i-button type="search" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="resetQueryAndTable"></i-button>
              </b-col>
            </b-row>
          </b-collapse>
        </div>
      </div>
    </section>
    <!-- 查詢結果 -->
    <section class="mt-2">
      <div v-if="stepVisible.step2" class="container">
        <i-table
          class="table-sm"
          :itemsUndefinedBehavior="'loading'"
          :items="prjQueryTable.data"
          :fields="prjQueryTable.fields"
          :totalItems="prjQueryTable.totalItems"
          :is-server-side-paging="true"
          ref="iTable"
          @changePagination="handlePaginationChanged($event)"
        >
          <template #cell(aopDate)="row">
            {{ formatDate(row.item.aopDate ? new Date(row.item.aopDate) : null, '/') }}
          </template>
          <template #cell(action)="row">
            <i-button type="binoculars" @click="toEditView(row.item)"></i-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { ref, reactive, watch, nextTick } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import { requiredIf, required, minLength } from '@/shared/validators';
import { VwBidProjectQueryCriteria } from '@/shared/model/bidModel/vw-bid-project-query.criteria';
import VwBidProjectService from '@/components/bid/bidService/vw-bid-project.service';
import BidUnit10Service from '@/components/bid/bidService/bid-unit10.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import ItreeSelect from '@/shared/i-tree-select/i-tree-select.vue';
import { Pagination } from '@/shared/model/pagination.model';
import { ASYNC_SEARCH } from '@riophae/vue-treeselect';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import NotificationService from '@/shared/notification/notification-service';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
export default {
  name: 'bidAba001Query',
  components: { 'i-tree-select': ItreeSelect },
  setup(props, context) {
    const prjnoElement = ref(null);
    const projectNameElement = ref(null);
    const specificUnitElement = ref(null);
    const wkutAdd92Element = ref(null);

    const bidUnit10Service = new BidUnit10Service();
    const vwBidProjectService = new VwBidProjectService();
    const admSysCodeService = new AdmSysCodeService();
    const options = ref([{ value: null, text: '請選擇' }]);
    const add92WkutOption = ref([]);
    let bidSysCodeTable = new BidSysCodeTable();
    const bidCommonService = new BidCommonService();

    const iTable = ref(null);

    const stepVisible = ref({
      step1: true,
      step2: false,
    });

    const notificationService: NotificationService = useNotification();

    interface IqueryOptions {
      radioType: string;
      prjno?: string | null;
      projectName?: string | null;
      specificUnit?: string | null;
      wkutNormal?: string | null;
      wkutAdd92Option?: string | null;
      wkutAdd92?: string | null;
    }

    let queryOptionsDefault = {
      radioType: '',
      prjno: '',
      projectName: '',
      specificUnit: '',
      wkutNormal: null,
      wkutAdd92Option: null,
      wkutAdd92: null,
    };

    //去後端撈選項
    let criteria = {
      moduleType: 'BID',
      dataType: 'BID_001',
    };
    //把I類行政院專案補助工程之專案類別 select的第一個選項 "非所屬專案" 改成disable or 直接砍掉
    admSysCodeService.getPromiseOptionsByCriteria(criteria, options.value).then(res => {
      let add92DisableOption = res.find(element => {
        return element.value === '9U';
      });
      if (add92DisableOption) {
        add92DisableOption['disabled'] = true;
        options.value.splice(0, 1);
        // options.value.splice(0, 1, add92DisableOption);
      }
    });

    let queryOptions: IqueryOptions = reactive(Object.assign({}, queryOptionsDefault));
    const rules = {
      radioType: { required: required },
      prjno: { requiredIf: requiredIf(() => queryOptions.radioType === 'A') },
      projectName: { requiredIf: requiredIf(() => queryOptions.radioType === 'B'), minLength: minLength(2) },
      specificUnit: { requiredIf: requiredIf(() => queryOptions.radioType === 'C') },
      wkutNormal: {
        requiredIf: requiredIf(() => {
          let condition = false;
          if (
            queryOptions.radioType === 'D' ||
            queryOptions.radioType === 'E' ||
            queryOptions.radioType === 'F' ||
            queryOptions.radioType === 'G' ||
            queryOptions.radioType === 'H'
          ) {
            condition = true;
          }
          return condition;
        }),
      },
      wkutAdd92Option: { requiredIf: requiredIf(() => queryOptions.radioType === 'I') },
      wkutAdd92: { requiredIf: requiredIf(() => queryOptions.radioType === 'I') },
    };

    const { $v, checkValidity, reset } = useValidation(rules, queryOptions, queryOptionsDefault);

    const treeSelectOptions = ref([]);

    const prjQueryTable = reactive({
      fields: [
        {
          key: 'wkut',
          label: i18n.t('label.wkut'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'prjno',
          label: i18n.t('label.prjno'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectName',
          label: i18n.t('label.projectName'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'aopDate',
          label: i18n.t('label.aopDate'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'place',
          label: i18n.t('label.constructionPlace'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'typeCode',
          label: i18n.t('label.typeCode'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'aeDate',
          label: i18n.t('label.aeDate'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    //透過選項focus對應 element,但是tree select好像沒辦法focus
    async function elementFocus(radioType: string) {
      await nextTick();
      switch (radioType) {
        case 'A':
          prjnoElement.value.focus();
          break;
        case 'B':
          projectNameElement.value.focus();
          break;
        case 'C':
          specificUnitElement.value.focus();
          break;
        case 'I':
          wkutAdd92Element.value.focus();
          break;
        default:
          break;
      }
    }
    //切換選項就清空input裡面的內容
    watch(
      () => queryOptions.radioType,
      newValue => {
        reset();
        queryOptions.radioType = newValue;
        elementFocus(newValue);
      }
    );

    //給元件判斷 :disable 為 T,F用的
    function judgeInputDisable(currentRadioValue: string, radioValue: string): boolean {
      if (currentRadioValue == 'E' || currentRadioValue == 'F' || currentRadioValue == 'G' || currentRadioValue == 'H') {
        currentRadioValue = 'D';
      }
      return !(radioValue === currentRadioValue);
    }

    //行政院專案補助工程之專案類別 選項變就去改下面的選項
    function handleAdd92Change() {
      add92WkutOption.value.splice(0, add92WkutOption.value.length);
      queryOptions.wkutAdd92 = null;
      if (queryOptions.wkutAdd92Option != '9U') {
        vwBidProjectService
          .findWkutByAdd92(queryOptions.wkutAdd92Option)
          .then(res => {
            if (res != null && res.length > 0) {
              res.forEach(ele => {
                const add92Option = { id: '', label: '' };
                add92Option.id = ele.wkut;
                add92Option.label = ele.wkutName;
                add92WkutOption.value.push(add92Option);
              });
            }
          })
          .catch(notificationErrorHandler(notificationService));
      }
    }

    //利用tree select 自帶的API來動態產生選項
    function loadOptions({ action, searchQuery, callback }) {
      //判斷注音
      let pattern = /[\u3105-\u3129\u02CA\u02C7\u02CB\u02D9]/;
      if (action === ASYNC_SEARCH && !pattern.test(searchQuery) && searchQuery.toString().length >= 2) {
        bidUnit10Service.findAllwithNameAndCodeByKeyWord(searchQuery.toString()).then(res => {
          const options = [];
          res.forEach(ele => {
            const wkutOption = { id: '', label: '' };
            wkutOption.id = ele.code;
            wkutOption.label = ele.name;
            options.push(wkutOption);
          });
          callback(null, options);
        });
      }
    }
    //送 query去後端撈資料，渲染到table
    function handleQuery() {
      prjQueryTable.data = null;
      prjQueryTable.totalItems = 0;
      checkValidity().then(result => {
        if (result) {
          if (iTable.value) iTable.value.state.pagination.currentPage = 1;
          stepVisible.value.step2 = true;
          let queryCriteria = queryStatusToCriteria(queryOptions);
          console.log('準備傳到後端', queryCriteria);
          getDatafromAxios(queryCriteria);
        } else {
          notificationService.info('請輸入條件');
          //do something
        }
      });
    }

    function getDatafromAxios(queryCriteria) {
      vwBidProjectService
        .findByCriteria(queryCriteria)
        .then(res => {
          console.log('result from backend', res);
          bidSysCodeTable.getBidSysCodeWithParam('BID_003').then(sysCodeData => {
            res.content.forEach(projectElement => {
              let obj = sysCodeData.find(element => {
                return element.dataKey === projectElement.typeCode;
              });
              projectElement.typeCode = obj.value;
            });
            prjQueryTable.data = res.content.slice(0, res.content.length);
            prjQueryTable.totalItems = res.totalElements;
          });
        })
        .catch(notificationErrorHandler(notificationService));
      //checkVailidaty 沒過
    }
    function toEditView(value) {
      let bidProjectKey = {
        wkut: value.wkut,
        prjno: value.prjno,
      };
      navigateByNameAndParams('bidAba001Info', { bidProjectKey });
    }

    function handlePaginationChanged(pagination: Pagination) {
      let queryCriteria = queryStatusToCriteria(queryOptions);
      Object.assign(queryCriteria, pagination);
      getDatafromAxios(queryCriteria);
    }

    function resetQueryAndTable() {
      reset();
      stepVisible.value.step2 = false;
      prjQueryTable.data = null;
      prjQueryTable.totalItems = 0;
    }

    //讓前端畫面的criteria轉成後端認得的 DTO
    function queryStatusToCriteria(queryOption: IqueryOptions) {
      console.log('origin cri', queryOption);
      let criteria = new VwBidProjectQueryCriteria();
      switch (queryOption.radioType) {
        case 'A':
          criteria.queryType = 'keyWord';
          criteria.prjno = queryOption.prjno;
          break;
        case 'B':
          criteria.queryType = 'keyWord';
          criteria.prjName = queryOption.projectName;
          break;
        case 'C':
          criteria.queryType = 'specificUnit';
          criteria.specificUnitName = queryOption.specificUnit;
          break;
        case 'D':
          criteria.queryType = 'wkut';
          criteria.queryCase = 'normal';
          criteria.wkut = queryOption.wkutNormal;
          break;
        case 'E':
          criteria.queryType = 'wkut';
          criteria.queryCase = 'GPMnet';
          criteria.wkut = queryOption.wkutNormal;
          break;
        case 'F':
          criteria.queryType = 'wkut';
          criteria.queryCase = 'union';
          criteria.wkut = queryOption.wkutNormal;
          break;
        case 'G':
          criteria.queryType = 'wkut';
          criteria.queryCase = 'pcm';
          criteria.wkut = queryOption.wkutNormal;
          break;
        case 'H':
          criteria.queryType = 'wkut';
          criteria.queryCase = 'favorable';
          criteria.wkut = queryOption.wkutNormal;
          break;
        case 'I':
          criteria.queryType = 'prjType';
          criteria.add92 = queryOptions.wkutAdd92Option;
          criteria.wkut = queryOption.wkutAdd92;
          break;
      }
      console.log('post cri', criteria);
      return criteria;
    }

    return {
      stepVisible,
      treeSelectOptions,
      prjnoElement,
      projectNameElement,
      specificUnitElement,
      wkutAdd92Element,
      queryOptions,
      options,
      add92WkutOption,
      prjQueryTable,
      $v,
      handleAdd92Change,
      handleQuery,
      loadOptions,
      judgeInputDisable,
      toEditView,
      reset,
      resetQueryAndTable,
      handlePaginationChanged,
      formatDate,
    };
  },
};
</script>

<style></style>

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
                變更執行機關查詢
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
        <div class="card-body">
          <b-collapse v-model="stepVisible.step1">
            <b-card class="col-12">
              <b-table-simple bordered>
                <b-tbody>
                  <b-tr>
                    <b-td>
                      <b-row class="h-100">
                        <b-col cols="2" class="text-right pt-2">
                          <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="A">A類</b-form-radio>
                        </b-col>
                        <b-col cols="10">
                          <i-form-group-check label-align="left" class="col-12" label-cols="3" content-cols="4" :label="'機關'">
                            <b-form-select ref="prjnoElement" :disabled="judgeInputDisable($v.radioType.$model, 'A')" trim></b-form-select>
                          </i-form-group-check>
                          <i-form-group-check label-align="left" class="col-12" label-cols="3" content-cols="4" :label="'查詢層級'">
                            <b-form-radio-group
                              ref="prjnoElement"
                              :disabled="judgeInputDisable($v.radioType.$model, 'A')"
                              trim
                            ></b-form-radio-group>
                          </i-form-group-check>
                          <i-form-group-check label-align="left" class="col-12" label-cols="3" content-cols="4" :label="'工程標案編號'">
                            <b-form-input ref="prjnoElement" :disabled="judgeInputDisable($v.radioType.$model, 'A')" trim></b-form-input>
                          </i-form-group-check>
                        </b-col>
                      </b-row>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td>
                      <b-row class="h-100">
                        <b-col cols="2" class="text-right pt-2">
                          <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="B">B類</b-form-radio>
                        </b-col>
                        <b-col cols="10">
                          <i-form-group-check label-align="left" class="col-12" label-cols="3" content-cols="4" :label="'工程標案型態'">
                            <b-form-radio-group
                              ref="projectNameElement"
                              :disabled="judgeInputDisable($v.radioType.$model, 'B')"
                              trim
                            ></b-form-radio-group>
                          </i-form-group-check>
                        </b-col>
                      </b-row>
                    </b-td>
                  </b-tr>
                </b-tbody>
              </b-table-simple>
              <b-row class="mt-2 justify-content-center" align-content="center">
                <b-col offset-sm="0" cols="4">
                  <i-button type="search" @click="handleQuery"></i-button>
                  <i-button type="x-circle" @click="resetQueryAndTable"></i-button>
                  <slot name="customButton"></slot>
                </b-col>
              </b-row>
            </b-card>
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
          <template #cell(action)="row">
            <!--            <div v-if="buttonNameProp">-->
            <!--              <b-button variant="info" @click="toEditView(row.item)">{{ buttonNameProp }}</b-button>-->
            <!--            </div>-->
            <!--            <div v-else>-->
            <!--              <i-button type="binoculars" @click="toEditView(row.item)"></i-button>-->
            <!--            </div>-->
            <b-button variant="info" @click="toEditView(row.item)">變更執行機關</b-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { ref, reactive, watch, nextTick, toRef, inject } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import { requiredIf, required, minLength } from '@/shared/validators';
import { VwBidProjectQueryCriteria } from '@/shared/model/bidModel/vw-bid-project-query.criteria';
import VwBidProjectService from '@/components/bid/bidService/vw-bid-project.service';
import BidUnit10Service from '@/components/bid/bidService/bid-unit10.service';
import BidPrjMonthService from '@/components/bid/bidService/bid-prj-month.service';
import { Pagination } from '@/shared/model/pagination.model';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import { commaFormatter } from '@/shared/formatter/common';
import AccountService from '@/account/account.service';

export default {
  name: 'bidAda001Query',
  props: {},
  setup(props, context) {
    //帳號資料
    const accountService = inject<() => AccountService>('accountService')();
    let unitId = accountService.user.pccOrgId;

    //定義component
    const prjnoElement = ref(null);
    const projectNameElement = ref(null);

    const bidUnit10Service = new BidUnit10Service();
    const vwBidProjectService = new VwBidProjectService();
    const bidPrjMonthService = new BidPrjMonthService();

    const iTable = ref(null);

    //
    const stepVisible = ref({
      step1: true,
      step2: false,
    });

    const notificationService: NotificationService = useNotification();

    //從使用者撈wkut
    // const store = context.root.$store;
    // let unitId = store.getters.account.unit.id;

    interface IqueryOptions {
      radioType: string;
      wkutName?: string | null;
      queryLevel?: string | null;
      prjno?: string | null;
      wkutType?: string | null;
    }

    let queryOptionsDefault = {
      radioType: '',
      wkutName: '',
      queryLevel: '',
      prjno: '',
      wkutType: '',
    };

    //儲存 project 和 對應的month
    let projectAndMonthArr = reactive([]);

    let queryOptions: IqueryOptions = reactive(Object.assign({}, queryOptionsDefault));
    const rules = {
      radioType: { required: required },
      wkutName: { requiredIf: requiredIf(() => queryOptions.radioType === 'A'), minLength: minLength(2) },
      queryLevel: { requiredIf: requiredIf(() => queryOptions.radioType === 'A'), minLength: minLength(2) },
      prjno: { requiredIf: requiredIf(() => queryOptions.radioType === 'A'), minLength: minLength(2) },
      wkutType: { requiredIf: requiredIf(() => queryOptions.radioType === 'B'), minLength: minLength(2) },
    };

    const { $v, checkValidity, reset } = useValidation(rules, queryOptions, queryOptionsDefault);

    const prjQueryTable = reactive({
      fields: [
        {
          key: 'prjno',
          label: i18n.t('label.prjno'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'wkutName',
          label: i18n.t('執行機關'),
          sortable: false,
          thStyle: 'width:20%',
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
          key: 'actDate',
          label: i18n.t('決標日期'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'bdgt1',
          label: i18n.t('工程預算'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'man',
          label: i18n.t('聯絡人'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'deptName',
          label: '聯絡單位',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'tel',
          label: '聯絡電話',
          sortable: false,
          thStyle: 'width:3%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
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

    //透過選項focus對應 element
    async function elementFocus(radioType: string) {
      await nextTick();
      switch (radioType) {
        case 'A':
          prjnoElement.value.focus();
          break;
        case 'B':
          projectNameElement.value.focus();
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
      return !(radioValue === currentRadioValue);
    }

    //送 query去後端撈資料，渲染到table
    function handleQuery() {
      prjQueryTable.data = null;
      prjQueryTable.totalItems = 0;
      checkValidity().then(result => {
        if (result) {
          if (iTable.value) iTable.value.state.pagination.currentPage = 1;
          stepVisible.value.step2 = true;
          // console.log(queryOptions);
          let queryCriteria = queryStatusToCriteria(queryOptions);
          // console.log(queryCriteria);
          getDatafromAxios(queryCriteria);
        } else {
          notificationService.info('請輸入條件');
          //do something
        }
      });
    }

    function getDatafromAxios(queryCriteria) {
      //清空保存資料的陣列
      projectAndMonthArr.splice(0, projectAndMonthArr.length);
      vwBidProjectService
        .findByCriteria(queryCriteria)
        .then(res => {
          // console.log('getDatafromAxios', res);
          //撈出的project去找他的最新進度資料
          res.content.forEach(ele => {
            const tableObj = { project: ele, month: null };
            projectAndMonthArr.push(tableObj);
            bidPrjMonthService.getPureLatestPrjMonthByProjectKey(ele.wkut, ele.prjno).then(result => {
              if (result != null && result !== '') {
                // console.log('result:', result);
                tableObj.month = result;
                // ele[latestPrjMonth] = result;
              }
            });
          });
          // console.log('getMonthfromAxios', projectAndMonthArr);
          prjQueryTable.data = projectAndMonthArr.slice(0, projectAndMonthArr.length); //後端分頁
          prjQueryTable.totalItems = res.totalElements; //後端分頁
        })
        .catch(notificationErrorHandler(notificationService));
    }
    function toEditView(value) {
      // console.log('toEditView', value);
      let bidProjectKey = {
        wkut: value.project.wkut,
        prjno: value.project.prjno,
      };
      //   navigateByNameAndParams('bidAaa001ProjectLoginMenu', { bidProjectKey });
      navigateByNameAndParams('bidAda001Edit', { bidProjectKey });
    }

    function handlePaginationChanged(pagination: Pagination) {
      let queryCriteria = queryStatusToCriteria(queryOptions);
      // console.log(queryCriteria);
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
      let criteria = new VwBidProjectQueryCriteria();
      criteria.queryType = 'projectLogin';
      // #fixme wkut之後要從登入者撈
      if ('1' === unitId || '' === unitId || unitId === null) {
        criteria.wkut = '9876543210';
      } else {
        criteria.wkut = unitId;
      }

      switch (queryOption.radioType) {
        case 'A':
          // criteria.prjno = queryOption.prjno;
          break;
        case 'B':
          // criteria.prjName = queryOption.projectName;
          break;

        default:
          break;
      }
      return criteria;
    }

    let admOrgNamesOptions = reactive([]);
    function handleAdmOrgNames(value) {}

    return {
      iTable,
      stepVisible,
      prjnoElement,
      projectNameElement,
      queryOptions,
      prjQueryTable,
      $v,
      handleQuery,
      judgeInputDisable,
      toEditView,
      reset,
      resetQueryAndTable,
      handlePaginationChanged,
      formatDate,
      commaFormatter,
      admOrgNamesOptions,
      handleAdmOrgNames,
    };
  },
};
</script>

<style></style>

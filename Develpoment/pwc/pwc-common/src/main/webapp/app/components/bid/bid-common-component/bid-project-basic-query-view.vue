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
        <div class="card-body">
          <b-collapse v-model="stepVisible.step1">
            <b-card class="col-12">
              <b-table-simple bordered>
                <b-thead head-variant="info">
                  <b-tr>
                    <b-th class="text-center align-middle">類別</b-th>
                    <b-th class="text-center align-middle">查詢條件</b-th>
                  </b-tr>
                </b-thead>
                <b-tbody>
                  <b-tr>
                    <b-td class="text-center">
                      <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="A"></b-form-radio>
                    </b-td>
                    <b-td>
                      <i-form-group-check label-align="left" class="col-12" label-cols="3" content-cols="4" :label="'標案部分編號'">
                        <b-form-input
                          v-model="$v.prjno.$model"
                          ref="prjnoElement"
                          :disabled="judgeInputDisable($v.radioType.$model, 'A')"
                        ></b-form-input>
                      </i-form-group-check>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="text-center">
                      <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="B"></b-form-radio>
                    </b-td>
                    <b-td>
                      <i-form-group-check label-align="left" class="col-12" label-cols="3" content-cols="4" :label="'標案名稱關鍵字'">
                        <b-form-input
                          ref="projectNameElement"
                          v-model="$v.projectName.$model"
                          :disabled="judgeInputDisable($v.radioType.$model, 'B')"
                          trim
                        ></b-form-input>
                      </i-form-group-check>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="text-center">
                      <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="C"></b-form-radio>
                    </b-td>
                    <b-td>
                      <b-row>
                        <b-form-radio-group
                          v-model="$v.typeCode.$model"
                          :disabled="judgeInputDisable($v.radioType.$model, 'C')"
                          class="pl-3 pb-3"
                        >
                          <b-form-radio value="all">所有金額在建工程</b-form-radio>
                          <b-form-radio value="serviceProperty">勞務財物標案</b-form-radio>
                        </b-form-radio-group>
                      </b-row>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="text-center">
                      <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="D"></b-form-radio>
                    </b-td>
                    <b-td>
                      <i-form-group-check label-align="left" class="col-12" label-cols="1" content-cols="9" :label="'發包金額'">
                        <b-form-radio-group v-model="$v.bdgtRange.$model" :disabled="judgeInputDisable($v.radioType.$model, 'D')">
                          <b-form-radio value="b1000">1000萬以下</b-form-radio>
                          <b-form-radio value="u1000b5000">1000萬到5000萬</b-form-radio>
                          <b-form-radio value="u5000">5000萬以上</b-form-radio>
                        </b-form-radio-group>
                      </i-form-group-check>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td class="text-center">
                      <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="E"></b-form-radio>
                    </b-td>
                    <b-td>
                      <b-form-radio-group
                        v-model="$v.finishWithoutAccept.$model"
                        :disabled="judgeInputDisable($v.radioType.$model, 'E')"
                        class="pl-3 pb-3"
                      >
                        <b-form-radio value="aeAfterThisYear">本年度完工未驗收案件</b-form-radio>
                        <b-form-radio value="beforeThisYear">以前年度完工未驗收案件</b-form-radio>
                        <b-form-radio value="all">完工未驗收案件</b-form-radio>
                      </b-form-radio-group>
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
          <template #cell(yearMonth)="row">
            <!-- {{ row.item.month.year + '/' + row.item.month.month }} -->
            <div v-if="row.item.month">
              {{ row.item.month ? row.item.month.syr : null }}年 {{ row.item.month ? row.item.month.month : null }}月
            </div>
            <div v-else>
              {{
                showMessageConverter(
                  row.item.project.ctSum,
                  row.item.project.actDate,
                  row.item.project.csDate,
                  row.item.project.asDate,
                  row.item.project.aeDate
                )
              }}
            </div>
          </template>
          <template #cell(diff)="row">
            <div v-if="row.item.month">{{ row.item.month.ptrate - row.item.month.atrate }}%</div>
          </template>
          <template #cell(project.actDate)="row">
            {{ row.item.project.actDate ? formatDate(new Date(row.item.project.actDate), '/') : '(未填)' }}
          </template>
          <template #cell(project.ctSum)="row"> {{ commaFormatter(row.item.project.ctSum, 3) }} </template>
          <template #cell(project.asDate)="row">
            {{ row.item.project.asDate ? formatDate(new Date(row.item.project.asDate), '/') : '(未填)' }}
          </template>
          <template #cell(month.ptrate)="row">
            <div v-if="row.item.month">{{ row.item.month.ptrate ? row.item.month.ptrate + '%' : '' }}</div>
          </template>
          <template #cell(month.atrate)="row">
            <div v-if="row.item.month">{{ row.item.month.atrate ? row.item.month.atrate + '%' : '' }}</div>
          </template>
          <template #cell(project.aeDate)="row">
            <div>
              {{ row.item.project.aeDate ? formatDate(new Date(row.item.project.aeDate), '/') : '(未填)' }}
            </div>
          </template>
          <template #cell(project.aokDate)="row">
            <div>
              {{ row.item.project.aokDate ? formatDate(new Date(row.item.project.aokDate), '/') : '(未填)' }}
            </div>
          </template>
          <template #cell(action)="row">
            <div v-if="buttonNameProp">
              <b-button variant="info" @click="toEditView(row.item)">{{ buttonNameProp }}</b-button>
            </div>
            <div v-else>
              <i-button type="binoculars" @click="toEditView(row.item)"></i-button>
            </div>
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
  name: 'bidProjectBasicQueryView',
  props: {
    toRouterPath: {
      type: String,
      required: true,
    },
    buttonName: {
      type: String,
      required: false,
    },
  },
  setup(props, context) {
    //從props接下要倒的路徑和客製化按鈕名字
    const toRouterPathProp = toRef(props, 'toRouterPath');
    const buttonNameProp = toRef(props, 'buttonName');
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
      prjno?: string | null;
      projectName?: string | null;
      typeCode?: string | null;
      bdgtRange?: string | null;
      finishWithoutAccept?: string | null;
    }

    let queryOptionsDefault = {
      radioType: '',
      prjno: '',
      projectName: '',
      typeCode: '',
      bdgtRange: '',
      finishWithoutAccept: '',
    };

    //儲存 project 和 對應的month
    let projectAndMonthArr = reactive([]);

    let queryOptions: IqueryOptions = reactive(Object.assign({}, queryOptionsDefault));
    const rules = {
      radioType: { required: required },
      prjno: { requiredIf: requiredIf(() => queryOptions.radioType === 'A'), minLength: minLength(2) },
      projectName: { requiredIf: requiredIf(() => queryOptions.radioType === 'B'), minLength: minLength(2) },
      typeCode: { requiredIf: requiredIf(() => queryOptions.radioType === 'C') },
      bdgtRange: { requiredIf: requiredIf(() => queryOptions.radioType === 'D') },
      finishWithoutAccept: { requiredIf: requiredIf(() => queryOptions.radioType === 'E') },
    };

    const { $v, checkValidity, reset } = useValidation(rules, queryOptions, queryOptionsDefault);

    const treeSelectOptions = ref([]);

    const prjQueryTable = reactive({
      fields: [
        {
          key: 'project.prjno',
          label: i18n.t('label.prjno'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'project.projectName',
          label: i18n.t('label.projectName'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'project.actDate',
          label: i18n.t('label.actDate'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'project.ctSum',
          label: i18n.t('label.ctSum'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'project.asDate',
          label: i18n.t('label.asDate'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'yearMonth',
          label: '進度年月',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'month.ptrate',
          label: '預計進度',
          sortable: false,
          thStyle: 'width:3%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'month.atrate',
          label: '實際進度',
          sortable: false,
          thStyle: 'width:3%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'diff',
          label: '差異',
          sortable: false,
          thStyle: 'width:3%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'project.aeDate',
          label: '實際' + i18n.t('label.aeDate'),
          sortable: false,
          thStyle: 'width:7%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'project.aokDate',
          label: i18n.t('label.aokDate'),
          sortable: false,
          thStyle: 'width:7%',
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
      navigateByNameAndParams(toRouterPathProp.value, { bidProjectKey });
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
          criteria.prjno = queryOption.prjno;
          break;
        case 'B':
          criteria.prjName = queryOption.projectName;
          break;
        case 'C':
          if ('all' === queryOption.typeCode) {
            criteria.typeCodeFirstNotEqual = '9';
            criteria.bdgtLowerBound = 0;
            criteria.bdgtUpperBound = 99999999;
            criteria.aokAeDateCase = '0116';
          } else if ('serviceProperty' === queryOption.typeCode) {
            criteria.typeCodeFirstEqual = '9';
          }
          break;
        case 'D':
          if ('b1000' === queryOption.bdgtRange) {
            criteria.bdgtLowerBound = 0;
            criteria.bdgtUpperBound = 10000;
          } else if ('u1000b5000' === queryOption.bdgtRange) {
            criteria.bdgtLowerBound = 10000;
            criteria.bdgtUpperBound = 50000;
          } else if ('u5000' === queryOption.bdgtRange) {
            criteria.bdgtLowerBound = 50000;
          }
          criteria.aokAeDateCase = '0116';
          break;
        case 'E':
          if ('all' === queryOption.finishWithoutAccept) {
            criteria.aokAeDateCase = 'bothNull';
          } else if ('beforeThisYear' === queryOption.finishWithoutAccept) {
            let firstDayOfThisYear = new Date(new Date().getFullYear(), 0, 1);
            criteria.aokDateUpperBound = firstDayOfThisYear;
          } else if ('aeAfterThisYear' === queryOption.finishWithoutAccept) {
            criteria.aokAeDateCase = 'aeThisYear';
          }
          break;
        default:
          break;
      }
      return criteria;
    }
    function showMessageConverter(
      ctSum: number,
      actDateString: string | null,
      csDateString: string | null,
      asDateString: string | null,
      aeDateString: string | null
    ): string {
      const today = new Date();
      let actDate = actDateString ? new Date(actDateString) : null;
      let csDate = csDateString ? new Date(csDateString) : null;
      let asDate = asDateString ? new Date(asDateString) : null;
      let aeDate = aeDateString ? new Date(aeDateString) : null;

      if (ctSum === 0 || ctSum === null || actDate === null) {
        return '尚未決標(BID-0001-W)';
      }
      if (actDate > today) {
        return '剛決標尚未填報預定開工日(BID-0002-W)';
      }

      if (asDate !== null && asDate >= today) {
        return `已決標並將於${formatDate(asDate, '/')}開工(BID-0007-W)`;
      } else if (csDate !== null && asDate !== null && asDate < today) {
        return `已於${formatDate(asDate, '/')}開工進度未填(BID-0008-W)`;
      }

      let lastMonthLastDay = new Date(today.getFullYear, today.getMonth - 1, 0);
      if (csDate !== null && asDate >= lastMonthLastDay) {
        return `已決標預定${formatDate(csDate)}開工(BID-0005-W)`;
      } else if (csDate !== null && asDate < lastMonthLastDay) {
        return `預定${formatDate(csDate)}開工日已屆進度未填(BID-0006-W))`;
      }

      if (csDate === null && asDate === null && aeDate === null) {
        return '進度資料未填(BID-0004-W)';
      } else if (csDate === null && asDate === null && aeDate !== null) {
        return `已於${formatDate(aeDate)}解約(BID-0003-W)`;
      }
    }

    return {
      iTable,
      buttonNameProp,
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
      showMessageConverter,
      commaFormatter,
    };
  },
};
</script>

<style></style>

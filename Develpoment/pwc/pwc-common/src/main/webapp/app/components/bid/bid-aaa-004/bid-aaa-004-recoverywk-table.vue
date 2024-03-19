<template>
  <div>
    <i-button type="printer" v-print="printObj"></i-button>
    <div class="card" id="printTable">
      <div class="card-body col-12">
        <section class="container mt-2">
          <!-- 執行單位 -->
          <div class="d-flex justify-content-center">
            <h5>{{ projectInfo.wkutName }}</h5>
          </div>

          <div class="d-flex justify-content-center"><h5>工程復工報告表</h5></div>
          <div class="d-flex justify-content-end">列印日期：{{ formatDateInChinese(new Date()) }}</div>
        </section>
        <div class="d-none">
          {{ projectInfo }}
        </div>
        <section class="container mt-2">
          <b-table-simple bordered fixed>
            <!-- 工程名稱 -->
            <b-tr>
              <b-td class="text-center">{{ $t('label.projectName') }}</b-td>
              <b-td colspan="3">{{ projectInfo.name }}</b-td>
            </b-tr>
            <!-- 工程地點 -->
            <b-tr>
              <b-td class="text-center">工程地點 </b-td>
              <b-td colspan="3"> {{ projectInfo.place }} </b-td>
            </b-tr>
            <!-- 契約金額 契約編號 -->
            <b-tr>
              <b-td class="text-center"> {{ $t('label.ctSum') }} </b-td>
              <b-td> {{ projectInfo.ctSum | commaFilter }}(千元) </b-td>
              <b-td class="text-center"> {{ $t('label.cntid') }} </b-td>
              <b-td> {{ projectInfo.cntid }} </b-td>
            </b-tr>
            <!-- 開始 復工 停工 -->
            <b-tr>
              <b-td colspan="4">
                <div v-if="bidProjectStopwk.endDate">
                  本工程定於中華民國 {{ formatDateInChinese(new Date(bidProjectStopwk.endDate)) }}復工({{
                    formatDateInChinese(new Date(bidProjectStopwk.startDate))
                  }}開始停工)
                </div>
                <div v-else>尚未復工</div>
              </b-td>
            </b-tr>
            <!-- 承包商 契約規定工期 -->
            <b-tr>
              <b-td class="text-center">承包商</b-td>
              <b-td>{{ bidProjectUnitName.ccutName ? bidProjectUnitName.ccutName : '(無資料)' }}</b-td>
              <b-td class="text-center">契約規定工期</b-td>
              <b-td> {{ projectInfo.wRemk }} </b-td>
            </b-tr>
            <!-- 契約規定開工日期 契約規定竣工日期-->
            <b-tr>
              <b-td class="text-center">契約規定開工日期</b-td>
              <b-td>{{ projectInfo.csDate ? formatDateInChinese(new Date(projectInfo.csDate)) : '(無資料)' }}</b-td>
              <b-td class="text-center">契約規定竣工日期</b-td>
              <b-td>{{ projectInfo.ceDate ? formatDateInChinese(new Date(projectInfo.ceDate)) : '(無資料)' }}</b-td>
            </b-tr>
            <!-- 實際開工日期 預定復工日期-->
            <b-tr>
              <b-td class="text-center">實際開工日期</b-td>
              <b-td>{{ projectInfo.asDate ? formatDateInChinese(new Date(projectInfo.asDate)) : '(無資料)' }}</b-td>
              <b-td class="text-center">預定復工日期</b-td>
              <b-td>{{ bidProjectStopwk.peDate ? formatDateInChinese(new Date(bidProjectStopwk.peDate)) : '(無資料)' }}</b-td>
            </b-tr>
            <!-- 說明 -->
            <b-tr>
              <b-td class="text-center align-middle">說明</b-td>
              <b-td colspan="3">
                <div>核定停工文號：{{ bidProjectStopwk.prmNo }}</div>
                <div>停工原因：</div>
                <div>{{ bidProjectStopwk.remark }}</div>
                <div>復工原因：</div>
                <div>{{ bidProjectStopwk.eRemark }}</div>
              </b-td>
            </b-tr>
            <!-- 提報單位 監造單位 主辦機關 -->
            <b-tr>
              <b-td colspan="2">
                <div class="text-center">提報單位</div>
                <div class="text-center">{{ bidProjectUnitName.ccutName ? bidProjectUnitName.ccutName : '(無資料)' }}</div>
              </b-td>
              <b-td>
                <div class="text-center">監造單位</div>
                <div class="text-center">{{ bidProjectUnitName.scutName ? bidProjectUnitName.scutName : '(無資料)' }}</div>
              </b-td>
              <b-td>
                <div class="text-center">主辦機關</div>
                <div class="text-center">{{ projectInfo.wkutName }}</div>
              </b-td>
            </b-tr>
            <!-- 印章 -->
            <b-tr>
              <b-td colspan="2">
                <div>&nbsp;</div>
                <div>&nbsp;</div>
                <div class="text-center">(公司及負責人章)</div>
              </b-td>
              <b-td>
                <div>&nbsp;</div>
                <div>&nbsp;</div>
                <div class="text-center">(監造單位印章)</div>
              </b-td>
              <b-td>
                <div>&nbsp;</div>
                <div>&nbsp;</div>
                <div class="text-center">(主辦機關印章)</div>
              </b-td>
            </b-tr>

            <!-- 印章 -->
            <b-tr>
              <b-td colspan="2">
                <div>&nbsp;</div>
                <div>&nbsp;</div>
                <div class="text-center">(專任技師或工地負責人簽章)</div>
              </b-td>
              <b-td>
                <div>&nbsp;</div>
                <div>&nbsp;</div>
                <div class="text-center">(承辦人員簽章)</div>
              </b-td>
              <b-td>
                <div>&nbsp;</div>
                <div>&nbsp;</div>
                <div class="text-center">(主辦者簽章)</div>
              </b-td>
            </b-tr>
          </b-table-simple>
        </section>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, onActivated } from '@vue/composition-api';
import { IBidPrjStopwk, BidPrjStopwk } from '@/shared/model/bidModel/bid-prj-stopwk.model';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import { Pagination } from '@/shared/model/pagination.model';
import { commaFormatter } from '@/shared/formatter/common';
import BidUnit10Service from '@/components/bid/bidService/bid-unit10.service';

import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import BidPrjStopwkService from '@/components/bid/bidService/bid-prj-stopwk.service';
import { formatDateInChinese } from '@/shared/date/minguo-calendar-utils';
export default {
  name: 'bidAaa004RecoverywkTable',
  props: {
    bidProjectStopwkKey: {
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectStopwkKeyProp: Ref<any> = toRef(props, 'bidProjectStopwkKey');
    const tempProjectKey = {
      wkut: '',
      prjno: '',
    };
    if (bidProjectStopwkKeyProp.value) {
      tempProjectKey.wkut = bidProjectStopwkKeyProp.value.wkut;
      tempProjectKey.prjno = bidProjectStopwkKeyProp.value.prjno;
    }
    const bidProjectService = new BidProjectService();
    const bidPrjStopwkService = new BidPrjStopwkService();
    const bidSysCodeTable = new BidSysCodeTable();
    const bidUnit10Service = new BidUnit10Service();

    const addNewModify = ref(false);
    //project的data
    const projectInfo = ref({ wkut: '', prjno: '', wkutName: '' });

    //停工的資料
    let bidProjectStopwk = ref(new BidPrjStopwk());

    let bidProjectUnitName = ref({
      dsutName: '',
      scutName: '',
      ccutName: '',
    });
    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut: string, prjno: string) {
      bidProjectService.findBidProjectByWkutAndPrjno(wkut, prjno).then(result => {
        Object.assign(projectInfo.value, result);
        bidUnit10Service.findUnitNameByCode(projectInfo.value.wkut).then(data => {
          projectInfo.value.wkutName = data.name;
        });
      });
    }

    function fetchBidProjecUnit(wkut: string, prjno: string) {
      bidProjectService.findBidProjectDUST(wkut, prjno).then(result => {
        if (result) bidProjectUnitName.value.dsutName = result.name;
      });
      bidProjectService.findBidProjectSCUT(wkut, prjno).then(result => {
        if (result) bidProjectUnitName.value.scutName = result.name;
      });
      bidProjectService.findBidProjectCCUT(wkut, prjno).then(result => {
        if (result) bidProjectUnitName.value.ccutName = result.name;
      });
    }

    function fetchStopwkInfo(wkut: string, prjno: string, startDate: Date) {
      bidPrjStopwkService.getBidProjectStopwkByKey(wkut, prjno, startDate).then(data => {
        Object.assign(bidProjectStopwk.value, data);
      });
    }

    watch(
      bidProjectStopwkKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          fetchBidProjecUnit(newValue.wkut, newValue.prjno);
          fetchStopwkInfo(newValue.wkut, newValue.prjno, newValue.startDate);
        }
      },
      { immediate: true }
    );

    let printObj = ref({
      id: 'printTable',
      popTitle: '公共工程雲端系統',
    });

    return {
      printObj,
      projectInfo,
      bidProjectStopwk,
      bidProjectUnitName,
      formatDateInChinese,
    };
  },
  filters: {
    commaFilter: (value: any) => commaFormatter(value),
  },
};
</script>

<template>
  <div>
    <i-button type="printer" v-print="'#printTable'"></i-button>
    <div class="card" id="printTable">
      <div class="card-body col-12">
        <section class="container mt-2">
          <div class="d-flex justify-content-center"><h5>變更經費或期程一覽表</h5></div>
          <div class="d-flex justify-content-end">列印日期{{ formatDate(new Date(), '-') }}</div>
        </section>
        <section class="container mt-2">
          <b-table-simple bordered>
            <b-tr>
              <b-td colspan="2">{{ $t('label.projectName') }} ： {{ projectInfo.name }}</b-td>
            </b-tr>
            <b-tr>
              <b-td> {{ $t('label.wkut') }}：{{ projectInfo.wkutName }} </b-td>
              <b-td> {{ $t('label.prjno') }}：{{ projectInfo.prjno }} </b-td>
            </b-tr>
            <b-tr>
              <b-td>{{ $t('label.contactPerson') }}：{{ projectInfo.man }}</b-td>
              <b-td>{{ $t('label.tel') }}：{{ projectInfo.tel }}</b-td>
            </b-tr>
            <b-tr>
              <b-td>{{ $t('label.bdgt1') }}：{{ projectInfo.bdgt1 | commaFilter }}</b-td>
              <b-td>{{ $t('label.ctSum') }}：{{ projectInfo.ctSum | commaFilter }}</b-td>
            </b-tr>
            <b-tr>
              <b-td>{{ $t('label.dept') }}：{{ projectInfo.deptName }}</b-td>
              <b-td>{{ $t('label.bos') }}：{{ projectInfo.bosName }}</b-td>
            </b-tr>
          </b-table-simple>
        </section>
        <section class="container mt-2">
          <div v-for="(item, index) in projectModifyDataArray" :key="index">
            <b-table-simple bordered>
              <b-thead class="text-center">
                <tr>
                  <b-th colspan="3"> 第{{ index + 1 }}次 變更日期：{{ item.chgDate }}</b-th>
                </tr>
              </b-thead>
              <b-tr>
                <b-td colspan="2">核准文號 ： {{ item.okNo }}</b-td>
                <b-td>程序 ： {{ item.mdyPress }}</b-td>
              </b-tr>
              <b-tr>
                <b-td> 變更原因：{{ item.mdyReson }} </b-td>
                <b-td colspan="2"> 原因說明：{{ item.mdyRemark }} </b-td>
              </b-tr>
              <b-tr>
                <b-td>變更前契約金額：{{ item.oldAmt }}</b-td>
                <b-td>變更後契約金額：{{ item.newAmt }}</b-td>
                <b-td>本次增減金額：{{ parseFloat(item.newAmt - item.oldAmt).toFixed(3) }}</b-td>
              </b-tr>
              <b-tr>
                <b-td>變更前預定竣工日：{{ item.oldDate }}</b-td>
                <b-td colspan="2">變更後預定竣工日：{{ item.newDate }}</b-td>
              </b-tr>
              <b-tr>
                <b-td colspan="3">准延天數：{{ item.nDays }}</b-td>
              </b-tr>
            </b-table-simple>
            <!-- <h5>{{ item }}</h5> -->
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, onActivated } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import BidPrModifyService from '@/components/bid/bidService/bid-prj-modify.service';
import { Pagination } from '@/shared/model/pagination.model';
import { commaFormatter } from '@/shared/formatter/common';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
export default {
  name: 'bidAaa003Table',
  props: {
    bidProjectKey: {
      required: false,
    },
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
    const bidPrModifyService = new BidPrModifyService();
    const bidSysCodeTable = new BidSysCodeTable();

    const addNewModify = ref(false);
    //上面project表格的資料
    const projectInfo = ref({ wkut: '', prjno: '' });

    //下面modify表格的資料
    const projectModifyDataArray = reactive([]);

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.assign(projectInfo.value, result);
      });
      // console.log(projectInfo);
    }

    function fetchProjectAllModify(wkut, prjno) {
      bidPrModifyService
        .findProjectAllModify({
          wkut: wkut,
          prjno: prjno,
        })
        .then(data => {
          projectModifyDataArray.splice(0, projectModifyDataArray.length, ...data.content);
          processArrayData(projectModifyDataArray);
        });
    }

    //format用
    function processArrayData(projectModifyDataArray: any[]) {
      bidSysCodeTable.getBidSysCodeWithParam('BID_010').then(data => {
        //data就是一個陣列，把陣列裡面的物件拿去對這個陣列
        projectModifyDataArray.forEach(item => {
          let compareObj = data.find(result => {
            return result.dataKey === item.mdyReson;
          });
          item.mdyReson = compareObj ? compareObj.value : item.mdyReson;
          if (item.mdyPress === '1') {
            item.mdyPress = '有簽報辦理變更設計';
          } else if (item.mdyPress === '2') {
            item.mdyPress = '單純工期展延';
          }
        });
      });
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          fetchProjectAllModify(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    onActivated(() => {
      if (bidProjectKeyProp.value) {
        fetchProjectAllModify(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
      } else if (tempProjectKey.wkut.length > 0) {
        fetchProjectAllModify(tempProjectKey.wkut, tempProjectKey.prjno);
      }
    });

    return {
      projectInfo,
      projectModifyDataArray,
      formatDate,
    };
  },
  filters: {
    commaFilter: (value: any) => commaFormatter(value),
  },
};
</script>

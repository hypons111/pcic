<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>

    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">標案設計變更資料</h5>
        </div>
        <div></div>
        <div class="card-body col-12">
          <b-row>
            <i-form-group-check :label="'原始母標案名稱：'">
              {{ projectInfo.name }}
            </i-form-group-check>
            <i-form-group-check :label="'標案編號：'">
              {{ bidPrjModifyDataProp.prjno }}
            </i-form-group-check>
          </b-row>
          <b-row>
            <i-form-group-check :label="'變更日期：'">
              {{ bidPrjModifyDataProp.chgDate }}
            </i-form-group-check>
          </b-row>
          <b-row>
            <i-form-group-check :label="'變更原因說明：'">
              {{ bidPrjModifyDataProp.mdyReson }}
              {{ mdyResonValue }}
            </i-form-group-check>
          </b-row>
          <b-row>
            <i-form-group-check :label="'核准變更文號：'">
              {{ bidPrjModifyDataProp.okNo }}
            </i-form-group-check>
          </b-row>
          <b-row>
            <i-form-group-check :label="'前次修正後總價：'">
              {{ bidPrjModifyDataProp.oldAmt }}
            </i-form-group-check>
          </b-row>
          <b-row>
            <i-form-group-check :label="'本次修正後總價：'">
              {{ bidPrjModifyDataProp.newAmt }}
            </i-form-group-check>
          </b-row>
          <b-row>
            <i-form-group-check :label="'變更前竣工日：'">
              {{ bidPrjModifyDataProp.oldDate }}
            </i-form-group-check>
          </b-row>
          <b-row>
            <i-form-group-check :label="'變更後竣工日：'">
              {{ bidPrjModifyDataProp.newDate }}
            </i-form-group-check>
          </b-row>
          <b-row>
            <i-form-group-check :label="'准延日期：'">
              {{ bidPrjModifyDataProp.nDays }}
            </i-form-group-check>
          </b-row>
          <b-row>
            <div>原本的專案名稱</div>
            <div>已自系統刪除</div>
          </b-row>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, onActivated } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidPrModifyService from '@/components/bid/bidService/bid-prj-modify.service';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
export default {
  name: 'bidAdc001Info',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
    bidPrjModifyData: {
      type: Object,
      required: false,
    },
    oldProjectName: {
      type: String,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidPrjModifyDataProp: Ref<any> = toRef(props, 'bidPrjModifyData');
    const oldProjectNameProp: Ref<any> = toRef(props, 'oldProjectName');

    const bidSysCodeTable = new BidSysCodeTable();

    console.log(bidPrjModifyDataProp);
    console.log(oldProjectNameProp);

    const mdyResonValue = ref('');

    //在keep alive的時候保留原本的project資料
    const tempProjectKey = {
      wkut: '',
      prjno: '',
    };
    setTempProjectKey();
    const bidProjectService = new BidProjectService();
    const bidPrModifyService = new BidPrModifyService();

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    function setTempProjectKey() {
      if (bidProjectKeyProp.value) {
        tempProjectKey.wkut = bidProjectKeyProp.value.wkut;
        tempProjectKey.prjno = bidProjectKeyProp.value.prjno;
      }
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          setTempProjectKey();
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    watch(
      bidPrjModifyDataProp,
      newValue => {
        bidSysCodeTable.getBidSysCodeWithParam('BID_010').then(data => {
          let sysCodeObj = data.find(element => {
            return element.dataKey === newValue.mdyReson;
          });
          mdyResonValue.value = sysCodeObj.value;
        });
      },
      { immediate: true }
    );

    onActivated(() => {
      if (bidProjectKeyProp.value) {
        setTempProjectKey();
      } else if (tempProjectKey.wkut.length > 0) {
      }
    });

    return {
      projectInfo,
      bidProjectKeyProp,
      bidPrjModifyDataProp,
      oldProjectNameProp,
      mdyResonValue,
    };
  },
};
</script>

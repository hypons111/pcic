<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">標案資料登錄功能列表</h5>
        </div>
        <div class="card-body col-12">
          <b-table-simple hover small caption-top responsive bordered>
            <b-thead head-variant="secondary">
              <b-tr>
                <b-th></b-th>
                <b-th>開工前</b-th>
                <b-th>履約期間</b-th>
                <b-th>竣工結案</b-th>
              </b-tr>
            </b-thead>
            <b-tbody>
              <b-tr>
                <b-th rowspan="8">必要功能</b-th>
                <b-td>
                  <b-button variant="primary" @click="toBidfunction('bidAaa002Edit')">基本資料</b-button>
                  <b-button variant="primary" @click="toBidfunction('bidAab006Info')">開工要件</b-button>
                </b-td>
                <b-td><b-button variant="primary" @click="toBidfunction('bidAab001InfoMenu')">各月分配</b-button></b-td>
                <b-td><b-button variant="info">竣工或結案</b-button> </b-td>
              </b-tr>
              <b-tr>
                <b-td>
                  <b-button variant="primary" @click="toBidfunction('bidAab002Info')">預定完成里程碑</b-button>
                  <b-button variant="info">地理座標</b-button>
                </b-td>
                <b-td><b-button variant="info">執行進度</b-button></b-td>
                <b-td><b-button variant="info">終止或解除合約</b-button> </b-td>
              </b-tr>
              <b-tr>
                <b-td>
                  <b-button variant="primary" @click="toBidfunction('bidAac001Info')">承造廠商之品管人員</b-button>
                </b-td>
                <b-td><b-button variant="primary" @click="toBidfunction('bidAaa005Edit')">付款進度</b-button></b-td>
                <b-td><b-button variant="info">履約情形計分</b-button> </b-td>
              </b-tr>
              <b-tr>
                <b-td>
                  <b-button variant="primary" @click="toBidfunction('bidAac002Info')">承造廠商專任工程人員</b-button>
                </b-td>
                <b-td><b-button variant="info">驗收資料</b-button></b-td>
                <b-td></b-td>
              </b-tr>
              <b-tr>
                <b-td>
                  <b-button variant="primary" @click="toBidfunction('bidAac003Info')">工地主任及工地負責人員</b-button>
                  <b-button variant="primary" @click="toBidfunction('bidAac005Info')">安衛人員</b-button>
                </b-td>
                <b-td></b-td>
                <b-td></b-td>
              </b-tr>
              <b-tr>
                <b-td>
                  <b-button variant="primary" @click="toBidfunction('bidAac004Info')">承造廠商技術士</b-button>
                  <b-button variant="primary" @click="toBidfunction('bidAac006Info')">其他合約規定人員</b-button>
                </b-td>
                <b-td></b-td>
                <b-td></b-td>
              </b-tr>
              <b-tr>
                <b-td>
                  <b-button variant="info">監造單位現場人員</b-button>
                </b-td>
                <b-td></b-td>
                <b-td></b-td>
              </b-tr>
              <b-tr>
                <b-td>
                  <b-button variant="info">監造單位監造PCM專技人員</b-button>
                </b-td>
                <b-td></b-td>
                <b-td></b-td>
              </b-tr>
              <b-tr>
                <b-th rowspan="6">非必要功能</b-th>
                <b-td>
                  <b-button variant="info">生態檢核</b-button>
                  <b-button variant="primary" @click="toBidfunction('bidAae021Info')">外籍勞工核定</b-button>
                </b-td>
                <b-td>
                  <b-button variant="info">開口合約派工</b-button>
                  <b-button variant="info">監督付款或連帶保證</b-button>
                </b-td>
                <b-td></b-td>
              </b-tr>
              <b-tr>
                <b-td>
                  <b-button variant="primary" @click="toBidfunction('bidAaa003InfoMenu')">變更經費期程</b-button>
                  <b-button variant="info">未開工原因</b-button>
                </b-td>
                <b-td>
                  <b-button variant="info">復建院核定項目</b-button>
                  <b-button variant="primary" @click="toBidfunction('bidAab009Info')">復建請撥款情形</b-button>
                </b-td>
                <b-td></b-td>
              </b-tr>
              <b-tr>
                <b-td>
                  <b-button variant="primary" @click="toBidfunction('bidAaa004InfoMenu')">停工狀況</b-button>
                </b-td>
                <b-td>
                  <b-button variant="primary" @click="toBidfunction('bidAad001EditInfo')">查核懲處狀況</b-button>
                  <b-button variant="info">延遲付款處理</b-button>
                </b-td>
                <b-td></b-td>
              </b-tr>
              <b-tr>
                <b-td>
                  <b-button variant="info">送機關備查之分包情形廠商</b-button>
                </b-td>
                <b-td>
                  <b-button variant="primary" @click="toBidfunction('bidAad002Info')">工安事件狀況</b-button>
                </b-td>
                <b-td></b-td>
              </b-tr>
              <b-tr>
                <b-td>
                  <b-button variant="info">工地型預拌混凝土通報</b-button>
                </b-td>
                <b-td>
                  <b-button variant="primary" @click="toBidfunction('bidAad003Query')">環保裁罰狀況</b-button>
                </b-td>
                <b-td></b-td>
              </b-tr>
              <b-tr>
                <b-td></b-td>
                <b-td>
                  <b-button variant="info">人力需求</b-button>
                  <b-button variant="primary" @click="toBidfunction('bidAad007InfoMenu')">施工相片影像文件上傳</b-button>
                </b-td>
                <b-td></b-td>
              </b-tr>
            </b-tbody>
          </b-table-simple>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, onActivated } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'bidAaa001ProjectLoginMenu',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidProjectService = new BidProjectService();

    const tempProjectKey = {
      wkut: '',
      prjno: '',
    };
    setTempProjectKey();

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        // console.log('fetchBidProjectInfo res ', result);
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    function toBidfunction(functionRouterName: string) {
      let bidProjectKey = {
        wkut: tempProjectKey.wkut,
        prjno: tempProjectKey.prjno,
      };
      navigateByNameAndParams(functionRouterName, { bidProjectKey });
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
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    onActivated(() => {
      if (bidProjectKeyProp.value) {
        setTempProjectKey();
        fetchBidProjectInfo(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
      } else if (tempProjectKey.wkut.length > 0) {
        fetchBidProjectInfo(tempProjectKey.wkut, tempProjectKey.prjno);
      }
    });

    return {
      projectInfo,
      toBidfunction,
    };
  },
};
</script>

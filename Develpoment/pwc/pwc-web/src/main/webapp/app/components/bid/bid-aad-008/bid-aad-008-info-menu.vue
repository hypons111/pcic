<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">公共工程施工廠商履約計分情形計分紀錄</h5>
        </div>
        <div class="card-body col-12">
          <div class="container">
            <b-row>
              <!-- 左半部 -->
              <b-col cols="6">
                <div v-if="canProjectGoKpifin">
                  <b-row>
                    <div v-if="formStatus === formStatusEnum.CREATE">
                      <i-button type="file-earmark-plus" @click="addNewKpi"></i-button>
                    </div>
                    <div v-else-if="formStatus === formStatusEnum.MODIFY">
                      <i-button type="pencil-square" @click="modifyKpi"></i-button>
                      <i-button type="trash" @click="deleteNewKpi"></i-button>
                      <i-button type="printer" @click="getBidModifyReport"></i-button>
                    </div>
                  </b-row>
                  <b-row>
                    <b-table-simple bordered table-class="text-center">
                      <b-thead head-variant="info">
                        <b-tr>
                          <b-th>計分指標</b-th>
                          <b-th>計分項目</b-th>
                          <b-th>加減分</b-th>
                        </b-tr>
                      </b-thead>
                      <b-tbody>
                        <!-- 工期 -->
                        <b-tr>
                          <b-td class="align-middle" rowspan="2">工期</b-td>
                          <b-td>提前竣工加分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kb01 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>逾期竣工減分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kb02 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <!-- 履約成本 -->
                        <b-tr>
                          <b-td class="align-middle" rowspan="2">履約成本及違約金</b-td>
                          <b-td>替代方案加分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kc01 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>違約金減分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kc02 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <!-- 施工品質 -->
                        <b-tr>
                          <b-td class="align-middle" rowspan="9">施工品質</b-td>
                          <b-td>驗收加分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kd01 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>驗收減分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kd02 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>施工作業減分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kd03 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>施工查核加分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kd04 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>施工查核減分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kd05 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>專任工程人員加分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kd06 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>專任工程人員減分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kd07 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>技術士參與情形</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kd08 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>品質獲獎加分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kd09 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <!-- 安衛環保 -->
                        <b-tr>
                          <b-td class="align-middle" rowspan="4">安衛環保</b-td>
                          <b-td>勞保獲獎加分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.ke01 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>無職災加分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.ke02 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>發生職災減分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.ke03 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>環保加分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.ke04 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <!-- 反映停權 -->
                        <b-tr>
                          <b-td class="align-middle" rowspan="2">反映及停權</b-td>
                          <b-td>反映及停權-民眾通報加分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kf01 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <b-tr>
                          <b-td>停權減分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kf02 }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                        <!-- 總分 -->
                        <b-tr>
                          <b-td colspan="2">總分</b-td>
                          <b-td v-if="formStatus === formStatusEnum.MODIFY"> {{ bidKpifin.kscore }} </b-td>
                          <b-td v-else></b-td>
                        </b-tr>
                      </b-tbody>
                    </b-table-simple>
                  </b-row>
                </div>
                <div v-else>
                  {{ errorMessage }}
                  <b-button @click="canProjectGoKpifin = true">測試用 開起來就有新增或修改</b-button>
                </div>
              </b-col>
              <!-- 右半部 -->
              <b-col cols="6">
                <div class="pl-2">
                  <b-row>
                    <div>
                      <div>
                        <div class="text-danger">108.11.25</div>
                        工程管字第
                        <b-link href="#foo" disabled>1080301027</b-link>
                        號函　修正「公共工程施工廠商履約情形計分要點」第三點附表一、附表二，並自中華民國一百零九年一月一日起生效。
                      </div>
                      <div>
                        <div>
                          附表一
                          <b-link href="#foo" disabled>修正總說明</b-link>
                        </div>
                        <div>
                          附表二
                          <b-link href="#foo" disabled>修正對照表</b-link>
                        </div>
                        <div>
                          附表二
                          <b-link href="#foo" disabled>修正規定</b-link>
                        </div>
                      </div>
                    </div>
                  </b-row>
                  <b-row class="pt-3">
                    <div>
                      <div>
                        <div class="text-danger">107.05.30</div>
                        工程管字第10700163260號函 修正
                        <b-link href="#foo" disabled>「公共工程施工廠商履約情形計分要點」</b-link>
                        第十二點及第三點附表一、附表二，並自中華民國一百零七年七月一日起生效。
                      </div>
                      <div>
                        <div>附表一 計分指標、計分項目及計分基準<b-link href="#foo" disabled>一覽表</b-link></div>
                        <div>附表二 出具之施工廠商履約情形<b-link href="#foo" disabled>計分表</b-link></div>
                        <div>
                          附表三 機關辦理履約計分及覆核作業
                          <b-link href="#foo" disabled>檢核表</b-link>
                        </div>
                        <div>
                          附表四 處理意見
                          <b-link href="#foo" disabled>作業流程</b-link>
                        </div>
                      </div>
                    </div>
                  </b-row>
                  <b-row class="pt-3">
                    <div>
                      <div>
                        <div class="text-danger">106.09.07</div>
                        工程管字第
                        <b-link href="#foo" disabled>10600283240</b-link>
                        號函　本會公共工程施工廠商履約情形計分資料，自106年9月15日起開放其細部組成供各工程主管（辦）機關查詢參考...
                      </div>
                    </div>
                  </b-row>
                  <b-row class="pt-3">
                    <div>
                      <div>
                        <div class="text-danger">105.12.23</div>
                        工程管字第
                        <b-link href="#foo" disabled>10500406700</b-link>
                        本會 「公共工程施工廠商履約情形計分制度」之廠商計分資料，自106年1月1日起開放各工程主管（辦）機關查詢參考...
                      </div>
                    </div>
                  </b-row>
                  <b-row class="pt-3">
                    <div>
                      <div>
                        <div class="text-danger">105.09.26</div>
                        工程管字第
                        <b-link href="#foo" disabled>10500307360</b-link>號函 修正
                        <b-link href="#foo" disabled>「公共工程施工廠商履約情形計分要點」</b-link>
                        ，並自即日起生效...
                      </div>
                    </div>
                  </b-row>
                  <b-row class="pt-3">
                    <div>
                      <div>
                        <div class="text-danger">103.11.17</div>
                        工程管字第
                        <b-link href="#foo" disabled>10300399570</b-link>
                        號函 為配合「公共工程施工廠商履約情形計分要點」推動，並協助工程主辦機關辦理計分作業...
                      </div>
                      <div>
                        <div>公共工程施工廠商履約情形計分<b-link href="#foo" disabled>填報說明</b-link></div>
                      </div>
                    </div>
                  </b-row>
                  <b-row class="pt-3">
                    <div>
                      <div>
                        <div class="text-danger">103.10.27</div>
                        工程管字第
                        <b-link href="#foo" disabled>10300375820</b-link>號函 修正
                        <b-link href="#foo" disabled>「公共工程施工廠商履約情形計分要點」</b-link>
                        ，並自即日起生效...
                      </div>
                    </div>
                  </b-row>
                </div>
              </b-col>
            </b-row>
          </div>
          <!-- 最下面 -->
          <div class="container">
            <div>
              <div>工程會解釋函：</div>
              <div>
                <div class="text-danger">106.07.04</div>
                <div>有關貴府函詢解除或終止契約本會「公共工程施工廠商履約計分要點」填報作業疑義，復如說明，請查照。</div>
                <div>高公局南工處來函內容</div>
              </div>
              <div>
                <div class="text-danger">104.03.02</div>
                <div>有關貴府函詢開口契約本會「公共工程施工廠商履約計分要點」填報作業疑義，復如說明，請查照。</div>
                <div>台北市政府來函內容</div>
              </div>
              <div>
                <div class="text-danger">104.03.23</div>
                <div>為貴部函詢海外工程辦理公共工程施工廠商履約計分之作業疑義，復如說明，請查照。</div>
                <div>外交部來函內容</div>
              </div>
              <div>
                <div class="text-danger">104.04.28</div>
                <div>
                  為貴處函詢本會標案管理系統內之廠商履約情形計分清單無法查詢其他機關案件情形乙案，復如說明，請查照。(已於106年1月1日於系統中開放查詢)
                </div>
                <div>台北市政府工務局公園路燈管理處來函內容</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <i-pdf-viewer ref="pdfViewer" />
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, onActivated } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidKpifinService from '@/components/bid/bidService/bid-kpifin-service';
import BidChkaptService from '@/components/bid/bidService/bid-chkapt.service';
import BidPrjRescisService from '@/components/bid/bidService/bid-prj-rescis.service';
import { IBidKpifin, BidKpifin } from '@/shared/model/bidModel/bid-kpifin.model';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { navigateByNameAndParams } from '@/router/router';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';

export default {
  name: 'bidAad008InfoMenu',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    //在keep alive的時候保留原本的project資料
    const tempProjectKey = {
      wkut: '',
      prjno: '',
    };
    setTempProjectKey();
    //判斷是否可以計分
    const canProjectGoKpifin = ref(false);
    const errorMessage = ref('');

    const bidProjectService = new BidProjectService();
    const bidKpifinService = new BidKpifinService();
    const bidChkaptService = new BidChkaptService();
    const bidPrjRescisService = new BidPrjRescisService();
    let bidSysCodeTable = new BidSysCodeTable();
    const projectInfo = reactive({ wkut: '', prjno: '' });
    const notificationService: NotificationService = useNotification();
    enum formStatusEnum {
      CREATE = 'create',
      MODIFY = 'modify',
      INFO = 'info',
    }
    //判斷表單狀態
    let formStatus = ref(null);
    //kpi物件
    let bidKpifin = reactive(new BidKpifin());

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }
    //撈出該project的kpifin並判斷是要 新增 還是 修改
    function fetchKpifinList(wkut, prjno) {
      bidKpifinService.getBidKpifinListByCriteria(wkut, prjno).then(kpifinDTOs => {
        if (kpifinDTOs && kpifinDTOs.length > 0) {
          //修改
          // console.log('修改');
          formStatus.value = formStatusEnum.MODIFY;
          Object.assign(bidKpifin, kpifinDTOs[0]);
        } else {
          //新增
          // console.log('新增');
          formStatus.value = formStatusEnum.CREATE;
          bidKpifin = {};
          Object.assign(bidKpifin, new BidKpifin());
        }
      });
    }

    function judgeCanProjectGoKpifin(wkut, prjno) {
      let regexp = new RegExp(/.*\u5df2\u89e3\u7d04.*/); //%已解約%

      bidProjectService.findBidProjectByWkutAndPrjno(wkut, prjno).then(bidProjectDTO => {
        bidChkaptService.getLatestBidChkaptByCriteria({ wkut: wkut, prjno: prjno }).then(bidChkApt => {
          if (bidChkApt.aokDate === null || bidChkApt.aokDate === undefined) {
            canProjectGoKpifin.value = false;
            errorMessage.value = '驗收資料(B6)尚未填報，不開放計分';
          } else if (bidProjectDTO.finWays === null || bidProjectDTO.finWays === undefined || bidProjectDTO.finWays <= 0) {
            canProjectGoKpifin.value = false;
            errorMessage.value = '驗收資料(B6)最終核定工期為0不合理，不開放計分';
            if ('K' === bidProjectDTO.typeCode) {
              errorMessage.value.concat('\n', '本案為開口合約，如完全未派工，可免辦理計分，直接至C6填報結案資料');
            }
          }
          bidPrjRescisService.getLatestBidPrjRescis({ wkut: wkut, prjno: prjno }).then(bidPrjRescis => {
            if (regexp.test(bidProjectDTO.name) && ('A' === bidPrjRescis.blong || 'B' === bidPrjRescis.blong)) {
              canProjectGoKpifin.value = false;
              errorMessage.value = '  已解約，解約原因為甲方或甲乙雙方，可免填報計分';
            }
          });
          if (errorMessage.value.length === 0) {
            canProjectGoKpifin.value = true;
          }
        });
      });
    }

    function setTempProjectKey() {
      if (bidProjectKeyProp.value) {
        tempProjectKey.wkut = bidProjectKeyProp.value.wkut;
        tempProjectKey.prjno = bidProjectKeyProp.value.prjno;
      }
    }

    function addNewKpi() {
      navigateByNameAndParams('bidAad008Edit', {
        bidProjectKey: { wkut: tempProjectKey.wkut, prjno: tempProjectKey.prjno },
        bidKpifinKey: { wkut: tempProjectKey.wkut, prjno: tempProjectKey.prjno },
        formStatus: { formStatus: formStatusEnum.CREATE },
      });
    }

    function modifyKpi() {
      navigateByNameAndParams('bidAad008Edit', {
        bidProjectKey: { wkut: tempProjectKey.wkut, prjno: tempProjectKey.prjno },
        bidKpifinKey: { wkut: tempProjectKey.wkut, prjno: tempProjectKey.prjno, idate: bidKpifin.idate, seq: bidKpifin.seq },
        formStatus: { formStatus: formStatusEnum.MODIFY },
      });
    }

    function deleteNewKpi() {
      navigateByNameAndParams('bidAad008Edit', {
        bidProjectKey: { wkut: tempProjectKey.wkut, prjno: tempProjectKey.prjno },
        bidKpifinKey: { wkut: tempProjectKey.wkut, prjno: tempProjectKey.prjno, idate: bidKpifin.idate, seq: bidKpifin.seq },
        formStatus: { formStatus: formStatusEnum.INFO },
      });
    }
    //印出pdf
    const pdfViewer = ref(null);
    function getBidModifyReport() {
      let criteria = {
        wkut: tempProjectKey.wkut,
        prjno: tempProjectKey.prjno,
        idate: bidKpifin.idate,
        seq: bidKpifin.seq,
      };
      bidKpifinService
        .printBidKpifinReport(criteria)
        .then(result => {
          let blob = new Blob([result.data], { type: 'application/pdf' });
          let url = window.URL.createObjectURL(blob);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => {
          notificationService.danger('製表失敗');
        });
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        setTempProjectKey();
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          fetchKpifinList(newValue.wkut, newValue.prjno);
          judgeCanProjectGoKpifin(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    onActivated(() => {
      if (bidProjectKeyProp.value) {
        // console.log('onActivated', bidProjectKeyProp.value.wkut + ',' + bidProjectKeyProp.value.prjno);
        setTempProjectKey();
        fetchBidProjectInfo(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
        fetchKpifinList(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
        judgeCanProjectGoKpifin(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
      } else if (tempProjectKey.wkut.length > 0) {
        fetchBidProjectInfo(tempProjectKey.wkut, tempProjectKey.prjno);
        fetchKpifinList(tempProjectKey.wkut, tempProjectKey.prjno);
        judgeCanProjectGoKpifin(tempProjectKey.wkut, tempProjectKey.prjno);
      }
    });

    return {
      projectInfo,
      errorMessage, //如果不能進行的話的錯誤訊息
      formStatus,
      formStatusEnum,
      bidKpifin,
      pdfViewer,
      bidProjectKeyProp,
      addNewKpi,
      modifyKpi,
      deleteNewKpi,
      canProjectGoKpifin, //判斷可否進行kpifin
      getBidModifyReport,
    };
  },
};
</script>

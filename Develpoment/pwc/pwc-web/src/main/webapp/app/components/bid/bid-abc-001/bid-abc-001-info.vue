<template>
  <div>
    <section class="container">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">查核(督導)紀錄</h5>
        </div>
        <div class="card-body">
          <div class="row">
            <div class="col"><i-button size="md" type="folder-plus"></i-button></div>
            <div class="col">查核督導紀錄僅限該次查核之查核(督導)小組單位之帳號密碼可修改，其他機關請通知該單位修正。</div>
          </div>
          <p></p>
          <div>
            <i-table
              class="table-sm"
              :itemsUndefinedBehavior="'loading'"
              :items="table.data"
              :fields="table.fields"
              :totalItems="table.totalItems"
              :is-server-side-paging="true"
              @changePagination="handlePaginationChanged($event)"
            >
              <template #cell(chkLeadString)="row">
                <p style="white-space: pre-wrap; margin-bottom: 0" v-text="row.item.chkLeadString"></p>
              </template>
              <template #cell(action)="row">
                <i-button size="md" type="pencil-square" @click="toEdit"></i-button>
                <b-button variant="info" @click="showCheckerrModal(row.item)">缺失編號</b-button>
                <b-button variant="info" @click="toReport(row.item)">審查表</b-button>
              </template>
            </i-table>
          </div>
        </div>
      </div>
    </section>
    <!-- 缺失編號 -->
    <b-modal
      id="checkerr-view-modal"
      size="sm"
      title="缺失編號"
      header-bg-variant="secondary"
      header-text-variant="light"
      centered
    >
      <b-row>
        <b-col class="text-center">
          <p style="white-space: pre-wrap; margin-bottom: 0">{{ checkerr1 }}</p>
        </b-col>
        <b-col class="text-center">
          <p style="white-space: pre-wrap; margin-bottom: 0">{{ checkerr2 }}</p>
        </b-col>
      </b-row>
      <template #modal-footer="{ cancel }">
        <div class="col-12 text-center">
          <b-button size="md" @click="cancel">關閉</b-button>
        </div>
      </template>
    </b-modal>
    <i-pdf-viewer ref="pdfViewer" />
  </div>
</template>

<script lang="ts">
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import BidGovCheckService from '@/components/bid/bidService/bid-gov-check.service';
import BidGovCheckerrService from '@/components/bid/bidService/bid-gov-checkerr.service';
import BidUnit10Servie from '@/components/bid/bidService/bid-unit10.service';
import NotificationService from '@/shared/notification/notification-service';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { onMounted, reactive, ref, toRef, watch, Ref } from '@vue/composition-api';
import { required } from '@/shared/validators';
import { handleBack } from '@/router/router';
import { navigateByNameAndParams } from '@/router/router';
import { Pagination } from '@/shared/model/pagination.model';
import { useGetters } from '@u3u/vue-hooks';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { formatDate } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'bidAbc001Info',
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  components: {
    bidProjectInfo,
  },
  setup(props, context) {
    const pdfViewer = ref(null);

    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();
    const admSysCodeService = new AdmSysCodeService();
    const bidGovCheckService = new BidGovCheckService();
    const bidSysCodeTable = new BidSysCodeTable();
    const bidUnit10Servie = new BidUnit10Servie();
    const bidGovCheckerrService = new BidGovCheckerrService();

    const tempProjectKey = {
      wkut: '',
      prjno: '',
    };
    setTempProjectKey();

    const queryKey = {
      wkut: tempProjectKey.wkut,
      prjno: tempProjectKey.prjno,
    };

    let checkerr1 = ref('');
    let checkerr2 = ref('');

    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
    }

    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();

    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
          result.typeCode = data;
          Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        });
      });
    }

    function setTempProjectKey() {
      if (bidProjectKeyProp.value) {
        tempProjectKey.wkut = bidProjectKeyProp.value.wkut;
        tempProjectKey.prjno = bidProjectKeyProp.value.prjno;
      }
    }

    //取的bidGovCheck資料
    function getBidGovCheckData(criteria) {
      console.log(criteria);
      bidGovCheckService.getGovCheckPageByCriteria(criteria).then(govCheckData => {
        govCheckData.content.forEach(govCheckElement => {
          // 辦理日期
          govCheckElement.checkDateString = formatDate(new Date(govCheckElement.checkDate), '/');
          if (govCheckElement.chkflag !== 'Y') {
            govCheckElement.checkDateString = govCheckElement.checkDate + '(尚未奉核)';
          }
          // 小組名稱
          bidUnit10Servie.findAdmAgentyCode(govCheckElement.checkUnit).then(admAgent => {
            govCheckElement.checkUnit = admAgent.orgName;
          });
          // 型式
          bidCommonService.bidGovCheckRoveChk5Converter(govCheckElement.roveChk5).then(sysCodeData => {
            govCheckElement.roveChk5String = sysCodeData;
          });
          // 參與人員
          if (govCheckElement.chkLead !== null && govCheckElement.chkLead !== undefined) {
            govCheckElement.chkLeadString = govCheckElement.chkLead + '\n' + govCheckElement.checker;
          } else {
            govCheckElement.chkLeadString = '(未填)'
          }
          table.data = govCheckData.content;
          table.totalItems = govCheckData.totalElements;
        });
      });
    }

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;
    //  const fromName = useStore().value.getters.routeData.fromName;

    //取得使用者資訊ip
    const store = context.root.$store;
    const userIp = store.getters.account.ip;

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      sdate: null, //裁處日期
      ctUnit: '', //裁處單位
      prmNo: '', //裁處文號
      ccut: '', //受處分單位
      stype: '', //裁處類型
      violateDate: null, //違反日期
      wdayed: '', //發生前累計工期
      violateFact: '', //違反事實
      prmTitle: '', //裁處主旨
      createDate: null,
      createUser: '',
      uIp: '',
    };

    // 表單物件驗證規則
    const rules = {
      sdate: { notnull: required }, //裁處日期
      ctUnit: { notnull: required }, //裁處單位
      prmNo: { notnull: required }, //裁處文號
      ccut: {}, //受處分單位
      stype: {}, //裁處類型
      violateDate: { notnull: required }, //違反日期
      wdayed: { notnull: required }, //發生前累計工期
      violateFact: { notnull: required }, //違反事實
      prmTitle: { notnull: required }, //裁處主旨
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    const notificationService: NotificationService = useNotification();

    // 是否需要重新查詢
    const isReload = ref(false);

    // 執行結果
    // const queryStatus = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    //下拉試選單響應試
    const ccutnitoptions = ref([]);
    const ccutnitoptionss = ref([]);

    // 下拉選單選項
    const queryOptions = reactive({
      BID_021: [],
      BID_022: [],
    });

    const table = reactive({
      fields: [
        {
          key: 'checkDateString',
          label: '辦理日期',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },

        {
          key: 'checkUnit',
          label: '小組名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'roveChk5String',
          label: '型式',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'chkLeadString',
          label: '參與人員',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: '動作',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width:25%',
        },
      ],
      data: undefined,
      totalItems: 2,
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      wkut: '',
      prjno: '',
      pname: '',
      id: '',
      job: '',
      certNo: '',
      startDate: '',
      endDate: '',
      duty: '',
      dsts: '',
      createDate: '',
      updateDate: '',
      tel: '',
      isJoing: '',
      remark: '',
    };

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      pname: {},
      id: {},
      job: {},
      certNo: {},
      startDate: {},
      endDate: {},
      duty: {},
      dsts: {},
      createDate: {},
      updateDate: {},
      tel: {},
      isJoing: {},
      remark: {},
    };
    //區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    //查詢頁面的編輯
    // const toEdit = bidProjectKeyProp => {
    //   navigateByNameAndParams('bidAbc001EditInfo', bidProjectKeyProp);
    // };

    const toEdit = () => {
      navigateByNameAndParams('bidAbc001EditInfo', { bidProjectKeyProp: props.bidProjectKey, isNotKeepAlive: true });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(criteria, pagination);
      getBidGovCheckData(criteria);
    };

    const createDefaultValue = data => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      reset();
    };

    function showCheckerrModal(criteria) {
      checkerr1.value = '';
      checkerr2.value = '';
      bidGovCheckerrService.findAllByCriteria(criteria).then(res => {
          let num = res.length / 2;
          res.forEach((element, index) => {
            if (index < num) {
              checkerr1.value += element.errNum + '\n';
            } else {
              checkerr2.value += element.errNum + '\n';
            }
          });
        });
      $bvModals.show('checkerr-view-modal');
    }

    function toReport(criteria) {
      axios
        .post('/bid-gov-check/bidAae034D', criteria, {responseType: 'blob'})
        .then(res => {
          let blob = new Blob([res.data], { type: "application/pdf" });
          let url = window.URL.createObjectURL(blob);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          getBidGovCheckData(newValue);
        }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      formDefault,
      $v,
      reset,
      FormStatusEnum,
      validateState,
      dataPromise,
      table,
      stepVisible,
      queryOptions,
      toEdit,
      handlePaginationChanged,
      userIdentity,
      ccutnitoptionss,
      $bvModals,
      showCheckerrModal,
      checkerr1,
      checkerr2,
      toReport,
      pdfViewer,
    };
  },
};
</script>
<style></style>

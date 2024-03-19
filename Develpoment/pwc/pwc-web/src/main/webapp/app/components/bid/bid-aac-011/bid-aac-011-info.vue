<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">監督付款或連帶保證詳細資料</h5>
        </div>

        <div class="card-body col-12">
          <div>
            <i-button size="md" type="folder-plus" variant="outline-secondary" @click="toAdd"></i-button>
          </div>
          <div>
            <i-table
              border0
              ref="iTable"
              class="table-sm"
              :itemsUndefinedBehavior="'loading'"
              :items="table.data"
              :fields="table.fields"
              :totalItems="table.totalItems"
              :is-server-side-paging="true"
              @changePagination="handlePaginationChanged($event)"
            >
              <template #cell(action)="row">
                <i-button type="pencil-square" @click="toEdit(row.item)"></i-button>
              </template>
            </i-table>
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
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidPrjStopwkService from '@/components/bid/bidService/bid-prj-stopwk.service';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import NotificationService from '@/shared/notification/notification-service';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { onMounted, reactive, ref, toRef, watch, Ref } from '@vue/composition-api';
import { required } from '@/shared/validators';
import { handleBack } from '@/router/router';
import { Pagination } from '@/shared/model/pagination.model';
import { useGetters } from '@u3u/vue-hooks';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import { formOptionsFormatter } from '@/shared/formatter/common';

export default {
  name: 'bidAac011Info',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const pdfViewer = ref(null);

    const tempProjectKey = {
      wkut: '',
      prjno: '',
    };

    onMounted(() => {
      getAllSysCodes();
    });

    // setTempProjectKey();
    const admSysCodeService = new AdmSysCodeService();
    const bidProjectService = new BidProjectService();
    const bidPrjStopwkService = new BidPrjStopwkService();
    let bidSysCodeTable = new BidSysCodeTable();
    const iTable = ref(null);
    const projectInfo = reactive({ wkut: '', prjno: '' });

    enum formStatusEnum {
      CREATE = '新增',
      MODIFY = '編輯',
      READONLY = '檢視',
    }

    const dataPromise = ref(null);
    const notificationService: NotificationService = useNotification();

    const formOptions = reactive({
      bid054: [],
    });

    const table = reactive({
      fields: [
        {
          //機關同意日期
          key: 'startDate',
          label: '機關同意日期',
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          //採用方式
          key: 'useType',
          label: '採用方式',
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.bid054, value),
        },
        {
          //同意文號
          key: 'prmNo',
          label: '同意文號',
          sortable: false,
          // thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //後續執行廠商
          key: 'nccUnit',
          label: '後續執行廠商',
          sortable: false,
          // thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => formOptionsFormatter(formOptions.approveLevel, value),
        },

        {
          key: 'action',
          label: '功能',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: 'width:10%',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    function dateDiffinDays(startDate: Date, endDate: Date): number {
      let endDateTime = endDate.getTime();
      let startDateTime = startDate.getTime();
      return Math.floor((endDateTime - startDateTime) / (24 * 3600 * 1000));
    }
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

    function handlePaginationChanged(pagination: Pagination) {
      let queryCriteria = {
        wkut: tempProjectKey.wkut,
        prjno: tempProjectKey.prjno,
      };
      Object.assign(queryCriteria, pagination);
      getProjectStopwkByCriteria(queryCriteria);
    }

    const handleQuery = () => {
      table.data = undefined;
      const projectPK = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      dataPromise.value = axios
        .post('/bid-prj-gdfk/criteria-jpa', projectPK) //後端端分頁(呼叫後端)\
        .then(({ data }) => {
          // table.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
          // table.totalItems = data.length; //前端分頁
          table.data = data.content.slice(0, data.content.length); //後端分頁
          table.totalItems = data.totalElements; //後端分頁
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    //新增
    const toAdd = () => {
      navigateByNameAndParams('bidAac011Edit', { bidProjectKeyProp: props.bidProjectKey, isNotKeepAlive: false });
    };

    //檢視
    const toEdit = bidProjectKeyProp => {
      navigateByNameAndParams('bidAac011Edit', {
        bidProjectKeyProp: bidProjectKeyProp,
        // formStatus: FormStatusEnum.MODIFY,
        isNotKeepAlive: false,
      });
    };

    //製報表
    const toTable = bidProject => {
      let criteria = {
        wkut: bidProject.wkut,
        prjno: bidProject.prjno,
        agreeDate: bidProject.agreeDate,
      };
      console.log('criteriacriteriacriteria:', criteria);
      axios
        .post('/bid-prj-segmset/bidAae022', criteria, { responseType: 'blob' })
        .then(res => {
          let blob = new Blob([res.data], { type: 'application/pdf' });
          let url = window.URL.createObjectURL(blob);
          console.log(context);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    };

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'BID');

      //自訂中文
      // formOptions.status = [
      //   { value: 'A', text: '目前尚未同意設置' },
      //   { value: 'B', text: '符合設置及拆除管理要點第三點第一款' },
      // ];
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        setTempProjectKey();
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          handleQuery();

          // fetchProjectAllstopwk(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    onActivated(() => {
      if (bidProjectKeyProp.value) {
        setTempProjectKey();
        // fetchProjectAllstopwk(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
      } else if (tempProjectKey.wkut.length > 0) {
        // fetchProjectAllstopwk(tempProjectKey.wkut, tempProjectKey.prjno);
      }
    });

    return {
      iTable,
      projectInfo,
      table,
      bidProjectKeyProp,
      dataPromise,
      handlePaginationChanged,
      handleQuery,
      toTable,
      pdfViewer,
      formStatusEnum,
      toAdd,
      toEdit,
      getAllSysCodes,
      formOptions,
    };
  },
};
</script>

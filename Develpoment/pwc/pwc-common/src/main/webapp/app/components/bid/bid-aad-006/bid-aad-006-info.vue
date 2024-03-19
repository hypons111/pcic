<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">承造商工地人員需求</h5>
        </div>

        <div class="card-body col-12">
          <div>
            <i-button size="md" type="folder-plus" variant="outline-secondary" @click="toAdd"></i-button>
            <b-button size="md" style="background-color: #1aa4b7" @click="toTableA">人力需求表(技術工)</b-button>
            <b-button size="md" style="background-color: #1aa4b7" @click="toTableB">人力需求表(技術士)</b-button>
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
              <template v-slot:cell(yearMonth)="slot"> {{ slot.item.syr }} /{{ slot.item.month }} </template>
              <template #cell(action)="row">
                <i-button type="pencil-square" @click="toEdit(row.item)"></i-button>
                <!-- <i-button size="md" type="concrete" variant="outline-secondary" @click="toTable(row.item)"></i-button> -->
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
import bidProjectInfo2 from '@/components/bid/bid-common-component/bid-project-info2.vue';
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
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import { formOptionsFormatter } from '@/shared/formatter/common';

export default {
  name: 'bidAad006Info',
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
      // getAllSysCodes();
    });

    // setTempProjectKey();
    const admSysCodeService = new AdmSysCodeService();
    const bidProjectService = new BidProjectService();
    const bidPrjStopwkService = new BidPrjStopwkService();
    let bidSysCodeTable = new BidSysCodeTable();
    const iTable = ref(null);
    const projectInfo = reactive({ wkut: '', prjno: '' });

    enum FormStatusEnum {
      CREATE = '新增',
      MODIFY = '編輯',
      READONLY = '檢視',
    }

    const dataPromise = ref(null);
    const notificationService: NotificationService = useNotification();

    const formOptions = reactive({
      status: [],
    });

    const table = reactive({
      fields: [
        {
          //年月
          key: 'yearMonth',
          label: i18n.t('label.syr') + '/' + i18n.t('label.month'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          //現場工程師
          key: 'person02',
          label: i18n.t('label.person02'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //鋼筋工
          key: 'worker01',
          label: i18n.t('label.worker01'),
          sortable: false,
          // thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //模板工
          key: 'aworker02',
          label: i18n.t('label.worker02'),
          sortable: false,
          // thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //泥水工
          key: 'aworker03',
          label: i18n.t('label.worker03'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //普通工
          key: 'aworker04',
          label: i18n.t('label.worker04'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //機械操作工
          key: 'aworker05',
          label: i18n.t('label.worker05'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //體力工
          key: 'aworker06',
          label: i18n.t('label.worker06'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //建築細木工
          key: 'aworker07',
          label: i18n.t('label.worker07'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //水電工
          key: 'aworker08',
          label: i18n.t('label.worker08'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //混凝土澆置工
          key: 'aworker09',
          label: i18n.t('label.worker09'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //路面鋪設工
          key: 'aworker10',
          label: i18n.t('label.worker10'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //砌石工
          key: 'aworker11',
          label: i18n.t('label.worker11'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          //其他工
          key: 'aworker12',
          label: i18n.t('label.worker12'),
          sortable: false,
          // thStyle: 'width:5%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
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
        .post('/bid-prj-wksorc/criteria-jpa', projectPK) //後端端分頁(呼叫後端)\
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
      navigateByNameAndParams('bidAad006Edit', {
        bidProjectKeyProp: props.bidProjectKey,
        formStatus: FormStatusEnum.CREATE,
        isNotKeepAlive: false,
      });
    };

    //檢視
    const toEdit = data => {
      navigateByNameAndParams('bidAad006Edit', {
        bidProjectKeyProp: data,
        formStatus: FormStatusEnum.MODIFY,
        isNotKeepAlive: false,
      });
    };

    //製報表(技術工)
    const toTableA = bidProject => {
      let criteria = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      axios
        .post('/bid-prj-wksorc/bidAae030A', criteria, { responseType: 'blob' })
        .then(res => {
          let blob = new Blob([res.data], { type: 'application/pdf' });
          let url = window.URL.createObjectURL(blob);
          console.log(context);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    };

    //製報表(技術士)
    const toTableB = bidProject => {
      let criteria = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
        syr: bidProject.agreeDate,
        month: bidProject.agreeDate,
      };
      console.log('criteriacriteriacriteria:', criteria);
      axios
        .post('/bid-prj-wksorc/bidAae030B', criteria, { responseType: 'blob' })
        .then(res => {
          let blob = new Blob([res.data], { type: 'application/pdf' });
          let url = window.URL.createObjectURL(blob);
          console.log(context);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    };

    // function getAllSysCodes() {
    //   admSysCodeService.setFormOptions(formOptions, 'BID');
    //   formOptions.status = [
    //     { value: '1', text: '目前尚未同意設置' },
    //     { value: '2', text: '符合設置及拆除管理要點第三點第一款' },
    //     { value: '3', text: '符合設置及拆除管理要點第三點第二款' },
    //   ];
    // }

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
      toTableA,
      toTableB,
      pdfViewer,
      FormStatusEnum,
      toAdd,
      toEdit,
      // getAllSysCodes,
      formOptions,
    };
  },
};
</script>

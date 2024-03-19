<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <i-button type="file-earmark-plus" @click="toNewModify"></i-button>
    <b-button variant="info" @click="getBidModifyReport">變更設計表</b-button>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">經費變更期程資料</h5>
        </div>
        <div></div>
        <div class="card-body col-12">
          <div class="container">
            <i-table
              border0
              title="經費變更期程"
              ref="iTable"
              class="table-sm"
              :itemsUndefinedBehavior="'loading'"
              :items="prjModifyQueryTable.data"
              :fields="prjModifyQueryTable.fields"
              :totalItems="prjModifyQueryTable.totalItems"
              :is-server-side-paging="true"
              hide-card-header
              @changePagination="handlePaginationChanged($event)"
            >
              <template #thead-top>
                <b-tr>
                  <b-th colspan="3"></b-th>
                  <b-th colspan="2">契約金額(千元)</b-th>
                  <b-th colspan="2">預定竣工日</b-th>
                  <b-th colspan="2"></b-th>
                </b-tr>
              </template>
              <template #cell(chgDate)="row">
                {{ row.item.chgDate ? formatDate(new Date(row.item.chgDate), '/') : '(未填)' }}
              </template>
              <template #cell(oldAmt)="row">{{ row.item.oldAmt | commaFilter }} </template>
              <template #cell(newAmt)="row">
                {{ row.item.newAmt | commaFilter }}
              </template>
              <template #cell(oldDate)="row">
                {{ row.item.oldDate ? formatDate(new Date(row.item.oldDate), '/') : '(未填)' }}
              </template>
              <template #cell(newDate)="row">
                {{ row.item.newDate ? formatDate(new Date(row.item.newDate), '/') : '(未填)' }}
              </template>
              <template #cell(action)="row">
                <i-button type="binoculars" @click="toUpadteModifyView(row.item)"></i-button>
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
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidPrModifyService from '@/components/bid/bidService/bid-prj-modify.service';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import { commaFormatter } from '@/shared/formatter/common';

export default {
  name: 'bidAaa003InfoMenu',
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
    const pdfViewer = ref(null);
    const bidProjectService = new BidProjectService();
    const bidPrModifyService = new BidPrModifyService();
    const iTable = ref(null);
    const addNewModify = ref(false);
    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });
    const notificationService: NotificationService = useNotification();

    enum formStatusEnum {
      CREATE = 'create',
      MODIFY = 'modify',
    }
    const prjModifyQueryTable = reactive({
      fields: [
        {
          key: 'chgDate',
          label: '變更日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'okNo',
          label: '核准文號',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'oldAmt',
          label: '變更前',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'newAmt',
          label: '變更後',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'oldDate',
          label: '變更前',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'newDate',
          label: '變更後',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'nDays',
          label: '准延天數',
          sortable: false,
          thStyle: 'width:15%',
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
    //分頁用
    function handlePaginationChanged(pagination: Pagination) {
      let queryCriteria = {
        wkut: tempProjectKey.wkut,
        prjno: tempProjectKey.prjno,
      };
      Object.assign(queryCriteria, pagination);
      fetchProjectAllModifyByCriteria(queryCriteria);
    }
    //單純查
    function fetchProjectAllModify(wkut, prjno) {
      let queryCriteria = {
        wkut: wkut,
        prjno: prjno,
      };
      if (iTable.value) iTable.value.state.pagination.currentPage = 1;
      fetchProjectAllModifyByCriteria(queryCriteria);
    }

    function fetchProjectAllModifyByCriteria(criteria) {
      bidPrModifyService.findProjectAllModify(criteria).then(data => {
        prjModifyQueryTable.data = data.content;
        prjModifyQueryTable.totalItems = data.totalElements;
      });
    }

    function toNewModify() {
      let bidProjectModifyKeyProp = bidProjectKeyProp.value ? bidProjectKeyProp.value : tempProjectKey;
      navigateByNameAndParams('bidAaa003Edit', {
        bidProjectModifyKey: bidProjectModifyKeyProp,
        formStatus: formStatusEnum.CREATE,
      });
    }

    function toUpadteModifyView(bidPrjModifyData) {
      const bidPrjModifyKey = {
        wkut: bidPrjModifyData.wkut,
        prjno: bidPrjModifyData.prjno,
        chgDate: bidPrjModifyData.chgDate,
      };
      navigateByNameAndParams('bidAaa003Edit', {
        bidProjectModifyKey: bidPrjModifyKey,
        formStatus: formStatusEnum.MODIFY,
      });
    }

    //去表格顯示欄位，要帶project的key
    function toTable() {
      let projectKey = {
        wkut: '',
        prjno: '',
      };
      if (bidProjectKeyProp.value) {
        projectKey.wkut = bidProjectKeyProp.value.wkut;
        projectKey.prjno = bidProjectKeyProp.value.prjno;
      } else if (tempProjectKey.wkut.length > 0) {
        projectKey.wkut = tempProjectKey.wkut;
        projectKey.prjno = tempProjectKey.prjno;
      }

      navigateByNameAndParams('bidAaa003Table', {
        bidProjectKey: projectKey,
      });
    }

    function getBidModifyReport() {
      let criteria = {
        wkut: tempProjectKey.wkut,
        prjno: tempProjectKey.prjno,
      };
      bidPrModifyService
        .printPrjModifyReportPDF(criteria)
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
        if (newValue !== null && newValue !== undefined) {
          setTempProjectKey();
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          fetchProjectAllModify(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    onActivated(() => {
      if (bidProjectKeyProp.value) {
        setTempProjectKey();
        fetchProjectAllModify(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
      } else if (tempProjectKey.wkut.length > 0) {
        fetchProjectAllModify(tempProjectKey.wkut, tempProjectKey.prjno);
      }
    });

    return {
      iTable,
      projectInfo,
      prjModifyQueryTable,
      bidProjectKeyProp,
      addNewModify,
      toNewModify,
      toUpadteModifyView,
      toTable,
      pdfViewer,
      getBidModifyReport,
      handlePaginationChanged,
      formatDate,
    };
  },
  filters: {
    commaFilter: (value: any) => commaFormatter(value, 3),
  },
};
</script>

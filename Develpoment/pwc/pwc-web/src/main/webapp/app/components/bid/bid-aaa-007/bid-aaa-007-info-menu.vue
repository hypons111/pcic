<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo"
                              :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>

    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">送機關備審之分包情形廠商資料</h5>
          <div style="margin: 10px 0px 10px 0px">
            <i-button type="file-earmark-plus" @click="toNewSubco"></i-button>
            <b-button variant="info" @click="toTable">送機關備審之分包情形廠商表</b-button>
          </div>
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
              :items="prjSubcoQueryTable.data"
              :fields="prjSubcoQueryTable.fields"
              :totalItems="prjSubcoQueryTable.totalItems"
              :is-server-side-paging="true"
              @changePagination="handlePaginationChanged($event)"
            >
              <template #cell(action)="row">
                <i-button type="pencil-square" @click="toUpadteSubcoView(row.item)"></i-button>
              </template>
            </i-table>
          </div>
        </div>
        <!-- 變更顯示的 列表 or 單一 -->
        <div>

        </div>
      </div>
    </section>

    <i-pdf-viewer ref="pdfViewer" />

  </div>
</template>

<script lang="ts">
import {Ref, ref, toRef, reactive, watch, onActivated} from '@vue/composition-api';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import {navigateByNameAndParams} from '@/router/router';
import BidPrjSubcoService from "@/components/bid/bidService/bid-prj-subco.service";
import axios from "axios";
import NotificationService from "@/shared/notification/notification-service";
import {useNotification} from "@/shared/notification";

export default {
  name: 'bidAaa007InfoMenu',
  components: {bidProjectInfo},
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

    const ccutKey = {
      subcoNo: '',
      subCcut:'',
    };

    if (bidProjectKeyProp.value) {
      tempProjectKey.wkut = bidProjectKeyProp.value.wkut;
      tempProjectKey.prjno = bidProjectKeyProp.value.prjno;
    }
    const bidProjectService = new BidProjectService();
    const bidPrjSubcoService = new BidPrjSubcoService();
    const iTable = ref(null);
    const addNewSubco = ref(false);
    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({wkut: '', prjno: ''});

    const notificationService: NotificationService = useNotification();

    enum formStatusEnum {
      CREATE = 'create',
      MODIFY = 'modify',
    }

    const prjSubcoQueryTable = reactive({
      fields: [
        {
          key: 'subCcut',
          label: '主承或分包協力廠商',
          sortable: false,
          thStyle: 'width:auto',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'spcw',
          label: '專業工程項目',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'eCode',
          label: '機具提供',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'subcoMemo',
          label: '工作內容',
          sortable: false,
          thStyle: 'width:20%',
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

    function fetchProjectAllSubco(wkut, prjno) {
      bidPrjSubcoService.findPrjSubcoAllData(wkut, prjno).then(data => {

        ccutKey.subcoNo = data[0].subcoNo;
        console.log('data',data)
        prjSubcoQueryTable.data = data;
        prjSubcoQueryTable.totalItems = data.totalElements;
      });
    }

    function toNewSubco() {
      let bidProjectSubcoKeyProp = bidProjectKeyProp.value ? bidProjectKeyProp.value : tempProjectKey;

      console.log('bidProjectSubcoKeyProp',bidProjectSubcoKeyProp)

      const bidPrjSubcoKey = {
        wkut: bidProjectSubcoKeyProp.wkut,
        prjno: bidProjectSubcoKeyProp.prjno,
        subcoNo: bidProjectSubcoKeyProp.subcoNo,
      };

      navigateByNameAndParams('bidAaa007Edit', {
        // bidProjectSubcoKey: bidProjectSubcoKeyProp,
        bidProjectKey: bidPrjSubcoKey,
        formStatus: formStatusEnum.CREATE,
      });
    }

    function toUpadteSubcoView(bidPrjSubcoData: I) {

      const bidPrjSubcoKey = {
        wkut: bidPrjSubcoData.wkut,
        prjno: bidPrjSubcoData.prjno,
        subcoNo: bidPrjSubcoData.subcoNo,
      };

      console.log('sbidPrjSubcoKeyo',bidPrjSubcoKey)

      navigateByNameAndParams('bidAaa007Edit', {
        bidProjectKey: bidPrjSubcoKey,
        formStatus: formStatusEnum.MODIFY,
      });
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          fetchProjectAllSubco(newValue.wkut, newValue.prjno);
        }
      },
      {immediate: true}
    );

    onActivated(() => {
      // console.log('onActivated');
      if (bidProjectKeyProp.value) {
        // console.log('bidProjectKeyProp.value');
        fetchProjectAllSubco(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
      } else if (tempProjectKey.wkut.length > 0) {
        // console.log('tempProjectKey');
        fetchProjectAllSubco(tempProjectKey.wkut, tempProjectKey.prjno);
      }
    });

    //製報表
    const toTable = bidProject => {

      let criteria = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
        subcoNo: ccutKey.subcoNo,
      };

      axios
        .post('/bid-prj-scequip/bidAae003', criteria, { responseType: 'blob' })
        .then(res => {
          let blob = new Blob([res.data], { type: 'application/pdf' });
          let url = window.URL.createObjectURL(blob);
          console.log(context);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    };


    return {
      iTable,
      projectInfo,
      prjSubcoQueryTable,
      bidProjectKeyProp,
      addNewSubco,
      toNewSubco,
      toUpadteSubcoView,
      toTable,
      pdfViewer,
    };
  },
};
</script>

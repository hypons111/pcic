<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">未開工原因資料</h5>
        </div>
        <div>
          <i-button class="mt-2" type="file-earmark-plus" @click="toCreate"></i-button>
        </div>
        <div class="mt-2">
          <i-table
            ref="iTable"
            class="table-sm"
            :itemsUndefinedBehavior="'loading'"
            :items="table.data"
            :fields="table.fields"
            :totalItems="table.totalItems"
            :is-server-side-paging="true"
            @changePagination="handlePaginationChanged($event)"
          >
            <!--#cell為id,row代表這一列的資料-->
            <template #cell(mft)="row">{{ row.item.mft ? queryOptionsFormatter(row.item.mft).text : '' }}</template>
            <template #cell(syr)="row">{{ row.item.syr }}/{{ row.item.month }}</template>
            <template #cell(action)="row">
              <i-button class="btn-sm" type="pencil-square" @click="toModify(row.item)"></i-button>
            </template>
          </i-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import NotificationService from '@/shared/notification/notification-service';
import { Reactive, reactive, Ref, ref, toRef, watch } from '@vue/composition-api';
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { Pagination } from '@/shared/model/pagination.model';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import i18n from '@/shared/i18n';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import { IBidPrjFals, BidPrjFals } from '@/shared/model/bidModel/bid-prj-fals.model';
import { IBidPrjPay2 } from '@/shared/model/bidModel/bid-prj-pay2.model';
import BidPrjFalsService from '@/components/bid/bidService/bid-prj-fals.service';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';

export default {
  name: 'bidAab005Info',
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
    const bidCommonService = new BidCommonService();
    const bidPrjFalsSerice = new BidPrjFalsService();
    const bidSysCodeTable = new BidSysCodeTable();

    const notificationService: NotificationService = useNotification();

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });

    const iTable = ref(null);

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
          result.typeCode = data;
          Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        });
      });
    }

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault: IBidPrjFals = new BidPrjFals();

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      year: {},
      month: {},
      syr: {},
      mft: {},
      mfas: {},
      mfrt: {},
      mfru: {},
      oldDate: {},
      newDate: {},
      createUser: {},
      createDate: {},
      updateDate: {},
      updateUser: {},
    };

    // 建立表單物件 ref
    const form: Reactive<IBidPrjFals> = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields: [
        {
          key: 'syr',
          label: '年/月',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'mft',
          label: '未開工原因',
          sortable: false,
          thStyle: 'width:22%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'mfas',
          label: '原因說明',
          sortable: false,
          thStyle: 'width:22%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'mfrt',
          label: '解決辦法',
          sortable: false,
          thStyle: 'width:22%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: '動作',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const handleQuery = () => {
      if (iTable.value) iTable.value.state.pagination.currentPage = 1;
      getDatafromAxios(bidProjectKeyProp.value);
    };

    function getDatafromAxios(queryCriteria) {
      bidPrjFalsSerice
        .getPrjFalsByCriteria(queryCriteria)
        .then(res => {
          table.data = res.content.slice(0, res.content.length); //後端分頁
          table.totalItems = res.totalElements; //後端分頁
          hadleSysCodeToName();
        })
        .catch(notificationErrorHandler(notificationService));
      //checkVailidaty 沒過
    }

    // 後端分頁用
    function handlePaginationChanged(pagination: Pagination) {
      Object.assign(form, pagination);
      handleQuery();
    }
    // 下拉選單:未開工原因
    //去後端撈選項
    const bidFalsOpts = reactive([]);

    function hadleSysCodeToName() {
      bidSysCodeTable.getBidSysCodeWithParam('BID_016').then(data => {
        bidFalsOpts.splice(0, bidFalsOpts.length);
        data.forEach(ele => {
          const selectOption = {
            value: '',
            text: '',
          };
          selectOption.value = ele.dataKey;
          selectOption.text = ele.value;
          bidFalsOpts.push(selectOption);
        });
      });
    }

    // 選項轉換
    const queryOptionsFormatter = (value: string) =>
      bidFalsOpts.find(function (item) {
        if (item.value === value) {
          return item.text; //為什還是傳出物件???
        }
      });

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          handleQuery();
        }
      },
      { immediate: true }
    );

    function toCreate() {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAab005Edit', { bidProjectKey, formStatus: 'create' });
    }

    function toModify(row) {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
        syr: row.syr,
        month: row.month,
      };

      navigateByNameAndParams('bidAab005Edit', { bidProjectKey, formStatus: 'modify' });
    }

    return {
      projectInfo,
      iTable,
      $v,
      reset,
      bidProjectKeyProp,
      table,
      form,
      handleQuery,
      toCreate,
      toModify,
      handlePaginationChanged,
      queryOptionsFormatter,
      bidFalsOpts,
      hadleSysCodeToName,
    };
  },
};
</script>

<style scoped></style>

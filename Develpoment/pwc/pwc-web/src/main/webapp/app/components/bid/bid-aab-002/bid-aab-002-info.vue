<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">預定完成里程碑資料</h5>
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
            <template v-slot:cell(milestoneType)="row">
              {{ row.item.milestoneType ? queryOptionsFormatter('milestoneType', row.item.milestoneType).text : '' }}
            </template>
            <template v-slot:cell(milestoneStatus)="row">
              {{ row.item.milestoneStatus ? queryOptionsFormatter('milestoneStatus', row.item.milestoneStatus).text : '' }}
            </template>
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
import { reactive, Ref, ref, toRef, watch } from '@vue/composition-api';
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { Pagination } from '@/shared/model/pagination.model';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import i18n from '@/shared/i18n';
import BidPrjMilestoneService from '@/components/bid/bidService/bid-prj-milestone.service';
import { formatToString } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'bidAab002Info',
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
    const bidPrjMilestoneService = new BidPrjMilestoneService();
    const notificationService: NotificationService = useNotification();

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });

    const iTable = ref(null);

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        // console.log('fetchBidProjectInfo res ', result);
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }
    // 選項轉換
    const queryOptionsFormatter = (option: string, value: string) =>
      formOptions[option].find(function (item) {
        if (item.value === value) {
          return item.text; //為什還是傳出物件???
        }
      });

    const formOptions = reactive({
      milestoneType: [
        { value: '1', text: '1:要徑項目' },
        { value: '2', text: '2:未完成開工要件項目' },
        { value: '3', text: '3:停工中預定復工' },
        { value: '4', text: '4:關鍵控管事件' },
        { value: '5', text: '5:重要事件' },
        { value: '6', text: '6:其他' },
      ],
      milestoneStatus: [
        { value: 'Y', text: '已完成' },
        { value: 'N', text: '未完成' },
      ],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      wkut: '',
      prjno: '',
      applyDate: '',
      milestoneNo: '',
      milestoneName: '',
      milestoneType: '',
      milestoneStatus: '',
      estimateDate: '',
      actualDate: '',
      remark: '',
      createDate: '',
      createUser: '',
      updateDate: '',
      updateUser: '',
    };

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      applyDate: {},
      milestoneNo: {},
      milestoneName: {},
      milestoneType: {},
      milestoneStatus: {},
      estimateDate: {},
      actualDate: {},
      remark: {},
      createDate: {},
      createUser: {},
      updateDate: {},
      updateUser: {},
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields: [
        {
          key: 'milestoneName',
          label: '里程碑名稱',
          sortable: false,
          thStyle: 'width:25%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'milestoneType',
          label: '類別',
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'milestoneStatus',
          label: '完成狀態',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'estimateDate',
          label: '預定完成日期',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          key: 'actualDate',
          label: '實際完成日期',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
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
      checkValidity().then(result => {
        if (result) {
          if (iTable.value) iTable.value.state.pagination.currentPage = 1;
          getDatafromAxios(bidProjectKeyProp.value);
        }
      });
    };

    function getDatafromAxios(queryCriteria) {
      bidPrjMilestoneService
        .getBidPrjMilestoneByCriteria(queryCriteria)
        .then(res => {
          table.data = res.content.slice(0, res.content.length); //後端分頁
          table.totalItems = res.totalElements; //後端分頁
        })
        .catch(notificationErrorHandler(notificationService));
      //checkVailidaty 沒過
    }
    // 後端分頁用
    function handlePaginationChanged(pagination: Pagination) {
      Object.assign(form, pagination);
      handleQuery();
    }

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
      navigateByNameAndParams('bidAab002Edit', { bidProjectKey, formStatus: 'create' });
    }

    function toModify(row) {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
        milestoneNo: row.milestoneNo,
      };

      navigateByNameAndParams('bidAab002Edit', { bidProjectKey, formStatus: 'modify' });
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
    };
  },
};
</script>

<style scoped></style>

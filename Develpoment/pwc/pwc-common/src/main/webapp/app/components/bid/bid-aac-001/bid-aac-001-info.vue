<template>
  <div>
    <section>
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <div class="card">
      <div class="mt-2">
        <i-button type="file-earmark-plus" @click="toCreate"></i-button>
        <b-button style="background-color: #1aa4b7">品管人員登錄表</b-button>
      </div>
      <div class="mt-2">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">承造廠商品管人員資料</h5>
        </div>
        <i-table
          class="table-sm"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :fields="table.fields"
          :totalItems="table.totalItems"
          :is-server-side-paging="true"
          @changePagination="handlePaginationChanged($event)"
        >
          <!--#cell為id,row代表這一列的資料-->
          <template #cell(action)>
            <i-button class="btn-sm" type="pencil-square" @click="toModify"></i-button>
            <b-button class="btn-sm" style="background-color: #1aa4b7">一覽表</b-button>
          </template>
        </i-table>
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

export default {
  name: 'bidAac001Info',
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

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      wkut: '',
      prjno: '',
      name: '',
      major: '',
      id: '',
      class: '',
      class2: '',
      startDate: '',
      endDate: '',
      duty: '',
      createDate: '',
    };

    // 表單物件驗證規則
    let rules = {
      wkut: {},
      prjno: {},
      name: {},
      major: {},
      id: {},
      class: {},
      class2: {},
      startDate: {},
      endDate: {},
      duty: {},
      createDate: {},
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields: [
        {
          key: 'name',
          label: '姓名',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'major',
          label: '專長',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'id',
          label: '身分證號',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'class',
          label: '受訓期別',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'class2',
          label: '回訓期別',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'startDate',
          label: '進駐工地日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'endDate',
          label: '辭職日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'duty',
          label: '目前狀況',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'createDate',
          label: '登陸日(修正日)',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: '動作',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const handleQuery = () => {
      table.data = [];
      table.totalItems = 1;
      table.data.splice(0, table.data.length, ...mockdata);
    };

    const mockdata = [
      {
        name: '張*寧',
        major: '土建',
        id: 'L22011****',
        class: 'IE8901',
        class2: 'ER20365,\r\nER10680',
        startDate: '109/11/29',
        endDate: '110/02/25',
        duty: '中途在職',
        createDate: '109/12/07\r\n(110/03/04)',
      },
      {
        name: '呂*賢',
        major: '機電',
        id: 'F12496****',
        class: 'EE10626',
        class2: '(無)',
        startDate: '109/11/29',
        endDate: '',
        duty: '專職 在職',
        createDate: '109/12/07',
      },
      {
        name: '廖*芬',
        major: '土建',
        id: 'F22241****',
        class: 'IE10303',
        class2: 'IR10804',
        startDate: '109/11/29',
        endDate: '110/01/26',
        duty: '中途在職',
        createDate: '109/12/07\r\n(110/04/01)',
      },
    ];

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
      navigateByNameAndParams('bidAac001Edit', { bidProjectKey, formStatus: 'create' });
    }

    function toModify() {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAac001Edit', { bidProjectKey, formStatus: 'modify' });
    }

    return {
      projectInfo,
      $v,
      reset,
      bidProjectKeyProp,
      table,
      form,
      handleQuery,
      toCreate,
      toModify,
    };
  },
};
</script>

<style scoped></style>

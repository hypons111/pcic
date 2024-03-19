<template>
  <div>
    <div class="card">
      <section>
        <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      </section>
      <div class="card-body">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">安衛人員詳細資料</h5>
        </div>
        <div class="mt-2">
          <i-button type="file-earmark-plus" @click="toCreate"></i-button>
          <b-button style="background-color: #1aa4b7">工地相關人員記錄表</b-button>
        </div>
        <div class="mt-2">
          <i-table
            class="table-sm"
            :itemsUndefinedBehavior="'loading'"
            :items="table.data"
            :fields="table.fields"
            :totalItems="table.totalItems"
            :is-server-side-paging="true"
            :hide-no="true"
            @changePagination="handlePaginationChanged($event)"
          >
            <!--#cell為id,row代表這一列的資料-->
            <template #cell(action)>
              <i-button class="btn-sm" type="pencil-square" @click="toModify"></i-button>
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

export default {
  name: 'bidAac005Info',
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

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields: [
        {
          key: 'pname',
          label: '姓名',
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
          key: 'job',
          label: '職稱',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'certNo',
          label: '證照號碼',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'startDate',
          label: '負責本工程起始日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'endDate',
          label: '負責本工程結束日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'duty',
          label: '日前狀況',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'createDate',
          label: '補登日(修正日)',
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
          tdClass: 'text-center align-middle',
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
        pname: '蔡*清',
        id: 'L12085****',
        job: '工地主任',
        certNo: '40H3009950',
        startDate: '109/11/29',
        endDate: '110/02/18',
        duty: '專職 中途離',
        createDate: '109/12/07\r\n(110/02/18)',
      },
      {
        pname: '李*驊',
        id: 'M12246****',
        job: '工地主任',
        certNo: '40H30027047',
        startDate: '111/02/18',
        endDate: '',
        duty: '專職 在職',
        createDate: '109/02/18',
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
      navigateByNameAndParams('bidAac005Edit', { bidProjectKey, formStatus: 'create' });
    }

    function toModify() {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAac005Edit', { bidProjectKey, formStatus: 'modify' });
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

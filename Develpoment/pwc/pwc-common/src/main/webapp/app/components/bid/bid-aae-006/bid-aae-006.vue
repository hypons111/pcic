<template>
  <div>
    <section>
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">標案工安事件狀況</h5>
      </div>
      <!--        查詢工安事件狀況列表(多)-->
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
          <template #cell(action)="row">
            <!--              檢視報表-->
            <!--              {{ row.item }} 得到的資料-->
            <i-button class="btn-sm" type="binoculars" @click="toDetailView(row.item)"></i-button>
          </template>
        </i-table>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch } from '@vue/composition-api';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import NotificationService from '@/shared/notification/notification-service';
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import { Pagination } from '@/shared/model/pagination.model';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';

export default {
  name: 'bidAae006',
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

    /**
     * 從使用者撈 wkut的作法。
     * 如果是登錄就有的資訊應從使用者那邊撈出來
     *
     * */
    const store = context.root.$store;
    let unitId = store.getters.account.unit.id;

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      wkut: '', // 執行機關代碼
      prjno: '', // 標案編號
      sdate: '', // 發生日期
      disasterScript: '', //罹災時災情
      peDate: '', // 提報勞動部日期
      meDate: '', //勞動部核定日期
      action: '', // 動作
      prmNo: '', // 提報文號(PK)
    };

    // 表單物件驗證規則
    let rules = {
      wkut: {}, // 執行機關代碼
      prjno: {}, // 標案編號
      sdate: {}, // 發生日期
      disasterScript: {}, //罹災時災情
      peDate: {}, // 提報勞動部日期
      meDate: {}, //勞動部核定日期
      action: {}, //動作
      prmNo: '', // 提報文號(PK)
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields: [
        {
          key: 'sdate',
          label: '發生日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'disasterScript',
          label: '罹災時災情',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'peDate',
          label: '提報勞動部日期',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'meDate',
          label: '勞動部核定日期',
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
    // 查詢結果及狀態
    const dataPromise = ref(null); //查詢結果
    //
    const notificationService: NotificationService = useNotification();

    const handleQuery = () => {
      table.data = undefined;

      form.wkut = bidProjectKeyProp.value.wkut;
      form.prjno = bidProjectKeyProp.value.prjno;
      dataPromise.value = axios
        .post('/bid-prj-safes/criteria', form) //後端分頁
        .then(({ data }) => {
          console.log('@@@@data : ', data);
          table.data = data.content.slice(0, data.content.length); //後端分頁
          table.totalItems = data.totalElements; //後端分頁
        })
        .catch(notificationErrorHandler(notificationService))
        .finally(() => (dataPromise.value = null));
    };

    // 後端分頁用
    function handlePaginationChanged(pagination: Pagination) {
      Object.assign(form, pagination);
      handleQuery();
    }

    // 檢視單一工安事件
    function toDetailView(value) {
      let bidPrjSafeKey = {
        wkut: value.wkut,
        prjno: value.prjno,
        sdate: value.sdate,
      };
      navigateByNameAndParams('bidAad006Report', { bidPrjSafeKey });
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

    return {
      projectInfo,
      $v,
      reset,
      bidProjectKeyProp,
      table,
      form,
      handleQuery,
      handlePaginationChanged,
      toDetailView,
    };
  },
};
</script>

<style scoped></style>

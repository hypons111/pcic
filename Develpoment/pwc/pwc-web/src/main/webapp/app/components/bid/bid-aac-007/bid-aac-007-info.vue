<template>
  <div>
    <section>
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">監造現場人員詳細資料</h5>
      
      <div class="mt-2">
        <i-button type="file-earmark-plus" @click="toCreate"></i-button>
        <b-button style="background-color: #1aa4b7">監造人員登錄表</b-button>
      </div>

      

     
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
            <i-button class="btn-sm" type="pencil-square" @click="navigateByNameAndParams('bidAac007Edit',{})" ></i-button>
            <b-button class="btn-sm" style="background-color: #1aa4b7">一覽表</b-button>
          </template>
        </i-table>
    </div>
  </div>
</template>

<script lang="ts">
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { reactive, Ref, toRef, watch } from '@vue/composition-api';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import { useValidation } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'bidAac007Edit',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
    formStatus: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const formStatusProp: Ref<any> = toRef(props, 'formStatus');
    const bidProjectService = new BidProjectService();

    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({ wkut: '', prjno: '' });

    /**
     * 撈出project的部分資料
     */
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
       {
        name: '曾*威',
        major: '空調',
        id: 'N12385****',
        class: 'EE10919',
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
        startDate: '110/03/31',
        endDate: '',
        duty: '專職 復職',
        createDate: '110/04/01',
      },
    ];
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

    const formOptions = reactive({
      duty: [
        { value: '1', text: '專職人員' },
        { value: '2', text: '非專職人員' },
      ],
      company: [
        { value: '1', text: '請選擇' },
        { value: '2', text: '1:港洲營造公司' },
        { value: '3', text: '2:有力機電工程股份有限公司' },
        { value: '4', text: '3:致邦空調工程有限公司' },
      ],
      status: [
        { value: '1', text: '1:在職' },
        { value: '2', text: '2:中途離職' },
        { value: '3', text: '3:竣工解雇' },
        { value: '4', text: '4:查核丙等被撤換' },
        { value: '5', text: '5:查核缺失改善成效不佳被更換' },
        { value: '6', text: '6:未實際於工地執行工作被撤換' },
        { value: '7', text: '7:未能確實執行工作被撤換' },
        { value: '9', text: '9:專案註記' },
      ],
    });

    // const handleQuery = () => {};
    const submitForm = () => {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAac001Info', { bidProjectKey });
    };
    const toCheck = () => {};
    const toCancel = () => {
      let bidProjectKey = {
        wkut: bidProjectKeyProp.value.wkut,
        prjno: bidProjectKeyProp.value.prjno,
      };
      navigateByNameAndParams('bidAac001Info', { bidProjectKey });
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    watch(
      bidProjectKeyProp,
      newValue => {
        // if (newValue !== null && newValue !== undefined) {
          // fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          // if (formStatusProp.value === 'modify') {
            handleQuery();
          // }
        // }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      $v,
      reset,
      bidProjectKeyProp,
      formStatusProp,
      formOptions,
      form,
      handleQuery,
      toCheck,
      submitForm,
      toCancel,
      table,
      navigateByNameAndParams,
    };
  },
};
</script>

<style scoped></style>


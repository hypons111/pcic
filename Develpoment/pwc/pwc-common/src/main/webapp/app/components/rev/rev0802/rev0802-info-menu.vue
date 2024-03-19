<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-12">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <!-- 帳號類別       -->       
              <i-form-group-check class="col-12" label-cols="1" :label="$t('label.type')">
                <b-col cols="1.5" class="text-left">
                  <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="A">全部</b-form-radio>
                </b-col>
                <b-col cols="2" class="text-left">
                  <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="D">未失效</b-form-radio>
                </b-col>
                <b-col cols="2" class="text-left">
                  <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="E">已失效</b-form-radio>
                </b-col>
                <b-col cols="2.5" class="text-left">
                  {{ $t('label.queryContent') + '：'}}
                </b-col>
                <b-col cols="4" class="test-left">
                  <b-form-input v-model="$v.queryContent.$model"></b-form-input>
                </b-col>
              </i-form-group-check>
            </b-form-row>
            <b-col offset="8">
              <b-button-toolbar>
                <i-button class="mt-1 ml-1" type="search" @click="handleQuery"></i-button>
                <i-button class="mt-1 ml-1" type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </b-col>
          </b-collapse>
        </div>
      </div>
    </section>

    
    <section class="container mt-2" v-if="queryStatus">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢結果清單
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step2 = !stepVisible.step2">
                <font-awesome-icon v-if="stepVisible.step2" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step2" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        
        <div class="card-body col-12">
          <div class="container">
          <b-collapse v-model="stepVisible.step2">
          <div class="button-float-right">
          
          <i-button type="file-earmark-plus" v-b-modal.addUnitPriceCat></i-button>
              <b-modal 
                id="addUnitPriceCat" 
                title="新增單位造價類別" size="lg" 
                header-bg-variant="secondary"
                header-text-variant="light">
                  <i-form-group-check class="col-12" label-cols="2" :label="'上層分類'">
                      <b-form-group label="選單模式">
                          <b-form-radio-group
                            id="radio-group-1"
                          >
                            <b-form-radio value="first">搜尋</b-form-radio>
                            <b-form-radio value="second">瀏覽</b-form-radio>
                          </b-form-radio-group>
                        </b-form-group>
                      <b-col class="text-left" cols="12">
                        <b-form-input type="search"></b-form-input>
                      </b-col>
                  </i-form-group-check>
                  <i-form-group-check class="col-12" label-cols="2" :label="'名稱'">
                      <b-col class="text-left">
                        <b-form-input></b-form-input>
                      </b-col>
                  </i-form-group-check>
                  <i-form-group-check class="col-12" label-cols="2" :label="'備註提示'">
                      <b-col class="text-left">
                        <b-form-input></b-form-input>
                      </b-col>
                  </i-form-group-check>
                  <i-form-group-check class="col-12" label-cols="2" :label="'類型'">
                          <b-form-group>
                          <b-form-radio-group
                            id="radio-group-2"
                          >
                            <b-form-radio value="first">分類</b-form-radio>
                            <b-form-radio value="second">工程單位</b-form-radio>
                          </b-form-radio-group>
                        </b-form-group>
                    </i-form-group-check>
                    <i-form-group-check class="col-12" label-cols="2" :label="'是否已失效'">
                          <b-form-group>
                          <b-form-radio-group
                            id="radio-group-3"
                          >
                            <b-form-radio value="first">是</b-form-radio>
                            <b-form-radio value="second">否</b-form-radio>
                          </b-form-radio-group>
                        </b-form-group>
                    </i-form-group-check>
                <template #modal-footer="{ cancel }">
                  <b-button variant="info">繼續新增</b-button>
                  <b-button variant="info">儲存</b-button>
                  <b-button variant="info">清除</b-button>
                  <b-button @click="cancel()" variant="info">取消</b-button>
                </template>
              </b-modal>
              <b-modal 
                id="editUnitPriceCat" 
                title="新增單位造價類別" size="lg" 
                header-bg-variant="secondary"
                header-text-variant="light">
                  <i-form-group-check class="col-12" label-cols="2" :label="'上層分類'">
                      <b-form-group label="選單模式">
                          <b-form-radio-group
                            id="radio-group-1"
                          >
                            <b-form-radio value="first">搜尋</b-form-radio>
                            <b-form-radio value="second">瀏覽</b-form-radio>
                          </b-form-radio-group>
                        </b-form-group>
                      <b-col class="text-left" cols="12">
                        <b-form-input type="search"></b-form-input>
                      </b-col>
                  </i-form-group-check>
                  <i-form-group-check class="col-12" label-cols="2" :label="'名稱'">
                      <b-col class="text-left">
                        <b-form-input></b-form-input>
                      </b-col>
                  </i-form-group-check>
                  <i-form-group-check class="col-12" label-cols="2" :label="'備註提示'">
                      <b-col class="text-left">
                        <b-form-input></b-form-input>
                      </b-col>
                  </i-form-group-check>
                  <i-form-group-check class="col-12" label-cols="2" :label="'類型'">
                          <b-form-group>
                          <b-form-radio-group
                            id="radio-group-2"
                          >
                            <b-form-radio value="first">分類</b-form-radio>
                            <b-form-radio value="second">工程單位</b-form-radio>
                          </b-form-radio-group>
                        </b-form-group>
                    </i-form-group-check>
                    <i-form-group-check class="col-12" label-cols="2" :label="'是否已失效'">
                          <b-form-group>
                          <b-form-radio-group
                            id="radio-group-3"
                          >
                            <b-form-radio value="first">是</b-form-radio>
                            <b-form-radio value="second">否</b-form-radio>
                          </b-form-radio-group>
                        </b-form-group>
                    </i-form-group-check>
                <template #modal-footer="{ cancel }">
                  <b-button variant="info">儲存</b-button>
                  <b-button variant="info">清除</b-button>
                  <b-button @click="cancel()" variant="info">取消</b-button>
                  <b-button variant="info">刪除</b-button>
                </template>
              </b-modal>
          <b-button variant="info" @click="reset">下載ods</b-button>
          </div>
            <i-table
              title="查詢結果清單"
              class="table-sm"
              :itemsUndefinedBehavior="'loading'"
              :items="table.data"
              :fields="table.fields"
              :totalItems="table.totalItems"
              :is-server-side-paging="true"
             
            >
              <template #cell(action)="{ item }">
                <i-button type="pencil-square" v-b-modal.editUnitPriceCat></i-button>
                <b-button variant="info" @click="reset">失效</b-button>
                <i-button type="trash" @click="reset"></i-button>
              </template>
            </i-table>
            </b-collapse>
          </div>
        </div> 
      </div>
    </section>
    <!-- govCheck的彈跳modal -->
    <!--<section>
      <b-modal
        id="bidGovCheckData"
        size="xl"
        title="查核督導紀錄(清單)"
        :header-text-variant="'light'"
        :header-bg-variant="'info'"
        hide-footer
      >
        <div>
          <i-table
            border0
            title="查核督導紀錄"
            class="table-sm"
            :itemsUndefinedBehavior="'loading'"
            :items="prjGovCheckTable.data"
            :fields="prjGovCheckTable.fields"
            :totalItems="prjGovCheckTable.totalItems"
            :is-server-side-paging="true"
            centered
            @changePagination="handlePaginationChanged($event)"
          >
            //ref="iTableGovCheck"
          </i-table>
        </div>
        <b-button class="mt-3" block @click="$bvModals.hide('bidGovCheckData')">關閉</b-button>
      </b-modal>
    </section>-->
    <!-- delte govPrecheck的彈跳modal -->
    <!--<section>
      <b-modal
        id="delteBidGovPrecheck"
        size="xl"
        title="刪除預排查核"
        :header-text-variant="'light'"
        :header-bg-variant="'info'"
        hide-footer
      >
        <div v-if="bidGovPreCheckKey.wkut !== ''">
          //<bidAbb001Info :bidGovPreCheckKey="bidGovPreCheckKey" :key="bidGovPreCheckKey.wkut + bidGovPreCheckKey.prjno"></bidAbb001Info>
          
          <b-row class="justify-content-center">
            <i-button type="x-lg" @click="deleteGovPreCheck(bidGovPreCheckKey)"> </i-button>
            <b-button class="mt-3" block @click="$bvModals.hide('delteBidGovPrecheck')">關閉視窗</b-button>
          </b-row>
        </div>
      </b-modal>
    </section>-->
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, onActivated,computed,reactive } from '@vue/composition-api';
import i18n from '@/shared/i18n';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidGovPrecheckService from '@/components/bid/bidService/bid-gov-precheck.service';
import BidGovCheckService from '@/components/bid/bidService/bid-gov-check.service';
// import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { RevMain } from '@/shared/model/revModel/rev-main.model';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
// import bidAbb001Info from '@/components/bid/bid-abb-001/bid-abb-001-info.vue';
import { useBvModal } from '@/shared/modal';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { useValidation, validateState } from '@/shared/form';
import ItreeSelect from '@/shared/i-tree-select/i-tree-select.vue';

export default {
  name: 'rev0802InfoMenu',
  // components: { bidProjectInfo, bidAbb001Info },
  // props: {
  //   bidProjectKey: {
  //     type: Object,
  //     required: false,
  //   },
  // },

  
  setup(props, context) {
    onActivated(() => {
      reset();
    });
    
    // const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');

    // const tempProjectKey = {
    //   wkut: '',
    //   prjno: '',
    // };
    // setTempProjectKey();

    // const notificationService: NotificationService = useNotification();
    // const bidProjectService = new BidProjectService();
    // const bidGovPrecheckService = new BidGovPrecheckService();
    // const bidGovCheckService = new BidGovCheckService();
    // const bidSysCodeTable = new BidSysCodeTable();
    // const $bvModals = useBvModal();
    // const iTable = ref(null);
    // const projectInfo = reactive({ wkut: '', prjno: '' });
    // const bidGovPreCheckKey = reactive({ wkut: '', prjno: '', checkDate: '' });

    // 查詢結果及狀態
    const queryStatus: Ref<Boolean> = ref(true);
    const dataPromise = ref(null);

    let stepVisible = reactive({
      step1: true;
      step2: true;
    });

    const formDefault = {
      radioType: 'A',
      orgName: null,
      status: '',
      moduleType: '',
      queryContent: '',
    };
    
    const form = reactive(Object.assign({}, formDefault));

    const rules ={
      radioType: {},
      orgName: {},
      moduleType:{},
      status: {} ,
      queryContent: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // function judgeInputDisable(currentRadioValue: string, radioValue: string): boolean {
    //   return (radioValue === currentRadioValue);
    // };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    

    enum formStatusEnum {
      CREATE = 'create',
      MODIFY = 'modify',
    }
    const table = reactive({
      fields: [
        {
          key: 'nodeType',
          label: '節點類型',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'name',
          label: '名稱',
          sortable: false,
          thStyle: 'width:40%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'valid',
          label: '是否失效',
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thStyle: 'width:27%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: [
        {
          nodeType: '分類',
          name: '下水道',
          valid: '',
          action: ''
        },
        {
          nodeType: '分類',
          name: '下水道-再生水工程',
          valid: '',
          action: ''
        },
        {
          nodeType: '分類',
          name: '下水道-再生水工程-加壓站',
          valid: '',
          action: ''
        },
        {
          nodeType: '工程單位',
          name: '下水道-再生水工程-加壓站-CMD',
          valid: '',
          action: ''
        },
        {
          nodeType: '分類',
          name: '下水道-再生水工程-再生水廠',
          valid: '',
          action: ''
        },
        {
          nodeType: '工程單位',
          name: '下水道-再生水工程-再生水廠-CMD',
          valid: '',
          action: ''
        },
        {
          nodeType: '分類',
          name: '下水道-再生水工程-輸配水管線',
          valid: '',
          action: ''
        },
        {
          nodeType: '分類',
          name: '下水道-再生水工程-輸配水管線-延性鑄鐵管(DIP)',
          valid: '',
          action: ''
        },
        {
          nodeType: '分類',
          name: '下水道-再生水工程-輸配水管線-延性鑄鐵管(DIP)-工作井及人孔',
          valid: '',
          action: ''
        },
        {
          nodeType: '工程單位',
          name: '下水道-再生水工程-輸配水管線-延性鑄鐵管(DIP)-工作井及人孔-處理作業',
          valid: '',
          action: ''
        }
      ],
      totalItems: 1,
    });

    // const table = reactive({
    //   fields: [
    //     {
    //       key: 'checkDate',
    //       label: '辨理日期',
    //       sortable: false,
    //       thStyle: 'width:10%',
    //       thClass: 'text-center',
    //       tdClass: 'text-left align-middle',
    //     },
    //     {
    //       key: 'checkUnit',
    //       label: '小組名稱',
    //       sortable: false,
    //       thStyle: 'width:15%',
    //       thClass: 'text-center',
    //       tdClass: 'text-left align-middle',
    //     },
    //     {
    //       key: 'roveChk5',
    //       label: '型式',
    //       sortable: false,
    //       thStyle: 'width:15%',
    //       thClass: 'text-center',
    //       tdClass: 'text-left align-middle',
    //     },
    //     {
    //       key: 'checker',
    //       label: '參與人員',
    //       sortable: false,
    //       thStyle: 'width:15%',
    //       thClass: 'text-center',
    //       tdClass: 'text-left align-middle',
    //     },
    //     {
    //       key: 'oChker',
    //       label: '外聘委員',
    //       sortable: false,
    //       thStyle: 'width:15%',
    //       thClass: 'text-center',
    //       tdClass: 'text-left align-middle',
    //     },
    //     {
    //       key: 'score',
    //       label: '成績',
    //       sortable: false,
    //       thStyle: 'width:15%',
    //       thClass: 'text-center',
    //       tdClass: 'text-left align-middle',
    //     },
    //     {
    //       key: 'detel3',
    //       label: '其他意見',
    //       sortable: false,
    //       thStyle: 'width:15%',
    //       thClass: 'text-center',
    //       tdClass: 'text-left align-middle',
    //     },
    //   ],
    //   data: undefined,
    //   totalItems: 0,
    // });

    /**
     * 撈出project的部分資料
     */
    // function fetchBidProjectInfo(wkut, prjno) {
    //   bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
    //     Object.assign(projectInfo, result);
    //   });
    // }

    // function setTempProjectKey() {
    //   if (bidProjectKeyProp.value) {
    //     tempProjectKey.wkut = bidProjectKeyProp.value.wkut;
    //     tempProjectKey.prjno = bidProjectKeyProp.value.prjno;
    //   }
    // }

    // function getGovPrecheckByCriteria(criteria) {
    //   bidGovPrecheckService.getGovPrecheckPageByCriteria(criteria).then(govPrecheckData => {
    //     if (govPrecheckData.content && govPrecheckData.content.length > 0) {
    //       govPrecheckData.content.forEach(element => {
    //         if (element.method === '1') {
    //           element.method = '事先函之';
    //         } else if (element.method === '2') {
    //           element.method = '不預告';
    //         } else {
    //           element.method = '(未填)';
    //         }

    //         element.checkUnit = '(請保密)';
    //       });
    //     }

    //     prjGovPrecheckTable.data = govPrecheckData.content;
    //     prjGovPrecheckTable.totalItems = govPrecheckData.totalElements;
    //   });
    // }

    // function fetchGovPrecheckPage(wkut, prjno) {
    //   getGovPrecheckByCriteria({
    //     wkut: wkut,
    //     prjno: prjno,
    //   });
    // }

    // function toAddNew() {
    //   let bidGovPreCheckKeyProp = bidProjectKeyProp.value ? bidProjectKeyProp.value : tempProjectKey;
    //   bidGovPrecheckService.findProjectLatestBidGovPrecheck(bidGovPreCheckKeyProp).then(latestDTO => {
    //     const diffDays = getNumberOfDays(new Date(latestDTO.checkDate), new Date());
    //     if (!latestDTO || diffDays >= 45) {
    //       navigateByNameAndParams('bidAbb001Edit', {
    //         bidGovPreCheckKey: bidGovPreCheckKeyProp,
    //         formStatus: formStatusEnum.CREATE,
    //       });
    //     } else {
    //       notificationService.danger('本案已有預排查核行程，不開放新增');
    //     }
    //   });
    // }
    
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          table.data = [];
          table.data.splice(0, table.data.length, ...mockdata);
        }
      });
    }
    
    const toEditView = ((revMain: RevMain) => {
      navigateByNameAndParams('rev0802Edit', { formStatusForward: 'info', revMain: revMain, isNotKeepAlive: false });
    };

    const toAddView = ((revMain: RevMain) => {
      navigateByNameAndParams('rev0502EditInfo', { formStatusForward: 'info', revMain: revMain, isNotKeepAlive: false });
    };

    // function getNumberOfDays(start: Date, end: Date): number {
    //   // One day in milliseconds
    //   const oneDay = 1000 * 60 * 60 * 24;
    //   // Calculating the time difference between two dates
    //   const diffInTime = end.getTime() - start.getTime();
    //   // Calculating the no. of days between two dates
    //   const diffInDays = Math.round(diffInTime / oneDay);
    //   return diffInDays;
    // }

    // function toEdit(wkut, prjno, checkDate) {
    //   let bidGovPreCheckKeyProp = {
    //     wkut: wkut,
    //     prjno: prjno,
    //     checkDate: checkDate,
    //   };
    //   navigateByNameAndParams('bidAbb001Edit', {
    //     bidGovPreCheckKey: bidGovPreCheckKeyProp,
    //     formStatus: formStatusEnum.MODIFY,
    //   });
    // }

    // function toContactList() {
    //   navigateByNameAndParams('rev0501Query', {});
    // }

    //給 key 值，讓info 的component運作
    // function checkDelete(wkut, prjno, checkDate) {
    //   bidGovPreCheckKey.wkut = wkut;
    //   bidGovPreCheckKey.prjno = prjno;
    //   bidGovPreCheckKey.checkDate = checkDate;
    //   $bvModals.show('delteBidGovPrecheck');
    // }

    //取的bidGovCheck資料
    // function getBidGovCheckData() {
    //   const criteria = {
    //     wkut: tempProjectKey.wkut,
    //     prjno: tempProjectKey.prjno,
    //   };
    //   bidGovCheckService.getGovCheckPageByCriteria(criteria).then(govCheckData => {
    //     bidSysCodeTable.getBidSysCodeWithParam('BID_047').then(sysCodeData => {
    //       govCheckData.content.forEach(govCheckElement => {
    //         let obj = sysCodeData.find(element => {
    //           return element.dataKey == govCheckElement.roveChk5;
    //         });
    //         govCheckElement.roveChk5 = obj.value;
    //         prjGovCheckTable.data = govCheckData.content;
    //         prjGovCheckTable.totalItems = govCheckData.totalElements;
    //         $bvModals.show('bidGovCheckData');
    //       });
    //     });
    //   });
    // }

    // function handlePaginationChanged(pagination: Pagination) {
    //   let queryCriteria = {
    //     wkut: tempProjectKey.wkut,
    //     prjno: tempProjectKey.prjno,
    //   };
    //   Object.assign(queryCriteria, pagination);
    //   getGovPrecheckByCriteria(queryCriteria);
    // }

    // function deleteGovPreCheck(govPrecheckPK) {
    //   bidGovPrecheckService.deleteBidGovPrecheck(govPrecheckPK).then(() => {
    //     $bvModals.hide('delteBidGovPrecheck');
    //     fetchGovPrecheckPage(tempProjectKey.wkut, tempProjectKey.prjno);
    //   });
    // }

    // watch(
    //   bidProjectKeyProp,
    //   newValue => {
    //     setTempProjectKey();
    //     if (newValue !== null && newValue !== undefined) {
    //       fetchBidProjectInfo(newValue.wkut, newValue.prjno);
    //       fetchGovPrecheckPage(newValue.wkut, newValue.prjno);
    //     }
    //   },
    //   { immediate: true }
    // );

    // onActivated(() => {
    //   if (bidProjectKeyProp.value) {
    //     setTempProjectKey();
    //     fetchGovPrecheckPage(bidProjectKeyProp.value.wkut, bidProjectKeyProp.value.prjno);
    //   } else if (tempProjectKey.wkut.length > 0) {
    //     fetchGovPrecheckPage(tempProjectKey.wkut, tempProjectKey.prjno);
    //   }
    // });

    return {
      // iTable,
      // projectInfo,
      table,
      // bidProjectKeyProp,
      // $bvModals,
      // prjGovCheckTable,
      // bidGovPreCheckKey,
      // getBidGovCheckData,
      // checkDelete,
      // handlePaginationChanged,
      // toAddNew,
      // toEdit,
      // deleteGovPreCheck,
      // toContactList,

      formDefault,
      form,
      rules,
      $v,
      queryStatus,
      // judgeInputDisable,
      checkValidity,
      stepVisible,
      reset,
      toEditView,
      toAddView,
    };
  },
};
</script>

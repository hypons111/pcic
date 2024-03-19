<template>
  <div>
    <section class="container">
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
        <div class="card-body">
          <b-collapse v-model="stepVisible.step1">
            <table border="0" align="center" width="80%">
              <tr>
                <td rowspan="100" align="center">
                  <map name="FPMap1">
                    <area v-for="item in mapInfo.place"
                      :shape="item.shape"
                      :coords="item.coords"
                      href="javascript:void(0);"
                      @click="handleQueryMap(item.placeCode)"/>
                  </map>
                  <img hidefocus="true" border="0" :src="mapInfo.mapGif" :width="mapInfo.mapWidth" :height="mapInfo.hei" usemap="#FPMap1">
                </td>
              </tr>
              <tr height="50px"/>
              <tr>
                <td colspan="6">
                  執行中標案<br>可點選地圖更深入分析查詢
                </td>
              </tr>
              <tr height="20px"/>
              <div v-if="mapStatusRef == mapStatusEnum.FIRST">
                <tr>
                  <td>
                    <b-button size="sm" variant="outline-secondary" @click="queryBidProject('9400')">西部地區</b-button>
                  </td>
                  <td>
                    <b-button size="sm" variant="outline-secondary" @click="queryBidProject('9500')">北部地區</b-button>
                  </td>
                  <td>
                    <b-button size="sm" variant="outline-secondary" @click="queryBidProject('9600')">中部地區</b-button>
                  </td>
                </tr>
                <tr>
                  <td>
                    <b-button size="sm" variant="outline-secondary" @click="queryBidProject('9700')">南部地區</b-button>
                  </td>
                  <td>
                    <b-button size="sm" variant="outline-secondary" @click="queryBidProject('9800')">東部地區</b-button>
                  </td>
                </tr>
                <tr height="50px"/>
                <tr>
                  <td>
                    <b-button size="sm" variant="outline-secondary" @click="queryBidProject('9900')">台灣地區</b-button>
                  </td>
                  <td>
                    <b-button size="sm" variant="outline-secondary" @click="queryBidProject('9999')">海外地區</b-button>
                  </td>
                </tr>
              </div>
              <div v-else>
                <tr>
                  <td>
                    跨{{ mapInfo.placeName }}行政區之標案
                    <b-button size="sm" variant="outline-secondary" @click="queryAcross()">查詢</b-button>
                  </td>
                </tr>
              </div>
              <tr height="50px"/>
              <tr>
                <td>
                  <b-button size="sm" variant="outline-secondary" @click="showProjectCountModel()">現況分析</b-button>
                </td>
              </tr>
              <tr height="150px"/>
            </table>
          </b-collapse>
        </div>
      </div>
    </section>
    <section class="mt-2">
      <div v-if="stepVisible.step2" class="container">
        <i-table
          class="table-sm"
          :itemsUndefinedBehavior="'loading'"
          :items="projectTable.data"
          :fields="projectTable.fields"
          :totalItems="projectTable.totalItems"
          :is-server-side-paging="true"
          @changePagination="handlePaginationChanged($event)"
        >
          <template #cell(action)="row">
            <i-button type="binoculars" @click="toEditView(row.item)"></i-button>
          </template>
        </i-table>
      </div>
    </section>
    <b-modal
      id="project-count-modal"
      size="xl"
      title="現況分析"
      header-bg-variant="secondary"
      header-text-variant="light"
    >
      <i-table
        class="table-sm"
        :itemsUndefinedBehavior="'loading'"
        :items="countTable.data"
        :fields="countTable.fields"
        :totalItems="countTable.totalItems"
        :is-server-side-paging="true"
        @changePagination="handlePaginationChanged2($event)"
      >
        <template #cell(action)="row">
          <i-button type="binoculars" @click="handleQueryMap(row.item.placeCode)" v-if="row.item.placeCode!='total'"></i-button>
        </template>
      </i-table>
      <template #modal-footer="{ cancel }">
        <div class="col-12 text-center">
          <b-button size="md" @click="cancel()">關閉</b-button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts">
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import BidPlaceService from '@/components/bid/bidService/bid-place.service';
import VwBidProjectService from '@/components/bid/bidService/vw-bid-project.service';
import { navigateByNameAndParams } from '@/router/router';
import { commaFormatter } from '@/shared/formatter/common';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { VwBidProjectQueryCriteria } from '@/shared/model/bidModel/vw-bid-project-query.criteria';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { onBeforeMount, reactive, ref } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';

export default {
  name: 'bidAba002',
  props: {
    isReload: {
      required: false,
    },
  },
  setup(props, context) {
    onBeforeMount(() => {
      //掛載前執行
      handleQueryMap('00');
    });

    const vwBidProjectService = new VwBidProjectService();
    const bidCommonService = new BidCommonService();
    const bidPlaceService = new BidPlaceService();

    enum mapStatusEnum {
      FIRST = 'first',
      SECOND = 'second',
      LAST = 'last',
    }

    // 表單操作狀態
    const mapStatusRef: Ref<mapStatusEnum> = ref(mapStatusEnum.FIRST);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const mapForm = {
      placeCode: '00',
    };

    const projectForm = {
      placeCode: '00',
    };

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      step2: false,
    });

    const mapInfo = reactive({
      allPlace: undefined,
      place: undefined,
      mapGif: require('./images/taiwan.gif'),
      mapWidth: 435,
      mapHeight: 616,
      placeName: '台灣地區',
    });

    const projectTable = reactive({
      fields: [
        {
          key: 'projectName',
          label: '標案名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'wkutName',
          label: '執行單位',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'aopDate',
          label: '實際公告日期',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => {
            var date = new Date(value);
            const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return year + '/' + month + '/' + day
          }
        },
        {
          key: 'actDate',
          label: '實際決標日期',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => {
            var date = new Date(value);
            const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return year + '/' + month + '/' + day
          }
        },
        {
          key: 'bdgt1',
          label: '發包預算(千元)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value)
        },
        {
          key: 'ctSum',
          label: '決標金額(千元)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value)
        },
        {
          key: 'action',
          label: '動作',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const countTable = reactive({
      fields: [
        {
          key: 'placeName',
          label: '工程地點',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'projectCount',
          label: '標案數',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'ctSum',
          label: '決標總金額(千元)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: any) => commaFormatter(value)
        },
        {
          key: 'action',
          label: '動作',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const handleQueryMap = (value: string) => {
      $bvModal.hide('project-count-modal');
      if (value === '00') {
        mapStatusRef.value = mapStatusEnum.FIRST; // 初始化第一層
        mapForm.placeCode = value;
        queryBidPlace(mapForm.placeCode);
      } else {
        if (value.substring(2, 4) === '00' && value.substring(0, 1) !== '9') {
          mapStatusRef.value = mapStatusEnum.SECOND; // 第一層進第二層
          mapForm.placeCode = value.substring(0, 2);
          queryBidPlace(mapForm.placeCode);
          projectForm.placeCode = value.substring(0, 2);
          queryBidProject(projectForm.placeCode);
        } else {
          mapStatusRef.value = mapStatusEnum.LAST; // 第二層進明細
          projectForm.placeCode = value;
          queryBidProject(projectForm.placeCode);
        }
      }
    };

    const queryBidPlace = (placeCode) => {
      bidPlaceService
        .findMapInfoByPlaceCode(placeCode)
        .then(data => {
          if (mapStatusRef.value === mapStatusEnum.FIRST) {
            mapInfo.allPlace = [];
            mapInfo.allPlace.splice(0, mapInfo.allPlace.length, ...data);
            mapInfo.place = [];
            mapInfo.allPlace.forEach(element => {
              mapInfo.place.push(element);
            });
          } else if (mapStatusRef.value === mapStatusEnum.SECOND) {
            mapInfo.allPlace = [];
            mapInfo.allPlace.splice(0, mapInfo.allPlace.length, ...data);
            mapInfo.place = [];
            mapInfo.allPlace.forEach(element => {
              if (element.placeCode.substring(2, 4) === '00') {
                mapInfo.mapGif = require('./images/' + element.mapsrc);
                mapInfo.mapWidth = element.wid;
                mapInfo.mapHeight = element.hei;
                mapInfo.placeName = element.placeName;
              } else {
                mapInfo.place.push(element);
              }
            });
          }
        })
        .catch(notificationErrorHandler(notificationService));
      //checkVailidaty 沒過
    }

    //送 query去後端撈資料，渲染到table
    function queryBidProject(placeCode) {
      stepVisible.step2 = true;
      let queryCriteria = queryProjectCriteria(placeCode);
      getDatafromAxios(queryCriteria);
    }

    function getDatafromAxios(queryCriteria) {
      vwBidProjectService
        .findByCriteria(queryCriteria)
        .then(date => {
          //為了formatter後端帶來的typeCode 用
          //因為寫的formatter 是 async function,所以沒辦法用 b-table內建的 formatter function
          //只好在撈data的時候做加工，把typeCode轉成對應的字串 ex 10=> 建築--建築建造工程
          date.content.forEach(element => {
            if (element.typeCode) {
              bidCommonService.bidProjectTypeCodeConverter(element.typeCode).then(data => {
                element.typeCode = data;
              });
            }
          });
          projectTable.data = date.content.slice(0, date.content.length); //後端分頁
          projectTable.totalItems = date.totalElements; //後端分頁
        })
        .catch(notificationErrorHandler(notificationService));
      //checkVailidaty 沒過
    }

    function toEditView(value) {
      let bidProjectKey = {
        wkut: value.wkut,
        prjno: value.prjno,
      };
      navigateByNameAndParams('bidAad001EditInfo', { bidProjectKey });
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      let queryCriteria = projectCriteria(projectForm.placeCode);
      Object.assign(queryCriteria, pagination);
      getDatafromAxios(queryCriteria);
    }

    //讓前端畫面的criteria轉成後端認得的 DTO
    function queryProjectCriteria(placeCode) {
      let criteria = new VwBidProjectQueryCriteria();
      criteria.queryType = 'placeCode';
      criteria.placeCode = placeCode;
      return criteria;
    }

    function toEditView(value) {
      let bidProjectKey = {
        wkut: value.wkut,
        prjno: value.prjno,
      };
      navigateByNameAndParams('bidAba001Info', { bidProjectKey });
    }

    const $bvModal: BvModal = useBvModal();

    const showProjectCountModel = () => {
      let queryCriteria = queryCountCriteria(mapForm.placeCode);
      queryProjectCount(queryCriteria);
      $bvModal.show('project-count-modal');
    }

    const handlePaginationChanged2 = (pagination: Pagination) => {
      let queryCriteria = queryCountCriteria(mapForm.placeCode);
      Object.assign(queryCriteria, pagination);
      queryProjectCount(queryCriteria);
    };

    const queryAcross = () => {
      projectForm.placeCode = mapForm.placeCode;
      queryBidProject(projectForm.placeCode);
    }

    //讓前端畫面的criteria轉成後端認得的 DTO
    function queryCountCriteria(placeCode) {
      let criteria = {
        placeCode: placeCode,
      };
      return criteria;
    }

    function queryProjectCount(queryCriteria) {
      bidPlaceService
        .findProjectCountByCriteria(queryCriteria)
        .then(res => {
          countTable.data = res.content.slice(0, res.content.length); //後端分頁
          countTable.totalItems = res.totalElements; //後端分頁
        })
        .catch(notificationErrorHandler(notificationService));
      //checkVailidaty 沒過
    }

    return {
      stepVisible,
      mapStatusEnum,
      mapStatusRef,
      mapInfo,
      projectTable,
      countTable,
      handleQueryMap,
      queryBidProject,
      handlePaginationChanged,
      showProjectCountModel,
      handlePaginationChanged2,
      queryAcross,
      toEditView,
    };
  },
};
</script>

<style scoped>
</style>

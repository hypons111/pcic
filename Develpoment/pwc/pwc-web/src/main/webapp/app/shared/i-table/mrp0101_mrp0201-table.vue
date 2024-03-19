<template>
  <b-card no-body :class="{ 'border-0': border0 }"
    v-if="$attrs.items !== undefined || state.itemsUndefinedBehaviorComputed">
    <slot name="table-top" />

    <b-card-header :class="{ 'd-none': true, 'd-md-block': !hideCardHeader }">
      <h5 class="my-0">
        <font-awesome-icon icon="list"></font-awesome-icon>
        {{ title }}
        <table-fields-picker v-show="$attrs.items && $attrs.items.length > 0 && showFilterIcon" v-bind="$attrs" @picked="handlePicked" class="float-right" />
      </h5>
    </b-card-header>

    <b-card-body class="m-0 p-0">
      <div id="searchTips">
        <p id="searchCondition"></p>
        <p class="pt-3">
          [基本查詢]：顯示全部結果&emsp;[分區查詢]：依地圖選擇資料&emsp;[樹狀顯示]：依類別區分&emsp;[趨勢圖表]：價格變化的趨勢
        </p>
        <div id="badgeGroup">
          <button id="basicSearchButton" class="mt-1 ml-2 btn btn-info">基本查詢</button>
          <button id="areaSearchButton" class="mt-1 ml-2 btn btn-info">分區查詢</button>
          <button id="treeSearchButton" class="mt-1 ml-2 btn btn-info">樹狀顯示</button>
          <button id="trendSearchButton" class="mt-1 ml-2 btn btn-info">趨勢圖表</button>
        </div>

        <p class="">提醒：</p>
        <p class="">1. 本資料庫不提供依物價指數調整後的價格資訊，使用者可依需求自行計算調整。</p>
        <p class="">2. 樣本數較少時，使用者應注意其平均價格與標準差兩者數據之差異，不宜逕行參考引用。</p>
        <p class="">3. 使用者請依使用目的，並考量工程規模、性質、規範要求、施工地點及工期等因素，配合工程專業判斷，予以調整，以符實際個案需求。</p>
      </div>

      <div id="sdPopupModal" class="hidden">
        <p>標準差說明為變異數開算術平方根,反映組內個體間的離散程度。如近似於常態分布的機率分布,則約有近7的價格分布在平均價格士標準差之內的範圍。</p>
      </div>
      
      <!-- 基本查詢 -->
      <div id="basicResult" class="resultDiv">
        <b-table 
          hover 
          show-empty responsive 
          class="m-0" 
          v-bind="$attrs" 
          v-on="$listeners" 
          :bordered="bordered"
          :fields="state.pickedFields" 
          :per-page="state.pagination.perPage"
          :current-page="isServerSidePaging ? 1 : state.pagination.currentPage" 
          :empty-text="state.emptyText"
          :empty-filtered-text="state.emptyFilterText" 
          :no-sort-reset="isServerSidePaging"
          :no-local-sorting="isServerSidePaging" 
          :filter="state.filter" 
          :filter-included-fields="state.filterOn"
          :filter-ignored-fields="state.ignoredFields" 
          @filtered="state.filtered"
          @sort-changed="handleSortChanged">
          <template v-for="(_, slot) of $scopedSlots" v-slot:[slot]="scope">
            <slot :name="slot" v-bind="scope" />
          </template>
          <template #empty>
            <div class="text-center m-3" v-if="state.itemsUndefinedBehaviorComputed">
              <b-spinner></b-spinner>
              <p>查詢中，請稍候...</p>
            </div>
          </template>
        </b-table>
        <div id="basicFooter" :class="{ 'd-none': true, 'd-sm-block': !hideCardFooter }" class="card-footer" v-if="$attrs.items && $attrs.items.length > 0 && state.showFooter">  
          <b-row align-h="around" align-v="center">
            <b-col lg="2">
              <b-input-group prepend="每頁" append="筆" class="mr-md-3">
                <b-form-select v-model="state.pagination.perPage" :options="state.pagination.perPageOptions" @input="handlePageChanged"></b-form-select>
              </b-input-group>
            </b-col>
            
            <b-col lg="4" class="pt-1 pt-lg-0">
              <div>
                <b-pagination 
                  class="my-0" align="fill" 
                  v-model="state.pagination.currentPage"
                  :total-rows="state.pagination.totalRows"
                  :per-page="state.pagination.perPage"
                  @input="handlePageChanged"/>
              </div>
            </b-col>

            <b-col lg="3" class="pt-1 pt-lg-0 text-center text-lg-left">
              第 {{ state.pagination.start }} 到 {{ state.pagination.end }} , 
              共 {{ state.pagination.totalRows ? state.pagination.totalRows : '沒給totalItems' }} 筆 , 
              頁次 {{ state.pagination.currentPage }}/{{ state.pagination.totalPages }}
            </b-col>

            <b-col lg="3" class="pt-1 pt-lg-0">
              <b-input-group prepend="前往頁數">
                <b-form-input v-model="state.pagination.gotoPage" @keydown.enter.prevent="handleGotoPage" />
                <b-input-group-append>
                  <b-button variant="outline-success" @click="handleGotoPage">GO</b-button>
                </b-input-group-append>
              </b-input-group>
            </b-col>
          </b-row>
        </div>
        <div id="basicFooterButtonGroup">
          <button id="basicCollectionDownloadButton" class="mt-1 ml-1 btn btn-info">下載</button>
          <button id="basicCollectionButton" type="button"  class="mt-1 ml-1 btn btn-info">
            查詢集合
            <span id="basicCollectionNumberIcon" class="badge badge-light">0</span>
          </button>
        </div>
      </div>

      <!-- 分區查詢 -->
      <div id="areaResult" class="resultDiv hidden">
        <table id="areaResultTable">
          <tr>
            <td>
              <div id="mapContainer" >
                <map name="FPMap1">
                  <area
                    id="area_1"
                    data-name="臺北市"
                    class="area_1 pin pin bluePin"
                    shape="polygon"
                    coords="314, 28, 338, 66" 
                    href="javascript:void(0);"
                  />
                  <area
                    id="area_2"
                    data-name="桃園市"
                    class="area_2 pin bluePin"
                    shape="polygon"
                    coords="241, 69, 259, 49, 285, 42, 300, 60, 289, 75, 294, 96, 301, 97, 311, 116, 301, 136, 288, 121, 284, 105, 272, 96, 265, 85"
                    href="javascript:void(0);"
                  />
                  <area
                    id="area_3"
                    data-name="新竹市"
                    class="area_3 pin bluePin"
                    shape="polygon"
                    coords="298, 140, 284, 124, 281, 111, 268, 98, 259, 87, 243, 74, 230, 74, 229, 82, 246, 91, 251, 105, 239, 114, 255, 134, 257, 146, 272, 147, 287, 162, 295, 150"
                    href="javascript:void(0);"
                  />
                  <area
                    id="area_4"
                    data-name="苗栗縣"
                    class="area_4 pin bluePin"
                    shape="polygon"
                    coords="221, 111, 239, 115, 254, 135, 255, 145, 263, 154, 271, 152, 285, 162, 251, 184, 232, 178, 226, 187,210, 180, 197, 176, 184, 157, 196, 132, 207, 124, 216, 120"
                    href="javascript:void(0);"
                  />
                  <area
                    id="area_5"
                    data-name="臺中市"
                    class="area_5 pin bluePin"
                    shape="polygon"
                    coords="183, 163, 198, 184, 225, 194, 235, 193, 238, 187, 256, 193, 292, 169, 312, 179, 295, 199, 270, 201 , 246, 216, 236, 216, 227, 222, 214, 232, 193, 234, 182, 218, 163, 200, 169, 188, 175, 174"
                    href="javascript:void(0);"
                  />
                  <area
                    id="area_6"
                    data-name="彰化縣"
                    class="area_6 pin bluePin"
                    shape="polygon"
                    coords="161, 204, 173, 218, 187, 232, 187, 244, 181, 248, 181, 259, 187, 272, 130, 263, 125, 256, 150, 224"
                    href="javascript:void(0);"
                  />
                  <area
                    id="area_7"
                    data-name="南投縣"
                    class="area_7 pin bluePin"
                    shape="polygon"
                    coords="188, 241, 213, 239, 233, 224, 246, 221, 271, 205, 291, 203, 272, 302, 264, 303, 248, 328, 237, 330, 221, 323, 220, 310, 203, 300, 186, 300, 187, 276, 193, 272, 185, 259, 183, 251"
                    href="javascript:void(0);"
                  />
                  <area
                    id="area_8"
                    data-name="雲林縣"
                    class="area_8 pin bluePin"
                    shape="polygon"
                    coords="117, 272, 133, 268, 187, 277, 188, 302, 205, 305, 203, 312, 182, 309, 160, 298, 143, 305, 132, 316, 119, 316, 109, 321, 105, 298"
                    href="javascript:void(0);"
                  />
                  <area id="area_9" data-name="嘉義市" class="area_9 pin bluePin" shape="circle" coords="161, 330, 8" href="javascript:void(0);" />
                  <area
                    id="area_10"
                    data-name="臺南市"
                    class="area_10 pin bluePin"
                    shape="polygon"
                    coords="106, 359, 119, 361, 154, 345, 173, 355, 176, 373, 187, 379, 171, 403, 155, 415, 140, 430, 122, 425, 125, 400, 119, 392, 104, 395, 103, 379"
                    href="javascript:void(0);"
                  />
                  <area
                    id="area_11"
                    data-name="高雄市"
                    class="area_11 pin bluePin"
                    shape="polygon"
                    coords="116, 430, 134, 438, 152, 425, 171, 411, 190, 383, 196, 373, 195, 366, 203, 363, 235, 342, 243, 335, 255,334, 251, 351, 240, 359, 236, 378, 228, 387, 227, 409, 219, 416, 218, 438, 211, 441, 192, 432, 176, 441,156, 442, 150, 502, 144, 499, 148, 488, 137, 476, 137, 457, 121, 458, 116, 438"
                    href="javascript:void(0);"
                  />
                  <area
                    id="area_12"
                    data-name="屏東縣"
                    class="area_12 pin bluePin"
                    shape="polygon"
                    coords="161, 446, 174, 445, 193, 437, 210, 446, 221, 442, 226, 455, 202, 484, 203,522, 209, 527, 211, 541, 224, 549, 225, 582, 217, 593, 217, 604, 211, 596, 200, 600, 195, 583, 198, 575, 195, 551, 187, 532, 175, 516, 162, 510, 164, 507, 154, 505, 156, 465"
                    href="javascript:void(0);"
                  />
                  <area
                    id="area_13"
                    data-name="宜蘭縣"
                    class="area_13 pin bluePin"
                    shape="polygon"
                    coords="290, 161, 312, 125, 369, 83, 368, 105, 375, 132, 370, 152, 360, 162, 357, 180, 339, 171, 330, 179, 307, 167, 302, 170"
                    href="javascript:void(0);"
                  />
                  <area
                    id="area_14"
                    data-name="花蓮縣"
                    class="area_14 pin bluePin"
                    shape="polygon"
                    coords="320, 180, 337, 185, 345, 179, 364, 186, 349, 201, 342, 220, 342, 235, 321, 309, 321, 326, 316, 340, 304, 336, 282, 391, 277, 384, 255, 364, 246, 352, 254, 334, 261, 317, 277, 305, 282, 271, 287, 234, 294, 204"
                    href="javascript:void(0);"
                  />
                  <area
                    id="area_15"
                    data-name="臺東縣"
                    class="area_15 pin bluePin"
                    shape="polygon"
                    coords="245, 360, 237, 363, 234, 377, 225, 390, 224, 408, 217, 420, 217, 436, 222, 458, 203, 477, 197, 489,199, 509, 199, 520, 207, 525, 207, 538, 216, 546, 225, 511, 238, 481, 238, 471, 267, 452, 269, 441, 283, 421, 300, 391, 307, 370, 317, 348, 308, 341, 283, 399"
                    href="javascript:void(0);"
                  />
                  <area id="area_16" data-name="金門縣" class="area_16 pin bluePin" shape="rect" coords="10, 135, 114, 207" href="javascript:void(0);" />
                  <area id="area_17" data-name="澎湖縣" class="area_17 pin bluePin" shape="rect" coords="5, 278, 82, 325" href="javascript:void(0);" />
                  <area 
                    id="area_19" 
                    data-name="新北市" 
                    class="area_19 pin bluePin" 
                    shape="rect"
                    coords="311, 72, 339, 72, 339, 53, 363, 55, 363, 41, 378, 41, 388, 61, 311, 115, 303, 90, 297, 91, 292, 74, 301, 58, 290, 40, 303, 36, 305, 20, 322, 11, 335, 14, 342, 26, 310, 25"
                    href="javascript:void(0);"
                  />
                  <area id="area_20" data-name="基隆市" class="area_20 pin bluePin" shape="circle" coords="347, 41, 11" href="javascript:void(0);" />
                  <area id="area_21" data-name="連江縣" class="area_21 pin bluePin" shape="rect" coords="11, 10, 115, 80" href="javascript:void(0);" />
                  <area id="area_22" data-name="新竹縣" class="area_22 pin bluePin" shape="circle" coords="235, 99, 12" href="javascript:void(0);" />

                  <area
                    id="area_23"
                    data-name="嘉義縣"
                    class="area_23 pin bluePin"
                    shape="polygon"
                    coords="233, 336, 221, 326, 221, 314, 212, 310, 204, 317, 185, 316, 160, 303, 146, 308, 136, 320, 124, 322, 121, 326, 109, 326, 103, 352, 114, 353, 128, 347, 149, 338, 159, 341, 174, 353, 176, 368, 185, 372, 186, 359, 197, 358, 208, 350"
                    href="javascript:void(0);"
                  />
                </map>
                <img hidefocus="true" src="../../components/mrp/image/Taiwan.png" width="430" usemap="#FPMap1" />
              </div>
            </td>
            <td>
              <b-table 
                hover 
                show-empty responsive 
                class="m-0" 
                v-bind="$attrs" 
                v-on="$listeners" 
                :bordered="bordered"
                :fields="state.pickedFields" 
                :per-page="state.pagination.perPage"
                :current-page="isServerSidePaging ? 1 : state.pagination.currentPage" 
                :empty-text="state.emptyText"
                :empty-filtered-text="state.emptyFilterText" 
                :no-sort-reset="isServerSidePaging"
                :no-local-sorting="isServerSidePaging" 
                :filter="state.filter" 
                :filter-included-fields="state.filterOn"
                :filter-ignored-fields="state.ignoredFields" 
                @filtered="state.filtered"
                @sort-changed="handleSortChanged">
                <template v-for="(_, slot) of $scopedSlots" v-slot:[slot]="scope">
                  <slot :name="slot" v-bind="scope" />
                </template>
                <template #empty>
                  <div class="text-center m-3" v-if="state.itemsUndefinedBehaviorComputed">
                    <b-spinner></b-spinner>
                    <p>查詢中，請稍候...</p>
                  </div>
                </template>
              </b-table>
              <div id="areaFooter" :class="{ 'd-none': true, 'd-sm-block': !hideCardFooter }" class="card-footer" v-if="$attrs.items && $attrs.items.length > 0 && state.showFooter">
                <b-row align-h="around" align-v="center">
                  <b-col lg="2">
                    <b-input-group prepend="每頁" append="筆" class="mr-md-3" :size="'sm'">
                      <b-form-select v-model="state.pagination.perPage" :options="state.pagination.perPageOptions" @input="handlePageChanged"></b-form-select>
                    </b-input-group>
                  </b-col>
                  <b-col lg="4" class="pt-1 pt-lg-0">
                    <div>
                      <b-pagination 
                        class="my-0" align="fill" 
                        :size="'sm'" 
                        v-model="state.pagination.currentPage"
                        :total-rows="state.pagination.totalRows"
                        :per-page="state.pagination.perPage"
                        @input="handlePageChanged"/>
                    </div>
                  </b-col>
                  <b-col lg="3" class="pt-1 pt-lg-0 text-center text-lg-left">
                    第 {{ state.pagination.start }} 到 {{ state.pagination.end }} , 
                    共 {{ state.pagination.totalRows ? state.pagination.totalRows : '沒給totalItems' }} 筆 , 
                    頁次 {{ state.pagination.currentPage }}/{{ state.pagination.totalPages }}
                  </b-col>
                  <b-col lg="3" class="pt-1 pt-lg-0">
                    <b-input-group prepend="前往頁數" :size="'sm'">
                      <b-form-input v-model="state.pagination.gotoPage" @keydown.enter.prevent="handleGotoPage" />
                      <b-input-group-append>
                        <b-button variant="outline-success" @click="handleGotoPage">GO</b-button>
                      </b-input-group-append>
                    </b-input-group>
                  </b-col>
                </b-row>
              </div>
              <div id="mapFooterButtonGroup">
                <button id="mapCollectionDownloadButton" class="mt-1 ml-1 btn btn-info">下載</button>
                <button id="mapCollectionButton" type="button"  class="mt-1 ml-1 btn btn-info">
                  查詢集合
                  <span id="areaCollectionNumberIcon" class="badge badge-light">0</span>
                </button>
              </div>
            </td>
          </tr>
        </table>
      </div>

      <!-- 樹狀顯示 -->
      <div id="treeResult" class="resultDiv hidden">
        <table id="treeResultTable">
          <tr>
            <td>
              <div id="treeContainer">
              </div>
            </td>
            <td id="treeResultTable_2" class="hidden">
              <b-table 
                hover 
                show-empty responsive 
                class="m-0" 
                v-bind="$attrs" 
                v-on="$listeners" 
                :bordered="bordered"
                :fields="state.pickedFields" 
                :per-page="state.pagination.perPage"
                :current-page="isServerSidePaging ? 1 : state.pagination.currentPage" 
                :empty-text="state.emptyText"
                :empty-filtered-text="state.emptyFilterText" 
                :no-sort-reset="isServerSidePaging"
                :no-local-sorting="isServerSidePaging" 
                :filter="state.filter" 
                :filter-included-fields="state.filterOn"
                :filter-ignored-fields="state.ignoredFields" 
                @filtered="state.filtered"
                @sort-changed="handleSortChanged">
                <template v-for="(_, slot) of $scopedSlots" v-slot:[slot]="scope">
                  <slot :name="slot" v-bind="scope" />
                </template>
                <template #empty>
                  <div class="text-center m-3" v-if="state.itemsUndefinedBehaviorComputed">
                    <b-spinner></b-spinner>
                    <p>查詢中，請稍候...</p>
                  </div>
                </template>
              </b-table>
              <div id="areaFooter" :class="{ 'd-none': true, 'd-sm-block': !hideCardFooter }" class="card-footer" v-if="$attrs.items && $attrs.items.length > 0 && state.showFooter">
                <b-row align-h="around" align-v="center">
                  <b-col lg="2">
                    <b-input-group prepend="每頁" append="筆" class="mr-md-3" :size="'sm'">
                      <b-form-select v-model="state.pagination.perPage" :options="state.pagination.perPageOptions" @input="handlePageChanged"></b-form-select>
                    </b-input-group>
                  </b-col>
                  <b-col lg="4" class="pt-1 pt-lg-0">
                    <div>
                      <b-pagination 
                        class="my-0" align="fill" 
                        :size="'sm'" 
                        v-model="state.pagination.currentPage"
                        :total-rows="state.pagination.totalRows"
                        :per-page="state.pagination.perPage"
                        @input="handlePageChanged"/>
                    </div>
                  </b-col>
                  <b-col lg="3" class="pt-1 pt-lg-0 text-center text-lg-left">
                    第 {{ state.pagination.start }} 到 {{ state.pagination.end }} , 
                    共 {{ state.pagination.totalRows ? state.pagination.totalRows : '沒給totalItems' }} 筆 , 
                    頁次 {{ state.pagination.currentPage }}/{{ state.pagination.totalPages }}
                  </b-col>
                  <b-col lg="3" class="pt-1 pt-lg-0">
                    <b-input-group prepend="前往頁數" :size="'sm'">
                      <b-form-input v-model="state.pagination.gotoPage" @keydown.enter.prevent="handleGotoPage" />
                      <b-input-group-append>
                        <b-button variant="outline-success" @click="handleGotoPage">GO</b-button>
                      </b-input-group-append>
                    </b-input-group>
                  </b-col>
                </b-row>
              </div>
              <div id="treeFooterButtonGroup">
                <button id="treeCollectionDownloadButton" class="mt-1 ml-1 btn btn-info">下載</button>
                <button id="treeCollectionButton" type="button"  class="mt-1 ml-1 btn btn-info">
                  查詢集合
                  <span id="treeCollectionNumberIcon" class="badge badge-light">0</span>
                </button>
              </div>
            </td>
          </tr>
        </table>
      </div>

      <!-- 趨勢圖表 -->
      <div id="trendResult" class="resultDiv hidden">
        <figure class="highcharts-figure">
          <div id="highChartContainer"></div>
        </figure>
      </div>

    </b-card-body>
  </b-card>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import TableFieldsPicker from '@/shared/table-fields-picker/table-fields-picker.vue';
import { computed, onMounted, reactive } from '@vue/composition-api';
import axios from 'axios';
import {
  BButton,
  BCard,
  BCardBody,
  BCardHeader, 
  BCol, 
  BFormInput,
  BFormSelect,
  BInputGroup,
  BInputGroupAppend, 
  BPagination, 
  BRow, 
  BTable
} from 'bootstrap-vue';
import { Pagination } from '../model/pagination.model';

export default {
  name: 'mrp-table',
  inheritAttrs: false,
  components: {
    TableFieldsPicker,
    BCol,
    BPagination,
    BRow,
    BButton, 
    BCard,
    BCardBody,
    BCardHeader,
    BFormInput, 
    BFormSelect, 
    BInputGroup, 
    BInputGroupAppend,
    BTable
  },
  props: {
    // 頁面名稱
    page: {
      type: String,
      required: false,
      default: '',
    },
    // card-header裡的標題，只有在不顯示篩選器(showFilter為false)時才有用
    title: {
      type: String,
      required: false,
      default: '查詢結果',
    },
    // 當items為undefined時候，表格呈現的行為。hide：預設值，表格不呈現。loading：則表格會呈現查詢中的圖示。
    itemsUndefinedBehavior: {
      type: String,
      required: false,
      default: 'hide',
    },
    // 是否後端分頁
    isServerSidePaging: {
      type: Boolean,
      required: false,
      default: true,
    },
    // 資料總筆數，後端分頁(isServerSidePaging為true)時必須傳遞的參數
    totalItems: {
      type: Number,
      required: false,
    },
    // 是否去除包住i-table的card外框
    border0: {
      type: Boolean,
      required: false,
      default: false,
    },
    // 是否顯示i-table中b-table(card-body)本身的框線
    bordered: {
      type: Boolean,
      required: false,
      default: true,
    },
    // 是否將card-header內容換成篩選器
    showFilter: {
      type: Boolean,
      default: false,
      required: false,
    },
    // 篩選器要去除的欄位，傳遞方式為陣列，value為fields裡的key值，例如:filter-ignored-fields="['no', 'subject']"
    filterIgnoredFields: {
      type: Array,
      required: false,
      default: () => [],
    },
    // card-header及card-footer裡的元件尺寸
    size: {
      type: String,
      required: false,
      default: 'md',
    },
    // 隱藏序號field
    hideNo: {
      type: Boolean,
      required: false,
      default: false,
    },
    showFilterIcon: {
      type: Boolean,
      required: false,
      default: false,
    },
    hideCardHeader: {
      type: Boolean,
      required: false,
      default: false,
    },
    hideCardFooter: {
      type: Boolean,
      required: false,
      default: false,
    },
    // hideTable: {
    //   type: Boolean,
    //   required: false,
    //   default: false,
    // },
    showButtonGroup: {
      type: Boolean,
      required: false,
      default: false,
    },
    hasSdPopupModal: {
      type: Boolean,
      required: false,
      default: false,
    },
  },
  setup(props, context) {

    // 在table最左側添加流水序號
    // 第三個判斷是否已經有tableNo，是為了避免同頁面多個元件傳入相同fields時重複添加元素
    if (!props.hideNo && context.attrs.fields instanceof Array && !context.attrs.fields.find(field => field.key === 'tableNo')) {
      context.attrs.fields.unshift({
        // 序號
        key: 'tableNo',
        label: i18n.t('label.no'),
        sortable: false,
        thClass: 'text-center align-middle',
        tdClass: 'text-center align-middle',
      });
    }

    // assign新的避免使用TableFieldsPicker時，又被傳入的props.filterIgnoredFields覆蓋
    let filterIgnoredFieldsProp = Object.assign([], props.filterIgnoredFields);
    // 如有序號field則篩選器須去除
    if (context.attrs.fields instanceof Array && context.attrs.fields.find(field => field.key === 'tableNo')) {
      filterIgnoredFieldsProp.push('tableNo');
    }

    const pagination = new Pagination(0, 20, [], 'ASC');

    const state = reactive({
      itemsUndefinedBehaviorComputed: computed(() => {
        return context.attrs.items === undefined && props.itemsUndefinedBehavior === 'loading';
      }),
      //      emptyText: props.isServerSidePaging ? '查無資料，請修改條件後再重新查詢一次。' : '查無資料',
      emptyText: '查無資料',
      emptyFilterText: '篩選後無對應資料，請選擇其他篩選條件。',
      // TableFieldsPicker有打勾的選項
      pickedFields: [],
      // 篩選checkbox有的選項
      filterFields: [],
      // 篩選忽略的欄位
      ignoredFields: computed(() =>
        // 從傳進來的items中找出所有key並去除filterFields沒有的欄位，避免篩選時，篩到未設篩選的欄位
        // 用items過濾而非fields，是因為篩選器會篩選資料所有屬性而非只有fields顯示的
        context.attrs.items instanceof Array && context.attrs.items.length > 0
          ? Object.keys(context.attrs.items[0]).filter(key => !state.filterFields.map(field => field.key).includes(key)) : []
      ),
      pagination: {
        perPageOptions: [20, 30, 40, 50, 100],
        perPage: pagination.perPage,
        currentPage: 1,
        start: computed(() => (state.pagination.currentPage - 1) * state.pagination.perPage + 1),
        end: computed(() => {
          const end = state.pagination.currentPage * state.pagination.perPage;
          return end > state.pagination.totalRows ? (state.pagination.totalRows === 0 ? 1 : state.pagination.totalRows) : end;
        }),
        totalPages: computed(() => Math.ceil(state.pagination.totalRows / state.pagination.perPage)),
        // 篩選時總筆數依據篩選結果，未篩選時則依據前端或後端分頁，前端依items長度，後端依傳進來的totalItems
        totalRows: computed(() =>
          state.filter === null
            ? props.isServerSidePaging
              ? props.totalItems
              : context.attrs.items instanceof Array
                ? context.attrs.items.length
                : 0
            : state.pagination.filterRows
        ),
        // 篩選結果筆數
        filterRows: null,
        gotoPage: undefined,
      },
      showFooter: true,
      // 篩選條件
      filter: null,
      // 篩選條件作用於哪些欄位
      filterOn: [],
      // 輸入篩選條件時，將totalRows替換為filterRows，如無篩選結果則隱藏footer
      filtered: (filteredItems: string | any[]) => {
        state.pagination.filterRows = filteredItems.length;
        // state.pagination.currentPage = 1; filtered時currentPage會自己跳到1
        state.showFooter = state.pagination.filterRows !== 0 ? true : false;
      },
    });

    // 使用TableFieldsPicker時，改變顯示的欄位及變更篩選checkbox的選項
    const handlePicked = (pickedFields: any) => {
      state.pickedFields = pickedFields;
      state.filterFields = pickedFields.filter((field: { key: string }) => !filterIgnoredFieldsProp.includes(field.key));
    };

    const handleGotoPage = () => {
      const gotoPage = state.pagination.gotoPage;
      if (!isNaN(gotoPage) && gotoPage > 0 && gotoPage <= state.pagination.totalPages) {
        state.pagination.currentPage = gotoPage;
      }
      state.pagination.gotoPage = undefined;
      emitPagination();
    };

    const handleSortChanged = ctx => {
      if (ctx.sortBy) {
        pagination.sortBy = [ctx.sortBy];
        pagination.sortDirection = ctx.sortDesc ? 'DESC' : 'ASC';
        emitPagination();
      }
    };

    const handlePageChanged = () => {
      pagination.perPage = state.pagination.perPage;
      emitPagination();
    };

    const emitPagination = () => {
      if (props.isServerSidePaging) {
        pagination.currentPage = state.pagination.currentPage - 1;
        const param = Object.assign({}, pagination);
        context.emit('changePagination', param);
      }
    };

    onMounted(() => {
      if (context.attrs.hasOwnProperty('per-page') && context.attrs['per-page'] && !isNaN(<any>context.attrs['per-page'])) {
        state.pagination.perPage = context.attrs['per-page'];
      }
      if (context.attrs.hasOwnProperty('empty-text') && context.attrs['empty-text']) {
        state.emptyText = context.attrs['empty-text'];
      }
      if (context.attrs.hasOwnProperty('empty-filtered-text') && context.attrs['empty-filtered-text']) {
        state.emptyFilterText = String(context.attrs['empty-filtered-text']);
      }
    });

    function download(name) {
      axios
        .get('/service/getTheFile', {
          params: { fileName: name },
          responseType: 'blob',
        })
        .then(response => {
          console.log('downloadPDF.reponse : ', response);
          const href = URL.createObjectURL(response.data);
          const link = document.createElement('a');
          link.href = href;
          link.setAttribute('download', `${name}`);
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
          URL.revokeObjectURL(href);
        });
    }

    return {
      state,
      handlePicked,
      handleGotoPage,
      handleSortChanged,
      handlePageChanged,
      pagination,
      emitPagination,
      download,
    };
  },
};
</script>

<style>

#basicFooterButtonGroup,
#mapFooterButtonGroup,
#treeFooterButtonGroup {
  padding: 10px 20px;
  border: 1px solid #dee2e6
}

td  {
  margin: 0px 10px 0px 0px !important;
}

#searchTips {
  padding: 20px 25px;
}


#searchTips button {
  margin-left: 0px !important;
  margin-right: 4px !important;
}

#searchTips p {
  margin: 0px !important;
  /* border: 1px solid green; */
}

#badgeGroup {
  margin: 20px 0px 20px 0px;
}

#optionButtonGroup {
  margin: 0px 20px 20px 20px;
}

/* mrp0101r, mrp0201r Style */

#sdPopupModal.popup {
  position: absolute;
  top: 290px;
  left: 60%;
  width: 400px;
  padding: 15px !important;
  border-radius: 10px;
  border: 2px solid #17a2b8;
  background-color: white;
}

.popup p {
  margin: 0px !important;
}

.hidden {
  display: none !important;
}

#basicResult table {
  width: 100%;
}


#basicResult td,
#areaResult td,
#treeResult td {
  vertical-align: top;
}

#areaResultTable>tr>td,
#treeResultTable>tr>td {
  padding: 0px;
}

#mapContainer,
#treeContainer {
  width: 460px !important;
  /* border: 1px solid green; */
}
#areaResultTable>tr>td:last-child,
#treeResultTable>tr>td:last-child {
  width: 100%;
}

#mapContainer {
  display: flex;
  justify-content: center;
  /* border: 1px solid blue; */
}
.bluePin { 
  content: url('../../components/mrp/image/pin.png');
}
.redPin {
  content: url('../../components/mrp/image/selectedPin.png') !important;
}
.pin {
  transform: scale(0.05);
  position: absolute;
}



.area_1 { /* 臺北市 */  
  left: -55px;
  top: 40px;
}

.area_2 { /* 桃園市 */
  left: -105px;
  top: 65px;
}
.area_3 { /* 新竹縣 */
  left: -140px;
  top: 85px;
}
.area_4 { /* 苗栗縣 */
  left: -150px;
  top: 140px;
}
.area_5 { /* 臺中市 */
  left: -175px;
  top: 190px;
}
.area_6 { /* 彰化縣 */
  left: -215px;
  top: 225px;
}
.area_7 { /* 南投縣 */
  left: -145px;
  top: 250px;
}
.area_8 { /* 雲林縣 */
  left: -240px;
  top: 265px;
}
.area_9 { /* 嘉義市 */
  left: -225px;
  top: 300px;
}
.area_10 { /* 臺南市 */
  left: -235px;
  top: 360px;
}
.area_11 { /* 高雄市 */
  left: -185px;
  top: 385px;
}
.area_12 { /* 屏東縣 */
  left: -195px;
  top: 465px;
}
.area_13 { /* 宜蘭縣 */
  left: -45px;
  top: 128px;
}
.area_14 { /* 花蓮縣 */
  left: -75px;
  top: 260px;
}
.area_15 { /* 臺東縣 */
  left: -135px;
  top: 400px;
}
.area_16 { /* 金門縣 */
  left: -320px;
  top: 155px;
}
.area_17 { /* 澎湖縣 */
  left: -345px;
  top: 270px;
}
.area_19 { /* 新北市 */  
  left: -60px;
  top: 80px;
}
.area_20 { /* 基隆市 */
  left: -30px;
  top: 30px;
}
.area_21 { /* 連江縣 */
  left: -310px;
  top: 25px;
}
.area_22 { /* 新竹縣 */
  left: -110px;
  top: 115px;
}

.area_23 { /* 嘉義縣 */
  left: -190px;
  top: 315px;
}


/* tree */
#treeContainer * {
  list-style: none !important;
}

#treeContainer>ul {
  padding-left: 25px;
}

#treeContainer>ul ul {
  padding-left: 1.2em;
}

.last_ul {
  padding-left: 0px;
}

.last_li {
  padding-left: 1.2em;
  background: url(../../components/mrp/image/file.png) no-repeat;
  background-position: left;
}
.closeFolder {
  padding-left: 1.2em;
  background: url(../../components/mrp/image/closeFolder.png) no-repeat;
  background-position: left;
}
.openFolder {
  padding-left: 1.2em;
  background: url(../../components/mrp/image/openFolder.png) no-repeat;
  background-position: left;
}


#treeContainer li {
  cursor: pointer;
}

/* tree */


/* trend */
/* trend */



</style>

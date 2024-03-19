<template>
  <div>
    <section class="container mt-2" v-if="roleRef === RoleEnum.ADMIN">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                鄰近致災案件 查詢條件
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
        <div class="card-body col-11">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <!-- 颱風 -->
              <i-form-group-check :label="$t('label.typhoonName')+'：'" :item="$v.typhoonName" :labelStar="true">
                <b-form-select v-model="$v.typhoonName.$model" :options="queryOptions.typhoonName" :state="validateState($v.typhoonName)"> </b-form-select>
              </i-form-group-check>
              <!-- 工程類別 -->
              <i-form-group-check :label="$t('label.typeCode')+'：'" :item="$v.typeCode" :labelStar="true">
                <b-form-select v-model="$v.typeCode.$model" :options="queryOptions.typeCode" :state="validateState($v.typeCode)"> </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 縣市 -->
              <i-form-group-check :label="$t('label.County')+'：'" :item="$v.County" :labelStar="true">
                <b-form-select v-model="$v.County.$model" :options="queryOptions.County" :state="validateState($v.County)"> </b-form-select>
              </i-form-group-check>
              <!-- 鄉鎮市 -->
              <i-form-group-check :label="$t('label.city')+'：'" :item="$v.city" :labelStar="true">
                <b-form-select v-model="$v.city.$model" :options="queryOptions.city" :state="validateState($v.city)"> </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!--鄰近案件距離(公尺)-->
              <i-form-group-check :label="'鄰近案件距離(公尺):'" :labelStar="true" >
                <b-form-input type="number" trim ></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <i-button type="search" v-promise-btn="{ promise: dataPromise }" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2" v-if="queryStatus">
      <i-table
        class="table-sm"
        itemsUndefinedBehavior="loading"
        :items="table.data"
        :fields="table.fields_backend"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      >
        <!--              @changePagination="handlePaginationChanged($event)"-->
        <!--            >-->
        <template #cell(action)="row">
          <b-button variant="info" @click="openViewProject">檢視工程</b-button>
          <b-button variant="info" class="mt-2" @click="openViewNearbyProjects">檢視鄰近案件</b-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import NotificationService from '@/shared/notification/notification-service';
import { useValidation, validateState } from '@/shared/form';
import { onActivated, reactive, Ref, ref, toRef } from '@vue/composition-api';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required } from '@/shared/validators';
import { parseRocDate, formatDate } from '@/shared/date/minguo-calendar-utils';
import { navigateByNameAndParams } from '@/router/router';
// import axios from 'axios';
// import { notificationErrorHandler } from '@/shared/http/http-response-helper';
// import { egrCertificate } from '@/shared/model/cms-comp.model';
// import { Pagination } from '@/shared/model/pagination.model';
import { RoleEnum } from '@/shared/enum';

export default {
  name: 'rec0211Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
    form: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    // 切換角色
    const switchRoles = () => {
      if (roleRef.value === RoleEnum.ADMIN) roleRef.value = RoleEnum.USER;
      else roleRef.value = RoleEnum.ADMIN;
    };

    const isReloadProp = toRef(props, 'isReload');

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    const queryOptions = reactive({
      typeCode: [
        { value: '', text:'無(請選擇)'},
        { value: '0', text: 'A1	水利工程' },
        { value: '1', text: 'B1	觀光工程' },
        { value: '3', text: 'C1	道路橋梁工程-公路系統工程' },
        { value: '4', text: 'C2	道路橋梁工程-村里聯絡道路工程' },
        { value: '5', text: 'D1	建築工程' },
        { value: '6', text: 'E1	下水道工程' },
        { value: '7', text: 'F1	共同管道工程' },
        { value: '8', text: 'G1	水土保持工程' },
        { value: '9', text: 'H1	農水路工程-農地重劃區農水路工程' },
        { value: '10', text: 'H2 農水路工程-養殖漁業專區農水路工程' },
        { value: '11', text: 'H3 農水路工程-其他農路工程' },
        { value: '12', text: 'I1 林道工程' },
        { value: '13', text: 'J1 森林遊樂區設施工程' },
        { value: '14', text: 'K1 漁港工程' },
        { value: '15', text: 'L1 學校工程' },
        { value: '16', text: 'M1 環境保護工程' },
        { value: '17', text: 'N1 原住民族部落聯絡道及環境工程' },
        { value: '18', text: 'P1 文化資產工程' },
      ],
      typhoonName: [
        { value: '', text:'無(請選擇)'},
        { value: '1', text: '未填' },
        { value: '2', text: '有填未完整' },
        { value: '3', text: '完整' },
        { value: '4', text: '未填或有填未完整' },
      ],
      County: [
        { value: '', text:'無(請選擇)'},
        { value: '0', text: '基隆市' },
        { value: '1', text: '臺北市' },
        { value: '3', text: '新北市' },
        { value: '4', text: '桃園市' },
        { value: '5', text: '新竹市' },
        { value: '6', text: '新竹縣' },
        { value: '7', text: '苗栗縣' },
        { value: '8', text: '臺中市' },
        { value: '9', text: '彰化縣' },
        { value: '10', text: '南投縣' },
        { value: '11', text: '雲林縣' },
        { value: '12', text: '嘉義市' },
        { value: '13', text: '嘉義縣' },
        { value: '14', text: '臺南市' },
        { value: '15', text: '高雄市' },
        { value: '16', text: '屏東縣' },
        { value: '17', text: '臺東縣' },
        { value: '18', text: '花蓮縣' },
        { value: '19', text: '宜蘭縣' },
        { value: '20', text: '澎湖縣' },
        { value: '21', text: '金門縣' },
        { value: '22', text: '連江縣' },
      ],
      city: [
        { value: '', text:'無(請選擇)'},
        {value: ' 0 ', text:' 台北市中正區 '},
        {value: ' 1 ', text:' 台北市大同區 '},
        {value: ' 2 ', text:' 台北市中山區 '},
        {value: ' 3 ', text:' 台北市松山區 '},
        {value: ' 4 ', text:' 台北市大安區 '},
        {value: ' 5 ', text:' 台北市萬華區 '},
        {value: ' 6 ', text:' 台北市信義區 '},
        {value: ' 7 ', text:' 台北市士林區 '},
        {value: ' 8 ', text:' 台北市北投區 '},
        {value: ' 9 ', text:' 台北市內湖區 '},
        {value: ' 10 ', text:' 台北市南港區 '},
        {value: ' 11 ', text:' 台北市文山區 '},
        {value: ' 12 ', text:' 基隆市仁愛區 '},
        {value: ' 13 ', text:' 基隆市信義區 '},
        {value: ' 14 ', text:' 基隆市中正區 '},
        {value: ' 15 ', text:' 基隆市中山區 '},
        {value: ' 16 ', text:' 基隆市安樂區 '},
        {value: ' 17 ', text:' 基隆市暖暖區 '},
        {value: ' 18 ', text:' 基隆市七堵區 '},
        {value: ' 19 ', text:' 新北市萬里區 '},
        {value: ' 20 ', text:' 新北市金山區 '},
        {value: ' 21 ', text:' 連江縣南竿鄉 '},
        {value: ' 22 ', text:' 連江縣北竿鄉 '},
        {value: ' 23 ', text:' 連江縣莒光鄉 '},
        {value: ' 24 ', text:' 連江縣東引鄉 '},
        {value: ' 25 ', text:' 新北市板橋區 '},
        {value: ' 26 ', text:' 新北市汐止區 '},
        {value: ' 27 ', text:' 新北市深坑區 '},
        {value: ' 28 ', text:' 新北市石碇區 '},
        {value: ' 29 ', text:' 新北市瑞芳區 '},
        {value: ' 30 ', text:' 新北市平溪區 '},
        {value: ' 31 ', text:' 新北市雙溪區 '},
        {value: ' 32 ', text:' 新北市貢寮區 '},
        {value: ' 33 ', text:' 新北市新店區 '},
        {value: ' 34 ', text:' 新北市坪林區 '},
        {value: ' 35 ', text:' 新北市烏來區 '},
        {value: ' 36 ', text:' 新北市永和區 '},
        {value: ' 37 ', text:' 新北市中和區 '},
        {value: ' 38 ', text:' 新北市土城區 '},
        {value: ' 39 ', text:' 新北市三峽區 '},
        {value: ' 40 ', text:' 新北市樹林區 '},
        {value: ' 41 ', text:' 新北市鶯歌區 '},
        {value: ' 42 ', text:' 新北市三重區 '},
        {value: ' 43 ', text:' 新北市新莊區 '},
        {value: ' 44 ', text:' 新北市泰山區 '},
        {value: ' 45 ', text:' 新北市林口區 '},
        {value: ' 46 ', text:' 新北市蘆洲區 '},
        {value: ' 47 ', text:' 新北市五股區 '},
        {value: ' 48 ', text:' 新北市八里區 '},
        {value: ' 49 ', text:' 新北市淡水區 '},
        {value: ' 50 ', text:' 新北市三芝區 '},
        {value: ' 51 ', text:' 新北市石門區 '},
        {value: ' 52 ', text:' 宜蘭縣宜蘭市 '},
        {value: ' 53 ', text:' 宜蘭縣頭城鎮 '},
        {value: ' 54 ', text:' 宜蘭縣礁溪鄉 '},
        {value: ' 55 ', text:' 宜蘭縣壯圍鄉 '},
        {value: ' 56 ', text:' 宜蘭縣員山鄉 '},
        {value: ' 57 ', text:' 宜蘭縣羅東鎮 '},
        {value: ' 58 ', text:' 宜蘭縣三星鄉 '},
        {value: ' 59 ', text:' 宜蘭縣大同鄉 '},
        {value: ' 60 ', text:' 宜蘭縣五結鄉 '},
        {value: ' 61 ', text:' 宜蘭縣冬山鄉 '},
        {value: ' 62 ', text:' 宜蘭縣蘇澳鎮 '},
        {value: ' 63 ', text:' 宜蘭縣南澳鄉 '},
        {value: ' 64 ', text:' 釣魚台 '},
        {value: ' 65 ', text:' 新竹市東區 '},
        {value: ' 66 ', text:' 新竹市北區 '},
        {value: ' 67 ', text:' 新竹市香山區 '},
        {value: ' 68 ', text:' 新竹縣竹北市 '},
        {value: ' 69 ', text:' 新竹縣湖口鄉 '},
        {value: ' 70 ', text:' 新竹縣新豐鄉 '},
        {value: ' 71 ', text:' 新竹縣新埔鎮 '},
        {value: ' 72 ', text:' 新竹縣關西鎮 '},
        {value: ' 73 ', text:' 新竹縣芎林鄉 '},
        {value: ' 74 ', text:' 新竹縣寶山鄉 '},
        {value: ' 75 ', text:' 新竹縣竹東鎮 '},
        {value: ' 76 ', text:' 新竹縣五峰鄉 '},
        {value: ' 77 ', text:' 新竹縣橫山鄉 '},
        {value: ' 78 ', text:' 新竹縣尖石鄉 '},
        {value: ' 79 ', text:' 新竹縣北埔鄉 '},
        {value: ' 80 ', text:' 新竹縣峨眉鄉 '},
        {value: ' 81 ', text:' 桃園市中壢區 '},
        {value: ' 82 ', text:' 桃園市平鎮區 '},
        {value: ' 83 ', text:' 桃園市龍潭區 '},
        {value: ' 84 ', text:' 桃園市楊梅區 '},
        {value: ' 85 ', text:' 桃園市新屋區 '},
        {value: ' 86 ', text:' 桃園市觀音區 '},
        {value: ' 87 ', text:' 桃園市桃園區 '},
        {value: ' 88 ', text:' 桃園市龜山區 '},
        {value: ' 89 ', text:' 桃園市八德區 '},
        {value: ' 90 ', text:' 桃園市大溪區 '},
        {value: ' 91 ', text:' 桃園市復興區 '},
        {value: ' 92 ', text:' 桃園市大園區 '},
        {value: ' 93 ', text:' 桃園市蘆竹區 '},
        {value: ' 94 ', text:' 苗栗縣竹南鎮 '},
        {value: ' 95 ', text:' 苗栗縣頭份鎮 '},
        {value: ' 96 ', text:' 苗栗縣三灣鄉 '},
        {value: ' 97 ', text:' 苗栗縣南庄鄉 '},
        {value: ' 98 ', text:' 苗栗縣獅潭鄉 '},
        {value: ' 99 ', text:' 苗栗縣後龍鎮 '},
        {value: ' 100 ', text:' 苗栗縣通霄鎮 '},
        {value: ' 101 ', text:' 苗栗縣苑裡鎮 '},
        {value: ' 102 ', text:' 苗栗縣苗栗市 '},
        {value: ' 103 ', text:' 苗栗縣造橋鄉 '},
        {value: ' 104 ', text:' 苗栗縣頭屋鄉 '},
        {value: ' 105 ', text:' 苗栗縣公館鄉 '},
        {value: ' 106 ', text:' 苗栗縣大湖鄉 '},
        {value: ' 107 ', text:' 苗栗縣泰安鄉 '},
        {value: ' 108 ', text:' 苗栗縣銅鑼鄉 '},
        {value: ' 109 ', text:' 苗栗縣三義鄉 '},
        {value: ' 110 ', text:' 苗栗縣西湖鄉 '},
        {value: ' 111 ', text:' 苗栗縣卓蘭鎮 '},
        {value: ' 112 ', text:' 台中市中區 '},
        {value: ' 113 ', text:' 台中市東區 '},
        {value: ' 114 ', text:' 台中市南區 '},
        {value: ' 115 ', text:' 台中市西區 '},
        {value: ' 116 ', text:' 台中市北區 '},
        {value: ' 117 ', text:' 台中市北屯區 '},
        {value: ' 118 ', text:' 台中市西屯區 '},
        {value: ' 119 ', text:' 台中市南屯區 '},
        {value: ' 120 ', text:' 台中市太平區 '},
        {value: ' 121 ', text:' 台中市大里區 '},
        {value: ' 122 ', text:' 台中市霧峰區 '},
        {value: ' 123 ', text:' 台中市烏日區 '},
        {value: ' 124 ', text:' 台中市豐原區 '},
        {value: ' 125 ', text:' 台中市后里區 '},
        {value: ' 126 ', text:' 台中市石岡區 '},
        {value: ' 127 ', text:' 台中市東勢區 '},
        {value: ' 128 ', text:' 台中市和平區 '},
        {value: ' 129 ', text:' 台中市新社區 '},
        {value: ' 130 ', text:' 台中市潭子區 '},
        {value: ' 131 ', text:' 台中市大雅區 '},
        {value: ' 132 ', text:' 台中市神岡區 '},
        {value: ' 133 ', text:' 台中市大肚區 '},
        {value: ' 134 ', text:' 台中市沙鹿區 '},
        {value: ' 135 ', text:' 台中市龍井區 '},
        {value: ' 136 ', text:' 台中市梧棲區 '},
        {value: ' 137 ', text:' 台中市清水區 '},
        {value: ' 138 ', text:' 台中市大甲區 '},
        {value: ' 139 ', text:' 台中市外埔區 '},
        {value: ' 140 ', text:' 台中市大安區 '},
        {value: ' 141 ', text:' 彰化縣彰化市 '},
        {value: ' 142 ', text:' 彰化縣芬園鄉 '},
        {value: ' 143 ', text:' 彰化縣花壇鄉 '},
        {value: ' 144 ', text:' 彰化縣秀水鄉 '},
        {value: ' 145 ', text:' 彰化縣鹿港鎮 '},
        {value: ' 146 ', text:' 彰化縣福興鄉 '},
        {value: ' 147 ', text:' 彰化縣線西鄉 '},
        {value: ' 148 ', text:' 彰化縣和美鎮 '},
        {value: ' 149 ', text:' 彰化縣伸港鄉 '},
        {value: ' 150 ', text:' 彰化縣員林鎮 '},
        {value: ' 151 ', text:' 彰化縣社頭鄉 '},
        {value: ' 152 ', text:' 彰化縣永靖鄉 '},
        {value: ' 153 ', text:' 彰化縣埔心鄉 '},
        {value: ' 154 ', text:' 彰化縣溪湖鎮 '},
        {value: ' 155 ', text:' 彰化縣大村鄉 '},
        {value: ' 156 ', text:' 彰化縣埔鹽鄉 '},
        {value: ' 157 ', text:' 彰化縣田中鎮 '},
        {value: ' 158 ', text:' 彰化縣北斗鎮 '},
        {value: ' 159 ', text:' 彰化縣田尾鄉 '},
        {value: ' 160 ', text:' 彰化縣埤頭鄉 '},
        {value: ' 161 ', text:' 彰化縣溪州鄉 '},
        {value: ' 162 ', text:' 彰化縣竹塘鄉 '},
        {value: ' 163 ', text:' 彰化縣二林鎮 '},
        {value: ' 164 ', text:' 彰化縣大城鄉 '},
        {value: ' 165 ', text:' 彰化縣芳苑鄉 '},
        {value: ' 166 ', text:' 彰化縣二水鄉 '},
        {value: ' 167 ', text:' 南投縣南投市 '},
        {value: ' 168 ', text:' 南投縣中寮鄉 '},
        {value: ' 169 ', text:' 南投縣草屯鎮 '},
        {value: ' 170 ', text:' 南投縣國姓鄉 '},
        {value: ' 171 ', text:' 南投縣埔里鎮 '},
        {value: ' 172 ', text:' 南投縣仁愛鄉 '},
        {value: ' 173 ', text:' 南投縣名間鄉 '},
        {value: ' 174 ', text:' 南投縣集集鎮 '},
        {value: ' 175 ', text:' 南投縣水里鄉 '},
        {value: ' 176 ', text:' 南投縣魚池鄉 '},
        {value: ' 177 ', text:' 南投縣信義鄉 '},
        {value: ' 178 ', text:' 南投縣竹山鎮 '},
        {value: ' 179 ', text:' 南投縣鹿谷鄉 '},
        {value: ' 180 ', text:' 嘉義市東區 '},
        {value: ' 181 ', text:' 嘉義市西區 '},
        {value: ' 182 ', text:' 嘉義縣番路鄉 '},
        {value: ' 183 ', text:' 嘉義縣梅山鄉 '},
        {value: ' 184 ', text:' 嘉義縣竹崎鄉 '},
        {value: ' 185 ', text:' 嘉義縣阿里山鄉 '},
        {value: ' 186 ', text:' 嘉義縣中埔鄉 '},
        {value: ' 187 ', text:' 嘉義縣大埔鄉 '},
        {value: ' 188 ', text:' 嘉義縣水上鄉 '},
        {value: ' 189 ', text:' 嘉義縣鹿草鄉 '},
        {value: ' 190 ', text:' 嘉義縣太保市 '},
        {value: ' 191 ', text:' 嘉義縣朴子市 '},
        {value: ' 192 ', text:' 嘉義縣東石鄉 '},
        {value: ' 193 ', text:' 嘉義縣六腳鄉 '},
        {value: ' 194 ', text:' 嘉義縣新港鄉 '},
        {value: ' 195 ', text:' 嘉義縣民雄鄉 '},
        {value: ' 196 ', text:' 嘉義縣大林鎮 '},
        {value: ' 197 ', text:' 嘉義縣溪口鄉 '},
        {value: ' 198 ', text:' 嘉義縣義竹鄉 '},
        {value: ' 199 ', text:' 嘉義縣布袋鎮 '},
        {value: ' 200 ', text:' 雲林縣斗南鎮 '},
        {value: ' 201 ', text:' 雲林縣大埤鄉 '},
        {value: ' 202 ', text:' 雲林縣虎尾鎮 '},
        {value: ' 203 ', text:' 雲林縣土庫鎮 '},
        {value: ' 204 ', text:' 雲林縣褒忠鄉 '},
        {value: ' 205 ', text:' 雲林縣東勢鄉 '},
        {value: ' 206 ', text:' 雲林縣台西鄉 '},
        {value: ' 207 ', text:' 雲林縣崙背鄉 '},
        {value: ' 208 ', text:' 雲林縣麥寮鄉 '},
        {value: ' 209 ', text:' 雲林縣斗六市 '},
        {value: ' 210 ', text:' 雲林縣林內鄉 '},
        {value: ' 211 ', text:' 雲林縣古坑鄉 '},
        {value: ' 212 ', text:' 雲林縣莿桐鄉 '},
        {value: ' 213 ', text:' 雲林縣西螺鎮 '},
        {value: ' 214 ', text:' 雲林縣二崙鄉 '},
        {value: ' 215 ', text:' 雲林縣北港鎮 '},
        {value: ' 216 ', text:' 雲林縣水林鄉 '},
        {value: ' 217 ', text:' 雲林縣口湖鄉 '},
        {value: ' 218 ', text:' 雲林縣四湖鄉 '},
        {value: ' 219 ', text:' 雲林縣元長鄉 '},
        {value: ' 220 ', text:' 台南市中西區 '},
        {value: ' 221 ', text:' 台南市東區 '},
        {value: ' 222 ', text:' 台南市南區 '},
        {value: ' 223 ', text:' 台南市北區 '},
        {value: ' 224 ', text:' 台南市安平區 '},
        {value: ' 225 ', text:' 台南市安南區 '},
        {value: ' 226 ', text:' 台南市永康區 '},
        {value: ' 227 ', text:' 台南市歸仁區 '},
        {value: ' 228 ', text:' 台南市新化區 '},
        {value: ' 229 ', text:' 台南市左鎮區 '},
        {value: ' 230 ', text:' 台南市玉井區 '},
        {value: ' 231 ', text:' 台南市楠西區 '},
        {value: ' 232 ', text:' 台南市南化區 '},
        {value: ' 233 ', text:' 台南市仁德區 '},
        {value: ' 234 ', text:' 台南市關廟區 '},
        {value: ' 235 ', text:' 台南市龍崎區 '},
        {value: ' 236 ', text:' 台南市官田區 '},
        {value: ' 237 ', text:' 台南市麻豆區 '},
        {value: ' 238 ', text:' 台南市佳里區 '},
        {value: ' 239 ', text:' 台南市西港區 '},
        {value: ' 240 ', text:' 台南市七股區 '},
        {value: ' 241 ', text:' 台南市將軍區 '},
        {value: ' 242 ', text:' 台南市學甲區 '},
        {value: ' 243 ', text:' 台南市北門區 '},
        {value: ' 244 ', text:' 台南市新營區 '},
        {value: ' 245 ', text:' 台南市後壁區 '},
        {value: ' 246 ', text:' 台南市白河區 '},
        {value: ' 247 ', text:' 台南市東山區 '},
        {value: ' 248 ', text:' 台南市六甲區 '},
        {value: ' 249 ', text:' 台南市下營區 '},
        {value: ' 250 ', text:' 台南市柳營區 '},
        {value: ' 251 ', text:' 台南市鹽水區 '},
        {value: ' 252 ', text:' 台南市善化區 '},
        {value: ' 253 ', text:' 台南市大內區 '},
        {value: ' 254 ', text:' 台南市山上區 '},
        {value: ' 255 ', text:' 台南市新市區 '},
        {value: ' 256 ', text:' 台南市安定區 '},
        {value: ' 257 ', text:' 高雄市新興區 '},
        {value: ' 258 ', text:' 高雄市前金區 '},
        {value: ' 259 ', text:' 高雄市苓雅區 '},
        {value: ' 260 ', text:' 高雄市鹽埕區 '},
        {value: ' 261 ', text:' 高雄市鼓山區 '},
        {value: ' 262 ', text:' 高雄市旗津區 '},
        {value: ' 263 ', text:' 高雄市前鎮區 '},
        {value: ' 264 ', text:' 高雄市三民區 '},
        {value: ' 265 ', text:' 高雄市楠梓區 '},
        {value: ' 266 ', text:' 高雄市小港區 '},
        {value: ' 267 ', text:' 高雄市左營區 '},
        {value: ' 268 ', text:' 高雄市仁武區 '},
        {value: ' 269 ', text:' 高雄市大社區 '},
        {value: ' 270 ', text:' 南海島東沙群島 '},
        {value: ' 271 ', text:' 南海島南沙群島 '},
        {value: ' 272 ', text:' 高雄市岡山區 '},
        {value: ' 273 ', text:' 高雄市路竹區 '},
        {value: ' 274 ', text:' 高雄市阿蓮區 '},
        {value: ' 275 ', text:' 高雄市田寮區 '},
        {value: ' 276 ', text:' 高雄市燕巢區 '},
        {value: ' 277 ', text:' 高雄市橋頭區 '},
        {value: ' 278 ', text:' 高雄市梓官區 '},
        {value: ' 279 ', text:' 高雄市彌陀區 '},
        {value: ' 280 ', text:' 高雄市永安區 '},
        {value: ' 281 ', text:' 高雄市湖內區 '},
        {value: ' 282 ', text:' 高雄市鳳山區 '},
        {value: ' 283 ', text:' 高雄市大寮區 '},
        {value: ' 284 ', text:' 高雄市林園區 '},
        {value: ' 285 ', text:' 高雄市鳥松區 '},
        {value: ' 286 ', text:' 高雄市大樹區 '},
        {value: ' 287 ', text:' 高雄市旗山區 '},
        {value: ' 288 ', text:' 高雄市美濃區 '},
        {value: ' 289 ', text:' 高雄市六龜區 '},
        {value: ' 290 ', text:' 高雄市內門區 '},
        {value: ' 291 ', text:' 高雄市杉林區 '},
        {value: ' 292 ', text:' 高雄市甲仙區 '},
        {value: ' 293 ', text:' 高雄市桃源區 '},
        {value: ' 294 ', text:' 高雄市那瑪夏區 '},
        {value: ' 295 ', text:' 高雄市茂林區 '},
        {value: ' 296 ', text:' 高雄市茄萣區 '},
        {value: ' 297 ', text:' 澎湖縣馬公市 '},
        {value: ' 298 ', text:' 澎湖縣西嶼鄉澎湖縣望安鄉 '},
        {value: ' 299 ', text:' 澎湖縣七美鄉 '},
        {value: ' 300 ', text:' 澎湖縣白沙鄉 '},
        {value: ' 301 ', text:' 澎湖縣湖西鄉 '},
        {value: ' 302 ', text:' 金門縣金沙鎮 '},
        {value: ' 303 ', text:' 金門縣金湖鎮 '},
        {value: ' 304 ', text:' 金門縣金寧鄉 '},
        {value: ' 305 ', text:' 金門縣金城鎮 '},
        {value: ' 306 ', text:' 金門縣烈嶼鄉 '},
        {value: ' 307 ', text:' 金門縣烏坵鄉 '},
        {value: ' 308 ', text:' 屏東縣屏東市 '},
        {value: ' 309 ', text:' 屏東縣三地門鄉 '},
        {value: ' 310 ', text:' 屏東縣霧台鄉 '},
        {value: ' 311 ', text:' 屏東縣瑪家鄉 '},
        {value: ' 312 ', text:' 屏東縣九如鄉 '},
        {value: ' 313 ', text:' 屏東縣里港鄉 '},
        {value: ' 314 ', text:' 屏東縣高樹鄉 '},
        {value: ' 315 ', text:' 屏東縣鹽埔鄉 '},
        {value: ' 316 ', text:' 屏東縣長治鄉 '},
        {value: ' 317 ', text:' 屏東縣麟洛鄉 '},
        {value: ' 318 ', text:' 屏東縣竹田鄉 '},
        {value: ' 319 ', text:' 屏東縣內埔鄉 '},
        {value: ' 320 ', text:' 屏東縣萬丹鄉 '},
        {value: ' 321 ', text:' 屏東縣潮州鎮 '},
        {value: ' 322 ', text:' 屏東縣泰武鄉 '},
        {value: ' 323 ', text:' 屏東縣來義鄉 '},
        {value: ' 324 ', text:' 屏東縣萬巒鄉 '},
        {value: ' 325 ', text:' 屏東縣崁頂鄉 '},
        {value: ' 326 ', text:' 屏東縣新埤鄉 '},
        {value: ' 327 ', text:' 屏東縣南州鄉 '},
        {value: ' 328 ', text:' 屏東縣林邊鄉 '},
        {value: ' 329 ', text:' 屏東縣東港鎮 '},
        {value: ' 330 ', text:' 屏東縣琉球鄉 '},
        {value: ' 331 ', text:' 屏東縣佳冬鄉 '},
        {value: ' 332 ', text:' 屏東縣新園鄉 '},
        {value: ' 333 ', text:' 屏東縣枋寮鄉 '},
        {value: ' 334 ', text:' 屏東縣枋山鄉 '},
        {value: ' 335 ', text:' 屏東縣春日鄉 '},
        {value: ' 336 ', text:' 屏東縣獅子鄉 '},
        {value: ' 337 ', text:' 屏東縣車城鄉 '},
        {value: ' 338 ', text:' 屏東縣牡丹鄉 '},
        {value: ' 339 ', text:' 屏東縣恆春鎮 '},
        {value: ' 340 ', text:' 屏東縣滿州鄉 '},
        {value: ' 341 ', text:' 台東縣台東市 '},
        {value: ' 342 ', text:' 台東縣綠島鄉 '},
        {value: ' 343 ', text:' 台東縣蘭嶼鄉 '},
        {value: ' 344 ', text:' 台東縣延平鄉 '},
        {value: ' 345 ', text:' 台東縣卑南鄉 '},
        {value: ' 346 ', text:' 台東縣鹿野鄉 '},
        {value: ' 347 ', text:' 台東縣關山鎮 '},
        {value: ' 348 ', text:' 台東縣海端鄉 '},
        {value: ' 349 ', text:' 台東縣池上鄉 '},
        {value: ' 350 ', text:' 台東縣東河鄉 '},
        {value: ' 351 ', text:' 台東縣成功鎮 '},
        {value: ' 352 ', text:' 台東縣長濱鄉 '},
        {value: ' 353 ', text:' 台東縣太麻里鄉 '},
        {value: ' 354 ', text:' 台東縣金峰鄉 '},
        {value: ' 355 ', text:' 台東縣大武鄉 '},
        {value: ' 356 ', text:' 台東縣達仁鄉 '},
        {value: ' 357 ', text:' 花蓮縣花蓮市 '},
        {value: ' 358 ', text:' 花蓮縣新城鄉 '},
        {value: ' 359 ', text:' 花蓮縣秀林鄉 '},
        {value: ' 360 ', text:' 花蓮縣吉安鄉 '},
        {value: ' 361 ', text:' 花蓮縣壽豐鄉 '},
        {value: ' 362 ', text:' 花蓮縣鳳林鎮 '},
        {value: ' 363 ', text:' 花蓮縣光復鄉 '},
        {value: ' 364 ', text:' 花蓮縣豐濱鄉 '},
        {value: ' 365 ', text:' 花蓮縣瑞穗鄉 '},
        {value: ' 366 ', text:' 花蓮縣萬榮鄉 '},
        {value: ' 367 ', text:' 花蓮縣玉里鎮 '},
        {value: ' 368 ', text:' 花蓮縣卓溪鄉 '},
        {value: ' 369 ', text:' 花蓮縣富里鄉 '},

      ],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      typhoonName: '',
      recEngName: '',
      typeCode: '',
      County:'',
      city:''
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      typhoonName: {required:required},
      typeCode: {required:required},
      County:{required:required},
      city:{required:required}

    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    //跳轉至檢視案件
    const openViewProject = () => {
      navigateByNameAndParams('rec0211-view-project', null);
    };

    //跳轉至檢視鄰近案件
    const openViewNearbyProjects = () => {
      navigateByNameAndParams('rec0211-view-nearby-projects', null);
    };

    // 查詢結果及狀態
    const queryStatus: Ref<Boolean> = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
      fields_backend: [
         {
           // 序號
           key: 'submittedId',
           label: '提報序號',
           sortable: false,
           thClass: 'text-center',
           tdClass: 'text-left align-middle',
         },
        {
          // 復健工程名稱
          key: 'recEngName',
          label: i18n.t('label.recEngName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          // 鄰近案件風災名稱
          key: 'nearbyCaseStormName',
          label: '鄰近案件風災名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          // 鄰近案件工程名稱
          key: 'nearbyCaseName',
          label: '鄰近案件工程名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 工程代碼
          key: 'typeCode',
          label: i18n.t('label.typeCode'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => optionsFormatter('typeCode', value),
        },
        {
          // 鄰近案件提報序號
          key: 'nearbyCaseId',
          label: '鄰近案件提報序號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 距離(公尺)
          key: 'nearbyCaseDistance',
          label: '距離(公尺)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 動作
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // thStyle: { width: '10%' },
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          // dataPromise.value = axios
          //   // .post('/cms-comps/criteria', form)           //前端分頁
          //   .post('/cms-comps/criteria-jpa', form) //後端分頁
          //   .then(({ data }) => {
          //     // table.data = data.slice(0, data.length);   //前端分頁
          //     // table.totalItems = data.length;            //前端分頁
          //     table.data = data.content.slice(0, data.content.length); //後端分頁
          //     table.totalItems = data.totalElements; //後端分頁
          //   })
          //   .finally(() => (dataPromise.value = null))
          //   .catch(notificationErrorHandler(notificationService));
          table.data = [
            {
              submittedId: '005',
              recEngName: '大埔村農地排水災修工程',
              nearbyCaseStormName: '108年8月利奇馬及白鹿颱風',
              nearbyCaseName: '西興村(中心崙坑)簡易自來水災修工程',
              typhoonName: '2',
              typeCode: '0',
              nearbyCaseId: '001',
              nearbyCaseDistance: '7877'
             
            },
            {
              submittedId: '005',
              recEngName: '大埔村農地排水災修工程',
              nearbyCaseStormName: '109年9月豪雨',
              nearbyCaseName: '大茅埔大排災後復建工程',
              typhoonName: '2',
              typeCode: '0',
              nearbyCaseId: '001',
              nearbyCaseDistance: '3005'
             
            },
            {
              submittedId: '005',
              recEngName: '大埔村農地排水災修工程',
              nearbyCaseStormName: '106年6月豪雨',
              nearbyCaseName: '永樂村(埔頂)簡易自來水災修工程',
              typhoonName: '2',
              typeCode: '0',
              nearbyCaseId: '002',
              nearbyCaseDistance: '4553'
             
            },
            {
              submittedId: '005',
              recEngName: '大埔村農地排水災修工程',
              nearbyCaseStormName: '104年8月蘇迪勒颱風',
              nearbyCaseName: '嘉義縣大埔鄉大埔村鄉內排水工程',
              typhoonName: '2',
              typeCode: '0',
              nearbyCaseId: '003',
              nearbyCaseDistance: '795'
             
            },
          ];
          table.totalItems = 4;
        }
      });
    };

    const toEditView = (engYearreport: any) => {
      navigateByNameAndParams('Eng0202EditInfo', { engYearreport: engYearreport, role: roleRef.value });
    };

    // const handlePaginationChanged = (pagination: Pagination) => {
    //   Object.assign(form, pagination);
    //   handleQuery();
    // };

    const optionsFormatter = (option: string, value: string) => {
      let itemFound = queryOptions[option].find((item: { value: string }) => item.value === value);
      if (itemFound) {
        return itemFound.text;
      } else {
        return '';
      }
    };

    return {
      validateState,
      queryOptions,
      $v,
      stepVisible,
      queryStatus,
      dataPromise,
      table,
      reset,
      handleQuery,
      toEditView,
      roleRef,
      RoleEnum,
      ref,
      switchRoles,
      formatDate,
      optionsFormatter,
      openViewProject,
      openViewNearbyProjects,
      // handlePaginationChanged,
    };
  },
};
</script>
<style></style>

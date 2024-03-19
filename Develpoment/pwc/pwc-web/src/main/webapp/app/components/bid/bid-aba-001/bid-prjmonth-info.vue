<template>
  <div>
    <div v-if="pageShowStatus === showStatus.monthScheduleData">
      <div class="card">
        <div class="card-body col-12">
          <div class="d-flex">
            <h3>{{ prjMonth.syr }}年{{ prjMonth.month }}月執行情形</h3>
            <i-button type="arrow-left" @click="changeToList"></i-button>
          </div>

          <b-table-simple bordered>
            <b-tbody>
              <b-tr>
                <!-- 資料新增日期 -->
                <b-td style="width: 25%">{{ $t('label.dataCreateDate') }}</b-td>
                <b-td style="width: 25%">{{ formatDateTime(prjMonth.createDate, '/') }}</b-td>
                <!-- 資料更新日期 -->
                <b-td style="width: 25%">{{ $t('label.dataUpdateDate') }}</b-td>
                <b-td style="width: 25%">{{ formatDateTime(prjMonth.updateDate, '/') }}</b-td>
              </b-tr>
              <b-tr>
                <!-- 更新者帳號 -->
                <b-td style="width: 25%">{{ $t('label.updateUser') }}</b-td>
                <b-td style="width: 25%">{{ prjMonth.updateUser }}</b-td>
                <!--  更新者帳號-->
                <b-td style="width: 25%">{{ $t('label.updateIpAddr') }}</b-td>
                <b-td style="width: 25%">{{ formatDateTime(prjMonth.updateDate, '/') }}</b-td>
              </b-tr>
              <b-tr>
                <!-- 總累計預定進度 -->
                <b-td style="width: 25%">{{ $t('label.ptrate') }}</b-td>
                <b-td style="width: 25%">{{ prjMonth.ptrate }}</b-td>
                <!-- 總累計實際進度 -->
                <b-td style="width: 25%">{{ $t('label.atrate') }}</b-td>
                <b-td style="width: 25%">{{ prjMonth.atrate }}</b-td>
              </b-tr>
              <b-tr>
                <!-- 總累計預定完成金額 -->
                <b-td style="width: 25%">{{ $t('label.pexp') }}</b-td>
                <b-td style="width: 25%">{{ prjMonth.pexp }}</b-td>
                <!-- 總累計實際完成金額 -->
                <b-td style="width: 25%">{{ $t('label.aexp') }}</b-td>
                <b-td style="width: 25%">{{ prjMonth.aexp }}</b-td>
              </b-tr>
              <b-tr>
                <!-- 年累計預定進度 -->
                <b-td style="width: 25%">{{ $t('label.ypexp') }}</b-td>
                <b-td style="width: 25%">{{ prjMonth.ypexp }}</b-td>
                <!-- 年累計實際進度 -->
                <b-td style="width: 25%">{{ $t('label.yaexp') }}</b-td>
                <b-td style="width: 25%">{{ prjMonth.yaexp }}</b-td>
              </b-tr>
              <b-tr>
                <!-- 累計估驗計價金額 -->
                <b-td style="width: 25%">{{ $t('label.sumt') }}</b-td>
                <b-td style="width: 25%">{{ prjMonth.sumt }}</b-td>
                <!-- 執行狀況 -->
                <b-td style="width: 25%">{{ $t('label.stus') }}</b-td>
                <b-td style="width: 25%">{{ prjMonth.stus }}</b-td>
              </b-tr>
              <b-tr>
                <!-- 預定工作摘要 -->
                <b-td style="width: 25%">{{ $t('label.predictWorkSummary') }}</b-td>
                <b-td colspan="3">{{ prjMonth.item }}</b-td>
              </b-tr>
              <b-tr>
                <!-- 實際執行摘要 -->
                <b-td style="width: 25%">{{ $t('label.actWorkSummary') }}</b-td>
                <b-td colspan="3">{{ prjMonth.remark }}</b-td>
              </b-tr>
              <b-tr>
                <b-td style="width: 25%">本月進行中之專業工程項目</b-td>
                <b-td colspan="3">{{ professionalEngineering }}</b-td>
              </b-tr>
              <b-tr>
                <b-td style="width: 25%">現場管理</b-td>
                <b-td colspan="3"
                  ><div v-for="(message, index) in onSiteManagementArr" :key="index">
                    {{ message }}
                  </div>
                </b-td>
              </b-tr>
              <b-tr>
                <b-td style="width: 25%">重大機具使用</b-td>
                <b-td colspan="3">
                  <div v-if="equipTable.data && equipTable.data.length > 0">
                    <b-table :items="equipTable.data" :fields="equipTable.fields">
                      <template #cell(tableNo)="row">
                        {{ row.index + 1 }}
                      </template>
                      <!-- <template #cell(eCode)="row">
                        {{ equipConvert(row.item.eCode) }}
                      </template> -->
                    </b-table>
                  </div>
                  <div v-else><h6>無重大機具使用資料</h6></div>
                </b-td>
              </b-tr>
            </b-tbody>
          </b-table-simple>
        </div>
      </div>
      <!-- 落後資料 -->
      <div class="card mt-2">
        <div class="card-body col-12">
          <h5>{{ prjMonth.syr }}年{{ prjMonth.month }}月落後原因資料</h5>
          <div v-if="prjFalTable.data && prjFalTable.data.length > 0">
            <b-table bordered :items="prjFalTable.data" :fields="prjFalTable.fields">
              <template #cell(tableNo)="row">
                {{ row.index + 1 }}
              </template>
              <template #cell(action)="row">
                <i-button type="binoculars" @click="showMonthPrjFal(row.item)"></i-button>
              </template>
            </b-table>
          </div>
          <div v-else>
            <h6>無落後原因資料</h6>
          </div>
        </div>
      </div>
    </div>
    <div v-else-if="pageShowStatus === showStatus.monthPrjFalData">
      <div class="d-flex">
        <h3>{{ prjMonth.syr }}年{{ prjMonth.month }}月落後原因資料</h3>
        <i-button type="arrow-left" @click="changeShowStatus(showStatus.monthScheduleData)"></i-button>
      </div>

      <bidPrjFalInfo :bidPrjFalInfo="currentMonthFal"></bidPrjFalInfo>
    </div>
  </div>
</template>

<script lang="ts">
import { reactive, Ref, ref, toRef, watch } from '@vue/composition-api';
import { IBidPrjMonth, BidPrjMonth } from '@/shared/model/bidModel/bid-prj-month.model';
import bidPrjFalInfo from '@/components/bid/bid-aba-001/bid-prj-fal-info.vue';
import BidPrjMonthFormatter from '@/components/bid/bidService/bid-prj-month.formatter';
import BidPrjMonthService from '@/components/bid/bidService/bid-prj-month.service';
import BidPrjEquipService from '@/components/bid/bidService/bid-prj-equip.service';
import BidPrjFalService from '@/components/bid/bidService/bid-prj-fal.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import { formatDateTime } from '@/shared/date/minguo-calendar-utils';
import i18n from '@/shared/i18n';

export default {
  name: 'bidPrjMonthInfo',
  components: { bidPrjFalInfo },
  props: {
    prjMonthkeyProp: {
      type: Object,
      required: true,
    },
  },
  setup(props, context) {
    const prjMonth: Ref<IBidPrjMonth> = ref(new BidPrjMonth());
    const bidCommonService = new BidCommonService();
    const prjMonthKey: Ref<any> = toRef(props, 'prjMonthkeyProp');
    const bidPrjMonthFormatter = new BidPrjMonthFormatter();
    const bidPrjMonthService = new BidPrjMonthService();
    const bidPrjEquipService = new BidPrjEquipService();
    const bidPrjFalService = new BidPrjFalService();
    enum showStatus {
      monthScheduleData = 1,
      monthPrjFalData = 2,
    }

    const pageShowStatus = ref(showStatus.monthScheduleData);
    let currentMonthFal = reactive({ wkut: '', prjno: '' });

    const equipTable = reactive({
      fields: [
        {
          key: 'tableNo',
          label: i18n.t('label.index'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'eCode',
          label: i18n.t('label.equipName'),
          sortable: false,
          thStyle: 'width:40%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'eCnt',
          label: i18n.t('label.quantity'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'eRemark',
          label: i18n.t('label.description'),
          sortable: false,
          thStyle: 'width:40%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
    });
    const prjFalTable = reactive({
      fields: [
        {
          key: 'tableNo',
          label: i18n.t('label.index'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'mft',
          label: i18n.t('label.mft'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'mfas',
          label: i18n.t('label.mfas'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'mfrt',
          label: i18n.t('label.mfrt'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'okDate',
          label: i18n.t('label.okDate'),
          sortable: false,
          thStyle: 'width:20%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    function updatePrjMonth() {
      bidPrjMonthService
        .getCertainPrjMonthByProjectKey(prjMonthKey.value.wkut, prjMonthKey.value.prjno, prjMonthKey.value.syr, prjMonthKey.value.month)
        .then(result => {
          // console.log('result', result);
          Object.keys(result).forEach(key => (prjMonth.value[key] = result[key]));
          changeFormatValue();
        });

      bidPrjEquipService
        .getMonthlyPrjEquip(prjMonthKey.value.wkut, prjMonthKey.value.prjno, prjMonthKey.value.year, prjMonthKey.value.month)
        .then(result => {
          // console.log('eqp result', result);
          result.forEach(ele => {
            bidCommonService.bidEquipCodeConverter(ele.eCode).then(data => {
              ele.eCode = data;
            });
          });
          equipTable.data = result;
        });

      bidPrjFalService
        .getMonthlyPrjEquip(prjMonthKey.value.wkut, prjMonthKey.value.prjno, prjMonthKey.value.syr, prjMonthKey.value.month)
        .then(result => {
          // console.log('fal result', result);
          result.forEach(ele => {
            bidCommonService.bidProjectFalCodeConverter(ele.mft).then(data => {
              ele.mft = data;
            });
          });
          prjFalTable.data = result;
        });
    }
    let professionalEngineering = ref('');
    let onSiteManagementArr = ref([]);

    function changeFormatValue() {
      professionalEngineering.value = bidPrjMonthFormatter.professionalEngineeringProjectFormat(prjMonth.value);
      onSiteManagementArr.value = bidPrjMonthFormatter.onSiteManagementFormat(prjMonth.value);
    }

    function showMonthPrjFal(data) {
      // console.log(data);
      Object.keys(data).forEach(key => {
        currentMonthFal[key] = data[key];
        pageShowStatus.value = showStatus.monthPrjFalData;
      });
    }

    function changeShowStatus(showStatus) {
      pageShowStatus.value = showStatus;
    }

    watch(
      prjMonthKey.value,
      () => {
        updatePrjMonth();
      },
      { immediate: true }
    );
    function changeToList() {
      context.emit('changeToList');
    }

    return {
      prjMonth,
      prjMonthKey,
      professionalEngineering,
      onSiteManagementArr,
      equipTable,
      prjFalTable,
      showStatus,
      pageShowStatus,
      currentMonthFal,
      i18n,
      changeToList,
      changeShowStatus,
      showMonthPrjFal,
      formatDateTime,
    };
  },
};
</script>

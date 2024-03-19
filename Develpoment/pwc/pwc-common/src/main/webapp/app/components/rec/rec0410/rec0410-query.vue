<template>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-10">
          <b-collapse v-model="stepVisible">
            <b-form-row>
              <i-form-group-check :label="$t('label.County')" :item="$v.county" :labelStar="true">
                <b-form-select
                  v-model.trim="$v.county.$model"
                  :options="queryOptions.county"
                  :state="validateState($v.county)"
                ></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.engCode')" :item="$v.projectCode" :labelStar="true">
                <b-form-select
                  v-model.trim="$v.projectCode.$model"
                  :options="queryOptions.projectCode"
                  :state="validateState($v.projectCode)"
                ></b-form-select>
              </i-form-group-check>
            </b-form-row>

            <div class="button-float-right">
              <b-button-toolbar>
                <i-button class="mt-1 ml-1" type="search" @click="handleQuery"></i-button>
                <b-button class="mt-1 ml-1" variant="info" @click="showSendModel()">匯出(整批清單)</b-button>
                <b-button class="mt-1 ml-1" variant="info" @click="">整批送出</b-button>
              </b-button-toolbar>
            </div>
            <b-modal
              id="send-modal"
              size="lg"
              title="請選擇申請完工展延日期"
              header-bg-variant="secondary"
              header-text-variant="light"
              hide-footer
            >
              <template>
                <div class="container">
                  <div class="card">
                    <div class="card-body col-12">
                      <b-form-row class="mt-3">
                        <i-form-group-check class="col-10" :label="$t('label.batch')" :item="$v.batch">
                          <b-form-select :options="queryOptions.exportCondition"></b-form-select>
                        </i-form-group-check>
                      </b-form-row>
                      <div class="button-float-right">
                        <b-button-toolbar>
                          <i-button class="mt-3" type="file-earmark-arrow-down" @click="$bvModal.hide('send-modal')"></i-button>
                        </b-button-toolbar>
                      </div>
                    </div>
                  </div>
                </div>
              </template>
            </b-modal>
            <b-modal id="view-modal" size="lg" title="申請經費調整" header-bg-variant="secondary" header-text-variant="light" hide-footer>
              <template>
                <div class="container">
                  <div class="card">
                    <div class="card-body col-12">
                      <b-form-row class="align-items-top">
                        <a href="">中央對各級地方政府重大天然災害救災經費處理辦法(第14、16條)</a>
                      </b-form-row>
                      <b-form-row class="mt-3">
                        <i-form-group-check class="col-10" :label="$t('label.completedDeadline')" :item="$v.completedDeadline">
                          <b-form-input value="2021.4.21" readonly> </b-form-input>
                        </i-form-group-check>
                      </b-form-row>
                      <b-form-row>
                        <i-form-group-check class="col-10" :label="$t('label.extensionDeadline')" :item="$v.extensionDeadline">
                          <b-form-input value="2022.10.31" readonly> </b-form-input>
                        </i-form-group-check>
                      </b-form-row>
                      <b-form-row>
                        <i-form-group-check class="col-10" :label="$t('label.handlingStatus')" :item="$v.handlingStatus">
                          <b-form-textarea rows="10" cols="100" maxlength="4000" readonly>
                            決標日期： 契約開工日期： 契約完工日期： 實際開工日期： 預定完工日期： 不計工期天數： 原因分析：
                          </b-form-textarea>
                        </i-form-group-check>
                      </b-form-row>
                      <b-form-row>
                        <i-form-group-check class="col-10" :label="$t('label.extensionCondition')" :item="$v.extensionCondition">
                          <b-form-select :options="queryOptions.extensionCondition" disabled></b-form-select>
                        </i-form-group-check>
                      </b-form-row>
                      <b-form-row class="mt-3">
                        <i-form-group-check class="col-10" :label="$t('label.approvedDeadline')" :item="$v.approvedDeadline">
                          <b-form-input value="2021.4.21" readonly> </b-form-input>
                        </i-form-group-check> </b-form-row
                      ><b-form-row>
                        <i-form-group-check class="col-10" :label="$t('label.opinions')" :item="$v.opinions">
                          <b-form-textarea rows="10" cols="100" maxlength="4000" readonly> </b-form-textarea>
                        </i-form-group-check>
                      </b-form-row>
                      <div class="button-float-right">
                        <b-button-toolbar>
                          <i-button class="mt-3" type="check-circle" @click="toRec0403R1View"></i-button>
                        </b-button-toolbar>
                      </div>
                    </div>
                  </div>
                </div>
              </template>
            </b-modal>
          </b-collapse>
        </div>
      </div>
    </section>

    <!-- i-table -->
    <section v-if="queryStatus">
      <div class="container">
        <b-table class="table-sm" :itemsUndefinedBehavior="'loading'" :items="table.data" :fields="table.fields">
          <template #cell(check)="row">
            <b-form-checkbox v-model="row.item.check" :true-value="true" :false-value="false"></b-form-checkbox>
          </template>
          <template #cell(action)="row">
            <i-button class="mt-1" type="binoculars" @click="showViewModel(row.item)"></i-button>
          </template>
        </b-table>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, ref, watch, onMounted } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import { RecProject } from '@/shared/model/recModel/rec-project.model';
import axios from 'axios';
import { required } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import iButton from '@/shared/buttons/i-button.vue';
import { useGetters } from '@u3u/vue-hooks';
import { commaFormatter } from '@/shared/formatter/common';

export default {
  components: { iButton },
  name: 'rec0410',
  props: {
    isReload: {
      required: false,
    },
  },
  setup() {
    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      county: '',
      projectCode: '',
    };

    const rules = {
      county: {},
      projectCode: {},
    };

    // 下拉選單選項
    const queryOptions = reactive({
      projectCode: [
        { value: '', text: '請選擇' },
        { value: 'A1', text: 'A1-水利工程' },
        { value: 'B1', text: 'B1-觀光工程' },
        { value: 'C1', text: 'C1-道路橋梁工程-公路系統工程' },
        { value: 'C2', text: 'C2-道路橋梁工程-村里聯絡道路工程' },
      ],
      county: [
        { value: '', text: '請選擇' },
        { value: '1', text: '南投縣政府' },
      ],
      exportCondition: [
        { value: 'A1', text: 'A1-2020-02-25 11:47:53' },
        { value: 'C1', text: 'C1-2020-03-31 15:00:36' },
      ],
    });

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const table = reactive({
      fields: [
        {
          key: 'check',
          label: $i18n.t('label.check'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'approveNo',
          label: $i18n.t('label.approveNo'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'projectName',
          label: $i18n.t('label.recEngName'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'approvedFinishDate',
          label: $i18n.t('label.approvedCompletionDate'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'applicationFinishDate',
          label: $i18n.t('label.applicationCompletionDate'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'extendedReason',
          label: $i18n.t('label.extendedReason'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'originalApplicationDate',
          label: $i18n.t('label.originalApplicationDate'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: $i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-right align-middle',
        },
      ],
      data: undefined,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    //顯示查詢條件區
    const stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //依條件查詢復建工程
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          table.data = [];
          table.data = mockdata;

          // axios //TODO:fix post api
          //   .post('/rec-projects/criteria-jpa', form)
          //   .then(({ data }) => {
          //     table.data = data.content.slice(0, data.content.length);
          //     table.totalItems = data.totalElements;
          //   })
          //   .finally()
          //   .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const showSendModel = () => {
      $bvModal.show('send-modal');
    };

    const showViewModel = (recProject: any) => {
      $bvModal.show('view-modal');
    };

    const toRec0403R1View = () => {
      // navigateByNameAndParams('rec0403-R1', {
      // });
      $bvModal.hide('view-modal');
    };

    const mockdata = [
      {
        check: true,
        approveNo: '006',
        projectName: '鹿谷鄉竹林村田頭巷旁野溪災修復建工程',
        approvedFinishDate: '2021/02/01',
        applicationFinishDate: '2022/10/31',
        extendedReason: '(4)因災害擴大或實際需求須變更設計，致增加工期',
        originalApplicationDate: '2021/08/24 17:35:03',
      },
      {
        check: true,
        approveNo: '012',
        projectName: '鹿谷鄉秀峰村崛底橋下游護岸災修復建工程',
        approvedFinishDate: '2021/02/01',
        applicationFinishDate: '2022/10/31',
        extendedReason: '(1)用地使用協調',
        originalApplicationDate: '2021/08/25 16:22:14',
      },
      {
        check: true,
        approveNo: '014',
        projectName: '魚池鄉中明村7鄰排水溝災修復建工程',
        approvedFinishDate: '2021/07/01',
        applicationFinishDate: '2022/12/31',
        extendedReason: '(8)其他特殊因素',
        originalApplicationDate: '2021/08/24 17:22:14',
      },
    ];

    return {
      $v,
      validateState,
      stepVisible,
      queryStatus,
      handleQuery,
      reset,
      queryOptions,
      table,
      showSendModel,
      showViewModel,
      toRec0403R1View,
    };
  },
  filters: {
    commaFormatter: (value: string) => commaFormatter(value),
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

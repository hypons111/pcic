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
              title="請選擇調整經費日期"
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
                      <a href="https://law.moj.gov.tw/LawClass/LawSingle.aspx?pcode=T0020059&flno=15" target="連結目標" title="中央對各級地方政府重大天然災害救災經費處理辦法(第15條)">中央對各級地方政府重大天然災害救災經費處理辦法(第15條)</a>
            <table width="80%">
              <tr>
                <td width="20%">目前核定經費</td>
                <td width="40%"><b-form-input v-model="$v.originalAmount.$model" disabled=ture></b-form-input></td>
                <td width="40%">（千元）</td>
              </tr>
              <tr>
                <td>調整經費</td>
                <td><b-form-input v-model="$v.requestAmount.$model" readonly></b-form-input></td>
                <td>（千元）</td>
              </tr>
            </table>
            <table width="80%">
              <tr>
                <td width="20%">調整經費說明</td>
                <td><b-form-textarea v-model="$v.requestReason.$model" rows="4" maxlength="4000" placeholder="調整經費說明" readonly></b-form-textarea></td>
              </tr>
            </table>
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
  name: 'rec0407Query',
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
      originalAmount:'1452',
      requestAmount:'1500',
      requestReason:'無意見',
      county: '',
      projectCode: '',
    };

    const rules = {
      originalAmount:{},
      requestAmount:{},
      requestReason:{},
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
          key: 'originalAmount',
          label: '原核定經費',
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'requestAmount',
          label: '申請調整經費',
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'requestDate',
          label: '原申請日期',
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
        projectName: '神木村9鄰簡易自來水5月豪雨災修復建工程',
        originalAmount: '',
        requestAmount: '',
        requestDate: '',
      },
      {
        check: true,
        approveNo: '014',
        projectName: '109年5月豪雨-南投市和興坑排水幹線護岸災修復建工程',
        originalAmount: '',
        requestAmount: '',
        requestDate: '',
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

<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視批次匯入紀錄
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check :label="$t('label.adm010.id') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ props.admBatchRecord.id }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm010.batchServiceNo') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ props.admBatchRecord.batchServiceNo }}
                </div>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.adm010.batchServiceName') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ props.admBatchRecord.batchServiceName }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm010.batchServiceCategory') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ props.admBatchRecord.batchServiceCategory }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <hr />

            <b-form-row>
              <i-form-group-check :label="$t('label.adm010.importId') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ props.admBatchRecord.id }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm010.processDate') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.processDate | dateFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm010.processStartTime') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.processStartTime | timeFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm010.processEndTime') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.processEndTime | timeFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <hr />

            <b-form-row>
              <i-form-group-check :label="$t('label.adm010.processFileName') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.processFileName }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm010.originalDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.originalDataCount | commaFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.adm010.processedDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.processedDataCount | commaFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm010.unprocessedDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.unprocessedDataCount | commaFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.adm010.insertSuccessDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.insertSuccessDataCount | commaFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm010.insertFailDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.insertFailDataCount | commaFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.adm010.updateSuccessDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.updateSuccessDataCount | commaFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm010.updateFailDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.updateFailDataCount | commaFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.adm010.deleteSuccessDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.deleteSuccessDataCount | commaFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm010.deleteFailDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.deleteFailDataCount | commaFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.adm010.caseSuccessDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.caseSuccessDataCount | commaFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm010.caseFailDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.caseFailDataCount | commaFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.adm010.interfacingSuccessDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.interfacingSuccessDataCount | commaFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm010.interfacingFailDataCount') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.interfacingFailDataCount | commaFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm010.failedDataMemo') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ form.failedDataMemo }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-button-toolbar class="float-right">
              <i-button class="mr-1" type="arrow-left" @click="toQueryView()"></i-button>
            </b-button-toolbar>
          </b-form>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { validateState } from '@/shared/form';
import { reactive, ref, toRefs, watch } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { commaFormatter, dateTimeFormatter } from '@/shared/formatter/common';
import { handleBack } from '@/router/router';
import { AdmImportRecord } from '@/shared/model/adm-import-record.model';
import { AdmBatchRecord } from '@/shared/model/adm-batch-record.model';
import { formatDate, formatTime } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Adm010EditInfo',
  props: {
    admBatchRecord: {
      type: Object as AdmBatchRecord,
      required: true,
    },
  },
  setup(props, context) {
    // 建立表單物件預設值
    let formDefault: AdmImportRecord = new AdmImportRecord();
    // 建立一份新的表單物件
    const form = reactive(Object.assign({}, formDefault));
    const isReload = ref(false);
    const notificationService: NotificationService = useNotification();

    const options = reactive({
      statusOpts: [
        { value: '', text: '請選擇' },
        { value: 'N', text: '停用' },
        { value: 'Y', text: '啟用' },
      ],
      batchServiceCategoryOpts: [
        { value: '', text: '請選擇' },
        { value: 'bsc01', text: '批次服務類型1' },
        { value: 'bsc02', text: '批次服務類型2' },
        { value: 'bsc03', text: '批次服務類型3' },
      ],
    });

    function initImportRecordInfo() {
      // 查整頁資訊
      axios
        .get(`/adm-import-records/${propsAdmBatchRecord.value.id}`)
        .then(({ data }) => {
          if (data) {
            console.log(data);
            Object.assign(formDefault, data);
            Object.assign(form, formDefault);
            console.log(formDefault);
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 返回列表
    function toQueryView() {
      const param = { isReload: isReload.value };
      handleBack(param);
    }

    const propsAdmBatchRecord = toRefs(props).admBatchRecord;

    watch(
      propsAdmBatchRecord,
      () => {
        initImportRecordInfo();
      },
      { immediate: true }
    );

    return {
      form,
      options,
      toQueryView,
      validateState,
      props,
    };
  },
  filters: {
    commaFilter: (value: any) => commaFormatter(value),
    dateTimeFilter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
    dateFilter: (value: any) => formatDate(value, '/'),
    timeFilter: (value: any) => formatTime(value, 'HH:mm:ss'),
  },
};
</script>

<template>
  <div>
    <nav class="nav pt-4">
      <div class="nav nav-tabs container-fluid px-2" id="nav-tab" role="tablist">
        <b-button
          class="nav-link active"
          id="nav-infrom-tab"
          data-toggle="tab"
          data-target="#nav-infrom"
          type="button"
          role="tab"
          aria-controls="nav-infrom"
          aria-selected="false"
          @click="reset"
        >
          {{ $t('button.index-information') }}</b-button
        >
        <b-button
          class="nav-link"
          id="nav-download-tab"
          data-toggle="tab"
          data-target="#nav-download"
          type="button"
          role="tab"
          aria-controls="nav-download"
          aria-selected="false"
          @click="reset"
          >{{ $t('button.index-doenload') }}</b-button
        >
      </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
      <div class="tab-pane fade show active" id="nav-infrom" role="tabpanel" aria-labelledby="nav-infrom-tab">
        <section class="container pt-2">
          <div class="card-header py-1 px-1">
            <b-form-row>
              <!--公告類別-->
              <b-form-group class="col-sm-3 mb-0" label-cols-md="4" content-cols-md="8" :label="$t('label.kind')" label-align-md="right">
                <b-input-group>
                  <b-form-select v-model="$v.kind.$model" :options="queryOptions.kind"></b-form-select>
                </b-input-group>
              </b-form-group>

              <!--公告日期-->
              <b-form-group
                class="col-sm-4 mb-0"
                label-cols-md="4"
                content-cols-md="8"
                :label="$t('label.releaseDate')"
                label-align-md="right"
              >
                <b-input-group>
                  <i-date-picker v-model="$v.startDate.$model" :disabled-date="startDateRule" :state="$v.startDate"></i-date-picker>
                  <i-date-picker v-model="$v.endDate.$model" :state="$v.endDate" :disabled-date="endDateRule"></i-date-picker>
                </b-input-group>
              </b-form-group>

              <!--關鍵字-->
              <b-form-group
                class="col-sm-3 mb-2 mb-sm-0"
                label-cols-md="3"
                content-cols-md="9"
                :label="$t('label.textKeyWord')"
                label-align-md="right"
              >
                <b-input-group>
                  <b-form-input v-model.trim="$v.subject.$model" placeholder="請輸入關鍵字"></b-form-input>
                </b-input-group>
              </b-form-group>

              <b-button-toolbar class="mx-auto">
                <i-button class="mr-2" type="search" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </b-form-row>
          </div>
        </section>
        <section class="container pt-2">
          <div class="card">
            <div class="card-body">
              <b-collapse id="collapse-5" class="px-1 py-1" v-model="stepVisible.result">
                <i-table
                  stacked="sm"
                  striped
                  class="table-sm table-hover"
                  :itemsUndefinedBehavior="'loading'"
                  :items="table.data"
                  :fields="table.fields"
                  :totalItems="table.totalItems"
                  :is-server-side-paging="true"
                  @changePagination="handlePaginationChanged($event)"
                >
                  <template #cell(subject)="row">
                    <a class="href-style" href="javascript: void(0)" @click="toInfoView(row.item)">{{ row.item.subject }}</a>
                  </template>
                </i-table>
              </b-collapse>
            </div>
          </div>
        </section>
      </div>
      <div class="tab-pane fade" id="nav-download" role="tabpanel" aria-labelledby="nav-download-tab">
        <section class="container pt-2">
          <div class="card">
            <div class="card-header py-1 px-1">
              <b-form-row>
                <!--公告類別-->
                <b-form-group class="col-sm-3 mb-0" label-cols-md="4" content-cols-md="8" :label="$t('label.kind')" label-align-md="right">
                  <b-input-group>
                    <b-form-select v-model="$v.kind.$model" :options="queryOptions.kind"></b-form-select>
                  </b-input-group>
                </b-form-group>

                <!--公告日期-->
                <b-form-group
                  class="col-sm-4 mb-0"
                  label-cols-md="4"
                  content-cols-md="8"
                  :label="$t('label.releaseDate')"
                  label-align-md="right"
                >
                  <b-input-group>
                    <i-date-picker v-model="$v.startDate.$model" :disabled-date="startDateRule" :state="$v.startDate"></i-date-picker>
                    <i-date-picker v-model="$v.endDate.$model" :state="$v.endDate" :disabled-date="endDateRule"></i-date-picker>
                  </b-input-group>
                </b-form-group>

                <!--關鍵字-->
                <b-form-group
                  class="col-sm-3 mb-0"
                  label-cols-md="3"
                  content-cols-md="8"
                  :label="$t('label.textKeyWord')"
                  label-align-md="right"
                >
                  <b-form-input v-model.trim="$v.subject.$model" placeholder="請輸入關鍵字"></b-form-input>
                </b-form-group>
                <b-button-toolbar class="mx-auto">
                  <i-button class="mr-2" type="search" @click="handleDownQuery"></i-button>
                  <i-button type="x-circle" @click="reset"></i-button>
                </b-button-toolbar>
              </b-form-row>
            </div>
          </div>
        </section>
        <section class="container pt-2">
          <div class="card">
            <div class="card-body">
              <b-collapse id="collapse-5" class="px-1 py-1" v-model="stepVisible.result">
                <i-table
                  stacked="sm"
                  striped
                  class="table-sm table-hover"
                  :itemsUndefinedBehavior="'loading'"
                  :items="tableD.data"
                  :fields="tableD.fields"
                  :totalItems="tableD.totalItems"
                  :is-server-side-paging="true"
                  @changePagination="handlePaginationChanged($event)"
                >
                  <template #cell(subject)="row">
                    <a class="href-style" href="javascript: void(0)" @click="toDownView(row.item)">{{ row.item.subject }}</a>
                  </template>
                </i-table>
              </b-collapse>
            </div>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { reactive, ref, onMounted } from '@vue/composition-api';
import { parseRocDate } from '@/shared/date/minguo-calendar-utils';
import { required } from '@/shared/validators';
import { useValidation, validateState } from '@/shared/form';
import { formatDateString, formatDate } from '@/shared/date/minguo-calendar-utils';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { useGetters } from '@u3u/vue-hooks';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { formOptionsFormatter } from '@/shared/formatter/common';
import NotificationService from '@/shared/notification/notification-service';
import i18n from '@/shared/i18n';
import axios from 'axios';

export default {
  name: 'pwb0103',
  setup() {
    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      subject: '', //內文搜尋
      kind: '', // 狀態
      startDate: new Date(new Date().getTime() - 31 * 24 * 60 * 60 * 1000),
      endDate: new Date(),
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      subject: {},
      kind: {},
      startDate: { notNull: required },
      endDate: { notNull: required },
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //Down查詢後顯示的內容
    const table = reactive({
      fields: [
        {
          //公告日期
          key: 'releaseDate',
          label: i18n.t('label.releaseDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => formatDate(new Date(value), '/'),
        },
        {
          //類別
          key: 'kind',
          label: i18n.t('label.kindType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //標題
          key: 'subject',
          label: i18n.t('label.subjectText'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //公告單位
          key: 'releaseUnit',
          label: i18n.t('label.releaseUnit'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    //Down查詢後顯示的內容
    const tableD = reactive({
      fields: [
        {
          //公告日期
          key: 'releaseDate',
          label: i18n.t('label.releaseDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => formatDate(new Date(value), '/'),
        },
        {
          //類別
          key: 'kind',
          label: i18n.t('label.kindType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //標題
          key: 'subject',
          label: i18n.t('label.subjectText'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //公告單位
          key: 'releaseUnit',
          label: i18n.t('label.releaseUnit'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    // 下拉選單選項
    const queryOptions = reactive({
      kind: [],
    });

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      result: true,
    });

    function startDateRule(date: Date) {
      return date > new Date(form.endDate);
    }

    function endDateRule(date: Date) {
      return date < new Date(form.startDate);
    }

    onMounted(() => {
      getDataFromAdmSysCode();
      handleQuery();
      handleDownQuery();
    });

    //公告類別下拉選單
    const getDataFromAdmSysCode = () => {
      const admUrl = '/service/sys-codes/criteria-jpa';
      let admSysSalaryRangeCriteria = {
        moduleType: 'PWC',
        dataType: 'KIND',
      };
      axios.post(admUrl, admSysSalaryRangeCriteria).then(({ data }) => {
        if (data != null && Array.isArray(data.content)) {
          queryOptions.kind.splice(0, queryOptions.kind.length);
          data.content.forEach(item => {
            let selectOption = {
              value: '',
              text: '',
            };
            selectOption.value = item.value;
            selectOption.text = item.codeDescript;
            queryOptions.kind.push(selectOption);
          });
        }
      });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    // 查詢結果及狀態
    // const queryStatus = ref(false);
    const dataPromise = ref(null);

    const notificationService: NotificationService = useNotification();

    // 取得登入帳號(ADM_ACCOUNT.USER_ID)
    const userId = ref(useGetters(['account']).account.value.id);

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          // queryStatus.value = true;
          form.releaseDate = formatDateString(form.startDate); //startDate轉字串 to releaseDate
          form.downDate = formatDateString(form.endDate);
          form.userId = userId.value;
          dataPromise.value = axios
            .post('/service/pwc-information/getInfoByCriteria/', form) //前端分頁(呼叫後端)
            .then(({ data }) => {
              table.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
              table.totalItems = data.length; //前端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const toInfoView = (pwcInformation: PwcInformation) => {
      navigateByNameAndParams('home-information', { pwcInformation: pwcInformation, isNotKeepAlive: true });
    };

    const handleDownQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          tableD.data = undefined;
          form.releaseDate = formatDateString(form.startDate); //startDate轉字串 to releaseDate
          form.downDate = formatDateString(form.endDate);
          dataPromise.value = axios
            .post('/service/pwc-download/getDownByCriteria', form) //前端分頁(呼叫後端)
            .then(({ data }) => {
              tableD.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
              tableD.totalItems = data.length; //前端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const toDownView = (pwcDownload: PwcDownload) => {
      navigateByNameAndParams('home-download', { pwcDownload: pwcDownload, isNotKeepAlive: true });
    };

    return {
      $v,
      reset,
      table,
      validateState,
      queryOptions,
      handleDownQuery,
      handleQuery,
      startDateRule,
      endDateRule,
      getDataFromAdmSysCode,
      stepVisible,
      handlePaginationChanged,
      toInfoView,
      toDownView,
      tableD,
    };
  },
};
</script>

<style scoped>
.widthClassForTitle {
  width: 14%;
  word-break: break-all;
}
.widthClassForDescription {
  width: 53%;
  word-break: break-all;
}
.widthClassForCreateUserAndUpdateDate {
  width: 10%;
  word-break: break-all;
}
.widthClassForOperation {
  width: 13%;
  word-break: break-all;
}
.nav-tabs .nav-link {
  width: 50%;
  font-size: 19px;
  line-height: 28px;
  opacity: 0.45;
  color: #000;
  background: #fff;
  border: 0;
  padding: 0.6rem;
  letter-spacing: 0.125rem;
}

.filter {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 1rem 1.5rem;
  background: rgba(0, 0, 0, 0.05);
  border-radius: 0.25rem;
  margin-bottom: 1.25rem;
}
.filter .input-group {
  margin-right: 1rem;
  display: flex;
  align-items: center;
}
.input-group label {
  font-size: 1.15rem;
  color: #333;
  letter-spacing: 0.15rem;
  white-space: nowrap;
  margin-right: 0.5rem;
}
.input-theme {
  font-size: 1rem;
  line-height: 1.5rem;
  height: 40px;
  min-width: 120px;
  border: 1px solid #bdbdbd;
  border-radius: 0.25rem;
  padding: 4px;
  margin: 4px;
  flex: auto;
}
.href-style {
  font-weight: normal;
}
.table-style-td {
  max-width: 700px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.table-style-td:hover {
  overflow: auto;
  white-space: pre-wrap;
}
.card {
  background: rgba(0, 0, 0, 0.03);
}
.btn-secondary:not(:disabled):not(.disabled).active {
  background: #1aa4b7;
}
</style>

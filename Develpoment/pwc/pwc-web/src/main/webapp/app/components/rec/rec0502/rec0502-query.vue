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
              <i-form-group-check :label="$t('label.typhoonName') + ':'" :item="$v.typhoonName" :labelStar="true">
                <b-form-input v-model="$v.typhoonName.$model" :state="validateState($v.typhoonName)" maxlength="30"> </b-form-input>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.applyStartTime') + ':'"
                :dual1="$v.applyStartTimeFrom"
                :dual2="$v.applyStartTimeTo"
                :labelStar="true"
              >
                <b-input-group>
                  <i-date-picker v-model="$v.applyStartTimeFrom.$model" :state="$v.applyStartTimeFrom"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.applyStartTimeTo.$model" :state="$v.applyStartTimeTo"></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.isLocalOnly') + ':'" :item="$v.isLocalOnly" :label-cols="5" :content-cols="7">
                <b-form-select v-model="$v.isLocalOnly.$model" :state="validateState($v.isLocalOnly)" :options="option"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.applyEndTime') + ':'" :dual1="$v.applyEndTimeFrom" :dual2="$v.applyEndTimeTo">
                <b-input-group>
                  <i-date-picker v-model="$v.applyEndTimeFrom.$model" :state="$v.applyEndTimeFrom"></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker v-model="$v.applyEndTimeTo.$model" :state="$v.applyEndTimeTo"></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="$t('label.isEditSubmitData') + ':'" :item="$v.isEditSubmitData" :label-cols="5" :content-cols="7">
                <b-form-select
                  v-model="$v.isEditSubmitData.$model"
                  :state="validateState($v.isEditSubmitData)"
                  :options="option"
                ></b-form-select>
              </i-form-group-check>
              <i-form-group-check
                :label="$t('label.isDenyBudgetChange') + ':'"
                :item="$v.isDenyBudgetChange"
                :label-cols="6"
                :content-cols="6"
              >
                <b-form-select
                  v-model="$v.isDenyBudgetChange.$model"
                  :state="validateState($v.isDenyBudgetChange)"
                  :options="option"
                ></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <div class="button-float-right">
              <b-button-toolbar>
                <i-button class="mt-1 ml-1" type="search" @click="handleQuery"></i-button>
                <i-button class="mt-1 ml-1" type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>
    <!-- 新增按鈕 -->
    <section>
      <div class="container mt-1 mb-1">
        <i-button type="file-earmark-plus" @click="toCreateView"></i-button>
      </div>
    </section>
    <!-- i-table -->
    <section>
      <div class="container" v-if="queryStatus">
        <i-table
          class="table-sm"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :fields="table.fields"
          :totalItems="table.totalItems"
          :is-server-side-paging="true"
          @changePagination="handlePaginationChanged($event)"
        >
          <template #cell(action)="row">
            <i-button class="mt-1" type="pencil-square" @click="toEditView(row.item)"></i-button>
            <i-button class="mt-1" type="trash" @click="deleteTyphoon(row.item.typhoonNo)"></i-button>
          </template>
          <!--提報只到地方層級checkbox-->
          <template #cell(isLocalOnly)="row">
            <b-form-checkbox disabled v-model="row.item.isLocalOnly" :true-value="true" :false-value="false"></b-form-checkbox>
          </template>
          <!--可修改提報資料checkbox-->
          <template #cell(isEditSubmitData)="row">
            <b-form-checkbox disabled v-model="row.item.isEditSubmitData" :true-value="true" :false-value="false"></b-form-checkbox>
          </template>
          <!--不可提報經費調整申請checkbox-->
          <template #cell(isDenyBudgetChange)="row">
            <b-form-checkbox disabled v-model="row.item.isDenyBudgetChange" :true-value="true" :false-value="false"></b-form-checkbox>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, ref } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import { RecTyphoon } from '@/shared/model/recModel/rec-typhoon.model';
import axios from 'axios';
import { required } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import iButton from '@/shared/buttons/i-button.vue';

export default {
  components: { iButton },
  name: 'rec0502Query',
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
      typhoonName: '',
      //開放申請日期起
      applyStartTimeFrom: undefined,
      //開放申請日期迄
      applyStartTimeTo: undefined,
      //申請截止日期起
      applyEndTimeFrom: undefined,
      //申請截止日期迄
      applyEndTimeTo: undefined,
      isLocalOnly: '',
      isEditSubmitData: '',
      isDenyBudgetChange: '',
    };

    const rules = {
      typhoonName: { notNull: required },
      applyStartTimeFrom: { notNull: required },
      applyStartTimeTo: { notNull: required },
      applyEndTimeFrom: {},
      applyEndTimeTo: {},
      isLocalOnly: {},
      isEditSubmitData: {},
      isDenyBudgetChange: {},
    };

    const option = ref([
      { value: '', text: '全部' },
      { value: true, text: '是' },
      { value: false, text: '否' },
    ]);

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const table = reactive({
      fields: [
        {
          key: 'typhoonName',
          label: $i18n.t('label.typhoonName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'applyStartTime',
          label: $i18n.t('label.applyStartTime'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => formatDate(new Date(value), '/'),
        },
        {
          key: 'applyEndTime',
          label: $i18n.t('label.applyEndTime'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => formatDate(new Date(value), '/'),
        },
        {
          key: 'finishDate1000',
          label: $i18n.t('label.finishDate1000'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => formatDate(new Date(value), '/'),
        },
        {
          key: 'finishDate5000',
          label: $i18n.t('label.finishDate5000'),
          sortable: true,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => formatDate(new Date(value), '/'),
        },
        {
          key: 'isLocalOnly',
          label: $i18n.t('label.isLocalOnly'),
          sortable: true,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'isEditSubmitData',
          label: $i18n.t('label.isEditSubmitData'),
          sortable: true,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'isDenyBudgetChange',
          label: $i18n.t('label.isDenyBudgetChange'),
          sortable: true,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'centerApprovedProgress',
          label: $i18n.t('label.centerApprovedProgress'),
          sortable: true,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: $i18n.t('label.action'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    //顯示查詢條件區
    let stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //轉到新增頁面
    const toCreateView = () => {
      navigateByNameAndParams('rec0502-edit-info', { formStatusForward: 'create' });
    };

    //轉到編輯頁面
    const toEditView = (recTyphoon: RecTyphoon) => {
      navigateByNameAndParams('rec0502-edit-info', { formStatusForward: 'modify', recTyphoon: recTyphoon, isNotKeepAlive: true });
    };

    //依條件查詢颱風
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/rec-typhoons/criteria-jpa', form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    //刪除颱風
    const deleteTyphoon = (deleteId: string) => {
      $bvModal.msgBoxConfirm('刪除風災後將一併刪除相關案件及變更資料，確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          let url: string;
          url = `/rec-typhoons/` + deleteId;
          axios
            .delete(url)
            .then($bvModal.msgBoxOk('資料刪除成功'))
            .finally(handleQuery())
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      $v,
      stepVisible,
      validateState,
      checkValidity,
      queryStatus,
      handleQuery,
      reset,
      handlePaginationChanged,
      option,
      table,
      toEditView,
      toCreateView,
      deleteTyphoon,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

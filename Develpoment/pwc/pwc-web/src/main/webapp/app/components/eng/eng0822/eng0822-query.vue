<template>
  <div>
    <section class="container mt-2"> <!-- v-if="queryStatus"--> <!--title="工程技術顧問商業同業公會通訊錄"-->
      <i-table
        small
        title="工程技術顧問商業同業公會通訊錄"
        ref="iTable"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields_backend"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >
        <template v-slot:cell(tel1)="slot">
          電話(一)：{{slot.item.tel1}}<br/>
          電話(二)：{{slot.item.tel2}}<br/>
          傳真：{{slot.item.fax}}<br/>
          信箱：{{slot.item.mail1}}<br/>
        </template>
        <!-- 加上地址元件 -->
        <template #cell(address1Other)="row">
          <b-form-row >
            <i-form-group-check class="col-12" content-cols="10" :item="[$v.town, $v.other, $v.city]">
              <i-address-picker
                :is-text="true"
                :zip="row.item.address1Zip"
                :city="row.item.address1City"
                :town="row.item.address1Town"
                :other="row.item.address1Other"
              >

              </i-address-picker>
            </i-form-group-check>
          </b-form-row>
        </template>
        
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import i18n from '@/shared/i18n';
import NotificationService from '@/shared/notification/notification-service';
import EngReviewLogService from '@/components/eng/eng-review-log/eng-review-log.service';
import { onMounted, onActivated, watch, reactive, Ref, ref, toRef, inject } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import { formOptionsFormatter } from '@/shared/formatter/common';

export default {
  name: 'Eng0822Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup() {
    // const engReviewLogService = new EngReviewLogService();
    const iTable = ref(null);

    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    // 前台直接顯示
    onMounted(() => {
        handleQuery();
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      address1Zip: '',
      address1City: '',
      address1Town: '',
      address1Other: ''
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);  // 雖無驗證但也不可刪

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
      fields_backend: [
        {
          // 公會名稱
          key: 'chName',  // 從後端撈回資料內的key
          label: i18n.t('label.guildName'),  // 表格欄位標題
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 理事長
          key: 'masterName',
          label: i18n.t('label.chairman'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 通訊資料
          key: 'tel1',
          label: i18n.t('label.connection'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          // formatter: (value: string) => formOptionsFormatter(formOptions.connections, value),
        },
        {
          // 地址
          key: 'address1Other',
          label: i18n.t('label.address1'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const handleQuery = () => {
      table.data = undefined;
      queryStatus.value = true;
      // 傳form才能設定回傳的分頁一次顯示幾筆資料
      axios.post('/service/eng-eng0822r1', form).then(({ data }) => {
        table.data = data.content.slice(0, data.content.length);
        table.totalItems = data.totalElements; //後端分頁
        // table.data.forEach(function(item, i){
        //   if(item.address1Other) { // 把地址用@分割成陣列再串回純文字串
        //     const arr = item.address1Other.split('@');
        //     item.address1Other = arr[0] + arr[1] + arr[2];
        //   }
        // })
        
      }).catch(notificationErrorHandler(notificationService));
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      iTable,
      validateState,
      $v,
      stepVisible,
      queryStatus,
      table,
      reset,
      roleRef,
      FormStatusEnum,
      RoleEnum,
      ref,
      formOptionsFormatter,
      handlePaginationChanged,
    };
  },
};
</script>

<style></style>

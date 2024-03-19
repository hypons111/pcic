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
              <i-form-group-check :labelStar="true" label="計畫名稱:" :item="$v.licProjectBasicId">
                <b-form-select v-model.trim="$v.licProjectBasicId.$model" :options="queryOptions.licProjectBasicName" :state="validateState($v.licProjectBasicId)"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="會議日期:">
                <i-date-picker v-model.trim="$v.meetingDate.$model"></i-date-picker>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="關鍵字:">
                <b-form-input v-model.trim="$v.mmKeyword.$model"></b-form-input>
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
            <i-button class="mt-1" type="trash" @click="deleteProject(row.item.licPlatformMeetingId)"></i-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, Ref, ref, watch, onMounted } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import { required } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import iButton from '@/shared/buttons/i-button.vue';

export default {
  components: { iButton },
  name: 'lic0704Query',
  props: {
    isReload: {
      required: false,
    },
  },
  setup() {
    onActivated(() => {
      // if (isReloadProp.value) {
      //   handleQuery();
      // }
    });

    onMounted(() => {
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      fileNameOri: '',
      meetingDate: undefined,
      mmKeyword:'',
      licProjectBasicId: '',
    };

    const rules = {
      fileNameOri: {},
      meetingDate: {},
      mmKeyword:{},
      licProjectBasicId: {notNull: required},
    };

    // 下拉選單選項
    const queryOptions = reactive({
      licProjectBasicName: [
        { value: '', text: '全部' },
        { value: 'L10801', text: 'L10801：天然氣事業部永安廠增建儲糟投資計畫' },
        { value: 'L10802', text: 'L10802：台9線花東縱谷公路安全景觀大道計畫(臺東段)' },
        { value: 'L10803', text: 'L10803：臺北都會區大眾捷運系統三鶯線建設計畫' },
      ],
    });

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const table = reactive({
      fields: [
        {
          key: 'licProjectBasicId',
          label: '計畫名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 25%',
        },
        {
          key: 'fileNameOri',
          label: '會議記錄',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 35%',
        },
        {
          key: 'meetingDate',
          label: '會議日期',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%',
        },
        {
          key: 'mmKeyword',
          label: '關鍵字',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 15%',
        },
        {
          key: 'action',
          label: '操作',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width:10%',
        },
      ],
      data: undefined,
      totalItems: 3,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    //顯示查詢條件區
    let stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //轉到新增頁面
    const toCreateView = () => {
      navigateByNameAndParams('lic0704-edit-info', { formStatusForward: 'create' , isNotKeepAlive: true  });
    };

    //轉到編輯頁面
    const toEditView = (licWorkshopMeeting: any) => {
      navigateByNameAndParams('lic0704-edit-info', { formStatusForward: 'modify', licWorkshopMeeting: licWorkshopMeeting, isNotKeepAlive: true });
    };

    //依條件查詢推動平臺會議記錄
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
      //   if (isValid) {
      //     //重置條件
      //     table.data = undefined;
      //     axios
      //       .post('/rec-projects/criteria-jpa', form)
      //       .then(({ data }) => {
      //         table.data = data.content.slice(0, data.content.length);
      //         table.totalItems = data.totalElements;
      //       })
      //       .finally()
      //       .catch(notificationErrorHandler(notificationService));
      //   }
      // });
      queryStatus.value=true;
      table.data = [
        {
          licProjectBasicId : '(L10801)天然氣事業部永安廠增建儲糟投資計畫',
          fileNameOri : '1040820第1次工作小組會議記錄.pdf',
          meetingDate : '2015-08-12',
          mmKeyword : '第1次工作小組會議記錄',
        },
        {
          licProjectBasicId : '(L10802)台9線花東縱谷公路安全景觀大道計畫(臺東段)',
          fileNameOri : '109年11月23日證照許可作業第1次工作小組會議記錄.pdf',
          meetingDate : '2020-11-23',
          mmKeyword : '證照許可作業第1次工作小組會議記錄',
        },
        {
          licProjectBasicId : '(L10803)臺北都會區大眾捷運系統三鶯線建設計畫',
          fileNameOri : 'D1041000426附件_證照許可工作小組第2次會議記錄.pdf',
          meetingDate : '2020-11-23',
          mmKeyword : '工作小組第2次會議記錄',
        },
      ]
    })};

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const meetingFile: Ref<File> = ref(null);
    const picPreview = ref(null);

    function meetingSelected(e) {
      if (e.target.files.length !== 0) {
        const fileReader = new FileReader();
        fileReader.onload = e => {
          picPreview.value = e.target.result;
        };
        fileReader.readAsDataURL(e.target.files[0]);
      } else {
        picPreview.value = null;
      }
    }

    function uploadMeetingFile() {
      const param = new FormData();
      param.append('file', meetingFile.value);
      axios
        .put('/rec-area/upload', param)
        .then(() => {
          notificationService.success(`上傳成功`);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    return {
      $v,
      stepVisible,
      queryStatus,
      validateState,
      checkValidity,
      handleQuery,
      reset,
      handlePaginationChanged,
      queryOptions,
      table,
      toCreateView,
      toEditView,
      meetingFile,
      picPreview,
      meetingSelected,
      uploadMeetingFile,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

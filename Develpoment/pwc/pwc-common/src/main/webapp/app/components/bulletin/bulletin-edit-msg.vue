<template>
  <div>
    <div class="tab-content" id="nav-tabContent">
      <div class="tab-pane fade show active" id="nav-inform" role="tabpanel" aria-labelledby="nav-inform-tab">
        <section class="container pt-2">
          <div class="card-header py-1 px-1">
            <b-form-row>
              <!--維護類型-->
              <b-form-group class="col-sm-4" label-cols-md="4" content-cols-md="8" :label="$t('label.mainType') + ':'" label-align-md="right">
                <b-form-radio-group v-model="$v.type.$model" :options="queryOptions.type" button-variant="outline-info" buttons />
              </b-form-group>
              <!--公告類別-->
              <b-form-group
                class="col-sm-4 mb-1 d-flex justify-content-center"
                label-cols-md="5"
                content-cols-md="7"
                :label="$t('label.kind') + ':'"
                label-align-md="right"
              >
                <b-input-group>
                  <b-form-select v-model="$v.kind.$model" :options="queryOptions.kind"></b-form-select>
                </b-input-group>
              </b-form-group>
              <!--公告類別-->
              <b-form-group class="col-sm-4 mb-1 mb-sm-0" label-cols-md="3" content-cols-md="8" :label="$t('label.textKeyWord') + ':'" label-align-md="right">
                <b-input-group>
                  <b-form-input v-model.trim="$v.subject.$model" placeholder="請輸入關鍵字"></b-form-input>
                </b-input-group>
              </b-form-group>
            </b-form-row>
            <b-form-row>
              <b-form-group
                class="col-sm-5 mb-1 d-flex justify-content-center"
                label-cols-md="3"
                content-cols-md="8"
                :label="$t('label.releaseDate') + ':'"
                label-align-md="right"
              >
                <b-input-group>
                  <i-date-picker v-model="$v.startDate.$model" :state="$v.startDate"></i-date-picker>
                  <i-date-picker v-model="$v.endDate.$model" :state="$v.endDate"></i-date-picker>
                </b-input-group>
                <dual-form-invalid-feedback
                  :dual-invalid-feedback="[$v.startDate.$errors, $v.endDate.$errors]"
                ></dual-form-invalid-feedback>
              </b-form-group>
              <b-button-toolbar class="mx-auto">
                <i-button class="mr-2" type="search" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </b-form-row>
          </div>
        </section>
        <section class="container mt-3">
          <b-button size="sm" variant="outline-secondary" @click="toCreateView()">
            <font-awesome-icon :icon="['fas', 'plus']" />{{ $t('button.add') }}
          </b-button>
        </section>
        <section class="container pt-2" v-if="queryStatus">
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
                  <template #cell(action)="row">
                    <b-button size="sm" variant="outline-secondary" @click="toOverView(row.item)">
                      <font-awesome-icon :icon="['fas', 'binoculars']" />檢視
                    </b-button>
                    <b-button
                      size="sm"
                      :disabled="row.item.releaseUnit !== userIdentity.unit.name"
                      variant="outline-secondary"
                      @click="toModifyView(row.item)"
                    >
                      <font-awesome-icon :icon="['fas', 'wrench']" />編輯
                    </b-button>
                    <b-button
                      size="sm"
                      :disabled="row.item.releaseUnit !== userIdentity.unit.name"
                      variant="outline-secondary"
                      @click="handleDelete(row.item.id)"
                    >
                      <font-awesome-icon :icon="['fas', 'trash']" />{{ $t('button.delete') }}
                    </b-button>
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
import { useValidation } from '@/shared/form';
import { computed, onActivated, reactive, ref, onMounted } from '@vue/composition-api';
import { guildTypeFormatter } from '@/shared/formatter/common';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { CmsComp } from '@/shared/model/cms-comp.model';
import { Pagination } from '@/shared/model/pagination.model';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { parseRocDate, formatDateString, formatDate } from '@/shared/date/minguo-calendar-utils';
import { required } from '@/shared/validators';
import { navigateByNameAndParams } from '@/router/router';
import DualFormInvalidFeedback from '@/shared/form/dual-form-invalid-feedback.vue';
import { handleBack } from '@/router/router';
import { accountStore } from '@/shared/config/store/account-store';
import { kindFormatter } from '@/shared/formatter/common';

export default {
  name: 'BulletinEditMsg',
  components: {
    DualFormInvalidFeedback,
  },
  props: {
    isReload: {
      required: false,
    },
  },
  setup(props, context) {
    onMounted(() => {
      //onMounted : 組件第一次載入時調用
      getDataFromAdmSysCode();
    });

    onActivated(() => {
      //onActivated : 被 keep-alive 緩存的組件呼叫時調用
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      type: 'INFO', //類型
      subject: '', //內文搜尋
      kind: '', // 狀態
      startDate: parseRocDate('111/02/01', '/'),
      endDate: parseRocDate('111/03/31', '/'),
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      type: {},
      subject: {},
      kind: {},
      startDate: { notNull: required },
      endDate: { notNull: required },
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      result: true,
    });

    // 下拉選單選項
    const queryOptions = reactive({
      kind: [],
      type: [
        { value: 'INFO', text: '公告專區' },
        { value: 'DOWNLOAD', text: '下載專區' },
      ],
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    //查詢後顯示的內容
    const table = reactive({
      fields: [
        {
          key: 'kind',
          label: i18n.t('label.kind'),//公告類別
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => kindFormatter(value),
        },
        {
          key: 'releaseDate',
          label: i18n.t('label.releaseDate'), //公告日期
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: value => formatDate(new Date(value), '/'),
        },
        {
          key: 'enable',
          label: i18n.t('label.enable'), //啟用
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'subject',
          label: i18n.t('label.mainSubject'), //主旨
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'updateUser',
          label: i18n.t('label.uploader'), //上傳人員
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'action',
          label: '',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          form.releaseDate = formatDateString(form.startDate); //startDate轉字串 to releaseDate
          form.downDate = formatDateString(form.endDate);
          //加入判斷 判斷是info或down
          const urlAddr = '/service/pwc-information/criteria';
          if (form.type === 'DOWNLOAD') {
            urlAddr = '/service/pwc-download/criteria';
          }
          dataPromise.value = axios
            .post(urlAddr, form) //前端分頁(呼叫後端)
            .then(({ data }) => {
              table.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
              table.totalItems = data.length; //前端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const getDataFromAdmSysCode = () => {
      const admUrl = '/adm-sys-codes/criteria-jpa';
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

    const handleDelete = (deleteId: deleteId) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否確認刪除？').then((isOK: boolean) => {
            if (isOK) {
              putForm(deleteId);
            }
          });
        }
      });
    };

    const putForm = (deleteId: deleteId) => {
      let url: string;
      form.id = deleteId;
      //加入判斷 判斷是info或down
      url = '/service/info-delete';
      if (form.type === 'DOWNLOAD') {
        url = '/service/download-delete';
      }
      dataPromise.value = axios
        .delete(url, { data: form })
        .then((response: AxiosResponse) => {
          $bvModal.msgBoxConfirm('成功刪除資料').then((isOK: boolean) => {
            if (isOK) {
              handleQuery();
            }
          });
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    //新增
    const toCreateView = () => {
      const itemDetails = {};
      itemDetails.type = form.type;
      itemDetails.webStatus = 'create';
      navigateByNameAndParams('bulletin-edit-msg-detail', { editMsgDetail: itemDetails, isNotKeepAlive: true });
    };

    //預覽
    const toOverView = (itemDetail: itemDetail) => {
      itemDetail.type = form.type;
      itemDetail.webStatus = 'infoView';
      navigateByNameAndParams('bulletin-edit-msg-detail', { editMsgDetail: itemDetail, isNotKeepAlive: true });
    };

    //取得使用者資訊
    const userIdentity = accountStore.state.userIdentity;

    //編輯
    const toModifyView = (itemDetail: itemDetail) => {
      itemDetail.type = form.type;
      itemDetail.webStatus = 'modify';
      navigateByNameAndParams('bulletin-edit-msg-detail', { editMsgDetail: itemDetail, isNotKeepAlive: true });
    };

    return {
      $v,
      stepVisible,
      queryOptions,
      queryStatus,
      dataPromise,
      table,
      reset,
      handleQuery,
      handlePaginationChanged,
      handleDelete,
      toModifyView,
      toCreateView,
      toOverView,
      userIdentity,
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
</style>

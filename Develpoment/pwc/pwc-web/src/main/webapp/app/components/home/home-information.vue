<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0" v-if="formStatusRef !== 'view'">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            {{ $t('label.informationItem') }}
          </h5>
        </div>
        <div class="card-body">
          <b-form-row class="align-items-center mt-2 border border-secondary table-secondary">
            <b-col class="d-none d-sm-block" cols="2">{{ $v.releaseDate.$model }}</b-col>
            <b-col class="d-none d-sm-block" cols="2">{{formOptionsFormatter(formOptions.kind, $v.kind.$model) }}</b-col>
            <b-col cols="4">{{ $v.subject.$model }}</b-col>
            <b-col class="d-none d-sm-block" cols="2">{{ $v.releaseUnit.$model }}</b-col>
          </b-form-row>
          <b-form-row class="align-items-center mt-2 border border-secondary table-secondary">
            <b-col cols="11">{{ $v.text.$model }}</b-col>
          </b-form-row>
          <b-form-row class="align-items-center mt-2 border border-secondary table-secondary">
            <span>{{ $t('label.attributesFile') }}</span>
            <section class="container pt-4">
              <b-table-simple responsive>
                <b-tbody>
                  <b-tr v-for="i of attachmentTable.data" :key="i.proInformationAppendixId">
                    <b-td>
                      <div>
                        {{ i.text }}
                        <b-button class="btn btn-sm" @click="downloadLocalFileByInfo(i.appendix)">
                          <font-awesome-icon icon="download"></font-awesome-icon>{{ $t('button.file-down') }}
                        </b-button>
                      </div>
                    </b-td>
                  </b-tr>
                </b-tbody>
              </b-table-simple>
            </section>
          </b-form-row>
          <b-form-row class="align-items-center mt-2 border border-secondary table-secondary">
            <span>{{ $t('label.attributesLink') }}</span>
            <section class="container pt-4">
              <b-table-simple responsive>
                <b-tbody>
                  <b-tr v-for="i of linkTable.data" :key="i.proInformationAppendixId">
                    <b-td>
                      <b-link :href="i.appendix" target="_blank">{{ i.text }}</b-link>
                    </b-td>
                  </b-tr>
                </b-tbody>
              </b-table-simple>
            </section>
          </b-form-row>
          <b-form-row class="align-items-center mt-2 border border-secondary table-secondary">
            <b-col class="col-12 col-sm-12 pt-2">{{ $t('label.releaseUnitDetail') }} : {{ $v.releaseUnit.$model }}</b-col>
            <b-col class="col-12 col-sm-12 pt-2" v-show="$v.contactPerson.$model !== ''"
              >{{ $t('label.contactPerson') }} : {{ $v.contactPerson.$model }}</b-col
            >
            <b-col class="col-12 col-sm-12 pt-2" v-show="$v.contactInformationTel.$model !== ''"
              >{{ $t('label.contactInformationTel') }} : {{ $v.contactInformationTel.$model }}</b-col
            >
            <b-col class="col-12 col-sm-12 pt-2" v-show="$v.contactInformationEmail.$model !== ''"
              >{{ $t('label.contactInformationEmail') }} : {{ $v.contactInformationEmail.$model }}</b-col
            >
          </b-form-row>
          <b-form-row class="justify-content-center mt-2" v-if="formStatusRef !== 'view'">
            <i-button type="arrow-bar-left" @click="toQueryView"></i-button>
          </b-form-row>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required } from '@/shared/validators';
import { guildTypeFormatter, statusFormatter, downloadFile } from '@/shared/formatter/common';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler, notificationResponseHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { parseRocDate, formatDateString, formatDate } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';

export default {
  name: 'HomeInformation',
  props: {
    pwcInformation: {
      type: Object,
      required: false,
    },
    bulletinEditInfoView: {
      type: Object,
      required: false,
      default: null,
    },
  },
  setup(props, context) {
    const pwcInformationProp = toRefs(props).pwcInformation;

    //for公告訊息維護功能(預覽)
    const bulletinEditInfoViewProp = toRefs(props).bulletinEditInfoView;
    //for公告訊息維護功能(預覽)
    const formStatusRef = setFormStatusRef(bulletinEditInfoViewProp);

    function setFormStatusRef(data: bulletinEditInfoViewProp) {
      if (data.value !== null) {
        formStatusRef = data.value.status;
      } else {
        formStatusRef = null;
      }
      return formStatusRef;
    }

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      proInformationId: '', //序號
      releaseDate: '', //上架日期
      kind: '', //類別
      subject: '', //主旨
      releaseUnit: '', //發布單位
      contactPerson: '', //聯絡人
      contactInformationTel: '', //聯絡資訊TEL
      contactInformationEmail: '', //聯絡資訊EMAIL
      text: '', //內文
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      proInformationId: {},
      releaseDate: {},
      kind: {},
      subject: {},
      releaseUnit: {},
      contactPerson: {},
      contactInformationTel: {},
      contactInformationEmail: {},
      text: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);
    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

     const formOptions = reactive({
      kind: [],
    });

    const toQueryView = () => {
      const param = { isReload: isReload.value };
      handleBack(param);
    };

    const createDefaultValue = (data: pwcInformation) => {
      Object.assign(formDefault, data);
      reset();
    };

    const linkTable = reactive({
      fields: [
        {
          key: 'subject',
          label: '內文',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const linkInfoQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          linkTable.data = undefined;
          dataPromise.value = axios
            .post('/service/info-appendix/link', form) //前端分頁(呼叫後端)
            .then(({ data }) => {
              linkTable.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
              linkTable.totalItems = data.length; //前端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const attachmentTable = reactive({
      fields: [
        {
          key: 'subject',
          label: '內文',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const attachmentQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          attachmentTable.data = undefined;
          dataPromise.value = axios
            .post('/service/info-appendix/attachment', form) //前端分頁(呼叫後端)
            .then(({ data }) => {
              attachmentTable.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
              attachmentTable.totalItems = data.length; //前端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const downloadLocalFileByInfo = (id: id) => {
      axios
        .get('/service/adm-files/download/' + id, { responseType: 'blob' })
        .then((response: AxiosResponse) => downloadFile(response))
        .catch(notificationErrorHandler(notificationService));
    };

    //取得KIND下拉式選單資料
    const getAdmKindSysCode = () => {
      const admUrl = '/service/sys-codes/criteria-jpa';
      let admKind = {
        moduleType: 'PWC',
        dataType: 'KIND',
      };
      axios.post(admUrl, admKind).then(({ data }) => {
        if (data != null && Array.isArray(data.content)) {
          formOptions.kind.splice(0, formOptions.kind.length);
          data.content.forEach(item => {
            let selectOption = {
              value: '',
              text: '',
            };
            selectOption.value = item.value;
            selectOption.text = item.codeDescript;
            formOptions.kind.push(selectOption);
          });
        }
      });
    };

    watch(
      pwcInformationProp,
      () => {
        if (pwcInformationProp.value) {
          createDefaultValue(pwcInformationProp.value);
          linkInfoQuery();
          attachmentQuery();
          getAdmKindSysCode();
        }
      },
      { immediate: true }
    );

    watch(
      bulletinEditInfoViewProp,
      () => {
        if (bulletinEditInfoViewProp.value) {
          createDefaultValue(bulletinEditInfoViewProp.value);
          linkInfoQuery();
          attachmentQuery();
          getAdmKindSysCode();
          // formDefault.releaseDate = formatDate(new Date(formDefault.releaseDate), '/');
        }
      },
      { immediate: true }
    );

    return {
      linkInfoQuery,
      linkTable,
      attachmentQuery,
      attachmentTable,
      $v,
      validateState,
      toQueryView,
      downloadLocalFileByInfo,
      formStatusRef,
      formOptionsFormatter,
      formOptions,
    };
  },
  filters: {
    status: (value: string) => statusFormatter(value),
    guildType: (value: string) => guildTypeFormatter(value),
  },
};
</script>
<style>
.red-text {
  color: red;
  font-weight: bold;
}
</style>

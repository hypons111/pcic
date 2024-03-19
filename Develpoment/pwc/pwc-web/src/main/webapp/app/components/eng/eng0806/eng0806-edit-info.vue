<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            
          </h5>
        </div>
        <div class="card-body">
          <!-- <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT"> -->
          <b-form-row>
            <!-- 機構名稱 -->
            <i-form-group-check :label="$t('label.compName') + '：'" :item="$v.compName">
                <b-form-input v-model="$v.compName.$model" trim></b-form-input>
              </i-form-group-check>
            <!-- 統一編號 -->
              <i-form-group-check :label="$t('label.compIdno') + '：'" :item="$v.compIdno">
                <b-form-input v-model="$v.compIdno.$model" trim></b-form-input>
              </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <b-col offset="8">
              <i-button type="filter_button" @click="handleQueryBtn"></i-button>
              <i-button type="arrow-left" @click="toQueryView" />
            </b-col>
          </b-form-row>

        </div>
      </div>
    </section>
    <section class="container mt-2" v-show="queryStatus">
      <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged" 
      >
        <template #cell(addr1) = "{ item }">
          {{ item.compAddrCity }}
          {{ item.compAddrTown }}
          {{ item.compAddrOther }}
        </template>
        
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import i18n from '@/shared/i18n';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
// import EngEngrSubjectpointService from './eng-engr-subjectpoint.service';
import { useBvModal } from '@/shared/modal';
import { useNotification } from '@/shared/notification';
import { useValidation, validateState } from '@/shared/form';
import { handleBack } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { reactive, ref, toRefs, onMounted, defineComponent, unref, onActivated, toRef } from '@vue/composition-api';
import { required, maxLength, nationalId, between, queryEngrByIdno } from '@/shared/validators';
import { ApplyItemEnum, FormStatusEnum, MemberTypeEnum, StatusEnum } from '@/shared/enum';
import { IEngEngrSubjectpoint, EngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import { Pagination } from '@/shared/model/pagination.model';
import { useStore } from '@u3u/vue-hooks';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';

interface propsType {
  engEngrSubjectpoint: IEngEngrSubjectpoint;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0806EditInfo',
  props: {
    engEngrSubjectpoint: {
      required: false,
      type: Object,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    criteria: {
      required: false,
      type: Object,
    },
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  components: {
    ISwitchCase,
  },
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    //const engEngrSubjectpointService = new EngEngrSubjectpointService();
    const iTableComponent = ref(null);
    const store = useStore();
    const $bvModal = useBvModal();
    const notificationService = useNotification();
    const formStatusRef = ref(props.formStatus);
    const { engEngrSubjectpoint, formStatus, criteria } = toRefs(props);

    const isReload = ref(false);

    const queryStatus = ref(false);

    const formOptions = reactive({

    });

    let formDefault = {
      compName: '',
      compIdno: '',
      compAddrCity: '',
      compAddrTown: '',
      compAddrOther: '',
      cstLicenseDocNo: '',
    };

    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      compName: {},
      compIdno: {},
      compAddrCity: {},
      compAddrTown: {},
      compAddrOther: {},
      cstLicenseDocNo: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const table = reactive({
      fields_backend: [
        {
          // 機構名稱
          key: 'compName',
          label: i18n.t('label.compName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 統一編號
          key: 'compIdno',
          label: i18n.t('label.compIdno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 地址
          key: 'addr1',
          label: i18n.t('label.addr'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 執照字號
          key: 'cstLicenseDocNo',
          label: i18n.t('label.engrLicenseDocNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    onMounted(() => {
      handleQuery();
    });

    const handleQueryBtn = () => {
      handleQuery();
    };

    function toQueryView() {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    }

    function submitForm() {
      $bvModal.msgBoxConfirm('請再次確認資料是否填寫正確？').then((isOK: boolean) => {
        if (isOK) {
          checkValidity().then((isValid: boolean) => {
            if (isValid) {
              //putForm();
            } else {
              $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
            }
          });
        }
      });
    }
    
    const handleQuery = () => {
      const now = new Date();
      const offset = now.getTimezoneOffset();

      //驗證
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          table.data = [
            {
              compName: '三禾測量',
              compIdno: '16048141',
              compAddrCity: '新北市',
              compAddrTown: '中和區',
              compAddrOther: '2段312巷37號4樓',
              cstLicenseDocNo: '技執字第009016號',
            },
          ];
          // axios
          //   // .post('/cms-comps/criteria', form)           //前端分頁
          //   .post('/eng-engr-certificates/criteria-jpa', form) //後端分頁
          //   .then(({ data }) => {
          //     // table.data = data.slice(0, data.length);   //前端分頁
          //     // table.totalItems = data.length;            //前端分頁
          //     table.data = data.content.slice(0, data.content.length); //後端分頁
          //     table.totalItems = data.totalElements; //後端分頁
          //   })
          //   .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    return {
      FormStatusEnum,
      toQueryView,
      handleQueryBtn,
      reset,
      formStatusRef,
      queryStatus,
      table,
      $v,
      validateState,
      submitForm,
      formOptions,
      handlePaginationChanged,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>

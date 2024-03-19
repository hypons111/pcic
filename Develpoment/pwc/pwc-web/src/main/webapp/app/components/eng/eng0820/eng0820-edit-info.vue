<template>
  <div>
    <!-- <section class="container"> -->
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="text-center font-weight-bold ">
            <!-- <font-awesome-icon icon="project-diagram"></font-awesome-icon> -->
            執照機構名冊
          </h5>
        </div>
        <div class="card-body">
          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 機構名稱 -->
            <i-form-group-check :label="$t('label.compChName') + '：'" :item="$v.compChName">
              <b-form-input v-model="$v.compChName.$model" trim></b-form-input>
            </i-form-group-check>
            <!-- 統一編號 -->
            <i-form-group-check :label="$t('label.compIdno') + '：'" :item="$v.compIdno">
              <b-form-input v-model="$v.compIdno.$model" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <b-col offset="8">
              <!-- 篩選 -->
              <i-button type="search" @click="handleQueryBtn"></i-button>
              <!-- 清除 -->
              <!-- <i-button type="x-circle" @click="reset"></i-button> -->
              <!-- 返回 -->
            <i-button type="arrow-left" @click="toQueryView" />
            </b-col>
          </b-form-row>

        </div>
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

          </i-table>
        </section>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import i18n from '@/shared/i18n';
import { useValidation, validateState } from '@/shared/form';
import { reactive, ref, toRefs, onMounted, defineComponent, unref, onActivated, toRef } from '@vue/composition-api';
import { ApplyItemEnum, FormStatusEnum, MemberTypeEnum, StatusEnum } from '@/shared/enum';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { IEngEngrSubjectpoint, EngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { handleBack } from '@/router/router';

interface propsType {
  engEngrSubjectpoint: IEngEngrSubjectpoint;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0820EditInfo',
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
    const formStatusRef = ref(props.formStatus);

    const queryStatus = ref(false);

    const isReload = ref(false);
    const { engEngrSubjectpoint, formStatus, criteria } = toRefs(props);

    const formOptions = reactive({

    });

    let formDefault = {
      compChName: '',
      compIdno: '',
    };

    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      compChName: {},
      compIdno: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    function toQueryView() {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    }

    const table = reactive({
      fields_backend: [
        {
          // 機構名稱
          key: 'compName',
          label: i18n.t('label.compChName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
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
          key: 'contactAddrOther',
          label: i18n.t('label.contactAddrOther'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
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
      // handleQuery();
    });

    const handleQueryBtn = () => {
      handleQuery();
    };
    
    const toEditView = (entity: IEngEngrSubjectpoint, formStatus: FormStatusEnum, index?: number) => {
        const criteria = Object.assign({}, form);
        criteria['index'] = index;
        criteria['totalItems'] = table.totalItems;

        navigateByNameAndParams('Eng0820EditInfo', {
          engEngrSubjectpoint: entity,
          formStatus: formStatus,
          criteria: criteria,
        });
      };

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
              no: '1',
              compName: '中興工程顧問股份有限公司',
              compIdno: '84124259',
              contactAddrOther: '台北市松山區',
              cstLicenseDocNo: '工程技顧登字第000047號',
            },
          ];
          table.totalItems=1;
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
      handleQueryBtn,
      reset,
      toEditView,
      formStatusRef,
      queryStatus,
      table,
      $v,
      validateState,
      formOptions,
      handlePaginationChanged,
      toQueryView,
      // data,
    };
  },
});
</script>
<style></style>

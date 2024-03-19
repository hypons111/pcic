<template>
  <div>
    <!-- <section class="container"> -->
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="text-center font-weight-bold ">
            <!-- <font-awesome-icon icon="project-diagram"></font-awesome-icon> -->
            執業技師名冊
          </h5>
        </div>
        <div class="card-body">
          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 姓名 -->
            <i-form-group-check :label="$t('label.chName') + '：'" :item="$v.chName">
              <b-form-input v-model="$v.chName.$model" trim></b-form-input>
            </i-form-group-check>
            <!-- 身分證字號 -->
            <i-form-group-check :label="$t('label.idno') + '：'" :item="$v.idno">
              <b-form-input v-model="$v.idno.$model" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>

          <b-form-row>
            <b-col offset="8">
              <i-button type="search" @click="handleQueryBtn"></i-button>
              <i-button type="arrow-left" @click="toQueryView" />
            </b-col>
          </b-form-row>
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
  name: 'Eng0802PracInfo',
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
      chName: '',
      idno: '',
    };

    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      chName: {},
      idno: {},
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
          // 姓名
          key: 'chName',
          label: i18n.t('label.chName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 身分證
          key: 'idno',
          label: i18n.t('label.idno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 執業字號
          key: 'engrLicenseDocNo',
          label: i18n.t('label.engrLicenseDocNo'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
        {
          // 科別
          key: 'subjectList',
          label: i18n.t('label.subjectList'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => formatToString(value, '/', '-'),
        },
      ],
      data: undefined,
      totalItems: undefined,
    })

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

        navigateByNameAndParams('Eng0802PracInfo', {
          engEngrSubjectpoint: entity,
          formStatus: formStatus,
          criteria: criteria,
        });
      };

    const handleQuery = () => {

      //驗證
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          table.data = [
            {
              no: '1',
              chName: '陳志明',
              idno: 'A123456789',
              engrLicenseDocNo: '技執字000046號',
              subjectList: '01',
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
    };
  },
});
</script>
<style></style>

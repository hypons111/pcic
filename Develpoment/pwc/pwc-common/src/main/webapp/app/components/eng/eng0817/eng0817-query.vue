<template>
  <div>
    <!-- <section class="container mt-2" v-if="roleRef === RoleEnum.ADMIN">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>

              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-11">
          <b-collapse v-model="stepVisible.step1">

            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="handleQueryBtn"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          
          </b-collapse>
        </div>
      </div>
    </section> -->

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
        <template #cell(action)="row">
          <!-- 檢視 -->
          <!-- <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button> -->
          <!-- 編輯 -->
          <!-- <i-button size="sm" type="pencil-square" @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)"></i-button> -->
        </template>

        <template v-slot:thead-top="data">
          <b-tr>
            <b-th class="text-center" colspan="3"></b-th>
            <b-th class="text-center" colspan="3">單獨設立或與其他技師組織聯合技師事務所之執業人數(男、女、合計)</b-th>
            <b-th class="text-center" colspan="3">組織或受聘於工程技術顧問公司之執業人數(男、女、合計)</b-th>
            <b-th class="text-center" colspan="3">受聘於前款以外依法令規定必需聘用領有執業執照之技師之營利事業或機構之執業人數(男、女、合計)</b-th>
            <b-th class="text-center" colspan="5">前三項合計(男、女、比例、合計)</b-th>
          </b-tr>
        </template>

      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import i18n from '@/shared/i18n';
import { onMounted, onActivated, watch, reactive, Ref, ref, toRef, inject } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import { useBvModal } from '@/shared/modal';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { IEng0601 } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';

export default {
  name: 'Eng0817Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {

    const iTableComponent = ref(null);

    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);
    const admSysCodeService = new AdmSysCodeService();
    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    // const isReloadProp = computed(() => props.isReload);
    const isReloadProp = toRef(props, 'isReload');

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    // modal物件
    // const $bvModal = useBvModal();
    
    const formOptions = reactive({
      subject: [],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      subject: '',
    };

    // 表單物件驗證規則
    const rules = {
      idno: {},
      chName: {},
      subject: {},
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    //$v : 抓取上面v-model的值、checkValidity:驗證、reset:清空頁面上的參數,rules:規則、form:新值、formDefault:預設值
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      handleQuery();
      getAllSysCodes();
    });

    watch(roleRef, () => {
      if (roleRef.value === RoleEnum.USER) {
        handleQuery();
      } else {
        queryStatus.value = false;
        // table.data = undefined;
      }
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }
    
    const table = reactive({
      fields_backend: [
        {
          // 科別
          key: 'subject',
          label: i18n.t('label.subject'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.subject, value),
        },
        {
          // 各科別技師證書人數
          key: 'eachSubject',
          label: "各科別技師證書人數",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 單獨設立或與其他技師組織聯合技師事務所之執業人數(男、女、合計)
          key: 'man1',
          label: "男",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 單獨設立或與其他技師組織聯合技師事務所之執業人數(男、女、合計)
          key: 'woman1',
          label: "女",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 單獨設立或與其他技師組織聯合技師事務所之執業人數(男、女、合計)
          key: 'sum1',
          label: "合計",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 組織或受聘於工程技術顧問公司之執業人數(男、女、合計)
          key: 'man2',
          label: "男",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 組織或受聘於工程技術顧問公司之執業人數(男、女、合計)
          key: 'woman2',
          label: "女",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 組織或受聘於工程技術顧問公司之執業人數(男、女、合計)
          key: 'sum2',
          label: "合計",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 受聘於前款以外依法令規定必需聘用領有執業執照之技師之營利事業或機構之執業人數(男、女、合計)
          key: 'man3',
          label: "男",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 受聘於前款以外依法令規定必需聘用領有執業執照之技師之營利事業或機構之執業人數(男、女、合計)
          key: 'woman3',
          label: "女",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 受聘於前款以外依法令規定必需聘用領有執業執照之技師之營利事業或機構之執業人數(男、女、合計)
          key: 'sum3',
          label: "合計",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 前三項合計(男、女、比例、合計)
          key: 'man4',
          label: "男",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 前三項合計(男、女、比例、合計)
          key: 'rare1',
          label: i18n.t('label.ratio'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 前三項合計(男、女、比例、合計)
          key: 'woman4',
          label: "女",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 前三項合計(男、女、比例、合計)
          key: 'rare2',
          label: i18n.t('label.ratio'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 前三項合計(男、女、比例、合計)
          key: 'sum4',
          label: "合計",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const handleQueryBtn = () => {
      iTableComponent.value.state.pagination.currentPage = 1;
      iTableComponent.value.emitPagination();
    };

    const toReadView = () => {
        navigateByNameAndParams('Eng0601Authorization', {
        });
      };
    const toEditView = (entity: IEng0601, formStatus: FormStatusEnum, index?: number) => {
        const criteria = Object.assign({}, form);
        criteria['index'] = index;
        criteria['totalItems'] = table.totalItems;

        navigateByNameAndParams('Eng0601EditInfo', {
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
              subject: '01',
              eachSubject: '10114',
              man1: '221',
              woman1: '4',
              sum1: '225',
              man2: '949',
              woman2: '41',
              sum2: '990',
              man3: '0',
              woman3: '0',
              sum3: '0',
              man4: '1170',
              rare1: '96.3',
              woman4: '45',
              rare2: '3.7',
              sum4: '1215',
            },
          ];
          table.totalItems = 1;
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
      iTableComponent,
      validateState,
      formOptions,
      $v,
      stepVisible,
      queryStatus,
      table,
      reset,
      handleQueryBtn,
      toEditView,
      toReadView,
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
<style>
</style>
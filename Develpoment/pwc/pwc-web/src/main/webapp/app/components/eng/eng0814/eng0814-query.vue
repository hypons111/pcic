<template>
  <div>
    <section class="container mt-2" v-if="roleRef === RoleEnum.ADMIN">
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
              <!-- 科別 -->
              <i-form-group-check :label="$t('label.subjectList') + '：'" :item="$v.subjectList">
                <b-form-select v-model="$v.subjectList.$model" :options="formOptions.subject">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 技師姓名 -->
              <i-form-group-check :label="$t('label.engrName') + '：'" :item="$v.chName">
                <b-form-input v-model="$v.chName.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 身分證字號 -->
              <i-form-group-check :label="$t('label.idno') + '：'" :item="$v.idno">
                <b-form-input v-model="$v.idno.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 機構統編 -->
              <i-form-group-check :label="$t('label.engCompIdno') + '：'" :item="$v.compIdno">
                <b-form-input v-model="$v.compIdno.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 機構名稱 -->
              <i-form-group-check :label="$t('label.compChName') + '：'" :item="$v.compChName">
                <b-form-input v-model="$v.compChName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="handleQueryBtn"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          
          </b-collapse>
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
        <template #cell(action)="row">
          <!-- 檢視 -->
          <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
          <!-- 編輯 -->
          <!-- <i-button size="sm" type="pencil-square" @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)"></i-button> -->
        </template>
 
        <template #cell(effectDate) = "{ item }">
          {{ item.effectDateStart }}
          <br/>
          {{ item.effectDateEnd }} 
        </template>

        <template #cell(subjectengrCertificateDocNo) = "{ item }">
          {{ item.subject }}
          <br/>
          {{ item.engrCertificateDocNo }} 
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
  name: 'Eng0814Query',
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
    
    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const formOptions = reactive({
      subject: [],
      subjectList: [],
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      subjectList: '',
      chName: '',
      idno: '',
      compIdno: '',
      compChName: '',
    };

    // 表單物件驗證規則
    const rules = {
      subjectList: {},
      chName: {},
      idno: {},
      compIdno: {},
      compChName: {},
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    //$v : 抓取上面v-model的值、checkValidity:驗證、reset:清空頁面上的參數,rules:規則、form:新值、formDefault:預設值
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
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

    // function getAllSysCodes() {
    //   formOptions.Status = [
    //     { value: '0', text: '暫存' },
    //     { value: '1', text: '批退' },
    //     { value: '3', text: '初審中' },
    //     { value: '4', text: '複審中' },
    //     { value: '5', text: '已結案' },
    //     { value: '6', text: '作廢' },
    //   ];
    // }
    
    const table = reactive({
      fields_backend: [
        {
          // 姓名
          key: 'chName',
          label: i18n.t('label.name'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 執照字號
          key: 'engrLicenseDocNo',
          label: i18n.t('label.engrLicenseDocNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 申請項目
          key: 'applyItem',
          label: i18n.t('label.applyItems'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 執業機構
          key: 'compChName',
          label: i18n.t('label.engCompChName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 技師證書科別/字號
          key: 'subjectengrCertificateDocNo',
          label: "技師證書科別/字號",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 效期起迄
          key: 'effectDate',
          label: "效期起迄",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 核准日期
          key: 'publicDate',
          label: i18n.t('label.checkDate1'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 懲戒情形
          key: 'disciplineStatus',
          label: i18n.t('label.disciplineStatus'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 動作
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
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

    const toEditView = (entity: IEng0601, formStatus: FormStatusEnum, index?: number) => {
        const criteria = Object.assign({}, form);
        criteria['index'] = index;
        criteria['totalItems'] = table.totalItems;

        navigateByNameAndParams('Eng0814EditInfo', {
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
              chName: '陳啟政',
              engrLicenseDocNo: '技執字第008052號',
              applyItem: '自行停業',
              compChName: '水昱有限公司',
              subject: '土木工程科',
              engrCertificateDocNo: '技證字第012251號',
              effectDateStart: '',
              effectDateEnd: '',
              publicDate: '2017/10/16',
              disciplineStatus: '有',
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
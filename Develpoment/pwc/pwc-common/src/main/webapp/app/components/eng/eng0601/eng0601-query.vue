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
                <b-col offset="8">
                  <i-button type="PerReInState" @click="toReadView()"></i-button>
                </b-col>
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
              <!-- 統一編號 -->
              <i-form-group-check :label="$t('label.compIdno') + '：'" :item="$v.compIdno">
                <b-form-input v-model="$v.compIdno.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 公司名稱 -->
              <i-form-group-check :label="$t('label.comName') + '：'" :item="$v.compName">
                <b-form-input v-model="$v.compName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 科別 -->
              <i-form-group-check :label="$t('label.subject') + '：'" :item="$v.subject">
                <b-form-select v-model="$v.subject.$model" :options="formOptions.subject">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
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
          <i-button size="sm" type="pencil-square" @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)"></i-button>
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

export default {
  name: 'Eng0601Query',
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
      compIdno: {},
      compName: {},
      subject: {},
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    //$v : 抓取上面v-model的值、checkValidity:驗證、reset:清空頁面上的參數,rules:規則、form:新值、formDefault:預設值
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
    //   getAllSysCodes();
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
          // 統一編號
          key: 'compIdno',
          label: i18n.t('label.compIdno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 公司名稱
          key: 'chName',
          label: i18n.t('label.comName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
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
              compIdno: 'H101320698',
              chName: '一代建築師事務所',
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
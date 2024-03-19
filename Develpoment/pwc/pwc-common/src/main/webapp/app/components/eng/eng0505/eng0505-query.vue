<template>
  <div>
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
          </div>
        </div>
        <div class="card-body col-11">
          <!-- 顧問公會 -->
          <b-form-row>
            <i-form-group-check :label="$t('label.guild') + '：'" 
            :item="$v.guild">
              <b-form-select v-model="$v.guild.$model" 
                :options="formOptions.guild">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 公司統號(統一編號) -->
            <i-form-group-check :label="$t('label.compIdno') + '：'"
            :item="$v.compIdno">
              <b-form-input v-model="$v.compIdno.$model" trim></b-form-input>
            </i-form-group-check>
            <i-form-group-check>
              <!-- 查詢 -->
              <i-button type="search" @click="handleQueryBtn"/>
              <!-- 清除 -->
              <i-button type="x-circle" class="ml-2" @click="clear"/>
            </i-form-group-check>
          </b-form-row>
        </div>
      </div>
    </section>
    <section class="container mt-2">
      <!-- 新增 -->
      <i-button type="file-earmark-plus"
      @click="toEditView(new EngManage(), FormStatusEnum.CREATE)"> 
      </i-button>
    </section>
    <section class="container mt-2" v-show="queryStatus">
      <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        :items="table.data"
        @changePagination="handlePaginationChanged"
      >
        <template #cell(action)="row">
          <!-- 編輯 -->
          <i-button
            size="sm"
            type="pencil-square"
            @click="toEditView(row.item, FormStatusEnum.EDIT)"
          ></i-button>
          <!-- 刪除 -->
          <i-button
            size="sm" @click="submitForm"
            type="trash"></i-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, ref, reactive } from '@vue/composition-api';
import {useValidation} from '@/shared/form';
import i18n from '@/shared/i18n';
import axios from 'axios';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import { EngCaseTotalDTO } from '@/shared/model/eng/eng-case.model';
import { useBvModal } from '@/shared/modal';
import { IEngManage, EngManage } from '@/shared/model/eng/eng-manage.model';

export default {
  name: 'Eng0505Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const iTableComponent = ref(null);
    const queryStatus = ref(false);
    const $bvModal = useBvModal();
    const formOptions = reactive({
      guild: [],
    })
    const formDefault = {
      guild: '',
      compIdno: '',
    }
    const rules = {
      guild: {},
      compIdno: {},
    }
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      getAllSyscodes();
    })

    const handleQueryBtn = () => {
      console.log('查詢');
      
      // iTableComponent.value.state.pagination.currentPage = 1;
      // iTableComponent.value.emitPagination();
      handleQuery();
    };

    const clear = () => {
      reset();
      queryStatus.value = false;
      // table.data = undefined;
    }

    const toEditView = async (engManage: IEngManage, formStatus: FormStatusEnum) => {
      navigateByNameAndParams('Eng0505EditInfo', {
        formStatus: formStatus,
      })
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    function submitForm() {
      $bvModal.msgBoxConfirm('是否確認刪除？').then((isOK: boolean) => {
        if(isOK) {
          $bvModal.msgBoxConfirm('刪除成功!').then((isOK: boolean) => {})
        }
      })
    }

    const handleQuery = () => {
      queryStatus.value = true;
      table.data = [];
      table.data = data;
      table.totalItems = data.length;
    }

    function getAllSyscodes() {
      formOptions.guild = [
        { value: '0', text: '中華民國工程技術顧問商業同業公會'},
        { value: '1', text: '台北市工程技術顧問商業同業公會'},
      ]
    }

    const table = reactive({
      fields_backend: [
        {
          // 顧問公司名稱
          key: 'chName',
          label: i18n.t('label.consultantName'),
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
          tdClass: 'text-center align-middle',
        },
        {
          // 負責人姓名
          key: 'masterName',
          label: i18n.t('label.masterName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => 
          // formOptionsFormatter(formOptions.caseType, value),
        },
        {
          // 地址
          key: 'addr',
          label: i18n.t('label.addr'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 電話
          key: 'tel1',
          label: i18n.t('label.phoneNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 傳真
          key: 'fax',
          label: i18n.t('label.fax'),
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

    const data = [
      {
        id: 1,
        chName: '亞新工程顧問股份有限公司',
        compIdno: '04723653',
        masterName: '莫若楫',
        addr: '新北市汐止區新台五路1段112號22樓',
        tel1: '02-2696173',
        fax: ''
      }
    ];

    return {
      queryStatus,
      formOptions,
      $v,
      handleQueryBtn,
      clear,
      toEditView,
      table,
      submitForm,
      handlePaginationChanged,
      FormStatusEnum,
      EngManage,
    }
  }
}
</script>
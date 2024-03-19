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
          <!-- 全聯會 -->
          <b-form-row>
            <i-form-group-check :label="$t('label.bloc') + '：'" 
            :item="$v.bloc">
              <b-form-select v-model="$v.bloc.$model" 
                :options="formOptions.bloc">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
            </i-form-group-check>
            <i-form-group-check>
              <!-- 查詢 -->
            <i-button type="search" @click="handleQueryBtn"/>
            </i-form-group-check>
          </b-form-row>  
        </div>
      </div>
    </section>
    <section class="container mt-2">
      <!-- 新增 -->
      <i-button type="file-earmark-plus"
      @click="toEditView(0, FormStatusEnum.CREATE)"> </i-button>
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
            @click="toEditView(row.item.id, FormStatusEnum.EDIT)"
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

export default {
    name: 'Eng0504Query',
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
        bloc: [],
      })
      const formDefault = {
        bloc: '',
      }
      const rules = {
        bloc: {},
      }
      const form = reactive(Object.assign({}, formDefault));
      const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

      onMounted(() => {
        getAllSyscodes();
      })

      function getAllSyscodes() {
        formOptions.bloc = [
          { value: '0', text: '中華民國土木技師公會全國聯合會'},
          { value: '1', text: '中華民國工礦安全衛生技師公會全國聯合會'},
        ]
      }

      function handleQueryBtn() {
        handleQuery();
      }

      function submitForm() {
      $bvModal.msgBoxConfirm('是否確認刪除？').then((isOK: boolean) => {
        if(isOK) {
          $bvModal.msgBoxConfirm('刪除成功!').then((isOK: boolean) => {})
        }
      })
    }

    const handleQuery = () => {
      table.data = [];
      switch($v.value.bloc.$model) {
        case'0':
          table.data = data1;
          table.totalItems = data1.length;
          break;
        case'1':
          table.data = data2;
          table.totalItems = data2.length;
          break;
        default:
          table.data.push(...data1);
          table.data.push(...data2);
          table.totalItems = table.data.length;
          break;
      }
      queryStatus.value = true;
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const toEditView = async (id: string, formStatus: FormStatusEnum) => {
      navigateByNameAndParams('Eng0504EditInfo', {
        id: id,
        formStatus: formStatus,
      })
    }

    const table = reactive({
      fields_backend: [
        {
          // 公會
          key: 'guildName',
          label: i18n.t('label.guildName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 理事長
          key: 'masterName',
          label: i18n.t('label.chairman'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 秘書長
          key: 'personName',
          label: i18n.t('label.secretaryGeneral'),
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

    const data1 = [
      {
        id: 1,
        guildName: '台北市冷凍空調技師公會',
        masterName: '許沛峰',
        personName: '詹佳慧',
        addr: '台北市中山區南京東路2段150號5樓',
        tel1: '02-2377073',
        fax: '02-25170789'
      }
    ]

    const data2 = [
      {
        id: 2,
        guildName: '高雄市冷凍空調技師公會',
        masterName: '吳承澐',
        personName: '',
        addr: '高雄市鳳山區福安二街105巷12弄1號',
        tel1: '07-8121559',
        fax: '07-8150218'
      }
    ]

    return {
      formOptions,
      $v,
      handleQueryBtn,
      table,
      queryStatus,
      handlePaginationChanged,
      submitForm,
      toEditView,
      FormStatusEnum
    }
  }
}
</script>
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
              <!-- 公會類型 -->
              <i-form-group-check :label="$t('label.guildType') + '：'" 
              :item="$v.type">
                <b-form-select v-model="$v.type.$model"
                :options="formOptions.type"
                >
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              
              <!-- 科別 -->
              <i-form-group-check :label="$t('label.subject') + '：'" 
              :item="$v.subject">
                <b-form-select v-model="$v.subject.$model"
                :options="formOptions.subject">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 公會名稱 -->
              <i-form-group-check :label="$t('label.guildName') + '：'"
              :item="$v.chName">
                <b-form-input v-model="$v.chName.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 理事長 -->
              <i-form-group-check :label="$t('label.chairman') + '：'" 
              :item="$v.masterName">
                <b-form-input  v-model="$v.masterName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 狀態 -->
              <i-form-group-check :label="$t('label.status') + '：'" 
              :item="$v.status">
                <b-form-select v-model="$v.status.$model" 
                :options="formOptions.status">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <b-col offset="8">
                <!-- 查詢 -->
                <i-button type="search" @click="handleQueryBtn"/>
                <!-- 清除 -->
                <i-button type="x-circle" @click="clear"/>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2">
      <!-- 新增 -->
      <i-button type="file-earmark-plus"
      v-if="roleRef === RoleEnum.ADMIN"
      @click="toEditView(new EngManage(), FormStatusEnum.CREATE)"> </i-button>
      <!--切換角色按鈕-->
      <b-button size="md" variant="warning"  @click="switchRoles">
        <font-awesome-icon :icon="['fas', 'people-arrows']" />
        {{ roleRef }}
      </b-button>
    </section>

    <section class="container mt-2" v-show="queryStatus">
      <i-table
      v-if="roleRef === RoleEnum.ADMIN"
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >
        <template v-slot:cell(tel1)="slot">
          電話(一)：<br/>
          {{slot.item.tel1}}<br/>
          電話(二)：<br/>
          {{slot.item.tel2}}<br/>
          傳真：<br/>
          {{slot.item.fax}}<br/>
        </template>

        <template #cell(address1Other)="row">
          <b-form-row >
            <i-form-group-check class="col-12" content-cols="10">
              <i-address-picker
                :is-text="true"
                :zip="row.item.address1Zip"
                :city="row.item.address1City"
                :town="row.item.address1Town"
                :other="row.item.address1Other"
              >
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>
        </template>
        <template #cell(action)="row">
          <!-- 檢視 -->
          <i-button size="sm" type="binoculars" 
          @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
          <!-- 編輯 -->
          <i-button
            size="sm"
            type="pencil-square"
            @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)"
          ></i-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { Ref, onMounted, onActivated, watch, ref, toRef, reactive } from '@vue/composition-api';
import { RoleEnum, FormStatusEnum} from '@/shared/enum';
import { useValidation, validateState } from '@/shared/form';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { Pagination } from '@/shared/model/pagination.model';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { IEngManage, EngManage } from '@/shared/model/eng/eng-manage.model';
import EngManageService from '../eng0703/eng0703-manage.service';
import { Eng0703, IEng0703 } from '@/shared/model/eng/eng0703.model';
import { Cipher } from 'crypto';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'Eng0502Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const engManageService = new EngManageService();
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);
    const iTableComponent = ref(null);
    const stepVisible = reactive({
      step1: true,
    });
    const notificationService: NotificationService = useNotification();
    const switchRoles = () => {
      if (roleRef.value === RoleEnum.ADMIN) roleRef.value = RoleEnum.USER;
      else roleRef.value = RoleEnum.ADMIN;
    };
    const queryStatus = ref(false);
    const isReloadProp = toRef(props, 'isReload');

    const table = reactive({
      fields_backend: [
        {
          // 公會類型
          key: 'type',
          label: i18n.t('label.guildType'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => 
          formOptionsFormatter(formOptions.type, value),
        },
        {
          // 公會名稱
          key: 'chName',
          label: i18n.t('label.guildName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 理事長
          key: 'masterName',
          label: i18n.t('label.chairman'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 電話
          key: 'tel1',
          label: i18n.t('label.tel'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 地址
          key: 'address1Other',
          label: i18n.t('label.addr'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 業務範圍
          key: 'subjectList',
          label: i18n.t('label.businScope'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => 
          formOptionsFormatter(formOptions.subject, value),
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

    const formOptions = reactive({
      type:[],
      subject: [],
      status: [],
    });

    const formDefault = {
      type: '',
      subject: '',
      chName: '',
      masterName: '',
      status: ''
    };

    const form = reactive(Object.assign({}, formDefault));

    const rules = {
      type: {},
      subject: {},
      chName: {},
      masterName: {},
      status: {}
    }

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    watch(roleRef, () => {
      if (roleRef.value === RoleEnum.ADMIN) {
        stepVisible.step1 = true;
      } else {
        stepVisible.step1 = false;
      }
    })

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    onMounted(() => {
      if (roleRef.value === RoleEnum.USER) {
        stepVisible.step1 = false;
      } else {
        getAllSysCodes();
      }
    }) 

    function getAllSysCodes() {
      axios
        .get('/service/adm-sys-codes')
        .then(({ data }) => {
          formOptions.type.push(
            ...data
            .filter(item => item['moduleType'] === 'ENG' && item['dataType'] === 'COMP_TYPE'
            && (item['dataKey'] === '2' || item['dataKey'] === '5' || item['dataKey'] === '6'))
            .map(item => {
              return { value: item['dataKey'], text: item['value'] };
            })
          );
          formOptions.subject.push(
            ...data.filter(
              item => item['moduleType'] === 'ENG' && item['dataType'] === 'SUBJECT')
              .map(item => {
                return { value: item['dataKey'], text: item['value']};
              })
          );
          formOptions.status = [
            { value: 0, text: '停用'},
            { value: 1, text: '啟用'}
          ];
        })
        .catch(notificationErrorHandler(notificationService));
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const handleQueryBtn = () => {
      iTableComponent.value.state.pagination.currentPage = 1;
      iTableComponent.value.emitPagination();
    };

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          
          axios
            .post('/eng-eng0502r1', form) //後端分頁
            .then(({ data }) => {      
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
              
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const clear = () => {
      reset();
      
      queryStatus.value = false;
      table.data = undefined;
    }

    const toEditView = async (engManage: IEngManage, formStatus: FormStatusEnum, index?: number) => {
      if (formStatus !== FormStatusEnum.CREATE) {
        engManageService.viewEng0502(engManage.engCompId)
        .then(eng0703 => routerTo(eng0703, formStatus, index))
        .catch(notificationErrorHandler(notificationService));
      } else {
        
        const eng0703 = new Eng0703();
        eng0703.engManageDTO = engManage;
        routerTo(eng0703, formStatus, index);
      }
    }

    const routerTo = (eng0703: IEng0703, formStatus: FormStatusEnum, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['currentPage'] = criteria['currentPage'] * criteria['perPage'] + index;
      criteria['perPage'] = 1;
      criteria['totalItems'] = table.totalItems;

      navigateByNameAndParams('Eng0502EditInfo', {
        eng0703: eng0703,
        formStatus: formStatus,
        role: roleRef.value,
        criteria: criteria,
      })      
    }

    return {
      stepVisible,
      ref,
      roleRef,
      switchRoles,
      queryStatus,
      formOptions,
      $v,
      reset,
      table,
      handleQueryBtn,
      iTableComponent,
      RoleEnum,
      handlePaginationChanged,
      clear,
      formOptionsFormatter,
      FormStatusEnum,
      toEditView,
      EngManage
    }
  }
}
</script>
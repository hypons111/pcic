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
              <!-- 負責人姓名 -->
              <i-form-group-check :label="$t('label.masterName') + '：'"
              :item="$v.masterName">
                <b-form-input v-model="$v.masterName.$model" trim>
                </b-form-input>
              </i-form-group-check>
              <!-- 身分證字號 -->
              <i-form-group-check :label="$t('label.idno') + '：'" 
              :item="$v.idno">
                <b-form-input v-model="$v.idno.$model" 
                trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 公司名稱 -->
              <i-form-group-check :label="$t('label.comName') + '：'"
              :item="$v.compName">
                <b-form-input v-model="$v.compName.$model" trim>
                </b-form-input>
              </i-form-group-check>
              <!-- 統一編號 -->
              <i-form-group-check :label="$t('label.compIdno') + '：'" 
              :item="$v.compIdno">
                <b-form-input v-model="$v.compIdno.$model" trim>
                </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 申請日期 -->
              <i-form-group-check :label="$t('label.applyDate') + '：'"
              :dual1="$v.applyDateStart" :dual2="$v.applyDateEnd">
                <b-input-group>
                  <i-date-picker
                    v-model="$v.applyDateStart.$model"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker 
                    v-model="$v.applyDateEnd.$model"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
              <!-- 發證日期 -->
              <i-form-group-check :label="$t('label.publicDate') + '：'"
              :dual1="$v.publicDateStart" :dual2="$v.publicDateEnd">
                <b-input-group>
                  <i-date-picker v-model="$v.publicDateStart.$model"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker
                    v-model="$v.publicDateEnd.$model"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 登記證字號 -->
              <i-form-group-check :label="$t('label.licenseNo') + '：'"
              :item="$v.licenseNo">
                <b-form-input v-model="$v.licenseNo.$model" trim>
                </b-form-input>
              </i-form-group-check>
              <!-- 審核狀態 -->
              <i-form-group-check :label="$t('label.reviewStatus') + '：'" 
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
              <!-- 收文文號 -->
              <i-form-group-check :label="$t('label.receiveNo') + '：'"
              :item="$v.receiveNo">
                <b-form-input v-model="$v.receiveNo.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 編號 -->
              <i-form-group-check :label="$t('label.engEngrCertificateNo') + '：'" 
              :item="$v.engCstLicenseNo">
                <b-form-input v-model="$v.engCstLicenseNo.$model" trim>
                </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 入帳狀態 -->
              <i-form-group-check :label="$t('label.isClose') + '：'" 
              :item="$v.isClose">
                <b-form-select v-model="$v.isClose.$model"
                :options="formOptions.isClose">
                  <template #first>
                    <option value=null>請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <!-- 申請方式 -->
              <i-form-group-check :label="$t('label.applyType') + '：'" 
              :item="$v.applyType">
                <b-form-select v-model="$v.applyType.$model"
                :options="formOptions.applyType">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <!-- 查詢 -->
                <i-button type="search" @click="handleQueryBtn"></i-button>
                <!-- 清除 -->
                <i-button type="x-circle" @click="clear"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2">
      <!-- 新增 -->
      <i-button type="file-earmark-plus" @click="toEditView(new EngManage(), FormStatusEnum.CREATE)"> </i-button>
      <!--切換角色按鈕-->
      <b-button size="md" variant="warning" @click="switchRoles">
        <font-awesome-icon :icon="['fas', 'people-arrows']" />
        {{ roleRef }}
      </b-button>
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
          <i-button @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)"
            v-if="
              (row.item.status !== '3' || roleRef === RoleEnum.ADMIN) &&
              (row.item.status !== '4' || roleRef === RoleEnum.ADMIN) &&
              (row.item.status !== '5' || roleRef === RoleEnum.ADMIN) &&
              row.item.status !== '6'
            "
            size="sm"
            type="pencil-square"
            
          ></i-button>
          <b-button size="sm" variant="info" 
          @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)">補登</b-button>
          <!-- 申請(新增另案) -->
          <!-- <i-button
            v-if="row.item.status === '5' && roleRef === RoleEnum.ADMIN"
            size="sm"
            type="node-plus"
            
          ></i-button> -->
          <!-- @click="toEditView(row.item, FormStatusEnum.REMAKE, row.index)" -->
          <!-- 申請(變更申請) -->
          <i-button
            v-if="row.item.status === '5' && roleRef === RoleEnum.USER"
            size="sm"
            type="node-plus"
            
          ></i-button>
          <!-- @click="toEditView(row.item, FormStatusEnum.CHANGE_APPLY, row.index)" -->
          <!-- 作廢 -->
          <i-button
            v-if="(row.item.status !== '5' || roleRef === RoleEnum.ADMIN) && row.item.status !== '6'"
            size="sm"
            type="clipboard-x"
            @click="invalid"
          ></i-button>
          <!-- @click="discard(row.item)" -->
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, ref, reactive, Ref } from '@vue/composition-api';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';
import i18n from '@/shared/i18n';
import axios from 'axios';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useValidation, validateState } from '@/shared/form';
import { Pagination } from '@/shared/model/pagination.model';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { IEngManage, EngManage } from '@/shared/model/eng/eng-manage.model';
import { useBvModal } from '@/shared/modal';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';

  export default {
    name: 'Eng0201Query',
    props: {
      isReload: {
        type: Boolean,
        required: false,
      },
    },
    setup(props) {
      const admSysCodeService = new AdmSysCodeService();
      const iTableComponent = ref(null);
      const notificationService: NotificationService = useNotification();
      const $bvModal = useBvModal();
      const stepVisible = reactive({
        step1: true,
      });
      // 手動定義角色
      const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);
      // 切換角色
      const switchRoles = () => {
        if (roleRef.value === RoleEnum.ADMIN) roleRef.value = RoleEnum.COMP;
        else roleRef.value = RoleEnum.ADMIN;
      };
      // 查詢結果及狀態
      const queryStatus = ref(false);

      const formOptions = reactive({
        status: [],  // 核准狀態
        isClose: [],
        applyType: [],
        applyItems: [],
      });

      const formDefault = {
        masterName: '',
        idno: '',
        compName: '',
        compIdno: '',
        applyDateStart: null,
        applyDateEnd: null,
        publicDateStart: null,
        publicDateEnd: null,
        engCstLicenseNo: '',
        status: '',
        receiveNo: '',
        licenseNo: '',
        isClose: null,
        applyType: '',
      };

      const form = reactive(Object.assign({}, formDefault));

      const rules = {
        masterName: {},
        idno: {},
        compName: {},
        compIdno: {},
        applyDateStart: {},
        applyDateEnd: {},
        publicDateStart: {},
        publicDateEnd: {},
        engCstLicenseNo: {},
        status: {},
        receiveNo: {},
        licenseNo: {},
        isClose: {},
        applyType: {},
      }

      const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

      const table = reactive({
        fields_backend: [
          {
            // 編號
            key: 'engCstLicenseNo',
            label: i18n.t('label.engEngrCertificateNo'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 公司名稱
            key: 'chName',
            label: i18n.t('label.comName'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-left align-middle',
          },
          {
            // 統編
            key: 'compIdno',
            label: i18n.t('label.compIdno'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-left align-middle',
            // formatter: (value: string) => formOptionsFormatter(formOptions.subject, value),
          },
          {
            // 登記證字號
            key: 'licenseNo',
            label: i18n.t('label.licenseNo'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 收文文號
            key: 'receiveNo',
            label: i18n.t('label.receiveNo'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-left align-middle',
          },
          {
            // 申請方式
            key: 'applyType',
            label: i18n.t('label.applyType'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-left align-middle',
            formatter: (value: string) => formOptionsFormatter(formOptions.applyType, value),
          },
          {
            // 申請項目
            key: 'applyItems',
            label: i18n.t('label.applyItems'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-left align-middle',
            formatter: (value: string) => formOptionsFormatter(formOptions.applyItems, value),
          },
          {
            // 申請日期
            key: 'applyDate',
            label: i18n.t('label.applyDate'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // formatter: (value: string) => formatToString(value, '/', '-'),
          },
          {
            // 發證日期
            key: 'publicDate',
            label: i18n.t('label.publicDate'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // formatter: (value: string) => formatToString(value, '/', '-'),
          },
          {
            // 狀態
            key: 'status',
            label: i18n.t('label.status'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-left align-middle',
            formatter: (value: string) => formOptionsFormatter(formOptions.status, value),
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
        data: [],
        totalItems: undefined,
      });

      onMounted(() => {
        if (roleRef.value === RoleEnum.COMP) {
          handleQuery();
        }
        getAllSysCodes();
      });

      function getAllSysCodes(): void {
        admSysCodeService.setFormOptions(formOptions, 'ENG');
        formOptions.applyItems = [
          { value: '0', text: '許可'},
          { value: '1', text: '許可變更'},
          { value: '2', text: '許可變更科別'},
          { value: '3', text: '許可變更董事'},
          { value: '4', text: '許可變更監察人'},
          { value: '7', text: '核發'},
          { value: '8', text: '換發'},
          { value: '10', text: '補發'},
          { value: '11', text: '註銷'},
          { value: '12', text: '自行停業'},
          { value: '13', text: '停業處分'},
          { value: '14', text: '撤銷或廢止登記'},
          { value: '15', text: '廢止或變更許可'},
          { value: '16', text: '復業'},
          { value: '17', text: '廢止許可'},
          { value: '20', text: '核發英文登記證'},
        ]
      }

      const handlePaginationChanged = (pagination: Pagination) => {
        Object.assign(form, pagination);
        handleQuery();
      }

      const handleQuery = () => {
        
        checkValidity().then((isValid: boolean) => {
            table.data = [];
            queryStatus.value = true;
            table.data = data;
            table.totalItems = data.length;
          }
        );
      };

      const invalid = () => {
        $bvModal.msgBoxConfirm('請再次確認資料是否作廢？').then((isOK: boolean) => {
          if(isOK) {
            $bvModal.msgBoxConfirm('作廢成功。').then((isOK: boolean) => {})
          }
        
        });
      $v.value.$reset();
      }

      const handleQueryBtn = () => {
        // iTableComponent.value.state.pagination.currentPage = 1;
        // iTableComponent.value.emitPagination();
        handleQuery();
      };

      const clear = () => {
        reset();
        
        queryStatus.value = false;
        table.data = [];
      }

      const toEditView = async (id: string, formStatus: FormStatusEnum) => {
        routerTo(id, formStatus);
      }

      const routerTo = (id: string, formStatus: FormStatusEnum) => {
        
        navigateByNameAndParams('Eng0201EditInfo', {
          id: id,
          formStatus: formStatus
        }) 
      }

      const data = [  // 資料
        {
          id: '01',
          masterName: '蔡貫洪',
          masterIdno: 'A123456789',
          engCstLicenseNo: 'b123456789',
          chName: '一宅通工程顧問公司',
          compIdno: 'c1234567',
          licenseNo: 'd1234567',
          receiveNo: 'e1234567',
          applyType: '1',
          applyItems: '8',
          applyDate: '2020-10-20',
          publicDate: '2020-10-21',
          status: '5',
          isClose: true
        },
        // {
        //   id: '02',
        //   masterName: '廖一青',
        //   masterIdno: 'F200061910',
        //   engCstLicenseNo: 'b223456789',
        //   chName: '萬事通顧問公司',
        //   compIdno: 'c2234567',
        //   licenseNo: 'd2234567',
        //   receiveNo: 'e2234567',
        //   applyType: '0',
        //   applyDate: '2010-10-20',
        //   publicDate: '2011-10-20',
        //   status: '3',
        //   isClose: false
        // },
      ];
      
      return {
        stepVisible,
        roleRef,
        switchRoles,
        RoleEnum,
        queryStatus,
        table,
        handleQueryBtn,
        handleQuery,
        handlePaginationChanged,
        $v,
        formOptions,
        clear,
        toEditView,
        FormStatusEnum,
        EngManage,
        invalid
      }
    }
  }

</script>
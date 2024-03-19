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
              <!-- 公會名稱 -->
              <i-form-group-check :label="$t('label.guildName') + '：'"
              :item="$v.chName">
                <b-form-input v-model="$v.chName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 姓名 -->
              <i-form-group-check :label="$t('label.name') + '：'"
              :item="$v.engrName">
                <b-form-input v-model="$v.engrName.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 身分證字號 -->
              <i-form-group-check :label="$t('label.idno') + '：'"
              :item="$v.idno">
                <b-form-input v-model="$v.idno.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 會員編號 -->
              <i-form-group-check :label="$t('label.engCompMemberNo') + '：'"
              :item="$v.engCompMemberNo">
                <b-form-input v-model="$v.engCompMemberNo.$model" trim></b-form-input>
              </i-form-group-check>
                <i-form-group-check>
                  <!-- 查詢 -->
                  <i-button type="search" @click="handleQueryBtn"/>
                  <!-- 清除 -->
                  <i-button type="x-circle" class="ml-2" @click="clear"/>
                </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :label="'excel檔：'">
                <b-form-file
                    v-model="$v.inputFile.$model"
                    multiple
                    plain
                  >
                  </b-form-file> 
                <!-- <b-button>選擇檔案</b-button> -->
              </i-form-group-check>
              <i-form-group-check>
                <b-button variant="outline-secondary" 
                 @click="putSaveForm">匯入名冊</b-button>
                <!-- <b-button>匯入名冊</b-button> -->
                <b-button variant="outline-secondary" class="ml-2">
                  下載範例</b-button>
              </i-form-group-check>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2">
      <!-- 新增 -->
      <i-button type="file-earmark-plus"
      @click="toEditView(new EngManage(), FormStatusEnum.CREATE, 0)"> </i-button>
      <!--切換角色按鈕-->
      <!-- <b-button size="md" variant="warning"  @click="switchRoles">
        <font-awesome-icon :icon="['fas', 'people-arrows']" />
        {{ roleRef }}
      </b-button> -->
    </section>

    <section class="container mt-2" v-show="queryStatus">
    <b-button @click="listEngr">技師名冊</b-button>
    <b-button @click="listIden">鑑定名冊</b-button>
    <b-button @click="listSave">災後動員名冊</b-button>
      <i-table
        small
        ref="iTableComponent"
        title="資料列表"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >
        <template #cell(action)="row">
          <!-- 編輯 -->
          <i-button
            size="sm" @click="toEditView(new EngManage(), FormStatusEnum.EDIT, row.item.id)"
            type="pencil-square"></i-button>
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
import { useBvModal } from '@/shared/modal';

export default {
  name: 'Eng0503Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    const engManageService = new EngManageService();
    const stepVisible = reactive({ step1: true, });
    const queryStatus = ref(false);
    const isReloadProp = toRef(props, 'isReload');
    const notificationService: NotificationService = useNotification();
    const iTableComponent = ref(null);
    enum ListEnum { ENGRS = '技師名冊', IDENTITY = '鑑定名冊', SAVE = '災後動員名冊' };
    let list: Ref<ListEnum> = ref(ListEnum.ENGRS);
    const $bvModal = useBvModal();

    const formDefault = {
      subject: '',
      chName: '',
      engrName: '',
      idno: '',
      engCompMemberNo: '',
      inputFile: [],
    };

    const rules = {
      subject: {},
      chName: {},
      engrName: {},
      idno: {},
      engCompMemberNo: {},
      inputFile: {},
    }

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const toEditView = async (engManage: IEngManage, formStatus: FormStatusEnum, id: number) => {
      routerTo(engManage, formStatus, id)
    }

    const clear = () => {
      reset();
      
      queryStatus.value = false;
      table.data = undefined;
    }

    function submitForm() {
      $bvModal.msgBoxConfirm('是否確認刪除？').then((isOK: boolean) => {
        if(isOK) {
          $bvModal.msgBoxConfirm('刪除成功!').then((isOK: boolean) => {})
        }
      })
    }

    onActivated(() => {
      if (isReloadProp.value) {
        handleQuery();
      }
    });

    const handleQueryBtn = () => {
      iTableComponent.value.state.pagination.currentPage = 1;
      iTableComponent.value.emitPagination();
    };

    const routerTo = (engManage: IEngManage, formStatus: FormStatusEnum, id: number) => {
      // const criteria = Object.assign({}, form);
      // criteria['currentPage'] = criteria['currentPage'] * criteria['perPage'] + index;
      // criteria['perPage'] = 1;
      // criteria['totalItems'] = table.totalItems;

      navigateByNameAndParams('Eng0503EditInfo', {
        engManage: engManage,
        formStatus: formStatus,
        id: id,
        // role: roleRef.value,
        // criteria: criteria,
      })      
    }

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const headers = {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    };

    const putSaveForm = () => {
      //加入判斷 判斷是info或down
      let url = '/service/excel-upload';
      const formData = new FormData();
      Array.from(form.inputFile).forEach(file => {
        formData.append('uploadFile', file);
      });
      $bvModal.msgBoxConfirm('是否確認匯入？').then((isOK: boolean) => {
          if (isOK) {
            $bvModal.msgBoxOk('儲存成功!');
          }
      })

      // axios
      //   .put(url, formData, headers)
      //   .then(() => {
      //     // notificationService.success(`上傳成功`);
      //   })
      //   .finally(() => {})
      //   .catch(notificationErrorHandler(notificationService));
    };

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          
          table.data = data;
          table.totalItems = data.length;
        }
      })
    }

    function listEngr() {
      data = data1;
      handleQuery();
    }
    function listIden() {
      data = data2;
      handleQuery();
    }
    function listSave() {
      data = data3;
      handleQuery();
    }

    const table = reactive({
      fields_backend: [
        {
          // 公會名稱
          key: 'chName',
          label: i18n.t('label.guildName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 編號
          key: 'engCompMemberNo',
          label: i18n.t('label.engEngrCertificateNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 姓名(技師)
          key: 'engrName',
          label: i18n.t('label.name'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 性別
          key: 'sex',
          label: i18n.t('label.sex'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 身份證字號
          key: 'idno',
          label: i18n.t('label.idno'),
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
        chName: '高雄市環境工程技師公會',
        engCompMemberNo: '410',
        engrName: '鄭煜騰',
        sex: '男',
        idno: 'R120193458',
      }
    ]
    

    const data2 = [
      {
        id: 2,
        chName: '高雄市環境工程技師公會',
        engCompMemberNo: '407',
        engrName: '蔡宗延',
        sex: '男',
        idno: 'N125573971',
      }
    ]
    

    const data3 = [
      {
        id: 3,
        chName: '臺灣省土木技師公會',
        engCompMemberNo: '2433',
        engrName: '盧垂德',
        sex: '男',
        idno: '	N120748834',
      }
    ]
    let data = data1;
    

    return {
      stepVisible,
      $v,
      table,
      iTableComponent,
      queryStatus,
      handlePaginationChanged,
      handleQueryBtn,
      ListEnum,
      list,
      listEngr,
      listIden,
      listSave,
      toEditView,
      clear,
      submitForm,
      EngManage,
      FormStatusEnum,
      data,
      putSaveForm,
    }
  }
}
</script>
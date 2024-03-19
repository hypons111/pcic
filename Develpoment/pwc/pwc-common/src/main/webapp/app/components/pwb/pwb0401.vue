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
              <!-- 權限類別 -->
              <i-form-group-check :label="$t('label.roleType') + '：'">
                <b-form-select v-model="$v.roleType.$model" :options="formOptions.roleType">
                  <template #first>
                    <option value="">全部</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <!-- 模組 -->
              <i-form-group-check :label="$t('label.moduleType') + '：'">
                <b-form-select v-model="$v.moduleType.$model" :options="formOptions.moduleType">
                  <template #first>
                    <option value="">全部</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 審核人員 -->
              <i-form-group-check :label="$t('label.reviewStaff') + '：'">
                <b-form-select v-model="$v.userId.$model" :options="formOptions.userId">
                  <template #first>
                    <option value="">全部</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
              <span>
                <input class="form-control w-50" disabled v-model="$v.chName.$model" />
              </span>
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="clickQueryBtn"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2">
      <i-button type="file-earmark-plus" @click="toCreate"></i-button>
    </section>

    <section class="container mt-2" v-if="queryStatus">
      <i-table
        class="table-sm"
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >
        <template #cell(action)="row">
          <!-- 檢視 -->
          <i-button size="sm" type="binoculars" @click="toReview(row.item)"></i-button>
          <!-- 編輯 -->
          <i-button size="sm" type="pencil-square" @click="toEdit(row.item)"> </i-button>
          <!-- 刪除 -->
          <i-button size="sm" type="trash" @click="toDelete(row.item)"></i-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, reactive, ref, watch } from '@vue/composition-api';
import { Pagination } from '@/shared/model/pagination.model';
import { useValidation } from '@/shared/form';,
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import i18n from '@/shared/i18n';
import axios from 'axios';


export default {
  name: 'pwb0401',
  setup() {

    onMounted(() => {
      getAllSysCodes();
      getApproveUserId();
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      roleType: '', //模組類別
      moduleType: '', //模組名稱
      userId: '', //審核人員
      chName: '', //審核人員(姓名)
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      roleType: {},
      moduleType: {},
      userId: {},
      chName: {}
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    const table = reactive({
      fields: [
        {
          // 權限類別
          key: 'roleType',
          label: i18n.t('label.roleType'),
          formatter: (value: string) => formOptionsFormatter(formOptions.roleType, value),
        },
        {
          // 模組
          key: 'moduleType',
          label: i18n.t('label.moduleType'),
          formatter: (value: string) => formOptionsFormatter(formOptions.moduleType, value),
        },
        {
          // 業務別
          key: 'businessType',
          label: i18n.t('label.businessType'),
        },
        {
          // 審核人員
          key: 'userId',
          label: i18n.t('label.reviewStaff'),
        },
        {
          key: 'action',
          label: '',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const formOptions = reactive({
      roleType: [
        { value: '00', text: '管理者帳號' },
        { value: '01', text: '個人帳號' },
      ],
      moduleType: [],
      userId: [],
      chName: [
        { value: '', text: ''}
      ]
    });

    // 查詢模組代號(ADM_SYS_CODE.DATA_KEY)所對應的中文名稱(ADM_SYS_CODE.VALUE)
    function getAllSysCodes(): void {
      axios
        .get('/service/adm-sys-codes')
        .then(({ data }) => {
            formOptions.moduleType.push(
              ...data
                .filter(item => item['moduleType'] === 'ADM' && item['dataType'] === 'MODULE_TYPE')
                .map(item => {
                  return { value: item['dataKey'], text: item['value'] };
                })
            );
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 查詢所有審核人員(PWB_ROLE_APPROVE.USER_ID)
    function getApproveUserId() {
      axios
        .post('/pwb-role-approve/getApproveUserId')
        .then(({ data }) => {
            formOptions.userId.push(
              ...data
                .map(item => {
                  return { value: item['userId'], text: item['userId'] };
                })
            );
            formOptions.chName.push(
              ...data
                .map(item => {
                  return { value: item['userId'], text: item['chName'] };
                })
            );
          }
        )
        .catch(notificationErrorHandler(notificationService));
    }

    watch(
      () => form.userId,
      newValue => {
         formOptions.chName.forEach(item => {
          if(item.value == newValue){
            form.chName = item.text;
          }
         })
      }
    );


    const iTableComponent = ref(null);

    const clickQueryBtn = () => {
      if (iTableComponent.value) iTableComponent.value.state.pagination.currentPage = 1;
      handleQuery();
    };

    const notificationService: NotificationService = useNotification();

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          queryStatus.value = true;
          axios
            .post('/pwb-role-approve/getApproveList', form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    //新增
    const toCreate = () => {
      const itemDetails = {
        webStatus: ''
      }
      itemDetails.webStatus = 'create';
      navigateByNameAndParams('pwb0401EditInfo', { editMsgDetail: itemDetails, isNotKeepAlive: true });
    };

    //編輯
    const toEdit = (itemDetail) => {
      itemDetail.webStatus = 'modify';
      navigateByNameAndParams('pwb0401EditInfo', { editMsgDetail: itemDetail, isNotKeepAlive: true });
    };

    //檢視
    const toReview = (itemDetail) => {
      itemDetail.webStatus = 'review';
      navigateByNameAndParams('pwb0401EditInfo', { editMsgDetail: itemDetail, isNotKeepAlive: true });
    };

    // modal物件
    const $bvModal = useBvModal();

    //顯示刪除b-modal
    const toDelete = (itemDetail) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否刪除').then((isOK: boolean) => {
            if (isOK) {
              deleteForm(itemDetail);
            }
          });
        }
      });
    };

    //刪除
    function deleteForm(itemDetail){
      let id = itemDetail.id;
      axios.delete('/pwb-role-approves/' + id)
           .then(() => {
              handleQuery();
            });
    }




    return {
      formDefault,
      form,
      rules,
      $v,
      reset,
      stepVisible,
      queryStatus,
      table,
      formOptions,
      clickQueryBtn,
      handleQuery,
      handlePaginationChanged,
      iTableComponent,
      formOptionsFormatter,
      toCreate,
      toEdit,
      toReview,
      toDelete
    };
  },
};
</script>

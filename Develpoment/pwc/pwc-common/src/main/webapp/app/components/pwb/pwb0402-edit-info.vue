<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-body col-11">
          <b-form-row>
            <!-- 類別 -->
            <i-form-group-check :label="$t('label.jobKind') + '：'" v-if="form.webStatus != 'create'">
              <span class="h-100 d-flex align-items-center flex-wrap">{{
                formOptionsFormatter(formOptions.roleType, $v.roleType.$model)
              }}</span>
            </i-form-group-check>
            <i-form-group-check labelStar :label="$t('label.jobKind') + '：'" v-else>
              <b-form-select v-model="$v.roleType.$model" :options="formOptions.roleType" @change="getRoleIdByModule"> </b-form-select>
            </i-form-group-check>

            <!-- 模組 -->
            <i-form-group-check :label="$t('label.moduleType') + '：'" v-if="form.webStatus != 'create'">
              <span class="h-100 d-flex align-items-center flex-wrap">{{
                formOptionsFormatter(formOptions.moduleType, $v.moduleType.$model)
              }}</span>
            </i-form-group-check>
            <i-form-group-check labelStar :label="$t('label.moduleType') + '：'" v-else>
              <b-form-select v-model="$v.moduleType.$model" :options="formOptions.moduleType" @change="getRoleIdByModule"> </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 業務別 -->
            <i-form-group-check :label="$t('label.businessType') + '：'" v-if="form.webStatus != 'create'">
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.businessType.$model }}</span>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.businessType') + '：'" v-else>
              <b-form-select v-model="$v.businessType.$model" :options="formOptions.businessType" @change="getRoleIdByModule">
              </b-form-select>
            </i-form-group-check>

            <!-- 核可層級 -->
            <i-form-group-check :label="$t('label.approveLevel') + '：'" v-if="form.webStatus == 'review'">
              <span class="h-100 d-flex align-items-center flex-wrap">{{
                formOptionsFormatter(formOptions.approveLevel, $v.approveLevel.$model)
              }}</span>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.approveLevel') + '：'" v-else>
              <b-form-select v-model="$v.approveLevel.$model" :options="formOptions.approveLevel" @change="getRoleIdByModule">
              </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <i-button class="mb-3" type="file-earmark-plus" @click="addNewRow" v-show="form.webStatus !== 'review'"></i-button>

          <section class="container mt-2">
            <b-table
              class="table-sm mb-3"
              :items="table.data"
              :fields="table.fields"
              show-empty
              empty-text="查無資料，請修改條件後再重新查詢一次"
              bordered
            >
              <template #cell(roleId)="row" v-if="form.webStatus !== 'review'">
                <b-form-select v-model="row.item.roleId" @change="changeRoleName(row.item)" :options="formOptions.roleId"> </b-form-select>
              </template>
              <template #cell(roleId)="row" v-else>
                <span>{{ row.item.roleId }}</span>
              </template>

              <template #cell(action)="{ index }">
                <!-- 刪除 -->
                <i-button size="sm" type="trash" @click="deleteRoleId(index)" :disabled="form.webStatus == 'review'"></i-button>
              </template>
            </b-table>
          </section>

          <div class="text-center mb-3">
            <!-- 儲存 -->
            <i-button type="save" @click="toSave" v-show="form.webStatus !== 'review'"></i-button>
            <!-- 刪除 -->
            <i-button type="trash" @click="toDelete" v-show="form.webStatus == 'modify'"></i-button>
            <!-- 上一頁 -->
            <i-button type="arrow-bar-left" @click="toQueryView"></i-button>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, reactive, ref, watch, toRefs } from '@vue/composition-api';
import { useValidation } from '@/shared/form';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { useBvModal } from '@/shared/modal';
import { handleBack } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import i18n from '@/shared/i18n';

export default {
  name: 'pwb0402EditInfo',
  props: {
    editMsgDetail: {
      type: Object,
      required: false,
    },
  },
  setup(props) {
    onMounted(() => {
      getAllSysCodes();
      getRoleIdByModule();
    });

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      moduleType: '', //模組名稱
      businessType: '', //業務別
      approveLevel: '', //核可層級
      webStatus: '', //判斷是從新增or編輯or檢視點進來
      roleId: '', //角色代號
      roleType: '', //類別
      table: [],
      updateTime: null,
      updateUser: '',
      createUser: '',
      createTime: null,
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      moduleType: {},
      approveLevel: {},
      businessType: {},
      roleType: {},
      table: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const formOptions = reactive({
      moduleType: [],
      businessType: [
        { value: '', text: '' },
        { value: '地方機關', text: '地方機關' },
        { value: '公會', text: '公會' },
        { value: '顧問公司', text: '顧問公司' },
        { value: '全聯會', text: '全聯會' },
      ],
      approveLevel: [
        { value: '', text: '' },
        { value: '1', text: '工程會' },
        { value: '2', text: '主管機關' },
      ],
      roleId: [],
      roleType: [
        { value: 'I', text: '個人' },
        { value: 'A', text: '機關管理者' },
        { value: 'C', text: '單位管理者' },
      ],
      roleName: [],
    });

    const notificationService: NotificationService = useNotification();

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

    const table = reactive({
      fields: [
        {
          // 角色代號
          key: 'roleId',
          label: i18n.t('label.roleCode'),
        },
        {
          // 角色描述
          key: 'roleName',
          label: i18n.t('label.roleName'),
        },
        {
          key: 'action',
          label: '',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const editMsgDetailProp = toRefs(props).editMsgDetail;

    watch(
      editMsgDetailProp,
      () => {
        if (editMsgDetailProp.value) {
          createDefaultValue(editMsgDetailProp.value);
        }
      },
      { immediate: true }
    );

    function createDefaultValue(data) {
      Object.assign(form, data);
    }

    // PWB_ROLE_MODULE.ROLE_TYPE & MODULE_TYPE & BUSINESS_TYPE & APPROVE_LEVEL為條件查詢PWB_ROLE_MODULE.ROLE_ID
    function getRoleIdByModule(): void {
      axios
        .post('/pwb-role-modules/getRoleIdByModule', form)
        .then(({ data }) => {
          table.data = data.slice(0, data.length); //前端分頁
          table.totalItems = data.length; //前端分頁
        })
        .catch(notificationErrorHandler(notificationService));
      getAllRoleId();
    }

    // 查詢PWB_ROLE_MODULE.MODULE_TYPE = ADM_ROLE.BUSINESS_CATEGORY的ADM_ROLE.ROLE_ID和ADM_ROLE.ROLE_NAME
    function getAllRoleId(): void {
      axios
        .post('/pwb-role-modules/getAllRoleId', form)
        .then(({ data }) => {
          formOptions.roleId = [];
          formOptions.roleId.push(
            ...data.map(item => {
              return { value: item['roleId'], text: item['roleId'] };
            })
          );
          formOptions.roleName = [];
          formOptions.roleName.push(
            ...data.map(item => {
              return { value: item['roleId'], text: item['roleName'] };
            })
          );
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function changeRoleName(item) {
      item.roleName = formOptions.roleName.find(i => i.value == item.roleId).text;
    }

    let isReload = false;

    function toQueryView() {
      handleBack({ isReload: isReload, isNotKeepAlive: true });
      isReload = false;
    }

    //新增新空白欄
    function addNewRow() {
      let newC = {
        roleId: '',
        roleName: '',
      };
      table.data.push(newC);
    }

    //取得登入帳號(PWB_ROLE_APPROVE.USER_ID)
    const loginUserId = ref(useGetters(['account']).account.value.id);

    //儲存
    function toSave() {
      let roleIdList = [];
      let duplicateNum = 0;
      //檢查要儲存的roleId是否有重複
      for (let i = 0; i < table.data.length; i++) {
        let roleId = table.data[i].roleId;
        if (roleIdList.indexOf(roleId) == -1) {
          roleIdList.push(roleId);
        } else {
          duplicateNum++;
        }
      }

      if (duplicateNum != 0) {
        $bvModal.msgBoxOk('角色代號不可重複!');
      } else {
        form.table = table.data;
        form.createUser = loginUserId;
        form.createTime = new Date();
        form.updateUser = loginUserId;
        form.updateTime = new Date();
        let url = '/pwb-role-modules/saveRoleId';
        axios.post(url, form).then(() => {
          toQueryView();
        });
      }
    }

    //modal物件
    const $bvModal = useBvModal();

    //刪除table中的row
    function deleteRoleId(dataIndex: number) {
      $bvModal.msgBoxConfirm('是否確認刪除此筆角色代號？').then((isOK: boolean) => {
        if (isOK) {
          table.data.splice(dataIndex, 1);
        }
      });
    }

    //顯示是否刪除的b-modal
    function toDelete() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否刪除').then((isOK: boolean) => {
            if (isOK) {
              deleteForm();
            }
          });
        }
      });
    }

    //刪除
    function deleteForm() {
      axios.post('/pwb-role-modules/deleteModule', form).then(() => {
        toQueryView();
      });
    }

    return {
      formDefault,
      form,
      rules,
      $v,
      reset,
      formOptionsFormatter,
      formOptions,
      toQueryView,
      addNewRow,
      deleteRoleId,
      table,
      changeRoleName,
      toSave,
      toDelete,
      getRoleIdByModule,
    };
  },
};
</script>

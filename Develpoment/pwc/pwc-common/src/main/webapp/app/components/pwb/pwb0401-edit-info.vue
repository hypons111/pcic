<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-body col-11">
          <b-form-row>
            <!-- 權限類別 -->
            <i-form-group-check :label="$t('label.roleType') + '：'" v-if="form.webStatus == 'review'">
              <span class="h-100 d-flex align-items-center flex-wrap">{{
                formOptionsFormatter(formOptions.roleType, $v.roleType.$model)
              }}</span>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.roleType') + '：'" v-else>
              <b-form-select v-model="$v.roleType.$model" :options="formOptions.roleType"> </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 模組 -->
            <i-form-group-check :label="$t('label.moduleType') + '：'" v-if="form.webStatus == 'review'">
              <span class="h-100 d-flex align-items-center flex-wrap">{{
                formOptionsFormatter(formOptions.moduleType, $v.moduleType.$model)
              }}</span>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.moduleType') + '：'" v-else>
              <b-form-select v-model="$v.moduleType.$model" :options="formOptions.moduleType"> </b-form-select>
            </i-form-group-check>

            <!-- 業務別 -->
            <i-form-group-check :label="$t('label.businessType') + '：'" v-if="form.webStatus == 'review'">
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.businessType.$model }}</span>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.businessType') + '：'" v-else>
              <b-form-select v-model="$v.businessType.$model" :options="formOptions.businessType"> </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 審核人員 -->
            <i-form-group-check
              class="col-4"
              label-cols="6"
              content-cols="5"
              :label="$t('label.reviewStaff') + '：'"
              v-if="form.webStatus == 'review'"
            >
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.userId.$model }}</span>
            </i-form-group-check>
            <i-form-group-check class="col-4" label-cols="6" content-cols="5" :label="$t('label.reviewStaff') + '：'" v-else>
              <b-form-select v-model="$v.userId.$model" :options="formOptions.userId"> </b-form-select>
            </i-form-group-check>
            <span>
              <input class="form-control w-50" disabled v-model="$v.chName.$model" />
            </span>
          </b-form-row>

          <div class="text-center mb-3">
            <!-- 儲存 -->
            <i-button type="save" @click="toSaveOrCreate" v-show="form.webStatus !== 'review'"></i-button>
            <!-- 清除 -->
            <i-button type="x-circle" @click="reset" v-show="form.webStatus !== 'review'"></i-button>
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

export default {
  name: 'pwb0401EditInfo',
  props: {
    editMsgDetail: {
      type: Object,
      required: false,
    },
  },
  setup(props) {
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
      businessType: '', //業務別
      webStatus: '', //判斷是從新增or編輯or檢視點進來
      updateTime: null,
      updateUser: '',
      createUser: '',
      createTime: null,
      id: '', //此筆的id
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      roleType: {},
      moduleType: {},
      userId: {},
      chName: {},
      businessType: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const formOptions = reactive({
      roleType: [
        { value: '00', text: '管理者帳號' },
        { value: '01', text: '個人帳號' },
      ],
      moduleType: [],
      userId: [],
      chName: [],
      businessType: [
        { value: '地方機關', text: '地方機關' },
        { value: '公會', text: '公會' },
        { value: '顧問公司', text: '顧問公司' },
      ],
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

    // 查詢所有審核人員(PWB_ROLE_APPROVE.USER_ID)
    function getApproveUserId() {
      axios
        .post('/pwb-role-approve/getApproveUserId')
        .then(({ data }) => {
          formOptions.userId.push(
            ...data.map(item => {
              return { value: item['userId'], text: item['userId'] };
            })
          );
          formOptions.chName.push(
            ...data.map(item => {
              return { value: item['userId'], text: item['chName'] };
            })
          );
          getChName();
        })
        .catch(notificationErrorHandler(notificationService));
    }

    watch(() => form.userId, getChName);

    function getChName() {
      formOptions.chName.forEach(item => {
        if (item.value == form.userId) {
          form.chName = item.text;
        }
      });
    }

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

    let isReload = false;

    function toQueryView() {
      handleBack({ isReload: isReload, isNotKeepAlive: true });
      isReload = false;
    }

    // modal物件
    const $bvModal = useBvModal();

    //顯示刪除b-modal
    const toDelete = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否刪除').then((isOK: boolean) => {
            if (isOK) {
              deleteForm();
            }
          });
        }
      });
    };

    //刪除
    function deleteForm() {
      let id = form.id;
      let url = '/pwb-role-approves/' + id;
      axios.delete(url).then(() => {
        toQueryView();
      });
    }

    // 取得登入帳號(PWB_ROLE_APPROVE.USER_ID)
    const loginUserId = ref(useGetters(['account']).account.value.id);

    //判斷是新增OR更新
    function toSaveOrCreate() {
      if (form.id == null || form.id == '') {
        toCreate();
      } else {
        toSave();
      }
    }

    //更新
    function toSave() {
      form.updateTime = new Date();
      form.updateUser = loginUserId;
      console.log('saveForm', form);
      let id = form.id;
      let url = '/pwb-role-approves/' + id;
      axios.put(url, form).then(() => {
        toQueryView();
      });
    }

    //新增
    function toCreate() {
      form.updateTime = new Date();
      form.updateUser = loginUserId;
      form.createUser = loginUserId;
      form.createTime = new Date();
      let url = '/pwb-role-approves';
      axios.post(url, form).then(() => {
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
      toDelete,
      toSaveOrCreate,
    };
  },
};
</script>

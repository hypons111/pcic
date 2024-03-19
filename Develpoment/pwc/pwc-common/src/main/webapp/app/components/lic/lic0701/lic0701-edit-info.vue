<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0" v-if="formStatusForwardProp === formStatusEnum.MODIFY">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          編輯工作小組成員
        </h5>
        <h5 class="m-0" v-else-if="formStatusForwardProp === formStatusEnum.CREATE">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          新增工作小組成員
        </h5>
      </div>
      <div class="card-body col-10">
        <div>
          <div>
            <b-form-row>
              <i-form-group-check :labelStar="true" label="計畫名稱:" :item="$v.licProjectBasicId">
                <b-form-select v-model.trim="$v.licProjectBasicId.$model" :options="queryOptions.licProjectBasicName" :state="validateState($v.licProjectBasicId)"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :labelStar="true" label="姓名:" :item="$v.memberName">
                <b-form-input v-model.trim="$v.memberName.$model" :state="validateState($v.memberName)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :labelStar="true" label="職稱:" :item="$v.memberTitle">
                <b-form-input v-model="$v.memberTitle.$model" :state="validateState($v.memberTitle)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check :labelStar="true" label="機關名稱:" :item="$v.orgId">
                <b-form-select v-model.trim="$v.orgId.$model" :options="queryOptions.orgIdList" :state="validateState($v.orgId)"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :labelStar="true" label="機關屬性:" :item="$v.orgAttr">
                <b-form-select v-model.trim="$v.orgAttr.$model" :options="queryOptions.orgAttrList" :state="validateState($v.orgAttr)"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <p>
            <table width="100%">
                  <tr>
                    <td width="40%">
                      <b-form-file v-model="licenseFile" @change="licenseSelected" plain></b-form-file>
                    </td>
                    <td width="60%">
                      <b-button class="p-1" variant="info" @click="uploadLicenseFile">上傳</b-button>
                    </td>
                  </tr>
            </table>
            <div>
              <table border="2" cellpadding="2" width="100%">
                <tr>
                  <th width="10%" class="text-center">序號</th>
                  <th width="70%" class="text-center">涉及證照項目</th>
                  <th width="20%" class="text-center">動作</th>
                </tr>
                <tr v-for="(item,key) in table1.data">
                  <td style="vertical-align : middle" class="text-center">{{key+1}}</td>
                  <td style="vertical-align : middle">{{item.licWorkshopLicenseId}}</td>
                  <td>
                    <i-button class="ml-1" type="trash" @click="deleteLicense"></i-button>
                    <i-button class="ml-1" type="binoculars" @click="toEditView(row.item)"></i-button>
                  </td>
                </tr>
              </table>
            </div>
            <div class="button-float-right">
              <b-button-toolbar class="mt-5" v-if="formStatusRef === 'modify'">
                <i-button class="ml-1" type="save" @click="submitForm('modify')"></i-button>
                <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
                <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
                <i-button class="ml-1" type="trash" @click="deleteMember"></i-button>
              </b-button-toolbar>
              <b-button-toolbar class="mt-5" v-else-if="formStatusRef === 'create'">
                <i-button class="ml-1" type="plus-circle" @click="submitFormNext"></i-button>
                <i-button class="ml-1" type="save" @click="submitForm('create')"></i-button>
                <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
                <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler, notificationResponseHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { FormStatusEnum } from '@/shared/enum';

export default {
  name: 'lic0701-edit-info',
  props: {
    formStatusForward: {
      type: String,
      required: true,
    },
    licWorkshopMember: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const licWorkshopMemberProp = toRefs(props).licWorkshopMember;

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    //空值表單
    const cleanTable = {
      memberName: '',
      memberTitle: '',
      orgId: '',
      orgAttr: '',
      licProjectBasicId: '',
      createUser: '',
      createTime: undefined,
      updateUser: '',
      updateTime: undefined,
    };

    //預設值
    const formDefault = Object.assign({}, cleanTable);

    //預存好的form
    const form = reactive(Object.assign({}, formDefault));

    //檢核規則
    const rules = {
      memberName: {notNull: required},
      memberTitle: {notNull: required},
      orgId: {notNull: required},
      orgAttr: { notNull: required },
      licProjectBasicId: { notNull: required },
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //是否要重新查詢
    const isReload: boolean = ref(null);
    //modal物件
    const $bvModal = useBvModal();

    const table1 = reactive({data: undefined,});
    table1.data = [
      {
        licWorkshopLicenseId: '大地技師證照',
      },
      {
        licWorkshopLicenseId: '環工證照',
      },
      {
        licWorkshopLicenseId: '水利證照',
      },
      {
        licWorkshopLicenseId: '工地安全證照',
      },
    ];

    // 下拉選單選項
    const queryOptions = reactive({
      orgIdList: [
        { value: '', text: '全部' },
        { value: '11111111', text: '11111111：台灣中油股份有限公司興建工程處' },
        { value: '22222222', text: '22222222：交通部公路總局第三區養護工程處' },
        { value: '33333333', text: '33333333：新北市政府工程局' },
        { value: '44444444', text: '44444444：臺灣港務股份有限公司基隆港務分公司' },
      ],
      orgAttrList: [
        { value: '', text: '全部' },
        { value: '1', text: '1：主辦機關' },
        { value: '2', text: '2：審證機關' },
      ],
      licProjectBasicName: [
        { value: '', text: '全部' },
        { value: 'L10801', text: 'L10801：天然氣事業部永安廠增建儲糟投資計畫' },
        { value: 'L10802', text: 'L10802：台9線花東縱谷公路安全景觀大道計畫(臺東段)' },
        { value: 'L10803', text: 'L10803：臺北都會區大眾捷運系統三鶯線建設計畫' },
      ],
    });

    //返回查詢畫面
    const toQueryView = () => {
      changeFormStatus(formStatusEnum.CREATE);
      createDefaultValue(cleanTable);
      reset();
      const param = { isReload: isReload.value };
      handleBack(param);
    };

    //創建初始值
    const createDefaultValue = (data: any) => {
      Object.assign(formDefault, data);
      reset();
    };

    //變更表單操作狀態，當form狀態不為查看時重置
    const changeFormStatus = (status: formStatusEnum) => {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    //修改表單狀態
    const changeformStatusForwardPropEdit = () => {
      formStatusRef.value = formStatusEnum.MODIFY;
    };

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;

    //新增&修改送出前檢查訊息
    const submitForm = (submitMethod: string) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (submitMethod === 'modify') {
            //提交修改
            putForm();
            $bvModal.msgBoxOk('資料異動成功');
          }
          if (submitMethod === 'create') {
            //提交新增
            insertTyphoon(true);
            $bvModal.msgBoxOk('資料新增成功');
            changeFormStatus(formStatusEnum.MODIFY); //改成修改按鈕
          }
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //繼續新增送出前檢查
    const submitFormNext = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //提交新增
          insertTyphoon(false);
          $bvModal.msgBoxOk('資料新增成功');
          changeFormStatus(formStatusEnum.CREATE);
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //修改
    const putForm = () => {
      form.updateUser = userIdentity;
      form.updateTime = new Date();
      let url: string;
      if (formStatusRef.value === formStatusEnum.MODIFY) {
        url = `/rec-typhoons/${form.typhoonNo}`;
      }
      axios
        .put(url, form)
        .then(({ data }) => {
          //填入值
          createDefaultValue(data);
          isReload.value = true;
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    };

    //刪除工作小組成員檔
    const deleteMember = () => {
      $bvModal.msgBoxConfirm('刪除工作小組成員檔（單筆），確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          let url: string;
          url = `/rec-typhoons/${form.typhoonNo}`;
          axios
            .delete(url)
            .then(toQueryView())
            .then($bvModal.msgBoxOk('資料刪除成功'))
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    //刪除工作小組成員證照檔
    const deleteLicense = () => {
      $bvModal.msgBoxConfirm('刪除工作小組成員證照檔（單筆），確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          let url: string;
          url = `/rec-typhoons/${form.typhoonNo}`;
          axios
            .delete(url)
            .then(toQueryView())
            .then($bvModal.msgBoxOk('資料刪除成功'))
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    //新增
    const insertTyphoon = (insertDone: boolean) => {
      //4個系統用欄位
      form.createUser = userIdentity;
      form.updateUser = userIdentity;
      form.createTime = new Date();
      form.updateTime = new Date();
      axios
        .post(`/rec-typhoons`, form)
        .then(({ data }) => {
          isReload.value = true;
          if (insertDone) {
            createDefaultValue(data); //不再新增時將值放入
          }
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    };

    //傳入狀態
    const formStatusForwardProp = toRefs(props).formStatusForward;

    watch(
      licWorkshopMemberProp,
      () => {
        if (formStatusForwardProp.value === 'modify') {
          createDefaultValue(licWorkshopMemberProp.value);
          changeFormStatus(formStatusEnum.MODIFY);
        } else {
          Object.assign(formDefault, cleanTable);
          Object.assign(form, formDefault);
          createDefaultValue(form);
          changeFormStatus(formStatusEnum.CREATE);
        }
      },
      { immediate: true }
    );

    const licenseFile: Ref<File> = ref(null);
    const picPreview = ref(null);

    function licenseSelected(e) {
      if (e.target.files.length !== 0) {
        const fileReader = new FileReader();
        fileReader.onload = e => {
          picPreview.value = e.target.result;
        };
        fileReader.readAsDataURL(e.target.files[0]);
      } else {
        picPreview.value = null;
      }
    }

    function uploadLicenseFile() {
      console.log(formStatusRef.value);
      const param = new FormData();
      param.append('file', licenseFile.value);
      axios
        .put('/rec-area/upload', param)
        .then(() => {
          notificationService.success(`上傳成功`);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    return {
      $v,
      formStatusEnum,
      formStatusRef,
      queryOptions,
      changeFormStatus,
      reset,
      validateState,
      toQueryView,
      submitForm,
      submitFormNext,
      deleteMember,
      deleteLicense,
      formStatusForwardProp,
      userIdentity,
      changeformStatusForwardPropEdit,
      table1,
      licenseFile,
      picPreview,
      licenseSelected,
      uploadLicenseFile,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

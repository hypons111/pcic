<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0" v-if="formStatusRef === formStatusEnum.INFO">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            檢視工程會帳號
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.CREATE">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            新增工程會帳號
          </h5>
          <h5 class="h5 m-0" v-else-if="formStatusRef === formStatusEnum.MODIFY">
            <font-awesome-icon icon="edit"></font-awesome-icon>
            修改工程會帳號
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check :label="$t('label.adm004.userId') + '：'" v-if="formStatusRef === formStatusEnum.CREATE">
                <div class="h-100 d-flex align-items-center">
                  {{ '系統自動帶出' }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm004.userId') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.userId.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm004.chName') + '：'" :item="$v.chName" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model="$v.chName.$model" type="text" :state="validateState($v.chName)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm004.chName') + '：'" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.chName.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm004.unitId') + '：'" :item="$v.unitId" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-select
                  v-model="$v.unitId.$model"
                  :options="options.unitOpts"
                  :state="validateState($v.unitId)"
                  @change="userIdChange()"
                >
                </b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm004.unitId') + '：'" :item="$v.unitId" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.unitId.$model | unitFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm004.status') + '：'" :item="$v.status" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-select v-model="$v.status.$model" :options="options.statusOpts" :state="validateState($v.status)"> </b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm004.status') + '：'" :item="$v.status" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.status.$model | statusFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm004.email') + '：'" :item="$v.email" v-if="formStatusRef !== formStatusEnum.INFO">
                <div class="h-100 d-flex align-items-center">
                  <b-form-input v-model="$v.email.$model" type="text" :state="validateState($v.email)"></b-form-input>
                  <b-form-invalid-feedback v-for="(error, index) of $v.email.$errors" :key="'email-' + index">
                    {{ error.$message }}
                  </b-form-invalid-feedback>
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm004.email') + '：'" :item="$v.email" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.email.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm004.title') + '：'" :item="$v.title" v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model="$v.title.$model" :state="validateState($v.title)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm004.title') + '：'" :item="$v.title" v-else>
                <div class="h-100 d-flex align-items-center">
                  {{ $v.title.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-group
              v-if="formStatusRef !== formStatusEnum.INFO"
              :label="$t('label.adm004.workTel') + '：'"
              :class="{ 'form-check-with-marginB': true, 'col-6': false }"
              :label-cols="2"
              :content-cols="10"
              :label-align="'right'"
              label-class="label"
            >
              <b-form-row>
                <b-col cols="3">
                  <b-input-group prepend="區碼">
                    <b-form-input
                      v-model="$v.areaNumber.$model"
                      type="text"
                      placeholder="如:02"
                      :state="validateState($v.areaNumber)"
                    ></b-form-input>
                    <b-form-invalid-feedback v-for="(error, index) of $v.areaNumber.$errors" :key="'areaNumber-' + index">
                      {{ error.$message }}
                    </b-form-invalid-feedback>
                  </b-input-group>
                </b-col>
                <b-col cols="5">
                  <b-input-group prepend="電話號碼">
                    <b-form-input
                      v-model="$v.telNumber.$model"
                      type="text"
                      placeholder="如:22551124"
                      :state="validateState($v.telNumber)"
                    ></b-form-input>
                    <b-form-invalid-feedback v-for="(error, index) of $v.telNumber.$errors" :key="'telNumber-' + index">
                      {{ error.$message }}
                    </b-form-invalid-feedback>
                  </b-input-group>
                </b-col>
                <b-col cols="4">
                  <b-input-group prepend="分機號碼">
                    <b-form-input
                      v-model="$v.extendNumber.$model"
                      type="text"
                      placeholder="如:1234"
                      :state="validateState($v.extendNumber)"
                    ></b-form-input>
                    <b-form-invalid-feedback v-for="(error, index) of $v.extendNumber.$errors" :key="'extendNumber-' + index">
                      {{ error.$message }}
                    </b-form-invalid-feedback>
                  </b-input-group>
                </b-col>
              </b-form-row>
            </b-form-group>

            <b-form-group
              v-if="formStatusRef !== formStatusEnum.INFO"
              :label="$t('label.adm004.mobileTel') + '：'"
              :class="{ 'form-check-with-marginB': true, 'col-6': false }"
              :label-cols="2"
              :content-cols="10"
              :label-align="'right'"
              label-class="label"
            >
              <b-form-row>
                <b-col cols="">
                  <b-input-group prepend="前4碼">
                    <b-form-input
                      v-model="$v.mobileTel4.$model"
                      type="text"
                      placeholder="如:0933"
                      :state="validateState($v.mobileTel4)"
                    ></b-form-input>
                    <b-form-invalid-feedback v-for="(error, index) of $v.mobileTel4.$errors" :key="'mobileTel4-' + index">
                      {{ error.$message }}
                    </b-form-invalid-feedback>
                  </b-input-group>
                </b-col>
                <b-col cols="">
                  <b-input-group prepend="後6碼">
                    <b-form-input
                      v-model="$v.mobileTel6.$model"
                      type="text"
                      placeholder="如:112233"
                      :state="validateState($v.mobileTel6)"
                    ></b-form-input>
                    <b-form-invalid-feedback v-for="(error, index) of $v.mobileTel6.$errors" :key="'mobileTel6-' + index">
                      {{ error.$message }}
                    </b-form-invalid-feedback>
                  </b-input-group>
                </b-col>
              </b-form-row>
            </b-form-group>

            <b-form-row v-else>
              <i-form-group-check :label="$t('label.adm004.workTel') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.workTel.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm004.mobileTel') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.mobileTel.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-group
              v-if="formStatusRef !== formStatusEnum.INFO"
              :label="$t('label.adm004.roleIdList') + '：'"
              :class="{ 'form-check-with-marginB': true, 'col-6': false }"
              :label-cols="2"
              :content-cols="10"
              :label-align="'right'"
              label-class="label"
            >
              <dual-list-box
                :source="roles.source"
                :destination="roles.destination"
                label="name"
                @onChangeList="onChangeList"
              ></dual-list-box>
              <b-form-invalid-feedback v-for="(error, index) of $v.roleIdList.$errors" :key="'roleIdList-' + index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>

            <b-form-row v-else>
              <i-form-group-check :label="$t('label.adm004.roleIdList') + '：'">
                <div class="h-100 d-flex align-items-center">
                  <b-table
                    :empty-text="'查無資料'"
                    :items="innerRoleIdList"
                    :fields="innerTable.fields"
                    show-empty
                    class="table table-sm"
                    head-variant="light"
                  >
                    <template #cell(action)="data">
                      <i-button class="mb-1" size="sm" type="binoculars" @click="toRolesInfo(data.item.roleId)"></i-button>
                    </template>
                  </b-table>
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm004.updateUser') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ formDefault.updateUser }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm004.updateTime') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ formDefault.updateTime | dateTimeFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-button-toolbar v-show="formStatusRef === formStatusEnum.INFO" class="float-right">
              <i-button class="mr-1" type="pencil-square" @click="changeFormStatus(formStatusEnum.MODIFY)"></i-button>
              <i-button class="mr-1" type="arrow-left" @click="toQueryView()"></i-button>
            </b-button-toolbar>

            <b-button-toolbar v-show="formStatusRef !== formStatusEnum.INFO" class="float-right">
              <i-button class="mr-1" type="save" @click="submitForm()"></i-button>
              <i-button class="mr-1" type="x-circle" @click="checkReset()"></i-button>
              <i-button v-if="formStatusRef === formStatusEnum.MODIFY" class="mr-1" type="arrow-left" @click="back()"></i-button>
              <i-button v-if="formStatusRef === formStatusEnum.CREATE" class="mr-1" type="arrow-left" @click="toQueryView()"></i-button>
            </b-button-toolbar>
          </b-form>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onMounted, reactive, ref, toRefs, watch } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useBvModal } from '@/shared/modal';
import { areaNumber, email, extendNumber, maxLength, mobileTel4, mobileTel6, required, telNumber } from '@/shared/validators';
import { accountStore } from '@/shared/config/store/account-store';
import { AdmAccountContact } from '@/shared/model/adm-account-contact.model';
import { dateTimeFormatter, pccUnitFormatter, statusFormatter, userTypeFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { formatDate } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Adm004EditInfo',
  props: {
    userId: {
      type: String,
      required: true,
    },
    formStatus: {
      type: String,
      required: true,
    },
  },
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef = ref(formStatusEnum.INFO);

    // modal物件
    const $bvModal = useBvModal();
    // 建立表單物件預設值
    let formDefault: AdmAccountContact = new AdmAccountContact().setCreateDefault();
    const isReload = ref(false);
    // 建立一份新的表單物件
    const form = reactive(Object.assign({}, formDefault));
    // 使用者資訊
    const userUnit = computed(() => {
      let account = context.root.$store.getters.account;
      if (account) {
        return account.unit;
      } else {
        return '';
      }
    });

    // @ts-ignore
    const userIdentity = accountStore.state.userIdentity;

    // 表單物件驗證規則
    const rules = {
      userId: { notNull: required },
      status: { notNull: required },
      auditStatus: { notNull: required },
      userType: { notNull: required },
      pccOrgId: { notNull: required },
      unitId: { notNull: required },
      roleIdList: { notNull: required },
      chName: { notNull: required, maxLength: maxLength(50) },
      title: { maxLength: maxLength(60) },
      workTel: {},
      areaNumber: { notNull: required, areaNumber: areaNumber(computed(() => form.areaNumber)) },
      telNumber: { notNull: required, telNumber: telNumber(computed(() => form.telNumber)) },
      extendNumber: { extendNumber: extendNumber(computed(() => form.extendNumber)) },
      mobileTel: {},
      mobileTel4: { notNull: required, mobileTel4: mobileTel4(computed(() => form.mobileTel4)) },
      mobileTel6: { notNull: required, mobileTel6: mobileTel6(computed(() => form.mobileTel6)) },
      email: { notNull: required, email: email, maxLength: maxLength(100) },
    };

    onMounted(() => {
      getAllUnitOpts();
      getAllRoles();
    });

    const allUnitOpts = ref([]);

    function getAllUnitOpts() {
      axios
        .get('/adm-units/get-unit-opts')
        .then(({ data }) => {
          allUnitOpts.value.splice(0, allUnitOpts.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    const notificationService: NotificationService = useNotification();

    const allRolesProp = ref([]);
    const checked = ref([]);

    let roles = reactive({
      source: [],
      destination: [],
    });

    let innerRoleIdList = computed(() => {
      const resultList = [];
      form.roleIdListDisplay.forEach(item => {
        const roleId = {};
        roleId['roleId'] = item;
        resultList.push(roleId);
      });
      return resultList;
    });

    watch(allRolesProp, () => {
      const options = allRolesProp.value.map(data => {
        return { code: data['roleId'], name: data['roleName'] };
      });
      const source = options.filter(option => isInLeft(option));
      roles.source.splice(0, roles.source.length, ...source);
      const destination = options.filter(option => !isInLeft(option));
      roles.destination.splice(0, roles.destination.length, ...destination);
    });

    function isInLeft(option) {
      if (checked.value.length) {
        return checked.value.filter(c => c === option.code).length <= 0;
      }
      return true;
    }

    const options = reactive({
      statusOpts: [
        { value: '', text: '請選擇' },
        { value: 'N', text: '停用' },
        { value: 'Y', text: '啟用' },
      ],
      unitOpts: [],
    });

    function onChangeList({ source, destination }) {
      roles.source = source;
      roles.destination = destination;
    }

    // 建立source，查全部角色選項並去除已選的角色
    function getAllRoles() {
      axios
        .get(`/adm-role/get-role-opts`)
        .then(({ data }) => {
          allRolesProp.value.splice(0, allRolesProp.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    // 查詢角色詳情
    function toRolesInfo(roleId: string) {
      navigateByNameAndParams('adm004-role-info', { userId: props.userId, roleId: roleId });
    }

    function initUserAccountInfo() {
      // 查整頁資訊
      axios
        .get(`/adm-account/${props.userId}`)
        .then(({ data }) => {
          if (data && data['userId']) {
            sliceWorkTel(data);
            sliceMobileTel(data);
            Object.assign(formDefault, data);
            formDefault.roleIdListDisplay = data.roleIdList.split(',');
            formDefault.roleIdListDisplay.pop();
            Object.assign(form, formDefault);
            checked.value = form.roleIdListDisplay;
            getAllRoles();
            console.log(formDefault);
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    const innerTable = reactive({
      fields: [
        {
          key: 'roleId',
          label: '角色代碼',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        // {
        //   key: 'roleName',
        //   label: '角色名稱',
        //   sortable: true,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        //   thStyle: { width: '8%' },
        // },
        // {
        //   key: 'roleDescript',
        //   label: '角色描述',
        //   sortable: false,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        //   thStyle: { width: '15%' },
        // },
        {
          key: 'action',
          sortable: false,
          label: '動作',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: { width: '5%' },
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    function sliceWorkTel(data: AdmAccountContact) {
      if (data.workTel) {
        data.areaNumber = data.workTel.substr(0, 2);
        if (data.workTel.indexOf('#') == -1) {
          data.telNumber = data.workTel.substr(data.workTel.indexOf('-') + 1);
          data.extendNumber = '';
        } else {
          data.telNumber = data.workTel.slice(data.workTel.indexOf('-') + 1, data.workTel.indexOf('#'));
          data.extendNumber = data.workTel.substr(data.workTel.indexOf('#') + 1);
        }
      }
    }

    function combineTel() {
      if (form.extendNumber.length > 0) {
        form.workTel = form.areaNumber + '-' + form.telNumber + '#' + form.extendNumber;
      } else {
        form.workTel = form.areaNumber + '-' + form.telNumber;
      }
    }

    function sliceMobileTel(data: AdmAccountContact) {
      if (data.mobileTel) {
        data.mobileTel4 = data.mobileTel.substr(0, 4);
        data.mobileTel6 = data.mobileTel.substr(5, 6);
      }
    }

    function combineMobileTel() {
      form.mobileTel = form.mobileTel4 + '-' + form.mobileTel6;
    }

    function setRoleIdList() {
      form['roleIdList'] = roles.destination.map(data => data['code']).join(',');
    }

    function userIdChange() {
      if (formStatusRef.value === formStatusEnum.CREATE) {
        form.userId = '3.60.' + form.unitId + '-';
      }
    }

    // 返回列表
    function toQueryView() {
      navigateByNameAndParams('adm004', { isReload: isReload.value });
    }

    function back() {
      initUserAccountInfo();
      changeFormStatus(formStatusEnum.INFO);
    }

    // 變更表單操作狀態
    function changeFormStatus(status: formStatusEnum) {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    }

    function submitForm() {
      if (formStatusRef.value === formStatusEnum.CREATE) {
        submitCreation();
      } else if (formStatusRef.value === formStatusEnum.MODIFY) {
        submitModify();
      }
    }

    function submitCreation() {
      form.updateUser = '申請人';
      form.updateTime = new Date();
      // form.pccOrgId = '3.60.' + form.unitId;
      // form.userId = '3.60.' + form.unitId + '-';

      setRoleIdList();
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          combineTel();
          combineMobileTel();
          $bvModal
            .msgBoxConfirm('是否確認新增?', {
              headerBgVariant: 'info',
              headerTextVariant: 'light',
              titleTag: 'h4',
              title: '新增確認',
              headerClass: 'p-2 justify-content-center font-weight-bolder',
              bodyClass: 'h5',
              footerClass: 'justify-content-center',
              okTitle: '確定',
              okVariant: 'info',
              cancelTitle: '取消',
            })
            .then((isOK: boolean) => {
              if (isOK) {
                postCreation();
              }
            });
        } else {
          console.log(form);
          $bvModal.msgBoxOk('請檢查資料是否輸入完畢。', {
            headerBgVariant: 'danger',
            headerTextVariant: 'light',
            titleTag: 'h4',
            title: '資料未輸入完畢',
            headerClass: 'p-2 justify-content-center font-weight-bolder',
            bodyClass: 'h5',
            footerClass: 'justify-content-center',
            okTitle: '確定',
            okVariant: 'info',
          });
        }
      });
    }

    function submitModify() {
      setRoleIdList();
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal
            .msgBoxConfirm('是否確認修改？', {
              headerBgVariant: 'info',
              headerTextVariant: 'light',
              titleTag: 'h4',
              title: '修改確認',
              headerClass: 'p-2 justify-content-center font-weight-bolder',
              bodyClass: 'h5',
              footerClass: 'justify-content-center',
              okTitle: '確定',
              okVariant: 'info',
              cancelTitle: '取消',
            })
            .then((isOK: boolean) => {
              if (isOK) {
                putModify();
              }
            });
        } else {
          $bvModal.msgBoxOk('請檢查資料是否輸入完畢。', {
            headerBgVariant: 'danger',
            headerTextVariant: 'light',
            titleTag: 'h4',
            title: '資料未輸入完畢',
            headerClass: 'p-2 justify-content-center font-weight-bolder',
            bodyClass: 'h5',
            footerClass: 'justify-content-center',
            okTitle: '確定',
            okVariant: 'info',
          });
        }
      });
    }

    // 送出新增表單
    function postCreation() {
      axios
        .post(`/adm-account`, form)
        .then(({ data }) => {
          console.log(data);
          isReload.value = true;
          Object.assign(formDefault, data);
          Object.assign(form, formDefault);
          innerRoleIdList = data.roleIdList.split(',');
          console.log(innerRoleIdList);
          changeFormStatus(formStatusEnum.INFO);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    const isReset = ref(false);

    function checkReset() {
      $bvModal
        .msgBoxConfirm('是否將已輸入資料清除？', {
          headerBgVariant: 'danger',
          headerTextVariant: 'light',
          titleTag: 'h4',
          title: '清除確認',
          headerClass: 'p-2 justify-content-center font-weight-bolder',
          bodyClass: 'h5',
          footerClass: 'justify-content-center',
          okTitle: '確定',
          okVariant: 'info',
          cancelTitle: '取消',
        })
        .then((isOK: boolean) => {
          if (isOK) {
            reset();
            form.roleIdList = '';
            getAllRoles();
            isReset.value = true;
          }
        });
    }

    function changeIsReset(param) {
      isReset.value = param;
    }

    // 送出修改表單
    function putModify() {
      combineTel();
      combineMobileTel();
      if (form.userPadHash !== formDefault.userPadHash) {
        form.padUpdateTime = new Date();
      }
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      axios
        .put(`/adm-account/${form.userId}`, form)
        .then(({ data }) => {
          isReload.value = true;
          Object.assign(formDefault, data);
          navigateByNameAndParams('adm004', { isReload: isReload });
          changeFormStatus(formStatusEnum.INFO);
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    const propsUserId = toRefs(props).userId;
    const propsFormStatus = toRefs(props).formStatus;

    watch(allUnitOpts, () => {
      options.unitOpts = allUnitOpts.value.map(data => {
        return { text: data['unitName'], value: data['unitId'] };
      });
      options.unitOpts.unshift({ text: '請選擇', value: '' });
    });

    watch(
      propsUserId,
      () => {
        if (propsUserId.value !== '_new') {
          initUserAccountInfo();
          if (propsFormStatus.value === formStatusEnum.INFO) {
            changeFormStatus(formStatusEnum.INFO);
          } else {
            changeFormStatus(formStatusEnum.MODIFY);
          }
        } else {
          Object.assign(formDefault, new AdmAccountContact().setCreateDefault());
          formDefault.roleIdListDisplay = [];
          Object.assign(form, formDefault);
          checked.value = form.roleIdListDisplay;
          getAllRoles();
          changeFormStatus(formStatusEnum.CREATE);
        }
      },
      { immediate: true }
    );

    return {
      formStatusEnum,
      formStatusRef,
      options,
      $v,
      userUnit,
      toQueryView,
      back,
      submitForm,
      checkReset,
      validateState,
      changeFormStatus,
      form,
      formDefault,
      innerTable,
      roles,
      onChangeList,
      isReset,
      changeIsReset,
      innerRoleIdList,
      toRolesInfo,
      userIdChange,
    };
  },
  filters: {
    statusFilter: (value: string) => statusFormatter(value),
    userTypeFilter: (value: string) => userTypeFormatter(value),
    unitFilter: (value: string) => pccUnitFormatter(value),
    dateFilter: (value: any) => formatDate(value, '/'),
    dateTimeFilter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
  },
};
</script>

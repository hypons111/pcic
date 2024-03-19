<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            審核帳號
          </h5>
        </div>
        <div class="card-body">
          <b-form @submit.prevent="">
            <b-form-row>
              <i-form-group-check :label="$t('label.adm003.userId') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.userId.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm003.chName') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.chName.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm003.userType') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.userType.$model | userTypeFilter }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm003.unitId') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.unitId.$model | unitFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm003.title') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.title.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm003.workTel') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.workTel.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm003.mobileTel') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.mobileTel.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm003.email') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.email.$model }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check :label="$t('label.adm003.roleIdList') + '：'">
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

            <!--            <b-form-group-->
            <!--              :label="$t('label.adm004.roleIdList') + '：'"-->
            <!--              :class="{ 'form-check-with-marginB': true, 'col-6': false }"-->
            <!--              :label-cols="2"-->
            <!--              :content-cols="10"-->
            <!--              :label-align="'right'"-->
            <!--              label-class="label">-->
            <!--              <b-table-->
            <!--                :empty-text="'查無資料'"-->
            <!--                :items="innerRoleIdList"-->
            <!--                :fields="innerTable.fields"-->
            <!--                show-empty-->
            <!--                class="table table-sm"-->
            <!--                head-variant="light"-->
            <!--              >-->
            <!--                <template #cell(action)="data">-->
            <!--                  <i-button class="mb-1" size="sm" type="binoculars" @click="toRolesInfo(data.item.roleId)"></i-button>-->
            <!--                </template>-->
            <!--              </b-table>-->
            <!--            </b-form-group>-->

            <b-form-row>
              <i-form-group-check :label="$t('label.adm003.applyUser') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.updateUser.$model }}
                </div>
              </i-form-group-check>
              <i-form-group-check :label="$t('label.adm003.applyTime') + '：'">
                <div class="h-100 d-flex align-items-center">
                  {{ $v.updateTime.$model | dateTimeFilter }}
                </div>
              </i-form-group-check>
            </b-form-row>

            <b-form-group
              :label="$t('label.adm003.auditStatus') + '：'"
              :class="{ 'form-check-with-marginB': true, 'col-6': false }"
              :label-cols="2"
              :content-cols="10"
              :label-align="'right'"
              label-class="label"
            >
              <b-form-radio-group
                :options="options.auditStatusOpts"
                v-model="$v.auditStatus.$model"
                :state="validateState($v.auditStatus)"
                buttons
                button-variant="outline-secondary"
              ></b-form-radio-group>
              <b-form-invalid-feedback v-for="(error, index) of $v.auditStatus.$errors" :key="'auditStatus-' + index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-form-group>

            <b-button-toolbar class="float-right">
              <i-button class="mr-1" type="cursor" @click="submitForm()"></i-button>
              <i-button class="mr-1" type="arrow-left" @click="toQueryView()"></i-button>
            </b-button-toolbar>
          </b-form>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, reactive, ref, watch, toRefs, onMounted } from '@vue/composition-api';
import axios from 'axios';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useBvModal } from '@/shared/modal';
import { required } from '@/shared/validators';
import { accountStore } from '@/shared/config/store/account-store';
import { AdmAccountContact } from '@/shared/model/adm-account-contact.model';
import { statusFormatter, userTypeFormatter, dateTimeFormatter, pccUnitFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'Adm003EditInfo',
  props: {
    userId: {
      type: String,
      required: true,
    },
  },
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
    }

    // 表單操作狀態
    const formStatusRef = ref(formStatusEnum.INFO);

    // modal物件
    const $bvModal = useBvModal();
    // 是否需要重新查詢
    const isReload = ref(false);
    // 建立表單物件預設值
    let formDefault: AdmAccountContact = new AdmAccountContact().setCreateDefault();
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
      userId: {},
      chName: {},
      userType: {},
      title: {},
      pccOrgId: {},
      unitId: {},
      auditStatus: { notNull: required },
      workTel: {},
      mobileTel: {},
      email: {},
      updateUser: {},
      updateTime: {},
      homeAddrZip: {},
      homeAddrCity: {},
      homeAddrTown: {},
      homeAddrOther: {},
      workAddrZip: {},
      workAddrCity: {},
      workAddrTown: {},
      workAddrOther: {},
      roleIdList: {},
    };

    onMounted(() => {
      getAllUnitOpts();
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
    const options = reactive({
      auditStatusOpts: [
        { value: 'N', text: '作廢' },
        { value: 'Y', text: '核准' },
      ],
      unitOpts: [],
      userTypeOpts: [
        { value: '', text: '請選擇' },
        { value: 'A', text: '機關帳號' },
        { value: 'C', text: '公司法人帳號' },
        { value: 'I', text: '個人帳號' },
      ],
    });

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

    function initUserAccountInfo() {
      // 查整頁資訊
      axios
        .get(`/adm-account/${props.userId}`)
        .then(({ data }) => {
          if (data && data['userId']) {
            createDefaultValue(data);
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function createDefaultValue(data: AdmAccountContact) {
      data.status = '';
      data.auditStatus = '';
      Object.assign(formDefault, data);
      formDefault.roleIdListDisplay = data.roleIdList.split(',');
      formDefault.roleIdListDisplay.pop();
      Object.assign(form, formDefault);
    }

    let innerRoleIdList = computed(() => {
      const resultList = [];
      form.roleIdListDisplay.forEach(item => {
        const roleId = {};
        roleId['roleId'] = item;
        resultList.push(roleId);
      });
      return resultList;
    });

    // 返回列表
    function toQueryView() {
      // const fromName = useStore().value.getters.routeData.fromName;
      // navigateByNameAndParams(fromName, { isReload: isReload.value });
      navigateByNameAndParams('adm003', { isReload: isReload.value });
    }

    // 查詢角色詳情
    function toRolesInfo(roleId: string) {
      navigateByNameAndParams('adm003-role-info', { userId: props.userId, roleId: roleId });
    }

    // 變更表單操作狀態
    function changeFormStatus(status: formStatusEnum) {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    }

    function submitForm() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          let result = '';
          if (form.auditStatus === 'N') {
            form.status = 'N';
            result = '作廢';
          } else if (form.auditStatus === 'Y') {
            form.status = 'Y';
            result = '核准';
          }
          $bvModal
            .msgBoxConfirm(`是否確認送出審核？ 審核結果為：${result}`, {
              headerBgVariant: 'info',
              headerTextVariant: 'light',
              titleTag: 'h4',
              title: '審核確認',
              headerClass: 'p-2 justify-content-center font-weight-bolder',
              bodyClass: 'h5',
              footerClass: 'justify-content-center',
              okTitle: '確定',
              okVariant: 'info',
              cancelTitle: '取消',
            })
            .then((isOK: boolean) => {
              if (isOK) {
                putAudit();
              }
            });
        } else {
          console.log(form);
          $bvModal.msgBoxOk('請檢查資料是否輸入完畢。', {
            headerBgVariant: 'danger',
            headerTextVariant: 'light',
            titleTag: 'h4',
            title: '審核結果未輸入完畢',
            headerClass: 'p-2 justify-content-center font-weight-bolder',
            bodyClass: 'h5',
            footerClass: 'justify-content-center',
            okTitle: '確定',
            okVariant: 'info',
          });
        }
      });
    }

    // 送出修改表單
    function putAudit() {
      if (form.auditStatus === 'N') {
        axios
          .delete(`/adm-account/${form.userId}`)
          .then(() => {
            toQueryView();
          })
          .catch(notificationErrorHandler(notificationService));
      } else {
        form.updateUser = 'SYS';
        form.updateTime = new Date();
        axios
          .put(`/adm-account/audit/${form.userId}`, form)
          .then(({ data }) => {
            isReload.value = true;
            navigateByNameAndParams('adm003', { isReload: isReload.value });
          })
          .finally(() => {})
          .catch(notificationErrorHandler(notificationService));
      }
    }

    watch(allUnitOpts, () => {
      options.unitOpts = allUnitOpts.value.map(data => {
        return { text: data['unitName'], value: data['unitId'] };
      });
      options.unitOpts.unshift({ text: '請選擇', value: '' });
    });

    const propsUserId = toRefs(props).userId;

    watch(
      propsUserId,
      () => {
        if (propsUserId.value) {
          initUserAccountInfo();
        }
        return propsUserId.value;
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
      toRolesInfo,
      submitForm,
      reset,
      validateState,
      changeFormStatus,
      form,
      innerRoleIdList,
      innerTable,
    };
  },
  filters: {
    statusFilter: (value: string) => statusFormatter(value),
    userTypeFilter: (value: string) => userTypeFormatter(value),
    unitFilter: (value: string) => pccUnitFormatter(value),
    dateTimeFilter: (value: any) => dateTimeFormatter(value, '民國年月日時間', 'YYY/MM/DD HH:mm:ss'),
  },
};
</script>

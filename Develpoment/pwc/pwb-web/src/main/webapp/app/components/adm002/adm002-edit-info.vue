<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            後臺帳號申請
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <i-form-group-check :label="$t('label.adm002.userId') + '：'">
              <b-form-input :disabled="true" placeholder="系統自動帶出"></b-form-input>
            </i-form-group-check>

            <i-form-group-check :label="$t('label.adm002.chName') + '：'" :item="$v.chName">
              <b-form-input v-model="$v.chName.$model" type="text" :state="validateState($v.chName)"></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <i-form-group-check :label="$t('label.adm002.title') + '：'" :item="$v.title">
              <b-form-input v-model="$v.title.$model" :state="validateState($v.title)"></b-form-input>
            </i-form-group-check>

            <i-form-group-check :label="$t('label.adm002.unitId') + '：'" :item="$v.unitId">
              <b-form-select
                v-model="$v.unitId.$model"
                :options="options.unitOpts"
                :state="validateState($v.unitId)"
                @change="userIdChange()"
              >
              </b-form-select>
            </i-form-group-check>
          </b-form-row>

          <b-form-group
            :label="$t('label.adm002.workTel') + '：'"
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
            :label="$t('label.adm002.mobileTel') + '：'"
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

          <b-form-group
            :label="$t('label.adm002.email') + '：'"
            :class="{ 'form-check-with-marginB': true, 'col-6': false }"
            :label-cols="2"
            :content-cols="10"
            :label-align="'right'"
            label-class="label"
          >
            <b-form-input v-model="$v.email.$model" type="text" :state="validateState($v.email)"></b-form-input>
            <b-form-invalid-feedback v-for="(error, index) of $v.email.$errors" :key="'email-' + index">
              {{ error.$message }}
            </b-form-invalid-feedback>
          </b-form-group>

          <b-form-group
            :label="$t('label.adm002.role') + '：'"
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

          <b-button-toolbar class="float-right">
            <i-button class="mr-1" type="send-check" @click="saveTask"></i-button>
            <i-button class="mr-1" type="x-circle" @click="checkReset()"></i-button>
            <i-button class="mr-1" type="arrow-left" @click="back()"></i-button>
          </b-button-toolbar>
        </div>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { computed, defineComponent, onMounted, reactive, ref, watch } from '@vue/composition-api';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { email, maxLength, required, telNumber, extendNumber, areaNumber, mobileTel4, mobileTel6 } from '@/shared/validators';
import axios from 'axios';
import { useBvModal } from '@/shared/modal';
import { useValidation, validateState } from '@/shared/form';
import { AdmAccountContact } from '@/shared/model/adm-account-contact.model';
import { navigateByNameAndParams } from '@/router/router';

export default defineComponent({
  name: 'Adm002EditInfo',

  setup(props, context) {
    const notificationService = useNotification();
    // modal物件
    const $bvModal = useBvModal();
    // 建立表單物件預設值
    let formDefault: AdmAccountContact = new AdmAccountContact().setApplyDefault();
    // 建立一份新的表單物件
    let form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      userId: { notNull: required },
      status: { notNull: required },
      auditStatus: { notNull: required },
      userType: { notNull: required },
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

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const allRolesProp = ref([]);
    const checked = ref([]);

    let roles = reactive({
      source: [],
      destination: [],
    });

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

    function setRoleIdList() {
      form['roleIdList'] = roles.destination.map(data => data['code']).join(',');
    }

    function userIdChange() {
      form.userId = '3.60.' + form.unitId + '-';
    }

    function saveTask() {
      form.updateUser = '申請人';
      form.updateTime = new Date();

      setRoleIdList();
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          combineTel();
          combineMobileTel();
          $bvModal
            .msgBoxConfirm('是否確認新增', {
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
                save();
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

    function combineTel() {
      if (form.extendNumber.length > 0) {
        form.workTel = form.areaNumber + '-' + form.telNumber + '#' + form.extendNumber;
      } else {
        form.workTel = form.areaNumber + '-' + form.telNumber;
      }
    }

    function combineMobileTel() {
      form.mobileTel = form.mobileTel4 + '-' + form.mobileTel6;
    }

    function save() {
      axios
        .post(`/service/adm-account/application`, form)
        .then(({ data }) => {
          reset();
          form.roleIdList = '';
          getAllRoles();
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

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
          }
        });
    }

    function back() {
      $bvModal
        .msgBoxConfirm('是否取消申請？', {
          headerBgVariant: 'danger',
          headerTextVariant: 'light',
          titleTag: 'h4',
          title: '取消確認',
          headerClass: 'p-2 justify-content-center font-weight-bolder',
          bodyClass: 'h5',
          footerClass: 'justify-content-center',
          okTitle: '確定',
          okVariant: 'info',
          cancelTitle: '取消',
        })
        .then((isOK: boolean) => {
          if (isOK) {
            navigateByNameAndParams('home', {});
          }
        });
    }

    const options = reactive({
      unitOpts: [
        { value: '', text: '請選擇' },
        { value: '1', text: '機關單位1' },
        { value: '2', text: '機關單位2' },
        { value: '3', text: '機關單位3' },
      ],
    });

    watch(allUnitOpts, () => {
      options.unitOpts = allUnitOpts.value.map(data => {
        return { text: data['unitName'], value: data['unitId'] };
      });
      options.unitOpts.unshift({ text: '請選擇', value: '' });
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

    return {
      back,
      checkReset,
      $v,
      form,
      saveTask,
      options,
      validateState,
      roles,
      onChangeList,
      userIdChange,
    };
  },
});
</script>

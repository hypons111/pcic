<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header py-2">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            使用者資料維護
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 帳號 -->
            <i-form-group-check labelStar :label="$t('label.account') + '：'" :item="$v.userId">
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.userId.$model }}</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 姓名 -->
            <i-form-group-check
              class="col-6"
              label-cols-md="4"
              content-cols-md="8"
              labelStar
              :label="$t('label.name') + '：'"
              :item="$v.chName"
            >
              <b-form-input v-model="$v.chName.$model" type="text" :state="validateState($v.chName)"></b-form-input>
            </i-form-group-check>

            <!-- 是否啟用 -->
            <i-form-group-check class="col-6" label-cols-md="4" content-cols-md="8" labelStar :label="$t('label.mainEnable') + '：'">
              <b-form-radio-group v-model="$v.status.$model" :options="formOptions.status"></b-form-radio-group>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 身分證字號/居留證號 -->
            <i-form-group-check labelStar :label="$t('label.certNo') + '：'" :item="$v.idno">
              <b-form-input v-model="$v.idno.$model" :state="validateState($v.idno)" trim></b-form-input>
            </i-form-group-check>
            <div class="d-flex p-1">身分證號 或 居留證號</div>
          </b-form-row>

          <b-form-row>
            <!-- 性別 -->
            <i-form-group-check class="col-4" labelStar label-cols-md="6" content-cols="5" :label="$t('label.sex') + '：'" :item="$v.sex">
              <b-form-select v-model="$v.sex.$model" :options="formOptions.sex" :state="validateState($v.sex)">
                <template #first>
                  <option :value="null">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>

            <!-- 出生日期 -->
            <i-form-group-check labelStar label-cols-md="3" content-cols="5" :label="$t('label.birthday') + '：'" :item="$v.birthday">
              <i-date-picker v-model="$v.birthday.$model" :state="validateState($v.birthday)" placeholder="yyy/MM/dd"></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 電話 -->
            <i-form-group-check labelStar class="col-12" label-cols="2" content-cols="5" :label="$t('label.tel') + '：'" :item="$v.workTel">
              <i-tel v-model.trim="$v.workTel.$model" :state="validateState($v.workTel)" showExt></i-tel>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 行動電話 -->
            <i-form-group-check labelStar content-cols="6" :label="$t('label.mobileTel') + '：'" :item="$v.mobileTel">
              <i-tel v-model.trim="$v.mobileTel.$model" :state="validateState($v.mobileTel)" isMobile></i-tel>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 通訊地址 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.address') + '：'"
              :item="[$v.homeAddrTown, $v.homeAddrOther, $v.homeAddrCity]"
            >
              <i-address-picker
                :zip.sync="$v.homeAddrZip.$model"
                :city.sync="$v.homeAddrCity.$model"
                :town.sync="$v.homeAddrTown.$model"
                :other.sync="$v.homeAddrOther.$model"
                trim
                :city-state="validateState($v.homeAddrCity)"
                :town-state="validateState($v.homeAddrTown)"
                :other-state="validateState($v.homeAddrOther)"
              >
              </i-address-picker>
              <template #text="{ zip, city, town, other }">
                {{ `[${zip}] ${city}${town}${other}` }}
              </template>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- email -->
            <i-form-group-check labelStar :label="$t('label.email') + '：'" :item="$v.email">
              <b-form-input v-model="$v.email.$model" type="text" :state="validateState($v.email)" placeholder="xx@xx.xx"></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <b-col offset="2" cols="8">
              <dual-list-box
                :source="jurisdictionRoles.source"
                :destination="jurisdictionRoles.destination"
                label="name"
                @onChangeList="onChangeJurisdiction"
              ></dual-list-box>
            </b-col>
          </b-form-row>
        </div>
        <div class="text-center mb-3">
          <i-button type="save" @click="showModal"></i-button>
          <i-button type="x-circle" @click="remove"></i-button>
          <i-button type="arrow-bar-left" @click="toQueryView"></i-button>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { reactive, ref, watch, toRefs } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form/';
import { required, email, maxLength, nationalId, mobileTel, lineTel, address } from '@/shared/validators';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import axios from 'axios';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';

export default {
  name: 'pwb0202EditInfo',
  props: {
    item: {
      type: Object,
      required: false,
    },
    editTable: {
      type: Object,
      required: false,
    },
    infoTable: {
      type: Object,
      required: false,
    },
  },
  setup(props) {
    // 宣告表單物件及初始值，是前後端 接收或傳遞 的資料物件
    const formDefault = {
      userId: null, //帳號
      chName: '', //姓名
      idno: '', //證號
      sex: '', //性別
      birthday: null, //生日
      workTel: '', //電話
      mobileTel: '', //行動電話
      homeAddrZip: '', //住家區碼
      homeAddrCity: '', //住家縣市
      homeAddrTown: '', //住家鄉鎮市區
      homeAddrOther: '', //住家其他地址
      email: '', // 信箱
      status: '', //是否啟用
      jurisdiction: '0', //權限
      jurisdictionRoles: [], //權限list
      updateUser: '',
      updateTime: null,
      loginUserId: null, //登入者的USER_ID
      pccOrgId: null, //機關
      compIdno: null, //公司
    };
    // 建立一份新的表單物件
    let form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      userId: { notNull: required },
      chName: { notNull: required, maxLength: maxLength(50) },
      idno: { nationalId: nationalId },
      sex: { notNull: required },
      birthday: { notNull: required },
      workTel: { lineTel: lineTel },
      mobileTel: { mobileTel: mobileTel },
      homeAddrZip: {},
      homeAddrCity: { required },
      homeAddrTown: { equalsCityBoolean: address(() => form.homeAddrCity) },
      homeAddrOther: { equalsCityBoolean: address(() => form.homeAddrCity) },
      email: { email: email, maxLength: maxLength(100) },
      status: { notNull: required },
      jurisdiction: {},
      jurisdictionRoles: {},
      updateUser: {},
      updateTime: {},
      loginUserId: {},
      pccOrgId: {},
      compIdno: {},
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const formOptions = reactive({
      sex: [
        { value: 'M', text: '男' },
        { value: 'F', text: '女' },
      ],
      status: [
        { value: 'Y', text: '是' },
        { value: 'N', text: '否' },
      ],
    });

    const itemProp = toRefs(props).item;
    const editTableProp = toRefs(props).editTable;
    const infoTableProp = toRefs(props).infoTable;

    //按編輯button傳的值
    watch(
      itemProp,
      () => {
        if (itemProp.value) {
          form.userId = itemProp.value.userId;
          getAdmContact();
        }
      },
      { immediate: true }
    );

    //按編輯button傳的值
    watch(
      editTableProp,
      () => {
        if (editTableProp.value) {
          Object.assign(form, editTableProp.value);
          getJurisdictionAllRoles();
        }
      },
      { immediate: true }
    );

    //按新增button傳的值
    watch(
      infoTableProp,
      () => {
        if (infoTableProp.value) {
          Object.assign(formDefault, infoTableProp.value);
          reset();
          getJurisdictionAllRoles();
        }
      },
      { immediate: true }
    );

    const notificationService = useNotification();

    //查詢AdmContact的資料
    function getAdmContact() {
      form.status = itemProp.value.status;
      axios
        .get('/adm-contacts/' + form.userId)
        .then(({ data }) => {
          Object.assign(form, data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function remove() {
      reset();
      form.userId = itemProp.value.userId;
    }

    // 是否需要重新查詢
    const isReload = ref(true);

    const toQueryView = () => {
      formDefault.userId = null;
      Object.assign(formDefault, {});
      reset();
      let queryStatusEdit = 'noTable';
      navigateByNameAndParams('pwb0202', { queryStatusEdit, isnotkeepAlive: true });
    };

    //權限
    let jurisdictionRoles = reactive({
      source: [],
      destination: [],
    });

    const allJurisdictionRolesProp = ref([]);

    watch(
      () => form.jurisdiction,
      newValue1 => {
        if (newValue1 === '0') {
          const source = allJurisdictionRolesProp.value.filter(option => isInLeft(option));
          jurisdictionRoles.source.splice(0, jurisdictionRoles.source.length, ...source);
          const destination = allJurisdictionRolesProp.value.filter(option => !isInLeft(option));
          jurisdictionRoles.destination.splice(0, jurisdictionRoles.destination.length, ...destination);
        }
      }
    );

    // 建立JurisdictionSource
    function getJurisdictionAllRoles() {
      const agentId = form.pccOrgId;
      const companyId = form.compIdno;
      let admUrl = null;

      //判斷登入者的單位是機關還是公司
      if (agentId == null) {
        admUrl = '/pwb-unit-role/get-role-opts/' + companyId;
      } else {
        admUrl = '/pwb-unit-role/get-role-opts/' + agentId;
      }

      axios.get(admUrl).then(({ data }) => {
        if (data != null) {
          allJurisdictionRolesProp.value.splice(0, allJurisdictionRolesProp.value.length);

          data.forEach(item => {
            let queryOption = { code: '', name: '' };
            queryOption.code = item.roleId;
            queryOption.name = item.roleName;
            allJurisdictionRolesProp.value.push(queryOption);
          });
        }
      });
    }

    //權限
    watch(allJurisdictionRolesProp, () => {
      const source = allJurisdictionRolesProp.value.filter(option => isInLeft(option));
      jurisdictionRoles.source.splice(0, jurisdictionRoles.source.length, ...source);
      const destination = allJurisdictionRolesProp.value.filter(option => !isInLeft(option));
      jurisdictionRoles.destination.splice(0, jurisdictionRoles.destination.length, ...destination);
    });

    //權限onChange
    function onChangeJurisdiction({ source, destination }) {
      jurisdictionRoles.source.splice(0, jurisdictionRoles.source.length, ...source);
      jurisdictionRoles.destination.splice(0, jurisdictionRoles.destination.length, ...destination);
    }

    const checked = ref([]);

    //權限及模組
    function isInLeft(option) {
      if (checked.value.length) {
        return checked.value.filter(c => c === option.code).length <= 0;
      }
      return true;
    }

    //把右邊的權限放到form.jurisdictionRoles
    function dataChang() {
      jurisdictionRoles.destination.forEach(item => {
        let selectOption = {
          code: '',
          text: '',
        };
        selectOption.code = item.code;
        selectOption.text = item.name;
        form.jurisdictionRoles.push(selectOption);
      });
    }

    //modal物件
    const $bvModal = useBvModal();

    //跳出b-modal
    function showModal() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal
            .msgBoxConfirm(`已啟用${form.chName}、帳號：${form.userId} 系統將發送帳號啟用通知至使用者信箱：${form.email}`)
            .then((isOK: boolean) => {
              if (isOK) {
                dataChang();
                save();
              }
            });
        }
      });
    }

    //儲存
    function save() {
      axios
        .put('/adm-account-contact/createAndSave', form)
        .then(({ data }) => {
          toQueryView();
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    return {
      formDefault,
      form,
      rules,
      $v,
      reset,
      formOptions,
      validateState,
      remove,
      toQueryView,
      onChangeJurisdiction,
      jurisdictionRoles,
      showModal,
    };
  },
};
</script>

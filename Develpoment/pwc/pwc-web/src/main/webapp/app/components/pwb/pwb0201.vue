<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0">
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
            <i-form-group-check labelStar :label="$t('label.name') + '：'" :item="$v.chName">
              <b-form-input v-model="$v.chName.$model" type="text" :state="validateState($v.chName)"></b-form-input>
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
              <b-form-select v-model="$v.sex.$model" :options="options.sex" :state="validateState($v.sex)">
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
            <b-col offset="4">
              <b-button variant="info" @click="changePassword">密碼變更</b-button>
              <i-button type="save" @click="saveAdmContact"></i-button>
              <i-button type="x-circle" @click="reset"></i-button>
              <b-button variant="info"><router-link to="/" class="btn-link text-white">回首頁</router-link></b-button>
            </b-col>
          </b-form-row>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, reactive, ref } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, email, maxLength, nationalId, mobileTel, lineTel, address } from '@/shared/validators';
import { useGetters } from '@u3u/vue-hooks';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import axios from 'axios';

export default {
  name: 'pwb0201',
  setup() {
    onMounted(() => {
      getAdmContact();
    });

    // 宣告表單物件及初始值，是前後端 接收或傳遞 的資料物件
    const formDefault = {
      userId: '', //帳號
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
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const options = reactive({
      sex: [
        { value: 'M', text: '男' },
        { value: 'F', text: '女' },
      ],
    });

    //取得登入帳號
    const userId = ref(useGetters(['account']).account.value.id);

    const notificationService = useNotification();

    //查詢AdmContact的資料
    function getAdmContact() {
      axios
        .get('/adm-contacts/' + userId.value)
        .then(({ data }) => {
          Object.assign(form, data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    //儲存AdmContact
    function saveAdmContact() {
      axios
        .put('/adm-contacts/' + userId.value, form)
        .then(({ data }) => {
          Object.assign(form, data);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    //密碼變更
    function changePassword() {
      navigateByNameAndParams('pwb0201pwd', { userId, isnotkeepAlive: true });
    }

    return {
      $v,
      form,
      options,
      validateState,
      reset,
      userId,
      saveAdmContact,
      changePassword,
    };
  },
};
</script>

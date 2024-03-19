<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            公會基本資料
          </h5>
        </div>
        <div class="card-body">
          <div v-if="formStatusRef === formStatusEnum.CREATE">
            <b-form-row class="align-items-center">
              <b-col cols="2" class="text-right label"><span class="danger">*</span><span>公會名稱</span></b-col>
              <b-col cols="2">
                <b-form-select v-model="$v.type.$model" :options="formOptions.type" :state="validateState($v.type)"></b-form-select>
                <b-form-invalid-feedback v-for="(error, index) of $v.type.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
              <b-col cols="3">
                <b-form-input v-model.trim="$v.name.$model" :state="validateState($v.name)"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
              <b-col cols="2">
                <b-form-select
                  v-model="$v.useStatus.$model"
                  :options="formOptions.useStatus"
                  :state="validateState($v.useStatus)"
                ></b-form-select>
                <b-form-invalid-feedback v-for="(error, index) of $v.useStatus.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
            </b-form-row>
          </div>
          <div v-else-if="formStatusRef === formStatusEnum.MODIFY">
            <b-form-row class="align-items-center">
              <b-col cols="2" class="text-right label">
                <span>序號</span>
              </b-col>
              <b-col cols="3">
                <span>{{ $v.id.$model }}</span>
              </b-col>
              <b-col cols="2" class="text-right label"><span class="red-text">*</span><span>公會名稱</span></b-col>
              <b-col cols="3">
                <b-form-input v-model.trim="$v.name.$model" :state="validateState($v.name)"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.name.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
            </b-form-row>
            <b-form-row class="align-items-center mt-2">
              <b-col cols="2" class="text-right label"><span class="red-text">*</span><span>公會類型</span></b-col>
              <b-col cols="3">
                <b-form-select v-model="$v.type.$model" :options="formOptions.type" :state="validateState($v.type)"></b-form-select>
                <b-form-invalid-feedback v-for="(error, index) of $v.type.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
              <b-col cols="2" class="text-right label"><span class="red-text">*</span><span>狀態</span></b-col>
              <b-col cols="2">
                <b-form-select
                  v-model="$v.useStatus.$model"
                  :options="formOptions.useStatus"
                  :state="validateState($v.useStatus)"
                ></b-form-select>
                <b-form-invalid-feedback v-for="(error, index) of $v.useStatus.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </b-col>
            </b-form-row>
          </div>
          <div v-else>
            <b-form-row class="align-items-center">
              <b-col cols="2" class="text-right label"><span class="red-text">*</span><span>公會名稱</span></b-col>
              <b-col cols="3">{{ $v.name.$model }}</b-col>
              <b-col cols="2" class="text-right label"><span class="red-text">*</span><span>類別</span></b-col>
              <b-col cols="2">{{ $v.type.$model | guildType }}</b-col>
              <b-col cols="1" class="text-right label"><span class="red-text">*</span><span>狀態</span></b-col>
              <b-col cols="2">{{ $v.useStatus.$model | status }}</b-col>
            </b-form-row>
          </div>
          <b-form-row class="align-items-center mt-2">
            <b-col cols="2" class="text-right label"><span class="red-text">*</span><span>理事長</span></b-col>
            <b-col cols="3">
              <div v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model.trim="$v.chairman.$model" :state="validateState($v.chairman)"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.chairman.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </div>
              <span v-else>{{ $v.chairman.$model }}</span>
            </b-col>
            <b-col cols="2" class="text-right label"><span class="red-text">*</span><span>秘書長</span></b-col>
            <b-col cols="3">
              <div v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model.trim="$v.secretaryGeneral.$model" :state="validateState($v.secretaryGeneral)"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.secretaryGeneral.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </div>
              <span v-else>{{ $v.secretaryGeneral.$model }}</span>
            </b-col>
          </b-form-row>
          <b-form-row class="align-items-center mt-2">
            <b-col cols="2" class="text-right label"><span class="red-text">*</span><span>電話一</span></b-col>
            <b-col cols="3">
              <div v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model.trim="$v.tel1.$model" :state="validateState($v.tel1)"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.tel1.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </div>
              <span v-else>{{ $v.tel1.$model }}</span>
            </b-col>
            <b-col cols="2" class="text-right label">
              <span>電話二</span>
            </b-col>
            <b-col cols="3">
              <div v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model.trim="$v.tel2.$model" :state="validateState($v.tel2)"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.tel2.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </div>
              <span v-else>{{ $v.tel2.$model }}</span>
            </b-col>
          </b-form-row>
          <b-form-row class="align-items-center mt-2">
            <b-col cols="2" class="text-right label">
              <span>理事長手機</span>
            </b-col>
            <b-col cols="3">
              <div v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model.trim="$v.chairmanTel.$model" :state="validateState($v.chairmanTel)"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.chairmanTel.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </div>
              <span v-else>{{ $v.chairmanTel.$model }}</span>
            </b-col>
            <b-col cols="2" class="text-right label">
              <span>傳真</span>
            </b-col>
            <b-col cols="3">
              <div v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model.trim="$v.fax.$model" :state="validateState($v.fax)"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.fax.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </div>
              <span v-else>{{ $v.fax.$model }}</span>
            </b-col>
          </b-form-row>
          <b-form-row v-if="formStatusRef !== formStatusEnum.INFO" class="align-items-center mt-2">
            <b-col cols="2" class="text-right label"><span class="red-text">*</span><span>地址</span></b-col>
            <b-col cols="2" class="text-right">
              <b-form-select
                v-model="$v.city.$model"
                :options="formOptions.city"
                @input="changeCity($v.city.$model)"
                :state="validateState($v.city)"
              ></b-form-select>
              <b-form-invalid-feedback v-for="(error, index) of $v.city.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
            <b-col cols="2">
              <b-form-select
                v-model="$v.dist.$model"
                :options="formOptions.dist"
                :disabled="!$v.city.$model"
                :state="validateState($v.dist)"
              ></b-form-select>
              <b-form-invalid-feedback v-for="(error, index) of $v.dist.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
            <b-col cols="5">
              <b-form-input v-model.trim="$v.road.$model" :state="validateState($v.road)"></b-form-input>
              <b-form-invalid-feedback v-for="(error, index) of $v.road.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
          </b-form-row>
          <b-form-row v-else class="align-items-center mt-2">
            <b-col cols="2" class="text-right label">
              <span>地址</span>
            </b-col>
            <b-col cols="10">
              <span>{{ $v.address.$model }}</span>
            </b-col>
          </b-form-row>
          <b-form-row class="align-items-center mt-2">
            <b-col cols="2" class="text-right label">
              <span>Email</span>
            </b-col>
            <b-col cols="5">
              <div v-if="formStatusRef !== formStatusEnum.INFO">
                <b-form-input v-model.trim="$v.mail.$model" :state="validateState($v.mail)"></b-form-input>
                <b-form-invalid-feedback v-for="(error, index) of $v.mail.$errors" :key="index">
                  {{ error.$message }}
                </b-form-invalid-feedback>
              </div>
              <span v-else>{{ $v.mail.$model }}</span>
            </b-col>
          </b-form-row>
          <b-form-row class="align-items-center mt-2">
            <b-col cols="2" class="text-right label">
              <span>業務範圍</span>
            </b-col>
            <b-col cols="4">
              <div v-for="option in formOptions.subjectListDisplay" :key="option.value" class="col-sm-6 d-inline-block">
                <b-form-checkbox
                  v-model="$v.subjectListDisplay.$model"
                  :value="option.value"
                  :disabled="formStatusRef === formStatusEnum.INFO"
                >
                  {{ option.text }}
                </b-form-checkbox>
              </div>
            </b-col>
          </b-form-row>
          <b-button-toolbar v-show="formStatusRef === formStatusEnum.INFO" class="float-right mt-5">
            <b-button size="sm" variant="outline-secondary" @click="changeFormStatus(formStatusEnum.MODIFY)">修改 </b-button>
            <b-button size="sm" variant="outline-secondary" @click="toQueryView">返回</b-button>
          </b-button-toolbar>
          <b-button-toolbar v-show="formStatusRef !== formStatusEnum.INFO" class="float-right mt-5">
            <b-button size="sm" variant="outline-secondary" v-promise-btn="{ promise: dataPromise }" @click="submitForm">儲存 </b-button>
            <b-button size="sm" variant="outline-secondary" @click="reset">重設</b-button>
            <b-button size="sm" variant="outline-secondary" @click="toQueryView">取消</b-button>
          </b-button-toolbar>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import { guildTypeFormatter, statusFormatter } from '@/shared/formatter/common';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler, notificationResponseHandler } from '@/shared/http/http-response-helper';
import { CmsComp } from '@/shared/model/cms-comp.model';
import { handleBack } from '@/router/router';

export default {
  name: 'Ogn01EditInfo',
  props: {
    cmsComp: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const cmsCompProp = toRefs(props).cmsComp;

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      id: '',
      type: '', // 公會類型
      name: '', // 公會名稱
      chairman: '', // 理事長
      secretaryGeneral: '', // 秘書長
      useStatus: '', // 啟用狀態
      tel1: '', // 電話一
      tel2: '', // 電話二
      chairmanTel: '', // 理事長手機
      fax: '', // 傳真
      city: '', // 縣市
      dist: '', // 鄉鎮
      road: '', // 路
      address: '', // 地址
      mail: '',
      subjectListDisplay: [], // 業務範圍
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      id: {},
      type: { notNull: required },
      name: { notNull: required },
      chairman: { notNull: required },
      secretaryGeneral: { notNull: required },
      useStatus: { notNull: required },
      tel1: { notNull: required },
      tel2: {},
      chairmanTel: {},
      fax: {},
      city: { notNull: required },
      dist: { notNull: required },
      road: { notNull: required },
      address: {},
      mail: { email: email },
      subjectListDisplay: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 下拉選單選項
    const formOptions = reactive({
      type: [
        { value: '', text: '請選擇' },
        { value: '1', text: '公會' },
        { value: '2', text: '全聯會' },
        { value: '3', text: '顧問公會' },
      ],
      useStatus: [
        { value: '', text: '請選擇' },
        { value: '1', text: '啟用' },
        { value: '0', text: '停用' },
      ],
      city: [
        { value: '', text: '請選擇' },
        { value: '台北市', text: '台北市' },
        { value: '新北市', text: '新北市' },
      ],
      dist: [],
      subjectListDisplay: [
        { value: '0', text: '全部' },
        { value: '1', text: '土木工程' },
        { value: '2', text: '水利工程' },
        { value: '3', text: '結構工程' },
        { value: '4', text: '大地工程' },
        { value: '5', text: '測量' },
        { value: '6', text: '環境工程' },
        { value: '7', text: '都市計畫' },
        { value: '8', text: '機械工程' },
        { value: '9', text: '冷凍空調工程' },
        { value: '10', text: '造船工程' },
        { value: '11', text: '電機工程' },
        { value: '12', text: '電子工程' },
        { value: '13', text: '資訊' },
        { value: '14', text: '航空工程' },
        { value: '15', text: '化學工程' },
        { value: '16', text: '工業工程' },
        { value: '17', text: '工業安全' },
        { value: '18', text: '工礦衛生' },
        { value: '19', text: '紡織工程' },
        { value: '20', text: '食品' },
        { value: '21', text: '冶金工程' },
        { value: '22', text: '農藝' },
        { value: '23', text: '園藝' },
        { value: '24', text: '林業' },
        { value: '25', text: '畜牧' },
        { value: '26', text: '漁撈' },
        { value: '27', text: '水產養殖' },
        { value: '28', text: '水土保持' },
        { value: '29', text: '採礦工程' },
        { value: '30', text: '應用地質' },
        { value: '31', text: '礦業安全' },
        { value: '32', text: '交通工程' },
        { value: '33', text: '職業衛生' },
      ],
    });

    const distList = [
      { city: '台北市', text: '中正區', value: '中正區' },
      { city: '台北市', text: '大同區', value: '大同區' },
      { city: '台北市', text: '中山區', value: '中山區' },
      { city: '台北市', text: '松山區', value: '松山區' },
      { city: '台北市', text: '大安區', value: '大安區' },
      { city: '台北市', text: '萬華區', value: '萬華區' },
      { city: '台北市', text: '信義區', value: '信義區' },
      { city: '台北市', text: '士林區', value: '士林區' },
      { city: '台北市', text: '北投區', value: '北投區' },
      { city: '台北市', text: '內湖區', value: '內湖區' },
      { city: '台北市', text: '南港區', value: '南港區' },
      { city: '台北市', text: '文山區', value: '文山區' },
      { city: '新北市', text: '板橋區', value: '板橋區' },
      { city: '新北市', text: '中和區', value: '中和區' },
      { city: '新北市', text: '永和區', value: '永和區' },
      { city: '新北市', text: '土城區', value: '土城區' },
      { city: '新北市', text: '三峽區', value: '三峽區' },
      { city: '新北市', text: '鶯歌區', value: '鶯歌區' },
      { city: '新北市', text: '樹林區', value: '樹林區' },
      { city: '新北市', text: '三重區', value: '三重區' },
      { city: '新北市', text: '蘆洲區', value: '蘆洲區' },
      { city: '新北市', text: '新莊區', value: '新莊區' },
      { city: '新北市', text: '五股區', value: '五股區' },
      { city: '新北市', text: '泰山區', value: '泰山區' },
      { city: '新北市', text: '林口區', value: '林口區' },
      { city: '新北市', text: '八里區', value: '八里區' },
      { city: '新北市', text: '淡水區', value: '淡水區' },
      { city: '新北市', text: '三芝區', value: '三芝區' },
      { city: '新北市', text: '石門區', value: '石門區' },
      { city: '新北市', text: '金山區', value: '金山區' },
      { city: '新北市', text: '萬里區', value: '萬里區' },
      { city: '新北市', text: '汐止區', value: '汐止區' },
      { city: '新北市', text: '瑞芳區', value: '瑞芳區' },
      { city: '新北市', text: '貢寮區', value: '貢寮區' },
      { city: '新北市', text: '平溪區', value: '平溪區' },
      { city: '新北市', text: '雙溪區', value: '雙溪區' },
      { city: '新北市', text: '新店區', value: '新店區' },
      { city: '新北市', text: '深坑區', value: '深坑區' },
      { city: '新北市', text: '坪林區', value: '坪林區' },
      { city: '新北市', text: '石碇區', value: '石碇區' },
    ];

    // 執行結果
    const dataPromise = ref(null);
    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const toQueryView = () => {
      const param = { isReload: isReload.value };
      handleBack(param);
    };

    const changeCity = (city: string) => {
      const distOptions = distList.filter(dist => dist.city === city);
      formOptions.dist.splice(0, formOptions.dist.length, ...distOptions);
      formOptions.dist.unshift({ text: '請選擇', value: '' });
    };

    const changeFormStatus = (status: formStatusEnum) => {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
            if (isOK) {
              setFormAddress();
              setSubjectList();
              putForm();
            }
          });
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const putForm = () => {
      let url: string;
      if (formStatusRef.value === formStatusEnum.MODIFY) {
        url = `/cms-comps/${form.id}`;
      }
      dataPromise.value = axios
        .put(url, form)
        .then(({ data }) => {
          changeFormStatus(formStatusEnum.INFO);
          createDefaultValue(data);
          isReload.value = true;
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const setCityAndDistAndRoad = () => {
      formDefault.address = formDefault.address.replace(/@/g, '');
      const addressArray = props.cmsComp.address.split('@');
      changeCity(addressArray[0]);
      formDefault.city = addressArray[0];
      formDefault.dist = addressArray[1];
      formDefault.road = addressArray[2];
      formDefault.subjectListDisplay = props.cmsComp.subjectList.split(',');
    };

    const setFormAddress = () => {
      form.address = form.city + '@' + form.dist + '@' + form.road;
    };

    const setSubjectList = () => {
      form['subjectList'] = form.subjectListDisplay.join(',');
    };

    const createDefaultValue = (data: CmsComp) => {
      Object.assign(formDefault, data);
      setCityAndDistAndRoad();
      reset();
    };

    // 傳入的物件
    // const cmsCompProp: ComputedRef<CmsComp> = computed(() => {
    //   if (props.cmsComp) {
    //     return props.cmsComp;
    //   }
    //   return null;
    // });

    watch(
      cmsCompProp,
      () => {
        if (cmsCompProp.value) {
          createDefaultValue(cmsCompProp.value);
        }
      },
      { immediate: true }
    );

    return {
      formStatusEnum,
      formStatusRef,
      $v,
      formOptions,
      dataPromise,
      changeCity,
      changeFormStatus,
      reset,
      validateState,
      toQueryView,
      submitForm,
    };
  },
  filters: {
    status: (value: string) => statusFormatter(value),
    guildType: (value: string) => guildTypeFormatter(value),
  },
};
</script>
<style>
.red-text {
  color: red;
  font-weight: bold;
}

.label {
  background-color: #737373;
  font-weight: bolder;
  color: white;
  padding-top: 5px;
  padding-bottom: 5px;
}
</style>

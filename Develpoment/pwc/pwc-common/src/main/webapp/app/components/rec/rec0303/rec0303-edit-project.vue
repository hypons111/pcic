<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0" v-if="formStatusForwardProp.value === formStatusEnum.MODIFY">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          編輯復建工程資料
        </h5>
        <h5 class="m-0" v-else-if="formStatusForwardProp.value === formStatusEnum.CREATE">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          新增復建工程資料
        </h5>
      </div>
      <div class="card-body col-10">
        <div>
          <!--新增或修改-->
          <div>
            <b-form-row class="align-items-top">
              <!-- 復建工程名稱 -->
              <i-form-group-check
                :labelStar="true"
                label="工程代碼："
                :item="$v.projectCode"
                :label-cols="4"
                :content-cols="8"
              >
                <b-form-select v-model="$v.projectCode.$model" :options="queryOptions.projectCode" :state="validateState($v.projectCode)"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="align-items-top">
              <!-- 復建工程名稱 -->
              <i-form-group-check
                :labelStar="true"
                label="復建工程名稱："
                :item="$v.projectName"
                :label-cols="4"
                :content-cols="8"
              >
                <b-form-input v-model.trim="$v.projectName.$model" :state="validateState($v.projectName)" maxlength="30"> </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="align-items-top">
              <!-- 災害發生日期 -->
              <i-form-group-check
                label="災害發生日期："
                :item="$v.damageDate"
                :label-cols="4"
                :content-cols="8"
              >
                <i-date-picker v-model.trim="$v.damageDate.$model" :state="$v.damageDate"></i-date-picker>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="align-items-top">
              　　　　是否符合中央補助原則（請逐項檢核。
              <a href="https://lawweb.pcc.gov.tw/LawContent.aspx?id=FL003750" target="連結目標" title="公共設施災後復建工程經費審議及執行作業要點">公共設施災後復建工程經費審議及執行作業要點</a>
              ）
            </b-form-row>
            <b-form-row class="align-items-top">
              <b-form-radio-group v-model="queryOptions.arrRadio[0].text" :options="queryOptions.type" class="pt-1 pl-5 ml-5">
              非屬災害消防、防汛、搶險、搶修等緊急搶救措施。
              </b-form-radio-group>
            </b-form-row>
            <b-form-row class="align-items-top">
              <b-form-radio-group  v-model="queryOptions.arrRadio[1].text" :options="queryOptions.type" class="pl-5 ml-5">
              非屬土方清除、疏濬、機具設備、用地、拆遷補償等非工程項目，與僅具宣傳、景觀功能之設施及植栽。
              </b-form-radio-group>
            </b-form-row>
            <b-form-row class="align-items-top">
              <b-form-radio-group  v-model="queryOptions.arrRadio[2].text" :options="queryOptions.type" class="pl-5 ml-5">
              非屬因年久失修等非天然災害造成之損失案件。
              </b-form-radio-group>
            </b-form-row>
            <b-form-row class="align-items-top">
              <b-form-radio-group  v-model="queryOptions.arrRadio[3].text" :options="queryOptions.type" class="pl-5 ml-5">
              非屬道路工程中路樹、路燈、反射鏡及交通號誌等涉及交通安全，需於災後立即施作之措施。(若需與道路復建工程一併施作者，不在此限)
              </b-form-radio-group>
            </b-form-row>
            <b-form-row class="align-items-top">
              <b-form-radio-group  v-model="queryOptions.arrRadio[4].text" :options="queryOptions.type" class="pl-5 ml-5">
              非屬公共造產或其他由各級政府所經營具有經濟價值之事業。
              </b-form-radio-group>
            </b-form-row>
            <b-form-row class="align-items-top">
              <b-form-radio-group  v-model="queryOptions.arrRadio[5].text" :options="queryOptions.type" class="pl-5 ml-5">
              非屬私人設施。
              </b-form-radio-group>
            </b-form-row>
            <b-form-row class="align-items-top">
              <b-form-radio-group  v-model="queryOptions.arrRadio[6].text" :options="queryOptions.type" class="pl-5 ml-5">
              擇一報請中央政府主管機關或行政院協助，無重覆提報。
              </b-form-radio-group>
            </b-form-row>
            <b-form-row class="align-items-top">
              <b-form-radio-group  v-model="queryOptions.arrRadio[7].text" :options="queryOptions.type" class="pb-1 pl-5 ml-5">
              有具體保護對象，且屬合法開發利用者。
              </b-form-radio-group>
            </b-form-row>
            <b-form-row class="pb-2">
              　　　　　　　　　　　　　　　若任一項目為「否」，不符中央補助原則，無需進行下列評估，<b>請本於權責自籌經費辦理。</b>
            </b-form-row>
            <b-form-row class="align-items-bottom">
              　　　　現況及災損情形概估：<br>
              　　　　 1. 災損說明（如影響戶數、災害區域大小、預估經濟損失）。<br>
            </b-form-row>
            <b-form-row class="align-items-top">
              <i-form-group-check class="col-8" label-cols="1" content-cols="11">
                <b-form-textarea v-model="$v.damageDescript.$model" rows="4" maxlength="4000" placeholder="災損說明(如影響戶數100)"></b-form-textarea>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="align-items-top">
              　　　　 2. 立即危險性之評估（以公共危險為主，可補充文字敍述,，如：可能造成人民生命威脅…）<br>
            </b-form-row>
            <b-form-row class="pt-2 pb-3">
              　　　　　　　　立即危險高　
              <b-form-radio v-model="$v.dangerousDegree.$model" value="5">5　</b-form-radio>
              <b-form-radio v-model="$v.dangerousDegree.$model" value="4">4　</b-form-radio>
              <b-form-radio v-model="$v.dangerousDegree.$model" value="3">3　</b-form-radio>
              <b-form-radio v-model="$v.dangerousDegree.$model" value="2">2　</b-form-radio>
              <b-form-radio v-model="$v.dangerousDegree.$model" value="1">1　</b-form-radio>
              立即危險低
            </b-form-row>           
            <b-form-row class="align-items-top">
              <i-form-group-check :labelStar="true" class="col-8" label-cols="1" content-cols="11">
                <b-form-textarea v-model="$v.dangerousDescript.$model" rows="4" maxlength="4000" placeholder="立即危險性之評估(可補充文字敍述以公共危險為主)" :state="validateState($v.dangerousDescript)"></b-form-textarea>
              </i-form-group-check>
            </b-form-row>
            
            <div class="button-float-right">
              <b-button-toolbar class="mt-5" v-if="formStatusRef === 'modify'">
                <i-button class="ml-1" type="save" @click="submitForm('modify')"></i-button>
                <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
                <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
                <i-button class="ml-1" type="trash" @click="deleteProject"></i-button>
              </b-button-toolbar>
              <b-button-toolbar class="mt-5" v-else-if="formStatusRef === 'create'">
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
import { reactive, Ref, ref, watch, toRefs, onActivated } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import { navigateByNameAndParams } from '@/router/router';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';

export default {
  name: 'rec0303-edit-project',
  props: {
    formStatusForward: {
      type: String,
      required: true,
    },
    recProject: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    //傳入狀態
    const formStatusForwardProp = toRefs(props).formStatusForward;

    const recProjectProp = toRefs(props).recProject;

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    //空值表單
    const formDefault = {
      projectCode: '',
      projectName: '',
      typhoonNo: '',
      projectNo: '',
      analysisNo: '',
      damageDate: undefined,
      damageDescript: '',
      dangerousDegree: 3,
      dangerousDescript: '',
      isReviewChangeAmount: false,
      isSurveyPlan: false,
      createUser: '',
      createTime: undefined,
      updateUser: '',
      updateTime: undefined,
    };

    //預存好的form
    const form = reactive(Object.assign({}, formDefault));

    //檢核規則
    const rules = {
      projectCode: {notNull: required},
      projectName: {notNull: required},
      typhoonNo: {},
      projectNo: {},
      analysisNo: {},
      damageDate: {},
      dangerousDegree: {},
      damageDescript: {},
      dangerousDescript: {notNull: required},
      isReviewChangeAmount: {},
      isSurveyPlan: {},
    };

    // 下拉選單選項
    const queryOptions = reactive({
      projectCode: [
        { value: '', text: '全部' },
        { value: 'A1', text: 'A1：水利工程' },
        { value: 'B1', text: 'B1：觀光工程' },
        { value: 'C1', text: 'C1：道路橋梁工程-公路系統工程' },
        { value: 'C2', text: 'C2：道路橋梁工程-村里聯絡道路工程' },
      ],
      type: [
        { value: 'Y', text: '是' },
        { value: 'N', text: '否' },
      ],
      arrRadio: [
        { text: 'Y' },
        { text: 'Y' },
        { text: 'Y' },
        { text: 'Y' },
        { text: 'Y' },
        { text: 'Y' },
        { text: 'Y' },
        { text: 'Y' },
      ],
    });

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //是否要重新查詢
    const isReload: boolean = ref(null);
    //modal物件
    const $bvModal = useBvModal();

    //返回查詢畫面
    const toQueryView = () => {
      changeFormStatus(formStatusEnum.CREATE);
      createDefaultValue(formDefault);
      const param = { isReload: isReload.value };
      handleBack(param);
    };

    //創建初始值
    const createDefaultValue = (data: Object) => {
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
            insertProject();
            $bvModal.msgBoxOk('資料新增成功');
            changeFormStatus(formStatusEnum.MODIFY); //改成修改按鈕
          }
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
        url = `/rec-projects/${form.projectNo}`;
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

    //刪除
    const deleteProject = () => {
      $bvModal.msgBoxConfirm('刪除此工程將連同相關基本資料、災損圖片、概念設計示意圖說文件、復建工項等資料一併刪除，確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          let url: string;
          url = `/rec-projects/${form.projectNo}`;
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
    const insertProject = () => {
      //4個系統用欄位
      form.createUser = userIdentity;
      form.updateUser = userIdentity;
      form.createTime = new Date();
      form.updateTime = new Date();
      form.typhoonNo= recTyphoonNoProp.value;
      axios
        .post(`/rec-projects`, form)
        .then(({ data }) => {
          isReload.value = true;
          createDefaultValue(data);
          recProjectProp.value = data; //新建資料存回 recProject
          formStatusForwardProp.value = 'modify'; //create -> modify
          form.projectNo = data.projectNo; //放入新增加的 projectNo
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    };

    watch(
      recProjectProp,
      () => {
        if (formStatusForwardProp.value === 'modify') {
          createDefaultValue(recProjectProp.value);
          changeFormStatus(formStatusEnum.MODIFY);
        } else {
          Object.assign(form, formDefault);
          createDefaultValue(formDefault);
          changeFormStatus(formStatusEnum.CREATE);
        }
      },
      { immediate: true },
    );

    watch(queryOptions.arrRadio,()=>{
      for (let i=0;i<queryOptions.arrRadio.length;i++){
        if (queryOptions.arrRadio[i].text==='N') {
          $bvModal.msgBoxConfirm('檢核結果不符中央補助原則，請本於權責自籌經費辦理。').then((isOK: boolean) => {
            if (isOK) {
              toQueryView();
              for (let i=0;i<queryOptions.arrRadio.length;i++){
                queryOptions.arrRadio[i].text='Y';
              }
            } else {
              for (let i=0;i<queryOptions.arrRadio.length;i++){
                queryOptions.arrRadio[i].text='Y';
              }
            }
          })
        }
      }
    }
    );

    return {
      $v,
      formStatusEnum,
      formStatusRef,
      changeFormStatus,
      reset,
      validateState,
      toQueryView,
      submitForm,
      queryOptions,
      deleteProject,
      formStatusForwardProp,
      userIdentity,
      recProjectProp,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

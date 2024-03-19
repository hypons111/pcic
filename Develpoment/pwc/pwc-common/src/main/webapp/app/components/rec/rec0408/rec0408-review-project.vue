<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          工程內容或經費變更情形概要
        </h5>
      </div>
      <div class="card-body col-12">
        <b-row align-h="center">
          <b-col cols="11">
            <table width="100%">
              <tr>
                <td width="15%">註銷此工程</td>
                <td colspan="2"><b-form-radio-group v-model="$v.isCancelProject.$model" :options="queryOptions.radioSel" disabled/></td>
              </tr>
              <tr>
                <td width="15%">目前核定經費</td>
                <td width="20%"><b-form-input v-model="$v.originalAmount.$model" disabled></b-form-input></td>
                <td width="65%">（千元）</td>
              </tr>
              <tr>
                <td width="15%">變更後概估經費</td>
                <td width="20%"><b-form-input v-model="$v.requestAmount.$model" disabled></b-form-input></td>
                <td width="65%">（千元）</td>
              </tr>
            </table>
            <table width="100%">
              <tr>
                <td width="15%">目前核定內容</td>
                <td><b-form-textarea v-model="$v.requestOpinion.$model" rows="3" maxlength="4000" placeholder="目前核定內容" disabled></b-form-textarea></td>
              </tr>
              <tr>
                <td>變更後內容</td>
                <td><b-form-textarea v-model="$v.requestOpinion.$model" rows="3" maxlength="4000" placeholder="變更後內容" disabled></b-form-textarea></td>
              </tr>
            </table>
            <table width="100%">
              <tr>
                <td width="15%">註銷或變更原因說明</td>
                <td colspan="2"><b-form-textarea v-model="$v.requestReason.$model" rows="3" maxlength="4000" placeholder="註銷或變更原因說明" disabled></b-form-textarea></td>
              </tr>
              <tr>
                <td>照片</td>
                <td colspan="2"><b-form-file v-model="picFile" @change="picSelected" plain disabled></b-form-file>
                <b-img v-if="picPreview" class="mt-1" style="height: 300px" alt="Image" fluid rounded :src="picPreview" />
                <!-- <b-button @click="uploadPicFile">上傳</b-button> -->
                </td>
              </tr>
            </table>
          </b-col>
        </b-row>
      </div>

      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          審核意見
        </h5>
      </div>
      <div class="card-body col-12">
        <b-row align-h="center">
          <b-col cols="11">
            <table width="100%">
              <tr>
                <td width="15%">核可變更經費</td>
                <td width="20%"><b-form-input v-model="$v.approveAmount.$model"></b-form-input></td>
                <td width="65%">（千元）</td>
              </tr>
              <tr>
                <td width="15%">完工期限</td>
                <td width="20%"><b-form-input v-model="$v.approveFinishDate.$model"></b-form-input></td>
                <td width="65%"></td>
              </tr>
            </table>
            <table width="100%">
              <tr>
                <td width="15%">註銷或變更原因</td>
                <td width="30%"><b-form-select v-model="$v.requestReasonList.$model" :options="queryOptions.requestReasonList" :state="validateState($v.requestReasonList)"></b-form-select></td>
                <td width="55%"></td>
              </tr>
            </table>
            <table width="100%">
              <tr>
                <td width="15%">意見</td>
                <td><b-form-textarea v-model="$v.approveOpinion.$model" rows="4" maxlength="4000" placeholder="審核意見"></b-form-textarea></td>
              </tr>
            </table>
          </b-col>
        </b-row>
        <div class="text-center mt-4">
          <i-button class="ml-1" type="save" @click="toEditInfo"></i-button>
          <b-button variant="info" @click="toEditInfo">核定</b-button>
          <b-button variant="info" @click="toEditInfo">退回</b-button>
          <b-button variant="info" @click="toEditInfo">不同意</b-button>
          <i-button class="ml-1" type="arrow-counterclockwise" @click="toEditInfo"></i-button>
        </div>
      </div>



    </div>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs, onActivated, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import { navigateByNameAndParams } from '@/router/router';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { formatDateInChinese } from '@/shared/date/minguo-calendar-utils';
import { commaFormatter } from '@/shared/formatter/common';

export default {
  name: 'rec0408-review-project',
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
      isCancelProject:false,
      originalAmount:9852,
      requestAmount:9876,
      requestOpinion:'護岸 (m2): 10550 * 378 = 3987900\n排水溝 (m):全寬6.3m 23100 * 30 = 693000\n臨時擋土牆結構 (支):打設10M鋼軌樁含擋土鐵板 5390 * 126 = 679140\n護岸 (m2): 10000 * 380 = 3800000\n排水溝 (m):全寬4.5m 21700 * 76 = 1649200\n其他: 16000',
      requestReasonList:'',
      requestReason:'其他',
      approveAmount:'9876',
      approveFinishDate:'2021.02.01',
      approveOpinion:'無意見',
      createUser: '',
      createTime: undefined,
      updateUser: '',
      updateTime: undefined,
    };

    // 下拉選單選項
    const queryOptions = reactive({
      radioSel: [
        { value: true, text: '是' },
        { value: false, text: '否' },
      ],
      requestReasonList: [
        { value: '', text: '請選擇' },
        { value: '(1)用地使用協調', text: '(1)用地使用協調' },
        { value: '(8)其他特殊因素', text: '(8)其他特殊因素' },
      ],
    });

    //預存好的form
    const form = reactive(Object.assign({}, formDefault));

    //檢核規則
    const rules = {
      isCancelProject:{},
      originalAmount:{},
      requestAmount:{},
      requestOpinion:{},
      requestReasonList:{},
      requestReason:{},
      approveAmount:{},
      approveFinishDate:{},
      approveOpinion:{},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //是否要重新查詢
    const isReload: boolean = ref(null);
    //modal物件
    const $bvModal = useBvModal();

    //返回查詢畫面
    const toQueryView = () => {
      const param = { isReload: isReload.value };
      handleBack(param);
    };

    //轉到申請經費調整
    // const toEditProject = () => {
    //   navigateByNameAndParams('rec0408-edit-project', { isNotKeepAlive: true });
    // };

    //轉到申請經費調整檢視畫面
    // const toViewProject = () => {
    //   navigateByNameAndParams('rec0408-view-project', { isNotKeepAlive: true });
    // };

    //轉到復建執行管控檢視案件畫面
    const toEditInfo = () => {
      navigateByNameAndParams('rec0403-edit-info', { isNotKeepAlive: true });
    };

    return {
      $v,
      formStatusEnum,
      formStatusRef,
      queryOptions,
      reset,
      validateState,
      toQueryView,
      formStatusForwardProp,
      recProjectProp,
      formatDateInChinese,
      // toViewProject,
      // toEditProject,
      toEditInfo,
    };
  },
  filters: {
    commaFilter: (value: any) => commaFormatter(value),
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

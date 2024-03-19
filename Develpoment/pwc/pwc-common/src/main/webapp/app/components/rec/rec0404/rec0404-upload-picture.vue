<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          完工照片上傳
        </h5>
      </div>
      <div class="card-body col-12">
        <b-row align-h="center">
          <b-col cols="11">
            <table width="100%">
              <tr>
                <td colspan="2">
                  <b-img fluid rounded src="https://th.bing.com/th/id/R.46cedf613744d16cfaa6261066158f06?rik=9ePdF%2b4gQiIwjQ&riu=http%3a%2f%2fshiokawa-kougyou.co.jp%2fwp-content%2fuploads%2f2019%2f09%2fproject03.jpg&ehk=dkJ5Fe5giz4ZsEYuxJscqV5qArYAFVTVSN0quu0iUBY%3d&risl=&pid=ImgRaw&r=0" style="height: 100px" alt="Image"></b-img>
                  <b-img fluid rounded src="https://th.bing.com/th/id/R.86e5e39645d0dd4461840d033d4f1340?rik=SSnKA1VoKpt7wQ&riu=http%3a%2f%2fwww.nagashima-kensetsu.co.jp%2fwp-content%2fuploads%2f2019%2f04%2f%E5%B9%B3%E6%88%9030%E5%B9%B4%E5%BA%A6%E6%BA%96%E7%94%A8%E6%B2%B3%E5%B7%9D%E6%A0%97%E5%8E%9F%E5%B7%9D%E8%AD%B7%E5%B2%B8%E6%95%B4%E5%82%99%E5%B7%A5%E4%BA%8B.jpg&ehk=MGcjhjdjzZnhdKMfHt5Z3OE%2bnyUAuV0dV6yrjcWzNTk%3d&risl=&pid=ImgRaw&r=0" style="height: 100px" alt="Image"></b-img>
                  <b-img fluid rounded src="https://www.teamjoy.com.tw/uploads/product/77/20190829090744_thum.JPG" style="height: 100px" alt="Image"></b-img>
                </td>
              </tr>
              <tr>
                <td width="40%">
                  <b-form-file v-model="picFile" @change="picSelected" plain></b-form-file>
                  <b-img v-if="picPreview" class="mt-1" style="height: 300px" alt="Image" fluid rounded :src="picPreview" />
                </td>
                <td width="60%">
                  <b-button class="p-1" variant="info" @click="uploadPicFile">上傳</b-button>
                </td>
              </tr>
            </table>
          </b-col>
        </b-row>
        <div class="text-center mt-4">
          <i-button class="ml-1" type="arrow-counterclockwise" @click="toQueryView"></i-button>
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
  name: 'rec0408-edit-project',
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
      approveAmount:'',
      createUser: '',
      createTime: undefined,
      updateUser: '',
      updateTime: undefined,
    };

    //預存好的form
    const form = reactive(Object.assign({}, formDefault));

    //檢核規則
    const rules = {
      isCancelProject:{},
      originalAmount:{},
      requestAmount:{notNull: required},
      requestOpinion:{},
      requestReasonList:{},
      requestReason:{notNull: required},
      approveAmount:{},
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

    //轉到申請經費調整審核意見
    const toReviewProject = () => {
      navigateByNameAndParams('rec0408-review-project', { isNotKeepAlive: true });
    };

    const picFile: Ref<File> = ref(null);
    const picPreview = ref(null);

    function picSelected(e) {
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

    function uploadPicFile() {
      console.log(formStatusRef.value);
      const param = new FormData();
      param.append('file', picFile.value);
      axios
        // .put('/adm-files/upload', param, { headers: { 'Content-Type': 'multipart/form-data' } })
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
      reset,
      validateState,
      toQueryView,
      formStatusForwardProp,
      recProjectProp,
      formatDateInChinese,
      toReviewProject,
      picFile,
      picPreview,
      picSelected,
      uploadPicFile,
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

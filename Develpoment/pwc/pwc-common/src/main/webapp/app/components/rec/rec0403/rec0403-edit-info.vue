<template>
  <div class="container">
    <div class="card">
      <h5 class="text-center font-weight-bold">
          南投縣政府109年5月豪雨公共設施災後復建工程<br>災害查估紀錄及復建經費概估
      </h5>
      <div class="card-body col-12">
        <b-row align-h="center">
          <b-col cols="11">
            <table width="100%">
              <tr>
                <td>一、工程代碼：{{queryOptions.projectCode[2].text}}</td>
              </tr>
              <tr>
                <td>　　核定序號：{{$v.approveNo.$model}}</td>
              </tr>
              <tr>
                <td>二、復建工程名稱：{{$v.projectName.$model}}</td>
              </tr>
              <tr>
                <td>三、災害發生地點：南投市漳興里復興路、和興坑排水</td>
              </tr>
              <tr>
                <td>四、核定經費：{{$v.reviewAmount.$model}}</td>
              </tr>
              <tr>
                <td>五、工程發包日期：{{$v.damageDate.$model}}</td>
              </tr>
              <tr>
                <td>六、工程進度：90.90％</td>
              </tr>
              <tr>
                <td>七、實際完工日期：</td>
              </tr>
              <tr>
                <td>八、完工期限：2021-04-21</td>
              </tr>
              <tr>
                <td>九、復建工程內容、數量及單價：</td>
              </tr>
              <tr>
                <td><b>　　衛星定位點：X座標 {{$v.gpsX.$model}}　Y座標 {{$v.gpsY.$model}}（TWD-97）</b></td>
              </tr>
              <tr>
                <table border="2" cellpadding="2" width="100%">
                  <tr>
                    <th width="15%" class="text-center">構造物</th>
                    <th width="30%" class="text-center">構造物形式</th>
                    <th width="5%" class="text-center">單位</th>
                    <th width="10%" class="text-center">單價(元)</th>
                    <th width="5%" class="text-center">數量</th>
                    <th width="10%" class="text-center">複價(元)</th>
                    <th width="25%" class="text-center">備註</th>
                  </tr>
                  <tr v-for="(item,key) in table2.data">
                    <td style="vertical-align : middle;">{{item.constItemName}}</td>
                    <td style="vertical-align : middle;">{{item.constModelName}}</td>
                    <td style="vertical-align : middle;">{{item.workItemUnit}}</td>
                    <td style="vertical-align : middle;text-align : right;">{{item.workItemPrice}}</td>
                    <td style="vertical-align : middle;text-align : right;">{{item.workItemQty}}</td>
                    <td style="vertical-align : middle;text-align : right;">{{item.workItemAmount}}</td>
                    <td style="vertical-align : middle;">{{item.remark}}</td>
                  </tr>
                </table>
              </tr>
              <tr>
                <table width="100%">
                  <tr>
                    <td width="12%" valign="top">十、審查意見：</td>
                    <td><b-form-textarea disabled=true v-model="$v.reviewDescript.$model" rows="4" maxlength="4000" placeholder="立即危險性之評估(可補充文字敍述以公共危險為主)"></b-form-textarea></td>
                  </tr>
                </table>
              </tr>
              <tr>
                <table width="30%">
                  <tr>
                    <td width="50%">調查規劃作業期限：</td>
                    <td width="50%"><b-form-input disabled=true /></td>
                  </tr>
                </table>
              </tr>
              <tr>
                <table width="40%">
                  <tr>
                    <td width="50%">調查規劃作業預計結標日：</td>
                    <td width="50%"><b-form-input disabled=true /></td>
                  </tr>
                </table>
              </tr>
              <tr>
                <table width="40%">
                  <tr>
                    <td width="50%">調查規劃作業預計完成日：</td>
                    <td width="50%"><b-form-input disabled=true /></td>
                  </tr>
                </table>
              </tr>
              <tr>
                <table width="40%">
                  <tr>
                    <td width="50%">調查規劃作業預計送工程會：</td>
                    <td width="50%"><b-form-input disabled=true /></td>
                  </tr>
                </table>
              </tr>
              <tr>
                <td>十一、照片：</td>
              </tr>
              <tr>
                <td>
                  <b-img fluid rounded src="https://th.bing.com/th/id/R.46cedf613744d16cfaa6261066158f06?rik=9ePdF%2b4gQiIwjQ&riu=http%3a%2f%2fshiokawa-kougyou.co.jp%2fwp-content%2fuploads%2f2019%2f09%2fproject03.jpg&ehk=dkJ5Fe5giz4ZsEYuxJscqV5qArYAFVTVSN0quu0iUBY%3d&risl=&pid=ImgRaw&r=0" style="height: 100px" alt="Image"></b-img>
                  <b-img fluid rounded src="https://th.bing.com/th/id/R.86e5e39645d0dd4461840d033d4f1340?rik=SSnKA1VoKpt7wQ&riu=http%3a%2f%2fwww.nagashima-kensetsu.co.jp%2fwp-content%2fuploads%2f2019%2f04%2f%E5%B9%B3%E6%88%9030%E5%B9%B4%E5%BA%A6%E6%BA%96%E7%94%A8%E6%B2%B3%E5%B7%9D%E6%A0%97%E5%8E%9F%E5%B7%9D%E8%AD%B7%E5%B2%B8%E6%95%B4%E5%82%99%E5%B7%A5%E4%BA%8B.jpg&ehk=MGcjhjdjzZnhdKMfHt5Z3OE%2bnyUAuV0dV6yrjcWzNTk%3d&risl=&pid=ImgRaw&r=0" style="height: 100px" alt="Image"></b-img>
                  <b-img fluid rounded src="https://www.teamjoy.com.tw/uploads/product/77/20190829090744_thum.JPG" style="height: 100px" alt="Image"></b-img>
                </td>
              </tr>
              <tr>
                <td>十二、相關技師：</td>
              </tr>
              <tr>
                <td><b-form-input v-model="$v.technician.$model" disabled=true></b-form-input></td>
              </tr>
              <tr>
                <td>十三、變更紀錄：</td>
              </tr>
              <tr>
                <table border="2" cellpadding="2" width="100%">
                  <tr>
                    <th width="6%" class="text-center">狀態</th>
                    <th width="12%" class="text-center">變更類別</th>
                    <th width="9%" class="text-center">申請日期</th>
                    <th width="41%" class="text-center">申請原因</th>
                    <th width="9%" class="text-center">核可日期</th>
                    <th width="9%" class="text-center">中央機關函復日期</th>
                    <th width="15%" class="text-center">動作</th>
                  </tr>
                  <tr v-for="(item,key) in table1.data">
                    <td style="vertical-align : middle">{{item.actionStatus}}</td>
                    <td style="vertical-align : middle">{{item.actionType}}</td>
                    <td style="vertical-align : middle">{{item.requestDate}}</td>
                    <td style="vertical-align : middle">{{item.requestReason}}</td>
                    <td style="vertical-align : middle">{{item.approveDate}}</td>
                    <td style="vertical-align : middle">{{item.centerApproveDate}}</td>
                    <td>
                      <!-- 間距處理 https://www.gushiciku.cn/pl/gGWZ/zh-tw -->
                      <b-button class="p-1" variant="info" @click="toSend(item)">送出</b-button>
                      <b-button class="p-1" variant="info" @click="toReview(item)">審查</b-button>
                      <b-button class="p-1" variant="info" @click="toView(item)">檢視</b-button>
                    </td>
                  </tr>
                </table>
              </tr>
            </table>
          </b-col>
        </b-row>
        <div class="text-center mt-4">
          <b-button variant="info" @click="toEditCost">申請經費調整</b-button>
          <b-button variant="info" @click="toEditProject">申請工程變更或註銷</b-button>
          <b-button variant="info" @click="toEditExtend">申請完工展延</b-button>
          <b-button variant="info" @click="toEditReview">填寫基本設計審查資料</b-button>
          <b-button variant="info" @click="toUploadPic">上傳完工照片</b-button>
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

export default {
  name: 'rec0403-edit-info',
  props: {
  },
  setup(props, context) {

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    //空值表單
    const formDefault = {
      isLandslides: false,
      isWoodland: false,
      isRepeat: false,
      originalBuiltYear:'1997',
      projectCode: '',
      applySubmitNo:'001',
      approveNo:'014',
      projectName: '109年5月豪雨-南投市和興排水幹線護岸災修復建工程',
      typhoonNo: '',
      projectNo: '',
      analysisNo: '',
      technician: '無技師資料',
      damageDate: '2020-05-20',
      damageDescript: '',
      dangerousDegree: 5,
      dangerousDescript: '評估結果非常的危險要注意施工安全',
      isReviewChangeAmount: false,
      isSurveyPlan: false,
      others:'無補充事項',
      applyAmount: '10,809',
      reviewAmount: '9,852',
      reviewDescript: '1.經現勘提報為2處破損，經現地實際丈量其災損長度約為63m與69m,該段流速較快有淘刷現象，建議溝中溝部分段增設固床工，降低流速。',
      gpsX:'217630',
      gpsY:'2642042',
      createUser: '',
      createTime: undefined,
      updateUser: '',
      updateTime: undefined,
    };

    //預存好的form
    const form = reactive(Object.assign({}, formDefault));

    //檢核規則
    const rules = {
      isLandslides: {},
      isWoodland:{},
      isRepeat:{},
      originalBuiltYear:{},
      projectCode: {notNull: required},
      applySubmitNo:{},
      approveNo:{},
      projectName: {notNull: required},
      typhoonNo: {},
      projectNo: {},
      analysisNo: {},
      technician: {},
      damageDate: {},
      dangerousDegree: {},
      damageDescript: {},
      dangerousDescript: {notNull: required},
      isReviewChangeAmount: {},
      isSurveyPlan: {},
      others:{},
      applyAmount: {},
      reviewAmount: {},
      reviewDescript: {},
      gpsX:{},
      gpsY:{},
    };

    // 下拉選單選項
    const queryOptions = reactive({
      projectCode: [
        { value: '', text: '全部' },
        { value: 'A1', text: 'A1-水利工程' },
        { value: 'B1', text: 'B1-觀光工程' },
        { value: 'C1', text: 'C1-道路橋梁工程-公路系統工程' },
        { value: 'C2', text: 'C2-道路橋梁工程-村里聯絡道路工程' },
      ],
      type: [
        { value: 'Y', text: '是' },
        { value: 'N', text: '否' },
      ],
      radioSel: [
        { value: true, text: '是' },
        { value: false, text: '否' },
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

    const table2 = reactive({data: undefined,});
          // 假資料從此開始
          table2.data = [
            {
              constItemName: '護岸',
              constModelName: 'RC半重力式護岸H 6m*L 63m',
              workItemUnit: 'm2',
              workItemPrice: '10,550',
              workItemQty: '378',
              workItemAmount: '3,987,900',
              remark: '',
            },
            {
              constItemName: '排水溝',
              constModelName: 'RC溝中溝(寬6.3m / 1.5m *深1.0m)',
              workItemUnit: 'm',
              workItemPrice: '23,100',
              workItemQty: '30',
              workItemAmount: '693,000',
              remark: '全寬6.3m',
            },
            {
              constItemName: '臨時擋土牆結構',
              constModelName: '施工擋土支撐',
              workItemUnit: '支',
              workItemPrice: '5,390',
              workItemQty: '126',
              workItemAmount: '679,140',
              remark: '打設10M鋼軌樁含擋土鐵板',
            },
            {
              constItemName: '護岸',
              constModelName: 'RC半重力式護岸H 5m*L 76m',
              workItemUnit: 'm2',
              workItemPrice: '10,000',
              workItemQty: '380',
              workItemAmount: '3,800,000',
              remark: '',
            },
            {
              constItemName: '排水溝',
              constModelName: 'RC溝中溝(寬4.5m / 1.5m *深1.0m)',
              workItemUnit: 'm',
              workItemPrice: '21,700',
              workItemQty: '76',
              workItemAmount: '1,649,200',
              remark: '全寬4.5m',
            },
          ];

    const table1 = reactive({data: undefined,});
            table1.data = [
            {
              actionStatus: '暫存',
              actionType: '工程變更或註銷',
              requestDate: '',
              requestReason: '其他',
              approveDate: '',
              centerApproveDate: '',
            },
            {
              actionStatus: '暫存',
              actionType: '經費調整',
              requestDate: '',
              requestReason: '經費 由9852-->9876(小於1千萬',
              approveDate: '',
              centerApproveDate: '',
            },
            {
              actionStatus: '同意',
              actionType: '工程變更或註銷',
              requestDate: '2021/03/08',
              requestReason: '（１）用地使用協調',
              approveDate: '2021/03/08',
              centerApproveDate: '',
            },
            {
              actionStatus: '申請中',
              actionType: '完工展延',
              requestDate: '2021/08/24',
              requestReason: '（８）其他特殊因素',
              approveDate: '',
              centerApproveDate: '',
            },
          ];

    //返回查詢畫面
    const toQueryView = () => {
      navigateByNameAndParams('rec0401Query', { isNotKeepAlive: true });
    };

    //轉到申請經費調整
    const toEditCost = () => {
      navigateByNameAndParams('rec0406-edit-cost', { isNotKeepAlive: true });
    };

    //轉到申請工程變更或註銷
    const toEditProject = () => {
      navigateByNameAndParams('rec0408-edit-project', { isNotKeepAlive: true });
    };

    //轉到申請完工展延
    const toEditExtend = () => {
      navigateByNameAndParams('rec0409-edit-extend', { isNotKeepAlive: true });
    };

    //轉到填寫基本設計審查資料
    const toEditReview = () => {
      navigateByNameAndParams('rec0411-edit-review', { isNotKeepAlive: true });
    };
    

    //轉到完工照片上傳
    const toUploadPic = () => {
      navigateByNameAndParams('rec0404-upload-picture', { isNotKeepAlive: true });
    };

    //轉到編輯審查畫面
    const toReview = (item:any) => {
      if (item.actionType=="經費調整") {
        navigateByNameAndParams('rec0406-review-cost', { isNotKeepAlive: true });
      } else if (item.actionType=='完工展延') {
        navigateByNameAndParams('rec0409-review-extend', { isNotKeepAlive: true });
      } else if (item.actionType=="工程變更或註銷") {
        navigateByNameAndParams('rec0408-review-project', { isNotKeepAlive: true });
      } else if (item.actionType=='4') {
        
      }
    };

    //轉到編輯送出畫面
    const toSend = (item:any) => {
      if (item.actionType=="經費調整") {
        navigateByNameAndParams('rec0406-edit-cost', { isNotKeepAlive: true });
      } else if (item.actionType=='完工展延') {
        navigateByNameAndParams('rec0409-edit-extend', { isNotKeepAlive: true });
      } else if (item.actionType=="工程變更或註銷") {
        navigateByNameAndParams('rec0408-edit-project', { isNotKeepAlive: true });
      } else if (item.actionType=='4') {
        
      }
    };
    
    //轉到檢視畫面
    const toView = (item:any) => {
      if (item.actionType=="經費調整") {
        navigateByNameAndParams('rec0406-view-cost', { isNotKeepAlive: true });
      } else if (item.actionType=='完工展延') {
        navigateByNameAndParams('rec0409-view-extend', { isNotKeepAlive: true });
      } else if (item.actionType=="工程變更或註銷") {
        navigateByNameAndParams('rec0408-view-project', { isNotKeepAlive: true });
      } else if (item.actionType=='4') {
        
      }
    };
    return {
      $v,
      formStatusEnum,
      formStatusRef,
      reset,
      validateState,
      toQueryView,
      queryOptions,
      formatDateInChinese,
      table1,
      table2,
      toEditCost,
      toEditProject,
      toEditExtend,
      toEditReview,
      toUploadPic,
      toSend,
      toReview,
      toView,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

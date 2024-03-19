<template>
  <div class="container">
    <div class="card">
      <h5 class="text-center font-weight-bold">
          南投縣政府109年5月豪雨公共設施災後復建工程<br>災害查估紀錄及復建經費概估
      </h5>
      <div class="card-body col-12">
        <b-row align-h="center">
          <b-col cols="11">
            <table>
              <tr>
                <td>一、工程代碼：{{queryOptions.projectCode[2].text}}</td>
                <td></td>
              </tr>
              <tr>
                <td>　　提報序號：{{$v.applySubmitNo.$model}}</td>
              </tr>
              <tr>
                <td>二、復建工程名稱：{{$v.projectName.$model}}</td>
                <td></td>
              </tr>
              <tr>
                <td>三、災害發生</td>
                <td></td>
              </tr>
              <tr>
                <td>　　日期：{{formatDateInChinese(new Date($v.damageDate.$model))}}</td>
                <td></td>
              </tr>
              <tr>
                <td>　　災害發生地點：南投市漳興里復興路、和興坑排水（所在或鄰近之河溪、道路或顯著目標）</td>
                <td></td>
              </tr>
              <tr>
                <table>
                  <tr>
                    <td>　　是否屬土石流潛勢溪流影響區 ？</td>
                    <td><b-form-radio-group disabled v-model="$v.isLandslides.$model" :options="queryOptions.radioSel"/></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td>　　是否屬林班地內 ？</td>
                    <td><b-form-radio-group disabled v-model="$v.isWoodland.$model" :options="queryOptions.radioSel"/></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td>　　是否屬重複致災地點 ？</td>
                    <td><b-form-radio-group disabled v-model="$v.isRepeat.$model" :options="queryOptions.radioSel"/>
                    <td>　　{{$v.originalBuiltYear.$model}} 年興建</td>
                  </tr>
                </table>
              </tr>
              <tr>
                <td>四、初步查估結果：</td>
              </tr>
              <tr>
                <td>　（一）是否符合中央補助原則（請逐項檢核）</td>
              </tr>
              <b-form-row class="align-items-top">
                <b-form-radio-group  disabled v-model="queryOptions.arrRadio[0].text" :options="queryOptions.type" class="pt-1 pl-5 ml-5">
                非屬災害消防、防汛、搶險、搶修等緊急搶救措施。
                </b-form-radio-group>
              </b-form-row>
              <b-form-row class="align-items-top">
                <b-form-radio-group  disabled v-model="queryOptions.arrRadio[1].text" :options="queryOptions.type" class="pl-5 ml-5">
                非屬土方清除、疏濬、機具設備、用地、拆遷補償等非工程項目，與僅具宣傳、景觀功能之設施及植栽。
                </b-form-radio-group>
              </b-form-row>
              <b-form-row class="align-items-top">
                <b-form-radio-group  disabled v-model="queryOptions.arrRadio[2].text" :options="queryOptions.type" class="pl-5 ml-5">
                非屬因年久失修等非天然災害造成之損失案件。
                </b-form-radio-group>
              </b-form-row>
              <b-form-row class="align-items-top">
                <b-form-radio-group  disabled v-model="queryOptions.arrRadio[3].text" :options="queryOptions.type" class="pl-5 ml-5">
                非屬道路工程中路樹、路燈、反射鏡及交通號誌等涉及交通安全，需於災後立即施作之措施。(若需與道路復建工程一併施作者，不在此限)
                </b-form-radio-group>
              </b-form-row>
              <b-form-row class="align-items-top">
                <b-form-radio-group  disabled v-model="queryOptions.arrRadio[4].text" :options="queryOptions.type" class="pl-5 ml-5">
                非屬公共造產或其他由各級政府所經營具有經濟價值之事業。
                </b-form-radio-group>
              </b-form-row>
              <b-form-row class="align-items-top">
                <b-form-radio-group  disabled v-model="queryOptions.arrRadio[5].text" :options="queryOptions.type" class="pl-5 ml-5">
                非屬私人設施。
                </b-form-radio-group>
              </b-form-row>
              <b-form-row class="align-items-top">
                <b-form-radio-group  disabled v-model="queryOptions.arrRadio[6].text" :options="queryOptions.type" class="pl-5 ml-5">
                擇一報請中央政府主管機關或行政院協助，無重覆提報。
                </b-form-radio-group>
              </b-form-row>
              <b-form-row class="align-items-top">
                <b-form-radio-group  disabled v-model="queryOptions.arrRadio[7].text" :options="queryOptions.type" class="pb-1 pl-5 ml-5">
                有具體保護對象，且屬合法開發利用者。
                </b-form-radio-group>
              </b-form-row>
              <tr>
                <td>　（二）現況及災損情形概估：</td>
              </tr>
              <tr>
                <td>　　　1. 現況（原保護形式）、災損情形、成因概述</td>
              </tr>
              <tr>
                <td>　　　2. 位置簡圖：</td>
              </tr>
              <tr>
                <td>
                　　　　.<b-img fluid rounded src="https://th.bing.com/th/id/OIP.b0QSXO7utRUXhL1q6lbAqAHaEq?pid=ImgDet&w=218&h=137&c=7&dpr=1.25" style="height: 300px" alt="Image"></b-img>
                
                </td>
              </tr>
              <tr>
                <td>　　　3. 照片：</td>
              </tr>
              <tr>
                <td>
                　　　　.<b-img fluid rounded src="https://image.cache.storm.mg/styles/smg-800xauto-er/s3/media/image/2015/12/30/20151230-053225_U5054_M115345_e46e.jpg?itok=uBCnCUxe" style="height: 300px" alt="Image"></b-img>
                </td>
              </tr>
              <tr>
                <td><b>　　　　工區 1 衛星定位點：X座標 {{$v.gpsX.$model}}　Y座標 {{$v.gpsY.$model}}（TWD-97）</b></td>
              </tr>
              <tr>
                <td>
                　　　　.<b-img fluid rounded src="https://attach.setn.com/newsimages/2019/08/08/2061909-XXL.jpg" style="height: 300px" alt="Image"></b-img>
                </td>
              </tr>
              <tr>
                <td><b>　　　　工區 1 衛星定位點：X座標 {{$v.gpsX.$model}}　Y座標 {{$v.gpsY.$model}}（TWD-97）</b></td>
              </tr>
              <tr>
                <td>
                　　　　.<b-img fluid rounded src="https://image.cache.storm.mg/styles/smg-800xauto-er/s3/media/image/2015/12/30/20151230-053225_U5054_M115346_7ba9.jpg?itok=Mh1bkK2t" style="height: 300px" alt="Image"></b-img>
                </td>
              </tr>
              <tr>
                <td><b>　　　　工區 1 衛星定位點：X座標 {{$v.gpsX.$model}}　Y座標 {{$v.gpsY.$model}}（TWD-97）</b></td>
              </tr>
              <tr>
                <td>4. 災損說明（如影響戶數、災害區域大小、預估經濟損失）。</td>
              </tr>
              <tr>
                <td>　{{$v.damageDescript.$model}}</td>
              </tr>
              <tr>
                <td>5. 立即危險性之評估（以公共危險為主，可補充文字敍述,，如：可能造成人民生命威脅…）</td>
              </tr>
              <b-form-row class="pt-2 pb-3">
              　　立即危險高　
                <b-form-radio disabled v-model="$v.dangerousDegree.$model" value="5">5　</b-form-radio>
                <b-form-radio disabled v-model="$v.dangerousDegree.$model" value="4">4　</b-form-radio>
                <b-form-radio disabled v-model="$v.dangerousDegree.$model" value="3">3　</b-form-radio>
                <b-form-radio disabled v-model="$v.dangerousDegree.$model" value="2">2　</b-form-radio>
                <b-form-radio disabled v-model="$v.dangerousDegree.$model" value="1">1　</b-form-radio>
                立即危險低
              </b-form-row> 
              <tr>
                <td>　{{$v.dangerousDescript.$model}}</td>
              </tr>
            </table>
            <div>
          （三）破壞模式與可能致災原因分析與檢討：
        </div>
        <div>
          <table border="2" cellpadding="2" cellspaceing="2" >
            <tr>
              <th width="5%" class="text-center">項次</th>
              <th width="10%" class="text-center">類型</th>
              <th width="20%" class="text-center">災害情形</th>
              <th width="25%" class="text-center">可能之致災原因</th>
              <th width="40%" class="text-center">範圍及嚴重度</th>
            </tr>
            <tr v-for="(item,key) in table1.data">
              <td style="vertical-align : middle;">{{key+1}}</td>
              <td style="vertical-align : middle;">{{item.type}}</td>
              <td style="vertical-align : middle;">{{item.analysis}}</td>
              <td style="vertical-align : middle;">{{item.reason}}</td>
              <td style="vertical-align : middle;">{{item.range}}</td>
            </tr>
          </table>
        </div>
        <div>
        （四）復建計畫：（以實地勘查結果研擬復建方案，應包括復建工程內容、數量、單價、基本設計相關圖說及經費估算等）
        </div>
        <div>
        1. 復建工程內容、數量及單價：
        </div>
        <div>
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
            <tr>
              <td colspan="7"><b>工區 1 衛星定位點：X座標 25.2342342　Y座標 23.3453455（TWD-97）</b></td>
            </tr>
          </table>
        </div>
        <div>
          <table>
            <tr>
              <td width="12%">2. 初估總經費：</td>
              <td width="10%"><b-form-input disabled=true v-model="$v.applyAmount.$model" maxlength="20"></b-form-input></td>
              <td>（千元）</td>
            </tr>
            <tr>
              <td colspan="3">3. 基本設計示意圖說：</td>
            </tr>
            <tr>
              <td colspan="3">
                　　　　.<b-img fluid rounded src="https://th.bing.com/th/id/R.46cedf613744d16cfaa6261066158f06?rik=9ePdF%2b4gQiIwjQ&riu=http%3a%2f%2fshiokawa-kougyou.co.jp%2fwp-content%2fuploads%2f2019%2f09%2fproject03.jpg&ehk=dkJ5Fe5giz4ZsEYuxJscqV5qArYAFVTVSN0quu0iUBY%3d&risl=&pid=ImgRaw&r=0" style="height: 300px" alt="Image"></b-img>
              </td>
            </tr>
            <tr>
              <td colspan="3">
                　　　　.<b-img fluid rounded src="https://th.bing.com/th/id/R.86e5e39645d0dd4461840d033d4f1340?rik=SSnKA1VoKpt7wQ&riu=http%3a%2f%2fwww.nagashima-kensetsu.co.jp%2fwp-content%2fuploads%2f2019%2f04%2f%E5%B9%B3%E6%88%9030%E5%B9%B4%E5%BA%A6%E6%BA%96%E7%94%A8%E6%B2%B3%E5%B7%9D%E6%A0%97%E5%8E%9F%E5%B7%9D%E8%AD%B7%E5%B2%B8%E6%95%B4%E5%82%99%E5%B7%A5%E4%BA%8B.jpg&ehk=MGcjhjdjzZnhdKMfHt5Z3OE%2bnyUAuV0dV6yrjcWzNTk%3d&risl=&pid=ImgRaw&r=0" style="height: 300px" alt="Image"></b-img>
              </td>
            </tr>
            <tr>
              <td colspan="3">
                　　　　.<b-img fluid rounded src="https://www.teamjoy.com.tw/uploads/product/77/20190829090744_thum.JPG" style="height: 300px" alt="Image"></b-img>
              </td>
            </tr>
            <tr>
              <td colspan="3"><b>　　　　工區 1 衛星定位點：X座標 {{$v.gpsX.$model}}　Y座標 {{$v.gpsY.$model}}（TWD-97）</b></td>
            </tr>
            <tr>
              <td colspan="3">　　4. 其他補充事項：</td>
            </tr>
            <tr>
              <td colspan="3">　　　{{$v.others.$model}}</td>
            </tr>
            <tr>
              <td width="12%">審查建議經費</td>
              <td width="10%"><b-form-input disabled=true v-model="$v.reviewAmount.$model" maxlength="20"></b-form-input></td>
              <td>（千元）</td>
            </tr>
          </table>
          <table width="90%">
            <tr>
              <td width="8%" valign="top">審查意見</td>
              <td width="70%"><b-form-textarea disabled=true v-model="$v.reviewDescript.$model" rows="4" maxlength="4000" placeholder="立即危險性之評估(可補充文字敍述以公共危險為主)" :state="validateState($v.dangerousDescript)"></b-form-textarea></td>
            </tr>
          </table>
        </div>
          </b-col>
        </b-row>
        <div class="text-center mt-4">
          <i-button type="arrow-left" @click="toQueryView"></i-button>
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
  name: 'rec0304-view-project',
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
      isLandslides: false,
      isWoodland: false,
      isRepeat: false,
      originalBuiltYear:'1997',
      projectCode: '',
      applySubmitNo:'001',
      projectName: '109年5月豪雨-南投市和興排水幹線護岸災修復建工程',
      typhoonNo: '',
      projectNo: '',
      analysisNo: '',
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
              type: '水利',
              analysis: '2.護岸損壞',
              reason: '豪雨沖刷',
              range: '1-1.復興路往上游二側，既有漿砌卵石護岸長約45公尺。\n1-2.毀損長度左岸約35公尺，護岸平均高6尺，右岸約28公尺，護岸平均高6公尺。',
            },
            {
              type: '道路',
              analysis: '1.道路流失',
              reason: '豪雨沖刷',
              range: '1-1.復興路往上游二側，道路損壞長度約45公尺。\n1-2.毀損道路位於八里左岸約35公尺，護岸道路需即時修建以利民眾通行。',
            },
            {
              type: '橋樑',
              analysis: '3.下部結構:橋台基礎沖刷',
              reason: '流木或石塊撞擊',
              range: '1-1.復興大橋兩測橋台被石塊撞擊，既有橋台水泥橋柱缺一塊直徑約30公分。\n1-2.毀損水泥橋柱共5根需要修補。',
            },
          ];


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
      formatDateInChinese,
      table1,
      table2,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

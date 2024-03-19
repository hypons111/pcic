<template>
  <div>
    <section class="container pt-4">
      <div class="card">
        <div class="card-header py-1 px-2">
          <h5 class="h5 m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            機關管理員帳號申請
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 機關卡驗證 -->
            <i-form-group-check labelStar class="col-12" label-cols="2" :label="$t('label.agencyCardVerify') + '：'">
              <b-col cols="4" class="text-left">
                <b-form-radio v-model="$v.agencyCardType.$model" value="Y">(是) 插入卡片並輸入序號</b-form-radio>
              </b-col>
              <b-col cols="3" class="text-left">
                <b-form-select id="slotDescription" v-model="$v.slotDescription.$model"></b-form-select>
              </b-col>
              <b-col cols="2" class="text-left">
                <b-form-input cols="4" v-model="$v.smartCard.$model" type="text"></b-form-input>
              </b-col>
              <b-col cols="3">
                <i-button class="mr-1" type="credit-card" credit-card @click="startTest"></i-button>
              </b-col>
              <b-col>
                <b-link cols="3" href="http://localhost:61161/selfTest.htm" target="_blank">　 使用環境建議與檢測</b-link>
              </b-col>
              <b-col cols="12  pt-2" class="text-left">
                <b-form-radio v-model="$v.agencyCardType.$model" value="N"
                  >(否) 未使用機關卡 送出申請後 請發文至公共工程委員會</b-form-radio
                >
              </b-col>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 機關 -->
            <i-form-group-check class="col-12" label-cols="2" labelStar :label="$t('label.agency') + '：'">
              <b-col cols="2" class="text-left">
                <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="D">機關名稱</b-form-radio>
              </b-col>
              <b-col cols="4" class="text-left">
                <b-form-radio v-model="$v.radioType.$model" name="some-radios" value="E">人事行政總處機關代碼</b-form-radio>
              </b-col>
              <b-col cols="6" class="text-left">
                <i-tree-select
                  v-model="$v.wkutNormal.$model"
                  placeholder="請輸入機關關鍵字查詢(至少輸入四個字)"
                  :multiple="false"
                  :async="true"
                  :load-options="loadOptions"
                ></i-tree-select>
              </b-col>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 機關資訊 -->
            <i-form-group-check class="col-12" label-cols="2" :label="$t('label.agencyInfo') + '：'">
              <b-form-row class="col-12 border">
                <b-col class="col-12">{{ $t('label.agencyName') }} : {{ $v.orgName.$model }} </b-col>
                <b-col class="col-12 pt-2">{{ $t('label.agencyAddr') }} : {{ $v.addr.$model }}</b-col>
                <b-col class="col-12 pt-2">{{ $t('label.agencyTel') }} : {{ $v.tel.$model }}</b-col>
                <b-col class="col-12 pt-2">{{ $t('label.agencyFax') }} : {{ $v.fax.$model }}</b-col>
              </b-form-row>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 姓名 -->
            <i-form-group-check labelStar :label="$t('label.chName') + '：'" :item="$v.chName">
              <b-form-input v-model="$v.chName.$model" type="text" :state="validateState($v.chName)"></b-form-input>
            </i-form-group-check>
            <!-- 身分證字號/居留證號 -->
            <i-form-group-check labelStar :label="$t('label.idno') + '：'" :item="$v.idno">
              <b-form-input v-model="$v.idno.$model" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 性別 -->
            <i-form-group-check labelStar content-cols="4" :label="$t('label.sex') + '：'" :item="$v.sex">
              <b-form-select v-model="$v.sex.$model" :options="options.sex" :state="validateState($v.sex)">
                <template #first>
                  <option :value="null">-- 請選擇 --</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 出生日期 -->
            <i-form-group-check labelStar content-cols="5" :label="$t('label.birthday') + '：'" :item="$v.birthday">
              <i-date-picker v-model="$v.birthday.$model" :state="validateState($v.birthday)" placeholder="yyy/MM/dd"></i-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 通訊地址 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.officeAddr') + '：'"
              :item="[$v.workAddrTown, $v.workAddrOther, $v.workAddrCity]"
            >
              <i-address-picker
                :zip.sync="$v.workAddrZip.$model"
                :city.sync="$v.workAddrCity.$model"
                :town.sync="$v.workAddrTown.$model"
                :other.sync="$v.workAddrOther.$model"
                :city-state="validateState($v.workAddrCity)"
                :town-state="validateState($v.workAddrTown)"
                :other-state="validateState($v.workAddrOther)"
              >
              </i-address-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 電話 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="5"
              :label="$t('label.officeTel') + '：'"
              :item="$v.workTel"
            >
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
            <!-- email -->
            <i-form-group-check labelStar :label="$t('label.email') + '：'" :item="$v.email">
              <b-form-input v-model="$v.email.$model" type="text" :state="validateState($v.email)" placeholder="xx@xx.xx"></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 驗證碼 -->
            <i-form-group-check labelStar :label="$t('label.verificationCode') + '：'" :item="$v.verificationCode">
              <b-form-input
                v-model="$v.verificationCode.$model"
                type="text"
                :state="validateState($v.verificationCode)"
                placeholder="點擊圖片可變換驗證碼"
              ></b-form-input>
            </i-form-group-check>
            <i-form-group-check label-cols-md="1" content-cols-md="8">
              <div @click="refreshCode">
                <SIdentify :identifyCodeDefault="identifyCodeRef"></SIdentify>
              </div>
            </i-form-group-check>
          </b-form-row>

          <b-button-toolbar class="d-flex justify-content-center">
            <i-button class="mr-1" type="send-check" :disabled="formStatusRef" @click="saveTask"></i-button>
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
import { required, email, maxLength, nationalId, mobileTel, lineTel, sameAs, address } from '@/shared/validators';
import { parseRocDate } from '@/shared/date/minguo-calendar-utils';
import axios from 'axios';
import { useBvModal } from '@/shared/modal';
import { useValidation, validateState } from '@/shared/form';
import { navigateByNameAndParams } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import SIdentify from '@/shared/form/identify.vue';
import BidUnit10Service from '@/components/bid/bidService/bid-unit10.service';
import ItreeSelect from '@/shared/i-tree-select/i-tree-select.vue';
import { ASYNC_SEARCH } from '@riophae/vue-treeselect';

export default defineComponent({
  name: 'pwc0301',
  components: {
    SIdentify,
    'i-tree-select': ItreeSelect,
  },

  setup(props, context) {
    const bidUnit10Service = new BidUnit10Service();

    const notificationService = useNotification();
    // modal物件
    const $bvModal = useBvModal();

    // 宣告表單物件及初始值，是前後端 接收或傳遞 的資料物件
    const formDefault = {
      userId: '', //帳號userId
      chName: '', //姓名
      idno: '', //證號idno
      sex: '', //性別
      birthday: null, //生日birthday
      workTel: '', //電話 workTel
      mobileTel: '', //行動電話
      workAddrZip: '', //地址 區碼
      workAddrCity: '', //地址 縣市
      workAddrTown: '', //地址 地區
      workAddrOther: '', //地址 路名
      email: '', // 信箱
      verificationCode: '', //驗證碼
      identifyCodeA: '', //驗證碼(圖)
      wkutNormal: null,
      radioType: 'D',
      orgName: '',
      addr: '',
      tel: '',
      fax: '',
      agencyCardType: 'Y',
      status: '',
      auditStatus: '',
      smartCard: '',
      slotDescription: '',
    };
    // 建立一份新的表單物件
    let form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      userId: { required },
      chName: { required, maxLength: maxLength(50) },
      idno: { required, nationalId: nationalId },
      sex: { required },
      birthday: { required },
      workAddrZip: {},
      workAddrCity: { required },
      workAddrTown: { equalsCityBoolean: address(() => form.workAddrCity) },
      workAddrOther: { equalsCityBoolean: address(() => form.workAddrCity) },
      workTel: { required, lineTel: lineTel },
      mobileTel: { required, mobileTel: mobileTel },
      email: { required, email: email, maxLength: maxLength(100) },
      verificationCode: {
        required,
        sameAs: sameAs(
          computed(() => form.identifyCodeA),
          '驗證碼'
        ),
      },
      wkutNormal: {},
      radioType: {},
      orgName: {},
      addr: {},
      tel: {},
      fax: {},
      agencyCardType: {},
      smartCard: {},
      slotDescription: {},
    };

    // 傳入驗證規則，表單物件及初始值
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // const checked = ref([]);

    const options = reactive({
      sex: [
        { value: 'M', text: '男' },
        { value: 'F', text: '女' },
      ],
    });

    //取得使用者資訊
    const userIdentity = ref(useGetters(['account']).account.value);

    //利用tree select 自帶的API來動態產生選項
    function loadOptions({ action, searchQuery, callback }) {
      //判斷注音
      let pattern = /[\u3105-\u3129\u02CA\u02C7\u02CB\u02D9]/;
      if (action === ASYNC_SEARCH && !pattern.test(searchQuery) && searchQuery.toString().length > 3) {
        const config = { headers: { Accept: 'application/json', 'Content-Type': 'application/json' } };
        const agentName = new Promise((resolve, reject) => {
          axios
            .post('/service/adm-agent-findAll', { keyWord: searchQuery.toString(), type: form.radioType }, config)
            .then(res => {
              resolve(res.data);
            })
            .catch(err => {
              reject(err);
            });
        }).then(res => {
          const options = [];
          res.forEach(ele => {
            const agentOption = { id: '', label: '' };
            if (form.radioType === 'D') {
              agentOption.id = ele.orgId;
              agentOption.label = ele.orgName;
              form.userId = ele.pccOrgId;
            } else {
              agentOption.id = ele.orgId;
              agentOption.label = ele.orgId;
              form.userId = ele.pccOrgId;
            }
            options.push(agentOption);
          });
          callback(null, options);
        });
      }
    }

    function saveTask() {
      form.updateUser = 'SYS';
      form.updateTime = new Date();
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal
            .msgBoxConfirm('是否確認申請', {
              headerBgVariant: 'info',
              headerTextVariant: 'light',
              titleTag: 'h4',
              title: '申請確認',
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

    function save() {
      axios
        .put('/service/adm-account-contact/save', form)
        .then(({ data }) => {
          reset();
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    }

    let formStatusRef = ref(false);

    function findAdmAgent() {
      let url = '/service/adm-agent-info';
      axios
        .post(url, form)
        .then(({ data }) => {
          Object.assign(form, data);
          console.log(data.status);
          if (data.status === 'Y') {
            $bvModal.msgBoxOk('機關已申請管理員帳號(姓名、連絡電話)');
            formStatusRef.value = true;
          } else if (data.status === 'N') {
            $bvModal.msgBoxOk('機關已申請管理員帳號停用，請發文至工程會重新啟用管理員帳號');
            formStatusRef.value = true;
          } else if (data.auditStatus === 'S') {
            $bvModal.msgBoxOk('機關已申請管理員帳號審核中，帳號：');
            formStatusRef.value = true;
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }

    watch(
      () => form.wkutNormal,
      newValue => {
        console.log(form.wkutNormal);
        if (form.wkutNormal !== undefined && form.wkutNormal !== null) {
          findAdmAgent();
        } else {
          reset();
        }
      }
    );

    const identifyCode = '';
    const identifyCodes = '1234567890';
    let identifyCodeRef = ref('');

    onMounted(() => {
      makeCode(identifyCodes, 4);
      myLoad();
    });

    //驗證碼
    function randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    }
    //重新產生驗證碼
    function refreshCode() {
      identifyCode = '';
      makeCode(identifyCodes, 4);
    }
    //驗證碼
    function makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        identifyCode += identifyCodes[randomNum(0, identifyCodes.length)];
      }
      identifyCodeRef.value = identifyCode;
      form.identifyCodeA = identifyCodeRef.value;
    }

    const postTarget;
    const timeoutId;
    const output1; //取值來檢核HiCOS版本

    //myLoad 將晶片資料載入及取得讀卡機
    function myLoad() {
      let img = null;
      let ctx;
      let output = '';
      let ua = window.navigator.userAgent;
      if (ua.indexOf('MSIE') == -1 && ua.indexOf('Trident') == -1) {
        //not IE
        img = document.createElement('img');
        img.crossOrigin = 'Anonymous';
        img.src = 'http://localhost:61161/p11Image.bmp';
        let canvas = document.createElement('canvas');
        canvas.width = 2000;
        canvas.height = 1;
        ctx = canvas.getContext('2d');

        img.onload = function () {
          ctx.drawImage(img, 0, 0);
          output = getImageInfo(ctx);
          output1 = output;
          setOutput(output);
        };
        // img.onerror = function () {
        //   alert('未安裝客戶端程式或未啟動服務');
        //   window.history.back();
        //   return;
        // };
      }
    }

    //取得憑證資料 ImageInfo
    function getImageInfo(ctx) {
      const output = '';
      for (let i = 0; i < 2000; i++) {
        const data = ctx.getImageData(i, 0, 1, 1).data;
        if (data[2] == 0) break;
        output = output + String.fromCharCode(data[2], data[1], data[0]);
      }
      if (output == '') output = '{"ret_code": 1979711501,"message": "執行檔錯誤或逾時"}';
      return output;
    }

    //將憑證資訊送出
    function setOutput(output) {
      const ret = JSON.parse(output);
      if (ret.ret_code == 0x76000031) {
        alert(window.location.hostname + '非信任網站，請先加入信任網站');
        window.history.back();
        return;
      }
      //document.getElementById('info').value= JSON.stringify(ret, null, 2);
      const slots = ret.slots;
      const selectSlot = document.getElementById('slotDescription');
      // selectSlot.innerHTML = "<option value='' selected></option>";
      for (let index in slots) {
        if (slots[index].token instanceof Object) {
          const opt = document.createElement('option');
          opt.value = slots[index].slotDescription;
          //opt.innerHTML = slots[index].slotDescription+" 卡號:["+slots[index].token.serialNumber+"]";
          opt.innerHTML = slots[index].slotDescription;
          selectSlot.appendChild(opt);
        }
      }
    }

    //呼叫驗證程式
    function makeSign() {
      postTarget = window.open('http://localhost:61161/popupForm', '簽章中', 'height=200, width=200, left=100, top=20');
      console.log(postTarget);
      timeoutId = setTimeout(checkFinish, 3500);
    }

    function checkFinish() {
      if (postTarget) {
        postTarget.close();
        // $bvModal.msgBoxOk("尚未安裝跨平台網頁元件或未啟動服務");
      }
    }

    //塞入檢測時所需欄位
    function getTbsPackage() {
      const tbsData = {};
      tbsData['tbs'] = 'TBS';
      tbsData['tbsEncoding'] = 'NONE';
      tbsData['hashAlgorithm'] = 'SHA256';
      tbsData['withCardSN'] = 'true';
      tbsData['pin'] = form.smartCard;
      tbsData['nonce'] = '';
      tbsData['func'] = 'MakeSignature';
      tbsData['signatureType'] = 'PKCS7';
      tbsData['slotDescription'] = document.getElementById('slotDescription').value;
      const json = JSON.stringify(tbsData).replace(/\+/g, '%2B');
      return json;
    }

    //處理錯誤訊息
    function setSignature(signature) {
      const ret = JSON.parse(signature);
      const errorMsg = '憑證登入錯誤';
      if (ret.ret_code != 0) {
        errorMsg = MajorErrorReason(ret.ret_code);
        if (ret.last_error) {
          errorMsg = errorMsg + '，' + MinorErrorReason(ret.last_error);
        }
        $bvModal.msgBoxOk(errorMsg);
      } else {
        return ret.signature;
      }
    }

    //取得回傳訊息
    function receiveMessage(event) {
      if (console) console.debug(event);

      //安全起見，這邊應填入網站位址檢查
      if (event.origin != 'http://localhost:61161') return;
      try {
        var ret = JSON.parse(event.data);
        if (ret.func) {
          var tbsData = {};
          if (ret.func == 'getTbs') {
            const json = getTbsPackage();
            postTarget.postMessage(json, '*');
          } else if (ret.func == 'sign') {
            const result = setSignature(event.data);
          } else {
            alert('unknown func ' + ret.func);
          }
        } else {
          if (console) console.error('no func');
          alert('no func ' + ret.func);
        }
      } catch (e) {
        //errorhandle
        if (console) console.error(e);
      }
    }

    //訊息回傳
    if (window.addEventListener) {
      window.addEventListener('message', receiveMessage, false);
    }
    const console = console || { log: function () {}, debug: function () {}, error: function () {} };

    //HiCOS憑證檢查
    function startTest() {
      //輸入pin碼檢查
      const pin = form.smartCard;
      const pinLength = pin.length;
      if (!pin || pinLength == 0) {
        $bvModal.msgBoxOk('憑證密碼 不能為空白');
        return;
      }
      if (pinLength < 6 || pinLength > 8) {
        $bvModal.msgBoxOk('憑證密碼 為6~8碼');
        return;
      }
      //載入憑證必要資訊
      myLoad();
      //檢查HiCOS版本
      const ret = JSON.parse(output1);
      const nVer = window.navigator.appVersion;
      const version = ret.serverVersion;
      if (nVer.indexOf('Win') >= 0) {
        if (version < '1.3.4.103328') {
          $bvModal.msgBoxOk('目前的版本為' + version + '，請升級至1.3.4.103328');
          return;
        }
      } else if (nVer.indexOf('Mac') >= 0) {
        if (version < '1.3.4.13') {
          $bvModal.msgBoxOk('目前的版本為' + version + '，請升級至1.3.4.13');
          return;
        }
      } else if (nVer.indexOf('Linux') >= 0) {
        if (version < '1.3.4.3') {
          $bvModal.msgBoxOk('目前的版本為' + version + '，請升級至1.3.4.3');
          return;
        }
      }
      //驗證及製作簽章 for (http://localhost:61161/popupForm)
      makeSign();
    }

    //錯誤代碼2
    function MinorErrorReason(rcode) {
      switch (rcode) {
        case 0x06:
          return '函式失敗';
        case 0xa0:
          return 'PIN碼錯誤';
        case 0xa2:
          return 'PIN碼長度錯誤';
        case 0xa4:
          return '已鎖卡';
        case 0x150:
          return '記憶體緩衝不足';
        case 0xffffffff80000001:
        case -2147483647:
          return 'PIN碼錯誤，剩餘一次機會';
        case 0xffffffff80000002:
        case -2147483646:
          return 'PIN碼錯誤，剩餘兩次機會';
        default:
          return rcode.toString(16);
      }
    }

    //錯誤代碼1
    function MajorErrorReason(rcode) {
      if (rcode < 0) rcode = 0xffffffff + rcode + 1;
      switch (rcode) {
        case 0x76000001:
          return '未輸入金鑰';
        case 0x76000002:
          return '未輸入憑證';
        case 0x76000003:
          return '未輸入待簽訊息';
        case 0x76000004:
          return '未輸入密文';
        case 0x76000005:
          return '未輸入函式庫檔案路徑';
        case 0x76000006:
          return '未插入IC卡';
        case 0x76000007:
          return '未登入';
        case 0x76000008:
          return '型態錯誤';
        case 0x76000009:
          return '檔案錯誤';
        case 0x7600000a:
          return '檔案過大';
        case 0x7600000b:
          return 'JSON格式錯誤';
        case 0x7600000c:
          return '參數錯誤';
        case 0x7600000d:
          return '執行檔錯誤或逾時';
        case 0x7600000e:
          return '不支援的方法';
        case 0x7600000f:
          return '禁止存取的網域';
        case 0x76000998:
          return '未輸入PIN碼';
        case 0x76000999:
          return '使用者已取消動作';
        case 0x76100001:
          return '無法載入IC卡函式庫檔案';
        case 0x76100002:
          return '結束IC卡函式庫失敗';
        case 0x76100003:
          return '無可用讀卡機';
        case 0x76100004:
          return '取得讀卡機資訊失敗';
        case 0x76100005:
          return '取得session失敗';
        case 0x76100006:
          return 'IC卡登入失敗';
        case 0x76100007:
          return 'IC卡登出失敗';
        case 0x76100008:
          return 'IC卡取得金鑰失敗';
        case 0x76100009:
          return 'IC卡取得憑證失敗';
        case 0x7610000a:
          return '取得函式庫資訊失敗';
        case 0x7610000b:
          return 'IC卡卡片資訊失敗';
        case 0x7610000c:
          return '找不到指定憑證';
        case 0x7610000d:
          return '找不到指定金鑰';
        case 0x76200001:
          return 'pfx初始失敗';
        case 0x76200006:
          return 'pfx登入失敗';
        case 0x76200007:
          return 'pfx登出失敗';
        case 0x76200008:
          return '不支援的CA';
        case 0x76300001:
          return '簽章初始錯誤';
        case 0x76300002:
          return '簽章型別錯誤';
        case 0x76300003:
          return '簽章內容錯誤';
        case 0x76300004:
          return '簽章執行錯誤';
        case 0x76300005:
          return '簽章憑證錯誤';
        case 0x76300006:
          return '簽章DER錯誤';
        case 0x76300007:
          return '簽章結束錯誤';
        case 0x76300008:
          return '簽章驗證錯誤';
        case 0x76300009:
          return '簽章BIO錯誤';
        case 0x76400001:
          return '解密DER錯誤';
        case 0x76400002:
          return '解密型態錯誤';
        case 0x76400003:
          return '解密錯誤';
        case 0x76500001:
          return '憑證尚未生效';
        case 0x76500002:
          return '憑證已逾期';
        case 0x76600001:
          return 'Base64編碼錯誤';
        case 0x76600002:
          return 'Base64解碼錯誤';
        case 0x76700001:
          return '伺服金鑰解密錯誤';
        case 0x76700002:
          return '未登錄伺服金鑰';
        case 0x76700003:
          return '伺服金鑰加密錯誤';
        case 0x76210001:
          return '身分證字號或外僑號碼比對錯誤';
        case 0x76210002:
          return '未支援的憑證型別';
        case 0x76210003:
          return '非元大寶來憑證';
        case 0x76210004:
          return '非中華電信通用憑證管理中心發行之憑證';

        case 0x77100001:
          return '圖形驗證碼不符';
        case 0x77200001:
          return '未輸入附卡授權SNO碼';
        case 0x77200002:
          return '讀附卡授權證發生錯誤:Buffer太小';
        case 0x77200003:
          return '讀附卡授權證發生錯誤:卡片空間不足';
        case 0x77200004:
          return '讀附卡授權證發生錯誤:資料太大';
        case 0x77200005:
          return '讀附卡授權證發生錯誤:DLL載入發生錯誤(E_NOT_LOAD_DLL)';
        case 0x77200006:
          return '讀附卡授權證發生錯誤:支援函數錯誤(E_NOT_SUPPORT_FUNCTION)';
        case 0x77200007:
          return '讀附卡授權證發生錯誤:讀卡slot錯誤(E_SLOT)';
        case 0x77200008:
          return '讀附卡授權證發生錯誤:Index格式錯誤';
        case 0x77200009:
          return '讀附卡授權證發生錯誤:讀卡機未選擇(READER_NOT_SELECT_ERROR)';
        case 0x77200010:
          return '讀附卡授權證發生錯誤:SNO碼錯誤(SNO_EXIST)';
        case 0x77200011:
          return '讀附卡授權證發生錯誤:SNO碼錯誤(SNO_NO_EXIST)';
        case 0x77200101:
          return '寫新憑證功能發生錯誤：寫新憑證前刪除舊憑證發生錯誤';
        case 0x77200102:
          return '寫新憑證功能發生錯誤：要寫入新憑證時發生錯誤';
        case 0x77200103:
          return '寫新憑證功能發生錯誤：輸入內容PIN和SOPIN不可同時有值';
        case 0x77301001:
          return 'JSON PARSER無法處理CONSOLE程式輸入的參數';
        case 0x77301002:
          return 'CONSOLE程式輸入的參數少於指定的參數值';
        case 0x77301003:
          return 'CONSOLE輸入的JSON值中少了指定的func';
        case 0x77301004:
          return '執行BUILDUNBLOCKCARDREQ中少了readername';
        case 0x77301005:
          return '執行BUILDUNBLOCKCARDREQ中少了caname';
        case 0x77301006:
          return '執行BUILDUNBLOCKCARDREQ中少了sid';
        case 0x77301007:
          return '執行BUILDUNBLOCKCARDREQ中少了newpin';
        case 0x77301008:
          return '執行執行UNBLOCKCARD時缺少CMSRESPONSE的值';
        case 0x77301009:
          return '執行UNBLOCKCARD時缺少SID的值';
        case 0x77301010:
          return '執行BUILDREASETUSERPINREQ時缺少READERNAME的值';
        case 0x77301011:
          return '執行BUILDREASETUSERPINREQ時缺少CANAME的值';
        case 0x77301012:
          return '執行RESTUSERPIN時缺少CMSRESPONSE的值';
        case 0x77301013:
          return '執行RESTUSERPIN時缺少SID的值';
        case 0x77301014:
          return '執行BUILDOPENCARDGETUSERPINREQ缺少CAName的值';
        case 0x77301015:
          return '執行BUILDOPENCARDGETUSERPINREQ缺少ReaderName的值';
        case 0x77301016:
          return '執行BUILDOPENCARDGETUSERPINREQ缺少SID的值';
        case 0x77301017:
          return '執行BUILDOPENCARDGETUSERPINREQ缺少NEWPIN的值';
        case 0x77301018:
          return '執行BUILDOPENCARDVALIDATEUSERREQ缺少CMSRESONSE的值';
        case 0x77301019:
          return '執行BUILDOPENCARDVALIDATEUSERREQ缺少SID的值';
        case 0x77301020:
          return '執行OPENCARD缺少CMSRESONSE的值';
        case 0x77301021:
          return '執行OPENCARD缺少SID的值';
        case 0x77301022:
          return '執行OPENCARD缺少RADERNAME的值';
        case 0x77301023:
          return '執行OPENCARD缺少CURRENTPIN的值';
        case 0x77301024:
          return '執行OPENCARD缺少NEWPIN的值';
        case 0x77301025:
          return '無支援此功能名稱';
        case 0x77301026:
          return '執行BUILDREASETUSERPINREQ缺少SID的值';
        case 0x77301027:
          return '執行CHANGEUSERPIN缺少CARDID的值';
        case 0x77301028:
          return '執行BUILDUNBLOCKCARDREQ缺少CARDID的值';
        case 0x77301029:
          return '執行BUILDOPENCARDGETUSERPINREQ缺少CARDID的值';
        case 0x77301030:
          return '執行BUILDREASETUSERPINREQ缺少CARDID的值';
        case 0x77301031:
          return '解密失敗(umakesig)';
        case 0x77301032:
          return '無法開啟簽章程式(umakesig)';
        case 0x77301033:
          return '輸入簽章值內容為空(umakesig)';
        case 0x77301034:
          return '輸入Hash演算法內容為空(umakesig)';
        case 0x77301035:
          return '輸入TBS值為空(umakesig)';
        case 0x77301036:
          return '輸入PIN值為空(umakesig)';
        case 0x77301037:
          return '輸入PIN值解base64失敗(umakesig)';
        case 0x77301038:
          return '簽章結果錯誤(umakesig)';
        case 0x77301039:
          return '簽章結果為空(umakesig)';
        case 0x77301040:
          return '剖析簽章回傳JSON值錯誤(umakesig)';
        case 0x77301041:
          return '呼叫簽章函數錯誤(umakesig)';
        case 0x77301042:
          return '呼叫簽章函數錯誤2(umakesig)';

        case 0xe0000013: //0xE0000013
          return '金鑰不相符';
        case 0xe0000012: //0xE0000012
          return '使用者取消';
        case 0xe0000010: //0xE0000010
          return '建立金鑰容器失敗，可能是因為權限不足';
        case 0xe000000f: //0xE000000F
          return '找不到任一家CA發的該類別用戶憑證，但中華電信該憑證類別中有找到其他用戶';
        case 0xe000000e: //0xE000000E
          return '開啟物件(p7b)失敗';
        case 0xe000000d: //0xE000000D
          return 'HEX字串格式錯誤';
        case 0xe000000c: //0xE000000C
          return 'HEX字串長度錯誤';
        case 0xe000000b: //0xE000000B
          return '寬位元字串轉多位元字串轉換失敗';
        case 0xe000000a: //0xE000000A
          return '開啟CertStore失敗';
        case 0xe0000009: //0xE0000009
          return '匯出檔案失敗';
        case 0xe0000008: //0xE0000008
          return '匯入檔案失敗';
        case 0xe0000007: //0xE0000007
          return '必須輸入檔案路徑';
        case 0xe0000006: //0xE0000006
          return '找不到任一家CA發的該類別用戶憑證';
        case 0xe0000005: //0xE0000005
          return '找不到中華電信該類別用戶憑證，但找得到其他CA發的該類別用戶憑證';
        case 0xe0000004: //0xE0000004
          return '未支援的參加單位代碼';
        case 0xe0000003: //0xE0000003
          return '金鑰的雜湊值不一致';
        case 0xe0000002: //0xE0000002
          return '程式配置記憶體失敗';
        case 0xe0000001: //0xE0000001
          return '找不到由中華電信所核發且合乎搜尋條件的憑證';

        //開卡鎖卡解鎖錯誤碼20200317
        case 0x81000001:
          return '沒有CONTENT_LENGTH';
        case 0x81000002:
          return 'CONTENT_LENGTH_SIZE太大';
        case 0x81000003:
          return '讀取設定檔錯誤';
        case 0x81000004:
          return '解析加密JSON格式錯誤(不是JSON格式)';
        case 0x81000005:
          return '解析加密JSON參數錯誤';
        case 0x81000007:
          return '解析卡管取USERPIN服務JSON格式錯誤';
        case 0x81000008:
          return '解析卡管取USERPIN服務JSON參數錯誤';
        case 0x81000009:
          return '卡管取USERPIN服務卡號比對錯誤';
        case 0x81000010:
          return '解析卡管取SOPIN服務JSON格式錯誤';
        case 0x81000011:
          return '解析卡管取SOPIN服務JSON參數錯誤';
        case 0x81000012:
          return '卡管取SOPIN服務卡號比對錯誤';
        case 0x81000013:
          return '解析RA驗證用戶代碼服務JSON格式錯誤';
        case 0x81000014:
          return '解析RA驗證用戶代碼服務JSON參數錯誤';
        case 0x81000015:
          return '取得伺服器名稱發生錯誤';
        case 0x81000111:
          return '解析請求JSON格式錯誤';
        case 0x81000112:
          return '解析請求JSON參數錯誤';
        case 0x81000113:
          return '解析請求JSON API版本錯誤';
        case 0x81000114:
          return '解析請求JSON METHOD錯誤';
        case 0x81000115:
          return '解析請求JSON 請求逾時，請校時電腦時間';
        case 0x81000201:
          return '用戶代碼錯誤1次';
        case 0x81000202:
          return '用戶代碼錯誤2次';
        case 0x81000203:
          return '用戶代碼錯誤3次';
        case 0x81000221:
          return 'DB連線錯誤';
        case 0x81000222:
          return 'DB連線錯誤';
        case 0x81000223:
          return 'DB連線錯誤';
        case 0x81000224:
          return 'DB卡號不存在';
        case 0x81000225:
          return '此卡號尚未開卡';
        case 0x81000226:
          return '此卡號已開卡';
        case 0x81000227:
          return '用戶代碼已鎖定';
        case 0x81000228:
          return 'DB UNBLOCK錯誤';
        case 0x81000229:
          return 'DB USERPIN錯誤';
        case 0x81000230:
          return 'DB 輸入參數錯誤';
        case 0x81000231:
          return 'DB錯誤，卡號檢查發生例外';
        case 0x81000232:
          return 'DB UNBLOCK解析錯誤';
        case 0x81000233:
          return 'DB USERPIN解析錯誤';
        case 0x81000234:
          return 'DB系統參數不存在';
        case 0x81000235:
          return 'DB系統參數讀取錯誤';
        case 0x81000236:
          return 'DB Log寫入錯誤';
        case 0x81000237:
          return 'RA系統忙碌中(驗證用戶代碼)，請稍後再試';
        case 0x81000238:
          return '卡管服務忙碌中(SO)，請稍後再試';
        case 0x81000239:
          return '卡管服務忙碌中(USER)，請稍後再試';
        case 0x81000301:
          return '連線到RA錯誤';
        case 0x81000302:
          return 'RA回應格式錯誤';
        case 0x81011000:
          return '底層錯誤Buffer size';
        case 0x81011001:
          return '底層錯誤 RSA加密';
        case 0x81011002:
          return '底層錯誤 RSA解密';
        case 0x81011003:
          return '底層錯誤 RSA簽章';
        case 0x81011004:
          return '底層錯誤 RSA驗簽';
        case 0x81011005:
          return '底層錯誤 AES加密';
        case 0x81011006:
          return '底層錯誤 AES解密';
        case 0x82000003:
          return '解析加密JSON格式錯誤(不是JSON格式)';
        case 0x82000004:
          return '解析加密JSON參數錯誤';
        case 0x82000111:
          return '解析JSON格式錯誤';
        case 0x82000112:
          return '解析JSON參數錯誤';
        case 0x82000113:
          return '解析JSON API版本錯誤';
        case 0x82000114:
          return '解析JSON METHOD錯誤';
        case 0x82000115:
          return '用戶代碼參數比對錯誤';
        case 0x82000116:
          return '卡號參數比對錯誤';
        case 0x82000117:
          return 'CANAME參數比對錯誤';
        case 0x82000118:
          return '回應逾時，請校時電腦時間';
        case 0x82000119:
          return '資料參數比對錯誤';
        case 0x83000100:
          return '插入的卡片不符合要求';
        case 0x83000101:
          return '選錯服務，您使用MOICA卡';
        case 0x83000102:
          return '選錯服務，您使用MOEACA卡';
        case 0x83000103:
          return '選錯服務，您使用GCA卡';
        case 0x83000104:
          return '選錯服務，您使用XCA卡';
        case 0x83000105:
          return '輸入之PIN碼格式錯誤';
        case 0x83000106:
          return '輸入之用戶代碼格式錯誤';
        case 0x83000107:
          return 'CA不支援此功能';
        case 0x83000108:
          return '計算RAO PIN發生錯誤';

        case 0x190:
          return '初始化卡片及讀卡機等硬體發生錯誤(P11錯誤)';
        default:
          return rcode.toString(16);
      }
    }

    return {
      formStatusRef,
      saveTask,
      $v,
      form,
      options,
      validateState,
      refreshCode,
      identifyCodeRef,
      loadOptions,
      startTest,
    };
  },
});
</script>

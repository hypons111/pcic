<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>

    <div>
      <span style="color: red">職業安全衛生法</span>
      <a
        href="http://law.moj.gov.tw/LawClass/LawSingle.aspx?Pcode=N0060001&FLNO=37"
        style="color: black; text-decoration: underline !important"
        >第37條</a
      >
      <span style="color: blue">事業單位工作發生職業災害，雇主應...</span>
    </div>
    <div class="card">
      <!--      標頭-->
      <div class="card-header py-1 text-left">
        <h5 class="m-0">工安事件狀況資料</h5>
      </div>
      <!--      標頭(尾)-->
      <div class="card-body col-12">
        <b-form-row
          v-if="formStatusForwardProp === 'modifyView' || formStatusForwardProp === 'createView' || formStatusForwardProp === 'info'"
        >
          <i-form-group-check :label="'發生日期 '" :label-cols="2" :content-cols="10" class="col-12">
            {{ form.sdate | dateToString }}</i-form-group-check
          >
        </b-form-row>
        <b-form-row v-if="formStatusForwardProp === 'info' || formStatusForwardProp === 'delete'">
          <i-form-group-check :label="'罹災人員隸屬單位 '" :label-cols="2" :content-cols="10" class="col-12">
            <div v-if="form.ccnt != null && form.ccnt != ''">{{ ccntName.text }}</div>
            <div v-if="form.ccnt == null || form.ccnt == ''">(未填寫)</div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row v-if="formStatusForwardProp === 'info' || formStatusForwardProp === 'delete'">
          <i-form-group-check :label="'可歸責之廠商1 '" :label-cols="2" :content-cols="10" class="col-12">
            <div v-if="form.respons != null && form.respons != ''">{{ responsName }}</div>
            <div v-if="form.respons == null || form.respons == ''">(未填寫)</div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row v-if="formStatusForwardProp === 'info' || formStatusForwardProp === 'delete'">
          <i-form-group-check :label="'可歸責之廠商2 '" :label-cols="2" :content-cols="10" class="col-12">
            <div v-if="form.respons2 != null && form.respons2 != ''">{{ respons2Name }}</div>
            <div v-if="form.respons2 == null || form.respons2 == ''">(未填寫)</div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row
          v-if="
            formStatusForwardProp === 'info' ||
            formStatusForwardProp === 'createView' ||
            formStatusForwardProp === 'modifyView' ||
            formStatusForwardProp === 'delete'
          "
        >
          <i-form-group-check :label="'管轄單位 '" :label-cols="2" :content-cols="10" class="col-12">
            <div v-if="form.ctUnit != null && form.ctUnit != ''">{{ ctUnitName }}</div>
            <div v-if="form.ctUnit == null || form.ctUnit == ''">(未填寫)</div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row
          v-if="
            formStatusForwardProp === 'info' ||
            formStatusForwardProp === 'createView' ||
            formStatusForwardProp === 'modifyView' ||
            formStatusForwardProp === 'delete'
          "
        >
          <i-form-group-check :label="'災害類型 '" :label-cols="2" :content-cols="10" class="col-12">
            <div v-if="form.stype != null && form.stype != ''">{{ sTypeName }}</div>
            <div v-if="form.stype == null || form.stype == ''">(未填寫)</div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row
          v-if="
            formStatusForwardProp === 'info' ||
            formStatusForwardProp === 'createView' ||
            formStatusForwardProp === 'modifyView' ||
            formStatusForwardProp === 'delete'
          "
        >
          <i-form-group-check :label="'媒介物 '" :label-cols="2" :content-cols="10" class="col-12">
            {{ form.media }}
          </i-form-group-check>
        </b-form-row>
        <b-form-row v-if="formStatusForwardProp === 'delete'">
          <i-form-group-check :label="'傷亡人數 '" :label-cols="2" :content-cols="10" class="col-12">
            死亡 {{ form.diedNum }} 人 受傷 {{ form.hurtNum }} 人
          </i-form-group-check>
        </b-form-row>
        <b-form-row
          v-if="formStatusForwardProp === 'info' || formStatusForwardProp === 'createView' || formStatusForwardProp === 'modifyView'"
        >
          <i-form-group-check :label="'死亡人數 '" :label-cols="2" :content-cols="10" class="col-12">
            {{ form.diedNum }}
          </i-form-group-check>
          <i-form-group-check :label="'受傷人數 '" :label-cols="2" :content-cols="10" class="col-12">
            {{ form.hurtNum }}
          </i-form-group-check>
        </b-form-row>
        <b-form-row
          v-if="
            formStatusForwardProp === 'info' ||
            formStatusForwardProp === 'delete' ||
            formStatusForwardProp === 'createView' ||
            formStatusForwardProp === 'modifyView'
          "
        >
          <i-form-group-check :label="'罹災時災情 '" :label-cols="2" :content-cols="10" class="col-12">
            {{ form.disasterScript }}
          </i-form-group-check>
        </b-form-row>
        <b-form-row v-if="formStatusForwardProp === 'info' || formStatusForwardProp === 'delete'">
          <i-form-group-check :label="'發生前累計工期 '" :label-cols="2" :content-cols="10" class="col-12">
            {{ form.wdayed }}
          </i-form-group-check>
        </b-form-row>
        <b-form-row v-if="formStatusForwardProp === 'info' || formStatusForwardProp === 'delete'">
          <i-form-group-check :label="'備註 '" :label-cols="2" :content-cols="10" class="col-12">
            {{ form.remark }}
          </i-form-group-check>
        </b-form-row>
        <b-form-row
          v-if="
            formStatusForwardProp === 'info' ||
            formStatusForwardProp === 'delete' ||
            formStatusForwardProp === 'createView' ||
            formStatusForwardProp === 'modifyView'
          "
        >
          <i-form-group-check :label="'提報勞動部日期 '" :label-cols="2" :content-cols="10" class="col-12">
            {{ form.peDate | dateToString }}
          </i-form-group-check>
        </b-form-row>
        <b-form-row
          v-if="
            formStatusForwardProp === 'info' ||
            formStatusForwardProp === 'delete' ||
            formStatusForwardProp === 'createView' ||
            formStatusForwardProp === 'modifyView'
          "
        >
          <i-form-group-check :label="'提報文號 '" :label-cols="2" :content-cols="10" class="col-12">
            {{ form.prmNo }}
          </i-form-group-check>
        </b-form-row>
        <b-form-row
          v-if="
            formStatusForwardProp === 'info' ||
            formStatusForwardProp === 'delete' ||
            formStatusForwardProp === 'createView' ||
            formStatusForwardProp === 'modifyView'
          "
        >
          <i-form-group-check :label="'勞動部核定日期 '" :label-cols="2" :content-cols="10" class="col-12">
            <div v-if="form.meDate == null">(未填報)</div>
            <div v-if="form.meDate != null">{{ form.meDate }} <span style="color: blue">(本欄由勞動部指定人員提報)</span></div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row
          v-if="
            formStatusForwardProp === 'info' ||
            formStatusForwardProp === 'delete' ||
            formStatusForwardProp === 'createView' ||
            formStatusForwardProp === 'modifyView'
          "
        >
          <i-form-group-check :label="'核定文號 '" :label-cols="2" :content-cols="10" class="col-12">
            <div v-if="form.meDate == null">(未填報)</div>
            <div v-if="form.meDate != null">{{ form.mrmNo }}</div>
          </i-form-group-check>
        </b-form-row>

        <div style="text-align: center">
          <b-col v-if="formStatusForwardProp === 'info'">
            <i-button type="arrow-left" @click="toConfirm"></i-button>
          </b-col>
          <b-col v-if="formStatusForwardProp === 'createView'">
            <i-button type="check-circle" @click="toConfirm"></i-button>
          </b-col>
          <b-col v-if="formStatusForwardProp === 'modifyView'">
            <i-button type="check-circle" @click="toConfirm"></i-button>
            <i-button type="pencil-square" @click="toModify"></i-button>
          </b-col>
          <!--          <b-button-toolbar v-if="formStatusForwardProp === 'delete'">-->
          <!--            <i-button type="trash" @click="submitForm('delete')"></i-button>-->
          <!--            <i-button type="arrow-left" @click="toCancel"></i-button>-->
          <!--          </b-button-toolbar>-->
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { reactive, ref, Ref, toRef, toRefs, watch } from '@vue/composition-api';
import BidProjectUnitService from '@/components/bid/bidService/bid-project-unit.service';
import { numeric, required } from '@/shared/validators';
import { useValidation } from '@/shared/form';
import { BidPrjSafe } from '@/shared/model/bidModel/bid-prj-safe';
import { useGetters } from '@u3u/vue-hooks';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';

export default {
  name: 'bidAad002Detail',
  components: {
    bidProjectInfo,
  },
  props: {
    formStatusForward: {
      type: String,
      required: true,
    },
    // 由前面傳來的值決定找單一工安事件狀況或者列表
    bidPrjSafeKey: {
      type: Object,
      required: false,
    },
  },

  setup(props, context) {
    // 傳入PK
    const bidPrjSafeKeyProp: Ref<any> = toRef(props, 'bidPrjSafeKey');
    //傳入狀態
    const formStatusForwardProp = toRefs(props).formStatusForward;
    // 跳轉分頁用
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      CREATEVIEW = 'createView',
      MODIFY = 'modify',
      MODIFYVIEW = 'modifyView',
      DELETE = 'delete',
    }
    // modal物件
    const $bvModal = useBvModal();
    // 基本資料
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();
    //其實我也不知道為啥要先給它屬性，如果是空的或是只有一個屬性就沒有響應式
    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

    const bidProjectUnitService = new BidProjectUnitService();

    const notificationService: NotificationService = useNotification();

    const iTable = ref(null);

    /**
     * 撈出project的部分資料(基本資料)
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
          result.typeCode = data;
          Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        });
      });
    }

    // 空值表單
    let cleanTable = {
      wkut: '', // 執行單位
      prjno: '', // 標案編號
      sdate: undefined, // 發生日期
      ccnt: '', // 罹災人員隸屬單位
      respons: '', // 可歸責廠商1
      respons2: '', // 可歸責廠商2
      ctUnit: '', // 管轄單位
      stype: '', // 災害類型
      media: '', // 媒介物
      diedNum: '', //死亡人數
      hurtNum: '', // 受傷人數
      disasterScript: '', //罹災時災情
      wdayed: '', // 發生前累計工期
      remark: '', // 備註
      peDate: '', // 提報勞動部日期
      prmNo: '', // 提報文號
      mrmNo: '', // 核定文號
      meDate: '', // 勞動部核定日期
      createDate: undefined, //新增日期
      createUser: '', //新增人員
      updateDate: undefined, //更新日期
      updateUser: '', //更新人員
      uIp: '', //更新IP
      confOut: '', // 預定執行單位資料
      ctrNo: '', // 管控編號
      ccut: '', // 發生廠商
      medatm: '', // 未使用
      pedatm: '', // 未使用
    };
    //檢核規則
    let rules = {
      wkut: {}, // 執行單位
      prjno: {}, // 標案編號
      sdate: { required: required }, // 發生日期
      ccnt: {}, // 罹災人員隸屬單位
      respons: {}, // 可歸責廠商1
      respons2: {}, // 可歸責廠商2
      ctUnit: {}, // 管轄單位
      stype: {}, // 災害類型
      media: {}, // 媒介物
      diedNum: { required: required, numeric: numeric }, //死亡人數
      hurtNum: { required: required, numeric: numeric }, // 受傷人數
      disasterScript: { required: required }, //罹災時災情
      wdayed: { required: required, numeric: numeric }, // 發生前累計工期
      remark: {}, // 備註
      peDate: {}, // 提報勞動部日期
      prmNo: { required: required }, // 提報文號
      mrmNo: {}, // 核定文號
      meDate: {}, // 勞動部核定日期
      createDate: {}, //新增日期
      createUser: {}, //新增人員
      updateDate: {}, //更新日期
      updateUser: {}, //更新人員
      uIp: {}, //更新IP
      confOut: {}, // 預定執行單位資料
      ctrNo: {}, // 管控編號
      ccut: {}, // 發生廠商
      medatm: {}, // 未使用
      pedatm: {}, // 未使用
    };
    //預設值
    const formDefault = Object.assign({}, cleanTable);
    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    //創建初始值
    const createDefaultValue = (data: BidPrjSafe) => {
      Object.assign(formDefault, data);
      reset();
    };
    // //取得使用者資訊id
    // const userIdentity = ref(useGetters(['account']).account.value).value.id;
    /**
     * 從使用者撈 user, ip的作法。
     * 如果是登錄就有的資訊應從使用者那邊撈出來
     *
     * */
    const store = context.root.$store;
    let userIdentity = store.getters.account.unit.id;

    const handleQuery = bidPrjSafeKeyProp => {
      axios
        .post('/bid-prj-safes/getOne', bidPrjSafeKeyProp.value) //後端分頁
        .then(({ data }) => {
          if (iTable.value) iTable.value.state.pagination.currentPage = 1;

          createDefaultValue(data);

          if (data.ccnt != null && data.ccnt.length > 0) {
            handleCcntName(data.wkut, data.prjno, data.ccnt);
          }

          if (data.respons != null && data.respons.length > 0) {
            handleResponsName(data.respons);
          }

          if (data.respons2 != null && data.respons2.length > 0) {
            handleRespons2Name(data.respons2);
          }

          if (data.ctUnit != null && data.ctUnit.length > 0) {
            handleCtUnitName(data.ctUnit);
          }
          if (data.stype != null && data.stype.length > 0) {
            handleStypeName(data.stype);
          }
        })
        .catch(notificationErrorHandler(notificationService));
    };
    const ccntOptions = ref([]);
    const ccntName = ref();
    const responsName = ref();
    const respons2Name = ref();
    const ctUnitName = ref();
    const sTypeName = ref();

    // 罹災人員隸屬名單
    function handleCcntName(wkut, prjno, code) {
      ccntOptions.value.splice(0, ccntOptions.value.length);
      bidProjectUnitService
        .findBidCcntByKeyWorld(wkut, prjno)
        .then(res => {
          if (res != null && res.length > 0) {
            res.forEach(ele => {
              if (!(ele.pcmut != null && ele.subccut != null)) {
                if (ele.type === '2') {
                  const ccntOption = { value: '', text: '' };
                  ccntOption.value = ele.scut;
                  ccntOption.text = '監造單位--' + ele.name;
                  ccntOptions.value.push(ccntOption);
                } else if (ele.type === '3') {
                  const ccntOption = { value: '', text: '' };
                  ccntOption.value = ele.ccut;
                  ccntOption.text = '承攬廠商--' + ele.name;
                  ccntOptions.value.push(ccntOption);
                }
              } else if (ele.pcmut != null) {
                const ccntOption = { value: '', text: '' };
                ccntOption.value = ele.pcmut;
                ccntOption.text = 'PCM單位--' + ele.name;
                ccntOptions.value.push(ccntOption);
              } else if (ele.subccut != null) {
                const ccntOption = { value: '', text: '' };
                ccntOption.value = ele.subccut;
                ccntOption.text = '分包協力--' + ele.name;
                ccntOptions.value.push(ccntOption);
              }
            });
            let obj = ccntOptions.value.find(item => {
              return item.value === code;
            });
            console.log('find obf', obj);
            ccntName.value = obj;
            // ccntOptions.value.forEach(elem => {
            //   if (elem.value === code) {
            //     ccntName.value.push(elem.text);
            //     console.log('$$$$', ccntName);
            //   }
            // });
          }
        })
        // .catch(notificationErrorHandler(notificationService));
        .catch(err => {
          console.log(err.response);
        });
    }
    // 可歸責廠商的名稱
    function handleResponsName(code) {
      axios
        .get(`/bid-unit-10/getResponsName/${code}`)
        .then(({ data }) => {
          if (data != null) {
            console.log(data);
            responsName.value = data[0].name;
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }
    // 可歸責廠商2的名稱
    function handleRespons2Name(code) {
      axios
        .get(`/bid-unit-10/getResponsName/${code}`)
        .then(({ data }) => {
          if (data != null) {
            respons2Name.value = data[0].name;
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }
    // 管轄單位名稱
    function handleCtUnitName(ctUnit) {
      let dataKey = ctUnit;
      let moduleType = 'BID';
      let dataType = 'BID_017';
      axios
        .get(`/adm-sys-codes/${moduleType}/${dataType}/${dataKey}`)
        .then(({ data }) => {
          if (data != null) {
            ctUnitName.value = data.value;
          }
        })
        .catch(notificationErrorHandler(notificationService));
    }
    // 災害類型名稱
    function handleStypeName(sType) {
      let dataKey = sType;
      let moduleType = 'BID';
      let dataType = 'BID_018';
      axios
        .get(`/adm-sys-codes/${moduleType}/${dataType}/${dataKey}`)
        .then(({ data }) => {
          if (data != null) {
            sTypeName.value = data.value;
          }
        })
        .catch(notificationErrorHandler(notificationService));
      //用來確定err來源
      // .catch(err => {
      // 沒有err.response 因為這個是由後端來的，要是純前端有問題不會有
      //   console.log(err.response);
      // });
    }

    const submitForm = (submitMethod: string) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (submitMethod === 'delete') {
            //提交刪除
            $bvModal.msgBoxConfirm('確認要刪除這筆資料嗎?', {
              title: '警告',
              headerClass: '',
              size: 'sm',
              buttonSize: 'sm',
              okTitle: '刪除',
              cancelTitle: '取消',
            });
            notificationService.success('資料即將成功');
            let bidPrjSafeKey = {
              wkut: form.wkut,
              prjno: form.prjno,
            };
            navigateByNameAndParams('bidAad002Info', { bidPrjSafeKey, formStatusForward: 'delete' });
          }
        }
      });
    };
    // 刪除資料
    const deleteForm = bidPrjSafeKeyProp => {
      axios.delete('/bid-prj-safes/deleteOne', { data: bidPrjSafeKeyProp }).then().catch(notificationErrorHandler(notificationService));
    };
    // 跳轉至編輯頁面
    const toModify = () => {
      reset();
      let bidPrjSafeKey = {
        wkut: form.wkut,
        prjno: form.prjno,
        sdate: form.sdate,
      };
      navigateByNameAndParams('bidAad002EditInfo', { bidPrjSafeKey, formStatusForward: 'modify' });
    };

    //確認
    const toConfirm = () => {
      reset();
      let bidProjectKey = {
        wkut: form.wkut,
        prjno: form.prjno,
      };
      navigateByNameAndParams('bidAad002Info', { bidProjectKey, formStatusForward: 'info' });
    };

    watch(
      bidPrjSafeKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          //基本資料
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);

          // 依照功能來判斷是否要先將資料匯入版面
          if (
            formStatusForwardProp.value === 'modifyView' ||
            formStatusForwardProp.value === 'createView' ||
            formStatusForwardProp.value === 'info'
          ) {
            handleQuery(bidPrjSafeKeyProp);
          }
        }
      },
      { immediate: true }
    );
    return {
      $v,
      bidPrjSafeKeyProp,
      formStatusForwardProp,
      formStatusEnum,
      projectInfo,
      form,
      handleQuery,
      ccntName,
      responsName,
      respons2Name,
      ctUnitName,
      sTypeName,
      reset,
      submitForm,
      toConfirm,
      toModify,
    };
  },
  filters: {
    dateToString: (value: string) => {
      var date = new Date(value);
      const year = (date.getFullYear() - 1911).toString().padStart(3, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      return year + '年' + month + '月' + day + '日';
    },
    responsIdToName: (value: string) => {},
  },
};
</script>

<style scoped></style>

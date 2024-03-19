<template>
  <div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">新增標案基本資料</h5>
      </div>
      <div class="card-body col-12">
        <b-collapse v-model="stepVisible.step1">
          <b-row>
            <b-col class="col-1">
            </b-col>
            <b-col class="col-11">
              <!-- 執行機關 -->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'執行機關：'" :item="$v.wkut">
                  {{ wkutName }}
                </i-form-group-check>
              </b-form-row>
              <!-- 標案名稱 -->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'標案名稱：'" :item="$v.name" :labelStar="true">
                  <b-form-input v-model="$v.name.$model" :state="validateState($v.name)"></b-form-input>
                </i-form-group-check>
              </b-form-row>
              <!-- 專案項目 -->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="3" :label="'專案項目：'" :item="$v.isAdd92">
                  <b-form-select v-model="$v.isAdd92.$model" :options="isAdd92Opts" :state="validateState($v.isAdd92)"></b-form-select>
                </i-form-group-check>
              </b-form-row>
              <!-- 工程類別 -->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="3" :label="'工程類別：'" :item="$v.typeCode" :labelStar="true">
                  <b-form-select v-model="$v.typeCode.$model" :options="typeCodeOpts" :state="validateState($v.typeCode)"></b-form-select>
                </i-form-group-check>
              </b-form-row>
              <!-- 發包預算 -->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'發包預算：'" :item="$v.bdgt1" :labelStar="true">
                  <div class="d-inline">
                    <b-form-input v-model="$v.bdgt1.$model" :state="validateState($v.bdgt1)"></b-form-input>
                  </div>
                  <div class="col-8">
                    <div class="d-inline">千元</div>
                  </div>
                </i-form-group-check>
              </b-form-row>
              <!-- 預定公告日期 -->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'預定公告日期：'" :item="$v.opDate" :labelStar="true">
                  <i-date-picker v-model="$v.opDate.$model" placeholder="yyy/MM/dd" :state="validateState($v.opDate)"></i-date-picker>
                </i-form-group-check>
              </b-form-row>
              <!-- 預定決標日期-->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'預定決標日期：'" :item="$v.pctDate" :labelStar="true">
                  <i-date-picker v-model="$v.pctDate.$model" placeholder="yyy/MM/dd" :state="validateState($v.pctDate)"></i-date-picker>
                </i-form-group-check>
              </b-form-row>
              <!-- 縣市鄉鎮 -->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'縣市鄉鎮：'" :item="$v.placeCode" :labelStar="true">
                  <b-form-select v-model="$v.placeCode.$model" :options="placeCodeOpts" :state="validateState($v.placeCode)"></b-form-select>
                </i-form-group-check>
              </b-form-row>
              <!-- 施工地點-->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'施工地點：'" :item="$v.place">
                  <b-form-input v-model="$v.place.$model" :state="validateState($v.place)"></b-form-input>
                </i-form-group-check>
              </b-form-row>
              <!-- 主辦機關,主管機關-->
              <b-form-row>
                <i-form-group-check class="col-sm-6" label-cols-md="4" content-cols-md="6" :label="'主辦機關：'" :item="$v.depCode">
                  <b-form-select v-model="$v.depCode.$model" :options="depCodeOpts" :state="validateState($v.depCode)"></b-form-select>
                </i-form-group-check>
                <i-form-group-check class="col-sm-6" label-cols-md="4" content-cols-md="6" :label="'主管機關：'" :item="$v.bosCode">
                  <b-form-select v-model="$v.bosCode.$model" :options="bosCodeOpts" :state="validateState($v.bosCode)"></b-form-select>
                </i-form-group-check>
              </b-form-row>
            </b-col>
          </b-row>
          <b-row>
            <b-col class="col-1">
              <!-- 歸屬計畫-->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="12" :label="'歸屬計畫'" />
              </b-form-row>
            </b-col>
            <b-col class="col-11">
              <!-- 計劃主辦機關-->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'計劃主辦機關：'" :item="$v.plnCode">
                  <b-form-input class="col-2" v-model="$v.plnCode.$model" :state="validateState($v.plnCode)"></b-form-input>
                  <b-col class="col-3">
                    <bid-unit10-name :orgId="$v.plnCode.$model" />
                    <b-button variant="outline-dark" @click="showOrgaModal">機關代碼查詢</b-button>
                  </b-col>
                </i-form-group-check>
              </b-form-row>
              <!-- 計畫編號-->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'計畫編號：'" :item="$v.plnNo">
                  <b-form-input class="col-2" v-model="$v.plnNo.$model" :state="validateState($v.plnNo)"></b-form-input>
                  <b-col class="col-3">
                    <b-button variant="outline-dark" @click="showPlanModal">計畫編號查詢</b-button>
                    <b-button variant="outline-dark" @click="showExtendModal">延伸查詢</b-button>
                  </b-col>
                </i-form-group-check>
              </b-form-row>
              <!-- 國發會列管個案計畫名稱 -->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="6" :label="'國發會列管個案計畫名稱：'" :item="$v.plnUnicode">
                  <b-form-select v-model="$v.plnUnicode.$model" :options="plnUnicodeOpts" :state="validateState($v.plnUnicode)"></b-form-select>
                </i-form-group-check>
              </b-form-row>
            </b-col>
          </b-row>
          <b-row>
            <b-col class="col-1">
              <!-- 標案主辦資料-->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="12" :label="'標案主辦資料'" :labelStar="true" />
              </b-form-row>
            </b-col>
            <b-col class="col-11">
              <!-- 姓名-->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'姓名：'" :item="$v.man">
                  <b-form-input v-model="$v.man.$model" :state="validateState($v.man)"></b-form-input>
                </i-form-group-check>
              </b-form-row>
              <!-- 連絡電話-->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'聯絡電話：'" :item="$v.tel">
                  <b-form-input v-model="$v.tel.$model" :state="validateState($v.tel)"></b-form-input>
                </i-form-group-check>
              </b-form-row>
              <!-- Email信箱-->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="2" :label="'Email信箱：'" :item="$v.eMail">
                  <b-form-input v-model="$v.eMail.$model" :state="validateState($v.eMail)"></b-form-input>
                </i-form-group-check>
              </b-form-row>
            </b-col>
          </b-row>
          <b-row>
            <b-col class="col-1">
            </b-col>
            <b-col class="col-11">
              <!-- 工程概要-->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :label="'工程概要：'" :labelStar="true" :item="$v.contain">
                  <b-form-textarea rows="3" max-rows="6" v-model="$v.contain.$model" :state="validateState($v.contain)"></b-form-textarea>
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <b-col offset="4">
                  <i-button type="save" @click="submitForm"></i-button>
                  <i-button size="md" type="arrow-counterclockwise" @click="backToPreviousPage"></i-button>
                  <i-button type="x-circle" @click="reset"></i-button>
                </b-col>
              </b-form-row>
            </b-col>
          </b-row>
        </b-collapse>
      </div>
    </div>
    <!-- 錯誤訊息 -->
    <b-modal id="valid-error-modal" size="lg" title="資料驗證有誤" :header-text-variant="'light'" :header-bg-variant="'danger'" hide-footer>
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModals.hide('valid-error-modal')">了解</b-button>
    </b-modal>
    <bid-unit10-list ref="bidUnit10List" />
    <bid-plnbas-list ref="bidPlnbasList" />
  </div>
</template>

<script lang="ts">
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import BidPlaceService from '@/components/bid/bidService/bid-place.service';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidUnit10Servie from '@/components/bid/bidService/bid-unit10.service';
import BidUtilService from '@/components/bid/bidService/bid-util-service';
import BidPlnUnitbService from '@/components/bid/bidService/bid-plnunitb.service';
import BidUnit10Name from '@/components/bid/bid-common-component/bid-unit10-name.vue';
import BidUnit10List from '@/components/bid/bid-common-component/bid-unit10-list.vue';
import BidPlnbasList from '@/components/bid/bid-common-component/bid-plnbas-list.vue';
import { IBidProject, BidProject } from '@/shared/model/bidModel/bid-project.model';
import { Ref, ref, toRef, reactive, watch, computed, inject, onActivated, onMounted } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import { notificationErrorHandlerWithErrorParam } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { useGetters } from '@u3u/vue-hooks';

export default {
  name: 'bidAaa002Edit',
  props: {},
  components: {
    BidUnit10Name,
    BidUnit10List,
    BidPlnbasList,
  },
  setup(props, context) {
    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const bidUnit10List = ref(null);
    const bidPlnbasList = ref(null);

    enum formStatusEnum {
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.CREATE);

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const notificationService: NotificationService = useNotification();
    const bidUnit10Servie = new BidUnit10Servie();
    const bidSysCodeTable = new BidSysCodeTable();
    const bidUtilService = new BidUtilService();
    const bidPlaceService = new BidPlaceService();
    const bidProjectService = new BidProjectService();
    const bidPlnUnitbService = new BidPlnUnitbService();

    const isAdd92Opts = reactive([]);
    const typeCodeOpts = reactive([]);
    const placeCodeOpts = reactive([]);
    const depCodeOpts = reactive([]);
    const bosCodeOpts = reactive([]);
    const plnUnicodeOpts = reactive([]);

    //去後端撈選項
    bidSysCodeTable.getBidSysCodeWithParam('BID_001').then(data => {
      isAdd92Opts.splice(0, isAdd92Opts.length);
      data.forEach(ele => {
        const selectOption = {
          value: '',
          text: '',
        };
        selectOption.value = ele.dataKey;
        selectOption.text = ele.value;
        isAdd92Opts.push(selectOption);
      });
    });
    bidSysCodeTable.getBidSysCodeWithParam('BID_003').then(data => {
      typeCodeOpts.splice(0, typeCodeOpts.length);
      data.forEach(ele => {
        const selectOption = {
          value: '',
          text: '',
        };
        selectOption.value = ele.dataKey;
        selectOption.text = ele.value;
        typeCodeOpts.push(selectOption);
      });
    });
    bidPlaceService.findAllBidPlaces().then(returnArray => {
      placeCodeOpts.splice(0, placeCodeOpts.length);
      if (returnArray && returnArray.length > 0) {
        returnArray.forEach(ele => {
          const item = { value: '', text: '' };
          item.value = ele.placeCode;
          item.text = ele.placeName;
          placeCodeOpts.push(item);
        });
      }
    });

    let wkutName = ref('');

    const pccOrgId = ref(useGetters(['account']).account.value).value.pccOrgId;

    let formDefault = new BidProject();
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      wkut: {},
      prjno: {},
      name: { required: required },
      isAdd92: {},
      typeCode: { required: required },
      bdgt1: { required: required },
      opDate: { required: required },
      pctDate: { required: required },
      placeCode: { required: required },
      place: {},
      depCode: {},
      bosCode: {},
      plnCode: {},
      plnNo: {},
      plnUnicode: {},
      man: { required: required },
      tel: { required: required },
      eMail: { required: required },
      contain: { required: required },
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      initDefaultValue();
    });

    onActivated(() => {
      initDefaultValue();
    });

    function initDefaultValue() {
      pccOrgId = ref(useGetters(['account']).account.value).value.pccOrgId
      createDefaultValue(new BidProject());
      form.prjno = getDateString(new Date());
      form.wkut = pccOrgId;
      wkutName.value = pccOrgId;
      bidUnit10Servie.findAdmAgentyCode(pccOrgId).then(admAgent => {
        wkutName.value = admAgent.orgName;
      });

      bidUtilService.findBidManageUnit(pccOrgId).then(returnArray => {
        depCodeOpts.splice(0, depCodeOpts.length);
        if (returnArray && returnArray.length > 0) {
          returnArray.forEach(ele => {
            const item = { value: '', text: '' };
            item.value = ele.code;
            item.text = ele.name;
            depCodeOpts.push(item);
          });
        }
      });

      bidUtilService.findBidManageUnit(pccOrgId).then(returnArray => {
        bosCodeOpts.splice(0, bosCodeOpts.length);
        if (returnArray && returnArray.length > 0) {
          returnArray.forEach(ele => {
            const item = { value: '', text: '' };
            item.value = ele.code;
            item.text = ele.name;
            bosCodeOpts.push(item);
          });
        }
      });
    }

    function createDefaultValue(data: BidProject) {
      if (data.opDate != null) {
        data.opDate = new Date(data.opDate);
      }
      if (data.pctDate != null) {
        data.pctDate = new Date(data.pctDate);
      }
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      reset();
    }

    function submitForm() {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModals.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
            if (isOK) {
              putForm();
            }
          });
        } else {
          $bvModals.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    function putForm() {
      if (formStatusRef.value === formStatusEnum.CREATE) {
        bidProjectService.saveBidProject(form)
        .then(data => {
          createDefaultValue(data);
          formStatusRef.value = formStatusEnum.MODIFY;
        })
        .catch(err => {
          if (err.response && err.response.data.errorKey === 'bidValidError') {
            errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
            $bvModals.show('valid-error-modal');
          } else {
            notificationErrorHandlerWithErrorParam(err, notificationService);
          }
        });
      } else if(formStatusRef.value === formStatusEnum.MODIFY) {
        bidProjectService.partialUpdateBidProject(form)
        .then(data => {
          createDefaultValue(data);
        })
        .catch(err => {
          if (err.response && err.response.data.errorKey === 'bidValidError') {
            errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
            $bvModals.show('valid-error-modal');
          } else {
            notificationErrorHandlerWithErrorParam(err, notificationService);
          }
        });
      }
    }

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAaa002Query');
    }

    function showOrgaModal() {
      const queryKey = reactive({
        code: form.plnCode;
        type: '0';
      });
      bidUnit10List.value.title = '機關代碼查詢';
      bidUnit10List.value.isShowDia(queryKey, true);
    }

    function showPlanModal() {
      const queryKey = reactive({
        wkut: form.wkut;
        plnCode: form.plnCode;
        bos2Code: form.bos2Code;
        bos2bCode: form.bos2bCode;
        bos2cCode: form.bos2cCode;
        bos2dCode: form.bos2dCode;
        isExtend: 'N';
      });
      bidPlnbasList.value.title = '計畫編號查詢';
      bidPlnbasList.value.isShowDia(queryKey, true);
    }

    function showExtendModal() {
      const queryKey = reactive({
        wkut: form.wkut;
        plnCode: form.plnCode;
        bos2Code: form.bos2Code;
        bos2bCode: form.bos2bCode;
        bos2cCode: form.bos2cCode;
        bos2dCode: form.bos2dCode;
        isExtend: 'Y';
      });
      bidPlnbasList.value.title = '延伸查詢';
      bidPlnbasList.value.isShowDia(queryKey, true);
    }

    function findPlnUnionCode() {
      const queryKey = reactive({
        wkut: form.wkut;
        plnCode: form.plnCode;
      });
      bidPlnUnitbService.findAllByCriteria(queryKey).then(returnArray => {
        plnUnicodeOpts.splice(0, plnUnicodeOpts.length);
        if (returnArray && returnArray.length > 0) {
          returnArray.forEach(ele => {
            const item = { value: '', text: '' };
            item.value = ele.plnoid;
            item.text = ele.plnname;
            plnUnicodeOpts.push(item);
          });
        }
      });
    }

    function pad(v) {
      return (v<10)?'0'+v:v
    }

    function getDateString(d) {
      var year = d.getFullYear();
      var month = pad(d.getMonth()+1);
      var day = pad(d.getDate());
      var hour = pad(d.getHours());
      var min = pad(d.getMinutes());
      var sec = pad(d.getSeconds());
      return year+month+day+hour+min+sec;
      //return year+"-"+month+"-"day+" "+hour+":"+min+":"+sec;
      //YYYYMMDDhhmmss
    }

    watch($v.value.wkut, newValue => {
      if (newValue !== null && newValue !== undefined) {
        if (newValue.$model && newValue.$model.length === 10) {
          findPlnUnionCode();
        }
      }
    });

    watch($v.value.plnCode, newValue => {
      if (newValue !== null && newValue !== undefined) {
        if (newValue.$model && newValue.$model.length === 10) {
          findPlnUnionCode();
        }
      }
    });

    return {
      $v,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      stepVisible,
      wkutName,
      isAdd92Opts,
      typeCodeOpts,
      placeCodeOpts,
      depCodeOpts,
      bosCodeOpts,
      plnUnicodeOpts,
      submitForm,
      showOrgaModal,
      bidUnit10List,
      showPlanModal,
      showExtendModal,
      bidPlnbasList,
    };
  },
};
</script>

<style></style>

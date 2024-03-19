<template>
  <div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">標案變更設計併案說明</h5>
      </div>
      <div class="card-body col-12">
        <div>
          一、本標案僅可併至同一機關執行之標案，如非同機關之標案，請先行以"執行機關變更"方式移轉本標案資料至母案執行機關，再交由該機關執行併案程序
        </div>
        <div>
          二、原始母標案如已完成驗收結案，系統選項中將不會出現，如需併入，請先將原始母標案(C6)完成驗收日期清除，恢復為在建工程即可。
        </div>
        <div>三、請先選取下方貴機關原已列管之原始標案名稱，並填入相關資料，點選[確定]，即可完成併案程序。</div>
      </div>
    </div>

    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">標案變更設計併案</h5>
      </div>
      <div class="card-body col-12">
        <!-- 因變更設計增減項目公告或彙送欲併案之標案名稱 -->
        <b-row>
          <i-form-group-check class="col-12" :label-cols="2" :content-cols="10" :label="'因變更設計增減項目公告或彙送欲併案之標案名稱：'">
            {{ projectName }}
            <span v-if="projectNameUpdate != projectName"> ,本案名稱已自行修改為：{{ projectNameUpdate }} </span>
            <!-- 國家XX研究園區興建工程(統包)第1次契約變更(XX工程施工事宜單XX範圍)  -->
          </i-form-group-check>
        </b-row>
        <!-- 原始母標案名稱 -->
        <b-row>
          <i-form-group-check :label="'原始母標案名稱：'">
            <b-form-select
              :options="dropDownProjectList"
              v-model="$v.mergeInPrjno.$model"
              :state="validateState($v.mergeInPrjno)"
            ></b-form-select>
            <div class="remark-text">如查無原始母標案，請參考上方說明二、說明三</div>
          </i-form-group-check>
        </b-row>
        <b-row> </b-row>
        <b-row>
          <i-form-group-check :label="'執行單位：'"> {{ wkutName }} </i-form-group-check>
          <i-form-group-check :label="'標案編號：'"> {{ form.prjno }} </i-form-group-check>
        </b-row>
        <!-- 變更日期 -->
        <b-row>
          <i-form-group-check :label="'變更日期：'" :labelStar="true" :item="$v.chgDate">
            <i-date-picker v-model="$v.chgDate.$model" lazy></i-date-picker>
            <div class="remark-text">本日期為變更設計案於決標公告之實際決標日期</div>
          </i-form-group-check>
        </b-row>
        <!-- 核准變更文號 -->
        <b-row>
          <i-form-group-check :label="'核准變更文號：'" :labelStar="true" :item="$v.okNo">
            <b-form-input v-model="$v.okNo.$model" :state="validateState($v.okNo)" lazy trim></b-form-input>
          </i-form-group-check>
        </b-row>
        <!-- 變更原因 -->
        <b-row>
          <i-form-group-check :label="'變更原因：'" :labelStar="true" :item="$v.mdyReson">
            <b-form-select v-model="$v.mdyReson.$model" :options="prjModifyOptions" :state="validateState($v.mdyReson)"></b-form-select>
          </i-form-group-check>
        </b-row>
        <!-- 原因說明 -->
        <b-row>
          <i-form-group-check :label="'原因說明：'" :labelStar="true" :item="$v.mdyRemark" class="col-12" :label-cols="2" :content-cols="6">
            <b-form-textarea
              v-model="$v.mdyRemark.$model"
              placeholder="原因說明..."
              rows="3"
              max-rows="6"
              :state="validateState($v.mdyRemark)"
              lazy
              trim
            ></b-form-textarea>
          </i-form-group-check>
        </b-row>
        <!-- 變更前母案進度 -->
        <b-row>
          <i-form-group-check
            :label="'變更前母案進度：'"
            :labelStar="true"
            :item="$v.chgRate"
            class="col-12"
            :label-cols="2"
            :content-cols="10"
          >
            <div>
              <b-form-input v-model="$v.chgRate.$model" :state="validateState($v.chgRate)" lazy trim></b-form-input>
            </div>
            <div class="remark-text d-inline">%(填列本次變更設計申請母案之實際進度)</div>
          </i-form-group-check>
        </b-row>
        <!-- 前次修正後累計增帳金額  前次修正後累計減帳金額-->
        <b-row>
          <i-form-group-check :label="'前次修正後累計增帳金額：'" :item="$v.oldAddAmt">
            <div class="d-inline">
              <b-form-input v-model="$v.oldAddAmt.$model" :item="$v.okNo" :state="validateState($v.oldAddAmt)"></b-form-input>
            </div>
            <div class="d-inline">千元</div>
            <div class="new-line-flex"></div>
            <div class="remark-text">為決標後以前歷次變更加帳金額累計</div>
          </i-form-group-check>
          <i-form-group-check :label="'前次修正後累計減帳金額：'" :item="$v.oldSubtractAmt">
            <div class="d-inline">
              <b-form-input v-model="$v.oldSubtractAmt.$model" :state="validateState($v.oldSubtractAmt)"></b-form-input>
            </div>
            <div class="d-inline">千元</div>
            <div class="new-line-flex"></div>
            <div class="remark-text">為決標後以前歷次變更減帳金額累計</div>
          </i-form-group-check>
        </b-row>
        <!-- 前次修正後總價 -->
        <b-row>
          <i-form-group-check
            :label="'前次修正後總價：'"
            :labelStar="true"
            class="col-12"
            :label-cols="2"
            :content-cols="10"
            :item="$v.oldAmt"
          >
            <div class="d-inline"><b-form-input v-model="$v.oldAmt.$model" :state="validateState($v.oldAmt)"></b-form-input></div>
            <div class="d-inline">(為母案決標金額 ＋ 以前歷次變更加帳金額 － 以前歷次變更減帳金額)</div>
            <div class="new-line-flex"></div>
            <div class="remark-text">如為第一次變更設計，本欄應等於母案決標金額。</div>
          </i-form-group-check>
        </b-row>
        <!-- 本次增帳金額  本次減帳金額-->
        <b-row>
          <i-form-group-check :label="'本次增帳金額：'" :item="$v.addAmt">
            <div class="d-inline"><b-form-input v-model="$v.addAmt.$model" :state="validateState($v.addAmt)"></b-form-input></div>
            <div class="d-inline">千元</div>
          </i-form-group-check>
          <i-form-group-check :label="'本次減帳金額：'" :item="$v.subtractAmt">
            <div class="d-inline"><b-form-input v-model="$v.subtractAmt.$model" :state="validateState($v.subtractAmt)"></b-form-input></div>
            <div class="d-inline">千元</div>
          </i-form-group-check>
        </b-row>
        <!-- 本次修正後總價 -->
        <!-- newAmt -->
        <b-row>
          <i-form-group-check :label="'本次修正後總價：'" class="col-12" :label-cols="2" :content-cols="10">
            <div class="d-inline"><b-form-input v-model="computedNewAmt" readonly></b-form-input></div>
            <div class="d-inline">本次修正後總價 = 前次修正後總價 + 本次增帳金額 - 本次減帳金額</div>
          </i-form-group-check>
        </b-row>
        <!-- 前次修正後預定完工日 -->
        <b-row>
          <i-form-group-check :label="'前次修正後預定完工日：'" :labelStar="true" :item="$v.oldDate">
            <i-date-picker v-model="$v.oldDate.$model"></i-date-picker>
          </i-form-group-check>
        </b-row>
        <!-- 本次修正後預定完工日 -->
        <b-row>
          <i-form-group-check :label="'本次修正後預定完工日：'" :labelStar="true" :item="$v.newDate">
            <i-date-picker v-model="$v.newDate.$model"></i-date-picker>
          </i-form-group-check>
        </b-row>
        <!-- 展延期程 -->
        <b-row>
          <i-form-group-check :label="'展延期程：'" class="col-12" :label-cols="2" :content-cols="10">
            <div class="d-inline position-relative">
              <div class="pt-2">
                依採購契約要項四十六條
                廠商履約有下列情形之一者，得檢具事證，以書面通知機關，機關得審酌期情形後，嚴懲履約期限，不計預期違約金
              </div>
              <div class="pt-2">
                屬不可抗力至共
                <b-form-input class="col-2 d-inline-block" v-model="$v.dlyd1.$model" :state="validateState($v.dlyd1)"></b-form-input>天(A)
              </div>
              <div class="pt-2">
                屬不可抗力不可歸責於廠商之契約變更或機關通知廠商停工共
                <b-form-input class="col-2 d-inline-block" v-model="$v.dlyd2.$model" :state="validateState($v.dlyd2)"></b-form-input>天(B)
              </div>
              <div class="pt-2">
                機關應提供予廠商之資料、器材、場所或應採行之審查獲同意等配合措施，未依契約規定提供或採行共
                <b-form-input class="col-2 d-inline-block" v-model="$v.dlyd3.$model" :state="validateState($v.dlyd3)"></b-form-input>天(C)
              </div>
              <div class="pt-2">
                可歸責於與機關有契約關係之其他廠商之延遲共
                <b-form-input class="col-2 d-inline-block" v-model="$v.dlyd4.$model" :state="validateState($v.dlyd4)"></b-form-input>天(D)
              </div>
              <div class="pt-2">
                其他可歸責於機關或不可歸責於廠商之事由共
                <b-form-input class="col-2 d-inline-block" v-model="$v.dlyd5.$model" :state="validateState($v.dlyd5)"></b-form-input>天(E)
              </div>
            </div>
          </i-form-group-check>
        </b-row>
        <!-- 准延天數 -->
        <b-row>
          <i-form-group-check :label="'准延天數：'">
            <b-form-input class="col-2 d-inline-block" v-model="computedNdays" readonly></b-form-input>天(A+B+C+D+E)(本欄自動計算)
          </i-form-group-check>
        </b-row>

        <!-- 功能按鈕 -->
        <b-row>
          <i-button type="save" @click="submitFormData"> </i-button>
          <i-button type="x-circle" @click="reset"> </i-button>
          <i-button type="arrow-left" @click="backToPreviousPage"> </i-button>
        </b-row>
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
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, computed } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { notificationErrorHandlerWithErrorParam } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { IBidPrjModify, BidPrjModify } from '@/shared/model/bidModel/bid-prj-modify.model';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import BidPrjModifyService from '@/components/bid/bidService/bid-prj-modify.service';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import NotificationService from '@/shared/notification/notification-service';
import BidUnit10Servie from '@/components/bid/bidService/bid-unit10.service';

export default {
  name: 'bidAdc001Edit',
  props: {
    bidProjectKey: {
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidPrjModifyService = new BidPrjModifyService();
    let bidSysCodeTable = new BidSysCodeTable();
    let formDefault = {
      mergeInPrjno: '',
    };
    Object.assign(formDefault, new BidPrjModify());

    const notificationService: NotificationService = useNotification();

    let wkutName = ref('');
    let projectName = ref('');
    let projectNameUpdate = ref('');

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    const bidProjectService = new BidProjectService();
    const bidUnit10Servie = new BidUnit10Servie();
    let errorMessages = ref([]);

    let dropDownProjectList = reactive([]);

    let prjModifyOptions = reactive([]);

    let form = reactive(Object.assign({}, formDefault));

    const rules = {
      chgDate: { required: required },
      okNo: { required: required },
      mdyReson: { required: required },
      mergeInPrjno: { required: required },
      mdyRemark: { required: required },
      chgRate: { required: required, numeric: numeric, maxValue: maxValue(100) },
      oldAddAmt: { numeric: numeric },
      oldSubtractAmt: { numeric: numeric },
      oldAmt: { required: required, numeric: numeric },
      addAmt: { numeric: numeric },
      subtractAmt: { numeric: numeric },
      newAmt: { numeric: numeric },
      oldDate: { required: required },
      newDate: { required: required },
      dlyd1: { numeric: numeric },
      dlyd2: { numeric: numeric },
      dlyd3: { numeric: numeric },
      dlyd4: { numeric: numeric },
      dlyd5: { numeric: numeric },
      nDays: {},
    };
    let { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    function proceedFrontEndDataToBackEndDTO(modifyData: BidPrjModify) {
      modifyData.mdyPress = 'Y';
      modifyData.nDays = computedNdays.value;
      modifyData.newAmt = computedNewAmt.value;
    }

    let computedNdays = computed(() => {
      return (
        parseInt(form.dlyd1 ? form.dlyd1 : 0) +
        parseInt(form.dlyd2 ? form.dlyd2 : 0) +
        parseInt(form.dlyd3 ? form.dlyd3 : 0) +
        parseInt(form.dlyd4 ? form.dlyd4 : 0) +
        parseInt(form.dlyd5 ? form.dlyd5 : 0)
      );
    });

    let computedNewAmt = computed(() => {
      return (
        parseInt(form.oldAmt ? form.oldAmt : 0) +
        parseInt(form.addAmt ? form.addAmt : 0) -
        parseInt(form.subtractAmt ? form.subtractAmt : 0)
      );
    });

    //產生下拉式選單
    function generateReasonDropDownMenu(projectName: string) {
      bidSysCodeTable.getBidSysCodeWithParam('BID_010').then(data => {
        prjModifyOptions.splice(0, prjModifyOptions.length);
        data.forEach(ele => {
          const selectOption = {
            value: '',
            text: '',
          };
          selectOption.value = ele.dataKey;
          selectOption.text = ele.value;

          const regex = new RegExp('^[1-9]$');
          const projectNameRule1 = new RegExp('\u5f8c\u7e8c\u64f4\u5145'); //後續擴充
          const projectNameRule2 = new RegExp('\u958b\u53e3.*\u7d04'); //開口%約

          if (regex.test(ele.dataKey) || ele.dataKey == '10') {
            prjModifyOptions.push(selectOption);
          } else if (ele.dataKey == '13' && (projectNameRule1.test(projectName) || projectNameRule2.test(projectName))) {
            prjModifyOptions.push(selectOption);
          }
        });
      });
    }

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectByWkutAndPrjno(wkut, prjno).then(result => {
        projectName.value = result.name0;
        projectNameUpdate.value = result.name;
      });
    }

    function getProjectDataAndCreateDropDown(wkut, prjno) {
      bidProjectService.findBidProjectByWkutAndPrjno(wkut, prjno).then(result => {
        if (result.name0) {
          generateReasonDropDownMenu(result.name0);
        } else {
          generateReasonDropDownMenu('');
        }
      });
    }

    //選擇project的 drop down menu, 並拿掉選定project
    function getProjectListForDropDownMenu(criteria, ignorePrjno) {
      let returnArray = [];
      bidProjectService.findProjectListByCriteria(criteria).then(data => {
        if (data && data.length > 0) {
          data.forEach(project => {
            const projectOption = {
              value: '',
              text: '',
            };
            projectOption.value = project.prjno;
            projectOption.text = project.name + '-決標金額：' + project.ctSum + '千元';
            if (project.prjno !== ignorePrjno) {
              returnArray.push(projectOption);
            }
          });
        }
        dropDownProjectList.splice(0, dropDownProjectList.length, ...returnArray);
      });
    }

    //回上一頁
    function backToPreviousPage() {
      const bidProjectKey = {
        wkut: form.wkut,
        prjno: form.prjno,
      };
      reset();

      navigateByNameAndParams('bidAdc001Query', {
        bidProjectKey: bidProjectKey,
      });

      dropDownProjectList;
    }

    //日期disable
    function notBeforeStartDate(date: Date) {
      if (form.startDate) return date < new Date(form.startDate);
    }

    function processDataToSendBackEnd() {}

    //接收傳下來的key並判斷是新增還是修改
    watch(
      bidProjectKeyProp,
      newValue => {
        reset();
        const criteria = {
          queryType: 'bidAdc001',
          wkut: newValue.wkut,
        };
        //取得project list
        getProjectListForDropDownMenu(criteria, newValue.prjno);

        //給初值
        Object.assign(formDefault, new BidPrjModify());
        Object.assign(form, formDefault);
        form.wkut = newValue.wkut;
        form.prjno = newValue.prjno;

        //拿到wkut的中文名字
        bidUnit10Servie.findAdmAgentyCode(newValue.wkut).then(admAgent => {
          wkutName.value = admAgent.orgName;
        });
        fetchBidProjectInfo(newValue.wkut, newValue.prjno);
        getProjectDataAndCreateDropDown(newValue.wkut, newValue.prjno);

        // delete form['id'];
      },
      { immediate: true }
    );

    function submitFormData() {
      checkValidity().then(bool => {
        if (bool) {
          proceedFrontEndDataToBackEndDTO(form);
          bidPrjModifyService
            .mergeProjectAndUpdateModify(form, form.mergeInPrjno)
            .then(data => {
              // console.log('data:', data);
              // console.log('projectName.value', projectName.value);
              navigateByNameAndParams('bidAdc001Info', {
                bidProjectKey: {
                  wkut: form.wkut,
                  prjno: form.mergeInPrjno,
                },
                bidPrjModifyData: data.data,
                oldProjectName: projectName.value,
              });
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
      });
    }

    return {
      form,
      $v,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      submitFormData,
      notBeforeStartDate,
      computedNdays,
      prjModifyOptions,
      computedNewAmt,
      dropDownProjectList,
      wkutName,
      projectName,
      projectNameUpdate,
    };
  },
};
</script>

<style>
.h-38p {
  height: 38px;
}
.new-line-flex {
  flex-basis: 100%;
  height: 0;
}
</style>

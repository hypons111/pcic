<template>
  <div>
    <section class="container">
      <div class="card col-12">
        <div class="card-body">
          <!-- 個人資料 -->
          <b-card>
            <b-form-row class="align-items-top">
              <b-form-group class="col-12" label-align="center" label-cols-md="12" label="個人資料" label-class="label-top"> </b-form-group>
            </b-form-row>
            <b-form-row>
              <!-- 姓名 -->
              <i-form-group-check content-cols="3" :label="$t('label.userName') + '：'" :label-star="true" :item="$v.userName">
                <b-form-input readonly v-model="$v.userName.$model" :state="validateState($v.userName)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 性別 -->
              <i-form-group-check :label="$t('label.sex') + '：'">
                <div class="h-100 d-flex align-items-center">
                  <b-form-radio-group disabled v-model="form.sex" :options="formOptions.sex" name="radio-options"></b-form-radio-group>
                </div>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 出生日期 -->
              <i-form-group-check :label="$t('label.birthday') + '：'" :label-star="true" :item="$v.birthday">
                <i-date-picker disabled v-model="$v.birthday.$model" :state="$v.birthday"></i-date-picker>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 通訊地址 -->
              <i-form-group-check
                :label="$t('label.contactAddr') + '：'"
                class="col-12"
                label-cols="2"
                content-cols="10"
                :labelStar="true"
                :hasInvalid="true"
              >
                <i-address-picker
                  ref="addressComp"
                  :isReset="true"
                  :addrCity="form.city"
                  :addrArea="form.dist"
                  :addrRoad="form.road"
                  @getCity="getHomeAddrCity"
                  @getArea="getHomeAddrTown"
                  @getRoad="getHomeAddrOther"
                  :key="componentKey"
                ></i-address-picker>
              </i-form-group-check>
              {{ $v.contactAddr }}
            </b-form-row>
            <b-form-row>
              <!-- email -->
              <i-form-group-check :label="$t('label.email') + '：'" :label-star="true" :item="$v.email">
                <b-form-input readonly v-model="$v.email.$model" :state="validateState($v.email)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 手機 -->
              <i-form-group-check
                :label="$t('label.mobileTel') + '：'"
                :label-star="true"
                class="col-12"
                label-cols="2"
                content-cols="8"
                :hasInvalid="true"
              >
                <i-phone-num ref="phoneNumComp" v-model="form.mobileTel" :readonly="true" :key="'mobileTel-' + componentKey"></i-phone-num>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 電話(公) -->
              <i-form-group-check
                :label="$t('label.contactTelOffice') + '：'"
                :label-star="true"
                class="col-12"
                label-cols="2"
                content-cols="8"
                :hasInvalid="true"
              >
                <i-tel-num
                  ref="workTelNumComp"
                  v-model="form.workTel"
                  :isRequired="true"
                  :readonly="true"
                  :key="'workTel-' + componentKey"
                ></i-tel-num>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 電話 家 -->
              <i-form-group-check
                :label="$t('label.contactTelHome') + '：'"
                class="col-12"
                label-cols="2"
                content-cols="8"
                :hasInvalid="true"
              >
                <i-tel-num
                  ref="homeTelNumComp"
                  v-model="form.homeTel"
                  :isRequired="false"
                  :readonly="true"
                  :key="'homeTel' + componentKey"
                ></i-tel-num>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 傳真 -->
              <i-form-group-check :label="$t('label.fax') + '：'" class="col-12" label-cols="2" content-cols="8">
                <i-tel-num ref="faxNumComp" v-model="form.fax" :readonly="true" :key="componentKey"></i-tel-num>
              </i-form-group-check>
            </b-form-row>
          </b-card>
          <!-- 學歷 -->
          <b-card>
            <!--sub-title="學歷(畢業年級)" <hr /> -->
            <b-form-row class="align-items-top">
              <b-form-group class="col-12" label-align="center" label-cols-md="12" label="學歷(畢業年級)" label-class="label-top">
              </b-form-group>
            </b-form-row>
            <b-form-row>
              <!-- 應考學歷 -->
              <i-form-group-check :label="$t('label.examineeDegree') + '：'" :label-star="true">
                <b-form-input v-model="$v.examineeDegree.$model" :state="validateState($v.examineeDegree)"></b-form-input>
              </i-form-group-check>
              <!-- 最高學歷 -->
              <i-form-group-check :label="$t('label.highestDegree') + '：'" :label-star="true">
                <b-form-input v-model="$v.highestDegree.$model" :state="validateState($v.highestDegree)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
          </b-card>
          <b-card>
            <!-- 考試證明 -->
            <b-form-row class="align-items-top">
              <b-form-group
                class="col-12"
                label-align="center"
                label-cols-md="12"
                label="第一階段考試及格或免試證明"
                label-class="label-top"
              >
              </b-form-group>
            </b-form-row>
            <b-form-row>
              <!-- 日期 -->
              <i-form-group-check :label="$t('label.date') + '：'">
                <i-date-picker v-model="$v.firstexamDate.$model"></i-date-picker>
              </i-form-group-check>
              <!-- 證號 -->
              <i-form-group-check :label="$t('label.certNo') + '：'" :label-star="true">
                <b-form-input v-model="form.firstexamCertNo"></b-form-input>
              </i-form-group-check>
            </b-form-row>
          </b-card>
          <b-card>
            <!-- 求才資訊-->
            <b-form-row class="align-items-top">
              <b-form-group class="col-12" label-align="center" label-cols-md="12" label="主動求才資訊" label-class="label-top">
              </b-form-group>
            </b-form-row>
            <b-form-row>
              <!-- 身份證 -->
              <i-form-group-check :label="$t('label.idno') + '：'" :label-star="true" :item="$v.idno">
                <b-form-input v-model="$v.idno.$model" :state="validateState($v.idno)"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 開放求才單位查詢 -->
              <i-form-group-check :label="$t('label.isEnablePublic') + '：'" :label-star="true">
                <b-form-radio-group
                  class="pt-2"
                  v-model="form.isEnable"
                  :options="formOptions.publicStatus"
                  name="radio-options2"
                ></b-form-radio-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 工作屬性 -->
              <i-form-group-check :label="$t('label.jobType') + '：'">
                <b-form-select v-model="form.jobType" :options="formOptions.jobTypeOptions"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 類別 -->
              <i-form-group-check :label="'類別：'">
                <b-form-select v-model="form.jobKind" :options="formOptions.jobKindOptions"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 希望待遇 -->
              <i-form-group-check :label="$t('label.salaryRange') + '：'">
                <b-form-select v-model="form.salaryRange" :options="formOptions.salaryOptions"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 希望工作地點 -->
              <i-form-group-check :label="$t('label.workplaces') + '：'">
                <b-form-checkbox-group
                  class="pt-2"
                  v-model="form.workplaces"
                  :options="formOptions.locationOptions"
                ></b-form-checkbox-group>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 工作需求 -->
              <i-form-group-check :label="$t('label.jobDemand') + '：'">
                <b-form-textarea v-model="form.jobDemand"></b-form-textarea>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 備註 -->
              <i-form-group-check :label="$t('label.remark') + '：'">
                <b-form-input v-model="form.remark"></b-form-input>
              </i-form-group-check>
            </b-form-row>
          </b-card>

          <!-- 最後的工作列 -->
          <div class="d-flex justify-content-center">
            <b-button-toolbar class="mt-5">
              <i-button class="ml-1" type="send-check" @click="submitForm"></i-button>
              <i-button class="ml-1" type="arrow-counterclockwise" @click="resetAll"></i-button>
              <i-button class="ml-1" type="arrow-left"></i-button>
            </b-button-toolbar>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { onMounted, reactive, Ref, ref } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';

import { useBvModal } from '@/shared/modal';
import { required, email, nationalId } from '@/shared/validators';
import { parseRocDate } from '@/shared/date/minguo-calendar-utils';
import { IGeoMember, GeoMember } from '@/shared/model/geoModel/geo-member.model';
import axios from 'axios';
import { useNotification } from '@/shared/notification';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';

export default {
  name: 'geoMn01EditInfo',
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      MODIFY = 'modify',
    }

    //功能 從登入的帳號取值
    const store = context.root.$store;

    //用flag判斷是否新增，如果該帳號已經在GEO MEMBER裡面有值就更新，不然就新增
    const isNewGeoData = ref(false);

    //功能 從ADM SYS CODE 取"希望待遇" 以及 "工作屬性" 值
    function getDataFromAdmSysCode() {
      const admUrl = '/adm-sys-codes/criteria-jpa';
      let admSysSalaryRangeCriteria = {
        moduleType: 'GEO',
        dataType: 'SALARY_RANGE',
      };
      axios.post(admUrl, admSysSalaryRangeCriteria).then(({ data }) => {
        if (data != null && Array.isArray(data.content)) {
          formOptions.salaryOptions.splice(0, formOptions.salaryOptions.length);
          data.content.forEach(item => {
            let selectOption = {
              value: '',
              text: '',
            };
            selectOption.value = item.value;
            selectOption.text = item.codeDescript;

            formOptions.salaryOptions.push(selectOption);
          });
        }
      });
      let admSysJobTypeCriteria = {
        moduleType: 'GEO',
        dataType: 'JOB_TYPE',
      };
      axios.post(admUrl, admSysJobTypeCriteria).then(({ data }) => {
        if (data != null && Array.isArray(data.content)) {
          formOptions.jobTypeOptions.splice(0, formOptions.jobTypeOptions.length);
          data.content.forEach(item => {
            let selectOption = {
              value: '',
              text: '',
            };
            selectOption.value = item.value;
            selectOption.text = item.codeDescript;

            formOptions.jobTypeOptions.push(selectOption);
          });
        }
      });

      //job kind
      let admSysJobKindCriteria = {
        moduleType: 'GEO',
        dataType: 'JOB_KIND',
      };
      axios.post(admUrl, admSysJobKindCriteria).then(({ data }) => {
        if (data != null && Array.isArray(data.content)) {
          formOptions.jobKindOptions.splice(0, formOptions.jobKindOptions.length);
          data.content.forEach(item => {
            let selectOption = {
              value: '',
              text: '',
            };
            selectOption.value = item.value;
            selectOption.text = item.codeDescript;

            formOptions.jobKindOptions.push(selectOption);
          });
        }
      });
    }

    //從帳號抓ID 去 adm-account 拿data，拿完後用adm account的account ID 對 GEO member裡的userID
    function fetchDataFromBackEndByUserId() {
      let userId = store.getters.account.id;
      let url = '/adm-account' + '/' + userId; //store.getters.account.id
      axios
        .get(url)
        .then(({ data }) => {
          //找尋所有form中的key,把data中有form中的key的屬性assign給form,如果沒有對應的屬性(undefined)就不複製
          Object.keys(form).forEach(key => {
            if (data[key] !== undefined) form[key] = data[key];
          });
          form.birthday = new Date(data.birthday);
          //處理adm_contack欄位名字修正(userName->chName)
          form.userName = data.chName;
          form.city = data.homeAddrCity;
          form.dist = data.homeAddrTown;
          form.road = data.homeAddrOther;

          //抓完adm-account data後,拿這個data去GEO MEMBER抓值
          let geoMrUrl = '/geo-members-by-user-id' + '/' + userId; //data.userId
          axios
            .get(geoMrUrl)
            .then(({ data }) => {
              form.examineeDegree = data.examineeDegree;
              form.highestDegree = data.highestDegree;
              form.firstexamCertNo = data.firstexamCertNo;
              form.geoMemberId = data.geoMemberId;
              form.isEnable = data.isEnable;
              form.jobType = data.jobType;
              form.jobKind = data.jobKind;
              form.jobDemand = data.jobDemand;
              form.salaryRange = data.salaryRange;
              form.remark = data.remark;
              form.birthday = new Date(data.birthday);
              form.firstexamDate = new Date(data.firstexamDate);
              //理論上formDefault和form一定會對到
              Object.keys(formDefault).forEach(key => (formDefault[key] = form[key]));

              let workplacesFromBackEnd = stringToWorkplace(data.workPlace);
              //清空workplaces
              form.workplaces.splice(0, form.workplaces.length);
              workplacesFromBackEnd.forEach(item => {
                form.workplaces.push(item);
              });
              reset();
            })
            .catch(error => {
              //用ADM account找不到在此資料表的資料，要把新增的flag開啟
              if (error.response && error.response.status === 404) {
                isNewGeoData.value = true;
              }
            });
        })
        .finally(() => {
          //刷新component的key
          componentKey.value += 1;
        });
    }

    function fetchGEOAfterUpdate(geoMemberId) {
      reset();
      let geoGetUrl = '/geo-members' + '/' + geoMemberId; //store.getters.account.id
      axios
        .get(geoGetUrl)
        .then(({ data }) => {
          Object.keys(form).forEach(key => {
            if (data[key] !== undefined) form[key] = data[key];
          });
          //date不能用字串...
          form.birthday = new Date(data.birthday);
          form.firstexamDate = new Date(data.firstexamDate);
          //理論上formDefault和form一定會對到
          Object.keys(formDefault).forEach(key => (formDefault[key] = form[key]));

          let workplacesFromBackEnd = stringToWorkplace(data.workPlace);
          //清空陣列
          form.workplaces.splice(0, form.workplaces.length);
          workplacesFromBackEnd.forEach(item => {
            form.workplaces.push(item);
          });
        })
        .catch(notificationErrorHandler(notificationService));
    }

    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);
    onMounted(() => {
      getDataFromAdmSysCode();
      fetchDataFromBackEndByUserId();
    });

    let componentKey = ref(0);

    const addressComp = ref(null);
    const phoneNumComp = ref(null);
    const workTelNumComp = ref(null);
    const homeTelNumComp = ref(null);
    const faxNumComp = ref(null);

    //使用地址元件
    function getHomeAddrCity(param) {
      form.city = param;
    }
    function getHomeAddrTown(param) {
      form.dist = param;
    }
    function getHomeAddrOther(param) {
      form.road = param;
    }

    const formOptions = reactive({
      sex: [
        { value: 'M', text: '男' },
        { value: 'F', text: '女' },
      ],
      publicStatus: [
        { value: true, text: '是' },
        { value: false, text: '否' },
      ],
      jobTypeOptions: [],
      jobKindOptions: [],
      salaryOptions: [],
      locationOptions: [
        { value: '1', text: '基隆市' },
        { value: '2', text: '台北市' },
        { value: '3', text: '新北市' },
        { value: '4', text: '桃園市' },
        { value: '5', text: '新竹市' },
        { value: '6', text: '新竹縣' },
        { value: '7', text: '苗栗縣' },
        { value: '8', text: '台中市' },
        { value: '9', text: '彰化縣' },
        { value: '10', text: '南投縣' },
        { value: '11', text: '雲林縣' },
        { value: '12', text: '嘉義市' },
        { value: '13', text: '嘉義縣' },
        { value: '14', text: '台南市' },
        { value: '15', text: '高雄市' },
        { value: '16', text: '屏東縣' },
        { value: '17', text: '宜蘭縣' },
        { value: '18', text: '花蓮縣' },
        { value: '19', text: '台東縣' },
        { value: '20', text: '澎湖縣' },
        { value: '21', text: '金門縣' },
        { value: '22', text: '連江縣' },
      ],
    });

    //針對form的表示，之後要轉成後端接收的data Type
    let formDefault = {
      geoMemberId: '',
      userName: '', //姓名
      sex: false, //性別
      birthday: null, //生日
      workTel: '', //工作電話,
      postNum: '', //地址
      city: '',
      dist: '',
      road: '',
      email: '', //信箱
      homeTel: '', //住家電話
      fax: '', //傳真
      mobileTel: '', //手機

      examineeDegree: '', //應考學歷
      highestDegree: '', //最高學歷

      firstexamDate: null, //第一階段考試及格或免試證明-日期
      firstexamCertNo: '', //第一階段考試證號

      idno: '', //身分證字號
      isEnable: false,
      jobType: '', //工作屬性
      jobKind: '', //工作類別
      salaryRange: '',
      workplaces: [], //希望工作地點
      jobDemand: '',
      remark: '', //備註

      isApprove: false,
      applyDate: new Date(), //申請日期
      userId: '', //帳號
      firstexamSubject: '', //第一階段考試科別
      ranking: '', //名次
      serialNo: '', //冊報序號
    };

    let form = reactive(Object.assign({}, formDefault));

    //如果共用套件有驗證的就不用再驗了ex:地址
    const rules = {
      userName: { notNull: required }, //姓名
      idno: { nationalId: nationalId }, //身分證字號
      birthday: {}, // notNull: required生日
      email: {}, //信箱
      examineeDegree: {}, //{notNull: required}, //應考學歷
      highestDegree: {}, //{notNull: required}, //最高學歷
      firstexamDate: {}, //{notNull: required}, //第一階段考試及格或免試證明-日期
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
            if (isOK) {
              let workplaceString = workplacesToString(form.workplaces);
              let newGeo: IGeoMember = transferFormToDTO(form);
              newGeo.contactAddr = form.city + form.dist + form.road;
              newGeo.workPlace = workplaceString;
              // console.log('newGeo', newGeo);
              submitFormToBackEnd(newGeo);
            }
          });
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    function submitFormToBackEnd(geoDTO: IGeoMember) {
      if (isNewGeoData.value) {
        //如果要新增
        axios
          .post('/geo-members', geoDTO)
          .then(({ data }) => {
            fetchDataFromBackEndByUserId();
          })
          .catch(notificationErrorHandler(notificationService));
      } else {
        // '更新'
        axios
          .put('/geo-members' + '/' + geoDTO.geoMemberId, geoDTO)
          .then(({ data }) => {
            fetchGEOAfterUpdate(geoDTO.geoMemberId);
          })
          .catch(notificationErrorHandler(notificationService));
      }
    }

    function transferFormToDTO(form): IGeoMember {
      let geoDTO = new GeoMember();
      Object.keys(geoDTO).forEach(key => (geoDTO[key] = form[key]));
      return geoDTO;
    }

    function workplacesToString(workplace: any): string {
      return workplace.join();
    }

    function stringToWorkplace(workplaceString: string): any {
      if (workplaceString.trim().length > 0) {
        return workplaceString.split(',');
      } else {
        return [];
      }
    }

    function resetAll() {
      reset();
    }

    return {
      componentKey,
      formStatusEnum,
      formStatusRef,
      formOptions,
      dataPromise,
      $v,
      form,

      addressComp,
      phoneNumComp,
      workTelNumComp,
      homeTelNumComp,
      faxNumComp,

      getHomeAddrCity,
      getHomeAddrTown,
      getHomeAddrOther,

      validateState,
      resetAll,
      submitForm,
    };
  },
};
</script>
<style>
.label-top {
  border: solid 0px;
  border-left-width: 40px;
  background-color: #1aa4b7;
  font-weight: bolder;
  color: white;
}
</style>

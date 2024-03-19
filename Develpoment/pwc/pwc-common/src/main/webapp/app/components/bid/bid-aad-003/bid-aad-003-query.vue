<template>
  <div>
    <section class="container">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      <div>
        <tr style="color:red">
          廢棄物清理法
          <td style="color:black">第11條
           <td> <a href="https://law.moj.gov.tw/LawClass/LawSingle.aspx?pcode=O0050001&flno=11"target="_blank">一般廢棄物，除應該下列規定清除外，其餘在指定清除地區以內者，由執行機關清除之...</a></td>
          </td>
        </tr>

        <tr style="color:red">
          空氣汙染防制法
          <td style="color:black">第23條</td>
          <a href="https://law.moj.gov.tw/LawClass/LawSingle.aspx?pcode=O0020001&flno=23"target="_blank">公司場所應有效收集各種空氣汙染物，並維持其空氣汙染防制設施...</a>
        </tr>
      </div>
      <div class="card">
        <div class="card-header">
          <h5 class="m-0" v-if="stepVisible.queryTable">環保裁罰列表</h5>
          <h5 class="m-0" v-if="stepVisible.queryRow">新增環保裁罰狀況資料</h5>
        </div>
        <div class="card-body">
          <div v-if="stepVisible.queryTable">
              <i-button size="md" type="folder-plus" variant="outline-secondary" @click="toAdd" ></i-button>
          </div>
          <p></p>

          <div v-if="stepVisible.queryTable">
            <i-table
              class="table-sm"
              :itemsUndefinedBehavior="'loading'"
              :items="table.data"
              :fields="table.fields"
              :totalItems="table.totalItems"
              :is-server-side-paging="true"
              :hide-no="true"
              @changePagination="handlePaginationChanged($event)"
            >
              <template #cell(action)="row">
                <i-button size="sm" type="binoculars" @click="toSee(row.item)"></i-button>
                <i-button size="sm" type="pencil-square" @click="toEdit(row.item)"></i-button>
                <b-button size="sm" style="background-color: #1aa4b7" @click="toTable(row.item)">環保裁罰狀況表</b-button>
                <!-- <i-button size="md" type="trash" variant="outline-secondary" @click="toDelete(row.item)"></i-button> -->
              </template>
            </i-table>
          </div>
          <div class="card-body">
            <b-collapse v-model="stepVisible.queryRow">
              <!-- 裁處日期 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="2"
                  :label="$t('label.sdate')"
                  :item="$v.sdate"
                  :labelStar="true"
                  label-align-md="left"
                >
                  <b-form-row>
                    <i-date-picker v-model="$v.sdate.$model" :state="$v.sdate" placeholder="yyy/MM/dd"></i-date-picker>
                  </b-form-row>
                </i-form-group-check>
              </b-form-row>
              <!-- 裁處單位 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="3"
                  :label="$t('label.ctUnit')"
                  :item="$v.ctUnit"
                  :labelStar="true"
                  label-align-md="left"
                >
                  <b-form-select v-model="$v.ctUnit.$model" :state="validateState($v.ctUnit)" :options="queryOptions.bid021" >---請選擇</b-form-select>
                </i-form-group-check>
              </b-form-row>
              <!-- 裁處文號 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="8"
                  :label="$t('label.prmNo')"
                  :item="$v.prmNo"
                  :labelStar="true"
                  label-align-md="left"
                >
                  <b-form-input v-model="$v.prmNo.$model" trim :state="validateState($v.prmNo)"></b-form-input>
                </i-form-group-check>
              </b-form-row>
              <!-- 受處分單位 -->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="3" :label="$t('label.ccut')" label-align-md="left">
                  <b-form-select v-model="$v.ccut.$model" :state="validateState($v.ccut)" :options="ccutnitoptionss"></b-form-select>
                </i-form-group-check>
              </b-form-row>
              <!-- 裁處類型 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="3"
                  :label="$t('label.stype')"
                  :item="$v.stype"
                  label-align-md="left"
                >
                  <b-form-select v-model="$v.stype.$model" :state="validateState($v.stype)" :options="queryOptions.bid022"></b-form-select>
                </i-form-group-check>
              </b-form-row>
              <!-- 違反日期 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="2"
                  :label="$t('label.violateDate')"
                  :item="$v.violateDate"
                  :labelStar="true"
                  label-align-md="left"
                >
                  <b-form-row>
                    <i-date-picker v-model="$v.violateDate.$model" placeholder="yyy/MM/dd"  :disabled-date="notAfterSdateEnd"></i-date-picker>
                  </b-form-row>
                </i-form-group-check>
              </b-form-row>
              <!-- 發生前累計工期 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="10"
                  :label="$t('label.wdayed')"
                  :item="$v.wdayed"
                  :labelStar="true"
                  label-align-md="left"
                >
                  <b-form-input class="col-1" v-model="$v.wdayed.$model" trim :state="validateState($v.wdayed)"></b-form-input>
                  <p>天</p><div class="col-10" > <p style="color: blue">(填寫發生災害前一日之公共工程施工日誌所填報之累計工期。)</p>
                  </div>
                  <div style="color:red">(截至110年3月底止累計工期共 455 天。)</div>
                </i-form-group-check>
              </b-form-row>
              <!-- 違反事實 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="10"
                  :label="$t('label.violateFact')"
                  :item="$v.violateFact"
                  :labelStar="true"
                  label-align-md="left"
                >
                  <b-form-textarea id="textarea-rows" v-model="$v.violateFact.$model" :state="validateState($v.violateFact)" trim placeholder="" rows="4"></b-form-textarea>
                </i-form-group-check>
              </b-form-row>
              <!-- 裁處主旨 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="10"
                  :label="$t('label.prmTitle')"
                  :item="$v.prmT9itle"
                  :labelStar="true"
                  label-align-md="left"
                >
                  <b-form-textarea id="textarea-rows" v-model="$v.prmTitle.$model" trim :state="validateState($v.prmTitle)" placeholder="" rows="4"></b-form-textarea>
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <b-col offset="4" v-if="stepVisible.addButton">
                  <i-button type="check-circle" @click="toSubmit"></i-button>
                  <i-button type="x-circle" @click="reset"></i-button>
                  <i-button size="md" type="arrow-counterclockwise" @click="toQueryView"></i-button>
                </b-col>
              </b-form-row>
            </b-collapse>
          </div>
        </div>
      </div>
    </section>
    <!-- 錯誤訊息 -->
    <b-modal id="valid-error-modal" size="lg" title="資料驗證有誤" :header-text-variant="'light'" :header-bg-variant="'danger'" hide-footer>
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModals.hide('valid-error-modal')">了解</b-button>
    </b-modal>
    <i-pdf-viewer ref="pdfViewer" />
  </div>
</template>

<script lang="ts">
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import NotificationService from '@/shared/notification/notification-service';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { onMounted, reactive, ref, toRef, watch, Ref} from '@vue/composition-api';
import { required } from '@/shared/validators';
import { handleBack } from '@/router/router';
import { navigateByNameAndParams } from '@/router/router';
import { Pagination } from '@/shared/model/pagination.model';
import { useGetters } from '@u3u/vue-hooks';
import { formatToString } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'bidAad003Query',
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  components: {
    bidProjectInfo,
  },

  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();
    const admSysCodeService = new AdmSysCodeService();
    const pdfViewer = ref(null);
  
    enum FormStatusEnum {
        READONLY = '檢視',
        MODIFY = '編輯',
        CREATE = '新增',
    }

     onMounted(() => {
      getAllSysCodes();
    });

    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

     //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);
  
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        bidCommonService.bidProjectTypeCodeConverter(result.typeCode).then(data => {
          result.typeCode = data;
          Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
        });
      });
    }

   //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;
    //  const fromName = useStore().value.getters.routeData.fromName;

     //取得使用者資訊ip
     const store = context.root.$store;
     const userIp = store.getters.account.ip
    

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      sdate:null, //裁處日期
      ctUnit: '', //裁處單位
      prmNo: '', //裁處文號
      ccut: '', //受處分單位
      stype: '', //裁處類型
      violateDate: null, //違反日期
      wdayed: '', //發生前累計工期
      violateFact: '', //違反事實
      prmTitle: '', //裁處主旨
      createDate:null,
      createUser:'',
      uIp:'',
    };

    // 表單物件驗證規則
    const rules = {
      sdate: { notnull: required }, //裁處日期
      ctUnit: { notnull: required }, //裁處單位
      prmNo: { notnull: required }, //裁處文號
      ccut: {}, //受處分單位
      stype: {}, //裁處類型
      violateDate: { notnull: required }, //違反日期
      wdayed: { notnull: required }, //發生前累計工期
      violateFact: { notnull: required }, //違反事實
      prmTitle: { notnull: required }, //裁處主旨
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    const notificationService: NotificationService = useNotification();

    // 是否需要重新查詢
    const isReload = ref(false);

    // 執行結果
    // const queryStatus = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    //下拉試選單響應試
    const ccutnitoptions = ref([]);
    const ccutnitoptionss = ref([]);

    // 下拉選單選項
    const queryOptions = reactive({
      bid021: [],
      bid022: [],
    });

    
    const table = reactive({
      fields: [
        {
          //裁處日期
          key: 'sdate',
          label: i18n.t('label.sdate'),
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: 'width:8%',
          formatter: (value: string) => formatToString(value, '/', '-'),

        },
        {
          //裁處主旨
          key: 'prmTitle',
          label: i18n.t('label.prmTitle'),
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width:40%',
        },
        {
          //違反日期
          key: 'violateDate',
          label: i18n.t('label.violateDate'),
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: 'width:8%',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          //違反事實
          key: 'violateFact',
          label: i18n.t('label.violateFact'),
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width:20%',
        },
        {
          key: 'action',
          label: '功能',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: 'width:20%',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    //區塊是否顯示
    const stepVisible = reactive({
      queryTable: true,
      queryRow: false,
      addButton: true,
      seeRow: false,
    });

    //進入新增畫面   
    const toAdd = () => {
      stepVisible.queryTable = false;
      stepVisible.queryRow = true;
      stepVisible.addButton = true;
    };

    //新增頁面的提交
    const toSubmit = () => {
      // checkValidity().then((isValid: boolean) => {
      const value = new Date();
      const offset = value.getTimezoneOffset();
      form.createDate = new Date();
      form.createDate = new Date(form.createDate.getTime() - offset * 60 * 1000);
      form.violateDate = new Date(form.violateDate.getTime() - offset * 60 * 1000);
      form.sdate = new Date(form.sdate.getTime() - offset * 60 * 1000);
      form.createUser = userIdentity;
      form.uIp = userIp;
       let criteria = {
        wkut:bidProjectKeyProp.value.wkut,
        prjno:bidProjectKeyProp.value.prjno
      };
      Object.assign(form, criteria);
       checkValidity().then((isValid: boolean) => {
        if (isValid) {
            dataPromise.value = axios
              .post('/bid-prj-envis', form)
              .then(({ data }) => {
                createDefaultValue(data);
                isReload.value = true;
                $bvModal.msgBoxOk('環保裁罰狀況新增成功!')
                navigateByNameAndParams('bidAad003Detail', { form, formStatus: FormStatusEnum.READONLY, isNotKeepAlive: false });
              })
              .finally(() => (dataPromise.value = null))
               .catch(err => {
                if (err.response && err.response.data.errorKey === 'bidValidError') {
                  errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
                  $bvModals.show('valid-error-modal');
                }
              });
      } else {
        $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
      }
      });
      };
      
  //受處分單位下拉試選單
      const ctUnitOptions = (wkut,prjno) => {
        const projectPK = {
          wkut: wkut,
          prjno: prjno,
        };
        dataPromise.value = axios
          .post('/bid-prj-find-ctunit', projectPK)
          .then(({ data }) => {
            ccutnitoptionss.value.slice(0, data.values.length)
            data.forEach( ele => {
              const responOptions ={ value:'', text:''};
            if(ele.wkut != null){
              responOptions.value = ele.wkut;
              responOptions.text = ele.name;
              ccutnitoptionss.value.push(responOptions);
            }
            else if(ele.type === '3'){
              responOptions.value = ele.ccut;
              responOptions.text = '承攬廠商--' + ele.name;
              ccutnitoptionss.value.push(responOptions);
            }
            else if(ele.type === '2'){
              responOptions.value = ele.scut;
              responOptions.text = '監造單位--' + ele.name;
              ccutnitoptionss.value.push(responOptions);
            }
            else if(ele.pcmut != null){
              responOptions.value = ele.pcmut;
              responOptions.text = ele.name;
              ccutnitoptionss.value.push(responOptions);
            }
            else if(ele.subccut != null){
              responOptions.value = ele.subccut;
              responOptions.text = ele.name;
              ccutnitoptionss.value.push(responOptions);
            }
            });
          })
        .finally(() => (dataPromise.value = null))
          .catch(notificationErrorHandler(notificationService));
      };


    //限制能選的日期
    function notAfterSdateEnd(date: Date) {
      return date > new Date();
    }
    

    //查詢頁面的檢視
    const toSee = (forms) => {
      console.log(FormStatusEnum.READONLY);
      navigateByNameAndParams('bidAad003Edit', { form:forms, formStatus: FormStatusEnum.READONLY,isNotKeepAlive: false });
    };

    //查詢頁面的編輯
    const toEdit = form => {
      navigateByNameAndParams('bidAad003Edit', { form:form, formStatus: FormStatusEnum.MODIFY, isNotKeepAlive: false });
    };
    
    //查詢頁面的刪除
    // const toDelete = form => {
      // if (value) {
      //   let deleteKey = {
      //     wkut: value.wkut,
      //     prjno: value.prjno,
      //     sdate: value.sdate,
      //   };
      //   axios.post('/bid-prj-envis-delete', deleteKey)
      //   .then(() =>{
      //     toQuery(deleteKey.wkut, deleteKey.prjno);
      //   })
      //   }
      //  navigateByNameAndParams('bidAad003Edit', { form, formStatus: FormStatusEnum.READONLY, KeepAlive: true });
    // };


    //查詢table
    const toQuery = (wkut, prjno) => {
      table.data = undefined;
      stepVisible.queryTable = true;
      stepVisible.queryRow = false;
      const projectPK = {
        wkut: wkut,
        prjno: prjno,
      };
      dataPromise.value = axios
        // .post('/bid-prj-add-find-by-pk', projectPK) //前端分頁(呼叫後端)\
        .post('/bid-prj-add/criteria-jpa', projectPK) //後端端分頁(呼叫後端)\
        .then(({ data }) => {
          // table.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
          // table.totalItems = data.length; //前端分頁
          table.data = data.content.slice(0, data.content.length); //後端分頁
          table.totalItems = data.totalElements; //後端分頁
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

     const handlePaginationChanged = (pagination: Pagination) => {
      let criteria = {
        wkut:bidProjectKeyProp.value.wkut,
        prjno:bidProjectKeyProp.value.prjno
      }
      Object.assign(criteria, pagination);
      axios.post('/bid-prj-add/criteria-jpa', criteria).then(({ data }) => {
          table.data = data.content.slice(0, data.content.length); //後端分頁
          table.totalItems = data.totalElements; //後端分頁
        })
    };

    //回去查詢頁面
    const toQueryView = () =>{
      //  handleBack({ isReload: isReload.value });
      // isReload.value = false;
       stepVisible.queryTable = true;
      stepVisible.queryRow = false;
      stepVisible.addButton = false;
    }

    //去SYSCODE找下拉是選單
    const getAllSysCodes = () => {
      admSysCodeService.setFormOptions(queryOptions, 'BID');
    };

    const createDefaultValue = (data) => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      reset();
    };


    //製報表
    const toTable = (bidProject) => {
        let criteria = {
          wkut:bidProject.wkut,
          prjno:bidProject.prjno,
          sdate:bidProject.sdate
      }
      axios
        .post('/bid-prj-envis/bidAae007', criteria, { responseType: 'blob' })
        .then(res => {
          let blob = new Blob([res.data], { type: 'application/pdf' });
          let url = window.URL.createObjectURL(blob);
          console.log(context);
          pdfViewer.value.pdfSrc = url;
          pdfViewer.value.isShowDia(url, true);
        })
        .catch(() => notificationService.danger('製表失敗'));
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          toQuery(newValue.wkut, newValue.prjno);
          ctUnitOptions(newValue.wkut, newValue.prjno);
        }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      formDefault,
      $v,
      reset,
      FormStatusEnum,
      validateState,
      dataPromise,
      table,
      toAdd,
      stepVisible,
      queryOptions,
      toQuery,
      toSee,
      toEdit,
      // toDelete,
      toSubmit,
      handlePaginationChanged,
      userIdentity,
      toQueryView,
      ctUnitOptions,
      ccutnitoptionss,
      getAllSysCodes,
      notAfterSdateEnd,
      $bvModals,
      errorMessages,
      toTable,
      pdfViewer
    };
  },
};
</script>
<style></style>

<template>
  <div>
    <section class="container">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">環保裁罰列表</h5>
        </div>
        <div class="card-body">
          <div>
            <b-form-row>
              <i-button size="md" type="add-prj-envi" variant="outline-secondary" @click="toAdd" v-if="stepVisible.queryTable"></i-button>
            </b-form-row>
            <!-- <b-form-row>
              <b-button size="md" type="add-prj-envi" variant="outline-secondary">查詢</b-button>
            </b-form-row> -->
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
              <!-- <template #cell(checkString)="row">
                <p style="white-space: pre-wrap; margin-bottom: 0" v-text="row.item.checkString"></p>
              </template>
              <template #cell(roveString)="row">
                <p style="white-space: pre-wrap; margin-bottom: 0" v-text="row.item.roveString"></p>
              </template> -->
              <template #cell(action)="row">
                <i-button size="md" type="binoculars" @click="toSee(row.item)"></i-button>
                <i-button size="md" type="pencil-square" @click="toEdit(row.item)"></i-button>
                <i-button size="md" type="trash" variant="outline-secondary" @click="toDelete(row.item)"></i-button>
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
                  label-align-md="left"
                >
                  <b-form-row>
                    <!-- <i-date-picker v-model="$v.sdate.$model" placeholder="yyy/MM/dd"></i-date-picker> -->
                    <span v-if="formStatusRef === FormStatusEnum.READONLY || FormStatusEnum.MODIFY">{{
                      $v.sdate.$model | dateToString
                    }}</span>
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
                  label-align-md="left"
                >
                  <b-form-select
                    v-if="formStatusRef === FormStatusEnum.MODIFY"
                    v-model="$v.ctUnit.$model"
                    :options="queryOptions.bid021"
                  ></b-form-select>
                  <span v-if="formStatusRef === FormStatusEnum.READONLY">{{
                    formOptionsFormatter(queryOptions.bid021, $v.ctUnit.$model)
                  }}</span>
                </i-form-group-check>
              </b-form-row>
              <!-- 裁處文號 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="5"
                  :label="$t('label.prmNo')"
                  label-align-md="left"
                >
                  <b-form-input v-if="formStatusRef === FormStatusEnum.MODIFY" v-model="$v.prmNo.$model" trim></b-form-input>
                  <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.prmNo.$model }}</span>
                </i-form-group-check>
              </b-form-row>
              <!-- 受處分單位 -->
              <b-form-row>
                <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="3" :label="$t('label.ccut')" label-align-md="left">
                  <b-form-select
                    v-if="formStatusRef === FormStatusEnum.MODIFY"
                    v-model="$v.ccut.$model"
                    :options="ccutnitoptionss"
                  ></b-form-select>
                  <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ getName }}</span>
                </i-form-group-check>
              </b-form-row>
              <!-- 裁處類型 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="3"
                  :label="$t('label.stype')"
                  label-align-md="left"
                >
                  <b-form-select
                    v-if="formStatusRef === FormStatusEnum.MODIFY"
                    v-model="$v.stype.$model"
                    :options="queryOptions.bid022"
                  ></b-form-select>
                  <span v-if="formStatusRef === FormStatusEnum.READONLY">
                    {{ formOptionsFormatter(queryOptions.bid022, $v.stype.$model) }}</span
                  >
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
                  label-align-md="left"
                >
                  <b-form-row>
                    <i-date-picker
                      v-if="formStatusRef === FormStatusEnum.MODIFY"
                      v-model="$v.violateDate.$model"
                      trim
                      placeholder="yyy/MM/dd"
                    ></i-date-picker>
                    <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.violateDate.$model | dateToString }}</span>
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
                  label-align-md="left"
                >
                  <b-form-input class="col-1" v-if="formStatusRef === FormStatusEnum.MODIFY" v-model="$v.wdayed.$model" trim></b-form-input>
                  <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.wdayed.$model }}天</span>

                  <div v-if="formStatusRef === FormStatusEnum.MODIFY" class="col-10">
                    <p style="color: blue">(填寫發生災害前一日之公共工程施工日誌所填報之累計工期。)</p>
                  </div>
                  <div v-if="formStatusRef === FormStatusEnum.MODIFY" style="color: red">(截至110年3月底止累計工期共 455 天。)</div>
                </i-form-group-check>
              </b-form-row>
              <!-- 違反事實 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="10"
                  :label="$t('label.violateFact')"
                  label-align-md="left"
                >
                  <b-form-textarea
                    v-if="formStatusRef === FormStatusEnum.MODIFY"
                    v-model="$v.violateFact.$model"
                    trim
                    id="textarea-rows"
                    placeholder=""
                    rows="4"
                  ></b-form-textarea>
                  <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.violateFact.$model }}</span>
                </i-form-group-check>
              </b-form-row>
              <!-- 裁處主旨 -->
              <b-form-row>
                <i-form-group-check
                  class="col-sm-12"
                  label-cols-md="2"
                  content-cols-md="10"
                  :label="$t('label.prmTitle')"
                  label-align-md="left"
                >
                  <b-form-textarea
                    v-if="formStatusRef === FormStatusEnum.MODIFY"
                    v-model="$v.prmTitle.$model"
                    trim
                    id="textarea-rows"
                    placeholder=""
                    rows="4"
                  ></b-form-textarea>
                  <span v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.prmTitle.$model }}</span>
                </i-form-group-check>
              </b-form-row>
              <b-form-row>
                <b-col offset="4" v-if="formStatusRef === FormStatusEnum.MODIFY">
                  <i-button type="check-circle" @click="toSubmit"></i-button>
                  <i-button type="x-circle" @click="reset"></i-button>
                  <i-button size="md" type="arrow-counterclockwise" @click="toQueryView"></i-button>
                </b-col>
                <b-col offset="5" v-if="formStatusRef === FormStatusEnum.READONLY">
                  <i-button type="arrow-bar-left" @click="toQueryView"></i-button>
                </b-col>
              </b-form-row>
            </b-collapse>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import i18n from '@/shared/i18n';
import { useValidation, validateState } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { reactive, ref, toRef, watch, Ref, onMounted } from '@vue/composition-api';
import { required } from '@/shared/validators';
import { handleBack } from '@/router/router';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import { useGetters } from '@u3u/vue-hooks';
import { RoleEnum, FormStatusEnum, ReviewStatusEnum, ReviewCaseTypeEnum } from '@/shared/enum';

export default {
  name: 'bidAad003Edit',
  props: {
    form: {
      type: Object,
      required: false,
    },
    // 原為 ref(FormStatusEnum) 傳過來會變物件 { "value": "xxx" }，目的為這樣更改value值就不會被Vue警告(避免變異父元件資料)
    formStatus: {
      required: false,
      type: String,
    },
  },
  components: {
    bidProjectInfo,
  },

  setup(props, context) {
    const bringValueProp = toRef(props, 'form');
    const formStatusRef = toRef(props, 'formStatus'); // toRef會保持對來源property的響應式連接，但改變父元件資料會被Vue警告，所以這邊改用ref複製一份新的資料
    const bidProjectService = new BidProjectService();
    const bidCommonService = new BidCommonService();
    const admSysCodeService = new AdmSysCodeService();

    onMounted(() => {
      getAllSysCodes();
      ctUnitName();
    });
    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
    }

    const projectInfo = reactive({
      wkut: '',
      prjno: '',
    });

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

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      sdate: null, //裁處日期
      ctUnit: '', //裁處單位
      prmNo: '', //裁處文號
      ccut: '', //受處分單位
      stype: '', //裁處類型
      violateDate: '', //違反日期
      wdayed: '', //發生前累計工期
      violateFact: '', //違反事實
      prmTitle: '', //裁處主旨
      createDate: null,
      createUser: '',
      updateDate: null,
      updateUser: '',
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

    // 下拉選單選項
    const queryOptions = reactive({
      bid021: [],
      bid022: [],
    });

    //下拉試選單響應試
    const ccutnitoptions = ref([]);
    const ccutnitoptionss = ref([]);

    const getName = ref();

    // 執行結果
    // const queryStatus = ref(false);
    const dataPromise = ref(null);

    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
      fields: [
        {
          //裁處日期
          key: 'sdate',
          label: i18n.t('label.sdate'),
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: 'width:8%',
        },
        {
          //裁處主旨
          key: 'prmTitle',
          label: i18n.t('label.prmTitle'),
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width:50%',
        },
        {
          //違反日期
          key: 'violateDate',
          label: i18n.t('label.violateDate'),
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width:8%',
        },
        {
          //違反事實
          key: 'violateFact',
          label: i18n.t('label.violateFact'),
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width:10%',
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

    const stepVisible = reactive({
      queryTable: false,
      queryRow: true,
      addButton: false,
      seeButton: true,
      seeRow: false,
    });

    // const toDelete = value => {
    //   if (value) {

    //     let deleteKey = {
    //       wkut: value.wkut,
    //       prjno: value.prjno,
    //       sdate: value.sdate,
    //       prmNo: value.prmNo,
    //     };

    //     axios.post('/bid-prj-envis-delete', deleteKey);
    //   }
    // };
    const toSubmit = () => {
      const value = new Date();
      const offset = value.getTimezoneOffset();
      form.updateDate = new Date();
      form.updateDate = new Date(form.updateDate.getTime() - offset * 60 * 1000);
      form.updateUser = userIdentity;

      let criteria = {
        wkut: bringValueProp.value.wkut,
        prjno: bringValueProp.value.prjno,
      };

      Object.assign(form, criteria);
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          dataPromise.value = axios
            .post('/bid-prj-envis-up', form)
            .then(({ data }) => {
              createDefaultValue(data);
              isReload.value = true;
              $bvModal.msgBoxOk('環保裁罰狀況修改成功!');
              navigateByNameAndParams('bidAad003Detail', { form, formStatus: FormStatusEnum.MODIFY, isNotKeepAlive: false });
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //受處分單位下拉式選單
    const ctUnitOptions = (wkut, prjno) => {
      const projectPK = {
        wkut: wkut,
        prjno: prjno,
      };
      dataPromise.value = axios
        .post('/bid-prj-find-ctunit', projectPK)
        .then(({ data }) => {
          ccutnitoptionss.value.slice(0, data.values.length);
          data.forEach(ele => {
            const responOptions = { value: '', text: '' };
            if (ele.wkut != null) {
              responOptions.value = ele.wkut;
              responOptions.text = ele.name;
              ccutnitoptionss.value.push(responOptions);
            } else if (ele.type === '3') {
              responOptions.value = ele.ccut;
              responOptions.text = '承攬廠商--' + ele.name;
              ccutnitoptionss.value.push(responOptions);
            } else if (ele.type === '2') {
              responOptions.value = ele.scut;
              responOptions.text = '監造單位--' + ele.name;
              ccutnitoptionss.value.push(responOptions);
            } else if (ele.pcmut != null) {
              responOptions.value = ele.pcmut;
              responOptions.text = ele.name;
              ccutnitoptionss.value.push(responOptions);
            } else if (ele.subccut != null) {
              responOptions.value = ele.subccut;
              responOptions.text = ele.name;
              ccutnitoptionss.value.push(responOptions);
            }
          });
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    //下拉式選單
    const getAllSysCodes = () => {
      admSysCodeService.setFormOptions(queryOptions, 'BID');
    };

    const ctUnitName = () => {
      const criteria = bringValueProp.value.ccut;
      axios.get('/bid-unit-10-name/' + criteria).then(({ data }) => {
        if (data.name != null) {
          getName.value = data.name;
        } else {
          getName.value = '(未填寫)';
        }
      });
    };

    const toQueryView = () => {
      // handleBack({ isReload: isReload.value });
      // isReload.value = false;
      let bidProjectKey = {
        wkut: bringValueProp.value.wkut,
        prjno: bringValueProp.value.prjno,
      };
      navigateByNameAndParams('bidAad003Query', { bidProjectKey, isNotKeepAlive: false });
    };

    const createDefaultValue = data => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
      ctUnitName();
      reset();
    };

    watch(
      bringValueProp,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          bringValueProp.value.violateDate = new Date(bringValueProp.value.violateDate);
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);

          ctUnitOptions(newValue.wkut, newValue.prjno);
          createDefaultValue(newValue);
        }
      },
      { immediate: true }
    );

    return {
      projectInfo,
      formDefault,
      $v,
      reset,
      validateState,
      dataPromise,
      table,
      stepVisible,
      queryOptions,
      // toDelete,
      toQueryView,
      createDefaultValue,
      FormStatusEnum,
      formStatusRef,
      toSubmit,
      getAllSysCodes,
      ccutnitoptionss,
      formOptionsFormatter,
      ctUnitName,
      getName,
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
<style></style>

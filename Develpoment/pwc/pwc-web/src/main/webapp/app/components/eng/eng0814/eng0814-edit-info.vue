<template>
  <div>
    <!-- <section class="container"> -->
    <section class="container" v-show="queryStatus">
      <div class="card">
        <div class="card-header">
          <h5 class="text-center font-weight-bold ">
            <!-- <font-awesome-icon icon="project-diagram"></font-awesome-icon> -->
            公司登記證資訊
          </h5>
        </div>
        <div class="card-body">
          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 負責人姓名 -->
            <i-form-group-check content-cols="4" :label="$t('label.masterName') + '：'" :item="$v.masterName">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.masterName.$model }}</div>
              <b-form-input v-else v-model="$v.masterName.$model" :state="validateState($v.masterName)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.masterName }}</div>
              <b-form-input v-else v-model="data.masterName" :state="validateState($v.masterName)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 統一編號 -->
            <i-form-group-check content-cols="4" :label="$t('label.compIdno') + '：'" :item="$v.compIdno">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.compIdno.$model }}</div>
              <b-form-input v-else v-model="$v.compIdno.$model" :state="validateState($v.compIdno)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.compIdno }}</div>
              <b-form-input v-else v-model="data.compIdno" :state="validateState($v.compIdno)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 公司名稱 -->
            <i-form-group-check content-cols="4" :label="$t('label.comName') + '：'" :item="$v.compName">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.compName.$model }}</div>
              <b-form-input v-else v-model="$v.compName.$model" :state="validateState($v.compName)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.compName }}</div>
              <b-form-input v-else v-model="data.compName" :state="validateState($v.compName)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 最近申請項目 -->
            <i-form-group-check content-cols="4" :label="$t('label.lateApplyItems') + '：'" :item="$v.applyItem">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.applyItem.$model }}</div>
              <b-form-input v-else v-model="$v.applyItem.$model" :state="validateState($v.applyItem)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.applyItem }}</div>
              <b-form-input v-else v-model="data.applyItem" :state="validateState($v.applyItem)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 公司所在地 -->
            <i-form-group-check content-cols="4" :label="$t('label.companyAddr') + '：'" :item="$v.companyAddr">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.companyAddr.$model }}</div>
              <b-form-input v-else v-model="$v.companyAddr.$model" :state="validateState($v.companyAddr)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.companyAddr }}</div>
              <b-form-input v-else v-model="data.companyAddr" :state="validateState($v.companyAddr)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="10"
              :label="$t('label.companyAddr') + '：'"
              :item="[$v.compAddrTown, $v.compAddrOther, $v.compAddrCity]"
            >
              <i-address-picker
                :isText="formStatusRef === FormStatusEnum.READONLY"
                :zip.sync="$v.compAddrZip.$model"
                :city.sync="$v.compAddrCity.$model"
                :town.sync="$v.compAddrTown.$model"
                :other.sync="$v.compAddrOther.$model"
                :cityState="validateState($v.compAddrCity)"
                :townState="validateState($v.compAddrTown)"
                :otherState="validateState($v.compAddrOther)"
                :cols="[1, 2, 2, 5]"
              >
              </i-address-picker>
            </i-form-group-check> -->
            <!-- 連絡電話 -->
            <i-form-group-check content-cols="4" :label="$t('label.compTel1') + '：'" :item="$v.compTel1">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.compTel1.$model }}</div>
              <b-form-input v-else v-model="$v.compTel1.$model" :state="validateState($v.compTel1)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.compTel1 }}</div>
              <b-form-input v-else v-model="data.compTel1" :state="validateState($v.compTel1)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 登記證號碼 -->
            <i-form-group-check content-cols="4" :label="$t('label.cstLicenseDocNo') + '：'" :item="$v.cstLicenseDocNo">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.cstLicenseDocNo.$model }}</div>
              <b-form-input v-else v-model="$v.cstLicenseDocNo.$model" :state="validateState($v.cstLicenseDocNo)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.cstLicenseDocNo }}</div>
              <b-form-input v-else v-model="data.cstLicenseDocNo" :state="validateState($v.cstLicenseDocNo)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 最後一次發證(照)日期 -->
            <i-form-group-check content-cols="4" :label="$t('label.lastpublicDate') + '：'" :item="$v.publicDate">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.publicDate.$model }}</div>
              <b-form-input v-else v-model="$v.publicDate.$model" :state="validateState($v.publicDate)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.publicDate }}</div>
              <b-form-input v-else v-model="data.publicDate" :state="validateState($v.publicDate)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 營業範圍 -->
            <i-form-group-check content-cols="4" :label="$t('label.saleAreaList') + '：'" :item="$v.saleAreaList">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.saleAreaList.$model }}</div>
              <b-form-input v-else v-model="$v.saleAreaList.$model" :state="validateState($v.saleAreaList)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.saleAreaList }}</div>
              <b-form-input v-else v-model="data.saleAreaList" :state="validateState($v.saleAreaList)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>
        <!-- <div> -->
        <i-table
          small
          ref="iTableComponent"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :is-server-side-paging="true"
          :fields="table.fields_backend"
          :totalItems="table.totalItems"
          @changePagination="handlePaginationChanged" 
        >  
          <template v-slot:thead-top="data">
            <b-tr>
              <b-th class="text-center" colspan="5">公司技師名冊</b-th>
            </b-tr>
          </template>

          <template #cell(execSubject) = "{ item }">
            {{ item.subject }}
            {{ item.engrCertificateDocNo }} 
          </template>

        </i-table>
        <!-- </div> -->
        
        <div class="card-header">
          <h5 class="text-center font-weight-bold ">
            <!-- <font-awesome-icon icon="project-diagram"></font-awesome-icon> -->
            懲戒資訊
          </h5>
        </div>
        <div class="card-body">
          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 決議書主文 -->
            <i-form-group-check content-cols="4" :label="$t('label.resoDesc') + '：'" :item="$v.resoDesc">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.resoDesc.$model }}</div>
              <b-form-input v-else v-model="$v.resoDesc.$model" :state="validateState($v.resoDesc)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>

        <i-table
          small
          ref="iTableComponent"
          :itemsUndefinedBehavior="'loading'"
          :items="table2.data"
          :is-server-side-paging="true"
          :fields="table2.fields_backend"
          :totalItems="table2.totalItems"
          @changePagination="handlePaginationChanged" 
        >  

          <template #cell(execdate) = "{ item }">
            {{ item.execdatestart }}
            ~
            {{ item.execdateend }} 
          </template>
          <!-- <template #cell(execSubject) = "{ item }">
            {{ item.subject }}
            {{ item.engrCertificateDocNo }} 
          </template> -->

        </i-table>

        <div class="text-center mb-2">
          <!-- 返回 -->
          <i-button type="arrow-left" @click="toQueryView" />
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import i18n from '@/shared/i18n';
import { useValidation, validateState } from '@/shared/form';
import { reactive, ref, toRefs, onMounted, defineComponent, unref, onActivated, toRef } from '@vue/composition-api';
import { ApplyItemEnum, FormStatusEnum, MemberTypeEnum, StatusEnum } from '@/shared/enum';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { IEngEngrSubjectpoint, EngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import { handleBack } from '@/router/router';

interface propsType {
  engEngrSubjectpoint: IEngEngrSubjectpoint;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0814EditInfo',
  props: {
    engEngrSubjectpoint: {
      required: false,
      type: Object,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    criteria: {
      required: false,
      type: Object,
    },
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  components: {
    ISwitchCase,
  },
  setup(props) {
    const formStatusRef = ref(props.formStatus);

    const queryStatus = ref(false);

    const isReload = ref(false);
    const { engEngrSubjectpoint, formStatus, criteria } = toRefs(props);

    const formOptions = reactive({

    });

    let formDefault = {
      masterName: '',
      compIdno: '',
      compName: '',
      applyItem: '',
      companyAddr: '',
      compTel1: '',
      cstLicenseDocNo: '',
      publicDate: null,
      saleAreaList: '',
      resoDesc: '',
    };

    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      masterName: {},
      compIdno: {},
      compName: {},
      applyItem: {},
      companyAddr: {},
      compTel1: {},
      cstLicenseDocNo: {},
      publicDate: {},
      saleAreaList: {},
      resoDesc: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    function toQueryView() {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    }

    const table = reactive({
      fields_backend: [
        {
          // 姓名
          key: 'chName',
          label: i18n.t('label.name'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 執照字號
          key: 'engrLicenseDocNo',
          label: i18n.t('label.engrLicenseDocNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 執業科別
          key: 'execSubject',
          label: i18n.t('label.execSubject'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 具7年經驗
          key: 'isMore7year',
          label: "具7年經驗",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const table2 = reactive({
         fields_backend: [
          {
            // 違反法條
            key: 'breaklawlist',
            label: i18n.t('label.breaklawlist'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 懲戒類型
            key: 'disciplineType',
            label: i18n.t('label.disciplineType'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
          },
          {
            // 懲戒方式
            key: 'countType',
            label: i18n.t('label.disciplineMethod'),
            sortable: false,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // thStyle: { width: '10%' },
          },
          {
            // 執行日期
            key: 'execdate',
            label: i18n.t('label.execDate2'),
            sortable: true,
            thClass: 'text-center',
            tdClass: 'text-center align-middle',
            // formatter: (value: string) => formatToString(value, '/', '-'),
          },
         ],
        data: undefined,
        totalItems: undefined,
      })

    onMounted(() => {
      handleQuery();
    });

    const handleQueryBtn = () => {
      handleQuery();
    };
    
    const toEditView = (entity: IEngEngrSubjectpoint, formStatus: FormStatusEnum, index?: number) => {
        const criteria = Object.assign({}, form);
        criteria['index'] = index;
        criteria['totalItems'] = table.totalItems;

        navigateByNameAndParams('Eng0814EditInfo', {
          engEngrSubjectpoint: entity,
          formStatus: formStatus,
          criteria: criteria,
        });
      };
      
    const data = {
      masterName: '莫仁雄',
      compIdno: '04723653',
      compName: '亞新有限公司',
      applyItem: '煥發',
      companyAddr: '新北市',
      compTel1: '26961555#1202',
      cstLicenseDocNo: '工程技顧登字第000064號',
      publicDate: '2020/11/03',
      saleAreaList: '土木工程、水利工程',
    }

    const handleQuery = () => {
      const now = new Date();
      const offset = now.getTimezoneOffset();

      //驗證
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          table.data = [
            {
              no: '1',
              chName:'廖承輝',
              engrLicenseDocNo:'季執字第008035號',
              subject:'土木工程科',
              engrCertificateDocNo: '技證字第006908號',
              isMore7year:'否',
            },
          ];
          table.totalItems=1;

          table2.data = undefined;
          queryStatus.value = true;
          table2.data = [
            {
              no: '1',
              breaklawlist: '第19條第一項第3款',
              disciplineType: '申誡',
              countType: '次數:1次',
              execdatestart: '2018/11/28',
              execdateend: '2018/11/28',
            },
          ];
          table2.totalItems=1;
          // axios
          //   // .post('/cms-comps/criteria', form)           //前端分頁
          //   .post('/eng-engr-certificates/criteria-jpa', form) //後端分頁
          //   .then(({ data }) => {
          //     // table.data = data.slice(0, data.length);   //前端分頁
          //     // table.totalItems = data.length;            //前端分頁
          //     table.data = data.content.slice(0, data.content.length); //後端分頁
          //     table.totalItems = data.totalElements; //後端分頁
          //   })
          //   .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    return {
      FormStatusEnum,
      handleQueryBtn,
      reset,
      toEditView,
      formStatusRef,
      queryStatus,
      table,
      $v,
      table2,
      validateState,
      formOptions,
      handlePaginationChanged,
      toQueryView,
      data,
    };
  },
});
</script>
<style></style>

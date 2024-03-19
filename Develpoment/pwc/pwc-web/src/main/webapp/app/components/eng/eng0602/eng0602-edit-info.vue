<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            
          </h5>
        </div>
        <div class="card-body">
          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 姓名 -->
            <i-form-group-check content-cols="4" :label="$t('label.name') + '：'" :item="$v.name">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.name.$model }}</div>
              <b-form-input v-else v-model="$v.name.$model" :state="validateState($v.name)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.name }}</div>
              <b-form-input v-else v-model="data.name" :state="validateState($v.name)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 執照號碼 -->
            <i-form-group-check content-cols="4" :label="$t('label.engrLicenseDocNo') + '：'" :item="$v.engrLicenseDocNo">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.engrLicenseDocNo.$model }}</div>
              <b-form-input v-else v-model="$v.engrLicenseDocNo.$model" :state="validateState($v.engrLicenseDocNo)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.engrLicenseDocNo }}</div>
              <b-form-input v-else v-model="data.engrLicenseDocNo" :state="validateState($v.engrLicenseDocNo)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT || formStatusRef === FormStatusEnum.CREATE">
            <!-- 機構名稱 -->
            <i-form-group-check content-cols="4" :label="$t('label.compChName') + '：'" :item="$v.compChName">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.compChName.$model }}</div>
              <b-form-input v-else v-model="$v.compChName.$model" :state="validateState($v.compChName)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.compChName }}</div>
              <b-form-input v-else v-model="data.compChName" :state="validateState($v.compChName)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 技師科別 -->
            <i-form-group-check content-cols="4" :label="$t('label.engrSubject') + '：'" :item="$v.engrSubject">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.engrSubject.$model }}</div>
              <b-form-input v-else v-model="$v.engrSubject.$model" :state="validateState($v.engrSubject)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.engrSubject }}</div>
              <b-form-input v-else v-model="data.engrSubject" :state="validateState($v.engrSubject)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 近3年簽證案件數 -->
            <i-form-group-check content-cols="4" :label="$t('label.engr3year') + '：'" :item="$v.engr3year">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.engr3year.$model }}</div>
              <b-form-input v-else v-model="$v.engr3year.$model" :state="validateState($v.engr3year)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.engr3year }}</div>
              <b-form-input v-else v-model="data.engr3year" :state="validateState($v.engr3year)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 近3年擔任主任技師年資 -->
            <i-form-group-check content-cols="4" :label="$t('label.engr3iSen') + '：'" :item="$v.engr3iSen">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.engr3iSen.$model }}</div>
              <b-form-input v-else v-model="$v.engr3iSen.$model" :state="validateState($v.engr3iSen)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.engr3iSen }}</div>
              <b-form-input v-else v-model="data.engr3iSen" :state="validateState($v.engr3iSen)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>
      </div>
    </section>
    <div>
  <b-table-simple hover small caption-top responsive>
    <b-thead head-variant="dark">
      <b-tr>
        <b-th class="text-center" colspan="1"></b-th>
        <b-th class="text-center" colspan="4">類別</b-th>
      </b-tr>
      <b-tr>
        <b-th>近3年參與第1多工程類別</b-th>
        <b-th>2億以上</b-th>
        <b-th>5000萬至2億</b-th>
        <b-th>1000萬至5000萬</b-th>
        <b-th>100萬至1000萬</b-th>
      </b-tr>
    </b-thead>
    <b-tbody>
      <b-tr>
        <b-th rowspan="1">規劃工程件數</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="1">設計工程件數</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="1">監造工程件數</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="1">專案管理件數</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
      </b-tr>
    </b-tbody>
  </b-table-simple>

  <b-table-simple hover small caption-top responsive>
    <b-thead head-variant="dark">
      <b-tr>
        <b-th class="text-center" colspan="1"></b-th>
        <b-th class="text-center" colspan="4">類別</b-th>
      </b-tr>
      <b-tr>
        <b-th>近3年參與第2多工程類別</b-th>
        <b-th>2億以上</b-th>
        <b-th>5000萬至2億</b-th>
        <b-th>1000萬至5000萬</b-th>
        <b-th>100萬至1000萬</b-th>
      </b-tr>
    </b-thead>
    <b-tbody>
      <b-tr>
        <b-th rowspan="1">規劃件數</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="1">設計工程件數</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="1">監造工程件數</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="1">專案管理工程件數</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
      </b-tr>
    </b-tbody>
  </b-table-simple>

  <b-table-simple hover small caption-top responsive>
    <b-thead head-variant="dark">
      <b-tr>
        <b-th class="text-center" colspan="1"></b-th>
        <b-th class="text-center" colspan="4">類別</b-th>
      </b-tr>
      <b-tr>
        <b-th>近3年參與第3多工程類別</b-th>
        <b-th>2億以上</b-th>
        <b-th>5000萬至2億</b-th>
        <b-th>1000萬至5000萬</b-th>
        <b-th>100萬至1000萬</b-th>
      </b-tr>
    </b-thead>
    <b-tbody>
      <b-tr>
        <b-th rowspan="1">規劃工程件數</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="1">設計工程件數</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="1">監造工程件數</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="1">專案管理工程件數</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
      </b-tr>
    </b-tbody>
  </b-table-simple>
</div>
    <div class="text-center mb-2">
      <!-- <a class="href-style" href="javascript: void(0)" @click="toDownView()">明細</a> -->
      <a class="href-style" href="javascript: void(0)" v-b-modal.query-0602-modal>明細</a>
    </div>
    <div class="card-body col-11">
      <b-form-row>
        <!-- 明細 -->
        <i-form-group-check class="col-12" label-cols="2" content-cols="9" :item="$v.reviewDesc">
          <b-form-textarea
            :plaintext="formStatusRef === FormStatusEnum.READONLY"
            class="text-center"
            variant="secondary" 
            v-model="data.reviewDesc2"
            rows="4"
            maxlength="2000"
            trim
           lazy
          ></b-form-textarea>
        </i-form-group-check>
      </b-form-row>
    </div>
    <b-tr>
      <b-th class="text-left">註:1.金額級距以施工工程發包預算金額劃分</b-th>
    </b-tr>
    <b-tr>
      <b-th class="text-left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.有關個人金質獎紀錄，可參考本會<a class="href-style" href="javascript: void(0)">歷屆得獎名單</a>查詢</b-th>
    </b-tr>
    <b-tr>
      <b-th class="text-left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.有關亞太工程師名單及相關資訊可詳網站:<a class="href-style" href="javascript: void(0)">http://www.apecengineer.org.tw</a></b-th>
    </b-tr>
    <b-col offset="8">
      <i-button type="arrow-left" @click="toEditView" />
    </b-col>

      <b-modal
      id="query-0602-modal"
      size="xl"
      title=""
      header-bg-variant="secondary"
      header-text-variant="light"
      @hide="handleHideQueryRecordModel"
    >
      <section class="container mt-2">
      <div class="card">
        <div class="card-body col-11">
            <b-form-row>
              <!-- 明細 -->
              <i-form-group-check class="col-12" label-cols="2" content-cols="9" :item="$v.reviewDesc">
                <b-form-textarea
                  :plaintext="formStatusRef === FormStatusEnum.READONLY"
                  class="text-center"
                  v-model="data.reviewDesc"
                  rows="4"
                  maxlength="2000"
                  trim
                  lazy
                ></b-form-textarea>
              </i-form-group-check>
            </b-form-row>
        </div>
      </div>
    </section>

      <template #modal-footer="{ cancel }">
        <b-button @click="cancel()" variant="info">關閉</b-button>
      </template>
    </b-modal>
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
  name: 'Eng0602EditInfo',
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
      name: '',
      engrLicenseDocNo: '',
      compChName: '',
      engrSubject: '',
      engr3year: '',
      engr3iSen: '',
    };

    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      name: {},
      engrLicenseDocNo: {},
      compChName: {},
      engrSubject: {},
      engr3year: {},
      engr3iSen: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const toDownView = (pwcDownload: PwcDownload) => {
      navigateByNameAndParams('Eng0601Design', { pwcDownload: pwcDownload, isNotKeepAlive: true });
    };

    function handleHideQueryRecordModel() {
      // table.queryString = '';
      // table.data = undefined;
      // queryStatus.value = false;
    }

    const table = reactive({
      fields_backend: [
        {
          // 2億以上
          key: 'money2001',
          label: "2億以上",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 5000萬至2億
          key: 'money2000',
          label: "5000萬至2億",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 1000萬至5000萬
          key: 'money5000',
          label: "1000萬至5000萬",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 100萬至1000萬
          key: 'money100',
          label: "100萬至1000萬",
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 備註
          key: 'action',
          label: i18n.t('label.remark'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    onMounted(() => {
      handleQuery();
    });

    const handleQueryBtn = () => {
      handleQuery();
    };

    function toQueryView() {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    }

    const toEditView = (entity: IEngEngrSubjectpoint, formStatus: FormStatusEnum, index?: number) => {
        const criteria = Object.assign({}, form);
        criteria['index'] = index;
        criteria['totalItems'] = table.totalItems;

        navigateByNameAndParams('Eng0602Query', {
          engEngrSubjectpoint: entity,
          formStatus: formStatus,
          criteria: criteria,
        });
      };

    const data ={
      name: '方明達',
      engrLicenseDocNo: '',
      compChName: '',
      engrSubject: '',
      engr3year: '0',
      engr3iSen: '無',
      reviewDesc: '查無任何資料',
      reviewDesc2: '無違反技師法懲戒紀錄',
    }

    const handleQuery = () => {

      //驗證
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          table.data = [
            {
              money2001: '0',
              money2000: '0',
              money5000: '0',
              money100: '0',
            },
          ];
          table.totalItems = 1;
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
      toQueryView,
      toDownView,
      formStatusRef,
      queryStatus,
      table,
      $v,
      validateState,
      formOptions,
      handlePaginationChanged,
      handleHideQueryRecordModel,
      data,
    };
  },
});
</script>
<style></style>

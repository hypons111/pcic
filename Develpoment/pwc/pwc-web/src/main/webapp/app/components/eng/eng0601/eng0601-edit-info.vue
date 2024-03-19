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
            <!-- 廠商名稱 -->
            <i-form-group-check content-cols="4" :label="$t('label.companyName') + '：'" :item="$v.companyName">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.companyName.$model }}</div>
              <b-form-input v-else v-model="$v.companyName.$model" :state="validateState($v.companyName)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.companyName }}</div>
              <b-form-input v-else v-model="data.companyName" :state="validateState($v.companyName)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT || formStatusRef === FormStatusEnum.CREATE">
            <!-- 營業範圍 -->
            <i-form-group-check content-cols="4" :label="$t('label.saleAreaList') + '：'" :item="$v.saleAreaList">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.saleAreaList.$model }}</div>
              <b-form-input v-else v-model="$v.saleAreaList.$model" :state="validateState($v.saleAreaList)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.saleAreaList }}</div>
              <b-form-input v-else v-model="data.saleAreaList" :state="validateState($v.saleAreaList)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 目前執業技師人數 -->
            <i-form-group-check content-cols="4" :label="$t('label.nowEngr') + '：'" :item="$v.nowEngr">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.nowEngr.$model }}</div>
              <b-form-input v-else v-model="$v.nowEngr.$model" :state="validateState($v.nowEngr)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.nowEngr }}</div>
              <b-form-input v-else v-model="data.nowEngr" :state="validateState($v.nowEngr)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 目前建築師人數 -->
            <i-form-group-check content-cols="4" :label="$t('label.archCompCount') + '：'" :item="$v.archCompCount">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.archCompCount.$model }}</div>
              <b-form-input v-else v-model="$v.archCompCount.$model" :state="validateState($v.archCompCount)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.archCompCount }}</div>
              <b-form-input v-else v-model="data.archCompCount" :state="validateState($v.archCompCount)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row v-if="formStatusRef === FormStatusEnum.READONLY || formStatusRef === FormStatusEnum.EDIT">
            <!-- 近5年平均得標勞務案件數 -->
            <i-form-group-check content-cols="4" :label="$t('label.comp5year') + '：'" :item="$v.comp5year">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.comp5year.$model }}</div>
              <b-form-input v-else v-model="$v.comp5year.$model" :state="validateState($v.comp5year)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.comp5year }}</div>
              <b-form-input v-else v-model="data.comp5year" :state="validateState($v.comp5year)" trim lazy> </b-form-input>
            </i-form-group-check>
            <!-- 近5年得標總金額 -->
            <i-form-group-check content-cols="4" :label="$t('label.comp5yearamount') + '：'" :item="$v.comp5yearamount">
              <!-- <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ $v.comp5yearamount.$model }}</div>
              <b-form-input v-else v-model="$v.comp5yearamount.$model" :state="validateState($v.comp5yearamount)" trim lazy> </b-form-input> -->
              <div v-if="formStatusRef === FormStatusEnum.READONLY">{{ data.comp5yearamount }}</div>
              <b-form-input v-else v-model="data.comp5yearamount" :state="validateState($v.comp5yearamount)" trim lazy> </b-form-input>
            </i-form-group-check>
          </b-form-row>
        </div>
      </div>
    </section>
    <div>
  <b-table-simple hover small caption-top responsive>
    <b-thead head-variant="dark">
      <b-tr>
        <b-th class="text-center" colspan="2"></b-th>
        <b-th class="text-center" colspan="5">施工工程發包預算金額</b-th>
      </b-tr>
      <b-tr>
        <b-th></b-th>
        <b-th></b-th>
        <b-th>2億以上</b-th>
        <b-th>5000萬至2億</b-th>
        <b-th>1000萬至5000萬</b-th>
        <b-th>100萬至1000萬</b-th>
        <b-th>備註</b-th>
      </b-tr>
    </b-thead>
    <b-tbody>
      <b-tr>
        <b-th rowspan="1">近5年設計工程件數</b-th>
        <b-th class="text-center"></b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td><a class="href-style" href="javascript: void(0)" v-b-modal.query-Record-modal>明細</a></b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="1">近5年監造工程件數</b-th>
        <b-th class="text-center"></b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td><a class="href-style" href="javascript: void(0)" v-b-modal.query-Record2-modal>明細</a></b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="1">近5年專案管理工程件數</b-th>
        <b-th class="text-center"></b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td><a class="href-style" href="javascript: void(0)" v-b-modal.query-Record3-modal>明細</a></b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="1">在建工程件數</b-th>
        <b-th class="text-center"></b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td><a class="href-style" href="javascript: void(0)" v-b-modal.query-Record4-modal>明細</a></b-td>
      </b-tr>
      <b-tr>
        <b-th rowspan="4">近5年專案管理、設計或監造工程被查核紀錄</b-th>
        <b-th class="text-left">優等</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <!-- <b-td rowspan="4"><a class="href-style" href="javascript: void(0)" @click="toDownView5()">明細</a></b-td> -->
        <b-td rowspan="4"><a class="href-style" href="javascript: void(0)" v-b-modal.query-Record5-modal>明細</a></b-td>
      </b-tr>
      <b-tr>
        <b-th class="text-left">甲等</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <!-- <b-td><a class="href-style" href="javascript: void(0)">明細</a></b-td> -->
      </b-tr>
      <b-tr>
        <b-th class="text-left">乙等</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <!-- <b-td><a class="href-style" href="javascript: void(0)">明細</a></b-td> -->
      </b-tr>
      <b-tr>
        <b-th class="text-left">丙等</b-th>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <b-td>0</b-td>
        <!-- <b-td><a class="href-style" href="javascript: void(0)">明細</a></b-td> -->
      </b-tr>
      <b-tr>
        <b-th rowspan="3">近5年參與前3大工程類別(以件數排序)</b-th>
        <b-th class="text-left">類別</b-th>
        <b-td>第1類:</b-td>
        <b-td></b-td>
        <b-td>第2類:</b-td>
        <b-td>第3類:</b-td>
      </b-tr>
      <b-tr>
        <b-th class="text-left">總金額</b-th>
        <b-td>第1類:元</b-td>
        <b-td></b-td>
        <b-td>第2類:元</b-td>
        <b-td>第3類:元</b-td>
      </b-tr>
      <b-tr>
        <b-th class="text-left">明細</b-th>
        <b-td><a class="href-style" href="javascript: void(0)" v-b-modal.query-Record6-modal>近5年參與前3大工程類別明細</a></b-td>
        <!-- <a class="href-style" href="javascript: void(0)" @click="toDownView6()"><b-td>近5年參與前3大工程類別明細</b-td></a> -->
      </b-tr>
    </b-tbody>
    <b-tr>
      <b-th class="text-left" colspan="7">本系統資料係按月批次介接，有關上開工城之機關最新填報及金質獎資訊，請至「<a class="href-style" href="javascript: void(0)">公共工程標案管理資訊系統</a>」查詢</b-th>
    </b-tr>
    <b-tr>
      <b-th class="text-left" colspan="7">重大職災害事件請連結<a class="href-style" href="javascript: void(0)">勞動部網站</a>查詢</b-th>
    </b-tr>
    <b-tr>
      <b-th class="text-left" colspan="7">有關廠商目前是否為「依政府採購法第103條目前列為拒絕往來廠商」，請至<a class="href-style" href="javascript: void(0)">政府電子採購網</a>查詢</b-th>
    </b-tr>
    
  </b-table-simple>
</div>
    <div class="text-center mb-2">
      <!-- 返回 -->
      <i-button type="arrow-left" @click="toEditView" />
    </div>
    <b-modal
      id="query-Record-modal"
      size="xl"
      title="近5年設計工程件數明細"
      header-bg-variant="secondary"
      header-text-variant="light"
      @hide="handleHideQueryRecordModel"
    >
      <section class="container mt-2">
      <div class="card">
        <div class="card-body col-11">
            <b-form-row>
              <!-- 檔案編號 -->
              <i-form-group-check :label="$t('label.fileNumber') + '：'" :item="$v.fileNumber">
                <b-form-input v-model="$v.fileNumber.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 工程名稱 -->
              <i-form-group-check :label="$t('label.engName') + '：'" :item="$v.engName">
                <b-form-input v-model="$v.engName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
        </div>
      </div>
    </section>
    <section class="container mt-2" v-show="queryStatus">
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
      </i-table>
    </section>

      <template #modal-footer="{ cancel }">
        <i-button type="search" @click="handleQueryBtn"></i-button>
        <b-button @click="cancel()" variant="info">關閉</b-button>
      </template>
    </b-modal>

    <b-modal
      id="query-Record2-modal"
      size="xl"
      title="近5年監造工程件數明細"
      header-bg-variant="secondary"
      header-text-variant="light"
      @hide="handleHideQueryRecordModel"
    >
      <section class="container mt-2">
      <div class="card">
        <div class="card-body col-11">
            <b-form-row>
              <!-- 檔案編號 -->
              <i-form-group-check :label="$t('label.fileNumber') + '：'" :item="$v.fileNumber">
                <b-form-input v-model="$v.fileNumber.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 工程名稱 -->
              <i-form-group-check :label="$t('label.engName') + '：'" :item="$v.engName">
                <b-form-input v-model="$v.engName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
        </div>
      </div>
    </section>
    <section class="container mt-2" v-show="queryStatus">
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
      </i-table>
    </section>

      <template #modal-footer="{ cancel }">
        <i-button type="search" @click="handleQueryBtn"></i-button>
        <b-button @click="cancel()" variant="info">關閉</b-button>
      </template>
    </b-modal>

    <b-modal
      id="query-Record3-modal"
      size="xl"
      title="近5年專案管理工程件數明細"
      header-bg-variant="secondary"
      header-text-variant="light"
      @hide="handleHideQueryRecordModel"
    >
      <section class="container mt-2">
      <div class="card">
        <div class="card-body col-11">
            <b-form-row>
              <!-- 檔案編號 -->
              <i-form-group-check :label="$t('label.fileNumber') + '：'" :item="$v.fileNumber">
                <b-form-input v-model="$v.fileNumber.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 工程名稱 -->
              <i-form-group-check :label="$t('label.engName') + '：'" :item="$v.engName">
                <b-form-input v-model="$v.engName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
        </div>
      </div>
    </section>
    <section class="container mt-2" v-show="queryStatus">
      <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table3.data"
        :is-server-side-paging="true"
        :fields="table3.fields_backend"
        :totalItems="table3.totalItems"
        @changePagination="handlePaginationChanged" 
      >
      </i-table>
    </section>

      <template #modal-footer="{ cancel }">
        <i-button type="search" @click="handleQueryBtn"></i-button>
        <b-button @click="cancel()" variant="info">關閉</b-button>
      </template>
    </b-modal>

    <b-modal
      id="query-Record4-modal"
      size="xl"
      title="在建工程件數明細"
      header-bg-variant="secondary"
      header-text-variant="light"
      @hide="handleHideQueryRecordModel"
    >
      <section class="container mt-2">
      <div class="card">
        <div class="card-body col-11">
            <b-form-row>
              <!-- 檔案編號 -->
              <i-form-group-check :label="$t('label.fileNumber') + '：'" :item="$v.fileNumber">
                <b-form-input v-model="$v.fileNumber.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 工程名稱 -->
              <i-form-group-check :label="$t('label.engName') + '：'" :item="$v.engName">
                <b-form-input v-model="$v.engName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 科別 -->
              <i-form-group-check :label="$t('label.subject') + '：'" :item="$v.subject">
                <b-form-select v-model="$v.subject.$model" :options="formOptions.subject">
                  <template #first>
                    <option value="">不拘</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>
        </div>
      </div>
    </section>
    <section class="container mt-2" v-show="queryStatus">
      <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table4.data"
        :is-server-side-paging="true"
        :fields="table4.fields_backend"
        :totalItems="table4.totalItems"
        @changePagination="handlePaginationChanged" 
      >
      </i-table>
    </section>

      <template #modal-footer="{ cancel }">
        <i-button type="search" @click="handleQueryBtn"></i-button>
        <b-button @click="cancel()" variant="info">關閉</b-button>
      </template>
    </b-modal>

    <b-modal
      id="query-Record5-modal"
      size="xl"
      title="近5年專案管理、設計或監造工程被查核紀錄明細"
      header-bg-variant="secondary"
      header-text-variant="light"
      @hide="handleHideQueryRecordModel"
    >
      <section class="container mt-2">
      <div class="card">
        <div class="card-body col-11">
            <b-form-row>
              <!-- 檔案編號 -->
              <i-form-group-check :label="$t('label.fileNumber') + '：'" :item="$v.fileNumber">
                <b-form-input v-model="$v.fileNumber.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 工程名稱 -->
              <i-form-group-check :label="$t('label.engName') + '：'" :item="$v.engName">
                <b-form-input v-model="$v.engName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
        </div>
      </div>
    </section>
    <section class="container mt-2" v-show="queryStatus">
      <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table5.data"
        :is-server-side-paging="true"
        :fields="table5.fields_backend"
        :totalItems="table5.totalItems"
        @changePagination="handlePaginationChanged" 
      >
      </i-table>
    </section>

      <template #modal-footer="{ cancel }">
        <i-button type="search" @click="handleQueryBtn"></i-button>
        <b-button @click="cancel()" variant="info">關閉</b-button>
      </template>
    </b-modal>

    <b-modal
      id="query-Record6-modal"
      size="xl"
      title="近5年參與前3大工程類別明細"
      header-bg-variant="secondary"
      header-text-variant="light"
      @hide="handleHideQueryRecordModel"
    >
      <section class="container mt-2">
      <div class="card">
        <div class="card-body col-11">
            <b-form-row>
              <!-- 檔案編號 -->
              <i-form-group-check :label="$t('label.fileNumber') + '：'" :item="$v.fileNumber">
                <b-form-input v-model="$v.fileNumber.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 工程名稱 -->
              <i-form-group-check :label="$t('label.engName') + '：'" :item="$v.engName">
                <b-form-input v-model="$v.engName.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>
        </div>
      </div>
    </section>
    <section class="container mt-2" v-show="queryStatus">
      <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table6.data"
        :is-server-side-paging="true"
        :fields="table6.fields_backend"
        :totalItems="table6.totalItems"
        @changePagination="handlePaginationChanged" 
      >
      </i-table>
    </section>

      <template #modal-footer="{ cancel }">
        <i-button type="search" @click="handleQueryBtn"></i-button>
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
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';

interface propsType {
  engEngrSubjectpoint: IEngEngrSubjectpoint;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0601EditInfo',
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

    const admSysCodeService = new AdmSysCodeService();

    const formOptions = reactive({
      subject: [],
    });

    let formDefault = {
      companyName: '',
      saleAreaList: '',
      nowEngr: '',
      comp5year: '',
      comp5yearamount: '',
      archCompCount: '',
      subject: '',
    };

    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      companyName: {},
      saleAreaList: {},
      nowEngr: {},
      comp5year: {},
      comp5yearamount: {},
      archCompCount: {},
      fileNumber: {},
      engName: {},
      subject: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const table = reactive({
      fields_backend: [
        {
          // 工程名稱
          key: 'engName',
          label: i18n.t('label.engName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 主辦單位
          key: 'organizer',
          label: i18n.t('label.organizer'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 工程類別
          key: 'projectCodes',
          label: i18n.t('label.projectCodes'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 決標金額
          key: 'ctSum',
          label: i18n.t('label.ctSum'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 開工日期
          key: 'asstartDate',
          label: i18n.t('label.asstartDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 預計完工日期
          key: 'exaeDate',
          label: i18n.t('label.exaeDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 目前進度
          key: 'curSch',
          label: i18n.t('label.curSch'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 設計
          key: 'design',
          label: i18n.t('label.design'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 監造
          key: 'supervision',
          label: i18n.t('label.supervision'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 專案管理
          key: 'proManage',
          label: i18n.t('label.proManage'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const table2 = reactive({
      fields_backend: [
        {
          // 工程名稱
          key: 'engName',
          label: i18n.t('label.engName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 主辦單位
          key: 'organizer',
          label: i18n.t('label.organizer'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 工程類別
          key: 'projectCodes',
          label: i18n.t('label.projectCodes'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 決標金額
          key: 'ctSum',
          label: i18n.t('label.ctSum'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 開工日期
          key: 'asstartDate',
          label: i18n.t('label.asstartDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 預計完工日期
          key: 'exaeDate',
          label: i18n.t('label.exaeDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 目前進度
          key: 'curSch',
          label: i18n.t('label.curSch'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 設計
          key: 'design',
          label: i18n.t('label.design'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 監造
          key: 'supervision',
          label: i18n.t('label.supervision'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 專案管理
          key: 'proManage',
          label: i18n.t('label.proManage'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });
    
    const table3 = reactive({
      fields_backend: [
        {
          // 工程名稱
          key: 'engName',
          label: i18n.t('label.engName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 主辦單位
          key: 'organizer',
          label: i18n.t('label.organizer'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 工程類別
          key: 'projectCodes',
          label: i18n.t('label.projectCodes'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 決標金額
          key: 'ctSum',
          label: i18n.t('label.ctSum'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 開工日期
          key: 'asstartDate',
          label: i18n.t('label.asstartDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 預計完工日期
          key: 'exaeDate',
          label: i18n.t('label.exaeDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 目前進度
          key: 'curSch',
          label: i18n.t('label.curSch'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 設計
          key: 'design',
          label: i18n.t('label.design'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 監造
          key: 'supervision',
          label: i18n.t('label.supervision'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 專案管理
          key: 'proManage',
          label: i18n.t('label.proManage'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const table4 = reactive({
      fields_backend: [
        {
          // 工程名稱
          key: 'engName',
          label: i18n.t('label.engName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 主辦單位
          key: 'organizer',
          label: i18n.t('label.organizer'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 工程類別
          key: 'projectCodes',
          label: i18n.t('label.projectCodes'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 決標金額
          key: 'ctSum',
          label: i18n.t('label.ctSum'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 開工日期
          key: 'asstartDate',
          label: i18n.t('label.asstartDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 預計完工日期
          key: 'exaeDate',
          label: i18n.t('label.exaeDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 目前進度
          key: 'curSch',
          label: i18n.t('label.curSch'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 設計
          key: 'design',
          label: i18n.t('label.design'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 監造
          key: 'supervision',
          label: i18n.t('label.supervision'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 專案管理
          key: 'proManage',
          label: i18n.t('label.proManage'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const table5 = reactive({
      fields_backend: [
        {
          // 查核日期
          key: 'checkDate',
          label: i18n.t('label.checkDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 查核小組
          key: 'checkTeam',
          label: i18n.t('label.checkTeam'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 工程名稱
          key: 'engName',
          label: i18n.t('label.engName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 主辦單位
          key: 'adminCompName',
          label: i18n.t('label.adminCompName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 開工日期
          key: 'asstartDate',
          label: i18n.t('label.asstartDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 設計
          key: 'design',
          label: i18n.t('label.design'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 監造
          key: 'supervision',
          label: i18n.t('label.supervision'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 監造扣點
          key: 'supervisionPoint',
          label: i18n.t('label.supervisionPoint'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 專案管理
          key: 'proManage',
          label: i18n.t('label.proManage'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 管理扣點
          key: 'managePoint',
          label: i18n.t('label.managePoint'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 評分
          key: 'score',
          label: i18n.t('label.score'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const table6 = reactive({
      fields_backend: [
        {
          // 工程名稱
          key: 'engName',
          label: i18n.t('label.engName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 主辦單位
          key: 'organizer',
          label: i18n.t('label.organizer'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 工程類別
          key: 'projectCodes',
          label: i18n.t('label.projectCodes'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 決標金額
          key: 'ctSum',
          label: i18n.t('label.ctSum'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 開工日期
          key: 'asstartDate',
          label: i18n.t('label.asstartDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 預計完工日期
          key: 'exaeDate',
          label: i18n.t('label.exaeDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 目前進度
          key: 'curSch',
          label: i18n.t('label.curSch'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 設計
          key: 'design',
          label: i18n.t('label.design'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 監造
          key: 'supervision',
          label: i18n.t('label.supervision'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 專案管理
          key: 'proManage',
          label: i18n.t('label.proManage'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    onMounted(() => {
      // handleQuery();
      getAllSysCodes();
    });

    const handleQueryBtn = () => {
      handleQuery();
    };

    function handleHideQueryRecordModel() {
      // table.queryString = '';
      table.data = undefined;
      queryStatus.value = false;
    }

    function toQueryView() {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    }

    const toEditView = (entity: IEngEngrSubjectpoint, formStatus: FormStatusEnum, index?: number) => {
        const criteria = Object.assign({}, form);
        criteria['index'] = index;
        criteria['totalItems'] = table.totalItems;

        navigateByNameAndParams('Eng0601Query', {
          engEngrSubjectpoint: entity,
          formStatus: formStatus,
          criteria: criteria,
        });
      };

    const data ={
      companyName: '一代建築師事務所',
      saleAreaList: '',
      nowEngr: '',
      archCompCount: '1',
      comp5year: '0',
      comp5yearamount: '0',
    }

    const handleQuery = () => {

      //驗證
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          table.data = [
            {
              no: '1',
              engName: '106Y0323',
              organizer: '桃園市立楊梅幼兒園',
              projectCodes: '裝(整)修工程',
              ctSum: '1498.00',
              asstartDate: '2017/05/01',
              exaeDate: '2017/07/27',
              curSch: '100%',
              design: '一艸建築師',
              supervision: '一艸建築師',
              proManage: '',
            },
          ];
          table.totalItems = 1;

          table2.data = [
            {
              no: '1',
              engName: '106Y0323',
              organizer: '桃園市立楊梅幼兒園',
              projectCodes: '裝(整)修工程',
              ctSum: '1498.00',
              asstartDate: '2017/05/01',
              exaeDate: '2017/07/27',
              curSch: '100%',
              design: '一艸建築師',
              supervision: '一艸建築師',
              proManage: '',
            },
          ];
          table2.totalItems = 1;

          table3.data = [
            {
              no: '1',
              engName: '106Y0323',
              organizer: '桃園市立楊梅幼兒園',
              projectCodes: '裝(整)修工程',
              ctSum: '1498.00',
              asstartDate: '2017/05/01',
              exaeDate: '2017/07/27',
              curSch: '100%',
              design: '一艸建築師',
              supervision: '一艸建築師',
              proManage: '',
            },
          ];
          table3.totalItems = 1;

          table4.data = [
            {
              // engName organizer projectCodes ctSum asstartDate exaeDate curSch design supervision proManage
              no: '1',
              engName: '106Y0323',
              organizer: '桃園市立楊梅幼兒園',
              projectCodes: '裝(整)修工程',
              ctSum: '1498.00',
              asstartDate: '2017/05/01',
              exaeDate: '2017/07/27',
              curSch: '100%',
              design: '一艸建築師',
              supervision: '一艸建築師',
              proManage: '',
            },
          ];
          table4.totalItems = 1;

          table5.data = [
            {
              no: '1',
              checkDate: '2017/06/26',
              checkTeam: '行政院公共委員會',
              engName: '國家建築工程',
              adminCompName: '台灣電力有限公司',
              asstartDate: '2012/05/22',
              design: '建築事務所',
              supervision: '建築事務所',
              supervisionPoint: '0',
              proManage: '顧問有限公司',
              managePoint: '0',
              score: '77',
            },
          ];
          table5.totalItems = 1;

          table6.data = [
            {
              no: '1',
              engName: '106Y0323',
              organizer: '桃園市立楊梅幼兒園',
              projectCodes: '裝(整)修工程',
              ctSum: '1498.00',
              asstartDate: '2017/05/01',
              exaeDate: '2017/07/27',
              curSch: '100%',
              design: '一艸建築師',
              supervision: '一艸建築師',
              proManage: '',
            },
          ];
          table6.totalItems = 1;
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
      formStatusRef,
      queryStatus,
      table,
      table2,
      table3,
      table4,
      table5,
      table6,
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

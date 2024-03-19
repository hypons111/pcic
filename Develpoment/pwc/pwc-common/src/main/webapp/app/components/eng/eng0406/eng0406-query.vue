<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
                <b-col offset="10">
                <i-button type="practiceChart" @click=""></i-button>
                </b-col>
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-11">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <!-- 承辦日期 -->
              <i-form-group-check :label="$t('label.contractorDate') + '：'" :dual1="$v.execDateStart" :dual2="$v.execDateEnd">
                <b-input-group>
                  <i-date-picker
                    v-model="$v.execDateStart.$model"
                    :disabled-date="notAfterexecDateEnd"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                  <b-input-group-text>至</b-input-group-text>
                  <i-date-picker
                    v-model="$v.execDateEnd.$model"
                    :disabled-date="notBeforeexecDateStart"
                    placeholder="yyy/MM/dd"
                  ></i-date-picker>
                </b-input-group>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="handleQueryBtn"></i-button>
                <i-button type="file-earmark-plus" @click="toEditView(null, FormStatusEnum.CREATE)"></i-button>
                <i-button type="box-arrow-up-right" @click=""></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2">
      <!--切換角色按鈕-->
      <b-button size="md" variant="warning" @click="switchRoles">
        <font-awesome-icon :icon="['fas', 'people-arrows']" />
        {{ roleRef }}
      </b-button>
    </section>

    <section class="container mt-2" v-show="stepVisible.step2">
      <i-table
        small
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :is-server-side-paging="true"
        :items="table.data"
        :fields="table.fields"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >

        <template #head(caseNocaseName)>
          {{ $t('label.caseNum') }}
          <br/>
          {{ $t('label.caseNa') }} 
        </template>

        <template #cell(caseNocaseName) = "{ item }">
          {{ item.caseNo }}
          <br/>
          {{ item.caseName }} 
        </template>

        <template #head(execorgNamecompName)>
          {{ $t('label.wkut') }}
          <br/>
          {{ $t('label.compName2') }} 
        </template>

        <template #cell(execorgNamecompName) = "{ item }">
          {{ item.execorgName }}
          <br/>
          {{ item.compName }} 
        </template>
 
        <template #head(contactPersoncontactTel)>
          {{ $t('label.contactPerson') }}
          <br/>
          {{ $t('label.contactTel') }} 
        </template>

        <template #cell(contactPersoncontactTel) = "{ item }">
          {{ item.contactPerson }}
          <br/>
          {{ item.contactTel }} 
        </template>

        <template #cell(execDate1) = "{ item }">
          {{ item.execDateStart }}
          <br/>
          {{ item.execDateEnd }} 
        </template>

        <template #head(projectType)>
          {{ $t('label.projectType') }}
          <br/>
          {{ $t('label.recordRange') }} 
          <br/>
          {{ $t('label.recordItems') }} 
        </template>

        <template #cell(projectType) = "{ item }">
          {{ item.projectType }}
          <br/>
          {{ item.recordRangeList }} 
          {{ item.recordRangeOther }} 
          <br/>
          {{ item.recordItemsList }} 
          {{ item.recordItemsOther }} 
        </template>

        <!--<template #cell(action)="{ item, index }">-->
        <template #cell(action)="row">
          <!-- 檢視 -->
          <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button>
          <!-- 編輯 -->
          <i-button size="sm" type="pencil-square" @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)"></i-button>
          <!-- 刪除 -->
          <i-button size="sm" type="delete" @click=""></i-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import axios from 'axios';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import EngCourseService from '@/components/eng/eng0401/eng-course.service';
import { defineComponent, reactive, ref, onActivated, toRefs, onMounted, Ref } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { FormStatusEnum, RoleEnum, StatusEnum } from '@/shared/enum';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { IEngCourse, EngCourse } from '@/shared/model/eng/eng-course.model';
import { IEng0401, Eng0401 } from '@/shared/model/eng/eng0401.model';
import { IEngEngrSubjectpoint, EngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import { useRouter } from '@u3u/vue-hooks';

interface propsType {
  isReload: boolean;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0406Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
    criteria: {
      required: false,
      type: Object,
    },
  },
  setup(props) {
    const { isReload, criteria } = toRefs(props);
    const admSysCodeService = new AdmSysCodeService();
    const notificationService = useNotification();
    const $bvModal = useBvModal();
    const iTableComponent = ref(null);

    const engCourseService = new EngCourseService();

    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);
    // 切換角色
    const switchRoles = () => {
      if (roleRef.value === RoleEnum.ADMIN) roleRef.value = RoleEnum.USER;
      else roleRef.value = RoleEnum.ADMIN;
    };

    onMounted(() => {
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
      formOptions.status = formOptions.status.filter(item => item.value !== StatusEnum.REVIEW1);
    }

    onActivated(() => {
      if (isReload.value) {
        handleQuery();
      }
      // iTableComponent內的currentPage改變就會觸發handlePaginationChanged事件
      iTableComponent.value.state.pagination.currentPage = criteria.value.currentPage + 1;
    });

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      step2: false,
    });

    const formOptions = reactive({
      status: [],
    });

    const formDefault = {
      execDateStart: null,
      execDateEnd: null,
    };
    const form = reactive(Object.assign({}, formDefault, new Pagination()));
    const rules = {
      execDateStart: {},
      execDateEnd: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields: [
        {
          // 案件編號+案件名稱
          key: 'caseNocaseName',
          //label: i18n.t('label.adminCompName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '15%' },
        },
        {
          // 案件類型 
          key: 'caseType',
          label: i18n.t('label.caseType'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '20%' },
        },
        {  
          // 執行機關 + 得標機構
          key: 'execorgNamecompName',
          // label: i18n.t('label.applyDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 聯絡人姓名 + 電話
          key: 'contactPersoncontactTel',
          // label: i18n.t('label.courseDate'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 預定承辦日期
          key: 'execDate1',
          label: i18n.t('label.execDate1'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 工程種類 + 簽證範圍 + 簽證項目
          key: 'projectType',
          // label: i18n.t('label.reviewStatus'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 動作
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    function handleQueryBtn() {
      iTableComponent.value.state.pagination.currentPage = 1;
      if (!form.currentPage) iTableComponent.value.emitPagination();
    }

    function handlePaginationChanged(pagination: Pagination) {
      Object.assign(form, pagination);
      handleQuery();
    }
 
    function handleQuery() {
      const now = new Date();
      const offset = now.getTimezoneOffset();
      form.execDateStart = form.execDateStart ? new Date(form.execDateStart.getTime() - offset * 60 * 1000) : null;
      form.execDateEnd = form.execDateEnd ? new Date(form.execDateEnd.getTime() - offset * 60 * 1000) : null;


      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          stepVisible.step2 = true;
          table.data = [
            {
              no: '1',
              caseNo: '1212121',
              caseName: '2121211',
              caseType: '機關委辦',
              execorgName: '桃園市航空工程處',
              compName: '亞新工程有限公司',
              contactPerson: '廖士傑先生',
              contactTel: '(03)30987623',
              execDateStart: '2022/02/07',
              execDateEnd: '2027/02/07',
              projectType: '新鎮區開發工程',
              recordRangeOther: '工址調查',
              recordItemsOther: '設計',
            },
          ];
          table.totalItems = 1; //後端分頁
      /*    axios
            .post('/eng-courses/criteria-jpa', form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .catch(notificationErrorHandler(notificationService));*/
        }
      });
    }

    /*const toEditView = (engCourseId: string, formStatus: FormStatusEnum, index?: number) => {
      if (formStatus !== FormStatusEnum.CREATE) {
        engCourseService
          .find(engCourseId)
          .then(eng0401 => routerTo(eng0401, formStatus, index))
          .catch(notificationErrorHandler(notificationService));
      } else {
        const eng0401 = new Eng0401();
        routerTo(eng0401, formStatus, index);
      }
    };*/

    const toEditView = (entity: IEngEngrSubjectpoint, formStatus: FormStatusEnum, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['index'] = index;
      criteria['totalItems'] = table.totalItems;

      navigateByNameAndParams('Eng0406EditInfo', {
        engEngrSubjectpoint: entity,
        formStatus: formStatus,
        criteria: criteria,
      });
    };
    const routerTo = (eng0401: IEng0401, formStatus: FormStatusEnum, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['index'] = index;
      criteria['totalItems'] = table.totalItems;

      console.log(eng0401);
      // navigateByNameAndParams('Eng0401EditInfo', {
      //   eng0401: eng0401,
      //   formStatus: formStatus,
      //   role: roleRef.value,
      //   criteria: criteria,
      // });
    };   

    function notBeforeexecDateStart(date: Date) {
      if (form.execDateStart) return date < new Date(form.execDateStart);
    }
  
    function notAfterexecDateEnd(date: Date) {
      if (form.execDateEnd) return date > new Date(form.execDateEnd);
    }

    return {
      switchRoles,
      $v,
      reset,
      table,
      roleRef,
      EngCourse,
      toEditView,
      stepVisible,
      formOptions,
      handleQueryBtn,
      FormStatusEnum,
      iTableComponent,
      formOptionsFormatter,
      handlePaginationChanged,
      notBeforeexecDateStart,
      notAfterexecDateEnd,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/', '-'),
  },
});
</script>
<style scoped></style>

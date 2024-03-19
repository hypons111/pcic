<template>
  <b-container>
    <b-card no-body>
      <b-card-header class="py-1">
        <b-row class="align-items-center">
          <b-col cols="11">
            <h5 class="m-0">
              <font-awesome-icon icon="search"></font-awesome-icon>
              查詢條件
            </h5>
          </b-col>
          <b-col cols="1" class="p-0">
            <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
              <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
              <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
            </b-button>
          </b-col>
        </b-row>
      </b-card-header>
      <b-card-body class="col-11">
        <b-collapse v-model="stepVisible.step1">
          <b-form-row>
            <!-- 課程名稱 -->
            <i-form-group-check :label="$t('label.courseName') + '：'" :item="$v.courseName">
              <b-form-input v-model="$v.courseName.$model" trim lazy></b-form-input>
            </i-form-group-check>
            <!-- 課程日期 -->
            <i-form-group-check :label="$t('label.courseDate') + '：'" :dual1="$v.dateStart" :dual2="$v.dateEnd">
              <i-dual-date-picker :dual1.sync="$v.dateStart.$model" :dual2.sync="$v.dateEnd.$model"></i-dual-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 主辦單位 -->
            <i-form-group-check :label="$t('label.adminCompName') + '：'" :item="$v.adminCompName">
              <b-form-input v-model="$v.adminCompName.$model" trim lazy></b-form-input>
            </i-form-group-check>
            <!-- 核備文號 -->
            <i-form-group-check :label="$t('label.docNo') + '：'" :item="$v.docNo">
              <b-form-input v-model="$v.docNo.$model" trim lazy></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 審核狀態-->
            <i-form-group-check :label="$t('label.reviewStatus') + '：'" :item="$v.status">
              <b-form-select v-model="$v.status.$model" :options="formOptions.status">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 審核日期 -->
            <i-form-group-check
              v-if="functionId === 'ENG0402'"
              :label="$t('label.reviewDate') + '：'"
              :dual1="$v.reviewDateStart"
              :dual2="$v.reviewDateEnd"
            >
              <i-dual-date-picker :dual1.sync="$v.reviewDateStart.$model" :dual2.sync="$v.reviewDateEnd.$model"></i-dual-date-picker>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <b-col offset="8">
              <i-button type="search" @click="handleQueryBtn"></i-button>
              <i-button type="x-circle" @click="reset"></i-button>
            </b-col>
          </b-form-row>
        </b-collapse>
      </b-card-body>
    </b-card>

    <div v-if="functionId === 'ENG0401'" class="mt-2">
      <i-button type="file-earmark-plus" @click="toEditView(null, FormStatusEnum.CREATE)"></i-button>
      <!--切換角色按鈕-->
      <b-button size="md" variant="warning" @click="switchRoles">
        <font-awesome-icon :icon="['fas', 'people-arrows']" />
        {{ roleRef }}
      </b-button>
    </div>

    <i-table
      v-show="stepVisible.step2"
      small
      class="mt-2"
      ref="iTableComponent"
      items-undefined-behavior="loading"
      is-server-side-paging
      :items="table.data"
      :fields="table.fields"
      :total-items="table.totalItems"
      @changePagination="handlePaginationChanged"
    >
      <template #cell(adminCompName)="{ item }">
        <i-part-of-message :id="item.engCourseId + '1'" :value="item.adminCompName"></i-part-of-message>
      </template>
      <template #cell(courseName)="{ item }">
        <i-part-of-message :id="item.engCourseId + '2'" :value="item.courseName"></i-part-of-message>
      </template>
      <template #cell(courseDate)="{ item }">
        {{ item.dateStart | dateToString }}<br />
        至<br />
        {{ item.dateEnd | dateToString }}
      </template>

      <template v-slot:cell(contactInfo)="{ item }">
        聯絡人：{{ item.contactPerson }}<br />
        電話：{{ item.contactTel }}<br />
        信箱：{{ item.contactEmail }}
      </template>

      <template #cell(action)="{ item, index }">
        <!-- 檢視 -->
        <i-button
          v-if="functionId === 'ENG0401'"
          size="sm"
          type="binoculars"
          @click="toEditView(item.engCourseId, FormStatusEnum.READONLY, index)"
        ></i-button>
        <!-- 編輯 -->
        <i-button
          v-if="functionId === 'ENG0401'"
          size="sm"
          type="pencil-square"
          @click="toEditView(item.engCourseId, FormStatusEnum.EDIT, index)"
        ></i-button>
        <!-- 作廢 -->
        <i-button v-if="functionId === 'ENG0401'" size="sm" type="clipboard-x"></i-button>
        <!-- 編輯(課程審核) -->
        <i-button
          v-if="functionId === 'ENG0402'"
          size="sm"
          type="pencil-square"
          @click="toEditView(item.engCourseId, FormStatusEnum.REVIEW, index)"
        ></i-button>
      </template>
    </i-table>
  </b-container>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import axios from 'axios';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import EngCourseService from '@/components/eng/eng0401/eng-course.service';
import { defineComponent, reactive, ref, onActivated, toRefs, onMounted, Ref, watch } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { FormStatusEnum, RoleEnum, StatusEnum } from '@/shared/enum';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { EngCourse } from '@/shared/model/eng/eng-course.model';
import { IEng0401, Eng0401 } from '@/shared/model/eng/eng0401.model';
import { useRouter } from '@u3u/vue-hooks';

interface propsType {
  isReload: boolean;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0401Query',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
    criteria: {
      type: Object,
      required: false,
    },
  },
  setup(props) {
    const { isReload, criteria } = toRefs(props);
    const admSysCodeService = new AdmSysCodeService();
    const notificationService = useNotification();
    const $bvModal = useBvModal();
    const iTableComponent = ref(null);

    const engCourseService = new EngCourseService();

    const functionId = ref(useRouter().route.value.meta.functionId);

    watch(
      () => useRouter().route.value.meta.functionId,
      newVal => {
        functionId.value = newVal;
      }
    );

    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);
    // 切換角色
    const switchRoles = () => {
      if (roleRef.value === RoleEnum.ADMIN) roleRef.value = RoleEnum.USER;
      else roleRef.value = RoleEnum.ADMIN;
    };

    onMounted(() => {
      getAllSysCodes();
      if (functionId.value === 'ENG0402') {
        formDefault.status = StatusEnum.REVIEW2_1;
        form.status = StatusEnum.REVIEW2_1;
        iTableComponent.value.emitPagination();
      }
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
      if (criteria.value) {
        iTableComponent.value.state.pagination.currentPage = criteria.value.currentPage + 1;
      }
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
      courseName: undefined,
      dateStart: undefined,
      dateEnd: undefined,
      adminCompName: undefined,
      docNo: undefined,
      status: undefined,
      reviewDateStart: undefined,
      reviewDateEnd: undefined,
    };
    const form = reactive(Object.assign({}, formDefault, new Pagination()));
    const rules = {
      courseName: {},
      dateStart: {},
      dateEnd: {},
      adminCompName: {},
      docNo: {},
      status: {},
      reviewDateStart: {},
      reviewDateEnd: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const table = reactive({
      fields: [
        {
          // 主辦單位
          key: 'adminCompName',
          label: i18n.t('label.adminCompName'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
          thStyle: { width: '15%' },
        },
        {
          // 課程名稱
          key: 'courseName',
          label: i18n.t('label.courseName'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
          thStyle: { width: '20%' },
        },
        {
          // 申請日期
          key: 'applyDate',
          label: i18n.t('label.applyDate'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '/', '-'),
        },
        {
          // 課程日期
          key: 'courseDate',
          label: i18n.t('label.courseDate'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          // 聯絡資訊
          key: 'contactInfo',
          label: i18n.t('label.contactInfo'),
          sortable: false,
          thClass: 'text-center align-middle',
          tdClass: 'text-left align-middle',
        },
        {
          // 審核狀態
          key: 'status',
          label: i18n.t('label.reviewStatus'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.status, value),
        },
        {
          // 核備文號
          key: 'docNo',
          label: i18n.t('label.docNo'),
          sortable: true,
          thClass: 'text-center align-middle',
          tdClass: 'text-center align-middle',
        },
        {
          // 動作
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center align-middle',
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
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          stepVisible.step2 = true;
          axios
            .post('/eng-courses/criteria-jpa', form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    }

    const toEditView = (engCourseId: string, formStatus: FormStatusEnum, index?: number) => {
      if (formStatus !== FormStatusEnum.CREATE) {
        engCourseService
          .find(engCourseId)
          .then(eng0401 => routerTo(eng0401, formStatus, index))
          .catch(notificationErrorHandler(notificationService));
      } else {
        const eng0401 = new Eng0401();
        routerTo(eng0401, formStatus, index);
      }
    };

    const routerTo = (eng0401: IEng0401, formStatus: FormStatusEnum, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['index'] = index;
      criteria['totalItems'] = table.totalItems;

      navigateByNameAndParams(functionId.value === 'ENG0401' ? 'Eng0401EditInfo' : 'Eng0402EditInfo', {
        eng0401: eng0401,
        formStatus: formStatus,
        role: roleRef.value,
        criteria: criteria,
      });
    };

    return {
      switchRoles,
      $v,
      reset,
      table,
      roleRef,
      EngCourse,
      functionId,
      toEditView,
      stepVisible,
      formOptions,
      handleQueryBtn,
      FormStatusEnum,
      iTableComponent,
      formOptionsFormatter,
      handlePaginationChanged,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/', '-'),
  },
});
</script>
<style scoped></style>

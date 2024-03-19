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
              <!-- 姓名 -->
              <i-form-group-check :label="$t('label.name') + '：'" :item="$v.name">
                <b-form-input v-model="$v.name.$model" trim></b-form-input>
              </i-form-group-check>
              <!-- 身分證字號 -->
              <i-form-group-check :label="$t('label.idno') + '：'" :item="$v.idno">
                <b-form-input v-model="$v.idno.$model" trim></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <!-- 匯入年度 -->
              <i-form-group-check :label="$t('label.imyear')+'：'" :item="$v.importDate">
                <i-date-picker
                  v-model="$v.importDate.$model"
                  type="year"
                  placeholder="yyy"
                ></i-date-picker>
              </i-form-group-check>

              <!-- 科別 -->
              <i-form-group-check :label="$t('label.subject') + '：'" :item="$v.subject">
                <b-form-select v-model="$v.subject.$model" :options="formOptions.subject">
                  <template #first>
                    <option value="">請選擇</option>
                  </template>
                </b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="handleQueryBtn"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
            <!-- <b-form-row> -->
            <!-- excel -->
            <!-- <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="9"
              :label="$t('label.exceldix') + '：'"
              :item="$v.appendixFiles"
            > -->
            <!-- v-model="$v.appendixFiles.$model"
                :state="validateState($v.appendixFiles)" -->
             <!-- <b-form-file
                multiple
                accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                browse-text="選擇檔案"
                placeholder="未選擇任何檔案"
                drop-placeholder="拖曳檔案至此"
                
                @input="appendixSelected"
                class="col-7"
              >
               <template slot="file-name" slot-scope="{ names }">
                  <b-badge variant="info">{{ names[0] }}</b-badge>
                  <b-badge v-if="names.length > 1" variant="info" class="ml-1">{{ names[1] }}</b-badge>
                  <b-badge v-if="names.length > 2" variant="info" class="ml-1"> + {{ names.length - 2 }} 個檔案 </b-badge>
                </template>
              </b-form-file>
              <span class="remark-text col-12 p-0"
                >考試證號不得重複</span
              > -->
               <!-- <span v-if="formStatusRef !== FormStatusEnum.READONLY" class="remark-text col-12 p-0"
                >考試證號不得重複</span
              > -->
            <!-- </i-form-group-check> -->

              <!-- <b-col offset="6">
                <i-button type="file-earmark-arrow-up" @click=""></i-button>
                <i-button type="example-file-earmark-arrow-down" @click="downloadAdmFile($v.picFileInfo.$model.id)"></i-button>
              </b-col>
            </b-form-row> -->
            <!-- <b-form-row class="col-12 border border-dark" > -->
            <b-form-row>
              <i-form-group-check
                class="col-12"
                label-cols="2"
                content-cols="10"
                :label="$t('label.exceldix') + '：'"
              >
                <b-col class="col-10  pt-4">
                  <b-form-file
                    v-model="$v.inputFile.$model"
                    multiple
                    plain
                  >
                  </b-form-file> 
                </b-col>
                <!-- <b-col class="col-2 pt-4"> -->
                <b-col offset="6">
                  <b-button variant="outline-secondary"  @click="putSaveForm">上傳附件</b-button>
                  <i-button type="example-file-earmark-arrow-down" @click="downloadAdmFile($v.picFileInfo.$model.id)"></i-button>
                </b-col>  
              </i-form-group-check>             
            </b-form-row>
          </b-collapse>
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
        :fields="table.fields"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >
        <template #cell(content)="{ item }">
          <i-part-of-message :id="item.engEngrSubjectpointId" :value="item.content"></i-part-of-message>
        </template>

        <!-- <template #cell(importDate) = "{ item }">
          {{ item.addrOther }}
        </template> -->
        
        <template #cell(contactAddr) = "{ item }">
          <!-- {{ item.addrZip }}
          {{ item.addrCity }}  
          {{ item.addrTown }} -->
          {{ item.addrOther }}
        </template>
        <!--  -->
        <template #cell(action)="row">
          <!-- 建證書 -->
          <!-- @click="toEditbook(row.item, FormStatusEnum.EDIT, row.index)" -->
          <i-button size="sm" type="build-certificate" @click="toEditbook(new EngEngrExam(), FormStatusEnum.CREATE)"></i-button>
          <!-- 檢視 -->
          <!-- <i-button size="sm" type="binoculars" @click="toEditView(row.item, FormStatusEnum.READONLY, row.index)"></i-button> -->
          <!-- 編輯 -->
          <i-button size="sm" type="pencil-square" @click="toEditView(row.item, FormStatusEnum.EDIT, row.index)"></i-button>
          <!-- 刪除 -->
          <i-button size="sm" type="trash" @click="submitForm"
          ></i-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import i18n from '@/shared/i18n';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { IEngEngrExam, EngEngrExam } from '@/shared/model/eng/eng-engr-exam.model';
import { onMounted, onActivated, reactive, ref, defineComponent, toRefs, watch, Ref } from '@vue/composition-api';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { Pagination } from '@/shared/model/pagination.model';
import { formatToString } from '@/shared/date/minguo-calendar-utils';
import { RoleEnum, FormStatusEnum } from '@/shared/enum';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { IEngEngrCertificate, EngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';

interface propsType {
  isReload: boolean;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0107Query',
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
    const queryStatus = ref(false);
    // 手動定義角色
    const roleRef: Ref<RoleEnum> = ref(RoleEnum.ADMIN);

    onMounted(() => {
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    watch(roleRef, () => {
      if (roleRef.value === RoleEnum.USER) {
        handleQuery();
      } else {
        queryStatus.value = false;
        // table.data = undefined;
      }
    });

    onActivated(() => {
      if (isReload.value) {
        handleQuery();
      }
      // iTableComponent內的currentPage改變就會觸發handlePaginationChanged事件
      // iTableComponent.value.state.pagination.currentPage = criteria.value.currentPage + 1;
    });

    const formOptions = reactive({
      subject: [],
      sex:[],
    });

    const formDefault = {
      name: '',
      idno: '',
      importDate: null,
      subject: '',
      inputFile: [],
    };
    const form = reactive(Object.assign({}, formDefault, new Pagination()));
    const rules = {
      name: {},
      idno: {},
      importDate: {},
      subject: {},
      inputFile: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      step2: false,
    });

    const table = reactive({
      fields: [
        {
          //年度
          key: 'importDate',
          label: i18n.t('label.iYear'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => formatToString(value, '', '-'),
        },
        {
          //類科名稱
          key: 'subject',
          label: i18n.t('label.subject'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.subject, value),
        },
        {
          // 姓名
          key: 'name',
          label: i18n.t('label.name'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //性別
          key: 'sex',
          label: i18n.t('label.sex'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => formOptionsFormatter(formOptions.sex, value),
        },
        {
          //出生日期
          key: 'birthday',
          label: i18n.t('label.birthDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 身分證字號
          key: 'idno',
          label: i18n.t('label.idno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          //地址
          key: 'contactAddr',
          label: i18n.t('label.contactAddrOther'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //動作
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const handleQueryBtn = () => {
      // iTableComponent.value.state.pagination.currentPage = 1;
      // if (!form.currentPage) iTableComponent.value.emitPagination();
      handleQuery();
    };

    function submitForm(isCheckReq: boolean) {
        $v.value.$reset();
        this.$nextTick(() => {
          $bvModal.msgBoxConfirm('請再次確認資料是否刪除？').then((isOK: boolean) => {
            if (isOK) {
              $bvModal.msgBoxOk('儲存成功。');
            }
        })})
      }

    const headers = {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    };

    const putSaveForm = () => {
      //加入判斷 判斷是info或down
      let url = '/service/excel-upload';
      const formData = new FormData();
      // const tempForm = Object.assign({}, form);
      // delete tempForm.inputFile;
      Array.from(form.inputFile).forEach(file => {
        formData.append('uploadFile', file);
      });
      
      $bvModal.msgBoxConfirm('是否確認匯入？').then((isOK: boolean) => {
          if (isOK) {
            axios
            .put(url, formData, headers)
            .then(() => {
              // notificationService.success(`上傳成功`);
            })
            .finally(() => {})
            .catch(notificationErrorHandler(notificationService));
            $bvModal.msgBoxOk('上傳成功!');
          }
      })
      9
      // axios
      //   .put(url, formData, headers)
      //   .then(() => {
      //     // notificationService.success(`上傳成功`);
      //   })
      //   .finally(() => {})
      //   .catch(notificationErrorHandler(notificationService));
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          // table.data = [
          //   {
          //     no: '1',
          //     importDate: '109',
          //     subject: '0',
          //     name: '邱炳其',
          //     sex: '0',
          //     birthDate: '1955/09/09',
          //     idno: 'E123456789',
          //     contactAddrOther: '高雄市前鎮區',
          //   },
          // ];
          // table.totalItems=1;
          
          
          axios
            // .post('/cms-comps/criteria', form)           //前端分頁
            .post('/eng-engr-exams/criteria-jpa', form) //後端分頁
            .then(({ data }) => {
              // table.data = data.slice(0, data.length);   //前端分頁
              // table.totalItems = data.length;            //前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
              
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const toEditView = (entity: IEngEngrExam, formStatus: FormStatusEnum, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['index'] = index;
      criteria['totalItems'] = table.totalItems;

      navigateByNameAndParams('Eng0107EditInfo', {
        EngEngrExam: entity,
        formStatus: formStatus,
        criteria: criteria,
      });
    };

    const toEditbook = (entity: IEngEngrExam, formStatus: FormStatusEnum, index?: number) => {
      const criteria = Object.assign({}, form);
      criteria['index'] = index;
      criteria['totalItems'] = table.totalItems;

      navigateByNameAndParams('Eng0101EditInfo', {
        EngEngrExam: entity,
        formStatus: formStatus,
        criteria: criteria,
      });
    };

    function appendixSelected(files: File[]) {}

    return {
      iTableComponent,
      formOptions,
      $v,
      stepVisible,
      table,
      reset,
      handleQueryBtn,
      toEditView,
      toEditbook,
      FormStatusEnum,
      EngEngrExam,
      formOptionsFormatter,
      handlePaginationChanged,
      appendixSelected,
      submitForm,
      RoleEnum,
      roleRef,
      queryStatus,
      putSaveForm,
      EngEngrCertificate,
    };
  },
});
</script>
<style>
</style>

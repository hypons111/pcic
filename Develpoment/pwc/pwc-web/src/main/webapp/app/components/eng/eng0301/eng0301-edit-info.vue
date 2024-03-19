<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            案件明細資料
          </h5>
        </div>
        <div class="card-body">
          <b-form-row>
            <!-- 案件編號 -->
            <i-form-group-check labelStar :label="$t('label.caseNum') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.caseNo }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.caseNo"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 案件類型 -->
            <i-form-group-check :label="$t('label.caseType') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.caseType, data.caseType) }}
              </div>
              <b-form-select v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.caseType" :options="formOptions.caseType">
                <template #first>
                  <option value=""></option>
                </template>
              </b-form-select>
              <b-form-select v-if="formStatusRef === FormStatusEnum.CREATE">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
            <!-- 案件名稱 -->
            <i-form-group-check labelStar :label="$t('label.caseNa') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.caseName }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.caseName"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 招標(自辦)機關(代碼及名稱) -->
            <i-form-group-check labelStar :label="$t('label.orgIdName') + '：'">
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.orgId }}</div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.orgId" trim lazy></b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
              <div v-if="formStatus === FormStatusEnum.READONLY">{{ data.orgName }}</div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.orgName" trim lazy></b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
            <!-- 執行機關 -->
            <i-form-group-check labelStar :label="$t('label.wkut') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.execOrgId }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.execOrgId"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>

              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.execOrgName }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.execOrgName"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 聯絡人 -->
            <i-form-group-check :label="$t('label.contactPerson') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.contactPerson }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.contactPerson"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 聯絡人電話 -->
          <b-form-row>
            <i-form-group-check :label="$t('label.contactTel') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.contactTel }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.contactTel" maxlength="50"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE" maxlength="50"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <!-- 服務項目及內容摘要 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.entrustitem') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.entrustItem }}
              </div>
              <b-form-textarea rows="4" maxlength="80" trim lazy v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.entrustItem">
              </b-form-textarea>
              <b-form-textarea v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-textarea>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 決標或自辦起始日期 -->
            <i-form-group-check :label="$t('label.tenderDate') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.tenderDateStart }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.tenderDateStart"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
            <!-- 決標金額 -->
            <i-form-group-check :label="$t('label.ctSum') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.ctSum }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.ctSum"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 預定辦理日期 -->
            <i-form-group-check labelStar :label="$t('label.execDate') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.execDateStart }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.execDateStart"> </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 工程種類 -->
            <i-form-group-check labelStar :label="$t('label.projectType') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.projectType, data.projectType) }}
              </div>
              <b-form-select v-if="formStatusRef === FormStatusEnum.EDIT" v-model="data.projectType" :options="formOptions.projectType">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
              <b-form-select v-if="formStatusRef === FormStatusEnum.CREATE" :options="formOptions.projectType">
                <template #first>
                  <option value="">請選擇</option>
                </template>
              </b-form-select>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 簽證範圍 -->
            <i-form-group-check labelStar class="col-12" label-cols="2" :label="$t('label.recordRangeList') + '：'" :item="$v.recordRanges">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.recordRangeList, data.recordRangeList) }}
              </div>
              <b-form-checkbox-group
                v-if="formStatusRef === FormStatusEnum.EDIT"
                v-model="data.recordRanges"
                :options="formOptions.recordRangeList"
              ></b-form-checkbox-group>
              <b-form-checkbox-group
                v-if="formStatusRef === FormStatusEnum.CREATE"
                :options="formOptions.recordRangeList"
              ></b-form-checkbox-group>
              <b-form-input class="col-4" v-if="formStatusRef !== FormStatusEnum.READONLY" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 簽證項目 -->
            <i-form-group-check labelStar class="col-12" label-cols="2" :label="$t('label.recordItems') + '：'" :item="$v.recordItemsList">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.recordItemsList, data.recordItemsList) }}
              </div>
              <b-form-checkbox-group
                v-if="formStatusRef === FormStatusEnum.EDIT"
                v-model="data.recordItems"
                :options="formOptions.recordItemsList"
              ></b-form-checkbox-group>
              <b-form-checkbox-group
                v-if="formStatusRef === FormStatusEnum.CREATE"
                :options="formOptions.recordItemsList"
              ></b-form-checkbox-group>
              <b-form-input class="col-4" v-if="formStatusRef !== FormStatusEnum.READONLY" trim></b-form-input>
            </i-form-group-check>
          </b-form-row>
          <b-form-row>
            <!-- 備註 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="6" :label="$t('label.remark') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{}}
              </div>
              <b-form-textarea rows="4" maxlength="80" trim lazy v-if="formStatusRef === FormStatusEnum.EDIT"> </b-form-textarea>
              <b-form-textarea v-if="formStatusRef === FormStatusEnum.CREATE"> </b-form-textarea>
            </i-form-group-check>
          </b-form-row>
        </div>
      </div>
    </section>
    <!-- 得標機構 -->
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          得標機構
        </h5>
      </div>
      <div class="card-body" v-if="formStatusRef !== FormStatusEnum.READONLY">
        <Eng0301EditComp :isEditComp="true" :comps="comps" :key="form.engCaseId" @changeCompData="changeCompData"> </Eng0301EditComp>
      </div>
      <div class="card-body" v-else>
        <Eng0301EditComp :isEditComp="false" :comps="comps" :key="form.engCaseId" @changeCompData="changeCompData"> </Eng0301EditComp>
      </div>
    </div>
    <br />
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            承辦技師
          </h5>
        </div>
        <div class="card-body">
          <Eng0301EditEngr :formStatus="formStatusRef" :isEditEngr="true" :key="form.engCaseId" :engrs="engrs">
            <!-- @changeEngrData="changeEngrData" -->
          </Eng0301EditEngr>
        </div>
      </div>
    </section>
    <b-form-row>
      <b-col offset="5">
        <!-- 返回 -->
        <i-button type="arrow-left" @click="toLast" />
        <!-- 確認送出 -->
        <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" type="send-check" @click="submitForm(true)" />
      </b-col>
    </b-form-row>
    <br />
    <b-form-row v-if="formStatusRef !== FormStatusEnum.CREATE">
      <b-col offset="5">
        <!-- 上一筆 -->
        <i-button type="arrow-bar-left" />
        <!-- 下一筆 -->
        <i-button type="arrow-bar-right" />
      </b-col>
    </b-form-row>
  </div>
</template>

<script lang="ts">
import { watch, toRef, toRefs, ref, unref, defineComponent, inject, onMounted, reactive } from '@vue/composition-api';
import { Eng0703, IEng0703 } from '@/shared/model/eng/eng0703.model';
import { FormStatusEnum, RoleEnum } from '@/shared/enum';
import { string } from 'prop-types';
import { handleBack } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { formOptionsFormatter } from '@/shared/formatter/common';
import axios, { AxiosResponse } from 'axios';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';
import { EngCaseTotalDTO } from '@/shared/model/eng/eng-case.model';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import i18n from '@/shared/i18n';
import Eng0301EditComp from '@/components/eng/eng0301/eng0301-edit-comp.vue';
import Eng0301EditEngr from '@/components/eng/eng0301/eng0301-edit-engr.vue';

interface propsType {
  eng0703: IEng0703;
  formStatus: FormStatusEnum;
  role: RoleEnum;
  criteria: any;
}
export default defineComponent<propsType>({
  name: 'Eng0301EditInfo',
  components: { Eng0301EditComp, Eng0301EditEngr },
  props: {
    id: {
      required: false,
    },
    formStatus: {
      type: String,
      required: false,
    },
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {
    //放得標機關的data用的
    let comps = ref([]);
    let engrs = ref([]);
    let formDefault = new EngCaseTotalDTO();
    const newFormDefault = new EngCaseTotalDTO();
    const isReload = ref(false); // 是否需要重新查詢
    const formStatusRef = ref(props.formStatus);
    const $bvModal = useBvModal();
    const admSysCodeService = new AdmSysCodeService();
    const form = reactive(Object.assign({}, formDefault));

    // 驗證
    const rules = {
      caseType: {},
      recordItemsList: {},
      recordItems: {},
      recordRanges: {},
      recordRangeList: {},
      projectType: {},
      caseNo: {},
      caseName: {},
      execOrgName: {},
      execOrgId: {},
      execDateStart: {},
      execDateEnd: {},
      tenderDateStart: {},
      tenderDateEnd: {},
      compName: {},
      compIdno: {},
      engrName: {},
      engrId: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const formOptions = reactive({
      recordItemsList: [], // 簽證項目
      recordRangeList: [], // 簽證範圍
      caseType: [], // 案件類型
      projectType: [], // 工程種類

      compName: [], // 所屬廠商
      engrCertificateList: [], // 證書
    });

    onMounted(() => {
      getAllSysCodes();
    });
    // 目前專注跳確認視窗
    function submitForm(isCheckReq: boolean) {
      $v.value.$reset();
      this.$nextTick(() => {
        $bvModal.msgBoxConfirm('是否確認送出？').then((isOK: boolean) => {
          if (isOK) {
            $bvModal.msgBoxOk('儲存成功!');
          }
        });
      });
    }

    function toLast() {
      navigateByNameAndParams('Eng0301Query', {
        isReload: isReload.value,
        isNotKeepAlive: true,
        formStatus: formStatusRef,
      });
      isReload.value = false;
    }

    // 初始化得標機構 和 技師資料
    function initCompAndEngr() {
      comps.value = [];
      engrs.value = [];

      let c1 = {
        // engCaseId: '1',
        // no: '1',
        agentName: '建業工程顧問有限公司',
        compIdno: '86151049',
        caslicenseNoeNo: '工程技顧登字第000201號',
        execDateStart: '106/03/17',
        execDateEnd: '108/11/14',
      };
      comps.value.push(c1);

      let e1 = {
        compName: '建業工程顧問有限公司',
        compNames: ['0'],
        idno: 'F125069458',
        name: '王茲為',
        execDate: '106/10/26',
        engrCertificate: [''],
        engrLicenseDocNo: '技執字第007123號',
        recordRangeList: '5',
        recordRanges: ['5'],
        recordItems: ['1'],
        recordItemsList: '1',
        docNo: '',
        closeDate: '108/11/14',
        signStartDate: '106/01/26',
        remark: '',
        engrCertificateList: '0',
        engrCertificates: ['0'],
      };
      engrs.value.push(e1);
    }

    function clearDataFromKeepAlive() {
      formDefault = Object.assign(formDefault, newFormDefault);
      formDefault.engCaseId = generateRandomID();
      Object.keys(form).forEach(key => {
        form[key] = formDefault[key];
      });
      comps.value = [];
      engrs.value = [new EngCaseTotalDTO()];
    }

    function generateRandomID() {
      var S4 = function () {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
      };
      return S4() + S4() + '-' + S4() + '-' + S4() + '-' + S4() + '-' + S4() + S4() + S4();
    }

    // 前往新增得標機構
    //在改變子組件的時候透過自定義方法更改parent的資料
    function changeCompData(data: any, index: number, flag: string) {
      switch (flag) {
        case 'edit':
          comps.value.splice(index, 1, data);
          break;
        case 'add':
          comps.value.push(data);
          break;
        case 'delete':
          comps.value.splice(index, 1);
          break;
        default:
          break;
      }
    }

    watch(
      formStatusRef,
      () => {
        switch (formStatusRef.value) {
          case FormStatusEnum.READONLY:
            initCompAndEngr();
            break;
          case FormStatusEnum.EDIT:
            initCompAndEngr();
            break;
          case FormStatusEnum.CREATE:
            clearDataFromKeepAlive();
            break;
        }
      },
      { immediate: true }
    );

    const removeEngr = () => {
      $bvModal.msgBoxConfirm('是否刪除承辦技師？').then((isOK: boolean) => {
        if (isOK) {
          $bvModal.msgBoxConfirm('刪除成功!').then((isOK: boolean) => {});
        }
      });
    };

    function getAllSysCodes() {
      formOptions.caseType = [
        { value: '0', text: 'BOT案件' },
        { value: '1', text: '機關自辦' },
        { value: '2', text: '機關委辦' },
      ];
      formOptions.recordItemsList = [
        { value: '0', text: '設計' },
        { value: '1', text: '監造' },
        { value: '2', text: '施工' },
        { value: '99', text: '其他' },
      ];
      formOptions.recordRangeList = [
        { value: '0', text: '航空測量' },
        { value: '1', text: '工址調查' },
        { value: '2', text: '定線工程' },
        { value: '3', text: '機電工程' },
        { value: '4', text: '照明工程' },
        { value: '5', text: '道路工程、橋樑工程及隧道工程' },
        { value: '6', text: '水理分析、排水工程' },
        { value: '7', text: '交通工程' },
        { value: '99', text: '其它' },
      ];
      formOptions.compName = [{ value: '0', text: '建業工程顧問有限公司' }];
      formOptions.engrCertificateList = [
        { value: '0', text: '土木工程' },
        { value: '1', text: '結構工程' },
      ];
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const data = {
      caseNo: 'work1050912-1',
      caseName: '成功橋拓寬工程',
      caseType: '0',
      recordItemsList: '1',
      recordItems: ['1'],
      projectType: '01',
      execOrgName: '臺北市政府工務局新建工程處',
      orgId: '3.79.11.1',
      orgName: '臺北市政府工務局新建工程處',
      execOrgId: '3.79.11.1',
      execDateStart: '106/10/26',
      execDateEnd: '108/11/14',
      tenderDateStart: '106/01/26',
      tenderDateEnd: null,
      compName: '建業工程顧問有限公司',
      compIdno: '',
      engrId: '',
      recordRanges: ['5'],
      recordRangeList: '5',
      recordRangeOther: '',
      contactPerson: '陳濬為',
      contactTel: '02-23456789',
      ctSum: '173,965,946',
      entrustItem: '',
    };

    return {
      formOptions,
      $v,
      // table,
      data,
      toLast,
      submitForm,
      FormStatusEnum,
      formStatusRef,
      EngCaseTotalDTO,
      formOptionsFormatter,
      changeCompData,
      // toCreateEngr,
      removeEngr,
      form,
      comps,
      engrs,
      // changeEngrData
    };
  },
});
</script>
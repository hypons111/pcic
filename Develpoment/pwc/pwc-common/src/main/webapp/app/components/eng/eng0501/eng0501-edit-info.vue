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
          <!-- 主要工程經歷 -->
          <b-form-row>
            <i-form-group-check class="col-12" label-cols="2" content-cols="6" 
            :label="$t('label.mainprojectexpe') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
              {{ data.mainprojectexpe }}
              </div>
              <b-form-textarea rows="4" maxlength="80" trim lazy 
              v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="data.mainprojectexpe">
              </b-form-textarea>
              <b-form-textarea v-if="formStatusRef === FormStatusEnum.CREATE">
              </b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 震災支援縣市 -->
            <i-form-group-check labelStar
            class="col-12" label-cols="2"
            :label="$t('label.supportcitylist') + '：'"
            :item="$v.supportcity">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.supportcitylist,
                  data.supportcitylist) }}
              </div>
              <b-form-checkbox-group
              v-if="formStatusRef === FormStatusEnum.EDIT"
                v-model="data.supportcity"
                :options="formOptions.supportcitylist"
              ></b-form-checkbox-group>
              <b-form-checkbox-group
              v-if="formStatusRef === FormStatusEnum.CREATE"
                
                :options="formOptions.supportcitylist"
              ></b-form-checkbox-group>
              <b-form-input class="col-4"
              v-if="formStatusRef !== FormStatusEnum.READONLY"
              trim></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 救災總隊 -->
            <i-form-group-check :label="$t('label.helpteam') + '：'"
            >
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ data.helpteam }}
              </div>
              <b-form-input v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="data.helpteam">
              </b-form-input>
              <b-form-input v-if="formStatusRef === FormStatusEnum.CREATE">
              </b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 專長 -->
            <i-form-group-check labelStar
            class="col-12" label-cols="2"
            :label="$t('label.skilllistskillother') + '：'"
            :item="$v.skill">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.skilllistskillother, 
                data.skilllistskillother) }}
              </div>
              <b-form-checkbox-group
              v-if="formStatusRef === FormStatusEnum.EDIT"
                v-model="data.skill"
                :options="formOptions.skilllistskillother"
              ></b-form-checkbox-group>
              <b-form-checkbox-group
              v-if="formStatusRef === FormStatusEnum.CREATE"
                
                :options="formOptions.skilllistskillother"
              ></b-form-checkbox-group>
              <b-form-input class="col-4"
              v-if="formStatusRef !== FormStatusEnum.READONLY"
              trim></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 查核委員 -->
            <i-form-group-check labelStar
            class="col-12" label-cols="2"
            :label="$t('label.checkmemberlistcheckmemberother') + '：'"
            :item="$v.checkmember">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.checkmemberlistcheckmemberother, 
                data.checkmemberlistcheckmemberother) }}
              </div>
              <b-form-checkbox-group
              v-if="formStatusRef === FormStatusEnum.EDIT"
                v-model="data.checkmember"
                :options="formOptions.checkmemberlistcheckmemberother"
              ></b-form-checkbox-group>
              <b-form-checkbox-group
              v-if="formStatusRef === FormStatusEnum.CREATE"
                
                :options="formOptions.checkmemberlistcheckmemberother"
              ></b-form-checkbox-group>
              <b-form-input class="col-4"
              v-if="formStatusRef !== FormStatusEnum.READONLY"
              trim></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 專家/學者 -->
            <i-form-group-check labelStar
            class="col-12" label-cols="2"
            :label="$t('label.expertlistexpertother') + '：'"
            :item="$v.expert">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
                {{ formOptionsFormatter(formOptions.expertlistexpertother, 
                data.expertlistexpertother) }}
              </div>
              <b-form-checkbox-group
              v-if="formStatusRef === FormStatusEnum.EDIT"
                v-model="data.expert"
                :options="formOptions.expertlistexpertother"
              ></b-form-checkbox-group>
              <b-form-checkbox-group
              v-if="formStatusRef === FormStatusEnum.CREATE"
                
                :options="formOptions.expertlistexpertother"
              ></b-form-checkbox-group>
              <b-form-input class="col-4"
              v-if="formStatusRef !== FormStatusEnum.READONLY"
              trim></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 重要論文 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="6" 
            :label="$t('label.thesis') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
              {{ data.thesis }}
              </div>
              <b-form-textarea rows="4" maxlength="80" trim lazy 
              v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="data.thesis">
              </b-form-textarea>
              <b-form-textarea v-if="formStatusRef === FormStatusEnum.CREATE">
              </b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 重要著作 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="6" 
            :label="$t('label.book') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
              {{ data.book }}
              </div>
              <b-form-textarea rows="4" maxlength="80" trim lazy 
              v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="data.book">
              </b-form-textarea>
              <b-form-textarea v-if="formStatusRef === FormStatusEnum.CREATE">
              </b-form-textarea>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 獎項 -->
            <i-form-group-check class="col-12" label-cols="2" content-cols="6" 
            :label="$t('label.awards') + '：'">
              <div v-if="formStatusRef === FormStatusEnum.READONLY">
              {{ data.awards }}
              </div>
              <b-form-textarea rows="4" maxlength="80" trim lazy 
              v-if="formStatusRef === FormStatusEnum.EDIT"
              v-model="data.awards">
              </b-form-textarea>
              <b-form-textarea v-if="formStatusRef === FormStatusEnum.CREATE">
              </b-form-textarea>
            </i-form-group-check>
          </b-form-row>

        </div>
      </div>
    </section>
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
        </h5>
      </div>
      <div class="card-body" v-if="formStatusRef !== FormStatusEnum.READONLY">
        <Eng0501EditComp :isEditComp="true" :comps="comps" 
        @changeCompData="changeCompData"> </Eng0501EditComp>
      </div>
      <div class="card-body" v-else>
        <Eng0501EditComp :isEditComp="false" :comps="comps" 
        @changeCompData="changeCompData"> </Eng0501EditComp>
      </div>
    </div>
    <div class="text-center mb-2">
      <!-- 編輯 -->
      <i-button v-if="formStatusRef == FormStatusEnum.READONLY"
      type="pencil-square" @click="changeFormStatus(FormStatusEnum.EDIT)"
      ></i-button>
      <!-- 儲存 -->
      <i-button v-if="formStatusRef !== FormStatusEnum.READONLY" 
      type="save" @click="submitForm(true)" />
      <!-- 返回 -->
      <i-button type="arrow-left" @click="toQueryView" />
    </div>
  </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
// import EngEngrSubjectpointService from './eng-engr-subjectpoint.service';
import { useBvModal } from '@/shared/modal';
import { useNotification } from '@/shared/notification';
import { useValidation, validateState } from '@/shared/form';
import { handleBack } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { formatToString, parseToDate } from '@/shared/date/minguo-calendar-utils';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { watch, reactive, ref, toRefs, onMounted, defineComponent, unref } from '@vue/composition-api';
import { required, maxLength, nationalId, between, queryEngrByIdno } from '@/shared/validators';
import { ApplyItemEnum, FormStatusEnum, MemberTypeEnum, StatusEnum } from '@/shared/enum';
import { IEngEngrSubjectpoint, EngEngrSubjectpoint } from '@/shared/model/eng/eng-engr-subjectpoint.model';
import { useStore } from '@u3u/vue-hooks';
import { EngCaseTotalDTO } from '@/shared/model/eng/eng-case.model';
import ISwitchCase from '@/shared/i-switch-case/i-switch-case.vue';
import { navigateByNameAndParams } from '@/router/router';
import { IEngEngrCertificate } from '@/shared/model/eng/eng-engr-certificate.model';
import Eng0501EditComp from '@/components/eng/eng0501/eng0501-edit-comp.vue';

interface propsType {
  engEngrSubjectpoint: IEngEngrSubjectpoint;
  formStatus: FormStatusEnum;
  criteria: any;
}

export default defineComponent<propsType>({
  name: 'Eng0501EditInfo',
  components: { ISwitchCase, Eng0501EditComp },
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
  },
  
  setup(props) {
    let comps = ref([]);
    const admSysCodeService = new AdmSysCodeService();
    //const engEngrSubjectpointService = new EngEngrSubjectpointService();
    const newFormDefault = new EngCaseTotalDTO();
    const store = useStore();
    const $bvModal = useBvModal();
    const notificationService = useNotification();

    const { engEngrSubjectpoint, formStatus, criteria } = toRefs(props);

    const isReload = ref(false);
    const formStatusRef = ref(props.formStatus);

    const formOptions = reactive({
      supportcitylist: [],
      skilllistskillother: [],
      checkmemberlistcheckmemberother: [],
      expertlistexpertother: [],
    });

    let formDefault = new EngEngrSubjectpoint();
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      mainprojectexpe: {},
      supportcity: {},
      supportcitylist: {},
      helpteam: {},
      skill: {},
      skilllistskillother: {},
      checkmember: {},
      checkmemberlistcheckmemberother: {},
      expert: {},
      expertlistexpertother: {},
      thesis: {},
      book: {},
      awards: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    onMounted(() => {
      getAllSysCodes();
    });

    function changeFormStatus(formStatus: FormStatusEnum) {
      formStatusRef.value = formStatus;
      // reset();
    }

    watch(
      formStatusRef,
      () => {
        switch(formStatusRef.value) {
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

    function toLast() {
      navigateByNameAndParams('Eng0501Query', {
        isReload: isReload.value, isNotKeepAlive: true,
        formStatus: formStatusRef
      } );
      isReload.value = false;
    }

    function getAllSysCodes(){
      formOptions.supportcitylist = [
          { value: '0', text: '台北市' },
          { value: '1', text: '新北市' },
          { value: '2', text: '基隆市' },
          { value: '3', text: '宜蘭縣' },
          { value: '4', text: '新竹市' },
          { value: '5', text: '新竹縣' },
          { value: '6', text: '桃園市' },
          { value: '7', text: '苗栗縣' },
          { value: '8', text: '台中市' },
          { value: '9', text: '彰化縣' },
          { value: '10', text: '南投縣' },
          { value: '11', text: '嘉義市' },
          { value: '12', text: '嘉義縣' },
          { value: '13', text: '雲林縣' },
          { value: '14', text: '台南市' },
          { value: '15', text: '高雄市' },
          { value: '16', text: '屏東縣' },
          { value: '17', text: '台東縣' },
          { value: '18', text: '花蓮縣' },
          { value: '19', text: '澎湖縣' },
          { value: '20', text: '金門縣' },
          { value: '21', text: '連江縣' }
        ];
      formOptions.skilllistskillother = [
          { value: '0', text: '橋樑工程' },
          { value: '1', text: '道路/鐵路工程' },
          { value: '2', text: '隧道工程' },
          { value: '3', text: '港灣/提岸工程' },
          { value: '4', text: '山坡地開發' },
          { value: '5', text: '機場工程' },
          { value: '6', text: '建築物結構工程' },
          { value: '7', text: '鋼構工程' },
          { value: '8', text: '水土保持' },
          { value: '9', text: '其他' }
        ];
      formOptions.checkmemberlistcheckmemberother = [
          { value: '0', text: '工程會' },
          { value: '1', text: '其他' }
        ];
      formOptions.expertlistexpertother = [
          { value: '0', text: '工程會' },
          { value: '1', text: '財政部' },
          { value: '2', text: '其他' }
        ];
    }

    function clearDataFromKeepAlive() {
      formDefault = Object.assign(formDefault, newFormDefault);
      // formDefault.engCaseId = generateRandomID();
      Object.keys(form).forEach(key => {
        form[key] = formDefault[key];
      });
      comps.value = [];
    }

    function generateRandomID() {
      var S4 = function () {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
      };
      return S4() + S4() + '-' + S4() + '-' + S4() + '-' + S4() + '-' + S4() + S4() + S4();
    }

    // 初始化得標機構 和 技師資料
    function initCompAndEngr() {
      comps.value = [];

      let c1 = {
        projectExpe: '國10燕巢',
        DateStart: '106/03/17',
        DateEnd: '108/11/14',
        EngiSen: '',
      };
      comps.value.push(c1);
    }

    function submitForm() {
      $bvModal.msgBoxConfirm('請再次確認資料是否填寫正確？').then((isOK: boolean) => {
        if (isOK) {
          checkValidity().then((isValid: boolean) => {
            if (isValid) {
              //putForm();
            } else {
              $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
            }
          });
        }
      });
    }

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

    function toQueryView() {
      handleBack({ isReload: unref(isReload), criteria: unref(criteria), isNotKeepAlive: true });
      isReload.value = false;
    }

    const data = {
        mainprojectexpe: '美商美聯',
        supportcity: ['1'],
        supportcitylist: '1',
        helpteam: '',
        skill: ['1','2','4'],
        skilllistskillother: '1',
        checkmember: [],
        checkmemberlistcheckmemberother: '0',
        expert: [],
        expertlistexpertother: '0',
        thesis: 'FLAC程式',
        book: '山岳隧道',
        awards: '1.優良隧道2.海岸大學',
    }

    return {
      FormStatusEnum,
      formStatusRef,
      $v,
      toLast,
      changeFormStatus,
      validateState,
      toQueryView,
      submitForm,
      formOptions,
      formOptionsFormatter,
      changeCompData,
      comps,
      data,
    };
  },
  filters: {
    dateToString: (value: Date) => formatToString(value, '/'),
  },
});
</script>
<style></style>

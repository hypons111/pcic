<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <!-- 品質缺失罰性違約金機制 -->
        <b-form-row>
          <b-col cols="1" class="text-right label pr-1">
            <div class="pt-2"></div>
          </b-col>
          <b-col cols="11">
            <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="6" :label="'品質缺失罰性違約金機制：'">
              <div class="d-flex h-38p align-items-center">
                <b-form-radio-group v-model="$v.isCkp.$model">
                  <b-form-radio value="Y">招標文件中已明訂施工單位相關規定</b-form-radio>
                  <b-form-radio value="N">施工單位尚未納人</b-form-radio>
                </b-form-radio-group>
              </div>
              <div class="d-flex h-38p align-items-center">
                <b-form-radio-group v-model="$v.isCkp2.$model">
                  <b-form-radio value="Y">招標文件中已明訂營造單位相關規定</b-form-radio>
                  <b-form-radio value="N">營造單位尚未納人</b-form-radio>
                </b-form-radio-group>
              </div>
              <div class="d-flex h-38p align-items-center">
                <b-form-radio-group v-model="$v.isCkp3.$model">
                  <b-form-radio value="Y">招標文件中已明訂專案管理單位相關規定</b-form-radio>
                  <b-form-radio value="N">專案管理單位尚未納人</b-form-radio>
                </b-form-radio-group>
              </div>
            </i-form-group-check>
            <!-- 品管費用 -->
            <i-form-group-check
              class="col-sm-12"
              label-cols-md="2"
              content-cols-md="10"
              :label="'品管費用：'"
              :item="$v.ckpMoney"
              :labelStar="true"
            >
              <div class="d-inline">
                <b-form-input class="col-12" v-model="$v.ckpMoney.$model" :state="validateState($v.ckpMoney)"></b-form-input>
              </div>
              <div class="col-8">
                <div class="d-inline">千元</div>
              </div>
            </i-form-group-check>
          </b-col>
        </b-form-row>
        <!-- 品質核定機關(單位) -->
        <b-form-row>
          <b-col cols="1" class="text-right label pr-1">
            <div class="pt-2">品質計畫</div>
          </b-col>

          <b-col cols="11">
            <i-form-group-check content-cols-md="6" :label="'核定機關(單位)：'" :item="$v.ckpUnit">
              <b-form-input v-model="$v.ckpUnit.$model" :state="validateState($v.ckpUnit)"></b-form-input>
            </i-form-group-check>
            <!-- 核定日期 -->
            <i-form-group-check content-cols-md="5" :labelStar="true" :label="'核定日期：'">
              <b-input-group>
                <i-date-picker v-model="$v.ckpDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
              </b-input-group>
            </i-form-group-check>
            <!-- 核定文號 -->
            <i-form-group-check content-cols-md="6" :label="'核定文號：'" :item="$v.ckpNum">
              <b-form-input v-model="$v.ckpNum.$model" :state="validateState($v.ckpNum)"></b-form-input>
            </i-form-group-check>
            <!-- 檔案上傳-->
            <!-- 檔案上傳-->
            <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :labelStar="true" :label="'檔案上傳：'" :item="$v.ckpFile">
              <b-form-file
                v-model="$v.ckpFile.$model"
                :state="Boolean($v.ckpFile.$model)"
                browse-text="瀏覽..."
                :placeholder="$v.ckpFile.$model ? $v.ckpFile.$model.name : ''"
              >
              </b-form-file>
              <div v-if="$v.ckpFile.$model" class="mt-3">已選擇檔案: {{ $v.ckpFile.$model ? $v.ckpFile.$model.name : '' }}</div>
            </i-form-group-check>
          </b-col>
        </b-form-row>
        <!-- 監造核定機關(單位) -->
        <b-form-row>
          <b-col cols="1" class="text-right label pr-1">
            <div class="pt-2">監造計畫</div>
          </b-col>

          <b-col cols="11">
            <i-form-group-check content-cols-md="6" :label="'核定機關(單位)：'" :item="$v.ckaUnit">
              <b-form-input v-model="$v.ckaUnit.$model" :state="validateState($v.ckaUnit)"></b-form-input>
            </i-form-group-check>
            <!-- 核定日期 -->
            <i-form-group-check content-cols-md="5" :labelStar="true" :label="'核定日期：'">
              <b-input-group>
                <i-date-picker v-model="$v.ckaDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
              </b-input-group>
            </i-form-group-check>
            <!-- 核定文號 -->
            <i-form-group-check content-cols-md="6" :label="'核定文號：'" :item="$v.ckaNum">
              <b-form-input v-model="$v.ckaNum.$model" :state="validateState($v.ckaNum)"></b-form-input>
            </i-form-group-check>
            <!-- 檔案上傳-->
            <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :labelStar="true" :label="'檔案上傳：'" :item="$v.ckaFile">
              <b-form-file
                v-model="$v.ckaFile.$model"
                :state="Boolean($v.ckaFile.$model)"
                browse-text="瀏覽..."
                :placeholder="$v.ckaFile.$model ? $v.ckaFile.$model.name : ''"
              >
              </b-form-file>
              <div v-if="$v.ckaFile.$model" class="mt-3">已選擇檔案: {{ $v.ckaFile.$model ? $v.ckaFile.$model.name : '' }}</div>
            </i-form-group-check>
          </b-col>
        </b-form-row>
        <!-- 施工核定機關(單位) -->
        <b-form-row>
          <b-col cols="1" class="text-right label pr-1">
            <div class="pt-2">施工計畫</div>
          </b-col>
          <b-col cols="11">
            <i-form-group-check content-cols-md="6" :label="'核定機關(單位)：'" :item="$v.ckdUnit">
              <b-form-input v-model="$v.ckdUnit.$model" :state="validateState($v.ckdUnit)"></b-form-input>
            </i-form-group-check>
            <!-- 核定日期 -->
            <i-form-group-check content-cols-md="5" :labelStar="true" :label="'核定日期：'">
              <b-input-group>
                <i-date-picker v-model="$v.ckdDate.$model" placeholder="yyy/MM/dd"></i-date-picker>
              </b-input-group>
            </i-form-group-check>
            <!-- 核定文號 -->
            <i-form-group-check content-cols-md="6" :label="'核定文號：'" :item="$v.ckdNum">
              <b-form-input v-model="$v.ckdNum.$model" :state="validateState($v.ckdNum)"></b-form-input>
            </i-form-group-check>
            <!-- 檔案上傳-->
            <i-form-group-check class="col-sm-12" label-cols-md="2" content-cols-md="10" :labelStar="true" :label="'檔案上傳：'" :item="$v.ckdFile">
              <b-form-file
                v-model="$v.ckdFile.$model"
                :state="Boolean($v.ckdFile.$model)"
                browse-text="瀏覽..."
                :placeholder="$v.ckdFile.$model ? $v.ckdFile.$model.name : ''"
              >
              </b-form-file>
              <div v-if="$v.ckdFile.$model" class="mt-3">已選擇檔案: {{ $v.ckdFile.$model ? $v.ckdFile.$model.name : '' }}</div>
            </i-form-group-check>
          </b-col>
        </b-form-row>
      </div>
    </div>
    <!-- 錯誤訊息 -->
    <b-modal id="valid-error-modal" size="lg" title="資料驗證有誤" :header-text-variant="'light'" :header-bg-variant="'danger'" hide-footer>
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModals.hide('valid-error-modal')">了解</b-button>
    </b-modal>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import BidProjectFileService from '@/components/bid/bidService/bid-project-file.service';
import { Ref, ref, toRef, reactive, watch, computed, Reactive } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';

export default {
  name: 'bidAaa002EditInfo5',
  props: {
    bidProjectInfo: {
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectInfoKeyProp: Ref<any> = toRef(props, 'bidProjectInfo');

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const notificationService: NotificationService = useNotification();
    const bidProjectFileService = new BidProjectFileService();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formDefault = {
      isCkp: '', //施工缺失懲罰機制納入合約
      isCkp2: '', //監造缺失懲罰機制納入合約
      isCkp3: '', //專案管理缺失機制納入合約
      ckpMoney: '', //品管費用
      ckpUnit: '', //品質核定機關(單位)
      ckpDate: '', //核定日期
      ckpNum: '', //核定文號
      ckpFile: null, //品質計畫
      ckpAdmFileId: undefined,
      ckaUnit: '', //監造核定機關(單位)
      ckaDate: '', //核定日期
      ckaNum: '', //核定文號
      ckaFile: null, //監造計畫
      ckaAdmFileId: undefined,
      ckdUnit: '', //施工核定機關(單位)
      ckdDate: '', //核定日期
      ckdNum: '', //核定文號
      ckdFile: null, //施工計畫
      ckdAdmFileId: undefined,
    };
    let formEmpty = Object.assign({}, formDefault);
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      isCkp: {},
      isCkp2: {},
      isCkp3: {},
      ckpMoney: { required: required },
      ckpUnit: {},
      ckpDate: { required: required },
      ckpNum: {},
      ckpFile: { required: required },
      ckpAdmFileId: {},
      ckaUnit: {},
      ckaDate: { required: required },
      ckaNum: {},
      ckaFile: { required: required },
      ckaAdmFileId: {},
      ckdUnit: {},
      ckdDate: { required: required },
      ckdNum: {},
      ckdFile: { required: required },
      ckdAdmFileId: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAaa002Query');
    }

    function putForm() {
      return form;
    }

    function getCkpFile() {
      if (!formDefault.ckpAdmFileId) return;
      axios
        .get(`/adm-files/download/${formDefault.ckpAdmFileId}`, { responseType: 'blob' })
        .then((res: AxiosResponse<Blob>) => {
          const blob = res.data;
          const content = String(res.headers['content-disposition']);
          const fileName = decodeURI(
            content
              .substring(content.lastIndexOf('filename*=') + 17)
              .replace(/"/g, '')
              .replace(/\+/g, '')
          );
          const extName = fileName.substring(fileName.lastIndexOf('.'));
          formDefault.ckpFile = new File([blob], fileName, { type: `text/${extName};charset=utf-8` });
          reset();
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function getCkaFile() {
      if (!formDefault.ckaAdmFileId) return;
      axios
        .get(`/adm-files/download/${formDefault.ckaAdmFileId}`, { responseType: 'blob' })
        .then((res: AxiosResponse<Blob>) => {
          const blob = res.data;
          const content = String(res.headers['content-disposition']);
          const fileName = decodeURI(
            content
              .substring(content.lastIndexOf('filename*=') + 17)
              .replace(/"/g, '')
              .replace(/\+/g, '')
          );
          const extName = fileName.substring(fileName.lastIndexOf('.'));
          formDefault.ckaFile = new File([blob], fileName, { type: `text/${extName};charset=utf-8` });
          reset();
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function getCkdFile() {
      if (!formDefault.ckdAdmFileId) return;
      axios
        .get(`/adm-files/download/${formDefault.ckdAdmFileId}`, { responseType: 'blob' })
        .then((res: AxiosResponse<Blob>) => {
          const blob = res.data;
          const content = String(res.headers['content-disposition']);
          const fileName = decodeURI(
            content
              .substring(content.lastIndexOf('filename*=') + 17)
              .replace(/"/g, '')
              .replace(/\+/g, '')
          );
          const extName = fileName.substring(fileName.lastIndexOf('.'));
          formDefault.ckdFile = new File([blob], fileName, { type: `text/${extName};charset=utf-8` });
          reset();
        })
        .catch(notificationErrorHandler(notificationService));
    }

    watch(() => props.bidProjectInfo, (newVal: object) => {
      Object.assign(formDefault, formEmpty)
      for(let key in formDefault){
        if(newVal[key]) formDefault[key] = newVal[key];
        if(key === 'ckpDate' || key === 'ckaDate' || key === 'ckdDate') {
          if (newVal[key] != null) {
            formDefault[key] = new Date(newVal[key]);
          }
        }
      }
      let ckpKey = { wkut: newVal.wkut, prjno: newVal.prjno, type: '1', seq: '1' };
      formDefault.ckpAdmFileId = undefined;
      formDefault.ckpFile = null;
      bidProjectFileService.findBidProjectFileById(ckpKey)
        .then(res => {
          formDefault.ckpAdmFileId = res.admFileId;
          getCkpFile();
        });
      let ckaKey = { wkut: newVal.wkut, prjno: newVal.prjno, type: '2', seq: '1' };
      formDefault.ckaAdmFileId = undefined;
      formDefault.ckaFile = null;
      bidProjectFileService.findBidProjectFileById(ckaKey)
        .then(res => {
          formDefault.ckaAdmFileId = res.admFileId;
          getCkaFile();
        });
      let ckdKey = { wkut: newVal.wkut, prjno: newVal.prjno, type: '3', seq: '1' };
      formDefault.ckdAdmFileId = undefined;
      formDefault.ckdFile = null;
      bidProjectFileService.findBidProjectFileById(ckdKey)
        .then(res => {
          formDefault.ckdAdmFileId = res.admFileId;
          getCkdFile();
        });
      reset();
    },{immediate: true, deep: true});

    return {
      $v,
      checkValidity,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      formDefault,
      stepVisible,
      putForm,
    };
  },
};
</script>

<style></style>

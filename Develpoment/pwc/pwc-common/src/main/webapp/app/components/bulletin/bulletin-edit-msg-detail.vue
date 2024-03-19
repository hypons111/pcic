<template>
  <div>
    <!-- {{ editMsgDetailProp }} -->
    <section class="container">
      <div class="card">
        <div class="card-body col-11">
          <b-form-row>
            <!--維護類型-->
            <i-form-group-check labelStar :label="$t('label.mainType') + '：'" v-if="formStatusRef !== formStatusEnum.INFO">
              <b-form-radio-group v-model="$v.type.$model" :options="formOptions.type" button-variant="outline-info" />
            </i-form-group-check>
            <i-form-group-check :label="$t('label.mainType') + '：'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ formOptionsFormatter(formOptions.type, $v.type.$model) }}</span>
            </i-form-group-check>
            <!--公告類別-->
            <i-form-group-check labelStar :label="$t('label.kind') + '：'" v-if="formStatusRef !== formStatusEnum.INFO" :item="$v.kind">
              <b-input-group>
                <b-form-select v-model="$v.kind.$model" :options="formOptions.kind" :state="validateState($v.kind)"></b-form-select>
              </b-input-group>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.kind') + '：'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ formOptionsFormatter(formOptions.kind, $v.kind.$model) }}</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!--上架日期-->
            <i-form-group-check
              labelStar
              :label="$t('label.mainReleaseDate') + '：'"
              v-if="formStatusRef !== formStatusEnum.INFO"
              :item="$v.releaseDate"
            >
              <i-date-picker v-model="$v.releaseDate.$model" :state="validateState($v.releaseDate)"></i-date-picker>
              <b-form-invalid-feedback v-for="(error, index) of $v.releaseDate.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.mainReleaseDate') + '：'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.releaseDate.$model }}</span>
            </i-form-group-check>
            <!--下架日期-->
            <i-form-group-check
              labelStar
              :label="$t('label.mainDueDate') + '：'"
              v-if="formStatusRef !== formStatusEnum.INFO"
              :item="$v.downDate"
            >
              <i-date-picker v-model="$v.downDate.$model" :state="validateState($v.downDate)"></i-date-picker>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.mainDueDate') + '：'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.downDate.$model }}</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!--是否啟用-->
            <i-form-group-check :label="$t('label.mainEnable') + '：'" v-if="formStatusRef !== formStatusEnum.INFO">
              <b-form-radio-group
                :options="formOptions.enable"
                v-model="$v.enable.$model"
                :state="validateState($v.enable)"
                class="pl-1"
              ></b-form-radio-group>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.mainEnable') + '：'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.enable.$model | yesNo }}</span>
            </i-form-group-check>
            <!--置頂-->
            <i-form-group-check
              :label="$t('label.mainTop') + '：'"
              v-if="formStatusRef !== formStatusEnum.INFO && $v.type.$model !== 'DOWNLOAD'"
            >
              <b-form-radio-group
                :options="formOptions.isTop"
                v-model="$v.isTop.$model"
                :state="validateState($v.isTop)"
                class="pl-1"
              ></b-form-radio-group>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.mainTop') + '：'" v-else v-show="$v.type.$model !== 'DOWNLOAD'">
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.isTop.$model | yesNo }}</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!--公告位置-->
            <i-form-group-check labelStar :label="$t('label.mainLocation') + '：'" v-if="formStatusRef !== formStatusEnum.INFO">
              <b-form-checkbox-group
                v-model="$v.locationListDisplay.$model"
                :options="formOptions.locationListDisplay"
              ></b-form-checkbox-group>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.mainLocation') + '：'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.location.$model | location }}</span>
            </i-form-group-check>
            <!--發布單位-->
            <i-form-group-check
              labelStar
              :label="$t('label.releaseUnitDetail') + '：'"
              v-if="formStatusRef !== formStatusEnum.INFO"
              :item="$v.releaseUnit"
            >
              <b-input-group>
                <b-form-select
                  v-model="$v.releaseUnit.$model"
                  :options="formOptions.releaseUnit"
                  :state="validateState($v.releaseUnit)"
                ></b-form-select>
              </b-input-group>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.releaseUnitDetail') + '：'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.releaseUnit.$model | pccUnit }}</span>
            </i-form-group-check>
          </b-form-row>
          <!--聯絡人-->
          <b-form-row>
            <i-form-group-check
              :label="$t('label.contactPerson') + '：'"
              v-if="formStatusRef !== formStatusEnum.INFO"
              :item="$v.contactPerson"
            >
              <b-form-input v-model="$v.contactPerson.$model" :state="validateState($v.contactPerson)"></b-form-input>
              <b-form-invalid-feedback v-for="(error, index) of $v.contactPerson.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.contactPerson') + '：'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.contactPerson.$model }}</span>
            </i-form-group-check>
            <!--email-->
            <i-form-group-check
              :label="$t('label.email') + '：'"
              v-if="formStatusRef !== formStatusEnum.INFO"
              :item="$v.contactInformationEmail"
            >
              <b-form-input
                v-model="$v.contactInformationEmail.$model"
                type="text"
                placeholder="xx@xx.xx"
                :state="validateState($v.contactInformationEmail)"
              ></b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.email') + '：'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.contactInformationEmail.$model }}</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 電話 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="5"
              v-if="formStatusRef !== formStatusEnum.INFO"
              :label="$t('label.tel') + '：'"
              :item="$v.contactInformationTel"
            >
              <i-tel v-model.trim="$v.contactInformationTel.$model" :state="validateState($v.contactInformationTel)" showExt></i-tel>
            </i-form-group-check>

            <i-form-group-check :label="$t('label.tel1') + ':'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.contactInformationTel.$model }}</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 權限 -->
            <i-form-group-check :label="$t('label.mainPermissions') + ':'" v-if="formStatusRef !== formStatusEnum.INFO">
              <b-form-radio-group
                :options="formOptions.jurisdiction"
                v-model="$v.jurisdiction.$model"
                :state="validateState($v.jurisdiction)"
              >
                <b-button
                  v-if="formStatusRef !== formStatusEnum.INFO && $v.jurisdiction.$model === '1'"
                  size="sm"
                  v-b-modal.modal-jurisdiction
                  variant="outline-secondary"
                  @click=""
                  >編輯</b-button
                >
              </b-form-radio-group>

              <b-modal id="modal-jurisdiction" size="md" title="權限">
                <b-form-row class="align-items-center mt-2">
                  <b-form-group v-if="formStatusRef !== formStatusEnum.INFO" label-cols-md="5" content-cols-md="12" label-align-md="center">
                    <dual-list-box
                      :source="jurisdictionRoles.source"
                      :destination="jurisdictionRoles.destination"
                      label="name"
                      @onChangeList="onChangeJurisdiction"
                    ></dual-list-box>
                  </b-form-group>
                </b-form-row>
              </b-modal>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.mainPermissions') + ':'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.jurisdiction.$model | jurisdiction }}</span>
            </i-form-group-check>
            <!-- 指定權限 -->
            <i-form-group-check class="col-4" content-cols="12">
              <b-row>
                <b-col class="col-6" v-for="destination of jurisdictionRoles.destination" :key="destination.code">
                  {{ destination.name }}
                </b-col>
              </b-row>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 模組 -->
            <i-form-group-check labelStar :label="$t('label.mainModule') + ':'" v-if="formStatusRef !== formStatusEnum.INFO">
              <b-form-radio-group :options="formOptions.module" v-model="$v.module.$model" :state="validateState($v.module)">
                <b-button
                  v-if="formStatusRef !== formStatusEnum.INFO && $v.module.$model === '1'"
                  size="sm"
                  v-b-modal.modal-module
                  variant="outline-secondary"
                  @click=""
                  >編輯</b-button
                >
              </b-form-radio-group>

              <b-modal id="modal-module" size="md" title="模組">
                <b-form-row class="align-items-center mt-2">
                  <b-form-group label-cols-md="5" content-cols-md="12" label-align-md="center">
                    <dual-list-box
                      :source="moduleRoles.source"
                      :destination="moduleRoles.destination"
                      label="name"
                      @onChangeList="onChangeModule"
                    ></dual-list-box>
                  </b-form-group>
                </b-form-row>
              </b-modal>
            </i-form-group-check>

            <i-form-group-check :label="$t('label.mainModule') + ':'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.module.$model | module }}</span>
            </i-form-group-check>
            <!-- 指定模組 -->
            <i-form-group-check class="col-4" content-cols="12">
              <b-row>
                <b-col class="col-6" v-for="destination of moduleRoles.destination" :key="destination.code">
                  {{ destination.name }}
                </b-col>
              </b-row>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 主旨 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="9"
              :label="$t('label.mainSubject') + ':'"
              v-if="formStatusRef !== formStatusEnum.INFO"
            >
              <b-form-input id="inputText-rows" v-model.trim="$v.subject.$model" :state="validateState($v.subject)"></b-form-input>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.mainSubject') + ':'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.subject.$model }}</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!-- 內文 -->
            <i-form-group-check
              labelStar
              class="col-12"
              label-cols="2"
              content-cols="9"
              :label="$t('label.mainText') + ':'"
              v-if="formStatusRef !== formStatusEnum.INFO"
            >
              <b-form-textarea
                id="textarea-rows"
                placeholder=""
                rows="4"
                v-model.trim="$v.text.$model"
                :state="validateState($v.text)"
              ></b-form-textarea>
            </i-form-group-check>
            <i-form-group-check :label="$t('label.mainText') + ':'" v-else>
              <span class="h-100 d-flex align-items-center flex-wrap">{{ $v.text.$model }}</span>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!--相關檔案-->
            <i-form-group-check
              class="col-3"
              label-cols="8"
              content-cols="3"
              :label="$t('label.attributesFile') + ':'"
              v-if="formStatusRef !== formStatusEnum.INFO"
            >
              <b-button size="sm" variant="outline-secondary" @click="addInputFile">新增</b-button>
            </i-form-group-check>
            <i-form-group-check class="col-9" content-cols="12" v-if="formStatusRef !== formStatusEnum.INFO">
              <b-form-row class="col-12">
                <b-col class="col-6"
                  ><b-form-input
                    v-for="(input, index) in $v.inputFileText.$model"
                    v-model="form.inputFileText[index]"
                    :key="index"
                  ></b-form-input
                ></b-col>
                <b-col class="col-4"
                  ><b-form-file
                    v-for="(input, index) in $v.inputFile.$model"
                    v-model="$v.inputFile.$model[index]"
                    :key="index"
                    placeholder="尚未上傳檔案"
                  ></b-form-file
                ></b-col>
                <b-col class="col-2">
                  <div v-for="(input, index) in $v.inputFileText.$model">
                    <b-button class="mt-2" size="sm" variant="outline-secondary" @click="deleteInputFile(index)">刪除</b-button>
                  </div>
                </b-col>
              </b-form-row>
            </i-form-group-check>
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.mainText') + ':'" v-else>
              <b-form-input
                disabled
                v-for="(inputFile, index) in $v.inputFileText.$model"
                v-model="form.inputFileText[index]"
                :key="'col1' + index"
              ></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row>
            <!--相關連結-->
            <i-form-group-check
              class="col-3"
              label-cols="8"
              content-cols="3"
              :label="$t('label.attributesLink') + ':'"
              v-if="formStatusRef !== formStatusEnum.INFO"
            >
              <b-button size="sm" variant="outline-secondary" @click="addInputUrl">新增</b-button>
            </i-form-group-check>

            <i-form-group-check class="col-9" content-cols="12" v-if="formStatusRef !== formStatusEnum.INFO">
              <b-form-row class="col-12">
                <b-col class="col-6"
                  ><b-form-input
                    v-for="(input, index) in $v.inputUrlText.$model"
                    v-model="form.inputUrlText[index]"
                    :key="index"
                  ></b-form-input
                ></b-col>
                <b-col class="col-4"
                  ><b-form-input
                    v-for="(input, index) in $v.inputUrlAdder.$model"
                    v-model="form.inputUrlAdder[index]"
                    :key="index"
                    placeholder="尚未加入相關鏈結"
                  ></b-form-input
                ></b-col>
                <b-col class="col-2">
                  <div v-for="(input, index) in $v.inputUrlText.$model">
                    <b-button class="mt-2" size="sm" variant="outline-secondary" @click="deleteInputUrl(index)">刪除</b-button>
                  </div>
                </b-col>
              </b-form-row>
            </i-form-group-check>
            <i-form-group-check class="col-12" label-cols="2" content-cols="9" :label="$t('label.attributesLink') + ':'" v-else>
              <b-form-input
                disabled
                v-for="(inputUrl, index) in $v.inputUrlText.$model"
                v-model="form.inputUrlText[index]"
                :key="'col2' + index"
              ></b-form-input>
            </i-form-group-check>
          </b-form-row>

          <b-form-row class="d-flex justify-content-center">
            <b-col class="col-7">
              <b-button-toolbar v-show="formStatusRef === formStatusEnum.INFO" class="float-right mt-5">
                <b-button size="sm" variant="outline-secondary" @click="toQueryView">回上頁</b-button>
              </b-button-toolbar>
              <b-button-toolbar v-show="formStatusRef !== formStatusEnum.INFO" class="float-right mt-5">
                <b-button
                  size="sm"
                  variant="outline-secondary"
                  v-show="formStatusRef === formStatusEnum.CREATE"
                  v-promise-btn="{ promise: dataPromise }"
                  @click="saveFormAll"
                  >儲存
                </b-button>
                <b-button
                  size="sm"
                  variant="outline-secondary"
                  v-show="formStatusRef === formStatusEnum.MODIFY"
                  v-promise-btn="{ promise: dataPromise }"
                  @click="submitModForm"
                  >更新
                </b-button>
                <b-button size="sm" variant="outline-secondary" @click="showCaseDetailModal(null)">預覽</b-button>
                <b-button
                  size="sm"
                  variant="outline-secondary"
                  v-show="formStatusRef === formStatusEnum.MODIFY"
                  @click="handleDelete($v.id.$model)"
                  >刪除</b-button
                >
                <b-button size="sm" variant="outline-secondary" @click="toQueryView">取消</b-button>
              </b-button-toolbar>
            </b-col>
            <b-col cols="1 mt-2" class="text-right">
              <span>更新 : </span><br />
              <span>建檔 : </span>
            </b-col>
            <b-col cols="2 mt-2">
              <span>{{ $v.updateUser.$model }}</span
              ><br />
              <span>{{ $v.createUser.$model }}</span>
            </b-col>
            <b-col cols="2 mt-2">
              <span>{{ formatDateTime($v.updateTime.$model, '-') }}</span
              ><br />
              <span>{{ formatDateTime($v.createTime.$model, '-') }}</span>
            </b-col>
          </b-form-row>
        </div>
      </div>
    </section>
    <b-modal id="case-detail-modal" size="xl" title="資料預覽" header-bg-variant="secondary" header-text-variant="light">
      <bulletin-edit-info-view :bulletinEditInfoView="bulletinEditInfoViewRef" ref="BulletinEditInfoView" />
    </b-modal>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import {
  yesNoFormatter,
  jurisdictionFormatter,
  moduleFormatter,
  kindFormatter,
  pccUnitFormatter,
  locationFormatter,
} from '@/shared/formatter/common';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { handleBack, navigateByNameAndParams } from '@/router/router';
import { parseRocDate, formatDate, formatDateTime } from '@/shared/date/minguo-calendar-utils';
import BulletinEditInfoView from '@/components/home/home-information.vue';
import { accountStore } from '@/shared/config/store/account-store';
import { useGetters } from '@u3u/vue-hooks';
import { required, email, maxLength, nationalId, mobileTel, lineTel } from '@/shared/validators';
import { formOptionsFormatter } from '@/shared/formatter/common';

export default {
  name: 'BulletinEditMsgDetail',
  components: {
    BulletinEditInfoView,
  },
  props: {
    editMsgDetail: {
      type: Object,
      required: false,
    },
  },

  setup(props, context) {
    onMounted(() => {
      getKind();
      getAdmUnit();
    });

    const editMsgDetailProp = toRefs(props).editMsgDetail;

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    const bulletinEditInfoView = ref(null);

    const allModuleRolesProp = ref([]);

    const allJurisdictionRolesProp = ref([]);

    const checked = ref([]);

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      id: '', //序號
      subject: '', //主旨
      text: '', //內文
      kind: '', //類別
      location: '', //位置(0：預設值 ; A：服務網&雲端系統 ; B：雲端系統 ; C：服務網)
      releaseDate: '', //上架日期
      downDate: '', //下架日期
      releaseUnit: '', //發布單位
      contactPerson: '', //聯絡人
      contactInformationEmail: '', //聯絡email
      contactInformationTel: '', //聯絡tel
      enable: 'Y', //是否啟用
      updateTime: '', //更新時間
      updateUser: '', //更新人員
      createTime: '', //建立時間
      createUser: '', //建立人員
      type: 'INFO', //類型
      isTop: 'Y', //置頂
      jurisdiction: '0', //權限
      jurisdictionRoles: [], //權限list
      module: '0', //模組別
      moduleRoles: [], //模組別list
      locationListDisplay: [], // 位置list
      inputUrlText: [],
      inputUrlAdder: [],
      inputFileText: [],
      inputFile: [],
      status: '',
      workTel: '',
      proInformationAppendixId: [],
      upLoadUrl: [],
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      id: {},
      subject: {},
      text: {},
      kind: {},
      location: {},
      releaseDate: { notNull: required },
      downDate: { notNull: required },
      releaseUnit: {},
      contactPerson: {},
      contactInformationEmail: { email: email, maxLength: maxLength(100) },
      contactInformationTel: { lineTel: lineTel },
      enable: {},
      updateTime: {},
      updateUser: {},
      createTime: {},
      createUser: {},
      type: {},
      isTop: {},
      locationListDisplay: {},
      jurisdiction: {},
      jurisdictionRoles: {},
      module: { notNull: required },
      moduleRoles: {},
      inputUrlText: {},
      inputUrlAdder: {},
      inputFileText: {},
      inputFile: {},
      status: {},
      workTel: {},
      proInformationAppendixId: {},
      upLoadUrl: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //權限
    let jurisdictionRoles = reactive({
      source: [],
      destination: [],
    });

    //模組
    let moduleRoles = reactive({
      source: [],
      destination: [],
    });

    // 下拉選單選項
    const formOptions = reactive({
      kind: [],
      type: [
        { value: 'INFO', text: '公告專區' },
        { value: 'DOWNLOAD', text: '下載專區' },
      ],
      enable: [
        { value: 'Y', text: '是' },
        { value: 'N', text: '否' },
      ],
      isTop: [
        { value: 'Y', text: '是' },
        { value: 'N', text: '否' },
      ],
      jurisdiction: [
        { value: '0', text: '不限制' },
        { value: '1', text: '特定權限' },
      ],
      module: [
        { value: '0', text: '不指定' },
        { value: '1', text: '特定模組' },
      ],
      locationListDisplay: [
        { value: 'B', text: '服務網' },
        { value: 'C', text: '雲端系統' },
      ],
      releaseUnit: [],
    });

    // 執行結果
    const dataPromise = ref(null);
    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    //回上頁
    const toQueryView = () => {
      const param = { isReload: isReload.value };
      handleBack(param);
    };

    //修改狀態
    const changeFormStatus = (status: formStatusEnum) => {
      formStatusRef.value = status;
    };

    //新增連結
    function addInputUrl() {
      form.inputUrlText.push('');
      form.inputUrlAdder.push('');
    }

    //新增檔案
    function addInputFile() {
      form.inputFileText.push('');
      form.inputFile.push(null);
    }

    //刪除連結
    function deleteInputUrl(index) {
      form.inputUrlText.splice(index, 1);
      form.inputUrlAdder.splice(index, 1);
    }

    //刪除檔案
    function deleteInputFile(index) {
      form.inputFileText.splice(index, 1);
      form.inputFile.splice(index, 1);
    }

    //取得使用者資訊
    const userIdentity = ref(useGetters(['account']).account.value);

    function dataChang() {
      //權限
      jurisdictionRoles.destination.forEach(item => {
        let selectOption = {
          code: '',
          text: '',
        };
        selectOption.code = item.code;
        selectOption.text = item.name;
        form.jurisdictionRoles.push(selectOption);
      });

      //模組
      moduleRoles.destination.forEach(item => {
        let selectOption = {
          code: '',
          text: '',
        };
        selectOption.code = item.code;
        selectOption.text = item.name;
        form.moduleRoles.push(selectOption);
      });

      //電話
      if (form.workTel !== '') {
        form.contactInformationTel = form.workTel;
      }

      //連結
      for (const i = 0; i < form.inputUrlText.length; i++) {
        const activeSubjectsObject = { text: '', appendix: '' };
        for (const j = 0; j < form.inputUrlAdder.length; j++) {
          if (i == j) {
            activeSubjectsObject.text = form.inputUrlText[i];
            activeSubjectsObject.appendix = form.inputUrlAdder[j];
            form.upLoadUrl.push(activeSubjectsObject);
          }
        }
      }
    }

    //取得KIND下拉式選單資料
    const getKind = () => {
      const admUrl = '/service/sys-codes/criteria-jpa';
      let admKind = {
        moduleType: 'PWC',
        dataType: 'KIND',
      };
      axios.post(admUrl, admKind).then(({ data }) => {
        if (data != null && Array.isArray(data.content)) {
          formOptions.kind.splice(0, formOptions.kind.length);
          data.content.forEach(item => {
            let selectOption = {
              value: '',
              text: '',
            };
            selectOption.value = item.value;
            selectOption.text = item.codeDescript;
            formOptions.kind.push(selectOption);
          });
        }
      });
    };

    //取得發布單位 AdmUnit
    const getAdmUnit = () => {
      const admUrl = '/service/adm-units/get-unit-opts';
      axios.get(admUrl).then(({ data }) => {
        if (data != null && Array.isArray(data)) {
          formOptions.releaseUnit.splice(0, formOptions.releaseUnit.length);
          data.forEach(item => {
            let selectOption = {
              value: '',
              text: '',
            };
            selectOption.value = item.unitId;
            selectOption.text = item.unitName;
            formOptions.releaseUnit.push(selectOption);
          });
        }
      });
    };

    //查詢連結
    const linkInfoQuery = (id: string) => {
      form.id = id;
      dataPromise.value = axios
        .post('/service/info-appendix/link', form) //前端分頁(呼叫後端)
        .then(({ data }) => {
          if (data != null) {
            data.forEach(item => {
              formDefault.inputUrlText.push(item.text);
              formDefault.inputUrlAdder.push(item.appendix);
            });
          }
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    //查詢下載檔案
    const attachmentQuery = (id: string) => {
      form.id = id;
      dataPromise.value = axios
        .post('/service/info-appendix/attachment', form) //前端分頁(呼叫後端)
        .then(({ data }) => {
          if (data != null) {
            //後端資料回傳
            data.forEach(item => {
              formDefault.inputFileText.push(item.text);
              formDefault.inputFile.push(item.appendix);
            });
          }
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    //SAVE存檔
    const saveFormAll = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否新增此筆資料').then((isOK: boolean) => {
            if (isOK) {
              dataChang();
              putSaveForm();
            }
          });
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const headers = {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    };

    const putSaveForm = () => {
      //加入判斷 判斷是info或down
      let url = '/service/information-save';
      if (form.type === 'DOWNLOAD') {
        url = '/service/download-save';
      }
      form.status = formStatusRef.value;
      form.updateUser = userIdentity.value.unit.name;
      form.createUser = userIdentity.value.unit.name;
      const formData = new FormData();
      const tempForm = Object.assign({}, form);
      delete tempForm.inputFile;
      formData.append(
        'form',
        new Blob([JSON.stringify(tempForm, null, 2)], {
          type: 'application/json',
        })
      );
      Array.from(form.inputFile).forEach(file => {
        formData.append('uploadFile', file);
      });

      axios
        .put(url, formData, headers)
        .then(({ data }) => {
          //將info/down id存回頁面
          navigateByNameAndParams('bulletin-edit-msg', { isReload: isReload.value });
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    };

    //modify修改
    const submitModForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
            if (isOK) {
              dataChang();
              putModForm();
            }
          });
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const putModForm = () => {
      //加入判斷 判斷是info或down
      let url = '/service/information-updated';
      if (form.type === 'DOWNLOAD') {
        url = '/service/download-updated';
      }
      form.updateUser = userIdentity.unit.name;
      const formData = new FormData();
      const tempForm = Object.assign({}, form);
      delete tempForm.inputFile;
      formData.append(
        'form',
        new Blob([JSON.stringify(tempForm, null, 2)], {
          type: 'application/json',
        })
      );
      Array.from(form.inputFile).forEach(file => {
        formData.append('uploadFile', file);
      });

      axios
        .put(url, formData, headers)
        .then(({ data }) => {
          changeFormStatus(formStatusEnum.MODIFY);
          createDefaultValue(data);
          isReload.value = true;
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    };

    //刪除
    const handleDelete = (deleteId: string) => {
      $bvModal.msgBoxConfirm('是否確認刪除？').then((isOK: boolean) => {
        if (isOK) {
          putDelForm(deleteId);
        }
      });
    };

    const putDelForm = (deleteId: string) => {
      form.id = deleteId;
      //加入判斷 判斷是info或down
      let url = '/service/info-delete';
      if (form.type === 'DOWNLOAD') {
        url = '/service/download-delete';
      }
      axios
        .delete(url, { data: form })
        .then((response: AxiosResponse) => {
          navigateByNameAndParams('bulletin-edit-msg', { isReload: isReload.value });
        })
        .finally(() => {})
        .catch(notificationErrorHandler(notificationService));
    };

    const bulletinEditInfoViewRef = ref(null);

    //預覽
    const showCaseDetailModal = () => {
      form.status = 'view';
      form.contactInformationTel = form.workTel;
      dataChang();
      bulletinEditInfoViewRef.value = form;
      $bvModal.show('case-detail-modal');
    };

    //進入detail頁面塞值
    const createDefaultValue = (data: editMsgDetailProp) => {
      //infoView  modify
      if ('create' !== data.webStatus) {
        data.releaseDate = formatDate(new Date(data.releaseDate), '/');
        data.downDate = formatDate(new Date(data.downDate), '/');
      }
      //判斷何種功能
      if ('infoView' === data.webStatus) {
        changeFormStatus(formStatusEnum.INFO);
        linkInfoQuery(data.id);
        attachmentQuery(data.id);
      } else if ('modify' === data.webStatus) {
        data.releaseDate = parseRocDate(data.releaseDate, '/');
        data.downDate = parseRocDate(data.downDate, '/');
        changeFormStatus(formStatusEnum.MODIFY);
        linkInfoQuery(data.id);
        attachmentQuery(data.id);
      } else if ('create' === data.webStatus) {
        changeFormStatus(formStatusEnum.CREATE);
      } else {
        data.releaseDate = parseRocDate(data.releaseDate, '/');
        data.downDate = parseRocDate(data.downDate, '/');
      }
      formDefault.locationListDisplay = data.location;
      getJurisdictionAllRoles();
      getModuleAllRoles();
      Object.assign(formDefault, data);
      reset();
    };

    watch(
      editMsgDetailProp,
      () => {
        if (editMsgDetailProp.value) {
          createDefaultValue(editMsgDetailProp.value);
        }
      },
      { immediate: true }
    );

    watch(
      () => form.module,
      newValue => {
        if (newValue === '0') {
          const source = allModuleRolesProp.value.filter(option => isInLeft(option));
          moduleRoles.source.splice(0, moduleRoles.source.length, ...source);
          const destination = allModuleRolesProp.value.filter(option => !isInLeft(option));
          moduleRoles.destination.splice(0, moduleRoles.destination.length, ...destination);
        }
      }
    );

    watch(
      () => form.jurisdiction,
      newValue1 => {
        if (newValue1 === '0') {
          const source = allJurisdictionRolesProp.value.filter(option => isInLeft(option));
          jurisdictionRoles.source.splice(0, jurisdictionRoles.source.length, ...source);
          const destination = allJurisdictionRolesProp.value.filter(option => !isInLeft(option));
          jurisdictionRoles.destination.splice(0, jurisdictionRoles.destination.length, ...destination);
        }
      }
    );

    // 建立JurisdictionSource
    function getJurisdictionAllRoles() {
      const admUrl = '/service/adm-roles/get-role-opts';
      axios.get(admUrl).then(({ data }) => {
        if (data != null) {
          allJurisdictionRolesProp.value.splice(0, allJurisdictionRolesProp.value.length);
          data.forEach(item => {
            let queryOption = { code: '', name: '' };
            queryOption.code = item.roleId;
            queryOption.name = item.roleName;
            allJurisdictionRolesProp.value.push(queryOption);
          });
        }
      });
    }

    //權限
    watch(allJurisdictionRolesProp, () => {
      const source = allJurisdictionRolesProp.value.filter(option => isInLeft(option));
      jurisdictionRoles.source.splice(0, moduleRoles.source.length, ...source);
      const destination = allJurisdictionRolesProp.value.filter(option => !isInLeft(option));
      jurisdictionRoles.destination.splice(0, moduleRoles.destination.length, ...destination);
    });

    //權限onChange
    function onChangeJurisdiction({ source, destination }) {
      jurisdictionRoles.source.splice(0, jurisdictionRoles.source.length, ...source);
      jurisdictionRoles.destination.splice(0, jurisdictionRoles.destination.length, ...destination);
    }

    // 建立ModuleSource
    function getModuleAllRoles() {
      const admUrl = '/service/sys-codes/criteria-jpa';
      let admModuleType = {
        moduleType: 'ADM',
        dataType: 'MODULE_TYPE',
      };
      axios.post(admUrl, admModuleType).then(({ data }) => {
        if (data != null && Array.isArray(data.content)) {
          allModuleRolesProp.value.splice(0, allModuleRolesProp.value.length);
          data.content.forEach(item => {
            let queryOption = { code: '', name: '' };
            queryOption.code = item.dataKey;
            queryOption.name = item.value;
            allModuleRolesProp.value.push(queryOption);
          });
        }
      });
    }

    //模組
    watch(allModuleRolesProp, () => {
      const source = allModuleRolesProp.value.filter(option => isInLeft(option));
      moduleRoles.source.splice(0, moduleRoles.source.length, ...source);
      const destination = allModuleRolesProp.value.filter(option => !isInLeft(option));
      moduleRoles.destination.splice(0, moduleRoles.destination.length, ...destination);
    });

    //模組onChange
    function onChangeModule({ source, destination }) {
      moduleRoles.source.splice(0, moduleRoles.source.length, ...source);
      moduleRoles.destination.splice(0, moduleRoles.destination.length, ...destination);
    }

    //權限及模組
    function isInLeft(option) {
      if (checked.value.length) {
        return checked.value.filter(c => c === option.code).length <= 0;
      }
      return true;
    }

    return {
      bulletinEditInfoViewRef,
      bulletinEditInfoView,
      showCaseDetailModal,
      formStatusEnum,
      formStatusRef,
      form,
      $v,
      formOptions,
      formOptionsFormatter,
      formatDateTime,
      dataPromise,
      validateState,
      toQueryView,
      saveFormAll,
      submitModForm,
      onChangeJurisdiction,
      onChangeModule,
      jurisdictionRoles,
      moduleRoles,
      handleDelete,
      addInputUrl,
      deleteInputUrl,
      addInputFile,
      deleteInputFile,
    };
  },
  filters: {
    pccUnit: (value: string) => pccUnitFormatter(value),
    kind: (value: string) => kindFormatter(value),
    module: (value: string) => moduleFormatter(value),
    jurisdiction: (value: string) => jurisdictionFormatter(value),
    yesNo: (value: string) => yesNoFormatter(value),
    location: (value: string) => locationFormatter(value),
  },
};
</script>
<style></style>

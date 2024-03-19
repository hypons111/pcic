<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            刊登求才資訊
          </h5>
        </div>
        <div class="card-body">
          <b-form-row class="align-items-center">
            <b-col cols="2">
              <div class="pr-2 text-right label"><span class="red-text">*</span>刊登日期起</div>
            </b-col>
            <b-col cols="4" v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">{{
              $v.publishdateFrom.$model
            }}</b-col>
            <b-col cols="4" v-else>
              <i-date-picker v-model="$v.publishdateFrom.$model" :state="validateState($v.publishdateFrom)"></i-date-picker>
            </b-col>
            <b-col cols="2">
              <div class="pr-2 text-right label"><span class="red-text">*</span>刊登日期迄</div>
            </b-col>
            <b-col cols="4" v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">{{
              $v.publishdateTo.$model
            }}</b-col>
            <b-col cols="4" v-else>
              <i-date-picker v-model="$v.publishdateTo.$model" :state="validateState($v.publishdateTo)"></i-date-picker>
            </b-col>
          </b-form-row>
          <b-form-row class="mb-1 feedback-row">
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.publishdateFrom.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.publishdateTo.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
          </b-form-row>

          <b-form-row class="align-items-center">
            <b-col cols="2">
              <div class="pr-2 text-right label"><span class="red-text">*</span>職稱</div>
            </b-col>
            <b-col cols="4">
              <span v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">{{ $v.jobTitle.$model }}</span>
              <div v-else>
                <b-form-input v-model.trim="$v.jobTitle.$model" :state="validateState($v.jobTitle)"></b-form-input>
              </div>
            </b-col>
            <b-col cols="2">
              <div class="pr-2 text-right label"><span class="red-text">*</span>工作屬性</div>
            </b-col>
            <b-col cols="4">
              <span v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">{{
                queryOptionsFormatter('jobType', $v.jobType.$model).text
              }}</span>
              <div v-else>
                <b-form-select
                  v-model="$v.jobType.$model"
                  :options="formOptions.jobType"
                  :state="validateState($v.jobType)"
                ></b-form-select>
              </div>
            </b-col>
          </b-form-row>
          <b-form-row class="mb-1 feedback-row">
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.jobTitle.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.jobType.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
          </b-form-row>

          <b-form-row class="align-items-center">
            <b-col cols="2">
              <div class="pr-2 text-right label"><span class="red-text">*</span>需求人數</div>
            </b-col>
            <b-col cols="4">
              <span v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">{{
                $v.numRequired.$model
              }}</span>
              <div v-else>
                <b-form-input type="number" v-model.trim="$v.numRequired.$model" :state="validateState($v.numRequired)"></b-form-input>
              </div>
            </b-col>
            <b-col cols="2">
              <div class="pr-2 text-right label"><span class="red-text">*</span>待遇</div>
            </b-col>
            <b-col cols="4">
              <span v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">{{
                queryOptionsFormatter('salaryRange', $v.salaryRange.$model).text
              }}</span>
              <div v-else>
                <b-form-select
                  v-model="$v.salaryRange.$model"
                  :options="formOptions.salaryRange"
                  :state="validateState($v.salaryRange)"
                ></b-form-select>
              </div>
            </b-col>
          </b-form-row>
          <b-form-row class="mb-1 feedback-row">
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.numRequired.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
            <b-col offset="2" cols="4">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.salaryRange.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
          </b-form-row>

          <b-form-row class="align-items-center">
            <b-col cols="12">
              <div class="pl-2 mb-1 text-left label">內容 - 第一類調查、試驗或監測</div>
            </b-col>
            <b-col cols="12">
              <div v-for="option in formOptions.content1" :key="option.value" class="col-sm-6 col-md-4 d-inline-block">
                <b-form-checkbox
                  v-model="$v.type1.$model"
                  :value="option.value"
                  :disabled="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE"
                >
                  {{ option.text }}
                </b-form-checkbox>
              </div>
            </b-col>
            <!--            <b-form-group-->
            <!--              label="內容 - 第一類調查、試驗或監測"-->
            <!--              label-class="label"-->
            <!--            >-->
            <!--              <div v-for="option in formOptions.content1" :key="option.value" class="col-sm-6 col-md-4 d-inline-block">-->
            <!--                <b-form-checkbox-->
            <!--                  v-model="$v.type1.$model"-->
            <!--                  :value="option.value"-->
            <!--                  :disabled="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE"-->
            <!--                >-->
            <!--                  {{ option.text }}-->
            <!--                </b-form-checkbox>-->
            <!--              </div>-->
            <!--            </b-form-group>-->
          </b-form-row>
          <b-form-row class="mb-1 feedback-row">
            <b-col>
              <div class="is-invalid"></div>
              <b-form-invalid-feedback class="pl-3" v-for="(error, index) of $v.type1.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
          </b-form-row>

          <b-form-row class="align-items-center">
            <b-col cols="12">
              <div class="pl-2 mb-1 text-left label">內容 - 第二類規劃、分析或設計</div>
            </b-col>
            <b-col cols="12">
              <div v-for="option in formOptions.content2" :key="option.value" class="col-sm-6 col-md-4 d-inline-block">
                <b-form-checkbox
                  v-model="$v.type2.$model"
                  :value="option.value"
                  :disabled="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE"
                >
                  {{ option.text }}
                </b-form-checkbox>
              </div>
            </b-col>
            <!--            <b-form-group-->
            <!--              label="內容 - 第二類規劃、分析或設計"-->
            <!--              label-class="label"-->
            <!--            >-->
            <!--              <div v-for="option in formOptions.content2" :key="option.value" class="col-sm-6 col-md-4 d-inline-block">-->
            <!--                <b-form-checkbox-->
            <!--                  v-model="$v.type2.$model"-->
            <!--                  :value="option.value"-->
            <!--                  :disabled="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE"-->
            <!--                >-->
            <!--                  {{ option.text }}-->
            <!--                </b-form-checkbox>-->
            <!--              </div>-->
            <!--            </b-form-group>-->
          </b-form-row>
          <b-form-row class="mb-1 feedback-row">
            <b-col>
              <div class="is-invalid"></div>
              <b-form-invalid-feedback class="pl-3" v-for="(error, index) of $v.type2.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
          </b-form-row>

          <b-form-row class="align-items-center">
            <b-col cols="12">
              <div class="pl-2 mb-1 text-left label">內容 - 第三類施工、監造或管理</div>
            </b-col>
            <b-col cols="12">
              <div v-for="option in formOptions.content3" :key="option.value" class="col-sm-6 col-md-4 d-inline-block">
                <b-form-checkbox
                  v-model="$v.type3.$model"
                  :value="option.value"
                  :disabled="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE"
                >
                  {{ option.text }}
                </b-form-checkbox>
              </div>
            </b-col>
            <!--            <b-form-group-->
            <!--              label="內容 - 第三類施工、監造或管理"-->
            <!--              label-class="label"-->
            <!--            >-->
            <!--              <div v-for="option in formOptions.content3" :key="option.value" class="col-sm-6 col-md-4 d-inline-block">-->
            <!--                <b-form-checkbox-->
            <!--                  v-model="$v.type3.$model"-->
            <!--                  :value="option.value"-->
            <!--                  :disabled="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE"-->
            <!--                >-->
            <!--                  {{ option.text }}-->
            <!--                </b-form-checkbox>-->
            <!--              </div>-->
            <!--            </b-form-group>-->
          </b-form-row>
          <b-form-row class="mb-1 feedback-row">
            <b-col>
              <div class="is-invalid"></div>
              <b-form-invalid-feedback class="pl-3" v-for="(error, index) of $v.type3.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
          </b-form-row>

          <div class="col-12 pl-2 text-left label">工作地點</div>
          <b-form-row>
            <b-col cols="12">
              <div v-for="(option, index) in formOptions.city" :key="option.value" class="col-4 col-md-3 d-inline-block mt-1">
                <b-form-checkbox
                  class="d-inline-block"
                  v-model="$v.workPlace.$model"
                  :value="option.value"
                  :disabled="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE"
                >
                  {{ option.text }}
                </b-form-checkbox>
                <div v-if="showInput(option.value)" class="d-inline-block">
                  <b-form-input
                    size="sm"
                    :disabled="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE"
                  ></b-form-input>
                </div>
              </div>
            </b-col>
          </b-form-row>
          <b-form-row class="mb-1 feedback-row">
            <b-col>
              <div class="is-invalid"></div>
              <b-form-invalid-feedback class="pl-3" v-for="(error, index) of $v.workPlace.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
          </b-form-row>

          <b-form-row class="align-items-center">
            <b-col cols="12">
              <div class="pl-2 mb-1 text-left label">其他條件</div>
            </b-col>
            <b-col cols="12">
              <span v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">{{
                $v.otherComment.$model
              }}</span>
              <div v-else>
                <b-form-textarea rows="4" v-model.trim="$v.otherComment.$model"> </b-form-textarea>
              </div>
            </b-col>
          </b-form-row>
          <b-form-row class="mb-1 feedback-row">
            <b-col>
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.otherComment.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
          </b-form-row>

          <div class="col-12 pl-2 mb-1 text-left label">聯絡方式</div>
          <b-form-row class="align-items-center">
            <!--            <b-form-group-->
            <!--              class="col-4 b-form-group-include-feedback"-->
            <!--              label="姓名："-->
            <!--              label-for="contactPerson"-->
            <!--              label-class="text-right pr-2"-->
            <!--              label-cols="4"-->
            <!--              content-cols="8"-->
            <!--              :state="validateState($v.contactPerson)"-->
            <!--            >-->
            <!--              <div v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">{{ $v.contactPerson.$model }}</div>-->
            <!--              <b-form-input-->
            <!--                v-else-->
            <!--                id="contactPerson"-->
            <!--                v-model="$v.contactPerson.$model"-->
            <!--                :state="validateState($v.contactPerson)"-->
            <!--                trim-->
            <!--              ></b-form-input>-->
            <!--              <b-form-invalid-feedback v-for="(error, index) of $v.contactPerson.$errors" :key="index">-->
            <!--                {{ error.$message }}-->
            <!--              </b-form-invalid-feedback>-->
            <!--            </b-form-group>-->

            <b-col cols="1">
              <div class="text-right">聯絡人：</div>
            </b-col>
            <b-col cols="3">
              <div v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">
                {{ $v.contactPerson.$model }}
              </div>
              <b-form-input v-else v-model="$v.contactPerson.$model" :state="validateState($v.contactPerson)" trim></b-form-input>
            </b-col>

            <b-col cols="1">
              <div class="text-right">手機：</div>
            </b-col>
            <b-col cols="3">
              <div v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">
                {{ $v.contactPhoneNum.$model }}
              </div>
              <b-form-input v-else v-model="$v.contactPhoneNum.$model" :state="validateState($v.contactPhoneNum)" trim></b-form-input>
            </b-col>

            <b-col cols="1">
              <div class="text-right">電話：</div>
            </b-col>
            <b-col cols="3">
              <div v-if="formStatusRef === formStatusEnum.INFO || formStatusRef === formStatusEnum.APPROVE">{{ $v.contactTel.$model }}</div>
              <b-form-input v-else v-model="$v.contactTel.$model" :state="validateState($v.contactTel)" trim></b-form-input>
            </b-col>
          </b-form-row>
          <b-form-row class="mb-1 feedback-row">
            <b-col offset="1" cols="3">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.contactPerson.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
            <b-col offset="1" cols="3">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.contactPhoneNum.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
            <b-col offset="1" cols="3">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.contactTel.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
          </b-form-row>

          <b-form-row class="align-items-center mb-3" v-if="formStatusRef !== formStatusEnum.CREATE">
            <b-col cols="2">
              <div class="pr-2 text-right label"><span class="red-text">*</span>刊登狀態</div>
            </b-col>
            <b-col cols="10">
              <b-form-radio-group
                v-if="formStatusRef !== formStatusEnum.APPROVE && $v.isApprove.$model === '2'"
                v-model="$v.publishStatus.$model"
                :options="formOptions.publishStatus.slice(1)"
              ></b-form-radio-group>
              <div v-else>
                {{ $v.publishStatus.$model ? queryOptionsFormatter('publishStatus', $v.publishStatus.$model).text : '' }}
              </div>
            </b-col>
          </b-form-row>

          <b-form-row class="align-items-center mb-3">
            <b-col cols="2">
              <div class="pr-2 text-right label"><span class="red-text">*</span>是否公開</div>
            </b-col>
            <b-col cols="10">
              <div v-if="formStatusRef === formStatusEnum.APPROVE">
                {{ $v.isPublic.$model ? queryOptionsFormatter('isPublic', $v.isPublic.$model).text : '' }}
              </div>
              <b-form-radio-group v-else v-model="$v.isPublic.$model" :options="formOptions.isPublic"> </b-form-radio-group>
            </b-col>
          </b-form-row>

          <b-form-row class="align-items-center mb-3" v-if="formStatusRef !== formStatusEnum.CREATE">
            <b-col cols="2">
              <div class="pr-2 text-right label"><span class="red-text">*</span>是否核准</div>
            </b-col>
            <b-col cols="10">
              <div v-if="formStatusRef !== formStatusEnum.APPROVE">
                {{ $v.isApprove.$model ? queryOptionsFormatter('isApprove', $v.isApprove.$model).text : '' }}
              </div>
              <b-form-radio-group
                v-if="formStatusRef === formStatusEnum.APPROVE"
                v-model="$v.isApprove.$model"
                :options="formOptions.isApprove.slice(1)"
              >
              </b-form-radio-group>
            </b-col>
          </b-form-row>

          <b-form-row v-if="$v.isApprove.$model === '0'">
            <b-col cols="2">
              <div class="pr-2 text-right label"><span class="red-text">*</span>退回原因</div>
            </b-col>
            <b-col cols="10">
              <span v-if="formStatusRef !== formStatusEnum.APPROVE">{{ $v.rejectReason.$model }}</span>
              <b-form-textarea
                v-if="formStatusRef === formStatusEnum.APPROVE"
                v-model.trim="$v.rejectReason.$model"
                :state="validateState($v.rejectReason)"
                rows="4"
              >
              </b-form-textarea>
            </b-col>
          </b-form-row>
          <b-form-row class="mb-4 feedback-row">
            <b-col offset="2">
              <div class="is-invalid"></div>
              <b-form-invalid-feedback v-for="(error, index) of $v.rejectReason.$errors" :key="index">
                {{ error.$message }}
              </b-form-invalid-feedback>
            </b-col>
          </b-form-row>

          <b-button-toolbar class="float-right mt-4">
            <b-button size="sm" variant="outline-secondary" @click=""><font-awesome-icon :icon="['far', 'save']" />儲存</b-button>
            <b-button
              v-show="formStatusRef === formStatusEnum.INFO && $v.isApprove.$model === '1'"
              size="sm"
              variant="outline-secondary"
              @click="changeFormStatus(formStatusEnum.MODIFY)"
              ><font-awesome-icon :icon="['far', 'edit']" />{{ $t('button.modify') }}</b-button
            >
            <b-button v-show="formStatusRef !== formStatusEnum.INFO" size="sm" variant="outline-secondary" @click="reset">重設</b-button>
            <b-button
              v-show="formStatusRef === formStatusEnum.MODIFY"
              size="sm"
              variant="outline-secondary"
              @click="changeFormStatus(formStatusEnum.INFO)"
              ><font-awesome-icon :icon="['fas', 'undo-alt']" />取消</b-button
            >
            <b-button v-show="formStatusRef !== formStatusEnum.MODIFY" size="sm" variant="outline-secondary" @click="toQueryView"
              ><font-awesome-icon :icon="['fas', 'arrow-left']" />返回</b-button
            >
          </b-button-toolbar>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, Ref, ref } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { statusFormatter } from '@/shared/formatter/common';
// import { guildTypeFormatter, statusFormatter } from '@/shared/formatter/common';
// import { CmsComp } from '@/shared/model/cms-comp.model';

export default {
  name: 'geoIn02EditInfo',
  props: {
    cmsComp: {
      required: false,
    },
    formStatus: {
      // type: Ref<formStatusEnum>,
      required: false,
    },
  },
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
      APPROVE = 'approve',
    }

    const formOptions = reactive({
      jobType: [
        { value: '', text: '不拘' },
        { value: '1', text: '工程技術顧問公司且有合格指導人' },
        { value: '2', text: '技師事務所且有合格指導人' },
        { value: '3', text: '營造業且有合格指導人' },
        { value: '4', text: '政府工程機關且有合格指導人' },
        { value: '5', text: '其他類型機構或無合格指導人' },
      ],
      salaryRange: [
        { value: '', text: '不拘' },
        { value: '1', text: '年薪 40萬(不含)以下' },
        { value: '2', text: '年薪 40萬(含)~55萬(不含)' },
        { value: '3', text: '年薪 55萬(含)~70萬(不含)' },
        { value: '4', text: '年薪 70萬(含)~85萬(不含)' },
        { value: '5', text: '年薪 85萬(含)~100萬(不含)' },
        { value: '6', text: '年薪 100萬(含)以上' },
        { value: '0', text: '面議' },
      ],
      publishStatus: [
        { value: '1', text: '未刊登' },
        { value: '2', text: '刊登中' },
        { value: '3', text: '已徵才' },
        { value: '0', text: '結束刊登', disabled: true },
      ],
      isPublic: [
        { value: '1', text: '是' },
        { value: '0', text: '否' },
      ],
      isApprove: [
        { value: '1', text: '待審核' },
        { value: '2', text: '通過' },
        { value: '0', text: '不通過' },
      ],
      city: [
        { value: '基隆市', text: '基隆市' },
        { value: '台北市', text: '台北市' },
        { value: '新北市', text: '新北市' },
        { value: '桃園市', text: '桃園市' },
        { value: '新竹縣', text: '新竹縣' },
        { value: '新竹市', text: '新竹市' },
        { value: '苗栗縣', text: '苗栗縣' },
        { value: '台中市', text: '台中市' },
        { value: '南投縣', text: '南投縣' },
        { value: '彰化縣', text: '彰化縣' },
        { value: '雲林縣', text: '雲林縣' },
        { value: '嘉義縣', text: '嘉義縣' },
        { value: '嘉義市', text: '嘉義市' },
        { value: '台南市', text: '台南市' },
        { value: '高雄市', text: '高雄市' },
        { value: '屏東縣', text: '屏東縣' },
        { value: '台東縣', text: '台東縣' },
        { value: '花蓮縣', text: '花蓮縣' },
        { value: '宜蘭縣', text: '宜蘭縣' },
        { value: '澎湖縣', text: '澎湖縣' },
        { value: '金門縣', text: '金門縣' },
        { value: '連江縣', text: '連江縣' },
      ],
      content1: [
        { value: '1', text: '地質鑽探調查' },
        { value: '2', text: '海上鑽探調查' },
        { value: '3', text: '其他鑽探調查(如斜孔、深孔等)' },
        { value: '4', text: '基地地質調查' },
        { value: '5', text: '斷層或其他構造調查' },
        { value: '6', text: '土石流調查' },
        { value: '7', text: '崩塌地調查' },
        { value: '8', text: '現地抽水試驗' },
        { value: '9', text: '地下水文調查' },
        { value: '10', text: '地下水污染調查' },
        { value: '11', text: '地質災害調查' },
        { value: '12', text: '植生調查' },
        { value: '13', text: '地球物理探測' },
        { value: '14', text: '其他現場調查(或測量)' },
        { value: '15', text: '現地試驗(如平鈑載重，直剪等)' },
        { value: '16', text: '航照判釋' },
        { value: '17', text: '基樁載重試驗' },
        { value: '18', text: '地錨揚起試驗' },
        { value: '19', text: '地質改良成效驗收試驗' },
        { value: '20', text: '土壤實驗室試驗(如物性、單壓、三軸、直剪、壓密、透水或CBR等)' },
        { value: '21', text: '岩石實驗室試驗(如物性、單壓、抗張、三軸、直剪、消散耐久或點荷重等)' },
        { value: '22', text: '調查、試驗之規範及法規' },
        { value: '23', text: '調查、試驗之估價' },
        { value: '24', text: '其他室內試驗或現地試驗' },
        { value: '25', text: '坡地安全監測' },
        { value: '26', text: '開挖工程安全' },
        { value: '27', text: '隧道安全監測' },
        { value: '28', text: '橋梁安全監測' },
        { value: '29', text: '壩工安全監測' },
        { value: '30', text: '其他監測' },
      ],
      content2: [
        { value: '1', text: '深開挖工程' },
        { value: '2', text: '連續壁、擋土排樁' },
        { value: '3', text: '支撐工程' },
        { value: '4', text: '山坡地護坡工程' },
        { value: '5', text: '坡地開發工程' },
        { value: '6', text: '背拉式擋土工程' },
        { value: '7', text: '地錨工程' },
        { value: '8', text: '邊坡擋土排樁及抗滑樁工程' },
        { value: '9', text: '加勁擋土牆' },
        { value: '10', text: '土釘(或岩釘)護坡工程' },
        { value: '11', text: 'RC擋土牆' },
        { value: '12', text: '土釘(或岩釘)工程' },
        { value: '13', text: '水土保持計畫' },
        { value: '14', text: '土壤隧道工程' },
        { value: '15', text: '排水工程' },
        { value: '16', text: '岩石隧道工程' },
        { value: '17', text: '崩塌地整治' },
        { value: '18', text: '地盤改良工程' },
        { value: '19', text: '舖面設計' },
        { value: '20', text: '大地工程數值分析' },
        { value: '21', text: '道路路工定線' },
        { value: '22', text: '儲槽基礎工程' },
        { value: '23', text: '橋梁基礎工程' },
        { value: '24', text: '液化潛能評估及處理' },
        { value: '25', text: '山坡地構造物基礎開挖及擋土支' },
        { value: '26', text: '基樁工程' },
        { value: '27', text: '淺基及井基工程' },
        { value: '28', text: '特殊地工結構工程' },
        { value: '29', text: '港灣工程' },
        { value: '30', text: '壩工(如拱壩、土石壩等)' },
        { value: '31', text: '坡地安全監測' },
        { value: '32', text: '開挖工程安全監測' },
        { value: '33', text: '規劃、分析、設計相關規範及法' },
        { value: '34', text: '橋梁安全監測' },
        { value: '35', text: '隧道安全監測' },
        { value: '36', text: '規劃、分析、設計相關估價' },
        { value: '37', text: '壩工安全監測' },
        { value: '38', text: '開挖工程安全監測' },
        { value: '39', text: '其他工程規劃、分析、設計' },
      ],
      content3: [
        { value: '1', text: '深開挖工程' },
        { value: '2', text: '連續壁、擋土排樁' },
        { value: '3', text: '支撐工程' },
        { value: '4', text: '坡地開發工程' },
        { value: '5', text: '地錨工程' },
        { value: '6', text: '加勁擋土牆' },
        { value: '7', text: 'RC擋土牆' },
        { value: '8', text: '山坡地護坡工程' },
        { value: '9', text: '邊坡擋土排樁及抗滑樁工程' },
        { value: '10', text: '土釘(或岩釘)工程' },
        { value: '11', text: '背拉式擋土工程' },
        { value: '12', text: '水土保持計畫' },
        { value: '13', text: '排水工程' },
        { value: '14', text: '崩塌地整治' },
        { value: '15', text: '舖面設計' },
        { value: '16', text: '道路路工定線' },
        { value: '17', text: '橋梁基礎工程' },
        { value: '18', text: '土壤隧道工程' },
        { value: '19', text: '岩石隧道工程' },
        { value: '20', text: '基樁工程' },
        { value: '21', text: '山坡地構造物基礎開挖及擋土支' },
        { value: '22', text: '淺基及井基工程' },
        { value: '23', text: '地盤改良工程' },
        { value: '24', text: '儲槽基礎工程' },
        { value: '25', text: '港灣工程' },
        { value: '26', text: '特殊地工結構工程' },
        { value: '27', text: '監造相關規範及法規' },
        { value: '28', text: '壩工(如拱壩、土石壩等)' },
        { value: '29', text: '監造相關估驗及品管' },
        { value: '30', text: '營建管理' },
        { value: '31', text: '坡地安全監測' },
        { value: '32', text: '開挖工程安全監測' },
        { value: '33', text: '隧道安全監測' },
        { value: '34', text: '壩工安全監測' },
        { value: '35', text: '橋梁安全監測' },
        { value: '36', text: '施工相關估價' },
        { value: '37', text: '其他施工、監造或管理' },
      ],
    });

    // const roleRef: Ref<RoleEnum> = ref(RoleEnum.USER);

    // 傳入的物件
    const cmsCompProp = computed(() => props.cmsComp);
    const formStatus = computed(() => props.formStatus);
    const formStatusRef: Ref<formStatusEnum> = ref(formStatus.value.value);
    // const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    let formEmpty = {
      jobTitle: '', //職位
      jobType: '', //工作屬性
      type1: [],
      type2: [],
      type3: [],
      numRequired: '',
      salaryRange: '', //薪水
      workPlace: [], //工作地點
      workPlaceComment: [],
      otherComment: '',
      publishdateFrom: '',
      publishdateTo: '',
      contactPerson: '',
      contactPhoneNum: '',
      contactTel: '',
      publishStatus: '', //刊登狀態
      isPublic: '', //是否公開
      isApprove: '', //是否核准
      rejectReason: '', //退回原因
    };

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      jobTitle: '', //職位
      jobType: '', //工作屬性
      type1: [],
      type2: [],
      type3: [],
      numRequired: '',
      salaryRange: '', //薪水
      workPlace: [], //工作地點
      workPlaceComment: [],
      otherComment: '',
      publishdateFrom: '',
      publishdateTo: '',
      contactPerson: '',
      contactPhoneNum: '',
      contactTel: '',
      publishStatus: '', //刊登狀態
      isPublic: '', //是否公開
      isApprove: '', //是否核准
      rejectReason: '', //退回原因
    };

    // 建立表單物件 ref
    let form = reactive(Object.assign({}, formDefault));

    onActivated(() => {
      reset();
      Object.assign(formDefault, formEmpty);
      if (cmsCompProp.value) {
        Object.assign(formDefault, cmsCompProp.value);
        Object.assign(form, formDefault);
      } else {
        Object.assign(form, formDefault);
      }
      formStatusRef.value = formStatus.value.value;
    });

    // 表單物件驗證規則
    const rules = {
      jobTitle: { notNull: required }, //職位
      jobType: { notNull: required }, //工作屬性
      type1: {},
      type2: {},
      type3: { notNull: required },
      numRequired: { notNull: required },
      salaryRange: { notNull: required }, //薪水
      workPlace: { notNull: required }, //工作地點
      workPlaceComment: {},
      otherComment: {},
      publishdateFrom: { notNull: required },
      publishdateTo: { notNull: required },
      contactPerson: { notNull: required },
      contactPhoneNum: { notNull: required },
      contactTel: {},
      publishStatus: {}, //刊登狀態
      isPublic: {}, //是否公開
      isApprove: {}, //是否核准
      rejectReason: { notNull: required }, //退回原因
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 執行結果
    const dataPromise = ref(null);
    // 是否需要重新查詢
    const isReload = ref(false);

    // modal物件
    const $bvModal = useBvModal();

    const toQueryView = () => {
      context.emit('changeComponent', 'GeoIn02Query', isReload.value);
    };

    const changeFormStatus = (status: formStatusEnum) => {
      if (status === formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
            if (isOK) {
              putForm();
            }
          });
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    const putForm = () => {
      let url: string;
      if (formStatusRef.value === formStatusEnum.MODIFY) {
        url = `/cms-comps/${form.id}`;
      }
      dataPromise.value = axios
        .put(url, form)
        .then(({ data }) => {
          changeFormStatus(formStatusEnum.INFO);
          createDefaultValue(data);
          isReload.value = true;
          notificationService.success(`${form.id}-${form.name}:修改成功`);
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    const createDefaultValue = (data: any) => {
      Object.assign(formDefault, data);
      Object.assign(form, formDefault);
    };

    const queryOptionsFormatter = (option: string, value: string) =>
      formOptions[option].find(function (item) {
        if (item.value === value) {
          return item.text; //為什還是傳出物件???
        }
      });
    // const queryOptionsFormatter = (option: string, value: string) => formOptions[option].find((item) => item.value === value);

    const showInput = (value: string) => {
      if ($v.value.workPlace.$model.find(item => item === value)) {
        return true;
      }
    };

    return {
      formStatusEnum,
      formStatusRef,
      $v,
      dataPromise,
      changeFormStatus,
      reset,
      validateState,
      toQueryView,
      submitForm,
      formOptions,
      queryOptionsFormatter,
      showInput,
      // cmsCompProp
    };
  },
};
</script>
<style>
.red-text {
  color: red;
  font-weight: bold;
}
.label {
  background-color: #737373;
  font-weight: bolder;
  color: white;
  padding-top: 5px;
  padding-bottom: 5px;
}
.feedback-row {
  height: 24px;
}
.b-form-group-include-feedback {
  margin-bottom: 0.25rem;
  height: 5.8rem;
}
.mx-datepicker {
  width: 100%;
}
</style>

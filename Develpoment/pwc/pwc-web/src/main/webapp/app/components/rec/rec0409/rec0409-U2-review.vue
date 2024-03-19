<template>
  <div class="container">
    <div class="card">
      <div class="card-body col-12">
        <div>
          <div>
            <b-form-row class="align-items-top">
              <a href="">中央對各級地方政府重大天然災害救災經費處理辦法(第14、16條)</a>
            </b-form-row>
            <b-form-row class="mt-3">
              <i-form-group-check class="col-10" :label="$t('label.completedDeadline')" :item="$v.completedDeadline">
                <b-form-input value="2021.4.21" readonly> </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-10" :label="$t('label.extensionDeadline')" :item="$v.extensionDeadline">
                <b-form-input value="2022.10.31" readonly> </b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-10" :label="$t('label.handlingStatus')" :item="$v.handlingStatus">
                <b-form-textarea rows="10" cols="100" maxlength="4000" disabled v-model="$v.defauleHandling.$model">
                  <!-- 決標日期： 契約開工日期： 契約完工日期： 實際開工日期： 預定完工日期： 不計工期天數： 原因分析： -->
                </b-form-textarea>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-10" :label="$t('label.extensionCondition')" :item="$v.extensionCondition">
                <b-form-select :options="queryOptions.extensionCondition"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="mt-3">
              <i-form-group-check class="col-10" :label="$t('label.approvedDeadline')" :item="$v.approvedDeadline" :labelStar="true">
                <b-form-input value="2021.4.21"> </b-form-input>
              </i-form-group-check> </b-form-row
            ><b-form-row>
              <i-form-group-check class="col-10" :label="$t('label.opinions')" :item="$v.opinions">
                <b-form-textarea rows="10" cols="100" maxlength="4000"> </b-form-textarea>
              </i-form-group-check>
            </b-form-row>
            <div class="button-float-right">
              <b-button-toolbar>
                <i-button class="mt-1 ml-1" type="save" @click="toEditInfo"></i-button>
                <b-button class="mt-1 ml-1" variant="info" @click="toEditInfo">核定</b-button>
                <i-button class="mt-1 ml-1" type="clipboard-minus" @click="toEditInfo"></i-button>
                <b-button class="mt-1 ml-1" variant="info" @click="toEditInfo">不同意</b-button>
                <i-button class="mt-1 ml-1" type="arrow-counterclockwise" @click="toEditInfo"></i-button>
              </b-button-toolbar>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, reactive, Ref, ref, watch, toRefs, onActivated, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useGetters } from '@u3u/vue-hooks';

export default {
  name: 'rec0409-U2-review',
  props: {

  },
  setup(props, context) {
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    // 空值表單
    const formDefault = {
      completedDeadline: '',
      handlingStatus: '',
      defauleHandling:'決標日期： \n契約開工日期： \n契約完工日期： \n實際開工日期： \n預定完工日期： \n不計工期天數： \n原因分析：',
      extensionCondition: '',
    };

    // 預存好的form
    const form = reactive(Object.assign({}, formDefault));

    // 檢核規則
    const rules = {
      handlingStatus: { notNull: required },
      defauleHandling:{},
      extensionCondition: { notNull: required },
    };

    // 下拉選單選項
    const queryOptions = reactive({
      extensionCondition: [
        { value: '', text: '請選擇' },
        { value: '1', text: '1-用地使用協調' },
        { value: '2', text: '2-民眾陳情或抗爭' },
        { value: '3', text: '3-已在規定期限內辦理勞務標或工程標招標經流標者' },
        { value: '4', text: '4-因災害擴大或實際需求須變更設計，致增加工期' },
        { value: '5', text: '5-配合其他工程暫無法施作，或暫無法申報竣工者' },
        { value: '6', text: '6-天候異常' },
        { value: '7', text: '7-因廠商延宕經機關依契約規定積極處理者' },
        { value: '8', text: '8-其他特殊因素' },
      ],
    });

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //是否要重新查詢
    const isReload: boolean = ref(null);
    //modal物件
    const $bvModal = useBvModal();

    //新增&修改送出前檢查訊息
    const submitForm = (submitMethod: string) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (submitMethod === 'modify') {
            //提交修改
            putForm();
            $bvModal.msgBoxOk('資料異動成功');
          }
          if (submitMethod === 'create') {
            //提交新增
            insertProject();
            $bvModal.msgBoxOk('資料新增成功');
          }
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //轉到復建執行管控檢視案件畫面
    const toEditInfo = () => {
      navigateByNameAndParams('rec0403-edit-info', { isNotKeepAlive: true });
    };

    return {
      $v,
      formStatusEnum,
      formStatusRef,
      reset,
      validateState,
      submitForm,
      queryOptions,
      toEditInfo,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

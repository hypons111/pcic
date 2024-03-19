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
            <!-- 申請方式 -->
            <i-form-group-check :label="$t('label.applyItems') + '：'" :item="$v.applyItem">
              <b-input-group>
                <b-form-select v-model="$v.applyItem.$model" :options="formOptions.applyItem"> </b-form-select>
              </b-input-group>
            </i-form-group-check>
            <b-col offset="2">
              <i-button type="box-arrow-up-right"></i-button>
              <i-button type="arrow-left" @click="toQueryView"></i-button>
            </b-col>
          </b-form-row>
        </b-collapse>
      </b-card-body>
    </b-card>
  </b-container>
</template>

<script lang="ts">
import axios from 'axios';
import AdmSysCodeService from '@/shared/common-service/adm-sys-code.service';
import { defineComponent, reactive, onMounted } from '@vue/composition-api';
import { useValidation } from '@/shared/form';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { handleBack } from '@/router/router';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { FormStatusEnum } from '@/shared/enum';

export default defineComponent({
  name: 'Eng0110',
  setup(props) {
    const admSysCodeService = new AdmSysCodeService();
    const notificationService = useNotification();
    const $bvModal = useBvModal();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formOptions = reactive({
      applyItem: [
        { value: '0', text: '新申請' },
        { value: '1', text: '換發、變更及換發' },
      ],
    });

    onMounted(() => {
      getAllSysCodes();
    });

    function getAllSysCodes() {
      admSysCodeService.setFormOptions(formOptions, 'ENG');
    }

    const formDefault = {
      applyItem: '0',
    };
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      applyItem: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    function toQueryView() {
      handleBack({ isNotKeepAlive: true });
    }

    return {
      $v,
      reset,
      stepVisible,
      formOptions,
      FormStatusEnum,
      toQueryView,
    };
  },
});
</script>
<style scoped></style>

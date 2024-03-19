<template>
  <b-form-row v-if="proad0101NoCommunicateRef !== null">
    <b-col cols="2" class="text-left">不交流狀態：</b-col>
    <b-col cols="10">
      <b-form-group v-slot="{ ariaDescribedby }">
        <b-form-checkbox-group
          v-model="nocmdata.noCommunicateTypes"
          :options="formOptions.noCommunicateType"
          :aria-describedby="ariaDescribedby"
          :disabled="formStatusRef === formStatusEnum.INFO"
          plain
          stacked
        ></b-form-checkbox-group>
      </b-form-group>
      <br />
      <br />
      請詳細說明選擇前項「不受理交流」的理由:
      <!--      formatDateTime{{ formatDateTime(nocmdata.proCommunicatedDTOs.updateTimePos.patentEndDate, '-') }}-->
      <br />
      <br />
      <div class="" v-if="formStatusRef === formStatusEnum.INFO">{{ nocmdata.proInnovativeProductMainDTO.commReason }}</div>
      <!--        <b-form-textarea disabled-->
      <!--                         v-if="formStatusRef !== formStatusEnum.INFO"-->
      <!--                         rows="4"-->
      <!--                         trim-->
      <!--        ></b-form-textarea>-->
    </b-col>
    <!--    noCommunicateType的值={{nocmdata.proNoCommunicateDTOs[0].noCommunicateType}}-->
    <!--    {{ nocmdata.proInnovativeProductMainDTO.patentEndDate | dateToString }}-->
  </b-form-row>
</template>

<script lang="ts">
import { reactive, watch, toRef, ref, onMounted } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
// import { formStatusEnum, roleEnum } from '@/shared/enum';
import { parseRocDate, formatDateTime, formatDate } from '@/shared/date/minguo-calendar-utils';
import { ProInnovativeProductSumSearchDetail } from '@/shared/model/proModel/pro-innovative-product-sum-search-detail-dto';

export default {
  name: 'proad0101NoCommunicate',

  props: {
    nocmdata: {
      // type: Array,
      required: false,
    },
    formStatus: {
      // type: formStatusEnum,
      required: true,
    },
    role: {
      required: true,
    },
    proNoCommunicateDTOs: {
      type: Object,
      required: false,
    },
  },

  setup(props) {
    // nocmdataRef=從query的table.data取資料
    const nocmdataRef = toRef(props, 'nocmdata');
    const formStatusRef = toRef(props, 'formStatus');
    const roleRef = toRef(props, 'role');
    const proad0101NoCommunicateRef = ref(null);
    // const communicateStatusRef = ref(null);
    const proNoCommunicateDTOsRef = toRef(props, 'proNoCommunicateDTOs');

    watch(
      nocmdataRef,
      () => {
        console.log('nocmdataRef======', nocmdataRef.value);
        if (nocmdataRef.value) {
          if (
            nocmdataRef.value.proInnovativeProductMainDTO.statusInnovativeProduct === '1' &&
            nocmdataRef.value.proInnovativeProductMainDTO.statusOrgSuggestion === '1'
          ) {
            proad0101NoCommunicateRef.value = nocmdataRef.value;
          } else {
            proad0101NoCommunicateRef.value = null;
          }
        }
      },

      { immediate: true, deep: true }
    );

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
      REMAKE = 'remake',
    }

    enum roleEnum {
      USER = '使用者',
      ADMIN = '管理員',
    }

    const formOptions = reactive({
      noCommunicateType: [
        { value: '0', text: '1.廠商提出之創新產品不具創新性。' },
        { value: '1', text: '2.廠商填報欄位內容不實，或有缺漏經通知限期補正屆期未補正。' },
        { value: '2', text: '3.機關無採購該產品之需求。' },
        { value: '3', text: '4.機關一年內曾就同一創新產品辦理交流' },
      ],
    });

    // let formDefault = {
    //   others: '',
    // };
    let formDefault = new ProInnovativeProductSumSearchDetail();

    const rules = {
      ProNoCommunicateDTO: {
        noCommunicateType: {},
      },
      ProOrgSuggestionDTO: {
        commReason: {},
      },
    };

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    return {
      formOptions,
      formStatusEnum,
      formStatusRef,
      roleRef,
      $v,
      reset,
      validateState,
      proad0101NoCommunicateRef,
      formatDateTime,
      proNoCommunicateDTOsRef,
    };
  },
  filters: {
    dateToString: (value: Date) => formatDate(value, '/'),
  },
};
</script>

<style scoped></style>

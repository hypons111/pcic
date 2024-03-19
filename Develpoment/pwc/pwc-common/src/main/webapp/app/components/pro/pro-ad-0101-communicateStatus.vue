<template>
  <div>
    <!--    proad0101communicateStatusRef有帶到資料就 !== null 沒帶到資料就不會顯示-->
    <b-form-row v-if="proad0101communicateStatusRef !== null">
      <b-col cols="2" class="text-left">已交流狀態</b-col>
      <b-col cols="10">
        <!--      <b-table class="table-sm" :items="proad0101communicateStatusRef" :fields="table.fields_first"-->
        <!--               bordered></b-table>-->
        <!--      <b-table class="table-sm" :items="proad0101communicateStatusRef" :fields="table.fields_second"-->
        <!--               bordered></b-table>-->
        <!--      <b-table class="table-sm" :items="proad0101communicateStatusRef" :fields="table.fields_third"-->
        <!--               bordered></b-table>-->
        <b-table-simple>
          <b-thead>
            <!--            formatDateTime(nocmdata.proCommunicatedDTOs.updateTimePos.patentEndDate, '-')-->
            <b-tr>
              <b-th>回覆更新日期:</b-th>
              <!--              這裡不需要兩項以上的時間故維持原樣-->
              <b-td>{{ formatDateTime(cmdata.proCommunicatedDTOs[0].updateTimePos, '-') }}</b-td>
            </b-tr>
          </b-thead>
          <b-tbody>
            <b-tr>
              <b-th>機關名稱:</b-th>
              <b-td>{{ cmdata.proInnovativeProductMainDTO.acceptedOrganization }}</b-td>
              <b-th>交流時間:</b-th>
              <b-td>{{ cmdata.proInnovativeProductMainDTO.time | dateToString }}</b-td>
            </b-tr>
            <b-tr>
              <b-th>交流方式:</b-th>
              <b-td>
                <!--                <b-form-input-->
                <!--                  plaintext-->
                <!--                  disabled-->
                <!--                  :value="formOptionsFormatter('communicateType', cmdata.proInnovativeProductMainDTO.type)"-->
                <!--                ></b-form-input>-->
                {{ formOptionsFormatter('communicateType', cmdata.proInnovativeProductMainDTO.type) }}
              </b-td>
              <b-th>交流地點:</b-th>
              <b-td>{{ cmdata.proInnovativeProductMainDTO.addr }}</b-td>
            </b-tr>
            <b-tr>
              <b-th>交流過程紀要:</b-th>
              <b-td>{{ cmdata.proInnovativeProductMainDTO.outline }}</b-td>
              <b-th>交流意見:</b-th>
              <b-td>{{ cmdata.proInnovativeProductMainDTO.memo }}</b-td>
            </b-tr>
            <b-tr>
              <b-th>參與人員:</b-th>
              <b-td>{{ cmdata.personNames }}</b-td>
              <b-th>其他:</b-th>
              <b-td>{{ cmdata.proInnovativeProductMainDTO.other }}</b-td>
            </b-tr>
          </b-tbody>
        </b-table-simple>
      </b-col>
    </b-form-row>
  </div>
</template>

<script lang="ts">
import { reactive, ref, toRef, watch } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
// import { formStatusEnum, roleEnum } from '@/shared/enum';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { parseRocDate, formatDateTime, formatDate } from '@/shared/date/minguo-calendar-utils';
import { statusFormatter } from '@/shared/formatter/common';

export default {
  name: 'proad0101communicateStatus',
  //從toEditView傳送進來
  props: {
    cmdata: {
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
    proInnovativeProductMainDTO: {
      type: Object,
      required: false,
    },
    proCommunicatedDTOs: {
      type: Object,
      required: false,
    },
  },

  setup(props, context) {
    //dataRef=從query的table.data取資料
    const cmdataRef = toRef(props, 'cmdata');
    const formStatusRef = toRef(props, 'formStatus');
    const roleRef = toRef(props, 'role');
    // const communicateStatusRef =toRef(props, 'communicateType');
    //創一個裝query結果的空物件
    const proad0101communicateStatusRef = ref(null);
    // const communicateStatusRef =ref(null);
    const proInnovativeProductMainDTORef = toRef(props, 'proInnovativeProductMainDTO');
    const proCommunicatedDTOsRef = toRef(props, 'proCommunicatedDTOs');

    watch(
      cmdataRef,
      () => {
        console.log('cmdataRef======', cmdataRef.value);
        //因為cmdataRef.value.communicateStatus直接吃到null值會報錯 所以要加上條件判斷 cmdataRef.value.proInnovativeProductMainDTO.statusOrgSuggestion
        if (cmdataRef.value) {
          // console.log("cmdataRef.value==", cmdataRef.value.proInnovativeProductMainDTO.writeCondition)
          if (
            cmdataRef.value.proInnovativeProductMainDTO.statusInnovativeProduct === '1' &&
            cmdataRef.value.proInnovativeProductMainDTO.statusOrgSuggestion === '0' &&
            cmdataRef.value.proInnovativeProductMainDTO.writeCondition === '1'
          ) {
            proad0101communicateStatusRef.value = cmdataRef.value.cmdata;
            // console.log("proad0101communicateStatusRef.value", proad0101communicateStatusRef.value)
            // communicateStatusRef.value = cmdataRef.value.communicateStatus
            //除了以交流狀態外都會變NULL
          } else {
            proad0101communicateStatusRef.value = null;
            // communicateStatusRef.value =null;
          }
        }
      },
      { immediate: true, deep: true }
    );

    const formOptions = reactive({
      communicateType: [
        { value: '0', text: '會議' },
        { value: '1', text: '現勘' },
        { value: '2', text: '公開示範說明會' },
        { value: '3', text: '依公共工程創新產品交流平台試辦作業要點第12點規定轉入' },
      ],
    });

    //帶出Options內的資料
    const formOptionsFormatter = (option: string, value: string) => {
      let itemFound = formOptions[option].find((item: any) => item.value === value);
      if (itemFound) {
        return itemFound.text;
      } else {
        return '';
      }
    };

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

    return {
      formStatusEnum,
      roleEnum,
      formStatusRef,
      roleRef,
      // $v,
      // reset,
      // validateState,
      proad0101communicateStatusRef,
      formatDateTime,
      formOptionsFormatter,
      formOptions,
      proInnovativeProductMainDTORef,
      proCommunicatedDTOsRef,
    };
  },
  filters: {
    dateToString: (value: Date) => formatDate(value, '/'),
  },
};
</script>

<style scoped></style>

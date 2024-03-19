<template>
  <b-container>
    <!-- {{dataArray}} -->
    <i-table
      small
      border0
      show-filter
      size="sm"
      per-page="10"
      :bordered="false"
      :isServerSidePaging="false"
      :items="dataArray"
      :fields="table.fields"
      :filter-ignored-fields="['no', 'subjectList']"
    >
      <template #cell(remark)="row">
        <b-form-textarea v-model.trim="row.item.remark" rows="2"></b-form-textarea>
        <!-- <i-form-input
          v-model="row.item.remark"
          :key="row.item.no"
          :rules="rules.remark"
        ></i-form-input> -->
        <!-- <b-form-textarea
          v-model="row.item.remark"
          :key="row.item.no"
          :state="vState(row.item.remark)"
          rows="2"
        ></b-form-textarea> -->
      </template>
    </i-table>
  </b-container>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import _ from 'lodash';
import { watch, reactive, ref } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required } from '@/shared/validators';

export default {
  name: 'Eng0202EngrRoster',
  props: {
    engYearreport: {
      type: Object,
      required: false,
    },
  },
  setup(props) {

    const formOptions = reactive({
      subject: [
        { value: '1', text: '土木工程' },
        { value: '2', text: '水利工程' },
        { value: '3', text: '結構工程' },
        { value: '4', text: '大地工程' },
        { value: '5', text: '水土保持' },
        { value: '6', text: '採礦工程' },
        { value: '7', text: '應用地質' },
      ],
    });

    const optionsFormatter = (option: string, value: string) => {
      let valueArray: string[] = value.split(',');
      let newValueArray: string[] = [];
      valueArray.forEach((element: string) => {
        let itemFound = formOptions[option].find((item: any) => item.value === element);
        if (itemFound) {
          newValueArray.push(itemFound.text);
        }
      });
      return newValueArray.join('、');
    };

    const table = reactive({
      fields: [
        // {
        //   // 序號
        //   key: 'no',
        //   label: i18n.t('label.no'),
        //   sortable: false,
        //   thClass: 'text-center',
        //   tdClass: 'text-left align-middle',
        // },
        {
          // 技師姓名
          key: 'engrName',
          label: i18n.t('label.engrName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 身分證字號
          key: 'engrIdno',
          label: i18n.t('label.idno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 執業執照字號
          key: 'engrLicenseNo',
          label: i18n.t('label.engrLicenseNo'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 科別
          key: 'subjectList',
          label: i18n.t('label.subject'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => optionsFormatter('subject', value),
        },
        {
          // 備註
          key: 'remark',
          label: i18n.t('label.remark'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
    });

    let dataArrayDefault = [];
    const dataArray = ref([]);

    watch(() => props.engYearreport, (newVal) => {
      // console.log("engr-roster watch!!!!!!!!!!")
      dataArray.value = [];
      if(newVal && newVal['engYearreportEngr'] instanceof Array) {
        dataArrayDefault = _.cloneDeep(newVal['engYearreportEngr']);
        for(let i = 0; i < newVal['engYearreportEngr'].length; i++) {
          dataArray.value[i] = reactive(Object.assign({}, newVal['engYearreportEngr'][i]));
        }
      }
    },{immediate: true, deep: true});

    const reset = () => {
      for(let i = 0; i < dataArrayDefault.length; i++){
        Object.assign(dataArray.value[i], dataArrayDefault[i]);
      }
    };

    const putForm = () => {
      return dataArray.value;
    }

    let data = {
      no: '1',
      engrName: '蘇玫心',
      engrIdno: 'P220723812',
      engrLicenseNo: '技執字第002151號',
      subjectList: '3,5',
      remark: '又擊斃俄指揮官！烏克蘭打退坦克軍團 畫面超震撼',
    };
    let tableData: object[] = [];
    for (let i = 1; i <= 200; i++) {
      let newData = Object.assign({}, data);
      newData.no = i.toString();
      newData.engrName = newData.engrName + i;
      tableData.push(newData);
    }
    table.data = tableData;

    const rules = {
      remark: {
        notNull: required,
      },
    };

    const vState = (v: any) => {
      // const { $v } = useValidation(rules, ref({ remark: v }), { remark: '' });
      // return $v.value.remark;
      const form = reactive({
        validationInput: v,
      });
      const rules = {
        validationInput: {notNull: required},
      };
      const { $v } = useValidation(rules, form, { validationInput: '' });
      return validateState($v.value.validationInput);
    }

    return {
      dataArray,
      reset,
      putForm,
      validateState,
      table,
      rules,
      vState
    };
  },
};
</script>

<style></style>

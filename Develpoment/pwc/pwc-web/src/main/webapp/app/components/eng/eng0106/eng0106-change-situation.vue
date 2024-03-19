<template>
  <b-container>
    <i-table
      small
      border0
      show-filter
      size="sm"
      per-page="10"
      :bordered="false"
      :isServerSidePaging="false"
      :items="engYearreport.changeSituation? engYearreport.changeSituation: null"
      :fields="table.fields"
      :filter-ignored-fields="['no', 'memberTitle', 'status', 'inDate']"
    >
    </i-table>
  </b-container>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import { reactive } from '@vue/composition-api';
import { parseRocDate, formatDate } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'Eng0202ChangeSituation',
  props: {
    engYearreport: {
      type: Object,
      required: false,
    },
  },
  setup() {
    const formOptions = reactive({
      memberTitle: [
        { value: '01', text: '董事長' },
        { value: '02', text: '董事' },
        { value: '03', text: '監察人' },
        { value: '04', text: '經理人兼技師' },
        { value: '05', text: '獨立董事' },
        { value: '06', text: '經理人' },
        { value: '07', text: '總經理' },
        { value: '08', text: '法人董事' },
        { value: '09', text: '股東' },
        { value: '10', text: '土木技師' },
        { value: '11', text: '負責人' },
        { value: '12', text: '經理' },
        { value: '13', text: '負責人兼技師' },
        { value: '14', text: '協理' },
        { value: '15', text: '公司負責人' },
        { value: '16', text: '副總經理' },
        { value: '17', text: '負責技術部門經理人' },
      ],
      status: [
        { value: '0', text: '離職' },
        { value: '1', text: '任職' },
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
          // 姓名
          key: 'name',
          label: i18n.t('label.name'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 身分證字號
          key: 'memberIdno',
          label: i18n.t('label.memberIdno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 職稱
          key: 'memberTitle',
          label: i18n.t('label.memberTitle'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => optionsFormatter('memberTitle', value),
        },
        {
          // 狀態
          key: 'status',
          label: i18n.t('label.status'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => optionsFormatter('status', value),
        },
        {
          // 入會(或任職)日期
          key: 'inDate',
          label: i18n.t('label.inDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: Date) => formatDate(value, '/'),
        },
      ],
    });

    let data = [
      {
        name: '巫哲緯',
        memberIdno: 'B120518158',
        memberTitle: '02',
        status: '1',
        inDate: parseRocDate('108/11/15', '/'),
      },
      {
        name: '林忠機',
        memberIdno: 'Y120051218	',
        memberTitle: '03',
        status: '0',
        inDate: parseRocDate('107/04/23', '/'),
      },
      {
        name: '金玉瑩',
        memberIdno: 'F220879607',
        memberTitle: '04',
        status: '1',
        inDate: parseRocDate('109/10/03', '/'),
      },
    ];
    let tableData: object[] = [];
    for (let i = 0; i < 10; i++) {
      for (let j = 0; j < data.length; j++) {
        let newData: object = Object.assign({}, data[j]);
        // newData['no'] = (i * data.length + j + 1).toString();
        tableData.push(newData);
      }
    }

    return {
      table,
      tableData
    };
  },
};
</script>

<style></style>

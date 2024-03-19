<template>
  <div>
    <div class="card">
      <div class="card-header py-1 text-left">
        <h5 class="m-0">全國工程施工查核小組聯絡人名單</h5>
      </div>
      <div class="card-body col-11">
        <b-table hover :items="contactList" :fields="fields"></b-table>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { reactive } from '@vue/composition-api';
export default {
  name: 'bidAbb001ContactList',
  setup(props, context) {
    const contactList = [
      { no: 1, wkutName: '內政部', contactPerson: '許何誠', title: '幹事', tel: '02-23565151' },
      { no: 2, wkutName: '外交部', contactPerson: '郭育綺', title: '助理員', tel: '02-23482014' },
      { no: 3, wkutName: '國防部', contactPerson: '劉金珠', title: '技士', tel: '02-21166117#637501' },
      { no: 4, wkutName: '財政部', contactPerson: '郭德福', title: '專員', tel: '02-23227583' },
      { no: 5, wkutName: '教育部', contactPerson: '王清松', title: '技正', tel: '02-77366060' },
      { no: 6, wkutName: '法務部', contactPerson: '廖偉智', title: '技正', tel: '02-21910189#2635' },
      { no: 7, wkutName: '經濟部', contactPerson: '黃旭暉', title: '科長', tel: '02-23713161#651' },
      { no: 8, wkutName: '交通部', contactPerson: '陳慧雯', title: '幫工程司', tel: '02-23492407' },
      { no: 9, wkutName: '蒙藏委員會', contactPerson: '黃潔瑩', title: '科員', tel: '02-23566436' },
      { no: 10, wkutName: '僑務委員會', contactPerson: '黃菁萍', title: '科員', tel: '02-23272608' },
      { no: 11, wkutName: '行政院主計處', contactPerson: '吳秀珍', title: '視察', tel: '02-33567548' },
      { no: 12, wkutName: '行政院人事行政總處', contactPerson: '張素萍', title: '專員', tel: '02-23979298#722' },
      { no: 13, wkutName: '衛生福利部', contactPerson: '朱玉', title: '專員', tel: '02-85906591' },
      { no: 14, wkutName: '國軍退除役官兵輔導委員會', contactPerson: '楊振宗', title: '技士', tel: '02-27571381' },
      { no: 15, wkutName: '國立故宮博物院', contactPerson: '凱獻', title: '技士', tel: '02-28812021#2751' },
      { no: 16, wkutName: '行政院原子能委員會', contactPerson: '張乃懿', title: '專員', tel: '02-2232253' },
      { no: 17, wkutName: '科技部', contactPerson: '賴志弘', title: '博士後研究', tel: '02-27377459' },
      { no: 18, wkutName: '國家發展委員會', contactPerson: '陳志信', title: '技士', tel: '02-23165444' },
      { no: 19, wkutName: '文化部', contactPerson: '梁文明', title: '專員', tel: '02-85126240' },
      { no: 20, wkutName: '行政院農業委員會', contactPerson: '吳耀琪', title: '秘書', tel: '02-23126956' },
      { no: 21, wkutName: '中央銀行', contactPerson: '李茂銘', title: '副科長', tel: '02-23571567' },
      { no: 22, wkutName: '中央選舉委員會', contactPerson: '謝文華', title: '專員', tel: '02-23565469' },
      { no: 23, wkutName: '金融監督管理委員會', contactPerson: '邱惠茹', title: '專員', tel: '02-89680122' },
      { no: 24, wkutName: '勞動部', contactPerson: '許淑品', title: '科長', tel: '02-85902968' },
      { no: 25, wkutName: '行政院環境保護署', contactPerson: '蔡進偉', title: '技士', tel: '04-22521718#5384' },
      { no: 26, wkutName: '原住民族委員會', contactPerson: '李明偉', title: '技士', tel: '02-89953237' },
      { no: 27, wkutName: '海洋委員會', contactPerson: '黃狄晟', title: '科員', tel: '02-22399277#266445' },
      { no: 28, wkutName: '客家委員會', contactPerson: '李文端', title: '科員', tel: '89956988-586' },
      { no: 29, wkutName: '國家通訊傳播委員會', contactPerson: '陳桂茂', title: '視察', tel: '02-33438722' },
      { no: 30, wkutName: '臺北市政府', contactPerson: '葉逸漩', title: '技佐', tel: '02-27817969#140' },
      { no: 31, wkutName: '高雄市政府', contactPerson: '趙玉娟', title: '約僱人員', tel: '07-3368333#2119' },
      { no: 32, wkutName: '新北市政府', contactPerson: '王智民', title: '技士', tel: '02-29603456#5570' },
      { no: 33, wkutName: '宜蘭縣政府', contactPerson: '簡淑美', title: '技術員', tel: '03-9251000#1017' },
      { no: 34, wkutName: '桃園市政府', contactPerson: '黃煒凱', title: '工程員', tel: '03-3322101#6762' },
      { no: 35, wkutName: '新竹縣政府', contactPerson: '張文華', title: '約僱', tel: '03-5518101#2603' },
      { no: 36, wkutName: '苗栗縣政府', contactPerson: '李宛諭', title: '幹事', tel: '037-559486' },
      { no: 37, wkutName: '彰化縣政府', contactPerson: '李仁豪', title: '技士', tel: '04-7531932' },
      { no: 38, wkutName: '南投縣政府', contactPerson: '鄭詩涵', title: '技士', tel: '049-2246057' },
      { no: 39, wkutName: '雲林縣政府', contactPerson: '謝旻洲', title: '科長', tel: '05-5523227' },
      { no: 40, wkutName: '嘉義縣政府', contactPerson: '黃智暉', title: '技佐', tel: '05-3622712#216' },
      { no: 41, wkutName: '屏東縣政府', contactPerson: '曾柏碩', title: '技士', tel: '08-7320415#2801' },
      { no: 42, wkutName: '臺東縣政府', contactPerson: '黃亮杰', title: '技士', tel: '089-327249' },
      { no: 43, wkutName: '花蓮縣政府', contactPerson: '周怡常', title: '技士', tel: '03-8242059' },
      { no: 44, wkutName: '澎湖縣政府', contactPerson: '莊穎儒', title: '技士', tel: '06-9270669' },
      { no: 45, wkutName: '基隆市政府', contactPerson: '戴玉華', title: '約僱人員', tel: '02-24261490' },
      { no: 46, wkutName: '新竹市政府', contactPerson: '李姿瑩', title: '工程師', tel: '03-5245314' },
      { no: 47, wkutName: '台中市政府', contactPerson: '蔡逸智', title: '組員', tel: '04-22289111#21903' },
      { no: 48, wkutName: '嘉義市政府', contactPerson: '陸兆惠', title: '技士', tel: '05-2229165' },
      { no: 49, wkutName: '臺南市政府', contactPerson: '詹景帆', title: '工程員', tel: '06-2991111' },
      { no: 50, wkutName: '金門縣政府', contactPerson: '林仲輝', title: '約聘人員', tel: '082-312781' },
      { no: 51, wkutName: '連江縣政府', contactPerson: '陳其鑣', title: '約僱人員', tel: '0836-25398#53' },
    ];
    // const contactList = reactive([]);
    // contactList.splice(0, contactList.length, dataList);

    const fields = [
      {
        key: 'no',
        label: '編號',
        sortable: false,
        thStyle: 'width:5%',
        thClass: 'text-center',
        tdClass: 'text-right align-middle',
      },
      {
        key: 'wkutName',
        label: '機關名稱',
        sortable: false,
        thStyle: 'width:20%',
        thClass: 'text-center',
        tdClass: 'text-left align-middle',
      },
      {
        key: 'contactPerson',
        label: '聯絡人',
        sortable: false,
        thStyle: 'width:20%',
        thClass: 'text-center',
        tdClass: 'text-left align-middle',
      },
      {
        key: 'title',
        label: '職稱',
        sortable: false,
        thStyle: 'width:20%',
        thClass: 'text-center',
        tdClass: 'text-left align-middle',
      },
      {
        key: 'tel',
        label: '電話',
        sortable: false,
        thStyle: 'width:20%',
        thClass: 'text-center',
        tdClass: 'text-left align-middle',
      },
    ];

    return { contactList, fields };
  },
};
</script>

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
      :items="table.domesticCase"
      :fields="table.fields"
      :filter-ignored-fields="['engYearreportCaseId', 'price', 'yearCompletePrice', 'completionRatio', 'action']"
      :filter-included-fields="['caseName', 'clientName']"
      class="mb-5"
    >
      <template #table-top>
        <b-row>
          <b-col cols="6">
            <h3 class="font-weight-bold">國內案件</h3>
          </b-col>
          <b-col cols="6" align="right">
            <i-button type="file-earmark-plus" @click="showCaseDetailModal(null)"></i-button>
          </b-col>
        </b-row>
      </template>
      <template #cell(completionRatio)="row">
        {{ isNaN(Math.round(row.item.yearCompletePrice / row.item.price)) ?
           '' : Math.round((row.item.yearCompletePrice / row.item.price) * 100) + '%' }}
      </template>
      <template #cell(action)="row">
        <i-button class="mb-1" size="sm" type="binoculars" @click="showCaseDetailModal(row.item)"></i-button>
        <br />
        <i-button class="mb-1" size="sm" type="trash" @click="deleteCase(row.item.engYearreportCaseId)"></i-button>
      </template>
    </i-table>

    <hr class="mb-5"/>

    <i-table
      small
      border0
      show-filter
      size="sm"
      per-page="10"
      :bordered="false"
      :isServerSidePaging="false"
      :items="table.foreignCase"
      :fields="table.fields"
      :filter-ignored-fields="['engYearreportCaseId', 'price', 'yearCompletePrice', 'completionRatio', 'action']"
    >
      <template #table-top>
        <b-row>
          <b-col cols="6">
            <h3 class="font-weight-bold">國外案件</h3>
          </b-col>
          <b-col cols="6" align="right">
            <i-button type="file-earmark-plus" @click="showCaseDetailModal(null, true)"></i-button>
          </b-col>
        </b-row>
      </template>
      <template #cell(completionRatio)="row">
        {{ isNaN(Math.round(row.item.yearCompletePrice / row.item.price)) ?
           '' : Math.round((row.item.yearCompletePrice / row.item.price) * 100) + '%' }}
      </template>
      <template #cell(action)="row">
        <i-button class="mb-1" size="sm" type="binoculars" @click="showCaseDetailModal(row.item)"></i-button>
        <br />
        <i-button class="mb-1" size="sm" type="trash" @click="deleteCase(row.item.engYearreportCaseId)"></i-button>
      </template>
    </i-table>

    <b-modal
      id="case-detail-modal"
      size="xl"
      title="案件維護"
      header-bg-variant="secondary"
      header-text-variant="light"
      @ok="handleOk"
      @cancel="handleCancel"
    >
      <eng-0202-service-case-detail
        :engYearreportCaseDetail="engYearreportCaseDetailRef"
        :engYearreportEngr="engYearreportEngrRef"
        :isForeignCase="isForeignCaseRef"
        ref="eng0202ServiceCaseDetail"
      />
      <template #modal-footer="{ ok, cancel }">
        <i-button type="save" @click="ok()"></i-button>
        <i-button type="x-circle" @click="modalReset()"></i-button>
        <i-button type="arrow-counterclockwise" @click="cancel()"></i-button>
        <!-- Button with custom close trigger value -->
        <!-- <b-button size="sm" variant="outline-secondary" @click="hide('forget')">
          Forget it
        </b-button> -->
      </template>
    </b-modal>
  </b-container>
</template>

<script lang="ts">
import i18n from '@/shared/i18n';
import { watch, reactive, ref, unref } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { commaFormatter } from '@/shared/formatter/common';
import { BvModal, BvModalEvent } from 'bootstrap-vue';
import Eng0202ServiceCaseDetail from '@/components/eng/eng0202/eng0202-service-case-detail.vue';
import _ from 'lodash';

export default {
  name: 'Eng0202ServiceCase',
  props: {
    engYearreport: {
      type: Object,
      required: false,
    },
  },
  components: {
    Eng0202ServiceCaseDetail,
  },
  setup(props) {
    const eng0202ServiceCaseDetail = ref(null);

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
          // 標案名稱
          key: 'caseName',
          label: i18n.t('label.caseName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '30%' },
        },
        {
          // 案主姓名
          key: 'clientName',
          label: i18n.t('label.clientName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '15%' },
        },
        {
          // 服務合約金額
          key: 'price',
          label: i18n.t('label.serviceCasePrice'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: number) => commaFormatter(value)
        },
        {
          // 本年度完成金額
          key: 'yearCompletePrice',
          label: i18n.t('label.yearCompletePrice'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          formatter: (value: number) => commaFormatter(value)
        },
        {
          // 完成比例
          key: 'completionRatio',
          label: i18n.t('label.completionRatio'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'action',
          label: '',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      domesticCase: undefined,
      foreignCase: undefined,
    });

    let engYearreportCaseDefault = [];
    const engYearreportCaseRef = ref([]);
    const engYearreportEngrRef = ref([]);

    watch(() => props.engYearreport, (newVal) => {
      // console.log("service-case watch!!!!!!!!!!")
      engYearreportCaseDefault = [];
      engYearreportCaseRef.value = [];
      if(newVal && newVal['engYearreportCase'] instanceof Array) {
        engYearreportCaseDefault = _.cloneDeep(newVal['engYearreportCase']);
        for(let i = 0; i < newVal['engYearreportCase'].length; i++){
          engYearreportCaseRef.value[i] = reactive(Object.assign({}, newVal['engYearreportCase'][i]));
        }
      }
      if(newVal && newVal['engYearreportEngr'] instanceof Array) {
        engYearreportEngrRef.value = newVal['engYearreportEngr'];
      }
    },{immediate: true, deep: true});

    watch(engYearreportCaseRef, (newVal) => {
      table.domesticCase = reactive(newVal.filter(item => Number(item.isForeignCase) !== 1));
      table.foreignCase = ref(newVal.filter(item => Number(item.isForeignCase) === 1));
    },{immediate: true});

    const $bvModal: BvModal = useBvModal();

    const engYearreportCaseDetailRef = ref(null);
    const isForeignCaseRef = ref(null);

    const showCaseDetailModal = (engYearreportCaseDetail: any, isForeignCase?: boolean) => {
      engYearreportCaseDetailRef.value = engYearreportCaseDetail;
      isForeignCaseRef.value = isForeignCase;
      $bvModal.show('case-detail-modal');
    };

    const handleOk = (bvModalEvt: BvModalEvent) => {
      bvModalEvt.preventDefault();
      eng0202ServiceCaseDetail.value.checkValidity().then((isValid: boolean) => {
        if (isValid) {
          const data = eng0202ServiceCaseDetail.value.sendData();
          if(unref(engYearreportCaseRef).find(item => item.engYearreportCaseId === data.engYearreportCaseId)){
            engYearreportCaseRef.value = unref(engYearreportCaseRef).map( item => {
              if(item.engYearreportCaseId === data.engYearreportCaseId) return data
              else return item
            });
          }else {
            data.engYearreportCaseId = unref(engYearreportCaseRef).length + 1;
            unref(engYearreportCaseRef).push(data);
          }
          $bvModal.hide('case-detail-modal');
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行儲存。');
        }
      });
    };

    const handleCancel = () => {
    };

    const deleteCase = (engYearreportCaseId: string) => {
      engYearreportCaseRef.value = unref(engYearreportCaseRef).filter(item => item.engYearreportCaseId !== engYearreportCaseId);
      engYearreportCaseRef.value.forEach((item, index) => item.engYearreportCaseId = index + 1);
    };

    const reset = () => {
      engYearreportCaseRef.value = [];
      for(let i = 0; i < engYearreportCaseDefault.length; i++){
        engYearreportCaseRef.value[i] = reactive(Object.assign({}, engYearreportCaseDefault[i]));
      }
    };

    const modalReset = () => {
      eng0202ServiceCaseDetail.value.modalReset();
    };

    const putForm = () => {
      return unref(engYearreportCaseRef);
    }

    return {
      eng0202ServiceCaseDetail,
      table,
      showCaseDetailModal,
      engYearreportCaseRef,
      engYearreportCaseDetailRef,
      isForeignCaseRef,
      engYearreportEngrRef,
      handleOk,
      handleCancel,
      deleteCase,
      reset,
      modalReset,
      putForm
    };
  },
};
</script>

<style></style>

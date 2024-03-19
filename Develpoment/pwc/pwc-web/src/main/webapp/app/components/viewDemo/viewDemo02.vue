<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header p-1 text-left">
          <div class="d-flex justify-content-between align-items-center">
            <div>
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>

            <b-button class="float-right" @click="stepVisible = !stepVisible">
              <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
              <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
            </b-button>
          </div>
        </div>
        <div class="card-body col-10">
          <b-collapse v-model="stepVisible">
            <!-- row1 -->
            <b-form-row>
              <i-form-group-check :label="'公會類型：'">
                <b-form-select v-model="$v.type.$model" :options="queryOptions.type"></b-form-select>
              </i-form-group-check>
              <i-form-group-check :label="'公會名稱：'">
                <b-form-input v-model.trim="$v.name.$model"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!-- row2 -->
            <b-form-row>
              <i-form-group-check :label="'理事長：'">
                <b-form-input v-model.trim="$v.chairman.$model"></b-form-input>
              </i-form-group-check>
              <i-form-group-check :label="'秘書長：'">
                <b-form-input v-model.trim="$v.secretaryGeneral.$model"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <!-- row3 -->
            <b-form-row>
              <i-form-group-check :label="$t('label.status')">
                <b-form-select v-model="$v.useStatus.$model" :options="queryOptions.useStatus"></b-form-select>
              </i-form-group-check>
              <b-col cols="2"></b-col>
              <b-col offset-sm="0" cols="4">
                <!-- @click="handleQuery" -->
                <i-button type="search" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>
    <!-- v-if="queryStatus" -->
    <section class="container mt-3">
      <div class="container">
        <!-- :itemsUndefinedBehavior="'loading'" -->
        <i-table
          class="table-sm"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :fields="table.fields"
          :totalItems="table.totalItems"
          :is-server-side-paging="true"
        >
          <!-- @changePagination="handlePaginationChanged($event)" -->
          <template #cell(action)="row">
            <b-button size="sm" variant="outline-secondary" @click="toEditView(row.item)">查看</b-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { ref, toRef, reactive } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { guildTypeFormatter } from '@/shared/formatter/common';
import i18n from '@/shared/i18n';
import axios from 'axios';
export default {
  name: 'viewDemo02',
  props: {
    isReload: {
      type: Boolean,
      required: false,
      default: false,
    },
  },
  setup(props, context) {
    const isReloadProp = toRef(props, 'isReload');
    const formDefault = {
      type: '', //公會類型
      name: '', //公會名稱
      chairman: '', //理事長
      secretaryGeneral: '', //秘書長
      useStatus: '', // 啟用狀態
    };
    const form = reactive(Object.assign({}, formDefault));
    const rules = {
      type: {},
      name: {},
      chairman: {},
      useStatus: {},
      secretaryGeneral: {},
    };
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 查詢結果及狀態
    const queryStatus = ref(false);

    // 下拉選單選項
    const queryOptions = reactive({
      type: [
        { value: '', text: '不拘' },
        { value: '1', text: '公會' },
        { value: '2', text: '全聯會' },
        { value: '3', text: '顧問公會' },
      ],
      useStatus: [
        { value: '', text: '不拘' },
        { value: '1', text: '啟用' },
        { value: '0', text: '停用' },
      ],
    });

    const table = reactive({
      fields: [
        {
          key: 'id',
          label: '代號',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'type',
          label: i18n.t('label.ogn.guildType'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: value => guildTypeFormatter(value),
        },
        {
          key: 'name',
          label: i18n.t('label.ogn.guildName'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'chairman',
          label: i18n.t('label.chairman'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'secretaryGeneral',
          label: '秘書長',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'tel1',
          label: '電話',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: '',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const stepVisible = ref(true);

    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/cms-comps/criteria-jpa', form)
            .then(({ data }) => {
              //  console.log(response.data.component);
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .catch(error => {
              console.log(error);
            });
        }
      });
    };

    const toEditView = rowData => {
      console.log(rowData);
    };

    return {
      $v,
      stepVisible,
      queryOptions,
      table,
      toEditView,
      reset,
      handleQuery,
    };
  },
};
</script>

<style></style>

<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <b-form-group class="col-sm-5" label-cols-md="3" content-cols-md="9" label="公會類型" label-align-md="right">
                <b-form-select v-model="$v.type.$model" :options="queryOptions.type"></b-form-select>
              </b-form-group>
              <b-form-group class="col-sm-5" label-cols-md="3" content-cols-md="9" label="科別" label-align-md="right">
                <b-form-select v-model="$v.type.$model" :options="queryOptions.type"></b-form-select>
              </b-form-group>
            </b-form-row>
            <b-form-row>
              <b-form-group class="col-sm-5" label-cols-md="3" content-cols-md="9" label="公會名稱" label-align-md="right">
                <b-form-input v-model.trim="$v.name.$model"></b-form-input>
              </b-form-group>
              <b-form-group class="col-sm-5" label-cols-md="3" content-cols-md="9" label="理事長" label-align-md="right">
                <b-form-input v-model.trim="$v.chairman.$model"></b-form-input>
              </b-form-group>
            </b-form-row>
            <b-form-row>
              <b-form-group class="col-sm-5" label-cols-md="3" content-cols-md="9" label="狀態" label-align-md="right">
                <b-form-select v-model="$v.useStatus.$model" :options="queryOptions.useStatus"></b-form-select>
              </b-form-group>
              <b-col offset-sm="5" cols="2">
                <b-button size="sm" variant="outline-secondary" v-promise-btn="{ promise: dataPromise }" @click="queryHandler"
                  >查詢</b-button
                >
                <b-button size="sm" variant="outline-secondary" @click="reset">清除</b-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>
    <section class="container mt-3" v-if="queryStatus">
      <div class="card">
        <div class="card-header py-1">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                系統資料區
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible.result = !stepVisible.result">
                <font-awesome-icon v-if="stepVisible.result" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.result" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse id="collapse-5" class="px-1 py-1" v-model="stepVisible.result">
            <i-table class="table-sm" itemsUndefinedBehavior="loading" :items="table.data" :fields="table.fields">
              <template #cell(action)="row">
                <b-button size="sm" variant="outline-secondary" @click="toEditView(row.item)">查看</b-button>
              </template>
            </i-table>
          </b-collapse>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation } from '@/shared/form';
import { computed, onActivated, reactive, ref } from '@vue/composition-api';
import { guildTypeFormatter } from '@/shared/formatter/common';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { CmsComp } from '@/shared/model/cms-comp.model';

export default {
  name: 'Ogn01Query',
  props: {
    isReload: {
      type: Boolean,
      required: true,
    },
  },
  setup(props, context) {
    onActivated(() => {
      if (isReloadProp.value) {
        queryHandler();
      }
    });

    const isReloadProp = computed(() => props.isReload);

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      type: '', //公會類型
      name: '', //公會名稱
      chairman: '', //理事長
      useStatus: '', // 啟用狀態
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      type: {},
      name: {},
      chairman: {},
      useStatus: {},
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      result: true,
    });

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

    // 查詢結果及狀態
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    // modal物件
    const $bvModal = useBvModal();

    const table = reactive({
      fields: [
        {
          key: 'id',
          label: '代號',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
        {
          key: 'type',
          label: '公會類型',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          formatter: (value: string) => guildTypeFormatter(value),
        },
        {
          key: 'name',
          label: '公會名稱',
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'chairman',
          label: '理事長',
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
          label: '動作',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
    });

    const queryHandler = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          queryStatus.value = true;
          dataPromise.value = axios
            .post('/cms-comps/criteria', form)
            .then(({ data }) => {
              table.data = [];
              table.data.splice(0, table.data.length, ...data);
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const toEditView = (cmsComp: CmsComp) => {
      context.emit('changeComponent', 'Ogn01EditInfo', cmsComp);
    };

    const deleteCmsComp = id => {
      $bvModal.msgBoxConfirm(`是否確認刪除公會${id}通訊資料？`).then((isOK: boolean) => {
        if (isOK) {
          console.log('實作刪除');
        }
      });
    };

    return { $v, stepVisible, queryOptions, queryStatus, dataPromise, table, reset, queryHandler, toEditView, deleteCmsComp };
  },
};
</script>
<style>
.col-form-label {
  background-color: #737373;
  color: white;
  font-weight: bolder;
}
</style>

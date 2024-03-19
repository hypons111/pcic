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
              
            </div>
          </div>
        </div>
        <div class="card-body">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <i-form-group-check class="col-12" label-cols-md="2" content-cols-md="8" :label="'委員狀態:'" label-align-md="right">
                  
            <b-form-radio v-model="selected" value="A">有效</b-form-radio>
            <b-form-radio v-model="selected" value="B">全部</b-form-radio>
 
                      
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check
                class="col-12"
                label-cols-md="2"
                content-cols-md="8"
                :label="'推薦機關:'"
                label-align-md="right"
              >
                <b-form-input class="col-4" v-model="$v.name.$model" trim></b-form-input>
                
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check
                class="col-12"
                label-cols-md="2"
                content-cols-md="8"
                :label="'姓名關鍵字:'"
                label-align-md="right"
              >
                <b-form-input class="col-4" v-model="$v.name.$model" trim></b-form-input>
                
              </i-form-group-check>
            </b-form-row>
             <b-form-row>
              <i-form-group-check
                class="col-12"
                label-cols-md="2"
                content-cols-md="8"
                :label="'主次要專長:'"
                label-align-md="right"
              >
                <b-form-input class="col-4" v-model="$v.name.$model" trim></b-form-input>
                
              </i-form-group-check>
            </b-form-row>
             <b-form-row>
              <i-form-group-check
                class="col-12"
                label-cols-md="2"
                content-cols-md="8"
                :label="'特殊專長:'"
                label-align-md="right"
              >
                <b-form-input class="col-4" v-model="$v.name.$model" trim></b-form-input>
                
              </i-form-group-check>
            </b-form-row>
             <b-form-row>
              <i-form-group-check
                class="col-12"
                label-cols-md="2"
                content-cols-md="8"
                :label="'其他特殊經驗:'"
                label-align-md="right"
              >
                <b-form-input class="col-4" v-model="$v.name.$model" trim></b-form-input>
                
              </i-form-group-check>
            </b-form-row>
              <b-form-row>
              <i-form-group-check
                class="col-12"
                label-cols-md="2"
                content-cols-md="8"
                :label="'現職縣市:'"
                label-align-md="right"
              >
                <b-form-input class="col-4" v-model="$v.name.$model" trim></b-form-input>
                
              </i-form-group-check>
            </b-form-row>
              <b-form-row>
              <i-form-group-check
                class="col-12"
                label-cols-md="2"
                content-cols-md="8"
                :label="'性別:'"
                label-align-md="right"
              >
                <b-form-input class="col-4" v-model="$v.name.$model" trim></b-form-input>
                
              </i-form-group-check>
            </b-form-row>
              <b-form-row>
              <i-form-group-check
                class="col-12"
                label-cols-md="2"
                content-cols-md="8"
                :label="'有效日期區間:'"
                label-align-md="right"
              >
                <i-dual-date-picker ></i-dual-date-picker>

                
              </i-form-group-check>
              
            </b-form-row>
            <b-form-row>
              <b-col align="center">
                <i-button type="search" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>
    <section class="mt-2">
      <div v-if="stepVisible.step2" class="container">
        <i-table
          class="table-sm"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :fields="table.fields"
          :totalItems="table.totalItems"
          :is-server-side-paging="true"
          @changePagination="handlePaginationChanged($event)"
        >
          <template #cell(action)="row">
            <i-button type="binoculars" @click="toSee(row.item)"></i-button>
            <i-button type="pencil-square" @click="toEdit(row.item)"></i-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import BidCommonService from '@/components/bid/bidService/bid-common.service';
import VwBidProjectService from '@/components/bid/bidService/vw-bid-project.service';
import { navigateByNameAndParams } from '@/router/router';
import { useValidation } from '@/shared/form';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import i18n from '@/shared/i18n';
import { Pagination } from '@/shared/model/pagination.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { reactive, ref } from '@vue/composition-api';

export default {
  name: 'bidAkc001Menu',
  props: {},
  setup(props, context) {
    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      code: '', // 機關代碼
      name: '', // 機關名稱
    };

    // 表單物件驗證規則
    const rules = {
      code: {}, // 機關代碼
      name: {}, // 機關名稱
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
      step2: false,
    });

    let selected = ref('A');

    enum FormStatusEnum {
      READONLY = '檢視',
      MODIFY = '編輯',
      CREATE = '新增',
    }

    // 查詢結果及狀態
    const dataPromise = ref(null);

    const table = reactive({
      fields: [
        {
          //推薦機關
          key: 'suggestDepartment',
          label: '推薦機關',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //姓名
          key: 'name',
          label: '姓名',
          sortable: false,
          thStyle:'width:6%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //性別
          key: 'sex',
          label: '性別',
          sortable: false,
          thStyle:'width:6%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //機關名稱
          key: 'agencyName',
          label:'機關名稱',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //職稱
          key: 'memberTitle',
          label:'職稱',
          sortable: false,
          thStyle:'width:6%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //電話
          key: 'tel',
          label:'電話',
          sortable: false,
          thStyle:'width:6%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //行動電話
          key: 'mobileTel',
          label:'行動電話',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //EMAIL
          key: 'email',
          label:'EMAIL',
          sortable: false,
          thStyle:'width:6%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //現職地址
          key: 'addr',
          label:'現職地址',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //主要專長
          key: ' primaryExpertise',
          label:'主要專長',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //特殊專長
          key: 'specialExpertise',
          label:'特殊專長',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //特定專長
          key: 'specificExpertise',
          label:'特定專長',
          sortable: false,
          thStyle:'width:10%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        }
      ],
      data: undefined,
      totalItems: 0,
    });

    //送 query去後端撈資料，渲染到table
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          table.data = undefined;
          stepVisible.step2 = true;
          dataPromise.value = axios
            .post('/bid-unit-10/criteria-jpa', form) //前端分頁(呼叫後端)
            .then(({ data }) => {
              // table.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
              // table.totalItems = data.length; //前端分頁
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
            })
            .finally(() => (dataPromise.value = null))
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const toSee = value => {
      navigateByNameAndParams('bidAbe007Edit', { value, formStatus: FormStatusEnum.READONLY, KeepAlive: true });
    };

    const toEdit = value => {
      console.log(value);
      navigateByNameAndParams('bidAbe007Edit', { value, formStatus: FormStatusEnum.MODIFY, KeepAlive: true });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      $v,
      reset,
      stepVisible,
      dataPromise,
      table,
      handleQuery,
      handlePaginationChanged,
      toEdit,
      toSee,
      selected,
      FormStatusEnum,
    };
  },
};
</script>
<style></style>

<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>
          </div>
        </div>
        <div class="card-body col-11">
          <b-collapse v-model="stepVisible.step1">
            <b-form-row>
              <i-form-group-check
                class="col-md-12"
                label-cols-md="2"
                content-cols-md="10"
                :label="$t('label.communicateNumber')"
                :item="$v.communicateNumber"
              >
                <b-form-input
                  v-model="$v.proInnovativeProductId.$model"
                  :state="validateState($v.proInnovativeProductId)"
                  trim
                  placeholder="請輸入編號"
                ></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <b-col offset="9">
                <i-button type="search" variant="success" @click="handleQuery"></i-button>
                <i-button type="x-circle" @click="reset"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>

    <section class="container mt-2" v-if="queryStatus">
      <i-table
        head-variant="dark"
        class="table-sm"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields_backend"
        :is-server-side-paging="false"
        :totalItems="table.totalItems"
      >
        <template v-slot:cell(status)="row">
          {{ statusValueToText(row.item.status) }}
        </template>

        <template v-slot:cell(writeCondition)="row">
          {{ writeConditionValueToText(row.item.writeCondition) }}
        </template>

        <template #cell(action)="row">
          <i-button size="sm" type="accept-communicate" @click="accept(row.item)"></i-button>
          <i-button size="sm" type="unaccept-communicate" @click="unAccept(row.item)"></i-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { watch, reactive, ref } from '@vue/composition-api';
import { required } from '@/shared/validators';
import { useBvModal } from '@/shared/modal';
import axios from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import i18n from '@/shared/i18n';
import { navigateByNameAndParams } from '@/router/router';



export default {
  name: 'pro0103r2',
  setup() {
    const notificationService = useNotification();

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      proInnovativeProductId: '',
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = reactive({
      proInnovativeProductId: { required: required },
    });

    //$v為表單物件轉換而來，checkValidity是用來做欄位檢核
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    //modal物件，用在bootstrap-vue彈出視窗用
    const $bvModal = useBvModal();


    //查詢表單樣式
    const table = reactive({
      fields_backend: [
        {
          key: 'proInnovativeProductId',
          label: i18n.t('label.communicateNumber'),
          // sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'innovateName',
          label: i18n.t('label.itemName'),
          // sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'writeCondition',
          label: i18n.t('label.writeCondition'),
          // sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'name',
          label: i18n.t('label.companyName'),
          // sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // formatter: (value: string) => queryOptionsFormatter('subject', value),
        },
        {
          key: 'status',
          label: i18n.t('label.communicateSuggestionStatus'),
          // sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'action',
          label: '',
          // sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          // thStyle: { width: '10%' },
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

     const writeCondition = ref([
      { text: '草稿', value: '0' },
      { text: '公開', value: '1' },
    ]);

      const status = ref([
      { text: '不受理交流', value: '0' },
      { text: '受理交流', value: '1' },
    ]);

    function statusValueToText(value) {
      return status.value.find(element => element.value === value).text;
    }


     function writeConditionValueToText(value) {
      return writeCondition.value.find(element => element.value === value).text;
    }

// 丟給pro0103r3接
      function accept(item:item) {

        item.status ='1';
        console.log( item);
           navigateByNameAndParams('pro0103r3', { review: item, isnotkeepAlive: true});
        }

//丟給pro0103r3接
       function unAccept(item:item) {

        item.status ='0';
        console.log('unAccept'item);
           navigateByNameAndParams('pro0103r3', { review: item, isnotkeepAlive: true});
        }


    //查詢
    const  handleQuery = () => {
                checkValidity().then((isValid: boolean) => {
                  if (isValid) {
                    table.data = undefined;
                    queryStatus.value = true;

                    dataPromise.value = axios
                      .post('/pro-innovation/criteria', form) //前端分頁(呼叫後端)
                      .then(({ data }) => {
                      console.log(data);
                        table.data = data.slice(0, data.length); //前端分頁(後端資料回傳)
                        table.totalItems = data.length; //前端分頁
                         //table.data = data.content.slice(0, data.content.length); //後端分頁
                         //table.totalItems = data.totalElements; //後端分頁
                      })
                      .finally(() => (dataPromise.value = null))
                      .catch(notificationErrorHandler(notificationService));
                  }
                });
              };

    return {
      validateState,
      $v,
      stepVisible,
      queryStatus,
      table,
      reset,
      handleQuery,
      status,
      statusValueToText,
      writeConditionValueToText,
      accept,
      unAccept,
    };
  },
};
</script>
<style></style>

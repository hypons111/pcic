<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <div class="d-grid gap-2 col-4 mx-auto" >
                    

                </div>
                

              </h5>
            </div>
            <div class="col-sm-1 p-0">
             
            </div>
          </div>
        </div>
        <div class="card">
          <div class="d-grid gap-2 col-4 mx-auto" >
                          <b-button class="mt-2">
                本機關查核委員推薦
                </b-button>
                <b-button class="mt-2">
                審查小組推薦審查
                </b-button >
                <b-button class="mt-2" @click="navigateByNameAndParams('bidAkc001Query',{})"  >
                查核委員專家名單資料庫
                
                </b-button>
                <b-button class="mt-2">
                查核委員聘用情形
                </b-button>
               </div> 

                <pre>
                <div class="col-8" style="color: red">109年6月10日工程管字第<a href="https://drive.google.com/drive/u/0/folders/1AWkh6pBvVJliZhMLWBmXgW9k7fsD2TTi"><u>1090300405</u></a>號函</div>
                <div class="col-8" style="color: blue">           訂定「<a href="https://chrome.google.com/webstore/category/extensions?hl=zh-TW"><u>工程施工查核委員專家名單資料庫建置及除名作業要點</u></a>」，並自中華民國一百零九年七月一日生效，請查照並轉知所屬
              (轄)機關。</div>
              <div class="col-8" style="color: blue"><a href="https://www.youtube.com/">                  <u>附件一</u></a> 查核委員推薦名單總表</div>
              <div class="col-8" style="color: blue"><a href="https://www.youtube.com/">                  <u>附件二</u></a> 查核委員候選人推薦表</div>
              <div class="col-8" style="color: blue"><a href="https://www.youtube.com/">                  <u>附件三</u></a> 專家學者同意接受工程主管機關推薦為工程施工查核小組查核委員聲明</div>
               </pre>
          <!--/b-collapse>-->
        </div>
      </div>
    </section>
  </div>
  
<!-- 上拉與下拉選單 <b-collapse v-model="stepVisible.step1"></b-collapse> -->
 
                <!-- 上拉與下拉選單按鍵 <b-button class="float-right" @click="stepVisible.step1 = !stepVisible.step1">
                <font-awesome-icon v-if="stepVisible.step1" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible.step1" icon="chevron-down"></font-awesome-icon>
                </b-button>-->
               <!-- 輸入欄位<b-form-input class="col-4" v-model="$v.code.$model" trim></b-form-input>-->
                <!--<b-col align="right">
                <i-button type="search" @click="handleQuery"></i-button>  查詢按鍵 
                <i-button type="x-circle" @click="reset"></i-button>      刪除按鍵
              </b-col>-->
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
          //機關代碼
          key: 'code',
          label: i18n.t('label.code'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //機關名稱
          key: 'name',
          label: i18n.t('label.agencyName'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //聯絡地址
          key: 'addr',
          label: i18n.t('label.addr'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //連絡電話
          key: 'tel',
          label: i18n.t('label.contactInformationTel'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          //傳真機號碼
          key: 'fax',
          label: i18n.t('label.fax'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
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
      navigateByNameAndParams,
      FormStatusEnum,
    };
  },
};
</script>
<style></style>

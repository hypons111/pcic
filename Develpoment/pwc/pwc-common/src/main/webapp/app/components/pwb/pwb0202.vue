<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1">
          <div class="row align-items-center pl-3">
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

        <div class="card-body col-12">
          <b-collapse v-model="stepVisible.step1">
            <b-row>
              <b-col cols="6">
                <!-- 帳號 -->
                <i-form-group-check :label="$t('label.account')">
                  <b-form-select style="width: auto" v-model="$v.userId.$model" :options="formOptions.account"></b-form-select>
                </i-form-group-check>
              </b-col>
              <b-col cols="6">
                <!-- 使用者 -->
                <i-form-group-check :label="$t('label.user')">
                  <b-form-input style="width: auto" v-model="$v.chName.$model" trim></b-form-input>
                </i-form-group-check>
              </b-col>
            </b-row>

            <b-row>
              <b-col>
                <!-- 角色 -->
                <i-form-group-check :label="$t('label.roleId')">
                  <b-form-select style="width: auto" v-model="$v.roleId.$model" :options="formOptions.roleId"></b-form-select>
                </i-form-group-check>
              </b-col>
              <b-col>
                <!-- 啟用 -->
                <i-form-group-check :label="$t('label.enable')">
                  <b-form-select style="width: auto" v-model="$v.status.$model" :options="formOptions.status"></b-form-select>
                </i-form-group-check>
              </b-col>
            </b-row>

            <b-form-row>
              <b-col offset="8">
                <i-button type="search" @click="clickQueryBtn"></i-button>
                <i-button type="x-circle" @click="remove"></i-button>
              </b-col>
            </b-form-row>
          </b-collapse>
        </div>
      </div>
    </section>
    <section class="container mt-2">
      <i-button type="file-earmark-plus" @click="create"></i-button>
    </section>
    <section class="container mt-2" v-if="queryStatus">
      <i-table
        class="table-sm"
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged"
      >
        <template #cell(action)="row">
          <i-button size="sm" type="pencil-square" @click="toEditView(row.item)"> </i-button>
        </template>
      </i-table>
    </section>
  </div>
</template>

<script lang="ts">
import { onMounted, reactive, ref, watch, toRefs } from '@vue/composition-api';
import { useGetters } from '@u3u/vue-hooks';
import { Pagination } from '@/shared/model/pagination.model';
import { useValidation } from '@/shared/form';,
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formOptionsFormatter } from '@/shared/formatter/common';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import i18n from '@/shared/i18n';
import axios from 'axios';
import { boolean } from '@storybook/addon-knobs';

export default {
  name: 'pwb0202',
  props: {
    queryStatusEdit: {
      type: String,
      required: false,
      default: '123', //隨便給的一個值(不給值是null -> 會報錯)
    },
  },
  setup(props) {

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    const formDefault = {
      userId: '',
      chName: '',
      roleId: '',
      status: '',
      loginUserId: '',  //登入者的帳號
      pccOrgId: '', //機關
      compIdno: '', //公司
      addUserId: '' //加1後的USER_ID
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));

    // 表單物件驗證規則
    const rules = {
      userId: {},
      chName: {},
      roleId: {},
      status: {},
      loginUserId: {},
      pccOrgId: {},
      compIdno: {},
      addUserId: {}
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    const table = reactive({
      fields: [
        {
          // 帳號
          key: 'userId',
          label: i18n.t('label.user'),
        },
        {
          // 姓名
          key: 'chName',
          label: i18n.t('label.userName'),
        },
        {
          // 啟用
          key: 'status',
          label: i18n.t('label.enable'),
          formatter: (value: string) => formOptionsFormatter(formOptions.status, value),
        },
        {
          // 角色
          key: 'roleName',
          label: i18n.t('label.roleId'),
        },
        {
          key: 'action',
          label: '',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    const formOptions = reactive({
      account: [],
      status: [
        { value: '', text: '全部' },
        { value: 'Y', text: '啟用' },
        { value: 'N', text: '未啟用' },
      ],
      roleId: [],
    });

    // 取得登入帳號(ADM_ACCOUNT.USER_ID)
    const loginUserId = ref(useGetters(['account']).account.value.id);

    onMounted(() => {
      getAdmAccount();
    });

    const watchLoginId = ref(form.loginUserId);

    watch(
      watchLoginId,
      () => {
        if (form.loginUserId == null || form.loginUserId == '') {
          // getAdmAccount();
        }
      },
      { immediate: true }
    );

    const queryStatusEditProp = toRefs(props).queryStatusEdit;

    //pwb0202EditInfo傳來的值
    watch(
      queryStatusEditProp,
      () => {
        if (queryStatusEditProp.value) {
         queryStatus.value = false;
        }
        roleIdEng = [];
        getRoleId();
      },
      { immediate: true }
    );

    // 查詢登入帳號的ADM_ACCOUNT所有欄位
    function getAdmAccount(){
          axios.get('/service/adm-account/' + loginUserId.value).then(({ data }) => {
            Object.assign(form, data);
            form.loginUserId = loginUserId.value;
            form.userId = '';
            form.chName = '';
            form.status = '';
            getUserId();
            getRoleId();
      });
    }

    // 查詢與登入帳號屬同一單位之帳號
    function getUserId() {
      axios.post('/service/adm-account/getUserId', form).then(({ data }) => {
        for (let userId of data) {
          if(formOptions.account.indexOf(userId) == -1){
            formOptions.account.push(userId);
          }
        }
      });
    }

    let roleIdEng = [];

    // 查詢登入帳號所擁有的角色(ADM_ACCOUNT_ROLE.ROLE_ID)
    function getRoleId() {
      axios.post('/adm-account-role/getRoleId', form).then(({ data }) => {
        for (let roleId of data) {
           if(roleIdEng.indexOf(roleId) == -1){
            // console.log('2222');
              roleIdEng.push(roleId);  
           }
        }
        getAllAdmRole();
      });
    }

    // 查詢所有ADM_ROLE
    function getAllAdmRole(){
        axios.get('/adm-roles').then(({ data }) => {

          formOptions.roleId = [];
          for (let i of roleIdEng) {
            formOptions.roleId.push(
            ...data
              .filter(item => item['roleId'] === i)
              .map(item => {
                return { value: item['roleName'], text: item['roleName'], eng: item['roleId'] };
              })
            );
          }

          // 把formOptions.roleId.eng的值都塞到roleIdEngList
          const roleIdEngList = Object.values(formOptions.roleId).map(item => item.eng);
          console.log('roleIdEngList',roleIdEngList)
          console.log('roleIdEng',roleIdEng)

          
          // 判斷後端抓的登入者擁有的ROLE_ID(roleIdEng)跟formOptions.roleId是否相同
          let diffRoleId = 0;
          if(roleIdEngList.length !== roleIdEng.length){
            diffRoleId++;
          } else {
            roleIdEng.forEach(i => {
            if(roleIdEngList.includes(i) == false){
              diffRoleId++;
            }
          })
          }
          
          // 如果roleIdEngList跟roleIdEng的值不相同 -> 重新塞值到formOptions.roleId
          if(diffRoleId > 0){
            formOptions.roleId = [];
            roleIdEng.forEach(i => {
                formOptions.roleId.push(
              ...data
                .filter(item => item['roleId'] === i)
                .map(item => {
                  return { value: item['roleName'], text: item['roleName'], eng: item['roleId'] };
                })
              );
            })
          }


          // roleIdEng.forEach(i => {
          //       formOptions.roleId.forEach(k => {
          //           if(i !== k.eng ){
          //             console.log('22222',i)
          //             console.log('33333',k); 
          //             formOptions.roleId.push(...data
          //                                 .filter(item => item['roleId'] === i)
          //                                 .map(item => {
          //                                 return { value: item['roleName'], text: item['roleName'], eng: item['roleId'] };
          //                                 }));
          //         }              
          //       })
          //   })

       })
    }
    

    const notificationService: NotificationService = useNotification();
    const iTableComponent = ref(null);
    
    const clickQueryBtn = () => {
      if (iTableComponent.value) iTableComponent.value.state.pagination.currentPage = 1;
      handleQuery();
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };


    //查詢
    const handleQuery = () => {
      queryStatus.value = true;
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          axios 
            .post('/service/adm-account/getAccountInfo', form) //後端分頁
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length); //後端分頁
              table.totalItems = data.totalElements; //後端分頁
            })
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    let infoTable = null;
    let editTable = null;
    
    //編輯
    // function toEditView(item){
    //     let loginUserId = form.loginUserId;
    //     let pccOrgId = form.pccOrgId;
    //     let compIdno = form.compIdno;
    //     navigateByNameAndParams('pwb0202EditInfo', { item, loginUserId, pccOrgId, compIdno, isnotkeepAlive: true });
    // }

    function toEditView(item){
        editTable = reactive(
          {loginUserId: form.loginUserId, pccOrgId: form.pccOrgId, compIdno: form.compIdno},
        )
        navigateByNameAndParams('pwb0202EditInfo', { item, editTable, isnotkeepAlive: true });
    }


    //新增
    function create(){
      addOne();
      infoTable = reactive(
        {userId: form.addUserId, loginUserId: form.loginUserId, pccOrgId: form.pccOrgId, compIdno: form.compIdno},
      )
      navigateByNameAndParams('pwb0202EditInfo', {infoTable, isnotkeepAlive: true });
    }

    //取出同個單位最後一筆USER_ID後加1
    function addOne(){
      const lastUserId = formOptions.account.pop();
      formOptions.account.push(lastUserId)
      const ifExist = lastUserId.lastIndexOf('-')

      if(ifExist != -1 ){
        let lastNum = lastUserId.substring(ifExist+1);
        lastNum = (parseInt(lastNum) + 1).toString();
        let firstNum = lastUserId.substring(0, ifExist+1);
        let num = firstNum + lastNum
        form.addUserId = num;
      } 
      // else {
      //   let num = parseInt(lastUserId) + 1;
      //   form.userId = num.toString();
      //   console.log(num)
      // }
    }

    function remove() {
      const pccOrgId = form.pccOrgId;
      const compIdno = form.compIdno;
      reset();
      if(pccOrgId != null || pccOrgId != ''){
        form.pccOrgId = pccOrgId;
      }else{
        form.compIdno = compIdno;
      }
    }
    


    return{
      formDefault,
      form,
      rules,
      $v,
      reset,
      stepVisible,
      table,
      formOptions,
      clickQueryBtn,
      iTableComponent,
      handlePaginationChanged,
      toEditView,
      queryStatus,
      create,
      remove
    }

    }
}

</script>

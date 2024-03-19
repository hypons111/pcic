<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          編輯復建工程項目
        </h5>
      </div>
      <div class="card-body col-10">
        <div>
          <div>
            <b-form-row class="align-items-top">
              <b>　　　　　　以實地勘查結果研擬復建方案，應包括復建工程內容、數量、單價、概念設計相關圖說及經費估算等<br>
              　　　　　　　1.復建工程內容、數量及單價: (復建項目請含基本尺寸，如:護岸H=3m、預力橋樑W=6m…)<br>
              　<br></b>
            </b-form-row>
            <b-form-row>
              <i-form-group-check label="構造物">
                <b-form-select v-model="$v.constItemName.$model" :options="queryOptions.itemName" :state="validateState($v.constItemName)"></b-form-select>
              </i-form-group-check>
              <i-form-group-check label="構造物形式">
                <b-form-select v-model="$v.constModelName.$model" :options="queryOptions.modelName" :state="validateState($v.constModelName)"></b-form-select>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-3" label-cols="6" content-cols="6" label="單價(元)">
                <b-form-input v-model.trim="$v.workItemPrice.$model" :state="validateState($v.workItemPrice)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check class="col-3" label-cols="6" content-cols="6" label="單位">
                <b-form-select v-model.trim="$v.workItemUnit.$model" :options="queryOptions.unit"></b-form-select>
              </i-form-group-check>
              <i-form-group-check class="col-3" label-cols="6" content-cols="6" label="數量">
                <b-form-input v-model.trim="$v.workItemQty.$model" :state="validateState($v.workItemQty)"></b-form-input>
              </i-form-group-check>
              <i-form-group-check class="col-3" label-cols="6" content-cols="6" label="複價(元)">
                <b-form-input disabled v-model="multiplyResult"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="align-items-top">
              <b>　　　　　　參考單價 (單價僅供參考，並確實依當時工料行情及工區條件等嚴實編列)<br></b>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-12" label-cols="1" content-cols="11">
                <b-form-textarea disabled v-model="refPrice" rows="4" maxlength="4000" placeholder="單價僅供參考，並確實依當時工料行情及工區條件等嚴實編列"></b-form-textarea>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <i-form-group-check class="col-12" label-cols="1" content-cols="11" label="備註">
                <b-form-input v-model.trim="$v.remark.$model"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            
          </div>
            <div class="button-float-right">
              <b-button-toolbar class="mt-5" v-if="formStatusRef === 'modify'">
                <i-button class="ml-1" type="save" @click="submitForm('modify')"></i-button>
                <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
                <i-button class="ml-1" type="trash" @click="deleteWorkItem"></i-button>
              </b-button-toolbar>
              <b-button-toolbar class="mt-5" v-else-if="formStatusRef === 'create'">
                <i-button class="ml-1" type="save" @click="submitForm('create')"></i-button>
                <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, reactive, Ref, ref, watch, toRefs, onActivated, onMounted} from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { required, email } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useGetters } from '@u3u/vue-hooks';

export default {
  name: 'rec0303-edit-item',
  props: {
    formStatusForwardExt: {
      type: String,
      required: true,
    },
    recWorkItem: {
      type: Object,
      required: false,
    },
    recAreaNo: {
      type: String,
      required: false,
    },
  },
  setup(props, context) {
    //傳入狀態
    const formStatusForwardExtProp = toRefs(props).formStatusForwardExt;
    const recWorkItemProp = toRefs(props).recWorkItem;
    const recAreaNoProp = toRefs(props).recAreaNo;
    
    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    const refPrice = ref('');

    // 單價 X 數量
    const multiplyResult = computed( () => form.workItemAmount = form.workItemPrice * form.workItemQty);

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    // 空值表單
    const formDefault = {
      workItemNo:'',
      areaNo:'',
      constItemName: '', 
      constModelName: '', 
      workItemPrice: '', 
      workItemUnit: '', 
      workItemQty: '',
      workItemAmount: 0,
      remark: '',
      workItemStatus:'',
      createUser: '',
      createTime: undefined,
      updateUser: '',
      updateTime: undefined,
    };

    // 預存好的form
    const form = reactive(Object.assign({}, formDefault));

    // 檢核規則
    const rules = {
      workItemNo:{},
      areaNo:{notNull: required},
      constItemName: {notNull: required}, 
      constModelName: {notNull: required}, 
      workItemPrice: {notNull: required}, 
      workItemUnit: {}, 
      workItemQty: {notNull: required}, 
      workItemAmount: {},
      remark: {},
      workItemStatus:{},
    };

    // 下拉選單選項
    const queryOptions = reactive({
      unit: [
        { value: '公分', text: '公分' },
        { value: '公尺', text: '公尺' },
        { value: '公里', text: '公里' },
        { value: '公克', text: '公克' },
        { value: '公斤', text: '公斤' },
        { value: '毫升', text: '毫升' },
        { value: '公升', text: '公升' },
        { value: '個', text: '個' },
        { value: '條', text: '條' },
        { value: '罐', text: '罐' },
        { value: '桶', text: '桶' },
      ],
      itemName: [
        { value: '路面', text: '路面'},
        { value: '排水溝', text: '排水溝'},
      ],
      modelName: [
        { value: '3', text: '路高15cm', source: '水利署', price: 0, unit: '' },
        { value: '4', text: '鐵制水溝蓋', source: '水利署', price: 0, unit: '' },
      ],
    });

    const allItemName = ref([]);
    const getItemName = () => {
      axios
        .get('/rec-projects/get-project-const-item')
        .then(({ data }) => {
          allItemName.value.splice(0, allItemName.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const allModelName = ref([]);
    const getModelName = () => {
      axios
        .get(`/rec-projects/get-project-const-model/${form.constItemName}`)
        .then(({ data }) => {
          allModelName.value.splice(0, allModelName.value.length, ...data);
        })
        .catch(notificationErrorHandler(notificationService));
    };

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    //是否要重新查詢
    const isReload: boolean = ref(null);
    //modal物件
    const $bvModal = useBvModal();

    //創建初始值
    const createDefaultValue = (data: any) => {
      Object.assign(formDefault, data);
      reset();
    };

    //變更表單操作狀態，當form狀態不為查看時重置
    const changeFormStatus = (status: formStatusEnum) => {
      if (status !== formStatusEnum.INFO) {
        reset();
      }
      formStatusRef.value = status;
    };

    //修改表單狀態
    const changeformStatusForwardPropEdit = () => {
      formStatusRef.value = formStatusEnum.MODIFY;
    };

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;

    //新增&修改送出前檢查訊息
    const submitForm = (submitMethod: string) => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          if (submitMethod === 'modify') {
            //提交修改
            putForm();
            $bvModal.msgBoxOk('資料異動成功');
          }
          if (submitMethod === 'create') {
            //提交新增
            insertProject();
            $bvModal.msgBoxOk('資料新增成功');
          }
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };

    //修改
    const putForm = () => {
      form.updateUser = userIdentity;
      form.updateTime = new Date();
      let url: string;
        url = `/rec-work-item/${form.workItemNo}`;
      axios
        .put(url, form)
        .then(({ data }) => {
          //填入值
          createDefaultValue(data);
          isReload.value = true;
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    };

    //刪除
    const deleteWorkItem = () => {
      $bvModal.msgBoxConfirm('刪除此工程項目資料，確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          let url: string;
          url = `/rec-work-item/${form.workItemNo}`;
          axios
            .delete(url)
            .then($bvModal.msgBoxOk('資料刪除成功'))
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    //新增
    const insertProject = () => {
      //4個系統用欄位
      form.createUser = userIdentity;
      form.updateUser = userIdentity;
      form.createTime = new Date();
      form.updateTime = new Date();
      axios
        .post(`/rec-work-item`, form)
        .then(({ data }) => {
          isReload.value = true;
          createDefaultValue(data); //不再新增時將值放入
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    };

    watch(
      formStatusForwardExtProp,
      () => {
        if (formStatusForwardExtProp.value === 'modify') {
          createDefaultValue(recWorkItemProp.value);
          changeFormStatus(formStatusEnum.MODIFY);
          getItemName();
          getModelName();
        } else {
          Object.assign(form, formDefault);
          createDefaultValue(form);
          changeFormStatus(formStatusEnum.CREATE);
          getItemName();
        }
      },
      { immediate: true },
    );

    watch(
      recAreaNoProp,
      () => {
          form.areaNo = recAreaNoProp.value; //傳入 areaNo
      },
      { immediate: true },
    );

    watch(allItemName, () => {
      queryOptions.itemName = allItemName.value.map(data => {
        return { value: data['constItemName'], text: data['constItemName'] };
      });
      // queryOptions.itemName.unshift({ text: '請選擇', value: '' }); //添加一個或多個元素至陣列的開頭
    });

    watch(allModelName, () => {
      queryOptions.modelName = allModelName.value.map(data => {
        return { value: data['constModelName'], text: data['constModelName'], source: data['constModelSource'], price: data['constModelPrice'], unit: data['constModelUnit']};
      });
      queryOptions.modelName.unshift({ text: '請選擇', value: '',source: '', price: 0, unit: '' }); //添加一個或多個元素至陣列的開頭
    });

    const testt = reactive(Object.assign({}, form.constItemName));

    //構造物改變，即時更新構造物形式
    watch(form, () => {
      //更新構造物形式說明
      for (let i=0;i< queryOptions.modelName.length;i++ ){
        if (queryOptions.modelName[i].value===form.constModelName){
          refPrice.value = '　　　'+queryOptions.modelName[i].source+'　提供'+'\n工程項目：'+queryOptions.modelName[i].text+
          '　　　單價(元)：'+queryOptions.modelName[i].price+'　　　單位：'+queryOptions.modelName[i].unit;
        }
      }
      getModelName();
    });
      
    return {
      $v,
      formStatusEnum,
      formStatusRef,
      changeFormStatus,
      reset,
      validateState,
      submitForm,
      deleteWorkItem,
      userIdentity,
      changeformStatusForwardPropEdit,
      recWorkItemProp,
      queryOptions,
      recAreaNoProp,
      multiplyResult,
      refPrice,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

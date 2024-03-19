<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          編輯災損地區資料
        </h5>
      </div>
      <div class="card-body col-10">
        <div>
          <div>
            <b-form-row>
              <i-form-group-check label="鄉（鎮、市）">
                <b-form-input v-model.trim="$v.city.$model"></b-form-input>
              </i-form-group-check>
              <i-form-group-check label="村（里）">
                <b-form-input v-model.trim="$v.village.$model"></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check label="所在或鄰近之河溪、道路或顯著目標">
                <b-form-input v-model.trim="$v.landmark.$model"></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check label="座標系統">
                <b-form-select v-model.trim="$v.coordinate.$model" :options="queryOptions.coordinate"></b-form-select>
              </i-form-group-check>
            </b-form-row>

            <b-form-row>
              <i-form-group-check label="Ｘ座標">
                <b-form-input v-model.trim="$v.gpsX.$model"></b-form-input>
              </i-form-group-check>
              <i-form-group-check label="Ｙ座標">
                <b-form-input v-model.trim="$v.gpsY.$model"></b-form-input>
              </i-form-group-check>
            </b-form-row>

            <b-form-row class="align-items-top">
              <i-form-group-check
                label-cols="6"
                content-cols="6"
                :item="$v.isLandslides"
                label="是否屬土石流潛勢溪流影響區 ？">
                <b-form-radio-group v-model="$v.isLandslides.$model" :options="queryOptions.type" :state="validateState($v.isLandslides)"/>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="align-items-top">
              <i-form-group-check
                label-cols="6"
                content-cols="6"
                :item="$v.isWoodland"
                label="是否屬林班地內 ？">
                <b-form-radio-group v-model="$v.isWoodland.$model" :options="queryOptions.type" :state="validateState($v.isWoodland)"/>
              </i-form-group-check>
            </b-form-row>

            <b-form-row class="align-items-top">
              <i-form-group-check
                label-cols="6"
                content-cols="6"
                :item="$v.isRepeat"
                label="是否屬重複致災地點 ？">
                <b-form-radio-group v-model="$v.isRepeat.$model" :options="queryOptions.type" :state="validateState($v.isRepeat)"/>
              </i-form-group-check>
              <i-form-group-check label="重複致災原建物年份">
                <b-form-input v-model.trim="$v.originalBuiltYear.$model"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <b-form-row>
              <!-- 照片 -->
              <i-form-group-check class="col-12" label-cols="2" content-cols="10" label="照片：">
                <!-- <b-img fluid rounded :src="require('./aaaaa.jpg')" alt="Image"></b-img> -->
                <table width="100%">
                  <tr>
                    <td width="50%">
                      <b-form-file v-model="picFile" @change="picSelected" plain></b-form-file>
                      <b-img v-if="picPreview" class="mt-1" style="height: 300px" alt="Image" fluid rounded :src="picPreview" />
                    </td>
                    <td width="50%">
                      <b-button class="p-1" variant="info" @click="uploadPicFile">上傳</b-button>
                    </td>
                  </tr>
                </table>
              </i-form-group-check>
            </b-form-row>
            <b-form-row class="align-items-bottom">
              　　　　其他補充事項:(如縣府審查意見...等)<br>
            </b-form-row>
            <b-form-row class="align-items-top">
              <i-form-group-check class="col-8" label-cols="1" content-cols="11">
                <b-form-textarea v-model="$v.others.$model" rows="4" maxlength="4000" placeholder="其他補充事項:(如縣府審查意見...等)"></b-form-textarea>
              </i-form-group-check>
            </b-form-row>
          </div>
          <div>
            <div class="button-float-right">
              <b-button-toolbar class="mt-5" v-if="formStatusRef === 'modify'">
                <i-button class="ml-1" type="save" @click="submitForm('modify')"></i-button>
                <i-button class="ml-1" type="x-circle" @click="reset"></i-button>
                <i-button class="ml-1" type="trash" @click="deleteArea"></i-button>
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
    <!-- 新增按鈕(只有在編輯災損地區時才出現) -->
    <section v-if="formStatusForwardProp === 'modify'">
      <div class="container mt-1 mb-1">
        <b-form-row class="align-items-top">
          <h3 class="font-weight-bold">工程項目　</h3>
          <i-button type="file-earmark-plus" @click="toCreateView"></i-button>
        </b-form-row>
      </div>
    </section>
    <!-- i-table -->
    <section v-if="formStatusForwardProp === 'modify'">
      <div class="container" v-if="queryStatus">
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
            <i-button class="mt-1" type="pencil-square" @click="toEditView(row.item)"></i-button>
            <i-button class="mt-1" type="trash" @click="deleteWorkItem(row.item.workItemNo)"></i-button>
          </template>
        </i-table>
      </div>
    </section>
    <b-modal
      id="case-work-item-modal"
      size="xl"
      title="案件維護"
      header-bg-variant="secondary"
      header-text-variant="light"
      @ok="handleOk"
      @cancel="handleQuery"
    >
      <rec-0303-edit-item
        :formStatusForwardExt="formStatusForwardExtProp"
        :recWorkItem="recWorkItemProp"
        :recAreaNo="recAreaNoProp"
        ref="rec0303EditItem"
      />
      <template #modal-footer="{ ok, cancel }">
        <!-- <i-button type="save" @click="ok()"></i-button>
        <i-button type="x-circle" @click="modalReset()"></i-button> -->
        <i-button type="arrow-counterclockwise" @click="cancel()"></i-button>
        <!-- Button with custom close trigger value -->
        <!-- <b-button size="sm" variant="outline-secondary" @click="hide('forget')">
          Forget it
        </b-button> -->
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { reactive, Ref, ref, watch, toRefs, onActivated, onMounted } from '@vue/composition-api';
import NotificationService from '@/shared/notification/notification-service';
import { useNotification } from '@/shared/notification';
import { useBvModal } from '@/shared/modal';
import { BvModal, BvModalEvent } from 'bootstrap-vue';
import { required, email } from '@/shared/validators';
import axios, { AxiosResponse } from 'axios';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useGetters } from '@u3u/vue-hooks';
import Rec0303EditItem from '@/components/rec/rec0303/rec0303-edit-item.vue';

export default {
  components: {
    Rec0303EditItem,
  },
  name: 'rec0303-edit-area',
  props: {
    formStatusForward: {
      type: String,
      required: true,
    },
    recArea: {
      type: Object,
      required: false,
    },
    recProjectNo: {
      type: String,
      required: false,
    },
  },
  setup(props, context) {
    //傳入狀態
    const formStatusForwardProp = toRefs(props).formStatusForward;
    const recAreaProp = toRefs(props).recArea;
    const recProjectNoProp = toRefs(props).recProjectNo;

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.MODIFY);

    //空值表單
    const formDefault = {
      projectNo:'',
      workItemNo:'',
      areaNo:'',
      city: '',
      village: '',
      landmark: '',
      coordinate: 'TWD-97',
      gpsX: '',
      gpsY: '',
      isLandslides: '',
      isWoodland: '',
      isRepeat: '',
      originalBuiltYear: 0,
      others: '',
      createUser: '',
      createTime: undefined,
      updateUser: '',
      updateTime: undefined,
    };

    //預存好的form
    const form = reactive(Object.assign({}, formDefault));

    //檢核規則
    const rules = {
      projectNo: {notNull: required},
      workItemNo: {},
      areaNo: {},
      city: {},
      village: {},
      landmark: {},
      coordinate: {},
      gpsX: {},
      gpsY: {},
      isLandslides: {},
      isWoodland: {},
      isRepeat: {},
      originalBuiltYear: {},
      others: {},
    };

    // 下拉選單選項
    const queryOptions = reactive({
      type: [
        { value: true, text: '是' },
        { value: false, text: '否' },
      ],
      coordinate: [
        { value: 'TWD-97', text: 'TWD-97' },
        { value: 'TWD-96', text: 'TWD-96' },
      ],

    });

    const table = reactive({
      fields: [
        {
          key: 'constItemName',
          label: '構造物',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '10%' },
        },
        {
          key: 'constModelName',
          label: '構造物形式',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '30%' },
        },
        {
          key: 'workItemPrice',
          label: '單價(元)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'workItemUnit',
          label: '單位',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '5%' },
        },
        {
          key: 'workItemQty',
          label: '數量',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '5%' },
        },
        {
          key: 'workItemAmount',
          label: '複價(元)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '8%' },
        },
        {
          key: 'remark',
          label: '備註',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '20%' },
        },
        {
          key: 'action',
          label: $i18n.t('label.action'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    const picFile: Ref<File> = ref(null);
    const picPreview = ref(null);

    function picSelected(e) {
      if (e.target.files.length !== 0) {
        const fileReader = new FileReader();
        fileReader.onload = e => {
          picPreview.value = e.target.result;
        };
        fileReader.readAsDataURL(e.target.files[0]);
      } else {
        picPreview.value = null;
      }
    }

    function uploadPicFile() {
      console.log(formStatusRef.value);
      const param = new FormData();
      param.append('file', picFile.value);
      axios
        // .put('/adm-files/upload', param, { headers: { 'Content-Type': 'multipart/form-data' } })
        .put('/rec-area/upload', param)
        .then(() => {
          notificationService.success(`上傳成功`);
        })
        .catch(notificationErrorHandler(notificationService));
    }

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

    // 查詢結果及狀態
    const queryStatus = ref(false);

    const formStatusForwardExtProp = ref(null);
    const recWorkItemProp = ref(null);
    const recAreaNoProp = ref(null);


    //轉到新增頁面
    const toCreateView = () => {
    formStatusForwardExtProp.value = 'create';
      recAreaNoProp.value = form.areaNo;
      $bvModal.show('case-work-item-modal');
    };

    //轉到編輯頁面
    const toEditView = (recWorkItem: any) => {
      formStatusForwardExtProp.value = 'modify';
      recWorkItemProp.value = recWorkItem;
      recAreaNoProp.value = form.areaNo;
      $bvModal.show('case-work-item-modal');
    };

    //依條件查詢災損地區的工程項目
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/rec-work-item/criteria-jpa', form)
            .then(({ data }) => {
              console.log(data);
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    //修改表單狀態
    const changeformStatusForwardPropEdit = () => {
      formStatusRef.value = formStatusEnum.MODIFY;
    };

    //取得使用者資訊id
    const userIdentity = ref(useGetters(['account']).account.value).value.id;

    const handleOk = (bvModalEvt: BvModalEvent) => {
      bvModalEvt.preventDefault();
    };

    const handleCancel = () => {
      handleQuery();
    };

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
          if (form.projectNo == '') {
            $bvModal.msgBoxOk('請先建立復建工程資料。');
          } else {
            $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
          }
        }
      });
    };

    //修改
    const putForm = () => {
      form.updateUser = userIdentity;
      form.updateTime = new Date();
      let url: string;
        url = `/rec-area/${form.areaNo}`;
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
    const deleteArea = () => {
      $bvModal.msgBoxConfirm('刪除此災損地區資料，確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          let url: string;
          url = `/rec-area/${form.areaNo}`;
          axios
            .delete(url)
            .then($bvModal.msgBoxOk('資料刪除成功'))
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    //刪除
    const deleteWorkItem = (deleteId: string) => {
      $bvModal.msgBoxConfirm('刪除此工程項目資料，確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          let url: string;
          url = `/rec-work-item/` + deleteId;
          axios
            .delete(url)
            .then($bvModal.msgBoxOk('資料刪除成功'))
            .finally(handleQuery())
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
        .post(`/rec-area`, form)
        .then(({ data }) => {
          isReload.value = true;
          createDefaultValue(data); //不再新增時將值放入
        })
        .finally()
        .catch(notificationErrorHandler(notificationService));
    };

    watch(
      recAreaProp,
      () => {
        if (formStatusForwardProp.value === 'modify') {
          createDefaultValue(recAreaProp.value);
          changeFormStatus(formStatusEnum.MODIFY);
        } else {
          Object.assign(form, formDefault);
          createDefaultValue(form);
          changeFormStatus(formStatusEnum.CREATE);
        }
      },
      { immediate: true },
    );

    watch(
      recProjectNoProp,
      () => {
          form.projectNo = recProjectNoProp.value; //傳入 projectNo
          handleQuery();
      },
      { immediate: true },
    );

    return {
      $v,
      formStatusEnum,
      formStatusRef,
      changeFormStatus,
      reset,
      validateState,
      submitForm,
      deleteArea,
      deleteWorkItem,
      userIdentity,
      changeformStatusForwardPropEdit,
      formStatusForwardExtProp,
      recAreaProp,
      recWorkItemProp,
      queryOptions,
      recProjectNoProp,
      recAreaNoProp,
      form,
      queryStatus,
      handleOk,
      handleQuery,
      table,
      toEditView,
      toCreateView,
      picFile,
      picPreview,
      picSelected,
      uploadPicFile,
      formStatusForwardProp
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

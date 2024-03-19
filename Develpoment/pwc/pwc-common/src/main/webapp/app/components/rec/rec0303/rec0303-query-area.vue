<template>
  <b-container>
  <div>
    <!-- 查詢條件 -->
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>
            <div class="col-sm-1 p-0">
              <b-button class="float-right" @click="stepVisible = !stepVisible">
                <font-awesome-icon v-if="stepVisible" icon="chevron-up"></font-awesome-icon>
                <font-awesome-icon v-if="!stepVisible" icon="chevron-down"></font-awesome-icon>
              </b-button>
            </div>
          </div>
        </div>
        <div class="card-body col-10">
          <b-collapse v-model="stepVisible">
            <b-form-row>
              <i-form-group-check label="復建工程編號:">
                <b-form-input disabled v-model.trim="$v.projectNo.$model"></b-form-input>
              </i-form-group-check>
            </b-form-row>
            <div class="button-float-right">
              <b-button-toolbar>
                <i-button hidden class="mt-1 ml-1" type="search" @click="handleQuery"></i-button>
                <i-button hidden class="mt-1 ml-1" type="x-circle" @click="reset"></i-button>
              </b-button-toolbar>
            </div>
          </b-collapse>
        </div>
      </div>
    </section>
    <!-- 新增按鈕 -->
    <section>
      <div class="container mt-1 mb-1">
        <b-form-row class="align-items-top">
        <h3 class="font-weight-bold">受災地區　</h3>
        <i-button type="file-earmark-plus" @click="toCreateView"></i-button>
        </b-form-row>
      </div>
    </section>
    <!-- i-table -->
    <section>
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
            <i-button class="mt-1" type="trash" @click="deleteArea(row.item.areaNo)"></i-button>
          </template>
        </i-table>
      </div>
    </section>
  </div>
    <b-modal
      id="case-area-modal"
      size="xl"
      title="案件維護"
      header-bg-variant="secondary"
      header-text-variant="light"
      @ok="handleOk"
      @cancel="handleQuery"
    >
      <rec-0303-edit-area
        :formStatusForward="formStatusForwardProp"
        :recArea="recAreaProp"
        :recProjectNo="recProjectNoProp"
        ref="rec0303EditArea"
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
  </b-container>
</template>

<script lang="ts">
import $i18n from '@/shared/i18n';
import { useValidation, validateState } from '@/shared/form';
import { toRefs, onActivated, reactive, ref, onMounted } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { BvModal, BvModalEvent } from 'bootstrap-vue';
import { navigateByNameAndParams } from '@/router/router';
import { Pagination } from '@/shared/model/pagination.model';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import { required } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import Rec0303EditArea from '@/components/rec/rec0303/rec0303-edit-area.vue';
import iButton from '@/shared/buttons/i-button.vue';

export default {
  components: { 
    iButton,
    Rec0303EditArea,
  },
  name: 'rec0303QueryArea',
  props: {
    isReload: {
      required: false,
    },
    recProject: {
      type: Object,
      required: false,
    },
  },
  setup(props) {
    const recProjectProp = toRefs(props).recProject;

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    onActivated(() => {
      // if (isReloadProp.value) {
        form.projectNo = recProjectProp.value['projectNo'];
        handleQuery();
      // }
    });

    onMounted(() => {
      form.projectNo = recProjectProp.value['projectNo'];
      handleQuery();
    });

    // const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      projectNo: '',
    };

    const rules = {
      projectNo: {notNull: required},
    };

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const modalReset = () => {
    };

    const table = reactive({
      fields: [
        {
          key: 'areaNo',
          label: '受災地區編號',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '13%' },
        },
        {
          key: 'city',
          label: '鄉(鎮、市)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '20%' },
        },
        {
          key: 'village',
          label: '村(里)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '20%' },
        },
        {
          key: 'gpsX',
          label: 'X座標',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '15%' },
        },
        {
          key: 'gpsY',
          label: 'Y座標',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: { width: '15%' },
        },
        {
          key: 'action',
          label: $i18n.t('label.action'),
          sortable: false,
          thStyle: 'width:10%',
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: { width: '10%' },
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);
    //顯示查詢條件區
    let stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //轉到新增頁面
    const toCreateView = () => {
      formStatusForwardProp.value = 'create';
      recProjectNoProp.value = form.projectNo;
      $bvModal.show('case-area-modal');
    };

    const formStatusForwardProp = ref(null);
    const recAreaProp = ref(null);
    const recProjectNoProp = ref(null);

    //轉到編輯頁面
    const toEditView = (recArea: any) => {
      formStatusForwardProp.value = 'modify';
      recAreaProp.value = recArea;
      recProjectNoProp.value = recArea.projectNo;
      $bvModal.show('case-area-modal');
    };

    //依條件查詢災損地區
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          //重置條件
          table.data = undefined;
          queryStatus.value = true;
          axios
            .post('/rec-area/criteria-jpa', form)
            .then(({ data }) => {
              table.data = data.content.slice(0, data.content.length);
              table.totalItems = data.totalElements;
            })
            .finally()
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    //刪除災損地區
    const deleteArea = (deleteId: string) => {
      $bvModal.msgBoxConfirm('刪除此災損情形資料，確定刪除？').then((isOK: boolean) => {
        if (isOK) {
          let url: string;
          url = `/rec-area/` + deleteId;
          axios
            .delete(url)
            .then($bvModal.msgBoxOk('資料刪除成功'))
            .finally(handleQuery())
            .catch(notificationErrorHandler(notificationService));
        }
      });
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      $v,
      stepVisible,
      validateState,
      checkValidity,
      queryStatus,
      handleQuery,
      reset,
      handlePaginationChanged,
      table,
      toEditView,
      toCreateView,
      deleteArea,
      formStatusForwardProp,
      recAreaProp,
      recProjectNoProp,
      recProjectProp,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

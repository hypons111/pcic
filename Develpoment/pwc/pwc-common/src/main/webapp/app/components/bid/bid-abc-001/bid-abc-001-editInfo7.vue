<template>
  <div>
    <div class="card">
      <div class="card-body col-12">
        <div>
          <i-button size="md" type="folder-plus" @click="toAdd"></i-button>
        </div>
        <div>
          <i-table
            class="table-sm"
            :itemsUndefinedBehavior="'loading'"
            :items="table.data"
            :fields="table.fields"
            :totalItems="table.totalItems"
            :is-server-side-paging="true"
            @changePagination="handlePaginationChanged($event)"
          >
            <template #cell(action)>
              <i-button size="md" type="pencil-square" @click="toEdit"></i-button>
              <i-button size="md" type="trash" @click="toDelete"></i-button>
              <i-button size="md" type="binoculars" @click="toSee"></i-button>
            </template>
          </i-table>
        </div>
      </div>
    </div>

    <!-- 新增 -->
    <b-modal title="新增查核相片" id="graph-add-modal" size="lg" header-bg-variant="secondary" header-text-variant="light" centered>
      <b-form-row>
        <i-form-group-check label="上傳日期：" :item="$v.updateDate" class="col-12" label-cols="2" content-cols="10">
          {{ $v.updateDate.$model | dateFilter }}
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check label="檔案名稱：" :item="$v.fileName" class="col-12" label-cols="2" content-cols="10">
          <b-form-input v-model="$v.fileName.$model" :state="validateState($v.fileName)"></b-form-input>
        </i-form-group-check>
      </b-form-row>

      <b-form-row>
        <i-form-group-check label="檔案類別：" :item="$v.type" class="col-12" label-cols="2" content-cols="10">
          <b-form-radio-group v-model="$v.type.$model" :state="validateState($v.type)" />
        </i-form-group-check>
      </b-form-row>

      <b-form-row>
        <i-form-group-check label="檔案說明：" :item="$v.gphmo" class="col-12" label-cols="2" content-cols="10">
          <b-form-textarea rows="3" v-model="$v.gphmo.$model" :state="validateState($v.gphmo)"></b-form-textarea>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check label="檔案上傳：" :item="$v.file" class="col-12" label-cols="2" content-cols="10">
          <b-form-file
            v-model="$v.file.$model"
            :disabled="$v.type.$model !== '1' && $v.type.$model !== '2' && $v.type.$model !== '3'"
            :accept="$v.type.$model === '1' ? '.jpg,.jpge' : $v.type.$model === '2' ? '.mp4' : $v.type.$model === '3' ? '.word,.pdf' : ''"
            :state="Boolean($v.file.$model)"
            browse-text="瀏覽..."
            :placeholder="$v.file.$model ? $v.file.$model.name : ''"
          >
          </b-form-file>
          <div v-if="$v.file.$model" class="mt-3">已選擇檔案: {{ $v.file.$model ? $v.file.$model.name : '' }}</div>
        </i-form-group-check>
      </b-form-row>
      <template #modal-footer="{ cancel }">
        <div class="col-12 text-center">
          <b-button size="md" @click="submitForm">儲存</b-button>
          <b-button size="md" @click="cancel">取消</b-button>
          <b-button size="md" @click="reset">清除</b-button>
        </div>
      </template>
    </b-modal>

    <!-- 修改 -->
    <b-modal title="編輯查核相片" id="graph-modify-modal" size="lg" header-bg-variant="secondary" header-text-variant="light" centered>
      <b-form-row>
        <i-form-group-check label="上傳日期：" :item="$v.updateDate" class="col-12" label-cols="2" content-cols="10">
          {{ $v.updateDate.$model | dateFilter }}
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check label="檔案名稱：" :item="$v.fileName" class="col-12" label-cols="2" content-cols="10">
          <b-form-input v-model="$v.fileName.$model" :state="validateState($v.fileName)"></b-form-input>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check label="檔案類別：" :item="$v.type" class="col-12" label-cols="2" content-cols="10">
          <b-form-radio-group v-if="$v.type.$model !== 'Z'" v-model="$v.type.$model" :state="validateState($v.type)" />
          <label v-if="$v.type.$model === 'Z'">{{ $v.type.$model | typeFilter }}</label>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check label="檔案說明：" :item="$v.gphmo" class="col-12" label-cols="2" content-cols="10">
          <b-form-textarea rows="3" v-model="$v.gphmo.$model" :state="validateState($v.gphmo)"></b-form-textarea>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check label="檔案上傳：" :item="$v.file" class="col-12" label-cols="2" content-cols="10">
          <b-form-file
            v-model="$v.file.$model"
            :disabled="$v.type.$model !== '1' && $v.type.$model !== '2' && $v.type.$model !== '3'"
            :accept="$v.type.$model === '1' ? '.jpg,.jpge' : $v.type.$model === '2' ? '.mp4' : $v.type.$model === '3' ? '.word,.pdf' : ''"
            :state="Boolean($v.file.$model)"
            browse-text="瀏覽..."
            :placeholder="$v.file.$model ? $v.file.$model.name : ''"
          >
          </b-form-file>
          <div v-if="$v.file.$model" class="mt-3">已選擇檔案: {{}}</div>
        </i-form-group-check>
      </b-form-row>
      <template #modal-footer="{ cancel }">
        <div class="col-12 text-center">
          <b-button size="md" @click="submitForm">儲存</b-button>
          <b-button size="md" @click="cancel">取消</b-button>
          <b-button size="md" @click="reset">清除</b-button>
        </div>
      </template>
    </b-modal>

    <!-- 檢視 -->
    <b-modal id="graph-view-modal" size="lg" title="檢視查核相片" header-bg-variant="secondary" header-text-variant="light" centered>
      <b-form-row>
        <i-form-group-check label="上傳日期：" :item="$v.updateDate" class="col-12" label-cols="2" content-cols="10">
          {{ $v.updateDate.$model | dateFilter }}
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check label="檔案名稱：" :item="$v.fileName" class="col-12" label-cols="2" content-cols="10">
          {{ $v.fileName.$model }}
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check label="檔案類別：" :item="$v.type" class="col-12" label-cols="2" content-cols="10">
          {{ $v.type.$model | typeFilter }}
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check label="檔案說明：" :item="$v.gphmo" class="col-12" label-cols="2" content-cols="10">
          {{ $v.gphmo.$model }}
        </i-form-group-check>
      </b-form-row>
      <!-- <b-form-row>
        <i-form-group-check label="外部檔案位置：" class="col-12" label-cols="2" content-cols="10">
          {{ $v.gphfil.$model }}
        </i-form-group-check>
      </b-form-row> -->
      <b-form-row>
        <i-form-group-check label="已上傳檔案：" class="col-12" label-cols="2" content-cols="10">
          <b-table-simple borderless v-if="$v.type.$model !== 'Z'">
            <b-tr>
              <b-td>
                {{ $v.file.$model ? $v.file.$model.name : '' }}
              </b-td>
              <b-td>
                <i-button size="sm" type="file-earmark-arrow-down" @click="download($v.file.$model)"></i-button>
              </b-td>
            </b-tr>
            <b-tr>
              <b-td colspan="2">
                <b-img
                  v-if="$v.type.$model === '1'"
                  class="mt-2 mr-4"
                  style="height: 300px"
                  alt="Image"
                  fluid
                  thumbnail
                  :src="picPreview"
                />
              </b-td>
            </b-tr>
          </b-table-simple>
        </i-form-group-check>
      </b-form-row>
      <template #modal-footer="{ cancel }">
        <div class="col-12 text-center">
          <b-button size="md" @click="cancel">取消</b-button>
        </div>
      </template>
    </b-modal>

    <!-- 刪除 -->
    <b-modal title="刪除查核相片" id="graph-delete-modal" size="lg" header-bg-variant="secondary" header-text-variant="light" centered>
      <b-form-row>
        <i-form-group-check label="上傳日期：" :item="$v.updateDate" class="col-12" label-cols="2" content-cols="10">
          {{ $v.updateDate.$model | dateFilter }}
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check label="檔案名稱：" :item="$v.fileName" class="col-12" label-cols="2" content-cols="10">
          <b-form-input v-model="$v.fileName.$model" :state="validateState($v.fileName)"></b-form-input>
        </i-form-group-check>
      </b-form-row>

      <b-form-row>
        <i-form-group-check label="檔案類別：" :item="$v.type" class="col-12" label-cols="2" content-cols="10">
          <b-form-radio-group v-model="$v.type.$model" :state="validateState($v.type)" />
        </i-form-group-check>
      </b-form-row>

      <b-form-row>
        <i-form-group-check label="檔案說明：" :item="$v.gphmo" class="col-12" label-cols="2" content-cols="10">
          <b-form-textarea rows="3" v-model="$v.gphmo.$model" :state="validateState($v.gphmo)"></b-form-textarea>
        </i-form-group-check>
      </b-form-row>
      <b-form-row>
        <i-form-group-check label="檔案上傳：" :item="$v.file" class="col-12" label-cols="2" content-cols="10">
          <b-form-file
            v-model="$v.file.$model"
            :disabled="$v.type.$model !== '1' && $v.type.$model !== '2' && $v.type.$model !== '3'"
            :accept="$v.type.$model === '1' ? '.jpg,.jpge' : $v.type.$model === '2' ? '.mp4' : $v.type.$model === '3' ? '.word,.pdf' : ''"
            :state="Boolean($v.file.$model)"
            browse-text="瀏覽..."
            :placeholder="$v.file.$model ? $v.file.$model.name : ''"
          >
          </b-form-file>
          <div v-if="$v.file.$model" class="mt-3">已選擇檔案: {{ $v.file.$model ? $v.file.$model.name : '' }}</div>
        </i-form-group-check>
      </b-form-row>
      <template #modal-footer="{ cancel }">
        <div class="col-12 text-center">
          <b-button size="md" @click="toDelete">確認刪除</b-button>
          <b-button size="md" @click="cancel">取消</b-button>
        </div>
      </template>
    </b-modal>

    <!-- 錯誤訊息 -->
    <b-modal
      id="valid-error-modal"
      size="lg"
      title="資料驗證有誤:"
      :header-text-variant="'light'"
      :header-bg-variant="'danger'"
      hide-footer
    >
      <div class="d-block text-center">以下欄位填報有誤!!</div>
      <div v-for="(item, index) in errorMessages" :key="index">
        <h5>{{ item }}</h5>
      </div>
      <b-button class="mt-3" block @click="$bvModals.hide('valid-error-modal')">了解</b-button>
    </b-modal>
  </div>
</template>

<script lang="ts">
import { Ref, ref, toRef, reactive, watch, computed, Reactive, onMounted } from '@vue/composition-api';
import { useValidation, validateState } from '@/shared/form';
import { required, numeric, maxValue } from '@/shared/validators';
import BidSysCodeTable from '@/components/bid/bid-sys-code-table-class/bid-SysCode.class';
import { useBvModal } from '@/shared/modal';
import { navigateByNameAndParams } from '@/router/router';
import { useNotification } from '@/shared/notification';
import { formatDate, parseRocDate } from '@/shared/date/minguo-calendar-utils';

export default {
  name: 'bidAbc001EditInfo7',
  props: {
    bidProjectKey: {
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectModifyKeyProp: Ref<any> = toRef(props, 'bidProjectKey');

    onMounted(() => {
      handleQuery();
    });

    //透過modal show錯誤訊息
    const $bvModals = useBvModal();
    let errorMessages = ref([]);

    const notificationService: NotificationService = useNotification();

    // 區塊是否顯示
    const stepVisible = reactive({
      step1: true,
    });

    const formDefault = {
      updateDate: new Date(), // 上傳日期
      fileName: '', //檔案名稱：
      type: '', //檔案類別：
      gphmo: '', //檔案說明：
      file: null, //檔案上傳：
    };

    const rules = {
      updateDate: {},
      fileName: {},
      type: {},
      gphmo: {},
      file: {},
    };

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(criteria, pagination);
      handleQuery();
    };

    const table = ref([]);
    const handleQuery = () => {
      table.data = [];
      table.totalItems = 3;
      table.data.splice(0, table.data.length, ...mockdata);
    };

    const table = reactive({
      fields: [
        {
          key: 'a',
          label: '檔案名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },

        {
          key: 'b',
          label: '檔案說明',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'c',
          label: '上傳日期',
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
          thStyle: 'width:20%',
        },
      ],
      data: undefined,
      totalItems: 6,
    });

    const mockdata = [
      {
        a: '查核相片1',
        b: '查核相片1111',
        c: '111/04/12',
      },
      {
        a: '查核相片2',
        b: '查核相片2222',
        c: '111/03/02',
      },
      {
        a: '查核相片3',
        b: '查核相片3333',
        c: '111/02/10',
      },
    ];

    // const formDefault = {};

    // const rules = {};

    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    //回上一頁
    function backToPreviousPage() {
      reset();
      navigateByNameAndParams('bidAbc001Query');
    }

    const toAdd = () => {
      $bvModals.show('graph-add-modal');
    };
    const toEdit = () => {
      $bvModals.show('graph-modify-modal');
    };
    const toSee = () => {
      $bvModals.show('graph-view-modal');
    };
    const toDelete = () => {
      $bvModals.show('graph-delete-modal');
    };
    const submitForm = () => {};
    const cancel = () => {};
    const reset = () => {};

    return {
      $v,
      reset,
      $bvModals,
      errorMessages,
      backToPreviousPage,
      validateState,
      formDefault,
      stepVisible,
      handlePaginationChanged,
      table,
      handleQuery,
      toAdd,
      toEdit,
      toSee,
      submitForm,
      cancel,
      toDelete,
    };
  },
  filters: {
    dateFilter: (value: any) => formatDate(value, '/'),
    typeFilter: (value: string) => {
      if (value === '1') {
        return '施工相片';
      } else if (value === '2') {
        return '施工影像';
      } else if (value === '3') {
        return '施工文件';
      } else if (value === 'Z') {
        return '舊案(無上傳檔)';
      }
    },
  },
};
</script>

<style></style>

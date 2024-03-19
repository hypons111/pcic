<template>
  <div>
    <section class="container mt-2">
      <bidProjectInfo :projectInfoProp="projectInfo" :key="projectInfo.wkut + projectInfo.prjno"></bidProjectInfo>
    </section>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <h5 class="m-0">施工相片影像文件資料</h5>
        </div>
        <div class="card-body">
          <div class="mb-1">
            <i-button type="file-earmark-plus" @click="showGraphAddModel"></i-button>
          </div>
          <i-table
            border0
            class="table-sm"
            :itemsUndefinedBehavior="'loading'"
            :items="table.data"
            :fields="table.fields"
            :totalItems="table.totalItems"
            :is-server-side-paging="true"
            @changePagination="handlePaginationChanged($event)"
          >
            <template #cell(action)="row">
              <i-button type="pencil-square" @click="showGraphModifyModel(row.item)"></i-button>
              <i-button type="trash" @click="showGraphDeleteModel(row.item)"></i-button>
              <i-button type="binoculars" @click="showGraphViewModel(row.item)"></i-button>
            </template>
          </i-table>
        </div>
      </div>
    </section>
    <!-- 新增 -->
    <b-modal
      title="新增施工相片影像文件"
      id="graph-add-modal"
      size="lg"
      header-bg-variant="secondary"
      header-text-variant="light"
      centered
    >
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
          <b-form-radio-group v-model="$v.type.$model" :options="typeOptions.type" :state="validateState($v.type)" />
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
    <b-modal
      title="修改施工相片影像文件"
      id="graph-modify-modal"
      size="lg"
      header-bg-variant="secondary"
      header-text-variant="light"
      centered
    >
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
          <b-form-radio-group v-if="$v.type.$model !== 'Z'" v-model="$v.type.$model" :options="typeOptions.type" :state="validateState($v.type)" />
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
    <!-- 刪除 -->
    <b-modal
      id="graph-delete-modal"
      size="lg"
      title="刪除施工相片影像文件"
      header-bg-variant="secondary"
      header-text-variant="light"
      centered
    >
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
      <template #modal-footer="{ cancel }">
        <div class="col-12 text-center">
          <b-button size="md" @click="deletePrjGph">確認刪除</b-button>
          <b-button size="md" @click="cancel">取消</b-button>
        </div>
      </template>
    </b-modal>
    <!-- 檢視 -->
    <b-modal
      id="graph-view-modal"
      size="lg"
      title="檢視施工相片影像文件"
      header-bg-variant="secondary"
      header-text-variant="light"
      centered
    >
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
      <b-form-row>
        <i-form-group-check label="外部檔案位置：" class="col-12" label-cols="2" content-cols="10">
          {{ $v.gphfil.$model }}
        </i-form-group-check>
      </b-form-row>
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
          <b-button size="md" @click="cancel">關閉</b-button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import bidProjectInfo from '@/components/bid/bid-common-component/bid-project-info.vue';
import BidProjectService from '@/components/bid/bidService/bid-project.service';
import FileSaver from 'file-saver';
import { useValidation, validateState } from '@/shared/form';
import i18n from '@/shared/i18n';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useBvModal } from '@/shared/modal';
import { Pagination } from '@/shared/model/pagination.model';
import { formatDate, parseRocDate } from '@/shared/date/minguo-calendar-utils';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { required, requiredIf } from '@/shared/validators';
import { Ref, ref, toRef, reactive, watch, onActivated, unref } from '@vue/composition-api';
;

export default {
  name: 'bidAad007InfoMenu',
  components: { bidProjectInfo },
  props: {
    bidProjectKey: {
      type: Object,
      required: false,
    },
  },
  setup(props, context) {
    const bidProjectKeyProp: Ref<any> = toRef(props, 'bidProjectKey');
    //在keep alive的時候保留原本的project資料
    const tempProjectKey = {
      wkut: '',
      prjno: '',
    };
    setTempProjectKey();

    const bidProjectService = new BidProjectService();
    const projectInfo = reactive({ wkut: '', prjno: '' });

    const picPreview = ref(null); // 照片預覽
    const picPreviewDefault = ref(null); // 照片預覽

    // 宣告表單物件及初始值，也可以是後端查回來的資料物件
    let formDefault = {
      wkut: '',
      prjno: '',
      phrase: '',
      seq: '',
      admFileId: undefined,
      updateDate: new Date(), // 上傳日期
      fileName: '', // 檔案名稱
      type: '', // 類別
      gphmo: '', // 檔案說明
      file: null,
      gphfil: '',
    };

    // 表單物件驗證規則
    const rules = {
      wkut: {},
      prjno: {},
      phrase: {},
      seq: {},
      admFileId: {},
      updateDate: {}, // 上傳日期
      fileName: { required }, // 檔案名稱
      type: {
        requiredIf: requiredIf(() => form.type !== 'Z'),
      }, // 類別
      gphmo: {}, // 檔案說明
      file: {
        requiredIf: requiredIf(() => form.type !== 'Z'),
      },
      gphfil: {},
    };

    // 建立表單物件 ref
    const form = reactive(Object.assign({}, formDefault));
    const formEmpty = Object.assign({}, formDefault);
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    // 執行結果
    const queryStatus = ref(false);
    const dataPromise = ref(null);

    const notificationService: NotificationService = useNotification();

    // 下拉選單選項
    const typeOptions = reactive({
      type: [
        { value: '1', text: '施工相片' },
        { value: '2', text: '施工影像' },
        { value: '3', text: '施工文件' },
      ],
    });

    const table = reactive({
      fields: [
        {
          key: 'fileName',
          label: '檔案名稱',
          sortable: false,
          thStyle: 'width:30%',
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          key: 'type',
          label: '檔案類別',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: string) => {
            if (value === '1') {
              return '施工相片';
            } else if (value === '2') {
              return '施工影像';
            } else if (value === '3') {
              return '施工文件';
            } else if (value === 'Z') {
              return '舊案(無上傳檔)';
            }
          }
        },
        {
          key: 'updateDate',
          label: '上傳日期',
          sortable: false,
          thStyle: 'width:15%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          formatter: (value: any) => formatDate(value, '/'),
        },
        {
          key: 'action',
          label: i18n.t('label.action'),
          sortable: false,
          thStyle: 'width:30%',
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    enum formStatusEnum {
      INFO = 'info',
      CREATE = 'create',
      MODIFY = 'modify',
    }

    // 表單操作狀態
    const formStatusRef: Ref<formStatusEnum> = ref(formStatusEnum.INFO);

    function fetchAllBidPrjGph(criteria) {
      table.data = undefined;
      queryStatus.value = true;
      dataPromise.value = axios
        .post('/bid-prj-gphs/getPages', criteria) //後端分頁
        .then(({ data }) => {
          table.data = data.content.slice(0, data.content.length); //後端分頁y
          table.totalItems = data.totalElements; //後端分頁
        })
        .finally(() => (dataPromise.value = null))
        .catch(notificationErrorHandler(notificationService));
    };

    /**
     * 撈出project的部分資料
     */
    function fetchBidProjectInfo(wkut, prjno) {
      bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut, prjno).then(result => {
        Object.keys(result).forEach(key => (projectInfo[key] = result[key]));
      });
    }

    function setTempProjectKey() {
      if (bidProjectKeyProp.value) {
        tempProjectKey.wkut = bidProjectKeyProp.value.wkut;
        tempProjectKey.prjno = bidProjectKeyProp.value.prjno;
      }
    }

    function handlePaginationChanged(pagination: Pagination) {
      let queryCriteria = {
        wkut: tempProjectKey.wkut,
        prjno: tempProjectKey.prjno,
      };
      Object.assign(queryCriteria, pagination);
      fetchAllBidPrjGph(queryCriteria);
    }

    const $bvModal: BvModal = useBvModal();

    function showGraphAddModel() {
      let bidPrjGph = {
        wkut: projectInfo.wkut,
        prjno: projectInfo.prjno,
        phrase: '0',
        seq: 0,
      };
      axios
        .post('/bid-prj-gphs/getMaxSeq', bidPrjGph) //後端分頁
        .then(({ data }) => {
          bidPrjGph.seq = data + 1;
          Object.assign(formDefault, formEmpty);
          createDefaultValue(bidPrjGph);
          delete form['id'];
          formStatusRef.value = formStatusEnum.CREATE;
          $bvModal.show('graph-add-modal');
          // console.log(form);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    const showGraphModifyModel = (bidPrjGph) => {
      createDefaultValue(bidPrjGph);
      formStatusRef.value = formStatusEnum.MODIFY;
      $bvModal.show('graph-modify-modal');
    }

    const showGraphDeleteModel = (bidPrjGph) => {
      createDefaultValue(bidPrjGph);
      $bvModal.show('graph-delete-modal');
    }

    const showGraphViewModel = (bidPrjGph) => {
      createDefaultValue(bidPrjGph);
      $bvModal.show('graph-view-modal');
    }

    function createDefaultValue(bidPrjGph) {
      if (bidPrjGph.updateDate != null) {
        bidPrjGph.updateDate = new Date(bidPrjGph.updateDate);
      }
      Object.assign(formDefault, bidPrjGph);
      Object.assign(form, formDefault);
      reset();
      getPic();
    };

    function getPic() {
      if (!form.admFileId) return;
      axios
        .get(`/adm-files/download/${form.admFileId}`, { responseType: 'blob' })
        .then((res: AxiosResponse<Blob>) => {
          const blob = res.data;
          const content = String(res.headers['content-disposition']);
          const fileName = decodeURI(
            content
              .substring(content.lastIndexOf('filename*=') + 17)
              .replace(/"/g, '')
              .replace(/\+/g, '')
          );
          const extName = fileName.substring(fileName.lastIndexOf('.'));
          form.file = new File([blob], fileName, { type: `text/${extName};charset=utf-8` });
          picSelected(form.file);
        })
        .catch(notificationErrorHandler(notificationService));
    }

    function picSelected(file: File | Blob) {
      const fileReader = new FileReader();
      fileReader.onload = e => {
        picPreview.value = e.target.result;
      };
      if (file && file.size !== 0) {
        fileReader.readAsDataURL(file);
      } else if (unref(picPreviewDefault) && unref(picPreviewDefault).size !== 0) {
        fileReader.readAsDataURL(unref(picPreviewDefault));
      } else {
        picPreview.value = null;
      }
    }

    const submitForm = () => {
      checkValidity().then((isValid: boolean) => {
        if (isValid) {
          $bvModal.msgBoxConfirm('是否確認送出修改內容？').then((isOK: boolean) => {
            if (isOK) {
              putForm();
            }
          });
        } else {
          $bvModal.msgBoxOk('欄位尚未填寫完畢，請於輸入完畢後再行送出。');
        }
      });
    };


    const putForm = () => {
      if (formStatusRef.value === formStatusEnum.CREATE) {
        const formData = new FormData();
        formData.append('form', new Blob([JSON.stringify(form)], { type: 'application/json' }));
        formData.append('file', form.file);
        axios
          .post('/bid-prj-gphs',
            formData, { headers: { 'Content-Type': 'multipart/form-data' } })
          .then(res => {
            fetchAllBidPrjGph(res.data);
            createDefaultValue(res.data);
            formStatusRef.value = formStatusEnum.MODIFY;
            // console.log("insert");
          })
          .catch(err => {
            if (err.response && err.response.data.errorKey === 'bidValidError') {
              errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
              $bvModal.show('valid-error-modal');
            }
          });
      } else if (formStatusRef.value === formStatusEnum.MODIFY) {
        const formData = new FormData();
        formData.append('form', new Blob([JSON.stringify(form)], { type: 'application/json' }));
        formData.append('file', form.file);
        axios
          .put('/bid-prj-gphs',
            formData, { headers: { 'Content-Type': 'multipart/form-data' } })
          .then(({ data }) => {
            createDefaultValue(data);
            fetchAllBidPrjGph(data);
            // console.log("update");
          })
          .catch(err => {
            if (err.response && err.response.data.errorKey === 'bidValidError') {
              errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
              $bvModal.show('valid-error-modal');
            }
          });
      }
    };

    const deletePrjGph = () => {
    let bidPrjGph = {
      wkut: projectInfo.wkut,
      prjno: projectInfo.prjno,
    };
    axios
      .delete('/bid-prj-gphs', { data: form })
      .then(() => {
        fetchAllBidPrjGph(bidPrjGph);
        $bvModal.hide('graph-delete-modal');
        // console.log("delete");
      })
      .catch(err => {
        if (err.response && err.response.data.errorKey === 'bidValidError') {
          errorMessages.value.splice(0, errorMessages.value.length, ...err.response.data.detailMessages);
          $bvModal.show('valid-error-modal');
        }
      });
    };

    function download(file: File) {
      FileSaver.saveAs(file, file.name);
    }

    watch(
      bidProjectKeyProp,
      newValue => {
        setTempProjectKey();
        if (newValue !== null && newValue !== undefined) {
          fetchBidProjectInfo(newValue.wkut, newValue.prjno);
          fetchAllBidPrjGph(newValue);
        }
      },
      { immediate: true }
    );

    onActivated(() => {
      if (bidProjectKeyProp.value) {
        fetchAllBidPrjGph(bidProjectKeyProp.value);
      } else if (tempProjectKey.wkut.length > 0) {
        fetchAllBidPrjGph(tempProjectKey);
      }
    });

    return {
      bidProjectKeyProp,
      projectInfo,
      table,
      queryStatus,
      dataPromise,
      showGraphAddModel,
      showGraphModifyModel,
      showGraphDeleteModel,
      showGraphViewModel,
      $v,
      typeOptions,
      validateState,
      reset,
      submitForm,
      handlePaginationChanged,
      deletePrjGph,
      picPreview,
      download,
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

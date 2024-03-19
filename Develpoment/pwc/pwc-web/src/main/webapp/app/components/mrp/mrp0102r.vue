<template>
  <div>
    <button id="addModalSwitch" class="hidden" data-toggle="modal" data-target="#exampleModal" @click="setModal('新增')">add</button>
    <button id="editModalSwitch" class="hidden" data-toggle="modal" data-target="#exampleModal" @click="setModal('編輯')">edit</button>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel"></h5>
            <button id="closeModalButton" type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="container-fluid">
              <div id="modal-table" class="row">
                <table class="col-12">
                  <thead>
                    <tr>
                      <th class="hidden"></th>
                      <th>俗別名</th>
                      <th>工程編碼用詞</th>
                      <th></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr id="insertButtonHere">
                      <td class="hidden"><input id="synonymId" value="" /></td>
                      <td><input type="text" id="newKeyword" name="newKeyword" value="" /></td>
                      <td><input type="text" id="newPhrase" name="newPhrase" value="" /></td>
                      <td><button id="submitAddButton" class="hidden btn btn-info">確定</button></td>
                      <td><button id="submitEditButton" class="hidden btn btn-info">確定</button></td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 查詢條件 -->
    <section class="container mt-2 mb-2">
      <div class="h4 m-0 py-2 button-border">同義詞管理</div>
      <div class="card mt-2">
        <div class="card-header text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="search"></font-awesome-icon>
                查詢條件
              </h5>
            </div>
          </div>
        </div>

        <div class="card-body">
          <div id="AdmSysCodeAGroup" class="flex card-row">
            <div class="flex item">
              <label for="codes">篇名</label>
              <select name="codes" id="codes">
                <option disabled selected value>請選擇</option>
              </select>
              <div class="block"></div>
            </div>
          </div>

          <div class="buttonGroup flex card-row">
            <button id="queryButton" class="btn btn-info" @click="queryMrpSynonym">查詢</button>
          </div>
        </div>
      </div>
    </section>

    <!-- 查詢結果 -->
    <section id="resultSection" class="container mt-4 mb-2 hidden">
      <mrp-table
        ref="iTable"
        class="table-sm"
        :page="'mrp0102r'"
        :addButton="true"
        :downloadButton="true"
        :hideNo="true"
        :itemsUndefinedBehavior="'loading'"
        :items="table.data"
        :fields="table.fields"
        :is-server-side-paging="true"
        :totalItems="table.totalItems"
        @changePagination="handlePaginationChanged($event)"
      >
        <template #cell(options)="row">
          <div>
            <div>
              <button
                id="submitEditButton"
                class="btn btn-info"
                data-toggle="modal"
                data-target="#exampleModal"
                @click="doUpdate(row.item)"
              >編輯</button>
              <button class="btn btn-info" @click="deleteMrpSynonym(row.item.id)">刪除</button>
            </div>
          </div>
        </template>
      </mrp-table>
    </section>
  </div>
</template>
<script lang="ts">
import mrpTable from '@/shared/i-table/mrp-table.vue';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { onMounted, reactive } from '@vue/composition-api';
import axios from 'axios';

export default {
  components: { mrpTable },
  setup() {
    const notificationService: NotificationService = useNotification();
    let currentSectionName = '';
    let code = undefined;
    let type = undefined;
    const table = reactive({
      fields: [
        {
          key: 'keyword',
          label: '俗別名',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'phrase',
          label: '工程編碼用詞',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'options',
          label: '編輯',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: [],
      totalItems: undefined,
    });
    let currentPage = 0; // 後端分頁用
    let totalPate = 0; // 後端分頁用

    function addListener() {
      document.querySelector('#addButton').addEventListener("click", () => { doAdd() })
      document.querySelector('#downloadButton').addEventListener("click", () => { doDownload() })
      document.querySelector('#submitAddButton').addEventListener('click', () => { addMrpSynonym(); })
      document.querySelector('#submitEditButton').addEventListener('click', () => {  updateMrpSynonym() });
    }

    function queryAdmSysCodeA() {
      axios.get('/service/MRP0102R/queryAdmSysCodeA').then(({ data }) => {
        data.response.forEach(code => {
          document.querySelector('#codes').innerHTML += `<option class="dropdown-item" value="${code.dataKey}" data-type="${code.dataKeyType}">${code.sectionName}</option>`;
        });
      });
    }

    function queryMrpSynonym() {
      const e = document.querySelector('#codes');
      code = e.value;
      type = e.options[e.selectedIndex];
      axios
        .get(`/service/MRP0102R/queryMrpSynonym?sectionName=${code}&dataKeyType=${type.dataset.type}`)
        .then(({ data }) => {
          table.totalItems = data.response.length;
          table.data = data.response;
        })
        .catch(error => { console.log("ERROR: ", error) })
        .finally(() => { 
          document.querySelector('#resultSection').classList.remove("hidden") 
          currentSectionName = e.value
        })
    }

    function doAdd() {
      document.querySelector('#addModalSwitch').click();
      document.querySelector('#newKeyword').value = "";
      document.querySelector('#newPhrase').value = "";
    }

    function doUpdate(data) {
      document.querySelector('#editModalSwitch').click();
      document.querySelector('#synonymId').value = data.id;
      document.querySelector('#newKeyword').value = data.keyword;
      document.querySelector('#newPhrase').value = data.phrase;
    }

    function setModal(title) {
      document.querySelector('#exampleModalLabel').innerText = title;
      if (title === '新增') {
        document.querySelector('#submitEditButton').classList.add('hidden');
        document.querySelector('#submitAddButton').classList.remove('hidden');
      } else if (title === '編輯') {
        document.querySelector('#submitAddButton').classList.add('hidden');
        document.querySelector('#submitEditButton').classList.remove('hidden');
      }
    }

    function addMrpSynonym() {
      const newKeyword = document.querySelector('#newKeyword').value.trim();
      const newPhrase = document.querySelector('#newPhrase').value.trim();
      if (newKeyword === '' && newPhrase === '') {
        notificationService.danger('請輸入俗別名和工程編碼用詞');
        return;
      }
      const requestData = {
        sectionName: currentSectionName,
        keyword: newKeyword,
        phrase: newPhrase,
        dataKeyType: 'CHAPTER',
        createId: 'SYS',
      };

      axios
        .post('/service/MRP0102R/addMrpSynonym', requestData)
        .catch(error => {  console.log("ERROR: ", error)  })
        .finally(() => {
          document.querySelector('#closeModalButton').click();
          document.querySelector('#queryButton').click();
        });
    }

    function updateMrpSynonym() {
      const synonymId = document.querySelector('#synonymId').value.trim();
      const newKeyword = document.querySelector('#newKeyword').value.trim();
      const newPhrase = document.querySelector('#newPhrase').value.trim();

      if(newKeyword !== "" && newPhrase !== "") {
        const requestData = {
          mrpSynonymId: synonymId,
          keyword: newKeyword,
          phrase: newPhrase,
          updateId: 'what is this ?',
        };
        axios
          .post('/service/MRP0102R/updateMrpSynonym', requestData)
          .catch(error => {  console.log("ERROR: ", error)  })
          .finally(() => {
            document.querySelector('#closeModalButton').click();
            document.querySelector('#queryButton').click();
          });
      } else {
        notificationService.danger("請輸入俗別名或工程編碼用詞")
      }
    }

    function deleteMrpSynonym(id) {
      const requestData = { mrpSynonymId: id };
      axios
        .post('/service/MRP0102R/deleteMrpSynonym', requestData)
        .catch(error => { console.log("ERROR: ", error) })
        .finally(() => {
          document.querySelector('#closeModalButton').click();
          document.querySelector('#queryButton').click();
        });
    }

    function doDownload() {
      const requestData = {
        sectionName: currentSectionName,
      };
      axios
        .post('/service/MRP0102R/downloadFile', requestData, { responseType: 'blob' })
        .then(response => {
          const href = URL.createObjectURL(response.data);
          const link = document.createElement('a');
          link.href = href;
          link.setAttribute('download', `${requestData.sectionName}.xls`);
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
          URL.revokeObjectURL(href);
        });
    }

    const handlePaginationChanged = (pagination : Pagination, requestData) => {
      const form = {}
      Object.assign(form, pagination);
      Object.assign(form, requestData);
      handleQuery(form);
    };

    const handleQuery = form => {
      axios
        .get(`/service/MRP0102R/queryMrpSynonym?sectionName=${code}&dataKeyType=${type.dataset.type}`)
        .then(({ data }) => {
          table.data = data.response.slice((form.currentPage * form.perPage), data.response.length);
        })
        .finally()
    };

    onMounted(() => {
      queryAdmSysCodeA();
      addListener();
    });

    return {
      table,
      currentSectionName,
      queryMrpSynonym,
      addListener,
      doAdd,
      doUpdate,
      setModal,
      addMrpSynonym,
      updateMrpSynonym,
      deleteMrpSynonym,
      doDownload,
      handlePaginationChanged
    };
  },
};
</script>

<style scoped>
@import 'mrp.css';
#codeList {
  height: 300px;
  overflow-y: scroll;
  overflow-x: hidden;
}
</style>

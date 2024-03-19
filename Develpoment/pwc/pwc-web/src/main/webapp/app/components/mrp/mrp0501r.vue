<template>
  <div>
    <!--    查詢結果-->
    <section class="container mt-2">
      <div class="h4 m-0 py-2 button-border">稽催報表</div>
      <div class="card mt-2">
        <mrp-table
          ref="iTable"
          class="table-sm"
          :page="'mrp0501r'"
          :hideCardHeader="true"
          :hideNo="true"
          :footerDownloadButton="true"
          :footerQueryButton="true"
          :itemsUndefinedBehavior="'loading'"
          :items="table.data"
          :fields="table.fields"
          :is-server-side-paging="true"
          :totalItems="table.totalItems"
          @changePagination="handlePaginationChanged($event)"
        >
          <template #cell(subject)="row">
            <div data-toggle="modal" data-target="#exampleModal" @click="showModalContents(row.item)">{{ row.item.subject }}</div>
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel" style="color: black">稽催報表</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    <table>
                      <tbody></tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </template>
        </mrp-table>
      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { validateState } from '@/shared/form';
import mrpTable from '@/shared/i-table/mrp-table.vue';
import { Pagination } from '@/shared/model/pagination.model';
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { onMounted, reactive } from '@vue/composition-api';
import axios from 'axios';

export default {
  components: { mrpTable },
  data() {
    return {
      date: '',
      title: '',
      relativeList: [],
    };
  },

  methods: {
    setData(date, title, relativeList) {
      this.date = date;
      this.title = title;
      // this.relativeList = relativeList
    }
  },

  props: {
    isReload: {
      required: false,
    },
  },

  setup() {
    const notificationService: NotificationService = useNotification();
    let currentPage = 0; // 後端分頁用
    let totalPate = 0; // 後端分頁用
    const table = reactive({
      fields: [
        {
          key: 'date',
          label: '日期',
          sortable: false,
          thClass: 'text-center col-3',
          tdClass: 'text-center align-middle'
        },
        {
          key: 'subject',
          label: '主旨',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle blue',
        },
      ],
      data: undefined,
      totalItems: undefined,
    });

    function addListener() {
      document.querySelector('.modal-body table tbody').addEventListener('click', e => {
        if (e.target.classList.contains('blue')) {
          download(e.target.innerText);
        }
      });
    }

    // 查詢請求, 顯示結果
    function query0501() {
      axios
        .get('/service/MRP0501R/queryMrpReport')
        .then(({ data }) => {
          console.log('response data : ', data);
          table.data = data.response;
          table.totalItems = data.response.length;
        })
        .catch(error => { console.log("ERROR : ", error)})
        .finally(() => { addListener() })
    }

    function showModalContents(rowData) {
      console.log("TEST : rowData.id : ", rowData.id)
      document.querySelector('.modal-body table tbody').innerHTML = `
        <tr><td class="black">${rowData.date}<br>${rowData.subject}</td></tr>
          <tr><td></td></tr>
            <tr>
              <td class="black">
                相關檔案
                <br><a id="" class='blue'>${rowData.subject}.ods</a>
                <br><a id="" class='blue'>${rowData.subject}.xls</a>
              </td>
            </tr>
          `;
    }

    function download(fileName) {
      axios
        .get("/service/MRP0501R/downloadMrpReport", { 
          params: { fileName: fileName }, 
          responseType: 'blob'
        })
        .then((response) => {
          const href = URL.createObjectURL(response.data)
          const link = document.createElement('a')
          link.href = href;
          link.setAttribute('download', `${fileName}`)
          document.body.appendChild(link)
          link.click()
          document.body.removeChild(link)
          URL.revokeObjectURL(href)
        });
    }

    // 分頁
    const handlePaginationChanged = (pagination: Pagination) => {
      const form = {};
      Object.assign(form, pagination);
      handleQuery(form);
    };

    const handleQuery = form => {
        axios
          .get('/service/MRP0501R/queryMrpReport')
          .then(({ data }) => {
            table.data = data.response.slice((form.currentPage * form.perPage), data.response.length);
          })
        .finally()
    };

    onMounted(() => {
      query0501();
    });

    return {
      table,
      download,
      showModalContents,
      validateState,
      handleQuery,
      handlePaginationChanged,
    };
  },
};
</script>

<style>
div.modal-content {
  width: 700px;
  right: 100px;
}

.modal-body table {
  width: 100%;
  text-align: left;
}

.modal-body table td {
  border: 0px solid white;
}

.black {
  color: black;
}

.blue {
  color: blue;
}

.blue:hover {
  cursor: pointer;
}
</style>

<template>
  <div>
    <section class="container mt-2">
      <div class="card">

        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                個別計畫資訊
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

        <div class="card-body col-12">
          <table>
              <tr>
                <td><b>　計畫名稱：臺北都會區大眾捷運系統工程計畫－環狀線北環段及南環段暨周邊土地開發</b></td>
              </tr>
              <tr>
                <td><b>　主辦機關：臺北市政府捷運工程局　　　　主管機關：交通部</b></td>
              </tr>
          </table>
          <table>
              <tr>
                <td><b>　證照取得（％）：23</b></td>
                <td></td>
              </tr>
              <tr>
                <td><b>　應辦證照取得情形燈號：<b-img fluid rounded src="https://www.emojiall.com/en/header-svg/%F0%9F%9F%A1.png" style="height: 20px" alt="Image"></b-img></b></td>
                <td>


                  <table>
                    <tr>
                      <td><b>　紅燈<b-img fluid rounded src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT0lqm0r51wWWUqNe-IoVpz2DCIqqxB5DNM9OX0RmZ2PbVv_7rH2jI3ifr9jQ72zhNfAag&usqp=CAU" style="height: 20px" alt="Image"></b-img>(證照未如期取得)</b></td>
                    </tr>
                    <tr>
                      <td><b>　黃燈<b-img fluid rounded src="https://www.emojiall.com/en/header-svg/%F0%9F%9F%A1.png" style="height: 20px" alt="Image"></b-img>(證照未如期送審)</b></td>
                    </tr>
                    <tr>
                      <td><b>　綠燈<b-img fluid rounded src="https://www.emojiall.com/images/60/skype/1f7e2.png" style="height: 20px" alt="Image"></b-img>(無上述兩種情形)</b></td>
                    </tr>
                    <tr>
                      <td><b>　灰燈<b-img fluid rounded src="https://cdn-thumbnail.mamilove.com.tw/rzEEg_-uo1ZrQt-0SQqpgD9_20c=/1000x0/https://cdn-images.mamilove.com.tw/origin/item/ff9d5480624acf9bfe08-AP5S9b.jpg" style="height: 20px" alt="Image"></b-img>(目前尚無應辦證照)</b></td>
                    </tr>
                  </table>
                </td>
              </tr>
          </table>
        </div>

        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                應辦證照里程碑　　　　　　　　　　　　　　　　　　　　　資料日期：{{formatDateInChinese(new Date())}}
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

        <div class="card-body col-12">
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
            </i-table>
          </div>
        </div>

        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                應辦證照列表
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

        <div class="card-body col-12">
          <b-collapse v-model="stepVisible">
            <table border="2" cellpadding="2" width="100%">
              <tr>
                <th width="5%" class="text-center">項次</th>
                <th width="10%" class="text-center">目前狀態</th>
                <th width="20%" class="text-center">書件名稱</th>
                <th width="10%" class="text-center">證照類別</th>
                <th width="10%" class="text-center">審照機關</th>
                <th width="10%" class="text-center">預定完成日期</th>
                <th width="10%" class="text-center">實際完成日期</th>
                <th width="5%" class="text-center">提前/逾期天數</th>
                <th width="10%" class="text-center">審證機關意見</th>
              </tr>
              <tr v-for="(item,key) in table1.data">
                <td style="vertical-align : middle" class="text-center">{{key+1}}</td>
                <td style="vertical-align : middle">{{item.projectStatusName}}</td>
                <td style="vertical-align : middle">{{item.projectName}}</td>
                <td style="vertical-align : middle">{{item.projectCategoryName}}</td>
                <td style="vertical-align : middle">{{item.reviewOrgName}}</td>
                <td style="vertical-align : middle" class="text-center">{{item.expectDate}}</td>
                <td style="vertical-align : middle" class="text-center">{{item.realDate}}</td>
                <td style="vertical-align : middle" class="text-right">{{item.expiredDays}}</td>
                <td style="vertical-align : middle">{{item.reviewRemark}}</td>
              </tr>
            </table>
          </b-collapse>
        </div>

      </div>
    </section>
  </div>
</template>
<script lang="ts">
import { useValidation, validateState } from '@/shared/form';
import { computed, onActivated, reactive, ref, watch, onMounted } from '@vue/composition-api';
import { useBvModal } from '@/shared/modal';
import { Pagination } from '@/shared/model/pagination.model';
import { navigateByNameAndParams } from '@/router/router';
import NotificationService from '@/shared/notification/notification-service';
import axios from 'axios';
import { required } from '@/shared/validators';
import { notificationErrorHandler } from '@/shared/http/http-response-helper';
import { useNotification } from '@/shared/notification';
import { formatDate } from '@/shared/date/minguo-calendar-utils';
import iButton from '@/shared/buttons/i-button.vue';
import { formatDateInChinese } from '@/shared/date/minguo-calendar-utils';

export default {
  components: { iButton },
  name: 'lic0301Query',
  props: {
    isReload: {
      required: false,
    },
  },
  setup() {
    onActivated(() => {
      // if (isReloadProp.value) {
      //   handleQuery();
      // }
    });

    onMounted(() => {
      handleQuery();
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      memberName: '',
    };

    const rules = {
      memberName: {},
    };

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const table1 = reactive({data: undefined,});
            table1.data = [
            {
              projectStatusName: '已取得',
              projectName: '樹木遷移計畫',
              projectCategoryName: '其他法令規定應辦理項目(由主辦機關自行視需要增列)',
              reviewOrgName: '臺北市政府工務局公園路權工程管理處',
              expectDate: '2020-08-31',
              realDate: '2020-08-17',
              expiredDays: 14,
              reviewRemark: '',
            },
            {
              projectStatusName: '已取得',
              projectName: '樹木遷移計畫',
              projectCategoryName: '其他法令規定應辦理項目(由主辦機關自行視需要增列)',
              reviewOrgName: '臺北市政府工務局公園路權工程管理處',
              expectDate: '2020-08-31',
              realDate: '2020-08-16',
              expiredDays: 15,
              reviewRemark: '',
            },
            {
              projectStatusName: '已取得',
              projectName: '樹木遷移計畫',
              projectCategoryName: '其他法令規定應辦理項目(由主辦機關自行視需要增列)',
              reviewOrgName: '臺北市政府工務局公園路權工程管理處',
              expectDate: '2020-08-31',
              realDate: '2020-08-15',
              expiredDays: 16,
              reviewRemark: '',
            },
            {
              projectStatusName: '已取得',
              projectName: '樹木遷移計畫',
              projectCategoryName: '其他法令規定應辦理項目(由主辦機關自行視需要增列)',
              reviewOrgName: '臺北市政府工務局公園路權工程管理處',
              expectDate: '2020-08-31',
              realDate: '2020-08-14',
              expiredDays: 17,
              reviewRemark: '',
            },
          ];

    const table = reactive({
      fields: [
        {
          key: 'season',
          label: '日期',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
          thStyle: 'width: 8%',
        },
        {
          key: 'milestone',
          label: '里程碑',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 75%',
        },
        {
          key: 'mustGet',
          label: '應取得',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width: 6%',
        },
        {
          key: 'alreadyGet',
          label: '已取得',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width: 6%',
        },
      ],
      data: undefined,
      totalItems: 3,
    });

    // 查詢結果及狀態
    const queryStatus = ref(false);

    //顯示查詢條件區
    let stepVisible = ref(true);
    // modal物件
    const $bvModal = useBvModal();

    //轉到新增頁面
    const toCreateView = () => {
      navigateByNameAndParams('lic0701-edit-info', { formStatusForward: 'create' ,isNotKeepAlive: true  });
    };

    //轉到編輯頁面
    const toEditView = (licWorkshopMember: any) => {
      navigateByNameAndParams('lic0701-edit-info', { formStatusForward: 'modify', licWorkshopMember: licWorkshopMember, isNotKeepAlive: true });
    };

    //依條件查詢工作小組成員
    const handleQuery = () => {
      checkValidity().then((isValid: boolean) => {
      //   if (isValid) {
      //     //重置條件
      //     table.data = undefined;
      //     axios
      //       .post('/rec-projects/criteria-jpa', form)
      //       .then(({ data }) => {
      //         table.data = data.content.slice(0, data.content.length);
      //         table.totalItems = data.totalElements;
      //       })
      //       .finally()
      //       .catch(notificationErrorHandler(notificationService));
      //   }
      // });
      queryStatus.value=true;
      table.data = [
        {
          season : '109.6',
          milestone : '●樹木遷移計畫 109-5-30　●房屋遷移計畫 109-4-30　',
          mustGet : 2,
          alreadyGet : 2,
        },
        {
          season : '109.12',
          milestone : '●樹木遷移計畫 109-11-30　●房屋遷移計畫 109-10-30　',
          mustGet : 2,
          alreadyGet : 2,
        },
        {
          season : '110.3',
          milestone : '●樹木遷移計畫 110-2-20　●房屋遷移計畫 110-1-10　',
          mustGet : 2,
          alreadyGet : 2,
        },
        {
          season : '110.6',
          milestone : '●樹木遷移計畫 110-5-30　●房屋遷移計畫 110-4-30　',
          mustGet : 2,
          alreadyGet : 2,
        },
      ]
    })};

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    };

    return {
      $v,
      stepVisible,
      queryStatus,
      validateState,
      checkValidity,
      handleQuery,
      reset,
      handlePaginationChanged,
      table,
      toCreateView,
      toEditView,
      table1,
      formatDateInChinese,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

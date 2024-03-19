<template>
  <div>
    <section class="container mt-2">
      <div class="card">
        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                計畫概況
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
            <table>
              <!-- <tr>
                <td>
                  <i-apexcharts :type="'bar'" horizontal :items="items" :data="data"></i-apexcharts>
                </td>
              </tr> -->
              <tr>
                <td>
                  證照如期取得(%) = 於預定時程內取得證照數 / 當期應取得證照數<br>
                  <b-img fluid rounded src="https://lh3.googleusercontent.com/QldE8Ra3UamItSKn1tfB6_QTiYKhBdaPy-hL0Lmnl09yE09PkYhIyuSY3h3bYzlynkkkKmxF9jlh-NyOzow3w_DcsJxr0xLqj3xKMh3OeS0mruEC3TbaccUH1DT5nGfVdTji2tNLToaSA9G-5mSF7bUeHNugOT81swa9NxUlO2-hZ3FMk9u1GNMcDpFHgMRdyX0pWr4Co3bJjz3vDL_yJfzlLqlhLBXJOY8pjO7EDEquzST4eKHTB1FHO2vUqNo9Ke8Jc1574T2qGG4_JQIBac_1UI83k4K6IgnAzOMg2zVrXP5j9g-fiBb2Mc0nHrw4jrdsN1kqELqQu16UST2fmaiXIPDQpN4ifNr1oURtegJ20q5UD2JhLjYdLYNUyba41yKWhkX8dRpPFemh_47d5F2qL45xUwrQ-uwSB39ldRcj_EC89ysB3ZXl5IQYm8Gl-T2m97DPZNFUhoU9vqJ0u_X1S_Il2zjFClvdmmuz5Xl1m5KBh76hgPfgvBvcBA5k0AaG8vuRR3QVQ9-8h3cnGR09zjbykUyljj2XXxwCkxpZKCd-EHjbChwOu_4cjJ4BG2QeSiO-vZzt5tE4zRnqgvEie-xs2LWOr7BXMLs8EPbW3O8JPKl72O7rT756XlT9IU6go-WgxqHnuBIHdVxfNMfx89B53aEtVm8Zeb4PoczZGtFR4xwv37hK3sxiRqSW-w5TlLjHdmjEGhgEUDMScJU7d9s0GXJio9K5h2knkyO8_w4IE-TjY_mTON6i=w464-h76-no?authuser=0" style="height: 80px" alt="Image"></b-img>
                </td>
              </tr>
              <tr>
                <td>
                  證照已取得(%) = 所有已取得證照數 / 所有應辦理證照數<br>
                  <b-img fluid rounded src="https://lh3.googleusercontent.com/swx-ayI9t3nzAGg1_tnIGASlSniOAYn0a-JIP7rKW3j1fwawHXYGyN_-AthYYCWeNzMtUtkFnWVHvI8bd-Ms-uStepT1_aBhCm-ENVNJyg1DwaKsVNT2tB_PWrp7OvspS4BbGgLvzcWmHeVQg1yxmFnQYpEjrO6cyRHpzZMtb0VipHnauaF1kaMqdVWuuKv_kKTgcqf85lchthBAJEjLfpngcLZYMUlk9AM4qTnHp_nW4lSSVwvMGNqhjA6bM7fnaFfSI0y0Y6pPWu4H4Q4kmkyQ8wjR9bCXiifoNvWCg6pmWIpjOCF85a00ubFfkkdhSP5jr_9R2HxauAiMBmzQ9V71E7aEs5sCTSklfmwvrF0q6DsOawJSJv4IrFvZPrfsMwH3puYCr1niJaOrBdC9j1iYvridHwSbqDZj8bDHKwCsBZKMzpcVCX_uStb8h-9XNLd9KLUbv6kTUDblgNNQWIamfP3r7tANIzJ_jqhmqElXrAL_6FrxysJHrjY5EJ9IKjST1vi3-mbeXwfVUdsjtaWc6dbYcBiuZuhXof_f3iLofV4OdzHdYcZ8RxUMuD057YbGjZmoWiQQyuzqKou78A0VgilEYQIBTNTQvFxz3UzDu7ROLz9aXw0zs8ajOILztdK1nQ_TFpkdjB579jFqd9dJc7pPJKUOJxApiznErJ9rWsA34roWIxNSFnW7jpTFHSUxowvDqN2i8_fxGywqTm4ddtFF-FUH-sL13OENbQynjGVDDYHRtGtdevBF=w466-h77-no?authuser=0" style="height: 80px" alt="Image"></b-img>
                </td>
              </tr>
            </table>
            <table border="2">
              <tr>
                <th class="text-center">應辦總證照數(A)</th>
                <th  class="text-center">已取得證照數(B=C+D)</th>
                <th  class="text-center">如期取得證照數(C)</th>
                <th  class="text-center">逾期但已取得證照數(D)</th>
                <th  class="text-center">截至目前應取得證照數(E)</th>
                <th class="text-center">證照如期取得(%)(F=C/E)</th>
                <th  class="text-center">證照已取得(%)(G=B/A)</th>
                <th width="4%" class="text-center">動作</th>
              </tr>
              <tr>
                <td class="text-center">100</td>
                <td class="text-center">60</td>
                <td class="text-center">35</td>
                <td class="text-center">25</td>
                <td class="text-center">80</td>
                <td class="text-center">43.75%</td>
                <td class="text-center">60%</td>
                <td><b-button class="p-1" variant="info" @click="toAllPlan">查看</b-button></td>
              </tr>
            </table>
          </b-collapse>
        </div>

        <div class="card-header py-1 text-left">
          <div class="row align-items-center pl-3">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <font-awesome-icon icon="project-diagram"></font-awesome-icon>
                所轄計畫列表
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
                <th width="4%" class="text-center">序號</th>
                <th width="74%" class="text-center">計畫名稱</th>
                <th width="9%" class="text-center">計畫類別</th>
                <th width="8%" class="text-center">應辦證照數</th>
                <th width="5%" class="text-center">動作</th>
              </tr>
              <tr v-for="(item,key) in table1.data">
                <td style="vertical-align : middle" class="text-center">{{key+1}}</td>
                <td style="vertical-align : middle">{{item.projectCategoryName}}</td>
                <td style="vertical-align : middle" class="text-center">{{item.projectCategory}}</td>
                <td style="vertical-align : middle" class="text-center">{{item.countA}}</td>
                <td style="vertical-align : middle" class="text-center">
                  <!-- 間距處理 https://www.gushiciku.cn/pl/gGWZ/zh-tw -->
                  <b-button class="p-1" variant="info" @click="toSinglePlan">查看</b-button>
                </td>
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
    });

    const isReloadProp = computed(() => Boolean(props.isReload));

    const formDefault = {
      memberName: '',
    };

    const rules = {
      memberName: {},
    };

    const items = ['證照如期取得(%)', '證照已取得(%)'];

    const data = [43.75 , 60];

    const form = reactive(Object.assign({}, formDefault));

    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);

    const notificationService: NotificationService = useNotification();

    const table1 = reactive({data: undefined,});
            table1.data = [
            {
              projectCategoryName: '國道4號台中環線豐原潭子段計畫',
              projectCategory: '公路',
              countA: 8,
            },
            {
              projectCategoryName: '臺北都會區大眾捷運系統三鶯線建設計畫',
              projectCategory: '軌道運輸',
              countA: 21,
            },
            {
              projectCategoryName: '桃園都會區大眾捷運系統航空城捷運線(綠線)暨土地整合發展計畫',
              projectCategory: '軌道運輸',
              countA: 17,
            },
            {
              projectCategoryName: '國道7號高雄路段計畫',
              projectCategory: '公路',
              countA: '7',
            },
          ];

    const table = reactive({
      fields: [
        {
          key: 'licProjectBasicId',
          label: '計畫名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 35%',
        },
        {
          key: 'memberName',
          label: '姓名',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 6%',
        },
        {
          key: 'memberTitle',
          label: '職稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%',
        },
        {
          key: 'orgId',
          label: '機關',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 25%',
        },
        {
          key: 'orgAttr',
          label: '機關屬性',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
          thStyle: 'width: 10%' ,
        },
        {
          key: 'action',
          label: '操作(編輯涉及證照項目)',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-right align-middle',
          thStyle: 'width:10%',
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

    const toAllPlan = () => {
      navigateByNameAndParams('lic0302Query', { isNotKeepAlive: true  });
    };

    const toSinglePlan = () => {
      navigateByNameAndParams('lic0201Query', { isNotKeepAlive: true  });
    };

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
          licProjectBasicId : '(L10801)天然氣事業部永安廠增建儲糟投資計畫',
          memberName : '陳宇軒',
          memberTitle : '工作小組成員',
          orgId : '台灣中油股份有限公司興建工程處',
          orgAttr : '主辦機關',
        },
        {
          licProjectBasicId : '(L10802)台9線花東縱谷公路安全景觀大道計畫(臺東段)',
          memberName : '陳克劼',
          memberTitle : '段長',
          orgId : '交通部公路總局第三區養護工程處',
          orgAttr : '審證機關',
        },
        {
          licProjectBasicId : '(L10803)臺北都會區大眾捷運系統三鶯線建設計畫',
          memberName : '包晃豪',
          memberTitle : '主任秘書',
          orgId : '新北市政府工務局',
          orgAttr : '審證機關',
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
      items,
      data,
      table1,
      toAllPlan,
      toSinglePlan,
    };
  },
};
</script>
<style scoped>
.button-float-right {
  float: right;
}
</style>

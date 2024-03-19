<template>
  <div>
    <section class="container">
      <div class="card">
        <div class="card-header">
          <h5 class="m-0">
            <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            公司登記查詢
          </h5>
        </div>
      </div>
    </section>
    <section class="container mt-2" v-show="queryStatus">
    <i-table
      small
      ref="iTableComponent"
      :itemsUndefinedBehavior="'loading'"
      :items="table.data"
      :is-server-side-paging="true"
      :fields="table.fields_backend"
      :totalItems="table.totalItems"
      @changePagination="handlePaginationChanged"
      >
      <!-- <template v-slot:cell(inDate)="slot">
        任職日期：{{slot.item.inDateStart}}<br/>
        離職日期：{{ slot.item.inDateEnd }}<br/>
      </template> -->
    </i-table>
  </section>
  <b-form-row>
    <b-col offset="5">
      <!-- 返回 -->
      <br/>
      <i-button type="arrow-left" @click="toLast" />
      <!-- 列印 -->
      <b-button variant="info">列印</b-button>
    </b-col>
  </b-form-row>  
  </div>
</template>

<script lang="ts">
import { Eng0703, IEng0703 } from "@/shared/model/eng/eng0703.model"
import i18n from '@/shared/i18n';
import { FormStatusEnum } from '@/shared/enum';
import { RoleEnum } from "@/shared/enum";
import { toRefs, ref, unref, defineComponent, inject, onMounted, reactive} from '@vue/composition-api';
import { Pagination } from '@/shared/model/pagination.model';
import { useValidation, validateState } from '@/shared/form';
import { handleBack } from '@/router/router';

interface propsType {
	eng0703: IEng0703;
	formStatus: FormStatusEnum;
	role: RoleEnum;
	criteria: any;
  id: string
}
export default defineComponent<propsType>({
  name: 'Eng0201EditInfo5',
	props: {
    id: {
      required: false,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
	},          
	setup(props) {
    const formStatusRef = ref(props.formStatus);
    const isReload = ref(false); 
    const queryStatus = ref(false);
    const formDefault = {

    }
    const rules = {

    }
    const form = reactive(Object.assign({}, formDefault));

    const table = reactive({
      fields_backend: [
        {
          // 公司/商業
          key: 'company',
          label: i18n.t('label.company'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 統編
          key: 'compIdno',
          label: i18n.t('label.compIdno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 姓名
          key: 'name',
          label: i18n.t('label.name'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 身分證字號
          key: 'idno',
          label: i18n.t('label.idno'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 職稱
          key: 'engCompMemberType',
          label: i18n.t('label.engCompMemberType'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 停業日期(起)
          key: 'outBusinStart',
          label: i18n.t('label.outBusinStart'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 停業日期(迄)
          key: 'outBusinEnd',
          label: i18n.t('label.outBusinEnd'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 核准設立日期
          key: 'approvedDate',
          label: i18n.t('label.approvedDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 最後一次核准日期
          key: 'approvedDateEnd',
          label: i18n.t('label.approvedDateEnd'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 登記現況說明
          key: 'registerDire',
          label: i18n.t('label.registerDire'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
      ],
      data: [],
      totalItems: undefined,
    })

    onMounted(() => {
      handleQuery();
    })

    const handlePaginationChanged = (pagination: Pagination) => {
      Object.assign(form, pagination);
      handleQuery();
    }

    const handleQuery = () => {
      table.data = [];
      queryStatus.value = true;
      table.data = data;
      table.totalItems = data.length;
    }

    function toLast() {
      handleBack({ isReload: isReload.value, isNotKeepAlive: true,
        formStatus: formStatusRef.value});
      isReload.value = false;
    }

    const data = [
      {
        company: '一宅通工程顧問公司',
        compIdno: '200007050705',
        name: '蔡貫洪',
        idno: '0912123123',
        engCompMemberType: '董事長',
        outBusinStart: '',
        outBusinEnd: '',
        approvedDate: '2020-12-12',
        approvedDateEnd: '2020-12-12',
        registerDire: '核准設立',
      },
    ]

    return {
      handlePaginationChanged,
      table,
      queryStatus,
      toLast,
      formStatusRef
    }
  }
})
</script>
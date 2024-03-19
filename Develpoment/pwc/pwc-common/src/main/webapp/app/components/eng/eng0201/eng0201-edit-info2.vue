<template>
  <div>
  <section class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            董事長或執行業務股東名冊
        </h5>
      </div>
      <div class="card-header py-1 text-left">
        <div class="row align-items-center pl-3">
          <div class="col-sm-11 p-0">
            <h5 class="m-0">
              <font-awesome-icon icon="search"></font-awesome-icon>
              查詢條件
            </h5>
          </div>
        </div>
      </div>
      <div class="card-body col-11">
        <b-form-row>
          <!-- 姓名 -->
          <i-form-group-check :label="$t('label.name') + '：'"
          >
            <b-form-input trim>
            </b-form-input>
          </i-form-group-check>
          <!-- 職稱 -->
          <i-form-group-check :label="$t('label.engCompMemberType') + '：'"
          >
            <b-form-input trim>
            </b-form-input>
          </i-form-group-check>
          <!-- 是否在職 -->
          <i-form-group-check labelStar
          :label="$t('label.isJob') + '：'" 
          >
            <b-form-input 
            trim></b-form-input>
          </i-form-group-check>
        </b-form-row>

        <b-form-row>
          <b-col offset="5">
            <!-- 查詢 -->
            <i-button type="search" @click="handleQueryBtn"></i-button>
            <!-- 清除 -->
                <i-button type="x-circle" @click="clear"></i-button>
          </b-col>
        </b-form-row>
      </div>
    </div>
  </section>
  <b-form-row>
    <b-col offset="5">
      <!-- 返回 -->
      <br/>
      <i-button type="arrow-left" @click="toLast" />
      <!-- 新增 -->
      <i-button type="file-earmark-plus" 
      @click="toEditView('Eng0201EditInfo2_1', FormStatusEnum.CREATE)"> 
      </i-button>
    </b-col>
  </b-form-row>
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
      <template v-slot:cell(inDate)="slot">
        任職日期：{{slot.item.inDateStart}}<br/>
        離職日期：{{ slot.item.inDateEnd }}<br/>
      </template>
      <template #cell(action)="row">
        <!-- 編輯 -->
        <i-button @click="toEditView('Eng0201EditInfo2_1', FormStatusEnum.EDIT, row.index)"
            v-if="
              (row.item.status !== '3' || roleRef === RoleEnum.ADMIN) &&
              (row.item.status !== '4' || roleRef === RoleEnum.ADMIN) &&
              (row.item.status !== '5' || roleRef === RoleEnum.ADMIN) &&
              row.item.status !== '6'
            "
            size="sm"
            type="pencil-square"
            
          ></i-button>
      </template>
    </i-table>
  </section>     
    
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
import { navigateByNameAndParams } from '@/router/router';

interface propsType {
	eng0703: IEng0703;
	formStatus: FormStatusEnum;
	role: RoleEnum;
	criteria: any;
  id: string
}
export default defineComponent<propsType>({
  name: 'Eng0201EditInfo2',
	props: {
    id: {
      required: false,
    },
    formStatus: {
      required: false,
      default: () => FormStatusEnum.READONLY,
    },
    // nextFormStatus: {
    //   required: false,
    //   default: () => FormStatusEnum.READONLY,
    // },
	},          
	setup(props) {
    const lastFormStatusRef = ref(props.formStatus); // 上一頁的檢視型態
    const isReload = ref(false); 
    const queryStatus = ref(false);
    const formDefault = {

    }
    const rules = {

    }
    const form = reactive(Object.assign({}, formDefault));
    const { $v, checkValidity, reset } = useValidation(rules, form, formDefault);
    const table = reactive({
      fields_backend: [
        {
          // 身分證字號
          key: 'idno',
          label: i18n.t('label.idno'),
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
          // 職稱
          key: 'engCompMemberType',
          label: i18n.t('label.engCompMemberType'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 電話
          key: 'tel',
          label: i18n.t('label.tel'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 是否為執業技師
          key: 'isEngr',
          label: i18n.t('label.isEngr'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 任職
          key: 'inDate',
          label: i18n.t('label.inDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 動作
          key: 'action',
          label: i18n.t('label.action'),
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

    const handleQueryBtn = () => {
      handleQuery();
    }

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

    const clear = () => {
      reset();
      
      queryStatus.value = false;
      table.data = [];
    }

    function toLast() {
      navigateByNameAndParams('Eng0201EditInfo', {
        isReload: isReload.value, isNotKeepAlive: true,
        formStatus: lastFormStatusRef.value
      });
      isReload.value = false;
    }

    function toEditView(name: string, formStatus: FormStatusEnum) {
      // 跳頁會重洗元件
      navigateByNameAndParams(name, {
        isReload: isReload.value, isNotKeepAlive: true,
        formStatus: formStatus, lastFormStatus: lastFormStatusRef.value
      });
      isReload.value = false;
    }

    const data = [
      {
        idno: 'A123456789',
        name: '蔡貫洪',
        engCompMemberType: '董事長',
        tel: '0912123123',
        isEngr: '是',
        inDateStart: '109/06/25',
        inDateEnd: ''
      },
    ]

    return {
      table,
      handlePaginationChanged,
      handleQueryBtn,
      clear,
      queryStatus,
      toLast,
      lastFormStatusRef,
      FormStatusEnum,
      toEditView
    }
  }
})
</script>
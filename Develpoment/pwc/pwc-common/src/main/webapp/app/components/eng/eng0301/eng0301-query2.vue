<template>
 <div>
  <section class="container">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
            簽證紀錄
        </h5>
      </div>
      <div class="card-body">
        <b-form-row>
          <!-- 技師 -->
          <i-form-group-check
          :label="$t('label.engr') + '：'"
          >
            <div>
              {{ viewData.engr }}
            </div>
          </i-form-group-check>
          <!-- 科別 -->
          <i-form-group-check
          :label="$t('label.subject') + '：'"
          >
            <div>
              {{ viewData.subjectList }}
            </div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <!-- 簽證範圍 -->
          <i-form-group-check
          :label="$t('label.recordRangeList') + '：'"
          >
            <div>
              {{ viewData.recordRangeList }}
            </div>
          </i-form-group-check>
          <!-- 簽證項目 -->
          <i-form-group-check
          :label="$t('label.recordItems') + '：'"
          >
            <div>
              {{ viewData.recordItems }}
            </div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <!-- 辦理時間 -->
          <i-form-group-check
          :label="$t('label.execDate') + '：'"
          >
            <div>
              {{ viewData.execDate }}
            </div>
          </i-form-group-check>
          <!-- 簽證日期 -->
          <i-form-group-check
          :label="$t('label.signStartDate') + '：'"
          >
            <div>
              {{ viewData.signStartDate }}
            </div>
          </i-form-group-check>
        </b-form-row>
        <b-form-row>
          <!-- 結案日期 -->
          <i-form-group-check
          :label="$t('label.closeDate') + '：'"
          >
            <div>
              {{ viewData.closeDate }}
            </div>
          </i-form-group-check>
          <!-- 修改日期 -->
          <i-form-group-check
          :label="$t('label.updateTime') + '：'"
          >
            <div>
              {{ viewData.updateTime }}
            </div>
          </i-form-group-check>
        </b-form-row>
      </div>
    </div>
  </section>

  <section class="container mt-2">
    <div class="card">
      <div class="card-header">
        <h5 class="m-0">
          <font-awesome-icon icon="project-diagram"></font-awesome-icon>
          資料列表
        </h5>
      </div>
      <div class="card-body">
        <b-table
        small
        sticky-header  
        :bordered="true"
        ref="iTableComponent"
        :itemsUndefinedBehavior="'loading'"
        :is-server-side-paging="true"
        :fields="table.fields_backend"
        :totalItems="table.totalItems"
        :items="table.data"
      >
      </b-table>
      </div>
    </div>
        
  </section>

  <b-form-row>
    <b-col offset="5">
      <!-- 返回 -->
      <br/>
      <i-button type="arrow-left" @click="toQueryView" />
    </b-col>
  </b-form-row>
  <br/>
  <b-form-row>
    <b-col offset="5">  
      <!-- 上一筆 -->
      <i-button type="arrow-bar-left" />
      <!-- 下一筆 -->
      <i-button type="arrow-bar-right"  />
    </b-col>
  </b-form-row>
 </div>
</template>

<script lang="ts">
import { toRefs, ref, unref, defineComponent, inject, onMounted, reactive} from '@vue/composition-api';
import { Eng0703, IEng0703 } from "@/shared/model/eng/eng0703.model"
import { FormStatusEnum } from '@/shared/enum';
import { RoleEnum } from "@/shared/enum";
import { string } from 'prop-types';
import { handleBack } from '@/router/router';
import { useValidation, validateState } from '@/shared/form';
import { formOptionsFormatter } from '@/shared/formatter/common';
import axios, { AxiosResponse } from 'axios';
import { navigateByNameAndParams } from '@/router/router';
import { useBvModal } from '@/shared/modal';
import { Pagination } from '@/shared/model/pagination.model';
import i18n from '@/shared/i18n';

export default  {
  name: 'Eng0301Query2',
  props: {
    isReload: {
      type: Boolean,
      required: false,
    },
  },
  setup(props) {



    const formDefault = {
      caseType: '',
      recordItemsList: '',
      recordItems: [],
      projectType: '',
      caseNo: '',
      caseName: '',
      execOrgName: '',
      execOrgId: '',
      execDateStart: null,
      execDateEnd: null,
      tenderDateStart: null,
      tenderDateEnd: null,
      compName: '',
      compIdno: '',
      engrName: '',
      engrId: ''
    }

    onMounted(() => {
      handleQuery();
    })

    const form = reactive(Object.assign({}, formDefault));

    const handlePaginationChanged = (pagination: Pagination) => {
        Object.assign(form, pagination);
        handleQuery();
      };

    const handleQuery = () => {
      table.data = data;
      table.totalItems = data.length;
    }

    function toQueryView() {
      navigateByNameAndParams('Eng0301Query', {});
    }

    const table = reactive({
      fields_backend: [
        {
          // 應登錄日期
          key: 'expectDate',
          label: i18n.t('label.expectDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 實際登陸日期
          key: 'realTime',
          label: i18n.t('label.realTime'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 簽證日期
          key: 'signStartDate',
          label: i18n.t('label.signStartDate'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          // 簽證內容
          key: 'recordContent',
          label: i18n.t('label.recordContent'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        {
          // 簽證意見
          key: 'recordComment',
          label: i18n.t('label.recordComment'),
          sortable: true,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
        
      ],
      data: undefined,
      totalItems: undefined,
    });

    const data = [
      {
        expectDate: '109-01-01',
        realTime: '109-09-15',
        signStartDate: '108-12-31',
        recordContent: '監造相關作業',
        recordComment: '依相關規定辦理'
      },
      {
        expectDate: '108-07-01',
        realTime: '109-09-15',
        signStartDate: '108-06-30',
        recordContent: '監造相關作業',
        recordComment: '依相關規定辦理'
      }
    ]
    

    const viewData = {
      engr: '王茲為',
      subjectList: '土木工程',
      recordRangeList: '道路工程、橋樑工程及隧道工程',
      recordItems: '監造',
      execDate: '106/10/26 ~ 108/11/14',
      signStartDate: '106/10/26',
      closeDate: '108/11/14',
      updateTime: '106/10/26'
    }

    return {
      viewData,
      handlePaginationChanged,
      table,
      data,
      toQueryView
    }
  }
}
</script>
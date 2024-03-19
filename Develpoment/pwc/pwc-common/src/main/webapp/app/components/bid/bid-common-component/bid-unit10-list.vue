<template>
  <div>
    <b-modal
      v-model="dialogIsVisible"
      id="bid-unit-model"
      size="xl"
      :title="title"
      header-bg-variant="secondary"
      header-text-variant="light"
      hide-footer
      @close="dialogIsVisible = false"
    >
      <b-form-row>
        <b-form-group class="col-sm-6" label-cols-md="3" content-cols-md="5" label="代碼：" label-align-md="right">
          <b-form-input v-model="queryKey.code" trim></b-form-input>
        </b-form-group>
        <b-form-group class="col-sm-6" label-cols-md="3" content-cols-md="5" label="名稱：" label-align-md="right">
          <b-form-input v-model="queryKey.name" trim></b-form-input>
        </b-form-group>
      </b-form-row>
      <i-table
        class="table-sm"
        itemsUndefinedBehavior="loading"
        per-page="15"
        :items="table.data"
        :fields="table.fields"
        :totalItems="table.totalItems"
        :isServerSidePaging="true"
        hide-card-header
        @changePagination="handlePaginationChanged($event)"
      >
      </i-table>
    </b-modal>
  </div>
</template>

<script lang="ts">
import BidUnit10Servie from '@/components/bid/bidService/bid-unit10.service';
import { watch, reactive, ref, toRef } from '@vue/composition-api';
import { Pagination } from '@/shared/model/pagination.model';

export default {
  name: 'bidUnit10List',
  props: {},
  setup(props, context) {
    const dialogIsVisible = false;
    const title = ''

    const bidUnit10Service = new BidUnit10Servie();

    const table = reactive({
      fields: [
        {
          key: 'code',
          label: '代碼',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-center align-middle',
        },
        {
          key: 'name',
          label: '名稱',
          sortable: false,
          thClass: 'text-center',
          tdClass: 'text-left align-middle',
        },
      ],
      data: undefined,
      totalItems: 0,
    });

    let queryKey = reactive({
      code: '',
      name: '',
      type: '',
      currentPage: 0,
    });

    function fetchBidUnits() {
      bidUnit10Service.findAllByCriteria(queryKey).then(res => {
        table.data = res.content.slice(0, res.content.length); //後端分頁
        table.totalItems = res.totalElements; //後端分頁
      });
    }

    function isShowDia(parentToChildData, isVisible) {
      this.dialogIsVisible = isVisible;
      Object.assign(queryKey, parentToChildData);
    }

    function handlePaginationChanged(pagination: Pagination) {
      Object.assign(queryKey, pagination);
      fetchBidUnits();
    }

    watch(
      queryKey,
      newValue => {
        if (newValue !== null && newValue !== undefined) {
          newValue.currentPage = 0;
          fetchBidUnits();
        }
      },
      { immediate: true});
    );

    return {
      table,
      handlePaginationChanged,
      queryKey,
      dialogIsVisible,
      isShowDia,
      title,
    };
  },
  filters: {
  },
};
</script>

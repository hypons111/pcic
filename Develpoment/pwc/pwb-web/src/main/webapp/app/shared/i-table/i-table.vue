<template>
  <div class="card" v-if="$attrs.items !== undefined || state.itemsUndefinedBehaviorComputed">
    <div class="card-header">
      <h5 class="my-0">
        <font-awesome-icon icon="list"></font-awesome-icon>
        {{ title }}
        <table-fields-picker v-if="$attrs.items && $attrs.items.length > 0" v-bind="$attrs" @picked="handlePicked" class="float-right" />
      </h5>
    </div>
    <div class="card-body m-0 p-0">
      <!-- I-Table 本體 -->
      <div class="d-flex justify-content-center m-0 p-0">
        <!-- main table grid -->
        <b-table
          :empty-text="state.emptyText"
          :empty-filtered-text="state.emptyFilterText"
          :fields="state.pickedFields"
          :per-page="state.pagination.perPage"
          :current-page="isServerSidePagingRef ? 1 : state.pagination.currentPage"
          :no-sort-reset="true"
          :no-local-sorting="isServerSidePaging"
          show-empty
          bordered
          responsive="responsive"
          class="table table-hover m-0"
          v-bind="$attrs"
          v-on="$listeners"
          @filtered="state.filtered"
          @sort-changed="handleSortChanged($event)"
        >
          <template v-for="(_, slot) of $scopedSlots" v-slot:[slot]="scope">
            <slot :name="slot" v-bind="scope" />
          </template>
          <template #empty>
            <div class="text-center m-3" v-if="state.itemsUndefinedBehaviorComputed">
              <b-spinner></b-spinner>
              <p>查詢中，請稍候...</p>
            </div>
          </template>
        </b-table>
      </div>
    </div>
    <div class="card-footer">
      <!-- 分頁 -->
      <div class="d-flex justify-content-center" v-if="$attrs.items && $attrs.items.length > 0 && state.pagination.rowsStatus">
        <b-form inline class="mr-3">
          <b-input-group prepend="每頁" append="筆">
            <b-form-select
              v-model="state.pagination.perPage"
              :options="state.pagination.perPageOptions"
              @input="handlePageChanged"
            ></b-form-select>
          </b-input-group>
        </b-form>
        <div class="align-middle">
          <b-pagination
            class="my-0"
            align="fill"
            v-model="state.pagination.currentPage"
            :total-rows="state.pagination.rows"
            :per-page="state.pagination.perPage"
            @input="handlePageChanged"
          />
        </div>
        <div class="align-self-center mr-3 ml-3">
          顯示 {{ state.pagination.start }} 到 {{ state.pagination.end }} 共 {{ state.pagination.rows ? state.pagination.rows : 1 }} 筆 頁次
          {{ state.pagination.currentPage }}/{{ Math.floor(state.pagination.rows / state.pagination.perPage) + 1 }}
        </div>
        <b-form inline>
          <b-input-group prepend="前往頁數">
            <b-form-input class="goto-input" v-model="state.pagination.gotoPage" @keydown.enter.prevent="handleGotoPage" />
            <b-input-group-append>
              <b-button variant="outline-success" @click="handleGotoPage">GO</b-button>
            </b-input-group-append>
          </b-input-group>
        </b-form>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { computed, reactive, onMounted, ref } from '@vue/composition-api';
import TableFieldsPicker from '@/shared/table-fields-picker/table-fields-picker.vue';
import { Pagination } from '../model/pagination.model';
export default {
  name: 'i-table',
  inheritAttrs: false,
  components: {
    TableFieldsPicker,
  },
  props: {
    /**
     * ITable 的表頭標題
     */
    title: {
      describe: '表頭標題',
      type: String,
      default: '查詢結果',
      required: false,
    },
    itemsUndefinedBehavior: {
      describe: '當 items 為 undefined 時候，表格呈現的行為。hide:預設值，表格不呈現。loading：則表格會呈現查詢中的圖示。',
      type: String,
      default: 'hide',
      required: false,
    },
    totalItems: {
      type: Number,
      required: true,
    },
    isServerSidePaging: {
      type: Boolean,
      default: true,
      required: false,
    },
  },
  setup(props, context) {
    const isServerSidePagingRef = ref(props.isServerSidePaging);

    const state = reactive({
      itemsUndefinedBehaviorComputed: computed(() => {
        return context.attrs.items === undefined && props.itemsUndefinedBehavior === 'loading';
      }),
      emptyText: '查無資料，請修改條件後再重新查詢一次。',
      emptyFilterText: '篩選後無對應資料，請選擇其他篩選條件。',
      pickedFields: [],
      pagination: {
        perPageOptions: [20, 30, 40, 50, 100],
        perPage: 20,
        currentPage: 1,
        start: computed(() => (state.pagination.currentPage - 1) * state.pagination.perPage + 1),
        end: computed(() => {
          const end = state.pagination.currentPage * state.pagination.perPage;
          return end > state.pagination.rows ? (state.pagination.rows === 0 ? 1 : state.pagination.rows) : end;
        }),
        totalPages: computed(() => Math.ceil(state.pagination.rows / state.pagination.perPage)),
        // rows: computed(() => (attrs.filter != null ? state.pagination.rowsFilter : attrs.items.length)),
        rows: computed(() => (context.attrs.filter != null ? state.pagination.rowsFilter : props.totalItems)),
        rowsFilter: null,
        rowsStatus: true,
        gotoPage: undefined,
      },
      filtered: filteredItems => {
        state.pagination.rowsFilter = filteredItems.length;
        state.pagination.rowsStatus = state.pagination.rowsFilter !== 0 ? true : false;
      },
    });

    const pagination = new Pagination(1, 10, [], 'ASC');

    const handlePicked = pickedFields => (state.pickedFields = pickedFields);

    const handleGotoPage = () => {
      const gotoPage = state.pagination.gotoPage;
      if (!isNaN(gotoPage) && gotoPage > 0 && gotoPage <= state.pagination.totalPages) {
        state.pagination.currentPage = gotoPage;
      }
      state.pagination.gotoPage = undefined;
      emitPagination();
    };

    const handleSortChanged = ctx => {
      if (ctx.sortBy) {
        pagination.sortBy = [ctx.sortBy];
        pagination.sortDirection = ctx.sortDesc ? 'DESC' : 'ASC';
        emitPagination();
      }
    };

    const handlePageChanged = () => {
      pagination.perPage = state.pagination.perPage;
      emitPagination();
    };

    const emitPagination = () => {
      if (isServerSidePagingRef.value) {
        pagination.currentPage = state.pagination.currentPage - 1;
        const param = Object.assign({}, pagination);
        context.emit('changePagination', param);
      }
    };

    onMounted(() => {
      // override value: per-page
      // eslint-disable-next-line no-prototype-builtins
      if (context.attrs.hasOwnProperty('per-page') && context.attrs['per-page'] && !isNaN(context.attrs['per-page'])) {
        state.pagination.perPage = context.attrs['per-page'];
      }
      // override value: empty-text
      // eslint-disable-next-line no-prototype-builtins
      if (context.attrs.hasOwnProperty('empty-text') && context.attrs['empty-text']) {
        state.emptyText = context.attrs['empty-text'];
      }
      // override value: empty-filtered-text
      // eslint-disable-next-line no-prototype-builtins
      if (context.attrs.hasOwnProperty('empty-filtered-text') && context.attrs['empty-filtered-text']) {
        state.emptyFilterText = String(context.attrs['empty-filtered-text']);
      }
    });

    return {
      isServerSidePagingRef,
      state,
      // methods
      handlePicked,
      handleGotoPage,
      handleSortChanged,
      handlePageChanged,
    };
  },
};
</script>

<style scoped>
.goto-input {
  width: 50px;
}
</style>

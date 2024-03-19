<template>
  <div>
    <slot name="header" />
    <b-row align-v="center" class="pb-2 pt-2 m-0 filter-row" v-if="$attrs.items && $attrs.items.length > 0" v-show="!hideFilter">
      <b-col cols="4" class="pl-0">
        <b-form-group label="篩選：" label-for="filter-input" label-cols="3" label-align-md="right" label-size="sm" class="mb-0">
          <b-input-group size="sm">
            <b-form-input id="filter-input" v-model="state.filter" type="search" placeholder="輸入篩選關鍵字"></b-form-input>

            <b-input-group-append>
              <b-button :disabled="!state.filter" @click="state.filter = ''">清除</b-button>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
      </b-col>

      <b-col cols="7">
        <b-form-checkbox-group v-model="state.filterOn" size="sm" :options="state.filterFields" value-field="key" text-field="label">
        </b-form-checkbox-group>
      </b-col>

      <b-col cols="1" class="pr-0">
        <table-fields-picker v-bind="$attrs" @picked="handlePicked" />
      </b-col>
    </b-row>

    <b-table
      v-bind="$attrs"
      v-on="$listeners"
      hover
      :striped="false"
      responsive
      show-empty
      :empty-text="state.emptyText"
      :empty-filtered-text="state.emptyFilterText"
      :fields="state.pickedFields"
      :per-page="state.pagination.perPage"
      :current-page="state.pagination.currentPage"
      :filter="state.filter"
      :filter-included-fields="state.filterOn"
      :filter-ignored-fields="state.ignoredFields"
      @filtered="state.filtered"
      @sort-changed="handleSortChanged($event)"
    >
      <template v-for="(_, slot) of $scopedSlots" v-slot:[slot]="scope">
        <slot :name="slot" v-bind="scope" />
      </template>
    </b-table>

    <b-row align-v="center" class="mb-3" v-if="$attrs.items && $attrs.items.length > 0">
      <b-col cols="3">
        <b-input-group prepend="每頁" append="筆" size="sm" class="col-10 float-right">
          <b-form-select
            v-model="state.pagination.perPage"
            :options="state.pagination.perPageOptions"
            @input="handlePageChanged"
          ></b-form-select>
        </b-input-group>
      </b-col>

      <b-col cols="6">
        <b-pagination
          class="my-0"
          align="fill"
          size="sm"
          v-model="state.pagination.currentPage"
          :total-rows="state.pagination.totalRows"
          :per-page="state.pagination.perPage"
          @input="handlePageChanged"
        />
      </b-col>

      <b-col cols="3">
        <span>
          頁次 {{ state.pagination.currentPage }}/{{ state.pagination.totalPages !== 0 ? state.pagination.totalPages : 1 }} ，共
          {{ state.pagination.totalRows }} 筆
        </span>
      </b-col>
    </b-row>
  </div>
</template>

<script lang="ts">
import { computed, reactive, onMounted, watch } from '@vue/composition-api';
import { Pagination } from '@/shared/model/pagination.model';
import TableFieldsPicker from '@/shared/table-fields-picker/table-fields-picker.vue';
export default {
  name: 'i-table-lite',
  inheritAttrs: false,
  components: {
    TableFieldsPicker,
  },
  props: {
    // 表頭標題
    title: {
      type: String,
      required: false,
    },
    filterIgnoredFields: {
      type: Array,
      required: false,
      default: () => [],
    },
    hideFilter: {
      type: Boolean,
      required: false,
      default: false,
    },
  },
  setup(props, context) {
    // 判斷型別方式
    // console.log('typeof:',typeof context.attrs.fields === 'object')
    // console.log('instanceof:',context.attrs.fields instanceof Array)
    // console.log('constructor:',context.attrs.fields.constructor === Array)

    const state = reactive({
      emptyText: '查無資料',
      emptyFilterText: '篩選後無對應資料，請選擇其他篩選條件。',
      pickedFields: [],
      filterFields: [],
      ignoredFields: computed(() =>
        context.attrs.fields && context.attrs.fields instanceof Array
          ? context.attrs.fields.filter((field: any) => !state.filterFields.includes(field)).map((field: { key: any }) => field.key)
          : []
      ),
      pagination: {
        perPageOptions: [10, 20, 30, 50, 100],
        perPage: 10,
        currentPage: 1,
        totalPages: computed(() => Math.ceil(state.pagination.totalRows / state.pagination.perPage)),
        totalRows: 0,
        gotoPage: undefined,
      },
      filter: null,
      filterOn: [],
      filtered: (filteredItems: string | any[]) => {
        state.pagination.totalRows = filteredItems.length;
        // state.pagination.currentPage = 1;
      },
    });

    const handlePicked = (pickedFields: any) => {
      state.pickedFields = pickedFields;
      state.filterFields = pickedFields.filter((field: { key: any }) => {
        if (props.filterIgnoredFields instanceof Array) return !props.filterIgnoredFields.includes(field.key);
      });
    };

    const handleGotoPage = () => {
      const gotoPage = state.pagination.gotoPage;
      if (!isNaN(gotoPage) && gotoPage > 0 && gotoPage <= state.pagination.totalPages) {
        state.pagination.currentPage = gotoPage;
      }
      state.pagination.gotoPage = undefined;
    };

    const pagination = new Pagination(1, 10, [], 'ASC');

    const handleSortChanged = (ctx: { sortBy: string; sortDesc: any }) => {
      if (ctx.sortBy) {
        pagination.sortBy = [ctx.sortBy];
        pagination.sortDirection = ctx.sortDesc ? 'DESC' : 'ASC';
      }
    };

    const handlePageChanged = () => {
      pagination.perPage = state.pagination.perPage;
    };

    onMounted(() => {
      // override value: per-page
      // eslint-disable-next-line no-prototype-builtins
      if (context.attrs.hasOwnProperty('per-page') && context.attrs['per-page'] && !isNaN(<any>context.attrs['per-page'])) {
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

    watch(
      () => context.attrs.items,
      () => {
        if (context.attrs.items instanceof Array) state.pagination.totalRows = context.attrs.items.length;
      },
      { immediate: true }
    );

    return {
      state,
      handlePicked,
      handleGotoPage,
      handleSortChanged,
      handlePageChanged,
    };
  },
};
</script>

<style scoped>
.filter-row {
  background-color: #d3f7f5;
  /* color: #697a7a; */
}
</style>

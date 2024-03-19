<template>
  <div class="list-box-wrapper">
    <div class="list-box-item">
      <div class="search-box">
        <input v-model="searchSource" type="text" placeholder="輸入關鍵字搜尋..." />
        <div v-if="searchSource" class="clear-search" title="清除條件" @click="searchSource = ''"></div>
      </div>
      <ul class="list-group-flush border rounded list-box">
        <li
          v-for="(item, key) in source
            .map((item, inx) => ({ inx, ...item }))
            .filter(item => item[label in item ? label : 'label'].toLowerCase().includes(searchSource.toLowerCase()))"
          v-bind:key="key"
          :class="'list-item' + (item.selected ? ' active' : '')"
          @click="selectSource(searchSource ? item.inx : key)"
        >
          {{ item[label in item ? label : 'label'] }}
        </li>
        <li
          v-if="
            source.filter(item => item[label in item ? label : 'label'].toLowerCase().includes(searchSource.toLowerCase())).length == 0 &&
            source.length
          "
          class="list-item"
        >
          查無資料
        </li>
      </ul>
      <div class="bulk-action">
        <div class="select-all" @click="selectAllSource">全選</div>
        <div class="deselect-all" @click="deSelectAllSource">全不選</div>
      </div>
    </div>
    <div class="actions">
      <div class="btn-action" @click="moveDestination">&gt;</div>
      <div class="btn-action" @click="moveAllDestination">&gt;&gt;</div>
      <div class="btn-action" @click="moveSource">&lt;</div>
      <div class="btn-action" @click="moveAllSource">&lt;&lt;</div>
    </div>
    <div class="list-box-item">
      <div class="search-box">
        <input v-model="searchDestination" type="text" placeholder="輸入關鍵字搜尋..." />
        <div v-if="searchDestination" class="clear-search" title="清除條件" @click="searchDestination = ''"></div>
      </div>
      <ul class="list-group-flush border rounded list-box">
        <li
          v-for="(item, key) in destination
            .map((item, inx) => ({ inx, ...item }))
            .filter(item => item[label in item ? label : 'label'].toLowerCase().includes(searchDestination.toLowerCase()))"
          v-bind:key="key"
          :class="'list-item' + (item.selected ? ' active' : '')"
          @click="selectDestination(searchDestination ? item.inx : key)"
        >
          {{ item[label in item ? label : 'label'] }}
        </li>
        <li
          v-if="
            destination.filter(item => item[label in item ? label : 'label'].toLowerCase().includes(searchDestination.toLowerCase()))
              .length == 0 && destination.length
          "
          class="list-item"
        >
          查無資料
        </li>
      </ul>
      <div class="bulk-action">
        <div class="select-all" @click="selectAllDestination">全選</div>
        <div class="deselect-all" @click="deSelectAllDestination">全不選</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    source: Array,
    destination: Array,
    label: String,
  },
  data: function () {
    return {
      searchSource: '',
      searchDestination: '',
    };
  },
  methods: {
    moveDestination: function () {
      let selected = this.source.filter(f => f.selected);
      if (!selected.length) return;
      selected = selected.map(item => ({
        ...item,
        selected: false,
      }));
      let destination = [...selected, ...this.destination];
      let source = this.source.filter(f => !f.selected);
      this.searchSource = '';
      this.searchDestination = '';
      this.$emit('onChangeList', {
        source,
        destination,
      });
    },
    moveSource: function () {
      let selected = this.destination.filter(f => f.selected);
      if (!selected.length) return;
      selected = selected.map(item => ({
        ...item,
        selected: false,
      }));
      let source = [...selected, ...this.source];
      let destination = this.destination.filter(f => !f.selected);
      this.searchSource = '';
      this.searchDestination = '';
      this.$emit('onChangeList', {
        source,
        destination,
      });
    },
    moveAllDestination: function () {
      let destination = [...this.source.map(item => ({ ...item, selected: false })), ...this.destination];
      let source = [];
      this.searchSource = '';
      this.searchDestination = '';
      this.$emit('onChangeList', {
        source,
        destination,
      });
    },
    moveAllSource: function () {
      let source = [...this.destination.map(item => ({ ...item, selected: false })), ...this.source];
      let destination = [];
      this.searchSource = '';
      this.searchDestination = '';
      this.$emit('onChangeList', {
        source,
        destination,
      });
    },
    selectDestination: function (key) {
      let source = this.source;
      let destination = this.destination.map((i, k) => {
        if (k === key) {
          i.selected = !i.selected;
        }
        return i;
      });
      this.$emit('onChangeList', {
        source,
        destination,
      });
    },
    selectSource: function (key) {
      let destination = this.destination;
      let source = this.source.map((i, k) => {
        if (k === key) {
          i.selected = !i.selected;
        }
        return i;
      });
      this.$emit('onChangeList', {
        source,
        destination,
      });
    },
    selectAllSource: function () {
      let source = this.source.map(item => ({ ...item, selected: true }));
      let destination = this.destination;
      this.$emit('onChangeList', {
        source,
        destination,
      });
    },
    deSelectAllSource: function () {
      let source = this.source.map(item => ({ ...item, selected: false }));
      let destination = this.destination;
      this.$emit('onChangeList', {
        source,
        destination,
      });
    },
    selectAllDestination: function () {
      let destination = this.destination.map(item => ({
        ...item,
        selected: true,
      }));
      let source = this.source;
      this.$emit('onChangeList', {
        source,
        destination,
      });
    },
    deSelectAllDestination: function () {
      let destination = this.destination.map(item => ({
        ...item,
        selected: false,
      }));
      let source = this.source;
      this.$emit('onChangeList', {
        source,
        destination,
      });
    },
  },
};
</script>
<style>
*,
*::before,
*::after {
  box-sizing: border-box;
}

.list-box-wrapper {
  font-family: sans-serif;
  width: 100%;
  display: flex;
  align-items: center;
}
.list-box-wrapper > div {
  flex: 1;
}
.list-box-wrapper .list-box-item {
  border: solid 1px #cccccc;
  border-radius: 3px;
}
.list-box-wrapper .list-box-item .search-box {
  border-bottom: solid 1px #cccccc;
  position: relative;
}
.list-box-wrapper .list-box-item .search-box input {
  border: none;
  width: 100%;
  padding: 0.5rem 1rem;
}
.list-box-wrapper .list-box-item .search-box .clear-search {
  position: absolute;
  padding: 0.5rem;
  right: 0;
  top: 0;
  cursor: pointer;
  font-weight: bold;
  color: #e74c3c;
}
.list-box-wrapper .list-box-item .list-box {
  height: 250px;
  overflow: auto;
  list-style: none;
  padding: 0;
  margin: 0;
}
.list-box-wrapper .list-box-item .list-box .list-item {
  padding: 0.5rem 1rem;
  border-bottom: solid 1px #cccccc;
  cursor: pointer;
}
.list-box-wrapper .list-box-item .list-box .list-item.active {
  background-color: #eeeeee;
}
.list-box-wrapper .list-box-item .bulk-action {
  display: flex;
  border-top: solid 1px #cccccc;
  text-align: center;
}
.list-box-wrapper .list-box-item .bulk-action .select-all {
  width: 100%;
  padding: 0.8rem;
  background-color: #007bff;
  color: #fff;
}
.list-box-wrapper .list-box-item .bulk-action .deselect-all {
  width: 100%;
  padding: 0.8rem;
  background-color: #6c757d;
  color: #fff;
}
.list-box-wrapper .actions {
  flex: none;
  padding: 0 1rem;
}
.list-box-wrapper .actions .btn-action {
  margin-bottom: 0.5rem;
}

.btn-action {
  display: inline-block;
  font-weight: 400;
  color: #212529;
  text-align: center;
  vertical-align: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  background-color: transparent;
  border: 1px solid transparent;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  line-height: 1.5;
  border-radius: 0.25rem;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  display: block;
  width: 100%;
  color: #fff;
  background-color: #007bff;
  border-color: #007bff;
  cursor: pointer;
}
.btn-action svg {
  vertical-align: middle;
}
</style>

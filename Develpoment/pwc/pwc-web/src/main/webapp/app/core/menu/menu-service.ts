import Vue from 'vue';
import VueCompositionAPI, { ref } from '@vue/composition-api';
import { Menu } from '@/core/menu/menu';
import axios from 'axios';
import { Store } from 'vuex';
import { 
  cloneDeep as _cloneDeep, keys as _keys, includes as _includes, filter as _filter, findIndex as _findIndex, 
  assign as _assign, reverse as _reverse, uniq as _uniq, take as _take, takeRight as _takeRight, 
  concat as _concat
} from 'lodash';

Vue.use(VueCompositionAPI);

export default class MenuService {
  category = 'pwc';

  constructor(private store: Store<any>) {}

  retrieveAppMenu() {
    const data = ref<Menu[]>(null);
    axios.get('content/menu.json', { baseURL: '' }).then(response => {
      data.value = response.data;
    });
    return { data };
  }

  // 來自 ADM_FUNCTION
  retrieveAppMenu_() {
    const data = ref<Menu[]>(null);
    axios.get(`/menu/${this.category}`).then(response => {
      data.value = buildMenuData(response.data);
    });
    return { data };
  }

  retrieveOpenMenu() {
    const data = ref<Menu[]>(null);
    axios.get('content/open-menu.json', { baseURL: '' }).then(response => {
      data.value = response.data;
    });
    return { data };
  }

  toggleMenu() {
    this.store.commit('toggleMenu');
  }

  getMenuOpenStatus(): boolean {
    return this.store.getters.isMenuOpen;
  }
}

function buildMenuData(data) {
  let arr = [], layerArr = [], count = 0;
  if (data.length === 0) return;
  data.forEach((d, index) => { flattenResponseData(arr, d, layerArr, index); })
  layerArr = _reverse(_uniq(layerArr));
  buildArrOrder(arr, count);
  rebuildNestedMenuData(arr, layerArr);
  arr = _filter(arr, a => { return a.meta.layer === 1; });
  arr.forEach(ar => { buildAttrForArrData(ar, ar.meta.functionId); });
  specialMenuForBid(arr);
  return arr;
}

function flattenResponseData(arr, item, layerArr, layer) {
  const clone = _cloneDeep(item), keys = _keys(clone);
  const obj = {
    id: '',
    path: clone.functionPath,
    meta: {
      functionId: clone.functionId,
      parentId: clone.masterFunctionId,
      rootId: '',
      label: clone.functionName,
      layer: layer,
      show: true,
    },
    origin: clone,
    children: [],
  }
  layerArr.push(layer);
  arr.push(obj);
  if (!_includes(keys, 'children')) return;
  if (clone.children.length === 0) return;
  clone.children.forEach(child => { flattenResponseData(arr, child, layerArr, layer + 1); });
}

function buildArrOrder(arr, count) {
  arr.forEach(a => {
    if (a.meta.layer === 0) return;
    count += 1
    a.id = count;
  })
}

function buildAttrForArrData(data, rootId) {
  data.meta.rootId = rootId;
  if (data.children.length === 0) return;
  data.meta = _assign(data.meta, {directory: true, icon: 'tasks'});
  data.children.forEach(child => { buildAttrForArrData(child, rootId); });
}

function specialMenuForBid(arr) {
  // const fIndex = _findIndex(arr, a => { return a.meta.functionId === 'pwc002' });
  // if (fIndex === -1) return;
  // const fLayerData = arr[fIndex];
  // const sIndex = _findIndex(fLayerData.children, fc => { return fc.meta.functionId === 'bid' });
  // if (sIndex === -1) return;
  // const frontArr = _take(arr[fIndex].children, sIndex + 1), behindArr = _takeRight(arr[fIndex].children, arr[fIndex].children.length - (sIndex + 1));
  // let tmpArr = [];
  // arr[fIndex].children[sIndex].children.forEach(c => {
  //   const clone = _cloneDeep(c);
  //   clone.meta.layer = 3;
  //   clone.meta.show = false;
  //   tmpArr.push(clone);
  // });
  // arr[fIndex].children = _concat(frontArr, tmpArr, behindArr);
}

function rebuildNestedMenuData(arr, layerArr) {
  layerArr.forEach(num => {
    // const filterArr = _filter(arr, a => { return a.meta.layer === num });
    // if (filterArr.length === 0) return;
    // filterArr.forEach(f => {
    //   const index = _findIndex(arr, ar => { return f.meta.parentId === ar.meta.functionId })
    //   if (index !== -1) arr[index].children.push(f);
    // })
  })
} 

import Vue from 'vue';
import VueCompositionAPI, { ref } from '@vue/composition-api';
import { Menu } from '@/core/menu/menu';
import axios from 'axios';

Vue.use(VueCompositionAPI);

export default class MenuService {
  retrieveAppMenu() {
    const data = ref<Menu[]>(null);
    axios.get('content/menu.json', { baseURL: '' }).then(response => {
      data.value = response.data;
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
}

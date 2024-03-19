<template>
  <div :class="fontSizeClassRef">
    <block-ui :is-loading="isLoading"></block-ui>
    <b-button class="fixed-button" variant="Info" @click="toggleSideBar"> <font-awesome-icon icon="bars"></font-awesome-icon> </b-button>
    <app-ribbon></app-ribbon>
    <app-header v-if="authenticated"></app-header>
    <open-header v-else @changeFontSizeClass="changeFontSizeClass"></open-header>
    <div class="">
      <b-sidebar width="85%" id="my-sidebar" shadow>
        <open-menu class="col-12"></open-menu>
        <div class="d-flex justify-content-center">
          <router-link to="/login" class="btn-link mt-4 pr-8 col-6 text-white">工程雲端系統登入</router-link>
          <div class="mt-4 d-flex justify-content-center">｜</div>
          <router-link to="/" class="btn-link mt-4 pl-4 col-5 text-white">帳號註冊</router-link>
        </div>
      </b-sidebar>
    </div>
    <div class="belt" v-if="authenticated">公共工程全生命週期管理</div>
    <div class="d-flex">
      <template v-if="routeData && routeData.toName !== 'login'">
        <app-menu v-show="isMenuOpen" class="col-md-2" v-if="authenticated" @reloadContent="reload"></app-menu>
        <open-menu :class="isMenuOpen ? 'col-md-2 d-none d-sm-block' : 'd-none'" v-else></open-menu>
      </template>
      <div class="bg pb-3 col-md-10" :class="routeData && routeData.toName !== 'login' && isMenuOpen ? 'col-md-10' : 'col-md-12'">
        <app-breadcrumb></app-breadcrumb>
        <keep-alive :include="keepAlivePage">
          <router-view v-if="isContentAlive"></router-view>
        </keep-alive>
      </div>
    </div>
    <app-footer></app-footer>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import AppHeader from '@/core/header/app-header.vue';
import OpenHeader from '@/core/header/open-header.vue';
import AppMenu from '@/core/menu/menu.vue';
import OpenMenu from '@/core/menu/open-menu.vue';
import AppBreadcrumb from '@/core/menu/breadcrumb.vue';
import AppFooter from '@/core/footer/app-footer.vue';
import BlockUi from '@/core/block-ui/block-ui.vue';
import { provide, ref, nextTick, onMounted, reactive, } from '@vue/composition-api';
import MenuService from '@/core/menu/menu-service';
import NotificationService from '@/shared/notification/notification-service';
import { BvModal } from 'bootstrap-vue';
import axios from 'axios';
import { dateReviver } from '@/shared/date/json-date-reviver';
import Ribbon from '@/core/ribbon/ribbon.vue';
import '@/shared/config/dayjs';
import { useGetters } from '@u3u/vue-hooks';
import {
  BButton, 
  BSidebar,
} from 'bootstrap-vue';

const ALERT_HEADER = 'x-pwc-alert';
const ALERT_MESSAGE = 'x-pwc-params';

export default {
  name: 'App',
  components: {
    BButton,
    AppHeader,
    OpenHeader,
    AppMenu,
    OpenMenu,
    AppBreadcrumb,
    AppFooter,
    AppRibbon: Ribbon,
    BlockUi,
    BSidebar
  },
  // mounted() {
  //   subset('#text_sung', 'sung');
  // },
  setup(_prop, context) {
    const pwcSearchStr = reactive({data: ''});
    const doPWCSearch = reactive({data: false});
    const useNewMenu = reactive({data: false});
    const menuService = new MenuService(context.root.$store);
    const notificationService = new NotificationService(context.root);
    provide('menuService', new MenuService());
    provide<NotificationService>('notificationService', notificationService);
    provide<BvModal>('$bvModal', overrideBvModal(context.root.$bvModal));
    provide('pwcSearchStr', pwcSearchStr);
    provide('doPWCSearch', doPWCSearch);
    provide('useNewMenu', useNewMenu);

    function overrideBvModal(bvModal: BvModal): BvModal {
      return {
        msgBoxOk: (message, options) =>
          bvModal.msgBoxOk(message, {
            ...options,
            okTitle: options && options.okTitle ? options.okTitle : '確定',
          }),
        msgBoxConfirm: (message, options) =>
          bvModal.msgBoxConfirm(message, {
            ...options,
            okTitle: options && options.okTitle ? options.okTitle : '確定',
            cancelTitle: options && options.cancelTitle ? options.cancelTitle : '取消',
          }),
        show: id => bvModal.show(id),
        hide: id => bvModal.hide(id),
      };
    }

    const fontSizeClassRef = ref('font-medium');

    axios.interceptors.response.use(
      response => {
        if (response.headers[ALERT_HEADER] === 'SUCCESS' || response.headers[ALERT_HEADER] === 'INFO') {
          notificationService.info(decodeURIComponent(response.headers[ALERT_MESSAGE]));
        }
        return response;
      },
      error => {
        return Promise.reject(error);
      }
    );

    axios.defaults.transformResponse = [
      data => {
        if (data && typeof data === 'string') {
          let response;
          try {
            response = JSON.parse(data, dateReviver);
          } catch (error) {
            throw Error(`Error parsing response JSON data - ${JSON.stringify(error)}`);
          }
          return response;
        }
        return data;
      },
    ];

    const isContentAlive = ref(true);

    const changeFontSizeClass = (fontSizeClass: string) => (fontSizeClassRef.value = fontSizeClass);

    const reload = () => {
      isContentAlive.value = false;
      nextTick(() => (isContentAlive.value = true));
    };

    // const getI18n = () => {
    //   axios.get('i18n/i18n.json', { baseURL: '' }).then(response => {
    //     i18n.mergeLocaleMessage('zh', response.data.zh);
    //   });
    // };

    // getI18n();
    const toggleSideBar = () => {
      menuService.toggleMenu();
      isMenuOpen.value = menuService.getMenuOpenStatus();
    };

    let isMenuOpen = ref(true);

    onMounted(() => { preventF1ToF12KeyupEvent(); });

    function preventF1ToF12KeyupEvent () {
      if (window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1') return;
      document.onkeydown = function (event) {
        if (event.keyCode === Vue.config.keyCodes.f1) preventBrowserEventAndHint(event);
        else if (event.keyCode === Vue.config.keyCodes.f2) preventBrowserEventAndHint(event);
        else if (event.keyCode === Vue.config.keyCodes.f3) preventBrowserEventAndHint(event);
        else if (event.keyCode === Vue.config.keyCodes.f4) preventBrowserEventAndHint(event);
        else if (event.keyCode === Vue.config.keyCodes.f5) preventBrowserEventAndHint(event);
        else if (event.keyCode === Vue.config.keyCodes.f6) preventBrowserEventAndHint(event);
        else if (event.keyCode === Vue.config.keyCodes.f7) preventBrowserEventAndHint(event);
        else if (event.keyCode === Vue.config.keyCodes.f8) preventBrowserEventAndHint(event);
        else if (event.keyCode === Vue.config.keyCodes.f9) preventBrowserEventAndHint(event);
        else if (event.keyCode === Vue.config.keyCodes.f10) preventBrowserEventAndHint(event);
        else if (event.keyCode === Vue.config.keyCodes.f11) preventBrowserEventAndHint(event);
        else if (event.keyCode === Vue.config.keyCodes.f12) preventBrowserEventAndHint(event);
      }
    }

    function preventBrowserEventAndHint (event) {
      event.preventDefault();
      notificationService.danger('本系統不支援此功能按鈕F1~F12');
    }

    return {
      fontSizeClassRef,
      isContentAlive,
      isMenuOpen,
      ...useGetters(['authenticated', 'routeData', 'isLoading', 'keepAlivePage']),
      changeFontSizeClass,
      reload,
      toggleSideBar,
      preventF1ToF12KeyupEvent,
      preventBrowserEventAndHint,
    };
  },
};
</script>
<style scoped>
.bg {
  background-color: #f5f5f5;
  background-clip: padding-box;
}
.btn-link {
  border-radius: 10rem;
  background: #1aa4b7;
  text-align: center;
}
.fixed-button {
  position: fixed;
  z-index: 10;
}
</style>

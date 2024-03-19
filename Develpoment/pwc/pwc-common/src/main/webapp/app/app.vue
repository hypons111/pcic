<template>
  <div :class="fontSizeClassRef">
    <block-ui :is-loading="isLoading"></block-ui>
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
        <app-menu class="col-md-2" v-if="authenticated" @reloadContent="reload"></app-menu>
        <open-menu class="col-md-2 d-none d-sm-block" v-else></open-menu>
      </template>
      <div id="text_sung" class="bg pb-3 col-md-10" :class="routeData && routeData.toName !== 'login' ? 'col-md-10' : 'col-md-12'">
        <app-breadcrumb v-if="authenticated"></app-breadcrumb>
        <keep-alive :include="keepAlivePage">
          <router-view v-if="isContentAlive"></router-view>
        </keep-alive>
      </div>
    </div>
    <app-footer></app-footer>
  </div>
</template>

<script lang="ts">
import AppHeader from '@/core/header/app-header.vue';
import OpenHeader from '@/core/header/open-header.vue';
import AppMenu from '@/core/menu/menu.vue';
import OpenMenu from '@/core/menu/open-menu.vue';
import AppBreadcrumb from '@/core/menu/breadcrumb.vue';
import AppFooter from '@/core/footer/app-footer.vue';
import BlockUi from '@/core/block-ui/block-ui.vue';
import { provide, ref, nextTick } from '@vue/composition-api';
import MenuService from '@/core/menu/menu-service';
import NotificationService from '@/shared/notification/notification-service';
import { BvModal } from 'bootstrap-vue';
import axios from 'axios';
import { dateReviver } from '@/shared/date/json-date-reviver';
import Ribbon from '@/core/ribbon/ribbon.vue';
import '@/shared/config/dayjs';
import { useGetters } from '@u3u/vue-hooks';
// import i18n from './shared/i18n';

const ALERT_HEADER = 'x-pwc-alert';
const ALERT_MESSAGE = 'x-pwc-params';

export default {
  name: 'App',
  components: {
    AppHeader,
    OpenHeader,
    AppMenu,
    OpenMenu,
    AppBreadcrumb,
    AppFooter,
    AppRibbon: Ribbon,
    BlockUi,
  },
  // mounted() {
  //   subset('#text_sung', 'sung');
  // },
  setup(_prop, context) {
    provide('menuService', new MenuService());
    const notificationService = new NotificationService(context.root);
    provide<NotificationService>('notificationService', notificationService);
    provide<BvModal>('$bvModal', context.root.$bvModal);

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
    provide<BvModal>('$bvModal', context.root.$bvModal);

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

    return {
      fontSizeClassRef,
      isContentAlive,
      ...useGetters(['authenticated', 'routeData', 'isLoading', 'keepAlivePage']),
      changeFontSizeClass,
      reload,
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
</style>

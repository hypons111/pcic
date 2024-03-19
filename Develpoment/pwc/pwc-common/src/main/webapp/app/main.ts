// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.common with an alias.
import './polyfills';
import Vue from 'vue';
import i18n from '@/shared/i18n';
import VueCompositionAPI from '@vue/composition-api';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
import hooks from '@u3u/vue-hooks';
import App from './app.vue';
import Vue2Filters from 'vue2-filters';
import { FormDatepickerPlugin, ToastPlugin, ModalPlugin } from 'bootstrap-vue';
import router from './router';
import * as config from './shared/config/config';
import 'bootstrap';
import 'mousetrap';
import 'mousetrap/plugins/global-bind/mousetrap-global-bind';
import * as bootstrapVueConfig from './shared/config/config-bootstrap-vue';
import JhiItemCountComponent from './shared/jhi-item-count.vue';
import JhiSortIndicatorComponent from './shared/sort/jhi-sort-indicator.vue';
import ITable from './shared/i-table/i-table.vue';
import IDatePicker from './shared/i-date-picker/i-date-picker.vue';
import IDualDatePicker from './shared/i-date-picker/i-dual-date-picker.vue';
import IFormInput from './shared/form/i-form-input.vue';
import IButton from './shared/buttons/i-button.vue';
import InfiniteLoading from 'vue-infinite-loading';
import LoginService from './account/login.service';
import AccountService from './account/account.service';
import IAddressPicker from './shared/i-address-picker/i-addr-picker.vue';
import VuePromiseBtn from 'vue-promise-btn';
import IFormGroupCheck from './shared/form/i-form-group-check.vue';
import IPhoneNum from '@/shared/form/i-phone-num.vue';
import ITelNum from '@/shared/form/i-tel-num.vue';
import ITel from '@/shared/i-tel/i-tel.vue';
import DualListBox from './shared/dual-list-box.vue';
import SIdentify from '@/shared/form/identify.vue';
import IGroupSelectCheckbox from '@/shared/form/i-group-select-checkbox.vue';
import IPartOfMessage from '@/shared/i-part-of-message/i-part-of-message.vue';
import IPDFViewer from '@/shared/report/i-pdf-viewer.vue';
import VueApexCharts from 'vue-apexcharts';
import IApexCharts from '@/shared/chart/i-apexcharts.vue';
import IFormSingleCheck from '@/shared/form/i-form-single-check.vue';

import 'vue-promise-btn/dist/vue-promise-btn.css';
import '../content/scss/vendor.scss';

import { ShortcutDirective } from '@/directives/shortcut-directive';
import { CurrencyDirective } from '@/directives/currency-directive';
import axios from 'axios';
import Print from 'vue-print-nb';
import AdmInfoService from '@/shared/config/service/adm-info.service';
/* tslint:disable */

// jhipster-needle-add-entity-service-to-main-import - JHipster will import entities services here

/* tslint:enable */
library.add(fas);
Vue.config.productionTip = false;
config.initVueApp(Vue);
config.initFortAwesome(Vue);
bootstrapVueConfig.initBootstrapVue(Vue);
Vue.use(Print);
Vue.use(VueCompositionAPI);
Vue.use(Vue2Filters);
Vue.use(ToastPlugin);
Vue.use(ModalPlugin);
Vue.use(FormDatepickerPlugin, {
  BFormDatepicker: {
    dateFormatOptions: { year: 'numeric', month: '2-digit', day: '2-digit' },
    valueAsDate: true,
    labelCalendar: '日曆',
    labelCloseButton: '關閉',
    labelCurrentMonth: '目前月份',
    labelHelp: '使用方向鍵切換日期',
    labelNav: '瀏覽',
    labelNextDecade: '下十年',
    labelNextMonth: '下一月',
    labelNextYear: '下一年',
    labelNoDateSelected: '日期尚未選取',
    labelPrevDecade: '前十年',
    labelPrevMonth: '前一月',
    labelPrevYear: '前一年',
    labelResetButton: '重設',
    labelSelected: '選取的日期',
    labelToday: '今日',
  },
});
Vue.use(VuePromiseBtn);
Vue.use(hooks);
Vue.use(VueApexCharts);
Vue.component('font-awesome-icon', FontAwesomeIcon);
Vue.component('jhi-item-count', JhiItemCountComponent);
Vue.component('jhi-sort-indicator', JhiSortIndicatorComponent);
Vue.component('infinite-loading', InfiniteLoading);
Vue.component('i-table', ITable);
Vue.component('i-form-input', IFormInput);
Vue.component('i-date-picker', IDatePicker);
Vue.component('i-dual-date-picker', IDualDatePicker);
Vue.component('i-button', IButton);
Vue.component('i-form-group-check', IFormGroupCheck);
Vue.component('i-button', IButton);
Vue.directive('i-shortcut', ShortcutDirective);
Vue.directive('i-currency', CurrencyDirective);
Vue.component('i-address-picker', IAddressPicker);
Vue.component('i-phone-num', IPhoneNum);
Vue.component('i-tel-num', ITelNum);
Vue.component('i-tel', ITel);
Vue.component('dual-list-box', DualListBox);
Vue.component('SIdentify', SIdentify);
Vue.component('i-group-select-checkbox', IGroupSelectCheckbox);
Vue.component('i-part-of-message', IPartOfMessage);
Vue.component('i-pdf-viewer', IPDFViewer);
Vue.component('apexchart', VueApexCharts);
Vue.component('i-apexcharts', IApexCharts);
Vue.component('i-form-single-check', IFormSingleCheck);

const store = config.initVueXStore(Vue);

const loginService = new LoginService();
const accountService = new AccountService(store, router);
new AdmInfoService(store);

const serviceUrlList: string[] = ['/login', '/service/', '/home'];

axios.defaults.baseURL = 'api';
router.beforeEach((to, from, next) => {
  if (to.path === '/forbidden' || to.path === '/not-found' || to.path === '/error') {
    next();
    return;
  }

  const routeData = {
    fromPath: from.path,
    toPath: to.path,
    fromName: from.name,
    toName: to.name,
    isNotKeepAlive: Boolean(to.params.isNotKeepAlive),
  };
  store.commit('setRouteData', routeData);

  accountService.currentFunctionId = '';
  routeGuard(to, from, next);
});

function routeGuard(to, from, next) {
  if (!to.matched.length) {
    next('/not-found');
  }

  if (
    to.fullPath === '/' ||
    to.path === '/login' ||
    to.path === '/forbidden' ||
    to.path === '/not-found' ||
    to.path === '/error' ||
    serviceUrlList.find(url => to.fullPath.startsWith(url))
  ) {
    if (sessionStorage.getItem('requested-url') === null) {
      sessionStorage.setItem('requested-url', to.fullPath);
    }
    to.meta.title = '公共工程雲端服務網';
    document.title = to.meta.title;
    next();
  } else {
    to.meta.title = '公共工程雲端系統';
    document.title = to.meta.title;
    accountService.checkAuth().then(authenticated => {
      if (authenticated) {
        if (to.params.isNotKeepAlive) {
          store.commit('removeKeepAlivePage', from.name);
        } else if (from.name) {
          store.commit('addKeepAlivePage', from.name);
        }

        if (to.path === '/') {
          next();
        } else if (to.meta && to.meta.functionId && to.meta.functionId.length > 0) {
          accountService.hasAnyAuthority(to.meta.functionId).then(hasAuthority => {
            if (hasAuthority) {
              accountService.currentFunctionId = to.meta.functionId;
              next();
            } else {
              next('/forbidden');
            }
          });
        } else {
          alert('請確定 Route 中的 meta.functionId 設定正確');
          next('/forbidden');
        }
      } else if (to.matched.length) {
        sessionStorage.setItem('requested-url', to.fullPath);
        next('/login');
      }
    });
  }
}

/* tslint:disable */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>',
  router,
  provide: {
    loginService: () => loginService,
    // jhipster-needle-add-entity-service-to-main - JHipster will import entities services here
    accountService: () => accountService,
  },
  store,
  i18n,
});

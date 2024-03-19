import Vue from 'vue';
import i18n from '@/shared/i18n';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import hooks, { useGetters } from '@u3u/vue-hooks';
import App from './app.vue';
import './polyfills';
import router from './router';
import * as config from './shared/config/config';
import 'bootstrap';
import { BootstrapVueIcons, ModalPlugin, VBTooltipPlugin } from 'bootstrap-vue';
import AdmInfoService from '@/shared/config/service/adm-info.service';
import axios from 'axios';
import '../content/scss/vendor.scss';
import AccountService from './account/account.service';
import LoginService from './account/login.service';
import MenuService from './core/menu/menu-service';
// import VueKeycloakJs from '@dsb-norge/vue-keycloak-js'; $$$
import { trim as _trim, size as _size, keys as _keys, cloneDeep as _cloneDeep, clone as _clone } from 'lodash';

Vue.config.productionTip = false;
config.initVueApp(Vue);
config.initFortAwesome(Vue);
Vue.use(BootstrapVueIcons);
Vue.use(ModalPlugin);
Vue.use(VBTooltipPlugin);
Vue.use(hooks);
Vue.component('font-awesome-icon', FontAwesomeIcon);

// const keycloakCookie: string[] = ['KEYCLOAK_SESSION_LEGACY', 'KEYCLOAK_IDENTITY_LEGACY', 'KEYCLOAK_IDENTITY', 'KEYCLOAK_SESSION'];
const store = config.initVueXStore(Vue);
const loginService = new LoginService();
const accountService = new AccountService(store, router);
const menuService = new MenuService(store);
new AdmInfoService(store);
const serviceUrlList: string[] = ['/login', '/service/', '/home'];
axios.defaults.baseURL = 'api';
//透過hashMap處理上下一頁的props
const paramMap = {};

//重新整理的時候，如果session storage有token，就幫他登入
// if (_size(_trim(sessionStorage.getItem('token'))) !== 0) accountService.retrieveAccountWithKeycloak();

//重新整理前，把parmMap存到localStorage
window.onbeforeunload = () => {
  if (_size(paramMap) > 0) localStorage.setItem('userParamMap', JSON.stringify(paramMap));
};
if (_trim(localStorage.getItem('userParamMap')).length > 0) {
  Object.assign(paramMap, JSON.parse(localStorage.getItem('userParamMap')));
  localStorage.removeItem('userParamMap');
}

router.beforeEach((to, from, next) => {
  if (to.path === '/forbidden' || to.path === '/not-found' || to.path === '/error') {
    next();
    return;
  }
  if (to.fullPath === '/service/pwc0601') sessionStorage.setItem('beforeEnterPwc0601', from.path);
  
  const routeData = {
    fromPath: from.path,
    toPath: to.path,
    fromName: from.name,
    toName: to.name,
    isNotKeepAlive: Boolean(to.params.isNotKeepAlive),
  };
  store.commit('setRouteData', routeData);

  accountService.currentFunctionId = '';

  // keycloakCookie.forEach(element => {
  //   delete_cookie(element, '/auth/realms/pwcWeb/', '192.168.57.200');
  // });

  routeGuard(to, from, next);
});

// 功能操作紀錄 ADM_FUNCTION_CLICK
function saveFucntionClickRoute(to, from) {
  if (_trim(to.name) === '' || _trim(to.meta.functionId) === '') return;
  if (to.path != '/' || (to.path !== '/login' && to.path !== '/forbidden' && to.path !== '/error' && from.name !== null)) {
    if (from.name !== null) {
      if (from.name == undefined || (!to.name.startsWith(from.name) && !from.name.startsWith(to.name))) {
        const form = {
          userId: '',
          functionId: to.meta.functionId,
          functionExtendId: to.name,
          createTime: new Date(),
        };
        axios.post('/service/adm-function-click', form).finally(() => {});
      }
    }
  }
}

function recordProps(to) {
  if (to !== null && to !== undefined && to.name !== null && to.name !== undefined) {
    if (to.params !== null && to.params !== undefined && _size(_keys(to.params)) !== 0) {
      paramMap[to.name] = to.params
      //如果to的param是空的,就去map裡面找,並塞給to
    } else {
      const inMemoryParam = _cloneDeep(paramMap[to.name]);
      if (inMemoryParam !== null && inMemoryParam !== undefined) Object.assign(to.params, inMemoryParam);
    }
  }
}

function routeGuard(to, from, next) {
  //紀錄router name對應的props，處理上下一頁props不見的問題
  recordProps(to);
  // console.log('接到paramMap',paramMap);
  //處理找不到router
  if (!to.matched.length) {
    next('/not-found');
  }
  //紀錄admFunctionClick用
  saveFucntionClickRoute(to, from);

  if (
    //這一部分是不需要權限的destination
    to.fullPath === '/' ||
    to.path === '/login' ||
    to.path === '/forbidden' ||
    to.path === '/not-found' ||
    to.path === '/error' ||
    serviceUrlList.find(url => to.fullPath.startsWith(url))
  ) {
    if (sessionStorage.getItem('requested-url') === null) {
      //這應該是算瀏覽人次的
      if (document.cookie.indexOf('_counter=') == -1) {
        getHomeCounter();
      }
      //把to存到sessionStorage的requested-url屬性裡
      sessionStorage.setItem('requested-url', to.fullPath);
    }
    to.meta.title = '公共工程雲端服務網';
    document.title = to.meta.title;
    //直接過去
    next();
  } else {
    to.meta.title = '公共工程雲端系統';
    document.title = to.meta.title;
    const closeAuthCheck = _trim(localStorage.getItem('routerGuard')) === 'routerGuard';
    if (closeAuthCheck) {
      localStorage.removeItem('routerGuard');
      next();
    }
    //判斷權限如果有權限會回傳true
    accountService.checkAuth().then((authenticated: boolean) => {
      //有權限的話
      if (authenticated) {
        if (to.params.isNotKeepAlive) {
          store.commit('removeKeepAlivePage', from.name);
        } else if (from.name) {
          store.commit('addKeepAlivePage', from.name);
        }
        //確認identity
        const userIdentity = useGetters(['account']).account.value;
        //判斷使用者要不要換密碼
        let guardPwb0201pwd = false;
        if (_trim(userIdentity) !== '') guardPwb0201pwd = userIdentity.isPadOverdue === 'Y' || userIdentity.isPadUpdate === 'Y';
        //如果是首頁 就直接過去
        if (to.path === '/') {
          if (guardPwb0201pwd) {
            toRestPad(to, next);
          } else {
            next();
          }
          //判斷權限
        } else if (to.meta && to.meta.functionId && to.meta.functionId.length > 0) {
          if (guardPwb0201pwd) {
            toRestPad(to, next);
          } else {
            //如果前往的頁面的functionId在 account的authorities裡面，就過去
            //不然就導向/forbidden
            accountService.hasAnyAuthority((to.meta.functionId.toLowerCase())).then(hasAuthority => {
              if (hasAuthority) {
                accountService.currentFunctionId = to.meta.functionId;
                next();
              } else {
                next('/forbidden');
              }
            });
          }
        } else {
          next('/forbidden');
        }
        //沒有權限的話
      } else if (to.matched.length) {
        if (to.fullPath === '/bidAce011Info') next();
        else {
          sessionStorage.setItem('requested-url', to.fullPath);
          next('/');
        }
      }
    });
  }
}

// 以防進入 修改pwd 導致死迴圈
function toRestPad(to, next) {
  if (to.name === 'pwb0201pwd') {
    next();
  } else {
    next('/pwb0201/pwb0201pwd');
  }
}

// Vue.use(VueKeycloakJs, { $$$
//   init: {
//     onLoad: 'check-sso',
//     silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html'
//   },
//   config: {
//     url: process.env.KEYCLOAK_URL,
//     realm: process.env.KEYCLOAK_REALM,
//     clientId: process.env.KEYCLOAK_CLIENT_ID,
//   },
//   bearerExcludedUrls: ['/forbidden', '/not-found', '/error', '/', '/homeApply'],
//   onReady: keycloak => {
//     tokenInterceptor();

//     if (keycloak.authenticated) {
//       window.sessionStorage.setItem('token', keycloak.token);
//       accountService.retrieveAccountWithKeycloak();
//     }
//   },
// });

function getHomeCounter() {
  axios.get('/service/homeCounter/pwc/get').then(({ data }) => {
    document.cookie = '_counter=' + data;
  });
}

function tokenInterceptor() {
  axios.interceptors.request.use(
    config => {
      const token = localStorage.getItem('token') || sessionStorage.getItem('token');
      if (Vue.prototype.$keycloak.authenticated) config.headers.Authorization = `Bearer ${token}`;
      return config;
    },
    error => {
      return Promise.reject(error);
    }
  );
}

new Vue({
  render: h => h(App),
  router,
  provide: {
    loginService: () => loginService,
    accountService: () => accountService,
    menuService: () => menuService,
  },
  store,
  i18n,
}).$mount('#app');

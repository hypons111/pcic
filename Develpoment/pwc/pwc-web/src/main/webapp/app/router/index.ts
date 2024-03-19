import Vue from 'vue';
import Component from 'vue-class-component';
import ServiceWeb from '@/router/service-web';

Component.registerHooks([
  'beforeRouteEnter',
  'beforeRouteLeave',
  'beforeRouteUpdate', // for vue-router 2.2+
]);
import Router from 'vue-router';
// import licRouter from './lic-router';
import mrpRouter from '../router/mrp-router';
// import engRouter from '../router/eng-router';
// import geoRouter from '../router/geo-router';
// import bidRouter from '../router/bid-router';
// import pwcRouter from '../router/pwc-router';
// import recRouter from './rec-router';
// import revRouter from './rev-router';
// import pwbRouter from '../router/pwb-router';
// import proRouter from '../router/pro-router';
const Home = () => import('@/components/home.vue');
const HomeInformation = () => import('@/components/home/home-information.vue');
const HomeDownload = () => import('@/components/home/home-download.vue');
const LoginForm = () => import('@/account/login-form/login-form.vue');
const Ogn01Query = () => import('@/components/ogn01/ogn01-query.vue');
const Ogn01EditInfo = () => import('@/components/ogn01/ogn01-edit-info.vue');
const BulletinEditMsg = () => import('@/components/bulletin/bulletin-edit-msg.vue');
const BulletinEditMsgDetail = () => import('@/components/bulletin/bulletin-edit-msg-detail.vue');

const Error = () => import('@/core/error/error.vue');

Vue.use(Router);

// prettier-ignore
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '',
      component: Home,
      props: true,
    },
    {
      path: '/',
      name: 'home',
      component: Home,
      props: true,
    },
    {
      path: '/login',
      name: 'login',
      component: LoginForm
    },
    {
      path: '/ogn01',
      name: 'ogn01',
      component: Ogn01Query,
      meta: {
        functionId: 'ogn01'
      },
      props: true,
    },
    {
      path: '/ogn01/edit-info',
      name: 'ogn01-edit-info',
      component: Ogn01EditInfo,
      meta: {
        functionId: 'ogn01',
      },
      props: true,
    },
    {
      path: '/home/homeDownload',
      name: 'home-download',
      component: HomeDownload,
      props: true,
    },
    {
      path: '/home/homeInformation',
      name: 'home-information',
      component: HomeInformation,
      props: true,
    },
    {
      path: '/bulletin',
      name: 'bulletin-edit-msg',
      component: BulletinEditMsg,
      meta: {
        functionId: 'bulletin'
      },
      props: true,
    },
    {
      path: '/bulletin/edit-detail',
      name: 'bulletin-edit-msg-detail',
      component: BulletinEditMsgDetail,
      meta: {
        functionId: 'bulletin',
      },
      props: true,
    },
    {
      path: '/forbidden',
      name: 'Forbidden',
      component: Error,
      meta: { error403: true }
    },
    {
      path: '/not-found',
      name: 'NotFound',
      component: Error,
      meta: { error404: true }
    },


    ...mrpRouter,
    // ...engRouter,
    // ...geoRouter,
    ...ServiceWeb,
    // ...licRouter,
    // ...bidRouter,
    // ...pwcRouter,
    // ...recRouter,
    // ...revRouter,
    // ...pwbRouter,
    // ...proRouter,
  ],
  scrollBehavior() {
    window.scroll({top: 0, left: 0, behavior: 'smooth'});
  }
});

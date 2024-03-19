import Vue from 'vue';
import Component from 'vue-class-component';

Component.registerHooks([
  'beforeRouteEnter',
  'beforeRouteLeave',
  'beforeRouteUpdate', // for vue-router 2.2+
]);
import Router from 'vue-router';
import AccountManagement from '@/router/account-management';
import BackendManagement from '@/router/backend-management';
import AuditManagement from '@/router/audit-management';
import ServiceWeb from '@/router/service-web';

const Home = () => import('@/components/home.vue');
const LoginForm = () => import('@/account/login-form/login-form.vue');
const Ogn01 = () => import('@/components/ogn01/ogn01.vue');
const Error = () => import('@/core/error/error.vue');

Vue.use(Router);

// prettier-ignore
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '',
      component: Home
    },
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: LoginForm
    },
    {
      path: '/ogn01',
      name: 'ogn01',
      component: Ogn01,
      meta: {
        functionId: 'ogn01'
      }
    },

    ...AccountManagement,
    ...BackendManagement,
    ...AuditManagement,
    ...ServiceWeb,
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
  ],
  scrollBehavior() {
    window.scroll({ top: 0, left: 0, behavior: 'smooth' });
  }
});

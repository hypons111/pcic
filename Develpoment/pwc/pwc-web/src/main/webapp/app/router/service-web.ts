const ServiceHome = () => import('@/components/home.vue');

import tecRouter from './tec-router';

export default [
  {
    path: '/service/home',
    name: 'ServiceHome',
    component: ServiceHome,
    meta: {
      functionId: 'ServiceHome',
      label: '首頁',
    },
  },
  ...tecRouter,
];

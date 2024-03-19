const tec0101RQuery = () => import('@/components/tec/tec0101/tec0101R-query.vue');
const tec0101RDetail = () => import('@/components/tec/tec0101/tec0101R-detail.vue');
const tec0201RQuery = () => import('@/components/tec/tec0201/tec0201R-query.vue');
const tec0202RQuery = () => import('@/components/tec/tec0202/tec0202R-query.vue');

export default [
  {
    path: '/service/tec0101R',
    name: 'tec0101R',
    component: tec0101RQuery,
    meta: {
      functionId: 'tec0101R',
    },
    props: true,
  },
  {
    path: '/service/tec0101R/Detail',
    name: 'tec0101R-detail',
    component: tec0101RDetail,
    meta: {
      functionId: 'tec0101R',
    },
    props: true,
  },
  {
    path: '/service/tec0201R',
    name: 'tec0201R-query',
    component: tec0201RQuery,
    meta: {
      functionId: 'tec0201R',
    },
    props: true,
  },
  {
    path: '/service/tec0202R',
    name: 'tec0202R-query',
    component: tec0202RQuery,
    meta: {
      functionId: 'tec0202R',
    },
    props: true,
  },
];

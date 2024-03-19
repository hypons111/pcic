const Adm015Query = () => import('@/components/adm015/adm015-query.vue');
const Adm015EditInfo = () => import('@/components/adm015/adm015-edit-info.vue');
const Adm016Query = () => import('@/components/adm016/adm016-query.vue');
const Adm016EditInfo = () => import('@/components/adm016/adm016-edit-info.vue');
const Adm017Query = () => import('@/components/adm017/adm017-query.vue');

export default [
  {
    path: '/adm015',
    name: 'adm015',
    component: Adm015Query,
    meta: {
      functionId: 'adm015',
    },
    props: true,
  },
  {
    path: '/adm015/edit-info',
    name: 'adm015-edit-info',
    component: Adm015EditInfo,
    meta: {
      functionId: 'adm015',
    },
    props: true,
  },
  {
    path: '/adm016',
    name: 'adm016',
    component: Adm016Query,
    meta: {
      functionId: 'adm016',
    },
    props: true,
  },
  {
    path: '/adm016/edit-info',
    name: 'adm016-edit-info',
    component: Adm016EditInfo,
    meta: {
      functionId: 'adm016',
    },
    props: true,
  },
  {
    path: '/adm017',
    name: 'adm017',
    component: Adm017Query,
    meta: {
      functionId: 'adm017',
    },
    props: true,
  },
];

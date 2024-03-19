const Adm008Query = () => import('@/components/adm008/adm008-query.vue');
const Adm008EditInfo = () => import('@/components/adm008/adm008-edit-info.vue');
const Adm009Query = () => import('@/components/adm009/adm009-query.vue');
const Adm009EditInfo = () => import('@/components/adm009/adm009-edit-info.vue');
const Adm010Query = () => import('@/components/adm010/adm010-query.vue');
const Adm010EditInfo = () => import('@/components/adm010/adm010-edit-info.vue');
const Adm011Query = () => import('@/components/adm011/adm011-query.vue');
const Adm011EditInfo = () => import('@/components/adm011/adm011-edit-info.vue');
const Adm012Query = () => import('@/components/adm012/adm012-query.vue');
const Adm012EditInfo = () => import('@/components/adm012/adm012-edit-info.vue');
const Adm013Query = () => import('@/components/adm013/adm013-query.vue');
const Adm013EditInfo = () => import('@/components/adm013/adm013-edit-info.vue');
const Adm014Query = () => import('@/components/adm014/adm014-query.vue');
const Adm014EditInfo = () => import('@/components/adm014/adm014-edit-info.vue');

export default [
  {
    path: '/adm008',
    name: 'adm008',
    component: Adm008Query,
    meta: {
      functionId: 'adm008',
    },
    props: true,
  },
  {
    path: '/adm008/edit-info',
    name: 'adm008-edit-info',
    component: Adm008EditInfo,
    meta: {
      functionId: 'adm008',
    },
    props: true,
  },
  {
    path: '/adm009',
    name: 'adm009',
    component: Adm009Query,
    meta: {
      functionId: 'adm009',
    },
    props: true,
  },
  {
    path: '/adm009/edit-info',
    name: 'adm009-edit-info',
    component: Adm009EditInfo,
    meta: {
      functionId: 'adm009',
    },
    props: true,
  },
  {
    path: '/adm010',
    name: 'adm010',
    component: Adm010Query,
    meta: {
      functionId: 'adm010',
    },
    props: true,
  },
  {
    path: '/adm010/edit-info',
    name: 'adm010-edit-info',
    component: Adm010EditInfo,
    meta: {
      functionId: 'adm010',
    },
    props: true,
  },
  {
    path: '/adm011',
    name: 'adm011',
    component: Adm011Query,
    meta: {
      functionId: 'adm011',
    },
    props: true,
  },
  {
    path: '/adm011/edit-info',
    name: 'adm011-edit-info',
    component: Adm011EditInfo,
    meta: {
      functionId: 'adm011',
    },
    props: true,
  },
  {
    path: '/adm012',
    name: 'adm012',
    component: Adm012Query,
    meta: {
      functionId: 'adm012',
    },
    props: true,
  },
  {
    path: '/adm012/edit-info',
    name: 'adm012-edit-info',
    component: Adm012EditInfo,
    meta: {
      functionId: 'adm012',
    },
    props: true,
  },
  {
    path: '/adm013',
    name: 'adm013',
    component: Adm013Query,
    meta: {
      functionId: 'adm013',
    },
    props: true,
  },
  {
    path: '/adm013/edit-info',
    name: 'adm013-edit-info',
    component: Adm013EditInfo,
    meta: {
      functionId: 'adm013',
    },
    props: true,
  },
  {
    path: '/adm014',
    name: 'adm014',
    component: Adm014Query,
    meta: {
      functionId: 'adm014',
    },
    props: true,
  },
  {
    path: '/adm014/edit-info',
    name: 'adm014-edit-info',
    component: Adm014EditInfo,
    meta: {
      functionId: 'adm014',
    },
    props: true,
  },
];

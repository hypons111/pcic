const Adm002 = () => import('@/components/adm002/adm002-edit-info.vue');
const Adm003Query = () => import('@/components/adm003/adm003-query.vue');
const Adm003EditInfo = () => import('@/components/adm003/adm003-edit-info.vue');
const Adm003RoleInfo = () => import('@/components/adm003/adm003-role-info.vue');
const Adm004Query = () => import('@/components/adm004/adm004-query.vue');
const Adm004EditInfo = () => import('@/components/adm004/adm004-edit-info.vue');
const Adm004RoleInfo = () => import('@/components/adm004/adm004-role-info.vue');
const Adm005Query = () => import('@/components/adm005/adm005-query.vue');
const Adm005EditInfo = () => import('@/components/adm005/adm005-edit-info.vue');
const Adm006Query = () => import('@/components/adm006/adm006-query.vue');
const Adm006EditInfo = () => import('@/components/adm006/adm006-edit-info.vue');
const Adm007Query = () => import('@/components/adm007/adm007-query.vue');
const Adm007EditInfo = () => import('@/components/adm007/adm007-edit-info.vue');

export default [
  {
    path: '/adm002',
    name: 'adm002',
    component: Adm002,
    meta: {
      functionId: 'adm002',
    },
    props: true,
  },
  {
    path: '/adm003',
    name: 'adm003',
    component: Adm003Query,
    meta: {
      functionId: 'adm003',
    },
    props: true,
  },
  {
    path: '/adm003/edit-info',
    name: 'adm003-edit-info',
    component: Adm003EditInfo,
    meta: {
      functionId: 'adm003',
    },
    props: true,
  },
  {
    path: '/adm003/role-info',
    name: 'adm003-role-info',
    component: Adm003RoleInfo,
    meta: {
      functionId: 'adm003',
    },
    props: true,
  },
  {
    path: '/adm004',
    name: 'adm004',
    component: Adm004Query,
    meta: {
      functionId: 'adm004',
    },
    props: true,
  },
  {
    path: '/adm004/edit-info',
    name: 'adm004-edit-info',
    component: Adm004EditInfo,
    meta: {
      functionId: 'adm004',
    },
    props: true,
  },
  {
    path: '/adm004/role-info',
    name: 'adm004-role-info',
    component: Adm004RoleInfo,
    meta: {
      functionId: 'adm004',
    },
    props: true,
  },
  {
    path: '/adm005',
    name: 'adm005',
    component: Adm005Query,
    meta: {
      functionId: 'adm005',
    },
    props: true,
  },
  {
    path: '/adm005/edit-info',
    name: 'adm005-edit-info',
    component: Adm005EditInfo,
    meta: {
      functionId: 'adm005',
    },
    props: true,
  },
  {
    path: '/adm006',
    name: 'adm006',
    component: Adm006Query,
    meta: {
      functionId: 'adm006',
    },
    props: true,
  },
  {
    path: '/adm006/edit-info',
    name: 'adm006-edit-info',
    component: Adm006EditInfo,
    meta: {
      functionId: 'adm006',
    },
    props: true,
  },
  {
    path: '/adm007',
    name: 'adm007',
    component: Adm007Query,
    meta: {
      functionId: 'adm007',
    },
    props: true,
  },
  {
    path: '/adm007/edit-info',
    name: 'adm007-edit-info',
    component: Adm007EditInfo,
    meta: {
      functionId: 'adm007',
    },
    props: true,
  },
];

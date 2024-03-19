const pwb0203 = () => import('@/components/pwb/pwb0203.vue');
const pwb0204 = () => import('@/components/pwb/pwb0204.vue');
const pwb0103 = () => import('@/components/pwb/pwb0103.vue');
const pwb0201 = () => import('@/components/pwb/pwb0201.vue');
const pwb0201pwd = () => import('@/components/pwb/pwb0201pwd.vue');
const pwb0301 = () => import('@/components/pwb/pwb0301.vue');
const pwb0302 = () => import('@/components/pwb/pwb0302.vue');
const pwb0202 = () => import('@/components/pwb/pwb0202.vue');
const pwb0202EditInfo = () => import('@/components/pwb/pwb0202-edit-info.vue');
const pwb0303 = () => import('@/components/pwb/pwb0303.vue');
const pwb0205 = () => import('@/components/pwb/pwb0205.vue');
const pwb0401 = () => import('@/components/pwb/pwb0401.vue');
const pwb0401EditInfo = () => import('@/components/pwb/pwb0401-edit-info.vue');
const pwb0402 = () => import('@/components/pwb/pwb0402.vue');
const pwb0402EditInfo = () => import('@/components/pwb/pwb0402-edit-info.vue');
const pwb0102 = () => import('@/components/pwb/pwb0102.vue');


export default [
  {
    path: '/pwb0203',
    name: 'pwb0203',
    component: pwb0203,
    meta: {
      functionId: 'pwb0203',
    },
    props: true,
  },
  {
    path: '/pwb0204',
    name: 'pwb0204',
    component: pwb0204,
    meta: {
      functionId: 'pwb0204',
    },
    props: true,
  },
  {
    path: '/pwb0103',
    name: 'pwb0103',
    component: pwb0103,
    meta: {
      functionId: 'pwb0103',
    },
    props: true,
  },
  {
    path: '/pwb0201',
    name: 'pwb0201',
    component: pwb0201,
    meta: {
      functionId: 'pwb0201',
    },
    props: true,
  },
  {
    path: '/pwb0201/pwb0201pwd',
    name: 'pwb0201pwd',
    component: pwb0201pwd,
    meta: {
      functionId: 'pwb0201',
    },
    props: true,
  },
  {
    path: '/pwb0301',
    name: 'pwb0301',
    component: pwb0301,
    meta: {
      functionId: 'pwb0301',
    },
  },
  {
    path: '/pwb0202',
    name: 'pwb0202',
    component: pwb0202,
    meta: {
      functionId: 'pwb0202',
    },
    props: true,
  },
  {
    path: '/pwb0302',
    name: 'pwb0302',
    component: pwb0302,
    meta: {
      functionId: 'pwb0302',
    },
    props: true,
  },
  {
    path: '/pwb0202/edit',
    name: 'pwb0202EditInfo',
    component: pwb0202EditInfo,
    meta: {
      functionId: 'pwb0202',
    },
    props: true,
  },
  {
    path: '/pwb0303',
    name: 'pwb0303',
    component: pwb0303,
    meta: {
      functionId: 'pwb0303',
    },
    props: true,
  },
  {
    path: '/pwb0205',
    name: 'pwb0205',
    component: pwb0205,
    meta: {
      functionId: 'pwb0205',
    },
    props: true,
  },
  {
    path: '/pwb0401',
    name: 'pwb0401',
    component: pwb0401,
    meta: {
      functionId: 'pwb0401',
    },
    props: true,
  },
  {
    path: '/pwb0401/edit',
    name: 'pwb0401EditInfo',
    component: pwb0401EditInfo,
    meta: {
      functionId: 'pwb0401',
    },
    props: true,
  },
  {
    path: '/pwb0402',
    name: 'pwb0402',
    component: pwb0402,
    meta: {
      functionId: 'pwb0402',
    },
    props: true,
  },
  {
    path: '/pwb0402/edit',
    name: 'pwb0402EditInfo',
    component: pwb0402EditInfo,
    meta: {
      functionId: 'pwb0402',
    },
    props: true,
  },
  {
    path: '/pwb0102',
    name: 'pwb0102',
    component: pwb0102,
    meta: {
      functionId: 'pwb0102',
    },
    props: true,
  },
];

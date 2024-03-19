const pwc0303Login = () => import('@/components/pwc/pwc0303.vue');
const pwc0304U = () => import('@/components/pwc/pwc0304.vue');
const pwc0301 = () => import('@/components/pwc/pwc0301.vue');
const pwc0302 = () => import('@/components/pwc/pwc0302.vue');
const pwc0101 = () => import('@/components/pwc/pwc0101.vue');

export default [
  {
    path: '/service/pwc0302',
    name: 'pwc0302',
    component: pwc0302,
    meta: {
      functionId: 'pwc0302',
    },
    props: true,
  },
  {
    path: '/service/pwc0301',
    name: 'pwc0301',
    component: pwc0301,
    meta: {
      functionId: 'pwc0301',
    },
    props: true,
  },
  {
    path: '/service/pwc0303',
    name: 'pwc0303Login',
    component: pwc0303Login,
    meta: {
      functionId: 'pwc0303',
    },
    props: true,
  },
  {
    path: '/service/pwc0304',
    name: 'pwc0304U',
    component: pwc0304U,
    meta: {
      functionId: 'pwc0304',
    },
    props: true,
  },
  {
    path: '/service/pwc0101',
    name: 'pwc0101',
    component: pwc0101,
    meta: {
      functionId: 'pwc0101',
    },
    props: true,
  },
];

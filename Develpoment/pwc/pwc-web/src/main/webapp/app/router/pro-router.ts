const pro0103r1 = () => import('@/components/pro/pro0103r1.vue');
const pro0103r2 = () => import('@/components/pro/pro0103r2.vue');
const pro0103r3 = () => import('@/components/pro/pro0103r3.vue');
const proAd0101Query = () => import('@/components/pro/pro-ad-0101-query.vue');
const proAd0101EditInfo = () => import('@/components/pro/pro-ad-0101-edit-info.vue');
const pro104r1History = () => import('@/components/pro/pro0104.vue');
const proAd0101 = () => import('@/components/pro/pro-ad-0101.vue');

export default [
  {
    path: '/service/proAd0101',
    name: 'proAd0101',
    component: proAd0101,
    meta: {
      functionId: 'proAd0101',
    },
    props: true,
  },
  {
    path: '/service/proAd0101Query/edit',
    name: 'proAd0101EditInfo',
    component: proAd0101EditInfo,
    meta: {
      functionId: 'pro0101',
    },
    props: true,
  },
  {
    path: '/proAd0101Query',
    name: 'proAd0101Query',
    component: proAd0101Query,
    meta: {
      functionId: 'pro0101',
    },
    props: true,
  },
  {
    path: '/proAd0101Query/edit',
    name: 'proAd0101EditInfoBack',
    component: proAd0101EditInfo,
    meta: {
      functionId: 'pro0101',
    },
    props: true,
  },
  {
    path: '/pro0103r1',
    name: 'pro0103r1',
    component: pro0103r1,
    meta: {
      functionId: 'pro0103r1',
    },
    props: true,
  },
  {
    path: '/pro0103r2',
    name: 'pro0103r2',
    component: pro0103r2,
    meta: {
      functionId: 'pro0103r1',
    },
    props: true,
  },
  {
    path: '/pro0103r3',
    name: 'pro0103r3',
    component: pro0103r3,
    meta: {
      functionId: 'pro0103r1',
    },
    props: true,
  },
  {
    path: '/pro104',
    name: 'pro104r1History',
    component: pro104r1History,
    meta: {
      functionId: 'pro0104',
    },
    props: true,
  },
];

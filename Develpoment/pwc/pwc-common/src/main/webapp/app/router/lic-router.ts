const lic0101Query = () => import('@/components/lic/lic0101/lic0101-query.vue');
const lic0201Query = () => import('@/components/lic/lic0201/lic0201-query.vue');
const lic0301Query = () => import('@/components/lic/lic0301/lic0301-query.vue');
const lic0302Query = () => import('@/components/lic/lic0302/lic0302-query.vue');
const lic0303Query = () => import('@/components/lic/lic0303/lic0303-query.vue');
const lic0401ReviewInfo = () => import('@/components/lic/lic0401/lic0401-review-info.vue');
const lic0402ReviewedInfo = () => import('@/components/lic/lic0402/lic0402-reviewed-info.vue');
const lic0501Query = () => import('@/components/lic/lic0501/lic0501-query.vue');
const lic0501EditInfo = () => import('@/components/lic/lic0501/lic0501-edit-info.vue');
const lic0601Query = () => import('@/components/lic/lic0601/lic0601-query.vue');
const lic0601EditInfo = () => import('@/components/lic/lic0601/lic0601-edit-info.vue');
const lic0602Query = () => import('@/components/lic/lic0602/lic0602-query.vue');
const lic0603Query = () => import('@/components/lic/lic0603/lic0603-query.vue');
const lic0603EditInfo = () => import('@/components/lic/lic0603/lic0603-edit-info.vue');
const lic0604Query = () => import('@/components/lic/lic0604/lic0604-query.vue');
const lic0605Query = () => import('@/components/lic/lic0605/lic0605-query.vue');
const lic0605HistoryInfo = () => import('@/components/lic/lic0605/lic0605-history-info.vue');
const lic0701Query = () => import('@/components/lic/lic0701/lic0701-query.vue');
const lic0701EditInfo = () => import('@/components/lic/lic0701/lic0701-edit-info.vue');
const lic0702Query = () => import('@/components/lic/lic0702/lic0702-query.vue');
const lic0702EditInfo = () => import('@/components/lic/lic0702/lic0702-edit-info.vue');
const lic0703Query = () => import('@/components/lic/lic0703/lic0703-query.vue');
const lic0703EditInfo = () => import('@/components/lic/lic0703/lic0703-edit-info.vue');
const lic0704Query = () => import('@/components/lic/lic0704/lic0704-query.vue');
const lic0704EditInfo = () => import('@/components/lic/lic0704/lic0704-edit-info.vue');
const lic0801Query = () => import('@/components/lic/lic0801/lic0801-query.vue');

export default [
  {
    path: '/lic0101',
    name: 'lic0101Query',
    component: lic0101Query,
    meta: {
      functionId: 'lic0101',
    },
    props: true,
  },
  {
    path: '/lic0201',
    name: 'lic0201Query',
    component: lic0201Query,
    meta: {
      functionId: 'lic0301',
    },
    props: true,
  },
  {
    path: '/lic0301',
    name: 'lic0301Query',
    component: lic0301Query,
    meta: {
      functionId: 'lic0301',
    },
    props: true,
  },
  {
    path: '/lic0302',
    name: 'lic0302Query',
    component: lic0302Query,
    meta: {
      functionId: 'lic0301',
    },
    props: true,
  },
  {
    path: '/lic0303',
    name: 'lic0303Query',
    component: lic0303Query,
    meta: {
      functionId: 'lic0301',
    },
    props: true,
  },
  {
    path: '/lic0401',
    name: 'lic0401ReviewInfo',
    component: lic0401ReviewInfo,
    meta: {
      functionId: 'lic0401',
    },
    props: true,
  },
  {
    path: '/lic0402',
    name: 'lic0402ReviewedInfo',
    component: lic0402ReviewedInfo,
    meta: {
      functionId: 'lic0402',
    },
    props: true,
  },
  {
    path: '/lic0501',
    name: 'lic0501Query',
    component: lic0501Query,
    meta: {
      functionId: 'lic0501',
    },
    props: true,
  },
  {
    path: '/lic0501/edit',
    name: 'lic0501EditInfo',
    component: lic0501EditInfo,
    meta: {
      functionId: 'lic0501',
    },
    props: true,
  },
  {
    path: '/lic0601',
    name: 'lic0601Query',
    component: lic0601Query,
    meta: {
      functionId: 'lic0601',
    },
    props: true,
  },
  {
    path: '/lic0601/edit',
    name: 'lic0601EditInfo',
    component: lic0601EditInfo,
    meta: {
      functionId: 'lic0601',
    },
    props: true,
  },
  {
    path: '/lic0602',
    name: 'lic0602Query',
    component: lic0602Query,
    meta: {
      functionId: 'lic0602',
    },
    props: true,
  },
  {
    path: '/lic0603',
    name: 'lic0603Query',
    component: lic0603Query,
    meta: {
      functionId: 'lic0603',
    },
    props: true,
  },
  {
    path: '/lic0603/edit',
    name: 'lic0603EditInfo',
    component: lic0603EditInfo,
    meta: {
      functionId: 'lic0603',
    },
    props: true,
  },
  {
    path: '/lic0604',
    name: 'lic0604Query',
    component: lic0604Query,
    meta: {
      functionId: 'lic0604',
    },
    props: true,
  },
  {
    path: '/lic0605',
    name: 'lic0605Query',
    component: lic0605Query,
    meta: {
      functionId: 'lic0605',
    },
    props: true,
  },
  {
    path: '/lic0605/history',
    name: 'lic0605HistoryInfo',
    component: lic0605HistoryInfo,
    meta: {
      functionId: 'lic0605',
    },
    props: true,
  },
  {
    path: '/lic0701',
    name: 'lic0701Query',
    component: lic0701Query,
    meta: {
      functionId: 'lic0701',
    },
    props: true,
  },
  {
    path: '/lic0701/edit-info',
    name: 'lic0701-edit-info',
    component: lic0701EditInfo,
    meta: {
      functionId: 'lic0701',
    },
    props: true,
  },
  {
    path: '/lic0702',
    name: 'lic0702Query',
    component: lic0702Query,
    meta: {
      functionId: 'lic0702',
    },
    props: true,
  },
  {
    path: '/lic0702/edit-info',
    name: 'lic0702-edit-info',
    component: lic0702EditInfo,
    meta: {
      functionId: 'lic0702',
    },
    props: true,
  },
  {
    path: '/lic0703',
    name: 'lic0703Query',
    component: lic0703Query,
    meta: {
      functionId: 'lic0703',
    },
    props: true,
  },
  {
    path: '/lic0703/edit-info',
    name: 'lic0703-edit-info',
    component: lic0703EditInfo,
    meta: {
      functionId: 'lic0703',
    },
    props: true,
  },
  {
    path: '/lic0704',
    name: 'lic0704Query',
    component: lic0704Query,
    meta: {
      functionId: 'lic0704',
    },
    props: true,
  },
  {
    path: '/lic0704/edit-info',
    name: 'lic0704-edit-info',
    component: lic0704EditInfo,
    meta: {
      functionId: 'lic0704',
    },
    props: true,
  },
  {
    path: '/lic0801',
    name: 'lic0801Query',
    component: lic0801Query,
    meta: {
      functionId: 'lic0801',
    },
    props: true,
  },
];

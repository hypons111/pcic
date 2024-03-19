const HomeApply = () => import('@/components/home-apply.vue');

export default [
  {
    path: '/homeApply',
    name: 'homeApply',
    component: HomeApply,
    meta: {
      functionId: 'homeApply',
      label: '帳號申請ZZ',
    },
  },
];

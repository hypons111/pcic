// ----------DONG----------
const GeoCo01 = () => import('@/components/geo/geo-co-01/geo-co-01.vue');
const GeoIn02 = () => import('@/components/geo/geo-in-02/geo-in-02.vue');
const GeoIn02EditInfo = () => import('@/components/geo/geo-in-02/geo-in-02-edit-info.vue');
const GeoIn03 = () => import('@/components/geo/geo-in-03/geo-in-03.vue');
const GeoMe01 = () => import('@/components/geo/geo-me-01/geo-me-01-edit-info.vue');
const GeoAd04 = () => import('@/components/geo/geo-ad-04/geo-ad-04-query.vue');
const GeoIn01 = () => import('@/components/geo/geo-in-01/geo-in-01-edit-info.vue');
const GeoMe02 = () => import('@/components/geo/geo-me-02/geo-me-02.vue');
const GeoAd07 = () => import('@/components/geo/geo-ad-07/geo-ad-07-query.vue');
const GeoAd08 = () => import('@/components/geo/geo-ad-08/geo-ad-08-query.vue');
// ----------DONG----------
const geo0401Query = () => import('@/components/geo/geo0401/geo0401r1.vue');
const geo0401Add = () => import('@/components/geo/geo0401/geo0401c1.vue');
const geo0401Edit = () => import('@/components/geo/geo0401/geo0401u1.vue');
const geo0502Query = () => import('@/components/geo/geo0502/geo0502-query.vue');
const geo0502Read = () => import('@/components/geo/geo0502/geo0502-read.vue');
const geo0502Edit = () => import('@/components/geo/geo0502/geo0502-edit-info.vue');
const geo0503Query = () => import('@/components/geo/geo0503/geo0503-query.vue');
const geo0503Read = () => import('@/components/geo/geo0503/geo0503-read.vue');
const geo0402 = () => import('@/components/geo/geo0402/geo0402.vue');
const geo0603Query = () => import('@/components/geo/geo0603/geo0603-query.vue');
const geo0603Edit = () => import('@/components/geo/geo0603/geo0603-edit-info.vue');
const geo0202Query = () => import('@/components/geo/geo0202/geo0202-query.vue');
const geo0202Read = () => import('@/components/geo/geo0202/geo0202-read.vue');
const geo0601Read = () => import('@/components/geo/geo0601/geo0601-read.vue');
const geo0607Query = () => import('@/components/geo/geo0607/geo0607-query.vue');

// ----------DONG----------
export default [
  {
    path: '/geoCo01',
    name: 'GeoCo01',
    component: GeoCo01,
    meta: {
      functionId: 'geoCo01',
    },
  },
  {
    path: '/geoIn02',
    name: 'GeoIn02',
    component: GeoIn02,
    meta: {
      functionId: 'geoIn02',
    },
  },
  {
    path: '/geoIn02EditInfo',
    name: 'GeoIn02EditInfo',
    component: GeoIn02EditInfo,
    props: true,
    // props: (route) => route.params,
    meta: {
      functionId: 'geoIn02',
    },
  },
  {
    path: '/geoIn03',
    name: 'GeoIn03',
    component: GeoIn03,
    meta: {
      functionId: 'geoIn03',
    },
  },
  {
    path: '/geoMe01',
    name: 'GeoMe01',
    component: GeoMe01,
    meta: {
      functionId: 'geoMe01',
    },
  },
  {
    path: '/geoAd04',
    name: 'GeoAd04',
    component: GeoAd04,
    meta: {
      functionId: 'geoAd04',
    },
  },
  {
    path: '/geoIn01',
    name: 'GeoIn01',
    component: GeoIn01,
    meta: {
      functionId: 'geoIn01',
    },
  },
  {
    path: '/geoMe02',
    name: 'GeoMe02',
    component: GeoMe02,
    meta: {
      functionId: 'geoMe02',
    },
  },
  {
    path: '/geoAd07',
    name: 'GeoAd07',
    component: GeoAd07,
    meta: {
      functionId: 'geoAd07',
    },
  },
  {
    path: '/geoAd08',
    name: 'GeoAd08',
    component: GeoAd08,
    meta: {
      functionId: 'geoAd08',
    },
  },
  // ----------DONG----------
  {
    path: '/geo0401',
    name: 'geo0401Query',
    component: geo0401Query,
    meta: {
      functionId: 'geo0401',
    },
    props: true,
  },
  {
    path: '/geo0401/Add',
    name: 'geo0401Add',
    component: geo0401Add,
    meta: {
      functionId: 'geo0401',
    },
    props: true,
  },
  {
    path: '/geo0401/Edit',
    name: 'geo0401Edit',
    component: geo0401Edit,
    meta: {
      functionId: 'geo0401',
    },
    props: true,
  },
  {
    path: '/geo0502',
    name: 'geo0502Query',
    component: geo0502Query,
    meta: {
      functionId: 'geo0502',
    },
    props: true,
  },
  {
    path: '/geo0502/Read',
    name: 'geo0502Read',
    component: geo0502Read,
    meta: {
      functionId: 'geo0502',
    },
    props: true,
  },
  {
    path: '/geo0502/Edit',
    name: 'geo0502Edit',
    component: geo0502Edit,
    meta: {
      functionId: 'geo0502',
    },
    props: true,
  },
  {
    path: '/geo0503',
    name: 'geo0503Query',
    component: geo0503Query,
    meta: {
      functionId: 'geo0503',
    },
    props: true,
  },
  {
    path: '/geo0503/Read',
    name: 'geo0503Read',
    component: geo0503Read,
    meta: {
      functionId: 'geo0503',
    },
    props: true,
  },
  {
    path: '/geo0402',
    name: 'geo0402',
    component: geo0402,
    meta: {
      functionId: 'geo0402',
    },
    props: true,
  },
  {
    path: '/geo0603',
    name: 'geo0603Query',
    component: geo0603Query,
    meta: {
      functionId: 'geo0603',
    },
    props: true,
  },
  {
    path: '/geo0603',
    name: 'geo0603Edit',
    component: geo0603Edit,
    meta: {
      functionId: 'geo0603',
    },
    props: true,
  },
  {
    path: '/geo0202',
    name: 'geo0202Query',
    component: geo0202Query,
    meta: {
      functionId: 'geo0202',
    },
    props: true,
  },
  {
    path: '/geo0202',
    name: 'geo0202Read',
    component: geo0202Read,
    meta: {
      functionId: 'geo0202',
    },
    props: true,
  },
  {
    path: '/geo0601',
    name: 'geo0601Read',
    component: geo0601Read,
    meta: {
      functionId: 'geo0601',
    },
    props: true,
  },
  {
    path: '/geo0607',
    name: 'geo0607Query',
    component: geo0607Query,
    meta: {
      functionId: 'geo0607',
    },
    props: true,
  },
];

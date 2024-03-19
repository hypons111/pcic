import { Module } from 'vuex';

export const keepAliveStore: Module<any, any> = {
  state: {
    keepAlivePage: [],
    isLoading: false,
    routeData: null,
  },
  getters: {
    keepAlivePage: state => state.keepAlivePage,
    isLoading: state => state.isLoading,
    routeData: state => state.routeData,
  },
  mutations: {
    addKeepAlivePage(state, name) {
      const nonDistinctArray = state.keepAlivePage.concat(name);
      state.keepAlivePage = nonDistinctArray.filter((target, index, array) => array.indexOf(target) == index);
    },
    removeKeepAlivePage(state, name) {
      state.keepAlivePage = state.keepAlivePage.filter(page => page.toLowerCase() !== name.toLowerCase());
    },
    clearKeepAlivePages(state) {
      state.keepAlivePage.splice(0, state.keepAlivePage.length);
    },
    setLoadingStatus(state, status: boolean) {
      state.isLoading = status;
    },
    setRouteData(state, routeData) {
      if (!routeData.fromName) {
        routeData.fromName = 'home';
      }
      state.routeData = routeData;
    },
  },
};

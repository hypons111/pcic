import axios, { AxiosResponse } from 'axios';

import { useStore } from '@u3u/vue-hooks';

const TIMEOUT = 1000000;
const onRequestSuccess = config => {
  window.scroll({ top: 0, left: 0, behavior: 'smooth' });
  const token = localStorage.getItem('jhi-authenticationToken') || sessionStorage.getItem('jhi-authenticationToken');
  if (token) {
    if (!config.headers) {
      config.headers = {};
    }
    config.headers.Authorization = `Bearer ${token}`;

    const functionId = useStore().value.getters.currentFunctionId;
    if (functionId) {
      config.headers['X-FUNCTION-ID'] = functionId;
    }
  }
  config.timeout = TIMEOUT;
  config.url = `${config.url}`;
  useStore().value.commit('setLoadingStatus', true);
  return config;
};
const setupAxiosInterceptors = (onUnauthenticated, onServerError) => {
  const onResponseError = err => {
    const status = err.status || err.response.status;
    if (status === 403 || status === 401) {
      onUnauthenticated();
    }
    if (status >= 500) {
      onServerError();
    }
    useStore().value.commit('setLoadingStatus', false);
    return Promise.reject(err);
  };

  if (axios.interceptors) {
    axios.interceptors.request.use(onRequestSuccess);
    axios.interceptors.response.use((response: AxiosResponse) => {
      useStore().value.commit('setLoadingStatus', false);
      return response;
    }, onResponseError);
  }
};

export { onRequestSuccess, setupAxiosInterceptors };

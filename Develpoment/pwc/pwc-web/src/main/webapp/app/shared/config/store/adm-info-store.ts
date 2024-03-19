import { IAdmPostAddr } from '@/shared/model/adm/adm-post-addr.model';
import { IAdmSysCode } from '@/shared/model/adm/adm-sys-code.model';
import { Module } from 'vuex';

interface IAdmInfo {
  admSysCode?: IAdmSysCode[] | null;
  admPostAddr?: IAdmPostAddr[] | null;
}

export const admInfoStore: Module<IAdmInfo, any> = {
  state: {
    admSysCode: [],
    admPostAddr: [],
  },
  actions: {
    handleAdmSysCode({ commit }, data) {
      commit('SET_ADM_SYS_CODE', data);
    },
    handleAdmPostAddr({ commit }, data) {
      commit('SET_ADM_POST_ADDR', data);
    },
  },
  mutations: {
    SET_ADM_SYS_CODE(state, data) {
      state.admSysCode = data;
    },
    SET_ADM_POST_ADDR(state, data) {
      state.admPostAddr = data;
    },
  },
  getters: {
    admSysCode: state => state.admSysCode,
    admPostAddr: state => state.admPostAddr,
  },
};

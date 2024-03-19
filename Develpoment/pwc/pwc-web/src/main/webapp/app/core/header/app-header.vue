<template>
  <header>
    <div class="top-nav pr-3">
      <router-link to="/" class="btn-link">回首頁</router-link>
      <router-link to="/" class="btn-link">帳號管理</router-link>
      <span>您好，{{ user.userId + '-' + user.chName + '（' + user.unitName + '）' }}</span>
      <span>current version: {{ versionTime }}</span>
      <a class="btn-link" @click.prevent="logout">登出</a>
    </div>
    <div class="top-bar pr-3">
      <router-link to="/" class="navbar-brand">
        <img src="../../../content/images/custom/logo.png" height="68" class="d-inline-block align-top" alt="首頁" />
      </router-link>

      <div id="searchBox">
        <input type="search" placeholder="搜尋..." />
        <button><img src="../../../content/images/custom/ic-search.svg" alt="搜尋" /></button>
      </div>
    </div>
  </header>
</template>

<script lang="ts">
import { useGetters, useRouter, useStore } from '@u3u/vue-hooks';
import { ref, computed } from '@vue/composition-api';
import { size as _size, isString as _isString } from 'lodash';

export default {
  name: 'AppHeader',
  setup(props, context) {
    // const accountService = inject<() => AccountService>('accountService')();
    // const user = computed(() => accountService.user);

    const user = ref(useGetters(['account']).account.value);

    //console.log(user.value);

    const versionTime = computed(() => dateTimeStr(VERSION));

    function dateTimeStr(str) {
      let d = new Date();
      if (_isString(str)) d = new Date(str);
      const year = d.getFullYear(),
        month = d.getMonth() + 1,
        date = d.getDate(),
        hour = d.getHours(),
        minute = d.getMinutes(),
        second = d.getSeconds();
      return (
        year.toString() +
        '-' +
        addZeroPrefix(month) +
        '-' +
        addZeroPrefix(date) +
        ' ' +
        addZeroPrefix(hour) +
        ':' +
        addZeroPrefix(minute) +
        ':' +
        addZeroPrefix(second)
      );
    }

    function addZeroPrefix(value) {
      return _size(value.toString()) === 1 ? '0' + value.toString() : value.toString();
    }

    function logout() {
      localStorage.removeItem('jhi-authenticationToken');
      sessionStorage.removeItem('jhi-authenticationToken');
      localStorage.removeItem('requested-url');
      sessionStorage.removeItem('requested-url');
      useStore().value.commit('logout');
      useRouter()
        .router.push('/')
        .catch(() => {
          context.emit('reloadContent');
        });
    }

    return {
      user,
      versionTime,
      ...useGetters(['authenticated', 'account']),
      logout,
      dateTimeStr,
    };
  },
};
</script>
<style scoped>
header > .top-nav {
  font-size: 14px;
  color: #fff;
  background-color: #1aa4b7;
}
header > .top-nav {
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
}
header > .top-nav {
  text-align: right;
  font-weight: 500;
}
header > .top-nav > span {
  color: rgba(0, 0, 0, 0.5);
}
header > .top-nav > a {
  margin-left: 0.25rem;
  margin-right: 0.25rem;
  color: #fff;
}
header > .top-nav > a:hover {
  border-bottom: 1px solid;
}
header > .top-bar {
  background-color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
#searchBox {
  display: flex;
  align-items: flex-end;
}
#searchBox input {
  border: 0;
  height: 36px;
  border-bottom: 1px solid #1aa4b7;
}
#searchBox input:focus-visible {
  outline: none;
  background-color: #eee;
}
#searchBox button {
  align-items: center;
  justify-content: center;
  display: flex;
  height: 36px;
  width: 36px;
  padding: 4px;
  border: 0;
  border-bottom: 1px solid #1aa4b7;
  background: transparent;
}
</style>

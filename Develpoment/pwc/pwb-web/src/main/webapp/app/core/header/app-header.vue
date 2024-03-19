<template>
  <header>
    <div class="top-nav pr-3">
      <router-link to="/" class="btn-link">回首頁</router-link>
      <router-link to="/" class="btn-link">帳號管理</router-link>
      <span>您好，{{ user.id + '-' + user.name + '（' + user.unit.name + '）' }}</span>
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
import { computed, inject } from '@vue/composition-api';
import AccountService from '@/account/account.service';

export default {
  name: 'AppHeader',
  setup() {
    const accountService = inject<() => AccountService>('accountService')();

    const user = computed(() => accountService.user);

    function logout() {
      localStorage.removeItem('jhi-authenticationToken');
      sessionStorage.removeItem('jhi-authenticationToken');
      useStore().value.commit('logout');
      useRouter()
        .router.push('/')
        .catch(() => {
          context.emit('reloadContent');
        });
    }

    return {
      user,
      ...useGetters(['authenticated', 'account']),
      logout,
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

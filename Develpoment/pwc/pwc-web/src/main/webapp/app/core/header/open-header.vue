<template>
  <header>
    <b-row class="align-items-center font-weight-light mr-0 pt-md-1 pb-md-1 pr-md-4 pl-md-4">
      <b-col class="col-12 col-md-6 text-center text-md-left">
        <router-link to="/" class="navbar-brand">
          <img src="../../../content/images/custom/open_logo.png" height="72" class="d-inline-block align-top" alt="" />
        </router-link>
      </b-col>
      <b-col class="col-12 col-md-6 pb-md-0 pb-2 d-flex justify-content-end align-items-center align-items-sm-end bd-highlight">
        <div class="d-none-block d-sm-none d-flex flex-grow-1">
          <b-button v-b-toggle.my-sidebar class="ml-4">
            <font-awesome-icon icon="bars"></font-awesome-icon>
          </b-button>
        </div>
        <router-link to="/" class="btn-link text-info">回首頁</router-link>｜
        <router-link to="/service/pwc0104" class="btn-link text-info">網站導覽</router-link>｜
        <div class="d-inline font-switcher">
          <button
            class="btn-font-size small"
            :class="fontSizeClassRef.endsWith('small') ? 'active' : ''"
            @click="changeFontSizeClass('font-small')"
          >
            字
          </button>
          <button
            class="btn-font-size medium"
            :class="fontSizeClassRef.endsWith('medium') ? 'active' : ''"
            @click="changeFontSizeClass('font-medium')"
          >
            字
          </button>
          <button
            class="btn-font-size large"
            :class="fontSizeClassRef.endsWith('large') ? 'active' : ''"
            @click="changeFontSizeClass('font-large')"
          >
            字
          </button>
        </div>
      </b-col>
    </b-row>
    <b-row class="bg-info align-items-center justify-content-end ml-4 ml-sm-0 mr-4 mr-sm-0 pr-md-4">
      <div class="d-none d-sm-block">
        <router-link to="/login" class="btn-link text-white">工程雲端系統登入</router-link>｜
        <router-link to="/service/pwc0101" class="btn-link text-white">帳號註冊</router-link>
      </div>
      <div class="p-md-2 flex-grow-1 flex-sm-grow-0">
        <b-input-group>
          <template #append>
            <b-input-group-text class="bg-info text-white btn" @click.stop="changePage">
              <b-icon icon="search" v-b-tooltip.hover.bottom="'提供公告事項、下載專區內容、附件檔案名稱搜尋'"></b-icon>
            </b-input-group-text>
          </template>
          <b-form-input v-model.trim="pwcSearchStrInject.data" delay></b-form-input>
        </b-input-group>
      </div>
    </b-row>
  </header>
</template>

<script lang="ts">
import AccountService from '@/account/account.service';
import OpenMenu from '@/core/menu/open-menu.vue';
import { useGetters, useRouter, useStore } from '@u3u/vue-hooks';
import { computed, inject, ref } from '@vue/composition-api';
import { 
  BIcon,
  BButton, 
  BFormInput, 
  BInputGroup,
  BCol,
  BRow, 
  VBToggle,
  BInputGroupText
} from 'bootstrap-vue';


export default {
  name: 'AppHeader',
  components: {
    BIcon,
    BInputGroupText,
    BButton, 
    BFormInput, 
    BInputGroup,
    BCol,
    BRow, 
  },
  directives: {
    'b-toggle': VBToggle,
  },
  setup(props, context) {
    const accountService = inject<() => AccountService>('accountService')();
    const user = computed(() => accountService.user);
    const fontSizeClassRef = ref('font-medium');
    const pwcSearchStrInject = inject('pwcSearchStr');
    const doPWCSearchInject = inject('doPWCSearch');

    function changeFontSizeClass(fontSizeClass: string) {
      fontSizeClassRef.value = fontSizeClass;
      context.emit('changeFontSizeClass', fontSizeClassRef.value);
    }

    function logout() {
      localStorage.removeItem('jhi-authenticationToken');
      sessionStorage.removeItem('jhi-authenticationToken');
      useStore().value.commit('logout');
      useRouter().router.push('/login');
    }

    const changePage = () => {
      doPWCSearchInject.data = true;
      if (!_includes(window.location.pathname, 'pwc0105')) useRouter().router.push({ name: 'pwc0105'});
    };

    return {
      changePage,
      user,
      fontSizeClassRef,
      ...useGetters(['authenticated', 'account']),
      changeFontSizeClass,
      logout,
      pwcSearchStrInject,
    };
  },
};
</script>
<style lang="scss" scoped>
.font-switcher {
  > button {
    width: 32px;
    height: 32px;
    color: var(--main-color);
    border: 1px solid var(--main-color);
    border-radius: 10rem;
    background: #fff;
    font-weight: bold;
    text-align: center;
    &.active {
      background: var(--main-color);
      color: #fff;
    }
    &:hover {
      cursor: pointer;
    }
    &:not(.active):hover {
      background: rgba(26, 164, 183, 0.35);
      color: #fff;
    }
    &.small {
      font-size: 12px;
    }
    &.medium {
      font-size: 15px;
    }
    &.large {
      font-size: 18px;
    }
  }
}
</style>

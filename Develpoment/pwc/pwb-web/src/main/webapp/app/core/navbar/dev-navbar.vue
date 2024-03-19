<template>
  <div class="sticky-top">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark pb-1">
      <router-link to="/" class="navbar-brand">
        <img src="../../../content/images/pwc.svg" width="30" height="30" class="d-inline-block align-top" alt="pwc" />
      </router-link>

      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <dev-app-menu></dev-app-menu>
        <div class="w-100 text-right">
          <b-dropdown right size="lg" variant="link" toggle-class="text-decoration-none" no-caret>
            <template #button-content>
              <span class="text-light" :title="'USER'">👤</span>
            </template>
            <b-dropdown-item-button @click="logout">Logout</b-dropdown-item-button>
          </b-dropdown>
        </div>
      </div>
    </nav>
    <div class="m-0" style="height: 0.25rem; background-color: goldenrod">
      <div style="width: 25%; height: 0.25rem; background-color: navy"></div>
    </div>
    <app-breadcrumb></app-breadcrumb>
  </div>
</template>

<script lang="ts">
import DevAppMenu from '@/core/menu/dev-menu.vue';
import AppBreadcrumb from '@/core/menu/breadcrumb.vue';

export default {
  name: 'DevNavbar',
  components: { DevAppMenu, AppBreadcrumb },
  setup() {
    function logout() {
      // localStorage.removeItem('jhi-authenticationToken');
      // sessionStorage.removeItem('jhi-authenticationToken');
      // useStore().value.commit('logout');
      // useRouter().router.push('/login');
    }

    return {
      logout,
    };
  },
};
</script>

<style scoped>
@media screen and (-ms-high-contrast: active), (-ms-high-contrast: none) {
  img.logo {
    /** FIXME */
    background-color: #ff0000;
  }
}
</style>

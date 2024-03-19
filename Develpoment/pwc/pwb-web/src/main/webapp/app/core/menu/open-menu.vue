<template>
  <div class="p-0">
    <b-list-group flush>
      <b-list-group-item button class="h4 m-0 text-center py-2 button-border" @click="login()">請登入查看功能</b-list-group-item>
      <template v-for="menu in data">
        <router-link :key="menu.id" :to="{ path: menu.path, params: { isNotKeepAlive: true } }" custom v-slot="{ route }">
          <b-list-group-item
            button
            class="h5 m-0"
            :class="currentFullPath['fullPath'] !== '/' && menu.path.startsWith(currentFullPath['fullPath']) ? 'current-item' : ''"
            @click="navigate(route)"
          >
            {{ menu.meta.label }}
          </b-list-group-item>
        </router-link>
      </template>
    </b-list-group>
  </div>
</template>
<script lang="ts">
import MenuService from '@/core/menu/menu-service';
import { inject, ref } from '@vue/composition-api';
import { useRouter, useStore } from '@u3u/vue-hooks';

export default {
  name: 'AppMenu',
  setup(props, context) {
    const menuService: MenuService = inject<MenuService>('menuService');
    const { data } = menuService.retrieveOpenMenu();

    function navigate(route) {
      useStore().value.commit('clearKeepAlivePages');
      useRouter()
        .router.push(route.path)
        .catch(() => {
          context.emit('reloadContent');
        });
    }

    function login() {
      useRouter()
        .router.push({ path: '/login' })
        .catch(() => {
          context.emit('reloadContent');
        });
    }
    const currentFullPath = ref(useRouter().route);

    return { data, navigate, currentFullPath, login };
  },
};
</script>
<style lang="scss" scoped>
.card-header {
  background-color: white;
  text-align: center;
}
.list-group {
  .list-group-item {
    &:hover {
      color: #1aa4b7;
    }
    &.current-item {
      background: #1aa4b7;
      color: white;
    }
  }
}
</style>

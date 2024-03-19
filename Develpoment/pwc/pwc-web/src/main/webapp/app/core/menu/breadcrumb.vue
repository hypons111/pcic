<template>
  <div>
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb p-1">
        <li
          class="breadcrumb-item d-inline-block align-middle"
          :class="breadcrumb.class"
          v-for="(breadcrumb, index) in breadcrumbs"
          :key="index">
          <font-awesome-icon v-if="breadcrumb.icon" :icon="breadcrumb.icon"></font-awesome-icon>
          <font-awesome-icon v-else icon="tasks"></font-awesome-icon>
          <router-link v-if="breadcrumb.path" :to="breadcrumb.path" class="text-secondary">
            {{ breadcrumb.label }}
            <code v-if="breadcrumb.functionId" class="text-secondary">({{ breadcrumb.functionId }})</code>
          </router-link>
          <span v-else class="text-secondary">{{ breadcrumb.label }}</span>
        </li>
      </ol>
    </nav>
  </div>
</template>

<script lang="ts">
import MenuService from './menu-service';
import { inject, reactive, watch, computed } from '@vue/composition-api';
import ExpandedMenu from '@/core/menu/expanded-menu';
import { flattenAndTransformMenus } from '@/core/menu/utils';
import { useRouter, useStore } from '@u3u/vue-hooks';

export default {
  name: 'AppBreadcrumb',
  setup() {
    const isAuthed = computed(() => useStore().value.getters.authenticated );
    const menuService: MenuService = inject<MenuService>('menuService');
    const useNewMenuInject = inject('useNewMenu');
    const { data: openData } = menuService.retrieveOpenMenu();
    const { data: menuData } = useNewMenuInject.data ? menuService.retrieveAppMenu_() : menuService.retrieveAppMenu();
    const { route } = useRouter();
    const breadcrumbs = reactive<ExpandedMenu[]>([]);
    const openMenus: ExpandedMenu[] = [];
    const menus: ExpandedMenu[] = [];

    function addBreadcrumb(id: number, breadcrumbsRef: ExpandedMenu[]) {
      const breadcrumb: ExpandedMenu = isAuthed.value
        ? menus.find(menu => menu.id === id)
        : openMenus.find(openMenu => openMenu.id === id);
      if (breadcrumb) {
        if (breadcrumb.parentId && breadcrumb.parentId !== 0) addBreadcrumb(breadcrumb.parentId, breadcrumbsRef);
        breadcrumbsRef.push(breadcrumb);
      }
    }
    
    watch(route, to => {
      const id: number = to.meta.functionId 
        ? (isAuthed.value
          ? menus.find(menu => menu.functionId === to.meta.functionId)?.id
          : openMenus.find(openMenu => openMenu.functionId === to.meta.functionId)?.id)
        : null;
      breadcrumbs.splice(0, breadcrumbs.length);
      breadcrumbs.push({
        id: 0,
        parentId: null,
        label: '工程雲端' + (isAuthed.value ? '系統' : '服務網'),
        path: '/',
        icon: 'home',
      });
      addBreadcrumb(id, breadcrumbs);
      breadcrumbs[breadcrumbs.length - 1].class = 'active';
    });

    watch(
      openData, 
      value => { openMenus.push(...flattenAndTransformMenus(value));},
      { immediate: false }
    );

    watch(
      menuData, 
      value => { menus.push(...flattenAndTransformMenus(value));},
      { immediate: false }
    );

    return { breadcrumbs };
  },
};
</script>

<style scoped>
.breadcrumb {
  background-color: #f5f5f5;
}
</style>

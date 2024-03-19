<template>
  <div>
    <ul class="navbar-nav mr-auto">
      <template v-for="(lifeCycle, idx) in data">
        <li class="nav-item dropdown border-bottom mt-3" :key="idx">
          <span>{{ lifeCycle.meta.label }}</span>
          <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown pl-3" v-for="(menu, index) in lifeCycle.children" :key="index">
              <!-- item for bid -->
              <a
                role="button"
                data-toggle="false"
                aria-haspopup="false"
                aria-expanded="false"
                class="nav-link dropdown-toggle"
                @click.stop="onNavItenClick(menu)"
                v-if="checkIsBidMenu(menu)"
              >
                <font-awesome-icon :icon="menu.meta.icon"></font-awesome-icon>
                {{ menu.meta.label }}
              </a>
              <!-- others -->
              <a
                id="conditionalDropdown"
                role="button"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="true"
                class="nav-link dropdown-toggle"
                :class="{ 'must-padding': checkIsSpecialBidItem(menu) }"
                v-else
              >
                <font-awesome-icon :icon="menu.meta.icon"></font-awesome-icon>
                {{ menu.meta.label }}
              </a>
              <div class="dropdown-menu" aria-labelledby="conditionalDropdown">
                <div :class="checkDropDownMenuLayout(menu)">
                  <template v-for="(menuItem, menuItemIndex) in menu.children">
                    <div v-if="menuItem.meta.directory" :key="menuItemIndex">
                      <h6 class="text-center text-muted mb-0" >
                      <!-- v-if="isChildrenExist(menuItem)"-->
                        <span>{{ menuItem.meta.label }}</span>
                      </h6>
                      <div class="dropdown-divider"></div>
                      <span v-for="(subMenu, subMenuIndex) in menuItem.children" :key="subMenuIndex">
                        <!-- v-if="accountService.userAuthorities.includes(subMenu.meta.functionId)" -->
                        <router-link
                          :to="{ path: subMenu.path, params: { isNotKeepAlive: true } }"
                          class="dropdown-item"
                          custom
                          v-slot="{ route }"
                        >
                          <!-- v-if="accountService.userAuthorities.includes(subMenu.meta.functionId)" -->
                          <button @click="navigate(route)" role="link">{{ subMenu.meta.label }}</button>
                        </router-link>
                      </span>
                    </div>
                    <div v-else :key="menuItemIndex">
                      <!-- v-if="accountService.userAuthorities.includes(menuItem.meta.functionId)" -->
                      <router-link
                        :to="{ path: menuItem.path, params: { isNotKeepAlive: true } }"
                        class="dropdown-item"
                        custom
                        v-slot="{ route }"
                      >
                        <!-- v-if="accountService.userAuthorities.includes(menuItem.meta.functionId)" -->
                        <button @click="navigate(route)" role="link">{{ menuItem.meta.label }}</button>
                      </router-link>
                    </div>
                  </template>
                </div>
              </div>
            </li>
          </ul>
        </li>
      </template>
    </ul>
  </div>
</template>
<script lang="ts">
import { inject, watch } from '@vue/composition-api';
import MenuService from '@/core/menu/menu-service';
import AccountService from '@/account/account.service';
import { Menu } from '@/core/menu/menu';
import { useRouter, useStore } from '@u3u/vue-hooks';
import { keys as _keys, isObject as _isObject, includes as _includes, findIndex as _findIndex, take as _take } from 'lodash';

export default {
  name: 'AppMenu',
  setup(props, context) {
    const menuService: MenuService = inject<MenuService>('menuService');
    const accountService = inject<() => AccountService>('accountService')();
    const useNewMenuInject = inject('useNewMenu');
    const { data } = useNewMenuInject.data ? menuService.retrieveAppMenu_() : menuService.retrieveAppMenu();
    const hasChildren = new Map<number, boolean>();
    function checkChildrenExist(menus: Menu[], parent: Menu) {
      let last = false;
      menus.forEach(m => {
        if (m.meta.directory && m.children.length) {
          checkChildrenExist(m.children, m);
        } else {
          last = true;
        }
      });
      if (parent && last) {
        const found = parent.children.some(c => {
          return accountService.userAuthorities.includes(c.meta.functionId);
        });
        hasChildren.set(parent.id, found);
      }
    }

    function isChildrenExist(menu: Menu) {
      if (hasChildren.has(menu.id)) {
        return hasChildren.get(menu.id);
      } else if (isDeepChildrenExist(menu)) {
        return true;
      } else {
        return menu.children.some(m => {
          if (m.children && m.children.length > 0) {
            m.children.some(c => {
              return hasChildren.get(c.id);
            });
          }
          return hasChildren.get(m.id);
        });
      }
    }

    function isDeepChildrenExist(menu: Menu) {
      if (menu.children && menu.children.length > 0) {
        return (
          hasChildren.get(menu.id) ||
          menu.children.some(m => {
            return isDeepChildrenExist(m);
          })
        );
      }
      return hasChildren.get(menu.id);
    }

    function navigate(route) {
      useStore().value.commit('clearKeepAlivePages');
      useRouter()
        .router.push(route.path)
        .catch(() => {
          context.emit('reloadContent');
        });
    }

    function onNavItenClick(item) {
      if (useNewMenuInject.data) {
        const fIndex = _findIndex(data.value, v => {
          return v.meta.functionId === item.meta.parentId;
        });
        if (fIndex === -1) return;
        data.value[fIndex].children.forEach(c => {
          if (!checkIsSpecialBidItem(c)) return;
          c.meta.show = !c.meta.show;
        });
      } else {
        data.value.forEach(d => {
          if (d.id !== 6) return;
          d.children.forEach(child => {
            if (child.id === 81000) child.meta.show = !child.meta.show;
            else if (child.id === 82000) child.meta.show = !child.meta.show;
            else if (child.id === 83000) child.meta.show = !child.meta.show;
            else if (child.id === 84000) child.meta.show = !child.meta.show;
            else if (child.id === 85000) child.meta.show = !child.meta.show;
            else if (child.id === 86000) child.meta.show = !child.meta.show;
            else if (child.id === 66620) child.meta.show = !child.meta.show;
          });
        });
      }
      if (window.location.pathname.length > 9 && window.location.pathname !== '/pwc-web/bidAaa000') {
        navigate({
          fullPath: '/bidAaa000',
          meta: { functionId: 'bidAaa000' },
          path: '/bidAaa000',
          params: { isNotKeepAlive: true },
        });
      }
    }

    function checkIsBidMenu(menu) {
      if (!_isObject(menu)) return false;
      return useNewMenuInject.data ? menu.meta.layer === 2 && menu.meta.functionId === 'bid' : menu.id === 80000;
    }

    function checkIsSpecialBidItem(menu) {
      if (!_isObject(menu)) return false;
      if (useNewMenuInject.data) return menu.meta.layer === 3 && menu.meta.parentId === 'bid';
      else {
        if (menu.id === 81000) return true;
        if (menu.id === 82000) return true;
        if (menu.id === 83000) return true;
        if (menu.id === 84000) return true;
        if (menu.id === 85000) return true;
        if (menu.id === 86000) return true;
        if (menu.id === 66620) return true;
      }
    }

    function checkDropDownMenuLayout(menu) {
      let str = 'd-inline',
        valid = false;
      if (!_isObject(menu)) return str;
      menu.children.forEach(c => {
        const keys = _keys(c);
        if (!_includes(keys, 'children')) return;
        valid = valid || c.children.length > 0;
      });
      if (valid) str = 'd-flex';
      return str;
    }

    watch(data, value => {
      checkChildrenExist(data.value, null);
    });

    return {
      isChildrenExist,
      checkIsBidMenu,
      checkIsSpecialBidItem,
      checkDropDownMenuLayout,
      data,
      accountService,
      navigate,
      onNavItenClick,
    };
  },
};
</script>
<style lang="scss" scoped>
.nav-item,
.nav-link,
.dropdown-item {
  font-size: large;
}

.navbar-nav > li > .nav-link {
  border-bottom: solid 0.5px #f0f0f0;
}
.dropdown-menu {
  .dropdown-item {
    &:hover {
      color: #1aa4b7;
      background-color: white;
    }
  }
}

.navbar-nav > .nav-item:hover > a {
  color: #1aa4b7;
  border-bottom: solid 0.5px #1aa4b7;
}
</style>

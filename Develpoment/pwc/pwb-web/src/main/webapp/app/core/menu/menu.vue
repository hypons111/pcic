<template>
  <div>
    <template v-for="lifeCycle in data">
      <ul class="navbar-nav mr-auto" :key="lifeCycle.index">
        <li class="nav-item dropdown pt-1" v-for="(menu, index) in lifeCycle.children" :key="index">
          <a
            v-if="isChildrenExist(menu)"
            class="nav-link dropdown-toggle button-border"
            href="#"
            id="conditionalDropdown"
            role="button"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            <font-awesome-icon :icon="menu.meta.icon"></font-awesome-icon>
            {{ menu.meta.label }}
          </a>
          <div class="dropdown-menu" aria-labelledby="conditionalDropdown">
            <div :class="menu.children.some(child => child.children) ? 'd-flex' : 'd-inline'">
              <template v-for="(menuItem, menuItemIndex) in menu.children">
                <div v-if="menuItem.meta.directory" :key="menuItemIndex">
                  <h6 class="text-center text-muted mb-0" v-if="isChildrenExist(menuItem)">
                    <!--<font-awesome-icon :icon="menuItem.meta.icon"></font-awesome-icon>-->
                    <span>{{ menuItem.meta.label }}</span>
                  </h6>
                  <div class="dropdown-divider"></div>
                  <span v-for="(subMenu, subMenuIndex) in menuItem.children" :key="subMenuIndex">
                    <router-link
                      :to="{ path: subMenu.path, params: { isNotKeepAlive: true } }"
                      class="dropdown-item"
                      v-if="accountService.userAuthorities.includes(subMenu.meta.functionId)"
                      custom
                      v-slot="{ route }"
                    >
                      <!--<font-awesome-icon icon="th-large"></font-awesome-icon>-->
                      <button @click="navigate(route)" role="link">{{ subMenu.meta.label }}</button>
                    </router-link>
                  </span>
                </div>
                <div v-else :key="menuItemIndex">
                  <router-link
                    :to="{ path: menuItem.path, params: { isNotKeepAlive: true } }"
                    class="dropdown-item"
                    v-if="accountService.userAuthorities.includes(menuItem.meta.functionId)"
                    custom
                    v-slot="{ route }"
                  >
                    <!--<font-awesome-icon icon="th-large"></font-awesome-icon>-->
                    <button @click="navigate(route)" role="link">{{ menuItem.meta.label }}</button>
                  </router-link>
                </div>
              </template>
            </div>
          </div>
        </li>
      </ul>
    </template>
  </div>
</template>
<script lang="ts">
import { inject, watch } from '@vue/composition-api';
import MenuService from '@/core/menu/menu-service';
import AccountService from '@/account/account.service';
import { Menu } from '@/core/menu/menu';
import { useRouter, useStore } from '@u3u/vue-hooks';

export default {
  name: 'AppMenu',
  setup(props, context) {
    const menuService: MenuService = inject<MenuService>('menuService');
    const accountService = inject<() => AccountService>('accountService')();
    const { data } = menuService.retrieveAppMenu();
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

    watch(data, value => {
      checkChildrenExist(data.value, null);
    });
    return { isChildrenExist, data, accountService, navigate };
  },
};
</script>
<style lang="scss" scoped>
.nav-item,
.nav-link,
.dropdown-item {
  font-size: large;
}

.card-header {
  background-color: white;
  text-align: center;
}

.button-border {
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

.navbar-nav > li:hover a {
  color: #1aa4b7;
  border-bottom: solid 0.5px #1aa4b7;
}
</style>

<template>
  <div>
    <ul class="navbar-nav mr-auto">
      <li class="nav-item dropdown" v-for="(menu, index) in data" :key="index">
        <a
          v-if="isChildrenExist(menu)"
          class="nav-link dropdown-toggle"
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
                  {{ menuItem.meta.label }}
                </h6>
                <div class="dropdown-divider"></div>
                <span v-for="(subMenu, subMenuIndex) in menuItem.children" :key="subMenuIndex">
                  <router-link :to="subMenu.path" class="dropdown-item">
                    <!--<font-awesome-icon icon="th-large"></font-awesome-icon>-->
                    {{ subMenu.meta.label }}
                  </router-link>
                </span>
              </div>
              <div v-else :key="menuItemIndex">
                <router-link :to="menuItem.path" class="dropdown-item">
                  <!--<font-awesome-icon icon="th-large"></font-awesome-icon>-->
                  {{ menuItem.meta.label }}
                </router-link>
              </div>
            </template>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>
<script lang="ts">
import { inject, watch } from '@vue/composition-api';
import MenuService from '@/core/menu/menu-service';
import AccountService from '@/account/account.service';
import { Menu } from '@/core/menu/menu';

export default {
  name: 'DevAppMenu',
  setup() {
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
          return true;
        });
        hasChildren.set(parent.id, found);
      }
    }

    function isChildrenExist(menu: Menu) {
      if (hasChildren.has(menu.id)) {
        return hasChildren.get(menu.id);
      } else {
        return menu.children.some(m => {
          return hasChildren.get(m.id);
        });
      }
    }
    watch(data, value => {
      checkChildrenExist(data.value, null);
    });
    return { isChildrenExist, data, accountService };
  },
};
</script>

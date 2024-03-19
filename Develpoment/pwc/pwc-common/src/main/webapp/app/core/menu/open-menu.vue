<template>
  <div>
    <div class="h4 m-0 text-center py-2 button-border">公開資訊</div>
    <ul class="navbar-nav mr-auto">
      <li class="nav-item dropdown pt-1" v-for="(menu, index) in data" :key="index">
        <a
          v-if="isChildrenExist(menu)"
          class="nav-link dropdown-toggle button-border"
          :class="currentFullPath['fullPath'] !== '/' && menu.path.startsWith(currentFullPath['fullPath']) ? 'current-item' : ''"
          href="#"
          id="conditionalDropdown"
          role="button"
          data-toggle="dropdown"
          aria-haspopup="true"
          aria-expanded="false"
        >
          <!-- <font-awesome-icon :icon="menu.meta.icon"></font-awesome-icon> -->
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
                  <router-link
                    :to="{ path: subMenu.path, params: { isNotKeepAlive: true } }"
                    class="dropdown-item"
                    custom
                    v-slot="{ route }"
                  >
                    <!--<font-awesome-icon icon="th-large"></font-awesome-icon>-->
                    <button @click="navigate(route)" role="link">{{ subMenu.meta.label }}</button>
                  </router-link>
                  <!-- v-if="accountService.userAuthorities.includes(subMenu.meta.functionId)" -->
                </span>
              </div>
              <div v-else :key="menuItemIndex">
                <router-link
                  :to="{ path: menuItem.path, params: { isNotKeepAlive: true } }"
                  class="dropdown-item"
                  custom
                  v-slot="{ route }"
                >
                  <!-- v-if="accountService.userAuthorities.includes(menuItem.meta.functionId)" -->
                  <!--<font-awesome-icon icon="th-large"></font-awesome-icon>-->
                  <button @click="navigate(route)" role="link">{{ menuItem.meta.label }}</button>
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
import MenuService from '@/core/menu/menu-service';
import { inject, ref, watch } from '@vue/composition-api';
import { Menu } from '@/core/menu/menu';
import { useRouter, useStore } from '@u3u/vue-hooks';

export default {
  name: 'AppOpenMenu',
  setup(props, context) {
    const menuService: MenuService = inject<MenuService>('menuService');
    const { data } = menuService.retrieveOpenMenu();
    // console.log('data',data)
    //判斷該menu是否有children
    const hasChildren = new Map<number, boolean>();

    function navigate(route) {
      useStore().value.commit('clearKeepAlivePages');
      useRouter()
        .router.push(route.path)
        .catch(() => {
          context.emit('reloadContent');
        });
    }
    //
    function checkChildrenExist(menus: Menu[], parent: Menu) {
      let last = false;
      //將menu陣列遍歷，只有第一層沒有parent，其他層都有
      menus.forEach(m => {
        //如果該menu是directory且他的children有值
        if (m.meta.directory && m.children && m.children.length) {
          //遞迴判斷他的sub-menu
          checkChildrenExist(m.children, m);
          //如果沒有children 他就是 最後的menu
        } else {
          last = true;
        }
      });
      //如果該層是最後一層的menu
      if (parent && last) {
        //下面本來是要去accountService抓functionId,但因為這個menu不用登入，也不會去抓了
        // const found = parent.children.some(c => {
        //   return accountService.userAuthorities.includes(c.meta.functionId);
        // });
        // hasChildren.set(parent.id, found);
        //把該parent的id 與 是否有子menu 存進hasChildren的map裡
        hasChildren.set(parent.id, true);
      }
    }

    function isChildrenExist(menu: Menu) {
      if (hasChildren.has(menu.id)) {
        return hasChildren.get(menu.id);
      } else if (isDeepChildrenExist(menu)) {
        return true;
      } else if (menu.children) {
        return menu.children.some(m => {
          if (m.children && m.children.length > 0) {
            m.children.some(c => {
              return hasChildren.get(c.id);
            });
          }
          return hasChildren.get(m.id);
        });
      } else {
        return false;
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

    const currentFullPath = ref(useRouter().route);

    watch(data, value => {
      checkChildrenExist(data.value, null);
    });
    return { data, navigate, currentFullPath, isChildrenExist };
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

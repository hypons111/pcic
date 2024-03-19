<template>
  <div>
    <nav>
      <button><img src="../../../content/images/custom/ic-minus.svg" alt="全部收合" />全部收合</button>
      <ul>
        <li>
          <a href="#" class="nav-item active">計畫規劃、設計</a>
          <ul>
            <li><a href="#" class="nav-item dropdown">災後復建資訊模組</a></li>
          </ul>
          <ul>
            <li>
              <a href="#" class="nav-item dropdown active">災後復建資訊模組</a>
              <ul>
                <li><a href="#" class="nav-item sub active">常用功能</a></li>
                <li><a href="#" class="nav-item sub">公告事項</a></li>
                <li><a href="#" class="nav-item sub">災後復建審議</a></li>
                <li><a href="#" class="nav-item sub">查詢統計</a></li>
              </ul>
            </li>
          </ul>
        </li>
        <li><a href="#" class="nav-item">招標決標</a></li>
        <li><a href="#" class="nav-item">履約驗收</a></li>
        <li><a href="#" class="nav-item">維護營運</a></li>
        <li><a href="#" class="nav-item">其他業務</a></li>
      </ul>
    </nav>
  </div>
</template>

<script lang="ts">
import AppMenu from '@/core/menu/menu.vue';
import AppBreadcrumb from '@/core/menu/breadcrumb.vue';

export default {
  name: 'Navbar',
  components: { AppMenu, AppBreadcrumb },
  setup() {},
};
</script>

<style scoped>
@media screen and (-ms-high-contrast: active), (-ms-high-contrast: none) {
  img.logo {
    /** FIXME */
    background-color: #ff0000;
  }
}

nav {
  background-color: #fff;
  box-shadow: 0 0 8px -4px rgb(0 0 0 / 35%);
  white-space: nowrap;
  padding-bottom: 3rem;
}
nav > button {
  width: 100%;
  background: #fff;
  border: 0;
  font-size: 17px;
  padding: 0.75rem;
  text-align: left;
  box-shadow: 0 2px 0 rgb(0 0 0 / 15%);
  color: var(--medium-grey);
  display: flex;
  align-items: center;
}

nav ul {
  margin-bottom: 1rem;
  display: flex;
  flex-direction: column;
  list-style: none;
  padding-left: 0;
}

nav > ul {
  padding-left: 1.5rem !important;
}

nav li {
  display: contents;
}

nav .nav-item {
  font-size: 17px;
  line-height: 1.5rem;
  padding: 0.5rem 0.75rem;
  color: #4a4a4a;
}

nav .nav-item:not(.sub):not(.dropdown) {
  border-bottom: 1px solid rgba(0, 0, 0, 0.15);
}

nav .nav-item:not(.sub):not(.dropdown).active,
nav .nav-item:not(.sub):not(.dropdown):hover {
  border-color: #1aa4b7;
  color: #1aa4b7;
}

nav .nav-item.dropdown,
nav .nav-item.sub {
  font-size: 16px;
  position: relative;
  padding-right: 32px;
}

nav .nav-item.sub {
  background-color: rgb(26 164 183 / 12%);
  padding-left: 1.75rem;
}

nav .nav-item.sub.active,
nav .nav-item.sub:hover {
  color: #1aa4b7;
}

nav .nav-item.dropdown::after {
  margin-left: 12px;
  position: absolute;
}

nav .nav-item.dropdown.active,
.nav-item.dropdown:not(.active):hover {
  background-color: #1aa4b7;
  color: #fff;
}

nav .nav-item.dropdown.active::after {
  content: '>';
  transform: rotate(90deg);
}

nav .nav-item.dropdown:not(.active)::after {
  content: '<';
}
</style>

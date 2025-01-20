<script setup lang="ts">
import { ref } from "vue";

interface MenuLink {
  name: string;
  path?: string;
  subItems: { name: string; path: string }[];
}
const Links: MenuLink[] = [
  { name: "首页", path: "/", subItems: [] },
  {
    name: "文章",
    subItems: [
      { name: "分类", path: "/blog/category" },
      { name: "归档", path: "/blog/archive" },
      { name: "标签", path: "/blog/tag" },
    ],
  },
  {
    name: "社交",
    subItems: [
      { name: "友链", path: "/social/friends" },
      { name: "留言", path: "/social/guestbook" },
    ],
  },
  {
    name: "个人",
    subItems: [
      { name: "关于", path: "/personal/about" },
      { name: "随笔", path: "/personal/essays" },
      { name: "工具", path: "/personal/tools" },
    ],
  },
];

const open = ref(false);
const hoveredLink = ref<MenuLink | null>(null);

function toggleMenu() {
  open.value = !open.value;
}

function handleMouseEnter(link: MenuLink) {
  hoveredLink.value = link;
}

function handleMouseLeave() {
  hoveredLink.value = null;
}
</script>

<template>
    <!-- 导航栏 -->
    <div class="bg-gray-900 text-gray-100 py-3.5 px-6 md:flex justify-between items-center">
      <!-- Logo 和标题 -->
      <div class="flex items-center">
        <span class="text-green-500 text-xl mr-1">
          <svg class="h-6 w-6 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
          </svg>
        </span>
        <div class="tabs">
          <router-link to="/" class="btn btn-ghost text-xl ">
            daisyUI
          </router-link>
        </div>
      </div>

      <!-- 移动端菜单按钮 -->
      <span @click="toggleMenu" class="absolute md:hidden right-6 top-1.5 cursor-pointer text-4xl">
        <svg v-if="!open" class="swap-off fill-current" xmlns="http://www.w3.org/2000/svg" width="32" height="32"
          viewBox="0 0 512 512">
          <path d="M64,384H448V341.33H64Zm0-106.67H448V234.67H64ZM64,128v42.67H448V128Z" />
        </svg>

        <svg v-else class="swap-on fill-current" xmlns="http://www.w3.org/2000/svg" width="32" height="32"
          viewBox="0 0 512 512">
          <polygon
            points="400 145.49 366.51 112 256 222.51 145.49 112 112 145.49 222.51 256 112 366.51 145.49 400 256 289.49 366.51 400 400 366.51 289.49 256 400 145.49" />
        </svg>
      </span>

      <!-- 导航菜单 -->
      <ul
          :class="[open ? 'left-0' : 'left-[-100%]']"
          class="md:flex md:items-center md:px-0 px-10 md:pb-0 pb-10 md:static absolute
          bg-gray-900 md:w-auto w-full top-14 duration-700 ease-in"
      >
        <!-- 菜单项 -->
        <li v-for="item in Links" :key="item.name"
          class="md:mx-4 md:my-0 my-6 relative
          md:hover:border-b-2 hover:border-blue-500 transition-all duration-300"
            @click="toggleSubMenu(item)"
          @mouseenter="handleMouseEnter(item)" @mouseleave="handleMouseLeave">
          <a class="text-xl hover:text-green-500" :href="item.path">{{
            item.name
          }}</a>
          <!-- 子菜单 -->
          <ul
              v-show="(hoveredLink && hoveredLink.name === item.name) || (activeSubMenu && activeSubMenu.name === item.name)"
              class="bg-gray-900 text-white py-2 w-48 left-0 top-full md:absolute mt-0.5"
          >
            <li v-for="subItem in item.subItems" :key="subItem.name">
              <a :href="subItem.path" class="block px-4 py-2 hover:bg-gray-700">{{ subItem.name }}</a>
            </li>
          </ul>
        </li>
        <!-- 按钮 -->
        <button class="bg-green-600 hover:bg-green-300 font-sm text-white rounded py-1.5 px-4">
          Get Started
        </button>
      </ul>
    </div>
</template>

<style scoped>
/* 自定义样式 */
</style>

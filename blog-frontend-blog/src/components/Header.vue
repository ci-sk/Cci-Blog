<script setup lang="ts">
import { ref } from "vue";

interface MenuLink {
  name: string;
  path: string;
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
  { name: "社交", subItems: [
      {name:"友链",path:"/social/friends"},
      {name:"留言",path:"/social/guestbook"},
    ]
  },
  { name: "个人", subItems: [
      {name:"关于",path:"/personal/about"},
      {name:"随笔",path:"/personal/essays"},
      {name:"工具",path:"/personal/tools"}
    ] },
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
  <div class="bg-green-400">
    <!-- 导航栏 -->
    <div class="bg-gray-900 text-gray-100 py-3.5 px-6 md:flex justify-between items-center">
      <!-- Logo 和标题 -->
      <div class="flex items-center">
        <span class="text-green-500 text-xl mr-1">
          <svg class="h-6 w-6 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
          </svg>
        </span>
        <h1 class="text-xl">Designer</h1>
      </div>

      <!-- 移动端菜单按钮 -->
      <span @click="toggleMenu" class="absolute md:hidden right-6 top-1.5 cursor-pointer text-4xl">
        <svg v-if="!open" class="h-6 w-6 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
        </svg>
        <span v-else class="text-white">×</span>
      </span>

      <!-- 导航菜单 -->
      <ul
          :class="[open ? 'left-0' : 'left-[-100%]']"
          class="md:flex md:items-center md:px-0 px-10 md:pb-0 pb-10 md:static absolute
          bg-gray-900 md:w-auto w-full top-14 duration-700 ease-in"
      >
        <!-- 菜单项 -->
        <li
            v-for="item in Links"
            :key="item.name"
            class="md:mx-4 md:my-0 my-6 relative"
            @mouseenter="handleMouseEnter(item)"
            @mouseleave="handleMouseLeave"
        >
          <a class="text-xl hover:text-green-500" :href="item.path">{{ item.name }}</a>
          <!-- 子菜单 -->
          <ul
              v-show="hoveredLink && hoveredLink.name === item.name"
              class="bg-gray-900 text-white py-2 w-48 left-0 top-full md:absolute"
          >
            <li v-for="subItem in item.subItems" :key="subItem.name">
              <router-link :to="subItem.path" class="block px-4 py-2 hover:bg-gray-700">{{ subItem.name }}</router-link>
<!--              <a class="block px-4 py-2 hover:bg-gray-700" :href="subItem.path">{{ subItem.name }}</a>-->
            </li>
          </ul>
        </li>
        <!-- 按钮 -->
        <button class="bg-green-600 hover:bg-green-300 font-sm text-white
  rounded py-1.5 px-4">
          Get Started
        </button>
      </ul>
    </div>
  </div>
</template>

<style scoped>
/* 自定义样式 */
</style>
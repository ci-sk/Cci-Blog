<script setup>
import {onMounted, ref} from "vue";
import { useRouter } from "vue-router";
import {FolderKanban, House, PencilLine, Send, UsersRound} from 'lucide-vue-next';
const router = useRouter();


const MenusLink = ref([
  { name: "首页", path: "/" ,icon:House},
  { name: "归档", path: "/blog/archive",icon:FolderKanban},
  { name: "随笔", path: "/personal/essays" ,icon:PencilLine},
  { name: "友链", path: "/social/friends" ,icon:UsersRound},
  { name: "关于", path: "/personal/about",icon:Send },
])
// 定义一个 ref 来存储当前的颜色模式
const isDarkMode = ref(false);


const hoveredItem = ref(null);
const activeSubMenu = ref(null);

function handleMouseEnter(item) {
  hoveredItem.value = item;
}

function handleMouseLeave() {
  hoveredItem.value = null;
}

function toggleSubMenu(item) {
  if(item.path){
    router.push(item.path);
    return;
  }
  if (activeSubMenu.value === item.name) {
    activeSubMenu.value = null;
  } else {
    activeSubMenu.value = item.name;
  }
}
</script>

<template>
  <div class="drawer drawer-end flex flex-col">
    <input id="my-drawer-3" type="checkbox" class="drawer-toggle" />

    <div class="drawer-content">
      <!-- Navbar -->
      <div class="navbar bg-base-400 w-full">
        <div class="mx-2 flex-1 px-2">
          <router-link to="/" class="btn btn-ghost text-xl">My Logo</router-link>
        </div>
        <!-- 桌面端 -->
        <div class="hidden flex-none md:block mr-10 font-semibold">
          <ul class="menu menu-horizontal text-lg px-1">
            <li
              v-for="item in MenusLink"
              :key="item.name"
              class="relative items-center"
            >
              <router-link :to="item.path">
                <component :is="item.icon" v-if="item.icon" />
                {{ item.name }}
              </router-link>
            </li>
          </ul>
        </div>

        <!--切换深色模式和浅色模式-->
        <label class="swap swap-rotate">
          <!-- this hidden checkbox controls the state -->
          <input type="checkbox" class="theme-controller" :value="isDarkMode?'light':'dark'" />
          <!-- sun icon -->
          <svg
            class="swap-off fill-current w-10 h-10"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
          >
            <path
              d="M5.64,17l-.71.71a1,1,0,0,0,0,1.41,1,1,0,0,0,1.41,0l.71-.71A1,1,0,0,0,5.64,17ZM5,12a1,1,0,0,0-1-1H3a1,1,0,0,0,0,2H4A1,1,0,0,0,5,12Zm7-7a1,1,0,0,0,1-1V3a1,1,0,0,0-2,0V4A1,1,0,0,0,12,5ZM5.64,7.05a1,1,0,0,0,.7.29,1,1,0,0,0,.71-.29,1,1,0,0,0,0-1.41l-.71-.71A1,1,0,0,0,4.93,6.34Zm12,.29a1,1,0,0,0,.7-.29l.71-.71a1,1,0,1,0-1.41-1.41L17,5.64a1,1,0,0,0,0,1.41A1,1,0,0,0,17.66,7.34ZM21,11H20a1,1,0,0,0,0,2h1a1,1,0,0,0,0-2Zm-9,8a1,1,0,0,0-1,1v1a1,1,0,0,0,2,0V20A1,1,0,0,0,12,19ZM18.36,17A1,1,0,0,0,17,18.36l.71.71a1,1,0,0,0,1.41,0,1,1,0,0,0,0-1.41ZM12,6.5A5.5,5.5,0,1,0,17.5,12,5.51,5.51,0,0,0,12,6.5Zm0,9A3.5,3.5,0,1,1,15.5,12,3.5,3.5,0,0,1,12,15.5Z"
            />
          </svg>
          <!-- moon icon -->
          <svg
            class="swap-on fill-current w-10 h-10"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
          >
            <path
              d="M21.64,13a1,1,0,0,0-1.05-.14,8.05,8.05,0,0,1-3.37.73A8.15,8.15,0,0,1,9.08,5.49a8.59,8.59,0,0,1,.25-2A1,1,0,0,0,8,2.36,10.14,10.14,0,1,0,22,14.05,1,1,0,0,0,21.64,13Zm-9.5,6.69A8.14,8.14,0,0,1,7.08,5.22v.27A10.15,10.15,0,0,0,17.22,15.63a9.79,9.79,0,0,0,2.1-.22A8.11,8.11,0,0,1,12.14,19.73Z"
            />
          </svg>
        </label>
        <div class="md:hidden">
          <label
            for="my-drawer-3"
            aria-label="open sidebar"
            class="btn btn-square btn-ghost"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              class="inline-block h-6 w-6 stroke-current"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M4 6h16M4 12h16M4 18h16"
              ></path>
            </svg>
          </label>
        </div>
      </div>
      <!-- Page content here -->
    </div>
      <!--    移动端-->
      <div class="drawer-side z-10">
        <label
          for="my-drawer-3"
          aria-label="close sidebar"
          class="drawer-overlay"
        ></label>
        <ul class="menu bg-primary min-h-full w-48 p-4 font-semibold text-lg">
          <li v-for="item in MenusLink" :key="item.name">
            <a @click="toggleSubMenu(item)">{{ item.name }} </a>
          </li>
        </ul>
      </div>
     </div>
</template>

<style scoped></style>

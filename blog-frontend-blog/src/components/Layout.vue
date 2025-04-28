<script setup>
import Header from './Header.vue';
import Footer from './Footer.vue';
import {ref} from "vue";
import {FolderKanban, House, PencilLine, Send, UsersRound} from "lucide-vue-next";

const MenusLink = ref([
  { name: "首页", path: "/" ,icon:House},
  { name: "归档", path: "/blog/archive",icon:FolderKanban},
  { name: "随笔", path: "/personal/essays" ,icon:PencilLine},
  { name: "友链", path: "/social/friends" ,icon:UsersRound},
  { name: "关于", path: "/personal/about",icon:Send },
])
</script>

<template>
  <div class="flex flex-col min-h-screen ">
    <!-- 头部 -->
    <Header />
    <!-- 主体 -->
    <main class="flex-grow">
      <router-view />
    </main>
    <!-- 底部 -->
    <Footer />
    <!-- 移动端底部导航栏 -->
    <div class="md:hidden sticky bottom-0 shadow-lg rounded-t-xl z-10 bg-base-200 pb-[env(safe-area-inset-bottom)]">
      <ul class="menu menu-horizontal text-xs sm:text-sm px-1 flex justify-around items-center">
        <li
            v-for="item in MenusLink"
            :key="item.name"
            class="relative items-center">
          <router-link :to="item.path" class="flex flex-col items-center space-y-1">
            <component :is="item.icon" v-if="item.icon" class="w-5 h-5 sm:w-6 sm:h-6" />
            <span class="text-xs sm:text-sm font-semibold">{{item.name}}</span>
          </router-link>
        </li>
      </ul>
    </div>

  </div>
</template>

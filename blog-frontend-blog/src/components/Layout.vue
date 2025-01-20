<script setup>
import Header from './Header.vue';
import Footer from './Footer.vue';
import Demo from "./Header/Demo.vue";

</script>

<template>
  <div class="flex flex-col min-h-screen ">
    <!-- 头部 -->
    <Header />
<!--    <Demo/>-->
    <div class="drawer-side">
      <label for="my-drawer-3" aria-label="close sidebar" class="drawer-overlay"></label>
      <ul class="menu bg-base-200 min-h-full w-48 p-4 font-semibold">
        <li v-for="item in menuItems" :key="item.name">
          <a @click="toggleSubMenu(item)">{{ item.name }}
            <!-- 下箭头标识 -->
            <span v-if="item.subItems" class="ml-2">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 inline-block" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
                </svg>
              </span>
          </a>
          <ul v-if="item.subItems && activeSubMenu === item.name" class="p-2">
            <li v-for="subItem in item.subItems" :key="subItem.name">
              <router-link :to="subItem.path">{{ subItem.name }}</router-link>
            </li>
          </ul>
        </li>
      </ul>
    </div>

    <!-- 主体 -->
    <main class="flex-grow">
      <router-view />
    </main>
    <!-- 底部 -->
    <Footer />
  </div>
</template>

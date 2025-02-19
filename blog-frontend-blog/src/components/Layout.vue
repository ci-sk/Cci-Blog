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
    <!-- 主体 -->
    <main class="flex-grow">
      <router-view />
    </main>
    <!-- 底部 -->
    <Footer />
  </div>
</template>

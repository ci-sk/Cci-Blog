<script setup>
import Header from './Header.vue';
import Footer from './Footer.vue';

</script>

<template>
  <div class="flex flex-col min-h-screen">
    <!-- 头部 -->
    <Header />
    <!-- 主体 -->
    <main class="flex-grow">
      <router-view /> <!-- 路由切换的内容会渲染在这里 -->
    </main>
    <!-- 底部 -->
    <Footer />
  </div>
</template>


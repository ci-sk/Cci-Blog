<script setup>
import { ref } from "vue";
import HotMap from "../../components/home/HotMap.vue";
import HomeTop from "../../components/home/HomeTop.vue";
import WordCloud from "../../components/home/WordCloud.vue";
</script>

<template>
  <div class="home">
    <HomeTop />
    <HotMap />
    <WordCloud />
  </div>
</template>

<style scoped>
.home {
  width: 100%;
  height: 100%;
  background-color: var(--cci-card-bg-color);
}
</style>

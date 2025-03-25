<script setup>
// import { Github, Bilibili, Cloud } from "lucide-vue-next";
import GithubIcon from "../../assets/github.svg?url";
import BilibiliIcon from "../../assets/bilibili.svg?svg";
const Infos = [
  { text: "文章", num: 12 },
  { text: "分类", num: 12 },
  { text: "标签", num: 12 },
];

const socialLinks = [
  { name: "知乎", icon: null, followers: 2879, color: "bg-blue-500" },
  {
    name: "Bilibili",
    followers: 1634,
    color: "bg-pink-500",
  },
  {
    name: "GitHub",
    followers: 765,
    color: "bg-gray-900",
  },
  // { name: "青鸟云", icon: Cloud, followers: 79, color: "bg-red-600" },
];

const blogInfo = {
  name: "吻鲸看日落",
  slogan: "Stay simple, stay naive.",
  description: "独立开发者/生活博主",
  details: "独立开发/生活/博客/游戏",
  website: "https://idealclover.top",
};
</script>

<template>
  <div class="w-full md:w-[240px] flex-shrink-0 hidden lg:block space-y-4">
    <!-- 个人信息卡片 -->
    <div class="bg-base-100 rounded-xl p-4 shadow-md">
      <div class="flex flex-col items-center justify-center gap-4">
        <!-- 头像 -->
        <div class="avatar">
          <div class="w-24 h-24 rounded-full overflow-hidden">
            <img
              src="../../assets/东山小红.jpg"
              alt="头像"
              class="object-cover"
            />
          </div>
        </div>

        <!-- 个人信息 -->
        <div class="text-center">
          <h2 class="text-xl font-bold">{{ blogInfo.name }}</h2>
          <p class="text-sm opacity-75">{{ blogInfo.slogan }}</p>
        </div>

        <!-- 博主信息 -->
        <div class="badge badge-outline p-3">{{ blogInfo.description }}</div>

        <!-- 详细信息 -->
        <div class="text-sm opacity-75 text-center">
          <p>{{ blogInfo.details }}</p>
          <a
            :href="blogInfo.website"
            class="text-blue-500 hover:underline text-xs mt-1 block"
            >{{ blogInfo.website }}</a
          >
        </div>

        <!-- 统计信息 -->
        <div class="flex justify-evenly w-full border-t border-b py-2">
          <span
            class="flex items-center justify-center flex-col"
            v-for="info in Infos"
            :key="info.text"
          >
            <p class="font-medium text-sm">{{ info.text }}</p>
            <p class="font-bold">{{ info.num }}</p>
          </span>
        </div>
      </div>
    </div>

    <!-- 社交媒体卡片 -->
    <div class="bg-base-100 rounded-xl shadow-md overflow-hidden">
      <div class="grid grid-cols-1 gap-2 p-2">
        <a
          v-for="link in socialLinks"
          :key="link.name"
          href="#"
          :class="`flex items-center justify-between p-3 rounded-lg ${link.color} text-white hover:opacity-90 transition-opacity`"
        >
          <div class="flex items-center gap-2">
            <span>{{ link.name }}</span>
          </div>
          <div class="text-sm">{{ link.followers }} Followers</div>
        </a>
      </div>
    </div>

    <!-- 博客文章列表卡片 -->
    <div class="bg-base-100 rounded-xl shadow-md overflow-hidden">
      <div class="p-3 flex items-center gap-2 border-b">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          class="w-5 h-5"
          viewBox="0 0 24 24"
          fill="none"
          stroke="currentColor"
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
        >
          <path
            d="M14.5 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V7.5L14.5 2z"
          />
          <polyline points="14 2 14 8 20 8" />
        </svg>
        <span>博客文章</span>
      </div>
      <div class="divide-y">
        <div
          v-for="i in 5"
          :key="i"
          class="p-3 hover:bg-base-200 cursor-pointer"
        >
          <div class="flex justify-between items-center">
            <p class="line-clamp-1 text-sm">这是一篇博客文章标题</p>
            <span class="text-xs opacity-70">2024.01.01</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 可以添加额外的自定义样式 */
</style>

<script setup>
import { Tag } from 'lucide-vue-next'
import {onMounted, computed, ref} from "vue";
import {useArticleStore, useTagStore } from "../store/index";
import {fetchArticles, fetchTags} from "../utils/store.js";
import CButton from "../components/Custom/CButton.vue";
import { randomColor } from "../utils/index";
const tags = useTagStore((state)=> state.tags)

const articles = useArticleStore((state)=> state.articles)

const selectedTag = ref(null);

// 按年份和标签筛选分组文章
const searchQuery = ref('');

// 修改后的分组计算属性，包含搜索功能
const groupedByYear = computed(() => {
  const groups = {};
  articles.value.forEach(article => {
    // 搜索筛选
    if (searchQuery.value &&
        !article.title.toLowerCase().includes(searchQuery.value.toLowerCase()) &&
        !article.content?.toLowerCase().includes(searchQuery.value.toLowerCase())) {
      return;
    }
    // 如果选择了标签且文章不包含该标签，则跳过
    if (selectedTag.value && !article.tags?.includes(selectedTag.value)) {
      return;
    }
    const year = new Date(article.time).getFullYear();
    if (!groups[year]) {
      groups[year] = [];
    }
    groups[year].push({
      title: article.title,
      time: article.time,
      tags: article.tags,
      viewCount: article.viewCount
    });
  });
  return Object.entries(groups).map(([year, posts]) => ({
    year,
    posts: posts.sort((a, b) => new Date(b.time) - new Date(a.time))
  })).sort((a, b) => b.year - a.year);
});

console.log("afasf",groupedByYear.value)

// 标签点击事件
const handleTagClick = (tagName) => {
  selectedTag.value = selectedTag.value === tagName ? null : tagName;
};

onMounted( async () => {
  await fetchTags()
  if(articles.value.length === 0){
     await fetchArticles()
  }
})

</script>

<template>
  <div class="container mx-auto px-4 py-8 max-w-7xl">
    <!-- 标题部分 -->
    <div class="text-center mb-12">
      <h1 class="text-4xl font-bold text-primary mb-2">文章归档</h1>
      <p class="text-lg text-gray-500">按年份和标签浏览所有文章</p>
    </div>

    <!-- 搜索框 -->
    <div class="mb-10 flex justify-center">
      <div class="relative w-full max-w-xl">
        <input
          type="text"
          placeholder="搜索文章标题或内容..."
          v-model="searchQuery"
          @input="searchQuery = $event.target.value"
          class="input input-bordered w-full pl-10 focus:ring-2 focus:ring-primary focus:border-primary"
        />
        <svg class="absolute left-3 top-3 h-5 w-5 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
        </svg>
      </div>
    </div>

    <!-- 主内容卡片 -->
    <div class="card bg-base-100 shadow-xl p-6">
      <!-- 标签部分 -->
      <div class="mb-10 p-4 bg-base-200 rounded-box">
        <div class="flex items-center mb-4">
          <Tag :size="28" class="text-primary" />
          <h2 class="ml-3 text-2xl font-bold text-primary ">文章标签</h2>
        </div>
        <div class="flex flex-wrap gap-3">
          <button
            v-for="tag in tags"
            :key="tag"
            class="btn btn-outline btn-sm hover:btn-primary hover:text-white transition-all"
            @click="handleTagClick(tag.tagName)"
            :class="selectedTag === tag.tagName ? 'btn-primary text-white' : 'btn-outline hover:btn-primary hover:text-white'"
          >
            {{ tag.tagName }}
          </button>
        </div>
      </div>

      <!-- 时间轴部分 -->
      <div class="relative">
        <div class="space-y-10 pl-8 border-l-2 border-primary/20">
          <div v-for="archive in groupedByYear" :key="archive.year" class="relative">
            <!-- 年份标记 -->
            <div class="absolute -left-8 -top-2 w-16 h-16 rounded-full bg-primary/10 flex items-center justify-center">
              <span class="text-xl font-bold text-primary">{{ archive.year }}</span>
            </div>

            <!-- 文章列表 -->
            <div class="ml-8 space-y-4">
              <div
                v-for="post in archive.posts"
                :key="post.aid"
                class="p-5 hover:bg-base-200 rounded-lg transition-all duration-300 hover:shadow-sm border border-transparent hover:border-primary/20"
              >
                <div class="flex flex-col space-y-3">
                  <h3 class="text-xl font-semibold text-primary hover:text-primary transition-colors">
                    {{ post.title }}
                  </h3>
                  <div class="flex flex-wrap items-center gap-4 text-sm">
                    <span class="flex items-center text-gray-500">
                      <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                      </svg>
                      {{ new Date(post.time).toLocaleDateString() }}
                    </span>
                    <span class="flex items-center text-gray-500">
                      <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z" />
                      </svg>
                      浏览: {{ post.viewCount || 0 }}次
                    </span>
                    <div class="flex flex-wrap gap-2">
                      <c-button
                        v-for="(tag,index) in post.tags?.split(',')"
                        :primary-color="randomColor(post.tags.length +index + post.title.length %12)"
                        class="px-2 py-0.5 text-sm"
                      >
                        {{ tag }}
                      </c-button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 自定义动画效果 */
.transition-all {
  transition: all 0.3s ease;
}
</style>

<script setup>
import {computed, onMounted, ref} from 'vue'
import {useArticleStore} from "../store/index";
import {fetchArticles} from "../utils/store.js";
import {renderMarkdown, timeAgo} from "../utils/index";
import img from "../assets/东山小红.jpg"

const articles = useArticleStore((state)=> state.articles)

// 筛选生活随笔文章 (categoryd=8)
const essays = computed(() => {
  return articles.value
    .filter(article => article.categoryId === 8)
    .map(article => ({
      id: article.aid,
      title: article.title,
      content: renderMarkdown(article.content),  // 使用marked转换Markdown
      date: timeAgo(article.time),
      tags: article.tags?.split(',') || [],
      author: '吻鲸看日落', // 作者名称
      avatar:img, // 作者头像
      viewCount: article.viewCount
    }))
})

// 分页相关
const currentPage = ref(1)
const itemsPerPage = 5

// 计算当前显示的随笔
const paginatedEssays = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage
  const end = start + itemsPerPage
  return essays.value.slice(start, end)
})

onMounted(()=>{
  if(articles.value.length === 0){
    fetchArticles()
  }
})
</script>

<template>
  <div class="container mx-auto px-4 py-8 max-w-4xl">
    <!-- 标题部分 -->
    <div class="text-center mb-12">
      <h1 class="text-4xl font-bold text-primary mb-2">生活随笔</h1>
      <p class="text-lg text-gray-500">记录生活点滴与思考</p>
    </div>

    <!-- 随笔列表 -->
    <div class="space-y-8">
      <div
        v-for="essay in paginatedEssays"
        :key="essay.id"
        class="bg-base-100 p-6 rounded-xl shadow-lg hover:shadow-xl transition-all duration-300 border border-transparent hover:border-primary/20"
      >
        <!-- 作者信息 -->
        <div class="flex items-center gap-3 mb-4">
          <img :src="essay.avatar" class="w-10 h-10 rounded-full object-cover" alt="作者头像">
          <div>
            <p class="font-medium">{{ essay.author }}</p>
            <p class="text-xs text-gray-500">{{ essay.date }}</p>
          </div>
        </div>

        <h2 class="text-2xl font-bold text-primary hover:text-primary transition-colors mb-3">
          {{ essay.title }}
        </h2>

        <!-- 浏览统计 -->
        <div class="flex items-center gap-4 text-sm text-gray-500 mb-4">
          <span class="flex items-center">
            <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"/>
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"/>
            </svg>
            浏览: {{ essay.viewCount }}次
          </span>
        </div>

        <!-- 完整内容（包含HTML和图片） -->
        <div class="prose max-w-none text-gray-700 mb-5" v-html="essay.content"></div>

        <div class="flex flex-wrap gap-2">
          <span
            v-for="tag in essay.tags"
            :key="tag"
            class="badge badge-outline badge-sm">
            {{ tag }}
          </span>
        </div>
      </div>
    </div>

    <!-- 分页 -->
    <div class="mt-10 flex justify-center" v-if="essays.length > itemsPerPage">
      <div class="btn-group">
        <button
          v-for="page in Math.ceil(essays.length / itemsPerPage)"
          :key="page"
          @click="currentPage = page"
          class="btn btn-sm"
          :class="currentPage === page ? 'btn-primary' : 'btn-ghost'"
        >
          {{ page }}
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 保留原有样式 */
.transition-all {
  transition: all 0.3s ease;
}
</style>
<!-- 新增全局样式 -->
<style>
.prose img {
  max-width: 80%;
  height: auto;
  border-radius: 0.5rem;
  margin: 1rem auto;
  display: block;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
/* 更精确的选择器 */
.prose :deep(img) {
  max-width: 80%;
  border: 1px solid #eee;
}
</style>
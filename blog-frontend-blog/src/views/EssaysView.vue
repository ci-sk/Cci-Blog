<script setup>
import {computed, ref} from 'vue'

// 模拟随笔数据
const essays = ref([
  {
    id: 1,
    title: '前端开发的未来趋势',
    content: '探讨前端开发在未来的发展方向...',
    date: '2023-09-15',
    tags: ['前端', '趋势']
  },
  {
    id: 2,
    title: 'Vue 3 新特性解析',
    content: '深入分析Vue 3的新特性和使用技巧...',
    date: '2023-09-20',
    tags: ['Vue', 'JavaScript']
  },
  // 更多随笔...
])

// 分页相关
const currentPage = ref(1)
const itemsPerPage = 5

// 计算当前显示的随笔
const paginatedEssays = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage
  const end = start + itemsPerPage
  return essays.value.slice(start, end)
})
</script>

<template>
  <div class="container mx-auto px-4 py-8">
    <!-- 标题 -->
    <h1 class="text-3xl font-bold mb-8 text-center">随笔</h1>

    <div class="max-w-4xl mx-auto">
      <!-- 随笔列表 -->
      <div class="space-y-6">
        <div
            v-for="essay in paginatedEssays"
            :key="essay.id"
            class="bg-base-100 p-6 rounded-lg shadow hover:shadow-md transition-shadow"
        >
          <h2 class="text-2xl font-semibold mb-2">{{ essay.title }}</h2>
          <div class="text-sm text-gray-500 mb-4">{{ essay.date }}</div>
          <p class="text-gray-700 mb-4">{{ essay.content }}</p>
          <div class="flex flex-wrap gap-2">
            <span
                v-for="tag in essay.tags"
                :key="tag"
                class="bg-base-200 px-2 py-1 rounded text-sm"
            >
              {{ tag }}
            </span>
          </div>
        </div>
      </div>

      <!-- 分页 -->
      <div class="mt-8 flex justify-center">
        <button
            v-for="page in Math.ceil(essays.length / itemsPerPage)"
            :key="page"
            @click="currentPage = page"
            :class="[
            'mx-1 px-4 py-2 rounded',
            currentPage === page ? 'bg-primary text-white' : 'bg-base-200'
          ]"
        >
          {{ page }}
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 可以根据需要添加自定义样式 */
</style>
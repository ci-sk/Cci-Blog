<script setup>
// ... archives 数据保持不变 ...
const archives = [
  {
    year: 2023,
    posts: [
      { title: 'Vue 3 新特性解析', date: '2023-03-15' },
      { title: 'TypeScript 高级技巧', date: '2023-05-20' },
      { title: 'Tailwind CSS 实战指南', date: '2023-08-10' }
    ]
  },
  {
    year: 2022,
    posts: [
      { title: 'React Hooks 深入理解', date: '2022-02-18' },
      { title: 'React Hooks 深入理解', date: '2022-02-18' },
      { title: 'React Hooks 深入理解', date: '2022-02-18' },
      { title: 'React Hooks 深入理解', date: '2022-02-18' },
      { title: 'Node.js 性能优化', date: '2022-07-22' }
    ]
  }
]
</script>

<template>
  <div class="container mx-auto px-4 py-8">
    <h1 class="text-4xl font-bold mb-8 text-center">归档</h1>

    <!-- 搜索框 -->
    <div class="mb-8">
      <input
          type="text"
          placeholder="搜索文章..."
          class="input input-bordered w-full max-w-md mx-auto block"
      />
    </div>

    <!-- 时间轴容器 -->
    <div class="relative">
      <!-- 时间轴竖线 -->
      <div class="absolute left-8 h-full w-1 bg-base-300"></div>

      <!-- 时间轴内容 -->
      <div class="space-y-12 pl-12">  <!-- 将 space-y-8 改为 space-y-12 -->
        <div v-for="archive in archives" :key="archive.year" class="relative">
          <!-- 年份标记 -->
          <div class="absolute left-8 transform -translate-x-1/2 -translate-y-1/2
     px-4 py-2">
            <span class="text-xl font-bold">{{ archive.year }}</span>
          </div>

          <!-- 文章列表 -->
          <div class="">
            <div
                v-for="post in archive.posts"
                :key="post.title"
                class="w-full p-4 hover:bg-base-200 rounded-lg transition-colors duration-200"
            >
              <div class="flex flex-col space-y-2">
                <span class="text-lg font-medium">{{ post.title }}</span>
                <span class="text-sm text-gray-500">{{ post.date }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<style scoped>
/* 可以根据需要添加自定义样式 */
</style>

<script setup>
const friends = [
  {
    name: '张三的博客',
    url: 'https://zhangsan.com',
    avatar: 'https://picsum.photos/100',
    description: '专注于前端技术分享'
  },
  {
    name: '李四的技术笔记',
    url: 'https://lisi.tech',
    avatar: 'https://picsum.photos/101',
    description: '记录技术成长之路'
  },
  {
    name: '王五的编程世界',
    url: 'https://wangwu.dev',
    avatar: 'https://picsum.photos/102',
    description: '探索编程的无限可能'
  },
  {
    name: '张三的博客',
    url: 'https://zhangsan.com',
    avatar: 'https://picsum.photos/100',
    description: '专注于前端技术分享'
  },
  {
    name: '李四的技术笔记',
    url: 'https://lisi.tech',
    avatar: 'https://picsum.photos/101',
    description: '记录技术成长之路'
  },
  {
    name: '王五的编程世界',
    url: 'https://wangwu.dev',
    avatar: 'https://picsum.photos/102',
    description: '探索编程的无限可能'
  }
]
</script>

<template>
  <div class="container mx-auto px-4 py-8">
    <h1 class="text-4xl font-bold mb-8 text-center">友链</h1>

    <!-- 友链容器 -->
    <div class="flex justify-center">
      <div class="w-full md:w-4/5 lg:w-4/5">  <!-- 响应式宽度 -->
        <!-- 友链列表 -->
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 ">  <!-- 响应式网格 -->
          <a
              v-for="friend in friends"
              :key="friend.name"
              :href="friend.url"
              target="_blank"
              class="bg-base-100 hover:bg-base-200 transition-colors duration-200 p-4 rounded-lg"
          >
            <div class="flex items-center space-x-4">  <!-- 调整布局 -->
              <div class="avatar">
                <div class="w-12 h-12 sm:w-14 sm:h-14 rounded-full">  <!-- 响应式头像大小 -->
                  <img :src="friend.avatar" :alt="friend.name" />
                </div>
              </div>
              <div>
                <h2 class="card-title text-base sm:text-lg">{{ friend.name }}</h2>  <!-- 响应式字体大小 -->
                <p class="text-xs sm:text-sm text-gray-500">{{ friend.description }}</p>
              </div>
            </div>
          </a>
        </div>

        <!-- 申请友链 -->
        <div class="mt-12 text-center">
          <p class="text-lg mb-4">想要交换友链？</p>
          <button class="btn btn-primary">申请友链</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 可以根据需要添加自定义样式 */
</style>

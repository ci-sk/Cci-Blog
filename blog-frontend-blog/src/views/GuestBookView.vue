<script setup>
import { ref } from 'vue'

// 模拟留言数据
const messages = ref([
  {
    id: 1,
    name: '张三',
    content: '这个博客真不错！',
    timestamp: '2023-10-01 10:00'
  },
  {
    id: 2,
    name: '李四',
    content: '学到了很多新知识，感谢分享！',
    timestamp: '2023-10-02 14:30'
  }
])

// 表单数据
const form = ref({
  name: '',
  content: ''
})

// 提交留言
const submitMessage = () => {
  if (!form.value.name || !form.value.content) return

  const newMessage = {
    id: messages.value.length + 1,
    name: form.value.name,
    content: form.value.content,
    timestamp: new Date().toLocaleString()
  }

  messages.value.unshift(newMessage)
  form.value.name = ''
  form.value.content = ''
}
</script>

<template>
  <div class="container mx-auto px-4 py-8">
    <!-- 标题 -->
    <h1 class="text-3xl font-bold mb-8 text-center">留言板</h1>

    <div class="max-w-2xl mx-auto">
      <!-- 留言表单 -->
      <div class="bg-base-100 p-6 rounded-lg shadow mb-8">
        <h2 class="text-xl font-semibold mb-4">留下你的留言</h2>
        <form @submit.prevent="submitMessage">
          <div class="mb-4">
            <label class="block mb-2">昵称</label>
            <input
                v-model="form.name"
                type="text"
                class="w-full p-2 border rounded"
                placeholder="请输入你的昵称"
                required
            />
          </div>
          <div class="mb-4">
            <label class="block mb-2">留言内容</label>
            <textarea
                v-model="form.content"
                class="w-full p-2 border rounded"
                rows="4"
                placeholder="请输入留言内容"
                required
            ></textarea>
          </div>
          <button
              type="submit"
              class="btn btn-primary w-full sm:w-auto"
          >
            提交留言
          </button>
        </form>
      </div>

      <!-- 留言列表 -->
      <div class="space-y-4">
        <div
            v-for="message in messages"
            :key="message.id"
            class="bg-base-100 p-4 rounded-lg shadow"
        >
          <div class="flex justify-between items-center mb-2">
            <h3 class="font-semibold">{{ message.name }}</h3>
            <span class="text-sm text-gray-500">{{ message.timestamp }}</span>
          </div>
          <p class="text-gray-700">{{ message.content }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 可以根据需要添加自定义样式 */
</style>
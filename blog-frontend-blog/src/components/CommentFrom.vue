<script setup>
import { ref, computed } from "vue";

const form = ref({
  name: "",
  email: "",
  website: "",
  content: "",
});

// 字数统计
const contentLength = ref(0);
const replyContentLength = ref(0);
const maxLength = 500;

// 提交评论
const submitComment = () => {
  if (!form.value.name || !form.value.email || !form.value.content) return;

  const newComment = {
    id: comments.value.length + 1,
    name: form.value.name,
    email: form.value.email,
    website: form.value.website,
    avatar: `https://api.dicebear.com/7.x/avataaars/svg?seed=${form.value.name}`,
    content: form.value.content,
    timestamp: new Date().toLocaleString(),
    likes: 0,
    replies: [],
  };

  comments.value.unshift(newComment);
  form.value.content = "";
  contentLength.value = 0;
};

// 监听内容变化更新字数
const updateContentLength = (event) => {
  contentLength.value = event.target.value.length;
};

// 监听回复内容变化更新字数
const updateReplyContentLength = (event) => {
  replyContentLength.value = event.target.value.length;
};
</script>

<template>
  <!-- 评论表单 -->
  <div class="card bg-base-200 shadow-xl mb-8">
    <div class="card-body">
      <!-- 头像预览 -->
      <div class="flex flex-col lg:flex-row gap-4">
        <div class="">
          <div class="avatar">
            <div class="w-16 rounded-full ring ring-offset-base-100 ">
              <img
                  :src="`https://api.dicebear.com/7.x/avataaars/svg?seed=${form.name}`"
                  alt="Avatar Preview"
              />
            </div>
          </div>
        </div>
        <div class="flex-1">
          <form @submit.prevent="submitComment">
            <div class="grid grid-cols-1 lg:grid-cols-3 gap-4 mb-4">
              <div>
                <label class="label">
                  <span class="label-text">昵称</span>
                  <span class="label-text-alt text-error">必填</span>
                </label>
                <input
                    v-model="form.name"
                    type="text"
                    class="input input-bordered w-full"
                    placeholder="请输入你的昵称"
                    required
                />
              </div>
              <div>
                <label class="label">
                  <span class="label-text">邮箱</span>
                  <span class="label-text-alt text-error">必填</span>
                </label>
                <input
                    v-model="form.email"
                    type="email"
                    class="input input-bordered w-full"
                    placeholder="请输入你的邮箱"
                    required
                />
              </div>
              <div>
                <label class="label">
                  <span class="label-text">网址</span>
                  <span class="label-text-alt">选填</span>
                </label>
                <input
                    v-model="form.website"
                    type="url"
                    class="input input-bordered w-full"
                    placeholder="请输入你的网站"
                />
              </div>
            </div>
            <div class="mb-4">
              <label class="label">
                <span class="label-text">评论内容</span>
                <span class="label-text-alt text-error">必填</span>
              </label>
              <textarea
                  v-model="form.content"
                  @input="updateContentLength"
                  class="textarea textarea-bordered w-full h-32"
                  placeholder="🐧在昵称框输入QQ号会自动获取邮箱和昵称，其中必填项为昵称和邮箱"
                  required
                  maxlength="500"
              ></textarea>
              <div class="flex justify-end mt-1">
            <span class="text-sm text-base-content/60">
              {{ contentLength }}/{{ maxLength }}
            </span>
              </div>
            </div>
            <div class="flex justify-end">
              <button
                  type="submit"
                  class="btn btn-primary"
                  :disabled="!form.name || !form.email || !form.content"
              >
                发送
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>

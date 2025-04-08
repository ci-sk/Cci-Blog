<script setup>
import { ref } from "vue";
import CommentFrom from "./CommentFrom.vue";

// 模拟评论数据
const comments = ref([
  {
    id: 1,
    name: "张三",
    email: "zhangsan@example.com",
    website: "https://example.com",
    avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Felix",
    content: "这篇文章写得非常好，学到了很多东西！",
    timestamp: "2023-10-01 10:00",
    likes: 5,
    replies: [
      {
        id: 101,
        parentId: 1,
        name: "王五",
        email: "wangwu@example.com",
        website: "",
        avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Wangwu",
        content: "我也觉得这篇文章很棒！",
        timestamp: "2023-10-01 11:30",
        likes: 2,
      },
    ],
  },
  {
    id: 2,
    name: "李四",
    email: "lisi@example.com",
    website: "",
    avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Aneka",
    content: "感谢分享，这些内容对我的项目很有帮助。期待更多类似的文章！",
    timestamp: "2023-10-02 14:30",
    likes: 3,
    replies: [],
  },
]);

// 表单数据
const form = ref({
  name: "",
  email: "",
  website: "",
  content: "",
});

// 回复表单数据
const replyForm = ref({
  parentId: null,
  name: "",
  email: "",
  website: "",
  content: "",
});

// 当前正在回复的评论ID
const activeReplyId = ref(null);

// 字数统计
const contentLength = ref(0);
const replyContentLength = ref(0);
const maxLength = 500;

// 监听内容变化更新字数
const updateContentLength = (event) => {
  contentLength.value = event.target.value.length;
};

// 监听回复内容变化更新字数
const updateReplyContentLength = (event) => {
  replyContentLength.value = event.target.value.length;
};

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

// 显示回复表单
const showReplyForm = (commentId) => {
  activeReplyId.value = commentId;
  // 重置回复表单
  replyForm.value = {
    parentId: commentId,
    name: "",
    email: "",
    website: "",
    content: "",
  };
  replyContentLength.value = 0;
};

// 取消回复
const cancelReply = () => {
  activeReplyId.value = null;
};

// 提交回复
const submitReply = () => {
  if (
    !replyForm.value.name ||
    !replyForm.value.email ||
    !replyForm.value.content
  )
    return;

  const parentComment = comments.value.find(
    (c) => c.id === replyForm.value.parentId
  );
  if (!parentComment) return;

  const newReply = {
    id: Date.now(), // 使用时间戳作为唯一ID
    parentId: replyForm.value.parentId,
    name: replyForm.value.name,
    email: replyForm.value.email,
    website: replyForm.value.website,
    avatar: `https://api.dicebear.com/7.x/avataaars/svg?seed=${replyForm.value.name}`,
    content: replyForm.value.content,
    timestamp: new Date().toLocaleString(),
    likes: 0,
  };

  // 添加回复到对应的评论中
  parentComment.replies.push(newReply);

  // 重置表单和状态
  activeReplyId.value = null;
  replyContentLength.value = 0;
};

// 点赞功能
const likeComment = (comment) => {
  comment.likes++;
};

// 回复点赞功能
const likeReply = (reply) => {
  reply.likes++;
};


// 获取被回复评论的用户名
const getCommentName = (commentId) => {
  // 在主评论中查找
  const mainComment = comments.value.find(c => c.id === commentId);
  if (mainComment) return mainComment.name;

  // 如果在主评论中没找到，可能在回复中
  for (const comment of comments.value) {
    const reply = comment.replies.find(r => r.id === commentId);
    if (reply) return reply.name;
  }

  return ''; // 如果都没找到，返回空字符串
};
</script>

<template>
  <div class="container mx-auto px-4 py-8">
    <!-- 评论标题 -->
    <div class="flex items-center gap-2 mb-6">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        class="h-6 w-6"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="M7 8h10M7 12h4m1 8l-4-4H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-3l-4 4z"
        />
      </svg>
      <h2 class="text-2xl font-bold">评论</h2>
      <span class="text-base-content/60 text-sm"
        >{{ comments.length }} 条评论</span
      >
    </div>
    <!--评论表单  -->
    <CommentFrom/>
    <!-- 评论列表 -->
    <div class="space-y-6">
      <div
        v-for="comment in comments"
        :key="comment.id"
        class="card bg-base-200 shadow-xl"
      >
        <div class="card-body">
          <div class="flex items-start gap-4">
            <!-- 头像 -->
            <div class="avatar">
              <div class="w-12 h-12 rounded-full">
                <img :src="comment.avatar" alt="avatar" />
              </div>
            </div>
            <div class="flex-1">
              <!-- 评论者信息 -->
              <div class="flex flex-wrap items-center gap-2 mb-1">
                <h4 class="font-semibold">{{ comment.name }}</h4>
                <a
                  v-if="comment.website"
                  :href="comment.website"
                  target="_blank"
                  class="text-primary text-sm hover:underline"
                >
                  {{ comment.website }}
                </a>
              </div>
               <!-- 评论内容 -->
              <p class="text-base-content/80 mb-3">{{ comment.content }}</p>
              <!-- 评论底部信息 -->
              <div
                class="flex justify-between items-center text-sm text-base-content/60"
              >
                <span>{{ comment.timestamp }}</span>
                <div class="flex gap-2">
                  <!--回复 -->
                  <button
                    @click="showReplyForm(comment.id)"
                    class="btn btn-sm btn-ghost gap-1"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      class="h-5 w-5"
                      fill="none"
                      viewBox="0 0 24 24"
                      stroke="currentColor"
                    >
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M3 10h10a8 8 0 018 8v2M3 10l6 6m-6-6l6-6"
                      />
                    </svg>
                    <span>回复</span>
                  </button>
                  <button
                    @click="likeComment(comment)"
                    class="btn btn-sm btn-ghost gap-1"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      class="h-5 w-5"
                      fill="none"
                      viewBox="0 0 24 24"
                      stroke="currentColor"
                    >
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M14 10h4.764a2 2 0 011.789 2.894l-3.5 7A2 2 0 0115.263 21h-4.017c-.163 0-.326-.02-.485-.06L7 20m7-10V5a2 2 0 00-2-2h-.095c-.5 0-.905.405-.905.905 0 .714-.211 1.412-.608 2.006L7 11v9m7-10h-2M7 20H5a2 2 0 01-2-2v-6a2 2 0 012-2h2.5"
                      />
                    </svg>
                    <span>{{ comment.likes }}</span>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 回复列表 (放在评论卡片内部) -->
        <div v-if="comment.replies && comment.replies.length > 0" class="ml-16">
          <div v-for="reply in comment.replies" :key="reply.id" >
            <div class="card-body">
              <div class="flex items-start gap-4">
                <!-- 回复者头像 -->
                <div class="avatar">
                  <div class="w-10 h-10 rounded-full">
                    <img :src="reply.avatar" alt="avatar" />
                  </div>
                </div>
                <div class="flex-1">
                  <!-- 回复者信息 -->
                  <div class="flex flex-wrap items-center gap-2 mb-1">
                    <h4 class="font-semibold">{{ reply.name }}</h4>
                    <span class="text-sm text-base-content/60">回复 @{{ getCommentName(reply.parentId) }}</span>
                  </div>
                  <!-- 回复内容 -->
                  <p class="text-base-content/80 mb-2">{{ reply.content }}</p>
                  <!-- 回复底部信息 -->
                  <div class="flex justify-between items-center text-sm text-base-content/60">
                    <span>{{ reply.timestamp }}</span>
                    <div class="flex gap-2">
                      <!--回复 -->
                      <button
                          @click="showReplyForm(comment.id)"
                          class="btn btn-sm btn-ghost gap-1"
                      >
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            class="h-5 w-5"
                            fill="none"
                            viewBox="0 0 24 24"
                            stroke="currentColor"
                        >
                          <path
                              stroke-linecap="round"
                              stroke-linejoin="round"
                              stroke-width="2"
                              d="M3 10h10a8 8 0 018 8v2M3 10l6 6m-6-6l6-6"
                          />
                        </svg>
                        <span>回复</span>
                      </button>
                      <button @click="likeReply(reply)" class="btn btn-sm btn-ghost gap-1">
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 10h4.764a2 2 0 011.789 2.894l-3.5 7A2 2 0 0115.263 21h-4.017c-.163 0-.326-.02-.485-.06L7 20m7-10V5a2 2 0 00-2-2h-.095c-.5 0-.905.405-.905.905 0 .714-.211 1.412-.608 2.006L7 11v9m7-10h-2M7 20H5a2 2 0 01-2-2v-6a2 2 0 012-2h2.5"/>
                        </svg>
                        <span>{{ reply.likes }}</span>
                      </button>
                    </div>

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 回复表单 (放在评论卡片底部) -->
        <div v-if="activeReplyId === comment.id" class="ml-16 ">
          <form @submit.prevent="submitReply">
            <div class="card-body">
              <div class="grid grid-cols-1 lg:grid-cols-3 gap-4 mb-4">
                <!-- 回复表单字段 -->
                <div>
                  <label class="label">
                    <span class="label-text">昵称</span>
                    <span class="label-text-alt text-error">必填</span>
                  </label>
                  <input
                      v-model="replyForm.name"
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
                      v-model="replyForm.email"
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
                      v-model="replyForm.website"
                      type="url"
                      class="input input-bordered w-full"
                      placeholder="请输入你的网址"
                  />
                </div>
              </div>
              <div class="mb-4">
        <textarea
            v-model="replyForm.content"
            @input="updateReplyContentLength"
            class="textarea textarea-bordered w-full h-24"
            placeholder="请输入回复内容"
            required
            maxlength="500"
        ></textarea>
                <div class="flex justify-end mt-1">
          <span class="text-sm text-base-content/60">
            {{ replyContentLength }}/{{ maxLength }}
          </span>
                </div>
              </div>
              <div class="flex justify-end gap-2">
                <button @click="cancelReply" type="button" class="btn btn-ghost">
                  取消
                </button>
                <button
                    type="submit"
                    class="btn btn-primary"
                    :disabled="!replyForm.name || !replyForm.email || !replyForm.content"
                >
                  发送回复
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.card {
  backdrop-filter: blur(8px);
  background-color: rgba(255, 255, 255, 0.05);
}
</style>

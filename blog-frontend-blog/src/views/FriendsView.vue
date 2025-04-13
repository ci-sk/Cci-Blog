<script setup>
import {onMounted, ref} from "vue";
import {fetchFriends} from "../utils/store.js";
import CommentSection from "../components/CommentSection.vue";

const myInfo = {
  name: "wallleap",
  url: "myblog.wallleap.cn",
  avatar: "myblog.wallleap.cn/avatar/bc1cedf020a62b89ad2c331c1b3d02a?size=256",
  desc: "Luwang's blog",
};

// const friends = [
//   {
//     name: "wallleap",
//     url: "https://myblog.wallleap.cn",
//     avatar: "https://myblog.wallleap.cn/avatar/1.jpg",
//     description: "Luwang's blog",
//   },
//   {
//     name: "叶落阁",
//     url: "https://yelog.org",
//     avatar: "https://cdn.jsdelivr.net/gh/yelog/assets/images/head.jpg",
//     description: "技术驱动生活",
//   },
//   {
//     name: "Avice-Blog",
//     url: "https://blog.avice.top",
//     avatar: "https://blog.avice.top/avatar.jpg",
//     description: "May The Force Be With You",
//   },
//   {
//     name: "Yremp",
//     url: "https://yremp.live",
//     avatar: "https://picsum.photos/100",
//     description: "沉舟，遗迹的博客",
//   },
//   {
//     name: "林木木",
//     url: "https://immmmm.com",
//     avatar: "https://immmmm.com/avatar.jpg",
//     description: "不问明天，悠然浪费",
//   },
//   {
//     name: "Uncleacc",
//     url: "https://uncleacc.cn",
//     avatar: "https://uncleacc.cn/avatar.jpg",
//     description: "一只努力学习的菜鸟！",
//   },
// ];

const friends = ref([])

onMounted( async () => {
  // 打乱数组顺序
  friends.value =  await fetchFriends()
})

</script>

<template>
  <div class="min-h-screen bg-base-100 py-12 px-4">
    <div class="max-w-4xl mx-auto">
      <!-- 页面标题 -->
      <div class="text-center mb-12">
        <h1 class="text-4xl font-bold text-primary mb-3">友链</h1>
        <p class="text-neutral text-lg">日月星之不见兮，抱影独对长夜。</p>
      </div>

      <!-- 个人友链信息 -->
      <div
        class="card bg-base-200 shadow-lg hover:shadow-xl transition-shadow duration-300 p-8 mb-12 rounded-2xl"
      >
        <h2 class="text-2xl font-semibold mb-6 flex items-center text-primary">
          <span class="i-carbon-link mr-3"></span>
          我的友链信息
        </h2>
        <p class="text-neutral text-lg mb-4">
          欢迎各位大佬交换友链 (づ￣ 3￣)づ
        </p>
        <p class="text-neutral text-lg mb-6">
          以下是我的友链信息，各位大佬可以在页面下按照格式留言
        </p>
        <div class="bg-base-300 rounded-xl p-6 font-mono text-sm shadow-inner">
          <pre class="text-primary">
          {
            "name": "{{ myInfo.name }}",
            "url": "{{ myInfo.url }}",
            "avatar": "{{ myInfo.avatar }}",
            "desc": "{{ myInfo.desc }}"
          }</pre>
        </div>
      </div>

      <!-- 友链列表 -->
      <div class="mb-12">
        <h2 class="text-2xl font-semibold mb-6 flex items-center text-primary">
          <span class="i-carbon-user-multiple mr-3"></span>
          小伙伴们
        </h2>
        <p class="text-neutral text-lg mb-3">
          ※ 以下友链友链随机排序，博主将不定期更新维护并新增友链
        </p>
        <p class="text-neutral text-lg mb-8">
          ※
          为了更好地展现站点，头像将保存到博主自己的存储空间，如果更新可联系博主修改
        </p>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          <a
            v-for="friend in friends"
            :key="friend.name"
            :href="friend.url"
            target="_blank"
            class="card bg-base-200 transition-all duration-300 p-6 flex flex-row items-center gap-6 rounded-xl group"
          >
            <div
              class="flex-shrink-0 transition-all duration-300 group-hover:opacity-0 group-hover:-translate-x-16 group-hover:-translate-y-16 z-10"
            >
              <img
                :src="friend.avatar"
                :alt="friend.name"
                class="w-16 h-16 rounded-full object-cover shadow-md hover:shadow-lg transition-shadow duration-300"
              />
            </div>
            <div
              class="flex-1 min-w-0 transition-all duration-300 transform translate-x-0 group-hover:translate-x-[-4rem]"
            >
              <h3
                class="text-lg font-semibold truncate text-primary transition-colors duration-300 group-hover:text-primary-focus"
              >
                {{ friend.name }}
              </h3>
              <p class="text-neutral text-sm truncate">
                {{ friend.description }}
              </p>
            </div>
          </a>
        </div>
      </div>

      <!-- 申请友链 -->
      <div class="text-center">
        <button
          class="btn btn-primary btn-lg hover:shadow-lg transition-shadow duration-300 px-8"
        >
          申请友链
        </button>
      </div>
      <!-- 评论组件 -->
      <CommentSection :aid="0"/>
    </div>
  </div>
</template>

<style scoped>
.i-carbon-link,
.i-carbon-user-multiple {
  display: inline-block;
  width: 1.2em;
  height: 1.2em;
  background-color: currentColor;
}
</style>

<script setup>
import CommentSection from "../components/CommentSection.vue";
</script>

<template>
  <div class="container mx-auto px-4 py-8">
    <!-- 个人简介部分 -->
    <div class="card bg-base-200 shadow-xl mb-8">
      <div class="card-body">
        <div class="flex items-center gap-4 mb-4">
          <div class="avatar">
            <div
              class="w-24 rounded-full ring ring-primary ring-offset-base-100 ring-offset-2"
            >
              <img src="../assets/东山小红.jpg" alt="avatar" />
            </div>
          </div>
          <div>
            <h2 class="card-title text-2xl mb-2">关于我</h2>
            <p class="text-base-content/80">
              博主是个小菜狗，大学学的网络工程，大二时候接触到了 Web
              开发，自那之后就对前端有了兴趣，毕业后也从事的前端开发。
            </p>
          </div>
        </div>
      </div>
    </div>

    <!-- 联系方式部分 -->
    <div class="card bg-base-200 shadow-xl mb-8">
      <div class="card-body">
        <h2 class="card-title text-2xl mb-4">联系我</h2>
        <p class="text-base-content/80 mb-4">你可以到这些地方找到我</p>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
          <a href="#" class="btn btn-outline gap-2">
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
                d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"
              />
            </svg>
            Email
          </a>
          <a
            href="https://space.bilibili.com/"
            target="_blank"
            class="btn btn-outline gap-2"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="h-6 w-6"
              viewBox="0 0 24 24"
              fill="currentColor"
            >
              <path
                d="M17.813 4.653h.854c1.51.054 2.769.578 3.773 1.574 1.004.995 1.524 2.249 1.56 3.76v7.36c-.036 1.51-.556 2.769-1.56 3.773s-2.262 1.524-3.773 1.56H5.333c-1.51-.036-2.769-.556-3.773-1.56S.036 18.858 0 17.347v-7.36c.036-1.511.556-2.765 1.56-3.76 1.004-.996 2.262-1.52 3.773-1.574h.774l-1.174-1.12a1.234 1.234 0 0 1-.373-.906c0-.356.124-.658.373-.907l.027-.027c.267-.249.573-.373.92-.373.347 0 .653.124.92.373L9.653 4.44c.071.071.134.142.187.213h4.267a.836.836 0 0 1 .16-.213l2.853-2.747c.267-.249.573-.373.92-.373.347 0 .662.151.929.4.267.249.391.551.391.907 0 .355-.124.657-.373.906zM5.333 7.24c-.746.018-1.373.276-1.88.773-.506.498-.769 1.13-.786 1.894v7.52c.017.764.28 1.395.786 1.893.507.498 1.134.756 1.88.773h13.334c.746-.017 1.373-.275 1.88-.773.506-.498.769-1.129.786-1.893v-7.52c-.017-.765-.28-1.396-.786-1.894-.507-.497-1.134-.755-1.88-.773zM8 11.107c.373 0 .684.124.933.373.25.249.383.569.4.96v1.173c-.017.391-.15.711-.4.96-.249.25-.56.374-.933.374s-.684-.125-.933-.374c-.25-.249-.383-.569-.4-.96V12.44c0-.373.129-.689.386-.947.258-.257.574-.386.947-.386zm8 0c.373 0 .684.124.933.373.25.249.383.569.4.96v1.173c-.017.391-.15.711-.4.96-.249.25-.56.374-.933.374s-.684-.125-.933-.374c-.25-.249-.383-.569-.4-.96V12.44c.017-.391.15-.711.4-.96.249-.249.56-.373.933-.373z"
              />
            </svg>
            Bilibili
          </a>
          <a
            href="https://github.com/"
            target="_blank"
            class="btn btn-outline gap-2"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="h-6 w-6"
              viewBox="0 0 24 24"
              fill="currentColor"
            >
              <path
                d="M12 .297c-6.63 0-12 5.373-12 12 0 5.303 3.438 9.8 8.205 11.385.6.113.82-.258.82-.577 0-.285-.01-1.04-.015-2.04-3.338.724-4.042-1.61-4.042-1.61C4.422 18.07 3.633 17.7 3.633 17.7c-1.087-.744.084-.729.084-.729 1.205.084 1.838 1.236 1.838 1.236 1.07 1.835 2.809 1.305 3.495.998.108-.776.417-1.305.76-1.605-2.665-.3-5.466-1.332-5.466-5.93 0-1.31.465-2.38 1.235-3.22-.135-.303-.54-1.523.105-3.176 0 0 1.005-.322 3.3 1.23.96-.267 1.98-.399 3-.405 1.02.006 2.04.138 3 .405 2.28-1.552 3.285-1.23 3.285-1.23.645 1.653.24 2.873.12 3.176.765.84 1.23 1.91 1.23 3.22 0 4.61-2.805 5.625-5.475 5.92.42.36.81 1.096.81 2.22 0 1.606-.015 2.896-.015 3.286 0 .315.21.69.825.57C20.565 22.092 24 17.592 24 12.297c0-6.627-5.373-12-12-12"
              />
            </svg>
            GitHub
          </a>
          <a href="https://t.me/" target="_blank" class="btn btn-outline gap-2">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="h-6 w-6"
              viewBox="0 0 24 24"
              fill="currentColor"
            >
              <path
                d="M11.944 0A12 12 0 0 0 0 12a12 12 0 0 0 12 12 12 12 0 0 0 12-12A12 12 0 0 0 12 0a12 12 0 0 0-.056 0zm4.962 7.224c.1-.002.321.023.465.14a.506.506 0 0 1 .171.325c.016.093.036.306.02.472-.18 1.898-.962 6.502-1.36 8.627-.168.9-.499 1.201-.82 1.23-.696.065-1.225-.46-1.9-.902-1.056-.693-1.653-1.124-2.678-1.8-1.185-.78-.417-1.21.258-1.91.177-.184 3.247-2.977 3.307-3.23.007-.032.014-.15-.056-.212s-.174-.041-.249-.024c-.106.024-1.793 1.14-5.061 3.345-.48.33-.913.49-1.302.48-.428-.008-1.252-.241-1.865-.44-.752-.245-1.349-.374-1.297-.789.027-.216.325-.437.893-.663 3.498-1.524 5.83-2.529 6.998-3.014 3.332-1.386 4.025-1.627 4.476-1.635z"
              />
            </svg>
            Telegram
          </a>
        </div>
      </div>
    </div>

    <!-- 博客说明部分 -->
    <div class="card bg-base-200 shadow-xl mb-8">
      <div class="card-body">
        <h2 class="card-title text-2xl mb-4">关于博客和主题</h2>
        <div class="space-y-4 text-base-content/80">
          <p>
            博客名称取得比名字首字母
            LW，然后用两个单词拼接起来的，意为越过屏障，遇到困难迎而上。博客主要写一些前端设计/网络的学习、思考总结，以及记录自己的想法、遇到的问题和解决方案等。
          </p>
          <p>
            之所以搭建个人博客，是因为想把数据都掌握在自己手里，可以随意迁移。
          </p>
          <p>
            个人使用博客最开始是 GitHub Pages +
            jekyll，但是发现没有好看的主题；于是改用 Hexo。Hexo 主题很多，使用
            Markdown 书写文章很方便，但每次写完文章都需要重新部署（Hugo
            也是），即使用了 GitHub Action 流程化，可以直接在 GitHub
            上粘贴写好的文章，但还是很不方便；也考虑过使用
            WP、Typecho，但是需要部署到服务器上，我不能确保一直续费下去；最后使用的是
            GitHub API + Vercel，在 GitHub Issue 上写好文章，Vue
            构建前端页面（只需要部署一次），托管到 Vercel，通过自定义域名访问。
          </p>
        </div>
      </div>
    </div>

    <!-- 评论组件 -->
    <CommentSection :aid="0"/>
  </div>
</template>

<style scoped>
.card {
  backdrop-filter: blur(8px);
  background-color: rgba(255, 255, 255, 0.05);
}
</style>

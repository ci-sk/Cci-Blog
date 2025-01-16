<script setup>
import { ref } from "vue";
import { getArticleCount } from "../../net/article.js";
import { getAccountCount } from "../../net/account.js";
import { getTagCount } from "../../net/tag.js";

const Article = ref(0);

const Account = ref(0);

const Tags = ref(0);

getArticleCount((res) => {
  Article.value = res;
});

getAccountCount((res) => {
  Account.value = res;
});

getTagCount((res) => {
  Tags.value = res;
});
</script>

<template>

  <div class="home-header">
    <el-card class="home-header-item">
      <div class="home-header-item-left">
        <!-- 文章图标 -->
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="72" height="72">
          <rect x="3" y="3" width="18" height="18" fill="#e0f7fa" />
          <path d="M5 5v14h14V5H5zm2 2h10v2H7V7zm0 3h10v2H7v-2zm0 3h10v2H7v-2zm0 3h10v2H7v-2z" fill="#00796b" />
        </svg>
      </div>
      <div class="home-header-item-right">
        <div class="home-header-item-title">文章</div>
        <div class="home-header-item-num">{{ Article }}</div>
      </div>
    </el-card>

    <el-card class="home-header-item">
      <div class="home-header-item-left">
        <!-- 用户图标 -->
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="72" height="72">
          <circle cx="12" cy="8" r="4" fill="#bbdefb" />
          <path d="M12 14c-4.41 0-8 2.24-8 5v2h16v-2c0-2.76-3.59-5-8-5z" fill="#1e88e5" />
        </svg>
      </div>
      <div class="home-header-item-right">
        <div class="home-header-item-title">用户</div>
        <div class="home-header-item-num">{{ Account }}</div>
      </div>
    </el-card>
    <el-card class="home-header-item">
      <div class="home-header-item-left">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="72" height="72">
          <path
            d="M17.63 5.84C17.27 5.33 16.7 5 16 5H5c-1.1 0-2 .9-2 2v11c0 1.1.9 2 2 2h11c.7 0 1.27-.33 1.63-.84l4.37-6.16-4.37-6.16zM5 18V7h11l3.55 5L16 18H5z"
            fill="#ffcc80" />
          <circle cx="7.5" cy="12" r="1.5" fill="#ff5722" />
        </svg>
      </div>
      <div class="home-header-item-right">
        <div class="home-header-item-title">标签</div>
        <div class="home-header-item-num">{{ Tags }}</div>
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.home-header {
  display: flex;

  .home-header-item {
    display: flex;
    justify-content: center;
    align-items: center;
    flex: 1;
    margin: 15px;

    .home-header-item-left,
    .home-header-item-right {
      display: flex;
      align-items: center;
    }

    .home-header-item-right {
      flex-direction: row;
      align-items: center;

      .home-header-item-title {
        font-size: 20px;
        font-weight: 600;
        margin-right: 10px;
      }

      .home-header-item-num {
        font-size: 18px;
        font-weight: 600;
      }
    }
  }
}
</style>
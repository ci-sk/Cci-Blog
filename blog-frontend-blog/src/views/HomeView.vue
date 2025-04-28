<script setup>
import Pagination from "../components/Pagination.vue"; // 引入分页组件
import { onMounted, ref, computed, watch } from "vue";
import { useRouter } from "vue-router";
import {useArticleStore} from "../store/index";
import {generateColorFromText, timeAgo} from "../utils/index";
import CButton from "../components/Custom/CButton.vue";
import ClockIcon from "../components/Custom/ClockIcon.vue";
import HomeAbser from "./ArticleView/HomeAbser.vue";
import {fetchArticles} from "../utils/store.js";

const router = useRouter();

const cate = ref(["全部", "test", "前端", "后端", "SQL", "工具"]);

const selectedCate = ref(0);

const article = useArticleStore((state) => state.articles);

// 当前页显示的文章数据
const currentPageArticles = ref([]);

// 根据选中的标签筛选文章
const filteredArticles = computed(() => {
  if (selectedCate.value === 0) {
    return article.value;
  }
  else{
    return article.value.filter((item) => {
      return (
          item.category.name === cate.value[selectedCate.value]
      );
    });
  }
});

// 分页组件引用
const paginationRef = ref(null);

const toArt = (id) => {
  console.log("id", id);
  router.push({
    name: `Article`,
    params: { id },
  });
};

const filterTag = (index) => {
  selectedCate.value = index;
};

// 监听筛选后的文章变化，更新分页
watch(filteredArticles, () => {
  setTimeout(() => {
    if (paginationRef.value) {
      paginationRef.value.resetPage();
    }
  }, 0);
});

onMounted(() => {
  // 监听媒体查询的变化，更新颜色模式
  fetchArticles();
});

const articleColor = computed(() => {
  if (!article.value.content) return '#7480ff'; // 默认颜色
  return generateColorFromText(article.value.content);
});
</script>

<template>
  <div class="container mx-auto p-4">
    <h1 class="text-3xl font-bold">欢迎来到我的博客</h1>
    <p class="mt-4 text-gray-600">
      这里是首页内容，你可以在这里展示一些简介或最新文章。
    </p>
  </div>
  <div class="tabs tabs-boxed bg-base-100 justify-center">
    <a
      class="tab font-black text-sm sm:text-base md:text-lg lg:text-xl"
      :class="{ 'tab-active': selectedCate === index }"
      @click="filterTag(index)"
      v-for="(tab, index) in cate"
      >{{ tab }}</a
    >
  </div>
  <div class="w-5/6 lg:max-w-screen-xl mx-auto">
    <div class="flex gap-4">
      <div class="flex-1">
        <div class="grid grid-cols-1 md:grid-cols-2 cto:grid-cols-3 gap-4">
          <div
            v-for="item in currentPageArticles"
            class="card bg-primary shadow-sm"
            @click="toArt(item.aid)"
          >
            <figure class="p-8 pb-0">
              <div class="magnify-container">
                <img
                  :src="item.img_url"
                  alt="Shoes"
                  class="magnify-img object-cover object-center"
                />
              </div>
            </figure>
            <div class="text-white pl-8 pt-2">
              <CButton
                v-if="item.category"
                :width="60" :height="25"
                :primary-color="`${generateColorFromText(item.content)}`"
                >{{ item.category.name }}
              </CButton>
            </div>
            <div class="card-body items-center text-white pt-0 mb-5">
              <h2 class="card-title">{{ item.title }}</h2>
              <div>
                <div class="flex">
                  <p class="text-base leading-6 transition-all line-clamp-2 break-words w-48 text-center">
                    {{ item.content }}
                  </p>
                </div>
              </div>
              <div class="absolute bottom-0 self-start items-center flex flex-row pb-5">
                <ClockIcon />
                <p class="pl-2">
                  {{ timeAgo(item.time) }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <HomeAbser/>

    </div>
  </div>
  <div class="mt-3">
    <Pagination
      ref="paginationRef"
      :data="filteredArticles"
      :perPage="9"
      @update:currentData="currentPageArticles = $event"
    />
  </div>
</template>

<style scoped lang="less">
.magnify-container {
  position: relative;
  overflow: hidden;
  border-radius: 10px;
  img {
    width: 100%; /* 图片宽度填充容器 */
    aspect-ratio: 16 / 9; /* 设置宽高比为 16:9 */
    object-fit: cover; /* 按比例填充，裁剪多余部分 */
  }
}

.magnify-img {
  transition: transform 0.3s ease;
}

.magnify-container:hover .magnify-img {
  transform: scale(1.5); /* 放大倍数 */
}
</style>

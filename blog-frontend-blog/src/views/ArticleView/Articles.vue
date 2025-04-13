<script setup>
import { marked } from 'marked';
import {ref, computed, onMounted, nextTick} from 'vue';
import Prism from 'prismjs';
import '../../assets/css/prism-dracula.css';
import 'prismjs/plugins/line-numbers/prism-line-numbers.min.js'; // 行号插件
import 'prismjs/plugins/line-numbers/prism-line-numbers.min.css';
import CButton from "../../components/Custom/CButton.vue";
import {generateId, renderMarkdown} from "../../utils/index.js";
import {ArticlesCont} from "../../utils/store.js";
import {useRoute} from "vue-router";
import CommentSection from "../../components/CommentSection.vue"; // 行号插件样式

// Markdown 文本
const value = ref('');
const markdownToHtml = ref();
const route = useRoute();
const articleId = ref(route.params.id); // 获取路由参数id
const article = ref({});

const fetchArticleContent = async () => {
  article.value =  await ArticlesCont(articleId.value)
  if (article.value.content) {
    console.log(article.value)
    value.value = article.value.content;
    markdownToHtml.value = renderMarkdown(value.value)
    await nextTick();
    Prism.highlightAll();
  }
};

// 生成大纲
const toc = computed(() => {
  const tokens = marked.lexer(value.value);
  console.log(tokens); // 打印 tokens，检查解析结果
  const headings = tokens.filter(token => token.type === 'heading');
  const toc = [];
  let currentLevel = 1;
  headings.forEach(heading => {
    const level = heading.depth;
    // 确保heading.text是字符串
    const textStr = String(heading.text);
    const id = generateId(textStr);
    const item = {id, text: textStr};

    if (level > currentLevel) {
      // 子标题
      const parent = toc[toc.length - 1];
      if (!parent.children) parent.children = [];
      parent.children.push(item);
    } else {
      // 新标题
      toc.push(item);
    }
    currentLevel = level;
  });
  return toc;
});

// 处理点击事件
const handleTocClick = (e, id) => {
  e.preventDefault();
  const target = document.getElementById(id);
  if (target) {
    console.log('Target element found:', target);
    const offset = 80;
    const targetPosition = target.getBoundingClientRect().top + window.scrollY - offset;
    window.scrollTo({
      top: targetPosition,
      behavior: 'smooth'
    });
  } else {
    console.error('Target element not found');
  }
};

onMounted(() => {
  fetchArticleContent();
});
</script>

<template>
  <div class="p-4 max-w-screen-lg mx-auto">
    <div class="flex gap-4">
      <div class="flex-1">
        <div class="ml-6">
          <div>
            <CButton :primary-color="'#7480ff'">分类</CButton>
          </div>
          <div class="flex justify-between items-center">
            <h1 class="text-3xl font-bold">文章标题</h1>
          </div>
          <div class="items-end gap-2 mt-2">
            <p class="text-sm text-gray-500">发布日期</p>
            <span class="text-sm text-gray-500">标签：</span>
          </div>
        </div>

        <!-- Markdown 内容 -->
        <div class="card bg-base-100 shadow-xl">
          <div class="card-body">
            <div v-html="markdownToHtml" class="markdown-body content line-numbers"></div>
          </div>
        </div>
      </div>
      <div class="w-[200px] flex-shrink-0 hidden lg:block rounded-xl">
        <!-- 大纲 -->
        <div class="sticky top-5 w-full">
          <ul class="space-y-2">
            <li v-for="item in toc" :key="item.id" class="pl-0">
              <a
                  :href="`#${item.id}`"
                  @click="(e)=>handleTocClick(e, item.id)"
                  class=" hover:text-blue-500 dark:hover:text-blue-400 transition-colors duration-200"
              >
                {{ item.text }}
              </a>
              <ul v-if="item.children" class="pl-4 mt-1 space-y-1">
                <li v-for="child in item.children" :key="child.id">
                  <a
                      :href="`#${child.id}`"
                      @click="(e)=>handleTocClick(e, child.id)"
                      class="text-sm text-gray-600 dark:text-gray-400 hover:text-blue-400 dark:hover:text-blue-300 transition-colors duration-200"
                  >
                    {{ child.text }}
                  </a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- 评论组件 -->
    <CommentSection :aid="articleId"/>
  </div>
</template>

<style>

.markdown-body h1 {
  font-size: 2em;
  margin: 0.67em 0;
  font-weight: bold;
  padding-bottom: 0.3em;
}

.markdown-body h2 {
  font-size: 1.5em;
  margin: 0.83em 0;
  font-weight: bold;
  padding-bottom: 0.3em;
}

.markdown-body h3 {
  font-size: 1.17em;
  margin: 1em 0;
  font-weight: bold;
}

.markdown-body h4 {
  font-size: 1em;
  margin: 1.33em 0;
  font-weight: bold;
}

.markdown-body h5 {
  font-size: 0.83em;
  margin: 1.67em 0;
  font-weight: bold;
}

.markdown-body h6 {
  font-size: 0.67em;
  margin: 2.33em 0;
  font-weight: bold;
}
</style>
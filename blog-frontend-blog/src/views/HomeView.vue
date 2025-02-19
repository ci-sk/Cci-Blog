<script setup>
import Pagination from '../components/Pagination.vue'; // 引入分页组件

import {ref} from "vue";
import {useRouter} from "vue-router";


const router = useRouter();

const cate = ref([
  "全部","test","前端","后端","数据库","工具"
])

// 数据源
const items = ref([
  { id: 1, name: 'Item 1' },
  { id: 2, name: 'Item 2' },
  { id: 3, name: 'Item 3' },
  { id: 4, name: 'Item 4' },
  { id: 5, name: 'Item 5' },
  { id: 6, name: 'Item 6' },
  { id: 7, name: 'Item 7' },
  { id: 8, name: 'Item 8' },
  { id: 9, name: 'Item 9' },
  { id: 10, name: 'Item 10' },
  { id: 11, name: 'Item 11' },
  { id: 12, name: 'Item 12' },
]);

const selectedCate = ref(0)

const Infos = [
  {text:"文章","num":12},
  {text:"分类","num":12},
  {text:"标签","num":12},
]

const toArt = () => {
  router.push("/blog/article")
}

</script>

<template>
  <div class="container mx-auto p-4">
    <h1 class="text-3xl font-bold">欢迎来到我的博客</h1>
    <p class="mt-4 text-gray-600">这里是首页内容，你可以在这里展示一些简介或最新文章。</p>
  </div>
  <div class="tabs tabs-boxed bg-base-100 justify-center p-4">
    <a class="tab font-black text-lg" :class="{'tab-active': selectedCate === index}"
       @click="selectedCate = index"
       v-for="(tab,index) in cate">{{tab}}</a>
  </div>

  <div class="w-5/6 lg:max-w-screen-xl  mx-auto ">
    <div class="flex gap-4">
      <div class="flex-1">
        <div class="grid grid-cols-1 md:grid-cols-2  cto:grid-cols-3 gap-4">
          <!-- 卡片 1 -->
          <div class="card bg-emerald-500 shadow-sm" @click="toArt">
            <figure class="p-8">
              <div class="magnify-container">
                <img
                    src="https://img.daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.webp"
                    alt="Shoes"
                    class="magnify-img object-cover object-center" />
              </div>
            </figure>
            <div class="card-body items-center text-center">
              <h2 class="card-title">跳转测试</h2>
              <div>
                <p>日期 · 分类</p>
                <div class="flex">
                  <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" fill-rule="evenodd" clip-rule="evenodd" class="fill-current">
                    <path d="M22.672 15.226l-2.432.811.841 2.515c.33 1.019-.209 2.127-1.23 2.456-1.15.325-2.148-.321-2.463-1.226l-.84-2.518-5.013 1.677.84 2.517c.391 1.203-.434 2.542-1.831 2.542-.88 0-1.601-.564-1.86-1.314l-.842-2.516-2.431.809c-1.135.328-2.145-.317-2.463-1.229-.329-1.018.211-2.127 1.231-2.456l2.432-.809-1.621-4.823-2.432.808c-1.355.384-2.558-.59-2.558-1.839 0-.817.509-1.582 1.327-1.846l2.433-.809-.842-2.515c-.33-1.02.211-2.129 1.232-2.458 1.02-.329 2.13.209 2.461 1.229l.842 2.515 5.011-1.677-.839-2.517c-.403-1.238.484-2.553 1.843-2.553.819 0 1.585.509 1.85 1.326l.841 2.517 2.431-.81c1.02-.33 2.131.211 2.461 1.229.332 1.018-.21 2.126-1.23 2.456l-2.433.809 1.622 4.823 2.433-.809c1.242-.401 2.557.484 2.557 1.838 0 .819-.51 1.583-1.328 1.847m-8.992-6.428l-5.01 1.675 1.619 4.828 5.011-1.674-1.62-4.829z"></path>
                  </svg>
                  <p class="line-clamp-3 text-base leading-6 transition-all"
                  >这是博客的摘要，提供一些关键信息，帮助读者了解博客的内容。</p>
                </div>
              </div>
            </div>
          </div>
          <!-- 卡片 2 -->
          <div class="card bg-emerald-500 shadow-sm">
            <figure class="p-8">
              <div class="magnify-container overflow-hidden rounded-lg shadow-lg">
                <img
                    src="../assets/东山小红.jpg"
                    alt="Shoes"
                    class="magnify-img object-contain object-center" />
              </div>
            </figure>
            <div class="card-body items-center text-center">
              <h2 class="card-title">博客标题 2</h2>
              <div>
                <p>日期 · 分类</p>
                <div class="flex">
                  <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" fill-rule="evenodd" clip-rule="evenodd" class="fill-current">
                    <path d="M22.672 15.226l-2.432.811.841 2.515c.33 1.019-.209 2.127-1.23 2.456-1.15.325-2.148-.321-2.463-1.226l-.84-2.518-5.013 1.677.84 2.517c.391 1.203-.434 2.542-1.831 2.542-.88 0-1.601-.564-1.86-1.314l-.842-2.516-2.431.809c-1.135.328-2.145-.317-2.463-1.229-.329-1.018.211-2.127 1.231-2.456l2.432-.809-1.621-4.823-2.432.808c-1.355.384-2.558-.59-2.558-1.839 0-.817.509-1.582 1.327-1.846l2.433-.809-.842-2.515c-.33-1.02.211-2.129 1.232-2.458 1.02-.329 2.13.209 2.461 1.229l.842 2.515 5.011-1.677-.839-2.517c-.403-1.238.484-2.553 1.843-2.553.819 0 1.585.509 1.85 1.326l.841 2.517 2.431-.81c1.02-.33 2.131.211 2.461 1.229.332 1.018-.21 2.126-1.23 2.456l-2.433.809 1.622 4.823 2.433-.809c1.242-.401 2.557.484 2.557 1.838 0 .819-.51 1.583-1.328 1.847m-8.992-6.428l-5.01 1.675 1.619 4.828 5.011-1.674-1.62-4.829z"></path>
                  </svg>
                  <p class="line-clamp-3 text-base leading-6 transition-all">这是博客的摘要，提供一些关键信息，帮助读者了解博客的内容。</p>
                </div>
              </div>
            </div>
          </div>
          <!-- 卡片 3 -->
          <div class="card bg-emerald-500 shadow-sm">
            <figure class="p-8">
              <div class="magnify-container overflow-hidden rounded-lg shadow-lg">
                <img
                    src="../assets/备选.jpg"
                    alt="Shoes"
                    class="magnify-img object-contain object-center" />
              </div>
            </figure>
            <div class="card-body items-center text-center">
              <h2 class="card-title">博客标题 2</h2>
              <div>
                <p>日期 · 分类</p>
                <div class="flex">
                  <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" fill-rule="evenodd" clip-rule="evenodd" class="fill-current">
                    <path d="M22.672 15.226l-2.432.811.841 2.515c.33 1.019-.209 2.127-1.23 2.456-1.15.325-2.148-.321-2.463-1.226l-.84-2.518-5.013 1.677.84 2.517c.391 1.203-.434 2.542-1.831 2.542-.88 0-1.601-.564-1.86-1.314l-.842-2.516-2.431.809c-1.135.328-2.145-.317-2.463-1.229-.329-1.018.211-2.127 1.231-2.456l2.432-.809-1.621-4.823-2.432.808c-1.355.384-2.558-.59-2.558-1.839 0-.817.509-1.582 1.327-1.846l2.433-.809-.842-2.515c-.33-1.02.211-2.129 1.232-2.458 1.02-.329 2.13.209 2.461 1.229l.842 2.515 5.011-1.677-.839-2.517c-.403-1.238.484-2.553 1.843-2.553.819 0 1.585.509 1.85 1.326l.841 2.517 2.431-.81c1.02-.33 2.131.211 2.461 1.229.332 1.018-.21 2.126-1.23 2.456l-2.433.809 1.622 4.823 2.433-.809c1.242-.401 2.557.484 2.557 1.838 0 .819-.51 1.583-1.328 1.847m-8.992-6.428l-5.01 1.675 1.619 4.828 5.011-1.674-1.62-4.829z"></path>
                  </svg>
                  <p class="line-clamp-3 text-base leading-6 transition-all">这是博客的摘要，提供一些关键信息，帮助读者了解博客的内容。</p>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>

      <div class="w-[240px] bg-amber-400 flex-shrink-0 hidden lg:block rounded-xl">
        <div class="flex flex-col items-center justify-center gap-4">
          <img src="../assets/vue.svg" alt=""
               class="w-28 h-28"/>
          <p class="text-2xl font-black">吻鲸看日落</p>
          <p class="font-semibold">从来是疯魔到痴</p>
          <div class="flex justify-evenly w-full">
            <span class="flex items-center justify-center flex-col" v-for="info in Infos">
              <p class="font-medium">{{info.text}}</p>
              <p class="text-xl font-bold">{{info.num}}</p>
            </span>

          </div>
          <button class="bg-blue-500 w-2/3 p-1 rounded-xl text-white ">
            Follow Me
          </button>
        </div>
      </div>
    </div>

  </div>

  <div class="mt-3">
    <Pagination :data="items" :perPage="5" />
  </div>
</template>

<style scoped lang="less">

.magnify-container {
  position: relative;
  overflow: hidden;
  border-radius: 10px;
  img{
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
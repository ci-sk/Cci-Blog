<script setup>
import router from "../router/index.js";
import { ref, onMounted } from 'vue';
import "/src/assets/css/loading.less"

const numberOfBlocks = ref(5);


// 模拟加载过程
console.log('Loading...');
setTimeout(() => {
  // 跳转到首页
  router.push({path:"/index"}) // 替换为你的首页路径
  console.log('Loading complete, redirecting...');
}, 3000); // 3秒后跳转

</script>

<template>
  <div class="container">
    <div class="loading"><i></i><i></i><i></i><i></i></div>
  </div>

</template>


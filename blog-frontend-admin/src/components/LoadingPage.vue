<template>
    <div class="loading-container">
      <div 
        class="loader" 
        v-for="i in numberOfBlocks" 
        :key="i" 
        :style="{ animationDelay: `${(i - 1) * 0.2}s` }"
      ></div>
      <div class="loading-text">Loading, please wait...</div>
    </div>
  </template>
  
  <script setup>
  import router from "../router/index.js";
  import { ref, onMounted } from 'vue';
  const numberOfBlocks = ref(5);

  // 模拟加载过程
  console.log('Loading...');
  setTimeout(() => {
    // 跳转到首页
    router.push({path:"/index"}) // 替换为你的首页路径
    console.log('Loading complete, redirecting...');
  }, 3000); // 3秒后跳转

</script>
  

<style scoped>
.loading-container {
    display: flex;
    justify-content: center; /* 水平居中 */
    align-items: center;
    height: 100vh;
    background: linear-gradient(135deg, #f0f0f0, #e0e0e0);
    animation: fadeIn 1s ease-in-out;
    position: relative;
    overflow: hidden;
}

.loader {
    width: 60px;
    height: 60px;
    background-color: #3498db; /* 蓝色 */
    animation: bounce 0.6s infinite alternate; /* 确保跳动动画被应用 */
    border-radius: 10px; /* 圆角 */
    margin: 0 10px; /* 方块之间的间距 */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); /* 阴影效果 */
}

.loading-text {
    margin-top: 20px;
    font-size: 24px;
    color: #333;
    text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
}

@keyframes bounce {
    0% {
        transform: translateY(0);
    }
    100% {
        transform: translateY(-30px); /* 上跳高度 */
    }
}

@keyframes rotate {
    0% {
        transform: rotate(0deg);
    }
    100% {
        transform: rotate(360deg); /* 旋转效果 */
    }
}

@keyframes fadeIn {
    0% { opacity: 0; }
    100% { opacity: 1; }
}

/* 背景动画 */
.loading-container::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(255, 255, 255, 0.1);
    animation: wave 2s infinite linear;
    z-index: -1; /* 确保背景层在内容层下面 */
}

@keyframes wave {
    0% { transform: translateY(0); }
    50% { transform: translateY(-20px); }
    100% { transform: translateY(0); }
}
</style>
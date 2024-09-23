<script setup>

</script>

<template>
  <div class="box">
    <div class="left-card">
      <el-image style="width: 100%; height: 100%" fit="cover"
                src="public/01.jpg"
      ></el-image>
    </div>
    <div class="welcome-title">
      <div style="font-size: 30px;font-weight: bold">欢迎来到then-blog</div>
      <div style="margin-top: 10px">在这里你可以观看各种博客blog</div>
      <div style="margin-top: 5px">在这里你可以狠狠的学Java</div>
    </div>
    <div class="right-card">
      <router-view/>
    </div>
  </div>
</template>

<style scoped>
.box{
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  display: flex;
}
.left-card{
  flex: 1;
  background-color: black;
}
.welcome-title{
  position: absolute;
  bottom: 30px;
  left: 30px;
  color: #3eede7;
  text-shadow: 2px 2px 2px black;
}
.right-card{
  width: 400px;
  z-index: 1;
  background-color: white;
}
</style>
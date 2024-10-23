<script setup>
import 'animate.css';
import CommonAside from "../components/CommonAside.vue";
import CommonHeader from "../components/CommonHeader.vue";
import CommonTags from "../components/CommonTags.vue";

</script>
<template>
  <div class="common-layout">
        <el-container>
          <el-aside width="auto">
            <CommonAside/>
          </el-aside>
          <el-container class="common-content" style="height: 100vh">
            <!--头部-->
            <el-header style="padding: 0">
              <CommonHeader/>
            </el-header>

            <!-- 导航标签-->
            <CommonTags/>
            <el-main>
              <router-view/>
            </el-main>
          </el-container>
        </el-container>
  </div>
</template>

<style scoped lang="less">

.common-layout{
  background: var(--cci-bg-color);
}
</style>
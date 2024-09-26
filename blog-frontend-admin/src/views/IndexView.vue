<script setup>
import {logout} from "../net/index.js";
import router from "../router/index.js";
import { ref } from 'vue'
import { Expand } from "@element-plus/icons-vue";
import IndexMenu from "./index/components/IndexMenu.vue";
import 'animate.css';

function  userLogout(){
  logout(()=>router.push('/'))

}

function is_show(){
  console.log("显示")
  downMenu.value = !downMenu.value;
}
let downMenu=ref(false);
</script>
<template>
  <div class="common-layout">
    <el-container >
      <el-row :gutter="10"  v-show="!downMenu">
        <el-col  :xs="6" class="hidden-xs-only">
          <el-aside width="200px">
            <h3>Admin Bolg</h3>
            <IndexMenu/>
          </el-aside>
        </el-col>
      </el-row>

      <el-container class="common-content">
        <div class="el-down" v-show="downMenu" @click="is_show">

        </div>
        <el-aside class="el-aside-menu" v-show="downMenu">
        <IndexMenu/>
          </el-aside>
        <el-header class="header">
          <el-icon class="icon-down"><Expand @click="is_show"/></el-icon>
          <h3>Admin Bolg</h3>
          <el-button @click="userLogout">退出登录</el-button>
        </el-header>
        <router-view/>
      </el-container>
    </el-container>


  </div>



</template>


<style scoped lang="less">
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}
.header{
  display: flex;
  justify-content: space-between;
  background-color: #409EFF;
  align-items: center;
}
.icon-down{
  color: var(--el-bg-color);
}
.icon-down :hover{
  border-bottom:3px solid #67e8f9;
}
.el-down{
  border: 1px solid red;
  height: 100vh;
  width: 100%;
  position: absolute;
  z-index: 0;
  background-color: rgba(0, 0, 0, 0.46);
}
.common-content{
  position: relative;
}
.el-aside-menu{
  position: absolute;
}
</style>
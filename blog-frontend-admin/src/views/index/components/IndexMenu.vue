<script setup>

import {ChatDotSquare, ChatSquare, Document, EditPen, ElementPlus, House, Picture, User} from "@element-plus/icons-vue";

const tupleList =[House,User,Document,EditPen, ElementPlus,
  ChatSquare,ChatDotSquare, Picture
]


import {useRoute, useRouter} from "vue-router";
import router from "../../../router/index.js";

const route = useRoute();
const useRouter1 = useRouter()




 const itemList =route.matched[0].children

function activeIndex(){
  useRouter1.push(routeName+"/")
}

function activeUser(){
  useRouter1.push(routeName+"/user")
}
</script>

<template>

<!--  <el-menu-->
<!--      default-active="2"-->
<!--      class="el-menu-vertical-demo"-->
<!--      style="width: 200px;"-->
<!--      :router="true"-->
<!--  >-->
<!--    <el-sub-menu index="1">-->
<!--      <template #title >-->
<!--        <el-icon><House/></el-icon>-->
<!--        <span>博客首页</span>-->
<!--      </template>-->
<!--      <el-menu-item-group title="item zero">-->
<!--        <el-menu-item index="1-1" >item one</el-menu-item>-->
<!--        <el-menu-item index="1-2">item two</el-menu-item>-->
<!--      </el-menu-item-group>-->
<!--    </el-sub-menu>-->
<!--    <el-sub-menu index="2" @click="activeUser">-->
<!--      <template #title>-->
<!--        <el-icon><User /></el-icon>-->
<!--        用户管理-->
<!--      </template>-->
<!--      <el-menu-item-group title="item zero">-->
<!--        <el-menu-item index="1-1">item one</el-menu-item>-->
<!--        <el-menu-item index="1-2">item two</el-menu-item>-->
<!--      </el-menu-item-group>-->
<!--    </el-sub-menu>-->
<!--    <el-menu-item index="3">-->
<!--      <el-icon><Document /></el-icon>-->
<!--      <span>文章管理</span>-->
<!--    </el-menu-item>-->
<!--    <el-menu-item index="4">-->
<!--      <el-icon><EditPen /></el-icon>-->
<!--      <span>撰写博客</span>-->
<!--    </el-menu-item>-->
<!--    <el-menu-item index="5">-->
<!--      <el-icon><ElementPlus /></el-icon>-->
<!--      <span>分类管理</span>-->
<!--    </el-menu-item>-->
<!--    <el-menu-item index="6">-->
<!--      <el-icon><ChatSquare /></el-icon>-->
<!--      <span>留言管理</span>-->
<!--    </el-menu-item>-->
<!--    <el-menu-item index="7">-->
<!--      <el-icon><ChatDotSquare /></el-icon>-->
<!--      <span>评论管理</span>-->
<!--    </el-menu-item>-->
<!--    <el-menu-item index="8">-->
<!--      <el-icon><Picture /></el-icon>-->
<!--      <span>图片管理</span>-->
<!--    </el-menu-item>-->
<!--  </el-menu>-->
  <el-menu

      class="el-menu-demo"
      style="width: 200px;"
      :router="true"
  >
    <el-menu-item v-for="(item,index) in itemList" :index="item.path" :key="index" :route="{path:item.path}">
      <template #title>
        <el-icon>
          <component :is="tupleList[index]"/>
        </el-icon>
<!--            <el-icon>-->
<!--              {{ item.meta.icon }}-->
<!--            </el-icon>-->
        <span>{{item.meat.title}}</span>
      </template>
    </el-menu-item>
  </el-menu>
</template>

<style scoped>

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
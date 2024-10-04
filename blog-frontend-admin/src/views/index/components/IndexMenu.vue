<script setup>

import {ChatDotSquare, ChatSquare, Document, EditPen, ElementPlus, House, Picture, User} from "@element-plus/icons-vue";

const tupleList =[House,User,Document,EditPen, ElementPlus,
  ChatSquare,ChatDotSquare, Picture
]

import {useRoute, useRouter} from "vue-router";

const route = useRoute();

const itemList =route.matched[0].children

</script>

<template>
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
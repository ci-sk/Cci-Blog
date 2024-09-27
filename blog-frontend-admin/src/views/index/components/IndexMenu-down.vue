<script setup>
import { ref } from 'vue'

import {ChatDotSquare, ChatSquare, Document, EditPen, ElementPlus, House, Picture, User} from "@element-plus/icons-vue";
import {useRoute} from "vue-router";

const tupleList =[House,User,Document,EditPen, ElementPlus,
  ChatSquare,ChatDotSquare, Picture
]
const route = useRoute();
const itemList =route.matched[0].children


const isCollapse = ref(true)
</script>

<template>
  <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
<!--    <el-radio-button :value="false">收缩</el-radio-button>-->
<!--    <el-radio-button :value="true">收缩</el-radio-button>-->

  </el-radio-group>
  <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      :collapse="isCollapse"
      :router="true"
  >
      <el-menu-item  v-for="(item,index) in itemList"  :index="item.path" :route="{path:item.path}" >
          <el-icon>
            <component :is="tupleList[index]"/>
          </el-icon>
      </el-menu-item>
  </el-menu>
</template>

<style scoped>

</style>
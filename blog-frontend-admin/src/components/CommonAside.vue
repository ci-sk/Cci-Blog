<script setup>
import { computed } from "vue";
import MenuData from "../data/MenuData.js";
import { useCounterStore } from "../store/index.js";
import router from "../router/index.js";
import { useRoute } from "vue-router";

const route = useRoute();

const Store = useCounterStore();

const noChildren = computed(() => {
  return MenuData.filter((item) => !item.children);
});

const hasChildren = computed(() => {
  return MenuData.filter((item) => item.children);
});

const isCollapse = computed(() => {
  return Store.isCollapse;
});

function clickItem(item) {
  // 如果是父菜单且有子菜单，使用第一个子菜单的路径
  if (item.children && item.children.length > 0) {
    router.push(item.children[0].path);
    Store.SelectMenu(Store, item.children[0]);
  } else {
    router.push(item.path);
    Store.SelectMenu(Store, item);
  }
}
</script>

<template>
  <el-menu
    :default-active="route.path"
    class="el-menu-vertical-demo"
    :collapse="isCollapse"
    text-color="#fff"
    active-text-color="#FFD700"
  >
    <h3>{{ isCollapse ? "后台" : "通用后台管理系统" }}</h3>
    <el-menu-item
      @click="clickItem(item)"
      v-for="item in noChildren"
      :key="item.name + '-' + item.path"
      :index="item.path"
    >
      <el-icon>
        <component :is="item.icon" />
      </el-icon>
      <span>{{ item.label }}</span>
    </el-menu-item>
    <el-sub-menu
      v-for="item in hasChildren"
      :key="item.name + '-parent'"
      :index="item.name + ''"
    >
      <template #title>
        <el-icon>
          <component :is="item.icon" />
        </el-icon>
        <span slot="title">{{ item.label }}</span>
      </template>
      <el-menu-item-group
        class="item-group"
        v-for="subItem in item.children"
        :key="subItem.name + '-' + subItem.path"
      >
        <el-menu-item @click="clickItem(subItem)" :index="subItem.path">
          <el-icon>
            <component :is="subItem.icon" />
          </el-icon>
          {{ subItem.label }}
        </el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
  </el-menu>
</template>

<style lang="less" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 100%;
}
.el-menu {
  height: 100%;
  border-right: 1px solid var(--bPageBgColor);
  background-color: var(--Aside-PageBgColor);
  h3 {
    text-align: center;
    line-height: 48px;
    font-size: 16px;
    font-weight: 400;
    color: #fff;
  }
}
.item-group {
  background: var(--Aside-PageBgColor);
}

.el-menu-vertical-demo .el-menu-item:hover {
  background-color: #3498db; /* 悬浮时的背景色 */
  color: #ffffff; /* 悬浮时的文本颜色 */
}
</style>

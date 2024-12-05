<script setup>
import {Menu} from "@element-plus/icons-vue";
import {logout} from "../net/index.js";
import {useCounterStore} from "../store/index.js";
import router from "../router/index.js";
import {useDark, useToggle} from "@vueuse/core";
import {onMounted} from "vue";
import {useRoute} from "vue-router";

const isDark = useDark()

const toggleDark = () =>useToggle(isDark)

const route = useRoute()

const Store = useCounterStore()

const handleMenu = () => {
  Store.isCollapse =!Store.isCollapse
}

function  userLogout(){
  logout(()=> {
    router.push('/')
    Store.$reset()
  })
}

onMounted(()=>{
  window.onresize = function () {
    if(document.documentElement.clientWidth<=768) Store.isCollapse = true
    else Store.isCollapse = false
  };
})

</script>

<template>
  <div class="header-container">
    <div class="l-content">
      <el-icon :size="26" color="#409efc" @click="handleMenu"><Menu /></el-icon>
      <!-- 面包屑 -->
      <el-breadcrumb separator="/">
        <el-breadcrumb-item v-for="item in route.matched"
          :key="item.path"
          :to="item.path"
        >{{item.name}}
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="r-content">
      <el-switch v-model="isDark" @change="toggleDark"
      active-color="var(--el-fill-color-dark)"
      inactive-color="var(--el-color-primary)" />
      <el-dropdown>
                <span class="el-dropdown-link">
                    <img class="user" src="../assets/images/user.png"  alt=""/>
                </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item @click="userLogout">退出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<style lang="less" scoped>
.header-container {
  background: var(--header-bg-color); /* 深蓝色 */
  height: 60px;

  display: flex;
  justify-content: space-between;
  align-items:center;
  padding: 0 20px;

  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;

    .user {
      width: 40px;
      height: 40px;
      // 50%变圆形
      border-radius: 50%;
    }
  }
  .l-content {
    display: flex;
    align-items: center;

    .el-breadcrumb {
      margin-left: 20px;
    }

    :deep (.el-breadcrumb__item) {
      .el-breadcrumb__inner {
        &.is-link {
          color: #ccc;
        }
      }

      &:last-child {
        .el-breadcrumb__inner {
          color: #fff;
        }
      }

    }
  }
  .r-content{
  width: 100px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #fff;
  }
}

</style>
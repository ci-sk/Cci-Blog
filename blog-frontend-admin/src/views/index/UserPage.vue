<script setup>
import {ref,onMounted} from "vue";
import {DelAccount, getUserInfo} from "../../net/index.js";
import * as register_Time from "element-plus/es/components/countdown/src/utils";
import data from "bootstrap/js/src/dom/data.js";
import {ElMessage, ElMessageBox} from "element-plus";

const username = ref('')
const email = ref("");
const type = ref("");

const UserInfo = ref([]);

const userLogin = () => {
  getUserInfo((res) => {
    UserInfo.value = res;
    UserInfo.value.forEach((item) => {
      item.register_Time = formatTime(item.register_Time);
    })
  });
}

//删除用户
const delAccount = (uid) => {
  ElMessageBox.confirm('此操作将永久删除用户,是否继续', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
      DelAccount(uid, (res) => {
        console.log(uid,res);
        if (res.code === 200) {
          userLogin();
        }
      });
    }).catch(() => {
      ElMessage({
        type: 'info',
        message: '已取消删除'
      })
  })
}

onMounted(() => {
  userLogin();
})

const formatTime = (value) => {
  if (!value) return '';
  const date = new Date(value);
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const hours = date.getHours();
  const minutes = date.getMinutes();
  const seconds = date.getSeconds();
  return `${year}年${month}月${day}日 ${hours}:${minutes}:${seconds}`;
}


</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <div class="head">
          <el-input v-m odel="type" id="type" style="width: 240px;margin-right: 10px;" placeholder="请输入..."/>
          <el-button type="primary" plain>查询</el-button>
        </div>

      </el-header>
      <el-main>
        <el-table :data="UserInfo" style="width: 100%">
          <el-table-column prop="uid" label="编号" width="180" />
          <el-table-column prop="username" label="姓名" width="180" />
          <el-table-column prop="email" label="邮箱" width="180" />
          <el-table-column prop="register_Time" label="注册时间" width="240"/>
          <el-table-column prop="role" label="用户角色" width="180" />
          <el-table-column fixed="right" label="操作" min-width="120">
            <template #default="scope">
              <el-button type="danger" size="small" @click="delAccount(scope.row.uid)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
  .head{
    display: flex;
    justify-content: flex-end;
    align-items: center;
    padding: 20px;
    border-bottom: 1px solid #ccc;
  }
  /deep/
  .el-table__row{
    height: 56px;
  }
</style>
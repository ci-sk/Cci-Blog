<script setup>
import {ref,onMounted} from "vue";
import {AccountLimit, DelAccount, getAccountCount} from "../../net/account.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {Refresh} from "@element-plus/icons-vue";

const text = ref('')

const UserInfo = ref([{}]);

const page = ref(1)

const total = ref(0)


function rest(){
  text.value = '';
  getAccountCount((res)=>{
    total.value = res;
  })
  userLogin();
}


//初始化加载数据
onMounted(() => {
  rest()
})

//获取用户列表
const userLogin = () => {
  AccountLimit({
    text:text.value,
    page:page.value
  },(res) => {
    UserInfo.value = res;
  });
}

//删除用户
const delAccount = (uid) => {
  ElMessageBox.confirm('此操作将永久删除用户,是否继续', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
      DelAccount(uid, () => {
          userLogin();
      });
    }).catch(() => {
      ElMessage({
        type: 'info',
        message: '已取消删除'
      })
  })
}

//查询
const search = (text) => {
  AccountLimit({
    text:text,
    page:page.value}, (res) => {
      UserInfo.value = res;
  });
}

//翻页
function currentChange(val){
  page.value = val;
  AccountLimit({
    text:text.value,
    page:page.value
  },(data)=>{
    UserInfo.value = data;
  })
}


</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <div class="head">
          <el-input v-model="text" id="type" style="width: 240px;margin-right: 10px;" placeholder="请输入..."/>
          <el-button type="primary" @click="search(text)" plain>查询</el-button>
          <el-button :icon="Refresh" @click="rest">重置</el-button>
        </div>
      </el-header>
        <el-table class="el-table-user" :data="UserInfo" style="width: 100%">
          <el-table-column prop="uid" label="编号" width="240"/>
          <el-table-column prop="icon" label="头像" width="120">
            <template #default="scope">
              <el-image
                  style="width: 40px; height: 40px"
                  :src="scope.row.avatar"
                  fit="cover"
                  :preview-src-list="[scope.row.avatar]"
              />
            </template>
          </el-table-column>
          <el-table-column prop="username" label="姓名" width="240" />
          <el-table-column prop="email" label="邮箱" width="240" />
          <el-table-column prop="time" label="注册时间" width="240"/>
          <el-table-column prop="role" label="用户角色" width="240" />
          <el-table-column prop="url" label="链接" width="240">
            <template #default="scope">
              <el-link type="primary" :href="scope.row.website" target="_blank">{{ scope.row.website }}</el-link>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" min-width="120" align="center">
            <template #default="scope">
              <el-button type="danger" @click="delAccount(scope.row.uid)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div style="margin-top: 30px">
          <el-pagination background
                         layout="prev, pager, next"
                         :total="total"
                         @current-change="currentChange" />
        </div>
    </el-container>
  </div>
</template>

<style lang="less" scoped>
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
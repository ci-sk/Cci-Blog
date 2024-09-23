<script setup>
import {Lock, User} from "@element-plus/icons-vue"
import {reactive} from "vue";

const form = reactive({
  username:'',
  password:'',
  remember:false
})

</script>

<template>
  <div class="login-page">
    <div class="login-box">
      <div style="font-size: 25px;font-weight: bold">登录</div>
      <div style="font-size: 15px;color: #999999">在进入博客之前请先进行登录</div>
    </div>
    <div style="margin-top: 50px">
      <el-form-item v-model="form">
        <el-input v-model="form.username"
                  maxlength="11"
                  ype="text"
                  placeholder="用户名/邮箱">
          <template #prefix>
            <el-icon><User/></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="form.password"
                  maxlength="22"
                  type="password"
                  placeholder="密码">
          <template #prefix>
            <el-icon><Lock/></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-row>
        <el-col :span="12" style="text-align: left">
          <el-form-item>
            <el-checkbox  v-model="form.remember" label="记住我"/>
          </el-form-item>
        </el-col>
        <el-col :span="12" style="text-align: right">
          <el-link>忘记密码</el-link>
        </el-col>
      </el-row>
    </div>
    <div>

    </div>
  </div>
</template>

<style scoped>
.login-page{
  text-align: center;
  margin: 0 20px;
}
.login-box{
  margin-top: 150px;
}
</style>
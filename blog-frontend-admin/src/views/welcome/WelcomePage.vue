<script setup>
import {Lock, User} from "@element-plus/icons-vue"
import {reactive,ref} from "vue";
import {login} from "../../net/index.js";
import router from "../../router/index.js";

const formRef = ref();

const form = reactive({
  username:'',
  password:'',
  remember:false
})

const rule ={
  username:[
    {required:true,message:'请输入用户名'}
  ],
  password:[
    {required:true,message:'请输入密码'}
  ]
}

function userLogin(){
  formRef.value.validate((valid)=>{
      if(valid){
        login(form.username,form.password,form.remember,()=>router.push('loading'))
      }
  })
}
</script>

<template>
  <div class="login-page">
    <div class="login-box">
      <div style="font-size: 25px;font-weight: bold">登录</div>
      <div style="font-size: 15px;color: #999999">在进入博客之前请先进行登录</div>
    </div>
    <div style="margin-top: 50px">
      <el-form :model="form" :rules="rule" ref="formRef">
      <el-form-item prop="username">
        <el-input v-model="form.username"
                  maxlength="11"
                  ype="text"
                  placeholder="用户名/邮箱">
          <template #prefix>
            <el-icon><User/></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
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
          <el-form-item prop="remember">
            <el-checkbox  v-model="form.remember" label="记住我"/>
          </el-form-item>
        </el-col>
        <el-col :span="12" style="text-align: right">
          <el-link>忘记密码</el-link>
        </el-col>
      </el-row>
    </el-form>
    </div>
    <div style="margin-top: 40px">
      <el-button @click="userLogin" style="width: 270px" type="success" plain>立即登录</el-button>
    </div>
    <el-divider>
      <span style="font-size: 13px;color: grey">没有账户</span>
    </el-divider>
    <div>
      <el-button  style="width: 270px" type="primary" plain>立即注册</el-button>
    </div>
  </div>
</template>

<style scoped>
.login-page{
  text-align: center;
  margin: 0 30px;
  //margin-right: 20px;
}
.login-box{
  margin-top: 150px;
}
.el-input{
  margin-top: 10px;
  //width:0px;
}


</style>
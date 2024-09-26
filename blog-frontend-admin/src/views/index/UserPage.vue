<script setup>
import {ref} from "vue";
import { defineComponent } from "vue";
  const username = ref('')
</script>

<template>
<div>
  <el-row>
    <el-col :span="24">
      <el-card class="box-card">
        <template #header>
          <span>用户信息</span>
        </template>
        <el-form label-width="120px" label-position="left">
          <el-form-item label="用户名">
            <el-input v-model="username" disabled></el-input>
          </el-form-item>
          <el-form-item label="昵称">
           <el-/>
          </el-form-item>
        </el-form>
      </el-card>
    </el-col>
  </el-row>
</div>
</template>

<style scoped>

</style>
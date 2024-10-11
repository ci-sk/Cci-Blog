<script setup>
import { ref } from 'vue';
import {Plus, Refresh, Search} from "@element-plus/icons-vue";

const input = ref('');

</script>

<template>
  <el-form class="Tag-header">
    <div class="Tag-header-from">
      <el-form-item label="标签名">
        <el-input
            style="width: 240px"
            placeholder="标签名称"
            clearable
            v-model="input"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :icon="Search">搜索</el-button>
        <el-button :icon="Refresh">重置</el-button>
      </el-form-item>
    </div>
  </el-form>

  <el-card style="max-width: 100vw">
    <el-button type="success" :icon="Plus">新增</el-button>

    <el-table
        :data="tableData"
        border
        style="width: 100%;margin-top: 20px"
    >
      <el-table-column
          prop="name"
          label="标签名"
      />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary">编辑</el-button>
          <el-button type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>


</template>

<style scoped>

.Tag-header{
  margin-bottom: 20px;
  padding: 20px;
  border-radius: 10px;
  background-color: var(--cci-card-bg-color);
  .Tag-header-from{
    width: 520px;
    display: flex;
    gap: 20px;
  }
}

</style>
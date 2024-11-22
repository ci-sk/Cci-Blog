<script setup>
import {ref} from 'vue';

const siteName = ref('');
const recordNumber = ref('');
const description = ref('');
const siteUrl = ref('');


const saveSettings = () => {

  const settings = {
    siteName: siteName.value,
    recordNumber: recordNumber.value,
    description: description.value,
    siteUrl: siteUrl.value
  };
  // 发送请求保存设置
  // axios.post('/api/saveSettings', settings)
  //  .then(response => {
  //     // 处理保存成功的逻辑
  //     console.log('设置保存成功');
  //   })
  //  .catch(error => {
  //     // 处理保存失败的逻辑
  //     console.error('设置保存失败', error);
  //   });
};
// 初始化设置
const initSettings = () => {
  // 在这里处理初始化设置的逻辑
  // 你可以从后端获取设置数据或者从本地存储中获取
  // 这里只是一个示例，你需要根据你的实际需求进行修改
  const settings = {
    siteName: '默认网站名称',
    recordNumber: '默认备案号',
    description: '默认描述',
    siteUrl: '默认网站地址'
  };
  // 初始化设置
  siteName.value = settings.siteName;
  recordNumber.value = settings.recordNumber;
  description.value = settings.description;
  siteUrl.value = settings.siteUrl;
};

initSettings()
</script>

<template>
  <div>
    <el-form :label-width="'100px'" :inline="true" class="responsive-form">
      <el-form-item label="网站头像">
        <el-upload action="#" list-type="picture-card" :show-file-list="false">
          <i class="el-icon-plus"></i>
          <div>上传头像</div>
        </el-upload>
      </el-form-item>
      <el-form-item label="游客头像">
        <el-upload action="#" list-type="picture-card" :show-file-list="false">
          <i class="el-icon-plus"></i>
          <div>上传头像</div>
        </el-upload>
      </el-form-item>
      <el-form-item label="网站名称">
        <el-input v-model="siteName"></el-input>
      </el-form-item>
      <el-form-item label="备案号">
        <el-input v-model="recordNumber"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input type="textarea" v-model="description"></el-input>
      </el-form-item>
      <el-form-item label="网站地址">
        <el-input v-model="siteUrl"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveSettings">保存设置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>
.responsive-form {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.responsive-form .el-form-item {
  flex: 1 1 45%; /* 使每个表单项在小屏幕上占据45%的宽度 */
  margin-bottom: 20px; /* 添加底部间距 */
}
</style>

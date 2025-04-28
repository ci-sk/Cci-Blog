<script setup>
import { ref } from 'vue';
import {getPromise, post} from "../../net/index.js";
import {ElMessage} from "element-plus";

const siteIcon = ref('');
const userIcon = ref('');

const fromData = ref({
  siteName: '',
  recordNumber: '',
  description: '',
  siteUrl: '',
  siteIcon: '',
  userIcon: ''
});
const changeUpdate= (file)=>{
  // 或者使用 URL.createObjectURL(file.raw) 来创建一个 URL
  const fileUrl = URL.createObjectURL(file.raw);
  console.log(fileUrl);
  // 将文件路径或 URL 存储在 imgBase 中，以便在模板中使用
  return fileUrl;
}

const changeUserIcon = (file)=>{
  userIcon.value = changeUpdate(file)
}

const changeSiteIcon = (file)=>{
  siteIcon.value = changeUpdate(file)
}

async function getSystemSettings(){
  return getPromise("api/settings")
}

const saveSettings = () => {
  fromData.value.siteIcon = siteIcon.value
  fromData.value.userIcon = userIcon.value
  post("/api/UpdateSettings", fromData.value,()=>{
    ElMessage.success("保存成功")
  },()=>{})
};

// 初始化设置
const initSettings = async () => {

  let settings = await getSystemSettings().then((res) => {
    return res;
  });
  siteIcon.value = settings.siteIcon
  userIcon.value = settings.userIcon

  fromData.value = settings;
};

initSettings()
</script>

<template>
  <div>
    <el-form :label-width="'100px'" :inline="true" class="responsive-form">
      <el-form-item label="网站头像">
        <el-upload
            class="avatar-uploader"
            :show-file-list="false"
            :on-change="changeSiteIcon"
        >
          <img v-if="siteIcon" :src="siteIcon" class="avatar" style="width: 240px" alt="img"/>
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="游客头像">
        <el-upload
            class="avatar-uploader"
            :show-file-list="false"
            :on-change="changeUserIcon"
        >
          <img v-if="userIcon" :src="userIcon" class="avatar" style="width: 240px" alt="ig"/>
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="网站名称">
        <el-input v-model="fromData.siteName"></el-input>
      </el-form-item>
      <el-form-item label="备案号">
        <el-input v-model="fromData.recordNumber"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input type="textarea" v-model="fromData.description"></el-input>
      </el-form-item>
      <el-form-item label="网站地址">
        <el-input v-model="fromData.siteUrl"></el-input>
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
  flex: 1 1 45%;
  /* 使每个表单项在小屏幕上占据45%的宽度 */
  margin-bottom: 20px;
  /* 添加底部间距 */
}
</style>

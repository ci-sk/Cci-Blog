<script setup>
import { ref,reactive } from 'vue';
import { MdEditor } from 'md-editor-v3';

import 'md-editor-v3/lib/style.css';

import {useDark} from "@vueuse/core";
import {uploadFile} from "../../net/index.js";
import {ElMessage} from "element-plus";
import {Plus} from "@element-plus/icons-vue";

const text = ref('# Hello Editor');

const form = reactive({
       name:"",
       desc:"",
       image:"",
       title:""
})

const imageUrl = ref('')

const isDark = useDark();

const onUploadImg = async (files, callback) => {

  const formData = new FormData();
  formData.append("file", files[0]);
  uploadFile(formData.get("file"),(res)=>{
    const data = [
      { url: res,
        alt: "Image",
      }
    ]
    callback(
        data.map((item)=>({
          url: item.url,
          alt: item.alt,
        }))

    );
  })
}

const beforeUpload = (file)=>{
  console.log("###",file)
  const formData = new FormData();
  formData.append("file", file);
  uploadFile(formData.get("file"),(res)=>{
    imageUrl.value = res;
    console.log("SS",form.image)
  })
}


</script>

<template>
  <el-form :model="form" label-width="auto" style="max-width:800px" >

    <el-form-item label="文章标题">
      <el-input v-model="form.title" style="width: 240px"></el-input>
    </el-form-item>

    <el-form-item label="标题图">
      <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/api/file/upload"
          :show-file-list="false"
          :before-upload="beforeUpload"
      >
        <img v-if="imageUrl" :src="imageUrl" class="avatar"  alt=""/>
        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
      </el-upload>
    </el-form-item>

    <el-form-item label="文章简介">
      <el-input v-model="form.desc" style="width: 240px" type="textarea" />
    </el-form-item>




  </el-form>

  <MdEditor
            :theme="isDark?'dark':'light'"
            v-model="text"

            @onUploadImg="onUploadImg"
  />
</template>

<style scoped>

.avatar-uploader .avatar {
  width: 164px;
  height:94px;
}

.avatar-uploader{
  background-color: var(--el-bg-color);
  width: 164px;
  height:94px;

}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 164px;
  height:94px;
  text-align: center;
}
</style>
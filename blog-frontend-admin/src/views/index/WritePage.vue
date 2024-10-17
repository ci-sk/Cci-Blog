<script setup>
import {onMounted, reactive, ref} from 'vue';
import {MdEditor} from 'md-editor-v3';

import 'md-editor-v3/lib/style.css';

import {useDark} from "@vueuse/core";
import {uploadFile} from "../../net/index.js";
import {Plus} from "@element-plus/icons-vue";
import {getTag} from "../../net/tag.js";

const text = ref('# Hello Editor');

const form = reactive({
       title:"",
       desc:"",
       image:"",
       tags:[],
       text:""
})

const imgBase = ref();

const isDark = useDark();

const onUploadImg = async (files, callback) => {
  const formData = new FormData();
  formData.append("file", files[0]);
  uploadFile(formData.get("file"),(res)=>{
    console.log(res);
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

const beforeUpload = async (file)=>{
  form.image = file;
  let reader = new FileReader();
  reader.readAsDataURL(file);
  console.log("@@@",reader)

  reader.onload = () => {
    imgBase.value = reader.result;
  }
  console.log(imgBase.value)

}

const handleAvatarSuccess = (file)=>{
  const formData = new FormData();
  formData.append("file", file);
  uploadFile(formData.get("file"),(res)=>{
    form.image  = res;
  })
}

const buttonRef = ref()

const tags = ref([]);

const pTags = ref([])

const TagUser = () => {
  getTag((res) => {
    tags.value = res;
  });
};

const add = (item)=>{
  pTags.value = [...new Set([...pTags.value, item])];
  form.tags = [...new Set([...form.tags, item.tagName])];
  console.log(form);
}

const submitForm = ()=>{
  handleAvatarSuccess(form.image);

}

onMounted(() => {
  TagUser();
})
</script>

<template>
  <el-form
           :model="form"
           label-width="auto"
           style="max-width:85%"
           :inline="true"
           label-position="top"
  >

      <div style="width: 240px">
        <el-form-item label="文章标题" >
        <el-input  v-model="form.title" style="width: 240px"></el-input>
        </el-form-item>

        <el-form-item label="文章简介">
          <el-input v-model="form.desc" style="width: 240px" type="textarea" />
        </el-form-item>
      </div>

      <el-form-item label="标题图" style="margin-left: 84px" >
        <el-upload
            class="avatar-uploader"
            action="http://localhost:8080/api/file/upload"
            :show-file-list="false"
            :before-upload="beforeUpload"
        >
          <img v-if="imgBase" :src="imgBase" class="avatar"  alt=""/>
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>

    <el-form-item label="标签" style="width: 240px">
      <el-tag
          v-for="(item,index) in pTags"
          :key="index"
          closable
          @close="pTags.splice(index,1)"
          style="margin-right: 2px"
      >
        {{item.tagName}}
      </el-tag>

      <el-button ref="buttonRef">
        添加标签
      </el-button>

      <el-popover
          :virtual-ref="buttonRef"
          trigger="click"
          title="标签"
          virtual-triggering
          width="280px"
      >
      <el-input placeholder="enter添加自定义标签">

      </el-input>
        <el-tag
            v-for="(item,index) in tags"
            :key="index"
            style="margin: 2px"
            @click="add(item)"
        >
          {{item.tagName}}
        </el-tag>
      </el-popover>
    </el-form-item>
    <el-button type="primary" plain @click="submitForm ">
      提交
    </el-button>

  </el-form>

  <MdEditor
            :theme="isDark?'dark':'light'"
            v-model="form.text"
            @onUploadImg="onUploadImg"
  />
</template>

<style scoped>

.el-form--inline.el-form--label-top{
  display: flex;
  justify-content: space-between;
}

.avatar-uploader .avatar {
  width: 176px;
  height:110px;
}

.avatar-uploader{
  background-color: var(--el-bg-color);
  width: 176px;
  height:110px;
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 176px;
  height:110px;
  text-align: center;
}
</style>
<script setup>
import {onMounted, reactive, ref} from 'vue';
import {MdEditor} from 'md-editor-v3';

import 'md-editor-v3/lib/style.css';

import {useDark} from "@vueuse/core";
import {get, uploadFile} from "../../net/index.js";
import {Plus} from "@element-plus/icons-vue";
import {getTag, insertTag} from "../../net/tag.js";
import {insertArticle} from "../../net/article.js";
import {ElMessage} from "element-plus";

const imgBase = ref();

const isDark = useDark();

const uploadRef = ref();

const buttonRef = ref()

const tags = ref([]);

const pTags = ref([])

const Tval = ref('')


const form = reactive({
  title:"",
  desc:"",
  img_url:"",
  tags:[],
  content:"# Hello Editor",
  del:0
})

//渲染标签
const TagUser = () => {
  getTag((res) => {
    tags.value = res;
  });
};

//添加标签去页面
const addTags = ()=>{
  if(Tval.value){
    pTags.value = [...new Set([...pTags.value, {tagName:Tval.value}])];
    form.tags = [...new Set([...form.tags, Tval.value])];
    Tval.value = "";
  }
}

//md 编辑器上传图片
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

//添加表单标签
const add = (item)=>{
  pTags.value = [...new Set([...pTags.value, item])];
  form.tags = [...new Set([...form.tags, item.tagName])];
  console.log(form);
}

// 上传图片(不进云存储)
const changeUpload = (file,fileList)=>{
  // 或者使用 URL.createObjectURL(file.raw) 来创建一个 URL
  const fileUrl = URL.createObjectURL(file.raw);
  console.log(fileUrl);
  // 将文件路径或 URL 存储在 imgBase 中，以便在模板中使用
  imgBase.value = fileUrl;
}

//自定义图片上传
function uploadRequest (req)  {
  return new Promise((resolve)=>{
    const formData = new FormData();
    formData.append("file", req.file);
     uploadFile(formData.get("file"),(res)=>{
      form.img_url  = res;
      console.log("存入表单",form);
      resolve(res);
    })
  })
}

//表单提交上传图片
const submitUpload =  () => {
    uploadRef.value.submit();
    console.log("上传完",form)
}

//上传标签
const submitTag =  ()=>{
  for (let i =0;i<pTags.value.length;i++){
    if(!pTags.value[i].tid){
      console.log(pTags.value[i].tagName);
       insertTag(pTags.value[i].tagName,(res)=>{
        ElMessage.success("添加成功");
      })
    }
  }
}

//提交表单
const submitForm = ()=>{
  submitUpload()
}

//图片上传成功后上传到数据库
const success = ()=>{
  console.log("上传成功",form);

  submitTag();

  insertArticle(form,(res)=>{
    ElMessage.success("添加成功");
    console.log(res);
  })
}

onMounted(()=>{
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
            ref="uploadRef"
            class="avatar-uploader"
            :on-success="success"
            :http-request="uploadRequest"
            :show-file-list="false"
            :auto-upload="false"
            :on-change="changeUpload"
        >
          <img v-if="imgBase" :src="imgBase" class="avatar"  alt=""/>
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>

    <el-form-item label="标签" style="width:160px">
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
      <el-input placeholder="enter添加自定义标签" v-model="Tval" @keyup.enter="addTags">
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

    <el-form-item label="状态" style="width:277px">
      <el-radio-group v-model="form.del">
        <el-radio :value=0 border>待处理</el-radio>
        <el-radio :value=1 border>已删除</el-radio>
        <el-radio :value=2 border>已发布</el-radio>
        <el-radio :value=3 border>草稿箱</el-radio>
      </el-radio-group>
    </el-form-item>

    <el-button type="primary" plain @click="submitForm">
      提交
    </el-button>

  </el-form>

  <MdEditor
            :theme="isDark?'dark':'light'"
            v-model="form.content"
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
.el-radio{
  background-color: var(--el-bg-color);
  margin-bottom: 10px;
}
</style>
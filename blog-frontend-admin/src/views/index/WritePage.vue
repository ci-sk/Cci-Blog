<script setup>
import { ref } from 'vue';
import { MdEditor } from 'md-editor-v3';

import 'md-editor-v3/lib/style.css';

import {useDark} from "@vueuse/core";
import {uploadFile} from "../../net/index.js";
import {ElMessage} from "element-plus";
import FIleUpdate from "../../components/FIleUpdate.vue";

const text = ref('# Hello Editor');

const isDark = useDark();

const onUploadImg = async (files, callback) => {

  const formData = new FormData();
  formData.append("file", files[0]);
  uploadFile(formData.get("file"),(res)=>{

    const data = [
      { url: "http://"+res,
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

</script>

<template>
  <MdEditor
            :theme="isDark?'dark':'light'"
            v-model="text"
            @onUploadImg="onUploadImg"
  />
  <FIleUpdate/>
</template>

<style scoped>
</style>
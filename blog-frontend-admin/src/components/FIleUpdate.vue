<script setup>
import axios from "axios";
import { ElButton } from "element-plus";

const uploadFile = async (options) => {
  console.log(options.file);
  const formData = new FormData();
  formData.append("file", options.file);
  axios
    .post(
      "api/file/upload",
      {
        multipartFile: formData.get("file"),
      },
      {
        headers: {
          Authorization:
            "Bearer " +
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiYWRtaW4iLCJpZCI6MSwiZXhwIjoxNzI5MDQ3ODQ4LCJpYXQiOjE3Mjg0NDMwNDgsImp0aSI6ImY5YmNiZThkLWY5YmItNDI3MC04N2UyLTAxNzIwZTM4NmI3NiIsImF1dGhvcml0aWVzIjpbIlJPTEVfYWRtaW4iXX0.HXkAwrJ5m0QuyO7RzrgIwfqd8kXJlWRKWgTzZeVkPe4",
          "Content-Type": "multipart/form-data",
        },
      }
    )
    .then((res) => {
      console.log(res, "@@@");
      console.log(res.data, "@@@");
      console.log("success");
      return res.data;
    });
};
</script>

<template>
  <ElForm>
    <ElFormItem>
      <ElUpload action="" :http-request="uploadFile">
        <ElButton type="primary">点击上传文件</ElButton>
      </ElUpload>
    </ElFormItem>
  </ElForm>
</template>

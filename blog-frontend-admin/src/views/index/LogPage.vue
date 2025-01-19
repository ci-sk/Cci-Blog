<script setup>
import {ref} from 'vue'
import {get} from "../../net/index.js";
import {Delete} from "@element-plus/icons-vue";

const logs = ref([])

const getLog = ()=>{
  get("api/get/requestLog",(res)=>{
    logs.value = res
    console.log(logs.value)
  })
}

const handleButtonClick = () => {
  // 处理按钮点击事件的逻辑
  // console.log('按钮被点击了');
  get("/api/clear/log",()=>{
    getLog()
  })
};

getLog()


</script>

<template>
  <el-card style="max-width: 100vw; height:85vh; overflow: auto;">
    <template #header>
      <div class="card-header">
        <span>日志</span>
      </div>
    </template>
    <div v-for="(requestLog, index) in logs" :key="index" class="log-item">
      <p>
        <span>{{requestLog.time}}</span>
        <span :style="{ color: requestLog.status === 200 ? 'green' : '#FFD700' }">{{ requestLog.status }}</span>
        <span>请求结果:</span>
        <span style="color: #3366CC;">{{ requestLog.url }}</span>
        <span>处理时间:</span>
        <span style="color:var(--cci-log-ms-color) ">{{ requestLog.processingTime }}ms</span>
      </p>
      <el-icon
          style="position: absolute; bottom: 100px; right: 100px;cursor: pointer;"
          @click="handleButtonClick"
      ><Delete /></el-icon>
    </div>
  </el-card>
</template>

<style scoped>
.log-item p span {
  display: inline-block;
  //white-space: nowrap;
  overflow: hidden;
  //text-overflow: ellipsis;
  margin: 0 10px;
}
</style>
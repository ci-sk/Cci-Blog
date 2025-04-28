<script setup>
import {Refresh, Search} from "@element-plus/icons-vue";
import {ref,onMounted} from "vue";
import {ArticleLimit} from "../../net/article.js";
import {getCountMessage, getLimitMessage} from "../../net/message.js";

const MessageInfo = ref([])

const input  = ref('')

const total = ref(1)

const page = ref(1)

const search = ()=>{
  getLimitMessage({
    text:input.value,
    page:page.value
  }, (res) => {
    MessageInfo.value = res;
    total.value = MessageInfo.value.length;
  });
}


const SelectComment =()=> {
  getLimitMessage({
    text:input.value,
    page:page.value
  },(res) => {
    MessageInfo.value  = res
  })
}

const currentChange = (val)=>{
  page.value = val;
  ArticleLimit({text:input.value,page:page.value},(res) => {
    MessageInfo.value = res;
  })
}

const init = ()=>{
  SelectComment()
  getCountMessage((data)=>{
    total.value = data
  })
  input.value = ''
}

onMounted(()=>{
  init()
})

</script>

<template>
  <div>
    <el-form class="Tag-header">
      <div class="Tag-header-from">
        <el-form-item label="留言">
          <el-input
              style="width: 240px"
              placeholder="留言"
              v-model="input"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="search">搜索</el-button>
          <el-button :icon="Refresh" @click="init">重置</el-button>
        </el-form-item>
      </div>
    </el-form>

    <el-card style="max-width: 100vw">

      <el-table :data="MessageInfo"
                border
                row-key="cid"
                style="width: 100%;margin-top: 20px"
      >
        <el-table-column prop="mid" label="序号" align="center"/>
        <el-table-column prop="username" label="评论用户" align="center"/>
        <el-table-column prop="content" label="内容" align="center"/>
        <el-table-column prop="time" label="时间" align="center"/>
      </el-table>


      <div style="margin-top: 30px">
        <el-pagination background layout="prev, pager, next"
                       :total="total"
                       @current-change="currentChange" />
      </div>
    </el-card>
  </div>

  <!--    <DemoPage/>-->

</template>

<style scoped>
.Tag-header{
  margin-bottom: 20px;
  padding: 20px;
  border-radius: 10px;
  .Tag-header-from{
    width: 520px;
    display: flex;
    gap: 20px;
  }
}
</style>
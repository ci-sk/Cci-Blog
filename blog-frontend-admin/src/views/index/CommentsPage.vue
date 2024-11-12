<script setup>
import { Refresh, Search} from "@element-plus/icons-vue";
import {getCountComment, getLimitComment} from "../../net/comment.js";
import {ref,onMounted} from "vue";
import {changeTime, getTags} from "../../uilt/index.js";
import {ArticleLimit} from "../../net/article.js";

const CommentInfo = ref([])

const input  = ref('')

const total = ref(1)

const page = ref(1)

const search = ()=>{
  getLimitComment({
    text:input.value,
    page:page.value
  }, (res) => {
    CommentInfo.value = res;
    CommentInfo.value = changeTime(CommentInfo.value);
    total.value = CommentInfo.value.length;
  });
}


const SelectComment =()=> {
  getLimitComment({
    text:input.value,
    page:page.value
  },(res) => {
    CommentInfo.value  = res
    CommentInfo.value = changeTime(CommentInfo.value)
    CommentInfo.value= CommentInfo.value.filter((item)=>{
          return item.reply_cid === null
        })
  })
}


const currentChange = (val)=>{
  page.value = val;
  ArticleLimit({text:input.value,page:page.value},(res) => {
    CommentInfo.value = res;
    CommentInfo.value =getTags(CommentInfo.value)
    CommentInfo.value = changeTime(CommentInfo.value);
  })
}

const init = ()=>{
  SelectComment()
  getCountComment((data)=>{
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
        <el-form-item label="评论">
          <el-input
              style="width: 240px"
              placeholder="评论"
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

      <el-table :data="CommentInfo"
                border
                row-key="cid"
                style="width: 100%;margin-top: 20px"
                :tree-props="{ children: 'children'}"
      >
        <el-table-column prop="cid" label="序号" align="center"/>
        <el-table-column prop="username" label="评论用户" align="center"/>
        <el-table-column prop="reply_username" label="回复用户" align="center"/>
        <el-table-column prop="title" label="所属文章" align="center"/>
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
  background-color: var(--cci-card-bg-color);
  .Tag-header-from{
    width: 520px;
    display: flex;
    gap: 20px;
  }
}
</style>
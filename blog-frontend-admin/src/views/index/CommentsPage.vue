<script setup>
import {Plus, Refresh, Search} from "@element-plus/icons-vue";
import {getComment, getCountComment, getLimitComment} from "../../net/comment.js";
import {ref,onMounted,reactive} from "vue";
import {changeTime, getTags} from "../../uilt/index.js";
import {ArticleLimit} from "../../net/article.js";
import DemoPage from "./DemoPage.vue";

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
  })
}


const fun = (arr) =>{
  let  reply = []
  let id;
  console.log("arr",arr)
  arr.forEach((item)=>{
    if(item.reply_cid !== null){
      id = item.aid;
      console.log("id",item)
      reply.push(item)
    }
    console.log("@@@",reply)

  })
  reply = reply.filter((item)=>{
    return item.aid === id
  })
  arr.forEach((item)=>{
    reply.forEach((item1)=>{
      if(item.cid === item1.reply_cid){
        item.reply = item1

        item.hasChildren = true
      }
    })
  })
  return arr
  //     .filter((item)=>{
  //   return item.reply_cid === null
  // })
}


const currentChange = (val)=>{
  page.value = val;
  ArticleLimit({text:input.value,page:page.value},(res) => {
    CommentInfo.value = res;
    CommentInfo.value =getTags(CommentInfo.value)
    CommentInfo.value = changeTime(CommentInfo.value);
  })
}

const load = (tree,treeNode,resolve)=>{
  tree.children.map((item)=>{
    console.log(item)
  })
  setTimeout(()=>{
    resolve([
      {
        cid: 31,
        time: '2016-05-01',
        name: 'wangxiaohu',
        address: 'No. 189, Grove St, Los Angeles',
      },
      {
        id: 32,
        date: '2016-05-01',
        name: 'wangxiaohu',
        address: 'No. 189, Grove St, Los Angeles',
      },

    ])
  },222)
}


const init = ()=>{
  SelectComment()
  getCountComment((data)=>{
    console.log(data)
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
      >
        <el-table-column prop="cid" label="序号" align="center"/>
        <el-table-column prop="username" label="评论用户" align="center"/>
        <el-table-column prop="reply_username" label="回复用户" align="center"/>
        <el-table-column prop="title" label="所属文章" align="center"/>
        <el-table-column prop="content" label="内容" align="center"/>
        <el-table-column prop="time" label="时间" align="center"/>
      </el-table>

<!--      <el-table-->
<!--          :data="tableData"-->
<!--          style="width: 100%; margin-bottom: 20px"-->
<!--          row-key="id"-->
<!--          border-->
<!--          default-expand-all-->
<!--      >-->
<!--        <el-table-column prop="date" label="Date" sortable />-->
<!--        <el-table-column prop="name" label="Name" sortable />-->
<!--        <el-table-column prop="address" label="Address" sortable />-->
<!--      </el-table>-->


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
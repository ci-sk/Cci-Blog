<script setup>
import {Plus, Refresh, Search} from "@element-plus/icons-vue";
import {getComment} from "../../net/comment.js";
import {ref,onMounted} from "vue";
import {changeTime} from "../../uilt/index.js";


const CommentInfo = ref([])

const input  = ref('')

const total = ref()

const search = ()=>{}

const SelectComment =()=> {
  getComment((res) => {
    console.log(res)
    CommentInfo.value  = res
    CommentInfo.value = changeTime(CommentInfo.value)
  })
}
const currentChange = ()=>{}
const init = ()=>{
  SelectComment()
}

  onMounted(()=>{
    init()
  })

</script>

<template>
  <el-form class="Tag-header">
    <div class="Tag-header-from">
      <el-form-item label="标签名">
        <el-input
            style="width: 240px"
            placeholder="标签名称"
            v-model="input"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :icon="Search" @click="search">搜索</el-button>
        <el-button :icon="Refresh" @click="input = ''">重置</el-button>
      </el-form-item>
    </div>
  </el-form>

  <el-card style="max-width: 100vw">

    <el-table
        :data="CommentInfo"
        border
        style="width: 100%;margin-top: 20px"
    >
      <el-table-column
          prop="cid"
          label="序号"
      />
      <el-table-column
          prop="username"
          label="评论用户"
      />
      <el-table-column
          prop="reply_username"
          label="回复用户"
      />
      <el-table-column prop="title" label="所属文章"/>
      <el-table-column prop="content" label="内容"/>
      <el-table-column prop="time" label="时间"/>
<!--      <el-table-column label="操作" align="center">-->
<!--        <template #default="scope" >-->
<!--          <el-button type="danger" @click="dTag(scope.row.tid)">删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>
    <div style="margin-top: 30px">
      <el-pagination background layout="prev, pager, next"
                     :total="total"
                     @current-change="currentChange" />
    </div>
  </el-card>
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
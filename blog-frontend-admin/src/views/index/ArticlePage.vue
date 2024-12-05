<script setup>
import {Plus, Refresh, Search} from "@element-plus/icons-vue";
import {ref,onMounted} from "vue"
import {getTag} from "../../net/tag.js";
import {ArticleLimit, DeleteArticle, getArticle, getArticleCount} from "../../net/article.js";
import {changeTime, getTags} from "../../uilt/index.js";
import router from "../../router/index.js";
import {useCounterStore, useUpDataArt} from "../../store/index.js";
import {ElMessage, ElMessageBox} from "element-plus";
const Store = useCounterStore()

const Art = useUpDataArt()

const input = ref('');

const tag = ref('');

const options = ref([])

const ArtInfo = ref([''])

const page = ref(1)

const total = ref(0)

function clickItem(){
  router.push(`/article/write`)
  Store.SelectMenu(Store,{
    name:"添加文章",
    label:"添加文章",
    path:"/article/write"
  })
}

const update = (item)=>{
  Art.ArtForm = item;
  router.push({
    path:"/article/write",
})
  Store.SelectMenu(Store,{
    name:"修改文章",
    label:"修改文章",
    path:`/article/write`
  })
}

function dArt(aid){
  ElMessageBox.confirm('此操作将永久删除标签,是否继续', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    DeleteArticle(aid, (data) => {
      getArtInfo()
    });
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '已取消删除'
    })
  })
}

const currentChange = (val)=>{
  page.value = val;
  ArticleLimit({text:input.value,page:page.value},(res) => {
    ArtInfo.value = res;
    ArtInfo.value =getTags(ArtInfo.value)
    ArtInfo.value = changeTime(ArtInfo.value);
  })
}

getTag((res) => {
    options.value = res;
  });

const getArtInfo = async  () => {
  ArticleLimit({text:"",page:1},(res) => {
    ArtInfo.value = res;
    ArtInfo.value =getTags(ArtInfo.value)
    ArtInfo.value = changeTime(ArtInfo.value);
  })
}


const search = (val) => {
  ArticleLimit({text:val,page:page.value}, (res) => {
    ArtInfo.value = res;
    ArtInfo.value =getTags(ArtInfo.value)
    ArtInfo.value = changeTime(ArtInfo.value);
    total.value = ArtInfo.value.length;
  });
}

const searchText = ()=>{
  search(input.value)
}


const searchTag = ()=>{
  search(tag.value)
}

const init = () => {
  tag.value = '';
  input.value = '';
  getArtInfo()
  getArticleCount((res)=>{
    total.value = res;
  })
}

onMounted(()=>{
  init()
})


</script>

<template>
  <el-form
      class="Tag-header"
  >
    <div class="Tag-header-from">
      <el-form-item label="文章名称">
        <el-input
            style="width: 240px"
            placeholder="文章名称"
            v-model="input"
        />
      </el-form-item>
      <el-form-item label="标签名">
        <el-select v-model="tag" placeholder="标签名" style="width: 240px" >
          <el-option
              v-for="item in options"
              :key="item.tid"
              :label="item.tagName"
              :value="item.tagName"
              @click="searchTag"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" :icon="Search" @click="searchText">搜索</el-button>
        <el-button :icon="Refresh" @click="init">重置</el-button>
      </el-form-item>
    </div>
  </el-form>

  <el-card style="max-width: 100vw">
    <el-button type="success" :icon="Plus" @click="clickItem()">新增</el-button>

    <el-table
        :data="ArtInfo"
        border
        style="width: 100%;margin-top: 20px"
    >
      <el-table-column prop="img_url"  label="文章封面" width="200" align="center">
        <template #default="scope">
          <img class="tup" :src="scope.row.img_url" alt="">
        </template>
      </el-table-column>
      <el-table-column prop="title" label="文章名称" align="center"/>
      <el-table-column label="标签名" align="center">
        <template #default="scope">
          <el-tag v-for=" item in scope.row.tags" style="margin-right: 5px;">
            {{item}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="time" label="创建时间" align="center"/>
      <el-table-column label="状态" align="center">
        <template #default="scope" >
          <el-tag v-if="scope.row.del === 0">待处理</el-tag>
          <el-tag v-if="scope.row.del === 1" type="warning">已下架</el-tag>
          <el-tag v-if="scope.row.del === 2" type="success">已发布</el-tag>
          <el-tag v-if="scope.row.del === 3" type="info">草稿箱</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope" >
          <el-button type="primary" @click="update(scope.row)">修改</el-button>
          <el-button type="danger" @click="dArt(scope.row.aid)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 30px">
      <el-pagination background layout="prev, pager, next"
                     :total="total"
                     @current-change="currentChange" />
    </div>
  </el-card>
</template>
<style>
.tup{
  width: 176px;
  height: 110px;
}
</style>
<style scoped>
.Tag-header{
  margin-bottom: 20px;
  padding: 20px;
  border-radius: 10px;
  /* background-color: var(--cci-card-bg-color); */
  .Tag-header-from{
    min-width: 850px;
    display: flex;
    gap: 20px;
  }
}

:deep(.cell){
  overflow: visible !important;
}
.el-tag{
  height: 32px;
}
</style>
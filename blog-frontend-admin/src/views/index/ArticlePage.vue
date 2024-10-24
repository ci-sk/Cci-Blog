<script setup>
import {Plus, Refresh, Search} from "@element-plus/icons-vue";
import {ref,onMounted} from "vue"
import {DelTag, getTag} from "../../net/tag.js";
import {DeleteArticle, getArticle} from "../../net/article.js";
import {changeTime, getTags} from "../../uilt/index.js";
import router from "../../router/index.js";
import {useRoute} from "vue-router";
import {useCounterStore} from "../../store/index.js";
import {ElMessage, ElMessageBox} from "element-plus";

const route = useRoute()

const Store = useCounterStore()

const input = ref('');

const value = ref('');

const options = ref([])

const ArtInfo = ref([''])

function clickItem(aid){
  router.push(`/article/write`)
  Store.SelectMenu(Store,{
    name:"添加文章",
    label:"添加文章",
    path:"/article/write"
  })
}

const update = (item)=>{

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

const currentChange = ()=>{

}

const UpArt = (aid)=>{
  router.push(`/article/write/${aid}`)
}

  getTag((res) => {
    options.value = res;
  });

const getArtInfo =  () => {
  getArticle((res) => {
    ArtInfo.value = res;
    ArtInfo.value =getTags(ArtInfo.value)
    ArtInfo.value = changeTime(ArtInfo.value);
    console.log(ArtInfo.value)
  })
}

onMounted(()=>{
  getArtInfo()
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
        <el-select v-model="value" placeholder="标签名" style="width: 240px">
          <el-option
              v-for="item in options"
              :key="item.tid"
              :label="item.tagName"
              :value="item.tagName"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" :icon="Search" >搜索</el-button>
        <el-button :icon="Refresh" @click="input = ''">重置</el-button>
      </el-form-item>
    </div>
  </el-form>

  <el-card style="max-width: 100vw">
    <el-button type="success" :icon="Plus" @click="clickItem(0)">新增</el-button>

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
          <el-tag v-if="scope.row.del === 1" type="danger">已下架</el-tag>
          <el-tag v-if="scope.row.del === 2" type="success">已发布</el-tag>
          <el-tag v-if="scope.row.del === 3" type="info">草稿箱</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope" >
          <el-button type="primary" @click="update(scope.row.aid)">修改</el-button>
          <el-button type="danger" @click="dArt(scope.row.aid)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 30px">
      <el-pagination background layout="prev, pager, next"
                     :total="ArtInfo.length"
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
  background-color: var(--cci-card-bg-color);
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
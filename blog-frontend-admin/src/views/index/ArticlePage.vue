<script setup>
import {Plus, Refresh, Search} from "@element-plus/icons-vue";
import {ref,onMounted} from "vue"
import {getTag} from "../../net/tag.js";
import {getArticle} from "../../net/article.js";
import {changeTime} from "../../uilt/index.js";
import router from "../../router/index.js";

const input = ref('');

const value = ref('');

const options = ref([])

const ArtInfo = ref([''])

function dArt(tid){

}

onMounted(() => {
  getTag((res) => {
    options.value = res;
  });

  getArticle((res) => {
    ArtInfo.value = res;
    ArtInfo.value = changeTime(ArtInfo.value);
  })
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
    <el-button type="success" :icon="Plus" @click="router.push('/article/write')">新增</el-button>

    <el-table
        :data="ArtInfo"
        border
        style="width: 100%;margin-top: 20px"
    >
      <el-table-column prop="img_url"  label="文章封面"/>
      <el-table-column prop="title" label="文章名称"/>
      <el-table-column prop="tags" label="标签名"/>
      <el-table-column prop="time" label="创建时间"/>
      <el-table-column label="操作" align="center">
        <template #default="scope" >
          <el-button type="primary" @click="UpArt(scope.row.aid)">修改</el-button>
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
</style>
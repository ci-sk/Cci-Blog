<script setup>
import { ref,onMounted } from 'vue';
import {Plus, Refresh, Search} from "@element-plus/icons-vue";
import {DelTag,getTagCount, insertTag, TagLimit, TagSearch} from "../../net/tag.js";
import {changeTime, formatTime} from "../../uilt/index.js";
import {ElMessage, ElMessageBox} from "element-plus";

const input = ref('');

const val = ref('')

const TagInfo = ref([]);

const total = ref('')

let dialogVisible = ref(false)

const addTags = ()=>{
  dialogVisible.value = false
  insertTag(val,(res)=>{
    ElMessage.success("添加成功");
    TagUser();
  })
}

const TagUser = () => {
  TagLimit(1,(res) => {
    console.log(res);
    TagInfo.value = res;
    TagInfo.value = changeTime(TagInfo.value);
  });
}

const dTag = (uid)=>{
  ElMessageBox.confirm('此操作将永久删除标签,是否继续', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    DelTag(uid, (data) => {
      console.log(uid, data, "!!");
      TagUser();
    });
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '已取消删除'
    })
  })
}

const currentChange = (val) => {
  TagLimit(val,(data)=>{
    TagInfo.value = data;
    TagInfo.value = changeTime(TagInfo.value);
  })
}

const search = ()=>{
  TagSearch(input,(data)=>{
    console.log(data);
    TagInfo.value = data;
    TagInfo.value = changeTime(TagInfo.value);
  })
}

//初始化加载数据
// onMounted(() => {
  TagUser();
  getTagCount((res)=>{
    total.value = res;
  })
// })

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
    <el-button type="success" :icon="Plus" @click="dialogVisible = true">新增</el-button>

    <el-dialog
        v-model="dialogVisible"
        title="新增标签"
        width="500"
    >
      <label for="">标签名:</label>
      <el-input
          style="width: 240px;margin-left: 10px"
          placeholder="请输入标签名称"
          clearable
          v-model="val"
      />
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="addTags">
            添加
          </el-button>
        </div>
      </template>
    </el-dialog>

    <el-table
        :data="TagInfo"
        border
        style="width: 100%;margin-top: 20px"
    >
      <el-table-column
          prop="tid"
          label="序号"
      />
      <el-table-column
        prop="tagName"
        label="标签名"
      />
      <el-table-column
          prop="time"
          label="创建时间"
      />
      <el-table-column label="操作" align="center">
        <template #default="scope" >
          <el-button type="danger" @click="dTag(scope.row.tid)">删除</el-button>
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
    width: 520px;
    display: flex;
    gap: 20px;
  }
}

</style>
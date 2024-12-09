<script setup>
import { ref, onMounted } from 'vue'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import {
  getLimitFriendLinks,
  getFriendLinkCount,
  searchFriendLinks,
  saveFriendLink,
  deleteFriendLink
} from '../../net/friendlink'

const searchText = ref('')
const loading = ref(false)
const friendLinkList = ref([])
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)

// 表单相关
const dialogVisible = ref(false)
const dialogType = ref('add')
const formRef = ref()
const form = ref({
  fid: '',
  fname: '',
  describes: '',
  url: '',
  icon: ''
})

const rules = {
  fname: [
    { required: true, message: '请输入友链名称', trigger: 'blur' },
    { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
  ],
  describes: [
    { required: true, message: '请输入友链描述', trigger: 'blur' },
    { max: 200, message: '最多200个字符', trigger: 'blur' }
  ],
  url: [
    { required: true, message: '请输入友链URL', trigger: 'blur' },
    { type: 'url', message: '请输入正确的URL格式', trigger: 'blur' }
  ],
  icon: [
    { required: true, message: '请输入图标链接', trigger: 'blur' },
    { type: 'url', message: '请输入正确的URL格式', trigger: 'blur' }
  ]
}

// 提交表单
const submitForm = () => {
  if (!formRef.value) return

  formRef.value.validate((valid) => {
    if (valid) {
      const data = {
        fname: form.value.fname,
        describes: form.value.describes,
        url: form.value.url,
        icon: form.value.icon
      }

      if (dialogType.value === 'add') {
        saveFriendLink(data, () => {
          ElMessage.success('添加成功')
          dialogVisible.value = false
          fetchFriendLinkList()
        })
      } else {
        saveFriendLink({
          ...data,
          fid: form.value.fid
        }, () => {
          ElMessage.success('更新成功')
          dialogVisible.value = false
          fetchFriendLinkList()
        })
      }
    }
  })
}

// 处理表单提交
const handleSubmit = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      submitForm()
    }
  })
}

// 获取友链列表
const fetchFriendLinkList = () => {
  loading.value = true
  getLimitFriendLinks({
    text: searchText.value,
    page: currentPage.value
  }, (data) => {
    friendLinkList.value = data
    // 获取总数
    getFriendLinkCount((count) => {
      total.value = count
      loading.value = false
    })
  }, () => {
    loading.value = false
  })
}

// 新增友链
const handleAdd = () => {
  dialogType.value = 'add'
  form.value = {
    fid: '',
    fname: '',
    describes: '',
    url: '',
    icon: ''
  }
  dialogVisible.value = true
}

// 编辑友链
const handleEdit = (row) => {
  dialogType.value = 'edit'
  form.value = { ...row }
  dialogVisible.value = true
}

// 删除友链
const handleDelete = (row) => {
  ElMessageBox.confirm(
      '确定要删除该友链吗？',
      '警告',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
  ).then(() => {
    deleteFriendLink(row.fid, () => {
      ElMessage.success('删除成功')
      fetchFriendLinkList()
    })
  })
}

const handleSizeChange = (val) => {
  pageSize.value = val
  fetchFriendLinkList()
}

const handleCurrentChange = (val) => {
  currentPage.value = val
  fetchFriendLinkList()
}

onMounted(() => {
  fetchFriendLinkList()
})
</script>


<template>
  <div class="friendlink-container">
    <div class="header">
      <el-input
        v-model="searchText"
        placeholder="搜索友链名称"
        class="search-input"
        clearable
        @keyup.enter="fetchFriendLinkList"
      >
        <template #append>
          <el-button @click="fetchFriendLinkList">
            <el-icon><Search /></el-icon>
          </el-button>
        </template>
      </el-input>
      
      <el-button type="primary" @click="handleAdd">
        <el-icon><Plus /></el-icon>新增友链
      </el-button>
    </div>

    <el-table :data="friendLinkList" style="width: 100%" v-loading="loading" table-layout="fixed">
      <el-table-column prop="fid" label="ID"/>
      <el-table-column prop="fname" label="名称" />
      <el-table-column prop="describes" label="描述" />
      <el-table-column prop="url" label="链接">
        <template #default="scope">
          <el-link type="primary" :href="scope.row.url" target="_blank">{{ scope.row.url }}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="icon" label="图标">
        <template #default="scope">
          <el-image 
            style="width: 40px; height: 40px"
            :src="scope.row.icon"
            fit="cover"
            :preview-src-list="[scope.row.icon]"
          />
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template #default="scope">
          <el-button type="primary" @click="handleEdit(scope.row)">
<!--            <el-icon><Edit /></el-icon>-->
            编辑
          </el-button>
          <el-button type="danger" @click="handleDelete(scope.row)">
<!--            <el-icon><Delete /></el-icon>-->
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin-top: 30px">
      <el-pagination background layout="prev, pager, next"
                     :total="total"
                     @current-change="handleCurrentChange" />
    </div>
    <!-- 新增/编辑对话框 -->
    <el-dialog
      :title="dialogType === 'add' ? '新增友链' : '编辑友链'"
      v-model="dialogVisible"
      width="500px"
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="80px"
      >
        <el-form-item label="名称" prop="fname">
          <el-input v-model="form.fname" placeholder="请输入友链名称" />
        </el-form-item>
        <el-form-item label="描述" prop="describes">
          <el-input
            v-model="form.describes"
            type="textarea"
            placeholder="请输入友链描述"
          />
        </el-form-item>
        <el-form-item label="链接" prop="url">
          <el-input v-model="form.url" placeholder="请输入友链URL" />
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入图标链接" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSubmit">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>



<style scoped>
.friendlink-container {
  padding: 20px;
}

.header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.search-input {
  width: 300px;
}

.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>

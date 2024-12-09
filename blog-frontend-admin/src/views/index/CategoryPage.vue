<script setup>
import { ref, onMounted } from 'vue'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getCategory, getLimitCategory, getCountCategory, addCategory, updateCategory, deleteCategory } from '../../net/category.js'

const loading = ref(false)
const searchText = ref('')
const categoryList = ref([])
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const dialogVisible = ref(false)
const dialogType = ref('add') // 'add' or 'edit'
const formRef = ref(null)

const form = ref({
  id: null,
  name: ''
})

const rules = {
  name: [
    { required: true, message: '请输入分类名称', trigger: 'blur' },
    { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
  ]
}

// 获取分类列表
const fetchCategoryList = () => {
  loading.value = true
  getLimitCategory({
    text: searchText.value,
    page: currentPage.value
  }, (data) => {
    categoryList.value = data
    // 获取总数
    getCountCategory((count) => {
      total.value = count
      loading.value = false
    })
  }, () => {
    loading.value = false
  })
}

// 搜索
const handleSearch = () => {
  currentPage.value = 1
  fetchCategoryList()
}

// 新增分类
const handleAdd = () => {
  dialogType.value = 'add'
  form.value = {
    id: null,
    name: ''
  }
  dialogVisible.value = true
}

// 编辑分类
const handleEdit = (row) => {
  dialogType.value = 'edit'
  form.value = { ...row }
  dialogVisible.value = true
}

// 删除分类
const handleDelete = (row) => {
  ElMessageBox.confirm(
      '确定要删除该分类吗？',
      '警告',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
  ).then(() => {
    deleteCategory(row.id, () => {
      ElMessage.success('删除成功')
      fetchCategoryList()
    })
  }).catch(() => {})
}

// 提交表单
const handleSubmit = () => {
  if (!formRef.value) return

  formRef.value.validate((valid) => {
    if (valid) {
      const data = {
        name: form.value.name
      }
      if (dialogType.value === 'add') {
        addCategory(data.name, () => {
          ElMessage.success('添加成功')
          dialogVisible.value = false
          fetchCategoryList()
        })
      } else {
        updateCategory(form.value.id, data, () => {
          ElMessage.success('修改成功')
          dialogVisible.value = false
          fetchCategoryList()
        })
      }
    }
  })
}

const handleSizeChange = (val) => {
  pageSize.value = val
  fetchCategoryList()
}

const handleCurrentChange = (val) => {
  currentPage.value = val
  fetchCategoryList()
}

onMounted(() => {
  fetchCategoryList()
})
</script>



<template>
  <div class="category-container">
    <div class="header">
      <el-input
        v-model="searchText"
        placeholder="搜索分类名称"
        class="search-input"
        clearable
        @clear="handleSearch"
        @keyup.enter="handleSearch"
      >
        <template #append>
          <el-button @click="handleSearch">
            <el-icon><Search /></el-icon>
          </el-button>
        </template>
      </el-input>
      
      <el-button type="primary" @click="handleAdd">
        <el-icon><Plus /></el-icon>新增分类
      </el-button>
    </div>

    <el-table :data="categoryList" v-loading="loading" table-layout='fixed'>
      <el-table-column prop="id" label="ID"/>
      <el-table-column prop="name" label="分类名称" />
      <el-table-column label="操作" width="200" align="center">
        <template #default="scope">
          <el-button type="primary" @click="handleEdit(scope.row)">
            编辑
          </el-button>
          <el-button type="danger" @click="handleDelete(scope.row)">
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
      :title="dialogType === 'add' ? '新增分类' : '编辑分类'"
      v-model="dialogVisible"
      width="500px"
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="80px"
      >
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入分类名称" />
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
.category-container {
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

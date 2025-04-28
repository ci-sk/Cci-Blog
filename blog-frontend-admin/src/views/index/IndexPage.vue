<script setup>
import { ref, onMounted } from "vue";
import HotMap from "../../components/home/HotMap.vue";
import HomeTop from "../../components/home/HomeTop.vue";
import WordCloud from "../../components/home/WordCloud.vue";
import CategoryPieChart from "../../components/home/CategoryPieChart.vue";
import { ElMessage } from 'element-plus';
import { getCategoryStats } from '../../net/category';

const newsFeed = ref([]);
const categoryData = ref([]);

// 获取分类统计数据
const fetchCategoryStats = () => {
  getCategoryStats((res) => {
    if (res.code === 200 && Array.isArray(res.data)) {
      categoryData.value = res.data.map(item => ({
        value: item.count,
        name: item.name
      }));
    } else {
      ElMessage.warning('获取分类统计失败');
    }
  });
};

// 模拟获取最新动态
const fetchNewsFeed = () => {
  newsFeed.value = [
    { id: 1, timestamp: '2023-10-01', content: '发布了新文章: Vue.js 实战' },
    { id: 2, timestamp: '2023-09-28', content: '系统维护公告' },
  ];
};

onMounted(() => {
  fetchCategoryStats();
  fetchNewsFeed();
});
</script>

<template>
  <div class="home">
    <HomeTop />
    <HotMap class="hotmap" />
    <div class="flex-container">
      <CategoryPieChart :data="categoryData" class="flex-item" />
      <WordCloud class="flex-item" />
    </div>

    <!-- 最新动态 -->
    <el-timeline>
      <el-timeline-item v-for="item in newsFeed" :key="item.id" :timestamp="item.timestamp">
        {{ item.content }}
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<style scoped>
.home {
  width: 100%;
  min-height: 100vh;
  padding: 20px;
  box-sizing: border-box;
}

.hotmap {
  margin-bottom: 100px; /* 添加底部间距 */
}

.flex-container {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 20px;
}

.flex-item {
  flex: 1;
  margin: 0 10px;
  max-width: 45%;
  padding: 20px;
  border-radius: var(--el-border-radius-base);
  background-color: var(--cci-component-bg);
  border: 1px solid var(--cci-component-border);
  box-shadow: 0 2px 12px 0 var(--cci-component-shadow);
}

:deep(.el-timeline-item__content) {
  color: var(--el-text-color-primary);
}

:deep(.el-timeline-item__timestamp) {
  color: var(--el-text-color-secondary);
}

:deep(.el-timeline-item__node) {
  background-color: var(--el-color-primary-light-3);
}

:deep(.el-timeline-item__tail) {
  border-left-color: var(--cci-component-border);
}
</style>

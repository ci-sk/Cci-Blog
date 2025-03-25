<script setup>
import { ref, computed } from "vue";

// 定义 props
const props = defineProps({
  data: {
    type: Array,
    required: true, // 数据源
  },
  perPage: {
    type: Number,
    default: 9, // 每页显示的数量，4行x3列
  },
});

const emit = defineEmits(["update:currentData"]);

// 当前页码
const currentPage = ref(1);

// 计算总页数
const totalPages = computed(() => Math.ceil(props.data.length / props.perPage));

// 计算当前页的数据
const paginatedData = computed(() => {
  const start = (currentPage.value - 1) * props.perPage;
  const end = start + props.perPage;
  return props.data.slice(start, end);
});

// 生成页码数组
const pages = computed(() => {
  const pages = [];
  for (let i = 1; i <= totalPages.value; i++) {
    pages.push(i);
  }
  return pages;
});

// 切换页码
const changePage = (page) => {
  if (page >= 1 && page <= totalPages.value) {
    currentPage.value = page;
    // 发射当前页数据给父组件
    emit("update:currentData", paginatedData.value);
  }
};

// 重置到第一页的方法，供父组件调用
const resetPage = () => {
  currentPage.value = 1;
  emit("update:currentData", paginatedData.value);
};

// 暴露方法给父组件
defineExpose({
  resetPage,
});

// 初始化时发射第一页数据
emit("update:currentData", paginatedData.value);
</script>

<template>
  <div class="flex flex-col items-center gap-4">
    <!-- 分页按钮 -->
    <div class="join">
      <!-- 上一页按钮 -->
      <button
        class="join-item btn"
        :disabled="currentPage === 1"
        @click="changePage(currentPage - 1)"
      >
        上一页
      </button>

      <!-- 页码按钮 -->
      <button
        v-for="page in pages"
        :key="page"
        class="join-item btn"
        :class="{ 'btn-active': currentPage === page }"
        @click="changePage(page)"
      >
        {{ page }}
      </button>

      <!-- 下一页按钮 -->
      <button
        class="join-item btn"
        :disabled="currentPage === totalPages"
        @click="changePage(currentPage + 1)"
      >
        下一页
      </button>
    </div>

    <!-- 显示当前页数据 -->
    <!--    <div class="w-full max-w-md">-->
    <!--      <div-->
    <!--          v-for="item in paginatedData"-->
    <!--          :key="item.id"-->
    <!--          class="card bg-base-100 shadow-md mb-4"-->
    <!--      >-->
    <!--        <div class="card-body">-->
    <!--          <h2 class="card-title">{{ item.name }}</h2>-->
    <!--          <p>这是第 {{ item.id }} 条数据。</p>-->
    <!--        </div>-->
    <!--      </div>-->
    <!--    </div>-->
  </div>
</template>

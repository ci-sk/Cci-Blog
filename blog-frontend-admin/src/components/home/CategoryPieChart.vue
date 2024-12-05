<template>
  <div ref="categoryChart" class="chart"></div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import * as echarts from 'echarts';

// 模拟分类数据
const categoryData = [
  { value: 40, name: '技术' },
  { value: 30, name: '生活' },
  { value: 20, name: '旅行' },
  { value: 10, name: '其他' }
];

const categoryChart = ref(null);

const initCategoryChart = () => {
  const myChart = echarts.init(categoryChart.value);
  const option = {
    title: {
      text: '分类数据',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: '分类',
        type: 'pie',
        radius: ['30%', '70%'],
        roseType: 'radius',
        data: categoryData,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };
  myChart.setOption(option);
};

onMounted(() => {
  initCategoryChart();
});
</script>

<style scoped>
.chart {
  width: 400px; /* 调整宽度以使图表更小 */
  height: 300px; /* 调整高度以使图表更小 */
  margin: 0 auto;
}
</style>

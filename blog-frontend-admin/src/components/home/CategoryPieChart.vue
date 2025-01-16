<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';
import {getCategoryStats} from "../../net/category.js";

const categoryChart = ref(null);
let chart = null;

const initCategoryChart = () => {
  if (!categoryChart.value) return;

  chart = echarts.init(categoryChart.value);
  updateChart();

  // 监听窗口大小变化
  window.addEventListener('resize', () => {
    chart && chart.resize();
  });
};

const updateChart = async () => {

  const arr = await getCategoryStats().then((res) => {
    return res.map(item => ({
      value: item.count,
      name: item.name
    }));
  });

  if (!chart) return;

  const option = {
    title: {
      text: '文章分类统计',
      left: 'center',
      textStyle: {
        fontSize: 16,
        fontWeight: 'normal'
      }
    },
    tooltip: {
      trigger: 'item',
      formatter: '{b}: {c} 篇 ({d}%)'
    },
    legend: {
      orient: 'vertical',
      left: 'left',
      top: 'middle'
    },
    series: [
      {
        name: '分类统计',
        type: 'pie',
        radius: ['40%', '70%'],
        center: ['60%', '50%'],
        roseType: 'radius',
        itemStyle: {
          borderRadius: 5
        },
        label: {
          show: true,
          formatter: '{b}: {c}篇'
        },
        data: arr || [],
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

  chart.setOption(option);
};

onMounted(() => {
  initCategoryChart();
});

// 组件卸载时清理
onUnmounted(() => {
  if (chart) {
    chart.dispose();
    chart = null;
  }
  window.removeEventListener('resize', chart && chart.resize);
});
</script>

<template>
  <div ref="categoryChart" class="chart"></div>
</template>



<style scoped>
.chart {
  width: 400px; /* 调整宽度以使图表更小 */
  height: 300px; /* 调整高度以使图表更小 */
  margin: 0 auto;
}
</style>

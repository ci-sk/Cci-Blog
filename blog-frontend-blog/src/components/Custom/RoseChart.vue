<script setup>
import { onMounted } from 'vue'
import * as echarts from 'echarts'

const props = defineProps({
  title: {
    type: String,
    default: '分类统计'
  },
  chartData: {
    type: Array,
    required: true,
    default: () => [
      { value: 40, name: '分类1' },
      { value: 38, name: '分类2' },
      { value: 32, name: '分类3' },
      { value: 30, name: '分类4' },
      { value: 28, name: '分类5' }
    ]
  },
  width: {
    type: [String, Number],
    default: 600
  },
  height: {
    type: [String, Number],
    default: 400
  }
})

onMounted(() => {
  const chartDom = document.getElementById('rose-chart')
  const myChart = echarts.init(chartDom)

  const option = {
    title: {
      text: props.title,
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      top: 'bottom'
    },
    series: [
      {
        name: '分类',
        type: 'pie',
        radius: [20, 100],
        center: ['50%', '50%'],
        roseType: 'area',
        itemStyle: {
          borderRadius: 8
        },
        data: props.chartData
      }
    ]
  }

  myChart.setOption(option)
})
</script>

<template>
  <div class="flex justify-center">
    <div id="rose-chart" :style="{ width: typeof width === 'number' ? `${width}px` : width, height: typeof height === 'number' ? `${height}px` : height }"></div>
  </div>
</template>

<style scoped>
/* 可以根据需要添加样式 */
</style>

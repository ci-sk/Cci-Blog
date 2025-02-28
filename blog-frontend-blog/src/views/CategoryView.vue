<script setup>
import RoseChart from '../components/Custom/RoseChart.vue'

const chartData = [
  { value: 40, name: '分类1' },
  { value: 38, name: '分类2' },
  { value: 32, name: '分类3' },
  { value: 30, name: '分类4' },
  { value: 28, name: '分类5' }
]
</script>

<template>
  <div>
    <h1>分类</h1>
    <RoseChart :title="`文章分类统计图`"  :chartData="chartData"  />
  </div>
</template>

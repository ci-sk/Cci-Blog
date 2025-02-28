<script setup>
import RoseChart from '../components/Custom/RoseChart.vue'
const chartData = [
  { value: 100, name: '标签1' },
  { value: 100, name: '标签2' },
  { value: 100, name: '标签3' },
  { value: 100, name: '标签4' },
  { value: 100, name: '标签5' },
]

</script>

<template>
  <div>
    <h1>标签</h1>
    <RoseChart :title="`文章标签图`"  :chartData="chartData"/>
  </div>
</template>

<style scoped>

</style>